package com.jw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jw.jdbc.ConnectionUtil;
import com.jw.model.Product;

import net.efabrika.util.DBTablePrinter;

public class ProductDAOImplement implements ProductDAO {
	public static Logger log = LoggerFactory.getLogger(UsersDAOImplement.class);

	public ProductDAOImplement() {

	}

	@Override
	public boolean addNewProduct(Product newProduct) {
		try (Connection conn = ConnectionUtil.getConnection()) { // try-with-resources
			String sql = "INSERT INTO Product (product_name, dimension, weight, quantity, price) VALUES (?, ?, ?, ?, ?);";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, newProduct.getProductName());
			statement.setString(2, newProduct.getDimension());
			statement.setString(3, newProduct.getWeight());
			statement.setInt(4, newProduct.getQuantity());
			statement.setDouble(5, newProduct.getPrice());
			statement.execute();
			int result = statement.getUpdateCount();
			statement.close();
			conn.close();
			log.debug("getUpdateCount: " + result);
			return result > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void printTable(String tableName) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()) { // try-with-resources
//			DBTablePrinter.printTable(conn, tableName);
			String sql = "SELECT * FROM Product";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			DBTablePrinter.printResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void listProducts() {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()) { // try-with-resources

			String sql = "SELECT product_id, product_name, dimension, weight, price FROM Product where quantity > 0";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			DBTablePrinter.printResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getQuantity(int productId) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()) { // try-with-resources
			String sql = "SELECT quantity FROM Product where product_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, productId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt("quantity");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public boolean updateProductQuantity(int orderQuantity, int inventoryQuantity, int productID, int userId) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()) { // try-with-resources
//			DBTablePrinter.printTable(conn, tableName);
			String sql = "UPDATE product SET quantity = ? where product_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, inventoryQuantity - orderQuantity);
			statement.setInt(2, productID);
			statement.execute();
			int result = statement.getUpdateCount();
			log.debug("getUpdateCount: " + result);

			String insertSql = "INSERT INTO order_table (user_id, product_id, shipping_address, status, quantity) VALUES (?, ?, 'DEFAULT', 'Ordered', ?);";
			PreparedStatement insertStatement = conn.prepareStatement(insertSql);
			insertStatement.setInt(1, userId);
			insertStatement.setInt(2, productID);
			insertStatement.setInt(3, orderQuantity);
			insertStatement.execute();
			int insertResult = statement.getUpdateCount();
			insertStatement.close();
			conn.close();
			log.debug("getUpdateCount: " + result);
			return (result > 0) && (insertResult > 0);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void listOrders(int userID) {
		try (Connection conn = ConnectionUtil.getConnection()) { // try-with-resources
//			DBTablePrinter.printTable(conn, tableName);
			String sql = "select p.product_name, o.status, o.quantity from product p inner join order_table o on p.product_id = o.product_id where o.user_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userID);
			ResultSet rs = statement.executeQuery();
			DBTablePrinter.printResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean updateQuantity(int productID, int quantity)
	{
		
		try (Connection conn = ConnectionUtil.getConnection()) { // try-with-resources
//			DBTablePrinter.printTable(conn, tableName);
			String sql = "UPDATE product SET quantity = ? where product_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, quantity);
			statement.setInt(2, productID);
			statement.execute();
			int result = statement.getUpdateCount();
			conn.close();
			log.debug("getUpdateCount: " + result);
			return (result > 0);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void listAllOrders()
	{
		try (Connection conn = ConnectionUtil.getConnection()) { // try-with-resources
//			DBTablePrinter.printTable(conn, tableName);
			String sql = "select o.user_id, p.product_name, o.status, o.quantity from product p inner join order_table o on p.product_id = o.product_id;";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			DBTablePrinter.printResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
