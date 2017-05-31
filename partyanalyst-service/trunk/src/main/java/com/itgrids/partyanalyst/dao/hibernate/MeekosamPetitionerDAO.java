package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMeekosamPetitionerDAO;
import com.itgrids.partyanalyst.model.MeekosamPetitioner;

public class MeekosamPetitionerDAO extends GenericDaoHibernate<MeekosamPetitioner, Long> implements IMeekosamPetitionerDAO {
	public MeekosamPetitionerDAO() {
		super(MeekosamPetitioner.class);
	}
	public List<Object[]> getPetitionerDetailsByCardNoMobileNoAadharNo(String cardNo,String type){
		StringBuilder sb = new StringBuilder();
		sb.append(" select " +
				  " meekosamPetitioner.meekosamPetitionerId, " +//0
				  " meekosamPetitioner.name, " +//1
				  " meekosamPetitioner.relativeName, " +//2
				  " meekosamPetitioner.dateOfBirth, " +//3
				  " meekosamPetitioner.gender, " +//4
				  " meekosamPetitioner.houseNo, " +//5
				  " meekosamPetitioner.aadharNo, " +//6
				  " meekosamPetitioner.voterCardNo, " +//7
				  " meekosamPetitioner.mobileNo, " +//8
				  " meekosamPetitioner.emailId, " +//9
				  " meekosamPetitioner.meekosamOccupationId, " +//10
				  " meekosamPetitioner.meekosamCasteCategoryId, " +//11
				  " meekosamPetitioner.meekosamArgeeCategoryId, " +//12
				  " meekosamPetitioner.meekosamAnnualIncomeId, " +//13
				  " meekosamPetitioner.duration, " +//14
				  " district.districtId, " +//15
				  " tehsil.tehsilId, " +//16
				  " localElectionBody.localElectionBodyId, " +//17
				  " panchayat.panchayatId, " +//18
				  " ward.constituencyId, " +//19
				  " hamlet.hamletId, " +//20
				  " meekosamPetitioner.age " +//21
				  " from " +
				  " MeekosamPetitioner meekosamPetitioner " +
				  " left outer join meekosamPetitioner.userAddress userAddress " +
				  " left outer join userAddress.district district " +
				  " left outer join userAddress.tehsil tehsil " +
				  " left outer join userAddress.localElectionBody localElectionBody " +
				  " left outer join userAddress.panchayat panchayat " +
				  " left outer join userAddress.ward ward " +
				  " left outer join userAddress.hamlet hamlet " +
				  " where meekosamPetitioner.isDeleted = 'N' ");
		if(type != null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("voter")){
			sb.append(" and meekosamPetitioner.voterCardNo = :cardNo ");
		}else if(type != null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("aadhar")){
			sb.append(" and meekosamPetitioner.aadharNo = :cardNo ");
		}else if(type != null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("mobile")){
			sb.append(" and meekosamPetitioner.mobileNo = :cardNo ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(type != null && !type.trim().isEmpty()){
			query.setParameter("cardNo", cardNo);
		}
		return query.list();
	}
}
