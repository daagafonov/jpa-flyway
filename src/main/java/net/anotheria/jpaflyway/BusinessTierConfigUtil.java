package net.anotheria.jpaflyway;

import net.anotheria.anoprise.metafactory.Extension;
import net.anotheria.anoprise.metafactory.MetaFactory;
import net.anotheria.jpaflyway.services.user.UserService;
import net.anotheria.jpaflyway.services.user.UserServiceFactory;

public class BusinessTierConfigUtil {
	
	public static void configure() {
		MetaFactory.reset();

        MetaFactory.addFactoryClass(UserService.class, Extension.LOCAL, UserServiceFactory.class);
        MetaFactory.addAlias(UserService.class, Extension.LOCAL);
	}

}
