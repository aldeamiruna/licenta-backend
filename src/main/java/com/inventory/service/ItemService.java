package com.inventory.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.inventory.dto.BuyoutDto;
import com.inventory.model.HistoryItem;
import com.inventory.model.Item;
import com.inventory.model.State;
import com.inventory.repository.ItemRepository;
import com.inventory.repository.StateRepository;
import com.inventory.utils.ItemStatus;
import com.inventory.utils.Result;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private HistoryItemService historyService;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private ModelMapper modelMapper;

	public Result<List<Item>> getIventoryItemsOrderedDesc() {// this is with stored procedure
		Result<List<Item>> result = new Result<List<Item>>();
		try {
			List<Item> items = itemRepo.getInventoryItemsOrderedDesc();
			result.setContent(items);
			result.setMessage("Success");
			return result;
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<Item>> getIventoryItems() {// this is with stored procedure
		Result<List<Item>> result = new Result<List<Item>>();
		try {
			List<Item> items = itemRepo.getInventoryItems();
			result.setContent(items);
			result.setMessage("Success");
			return result;
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<String>> getProducersByStoredProcedure() {
		Result<List<String>> result = new Result<List<String>>();
		try {
			List<String> producerList = new ArrayList<String>();
			producerList = itemRepo.getProducers();
			Set<String> producers = new LinkedHashSet<String>();
			producerList.forEach(producer -> producers.add(producer));
			result.setContent(new ArrayList<>(producers));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<Item>> getAll() {
		Result<List<Item>> result = new Result<List<Item>>();
		try {
			List<Item> items = new ArrayList<Item>();
			itemRepo.findAll().forEach(items::add);
			result.setContent(items);
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<String>> getAllProducers() {
		Result<List<String>> result = new Result<List<String>>();
		try {
			List<Item> items = new ArrayList<Item>();

			itemRepo.findAll().forEach(items::add);

			Set<String> producers = new LinkedHashSet<String>();

			items.forEach(item -> producers.add(item.getProducer()));
//			producers.stream().filter(x -> x != null).close();// WHY IS NOT WORKING LIKE THIS?:(((
			producers.removeAll(Collections.singleton(null));
//			System.out.println(producers);
			result.setContent(new ArrayList<>(producers));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<String>> getAllModels() {
		Result<List<String>> result = new Result<List<String>>();
		try {
			List<Item> items = new ArrayList<Item>();
			itemRepo.findAll().forEach(items::add);
			Set<String> models = new LinkedHashSet<String>();
			items.forEach(item -> models.add(item.getModel()));
			result.setContent(new ArrayList<>(models));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<String>> getAllSerialIds() {
		Result<List<String>> result = new Result<List<String>>();
		try {
			List<Item> items = new ArrayList<Item>();
			itemRepo.findAll().forEach(items::add);
			Set<String> serialIds = new LinkedHashSet<String>();
			items.forEach(item -> serialIds.add(item.getSerialId()));
			result.setContent(new ArrayList<>(serialIds));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<Long>> getInventoryNumberList() {
		Result<List<Long>> result = new Result<List<Long>>();
		try {
			List<Item> items = new ArrayList<Item>();
			itemRepo.findAll().forEach(items::add);
			Set<Long> inventoryNumberList = new LinkedHashSet<Long>();
			items.forEach(item -> inventoryNumberList.add(item.getInventoryNumber()));
			result.setContent(new ArrayList<>(inventoryNumberList));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<Item> getById(int id) {
		Result<Item> result = new Result<Item>();
		try {
			result.setContent(itemRepo.findById(id).get());
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<HistoryItem>> getHistory(int itemId) {
		Result<List<HistoryItem>> result = new Result<List<HistoryItem>>();
		try {
			List<HistoryItem> history = new ArrayList<HistoryItem>();
			Item item = itemRepo.findById(itemId).get();
			item.getHistoryItems().forEach(history::add);
			result.setContent(history);
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<Item>> filterItemsByStatus(String status) {
		Result<List<Item>> result = new Result<List<Item>>();
		try {
			result.setContent(((Collection<Item>) itemRepo.findAll()).stream()
					.filter(item -> item.getState().equals(ItemStatus.valueOf(status.toUpperCase())))
					.collect(Collectors.toList()));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public String addItem(Item item) {
		Boolean check = itemRepo.addItem(item);
		if (check) {
			return "Success";
		} else {
			return "Error";
		}
	}

	public Result<Item> updateStateById(String itemId, String stateId) {
		Result<Item> result = new Result<Item>();
		try {
			Item item = itemRepo.findById(Integer.parseInt(itemId)).get();
			State state = stateRepo.findById(Integer.parseInt(stateId)).get();
			item.setState(state);
			itemRepo.save(item);
			result.setContent(item);
			result.setMessage("Success!");

		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public String deleteItem(int targetId) {
		try {
			itemRepo.deleteById(targetId);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Success";
	}

	public String updateItem(Item item) {
		try {
			itemRepo.save(item);
			historyService.addItemRecord(item);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Success";
	}

	public Result<Item> updateStateByType(String itemId, String stateType) {
		Result<Item> result = new Result<Item>();
		Item item = itemRepo.findById((Integer.parseInt(itemId))).get();
		try {
			switch (stateType.toUpperCase()) {
			case "AVAILABLE": {
				State state = stateRepo.findById((1)).get();
				item.setState(state);
				itemRepo.save(item);
				result.setContent(item);
				result.setMessage("Success");
				return result;
			}
			case "ON SERVICE": {
				State state = stateRepo.findById((2)).get();
				item.setState(state);
				itemRepo.save(item);
				result.setContent(item);
				result.setMessage("Success");
				return result;
			}
			case "BROKEN": {
				State state = stateRepo.findById((3)).get();
				item.setState(state);
				itemRepo.save(item);
				result.setContent(item);
				result.setMessage("Success");
				return result;
			}
			case "ASSIGNED": {
				State state = stateRepo.findById((4)).get();
				item.setState(state);
				itemRepo.save(item);
				result.setContent(item);
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

	public Result<List<Item>> findByCriteria(Item item) {

		Result<List<Item>> result = new Result<List<Item>>();

		try {
			result.setContent(itemRepo.findAll(new Specification<Item>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
					List<Predicate> predicates = new ArrayList<>();
					if (item.getProducer() != null) {
						predicates.add(criteriaBuilder
								.and(criteriaBuilder.like(root.get("producer"), "%" + item.getProducer() + "%")));
					}
					if (item.getModel() != null) {
						if (!item.getModel().equals("")) {

							predicates.add(criteriaBuilder
									.and(criteriaBuilder.like(root.get("model"), "%" + item.getModel() + "%")));
						}
					}

					if (item.getInventoryNumber() != 0) {
						predicates.add(
								criteriaBuilder.and(criteriaBuilder.like(root.get("inventoryNumber").as(String.class),
										"%" + Long.toString(item.getInventoryNumber()) + "%")));
					}
					if (item.getItemType() != null) {
						predicates.add(
								criteriaBuilder.and(criteriaBuilder.equal(root.get("itemType"), item.getItemType())));
					}
					if (item.getState() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("state"), item.getState())));
					}
					if (item.getUser() != null) {

						predicates.add(criteriaBuilder
								.and(criteriaBuilder.like(root.get("user").get("companyId").as(String.class),
										"%" + item.getUser().getCompanyId() + "%")));

					}
					if (item.getSerialId() != null) {
						if (!item.getSerialId().equals("")) {
							predicates.add(criteriaBuilder
									.and(criteriaBuilder.like(root.get("serialId"), "%" + item.getSerialId() + "%")));

						}
					}
					if (item.getRoom() != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("room"), item.getRoom())));
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

	public int countItemsByStatus(ItemStatus status) {
		return (int) ((Collection<Item>) itemRepo.findAll()).stream().filter(i -> i.getItemStatus().equals(status))
				.count();
	}

	public Result<Item> updateStateById(int itemId, int stateId) {
		Result<Item> result = new Result<Item>();

		try {
			Item item = itemRepo.findById(itemId).get();
			State state = stateRepo.findById((stateId)).get();
			item.setState(state);
			itemRepo.save(item);
			result.setContent(item);
			result.setMessage("Success");

		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<Integer>> getDatasetByState() {
		Result<List<Integer>> result = new Result<List<Integer>>();
		try {
			ArrayList<Integer> dataset = new ArrayList<Integer>() {
				{
					add(countItemsByStatus(ItemStatus.AVAILABLE));
					add(countItemsByStatus(ItemStatus.ON_SERVICE));
					add(countItemsByStatus(ItemStatus.BROKEN));
					add(countItemsByStatus(ItemStatus.ASSIGNED));
				}
			};
			result.setContent(dataset);
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<Item>> getBuyoutInfo() {
		Result<List<Item>> result = new Result<List<Item>>();
		List<Item> items = new ArrayList<Item>();
		try {
			items = matchBuyout();
			result.setContent(items);
			result.setMessage("Success");

		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;

	}

	public Item getItemFromBuyout(String producer, String model, String firstName, String lastName) {
		List<Item> items = ((Collection<Item>) itemRepo.findAll()).stream()
                .filter(item->item.getItemType().getType().equals("Laptop"))
                .collect(Collectors.toList());
		Item toFind = items.stream()
				.filter(item -> item.getProducer().equals(producer))
				.filter(item -> item.getModel().equals(model))
				.filter(item -> item.getUser().getFirstName().equals(firstName))
				.filter(item -> item.getUser().getLastName().equals(lastName)).findFirst().get();

		return toFind;

	}

	public List<Item> matchBuyout() {
		List<BuyoutDto> buyoutList = itemRepo.getBuyoutInfo();
		List<Item> items = new ArrayList<Item>();
		buyoutList.stream().forEach(value -> items.add(modelMapper.map(
				getItemFromBuyout(value.getProducer(), value.getModel(), value.getFirstName(), value.getLastName()),
				Item.class)));

		return items;
	}

	public Result<List<Item>> getRecentAssets() {
		Result<List<Item>> result = new Result<List<Item>>();
		try {
			result.setContent(itemRepo.getRecentAssets().stream().collect(Collectors.toList()));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}

		return result;
	}
	
	public Result<List<Item>> getItemByInventoryNr(String number){
		Result<List<Item>> result = new Result<List<Item>>();
		try {
			result.setContent(((Collection<Item>) itemRepo.findAll())
					.stream()
                    .filter(item -> Long.toString(item.getInventoryNumber()).equals(number))
                    .collect(Collectors.toList()));
                    result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
