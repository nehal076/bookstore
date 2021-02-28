package ssnbookstore;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class addtocart
 */
@WebServlet("/addtocart")
public class addtocart extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addtocart() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		 ServletContext sc=request.getServletContext();
		 books b=(books)sc.getAttribute("home");
			 
		 int qty=Integer.parseInt(request.getParameter("qty"));
		 int bookid=Integer.parseInt(request.getParameter("bookid"));
		 BookDetail bd=b.books.get(bookid);
		 int n=0;

		 String html="<html>";

		 
		 HttpSession ssn=request.getSession(false);
		 if(ssn==null)
		 {
			 ssn=request.getSession();		 
		 }
		 else
		 {
		  try
		  {
		   n=(int)ssn.getAttribute(bookid+"");
           if(n!=0)
           {
        	   qty=qty+n;
           }
		  }
		  catch(NullPointerException e) {}	  
		  	  
		 } 
		 
		 ssn.setAttribute(bookid+"", qty);
		 
		 bd.qoh=bd.qoh-qty;	
		 
		 html=html+"<body bgcolor=teal><center>"
				  +"<img src='"+bookid+".jpg' height=100 width=100>"
				  +bd.title+" "+bd.price+" "
				  +qty+" item(s) added.<br>"
				  +"<a href='books'>"
		          +"<input type=button value='CONTINUE SHOPPING' name=\"back\" "
		          +"style=\"background-color:#ff9f00;box-shadow: 0 1px 2px 0 rgba(0,0,0,.2);padding: 18px 8px;border-radius: 2px;border:none;color: #fff;size:14;\"/></a>";
		   
		 response.getWriter().println(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
