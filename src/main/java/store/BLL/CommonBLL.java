package store.BLL;

import java.util.List;

import store.modal.BillDetails;
import store.modal.CartItems;
import store.modal.CheckOutModal;
import store.modal.CouponDetails;
import store.modal.Customer;

public interface CommonBLL {

	boolean validateCoupon(CouponDetails coupon);

	BillDetails createBill(List<CartItems> items, CouponDetails coupon, CheckOutModal checkoutDetails,
			Customer customer);
	
}
