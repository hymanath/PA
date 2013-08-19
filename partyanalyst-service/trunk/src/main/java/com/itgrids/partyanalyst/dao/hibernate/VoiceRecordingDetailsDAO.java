package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoiceRecordingDetailsDAO;
import com.itgrids.partyanalyst.model.VoiceRecordingDetails;

public class VoiceRecordingDetailsDAO extends GenericDaoHibernate<VoiceRecordingDetails,Long> implements IVoiceRecordingDetailsDAO 
{

		public VoiceRecordingDetailsDAO() {
			super(VoiceRecordingDetails.class);
		}
		
		public List<Object[]> getAllTheRecordingDetailsOfUser(Long userId)
		{
			Query query = getSession().createQuery("select model.recordingName , model.recordingDescription  , model.user.userId " +
					" from VoiceRecordingDetails model where model.user.userId = :userId");
			
			query.setParameter("userId", userId);
			
			return query.list();
			
		}
}
