package com.itgrids.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "biometric_holiday")
public class BiometricHoliday {

	private Long biometricHolidayId;
	private String holidayName;
	private String year;
	private Date date;
	private String day;
	private String holidayType;
	private String isDeleted;
	
	@Id
	@Column(name="biometric_holiday_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getBiometricHolidayId() {
		return biometricHolidayId;
	}
	public void setBiometricHolidayId(Long biometricHolidayId) {
		this.biometricHolidayId = biometricHolidayId;
	}
	@Column(name = "holiday_name")
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	@Column(name = "year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name = "day")
	public String getDay() {
		return day;
	}
	
	public void setDay(String day) {
		this.day = day;
	}
	@Column(name = "holiday_type")
	public String getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
