package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreCardPrintDAO;
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
		"        model.cardPrintVendorId,model.boxNo" +//14
		" from   TdpCadreCardPrint model " +
		" where  model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 and " +
		"        model.memberShipId = :memberShipId " );
		query.setParameter("memberShipId",memberShipId );
		return query.list();
	}
	
 }
