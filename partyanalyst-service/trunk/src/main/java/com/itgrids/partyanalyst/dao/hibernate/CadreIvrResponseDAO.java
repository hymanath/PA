package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreIvrResponseDAO;
import com.itgrids.partyanalyst.model.CadreIvrResponse;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreIvrResponseDAO extends GenericDaoHibernate<CadreIvrResponse, Long> implements ICadreIvrResponseDAO{

	public CadreIvrResponseDAO() {
		super(CadreIvrResponse.class);
		
	}

	
	public List<Date> getDates()
	{
		Query query = getSession().createQuery("select distinct date(model.date) from CadreIvrResponse model");
		return query.list();
	}
	
	public Long getIvrStatusCount(Date date,Long Id,String searchType)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId) from CadreIvrResponse model where ");
		if(date != null)
			str.append(" date(model.date) =:date");
		else if(Id != null)
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId =:Id and model.isDeleted = 'N' ");
		if(searchType.equalsIgnoreCase(IConstants.Received))
		 str.append(" and model.responseKey = 1");	
		else if(searchType.equalsIgnoreCase(IConstants.NotReceived))
			 str.append(" and model.responseKey = 2");	
		else if(searchType.equalsIgnoreCase(IConstants.NotRegistered))
			 str.append(" and model.responseKey = 3");	
		Query query = getSession().createQuery(str.toString());
		if(date != null)
			query.setDate("date", date);
		else if(Id != null)
			query.setParameter("Id", Id);
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getIvrCadreDetails(Date date,Long Id,String searchType,Integer startIndex,Integer maxIndex)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCadre.firstname,model.mobileNo,model.currentStatus");
		str.append(" ,constituency.name");	
		str.append(" ,tehsil.tehsilName");
		str.append(" ,panc.panchayatName");
		str.append(" ,localElectionBody.name");
		str.append(" from CadreIvrResponse model left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
	    str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" where");
		if(date != null)
			str.append(" date(model.date) =:date");
		if(Id != null)
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId =:Id and model.isDeleted = 'N' ");
		if(searchType.equalsIgnoreCase(IConstants.Received))
		 str.append(" and model.responseKey = 1");	
		else if(searchType.equalsIgnoreCase(IConstants.NotReceived))
			 str.append(" and model.responseKey = 2");	
		else if(searchType.equalsIgnoreCase(IConstants.NotRegistered))
			 str.append(" and model.responseKey = 3");	
		else if(searchType.equalsIgnoreCase(IConstants.Response))
			 str.append(" and model.responseKey in (1,2,3)");
		Query query = getSession().createQuery(str.toString());
		if(date != null)
			query.setDate("date", date);
		else if(Id != null)
			query.setParameter("Id", Id);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	public  Long  getTotalIvrCount()
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId) from CadreIvrResponse model where model.isDeleted = 'N' ");
		
		Query query = getSession().createQuery(str.toString());
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getIvrCountForAPAndTS()
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId),model.responseKey from CadreIvrResponse model where model.isDeleted = 'N' ");
		str.append(" group by model.responseKey");
		Query query = getSession().createQuery(str.toString());
			
		return query.list();
	}
	public List<Object[]> getIvrCountByDate(Date fromDate,Date toDate,String state)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId),model.userAddress.district.districtId,model.responseKey from CadreIvrResponse model where model.isDeleted = 'N' ");
		if(state.equalsIgnoreCase("AP")){
			str.append(" and model.userAddress.district.districtId > 10");
		}else if(state.equalsIgnoreCase("TS")){
			str
			.append(" and model.userAddress.district.districtId < 11");
		}
		if((fromDate != null && toDate != null) && !fromDate.equals(toDate))
		str.append(" and date(model.date) >=:fromDate and date(model.date) <=:toDate");
		else if((fromDate != null && toDate != null) && fromDate.equals(toDate))
		str.append(" and date(model.date) =:fromDate");
		str.append(" group by model.userAddress.district.districtId,model.responseKey");
		Query query = getSession().createQuery(str.toString());
		if((fromDate != null && toDate != null) && !fromDate.equals(toDate))
		{
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		}
		if((fromDate != null && toDate != null) && fromDate.equals(toDate))
		query.setDate("fromDate", fromDate);	
		return query.list();
	}
	
	public List<Object[]> getConstituencyWiseIvrCount()
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.cadreIvrResponseId),model.tdpCadre.userAddress.constituency.constituencyId,model.tdpCadre.userAddress.constituency.name,model.userAddress.district.districtId,model.userAddress.district.districtName,model.responseKey from CadreIvrResponse model where ");
		str.append(" model.tdpCadre.userAddress.constituency.constituencyId is not null and model.isDeleted = 'N' ");
		str.append(" group by model.tdpCadre.userAddress.constituency.constituencyId,model.responseKey");
		Query query = getSession().createQuery(str.toString());
		return query.list();
	}	
	public List<Object[]> getTehsilWiseIVRInfo(){
		//0 districtId,1constituencyId,2constituencyName,3tehsilId,4tehsilName,5count,6response key,7districtName
		Query query = getSession().createQuery("select model.userAddress.district.districtId,model.userAddress.constituency.constituencyId,model.userAddress.constituency.name,model.userAddress.tehsil.tehsilId,concat(model.userAddress.tehsil.tehsilName,' Mandal')," +
				" count(*),model.responseKey,model.userAddress.district.districtName from  CadreIvrResponse model where model.isDeleted = 'N' and model.userAddress.localElectionBody.localElectionBodyId is null and " +
				" model.userAddress.tehsil.tehsilId is not null and model.responseKey is not null group by model.userAddress.tehsil.tehsilId,model.responseKey ");
		
		return query.list();
	}
	public List<Object[]> getTehsilWiseIVRTotalCountInfo(){
		//0 id,1count
		Query query = getSession().createQuery("select model.userAddress.tehsil.tehsilId,count(*) " +
				"  from  CadreIvrResponse model where model.isDeleted = 'N' and model.userAddress.localElectionBody.localElectionBodyId is null and " +
				" model.userAddress.tehsil.tehsilId is not null group by model.userAddress.tehsil.tehsilId ");
		
		return query.list();
	}
	
	public List<Object[]> getLocalBodyWiseIVRInfo(){
		//0 districtId,1constituencyId,2constituencyName,localElectionBodyId,4localElectionBodyName,5count,6response key,7districtName
		Query query = getSession().createQuery("select model.userAddress.district.districtId,model.userAddress.constituency.constituencyId,model.userAddress.constituency.name,model.userAddress.localElectionBody.localElectionBodyId," +
				" concat(model.userAddress.localElectionBody.name, model.userAddress.localElectionBody.electionType.electionType),count(*),model.responseKey,model.userAddress.district.districtName from  CadreIvrResponse model where model.isDeleted = 'N' and " +
				" model.userAddress.localElectionBody.localElectionBodyId is not null and model.responseKey is not null group by model.userAddress.constituency.constituencyId,model.userAddress.localElectionBody.localElectionBodyId,model.responseKey ");
		
		return query.list();
	}
	public List<Object[]> getLocalBodyWiseIVRTotalCountInfo(){
		//0 constituencyId,1 localBodyId,2 count
		Query query = getSession().createQuery("select model.userAddress.constituency.constituencyId,model.userAddress.localElectionBody.localElectionBodyId,count(*) from CadreIvrResponse model where model.isDeleted = 'N' " +
				"  and model.userAddress.localElectionBody.localElectionBodyId is not null group by model.userAddress.constituency.constituencyId,model.userAddress.localElectionBody.localElectionBodyId ");
		
		return query.list();
	}
	
	public List<Object[]> getPanchayatWiseIVRInfo(String state){
		StringBuilder queryStr = new StringBuilder();
		//0panchayatId,1panchayatName,2districtName,3constituencyName,4count,5responseKey
		queryStr.append("select model.userAddress.panchayat.panchayatId,model.userAddress.panchayat.panchayatName,model.userAddress.district.districtName,model.userAddress.constituency.name,count(*),model.responseKey from " +
				" CadreIvrResponse model where model.isDeleted = 'N' and model.responseKey is not null ");
		if(state.equalsIgnoreCase("AP")){
			queryStr.append(" and model.userAddress.district.districtId > 10");
		}else{
			queryStr.append(" and model.userAddress.district.districtId < 11");
		}
		queryStr.append(" and model.userAddress.panchayat.panchayatId is not null group by model.userAddress.panchayat.panchayatId,model.responseKey");
		Query query = getSession().createQuery(queryStr.toString());
		
		return query.list();
	}
	
	public List<Object[]> getPanchayatWiseIVRCountInfo(String state){
		StringBuilder queryStr = new StringBuilder();
		//0panchayatId,1count
		queryStr.append("select model.userAddress.panchayat.panchayatId,count(*) from " +
				" CadreIvrResponse model  where model.isDeleted = 'N' ");
		if(state.equalsIgnoreCase("AP")){
			queryStr.append(" and model.userAddress.district.districtId > 10");
		}else{
			queryStr.append(" and model.userAddress.district.districtId < 11");
		}
		queryStr.append("and model.userAddress.panchayat.panchayatId is not null group by model.userAddress.panchayat.panchayatId");
		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
}
