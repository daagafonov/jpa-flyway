package net.anotheria.jpaflyway.services.user.persistence.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public class AbstractDAO<K extends Serializable, T extends Serializable> {

	private EntityManager entityManager;

	private Class<T> clazz;

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public AbstractDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public AbstractDAO(EntityManager entityManager, Class<T> clazz) {
		this(entityManager);
		this.clazz = clazz;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public T getById(K id) {
		return this.entityManager.find(this.clazz, id);
	}

	public List<T> findAll() {
		return this.entityManager.createQuery("from " + this.clazz.getName()).getResultList();
	}

	public void save(T entity) {
		this.entityManager.persist(entity);
	}

	public void update(T entity) {
		this.entityManager.merge(entity);
	}

	public void delete(T entity) {
		this.entityManager.remove(entity);
	}

	public void deleteById(K entityId) {
		T entity = this.getById(entityId);
		this.delete(entity);
	}

}
