package com.itgrids.service.integration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IConvergenceTypeDAO;
import com.itgrids.dao.INregaComponentDAO;
import com.itgrids.dao.INregaComponentServiceDAO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.NregaConsolidatedDataVO;
import com.itgrids.dto.NregaConsolidatedInputVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsProjectsVO;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.service.integration.impl.INREGSConsolidatedService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.NREGSCumulativeThread;
import com.sun.jersey.api.client.ClientResponse;

@Service
@Transactional
public class NREGSConsolidatedService implements INREGSConsolidatedService{

	private static final Logger LOG = Logger.getLogger(NREGSConsolidatedService.class);
	
	@Autowired
	private INregaComponentServiceDAO nregaComponentServiceDAO;
	
	 
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private IConvergenceTypeDAO convergenceTypeDAO;
	@Autowired
	private INregaComponentDAO nregaComponentDAO;
	
	/*
	 * Date : 26/07/2017
	 * Author :Sravanth
	 * @description : getNregaComponentsList
	 * 
	 */
	public List<NregaConsolidatedDataVO> getNregaComponentsList(List<Object[]> list){
		List<NregaConsolidatedDataVO> returnList = new ArrayList<NregaConsolidatedDataVO>(0);
		try {
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					NregaConsolidatedDataVO componentvo = new NregaConsolidatedDataVO();
					componentvo.setComponent(obj[1] != null ? obj[1].toString():"");
					componentvo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					returnList.add(componentvo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getNregaComponentsList - NREGSConsolidatedService Service", e);
		}
		return returnList;
	}
	
	/*
	 * Date : 21/07/2017
	 * Author :Sravanth
	 * @description : getNREGSLevelWiseConsolidatedReport
	 * 
	 */
	public List<NregaConsolidatedDataVO> getNREGSLevelWiseConsolidatedReport(NregaConsolidatedInputVO inputVO){
		List<NregaConsolidatedDataVO> returnList = new ArrayList<NregaConsolidatedDataVO>(0);
		try {
			Map<String,NregaConsolidatedDataVO> locationMap = new LinkedHashMap<String,NregaConsolidatedDataVO>();
			List<NregaConsolidatedInputVO> urlsList = new ArrayList<NregaConsolidatedInputVO>(0);
			//List<NregaConsolidatedDataVO> componentsList = new ArrayList<NregaConsolidatedDataVO>(0);
			
			if(inputVO != null && inputVO.getComponentIds() != null && !inputVO.getComponentIds().isEmpty()){
				List<Object[]> list = nregaComponentServiceDAO.getComponentUrlsByComponentIds(inputVO.getComponentIds(), "DATA");
				if(list != null && !list.isEmpty()){
					for (Object[] obj : list) {
						NregaConsolidatedInputVO vo = new NregaConsolidatedInputVO();
						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setUrl(obj[2] != null ? obj[2].toString():"");
						vo.setComponentName(obj[1] != null ? obj[1].toString():"");
						urlsList.add(vo);
					}
				}
				
				List<ClientResponse> responseList = new ArrayList<ClientResponse>();
				if(urlsList != null && !urlsList.isEmpty()){
					ExecutorService executor = Executors.newFixedThreadPool(30);
					for (NregaConsolidatedInputVO urlvo : urlsList) {
						if(urlvo.getId() != null && (urlvo.getId().longValue() == 43l || urlvo.getId().longValue() == 44l 
								|| urlvo.getId().longValue() == 45l || urlvo.getId().longValue() == 46l || urlvo.getId().longValue() == 47l 
								|| urlvo.getId().longValue() == 48l || urlvo.getId().longValue() == 61l || urlvo.getId().longValue() == 62l
								|| urlvo.getId().longValue() == 63l)){
							inputVO.setCategoryName(urlvo.getComponentName());
						}
						else if(urlvo.getId() != null && (urlvo.getId().longValue() == 49l || urlvo.getId().longValue() == 50l 
								|| urlvo.getId().longValue() == 51l || urlvo.getId().longValue() == 52l || urlvo.getId().longValue() == 53l 
								|| urlvo.getId().longValue() == 54l || urlvo.getId().longValue() == 55l || urlvo.getId().longValue() == 56l
								|| urlvo.getId().longValue() == 57l || urlvo.getId().longValue() == 58l || urlvo.getId().longValue() == 59l
								|| urlvo.getId().longValue() == 64l || urlvo.getId().longValue() == 65l || urlvo.getId().longValue() == 66l
								|| urlvo.getId().longValue() == 67l || urlvo.getId().longValue() == 106l)){
							if(urlvo.getId().longValue() == 66l)
								inputVO.setGroupName("Comprehensive Restoration of minor Irrigation Tank");
							else
								inputVO.setGroupName(urlvo.getComponentName());
						}
						else if(urlvo.getId() != null && urlvo.getId().longValue() >= 69l && urlvo.getId().longValue() <= 105l){
							inputVO.setGroupName(urlvo.getComponentName());
						}
						String str = convertingInputVOToString(inputVO);
						String faStr = convertingInputVOToStringForFA(inputVO);
						if(urlvo.getId() != null && urlvo.getId().longValue() == 14l){
							Runnable worker = new NREGSCumulativeThread(urlvo.getUrl(),responseList,faStr);
							executor.execute(worker);
						}
						else{
							Runnable worker = new NREGSCumulativeThread(urlvo.getUrl(),responseList,str);
							executor.execute(worker);
						}
					}	 
					executor.shutdown();
					while (!executor.isTerminated()) {
							
					}
						System.out.println("Finished all threads"+responseList);

				}
				
				if(responseList != null && !responseList.isEmpty()){
					for (ClientResponse response : responseList) {
						if(response == null || response.getStatus() != 200){
							if(response != null){
								System.out.println("Exception Occured in "+response.toString());
								throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
							}
						}else{
							String output = response.getEntity(String.class);
							String returnUrl = response.toString().split(" ")[1].toString().split("rest/")[1];
							String componentName = null;
							if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("LabourBudgetServiceNew/LabourBudgetDataNew"))
								componentName = "Labour Budget";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("FarmPondService_new/FarmPondData_new"))
								componentName = "Farm Ponds";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("IHHLService_new/IHHLData_new"))
								componentName = "IHHL";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("VermiService_new/VermiData_new"))
								componentName = "Vermi Compost";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("SolidWasteManagementServices/SolidWasteManagementData"))
								componentName = "Solid Waste Management";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("BurialGroundsServices/BurialGroundsData"))
								componentName = "Burial Grounds";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("PlayFieldsServices/PlayFieldsData"))
								componentName = "Play Fields";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("AgricultureServices/AgricultureData"))
								componentName = "Agriculture Activities";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("AveragewageServicesNew/AveragewageDataNew"))
								componentName = "Average Wage";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("AverageDaysServicesNew/AverageDaysDataNew"))
								componentName = "Average Days of Employment";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("HundredHoursServices/HundredHoursData"))
								componentName = "HH Completed 100 Days";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("TimePaymentsServicesNew/TimePaymentsDataNew"))
								componentName = "Timely Payment";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("CCRoadsServicesNew/CCRoadsDataNew"))
								componentName = "CC Roads";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("AnganawadiServiceNew/AnganawadiDataNew"))
								componentName = "Anganwadi Buildings";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("GpBuildingServiceNew/GpBuildingDataNew"))
								componentName = "GP Buildings";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("MandalBuildingServiceNew/MandalBuildingDataNew"))
								componentName = "Mandal Buildings";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("HousingServiceNew/HousingDataNew"))
								componentName = "NTR 90 Days";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("BricksServiceNew/BricksDataNew"))
								componentName = "Production of Bricks";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("MulberyServiceNew/MulberyDataNew"))
								componentName = "Mulbery";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("SilkwarmServiceNew/SilkwarmDataNew"))
								componentName = "Silk Worms";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("CattleServiceNew/CattleDataNew"))
								componentName = "Cattle Drinking Water Troughs";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("FodderServiceNew/FodderDataNew"))
								componentName = "Raising of Perinnial Fodders";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("HorticultureServiceNew/HorticultureDataNew"))
								componentName = "Horticulture";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("AvenueServicesNew/AvenueDataNew"))
								componentName = "Avenue";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("FishPondServiceNew/FishPondDataNew"))
								componentName = "Fish Ponds";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("FishDryingServiceNew/FishDryingDataNew"))
								componentName = "Fish Drying Platforms";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("NurseriesServiceNew/NurseriesDataNew"))
								componentName = "Nurseries";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("FaPerformanceServiceNew/FaPerformanceDataNew"))
								componentName = "FAperformance";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("StaggeredService/StaggeredData"))
								componentName = "SMC Trench";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("ImprovmentsService/ImprovmentsData"))
								componentName = "Imp to CD";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("MiniPercolationService/MiniPercolationData"))
								componentName = "MPT_PT";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("GCWorkService/GCWorkData"))
								componentName = "GC Works";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("CheckDamService/CheckDamData"))
								componentName = "CD_CW";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("GreeningHillocksService/GreeningHillocksData"))
								componentName = "GH";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("NTRRuralService/NTRRuralData"))
								componentName = "NTR Rural House";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("PernnialService/PernnialData"))
								componentName = "OPGK-Perinnials";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("AnnualsService/AnnualsData"))
								componentName = "OPGK-Annuals";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("UGDrainageService/UGDrainageData"))
								componentName = "UGDrainage";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("CheckDamServiceNew/CheckDamDataNew"))
								componentName = "Check Dam";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("RockfillDamService/RockfillDamData"))
								componentName = "Rock fill dams";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("CoffeeService/CoffeeData"))
								componentName = "Coffee plantation";
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("ForestService/ForestData")){
								if(output != null && !output.isEmpty()){
									JSONArray finalArray = new JSONArray(output);
									if(finalArray != null && finalArray.length() > 0){
										//for(int i=0;i<finalArray.length();i++){
											JSONObject jObj = (JSONObject) finalArray.get(0);
											if(jObj != null && jObj.has("NEW_CAT_NAME"))
												componentName = jObj.getString("NEW_CAT_NAME");
										//}
									}
								}
							}
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("MCCOthersNewService/MCCOthersAbstract")){
								if(output != null && !output.isEmpty()){
									JSONArray finalArray = new JSONArray(output);
									if(finalArray != null && finalArray.length() > 0){
										//for(int i=0;i<finalArray.length();i++){
											JSONObject jObj = (JSONObject) finalArray.get(0);
											if(jObj != null && jObj.has("GROUP_NAME"))
												componentName = jObj.getString("GROUP_NAME");
										//}
									}
								}
							}
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("AHOthersService/AHOthersData")){
								if(output != null && !output.isEmpty()){
									JSONArray finalArray = new JSONArray(output);
									if(finalArray != null && finalArray.length() > 0){
										//for(int i=0;i<finalArray.length();i++){
											JSONObject jObj = (JSONObject) finalArray.get(0);
											if(jObj != null && jObj.has("CAT_NAME"))
												if(jObj.getString("CAT_NAME") != null && jObj.getString("CAT_NAME").trim().equalsIgnoreCase("Comprehensive Restoration of minor Irrigation Tank"))
													componentName = "Comprehensive Restoration of minor Irrigation Tank1";
												else
													componentName = jObj.getString("CAT_NAME");
										//}
									}
								}
							}
							else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("DCCPRService/DCCPRData")){
								if(output != null && !output.isEmpty()){
									JSONArray finalArray = new JSONArray(output);
									if(finalArray != null && finalArray.length() > 0){
										//for(int i=0;i<finalArray.length();i++){
											JSONObject jObj = (JSONObject) finalArray.get(0);
											if(jObj != null && jObj.has("WORK_TYPE_NAME"))
												componentName = jObj.getString("WORK_TYPE_NAME");
										//}
									}
								}
							}
								
							/*else if(returnUrl != null && returnUrl.toString().trim().equalsIgnoreCase("SilkwarmServiceNew/SilkwarmDataNew"))
								componentName = "Silk Worms";*/
							
							if(inputVO.getSearchType() != null && inputVO.getSearchType().trim().equalsIgnoreCase("grounded")){
								if(output != null && !output.isEmpty()){
									JSONArray finalArray = new JSONArray(output);
									if(finalArray != null && finalArray.length() > 0){
										for(int i=0;i<finalArray.length();i++){
											JSONObject jObj = (JSONObject) finalArray.get(i);
											if(inputVO.getLocationType().trim().equalsIgnoreCase("state") && inputVO.getSubLocationType().trim().equalsIgnoreCase("state")){
												NregaConsolidatedDataVO levelvo = locationMap.get("state");
												if(levelvo == null){
													levelvo = new NregaConsolidatedDataVO();
													levelvo.setSubList(getNregaComponentsList(list));
													levelvo.setName("state");
													NregaConsolidatedDataVO componentvo = getMatchedVOByString(levelvo.getSubList(), componentName);
													if(componentvo == null)
														componentvo = new NregaConsolidatedDataVO();
													componentvo.setName("state");
													componentvo.setComponent(componentName);
													if(componentName != null && componentName.trim().equalsIgnoreCase("Labour Budget")){
														componentvo.setPercentage(jObj.getString("PER_APP_LB"));
													}
													else if(componentName != null && 
															(componentName.trim().equalsIgnoreCase("Horticulture") || componentName.trim().equalsIgnoreCase("Avenue"))){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PITTINGAREA"))*100.00/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance")){
														componentvo.setPercentage(jObj.getString("AVG_TOT_MARKS"));
													}
													else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities")){
														componentvo.setPercentage(jObj.getString("ACHEIVEMENT"));
													}
													else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PITTING_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													else if(componentName != null && (componentName.toString().trim().equalsIgnoreCase("Average Wage")
															|| componentName.toString().trim().equalsIgnoreCase("Average Days of Employment")
															|| componentName.toString().trim().equalsIgnoreCase("HH Completed 100 Days")
															|| componentName.toString().trim().equalsIgnoreCase("Timely Payment")
															|| componentName.toString().trim().equalsIgnoreCase("CC Roads")
															|| componentName.toString().trim().equalsIgnoreCase("UGDrainage")
															|| componentName.toString().trim().equalsIgnoreCase("Nurseries"))){
														componentvo.setPercentage(jObj.getString("PERCENTAGE"));
													}
													else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Coffee plantation")){
														componentvo.setPercentage(jObj.getString("PIT_PERC"));
													}
													else{
														if(jObj.getLong("GROUNDED") > 0 && jObj.getString("TARGET") != null && jObj.getLong("TARGET") > 0)
															componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														else
															componentvo.setPercentage("0.00");
														//componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													
													/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Anganwadi Buildings")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("SMC Trench")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Imp to CD")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("MPT_PT")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GC Works")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("CD_CW")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}*/
													/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}*/
												
													//levelvo.getSubList().add(componentvo);
														
													locationMap.put("state", levelvo);
												}else{
													NregaConsolidatedDataVO componentvo = getMatchedVOByString(levelvo.getSubList(), componentName);
													if(componentvo == null)
														componentvo = new NregaConsolidatedDataVO();
													componentvo.setName("state");
													componentvo.setComponent(componentName);
													if(componentName != null && componentName.trim().equalsIgnoreCase("Labour Budget")){
														componentvo.setPercentage(jObj.getString("PER_APP_LB"));
													}
													else if(componentName != null && 
															(componentName.trim().equalsIgnoreCase("Horticulture") || componentName.trim().equalsIgnoreCase("Avenue"))){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PITTINGAREA"))*100.00/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance")){
														componentvo.setPercentage(jObj.getString("AVG_TOT_MARKS"));
													}
													else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities")){
														componentvo.setPercentage(jObj.getString("ACHEIVEMENT"));
													}
													else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PITTING_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													else if(componentName != null && (componentName.toString().trim().equalsIgnoreCase("Average Wage")
															|| componentName.toString().trim().equalsIgnoreCase("Average Days of Employment")
															|| componentName.toString().trim().equalsIgnoreCase("HH Completed 100 Days")
															|| componentName.toString().trim().equalsIgnoreCase("Timely Payment")
															|| componentName.toString().trim().equalsIgnoreCase("CC Roads")
															|| componentName.toString().trim().equalsIgnoreCase("UGDrainage")
															|| componentName.toString().trim().equalsIgnoreCase("Nurseries"))){
														componentvo.setPercentage(jObj.getString("PERCENTAGE"));
													}
													else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Coffee plantation")){
														componentvo.setPercentage(jObj.getString("PIT_PERC"));
													}
													else{
														if(jObj.getLong("GROUNDED") > 0 && jObj.getString("TARGET") != null && jObj.getLong("TARGET") > 0)
															componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														else
															componentvo.setPercentage("0.00");
														//componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													
													/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Anganwadi Buildings")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("SMC Trench")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Imp to CD")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("MPT_PT")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GC Works")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("CD_CW")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}*/
													//levelvo.getSubList().add(componentvo);
													//}
												}
											}else{
												NregaConsolidatedDataVO levelvo = null;
												if(componentName != null && componentName.trim().equalsIgnoreCase("GH") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district") && jObj.getString("DISTRICT").trim().equalsIgnoreCase("Cuddapah"))
													levelvo = locationMap.get("Kadapa");
												else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
													levelvo = locationMap.get(jObj.getString("DISTRICT_DESCRIPTION").trim());
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
													levelvo = locationMap.get(jObj.getString("DISTRICT").trim());
												else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
													levelvo = locationMap.get(jObj.getString("ASSEMBLY").trim());
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
													levelvo = locationMap.get(jObj.getString("CONSTITUENCY").trim());
												else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities") && inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal") && jObj.getString("MANDAL").trim().equalsIgnoreCase("Rentachintala"))
													levelvo = locationMap.get("Rentacrintala");
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
													levelvo = locationMap.get(jObj.getString("MANDAL").trim());
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
													levelvo = locationMap.get(jObj.getString("PANCHAYAT").trim());
												
												if(levelvo == null){
													levelvo = new NregaConsolidatedDataVO();
													levelvo.setSubList(getNregaComponentsList(list));
													if(componentName != null && componentName.trim().equalsIgnoreCase("GH") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district") && jObj.getString("DISTRICT").trim().equalsIgnoreCase("Cuddapah"))
														levelvo.setDistrict("Kadapa");
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
														levelvo.setDistrict(jObj.getString("DISTRICT_DESCRIPTION").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
														levelvo.setDistrict(jObj.getString("DISTRICT").trim());
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
														levelvo = locationMap.get(jObj.getString("ASSEMBLY").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
														levelvo.setConstituency(jObj.getString("CONSTITUENCY").trim());
													else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities") && inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal") && jObj.getString("MANDAL").trim().equalsIgnoreCase("Rentachintala"))
														levelvo = locationMap.get("Rentacrintala");
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
														levelvo.setMandal(jObj.getString("MANDAL").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
														levelvo.setPanchayat(jObj.getString("PANCHAYAT").trim());
													NregaConsolidatedDataVO componentvo = getMatchedVOByString(levelvo.getSubList(), componentName);
													if(componentvo == null)
														componentvo = new NregaConsolidatedDataVO();	
														
														if(componentName != null && componentName.trim().equalsIgnoreCase("GH") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district") && jObj.getString("DISTRICT").trim().equalsIgnoreCase("Cuddapah"))
															componentvo.setName("Kadapa");
														else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
															componentvo.setName(jObj.getString("DISTRICT_DESCRIPTION").trim());
														else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
															componentvo.setName(jObj.getString("DISTRICT").trim());
														else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
															levelvo = locationMap.get(jObj.getString("ASSEMBLY").trim());
														else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
															componentvo.setName(jObj.getString("CONSTITUENCY").trim());
														else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities") && inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal") && jObj.getString("MANDAL").trim().equalsIgnoreCase("Rentachintala"))
															componentvo.setName("Rentacrintala");
														else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
															componentvo.setName(jObj.getString("MANDAL").trim());
														else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
															componentvo.setName(jObj.getString("PANCHAYAT").trim());
														componentvo.setComponent(componentName);
														if(componentName != null && componentName.trim().equalsIgnoreCase("Labour Budget")){
															componentvo.setPercentage(jObj.getString("PER_APP_LB"));
														}
														/*else if(componentName != null && 
																(componentName.trim().equalsIgnoreCase("Horticulture") || componentName.trim().equalsIgnoreCase("Avenue"))){
															//if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
																componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLANTINGAREA"))*100.00/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
															//else
																//componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLANTINGAREA"))*100.00/Double.valueOf(jObj.getString("SANCTIONEDACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}*/else if(componentName != null && 
																(componentName.trim().equalsIgnoreCase("Horticulture") || componentName.trim().equalsIgnoreCase("Avenue"))){
															if(jObj.getString("PITTINGAREA") != null && Double.valueOf(jObj.getString("PITTINGAREA")) > 0l && jObj.getString("TARGETACRES") != null && Double.valueOf(jObj.getString("TARGETACRES")) > 0l)
																componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PITTINGAREA"))*100.00/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
															else
																componentvo.setPercentage("0.00");
														}
														else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance")){
															componentvo.setPercentage(jObj.getString("AVG_TOT_MARKS"));
														}
														else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities")){
															componentvo.setPercentage(jObj.getString("ACHEIVEMENT"));
														}
														else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
															componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PITTING_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}
														else if(componentName != null && (componentName.toString().trim().equalsIgnoreCase("Average Wage")
																|| componentName.toString().trim().equalsIgnoreCase("Average Days of Employment")
																|| componentName.toString().trim().equalsIgnoreCase("HH Completed 100 Days")
																|| componentName.toString().trim().equalsIgnoreCase("Timely Payment")
																|| componentName.toString().trim().equalsIgnoreCase("CC Roads")
																|| componentName.toString().trim().equalsIgnoreCase("UGDrainage")
																|| componentName.toString().trim().equalsIgnoreCase("Nurseries"))){
															componentvo.setPercentage(jObj.getString("PERCENTAGE"));
														}
														else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Coffee plantation")){
															componentvo.setPercentage(jObj.getString("PIT_PERC"));
														}
														else{
															if(componentName != null && (componentName.trim().equalsIgnoreCase("GP Buildings") || componentName.trim().equalsIgnoreCase("Mandal buildings")
																	|| componentName.trim().equalsIgnoreCase("OPGK-Perinnials") || componentName.trim().equalsIgnoreCase("OPGK-Annuals") || componentName.trim().equalsIgnoreCase("Mulbery"))
																	&& (inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal") || inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))){
																if(jObj.getLong("GROUNDED") > 0 && jObj.getString("TARGETNEW") != null && jObj.getLong("TARGETNEW") > 0)
																	componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("TARGETNEW"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
																else
																	componentvo.setPercentage("0.00");
															}
															/*else if(componentName != null && (componentName.trim().equalsIgnoreCase("OPGK-Perinnials") || componentName.trim().equalsIgnoreCase("OPGK-Annuals") || componentName.trim().equalsIgnoreCase("Mulbery"))
																	&& (inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal") || inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))){
																if(jObj.getLong("GROUNDED") > 0 && jObj.getString("TARGETNEW") != null && jObj.getLong("TARGET") > 0)
																	componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
																else
																	componentvo.setPercentage("0.00");
															}*/
															else {
																if(jObj.getLong("GROUNDED") > 0 && jObj.getString("TARGET") != null && jObj.getLong("TARGET") > 0)
																	componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
																else
																	componentvo.setPercentage("0.00");
															}
																
														}
														
														/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Anganwadi Buildings") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
															componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}
														if(componentName != null && componentName.toString().trim().equalsIgnoreCase("SMC Trench") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
															componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}
														if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Imp to CD") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
															componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}
														if(componentName != null && componentName.toString().trim().equalsIgnoreCase("MPT_PT") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
															componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}
														if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GC Works") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
															componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}
														if(componentName != null && componentName.toString().trim().equalsIgnoreCase("CD_CW") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
															componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}*/
														/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
															componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}*/
														//levelvo.getSubList().add(componentvo);
														
													if(componentName != null && componentName.trim().equalsIgnoreCase("GH") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district") && jObj.getString("DISTRICT").trim().equalsIgnoreCase("Cuddapah"))
														locationMap.put("Kadapa", levelvo);
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
														locationMap.put(jObj.getString("DISTRICT_DESCRIPTION").trim(), levelvo);
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
														locationMap.put(jObj.getString("DISTRICT").trim(), levelvo);
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
														levelvo = locationMap.get(jObj.getString("ASSEMBLY").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
														locationMap.put(jObj.getString("CONSTITUENCY").trim(), levelvo);
													else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities") && inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal") && jObj.getString("MANDAL").trim().equalsIgnoreCase("Rentachintala"))
														locationMap.put("Rentacrintala", levelvo);
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
														locationMap.put(jObj.getString("MANDAL").trim(), levelvo);
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
														locationMap.put(jObj.getString("PANCHAYAT").trim(), levelvo);
												}else{
													NregaConsolidatedDataVO componentvo = getMatchedVOByString(levelvo.getSubList(), componentName);
													if(componentvo == null)
														componentvo = new NregaConsolidatedDataVO();
													if(componentName != null && componentName.trim().equalsIgnoreCase("GH") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district") && jObj.getString("DISTRICT").trim().equalsIgnoreCase("Cuddapah"))
														componentvo.setName("Kadapa");
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
														componentvo.setName(jObj.getString("DISTRICT_DESCRIPTION").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
														componentvo.setName(jObj.getString("DISTRICT").trim());
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
														levelvo = locationMap.get(jObj.getString("ASSEMBLY").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
														componentvo.setName(jObj.getString("CONSTITUENCY").trim());
													else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities") && inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal") && jObj.getString("MANDAL").trim().equalsIgnoreCase("Rentachintala"))
														componentvo.setName("Rentacrintala");
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
														componentvo.setName(jObj.getString("MANDAL").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
														componentvo.setName(jObj.getString("PANCHAYAT").trim());
													componentvo.setComponent(componentName);
													if(componentName != null && componentName.trim().equalsIgnoreCase("Labour Budget")){
														componentvo.setPercentage(jObj.getString("PER_APP_LB"));
													}
													/*else if(componentName != null && 
															(componentName.trim().equalsIgnoreCase("Horticulture") || componentName.trim().equalsIgnoreCase("Avenue"))){
														componentvo.setPercentage(jObj.getString("PERCENTAGEOFPLANTING"));
													}*/else if(componentName != null && 
															(componentName.trim().equalsIgnoreCase("Horticulture") || componentName.trim().equalsIgnoreCase("Avenue"))){
														if(jObj.getString("PITTINGAREA") != null && Double.valueOf(jObj.getString("PITTINGAREA")) > 0l && jObj.getString("TARGETACRES") != null && Double.valueOf(jObj.getString("TARGETACRES")) > 0l)
															componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PITTINGAREA"))*100.00/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														else
															componentvo.setPercentage("0.00");
															
													}
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance")){
														componentvo.setPercentage(jObj.getString("AVG_TOT_MARKS"));
													}
													else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities")){
														componentvo.setPercentage(jObj.getString("ACHEIVEMENT"));
													}
													else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PITTING_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													else if(componentName != null && (componentName.toString().trim().equalsIgnoreCase("Average Wage")
															|| componentName.toString().trim().equalsIgnoreCase("Average Days of Employment")
															|| componentName.toString().trim().equalsIgnoreCase("HH Completed 100 Days")
															|| componentName.toString().trim().equalsIgnoreCase("Timely Payment")
															|| componentName.toString().trim().equalsIgnoreCase("CC Roads")
															|| componentName.toString().trim().equalsIgnoreCase("UGDrainage")
															|| componentName.toString().trim().equalsIgnoreCase("Nurseries"))){
														componentvo.setPercentage(jObj.getString("PERCENTAGE"));
													}
													else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Coffee plantation")){
														componentvo.setPercentage(jObj.getString("PIT_PERC"));
													}
													else{
														if(componentName != null && (componentName.trim().equalsIgnoreCase("GP Buildings") || componentName.trim().equalsIgnoreCase("Mandal buildings")
																|| componentName.trim().equalsIgnoreCase("OPGK-Perinnials") || componentName.trim().equalsIgnoreCase("OPGK-Annuals") || componentName.trim().equalsIgnoreCase("Mulbery"))
																&& (inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal") || inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))){
															if(jObj.getLong("GROUNDED") > 0 && jObj.getString("TARGETNEW") != null && jObj.getLong("TARGETNEW") > 0)
																componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("TARGETNEW"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
															else
																componentvo.setPercentage("0.00");
														}else {
															if(jObj.getLong("GROUNDED") > 0 && jObj.getString("TARGET") != null && jObj.getLong("TARGET") > 0)
																componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
															else
																componentvo.setPercentage("0.00");
														}
														
													}
													
													/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Anganwadi Buildings") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("SMC Trench") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Imp to CD") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("MPT_PT") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GC Works") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
														if(Double.valueOf(jObj.getString("SANCTIONEDTARGET")) != null && Double.valueOf(jObj.getString("SANCTIONEDTARGET")) > 0)
															componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("CD_CW") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("GROUNDED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}*/
													/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}*/
													//levelvo.getSubList().add(componentvo);
													//}
												}
											}
										}
									}
								}
							}
							else{
								if(output != null && !output.isEmpty()){
									JSONArray finalArray = new JSONArray(output);
									if(finalArray != null && finalArray.length() > 0){
										for(int i=0;i<finalArray.length();i++){
											JSONObject jObj = (JSONObject) finalArray.get(i);
											if(inputVO.getLocationType().trim().equalsIgnoreCase("state") && inputVO.getSubLocationType().trim().equalsIgnoreCase("state")){
												NregaConsolidatedDataVO levelvo = locationMap.get("state");
												if(levelvo == null){
													levelvo = new NregaConsolidatedDataVO();
													levelvo.setSubList(getNregaComponentsList(list));
													levelvo.setName("state");
													NregaConsolidatedDataVO componentvo = getMatchedVOByString(levelvo.getSubList(), componentName);
													if(componentvo == null)
														componentvo = new NregaConsolidatedDataVO();
													componentvo.setName("state");
													componentvo.setComponent(componentName);
													if(componentName != null && componentName.trim().equalsIgnoreCase("Labour Budget")){
														componentvo.setPercentage(jObj.getString("PER_APP_LB"));
													}
													else if(componentName != null && 
															(componentName.trim().equalsIgnoreCase("Horticulture") || componentName.trim().equalsIgnoreCase("Avenue"))){
														//componentvo.setPercentage(jObj.getString("PERCENTAGEOFPLANTING"));
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLANTINGAREA"))*100.00/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance")){
														componentvo.setPercentage(jObj.getString("AVG_TOT_MARKS"));
													}
													else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities")){
														componentvo.setPercentage(jObj.getString("ACHEIVEMENT"));
													}
													else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Coffee plantation")){
														componentvo.setPercentage(jObj.getString("PLANT_PERC"));
													}
													else{
														if(jObj.has("PERC"))
															componentvo.setPercentage(jObj.has("PERC") ? jObj.getString("PERC") : "0.00");
														else
															componentvo.setPercentage(jObj.has("PERCENTAGE") ? jObj.getString("PERCENTAGE") : "0.00");
													}
													
													/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Anganwadi Buildings")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("SMC Trench")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Imp to CD")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("MPT_PT")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GC Works")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("CD_CW")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}*/
													/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}*/
													
													//levelvo.getSubList().add(componentvo);
													locationMap.put("state", levelvo);
												}else{
													NregaConsolidatedDataVO componentvo = getMatchedVOByString(levelvo.getSubList(), componentName);
													if(componentvo == null)
														componentvo = new NregaConsolidatedDataVO();
													componentvo.setName("state");
													componentvo.setComponent(componentName);
													if(componentName != null && componentName.trim().equalsIgnoreCase("Labour Budget")){
														componentvo.setPercentage(jObj.getString("PER_APP_LB"));
													}
													else if(componentName != null && 
															(componentName.trim().equalsIgnoreCase("Horticulture") || componentName.trim().equalsIgnoreCase("Avenue"))){
														//componentvo.setPercentage(jObj.getString("PERCENTAGEOFPLANTING"));
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLANTINGAREA"))*100.00/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance")){
														componentvo.setPercentage(jObj.getString("AVG_TOT_MARKS"));
													}
													else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities")){
														componentvo.setPercentage(jObj.getString("ACHEIVEMENT"));
													}
													else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Coffee plantation")){
														componentvo.setPercentage(jObj.getString("PLANT_PERC"));
													}
													else{
														if(jObj.has("PERC"))
															componentvo.setPercentage(jObj.has("PERC") ? jObj.getString("PERC") : "0.00");
														else
															componentvo.setPercentage(jObj.has("PERCENTAGE") ? jObj.getString("PERCENTAGE") : "0.00");
													}
													
													/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Anganwadi Buildings")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("SMC Trench")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Imp to CD")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("MPT_PT")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GC Works")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("CD_CW")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}*/
													/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}*/
													//levelvo.getSubList().add(componentvo);
													//}
												}
											}else{
												NregaConsolidatedDataVO levelvo = null;
												if(componentName != null && componentName.trim().equalsIgnoreCase("GH") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district") && jObj.getString("DISTRICT").trim().equalsIgnoreCase("Cuddapah"))
													levelvo = locationMap.get("Kadapa");
												else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
													levelvo = locationMap.get(jObj.getString("DISTRICT_DESCRIPTION").trim());
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
													levelvo = locationMap.get(jObj.getString("DISTRICT").trim());
												else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
													levelvo = locationMap.get(jObj.getString("ASSEMBLY").trim());
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
													levelvo = locationMap.get(jObj.getString("CONSTITUENCY").trim());
												else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities") && inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal") && jObj.getString("MANDAL").trim().equalsIgnoreCase("Rentachintala"))
													levelvo = locationMap.get("Rentacrintala");
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
													levelvo = locationMap.get(jObj.getString("MANDAL").trim());
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
													levelvo = locationMap.get(jObj.getString("PANCHAYAT").trim());
												
												if(levelvo == null){
													levelvo = new NregaConsolidatedDataVO();
													levelvo.setSubList(getNregaComponentsList(list));
													if(componentName != null && componentName.trim().equalsIgnoreCase("GH") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district") && jObj.getString("DISTRICT").trim().equalsIgnoreCase("Cuddapah"))
														levelvo.setDistrict("Kadapa");
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
														levelvo.setDistrict(jObj.getString("DISTRICT_DESCRIPTION").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
														levelvo.setDistrict(jObj.getString("DISTRICT").trim());
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
														levelvo = locationMap.get(jObj.getString("ASSEMBLY").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
														levelvo.setConstituency(jObj.getString("CONSTITUENCY").trim());
													else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities") && inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal") && jObj.getString("MANDAL").trim().equalsIgnoreCase("Rentachintala"))
														levelvo = locationMap.get("Rentacrintala");
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
														levelvo.setMandal(jObj.getString("MANDAL").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
														levelvo.setPanchayat(jObj.getString("PANCHAYAT").trim());
													NregaConsolidatedDataVO componentvo = getMatchedVOByString(levelvo.getSubList(), componentName);
													if(componentvo == null)
														componentvo = new NregaConsolidatedDataVO();	
														
														if(componentName != null && componentName.trim().equalsIgnoreCase("GH") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district") && jObj.getString("DISTRICT").trim().equalsIgnoreCase("Cuddapah"))
															componentvo.setName("Kadapa");
														else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
															componentvo.setName(jObj.getString("DISTRICT_DESCRIPTION").trim());
														else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
															componentvo.setName(jObj.getString("DISTRICT").trim());
														else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
															componentvo.setName(jObj.getString("ASSEMBLY").trim());
														else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
															componentvo.setName(jObj.getString("CONSTITUENCY").trim());
														else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities") && inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal") && jObj.getString("MANDAL").trim().equalsIgnoreCase("Rentachintala"))
															componentvo.setName("Rentacrintala");
														else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
															componentvo.setName(jObj.getString("MANDAL").trim());
														else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
															componentvo.setName(jObj.getString("PANCHAYAT").trim());
														componentvo.setComponent(componentName);
														if(componentName != null && componentName.trim().equalsIgnoreCase("Labour Budget")){
															componentvo.setPercentage(jObj.getString("PER_APP_LB"));
														}
														else if(componentName != null && 
																(componentName.trim().equalsIgnoreCase("Horticulture") || componentName.trim().equalsIgnoreCase("Avenue"))){
															//componentvo.setPercentage(jObj.getString("PERCENTAGEOFPLANTING"));
															if(jObj.getString("PLANTINGAREA") != null && Double.valueOf(jObj.getString("PLANTINGAREA")) > 0l && jObj.getString("TARGETACRES") != null && Double.valueOf(jObj.getString("TARGETACRES")) > 0l)
																componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLANTINGAREA"))*100.00/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
															else
																componentvo.setPercentage("0.00");
														}
														else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance")){
															componentvo.setPercentage(jObj.getString("AVG_TOT_MARKS"));
														}
														else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities")){
															componentvo.setPercentage(jObj.getString("ACHEIVEMENT"));
														}
														else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
															componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}
														else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Coffee plantation")){
															componentvo.setPercentage(jObj.getString("PLANT_PERC"));
														}
														else{
															if(jObj.has("PERC"))
																componentvo.setPercentage(jObj.has("PERC") ? jObj.getString("PERC") : "0.00");
															else
																componentvo.setPercentage(jObj.has("PERCENTAGE") ? jObj.getString("PERCENTAGE") : "0.00");
														}
														
														/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Anganwadi Buildings") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
															componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}
														if(componentName != null && componentName.toString().trim().equalsIgnoreCase("SMC Trench") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
															componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}
														if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Imp to CD") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
															componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}
														if(componentName != null && componentName.toString().trim().equalsIgnoreCase("MPT_PT") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
															componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}
														if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GC Works") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
															componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}
														if(componentName != null && componentName.toString().trim().equalsIgnoreCase("CD_CW") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
															componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}*/
														/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
															componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														}*/
														//levelvo.getSubList().add(componentvo);
														
														
													if(componentName != null && componentName.trim().equalsIgnoreCase("GH") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district") && jObj.getString("DISTRICT").trim().equalsIgnoreCase("Cuddapah"))
														locationMap.put("Kadapa", levelvo);
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
														locationMap.put(jObj.getString("DISTRICT_DESCRIPTION").trim(), levelvo);
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
														locationMap.put(jObj.getString("DISTRICT").trim(), levelvo);
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
														levelvo = locationMap.get(jObj.getString("ASSEMBLY").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
														locationMap.put(jObj.getString("CONSTITUENCY").trim(), levelvo);
													else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities") && inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal") && jObj.getString("MANDAL").trim().equalsIgnoreCase("Rentachintala"))
														locationMap.put("Rentacrintala",levelvo);
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
														locationMap.put(jObj.getString("MANDAL").trim(), levelvo);
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
														locationMap.put(jObj.getString("PANCHAYAT").trim(), levelvo);
												}else{
													NregaConsolidatedDataVO componentvo = getMatchedVOByString(levelvo.getSubList(), componentName);
													if(componentvo == null)
														componentvo = new NregaConsolidatedDataVO();
													if(componentName != null && componentName.trim().equalsIgnoreCase("GH") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district") && jObj.getString("DISTRICT").trim().equalsIgnoreCase("Cuddapah"))
														componentvo.setName("Kadapa");
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
														componentvo.setName(jObj.getString("DISTRICT_DESCRIPTION").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
														componentvo.setName(jObj.getString("DISTRICT").trim());
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance") && inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
														levelvo = locationMap.get(jObj.getString("ASSEMBLY").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
														componentvo.setName(jObj.getString("CONSTITUENCY").trim());
													else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities") && inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal") && jObj.getString("MANDAL").trim().equalsIgnoreCase("Rentachintala"))
														componentvo.setName("Rentacrintala");
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
														componentvo.setName(jObj.getString("MANDAL").trim());
													else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
														componentvo.setName(jObj.getString("PANCHAYAT").trim());
													componentvo.setComponent(componentName);
													if(componentName != null && componentName.trim().equalsIgnoreCase("Labour Budget")){
														componentvo.setPercentage(jObj.getString("PER_APP_LB"));
													}
													else if(componentName != null && 
															(componentName.trim().equalsIgnoreCase("Horticulture") || componentName.trim().equalsIgnoreCase("Avenue"))){
														//componentvo.setPercentage(jObj.getString("PERCENTAGEOFPLANTING"));
														if(jObj.getString("PLANTINGAREA") != null && Double.valueOf(jObj.getString("PLANTINGAREA")) > 0l && jObj.getString("TARGETACRES") != null && Double.valueOf(jObj.getString("TARGETACRES")) > 0l)
															componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLANTINGAREA"))*100.00/Double.valueOf(jObj.getString("TARGETACRES"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
														else
															componentvo.setPercentage("0.00");
													}
													else if(componentName != null && componentName.trim().equalsIgnoreCase("FAperformance")){
														componentvo.setPercentage(jObj.getString("AVG_TOT_MARKS"));
													}
													else if(componentName != null && componentName.trim().equalsIgnoreCase("Agriculture Activities")){
														componentvo.setPercentage(jObj.getString("ACHEIVEMENT"));
													}
													else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													else if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Coffee plantation")){
														componentvo.setPercentage(jObj.getString("PLANT_PERC"));
													}
													else{
														if(jObj.has("PERC"))
															componentvo.setPercentage(jObj.has("PERC") ? jObj.getString("PERC") : "0.00");
														else
															componentvo.setPercentage(jObj.has("PERCENTAGE") ? jObj.getString("PERCENTAGE") : "0.00");
													}
													
													/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Anganwadi Buildings") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("SMC Trench") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("Imp to CD") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("MPT_PT") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GC Works") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
														if(Double.valueOf(jObj.getString("SANCTIONEDTARGET")) != null && Double.valueOf(jObj.getString("SANCTIONEDTARGET")) > 0)
															componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}
													if(componentName != null && componentName.toString().trim().equalsIgnoreCase("CD_CW") && inputVO.getSubLocationType().trim().equalsIgnoreCase("district")){
														componentvo.setPercentage(new BigDecimal(jObj.getLong("COMPLETED")*100.00/Double.valueOf(jObj.getString("SANCTIONEDTARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}*/
													/*if(componentName != null && componentName.toString().trim().equalsIgnoreCase("GH")){
														componentvo.setPercentage(new BigDecimal(Double.valueOf(jObj.getString("PLNTNG_EXT"))*100.00/Double.valueOf(jObj.getString("SANCTION_TARGET"))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
													}*/
													//levelvo.getSubList().add(componentvo);
													//}
													
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
				if(locationMap != null)
					returnList = new ArrayList<NregaConsolidatedDataVO>(locationMap.values());
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSLevelWiseConsolidatedReport - NREGSConsolidatedService Service", e);
		}
		return returnList;
	}
	
	/*
	 * Date : 21/07/2017
	 * Author :Sravanth
	 * @description : getMatchedVOByString
	 * 
	 */
	public NregaConsolidatedDataVO getMatchedVOByString(List<NregaConsolidatedDataVO> list,String component){
		try{
			if(list != null && !list.isEmpty()){
				for (NregaConsolidatedDataVO nregaConsolidatedDataVO : list) {
					if(nregaConsolidatedDataVO.getComponent().trim().equalsIgnoreCase(component)){
						return nregaConsolidatedDataVO;
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getMatchedVOByString - NREGSConsolidatedService service", e);
		}
		return null;
	}
	
	/*
	 * Date : 21/07/2017
	 * Author :Sravanth
	 * @description : convertingInputVOToString
	 * 
	 */
	public String convertingInputVOToString(NregaConsolidatedInputVO inputVO){
		String str = "";
		try {
			/*if(inputVO.getLocationId() != null)
				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
					if(inputVO.getLocationId().longValue() > 0l && inputVO.getLocationId().longValue() <= 9l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
					if(inputVO.getLocationId().longValue() > 0l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}*/
				
			str = "{";
			
			if(inputVO.getFromDate() != null )
				str += "\"fromDate\" : \""+inputVO.getFromDate()+"\",";
			if(inputVO.getToDate() != null)
				str += "\"toDate\" : \""+inputVO.getToDate()+"\",";
			if(inputVO.getYear() != null)
				str += "\"year\" : \""+inputVO.getYear()+"\",";
			if(inputVO.getLocationType() != null)
				str += "\"locationType\" : \""+inputVO.getLocationType()+"\",";
			if(inputVO.getLocationIdStr() != null)
				str += "\"locationId\" : \""+inputVO.getLocationIdStr()+"\",";
			if(inputVO.getSubLocationType() != null)
				str += "\"SublocationType\" : \""+inputVO.getSubLocationType()+"\",";
			if(inputVO.getComponentName() != null)
				str += "\"type\" : \""+inputVO.getComponentName()+"\",";
			if(inputVO.getProgram() != null)
				str += "\"program\" : \""+inputVO.getProgram()+"\",";
			if(inputVO.getCategoryName() != null)
				str += "\"categoryName\" : \""+inputVO.getCategoryName()+"\",";
			if(inputVO.getGroupName() != null)
				str += "\"groupName\" : \""+inputVO.getGroupName()+"\",";
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception raised at convertingInputVOToString - NREGSConsolidatedService service", e);
		}
		return str;
	}
	
	/*
	 * Date : 09/08/2017
	 * Author :Sravanth
	 * @description : convertingInputVOToStringForFA
	 * 
	 */
	public String convertingInputVOToStringForFA(NregaConsolidatedInputVO inputVO){
		String str = "";
		try {
			/*if(inputVO.getLocationId() != null)
				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
					if(inputVO.getLocationId().longValue() > 0l && inputVO.getLocationId().longValue() <= 9l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
					if(inputVO.getLocationId().longValue() > 0l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}*/
				
			str = "{";
			
			if(inputVO.getFromDate() != null )
				str += "\"fromDate\" : \""+inputVO.getFromDate()+"\",";
			if(inputVO.getToDate() != null)
				str += "\"toDate\" : \"2017-05-30\",";
			if(inputVO.getYear() != null)
				str += "\"year\" : \""+inputVO.getYear()+"\",";
			if(inputVO.getLocationType() != null)
				str += "\"locationType\" : \""+inputVO.getLocationType()+"\",";
			if(inputVO.getLocationIdStr() != null)
				str += "\"locationId\" : \""+inputVO.getLocationIdStr()+"\",";
			if(inputVO.getSubLocationType() != null)
				str += "\"SublocationType\" : \""+inputVO.getSubLocationType()+"\",";
			if(inputVO.getComponentName() != null)
				str += "\"type\" : \""+inputVO.getComponentName()+"\",";
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception raised at convertingInputVOToStringForFA - NREGSConsolidatedService service", e);
		}
		return str;
	}
	
	/*
	 * Date : 21/7/17
	 * Author : Nandhini.k
	 * @description : getting All Convergence Types
	 */
	public List<IdNameVO> getAllConvergenceTypes(){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
		try{
			List<Object[]> convergenceList = convergenceTypeDAO.getAllConvergenceTypes();
			if(commonMethodsUtilService.isListOrSetValid(convergenceList)){
				for (Object[] objects : convergenceList) {
					IdNameVO vo = new IdNameVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					finalList.add(vo);
				}
			}
		}catch (Exception e){
			LOG.error("Exception raised at getAllConvergenceTypes - NREGSConsolidatedService", e);
		}
		return finalList;
	}
	
	/*
	 * Date : 21/7/17
	 * Author : Nandhini.k
	 * @description : getting Component(IHHL OR Labor Budget ETC) By Passing ConvergenceTypeId 
	 */
	public List<IdNameVO> getComponentByConvergType(NregaConsolidatedInputVO nregaConsolidatedInputVO){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
		try{
			List<Object[]> componentList = nregaComponentDAO.getComponentByConvergType(nregaConsolidatedInputVO.getConvergenceTypeId());
			if(commonMethodsUtilService.isListOrSetValid(componentList)){
				for (Object[] objects : componentList) {
					IdNameVO vo = new IdNameVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					finalList.add(vo);
				}
			}
		}catch (Exception e){
			LOG.error("Exception raised at getComponentByConvergType - NREGSConsolidatedService", e);
		}
		return finalList;
	}
	
	/*
	 * Date : 21/07/2017
	 * Author :Nandhini.k
	 * @description : getNREGSConsolidatedAbstrct
	 * 
	 */
	public List<NregsProjectsVO> getNREGSConsolidatedAbstrct(NregaConsolidatedInputVO inputVO){
		List<NregsProjectsVO> returnList = new ArrayList<NregsProjectsVO>();
		try {
			List<NregsProjectsVO> voList = null;
			List<NregaConsolidatedInputVO> urlsList = new ArrayList<NregaConsolidatedInputVO>(0);
			if(inputVO != null && inputVO.getComponentIds() != null && !inputVO.getComponentIds().isEmpty()){
				List<Object[]> list = nregaComponentServiceDAO.getComponentUrlsByComponentIds(inputVO.getComponentIds(), "ABSTRACT");
				if(list != null && !list.isEmpty()){
					for (Object[] obj : list) {
						NregaConsolidatedInputVO vo = new NregaConsolidatedInputVO();
						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setUrl(obj[2] != null ? obj[2].toString():"");
						vo.setComponentName(obj[1] != null ? obj[1].toString():"");
						urlsList.add(vo);
					}
				}
			}
			
			String projectType = null;
			//String str = convertingInputVOToString(inputVO);
			if(urlsList != null && !urlsList.isEmpty()){
				for (NregaConsolidatedInputVO urlvo : urlsList) {
					NregsProjectsVO finalVO =  new NregsProjectsVO();
					inputVO.setComponentName(urlvo.getComponentName());
					String str = convertingInputVOToString(inputVO);
					ClientResponse response = webServiceUtilService.callWebService(urlvo.getUrl().toString(), str);
					if(response.getStatus() != 200){
						throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
					}else{
						if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase("Burial Grounds"))
			 	    		  inputVO.setComponentName("Burial Ground");
			 	    	  else if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase("Anganwadi Buildings"))
			 	    		  inputVO.setComponentName("Anganwadi");
			 	    	  else if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase("GP Buildings"))
			 	    		  inputVO.setComponentName("Gram Panchayat Buildings");
			 	    	 else if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase("Silk Worms"))
			 	    		  inputVO.setComponentName("Silk worm");
			 	    	 else if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase("Cattle Drinking Water Troughs"))
			 	    		  inputVO.setComponentName("Cattle drinking water trough");
			 	    	 else if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase("Raising of Perinnial Fodders"))
				 	    	  inputVO.setComponentName("Raising of Perinnial Fodder");
						
						String output = response.getEntity(String.class);
						if(output != null && !output.isEmpty()){
							JSONArray finalArray = new JSONArray(output);
							if(finalArray != null && finalArray.length() > 0){
								for(int i=0;i<finalArray.length();i++){
									if(inputVO.getComponentName().trim().equalsIgnoreCase("Labour Budget") || inputVO.getComponentName().trim().equalsIgnoreCase("Farm Ponds") ||
									   inputVO.getComponentName().trim().equalsIgnoreCase("IHHL") || inputVO.getComponentName().trim().equalsIgnoreCase("Vermi Compost") || 
									   inputVO.getComponentName().trim().equalsIgnoreCase("Agriculture Activities") || inputVO.getComponentName().trim().equalsIgnoreCase("Average Wage") ||
									   inputVO.getComponentName().trim().equalsIgnoreCase("Average Days of Employment") || inputVO.getComponentName().trim().equalsIgnoreCase("HH Completed 100 Days") || 
									   inputVO.getComponentName().trim().equalsIgnoreCase("Timely Payment") || inputVO.getComponentName().trim().equalsIgnoreCase("Horticulture") ||
									   inputVO.getComponentName().trim().equalsIgnoreCase("Avenue") || inputVO.getComponentName().trim().equalsIgnoreCase("Nurseries") ||
									   inputVO.getComponentName().trim().equalsIgnoreCase("SMC Trench") || inputVO.getComponentName().trim().equalsIgnoreCase("Imp to CD") ||
									   inputVO.getComponentName().trim().equalsIgnoreCase("MPT_PT") || inputVO.getComponentName().trim().equalsIgnoreCase("GC Works") ||
									   inputVO.getComponentName().trim().equalsIgnoreCase("CD_CW")){
										
										NregsProjectsVO vo = new NregsProjectsVO();
				 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
				 	    				
				 	    				if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Nurseries") && (inputVO.getLocationType().toString().trim().equalsIgnoreCase("state") || inputVO.getLocationType().toString().trim().equalsIgnoreCase("constituency")))
				 	    					vo.setParameter(jObj.getString("'NURSERIES'"));
				 	    				else
				 	    					vo.setParameter(jObj.getString("PARAMETER"));
				 	    				
				 	    				if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Average Wage")){
				 	    					vo.setTarget(jObj.getString("MAX(T.AVG_WAGE_TARGET)"));
					 	    				vo.setCompleted(jObj.getString("AVG_WAGE"));
				 	    				}
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Average Days of Employment")){
				 	    					vo.setTarget(jObj.getString("MAX(T.AVG_DAYS_TARGET)"));
					 	    				vo.setCompleted(jObj.getString("AVG_DAYS"));
				 	    				}
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("HH Completed 100 Days")){
				 	    					vo.setTarget(jObj.getString("ROUND(NVL(SUM(HH_WORKING)*0.2,0),0)"));
					 	    				vo.setCompleted(jObj.getString("COMP_100"));
				 	    				}
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Timely Payment")){
				 	    					vo.setTarget(jObj.getString("MAX(T.UPLOAD_5_TARGET)"));
					 	    				vo.setCompleted(jObj.getString("UPLOAD_5"));
				 	    				}
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Horticulture") || inputVO.getComponentName().toString().trim().equalsIgnoreCase("Avenue")){
				 	    					vo.setTarget(jObj.getString("TARGET"));
					 	    				vo.setCompleted(jObj.getString("ACHIVEMENT"));
				 	    				}
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Payments")){
				 	    					vo.setFTONOTGENCNT(jObj.getString("FTONOTGENCNT"));
				 	    					vo.setFTONOTUPLOADCNT(jObj.getString("FTONOTUPLOADCNT"));
				 	    					vo.setFTONOTSENTCNT(jObj.getString("FTONOTSENTCNT"));
				 	    					vo.setREJECTCNT(jObj.getString("REJECTCNT"));
				 	    				}
				 	    				else{
				 	    					vo.setTarget(jObj.getString("TARGET"));
					 	    				vo.setCompleted(jObj.getString("COMPLETED"));
				 	    				}
				 	    				
				 	    				if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("IHHL") || inputVO.getComponentName().toString().trim().equalsIgnoreCase("Vermi Compost")
				 	    						|| inputVO.getComponentName().toString().trim().equalsIgnoreCase("Agriculture Activities")
				 	    						|| inputVO.getComponentName().toString().trim().equalsIgnoreCase("Farm Ponds"))
				 	    					vo.setPercentage(jObj.getString("PERC"));
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Average Wage")
				 	    						|| inputVO.getComponentName().toString().trim().equalsIgnoreCase("Average Days of Employment")
				 	    						|| inputVO.getComponentName().toString().trim().equalsIgnoreCase("HH Completed 100 Days")
				 	    						|| inputVO.getComponentName().toString().trim().equalsIgnoreCase("Timely Payment")
				 	    						|| inputVO.getComponentName().toString().trim().equalsIgnoreCase("Horticulture") || inputVO.getComponentName().toString().trim().equalsIgnoreCase("Avenue"))
				 	    					vo.setPercentage(jObj.getString("PER"));
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Payments"))
				 	    					vo.setPENDINGRESPONSECNT(jObj.getString("PENDINGRESPONSECNT"));
				 	    				else
				 	    					vo.setPercentage(jObj.getString("PERCENTAGE"));
				 	    				
				 	    				if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
				 	    					vo.setType("STATE");
				 	    				else if(vo.getParameter().contains("state") || vo.getParameter().contains("State") || vo.getParameter().contains("STATE"))
				 	    					vo.setType("STATE");
				 	    				else if(vo.getParameter().contains("district") || vo.getParameter().contains("District") || vo.getParameter().contains("DISTRICT"))
				 	    					vo.setType("DISTRICT");
				 	    				else if(inputVO.getLocationType().trim().equalsIgnoreCase("constituency"))
				 	    					vo.setType("CONSTITUENCY");
				 	    				finalVO.getSubList().add(vo);
				 	    				
									}else{
										NregsProjectsVO vo = new NregsProjectsVO();
				 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
				 	    				if(inputVO.getLocationType().trim().equalsIgnoreCase("district"))
				 	    					projectType = jObj.getString("CAT_NAME||'_DISTRICT'");
				 	    				else if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
				 	    					projectType = jObj.getString("CAT_NAME");
				 	    				
				 	    				String[]  projectTypeArr = projectType.split("_");
				 	    				if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase(projectTypeArr[0])){
				 	    					if(inputVO.getLocationType().trim().equalsIgnoreCase("district"))
				 	    						vo.setParameter(jObj.getString("CAT_NAME||'_DISTRICT'"));
				 	    					else if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
				 	    						vo.setParameter(jObj.getString("CAT_NAME"));
				 	    					else
				 	    						vo.setParameter(jObj.getString("CAT_NAME"));
				 	    					
				 	    					if(inputVO.getLocationType().trim().equalsIgnoreCase("district"))
				 	    						vo.setType(projectTypeArr[1]);
				 	    					else if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
				 	    						vo.setType("STATE");
				 	    					else
				 	    						vo.setType("CONSTITUENCY");
				 	    					
				 	    					vo.setTarget(jObj.getString("TARGET"));
					 	    				vo.setCompleted(jObj.getString("COMPLETED"));
					 	    				vo.setPercentage(jObj.getString("PERC"));
					 	    				finalVO.getSubList().add(vo);
				 	    				}
									}
								}
							}
						}
		 	    	}
					finalVO.setComponentType(inputVO.getComponentName());
					returnList.add(finalVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSLevelWiseConsolidatedReport - NREGSConsolidatedService Service", e);
		}
		return returnList;
	}
}
