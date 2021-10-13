package com.jw.service;

import com.jw.dao.ProductDAO;
import com.jw.dao.ProductDAOImplement;
import com.jw.model.Product;

public class ProductService {

	private ProductDAO productDAO = new ProductDAOImplement();
	public ProductService() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean addNewProduct(Product newProduct)
	{
		return productDAO.addNewProduct(newProduct);
	}
	
	public void printTable(String tableName)
	{
		productDAO.printTable(tableName);
	}
	
	public void listProducts()
	{
		productDAO.listProducts();
	}
	
	public int getQuantity(int productId)
	{
		return productDAO.getQuantity(productId);
	}
	
	public boolean updateProductQuantity(int orderQuantity, int inventoryQuantity, int productID, int userId)
	{
		return productDAO.updateProductQuantity(orderQuantity, inventoryQuantity, productID, userId);
	}
	
	public void listOrders(int userID)
	{
		productDAO.listOrders(userID);
	}
	
	public void listAllOrders()
	{
		productDAO.listAllOrders();
	}
	
	public boolean updateQuantity(int productID, int quantity)
	{
		return productDAO.updateQuantity(productID, quantity);
	}

}
