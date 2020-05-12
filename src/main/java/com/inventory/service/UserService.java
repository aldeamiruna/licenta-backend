package com.inventory.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.inventory.model.Item;
import com.inventory.model.User;
import com.inventory.model.UserStatus;
import com.inventory.repository.UserRepository;
import com.inventory.repository.UserStatusRepository;
import com.inventory.utils.Result;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private UserStatusRepository stateRepo;

	@Autowired
	ItemService itemService;

	public String addUser(User user) {
		Boolean check = repo.addUser(user);
		if (check) {
			return "Success";
		} else {
			return "Error";
		}
	}

	public String updateUser(User user) {
		try {
			repo.save(user);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Success";
	}

	public String deleteUser(int targetId) {
		/*try {
			List<User> itemList = repo.findById(targetId).get().getItems();
			User user = repo.getUserByCompanyId("BvOffice");
			for (Item item : itemList) {
				item.setUser(user);
				itemService.updateStateByType(item.getId().toString(), "Available");
				itemService.updateItem(item);
			}
			repo.deleteById(targetId);
		} catch (Exception e) {
			return e.getMessage();
		}*/
		return "Success";
	}

	public Result<List<User>> getAll() {
		Result<List<User>> result = new Result<List<User>>();
		try {
			List<User> list = new ArrayList<User>();
			repo.findAll().forEach(list::add);
			result.setContent(list);
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<User>> getUserByCompanyId(String targetId) {
		Result<List<User>> result = new Result<List<User>>();
		try {
			List<User> list = new ArrayList<User>();
			list.add(repo.getUserByCompanyId(targetId));
			result.setContent(list);
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<String>> getUsersByCompanyName() {
		Result<List<String>> result = new Result<List<String>>();
		try {
			List<User> users = new ArrayList<User>();
			repo.findAll().forEach(users::add);
			List<String> usersCompanyNames = new ArrayList<String>();
			users.forEach(user -> usersCompanyNames.add(user.getCompanyId()));
			result.setContent(usersCompanyNames);
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<User>> getUserTable() {
		Result<List<User>> result = new Result<List<User>>();
		try {
			List<User> users = repo.getUsers();
			result.setContent(users);
			result.setMessage("Success");
			return result;
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<User> updateStateByType(String userId, String stateType) {
		Result<User> result = new Result<User>();
		User user = repo.findById((Integer.parseInt(userId))).get();
		try {
			switch (stateType.toUpperCase()) {
			case "EMPLOYEE": {
				UserStatus state = stateRepo.findById(1).get();
				user.setStatus(state);
				repo.save(user);
				result.setContent(user);
				result.setMessage("Success");
				return result;
			}
			case "INTERN": {
				UserStatus state = stateRepo.findById(2).get();
				user.setStatus(state);
				repo.save(user);
				result.setContent(user);
				result.setMessage("Success");
				return result;
			}
			case "LEFT_THE_COMPANY": {
				UserStatus state = stateRepo.findById(3).get();
				user.setStatus(state);
				repo.save(user);
				result.setContent(user);
				result.setMessage("Success");
				return result;
			}
			case "MATERNITY_LEAVE": {
				UserStatus state = stateRepo.findById(4).get();
				user.setStatus(state);
				repo.save(user);
				result.setContent(user);
				result.setMessage("Success");
				return result;
			}
			case "OFFICE": {
				UserStatus state = stateRepo.findById(5).get();
				user.setStatus(state);
				repo.save(user);
				result.setContent(user);
				result.setMessage("Success");
				return result;
			}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			return result;
		}
		return result;
	}

	public Result<List<String>> getCompanyIds() {
		Result<List<String>> result = new Result<List<String>>();
		try {
			List<User> users = new ArrayList<User>();
			repo.findAll().forEach(users::add);
			Set<String> list = new LinkedHashSet<String>();
			users.forEach(item -> list.add(item.getCompanyId()));
			result.setContent(new ArrayList<>(list));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<String>> getLastNameList() {
		Result<List<String>> result = new Result<List<String>>();
		try {
			List<User> users = new ArrayList<User>();
			repo.findAll().forEach(users::add);
			Set<String> list = new LinkedHashSet<String>();
			users.forEach(item -> list.add(item.getLastName()));
			result.setContent(new ArrayList<>(list));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<String>> getFirstName() {
		Result<List<String>> result = new Result<List<String>>();
		try {
			List<User> users = new ArrayList<User>();
			repo.findAll().forEach(users::add);
			Set<String> list = new LinkedHashSet<String>();
			users.forEach(item -> list.add(item.getFirstName()));
			result.setContent(new ArrayList<>(list));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<String>> getEmailList() {
		Result<List<String>> result = new Result<List<String>>();
		try {
			List<User> users = new ArrayList<User>();
			repo.findAll().forEach(users::add);
			Set<String> list = new LinkedHashSet<String>();
			users.forEach(item -> list.add(item.getEmail()));
			result.setContent(new ArrayList<>(list));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<User>> filterUsersByStatus(User user) {
		Result<List<User>> result = new Result<List<User>>();

		try {
			result.setContent(repo.findAll(new Specification<User>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
					List<Predicate> predicates = new ArrayList<>();

					if (user.getStatus() != null) {
						predicates
								.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("status"), user.getStatus())));
					}

					return (criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

				}

			}));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}
	public Result<List<Item>> getLaptops(int id) {
        Result<List<Item>> result = new Result<List<Item>>();
        try {
        	List<Item> items = new ArrayList<Item>();
        	User user = repo.findById(id).get();
			user.getItems().forEach(items::add);
			List<Item> resultList = items.stream()
					.filter(item->item.getItemType().getType().equals("Laptop")).collect(Collectors.toList());
            result.setContent(resultList);
            result.setMessage("Success");
        } catch (Exception e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }
	
}
