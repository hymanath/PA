package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.DrainsVO;
import com.itgrids.dto.InputVO;

public interface IDrainsService 
{
	public List<DrainsVO> getDrainsInfoLocationWise(InputVO inputVO);
	public DrainsVO getDrainsInfoStateWise(InputVO inputVO);
	
	
	
}

