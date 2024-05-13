package store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import store.DAO.DataBaseBridge;
import store.modal.ProductList;
/**
 * Servlet implementation class ProductsServlet
 */
@SuppressWarnings("serial")
@WebServlet("/product")
public class ProductsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Gson gson = new Gson();
		res.setHeader("Access-Control-Allow-Origin", "*"); // Allow all origins
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		String category = req.getParameter("category");
		String sort = req.getParameter("sort");
		Integer pages = Integer.parseInt(req.getParameter("pages"));
//		System.out.println(category + sort);
		if ("null".equalsIgnoreCase(category)) {
			category = null;
			System.out.println("Category is null or empty");
		}

		if ("null".equalsIgnoreCase(sort)) {
			sort = null;
		}
		DataBaseBridge dao = new DataBaseBridge();
		ProductList pl = dao.getProductDAL().getProducts(category);
		res.setContentType("application/json");
		res.getWriter().write(gson.toJson(pl));
		// res.sendRedirect("www.google.com");
	}

}
