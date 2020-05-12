package com.inventory.dto;

import javax.persistence.Column;

public class BuyoutDto {
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "model")
	private String model;
	@Column(name = "producer")
	private String producer;

	public BuyoutDto(String firstName, String lastName, String model, String producer) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.model = model;
		this.producer = producer;
	}

	public BuyoutDto() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "BuyoutDto [firstName=" + firstName + ", lastName=" + lastName + ", model=" + model + ", producer="
				+ producer + "]";
	}

}
