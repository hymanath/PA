package com.itgrids.partyanalyst.dao.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMobileNumbersDAO;
import com.itgrids.partyanalyst.model.MobileNumbers;
import com.itgrids.partyanalyst.utils.IConstants;

public class MobileNumbersDAO extends GenericDaoHibernate<MobileNumbers, Long> implements IMobileNumbersDAO{

	public MobileNumbersDAO() {
		super(MobileNumbers.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Long> getConstituencysByDistictID(Long districtId)
	{
		Query query = getSession().createQuery("Select distinct model.acId from MobileNumbers model where model.distNo = :districtId");
		query.setParameter("districtId", districtId);
		return query.list();
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getMobileNoforVoter(Long constituencyId)
	{
		Query query = getSession().createQuery("Select model1.mobile,BPV.voter.voterId from MobileNumbers model1,BoothPublicationVoter BPV where " +
			"model1.idcardNO = BPV.voter.voterIDCardNo and " +
			"BPV.booth.publicationDate.publicationDateId = 10 AND model1.acId = :constituencyId");
		
		query.setParameter("constituencyId", constituencyId);
		return query.list();
		
	}
	public List<Object[]> getUservoterDetailsByUserId(Long userId,List<Long> voterIds)
	{
		Query query = getSession().createSQLQuery("select uvd.voter_id,uvd.mobile_no,uvd." +
			"user_voter_details_id from user_voter_details uvd where uvd.user_id = :userId and uvd.mobile_no is not null and uvd.voter_id in (:voterIds) ");
		query.setParameter("userId", userId);
		query.setParameterList("voterIds", voterIds);
		return query.list();
		
	}
	
	public List<Object[]> getVotersMobileNumberDetails(List<Long> voterIdsList)
	{
		Query query = getSession().createQuery(" select model.voterId, model.mobileNumber from  MobileNumbers model " +
				" where model.voter.voterId in (:voterIdsList) and model.isDeleted='N' and model.isUsed='Y' ");
		query.setParameterList("voterIdsList", voterIdsList);
		return query.list();
	}
	public Integer updateMobileNo(String mobileNo,Long userVoterDetailsId)
	{
		Query query = getSession().createQuery("update UserVoterDetails model set model.mobileNo = :mobileNo where model.userVoterDetailsId = :userVoterDetailsId");
		query.setParameter("mobileNo", mobileNo);
		query.setParameter("userVoterDetailsId", userVoterDetailsId);
		return query.executeUpdate();
	}

	public List<Object[]> getMobileNumberDetailsByBoothId(Long boothId)
	{
		Query query = getSession().createQuery(" select model.mobileSourceTypeId,model.voterId, model.mobileNumber from  MobileNumbers model " +
				" where model.boothId = :boothId ");
		
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
	
	
	
	public Set<String> getVotersMobilenos(Long scopeId,Long location,int maxIndex) 
	{
		StringBuilder str = new StringBuilder(); 
		str.append("select distinct model.mobileNumber from MobileNumbers model where");
		if(scopeId == 1)
		 str.append(" model.district.districtId =:location")	;
		 else
		 str.append(" model.constituency.constituencyId =:location");
		 str.append(" and model.mobileNumber is not null and length(model.mobileNumber) = 10 and model.mobileNumber <> '9999999999' and model.voter.voterId is not null order by rand()");
		 Query query = getSession().createQuery(str.toString());
		 query.setParameter("location", location);
		 if(maxIndex > 0)
		 {
			 query.setMaxResults(maxIndex);	 
		 }
		 return new HashSet(query.list());
		 
	}
	
	
	public Set<String> getIvrMobilenosBasedOnPriority(Long scopeId,Long location,int maxIndex,String priority) 
	{
		StringBuilder str = new StringBuilder(); 
		str.append("select distinct model.mobileNumber from MobileNumbers model where");
		 if(scopeId == 1)
		 str.append(" model.district.districtId =:location")	;
		 else
		 str.append(" model.constituency.constituencyId =:location");
		 str.append(" and model.mobileNumber is not null and length(model.mobileNumber) = 10 and model.mobileNumber <> '9999999999' and model.voter.voterId is null");
		 
		 if(priority.equalsIgnoreCase(IConstants.PANCHAYAT))
		  str.append(" and model.panchayat.panchayatId is not null order by rand()");
		 else if(priority.equalsIgnoreCase(IConstants.TEHSIL))
			 str.append(" and model.panchayat.panchayatId is null and model.tehsil.tehsilId is not null order by rand()"); 
		 else if(priority.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append(" and model.panchayat.panchayatId is null and model.tehsil.tehsilId is null and constituency.constituencyId is not null order by rand()"); 
		 
		 Query query = getSession().createQuery(str.toString());
		 query.setParameter("location", location);
		 
		 if(maxIndex > 0)
		 {
			 query.setMaxResults(maxIndex);	 
		 }
		 return new HashSet(query.list());
		 
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getMobileNosCountByIds(List<Long> Ids,String type,String scope)
	{
		StringBuilder str = new StringBuilder();
		Query query = null;
		str.append("select count(distinct model1.mobileNumber)");
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" ,model1.constituency.constituencyId,model1.constituency.name");
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
		str.append(" ,model1.tehsil.tehsilId,model1.tehsil.tehsilName");
		else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			str.append(" ,model1.panchayat.panchayatId ,model1.panchayat.panchayatName");
		str.append(" from MobileNumbers model1,BoothPublicationVoter BPV where" +
			" model1.voter.voterId = BPV.voter.voterId and" +
			" BPV.booth.publicationDate.publicationDateId = 10");
		if(scope.equalsIgnoreCase(IConstants.DISTRICT))
		str.append(" and model1.district.districtId in(:Ids)");
		if(scope.equalsIgnoreCase(IConstants.CONSTITUENCY))
		str.append(" and model1.constituency.constituencyId in(:Ids)");
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
		str.append(" group by model1.constituency.constituencyId");
		if(type.equalsIgnoreCase(IConstants.TEHSIL))
		str.append(" group by model1.tehsil.tehsilId");
		if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
		str.append(" group by model1.panchayat.panchayatId");
		query = getSession().createQuery(str.toString());
		query.setParameterList("Ids", Ids);
		return query.list();
		
	}*/
	
	@SuppressWarnings("unchecked")
	public Long getMobileNosTotal(Long Id,String type,Long scopeId)
	{
		StringBuilder str = new StringBuilder();
		Query query = null;
		str.append("select count(distinct model.mobileNumber)");
		str.append(" from MobileNumbers model where");
		if(scopeId == 1)//District
		str.append(" model.district.districtId = :Id");
		if(scopeId == 2)//Constituency
		str.append(" model.constituency.constituencyId = :Id");
		if(scopeId == 4)//Mandal
		str.append(" model.tehsil.tehsilId = :Id");	
		
		str.append(" and model.mobileNumber is not null and length(model.mobileNumber) = 10 and model.mobileNumber <> '9999999999' ");
		str.append("and model.isUsed = 'N' and model.isDeleted = 'N' ");
		query = getSession().createQuery(str.toString());
		query.setParameter("Id", Id);
		return (Long) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public Long getMobileNosCountByIds(Long Id,String type,Long scopeId)
	{
		StringBuilder str = new StringBuilder();
		Query query = null;
		str.append("select count(distinct model.mobileNumber)");
		str.append(" from MobileNumbers model where");
		if(type.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" model.district.districtId is not null and model.constituency.constituencyId is null and " +
					" model.tehsil.tehsilId is null and  model.panchayat.panchayatId is null");
		else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" model.constituency.constituencyId is not null and model.tehsil.tehsilId is null" +
					" and model.panchayat.panchayatId is null");
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
		str.append(" model.tehsil.tehsilId is not null and model.panchayat.panchayatId is null");
		else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			str.append(" model.panchayat.panchayatId is not null");
		
		if(scopeId == 1)//District
		str.append(" and model.district.districtId = :Id");
		if(scopeId == 2)//Constituency
		str.append(" and model.constituency.constituencyId = :Id");
		if(scopeId == 4)//mandal
		str.append(" and model.tehsil.tehsilId = :Id");
		 str.append(" and model.mobileNumber is not null and length(model.mobileNumber) = 10 and model.mobileNumber <> '9999999999' ");
		 str.append("and model.isUsed = 'N' and model.isDeleted = 'N' ");
		query = getSession().createQuery(str.toString());
		query.setParameter("Id", Id);
		return (Long) query.uniqueResult();
	}
	
	public Set<String> getMobilenosBasedOnPriority(Long scopeId,Long location,int maxIndex,String priority) 
	{
		StringBuilder str = new StringBuilder(); 
		str.append("select distinct model.mobileNumber from MobileNumbers model where");
		 if(scopeId == 1)
		 str.append(" model.district.districtId =:location")	;
		 if(scopeId == 2)
		 str.append(" model.constituency.constituencyId =:location");
		if(scopeId == 4)//mandal
		str.append(" model.tehsil.tehsilId = :location");
		 str.append(" and model.mobileNumber is not null and length(model.mobileNumber) = 10 and model.mobileNumber <> '9999999999' ");
		 str.append("and model.isUsed = 'N' and model.isDeleted = 'N' ");
		 if(priority.equalsIgnoreCase(IConstants.PANCHAYAT))
		  str.append(" and model.panchayat.panchayatId is not null order by rand()");
		 else if(priority.equalsIgnoreCase(IConstants.TEHSIL))
			 str.append(" and model.tehsil.tehsilId is not null and model.panchayat.panchayatId is null order by rand()"); 
		 else if(priority.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append(" and model.constituency.constituencyId is not null and model.tehsil.tehsilId is null" +
						" and model.panchayat.panchayatId is null order by rand()"); 
		 else if(priority.equalsIgnoreCase(IConstants.DISTRICT))
			 str.append(" and model.district.districtId is not null and model.constituency.constituencyId is null " +
						" and model.tehsil.tehsilId is null and  model.panchayat.panchayatId is null order by rand()");
		 Query query = getSession().createQuery(str.toString());
		 query.setParameter("location", location);
		 
		 if(maxIndex > 0)
		 {
			 query.setMaxResults(maxIndex);	 
		 }
		 return new HashSet(query.list());
		 
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getMobileNosCountForLocation(List<Long> Ids,String type,Long scopeId)
	{
		StringBuilder str = new StringBuilder();
		Query query = null;
		str.append("select count(distinct model.mobileNumber)");
		if(scopeId == 2)//District
			str.append(" ,model.district.districtId,model.district.districtName");
			if(scopeId == 3)//Constituency
			str.append(" ,model.constituency.constituencyId, model.constituency.name");
			if(scopeId == 5)//mandal
			str.append(" ,model.tehsil.tehsilId,model.tehsil.tehsilName");
		str.append(" from MobileNumbers model where");
		if(type.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" model.district.districtId is not null and model.constituency.constituencyId is null and " +
					" model.tehsil.tehsilId is null and  model.panchayat.panchayatId is null");
		else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" model.constituency.constituencyId is not null and model.tehsil.tehsilId is null" +
					" and model.panchayat.panchayatId is null");
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
		str.append(" model.tehsil.tehsilId is not null and model.panchayat.panchayatId is null");
		else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			str.append(" model.panchayat.panchayatId is not null");
		 str.append(" and model.mobileNumber is not null and length(model.mobileNumber) = 10 and model.mobileNumber <> '9999999999' ");
		 str.append("and model.isUsed = 'N' and model.isDeleted = 'N' ");
		if(scopeId == 2)//District
		str.append(" and model.district.districtId in (:Ids) group by model.district.districtId");
		if(scopeId == 3)//Constituency
		str.append(" and model.constituency.constituencyId in (:Ids) group by model.constituency.constituencyId");
		if(scopeId == 5)//mandal
		str.append(" and model.tehsil.tehsilId in (:Ids) group by model.tehsil.tehsilId");
		query = getSession().createQuery(str.toString());
		query.setParameterList("Ids", Ids);
		return query.list();
		
	}	
	
	@SuppressWarnings("unchecked")
	public Long getMobileNosTotalForLocationIDs(List<Long> Ids,String type,Long scopeId)
	{
		StringBuilder str = new StringBuilder();
		Query query = null;
		str.append("select count(distinct model.mobileNumber)");
		str.append(" from MobileNumbers model where");
		if(scopeId == 1)//District
		str.append(" model.district.districtId in (:Ids)");
		if(scopeId == 2)//Constituency
		str.append(" model.constituency.constituencyId in (:Ids)");
		if(scopeId == 4)//Mandal
		str.append(" model.tehsil.tehsilId in (:Ids)");	
		
		str.append(" and model.mobileNumber is not null and length(model.mobileNumber) = 10 and model.mobileNumber <> '9999999999' ");
		str.append("and model.isUsed = 'N' and model.isDeleted = 'N' ");
		query = getSession().createQuery(str.toString());
		query.setParameterList("Ids", Ids);
		return (Long) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public Long getMobileNosCountByIdsForLocationIDs(List<Long> Ids,String type,Long scopeId)
	{
		StringBuilder str = new StringBuilder();
		Query query = null;
		str.append("select count(distinct model.mobileNumber)");
		str.append(" from MobileNumbers model where");
		if(type.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" model.district.districtId is not null and model.constituency.constituencyId is null and " +
					" model.tehsil.tehsilId is null and  model.panchayat.panchayatId is null");
		else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" model.constituency.constituencyId is not null and model.tehsil.tehsilId is null" +
					" and model.panchayat.panchayatId is null");
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
		str.append(" model.tehsil.tehsilId is not null and model.panchayat.panchayatId is null");
		else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			str.append(" model.panchayat.panchayatId is not null");
		
		if(scopeId == 1)//District
		str.append(" and model.district.districtId in (:Ids)");
		if(scopeId == 2)//Constituency
		str.append(" and model.constituency.constituencyId in (:Ids)");
		if(scopeId == 4)//mandal
		str.append(" and model.tehsil.tehsilId in (:Ids)");
		 str.append(" and model.mobileNumber is not null and length(model.mobileNumber) = 10 and model.mobileNumber <> '9999999999' ");
		 str.append("and model.isUsed = 'N' and model.isDeleted = 'N' ");
		query = getSession().createQuery(str.toString());
		query.setParameterList("Ids", Ids);
		return (Long) query.uniqueResult();
	}
	public Set<String> getMobilenosBasedOnPriorityForLocationIDs(Long scopeId,List<Long> locations,int maxIndex,String priority) 
	{
		StringBuilder str = new StringBuilder(); 
		str.append("select distinct model.mobileNumber from MobileNumbers model where");
		 if(scopeId == 1)
		 str.append(" model.district.districtId in(:locations)")	;
		 if(scopeId == 2)
		 str.append(" model.constituency.constituencyId in(:locations)");
		if(scopeId == 4)//mandal
		str.append(" model.tehsil.tehsilId in(:locations)");
		 str.append(" and model.mobileNumber is not null and length(model.mobileNumber) = 10 and model.mobileNumber <> '9999999999' ");
		 str.append("and model.isUsed = 'N' and model.isDeleted = 'N' ");
		 if(priority.equalsIgnoreCase(IConstants.PANCHAYAT))
		  str.append(" and model.panchayat.panchayatId is not null order by rand()");
		 else if(priority.equalsIgnoreCase(IConstants.TEHSIL))
			 str.append(" and model.tehsil.tehsilId is not null and model.panchayat.panchayatId is null order by rand()"); 
		 else if(priority.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append(" and model.constituency.constituencyId is not null and model.tehsil.tehsilId is null" +
						" and model.panchayat.panchayatId is null order by rand()"); 
		 else if(priority.equalsIgnoreCase(IConstants.DISTRICT))
			 str.append(" and model.district.districtId is not null and model.constituency.constituencyId is null " +
						" and model.tehsil.tehsilId is null and  model.panchayat.panchayatId is null order by rand()");
		 Query query = getSession().createQuery(str.toString());
		 query.setParameterList("locations", locations);
		 
		 if(maxIndex > 0)
		 {
			 query.setMaxResults(maxIndex);	 
		 }
		 return new HashSet(query.list());
		 
	}
	public Integer updateMobileNos()
	{
		Query query = getSession().createQuery("update MobileNumbers model set model.isUsed = 'N' where model.isUsed = 'Y' ");
		return query.executeUpdate();
	}
	public Integer updateUsedMobileNos(List<String> mobileNos)
	{
		Query query = getSession().createQuery("update MobileNumbers model set model.isUsed = 'Y' where model.isUsed = 'N' and model.mobileNumber in(:mobileNos)");
		query.setParameterList("mobileNos", mobileNos);
		return query.executeUpdate();
	}
	
}
