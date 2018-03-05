package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.QuickRequestVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IUserService {

	public Long getUserDistrictByUserId(Long userId);
	public  ResultStatus sendMail(QuickRequestVO quickRequestVO);
}
