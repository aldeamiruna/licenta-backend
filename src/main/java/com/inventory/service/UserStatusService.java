package com.inventory.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.model.UserStatus;
import com.inventory.repository.UserStatusRepository;
import com.inventory.utils.Result;

@Service

public class UserStatusService {
	@Autowired
	UserStatusRepository repo;

	public Result<List<UserStatus>> getAll() {// this is without stored procedure
		Result<List<UserStatus>> result = new Result<List<UserStatus>>();
		try {
			List<UserStatus> states = new ArrayList<UserStatus>();

			repo.findAll().forEach(states::add);

			result.setContent(states);
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}

		return result;
	}

	public Result<List<String>> getDistinctUserStatus() {
		Result<List<String>> result = new Result<List<String>>();
		try {
			List<UserStatus> userStatusList = new ArrayList<UserStatus>();
			repo.findAll().forEach(userStatusList::add);
			Set<String> userStatuses = new LinkedHashSet<String>();
			userStatuses.forEach(userStatus -> userStatuses.add(userStatus));
			result.setContent(new ArrayList<>(userStatuses));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
