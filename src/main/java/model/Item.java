package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String comment;

	@Column(name="inventory_number")
	private long inventoryNumber;

	private String model;

	private String producer;

	@Column(name="serial_id")
	private String serialId;

	//bi-directional many-to-one association to History_Item
	@OneToMany(mappedBy="item")
	private List<History_Item> historyItems;

	//bi-directional many-to-one association to Item_Type
	@ManyToOne
	@JoinColumn(name="type_id")
	private Item_Type itemType;

	//bi-directional many-to-one association to Room
	@ManyToOne
	private Room room;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name="status_id")
	private State state;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="owner_id")
	private User user;

	public Item() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getInventoryNumber() {
		return this.inventoryNumber;
	}

	public void setInventoryNumber(long inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProducer() {
		return this.producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getSerialId() {
		return this.serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public List<History_Item> getHistoryItems() {
		return this.historyItems;
	}

	public void setHistoryItems(List<History_Item> historyItems) {
		this.historyItems = historyItems;
	}

	public History_Item addHistoryItem(History_Item historyItem) {
		getHistoryItems().add(historyItem);
		historyItem.setItem(this);

		return historyItem;
	}

	public History_Item removeHistoryItem(History_Item historyItem) {
		getHistoryItems().remove(historyItem);
		historyItem.setItem(null);

		return historyItem;
	}

	public Item_Type getItemType() {
		return this.itemType;
	}

	public void setItemType(Item_Type itemType) {
		this.itemType = itemType;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}