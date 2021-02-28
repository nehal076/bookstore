package ssnbookstore;


import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


/**
 * Servlet implementation class books
 */
@WebServlet("/books")
public class books extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    int userid=1000;
	
	Hashtable<Integer,BookDetail> books = new Hashtable<>();
	Hashtable<Integer,Hashtable<Integer,Integer>> users = new Hashtable<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public books() 
    {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException 
	{
     super.init(config);
	 try
	 {
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection cn=DriverManager.getConnection("jdbc:mysql://sociogram.c9rgg7nk0bfq.us-east-2.rds.amazonaws.com:3306/bookstore","admin","Nehal!076");
	  Statement st=cn.createStatement();
	  ResultSet rs=st.executeQuery("select * from bookstore.books"); 
     
	  while(rs.next())
	  {  
	   int bookid=rs.getInt("bookid");
	   books.put(bookid, new BookDetail(bookid,rs.getString("title"),rs.getString("author"),rs.getInt("price"),rs.getInt("discount"),rs.getInt("qoh")));
	  }   
	 }
	 catch(ClassNotFoundException e)
	 {
	  System.err.println("\ninit - "+e.getMessage()+"\n");
	 }
	 catch(SQLException e)
	 {
	  System.err.println("\nsql alert - init - "+e.getMessage()+"\n");
	 }
	 
	 ServletContext sc=getServletContext();
	 sc.setAttribute("home", this);
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() 
	{
	 System.out.println("Destroy called");
	 Enumeration et=books.keys();
	 while(et.hasMoreElements())
	 {
	  int bookid=(int)et.nextElement();
      	  BookDetail bd=books.get(bookid);
	  try
	  {
	   Class.forName("com.mysql.jdbc.Driver");
	   Connection cn=DriverManager.getConnection("jdbc:mysql://sociogram.c9rgg7nk0bfq.us-east-2.rds.amazonaws.com:3306/bookstore","admin","Nehal!076");
	   PreparedStatement pst=cn.prepareStatement("update books set qoh=? where bookid=?");
	  
	   pst.setInt(1, bd.qoh);
	   pst.setInt(2, bd.bookid);
	   pst.executeUpdate(); 
	  }
	  catch(ClassNotFoundException e)
	  {
	   System.err.println("\ninit - "+e.getMessage()+"\n");
	  }
	  catch(SQLException e)
	  {
	   System.err.println("\nsql alert - destroy - "+e.getMessage()+"\n");
	  }
	 }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	 int cnt=0;
         HttpSession ssn=request.getSession(false);
		 
	 if(ssn!=null)
	 {
	  Enumeration et=ssn.getAttributeNames();
	  while(et.hasMoreElements())
	  {
	   et.nextElement();
	   cnt++;
	  }
	 }
		  
	 String html="<html><body bgcolor=teal>"
			    +"<div align=right><font color=orange>"
			    +"<a href='kart'><img src=\"cart.jpg\" height=70 width=70 ></a>("+cnt+")</div></font>"				    +"<center>"
	   		    +"<font face=\"lucida console\" size=6 color=yellow>"
			    +"<br><br><br>Welcome to Bookstore!<br><br></font>"; 
	 
	 Enumeration et=books.keys();
	 while(et.hasMoreElements())
	 {
      int bookid=(Integer)et.nextElement();
	  BookDetail bd=books.get(bookid);
	  //System.out.println(bookid+" "+bd.title+" "+bd.author+" "+bd.price+" "+bd.discount+" "+bd.qoh);
	 
	  html=html+"<div style=\"float:left;border:solid 2px teal;margin:10px;padding:30px;\">"
	 	       +"<font face=\"arial\" size=2 color=black>"
		       +"<a href='detail?bookid="+bookid+"'>"
		       +"<img src='"+bookid+".jpg' height=250 width=190>&nbsp;</a>"
		       +"<br><b>"+bd.title+"</b>"
		       +"<br>Rs."+bd.price
		       +"</div>"; 
	 }
	 
	 html=html+"</font></center></body></html>";
	   
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
