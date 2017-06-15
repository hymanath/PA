package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertMeekosamPetitionerDAO;
import com.itgrids.partyanalyst.model.AlertMeekosamPetitioner;

public class AlertMeekosamPetitionerDAO extends
		GenericDaoHibernate<AlertMeekosamPetitioner, Long> implements
		IAlertMeekosamPetitionerDAO {
	public AlertMeekosamPetitionerDAO() {
		super(AlertMeekosamPetitioner.class);
		
	}
	public List<Object[]> getMeekosamPetitionerDetails(Long alertId){
		StringBuilder queryStr = new StringBuilder();
				queryStr.append(" select model.meekosamPetitioner.name,model.meekosamPetitioner.relativeName," +
						" model.meekosamPetitioner.age,model.meekosamPetitioner.dateOfBirth,model.meekosamPetitioner.gender," +
						" model.meekosamPetitioner.houseNo,model.meekosamPetitioner.mobileNo," +
						" model.meekosamPetitioner.meekosamOccupation.meekosamOccupation, " +
						" model.meekosamPetitioner.meekosamCasteCategory.meekosamCasteCategory," +
						" model.meekosamPetitioner.meekosamArgeeCategory.meekosamArgeeCategory, model.meekosamPetitioner.meekosamAnnualIncome.meekosamAnnualIncome," +
						" model.meekosamPetitioner.aadharNo,model.meekosamPetitioner.voterCardNo,state.stateName,district.districtName," +
						" constituency.name, tehsil.tehsilName,panchayat.panchayatName,localElectionBody.name " +
						//" ward.wardName " +
						" from AlertMeekosamPetitioner model "+
						" left join model.meekosamPetitioner.userAddress userAddress " +
						" left join userAddress.state state  " +
						" left join userAddress.district district  " +
						" left join userAddress.constituency constituency  " +
						" left join userAddress.tehsil tehsil  " +
						" left join userAddress.localElectionBody localElectionBody  " + 
						" left join userAddress.panchayat panchayat  " +
						" left join userAddress.ward ward  " +
						" where  model.alert.alertId = :alertId and model.meekosamPetitioner.isDeleted ='N' ");
			Query query = getSession().createQuery(queryStr.toString());	
				query.setParameter("alertId", alertId);
		return query.list();
	}
}
