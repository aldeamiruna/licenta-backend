package com.inventory.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.inventory.model.Item;
import com.inventory.model.User;

public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User getUserByCompanyId(String targetId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("uspGetUserByCompanyId");
		query.setParameter("companyId", targetId);
		User user = (User) query.getResultList().get(0);
		return user;
//		User user = (User) em.createNamedQuery("getUspUserByCompanyId", User.class)
//				.setParameter(1, targetId)
//				.getResultList();
//		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		StoredProcedureQuery query = em.createStoredProcedureQuery("uspGetUsers", User.class);
		List<User> result = query.getResultList();
		return result;
	}

	@Override
	public Boolean addUser(User user) {
		String companyId = null;
		String firstName = null;
		String lastName = null;
		String email = null;
		Integer statusId = null;
		try {
			User fetchedNewUser = user;
			System.out.println(fetchedNewUser);
			if (fetchedNewUser.getCompanyId() != null) {
				companyId = fetchedNewUser.getCompanyId();
			}
			if (fetchedNewUser.getFirstName() != null) {
				firstName = fetchedNewUser.getFirstName();
			}
			if (fetchedNewUser.getLastName() != null) {
				lastName = fetchedNewUser.getLastName();
			}
			if (fetchedNewUser.getEmail() != null) {
				email = fetchedNewUser.getEmail();
			}
			if (fetchedNewUser.getStatus() != null) {
				statusId = fetchedNewUser.getStatus().getId();
			}

			System.out.printf(String.format("companyId: %s , firstName: %s , lastName: %s ,email: %s ,statusId: %s ",
					companyId, firstName, lastName, email, statusId));
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("uspAddUser");// TODO this is working
																								// ,dont delete
			storedProcedure.registerStoredProcedureParameter("companyId", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("firstName", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("lastName", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("email", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("statusId", Integer.class, ParameterMode.IN);

			storedProcedure.setParameter("companyId", companyId);
			storedProcedure.setParameter("firstName", firstName );
			storedProcedure.setParameter("lastName", lastName);
			storedProcedure.setParameter("email", email);
			storedProcedure.setParameter("statusId", statusId);
			
	
			storedProcedure.execute();

			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
