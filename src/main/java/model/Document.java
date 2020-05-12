package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Document database table.
 * 
 */
@Entity
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private Object name;

	private Object template;

	public Document() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Object getName() {
		return this.name;
	}

	public void setName(Object name) {
		this.name = name;
	}

	public Object getTemplate() {
		return this.template;
	}

	public void setTemplate(Object template) {
		this.template = template;
	}

}