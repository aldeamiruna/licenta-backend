package com.inventory.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import com.inventory.model.Item;
import com.inventory.model.ItemType;

public class ItemTypeRepositoryCustomImpl implements ItemTypeRepositoryCustom{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ItemType> getItemTypes() {
		Query inventory = em.createNamedQuery("getIspItemTypes");
		@SuppressWarnings("unchecked")
		List<ItemType> result = inventory.getResultList();
		return result;

	}
	
	@Override
	public void insertItemType(String type) {
		String itemType = type;
		try {
			itemType = itemType.substring(0,1).toUpperCase() + itemType.substring(1).toLowerCase();//sets first letter capital
			itemType.trim();
			System.out.printf(String.format("itemType: %s",itemType.trim()));
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("ispInsertItemType");
			storedProcedure.registerStoredProcedureParameter("itemType", String.class, ParameterMode.IN);
			storedProcedure.setParameter("itemType", itemType);
			storedProcedure.execute();
			
		}catch(Exception e) {
			throw e;
		}
	}
}
