package com.inventory.repository;

import java.util.List;

import com.inventory.model.State;

public interface StateRepositoryCustom {

	public List<State> getExistingItemStates();
}
