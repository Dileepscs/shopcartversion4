/**
 * 
 */
package store.DAL;

import java.sql.PreparedStatement;
import java.util.List;

import store.modal.CartItems;
import store.modal.Product;
import store.modal.ProductList;

/**
 * @author DileepKumarK
 *
 */
public interface Products {

	ProductList getProducts(PreparedStatement ps);

//	ProductList getProducts(String cat, String sort);

	Product getProductById(int id);

	ProductList getProducts(String cat);

	ProductList getProductsList(List<CartItems> items);

	boolean updateStock(ProductList products, String orderId);

	// ProductList getProducts(String cat);
}
