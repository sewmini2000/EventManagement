package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Event;
import service.EventServiceImpl;
import service.IEventService;

public class UpdateEventServlet  extends HttpServlet{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateEventServlet() {
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

		Event event = new Event();
		String eventID = request.getParameter("eventID");	
		event.setEventID(eventID);
		event.setCustomerName(request.getParameter("customerName"));
		event.setType(request.getParameter("type"));
		event.setQuantity(request.getParameter("quantity"));
		event.setHours(request.getParameter("hours"));
		event.setLocation(request.getParameter("location"));
		event.setTime(request.getParameter("time"));
		event.setDescription(request.getParameter("description"));
		
		
		IEventService iEventService = new EventServiceImpl();
		iEventService.updateEvent(eventID, event);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListEvents.jsp");
		dispatcher.forward(request, response);
	}

	

}
