package com.bus.Beans;

import java.util.Objects;

public class CusBusDTO {
	
	private int bId;//primary
	private int cusID;//customer table primary here foreign
	private int busNo;//bus table primary here foreign 
	private  int seatFrom;
	private  int seatTo;
	private int status;//check 
	
	public CusBusDTO() {
		// TODO Auto-generated constructor stub
	}

	public CusBusDTO(int bId, int cusID, int busNo, int seatFrom, int seatTo, int status) {
		super();
		this.bId = bId;
		this.cusID = cusID;
		this.busNo = busNo;
		this.seatFrom = seatFrom;
		this.seatTo = seatTo;
		this.status = status;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

	public int getBusNo() {
		return busNo;
	}

	public void setBusNo(int busNo) {
		this.busNo = busNo;
	}

	public int getSeatFrom() {
		return seatFrom;
	}

	public void setSeatFrom(int seatFrom) {
		this.seatFrom = seatFrom;
	}

	public int getSeatTo() {
		return seatTo;
	}

	public void setSeatTo(int seatTo) {
		this.seatTo = seatTo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CusBusDTO [bId=" + bId + ", cusID=" + cusID + ", busNo=" + busNo + ", seatFrom=" + seatFrom
				+ ", seatTo=" + seatTo + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bId, busNo, cusID, seatFrom, seatTo, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CusBusDTO other = (CusBusDTO) obj;
		return bId == other.bId && busNo == other.busNo && cusID == other.cusID && seatFrom == other.seatFrom
				&& seatTo == other.seatTo && status == other.status;
	}
	
	

}
