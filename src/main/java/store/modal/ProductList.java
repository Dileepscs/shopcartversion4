package store.modal;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class ProductList extends ArrayList<Product> {
	public double sum() {
		double s = 0;
		for (Product p : this)
			s += p.getPrice()*p.getQty();
		return s;
	}
}
