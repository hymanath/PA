package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothLocalBodyWardDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dto.ConstituencyBoothInfoVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.model.AssemblyLocalElectionBody;
import com.itgrids.partyanalyst.model.AssemblyLocalElectionBodyWard;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothLocalBodyWard;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.service.IBoothMapperService;

public class BoothMapperService implements IBoothMapperService{

	private IBoothDAO boothDAO;
	private IConstituencyDAO constituencyDAO;
	private IBoothLocalBodyWardDAO boothLocalBodyWardDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;
	
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
	
	public ResultWithExceptionVO saveBoothLocalElectionBodyMappingInfo(List<Long> boothIds, Long locationId, Boolean isWard){
		int i = 0;
		ResultWithExceptionVO result = new ResultWithExceptionVO(); 
		try{
			Long localBodyId = 0l;
			Constituency localBodyWard = null;
			if(isWard){
				localBodyWard = constituencyDAO.get(locationId);
				localBodyId = localBodyWard.getLocalElectionBody().getLocalElectionBodyId();
				List<Booth> booths = boothDAO.findByBoothIds(boothIds);
				for(Booth booth:booths)
					boothLocalBodyWardDAO.save(new BoothLocalBodyWard(booth, localBodyWard));
			}else
				localBodyId = locationId;
			
			i = boothDAO.updateLocalBodyInfoByBoothIdsAndWardId(localBodyId, boothIds);

		}catch (Exception e) {
			e.printStackTrace();
			result.setExceptionEncountered(e);
		}
		
		return result;
	}
	
	public ResultWithExceptionVO saveAssemblyLocalBodyMappingInfo(Long localBodyId, List<Long> localBodyOrWardIds, 
			Long assemblyId, String year, Boolean isWard){
		ResultWithExceptionVO result = new ResultWithExceptionVO();
		Constituency constituency = constituencyDAO.get(assemblyId);
		LocalElectionBody localElectionBody = null;
		AssemblyLocalElectionBody assemblyLocalElectionBody = null;
		AssemblyLocalElectionBodyWard assemblyLocalElectionBodyWard = null;
		List<AssemblyLocalElectionBody> assemblyLocalBodies = null;
		List rawData = null;
		try {
			if(isWard){
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
			}else{
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
		} catch (Exception e) {
			e.printStackTrace();
			result.setExceptionEncountered(e);
		}
		
		return result;
		
	}
	
}
