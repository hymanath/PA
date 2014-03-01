package com.itgrids.partyanalyst.social.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
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
	
	public void testPopulatePriority(){
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
	}
}
