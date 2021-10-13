package com.jw.dao;

import com.jw.model.Product;

public interface ProductDAO {
	public boolean addNewProduct(Product newProduct);
	
	public void printTable(String tableName);

	public void listProducts();
	
	public int getQuantity(int productId);
	
	public boolean updateProductQuantity(int orderQuantity, int inventoryQuantity, int productID, int userId);
	
	public void listOrders(int userID);
	
	public boolean updateQuantity(int productID, int quantity);
	
	public void listAllOrders();
}
