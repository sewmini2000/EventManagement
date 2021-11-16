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
 * Update supplier servlet
 */
public class UpdateSupplierServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateSupplierServlet() {
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
		String supplierID = request.getParameter("supplierID");	
		supplier.setSupplierID(supplierID);
		supplier.setName(request.getParameter("name"));
		supplier.setContactNo(request.getParameter("contactNo"));
		supplier.setAddress(request.getParameter("address"));
		supplier.setItemsServices(request.getParameter("itemsServices"));
		supplier.setPrice(request.getParameter("price"));
		supplier.setDiscounts(request.getParameter("discounts"));
		
		ISupplierService iSupplierService = new SupplierServiceImpl();
		iSupplierService.updateSupplier(supplierID, supplier);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListSuppliers.jsp");
		dispatcher.forward(request, response);
	}

}
