package com.itgrids.partyanalyst.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreCommitteeIncreasedPositionsDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;

public class CadreDAOHibernateTest extends BaseDaoTestCase {
	ICadreDAO cadreDAO;
	IUserAddressDAO userAddressDAO;
	ILocalElectionBodyDAO localElectionBodyDAO;
	ICadreCommitteeIncreasedPositionsDAO cadreCommitteeIncreasedPositionsDAO;
	ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	ITdpCommitteeDAO tdpCommitteeDAO;
	
	SimpleDateFormat format  = new SimpleDateFormat("yy-MM-dd");
	
	
	public ITdpCommitteeMemberDAO getTdpCommitteeMemberDAO() {
		return tdpCommitteeMemberDAO;
	}

	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	public ICadreDAO getCadreDAO() {
		return cadreDAO;
	}

	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	public ICadreCommitteeIncreasedPositionsDAO getCadreCommitteeIncreasedPositionsDAO() {
		return cadreCommitteeIncreasedPositionsDAO;
	}

	public void setCadreCommitteeIncreasedPositionsDAO(
			ICadreCommitteeIncreasedPositionsDAO cadreCommitteeIncreasedPositionsDAO) {
		this.cadreCommitteeIncreasedPositionsDAO = cadreCommitteeIncreasedPositionsDAO;
	}
	
	
	/*public void testFindCadresByLevels()
	{
		List result = cadreDAO.findCadresByLevels(1l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			
		}
		
	}*/
	
	/*@SuppressWarnings("unchecked")
	public void testFindCadreSearchCriteria(){
		//List result = cadreDAO.findCadreDetailsByLevelAndProperty(new Long(7), "currentAddress", "district", "districtId", new Long(1));
		List<Long> cadreIds = new ArrayList<Long>();
		cadreIds.add(new Long(1));
		cadreIds.add(new Long(2));
		
		List<Long> propertyIds = new ArrayList<Long>();
		propertyIds.add(new Long(1));
		propertyIds.add(new Long(2));
		
		//List result = cadreDAO.findCadreIdsByMemberTypeAndCadreList("Normal", cadreIds);
		//List result = cadreDAO.findCadreByPropertyValueAndCadreIds("education", "eduQualificationId", new Long(6), cadreIds);
		//List result = cadreDAO.findCadreByPropertyValueAndUser(new Long(7),"education", "eduQualificationId", new Long(7));
		//List result = cadreDAO.findCadreByPartyWorkingCommitteeAndCadreIds(new Long(1), cadreIds);
		//List result = cadreDAO.findCadreByPartyWorkingCommitteeAndUser(new Long(7), new Long(1));
		//List result = cadreDAO.findCadreByUserAndCadreLevel(new Long(7), new Long(2));
		
		//List result = cadreDAO.findCadreByPropertyValueListAndUser(new Long(7),"education", "eduQualificationId", propertyIds);
		
		//List result = cadreDAO.findCadreByPropertyValueListAndCadreIds("education", "eduQualificationId", propertyIds, cadreIds);
		
		//List result = cadreDAO.findCadreByPartyWorkingCommitteeListAndUser(new Long(7), propertyIds);
		
		//List result = cadreDAO.findCadreByPartyWorkingCommitteeListAndCadreIds(propertyIds, cadreIds);
		
		//List result = cadreDAO.findCadreByPartyWorkingCommitteeDesignationListAndUser(new Long(7), propertyIds);
		
		List result = cadreDAO.findCadreByPartyWorkingCommitteeDesignationListAndCadreIds(propertyIds, cadreIds);
		
		System.out.println(" Result Size :" + result.size());
	}*/
	
	/*public void testDeleteCadre()
	{
		int result=cadreDAO.deleteByCadreId(5l);
		System.out.println("No of records deleted:"+result);
		
	}*/
	
	/*public void testFindConstituencyCadresByDist()
	{
		List result = cadreDAO.findConstituencyCadresByDist(19l, 5l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			System.out.println(parms[2].toString());
			
		}
		
	}*/
	
	/*public void testFindConstituencyCadresByParliamentConst()
	{
		List result = cadreDAO.findConstituencyCadresByParliamentConst(404l, 12l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			System.out.println(parms[2].toString());
			
		}
		
	}*/
	
	/*public void testFindLocalElectionBodiesCadresByConst()
	{
		List result = cadreDAO.findLocalElectionBodiesCadresByConst(340l, 5l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			System.out.println(parms[2].toString());
			System.out.println(parms[3].toString());
			
		}
		
	}*/
	
	/*public void testFindHamletCadresByMandal()
	{
		List result = cadreDAO.findHamletCadresByMandal(844l, 5l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			System.out.println(parms[2].toString());
			
		}
		
	}
	*/
/*	public void testFindWardCadresByMandal()
	{
		List result = cadreDAO.findCadresByWard(566l,7l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			Constituency consti = (Constituency)parms[0];
			System.out.print(consti.getName());
			System.out.print(consti.getLocalElectionBodyWard().getWardName());
			System.out.print(consti.getConstituencyId());
			
			System.out.println(parms[1].toString());
			
		}
		
	}*/
	/*public void testFindCadresByHamlet()
	{
		List<Cadre> result = cadreDAO.findCadresByHamlet(857l, 5l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		System.out.println(result.size());
		for(Cadre cadre:result){
			
			System.out.print(cadre.getFirstName()+cadre.getLastName());
			System.out.println(cadre.getMemberType());
			
			
		}
	}*/
	
	/*public void testFindStateCadresByCountry()
	{
		List result = cadreDAO.findStateCadresByCountry(1l,7l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		System.out.println(result.size());
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			System.out.println(parms[2].toString());
			
		}
	}*/
	
	/*public void testFindDistrictsByStateID()
	{
		List result = cadreDAO.findDistrictsByStateID("1");
		System.out.println(result.size());
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			//System.out.println(parms[2].toString());
			
		}
	}*/
	/*public void testFindCadreSizeMandalWise()
	{
		List result = cadreDAO.findCadreSizeMandalWise(1l);
		System.out.println(result.size());
		for(int i=0;i<result.size();i++){
			System.out.print("if");
			Object[] parms = (Object[])result.get(i);
			System.out.println(parms[0]);
			
			System.out.println(parms[1]);
			//System.out.println(parms[2].toString());
			
		}
	}*/
	
	/*public void testFindCadreSizeConstituencywise()
	{
		List result = cadreDAO.findCadreSizeConstituencywise(1l);
		System.out.println(result.size());
		for(int i=0;i<result.size();i++){
			System.out.print("if");
			Object[] parms = (Object[])result.get(i);
			System.out.println(parms[0]);
			
			System.out.println(parms[1]);
			//System.out.println(parms[2].toString());
			
		}
	}*/
	
	/*public void testFindCadreSizeWardswise()
	{
		List result = cadreDAO.findCadreSizeWardswise(5l);
		System.out.println(result.size());
		for(int i=0;i<result.size();i++){
			System.out.print("if");
			Object[] parms = (Object[])result.get(i);
			System.out.println(parms[0]);
			
			System.out.println(parms[1]);
			//System.out.println(parms[2].toString());
			
		}
	}*/
	
	/*public void testFfindHamletsByTehsilIds()
	{
		List result = cadreDAO.findHamletsByTehsilIds("313");
		System.out.println(result.size());
		for(int i=0;i<result.size();i++){
			System.out.print("if");
			Object[] parms = (Object[])result.get(i);
			System.out.println(parms[0]);
			
			System.out.println(parms[1]);
			//System.out.println(parms[2].toString());
			
		}
	}*/
	
	/*public void testFindCadreSizeBoothwise()
	{
		List result = cadreDAO.findCadreSizeBoothwise(1l);
		System.out.println(result.size());
		for(int i=0;i<result.size();i++){
			System.out.print("if");
			Object[] parms = (Object[])result.get(i);
			System.out.println(parms[0]);
			
			System.out.println(parms[1]);
			//System.out.println(parms[2].toString());
			
		}
	}*/
	/*
	public void testFindCadreSizeBoothwiseInMandal()
	{
		List result = cadreDAO.findCadreSizeBoothwiseInMandal(1l);
		System.out.println(result.size());
		for(int i=0;i<result.size();i++){
			System.out.print("if");
			Object[] parms = (Object[])result.get(i);
			System.out.println(parms[0]);
			
			System.out.println(parms[1]);
			//System.out.println(parms[2].toString());
			
		}
	}*/
	/*public void testFindBoothCadresByMandal()
	{
		List result = cadreDAO.findBoothCadresByMandal(844l, 1l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		System.out.print(result.size());
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			System.out.println(parms[2].toString());			
		}		
	}*/
	
/*	public void testFindBoothCadresByLocalElectionBody()
	{
		List result = cadreDAO.findBoothCadresByLocalElectionBody(566l, 11l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		System.out.print(result.size());
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			System.out.println(parms[2].toString());			
		}		
	}*/
	
	/*public void testFindCadresByBooth()
	{
		List<Cadre> result = cadreDAO.findCadresByBooth(67026l, 1l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		System.out.print(result.size());
		for(Cadre cadre:result){
			System.out.println(cadre.getFirstName());			
		}		
	}*/
	/*public void testFindCadresNotAssignedToBooth()
	{
		List result = cadreDAO.findCadresNotAssignedToBooth(1l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		System.out.print("Size"+result.size());
		//System.out.print(result.get(0));
		Long count  = Long.parseLong(result.get(0).toString());
			System.out.print("count"+count);		
				
	}*/
	
	/*public void testFindCadreDetailsNotAssignedToBooth()
	{
		List<Cadre> result = cadreDAO.findCadreDetailsNotAssignedToBooth(1l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		System.out.print(result.size());
		for(Cadre cadre:result){
			System.out.println(cadre.getFirstName());			
		}		
	}*/
	/*@SuppressWarnings("unchecked")
	public void testFindVillageLevelCadresContByMandal()
	{
		List result = cadreDAO.findVillageLevelCadresContByMandal(844l, 1l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		Long count  = Long.parseLong(result.get(0).toString());
		System.out.print("count"+result);			
	}*/
	
	/*@SuppressWarnings("unchecked")
	public void testFindCadresByBoothInWard()
	{
		List result = cadreDAO.findCadresByBoothInWard(7713l, 1l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.println(parms[1].toString());
			System.out.println(parms[2].toString());
			System.out.println(parms[3].toString());
		}			
	}*/
	
	//@SuppressWarnings("unchecked")
	//public void testFindCadreForSMS()
	//{
		/*String s = "and model.currentAddress.state.stateId = 1";
		//String s = "and model.currentAddress.district.districtId = 19";
		//String s = "and model.currentAddress.constituency.constituencyId = 231";
		//String s = "and model.currentAddress.tehsil.tehsilId = 861";
		//String s = "and model.currentAddress.hamlet.hamletId = 2094";
		//String s = "and model.currentAddress.localElectionBody.localElectionBodyId = 478";
		//String s = "and model.currentAddress.ward.constituencyId = 8147";
		//String s = "and model.currentAddress.booth.boothId = 69082";
		//String cadreType="and model.memberType = 'Active'";
		//String cadreType="and model.memberType = 'Normal'";
		String cadreType="";
		String sortOption = null;
		String order = null;
		
		sortOption = " model.firstName ";
		//sortOption = " model.mobile ";
		//sortOption = " model.cadreLevel.level ";
		//sortOption = " model.memberType ";
		//sortOption = " model.education.qualification ";
		//sortOption = " model.occupation.occupation ";
		//sortOption = " model.casteCategory.category ";
		
		order = " asc ";
		//order = " desc ";
		
		String socialStr = new String();
		String genderStr = new String();
				
		//socialStr =" and (model.casteCategory.socialCategoryId = 1 or model.education.eduQualificationId = 1 or model.occupation.occupationId = 1)";
		
		genderStr = " and model.gender like 'Male'";
		
		//String s = " and model.cadreLevel.cadreLevelID = 2";
		
		String mobileStr=" and length(model.mobile) > 0";
		
		String name = "";
		boolean flag = !name.isEmpty();
		
		StringTokenizer st = new StringTokenizer(name);
		String cadreNameStr = "";
		if(flag)
		cadreNameStr = "and ( ";
		
		while(st.hasMoreTokens())
		{
			String names = st.nextToken();
			cadreNameStr += "model.firstName like '"+names+"%' or model.middleName like '"+names+"%' or model.lastName like '"+names+"%'";
			cadreNameStr += " or ";
		}
		if(flag)
		{
			cadreNameStr = cadreNameStr.substring(0,cadreNameStr.length()-4);
			cadreNameStr += ")";
		}
		
		System.out.println(cadreNameStr);
		cadreNameStr = " and (model.firstName like '"+name+"%' or model.middleName like '"+name+"%' or model.lastName like '"+name+"%')";
		
		String roleStr = " and model.cadreId in (select model1.cadre.cadreId from CadreRoleRelation model1) "; 
		//String roleStr = "";
		*/
		/*List<Long> x = cadreDAO.findCadreForSMS(1l,"","and model.currentAddress.state.stateId = 1","","","","and (  model.firstName like 'a%' or model.middleName like 'a%' or model.lastName like 'a%' )","","",""," model.firstName ","asc",0,20);
		
		for(Long y:x)
		{
			//System.out.println(cadreDAO.get(y).getCadreId());
			System.out.println(cadreDAO.get(y).getFirstName());
			System.out.println(cadreDAO.get(y).getLastName());
			System.out.println(cadreDAO.get(y).getMiddleName());
		}
		*/
	//	List<Long> y = cadreDAO.findTotalCadreCountForSms(1l,cadreType,s,socialStr,genderStr,mobileStr,cadreNameStr,roleStr);
		//System.out.println(y.get(0).toString());
		
	//}
 /*	@SuppressWarnings("unchecked")
	public void testFindNormalCadre()
	{
		List<Object[]> obj = cadreDAO.findNormalCadre(1l);
		System.out.println(obj.size());
	}*/
	
	/*@SuppressWarnings("unchecked")
	public void testFindCadre()
	{
		List<Long> obj = cadreDAO.findCadres(1l);
		System.out.println(obj.size());
		for(Long y:obj)
		{	
			System.out.println(y);
			Cadre cadre = cadreDAO.get(y);
		}
		
	
		
		//Long x = Long.parseLong(y[0].toString());

	}*/
	
	/*public void testFindTotalCadresByUserID()
	{
		List result = cadreDAO.findTotalCadresByUserIdBasedOnCadreLevel(1L, IConstants.CADRE_MEMBER_TYPE_ACTIVE, "constituency", "constituencyId", 232L);
		
		System.out.println(result);
		
	}*/
	
	/*public void testFindCadresByCadreLevelByUserIDInALocation()
	{
		List<Cadre> result = cadreDAO.findCadresByCadreLevelByUserIDInALocation("STATE", 1L, IConstants.CADRE_MEMBER_TYPE_ACTIVE, "constituency", "constituencyId", 232L);
		System.out.println(result.size());
	}*/
	
	/*public void testUpdateCadreImage()
	{
		int x = cadreDAO.updateCadreImage(2l,"human.jpeg");
		System.out.println(cadreDAO.get(1l).getImage());
		System.out.println(x);
	}*/
	
	/*public void testGetMobileNosOfCadre()
	{
		List<Long> idList = new ArrayList<Long>(0);
		idList.add(7l);idList.add(14l);idList.add(15l);idList.add(16l);
		List<Object> list = cadreDAO.getMobileNosOfCadre(idList);
		System.out.println(list.size());
		
		for(Object object : list)
		{
			System.out.println(object.toString());
		}
	}*/
	/*public void testFindCandidatePeopleDetails()
	{
		List<Long> voterIds = new ArrayList<Long>();
		voterIds.add(2172793l);
		voterIds.add(2130033l);
		voterIds.add(2152480l);
		voterIds.add(20l);
		voterIds.add(210l);
		List<Long> list = cadreDAO.findCadrePeopleDetails(voterIds,1l);
		System.out.println(list.size());
	}*/
	
	
	/*public void testgetInfluencingPeopleCount() 
	{
		List<Long> locationValue = new ArrayList<Long>(0);
		locationValue.add(232l);
		
		List<Cadre> values = cadreDAO.getCadreVoterIDs(1l,locationValue,"CONSTITUENCY",1,30,"voterIDCardNo","asc");
		System.out.println(values.size());
		for (Cadre cadre : values) {
			System.out.println(cadre.getFirstName());
		}
	}*/
	
	/*public void testGetCadreCountInALocation()
	{
		List<Long>locationValue = new ArrayList<Long>();
		locationValue.add(738l);
		System.out.println(cadreDAO.getCadreCountInALocation(1l, locationValue, "mandal"));
	}*/
	
	/*public void testGetCadreList()
	{
	  cadreDAO.getCadreList();	
	}*/
	
	/*public void testGetCadreDetailsInAConstituency(Long userId, Long constituencyId)
	{
		List<Cadre> list = cadreDAO.getCadreDetailsInAConstituency(1l,232l);
		System.out.println(list.size());
	}*/
	
	/*public void testDetails(){
		
		 CadreVo returnVo = new CadreVo();
		 List<CadreVo> returnList = new ArrayList<CadreVo>();
		 try {
			 String queryStr = null;
			// if(searchType.equalsIgnoreCase("firstName")){
				 queryStr = " model.firstName like '%a%' ";
			
			List<Object[]> cadreInfo1 =  cadreDAO.searchCadreInfoByConstidAndNameORMobile(232L,"asc","firstName",1,10,queryStr,"count");
			List<Object[]> cadreInfo =  cadreDAO.searchCadreInfoByConstidAndNameORMobile(232L,"asc","firstName",1,10,queryStr,null);
			
			System.out.println(cadreInfo);
			 
			if(cadreInfo != null && cadreInfo.size()>0){
				
				for (Object[] cadre : cadreInfo) {
					CadreVo vo = new CadreVo();
					vo.setFirstName(cadre[0] != null?cadre[0].toString():"");
					vo.setLastName(cadre[1] != null?cadre[1].toString():"");
					vo.setMobileNo(cadre[2] != null?cadre[2].toString():"");
					vo.setMemberType(cadre[3] != null?cadre[3].toString():"");
					vo.setImage(cadre[4] != null?cadre[4].toString():"human.jpg");
					vo.setDistrictName(cadre[6] != null?cadre[6].toString():"");
					vo.setConstituencyName(cadre[7] != null?cadre[7].toString():"");					
					if(cadre[10] == null){
						vo.setMandalName(cadre[8] != null?cadre[8].toString():"");
						vo.setVillageName(cadre[9] != null?cadre[9].toString():"");
					}
					else{
						vo.setLocalelectionName(localElectionBodyDAO.getLocalElectionBodyName(83L)+" Muncipality ");
					}
					
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		 returnList.add(null);
	}*/
	
	/*public void testDtails()
	{
		StringBuilder searchQuery = new StringBuilder();
		List<VoterInfoVO> returnList = null;

		
		searchQuery.append(" C.firstName like '%raj%' OR C.lastName like '%raj%' and ");
		System.out.println(new Date());
		List<Object[]> searchList = cadreDAO.getCadreDetailsForCadreRegistratiobByconstituencId(232L, searchQuery.toString());
		System.out.println(new Date());
		if(searchList != null && searchList.size()>0 )
		{
			returnList = new ArrayList<VoterInfoVO>();
			
			for (Object param : searchList)
			{
				try {
					
					Object[] cadre = (Object[]) param;
					VoterInfoVO vo = new VoterInfoVO();
					vo.setId(cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L);
					vo.setName(cadre[1] != null ? cadre[1].toString().trim():"");
					vo.setName(vo.getName() + (cadre[2] != null ? " "+cadre[2].toString().trim():" "));
					
					vo.setRelativeName(cadre[3] != null ? cadre[3].toString().trim():"");
					
					
					String dateOfBirth = cadre[4] != null ? cadre[4].toString().trim():"";
					
					if(dateOfBirth != null && dateOfBirth.trim().length()>0)
					{
						Calendar startDate = new GregorianCalendar();
						Calendar endDate = new GregorianCalendar();
						
						startDate.setTime(format.parse(dateOfBirth.substring(0,10)));
						
						endDate.setTime(new Date());

						int diffYear = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
						
						vo.setAge(String.valueOf(diffYear));
					}
					else if(cadre[5] != null )
					{
						vo.setAge(cadre[5] != null ? cadre[5].toString().trim():"");
					}
					
					
					vo.setHouseNo(" -- ");
					vo.setRelationType(" -- ");
					
					vo.setGender(cadre[6] != null ? cadre[6].toString().trim():"");
					
					returnList.add(vo);
				
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		System.out.println(returnList);
	}*/
	
	
	
	

	/*public void testDtails()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(601L);
		ids.add(602L);
		ids.add(603L);
		ids.add(604L);
		ids.add(605L);
		StringBuilder searchQuery = new StringBuilder();

		searchQuery.append(" C.firstName like '%raj%' OR C.lastName like '%raj%' ");
		searchQuery.append(" and V.voterIDCardNo like '%AP%'");
		searchQuery.append(" and V.houseNo like '%1-165%' ");
	
		System.out.println(new Date());
		List<Object[]> searchList = cadreDAO.getvoterdetailsByCadreIds(ids,searchQuery.toString());
		
		System.out.println(new Date());
		System.out.println(searchList.size());
	}*/
	
	public ITdpCommitteeDAO getTdpCommitteeDAO() {
		return tdpCommitteeDAO;
	}

	public void setTdpCommitteeDAO(ITdpCommitteeDAO tdpCommitteeDAO) {
		this.tdpCommitteeDAO = tdpCommitteeDAO;
	}

	public void test(){
		Date startDate = null;
		Date endDate = null;
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		try {
			startDate = (Date)format.parse("2015-01-28 01:33:43");
			endDate = (Date)format.parse("2015-01-30 21:35:51");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		/*Long searchList = cadreCommitteeIncreasedPositionsDAO.getAllRecordsCount();
		System.out.println(searchList);*/
		
		/*List<Object[]> list = cadreCommitteeIncreasedPositionsDAO.getAllRecordsCountStatusWise();
		System.out.println(list.size());*/
		List<Long> levelIds = new ArrayList<Long>();
		levelIds.add(6l);
		 
		/*List<Object[]> list = tdpCommitteeMemberDAO.membersCountDistrictWise(levelIds, null, null);
		System.out.println(list.size());*/
		
		/*List<Object[]> list = tdpCommitteeDAO.committeesCountByDistrict(levelIds, startDate, endDate, "completed");
		System.out.println(list.size());*/
		
	}
	
}
	

