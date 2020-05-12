package com.inventory.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the State database table.
 * 
 */
@Entity
@Table(name = "State")
@NamedQuery(name = "State.findAll", query = "SELECT s FROM State s")
@NamedNativeQueries({
		@NamedNativeQuery(name = "getIspItemStates", query = "EXEC [ispGetInventoryItemStates]", resultClass = State.class) })

public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String type;

	// bi-directional many-to-one association to Item
	@OneToMany(mappedBy = "state")
	@JsonIgnore
	private List<Item> items;

	// bi-directional many-to-one association to HistoryItem
	@OneToMany(mappedBy = "state")
	@JsonIgnore
	private List<HistoryItem> historyItems;

	public State() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setState(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setState(null);

		return item;
	}

	public List<HistoryItem> getHistoryItems() {
		return this.historyItems;
	}

	public void setHistoryItems(List<HistoryItem> historyItems) {
		this.historyItems = historyItems;
	}

	public HistoryItem addHistoryItem(HistoryItem historyItem) {
		getHistoryItems().add(historyItem);
		historyItem.setState(this);

		return historyItem;
	}

	public HistoryItem removeHistoryItem(HistoryItem historyItem) {
		getHistoryItems().remove(historyItem);
		historyItem.setState(null);

		return historyItem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((historyItems == null) ? 0 : historyItems.hashCode());
		result = prime * result + id;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (historyItems == null) {
			if (other.historyItems != null)
				return false;
		} else if (!historyItems.equals(other.historyItems))
			return false;
		if (id != other.id)
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}