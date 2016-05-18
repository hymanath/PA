package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class MahanaduVisitVO {

	private Long above8hrs;
	private Long seventoeight;
	private Long sixtoseven;
	private Long fivetosix;
	private Long fourtofive;
	private Long threetofour;
	private Long twotothree;
	private Long onetotwo;
	private Long halfanhour;
	private Long belowhalfanhour;
	private Long totalVisitors;
	private Long currentVisitors;
	private Long currentInviteeVisitors;
	private Long parentEventId;
	private String lastUpdated;
	
	private Long above8hrsInv;
	private Long seventoeightInv;
	private Long sixtosevenInv;
	private Long fivetosixInv;
	private Long fourtofiveInv;
	private Long threetofourInv;
	private Long twotothreeInv;
	private Long onetotwoInv;
	private Long halfanhourInv;
	private Long belowhalfanhourInv;
	
	private Long totalInviteeVisitors=0l;
	private Long totalNonInviteeVisitors=0l;
	private Long currentNonInviteeVisitors=0l;
	
	private List<MahanaduEventVO> eventVOList = new ArrayList<MahanaduEventVO>();
	
	
	public Long getAbove8hrs() {
		return above8hrs;
	}
	public void setAbove8hrs(Long above8hrs) {
		this.above8hrs = above8hrs;
	}
	public Long getSeventoeight() {
		return seventoeight;
	}
	public void setSeventoeight(Long seventoeight) {
		this.seventoeight = seventoeight;
	}
	public Long getSixtoseven() {
		return sixtoseven;
	}
	public void setSixtoseven(Long sixtoseven) {
		this.sixtoseven = sixtoseven;
	}
	public Long getFivetosix() {
		return fivetosix;
	}
	public void setFivetosix(Long fivetosix) {
		this.fivetosix = fivetosix;
	}
	public Long getFourtofive() {
		return fourtofive;
	}
	public void setFourtofive(Long fourtofive) {
		this.fourtofive = fourtofive;
	}
	public Long getThreetofour() {
		return threetofour;
	}
	public void setThreetofour(Long threetofour) {
		this.threetofour = threetofour;
	}
	public Long getTwotothree() {
		return twotothree;
	}
	public void setTwotothree(Long twotothree) {
		this.twotothree = twotothree;
	}
	public Long getOnetotwo() {
		return onetotwo;
	}
	public void setOnetotwo(Long onetotwo) {
		this.onetotwo = onetotwo;
	}
	public Long getHalfanhour() {
		return halfanhour;
	}
	public void setHalfanhour(Long halfanhour) {
		this.halfanhour = halfanhour;
	}
	public Long getBelowhalfanhour() {
		return belowhalfanhour;
	}
	public void setBelowhalfanhour(Long belowhalfanhour) {
		this.belowhalfanhour = belowhalfanhour;
	}
	public Long getTotalVisitors() {
		return totalVisitors;
	}
	public void setTotalVisitors(Long totalVisitors) {
		this.totalVisitors = totalVisitors;
	}
	public Long getCurrentVisitors() {
		return currentVisitors;
	}
	public void setCurrentVisitors(Long currentVisitors) {
		this.currentVisitors = currentVisitors;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Long getAbove8hrsInv() {
		return above8hrsInv;
	}
	public void setAbove8hrsInv(Long above8hrsInv) {
		this.above8hrsInv = above8hrsInv;
	}
	public Long getSeventoeightInv() {
		return seventoeightInv;
	}
	public void setSeventoeightInv(Long seventoeightInv) {
		this.seventoeightInv = seventoeightInv;
	}
	public Long getSixtosevenInv() {
		return sixtosevenInv;
	}
	public void setSixtosevenInv(Long sixtosevenInv) {
		this.sixtosevenInv = sixtosevenInv;
	}
	public Long getFivetosixInv() {
		return fivetosixInv;
	}
	public void setFivetosixInv(Long fivetosixInv) {
		this.fivetosixInv = fivetosixInv;
	}
	public Long getFourtofiveInv() {
		return fourtofiveInv;
	}
	public void setFourtofiveInv(Long fourtofiveInv) {
		this.fourtofiveInv = fourtofiveInv;
	}
	public Long getThreetofourInv() {
		return threetofourInv;
	}
	public void setThreetofourInv(Long threetofourInv) {
		this.threetofourInv = threetofourInv;
	}
	public Long getTwotothreeInv() {
		return twotothreeInv;
	}
	public void setTwotothreeInv(Long twotothreeInv) {
		this.twotothreeInv = twotothreeInv;
	}
	public Long getOnetotwoInv() {
		return onetotwoInv;
	}
	public void setOnetotwoInv(Long onetotwoInv) {
		this.onetotwoInv = onetotwoInv;
	}
	public Long getHalfanhourInv() {
		return halfanhourInv;
	}
	public void setHalfanhourInv(Long halfanhourInv) {
		this.halfanhourInv = halfanhourInv;
	}
	public Long getBelowhalfanhourInv() {
		return belowhalfanhourInv;
	}
	public void setBelowhalfanhourInv(Long belowhalfanhourInv) {
		this.belowhalfanhourInv = belowhalfanhourInv;
	}
	public Long getCurrentInviteeVisitors() {
		return currentInviteeVisitors;
	}
	public void setCurrentInviteeVisitors(Long currentInviteeVisitors) {
		this.currentInviteeVisitors = currentInviteeVisitors;
	}
	public Long getParentEventId() {
		return parentEventId;
	}
	public void setParentEventId(Long parentEventId) {
		this.parentEventId = parentEventId;
	}
	public void setTotalInviteeVisitors(Long totalInviteeVisitors) {
		this.totalInviteeVisitors = totalInviteeVisitors;
	}
	public void setTotalNonInviteeVisitors(Long totalNonInviteeVisitors) {
		this.totalNonInviteeVisitors = totalNonInviteeVisitors;
	}
	public void setCurrentNonInviteeVisitors(Long currentNonInviteeVisitors) {
		this.currentNonInviteeVisitors = currentNonInviteeVisitors;
	}
	public List<MahanaduEventVO> getEventVOList() {
		return eventVOList;
	}
	public void setEventVOList(List<MahanaduEventVO> eventVOList) {
		this.eventVOList = eventVOList;
	}
	
	
	
}
