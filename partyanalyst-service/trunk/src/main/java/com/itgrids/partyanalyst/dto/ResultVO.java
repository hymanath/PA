package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ResultVO  implements Serializable{
  String panchayat ="";
  String pVotes2009 ="";
  String pVotes2004 ="";
  String tdp9Votes ="";
  String prp9Votes ="";
  String tdp4Votes ="";
  String tdp9perc ="";
  String tdp4perc ="";
  String prp9perc ="";
  String constituency="";
public String getPanchayat() {
	return panchayat;
}
public void setPanchayat(String panchayat) {
	this.panchayat = panchayat;
}
public String getpVotes2009() {
	return pVotes2009;
}
public void setpVotes2009(String pVotes2009) {
	this.pVotes2009 = pVotes2009;
}
public String getpVotes2004() {
	return pVotes2004;
}
public void setpVotes2004(String pVotes2004) {
	this.pVotes2004 = pVotes2004;
}

public String getTdp9perc() {
	return tdp9perc;
}
public void setTdp9perc(String tdp9perc) {
	this.tdp9perc = tdp9perc;
}
public String getTdp4perc() {
	return tdp4perc;
}
public void setTdp4perc(String tdp4perc) {
	this.tdp4perc = tdp4perc;
}
public String getPrp9perc() {
	return prp9perc;
}
public void setPrp9perc(String prp9perc) {
	this.prp9perc = prp9perc;
}
public String getTdp9Votes() {
	return tdp9Votes;
}
public void setTdp9Votes(String tdp9Votes) {
	this.tdp9Votes = tdp9Votes;
}
public String getPrp9Votes() {
	return prp9Votes;
}
public void setPrp9Votes(String prp9Votes) {
	this.prp9Votes = prp9Votes;
}
public String getTdp4Votes() {
	return tdp4Votes;
}
public void setTdp4Votes(String tdp4Votes) {
	this.tdp4Votes = tdp4Votes;
}
public String getConstituency() {
	return constituency;
}
public void setConstituency(String constituency) {
	this.constituency = constituency;
}
  
  
}
