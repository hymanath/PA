package com.itgrids.service.impl;



import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
import com.itgrids.dto.ItecOverviewVO;
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
	 * @author Santosh
	 * @param InputVO inputVO
	 * @description {This service is used to get department wise promotion overview details.}
	 * @return ItecOverviewVO
	 * @Date 21-09-2017
	 */
	public ItecOverviewVO getPromotionsOverviewByDepartmentType(InputVO inputVO) {
		ItecOverviewVO resultVO = new ItecOverviewVO();
		 try {
			 
		 }catch (Exception e) {
			 LOG.error("Exception occured at getPromotionsOverviewByDepartmentType() in  ItcDashboardService class",e);
		 }
		 return resultVO;
	}
	/**
	 * @author Santosh
	 * @param InputVO inputVO
	 * @description {This service is used to get department wise promotion overview details.}
	 * @return List<ItInformationDtlsVO>
	 * @Date 21-09-2017
	 */
	public List<ItInformationDtlsVO> getPromotionsDetailedDepartmentWise(InputVO inputVO) {
		List<ItInformationDtlsVO> resultList = new ArrayList<ItInformationDtlsVO>(0);
		 try {
			 
		 }catch (Exception e) {
			 LOG.error("Exception occured at getPromotionsDetailedDepartmentWise() in  ItcDashboardService class",e);
		 }
		 return resultList;
	}
	
	/**
	 * @author Santosh
	 * @param InputVO inputVO
	 * @description {This service is used to get E office details.}
	 * @return EofficeDtlsVO
	 * @Date 21-09-2017
	 */
	public EofficeDtlsVO getEOfficePendencyDtlsByDepartmentType(InputVO inputVO) {
		EofficeDtlsVO resultVO = new EofficeDtlsVO();
		 try {
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception occured at getEOfficePendencyDtlsByDepartmentType() in  ItcDashboardService class",e);
		 }
		 return resultVO;
	}
	/**
	 * @author Santosh
	 * @param InputVO inputVO
	 * @description {This service is used to get E office details department or designation and day wise}
	 * @return List<EofficeDtlsVO>
	 * @Date 21-09-2017
	 */
	public List<EofficeDtlsVO> getEOfficePendencyByDepartmentAndDayWise(InputVO inputVO) {
		List<EofficeDtlsVO> resultList = new ArrayList<EofficeDtlsVO>(0);
		 try {
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception occured at getEOfficePendencyByDepartmentAndDayWise() in  ItcDashboardService class",e);
		 }
		 return resultList;
	}
	/**
	 * @author Santosh
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
	 * @author Santosh
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
	 * @author Santosh
	 * @param InputVO inputVO
	 * @description {This service is used to get MeesevaKpi indicator progress details.}
	 * @return List<MeesevaKPIDtlsVO>
	 * @Date 21-09-2017
	 */
	public List<MeesevaKPIDtlsVO> getMeesevaKPIIndicatorsProgressDtls(InputVO inputVO) {
		List<MeesevaKPIDtlsVO> resultList = new ArrayList<MeesevaKPIDtlsVO>(0);
		 try {
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception occured at getMeesevaKPIIndicatorsProgressDtls() in  ItcDashboardService class",e);
		 }
		 return resultList;
	}
	/**
	 * @author Santosh
	 * @param InputVO inputVO
	 * @description {This service is used to get MeesevaKpi indicator details period wise.}
	 * @return List<MeesevaKPIDtlsVO>
	 * @Date 21-09-2017
	 */
	public List<MeesevaKPIDtlsVO> getMeesevaKPIIndicatorsPeriodWise(InputVO inputVO) {
		List<MeesevaKPIDtlsVO> resultList = new ArrayList<MeesevaKPIDtlsVO>(0);
		 try {
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception occured at getMeesevaKPIIndicatorsPeriodWise() in  ItcDashboardService class",e);
		 }
		 return resultList;
	}
	/**
	 * @author Santosh
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
	 * @author Santosh
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
	 * @author Santosh
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
	 * @author Santosh
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
	 * @author Santosh
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
	 * @author Santosh
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
	 * @author Santosh
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
	 * @author Santosh
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
	 * @author Santosh
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
	 * @author Santosh
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
}