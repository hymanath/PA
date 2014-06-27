package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.language.RefinedSoundex;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IMemberVoterMappingDetailsDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.ITdMemberDAO;
import com.itgrids.partyanalyst.dto.SoundexVO;
import com.itgrids.partyanalyst.model.MemberVoterMappingDetails;
import com.itgrids.partyanalyst.service.ISoundexService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.IConstants.MatchTypes;
import com.itgrids.partyanalyst.utils.MatchedTypeVo;

public class SoundexService implements ISoundexService {
	private static final Logger LOG = Logger.getLogger(SoundexService.class);
	
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private ITdMemberDAO tdMemberDAO;
	private IPanchayatDAO panchayatDAO;
	private IMemberVoterMappingDetailsDAO memberVoterMappingDetailsDAO ;
	java.util.Set<Long> totalSet = new java.util.HashSet<Long>();
	private RefinedSoundex refinedSoundex = new RefinedSoundex();
	int testcount =0;
	private Map<String,List<SoundexVO>> map = new HashMap<String,List<SoundexVO>>();

	
	



	public IMemberVoterMappingDetailsDAO getMemberVoterMappingDetailsDAO() {
		return memberVoterMappingDetailsDAO;
	}


	public void setMemberVoterMappingDetailsDAO(
			IMemberVoterMappingDetailsDAO memberVoterMappingDetailsDAO) {
		this.memberVoterMappingDetailsDAO = memberVoterMappingDetailsDAO;
	}


	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}


	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}


	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}


	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}


	public ITdMemberDAO getTdMemberDAO() {
		return tdMemberDAO;
	}


	public void setTdMemberDAO(ITdMemberDAO tdMemberDAO) {
		this.tdMemberDAO = tdMemberDAO;
	}
	
	public List<SoundexVO> getMappedVoterDetailsByUsingSoundexByPanchayatId(Long constituecyId)
	{
		List<Long> list = new ArrayList<Long>();
		
		
		
	
		
	/*	
		list.add(1L);
		list.add(2L);
		list.add(3L);
		list.add(4L);
		list.add(5L);
		list.add(6L);
		list.add(7L);
		list.add(8L);
		list.add(10L);
		list.add(11L);
		list.add(12L);
		list.add(13L);
		list.add(15L);
		list.add(16L);
		list.add(18L);
		list.add(20L);
		list.add(21L);
		list.add(23L);
		list.add(24L);
		list.add(26L);
		list.add(30L);
		list.add(31L);
		list.add(32L);
		list.add(34L);
		list.add(35L);
		list.add(36L);
		list.add(37L);
		list.add(39L);
		list.add(40L);
		list.add(41L);
		list.add(43L);
		list.add(44L);
		list.add(46L);
		list.add(47L);
		list.add(49L);
		list.add(50L);
		list.add(51L);
		list.add(52L);
		list.add(53L);
		list.add(54L);
		list.add(55L);	
		list.add(56L);
		list.add(57L);
		list.add(58L);
		list.add(59L);
		list.add(60L);
		list.add(61L);
		list.add(62L);
		list.add(63L);
		list.add(64L);
		list.add(65L);
		list.add(66L);
		list.add(67L);
		list.add(68L);
		list.add(69L);
		list.add(70L);
		list.add(71L);
		list.add(73L);
		list.add(74L);
		list.add(75L);
		list.add(77L);
		list.add(78L);
		list.add(79L);
		list.add(80L);
		list.add(81L);
		list.add(82L);
		list.add(84L);
		*/
		
		
		/*list.add(85L);
		list.add(86L);
		list.add(87L);
		list.add(89L);
		list.add(91L);
		list.add(92L);
		list.add(93L);
		list.add(94L);
		list.add(97L);
		list.add(100L);
		list.add(101L);
		list.add(102L);
		list.add(103L);
		list.add(104L);
		list.add(105L);
		list.add(107L);
		list.add(108L);
		list.add(109L);
		list.add(111L);
		list.add(112L);
		list.add(113L);
		list.add(114L);
		list.add(116L);
		list.add(117L);
		list.add(120L);
		
	
		
		list.add(121L);
		list.add(122L);
		list.add(124L);
		list.add(125L);
		list.add(127L);
		list.add(129L);
		list.add(133L);
		list.add(134L);
		list.add(135L);
		list.add(136L);
		list.add(137L);
		list.add(138L);
		list.add(140L);
		list.add(141L);*/
		
		
		/*
		
		
		list.add(146L);
		list.add(147L);
		list.add(149L);
		list.add(152L);
		list.add(153L);
		list.add(155L);
		list.add(156L);
		list.add(157L);
		list.add(159L);
		list.add(160L);
		list.add(163L);
		list.add(167L);
		list.add(168L);
		list.add(169L);
		list.add(170L);
		list.add(171L);
		list.add(172L);
		list.add(173L);
		list.add(174L);
		list.add(176L);
		list.add(177L);
		list.add(178L);
		list.add(179L);
		list.add(180L);
		list.add(181L);
		list.add(182L);
		list.add(184L);
		list.add(185L);
		list.add(186L);
		list.add(187L);
		list.add(191L);
		list.add(192L);
		list.add(193L);
		list.add(194L);
		list.add(195L);
		list.add(196L);
		list.add(199L);*/
		/*list.add(203L);
		list.add(205L);
		list.add(206L);
		list.add(207L);
		list.add(208L);
		list.add(209L);
		list.add(210L);
		list.add(211L);
		list.add(212L);
		list.add(213L);
		list.add(214L);
		list.add(215L);
		list.add(216L);
		list.add(217L);
		list.add(218L);
		list.add(219L);
		list.add(221L);
		list.add(222L);
		list.add(223L);
		list.add(224L);
		list.add(225L);
		list.add(226L);
		list.add(227L);
		list.add(228L);
		list.add(229L);
		list.add(231L);
		list.add(232L);
		list.add(233L);
		list.add(236L);
		list.add(237L);
		list.add(238L);
		list.add(239L);
		list.add(241L);
		list.add(242L);
		list.add(243L);
		list.add(244L);
		list.add(245L);
		list.add(246L);
		list.add(248L);
		list.add(249L);
		list.add(250L);
		list.add(251L);
		list.add(252L);
		list.add(253L);
		list.add(254L);
		list.add(255L);
		list.add(257L);
		list.add(258L);
		list.add(260L);
		list.add(261L);
		list.add(262L);
		list.add(263L);
		list.add(264L);
		list.add(265L);
		list.add(267L);
		list.add(270L);
		list.add(271L);
		list.add(272L);
		list.add(273L);
		list.add(275L);
		list.add(276L);
		list.add(277L);
		list.add(278L);
		list.add(279L);
		list.add(280L);
		list.add(281L);
		list.add(282L);
		list.add(283L);
		list.add(284L);
		list.add(285L);
		list.add(286L);
		list.add(288L);
		list.add(289L);
		//list.add(290L);
		list.add(291L);
		list.add(294L);
		list.add(295L);
		list.add(296L);
		list.add(297L);
		list.add(298L);
		list.add(299L);
		list.add(300L);
		list.add(301L);
		list.add(302L);
		list.add(303L);
		list.add(304L);
		list.add(305L);
		list.add(306L);
		list.add(307L);
		list.add(308L);
		list.add(309L);
		list.add(310L);
		list.add(311L);
		list.add(312L);
		list.add(313L);
		list.add(314L);
		list.add(315L);
		list.add(316L);
		list.add(317L);
		list.add(318L);
		list.add(319L);
		list.add(320L);
		list.add(321L);
		list.add(322L);
		list.add(323L);
		list.add(324L);
		list.add(325L);
		list.add(326L);
		list.add(327L);
		list.add(328L);
		list.add(329L);
		list.add(330L);
		list.add(331L);
		list.add(332L);
		list.add(333L);
		list.add(334L);
		list.add(335L);
		list.add(336L);
		list.add(337L);
		list.add(338L);
		list.add(339L);
		list.add(340L);
		list.add(341L);
		list.add(342L);
		list.add(343L);
		list.add(344L);
		list.add(345L);
		list.add(346L);
		list.add(347L);
		list.add(348L);
		list.add(349L);
		list.add(350L);
		list.add(351L);
		list.add(352L);
		list.add(353L);
		list.add(354L);
		list.add(355L);
		list.add(356L);
		list.add(357L);
		list.add(358L);
		list.add(359L);
		list.add(360L);
		list.add(361L);
		list.add(362L);
		list.add(363L);
		list.add(364L);
		list.add(365L);
		list.add(366L);
		list.add(367L);
		list.add(368L);
		list.add(369L);
*/
		
		/*
		
		list.add(351L);
		list.add(361L);
		list.add(323L);
		list.add(344L);
		list.add(313L);
		list.add(286L);
		list.add(363L);
		list.add(360L);
		list.add(53L);
		list.add(149L);
		list.add(233L);
		list.add(208L);
		list.add(246L);
		list.add(138L);
		list.add(184L);
		list.add(285L);
		list.add(241L);
		list.add(219L);
		list.add(283L);
		list.add(44L);
		list.add(337L);
		list.add(226L);
		list.add(134L);
		list.add(236L);
		list.add(275L);
		list.add(187L);
		list.add(211L);
		list.add(185L);
		list.add(252L);
		list.add(281L);
		list.add(301L);
		list.add(173L);
		list.add(159L);
		list.add(318L);
		list.add(316L);
		list.add(196L);
		list.add(327L);
		list.add(237L);
		list.add(320L);
		list.add(231L);
		list.add(248L);
		list.add(306L);
		list.add(212L);
		list.add(176L);
		list.add(322L);
		list.add(147L);
		list.add(171L);
		list.add(364L);
		list.add(368L);
		list.add(192L);
		list.add(194L);
		list.add(315L);
		list.add(272L);
		list.add(359L);
		list.add(238L);
		list.add(155L);
		list.add(290L);
		list.add(311L);
		list.add(186L);
		list.add(324L);
		list.add(325L);
		list.add(331L);
		list.add(277L);
		list.add(297L);
		list.add(312L);
		list.add(328L);
		list.add(310L);
		list.add(182L);
		list.add(261L);
		list.add(153L);
		list.add(163L);
		list.add(169L);
		list.add(291L);
		list.add(330L);
		list.add(250L);
		list.add(168L);
		list.add(258L);
		list.add(317L);
		list.add(257L);
		list.add(239L);
		list.add(278L);
		list.add(303L);
		list.add(264L);
		list.add(357L);
		list.add(215L);
		list.add(302L);
		list.add(157L);
		list.add(170L);
		list.add(319L);
		list.add(180L);
		list.add(179L);
		list.add(329L);
		list.add(217L);
		list.add(156L);
		list.add(47L);
		list.add(228L);
		list.add(362L);
		list.add(339L);
		list.add(254L);
		list.add(353L);
		list.add(177L);
		list.add(178L);
		list.add(160L);
		list.add(270L);
		list.add(289L);
		list.add(343L);
		list.add(308L);
		list.add(146L);
		list.add(232L);
		list.add(167L);
		list.add(213L);
		list.add(309L);
		list.add(334L);
		list.add(101L);
		list.add(260L);
		list.add(284L);
		list.add(52L);
		list.add(218L);
		list.add(354L);
		list.add(255L);
		list.add(280L);
		list.add(253L);
		list.add(225L);
		list.add(49L);
		list.add(214L);
		list.add(210L);
		list.add(358L);
		list.add(50L);
		list.add(299L);
		list.add(223L);
		list.add(300L);
		list.add(221L);
		list.add(338L);
		list.add(352L);
		list.add(298L);
		list.add(152L);
		list.add(222L);
		list.add(314L);
		list.add(276L);
		list.add(294L);
		list.add(367L);
		list.add(263L);
		list.add(199L);
		list.add(227L);
		list.add(195L);
		list.add(356L);
		list.add(224L);
		list.add(304L);
		list.add(51L);
		list.add(244L);
		list.add(267L);
		list.add(271L);
		list.add(262L);
		list.add(307L);
		list.add(229L);
		list.add(216L);
		list.add(336L);
		list.add(332L);
		list.add(191L);
		list.add(365L);
		list.add(348L);
		list.add(345L);
		list.add(347L);
		list.add(349L);
		list.add(355L);
		list.add(346L);
		list.add(282L);
		list.add(350L);
		list.add(333L);
		list.add(207L);
		list.add(265L);*/
		
		
		list.add(32L);
		list.add(243L);
		list.add(273L);
		list.add(295L);
		list.add(296L);
		list.add(242L);
		list.add(70L);
		list.add(251L);
		list.add(335L);
		list.add(326L);
		list.add(369L);
		list.add(340L);
		list.add(193L);
		list.add(288L);
		list.add(54L);
		list.add(321L);
		list.add(43L);
		list.add(249L);
		list.add(341L);
		list.add(342L);
		list.add(245L);
		list.add(46L);
		list.add(305L);
		list.add(279L);

		
		List<Long> constituencyIds = memberVoterMappingDetailsDAO.getConstituencyIdsAlreadyhasData();
		
	


		for(Long constituencyId:list)
			if(!constituencyIds.contains(constituencyId))
			mapVoterDetailsByUsingSoundexByPanchayatId(constituencyId);
		
		return null;
		
	}


	public List<SoundexVO> mapVoterDetailsByUsingSoundexByPanchayatId(Long constitueecyId)
	{
		LOG.debug("Entered into the getMappedVoterDetailsByUsingSoundexByPanchayatId service method "+constitueecyId);
		List<SoundexVO>  resultList = new ArrayList<SoundexVO>();
		List<SoundexVO>  membersList = null;
		Map<String,List<SoundexVO>> totalVoters=new HashMap<String, List<SoundexVO>>();
		int count=0;
		char[] letters="abcdefghijklmnopqrstuvwxyz".toCharArray();
		System.out.println(letters.length);
		
		
		for(;;)
		{
			
			totalVoters.put(letters[count]+"", new ArrayList<SoundexVO>());
			if(count++==25)
				break;  
			
		}
		System.out.println(totalVoters.size());
	 
		try
		{
			List<Object[]> panchayatDEtails = panchayatDAO.getPanchayatsByConstituencyId(constitueecyId);
			
			for(Object[] panchayatList:panchayatDEtails)
			{
				List<Object[]> memberDetails = tdMemberDAO.getMembersDetailsBypanchayatId((Long)panchayatList[0]);
				
				if(memberDetails == null || memberDetails.size() == 0)
				{
					
					  List<Object[]> list = boothPublicationVoterDAO.getVotersDetailsForPanchayatByPublicationIdAndPAnchayatId((Long)panchayatList[0], 10L);
						
						List<SoundexVO> votersDetails = new ArrayList<SoundexVO>();
						setVotersDetails(votersDetails,list,totalVoters);
					continue;
					
				}
				
				membersList = new ArrayList<SoundexVO>();				
				setMembersDetails(membersList,memberDetails,constitueecyId);
				
			    List<Object[]> list = boothPublicationVoterDAO.getVotersDetailsForPanchayatByPublicationIdAndPAnchayatId((Long)panchayatList[0], 10L);
			
				List<SoundexVO> votersDetails = new ArrayList<SoundexVO>();
				setVotersDetails(votersDetails,list,totalVoters);

				buildAllTheMemberAndMatchedVotersDetails(membersList,votersDetails);
				resultList.addAll(membersList);
			}
			
			 List<Object[]> urbanVotersDetails = boothPublicationVoterDAO.getUrbanVotersDetails(constitueecyId,10L);
			 setVotersDetails(null,urbanVotersDetails,totalVoters);
			 
				List<Object[]> memberDetails = tdMemberDAO.getUrbanMembersDetails(constitueecyId);
				setMembersDetails(resultList,memberDetails,constitueecyId);


			
			constLevelDest(resultList,totalVoters);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getMappedVoterDetailsByUsingSoundexByPanchayatId service method");
		}
		
		return membersList;
	}
	
	/**
	 * This method will save member voter mapping details
	 * @param memberVO
	 * @param voterVO
	 * @param matchedTypeVo
	 * @param memberMatchedTypeId
	 * @param matchCount
	 */
	public void saveMemberVoterMappingDetails(SoundexVO memberVO,SoundexVO voterVO,MatchedTypeVo matchedTypeVo,Long memberMatchedTypeId,Integer matchCount)
	{
		try
		{
			MemberVoterMappingDetails memberVoterMappingDetails = new MemberVoterMappingDetails();
			
			totalSet.add(memberVO.getId());
			
			if(voterVO != null)
			{
				memberVoterMappingDetails.setVoterId(voterVO.getVoterId());
				
				if(matchedTypeVo != null)
				{
				
					memberVoterMappingDetails.setAgeMatched(matchedTypeVo.isAgeMatched() == true?"Y":"N");
					memberVoterMappingDetails.setGenderMatched(matchedTypeVo.isGenderMatch() == true?"Y":"N");
					memberVoterMappingDetails.setRelativeNameMatched(matchedTypeVo.isRelativeNameMatch() == true?"Y":"N");
					
				}else
				{
					memberVoterMappingDetails.setAgeMatched(voterVO.isAgeMatched() == true?"Y":"N");
					memberVoterMappingDetails.setGenderMatched(voterVO.isGenderMatch() == true?"Y":"N");
					memberVoterMappingDetails.setRelativeNameMatched(voterVO.isRelativeNameMatch() == true?"Y":"N");
				
				}
				
				memberVoterMappingDetails.setVoterPanchayatName(voterVO.getPanchayatName());
				memberVoterMappingDetails.setVoterAge(voterVO.getAge());
				memberVoterMappingDetails.setVoterGender(voterVO.getGender());
				memberVoterMappingDetails.setVoterRelativeName(voterVO.getRelativeName());
				
			}
			memberVoterMappingDetails.setMemberId(memberVO.getId());
			memberVoterMappingDetails.setMemberMatchedTypeId(memberMatchedTypeId);
			memberVoterMappingDetails.setMatchCount(matchCount.longValue());
			
			memberVoterMappingDetails.setMemberPanchayatName(memberVO.getPanchayatName());
			memberVoterMappingDetails.setMemberAge(memberVO.getAge());
			memberVoterMappingDetails.setMemberGender(memberVO.getGender());
			memberVoterMappingDetails.setMemberRelativeName(memberVO.getRelativeName());
			memberVoterMappingDetails.setConstituencyId(memberVO.getConstituencyId());
			
			
			memberVoterMappingDetailsDAO.save(memberVoterMappingDetails);
			
			memberVoterMappingDetailsDAO.flushSession();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in saveMemberVoterMappingDetails service method");
		}
		
	}
	
	public void setVotersDetails(List<SoundexVO> votersDetails , List<Object[]> list, Map<String, List<SoundexVO>> totalVoters)
	{
		//LOG.debug("Entered into the setVotersDetails service method");
		String firstLitter = null;
		String testString = null;

		try
		{
			
			for(Object[] obj:list)
			{
				
				SoundexVO voter = new SoundexVO();
				
				voter.setVoterId((Long)obj[0]);
                voter.setRelationShipType(obj[1].toString());
                voter.setRelativeName(obj[2].toString());
                voter.setGender(obj[3].toString());
                voter.setAge((Long)obj[4]);
                voter.setVoterIDCardNo(obj[5].toString());
                voter.setName(obj[6].toString());
                
                if(obj.length ==8)
                 voter.setPanchayatName(obj[7].toString());
                
                if(votersDetails != null)
                	votersDetails.add(voter);
                
                testString = obj[6].toString();
            	 firstLitter=	obj[6].toString().substring(0, 1).trim().toLowerCase();
            	 if(totalVoters.get(firstLitter) != null)
            		totalVoters.get(firstLitter).add(voter);
            	 else
            	 {
            		 System.out.println("----------------"+testString+"----------------"+(Long)obj[0]);
            	 }
            	testcount++;
            	
    			String voterName = obj[6].toString().trim();
    			String[] voterSplit = null;
    			
    			 voterSplit = voterName.split(" ");
    			 
    			 if(voterSplit.length != 2)
    				 voterSplit = voterName.split(".");
    			 
    			 if(voterSplit.length == 2)
    			 {
    				 String nameSoundex = refinedSoundex.soundex(voterSplit[0].trim())+refinedSoundex.soundex(voterSplit[1].trim());
    				 String reverseNameSoundex = refinedSoundex.soundex(voterSplit[1].trim())+refinedSoundex.soundex(voterSplit[0].trim());
    				 
    				 
    				 if(map.get(nameSoundex) != null)
    				 {
    					 map.get(nameSoundex).add(voter);
    				 }else
    				 {
    					List<SoundexVO>  nameList = new ArrayList<SoundexVO>();
    					nameList.add(voter);
    				    map.put(nameSoundex,nameList ); 
    				 }
    				 
    				 
    				 if(map.get(reverseNameSoundex) != null)
    				 {
    					 map.get(reverseNameSoundex).add(voter);
    				 }else
    				 {
    					List<SoundexVO>  reverseNameList = new ArrayList<SoundexVO>();
    					reverseNameList.add(voter);
    				    map.put(reverseNameSoundex,reverseNameList); 
    				 }
    				 
    			 }else
    			 {
    				 String nameSoundex = refinedSoundex.soundex(voterName.trim());
    				 
    				 if(map.get(nameSoundex) != null)
    				 {
    					 map.get(nameSoundex).add(voter);
    				 }else
    				 {
    					List<SoundexVO>  reverseNameList = new ArrayList<SoundexVO>();
    					reverseNameList.add(voter);
    				    map.put(nameSoundex,reverseNameList); 
    				 }
    				 
    			 }
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in  the setVotersDetails service method"+firstLitter+"-"+testString+"-"+testcount);

		}
		
	}
	
	public void setMembersDetails(List<SoundexVO> membersList , List<Object[]> memberDetails,Long constitueecyId)
	{
		LOG.debug("Entered into the setMembersDetails service method");
		try
		{
			
			
			for(Object[] obj:memberDetails)
			{
				SoundexVO member = new SoundexVO();
				
				member.setId((Long)obj[0]);
				member.setName(obj[1].toString());
				member.setRelationShipType(obj[2].toString());
				member.setRelativeName(obj[3].toString());
				member.setGender(obj[4].toString());
				
				if(obj.length == 7 )
				  member.setPanchayatName(obj[6].toString());
				member.setConstituencyId(constitueecyId);
				
				Date joinedDate = (Date)obj[5];
				
				if(joinedDate != null)
				{
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(joinedDate);
					Calendar cal2 = Calendar.getInstance();
					cal2.set(2014, 2, 1);
					
					Long diff = cal2.getTimeInMillis() - cal1.getTimeInMillis();
					
					member.setAge((diff/(1000*24*60*60))/365);
				}
			/*	 if((Long)obj[0] == 1254772L || (Long)obj[0] == 1365758L||(Long)obj[0] == 1365764L||
	            		 (Long)obj[0] == 1365825L||(Long)obj[0] == 1365851L||(Long)obj[0] == 1367932L||(Long)obj[0] == 1377540L)*/
				
				//if((Long)obj[0] == 1012631L)
	            		membersList.add(member);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in  the setMembersDetails service method");

		}
	}
	
	public void buildAllTheMemberAndMatchedVotersDetails(List<SoundexVO> membersList,List<SoundexVO>  votersDetails)
	{
		LOG.debug("Entered into the buildAllTheMemberAndMatchedVotersDetails service method");
		RefinedSoundex soundex = new RefinedSoundex();

		try
		{
			
			for(SoundexVO memberVO:membersList)
			{
				boolean matched = false;
				for(SoundexVO voterVO:votersDetails)
				{
					if(memberVO.getName().replaceAll(" ","").trim().equalsIgnoreCase(voterVO.getName().replaceAll(" ","").trim()))
					{
						matched = true;
						memberVO.setExactMatchCount(memberVO.getExactMatchCount()+1);
						memberVO.getExactMatchList().add(voterVO);
					}else
					{
						 
						String voterName = voterVO.getName().trim().toLowerCase();
						String memeberName = memberVO.getName().trim().toLowerCase();
						
						 if(soundex.soundex(memberVO.getName()).equalsIgnoreCase(soundex.soundex(voterVO.getName())))
						 {
							 memberVO.getSoundexMatchList().add(voterVO);
							 matched = true;
						 }else if(checkForNameExist(voterName,memeberName,soundex))
						 {
								 matched = true;
								 memberVO.getSoundexMatchList().add(voterVO);
								 
						 }else    if( voterName.replaceAll("[\\s\\.+]", "").contains(memeberName.replaceAll("[\\s\\.+]", "")) ||
								    memeberName.replaceAll("[\\s\\.+]", "").contains(voterName.replaceAll("[\\s\\.+]", ""))  || soundex.soundex(voterName).replaceAll("0", "").equalsIgnoreCase(soundex.soundex(memeberName).replaceAll("0", "")))
							 
						 {
							 matched = true;
							 memberVO.getSoundexMatchList().add(voterVO);

						 }else 
						 {
							 	String[] memberName = null;
							    memberName =memeberName.split("\\s+");
							    
							    if(memberName.length !=2)
								    memberName = memeberName.split(".");
							    
							    if(memberName.length == 2)
							    {
									    // String firstString = memberName[0].trim()+""+memberName[1].trim();
									    String lastString = memberName[1].trim()+""+memberName[0].trim();
									    if(soundex.soundex(voterName).equalsIgnoreCase(soundex.soundex(lastString)) || voterName.replaceAll("[\\s\\.+]", "").contains(lastString.replaceAll("[\\s\\.+]", "")) ||
									    lastString.replaceAll("[\\s\\.+]", "").contains(voterName.replaceAll("[\\s\\.+]", "")) ||
									    soundex.soundex(voterName).replaceAll("0", "").equalsIgnoreCase(soundex.soundex(lastString).replaceAll("0", "")) )
									    {
									    	matched=true;
											 memberVO.getSoundexMatchList().add(voterVO);

									    }
							   }
							  
						 }
					}
					
				}
				if(!matched)
				{
					memberVO.setUnMatched(true);
				}
			}
			
			
		/*	for(SoundexVO memberVO:membersList)
			{
				if(memberVO.isUnMatched())
				{
					for(SoundexVO voterVO:votersDetails)
					{
						
						String[] memberName = null;
						  memberName = memberVO.getName().split(" ");
						  
						 if(memberName.length !=2)
							 memberName = memberVO.getName().split(".");
						 
						
						if(memberName != null && memberName.length == 2)
						{
							String firstString = memberName[0].trim()+""+memberName[1].trim();
							String lastString = memberName[1].trim()+""+memberName[0].trim();
							
							if (soundex.soundex(voterVO.getName().trim())
									.equalsIgnoreCase(soundex.soundex(firstString.trim()))
									|| soundex
											.soundex(voterVO.getName().trim())
											.equalsIgnoreCase(soundex.soundex(lastString.trim())))						
							{
								memberVO.getSoundexMatchList().add(voterVO);
								memberVO.setSplit(true);
							}
							
							
							boolean gender = voterVO.getGender().equalsIgnoreCase(memberVO.getGender());
							
							if(gender)
								voterVO.setGenderMatch(true);
							
							boolean relativeName = voterVO.getRelativeName().equalsIgnoreCase(memberVO.getRelativeName());

							if(relativeName)
								voterVO.setRelativeNameMatch(true);
							
							long low = memberVO.getAge() - 4;
							long high = memberVO.getAge() + 4;
							
							boolean age = (voterVO.getAge() >= low) &&( voterVO.getAge() <= high);
							
							 if(age)
								voterVO.setAgeMatched(true);
							
						}
					}
					
					if(memberVO.isSplit())
						memberVO.setUnMatched(false);
				}
			}*/
			
			/*for(SoundexVO memberVO:membersList)
			{
				if(memberVO.isUnMatched())
				{
					for(SoundexVO voterVO:votersDetails)
					{
						if (soundex
								.soundex(memberVO.getName())
								.replaceAll("0", "")
								.equalsIgnoreCase(
										soundex.soundex(voterVO.getName())
												.replaceAll("0", "")))						{
							
							memberVO.getSoundexMatchList().add(voterVO);
							memberVO.setSplit(true);
							memberVO.setUnMatched(false);
						}
					}
					
					
				}
			}*/
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in buildAllTheMemberAndMatchedVotersDetails service method");
		}
		
	}

	/**
	 * This method is used to check mapping details for those members ,which  not matched in panchayat level
	 * @param membersList
	 * @param totalVoters
	 */
    public void constLevelDest(List<SoundexVO>  membersList, Map<String, List<SoundexVO>> totalVoters )
	{
    	
		try {
			System.out.println("input size"+membersList.size());

			Map<String,List<SoundexVO> > mapIds= totalVoters;
			 for (SoundexVO memberVO : membersList) {
				 
				 if(!memberVO.isUnMatched())
						continue;
				 

					List<SoundexVO> voterMatchedList = map.get(refinedSoundex.soundex(memberVO.getName()));
				 
					if(voterMatchedList != null)
					{
						if(voterMatchedList.size() > 1)
							memberVO.getSoundexMatchList1().addAll(voterMatchedList);
							
						System.out.println("HAI I FOUND IN NEW IMPLEMENTATION");
						continue;
					}
					
			List<SoundexVO> votersDetails = new ArrayList<SoundexVO>();

			
			
			String name1=memberVO.getName().substring(0, 1).trim().toLowerCase();
				
			votersDetails=mapIds.get(name1);		
			
			RefinedSoundex soundex = new RefinedSoundex();
			
		
			int flagset=0;
			if(votersDetails != null)
				for(SoundexVO voterVO:votersDetails)
				{
					boolean name  = false;
					String memName=memberVO.getName().trim().replaceAll("[\\s\\.+]", "").trim();
					String voterName=voterVO.getName().trim().replaceAll("[\\s\\.+]", "").trim();
					//exact match
					if(memName.equalsIgnoreCase(voterName))//comparing name by removing spaces of cadre and voter names
					{
						name = true;						
						memberVO.getExactMatchList1().add(voterVO);	
					

					}else
					{
						
						
					
						
					  //soundex match
						 name = soundex.soundex(memName).equalsIgnoreCase(soundex.soundex(voterName));//comparing name by applying soundex to cadre and voter names
						 
						 if(name) {
							 memberVO.getSoundexMatchList1().add(voterVO);
							 
						 }else if(checkForNameExist(voterVO.getName().trim(), memberVO.getName().trim(), soundex))
						 {
							 memberVO.getSoundexMatchList1().add(voterVO);
						 }
						 else if(flagset==0)
						 {
						 
							 String[] memberName = null;
							 memberName = memberVO.getName().split("\\s+");

							 if(memberName.length !=2)
							 memberName = memberVO.getName().split(".");

							 if(memberName.length == 2) //comparing name by reversing the words of member name, when it contains exactly 2 words
							 {
							
							     String lastString = memberName[1].trim()+""+memberName[0].trim();	 
							     String firstLet=lastString.substring(0, 1).trim().toLowerCase();								 
								 if(firstLet.equalsIgnoreCase(name1))
									 continue;
								 
								 List<SoundexVO> ids=mapIds.get(firstLet);
								 if(ids != null)
								 for (SoundexVO soundexVO : ids) {									 
									
									if (soundex.soundex(soundexVO.getName().trim()).equalsIgnoreCase(soundex.soundex(lastString)) || 
										soundex.soundex(soundexVO.getName().replaceAll("[\\s\\.+]", "")).equalsIgnoreCase(soundex.soundex(lastString.replaceAll("[\\s\\.+]", "")))	)
										
									 {
										 memberVO.getSoundexMatchList1().add(soundexVO);							
										 memberVO.setSplit(true);
										 memberVO.setUnMatched(false);
								      }
							  }//for
								 flagset++;
							 }
							
						   } //else
						 

					}
				}
				
				if(memberVO.getExactMatchList1().size() == 0 && memberVO.getSoundexMatchList1().size() == 0)
				{
					memberVO.setUnMatched(true);					

				}else
				{
					memberVO.setUnMatched(false);
				}
			
			}
   
          System.out.println("end of loop"+membersList.size());
   
          calculateCateries(membersList);
          processList1(membersList);

   
   // processList(membersList);   
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in constLevelDest method");
		}
	}
    
    /**
     * This method is used to  iterate and identifying the exact and soundex match details
     * @param resultList
     */
    
    public void processList1( List<SoundexVO> resultList )
    {
    	LOG.debug("Entered into the processList1 service method");
    	try
    	{

    	for(SoundexVO  member:resultList)
		{
    		 if(member.getExactMatchList1().size() == 1 && member.getSoundexMatchList1().size() == 0)//panchayat level exact match
 			{
 				 
 				int i=0;
 				for(SoundexVO exactMatch:member.getExactMatchList1())
 				{  
 					List<MatchedTypeVo> lists=null;
 					
 					Long memeberId=member.getId();
 					MatchedTypeVo evo=new MatchedTypeVo();
 					evo.setMemberId(memeberId);
 					lists=	exactMatch.getMaps().get(IConstants.MatchTypes.CExactMatch);
 					if(lists==null)
 						continue;
 					evo=lists.get(lists.indexOf(evo));
 					
 					if(i==0)
 					System.out.println("EXACT MATCH_1 ::"+member.getId()+"-"+
 														  member.getName()+"-"+
 														  exactMatch.getName()+"-"+
 														  
 														  evo.isAgeMatched()+"-"+
 														  member.getAge()+"-"+
 														  exactMatch.getAge()+"-"+
 														  
 														  evo.isGenderMatch()+"-"+
 														  member.getGender()+"-"+
 														  exactMatch.getGender()+"-"+
 														  
 														  evo.isRelativeNameMatch()+"-"+
 														  member.getRelativeName()+"-"+
 														  exactMatch.getRelativeName()+"-"+
 														  
 														  exactMatch.getVoterIDCardNo()+"-"+
 														  exactMatch.getVoterId()+"-"+
 														  
														  member.getPanchayatName()+"-"+
														  exactMatch.getPanchayatName()
 														  );
 													
 					else
 						System.out.println("         _1::"+member.getId()+"-"+
 								 member.getName()+"-"+
 								  exactMatch.getName()+"-"+
 								  
 								  evo.isAgeMatched()+"-"+
 								  member.getAge()+"-"+
 								  exactMatch.getAge()+"-"+
 								  
 								  evo.isGenderMatch()+"-"+
 								  member.getGender()+"-"+
 								  exactMatch.getGender()+"-"+
 								  
 								  evo.isRelativeNameMatch()+"-"+
 								  member.getRelativeName()+"-"+
 								  exactMatch.getRelativeName()+"-"+
 								  
 								  exactMatch.getVoterIDCardNo()+"-"+
 								  exactMatch.getVoterId()+"-"+
											  
								  member.getPanchayatName()+"-"+
								  exactMatch.getPanchayatName()
								  
 								  );
 					i++;
					
					saveMemberVoterMappingDetails(member,exactMatch,evo,1L,1);
				}
			}			
		
    	
    	
    	
    	 if(member.getExactMatchList().size() == 1 && member.getSoundexMatchList().size() == 0)//constituency level exact match
			{
    		 
				int i=0;
				for(SoundexVO exactMatch:member.getExactMatchList())
				{  
					List<MatchedTypeVo> lists=null;
					
					Long memeberId=member.getId();
					MatchedTypeVo evo=new MatchedTypeVo();
					evo.setMemberId(memeberId);
					lists=	exactMatch.getMaps().get(IConstants.MatchTypes.PExactMatch);
					evo=lists.get(lists.indexOf(evo));
					
					if(i==0)
					System.out.println("EXACT MATCH_1 ::"+member.getId()+"-"+
														  member.getName()+"-"+
														  exactMatch.getName()+"-"+
														  
														  evo.isAgeMatched()+"-"+
														  member.getAge()+"-"+
														  exactMatch.getAge()+"-"+
														  
														  evo.isGenderMatch()+"-"+
														  member.getGender()+"-"+
														  exactMatch.getGender()+"-"+
														  
														  evo.isRelativeNameMatch()+"-"+
														  member.getRelativeName()+"-"+
														  exactMatch.getRelativeName()+"-"+
														  
														  exactMatch.getVoterIDCardNo()+"-"+
														  exactMatch.getVoterId()+"-"+
																  
													  	 member.getPanchayatName()+"-"+
													  	 exactMatch.getPanchayatName()
														  );
													
					else
						System.out.println("         _1::"+member.getId()+"-"+
								 member.getName()+"-"+
								  exactMatch.getName()+"-"+
								  
								  evo.isAgeMatched()+"-"+
								  member.getAge()+"-"+
								  exactMatch.getAge()+"-"+
								  
								  evo.isGenderMatch()+"-"+
								  member.getGender()+"-"+
								  exactMatch.getGender()+"-"+
								  
								  evo.isRelativeNameMatch()+"-"+
								  member.getRelativeName()+"-"+
								  exactMatch.getRelativeName()+"-"+
								  
								  exactMatch.getVoterIDCardNo()+"-"+
								  exactMatch.getVoterId() +"-"+
								  
								  member.getPanchayatName()+"-"+
								  exactMatch.getPanchayatName()
								  );
					i++;
					
					saveMemberVoterMappingDetails(member,exactMatch,evo,1L,1);


				}
				
				
			}			
		}//end of exact match
    	
		//if( (member.getExactMatchList().size() >1&& member.getSoundexMatchList().size()==0) ||  member.getSoundexMatchList().size() >=1 )
    	
    	
    	
    	for(SoundexVO member:resultList)//soundex match members details
    	{
    		if(member.getBothMatchedList().size() > 0) // gender, relative name and age matched member details
    		{
    			
    			int i=0;
    			for(SoundexVO bothMatched:member.getBothMatchedList())
				{ 
 					
    				if(i==0)
    					System.out.print("RSOUNDEX MATCH");
    					
    					System.out.println(" _"+member.getBothMatchedList().size()+"::"+member.getId()+"-"+
								  member.getName()+"-"+
								  bothMatched.getName()+"-"+
								  
								  bothMatched.isAgeMatched()+"-"+
								  member.getAge()+"-"+
								  bothMatched.getAge()+"-"+
																  
								  bothMatched.isGenderMatch()+"-"+
								  member.getGender()+"-"+
								  bothMatched.getGender()+"-"+
																  
								  bothMatched.isRelativeNameMatch()+"-"+
								  member.getRelativeName()+"-"+
								  bothMatched.getRelativeName()+"-"+
																  
								  bothMatched.getVoterIDCardNo()+"-"+
								  bothMatched.getVoterId()+"-"+
								  
							      member.getPanchayatName()+"-"+
							      bothMatched.getPanchayatName()
							     
								 );
    					
    					i++;
    				saveMemberVoterMappingDetails(member,bothMatched,null,2L,member.getBothMatchedList().size());

				}
    		}else if(member.getRelativeMatchedList().size() >0 )// gender, relative name   matched member details
    		{
    			int i=0;
    			for(SoundexVO relativeMatched:member.getRelativeMatchedList())
				{ 
    				if(i==0)
    					System.out.print("RSOUNDEX MATCH");
    					
    					System.out.println(" _"+member.getRelativeMatchedList().size()+"::"+member.getId()+"-"+
								  member.getName()+"-"+
								  relativeMatched.getName()+"-"+
								  
								  relativeMatched.isAgeMatched()+"-"+
								  member.getAge()+"-"+
								  relativeMatched.getAge()+"-"+
																  
							      relativeMatched.isGenderMatch()+"-"+
								  member.getGender()+"-"+
								  relativeMatched.getGender()+"-"+
								 								  
								  relativeMatched.isRelativeNameMatch()+"-"+
								  member.getRelativeName()+"-"+
								  relativeMatched.getRelativeName()+"-"+
																	  
								  relativeMatched.getVoterIDCardNo()+"-"+
								  relativeMatched.getVoterId()+"-"+
								  
							      member.getPanchayatName()+"-"+
							      relativeMatched.getPanchayatName()
								 );
    					
    					i++;
    					
        				saveMemberVoterMappingDetails(member,relativeMatched,null,2L,member.getRelativeMatchedList().size());

				}
    		}else if(member.getAgeMatchedList().size() >0 )// gender, age   matched member details
    		{
    			int i=0;
    			for(SoundexVO ageMatched:member.getAgeMatchedList())
				{ 
    				
  					
    				if(i==0)
    					System.out.print("RSOUNDEX MATCH");
    					
    					System.out.println(" _"+member.getAgeMatchedList().size()+"::"+member.getId()+"-"+
								  member.getName()+"-"+
								  ageMatched.getName()+"-"+
								  
								  ageMatched.isAgeMatched()+"-"+
								  member.getAge()+"-"+
								  ageMatched.getAge()+"-"+
																  
								  ageMatched.isGenderMatch()+"-"+
								  member.getGender()+"-"+
								  ageMatched.getGender()+"-"+
																  
								  ageMatched.isRelativeNameMatch()+"-"+
								  member.getRelativeName()+"-"+
								  ageMatched.getRelativeName()+"-"+
																  
								  ageMatched.getVoterIDCardNo()+"-"+
								  ageMatched.getVoterId()+"-"+
								  
								  member.getPanchayatName()+"-"+
								  ageMatched.getPanchayatName()
								  );
    					
    					i++;
    					
    					saveMemberVoterMappingDetails(member,ageMatched,null,2L,member.getAgeMatchedList().size());
    				
				}
    			

				} else if (member.getBothMatchedList().size() == 0
						&& member.getAgeMatchedList().size() == 0
						&& member.getRelativeMatchedList().size() == 0)    		{
    			int i=0;
    			for(SoundexVO nomatch:member.getNotMatchedList())
				{ 
    				
    				if(i==0)
    					System.out.print("RSOUNDEX MATCH");
    					
    					System.out.println(" _"+member.getNotMatchedList().size()+"::"+member.getId()+"-"+
								  member.getName()+"-"+
								  nomatch.getName()+"-"+
								  
								  nomatch.isAgeMatched()+"-"+
								  member.getAge()+"-"+
								  nomatch.getAge()+"-"+
								  								  
								  nomatch.isGenderMatch()+"-"+
								  member.getGender()+"-"+
								  nomatch.getGender()+"-"+
																  
								  nomatch.isRelativeNameMatch()+"-"+
								  member.getRelativeName()+"-"+
								  nomatch.getRelativeName()+"-"+
																  
								 nomatch.getVoterIDCardNo()+"-"+
								 nomatch.getVoterId()+"-"+
								  
							     member.getPanchayatName()+"-"+
							     nomatch.getPanchayatName()
								 );
    					i++;
    					
    					saveMemberVoterMappingDetails(member,nomatch,null,2L,member.getNotMatchedList().size());

    					
				}
    		}
    		
    	}
    	
    	
    	for(SoundexVO  member:resultList)//no matched members details
    	{
    		//if(member.isUnMatched())
    		if (member.getBothMatchedList().size() == 0
					&& member.getAgeMatchedList().size() == 0
					&& member.getRelativeMatchedList().size() == 0 && member.getNotMatchedList().size() == 0 && 
					member.getExactMatchList().size() == 0L && member.getExactMatchList1().size() ==0)    
    		{
    			System.out.println("NO MATCH FOUND _0::"+member.getId()+"-"+
    					  member.getName()+"-"+
    					  member.getName()+"-"+
    					  
    					  member.getAge()+"-"+
    					  member.getAge()+"-"+
    					  member.getAge()+"-"+
    					  
    					  member.getGender()+"-"+
    					  member.getGender()+"-"+
    					  member.getGender()+"-"+
    					  
    					 member.getRelativeName()+"-"+
    					 member.getRelativeName()+"-"+
    					 member.getRelativeName()+"-"+
    					  member.getGender()+"-"+
    					  member.getGender()+"-"+
    					  member.getGender()+"-"+
    					  member.getGender());
				saveMemberVoterMappingDetails(member,null,null,3L,0);

    		}
    		
    	}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
        	LOG.error("Exception raised in   processList service method");

    	}
    	
    }
	
    
    public void processList( List<SoundexVO> resultList )
    {
    	LOG.debug("Entered into the processList service method");
    	try
    	{

    	for(SoundexVO  member:resultList)
		{
    		 if(member.getExactMatchList1().size() == 1 && member.getSoundexMatchList1().size() == 0)
 			{
 				 
 				int i=0;
 				for(SoundexVO exactMatch:member.getExactMatchList1())
 				{  
 					List<MatchedTypeVo> lists=null;
 					
 					Long memeberId=member.getId();
 					MatchedTypeVo evo=new MatchedTypeVo();
 					evo.setMemberId(memeberId);
 					lists=	exactMatch.getMaps().get(IConstants.MatchTypes.CExactMatch);
 					if(lists==null)
 						continue;
 					evo=lists.get(lists.indexOf(evo));
 					
 					if(i==0)
 					System.out.println("EXACT MATCH ::"+member.getId()+"-"+
 														  member.getName()+"-"+
 														  exactMatch.getName()+"-"+
 														  
 														  evo.isAgeMatched()+"-"+
 														  member.getAge()+"-"+
 														  exactMatch.getAge()+"-"+
 														  
 														  evo.isGenderMatch()+"-"+
 														  member.getGender()+"-"+
 														  exactMatch.getGender()+"-"+
 														  
 														  evo.isRelativeNameMatch()+"-"+
 														  member.getRelativeName()+"-"+
 														  exactMatch.getRelativeName()+"-"+
 														  
 														  exactMatch.getVoterIDCardNo()+"-"+
 														  exactMatch.getVoterId());
 													
 					else
 						System.out.println("         ::"+member.getId()+"-"+
 								 member.getName()+"-"+
 								  exactMatch.getName()+"-"+
 								  
 								  evo.isAgeMatched()+"-"+
 								  member.getAge()+"-"+
 								  exactMatch.getAge()+"-"+
 								  
 								  evo.isGenderMatch()+"-"+
 								  member.getGender()+"-"+
 								  exactMatch.getGender()+"-"+
 								  
 								  evo.isRelativeNameMatch()+"-"+
 								  member.getRelativeName()+"-"+
 								  exactMatch.getRelativeName()+"-"+
 								  
 								  exactMatch.getVoterIDCardNo()+"-"+
 								  exactMatch.getVoterId());
 					i++;
					
					//saveMemberVoterMappingDetails(member,exactMatch,evo,4L);


				}
				
			}			
		
    	
    	
    	
    	 if(member.getExactMatchList().size() == 1 && member.getSoundexMatchList().size() == 0)
			{
				 
				int i=0;
				for(SoundexVO exactMatch:member.getExactMatchList())
				{  
					List<MatchedTypeVo> lists=null;
					
					Long memeberId=member.getId();
					MatchedTypeVo evo=new MatchedTypeVo();
					evo.setMemberId(memeberId);
					lists=	exactMatch.getMaps().get(IConstants.MatchTypes.PExactMatch);
					evo=lists.get(lists.indexOf(evo));
					
					if(i==0)
					System.out.println("EXACT MATCH ::"+member.getId()+"-"+
														  member.getName()+"-"+
														  exactMatch.getName()+"-"+
														  
														  evo.isAgeMatched()+"-"+
														  member.getAge()+"-"+
														  exactMatch.getAge()+"-"+
														  
														  evo.isGenderMatch()+"-"+
														  member.getGender()+"-"+
														  exactMatch.getGender()+"-"+
														  
														  evo.isRelativeNameMatch()+"-"+
														  member.getRelativeName()+"-"+
														  exactMatch.getRelativeName()+"-"+
														  
														  exactMatch.getVoterIDCardNo()+"-"+
														  exactMatch.getVoterId());
													
					else
						System.out.println("         ::"+member.getId()+"-"+
								 member.getName()+"-"+
								  exactMatch.getName()+"-"+
								  
								  evo.isAgeMatched()+"-"+
								  member.getAge()+"-"+
								  exactMatch.getAge()+"-"+
								  
								  evo.isGenderMatch()+"-"+
								  member.getGender()+"-"+
								  exactMatch.getGender()+"-"+
								  
								  evo.isRelativeNameMatch()+"-"+
								  member.getRelativeName()+"-"+
								  exactMatch.getRelativeName()+"-"+
								  
								  exactMatch.getVoterIDCardNo()+"-"+
								  exactMatch.getVoterId());
					i++;
					
				//	saveMemberVoterMappingDetails(member,exactMatch,evo,1L);


				}
				
				
			}			
		}//end of exact match
    	
		//if( (member.getExactMatchList().size() >1&& member.getSoundexMatchList().size()==0) ||  member.getSoundexMatchList().size() >=1 )

    	for(SoundexVO  member:resultList)
    	{
			if( (member.getExactMatchList1().size() >1&& member.getSoundexMatchList1().size()==0) ||  member.getSoundexMatchList1().size() >=1 )
    		{
    			
    			
    			int j=0;
    			for(SoundexVO soundex:member.getExactMatchList1())
    			{
	                List<MatchedTypeVo> lists=null;
					
					Long memeberId=member.getId();
					MatchedTypeVo evo=new MatchedTypeVo();
					evo.setMemberId(memeberId);
					lists=	soundex.getMaps().get(IConstants.MatchTypes.CExactMatch);
					if(lists==null)
						continue;
					evo=lists.get(lists.indexOf(evo));
    				
    				if(j==0)
    				System.out.println("EXACT MATCH ::"+member.getId()+"-"+
    													  member.getName()+"-"+
    													  soundex.getName()+"-"+
    													  
    													  
    													  evo.isAgeMatched()+"-"+
    													  member.getAge()+"-"+
    													  soundex.getAge()+"-"+
    													  
    													  
    													 evo.isGenderMatch()+"-"+
    													 member.getGender()+"-"+
    													 soundex.getGender()+"-"+
    																											 
    													 evo.isRelativeNameMatch()+"-"+
    													 member.getRelativeName()+"-"+
    													 soundex.getRelativeName()+"-"+
    																											 
    													 soundex.getVoterIDCardNo()+"-"+
    													 soundex.getVoterId());
    												
    				else
    					System.out.println("           ::"+member.getId()+"-"+
    							  member.getName()+"-"+
								  soundex.getName()+"-"+
								  
								  
								  evo.isAgeMatched()+"-"+
								  member.getAge()+"-"+
								  soundex.getAge()+"-"+
								  
								  
								 evo.isGenderMatch()+"-"+
								 member.getGender()+"-"+
								 soundex.getGender()+"-"+
																						 
								 evo.isRelativeNameMatch()+"-"+
								 member.getRelativeName()+"-"+
								 soundex.getRelativeName()+"-"+
																						 
								 soundex.getVoterIDCardNo()+"-"+
								 soundex.getVoterId());
    							  
    						
    				j++;
    				
					//saveMemberVoterMappingDetails(member,soundex,evo,4L);

    			}
    			
    			
    			int i=0;
    			for(SoundexVO soundex:member.getSoundexMatchList1())
    			{
    				
                    List<MatchedTypeVo> lists=null;
					
					Long memeberId=member.getId();
					MatchedTypeVo evo=new MatchedTypeVo();
					evo.setMemberId(memeberId);
					lists=	soundex.getMaps().get(IConstants.MatchTypes.CsoundexMatch);
					
					if(lists==null)
						continue;
					
					evo=lists.get(lists.indexOf(evo));
					
    				if(i==0)
    				System.out.println("RSOUNDEX MATCH ::"+member.getId()+"-"+
    						member.getName()+"-"+
							  soundex.getName()+"-"+
							  
							  
							  evo.isAgeMatched()+"-"+
							  member.getAge()+"-"+
							  soundex.getAge()+"-"+
							  
							  
							 evo.isGenderMatch()+"-"+
							 member.getGender()+"-"+
							 soundex.getGender()+"-"+
																					 
							 evo.isRelativeNameMatch()+"-"+
							 member.getRelativeName()+"-"+
							 soundex.getRelativeName()+"-"+
																					 
							 soundex.getVoterIDCardNo()+"-"+
							 soundex.getVoterId());
    													  
    												
    				else
    					System.out.println("           ::"+member.getId()+"-"+
    							member.getName()+"-"+
								  soundex.getName()+"-"+
								  
								  
								  evo.isAgeMatched()+"-"+
								  member.getAge()+"-"+
								  soundex.getAge()+"-"+
								  
								  
								 evo.isGenderMatch()+"-"+
								 member.getGender()+"-"+
								 soundex.getGender()+"-"+
																						 
								 evo.isRelativeNameMatch()+"-"+
								 member.getRelativeName()+"-"+
								 soundex.getRelativeName()+"-"+
																						 
								 soundex.getVoterIDCardNo()+"-"+
								 soundex.getVoterId());
    							  
    						
    				i++;
    				
					//saveMemberVoterMappingDetails(member,soundex,evo,5L);

    			}
    		}
			
			
			
			if( (member.getExactMatchList().size() >1&& member.getSoundexMatchList().size()==0) ||  member.getSoundexMatchList().size() >=1 )
    		{
    			
    			
    			int j=0;
    			for(SoundexVO soundex:member.getExactMatchList())
    			{
	                List<MatchedTypeVo> lists=null;
					
					Long memeberId=member.getId();
					MatchedTypeVo evo=new MatchedTypeVo();
					evo.setMemberId(memeberId);
					lists=	soundex.getMaps().get(IConstants.MatchTypes.PExactMatch);
					evo=lists.get(lists.indexOf(evo));
    				
    				if(j==0)
    				System.out.println("EXACT MATCH ::"+member.getId()+"-"+
    													  member.getName()+"-"+
    													  soundex.getName()+"-"+
    													  
    													  
    													  evo.isAgeMatched()+"-"+
    													  member.getAge()+"-"+
    													  soundex.getAge()+"-"+
    													  
    													  
    													 evo.isGenderMatch()+"-"+
    													 member.getGender()+"-"+
    													 soundex.getGender()+"-"+
    																											 
    													 evo.isRelativeNameMatch()+"-"+
    													 member.getRelativeName()+"-"+
    													 soundex.getRelativeName()+"-"+
    																											 
    													 soundex.getVoterIDCardNo()+"-"+
    													 soundex.getVoterId());
    												
    				else
    					System.out.println("           ::"+member.getId()+"-"+
    							  member.getName()+"-"+
								  soundex.getName()+"-"+
								  
								  
								  evo.isAgeMatched()+"-"+
								  member.getAge()+"-"+
								  soundex.getAge()+"-"+
								  
								  
								 evo.isGenderMatch()+"-"+
								 member.getGender()+"-"+
								 soundex.getGender()+"-"+
																						 
								 evo.isRelativeNameMatch()+"-"+
								 member.getRelativeName()+"-"+
								 soundex.getRelativeName()+"-"+
																						 
								 soundex.getVoterIDCardNo()+"-"+
								 soundex.getVoterId());
    							  
    						
    				j++;
    				
					//saveMemberVoterMappingDetails(member,soundex,evo,1L);

    			}
    			
    			
    			int i=0;
    			for(SoundexVO soundex:member.getSoundexMatchList())
    			{
    				
                    List<MatchedTypeVo> lists=null;
					
					Long memeberId=member.getId();
					MatchedTypeVo evo=new MatchedTypeVo();
					evo.setMemberId(memeberId);
					lists=	soundex.getMaps().get(IConstants.MatchTypes.PSoundexMatch);
					evo=lists.get(lists.indexOf(evo));
					
    				if(i==0)
    				System.out.println("RSOUNDEX MATCH ::"+member.getId()+"-"+
    						member.getName()+"-"+
							  soundex.getName()+"-"+
							  
							  
							  evo.isAgeMatched()+"-"+
							  member.getAge()+"-"+
							  soundex.getAge()+"-"+
							  
							  
							 evo.isGenderMatch()+"-"+
							 member.getGender()+"-"+
							 soundex.getGender()+"-"+
																					 
							 evo.isRelativeNameMatch()+"-"+
							 member.getRelativeName()+"-"+
							 soundex.getRelativeName()+"-"+
																					 
							 soundex.getVoterIDCardNo()+"-"+
							 soundex.getVoterId());
    													  
    												
    				else
    					System.out.println("           ::"+member.getId()+"-"+
    							member.getName()+"-"+
								  soundex.getName()+"-"+
								  
								  
								  evo.isAgeMatched()+"-"+
								  member.getAge()+"-"+
								  soundex.getAge()+"-"+
								  
								  
								 evo.isGenderMatch()+"-"+
								 member.getGender()+"-"+
								 soundex.getGender()+"-"+
																						 
								 evo.isRelativeNameMatch()+"-"+
								 member.getRelativeName()+"-"+
								 soundex.getRelativeName()+"-"+
																						 
								 soundex.getVoterIDCardNo()+"-"+
								 soundex.getVoterId());
    							  
    						
    				i++;
    				
    				//saveMemberVoterMappingDetails(member,soundex,evo,2L);
    			}
    		}//end of soundex match
    	}
    	
    	
    	for(SoundexVO  member:resultList)
    	{
    		if(member.isUnMatched())
    		{
    			System.out.println("NO MATCH FOUND ::"+member.getId()+"-"+
    					  member.getName()+"-"+
    					  member.getName()+"-"+
    					  
    					  member.getAge()+"-"+
    					  member.getAge()+"-"+
    					  member.getAge()+"-"+
    					  
    					  member.getGender()+"-"+
    					  member.getGender()+"-"+
    					  member.getGender()+"-"+
    					  
    					 member.getRelativeName()+"-"+
    					 member.getRelativeName()+"-"+
    					 member.getRelativeName()+"-"+
    					  member.getGender()+"-"+
    					  member.getGender());
				//saveMemberVoterMappingDetails(member,null,null,6L);

    		}
    		
    	}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
        	LOG.error("Exception raised in   processList service method");

    	}
    	
    }
    
    /**
     * This method iterate all the details to set matched details
     * @param resultList
     */
   
    public void calculateCateries( List<SoundexVO> resultList )
    {

    	try {
    		
			for(SoundexVO  member:resultList)//exact match details(If more than one exact match exist for a member , then it is considered as soundex match)
			{
				if(member.getExactMatchList1().size() == 1 && member.getSoundexMatchList1().size() == 0)
				{
					for(SoundexVO exactMatch:member.getExactMatchList1())
					{
						setCategories(exactMatch, member,IConstants.MatchTypes.CExactMatch,"exact");
					}
				}
				 if(member.getExactMatchList().size() == 1 && member.getSoundexMatchList().size() == 0)
				 {
						for(SoundexVO exactMatch:member.getExactMatchList())
						{
							setCategories(exactMatch, member,IConstants.MatchTypes.PExactMatch,"exact");
						}
				 }
			}
			
			
			
			for(SoundexVO  member:resultList)//soundex match details
			{
				if( (member.getExactMatchList1().size() >1 && member.getSoundexMatchList1().size() ==0) || member.getSoundexMatchList1().size()>=1 )
				{
				
					for(SoundexVO soundex:member.getExactMatchList1())
					{
						setCategories(soundex, member,IConstants.MatchTypes.CExactMatch,"");
					}
					
					
					
					for(SoundexVO soundex:member.getSoundexMatchList1())
					{
						setCategories(soundex, member,IConstants.MatchTypes.CsoundexMatch,"");

					}
				}
			
			if((member.getExactMatchList().size() >1 && member.getSoundexMatchList().size() ==0) || member.getSoundexMatchList().size()>=1 )
			{
				for(SoundexVO soundex:member.getExactMatchList())
				{
					setCategories(soundex, member,IConstants.MatchTypes.PExactMatch,"");
				}
				
				for(SoundexVO soundex:member.getSoundexMatchList())
				{
					setCategories(soundex, member,IConstants.MatchTypes.PSoundexMatch,"");

				}
			}
   }//for
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in calculateCateries service method");
		}
    	
    	
    }
    	
    	
    /**
     * This method will set age matched,gender matched,relative name matched details
     * @param soundexVO
     * @param memberVO
     * @param type
     * @param matchType
     */
    public void setCategories(SoundexVO soundexVO,SoundexVO memberVO,IConstants.MatchTypes type,String matchType)
	{
	
		    try {
				Map<MatchTypes, List<MatchedTypeVo>> maps= soundexVO.getMaps();
				MatchedTypeVo vo=new MatchedTypeVo();
				List<MatchedTypeVo> list=null;
				Long memberId=memberVO.getId();
				vo.setMemberId(memberId);
				
				if(maps==null)		
				{

				    maps=new HashMap<IConstants.MatchTypes, List<MatchedTypeVo>>();
				    list=new ArrayList<MatchedTypeVo>();
				    list.add(vo);
				    maps.put(type, list);
				    soundexVO.setMaps(maps);
				    
				}else {
					    	
					    list= maps.get(type);
					    
					    if(list==null)
					    {
							    list=new ArrayList<MatchedTypeVo>();
							    list.add(vo);
							    maps.put(type, list);
					    }else {
							    int index=list.indexOf(vo);
							    if(index!=-1)
							    vo=list.get(index);
							    else
							    list.add(vo);
					    }
				}

				boolean gender = soundexVO.getGender().equalsIgnoreCase(memberVO.getGender());

				if(gender)
				{
					vo.setGenderMatch(true);
				}
				
				RefinedSoundex soundexAlg=new RefinedSoundex();
				String voterName=soundexVO.getRelativeName().trim().toLowerCase();
				String memeberName=memberVO.getRelativeName().trim().toLowerCase();
				 boolean relativeName = false;

				if(memeberName != null && !memeberName.equalsIgnoreCase(""))
				{ 
					     relativeName = soundexVO.getRelativeName().equalsIgnoreCase(memberVO.getRelativeName());
					
					    if(!relativeName)
					    {
					    	if(checkForNameExist(voterName, memeberName, soundexAlg))
					    	{
							    relativeName=true;

					    	}
						else {
							    String[] memberName = null;
							    memberName =memeberName.split("\\s+");
							
							    if(memberName.length !=2)
							    memberName = memeberName.split(".");
							
							    if(memberName.length == 2)
								 {
								    // String firstString = memberName[0].trim()+""+memberName[1].trim();
								    String lastString = memberName[1].trim()+""+memberName[0].trim();
								    
								    if(soundexAlg.soundex(voterName).equalsIgnoreCase(soundexAlg.soundex(lastString)) || voterName.replaceAll("[\\s\\.+]", "").contains(lastString.replaceAll("[\\s\\.+]", "")) ||
								    lastString.replaceAll("[\\s\\.+]", "").contains(voterName.replaceAll("[\\s\\.+]", "")) ||
								    soundexAlg.soundex(voterName).replaceAll("0", "").equalsIgnoreCase(soundexAlg.soundex(lastString).replaceAll("0", "")) || 
								    soundexAlg.soundex(voterName).contains(soundexAlg.soundex(memberName[0])) || soundexAlg.soundex(voterName).contains(soundexAlg.soundex(memberName[1])))
								    {
								    	relativeName=true;
								    }
								 }
					   }
					    }
					
					    if(relativeName)
					    {
					    vo.setRelativeNameMatch(true);
					    }
				}

				long difference=memberVO.getAge()-soundexVO.getAge();
				boolean age=false;
				if(difference<0 && difference>=-4L )
				age=true;
				else if(difference>0 && difference<=4L )
				age=true;
				else if(difference==0)
				age=true;
				if(age)
				{
					vo.setAgeMatched(true);		
				}
				
				if(!matchType.equalsIgnoreCase("exact"))
				if(gender)
				{
				
				    SoundexVO vo1 = new SoundexVO();
				    
				    vo1.setVoterId(soundexVO.getVoterId());
				    vo1.setVoterIDCardNo(soundexVO.getVoterIDCardNo());
				    vo1.setAgeMatched(age);
				    vo1.setRelativeNameMatch(relativeName);
				    vo1.setGenderMatch(gender);
				    vo1.setAge(soundexVO.getAge());
				    vo1.setGender(soundexVO.getGender());
				    vo1.setRelativeName(soundexVO.getRelativeName());
				    vo1.setName(soundexVO.getName());
				    vo1.setPanchayatName(soundexVO.getPanchayatName());
				    
				   if(age && relativeName) 
					   memberVO.getBothMatchedList().add(vo1);
				   else if(age)
					   memberVO.getAgeMatchedList().add(vo1);
				   else if(relativeName)
					   memberVO.getRelativeMatchedList().add(vo1);
				   else
					   memberVO.getNotMatchedList().add(vo1);
				
				}else
				{
					memberVO.setUnMatched(true);	//if gender is not matched we are considering it as matched	    	
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised in setCategories service method");
			}
	 }
    
    /*public void constLevelDest(List<SoundexVO>  membersList, Map<String, List<SoundexVO>> totalVoters )
	
	{
		System.out.println("input size"+membersList);
	//	List<Object[]> objs=	boothPublicationVoterDAO.getVotersDetailsForConstId(8L, 10L);
		//List<SoundexVO> votersDetails = new ArrayList<SoundexVO>();
		//setVotersDetails(votersDetails,objs);
		Map<String,List<SoundexVO> > mapIds= new HashMap<String,List<SoundexVO> >();
    for (SoundexVO memberVO : membersList) {
			
		List<SoundexVO> votersDetails = new ArrayList<SoundexVO>();

    	//List<SoundexVO> votersDetails =null;
    	if(!memberVO.isUnMatched())
    		continue;
    	String name1=memberVO.getName().substring(0, 1).trim().toLowerCase();
    	if(mapIds.containsKey(name1))
    	{
    		votersDetails=mapIds.get(name1);
    	}else {
        	List<Object[]> objs=	boothPublicationVoterDAO.getVotersDetailsForConstIdBasedOnName(8L, 10L,name1);
    		setVotersDetails(votersDetails,objs);
    		mapIds.put(name1, votersDetails);
    	}
    				
    	//List<SoundexVO> votersDetails = new ArrayList<SoundexVO>();
    	
    	RefinedSoundex soundex = new RefinedSoundex();
		
		for(SoundexVO memberVO:membersList)
		{
			//Collections.b
    	int flagset=0;
			for(SoundexVO voterVO:votersDetails)
			{
				boolean name  = false;
				String memName=memberVO.getName().trim().replaceAll("[\\s\\.+]", "").trim();
				String voterName=voterVO.getName().trim().replaceAll("[\\s\\.+]", "").trim();
				//exact match
				if(memName.equalsIgnoreCase(voterName))
				{
					name = true;
					//memberVO.setExactMatchCount(memberVO.getExactMatchCount()+1);
					memberVO.getExactMatchList1().add(voterVO);
				}else
				{
				  //soundex match
					 name = soundex.soundex(memName).equalsIgnoreCase(soundex.soundex(voterName));
					 
					 if(name) {
						 memberVO.getSoundexMatchList1().add(voterVO);
					 }
					 else if (soundex
								.soundex(memberVO.getName())
								.replaceAll("0", "")
								.equalsIgnoreCase(
										soundex.soundex(voterVO.getName())
												.replaceAll("0", "")))	{
							
							memberVO.getSoundexMatchList1().add(voterVO);
							memberVO.setSplit(true);
					 }
					   else if(flagset==0)
					   {
						 //string split and identify strings
						// String[] memNameSplited=memberVO.getName().split("[\\s\\.+]");
						 String voterNameSplited=voterVO.getName().trim().replaceAll("[\\s\\.+]", "");
					 
						 String[] memberName = null;
						 memberName = memberVO.getName().split("\\s+");

						 if(memberName.length !=2)
						 memberName = memberVO.getName().split(".");

						 if(memberName.length == 2)
						 {
						// String firstString = memberName[0].trim()+""+memberName[1].trim();
						 String lastString = memberName[1].trim()+""+memberName[0].trim();
						 //check for that having last name 
						 
						
							 
							 String firstLet=lastString.substring(0, 1).trim().toLowerCase();
							 
							 if(firstLet.equalsIgnoreCase(name1))
								 continue;
							 
							 List<SoundexVO> ids=null;
							 if(mapIds.containsKey(firstLet))
								 ids=mapIds.get(firstLet);
							 else {
								 ids= new ArrayList<SoundexVO>();
								     List<Object[]> objs=	boothPublicationVoterDAO.getVotersDetailsForConstIdBasedOnName(8L, 10L,firstLet);
						    		setVotersDetails(ids,objs);
						    		mapIds.put(firstLet, ids);
							 }
							 for (SoundexVO soundexVO : ids) {
								 
								
							if (soundex.soundex(voterVO.getName().trim()).equalsIgnoreCase(soundex.soundex(lastString)) || 
									
									soundex.soundex(voterVO.getName().replaceAll("[\\s\\.+]", "")).equalsIgnoreCase(soundex.soundex(lastString.replaceAll("[\\s\\.+]", "")))	
									
									
									)
								
							 {
								    System.out.println("matched" +cnt++);
								 
								    memberVO.getSoundexMatchList1().add(voterVO);
								   
								 memberVO.setSplit(true);
						      }
						 	 }//for
							 flagset++;
						 }
						
					   } //else

				}
				
				if(name)
				{
					
					boolean gender = voterVO.getGender().equalsIgnoreCase(memberVO.getGender());
					
					if(gender)
					{
						voterVO.setGenderMatch(true);
					}
					
					boolean relativeName = voterVO.getRelativeName().equalsIgnoreCase(memberVO.getRelativeName());

					if(relativeName)
					{
						voterVO.setRelativeNameMatch(true);
					}
					
					
					long low = memberVO.getAge() - 4;
					long high = memberVO.getAge() + 4;
					
					boolean age = (voterVO.getAge() >= low) &&( voterVO.getAge() <= high);
					
					
					if(age)
					{
						voterVO.setAgeMatched(true);
						
					}
				//}
			}
			
			if(memberVO.getExactMatchList1().size() == 0 && memberVO.getSoundexMatchList1().size() == 0)
			{
				memberVO.setUnMatched(true);
			}else
			{
				memberVO.setUnMatched(false);
			}
			
		//}
    	
    	
    	
		}
    
    System.out.println("end of loop"+membersList.size());
    List<SoundexVO>  resultList=membersList;
	for(SoundexVO  member:resultList)
	{
		if(member.getExactMatchList1().size() != 1 && member.getSoundexMatchList1().size() >=0 )
		{
			
			
			int j=0;
			for(SoundexVO soundex:member.getExactMatchList1())
			{
				if(j==0)
				System.out.println("EXACT MATCH ::"+member.getId()+"-"+
													  member.getName()+"-"+
													  soundex.getName()+"-"+
													  
													  
													  soundex.isAgeMatched()+"-"+
													  member.getAge()+"-"+
													  soundex.getAge()+"-"+
													  
													  
													 soundex.isGenderMatch()+"-"+
													 member.getGender()+"-"+
													 soundex.getGender()+"-"+
																											 
													 soundex.isRelativeNameMatch()+"-"+
													 member.getRelativeName()+"-"+
													 soundex.getRelativeName()+"-"+
																											 
													 soundex.getVoterIDCardNo()+"-"+
													 soundex.getVoterId());
												
				else
					System.out.println("           ::"+member.getId()+"-"+
							  member.getName()+"-"+
							  soundex.getName()+"-"+
							  
							  
							  soundex.isAgeMatched()+"-"+
							  member.getAge()+"-"+
							  soundex.getAge()+"-"+
							  
							  
							 soundex.isGenderMatch()+"-"+
							 member.getGender()+"-"+
							 soundex.getGender()+"-"+
																					 
							 soundex.isRelativeNameMatch()+"-"+
							 member.getRelativeName()+"-"+
							 soundex.getRelativeName()+"-"+
																					 
							 soundex.getVoterIDCardNo()+"-"+
							 soundex.getVoterId());
							  
						
				j++;
			}
			
			
			int i=0;
			for(SoundexVO soundex:member.getSoundexMatchList1())
			{
				if(i==0)
				System.out.println("RSOUNDEX MATCH ::"+member.getId()+"-"+
													  member.getName()+"-"+
													  soundex.getName()+"-"+
													  
													  
													  soundex.isAgeMatched()+"-"+
													  member.getAge()+"-"+
													  soundex.getAge()+"-"+
													  
													  
													 soundex.isGenderMatch()+"-"+
													 member.getGender()+"-"+
													 soundex.getGender()+"-"+
																											 
													 soundex.isRelativeNameMatch()+"-"+
													 member.getRelativeName()+"-"+
													 soundex.getRelativeName()+"-"+
																											 
													 soundex.getVoterIDCardNo()+"-"+
													 soundex.getVoterId());
													  
												
				else
					System.out.println("           ::"+member.getId()+"-"+
							  member.getName()+"-"+
							  soundex.getName()+"-"+
							  
							  
							  soundex.isAgeMatched()+"-"+
							  member.getAge()+"-"+
							  soundex.getAge()+"-"+
							  
							  
							 soundex.isGenderMatch()+"-"+
							 member.getGender()+"-"+
							 soundex.getGender()+"-"+
																					 
							 soundex.isRelativeNameMatch()+"-"+
							 member.getRelativeName()+"-"+
							 soundex.getRelativeName()+"-"+
																					 
							 soundex.getVoterIDCardNo()+"-"+
							 soundex.getVoterId());
							  
						
				i++;
			}
		}
	}
	
	
	for(SoundexVO  member:resultList)
	{
		if(member.isUnMatched())
		{
			System.out.println("NO MATCH FOUND ::"+member.getId()+"-"+
					  member.getName()+"-"+
					  member.getName()+"-"+
					  
					  member.getAge()+"-"+
					  member.getAge()+"-"+
					  member.getAge()+"-"+
					  
					  member.getGender()+"-"+
					  member.getGender()+"-"+
					  member.getGender()+"-"+
					  
					 member.getRelativeName()+"-"+
					 member.getRelativeName()+"-"+
					 member.getRelativeName()+"-"+
					  member.getGender()+"-"+
					  member.getGender());
			

		}
		
	}
	
	
	
	
		
	
	
	}*/
    
    
    /**
     * This method is will return weather voterName and memberName both are matched by using soundex 
     * algorithm with different approaches
     * @param voterName
     * @param memberName
     * @param soundexAlg
     * @return
     */
    public static Boolean checkForNameExist(String voterName,String memberName,RefinedSoundex soundexAlg)
    {
		//LOG.debug("Entered into the checkForNameExist service method");

    	 boolean flag = false;
    	try
    	{
		   
		    String[] firstNamesArray = voterName.split(" ");
		    String[] memberNameArray = memberName.split(" ");
		    
		    
			    if(firstNamesArray.length == memberNameArray.length )//if both having the same no of words
			    {
			    		if(firstNamesArray.length == 2)//if both having 2 words exactly.Here we implemented for the names which contains only 2 words
						{
										String voterFirstName = firstNamesArray[0];
										String voterLasteName = firstNamesArray[1];
										
										String memberFirstName = memberNameArray[0];
										String memberLastName = memberNameArray[1];
										
								        //comparing member voter first name  and member first name by 
										//case1: applying soundex to both names
										//case2: replacing all the 0's after applying refined soundex
										
								    if(soundexAlg.soundex(voterFirstName).trim().equalsIgnoreCase(soundexAlg.soundex(memberFirstName)) || 
								       soundexAlg.soundex(voterFirstName).replaceAll("0", "").equalsIgnoreCase(soundexAlg.soundex(memberFirstName).replaceAll("0", "")))
								    {
								
										    Integer voterNameSize = voterLasteName.length();
										    Integer memberNameSize = memberLastName.length();
								
										    if(voterNameSize > memberNameSize)
										    {
											    if(voterLasteName.toUpperCase().indexOf(memberLastName.toUpperCase())==0)
											    {
											      flag = true;
											    }
										    }
										    else
										    {
											    if(memberLastName.toUpperCase().indexOf(voterLasteName.toUpperCase())==0)
											    {
											      flag = true;
											    }
										    }
								    }
								    //comparing  voter first name  and member last name by 
								    //case1:applying soundex to both
								    //case2:replacing 0's in both after applying soundex
								    else if(soundexAlg.soundex(voterFirstName).trim().equalsIgnoreCase(soundexAlg.soundex(memberLastName)) ||
								    		soundexAlg.soundex(voterFirstName).replaceAll("0", "").equalsIgnoreCase(soundexAlg.soundex(memberLastName).replaceAll("0", "")))
								    {
								    	
										    Integer voterNameSize = voterLasteName.length();
										    Integer memberNameSize = memberFirstName.length();
								
										    if(voterNameSize > memberNameSize)
										    {
											    if(voterLasteName.toUpperCase().indexOf(memberFirstName.toUpperCase())==0)
											    {
											    	flag = true;
											    }
										    }
										    else
										    {
											    if(memberFirstName.toUpperCase().indexOf(voterLasteName.toUpperCase())==0)
											    {
											    	flag = true;
											    }
										    }
								    }
								    //comparing  voter last name  and member first name by 
								    //case1:applying soundex to both
								    //case2:replacing 0's in both after applying soundex
								    else if(soundexAlg.soundex(voterLasteName).trim().equalsIgnoreCase(soundexAlg.soundex(memberFirstName)) || 
								    		soundexAlg.soundex(voterLasteName).replaceAll("0", "").equalsIgnoreCase(soundexAlg.soundex(memberFirstName).replaceAll("0", "")))
								    {
										    Integer voterNameSize = voterFirstName.length();
										    Integer memberNameSixe = memberLastName.length();
										
										    if(voterNameSize > memberNameSixe)
										    {
											    if(voterFirstName.toUpperCase().indexOf(memberLastName.toUpperCase())==0)
											    {
											    	flag = true;
											    }
										    }
										    else
										    {
											    if(memberLastName.toUpperCase().indexOf(voterFirstName.toUpperCase())==0)
											    {
											    	flag = true;
											    }
										    }
								    }
								    //comparing  voter last name  and member last name by 
								    //case1:applying soundex to both
								    //case2:replacing 0's in both after applying soundex
								    else if(soundexAlg.soundex(voterLasteName).trim().equalsIgnoreCase(soundexAlg.soundex(memberLastName)) ||
								    		soundexAlg.soundex(voterLasteName).replaceAll("0", "").equalsIgnoreCase(soundexAlg.soundex(memberLastName).replaceAll("0", "")))
								    {
									    Integer voterNameSize = voterFirstName.length();
									    Integer memberNameSize = memberFirstName.length();
								
									    if(voterNameSize > memberNameSize)
									    {
										    if(voterFirstName.toUpperCase().indexOf(memberFirstName.toUpperCase())==0)
										    {
										    	flag = true;
										    }
									    }
									    else
									    {
										    if(memberFirstName.toUpperCase().indexOf(voterFirstName.toUpperCase())==0)
										    {
										    	  flag = true;
										    }
									    }
								    }
					}
			    	/*	if(flag)
			    			System.out.println("hello");*/
			    }
			
			    else if(!flag)
			    {
				    if(soundexAlg.soundex(voterName).equalsIgnoreCase(soundexAlg.soundex(memberName))||
				    voterName.replaceAll("[\\s\\.+]", "").contains(memberName.replaceAll("[\\s\\.+]", ""))||
				    memberName.replaceAll("[\\s\\.+]", "").contains(voterName.replaceAll("[\\s\\.+]", "")) ||
				    soundexAlg.soundex(voterName).replaceAll("0", "").equalsIgnoreCase(soundexAlg.soundex(memberName).replaceAll("0", "")))
				    {
				    	
				    	flag = true;
				     
				    }
			    }
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		LOG.error("Exception raised in checkForNameExist service method");
    	}
    return flag;
    }
	
}
