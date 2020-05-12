
package com.inventory.utils;

public enum UserState implements Status{

	EMPLOYEE("Employee"),
	INTERN("Intern"),
	LEFT_THE_COMPANY("Left the company"),
	MATERNITY_LEAVE("Maternity leave"),
	OFFICE("Office");
	

	private final String type;
	
	UserState(String type){
		this.type = type;
	}
	
	public String getTypeName() {
		return this.type;
	}
}
