package com.jw.model;

public class Product {

	
	private String productName;
	private String dimension;
	private String weight;
	private int quantity;
	private double price;
	public Product()
	{
		super();
	}
	public Product(String productName, String dimension, String weight, int quantity, double price) {
		// TODO Auto-generated constructor stub
		 this.productName =productName;
		 this.dimension = dimension;
		 this.weight = weight; 
		 this.quantity = quantity;
		 this.price = price;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
