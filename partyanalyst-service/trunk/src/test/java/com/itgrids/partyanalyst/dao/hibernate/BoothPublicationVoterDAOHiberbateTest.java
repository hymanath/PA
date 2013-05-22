package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothPublicationVoterDAOHiberbateTest extends BaseDaoTestCase {

	private IBoothPublicationVoterDAO boothPublicationVoterDAO;

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

	/*
	 * public void testGetCastWiseCount()
	 * 
	 * { List<Object[]> list = boothPublicationVoterDAO.getCastWiseCount(1l,
	 * "panchayat", 1l, 7l); System.out.println(list.size()); for(Object[]
	 * data:list){ System.out.println(data[0]+" "+data[1]); } }
	 */

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
	
	public void testGetCatstesForBooths()
	{
		List<Long> boothIds = new ArrayList<Long>(0);
		boothIds.add(122792l);
		List<Object[]> list = boothPublicationVoterDAO.getCatstesForBooths(1l, boothIds, 8l);
		System.out.println(list.size());
	}
}
