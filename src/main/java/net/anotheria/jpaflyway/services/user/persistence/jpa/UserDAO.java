package net.anotheria.jpaflyway.services.user.persistence.jpa;

import java.util.List;

import net.anotheria.db.dao.DAOException;
import net.anotheria.jpaflyway.entity.User;

public interface UserDAO {

	List<User> getAllUsers() throws DAOException;

	void create(User user) throws DAOException;

}
