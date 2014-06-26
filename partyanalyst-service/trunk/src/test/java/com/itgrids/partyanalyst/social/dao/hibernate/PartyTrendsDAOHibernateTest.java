package com.itgrids.partyanalyst.social.dao.hibernate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.appfuse.dao.BaseDaoTestCase;
import org.jfree.util.Log;



import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.dto.BaseCandidateResultVO;
import com.itgrids.partyanalyst.dto.BasePartyIdsMAp;
import com.itgrids.partyanalyst.service.ISoundexService;

public class PartyTrendsDAOHibernateTest extends BaseDaoTestCase {
   // @Autowired
	private IPartyTrendsDAO partyTrendsDAO;
	
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;

	/*private IBoothPollingUpdatesDAO  boothPollingUpdatesDAO;
	private ISmsWebServiceHandler smsWebServiceHandlerImpl;*/
	
	
   private ISoundexService soundexService;
   
   
   //getMappedVoterDetailsByUsingSoundexByPanchayatId

   
   
	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
	return boothPublicationVoterDAO; 
}





public void setBoothPublicationVoterDAO(
		IBoothPublicationVoterDAO boothPublicationVoterDAO) {
	this.boothPublicationVoterDAO = boothPublicationVoterDAO;
}





	public IPartyTrendsDAO getPartyTrendsDAO() {
		return partyTrendsDAO;
	}





	public ISoundexService getSoundexService() {
		return soundexService;
	}





	public void setSoundexService(ISoundexService soundexService) {
		this.soundexService = soundexService;
	}





	public void setPartyTrendsDAO(IPartyTrendsDAO partyTrendsDAO) {
		this.partyTrendsDAO = partyTrendsDAO;
	}


	private  IDelimitationConstituencyDAO delimitationConstituencyDAO;

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public void testSoundex()
	
	{//
	/*List<Object[]> voters=	boothPublicationVoterDAO.checkVoterInState(10L, "Anilkumar Ravula", "Venkateswarlu Ravula", "M", 23);
		
		System.out.println(voters.size());*/
		ArrayList<Long> list=new ArrayList<Long>();
		/*list.add(109L);
		list.add(207L);
		list.add(345L);
		list.add(111L);
		list.add(314L);
		list.add(50L);
		list.add(210L);
		list.add(73L);
		list.add(100L);
		list.add(213L);
		list.add(348L);
		list.add(352L);
		list.add(114L);
		list.add(353L);
		list.add(121L);
		list.add(344L);
		list.add(13L);
		list.add(360L);
		list.add(350L);
		list.add(250L);
		list.add(251L);
		list.add(178L);
		list.add(206L);
		list.add(53L);
		list.add(329L);
		list.add(245L);
		list.add(47L);
		list.add(11L);
		list.add(343L);
		list.add(286L);
		list.add(257L);
		list.add(226L);
		list.add(169L);
		list.add(125L);
		list.add(93L);
		list.add(138L);
		list.add(196L);
		list.add(60L);
		list.add(187L);
		list.add(306L);
		list.add(102L);
		list.add(105L);
		list.add(321L);
		list.add(10L);
		list.add(133L);
		list.add(104L);
		list.add(227L);
		list.add(156L);
		list.add(331L);
		list.add(116L);
		list.add(91L);
		list.add(221L);
		list.add(23L);
		list.add(117L);
		list.add(163L);
		list.add(180L);
		list.add(244L);
		list.add(367L);
		list.add(181L);
		list.add(79L);
		list.add(113L);
		list.add(174L);
		list.add(242L);
		list.add(203L);
		list.add(177L);
		list.add(271L);
		list.add(92L);
		list.add(108L);
		list.add(56L);
		list.add(124L);
		list.add(361L);
		list.add(153L);
		list.add(212L);
		list.add(325L);
		list.add(147L);
		list.add(275L);
		list.add(101L);
		list.add(308L);
		list.add(300L);
		list.add(228L);
		list.add(351L);
		list.add(249L);
		list.add(355L);
		list.add(168L);
		list.add(63L);
		list.add(305L);
		list.add(82L);
		list.add(137L);
		list.add(120L);
		list.add(356L);
		list.add(167L);
		list.add(199L);
		list.add(74L);
		list.add(205L);
		list.add(65L);
		list.add(232L);
		list.add(176L);
		list.add(225L);
		list.add(270L);
		list.add(241L);
		list.add(335L);
		list.add(315L);
		list.add(61L);
		list.add(51L);
		list.add(173L);
		list.add(319L);
		list.add(54L);
		list.add(40L);
		list.add(222L);
		list.add(224L);
		list.add(52L);
		list.add(362L);
		list.add(208L);
		list.add(135L);
		list.add(192L);
		list.add(103L);
		list.add(194L);
		list.add(211L);
		list.add(49L);
		list.add(358L);
		list.add(272L);
		list.add(243L);
		list.add(338L);
		list.add(290L);
		list.add(68L);
		list.add(193L);
		list.add(267L);
		list.add(107L);
		list.add(326L);
		list.add(215L);
		list.add(303L);
		list.add(316L);
		list.add(349L);
		list.add(62L);
		list.add(330L);
		list.add(341L);
		list.add(288L);
		list.add(248L);
		list.add(81L);
		list.add(291L);
		list.add(5L);
		list.add(342L);
		list.add(223L);
		list.add(70L);
		list.add(285L);
		list.add(255L);
		list.add(94L);
		list.add(304L);
		list.add(97L);
		list.add(112L);
		list.add(36L);
		list.add(231L);
		list.add(261L);
		list.add(340L);
		list.add(134L);
		list.add(20L);
		list.add(262L);
		list.add(307L);
		list.add(84L);
		list.add(309L);
		list.add(237L);
		list.add(195L);
		list.add(327L);
		list.add(16L);
		list.add(136L);
		list.add(69L);
		list.add(57L);
		list.add(87L);
		list.add(253L);
		list.add(71L);
		list.add(254L);
		list.add(31L);
		list.add(214L);
		list.add(273L);
		list.add(186L);
		list.add(289L);
		list.add(218L);
		list.add(260L);
		list.add(80L);
		list.add(2L);
		list.add(55L);
		list.add(159L);
		list.add(209L);
		list.add(279L);
		list.add(24L);
		list.add(238L);
		list.add(8L);
		list.add(336L);
		list.add(184L);
		list.add(127L);
		list.add(15L);
		list.add(217L);
		list.add(317L);
		list.add(191L);
		list.add(160L);
		list.add(366L);
		list.add(46L);
		list.add(368L);
		list.add(59L);
		list.add(369L);
		list.add(359L);
		list.add(172L);
		list.add(35L);
		list.add(229L);
		list.add(328L);
		list.add(339L);
		list.add(318L);
		list.add(219L);
		list.add(294L);
		list.add(85L);
		list.add(298L);
		list.add(333L);
		list.add(58L);
		list.add(347L);
		list.add(337L);
		list.add(152L);
		list.add(18L);
		list.add(364L);
		list.add(67L);
		list.add(236L);
		list.add(332L);
		list.add(43L);
		list.add(280L);
		list.add(252L);
		list.add(310L);
		list.add(284L);
		list.add(182L);
		list.add(170L);
		list.add(34L);
		list.add(77L);
		list.add(149L);
		list.add(276L);
		list.add(39L);
		list.add(4L);
		list.add(278L);
		list.add(320L);
		list.add(21L);
		list.add(263L);
		list.add(6L);
		list.add(295L);
		list.add(264L);
		list.add(281L);
		list.add(323L);
		list.add(334L);
		list.add(26L);
		list.add(140L);
		list.add(146L);
		list.add(301L);
		list.add(1L);
		list.add(277L);
		list.add(233L);
		list.add(365L);
		list.add(41L);
		list.add(354L);
		list.add(322L);
		list.add(7L);
		list.add(299L);
		list.add(313L);
		list.add(324L);
		list.add(357L);
		list.add(32L);
		list.add(185L);
		list.add(258L);
		list.add(129L);*/
		list.add(207L);
		list.add(213L);
		list.add(109L);
		list.add(114L);
		list.add(13L);
		list.add(50L);
		list.add(206L);
		list.add(178L);
		list.add(353L);
		list.add(251L);
		list.add(245L);
		list.add(350L);
		list.add(11L);
		list.add(286L);
		list.add(257L);
		list.add(343L);
		list.add(93L);
		list.add(250L);
		list.add(344L);
		list.add(169L);
		list.add(53L);
		list.add(226L);
		list.add(47L);
		list.add(133L);
		list.add(352L);
		list.add(321L);
		list.add(156L);
		list.add(348L);
		list.add(10L);
		list.add(163L);
		list.add(180L);
		list.add(121L);
		list.add(221L);
		list.add(187L);
		list.add(91L);
		list.add(113L);
		list.add(174L);
		list.add(181L);
		list.add(345L);
		list.add(271L);
		list.add(242L);
		list.add(360L);
		list.add(177L);
		list.add(329L);
		list.add(125L);
		list.add(147L);
		list.add(92L);
		list.add(367L);
		list.add(212L);
		list.add(275L);
		list.add(308L);
		list.add(300L);
		list.add(56L);
		list.add(23L);
		list.add(325L);
		list.add(116L);
		list.add(168L);
		list.add(249L);
		list.add(74L);
		list.add(210L);
		list.add(314L);
		list.add(137L);
		list.add(120L);
		list.add(167L);
		list.add(351L);
		list.add(232L);
		list.add(51L);
		list.add(270L);
		list.add(79L);
		list.add(176L);
		list.add(361L);
		list.add(305L);
		list.add(228L);
		list.add(203L);
		list.add(173L);
		list.add(65L);
		list.add(227L);
		list.add(124L);
		list.add(73L);
		list.add(153L);
		list.add(222L);
		list.add(135L);
		list.add(224L);
		list.add(272L);
		list.add(315L);
		list.add(243L);
		list.add(101L);
		list.add(103L);
		list.add(267L);
		list.add(211L);
		list.add(193L);
		list.add(326L);
		list.add(303L);
		list.add(316L);
		list.add(338L);
		list.add(362L);
		list.add(215L);
		list.add(341L);
		list.add(49L);
		list.add(241L);
		list.add(52L);
		list.add(335L);
		list.add(349L);
		list.add(288L);
		list.add(199L);
		list.add(105L);
		list.add(342L);
		list.add(94L);
		list.add(285L);
		list.add(255L);
		list.add(81L);
		list.add(248L);
		list.add(70L);
		list.add(107L);
		list.add(262L);
		list.add(307L);
		list.add(231L);
		list.add(355L);
		list.add(237L);
		list.add(134L);
		list.add(97L);
		list.add(100L);
		list.add(36L);
		list.add(117L);
		list.add(244L);
		list.add(84L);
		list.add(5L);
		list.add(261L);
		list.add(291L);
		list.add(186L);
		list.add(61L);
		list.add(253L);
		list.add(358L);
		list.add(254L);
		list.add(223L);
		list.add(192L);
		list.add(136L);
		list.add(273L);
		list.add(82L);
		list.add(327L);
		list.add(20L);
		list.add(290L);
		list.add(319L);
		list.add(87L);
		list.add(217L);
		list.add(71L);
		list.add(205L);
		list.add(108L);
		list.add(24L);
		list.add(279L);
		list.add(8L);
		list.add(31L);
		list.add(356L);
		list.add(209L);
		list.add(336L);
		list.add(15L);
		list.add(225L);
		list.add(304L);
		list.add(317L);
		list.add(138L);
		list.add(260L);
		list.add(229L);
		list.add(331L);
		list.add(366L);
		list.add(59L);
		list.add(218L);
		list.add(309L);
		list.add(112L);
		list.add(63L);
		list.add(359L);
		list.add(194L);
		list.add(102L);
		list.add(57L);
		list.add(46L);
		list.add(127L);
		list.add(60L);
		list.add(172L);
		list.add(219L);
		list.add(369L);
		list.add(160L);
		list.add(16L);
		list.add(328L);
		list.add(214L);
		list.add(339L);
		list.add(69L);
		list.add(318L);
		list.add(85L);
		list.add(306L);
		list.add(340L);
		list.add(170L);
		list.add(149L);
		list.add(280L);
		list.add(252L);
		list.add(298L);
		list.add(333L);
		list.add(368L);
		list.add(77L);
		list.add(62L);
		list.add(191L);
		list.add(284L);
		list.add(347L);
		list.add(80L);
		list.add(54L);
		list.add(67L);
		list.add(2L);
		list.add(43L);
		list.add(364L);
		list.add(6L);
		list.add(58L);
		list.add(184L);
		list.add(278L);
		list.add(294L);
		list.add(263L);
		list.add(332L);
		list.add(264L);
		list.add(281L);
		list.add(323L);
		list.add(334L);
		list.add(35L);
		list.add(159L);
		list.add(301L);
		list.add(337L);
		list.add(68L);
		list.add(195L);
		list.add(289L);
		list.add(4L);
		list.add(140L);
		list.add(21L);
		list.add(330L);
		list.add(320L);
		list.add(7L);
		list.add(185L);
		list.add(354L);
		list.add(295L);
		list.add(129L);
		list.add(322L);
		list.add(152L);
		list.add(310L);
		list.add(32L);
		list.add(299L);
		list.add(313L);
		list.add(34L);
		list.add(324L);
		list.add(1L);
		list.add(39L);
		list.add(55L);
		list.add(258L);
		list.add(18L);
		list.add(40L);
		list.add(276L);
		list.add(182L);
		list.add(277L);
		list.add(146L);
		list.add(365L);
		//Long constId=207L;
		System.out.println(list.size());
		soundexService.mapAreaInchargersForAConst(207L);
		/*for (Long long1 : list) {
			soundexService.mapAreaInchargersForAConst(long1);
		}*/
		
		
	//System.exit(0);
	
	
	}
	
	
	
	/*public void testGetAllAnswersForTheQuestion() throws Exception{
		 Map<Long, List<PartyTrendsVO>> map =new HashMap<Long,List<PartyTrendsVO> >();
		List<Long> cost = new ArrayList<Long>();
		cost.add(341L);
		cost.add(267L);
		System.out.println(partyTrendsDAO.hashCode());
	//	partyTrendsDAO.loadEntitiesForXl(cost);
List<Object[]> obj=(List<Object[]>) partyTrendsDAO.loadEntitiesForXl(cost);
		
		for (Object[] objects : obj) {
			
			Long constId=Long.valueOf(objects[0].toString());
			System.out.print(constId);
			System.out.println(objects[1].toString());
			PartyTrendsVO vo =new PartyTrendsVO();
			vo.setConstituencyId(constId);
			vo.setConstituencyName(objects[1].toString());
			vo.setName(objects[2].toString());
			vo.setPervTrenzWt(Float.valueOf(objects[3].toString()));
			vo.setPrpWt(Float.valueOf(objects[4].toString()));
			vo.setTotalWt(Float.valueOf(objects[6].toString()));
			vo.setYoungVotersWt(Float.valueOf(objects[5].toString()));
			if(map.containsKey(constId))
			{
				map.get(constId).add(vo);
			}else
			{
				List<PartyTrendsVO> l = new ArrayList<PartyTrendsVO>();
				l.add(vo);
				map.put(constId, l);
			}
			
		}
		System.out.println(map.size());
		generateXL(map);
	}
	public void  generateXL(Map<Long,List<PartyTrendsVO>> map) throws Exception
	   {
		 String filename= "Report"+ new Random().nextInt(10000000)+".xls";
		   FileOutputStream fileOut =    new FileOutputStream(IConstants.STATIC_CONTENT_FOLDER_URL+filename);
		   Set<Long> keys = map.keySet();
		    HSSFWorkbook workbook=new HSSFWorkbook();
		    HSSFSheet sheet =null;
		    
		  for (Long long1 : keys) {
			List<PartyTrendsVO> voa=  map.get(long1);
			
			   sheet =  workbook.createSheet(voa.get(0).getConstituencyName());
			    sheet.setColumnWidth(0, 4800);
				sheet.setColumnWidth(1, 4800);
				sheet.setColumnWidth(2, 3000);
				sheet.setColumnWidth(3, 3000);
				sheet.setColumnWidth(4, 3000);
				sheet.setColumnWidth(5, 3000);
			  int cnt=2;
			  HSSFRow rowhead =sheet.createRow(0);
			  Cell cell = rowhead.createCell(0);
				 
		       cell.setCellValue("Constituency");
		       
		       cell = rowhead.createCell(1);
		       cell.setCellValue("Name");
		       cell = rowhead.createCell(2);
		       cell.setCellValue("PervTrenzWt");
		       cell = rowhead.createCell(3);
		       cell.setCellValue("PrpWt");
		       cell = rowhead.createCell(4);
		       cell.setCellValue("YoungVotersWt");
		       
		       cell = rowhead.createCell(5);
		       cell.setCellValue("TotalWt");
		       
			for (PartyTrendsVO partyTrendsVO : voa) {
				  rowhead =sheet.createRow(cnt);
				 
				   cell = rowhead.createCell(0);
				 
			       cell.setCellValue(partyTrendsVO.getConstituencyName());
			       
			       cell = rowhead.createCell(1);
			       cell.setCellValue(partyTrendsVO.getName());
			       cell = rowhead.createCell(2);
			       cell.setCellValue(partyTrendsVO.getPervTrenzWt());
			       cell = rowhead.createCell(3);
			       cell.setCellValue(partyTrendsVO.getPrpWt());
			       cell = rowhead.createCell(4);
			       cell.setCellValue(partyTrendsVO.getYoungVotersWt());
			       
			       cell = rowhead.createCell(5);
			       cell.setCellValue(partyTrendsVO.getTotalWt());
			       cnt++;
			}
		  }
		    workbook.write(fileOut);
			 fileOut.close(); 

			  
		
	   }*/
	
/*	public void testPopulatePriority(){
		List<Long> constiIds = partyTrendsDAO.getConstituencyIds();
		for(Long conId:constiIds){
			List<PartyTrends> trends = partyTrendsDAO.getAllTrends(conId);
			int totalCount = trends.size();
			for(int i =1;i<=totalCount;i++){
				PartyTrends pt = trends.get(i-1);
				Long priorty = new Long((i*100)/totalCount);
				if(priorty.longValue() == 0l)
					priorty = 1l;
				pt.setPriority(priorty);
				partyTrendsDAO.save(pt);
			}
		}
	}*/
	
	/*public void test()
	{
		Map<String,PartyElectionTrendsReportVO> maps = new HashMap<String, PartyElectionTrendsReportVO>();
		
		List<Object[]> prp1=partyTrendsDAO.getPreviousTrendsData(232L);
	//	List<Object[]> prp= partyTrendsDAO.getPreviousForPrp(null);
		for (Object[] objects : prp1) {
			
			PartyElectionTrendsReportVO vo =new PartyElectionTrendsReportVO();
			vo.setElectionYear(Integer.valueOf(objects[0].toString()));
			vo.setTotalVoters(((Double)objects[1]).longValue());
			vo.setTotalVotesPolled(((Double)objects[2]).longValue());
			System.out.println(objects[3].toString()+""+objects[9].toString());
			PartyElectionTrendsReportHelperVO tdpVo = new PartyElectionTrendsReportHelperVO(objects[3]!=null? objects[3].toString():"",objects[5] !=null ?((Double)objects[5]).longValue():0L ,objects[8] !=null ?((Double)objects[8]):0.0);
			PartyElectionTrendsReportHelperVO incVo = new PartyElectionTrendsReportHelperVO(objects[9]!=null? objects[9].toString():"",objects[11] !=null ?((Double)objects[11]).longValue():0L ,objects[12] !=null ?((Double)objects[12]):0.0);
			PartyElectionTrendsReportHelperVO othersVo = new PartyElectionTrendsReportHelperVO(objects[13] !=null ? objects[13].toString():"",objects[14] !=null ?((Double)objects[14]).longValue():0L ,objects[15] !=null ?((Double)objects[15]):0.0);
			PartyElectionTrendsReportHelperVO prpVo= new PartyElectionTrendsReportHelperVO();

			
			vo.setTdpVo(tdpVo);
			vo.setIncVo(incVo);
			vo.setPrpVo(prpVo);
			vo.setOthersVo(othersVo);
			//System.out.println(objects[0].toString());
			maps.put(objects[0].toString(),vo);
		}
		
		//List<Object[]> prp= partyTrendsDAO.getPreviousForPrp(null);
		
		System.out.println(maps.size());
		
		for ( String name: maps.keySet()) {
			System.out.println(name);
			maps.get("2009");
		}
		//System.out.println(prp.size());
		List<Object[]> prp= partyTrendsDAO.getPreviousForPrp(232L);
		for (Object[] objects : prp) {
			String year =objects[0].toString();
			if(maps.containsKey(year))
			{
				PartyElectionTrendsReportHelperVO vo=	maps.get(year).getPrpVo();
				vo.setName(objects[1].toString());
				vo.setVotesEarned(objects[3] !=null ?((Double)objects[3]).longValue():0L);
				vo.setPercentage(objects[4] !=null ?((Double)objects[4]):0.0);
			}
		}
		System.out.println(maps.size());
		for ( String name: maps.keySet()) {
			//System.out.println(name);
			PartyElectionTrendsReportVO vo = maps.get(name);
			ArrayList<Long> l =new ArrayList<Long>();
			
			Long tdp=vo.getTdpVo().getVotesEarned();
		
			Long inc = vo.getIncVo().getVotesEarned();
			 
			Long prp11= vo.getPrpVo().getVotesEarned();
			 
			Long others = vo.getOthersVo().getVotesEarned();
			
			Long max=inc;
			if(prp11!=null && prp11>inc )
				max=prp11;
			else if(others !=null && others>inc )
				max=others;
			vo.getTdpVo().setMarginVotes(tdp-max);
			vo.getTdpVo().setMarginVotesPercentage( (double)(vo.getTdpVo().getMarginVotes()/vo.getTotalVotesPolled())*100);
			 
			 
			//System.out.println(vo);
		}
		System.out.println(maps.size());

	}*/
	/*public void test()
	{
		Map<String,PartyElectionTrendsReportVO> maps = new HashMap<String, PartyElectionTrendsReportVO>();
		List<Object[]> prp1=partyTrendsDAO.getParliamentCountForInc(232L);
	//	List<Object[]> prp= partyTrendsDAO.getPreviousForPrp(null);
		for (Object[] objects : prp1) {
			
			PartyElectionTrendsReportVO vo =new PartyElectionTrendsReportVO();
			vo.setElectionYear(Integer.valueOf(objects[0].toString()));
		//	vo.setTotalVoters(((Double)objects[1]).longValue());
			vo.setTotalVotesPolled(((Double)objects[1]).longValue());
			
		Long d1=	objects[4] !=null ?((Double)objects[4]).longValue():0L;
		Long d2=	objects[8] !=null ?((Double)objects[8]).longValue():0L;
			
			PartyElectionTrendsReportHelperVO tdpVo = new PartyElectionTrendsReportHelperVO(objects[2]!=null? objects[2].toString():"",objects[4] !=null ?((Double)objects[4]).longValue():0L ,objects[5] !=null ?((Double)objects[5]):0.0);
			PartyElectionTrendsReportHelperVO incVo = new PartyElectionTrendsReportHelperVO(objects[6]!=null? objects[6].toString():"",objects[8] !=null ?((Double)objects[8]).longValue():0L ,objects[9] !=null ?((Double)objects[9]):0.0);
			//PartyElectionTrendsReportHelperVO othersVo = new PartyElectionTrendsReportHelperVO(objects[13] !=null ? objects[13].toString():"",objects[14] !=null ?((Double)objects[14]).longValue():0L ,objects[15] !=null ?((Double)objects[15]):0.0);
			PartyElectionTrendsReportHelperVO prpVo= new PartyElectionTrendsReportHelperVO();

			
			vo.setTdpVo(tdpVo);
			vo.setIncVo(incVo);
		//	vo.setPrpVo(prpVo);
			//vo.setOthersVo(othersVo);
			//System.out.println(objects[0].toString());
			maps.put(objects[0].toString(),vo);
		}
		
		//List<Object[]> prp= partyTrendsDAO.getPreviousForPrp(null);
		
		System.out.println(maps.size());
		
		for ( String name: maps.keySet()) {
			System.out.println(name);
			maps.get("2009");
		}
		//System.out.println(prp.size());
		List<Object[]> prp= partyTrendsDAO.getParliamentCountForPrpAndYsr(232L);
		for (Object[] objects : prp) {
			String year =objects[0].toString();
			if(maps.containsKey(year))
			{
				PartyElectionTrendsReportHelperVO vo=	maps.get(year).getPrpVo();
				vo.setName(objects[1].toString());
				vo.setVotesEarned(objects[3] !=null ?((Double)objects[3]).longValue():0L);
				vo.setPercentage(objects[4] !=null ?((Double)objects[4]):0.0);
			}
		}
		System.out.println(maps.size());
		
		for ( String name: maps.keySet()) {
			//System.out.println(name);
			PartyElectionTrendsReportVO vo = maps.get(name);
			 vo.getTdpVo().getVotesEarned();
			 vo.getIncVo().getVotesEarned();
			 vo.getPrpVo().getVotesEarned();
			 vo.getOthersVo().getVotesEarned();
			 
			 
			//System.out.println(vo);
		}
		
	}*/
/*public  void test()
{    
	//Map<Long,Map<Long,PartyElectionTrendsReportHelperVO>> mmap = new HashMap<Long,Map<Long,PartyElectionTrendsReportHelperVO>>();
	List<Object[]> ids = (List<Object[]>)partyTrendsDAO.getPreviousTrendsDataForParleament(null, null);
	Map<Long,PartyElectionTrendsReportVO> maps = new HashMap<Long, PartyElectionTrendsReportVO>();

	System.out.println(ids.size());
	
	for (Object[] object : ids) {
		
	if(maps.containsKey(Long.valueOf(object[0].toString())))
	{
		PartyElectionTrendsReportVO vo =maps.get(Long.valueOf(object[0].toString()));
        vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
        vo.setTotalVoters(((Double)object[1]).longValue());
        vo.setTotalVotesPolled(((Double)object[2]).longValue());
        PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
        voh.setPercentage((Double)object[7]);
        voh.setVotesEarned(((Double)object[4]).longValue());
        if(((Long)object[3]).equals(872L))
        {
     	   voh.setMarginVotes(((Double)object[5]).longValue());
     	   voh.setMarginVotesPercentage((Double.valueOf(object[6].toString())));
     	   vo.setTdpVo(voh);
        }else if(((Long)object[3]).equals(362L))
        {
     	   vo.setIncVo(voh);

        }else if(((Long)object[3]).equals(662L)||((Long)object[3]).equals(1117L)  )
        {
     	   vo.setPrpVo(voh);

        }else {
     	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
     	   if(vo1!=null)
     		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
     	   else 
     	   {
     		   vo1= new PartyElectionTrendsReportHelperVO();
     		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;

     	   }
     		   
     	  vo.setOthersVo(vo1);
        }
		
	}
	else
	{
		PartyElectionTrendsReportVO vo =new PartyElectionTrendsReportVO();
		           vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
		           vo.setTotalVoters(((Double)object[1]).longValue());
		           vo.setTotalVotesPolled(((Double)object[2]).longValue());
		           PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
		           voh.setPercentage((Double)object[7]);
		           voh.setVotesEarned(((Double)object[4]).longValue());
		           if(((Long)object[3]).equals(872L))
		           {
		        	   voh.setMarginVotes((Long)object[5]);
		        	   voh.setMarginVotesPercentage(((Double)object[6]));
		        	   vo.setTdpVo(voh);
		           }else if(((Long)object[3]).equals(362L))
		           {
		        	   vo.setIncVo(voh);

		           }else if(((Long)object[3]).equals(662L)||((Long)object[3]).equals(1117L)  )
		           {
		        	   vo.setPrpVo(voh);

		           }else {
		        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
		        	   if(vo1!=null)
		        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
		        	   else 
		        	   {
		        		   vo1= new PartyElectionTrendsReportHelperVO();
		        		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;

		        	   }
		        		   
		        	   vo.setOthersVo(vo1);
		           }
		           
	maps.put(Long.valueOf(object[0].toString()),vo );
	}
	}
	System.out.println(maps);
	
	for(Long year:maps.keySet())
	{
		PartyElectionTrendsReportVO vo=	maps.get(year);
		
		//vo.getOthersVo().setVotesEarned(vo.getTotalVotesPolled()-( + vo.getIncVo()!=null ?vo.getIncVo().getVotesEarned().longValue():0L));
		vo.getOthersVo().setMarginVotesPercentage((double)((double)vo.getOthersVo().getVotesEarned()/(double)vo.getTotalVotesPolled())*100);
	}
	
	System.out.println(maps);
}*/
	
	/*public  void test()
	{    
		//Map<Long,Map<Long,PartyElectionTrendsReportHelperVO>> mmap = new HashMap<Long,Map<Long,PartyElectionTrendsReportHelperVO>>();
		List<Object[]> ids = (List<Object[]>)partyTrendsDAO.getPreviousTrendsDataForParleament(null, null);
		Map<Long,PartyElectionTrendsReportVO> maps = new HashMap<Long, PartyElectionTrendsReportVO>();

		System.out.println(ids.size());
		
		for (Object[] object : ids) {
			
		if(maps.containsKey(Long.valueOf(object[0].toString())))
		{
			PartyElectionTrendsReportVO vo =maps.get(Long.valueOf(object[0].toString()));
			  vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
	          // vo.setTotalVoters(((Double)object[1]).longValue());
	           vo.setTotalVotesPolled(Long.valueOf(object[1].toString()));
	           PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
	           voh.setPercentage(((Long)object[4]).doubleValue());
	           voh.setVotesEarned(((Long)object[3]).longValue());
	           if(((Long)object[2]).equals(872L))
	           {
	        	   voh.setMarginVotes((Long)object[5]);
	        	   voh.setMarginVotesPercentage(((Double)object[6]));
	        	   vo.setTdpVo(voh);
	           }else if(((Long)object[2]).equals(362L))
	           {
	        	   vo.setIncVo(voh);

	           }else if(((Long)object[2]).equals(662L)||((Long)object[2]).equals(1117L)  )
	           {
	        	   vo.setPrpVo(voh);

	           }else {
	        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
	        	   if(vo1!=null)
	        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Long)object[3]).longValue()) ;
	        	   else 
	        	   {
	        		   vo1= new PartyElectionTrendsReportHelperVO();
	        		   vo1.setVotesEarned(((Long)object[3]).longValue()) ;

	        	   }
	        		   
	        	   vo.setOthersVo(vo1);
	        }
			
		}
		else
		{
			PartyElectionTrendsReportVO vo =new PartyElectionTrendsReportVO();
			           vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
			          // vo.setTotalVoters(((Double)object[1]).longValue());
			           vo.setTotalVotesPolled(Long.valueOf(object[1].toString()));
			           PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
			           voh.setPercentage(((Long)object[4]).doubleValue());
			           voh.setVotesEarned(((Long)object[3]).longValue());
			           if(((Long)object[2]).equals(872L))
			           {
			        	   voh.setMarginVotes((Long)object[5]);
			        	   voh.setMarginVotesPercentage(((Double)object[6]));
			        	   vo.setTdpVo(voh);
			           }else if(((Long)object[2]).equals(362L))
			           {
			        	   vo.setIncVo(voh);

			           }else if(((Long)object[2]).equals(662L)||((Long)object[2]).equals(1117L)  )
			           {
			        	   vo.setPrpVo(voh);

			           }else {
			        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
			        	   if(vo1!=null)
			        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Long)object[3]).longValue()) ;
			        	   else 
			        	   {
			        		   vo1= new PartyElectionTrendsReportHelperVO();
			        		   vo1.setVotesEarned(((Long)object[3]).longValue()) ;

			        	   }
			        		   
			        	   vo.setOthersVo(vo1);
			           }
			           
		maps.put(Long.valueOf(object[0].toString()),vo );
		}
		}
		System.out.println(maps);
		
		for(Long year:maps.keySet())
		{PartyElectionTrendsReportVO vo=maps.get(year);
			Long tdp=vo.getTdpVo()!=null ?vo.getTdpVo().getVotesEarned():0L;
			
			Long inc =vo.getIncVo()!=null ? vo.getIncVo().getVotesEarned():0L;
			 
			Long prp11= vo.getPrpVo()!=null ? vo.getPrpVo().getVotesEarned() :0L;
			 
			Long others = vo.getOthersVo().getVotesEarned();
			
			Long max=inc;
			if(prp11!=null && prp11>inc )
				max=prp11;
			else if(others !=null && others>inc )
				max=others;
			vo.getTdpVo().setMarginVotes(tdp-max);
			vo.getTdpVo().setMarginVotesPercentage( (double)(vo.getTdpVo().getMarginVotes()/vo.getTotalVotesPolled())*100);
			
			//PartyElectionTrendsReportVO vo=	maps.get(year);
			
			//vo.getOthersVo().setVotesEarned(vo.getTotalVotesPolled()-( + vo.getIncVo()!=null ?vo.getIncVo().getVotesEarned().longValue():0L));
			vo.getOthersVo().setPercentage((double)((double)vo.getOthersVo().getVotesEarned()/(double)vo.getTotalVotesPolled())*100);
		}
		
		System.out.println(maps);
	}*/
	/*public  void test()
	{
	//List<Object[]> obj=	partyTrendsDAO.getTotalVotersForConst(232L);
		List<Object[]> obj=new ArrayList<Object[]>();
		try {
			obj = (List<Object[]>) partyTrendsDAO.getPreviousTrendsDataForParleament(null,149L);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	System.out.println(obj.size());

	}*/
	
	
	/*public void testqueryTest()
	
	{
		List<?> helloList=partyTrendsDAO.callStoredProcedure();
		
		System.out.println(helloList.size());
		SmsWebServiceHandlerImpl smsWebServiceHandler = (SmsWebServiceHandlerImpl)smsWebServiceHandlerImpl;
		smsWebServiceHandler.boothPollingUpdatesDAO=boothPollingUpdatesDAO;
		
		BoothPollingUpdatesVO inputVo = new BoothPollingUpdatesVO();
    	inputVo.setMessage("hello");
    	inputVo.setMobileNumber("doctoe");
    	inputVo.setRecievedDate(getDateFromString("05/23/89 12:12:12 AM"));
    	
    	
    	smsWebServiceHandler.saveBoothPollingData(inputVo);
		
	}*/
	/*public  Date getDateFromString(String date)  {
		//String date="05/23/89 12:12:12 AM";
		Date dt=null;
		try {
		String dateFormat="MM/dd/yy HH:mm:ss a";
		  SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		 dt= format.parse(date);
		 System.out.println(dt);
		
		}catch (Exception e) {
			throw new RuntimeException("date format should be "+dt);
		}
		return dt;
	}
	*/
	private static  Map<Long,Long> constIdsMap=new HashMap<Long,Long>();
	private static  Map<Long,Long> totalVotesMap=new HashMap<Long,Long>();

	public void testInsert(int x) {
		if(true)
			return;
		String insertFor="Parliament";
		
	/*	int x=partyTrendsDAO.updateCandidateReult(362L, 259L, Double.valueOf("100".toString()));

		System.out.println(x);*/
	//int x =partyTrendsDAO.updateCandidateReult(362L, 259L, Double.valueOf("100".toString()),40761L);
		/*int x =partyTrendsDAO.updateCandidateReultForIndependents("DHANENDRA SAHU", 259L, Double.valueOf("100".toString()),40761L);
		System.out.println(x);
		
		*
		*/
		InputStream in=null;
		try {
			
			//reading paroperties file
			in = PartyTrendsDAOHibernateTest.class.getClassLoader().getResourceAsStream("electionresults2014.properties");
		    Properties prop = new  Properties();
		    prop.load(in);
		    Long electionId=null;
		    Long electionScopeId=null;
			Long stateId=Long.valueOf((String) prop.get("stateId"));
            boolean itIsForParliament=false;
		    if(!insertFor.equalsIgnoreCase("Parliament"))
		    {
		    	
		    	electionId=Long.valueOf((String) prop.get("electionId"));
		    	electionScopeId=Long.valueOf((String) prop.get("electionScopeId"));
		    }
		    else
		    {
		    	
		    	electionId=Long.valueOf((String) prop.get("electionIdForPaliament"));
		    	electionScopeId=Long.valueOf((String) prop.get("electionScopeIdForPaliament"));
		    }
			
			     
			
			//System.exit(0);
			
			//get totalvotes polled for all constituencies
		    List<Object[]>	 objs=(List<Object[]>) partyTrendsDAO.getVotesPolledForConst(electionId,stateId);
		    
		    for (Object[] objects : objs) {
		    	totalVotesMap.put(Long.valueOf(objects[0].toString()), Double.valueOf(objects[1].toString()).longValue());
			}
		    System.out.println(totalVotesMap.size());
	       // System.exit(0);
			
			//load all constituencies which are not completed
			List<Object[]> value = delimitationConstituencyDAO.getConstituencyNoByCountry(stateId,2009L,electionScopeId);
			for (Object[] objects : value) {
				//System.out.println(objects[1]+"====="+objects[0]);
				constIdsMap.put(Long.valueOf(objects[1].toString()), Long.valueOf(objects[0].toString()));
			}
			System.out.println(constIdsMap.size());
			
			
			// get not completed constituencies			
			List<?>  filtered=partyTrendsDAO.loadConStituencyIdsWithNo(electionId,stateId,electionScopeId,2009L);
			System.out.println("remaining to complete"+filtered.size());
			
			String constNo="53";
			insertCandidateResult(constNo, prop, constIdsMap, totalVotesMap, stateId, electionId, electionScopeId,insertFor);
		}catch (Exception e) {
			log.error("exception thrown in candidate result"+e.getMessage());
		}finally {
			  if(in!=null)
				try {
					in.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			
		}//finally 
			
		}
	public void  insertCandidateResult(String constNo,Properties prop, Map<Long,Long> constIdsMap,Map<Long,Long> totalVotesMap,Long stateId,Long  electionId,Long electionScopeId, String insertFor)
			
			{
				 
				try {
			
			
			List<BaseCandidateResultVO> candidatesList=	getCandidatesVotesForConstituency(constNo,prop,insertFor);
			
			Comparator<BaseCandidateResultVO> countComp=new Comparator<BaseCandidateResultVO>() {

				//@Override
				public int compare(BaseCandidateResultVO o1, BaseCandidateResultVO o2) {
				
					return o2.getCount().compareTo(o1.getCount());
				}
			};
			Long constId=Long.valueOf(constNo);
			Collections.sort(candidatesList, countComp);
			Long topCount=0L;
			Long constituencyId=constIdsMap.get(constId);
			if(constituencyId==null)
				constituencyId=40761L;//==================================================
			long totalvotespolled=0;
			for(int rankers=0;rankers<candidatesList.size();rankers++)
			{
				BaseCandidateResultVO bvo=candidatesList.get(rankers);
				
				Long  count=bvo.getCount();
				totalvotespolled=totalvotespolled+count;
				bvo.setRank((long)rankers+1);
				Long totalVotesPolled=totalVotesMap.get(constituencyId);
				//set votes percentage here
				if(totalVotesMap.containsKey(constituencyId) )
				{
					
					if(totalVotesPolled!=0L)
					{
						//caculate percentage
						float percentage=(float)((float)count/(float)totalVotesPolled)*100;
						bvo.setVotesPercengate(roundTo2DigitsFloatValue(percentage));
					}
					
					
				}
				
				//set margin here and percentage here
				if(rankers==0)
				{
					topCount=count;
					BaseCandidateResultVO bvo1=candidatesList.get(1);
					Long secondCount=bvo1.getCount();
					
					Long marginVotes=topCount-secondCount;
					//calculate percentage
					bvo.setMarginVotes(marginVotes.doubleValue());
					if(totalVotesPolled!=0L)
					{
					
						//caculate percentage
						float percentage=(float)((float)marginVotes/(float)totalVotesPolled)*100;
						bvo.setMarginVotesPercentage(roundTo2DigitsFloatValue(percentage));
					}
					
				}else
				{
					Long marginVotes=topCount-count;
					//calculate percentage
					bvo.setMarginVotes(marginVotes.doubleValue());
					if(totalVotesPolled!=0L)
					{
					
						//caculate percentage
						float percentage=(float)((float)marginVotes/(float)totalVotesPolled)*100;
						bvo.setMarginVotesPercentage(roundTo2DigitsFloatValue(percentage));
				}
				
				}
				
			}
			
			System.out.println(candidatesList);
			Map<String,Integer> partyIds=BasePartyIdsMAp.getMap();

			for (BaseCandidateResultVO baseCandidateResultVO : candidatesList) {
				
				//check whether partyId is avilable or not
				
				Integer status=baseCandidateResultVO.getStatus();
				if(status!=null && status.equals(1))
				{
					//update constituency election
					int status1=partyTrendsDAO.updateCandidateElection(electionId, status, constituencyId);
					Log.debug("SucessFully Completed"+constituencyId+"==="+constNo+"=="+status1);
				}
					
				
				String pName=baseCandidateResultVO.getPartyName();
				
				
				//if partyName Available
				
				
				if(partyIds.containsKey(pName))
				{
					System.out.println("inside parliament===="+pName);
				    //if not  independent  do  this
					
					Long partyId=Long.valueOf(partyIds.get(pName).toString());
					
					if(!partyId.equals(366L) && !partyId.equals(232323L))
					{
				   //update candidate result  by using partyId and electionId if his not not independent
						//constIdsMap.get(constId);
					int updatesCount =0;//partyTrendsDAO.updateCandidateReult(partyId, electionId, baseCandidateResultVO.getCount().doubleValue(),constituencyId,baseCandidateResultVO.getVotesPercengate(),baseCandidateResultVO.getMarginVotes(),baseCandidateResultVO.getMarginVotesPercentage());

					System.out.println(updatesCount);
					if(updatesCount!=1)
					{
						Log.warn("failed updation of party"+partyId+"and constId"+partyId);
						System.out.println("failed updation of party "+partyId+" and constId "+partyId);
					}
					}else {
						if(partyId.equals(366L)) {
					//here update independent
						int updated =0;//partyTrendsDAO.updateCandidateReultForIndependents(baseCandidateResultVO.getCandidateName(), electionId, baseCandidateResultVO.getCount().doubleValue(),constituencyId,baseCandidateResultVO.getVotesPercengate(),baseCandidateResultVO.getMarginVotes(),baseCandidateResultVO.getMarginVotesPercentage());
						System.out.println(updated);
							if(updated!=1)
							{
								Log.warn("failed updation of independent "+baseCandidateResultVO.getCandidateName());
							}
						}else {
								if(partyId.equals(232323L)) {
									//update contituency election result
									int updatedS=partyTrendsDAO.updateNotaVotesInConstElectResult(electionId, baseCandidateResultVO.getCount(), constituencyId);
									Log.warn("nota "+updatedS);
									System.out.println("nota not updated any where ");
								}
							
						}
					}//end of else
				
				}else
				{
					// acknowledge to user this partyname not in database need to do something
					System.out.println("the party names those not avilable =="+pName);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
		}
	}
	public List<BaseCandidateResultVO> getCandidatesVotesForConstituency(String constNo,Properties prop, String insertFor) throws IOException
	{
	//	List<BaseCandidateResultVO> constList =new ArrayList<BaseCandidateResultVO>();
		//String url="http://affidavitarchive.nic.in/DynamicAffidavitDisplay/CANDIDATEAFFIDAVIT.aspx?YEARID=May-2014 ( GEN )&AC_No=84&st_code=S01&constType=AC";
  	     //String url="http://eciresults.ap.nic.in/ConstituencywiseS26"+constNo+".htm?ac="+constNo;
		
		//read propertiesFileDta
	    
	    String url1=(String) prop.get("url");  
	    
	    String stateNo=(String) prop.get("stateNo");
	   /* String divForConst=(String) prop.get("divToConst");
	    String resultFlag=(String) prop.get("resultFlag");
	    int statusCount=Integer.valueOf(prop.get("resultStatusDivCount").toString()) ;
	    int  tableCount=Integer.valueOf(prop.get("tableDataDivCount").toString()) ; */
	   
	    
	    //end read properties
	    
		url1=url1.replaceAll("\\$", stateNo);
		System.out.println(url1);
		url1=url1.replaceAll("\\@", constNo);
		String url=url1;
		
		System.out.println(url);
		
	   // System.exit(0);
     	 HttpClient client = null;
     	 PostMethod post = null;
         GetMethod get=null;
   		 client = new HttpClient(new MultiThreadedHttpConnectionManager());

   		 client.getHttpConnectionManager().getParams().setConnectionTimeout(Integer.parseInt("30000"));
   		 get=new GetMethod(url);
   		
   		try {
   		int status=	client.executeMethod(get);
   		System.out.println(status);
   		}catch (Exception e) {
   			e.printStackTrace();
			}
   		
   		//System.out.println(get.getResponseBodyAsString());
   		
   		/*String sb=null;
		
			sb = get.getResponseBodyAsString();*/
   		InputStream fis=	get.getResponseBodyAsStream();
    	BufferedReader br= new BufferedReader(new InputStreamReader(fis));
    	StringBuffer sb =new StringBuffer();
    	
    	String line = br.readLine();

        while (line != null) {
            sb.append(line);
         
            line = br.readLine();
        }
    	
    		
		 get=null;
		return  divideStrinIntoResults(sb, constNo, prop, insertFor);
		 
	}
	public List<BaseCandidateResultVO> divideStrinIntoResults(StringBuffer sb,String constNo,Properties prop,String insertFor)
		 {
			 
		List<BaseCandidateResultVO> constList =new ArrayList<BaseCandidateResultVO>();
		//read data from properties file
		String divForConst=null;
		
	    String stateNo=(String) prop.get("stateNo");
	    if(!insertFor.equals("Parliament"))
	     divForConst=(String) prop.get("divToConst");
	    else
	      divForConst=(String) prop.get("divToParliament");
	    String resultFlag=(String) prop.get("resultFlag");
	    int statusCount=Integer.valueOf(prop.get("resultStatusDivCount").toString()) ;
	    int  tableCount=Integer.valueOf(prop.get("tableDataDivCount").toString()) ; 
	    
		 StringBuffer sbString =new StringBuffer();
		 sbString.append("<div id=\"");
		 sbString.append(divForConst);
		 sbString.append("\"[\\s\\S]*?<table.*?>(<tr.*?>(<td.*?>.*</td>)*</tr>)</table>");
		 System.out.println(sbString);
   		// String patern="<div id=\"div1\"[\\s\\S]*?<table.*?>(<tr.*?>(<td.*?>.*</td>)*</tr>)</table>"; 
   		 String pattern2="<td.*?>([\\s\\S]+?)</td>";
   		 String patern=sbString.toString();
   		 Pattern pat =    Pattern.compile(patern, Pattern.CASE_INSENSITIVE);
		 Matcher matcher=pat.matcher(sb);
		 String str=null;
			 while (matcher.find())
			 {
			 str=matcher.group(2).toString();
		     }
			 if(str==null) {
				 System.out.println("Some Thing Wrong With URl"+sb.toString());
				 log.debug("constNo Not Have A Profer Url"+constNo+"data i ahve got is "+sb.toString());
 				 return null;
 				 
			 }
			Pattern pat1 =    Pattern.compile(pattern2, Pattern.CASE_INSENSITIVE);
			Matcher matcher1=pat1.matcher(str);
			int count=1;
				
			int flagCount=0;
			BaseCandidateResultVO vo = new BaseCandidateResultVO();
			   while (matcher1.find()) {
				   try {
					   if(count==statusCount) {
					System.out.print("======="+matcher1.group(1).toString());
					String resultStatus=matcher1.group(1).toString().trim();
					if(resultStatus.equalsIgnoreCase(resultFlag))
						vo.setStatus(1);
					   }
					if(count>tableCount)
					{
						

						if(flagCount<=2) {
							System.out.print("======="+matcher1.group(1).toString());
							String data=matcher1.group(1).toString().trim();
							data=data.replaceAll("\\.", " ").replaceAll("  ", " ").replaceAll("[â?]", "");
						if(flagCount==0)
						vo.setCandidateName(data);
						if(flagCount==1)							
						vo.setPartyName(data);
						if(flagCount==2)	{
						vo.setCount(Long.valueOf(data));
						flagCount=-1;
						//vo.setCandidateName(matcher1.group(1).toString());
						constList.add(vo);
						System.out.println();
						flagCount=-1;
						vo=new BaseCandidateResultVO();
						}
						}
						else {
							vo.setCandidateName(matcher1.group(1).toString());
							constList.add(vo);
							System.out.println();
							flagCount=-1;
							vo=new BaseCandidateResultVO();
						}
						flagCount++;
				}
					count++;
					    }
			   
				
			   catch (Exception e) {
				Log.debug("Exception Raised for"+constNo+e.getMessage());
			}
			   }//while
				return constList;
	}
		
	
	
	
	public static String roundTo2DigitsFloatValue(Float number){
		  
		  Log.debug("Entered into the roundTo2DigitsFloatValue service method");
		  
		  String result = "";
		  try
		  {
			  
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e)
		  {
			  Log.error("Exception raised in roundTo2DigitsFloatValue service method");
			  e.printStackTrace();
		  }
		  return result;
	}
	
	
	public static  void  callWebService(String string2,List<String> exceptionOccuredList1)
	{
		
	
		
	}
	
	/* {
		System.out.println("inside static block to get all constituencyids ");
		List<Object[]> value = delimitationConstituencyDAO.getConstituencyNoByState(1l,2009L,2L);
		System.out.println(value.size());
		
		for (Object[] objects : value) {
			//System.out.println(objects[1]+"====="+objects[0]);
			constIdsMap.put(Long.valueOf(objects[1].toString()), Long.valueOf(objects[0].toString()));
		}
	}*/
}
