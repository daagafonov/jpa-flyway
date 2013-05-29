package net.anotheria.jpaflyway.services.user.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import net.anotheria.db.dao.DAOException;
import net.anotheria.jpaflyway.entity.User;

public class UserDAOImpl extends AbstractDAO<Long, User> implements UserDAO {

	public UserDAOImpl(EntityManager entityManager) {
		super(entityManager, User.class);
	}

	@Override
	public List<User> getAllUsers() throws DAOException {
		return findAll();
	}

	@Override
	public void create(User user) throws DAOException {
		EntityTransaction et = getEntityManager().getTransaction();
		try {
			et.begin();
			super.save(user);
			et.commit();
		} catch (Exception ex) {
			et.rollback();
		}
	}

}
