package net.anotheria.jpaflyway.actions;

import net.anotheria.maf.action.ActionForward;
import net.anotheria.maf.action.ActionMappings;
import net.anotheria.maf.action.ActionMappingsConfigurator;

public class JPAFlywayConfigurator implements ActionMappingsConfigurator {
	
	@Override
	public void configureActionMappings(ActionMappings mappings) {
		mappings.addMapping("", UsersListAction.class, new ActionForward("success", "/net/anotheria/jpaflyway/jsp/list.jsp"));
		mappings.addMapping("register", RegisterShowAction.class, new ActionForward("success", "/net/anotheria/jpaflyway/jsp/register.jsp"));
		mappings.addMapping("doRegister", DoRegisterAction.class, new ActionForward("success", "/net/anotheria/jpaflyway/jsp/gotolist.jsp"));
		
	}
	
}
