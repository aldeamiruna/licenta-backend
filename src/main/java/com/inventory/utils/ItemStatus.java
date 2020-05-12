package com.inventory.utils;

public enum ItemStatus implements Status{
	AVAILABLE("Available"),
	ON_SERVICE("On service"),
	BROKEN("Broken"),
	ASSIGNED("Assigned"),
	RETIRED("Retired");
	
	private final String type;
	
	ItemStatus(String type){
		this.type = type;
	}
	
	public String getTypeName() {
		return this.type;
	}
}
