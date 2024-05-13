package store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import store.BLL.Bll;
import store.DAL.Products;
import store.DAO.BLLBridge;
import store.DAO.DataBaseBridge;
import store.modal.CartItems;
import store.modal.CheckOutModal;
import store.modal.Product;
import store.modal.ProductList;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ProductList products = new ProductList();
		HttpSession hs = request.getSession();
		List<CartItems> cartitems = (ArrayList<CartItems>) hs.getAttribute("cartitems");
		Products p = new DataBaseBridge().getProductDAL();
		for(CartItems c : cartitems) {
			Product p1 = p.getProductById(c.getId());
			p1.setQty(c.getQuantity());
			products.add(p1);
		}
		Bll bussinessLogic = BLLBridge.getBLLObject();
		CheckOutModal c = bussinessLogic.getCheckOutDetails(products);
		request.setAttribute("checkoutDetails", c);
		hs.setAttribute("checkoutDetails", c);
		RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
		rd.forward(request, response);
	}

}
