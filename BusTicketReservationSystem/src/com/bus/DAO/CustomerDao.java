package com.bus.DAO;

import java.util.List;

import com.bus.Beans.Bus;
import com.bus.Beans.CusBusDTO;
import com.bus.Beans.Customer;
import com.bus.Exception.BookingException;
import com.bus.Exception.BusException;
import com.bus.Exception.CustomerException;

public interface CustomerDao {
	
	public String SingUpCustomer(Customer cu);
	
	public Customer LoginCustomer(String userName,String passWord) throws CustomerException;
	
    public List<Bus> viewAllBusDetais()throws BusException;
    
    public String updatePassword(String userName,String password);
    
    public List<Bus> checkAval(String routeFrom,String routeTo)throws BusException;
    
    public String bookTicket(int busNo,int cusId,int nOfSeates)throws BookingException,BusException,CustomerException;
    
    public String calcleTicket(int busNo,int cusId)throws BookingException,BusException;
    
    public void statusOfTicket(int cusId);
    
    public void getCustomerID(String userName,String passWord)throws CustomerException;
	

}
