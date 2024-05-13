package store.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.DAL.DropDown;
import store.DAO.DataBaseBridge;
import store.modal.CategoeryList;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataBaseBridge dao = new DataBaseBridge();
		DropDown d = dao.getDropDownDAL();
		CategoeryList cl = d.getCategories();
		// HttpSession hs = request.getSession(true);
		request.setAttribute("categories", cl);
		RequestDispatcher rd = request.getRequestDispatcher("store.jsp");
		rd.forward(request, response);
	}

}
