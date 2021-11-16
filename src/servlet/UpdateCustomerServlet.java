package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerServiceImpl;
import service.ICustomerService;

public class UpdateCustomerServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCustomerServlet() {
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

		Customer customer = new Customer();
		String customerID = request.getParameter("customerID");	
		customer.setCustomerID(customerID);
		customer.setFirstName(request.getParameter("firstName"));
		customer.setLastName(request.getParameter("lastName"));
		customer.setNIC(request.getParameter("nIC"));
		customer.setMobile(request.getParameter("mobile"));
		customer.setAddress(request.getParameter("address"));
		customer.setBirthday(request.getParameter("birthday"));
		customer.setGender(request.getParameter("gender"));
		
		ICustomerService iCustomerService = new CustomerServiceImpl();
		iCustomerService.updateCustomer(customerID, customer);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListCustomers.jsp");
		dispatcher.forward(request, response);
	}

	

}
