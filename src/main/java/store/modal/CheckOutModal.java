package store.modal;

public class CheckOutModal {
	private double subTotal;
	private double shipping_tax;
	private double shipping_charges;
	private double total;

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getShipping_tax() {
		return shipping_tax;
	}

	public void setShipping_tax(double shipping_tax) {
		this.shipping_tax = shipping_tax;
	}

	public double getShipping_charges() {
		return shipping_charges;
	}

	public void setShipping_charges(double shipping_charges) {
		this.shipping_charges = shipping_charges;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
