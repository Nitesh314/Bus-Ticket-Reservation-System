package com.bus.UseCases;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.bus.Beans.Bus;
import com.bus.Beans.CusBusDTO;
import com.bus.Colours.Colours;
import com.bus.DAO.AdminDao;
import com.bus.DAO.AdminDaoimpl;
import com.bus.Exception.BookingException;

public class AdminUseCase1 {
	
	public static Colours c=new Colours();
	
	private static Scanner sc = new Scanner(System.in);
     
	
	private static AdminDao aDao=new AdminDaoimpl();
	
	//login method;
	
	public static  boolean LoginAdmin() {
		
		boolean flag=false;
		
		try {
			
			System.out.print(c.RED_BOLD+"Enter the Admin User-name : "+c.GREEN_BOLD);
			String userName=sc.next();
			
			System.out.print(c.RED_BOLD+"Enter the Admin Password :"+c.GREEN_BOLD);
			String password=sc.next();
			
			System.out.println(c.Reset);
			
		   
		    String msg=aDao.loginAdmin(userName, password);
		    
		   if(msg.equalsIgnoreCase("Login Successfull...")) {
			   flag=true;
			   
			   System.out.println(c.BLACK+c.BackgroundBrightGreen+msg+c.Reset);
			   
		   }
		   else {
			   flag=false;
	            System.out.println(c.WHITE+c.BackgroundBrightRed+msg+c.Reset);
	           
		   }
		} catch (InputMismatchException e) {
				 System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
			}
  
		return flag;
		
 }
	
	
	//addbus method
	
	public static void addBus() {
		
		try {
		
			System.out.print(c.RED_BOLD+"Enter the Bus Number : "+c.GREEN_BOLD);
			int busNO=sc.nextInt();
			
			System.out.print(c.RED_BOLD+"Enter the Bus Name : "+c.GREEN_BOLD);
			String bName=sc.next();
			
			System.out.print(c.RED_BOLD+"Enter the bus route from  : "+c.GREEN_BOLD);
			String rF =sc.next();
			
			System.out.print(c.RED_BOLD+"Enter the bus route to : "+c.GREEN_BOLD);
			String rT=sc.next();
			
			System.out.print(c.RED_BOLD+"Enter the bus Type Ac/n-Ac : "+c.GREEN_BOLD);
			String bt=sc.next();
			
			sc.nextLine();
			
			System.out.print(c.RED_BOLD+"Enter the bus departure date time yyyy-MM-dd HH:mm:ss : "+c.GREEN_BOLD);
			String dDate=sc.nextLine();
			
			System.out.print(c.RED_BOLD+"Enter the bus Arrival date time yyyy-MM-dd HH:mm:ss : "+c.GREEN_BOLD);
			String aDate=sc.nextLine();
			
			System.out.print(c.RED_BOLD+"Enter the capacity of bus belong to sets : "+c.GREEN_BOLD);
			int tSets=sc.nextInt();
			
			System.out.print(c.RED_BOLD+"Enter the available seats  : "+c.GREEN_BOLD);
			int aSets=sc.nextInt();
			
			System.out.print(c.RED_BOLD+"Enter the fare of bus : "+c.GREEN_BOLD);
			int fare=sc.nextInt();
			
			System.out.println(c.Reset);
			
			
			
			Bus bus=new Bus(busNO, bName, rF, rT, bt, dDate, aDate, tSets, aSets, fare);
			
		    String msg=aDao.addBus(bus);
		   
		    System.out.println(msg);

			
		} catch (InputMismatchException e) {
			 System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
		}
  
		
	}
	
//*****************************************************************************************
	
	public static void updateStatus() {
		
		try {
		
			System.out.print(c.RED_BOLD+"Enter the Custoemr id : "+c.GREEN_BOLD);
			int cusID=sc.nextInt();
			
			System.out.println(c.Reset);
			
			String msg=aDao.updateStatus(cusID);
			
			System.out.println(msg);

			
		} catch (InputMismatchException e) {
			 System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
		}
	
		
		
	}
	
//**************************************************************************************
	
	public static void viewAllTickets() {
		
		try {
			List<CusBusDTO> tList= aDao.viewAllTickets();
			
			tList.forEach(t->System.out.println(c.TEAL+"+----------------------------+\n"
						                                                      + c.RED_BOLD+"Bus Booking Id : "+c.GREEN_BOLD+t.getbId()+"\n"
				                                                              + c.RED_BOLD+"Customer Id : "+c.GREEN_BOLD+t.getCusID()+"\n"
				                                                          	  + c.RED_BOLD+"Bus Number : "+c.GREEN_BOLD+t.getBusNo()+"\n"
				                                                          	  + c.RED_BOLD+"Customer Seat Start from : "+c.GREEN_BOLD+t.getSeatFrom()+"\n"
				                                                          	  + c.RED_BOLD+"Customer Seat End From : "+c.GREEN_BOLD+t.getSeatTo()+"\n"
				                                                          	  + c.RED_BOLD+"Status OF Booking : "+c.GREEN_BOLD+t.getStatus()+"\n"
				                                                          	  + c.TEAL+"+-----------------------------+"+c.Reset+"\n"));
			
			
			
		} catch (BookingException e) {
		}
		
		
		
	}

}
