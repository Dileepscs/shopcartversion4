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

import store.BLL.CommonBLL;
import store.DAO.BLLBridge;
import store.modal.BillDetails;
import store.modal.CartItems;
import store.modal.CheckOutModal;
import store.modal.CouponDetails;
import store.modal.Customer;

/**
 * Servlet implementation class BillServlet
 */
@WebServlet("/showBill")
public class BillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="bill.jsp";
		
		
		HttpSession hs = request.getSession();
		List<CartItems> items = (ArrayList<CartItems>) hs.getAttribute("cartitems");
//		System.out.println(hs.getAttribute("cartitems")==null);
//		System.out.println(hs.getAttribute("cartitems"));
		CouponDetails coupon = (CouponDetails) hs.getAttribute("coupon"); 
		CheckOutModal checkoutDetails = (CheckOutModal) hs.getAttribute("checkoutDetails");
		Customer customer = (Customer) hs.getAttribute("CustomerDetails");
		
		
		CommonBLL bll = BLLBridge.getCommonBLLObject();
		if(coupon == null) {
			coupon = new CouponDetails();
			coupon.setDiscount(0);
		}
		System.out.println("coupon discount : "+coupon.getDiscount());
		BillDetails bill = bll.createBill(items,coupon,checkoutDetails,customer);
		
		bill.setPaymentId(request.getParameter("payment_id"));
		hs.setAttribute("bill", bill);
		
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();	
//		out.write("{\"status\":"+url+"}");
		out.write("{\"url\":\"" + url + "\"}");
	}



}
