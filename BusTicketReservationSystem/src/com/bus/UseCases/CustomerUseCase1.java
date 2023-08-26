package com.bus.UseCases;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.bus.Beans.Bus;
import com.bus.Beans.CusBusDTO;
import com.bus.Beans.Customer;
import com.bus.Colours.Colours;
import com.bus.DAO.CustomerDao;
import com.bus.DAO.CustomerDaoImple;
import com.bus.Exception.BookingException;
import com.bus.Exception.BusException;
import com.bus.Exception.CustomerException;


public class CustomerUseCase1 {
	
	private static Scanner sc=new Scanner(System.in);
	
	private static CustomerDao cDao=new CustomerDaoImple();
	
	private static Colours c=new Colours();
	
	
	
    public static boolean SingUpCustomer() {
    	
    	boolean flag=false;
    	
    	try {
			flag=true;
    	System.out.print(c.RED_BOLD+"Enter the user-name : "+c.GREEN_BOLD);
		String userName=sc.next();
		
		System.out.print(c.RED_BOLD+"Enter the Password : "+c.GREEN_BOLD);
		String password=sc.next();
		
		System.out.print(c.RED_BOLD+"Enter the First Name : "+c.GREEN_BOLD);
		String fName =sc.next();
		
		System.out.print(c.RED_BOLD+"Enter the Last Name : "+c.GREEN_BOLD);
		String lName=sc.next();
		
		System.out.print(c.RED_BOLD+"Enter the Address : "+c.GREEN_BOLD);
		String address=sc.next();
		
		System.out.print(c.RED_BOLD+"Enter the Mobile No. : "+c.GREEN_BOLD);
		String mobile=sc.next();
		
		System.out.println(c.Reset);
		
		Customer customer=new Customer(userName, password, fName, lName, address, mobile);
		
		String msg=cDao.SingUpCustomer(customer);
		
		
		
    }catch (InputMismatchException e) {
    	 System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
	}
	
	return flag;
		
    	
    }
  
//***************************************************************************************    
	
	public static Customer LoginCustomer() {
		
		Customer cu=null;
		
		try {
		
		System.out.print(c.RED_BOLD+"Enter the user-name : "+c.GREEN_BOLD);
		String userName=sc.next();
		
		System.out.print(c.RED_BOLD+"Enter the Password : "+c.GREEN_BOLD);
		String password=sc.next();
		
		System.out.println(c.Reset);
		
		try {
			 
			 cu=new Customer();
			
			 cu= cDao.LoginCustomer(userName, password);
			
		} catch (CustomerException e) {
			System.out.println(e.getMessage());
		}
		
	}	
	catch (InputMismatchException e) {
		 System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
	}
		
		return cu;
		
	}
//***************************************************************************************	
    public static void viewAllBusDetais(){
    	
      try {
		List<Bus>	lBus=cDao.viewAllBusDetais();
		
		lBus.forEach(b->System.out.println(c.TEAL+"+---------------------------------------------------------+\n"
									                  + c.RED_BOLD+" Bus Number : "+c.GREEN_BOLD+b.getBusNo()+"\n"
									                  + c.RED_BOLD+" Bus Naem : "+c.GREEN_BOLD+b.getbName()+"\n"
									            	  + c.RED_BOLD+" Start Route from : "+c.GREEN_BOLD+b.getRouteFrom()+"\n"
									            	  + c.RED_BOLD+" Drop route to : "+c.GREEN_BOLD+b.getRouteTo()+"\n"
									            	  + c.RED_BOLD+" Bus Type : "+c.GREEN_BOLD+b.getBusType()+"\n"
									            	  + c.RED_BOLD+" Bus Departure Date time : "+c.GREEN_BOLD+b.getDeparture()+"\n"
									            	  + c.RED_BOLD+" Bus Arrival Date time : "+c.GREEN_BOLD+b.getArrival()+"\n"
									            	  + c.RED_BOLD+" Total capcity of bus : "+c.GREEN_BOLD+b.getTotalSeats()+"\n"
									            	  + c.RED_BOLD+" Available Seats : "+c.GREEN_BOLD+b.getAvaiSeates()+"\n"
									            	  + c.RED_BOLD+" Bus Fare : "+c.GREEN_BOLD+b.getFare()+"\n"
									                  + c.TEAL+"+---------------------------------------------------------+"+c.Reset+"\n"));
		
		
	} catch (BusException e) {
		System.out.println(e.getMessage());
	}
    	
    }
//***************************************************************************************  
    public static void updatePassword() {
    	
    	try {
    		
    	
    	System.out.print(c.RED_BOLD+"Enter the user-name : "+c.GREEN_BOLD);
		String userName=sc.next();
		
		System.out.print(c.RED_BOLD+"Enter the New Password : "+c.GREEN_BOLD);
		String password=sc.next();
		
		System.out.println(c.Reset);
		
		String msg=cDao.updatePassword(userName, password);
		
		System.out.println(msg);
      }catch (InputMismatchException e) {
    	  System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
	}
  }
//***************************************************************************************    
    public static void checkAval(){
    	
    	try {
    	
    	System.out.print(c.RED_BOLD+"Enter the Pick-up point : "+c.GREEN_BOLD);
		String rFrom=sc.next();
		
		System.out.print(c.RED_BOLD+"Enter the drop point : "+c.GREEN_BOLD);
		String rTo=sc.next();
		
		System.out.println(c.Reset);
		
		try {
		    List<Bus> lBus=cDao.checkAval(rFrom, rTo);
		    
		    lBus.forEach(b->System.out.println(c.TEAL+"+---------------------------------------------------------+\n"
	                  + c.RED_BOLD+" Bus Number : "+c.GREEN_BOLD+b.getBusNo()+"\n"
	                  + c.RED_BOLD+" Bus Naem : "+c.GREEN_BOLD+b.getbName()+"\n"
	            	  + c.RED_BOLD+" Start Route from : "+c.GREEN_BOLD+b.getRouteFrom()+"\n"
	            	  + c.RED_BOLD+" Drop route to : "+c.GREEN_BOLD+b.getRouteTo()+"\n"
	            	  + c.RED_BOLD+" Bus Type : "+c.GREEN_BOLD+b.getBusType()+"\n"
	            	  + c.RED_BOLD+" Bus Departure Date time : "+c.GREEN_BOLD+b.getDeparture()+"\n"
	            	  + c.RED_BOLD+" Bus Arrival Date time : "+c.GREEN_BOLD+b.getArrival()+"\n"
	            	  + c.RED_BOLD+" Total capcity of bus : "+c.GREEN_BOLD+b.getTotalSeats()+"\n"
	            	  + c.RED_BOLD+" Available Seats : "+c.GREEN_BOLD+b.getAvaiSeates()+"\n"
	            	  + c.RED_BOLD+" Bus Fare : "+c.GREEN_BOLD+b.getFare()+"\n"
	                  + c.TEAL+"+---------------------------------------------------------+"+c.Reset+"\n"));
		    
		    
		    
		} catch (BusException e) {
			System.out.println(e.getMessage());
		}
    }catch(InputMismatchException e) {
    	 System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
    }
    	
   }
//***************************************************************************************    
    public static void bookTicket() {
    	
    	try {
    		
    	System.err.print(c.RED_BOLD+"Enter the Bus Number : "+c.GREEN_BOLD);
    	int busNo=sc.nextInt();
    	
    	System.err.print(c.RED_BOLD+"Enter the customer Id  : "+c.GREEN_BOLD);
    	int cusId=sc.nextInt();
    	
    	System.err.print(c.RED_BOLD+"Enter the Number of seates : "+c.GREEN_BOLD);
    	int nOfSeats=sc.nextInt();
    	
    	System.out.println(c.Reset);
    	
    	try {
			String msg=cDao.bookTicket(busNo, cusId, nOfSeats);
			
			System.out.println(msg);
			
		} catch (BookingException e) {
			System.out.println(e.getMessage());
		} catch (BusException e) {
			System.out.println(e.getMessage());
		} catch (CustomerException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
      }catch (InputMismatchException e) {
    	  System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
	} 	
    	
    }
//***************************************************************************************    
    public static void calcleTicket() {
    	
    	try {
    		
    	
    	System.err.print(c.RED_BOLD+"Enter the Bus Number : "+c.GREEN_BOLD);
    	int busNo=sc.nextInt();
    	
    	System.err.print(c.RED_BOLD+"Enter the customer Id  : "+c.GREEN_BOLD);
    	int cusId=sc.nextInt();
    	
    	System.out.println(c.Reset);
    	
    	try {
			String msg=cDao.calcleTicket(busNo, cusId);
			
			System.out.println(msg);
			
		} catch (BookingException e) {
			System.out.println(e.getMessage());
		} catch (BusException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
      }catch (InputMismatchException e) {
    	  System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
	}	
    	
    }
	
//**********************************************************************************************
   
    public static void statusOfTicket() {
    	
    	try {
    		
    	System.out.print(c.RED_BOLD+"Enter the Customer ID : "+c.GREEN_BOLD);
		int cusID=sc.nextInt();
		
		cDao.statusOfTicket(cusID);
		
    	}catch (InputMismatchException e) {
    		System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
		}
    	
    	
    }
    
    
//************************************************************************************************
    
    public static void getCustomerID() {
    	
    	try {
    		
    		System.out.print(c.RED_BOLD+"Enter the user-name : "+c.GREEN_BOLD);
    		String userName=sc.next();
    		
    		System.out.print(c.RED_BOLD+"Enter the Password : "+c.GREEN_BOLD);
    		String password=sc.next();
    		
    		System.out.println(c.Reset);
    		
    		try {
    			
    			cDao.getCustomerID(userName, password);
    			
    		} catch (CustomerException e) {
    			System.out.println(e.getMessage());
    		}
    		
    	}	
    	catch (InputMismatchException e) {
    		 System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
    	}
    		
    	
    }
	
    
    
    
}
