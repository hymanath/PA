package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "township_party_results")
public class TownshipElectionPartyResult extends BaseModel {
	
	private Long townshipPartyResultsID;
	private Nomination nomination;
	private Township township;
	private Long votesEarned;
	
	@Id
	@Column(name = "township_party_results_id", unique = true, nullable = false)
	public Long getTownshipPartyResultsID() {
		return townshipPartyResultsID;
	}
	public void setTownshipPartyResultsID(Long townshipPartyResultsID) {
		this.townshipPartyResultsID = townshipPartyResultsID;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nomination_id")
	public Nomination getNomination() {
		return nomination;
	}
	public void setNomination(Nomination nomination) {
		this.nomination = nomination;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "township_id")
	public Township getTownship() {
		return township;
	}
	public void setTownship(Township township) {
		this.township = township;
	}

	@Column(name = "votes_earned")
	public Long getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
}
