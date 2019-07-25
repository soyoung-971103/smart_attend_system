package model;

import java.sql.Date;

public class HolidayDTO {
	int id;
	int yyyy;
	Date holiday;
	String reason;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYyyy() {
		return yyyy;
	}
	public void setYyyy(int yyyy) {
		this.yyyy = yyyy;
	}
	public Date getHoliday() {
		return holiday;
	}
	public void setHoliday(Date holiday) {
		this.holiday = holiday;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
