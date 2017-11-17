package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IAssemblyMlaDAO;
import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.service.IConstituencyWiseWorkStatusService;
import com.itgrids.utils.CommonMethodsUtilService;

@Service
@Transactional
public class ConstituencyWiseWorkStatusService implements IConstituencyWiseWorkStatusService {
	private static final Logger LOG = Logger.getLogger(ConstituencyWiseWorkStatusService.class);
	@Autowired
	private IConstituencyDAO constituencyDAO;
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private IAssemblyMlaDAO assemblyMlaDAO;
	
	public List<LocationVO> getLocationsNamesBySubLocation( InputVO inputVO){
		List<LocationVO> finalList = new ArrayList<LocationVO>(0);
		List<Object[]> objects=constituencyDAO.getLocationsNamesBySubLocation(inputVO.getLocationId());
		if(objects != null && objects.size() > 0){
			for(Object[] param : objects){
				LocationVO vo = new LocationVO();
				vo.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
				finalList.add(vo);
			}
		}
		return finalList;
	}
	public List<LocationVO> getDistrictNameAndMlaNameByConsitutency( InputVO inputVO){
		List<LocationVO> finalList = new ArrayList<LocationVO>(0);
		List<Object[]> objects=assemblyMlaDAO.getAllConstituencyDetails(inputVO.getLocationId());
		if(objects != null && objects.size() > 0){
			for(Object[] param : objects){
				LocationVO vo = new LocationVO();
				vo.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
				vo.setMlaName(commonMethodsUtilService.getStringValueForObject(param[2]));
				vo.setWorkName(commonMethodsUtilService.getStringValueForObject(param[3]));
				finalList.add(vo);
			}
		}
		return finalList;
	}
}
