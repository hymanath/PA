package com.itgrids.partyanalyst.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.PartyInfoVO;
import com.itgrids.partyanalyst.dto.PartyResultInfoVO;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.impl.PartyResultService;
import com.itgrids.partyanalyst.util.DummyPartyResultsData;
import com.itgrids.partyanalyst.utils.ElectionScopeLevelEnum;
//5
public class PartyResultServiceTest {


	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private INominationDAO nominationDAO;

	private IElectionScopeDAO electionScopeDAO;
	private IElectionDAO electionDAO;
	
	@Before
	public void init(){
		constituencyElectionResultDAO = EasyMock.createMock(IConstituencyElectionResultDAO.class);
		constituencyElectionDAO = EasyMock.createMock(IConstituencyElectionDAO.class);
		nominationDAO = EasyMock.createMock(INominationDAO.class);
		electionScopeDAO = EasyMock.createMock(IElectionScopeDAO.class);
		electionDAO = EasyMock.createMock(IElectionDAO.class);
		
	}
	
	@Test
	public void testPartyResultsInfo(){

		PartyResultService service = new PartyResultService();
		List<ElectionScope> electionScopeList = new ArrayList<ElectionScope>();
		electionScopeList.add(new ElectionScope(new Long(1)));
		EasyMock.expect(electionScopeDAO.findByTypeIdCountryIdStateId(new Long(2),new Long(1),new Long(1))).andReturn(electionScopeList);
		EasyMock.replay(electionScopeDAO);
		service.setElectionScopeDAO(electionScopeDAO);
		Election election1 = new Election(new Long(1));
		election1.setElectionYear("2009");
		List<Election> elections = new ArrayList<Election>();
		elections.add(election1);
		
		EasyMock.expect(electionDAO.findByElectionScope(electionScopeList.get(0).getElectionScopeId())).andReturn(elections);
		EasyMock.replay(electionDAO);
		service.setElectionDAO(electionDAO);
		List<PartyResultInfoVO> returns = new ArrayList<PartyResultInfoVO>();
		for(Election election: elections){
			List<ConstituencyElection> dummyData = DummyPartyResultsData.getConstituencyElectionList( election);
			EasyMock.expect(constituencyElectionDAO.findByElection(election.getElectionId())).andReturn(dummyData);
			EasyMock.replay(constituencyElectionDAO);

			
			for(ConstituencyElection constituencyElection: dummyData){
				Set<Nomination> set = constituencyElection.getNominations();
				List<Nomination> list = new ArrayList<Nomination>();
				list.addAll(set);
				EasyMock.expect(nominationDAO.findByConstituencyElection(constituencyElection.getConstiElecId())).andReturn(list);
			}
			EasyMock.replay(nominationDAO);
			service.setNominationDAO(nominationDAO);
			
			List<ConstituencyElectionResult> constElectionResult = new ArrayList<ConstituencyElectionResult>();
			ConstituencyElectionResult obj1 = new ConstituencyElectionResult();obj1.setConstiElecResultId(1l);obj1.setValidVotes(1250d);
			ConstituencyElectionResult obj2 = new ConstituencyElectionResult();obj2.setConstiElecResultId(2l);obj2.setValidVotes(2250d);
			ConstituencyElectionResult obj3 = new ConstituencyElectionResult();obj3.setConstiElecResultId(3l);obj3.setValidVotes(3250d);
			ConstituencyElectionResult obj4 = new ConstituencyElectionResult();obj4.setConstiElecResultId(4l);obj4.setValidVotes(4250d);
			ConstituencyElectionResult obj5 = new ConstituencyElectionResult();obj5.setConstiElecResultId(5l);obj5.setValidVotes(1750d);
			constElectionResult.add(obj1);
			constElectionResult.add(obj2);
			constElectionResult.add(obj3);
			constElectionResult.add(obj4);
			constElectionResult.add(obj5);
			EasyMock.expect(constituencyElectionResultDAO.findByConstituencyElections("1,2,3,4,5")).andReturn(constElectionResult);
			EasyMock.replay(constituencyElectionResultDAO);
			service.setConstituencyElectionDAO(constituencyElectionDAO);
			service.setConstituencyElectionResultDAO(constituencyElectionResultDAO);
		}
		
		returns = service.getPartyResultsInfo("TDP", new Long(2), new Long(1), new Long(1), null, null, ElectionScopeLevelEnum.STATE_LEVEL);
		PartyResultInfoVO actual = returns.get(0);
		Assert.assertEquals("TDP", actual.getPartyInfoVO().getPartyShortName());
		Assert.assertEquals("INC", actual.getOppositionPartyInfo().get(0).getPartyShortName());
	}
}
