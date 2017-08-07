package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceTabUserDAO;
import com.itgrids.partyanalyst.model.PartyMeeting;
import com.itgrids.partyanalyst.model.PartyMeetingAttendanceTabUser;

public class PartyMeetingAttendanceTabUserDAO extends GenericDaoHibernate<PartyMeetingAttendanceTabUser,Long> implements IPartyMeetingAttendanceTabUserDAO{

	public PartyMeetingAttendanceTabUserDAO()
	{
		super(PartyMeetingAttendanceTabUser.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<PartyMeeting> getPartyMeetingsOfAttendanceTabUser(Long attendanceTabUserId)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting FROM PartyMeetingAttendanceTabUser model where " +
				" model.isDeleted = 'N' and model.attendanceTabUser.attendanceTabUserId = :attendanceTabUserId and model.attendanceTabUser.isEnabled = 'Y'");
		query.setParameter("attendanceTabUserId",attendanceTabUserId);
		return query.list(); 
	}

	
			@SuppressWarnings("unchecked")
		public List<Object[]> getPartyMeetingsTabUserNameByDistrict(Long districtId){
			StringBuilder sb=new StringBuilder();
			sb.append("select distinct model.attendanceTabUser.attendanceTabUserId," +
					" model.attendanceTabUser.firstname,model.attendanceTabUser.lastname,"); 
			sb.append("model.attendanceTabUser.mobile from PartyMeetingAttendanceTabUser model  ");
			sb.append(" where model.attendanceTabUser.isEnabled='Y' ");
			if(districtId !=null && districtId.longValue() > 0L){
			sb.append("and model.partyMeeting.meetingAddress.district.districtId=:districtId ");
			}
			Query query = getSession().createQuery(sb.toString());
			if(districtId !=null && districtId.longValue() > 0L){
			query.setParameter("districtId",districtId);
			}
			return query.list(); 
		}
		public List<Object[]> getPartyMeetingTabUserDetails(Long partyMeetingId){
			StringBuilder sb=new StringBuilder();
			sb.append(" select distinct model.attendanceTabUser.attendanceTabUserId ," +//0
					"model.attendanceTabUser.username, " +//1
					"model.attendanceTabUser.firstname," +//2
					"model.attendanceTabUser.lastname," +//3
					"model.attendanceTabUser.mobile, "+//4
					"model.partyMeetingAttendanceTabUserId "+//5
			       " from PartyMeetingAttendanceTabUser model "+
			        " where model.partyMeeting.partyMeetingId=:partyMeetingId and model.isDeleted='N' ");
			Query query = getSession().createQuery(sb.toString());
			if(partyMeetingId !=null && partyMeetingId.longValue() > 0L)
			query.setParameter("partyMeetingId",partyMeetingId);
			return query.list(); 
		}
		public List<Object[]> getTabuserTotaldetailsFromMeetingId(Long partyMeetingId,List<Long>attendanceTabUserIdList){
		      StringBuilder sb=new StringBuilder();
		      sb.append("select distinct model.partyMeetingAttendanceTabUserId , "+
		              "model.partyMeeting.partyMeetingId , "+
		              "model.attendanceTabUser.attendanceTabUserId , "+
		              "model.isDeleted , "+
		              "model.insertedBy.userId , "+
		              "model.insertedTime "+
		              " from PartyMeetingAttendanceTabUser model "+
		              "where model.partyMeeting.partyMeetingId=:partyMeetingId and "+
		              "model.attendanceTabUser.attendanceTabUserId in (:attendanceTabUserIdList) and model.isDeleted='N' ");
		      Query query = getSession().createQuery(sb.toString());
		      query.setParameter("partyMeetingId",partyMeetingId);
		       query.setParameterList("attendanceTabUserIdList", attendanceTabUserIdList);
		      return query.list(); 
		  
		      
		    }
		
		public Integer updatePartyMeetingAttendance(Long meetingId){
	        Query query=getSession().createQuery(" update PartyMeetingAttendanceTabUser model set model.isDeleted = 'Y' where model.partyMeetingId =:meetingId ");    
	        query.setParameter("meetingId",meetingId);
	        return  query.executeUpdate();
	      }
}
