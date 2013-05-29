package net.anotheria.jpaflyway.services.user.persistence;

import java.util.List;

import net.anotheria.anoprise.metafactory.Service;
import net.anotheria.jpaflyway.entity.User;

/**
 * 
 * @author dagafonov
 * 
 */
public interface UserPersistenceService extends Service {

	List<User> getAllUsers() throws UserPersistenceServiceException;

	void create(User user) throws UserPersistenceServiceException;

}
