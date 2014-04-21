package com.itgrids.partyanalyst.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="voter_family_count")
public class VoterFamilyCount {
	
	private static final long serialVersionUID = 5897555724979410783L;
	private Long constituencyId;
	private String tehsilName;
	private String panchayatName;
    private String  hamletName;
	private Long boothNo;
	private String houseNo;
	private Long count;
	
	private int elderPersonAge;
	private String elderVoterId;
	private String elderVoterIdCardNo;
	private String elderPersonName;
	private String eldPersomGender;

	
	private String caste;
	
	private String youngerVoterId;
	private String youngerVoterIdCardNo;
	private String youngerPersonName;	
	private String youngPersomGender;
	private Long youngerPersonAge;
	private Long boothId;
	
	private Long voterInfoCountId;

	@Column(name="L_Constituency_id" )
	public Long getConstituencyId() {
		return constituencyId;
	}
    
	
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	@Column(name="L_Tehsil_Name" )
	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	@Column(name="L_Panchayat_Name" )
	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	
	@Column(name="L_Hamlet_Name" )
	public String getHamletName() {
		return hamletName;
	}

	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	
	@Column(name="L_Booth_NO" )
	public Long getBoothNo() {
		return boothNo;
	}

	public void setBoothNo(Long boothNo) {
		this.boothNo = boothNo;
	}
	@Column(name="L_HOUSE_NO" )
	public String getHouseNo() {
		return houseNo;
	}
	
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	@Column(name="L_COUNT_OF_MEMBERS")
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	@Column(name="L_ELDER_AGE")
	public int getElderPersonAge() {
		return elderPersonAge;
	}

	public void setElderPersonAge(int elderPersonAge) {
		this.elderPersonAge = elderPersonAge;
	}
	@Column(name="L_ELDER_VOTER_ID")
	public String getElderVoterId() {
		return elderVoterId;
	}

	public void setElderVoterId(String elderVoterId) {
		this.elderVoterId = elderVoterId;
	}
	@Column(name="L_ELDER_VOTER_ID_CARD_NO")
	public String getElderVoterIdCardNo() {
		return elderVoterIdCardNo;
	}

	public void setElderVoterIdCardNo(String elderVoterIdCardNo) {
		this.elderVoterIdCardNo = elderVoterIdCardNo;
	}
	@Column(name="L_ELDER_PERSON_NAME")
	public String getElderPersonName() {
		return elderPersonName;
	}

	public void setElderPersonName(String elderPersonName) {
		this.elderPersonName = elderPersonName;
	}
	@Column(name="L_ELDER_GENDER")
	public String getEldPersomGender() {
		return eldPersomGender;
	}

	public void setEldPersomGender(String eldPersomGender) {
		this.eldPersomGender = eldPersomGender;
	}
	@Column(name="L_Caste")
	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}
	@Column(name="L_YOUNGER_VOTER_ID")
	public String getYoungerVoterId() {
		return youngerVoterId;
	}

	public void setYoungerVoterId(String youngerVoterId) {
		this.youngerVoterId = youngerVoterId;
	}
	@Column(name="L_YOUNGER_VOTER_ID_CARD_NO")
	public String getYoungerVoterIdCardNo() {
		return youngerVoterIdCardNo;
	}

	public void setYoungerVoterIdCardNo(String youngerVoterIdCardNo) {
		this.youngerVoterIdCardNo = youngerVoterIdCardNo;
	}
	@Column(name="L_YOUNGER_PERSON_NAME")
	public String getYoungerPersonName() {
		return youngerPersonName;
	}
	
	public void setYoungerPersonName(String youngerPersonName) {
		this.youngerPersonName = youngerPersonName;
	}
	@Column(name="L_YOUNGER_GENDER")
	public String getYoungPersomGender() {
		return youngPersomGender;
	}

	public void setYoungPersomGender(String youngPersomGender) {
		this.youngPersomGender = youngPersomGender;
	}
	@Column(name="L_YOUNGER_AGE")
	public Long getYoungerPersonAge() {
		return youngerPersonAge;
	}

	public void setYoungerPersonAge(Long youngerPersonAge) {
		this.youngerPersonAge = youngerPersonAge;
	}
	@Column(name="L_Booth_id")
	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "L_voter_family_count_id",unique = true, nullable=false)
	public Long getVoterInfoCountId() {
		return voterInfoCountId;
	}

	public void setVoterInfoCountId(Long voterInfoCountId) {
		this.voterInfoCountId = voterInfoCountId;
	}
	
	
	

}
