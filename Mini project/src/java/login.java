import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;


public class login extends HttpServlet
{
	public void doPost(HttpServletRequest request ,HttpServletResponse response)throws ServletException,IOException
	{
		PrintWriter out=response.getWriter();
		String s1=request.getParameter("username");
		String s2=request.getParameter("password");
		out.println("<html>");
		out.println("<body>");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql:///ravidb?useSSL=false","root","root");
			Statement st=con.createStatement();
			String q=" select * from login where  UNAME='"+s1+"' AND UPASS='"+s2+"' ";
			ResultSet rs=st.executeQuery(q);
			if(rs.next())
			{
                            /*
                            HttpSession session=request.getSession();
                            session.setAttribute("uname",s1);
                                    */
                         Cookie ck=new Cookie("uname","ravi");
                         ck.setMaxAge(60*60*4);
                         response.addCookie(ck);
			response.sendRedirect("Menudemo");
			}
			else
			{
				out.println("<h1>invalid username and password </h1>");
			}
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