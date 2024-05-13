package store.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import store.BLL.Bll;
import store.BLL.CommonBLL;
import store.DAL.Common;
import store.DAO.BLLBridge;
import store.DAO.DataBaseBridge;
import store.modal.CouponDetails;

/**
 * Servlet implementation class CouponServlet
 */
@WebServlet("/checkcoupon")
public class CouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean status=false;
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String code = request.getParameter("code");
		
		System.out.println(code);
		DataBaseBridge dao = new DataBaseBridge();
		Common dal = dao.getCommonDAL();
		CouponDetails coupon = dal.getCouponDetails(code);
//		Bll ;
		CommonBLL bll = BLLBridge.getCommonBLLObject();
		status = bll.validateCoupon(coupon);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
//		System.out.println(coupon.getDiscount());
		if(status) {
			HttpSession hs = request.getSession();
			hs.setAttribute("coupon", coupon);
//			System.out.println("{\"discount\":"+coupon.getDiscount()+"}");
			 out.write("{\"status\":true, \"discount\":" + coupon.getDiscount() + "}");
		}else {
			out.write("{\"status\":false, \"discount\":0.0}");
		}
	}

}
