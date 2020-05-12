package com.inventory.repository;

import java.util.List;

import com.inventory.model.User;

public interface UserRepositoryCustom {

	User getUserByCompanyId(String targetId);
	List<User> getUsers();
	public Boolean addUser(User user);

}
