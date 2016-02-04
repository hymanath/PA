package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IDesignationDAO;
import com.itgrids.partyanalyst.dao.IRtcDepotDAO;
import com.itgrids.partyanalyst.dao.IRtcRegionDAO;
import com.itgrids.partyanalyst.dao.IRtcZoneDAO;
import com.itgrids.partyanalyst.dao.IUnionTypeDesignationDAO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.model.RtcZone;
import com.itgrids.partyanalyst.service.IRtcUnionService;

public class RtcUnionService implements IRtcUnionService{

	 private static final Logger LOG = Logger.getLogger(RtcUnionService.class);
	 private IRtcZoneDAO rtcZoneDAO;
	 private IRtcRegionDAO rtcRegionDAO;
	 private IRtcDepotDAO rtcDepotDAO;
	 private IUnionTypeDesignationDAO unionTypeDesignationDAO;
	 
	 
	 public IRtcZoneDAO getRtcZoneDAO() {
		return rtcZoneDAO;
	}
	public void setRtcZoneDAO(IRtcZoneDAO rtcZoneDAO) {
		this.rtcZoneDAO = rtcZoneDAO;
	}
	public IRtcRegionDAO getRtcRegionDAO() {
		return rtcRegionDAO;
	}
	public void setRtcRegionDAO(IRtcRegionDAO rtcRegionDAO) {
		this.rtcRegionDAO = rtcRegionDAO;
	}
	public IRtcDepotDAO getRtcDepotDAO() {
		return rtcDepotDAO;
	}
	public void setRtcDepotDAO(IRtcDepotDAO rtcDepotDAO) {
		this.rtcDepotDAO = rtcDepotDAO;
	}
	public IUnionTypeDesignationDAO getUnionTypeDesignationDAO() {
		return unionTypeDesignationDAO;
	}
	public void setUnionTypeDesignationDAO(
			IUnionTypeDesignationDAO unionTypeDesignationDAO) {
		this.unionTypeDesignationDAO = unionTypeDesignationDAO;
	}
	
	public List<IdNameVO> getAllRTCZones(){
		List<IdNameVO> fianaVoList = new ArrayList<IdNameVO>(0);
		 try {
			 List<RtcZone> rtcZoneList = rtcZoneDAO.getAll();
			 
			 if(rtcZoneList != null && rtcZoneList.size() > 0){
				 for (RtcZone rtcZone : rtcZoneList) {
					 IdNameVO vo = new IdNameVO();
					 vo.setId(rtcZone.getRtcZoneId());
					 vo.setName(rtcZone.getZoneName());
					 fianaVoList.add(vo);
				}
			 }
			 
		} catch (Exception e) {
			LOG.error("Exception raised at getAllRTCZones", e);
		}
		 
		 return fianaVoList;
	 }
	
	public List<IdNameVO> getRegionsOfZone(Long zoneId){
		List<IdNameVO> finalVoList = new ArrayList<IdNameVO>(0);
		try {
			List<Object[]> rtcRegionObjList = rtcRegionDAO.getRegionsOfZone(zoneId);
			
			if(rtcRegionObjList != null && rtcRegionObjList.size() > 0){
				for (Object[] objects : rtcRegionObjList) {
					IdNameVO vo =new IdNameVO();
					vo.setId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setName(objects[1]!=null?objects[1].toString():"");
					finalVoList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getRegionsOfZone", e);
		}
		return finalVoList;
	}
	
	public List<IdNameVO> getDepotsOfRegion(Long regionId){
		List<IdNameVO> finaleVoList = new ArrayList<IdNameVO>(0);
		try {
			List<Object[]> rtcDepotsObjList = rtcDepotDAO.getDepotsOfRegion(regionId);
			
			if(rtcDepotsObjList != null && rtcDepotsObjList.size() > 0){
				for (Object[] objects : rtcDepotsObjList) {
					IdNameVO vo = new IdNameVO();
					vo.setId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setName(objects[1]!=null?objects[1].toString():"");
					finaleVoList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exceptionr riased at getDepotsOfRegion", e);
		}
		return finaleVoList;
	}
	
	public List<IdNameVO> getDesignationsOfUnionType(Long uniontypeId){
		List<IdNameVO> finalevoList = new ArrayList<IdNameVO>(0);
		try {
			List<Object[]> unionTypeDesignationObjList = unionTypeDesignationDAO.getDesignationsOfUnionType(uniontypeId);
			if(unionTypeDesignationObjList != null && unionTypeDesignationObjList.size() > 0){
				for (Object[] objects : unionTypeDesignationObjList) {
					IdNameVO vo = new IdNameVO();
					vo.setId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setName(objects[1]!=null?objects[1].toString():"");
					finalevoList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception riased at getDesignationsOfUnionType", e);
		}
		return finalevoList;
	}
}
