package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IZebraPrintDetailsDAO;
import com.itgrids.partyanalyst.model.ZebraPrintDetails;

public class ZebraPrintDetailsDAOHibernateTest extends BaseDaoTestCase{

	private IZebraPrintDetailsDAO zebraPrintDetailsDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public IZebraPrintDetailsDAO getZebraPrintDetailsDAO() {
		return zebraPrintDetailsDAO;
	}
	public void setZebraPrintDetailsDAO(IZebraPrintDetailsDAO zebraPrintDetailsDAO) {
		this.zebraPrintDetailsDAO = zebraPrintDetailsDAO;
	}

	/*public void testgetMemberShipCardPrintStatusByDate()
	{
		ZebraPrintDetailsVO returnVO = new ZebraPrintDetailsVO();
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String searchType = IConstants.CONSTITUENCY;
			List<Long> selectedLocationIds = new ArrayList<Long>();
			selectedLocationIds.add(1L);
			selectedLocationIds.add(2L);
			selectedLocationIds.add(3L);
			selectedLocationIds.add(4L);
			selectedLocationIds.add(5L);
			
			if(searchType != null && (selectedLocationIds != null && selectedLocationIds.size()>0))
			{
				
				Date fromDate = format.parse("20-11-2014");
				Date toDate = format.parse("22-11-2014");
				StringBuilder queryStr = new StringBuilder();
				List<Object[]> printStatusDetailsList = null;
				List<ZebraPrintDetailsVO> printStatusList = null;
					
					Map<Long,String> parliamentForAssemblyMap = new TreeMap<Long, String>();
					List<Object[]> locationsList =  delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(2L);							
						
					List<Long> locationIdList = new ArrayList<Long>(0);
					List<Long> assemblyIds = new ArrayList<Long>(0);
					
					if(locationsList != null && locationsList.size()>0)
					{
						for (Object[] param : locationsList)
						{
							locationIdList.add(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
						}
					}
							
					if(locationIdList != null && locationIdList.size()>0)
					{
							locationsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(locationIdList);
													
							if(locationsList != null && locationsList.size()>0)
							{
								for (Object[] param : locationsList)
								{
									assemblyIds.add(param[0] != null ? Long.valueOf(param[0].toString().trim()):0L );
									parliamentForAssemblyMap.put(param[0] != null ? Long.valueOf(param[0].toString().trim()):0L , param[2] != null ? param[2].toString().trim() :"");
								}
							}	
					}
					
					
					if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY))
					{
						queryStr.append(" select UA.constituency.constituencyId, UA.constituency.name, UA.district.districtName, date(ZPD.insertedTime), count(ZPD.zebraPrintDetailsId) ,  ");
						queryStr.append(" date(ZPD.updatedTime), ZPD.printStatus, count(ZPD.printStatus), ZPD.errorStatus, count(ZPD.errorStatus)  ");
						queryStr.append(" from ZebraPrintDetails ZPD, TdpCadre TD, UserAddress UA where ");
						
						queryStr.append(" TD.tdpCadreId = ZPD.tdpCadreId and TD.userAddress.userAddressId = UA.userAddressId and ((ZPD.printStatus !='' or ZPD.printStatus is null) or ( ZPD.errorStatus !='' or ZPD.errorStatus is null )) and ");
						queryStr.append(" TD.isDeleted = 'N' and TD.enrollmentYear = 2014 and UA.constituency.constituencyId not in (:locationIdList) ");
						
						if(fromDate != null && toDate != null)
						{
							queryStr.append(" and ( date(ZPD.insertedTime) >= :fromDate and date(ZPD.insertedTime) <= :toDate ) ");
						}
						
						queryStr.append(" group by UA.constituency.constituencyId, date(ZPD.insertedTime) , date(ZPD.updatedTime), ZPD.printStatus, ZPD.errorStatus  order by date(ZPD.updatedTime) asc ");
						
					}
					
					printStatusDetailsList = zebraPrintDetailsDAO.getMemberShipCardPushStatusByDate(selectedLocationIds, fromDate, toDate,queryStr.toString());
					Map<Long,ZebraPrintDetailsVO> zebraPrintMap = new HashMap<Long, ZebraPrintDetailsVO>(0);
					Map<String,ZebraPrintDetailsVO>  daywiseVOMap = new TreeMap<String, ZebraPrintDetailsVO>();					
					if(printStatusDetailsList != null && printStatusDetailsList.size()>0)
					{
						for (Object[] printStatus : printStatusDetailsList) 
						{
							ZebraPrintDetailsVO locationVO = null;
							
							if(zebraPrintMap.get(printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L) != null)
							{
								locationVO = zebraPrintMap.get(printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L);
								printStatusList = locationVO.getZebraPrintDetailsVOList();
							}
							else
							{
								locationVO = new ZebraPrintDetailsVO();
								locationVO.setId(printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L);
								locationVO.setName(printStatus[1] != null ? printStatus[1].toString().trim():"");
								locationVO.setDistrict(printStatus[2] != null ? printStatus[2].toString().trim():"");
								locationVO.setParliament(parliamentForAssemblyMap.get(locationVO.getId()) != null ? parliamentForAssemblyMap.get(locationVO.getId()):"");
								locationVO.setDataPushDate(printStatus[3] != null ? format.format(dateFormat.parse(printStatus[3] != null ? printStatus[3].toString().trim():"")):null);
								printStatusList = new ArrayList<ZebraPrintDetailsVO>();
							}
							
							ZebraPrintDetailsVO reportVO = new ZebraPrintDetailsVO();
							
							reportVO.setDataPushDate(printStatus[3] != null ? format.format(dateFormat.parse(printStatus[3] != null ? printStatus[3].toString().trim():"")):null);
							reportVO.setTotalPushCount(printStatus[4] != null ? Long.valueOf(printStatus[4].toString().trim()):0L);
							
							reportVO.setUpdatedDate(printStatus[5] != null ? format.format(dateFormat.parse(printStatus[5] != null ? printStatus[5].toString().trim():"")):null);
							reportVO.setPrintStatus(printStatus[6] != null ? printStatus[6].toString().trim():null);
							reportVO.setPrintStatusCount(printStatus[7] != null ? Long.valueOf(printStatus[7].toString().trim()):0L);
							
							reportVO.setErrorStatus(printStatus[8] != null ? printStatus[8].toString().trim():null);
							reportVO.setErrorStatusCount(printStatus[9] != null ? Long.valueOf(printStatus[9].toString().trim()):0L);
													
							printStatusList.add(reportVO);
							
							locationVO.setZebraPrintDetailsVOList(printStatusList);
							
							zebraPrintMap.put(locationVO.getId(), locationVO);
							
							if(reportVO.getUpdatedDate() != null)
							{
								daywiseVOMap.put(reportVO.getUpdatedDate(), null);
							}
						}
					}
					
					List<ZebraPrintDetailsVO> finalReturnList = new ArrayList<ZebraPrintDetailsVO>();
					if(zebraPrintMap != null && zebraPrintMap.size()>0)
					{
						for (Long constituencyId : zebraPrintMap.keySet()) 
						{
							ZebraPrintDetailsVO constituencyVO = zebraPrintMap.get(constituencyId);
							printStatusList = new ArrayList<ZebraPrintDetailsVO>();
							Map<String,ZebraPrintDetailsVO>  daywiseCountMap = new TreeMap<String, ZebraPrintDetailsVO>();									
							if(constituencyVO.getZebraPrintDetailsVOList() != null && constituencyVO.getZebraPrintDetailsVOList().size()>0)
							{
								Long totalCount = 0L;
								Long printCount = 0L;
								Long errorCount = 0L;
								
								if(daywiseVOMap != null && daywiseVOMap.size()>0)
								{
									for (String date : daywiseVOMap.keySet()) 
									{
										
										ZebraPrintDetailsVO updatedVO = null;
										ZebraPrintDetailsVO filterVO  = getMatchedZebraPrintDetailsVOByDate(constituencyVO.getZebraPrintDetailsVOList(),date);
										
										if(filterVO != null)
										{
											totalCount = totalCount + filterVO.getTotalPushCount();
											
											if(filterVO != null && filterVO.getUpdatedDate() != null )
											{				
												if(daywiseCountMap.get(filterVO.getUpdatedDate().trim()) != null)
												{
													updatedVO = daywiseCountMap.get(filterVO.getUpdatedDate().trim());
													printCount = updatedVO.getPrintStatusCount();
													errorCount = updatedVO.getErrorStatusCount();
												}
												else
												{
													updatedVO = new ZebraPrintDetailsVO();
												}
												
												if(filterVO.getPrintStatus() != null)
												{
													printCount = printCount + filterVO.getPrintStatusCount();
												}
																						
												if(filterVO.getErrorStatus() != null)
												{
													errorCount = errorCount + filterVO.getErrorStatusCount();
												}
												
												updatedVO.setUpdatedDate(filterVO.getUpdatedDate());
												updatedVO.setPrintStatusCount(printCount);
												updatedVO.setErrorStatusCount(errorCount);
												
												daywiseCountMap.put(date.trim(), updatedVO);
												
											}
										}									
										else
										{
											updatedVO = new ZebraPrintDetailsVO();
											updatedVO.setUpdatedDate(" -- ");
											updatedVO.setPrintStatusCount(printCount);
											updatedVO.setErrorStatusCount(errorCount);
											
											daywiseCountMap.put(date.trim(), updatedVO);
										}
									
									}
								}
								
								if(daywiseCountMap != null && daywiseCountMap.size()>0)
								{
									for (String updatedDate : daywiseCountMap.keySet()) 
									{
										printStatusList.add(daywiseCountMap.get(updatedDate));
									}
								}
								
								constituencyVO.setTotalPushCount(totalCount);
								constituencyVO.setZebraPrintDetailsVOList(printStatusList);
							}
							
							

							finalReturnList.add(constituencyVO);
						}
					}
					
					System.out.println(finalReturnList.size());
					if(daywiseVOMap != null && daywiseVOMap.size()>0)
					{
						returnVO.getDatesList().addAll(daywiseVOMap.keySet());
					}
					returnVO.setZebraPrintDetailsVOList(finalReturnList);
				}
			}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public ZebraPrintDetailsVO getMatchedZebraPrintDetailsVOByDate(List<ZebraPrintDetailsVO> reportVOList,String dateStr)
	{
		ZebraPrintDetailsVO returnVo = null;
		try {
			
			if(reportVOList != null && reportVOList.size()>0)
			{
				for (ZebraPrintDetailsVO reportVO : reportVOList) 
				{
					if(reportVO.getUpdatedDate() != null)
					{
						if(reportVO.getUpdatedDate().trim().equalsIgnoreCase(dateStr.trim()))
						{
							return reportVO;
						}
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnVo;
	}*/
	
	/*public void testUpdateVoterName()
	{
		ZebraPrintDetails zebraPrintDetails = zebraPrintDetailsDAO.get(206753l);
		long zebraPrintDetailsId = zebraPrintDetails.getZebraPrintDetailsId();
		String voterName = zebraPrintDetails.getVoterName();
		voterName = voterName.replaceAll("\\p{C}", "");
		System.out.println(zebraPrintDetailsDAO.updateVoterName(zebraPrintDetailsId, voterName));
	}*/
}
