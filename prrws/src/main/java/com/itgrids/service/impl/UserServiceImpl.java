package com.itgrids.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.IFavouriteComponentDAO;
import com.itgrids.dao.IHamletDAO;
import com.itgrids.dao.IPanchayatDAO;
import com.itgrids.dao.IParliamentAssemblyDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dao.IUserAccessLevelValueDAO;
import com.itgrids.dao.IUserDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.ResultVO;
import com.itgrids.dto.UserVO;
import com.itgrids.model.FavouriteComponent;
import com.itgrids.model.User;
import com.itgrids.service.IPmRequestDetailsService;
import com.itgrids.service.IUserService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	public static Logger log = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private IConstituencyDAO constituencyDAO;
	@Autowired
	private IDistrictDAO districtDAO;
	@Autowired
	private IParliamentAssemblyDAO parliamentAssemblyDAO;
	@Autowired
	private ITehsilDAO tehsilDAO;
	@Autowired
	private IPanchayatDAO panchayatDAO;
	@Autowired
	private IHamletDAO hamletDAO;
	@Autowired
	private IFavouriteComponentDAO favouriteComponentDAO;
	@Autowired
	private IPmRequestDetailsService pmRequestDetailsService;
	@Autowired
	private IUserAccessLevelValueDAO userAccessLevelValueDAO;

	@Override
	public UserVO userAuthentication(String userName, String password,HttpServletRequest request) {
		UserVO userVO = new UserVO();
		HttpSession httpSession = request.getSession();
		Long userId = null;
		//User user = userDAO.loginAuthentication(userName, password);
		Object[] userObj = userDAO.getUrlForMatchedCredentials(userName, password);
		if(userObj != null && userObj.length > 0){
			String url=userObj[0] != null ? userObj[0].toString():"";
			userId =commonMethodsUtilService.getLongValueForObject(userObj[1]);
			if (url != null && url.trim().length() > 0) {
				userVO.setUrl(url);
				userVO.setUserId(userId);
				pmRequestDetailsService.getPmOffceUserDetails(userId,userVO);
				userVO.setResponceCode(1l);
				userVO.setStatus("Valid user");
			}else {
				userVO.setResponceCode(0l);
				userVO.setStatus("InValid user");
			}
		}else{
			userVO.setResponceCode(0l);
			userVO.setStatus("InValid user");
		}
		//Setting User  Access Details
		if(userId != null && userId.longValue() > 0L){
			List<Object[]> accessList = userAccessLevelValueDAO.getUserAccessDetails(userId);
			if(accessList != null && !accessList.isEmpty()){
				for (Object[] param : accessList) {
					userVO.setAccessLvelId(commonMethodsUtilService.getLongValueForObject(param[0]));
					userVO.setAccessLevelValue(commonMethodsUtilService.getLongValueForObject(param[1]));
				}
				
			}
		}
		if(userVO.getAccessLvelId() != null && userVO.getAccessLvelId().longValue() == 2L){
			List<Object[]> districtList = districtDAO.getMgnregsDistrictMappingCode(userVO.getAccessLevelValue());
			if(districtList != null && !districtList.isEmpty()){
				for (Object[] param : districtList) {
					userVO.setPrDistrictId(commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
		}else{
			userVO.setPrDistrictId("-1");
		}
		if(userVO != null && userVO.getStatus().equalsIgnoreCase("Valid user")){
			httpSession.setAttribute("User", userVO);
		}
		return userVO;
	}
	
	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return AddressVO 
	 */
	public AddressVO getOriginalLocationIdForTempId( Long searchLevelId,String searchLevelValue,String fromPage,String toPage){
		AddressVO addressVO = new AddressVO();
		try {
			List<Object[]> resultList = getAssignedSearchIdByTypeId(searchLevelId,searchLevelValue,fromPage,toPage);
			if(resultList != null && resultList.size()>0){
				for (Object[] param : resultList) {
					addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[0]));
					addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[1]));
					addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[2]));
					addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[3]));
					addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[4]));
					addressVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[5]));
					addressVO.setHamletName(commonMethodsUtilService.getStringValueForObject(param[6]));
				}
			}
		} catch (Exception e) {
			log.error("error occured in getOriginalLocationIdForTempId() method from UserServiceImpl class.",e);
		}
		return addressVO;
	}
	
	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getAssignedSearchIdByTypeId( Long searchLevelId,String searchLevelValue,String fromPage,String toPage){
		try{
			if(toPage.equalsIgnoreCase("FMS")){
				if(fromPage.equalsIgnoreCase("FMS"))
					return getFMSLocationIdfromFMSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("RWS"))
					return getFMSLocationIdfromRWSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("MGNREGS"))
					return getFMSLocationIdfromMGNREGSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("PRIS"))
					return getFMSLocationIdfromPRISLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("ENC"))
					return getFMSLocationIdfromENCLocationId(searchLevelId,searchLevelValue);
			}
			else if(toPage.equalsIgnoreCase("RWS")){
				if(fromPage.equalsIgnoreCase("RWS"))
					return getRWSLocationIdfromRWSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("FMS"))
					return getRWSLocationIdfromFMSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("MGNREGS"))
					return getRWSLocationIdfromMGNREGSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("PRIS"))
					return getRWSLocationIdfromPRISLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("ENC"))
					return getRWSLocationIdfromENCLocationId(searchLevelId,searchLevelValue);
			}
			else if(toPage.equalsIgnoreCase("MGNREGS")){
				if(fromPage.equalsIgnoreCase("MGNREGS"))
					return getMGNREGSLocationIdfromMGNREGSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("FMS"))
					return getMGNREGSLocationIdfromFMSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("RWS"))
					return getMGNREGSLocationIdfromRWSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("PRIS"))
					return getMGNREGSLocationIdfromPRISLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("ENC"))
					return getMGNREGSLocationIdfromENCLocationId(searchLevelId,searchLevelValue);
			}
			else if(toPage.equalsIgnoreCase("PRIS")){
				if(fromPage.equalsIgnoreCase("PRIS"))
					return getPRISLocationIdfromPRISLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("FMS"))
					return getPRISLocationIdfromFMSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("RWS"))
					return getPRISLocationIdfromRWSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("MGNREGS"))
					return getPRISLocationIdfromMGNREGSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("ENC"))
					return getPRISLocationIdfromENCLocationId(searchLevelId,searchLevelValue);
			}
			else if(toPage.equalsIgnoreCase("ENC")){
				if(fromPage.equalsIgnoreCase("ENS"))
					return getENSLocationIdfromENSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("FMS"))
					return getENCLocationIdfromFMSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("RWS"))
					return getENCLocationIdfromRWSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("MGNREGS"))
					return getENCLocationIdfromMGNREGSLocationId(searchLevelId,searchLevelValue);
				else if(fromPage.equalsIgnoreCase("PRIS"))
					return getENCLocationIdfromPRISLocationId(searchLevelId,searchLevelValue);
			}
		}catch(Exception e){
			log.error("error occured in getAssignedSearchIdByTypeId() method",e);
		}
		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getFMSLocationIdfromFMSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getFMSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getFMSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getFMSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getFMSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getFMSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getFMSLocationIdfromFMSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}
	
	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getRWSLocationIdfromRWSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getRWSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getRWSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getRWSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getRWSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getRWSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getRWSLocationIdfromRWSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}
	
	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getMGNREGSLocationIdfromMGNREGSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getMGNREGSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getMGNREGSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getMGNREGSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getMGNREGSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getMGNREGSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getMGNREGSLocationIdfromMGNREGSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}
	
	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getPRISLocationIdfromPRISLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getPRISLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getPRISLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getPRISLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getPRISLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getPRISLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getPRISLocationIdfromPRISLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}
	
	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getENSLocationIdfromENSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getENSLocationIdfromENSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getENSLocationIdfromENSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getENSLocationIdfromENSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getENSLocationIdfromENSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getENSLocationIdfromENSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getENSLocationIdfromENSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}
	
	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getFMSLocationIdfromRWSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getFMSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getFMSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getFMSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getFMSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getFMSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getFMSLocationIdfromRWSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getFMSLocationIdfromMGNREGSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getFMSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getFMSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getFMSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getFMSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getFMSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getFMSLocationIdfromMGNREGSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getFMSLocationIdfromPRISLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getFMSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getFMSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getFMSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getFMSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getFMSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getFMSLocationIdfromPRISLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getFMSLocationIdfromENCLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getFMSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getFMSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getFMSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getFMSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getFMSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getFMSLocationIdfromENCLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getRWSLocationIdfromFMSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getRWSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getRWSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getRWSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getRWSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getRWSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getRWSLocationIdfromFMSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getRWSLocationIdfromMGNREGSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getRWSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getRWSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getRWSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getRWSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getRWSLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getRWSLocationIdfromMGNREGSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getRWSLocationIdfromPRISLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getRWSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getRWSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getRWSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getRWSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getRWSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getRWSLocationIdfromPRISLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getRWSLocationIdfromENCLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getRWSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getRWSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getRWSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getRWSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getRWSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getRWSLocationIdfromENCLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getMGNREGSLocationIdfromFMSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getMGNREGSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getMGNREGSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getMGNREGSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getMGNREGSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getMGNREGSLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getMGNREGSLocationIdfromFMSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getMGNREGSLocationIdfromRWSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getMGNREGSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getMGNREGSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getMGNREGSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getMGNREGSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getMGNREGSLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getMGNREGSLocationIdfromRWSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getMGNREGSLocationIdfromPRISLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getMGNREGSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getMGNREGSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getMGNREGSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getMGNREGSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getMGNREGSLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getMGNREGSLocationIdfromPRISLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getMGNREGSLocationIdfromENCLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getMGNREGSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getMGNREGSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getMGNREGSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getMGNREGSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getMGNREGSLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getMGNREGSLocationIdfromENCLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getPRISLocationIdfromFMSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getPRISLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getPRISLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getPRISLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getPRISLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getPRISLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getPRISLocationIdfromFMSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getPRISLocationIdfromRWSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getPRISLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getPRISLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getPRISLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getPRISLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getPRISLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getPRISLocationIdfromRWSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getPRISLocationIdfromMGNREGSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getPRISLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getPRISLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getPRISLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getPRISLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getPRISLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getPRISLocationIdfromMGNREGSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getPRISLocationIdfromENCLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getPRISLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getPRISLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getPRISLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getPRISLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getPRISLocationIdfromENCLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getPRISLocationIdfromENCLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getENCLocationIdfromFMSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getENCLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getENCLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getENCLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getENCLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getENCLocationIdfromFMSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getENCLocationIdfromFMSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getENCLocationIdfromRWSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getENCLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getENCLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getENCLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getENCLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getENCLocationIdfromRWSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getENCLocationIdfromRWSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getENCLocationIdfromMGNREGSLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getENCLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getENCLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getENCLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getENCLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getENCLocationIdfromMGNREGSLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getENCLocationIdfromMGNREGSLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}

	/**
	 * @Author : Srishailam Pittala
	 * @Date : 7th July,2017
	 * @return List<Object[]> 
	 */
	public List<Object[]> getENCLocationIdfromPRISLocationId(Long searchLevelId,String searchLocationValue){
		try {
			if(searchLevelId != null && searchLevelId == IConstants.DISTRICT_LEVEL_SCOPE_ID )
				return districtDAO.getENCLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID)
				return parliamentAssemblyDAO.getENCLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.CONSTITUENCY_LEVEL_SCOPE_ID)
				return constituencyDAO.getENCLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.MANDAL_LEVEL_SCOPE_ID )
				return tehsilDAO.getENCLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.VILLAGE_LEVEL_SCOPE_ID )
				return panchayatDAO.getENCLocationIdfromPRISLocationId(searchLocationValue);
			else if(searchLevelId != null && searchLevelId == IConstants.HAMLET_LEVEL_SCOPE_ID)
				return hamletDAO.getENCLocationIdfromPRISLocationId(searchLocationValue);
		} catch (Exception e) {
			log.error("Exception occured in getFMSLocationIdfromRWSLocationId from UserServiceImpl ",e);
		}

		return null;
	}
	/**
	 * @Author : Santosh Kumar Verma
	 * @Date : 27-09-2017
	 * @param InputVO inputVO
	 * @return String  
	 */
	public ResultVO saveFavouriteComponentDtls(IdNameVO inputVO) {
		ResultVO statusVO = new ResultVO();
		statusVO.setMessage("success");
		statusVO.setStatusCode(0);
		try {
			if (inputVO.getName() != null && inputVO.getName().trim().length() > 0) {
				List<Long> componentsIdList = favouriteComponentDAO.getFavouriteComponentId(inputVO.getName());
				if (componentsIdList == null || componentsIdList.size() == 0) {
					Long maxOrder = favouriteComponentDAO.getMaxOrderNo();
					DateUtilService dateUtil = new DateUtilService();
					FavouriteComponent model = new FavouriteComponent();
					model.setName(inputVO.getName());
					model.setUrl(inputVO.getUrl());
					model.setInsertedTime(dateUtil.getCurrentDateAndTime());
					model.setUpdatedTime(dateUtil.getCurrentDateAndTime());
					model.setIsDeleted("N");
					model.setPageId(inputVO.getId());
					if(maxOrder != null && maxOrder > 0L)
						model.setOrderNo(maxOrder+1L);
					else
						maxOrder = 101L;
					favouriteComponentDAO.save(model);
					statusVO.setMessage("success");
					statusVO.setStatusCode(0);
				}
			}
		} catch (Exception e) {
			log.error("Exception occured in saveFavouriteComponentDtls from UserServiceImpl ",e);
			statusVO.setMessage("fail");
			statusVO.setStatusCode(1);
		}
		return statusVO;
	}
	/**
	 * @Author : Santosh Kumar Verma
	 * @Date : 27-09-2017
	 * @return List<IdNameVO  
	 */
	public List<IdNameVO> getFavouriteComponents(IdNameVO inputVO) {
		List<IdNameVO> resultList = new ArrayList<>(0);
		try {
			List<Object[]> favouriteComponentObjList = favouriteComponentDAO.getFavouriteComponencts(inputVO);
			if (favouriteComponentObjList != null && favouriteComponentObjList.size() > 0) {
				for (Object[] param : favouriteComponentObjList) {
					IdNameVO componentVO = new IdNameVO();
					componentVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					componentVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					componentVO.setUrl(commonMethodsUtilService.getStringValueForObject(param[2]));
					componentVO.setOrderNo(commonMethodsUtilService.getLongValueForObject(param[3]));
					resultList.add(componentVO);
				}
			}
		} catch (Exception e) {
			log.error("Exception occured in getFavouriteComponents from UserServiceImpl ",e);
		}
		return resultList;
	}
	
	/**
	 * @Author : Sravanth
	 * @Date : 10-10-2017
	 * @param InputVO inputVO
	 * @return String  
	 */
	public ResultVO saveFavouriteComponentOrderDtls(IdNameVO inputVO) {
		ResultVO statusVO = new ResultVO();
		statusVO.setMessage("success");
		statusVO.setStatusCode(0);
		try {
			if (inputVO.getComponentIds() != null && !inputVO.getComponentIds().isEmpty()) {
				Long orderNo = 101L;
				for (Long id : inputVO.getComponentIds()) {
					FavouriteComponent model = favouriteComponentDAO.get(id);
					model.setOrderNo(orderNo);
					favouriteComponentDAO.save(model);
					orderNo++;
				}
				statusVO.setMessage("success");
				statusVO.setStatusCode(0);
			}
		} catch (Exception e) {
			log.error("Exception occured in saveFavouriteComponentOrderDtls from UserServiceImpl ",e);
			statusVO.setMessage("fail");
			statusVO.setStatusCode(1);
		}
		return statusVO;
	}
	
	/**
	 * @Author : Santosh Kumar Verma
	 * @Date : 27-09-2017
	 * @param InputVO inputVO
	 * @return String  
	 */
	public ResultVO deleteFavouriteComponent(IdNameVO inputVO) {
		ResultVO statusVO = new ResultVO();
		try {
			int updatedCount = favouriteComponentDAO.updateFavouriteComponentDtls(inputVO.getId(),"Y");
			statusVO.setMessage("success");
			statusVO.setStatusCode(0);
		} catch (Exception e) {
			statusVO.setMessage("fail");
			statusVO.setStatusCode(1);
			log.error("Exception occured in deleteFavouriteComponent from UserServiceImpl ",e);
		}
		return statusVO;
	}
}
