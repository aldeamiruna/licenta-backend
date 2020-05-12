package com.inventory.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.inventory.service.DocumentService;

public class PDFField {
	private String text;
	private List<String> params = new ArrayList<String>();

	public PDFField() {
		this.text = null;
	}

	public PDFField(String text) {
		this.text = text;
		defineParams();
	}

	public PDFField(String text, List<String> params) {
		this(text);
		this.params = params;
	}

	public PDFField(String text, String param) {
		this(text);
		this.params.add(param);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public void addParam(String param) {
		this.params.add(param);
	}

	public void defineParams() {
		int count = StringUtils.countOccurrencesOf(text, "<");
		for (int counter = 0; counter < count; counter++) {
			params.add("");
		}
	}

	public String generate() {
		String result = text;
		for (int counter = 0; counter < params.size(); counter++) {
			String identifier = result.substring(result.indexOf("<") + 1, result.indexOf(">"));
			if(identifier == null) {
				return null;
			}
			if (DocumentService.itemFields.containsKey(identifier) && 
					DocumentService.itemFields.get(identifier) != null) {
				text = result.replace(new String("<" + identifier + ">"), DocumentService.itemFields.get(identifier));
				result = text;
				params.remove(counter);
			} else {
				result = result.replace(new String("<" + identifier + ">"), params.get(counter));
				
			}
		}
		text = result;
		return result;
	}

	public String generateNonEmpty() {
		String result = text;
		for (int counter = 0; counter < params.size(); counter++) {
			String identifier = result.substring(result.indexOf("<") + 1, result.indexOf(">"));
			if (DocumentService.itemFields.containsKey(identifier) && 
					DocumentService.itemFields.get(identifier) != null) {
				text = result.replace(new String("<" + identifier + ">"), DocumentService.itemFields.get(identifier));
				result = text;
				params.remove(counter);
			} else {
				if (!params.get(counter).equals("")) {
					result = result.replace(new String("<" + identifier + ">"), params.get(counter));
				}
			}
		}
		text = result;
		return result;
	}
}
