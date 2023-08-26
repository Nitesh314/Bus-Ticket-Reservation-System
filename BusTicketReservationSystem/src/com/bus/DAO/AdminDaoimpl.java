package com.bus.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bus.Beans.Bus;
import com.bus.Beans.CusBusDTO;
import com.bus.Colours.Colours;
import com.bus.Exception.AdminException;
import com.bus.Exception.BookingException;
import com.bus.Utility.DBUtil;

public class AdminDaoimpl implements AdminDao {
	
	Colours c=new Colours();

	@Override
	public String loginAdmin(String userName, String passWord) {
		
		
		
		String message="Invalid UserName and PassWord";
		
		if(userName.equalsIgnoreCase(AdminDao.userName) && passWord.equalsIgnoreCase(AdminDao.passWord)) {
			
			message="Login Successfull...";
			
			
		}
		
		return message;
		
	}

//***********************************************************************************************	
	
	@Override
	public String addBus(Bus bus) {
		String message=c.WHITE+c.BackgroundRed+"Bus Not Added..."+c.Reset;
		
		try(Connection conn=DBUtil.provideConnection()) {
			
		PreparedStatement ps=conn.prepareStatement("insert into bus values(?,?,?,?,?,?,?,?,?,?)");
		
		//binding the values;
		
		ps.setInt(1,bus.getBusNo());
		ps.setString(2,bus.getbName());
		ps.setString(3,bus.getRouteFrom());
		ps.setString(4,bus.getRouteTo());
		ps.setString(5,bus.getBusType());
		ps.setString(6,bus.getDeparture());
		ps.setString(7,bus.getArrival() );
		ps.setInt(8,bus.getTotalSeats());
		ps.setInt(9,bus.getAvaiSeates());
		ps.setInt(10,bus.getFare());
		
	    int x=ps.executeUpdate();
	    
	    if(x>0) {
	    	message=c.WHITE+c.BackgroundGreen+"Bus Added Successfully..."+c.Reset;
	    }
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return message;
	}
	
//**************************************************************************	

	@Override
	public String updateStatus(int cusId){
		
		String message=c.WHITE+c.BackgroundRed+"Status not updated from customer id : "+cusId+c.Reset;
		
		try(Connection conn=DBUtil.provideConnection()) {
			
		PreparedStatement ps=conn.prepareStatement("update  booking set status=true where cusId=? ");
		ps.setInt(1, cusId);
		
		int x=ps.executeUpdate();
		
		if(x>0) {
			message=c.WHITE+c.BackgroundGreen+"Status successfully updated from customer id : "+cusId+c.Reset;
		}
			
		} catch (SQLException e) {
			message=e.getMessage();
		}
		
		
		return message;

	}
	
//*******************************************************************************************************	

	@Override
	public List<CusBusDTO> viewAllTickets() throws BookingException {
	
		
		List<CusBusDTO> bookTicket=new ArrayList<>();
		
		try(Connection conn=DBUtil.provideConnection()) {
			
		PreparedStatement ps=conn.prepareStatement("Select * from booking");
		
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			
			int bid=rs.getInt("bId");
			int cusId=rs.getInt("cusId");
			int busNo=rs.getInt("busNo");
			int seatFrom=rs.getInt("seatFrom");
			int seatTo=rs.getInt("seatTo");
			int status=rs.getInt("status");
			
			CusBusDTO csb=new CusBusDTO();
			csb.setbId(bid);
			csb.setCusID(cusId);
			csb.setBusNo(busNo);
			csb.setSeatFrom(seatFrom);
			csb.setSeatTo(seatTo);
			csb.setStatus(status);
			
			bookTicket.add(csb);
			
		}
		
		if(bookTicket.isEmpty()) {
			throw new BookingException(c.WHITE+c.BackgroundRed+"Non of the ticket booked by user..."+c.Reset);
		}
		
			
		} catch (SQLException e) {
			throw new BookingException(c.WHITE+c.BackgroundRed+"Invalid Data..."+c.Reset);
		}
		
		
		
		return bookTicket;
	}

	

}
