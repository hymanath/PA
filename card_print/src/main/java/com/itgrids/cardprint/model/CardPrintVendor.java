package com.itgrids.cardprint.model;

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
@Table(name = "card_print_vendor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CardPrintVendor extends BaseModel implements java.io.Serializable {

	private Long cardPrintVendorId;
	private String vendorName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "card_print_vendor_id", unique = true, nullable = false)
	public Long getCardPrintVendorId() {
		return cardPrintVendorId;
	}
	public void setCardPrintVendorId(Long cardPrintVendorId) {
		this.cardPrintVendorId = cardPrintVendorId;
	}
	@Column(name = "vendor_name")
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
}
