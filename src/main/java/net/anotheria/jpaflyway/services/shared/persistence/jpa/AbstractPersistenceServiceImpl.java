package net.anotheria.jpaflyway.services.shared.persistence.jpa;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.anotheria.jpaflyway.services.user.persistence.jpa.JDBCConfig;
import net.anotheria.util.StringUtils;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.configureme.ConfigurationManager;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.MigrationInfoService;

/**
 * 
 * @author dagafonov
 * 
 * @param <T>
 */
public abstract class AbstractPersistenceServiceImpl<K extends Serializable, T extends Serializable> {

	private Logger log = Logger.getLogger(AbstractPersistenceServiceImpl.class);

	private String configName;

	private EntityManagerFactory emf;

	private Class<T> klass;

	public AbstractPersistenceServiceImpl() {
		this(null);
	}

	public AbstractPersistenceServiceImpl(String configName) {
		this.configName = configName;
		init();
	}

	public void setKlass(Class<T> klass) {
		this.klass = klass;
	}

	private void init() {

		BasicDataSource newDataSource = new BasicDataSource();
		if (configName == null)
			throw new IllegalStateException("Config not set");
		JDBCConfig config = new JDBCConfig();
		ConfigurationManager.INSTANCE.configureAs(config, configName);
		log.info("Using config: " + config);

		newDataSource.setDriverClassName(config.getDriver());
		newDataSource.setUrl(config.getUrl());
		newDataSource.setUsername(config.getUsername());
		newDataSource.setPassword(config.getPassword());

		if (config.getMaxConnections() != Integer.MAX_VALUE && config.getMaxConnections() > 0)
			newDataSource.setMaxActive(config.getMaxConnections());

		Flyway flyway = new Flyway();
		flyway.setDataSource(newDataSource);
		flyway.setLocations(getClass().getPackage().getName() + ".migrations");
		flyway.setTable(getTableNameForMigration());
		flyway.setInitOnMigrate(true);
		flyway.migrate();
		MigrationInfoService flywayInfo = flyway.info();

		log.info("Flyway current version:" + flywayInfo.current().getVersion());

		Map<String, String> map = new HashMap<String, String>();
		//
		map.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		map.put("hibernate.hbm2ddl.auto", "none");
		map.put("hibernate.show_sql", "true");
		map.put("hibernate.format_sql", "true");

		map.put("javax.persistence.jdbc.driver", config.getDriver()); // "org.hsqldb.jdbc.JDBCDriver"
		map.put("javax.persistence.jdbc.url", config.getUrl()); // "jdbc:hsqldb:/tmp/jpa-flyway"
		map.put("javax.persistence.jdbc.user", config.getUsername()); //
		map.put("javax.persistence.jdbc.password", ""); //

		emf = Persistence.createEntityManagerFactory("persistenceUnitName", map);
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	private String getTableNameForMigration() {
		String[] commonPackage = StringUtils.tokenize(AbstractPersistenceServiceImpl.class.getPackage().getName(), '.');
		String[] customPackage = StringUtils.tokenize(getClass().getPackage().getName(), '.');
		HashSet<String> parts = new HashSet<String>();
		for (String p : customPackage) {
			parts.add(p);
		}
		for (String p : commonPackage) {
			parts.remove(p);
		}

		StringBuilder name = new StringBuilder();
		for (Iterator<String> it = parts.iterator(); it.hasNext();) {
			if (name.length() > 0)
				name.append('_');
			name.append(it.next());
		}
		return "flyway_" + name.toString();
	}
	
	public T getById(K id) {
		return getEntityManager().find(this.klass, id);
	}

	public List<T> findAll() {
		return getEntityManager().createQuery("from " + this.klass.getName()).getResultList();
	}

	public void save(T entity) {
		getEntityManager().persist(entity);
	}

	public void update(T entity) {
		getEntityManager().merge(entity);
	}

	public void delete(T entity) {
		getEntityManager().remove(entity);
	}

	public void deleteById(K entityId) {
		T entity = this.getById(entityId);
		this.delete(entity);
	}

}
