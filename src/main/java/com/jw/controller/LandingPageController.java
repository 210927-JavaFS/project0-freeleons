package com.jw.controller;

import com.jw.model.Product;
import com.jw.service.ProductService;
import com.jw.service.UserService;

public class LandingPageController {

	private ProductService productService = new ProductService();
	private UserService userService = new UserService();
	private int currentUserId;
	public LandingPageController() {
		
		this.showLoggedInMenu();
	}
	
	public LandingPageController(String accountType, int user_id) {
	
		this.currentUserId = user_id;
		if(accountType.equals("Customer"))
			this.showLoggedInMenu();
		else if(accountType.equals("Manager"))
			this.showManagerLoggedInMenu();
		else if(accountType.equals("Admin"))
			this.showAdminLoggedInMenu();
	}
	
	private void showLoggedInMenu()
	{
		System.out.println("Welcome to Customer Page!");
		System.out.println("1. Order Product");
		System.out.println("2. Check Orders");
		System.out.println("3. Logout");
		
		String input = waitingForInput();

		String menuSelected = "Error";
		System.out.println(input);
		switch (input) {
		case "1":
			menuSelected = "1";
			break;
		case "2":
			menuSelected = "2";
			break;
		case "3":
			menuSelected = "3";
			break;
		default:
			menuSelected = "Error";
			break;
		}
		
		if (menuSelected.equals("1")) {
			showOrderProductPage();
		} else if (menuSelected.equals("2")) {
			showCheckOrdersPage();
		}  else if (menuSelected.equals("3")) {
			return;
		}else {
			System.out.println("Input Error, Please input 1, 2, or 3");
			showLoggedInMenu();
		}
	}
	
	private void showManagerLoggedInMenu()
	{
		System.out.println("Welcome to Manager Page!");
		System.out.println("1. Product Menu");
		System.out.println("2. Alter Quantity of Inventory");
		System.out.println("3. Check Orders");
		System.out.println("4. Log out");
		
		String input = waitingForInput();

		String menuSelected = "Error";
		System.out.println(input);
		switch (input) {
		case "1":
			menuSelected = "1";
			break;
		case "2":
			menuSelected = "2";
			break;
		case "3":
			menuSelected = "3";
			break;
		case "4":
			menuSelected = "4";
			break;
		default:
			menuSelected = "Error";
			break;
		}
		
		if (menuSelected.equals("1")) {
			showProductMenuPage();
			showManagerLoggedInMenu();
		} else if (menuSelected.equals("2")) {
			showAlterQuantityPage();
			showManagerLoggedInMenu();
		} else if (menuSelected.equals("3")) {
			showCheckAllOrdersPage();
			showManagerLoggedInMenu();
		} else if (menuSelected.equals("4")) {
			return;
		}else {
			System.out.println("Input Error, Please input 1, 2, or 3");
			showManagerLoggedInMenu();
		}
	}
	
	private void showAdminLoggedInMenu()
	{
		System.out.println("Welcome to Admin Page!");
		System.out.println("1. Product Menu");
		System.out.println("2. Alter Quantity of Inventory");
		System.out.println("3. Check Orders");
		System.out.println("4. List Users");
		System.out.println("5. Log out");
		
		String input = waitingForInput();

		String menuSelected = "Error";
		System.out.println(input);
		switch (input) {
		case "1":
			menuSelected = "1";
			break;
		case "2":
			menuSelected = "2";
			break;
		case "3":
			menuSelected = "3";
			break;
		case "4":
			menuSelected = "4";
			break;
		case "5":
			menuSelected = "5";
			break;
		default:
			menuSelected = "Error";
			break;
		}
		
		if (menuSelected.equals("1")) {
			showProductMenuPage();
			showAdminLoggedInMenu();
		} else if (menuSelected.equals("2")) {
			showAlterQuantityPage();
			showAdminLoggedInMenu();
		} else if (menuSelected.equals("3")) {
			showCheckAllOrdersPage();
			showAdminLoggedInMenu();
		} 
		else if (menuSelected.equals("4")) {
			showListUserPage();
		}
		else if (menuSelected.equals("5")) {
			return;
		}else {
			System.out.println("Input Error, Please input 1, 2, or 3");
			showAdminLoggedInMenu();
		}
		
	}
	
	private String waitingForInput() {
		String input = MenuController.scanner.nextLine();
		return input;
	}
	
	
	
	//showAddNewProductPage()
	private void showProductMenuPage()
	{
		System.out.println("Product Menu Page:");
		System.out.println("1. List Products");
		System.out.println("2. Add New Product");
		System.out.println("3. Back");
		
		String input = waitingForInput();

		String menuSelected = "Error";
		System.out.println(input);
		switch (input) {
		case "1":
			menuSelected = "1";
			break;
		case "2":
			menuSelected = "2";
			break;
		case "3":
			menuSelected = "3";
			break;
		default:
			menuSelected = "Error";
			break;
		}
		
		if (menuSelected.equals("1")) {
			showListProductPage();
		} else if (menuSelected.equals("2")) {
			showAddNewProductPage();
		} else if (menuSelected.equals("3")) {
			return;
		} else {
			System.out.println("Input Error, Please input 1, 2, or 3");
			showAdminLoggedInMenu();
		}
	}
	
	private void showAlterQuantityPage()
	{
		System.out.println("List Inventory:");
		productService.printTable("Product");
		System.out.println("Please enter Product ID:");
		String productId = waitingForInput();
		System.out.println("Please enter quantity:");
		String quantity = waitingForInput();
		boolean success = productService.updateQuantity(Integer.parseInt(productId), Integer.parseInt(quantity));
		if(success)
		{
			System.out.println("Updated Quantiity Successfully!");
		}
		else
		{
			System.out.println("Updated Quantiity Failed!");
		}
	}
	
	private void showCheckAllOrdersPage()
	{
		System.out.println("List All Orders:");
		productService.listAllOrders();
	}
	
	private void showListUserPage()
	{
		System.out.println("Now Listing Users:");
		userService.printUserTable();
		showAdminLoggedInMenu();
		
	}
	
	private void showOrderProductPage()
	{
		System.out.println("Please Order Product, Here are the available products:");
		productService.listProducts();
		System.out.println("Please enter one Product ID which you want to order:");
		String productId = waitingForInput();
		System.out.println("Please Enter the quantity you want to order for this item:");
		String quantity = waitingForInput();
		
		int inventoryQuantity = productService.getQuantity(Integer.parseInt(productId));
		if((inventoryQuantity - Integer.parseInt(productId)) >=0)
		{
			boolean success = productService.updateProductQuantity(Integer.parseInt(quantity), inventoryQuantity, Integer.parseInt(productId), currentUserId);
			if (success)
				System.out.println("Order Successfully!!");
			else
				System.out.println("Order Failed!");
		}
		else
		{
			System.out.println("Inventory quantity can not filfull this order.");
			showOrderProductPage();
		}
		showLoggedInMenu();
	}
	
	private void showCheckOrdersPage()
	{
		System.out.println("List Orders:");
		productService.listOrders(currentUserId);
		showLoggedInMenu();
	}
	
	private void showListProductPage()
	{
		System.out.println("Now Listing Products:");
		productService.printTable("product");
		showProductMenuPage();
	}
	
	private void showAddNewProductPage()
	{
		System.out.println("Please enter product name:");
		String productName = waitingForInput();
		System.out.println("Please enter product dimension:");
		String dimension = waitingForInput();
		System.out.println("Please enter product weight:");
		String weight = waitingForInput();
		System.out.println("Please enter inventory quantity:");
		String quantity = waitingForInput();
		System.out.println("Please enter Unit price:");
		String price = waitingForInput();
		
		Product product = new Product();
		product.setProductName(productName);
		product.setDimension(dimension);
		product.setWeight(weight);
		product.setQuantity(Integer.parseInt(quantity));
		product.setPrice(Double.parseDouble(price));
		
		boolean isSuccessful = productService.addNewProduct(product);
		if(isSuccessful)
		{
			System.out.println("Added Product Successfully!");
		}
		else
		{
			System.out.println("Adding new Product failed!");
		}
		
		showProductMenuPage();
		
		
	}

}
