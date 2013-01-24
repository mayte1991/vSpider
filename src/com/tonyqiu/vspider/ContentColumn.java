package com.tonyqiu.vspider;

public class ContentColumn {
	String name;
	String cssSelector;
	ColumnType type;
	
	String value;
	boolean needImageExtracted;
	String imageSelector;
	
	public ContentColumn(String name, String cssSelector) {
		super();
		this.name = name;
		this.cssSelector = cssSelector;
		this.type = ColumnType.TEXT;
	}

	public ContentColumn(String name, String cssSelector, ColumnType type) {
		super();
		this.name = name;
		this.cssSelector = cssSelector;
		this.type = type;
	}
	public ContentColumn(String name, String cssSelector, ColumnType type, boolean needImageExtracted, String imageSelector) {
		super();
		this.name = name;
		this.cssSelector = cssSelector;
		this.type = type;
		this.needImageExtracted = needImageExtracted;
		this.imageSelector = imageSelector;
	}

	@Override
	public String toString() {
		return "ContentColumn [name=" + name + ", cssSelector=" + cssSelector
				+ ", type=" + type + ", value=" + value + "]";
	}
	
	
	
}
