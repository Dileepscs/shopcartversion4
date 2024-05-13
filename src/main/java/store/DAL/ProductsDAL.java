package store.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import store.modal.CartItems;
import store.modal.Product;
import store.modal.ProductList;

public class ProductsDAL implements Products {

	ProductList pl = null;
	Connection con;

	public ProductsDAL() {
		pl = new ProductList();
		con = Db.connect();
	}

	@Override
	public ProductList getProducts(String cat) {
		String query = "";
		String sort=null;
		PreparedStatement ps = null;
		query = "select p.id, title, c.category, hsnc_gstc_percentage , image,  prod_mrp,price from product_1 p, HSNCodes_1 h, categories c, ProductStocks_1 stock where stock.prod_id=p.id and h.hsnc_id=p.hsnid and c.category_id=p.category_id";
		if ((cat == null && sort == null) || (cat.equals("*") && sort.equals("*"))) {
			try {
//				System.out.println("in 1" + cat + "   -  " + sort);
				ps = con.prepareStatement(query);
				//System.out.println(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (!cat.equals("*") && sort.equals("*")) {
			System.out.println("in 2" + cat + "   -  " + sort);
			query = query + " and c.category_id = ?";
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(cat));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (cat.equals("*") && !sort.equals("*")) {
			query = query + " orderby ?";
			System.out.println("in 1" + cat + "   -  " + sort);
			try {
				ps = con.prepareStatement(query);
				// ps.setInt(1, Integer.parseInt(cat));
				ps.setString(1, sort);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			query = query + " and category_id = ? orderby ? ";
			try {
				System.out.println("in 4" + cat + "   -  " + sort);
				ps = con.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(cat));
				ps.setString(2, sort);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return getProducts(ps);

	}

	@Override
	public ProductList getProducts(PreparedStatement ps) {
		// TODO Auto-generated method stub
		try {
			ResultSet rs = ps.executeQuery();
			//System.out.println("in function getProducts");
			while (rs.next()) {
				//System.out.println("in while loop");
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setTitle(rs.getString("title"));
				p.setCategory(rs.getString("category"));
				p.setGST(Double.parseDouble(rs.getString("hsnc_gstc_percentage")));
				p.setImage(rs.getString("image"));
				p.setPrice(rs.getDouble("price"));
//				System.out.println(rs.getDouble("prod_mrp"));
				p.setMRP(rs.getDouble("prod_mrp"));
				pl.add(p);
				//System.out.println(p.getId());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pl;
	}
//	private Product createProduct(ResultSet rs) throws SQLException {
//		Product p = new Product();
//		p.setId(rs.getInt("id"));
//		p.setTitle(rs.getString("title"));
//		p.setCategory(rs.getString("category"));
//		p.setGST(Double.parseDouble(rs.getString("hsnc_gstc_percentage")));
//		p.setImage(rs.getString("image"));
//		p.setPrice(rs.getDouble("price"));
////		System.out.println(rs.getDouble("prod_mrp"));
//		p.setMRP(rs.getDouble("prod_mrp"));
//		return p;
//	}

	@Override
	public Product getProductById(int id) {
		String query = "select p.id, p.title, c.category, hsnc_gstc_percentage ,image,  prod_mrp,price from product_1 p, HSNCodes_1 h, categories c, ProductStocks_1 stock where stock.prod_id=p.id and h.hsnc_id=p.hsnid and c.category_id=p.category_id and p.id = ?";
		PreparedStatement pst;
		Product p = null;
		try {
			pst = con.prepareStatement(query);
//			System.out.println("connect");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
//				p = createProduct(rs);
				p = new Product();
				p.setId(rs.getInt("id"));
				System.out.println();
				p.setTitle(rs.getString("title"));
				p.setCategory(rs.getString("category"));
				p.setGST(Double.parseDouble(rs.getString("hsnc_gstc_percentage")));
				p.setImage(rs.getString("image"));
				p.setPrice(rs.getDouble("price"));
//				System.out.println(rs.getDouble("prod_mrp"));
				p.setMRP(rs.getDouble("prod_mrp"));
				System.out.println(p);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return p;
	}
	public double getGST(int hsnid) throws SQLException {
		String query = "select hsnc_gstc_percentage from hsncodes_1  where hsnc_id = ?";
		PreparedStatement pst;
		pst = con.prepareStatement(query);
		double gst=0;
		//System.out.println(hsnid);
		pst.setInt(1,hsnid);
		ResultSet rs = pst.executeQuery();
		//System.out.println(rs);
		if (rs.next()) {
			gst= rs.getDouble(1);
//			System.out.println(gst);
		}
		return gst;
	}

	@Override
	public ProductList getProductsList(List<CartItems> items) {
		// TODO Auto-generated method stub
		ProductList pl = new ProductList();
		for(CartItems item : items) {
			Product p = getProductById(item.getId());
//			if(updateStockByProductId(p.getId(), item.getQuantity())) {
//				
//			}else {
//				p.setQty(0);
//			}
			p.setQty(item.getQuantity());
			pl.add(p);
		}
		return pl;
	}

	private boolean updateStockByProductId(int id,int qty) {
		
		boolean status = false;
		String query = "UPDATE productstocks_1 SET prod_stock = prod_stock - ? where prod_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, qty);
			ps.setInt(2, id);
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected>0)
				status = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return status;
	}

	@Override
	public boolean updateStock(ProductList products, String orderId) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO order_products_1 (order_id, prod_id, quantity, price) VALUES (?, ?, ?, ?)";
		boolean flag = false;
		for(Product p : products) {
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1,Integer.parseInt(orderId));
				ps.setInt(2, p.getId());
				ps.setInt(3, p.getQty());
				ps.setDouble(4, p.getPrice());
				
				if(ps.executeUpdate()>0) {
					flag = updateStockByProductId(p.getId(), p.getQty());
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	

}