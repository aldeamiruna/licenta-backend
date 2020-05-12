package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the State database table.
 * 
 */
@Entity
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String type;

	//bi-directional many-to-one association to History_Item
	@OneToMany(mappedBy="state")
	private List<History_Item> historyItems;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="state")
	private List<Item> items;

	public State() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<History_Item> getHistoryItems() {
		return this.historyItems;
	}

	public void setHistoryItems(List<History_Item> historyItems) {
		this.historyItems = historyItems;
	}

	public History_Item addHistoryItem(History_Item historyItem) {
		getHistoryItems().add(historyItem);
		historyItem.setState(this);

		return historyItem;
	}

	public History_Item removeHistoryItem(History_Item historyItem) {
		getHistoryItems().remove(historyItem);
		historyItem.setState(null);

		return historyItem;
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

}