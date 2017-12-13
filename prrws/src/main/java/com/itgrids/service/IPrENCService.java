package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.EncVO;
import com.itgrids.dto.InputVO;

public interface IPrENCService {

	public List<EncVO> getLocationWiseRoadsInformation(InputVO inputVO);

}
