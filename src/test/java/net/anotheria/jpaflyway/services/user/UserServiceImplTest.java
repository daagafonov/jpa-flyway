package net.anotheria.jpaflyway.services.user;

import net.anotheria.anoprise.metafactory.Extension;
import net.anotheria.anoprise.metafactory.MetaFactory;
import net.anotheria.anoprise.metafactory.MetaFactoryException;
import net.anotheria.jpaflyway.entity.User;
import net.anotheria.jpaflyway.services.user.persistence.JPAPickerConflictResolver;

import org.configureme.ConfigurationManager;
import org.configureme.environments.DynamicEnvironment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserServiceImplTest {
	
	@Before
	public void before() {
		
		ConfigurationManager.INSTANCE.setDefaultEnvironment(new DynamicEnvironment("test", "hsqldb"));
        MetaFactory.reset();

        MetaFactory.addFactoryClass(UserService.class, Extension.LOCAL, UserServiceFactory.class);
        MetaFactory.addAlias(UserService.class, Extension.LOCAL);

        MetaFactory.addOnTheFlyConflictResolver(new JPAPickerConflictResolver());
		
	}
	
	@After
	public void after() {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	UserService service;
	
	public UserService getService() throws MetaFactoryException {
		if (service == null) {
			service = MetaFactory.get(UserService.class);
		}
		return service;
	}
	
	@Test
	public void test() throws Exception {
		System.out.println("dima");
		System.out.println(getService());
		
		User user = new User();
		user.setLogin("daa");
		user.setEmail("daaaaaa@gmail.com");
		user.setPassword("passwd");
		
		getService().create(user);
	}
	
//	@Test
//	public void testA() throws Exception {
//		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaExampleUnitName");
//		EntityManager em = emf.createEntityManager();
//		
//		EntityTransaction et = em.getTransaction();
//		try {
//			et.begin();
//			em.persist(new User());
//			et.commit();
//		} catch (Exception e) {
//			et.rollback();
//			e.printStackTrace();
//		}
//		
//	}

}
