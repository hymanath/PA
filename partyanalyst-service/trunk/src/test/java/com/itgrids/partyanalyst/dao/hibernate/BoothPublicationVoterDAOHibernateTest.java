package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;

	public class BoothPublicationVoterDAOHibernateTest  extends BaseDaoTestCase{
	  private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IBoothDAO boothDAO;
	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	/*public void test()
	{
		boothPublicationVoterDAO.getAll();
	}*/
	
	/*public void testGetVotersCountByPublicationId(){
		List<Object[]> count = boothPublicationVoterDAO.getVotersCountByPublicationId("constituency", 12l, 1l);
		System.out.println(count.size());
	}
	
	public void testGetVotersCountForPanchayatByPublicationId(){
		 boothPublicationVoterDAO.getPublicationUserCount(1l,8l,29l,IConstants.HAMLET);
		//stem.out.println(count.size());
		//System.out.println(count.get(0)+" ");
		//System.out.println(count);
	}
	*/
	
	/*public void testGetVotersDetailsForPanchayatByPublicationId(){
		
		List l = boothPublicationVoterDAO.getVotersDetailsForPanchayatByPublicationId(444L,1L,0,15,"asc","voterId");
		
		System.out.println(l.size());
	}*/
	
/*	public void testGetVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(){
		
		List l = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(11L,1L,18L,25L);
		
	
		System.out.println(((Object[])l.get(0))[0].toString());
		System.out.println(((Object[])l.get(0))[1]);
		
	}
		}*/
	
	/*public void testGetAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(){
		
		List<Object[]> l = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForHamletByPublicationId(50819L,7L,18L,25L);

      for(Object[] obj:l){
    	  System.out.println(obj[0].toString()+"-"+obj[1].toString());
    	  
      }		

}*/
	
	//public void testGetLocationNameByLocationValue()
	//{
	/*	List name = boothPublicationVoterDAO.getLocalitiesForBooth(122094l, 1l);
		
		System.out.println(name.size());
		if(name != null && name.size() > 0)
		  System.out.println(name.get(0));*/
	//	List l=new ArrayList<Long>();
	//	l.add(42l);
		
	//	List<Object[]> name = boothPublicationVoterDAO.getVoterDetailsByPanchayatIds(4l,8l,1l);
	 // System.out.println(name);
	//  for(Object[] obj:name){
    //	  System.out.println(obj[0].toString()+"-"+obj[1].toString());
	//}
	
	//}
	
		
		/*public void testGetVotersCasteDetailsForAgeRange(){
			Long starttime = System.currentTimeMillis();
			List<Long> list = new ArrayList<Long>();
			list.add(122978l);list.add(
					122979l);list.add(
					122980l);list.add(
					122981l);list.add(
					122982l);list.add(
					122983l);list.add(
					122984l);list.add(
					122985l);list.add(
					122986l);list.add(
					122987l);list.add(
					122988l);list.add(
					122989l);list.add(
					122990l);list.add(
					122991l);list.add(
					122992l);list.add(
					122993l);list.add(
					122994l);list.add(
					122995l);
					
					
			List<Object[]> castInfo = boothPublicationVoterDAO.getVotersCasteDetailsForAgeRange(23l, 30l, list,1l);
			Long endtime = System.currentTimeMillis();
			System.out.println((endtime-starttime)/1000);
			for(Object[] caste : castInfo){
				for(Object value : caste){
					System.out.print(value+"  ");
				}
				System.out.println("");
			}
		}
		public void testGetVotersCasteDetailsForDiffAgeRange(){
			List<Long> pancIds = new ArrayList<Long>();
			pancIds.add(1l);
			pancIds.add(2l);
			pancIds.add(3l);
			pancIds.add(4l);
			pancIds.add(5l);
			pancIds.add(6l);
			pancIds.add(7l);
			pancIds.add(8l);
			pancIds.add(9l);
			pancIds.add(10l);
			pancIds.add(11l);
			pancIds.add(12l);
			pancIds.add(13l);
			
			Long mainstarttime = System.currentTimeMillis();
			for(Long pancId:pancIds){
			Long starttime = System.currentTimeMillis();
			System.out.println("Start: "+starttime);
			List<Long> publicIds = new ArrayList<Long>();
			publicIds.add(8l);
			List<Object[]> castInfo = null;//boothPublicationVoterDAO.getVotersCasteDetailsForAgeRange(23l, 30l, boothDAO.getBoothIdsByLocalValuesList("panchayat", pancId, 232l, publicIds));
			Long endtime = System.currentTimeMillis();
			System.out.println("End: "+endtime);
			System.out.println((endtime-starttime)/1000);
			for(Object[] caste : castInfo){
				for(Object value : caste){
					System.out.print(value+"  ");
				}
				System.out.println("");
			}
		  }
			Long mainendtime = System.currentTimeMillis();
			System.out.println("mainDiff: "+(mainendtime-mainstarttime)/1000);
		}*/
	
	
	/*public void testGetAddedVotersBetweenTwoPublications()
	{
	  
	  //List<Object[]> list = boothPublicationVoterDAO.getAddedVotersBetweenTwoPublications(232L, 7L, 8L);
		List<Object[]> list = boothPublicationVoterDAO.getDeletedVotersBetweenTwoPublications(232L, 7L, 8L);
	  System.out.println(list.size());
	}*/
	
		/*public void testGetWardWiseTotalVotersCount(){
			List<Object[]> countList = boothPublicationVoterDAO.getCustomWardWiseTotalVotersCount(83l, 1l, 8l, 232l);
			for(Object[] count:countList){
				System.out.print(count[0].toString()+"--"+count[1].toString());
				System.out.println("");
			}
			
		}*/
	
		/* public void testGetCasteGroupContsByCustomWardWise(){
			List<Object[]> countList = boothPublicationVoterDAO.getCasteGroupContsByCustomWardWise(1l,83l, 8l,232l);
			for(Object[] count:countList){
				System.out.print(count[0].toString()+"--"+count[1].toString()+"--"+count[2].toString());
				System.out.println("");
			}
			
		}*/
		 
		/* public void testGetCasteWiseGenderWiseContsByCustomWardWise(){
				List<Object[]> countList = boothPublicationVoterDAO.getCasteWiseGenderWiseContsByCustomWardWise(1l,83l, 8l,232l);
				for(Object[] count:countList){
					System.out.print(count[0].toString()+"--"+count[1].toString()+"--"+count[2].toString()+"--"+count[3].toString());
					System.out.println("");
				}
				
			}*/
		 /*public void testGetCustomWardWiseTotalMaleFemaleVotersCount(){
				List<Object[]> countList = boothPublicationVoterDAO.getCustomWardWiseTotalMaleFemaleVotersCount(83l,1l, 8l,232l);
				for(Object[] count:countList){
					System.out.print(count[0].toString()+"--"+count[1].toString()+"--"+count[2].toString()+"--"+count[3].toString());
					System.out.println("");
				}
				
			}*/
	     /*public void testGetCustomWardWiseFamilyVotersCount(){
		  List<Object[]> countList = boothPublicationVoterDAO.getCustomWardWiseFamilyVotersCount(83l,1l, 8l,232l);
			for(Object[] count:countList){
				System.out.print(count[0].toString()+"--"+count[1].toString());
				System.out.println("");
			}
		
	    }*/
	   /* public void testGetCustomWardAgeCount(){
		  List<Object[]> countList = boothPublicationVoterDAO.getCustomWardAgeCount(83l,1l, 8l,232l);
			for(Object[] count:countList){
				System.out.print(count[0].toString()+"--"+count[1].toString()+"--"+count[2].toString()+"--"+count[3].toString());
				System.out.println("");
			}
		
	    }*/
	   /* public void testGetCustomWard18To22AgeCount(){
			  List<Object[]> countList = boothPublicationVoterDAO.getCustomWard18To22AgeCount(83l,1l, 8l,232l);
				for(Object[] count:countList){
					System.out.print(count[0].toString()+"--"+count[1].toString()+"--"+count[2].toString()+"--"+count[3].toString());
					System.out.println("");
				}
			
		    }*/
	    /*public void testGetWardBoothTotalMaleFemaleVotersCount(){
		  List<Object[]> countList = boothPublicationVoterDAO.getWardBoothAgeCount(47l,1l, 8l,309l);
			for(Object[] count:countList){
				System.out.print(count[0].toString()+"--"+count[1].toString()+"--"+count[2].toString()+"--"+count[3].toString());
				System.out.println("");
			}
		
	    }*/
	
		/*public void testGetVoterDetailsOfAConstituencyAndPublication()
		{
			List<Object[]> list = boothPublicationVoterDAO.getVoterDetailsOfAConstituencyAndPublication(173l,10l);
			System.out.println(list.size());
			
			for(Object[] params : list)
			{
				String name = params[2].toString();
				String name2 = "";
				char[] charArray = name.toCharArray();
				for(Character C : charArray)
				{
					if(!Character.isLetter(C) && !C.toString().equals(" "))
						System.out.println(params[0]);
					else
					{
						name2 = name2+C.toString();
					}
				}
				if(name2.length() > 0)
				{
					System.out.println(name+"   "+name2);
				}
			}
		}*/
	
		public void test()
		{
			List<Long> cIdsList = new ArrayList<Long>(0);
			cIdsList.add(147l);
			cIdsList.add(304l);
			cIdsList.add(305l);
			cIdsList.add(303l);
			cIdsList.add(171l);
			cIdsList.add(172l);
			cIdsList.add(366l);
			cIdsList.add(149l);
			cIdsList.add(308l);
			cIdsList.add(307l);
			cIdsList.add(155l);
			cIdsList.add(156l);
			cIdsList.add(157l);
			cIdsList.add(163l);
			cIdsList.add(146l);
			cIdsList.add(249l);
			cIdsList.add(152l);
			cIdsList.add(309l);
			cIdsList.add(153l);
			cIdsList.add(159l);
			cIdsList.add(160l);
			cIdsList.add(187l);
			cIdsList.add(193l);
			cIdsList.add(168l);
			cIdsList.add(169l);
			cIdsList.add(170l);
			cIdsList.add(176l);
			cIdsList.add(180l);
			cIdsList.add(199l);
			cIdsList.add(203l);
			cIdsList.add(205l);
			cIdsList.add(208l);
			cIdsList.add(210l);
			cIdsList.add(214l);
			cIdsList.add(207l);
			cIdsList.add(311l);
			cIdsList.add(312l);
			cIdsList.add(206l);
			cIdsList.add(211l);
			cIdsList.add(212l);
			cIdsList.add(215l);
			cIdsList.add(216l);
			cIdsList.add(181l);
			cIdsList.add(167l);
			cIdsList.add(173l);
			cIdsList.add(174l);
			cIdsList.add(177l);
			cIdsList.add(178l);
			cIdsList.add(179l);
			cIdsList.add(196l);
			cIdsList.add(331l);
			cIdsList.add(195l);
			cIdsList.add(191l);
			cIdsList.add(192l);
			cIdsList.add(186l);
			cIdsList.add(194l);
			cIdsList.add(184l);
			cIdsList.add(185l);
			cIdsList.add(327l);
			cIdsList.add(328l);
			cIdsList.add(182l);
			cIdsList.add(329l);
			cIdsList.add(330l);
			cIdsList.add(209l);
			cIdsList.add(213l);
			cIdsList.add(217l);
			cIdsList.add(218l);
			cIdsList.add(219l);
			cIdsList.add(228l);
			cIdsList.add(229l);
			cIdsList.add(280l);
			cIdsList.add(281l);
			cIdsList.add(301l);
			cIdsList.add(282l);
			cIdsList.add(283l);
			cIdsList.add(284l);
			cIdsList.add(302l);
			cIdsList.add(241l);
			cIdsList.add(232l);
			cIdsList.add(233l);
			cIdsList.add(340l);
			cIdsList.add(341l);
			cIdsList.add(238l);
			cIdsList.add(223l);
			cIdsList.add(221l);
			cIdsList.add(222l);
			cIdsList.add(224l);
			cIdsList.add(225l);
			cIdsList.add(226l);
			cIdsList.add(227l);
			cIdsList.add(344l);
			cIdsList.add(288l);
			cIdsList.add(289l);
			cIdsList.add(291l);
			cIdsList.add(231l);
			cIdsList.add(236l);
			cIdsList.add(237l);
			cIdsList.add(239l);
			cIdsList.add(260l);
			cIdsList.add(264l);
			cIdsList.add(258l);
			cIdsList.add(265l);
			cIdsList.add(334l);
			cIdsList.add(253l);
			cIdsList.add(255l);
			cIdsList.add(254l);
			cIdsList.add(332l);
			cIdsList.add(261l);
			cIdsList.add(263l);
			cIdsList.add(262l);
			cIdsList.add(333l);
			cIdsList.add(257l);
			cIdsList.add(276l);
			cIdsList.add(279l);
			cIdsList.add(297l);
			cIdsList.add(278l);
			cIdsList.add(277l);
			cIdsList.add(298l);
			cIdsList.add(272l);
			cIdsList.add(299l);
			cIdsList.add(273l);
			cIdsList.add(270l);
			cIdsList.add(275l);
			cIdsList.add(300l);
			cIdsList.add(267l);
			cIdsList.add(271l);
			cIdsList.add(242l);
			cIdsList.add(243l);
			cIdsList.add(251l);
			cIdsList.add(245l);
			cIdsList.add(244l);
			cIdsList.add(250l);
			cIdsList.add(249l);
			cIdsList.add(252l);
			cIdsList.add(246l);
			cIdsList.add(248l);
			cIdsList.add(290l);
			cIdsList.add(285l);
			cIdsList.add(294l);
			cIdsList.add(286l);
			cIdsList.add(133l);
			cIdsList.add(134l);
			cIdsList.add(135l);
			cIdsList.add(136l);
			cIdsList.add(137l);
			cIdsList.add(140l);
			cIdsList.add(141l);
			cIdsList.add(306l);
			cIdsList.add(113l);
			cIdsList.add(359l);
			cIdsList.add(138l);
			cIdsList.add(360l);
			cIdsList.add(124l);
			cIdsList.add(125l);
			cIdsList.add(108l);
			cIdsList.add(111l);
			cIdsList.add(112l);
			cIdsList.add(352l);
			cIdsList.add(114l);
			cIdsList.add(116l);
			cIdsList.add(117l);
			cIdsList.add(368l);
			cIdsList.add(358l);
			cIdsList.add(354l);
			cIdsList.add(356l);
			cIdsList.add(355l);
			cIdsList.add(357l);
			cIdsList.add(127l);
			cIdsList.add(109l);
			cIdsList.add(353l);
			cIdsList.add(122l);
			cIdsList.add(120l);
			cIdsList.add(121l);
			cIdsList.add(361l);
			cIdsList.add(129l);

			for(Long cid : cIdsList)
			{
				Map<String,Long> pmap = new HashMap<String,Long>(0);
				List<Object[]> list = boothPublicationVoterDAO.getpanchayts(cid,10l);
				for(Object[] params : list)
				{
					String pstr = params[1].toString().toUpperCase();
					if(pmap.get(pstr) != null)
					{
						System.out.println("In "+cid+" Constituency --> Panchayat Name - "+pstr
								+" with Duplicate Names, their Ids are "+params[0].toString()+","+pmap.get(pstr));
					}
					else
					{
						pmap.put(pstr, (Long)params[0]);
					}
				}
			}
					
		}
	    
	}
