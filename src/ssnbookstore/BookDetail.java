package ssnbookstore;

public class BookDetail 
{
	 int bookid,price,discount,qoh;
	 String title,author;
	 
	 BookDetail(int bookid,String title,String author,int price,int discount,int qoh)
	 {
	  this.bookid=bookid;
	  this.title=title;
	  this.author=author;
	  this.price=price;
	  this.discount=discount;
	  this.qoh=qoh; 
	 }
}
