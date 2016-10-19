package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.service.IDataMonitoringService;

public class DataMonitoringService implements IDataMonitoringService {
	
	private static final Logger LOG = Logger.getLogger(DataMonitoringService.class);
	
	private ITdpCadreDAO tdpCadreDAO;

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}     
	public IdNameVO getTotalRegCdrVendorWise(Long stateId, Long vendorId, Long distId, Long constId, String startDate, String endDate){
		LOG.info("Entered into getTotalRegCdrVendorWise() of DataMonitoringService");
		try{
			IdNameVO idNameVO = new IdNameVO();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date stDate = null;
			Date ndDate = null;
			if(startDate != null && startDate.length() > 0 && endDate != null && endDate.length() > 0){
				stDate = sdf.parse(startDate);
				ndDate = sdf.parse(endDate);
			}
			Long pending = 0l;
			Long approved = 0l;
			Long rejected = 0l;
			Long total = 0l;
			//tab user dtls
			List<Object[]> totalCnt = tdpCadreDAO.getTotalRegCdrVendorWise(stateId,vendorId,distId,constId,stDate,ndDate);
			if(totalCnt != null && totalCnt.size() > 0){
				for(Object[] param : totalCnt){
					if((Long)param[0] == 1){
						approved = (Long)param[1];
					}else if((Long)param[0] == 2){
						rejected = (Long)param[1];
					}else{
						pending = (Long)param[1];
					}
				}
			}
			total = approved + rejected + rejected;
			idNameVO.setCount(total);//total
			idNameVO.setActualCount(approved);//approved
			idNameVO.setAvailableCount(pending);//pending
			idNameVO.setOrderId(rejected);//rejected
			//web and online user dtls
			return idNameVO;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in getTotalRegCdrVendorWise() of DataMonitoringService", e); 
		}
		return null;  
	}
	
	
}
