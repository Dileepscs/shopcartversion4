package store.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import store.modal.Customer;

public class UserDAL implements User {

	@Override
	public boolean isValidUser(String name, String password) {
		// TODO Auto-generated method stub
		Connection con = Db.connect();

		try {
			PreparedStatement ps = con.prepareStatement("select password from customer_1 where username = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (password.compareTo(rs.getString(1)) == 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean createUser(Customer c) {
		// TODO Auto-generated method stub
		Connection con = Db.connect();
		try {
			// add new user to the table
			String query = "INSERT INTO customer_1(cust_name, cust_mobile,username,password) VALUES ( ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, c.getCname());
			ps.setString(2, c.getCmobile());
			ps.setString(3, c.getCemail());
			ps.setString(4, c.getCpassword());
			// return true if insertion is done completely
			int rows_affected = ps.executeUpdate();
			return rows_affected > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Customer getCustomerDetails(String username) {
		// TODO Auto-generated method stub
		Customer c = new Customer();
		Connection con = Db.connect();

		try {
			PreparedStatement ps = con.prepareStatement("select * from customer_1 where username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c.setCid(rs.getInt(1));
				c.setCname(rs.getString(2));
				c.setCmobile(rs.getString(3));
				c.setCemail(rs.getString(4));
				c.setCpassword(rs.getString(5));
			}
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
