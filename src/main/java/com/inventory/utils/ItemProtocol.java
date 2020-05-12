package com.inventory.utils;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemProtocol {
	
	private String name;
	private String title;
	private PDFField subtitle;
	private float titleFontSize;
	private float textFontSize;
	private float lineSpacing;
	private List<PDFField> fields = new ArrayList<PDFField>();

	public ItemProtocol() {
		this.title = null;
		this.subtitle = null;
	}
	
	public void copy(ItemProtocol source) {
		this.name = source.name;
		this.subtitle = source.subtitle;
		this.title = source.title;
		this.titleFontSize = source.titleFontSize;
		this.textFontSize = source.textFontSize;
		this.lineSpacing = source.lineSpacing;
		this.fields = source.fields;
	}
	
	public String getName() {
		return name;
	}

	public float getLineSpacing() {
		return lineSpacing;
	}

	public void setLineSpacing(float lineSpacing) {
		this.lineSpacing = lineSpacing;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public float getTitleFontSize() {
		return titleFontSize;
	}

	public void setTitleFontSize(float titleFontSize) {
		this.titleFontSize = titleFontSize;
	}

	public float getTextFontSize() {
		return textFontSize;
	}

	public void setTextFontSize(float textFontSize) {
		this.textFontSize = textFontSize;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PDFField getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(PDFField subtitle) {
		this.subtitle = subtitle;
	}

	public List<PDFField> getFields() {
		return fields;
	}

	public void setFields(List<PDFField> fields) {
		this.fields = fields;
	}
	
	public void addField(PDFField field) {
		this.fields.add(field);
	}
	
	public void addField(String text, List<String> params) {
		this.fields.add(new PDFField(text, params));
	}
	
	public void addField(String text) {
		this.fields.add(new PDFField(text));
	}
	
	public void getDataFromFile(InputStream source) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			ItemProtocol temp = mapper.readValue(source, ItemProtocol.class);
			this.copy(temp);
			for(PDFField field : this.fields)
				field.defineParams();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getDataFromString(String source) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			ItemProtocol temp = mapper.readValue(source, ItemProtocol.class);
			this.copy(temp);
			subtitle.defineParams();
			for(PDFField field : this.fields)
				field.defineParams();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
