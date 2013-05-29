package net.anotheria.jpaflyway.services.user;

import net.anotheria.anoprise.metafactory.ServiceFactory;

public class UserServiceFactory implements ServiceFactory<UserService>{

	@Override
	public UserService create() {
		return new UserServiceImpl();
	}
	
}
