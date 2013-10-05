package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothDAOHibernateTest extends BaseDaoTestCase{
	
	private IBoothDAO boothDAO;

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	
	
	
	/*public void testFindTehsilsByConstituencyElectionScopeAndElection(){
		List<Tehsil> mandals = boothDAO.findTehsilsByElectionAndConstituency("2009", new Long(232));
		assertEquals(mandals.size(), 7);
	}*/
	
	/*public void testFindByConstituencyAndElectionYear(){
		List list = boothDAO.findByConstituencyAndElectionYear(231l, 2009l);
		System.out.println((Long)list.get(0));
	}*/
	
	/*public void testFindByPartNoConstituencyIdAndYear(){
		Long count = (Long)boothDAO.findByPartNoConstituencyIdAndYear(231l, 2009l, "30").get(0);
		System.out.println(count);
	}*/

	/*public void testFindbyConstituencyIdPartnoAndElectionYear() {
		List<Booth> booths = boothDAO.findbyConstituencyNameDistrictIdPartnoAndElectionYear("kavali", 19l, 2009l, "40");
	}*/
	
/*	public void testFindBoothInfoByConstituencyIdAndYear(){
		List list = boothDAO.findBoothInfoByConstituencyIdAndYear(232l, 2009l);
		System.out.println(list.size());
	}*/
	
	/*public void testUpdateLocalBodyInfoByBoothIdsAndWardId(){
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(9000l);
		boothIds.add(9001l);
		int i = boothDAO.updateLocalBodyInfoByBoothIdsAndWardId(7700l, boothIds);
		setComplete();
		System.out.println(i);
	}*/
	
	/*public void testGetBoothsByIds(){
		List<Long> boothIds = new ArrayList<Long>();
		boothIds.add(9000l);
		boothIds.add(9001l);
		List<Booth> booths = boothDAO.findByBoothIds(boothIds);
		System.out.println(booths.size());
	}*/

	/*public void testFindVotersInfoForConstituencyInAnYearByLocalElectionBody(){
		List list = boothDAO.findVotersInfoForConstituencyInAnYearByLocalElectionBody(232l, 2009l, "'"+IConstants.GREATER_ELECTION_TYPE+"'");
		System.out.println(list.size());
		for(int i=0; i<list.size(); i++)
			System.out.println(((Object[])list.get(i))[0]+"\t"+((Object[])list.get(i))[1]+"\t"+((Object[])list.get(i))[2]+"\t"+((Object[])list.get(i))[3]+"\t"+((Object[])list.get(i))[4]+"\t"+((Object[])list.get(i))[5]+"\t"+((Object[])list.get(i))[6]);
	}*/
	
	/*public void testFindAssemblyConstituenciesDetailsForParliament(){
		List list = boothDAO.findAssemblyConstituenciesDetailsForParliament("223,241,232,233,340,341,238", "2009");
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]+"\t"+values[3]);
	}*/
	
	/*public void testmandalInfo()
	{
		List votersList  = boothDAO.findVoterInformationByMandalIdsAndDelimitationYear("849,851,852,853,854,859,860,862","2004", 235l);
		List<VotersInfoForMandalVO> votersInfoForMandalList = new ArrayList<VotersInfoForMandalVO>();
		for(int j = 0;j<votersList.size();j++)
		{
			VotersInfoForMandalVO votersInfo = new VotersInfoForMandalVO();
			
			Object[] vObj = (Object[]) votersList.get(j);
			votersInfo.setMandalId( vObj[0].toString());
			votersInfo.setMandalName(vObj[1].toString());
			votersInfo.setTotalMaleVoters(vObj[2].toString());
			votersInfo.setTotalFemaleVoters(vObj[3].toString());
			votersInfo.setTotalVoters(vObj[4].toString());
			
			votersInfoForMandalList.add(votersInfo);
		}
		
		for(VotersInfoForMandalVO obj : votersInfoForMandalList)
		{
			System.out.println(obj.getMandalId()+" - "+obj.getMandalName()+" - "+obj.getTotalVoters()+" - "+obj.getTotalMaleVoters()+" - "+obj.getTotalMaleVoters());
		}
	}*/
	
	/*public void testFindbyConstituencyNameDistrictIdAndElectionYear(){
		List list = boothDAO.findbyConstituencyNameDistrictIdAndElectionYear("Kavali", 19l, 2009l);
	}*/

	/*public void testFindTehsilsByElectionAndConstituency(){
		List list = boothDAO.findTehsilsByElectionAndConstituency("2009", 315l);
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]);
	}*/
	
	/*public void testFindLocalBodiesByElectionAndConstituency(){
		List list = boothDAO.findLocalBodiesByElectionAndConstituency("2009", 232l, "'"+IConstants.MUNCIPLE_ELECTION_TYPE+"'");
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]);
	}*/
	
	/*public void testFindLocalBodyWardsByElectionAndConstituency(){
		List list = boothDAO.findLocalBodyWardsByElectionAndConstituency("2009", 315l, "'"+IConstants.GREATER_ELECTION_TYPE+"'");
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[0]+"\t"+values[1]+"\t"+values[2]);
	}*/
	
	/*public void testFindByConstituencyElectionAndPartNo(){
		List<Long> list = boothDAO.findByConstituencyElectionAndPartNo(232l, 2009l, "1,2,3");
		System.out.println(list.size());
	}
	*/
	
	/*public void testFindBoothsInfoForAMandalByConstituencyAndYear(){
		List list = boothDAO.findBoothsInfoForAMandalByConstituencyAndYear(835l, 2009l, 232l);
		System.out.println(list.size());
	}*/
	
/*	public void testFindBoothsInfoForALocalElectionBodyByConstituencyAndYear(){
		List list = boothDAO.findBoothsInfoForALocalElectionBodyByConstituencyAndYear(488l, 2009l, 232l);
		System.out.println(list.size());
	}*/
	
	/*public void testFindBoothsInfoForALocalBodyWardByConstituencyAndYear(){
		List list = boothDAO.findBoothsInfoForALocalBodyWardByConstituencyAndYear(18381l, 2009l, 315l);
		System.out.println(list.size());
	}*/
	
	/*public void testFindBoothsInLocalElectionBodies(){
		List list = boothDAO.findBoothsInLocalElectionBodies("458, 495, 488", "1,294, 232", 2009l );
		System.out.println(list.size());
		for(Object[] values:(List<Object[]>)list)
			System.out.println("id:"+values[0]+"\t"+"Part No:"+values[1]+"\t"+"const Id "+values[2]+"\t"+"local id:"+values[3]);
	}*/
	
	/*public void testUpdateVillagesCoveredInfoInAConstituency() {
		int update = boothDAO.updateVillagesCoveredInfoInAConstituency(232l, "", "", 2009l);
		System.out.println(update);
	}*/
	
	/*public void testgetPollingStationByConstituencyId()
	{
	 List<Booth> list = boothDAO.getPollingStationByConstituencyId(232l);
	 System.out.println(list.size());
	 
	 for (Booth booth : list) {
		 
		 System.out.println(booth.getLocation());
		
	}
	}
	*/
	/*public void testgetPollingStationByMandalId()
	{
	 List<Booth> list = boothDAO.getPollingStationByMandalId(844l,232l);
	 System.out.println(list.size());
	 
	 for (Booth booth : list) {
		 
		 System.out.println(booth.getPartNo());
		
	}
	}*/
	
	/*public void testgetPublicationDetailsBasedOnConstituency()
	{
	 List<Object[]> list = boothDAO.getPublicationDetailsBasedOnConstituency(232l);
	 System.out.println(list.size());
	}*/
	
	/*public void testGetBoothsInAMandalIdByPublication()
	{
		List<Object[]> params = boothDAO.getBoothsInAMandalByPublication(999l,6l);
		System.out.println(params.size());
	}*/
	
	/*public void testGetBoothsInAConstituencyByPublication()
	{
		List<Object[]> list = boothDAO.getBoothsInAConstituencyByPublication(78l,6l);
		System.out.println(list.size());
	}
	
	
	public void testgetBoothDetailsByBoothId(){
		List<Booth> list=boothDAO.getBoothDetailsByBoothId(207l);
		System.out.println(list.size());
		
		
	}*/
	
	/*public void testGetBoothInfo(){
		List<Object[]> list=boothDAO.getBoothInfo(7l,78l,"86");
		System.out.println(list.size());
		
		
	}
	*/
	/*public void testGetTotalaVotesByBoothIds()
	{
		List<Long> boothIds = new ArrayList<Long>(0);
		
		boothIds.add(204l);
		boothIds.add(205l);
		boothIds.add(206l);
		boothIds.add(207l);
		boothIds.add(208l);
		boothIds.add(209l);
		boothIds.add(210l);
		boothIds.add(211l);
		boothIds.add(212l);
		boothIds.add(213l);
		boothIds.add(214l);
		boothIds.add(215l);
		boothIds.add(216l);
		boothIds.add(217l);
		boothIds.add(218l);
		boothIds.add(219l);
		
		List<Long> data = boothDAO.getTotalaVotesByBoothIds(boothIds);
		if(!data.isEmpty())
		System.out.println(data.get(0));
	}*/
	
	/*public void testgetPanchayatiesCount()
	{
	List<Object[]> list = boothDAO.getBoothsInAMunicipality(20l,7l);
	System.out.println(list.size());
	}
	*/
	
	/*public void testgetBoothIdsByPanchayatIds()
	{
		List<Long> panchayatIds = new ArrayList<Long>(0);
		panchayatIds.add(1l);
		List<Object[]> list = boothDAO.getBoothIdsByPanchayatIdsInAPublication(panchayatIds,7l);
		
		if(list != null && list.size() > 0)
		{
			for(Object[] booths : list)
				System.out.println(booths[0]+" "+booths[1]);
		}
		
	}*/
	
	/*public void testCheckForUrbanBooth()
	{
		List<Long> list = boothDAO.checkForUrbanBooth(121904l, 7l);
		System.out.println(list.size());
	}
	*/
	/*public void testgetBoothIdsInLocalBodiesForAPublication()
	{
		List<Long> localEleBodyIdsList = new ArrayList<Long>(0);
		localEleBodyIdsList.add(117l);
		List<Object[]> list = boothDAO.getBoothIdsInLocalBodiesForAPublication(localEleBodyIdsList,8l,341l);
		
		if(list != null && list.size() > 0)
		{
			System.out.println(list.size());
			for(Object[] booths : list)
				System.out.println(booths[0]+" "+booths[1]);
		}
		
	}*/
	
	/*public void testGetBoothIdsByPanchayatIdsListOrLocalEleBodyIdsList()
	{
		List<Long> panchayatIdsList = new ArrayList<Long>(0);
		panchayatIdsList.add(83l);
		List<Long> l = boothDAO.getBoothIdsByPanchayatIdsListOrLocalEleBodyIdsList(IConstants.LOCALELECTIONBODY, 7l, panchayatIdsList);
		System.out.println(l);
	}*/
	
	/*public void testGetWardsByLocalElecBodyIds()
	{
		List<Long> panchayatIdsList = new ArrayList<Long>(0);
		panchayatIdsList.add(83l);
		List<Object[]> l = boothDAO.getWardsByLocalElecBodyIds(panchayatIdsList, 7l);
		for(Object[] ward:l)
			System.out.println(ward[0]+"   "+ward[1].toString());
	}*/
	
	/*public void testfindBoothsInfoForALocalBodyWardByConstituencyAndPublicationId(){
		List<Object[]> l =boothDAO.findBoothsInfoForALocalBodyWardByConstituencyAndPublicationId(1805l,347l,8l);
		for(Object[] ward:l)
			System.out.println(ward[0]+"   "+ward[1].toString());
	}*/
	
	/*public void testgetBoothsForWard()
	{
		
		List<Object[]> l = boothDAO.getBoothsForWard(1805l, 8l);
		for(Object[] ward:l)
			System.out.println(ward[0]+"   "+ward[1].toString());
	}	*/

	
	/*public void testgetWardIdsByLocalEleBodyIdsList()
	{
		List<Long> localEleBodyIds = new ArrayList<Long>(0);
		localEleBodyIds.add(20l);
		List<Long> wardIds = boothDAO.getWardIdsByLocalEleBodyIdsList(localEleBodyIds, 8l);
		System.out.println(wardIds);
	}*/
	
	/*public void testGetBoothsCountByPublicationId()
	{
		List<Long> wardIds = boothDAO.getBoothsCountByPublicationId("mandal",844l, 7l);
		System.out.println(wardIds);
	}*/
	
	/*public void testGetBoothIdsAndPartNosOfAConstituencyInAPublication()
	{
		List<Object[]> list = boothDAO.getBoothIdsAndPartNosOfAConstituencyInAPublication(232l,8l);
		System.out.println(list.size());
		
		for(Object[] params : list)
			System.out.println(params[0]+"\t"+params[1]);
	}*/
	
	/*public void testGetBoothByPartNoAndPublicationIdInAConstituency()
	{
		Booth booth = boothDAO.getBoothByPartNoAndPublicationIdInAConstituency("1",241l,8l);
		System.out.println(booth.getLocation());
	}*/
	/*public void testgetWardsByLocalElecBodyIds()
	{
		List<Long> localEleBodyIdsList = new ArrayList<Long>(0);
		localEleBodyIdsList.add(20l);
		List<Object[]> list = boothDAO.getWardsByLocalElecBodyIds(localEleBodyIdsList,8l,347l);
		
		if(list != null && list.size() > 0)
		{
			System.out.println(list.size());
			for(Object[] booths : list)
				System.out.println(booths[0]+" "+booths[1]);
		}
		
	}*/
	
	/*public void testGetBoothsInAMandalByPublicationAndConstId()
	{
		List<Object[]> list = boothDAO.getBoothsInAMandalByPublicationAndConstId(739l,7l, 207l);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
			for(Object[] params : list)
				System.out.println(params[0]+" "+params[1]);
	}*/
	
	/*public void testString()
	{
		Booth booth = boothDAO.get(3489l);
		String vill=booth.getVillagesCovered();
        String vill1=vill.replace("'"," ").replace("\"", " ").replace("\r", "");
	System.out.println(vill1);
		
	}*/
	/*public void testgetBoothsInAPanchayatByPublicationId()
	{
		List<Object[]> list = boothDAO.getBoothsInAPanchayatByPublicationId(1407l, 7l);
		for(Object[] params : list)
			System.out.println(params[0]+" "+params[1]);
	}*/
	
	
	/*public void testgetPartNoByPanchayatIdAndPublicationDateIdsList()
	{
		List<Long> publicationDateIdsList = new ArrayList<Long>(0);
		publicationDateIdsList.add(7l);
		publicationDateIdsList.add(8l);
		
		List<String> boothIds = boothDAO.getPartNoByPanchayatIdAndPublicationDateIdsList(1l, publicationDateIdsList, 232l, "panchayat");
		if(boothIds != null && boothIds.size() > 0)
			for(String id : boothIds)
				System.out.println(id);
		
		
	}*/
	
/*	public void testgetWardsByLocalElecBodyIdAndPublicationIdsList()
	{
		List<Long> publicationDateIdsList = new ArrayList<Long>(0);
		//publicationDateIdsList.add(7l);
		publicationDateIdsList.add(8l);
		List<Long> wardIds = boothDAO.getWardsByLocalElecBodyIdAndPublicationIdsList(20l, publicationDateIdsList, 347l);
		System.out.println(wardIds.size());
		
	}*/
	
	
	/*public void testGetBoothIdsByLocalValuesList()
	{
		List<Long> publicationDateIdsList = new ArrayList<Long>(0);
		//publicationDateIdsList.add(7l);
		publicationDateIdsList.add(8l);
		List<Long> boothIdsList = boothDAO.getBoothIdsByLocalValuesList(IConstants.MANDAL, 844l, 232l, publicationDateIdsList);
		System.out.println(boothIdsList.size());
		for(Long boothId:boothIdsList)
			System.out.println(boothId);
		
	}*/
	
	/*public void testGetPartNosByBoothIdsList()
	{
		List<Long> boothIdsList = new ArrayList<Long>(0);
		boothIdsList.add(121328l);
		List<String> list = boothDAO.getPartNosByBoothIdsList(299l, 8l, boothIdsList);
		for(String params : list)
			System.out.println(params);
	}*/
	/*public void testgetBoothsInAPanchayatUsingConstituencyId(){
	List<Object[]> list = boothDAO.getBoothsInAPanchayatUsingConstituencyId(16987l,8l,280l,"mandal",1060l);
	System.out.println(list.size());
	for(Object[] params : list)
		System.out.println(params[0]+" "+params[1]);
	}*/
	
	/*public void testGetBoothIdsByLocalEleBodyId()
	{
		List<Object[]> list = boothDAO.getBoothIdsByLocalEleBodyId(83l, 8l, 232l);
		System.out.println(list.size());
		for(Object[] params : list)
			System.out.println(params[0]+" "+params[1]);
	}*/
	
	/*public void testGetAllBoothsInAConstituency()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(31809l);
		ids.add(31806l);
		ids.add(31808l);
		List<Object[]> values = boothDAO.getBoothsForSelectedWards(ids,8l);
		System.out.println(values.size());
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1]);
			
		}
	}*/
	
	/*public void testGetAllBoothsInAMuncipality()
	{
		List<Object[]> values = boothDAO.getAllBoothsInAMuncipality(31l,8l);
		System.out.println(values.size());
		for (Object[] parms : values) {
			
			System.out.println(parms[0] +":"+ parms[1]);
			
		}
	}*/

	
	/*public void testGetMandalIdsListByConstituencyId()
	{
		List<Long> list = boothDAO.getMandalsListByConstituencyId(232l, 8l);
		System.out.println(list.size());
		
	}*/
	
	/*public void testGetPanchayatsListByConstituencyId()
	{
		List<Long> list = boothDAO.getPanchayatsListByConstituencyId(232l, 8l);
		System.out.println(list.size());
		
	}*/
	
	/*public void testGetMuncipalitiesListByConstituencyId()
	{
		List<Long> list = boothDAO.getMuncipalitiesListByConstituencyId(232l, 8l);
		System.out.println(list.size());
		
	}*/
	/*public void testGetBooListByConstituencyId()
	{
		List<Long> list = boothDAO.getBoothsListByConstituencyId(232l, 8l);
		System.out.println(list.size());
		
	}*/
	
	/*public void testGetWardsListByConstituencyId()
	{
		List<Long> list = boothDAO.getWardsListByConstituencyId(347l, 8l);
		System.out.println(list.size());
		
	}*/
	
	/*public void testgetBoothsByBoothIdsList()
	{
		List<Long> boothIdsList = new ArrayList<Long>(0);
		boothIdsList.add(121328l);
		List<Object[]> list = boothDAO.getBoothsByBoothIdsList(boothIdsList);
		for(Object[] params :list)
			System.out.println(params[0]+" "+params[1]);
	}*/
	
	/*public void testGetMuncipalitiesByMuncipalityIdsList()
	{
		List<Long> muncipalityIdsList = new ArrayList<Long>(0);
		muncipalityIdsList.add(83l);
		List<Object[]> list = boothDAO.getMuncipalitiesByMuncipalityIdsList(232l, 8l, muncipalityIdsList);
		for(Object[] params :list)
			System.out.println(params[0]+" "+params[1]);
	}*/
	
	/*public void testGetMuncipalitiesByMuncipalityIdsList()
	{
		List<Long> wardIdsList = new ArrayList<Long>(0);
		wardIdsList.add(31805l);
		List<Object[]> list = boothDAO.getWardsByWardIdsList(347l, 8l, wardIdsList);
		for(Object[] params :list)
			System.out.println(params[0]+" "+params[1]);
	}*/
	/*
	public void testgetPanchayatiesCountByTahsilAndConstituencyId(){
		List<Object[]> list = boothDAO.getPanchayatiesCountByTahsilAndConstituencyId(232l,844l,8l,"mandal");
		System.out.println(list.size());
		for(Object[] params :list)
		{
			System.out.println(params[0]+" "+params[1]);
		}
	}*/
	
	
	/*public void testgetBoothPartNoByBoothId()
	{
		System.out.println(boothDAO.getBoothPartNoByBoothId(1l));
	}*/
	
	/*public void testgetPanchayatsByPanchayatIdsList()
	{
		List<Long> panchayatIdsList = new ArrayList<Long>(0);
		panchayatIdsList.add(1l);
		List<Long> list = boothDAO.getPanchayatsByPanchayatIdsList(232l, 8l, panchayatIdsList);
		System.out.println(list.size());
	}*/
	
	/*public void testGetBoothsListByBoothIdsList()
	{
		List<Long> boothIdsList = new ArrayList<Long>(0);
		boothIdsList.add(1l);
		List<Object[]> list = boothDAO.getBoothsListByBoothIdsList(boothIdsList);
		System.out.println(list.size());
		for(Object[] params :list)
		 System.out.println(params[0]+" "+params[1]+" "+params[2]);
	}*/
	/*public void testgetNoOfWardsInMuncipality(){
		List<Object> list=boothDAO.getNoOfWardsInMuncipality(20l, 8l, 347l);
		System.out.println(list);
	}*/
	
	/*public void testGetPanchayatiesByMandalsListAndConstituencyId()
	{
		List<Long> mandalsList = new ArrayList<Long>(0);
		mandalsList.add(844l);
		mandalsList.add(836l);
		List<Object[]> list = boothDAO.getPanchayatiesByMandalsListAndConstituencyId(mandalsList,232l,8l);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj.toString());
		}
	}*/
	
	/*public void testGetBoothsByPanchayatIdsListAndConstituencyIdInAPublication()
	{
		List<Long> mandalsList = new ArrayList<Long>(0);
		mandalsList.add(1l);
		mandalsList.add(2l);
		List<Object[]> list = boothDAO.getBoothsByPanchayatIdsListAndConstituencyIdInAPublication(mandalsList,232l,8l);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
				System.out.print("\t"+obj.toString());
		}
	}*/
	
	/*public void testGetPanchayatsCountByMandalId()
	{
	 List<Long> list = boothDAO.getPanchayatsCountByMandalId(844l, 232l, 8l);
	 System.out.println(list.get(0));
	}*/
	
	/*public void testGetBoothsCountByLocationId()
	{
		List<Long> list = boothDAO.getBoothsCountByLocationId(IConstants.WARD, 31937l,347l, 8l);
		System.out.println(list.get(0));
	}*/
	
	/*public void testgetWardsCountByLocalEleBodyId()
	{
		List<Long> list = boothDAO.getWardsCountByLocalEleBodyId(20l, 8l, 347l);
		System.out.println(list.get(0));
	}*/
	
	/*public void testGetBoothsCountByLocationId()
	{
		List<Long> constituencyIds = new ArrayList<Long>();
		constituencyIds.add(232l);
		constituencyIds.add(347l);
		List<Object[]> list = boothDAO.getBoothsForConstituencyList(constituencyIds);
		for (Object[] params : list) {
			System.out.println(params[0]+" "+params[1]+" "+params[2]);
		}
	}*/
	
	/*public void testgetBoothsForLocalEleBodyByCOnstituencyId()
	{
		List<Object[]> list = boothDAO.getBoothsForLocalEleBodyByCOnstituencyId(292l, 122l, 8l);
		System.out.println(list.size());
	}*/
	/*public void testConstituencys()
	{
		List<Long> list = new ArrayList<Long>();
		list.add(2655l);list.add(2655l);list.add(2655l);list.add(2655l);list.add(2655l);list.add(29880l);list.add(29880l);list.add(29880l);list.add(30088l);list.add(30088l);list.add(30087l);list.add(30087l);list.add(30087l);list.add(29886l);list.add(30086l);list.add(30086l);list.add(30086l);list.add(30010l);list.add(29975l);list.add(29877l);list.add(29877l);list.add(29877l);list.add(30068l);list.add(30068l);list.add(30007l);list.add(29976l);list.add(29976l);list.add(29976l);list.add(29885l);list.add(29885l);list.add(30103l);list.add(30103l);list.add(30103l);list.add(30103l);list.add(30103l);list.add(30102l);list.add(30102l);list.add(30102l);list.add(30102l);list.add(30102l);list.add(80222l);list.add(80222l);list.add(80224l);list.add(80226l);list.add(80226l);list.add(80232l);list.add(80232l);list.add(80236l);list.add(80318l);list.add(80318l);list.add(80318l);list.add(80318l);list.add(80318l);list.add(80318l);list.add(80245l);list.add(80245l);list.add(80245l);list.add(80245l);list.add(80246l);list.add(80246l);list.add(80246l);list.add(29922l);list.add(29922l);list.add(29922l);list.add(29922l);list.add(29922l);list.add(29923l);list.add(29923l);list.add(29923l);list.add(29924l);list.add(29924l);list.add(29924l);list.add(29924l);list.add(29924l);list.add(29926l);list.add(29926l);list.add(29926l);list.add(29926l);list.add(29929l);list.add(29929l);list.add(29933l);list.add(29935l);list.add(29935l);list.add(29935l);list.add(29936l);list.add(29936l);list.add(29936l);list.add(29938l);list.add(29938l);list.add(29940l);list.add(29941l);list.add(29941l);list.add(29941l);list.add(29956l);list.add(29956l);list.add(29956l);list.add(29958l);list.add(29942l);list.add(29942l);list.add(29954l);list.add(29954l);list.add(29954l);list.add(29954l);list.add(29955l);list.add(29955l);list.add(29934l);list.add(29934l);list.add(29934l);list.add(29934l);list.add(29934l);list.add(29934l);list.add(29934l);list.add(29970l);list.add(29968l);list.add(29970l);list.add(29969l);list.add(29969l);list.add(29969l);list.add(29947l);list.add(29946l);list.add(29946l);list.add(29945l);list.add(29945l);list.add(29945l);list.add(29945l);list.add(29944l);list.add(29948l);list.add(29948l);list.add(29948l);list.add(29948l);list.add(29948l);list.add(29948l);list.add(29949l);list.add(29949l);list.add(29949l);list.add(29949l);list.add(29962l);list.add(29962l);list.add(29962l);list.add(29962l);list.add(29967l);list.add(29967l);list.add(29967l);list.add(29967l);list.add(29967l);list.add(29967l);list.add(29967l);list.add(29967l);
		List<Object[]> values = boothDAO.getConstityencysByBooths(list,IConstants.TEHSIL);
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1]);
		}
	}*/
	
	/*public void testgetboothsByTehsilIds()
	{
		List<Long> mandalsList = new ArrayList<Long>(0);
		mandalsList.add(1l);
		mandalsList.add(2l);
		List<Object[]> values = boothDAO.getboothsByTehsilIds(mandalsList);
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1]);
		}
	}*/
	
	
	/*public void testPanchyatsList(){
		List<Object[]> list= boothDAO.getPanchayatsListByTehsilId(844l,8l);
		for(Object[] li:list){
			System.out.println(li[0]+":"+li[1]);
		}
	}*/
	
	/*public void testVoters()
	{
		List<Object[]> values = boothDAO.getBoothsByPanchayat(11l,8l);
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1]);
		}
	}*/
	
	/*public void testgetTotalVotesForBooth()
	{
		List<Long> boothIdsList = new ArrayList<Long>(0);
		boothIdsList.add(241L);
		System.out.println(boothDAO.getTotalVotesForBooth(boothIdsList, 232l));
	}*/
	
	/*public void testGetPanchayatsListByConstituencyId()
	{
		List<Object[]> list = boothDAO.getPanchayatsNamesListByConstituencyId(232l, 8l);
		for(Object[] param:list){
			System.out.println(param[0]+":"+param[1]);
		}
		
	}*/
	
	/*public void testgetTotalVotesForBooth()
	{
		List<Long> boothIdsList = new ArrayList<Long>(0);
		boothIdsList.add(241L);
		System.out.println(boothDAO.getTotalVotesForBooth(boothIdsList, 232l));
		List<Booth> list = boothDAO.getboothsDetailsByTehsilId(83l,6l);
		
		System.out.println(list.size());
		for (Booth booth : list) {
			System.out.println(booth.getBoothId());
			System.out.println(booth.getPartNo());
		}

	}*/
	
	/*public void testgetBoothListByConstituencyId()
	{
		boothDAO.getBoothsListByConstituencyId(232L);
	}*/
	
	/*public void testGetLatestPublicationDateIdForAConstituency()
	{
		System.out.println(boothDAO.getLatestPublicationDateIdForAConstituency(232l));
	}*/
	/*public void testGetTehsilsForAConstituencyForAPublication()
	{
		List<Object[]> list = boothDAO.getTehsilsForAConstituencyForAPublication(232l,8l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetPanchayatsForAConstituencyForAPublication()
	{
		List<Object[]> list = boothDAO.getPanchayatsForAConstituencyForAPublication(232l,8l);
		System.out.println(list.size());
	}*/
	
	/*public void testgetConstituencyHamletCount()
	{
		System.out.println(boothDAO.getHamletIdsListByConstituencyId(232l, 8l));
	}*/
	/*public void testGetHamletsForAConstituencyForAPublication()
	{
		List<Object[]> list = boothDAO.getHamletsForAConstituencyForAPublication(232l,8l);
		System.out.println(list.size());
	}*/
	
	/*public void testgetBoothsForUrbanConstituencyes()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(31804l);
		ids.add(31805l);
		ids.add(31806l);
		ids.add(31807l);
		ids.add(31808l);
		ids.add(31809l);
		ids.add(31810l);
		ids.add(31937l);
		List<Object[]> values = boothDAO.getBoothsAndWardsInUrbanConstituency(347l,8l);
		System.out.println(values.size());
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1] +":"+ parms[2] +":"+ parms[3]);
		}
	}*/
	
	/*public void testgetTotalVotesForSelectedBooth()
	{
		System.out.println(boothDAO.getTotalVotesForSelectedBooth(1L));
	}*/
/*	public void testgetBoothIdsByConstituencyIdAndPublicationId()
	{
		List<Object[]> list = boothDAO.getDescriptionForPanchayatLevel(3l, 8l);
		System.out.println(list.size());
	}
	*/
	/*public void testgetDescriptionForPanchayatLevel()
	{
		List<Object[]> list = boothDAO.getDescriptionForHamletLevel(4l,33l);
		System.out.println(list.size());
		
		for (Object[] parms : list) {
			System.out.println(parms[0]); //url
			System.out.println(parms[1]); // pre panch
			System.out.println(parms[2]); //mappd pnch		
			System.out.println(parms[3]); // mappd hamlet
			System.out.println(parms[4]); //booth
			System.out.println(parms[5]); // pr panch id
		}
		
	}*/
	/*public void testgetPartialBoothsDetailsOfPanchayat()
	{
		List<Long> list1 = new ArrayList<Long>();
		list1.add(122995l);
		List<Object[]> list = boothDAO.getPartialBoothsDetailsOfPanchayat(list1, 8l);
		System.out.println(list.size());
		for (Object[] parms : list) {
			System.out.println(parms[0]); //url
			System.out.println(parms[2]); // pre panch
			System.out.println(parms[1]); //mappd pnch		
			System.out.println(parms[3]); // mappd hamlet
			System.out.println(parms[4]); //booth
			System.out.println(parms[5]); // pr panch id
		}
	}*/
	
	public void testgetDescriptionForPanchayatLevel()
	{
		List<Object[]> list = boothDAO.getPanchayatByBoothId(123032l,8l);
		System.out.println(list.size());
		
		for (Object[] parms : list) {
			System.out.println(parms[0]); //url
			System.out.println(parms[1]); // pre panch
		}
		
	}
}
