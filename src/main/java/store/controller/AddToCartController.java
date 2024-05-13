package store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import store.modal.CartItems;

@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println("pid : "+request.getParameter("pid"));
		int prodId = Integer.parseInt(request.getParameter("pid"));
//		int prodId = 1;

		HttpSession session = request.getSession(true);
		@SuppressWarnings("unchecked")
		List<CartItems> cartItems = (List<CartItems>) session.getAttribute("cartitems");
		if (cartItems == null) {
			cartItems = new ArrayList<>();
		}

		boolean productExists = false;
		boolean flag = false;
		for (CartItems item : cartItems) {
			if (item.getId() == prodId) {
				item.setQuantity(item.getQuantity() + 1);
				productExists = true;
				flag = true;
				break;
			}
		}

		if (!productExists) {
			CartItems item = new CartItems(prodId, 1);
			cartItems.add(item);
			flag = true;
		}

		session.setAttribute("cartitems", cartItems);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		if(flag) {
			out.write("{\"status\":true}");
		}else {
			out.write("{\"status\":false}");
		}
	}
}
