package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the History_Item database table.
 * 
 */
@Entity
@NamedQuery(name="History_Item.findAll", query="SELECT h FROM History_Item h")
public class History_Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String comment;

	private Object date;

	//bi-directional many-to-one association to Item
	@ManyToOne
	private Item item;

	//bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name="status_item")
	private State state;

	public History_Item() {
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

	public Object getDate() {
		return this.date;
	}

	public void setDate(Object date) {
		this.date = date;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

}