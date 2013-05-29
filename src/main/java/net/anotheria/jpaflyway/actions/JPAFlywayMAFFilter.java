package net.anotheria.jpaflyway.actions;

import java.util.Arrays;
import java.util.List;

import net.anotheria.maf.MAFFilter;
import net.anotheria.maf.action.ActionMappingsConfigurator;

public class JPAFlywayMAFFilter extends MAFFilter {
	
	@Override
	protected List<ActionMappingsConfigurator> getConfigurators() {
		return Arrays.asList(new ActionMappingsConfigurator[] { new JPAFlywayConfigurator() });
	}

}
