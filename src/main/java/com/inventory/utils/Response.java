package com.inventory.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Response {
	private ArrayList output = new ArrayList();
	private String message;

	public Response() {
		message = "";
	}

	public Response(Result<?> result) {
		fillWithResultData(result);
	}

	public ArrayList getOutput() {
		return output;
	}

	public void setOutput(ArrayList output) {
		this.output = output;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void fillWithResultData(Result<?> result) {
		setMessage(result.getMessage());
		if (!message.equals("Success")) {
			return;
		}
		try {
			if(result.getContent() instanceof ArrayList) {
				this.output = (ArrayList)result.getContent();
				
			}
			else this.output.add(result.getContent());

		} catch (Exception e) {
			setMessage(e.getMessage());
		}
	}
}
