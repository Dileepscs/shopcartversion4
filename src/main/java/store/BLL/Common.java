package store.BLL;

import java.sql.Date;
import java.util.List;

import store.DAL.Products;
import store.DAO.DataBaseBridge;
import store.modal.BillDetails;
import store.modal.CartItems;
import store.modal.CheckOutModal;
import store.modal.CouponDetails;
import store.modal.Customer;
import store.modal.Product;
import store.modal.ProductList;

public class Common implements CommonBLL {

	@Override
	public boolean validateCoupon(CouponDetails coupon) {
		// TODO Auto-generated method stub
		
		return coupon.isValid();
	}

	@Override
	public BillDetails createBill(List<CartItems> items, CouponDetails coupon, CheckOutModal checkoutDetails,
			Customer customer) {
		// TODO Auto-generated method stub
		DataBaseBridge dao = new DataBaseBridge();
		BillDetails bill = new BillDetails();
		Products productdal = dao.getProductDAL();
		ProductList products = productdal.getProductsList(items);
		float discountAmt = (float) (coupon.getDiscount()*0.01*checkoutDetails.getSubTotal());
		calculateDiscountPerProduct(products,checkoutDetails.getSubTotal(),discountAmt);
//		calculateDiscountPerProduct(products,coupon.getDiscount());
		bill.setProducts(products);
		
		bill.setDelivery(checkoutDetails);
		bill.setCustomer(customer);
		double totalAmt = checkoutDetails.getTotal();
		totalAmt = totalAmt - (totalAmt*coupon.getDiscount()*0.01);
		bill.setNetAmount(totalAmt);
		store.DAL.Common dal = dao.getCommonDAL();
		//Date d = new Date(new java.util.Date().getTime());
		@SuppressWarnings("deprecation")
		Date d = new Date(2024, 5, 13);
		System.out.println(d.toString());
		bill.setBillDate("2024-05-13");
		String orderId = dal.createOrder("2024-05-13",totalAmt,customer.getCid(),products);
		if(orderId==null) {
			return null;
		}
		return bill;
	}

	private void calculateDiscountPerProduct(ProductList products, double subTotal, float discountAmt) {
		// TODO Auto-generated method stub
		for(Product p : products) {
			double price_without_gst;
			double price = p.getPrice();
			price_without_gst=price/(1+(p.getGST()*0.01));
			p.setPrice_without_gst(price_without_gst);
			double weightage = (p.getPrice()*p.getQty())/subTotal;
			p.setDiscount(weightage*discountAmt);
		}
	}
	
//	public void calculateDiscountPerProduct(ProductList products, float discount) {
//		// TODO Auto-generated method stub
//		double subtotal=0;
//		for(Product p : products) {
//			double price_without_gst;
//			double price = p.getPrice();
//			price_without_gst=price/(1+(p.getGST()*0.01));
//			p.setPrice_without_gst(price_without_gst);
//			subtotal+=p.getPrice_without_gst();
//		}
//		for(Product p : products) {
//			double weightage = (p.getPrice_without_gst()*p.getQty())/subtotal;
//			p.setDiscount(weightage*(discount*0.01)*p.getPrice_without_gst());//since discount is in percentage 
//		}
//	}

	
}
