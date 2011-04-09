package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.QuickRequestVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IMailService {

	 public ResultStatus sendQuickRequestEmailToAdmin(QuickRequestVO quickRequestVO);

}
