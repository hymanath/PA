package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigInteger;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IRegistrationStatusDAO;
import com.itgrids.partyanalyst.model.RegistrationStatus;

public class RegistrationStatusDAO extends  GenericDaoHibernate<RegistrationStatus,Long> implements IRegistrationStatusDAO {
     public RegistrationStatusDAO(){
    	 super(RegistrationStatus.class);
     }
	
     //0partNo,1totalVoters,2polled count,3boothType,4insertedTime,5mobile
     public List<Object[]> getAllBoothsInfo(){
    	 Query query = getSession().createQuery("select booth.partNo,booth.totalVoters,model.registrationCount,model.boothType,model.insertedTime, " +
    	 		" model.mobile from RegistrationStatus model  Left Join model.booth booth where model.isDeleted ='N' order by booth.boothId");
    	 
    	 return query.list();
     }
     
   //0partNo,1totalVoters,2polled count,3boothType,4insertedTime,5mobile
     public List<Object[]> getAllKnownBoothsInfo(Long constituencyId,Long publicationId){
    	 Query query = getSession().createSQLQuery("select booth.part_no,booth.total_voters,rs.registrationCount,rs.booth_type,rs.inserted_time, " +
    	 		" rs.mobile_no,booth.location from (select booth1.booth_id as booth_id,max(rs1.inserted_time) as inserted_time from registration_status rs1,booth booth1 " +
    	 		" where rs1.booth_id = booth1.booth_id and rs1.is_deleted ='N' and booth1.constituency_id =:constituencyId and booth1.publication_date_id =:publicationId group by booth1.booth_id ) as uniqueResult , registration_status rs," +
    	 		" booth booth where rs.booth_id = uniqueResult.booth_id and rs.inserted_time = uniqueResult.inserted_time and rs.booth_id = booth.booth_id  " +
    	 		" and rs.is_deleted ='N' order by rs.booth_id ");
    	 query.setParameter("constituencyId", constituencyId);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
     
     //0polled count,1insertedTime,2mobile
     public List<Object[]> getAllUnKnownBoothsInfo(){
    	 Query query = getSession().createQuery("select model.registrationCount,model.insertedTime, " +
    	 		" model.mobile from RegistrationStatus model where model.isDeleted ='N' and model.booth.boothId is null");
    	 
    	 return query.list();
     }
   
     public List<BigInteger> getAllKnownBoothsInfoByConstituency(Long constituencyId,Long publicationId){
    	 Query query = getSession().createSQLQuery("select booth.booth_id " +
    	 		" from (select booth1.booth_id as booth_id,max(rs1.inserted_time) as inserted_time from registration_status rs1,booth booth1 " +
    	 		" where rs1.booth_id = booth1.booth_id and rs1.is_deleted ='N' and booth1.constituency_id =:constituencyId and booth1.publication_date_id =:publicationId and rs1.twoway_sms_mobile_id is not null group by booth1.booth_id ) as uniqueResult , registration_status rs," +
    	 		" booth booth where rs.booth_id = uniqueResult.booth_id and rs.inserted_time = uniqueResult.inserted_time and rs.booth_id = booth.booth_id  " +
    	 		" and rs.is_deleted ='N' order by rs.booth_id ");
    	 query.setParameter("constituencyId", constituencyId);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
     
     public List<BigInteger> getAllUnRecognizedBoothsInfoByConstituency(Long constituencyId,Long publicationId){
    	 Query query = getSession().createSQLQuery("select booth.booth_id " +
     	 		" from (select booth1.booth_id as booth_id,max(rs1.inserted_time) as inserted_time from registration_status rs1,booth booth1 " +
     	 		" where rs1.booth_id = booth1.booth_id and rs1.is_deleted ='N' and booth1.constituency_id =:constituencyId and booth1.publication_date_id =:publicationId and rs1.twoway_sms_mobile_id is null group by booth1.booth_id ) as uniqueResult , registration_status rs," +
     	 		" booth booth where rs.booth_id = uniqueResult.booth_id and rs.inserted_time = uniqueResult.inserted_time and rs.booth_id = booth.booth_id  " +
     	 		" and rs.is_deleted ='N' order by rs.booth_id ");
    	 query.setParameter("constituencyId", constituencyId);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
   //0partNo,1totalVoters,2polled count,3boothType,4insertedTime,5mobile
     public List<Object[]> getBoothsInfo(List<Long> boothIds,Long publicationId){
    	 StringBuilder str = new StringBuilder();
    	 Query query = getSession().createSQLQuery("select booth.booth_id,booth.total_voters,rs.registrationCount,rs.inserted_time " +
    	 		" from (select booth1.booth_id as booth_id,max(rs1.inserted_time) as inserted_time from registration_status rs1,booth booth1 " +
    	 		" where rs1.booth_id = booth1.booth_id and rs1.is_deleted ='N' and booth1.booth_id in(:boothIds) and booth1.publication_date_id =:publicationId  group by booth1.booth_id ) as uniqueResult , registration_status rs," +
    	 		" booth booth where rs.booth_id = uniqueResult.booth_id and rs.inserted_time = uniqueResult.inserted_time and rs.booth_id = booth.booth_id  " +
    	 		" and rs.is_deleted ='N' order by rs.booth_id ");
    	 query.setParameterList("boothIds", boothIds);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
    
     public List<Object[]> getRecognizeBoothsInfo(List<Long> boothIds,Long publicationId){
    	 Query query = getSession().createSQLQuery("select booth.booth_id,booth.total_voters,rs.registrationCount,rs.inserted_time " +
    	 		" from (select booth1.booth_id as booth_id,max(rs1.inserted_time) as inserted_time from registration_status rs1,booth booth1 " +
    	 		" where rs1.booth_id = booth1.booth_id and rs1.is_deleted ='N' and booth1.booth_id in(:boothIds) and booth1.publication_date_id =:publicationId and rs1.twoway_sms_mobile_id is not null group by booth1.booth_id ) as uniqueResult , registration_status rs," +
    	 		" booth booth where rs.booth_id = uniqueResult.booth_id and rs.inserted_time = uniqueResult.inserted_time and rs.booth_id = booth.booth_id  " +
    	 		" and rs.is_deleted ='N' order by rs.booth_id ");
    	 query.setParameterList("boothIds", boothIds);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
     public List<Object[]> getUnRecognizeBoothsInfo(List<Long> boothIds,Long publicationId){
    	 Query query = getSession().createSQLQuery("select booth.booth_id,booth.total_voters,rs.registrationCount,rs.inserted_time " +
    	 		" from (select booth1.booth_id as booth_id,max(rs1.inserted_time) as inserted_time from registration_status rs1,booth booth1 " +
    	 		" where rs1.booth_id = booth1.booth_id and rs1.is_deleted ='N' and booth1.booth_id in(:boothIds) and booth1.publication_date_id =:publicationId and rs1.twoway_sms_mobile_id is null group by booth1.booth_id ) as uniqueResult , registration_status rs," +
    	 		" booth booth where rs.booth_id = uniqueResult.booth_id and rs.inserted_time = uniqueResult.inserted_time and rs.booth_id = booth.booth_id  " +
    	 		" and rs.is_deleted ='N' order by rs.booth_id ");
    	 query.setParameterList("boothIds", boothIds);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
     
     public List<BigInteger> getRecognizeByBooths(List<Long> boothIds,Long publicationId){
    	 Query query = getSession().createSQLQuery("select booth.booth_id " +
    	 		" from (select booth1.booth_id as booth_id,max(rs1.inserted_time) as inserted_time from registration_status rs1,booth booth1 " +
    	 		" where rs1.booth_id = booth1.booth_id and rs1.is_deleted ='N' and booth1.publication_date_id =:publicationId and rs1.twoway_sms_mobile_id is not null and rs1.booth_id in(:boothIds) group by booth1.booth_id ) as uniqueResult , registration_status rs," +
    	 		" booth booth where rs.booth_id = uniqueResult.booth_id and rs.inserted_time = uniqueResult.inserted_time and rs.booth_id = booth.booth_id  " +
    	 		" and rs.is_deleted ='N' order by rs.booth_id ");
    	 query.setParameterList("boothIds", boothIds);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
     
     public List<BigInteger> getUnrecognizeByBooths(List<Long> boothIds,Long publicationId){
    	 Query query = getSession().createSQLQuery("select booth.booth_id " +
     	 		" from (select booth1.booth_id as booth_id,max(rs1.inserted_time) as inserted_time from registration_status rs1,booth booth1 " +
     	 		" where rs1.booth_id = booth1.booth_id and rs1.is_deleted ='N' and booth1.publication_date_id =:publicationId and rs1.twoway_sms_mobile_id is null and rs1.booth_id in(:boothIds) group by booth1.booth_id ) as uniqueResult , registration_status rs," +
     	 		" booth booth where rs.booth_id = uniqueResult.booth_id and rs.inserted_time = uniqueResult.inserted_time and rs.booth_id = booth.booth_id  " +
     	 		" and rs.is_deleted ='N' order by rs.booth_id ");
    	 query.setParameterList("boothIds", boothIds);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
     
     public List<BigInteger> getAllBooths(List<Long> boothIds,Long publicationId){
    	 Query query = getSession().createSQLQuery("select booth.booth_id " +
     	 		" from (select booth1.booth_id as booth_id,max(rs1.inserted_time) as inserted_time from registration_status rs1,booth booth1 " +
     	 		" where rs1.booth_id = booth1.booth_id and rs1.is_deleted ='N' and booth1.publication_date_id =:publicationId and rs1.booth_id in(:boothIds) group by booth1.booth_id ) as uniqueResult , registration_status rs," +
     	 		" booth booth where rs.booth_id = uniqueResult.booth_id and rs.inserted_time = uniqueResult.inserted_time and rs.booth_id = booth.booth_id  " +
     	 		" and rs.is_deleted ='N' order by rs.booth_id ");
    	 query.setParameterList("boothIds", boothIds);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
     
     public List<Object[]> getRecognizeByBoothsTotalVotersAndPolledVotes(List<Long> boothIds,Long publicationId){
    	 Query query = getSession().createSQLQuery("select sum(booth.total_voters),sum(rs.registrationCount) " +
    	 		" from (select booth1.booth_id as booth_id,max(rs1.inserted_time) as inserted_time from registration_status rs1,booth booth1 " +
    	 		" where rs1.booth_id = booth1.booth_id and rs1.is_deleted ='N' and booth1.publication_date_id =:publicationId and rs1.twoway_sms_mobile_id is not null and rs1.booth_id in(:boothIds) group by booth1.booth_id ) as uniqueResult , registration_status rs," +
    	 		" booth booth where rs.booth_id = uniqueResult.booth_id and rs.inserted_time = uniqueResult.inserted_time and rs.booth_id = booth.booth_id  " +
    	 		" and rs.is_deleted ='N' order by rs.booth_id ");
    	 query.setParameterList("boothIds", boothIds);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
     
     public List<Object[]> getUnrecognizeByBoothsTotalVotersAndPolledVotes(List<Long> boothIds,Long publicationId){
    	 Query query = getSession().createSQLQuery("select sum(booth.total_voters),sum(rs.registrationCount) " +
     	 		" from (select booth1.booth_id as booth_id,max(rs1.inserted_time) as inserted_time from registration_status rs1,booth booth1 " +
     	 		" where rs1.booth_id = booth1.booth_id and rs1.is_deleted ='N' and booth1.publication_date_id =:publicationId and rs1.twoway_sms_mobile_id is null and rs1.booth_id in(:boothIds) group by booth1.booth_id ) as uniqueResult , registration_status rs," +
     	 		" booth booth where rs.booth_id = uniqueResult.booth_id and rs.inserted_time = uniqueResult.inserted_time and rs.booth_id = booth.booth_id  " +
     	 		" and rs.is_deleted ='N' order by rs.booth_id ");
    	 query.setParameterList("boothIds", boothIds);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
     
     public List<Object[]> getAllBoothsTotalVotersAndPolledVotes(List<Long> boothIds,Long publicationId){
    	 Query query = getSession().createSQLQuery("select sum(booth.total_voters),sum(rs.registrationCount) " +
     	 		" from (select booth1.booth_id as booth_id,max(rs1.inserted_time) as inserted_time from registration_status rs1,booth booth1 " +
     	 		" where rs1.booth_id = booth1.booth_id and rs1.is_deleted ='N' and booth1.publication_date_id =:publicationId and rs1.booth_id in(:boothIds) group by booth1.booth_id ) as uniqueResult , registration_status rs," +
     	 		" booth booth where rs.booth_id = uniqueResult.booth_id and rs.inserted_time = uniqueResult.inserted_time and rs.booth_id = booth.booth_id  " +
     	 		" and rs.is_deleted ='N' order by rs.booth_id ");
    	 query.setParameterList("boothIds", boothIds);
    	 query.setParameter("publicationId", publicationId);
    	 return query.list();
     }
}
