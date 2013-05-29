package net.anotheria.jpaflyway.services.user;

import java.util.List;

import net.anotheria.anoprise.metafactory.MetaFactory;
import net.anotheria.anoprise.metafactory.MetaFactoryException;
import net.anotheria.jpaflyway.services.user.persistence.UserPersistenceService;
import net.anotheria.jpaflyway.services.user.persistence.UserPersistenceServiceException;
import net.anotheria.jpaflyway.services.user.persistence.jpa.User;

public class UserServiceImpl implements UserService {
	
	private UserPersistenceService persistence;
	
	public UserServiceImpl() {
		try {
			persistence = MetaFactory.get(UserPersistenceService.class);
		} catch (MetaFactoryException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<User> getAllUsers() throws UserServiceException {
		try {
			return persistence.getAllUsers();
		} catch (UserPersistenceServiceException e) {
			throw new UserServiceException("", e);
		}
	}
	
	@Override
	public void create(User user) throws UserServiceException {
		try {
			persistence.create(user);
		} catch (UserPersistenceServiceException e) {
			throw new UserServiceException("", e);
		}
	}

}
