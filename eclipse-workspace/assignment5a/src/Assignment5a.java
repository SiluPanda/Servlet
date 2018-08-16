
import java.io.PrintWriter;
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Assignment5a
 */
@WebServlet("/Assignment5a")
public class Assignment5a extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Assignment5a() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		PrintWriter out = response.getWriter();
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
		try (
			    Connection conn = DriverManager.getConnection(
			    		"jdbc:postgresql://localhost:8000/postgres", "labuser", "");
			    Statement stmt = conn.createStatement();
			){
			
			PrintWriter out = response.getWriter();
			out.println("<html><form action=\"Assignment5a \" method=\"post\"> Enter your name: <input type=\"text\" name = \"name\"> <br> Enter your password: <input type=\"text\" name = \"password\"> <br> <input type=\"submit\" value = \"Submit\"> </form> </html>");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			PreparedStatement ps = conn.prepareStatement("select password from password where id = ?");
			ps.setString(1,name);
			ResultSet rset = ps.executeQuery();
			String pass_original = "";
			while(rset.next()) {
				 pass_original = rset.getString(1);
				 //out.println("loop running");
			}
			if(password.isEmpty() || name.isEmpty()) {
				out.println("ID or password can not be blank");
			}
			else {
			if(password.equals(pass_original)) {
				out.println("Congrats, You are logged in!");
				HttpSession session =request.getSession();
				
				session.setAttribute("att_name", name);
				response.sendRedirect("Home");
			}
			else {
				out.println("Sorry, try again");
			}
			}
			
			
			
		}
		catch (Exception sqle)
		{
		System.out.println("Exception : " + sqle);
		}
		
	}

}
