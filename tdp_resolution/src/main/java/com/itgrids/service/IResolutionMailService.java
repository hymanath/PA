package com.itgrids.service;

import java.util.List;
import com.itgrids.dto.TdpResolutionVo;

public interface IResolutionMailService {

		public String sentEmails(TdpResolutionVo tdpResolutionVo);

		public List getResolutions(String days);
}
