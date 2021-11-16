package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Supplier;
import service.SupplierServiceImpl;
import service.ISupplierService;

/**
 * Servlet implementation class LoginServlet
 */
public class AddSupplierServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSupplierServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		Supplier supplier = new Supplier();
		
		supplier.setName(request.getParameter("name"));
		supplier.setContactNo(request.getParameter("contactNo"));
		supplier.setAddress(request.getParameter("address"));
		supplier.setItemsServices(request.getParameter("itemsServices"));
		supplier.setPrice(request.getParameter("price"));
		supplier.setDiscounts(request.getParameter("discounts"));

		ISupplierService iSupplierService = new SupplierServiceImpl();
		iSupplierService.addSupplier(supplier);

		request.setAttribute("supplier", supplier);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListSuppliers.jsp");
		dispatcher.forward(request, response);
	}

}
