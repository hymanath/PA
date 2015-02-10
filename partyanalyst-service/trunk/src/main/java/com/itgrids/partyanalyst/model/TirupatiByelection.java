package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="tirupati_byelection")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class TirupatiByelection  extends  BaseModel implements Serializable{
	
 private Long tirupatiByelectionId;
 private String clusterName;
 private String divisionName;
 private Long boothId2015;
 private String partNo2015;
 private String boothLocation;
 private Long totalVoters2015;
 private Long partNo2014;
 private Long voterIn2015From2014;
 private Long totalVoters2014;
 private String pollingPercentage2014;
 private String polledVotes2014;
 private Long tdpVotes;
 private Long ysrcVotes;
 private Long incVotes;
 private Long othersVotes;
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tirupati_byelection_id",nullable=false)	
public Long getTirupatiByelectionId() {
	return tirupatiByelectionId;
}
public void setTirupatiByelectionId(Long tirupatiByelectionId) {
	this.tirupatiByelectionId = tirupatiByelectionId;
}
@Column(name="cluster_name")
public String getClusterName() {
	return clusterName;
}
public void setClusterName(String clusterName) {
	this.clusterName = clusterName;
}
@Column(name="division_name")
public String getDivisionName() {
	return divisionName;
}
public void setDivisionName(String divisionName) {
	this.divisionName = divisionName;
}
@Column(name="2015_boothid")
public Long getBoothId2015() {
	return boothId2015;
}
public void setBoothId2015(Long boothId2015) {
	this.boothId2015 = boothId2015;
}
@Column(name="2015_partno")
public String getPartNo2015() {
	return partNo2015;
}
public void setPartNo2015(String partNo2015) {
	this.partNo2015 = partNo2015;
}
@Column(name="booth_location")
public String getBoothLocation() {
	return boothLocation;
}
public void setBoothLocation(String boothLocation) {
	this.boothLocation = boothLocation;
}
@Column(name="2015_totalvoters")
public Long getTotalVoters2015() {
	return totalVoters2015;
}
public void setTotalVoters2015(Long totalVoters2015) {
	this.totalVoters2015 = totalVoters2015;
}
@Column(name="2014_partno")
public Long getPartNo2014() {
	return partNo2014;
}
public void setPartNo2014(Long partNo2014) {
	this.partNo2014 = partNo2014;
}
@Column(name="2014_voterin_2015")
public Long getVoterIn2015From2014() {
	return voterIn2015From2014;
}
public void setVoterIn2015From2014(Long voterIn2015From2014) {
	this.voterIn2015From2014 = voterIn2015From2014;
}
@Column(name="2014_totalvoters")
public Long getTotalVoters2014() {
	return totalVoters2014;
}
public void setTotalVoters2014(Long totalVoters2014) {
	this.totalVoters2014 = totalVoters2014;
}
@Column(name="2014_pollingpercentage")
public String getPollingPercentage2014() {
	return pollingPercentage2014;
}
public void setPollingPercentage2014(String pollingPercentage2014) {
	this.pollingPercentage2014 = pollingPercentage2014;
}
@Column(name="2014_polledvotes")
public String getPolledVotes2014() {
	return polledVotes2014;
}
public void setPolledVotes2014(String polledVotes2014) {
	this.polledVotes2014 = polledVotes2014;
}
@Column(name="tdp_votes")
public Long getTdpVotes() {
	return tdpVotes;
}
public void setTdpVotes(Long tdpVotes) {
	this.tdpVotes = tdpVotes;
}
@Column(name="ysrc_votes")
public Long getYsrcVotes() {
	return ysrcVotes;
}
public void setYsrcVotes(Long ysrcVotes) {
	this.ysrcVotes = ysrcVotes;
}
@Column(name="inc_votes")
public Long getIncVotes() {
	return incVotes;
}
public void setIncVotes(Long incVotes) {
	this.incVotes = incVotes;
}
@Column(name="others_votes")
public Long getOthersVotes() {
	return othersVotes;
}
public void setOthersVotes(Long othersVotes) {
	this.othersVotes = othersVotes;
}
 
 
 
 
}
