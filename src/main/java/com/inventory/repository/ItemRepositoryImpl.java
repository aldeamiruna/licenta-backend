package com.inventory.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.inventory.dto.BuyoutDto;
import com.inventory.model.Item;

public class ItemRepositoryImpl implements ItemRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getInventoryItems() {
		StoredProcedureQuery inventory = em.createStoredProcedureQuery("ispGetInventoryItems", Item.class);
		List<Item> result = inventory.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getProducers() {
		StoredProcedureQuery inventory = em.createStoredProcedureQuery("ispGetProducers", String.class);
		List<String> result = inventory.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getInventoryItemsOrderedDesc() {
		StoredProcedureQuery inventory = em.createStoredProcedureQuery("ispGetInventoryItemsOrderedDesc", Item.class);
		List<Item> result = inventory.getResultList();
		return result;
	}

	@Override
	public Boolean addItem(Item item) {
		long inventoryNumber = 0;
		Integer itemType = null;
		Integer roomId = null;
		String seriaId = null;
		Integer itemStatus = null;
		String model = null;
		String comment = null;
		Integer ownerId = null;
		String producer = null;
		try {
			Item fetchedNewItem = item;
			System.out.println(fetchedNewItem);
			if (fetchedNewItem.getInventoryNumber() != 0) {
				inventoryNumber = fetchedNewItem.getInventoryNumber();
			}
			if (fetchedNewItem.getItemType() != null) {
				itemType = fetchedNewItem.getItemType().getId();
			}
			if (fetchedNewItem.getRoom() != null) {
				roomId = fetchedNewItem.getRoom().getId();
			}
			if (fetchedNewItem.getSerialId() != null) {
				seriaId = fetchedNewItem.getSerialId();
			}
			if (fetchedNewItem.getState() != null) {
				itemStatus = fetchedNewItem.getState().getId();
			}
			if (fetchedNewItem.getModel() != null) {
				model = fetchedNewItem.getModel();
				model = model.substring(0, 1).toUpperCase() + model.substring(1).toLowerCase();// sets first letter
																								// capital
				model.trim();
			}
			if (fetchedNewItem.getComment() != null) {
				comment = fetchedNewItem.getComment();
			}
			if (fetchedNewItem.getUser() != null) {
				ownerId = fetchedNewItem.getUser().getId();
			}
			if (fetchedNewItem.getProducer() != null) {
				producer = fetchedNewItem.getProducer();
				producer = producer.substring(0, 1).toUpperCase() + producer.substring(1).toLowerCase();// sets first
																										// letter
																										// capital
				producer.trim();
			}
			System.out.printf(String.format(
					"inventoryNr: %s , itemType: %s ,itemStatus: %s ,model: %s ,comment: %s ,ownerId: %s ,producer: %s,seriaId: %s,roomId: %s",
					inventoryNumber, itemType, itemStatus, model, comment, ownerId, producer, seriaId, roomId));
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("ispAddItemAndHistoryRecord");// TODO
																												// this
																												// is
																												// working
			// ,dont delete
			storedProcedure.registerStoredProcedureParameter("ownerId", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("roomId", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("typeId", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("seriaId", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("model", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("producer", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("inventoryNumber", Long.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("statusId", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("comment", String.class, ParameterMode.IN);
			storedProcedure.setParameter("ownerId", ownerId);
			storedProcedure.setParameter("typeId", itemType);
			storedProcedure.setParameter("roomId", roomId);
			storedProcedure.setParameter("seriaId", seriaId);
			storedProcedure.setParameter("inventoryNumber", inventoryNumber);
			storedProcedure.setParameter("statusId", itemStatus);
			storedProcedure.setParameter("model", model);
			storedProcedure.setParameter("comment", comment);
			storedProcedure.setParameter("producer", producer);
			storedProcedure.execute();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuyoutDto> getBuyoutInfo() {

		StoredProcedureQuery inventory = em.createStoredProcedureQuery("ispGetBuyout", "ItemUserMapping");

		List<BuyoutDto> results = inventory.getResultList();

		return results;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getRecentAssets() {
		
		StoredProcedureQuery inventory = em.createStoredProcedureQuery("ispGetRecentAssets", Item.class);

		List<Item> results = inventory.getResultList();

		return results;
	}

}
