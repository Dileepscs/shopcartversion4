package store.DAL;

import java.util.List;

import store.modal.BillDetails;
import store.modal.CartItems;
import store.modal.CheckOutModal;
import store.modal.CouponDetails;
import store.modal.ProductList;

public interface Common {
	public double getBaseShippingAmount(double amt);

//	public CouponDetails getCouponStatus(CouponDetails coupon);

	public CouponDetails getCouponDetails(String code);

	public String createOrder(String d, double totalAmt, int cid, ProductList products);

//	public BillDetails createBill(List<CartItems> items, CouponDetails coupon, CheckOutModal checkoutDetails,Customer customer);
}
