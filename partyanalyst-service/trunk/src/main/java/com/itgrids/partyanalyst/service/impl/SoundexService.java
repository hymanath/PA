package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.language.RefinedSoundex;
import org.apache.log4j.Logger;
import org.hsqldb.lib.HashSet;

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


	public List<SoundexVO> getMappedVoterDetailsByUsingSoundexByPanchayatId(Long constituewcyId)
	{
		LOG.debug("Entered into the getMappedVoterDetailsByUsingSoundexByPanchayatId service method");
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
			List<Object[]> panchayatDEtails = panchayatDAO.getPanchayatsByConstituencyId(constituewcyId);
			
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
				setMembersDetails(membersList,memberDetails);
				
			    List<Object[]> list = boothPublicationVoterDAO.getVotersDetailsForPanchayatByPublicationIdAndPAnchayatId((Long)panchayatList[0], 10L);
			
				List<SoundexVO> votersDetails = new ArrayList<SoundexVO>();
				setVotersDetails(votersDetails,list,totalVoters);

				buildAllTheMemberAndMatchedVotersDetails(membersList,votersDetails);
				resultList.addAll(membersList);
			}
			
			displayMemberMatchingDetails(resultList);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getMappedVoterDetailsByUsingSoundexByPanchayatId service method");
		}
		constLevelDest(resultList,totalVoters);
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
					
					saveMemberVoterMappingDetails(member,exactMatch,1L);
				}
			}			
		}
		
		
		for(SoundexVO  member:resultList)
		{
		//	if(member.getExactMatchList().size() != 1 && member.getSoundexMatchList().size() >=0 )
			if( member.getExactMatchList().size() >0 ||  member.getSoundexMatchList().size() >0 )
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
					
					saveMemberVoterMappingDetails(member,soundex,1L);
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
					
					saveMemberVoterMappingDetails(member,soundex,2L);
				}
			}
		}
		
		
		/*for(SoundexVO  member:resultList)
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
				
				//saveMemberVoterMappingDetails(member,null,3L);

			}
			
		}*/
		
	}catch(Exception e)
	{
		e.printStackTrace();
		LOG.error("Exception raised in displayMemberMatchingDetails service method");

		
	}
	}
	
	public void saveMemberVoterMappingDetails(SoundexVO memberVO,SoundexVO voterVO,Long levelMappingId)
	{
		LOG.debug("Entered into the saveMemberVoterMappingDetails service method");
		try
		{
			MemberVoterMappingDetails memberVoterMappingDetails = new MemberVoterMappingDetails();
			
			totalSet.add(memberVO.getId());
			
			if(voterVO != null)
			{
				memberVoterMappingDetails.setVoterId(voterVO.getVoterId());
				
				memberVoterMappingDetails.setAgeMatched(voterVO.isAgeMatched() == true?"Y":"N");
				memberVoterMappingDetails.setGenderMatched(voterVO.isGenderMatch() == true?"Y":"N");
				memberVoterMappingDetails.setRelativeNameMatched(voterVO.isRelativeNameMatch() == true?"Y":"N");
			}
			memberVoterMappingDetails.setMemberId(memberVO.getId());
			memberVoterMappingDetails.setMemberMatchedLevelTypeMappingId(levelMappingId);
			
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
                
            	String firstLitter=	obj[6].toString().substring(0, 1).trim().toLowerCase();
            	totalVoters.get(firstLitter).add(voter);

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
						
						long difference=memberVO.getAge()-voterVO.getAge();
						boolean age=false;
						if(difference<0 && difference>=-4L )
							age=true;
						else if(difference>0 && difference<=4L )
							age=true;
						else if(difference==0)
							age=true;
						
						/*long low = memberVO.getAge() - 4;
						long high = memberVO.getAge() + 4;
						
						System.out.println(low+"=========="+high+"=============" );
						
						boolean age = (voterVO.getAge() >= low) &&( voterVO.getAge() <= high);*/
						
						 if(age)
						 {
							// System.out.println(true+"=========="+memberVO.getId()+"=============" );
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
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in buildAllTheMemberAndMatchedVotersDetails service method");
		}
		
	}

    public void constLevelDest(List<SoundexVO>  membersList, Map<String, List<SoundexVO>> totalVoters )
	
	{
    	
		System.out.println("input size"+membersList.size());
	
		Map<String,List<SoundexVO> > mapIds= totalVoters;
         for (SoundexVO memberVO : membersList) {
			
		List<SoundexVO> votersDetails = new ArrayList<SoundexVO>();

    	
    	if(!memberVO.isUnMatched())
    		continue;
    	String name1=memberVO.getName().substring(0, 1).trim().toLowerCase();
    		
    	votersDetails=mapIds.get(name1);
    	
    	//List<SoundexVO> votersDetails = new ArrayList<SoundexVO>();
    	
    	RefinedSoundex soundex = new RefinedSoundex();
		
		/*for(SoundexVO memberVO:membersList)
		{*/
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
					
					saveMemberVoterMappingDetails(memberVO,voterVO,4L);

				}else
				{
				  //soundex match
					 name = soundex.soundex(memName).equalsIgnoreCase(soundex.soundex(voterName));
					 
					 if(name) {
						 memberVO.getSoundexMatchList1().add(voterVO);
						 
						saveMemberVoterMappingDetails(memberVO,voterVO,5L);
					 }
					 else if (soundex
								.soundex(memberVO.getName())
								.replaceAll("0", "")
								.equalsIgnoreCase(
										soundex.soundex(voterVO.getName())
												.replaceAll("0", "")))	{
							
							memberVO.getSoundexMatchList1().add(voterVO);
							memberVO.setSplit(true);
							saveMemberVoterMappingDetails(memberVO,voterVO,5L);

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
							 
							/* List<SoundexVO> ids=null;
							 if(mapIds.containsKey(firstLet))
								 ids=mapIds.get(firstLet);
							 else {
								 ids= new ArrayList<SoundexVO>();
								     List<Object[]> objs=	boothPublicationVoterDAO.getVotersDetailsForConstIdBasedOnName(8L, 10L,firstLet);
						    		setVotersDetails(ids,objs);
						    		mapIds.put(firstLet, ids);
							 }*/
							 List<SoundexVO> ids=mapIds.get(firstLet);
							 for (SoundexVO soundexVO : ids) {
								 
								
							if (soundex.soundex(soundexVO.getName().trim()).equalsIgnoreCase(soundex.soundex(lastString)) || 
									
									soundex.soundex(soundexVO.getName().replaceAll("[\\s\\.+]", "")).equalsIgnoreCase(soundex.soundex(lastString.replaceAll("[\\s\\.+]", "")))	
									
									
									)
								
							 {
								boolean gender = soundexVO.getGender().equalsIgnoreCase(memberVO.getGender());
								
								if(gender)
								{
									soundexVO.setGenderMatch(true);
								}
								
								boolean relativeName = soundexVO.getRelativeName().equalsIgnoreCase(memberVO.getRelativeName());

								if(relativeName)
								{
									soundexVO.setRelativeNameMatch(true);
								}
								
								
								/*long low = memberVO.getAge() - 4;
								long high = memberVO.getAge() + 4;
								
								boolean age = (soundexVO.getAge() >= low) &&( soundexVO.getAge() <= high);
								*/
								long difference=memberVO.getAge()-voterVO.getAge();
								boolean age=false;
								if(difference<0 && difference>=-4L )
									age=true;
								else if(difference>0 && difference<=4L )
									age=true;
								else if(difference==0)
									age=true;
								if(age)
								{
									soundexVO.setAgeMatched(true);
									
								}
								 
								    memberVO.getSoundexMatchList1().add(soundexVO);
								   
							  saveMemberVoterMappingDetails(soundexVO,voterVO,5L);

								 memberVO.setSplit(true);
								 memberVO.setUnMatched(false);
						      }
						 	 }//for
							 flagset++;
						 }
						
					   } //else

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
			
			if(memberVO.getExactMatchList1().size() == 0 && memberVO.getSoundexMatchList1().size() == 0)
			{
				memberVO.setUnMatched(true);
				saveMemberVoterMappingDetails(memberVO,null,6L);

			}else
			{
				memberVO.setUnMatched(false);
			}
		
		}
    
    System.out.println("end of loop"+membersList.size());
    //analyze constituency level
    
    calculateCateries(membersList);
    processList(membersList);
	
	
	
	
	}
	
    
    public void processList( List<SoundexVO> resultList )
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
				}
			}			
		}
    	for(SoundexVO  member:resultList)
    	{
    		if(member.getExactMatchList1().size() != 1 && member.getSoundexMatchList1().size() >=0 )
    		{
    			
    			
    			int j=0;
    			for(SoundexVO soundex:member.getExactMatchList1())
    			{
	                List<MatchedTypeVo> lists=null;
					
					Long memeberId=member.getId();
					MatchedTypeVo evo=new MatchedTypeVo();
					evo.setMemberId(memeberId);
					lists=	soundex.getMaps().get(IConstants.MatchTypes.CExactMatch);
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
    			}
    			
    			
    			int i=0;
    			for(SoundexVO soundex:member.getSoundexMatchList1())
    			{
    				
                    List<MatchedTypeVo> lists=null;
					
					Long memeberId=member.getId();
					MatchedTypeVo evo=new MatchedTypeVo();
					evo.setMemberId(memeberId);
					lists=	soundex.getMaps().get(IConstants.MatchTypes.CsoundexMatch);
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
    			saveMemberVoterMappingDetails(member,null,6L);

    		}
    		
    	}
    	
    }
	
	
    public void calculateCateries( List<SoundexVO> resultList )
    {


    	for(SoundexVO  member:resultList)
		{
    		
    		
    		
			 if(member.getExactMatchList1().size() == 1 && member.getSoundexMatchList1().size() == 0)
			{
				int i=0;
				for(SoundexVO exactMatch:member.getExactMatchList1())
				{
					setCategories(exactMatch, member,IConstants.MatchTypes.CExactMatch);
				}
			}
			 if(member.getExactMatchList().size() == 1 && member.getSoundexMatchList().size() == 0)
				{
					int i=0;
					for(SoundexVO exactMatch:member.getExactMatchList())
					{
						setCategories(exactMatch, member,IConstants.MatchTypes.PExactMatch);
					}
				}
		}
    	for(SoundexVO  member:resultList)
    	{
    		if( member.getSoundexMatchList1().size() >=1 )
    		{
    		
    			for(SoundexVO soundex:member.getExactMatchList1())
    			{
    				setCategories(soundex, member,IConstants.MatchTypes.CsoundexMatch);
    			}
    			
    			
    			
    			for(SoundexVO soundex:member.getSoundexMatchList1())
    			{
    				setCategories(soundex, member,IConstants.MatchTypes.PSoundexMatch);

    			}
    		}
        
    	if( member.getSoundexMatchList().size() >=1 )
		{
			int j=0;
			for(SoundexVO soundex:member.getExactMatchList())
			{
				setCategories(soundex, member,IConstants.MatchTypes.CsoundexMatch);
			}
			
			
			int i=0;
			for(SoundexVO soundex:member.getSoundexMatchList())
			{
				setCategories(soundex, member,IConstants.MatchTypes.PSoundexMatch);

			}
		}
    }//for
    	
    	
    }
    	
    	
    
    public void setCategories(SoundexVO soundexVO,SoundexVO memberVO,IConstants.MatchTypes type)
    {
    	
    	
    	 Map<MatchTypes, List<MatchedTypeVo>>   maps= soundexVO.getMaps();
    	 MatchedTypeVo vo=new MatchedTypeVo();
    	 List<MatchedTypeVo> list=null;
    	 Long  memberId=memberVO.getId();
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
		
		boolean relativeName = soundexVO.getRelativeName().equalsIgnoreCase(memberVO.getRelativeName());

		if(relativeName)
		{
			vo.setRelativeNameMatch(true);
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
	
	
}
