package net.anotheria.jpaflyway.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anotheria.maf.action.Action;
import net.anotheria.maf.action.ActionMapping;

public abstract class BaseAction implements Action {
	
	@Override
	public void postProcess(ActionMapping mapping, HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void preProcess(ActionMapping mapping, HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub

	}

}
