package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.UserProfileVO;

public interface IUserProfileService {
	public List<UserProfileVO> getPartyAnalystLatestUpdates(Date fromDate,Date toDate,Long userId);
}
