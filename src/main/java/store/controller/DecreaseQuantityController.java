package store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import store.modal.CartItems;

/**
 * Servlet implementation class DecreaseQuantityController
 */
@WebServlet("/DecreaseQuantityController")
public class DecreaseQuantityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int prodId = Integer.parseInt(request.getParameter("pid"));

		HttpSession session = request.getSession();
		List<CartItems> cartItems = (List<CartItems>) session.getAttribute("cartitems");
		if (cartItems != null) {
			for (CartItems item : cartItems) {
				if (item.getId() == prodId) {
					int newQuantity = item.getQuantity() - 1;
					if (newQuantity >= 0) {
						item.setQuantity(newQuantity);
						break;
					}
				}
			}
//			for (CartItems item : cartItems) {
//				System.out.println(item.display());
//			}
		}
	}
}
