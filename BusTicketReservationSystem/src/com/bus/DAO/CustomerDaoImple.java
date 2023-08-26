package com.bus.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.undo.CannotUndoException;

import com.bus.Beans.Bus;
import com.bus.Beans.CusBusDTO;
import com.bus.Beans.Customer;
import com.bus.Colours.Colours;
import com.bus.Exception.BookingException;
import com.bus.Exception.BusException;
import com.bus.Exception.CustomerException;
import com.bus.Utility.DBUtil;
import com.bus.Utility.DateUtil;


public class CustomerDaoImple implements CustomerDao  {
	
	
	Colours c=new Colours();

	@Override
	public String SingUpCustomer(Customer cu) {
        String message=c.BackgroundRed+"Invalid Data please singUp Again with proper data"+c.Reset;
        
        try(Connection conn=DBUtil.provideConnection()) {
        	
        PreparedStatement ps=conn.prepareStatement("insert into customer(userName,password,firstName,lastName,address,mobile) values(?,?,?,?,?,?)");
        
        //binding values;
        
        ps.setString(1,cu.getUserName());
        ps.setString(2,cu.getPassWord());
        ps.setString(3,cu.getFirstName());
        ps.setString(4,cu.getLastName());
        ps.setString(5,cu.getAddress());
        ps.setString(6,cu.getMobile());
        
        int x=ps.executeUpdate();
        
        if(x>0) {
        	message=c.GREEN_BACKGROUND_BRIGHT+"SingUp Successfull"+c.Reset;
        			
        }
        
		} catch (SQLException e) {
			System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
		}
        
        
        return message;
	}
	
//********************************************************************************
	
	
	
	@Override
	public Customer LoginCustomer(String username, String password)throws CustomerException {
		
		Customer cu=null;
		
		try (Connection conn=DBUtil.provideConnection()){
			
		PreparedStatement ps=conn.prepareStatement("select c.firstName,c.lastName from Customer c where c.username=? AND c.password=?");
         
		//binding value;
		
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()) {

			String Fname=rs.getString("firstName");
			String Lname=rs.getString("lastName");
			
			cu=new Customer();   
			
			cu.setFirstName(Fname);
			cu.setLastName(Lname);
	
		}
			
		} catch (SQLException e) {
			throw new CustomerException(c.WHITE + c.BackgroundRed + "An error occurred while processing the login request." + c.Reset);
		}
		
		
		return cu;
		
	}
//**********************************************************************************************************
	
	@Override
	public List<Bus> viewAllBusDetais() throws BusException {
		List<Bus> lBus=new ArrayList<>();
		
		
		try(Connection conn=DBUtil.provideConnection()) {
			
		PreparedStatement ps=conn.prepareStatement("select * from bus");
		
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
		  int busNo=rs.getInt("busNo");
		  String bName=rs.getString("bName");
		  String rf=rs.getString("routeFrom");
		  String rt=rs.getString("routeTo");
		  String busType= rs.getString("bType");
		  
		  Date dDate= rs.getDate("departure");
		  String dDateS=  DateUtil.formatDateToString(dDate);
		  
		  Date aDate=rs.getDate("arrival");
		  String aDateA= DateUtil.formatDateToString(aDate);
		  
		  int tSetas=rs.getInt("totalSeats");
		  int aSeates= rs.getInt("availSeats");
		  int fare= rs.getInt("fare");
		  
		  Bus bus=new Bus(busNo, bName, rf, rt, busType,dDateS,aDateA , tSetas, aSeates, fare);
		  
		  
		  lBus.add(bus);
		  
		}
		if(lBus.isEmpty()) {
			throw new BusException(c.WHITE+c.RED_BACKGROUND_BRIGHT+"Sorry...bus services is off for some time...");
		}
			
			
			
		} catch (SQLException e) {
			throw new BusException(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
		}
		
		
		
		
		
		
		return lBus;
	}

//*****************************************************************************************************	

	@Override
	public String updatePassword(String userName,String password) {
		String msg=c.WHITE+c.BackgroundRed+"Password not updated Enter the Correct user name "+c.Reset;
		
		try(Connection conn=DBUtil.provideConnection()){
			
		PreparedStatement ps=conn.prepareStatement("Update customer set password=? where username=?");
		
		ps.setString(1,password);
		ps.setString(2,userName);
		
		int x=ps.executeUpdate();
		
		if(x>0) {
			msg=c.BLACK+c.BackgroundGreen+"Password updated Successfully..."+c.Reset;
		}
			
		}catch(SQLException e) {
			msg=c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset;
		}
		
		
		return msg;
	}
//*************************************************************************************
	
	@Override
	public List<Bus> checkAval(String routeFrom, String routeTo) throws BusException {
	    List<Bus> bList=new ArrayList<>();
	    
	    try(Connection conn=DBUtil.provideConnection()) {
	    	
	    PreparedStatement ps=conn.prepareStatement("select * from bus where routeFrom=? AND routeTo=?");
	    
	    ps.setString(1, routeFrom);
		ps.setString(2, routeTo);
	
		
	     ResultSet rs=ps.executeQuery();
	     
	     while(rs.next()) {
	    	 
	    	  int busNo=rs.getInt("busNo");
			  String bName=rs.getString("bName");
			  String rf=rs.getString("routeFrom");
			  String rt=rs.getString("routeTo");
			  String busType= rs.getString("bType");	
			  String dDate= rs.getString("departure");
			  String aDate=rs.getString("arrival");
			  int tSetas=rs.getInt("totalSeats");
			  int aSeates= rs.getInt("availSeats");
			  int fare= rs.getInt("fare");
			  
			  Bus bus=new Bus(busNo, bName, routeFrom, routeTo, busType, rt, aDate, tSetas, aSeates, fare);
			  
			  bList.add(bus);
	    	 
	     }
		  if(bList.isEmpty()) {
			  throw new BusException(c.WHITE+c.BackgroundRed+"Sorry, we dont have any bus for this route...!!"+c.Reset);
		  }
		
		} catch (SQLException e) {
			throw new BusException(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
		}
	    
		return bList;
	}
//************************************************************************************************************
	
	
	@Override
	public String  bookTicket(int busNo,int cusId,int nOfSeteas) throws BookingException,BusException,CustomerException {
		String msg=c.WHITE+c.BackgroundRed+"Ticket not booked..."+c.Reset;
		
		try(Connection conn=DBUtil.provideConnection()) {
		
			
		  PreparedStatement ps=conn.prepareStatement("select * from customer where cusId=?");
		  
		  //binding values;
		  
		   ps.setInt(1,cusId);
		  
		   ResultSet rs= ps.executeQuery();
		 
		  if(rs.next()) {
			 
			 PreparedStatement ps1=conn.prepareStatement("select * from bus where busNo=?");
				
				//binding value;
				
				ps1.setInt(1, busNo);
				
			    ResultSet rs1=ps1.executeQuery();
			    
			    if(rs1.next()) {
			    	
			    	int buNo = rs1.getInt("busNo");
					int totalSeats = rs1.getInt("totalSeats");
					int availSeats = rs1.getInt("availSeats");
					Date departure = rs1.getDate("departure");
					int fare = rs1.getInt("fare");
					
					 PreparedStatement ps2=conn.prepareStatement("select DATEDIFF(?,('2020-02-12')) AS date");
				    	
					   //binding values;
					   
					   ps2.setDate(1, departure);
					   
					   ResultSet rs2= ps2.executeQuery();
					   
					   int days=0;
					   
					   if(rs2.next()) {
						   
						  days+=rs2.getInt("date");
					   }
					   
					   if(days<=0) {
						   throw new BusException(c.WHITE+c.BackgroundRed+"Booking is not available for this date..."+c.Reset);
					   }
					   
					   else if(availSeats >= nOfSeteas) {
						   
						   int seatFrom=totalSeats-availSeats+1;
						   
						   int seatTo=seatFrom+nOfSeteas-1;
						   
						   int fares =fare*nOfSeteas;
						   
						  PreparedStatement ps3= conn.prepareStatement("insert into booking(cusId,busNo,seatFrom,seatTo) values(?,?,?,?)");
						  
						  ps3.setInt(1,cusId);
						  ps3.setInt(2, busNo);
						  ps3.setInt(3, seatFrom);
						  ps3.setInt(4,seatTo);
						  
						  int x=ps3.executeUpdate();

						  if(x>0) {
							  
							 PreparedStatement ps4=conn.prepareStatement("update bus set availSeats=? where busNo=?");
							  
							 availSeats=availSeats-nOfSeteas;
							 
							 ps4.setInt(1,availSeats);
							 ps4.setInt(2,buNo);
							 
							 int y=ps4.executeUpdate();
							 
							 if(y<=0)throw new BusException(c.WHITE+c.BackgroundRed+"Available sets are not updated..."+c.Reset);
							 
							 
							 System.out.println(c.TEAL+ "--------------------------------------------" + "\n"
									   + c.RED_BOLD+"Customer Id is : "+c.GREEN_BOLD+ cusId + "\n"
									   + c.RED_BOLD+"Bus No is : " +c.GREEN_BOLD+ busNo + "\n"
									   + c.RED_BOLD+"Seat No is from : " +c.GREEN_BOLD+ seatFrom +c.RED_BOLD+" to "+c.GREEN_BOLD+ seatTo + "\n"
									   + c.RED_BOLD+"Bus fare is : "+c.GREEN_BOLD + fares + "\n"
									   + c.RED_BOLD+"Booking yet to be confirm by Adminstrator" + "\n" 
									   +c.TEAL+ "---------------------------------------------" + c.Reset);
							  
							  
							 msg=c.BLACK+c.BackgroundGreen+"Ticket booked Successfully..."+c.Reset;
							  
							  
						  }
						  
						  
					   }else {
						   throw new BookingException(c.WHITE+c.BackgroundRed+"Enter the proper details..."+c.Reset);
					   }
			 
		       }
			    else {
			    	throw new BusException(c.WHITE+c.BackgroundRed+"Enter the valid Bus Number..."+c.Reset);
			    }
			 
	    }
		  else {
			  throw new CustomerException(c.WHITE+c.BackgroundRed+"Enter the valid Customer ID..."+c.Reset);
		  }
	    
		} catch (SQLException e) {
			throw new BusException(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
		}
		
		return msg;
	}
//**********************************************************************************************************
	
	
	@Override
	public String calcleTicket(int busNo,int cusId) throws BookingException,BusException {
		String msg=c.WHITE+c.BackgroundRed+"Ticket cancellation failed"+c.Reset;
		
		try(Connection conn=DBUtil.provideConnection()) {
			
		PreparedStatement ps=conn.prepareStatement("Select * from bus where busNo=?");
		
		ps.setInt(1, busNo);
		
	    ResultSet rs=ps.executeQuery();
	    
	    if(rs.next()) {
	    	int availSeats = rs.getInt("availSeats");
	    	
	    	PreparedStatement ps1=conn.prepareStatement("select * from booking where busNo=? AND cusId=?");
	    	
	    	ps1.setInt(1,busNo);
	    	ps1.setInt(2,cusId);
	    	
	    	ResultSet rs1=ps1.executeQuery();
	    	
	    	boolean flag=false;
	    	int count=0;
	    	
	    	while(rs1.next()) {
	    		
	    		flag=true;
	    		
	    		int seatFrom=rs1.getInt("seatFrom");
	    		int seatTo=rs1.getInt("seatTo");
	    		
	    		count+=seatTo-seatFrom+1;
	    	}
	    	 if (flag) {
					
					PreparedStatement ps2 = conn.prepareStatement("delete from booking where busNo = ? and cusId = ?");
					ps2.setInt(1, busNo);
					ps2.setInt(2, cusId);
					
					int x = ps2.executeUpdate();
					if (x > 0) {
						
						PreparedStatement ps3 = conn.prepareStatement("update bus set availseats = ? where busNo = ?");
						availSeats = availSeats + count;
						ps3.setInt(1, availSeats);
						ps3.setInt(2, busNo);
						
						
						int y = ps3.executeUpdate();
						
						if(y<=0) {
							throw new BusException("Available Seat is not updated");
						}
						
						msg=c.BLACK+c.BackgroundGreen+"Ticket cancelled Successfully"+c.Reset;
						
					}
				
				}
	    	 else msg = c.WHITE+c.BackgroundRed+"No booking found"+c.Reset;
	    	
	    }
	    else {
	    	throw new BusException(c.WHITE+c.BackgroundRed+"Bus Number "+c.GREEN_BOLD+c.BackgroundRed+ busNo +c.WHITE+c.BackgroundRed+" is not availabl"+c.Reset);
	    }
			
			
		} catch (SQLException e) {
			throw new BookingException(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
		}
		
		return msg;
	}
	
//******************************************************************************************************************
	

	@Override
	public void statusOfTicket(int cusId) {
		
        boolean flag = false;
		
		try(Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps1 = conn.prepareStatement("select * from booking where cusId = ?");
			ps1.setInt(1, cusId);
			
			ResultSet rs1 = ps1.executeQuery();
			
			while (rs1.next()) {
				flag = true;
				System.out.println(c.ROSY_PINK + "---------------------------------------------" + c.Reset);
				System.out.println(c.ROSY_PINK + "Bus Id : " + rs1.getInt("bId") + c.Reset);
				System.out.println(c.ROSY_PINK + "Bus No : " + rs1.getInt("busNo") + c.Reset);
				System.out.println(c.ROSY_PINK + "Total tickets : " + (rs1.getByte("seatTo") - rs1.getInt("seatFrom") + 1) + c.Reset);
				if (rs1.getBoolean("status")) System.out.println(c.ROSY_PINK + "Status : Booked"  + c.Reset);
				else System.out.println(c.ROSY_PINK + "Status : Pending" + c.Reset);
				
				System.out.println(c.ROSY_PINK + "----------------------------------------------" + c.Reset);
			}
			
			if (flag == false) System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + "No tickets found" + c.Reset);
		}
		catch (SQLException s){
			System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + s.getMessage() + c.Reset);
		}
		
	}
//**********************************************************************************************************************
	@Override
	public void getCustomerID(String userName, String passWord) throws CustomerException {
	
		
		try(Connection conn=DBUtil.provideConnection()) {
			
			
			
		PreparedStatement ps=conn.prepareStatement("Select cusId,firstName from customer where userName=? AND passWord=?");
		
		ps.setString(1, userName);
		ps.setString(2, passWord);
		
	    ResultSet rs=ps.executeQuery();
	    
	    if(rs.next()) {
	    	
	    	int custID=rs.getInt("cusId");
	    	String name=rs.getString("firstName");
	    	System.out.println(c.BLACK_BOLD+c.GREEN_BACKGROUND+name+" your customer ID  is "+custID+" "+c.Reset);	    	
	    }else {
	    	
	    	throw new CustomerException(c.WHITE_BOLD+c.RED_BACKGROUND+"Enter the Valid userName and passWord"+c.Reset);
	    }
			
		} catch (SQLException e) {
			System.out.println(c.WHITE_BOLD+c.RED_BACKGROUND + e.getMessage() + c.Reset);
		}
		
		
	}
//**************************************************************************************************

			
	
		
		
}	
	
	

