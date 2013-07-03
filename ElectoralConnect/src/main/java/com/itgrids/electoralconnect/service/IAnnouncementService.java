package com.itgrids.electoralconnect.service;

import com.itgrids.electoralconnect.dto.AnnouncementVO;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.dto.ResultStatus;

public interface IAnnouncementService {
	public ResultStatus uploadFile(AnnouncementVO announcementVO,RegistrationVO user);
}
