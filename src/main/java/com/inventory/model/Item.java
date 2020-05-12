package com.inventory.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventory.dto.BuyoutDto;
import com.inventory.utils.ItemStatus;

/**
 * The persistent class for the Item database table.
 * 
 */
@Entity
@Table(name = "Item")

@SqlResultSetMapping(name = "ItemUserMapping", classes = @ConstructorResult(targetClass = BuyoutDto.class, columns = {
		@ColumnResult(name = "first_name", type = String.class), @ColumnResult(name = "last_name", type = String.class),
		@ColumnResult(name = "model", type = String.class), @ColumnResult(name = "producer", type = String.class) }))

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	private String comment;

	@Column(name = "inventory_number")
	private long inventoryNumber;

	@Column(name = "model")
	private String model;

	@Column(name = "producer")
	private String producer;

	@Column(name = "serial_id")
	private String serialId;

	// bi-directional many-to-one association to Room
	@ManyToOne
	private Room room;

	// bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name = "status_id")
	private State state;

	@Transient
	private ItemStatus itemStatus;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User user;

	// bi-directional many-to-one association to HistoryItem
	@OneToMany(mappedBy = "item")
	@JsonIgnore
	private List<HistoryItem> historyItems;

	// bi-directional many-to-one association to ItemType
	@ManyToOne
	@JoinColumn(name = "type_id")
	private ItemType itemType;

	public Item() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public List<HistoryItem> getHistoryItems() {
		return this.historyItems;
	}

	public void setHistoryItems(List<HistoryItem> historyItems) {
		this.historyItems = historyItems;
	}

	public HistoryItem addHistoryItem(HistoryItem historyItem) {
		getHistoryItems().add(historyItem);
		historyItem.setItem(this);

		return historyItem;
	}

	public HistoryItem removeHistoryItem(HistoryItem historyItem) {
		getHistoryItems().remove(historyItem);
		historyItem.setItem(null);

		return historyItem;
	}

	public ItemType getItemType() {
		return this.itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public ItemStatus getItemStatus() {
		return this.itemStatus;
	}

	@PostLoad
	public void initIt() throws Exception {
		this.updateStatus();
	}

	public void updateStatus() {
		this.itemStatus = ItemStatus.values()[state.getId() - 1];
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", comment=" + comment + ", inventoryNumber=" + inventoryNumber + ", model=" + model
				+ ", producer=" + producer + ", serialId=" + serialId + ", room=" + room + ", state=" + state
				+ ", itemStatus=" + itemStatus + ", user=" + user + ", historyItems=" + historyItems + ", itemType="
				+ itemType + "]";
	}
}