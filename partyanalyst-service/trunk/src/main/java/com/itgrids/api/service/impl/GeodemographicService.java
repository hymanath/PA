package com.itgrids.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.itgrids.api.service.IGeodemographicService;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;

public class GeodemographicService implements IGeodemographicService{
	private final static Logger LOG =  Logger.getLogger(GeodemographicService.class);
	private IDistrictDAO districtDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IPanchayatDAO panchayatDAO;
	private IBoothDAO boothDAO;
	
	
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}
	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	/*
	 * Author:Teja
	 * 
	 * @see
	 * com.itgrids.partyanalyst.service.IGeodemographicService#getDistricts(java
	 * .lang.Long) Method to get all districts in a state
	 */
	public List<SelectOptionVO> getDistricts(Long stateId) {
		List<District> list = districtDAO.findByStateId(stateId);
		List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>();
		for (District district : list) {
			
			districts.add(new SelectOptionVO(district.getDistrictId(), WordUtils.capitalize(district.getDistrictName().toLowerCase())));
		}
		return districts;
	}
	public List<SelectOptionVO> getConstituenciesByDistrictID(Long districtID){
		List<Constituency> constituencies = delimitationConstituencyDAO.getLatestConstituenciesForDistrict(districtID);
		List<SelectOptionVO> constituencyNames=new ArrayList<SelectOptionVO>();
		for(Constituency constituency:constituencies)
			constituencyNames.add(new SelectOptionVO(constituency.getConstituencyId(),WordUtils.capitalize(constituency.getName().toLowerCase())));		
		return constituencyNames;
	}
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getMandalsByConstituencyIDFromBooth(Long constituencyID) {
		List list = boothConstituencyElectionDAO.getMandalsByConstituencyIDFromBooth(constituencyID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(int i=0; i<list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			result.add(new SelectOptionVO(new Long(obj[0].toString()),obj[1].toString()));
		}
		return result;
	}
	public List<SelectOptionVO> getVillagesForMandalId(Long mandalId){
		List<SelectOptionVO> finaleVoList = new ArrayList<SelectOptionVO>(0);
		try {
			List<Object[]> manList = panchayatDAO.getPanchayatsByTehsilId(mandalId);
			
			if(manList != null && manList.size() > 0){
				for (Object[] objects : manList) {
					SelectOptionVO vo = new SelectOptionVO();
					vo.setId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setName(objects[1]!=null?objects[1].toString():"");
					finaleVoList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exceptionr riased at getDistrictsForState", e);
		}
		return finaleVoList;
	}	
	 
	 public List<SelectOptionVO> getBoothsList(Long panchayatId){
		  List<SelectOptionVO> retunBoothList=new ArrayList<SelectOptionVO>();
		  try{
				  List<Object[]> boothListForPan=boothDAO.getBoothListFrPanchayat(panchayatId);
				  for (Object[] objects : boothListForPan) {
					  SelectOptionVO vo = new SelectOptionVO();
					  vo.setId(objects[0]!=null?(Long)objects[0]:0l);
					  vo.setName(objects[1]!=null?objects[1].toString():"");
					  vo.setName("Booth No:"+vo.getName());
					retunBoothList.add(vo);
				}
			
		  }catch(Exception e){
			  LOG.error("Error occured at getBoothsList() in GeodemographicService {}",e); 
		  }
		  return retunBoothList;
    }
}
