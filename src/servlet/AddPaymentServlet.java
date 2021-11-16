package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Payment_info;
import service.PaymentServiceImpl;
import service.IPaymentService;


public class AddPaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AddPaymentServlet() {
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

		Payment_info payment = new Payment_info();
		
		payment.setCustomerName(request.getParameter("customerName"));
		payment.setAddress(request.getParameter("address"));
		payment.setCardType(request.getParameter("cardType"));
		payment.setCardNumber(request.getParameter("cardNumber"));
		payment.setExpDate(request.getParameter("expDate"));
		payment.setCvc(request.getParameter("cvc"));
		payment.setTotal(request.getParameter("total"));


		IPaymentService iPaymentService = new PaymentServiceImpl();
		iPaymentService.addPayment(payment);

		request.setAttribute("payment", payment);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListPayments.jsp");
		dispatcher.forward(request, response);
	}

}
