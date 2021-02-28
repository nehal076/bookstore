package ssnbookstore;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class checkout
 */
@WebServlet("/checkout")
public class checkout extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkout() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	 HttpSession ssn=request.getSession(false);
	 ServletContext sc=request.getServletContext();
	 books b=(books)sc.getAttribute("home");
	 int qty=0;
	 

	 Enumeration et=ssn.getAttributeNames();
	 while(et.hasMoreElements())
	 {
	  int bookid=Integer.parseInt(et.nextElement()+"");
	  
	  BookDetail bd=b.books.get(bookid);
	  qty=(int)ssn.getAttribute(bookid+"");
	  
	  bd.qoh=bd.qoh-(int)ssn.getAttribute(bookid+"");
	  ssn.removeAttribute(bookid+"");	  
	 }
	 
	 String html="<html><body bgcolor=teal><center>"
			    +"<font face='lucidia console' size=6 color=yellow>"
			    +"<center><h1><br><br><br>Order successfully placed!<br><br></font>"
			    +"<img src=\"checkout.png\" height=70 width=70 ><br><br>"
			    +"<a href='books'>"
	 			+"<input type=button value='Back To Bookstore'"
	 			+"style=\"background-color:#ff9f00;box-shadow: 0 1px 2px 0 rgba(0,0,0,.2);padding: 18px 8px;border-radius: 2px;border:none;color: #fff;size:14;\"></a>"
			    +"</center></body></html>";

	 
	 response.getWriter().println(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
