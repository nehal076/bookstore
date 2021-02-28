package ssnbookstore;

import java.io.*;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class kart
 */
@WebServlet("/kart")
public class kart extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public kart() 
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
	
		//int bookid=Integer.parseInt(request.getParameter("bookid"));
	     ServletContext sc=request.getServletContext();
		 books b=(books)sc.getAttribute("home");
		 int delbid,bid,qty1 = 0;
		 try
		 {
		  bid=Integer.parseInt(request.getParameter("bookid"));
		  qty1=Integer.parseInt(request.getParameter("updqty"));
		 }
		 catch(NumberFormatException e)
		 {
		  bid=0;
		 }
		 
		 if(bid!=0)
			 ssn.setAttribute(bid+"",qty1);
		 
		 try
		 {
		  delbid=Integer.parseInt(request.getParameter("delbookid"));  
		 }
		 catch(NumberFormatException e)
		 {
		  delbid=0;
		 }
		 if(delbid!=0)
			 ssn.removeAttribute(delbid+"");
		 
	 
		 String html="<html><body bgcolor=teal>"
				 	+"<a href='books'>"
		 			+"<input type=button value='Back To Bookstore'"
		 			+"style=\"background-color:#ff9f00;box-shadow: 0 1px 2px 0 rgba(0,0,0,.2);padding: 18px 8px;border-radius: 2px;border:none;color: #fff;size:14;\"></a>"
		 			+"<center>"
				    +"<font face='lucidia console' size=6 color=yellow>"
				    +"Your order(s)</font>";

		  Enumeration et=ssn.getAttributeNames();
	      while(et.hasMoreElements())
	      {
	       int bookid=Integer.parseInt(et.nextElement()+"");
	       BookDetail bd=b.books.get(bookid);
	       int qty=(int) ssn.getAttribute(bookid+"");	 
	      
		   float disc=(float) (((float)bd.discount/100.0)*bd.price);
		   int rate=(int) (bd.price-disc);
	 
	       html=html+"<form action=\"kart\">"
	    	 	   +"<div style=\"width:1000px; height:200px;border:solid 1px yellow;margin:10px;padding:20px;display: block;\">"
	    		   +" <div style=\"width:250px; height:80px; float:left;\">"
	      		   +"  <img src='"+bookid+".jpg' height=200 width=180>"
	      		   +" </div>"
	    		   +" <div style=\"width:500px; height:80px; float:left;\">"
	      		   +"  <font face='lucida console' size=4 color=orange>"
			 	   +"  <h3 >"+bd.title+"</h3></font>"
	      		   +"  <h1 style=\"float:left;\"><font color=black>Rs."+rate+"</font></h1>"
	               +"  <h3 style=\"float:left;\"><font color=gray><strike>Rs."+bd.price+"</strike></font></h3>"
	               +"  <h3 style=\"float:left;\"><font color=green>"+bd.discount+"% off!</font></h3>"
	               +"  <br>Qty:<input type=number max='"+bd.qoh+"' min=1 value='"+qty+"' name='updqty'>"
	               +"  <input type=hidden value='"+bookid+"' name=\"bookid\">"
	               +"  <a href='updatekart'><input type=submit value='Update' name='upd'></a>"
	               +"  <a href='kart?delbookid="+bookid+"'><input type=button value='Delete' name='dlt'></a>"
	      		   +" </div>"
	      		   +"</div></form><br>";

	      }
	  
		  html=html+ "<a href='checkout'>"
		 		   + "<input type=button value='Checkout'"
		 		   + "style=\"background-color:#ff9f00;box-shadow: 0 1px 2px 0 rgba(0,0,0,.2);padding: 18px 8px;border-radius: 2px;border:none;color: #fff;size:14;\">"
		 		   + "</center></body></html>";
		 
		 response.getWriter().println(html);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}
}
