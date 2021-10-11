package com.jw.controller;

public class LandingPageController {

	public LandingPageController() {
		// TODO Auto-generated constructor stub
		this.showLoggedInMenu();
	}
	
	public LandingPageController(String accountType) {
		// TODO Auto-generated constructor stub
		if(accountType.equals("Customer"))
			this.showLoggedInMenu();
		else if(accountType.equals("Admin"))
			this.showAdminLoggedInMenu();
	}
	
	private void showLoggedInMenu()
	{
		System.out.println("Welcome to Customer Landing Page!");
		System.out.println("1. Order Product");
		System.out.println("2. Check Orders");
		System.out.println("3. Logout");
	}
	
	private void showAdminLoggedInMenu()
	{
		System.out.println("Welcome to Admin Landing Page!");
		System.out.println("1. Add New Product");
		System.out.println("2. Alter Quantity of Inventory");
		System.out.println("3. Check Orders");
		System.out.println("4. Log out");
		
	}

}
