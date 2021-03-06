package com.itgrids.partyanalyst.dao.hibernate;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.ICriticalPanchayatsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.BasicVO;

public class BoothPublicationVoterDAOHiberbateTest extends BaseDaoTestCase {

	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private ICriticalPanchayatsDAO criticalPanchayatsDAO;
	private IBoothDAO boothDAO;
	private ICandidateResultDAO candidateResultDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IPartyDAO partyDAO;
	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	
	
	/*
	 * public void testfindVotersCastInfoByConstituencyAndPublicationDate() {
	 * List voters =
	 * boothPublicationVoterDAO.findVotersCastInfoByConstituencyAndPublicationDate
	 * (232l,2l); System.out.println(voters.size());
	 * 
	 * for(Object[] voter:(List<Object[]>)voters) {
	 * System.out.println(voter[0]); System.out.println(voter[1]);
	 * System.out.println(voter[2]);
	 * 
	 * }
	 * 
	 * }
	 */

	/*
	 * public void testfindTotalVotersCastInfoByConstituencyAndPublicationDate()
	 * { Long value = boothPublicationVoterDAO.
	 * findTotalVotersCastInfoByConstituencyAndPublicationDate(232l,2l);
	 * System.out.println(value); }
	 */

	/*
	 * public void testfindVotersCastInfoByBoothIdAndPublicationDate() { List
	 * voters =
	 * boothPublicationVoterDAO.findVotersCastInfoByPanchayatAndPublicationDate
	 * (1l,1l); System.out.println(voters.size());
	 * 
	 * for(Object[] voter:(List<Object[]>)voters) {
	 * System.out.println(voter[0]); System.out.println(voter[1]);
	 * System.out.println(voter[2]);
	 * 
	 * } }
	 */

	/*
	 * public void testgetGenderWiseCountInConstituency() { List list =
	 * boothPublicationVoterDAO. getGenderWiseCountInConstituency(232l,2l);
	 * for(Object[] voter:(List<Object[]>)list) { System.out.println(voter[0]);
	 * System.out.println(voter[1]); System.out.println(voter[2]); } }
	 */

	/*
	 * public void
	 * testfindVotersCastInfoByMandalAndPublicationDateBasedOnSearch() { List
	 * list =
	 * boothPublicationVoterDAO.findVotersCastInfoByMandalAndPublicationDate
	 * (835l, 2l); for(Object[] voter:(List<Object[]>)list) {
	 * System.out.println(voter[0]); System.out.println(voter[1]);
	 * System.out.println(voter[2]); } }
	 */

	/*
	 * public void testgetVotersCastInfoFromLocalElectionBody() { List list =
	 * boothPublicationVoterDAO.getVotersCastInfoFromLocalElectionBody(31l,2l);
	 * for(Object[] voter:(List<Object[]>)list) { System.out.println(voter[0]);
	 * System.out.println(voter[1]); System.out.println(voter[2]); } }
	 */

	/*
	 * public void testgetVoterDetailsByCaste() { List<Voter> list
	 * =boothPublicationVoterDAO.getVoterDetailsByCaste(206l,2l,"YERUKULA");
	 * for(Voter params :list) { System.out.println(params.getFirstName()); }
	 * 
	 * }
	 */

	/*
	 * public void testGetVotersCountByPublicationId() { List<Object[]> list =
	 * boothPublicationVoterDAO
	 * .getVotersCountByPublicationId("mandal",844l,7l);;
	 * System.out.println(list.size());
	 * System.out.println(list.get(0)[0]+" -- "+list.get(0)[1]);
	 * System.out.println(list.get(1)[0]+" -- "+list.get(1)[1]); }
	 */

	/*
	 * public void testFindVotersCountByPublicationIdInALocation() { Long count
	 * = boothPublicationVoterDAO.findVotersCountByPublicationIdInALocation(
	 * "localElectionBody",83l,6l); System.out.println(count); }
	 */

	/*
	 * public void
	 * testGetCastCategoryWiseVotersCountByPublicationIdInALocation() {
	 * List<Object[]> list = boothPublicationVoterDAO.
	 * getCastCategoryWiseVotersCountByPublicationIdInALocation
	 * (1l,"constituency",232l,6l); System.out.println(list.size());
	 * 
	 * for(Object[] params :list) {
	 * System.out.println(params[0]+"  --  "+params[1]); } }
	 */

	/*
	 * public void
	 * testGetCastAndGenderWiseVotersCountByPublicationIdInALocation() {
	 * List<Object[]> list = boothPublicationVoterDAO.
	 * getCastAndGenderWiseVotersCountByPublicationIdInALocation
	 * (1l,"constituency",232l,6l);
	 * 
	 * System.out.println(list.size());
	 * 
	 * for(Object[] params :list) {
	 * //System.out.println(params[0]+"  --  "+params[1]+"  --  "+params[2]);
	 * System.out.println(params[4]); } }
	 */
	/*
	 * public void testgetVoterDetailsByCasteState() { //List<Voter> voter =
	 * boothPublicationVoterDAO.getVoterDetailsByCasteState(204l,2l,1l);
	 * //System.out.println(voter.size()); }
	 */

	/*
	 * public void
	 * testgetPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation()
	 * { System.out.println("start"); long startPrevElecTime =
	 * System.currentTimeMillis(); List<Object[]> list =
	 * boothPublicationVoterDAO
	 * .getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation
	 * (1l,"constituency",232l,7l,232l); System.out.println(list.size()); long
	 * endPrevElecTime = System.currentTimeMillis(); long diff =
	 * endPrevElecTime-startPrevElecTime;
	 * System.out.println("time to execute Voters Cast Details in millsec :"
	 * +diff+" in seconds:"+diff/1000+"");
	 * 
	 * }
	 */

	/*
	 * public void testgetPartyWiseCastCategoryVotersCount() { List<Object[]>
	 * list = boothPublicationVoterDAO.
	 * getCastCategoryWiseVotersCountByPublicationIdInALocation(1l,
	 * "constituency", 232l, 6l); System.out.println(list.size()); }
	 */

	
	 /*public void testGetCastWiseCount()
	  
	  {
		List<Object[]> list = boothPublicationVoterDAO.getCastWiseCount(1l,"panchayat", 1l, 7l,232l); 
	  	System.out.println(list.size());
	  	for(Object[] data:list){
	  		System.out.println(data[0]+" "+data[1]); 
	  	} 
	  }*/
	 

	/*
	 * public void testGetPartyWiseCount() { List<Object[]> list =
	 * boothPublicationVoterDAO.getPartyWiseCount(1l, "panchayat", 1l, 7l);
	 * System.out.println(list.size()); for(Object[] data:list){
	 * System.out.println(data[0]+" "+data[1]+" "+data[2]+" "+data[3]); } }
	 */

	/*
	 * public void testgetConstituencies() { List<Object[]> list =
	 * boothPublicationVoterDAO.getConstituencies();
	 * System.out.println(list.size()); for(Object[] params:list) {
	 * System.out.println(params[0]); System.out.println(params[1]); } }
	 */

	/*
	 * public void testGetConstituenciesIds() { List<Long> constituencyIds =
	 * boothPublicationVoterDAO.getConstituenciesIds();
	 * System.out.println(constituencyIds.size()); if(constituencyIds != null &&
	 * constituencyIds.size() >0) System.out.println(constituencyIds.get(0)); }
	 */

	/*
	 * public void testFindVotersGenderWiseCountByPublicationIdInALocation() {
	 * List<Object[]> list =
	 * boothPublicationVoterDAO.findVotersGenderWiseCountByPublicationIdInALocation
	 * (IConstants.CONSTITUENCY,232l,7l); System.out.println(list.size());
	 * 
	 * for(Object[] params : list)
	 * System.out.println(params[0]+" -- "+params[1]); }
	 */

	/*
	 * public void testFindFamiliesCountByPublicationIdInALocation() {
	 * List<Long> list =
	 * boothPublicationVoterDAO.findFamiliesCountByPublicationIdInALocation
	 * (IConstants.CONSTITUENCY, 233l, 8l); System.out.println(list.size());
	 * Long total = 0L; for(Long l : list) { System.out.println(l); total =
	 * total + l; } System.out.println(total); }
	 */
	// getAllImpFamilesCount

	/*
	 * public void testGetAllImpFamilesCount() { List<Long> list =
	 * boothPublicationVoterDAO.getAllImpFamilesCount(IConstants.BOOTH, 122084l,
	 * 7l); System.out.println(list.size()); for(Long l :list)
	 * System.out.println(l); }
	 */

	/*
	 * public void testGetVotersCountInAAgeRange() {
	 * System.out.println(boothPublicationVoterDAO
	 * .getVotersCountInAAgeRange(IConstants.BOOTH, 122084l,7l,16l, 25l,"F"));
	 * System
	 * .out.println(boothPublicationVoterDAO.getVotersCountInAAgeRange(IConstants
	 * .BOOTH, 122084l,7l,26l, 35l,"F"));
	 * System.out.println(boothPublicationVoterDAO
	 * .getVotersCountInAAgeRange(IConstants.BOOTH, 122084l,7l,36l, 45l,"F"));
	 * System
	 * .out.println(boothPublicationVoterDAO.getVotersCountInAAgeRange(IConstants
	 * .BOOTH, 122084l,7l,46l, 60l,"F"));
	 * System.out.println(boothPublicationVoterDAO
	 * .getVotersCountInAAgeRange(IConstants.BOOTH, 122084l,7l,60l, null,"F"));
	 * }
	 */

	/*
	 * public void test() { List<Object[]> list =
	 * boothPublicationVoterDAO.getPublicationDetailsBasedOnConstituency(232l);
	 * System.out.println(list.size()); }
	 */
	/*
	 * public void testFindVoterContactDetails() { List<BoothPublicationVoter>
	 * list = boothPublicationVoterDAO.findVoterContactDetails(1l); for
	 * (BoothPublicationVoter objects : list) {
	 * 
	 * System.out.println(objects); } }
	 */
	/*
	 * public void testGetFamileyMembersDetailsForHouseNo() { List<Object[]>
	 * list =
	 * boothPublicationVoterDAO.getFamileyMembersDetailsForHouseNo("000",121884l
	 * ,1l); for (Object[] objects : list) {
	 * 
	 * System.out.println(objects[0]); System.out.println(objects[1]);
	 * System.out.println(objects[2]); } }
	 */
	/*
	 * public void test() { List<Object[]> list = boothPublicationVoterDAO.
	 * getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId
	 * (83l,7l,46L, 60L); System.out.println(list.size()); }
	 */

	/*
	 * public void testGetVoterPublicationIdsBetweenTwoPublications() {
	 * List<Long> list =
	 * boothPublicationVoterDAO.getVoterPublicationIdsBetweenTwoPublications
	 * (1l,8l); System.out.println(list.size());
	 * 
	 * for(Long l : list) System.out.println(l); }
	 */

	/*
	 * public void testGetPreviousPublicationIds() { List<Long> list =
	 * boothPublicationVoterDAO.getPreviousPublicationIds(8l); for(Long l :
	 * list) System.out.println(l); }
	 */

	/*
	 * public void testGetVotersByBoothId() { List<Voter> list =
	 * boothPublicationVoterDAO.getVotersByBoothId(121884l);
	 * System.out.println(list.size()); }
	 */

	/*
	 * public void testGetBoothIdAndVoterIdByConstituencyInAPublication() {
	 * List<Object[]> list =
	 * boothPublicationVoterDAO.getPartNoAndVoterIdByConstituencyInAPublication
	 * (232l,7l); System.out.println(list.size()); }
	 */

	/*
	 * public void testUpdateSerialNoByVoterId() {
	 * System.out.println(boothPublicationVoterDAO
	 * .updateSerialNoByVoterId(1l,1l)); }
	 */

	/*
	 * public void testGetVotersInABooth() { List<String> partNosList = new
	 * ArrayList<String>(0); //partNosList.add("141"); //partNosList.add("142");
	 * //partNosList.add("143"); //partNosList.add("144");
	 * //partNosList.add("145"); partNosList.add("205"); List<Voter> votersList
	 * = boothPublicationVoterDAO.getVotersInABoothsList(partNosList,163l,8l);
	 * List<String> houseNoList = new ArrayList<String>(0);
	 * 
	 * int count = 0;
	 * 
	 * Map<String,Long> houseNoMap = new HashMap<String,Long>(0); for(Voter
	 * voter : votersList) { if(houseNoMap.get(voter.getHouseNo()) == null) {
	 * houseNoMap.put(voter.getHouseNo(), 0L); } Long hCount =
	 * houseNoMap.get(voter.getHouseNo()); houseNoMap.put(voter.getHouseNo(),
	 * hCount.longValue()+1); }
	 * 
	 * for(Voter voter :votersList) {
	 * if(!houseNoList.contains(voter.getHouseNo())) { System.out.println();
	 * houseNoList.add(voter.getHouseNo());
	 * System.out.print("\t#"+voter.getHouseNo());
	 * System.out.print("\t"+houseNoMap.get(voter.getHouseNo())); count = 0; }
	 * 
	 * if(count > 0 && count % 3 == 0) { System.out.println();
	 * System.out.print("\t#"+voter.getHouseNo());
	 * System.out.print("\t"+houseNoMap.get(voter.getHouseNo())); } count++;
	 * System.out.print("\t"+voter.getName());
	 * System.out.print("\t"+voter.getGender());
	 * System.out.print("\t"+voter.getAge()); } }
	 */
	/*
	 * public void testGetSerialNoByVoterIdsList() { List<Long> voterIdsList =
	 * new ArrayList<Long>(0); voterIdsList.add(1l); List<Object[]> list =
	 * boothPublicationVoterDAO.getSerialNoByVoterIdsList(voterIdsList); if(list
	 * != null && list.size() > 0) { for(Object[] params : list)
	 * System.out.println(params[0] +" "+params[1]); } }
	 */
	/*
	 * public void testgetCadreMobileDetails(){ List<Long> voterIdsList = new
	 * ArrayList<Long>(0); voterIdsList.add(315l); List
	 * list=boothPublicationVoterDAO
	 * .getCadreMobileDetails(1l,voterIdsList,"Constituency");
	 * 
	 * System.out.println(list.size()); }
	 */
	/*
	 * public void testgetInfluencePeopleMobileDetails(){ List<String>
	 * voterIdsList = new ArrayList<String>(0); voterIdsList.add("1013"); List
	 * list
	 * =boothPublicationVoterDAO.getInfluencePeopleMobileDetails(1l,voterIdsList
	 * ,"Mandal");
	 * 
	 * System.out.println(list.size()); }
	 */

	/*
	 * public void testgetVoterMobileDetails(){
	 * 
	 * List
	 * list=boothPublicationVoterDAO.getVoterMobileDetails(1l,121896l,"Booth");
	 * System.out.println(list.size()); System.out.println(list.get(0));
	 * System.out.println(list.get(1)); System.out.println(list.get(2)); }
	 */
	/*
	 * public void testgetLatestpublicationDate(){ List<Long> voterIdsList = new
	 * ArrayList<Long>(0); voterIdsList.add(995l); voterIdsList.add(996l);
	 * voterIdsList.add(997l); voterIdsList.add(998l); voterIdsList.add(999l);
	 * List<Object[]>
	 * list=boothPublicationVoterDAO.getVotersBasedOnVoterIdsAndPublicationAndGender
	 * (7l,voterIdsList); Iterator it=val.iterator(); while(it.hasNext())
	 * System.out.println(it.next().toString()); if(list != null && list.size()
	 * > 0) { for(Object[] params : list) System.out.println(params[0]
	 * +" "+params[1]); }
	 * 
	 * }
	 */

	/*
	 * public void tests(){
	 * 
	 * //List<?> list1=boothPublicationVoterDAO.getVotersListInPanchayat(7l,1l);
	 * //List<?> list1 = null; Iterator it=val.iterator(); while(it.hasNext())
	 * System.out.println(it.next().toString()); List<Long> voterIdsList = new
	 * ArrayList<Long>(0); voterIdsList.add(995l); voterIdsList.add(996l);
	 * voterIdsList.add(997l); voterIdsList.add(998l); voterIdsList.add(999l);
	 * 
	 * List<Object[]>
	 * list=boothPublicationVoterDAO.getVotersListInPanchayat(7l,1l,1l); if(list
	 * != null && list.size() > 0) { for(Object[] params : list)
	 * System.out.println(params[0]+" "+params[1]); }
	 * 
	 * 
	 * }
	 */

	/*
	 * public void tests(){
	 * 
	 * //List<?> list1=boothPublicationVoterDAO.getVotersListInPanchayat(7l,1l);
	 * List<?> list1 = null; Iterator it=val.iterator(); while(it.hasNext())
	 * System.out.println(it.next().toString()); List<Long> voterIdsList = new
	 * ArrayList<Long>(0); voterIdsList.add(995l); voterIdsList.add(996l);
	 * voterIdsList.add(997l); voterIdsList.add(998l); voterIdsList.add(999l);
	 * 
	 * List<Object[]> list=
	 * boothPublicationVoterDAO.getUnassignedVotersInPanchayat(1l); if(list !=
	 * null && list.size() > 0) { for(Object[] params : list)
	 * System.out.println(params[0].toString()+" "+params[1].toString()); }
	 * 
	 * 
	 * }
	 */

	/*
	 * public void testGetBoothPublicationVoterIdsByVoterIdsList() { List<Long>
	 * voterIdsList = new ArrayList<Long>(0); voterIdsList.add(22326l);
	 * voterIdsList.add(22327l); voterIdsList.add(22328l);
	 * voterIdsList.add(22329l); voterIdsList.add(22330l); List<Long> list =
	 * boothPublicationVoterDAO
	 * .getBoothPublicationVoterIdsByVoterIdsList("1",voterIdsList,8l);
	 * 
	 * System.out.println(list.size());
	 * 
	 * for(Long bpvIds : list) System.out.println(bpvIds);
	 * 
	 * }
	 */

	/*
	 * public void testDeleteByIdsList() { List<Long> voterIdsList = new
	 * ArrayList<Long>(0); voterIdsList.add(1l);
	 * System.out.println(boothPublicationVoterDAO
	 * .deleteByIdsList(voterIdsList));
	 * 
	 * }
	 */

	/*
	 * public void tests(){ List<Object[]>
	 * objlist=boothPublicationVoterDAO.getAssignedAndUnassignedVtrsOfLclBdy
	 * (42l,1l,IConstants.HAMLET); if(objlist != null && objlist.size() > 0) { for(Object[] params
	 * : objlist)
	 * System.out.println(params[0].toString()+" "+params[1].toString()); }
	 * 
	 * }
	 */
	/*public void testgetVIdsAndSerialNoByBoothId() {
		List<Object[]> objlist = boothPublicationVoterDAO
				.getVIdsAndSerialNoByBoothId(122995l, 19l, 25l);
		if (objlist != null && objlist.size() > 0) {
			for (Object[] params : objlist)
				System.out.println(params[1].toString());
		}
	}*/

	/*public void testcheckSerialNoandVoterIdDuplicates() {
		
		List<Long> values = boothPublicationVoterDAO
				.checkSerialNoandVoterIdDuplicates(3000000l, 2l, 122995l);
		System.out.println(values.size());
	}*/
	
	/*public void testGetVoterData()
	{
		List<Voter> voter = boothPublicationVoterDAO.getVoterDataForPanchayat(4l, 7l,0l, 100l, "voterId", "asc");
		System.out.println(voter.size());
	}*/
	/*public void testGetVoterData()
	{
		List<Object[]> voter = boothPublicationVoterDAO.getVoterDataForBooth(123020l, 7l,0l, 100l, "voterId", "asc");
		System.out.println(voter.size());
	}*/
	
	/*public void test()
	{
		List<Long> attributeIds = new ArrayList<Long>();
		attributeIds.add(5l);
		 List<Object[]> count = boothPublicationVoterDAO.getAgeWiseDetailsForHamlet(1l,attributeIds,"hamlet",2844l,232l,8l,26l,
				35l);
		for(Object[] params : count)
		{
			System.out.println((Long)params[0]);
			System.out.println(params[1].toString());
			System.out.println("userVOterCategoryId -" +params[2]);
			System.out.println("userVOterCategoryId -" +params[3]);
			
		}
		 
	}*/
	
	/*public void testGetVoterData()
	{
		List<Object[]> voter = boothPublicationVoterDAO.getCategoryWiseVoterDetailsByCategoryId("Panchayat", 1l, 232l, 5l, 8l, 1l, 1, 100, "asc", "name");
		System.out.println(voter.size());
		if(voter != null && voter.size() > 0)
		{
			for(Object[] params : voter)
			{
				System.out.println(params[1]+" "+params[2]);
			}
		}
	}*/
	
	/*public void testGetcategoryWiseVotersCount()
	{
		List voter = boothPublicationVoterDAO.getcategoryWiseVotersCount("Panchayat", 1l, 232l, 5l, 8l, 1l);
		System.out.println(voter.get(0));
		
	}*/
	
	/*public void testGetCategoryWiseVoterDetailsByHamletId()
	{
		List<Object[]> list = boothPublicationVoterDAO.getCategoryWiseVoterDetailsByHamletId(42l, 232l, 5l, 8l, 1l, 0, 100, "asc", "name");
		System.out.println(list.size());
	}*/
	
	
	/*public void testGetVotersCountForCustomWard()
	{
		List<Object[]> list = boothPublicationVoterDAO.getVotersCountForCustomWardBooths(232l, 28858l, 8l, 1l);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(Object[] params :list)
				System.out.println(params[1]);
		}
		
	}*/
	
	/*public void testgetCustomWardWiseVotersInfoByLocalEleId()
	{
		List<Object[]> list = boothPublicationVoterDAO.getCustomWardWiseVotersInfoByLocalEleId(232l, 8l, 83l, 1l);
		System.out.println(list.size());
	}*/
	
	/*public void testgetCasteCountForLocalEleBody()
	{
		System.out.println(boothPublicationVoterDAO.getCasteCountForLocalEleBody(1l, 8l, 232l, 83l));
	}*/
	
	/*public void testGetWardsByLocalEleBodyIdId()
	{
		List<Object[]> list = boothPublicationVoterDAO.getWardsByLocalEleBodyIdId(1l, 8l, 83l, 232l);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
	}*/
	
	/*public void testGetVoterDetailsForCustomWard()
	{
		List<Voter> votersList = boothPublicationVoterDAO.getVoterDetailsForCustomWard(28858l, 8l, 1l, 285l);
		System.out.println(votersList.size());
		
	}*/
	
	/*public void testGetVoterDetailsForCustomWardBooths()
	{
		List<Voter> votersList = boothPublicationVoterDAO.getVoterDetailsForCustomWardBooths(28858l, 122829l, 1l, 8l, 296l);
		System.out.println(votersList.size());
		
	}*/
	/*public void testGetVoterCastAndPartyCountForDifferentLocations()
	{   List<Long> attributeIds = new ArrayList<Long>();
	    List<Long> ids = new ArrayList<Long>();
	    List<String> partNos = new ArrayList<String>();
		attributeIds.add(296l);attributeIds.add(284l);attributeIds.add(10l);attributeIds.add(87l);attributeIds.add(290l);attributeIds.add(91l);attributeIds.add(34l);attributeIds.add(95l);attributeIds.add(189l);attributeIds.add(211l);attributeIds.add(38l);attributeIds.add(292l);attributeIds.add(285l);attributeIds.add(283l);attributeIds.add(62l);attributeIds.add(118l);attributeIds.add(161l);attributeIds.add(280l);
		partNos.add("25");partNos.add("29");partNos.add("1");partNos.add("2");partNos.add("3");


		List<Object[]> votersList = boothPublicationVoterDAO.getVoterCastAndPartyCountForDifferentLocations(1l,attributeIds,"booth",ids,232l,8l,"caste",partNos);


		
	}*/
	
/*	public void testGetCatstesForBooths()
	{
		List<Long> boothIds = new ArrayList<Long>(0);
		boothIds.add(122792l);
		List<Object[]> list = boothPublicationVoterDAO.getCatstesForBooths(1l, boothIds, 8l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetCadreCountForSelectedLevel()
	{
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(123073l);
		boothIds.add(123074l);
		boothIds.add(123075l);
		boothIds.add(123076l);
		List<Long> values = boothPublicationVoterDAO.getCadreCountForSelectedLevel(boothIds,241l,1l);
		for (Long long1 : values) {
			System.out.println(long1);
		}
	}*/

	/*public void testGetInfluencingPeopleCountForSelectedLevel()
	{
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(123074l);
		boothIds.add(123075l);
		boothIds.add(123076l);
		boothIds.add(123077l);
		List<Long> values = boothPublicationVoterDAO.getInfluencingPeopleCountForSelectedLevel(boothIds,241l,1l);
		for (Long long1 : values) {
			System.out.println(long1);
		}
	}*/
	/*public void testGetPoliticianCountForSelectedLevel()
	{
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(123074l);
		boothIds.add(123075l);
		boothIds.add(123076l);
		boothIds.add(123077l);
		List<Long> values = boothPublicationVoterDAO.getPoliticianCountForSelectedLevel(boothIds,241l);
		for (Long long1 : values) {
			System.out.println(long1);
		}
	}*/
	/*public void testgetCadreDetailsForSelectedlevel()
	{
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(123074l);
		boothIds.add(123075l);
		boothIds.add(123076l);
		boothIds.add(123077l);
		List<Voter> values = boothPublicationVoterDAO.getCadreDetailsForSelectedlevel(boothIds,241l,1L,0,5,"asc","voterId");
		for (Voter voter : values) {
			System.out.println(voter.getName());
		}
	}*/
	
	/*public void testGetInfluencingPeopleDetailsForSelectedlevel()
	{
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(123074l);
		boothIds.add(123075l);
		boothIds.add(123076l);
		boothIds.add(123077l);
		List<Voter> values = boothPublicationVoterDAO.getInfluencingPeopleDetailsForSelectedlevel(boothIds,241l,1L,0,5,"asc","voterId");
		for (Voter voter : values) {
			System.out.println(voter.getName());
		}
	}*/
	/*public void testGetPoliticanDetailsForSelectedlevel()
	{
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(123074l);
		boothIds.add(123075l);
		boothIds.add(123076l);
		boothIds.add(123077l);
		List<Voter> values = boothPublicationVoterDAO.getPoliticanDetailsForSelectedlevel(boothIds,241l,0,5,"asc","voterId");
		for (Voter voter : values) {
			System.out.println(voter.getName());
		}
	}*/
	
	/*public void testGetInfluencingPeopleCountForSelectedConstituency()
	{
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(123074l);
		boothIds.add(123075l);
		boothIds.add(123076l);
		boothIds.add(123077l);
		long startTime = System.currentTimeMillis();
		List<Long> values = boothPublicationVoterDAO.getCountForSelectedTypeAndSelectedLevel(boothIds,241l,1l,"panchayat",844l,"influencingpeople");
		Long endTime = System.currentTimeMillis();
		System.out.println(startTime);
		System.out.println(endTime);
		for (Long long1 : values) 
		{
			System.out.println(long1);
		}
		System.out.println(endTime-startTime);
	}*/
	
	/*public void testgetAgeWiseCustomVoterDetails()
	{
		List<Object[]> list = boothPublicationVoterDAO.getAgeWiseCustomVoterDetails(232l, 844l, 8l, "RURAL", 1l, "18to25");
		System.out.println(list.size());
	}*/
	
	/*public void testgetAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId()
	{
		List<Object[]> list= boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(844l, 8l, 15l, 150l, 232l);
		
		for(Object[] params : list)
		 System.out.println(params[0]);
	}*/
	
	/*public void testGetTotalVotersForBooth()
	{
		List<Object[]> list = boothPublicationVoterDAO.getBoothWiseTotalVoters(232l, 8l, IConstants.BOOTH);
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]+" "+params[2]);
	}*/
	
	/*public void testgetWardWiseTotalVotersCount()
	{
	  List<Object[]> list = boothPublicationVoterDAO.getWardWiseTotalVotersCount(232l, 8l, 83l);
	  System.out.println(list.size());
		
	}*/
	
	/*public void testgetPanchayatsCountByMandalIdsList()
	{
		List<Long> mandalIdsList = new ArrayList<Long>(0);
		mandalIdsList.add(844l);
		mandalIdsList.add(836l);
		List<Object[]> list = boothPublicationVoterDAO.getPanchayatsCountByMandalIdsList(mandalIdsList, 232l, 8l,"panchayatsCount");
		
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]);
	}*/
	
	/*public void testgetBoothsCountByPanchayatIdsList()
	{
		List<Long> panchayatIdsList = new ArrayList<Long>(0);
		panchayatIdsList.add(5152L);
      List<Object[]> list = boothPublicationVoterDAO.getBoothsCountByPanchayatIdsList(panchayatIdsList, 221l, 8l,"panchayatBooths");
		
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]);
	}*/
	/*public void testgetVotersCountAgeWise(){
		
		List<Long> boothIdsList = new ArrayList<Long>(0);
		boothIdsList.add(122992L);
		List<Object[]> list= boothPublicationVoterDAO.getVotersCountAgeWise(90l, 105l, boothIdsList);
	}*/
	/*public void testa(){
		List<Long> boothIds = new ArrayList<Long>(0);
		boothIds.add(122992L);
		boothIds.add(122993L);
		
		List<Long> casteIds = new ArrayList<Long>(0);
		casteIds.add(211L);
		casteIds.add(189L);
		casteIds.add(285L);
		casteIds.add(290L);
		casteIds.add(292L);
			
		//List<Object[]> list=boothPublicationVoterDAO.getVotersCasteDetailsForAgeRangeForSelectedCastes(18l, 25l, boothIds,1l,casteIds);
		List<Object[]> list1=boothPublicationVoterDAO.getVotersCasteDetailsForAgeRange(18l, 25l, boothIds,1l);
		/*for(Object[] l:list){
			for(Object o:l){
				System.out.print(o.toString()+" ");
			}
			System.out.println();
		}*/
		/*System.out.println("--------");
		for(Object[] l:list1){
			for(Object o:l){
				System.out.print(o.toString()+" ");
			}
			System.out.println();
		}
	}*/
	/*public void testgetTotalVoters()
	{
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(123074l);
		boothIds.add(123075l);
		boothIds.add(123076l);
		boothIds.add(123077l);
		List<Object[]> values = boothPublicationVoterDAO.getTotalVotersByBooths(boothIds);
		for (Object[] params : values) {
			 System.out.println(params[0]+" "+params[1]);
		}
	}*/
	
	/*public void testFamilesCount()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(127l);
		List<Object[]> values = boothPublicationVoterDAO.getImpFamilesForPanchayatByPublicationIdAndVoters(8l,1l,IConstants.HAMLET,ids);
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1]);
		}
	}*/
	
	/*public void testGetRecordsFromBoothPublicationVoter()
	{
		List<Object[]> list = boothPublicationVoterDAO.getRecordsFromBoothPublicationVoter(232l,8l);
		System.out.println(list.size());
	}*/
	
	/*public void testgetVoterDetailsForMessageCenter()
	{
		String queryStr = "";
		List<Object[]> list = boothPublicationVoterDAO.getVoterDetailsForMessageCenter(232l, 8l, 2844l, queryStr, null, null, 1l);
	}*/
	
	/*public void testGetVoterDetailsOfAConstituency()
	{
		List<Object[]> list = boothPublicationVoterDAO.getVoterDetailsOfAConstituency(232l,8l,1l);
		System.out.println(list.size());
	}*/
	
	/*public void testgetVoterAgeDetailsForSelectedLocation()
	{
		List<Long> locationIdsList = new ArrayList<Long>(0);
		locationIdsList.add(232l);
		List<Object[]> list = boothPublicationVoterDAO.getVoterAgeDetailsForSelectedLocation(232l, 8l, locationIdsList, IConstants.CONSTITUENCY);
		System.out.println(list.size());
	}*/
	
	/*public void testgetAgeAndGenderWiseVoterInConstituency(){
		List<Object[]> list = boothPublicationVoterDAO.getAgeAndGenderWiseVotersCountInPanchayatOfConstituency(232l, 8l, 18l, 22l,"rural",1l);
		for(Object[] obj:list){
			System.out.println(obj[0].toString()+"--"+obj[1].toString()+"--"+obj[2].toString()+"--"+obj[3].toString()+"--"+obj[4].toString());
		}
	}*/
	
/*	public void testgetAgeAndGenderWiseVoterInConstituency(){
		List<Object[]> list = boothPublicationVoterDAO.getTotalVotersInPanchayatOfConstituency(228l, 8l,"rural");
		for(Object[] obj:list){
			System.out.println(obj[0].toString()+"--"+obj[1].toString()+"--"+obj[2].toString());
		}
	}*/
	
	/*public void testgetAgeAndGenderWiseVoterInConstituency(){
		List<Object[]> list = boothPublicationVoterDAO.getTotalVotersInPanchayatOfConstituencyByAge(228l, 8l,18l,22l,"rural");
		for(Object[] obj:list){
			System.out.println(obj[0].toString()+"--"+obj[1].toString()+"--"+obj[2].toString()+"--"+obj[3].toString());
		}
	}*/
	/*public void testgetAgeAndGenderWiseVoterInConstituency(){
		List<Object[]> list = boothPublicationVoterDAO.getTotalVotersInBoothOfMuncipalityByConstituencyId(232l, 8l);
		for(Object[] obj:list){
			System.out.println(obj[0].toString()+"--"+obj[1].toString()+"--"+obj[2].toString()+"--"+obj[3].toString()+"--"+obj[4].toString()+"--"+obj[5].toString());
		}
	}*/
	/*public void testgetAgeAndGenderWiseVoterInConstituency(){
		List<Long> casteIds = new ArrayList<Long>();
		casteIds.add(228l);
		casteIds.add(202l);
		casteIds.add(296l);
		casteIds.add(290l);
		List<Object[]> values = boothPublicationVoterDAO.getExpCasteForAgeAndGenderWisesMuncipaltiyVotersCount(232l, 8l, 18l, 22l,1l,casteIds);
		for (Object[] params : values) {
			System.out.println(params[0]+" "+params[1]+" "+params[2]);
		}
	}*/
	
	/*public void testgetAgeAndGenderWiseVoterInConstituency(){
		List<Object[]> list = boothPublicationVoterDAO.getTotalVotersInBoothOfMuncipalityOfConstituencyByAge(232l, 8l, 18l, 22l);
		for(Object[] obj:list){
			System.out.println(obj[0].toString()+"--"+obj[1].toString()+"--"+obj[2].toString()+"--"+obj[3].toString()+"--"+obj[4].toString()+"--"+obj[5].toString()+"--"+obj[6].toString());
		}
	}*/
	
	/*public void testgetVotersDetailsForPanchayatByPublicationId(){
		List<Object[]> list = boothPublicationVoterDAO.getVotersDetailsForPanchayatByPublicationId(4l,4l,0,100,"asc","initial");
		System.out.println(list.size());
	}*/
	
	/*public void testgetBoothAndHamletIdsByConstituencyId()
	{
		System.out.println(boothPublicationVoterDAO.getBoothAndHamletIdsByConstituencyId(232l, 8l, 1l).size());
	}*/
	/*public void testgetVotersDetailsForPanchayatByPublicationId(){
		List<Long> panchayatIds = new ArrayList<Long>();
		
		panchayatIds.add(1l);
		panchayatIds.add(2l);
		panchayatIds.add(3l);
		panchayatIds.add(4l);
		panchayatIds.add(5l);
		panchayatIds.add(6l);
		panchayatIds.add(7l);
		panchayatIds.add(8l);
		panchayatIds.add(9l);
		Long start = System.currentTimeMillis();
		System.out.println("started");
		List<Object[]> result = boothPublicationVoterDAO.getVoterAgeDetailsForPartialPanchayats(1l, 8l, panchayatIds);
		Long end = System.currentTimeMillis();
		
		for(Object[] arr:result){
			for(Object data:arr){
				System.out.print(data != null?data.toString():""+"---");
			}
			System.out.println("");
		}
		System.out.println((end-start)/1000 +" sec "+(end-start));
	}*/
	/*public void testgetVoterAttributeDetailsForPartialPanchayat(){
List<Long> attrIds = new ArrayList<Long>();
		
	attrIds.add(1l);
	attrIds.add(2l);
	attrIds.add(3l);
		List<Object[]> list = boothPublicationVoterDAO.getVoterAttributeDetailsForPartialPanchayatByHamlet(1l, attrIds, attrIds, 22l,8l);
		
	}*/
	
	/*public void testgetYoungVotersCount()
	{
		List<Long> locationIdsList = new ArrayList<Long>(0);
		locationIdsList.add(844l);
		List<Object[]> list = boothPublicationVoterDAO.getYoungVotersCount(232l, 8l, locationIdsList, "mandal", 18l, 22l);
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]+" "+params[2]);
	}
	public void testgetVotersCountByCustomWardId(){
		Long totalCount=(Long) boothPublicationVoterDAO.getVotersCountByCustomWardId(28876L,8L,232L,1L).get(0);
      System.out.println(totalCount);
	}*/
	
	/*public void testgetPanchayatAgeWiseDetailsByHamletWise()
	{
		List<Long> panchayatIds = new ArrayList<Long>();
		panchayatIds.add(1l);
		panchayatIds.add(2l);
		panchayatIds.add(3l);
		panchayatIds.add(4l);
		panchayatIds.add(5l);
		panchayatIds.add(6l);
		panchayatIds.add(7l);
		panchayatIds.add(8l);
		panchayatIds.add(9l);
		List<Object[]> values = boothPublicationVoterDAO.getPanchayatAgeWiseDetailsByHamletWise(1l,8l,panchayatIds);
		for (Object[] params : values) {
			System.out.println(params[0]+" "+params[1]+" "+params[2]);
		}
	}*/
	
	/*public void testGetVoterBySerialNo()
	{
		try{
		File resultFile  = new File("D:\\kamal\\result.txt");
		BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
		StringBuilder sb = new StringBuilder();
		List<BoothVoterVO> votersList = new ArrayList<BoothVoterVO>(0);
		
		Connection conn = null;
		Statement stmt = null;
		
		Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/dakavara_pa","root","root");
        stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("select sno,cid,part,serno from st where serno != 0 and voter_id is null");
        
        while(rs.next()) 
        {
        	BoothVoterVO b1 = new BoothVoterVO();
        	b1.setSno(rs.getLong("sno")); 
        	b1.setConstituencyId(rs.getLong("cid")); 
        	b1.setPartNo(rs.getString("part")); 
        	b1.setSerialNo(rs.getLong("serno")); 
        	votersList.add(b1);
        }
        
        
		for(BoothVoterVO bv : votersList)
		{
			try{
			BoothPublicationVoter bpv = boothPublicationVoterDAO.getVoterBySerialNo(bv.getConstituencyId(),bv.getPartNo(),bv.getSerialNo());
			System.out.println("ID --> "+bv.getSno());
			if(bpv != null)
			{
				bv.setVoterId(bpv.getVoter().getVoterId());
				bv.setVoterCardNo(bpv.getVoter().getVoterIDCardNo());
				sb.append("SNO - "+"\t"+bv.getSno()+"\tCID - \t"+bv.getConstituencyId()+"\tPart No -\t"+bv.getPartNo()+"\tSer No\t"+bv.getSerialNo()+"\tVID - \t"+bpv.getVoter().getVoterId()+"\tCard - \t"+bpv.getVoter().getVoterIDCardNo()+"\tAge - \t"+bpv.getVoter().getAge()+"\tGender - \t"+bpv.getVoter().getGender()+"\n");
				
				String uq = "update st set voter_id = "+bv.getVoterId()+", card_no = '"+bv.getVoterCardNo()+"' where sno = "+bv.getSno();
				stmt.executeUpdate(uq);
			}
			else
			{
				sb.append("SNO - "+"\t"+bv.getSno()+"\tCID - \t"+bv.getConstituencyId()+"\tPart No -\t"+bv.getPartNo()+"\tSer No\t"+bv.getSerialNo()+"\tVID - \tN/A\tCard - \tN/A\tAge - \tN/A\tGender - \tN/A\n");
			}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		outwriter.write(sb.toString());
        outwriter.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/
	
	/*public void testGetVoterBySerialNo()
	{
		try{
		File resultFile  = new File("D:\\kamal\\result.txt");
		BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
		StringBuilder sb = new StringBuilder();
		List<BoothVoterVO> votersList = new ArrayList<BoothVoterVO>(0);
		
		Connection conn = null;
		Connection conn2 = null;
		Statement stmt = null;
		
		Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/dakavara_pa","root","root");
        conn2 = DriverManager.getConnection("jdbc:mysql://74.208.7.129:3372/dakavara_pa","devuser","devuser@123");
        stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("select sai_id,voter_id from st_temp");
        
        while(rs.next()) 
        {
        	
        }
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/
	
	/*public void test()
	{
		try{
		List<Object[]> list = boothPublicationVoterDAO.getlist1();
		int i = 0;
		File resultFile = new File("F:\\Kamal\\kamal.txt");
		BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
		StringBuilder sb = new StringBuilder();

		for(Object[] params : list)
		{
			List<Object[]> list2 = boothPublicationVoterDAO.getlist2((Long)params[0],params[1].toString());
			System.out.println(++i+" -->\t"+list2.get(0)[0].toString()+"\t"+list2.get(0)[1].toString()+"\t"+list2.get(0)[2].toString()+"\t"+list2.get(0)[3].toString()+"\t"+list2.get(0)[4].toString()+"\t"+params[1].toString()+"\t"+params[2].toString());
			sb.append(i+" -->\t"+list2.get(0)[0].toString()+"\t"+list2.get(0)[1].toString()+"\t"+list2.get(0)[2].toString()+"\t"+list2.get(0)[3].toString()+"\t"+list2.get(0)[4].toString()+"\t#"+params[1].toString()+"\t"+params[2].toString()+"\n");
		}
		
		i = 0;
		for(Object[] params : list)
		{
			List<Object[]> list2 = boothPublicationVoterDAO.getlist3((Long)params[0],params[1].toString());
			System.out.println(++i+" -->\t"+list2.get(0)[0].toString()+"\t"+list2.get(0)[1].toString()+"\t"+list2.get(0)[2].toString()+"\t"+list2.get(0)[3].toString()+"\t"+list2.get(0)[4].toString()+"\t"+params[1].toString()+"\t"+params[2].toString());
			sb.append(i+" -->\t"+list2.get(0)[0].toString()+"\t"+list2.get(0)[1].toString()+"\t"+list2.get(0)[2].toString()+"\t"+list2.get(0)[3].toString()+"\t"+list2.get(0)[4].toString()+"\t#"+params[1].toString()+"\t"+params[2].toString()+"\n");
		}
		outwriter.write(sb.toString());
		outwriter.close();
		}
		catch (Exception e) {
		}
	}*/
	
	/*public void test()
	{
		try{
		List<Long> cIds = new ArrayList<Long>(0);
		cIds.add(108l);	cIds.add(109l);	cIds.add(111l);	cIds.add(112l);	cIds.add(113l);	cIds.add(114l);
		cIds.add(116l);	cIds.add(117l);	cIds.add(120l);	cIds.add(121l);	cIds.add(122l);	cIds.add(124l);
		cIds.add(125l);	cIds.add(127l);	cIds.add(129l);	cIds.add(133l);	cIds.add(134l);	cIds.add(135l);
		cIds.add(136l);	cIds.add(137l);	cIds.add(138l);	cIds.add(140l); cIds.add(141l);	cIds.add(352l);
		cIds.add(353l);	cIds.add(354l);	cIds.add(355l); cIds.add(356l);	cIds.add(357l);	cIds.add(358l);
		cIds.add(359l);	cIds.add(360l);	cIds.add(361l);	cIds.add(368l);
		List<String> list = new ArrayList<String>();
		for(Long cid : cIds)
		{
			System.out.println("Constituency Id --> "+cid);
			List<String> list2 = boothPublicationVoterDAO.getVoterNamesOfAConstituency(cid,8l);
			list.addAll(list2);
			System.out.println("List Size --> "+list.size());
		}
		int i = 0;
		File resultFile = new File("F:\\Kamal\\velama.txt");
		BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
		StringBuilder sb = new StringBuilder();
		Map<String,Integer> countMap = new HashMap<String, Integer>(0);
		
		for(String name : list)
		{
			try{
			System.out.println("Name --> "+name);
			String[] arr = name.trim().split(" ");
			for(String str : arr)
			{
				try{
					System.out.println("Str --> "+str);
				if(str.trim().length() > 0)
				{
					str = str.toUpperCase();
					Integer count = countMap.get(str);
					if(count == null)
						count = 0;
					count++;
					countMap.put(str,count);
				}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for(Map.Entry<String,Integer> entry : countMap.entrySet())
		{
			if(entry.getValue() > 50)
			{
				System.out.println(++i+"\t"+entry.getKey()+"\t"+entry.getValue());
				sb.append(i+"\t"+entry.getKey()+"\t"+entry.getValue()+"\n");
			}
		}
		outwriter.write(sb.toString());
		outwriter.close();
		}
		catch (Exception e) {
		}
	}*/
	
	
	/*public void testgetPdfsForVotersAddress()
	{
		List<Object[]> values = boothPublicationVoterDAO.getPdfsForVotersAddress();
		if(values != null && values.size() > 0 )
		{
			System.out.println(values.size());
			StringBuilder sb  = new StringBuilder();
			
			
			int i =0;
			int k = 0;
			for (Object[] parms : values) {
				if(k == 0)
				sb.append("<table  style='font-family: arial; font-size: 7px;'>");
				//System.out.println(k++);
				i++;
				k++;
				if(i == 0)
				{
					sb.append("<tr>");
				}
				
				sb.append("<td style='width: 200px; height: 140px;'>");
				sb.append("<div style='border: 1px solid;'><p>To,<p>");
				sb.append("<p style='margin-left: 10px;'>"+replaceSpecialChars(parms[7].toString())+"<p>");
				String name = "";
				if(parms[12].toString().equalsIgnoreCase("Father") || parms[12].toString().equalsIgnoreCase("Mother"))
				{
					if(parms[8].toString().trim().equalsIgnoreCase("M"))
					{
						name = "S/O , " + replaceSpecialChars(parms[11].toString());
					}
					else
					{
						name = "D/O , " + replaceSpecialChars(parms[11].toString());
					}
				}
				
				else if(parms[12].toString().equalsIgnoreCase("Husband"))
				{
					name = "W/O , "  + replaceSpecialChars(parms[11].toString());
				}
				else
				{
					name = "C/O , "  + replaceSpecialChars(parms[11].toString());
				}
				sb.append("<p style='margin-left: 10px;'>"+name+"<p>");
				sb.append("<p style='margin-left: 10px;'>H.NO : "+parms[10].toString()+"</p>");
				if(parms[2] != null)
				{
					sb.append("<p style='margin-left: 10px;'>Village : "+parms[2].toString()+"<p>");
				}
				sb.append("<p style='margin-left: 10px;'>Panchayat : "+parms[1].toString()+"<p>");
				sb.append("<p style='margin-left: 10px;'>Mandal : "+parms[0].toString()+"<p>");
				sb.append("<p style='margin-left: 10px;'>District : "+parms[14].toString()+"<p>");
				sb.append("<p style='margin-left: 10px;'>Andhra Pradesh<p>");
				sb.append("</div></td>");
				sb.append("<td></td>");
				if(i == 4)
				{
					sb.append("</tr>");
					i = 0;
				}
				if(k==24)
				{
					k = 0;
					sb.append("</table></br></br></br>");
				}
			}
			sb.append("</table>");
			//System.out.println(sb.toString());
			try{
			BufferedWriter outwriter = new BufferedWriter(new FileWriter(new File("C:\\Data.html")));
			outwriter.write(sb.toString());
			outwriter.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	*/
	
	
	public ICandidateResultDAO getCandidateResultDAO() {
		return candidateResultDAO;
	}



	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}



	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}



	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}



	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}



	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}



	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}



	/*public void testgetPdfsForVotersList()
	{
		List<Long> boothIds = boothDAO.getBoothIdByConstituencyPublication(282l,10l);
		for (Long boothId : boothIds) {
		
			List<Object[]> voterTeluguNames = boothPublicationVoterDAO.getVoterTeluguNames(boothId);
			Map<String,String> teluguNamesMap = new HashMap<String, String>();
			if(voterTeluguNames != null && voterTeluguNames.size() > 0)
			{
				for (Object[] objects : voterTeluguNames)
				{
					if(objects[0] != null && objects[1] != null)
					{
						teluguNamesMap.put(objects[0].toString(), objects[1].toString());
					}
					
				}
			}
			List<Object[]> values = boothPublicationVoterDAO.getVoterDetaildsByBoothWise(boothId);
			if(values != null && values.size() > 0 )
			{
				System.out.println(values.size());
				StringBuilder sb  = new StringBuilder();
				
				
				int i =0;
				int k = 0;
				sb.append("<html>");
				sb.append("<head>");
				sb.append("<meta content='text/html; charset=utf-8' http-equiv='Content-Type'>");
				sb.append("<title></title>");
				sb.append("</head>");
				sb.append("<body>");
				String partNo = "";
				for (Object[] parms : values) {
					partNo = parms[0].toString();
					if(k == 0)
					sb.append("<table  style='font-family: arial; font-size: 7px;'>");
					//System.out.println(k++);
					i++;
					k++;
					if(i == 0)
					{
						sb.append("<tr>");
					}
					
					sb.append("<td style='width: 200px; height: 140px;'>");
					sb.append("<div style='border: 1px solid;'><p style='margin-left: 10px;'>Polling Date : 22-12-1989<p>");
					sb.append("<p style='margin-left: 10px;'>"+parms[1]+"<p>");
					String voterName = teluguNamesMap.get(parms[3].toString());
					if(voterName == null)
					{
						sb.append("<p style='margin-left: 10px;'>Name : <b>"+replaceSpecialChars(parms[2].toString())+"</b><p>");
					}
					else
					{
						sb.append("<p style='margin-left: 10px;'>Name : <b>"+voterName+"</b><p>");
					}
					sb.append("<p style='margin-left: 10px;'> Voter Card NO : "+parms[3].toString()+"<p>");
					sb.append(" <p style='margin-left: 10px;'>   Part NO : "+parms[0].toString()+"");
					sb.append("  &nbsp&nbsp &nbsp&nbsp  Serial NO : "+parms[4].toString()+"<p>");
					if(parms[5].toString().equalsIgnoreCase("F"))
					{
						sb.append("<p style='margin-left: 10px;'>Gender : Female   ");
					}
					else
					{
						sb.append("<p style='margin-left: 10px;'>Gender : Male ");
					}
					sb.append(" &nbsp&nbsp  Age : "+parms[6].toString()+     "  </p> ");
					
					sb.append("<p style='margin-left: 10px;'>H.NO : "+parms[7].toString()+"<p>");
					sb.append("<p style='margin-left: 10px;'> Relation Type : "+parms[8].toString()+"<p>");
					sb.append("<p style='margin-left: 10px;'>Relation Name : "+parms[9].toString()+"<p>");
					
					sb.append("</div></td>");
					sb.append("<td></td>");
					if(i == 4)
					{
						sb.append("</tr>");
						i = 0;
					}
					if(k==24)
					{
						k = 0;
						sb.append("</table></br></br></br>");
					}
				}
				sb.append("</table>");
				sb.append("</body>");
				sb.append("</html>");
				try{
		
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Kuppam\\Kuppam_294_Booth "+partNo+".html"),"UTF-8"));
				out.write(sb.toString());
				out.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}*/
	
	/*public void testgetPdfsForVotersList()
	{
		List<Long> boothIds = boothDAO.getBoothIdByConstituencyPublication(282l,10l);
		for (Long boothId : boothIds) {
		
			List<Object[]> voterTeluguNames = boothPublicationVoterDAO.getVoterTeluguNames(boothId);
			Map<String,String> teluguNamesMap = new HashMap<String, String>();
			if(voterTeluguNames != null && voterTeluguNames.size() > 0)
			{
				for (Object[] objects : voterTeluguNames)
				{
					if(objects[0] != null && objects[1] != null)
					{
						teluguNamesMap.put(objects[0].toString(), objects[1].toString());
					}
					
				}
			}
			List<Object[]> values = boothPublicationVoterDAO.getVoterDetaildsByBoothWise(boothId);
			if(values != null && values.size() > 0 )
			{
				System.out.println(values.size());
				StringBuilder sb  = new StringBuilder();
				//Date date = new Date();
				//SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
				//System.out.println(sdf.format(date));
				
				int i =0;
				int k = 0;
				sb.append("<html>");
				sb.append("<head>");
				sb.append("<meta content='text/html; charset=utf-8' http-equiv='Content-Type'>");
				sb.append("<title></title>");
				sb.append("</head>");
				sb.append("<body>");
				
				String partNo = "";
				for (Object[] parms : values) {
					partNo = parms[0].toString();
					if(k == 0)
					sb.append("<table  style='font-family: arial; font-size: 7px;'>");
					//System.out.println(k++);
					i++;
					k++;
					if(i == 0)
					{
						sb.append("<tr>");
					}
					
					sb.append("<td style='width: 400px; height: 140px;'>");
					//sb.append("<div style='border: 1px solid;'><p style='margin-left: 10px;'>General Election To House of People/ Andhra Pradesh Legislative Assembly 2014<p>");
					sb.append("<div style='border: 1px solid;'><p style='margin-left: 123px;'><b>VOTER SLIP</b><p>");
					sb.append("<p style='margin-left: 10px;'>No and Name of PC/AC : 294-KUPPAM / 23-CHITTOOR<p>");
					sb.append("<p style='margin-left: 10px;'>Part No : <b>"+partNo+"</b>");
					sb.append(" &nbsp&nbsp &nbsp&nbsp Voter Serial Number : <b>"+parms[4].toString()+"</b> </p>");
					sb.append("<p style='margin-left: 10px;'>House No : "+parms[7].toString() +"<p>");
					String voterName = teluguNamesMap.get(parms[3].toString());
					if(voterName == null)
					{
						sb.append("<p style='margin-left: 10px;'>Name : <b>"+replaceSpecialChars(parms[2].toString()) +"</b><p>");
					}
					else
					{
						sb.append("<p style='margin-left: 10px;'>Name : <b>"+voterName+"</b><p>");
					}
					
					sb.append("<p style='margin-left: 10px;'>sex :"+parms[5].toString() +"");
					sb.append(" &nbsp&nbsp &nbsp&nbsp EPIC NO : <b>"+parms[3].toString() +"</b> </p>");
					sb.append("<p style='margin-left: 10px;'>"+parms[8].toString() +"'s Name : <b>"+parms[9].toString() +"</b> <p>");
					sb.append("<p style='margin-left: 10px;'>Polling Station Name :  "+parms[1].toString() +" <p>");
					sb.append("<p style='margin-left: 10px;'>Polling Date,Day and Time : <b>7 th May 2014,Thursday ( 7.00 AM to 6.00 PM ) <b> <p>");
					sb.append("<p style='margin-left: 10px;'><p>");
					//sb.append("<p style='margin-left: 180px;'>Signature and Stamp of<p>");
					//sb.append("<p style='margin-left: 180px;'>Electrol Registration Officer<p>");
					sb.append("</div></td>");
					sb.append("<td></td>");
					if(i == 2)
					{
						sb.append("</tr>");
						i = 0;
					}
					if(k==12)
					{
						k = 0;
						sb.append("</table></br></br></br>");
					}
				}
				sb.append("</table>");
				sb.append("</body>");
				sb.append("</html>");
				try{
		
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Kuppam\\Kuppam_294_Booth "+partNo+".html"),"UTF-8"));
				out.write(sb.toString());
				out.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	
	*/
	/*public void testGetTotalVotersBoothWise(){
		List<Object[]> list=boothPublicationVoterDAO.getTotalVotersOfBoothByConstituencyId(228l, 10l);
		System.out.println(list.size());
	}
	*/
	public String replaceSpecialChars(String str)
  	{
  		try{
  			String newStr = "";
  			
  			char[] charArray = str.toCharArray();
  			for(Character C : charArray)
			{
				if(Character.isLetter(C) || C.toString().equals(" "))
					newStr = newStr+C.toString();
			}
  			return newStr.trim();
  		}catch(Exception e)
  		{
  			return str;
  		}
  	}
	
	/*public ICriticalPanchayatsDAO getCriticalPanchayatsDAO() {
		return criticalPanchayatsDAO;
	}



	public void setCriticalPanchayatsDAO(
			ICriticalPanchayatsDAO criticalPanchayatsDAO) {
		this.criticalPanchayatsDAO = criticalPanchayatsDAO;
	}



	public void testGetMaxSerialNoOfABooth()
	{
		Long serialNo = boothPublicationVoterDAO.getMaxSerialNoOfABooth(371092l);
		System.out.println(serialNo);
		VoterVO voterVo = new VoterVO();
		voterVo.setConstituencyId(181l);
		voterVo.setPublicationDateId(10l);
		voterVo.setType("Normal");
		voterVo.setYear(2009l);
		 List<Object[]> list = criticalPanchayatsDAO.getCriticalPanchayatBoothHnos(voterVo,0,1000);
		 System.out.println(list.size());
		 for(Object[] params : list)
		 {
			 System.out.println(params[0]);
		 }
		
	}*/
	
	/*public void testResult()
	{
		List<Long> partyIds = new ArrayList();
		partyIds.add(72l);
		partyIds.add(163l);
		partyIds.add(265l);
		partyIds.add(269l);
		partyIds.add(362l);
		partyIds.add(429l);
		partyIds.add(872l);
		partyIds.add(886l);
		getPartyWiseComperassionResult(1l,258l,partyIds);
	}
	
	public List<BasicVO> getPartyWiseComperassionResult(Long stateId,Long electionId,List<Long> partyIds)
	{
		List<BasicVO> returnList = null;
		try 
		{
			List<Object[]> result = candidateResultDAO.getElectionResultsForSelection(electionId,stateId,partyIds,1l);
			if(result != null && result.size() > 0)
			{
				
				Map<Long,List<BasicVO>> constituencyWiseMap = new HashMap<Long, List<BasicVO>>();
				Map<Long,Long> constituencyNosMap = new HashMap<Long, Long>();
				Map<Long,String> constituencyNameMap = new HashMap<Long, String>();
				for (Object[] objects : result)
				{
					List<BasicVO> constituencyWiseList = constituencyWiseMap.get((Long)objects[0]);
					if(constituencyWiseList == null)
					{
						constituencyWiseList = new ArrayList<BasicVO>();
						constituencyWiseMap.put((Long)objects[0], constituencyWiseList);
					}
					BasicVO basicVO = new BasicVO();
					basicVO.setId((Long)objects[0]);//constituency Id
					basicVO.setName(objects[1] != null ? objects[1].toString() : "");//constituency Name
					basicVO.setMandalName(objects[2] != null ? objects[2].toString() : "");//District Name
					basicVO.setCount(objects[3] != null ?Double.valueOf(objects[3].toString()).longValue() : 0l);//gained Votes
					basicVO.setDescription(objects[4] != null ? objects[4].toString() : "0");//votes percentage
					basicVO.setLevelId((Long)objects[5]);//party Id
					basicVO.setDescription(objects[6] != null ? objects[6].toString() : "");//party Name
					basicVO.setCasteName(objects[7] != null ? objects[7].toString() : "");//candidate
					constituencyWiseList.add(basicVO);
				}
				List<Object[]> constituencyDetails = delimitationConstituencyDAO.getConstituencyNoByState(stateId,2009l,1l,"ac");
				if(constituencyDetails != null && constituencyDetails.size() > 0)
				{
					for (Object[] objects : constituencyDetails)
					{
						constituencyNosMap.put((Long)objects[0], (Long)objects[1]);
						constituencyNameMap.put((Long)objects[0], objects[2].toString());
					}
				}
				List<Object[]> parties = partyDAO.getPartyShortNameByIds(partyIds);
				
				List<Long> constituenctyIds = new ArrayList<Long>(constituencyNosMap.keySet());
				if(constituenctyIds != null && constituenctyIds.size() > 0)
				{
					returnList = new ArrayList<BasicVO>();
					for (Long constituencyId : constituenctyIds)
					{
						BasicVO VO = new BasicVO();
						VO.setId(constituencyId);
						VO.setHamletId(constituencyNosMap.get(constituencyId));
						VO.setName(constituencyNameMap.get(constituencyId));
						List<BasicVO> constiwiseList = constituencyWiseMap.get(constituencyId);
						if(constiwiseList != null && constiwiseList.size() > 0)
						{
							List<BasicVO> partiesList = new ArrayList<BasicVO>();
							if(parties != null && parties.size() > 0)
							{
								for (Object[] objects : parties)
								{
									BasicVO partyVO = new BasicVO();
									partyVO.setId((Long)objects[0]);
									partyVO.setName(objects[1].toString());
									partyVO.setCount(0l);
									partyVO.setPerc(0.0);
									partiesList.add(partyVO);
								}
							}
							for (BasicVO subVO : constiwiseList)
							{
								VO.setMandalName(subVO.getMandalName());
								for(BasicVO partyVO : partiesList)
								{
									if(partyVO.getId().longValue() == subVO.getLevelId().longValue())
									{
										partyVO.setCount(subVO.getCount());
										partyVO.setDescription(subVO.getDescription());
									}
								}
							}
							VO.setSelectedCasteDetails(partiesList);
						}
						returnList.add(VO);
					}
				}
						
				
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}*/
	
	/*public void test(){
		List<Long> voterIds = new ArrayList<Long>();
		voterIds.add(6979788l);
		voterIds.add(6979790l);
		
		List<Long> q = boothPublicationVoterDAO.getBoothIdsOfVoterIds(voterIds,8l);
		q.size();
	}*/
	
	
	
	public void testgetBoothWiseCasteDetails(){

		List<Object[]> casteInfo  = boothPublicationVoterDAO.getBoothWiseCasteDetails(122941L);
			System.out.println(casteInfo.size());
	}
	
	
	
}
