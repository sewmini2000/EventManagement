package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String employeeID=request.getParameter("employeeID");
		String password=request.getParameter("password");
		
		if (employeeID.equals("admin") && password.equals("admin")){
			response.sendRedirect("homeView.jsp");
		}
		else {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop?characterEncoding=latin1", "root", "");
			
				PreparedStatement ps = c.prepareStatement("select * from customer where customerID=? and nIC=?");
				ps.setString(1, employeeID);
				ps.setString(2, password);
		 
				ResultSet rs = ps.executeQuery();
		 
				while (rs.next()) {
					response.sendRedirect("homeView1.jsp");
					return;
				}
				response.sendRedirect("Login.jsp");
				return;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

}
}