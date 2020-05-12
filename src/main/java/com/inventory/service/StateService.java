package com.inventory.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.model.State;
import com.inventory.repository.StateRepository;
import com.inventory.utils.Result;

@Service

public class StateService {
	@Autowired
	StateRepository repo;

	public Result<List<State>> getItemStates() {
		Result<List<State>> result = new Result<List<State>>();
		try {
			List<State> states = repo.getExistingItemStates();
			result.setContent(states);
			result.setMessage("Success");
			return result;
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<State>> getAll() {// this is without stored procedure
		Result<List<State>> result = new Result<List<State>>();
		try {
			List<State> states = new ArrayList<State>();

			repo.findAll().forEach(states::add);

			result.setContent(states);
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}

		return result;
	}

	public Result<List<String>> getDistinctStates() {
		Result<List<String>> result = new Result<List<String>>();
		try {
			List<State> states = new ArrayList<State>();
			repo.findAll().forEach(states::add);
			Set<String> itemStates = new LinkedHashSet<String>();
			states.forEach(state -> itemStates.add(state.getType()));
			result.setContent(new ArrayList<>(itemStates));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<State>> getDistinctObjectStates() {
        Result<List<State>> result = new Result<List<State>>();
        try {
            List<State> states = new ArrayList<State>();
            repo.findAll().forEach(states::add);
            Set<State> itemStates = new LinkedHashSet<State>();
            states.forEach(state -> itemStates.add(state));
            result.setContent(new ArrayList<>(itemStates));
            result.setMessage("Success");
        } catch (Exception e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
