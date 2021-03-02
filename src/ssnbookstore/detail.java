package ssnbookstore;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


/**
 * Servlet implementation class detail
 */
@WebServlet("/detail")
public class detail extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detail() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	 
	     int bookid=Integer.parseInt(request.getParameter("bookid")); 

		 System.out.println("\nbook id : "+bookid);
		 
		 int i;
		 ServletContext sc=request.getServletContext();
		 books b=(books)sc.getAttribute("home");	 

		 System.out.println("\nrecd book ref");
		 	 
		 BookDetail bd=b.books.get(bookid);
		 
		 float disc=(float) (((float)bd.discount/100.0)*bd.price);
		 int rate=(int) (bd.price-disc);
		 
		 String html="<html><body bgcolor=teal><center>"
		 		    +"<div style=\"border:solid 1px red;margin:10px;padding:50px;display: block;width: 350px;\">"
		 		    +"<form action=addtocart>"
					+"<img src='"+bookid+".jpg' height=300 width=240>"
					+"<font face=\"lucida console\" size=4 color=yellow>"
		 			+"<h3 >"+bd.title+"</h3></font>"
		 			+"<h4>("+bd.author+")</h4>"
		            +"<h1 style=\"float:left;\"><font color=black>Rs."+rate+"</font></h1>"
	                +"<h3 style=\"float:left;\"><font color=gray><strike>Rs."+bd.price+"</strike></font></h3>"
	                +"<h3 style=\"float:left;\"><font color=green>"+bd.discount+"% off!</font></h3>";
	                
	     if(bd.qoh==0)
		 {
			html=html+"OUT OF STOCK<br><br>"; 
		 }
	     else
	     {
		  html=html+"<br><br>Quantity <select name=qty>";
		 
		  for(i=1; i<=bd.qoh; i++)
		  {
		   html=html+"<option>"+i+"</option>";
		  }
	               
	       html=html+"</select><br><br><br>"
	     		  +"<input type=hidden name=\"bookid\" value="+bookid+">";
	     }
	     html=html+"<input type=submit value='ADD TO CART' name=\"kart\" "
	     		  +"style=\"background-color:#ff9f00;box-shadow: 0 1px 2px 0 rgba(0,0,0,.2);padding: 18px 8px;border-radius: 2px;border:none;color: #fff;size:14;\"/> "
	     		  +"<a href='books'>"
		          +"<input type=button value='CONTINUE SHOPPING' name=\"back\" "
		          +"style=\"background-color:#ff9f00;box-shadow: 0 1px 2px 0 rgba(0,0,0,.2);padding: 18px 8px;border-radius: 2px;border:none;color: #fff;size:14;\"/></a>";

		  
		 html=html+"</form></div></center></body></html>";
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
