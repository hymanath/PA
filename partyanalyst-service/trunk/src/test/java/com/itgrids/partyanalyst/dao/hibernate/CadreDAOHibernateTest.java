package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.columns.enums.CadreColumnNames;
import com.itgrids.partyanalyst.dao.columns.enums.EducationQualificationColumnNames;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreDAOHibernateTest extends BaseDaoTestCase {
	ICadreDAO cadreDAO;
	IUserAddressDAO userAddressDAO;
		
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	public ICadreDAO getCadreDAO() {
		return cadreDAO;
	}

	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	
	/*public void testFindCadresByLevels()
	{
		List result = cadreDAO.findCadresByLevels(7l, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
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
	
	/*@SuppressWarnings("unchecked")
	public void testFindCadreForSMS()
	{
		String s = "and model.currentAddress.state.stateId = 1";
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
	//	cadreNameStr = " and (model.firstName like '"+name+"%' or model.middleName like '"+name+"%' or model.lastName like '"+name+"%')";
		
		//String roleStr = " and model.cadreId in (select model1.cadre.cadreId from CadreRoleRelation model1) "; 
		String roleStr = "";
		
		List<Long> x = cadreDAO.findCadreForSMS(1l,cadreType,s,socialStr,genderStr,mobileStr,cadreNameStr,roleStr,sortOption,order,0,20);
		
		for(Long y:x)
		{
			System.out.println(cadreDAO.get(y).getCadreId());
			System.out.println(cadreDAO.get(y).getFirstName());
			System.out.println(cadreDAO.get(y).getLastName());
			System.out.println(cadreDAO.get(y).getMiddleName());
		}
		
		List<Long> y = cadreDAO.findTotalCadreCountForSms(1l,cadreType,s,socialStr,genderStr,mobileStr,cadreNameStr,roleStr);
		System.out.println(y.get(0).toString());
		
	}*/
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
	
	public void testGetMobileNosOfCadre()
	{
		List<Long> idList = new ArrayList<Long>(0);
		idList.add(7l);idList.add(14l);idList.add(15l);idList.add(16l);
		List<Object> list = cadreDAO.getMobileNosOfCadre(idList);
		System.out.println(list.size());
		
		for(Object object : list)
		{
			System.out.println(object.toString());
		}
	}
}
	

