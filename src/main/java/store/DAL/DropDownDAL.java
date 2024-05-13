package store.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import store.modal.Categoery;
import store.modal.CategoeryList;

public class DropDownDAL implements DropDown {
	Connection con;
	CategoeryList cl = null;

	public DropDownDAL() {
		con = Db.connect();
		cl = new CategoeryList();
	}

	public CategoeryList getCategories() {
		try {
			PreparedStatement ps = con.prepareStatement("select * from categories");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categoery c = new Categoery();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				cl.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cl;
	}
}
