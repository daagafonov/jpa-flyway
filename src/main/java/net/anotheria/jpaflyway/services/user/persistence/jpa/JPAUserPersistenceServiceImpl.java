package net.anotheria.jpaflyway.services.user.persistence.jpa;

import java.util.List;

import net.anotheria.db.dao.DAOException;
import net.anotheria.jpaflyway.entity.User;
import net.anotheria.jpaflyway.services.user.persistence.AbstractPersistenceServiceImpl;
import net.anotheria.jpaflyway.services.user.persistence.UserPersistenceService;
import net.anotheria.jpaflyway.services.user.persistence.UserPersistenceServiceException;

public class JPAUserPersistenceServiceImpl extends AbstractPersistenceServiceImpl implements UserPersistenceService {

	private UserDAO dao;

	public JPAUserPersistenceServiceImpl() {
		super("jpa-users-example");
		dao = new UserDAOImpl(getEntityManager());
	}

	@Override
	public List<User> getAllUsers() throws UserPersistenceServiceException {
		try {
			return dao.getAllUsers();
		} catch (DAOException e) {
			throw new UserPersistenceServiceException("dao.getAllUsers() failed", e);
		}
	}
	
	@Override
	public void create(User user) throws UserPersistenceServiceException {
		try {
			dao.create(user);
		} catch (DAOException e) {
			throw new UserPersistenceServiceException("dao.getAllUsers() failed", e);
		}
	}

}
