package store.DAO;

import store.DAL.Common;
import store.DAL.CommonDAL;
import store.DAL.DropDown;
import store.DAL.DropDownDAL;
import store.DAL.Products;
import store.DAL.ProductsDAL;
import store.DAL.User;
import store.DAL.UserDAL;

public class DataBaseBridge {

	public User getUserDAL() {
		return new UserDAL();
	}

	public DropDown getDropDownDAL() {
		return new DropDownDAL();
	}

	public Products getProductDAL() {
		return new ProductsDAL();
	}

	public Common getCommonDAL() {
		return new CommonDAL();
	}
}
