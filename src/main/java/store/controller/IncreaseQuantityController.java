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
@WebServlet("/IncreaseQuantityController")
public class IncreaseQuantityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int prodId = Integer.parseInt(request.getParameter("pid"));
//		System.out.println("Increase");
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<CartItems> cartItems = (List<CartItems>) session.getAttribute("cartitems");
		if (cartItems != null) {
			for (CartItems item : cartItems) {
				if (item.getId() == prodId) {
					item.setQuantity(item.getQuantity() + 1);
					break;
				}
			}
		}
//		for (CartItems item : cartItems) {
//			System.out.println(item.display());
//		}

		session.setAttribute("cartitems", cartItems);

		// Send response to update the UI
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("success");
	}
}
