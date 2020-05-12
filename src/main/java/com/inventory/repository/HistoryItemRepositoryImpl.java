package com.inventory.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.inventory.model.HistoryItem;

public class HistoryItemRepositoryImpl {
	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<HistoryItem> getItemHistory(Integer itemId) {
		try {
			System.out.println(itemId);
			
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("hspItemHistory",HistoryItem.class);
																								// ,dont delete
			storedProcedure.registerStoredProcedureParameter("itemId", Integer.class, ParameterMode.IN);
			storedProcedure.setParameter("itemId", itemId);
			storedProcedure.getResultList();
			List<HistoryItem> result = storedProcedure.getResultList();
			return result;
		}catch(Exception e) {
			System.out.println(e);
			throw e;
		}
	}
}
