package com.itgrids.service.impl;



import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import org.tempuri.SDP;
import org.tempuri.TrackerITServiceSoapProxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.itgrids.dto.ApInnovationCenterVO;
import com.itgrids.dto.ApInnovationSocietyOverviewVO;
import com.itgrids.dto.CmEoDBDtlsVO;
import com.itgrids.dto.CohortDtlsVO;
import com.itgrids.dto.InnovationSocietyDtlsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.ItecCMeoDBDetailsVO;
import com.itgrids.dto.ItecEOfficeVO;
import com.itgrids.dto.ItecPromotionDetailsVO;
import com.itgrids.dto.MeesevaDtlsVO;
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
	public CmEoDBDtlsVO getCMEDOBOverview(InputVO inputVo) {
		CmEoDBDtlsVO resultVO = new CmEoDBDtlsVO();
		 try {
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 Calendar fromDate=Calendar.getInstance();
			 Calendar toDate=Calendar.getInstance();
			 if(inputVo.getFromDate() != null && inputVo.getToDate() != null){
				 fromDate.setTime(sdf.parse(inputVo.getFromDate()));
				 toDate.setTime(sdf.parse(inputVo.getToDate()));
			 }
			 SDP[] dataArr = new TrackerITServiceSoapProxy().get_SDP_Abstract_Details(inputVo.getSector(), fromDate, toDate);
			 if (dataArr != null && dataArr.length > 0 ) {
				 CmEoDBDtlsVO overviewVO = getCmEodbStatusWiseOverviewDetails(dataArr);
				 List<CmEoDBDtlsVO> deptList = getCmEoDbDepartmentList(dataArr);
				 
				 if (deptList != null && deptList.size() > 0) {
					 Collections.sort(deptList, sortDecendingDpetByRejectedCount);
					 setRequiredData(deptList.get(0),deptList.get(deptList.size()-1),"rejected",resultVO);
					 Collections.sort(deptList, sortDecendingDpetByApprovedCount);
					 setRequiredData(deptList.get(0),deptList.get(deptList.size()-1),"approved",resultVO);
					 Collections.sort(deptList, sortDecendingByPendingCount);
					 setRequiredData(deptList.get(0),deptList.get(deptList.size()-1),"pending",resultVO);
				 }
				 resultVO.setOverviewDtls(overviewVO); 
			 }
			
		 }catch (Exception e) {
			 LOG.error("Exception occured at getCMEDOBOverview() in  ItcDashboardService class",e);
		 }
		 return resultVO;
	}

	public void setRequiredData( CmEoDBDtlsVO topDeptVO,CmEoDBDtlsVO lowDeptVO,String type,CmEoDBDtlsVO resultVO) {
		try {
			if (type.equalsIgnoreCase("rejected")) {
				resultVO.setHighRejectedDepartmentName(topDeptVO.getName());
				resultVO.setHighRejectedDepartmentCount(topDeptVO.getRejected());
				resultVO.setLowRejectedDepartmentName(lowDeptVO.getName());
				resultVO.setLowRejectedDepartmentCount(lowDeptVO.getRejected());
			} else if (type.equalsIgnoreCase("approved")) {
				resultVO.setHighApprovalDepartmentName(topDeptVO.getName());
				resultVO.setHighApprovalDepartmentCount(topDeptVO.getAprooved());
				resultVO.setLowApprovalDepartmentName(lowDeptVO.getName());
				resultVO.setLowApprovalDepartmentCount(lowDeptVO.getAprooved());
			} else if (type.equalsIgnoreCase("pending")) {
				resultVO.setHighPendingDepartmentName(topDeptVO.getName());
				resultVO.setHighPendingDepartmentCount(topDeptVO.getPendingBeyondSLA());
				resultVO.setLowPendingDepartmentName(lowDeptVO.getName());
				resultVO.setLowPendingDepartmentCount(lowDeptVO.getPendingBeyondSLA());
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured at setRequiredData() in  ItcDashboardService class",e);
		}
	}
	
		private List<CmEoDBDtlsVO> getCmEoDbDepartmentList(SDP[] dataArr) {
			List<CmEoDBDtlsVO> deptList = new ArrayList<>(0);
			Map<String,CmEoDBDtlsVO> deptMap = new HashMap<>();
			 try {
				   Long count = 0l;
				    for (SDP moutrackerVO : dataArr) {
				    	   count++;
				    	   if (count == dataArr.length){//we are ignoring last object from array
				    		   continue;
				    	   }
				    	 
						   CmEoDBDtlsVO deptVO = deptMap.get(moutrackerVO.getDepartment_Name());
						   if (deptVO == null ) {
							   	 deptVO = new CmEoDBDtlsVO();
							     deptVO.setName(moutrackerVO.getDepartment_Name());
								 deptVO.setIdStr(moutrackerVO.getDepartment_ID()); 
								 deptMap.put(deptVO.getName(), deptVO);
						   } 
						 deptVO.setAprooved(deptVO.getAprooved()+Long.valueOf(moutrackerVO.getTotal_Approved()));
						 deptVO.setRejected(deptVO.getRejected()+Long.valueOf(moutrackerVO.getTotal_Rejected()));
						 deptVO.setPendingBeyondSLA(deptVO.getPendingBeyondSLA()+Long.valueOf(moutrackerVO.getTotal_Pending()));	
					 }
				 if (deptMap.size() > 0) {
					 deptList.addAll(deptMap.values());
				 }
			 } catch (Exception e) {
				 LOG.error("Exception occured at getCmEoDbDepartmentList() in  ItcDashboardService class",e);
			 }
			 return deptList;
		}
		
	//kkb change new
	private CmEoDBDtlsVO getCmEodbStatusWiseOverviewDetails( SDP[] dataArr) {
		CmEoDBDtlsVO overStatusDltsVO = new CmEoDBDtlsVO();
		 try {
			 SDP overViewData = dataArr[dataArr.length-1];
			 overStatusDltsVO.setName(overViewData.getClearance_Name());
			 overStatusDltsVO.setAprooved(Long.valueOf(overViewData.getTotal_Approved()));
			 overStatusDltsVO.setRejected(Long.valueOf(overViewData.getTotal_Rejected()));
			 overStatusDltsVO.setReAprooved(Long.valueOf(overViewData.getTotal_ReApproved()));
			 overStatusDltsVO.setPendingWithinSLA(Long.valueOf(overViewData.getTotal_Pending_Within_SLA()));
			 overStatusDltsVO.setPendingBeyondSLA(Long.valueOf(overViewData.getTotal_Pending_Beyond_SLA()));
			 overStatusDltsVO.setTotal(overStatusDltsVO.getAprooved()+overStatusDltsVO.getReAprooved()
			 +overStatusDltsVO.getRejected()+overStatusDltsVO.getPendingBeyondSLA()+overStatusDltsVO.getPendingWithinSLA());
			
		 } catch (Exception e) {
			 LOG.error("Exception occured at getCmEodbStatusWiseOverviewDetails() in  ItcDashboardService class",e);
		 }
		 return overStatusDltsVO;
	}
	private static Comparator<CmEoDBDtlsVO> sortDecendingDpetByRejectedCount = new Comparator<CmEoDBDtlsVO>() {
		public int compare(CmEoDBDtlsVO o1, CmEoDBDtlsVO o2) {
			try {
				if (o2.getRejected() != null && o1.getRejected() != null) {
					return o2.getRejected().compareTo(o1.getRejected());
				}
			} catch (Exception e) {
				LOG.error(" Exception occured in sortDpetByRejectedCount ", e);
			}
			return 0;
		}
	};
	private static Comparator<CmEoDBDtlsVO> sortDecendingDpetByApprovedCount = new Comparator<CmEoDBDtlsVO>() {
		public int compare(CmEoDBDtlsVO o1, CmEoDBDtlsVO o2) {
			try {
				if (o2.getAprooved() != null && o1.getAprooved() != null) {
					return o2.getAprooved().compareTo(o1.getAprooved());
				}
			} catch (Exception e) {
				LOG.error(" Exception occured in sortDecendingDpetByApprovedCount ", e);
			}
			return 0;
		}
	};
	private static Comparator<CmEoDBDtlsVO> sortDecendingByPendingCount = new Comparator<CmEoDBDtlsVO>() {
		public int compare(CmEoDBDtlsVO o1, CmEoDBDtlsVO o2) {
			try {
				if (o2.getPendingBeyondSLA() != null && o1.getPendingBeyondSLA() != null) {
					return o2.getPendingBeyondSLA().compareTo(o1.getPendingBeyondSLA());
				}
			} catch (Exception e) {
				LOG.error(" Exception occured in sortDecendingByPendingCount ", e);
			}
			return 0;
		}
	};
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get CM eoDB details status wise.}
	 * @return List<MeesevaKPIDtlsVO>
	 * @Date 21-09-2017
	 */
	//babu
	public List<ItecCMeoDBDetailsVO> getCMEDOBReportStatusWise(InputVO inputVo) {
		List<ItecCMeoDBDetailsVO> resultList = new ArrayList<ItecCMeoDBDetailsVO>(0);
		try{
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 Calendar fromDate=Calendar.getInstance();
			 Calendar toDate=Calendar.getInstance();
			 if(inputVo.getFromDate() != null && inputVo.getToDate() != null){
				 fromDate.setTime(sdf.parse(inputVo.getFromDate()));
				 toDate.setTime(sdf.parse(inputVo.getToDate()));
			 }
			SDP[] dataArr = new TrackerITServiceSoapProxy().get_SDP_Abstract_Details(inputVo.getSector(), fromDate, toDate);
			 Map<String,List<ItecCMeoDBDetailsVO>> deptNameAndVosMap = new HashMap<String,List<ItecCMeoDBDetailsVO>>();
		     if(dataArr != null && dataArr.length > 0){
		    	 for( int i = 0; i < dataArr.length-2 ; i++ ){
		    		List<ItecCMeoDBDetailsVO> clearnceVosList= deptNameAndVosMap.get(dataArr[i].getDepartment_Name().trim());
		    		if( clearnceVosList == null ){
		    			clearnceVosList = new ArrayList<ItecCMeoDBDetailsVO>();
		    			deptNameAndVosMap.put(dataArr[i].getDepartment_Name().trim(),clearnceVosList);
		    		}
		    		ItecCMeoDBDetailsVO vo = new ItecCMeoDBDetailsVO();
		    		vo.setClearenceName(dataArr[i].getClearance_Name());
		    		vo.setTotalApplications(dataArr[i].getTotal_Applications());
		    		vo.setTotalApproved(dataArr[i].getTotal_Approved());
		    		vo.setTotalRejected(dataArr[i].getTotal_Rejected());
		    		vo.setTotalPending(dataArr[i].getTotal_Pending());
		    		vo.setPendingWithInSLA(dataArr[i].getTotal_Pending_Within_SLA());
		    		vo.setPendingBeyondSLA(dataArr[i].getTotal_Pending_Beyond_SLA());
		    		vo.setTotalReApproved(dataArr[i].getTotal_ReApproved());
		    		vo.setDashBoardNO(dataArr[i].getDepartment_ID());
		    		vo.setClearenceId(dataArr[i].getClearance_ID());
		    		clearnceVosList.add(vo);
		    	 }
		    	 if(deptNameAndVosMap != null && deptNameAndVosMap.size() > 0){
		    		 for(Entry<String,List<ItecCMeoDBDetailsVO>> entry: deptNameAndVosMap.entrySet()){
		    			 ItecCMeoDBDetailsVO mainVo = new ItecCMeoDBDetailsVO();
		    			String dashBordname= entry.getKey();
		    			if(dashBordname != null && dashBordname.trim().length() > 0){
		    				mainVo.setDashboardName(dashBordname);
		    				mainVo.getSubList().addAll(entry.getValue());
		    				 resultList.add(mainVo);
		    			}
		    			
		    		 }
		    		
		    	 }
		       }
			 
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
			Map<String,ItecEOfficeVO> departMap = new HashMap<String,ItecEOfficeVO>(0);
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
			
			if(returnList != null && !returnList.isEmpty()){
				ItecEOfficeVO finalCountVO = new ItecEOfficeVO();
				for (ItecEOfficeVO vo : returnList) {
					finalCountVO.setCreated(finalCountVO.getCreated()+vo.getCreated());
					finalCountVO.setTotalCount(finalCountVO.getTotalCount()+vo.getTotalCount());
					finalCountVO.setZeroToSeven(finalCountVO.getZeroToSeven()+vo.getZeroToSeven());
					finalCountVO.setEightToFifteen(finalCountVO.getEightToFifteen()+vo.getEightToFifteen());
					finalCountVO.setSixteenToThirty(finalCountVO.getSixteenToThirty()+vo.getSixteenToThirty());
					finalCountVO.setThirtyoneToSixty(finalCountVO.getThirtyoneToSixty()+vo.getThirtyoneToSixty());
					finalCountVO.setAboveSixty(finalCountVO.getAboveSixty()+vo.getAboveSixty());
					finalCountVO.setDepartmentName("ITE & C");
				}
				if(finalCountVO.getCreated() != null && finalCountVO.getCreated().longValue() > 0L && finalCountVO.getTotalCount() != null && finalCountVO.getTotalCount().longValue() > 0L){
					finalCountVO.setPercentage(new BigDecimal(finalCountVO.getTotalCount()*100.00/finalCountVO.getCreated()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}else{
					finalCountVO.setPercentage("0.00");
				}
				finalCountVO.setOrderNumber(1L);
				returnList.add(finalCountVO);
			}
			
			 Collections.sort(returnList, new Comparator<ItecEOfficeVO>() {
	    	    public int compare(ItecEOfficeVO vo1, ItecEOfficeVO vo2) {
	    	        return vo2.getOrderNumber().compareTo(vo1.getOrderNumber());
	    	    }
		    });
			
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
		            	  ItecEOfficeVO designationVO = designtionMap.get(jObj.getString("postname"));
		            	  if(designationVO == null){
		            		  designationVO = new ItecEOfficeVO();
		            		  //designationVO.setDepartmentId(jObj.getLong("departmentid"));
		            		  //designationVO.setDepartmentName(jObj.getString("departmentname"));
		            		  designationVO.setDesignation(jObj.getString("postname"));
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
		            	  ItecEOfficeVO designationVO = designtionMap.get(jObj.getString("postname"));
		            	  if(designationVO == null){
		            		  designationVO = new ItecEOfficeVO();
		            		 // designationVO.setDepartmentId(jObj.getLong("departmentid"));
		            		 // designationVO.setDepartmentName(jObj.getString("departmentname"));
		            		  designationVO.setDesignation(jObj.getString("postname"));
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 Calendar fromDate=Calendar.getInstance();
			 Calendar toDate=Calendar.getInstance();
			 if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				 fromDate.setTime(sdf.parse(inputVO.getFromDate()));
				 toDate.setTime(sdf.parse(inputVO.getToDate()));
			 }
			 SDP[]  dataArr = new TrackerITServiceSoapProxy().get_SDP_Total_Details(inputVO.getSector(), inputVO.getDeptCode(),inputVO.getClearence() , inputVO.getStatus(), fromDate, toDate);
			if(dataArr != null && dataArr.length > 0){
				 for (SDP sdpObj:dataArr) {
					
						ItecCMeoDBDetailsVO vo = new ItecCMeoDBDetailsVO();
						    vo.setDistrictName(sdpObj.getDistrict());
						    vo.setAddress(sdpObj.getAddress());
						    vo.setSectorName(sdpObj.getSector());
						    vo.setActivity(sdpObj.getActivity());
						    vo.setInvestmentAmount(sdpObj.getInvestment_Amount());
						    vo.setEmpolyeement(sdpObj.getEmployment());
						    vo.setAppFilledDate(sdpObj.getApplication_Filled_Date());
						    vo.setRecievedDate(sdpObj.getReceived_Date());
						    vo.setSlaDays(sdpObj.getSLA_Days());
						    vo.setPermApprovalDate(sdpObj.getPermissable_Approval_Date());
						    vo.setStatus(sdpObj.getStatus());
						    vo.setApprovalDate(sdpObj.getApproval_Date());
							vo.setApprovalFileId(sdpObj.getApproval_File_ID());	
							
						returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCMeoDBStatusCountDetails - ItcDashboardService service",e);
		}
		return returnList;
	}
	
	public List<ItecEOfficeVO> getDepartmentWiseHierarchicalDetails(){
		List<ItecEOfficeVO> deptList = new ArrayList<ItecEOfficeVO>();
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
									deptList.add(departVO);
								}
							}
						}
					}
				getHierarchicalDesignationsListForDept(deptList);
				}
		} catch (Exception e) {
			LOG.error("Exception raised at getDepartmentWiseHierarchicalDetails - ItcDashboardService service",e);
		}
		return deptList;
	}
	
	private void getHierarchicalDesignationsListForDept(List<ItecEOfficeVO> departmentList){
		try {
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
		            	  ItecEOfficeVO deptvo = getMatchedItecEOfficeVO(departmentList, departmentId);
		            	  if(deptvo != null){
		            		  if(departmentId != null && departmentId.longValue() == 15L){
		            			  String postName = jObj.getString("postname");
			            		  if(postName != null && postName.trim().equalsIgnoreCase("JOINT SECRETARY")){
			            			  ItecEOfficeVO vo = new ItecEOfficeVO();
			            			  vo.setPostName(postName);
			            			  vo.setDesignation(jObj.getString("designation"));
			            			  vo.setEmployeeName(jObj.getString("employeename"));
			            			  vo.setCreated(jObj.getLong("created"));
			            			  vo.setTotalCount(jObj.getLong("totalcount"));
			            			  deptvo.getJsList().add(vo);
			            		  }else if(postName != null && postName.trim().equalsIgnoreCase("DIRECTOR")){
			            			  ItecEOfficeVO vo = new ItecEOfficeVO();
			            			  vo.setPostName(postName);
			            			  vo.setDesignation(jObj.getString("designation"));
			            			  vo.setEmployeeName(jObj.getString("employeename"));
			            			  vo.setCreated(jObj.getLong("created"));
			            			  vo.setTotalCount(jObj.getLong("totalcount"));
			            			  deptvo.getDirectorList().add(vo);
			            		  }else if(postName != null && postName.trim().equalsIgnoreCase("JOINT DIRECTOR")){
			            			  ItecEOfficeVO vo = new ItecEOfficeVO();
			            			  vo.setPostName(postName);
			            			  vo.setDesignation(jObj.getString("designation"));
			            			  vo.setEmployeeName(jObj.getString("employeename"));
			            			  vo.setCreated(jObj.getLong("created"));
			            			  vo.setTotalCount(jObj.getLong("totalcount"));
			            			  deptvo.getJdList().add(vo);
			            		  }else if(postName != null && postName.trim().equalsIgnoreCase("SPECIAL OFFICER")){
			            			  ItecEOfficeVO vo = new ItecEOfficeVO();
			            			  vo.setPostName(postName);
			            			  vo.setDesignation(jObj.getString("designation"));
			            			  vo.setEmployeeName(jObj.getString("employeename"));
			            			  vo.setCreated(jObj.getLong("created"));
			            			  vo.setTotalCount(jObj.getLong("totalcount"));
			            			  deptvo.getSpecialOfficerList().add(vo);
			            		  }else if(postName != null && (postName.trim().equalsIgnoreCase("PROJECT MANAGER") || postName.trim().equalsIgnoreCase("PROJECT ENGINEER") || postName.trim().equalsIgnoreCase("PROJECT ASSISTANT"))){
			            			  ItecEOfficeVO vo = new ItecEOfficeVO();
			            			  vo.setPostName(postName);
			            			  vo.setDesignation(jObj.getString("designation"));
			            			  vo.setEmployeeName(jObj.getString("employeename"));
			            			  vo.setCreated(jObj.getLong("created"));
			            			  vo.setTotalCount(jObj.getLong("totalcount"));
			            			  deptvo.getPmList().add(vo);
			            		  }else if(postName != null && postName.trim().equalsIgnoreCase("SECTION OFFICER")){
			            			  ItecEOfficeVO vo = new ItecEOfficeVO();
			            			  vo.setPostName(postName);
			            			  vo.setDesignation(jObj.getString("designation"));
			            			  vo.setEmployeeName(jObj.getString("employeename"));
			            			  vo.setCreated(jObj.getLong("created"));
			            			  vo.setTotalCount(jObj.getLong("totalcount"));
			            			  deptvo.getSoList().add(vo);
			            		  }else if(postName != null && postName.trim().equalsIgnoreCase("ADMIN ASSISTANT")){
			            			  ItecEOfficeVO vo = new ItecEOfficeVO();
			            			  vo.setPostName(postName);
			            			  vo.setDesignation(jObj.getString("designation"));
			            			  vo.setEmployeeName(jObj.getString("employeename"));
			            			  vo.setCreated(jObj.getLong("created"));
			            			  vo.setTotalCount(jObj.getLong("totalcount"));
			            			  deptvo.getAaoList().add(vo);
			            		  }else if(postName != null && postName.trim().equalsIgnoreCase("ASSISTANT SECTION OFFICER")){
			            			  ItecEOfficeVO vo = new ItecEOfficeVO();
			            			  vo.setPostName(postName);
			            			  vo.setDesignation(jObj.getString("designation"));
			            			  vo.setEmployeeName(jObj.getString("employeename"));
			            			  vo.setCreated(jObj.getLong("created"));
			            			  vo.setTotalCount(jObj.getLong("totalcount"));
			            			  deptvo.getAsoList().add(vo);
			            		  }else if(postName != null && postName.trim().equalsIgnoreCase("MINISTER")){
			            			  ItecEOfficeVO vo = new ItecEOfficeVO();
			            			  vo.setPostName(postName);
			            			  vo.setDesignation(jObj.getString("designation"));
			            			  vo.setEmployeeName(jObj.getString("employeename"));
			            			  vo.setCreated(jObj.getLong("created"));
			            			  vo.setTotalCount(jObj.getLong("totalcount"));
			            			  deptvo.getMinisterList().add(vo);
			            		  }else if(postName != null && !postName.trim().equalsIgnoreCase("MINISTER")){
			            			  ItecEOfficeVO vo = new ItecEOfficeVO();
			            			  vo.setPostName(postName);
			            			  vo.setDesignation(jObj.getString("designation"));
			            			  vo.setEmployeeName(jObj.getString("employeename"));
			            			  vo.setCreated(jObj.getLong("created"));
			            			  vo.setTotalCount(jObj.getLong("totalcount"));
			            			  deptvo.getOtherList().add(vo);
			            		  }
		            		  }
		            		  else{
		            			  String postName = jObj.getString("postname");
			            		  if(postName != null && postName.trim().equalsIgnoreCase("CHIEF EXECUTIVE OFFICER")){
			            			  ItecEOfficeVO vo = new ItecEOfficeVO();
			            			  vo.setPostName(postName);
			            			  vo.setDesignation(jObj.getString("designation"));
			            			  vo.setEmployeeName(jObj.getString("employeename"));
			            			  vo.setCreated(jObj.getLong("created"));
			            			  vo.setTotalCount(jObj.getLong("totalcount"));
			            			  deptvo.getCeoList().add(vo);
			            		  }else{
			            			  ItecEOfficeVO vo = new ItecEOfficeVO();
			            			  vo.setPostName(postName);
			            			  vo.setDesignation(jObj.getString("designation"));
			            			  vo.setEmployeeName(jObj.getString("employeename"));
			            			  vo.setCreated(jObj.getLong("created"));
			            			  vo.setTotalCount(jObj.getLong("totalcount"));
			            			  deptvo.getOtherList().add(vo);
			            		  }
		            		  }
		            	  }
		              }
		            }
		          }
		        }
		      }
		} catch (Exception e) {
			LOG.error("Exception raised at getHierarchicalDesignationsListForDept - ItcDashboardService service",e);
		}
	}
	
	private ItecEOfficeVO getMatchedItecEOfficeVO(List<ItecEOfficeVO> list,Long deptId){
		try{
			if(list != null && !list.isEmpty()){
				for (ItecEOfficeVO vo : list) {
					if(vo.getDepartmentId() != null && vo.getDepartmentId().longValue() == deptId.longValue()){
						return vo;
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getMatchedItecEOfficeVO - ItcDashboardService service", e);
		}
		return null;
	}
	
	/**
	 * @author Nandhini
	 * @param InputVO inputVO
	 * @description {This service is used to get CM eoDB Status Deatails.}
	 * @return List<ItecCMeoDBDetailsVO>
	 * @Date 27-10-2017
	 */
	public List<ItecCMeoDBDetailsVO> getCMeoDBStatusDetailsNew(){
	    List<ItecCMeoDBDetailsVO> returnList = new ArrayList<ItecCMeoDBDetailsVO>(0);
	    try {
	    	
	    	Map<String,ItecCMeoDBDetailsVO> departmentMap = new HashMap<String,ItecCMeoDBDetailsVO>(0);
	    	MOUTrackerIT[] list = new TrackerITServiceSoapProxy().EODB_ABSTRACT_REPORT();
            if(list != null && list.length > 0){
				for (int i = 0; i < list.length; i++) {
					if(list[i].getCLEARENCE_NAME() != null && !list[i].getCLEARENCE_NAME().trim().equalsIgnoreCase("Total")){
						ItecCMeoDBDetailsVO departmntVO = departmentMap.get(list[i].getDASH_BOARD_NAME());
		            	  if(departmntVO == null){
		            		  departmntVO  = new ItecCMeoDBDetailsVO();
		            		  departmntVO.setDashboardName(list[i].getDASH_BOARD_NAME());
		            		  ItecCMeoDBDetailsVO subVO = new ItecCMeoDBDetailsVO();
			            		  subVO.setClearenceName(list[i].getCLEARENCE_NAME());
			            		  subVO.setTotalApplications(list[i].getTOTAL_APPLICATIONS());
			            		  subVO.setTotalApproved(list[i].getTOTAL_APPROVED());
			            		  subVO.setTotalRejected(list[i].getTOTAL_REJECTED());
								  subVO.setTotalReApproved(list[i].getTOTAL_REAPPROVED());
								  subVO.setTotalPending(list[i].getTOTAL_PENDING());
								  subVO.setPendingWithInSLA(list[i].getPENDING_WITN_IN_SLA());
								  subVO.setPendingBeyondSLA(list[i].getPENDING_BEYOND_SLA());
								  subVO.setDashBoardNO(list[i].getDASH_BOARD_NO());
								  subVO.setClearenceId(list[i].getCLEARANCE_ID());
							  departmntVO.getSubList().add(subVO);
							  departmentMap.put(departmntVO.getDashboardName(), departmntVO);
		            		 }else{
		            			 ItecCMeoDBDetailsVO subVO = new ItecCMeoDBDetailsVO();
			            			  subVO.setClearenceName(list[i].getCLEARENCE_NAME());
				            		  subVO.setTotalApplications(list[i].getTOTAL_APPLICATIONS());
				            		  subVO.setTotalApproved(list[i].getTOTAL_APPROVED());
				            		  subVO.setTotalRejected(list[i].getTOTAL_REJECTED());
									  subVO.setTotalReApproved(list[i].getTOTAL_REAPPROVED());
									  subVO.setTotalPending(list[i].getTOTAL_PENDING());
									  subVO.setPendingWithInSLA(list[i].getPENDING_WITN_IN_SLA());
									  subVO.setPendingBeyondSLA(list[i].getPENDING_BEYOND_SLA());
									  subVO.setDashBoardNO(list[i].getDASH_BOARD_NO());
									  subVO.setClearenceId(list[i].getCLEARANCE_ID());
							  departmntVO.getSubList().add(subVO);
			            	  }
							}
		             	}
		           }
            
              if(departmentMap != null){
		    	  returnList = new ArrayList<ItecCMeoDBDetailsVO>(departmentMap.values());
		      }
              if(returnList != null && !returnList.isEmpty()){
            	  for (ItecCMeoDBDetailsVO finalVO : returnList) {
					for (ItecCMeoDBDetailsVO subCuntVO : finalVO.getSubList()) {
						finalVO.setDeprtTotalApplications(finalVO.getDeprtTotalApplications()+Long.valueOf(subCuntVO.getTotalApplications()));
						finalVO.setDeprtTotalApproval(finalVO.getDeprtTotalApproval()+Long.valueOf(subCuntVO.getTotalApproved()));
					}
				}
              }
		      
		    } catch (Exception e) {
		      LOG.error("Exception raised at getCMeoDBStatusDetailsNew - ItcDashboardService service",e);
		    }
		    return returnList;
	}
	public List<CmEoDBDtlsVO> getCMeoDBSectorWiseStatusDetais(InputVO inputVo){
		List<CmEoDBDtlsVO> finalList = new ArrayList<CmEoDBDtlsVO>();
		String [] sectorsArr={"E","I"};
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 Calendar fromDate=Calendar.getInstance();
			 Calendar toDate=Calendar.getInstance();
			 if(inputVo.getFromDate() != null && inputVo.getToDate() != null){
				 fromDate.setTime(sdf.parse(inputVo.getFromDate()));
				 toDate.setTime(sdf.parse(inputVo.getToDate()));
			 }
			 for(String sector : sectorsArr){
				 SDP[] dataArr = new TrackerITServiceSoapProxy().get_SDP_Abstract_Details(sector, fromDate, toDate);
				 CmEoDBDtlsVO returnVo= getDepartmentWiseApplicationDetails(dataArr);
				 finalList.add(returnVo);
			 }
			
	}catch(Exception e){
		 LOG.error("Exception raised at getCMeoDBSectorWiseStatusDetais - ItcDashboardService service",e);
	}
		return finalList;
	}
	public CmEoDBDtlsVO getDepartmentWiseApplicationDetails( SDP[] dataArr){
		CmEoDBDtlsVO vo = new CmEoDBDtlsVO();
		try{
			if(dataArr != null && dataArr.length >0){
				vo.setTotal(0L);
				vo.setAprooved(0L);
				vo.setRejected(0L);
				vo.setReAprooved(0L);
				vo.setPendingBeyondSLA(0L);
				vo.setPendingWithinSLA(0L);
				for(SDP  obj : dataArr){
					vo.setTotal(vo.getTotal()+Long.valueOf(obj.getTotal_Applications()));
					vo.setAprooved(vo.getAprooved()+Long.valueOf(obj.getTotal_Approved()));
					vo.setRejected(vo.getRejected()+Long.valueOf(obj.getTotal_Rejected()));
					vo.setReAprooved(vo.getReAprooved()+Long.valueOf(obj.getTotal_ReApproved()));
					vo.setPendingBeyondSLA(vo.getPendingBeyondSLA()+Long.valueOf(obj.getTotal_Pending_Beyond_SLA()));
					vo.setPendingWithinSLA(vo.getPendingWithinSLA()+Long.valueOf(obj.getTotal_Pending_Within_SLA()));
				}
			}
		}catch(Exception e){
			 LOG.error("Exception raised at setStatusCountsOfSectors - ItcDashboardService service",e);
		}
		return vo;
	}
	
	/**
	 * @author Nandhini
	 * @param InputVO inputVO
	 * @description {This service is used to get E Office Designation Wise Deatails.}
	 * @return List<ItecEOfficeVO>
	 * @Date 11-08-2017
	 */
	
	public List<ItecEOfficeVO> getEofficeDesginationDetailsByDepartment(InputVO inputVO){
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
		            for (int i = 0; i < finalArray.length(); i++) {
		              JSONObject jObj = (JSONObject) finalArray.get(i);
		              Long departmentId = jObj.getLong("departmentid");
		              String postName = jObj.getString("postname");
		              if(inputVO.getDepartmentId() != null && inputVO.getDepartmentId().longValue() == departmentId.longValue()){
		            	  ItecEOfficeVO  designationVO = new ItecEOfficeVO();
		            	  if(departmentId != null && departmentId.longValue() == 15L){
		            		  if(postName != null && postName.trim().equalsIgnoreCase("MINISTER")){
		            			  designationVO.setOrderNumber(1L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("PRINCIPAL SECRETARY")){
		            			  designationVO.setOrderNumber(2L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("JOINT SECRETARY")){
		            			  designationVO.setOrderNumber(3L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("DIRECTOR")){
		            			  designationVO.setOrderNumber(4L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("CHIEF EXECUTIVE OFFICER")){
		            			  designationVO.setOrderNumber(5L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("JOINT DIRECTOR")){
		            			  designationVO.setOrderNumber(6L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("SPECIAL OFFICER")){
		            			  designationVO.setOrderNumber(7L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("PROJECT MANAGER")){
		            			  designationVO.setOrderNumber(8L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("SECTION OFFICER")){
		            			  designationVO.setOrderNumber(9L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("ASSISTANT SECTION OFFICER")){
		            			  designationVO.setOrderNumber(10L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("PROJECT ENGINEER")){
		            			  designationVO.setOrderNumber(11L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("ADMIN ASSISTANT")){
		            			  designationVO.setOrderNumber(12L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("ACCOUNTS OFFICER")){
		            			  designationVO.setOrderNumber(13L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("JUNIOR ACCOUNTS OFFICER")){
		            			  designationVO.setOrderNumber(14L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("ASSISTANT ACCOUNT OFFICER")){
		            			  designationVO.setOrderNumber(15L);
		            		  }else{
		            			  designationVO.setOrderNumber(16L);
		            		  }
		            	  }else if(departmentId != null && departmentId.longValue() == 1260L){
		            		  if(postName != null && postName.trim().equalsIgnoreCase("Director")){
		            			  designationVO.setOrderNumber(1L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("Assistant Secetary")){
		            			  designationVO.setOrderNumber(2L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("Deputy Director")){
		            			  designationVO.setOrderNumber(3L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("SUPERINTENDENT")){
		            			  designationVO.setOrderNumber(4L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("SENIOR ASSISTANT")){
		            			  designationVO.setOrderNumber(5L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("COMPUTER OPERATOR")){
		            			  designationVO.setOrderNumber(6L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("PERSONAL ASSISTANT")){
		            			  designationVO.setOrderNumber(7L);
		            		  }else{
		            			  designationVO.setOrderNumber(8L);
		            		  }
		            	  }else if(departmentId != null && departmentId.longValue() == 6567L){
		            		  if(postName != null && postName.trim().equalsIgnoreCase("CHIEF EXECUTIVE OFFICER")){
		            			  designationVO.setOrderNumber(1L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("Joint Director")){
		            			  designationVO.setOrderNumber(2L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("JOINT SECRETARY")){
		            			  designationVO.setOrderNumber(3L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("Manager")){
		            			  designationVO.setOrderNumber(4L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("Executive Assistant")){
		            			  designationVO.setOrderNumber(5L);
		            		  }else{
		            			  designationVO.setOrderNumber(6L);
		            		  }
		            	  }else if(departmentId != null && departmentId.longValue() == 6581L){
		            		  if(postName != null && postName.trim().equalsIgnoreCase("CHIEF EXECUTIVE OFFICER")){
		            			  designationVO.setOrderNumber(1L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("VICE PRESIDENT")){
		            			  designationVO.setOrderNumber(2L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("GENERAL MANAGER")){
		            			  designationVO.setOrderNumber(3L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("MANAGER")){
		            			  designationVO.setOrderNumber(4L);
		            		  }else{
		            			  designationVO.setOrderNumber(6L);
		            		  }
		            	  }else if(departmentId != null && departmentId.longValue() == 1257L){
		            		  if(postName != null && postName.trim().equalsIgnoreCase("GENERAL MANAGER")){
		            			  designationVO.setOrderNumber(1L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("MANAGER")){
		            			  designationVO.setOrderNumber(2L);
		            		  }else{
		            			  designationVO.setOrderNumber(3L);
		            		  }
		            	  }else if(departmentId != null && departmentId.longValue() == 3688L){
		            		  if(postName != null && postName.trim().equalsIgnoreCase("CHIEF EXECUTIVE OFFICER")){
		            			  designationVO.setOrderNumber(1L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("SPECIAL OFFICER")){
		            			  designationVO.setOrderNumber(2L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("MANAGER")){
		            			  designationVO.setOrderNumber(4L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("GENERAL MANAGER")){
		            			  designationVO.setOrderNumber(3L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("PROJECT MANAGER")){
		            			  designationVO.setOrderNumber(5L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("PROGRAMME MANAGER")){
		            			  designationVO.setOrderNumber(6L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("ACCOUNTS OFFICER")){
		            			  designationVO.setOrderNumber(7L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("IT ASSOCIATE")){
		            			  designationVO.setOrderNumber(8L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("TEAM LEAD")){
		            			  designationVO.setOrderNumber(9L);
		            		  }else{
		            			  designationVO.setOrderNumber(10L);
		            		  }
		            	  }else if(departmentId != null && departmentId.longValue() == 5300L){
		            		  if(postName != null && postName.trim().equalsIgnoreCase("CHIEF EXECUTIVE OFFICER")){
		            			  designationVO.setOrderNumber(1L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("GENERAL MANAGER")){
		            			  designationVO.setOrderNumber(2L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("ACCOUNTS OFFICER")){
		            			  designationVO.setOrderNumber(3L);
		            		  }else{
		            			  designationVO.setOrderNumber(4L);
		            		  }
		            	  }else if(departmentId != null && departmentId.longValue() == 5300L){
		            		  if(postName != null && postName.trim().equalsIgnoreCase("CEO EGOVERNANCE")){
		            			  designationVO.setOrderNumber(1L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("Joint Director")){
		            			  designationVO.setOrderNumber(2L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("MANAGER")){
		            			  designationVO.setOrderNumber(3L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("ACCOUNTS OFFICER")){
		            			  designationVO.setOrderNumber(4L);
		            		  }else if(postName != null && postName.trim().equalsIgnoreCase("ASSISTANT ACCOUNTS OFFICER")){
		            			  designationVO.setOrderNumber(5L);
		            		  }else{
		            			  designationVO.setOrderNumber(6L);
		            		  }
		            	  }
		            	  
		            	 
		            		  designationVO.setDesignation(jObj.getString("postname"));
		            		  designationVO.setOwnerName(jObj.getString("ouname"));
		            		  designationVO.setCreated(jObj.getLong("created"));
		            		  designationVO.setTotalCount(jObj.getLong("totalcount"));
		            		  designationVO.setEmployeeName(jObj.getString("employeename"));
		            		  designationVO.setDepartmentId(jObj.getLong("departmentid"));
		            		  designationVO.setDepartmentName(jObj.getString("departmentname"));
		            		  designationVO.setZeroToSeven(jObj.getLong("0-7"));
		            		  designationVO.setEightToFifteen(jObj.getLong("8-15"));
		            		  designationVO.setSixteenToThirty(jObj.getLong("16-30"));
		            		  designationVO.setThirtyoneToSixty(jObj.getLong("31-60"));
		            		  designationVO.setAboveSixty(jObj.getLong(">60"));
			            	  if(designationVO.getCreated() != null && designationVO.getCreated().longValue() > 0L && designationVO.getTotalCount() != null && designationVO.getTotalCount().longValue() > 0L){
			            		designationVO.setPercentage(new BigDecimal(designationVO.getTotalCount()*100.00/designationVO.getCreated()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
							  }else{
								designationVO.setPercentage("0.00");
							  }
			            	  returnList.add(designationVO);
		              		}
		            	  
		              	  }
		               }
		             }
		           }
		      
		      
		      Collections.sort(returnList, new Comparator<ItecEOfficeVO>() {
		    	    public int compare(ItecEOfficeVO vo1, ItecEOfficeVO vo2) {
		    	        return vo1.getOrderNumber().compareTo(vo2.getOrderNumber());
		    	    }
		    	});
		      
		    } catch (Exception e) {
		      LOG.error("Exception raised at getEofficeDesginationDetailsByDepartment - ItcDashboardService service",e);
		    }
		    return returnList;
	}
}
