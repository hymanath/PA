package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.language.RefinedSoundex;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.ITdMemberDAO;
import com.itgrids.partyanalyst.dto.SoundexVO;
import com.itgrids.partyanalyst.service.ISoundexService;

public class SoundexService implements ISoundexService {
	private static final Logger LOG = Logger.getLogger(SoundexService.class);
	
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private ITdMemberDAO tdMemberDAO;
	private IPanchayatDAO panchayatDAO;

	
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


	public List<SoundexVO> getMappedVoterDetailsByUsingSoundexByPanchayatId(Long constituewcyId)
	{
		LOG.debug("Entered into the getMappedVoterDetailsByUsingSoundexByPanchayatId service method");
		List<SoundexVO>  resultList = new ArrayList<SoundexVO>();
		List<SoundexVO>  membersList = null;

		try
		{
			List<Object[]> panchayatDEtails = panchayatDAO.getPanchayatsByConstituencyId(constituewcyId);
			
			for(Object[] panchayatList:panchayatDEtails)
			{
				List<Object[]> memberDetails = tdMemberDAO.getMembersDetailsBypanchayatId((Long)panchayatList[0]);
				
				if(memberDetails == null || memberDetails.size() == 0)
					continue;
				
				membersList = new ArrayList<SoundexVO>();				
				setMembersDetails(membersList,memberDetails);
				
			List<Object[]> list = boothPublicationVoterDAO.getVotersDetailsForPanchayatByPublicationIdAndPAnchayatId((Long)panchayatList[0], 10L);
			
				List<SoundexVO> votersDetails = new ArrayList<SoundexVO>();
				setVotersDetails(votersDetails,list);

				buildAllTheMemberAndMatchedVotersDetails(membersList,votersDetails);
				resultList.addAll(membersList);
			}
			
			displayMemberMatchingDetails(resultList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getMappedVoterDetailsByUsingSoundexByPanchayatId service method");
		}
		return membersList;
	}
	
	public void displayMemberMatchingDetails(List<SoundexVO> resultList)
	{
		LOG.debug("Entered to displayMemberMatchingDetails service method");
		
		try
		{
			
		for(SoundexVO  member:resultList)
		{
			 if(member.getExactMatchList().size() == 1 && member.getSoundexMatchList().size() == 0)
			{
				int i=0;
				for(SoundexVO exactMatch:member.getExactMatchList())
				{
					if(i==0)
					System.out.println("EXACT MATCH ::"+member.getId()+"-"+
														  member.getName()+"-"+
														  exactMatch.getName()+"-"+
														  
														  exactMatch.isAgeMatched()+"-"+
														  member.getAge()+"-"+
														  exactMatch.getAge()+"-"+
														  
														  exactMatch.isGenderMatch()+"-"+
														  member.getGender()+"-"+
														  exactMatch.getGender()+"-"+
														  
														  exactMatch.isRelativeNameMatch()+"-"+
														  member.getRelativeName()+"-"+
														  exactMatch.getRelativeName()+"-"+
														  
														  exactMatch.getVoterIDCardNo()+"-"+
														  exactMatch.getVoterId());
													
					else
						System.out.println("         ::"+member.getId()+"-"+
								  member.getName()+"-"+
								  exactMatch.getName()+"-"+
								  
								  exactMatch.isAgeMatched()+"-"+
								  member.getAge()+"-"+
								  exactMatch.getAge()+"-"+
								  
								  exactMatch.isGenderMatch()+"-"+
								  member.getGender()+"-"+
								  exactMatch.getGender()+"-"+
								  
								  exactMatch.isRelativeNameMatch()+"-"+
								  member.getRelativeName()+"-"+
								  exactMatch.getRelativeName()+"-"+
								  
								  exactMatch.getVoterIDCardNo()+"-"+
								  exactMatch.getVoterId());
					i++;
				}
			}			
		}
		
		
		for(SoundexVO  member:resultList)
		{
			if(member.getExactMatchList().size() != 1 && member.getSoundexMatchList().size() >=0 )
			{
				
				
				int j=0;
				for(SoundexVO soundex:member.getExactMatchList())
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
				for(SoundexVO soundex:member.getSoundexMatchList())
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
			if(member.getExactMatchList().size() == 0 && member.getSoundexMatchList().size() == 0)
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
		
	}catch(Exception e)
	{
		e.printStackTrace();
		LOG.error("Exception raised in displayMemberMatchingDetails service method");

		
	}
	}
	
	public void setVotersDetails(List<SoundexVO> votersDetails , List<Object[]> list)
	{
		LOG.debug("Entered into the setVotersDetails service method");

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
                
                votersDetails.add(voter);
			}
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in  the setVotersDetails service method");

		}
		
	}
	
	public void setMembersDetails(List<SoundexVO> membersList , List<Object[]> memberDetails)
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
						boolean name = soundex.soundex(memberVO.getName()).equalsIgnoreCase(soundex.soundex(voterVO.getName()));
						 
						 if(name)
						 {
							 memberVO.getSoundexMatchList().add(voterVO);
							 matched = true;
						 }

					}
					
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
				}
				
				if(!matched)
				{
					memberVO.setUnMatched(true);
				}
			}
			
			
			for(SoundexVO memberVO:membersList)
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
					
					if(!memberVO.isSplit())
						memberVO.setUnMatched(true);
				}
			}
			
			for(SoundexVO memberVO:membersList)
			{
				boolean matched = false;

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
							matched = true;
						}
					}
				}
				
				if(matched)
					memberVO.setUnMatched(true);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in buildAllTheMemberAndMatchedVotersDetails service method");
		}
		
	}

}
