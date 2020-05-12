package com.inventory.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.inventory.model.State;

public class StateRepositoryCustomImpl implements StateRepositoryCustom{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<State> getExistingItemStates(){
		Query inventory = em.createNamedQuery("getIspItemStates");
		@SuppressWarnings("unchecked")
		List<State> result = inventory.getResultList();
		return result;

	}
}
