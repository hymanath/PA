package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreCardPrintDAO;
import com.itgrids.partyanalyst.dto.PrintVO;
import com.itgrids.partyanalyst.model.TdpCadreCardPrint;

public class TdpCadreCardPrintDAO extends GenericDaoHibernate<TdpCadreCardPrint, Long> implements ITdpCadreCardPrintDAO{

	public TdpCadreCardPrintDAO() {
		super(TdpCadreCardPrint.class);
	}
	
	public List<Object[]> getCardPrintDetailsByMemberShipId(String memberShipId){
		
		Query query = getSession().createQuery("" +
		" select model.tdpCadreId , model.memberShipId , model.cadreName , model.imagePath," +//3
		"        model.districtName , model.constituencyName , model.mandalName , model.panchayatName," +//7
		"        model.muncipalityName,model.wardName,model.boothName,model.areaCovered,model.houseNo," +//12
		"        model.boxNo , model.outerBoxNo , model.cardPrintVendorId " +//15
		" from   TdpCadreCardPrint model " +
		" where  model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 and " +
		"        model.memberShipId = :memberShipId " );
		query.setParameter("memberShipId",memberShipId );
		return query.list();
	}
	
	public List<Object[]> getStatusWisePrintingCardsCounts(Long stateId,Long vendorId,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select model1.printStatus.printStatusId," +
					" count(distinct model.tdpCadreCardPrintId)" +
					" from TdpCadreCardPrint model,ConstituencyPrintStatus model1" +
					" where model.constituency.constituencyId = model1.constituency.constituencyId");
		if(fromDate != null && toDate != null)
			sb.append(" and date(model1.updatedTime) between :fromDate and :toDate");
		
		if(stateId != null && stateId.longValue() == 1l)
			sb.append(" and model1.constituency.district.districtId between 11 and 23");
		else if(stateId != null && stateId.longValue() == 36l)
			sb.append(" and model1.constituency.district.districtId between 1 and 10");
		
		if(vendorId != null && vendorId.longValue() > 0l)
			sb.append(" and model1.cardPrintVendor.cardPrintVendorId = :vendorId");
		
		sb.append(" group by model1.printStatus.printStatusId" +
					" order by model1.printStatus.printStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		if(vendorId != null && vendorId.longValue() > 0l)
			query.setParameter("vendorId", vendorId);
		
		return query.list();
	}
	
	public List<Object[]> getBoxWisePrintingDispatchDetails(Long vendorId,Long districtId,Long constituencyId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.boxNo," +
						" tehsil.tehsilId,tehsil.tehsilName," +
						" leb.localElectionBodyId,leb.name," +
						" panchayat.panchayatId,panchayat.panchayatName," +
						" ward.constituencyId,ward.name," +
						" count(model.tdpCadreCardPrintId)" +
						" from TdpCadreCardPrint model" +
						" left join model.tehsil tehsil" +
						" left join model.localElectionBody leb" +
						" left join model.panchayat panchayat" +
						" left join model.ward ward" +
						" where");
		if(vendorId != null && vendorId > 0l)
			sb.append(" model.cardPrintVendor.cardPrintVendorId = :vendorId");
		if(districtId != null && districtId > 0l)
			sb.append(" and model.district.districtId = :districtId");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model.constituency.constituencyId = :constituencyId");
		
		sb.append(" group by model.boxNo");
		
		Query query = getSession().createQuery(sb.toString());
		if(vendorId != null && vendorId > 0l)
			query.setParameter("vendorId", vendorId);
		if(districtId != null && districtId > 0l)
			query.setParameter("districtId", districtId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	public Integer updateTdpCadreCardPrintDataById(PrintVO printVO) {
		Query query = getSession().createQuery(
		 " update TdpCadreCardPrint model " +
		 " set    model.printTime= :printTime, model.serialNumber = :serialNumber,model.printStatus = :printStatus , model.printCode = :printCode , " +
		 "        model.printDesc = :printDesc ,model.printerSerialNumber = :printerSerialNumber , model.boxNo = :boxNo , model.pcNo = :pcNo , model.outerBoxNo = :outerBoxNo," +
		 "        model.updatedTime =:updatedTime  " +
		 " where  model.tdpCadreCardPrintId=:tdpCadreCardPrintId ");
		
		query.setParameter("tdpCadreCardPrintId", printVO.getTdpCadreCardPrintId());
		
		query.setTimestamp("printTime", printVO.getPrintTime());
		query.setParameter("serialNumber",printVO.getSerialNumber());
		query.setParameter("printStatus",printVO.getPrintStatus());
		query.setParameter("printCode",printVO.getPrintCode());
		query.setParameter("printDesc",printVO.getPrintDesc());
		query.setParameter("printerSerialNumber",printVO.getPrinterSerialNumber());
		query.setParameter("boxNo",printVO.getBoxNo());
		query.setParameter("pcNo",printVO.getPcNo());
		query.setParameter("outerBoxNo",printVO.getOuterBoxNo());
		query.setTimestamp("updatedTime", printVO.getUpdatedTime());
		
		return query.executeUpdate();
	}
	
	public List<Object[]> postVerificationCadreData(Long constituencyId){
		
		Query query = getSession().createQuery("" +
		" select model.tdpCadreCardPrintId , model.tdpCadreId ,model.memberShipId," +//2
		"        model.cadreName , model.imagePath ,model.mobileNo , " +//5
		"        model.locationType , model.districtName , model.constituencyName ," +//8
		"        model.mandalName , model.muncipalityName , " +//10
		"        model.panchayatName , model.wardName," +//12
		"        model.constituencyType  " +//13
		" from   TdpCadreCardPrint model " +
		" where  model.constituencyId = :constituencyId ");
		query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	public List<Object[]> getPrintPushedConstituencies(){
		Query query = getSession().createQuery("" +
		" select distinct model.constituency.constituencyId , model.constituency.name " +
		" from  TdpCadreCardPrint model ");
		return query.list();
	}
	
	public void flushAndclearSession(){
		getSession().flush();
		getSession().clear();
	}
	
	public Long getTotalCadreInConstituency(Long vendorId , Long constituencyId){
		
		Query query = getSession().createQuery("" +
		" select count(model.tdpCadreCardPrintId)  " +
		" from   TdpCadreCardPrint model where model.cardPrintVendor.cardPrintVendorId = :vendorId and model.constituency.constituencyId = :constituencyId");
		
		query.setParameter("vendorId", vendorId);
		query.setParameter("constituencyId", constituencyId);
		
		return (Long)query.uniqueResult();
	}
	
 }
