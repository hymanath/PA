package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;

public class VoterLocationVO implements Serializable{

	
	private static final long serialVersionUID = -2497801255884231820L;
	private Country 				country;
	private State 					state;
	private District 				district;
	private Constituency 			constituency;
	private Constituency 			pConstituency;
	private Tehsil 					tehsil;
	private LocalElectionBody 	 	LocalElectionBody;
	private Panchayat 				panchayat;
	private Constituency 			ward;//user voter details or booth 
	private Hamlet					hamlet;// user voter details or booth
	private Booth 					booth;
	
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	public Constituency getpConstituency() {
		return pConstituency;
	}
	public void setpConstituency(Constituency pConstituency) {
		this.pConstituency = pConstituency;
	}
	public Tehsil getTehsil() {
		return tehsil;
	}
	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}
	public LocalElectionBody getLocalElectionBody() {
		return LocalElectionBody;
	}
	public void setLocalElectionBody(LocalElectionBody localElectionBody) {
		LocalElectionBody = localElectionBody;
	}
	public Panchayat getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}

	public Constituency getWard() {
		return ward;
	}
	public void setWard(Constituency ward) {
		this.ward = ward;
	}
	public Hamlet getHamlet() {
		return hamlet;
	}
	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	public Booth getBooth() {
		return booth;
	}
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
	
			
}
