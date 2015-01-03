package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;




@Entity
@Table(name = "tdp_cadre_travel_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreTravelInfo {
	
	private Long tdpCadreTravelInfoId;	
	private Long custId;		
	private String membershipNo;			
	private Long	ticketsCount;	
	private Double totalAmount;					
	private Double	discountPerc;
	private Double	discountAmount;
	private Double	amountAfterDiscount;	
	private Date insertedTime;	
	private Date updatedTime;	
	private Date dateOfJourney;
	private Long tdpCadreTravelsId;
	private TdpCadreTravels tdpCadreTravels;
	private Long cancelledTicketsCount;
	private String isDeleted;
	private Long tdpCadreId;
	private TdpCadre tdpCadre;
	private Double ticketCost;
	
		
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_travel_info_id", unique = true, nullable = false)
	public Long getTdpCadreTravelInfoId() {
		return tdpCadreTravelInfoId;
	}
	public void setTdpCadreTravelInfoId(Long tdpCadreTravelInfoId) {
		this.tdpCadreTravelInfoId = tdpCadreTravelInfoId;
	}
	
	
	@Column(name="cust_id")
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	
	@Column(name="membership_no")
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	
	@Column(name="tickets_count")
	public Long getTicketsCount() {
		return ticketsCount;
	}
	public void setTicketsCount(Long ticketsCount) {
		this.ticketsCount = ticketsCount;
	}
	
	@Column(name="total_amount")
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	@Column(name="discount_perc")
	public Double getDiscountPerc() {
		return discountPerc;
	}
	public void setDiscountPerc(Double discountPerc) {
		this.discountPerc = discountPerc;
	}
	
	@Column(name="discount_amount")
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	
	@Column(name="amount_after_discount")
	public Double getAmountAfterDiscount() {
		return amountAfterDiscount;
	}
	public void setAmountAfterDiscount(Double amountAfterDiscount) {
		this.amountAfterDiscount = amountAfterDiscount;
	}
	
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
	
	@Column(name="date_of_journey")
	public Date getDateOfJourney() {
		return dateOfJourney;
	}
	public void setDateOfJourney(Date dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}
	
	@Column(name="tdp_cadre_travels_id")
	public Long getTdpCadreTravelsId() {
		return tdpCadreTravelsId;
	}
	public void setTdpCadreTravelsId(Long tdpCadreTravelsId) {
		this.tdpCadreTravelsId = tdpCadreTravelsId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_travels_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	public TdpCadreTravels getTdpCadreTravels() {
		return tdpCadreTravels;
	}
	public void setTdpCadreTravels(TdpCadreTravels tdpCadreTravels) {
		this.tdpCadreTravels = tdpCadreTravels;
	}
	
	@Column(name="cancelled_tickets_count")
	public Long getCancelledTicketsCount() {
		return cancelledTicketsCount;
	}
	public void setCancelledTicketsCount(Long cancelledTicketsCount) {
		this.cancelledTicketsCount = cancelledTicketsCount;
	}
	
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@Column(name="ticket_cost")
	public Double getTicketCost() {
		return ticketCost;
	}
	public void setTicketCost(Double ticketCost) {
		this.ticketCost = ticketCost;
	}
	
	
	

}
