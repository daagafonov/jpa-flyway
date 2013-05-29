package net.anotheria.jpaflyway.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.anoprise.metafactory.MetaFactory;
import net.anotheria.anoprise.metafactory.MetaFactoryException;
import net.anotheria.jpaflyway.entity.User;
import net.anotheria.jpaflyway.forms.RegisterBean;
import net.anotheria.jpaflyway.services.user.UserService;
import net.anotheria.maf.action.ActionCommand;
import net.anotheria.maf.action.ActionMapping;
import net.anotheria.maf.bean.FormBean;
import net.anotheria.maf.bean.annotations.Form;

public class DoRegisterAction extends BaseAction {
	
	private UserService userService;

	public DoRegisterAction() {
		try {
			userService = MetaFactory.get(UserService.class);
		} catch (MetaFactoryException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ActionCommand execute(ActionMapping mapping, @Form(RegisterBean.class)FormBean formBean, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		RegisterBean bean = (RegisterBean)formBean;
		
		User user = new User();
		user.setLogin(bean.getLogin());
		user.setPassword(bean.getPassword());
		user.setEmail(bean.getEmail());
		user.setGender(bean.getGender());
		
		userService.create(user);
		
		return mapping.success();
	}
	
}
