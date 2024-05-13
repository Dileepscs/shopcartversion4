package store.modal;


public class BillDetails {
	
	private int billNo;
	private String billDate;
	private String orderId;
	private String paymentId;
	private double netAmount;
	private ProductList products;
	private CheckOutModal delivery;
	private CouponDetails coupon;
	private Customer customer;
	
	public int getBillNo() {
		return billNo;
	}
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
	public ProductList getProducts() {
		return products;
	}
	public void setProducts(ProductList products) {
		this.products = products;
	}
	public CheckOutModal getDelivery() {
		return delivery;
	}
	public void setDelivery(CheckOutModal delivery) {
		this.delivery = delivery;
	}
	public CouponDetails getCoupon() {
		return coupon;
	}
	public void setCoupon(CouponDetails coupon) {
		this.coupon = coupon;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
