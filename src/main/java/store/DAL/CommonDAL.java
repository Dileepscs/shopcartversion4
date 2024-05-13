package store.DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import store.DAO.DataBaseBridge;
import store.modal.BillDetails;
import store.modal.CartItems;
import store.modal.CheckOutModal;
import store.modal.CouponDetails;
import store.modal.Customer;
import store.modal.Product;
import store.modal.ProductList;

public class CommonDAL implements Common {
	Connection con;

	public CommonDAL() {
		con = Db.connect();
	}

	public double getBaseShippingAmount(double amt) {

		double ship = 50;
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT orvl_shippingamount FROM ordervaluewiseshippingcharges_1 WHERE orvl_id = (SELECT orvl_id FROM ordervaluewiseshippingcharges_1 WHERE orvl_from <= ? ORDER BY orvl_from DESC LIMIT 1 )");
			ps.setDouble(1, amt);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ship = Double.parseDouble(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ship;
	}

	@Override
	public CouponDetails getCouponDetails(String code) {
		// TODO Auto-generated method stub
		CouponDetails coupon = new CouponDetails();
		coupon.setCode(code);
		coupon.setDiscount(0);
		try {
			PreparedStatement ps = con.prepareStatement("select cpn_count,coupon_discount_percentage from coupons_1 where cpn_code=?");
			ps.setString(1,code);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				if(rs.getInt("cpn_count")>0) {
					coupon.setValid(true);
					coupon.setDiscount(rs.getFloat("coupon_discount_percentage"));
				}else {
					coupon.setValid(false);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			coupon.setValid(false);
			e.printStackTrace();
		}
		
		return coupon;
	}

	@Override
	public String createOrder(String d, double totalAmt, int cid, ProductList products) {
		// TODO Auto-generated method stub
		String orderId = null;
		String query="INSERT INTO orders_1 (order_date, order_total, cust_id) VALUES (?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, d);
			ps.setDouble(2, totalAmt);
			ps.setInt(3, cid);
			ps.executeUpdate();
			
			    // Retrieve the auto-increment key generated by the database
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
			    orderId = rs.getInt(1)+"";
			    System.out.println("Order Generated ID: " + orderId);
			    Products dal = new DataBaseBridge().getProductDAL();
			    boolean statusOfProductsUpdate = dal.updateStock(products,orderId);
			    if(statusOfProductsUpdate) {
			    	return orderId;
			    }else {
			    	return null;
			    }
			} 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderId;
	}
}