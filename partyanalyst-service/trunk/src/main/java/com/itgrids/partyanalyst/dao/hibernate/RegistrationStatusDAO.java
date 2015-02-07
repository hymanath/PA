package com.itgrids.partyanalyst.dao.hibernate;

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
     public List<Object[]> getAllKnownBoothsInfo(){
    	 Query query = getSession().createSQLQuery("select booth.part_no,booth.total_voters,rs.registrationCount,rs.booth_type,rs.inserted_time, " +
    	 		" rs.mobile_no from (select booth1.booth_id as booth_id,max(rs1.inserted_time) as inserted_time from registration_status rs1,booth booth1 " +
    	 		" where rs1.booth_id = booth1.booth_id and rs1.is_deleted ='N' group by booth1.booth_id ) as uniqueResult , registration_status rs," +
    	 		" booth booth where rs.booth_id = uniqueResult.booth_id and rs.inserted_time = uniqueResult.inserted_time and rs.booth_id = booth.booth_id  " +
    	 		" and rs.is_deleted ='N' order by rs.booth_id ");
    	 
    	 return query.list();
     }
     
     //0polled count,1insertedTime,2mobile
     public List<Object[]> getAllUnKnownBoothsInfo(){
    	 Query query = getSession().createQuery("select model.registrationCount,model.insertedTime, " +
    	 		" model.mobile from RegistrationStatus model where model.isDeleted ='N' and model.booth.boothId is null");
    	 
    	 return query.list();
     }
}
