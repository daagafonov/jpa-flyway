package net.anotheria.jpaflyway.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.anoprise.metafactory.MetaFactory;
import net.anotheria.anoprise.metafactory.MetaFactoryException;
import net.anotheria.jpaflyway.services.user.UserService;
import net.anotheria.maf.action.ActionCommand;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;

public class UsersListAction extends BaseAction {

	private UserService userService;

	public UsersListAction() {
		try {
			userService = MetaFactory.get(UserService.class);
		} catch (MetaFactoryException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ActionCommand execute(ActionMapping mapping, FormBean formBean, HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.setAttribute("users", userService.getAllUsers());

		return mapping.success();
	}

}
