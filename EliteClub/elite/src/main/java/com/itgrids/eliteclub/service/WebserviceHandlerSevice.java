package com.itgrids.eliteclub.service;

import java.util.List;

import com.itgrids.eliteclub.dto.CategoryResponseVo;
import com.itgrids.eliteclub.dto.UserContactsInputVO;
import com.itgrids.eliteclub.model.Category;

public interface WebserviceHandlerSevice {

	
	
	public Object loadCategories(UserContactsInputVO inputVo);
	public CategoryResponseVo  getAudioFilesBasedOnCategories(String imei);
	
	
}
