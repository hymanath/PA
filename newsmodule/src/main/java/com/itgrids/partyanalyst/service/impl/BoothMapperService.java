package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IBoothMapperService;

public class BoothMapperService implements IBoothMapperService{/*

	private IBoothDAO boothDAO;
	private IConstituencyDAO constituencyDAO;
	private IBoothLocalBodyWardDAO boothLocalBodyWardDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;
	private IBoothVillageDAO boothVillageDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;	
	private IUserMappingsHistoryDAO userMappingsHistoryDAO;
	private IDateService dateService;
	private IUserDAO userDAO;
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	
	public IBoothVillageDAO getBoothVillageDAO() {
		return boothVillageDAO;
	}

	public void setBoothVillageDAO(IBoothVillageDAO boothVillageDAO) {
		this.boothVillageDAO = boothVillageDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IBoothLocalBodyWardDAO getBoothLocalBodyWardDAO() {
		return boothLocalBodyWardDAO;
	}

	public void setBoothLocalBodyWardDAO(
			IBoothLocalBodyWardDAO boothLocalBodyWardDAO) {
		this.boothLocalBodyWardDAO = boothLocalBodyWardDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	
	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public IAssemblyLocalElectionBodyWardDAO getAssemblyLocalElectionBodyWardDAO() {
		return assemblyLocalElectionBodyWardDAO;
	}

	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}	

	public IUserMappingsHistoryDAO getUserMappingsHistoryDAO() {
		return userMappingsHistoryDAO;
	}

	public void setUserMappingsHistoryDAO(
			IUserMappingsHistoryDAO userMappingsHistoryDAO) {
		this.userMappingsHistoryDAO = userMappingsHistoryDAO;
	}	

	public IDateService getDateService() {
		return dateService;
	}

	public void setDateService(IDateService dateService) {
		this.dateService = dateService;
	}

	public List<ConstituencyBoothInfoVO> getBoothOfAssemblyByYear(Long constituencyId, Long year){
		List<ConstituencyBoothInfoVO> booths = new ArrayList<ConstituencyBoothInfoVO>();
		ConstituencyBoothInfoVO boothInfoVO = null;
		List rawBoothData = boothDAO.findBoothInfoByConstituencyIdAndYear(constituencyId, year);
		for(Object[] values:(List<Object[]>)rawBoothData){
			boothInfoVO = new ConstituencyBoothInfoVO();
			boothInfoVO.setBoothConstiElecId(Long.parseLong(values[0].toString()));
			boothInfoVO.setPartNo(values[1].toString());
			boothInfoVO.setVillagesCovered(values[2].toString());
			booths.add(boothInfoVO);
		}
		return booths;
	}
	
	public ResultStatus saveBoothLocalElectionBodyMappingInfo(List<Long> boothIds,List<Long> boothIdsToModify, Long locationId, Boolean isWard, Long userId){
		int i = 0;
		ResultStatus result = new ResultStatus(); 
		
		try{
			Long localBodyId = 0l;
			Constituency localBodyWard = null;
			if(isWard){
				if(boothIdsToModify.size()>0)
					boothLocalBodyWardDAO.deleteRecords(boothIdsToModify,locationId);
				if(boothIds.size()>0)
				{
					localBodyWard = constituencyDAO.get(locationId);
					localBodyId = localBodyWard.getLocalElectionBody().getLocalElectionBodyId();
					List<Booth> booths = boothDAO.findByBoothIds(boothIds);
					for(Booth booth:booths)
					{
						boothLocalBodyWardDAO.save(new BoothLocalBodyWard(booth, localBodyWard));						
					}	
				}
				result.setExceptionMsg(saveDataInUserMappingHistory(userId, "BoothLocalBodyWard", boothIdsToModify.size(),boothIds.size()));	
			}else
				if(boothIdsToModify.size()>0)
					boothDAO.removeMappingToLocalBody(boothIdsToModify);
				localBodyId = locationId;
			if(boothIds.size()>0)
				i = boothDAO.updateLocalBodyInfoByBoothIdsAndWardId(localBodyId, boothIds);
			result.setExceptionMsg(saveDataInUserMappingHistory(userId, "Booth", boothIdsToModify.size(),boothIds.size()));	
		}catch (Exception e) {
			e.printStackTrace();
			result.setExceptionEncountered(e);
		}
		
		return result;
	}
	
	public ResultStatus saveAssemblyLocalBodyMappingInfo(Long localBodyId, List<Long> localBodyOrWardIds, List<Long> localBodyOrWardIdsToModify, 
			Long assemblyId, String year, Boolean isWard, Long userId){
		ResultStatus result = new ResultStatus();
		Constituency constituency = constituencyDAO.get(assemblyId);
		LocalElectionBody localElectionBody = null;
		AssemblyLocalElectionBody assemblyLocalElectionBody = null;
		AssemblyLocalElectionBodyWard assemblyLocalElectionBodyWard = null;
		List<AssemblyLocalElectionBody> assemblyLocalBodies = null;
		List rawData = null;
		try {
			if(isWard){
				if(localBodyOrWardIdsToModify.size()>0)
				{
					//get assemblyLocalElectionBodyWard ids to delete records based on these ids
					List resultList = assemblyLocalElectionBodyWardDAO.getAssemblyLocalElectionBodyWardIds(assemblyId, localBodyOrWardIdsToModify);
					List<Long> idsList = new ArrayList<Long>();
					if(resultList.size()>0)
						for(int i = 0;i<resultList.size();i++)
						{
							idsList.add(Long.parseLong(resultList.get(i).toString()));							
						}
					
					assemblyLocalElectionBodyWardDAO.deleteByWardsAndConstituency(idsList);
				}
					
				assemblyLocalBodies = assemblyLocalElectionBodyDAO.findByAssemblyLocalBodyAndYear(localBodyId, 
						assemblyId, year);
				if(assemblyLocalBodies.size() == 1)
					assemblyLocalElectionBody = assemblyLocalBodies.get(0);
				else if(assemblyLocalBodies.size() == 0){
					localElectionBody = localElectionBodyDAO.get(localBodyId);
					assemblyLocalElectionBody = new AssemblyLocalElectionBody();
					assemblyLocalElectionBody.setConstituency(constituency);
					assemblyLocalElectionBody.setLocalElectionBody(localElectionBody);
					assemblyLocalElectionBody.setYear(year);
					assemblyLocalElectionBody.setIsPartial("1");
					assemblyLocalElectionBody = assemblyLocalElectionBodyDAO.save(assemblyLocalElectionBody);
				}
				if(localBodyOrWardIds.size()>0)
				{	
					List<Constituency> wards = constituencyDAO.getAllWardsObjsByLocalElectionBodyWardIds(localBodyOrWardIds);
						for(Constituency ward:wards){
						rawData = assemblyLocalElectionBodyWardDAO.findByAssemblyLocalElectionBodyWardAndYear(assemblyLocalElectionBody.getAssemblyLocalElectionBodyId(),
								ward.getConstituencyId(), year);
						if(rawData.size() > 0)
							continue;
						assemblyLocalElectionBodyWard = new AssemblyLocalElectionBodyWard();
						assemblyLocalElectionBodyWard.setAssemblyLocalElectionBody(assemblyLocalElectionBody);
						assemblyLocalElectionBodyWard.setConstituency(ward);
						assemblyLocalElectionBodyWard.setYear(year);
						assemblyLocalElectionBodyWardDAO.save(assemblyLocalElectionBodyWard);	
						}	
						
				}
				result.setExceptionMsg(saveDataInUserMappingHistory(userId, "AssemblyLocalElectionBodyWard", localBodyOrWardIdsToModify.size(),localBodyOrWardIds.size()));
			}else{
				//delete existing mappings if they were unchecked in mapper ui
				if(localBodyOrWardIdsToModify.size()>0)
					assemblyLocalElectionBodyDAO.deleteByLocalElectionBodyAndConstituency(localBodyOrWardIdsToModify, assemblyId);
				if(localBodyOrWardIds.size()>0)
				{	
					List<LocalElectionBody> localBodies = localElectionBodyDAO.findByLocalElectionBodyIds(localBodyOrWardIds);
					for(LocalElectionBody electionBody:localBodies){
						assemblyLocalBodies = assemblyLocalElectionBodyDAO.findByAssemblyLocalBodyAndYear(electionBody.getLocalElectionBodyId(), 
								assemblyId, year);
						if(assemblyLocalBodies.size() > 0)
							continue;
						assemblyLocalElectionBody = new AssemblyLocalElectionBody();
						assemblyLocalElectionBody.setConstituency(constituency);
						assemblyLocalElectionBody.setLocalElectionBody(electionBody);
						assemblyLocalElectionBody.setYear(year);
						assemblyLocalElectionBody.setIsPartial("0");
						assemblyLocalElectionBody = assemblyLocalElectionBodyDAO.save(assemblyLocalElectionBody);
					}	
				}
				result.setExceptionMsg(saveDataInUserMappingHistory(userId, "AssemblyLocalElectionBody", localBodyOrWardIdsToModify.size(),localBodyOrWardIds.size()));
			}	
		} catch (Exception e) {
			e.printStackTrace();
			result.setExceptionEncountered(e);
			
		}		
		return result;		
	}
	
	*//**
	 * This method is used to save the villages that are covered under a booth.
	 * 
	 * @author Ravi Kiran.Y
	 * @return ResultStatus
	 *//*
	public ResultStatus setDataForVillageBoothRelation(Long districtId,Long electionYear){
		ResultStatus resultWithException = new ResultStatus();
		try{	
		List<Constituency> conList = delimitationConstituencyDAO.getLatestConstituenciesForDistrict(districtId);
			
			for(Constituency result : conList){			
				Long constituencyId = result.getConstituencyId();
				List<Object> partNo = boothDAO.getPartNumbersAndVillagesCoveredInAConstituency(constituencyId, electionYear);
				 
				 if(partNo!=null && partNo.size()!=0){
					 for(int i=0;i<partNo.size();i++){
						 Object[] parms = (Object[])partNo.get(i);						 
						
						 Long partNumber = new Long(parms[0].toString());
						 
						 List villageNames = boothVillageDAO.getVillagesForABoothInAConstituency(result.getName(),partNumber);
						 
						 List<String> villages = Arrays.asList(parms[1].toString().toUpperCase().split(","));
						 Set<String> unDuplicateVillages = new HashSet<String>(villages);
						 if(villageNames!=null && villageNames.size()!=0){
							 for(int j=0;j<villageNames.size();j++){
								 Object[] data = (Object[])villageNames.get(j);
								 if(data[0]!=null){
									 String names = data[0].toString();
									 if(!villages.contains(names)){									
										 Set<String> villageSet =  new HashSet<String>(Arrays.asList(names.toUpperCase().split(",")));
										 if(villageSet.contains(".")){
											 villageSet.remove(".");
										 }
										 unDuplicateVillages.addAll(villageSet);
									 }
								 }
							 }
						 }
						
						String uniqueNames = new String();
				        for (Iterator iterator = unDuplicateVillages.iterator(); iterator.hasNext();) {
				        	uniqueNames += iterator.next().toString();				        	
				        	uniqueNames +=", ";					        		
				         }		
				        uniqueNames =  new StringBuilder(uniqueNames).delete(uniqueNames.length()-2, uniqueNames.length()).toString();
						 boothDAO.updateVillagesCoveredInfoInAConstituency(constituencyId,uniqueNames,partNumber.toString(),electionYear);
					 }
				 }
			}
			resultWithException.setResultCode(ResultCodeMapper.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			resultWithException.setResultCode(ResultCodeMapper.FAILURE);
			resultWithException.setExceptionEncountered(e);
		}
		return resultWithException;
	}
	*//**
	 * This method is used to save the data in userMappingsHistory table, to keep track of all the modifications 
	 * made by user in mapping related tables in the database 
	 * @param userId
	 * @param tableName
	 * @param noOfRowsDeleted
	 * @param noOfRowsUpdated
	 * @return
	 *//*
	private String saveDataInUserMappingHistory(Long userId, String tableName, int noOfRowsDeleted, int noOfRowsUpdated)
	{
	
		String message = null;
		UserMappingsHistory userMappingsHistory = new UserMappingsHistory();
		userMappingsHistory.setUser(userDAO.get(userId));
		userMappingsHistory.setTableName(tableName);
		userMappingsHistory.setLastUpdated(new Date());
		userMappingsHistory.setNoOfRowsDeleted(Long.parseLong(noOfRowsDeleted+""));
		userMappingsHistory.setNoOfRowsUpdated(Long.parseLong(noOfRowsUpdated+""));
		try
		{
			userMappingsHistoryDAO.save(userMappingsHistory);			
		} catch(Exception e)
		{
			e.printStackTrace();
			message = "Exception occured while saving data in UserMappingsHistory table";			
		}
		return message;
		
	}
	
*/}
