package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CastePositionVO {
	
	private Long casteId;
	private String casteName;
	private Long positionId;
	private String positionName;
	private Long count = 0l;
	private List<CastePositionVO> casteList = new ArrayList<CastePositionVO>(0);
	private Long totalPositionCn=0l;
	private Long noCandidateCnt=0l;
	private Long shortedListedCndtCnt=0l;
	private Long notStartedCantCnt=0l;
	private Long finalReviewCantCnt=0l;
	private Long finalizedCnt=0l;
	private Long confirmCntCnt=0l;
	private Long openPostCnt=0l;
	private Long goIssuedCnt=0l;
	private Long rejectedCnt=0l;
	private Long inProgressCnt=0l;
	private Long totalAppReceivedCnt=0l;
	private List<CastePositionVO> positionList = new ArrayList<CastePositionVO>(0);
	private List<CastePositionVO> applicationList = new ArrayList<CastePositionVO>(0);
	private String totalPositionCntPer="0";
	private String noCandidateCntPer="0";
	private String shortListedCntper="0";
	private String notStaredCntPer="0";
	private String finalReviewPer="0";
	private String confirmCntPer="0";
	private String goIssuedPer="0";
	private String rejectedAppPer="0";
	private String inProgressAppPer="0";
	private String completedAppPer="0";
	private String openPostCntPer="0";
	private String totalAppRecevidPer="0";
	private Long totalOpendPositionCnt=0l;
	private String totalOpendPositionCntPer="0";
	private Long readyForFinalReviewCnt=0l;
	private String readyForFinalReviewCntPer="0";
	private Long totalPostsCnt=0l;
	private String totalPostsCntPer="0";
	
	
	public String getOpenPostCntPer() {
		return openPostCntPer;
	}
	public void setOpenPostCntPer(String openPostCntPer) {
		this.openPostCntPer = openPostCntPer;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<CastePositionVO> getCasteList() {
		return casteList;
	}
	public void setCasteList(List<CastePositionVO> casteList) {
		this.casteList = casteList;
	}
    
	public Long getTotalPositionCn() {
		return totalPositionCn;
	}
	public void setTotalPositionCn(Long totalPositionCn) {
		this.totalPositionCn = totalPositionCn;
	}
	public Long getNoCandidateCnt() {
		return noCandidateCnt;
	}
	public void setNoCandidateCnt(Long noCandidateCnt) {
		this.noCandidateCnt = noCandidateCnt;
	}
	public Long getShortedListedCndtCnt() {
		return shortedListedCndtCnt;
	}
	public void setShortedListedCndtCnt(Long shortedListedCndtCnt) {
		this.shortedListedCndtCnt = shortedListedCndtCnt;
	}
	public Long getNotStartedCantCnt() {
		return notStartedCantCnt;
	}
	public void setNotStartedCantCnt(Long notStartedCantCnt) {
		this.notStartedCantCnt = notStartedCantCnt;
	}
	public Long getFinalReviewCantCnt() {
		return finalReviewCantCnt;
	}
	public void setFinalReviewCantCnt(Long finalReviewCantCnt) {
		this.finalReviewCantCnt = finalReviewCantCnt;
	}
	public Long getFinalizedCnt() {
		return finalizedCnt;
	}
	public void setFinalizedCnt(Long finalizedCnt) {
		this.finalizedCnt = finalizedCnt;
	}
    
	public Long getConfirmCntCnt() {
		return confirmCntCnt;
	}
	public void setConfirmCntCnt(Long confirmCntCnt) {
		this.confirmCntCnt = confirmCntCnt;
	}
	public Long getOpenPostCnt() {
		return openPostCnt;
	}
	public void setOpenPostCnt(Long openPostCnt) {
		this.openPostCnt = openPostCnt;
	}
	public Long getGoIssuedCnt() {
		return goIssuedCnt;
	}
	public void setGoIssuedCnt(Long goIssuedCnt) {
		this.goIssuedCnt = goIssuedCnt;
	}
	public Long getRejectedCnt() {
		return rejectedCnt;
	}
	public void setRejectedCnt(Long rejectedCnt) {
		this.rejectedCnt = rejectedCnt;
	}
	public Long getInProgressCnt() {
		return inProgressCnt;
	}
	public void setInProgressCnt(Long inProgressCnt) {
		this.inProgressCnt = inProgressCnt;
	}
	public Long getTotalAppReceivedCnt() {
		return totalAppReceivedCnt;
	}
	public void setTotalAppReceivedCnt(Long totalAppReceivedCnt) {
		this.totalAppReceivedCnt = totalAppReceivedCnt;
	}
	public List<CastePositionVO> getPositionList() {
		return positionList;
	}
	public void setPositionList(List<CastePositionVO> positionList) {
		this.positionList = positionList;
	}
	public List<CastePositionVO> getApplicationList() {
		return applicationList;
	}
	public void setApplicationList(List<CastePositionVO> applicationList) {
		this.applicationList = applicationList;
	}
	public String getTotalPositionCntPer() {
		return totalPositionCntPer;
	}
	public void setTotalPositionCntPer(String totalPositionCntPer) {
		this.totalPositionCntPer = totalPositionCntPer;
	}
	public String getNoCandidateCntPer() {
		return noCandidateCntPer;
	}
	public void setNoCandidateCntPer(String noCandidateCntPer) {
		this.noCandidateCntPer = noCandidateCntPer;
	}
	public String getShortListedCntper() {
		return shortListedCntper;
	}
	public void setShortListedCntper(String shortListedCntper) {
		this.shortListedCntper = shortListedCntper;
	}
	public String getNotStaredCntPer() {
		return notStaredCntPer;
	}
	public void setNotStaredCntPer(String notStaredCntPer) {
		this.notStaredCntPer = notStaredCntPer;
	}
	public String getFinalReviewPer() {
		return finalReviewPer;
	}
	public void setFinalReviewPer(String finalReviewPer) {
		this.finalReviewPer = finalReviewPer;
	}
	public String getConfirmCntPer() {
		return confirmCntPer;
	}
	public void setConfirmCntPer(String confirmCntPer) {
		this.confirmCntPer = confirmCntPer;
	}
	public String getGoIssuedPer() {
		return goIssuedPer;
	}
	public void setGoIssuedPer(String goIssuedPer) {
		this.goIssuedPer = goIssuedPer;
	}
	public String getRejectedAppPer() {
		return rejectedAppPer;
	}
	public void setRejectedAppPer(String rejectedAppPer) {
		this.rejectedAppPer = rejectedAppPer;
	}
	public String getInProgressAppPer() {
		return inProgressAppPer;
	}
	public void setInProgressAppPer(String inProgressAppPer) {
		this.inProgressAppPer = inProgressAppPer;
	}
	public String getCompletedAppPer() {
		return completedAppPer;
	}
	public void setCompletedAppPer(String completedAppPer) {
		this.completedAppPer = completedAppPer;
	}
	public String getTotalAppRecevidPer() {
		return totalAppRecevidPer;
	}
	public void setTotalAppRecevidPer(String totalAppRecevidPer) {
		this.totalAppRecevidPer = totalAppRecevidPer;
	}
	public Long getTotalOpendPositionCnt() {
		return totalOpendPositionCnt;
	}
	public void setTotalOpendPositionCnt(Long totalOpendPositionCnt) {
		this.totalOpendPositionCnt = totalOpendPositionCnt;
	}
	public String getTotalOpendPositionCntPer() {
		return totalOpendPositionCntPer;
	}
	public void setTotalOpendPositionCntPer(String totalOpendPositionCntPer) {
		this.totalOpendPositionCntPer = totalOpendPositionCntPer;
	}
	public Long getReadyForFinalReviewCnt() {
		return readyForFinalReviewCnt;
	}
	public void setReadyForFinalReviewCnt(Long readyForFinalReviewCnt) {
		this.readyForFinalReviewCnt = readyForFinalReviewCnt;
	}
	public String getReadyForFinalReviewCntPer() {
		return readyForFinalReviewCntPer;
	}
	public void setReadyForFinalReviewCntPer(String readyForFinalReviewCntPer) {
		this.readyForFinalReviewCntPer = readyForFinalReviewCntPer;
	}
	public Long getTotalPostsCnt() {
		return totalPostsCnt;
	}
	public void setTotalPostsCnt(Long totalPostsCnt) {
		this.totalPostsCnt = totalPostsCnt;
	}
	public String getTotalPostsCntPer() {
		return totalPostsCntPer;
	}
	public void setTotalPostsCntPer(String totalPostsCntPer) {
		this.totalPostsCntPer = totalPostsCntPer;
	}
}
