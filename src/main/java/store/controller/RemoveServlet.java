package store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import store.modal.CartItems;
@WebServlet("/RemoveServlet")
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("pid"));

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<CartItems> cartItems = (ArrayList<CartItems>) session.getAttribute("cartitems");

		if (cartItems != null) {
			// Iterate over the cart items and remove the item with the specified product ID
			for (int i = 0; i < cartItems.size(); i++) {
				if (cartItems.get(i).getId() == productId) {
					cartItems.remove(i);
					break; // Exit the loop after removing the item
				}
			}

			// Update the session attribute with the modified cart items list
			session.setAttribute("cartitems", cartItems);

			// Send response to update the UI
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("success");
		} else {
			// If cartItems is null, send an error response
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Cart is empty");
		}
	}
}
