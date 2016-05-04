package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrSurveyCandidateQuestionDAO;
import com.itgrids.partyanalyst.model.HomePageQuestion;
import com.itgrids.partyanalyst.model.IvrSurveyCandidateQuestion;

public class IvrSurveyCandidateQuestionDAO extends GenericDaoHibernate<IvrSurveyCandidateQuestion, Long> implements IIvrSurveyCandidateQuestionDAO {

    public IvrSurveyCandidateQuestionDAO(){
    	super(IvrSurveyCandidateQuestion.class);
    }
}
