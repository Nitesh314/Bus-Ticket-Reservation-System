package com.bus.Beans;

import java.time.LocalDateTime;
import java.util.Objects;

public class Bus {
	
    private int busNo;
    private String bName;
    private String routeFrom;
    private String routeTo;
    private String busType;
    private String departure;
    private String arrival;
    private int totalSeats;
    private int avaiSeates;
    private int fare;
    
    
    public Bus() {
		// TODO Auto-generated constructor stub
	}


	public Bus(int busNo, String bName, String routeFrom, String routeTo, String busType, String departure,
			String arrival, int totalSeats, int avaiSeates, int fare) {
		super();
		this.busNo = busNo;
		this.bName = bName;
		this.routeFrom = routeFrom;
		this.routeTo = routeTo;
		this.busType = busType;
		this.departure = departure;
		this.arrival = arrival;
		this.totalSeats = totalSeats;
		this.avaiSeates = avaiSeates;
		this.fare = fare;
	}


	public int getBusNo() {
		return busNo;
	}


	public void setBusNo(int busNo) {
		this.busNo = busNo;
	}


	public String getbName() {
		return bName;
	}


	public void setbName(String bName) {
		this.bName = bName;
	}


	public String getRouteFrom() {
		return routeFrom;
	}


	public void setRouteFrom(String routeFrom) {
		this.routeFrom = routeFrom;
	}


	public String getRouteTo() {
		return routeTo;
	}


	public void setRouteTo(String routeTo) {
		this.routeTo = routeTo;
	}


	public String getBusType() {
		return busType;
	}


	public void setBusType(String busType) {
		this.busType = busType;
	}


	public String getDeparture() {
		return departure;
	}


	public void setDeparture(String departure) {
		this.departure = departure;
	}


	public String getArrival() {
		return arrival;
	}


	public void setArrival(String arrival) {
		this.arrival = arrival;
	}


	public int getTotalSeats() {
		return totalSeats;
	}


	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}


	public int getAvaiSeates() {
		return avaiSeates;
	}


	public void setAvaiSeates(int avaiSeates) {
		this.avaiSeates = avaiSeates;
	}


	public int getFare() {
		return fare;
	}


	public void setFare(int fare) {
		this.fare = fare;
	}


	@Override
	public String toString() {
		return "Bus [busNo=" + busNo + ", bName=" + bName + ", routeFrom=" + routeFrom + ", routeTo=" + routeTo
				+ ", busType=" + busType + ", departure=" + departure + ", arrival=" + arrival + ", totalSeats="
				+ totalSeats + ", avaiSeates=" + avaiSeates + ", fare=" + fare + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(arrival, avaiSeates, bName, busNo, busType, departure, fare, routeFrom, routeTo,
				totalSeats);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bus other = (Bus) obj;
		return Objects.equals(arrival, other.arrival) && avaiSeates == other.avaiSeates
				&& Objects.equals(bName, other.bName) && busNo == other.busNo && Objects.equals(busType, other.busType)
				&& Objects.equals(departure, other.departure) && fare == other.fare
				&& Objects.equals(routeFrom, other.routeFrom) && Objects.equals(routeTo, other.routeTo)
				&& totalSeats == other.totalSeats;
	}



    
	

}
