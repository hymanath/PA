package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingStatusTempDAO;
import com.itgrids.partyanalyst.model.PartyMeetingStatusTemp;

public class PartyMeetingStatusTempDAO extends GenericDaoHibernate<PartyMeetingStatusTemp, Long> implements IPartyMeetingStatusTempDAO {

	public PartyMeetingStatusTempDAO() {
		super(PartyMeetingStatusTemp.class);
	}
	
	 public int deleteAllRecordsFromTemp(){
	    	
    	Query query = getSession().createSQLQuery(" delete from party_meeting_status_temp2 ");
    	return query.executeUpdate();
    }
	 
	public int setPrimaryKeyAutoIncrementToOneToTemp(){
    	Query query = getSession().createSQLQuery(" ALTER TABLE party_meeting_status_temp2 AUTO_INCREMENT = 1 ");
    	return query.executeUpdate();
	}
	
	 public int insertPartyofficeAndIvrStatusToTemp(){
	    	
	    	Query query = getSession().createSQLQuery("" +
	    	"  INSERT INTO party_meeting_status_temp2(party_meeting_id,party_office_status,ivr_status,third_party_status) " +
	        "         SELECT PM.party_meeting_id,PM.is_conducted,PM.is_conducted_by_ivr,PM.third_party_status " +
	        "         FROM   party_meeting PM " +
	        "         WHERE PM.start_date IS NOT NULL AND "+
	        "         PM.is_active = 'Y' ");
	    	return query.executeUpdate();
	 }
	 
	 public int updatePartyMeetingStatus1(){
	    	
	    	Query query = getSession().createSQLQuery("" +
	    	"  UPDATE party_meeting_status_temp2 SET meeting_status = 'Y' "+
	        "  WHERE  party_office_status IS NOT NULL AND " +
	        "         ivr_status IS NOT NULL AND "+
	        "         party_office_status = 'Y' AND "+
	        "         ivr_status = 'Y' " ); 
	    	return query.executeUpdate();
	 }
	 public int updatePartyMeetingStatus2(){
	    	
	    	Query query = getSession().createSQLQuery("" +
	    	"  UPDATE party_meeting_status_temp2 SET meeting_status = 'N' "+ 
			"   WHERE  party_office_status IS NOT NULL AND "+ 
			"	       ivr_status IS NOT NULL AND "+ 
			"	       party_office_status = 'N' AND "+ 
			"	       ivr_status = 'N' "); 
	    	return query.executeUpdate();
	    	
	    }
	    public int updatePartyMeetingStatus3(){
	    	
	    	Query query = getSession().createSQLQuery("" +
	    	"  UPDATE party_meeting_status_temp2 SET meeting_status = 'M' "+
	        "   WHERE  party_office_status IS NOT NULL AND "+
	        "   ivr_status IS NOT NULL AND "+
	        "   party_office_status = 'Y' AND "+
	        "  ivr_status = 'N' ");
	    	return query.executeUpdate();
	    	
	    }
	    public int updatePartyMeetingStatus4(){
	    	
	    	Query query = getSession().createSQLQuery("" +
			" UPDATE party_meeting_status_temp2 SET meeting_status = 'M' "+ 
			" WHERE  party_office_status IS NOT NULL AND "+
			"       ivr_status IS NOT NULL AND "+
			"       party_office_status = 'N' AND "+
			"      ivr_status = 'Y' ");
	    	return query.executeUpdate();
	    	
	    }
	    
	    public int updatePartyMeetingStatus5(){
	    	
	    	Query query = getSession().createSQLQuery("" +
			" UPDATE party_meeting_status_temp2 SET meeting_status = 'Y' "+ 
			" WHERE  party_office_status IS NOT NULL AND "+
			"       ivr_status IS NULL AND "+
			"       party_office_status = 'Y' ");
	    	return query.executeUpdate();
	    	
	    }
	    
	    public int updatePartyMeetingStatus6(){
	    	
	    	Query query = getSession().createSQLQuery("" +
			" UPDATE party_meeting_status_temp2 SET meeting_status = 'N' "+ 
			" WHERE  party_office_status IS NOT NULL AND "+
			"       ivr_status IS NULL AND "+
			"       party_office_status = 'N' ");
	    	return query.executeUpdate();
	    }
	    
	    public int updatePartyMeetingStatus7(){
	    	
	    	Query query = getSession().createSQLQuery("" +
			"UPDATE party_meeting_status_temp2 SET meeting_status = 'Y' "+ 
			"WHERE  party_office_status IS NULL AND "+
			"       ivr_status IS NOT NULL AND "+
			"       ivr_status = 'Y' ");
	    	return query.executeUpdate();
	    }
	    
	   public int updatePartyMeetingStatus8(){
	    	
	    	Query query = getSession().createSQLQuery("" +
			"UPDATE party_meeting_status_temp2 SET meeting_status = 'N' "+
			"WHERE  party_office_status IS NULL AND "+
			"       ivr_status IS NOT NULL AND "+
			"       ivr_status = 'N' ");
	    	return query.executeUpdate();
	    }
	   public int updatePartyMeetingStatus9(){
	   	
	   	Query query = getSession().createSQLQuery("" +
		" UPDATE party_meeting_status_temp2 SET meeting_status = 'NU' "+ 
		" WHERE  party_office_status IS NULL AND "+
		"	     ivr_status IS NULL ");
	   	return query.executeUpdate();
	   }
	   
	   public int updatePartyMeetingStatus10(){
		 	Query query = getSession().createSQLQuery("" +
			" UPDATE party_meeting_status_temp2 SET meeting_status = 'Y' "+ 
			" WHERE  third_party_status IS NOT NULL AND third_party_status = 'Y' and meeting_status = 'M' ");
			return query.executeUpdate();
	   }
	   public int updatePartyMeetingStatus11(){
		 	Query query = getSession().createSQLQuery("" +
			" UPDATE party_meeting_status_temp2 SET meeting_status = 'N' "+ 
			" WHERE  third_party_status IS NOT NULL AND third_party_status = 'N' and meeting_status = 'M' ");
			return query.executeUpdate();
	  }
	  public int setInsertedDate(Date currentDateTime){
		   Query query = getSession().createSQLQuery(" UPDATE party_meeting_status_temp2 SET inserted_time =:currentDateTime  ");
		   query.setParameter("currentDateTime", currentDateTime);
		   return query.executeUpdate();
	  }
	  
	  public int insertDataToPartyMeetingStatusFromTemp(){
		  
		  Query query = getSession().createSQLQuery("" +
		  " insert into party_meeting_status(party_meeting_id,party_office_status,ivr_status,third_party_status,meeting_status,inserted_time) " +
		  " select party_meeting_id,party_office_status,ivr_status,third_party_status,meeting_status,inserted_time from party_meeting_status_temp2 ");
		  return query.executeUpdate();
	  }
}
