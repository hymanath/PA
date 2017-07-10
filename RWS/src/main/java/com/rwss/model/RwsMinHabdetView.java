package com.rwss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aprwssuser.rws_min_habdet_view")
public class RwsMinHabdetView {
   
	 private String habitationName;
	 private String habitationCode;
	
	 @Column(name="HABITATION_NAME")
	 public String getHabitationName() {
		return habitationName;
	 }
	 public void setHabitationName(String habitationName) {
		this.habitationName = habitationName;
	 }
	 @Id
	 @Column(name="HABIATTION_CODE")
	 public String getHabitationCode() {
		return habitationCode;
	 }
	 public void setHabitationCode(String habitationCode) {
		this.habitationCode = habitationCode;
	 }
	 
}
