import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Regisdemo extends HttpServlet
{
	public void doPost(HttpServletRequest request ,HttpServletResponse response)throws ServletException,IOException
	{
		PrintWriter out=response.getWriter();
		String s1=request.getParameter("name");
		String s2=request.getParameter("email");
		String s3=request.getParameter("password");
		out.println("<html>");
		out.println("<body>");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql:///ravidb?useSSL=false","root","root");
			Statement st=con.createStatement();
			String q="insert into login values('"+s1+"','"+s2+"','"+s3+"')";
			st.executeUpdate(q);
			response.sendRedirect("Logindemo.html");
			con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
		out.println("</body>");
		out.println("</html>");
	}	
}