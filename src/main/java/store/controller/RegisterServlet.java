package store.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.DAL.User;
import store.DAO.DataBaseBridge;
import store.modal.Customer;
/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataBaseBridge dao = new DataBaseBridge();
		User u = dao.getUserDAL();
		RequestDispatcher rd;
		Customer c = new Customer();
		c.setCname(request.getParameter("logname"));
		c.setCemail(request.getParameter("logemail"));
		c.setCpassword(request.getParameter("logpass"));
		c.setCmobile(request.getParameter("logphone"));
		if (u.createUser(c)) {
			rd = request.getRequestDispatcher("customer-login.jsp");
			rd.forward(request, response);
		}
	}

}
