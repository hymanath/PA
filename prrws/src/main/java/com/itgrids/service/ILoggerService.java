package com.itgrids.service;

import com.itgrids.dto.ErrorLogVO;

public interface ILoggerService {

	public void saveErrorLog(ErrorLogVO errorLogVO);
}
