package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class NominatedPostDetailsVO {

	private Long id;
	private String name;
	
	private Long totalCount = 0l;
	private Long maleCount = 0l;
	private Long femaleCount = 0l;
	private List<NominatedPostCandidateDtlsVO> subList1 = new ArrayList<NominatedPostCandidateDtlsVO>();
	private List<NominatedPostCandidateDtlsVO> subList2 = new ArrayList<NominatedPostCandidateDtlsVO>();
	
	private List<NominatedPostDetailsVO> subList = new ArrayList<NominatedPostDetailsVO>();
	private Long count = 0l;
	public String Perc= "";
	public String Perc1= "";
	private List<NominatedPostDetailsVO> list = new ArrayList<NominatedPostDetailsVO>();
	private Long openCnt = 0l;
	private Long finalOrGOCnt = 0l;
	private Long receivedCnt = 0l;
	private Long shorlistedCnt = 0l;
	private Long readyToFinalCnt = 0l;
	private Long finalizedCnt=0l;
	private Long goIsuuedCnt =0l;
	private Long expireOneMnth =0l;
	private Long expireTwoMnth=0l;
	private Long expireThreMnth=0l;
	private Long boardId;
	private String board;
	private Long positionId;
	private String position;
	private Long positionCount=0l;
	private Long boardCunt=0l;
	
	public String getPerc1() {
		return Perc1;
	}
	public void setPerc1(String perc1) {
		Perc1 = perc1;
	}
	public Long getOpenCnt() {
		return openCnt;
	}
	public void setOpenCnt(Long openCnt) {
		this.openCnt = openCnt;
	}
	public Long getFinalOrGOCnt() {
		return finalOrGOCnt;
	}
	public void setFinalOrGOCnt(Long finalOrGOCnt) {
		this.finalOrGOCnt = finalOrGOCnt;
	}
	public Long getReceivedCnt() {
		return receivedCnt;
	}
	public void setReceivedCnt(Long receivedCnt) {
		this.receivedCnt = receivedCnt;
	}
	public Long getShorlistedCnt() {
		return shorlistedCnt;
	}
	public void setShorlistedCnt(Long shorlistedCnt) {
		this.shorlistedCnt = shorlistedCnt;
	}
	public Long getReadyToFinalCnt() {
		return readyToFinalCnt;
	}
	public void setReadyToFinalCnt(Long readyToFinalCnt) {
		this.readyToFinalCnt = readyToFinalCnt;
	}
	public List<NominatedPostDetailsVO> getList() {
		return list;
	}
	public void setList(List<NominatedPostDetailsVO> list) {
		this.list = list;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(Long maleCount) {
		this.maleCount = maleCount;
	}
	public Long getFemaleCount() {
		return femaleCount;
	}
	public void setFemaleCount(Long femaleCount) {
		this.femaleCount = femaleCount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<NominatedPostCandidateDtlsVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<NominatedPostCandidateDtlsVO> subList1) {
		this.subList1 = subList1;
	}
	public List<NominatedPostCandidateDtlsVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<NominatedPostCandidateDtlsVO> subList2) {
		this.subList2 = subList2;
	}
	public List<NominatedPostDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<NominatedPostDetailsVO> subList) {
		this.subList = subList;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getPerc() {
		return Perc;
	}
	public void setPerc(String perc) {
		Perc = perc;
	}
	public Long getFinalizedCnt() {
		return finalizedCnt;
	}
	public void setFinalizedCnt(Long finalizedCnt) {
		this.finalizedCnt = finalizedCnt;
	}
	public Long getGoIsuuedCnt() {
		return goIsuuedCnt;
	}
	public void setGoIsuuedCnt(Long goIsuuedCnt) {
		this.goIsuuedCnt = goIsuuedCnt;
	}
	public Long getExpireOneMnth() {
		return expireOneMnth;
	}
	public void setExpireOneMnth(Long expireOneMnth) {
		this.expireOneMnth = expireOneMnth;
	}
	public Long getExpireTwoMnth() {
		return expireTwoMnth;
	}
	public void setExpireTwoMnth(Long expireTwoMnth) {
		this.expireTwoMnth = expireTwoMnth;
	}
	public Long getExpireThreMnth() {
		return expireThreMnth;
	}
	public void setExpireThreMnth(Long expireThreMnth) {
		this.expireThreMnth = expireThreMnth;
	}
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Long getPositionCount() {
		return positionCount;
	}
	public void setPositionCount(Long positionCount) {
		this.positionCount = positionCount;
	}
	public Long getBoardCunt() {
		return boardCunt;
	}
	public void setBoardCunt(Long boardCunt) {
		this.boardCunt = boardCunt;
	}
	
	
	
}
