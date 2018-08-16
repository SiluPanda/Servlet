import java.io.PrintWriter;
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/displaygrades")
public class displaygrades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public displaygrades() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name_logg = (String) session.getAttribute("att_name");
		try (
			    Connection conn = DriverManager.getConnection(
			    		"jdbc:postgresql://localhost:8000/postgres", "labuser", "");
			    Statement stmt = conn.createStatement();
			)
		{
			PrintWriter out = response.getWriter();
			PreparedStatement pstm = conn.prepareStatement("select * from takes where ID = ?");
			pstm.setString(1,name_logg);
			
			ResultSet rset = pstm.executeQuery();
			out.println("<table <tr> <th> ID </th> <th> course_id </th> <th> sec_id </th> <th> semester </th> <th> year </th> <th> grade </th> </tr>");
			while(rset.next()) {
				
				
				
				out.println("<tr> <td>" + rset.getString(1) + "</td>  <td>" + rset.getString(2) + " </td> <td>" +rset.getString(3)+ "  </td> <td>" + rset.getString(4) + " </td> <td> " + rset.getInt(5)+ "  </td> <td>" + rset.getString(6) + "</tr> ");
				
			}
			out.println("</table> <br>");
		
	
		
		//out.println("<html> <a href = \"http://localhost:8080/assignment5a/logout \"> Logout </a> </html> ");
	
		}
		catch (Exception sqle)
		{
		System.out.println("Exception : " + sqle);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
