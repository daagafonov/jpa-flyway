package net.anotheria.jpaflyway.services.user.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import net.anotheria.jpaflyway.services.shared.persistence.jpa.AbstractPersistenceServiceImpl;
import net.anotheria.jpaflyway.services.user.persistence.UserPersistenceService;
import net.anotheria.jpaflyway.services.user.persistence.UserPersistenceServiceException;

public class JPAUserPersistenceServiceImpl extends AbstractPersistenceServiceImpl<Long, User> implements UserPersistenceService {

	public JPAUserPersistenceServiceImpl() {
		super("jpa-users-example");
		setKlass(User.class);
	}

	@Override
	public List<User> getAllUsers() throws UserPersistenceServiceException {
		return super.findAll();
	}
	
	@Override
	public void create(User user) throws UserPersistenceServiceException {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(user);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		}
	}

}
