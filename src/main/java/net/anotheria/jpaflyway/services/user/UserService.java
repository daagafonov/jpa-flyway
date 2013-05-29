package net.anotheria.jpaflyway.services.user;

import java.util.List;

import net.anotheria.anoprise.metafactory.Service;
import net.anotheria.jpaflyway.entity.User;

/**
 * 
 * @author dagafonov
 * 
 */
public interface UserService extends Service {

	List<User> getAllUsers() throws UserServiceException;

	void create(User user) throws UserServiceException;

}
