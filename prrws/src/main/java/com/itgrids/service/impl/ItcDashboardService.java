package com.itgrids.service.impl;



import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tempuri.MOUTrackerIT;
import org.tempuri.TrackerITServiceSoapProxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.itgrids.dto.ApInnovationCenterVO;
import com.itgrids.dto.ApInnovationSocietyOverviewVO;
import com.itgrids.dto.CmEoDBDtlsVO;
import com.itgrids.dto.CohortDtlsVO;
import com.itgrids.dto.EofficeDtlsVO;
import com.itgrids.dto.InnovationSocietyDtlsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.ItInformationDtlsVO;
import com.itgrids.dto.ItecCMeoDBDetailsVO;
import com.itgrids.dto.ItecEOfficeVO;
import com.itgrids.dto.ItecOverviewVO;
import com.itgrids.dto.ItecPromotionDetailsVO;
import com.itgrids.dto.MeesevaDtlsVO;
import com.itgrids.dto.MeesevaKPIDtlsVO;
import com.itgrids.service.IItcDashboardService;
import com.itgrids.service.integration.external.ItcWebServiceUtilService;
import com.itgrids.utils.IConstants;
import com.sun.jersey.api.client.ClientResponse;


@Transactional
@Service
public class ItcDashboardService implements IItcDashboardService {

	private static final Logger LOG = Logger.getLogger(ItcDashboardService.class);
	
	@Autowired
	private ItcWebServiceUtilService itcWebServiceUtilService;
	
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to meesevaSLA overview details.}
	 * @return List<MeesevaDtlsVO>
	 * @Date 21-09-2017
	 */
	public List<MeesevaDtlsVO> getMeesevaSLAOverviewDtls(InputVO inputVO) {
		List<MeesevaDtlsVO> resultList = new ArrayList<MeesevaDtlsVO>(0);
		try {
			List<MeesevaDtlsVO> returnDeptList = getMeesevaSLAMonitoringDtlsDepartmentWise(inputVO);

			if (returnDeptList != null && returnDeptList.size() > 0) {

				MeesevaDtlsVO overviewDtlsVO = getOverAllTransactionDtls(returnDeptList);
				MeesevaDtlsVO totalTransactionsDtlsVO = getTotalTransactionDetails(returnDeptList, overviewDtlsVO);
				MeesevaDtlsVO withInSlaDtlsVO = getWithInSlaDetails(returnDeptList, overviewDtlsVO);
				MeesevaDtlsVO beyondSlaDtlsVO = getBeyondSlaDetails(returnDeptList, overviewDtlsVO);
				resultList.add(totalTransactionsDtlsVO);
				resultList.add(withInSlaDtlsVO);
				resultList.add(beyondSlaDtlsVO);

			}
		} catch (Exception e) {
			LOG.error("Exception occured at getMeesevaSLAOverviewDtls() in  ItcDashboardService class",e);
		}
		return resultList;
	}
	
	private MeesevaDtlsVO getOverAllTransactionDtls(List<MeesevaDtlsVO> deptList) {
		MeesevaDtlsVO overviewDtlsVO = new MeesevaDtlsVO();
		try {
			// setting default value
			overviewDtlsVO.setGrandTotalCount(0l);
			overviewDtlsVO.setTotalWithInSlaCount(0l);
			overviewDtlsVO.setTotalBeyondSlaCount(0l);
			for (MeesevaDtlsVO deptVO : deptList) {
				overviewDtlsVO.setGrandTotalCount(overviewDtlsVO.getGrandTotalCount() + deptVO.getTotalTransactionCount());
				overviewDtlsVO.setTotalWithInSlaCount(overviewDtlsVO.getTotalWithInSlaCount() + deptVO.getPendingWithinSla());
				overviewDtlsVO.setTotalBeyondSlaCount(overviewDtlsVO.getTotalBeyondSlaCount() + deptVO.getPendingBeyondSla());
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getOverAllTransactionDtls() in  ItcDashboardService class",e);
		}
		return overviewDtlsVO;
	}
	
	private MeesevaDtlsVO getTotalTransactionDetails(List<MeesevaDtlsVO> deptList, MeesevaDtlsVO overviewDtlsVO) {
		MeesevaDtlsVO totalTransactionsDtlsVO = new MeesevaDtlsVO();
		try {
			totalTransactionsDtlsVO.setName("TOTAL TRANSACTIONS");
			totalTransactionsDtlsVO.setDepartmentCount(Long.valueOf(deptList.size()));
			totalTransactionsDtlsVO.setTotalCount(overviewDtlsVO.getGrandTotalCount());

			Collections.sort(deptList, amountWiseDescendingOrder);// get sorted list

			MeesevaDtlsVO topTransactionDeptVO = (MeesevaDtlsVO) deptList.get(0).clone();
			topTransactionDeptVO.setName("TOP TRANSACTIONS DEPARTMENT ("+ topTransactionDeptVO.getName() + ")");
			topTransactionDeptVO.setTotalCount(topTransactionDeptVO.getTotalTransactionCount());
			MeesevaDtlsVO lowTransactionDeptVO = (MeesevaDtlsVO) deptList.get(deptList.size() - 1).clone();
			lowTransactionDeptVO.setName("LOW TRANSACTIONS DEPARTMENT ("+ lowTransactionDeptVO.getName() + ")");
			lowTransactionDeptVO.setTotalCount(lowTransactionDeptVO.getTotalTransactionCount());

			totalTransactionsDtlsVO.setSubList(new ArrayList<MeesevaDtlsVO>());
			totalTransactionsDtlsVO.getSubList().add(topTransactionDeptVO);
			totalTransactionsDtlsVO.getSubList().add(lowTransactionDeptVO);

		} catch (Exception e) {
			LOG.error("Exception occured at getTotalTransactionDetails() in  ItcDashboardService class",e);
		}
		return totalTransactionsDtlsVO;
	}

	private MeesevaDtlsVO getWithInSlaDetails(List<MeesevaDtlsVO> deptList,MeesevaDtlsVO overviewDtlsVO) {
		MeesevaDtlsVO pendingWithInSlaDtlsVO = new MeesevaDtlsVO();
		try {
			pendingWithInSlaDtlsVO.setName("With in SLA");
			pendingWithInSlaDtlsVO.setDepartmentCount(Long.valueOf(deptList.size()));
			pendingWithInSlaDtlsVO.setTotalCount(overviewDtlsVO.getTotalWithInSlaCount());

			Collections.sort(deptList,pendingWithinSlaamountWiseDescendingOrder);// get sorted list

			MeesevaDtlsVO topDeptWithInSlaVO = (MeesevaDtlsVO) deptList.get(0).clone();
			topDeptWithInSlaVO.setName("TOP With in SLA ("+ topDeptWithInSlaVO.getName() + ")");
			topDeptWithInSlaVO.setTotalCount(topDeptWithInSlaVO.getPendingWithinSla());
			MeesevaDtlsVO lowDeptWithInSlaVO = (MeesevaDtlsVO) deptList.get(deptList.size() - 1).clone();
			lowDeptWithInSlaVO.setName("LOW With in SLA ("+ lowDeptWithInSlaVO.getName() + ")");
			lowDeptWithInSlaVO.setTotalCount(lowDeptWithInSlaVO.getPendingWithinSla());

			pendingWithInSlaDtlsVO.setSubList(new ArrayList<MeesevaDtlsVO>());
			pendingWithInSlaDtlsVO.getSubList().add(topDeptWithInSlaVO);
			pendingWithInSlaDtlsVO.getSubList().add(lowDeptWithInSlaVO);

		} catch (Exception e) {
			LOG.error("Exception occured at getWithInSlaDetails() in  ItcDashboardService class",e);
		}
		return pendingWithInSlaDtlsVO;
	}

	private MeesevaDtlsVO getBeyondSlaDetails(List<MeesevaDtlsVO> deptList,MeesevaDtlsVO overviewDtlsVO) {
		MeesevaDtlsVO pendingBeyondSlaDtlsVO = new MeesevaDtlsVO();
		try {
			pendingBeyondSlaDtlsVO.setName("Beyond SLA");
			pendingBeyondSlaDtlsVO.setDepartmentCount(Long.valueOf(deptList.size()));
			pendingBeyondSlaDtlsVO.setTotalCount(overviewDtlsVO.getTotalBeyondSlaCount());

			Collections.sort(deptList,pendingBeyondSlaamountWiseDescendingOrder);// get sorted// list

			MeesevaDtlsVO topDeptBeyondSlaVO = (MeesevaDtlsVO) deptList.get(0).clone();
			topDeptBeyondSlaVO.setName("TOP Beyond SLA ("+ topDeptBeyondSlaVO.getName() + ")");
			topDeptBeyondSlaVO.setTotalCount(topDeptBeyondSlaVO.getPendingBeyondSla());
			MeesevaDtlsVO lowDeptBeyondSlaVO = (MeesevaDtlsVO) deptList.get(deptList.size() - 1).clone();
			lowDeptBeyondSlaVO.setName("LOW Beyond SLA ("+ lowDeptBeyondSlaVO.getName() + ")");
			lowDeptBeyondSlaVO.setTotalCount(lowDeptBeyondSlaVO.getPendingBeyondSla());

			pendingBeyondSlaDtlsVO.setSubList(new ArrayList<MeesevaDtlsVO>());
			pendingBeyondSlaDtlsVO.getSubList().add(topDeptBeyondSlaVO);
			pendingBeyondSlaDtlsVO.getSubList().add(lowDeptBeyondSlaVO);

		} catch (Exception e) {
			LOG.error("Exception occured at getBeyondSlaDetails() in  ItcDashboardService class",e);
		}
		return pendingBeyondSlaDtlsVO;
	}

	private static Comparator<MeesevaDtlsVO> amountWiseDescendingOrder = new Comparator<MeesevaDtlsVO>() {
		public int compare(MeesevaDtlsVO o1, MeesevaDtlsVO o2) {
			try {
				if (o2.getTotalTransactionCount() != null && o1.getTotalTransactionCount() != null) {
					return o2.getTotalTransactionCount().compareTo(o1.getTotalTransactionCount());
				}
			} catch (Exception e) {
				LOG.error(" Exception occured in amountWiseDescendingOrder ", e);
			}
			return 0;
		}
	};
	private static Comparator<MeesevaDtlsVO> pendingWithinSlaamountWiseDescendingOrder = new Comparator<MeesevaDtlsVO>() {
		public int compare(MeesevaDtlsVO o1, MeesevaDtlsVO o2) {
			try {
				if (o2.getPendingWithinSla() != null && o1.getPendingWithinSla() != null) {
					return o2.getPendingWithinSla().compareTo(o1.getPendingWithinSla());
				}
			} catch (Exception e) {
				LOG.error(" Exception occured in pendingWithinSlaamountWiseDescendingOrder ",e);
			}
			return 0;
		}
	};
	private static Comparator<MeesevaDtlsVO> pendingBeyondSlaamountWiseDescendingOrder = new Comparator<MeesevaDtlsVO>() {
		public int compare(MeesevaDtlsVO o1, MeesevaDtlsVO o2) {
			try {
				if (o2.getPendingBeyondSla() != null && o1.getPendingBeyondSla() != null) {
					return o2.getPendingBeyondSla().compareTo(o1.getPendingBeyondSla());
				}
			} catch (Exception e) {
				LOG.error(" Exception occured in pendingBeyondSlaamountWiseDescendingOrder ",e);
			}
			return 0;
		}
	};
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get E office details department or designation and day wise}
	 * @return List<MeesevaDtlsVO>
	 * @Date 21-09-2017
	 */
	public List<MeesevaDtlsVO> getMeesevaSLAMonitoringDtlsDepartmentWise(InputVO inputVO) {
		List<MeesevaDtlsVO> resultList = new ArrayList<MeesevaDtlsVO>(0);
		try {
			String input = prepareInputParameter(inputVO);
			ClientResponse response = itcWebServiceUtilService.postWebServiceCall("http://apdept.meeseva.gov.in/meesevawebservice/meesevawebservice.asmx/TRANSACTIONDETAILS",input);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				List<Element> elementList = getDataInxmlFormat(response);
				Namespace nameSpace = Namespace.getNamespace(IConstants.ITC_WEB_SERVICE_NAME_SPACE);
				if (elementList != null && elementList.size() > 0) {

					for (Element element : elementList) {

						MeesevaDtlsVO transactionDtlsVO = new MeesevaDtlsVO();
						transactionDtlsVO.setId(element.getChildText("department_id", nameSpace));
						transactionDtlsVO.setName(element.getChildText("department_description", nameSpace));
						String totalTransactions = (element.getChildText("Total_x0020_Trans", nameSpace)) != null ? element.getChildText("Total_x0020_Trans", nameSpace):"0";
						transactionDtlsVO.setTotalTransactionCount(Long.valueOf(totalTransactions));
						transactionDtlsVO.setCateoryA(element.getChildText("CateoryA", nameSpace));
						transactionDtlsVO.setCategoryB(element.getChildText("CategoryB", nameSpace));
						transactionDtlsVO.setbApproved(element.getChildText("BApproved", nameSpace));
						transactionDtlsVO.setbRejected(element.getChildText("BRejected", nameSpace));
						String totalPendingWithInSla = (element.getChildText("BPENDINGWITHINSLA", nameSpace)) != null ? element.getChildText("BPENDINGWITHINSLA", nameSpace):"0";
						transactionDtlsVO.setPendingWithinSla(Long.valueOf(totalPendingWithInSla));
						String totalPendingBeyondSla = (element.getChildText("BPENDINGBEYONDSLA", nameSpace)) != null ? element.getChildText("BPENDINGBEYONDSLA", nameSpace):"0";
						transactionDtlsVO.setPendingBeyondSla(Long.valueOf(totalPendingBeyondSla));
						transactionDtlsVO.setRevoked(element.getChildText("Revoked",nameSpace));
						
						resultList.add(transactionDtlsVO);
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception occured at getMeesevaSLAMonitoringDtlsDepartmentWise() in  ItcDashboardService class",e);
		}
		return resultList;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get CM eoDB details period.}
	 * @return CmEoDBDtlsVO
	 * @Date 21-09-2017
	 */
	public CmEoDBDtlsVO getCMEDOBOverview(InputVO inputVO) {
		CmEoDBDtlsVO resultVO = new CmEoDBDtlsVO();
		 try {
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception occured at getCMEDOBOverview() in  ItcDashboardService class",e);
		 }
		 return resultVO;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get CM eoDB details status wise.}
	 * @return List<MeesevaKPIDtlsVO>
	 * @Date 21-09-2017
	 */
	public List<CmEoDBDtlsVO> getCMEDOBReportStatusWise(InputVO inputVO) {
		List<CmEoDBDtlsVO> resultList = new ArrayList<CmEoDBDtlsVO>(0);
		 try {
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception occured at getCMEDOBReportStatusWise() in  ItcDashboardService class",e);
		 }
		 return resultList;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get innovation society details.}
	 * @return InnovationSocietyDtlsVO
	 * @Date 21-09-2017
	 */
	public ApInnovationSocietyOverviewVO getAPInnovationSocietyOverview(InputVO inputVO) {
		ApInnovationSocietyOverviewVO resultVO = new ApInnovationSocietyOverviewVO();
		try {
			ClientResponse response = itcWebServiceUtilService.getWebServiceCall("http://www.apinnovationsociety.com/dashboard/api/applications.php");
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				if (output != null && output.length() > 0) {
					Gson gson = new GsonBuilder().create();
					resultVO = gson.fromJson(output, ApInnovationSocietyOverviewVO.class);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getAPInnovationSocietyOverview() in  ItcDashboardService class",e);
		}
		return resultVO;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get innovation society details.}
	 * @return InnovationSocietyDtlsVO
	 * @Date 21-09-2017
	 */
	public InnovationSocietyDtlsVO getEducationalInfoDetails(InputVO inputVO) {
		InnovationSocietyDtlsVO resultVO = new InnovationSocietyDtlsVO();
		try {
			ClientResponse response = itcWebServiceUtilService.getWebServiceCall("http://www.apinnovationsociety.com/dashboard/get_educational_info.php");
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				Gson gson = new GsonBuilder().create();
				resultVO = gson.fromJson(output, InnovationSocietyDtlsVO.class);
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured at getEducationalInfoDetails() in  ItcDashboardService class",e);
		}
		return resultVO;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get APISXLR8 details.}
	 * @return List<InnovationSocietyDtlsVO>
	 * @Date 21-09-2017
	 */
	public List<ApInnovationCenterVO> getAPISXLR8APDetailedData(InputVO inputVO) {
		List<ApInnovationCenterVO> resultList = new ArrayList<>(0);
		try {
			ClientResponse response = itcWebServiceUtilService.getWebServiceCall("http://www.apinnovationsociety.com/dashboard/api/cohorts.php");
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				if (output != null && !output.isEmpty()) {
					JSONArray finalArray
					= new JSONArray(output);
					if (finalArray != null && finalArray.length() > 0) {
						for (int i = 0; i < finalArray.length(); i++) {
							ApInnovationCenterVO bathDtlsVO = new ApInnovationCenterVO();
							JSONObject jObj = (JSONObject) finalArray.get(i);
							bathDtlsVO.setBatch(jObj.has("batch") ? jObj.getString("batch"):" ");
							if (bathDtlsVO.getBatch().trim().length() > 0) {
								String[] bathArr = bathDtlsVO.getBatch().split("-");
								bathDtlsVO.setBatchId(Long.valueOf(bathArr[bathArr.length-1]));
							}
							bathDtlsVO.setDuration(jObj.has("duration") ? jObj.getString("duration"):" ");
							bathDtlsVO.setCompaniesRegisterd(jObj.has("companiesRegisterd") ? jObj.getLong("companiesRegisterd"):0l);
							bathDtlsVO.setJobsCreated(jObj.has("jobsCreated") ? jObj.getString("jobsCreated"):"");
							resultList.add(bathDtlsVO);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getAPISXLR8APDetailedData() in  ItcDashboardService class",e);
		}
		return resultList;
	}
	public InnovationSocietyDtlsVO getAPISXLR8APInfo(InputVO inputVO) {
		InnovationSocietyDtlsVO resultVO = new InnovationSocietyDtlsVO();
		try {
			ClientResponse response = itcWebServiceUtilService.getWebServiceCall("http://www.apinnovationsociety.com/dashboard/get_xlr8ap_info.php");
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				Gson gson = new GsonBuilder().create();
				resultVO = gson.fromJson(output, InnovationSocietyDtlsVO.class);
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getAPISXLR8APInfo() in  ItcDashboardService class",e);
		}
		return resultVO;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get APISXLR8 details.}
	 * @return List<InnovationSocietyDtlsVO>
	 * @Date 21-09-2017
	 */
	public List<ApInnovationCenterVO> getCampaignsDetailedData(InputVO inputVO) {
		List<ApInnovationCenterVO> resultList = new ArrayList<>(0);
		try {
			ClientResponse response = itcWebServiceUtilService.getWebServiceCall("http://www.apinnovationsociety.com/dashboard/api/campaigns.php");
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				if (output != null && !output.isEmpty()) {
					JSONArray finalArray = new JSONArray(output);
					if (finalArray != null && finalArray.length() > 0) {
						for (int i = 0; i < finalArray.length(); i++) {
							ApInnovationCenterVO campaignsVO = new ApInnovationCenterVO();
							JSONObject jObj = (JSONObject) finalArray.get(i);
							campaignsVO.setCampaignName(jObj.has("campaignName") ? jObj.getString("campaignName"):" ");
							campaignsVO.setCampaignType(jObj.has("campaignType") ? jObj.getString("campaignType"):" ");
							campaignsVO.setDuration(jObj.has("date") ? jObj.getString("date"):" ");
							campaignsVO.setLocation(jObj.has("location") ? jObj.getString("location"):"");
							resultList.add(campaignsVO);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getCampaignsDetailedData() in  ItcDashboardService class",e);
		}
		return resultList;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get APISXLR8 details.}
	 * @return List<InnovationSocietyDtlsVO>
	 * @Date 21-09-2017
	 */
	public InnovationSocietyDtlsVO getIncubationCentersDetails(InputVO inputVO) {
		InnovationSocietyDtlsVO resultVO = new InnovationSocietyDtlsVO();
		try {
			ClientResponse response = itcWebServiceUtilService.getWebServiceCall("http://www.apinnovationsociety.com/dashboard/get_incubators_info.php");
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				Gson gson = new GsonBuilder().create();
				resultVO = gson.fromJson(output, InnovationSocietyDtlsVO.class);
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getIncubationCentersDetails() in  ItcDashboardService class",e);
		}
		return resultVO;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get getCampusInnovationCentersDetails.}
	 * @return List<InnovationSocietyDtlsVO>
	 * @Date 21-09-2017
	 */
	public List<ApInnovationCenterVO> getCampusInnovationCentersDetails(InputVO inputVO) {
		List<ApInnovationCenterVO> resultList = new ArrayList<ApInnovationCenterVO>(0);
		try {
			ClientResponse response = itcWebServiceUtilService.getWebServiceCall("http://www.apinnovationsociety.com/dashboard/api/campus_innovation_centers.php");
			if (response.getStatus() != 200) {throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				if (output != null && !output.isEmpty()) {
					JSONArray finalArray = new JSONArray(output);
					if (finalArray != null && finalArray.length() > 0) {
						for (int i = 0; i < finalArray.length(); i++) {
							ApInnovationCenterVO innovationCenterVO = new ApInnovationCenterVO();
							JSONObject jObj = (JSONObject) finalArray.get(i);
							innovationCenterVO.setUniversityORCollegeName(jObj.has("universityORCollegeName") ? jObj.getString("universityORCollegeName"):" ");
							innovationCenterVO.setInnovationCentreName(jObj.has("innovationCentreName") ? jObj.getString("innovationCentreName"):" ");
							innovationCenterVO.setLocation(jObj.has("location") ? jObj.getString("location"):" ");
							resultList.add(innovationCenterVO);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getIncubationCentersDetails() in  ItcDashboardService class",e);
		}
		return resultList;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get suo Moto Proposals details.}
	 * @return List<InnovationSocietyDtlsVO>
	 * @Date 07-10-2017
	 */
	public List<CohortDtlsVO> getCohortDetailsByCohortId(InputVO inputVO) {
		List<CohortDtlsVO> resultList = new ArrayList<CohortDtlsVO>();
		try {
			String URL = "http://www.apinnovationsociety.com/dashboard/api/cohorts.php?id='"+inputVO.getSearchLevelId()+"'";
			ClientResponse response = itcWebServiceUtilService.getWebServiceCall(URL);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				if (output != null && output.length() > 0) {
					Gson gson = new GsonBuilder().create();
					resultList = gson.fromJson(output,new TypeToken<List<CohortDtlsVO>>() {}.getType());
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getCohortDetailsByCohortId() in  ItcDashboardService class",e);
		}
		return resultList;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get Innovation Awards Details details.}
	 * @return List<InnovationSocietyDtlsVO>
	 * @Date 21-09-2017
	 */
	public List<InnovationSocietyDtlsVO> getInnovationAwardsDetailedData(InputVO inputVO) {
		List<InnovationSocietyDtlsVO> resultList = new ArrayList<InnovationSocietyDtlsVO>();
		 try {
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception occured at getInnovationAwardsDetailedData() in  ItcDashboardService class",e);
		 }
		 return resultList;
	}
	
	private String prepareInputParameter(InputVO inputVO){
		String str = "";
		 try {
			 str +="TYPE="+inputVO.getFilterId()+"&FROMDATE="+inputVO.getFromDate()+"&TODATE="+inputVO.getToDate()+"&USERID="+IConstants.ITC_WEB_SERVICE_USER_NAME+"&PASSWORD="+IConstants.ITC_WEB_SERVICE_USER_NAME; 
		} catch (Exception e) {
			LOG.error("Exception raised at prepareInputParameter - ItcDashboardService service", e);
		}
		return str;
	}

	@SuppressWarnings("unchecked")
	private List<Element> getDataInxmlFormat(ClientResponse response) {
		List<Element> list = null;
		try {
			String output = response.getEntity(String.class);
			String replaceStr = "";
			replaceStr = output.replaceAll("&lt;", "<");
			replaceStr = replaceStr.replaceAll("&gt;", ">");
			InputStream fXmlFile = new ByteArrayInputStream(replaceStr.getBytes());
			
			SAXBuilder builder = new SAXBuilder();
			Document document = (Document) builder.build(fXmlFile);
			Namespace namespace = Namespace.getNamespace(IConstants.ITC_WEB_SERVICE_NAME_SPACE);
			Element rootNode = document.getRootElement();
			Element rootN = rootNode.getChild("NewDataSet", namespace);
			list = rootN.getChildren("Table1", namespace);

		} catch (Exception e) {
			LOG.error("Exception raised at getDataInxmlFormat - ItcDashboardService service",e);
		}
		return list;
	}
	
	/**
	 * @author Sravanth
	 * @param InputVO inputVO
	 * @description {This service is used to get Itec Promotions Overview Details.}
	 * @return List<ItecPromotionDetailsVO>
	 * @Date 12-10-2017
	 */
	public List<ItecPromotionDetailsVO> getITSectorWiseOverviewDetails(){
		List<ItecPromotionDetailsVO> returnList = new ArrayList<ItecPromotionDetailsVO>();
		try {
			MOUTrackerIT[] list = new TrackerITServiceSoapProxy().get_IT_SECTOR_WISE_OVERVIEW();
			setDataToVO(list, returnList);
		} catch (Exception e) {
			LOG.error("Exception raised at getITSectorWiseOverviewDetails - ItcDashboardService service",e);
		}
		return returnList;
	}
	
	/**
	 * @author Sravanth
	 * @param InputVO inputVO
	 * @description {This service is used to get Itec Promotions Overview Details.}
	 * @return List<ItecPromotionDetailsVO>
	 * @Date 12-10-2017
	 */
	public List<ItecPromotionDetailsVO> getITSectorCategoryWiseDetails(InputVO inputVO){
		List<ItecPromotionDetailsVO> returnList = new ArrayList<ItecPromotionDetailsVO>();
		try {
			MOUTrackerIT[] list = new TrackerITServiceSoapProxy().get_IT_SECTOR_CATEGORY_WISE(inputVO.getCategory());
			setDataToVO(list, returnList);
		} catch (Exception e) {
			LOG.error("Exception raised at getITSectorCategoryWiseDetails - ItcDashboardService service",e);
		}
		return returnList;
	}
	
	/**
	 * @author Sravanth
	 * @param InputVO inputVO
	 * @description {This service is used to get Itec Promotions Overview Details.}
	 * @return List<ItecPromotionDetailsVO>
	 * @Date 12-10-2017
	 */
	public List<ItecPromotionDetailsVO> getITDistrictWiseDetails(InputVO inputVO){
		List<ItecPromotionDetailsVO> returnList = new ArrayList<ItecPromotionDetailsVO>();
		try {
			Map<String,ItecPromotionDetailsVO> distMap = new HashMap<String,ItecPromotionDetailsVO>(0);
			MOUTrackerIT[] list = new TrackerITServiceSoapProxy().get_IT_DISTRICT_WISE_DTLS(inputVO.getSector(), inputVO.getCategory());
			//setDataToVO(list, returnList);
			
			inputVO.setCategory("RED");
			MOUTrackerIT[] redList = new TrackerITServiceSoapProxy().get_IT_DISTRICT_WISE_DTLS(inputVO.getSector(), inputVO.getCategory());
			
			inputVO.setCategory("GREEN");
			MOUTrackerIT[] greenList = new TrackerITServiceSoapProxy().get_IT_DISTRICT_WISE_DTLS(inputVO.getSector(), inputVO.getCategory());
			
			inputVO.setCategory("DROPPED");
			MOUTrackerIT[] dropList = new TrackerITServiceSoapProxy().get_IT_DISTRICT_WISE_DTLS(inputVO.getSector(), inputVO.getCategory());
			
			if(list != null && list.length > 0){
				for (int i = 0; i < list.length; i++) {
					ItecPromotionDetailsVO distVO = distMap.get(list[i].getDISTRICT());
					if(distVO == null){
						distVO = new ItecPromotionDetailsVO();
						distVO.setSector(list[i].getSECTOR());
						distVO.setDistrict(list[i].getDISTRICT());
						distVO.setNoProjects(list[i].getNO_PROJECTS());
						distVO.setInvestment(list[i].getINVESTMENT());
						distVO.setRealizedInvestment(list[i].getREALIZED_INVESTMENT());
						distVO.setEmployment(list[i].getEMPLOYMENT());
						distVO.setRealizedEmployment(list[i].getREALIZED_EMPLOYMENT());
						ItecPromotionDetailsVO greenVO  = new ItecPromotionDetailsVO();
							distVO.getSubList().add(greenVO);
						ItecPromotionDetailsVO redVO  = new ItecPromotionDetailsVO();
							distVO.getSubList().add(redVO);
						ItecPromotionDetailsVO dropVO  = new ItecPromotionDetailsVO();
							distVO.getSubList().add(dropVO);
						distMap.put(distVO.getDistrict(), distVO);
					}
				}
			}
					
			if(greenList != null && greenList.length > 0L){
				for ( int j = 0; j < greenList.length; j++) {
					ItecPromotionDetailsVO distVO = distMap.get(greenList[j].getDISTRICT());
					if(distVO != null){
						distVO.getSubList().get(0).setSector(greenList[j].getSECTOR());
						distVO.getSubList().get(0).setDistrict(greenList[j].getDISTRICT());
						distVO.getSubList().get(0).setNoProjects(greenList[j].getNO_PROJECTS());
						distVO.getSubList().get(0).setInvestment(greenList[j].getINVESTMENT());
						distVO.getSubList().get(0).setRealizedInvestment(greenList[j].getREALIZED_INVESTMENT());
						distVO.getSubList().get(0).setEmployment(greenList[j].getEMPLOYMENT());
						distVO.getSubList().get(0).setRealizedEmployment(greenList[j].getREALIZED_EMPLOYMENT());
						distVO.getSubList().get(0).setCategory("GREEN");
					}
				}
			}
						
			if(redList != null && redList.length > 0L){
				for ( int j = 0; j < redList.length; j++) {
					ItecPromotionDetailsVO distVO = distMap.get(redList[j].getDISTRICT());
					if(distVO != null){
						distVO.getSubList().get(1).setSector(redList[j].getSECTOR());
						distVO.getSubList().get(1).setDistrict(redList[j].getDISTRICT());
						distVO.getSubList().get(1).setNoProjects(redList[j].getNO_PROJECTS());
						distVO.getSubList().get(1).setInvestment(redList[j].getINVESTMENT());
						distVO.getSubList().get(1).setRealizedInvestment(redList[j].getREALIZED_INVESTMENT());
						distVO.getSubList().get(1).setEmployment(redList[j].getEMPLOYMENT());
						distVO.getSubList().get(1).setRealizedEmployment(redList[j].getREALIZED_EMPLOYMENT());
						distVO.getSubList().get(1).setCategory("RED");
					}
				}
			}
			
			if(dropList != null && dropList.length > 0L){
				for ( int j = 0; j < dropList.length; j++) {
					ItecPromotionDetailsVO distVO = distMap.get(dropList[j].getDISTRICT());
					if(distVO != null){
						distVO.getSubList().get(2).setSector(dropList[j].getSECTOR());
						distVO.getSubList().get(2).setDistrict(dropList[j].getDISTRICT());
						distVO.getSubList().get(2).setNoProjects(dropList[j].getNO_PROJECTS());
						distVO.getSubList().get(2).setInvestment(dropList[j].getINVESTMENT());
						distVO.getSubList().get(2).setRealizedInvestment(dropList[j].getREALIZED_INVESTMENT());
						distVO.getSubList().get(2).setEmployment(dropList[j].getEMPLOYMENT());
						distVO.getSubList().get(2).setRealizedEmployment(dropList[j].getREALIZED_EMPLOYMENT());
						distVO.getSubList().get(2).setCategory("DROPED");
					}
				}
			}
			
			if(distMap != null){
				returnList = new ArrayList<>(distMap.values());
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getITDistrictWiseDetails - ItcDashboardService service",e);
		}
		return returnList;
	}
	
	private void setDataToVO(MOUTrackerIT[] list,List<ItecPromotionDetailsVO> returnList){
		try {
			if(list != null && list.length > 0){
				for (int i = 0; i < list.length; i++) {
					ItecPromotionDetailsVO vo = new ItecPromotionDetailsVO();
					
					vo.setSector(list[i].getSECTOR());
					vo.setDistrict(list[i].getDISTRICT());
					vo.setNoProjects(list[i].getNO_PROJECTS());
					vo.setInvestment(list[i].getINVESTMENT());
					vo.setRealizedInvestment(list[i].getREALIZED_INVESTMENT());
					vo.setEmployment(list[i].getEMPLOYMENT());
					vo.setRealizedEmployment(list[i].getREALIZED_EMPLOYMENT());
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at setDataToVO - ItcDashboardService service",e);
		}
	}
	
	/**
	 * @author Sravanth
	 * @param InputVO inputVO
	 * @description {This service is used to get Itec Promotions Overview Details.}
	 * @return List<ItecPromotionDetailsVO>
	 * @Date 12-10-2017
	 */
	public List<ItecPromotionDetailsVO> getITSectorSubLeadCategoryWiseDetails(InputVO inputVO){
		List<ItecPromotionDetailsVO> returnList = new ArrayList<ItecPromotionDetailsVO>();
		try {
			MOUTrackerIT[] list = new TrackerITServiceSoapProxy().GET_LEAD_CATEGORY_WISE(inputVO.getLeadName(),inputVO.getCategory());
			if(list != null && list.length > 0){
				for (int i = 0; i < list.length; i++) {
					String categoryNew = list[i].getCATEGORY().trim();
					if(categoryNew != null && categoryNew.equalsIgnoreCase("R3A"))
						categoryNew = "R3";
					else if(categoryNew != null && categoryNew.equalsIgnoreCase("R3B"))
						categoryNew = "R3";
					if(categoryNew != null && categoryNew.equalsIgnoreCase("R3C"))
						categoryNew = "R3";
					
					if(inputVO.getReportType() != null && !list[i].getLINEOFACTIVITY().trim().equalsIgnoreCase("Total") && inputVO.getReportType().trim().equalsIgnoreCase(categoryNew)){
						ItecPromotionDetailsVO vo = new ItecPromotionDetailsVO();
						vo.setSector(list[i].getSECTOR());
						vo.setDistrict(list[i].getDISTRICT());
						vo.setNoProjects(list[i].getNO_PROJECTS());
						vo.setInvestment(list[i].getINVESTMENT());
						vo.setRealizedInvestment(list[i].getREALIZED_INVESTMENT());
						vo.setEmployment(list[i].getEMPLOYMENT());
						vo.setRealizedEmployment(list[i].getREALIZED_EMPLOYMENT());
						vo.setSourceOfLead(list[i].getSOURCE_OF_LEAD());
						vo.setCategory(list[i].getCATEGORY());
						vo.setLineOfActivity(list[i].getLINEOFACTIVITY());
						vo.setSubSector(list[i].getSUBSECTOR());
						vo.setItSector(list[i].getITSECTOR());
						vo.setNameOfCompany(list[i].getNAMEOFTHECOMPANY());
						vo.setDistrictName(list[i].getDISTRICTNAME());
						vo.setDeptName(list[i].getDEPTNAME());
						
						returnList.add(vo);
					}
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getITSectorSubLeadCategoryWiseDetails - ItcDashboardService service",e);
		}
		return returnList;
	}
	
	/**
	 * @author Nandhini
	 * @param InputVO inputVO
	 * @description {This service is used to get Itec Promotions Overview Details.}
	 * @return List<ItecPromotionDetailsVO>
	 * @Date 12-10-2017
	 */
	public List<ItecPromotionDetailsVO> getITSectorLeadCategoryWiseDetails(InputVO inputVO){
		List<ItecPromotionDetailsVO> returnList = new ArrayList<ItecPromotionDetailsVO>();
		try {
			MOUTrackerIT[] list = new TrackerITServiceSoapProxy().GET_LEAD_CATEGORY_WISE(inputVO.getLeadName(),inputVO.getCategory());
			if(list != null && list.length > 0){
				for (int i = 0; i < list.length; i++) {
					ItecPromotionDetailsVO vo = new ItecPromotionDetailsVO();
					if(list[i].getLINEOFACTIVITY() != null && list[i].getLINEOFACTIVITY().trim().equalsIgnoreCase("Total")){
						vo.setInvestment(list[i].getINVESTMENT());
						vo.setEmployment(list[i].getEMPLOYMENT());
						vo.setCategory(list[i].getCATEGORY());
						returnList.add(vo);
					}
				}
			}
			
			if(returnList != null && !returnList.isEmpty()){
				for (ItecPromotionDetailsVO finalVO : returnList) {
					for(int i = 0; i < list.length; i++){
						if(list[i].getLINEOFACTIVITY() != null && !list[i].getLINEOFACTIVITY().trim().equalsIgnoreCase("Total")){
							String categoryNew = list[i].getCATEGORY().trim();
							if(categoryNew != null && categoryNew.equalsIgnoreCase("R3A"))
								categoryNew = "R3";
							else if(categoryNew != null && categoryNew.equalsIgnoreCase("R3B"))
								categoryNew = "R3";
							if(categoryNew != null && categoryNew.equalsIgnoreCase("R3C"))
								categoryNew = "R3";
								
							if(categoryNew != null && categoryNew.equalsIgnoreCase(finalVO.getCategory())){
								finalVO.setCategoryCount(finalVO.getCategoryCount()+1);
							}
						}
						
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getITSectorLeadCategoryWiseDetails - ItcDashboardService service",e);
		}
		return returnList;
	}
	
	/*public List<ItecEOfficeVO> getEofficeDesignationWiseDetails(){
		List<ItecEOfficeVO> returnList = new ArrayList<ItecEOfficeVO>(0);
		try {
			ClientResponse response = itcWebServiceUtilService.getWebServiceCall("https://demo.eoffice.ap.gov.in/TTReports/Apsectdesignationwise.php");
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				if (output != null && !output.isEmpty()) {
					JSONArray finalArray = new JSONArray(output);
					if (finalArray != null && finalArray.length() > 0) {
						List<Long> departmentIds = new ArrayList<Long>(0);
						Long[] deptArr = IConstants.ITEC_EOFFICE_DEPT_IDS;
						for (int i = 0; i < deptArr.length; i++) {
							departmentIds.add(deptArr[i]);
						}
						for (int i = 0; i < finalArray.length(); i++) {
							JSONObject jObj = (JSONObject) finalArray.get(i);
							Long departmentId = jObj.getLong("departmentid");
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getEofficeDesignationWiseDetails - ItcDashboardService service",e);
		}
		return returnList;
	}*/
	
	/**
	 * @author Nandhini
	 * @description {This service is used to get getEOfcDepartWiseOverviewDetails.}
	 * @return List<ItecEOfficeVO>
	 * @Date 21-10-2017
	 */
	public List<ItecEOfficeVO> getEOfcDepartWiseOverviewDetails() {
		List<ItecEOfficeVO> returnList = new ArrayList<ItecEOfficeVO>(0);
		try {
			Long[] deptIdsArr = IConstants.ITEC_EOFFICE_DEPT_IDS;
			List<Long> deptIds = new ArrayList<Long>(0);
			if(deptIdsArr != null && deptIdsArr.length > 0){
				for (int i = 0; i < deptIdsArr.length; i++) {
					deptIds.add(Long.valueOf(deptIdsArr[i].toString()));
				}
			}
			
			ClientResponse response = itcWebServiceUtilService.getWebServiceCall("https://demo.eoffice.ap.gov.in/TTReports/Apsectdeptwise.php");
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				if (output != null && !output.isEmpty()) {
					JSONArray finalArray = new JSONArray(output);
					if (finalArray != null && finalArray.length() > 0) {
						for (int i = 0; i < finalArray.length(); i++) {
							JSONObject jObj = (JSONObject) finalArray.get(i);
								if(Long.valueOf(jObj.getLong("departmentid")) != null && deptIds.contains(jObj.getLong("departmentid"))){
									ItecEOfficeVO departVO = new ItecEOfficeVO();
									departVO.setDepartmentId(jObj.getLong("departmentid"));
									departVO.setDepartmentName(jObj.getString("departmentname"));
									departVO.setCreated(jObj.getLong("created"));
									departVO.setTotalCount(jObj.getLong("totalcount"));
									if(departVO.getCreated() != null && departVO.getCreated().longValue() > 0L && departVO.getTotalCount() != null && departVO.getTotalCount().longValue() > 0L){
										departVO.setPercentage(new BigDecimal(departVO.getTotalCount()*100.00/departVO.getCreated()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									}else{
										departVO.setPercentage("0.00");
									}
									returnList.add(departVO);
								}
							}
						}
					}
				}
			
			if(returnList != null && !returnList.isEmpty()){
				ItecEOfficeVO finalCountVO = new ItecEOfficeVO();
				for (ItecEOfficeVO vo : returnList) {
					finalCountVO.setCreated(finalCountVO.getCreated()+vo.getCreated());
					finalCountVO.setTotalCount(finalCountVO.getTotalCount()+vo.getTotalCount());
				}
				returnList.get(0).getSubList().add(finalCountVO);
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured at getEOfcDepartWiseOverviewDetails() in  ItcDashboardService class",e);
		}
		return returnList;
	}
	
	/**
	 * @author Nandhini
	 * @description {This service is used to get getEOfcDepartWiseOverviewDetails.}
	 * @return List<ItecEOfficeVO>
	 * @Date 21-10-2017
	 */
	public List<ItecEOfficeVO> getEOfcDeptPendancyStatusWiseDetails() {
		List<ItecEOfficeVO> returnList = new ArrayList<ItecEOfficeVO>(0);
		try {
			Long[] deptIdsArr = IConstants.ITEC_EOFFICE_DEPT_IDS;
			List<Long> deptIds = new ArrayList<Long>(0);
			if(deptIdsArr != null && deptIdsArr.length > 0){
				for (int i = 0; i < deptIdsArr.length; i++) {
					deptIds.add(Long.valueOf(deptIdsArr[i].toString()));
				}
			}
			
			ClientResponse response = itcWebServiceUtilService.getWebServiceCall("https://demo.eoffice.ap.gov.in/TTReports/Apsectdeptwise.php");
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				if (output != null && !output.isEmpty()) {
					JSONArray finalArray = new JSONArray(output);
					if (finalArray != null && finalArray.length() > 0) {
						for (int i = 0; i < finalArray.length(); i++) {
							JSONObject jObj = (JSONObject) finalArray.get(i);
								if(Long.valueOf(jObj.getLong("departmentid")) != null && deptIds.contains(jObj.getLong("departmentid"))){
									ItecEOfficeVO departVO = new ItecEOfficeVO();
									departVO.setDepartmentId(jObj.getLong("departmentid"));
									departVO.setDepartmentName(jObj.getString("departmentname"));
									departVO.setCreated(jObj.getLong("created"));
									departVO.setTotalCount(jObj.getLong("totalcount"));
									departVO.setZeroToSeven(jObj.getLong("0-7"));
									departVO.setEightToFifteen(jObj.getLong("8-15"));
									departVO.setSixteenToThirty(jObj.getLong("16-30"));
									departVO.setThirtyoneToSixty(jObj.getLong("31-60"));
									departVO.setAboveSixty(jObj.getLong(">60"));
									if(departVO.getCreated() != null && departVO.getCreated().longValue() > 0L && departVO.getTotalCount() != null && departVO.getTotalCount().longValue() > 0L){
										departVO.setPercentage(new BigDecimal(departVO.getTotalCount()*100.00/departVO.getCreated()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
									}else{
										departVO.setPercentage("0.00");
									}
									returnList.add(departVO);
								}
							}
						}
					}
				}
		} catch (Exception e) {
			LOG.error("Exception occured at getEOfcDeptPendancyStatusWiseDetails() in  ItcDashboardService class",e);
		}
		return returnList;
	}
	
	public List<ItecEOfficeVO> getEofficeDesignationWiseDetails(){
	    List<ItecEOfficeVO> returnList = new ArrayList<ItecEOfficeVO>(0);
	    try {
	    	
	    	Map<String,ItecEOfficeVO> designtionMap = new HashMap<String,ItecEOfficeVO>(0);
	    	List<Long> departmentIds = new ArrayList<Long>(0);
            Long[] deptArr = IConstants.ITEC_EOFFICE_DEPT_IDS;
            for (int i = 0; i < deptArr.length; i++) {
              departmentIds.add(deptArr[i]);
            }
            
            ClientResponse response = itcWebServiceUtilService.getWebServiceCall("https://demo.eoffice.ap.gov.in/TTReports/Apsectdesignationwise.php");
		      if (response.getStatus() != 200) {
		        throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		      } else {
		        String output = response.getEntity(String.class);
		        if (output != null && !output.isEmpty()) {
		          JSONArray finalArray = new JSONArray(output);
		          if (finalArray != null && finalArray.length() > 0) {
		            for (int i = 0; i < finalArray.length(); i++) {
		              JSONObject jObj = (JSONObject) finalArray.get(i);
		              Long departmentId = jObj.getLong("departmentid");
		              if(departmentId != null && departmentIds.contains(departmentId)){
		            	  ItecEOfficeVO designationVO = designtionMap.get(jObj.getString("designation"));
		            	  if(designationVO == null){
		            		  designationVO = new ItecEOfficeVO();
		            		  //designationVO.setDepartmentId(jObj.getLong("departmentid"));
		            		  //designationVO.setDepartmentName(jObj.getString("departmentname"));
		            		  designationVO.setDesignation(jObj.getString("designation"));
		            		  	ItecEOfficeVO subvo = new ItecEOfficeVO();
		            		  	subvo.setCreated(jObj.getLong("created"));
		            		  	subvo.setTotalCount(jObj.getLong("totalcount"));
		            		  	subvo.setEmployeeName(jObj.getString("employeename"));
		            		  	subvo.setDepartmentId(jObj.getLong("departmentid"));
		            		  	subvo.setDepartmentName(jObj.getString("departmentname"));
			            		
		            		  	if(subvo.getCreated() != null && subvo.getCreated().longValue() > 0L && subvo.getTotalCount() != null && subvo.getTotalCount().longValue() > 0L){
		            		  		subvo.setPercentage(new BigDecimal(subvo.getTotalCount()*100.00/subvo.getCreated()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								}else{
									subvo.setPercentage("0.00");
								}
		            		  	designationVO.getSubList().add(subvo);
		            		  designtionMap.put(designationVO.getDesignation(), designationVO);
		            	  }else{
		            		  ItecEOfficeVO subvo = new ItecEOfficeVO();
		            		  subvo.setCreated(jObj.getLong("created"));
		            		  subvo.setTotalCount(jObj.getLong("totalcount"));
		            		  subvo.setEmployeeName(jObj.getString("employeename"));
		            		  subvo.setDepartmentId(jObj.getLong("departmentid"));
		            		  subvo.setDepartmentName(jObj.getString("departmentname"));
		            		  
		            		  if(subvo.getCreated() != null && subvo.getCreated().longValue() > 0L && subvo.getTotalCount() != null && subvo.getTotalCount().longValue() > 0L){
		            			  subvo.setPercentage(new BigDecimal(subvo.getTotalCount()*100.00/subvo.getCreated()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								}else{
									subvo.setPercentage("0.00");
								}
		            		  designationVO.getSubList().add(subvo);
		            	  }
		               }
		             }
		           }
		         }
		      }
		      if(designtionMap != null){
		    	  returnList = new ArrayList<ItecEOfficeVO>(designtionMap.values());
		      }
		      
		    } catch (Exception e) {
		      LOG.error("Exception raised at getEofficeDesignationWiseDetails - ItcDashboardService service",e);
		    }
		    return returnList;
	}
	
	public List<ItecEOfficeVO> getEofficeDesignationWisePendencyDetails(){
	    List<ItecEOfficeVO> returnList = new ArrayList<ItecEOfficeVO>(0);
	    try {
	    	
	    	Map<String,ItecEOfficeVO> designtionMap = new HashMap<String,ItecEOfficeVO>(0);
	    	List<Long> departmentIds = new ArrayList<Long>(0);
            Long[] deptArr = IConstants.ITEC_EOFFICE_DEPT_IDS;
            for (int i = 0; i < deptArr.length; i++) {
              departmentIds.add(deptArr[i]);
            }
            
            ClientResponse response = itcWebServiceUtilService.getWebServiceCall("https://demo.eoffice.ap.gov.in/TTReports/Apsectdesignationwise.php");
		      if (response.getStatus() != 200) {
		        throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		      } else {
		        String output = response.getEntity(String.class);
		        if (output != null && !output.isEmpty()) {
		          JSONArray finalArray = new JSONArray(output);
		          if (finalArray != null && finalArray.length() > 0) {
		            for (int i = 0; i < finalArray.length(); i++) {
		              JSONObject jObj = (JSONObject) finalArray.get(i);
		              Long departmentId = jObj.getLong("departmentid");
		              if(departmentId != null && departmentIds.contains(departmentId)){
		            	  ItecEOfficeVO designationVO = designtionMap.get(jObj.getString("designation"));
		            	  if(designationVO == null){
		            		  designationVO = new ItecEOfficeVO();
		            		 // designationVO.setDepartmentId(jObj.getLong("departmentid"));
		            		 // designationVO.setDepartmentName(jObj.getString("departmentname"));
		            		  designationVO.setDesignation(jObj.getString("designation"));
		            		  	ItecEOfficeVO subvo = new ItecEOfficeVO();
		            		  	subvo.setCreated(jObj.getLong("created"));
		            		  	subvo.setTotalCount(jObj.getLong("totalcount"));
		            		  	subvo.setEmployeeName(jObj.getString("employeename"));
		            		  	subvo.setZeroToSeven(jObj.getLong("0-7"));
		            		  	subvo.setEightToFifteen(jObj.getLong("8-15"));
		            		  	subvo.setSixteenToThirty(jObj.getLong("16-30"));
		            		  	subvo.setThirtyoneToSixty(jObj.getLong("31-60"));
		            		  	subvo.setAboveSixty(jObj.getLong(">60"));
		            		  	subvo.setDepartmentId(jObj.getLong("departmentid"));
		            		  	subvo.setDepartmentName(jObj.getString("departmentname"));
		            		  	
		            		  	if(subvo.getCreated() != null && subvo.getCreated().longValue() > 0L && subvo.getTotalCount() != null && subvo.getTotalCount().longValue() > 0L){
		            		  		subvo.setPercentage(new BigDecimal(subvo.getTotalCount()*100.00/subvo.getCreated()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								}else{
									subvo.setPercentage("0.00");
								}
		            		  	designationVO.getSubList().add(subvo);
		            		  designtionMap.put(designationVO.getDesignation(), designationVO);
		            	  }else{
		            		  ItecEOfficeVO subvo = new ItecEOfficeVO();
		            		  subvo.setCreated(jObj.getLong("created"));
		            		  subvo.setTotalCount(jObj.getLong("totalcount"));
		            		  subvo.setEmployeeName(jObj.getString("employeename"));
		            		  subvo.setZeroToSeven(jObj.getLong("0-7"));
		            		  subvo.setEightToFifteen(jObj.getLong("8-15"));
		            		  subvo.setSixteenToThirty(jObj.getLong("16-30"));
		            		  subvo.setThirtyoneToSixty(jObj.getLong("31-60"));
		            		  subvo.setAboveSixty(jObj.getLong(">60"));
		            		  subvo.setDepartmentId(jObj.getLong("departmentid"));
		            		  subvo.setDepartmentName(jObj.getString("departmentname"));
		            		  	
		            		  if(subvo.getCreated() != null && subvo.getCreated().longValue() > 0L && subvo.getTotalCount() != null && subvo.getTotalCount().longValue() > 0L){
		            			  subvo.setPercentage(new BigDecimal(subvo.getTotalCount()*100.00/subvo.getCreated()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
								}else{
									subvo.setPercentage("0.00");
								}
		            		  designationVO.getSubList().add(subvo);
		            	  }
		               }
		             }
		           }
		         }
		      }
		      if(designtionMap != null){
		    	  returnList = new ArrayList<ItecEOfficeVO>(designtionMap.values());
		      }
		      
		    } catch (Exception e) {
		      LOG.error("Exception raised at getEofficeDesignationWisePendencyDetails - ItcDashboardService service",e);
		    }
		    return returnList;
	}
	
	/**
	 * @author Nandhini
	 * @param InputVO inputVO
	 * @description {This service is used to get Sector wise Count  Details.}
	 * @return List<ItecPromotionDetailsVO>
	 * @Date 25-10-2017
	 */
	public List<ItecPromotionDetailsVO> getSectorWiseOverviewCountDetails(InputVO inputVO){
		List<ItecPromotionDetailsVO> returnList = new ArrayList<ItecPromotionDetailsVO>();
		try {
			MOUTrackerIT[] list = new TrackerITServiceSoapProxy().GET_LEAD_CATEGORY_WISE(inputVO.getLeadName(),inputVO.getCategory());
			if(list != null && list.length > 0){
				for (int i = 0; i < list.length; i++) {
					if(inputVO.getSector() != null && inputVO.getSector().trim().equalsIgnoreCase(list[i].getITSECTOR()) 
					  && inputVO.getDistrictValue() != null && inputVO.getDistrictValue().trim().equalsIgnoreCase(list[i].getDISTRICTNAME())){
						ItecPromotionDetailsVO vo = new ItecPromotionDetailsVO();
							vo.setInvestment(list[i].getINVESTMENT());
							vo.setRealizedInvestment(list[i].getREALIZED_INVESTMENT());
							vo.setEmployment(list[i].getEMPLOYMENT());
							vo.setRealizedEmployment(list[i].getREALIZED_EMPLOYMENT());
							vo.setSourceOfLead(list[i].getSOURCE_OF_LEAD());
							vo.setCategory(list[i].getCATEGORY());
							vo.setLineOfActivity(list[i].getLINEOFACTIVITY());
							vo.setSubSector(list[i].getSUBSECTOR());
							vo.setItSector(list[i].getITSECTOR());
							vo.setNameOfCompany(list[i].getNAMEOFTHECOMPANY());
							vo.setDistrictName(list[i].getDISTRICTNAME());
							vo.setDeptName(list[i].getDEPTNAME());
						
						returnList.add(vo);
					}
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getSectorWiseOverviewCountDetails - ItcDashboardService service",e);
		}
		return returnList;
	}
	
	/**
	 * @author Nandhini
	 * @param InputVO inputVO
	 * @description {This service is used to get CM eoDB OverView Deatails.}
	 * @return List<ItecCMeoDBDetailsVO>
	 * @Date 26-10-2017
	 */
	public List<ItecCMeoDBDetailsVO> getCMeoDBOverviewDetails(){
		List<ItecCMeoDBDetailsVO> returnList = new ArrayList<ItecCMeoDBDetailsVO>();
		try {
			MOUTrackerIT[] list = new TrackerITServiceSoapProxy().EODB_ABSTRACT_REPORT();
			if(list != null && list.length > 0){
				for (int i = 0; i < list.length; i++) {
					if(list[i].getCLEARENCE_NAME() != null && list[i].getCLEARENCE_NAME().trim().equalsIgnoreCase("Total")){
						ItecCMeoDBDetailsVO totalVO = new ItecCMeoDBDetailsVO();
							totalVO.setClearenceName(list[i].getCLEARENCE_NAME());
							totalVO.setTotalApplications(list[i].getTOTAL_APPLICATIONS());
							totalVO.setTotalApproved(list[i].getTOTAL_APPROVED());
							totalVO.setTotalRejected(list[i].getTOTAL_REJECTED());
							totalVO.setTotalReApproved(list[i].getTOTAL_REAPPROVED());
							totalVO.setTotalPending(list[i].getTOTAL_PENDING());
							totalVO.setPendingWithInSLA(list[i].getPENDING_WITN_IN_SLA());
							totalVO.setPendingBeyondSLA(list[i].getPENDING_BEYOND_SLA());
					
						returnList.add(totalVO);
					}
				}
			}
			Long highApprovedValue = 0L;
			Long lowApprovedValue = 0L;
			Long highRejectedValue = 0L;
			Long lowRejectedValue = 0L;
			Long highPendingValue = 0L;
			Long lowPendingValue = 0L;
			if(list != null && list.length > 0){
				for (int i = 0; i < list.length; i++) {
				   if(i != list.length-1){
					if(list[i+1].getCLEARENCE_NAME() != null && !list[i+1].getCLEARENCE_NAME().trim().equalsIgnoreCase("Total")){
					if(i == 0){
						highApprovedValue = Long.valueOf(list[i].getTOTAL_APPROVED());
						lowApprovedValue = Long.valueOf(list[i].getTOTAL_APPROVED());
						highRejectedValue = Long.valueOf(list[i].getTOTAL_REJECTED());
						lowRejectedValue = Long.valueOf(list[i].getTOTAL_REJECTED());
						highPendingValue  = Long.valueOf(list[i].getTOTAL_PENDING());
						lowPendingValue = Long.valueOf(list[i].getTOTAL_PENDING());
					}
					
					//High Approved 
					 if(highApprovedValue >= Long.valueOf(list[i+1].getTOTAL_APPROVED())){
						 highApprovedValue = highApprovedValue;
					}else{
						highApprovedValue = Long.valueOf(list[i+1].getTOTAL_APPROVED());
					}
					 
					 //Low Approved
					if(lowApprovedValue <= Long.valueOf(list[i+1].getTOTAL_APPROVED())){
						lowApprovedValue = lowApprovedValue;
					}else{
						lowApprovedValue = Long.valueOf(list[i+1].getTOTAL_APPROVED());
					}
					
					//High Rejected 
					 if(highRejectedValue >= Long.valueOf(list[i+1].getTOTAL_REJECTED())){
						 highRejectedValue = highRejectedValue;
					}else{
						highRejectedValue = Long.valueOf(list[i+1].getTOTAL_REJECTED());
					}
					 
					 //Low Rejected
					if(lowRejectedValue <= Long.valueOf(list[i+1].getTOTAL_REJECTED())){
						lowRejectedValue = lowRejectedValue;
					}else{
						lowRejectedValue = Long.valueOf(list[i+1].getTOTAL_REJECTED());
					}
					
					//High Pending 
					 if(highPendingValue >= Long.valueOf(list[i+1].getTOTAL_PENDING())){
						 highPendingValue = highPendingValue;
					}else{
						highPendingValue = Long.valueOf(list[i+1].getTOTAL_PENDING());
					}
					 
					 //Low Pending
					if(lowPendingValue <= Long.valueOf(list[i+1].getTOTAL_PENDING())){
						lowPendingValue = lowPendingValue;
					}else{
						lowPendingValue = Long.valueOf(list[i+1].getTOTAL_PENDING());
					}
					
					}
				 }
				}
			}
			if(list != null && list.length > 0){
				ItecCMeoDBDetailsVO comparisionVO= new ItecCMeoDBDetailsVO();
				for (int i = 0; i < list.length; i++) {
						if(highApprovedValue != null && highApprovedValue.longValue() == Long.valueOf(list[i].getTOTAL_APPROVED())){
							comparisionVO.setClearenceName(list[i].getCLEARENCE_NAME());
							comparisionVO.setDashboardName(list[i].getDASH_BOARD_NAME());
							comparisionVO.setTotalApproved(list[i].getTOTAL_APPROVED());
						}
						if(lowApprovedValue != null && lowApprovedValue.longValue() == Long.valueOf(list[i].getTOTAL_APPROVED())){
							comparisionVO.setLowApproveClearenceName(list[i].getCLEARENCE_NAME());
							comparisionVO.setLowApprDashBoardName(list[i].getDASH_BOARD_NAME());
							comparisionVO.setLowApproval(list[i].getTOTAL_APPROVED());
						}		
						if(highRejectedValue != null && highRejectedValue.longValue() == Long.valueOf(list[i].getTOTAL_REJECTED())){
							comparisionVO.setHighRejectedClearenceName(list[i].getCLEARENCE_NAME());
							comparisionVO.setHighRejectedDashboardName(list[i].getDASH_BOARD_NAME());
							comparisionVO.setHighRejected(list[i].getTOTAL_REJECTED());
						}
						if(lowRejectedValue != null && lowRejectedValue.longValue() == Long.valueOf(list[i].getTOTAL_REJECTED())){
							comparisionVO.setLowRejectedClearenceName(list[i].getCLEARENCE_NAME());
							comparisionVO.setLowRejctedDashBoardName(list[i].getDASH_BOARD_NAME());
							comparisionVO.setLowRejected(list[i].getTOTAL_REJECTED());
						}
						if(highPendingValue != null && highPendingValue.longValue() == Long.valueOf(list[i].getTOTAL_PENDING())){
							comparisionVO.setHighPendingClearenceName(list[i].getCLEARENCE_NAME());
							comparisionVO.setHighPendingDashboardName(list[i].getDASH_BOARD_NAME());
							comparisionVO.setHighPending(list[i].getTOTAL_PENDING());
						}
						if(lowPendingValue != null && lowPendingValue.longValue() == Long.valueOf(list[i].getTOTAL_PENDING())){
							comparisionVO.setLowPendingClearenceName(list[i].getCLEARENCE_NAME());
							comparisionVO.setLowPendingDashBoardName(list[i].getDASH_BOARD_NAME());
							comparisionVO.setLowPending(list[i].getTOTAL_PENDING());
						}
					}
				returnList.add(comparisionVO);
				}
		} catch (Exception e) {
			LOG.error("Exception raised at getCMeoDBOverviewDetails - ItcDashboardService service",e);
		}
		return returnList;
	}
	
	/**
	 * @author Nandhini
	 * @param InputVO inputVO
	 * @description {This service is used to get CM eoDB Status Deatails.}
	 * @return List<ItecCMeoDBDetailsVO>
	 * @Date 26-10-2017
	 */
	public List<ItecCMeoDBDetailsVO> getCMeoDBStatusDetails(){
		List<ItecCMeoDBDetailsVO> returnList = new ArrayList<ItecCMeoDBDetailsVO>();
		try {
			MOUTrackerIT[] list = new TrackerITServiceSoapProxy().EODB_ABSTRACT_REPORT();
			if(list != null && list.length > 0){
				for (int i = 0; i < list.length; i++) {
					if(list[i].getCLEARENCE_NAME() != null && !list[i].getCLEARENCE_NAME().trim().equalsIgnoreCase("Total")){
						ItecCMeoDBDetailsVO vo = new ItecCMeoDBDetailsVO();
							vo.setClearenceName(list[i].getCLEARENCE_NAME());
							vo.setDashboardName(list[i].getDASH_BOARD_NAME());
							vo.setTotalApplications(list[i].getTOTAL_APPLICATIONS());
							vo.setTotalApproved(list[i].getTOTAL_APPROVED());
							vo.setTotalRejected(list[i].getTOTAL_REJECTED());
							vo.setTotalReApproved(list[i].getTOTAL_REAPPROVED());
							vo.setTotalPending(list[i].getTOTAL_PENDING());
							vo.setPendingWithInSLA(list[i].getPENDING_WITN_IN_SLA());
							vo.setPendingBeyondSLA(list[i].getPENDING_BEYOND_SLA());
							vo.setDashBoardNO(list[i].getDASH_BOARD_NO());
							vo.setClearenceId(list[i].getCLEARANCE_ID());
					
						returnList.add(vo);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCMeoDBStatusDetails - ItcDashboardService service",e);
		}
		return returnList;
	}
	
	/**
	 * @author Nandhini
	 * @param InputVO inputVO
	 * @description {This service is used to get CM eoDB Status Deatails.}
	 * @return List<ItecCMeoDBDetailsVO>
	 * @Date 26-10-2017
	 */
	public List<ItecCMeoDBDetailsVO> getCMeoDBStatusCountDetails(InputVO inputVO){
		List<ItecCMeoDBDetailsVO> returnList = new ArrayList<ItecCMeoDBDetailsVO>();
		try {
			MOUTrackerIT[] list = new TrackerITServiceSoapProxy().GET_SECTOR_WISE_DETAILS(inputVO.getEoDBstatus(),inputVO.getClearence(),null);
			if(list != null && list.length > 0){
				for (int i = 0; i < list.length; i++) {
						ItecCMeoDBDetailsVO vo = new ItecCMeoDBDetailsVO();
							vo.setCategory(list[i].getCATEGORY());
							vo.setFinaYear(list[i].getFINYEAR());
							vo.setSectorName(list[i].getSECTOR_NAME());
							vo.setIndustryName(list[i].getINDUSTRY_NAME());
							vo.setAddress(list[i].getADDRESS());
							vo.setActivity(list[i].getACTIVITY());
							vo.setTotalCost(list[i].getTOTAL_COST());
							vo.setEmpolyeement(list[i].getEMPLOYEMENT());
							vo.setAppFilledDate(list[i].getAPP_FILLED_DATE());
							vo.setCorRecievedDate(list[i].getCOR_RECEIVED_DATE());
							vo.setDelayDays(list[i].getDELAY_DAYS());
							vo.setPermApprovalDate(list[i].getPERM_APPROVAL_DATE());
							vo.setAppRejDate(list[i].getAPP_REJ_DATE());
							vo.setApprovalFileId(list[i].getAPPROVALFILE_ID());
							vo.setClearenceId(list[i].getCLEARANCE_ID());
							vo.setApplicationId(list[i].getAPPLICATION_ID());
							vo.setRegId(list[i].getREG_ID());
					
						returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCMeoDBStatusCountDetails - ItcDashboardService service",e);
		}
		return returnList;
	}
}