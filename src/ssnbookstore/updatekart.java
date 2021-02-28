package ssnbookstore;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class updatekart
 */
@WebServlet("/updatekart")
public class updatekart extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatekart() 
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
		 String html="<html><body bgcolor=teal><center>"
				    +"<font face='lucidia console' size=6 color=yellow>"
				    +"Your order(s)</font>";

		 

		  Enumeration et=ssn.getAttributeNames();
	      while(et.hasMoreElements())
	      {

	       int bookid=Integer.parseInt(et.nextElement()+"");
	       BookDetail bd=b.books.get(bookid);
	       int qty=(int) ssn.getAttribute(bookid+"");
	       int upd;
	       
	       try
	       {
	        upd=Integer.parseInt(request.getParameter("updqty"));

	    	 System.out.println("try - "+upd);
	       }
	       catch(NumberFormatException e)
	       {
	    	 upd=qty;
	    	 System.out.println("catch - "+upd);
	       }
	       
	       
	       if(upd==0)
	       {
	    	   ssn.removeAttribute(bookid+"");
	       }
	       else
	       {
	    	   ssn.setAttribute(bookid+"", upd);
	       }
	      
		   float disc=(float) (((float)bd.discount/100.0)*bd.price);
		   int rate=(int) (bd.price-disc);
	 
	       html=html+"<form action=\"updatekart\">"
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
	               +"  <br>Qty:<input type=number max='"+bd.qoh+"' min=0 value='"+upd+"' name='updqty'>"
	               +"  <input type=hidden value='"+bookid+"' name=\"bookid\">"             
	      		   +" </div>"
	      		   +"</div></form><br>";
	       
	       

	      }
	  
		  html=html+ "<a href='updatekart'>"
		 		   + "<input type=button value='Update Kart'"
		 		   + "style=\"background-color:#ff9f00;box-shadow: 0 1px 2px 0 rgba(0,0,0,.2);padding: 18px 8px;border-radius: 2px;border:none;color: #fff;size:14;\">"
		 		   + "</center></body></html>";

		 
		 response.getWriter().println(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
