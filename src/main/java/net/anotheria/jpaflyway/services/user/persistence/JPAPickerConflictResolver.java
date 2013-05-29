package net.anotheria.jpaflyway.services.user.persistence;

import java.util.Collection;

import net.anotheria.anoprise.metafactory.OnTheFlyConflictResolver;

public class JPAPickerConflictResolver implements OnTheFlyConflictResolver {

	@Override
	public <T> Class<? extends T> resolveConflict(Collection<Class<? extends T>> classes) {
		for (Class<? extends T> clazz : classes) {
			if (clazz.getSimpleName().toLowerCase().indexOf("jpa") != -1)
				return clazz;
		}
		return null;
	}

}
