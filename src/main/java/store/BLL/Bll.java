package store.BLL;

import store.modal.CheckOutModal;
import store.modal.ProductList;

public interface Bll {
	// CheckOutModal getCheckOutDetails();

	CheckOutModal getCheckOutDetails(ProductList pl);
}
