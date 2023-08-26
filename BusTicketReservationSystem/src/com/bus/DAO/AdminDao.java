package com.bus.DAO;

import java.util.List;

import com.bus.Beans.Bus;
import com.bus.Beans.CusBusDTO;
import com.bus.Exception.AdminException;
import com.bus.Exception.BookingException;

public interface AdminDao {
	
	public final String userName="Admin";
	
	public final String passWord="1234";
	
	public String loginAdmin(String userName,String passWord);
	
	public String addBus(Bus bus);
	
	public String updateStatus(int cusId);
	
	public List<CusBusDTO> viewAllTickets()throws BookingException;
	

}
