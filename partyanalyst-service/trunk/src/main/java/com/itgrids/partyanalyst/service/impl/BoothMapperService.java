package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothLocalBodyWardDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dto.ConstituencyBoothInfoVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothLocalBodyWard;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.service.IBoothMapperService;

public class BoothMapperService implements IBoothMapperService{

	private IBoothDAO boothDAO;
	private IConstituencyDAO constituencyDAO;
	private IBoothLocalBodyWardDAO boothLocalBodyWardDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	
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
	
	public void saveBoothLocalElectionBodyMappingInfo(List<Long> boothIds, Long wardId){
		try{
			Constituency localBodyWard = constituencyDAO.get(wardId);
			int i = boothDAO.updateLocalBodyInfoByBoothIdsAndWardId(wardId, boothIds);
			System.out.println("Records Updated::"+i);
			List<Booth> booths = boothDAO.findByBoothIds(boothIds);  
			for(Booth booth:booths)
				boothLocalBodyWardDAO.save(new BoothLocalBodyWard(booth, localBodyWard));	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
