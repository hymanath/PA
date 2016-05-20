package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.BloodBankVO;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.service.IBloodBankService;

public class BloodBankService implements IBloodBankService{

	private static final Logger   LOG = Logger.getLogger(BloodBankService.class);

	private IOccupationDAO occupationDAO;
	private IEducationalQualificationsDAO educationalQualificationsDAO;
	private ITdpCadreDAO tdpCadreDAO;
	
   
	public IOccupationDAO getOccupationDAO() {
		return occupationDAO;
	}

	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	public void setOccupationDAO(IOccupationDAO occupationDAO) {
		this.occupationDAO = occupationDAO;
	}

	public IEducationalQualificationsDAO getEducationalQualificationsDAO() {
		return educationalQualificationsDAO;
	}

	public void setEducationalQualificationsDAO(
			IEducationalQualificationsDAO educationalQualificationsDAO) {
		this.educationalQualificationsDAO = educationalQualificationsDAO;
	}

	@Override
	public List<BloodBankVO> getOccupationList() {
		
		List<BloodBankVO> occuList=new ArrayList<BloodBankVO>(0);
		try{
		  List<Occupation> rtrnOccuptnLst=occupationDAO.getAll();
		  if(rtrnOccuptnLst!=null && rtrnOccuptnLst.size()>0){
			  for (Occupation occupation : rtrnOccuptnLst) {
				BloodBankVO occupationVO=new BloodBankVO();
				occupationVO.setId(occupation.getOccupationId());
				occupationVO.setName(occupation.getOccupation());
				occuList.add(occupationVO);
			}
		  }
		}catch(Exception e){
		LOG.info("Error raised at getOccupationList() BloodBankService in ",e);	
		}
		return occuList;
	}

	@Override
	public List<BloodBankVO> getEducationalQualificationsList() {
		
		List<BloodBankVO> eductnQulfctnLst=new ArrayList<BloodBankVO>(0);
		try{
		 List<EducationalQualifications> rtrnEdctnQulfctnList=educationalQualificationsDAO.getAll();
		 if(rtrnEdctnQulfctnList!=null && rtrnEdctnQulfctnList.size()>0){
			 for (EducationalQualifications edctnlQlfctns : rtrnEdctnQulfctnList) {
				 BloodBankVO edctnQulfctnVO=new BloodBankVO();
				  edctnQulfctnVO.setId(edctnlQlfctns.getId());
				  edctnQulfctnVO.setName(edctnlQlfctns.getQualification());
				  eductnQulfctnLst.add(edctnQulfctnVO);
			}
		 }
		}catch(Exception e) {
			LOG.info("Error raised at getEducationalQualificationsList() BloodBankService in ",e);			
		}
		return eductnQulfctnLst;
	}

	@Override
	public BloodBankVO getCadreDetails(String memberShipNO){
          
		BloodBankVO cadreDtlsVO=new BloodBankVO();
		try {
			 Object[] obj=null;
			 if(memberShipNO!=null && !memberShipNO.isEmpty()){
			  obj=tdpCadreDAO.getCadreDetailsByMmbrShpId(memberShipNO);
			 }
			 
			  if(obj!=null && obj.length>0){
				  cadreDtlsVO.setId((Long)obj[0]);
				  String firstName=obj[1]!=null?obj[1].toString():" ";
				  String lastName=obj[2]!=null?obj[2].toString():" ";
				  cadreDtlsVO.setName(firstName+" "+lastName);
				  cadreDtlsVO.setRelativeName(obj[3]!=null?obj[3].toString():" ");
				  cadreDtlsVO.setMobile(obj[4]!=null?obj[4].toString():" ");
				  cadreDtlsVO.setEmail(obj[5]!=null?obj[5].toString():" ");
				  cadreDtlsVO.setSex(obj[6]!=null?obj[6].toString():" ");
				  cadreDtlsVO.setAge(obj[7]!=null?(Long)obj[7]:0l);
				  cadreDtlsVO.setDob(obj[8]!=null?obj[8].toString():" ");
				  cadreDtlsVO.setEducation(obj[9]!=null?obj[9].toString():" ");
				  cadreDtlsVO.setOccupation(obj[10]!=null?obj[10].toString():" ");
				  cadreDtlsVO.getAddressVO().setStateId(obj[11]!=null?(Long)obj[11]:0l);
				  cadreDtlsVO.getAddressVO().setStateName(obj[12]!=null?obj[12].toString():"");
				  cadreDtlsVO.getAddressVO().setDistrictId(obj[13]!=null?(Long)obj[13]:0l);
				  cadreDtlsVO.getAddressVO().setDistrictName(obj[14]!=null?obj[14].toString():" ");
				  cadreDtlsVO.getAddressVO().setConstituencyId(obj[15]!=null?(Long)obj[15]:0l);
				  cadreDtlsVO.getAddressVO().setConstituencyName(obj[16]!=null?obj[16].toString():" ");
				  if(obj[17]!=null){
					   String  tehsilId="1"+obj[17].toString();
					  cadreDtlsVO.getAddressVO().setTehsilId(Long.valueOf(tehsilId));
				  }
				  cadreDtlsVO.getAddressVO().setTehsilName(obj[18]!=null?obj[18].toString():" ");
				  cadreDtlsVO.getAddressVO().setWardId(obj[19]!=null?(Long)obj[19]:0l);
				  cadreDtlsVO.getAddressVO().setWardName(obj[20]!=null?obj[20].toString():" ");
				  cadreDtlsVO.getAddressVO().setPanchaytId(obj[21]!=null?(Long)obj[21]:0l);
				  cadreDtlsVO.getAddressVO().setPanchayatName(obj[22]!=null?obj[22].toString():" ");
				  if(obj[23]!=null){
					  String localElectionBodyId="2"+obj[23].toString();
					  cadreDtlsVO.getAddressVO().setLocalElectionBodyId(Long.valueOf(localElectionBodyId));
				  }
				  cadreDtlsVO.getAddressVO().setLocalElectionBodyName(obj[24]!=null?obj[24].toString():" ");
			  }
		} catch (Exception e) {
			LOG.info("Error raised at getCadreDetails() BloodBankService in ",e);	
		}
		return cadreDtlsVO;
	}
}
