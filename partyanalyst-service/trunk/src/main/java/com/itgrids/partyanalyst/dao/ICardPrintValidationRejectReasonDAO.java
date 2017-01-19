package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CardPrintValidationRejectReason;

public interface ICardPrintValidationRejectReasonDAO extends GenericDao<CardPrintValidationRejectReason,Long>{
	
	public List<Long> checkRejectReasonsByCardPrintValidationId(Long cardPrintValidationId);
	public Integer deleteCardPrintValidationRejectReasonIds(List<Long> ids);
}
