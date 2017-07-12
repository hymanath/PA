package com.rwss.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rwss.dao.IRwsMinWorkscompViewDAO;
import com.rwss.dto.InputVO;
import com.rwss.model.RwsMinWorkscompView;
import com.rwss.utils.IConstants;

@Repository
public class RwsMinWorkscompViewDAO extends GenericDaoHibernate<RwsMinWorkscompView, String> implements IRwsMinWorkscompViewDAO {

	public RwsMinWorkscompViewDAO() {
		super(RwsMinWorkscompView.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Object[]> getSchemeWiseWorkDetails(InputVO inputVO, String workType) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model1.typeOfAssestName, count(distinct model.workId) ");
		 if(inputVO.getLocationType().equalsIgnoreCase("state")){
			 queryStr.append(" ,'01','Andra Pradesh'");
		} else if(inputVO.getLocationType().equalsIgnoreCase("district")){
					   queryStr.append(" ,model3.dCode,model3.dName");
		}else if (inputVO.getLocationType().equalsIgnoreCase("constituency")) {
					   queryStr.append(" ,model4.constituencyCode,model4.contituencyName");
		}else if (inputVO.getLocationType().equalsIgnoreCase("mandal")) {
					   queryStr.append(" ,model3.mCode,model3.mName,model3.dCode,model3.dName ");
		}
		queryStr.append( " from RwsMinWorkscompView model,  RwsMinWorksAdminView model1 , RwsMinWorksAdminHabsView model2,RwsMinHabView model3,"+
				" RwsMinConstituencyView model4  where  model1.workId = model.workId and  model.workId=model2.workId and  model2.habCode=model3.panchCode and "+
				" model3.mCode=model4.mCode and  model3.dCode=model4.dCode ");

		if (workType.equalsIgnoreCase(IConstants.WORK_COMMISSIONED)
				&& inputVO.getFromDate() != null && inputVO.getToDate() != null) {
			queryStr.append(" and model.dateOfComm is not null and model.dateOfComm between :fromDate and :todate");
		} else if (workType.equalsIgnoreCase(IConstants.WORK_COMPLETION)
				&& inputVO.getFromDate() != null && inputVO.getToDate() != null) {
			queryStr.append(" and model.dateOfCompletion is not null and model.dateOfCompletion between :fromDate and :todate");
		}

		if (inputVO.getFilterType() != null && inputVO.getFilterType().trim().length() > 0 && inputVO.getFilterValue() != null && inputVO.getFilterValue().trim().length() > 0) {
			if (inputVO.getFilterType().equalsIgnoreCase("district")) {
				queryStr.append(" and trim(model4.dCode) =:locationValue ");
			} else if (inputVO.getFilterType().equalsIgnoreCase("constituency")) {
				queryStr.append(" and trim(model4.constituencyCode) =:locationValue ");
			} else if (inputVO.getFilterType().equalsIgnoreCase("mandal")) {
				queryStr.append(" and trim(model4.mCode) =:locationValue ");
			}
		}
		if(inputVO.getDistrictValue()!=null && inputVO.getDistrictValue().trim().length()>0 && inputVO.getFilterType().equalsIgnoreCase("mandal")){
			queryStr.append(" and trim(model4.dCode) =:districtValue ");
		}
		queryStr.append(" group by model1.typeOfAssestName ");
		
		if(inputVO.getLocationType()!= null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
			   queryStr.append(" ,model3.dCode,model3.dName");
		   }else if (inputVO.getLocationType()!= null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")) {
			   queryStr.append(" ,model4.constituencyCode,model4.contituencyName");
		   }else if (inputVO.getLocationType()!= null &&  inputVO.getLocationType().trim().equalsIgnoreCase("mandal")) {
			   queryStr.append(" ,model3.mCode,model3.mName,model3.dCode,model3.dName order by model3.dCode,model3.mCode");
		   }
		Query query = getSession().createQuery(queryStr.toString());

		if (inputVO.getFromDate() != null && inputVO.getToDate() != null) {
			query.setDate("fromDate", inputVO.getFromDate());
			query.setDate("todate", inputVO.getToDate());
		}
		if (inputVO.getFilterValue() != null && inputVO.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVO.getFilterValue().trim());
		}
		if(inputVO.getDistrictValue()!=null && inputVO.getDistrictValue().trim().length()>0 && inputVO.getFilterType().equalsIgnoreCase("mandal")){
			query.setParameter("districtValue",inputVO.getDistrictValue());
		}
		return query.list();

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getOnclickWorkDetails(InputVO inputVO) {
		
		StringBuilder queryStr= new StringBuilder();
		queryStr.append("select model.dCode,model.dName,constituencyModel.constituencyCode, constituencyModel.contituencyName,model.mCode,model.mName, model.panchCode, model.panchName," +
				"  compModel.workId,compModel.workName,compModel.dateOfCompletion, compModel.dateOfComm,compModel.sanctionAmount," +
				" adminModel.typeOfAssestName from RwsMinHabView model, " +
				" RwsMinConstituencyView constituencyModel, RwsMinWorkscompView compModel, RwsMinWorksAdminHabsView adminHabModel, RwsMinWorksAdminView adminModel  where " +
				" model.panchCode = adminHabModel.habCode and adminHabModel.workId= compModel.workId and "+
				" model.dCode = constituencyModel.dCode and model.mCode = constituencyModel.mCode and adminModel.workId= compModel.workId ");
		
		if(inputVO.getFilterType()!=null &&  inputVO.getFilterType().equalsIgnoreCase(IConstants.MANDAL)&& inputVO.getFilterType().trim().length() > 0  && inputVO.getDistrictValue()!=null ){
				queryStr.append(" and model.mCode=:mcode ");
		}else if(inputVO.getFilterType()!=null && inputVO.getFilterType().equalsIgnoreCase(IConstants.DISTRICT) && inputVO.getFilterType().trim().length() > 0 ){
				queryStr.append(" and model.dCode=:dCode");
		}else if(inputVO.getFilterType()!=null && inputVO.getFilterType().equalsIgnoreCase(IConstants.CONSTITUENCY)&& inputVO.getFilterType().trim().length() > 0 ){
				queryStr.append(" and constituencyModel.constituencyCode=:conCode");
		}
		
		if(inputVO.getAssetSubType() != null && !inputVO.getAssetSubType().trim().isEmpty()){
			queryStr.append(" and adminModel.typeOfAssestName = :assetSubType ");
		}
		if(inputVO.getDistrictValue()!=null && inputVO.getDistrictValue().trim().length()>0){
			queryStr.append(" and model.dCode=:dCode ");
		} 
		if(inputVO.getAssetType()!= null && inputVO.getAssetType().trim().length()>0){
			queryStr.append(" and adminModel.typeOfAssestName=:assetType ");
		}else{
			queryStr.append(" and adminModel.typeOfAssestName in('PWS','CPWS')");
		}
		if(inputVO.getWorkStatus()!=null && inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_COMMISSIONED)){
		      queryStr.append(" and compModel.dateOfComm is not null and compModel.dateOfComm between :fromDate and :todate");

		} else if(inputVO.getWorkStatus().equalsIgnoreCase(IConstants.WORK_COMPLETION)){
		      queryStr.append(" and compModel.dateOfCompletion is not null and compModel.dateOfCompletion between :fromDate and :todate");
		}
		
		queryStr.append(" group by  model.dCode,model.dName,constituencyModel.constituencyCode, constituencyModel.contituencyName,model.mCode,model.mName, model.panchCode, model.panchName," +
				"  compModel.workId,compModel.workName,compModel.dateOfCompletion, compModel.dateOfComm,compModel.sanctionAmount,adminModel.typeOfAssestName");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(inputVO.getFilterValue()!=null && inputVO.getFilterType().equalsIgnoreCase(IConstants.MANDAL) && inputVO.getFilterValue().trim().length()>0 ){
			query.setParameter("mcode",inputVO.getFilterValue());
		}else if(inputVO.getFilterValue()!=null && inputVO.getFilterType().equalsIgnoreCase(IConstants.DISTRICT) && inputVO.getFilterValue().trim().length()>0 ){
			query.setParameter("dCode",inputVO.getFilterValue());
		}else if(inputVO.getFilterValue()!=null && inputVO.getFilterType().equalsIgnoreCase(IConstants.CONSTITUENCY) && inputVO.getFilterValue().trim().length()>0){
			query.setParameter("conCode",inputVO.getFilterValue());
		}
		
		if(inputVO.getAssetSubType() != null && !inputVO.getAssetSubType().trim().isEmpty()){
			query.setParameter("assetSubType", inputVO.getAssetSubType());
		}
		
		if(inputVO.getFromDate() != null && inputVO.getToDate()!=null){
		      query.setDate("fromDate", inputVO.getFromDate());
		      query.setDate("todate", inputVO.getToDate());
		}
		if(inputVO.getDistrictValue()!=null && inputVO.getDistrictValue().trim().length()>0){
			query.setParameter("dCode",inputVO.getDistrictValue());
		}
		if(inputVO.getAssetType()!= null && inputVO.getAssetType().trim().length()>0){
			query.setParameter("assetType",inputVO.getAssetType());
		}
		return query.list();
		
	}

	@Override
	public List<Object[]> getOnclickTargetsAcheievementsDetails(InputVO inputVO) {

		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();

		sb.append("select ");

		sb2.append(" model.dCode,model.dName,model1.constituencyCode, model1.contituencyName,"
				+ " model.mCode, model.mName, model.panchCode, model.panchName , sum(model.plainPopCovered+model.scPopCovered)");

		sbm.append("from RwsMinHabView model,"
				+ "  RwsMinConstituencyView model1,RwsMinWorksAdminHabsView targetModel, ");

		sbe.append(" where" + "  model.panchCode = targetModel.habCode and "
				+ " model.dCode = model1.dCode and model.mCode = model1.mCode ");

		if (inputVO.getWorkStatus().equalsIgnoreCase(IConstants.TARGET_ALL) && inputVO.getWorkStatus().trim().length()>0) {
			sbm.append(" RwsMinWorksAdminView adminModel ");
			sbe.append(" and targetModel.workId= adminModel.workId ");
			if (inputVO.getFromDate() != null && inputVO.getToDate() != null) {
				sbe.append(" and adminModel.targetDateComp between :fromDate and :toDate ");
			}
			if (inputVO.getAssetType() != null && inputVO.getAssetType().trim().length() > 0) {
				sbe.append(" and model.coverageStatus like :statusType");
			}
		} else if (inputVO.getWorkStatus().equalsIgnoreCase(IConstants.TARGET_COMPLETED) && inputVO.getWorkStatus().trim().length()>0) {
			sbm.append(" RwsMinWorkscompView acheieveModel ");
			sbe.append(" and acheieveModel.workId= targetModel.workId ");
			if (inputVO.getFromDate() != null && inputVO.getToDate() != null) {
				sbe.append(" and acheieveModel.dateOfCompletion between :fromDate and :toDate ");
			}
			if (inputVO.getAssetType() != null && inputVO.getAssetType().trim().length() > 0) {
				sbe.append(" and model.coverageStatus like :statusType");
			}

		}
		if (inputVO.getFilterType() != null && inputVO.getFilterType().trim().length() > 0
				&& inputVO.getFilterValue() != null && inputVO.getFilterValue().trim().length() > 0) {
			if (inputVO.getFilterType().equalsIgnoreCase("district")) {
				sbe.append(" and trim(model1.dCode) =:locationValue ");
			} else if (inputVO.getFilterType().equalsIgnoreCase("constituency")) {
				sbe.append(" and trim(model1.constituencyCode) =:locationValue ");
			} else if (inputVO.getFilterType().equalsIgnoreCase("mandal")) {
				sbe.append(" and trim(model1.mCode) =:locationValue ");
			}
		}
		if (inputVO.getDistrictValue() != null && inputVO.getDistrictValue().trim().length() > 0) {
			sbe.append(" and trim(model1.dCode) =:districtValue ");
		}
		sbe.append(" group by model.dCode,model.dName,model1.constituencyCode, model1.contituencyName,"
				+ " model.mCode, model.mName, model.panchCode, model.panchName ");
		sb.append(sb2.toString()).append(sbm.toString()).append(sbe.toString());
		Query query = getSession().createQuery(sb.toString());

		if (inputVO.getAssetType() != null && inputVO.getAssetType().trim().length() > 0) {
			query.setParameter("statusType", inputVO.getAssetType() + "%");
		}
		if (inputVO.getFromDate() != null && inputVO.getToDate() != null) {
			query.setDate("fromDate", inputVO.getFromDate());
			query.setDate("toDate", inputVO.getToDate());
		} 
		if (inputVO.getDistrictValue() != null && inputVO.getDistrictValue().trim().length() > 0) {
			query.setParameter("districtValue", inputVO.getDistrictValue());
		}
		if (inputVO.getFilterValue() != null && inputVO.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVO.getFilterValue().trim());
		}
		if (inputVO.getStartValue() != null && inputVO.getStartValue()!=0){
			query.setFirstResult(inputVO.getStartValue());
		}
		if (inputVO.getEndValue() != null && inputVO.getEndValue()!=0){
			query.setMaxResults(inputVO.getEndValue());
		}
		return query.list();

	}

	@Override
	public List<Object[]> getStressedKPIDeatils(InputVO inputVO,String TargetType) {
		
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		
		sb.append("select ");
		sb2.append(" model.coverageStatus, count(distinct model.panchCode),sum(model.plainPopCovered+model.scPopCovered) ");
		
		sbm.append("from RwsMinHabView model,"+ 
				" RwsMinStressedHabView stressedModel, RwsMinConstituencyView model1,RwsMinWorksAdminHabsView targetModel, ");
		
		sbe.append(" where" +
			" stressedModel.habCode=model.panchCode and model.panchCode = targetModel.habCode and " +
			" model.dCode = model1.dCode and model.mCode = model1.mCode ");
		
		if(TargetType.equalsIgnoreCase(IConstants.TARGET_ALL)){
			sbm.append(" RwsMinWorksAdminView adminModel ");
			sbe.append(" and targetModel.workId= adminModel.workId ");
			if(inputVO.getYear() != null  && inputVO.getFromDate() != null && inputVO.getToDate() == null){
				sbe.append(" and adminModel.targetDateComp >= :year ");
			}else if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				sbe.append(" and adminModel.targetDateComp between :fromDate and :toDate ");
			}
			
		}else if(TargetType.equalsIgnoreCase(IConstants.TARGET_COMPLETED)){
			sbm.append(" RwsMinWorkscompView acheieveModel ");
			sbe.append(" and acheieveModel.workId= targetModel.workId ");
			if(inputVO.getYear() != null  && inputVO.getFromDate() != null && inputVO.getToDate() == null){
				sbe.append(" and acheieveModel.dateOfCompletion >= :year ");
			}else if(inputVO.getFromDate() != null && inputVO.getToDate() != null){

				sbe.append(" and acheieveModel.dateOfCompletion between :fromDate and :toDate ");
			}
			
		}
		
		if(inputVO.getStressedHabitationYearsList() != null && inputVO.getStressedHabitationYearsList().size() > 0)
			sbe.append(" and trim(stressedModel.year) in (:stressedYear) ");
		
		if (inputVO.getFilterType() != null && inputVO.getFilterType().trim().length() > 0	&& inputVO.getFilterValue() != null
				&& inputVO.getFilterValue().trim().length() > 0) {
			if (inputVO.getFilterType().equalsIgnoreCase("district")) {
				sbe.append(" and trim(model1.dCode) =:locationValue ");
			} else if (inputVO.getFilterType().equalsIgnoreCase("constituency")) {
				sbe.append(" and trim(model1.constituencyCode) =:locationValue ");
			} else if (inputVO.getFilterType().equalsIgnoreCase("mandal")) {
				sbe.append(" and trim(model1.mCode) =:locationValue ");
			}
		}if(inputVO.getDistrictValue()!=null && inputVO.getDistrictValue().trim().length()>0){
			sbe.append(" and trim(model1.dCode) =:districtValue ");
		}
		if (inputVO.getLocationType().equalsIgnoreCase("state")) {
			sb.append(" '01','Andra Pradesh',");
			sbe.append(" group by model.coverageStatus");
			sbe.append("  order by model.coverageStatus");
			
		} else if (inputVO.getLocationType().equalsIgnoreCase("district")) {
			sb.append(" model.dCode,model.dName, ");
			sbe.append(" group by model.dCode,model.dName, model.coverageStatus order by model.dCode ,model.coverageStatus ");
		}else if (inputVO.getLocationType().equalsIgnoreCase("constituency")) {
			sb.append(" model1.constituencyCode, model1.contituencyName, ");
			sbe.append("  group by  model1.constituencyCode, model1.contituencyName, model.coverageStatus order by model1.constituencyCode,model.coverageStatus");
		} else if (inputVO.getLocationType().equalsIgnoreCase("mandal")) {
			sb.append(" model1.mCode, model1.mName,");
			sb2.append(", model.dCode,model.dName ");
			sbe.append(" group by  model1.mCode, model1.mName,model.dCode,model.dName, model.coverageStatus order by model1.mCode ,model.dCode,model.coverageStatus");
		}
	
		sb.append(sb2.toString()).append(sbm.toString()).append(sbe.toString());
		Query query = getSession().createQuery(sb.toString());
		
		if (inputVO.getFromDate() != null && inputVO.getToDate() != null) {
			query.setDate("fromDate", inputVO.getFromDate());
			query.setDate("toDate", inputVO.getToDate());
		} else if (inputVO.getYear() != null && inputVO.getFromDate() != null && inputVO.getToDate() == null) {
			query.setDate("year", inputVO.getFromDate());
		}
		if(inputVO.getStressedHabitationYearsList() != null && inputVO.getStressedHabitationYearsList().size() > 0){
			query.setParameterList("stressedYear", inputVO.getStressedHabitationYearsList());
		}
		if(inputVO.getLocationName()!= null && inputVO.getLocationName().trim().length()>0){
			
			query.setParameter("locationName", inputVO.getLocationName());
		}
		if(inputVO.getDistrictValue()!=null && inputVO.getDistrictValue().trim().length()>0){
			query.setParameter("districtValue",inputVO.getDistrictValue());
		}
		if (inputVO.getFilterValue() != null && inputVO.getFilterValue().trim().length() > 0) {
			query.setParameter("locationValue", inputVO.getFilterValue().trim());
		}
		return query.list();
	}

	

}
