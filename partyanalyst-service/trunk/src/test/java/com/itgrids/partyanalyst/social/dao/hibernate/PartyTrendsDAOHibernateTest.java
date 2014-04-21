package com.itgrids.partyanalyst.social.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportHelperVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.model.PartyTrends;

public class PartyTrendsDAOHibernateTest extends BaseDaoTestCase {
   // @Autowired
	private IPartyTrendsDAO partyTrendsDAO;

	
  
	
	
	public IPartyTrendsDAO getPartyTrendsDAO() {
		return partyTrendsDAO;
	}





	public void setPartyTrendsDAO(IPartyTrendsDAO partyTrendsDAO) {
		this.partyTrendsDAO = partyTrendsDAO;
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
	
	
	public void testqueryTest()
	
	{
		List<?> helloList=partyTrendsDAO.callStoredProcedure();
		
		System.out.println(helloList.size());
		
	}
	
	
}
