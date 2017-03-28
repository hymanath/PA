package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoiceRecordingDetails;

public interface IVoiceRecordingDetailsDAO extends GenericDao<VoiceRecordingDetails,Long>{
	
	public List<Object[]> getAllTheRecordingDetailsOfUser(Long userId);

    public List<String> getAllVoiceRecordingNames();
}
