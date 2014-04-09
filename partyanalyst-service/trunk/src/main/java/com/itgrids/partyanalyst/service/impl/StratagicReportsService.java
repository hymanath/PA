package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICasteContainConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatResultDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.IStrategyMergPancListDAO;
import com.itgrids.partyanalyst.dao.IStrategyMergePanchayatDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.AlliancePartyResultsVO;
import com.itgrids.partyanalyst.dto.AssumptionsVO;
import com.itgrids.partyanalyst.dto.DelimitationEffectVO;
import com.itgrids.partyanalyst.dto.PDFHeadingAndReturnVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportHelperVO;
import com.itgrids.partyanalyst.dto.PartyElectionTrendsReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsVerVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.dto.VoterDensityWithPartyVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.StrategyMergPancList;
import com.itgrids.partyanalyst.model.StrategyMergePanchayat;
import com.itgrids.partyanalyst.model.VoterAgeRange;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IStratagicReportsService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;

public class StratagicReportsService implements IStratagicReportsService{
	private static final Logger LOG = Logger.getLogger(StratagicReportsService.class);
	  Font  TITLE = FontFactory.getFont("Calibri",9,Font.BOLD);
	  BaseColor bcolor=BaseColor.YELLOW;
	  BaseColor subHeading= new BaseColor(69,109,142);
	  Font SMALLFONT = FontFactory.getFont("Calibri",9,Font.NORMAL);
	 Font calibriBold = FontFactory.getFont("Calibri",9,Font.BOLDITALIC);
	 Font calibriBold2 = FontFactory.getFont("Calibri",9,Font.BOLD);
	
	@Autowired public IUserDAO userDAO;
	
	
	@Autowired public  IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	@Autowired IPartyTrendsDAO partyTrendsDAO;
	
	@Autowired public INominationDAO nominationDAO;
	
	@Autowired RegionServiceDataImp regionServiceDataImp;
	

	@Autowired
	public IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	

	
	@Autowired public IConstituencyDAO constituencyDAO;
	
	@Autowired IHamletBoothElectionDAO hamletBoothElectionDAO;
	
	@Autowired public IElectionDAO electionDAO;
	
	@Autowired public ICandidateBoothResultDAO candidateBoothResultDAO;
	
	@Autowired IStaticDataService staticDataService;
	

	@Autowired public IPanchayatDAO panchayatDAO;
	
	@Autowired
	public ILocalElectionBodyDAO localElectionBodyDAO;

	
	


	

	@Autowired public IBoothDAO boothDAO;
	
	@Autowired IPartyDAO partyDAO;
	
	@Autowired IStrategyMergePanchayatDAO strategyMergePanchayatDAO;
	
	@Autowired IStrategyMergPancListDAO strategyMergPancListDAO;
	
	@Autowired IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	
	private TransactionTemplate transactionTemplate = null;
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Autowired public IRegionServiceData regionServiceData;
	
	@Autowired public IVoterModificationInfoDAO voterModificationInfoDAO;
	
	@Autowired public ITehsilDAO tehsilDAO;
	
	@Autowired public IVoterInfoDAO voterInfoDAO;
	
	@Autowired public IVoterModificationDAO voterModificationDAO;
	
	@Autowired public IVotersAnalysisService votersAnalysisService;
	
	@Autowired public IPanchayatResultDAO panchayatResultDAO;
	
	@Autowired public IVoterAgeRangeDAO voterAgeRangeDAO;
	
	@Autowired IVoterReportLevelDAO voterReportLevelDAO;

	@Autowired IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO;
	
	@Autowired public IAllianceGroupDAO allianceGroupDAO;
	
	public IPublicationDateDAO publicationDateDAO;
	
	public ICasteContainConstituencyDAO casteContainConstituencyDAO;
	
	public ICasteContainConstituencyDAO getCasteContainConstituencyDAO() {
		return casteContainConstituencyDAO;
	}

	public void setCasteContainConstituencyDAO(
			ICasteContainConstituencyDAO casteContainConstituencyDAO) {
		this.casteContainConstituencyDAO = casteContainConstituencyDAO;
	}

	@Autowired public IBoothResultDAO boothResultDAO;
	
	
	   
	public IPublicationDateDAO getPublicationDateDAO() {
		return publicationDateDAO;
	}

	public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
		this.publicationDateDAO = publicationDateDAO;
	}

	public List<AgeRangeVO> getBoothWiseAddedAndDeletedVoters(Long constiId,Long pubId){
		List<AgeRangeVO> boothWiseAddedDeletedVoters=new ArrayList<AgeRangeVO>();
		try{
			//String constiId = "221";
			StringBuilder table = new StringBuilder();
			
			String queryham = "select VM.part_no,count(VM.voter_id),V.voter_age_range_id,VM.voter_status_id  from voter_modification VM, voter V where "+
			"V.voter_id = VM.voter_id AND "+
			"VM.constituency_id = "+constiId+" and "+
			"VM.publication_date_id = "+pubId+" and VM.voter_status_id in(1,2) "+ 
			"GROUP BY VM.part_no,VM.voter_status_id ,V.voter_age_range_id "+
			"ORDER BY VM.part_no";

			String queryham1 =  "select VM.part_no,count(VM.voter_id),VM.voter_status_id from voter_modification VM, voter V where "+
			"V.voter_id = VM.voter_id AND "+
			"VM.constituency_id = "+constiId+" and "+
			"VM.publication_date_id = "+pubId+"  and VM.voter_status_id in(1,2)  and V.age BETWEEN 18 and 22 "+ 
			"GROUP BY VM.part_no,VM.voter_status_id "+
			"ORDER BY VM.part_no";
			
			String queryham2 = "select p.panchayat_name,b.part_no from booth b,panchayat p where b.constituency_id = "+constiId+" and b.publication_date_id = "+pubId+" and b.local_election_body_id is null and b.panchayat_id = p.panchayat_id ";
			String queryham3 = "select t.tehsil_name,b.part_no from booth b,tehsil t where b.constituency_id = "+constiId+" and b.publication_date_id = "+pubId+" and b.local_election_body_id is not null and b.tehsil_id = t.tehsil_id ";	
			
			List<Object[]> list = userDAO.getData(queryham);
			
				List<Object[]> pancnames = userDAO.getData(queryham2);
				List<Object[]> muncnames = userDAO.getData(queryham3);
				
				Map<String,String> namesMap = new HashMap<String,String>();
				List<Long> loc = new ArrayList<Long>();
				for(Object[] data:pancnames){
					namesMap.put(data[1].toString(), data[0].toString().substring(0, 1).toUpperCase() + data[0].toString().substring(1).toLowerCase());
					loc.add(new Long(data[1].toString().trim()));
				}
				for(Object[] data:muncnames){
					namesMap.put(data[1].toString(), data[0].toString()+" Municipality");
					loc.add(new Long(data[1].toString().trim()));
				}
				Map<String,AgeRangeVO> map = new HashMap<String,AgeRangeVO>();
				
				List<Object[]> boothTotalsList=boothPublicationVoterDAO.getTotalVotersOfBoothByConstituencyId(constiId, pubId);
				Map<String,Long> boothsTtlMap=new HashMap<String, Long>();
				if(boothTotalsList.size()>0){
					for(Object[] obj:boothTotalsList){
						boothsTtlMap.put(obj[1].toString().trim(), Long.valueOf(obj[0].toString()));
					}
				}
				
				if(list != null && list.size() >0){
				
				for(Object[] data:list){
					AgeRangeVO vo = map.get(data[0].toString());
					Long boothTotalVoters=0l;
					if(vo == null){
						vo = new AgeRangeVO();
						vo.setPanchayat(namesMap.get(data[0].toString()));
						vo.setHamlet(data[0].toString());
						boothTotalVoters=boothsTtlMap.get(vo.getHamlet().trim());
						vo.setTotalVotersInBooth(boothTotalVoters);
						
						map.put(data[0].toString(),vo);
					}
					
					
					if(data[1] != null){
					 if(((BigInteger)data[3]).longValue() == 1){
						if(((BigInteger)data[2]).longValue() == 2){
							vo.setAge18To25(((BigInteger)data[1]).longValue());
							/*vo.setAge18To25Per(calcPercentage(boothTotalVoters, vo.getAge18To25()));
							totalAdded+=vo.getAge18To25();*/
						}else if(((BigInteger)data[2]).longValue() == 3){
							vo.setAge26to35(((BigInteger)data[1]).longValue());
							/*vo.setAge26to35Per(calcPercentage(boothTotalVoters, vo.getAge26to35()));
							totalAdded+=vo.getAge26to35();*/
						}else if(((BigInteger)data[2]).longValue() == 4){
							vo.setAge36to45(((BigInteger)data[1]).longValue());
							/*vo.setAge36to45Per(calcPercentage(boothTotalVoters,vo.getAge36to45()));
							totalAdded+=vo.getAge36to45();*/
						}else if(((BigInteger)data[2]).longValue() == 5){
							vo.setAge46to60(((BigInteger)data[1]).longValue());
							/*vo.setAge46to60Per(calcPercentage(boothTotalVoters, vo.getAge46to60()));
							totalAdded+=vo.getAge46to60();*/
						}else if(((BigInteger)data[2]).longValue() == 6){
							vo.setAbove60(((BigInteger)data[1]).longValue());
							/*vo.setAbove60Per(calcPercentage(boothTotalVoters,vo.getAbove60()));
							totalAdded+=vo.getAbove60();*/
						}
					 }else{
						 if(((BigInteger)data[2]).longValue() == 2){
								vo.setDelAge18To25(((BigInteger)data[1]).longValue());
								/*totalDeleted+=vo.getDelAge18To25();
								vo.setDelAge18To25Per(calcPercentage(boothTotalVoters, vo.getDelAge18To25()));*/
							}else if(((BigInteger)data[2]).longValue() == 3){
								vo.setDelAge26to35(((BigInteger)data[1]).longValue());
								/*totalDeleted+=vo.getDelAge26to35();
								vo.setDelAge26to35Per(calcPercentage(boothTotalVoters, vo.getDelAge26to35()));*/
							}else if(((BigInteger)data[2]).longValue() == 4){
								vo.setDelAge36to45(((BigInteger)data[1]).longValue());
								/*totalDeleted+=vo.getDelAge36to45();
								vo.setDelAge36to45Per(calcPercentage(boothTotalVoters, vo.getDelAge36to45()));*/
							}else if(((BigInteger)data[2]).longValue() == 5){
								vo.setDelAge46to60(((BigInteger)data[1]).longValue());
								/*totalDeleted+=vo.getDelAge46to60();
								vo.setDelAge46to60Per(calcPercentage(boothTotalVoters, vo.getDelAge46to60()));*/
							}else if(((BigInteger)data[2]).longValue() == 6){
								vo.setDelAbove60(((BigInteger)data[1]).longValue());
								/*totalDeleted+=vo.getDelAbove60();
								vo.setDelAbove60Per(calcPercentage(boothTotalVoters, vo.getDelAbove60()));*/
							}
					 }
					}
					//vo.setTotalVotersAdded(totalAdded);
					//vo.setTotalVotersDeleted(totalDeleted);
					//vo.setTotalVotersAddedPer(calcPercentage(boothTotalVoters,totalAdded));
					//vo.setTotalVotersDeletedPer(calcPercentage(boothTotalVoters,totalDeleted));
				}
				
				}
				list = userDAO.getData(queryham1);
				if(list != null && list.size() >0){
					
					
					for(Object[] data:list){
						AgeRangeVO vo = map.get(data[0].toString());
						if(vo == null){
							vo = new AgeRangeVO();
							vo.setPanchayat(namesMap.get(data[0].toString()));
							vo.setHamlet(data[0].toString());
							map.put(data[0].toString(),vo);
						}
						if(data[1] != null){
						 if(((BigInteger)data[2]).longValue() == 1){
							 vo.setYoungVoters(((BigInteger)data[1]).longValue());
							// vo.setYoungVotersPer(calcPercentage(vo.getTotalVotersInBooth(), vo.getYoungVoters()));
						 }else{
							 vo.setDelYoungVoters(((BigInteger)data[1]).longValue());
							// vo.setDelYoungVotersPer(calcPercentage(vo.getTotalVotersInBooth(), vo.getDelYoungVoters()));
						 }
						}
					}
					}
				
				loc = new ArrayList<Long>(new HashSet<Long>(loc));
				Collections.sort(loc);
				
				
				
				/*table.append("<table>");
				table.append("<tr>");
				table.append("<td>Panchayat</td><td>Booth</td><td>Young Voters</td><td>18 - 25</td><td>26 - 35</td><td>36 - 45</td><td>46 - 60</td><td>Above 60</td><td>Young Voters</td><td>18 - 25</td><td>26 - 35</td><td>36 - 45</td><td>46 - 60</td><td>Above 60</td>");
				table.append("</tr>");*/
				for(Long id:loc){
					AgeRangeVO vo = map.get(id.toString());
					if(vo == null){
						vo = new AgeRangeVO();
						vo.setPanchayat(namesMap.get(id.toString().substring(0, 1).toUpperCase() + id.toString().substring(1).toLowerCase()));
						vo.setHamlet(id.toString());
					}
					
					//PERCENTAGE CALUCULATIONS
					vo.setTotalVotersAdded(vo.getAge18To25()+vo.getAge26to35()+vo.getAge36to45()+vo.getAge46to60()+vo.getAbove60());
					vo.setTotalVotersAddedPer(calcPercentage(vo.getTotalVotersInBooth(), vo.getTotalVotersAdded()));
					
					vo.setAge18To25Per(calcPercentage(vo.getTotalVotersAdded(), vo.getAge18To25()));
					vo.setAge26to35Per(calcPercentage(vo.getTotalVotersAdded(), vo.getAge26to35()));
					vo.setAge36to45Per(calcPercentage(vo.getTotalVotersAdded(),vo.getAge36to45()));
					vo.setAge46to60Per(calcPercentage(vo.getTotalVotersAdded(), vo.getAge46to60()));
					vo.setAbove60Per(calcPercentage(vo.getTotalVotersAdded(),vo.getAbove60()));
					vo.setYoungVotersPer(calcPercentage(vo.getTotalVotersAdded(), vo.getYoungVoters()));
					
					
					vo.setTotalVotersDeleted(vo.getDelAge18To25()+vo.getDelAge26to35()+vo.getDelAge36to45()+vo.getDelAge46to60()+vo.getDelAbove60());
					vo.setTotalVotersDeletedPer(calcPercentage(vo.getTotalVotersInBooth(), vo.getTotalVotersDeleted()));
					vo.setDelAge18To25Per(calcPercentage(vo.getTotalVotersDeleted(), vo.getDelAge18To25()));
					vo.setDelAge26to35Per(calcPercentage(vo.getTotalVotersDeleted(), vo.getDelAge26to35()));
					vo.setDelAge36to45Per(calcPercentage(vo.getTotalVotersDeleted(), vo.getDelAge36to45()));
					vo.setDelAge46to60Per(calcPercentage(vo.getTotalVotersDeleted(), vo.getDelAge46to60()));
					vo.setDelAbove60Per(calcPercentage(vo.getTotalVotersDeleted(), vo.getDelAbove60()));
					vo.setDelYoungVotersPer(calcPercentage(vo.getTotalVotersDeleted(), vo.getDelYoungVoters()));
					
					boothWiseAddedDeletedVoters.add(vo);
					/*table.append("<tr>");
					   table.append("<td>"+vo.getPanchayat()+"</td><td>Booth-"+vo.getHamlet()+"</td><td>"+vo.getYoungVoters()+"</td><td>"+vo.getAge18To25()+"</td><td>"+vo.getAge26to35()+"</td><td>"+vo.getAge36to45()+"</td><td>"+vo.getAge46to60()+"</td><td>"+vo.getAbove60()+"</td><td>"+vo.getDelYoungVoters()+"</td><td>"+vo.getDelAge18To25()+"</td><td>"+vo.getDelAge26to35()+"</td><td>"+vo.getDelAge36to45()+"</td><td>"+vo.getDelAge46to60()+"</td><td>"+vo.getDelAbove60()+"</td>");
					table.append("</tr>");*/
				}
				sortByVotersPercentage(boothWiseAddedDeletedVoters);
				
				/*table.append("</table>");*/
			System.out.println(table.toString());
			}catch(Exception e){
				LOG.error("Exception rised in getBoothWiseAddedAndDeletedVoters",e);
			}
		if(boothWiseAddedDeletedVoters.size()>0){
			boothWiseAddedDeletedVoters.get(0).setMainHeading1("Age Group –Booth wise Additions information");
			boothWiseAddedDeletedVoters.get(0).setMainHeading2("Age Group –Booth wise Deletions information");
		}
		return boothWiseAddedDeletedVoters;
	}
	
	  public void sortByVotersPercentage(List<AgeRangeVO> boothWiseAddedDeletedVoters)
		{
			 Collections.sort(boothWiseAddedDeletedVoters, new Comparator<AgeRangeVO>() {
				 
					 public int compare(AgeRangeVO arg0, AgeRangeVO arg1) {
						 if(arg0.getTotalVotersAddedPer()!= null && arg1.getTotalVotersAddedPer()!= null ){
							 return Double.valueOf(arg0.getTotalVotersAddedPer()).compareTo(Double.valueOf(arg1.getTotalVotersAddedPer()));
						 
					 }else
					return 0;
					 }
			 });
		}
	
	public String calcPercentage(Long total,Long count){
		if(total>0){
			return count != 0 ? roundTo2DigitsFloatValue((float) count * 100f / total): "0.00";
		}else{
			return "0.00";
		}
	}
	
	public String roundTo2DigitsFloatValue(Float number){
		  
		//LOG.debug("Entered into the roundTo2DigitsFloatValue service method");
		  
		  String result = "";
		  try
		  {
			  
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e)
		  {
			  LOG.error("Exception raised in roundTo2DigitsFloatValue service method",e);
			 
		  }
		  return result;
	  }
	public String roundTo2DigitsDoubleValue(Double number){
		  
		LOG.debug("Entered into the roundTo2DigitsFloatValue service method");
		  
		  String result = "";
		  try
		  {
			  
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e)
		  {
			  LOG.error("Exception raised in roundTo2DigitsFloatValue service method",e);
			
		  }
		  return result;
	  }
	public List<PartyElectionTrendsReportVO> getPreviousTrendsReport(Long constId){
		
		List<Object[]> ids = (List<Object[]>)partyTrendsDAO.getPreviousTrendsData(null, constId);
		Map<Long,PartyElectionTrendsReportVO> maps = new HashMap<Long, PartyElectionTrendsReportVO>();

		System.out.println(ids.size());
		
		for (Object[] object : ids) {
			
		if(maps.containsKey(Long.valueOf(object[0].toString())))
		{
			PartyElectionTrendsReportVO vo =maps.get(Long.valueOf(object[0].toString()));
	        vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
	        vo.setTotalVoters(((Double)object[1]).longValue());
	        vo.setTotalVotesPolled(((Double)object[2]).longValue());
	        vo.setDistrictId(Long.valueOf(object[9].toString()));
	        PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
	        voh.setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((Double)object[7])));
	        voh.setVotesEarned(((Double)object[4]).longValue());
	        voh.setRank((Long)object[8]);
	        if(((Long)object[3]).equals(872L))
	        {
	     	   voh.setMarginVotes(((Double)object[5]).longValue());
	     	   voh.setMarginVotesPercentage((Double.valueOf(object[6].toString())));
	     	   vo.setTdpVo(voh);
	        }else if(((Long)object[3]).equals(362L))
	        {
	     	   vo.setIncVo(voh);

	        }else if(((Long)object[3]).equals(1117L))
	        {
	     	   vo.setPrpVo(voh);

	        }
	        else if(Long.valueOf(object[9].toString())<11){
	        	if(((Long)object[3]).equals(886L)){
	        		vo.setTrsVo(voh);
	        	}
	        	 else {
	  	     	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
	  	     	   if(vo1!=null){
	  	     		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
	  	     		  if(vo1.getRank()>(Long)object[8] )
	          			  vo1.setRank((Long)object[8]); 
	  	     	   }
	  	        
	  	     	   else 
	  	     	   {
	  	     		   vo1= new PartyElectionTrendsReportHelperVO();
	  	     		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;
	  		 	     	  vo1.setRank((Long)object[8]); 

	  	     	   }
	  	     	  //vo1.setRank((Long)object[8]); 
	  	     	  vo.setOthersVo(vo1);
	  	        }
	        }
	        else {
	     	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
	     	   if(vo1!=null){
	     		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
	     		  if( vo1.getRank()>(Long)object[8] )
        			  vo1.setRank((Long)object[8]); 
	     	   }
	        
	     	   else 
	     	   {
	     		   vo1= new PartyElectionTrendsReportHelperVO();
	     		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;
		 	     	  vo1.setRank((Long)object[8]); 

	     	   }
	     	  //vo1.setRank((Long)object[8]); 
	     	  vo.setOthersVo(vo1);
	        }
			
		}
		else
		{
			PartyElectionTrendsReportVO vo =new PartyElectionTrendsReportVO();
			           vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
			           vo.setTotalVoters(((Double)object[1]).longValue());
			           vo.setTotalVotesPolled(((Double)object[2]).longValue());
			           vo.setDistrictId(Long.valueOf(object[9].toString()));
			           PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
			           voh.setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((Double)object[7])));
			           voh.setVotesEarned(((Double)object[4]).longValue());
				     	 voh.setRank((Long)object[8]); 

			           if(((Long)object[3]).equals(872L))
			           {
			        	   voh.setMarginVotes((Long)object[5]);
			        	   voh.setMarginVotesPercentage(((Double)object[6]));
			        	   vo.setTdpVo(voh);
			           }else if(((Long)object[3]).equals(362L))
			           {
			        	   vo.setIncVo(voh);

			           }else if(((Long)object[3]).equals(1117L)  )
			           {
			        	   vo.setPrpVo(voh);

			           }
			           else if(Long.valueOf(object[9].toString())<11){
			        	 if(((Long)object[3]).equals(886L)){
			           			vo.setTrsVo(voh);
			           		}
			           	 else {
				        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
				        	   if(vo1!=null){
				        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
				        		  if( vo1.getRank()>(Long)object[8] )
				        			  vo1.setRank((Long)object[8]); 
				        	   }
				        	   else 
				        	   {
				        		   vo1= new PartyElectionTrendsReportHelperVO();
				        		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;
						 	     	  vo1.setRank((Long)object[8]); 

				        	   }
	   
				        	   vo.setOthersVo(vo1);
				           }
			           }
			           
			           else {
			        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
			        	   if(vo1!=null){
			        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Double)object[4]).longValue()) ;
			        		  if( vo1.getRank()>(Long)object[8] )
			        			  vo1.setRank((Long)object[8]); 
			        	   }
			        	   else 
			        	   {
			        		   vo1= new PartyElectionTrendsReportHelperVO();
			        		   vo1.setVotesEarned(((Double)object[4]).longValue()) ;
					 	     	  vo1.setRank((Long)object[8]); 

			        	   }
   
			        	   vo.setOthersVo(vo1);
			           }
			           
		maps.put(Long.valueOf(object[0].toString()),vo );
		}
		}
		System.out.println(maps);
		List<PartyElectionTrendsReportVO> finalRes= new ArrayList<PartyElectionTrendsReportVO>();
		for(Long year:maps.keySet())
		{
			PartyElectionTrendsReportVO vo=	maps.get(year);
			if(vo.getOthersVo()!=null){
				//vo.getOthersVo().setVotesEarned(vo.getTotalVotesPolled()-( + vo.getIncVo()!=null ?vo.getIncVo().getVotesEarned().longValue():0L));
				vo.getOthersVo().setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((double)((double)vo.getOthersVo().getVotesEarned()/(double)vo.getTotalVotesPolled())*100)));
			}else{
				PartyElectionTrendsReportHelperVO othersVo=new PartyElectionTrendsReportHelperVO();
				vo.setOthersVo(othersVo);
			}
			finalRes.add(vo);
		}
		Collections.sort(finalRes);
		return finalRes;
	}
	public List<PartyElectionTrendsReportVO> getPreviousTrendsReportParliament(Long constId){
		Map<Long,Long> idMap = new HashMap<Long, Long>();
		List<Object[]> obj=	partyTrendsDAO.getTotalVotersForConst(constId);
		for (Object[] objects : obj) {
			idMap.put(Long.valueOf(objects[0].toString()), ((Double)objects[1]).longValue());
			
		}

		List<Object[]> ids = (List<Object[]>)partyTrendsDAO.getPreviousTrendsDataForParleament(null, constId);
		Map<Long,PartyElectionTrendsReportVO> maps = new HashMap<Long, PartyElectionTrendsReportVO>();

		System.out.println(ids.size());
		
		for (Object[] object : ids) {
			
		if(maps.containsKey(Long.valueOf(object[0].toString())))
		{
			PartyElectionTrendsReportVO vo =maps.get(Long.valueOf(object[0].toString()));
			  vo.setElectionYear(Long.valueOf(object[0].toString()).intValue());
			  if(idMap.containsKey(Long.valueOf(object[0].toString())))
			   vo.setTotalVoters(idMap.get(Long.valueOf(object[0].toString())));
	           vo.setTotalVotesPolled(Long.valueOf(object[1].toString()));
	           vo.setDistrictId(Long.valueOf(object[5].toString()));
	           PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
	           voh.setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((double)(((double)((Long)object[3]).longValue()/(double)Long.valueOf(object[1].toString()))*100))));
	           voh.setVotesEarned(((Long)object[3]).longValue());
	           voh.setRank(((Long)object[6]).longValue());
	           if(((Long)object[2]).equals(872L))
	           {
	        	 /*  voh.setMarginVotes((Long)object[5]);
	        	   voh.setMarginVotesPercentage(((Double)object[6]));*/
	        	   vo.setTdpVo(voh);
	           }else if(((Long)object[2]).equals(362L))
	           {
	        	   vo.setIncVo(voh);

	           }else if(((Long)object[2]).equals(1117L)  )
	           {
	        	   vo.setPrpVo(voh);

	           }
	           else if(Long.valueOf(object[5].toString())<11){
	        	   if(((Long)object[2]).equals(886L)){
	           			vo.setTrsVo(voh);
	           		}
	           		else {
			        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
			        	   if(vo1!=null){
			        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Long)object[3]).longValue()) ;
			        	   		if( vo1.getRank()>(Long)object[6] ){
			          			  vo1.setRank((Long)object[6]);
			        	   		}
			        	   }
			        	   else 
			        	   {
			        		   vo1= new PartyElectionTrendsReportHelperVO();
			        		   vo1.setVotesEarned(((Long)object[3]).longValue()) ;
			        		   vo1.setRank((Long)object[6]); 
			        	   }
			        		   
			        	   vo.setOthersVo(vo1);
			           }
	           }
	           else {
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
			     	  if(idMap.containsKey(Long.valueOf(object[0].toString())))
				       vo.setTotalVoters(idMap.get(Long.valueOf(object[0].toString())));
			           vo.setTotalVotesPolled(Long.valueOf(object[1].toString()));
			           vo.setDistrictId(Long.valueOf(object[5].toString()));
			           PartyElectionTrendsReportHelperVO voh= new PartyElectionTrendsReportHelperVO();
			           voh.setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((double)(((double)(((Long)object[3]).longValue())/(double)(Long.valueOf(object[1].toString()).longValue()))*100))));
			           voh.setVotesEarned(((Long)object[3]).longValue());
			           voh.setRank(((Long)object[6]).longValue());
			           if(((Long)object[2]).equals(872L))
			           {
			        	 /*  voh.setMarginVotes((Long)object[5]);
			        	   voh.setMarginVotesPercentage(((Double)object[6]));*/
			        	   vo.setTdpVo(voh);
			           }else if(((Long)object[2]).equals(362L))
			           {
			        	   vo.setIncVo(voh);

			           }else if(((Long)object[2]).equals(1117L))
			           {
			        	   vo.setPrpVo(voh);

			           } else if(Long.valueOf(object[5].toString())<11){
			        	   if(((Long)object[2]).equals(886L)){
			           			vo.setTrsVo(voh);
			           		}
			           		else {
					        	   PartyElectionTrendsReportHelperVO vo1 = vo.getOthersVo();
					        	   if(vo1!=null){
					        		   vo1.setVotesEarned(vo1.getVotesEarned()+((Long)object[3]).longValue()) ;
					        	   		
					        		   if( vo1.getRank()>(Long)object[6] ){
					          			  vo1.setRank((Long)object[6]); 
					        		   }
					        	   }
					        	   else 
					        	   {
					        		   vo1= new PartyElectionTrendsReportHelperVO();
					        		   vo1.setVotesEarned(((Long)object[3]).longValue()) ;
					        		   vo1.setRank((Long)object[6]); 
					        	   }
					        		   
					        	   vo.setOthersVo(vo1);
					           }
			           }
			           
			           else {
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
		List<PartyElectionTrendsReportVO> finalRes= new ArrayList<PartyElectionTrendsReportVO>();

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
			
			if(vo.getTdpVo()==null){
				PartyElectionTrendsReportHelperVO tdpVo=new PartyElectionTrendsReportHelperVO();
				vo.setTdpVo(tdpVo);
			}
			vo.getTdpVo().setMarginVotes(tdp-max);
			System.out.println(vo.getTdpVo().getMarginVotes());
			System.out.println(vo.getTotalVotesPolled());
			System.out.println((double)(vo.getTdpVo().getMarginVotes()/vo.getTotalVotesPolled())*100); 
			
			vo.getTdpVo().setMarginVotesPercentage(Double.valueOf(roundTo2DigitsDoubleValue( (double)(((double)vo.getTdpVo().getMarginVotes()/(double)vo.getTotalVotesPolled())*100))));
			
			//PartyElectionTrendsReportVO vo=	maps.get(year);
			
			//vo.getOthersVo().setVotesEarned(vo.getTotalVotesPolled()-( + vo.getIncVo()!=null ?vo.getIncVo().getVotesEarned().longValue():0L));
			vo.getOthersVo().setPercentage(Double.valueOf(roundTo2DigitsDoubleValue((double)(((double)vo.getOthersVo().getVotesEarned()/(double)vo.getTotalVotesPolled())*100))));
			finalRes.add(vo);
		}
		
	
		Collections.sort(finalRes);
	return finalRes	;
	}
	
	public PartyResultsVerVO getZptcMptcResultsOfConstituency(Long constiutencyId){
		PartyResultsVerVO pvMain=new PartyResultsVerVO();
		
		List<Long> electionTypeIds=new ArrayList<Long>();
		electionTypeIds.add(37l);
		electionTypeIds.add(39l);
		electionTypeIds.add(65l);
		electionTypeIds.add(67l);
		
		List<Object[]> li=nominationDAO.findAllZptcOrMptcResultsInaConstituency(constiutencyId,electionTypeIds,"2009");
		List<Object[]> li1=nominationDAO.findAllZptcOrMptcResultsInaConstituencyPartyWise(constiutencyId, electionTypeIds, "2009");
		Long districtId=null;
		List<Long> districtIds=constituencyDAO.getDistrictIdByConstituencyId(constiutencyId);
		if(districtIds.size()>0){
			districtId=districtIds.get(0).longValue();
		}
		
		Map<Long,Double> electionWisePolledVotesMap=new HashMap<Long, Double>();
		if(li1.size()>0){
			for(Object[] cnt:li1){
				Double count=electionWisePolledVotesMap.get(Long.valueOf(cnt[5].toString()));
				if(count==null){
					count=0.0d;
					electionWisePolledVotesMap.put(Long.valueOf(cnt[5].toString()),count);
				}
				count=count+(Double)cnt[2];
				electionWisePolledVotesMap.put(Long.valueOf(cnt[5].toString()),count);
			}
		}
		
		List<PartyResultsVO> electionList=new ArrayList<PartyResultsVO>();
		for(Object[] ob:li){
			PartyResultsVO pvo=new PartyResultsVO();
			pvo.setElectionId(Long.valueOf(ob[5].toString()));
			Double validCount=electionWisePolledVotesMap.get(Long.valueOf(ob[5].toString()));
			if(ob[4]==validCount){
				pvo.setValidVotes(ob[4]!=null?((Double)ob[4]).longValue():0l);
			}else{
				pvo.setValidVotes(validCount.longValue());
			}
			
			pvo.setVotesPolled(ob[3]!=null?((Double)ob[3]).longValue():0l);
			pvo.setYear(Long.valueOf(ob[0].toString()));
			pvo.setElectionName(ob[1].toString());
			
			electionList.add(pvo);
		}
		
		List<Long> partyIds=new ArrayList<Long>();
		partyIds.add(872l);
		partyIds.add(362l);
		if(districtId<=10 && districtId!=null){
			partyIds.add(886l);
		}
		
		
		for(Object[] ob:li1){
			PartyResultsVO pvo_temp=getMatchedVO(electionList,Long.valueOf(ob[5].toString()));
			List<PartyResultsVO> partyResults=pvo_temp.getPartyResultsVOList();
			if(partyResults==null){
				partyResults=new ArrayList<PartyResultsVO>();
			}
			PartyResultsVO pv=getMatchedPartyVO(partyResults,Long.valueOf(ob[0].toString()));
			if(pv==null){
				pv=new PartyResultsVO();
			}
			pv.setPartyId(Long.valueOf(ob[0].toString()));
			pv.setPartyName(ob[1].toString());
			pv.setVotesEarned(((Double)(ob[2])).longValue());
			pv.setDiffPercent(calcPercentage(pvo_temp.getValidVotes(), pv.getVotesEarned()));
			
			partyResults.add(pv);
			
			
			if(!partyIds.contains(pv.getPartyId())){
				Long existVotes=pvo_temp.getOtherVotes()!=null?pvo_temp.getOtherVotes():0l;
				pvo_temp.setOtherVotes(existVotes+pv.getVotesEarned());
				pvo_temp.setOtherVotesPercent(calcPercentage(pvo_temp.getValidVotes(), pvo_temp.getOtherVotes()));
			}
			pvo_temp.setPartyResultsVOList(partyResults);
			
		}
		
		for(PartyResultsVO pvo:electionList){
			List<PartyResultsVO> partyResults=pvo.getPartyResultsVOList();
			//Collections.sort(partyResults);
			Collections.sort(partyResults, sortByVotesEarned);
			Collections.reverse(partyResults);
			
			if(partyResults.size()>0){
				if(!partyIds.contains(partyResults.get(0).getPartyId().longValue())){
					pvo.setOtherRankedOne(true);
				}
				if(!partyIds.contains(partyResults.get(1).getPartyId().longValue())){
					pvo.setOtherRankedTwo(true);
				}
				
				partyResults.get(0).setRank(1l);
				LOG.error("RANK 1 -" +partyResults.get(0).getPartyId());
				if(partyResults.size()>1){
					partyResults.get(1).setRank(2l);
					LOG.error("RANK 2 -" +partyResults.get(1).getPartyId());
				}
				LOG.error(" --- ---- ");
			}
			
			
			
			Long marginVotes=0l;
			for(int i=0;i<partyResults.size();i++){
				if(partyResults.get(i).getPartyId().longValue()==872l){
					if(i==0){
						if(partyResults.size()>1){
						if(!partyIds.contains(partyResults.get(1).getPartyId())){
							marginVotes=partyResults.get(0).getVotesEarned()-pvo.getOtherVotes();
							//partyResults.get(0).setRank(1l);
							LOG.error("IF TDP AT POS-1 AND OTHERS ARE SECOND LEADING ");
							LOG.error("TDP VOTES -"+partyResults.get(0).getVotesEarned());
							LOG.error("OTHERS VOTES -"+pvo.getOtherVotes());
							marginVotes=partyResults.get(0).getVotesEarned()-pvo.getOtherVotes();
							LOG.error("MARGIN VOTES -"+marginVotes);
							LOG.error(" ----------------------------- ");
							pvo.setMarginVotes(marginVotes);
							pvo.setMarginPercent(calcPercentage(pvo.getValidVotes(), marginVotes));
						}else{
							LOG.error("IF TDP AT POS-1 AND OTHER PARTIES ARE SECOND LEADING ");
							LOG.error("TDP VOTES -"+partyResults.get(0).getVotesEarned());
							LOG.error("SECOND PARTY VOTES -"+partyResults.get(1).getVotesEarned());
							
							marginVotes=partyResults.get(0).getVotesEarned()-partyResults.get(1).getVotesEarned();
							//partyResults.get(0).setRank(1l);
							pvo.setMarginVotes(marginVotes);
							LOG.error("MARGIN VOTES -"+marginVotes);
							LOG.error(" ----------------------------- ");
							pvo.setMarginPercent(calcPercentage(pvo.getValidVotes(), marginVotes));
						}
						}else{
							marginVotes=0l;
							//partyResults.get(0).setRank(1l);
							pvo.setMarginVotes(marginVotes);
							pvo.setMarginPercent(calcPercentage(pvo.getValidVotes(), marginVotes));
						}
					}else{
						if(!partyIds.contains(partyResults.get(0).getPartyId())){
							LOG.error("IF OTHERS AT POS-1 AND TDP IS RUNNING BEHIND ");
							LOG.error("TDP VOTES -"+partyResults.get(i).getVotesEarned());
							LOG.error("OTHERS VOTES -"+pvo.getOtherVotes());
							marginVotes=partyResults.get(i).getVotesEarned()-pvo.getOtherVotes();
							LOG.error("MARGIN VOTES -"+marginVotes);
							LOG.error(" ----------------------------- ");
							//partyResults.get(0).setRank(1l);
							pvo.setMarginVotes(marginVotes);
							pvo.setMarginPercent(calcPercentage(pvo.getValidVotes(), marginVotes));
						}else{
							marginVotes=partyResults.get(i).getVotesEarned()-partyResults.get(0).getVotesEarned();
							//partyResults.get(0).setRank(1l);
							LOG.error("IF OTHER PARTY AT POS-1 AND TDP IS RUNNING BEHIND ");
							LOG.error("TDP VOTES -"+partyResults.get(i).getVotesEarned());
							LOG.error("OTHERS VOTES -"+partyResults.get(0).getVotesEarned());
							pvo.setMarginVotes(marginVotes);
							LOG.error("MARGIN VOTES -"+marginVotes);
							LOG.error(" ----------------------------- ");
							pvo.setMarginPercent(calcPercentage(pvo.getValidVotes(), marginVotes));
						}
					}
				}
			}
		}
		
		pvMain.setPartyResultsVOList(electionList);
		pvMain.setDistrictId(districtId);
		return pvMain;
		
	}
	
	public PartyResultsVerVO getMuncipalCorpPrevResults(Long constiutencyId){
		List<Long> electionIds=new ArrayList<Long>();
		electionIds.add(40l);
		electionIds.add(42l);
		electionIds.add(201l);
		
		//electionIds.add(63l);
		
		//List<Object[]> li=nominationDAO.findAllZptcOrMptcResultsInaConstituency(323l,electionTypeIds,"2009");
		//List<Object[]> li1=nominationDAO.findAllZptcOrMptcResultsInaConstituencyPartyWise(323l, electionTypeIds, "2009");
		Long districtId=null;
		List<Long> districtIds=constituencyDAO.getDistrictIdByConstituencyId(constiutencyId);
		if(districtIds.size()>0){
			districtId=districtIds.get(0).longValue();
		}
		
		List<Object[]> li=nominationDAO.findMuncipalOrCorpResultsInaConstituency(constiutencyId,electionIds);
		List<Object[]> li1=nominationDAO.findMuncipalOrCorpResultsInaConstituencyPartyWise(constiutencyId, electionIds);
		
		
		String name="";
		String localBodyType="";
		if(li.size()>0){
			name=li.get(0)[0].toString()+" "+li.get(0)[9].toString()+" "+li.get(0)[1].toString()+" Results - WARD Wise";
			localBodyType=li.get(0)[1].toString();
		}
		
		/*List<Object[]> li=nominationDAO.findMuncipalOrCorpResultsOfGMCInaConstituency(315l, electionIds);
		List<Object[]> li1=nominationDAO.findMuncipalOrCorpResultsOfGMCInaConstituencyPartyWise(315l, electionIds);*/
		
		
		List<PartyResultsVO> electionList=new ArrayList<PartyResultsVO>();
		Map<Long,List<PartyResultsVO>> eleMap=new HashMap<Long, List<PartyResultsVO>>();
		
		for(Object[] ob:li){
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[7].toString()));
			if(wardsList==null){
				wardsList=new ArrayList<PartyResultsVO>();
			}
			
			eleMap.put(Long.valueOf(ob[7].toString()), wardsList);
		}
		for(Object[] ob:li){
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[7].toString()));
			
			PartyResultsVO pvo=new PartyResultsVO();
			pvo.setElectionId(Long.valueOf(ob[7].toString()));
			pvo.setValidVotes(ob[6]!=null?((Double)ob[6]).longValue():0l);
			pvo.setVotesPolled(ob[5]!=null?((Double)ob[5]).longValue():0l);
			pvo.setYear(Long.valueOf(ob[0].toString()));
			pvo.setElectionName(ob[1].toString());
			pvo.setLocation(ob[3].toString());
			pvo.setLocationId(Long.valueOf(ob[2].toString()));
			wardsList.add(pvo);
		}
		
		List<Long> partyIds=new ArrayList<Long>();
		partyIds.add(872l);
		partyIds.add(362l);
		partyIds.add(886l);
		partyIds.add(163l);
		
		
		for(Object[] ob:li1){
			
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[5].toString()));
			
			PartyResultsVO pvo_temp=getMatchedWardVO(wardsList,Long.valueOf(ob[5].toString()),Long.valueOf(ob[6].toString()));
			
			List<PartyResultsVO> partyResults=pvo_temp.getPartyResultsVOList();
			if(partyResults==null){
				partyResults=new ArrayList<PartyResultsVO>();
			}
			PartyResultsVO pv=getMatchedPartyVO1(partyResults,Long.valueOf(ob[0].toString()),Long.valueOf(ob[9].toString()));
			if(pv==null){
				pv=new PartyResultsVO();
			}
			pv.setPartyId(Long.valueOf(ob[0].toString()));
			pv.setPartyName(ob[1].toString());
			pv.setVotesEarned(((Double)(ob[2])).longValue());
			pv.setDiffPercent(calcPercentage(pvo_temp.getVotesPolled(), pv.getVotesEarned()));
			pv.setRank(Long.valueOf(ob[8].toString()));
			pv.setNominationId(Long.valueOf(ob[9].toString()));
			pv.setLocationId(pvo_temp.getLocationId());
			partyResults.add(pv);
			
			
			if(!partyIds.contains(pv.getPartyId())){
				Long existVotes=pvo_temp.getOtherVotes()!=null?pvo_temp.getOtherVotes():0l;
				pvo_temp.setOtherVotes(existVotes+pv.getVotesEarned());
				pvo_temp.setOtherVotesPercent(calcPercentage(pvo_temp.getVotesPolled(), pvo_temp.getOtherVotes()));
			}
			pvo_temp.setPartyResultsVOList(partyResults);
			
		}
		

		PartyResultsVerVO prvo=new PartyResultsVerVO();
		Map<Long,PartyResultsVerVO> partyRanksMap=new HashMap<Long, PartyResultsVerVO>();
		
		Map<Long,PartyResultsVO> party_res=new HashMap<Long, PartyResultsVO>();
		for (Entry<Long, List<PartyResultsVO>> entry : eleMap.entrySet())
		{
		List<PartyResultsVO> electionList1=entry.getValue();
		Long totalValidVotes=0l;
		for(PartyResultsVO pvo:electionList1){
			totalValidVotes+=pvo.getValidVotes();
		}
		for(PartyResultsVO pvo:electionList1){
			List<PartyResultsVO> partyResults=pvo.getPartyResultsVOList();
			List<PartyResultsVO> partyResultsToRmve=new ArrayList<PartyResultsVO>();
			Collections.sort(partyResults);
			Long marginVotes=0l;
			for(int i=0;i<partyResults.size();i++){
				//PARTICIPATED AND RANK COUNT START
				PartyResultsVO parr=party_res.get(partyResults.get(i).getPartyId().longValue());
				if(parr==null){
					parr=new PartyResultsVO();
					parr.setWon(0l);
					parr.setParticipated(0l);
					parr.setVotesEarned(0l);
					parr.setPercentage(calcPercentage(totalValidVotes, parr.getVotesEarned()));
					//party_res.put(partyResults.get(i).getPartyId().longValue(), parr);
				} 
				
				if(partyResults.get(i).getRank()!=null){
					if(partyResults.get(i).getRank()==1){
						Long exist=parr.getWon();
						parr.setWon(exist+1l);
					}
				}
					Long existPrtcptd=parr.getParticipated();
					parr.setParticipated(existPrtcptd+1l);
					
					
					Long votesErnd_exist=parr.getVotesEarned();
					parr.setVotesEarned(partyResults.get(i).getVotesEarned()+votesErnd_exist);
					parr.setPercentage(calcPercentage(totalValidVotes,parr.getVotesEarned()));
					
					
					if(!partyIds.contains(partyResults.get(i).getPartyId().longValue())){
						if(prvo.getParticipated()==null){
							prvo.setParticipated(0l);
							prvo.setWon(0l);
							prvo.setOtherVotes(0l);
							prvo.setOtherVotesPercent(calcPercentage(totalValidVotes,prvo.getOtherVotes()));
						}
						Long exPr=prvo.getParticipated();
						prvo.setParticipated(exPr+1l);
						
						if(partyResults.get(i).getRank()==1){
							Long exWn=prvo.getWon();
							prvo.setWon(exWn+1l);
						}
						
						Long votesErnd=prvo.getOtherVotes();
						prvo.setOtherVotes(partyResults.get(i).getVotesEarned()+votesErnd);
						prvo.setOtherVotesPercent(calcPercentage(totalValidVotes,prvo.getOtherVotes()));
					
					}
					
					if(IConstants.INDEPENDENT_ID.longValue()!=partyResults.get(i).getPartyId().longValue()){
						if(parr.getPartyId()!=null && parr.getLocationId()!=null){
							if(parr.getPartyId().longValue()==partyResults.get(i).getPartyId().longValue() && parr.getLocationId().longValue()==partyResults.get(i).getLocationId().longValue()){
								partyResultsToRmve.add(partyResults.get(i));
							}
						}
					}
					parr.setLocationId(partyResults.get(i).getLocationId());
					parr.setPartyId(partyResults.get(i).getPartyId().longValue());
					party_res.put(partyResults.get(i).getPartyId().longValue(), parr);
				//END	
				
					
				
					if(partyResults.get(i).getPartyId().longValue()==872l){
						if(i==0){
							if(partyResults.size()>1){
							if(!partyIds.contains(partyResults.get(1).getPartyId())){
								marginVotes=partyResults.get(0).getVotesEarned()-pvo.getOtherVotes();
								partyResults.get(0).setRank(1l);
								pvo.setMarginVotes(marginVotes);
							}else{
								marginVotes=partyResults.get(0).getVotesEarned()-partyResults.get(1).getVotesEarned();
								partyResults.get(0).setRank(1l);
								pvo.setMarginVotes(marginVotes);
							}
							}else{
								marginVotes=0l;
								partyResults.get(0).setRank(1l);
								pvo.setMarginVotes(marginVotes);
							}
						}else{
							if(!partyIds.contains(partyResults.get(0).getPartyId())){
								marginVotes=partyResults.get(i).getPartyId().longValue()-pvo.getOtherVotes();
								partyResults.get(0).setRank(1l);
								pvo.setMarginVotes(marginVotes);
							}else{
								marginVotes=partyResults.get(i).getVotesEarned()-partyResults.get(0).getVotesEarned();
								partyResults.get(0).setRank(1l);
								pvo.setMarginVotes(marginVotes);
							}
						}
					}
			}
			
			//REMOVING RESULTS WHEN MORE THAN ONE MEMBER OF A PARTY PARTICIPATED FROM SAME WARD -- START --SASI
			if(partyResultsToRmve.size()>0){
				for(PartyResultsVO prTemp:partyResultsToRmve){
					Long nominId=prTemp.getNominationId();
					ListIterator<PartyResultsVO> it = partyResults.listIterator();
					while ( it.hasNext() ) {
						PartyResultsVO prTempIn = it.next();
					      if (prTempIn.getNominationId().longValue()==nominId.longValue()){ 
					        it.remove();
					      }
					}
				}
			}
			//END -- SASI
			
		}
		prvo.setElectionId(entry.getKey());
		
		List<PartyResultsVO> partyStrengths=new ArrayList<PartyResultsVO>(party_res.values());
			if(prvo.getPartyResultsVOList()==null){
				prvo.setPartyResultsVOList(electionList1);
				prvo.setPartyStrengths(partyStrengths);
			}else{
				List<PartyResultsVO> eleList=prvo.getPartyResultsVOList();
				eleList.addAll(electionList1);
				prvo.setPartyResultsVOList(eleList);
			}
		}
		prvo.setDistrictId(districtId);
		prvo.setMuncipalOrCorpOrGmc(name);
		prvo.setElectionBodyType(localBodyType);
		return prvo;
	}
	
	public PartyResultsVerVO getMuncipalCorpPrevResultsInGHMC(Long constiutencyId){
		List<Long> electionIds=new ArrayList<Long>();
		
		electionIds.add(63l);
		
		List<Object[]> li=nominationDAO.findMuncipalOrCorpResultsOfGMCInaConstituency(constiutencyId, electionIds);
		List<Object[]> li1=nominationDAO.findMuncipalOrCorpResultsOfGMCInaConstituencyPartyWise(constiutencyId, electionIds);
		
		String name="";
		String localBodyType="";
		if(li.size()>0){
			name=li.get(0)[0].toString()+" "+li.get(0)[8].toString()+" "+li.get(0)[1].toString()+" Results - WARD Wise";
			localBodyType=li.get(0)[1].toString();
		}
		
		
		Long districtId=null;
		List<Long> districtIds=constituencyDAO.getDistrictIdByConstituencyId(constiutencyId);
		if(districtIds.size()>0){
			districtId=districtIds.get(0).longValue();
		}
		
		List<PartyResultsVO> electionList=new ArrayList<PartyResultsVO>();
		Map<Long,List<PartyResultsVO>> eleMap=new HashMap<Long, List<PartyResultsVO>>();
		
		for(Object[] ob:li){
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[7].toString()));
			if(wardsList==null){
				wardsList=new ArrayList<PartyResultsVO>();
			}
			
			eleMap.put(Long.valueOf(ob[7].toString()), wardsList);
		}
		for(Object[] ob:li){
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[7].toString()));
			
			PartyResultsVO pvo=new PartyResultsVO();
			pvo.setElectionId(Long.valueOf(ob[7].toString()));
			pvo.setValidVotes(ob[6]!=null?((Double)ob[6]).longValue():0l);
			pvo.setVotesPolled(ob[5]!=null?((Double)ob[5]).longValue():0l);
			pvo.setYear(Long.valueOf(ob[0].toString()));
			pvo.setElectionName(ob[1].toString());
			pvo.setLocation(ob[3].toString());
			pvo.setLocationId(Long.valueOf(ob[2].toString()));
			wardsList.add(pvo);
		}
		
		List<Long> partyIds=new ArrayList<Long>();
		partyIds.add(872l);
		partyIds.add(362l);
		partyIds.add(886l);
		partyIds.add(163l);
		
		for(Object[] ob:li1){
			
			List<PartyResultsVO> wardsList=eleMap.get(Long.valueOf(ob[5].toString()));
			
			PartyResultsVO pvo_temp=getMatchedWardVO(wardsList,Long.valueOf(ob[5].toString()),Long.valueOf(ob[6].toString()));
			
			List<PartyResultsVO> partyResults=pvo_temp.getPartyResultsVOList();
			if(partyResults==null){
				partyResults=new ArrayList<PartyResultsVO>();
			}
			PartyResultsVO pv=getMatchedPartyVO1(partyResults,Long.valueOf(ob[0].toString()),Long.valueOf(ob[9].toString()));
			if(pv==null){
				pv=new PartyResultsVO();
				pv.setRank(0l);
			}
			pv.setPartyId(Long.valueOf(ob[0].toString()));
			pv.setPartyName(ob[1].toString());
			pv.setVotesEarned(((Double)(ob[2])).longValue());
			pv.setDiffPercent(calcPercentage(pvo_temp.getVotesPolled(), pv.getVotesEarned()));
			pv.setRank(Long.valueOf(ob[8].toString()));
			pv.setNominationId(Long.valueOf(ob[9].toString()));
			partyResults.add(pv);
			
			
			if(!partyIds.contains(pv.getPartyId())){
				Long existVotes=pvo_temp.getOtherVotes()!=null?pvo_temp.getOtherVotes():0l;
				pvo_temp.setOtherVotes(existVotes+pv.getVotesEarned());
				pvo_temp.setOtherVotesPercent(calcPercentage(pvo_temp.getVotesPolled(), pvo_temp.getOtherVotes()));
			}
			pvo_temp.setPartyResultsVOList(partyResults);
			
		}
		

		PartyResultsVerVO prvo=new PartyResultsVerVO();
		Map<Long,PartyResultsVO> party_res=new HashMap<Long, PartyResultsVO>();
		
		for (Entry<Long, List<PartyResultsVO>> entry : eleMap.entrySet())
		{
		List<PartyResultsVO> electionList1=entry.getValue();
		Long totalValidVotes=0l;
		for(PartyResultsVO pvo:electionList1){
			totalValidVotes+=pvo.getValidVotes();
		}
		for(PartyResultsVO pvo:electionList1){
			List<PartyResultsVO> partyResultsToRmve=new ArrayList<PartyResultsVO>();
			List<PartyResultsVO> partyResults=pvo.getPartyResultsVOList();
			Collections.sort(partyResults);
			Long marginVotes=0l;
			
			for(int i=0;i<partyResults.size();i++){
				
				//PARTICIPATED AND RANK COUNT START
				PartyResultsVO parr=party_res.get(partyResults.get(i).getPartyId().longValue());
				if(parr==null){
					parr=new PartyResultsVO();
					parr.setWon(0l);
					parr.setParticipated(0l);
					parr.setVotesEarned(0l);
					parr.setPercentage(calcPercentage(totalValidVotes, parr.getVotesEarned()));
				}
				
				if(partyResults.get(i).getRank()!=null){
					if(partyResults.get(i).getRank()==1){
						Long exist=parr.getWon();
						parr.setWon(exist+1l);
					}
				}
					Long existPrtcptd=parr.getParticipated();
					parr.setParticipated(existPrtcptd+1l);
					
					Long votesErnd_exist=parr.getVotesEarned();
					parr.setVotesEarned(partyResults.get(i).getVotesEarned()+votesErnd_exist);
					parr.setPercentage(calcPercentage(totalValidVotes,parr.getVotesEarned()));
					
					
					if(!partyIds.contains(partyResults.get(i).getPartyId().longValue())){
						if(prvo.getParticipated()==null){
							prvo.setParticipated(0l);
							prvo.setWon(0l);
							prvo.setOtherVotes(0l);
							prvo.setOtherVotesPercent(calcPercentage(totalValidVotes,prvo.getOtherVotes()));
						}
						Long exPr=parr.getParticipated();
						prvo.setParticipated(exPr+1l);
						
						if(partyResults.get(i).getRank()==1){
							Long exWn=prvo.getWon();
							prvo.setWon(exWn+1l);
						}
						
						Long votesErnd=prvo.getOtherVotes();
						prvo.setOtherVotes(partyResults.get(i).getVotesEarned()+votesErnd);
						prvo.setOtherVotesPercent(calcPercentage(totalValidVotes,prvo.getOtherVotes()));
					
					}
					
					if(IConstants.INDEPENDENT_ID.longValue()!=partyResults.get(i).getPartyId().longValue()){
						if(parr.getPartyId()!=null && parr.getLocationId()!=null){
							if(parr.getPartyId().longValue()==partyResults.get(i).getPartyId().longValue() && parr.getLocationId().longValue()==partyResults.get(i).getLocationId().longValue()){
								partyResultsToRmve.add(partyResults.get(i));
							}
						}
					}
					parr.setLocationId(partyResults.get(i).getLocationId());
					parr.setPartyId(partyResults.get(i).getPartyId().longValue());
					party_res.put(partyResults.get(i).getPartyId().longValue(), parr);
					
				//END
				
					if(partyResults.get(i).getPartyId().longValue()==872l){
						if(i==0){
							if(partyResults.size()>1){
							if(!partyIds.contains(partyResults.get(1).getPartyId())){
								marginVotes=partyResults.get(0).getVotesEarned()-pvo.getOtherVotes();
								partyResults.get(0).setRank(1l);
								pvo.setMarginVotes(marginVotes);
							}else{
								marginVotes=partyResults.get(0).getVotesEarned()-partyResults.get(1).getVotesEarned();
								partyResults.get(0).setRank(1l);
								pvo.setMarginVotes(marginVotes);
							}
							}else{
								marginVotes=0l;
								partyResults.get(0).setRank(1l);
								pvo.setMarginVotes(marginVotes);
							}
						}else{
							if(!partyIds.contains(partyResults.get(0).getPartyId())){
								marginVotes=partyResults.get(i).getPartyId().longValue()-pvo.getOtherVotes();
								partyResults.get(0).setRank(1l);
								pvo.setMarginVotes(marginVotes);
							}else{
								marginVotes=partyResults.get(i).getVotesEarned()-partyResults.get(0).getVotesEarned();
								partyResults.get(0).setRank(1l);
								pvo.setMarginVotes(marginVotes);
							}
						}
					}
			}
			
			//REMOVING RESULTS WHEN MORE THAN ONE MEMBER OF A PARTY PARTICIPATED FROM SAME WARD -- START --SASI
			if(partyResultsToRmve.size()>0){
				for(PartyResultsVO prTemp:partyResultsToRmve){
					Long nominId=prTemp.getNominationId();
					ListIterator<PartyResultsVO> it = partyResults.listIterator();
					while ( it.hasNext() ) {
						PartyResultsVO prTempIn = it.next();
					      if (prTempIn.getNominationId().longValue()==nominId.longValue()){ 
					        it.remove();
					      }
					}
				}
			}
			//END -- SASI
		}
		prvo.setElectionId(entry.getKey());
		
		List<PartyResultsVO> partyStrengths=new ArrayList<PartyResultsVO>(party_res.values());
		
			if(prvo.getPartyResultsVOList()==null){
				prvo.setPartyResultsVOList(electionList1);
				prvo.setPartyStrengths(partyStrengths);
			}else{
				List<PartyResultsVO> eleList=prvo.getPartyResultsVOList();
				eleList.addAll(electionList1);
				prvo.setPartyResultsVOList(eleList);
			}
		}
		prvo.setMuncipalOrCorpOrGmc(name);
		prvo.setElectionBodyType(localBodyType);
		prvo.setDistrictId(districtId);
		return prvo;
	} 
	
	public PartyResultsVO getMatchedVO(List<PartyResultsVO> pvoList,Long electionId){
		for(PartyResultsVO pv:pvoList){
			if(pv.getElectionId().longValue()==electionId.longValue()){
				return pv;
			}
		}
		return null;
	}
	public PartyResultsVO getMatchedPartyVO(List<PartyResultsVO> pvoList,Long partyId){
		for(PartyResultsVO pv:pvoList){
			if(pv.getPartyId().longValue()==partyId.longValue()){
				return pv;
			}
		}
		return null;
	}
	
	public PartyResultsVO getMatchedPartyVO1(List<PartyResultsVO> pvoList,Long partyId,Long nominationId){
		for(PartyResultsVO pv:pvoList){
			if(pv.getPartyId().longValue()==partyId.longValue() && pv.getNominationId().longValue() == nominationId.longValue()){
				return pv;
			}
		}
		return null;
	}
	
	public PartyResultsVO getMatchedWardVO(List<PartyResultsVO> pvoList,Long electionId,Long wardId){
		for(PartyResultsVO pv:pvoList){
			if(pv.getElectionId().longValue()==electionId.longValue() && pv.getLocationId().longValue()==wardId.longValue()){
				return pv;
			}
		}
		return null;
	}
	
	 public PartyPositionResultsVO getPartyChanges(Long constituencyId,List<Long> assemblyEleIdsList,List<Long> partiesSelected)
		{
		  List<PartyPositionResultsVO> resultList = null;
		  List<PartyPositionResultsVO> locationsList=new ArrayList<PartyPositionResultsVO>();
		  
		  PartyPositionResultsVO finalVO=new PartyPositionResultsVO();
			try{
			List<Long> constituencyIdsList = new ArrayList<Long>(0);
			constituencyIdsList.add(constituencyId);
			//List<Long> assemblyEleIdsList = new ArrayList<Long>(0);
			List<Long> mandalIds = new ArrayList<Long>();
			List<Long> localbodyIds = new ArrayList<Long>();
			List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId,IConstants.PRESENT_YEAR, null);
			if(mandalsList != null && mandalsList.size() > 0)
			for(SelectOptionVO vo : mandalsList)
			if(vo.getId().toString().substring(0,1).equalsIgnoreCase("2"))
			mandalIds.add(new Long(vo.getId().toString().substring(1)));
			else
				localbodyIds.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(vo.getId().toString().substring(1))).get(0));

			
			Constituency constituency = constituencyDAO.get(constituencyId);
			
			Map<Long, List<PartyPositionResultsVO>> elections=new HashMap<Long, List<PartyPositionResultsVO>>();
			Map<Long, String> electionsTypeMap=new HashMap<Long,String>();
			
			Collections.sort(assemblyEleIdsList);
			
			

			/*assemblyEleIdsList.add(IConstants.ELECTION_YEAR2);
			assemblyEleIdsList.add(IConstants.ELECTION_YEAR1);*/
			
			List<PartyPositionResultsVO> prevPancResultList = new ArrayList<PartyPositionResultsVO>();
			List<PartyPositionResultsVO> currPancResultList = new ArrayList<PartyPositionResultsVO>();
			
			elections.put(assemblyEleIdsList.get(0).longValue(),prevPancResultList);
			elections.put(assemblyEleIdsList.get(1).longValue(),currPancResultList);
			
			
			electionsTypeMap.put(assemblyEleIdsList.get(0).longValue(),"PAST");
			electionsTypeMap.put(assemblyEleIdsList.get(1).longValue(),"CURRENT");
			
			
			if(assemblyEleIdsList != null && assemblyEleIdsList.size() > 0)
			{
				resultList = new ArrayList<PartyPositionResultsVO>(0);
				  //List<SuggestiveRange> suggestiveRangeList = suggestiveRangeDAO.getSuggestiveRangeList();
					  
				PartyPositionResultsVO partyPositionResultsVO = null;
				  for(Long eleId :assemblyEleIdsList)
				  {
					Election election = electionDAO.get(eleId);
					partyPositionResultsVO = new PartyPositionResultsVO();
					List<PartyPositionResultsVO> pancResultList = null;
					
					pancResultList = elections.get(eleId.longValue());
                /* if(eleId.longValue() ==38l){
					  pancResultList = currPancResultList;
                 }else{
                	  pancResultList = prevPancResultList;
                 }*/
					
					Map<Long,String> partyNamesMap=new HashMap<Long, String>();
					List<Object[]> partyNames=partyDAO.getPartyShortNameByIds(partiesSelected);
					for(Object[] ob:partyNames){
						partyNamesMap.put(Long.valueOf(ob[0].toString()), ob[1].toString());
					}
					
                 partyPositionResultsVO.setWeakPollingPercentVOList(pancResultList);


                 partyPositionResultsVO.setName(election.getElectionYear() != null?election.getElectionYear():" ");
                 partyPositionResultsVO.setId(eleId);
                 partyPositionResultsVO.setConstituencyId(constituencyId);
                 partyPositionResultsVO.setPartiesCount(partiesSelected.size());
                 partyPositionResultsVO.setYearSpanCount((partiesSelected.size()*2)+1);
                 
                 
                 
                 List<PartyPositionResultsVO> partiesList=new ArrayList<PartyPositionResultsVO>();
                 
                 Collections.sort(partiesSelected);
                 
                 for(Long partyId:partiesSelected){
                	 PartyPositionResultsVO pvo=new PartyPositionResultsVO();
                	 pvo.setPartyId(partyId);
                	 pvo.setPartyName(partyNamesMap.get(partyId));
                	 
                	 partiesList.add(pvo);
                 }
                 
                 
                 partyPositionResultsVO.setPartiesList(partiesList);
					
					if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
					{
						List<Long> panchayatIds = null;
						if(mandalIds != null && mandalIds.size() > 0)
						 panchayatIds =hamletBoothElectionDAO.getPanchayatIdsByEleIdAndMandalIdsList(mandalIds,eleId);
						if(panchayatIds != null && panchayatIds.size() > 0)
							getMandalWisePartyPerformanceReportNew(constituencyId,eleId,pancResultList,panchayatIds,null,partiesSelected,partyNamesMap,electionsTypeMap);	
						
					}
					else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN))
					{
						List<Long> panchayatIds = null;
						if(mandalIds != null && mandalIds.size() > 0)
						 panchayatIds =hamletBoothElectionDAO.getPanchayatIdsByEleIdAndMandalIdsList(mandalIds,eleId);
						List<Object[]> boothIdsList = null;
						if(localbodyIds != null && localbodyIds.size() > 0)
						 boothIdsList = hamletBoothElectionDAO.getBoothsByLocalBodyNElectionId(localbodyIds,eleId);
						if(panchayatIds != null && panchayatIds.size() > 0)
							getMandalWisePartyPerformanceReportNew(constituencyId,eleId,pancResultList,panchayatIds,boothIdsList,partiesSelected,partyNamesMap,electionsTypeMap);
					}
					resultList.add(partyPositionResultsVO);
				  }
			}
			//PROCESSING THE RESULTS OF TWO YEARS
			
		//	System.out.println(resultList.size());
			
			if(resultList != null && resultList.size() == 2){
				List<PartyPositionResultsVO> prevResults = resultList.get(0).getWeakPollingPercentVOList();
				List<PartyPositionResultsVO> currResults = resultList.get(1).getWeakPollingPercentVOList();
				
				finalVO.setPartiesCount(partiesSelected.size());
                finalVO.setYearSpanCount((partiesSelected.size()*2)+1);
                finalVO.setPastYear(resultList.get(0).getName());
                finalVO.setCurrentYear(resultList.get(1).getName());
                finalVO.setPartiesList(resultList.get(0).getPartiesList());
				
				for(PartyPositionResultsVO tempParam:currResults){
					PartyPositionResultsVO pvo=new PartyPositionResultsVO();
					pvo.setLocation(tempParam.getName());
					pvo.setId(tempParam.getId());
					PartyPositionResultsVO posvo=getMatchedPastYearVO(tempParam.getId(),prevResults);
					if(posvo==null){
						posvo=new PartyPositionResultsVO();
					}
					List<PartyPositionResultsVO> finalPartyList=null;
					if(tempParam.getPartiesList()!=null){
						
						finalPartyList=pvo.getPartiesList();
						if(finalPartyList==null){
							finalPartyList=new ArrayList<PartyPositionResultsVO>();
						}
						
						
						List<Long> partyIdsLocal=new ArrayList<Long>();
						for(PartyPositionResultsVO param:tempParam.getPartiesList()){
							PartyPositionResultsVO party=new PartyPositionResultsVO();
							
							Double presPercntg=null;
							Double prevPercntg=null;
							Double difference=null;
							partyIdsLocal.add(param.getPartyId());
							
							party.setPartyId(param.getPartyId());
							party.setPresentValidVotes(param.getTotalValidVotes());
							party.setPresentYearVotersEarned(param.getVotesEarned());
							party.setPresentYearPercentage(String.valueOf(param.getPercentage()));
							if(param.isCurrentAlianced()){
								party.setCurrentAlianced(true);
								party.setCurrentAlianceName(param.getPartyName());
							}
							presPercntg=param.getPercentage();
							
							List<PartyPositionResultsVO> pastPartiesList=posvo.getPartiesList();
							if(pastPartiesList!=null){
								PartyPositionResultsVO pastVO=getMatchedPartyResultsVO(pastPartiesList,param.getPartyId());
								if(pastVO!=null){
									party.setPastValidVotes(pastVO.getTotalValidVotes());
									party.setPastYearVotesEarned(pastVO.getVotesEarned());
									party.setPastYearPercentage(String.valueOf(pastVO.getPercentage()));
									
									if(pastVO.isPastAlianced()){
										party.setPastAlianced(true);
										party.setPastAlianceName(pastVO.getPartyName());
									}
									
									
									prevPercntg=pastVO.getPercentage();
								}
							}
							
							if(party.getPresentYearPercentage()==null){
								presPercntg=0.0d;
							}
							if(party.getPastYearPercentage()==null){
								prevPercntg=0.0d;
							}
						
							if(presPercntg!=null && prevPercntg!=null){
								difference=presPercntg-prevPercntg;
								difference=new BigDecimal(difference).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							}
							
							party.setPercentage(difference);
							if(difference>0){
								party.setStatus("UP");
							}else{
								party.setStatus("DOWN");
							}
							
							finalPartyList.add(party);
						}
						
						List<PartyPositionResultsVO> pastPartiesList=posvo.getPartiesList();
						if(pastPartiesList!=null){
							for(PartyPositionResultsVO plcl:pastPartiesList){
								if(!partyIdsLocal.contains(plcl.getPartyId().longValue()) && partiesSelected.contains(plcl.getPartyId().longValue())){
									Double presPercntg=null;
									Double prevPercntg=null;
									Double difference=null;
									
									PartyPositionResultsVO party=new PartyPositionResultsVO();
									party.setPartyId(plcl.getPartyId().longValue());
									party.setPastValidVotes(plcl.getTotalValidVotes());
									party.setPastYearVotesEarned(plcl.getVotesEarned());
									party.setPastYearPercentage(String.valueOf(plcl.getPercentage()));
									if(plcl.isPastAlianced()){
										party.setPastAlianced(true);
										party.setPastAlianceName(plcl.getPartyName());
									}
									prevPercntg=plcl.getPercentage();
									
									if(presPercntg==null){
										presPercntg=0.0d;
									}
									if(party.getPastYearPercentage()==null){
										prevPercntg=0.0d;
									}
								
									if(presPercntg!=null && prevPercntg!=null){
										difference=presPercntg-prevPercntg;
									}
									
									party.setPercentage(difference);
									if(difference>0){
										party.setStatus("UP");
									}else{
										party.setStatus("DOWN");
									}
									
									finalPartyList.add(party);
								}
							}
						}
						
						
					}
					
					
					pvo.setPresentValidVotes(tempParam.getTotalValidVotes());
					pvo.setPastValidVotes(posvo.getTotalValidVotes());
					pvo.setVotesIncreased(tempParam.getTotalValidVotes()-posvo.getTotalValidVotes());
					pvo.setPartiesList(finalPartyList);
					
					 if(finalPartyList!=null){
						 Collections.sort(finalPartyList, sortPartiesById);
					 }
					
					locationsList.add(pvo);
				}
				finalVO.setLocationWiseList(locationsList);
				
				//System.out.println(locationsList.size());
				
			}
			
			
			}catch(Exception e){
				LOG.error("Exception Raised in PartyChanges in Stratagic Report Service" , e);
			}
			
			return finalVO;
		}
	 
	 public PartyPositionResultsVO getMatchedPastYearVO(Long id,List<PartyPositionResultsVO> resultsList){
		 for(PartyPositionResultsVO pv:resultsList){
				if(pv.getId()==id.longValue()){
					return pv;
				}
			}
			return null;
	 }
	 public PartyPositionResultsVO getMatchedPartyResultsVO(List<PartyPositionResultsVO> pvoList,Long partyId){
			for(PartyPositionResultsVO pv:pvoList){
				if(pv.getPartyId().longValue()==partyId.longValue()){
					return pv;
				}
			}
			return null;
		}
	 
	 	
	 public void getMandalWisePartyPerformanceReportNew(Long constituencyId,Long electionId,List<PartyPositionResultsVO> pancResultList,List<Long> panchaytIdsList,List<Object[]> localbodybooths,List<Long> partiesSelected,Map<Long,String> partyNamesMap,Map<Long,String> electionTypeMap)
		{
			try{
			Map<Long,List<Long>> boothIds = new HashMap<Long, List<Long>>();//Map<boothId,List<panchayatId>>
			Map<Long,Map<Long,Long>> resultMap = new HashMap<Long, Map<Long,Long>>(0);//Map<panchayatId,Map<partyId,totalvoters>>
			Map<Long,Map<Long,Long>> resultMap1 = new HashMap<Long,Map<Long,Long>>(0);//Map<localbodyname,Map<partyId,totalvoters>>
			Map<Long,List<Long>> boothIdMap = new HashMap<Long, List<Long>>(0);//<localBodyName,<boothIds>
			//List<Long> boothIds1 = null;
			List<Long> booths = new ArrayList<Long>();
			if(localbodybooths != null && localbodybooths.size() > 0)
			{
			for(Object[] params: localbodybooths)
			{
				List<Long> boothIds1 = boothIdMap.get(params[2]);
					if(boothIds1 == null)
					{
					boothIds1 = new ArrayList<Long>(0);	
					boothIdMap.put((Long)params[2], boothIds1);
					}
					if(!boothIds1.contains((Long)params[0]))
					boothIds1.add((Long)params[0]);
			}
			
			for(Long localbodyId : boothIdMap.keySet())
			{
				booths = boothIdMap.get(localbodyId);
				List<Object[]> partyResult = candidateBoothResultDAO.findBoothResultsForBoothsAndElectionAndAllParties( boothIdMap.get(localbodyId),electionId,null);
				Map<Long,Long> partyMap1 = null;
				for(Object[] params : partyResult)
				{
					partyMap1 = resultMap1.get(localbodyId);
					if(partyMap1 == null)
					{
						partyMap1 = new HashMap<Long, Long>(0);
						resultMap1.put(localbodyId, partyMap1);
					}
					Long votesEarned = partyMap1.get((Long)params[0]);
					if(votesEarned == null)
						partyMap1.put((Long)params[0], (Long)params[2]);	
					else
						partyMap1.put((Long)params[0], votesEarned+(Long)params[2]);	
				}
				
			}
			}
			if(panchaytIdsList != null && panchaytIdsList.size() > 0)
			{
			  List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchaytIdsList, electionId); 
			  if(list != null && list.size() > 0)
			  {
				  for(Object[] params:list)
				  {
					  List<Long> panchayatIds = boothIds.get((Long)params[1]);
					  if(panchayatIds == null)
					  {
					   panchayatIds = new ArrayList<Long>(0);
					   boothIds.put((Long)params[1], panchayatIds);
					  }
					  if(!panchayatIds.contains((Long)params[0]))
					   panchayatIds.add((Long)params[0]);
				  }
				  
				  List<Object[]> resultList = candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionIdForSelElection(boothIds.keySet(), electionId);
				  if(resultList != null && resultList.size() > 0)
				  {
					 for(Object[] params:resultList)
					 {
					   List<Long> panchayatIdsList = boothIds.get((Long)params[0]);
					   if(panchayatIdsList != null && panchayatIdsList.size() > 0)
					   {
						 Map<Long,Long> partyMap = null;//Map<PartyId,totalvotes>
						 for(Long panchayatId :panchayatIdsList)
						 {
							 partyMap = resultMap.get(panchayatId);
							 if(partyMap == null)
							 {
								 partyMap = new HashMap<Long, Long>(0);
								 resultMap.put(panchayatId, partyMap);
							 }
							 Long votesEarned = partyMap.get((Long)params[1]);
							 if(votesEarned == null)
							  partyMap.put((Long)params[1],(Long)params[2]);
							 else
							  partyMap.put((Long)params[1], votesEarned+(Long)params[2]);
						 }
					   }
					   
					 }
				  }
				  
			  }
			}
			AlliancePartyResultsVO alliancePartiesVO = staticDataService.getAlliancePartiesByElectionAndParty(electionId,872l);
			
			
			
			if(resultMap != null && resultMap.size() > 0)
				getPartyPerformanceForPanchayatNew(resultMap,pancResultList,electionId,alliancePartiesVO,partiesSelected,partyNamesMap,electionTypeMap); 
			if(resultMap1 != null && resultMap1.size() > 0)
				getPartyPerformanceForLocalBodyNew(pancResultList,resultMap1,booths,electionId,alliancePartiesVO,partiesSelected,partyNamesMap,electionTypeMap);
			
			
			}catch (Exception e) {
			
				LOG.error(" Exception Occured in getMandalWisePartyPerformanceReport() method, Exception - ",e);
			  }
		}
	 
	 public void getPartyPerformanceForPanchayatNew(Map<Long,Map<Long,Long>> resultMap,List<PartyPositionResultsVO> resultList,Long electionId,AlliancePartyResultsVO alliancePartiesVO,List<Long> partiesSelected,Map<Long,String> partyNamesMap,Map<Long,String> electionTypeMap)
		{
			try{

			 for(Long id:resultMap.keySet())
			 {
				 Map<Long,Long> partyMap = resultMap.get(id);
				 Long totalVotes = 0L;
				
				 PartyPositionResultsVO locationVO = new PartyPositionResultsVO();
				 
				 for(Long partysId:partyMap.keySet())
				  totalVotes += partyMap.get(partysId); 
				
				 List<Long> partiesDone=new ArrayList<Long>();
				 for (Entry<Long, Long> entry : partyMap.entrySet()){
					 
					 Long partyId=entry.getKey();
					
					 if(partiesSelected.contains(partyId)){
					 List<PartyPositionResultsVO> partiesList=locationVO.getPartiesList();
					 if(partiesList==null){
						 partiesList=new ArrayList<PartyPositionResultsVO>();
					 }
					 
					 
					 

					 PartyPositionResultsVO partyvo=new PartyPositionResultsVO();
					
					
						Long selectedPartyTotal=partyMap.get(partyId);
					 	if(selectedPartyTotal==null){
							 AlliancePartyResultsVO alliances = staticDataService.getAlliancePartiesByElectionAndParty(electionId,partyId);
						 
							 for(SelectOptionVO alianceParty:alliances.getAllianceParties()){
								 if(selectedPartyTotal == null || selectedPartyTotal.longValue() == 0l){
									 selectedPartyTotal = partyMap.get(alianceParty.getId());
								 }
							 }
						 }
					 
					 
					partyvo.setPartyId(partyId);
					partyvo.setPartyName(partyNamesMap.get(partyId));
					partyvo.setVotesEarned(selectedPartyTotal);
					partyvo.setPercentage(new BigDecimal((partyvo.getVotesEarned()*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					
					partiesList.add(partyvo);
					
					partiesDone.add(partyId);
					locationVO.setPartiesList(partiesList);
					 }
					 
					 
					
					 }
				 
				 //THOSE WHICH ARE NOT PARTICIPATED BECAUSE OF ALLIANCE
				  Collection<Long> similar = new HashSet<Long>( partiesSelected );
		          Collection<Long> different = new HashSet<Long>();
		          different.addAll( partiesSelected );
		          different.addAll( partiesDone );

		          similar.retainAll( partiesDone );
		          different.removeAll( similar );
				 
		          
				 for(Long parId:different){
					 
					 List<PartyPositionResultsVO> partiesList=locationVO.getPartiesList();
					 if(partiesList==null){
						 partiesList=new ArrayList<PartyPositionResultsVO>();
					 }
					 
					 PartyPositionResultsVO partyvo=new PartyPositionResultsVO();
					 
					 Long selectedPartyTotal=partyMap.get(parId);
					 
					 	if(selectedPartyTotal==null){
							 AlliancePartyResultsVO alliances = staticDataService.getAlliancePartiesByElectionAndParty(electionId,parId);
							 StringBuilder st=new StringBuilder();
							 if(alliances!=null){
							 for(SelectOptionVO alianceParty:alliances.getAllianceParties()){
								 
								 if(selectedPartyTotal == null || selectedPartyTotal.longValue() == 0l){
									 selectedPartyTotal = partyMap.get(alianceParty.getId());
									 st.append(partyNamesMap.get(alianceParty.getId()));
									 st.append(",");
								 }
								 
								 if(electionTypeMap.get(electionId).equalsIgnoreCase("CURRENT")){
									 partyvo.setCurrentAlianced(true);
								 }else{
									 partyvo.setPastAlianced(true);
								 }
								 
							 }
							 }
							 st.append(partyNamesMap.get(parId));
							 
							 partyvo.setPartyName(st.toString());
						 }
					 	
					 	partyvo.setPartyId(parId);
						partyvo.setVotesEarned(selectedPartyTotal);
						
						if(partyvo.getVotesEarned()!=null){
							partyvo.setPercentage(new BigDecimal((partyvo.getVotesEarned()*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						}
						partiesList.add(partyvo);
						
						locationVO.setPartiesList(partiesList);
				 }
				 
				 
				 
				 String locationName = "";
				 locationName = panchayatDAO.getPanchayatNameById(id); 
				 
				
				 
		    	 locationVO.setId(id);
		    	 locationVO.setName(locationName != null?locationName:" ");
		    	 locationVO.setTotalValidVotes(totalVotes);
		    	 locationVO.setType("Panchayat");
		    	 resultList.add(locationVO);
		   } 
			
			}catch (Exception e) {
			
			 LOG.error(" Exception Occured in getPartyPerformanceForPanchayatNew() method, Exception - ",e);
			}
		}
	 
	 		public static Comparator<PartyPositionResultsVO> sortPartiesById = new Comparator<PartyPositionResultsVO>()
				{
							  
						  public int compare(PartyPositionResultsVO vo1, PartyPositionResultsVO vo2)
							{
							   return (int) (new Long(vo1.getPartyId()) - (new Long(vo2.getPartyId())));
							}
				};
				
				private static String removeLastChar(String str) {
			        return str.substring(0,str.length()-1);
			    }
	 
	  
	  public void getPartyPerformanceForLocalBodyNew(List<PartyPositionResultsVO> pancResultList,Map<Long,Map<Long,Long>> resultMap1,List<Long> localbodyboothIds,Long electionId,AlliancePartyResultsVO alliancePartiesVO,List<Long> partiesSelected,Map<Long,String> partyNamesMap,Map<Long,String> electionTypeMap)
		 {
			 try{
				 Long localbodytotalVoters = 0l;
				 if(localbodyboothIds != null && localbodyboothIds.size() > 0)
			     localbodytotalVoters = boothDAO.getTotalaVotesByBoothIds(localbodyboothIds).get(0);	
				 if(resultMap1 != null)
				 {
				 for(Long localbodyId : resultMap1.keySet())
				 {
					 	String localbodyName = localElectionBodyDAO.getLocalElectionBodyName1(localbodyId);
						Map<Long,Long> partyMap1 = resultMap1.get(localbodyId);
						Long totalVotes = 0L;
							 
						for(Long partysId:partyMap1.keySet())
						  totalVotes += partyMap1.get(partysId); 
						
						List<Long> partiesDone=new ArrayList<Long>();
						
						PartyPositionResultsVO locationVO = new PartyPositionResultsVO();
							 
						for (Entry<Long, Long> entry : partyMap1.entrySet()){
							 
							 Long partyId=entry.getKey();
							 
							 if(partiesSelected.contains(partyId)){
							 List<PartyPositionResultsVO> partiesList=locationVO.getPartiesList();
							 if(partiesList==null){
								 partiesList=new ArrayList<PartyPositionResultsVO>();
							 }
							 
							 
							 
							 Long selectedPartyTotal=null;
							 PartyPositionResultsVO partyvo=new PartyPositionResultsVO();
							
							
								selectedPartyTotal=partyMap1.get(partyId);
							 	if(selectedPartyTotal==null){
									 AlliancePartyResultsVO alliances = staticDataService.getAlliancePartiesByElectionAndParty(electionId,partyId);
								 
									 for(SelectOptionVO alianceParty:alliances.getAllianceParties()){
										 if(selectedPartyTotal == null || selectedPartyTotal.longValue() == 0l){
											 selectedPartyTotal = partyMap1.get(alianceParty.getId());
										 }
									 }
								 }
							 
							 
							partyvo.setPartyId(partyId);
							partyvo.setPartyName(partyNamesMap.get(partyId));
							partyvo.setVotesEarned(selectedPartyTotal);
							partyvo.setPercentage(new BigDecimal((partyvo.getVotesEarned()*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							
							partiesDone.add(partyId);
							
							partiesList.add(partyvo);
							locationVO.setPartiesList(partiesList);
							 }
						 }
						
						//THOSE WHICH ARE NOT PARTICIPATED BECAUSE OF ALLIANCE
						  Collection<Long> similar = new HashSet<Long>( partiesSelected );
				          Collection<Long> different = new HashSet<Long>();
				          different.addAll( partiesSelected );
				          different.addAll( partiesDone );

				          similar.retainAll( partiesDone );
				          different.removeAll( similar );
						 
				          
						 for(Long parId:different){
							 
							 List<PartyPositionResultsVO> partiesList=locationVO.getPartiesList();
							 if(partiesList==null){
								 partiesList=new ArrayList<PartyPositionResultsVO>();
							 }
							 
							 PartyPositionResultsVO partyvo=new PartyPositionResultsVO();
							 
							 Long selectedPartyTotal=partyMap1.get(parId);
							 
							 	if(selectedPartyTotal==null){
									 AlliancePartyResultsVO alliances = staticDataService.getAlliancePartiesByElectionAndParty(electionId,parId);
									 StringBuilder st=new StringBuilder();
									 if(alliances!=null){
									 for(SelectOptionVO alianceParty:alliances.getAllianceParties()){
										 if(selectedPartyTotal == null || selectedPartyTotal.longValue() == 0l){
											 selectedPartyTotal = partyMap1.get(alianceParty.getId());
											 st.append(partyNamesMap.get(alianceParty.getId()));
											 st.append(",");
										 }
										 if(electionTypeMap.get(electionId).equalsIgnoreCase("CURRENT")){
											 partyvo.setCurrentAlianced(true);
										 }else{
											 partyvo.setPastAlianced(true);
										 }
									 }
									 }
									 st.append(partyNamesMap.get(parId));
									 
									 partyvo.setPartyName(st.toString());
								 }
							 	
							 	partyvo.setPartyId(parId);
								partyvo.setVotesEarned(selectedPartyTotal);
								if(partyvo.getVotesEarned()!=null){
									partyvo.setPercentage(new BigDecimal((partyvo.getVotesEarned()*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
								}
								partiesList.add(partyvo);
								
								locationVO.setPartiesList(partiesList);
						 }
						 
						 
						
						
				    		 locationVO.setId(localbodyId);
				    		 locationVO.setName(localbodyName);
				    		// locationVO.setPartyPercentage(selectedPartyTotalPercent);
				    		// locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
				    		 locationVO.setTotalValidVotes(totalVotes);
				    		 locationVO.setTotalVoters(localbodytotalVoters);
				    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/localbodytotalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				    		// locationVO.setMargin(difference);
				    		 locationVO.setType("Municipality");
				    		// locationVO.setPrp(prpperc);
				    		 pancResultList.add(locationVO);

				 }
				 } 
			 }
			 catch(Exception e)
			 {
				 LOG.error(" Exception Occured in getPartyPerformanceForLocalBodyNew() method StratagicReportService class, Exception - ",e);
			 }
		 }
	  
	  
	  //METHOD TO GET SUBLEVEL VOTER MODIFICATION REPORT TOTAL ADDED DELETED VOTERS GENDER WISE -- START
	  //WITH SUPPORTING METHODS FROM VOTER MODIFICATION SERVICE
	  public VoterModificationVO getSubLevelsVoterModificationDetailsByLocationValue(
				String locationType, Long locationValue, Long constituencyId,
				Long fromPublicationDateId, Long toPublicationDateId)	 {
			 VoterModificationVO voterModificationVO = new VoterModificationVO();
			 LOG.debug("Entered into getSubLevelsVoterModificationDetailsByLocationValue() Method");
			 try{
				 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
				 List<Long> pubIdsListForTotVoters = new ArrayList<Long>(0);
				 
				 if(fromPublicationDateId != null && fromPublicationDateId > 0 && !pubIdsListForTotVoters.equals(fromPublicationDateId))
					 pubIdsListForTotVoters.add(fromPublicationDateId);
				 if(toPublicationDateId != null && toPublicationDateId > 0 && !pubIdsListForTotVoters.equals(toPublicationDateId))
					 pubIdsListForTotVoters.add(toPublicationDateId);
				 
				 List<SelectOptionVO> locationValuesList = new ArrayList<SelectOptionVO>(0);
				 
				 if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
				 {
					 List<SelectOptionVO> mandalsList = regionServiceData.getSubRegionsInConstituency(constituencyId,IConstants.PRESENT_YEAR, null);
					 if(mandalsList == null || mandalsList.size() == 0)
						  return null;
					 
					 List<Long> mandalIdsList = new ArrayList<Long>(0);
					 List<Long> localEleBodyIdsList = new ArrayList<Long>(0);
					 
					  for(SelectOptionVO selectOptionVO : mandalsList)
					  {
						  if(selectOptionVO.getId().toString().substring(0,1).equalsIgnoreCase(IConstants.RURAL_TYPE))
							  mandalIdsList.add(new Long(selectOptionVO.getId().toString().substring(1)));
						  else
							  localEleBodyIdsList.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(selectOptionVO.getId().toString().substring(1))).get(0));
					  }
					  
					  if(mandalIdsList != null && mandalIdsList.size() > 0)
						 locationValuesList.add(new SelectOptionVO(mandalIdsList, "mandal"));
					  
					  if(localEleBodyIdsList != null && localEleBodyIdsList.size() > 0)
						locationValuesList.add(new SelectOptionVO(localEleBodyIdsList, IConstants.LOCALELECTIONBODY));
					
				 }
				 
				 if(locationValuesList != null && locationValuesList.size() > 0)
					 voterModificationVO = getVoterModificationSubLevelsData(locationValuesList, constituencyId, publicationIdsList,pubIdsListForTotVoters);
					  
				
				
			 }catch (Exception e) {
				 LOG.error("Exception Occured in getSubLevelsVoterModificationDetailsByLocationValue() Method");
				 LOG.error("Exception is - ",e);
			}
			 voterModificationVO.setMainHeading("Locality");
			 return voterModificationVO;
		 }
	  
	  public VoterModificationVO getVoterModificationSubLevelsData(List<SelectOptionVO> selectOptionVOList, Long constituencyId, List<Long> publicationIdsList,List<Long> pubIdsListForTotVoters)
		 {
			 VoterModificationVO voterModificationVO = new VoterModificationVO();
			 List<VoterModificationVO> voterModificationVOsList = new ArrayList<VoterModificationVO>(0);
			 try{
				 
				 if(selectOptionVOList != null && selectOptionVOList.size() > 0)
				 {
					 for(SelectOptionVO optionVO : selectOptionVOList)
						 getVoterModificationDataByPublicationDateList(optionVO, constituencyId, publicationIdsList, voterModificationVOsList,pubIdsListForTotVoters);
						 
				 }
				 voterModificationVO.setModifiedVotersList(voterModificationVOsList);
				 return voterModificationVO;
				 
			 }catch (Exception e) {

				 LOG.error("Exception Occured in getVoterModificationSubLevelsData() Method, Exception - ",e);
				 return voterModificationVO;
			}
		 }
	  
	  public List<Long> getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(Long fromPublicationDateId,Long toPublicationDateId)
		 {
			 LOG.debug("Entered into getVoterPublicationIdsBetweenTwoPublicationsForVotersModification() Method");
			 List<Long> publicationIdsList = new ArrayList<Long>(0);
			 try{
				 if(fromPublicationDateId == null || fromPublicationDateId == 0 || fromPublicationDateId.equals(toPublicationDateId))
					 publicationIdsList.add(toPublicationDateId);
				 else
				 {
					 publicationIdsList = getVoterPublicationIdsBetweenTwoPublications(fromPublicationDateId,toPublicationDateId);
					 publicationIdsList.remove(fromPublicationDateId);
				 }
				 return publicationIdsList;
			 }catch (Exception e) {
				 LOG.error("Exception Occured in getVoterPublicationIdsBetweenTwoPublicationsForVotersModification() Method");
				 LOG.error("Exception is - ",e);
				 return publicationIdsList; 
			 }
		 }
	  
	  public void getVoterModificationDataByPublicationDateList(SelectOptionVO optionVO, Long constituencyId,List<Long> publicationIdsList, List<VoterModificationVO> voterModificationVOsList, List<Long> pubIdsListForTotVoters)
		 {
			 try{
				 if(optionVO != null)
				 {
					 List<Object[]> list  = null;
					 
					 if(optionVO.getType() != null && !optionVO.getType().equalsIgnoreCase(IConstants.BOOTH))
					   list = voterModificationInfoDAO.getVoterModificationGenderDetailsByLocationValuesList(optionVO.getLocationValuesList(), publicationIdsList, constituencyId, votersAnalysisService.getReportLevelId(optionVO.getType()));
					 if(list != null && list.size() > 0)
						 getVotermodificationDetailsFromVoterModifInfoTable(list, voterModificationVOsList,optionVO,publicationIdsList,constituencyId,pubIdsListForTotVoters);
					 else
					 {
						 String queryString = getVoterModificationSublevelQueryString(optionVO.getType());
						 list = voterModificationDAO.getSublevelVoterModificationDetailsByLocationValues(constituencyId, publicationIdsList, optionVO.getLocationValuesList(), optionVO.getType(), queryString);
						 getVotermodificationDetailsFromVoterModificationTable(list, voterModificationVOsList, optionVO,pubIdsListForTotVoters,constituencyId);
					 }
						
					 
				 }
				 
			 }catch (Exception e) {

				 LOG.error("Exception Occured in getVoterModificationDataByPublicationDateList() Method, Exception - ",e);
				 
			}
		 }
	  
	  public List<Long> getVoterPublicationIdsBetweenTwoPublications(Long fromPublicationDateId,Long toPublicationDateId) 
		 {
			 LOG.debug("Entered into getVoterPublicationIdsBetweenTwoPublications() Method");
			 try{
				 return voterInfoDAO.getVoterPublicationIdsBetweenTwoPublications(fromPublicationDateId, toPublicationDateId);
			 }catch (Exception e) {
				 LOG.error("Exception Occured in getVoterPublicationIdsBetweenTwoPublications() Method");
				 LOG.error("Exception is - ",e);
				 return null;
			 }
		 }
	  
	  public List<SelectOptionVO> getSearchTypeDetails(Long userId,String searchtype,Long cosntituencyId){
			LOG.info("Entered into the setValuesToCensusVO method StratagicReportService class");
			List<SelectOptionVO> returnList = null;
			try
			{
				if(searchtype.equalsIgnoreCase("basedOnElection")){
									
				}
				else if(searchtype.equalsIgnoreCase("basedOnPublication")){
					
				}
			}catch(Exception e)
			{
			LOG.error("Exception Occured in the setValuesToCensusVO method StratagicReportService class",e);
				
			}
			return returnList;
		}
	  
	  public ResultStatus mergePanchayatsToOnePanchayat(final Long userId,final  String searchtype,final Long searchTypeValue,final Long cosntituencyId,final Long panchayatId,final List<Long> mergedPanchyatsIds){
		  LOG.info("Entered into the mergePanchayatsToOnePanchayat method StratagicReportService class");
			ResultStatus status =  new ResultStatus();;
			try
			{
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						
						StrategyMergePanchayat strategyMergePanchayat = new StrategyMergePanchayat();
						
						strategyMergePanchayat.setType(searchtype);
						strategyMergePanchayat.setTypeValue(searchTypeValue);
						strategyMergePanchayat.setPanchayat(panchayatDAO.get(panchayatId));
						strategyMergePanchayat.setConstituency(constituencyDAO.get(cosntituencyId));
						
						strategyMergePanchayat = strategyMergePanchayatDAO.save(strategyMergePanchayat);
						
						if(mergedPanchyatsIds != null && mergedPanchyatsIds.size()>0){
							for (Long mergePanchyatId : mergedPanchyatsIds) {
								
								StrategyMergPancList strategyMergPancList = new StrategyMergPancList();									
								
								strategyMergPancList.setStrategyMergePanchayat(strategyMergePanchayat);
								strategyMergPancList.setPanchayat(panchayatDAO.get(mergePanchyatId));
								
								strategyMergPancListDAO.save(strategyMergPancList);
							}
						}
					}
				});
				

				status.setResultCode(0);
			}catch(Exception e)
			{
				status.setResultCode(1);
				LOG.error("Exception Occured in the mergePanchayatsToOnePanchayat method StratagicReportService class",e);
			}
			return status;
	  }
	  
	  public List<SelectOptionVO> getElectionIdsAndYearsByCosntutuencyId(Long electionScopeId,Long constituencyId) {
			LOG.info(" entered into getElectionIdsAndYearsByCosntutuencyId() of StratagicReportService class.");
			List<Long> mandalIds;
			List<SelectOptionVO> reteurnList = null;
			try {
				 Constituency constituency = constituencyDAO.get(constituencyId);
				 String constiType = constituency.getAreaType();
				 
				 Long publicationId = voterInfoDAO.getLatestPublicationDate(constituencyId);
				 List<Long> electionIds=new ArrayList<Long>();
				 if(constiType.equalsIgnoreCase("rural")||constiType.equalsIgnoreCase("rural-urban")){
					mandalIds = boothDAO.getTehsildByConstituency(constituencyId, publicationId);
					electionIds = boothConstituencyElectionDAO.getElectionsByMandals(mandalIds,null,electionScopeId);
				 }else{
					electionIds = boothConstituencyElectionDAO.getElectionsByUrbanConsti(constituencyId,electionScopeId);
				 }			 
					List<Object[]> electionYearsList = nominationDAO.getElectionyearsByElectionIds(electionIds);
					if(electionYearsList != null && electionYearsList.size() > 0)
					{
						reteurnList = new ArrayList<SelectOptionVO>();
						for (Object[] parms : electionYearsList) {
							SelectOptionVO selectOptionVO = new SelectOptionVO();
							selectOptionVO.setId((Long) parms[0]);
							selectOptionVO.setName( parms[1].toString()+"("+parms[2].toString()+")");
							reteurnList.add(selectOptionVO);
						}
					}
							 
			
			} catch (Exception e) {
				LOG.error(" exception occured  in getElectionIdsAndYearsByCosntutuencyId() of StratagicReportService class.",e);
			}
			
			return reteurnList;
		}
	  
	  public List<SelectOptionVO> getPanchayatDetailsForElectionInCosntituency(Long userId,Long constituencyId,Long elctionId){
			LOG.info(" entered into getElectionIdsAndYearsByCosntutuencyId() of StratagicReportService class.");
			List<SelectOptionVO> panchayatList = null;
			try {
				
				List<Object[]> panchayatDetils = hamletBoothElectionDAO.getPanchayatDetailsByConstituencyId(constituencyId, elctionId);

				if(panchayatDetils != null && panchayatDetils.size()>0){
					panchayatList = new ArrayList<SelectOptionVO>();
					for (Object[] parms : panchayatDetils) {
						
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long) parms[0]);
						selectOptionVO.setName( parms[1].toString());
						panchayatList.add(selectOptionVO);
					}
				}
				
				
			} catch (Exception e) {
				LOG.error(" exception occured  in getElectionIdsAndYearsByCosntutuencyId() of StratagicReportService class.",e);
			}	
			return panchayatList;
		}
	  
	  public void getVotermodificationDetailsFromVoterModifInfoTable(List<Object[]> voterModifDetails, List<VoterModificationVO> voterModificationVOs, SelectOptionVO optionVO, List<Long> publicationIdsList, Long constituencyId, List<Long> pubIdsListForTotVoters)
		 {
			 try{
				 
				 if(voterModifDetails != null && voterModifDetails.size() > 0)
				 {
					 List<Long> locationIds = optionVO.getLocationValuesList();
					 
					 for(Long id : locationIds)
					 {
						 VoterModificationVO modificationVO = new VoterModificationVO();
						 for(Object[] params : voterModifDetails)
						 {
							if(id.equals(params[4]))
							{
							 if(params[3] != null && params[3].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
							 {
								 modificationVO.setMaleVotersAdded((Long)params[1]);
								 modificationVO.setFemaleVotersAdded((Long)params[2]);
								 modificationVO.setAddedCount((Long)params[0]);
							 }
							 else if(params[3] != null && params[3].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
							 {
								 modificationVO.setMaleVotersDeleted((Long)params[1]);
								 modificationVO.setFemaleVotersDeleted((Long)params[2]);
								 modificationVO.setDeletedCount((Long)params[0]);
							 }
							 
							 if(modificationVO.getId() == null)
							 {
								   modificationVO.setId((Long)params[4]);
								    if(optionVO.getType().equalsIgnoreCase(IConstants.CONSTITUENCY))
								    {
									  modificationVO.setName(constituencyDAO.get((Long)params[4]).getName());
									  modificationVO.setLocationType("constituency");
								    }
								    else if(optionVO.getType().equalsIgnoreCase("mandal"))
								    {
								      modificationVO.setLocationType("mandal");
									  modificationVO.setName(tehsilDAO.get((Long)params[4]).getTehsilName());
								    }
									else if(optionVO.getType().equalsIgnoreCase("panchayat"))
									{
									  modificationVO.setLocationType("panchayat");	
									  modificationVO.setName(panchayatDAO.get((Long)params[4]).getPanchayatName());
									}
									else if(optionVO.getType().equalsIgnoreCase("ward"))
									{
									  modificationVO.setLocationType("ward");
									  modificationVO.setName(constituencyDAO.get((Long)params[4]).getName());
									}
									else if(optionVO.getType().equalsIgnoreCase("booth"))
									{
									  modificationVO.setLocationType("booth");
									  modificationVO.setName(boothDAO.get((Long)params[4]).getPartNo());
									}
									else if(optionVO.getType().equalsIgnoreCase("localElectionBody") || optionVO.getType().equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
									{
										modificationVO.setLocationType("localElectionBody");
										LocalElectionBody localElectionBody = localElectionBodyDAO.get((Long)params[4]);
										modificationVO.setName(localElectionBody.getName()+" "+localElectionBody.getElectionType().getElectionType());
									} 
								
							 }
							
							 modificationVO.setSelectOptionVOsList(getTotalVotersByPublicationIdsList(pubIdsListForTotVoters, votersAnalysisService.getReportLevelId(optionVO.getType()), (Long)params[4], constituencyId));
							 
						   }
						 }
						 voterModificationVOs.add(modificationVO);
					 }
					 
				 }
				 
			 }catch (Exception e) {

				 LOG.error("Exception Occured in getVotermodificationDetailsFromVoterModifInfoTable() Method, Exception - ",e);
			}
		 }
	  public void getVotermodificationDetailsFromVoterModificationTable(List<Object[]> voterModifDetails, List<VoterModificationVO> voterModificationVOsList, SelectOptionVO optionVO, List<Long> pubIdsListForTotVoters,Long constituencyId)
		 {
			 try{
				 
				 if(voterModifDetails != null && voterModifDetails.size() > 0)
				 {
					 List<String> locationNamesList = new ArrayList<String>(0);
					 
					for(Object[] params: voterModifDetails)
					{
						if(!locationNamesList.contains(params[4]))
							locationNamesList.add(params[4].toString());
					}
					Collections.sort(locationNamesList);
					
					for(String locationName : locationNamesList)
					{
						VoterModificationVO modificationVO = new VoterModificationVO();
						
						for(Object[] params : voterModifDetails)
						 {
							if(locationName.equalsIgnoreCase(params[4].toString()))
							{
								 if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
								 {
									 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
										 modificationVO.setMaleVotersAdded((Long)params[0]);
									 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
										 modificationVO.setFemaleVotersAdded((Long)params[0]);
									 
									 modificationVO.setAddedCount(modificationVO.getMaleVotersAdded()+modificationVO.getFemaleVotersAdded());
								 
								 }
								 if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
								 {
									 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
										 modificationVO.setMaleVotersDeleted((Long)params[0]);
									 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
										 modificationVO.setFemaleVotersDeleted((Long)params[0]);
									 
									 modificationVO.setDeletedCount(modificationVO.getMaleVotersDeleted()+modificationVO.getFemaleVotersDeleted());
								 }
								 if(optionVO.getType() != null && optionVO.getType().equalsIgnoreCase(IConstants.BOOTH))
								 {
									 if(params[1] != null&& params[1].toString().equalsIgnoreCase(IConstants.STATUS_MOVED))
									 {
										 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
											 modificationVO.setMaleVotersMoved((Long)params[0]);
										 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
											 modificationVO.setFemaleVotersMoved((Long)params[0]);
									 
										 modificationVO.setMovedCount(modificationVO.getMaleVotersMoved()+modificationVO.getFemaleVotersMoved());
									 }
								 
									 if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_RELOCATED))
									 {
										 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
											 modificationVO.setMaleVotersRelocated((Long)params[0]);
										 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
											 modificationVO.setFemaleVotersRelocated((Long)params[0]);
									 
										 modificationVO.setRelocatedCount(modificationVO.getMaleVotersRelocated()+modificationVO.getFemaleVotersRelocated());
									 }
								 }
							 
								 if(optionVO.getType() != null && optionVO.getType().equalsIgnoreCase(IConstants.BOOTH))
								 {
									modificationVO.setPartNo(new Long(params[4].toString())); 
									modificationVO.setName(params[4].toString());
								 }
								 else if(optionVO.getType() != null && (optionVO.getType().equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || optionVO.getType().equalsIgnoreCase("localElectionBody")))
									 modificationVO.setName(params[4].toString()+" "+params[5].toString());
								 else
									 modificationVO.setName(params[4].toString());
								 
								  modificationVO.setLocationType(optionVO.getType());
								 
								 	modificationVO.setId((Long)params[3]);
								 	
								 modificationVO.setSelectOptionVOsList(getTotalVotersByPublicationIdsList(pubIdsListForTotVoters, votersAnalysisService.getReportLevelId(optionVO.getType()), (Long)params[3], constituencyId));
							}
						 }
						
						 voterModificationVOsList.add(modificationVO);
					}
					 
				 }
				 
			 }catch (Exception e) {

				 LOG.error("Exception Occured in getVotermodificationDetailsFromVoterModificationTable() Method, Exception -" ,e);
				 
				 
			}
		 }
	  public String getVoterModificationSublevelQueryString(String locationType)
		 {
			 try{
				 StringBuilder stringBuilder = new StringBuilder();
				 stringBuilder.append(" select count(model.voter.voterId),model.voterStatus.status,model.voter.gender,"); 
				 
				 if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
					 stringBuilder.append(" model2.constituency.constituencyId, model2.constituency.name ");
				 
				 else if(locationType.equalsIgnoreCase(IConstants.MANDAL))
					 stringBuilder.append(" model2.tehsil.tehsilId,model2.tehsil.tehsilName ");
				 
				 else if(locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || locationType.equalsIgnoreCase("localElectionBody"))
					 stringBuilder.append(" model2.localBody.localElectionBodyId,model2.localBody.name,model2.localBody.electionType.electionType ");
				 
				 else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
					 stringBuilder.append(" model2.panchayat.panchayatId,model2.panchayat.panchayatName ");
				 
				 else if(locationType.equalsIgnoreCase(IConstants.BOOTH))
					 stringBuilder.append(" model2.boothId,model2.partNo ");
				 
				 else if(locationType.equalsIgnoreCase(IConstants.WARD))
					 stringBuilder.append(" model2.localBodyWard.constituencyId,model2.localBodyWard.name ");
				
				 return stringBuilder.toString();
				 
			 }catch (Exception e) {

				 LOG.error("Exception Occured in getVoterModificationSublevelQueryString() method, Exception - ",e);
				 return "";
			}
		 }
	  
	  public List<SelectOptionVO> getTotalVotersByPublicationIdsList(List<Long> publicationDateIdsList, Long reportLevelId, Long locationValue, Long constituencyId)
		 {
			 try{
				 
				 List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
				 
				List<Object[]> list = voterInfoDAO.getTotalVotersByPublicationDateIdsList(publicationDateIdsList, reportLevelId, locationValue, constituencyId);
				 if(list != null && list.size() > 0)
					 for(Object[] params : list)
						 selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
				 
				 return selectOptionVOList;
			 }catch (Exception e) {

				 LOG.error("Exception Occured in getTotalVotersByPublicationIdsList() method, Exception - ",e);
				 return null;
			}
		}
	  
	  //METHOD TO GET SUBLEVEL VOTER MODIFICATION REPORT TOTAL ADDED DELETED VOTERS GENDER WISE -- END
	  
	  
	  
	  //METHOD TO GET VOTERS DENSITY PANCHAYAT WITH PARTY'S WON IN THE PANCHAYATS -- SASI -- START
	  public VoterDensityWithPartyVO getVotersCountInPanchayatsForDensity(Long constituencyId,Long publicationId)
		{
			
			VoterDensityWithPartyVO voterDensityPartyVO=new VoterDensityWithPartyVO();
			try {
				LOG.debug("Enterd into voterDensityInPanchayat() method in Suggestive Model Service");
				//Long publicationId = voterInfoDAO.getLatestPublicationDate(constituencyId);
				if(publicationId != null)
				{
					//String constituencyType = constituencyDAO.get(constituencyId).getAreaType();
					List<VoterDensityWithPartyVO> voterCountVOList=createObjectsForVoterCountVO();
					
					List<Object[]> votersCountList = voterInfoDAO.getVoterCountInPanchayatLevel(constituencyId,publicationId,3l);
					
					List<Long> panchayatIds=new ArrayList<Long>();
					List<Long> ananymousPanchayatIds=new ArrayList<Long>();
					
					if(votersCountList != null && votersCountList.size() > 0)
					{
						for (Object[] parms : votersCountList) {
							
							for (int i = 0; i < voterCountVOList.size(); i++) {
								if((Long)parms[1] >= voterCountVOList.get(i).getMinValue() && (Long)parms[1] <= voterCountVOList.get(i).getMaxValue())
								{
									Long count = voterCountVOList.get(i).getCount();
									
									List<Long> pids=voterCountVOList.get(i).getPanchayatIds();
									if(pids==null){
										pids=new ArrayList<Long>();
									}
									count = count + 1;
									voterCountVOList.get(i).setCount(count);
									pids.add(Long.valueOf(parms[0].toString()));
									
									panchayatIds.add(Long.valueOf(parms[0].toString()));
									
									voterCountVOList.get(i).setPanchayatIds(pids);
									break;
								}
							}
						}
						
					}
					
					
					List<Long> panchytsResulted=new ArrayList<Long>();
					if(panchayatIds.size()>0){
						List<Object[]> panchayatResults=panchayatResultDAO.getPartyWiseWonInPanchayts(panchayatIds);
						if(panchayatResults.size()>0){
						for(Object[] ob:panchayatResults){
								if(panchayatIds.contains(Long.valueOf(ob[2].toString()))){
									List<VoterDensityWithPartyVO> partyList=voterDensityPartyVO.getPartyWiseList();
									if(partyList==null){
										partyList=new ArrayList<VoterDensityWithPartyVO>();
										voterDensityPartyVO.setPartyWiseList(partyList);
									}
									VoterDensityWithPartyVO partyvo=getMatchedPartyForDensityVO(partyList,Long.valueOf(ob[0].toString()));
									if(partyvo==null){
										partyvo=new VoterDensityWithPartyVO();
										partyList.add(partyvo);
									}
									List<VoterDensityWithPartyVO> densityList=partyvo.getDensityList();
									if(densityList==null){
										densityList=createObjectsForVoterCountVO();
										partyvo.setDensityList(densityList);
									}
									partyvo.setPartyId(Long.valueOf(ob[0].toString()));
									partyvo.setPartyName(ob[1].toString());
									
									List<Long> panchList=partyvo.getPanchayatIds();
									if(panchList==null){
										panchList=new ArrayList<Long>();
									}
									panchList.add(Long.valueOf(ob[2].toString()));
									
									panchytsResulted.add(Long.valueOf(ob[2].toString()));
									
									partyvo.setPanchayatIds(panchList);
								}
						}
						ananymousPanchayatIds=panchayatIds;
						ananymousPanchayatIds.removeAll(panchytsResulted);
						voterDensityPartyVO.setAnanymousPanchayatsIds(ananymousPanchayatIds);
						}
						
						
					}
					
					
					if(ananymousPanchayatIds.size()>0){
						List<Object[]> ananymousPanchayats=panchayatDAO.getPanchayatsByPanchayatIdsList(ananymousPanchayatIds);
						if(ananymousPanchayats.size()>0){
							StringBuilder sb = new StringBuilder();
							for (Object[] obj : ananymousPanchayats) {
							    if (sb.length() != 0) {
							        sb.append(", ");
							    }							   
							    sb.append(obj[1].toString().substring(0, 1).toUpperCase() + obj[1].toString().substring(1).toLowerCase());
				    		}
							
							voterDensityPartyVO.setAnanymousPanchayats(sb.toString());
							voterDensityPartyVO.setInformation2("Ananymous Panchayaths are "+sb.toString());
						}
					}
					
					
					
					voterDensityPartyVO.setDensityList(voterCountVOList);
					
					voterDensityPartyVO.setAnanymousDensity(createObjectsForVoterCountVO());
					
					if(ananymousPanchayatIds.size()>0){
					for(Long ananymousId:ananymousPanchayatIds){
						String densityType=getDensityTypeOfPanchayat(voterDensityPartyVO,ananymousId);
						if(densityType!=null){
							voterDensityPartyVO.setAnanymousDensity(addPanchayatToPartyDensityList(densityType,ananymousId,voterDensityPartyVO.getAnanymousDensity()));
						}
					}
					}
					
					if(voterDensityPartyVO.getPartyWiseList()!=null){
					for(VoterDensityWithPartyVO param:voterDensityPartyVO.getPartyWiseList()){
						List<VoterDensityWithPartyVO> partyDensitiesList=param.getDensityList();
						if(param.getPanchayatIds()!=null){
						for(Long panchayatId:param.getPanchayatIds()){
							String densityType=getDensityTypeOfPanchayat(voterDensityPartyVO,panchayatId);
							if(densityType!=null){
								partyDensitiesList=addPanchayatToPartyDensityList(densityType,panchayatId,partyDensitiesList);
							}
						}
						}
					}
					}
				}
				
			} catch (Exception e) {
				LOG.error("Exception raised in voterDensityInPanchayat() method in Suggestive Model Service",e);
			}
			voterDensityPartyVO.setInformation("A categorization that provides you with insight on the density of votes and similar number of panchayaths in your constituency helping in creating a common strategy");
			return voterDensityPartyVO;
		}
	  
	  public String getDensityTypeOfPanchayat(VoterDensityWithPartyVO voterDensityPartyVO,Long panchayatId){
		  List<VoterDensityWithPartyVO> densitiesList=voterDensityPartyVO.getDensityList();
		  for(VoterDensityWithPartyVO density:densitiesList){
			  if(density.getPanchayatIds().contains(panchayatId.longValue())){
				  return density.getType();
			  }
		  }
		  return null;
	  }
	  public List<VoterDensityWithPartyVO> addPanchayatToPartyDensityList(String densityType,Long panchayatId,List<VoterDensityWithPartyVO> partyDensistyList){
		  for(VoterDensityWithPartyVO param:partyDensistyList){
			  if(param.getType().equalsIgnoreCase(densityType)){
				  List<Long> panchayatIds=param.getPartyIds();
				  if(panchayatIds==null){
					  panchayatIds=new ArrayList<Long>();
					  param.setPartyIds(panchayatIds);
				  }
				  panchayatIds.add(panchayatId);
				  param.setCount(param.getCount()!=null?param.getCount()+1:0l);
				  return partyDensistyList;
			  }
		  }
		  return partyDensistyList;
	  }
	  
	  public VoterDensityWithPartyVO getMatchedPartyForDensityVO(List<VoterDensityWithPartyVO> partyList,Long partyId){
		  for(VoterDensityWithPartyVO partyvo:partyList){
			  if(partyvo.getPartyId().longValue()==partyId.longValue()){
				  return partyvo;
			  }
		  }
		  return null;
	  }
	  
	  
	  public List<VoterDensityWithPartyVO> createObjectsForVoterCountVO()
		{
		  List<VoterDensityWithPartyVO> voterCountVOList=new ArrayList<VoterDensityWithPartyVO>();
		  
		  VoterDensityWithPartyVO voterCountVO1 = new VoterDensityWithPartyVO();
			voterCountVO1.setMinValue(0l);
			voterCountVO1.setMaxValue(500l);
			voterCountVO1.setType("Below 500");
			voterCountVOList.add(voterCountVO1);
			
			VoterDensityWithPartyVO voterCountVO2 = new VoterDensityWithPartyVO();
			voterCountVO2.setMinValue(501l);
			voterCountVO2.setMaxValue(1000l);
			voterCountVO2.setType("501-1000");
			voterCountVOList.add(voterCountVO2);
			
			VoterDensityWithPartyVO voterCountVO3 = new VoterDensityWithPartyVO();
			voterCountVO3.setMinValue(1001l);
			voterCountVO3.setMaxValue(1500l);
			voterCountVO3.setType("1001-1500");
			voterCountVOList.add(voterCountVO3);
			
			VoterDensityWithPartyVO voterCountVO4 = new VoterDensityWithPartyVO();
			voterCountVO4.setMinValue(1501l);
			voterCountVO4.setMaxValue(2000l);
			voterCountVO4.setType("1501-2000");
			voterCountVOList.add(voterCountVO4);
			
			VoterDensityWithPartyVO voterCountVO5 = new VoterDensityWithPartyVO();
			voterCountVO5.setMinValue(2001l);
			voterCountVO5.setMaxValue(2500l);
			voterCountVO5.setType("2001-2500");
			voterCountVOList.add(voterCountVO5);
			
			VoterDensityWithPartyVO voterCountVO6 = new VoterDensityWithPartyVO();
			voterCountVO6.setMinValue(2501l);
			voterCountVO6.setMaxValue(3000l);
			voterCountVO6.setType("2501-3000");
			voterCountVOList.add(voterCountVO6);
			
			VoterDensityWithPartyVO voterCountVO7 = new VoterDensityWithPartyVO();
			voterCountVO7.setMinValue(3001l);
			voterCountVO7.setMaxValue(4000l);
			voterCountVO7.setType("3001-4000");
			voterCountVOList.add(voterCountVO7);
			
			VoterDensityWithPartyVO voterCountVO8 = new VoterDensityWithPartyVO();
			voterCountVO8.setMinValue(4001l);
			voterCountVO8.setMaxValue(5000l);
			voterCountVO8.setType("4001-5000");
			voterCountVOList.add(voterCountVO8);
			
			VoterDensityWithPartyVO voterCountVO9 = new VoterDensityWithPartyVO();
			voterCountVO9.setMinValue(5001l);
			voterCountVO9.setMaxValue(6000l);
			voterCountVO9.setType("5001-6000");
			voterCountVOList.add(voterCountVO9);
			
			VoterDensityWithPartyVO voterCountVO10 = new VoterDensityWithPartyVO();
			voterCountVO10.setMinValue(6001l);
			voterCountVO10.setMaxValue(60000l);
			voterCountVO10.setType("Above 6000");
			voterCountVOList.add(voterCountVO10);
			
			return voterCountVOList;
			
		}
	  
	//METHOD TO GET VOTERS DENSITY PANCHAYAT WITH PARTY'S WON IN THE PANCHAYATS -- SASI -- END
	  
	  
	  /*
	   * 
	   * Methods From VoterModificationReport
	   *
	   * */
	  
	  public PDFHeadingAndReturnVO getVoterInfoByPublicationDateList(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId)
		 {
		  	PDFHeadingAndReturnVO pdfVO=new PDFHeadingAndReturnVO();
			 List<VoterAgeRangeVO> voterAgeRangeVOList = null;
			 try{
				 
				 List<Long> publicationDateIds = getVoterPublicationIdsBetweenTwoPublications(fromPublicationDateId, toPublicationDateId);
				 
				 if(publicationDateIds != null && publicationDateIds.size() > 0)
				 {
					 
					List<Object[]> list = voterInfoDAO.getVoterInfoByPublicationDateIds(votersAnalysisService.getReportLevelId(locationType), locationValue, publicationDateIds);
				   if(list != null && list.size() > 0)
				   {
					   voterAgeRangeVOList = new ArrayList<VoterAgeRangeVO>(0);
					 for(Object[] params : list)
					 {
						 VoterAgeRangeVO voterAgeRangeVO = new VoterAgeRangeVO();
						 voterAgeRangeVO.setTotalVoters((Long)params[0]);
						 voterAgeRangeVO.setMaleVoters((Long)params[1]);
						 voterAgeRangeVO.setFemaleVoters((Long)params[2]);
						 voterAgeRangeVO.setPublicationDate(params[3].toString());
						 voterAgeRangeVOList.add(voterAgeRangeVO);
					 }
				   }
				}
				 pdfVO.setVoterInfoByPublicationList(voterAgeRangeVOList);
				 pdfVO.setMainHeading("Voters Additions & Deletions");
				 return pdfVO;
			 }catch (Exception e) {
				 LOG.error("Exception Occured in getVoterInfoByPublicationDateList() Method, Exception - ",e);
				 return pdfVO;
			}
			 
		 }
	  
	  public PDFHeadingAndReturnVO getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String queryType)
		 {
		  	PDFHeadingAndReturnVO pdfVO=new PDFHeadingAndReturnVO();
			 LOG.debug("Entered into getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications() Method");
			 List<VoterModificationAgeRangeVO> result = new ArrayList<VoterModificationAgeRangeVO>(0);
			 try{
				 List<String> ageRanges = voterAgeRangeDAO.getAllVoterAgeRanges();
				 VoterModificationAgeRangeVO voterModificationAgeRangeVO = null;
				 if("intermediate".equalsIgnoreCase(queryType) && locationType != null && !locationType.equalsIgnoreCase(IConstants.BOOTH)){
					 //getting data from intermediate table
				   result = getVotersAddedDeletedCountAgeWiseInBetwnPublicsFromIntermediateTable(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
				 }
				 
				 //if data not present in intermediate table then calculating by actual process
				 if("main".equalsIgnoreCase(queryType) || result.isEmpty()){
					 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
					 for(String ageRange : ageRanges)
					 {
						 try{
						 voterModificationAgeRangeVO = new VoterModificationAgeRangeVO();
						 voterModificationAgeRangeVO.setRange(ageRange);
						 if(ageRange != null && ageRange.equalsIgnoreCase(IConstants.YOUNGER_VOTERS))
						  ageRange = IConstants.YOUNG_VOTERS_AGE_RANGE;
						 String[] ages = ageRange.split("-");
						 Long ageFrom = new Long(ages[0].trim());
						 Long ageTo = null;
						 if(!ages[1].trim().equalsIgnoreCase("Above"))
							 ageTo = Long.valueOf(ages[1].trim());
							 
						 List<Object[]> list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation(locationType, locationValue, constituencyId, publicationIdsList, ageFrom, ageTo);
						 
						 if(list != null && list.size() > 0)
						 {
							 for(Object[] params :list)
							 {
								 if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
								 	voterModificationAgeRangeVO.setAddedCount((Long)params[0]);
								 else if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
								 	voterModificationAgeRangeVO.setDeletedCount((Long)params[0]);
							 }
						 }
						 result.add(voterModificationAgeRangeVO);
						 }catch (Exception e) {}
					 }
				 }
				 pdfVO.setAgeRangeWiseAddedDeletedList(result);
				 pdfVO.setSubHeading("Age Group ");
				 return pdfVO; 
			 }catch (Exception e) {
				 LOG.error("Exception Occured in getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications() Method");
				 LOG.error("Exception is - ",e);
				 return pdfVO; 
			 }
		 }
	  
	  public List<VoterModificationAgeRangeVO> getVotersAddedDeletedCountAgeWiseInBetwnPublicsFromIntermediateTable(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId)
		 {
			 LOG.debug("Entered into getVotersAddedDeletedCountAgeWiseInBetwnPublicsFromIntermediateTable() Method");
			 Map<Long,VoterModificationAgeRangeVO> mapObjects = new HashMap<Long,VoterModificationAgeRangeVO>();
			 try{
				 List<VoterAgeRange> ageRanges = voterAgeRangeDAO.getAll();
				 Map<Long,String> ageRangeMap = new HashMap<Long,String>();
				 for(VoterAgeRange range:ageRanges){
					 ageRangeMap.put(range.getVoterAgeRangeId(), range.getAgeRange());
				 }
				 String location = locationType;
			
				 VoterModificationAgeRangeVO voterModificationAgeRangeVO = null;
				 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
				 List<Object[]> ageWiseAddedDelVoters = voterModificationAgeInfoDAO.getGenderWiseVoterModificationsBetweenPublications(getReportLevelId(location),locationValue,constituencyId,publicationIdsList,ageRangeMap.keySet());
				 for(Object[] ageRange : ageWiseAddedDelVoters)
				 {
					 try{
					 voterModificationAgeRangeVO = mapObjects.get((Long)ageRange[1]);
					 if(voterModificationAgeRangeVO == null){
						 voterModificationAgeRangeVO = new VoterModificationAgeRangeVO();
						 mapObjects.put((Long)ageRange[1], voterModificationAgeRangeVO);
						 voterModificationAgeRangeVO.setRange(ageRangeMap.get((Long)ageRange[1]));
					 }
					 if(ageRange[2].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
						voterModificationAgeRangeVO.setAddedCount((Long)ageRange[0]);
					 else if(ageRange[2].toString().equalsIgnoreCase(IConstants.STATUS_DELETED));
						voterModificationAgeRangeVO.setDeletedCount((Long)ageRange[0]);
					 }catch (Exception e) {}
				 }
				 return new ArrayList<VoterModificationAgeRangeVO>(mapObjects.values()); 
			 }catch (Exception e) {
				 LOG.error("Exception Occured in getVotersAddedDeletedCountAgeWiseInBetwnPublicsFromIntermediateTable() Method");
				 LOG.error("Exception is - ",e);
				 return new ArrayList<VoterModificationAgeRangeVO>(); 
			 }
			 
		 }
	  
	  public VoterModificationGenderInfoVO getGenderWiseVoterModificationsBetweenPublications(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String queryType)
		 {
			 LOG.debug("Entered into getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications() Method");
			 VoterModificationGenderInfoVO result = new VoterModificationGenderInfoVO();
			 try{
				 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
				 if("intermediate".equalsIgnoreCase(queryType) && locationType!= null && !locationType.equalsIgnoreCase(IConstants.BOOTH)){
					  String location = locationType;
					   // getting sum of all added/deleted male/female voters count from intermediate table for all publications present between selected publications
					  getGendWiseVoterModifsBetwnPublicationsFromIntermediateTable(getReportLevelId(location),locationValue,constituencyId,publicationIdsList,result);
				 }
				  //if data not present in intermediate table then calculating by actual process
				  if("main".equalsIgnoreCase(queryType) || !result.isDataPresent()){
					  List<Object[]> list = voterModificationDAO.getGenderWiseVoterModificationsBetweenPublications(locationType, locationValue, constituencyId, publicationIdsList);
					 
					  if(list != null && list.size() > 0)
					  {
						 for(Object[] params : list)
						 {
							 if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
							 {
								 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
									 result.setAddedMale((Long)params[0]);
								 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
									 result.setAddedFemale((Long)params[0]);
							 }
							 else if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
							 {
								 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
									 result.setDeletedMale((Long)params[0]);
								 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
									 result.setDeletedFemale((Long)params[0]);
							 }
							 
						 }
						 result.setAddedTotal(result.getAddedMale() + result.getAddedFemale());
						 result.setDeletedTotal(result.getDeletedMale() + result.getDeletedFemale());
						 
					  }
			    }
				 return result;
			 }catch (Exception e) {
				 LOG.error("Exception Occured in getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications() Method");
				 LOG.error("Exception is - ",e);
				 return result;
			 }
		 }
	  
	  public void getGendWiseVoterModifsBetwnPublicationsFromIntermediateTable(Long reportLevelId,Long locationValue,Long constituencyId,List<Long> publicationIdsList,VoterModificationGenderInfoVO result){
			 try{
			  List<Object[]> addedDeletedList = voterModificationInfoDAO.getGenderWiseVoterModificationsBetweenPublications(reportLevelId,locationValue,constituencyId,publicationIdsList);
			  if(addedDeletedList != null && !addedDeletedList.isEmpty()){
				  result.setDataPresent(true);
				  for(Object[] addedDeleted:addedDeletedList){
					  if((IConstants.STATUS_ADDED).equalsIgnoreCase(addedDeleted[2].toString())){
						  result.setAddedMale((Long)addedDeleted[0]);
						  result.setAddedFemale((Long)addedDeleted[1]);
					  }else if((IConstants.STATUS_DELETED).equalsIgnoreCase(addedDeleted[2].toString())){
						  result.setDeletedMale((Long)addedDeleted[0]);
						  result.setDeletedFemale((Long)addedDeleted[1]);
					  }
				  }
				  if(result.getAddedMale() != null &&  result.getAddedFemale() != null)
				     result.setAddedTotal(result.getAddedMale() + result.getAddedFemale());
				  if(result.getDeletedMale() != null &&  result.getDeletedFemale() != null)
					 result.setDeletedTotal(result.getDeletedMale() + result.getDeletedFemale());
			  }
			 }catch(Exception e){
				 LOG.error("Exception Occured in getGendWiseVoterModifsBetwnPublicationsFromIntermediateTable() Method",e);
			 }
		 }
	  
	  
	  public Long getReportLevelId(String type)
		 {
			 Long reportLevelId = 0l;
			 try{
				 reportLevelId = voterReportLevelDAO.getReportLevelIdByType(type);
				 return reportLevelId;
			 }catch (Exception e) {
				LOG.error("Exception Occured in getReportLevelId() Method, Exception - ",e);
				return reportLevelId;
			}
		 }
	  
	  public PDFHeadingAndReturnVO getGenderWiseVoterModificationsForEachPublication(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String queryType)
		 {
		  
		  PDFHeadingAndReturnVO pdfVO=new PDFHeadingAndReturnVO();
			 LOG.debug("Entered into getGenderWiseVoterModificationsForEachPublication() Method");
			 List<VoterModificationGenderInfoVO> result = new ArrayList<VoterModificationGenderInfoVO>();
			 try{
				 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
				 if("intermediate".equalsIgnoreCase(queryType) && !locationType.equalsIgnoreCase(IConstants.BOOTH)){
					 String location = locationType;
					  
					 // getting data from intermediate table 
					 getGendWiseVoterModifsForEachPublicationsFromIntermediateTable(getReportLevelId(location),locationValue,constituencyId,publicationIdsList,result);
				 }
				// if data from not present in intermediate table then calculating by normal process
				 if("main".equalsIgnoreCase(queryType) || result.isEmpty()){
					 List<Object[]> list = voterModificationDAO.getGenderWiseVoterModificationsForEachPublication(locationType, locationValue, constituencyId, publicationIdsList);
					 
					 if(list != null && list.size() > 0)
					 {
						 for(Object[] params : list)
						 {
							 VoterModificationGenderInfoVO infoVO = getVoterModificationGenderInfoVOFromResultList((Long)params[0],result);
							 boolean flag = false;
							 
							 if(infoVO == null)
							 {
								 infoVO = new VoterModificationGenderInfoVO();
								 infoVO.setPublicationId((Long)params[0]);
								 infoVO.setPublicationName(params[4].toString());
								 flag = true;
							 }
							 if(params[2].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
							 {
								 if(params[3].toString().equalsIgnoreCase(IConstants.MALE))
									 infoVO.setAddedMale((Long)params[1]);
								 else if(params[3].toString().equalsIgnoreCase(IConstants.FEMALE))
									 infoVO.setAddedFemale((Long)params[1]);
							 }
							 else if(params[2].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
							 {
								 if(params[3].toString().equalsIgnoreCase(IConstants.MALE))
									 infoVO.setDeletedMale((Long)params[1]);
								 else if(params[3].toString().equalsIgnoreCase(IConstants.FEMALE))
									 infoVO.setDeletedFemale((Long)params[1]);
							 }
							 
							 if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
							 {
								 if(params[2].toString().equalsIgnoreCase(IConstants.STATUS_MOVED))
								 {
									 if(params[3].toString().equalsIgnoreCase(IConstants.MALE))
										 infoVO.setMovedMale((Long)params[1]);
									 else if(params[3].toString().equalsIgnoreCase(IConstants.FEMALE))
										 infoVO.setMovedFemale((Long)params[1]);
								 }
								 else if(params[2].toString().equalsIgnoreCase(IConstants.STATUS_RELOCATED))
								 {
									 if(params[3].toString().equalsIgnoreCase(IConstants.MALE))
										 infoVO.setRelocatedMale((Long)params[1]);
									 else if(params[3].toString().equalsIgnoreCase(IConstants.FEMALE))
										 infoVO.setRelocatedFemale((Long)params[1]);
								 }
							 }
							 if(flag)
								 result.add(infoVO); 
						 }
					 }
				 }
				 for(VoterModificationGenderInfoVO infoVO : result)
				 {
					 List<Long>	idsList = getPreviousPublicationIds(infoVO.getPublicationId());
					 if(idsList != null && idsList.size() > 0)
					 {
						 infoVO.setPreviousPublicationId(idsList.get(0));
						 infoVO.setPreviousPublicationName(publicationDateDAO.getNamePublicationDateId(idsList.get(0)));
					 }
					 else
						 infoVO.setPreviousPublicationId(0L);
					 
					 infoVO.setAddedTotal(infoVO.getAddedMale() + infoVO.getAddedFemale());
					 infoVO.setDeletedTotal(infoVO.getDeletedMale() + infoVO.getDeletedFemale());
					 
				 }
				 
				 pdfVO.setGenderWiseVoterModificationInfoForEachPublicationList(result);
				 pdfVO.setMainHeading("Gender Wise Voter Modifications between "+ result.get(0).getPublicationName() +" - " +result.get(0).getPreviousPublicationName());
				 return pdfVO;
			 }catch (Exception e) {
				 LOG.error("Exception Occured in getGenderWiseVoterModificationsForEachPublication() Method");
				 LOG.error("Exception is - ",e);
				 return pdfVO;
			 }
		 }
	  
	  public void getGendWiseVoterModifsForEachPublicationsFromIntermediateTable(Long reportLevelId,Long locationValue,Long constituencyId,List<Long> publicationIdsList,List<VoterModificationGenderInfoVO> resultList){
			try{
			 // getting sum of all added/deleted male/female voters count from intermediate table for each publication wise present between selected publications
			 List<Object[]> addedDeletedList = voterModificationInfoDAO.getGenderWiseVoterModificationsForEachPublication(reportLevelId,locationValue,constituencyId,publicationIdsList);
			 VoterModificationGenderInfoVO result = null;
			 Map<Long,VoterModificationGenderInfoVO> resultMap = new HashMap<Long,VoterModificationGenderInfoVO>();
			 if(addedDeletedList != null && !addedDeletedList.isEmpty()){
				 //populating data to VoterModificationGenderInfoVO
				  for(Object[] addedDeleted:addedDeletedList){
					  result = resultMap.get((Long)addedDeleted[3]);
					  if(result == null){
						  result = new VoterModificationGenderInfoVO();
						  result.setPublicationId((Long)addedDeleted[3]);
						  result.setPublicationName(addedDeleted[4].toString());
						  resultMap.put((Long)addedDeleted[3], result);
					  }
					  if((IConstants.STATUS_ADDED).equalsIgnoreCase(addedDeleted[2].toString())){
						  result.setAddedMale((Long)addedDeleted[0]);
						  result.setAddedFemale((Long)addedDeleted[1]);
					  }else if((IConstants.STATUS_DELETED).equalsIgnoreCase(addedDeleted[2].toString())){
						  result.setDeletedMale((Long)addedDeleted[0]);
						  result.setDeletedFemale((Long)addedDeleted[1]);
					  }
				  }
				  if(!resultMap.isEmpty()){
					   for(VoterModificationGenderInfoVO vo : resultMap.values()){
						   if(vo.getAddedMale() != null &&  vo.getAddedFemale() != null)
						     vo.setAddedTotal(vo.getAddedMale() + vo.getAddedFemale());
						   if(vo.getDeletedMale() != null &&  vo.getDeletedFemale() != null)
						     vo.setDeletedTotal(vo.getDeletedMale() + vo.getDeletedFemale());
					   }
				       resultList.addAll(resultMap.values());
				   }
			  }
			 }catch(Exception e){
				 LOG.error("Exception Occured in getGendWiseVoterModifsForEachPublicationsFromIntermediateTable() Method",e);
			 }
		 }
	  
	  
	  public VoterModificationGenderInfoVO getVoterModificationGenderInfoVOFromResultList(Long publicationDateId,List<VoterModificationGenderInfoVO> resultList)
		 {
			 try{
				 if(resultList != null && resultList.size() > 0)
					 for(VoterModificationGenderInfoVO infoVO : resultList)
						 if(infoVO.getPublicationId().equals(publicationDateId))
							 return infoVO;
				 return null;
			 }catch (Exception e) {
				 LOG.error("Exception Occured in getVoterModificationGenderInfoVOFromResultList() Method",e);

				 return null;
			}
		 }
	  
	  public List<Long> getPreviousPublicationIds(Long publicationDateId)
		 {
			 LOG.debug("Entered into getPreviousPublicationId() Method");
			 try{
				 return voterInfoDAO.getPreviousPublicationIds(publicationDateId);
			 }catch (Exception e) {
				 LOG.error("Exception Occured in getPreviousPublicationId() Method",e);

				 return null; 
			 }
		 }
	  
	  /**
	   * This service is ude for getting the delimation effect for constitueny
	   * @param constituencyId
	   * @param partyId
	   * @return delimationDetails
	   */
	  @SuppressWarnings("unused")
		public DelimitationEffectVO getDelimationEffectOnConstituency(Long constituencyId,Long partyId)
		{
			DelimitationEffectVO delimationDetails = new DelimitationEffectVO();
			try {
				List<DelimitationEffectVO> delimitationEffectList = null;
				List<DelimitationEffectVO> delimitationEffectList1 = null;
				List<DelimitationEffectVO> delimitationEffectList2 = null;
				Map<String, DelimitationEffectVO> delimationEffectMap = new HashMap<String, DelimitationEffectVO>();
				Map<String, DelimitationEffectVO> delimationEffectMap1 = new HashMap<String, DelimitationEffectVO>();
				LOG.debug("Enterd into getDelimationEffectOnConstituency() method in Suggestive Model Service");
				DelimitationEffectVO others = new DelimitationEffectVO();
				String presentElectionYear = IConstants.PRESENT_ELECTION_YEAR;
				String previousElectionYear = IConstants.PREVIOUS_ELECTION_YEAR;
				delimationDetails.setPresentYear(presentElectionYear);
				delimationDetails.setPreviousyear(previousElectionYear);
				Long presentElectionId = electionDAO.getElectionId(presentElectionYear,2l,1l);
				// here we are getting the present election polled and total voter details
				List<Object[]> afterDelimationtotalAndPolledVotesCount = boothResultDAO.getAfterDelimitationEffectBasedOnVoters(presentElectionId,constituencyId);
				if(afterDelimationtotalAndPolledVotesCount != null && afterDelimationtotalAndPolledVotesCount.size() > 0)
				{
					// here we are fillng the total and polled voters details into VO
					fillVotersCountForConstituency(afterDelimationtotalAndPolledVotesCount,delimationDetails,"after");
					
				}
				// here we are getting the party wise polled votes for present election
				List<Object[]> afterDelimationPartyResult = candidateBoothResultDAO.getPartyWiseAfterDelimationEffectBasedOnVoters(presentElectionId,constituencyId);
				if(afterDelimationPartyResult != null && afterDelimationPartyResult.size() > 0)
				{
					// here we are filling the party wise votes polled and how much percentage they got into VO
					fillPartyWiseVotersCountAndPercentage(afterDelimationPartyResult,delimationDetails,partyId,delimationEffectMap,"after",others);
				}
				
				// here we are getting the previous election id based on present election id
				Long previousElectionId = electionDAO.getElectionId(previousElectionYear,2l,1l);
				
				// here we are getting the tehsils for present elections (then only we can compare previous and present election details)
				List<Long> tehsilIds = boothDAO.getTehsilsForAfterDelimation(constituencyId,Long.valueOf(presentElectionYear));
				//List<Long> panchayatIds = panchayatDAO.getPanchayatIdsForDelemationEffect(constituencyId,Long.valueOf(presentElectionYear));
				
				// here we are getting the booth ids for list of tehsil present in present election.
				List<Long> boothIds  = boothDAO.getBoothsBeforDelimation(Long.valueOf(previousElectionYear),tehsilIds);
				//List<Long> boothIds  = panchayatHamletDAO.getboothdByPanchayat(Long.valueOf(previousElectionYear),panchayatIds);
				
				// here we are getting the previous elections total voters and polled votes
				List<Object[]> beforeDelimationtotalAndPolledVotesCount = boothResultDAO.getBeforeDelimitationEffectBasedOnVoters(previousElectionId,boothIds);
				if(beforeDelimationtotalAndPolledVotesCount != null && beforeDelimationtotalAndPolledVotesCount.size() > 0)
				{
					// here we are filling the previous voters and polled votes details in VO
					fillVotersCountForConstituency(beforeDelimationtotalAndPolledVotesCount,delimationDetails,"before");
				}
				
				// here we are getting the previous election details based on present booths
				List<Object[]> beforeDelimationPartyResult = candidateBoothResultDAO.getPartyWiseBeforDelimationEffectBasedOnVoters(previousElectionId,boothIds);
				if(beforeDelimationPartyResult != null && beforeDelimationPartyResult.size() > 0)
				{
					// here we are filling the party wise votes polled and percentage for previous election
					fillPartyWiseVotersCountAndPercentage(beforeDelimationPartyResult,delimationDetails,partyId,delimationEffectMap,"before",others);
				}
				// here we are placing the all details into VO by party wise
				Set<String> totalParties = delimationEffectMap.keySet();
				if(totalParties != null && totalParties.size() > 0)
				{
				    delimitationEffectList = new ArrayList<DelimitationEffectVO>();
					for (DelimitationEffectVO delimitationEffectVO : delimationEffectMap.values()) {
						if(delimitationEffectVO.getPresentCount() > 0 || delimitationEffectVO.getPreviousCount() > 0)
						{
							DelimitationEffectVO delimitationEffect = new DelimitationEffectVO();
							delimitationEffect.setPartyId(delimitationEffectVO.getPartyId());
							delimitationEffect.setPartyName(delimitationEffectVO.getPartyName());
							delimitationEffect.setPresentCount(delimitationEffectVO.getPresentCount());
							delimitationEffect.setPresentPolledVotes(delimitationEffectVO.getPresentPolledVotes());
							delimitationEffect.setPresentPerc(delimitationEffectVO.getPresentPerc());
							delimitationEffect.setPreviousCount(delimitationEffectVO.getPreviousCount());
							delimitationEffect.setPreviousPolledVotes(delimitationEffectVO.getPreviousPolledVotes());
							delimitationEffect.setPreviousPerc(delimitationEffectVO.getPreviousPerc());
							
							delimitationEffectList.add(delimitationEffect);
						}
						
					}
				}
				
				delimationDetails.setDelimitationEffectVO(delimitationEffectList);
				delimitationEffectList1 = new ArrayList<DelimitationEffectVO>();
				
				// here we are processing the result for the sake of allians party wise results for present election
				for (DelimitationEffectVO delimitationEffectVO : delimationDetails.getDelimitationEffectVO()) {
					
					DelimitationEffectVO delimitationEffectVO2 = new DelimitationEffectVO();
					
					List<Object[]> allianceParites = allianceGroupDAO.findAlliancePartiesByElectionAndParty(38l, delimitationEffectVO.getPartyId());
					if(allianceParites != null && allianceParites.size() > 0)
					{
						Long presentCount = 0l;
						for (Object[] objects : allianceParites)
						{
							DelimitationEffectVO delimitationEffect = delimationEffectMap.get(objects[3].toString());
							if(delimitationEffect != null)
							{
								delimitationEffectVO2.setPartyId(delimitationEffectVO.getPartyId());
								delimitationEffectVO2.setPartyName(delimitationEffectVO.getPartyName());
								delimitationEffectVO2.setPresentCount(delimitationEffectVO2.getPresentCount() + delimitationEffect.getPresentCount());
								delimitationEffectVO2.setPresentPolledVotes(delimitationEffectVO2.getPresentPolledVotes() + delimitationEffect.getPresentPolledVotes());
								delimitationEffectVO2.setPresentPerc(delimitationEffectVO2.getPresentPerc() + delimitationEffect.getPresentPerc());
								
							}
							
						}
					}
					else
					{
						delimitationEffectVO = delimationEffectMap.get(delimitationEffectVO.getPartyName());
						delimitationEffectVO2.setPartyId(delimitationEffectVO.getPartyId());
						delimitationEffectVO2.setPartyName(delimitationEffectVO.getPartyName());
						delimitationEffectVO2.setPresentCount(delimitationEffectVO.getPresentCount());
						delimitationEffectVO2.setPresentPolledVotes(delimitationEffectVO.getPresentPolledVotes());
						delimitationEffectVO2.setPresentPerc(delimitationEffectVO.getPresentPerc());
						
					}
					if(delimitationEffectVO2.getPartyName() != null)
					{
						delimationEffectMap1.put(delimitationEffectVO.getPartyName(), delimitationEffectVO2);
						delimitationEffectList1.add(delimitationEffectVO2);
					}
					
				}
				//delimationDetails.setPresentElections(delimitationEffectList1);
				
				
				delimitationEffectList2 = new ArrayList<DelimitationEffectVO>();
				// here we are processing the result for the sake of alliance party wise result for previous elections
				for (DelimitationEffectVO delimitationEffectVO : delimationDetails.getDelimitationEffectVO()) {
					
					
						//DelimitationEffectVO delimitationEffectVO2 = new DelimitationEffectVO();
					DelimitationEffectVO delimitationEffectVO2 = delimationEffectMap1.get(delimitationEffectVO.getPartyName());
						if(delimitationEffectVO != null)
						{
							
							List<Object[]> allianceParites = allianceGroupDAO.findAlliancePartiesByElectionAndParty(3l, delimitationEffectVO.getPartyId());
							if(allianceParites != null && allianceParites.size() > 0)
							{
								Long presentCount = 0l;
								for (Object[] objects : allianceParites)
								{
									DelimitationEffectVO delimitationEffect = delimationEffectMap.get(objects[3].toString());
									if(delimitationEffect != null)
									{
										delimitationEffectVO2.setPartyId(delimitationEffectVO.getPartyId());
										delimitationEffectVO2.setPartyName(delimitationEffectVO.getPartyName());
										delimitationEffectVO2.setPreviousCount(delimitationEffectVO2.getPreviousCount() + delimitationEffect.getPreviousCount());
										delimitationEffectVO2.setPreviousPolledVotes(delimitationEffectVO2.getPreviousPolledVotes() + delimitationEffect.getPreviousPolledVotes());
										delimitationEffectVO2.setPreviousPerc(delimitationEffectVO2.getPreviousPerc() + delimitationEffect.getPreviousPerc());
										
									}
									
								}
							}
							else
							{
								delimitationEffectVO = delimationEffectMap.get(delimitationEffectVO.getPartyName());
								delimitationEffectVO2.setPartyId(delimitationEffectVO.getPartyId());
								delimitationEffectVO2.setPartyName(delimitationEffectVO.getPartyName());
								delimitationEffectVO2.setPreviousCount(delimitationEffectVO.getPreviousCount());
								delimitationEffectVO2.setPreviousPolledVotes(delimitationEffectVO.getPreviousPolledVotes());
								delimitationEffectVO2.setPreviousPerc(delimitationEffectVO.getPreviousPerc());
								
							}
							
							if(delimitationEffectVO2.getPartyId() != null)
							{
								delimationEffectMap1.put(delimitationEffectVO.getPartyName(), delimitationEffectVO2);
								delimitationEffectList2.add(delimitationEffectVO2);
							}
						}
						else
						{
							delimationEffectMap1.put(delimitationEffectVO.getPartyName(), delimitationEffectVO2);
						}
					}
					

				//delimationDetails.setPervElections(delimitationEffectList2);
				
				
				// here we are processing the result for only selected parties
				String reqParties = IConstants.STATIC_PARTIESFOR_DELIMATION.replace("'", "");
				List<String> partyNames = Arrays.asList(reqParties.split("\\s*,\\s*"));
				List<DelimitationEffectVO> withoutAllianceList = new ArrayList<DelimitationEffectVO>();
				List<DelimitationEffectVO> withAllianceList = new ArrayList<DelimitationEffectVO>();
				for (String partyName : partyNames)
				{
					DelimitationEffectVO withoutAlliance = delimationEffectMap.get(partyName);
					DelimitationEffectVO withAlliance = delimationEffectMap1.get(partyName);
					if(withoutAlliance != null)
					withoutAllianceList.add(withoutAlliance);
					if(withAlliance != null)
					withAllianceList.add(withAlliance);
				}
				delimationDetails.setDelimitationEffectVO(withoutAllianceList);
				delimationDetails.setPresentElections(withAllianceList);
			} catch (Exception e) {
				LOG.error("Exception raised in getDelimationEffectOnConstituency() method ",e);
			}
			
			delimationDetails.setHeading2("Based on the new boundaries of the Constituency that " +
					"came into effect post the delimitation in 2009, we have segregated each " +
					"party’s performance for 2004 Results to match " +
					"these areas. Thus the setting of goal is more " +
					"accurate as we have the exact statistics for" +
					" the present boundaries.)");
			
			delimationDetails.setHeading3(" Parties Performance " +
					" in "+constituencyDAO.get(constituencyId).getName()+" Constituency for 2004 & 2009" +
					" Elections (Based on 2009 Delimitation)");
			return delimationDetails;		
		}

	  
	  public void fillVotersCountForConstituency(List<Object[]> result,DelimitationEffectVO delimationDetails,String type)
		{
			try {
				LOG.debug("Enterd into fillPartyWiseVotersCountAndPercentage() method ");
				DecimalFormat df = new DecimalFormat("#.##");
				if(type.equalsIgnoreCase("after"))
				{
					for (Object[] parms : result) {
						delimationDetails.setPresentCount((Long)parms[0]);
						delimationDetails.setPresentPolledVotes((Long)parms[1]);
						if(delimationDetails.getPresentCount() >0 && delimationDetails.getPresentPolledVotes() > 0)
						{
							Double percentage = Double.valueOf(df.format(Long.valueOf(delimationDetails.getPresentPolledVotes())*100/(float)delimationDetails.getPresentCount()));
							delimationDetails.setPresentPerc(percentage);
						}
					}
				}
				else
				{
					for (Object[] parms : result) {
						delimationDetails.setPreviousCount((Long)parms[0]);
						delimationDetails.setPreviousPolledVotes((Long)parms[1]);
						if(delimationDetails.getPreviousCount() >0 && delimationDetails.getPreviousPolledVotes() > 0)
						{
							Double percentage = Double.valueOf(df.format(Long.valueOf(delimationDetails.getPreviousPolledVotes())*100/(float)delimationDetails.getPreviousCount()));
							delimationDetails.setPreviousPerc(percentage);
						}
					}
				}
				
			} catch (Exception e) {
				LOG.error("Exception raised in fillVotersCountForConstituency() method ",e);
			}
			
		}
	  
	  public void fillPartyWiseVotersCountAndPercentage(List<Object[]> result,DelimitationEffectVO delimationDetails,Long partyId,Map<String, DelimitationEffectVO> delimationEffectMap,String type,DelimitationEffectVO others)
		{
			try {
				LOG.debug("Enterd into fillPartyWiseVotersCountAndPercentage() method ");
				DecimalFormat df = new DecimalFormat("#.##");
				//String parties = IConstants.STATIC_PARTIESFOR_DELIMATION.replace("'", "");
				String parties = IConstants.STATIC_PARTIES.replace("'", "");
				List<String> partyNames = Arrays.asList(parties.split("\\s*,\\s*"));
				
				Long count = 0l;
				DelimitationEffectVO delimitationEffectVO = null;
				
					for (Object[] parms : result) {
						
						String party = parms[2].toString();
						delimitationEffectVO =  delimationEffectMap.get(party);
						if(delimitationEffectVO == null)
						{
							delimitationEffectVO = new DelimitationEffectVO();
							delimationEffectMap.put(party, delimitationEffectVO);
						}
						if(type.equalsIgnoreCase("after"))
						{
							if(partyNames.contains(party))
							{
								delimitationEffectVO.setPresentCount((Long)parms[1]);
								delimitationEffectVO.setPartyId((Long)parms[0]);
								delimitationEffectVO.setPartyName(parms[2] != null ? parms[2].toString() : "");
								if(delimationDetails.getPresentCount() > 0 && delimationDetails.getPresentPolledVotes() > 0)
								{
									Double percentage = Double.valueOf(df.format(Long.valueOf(delimitationEffectVO.getPresentCount())*100/(float)delimationDetails.getPresentPolledVotes()));
									delimitationEffectVO.setPresentPerc(percentage);
									
								}
							}
							else
							{
								count = count + (Long)parms[1];
								others.setPartyId(0l);
								others.setPresentCount(count);
								others.setPartyName("Others");
								Double percentage = Double.valueOf(df.format(Long.valueOf(count)*100/(float)delimationDetails.getPresentPolledVotes()));
								others.setPresentPerc(percentage);
							
							}
						}
						else
						{
							if(partyNames.contains(party))
							{
								delimitationEffectVO.setPreviousCount((Long)parms[1]);
								delimitationEffectVO.setPartyId((Long)parms[0]);
								delimitationEffectVO.setPartyName(parms[2] != null ? parms[2].toString() : "");
								if(delimationDetails.getPreviousCount() > 0 && delimationDetails.getPreviousPolledVotes() > 0)
								{
									Double percentage = Double.valueOf(df.format(Long.valueOf(delimitationEffectVO.getPreviousCount())*100/(float)delimationDetails.getPreviousPolledVotes()));
									delimitationEffectVO.setPreviousPerc(percentage);
									
								}
							}
							else
							{
								count = count + (Long)parms[1];
								others.setPartyId(0l);
								others.setPreviousCount(count);
								others.setPartyName("Others");
								Double percentage = Double.valueOf(df.format(Long.valueOf(count)*100/(float)delimationDetails.getPreviousPolledVotes()));
								others.setPreviousPerc(percentage);
							
							}
						}
						
					}
				
				
				if(others.getPreviousCount() > 0 || others.getPresentCount() > 0)
				{
					delimationEffectMap.put(others.getPartyName(), others);
				}
				
				
				
			} catch (Exception e) {
				LOG.error("Exception raised in fillPartyWiseVotersCountAndPercentage() method ",e);
			}
			
		}
		
	  
	  public AssumptionsVO votersAssumptionsService(Long constituencyId,Long base,Long assured,Long publicationDateId,Long tdpPerc)
	  {
		  AssumptionsVO assumptionsVO = null;
		  try {
			  LOG.debug("Enterd into fillPartyWiseVotersCountAndPercentage() method ");
			  
			  Long totalVoters = voterInfoDAO.getVotersCountInALocation(1l, constituencyId, publicationDateId, constituencyId);
			  if(totalVoters != null && totalVoters > 0)
			  {
				  
				  

				  assumptionsVO = new AssumptionsVO();
				  Long targetedPolledVotees = ((totalVoters * base)/100);
				  
				  Long targetedVotesForTDP  = ((targetedPolledVotees * tdpPerc)/100);
				  Long addtionalPerc = tdpPerc - assured ;
				  Long addtionalVoters = ((targetedPolledVotees * addtionalPerc)/100);
				  
				  
				  assumptionsVO.setTotalVoters(totalVoters);
				  assumptionsVO.setExpPerc(base);
				  assumptionsVO.setTargetedPolledVotees(targetedPolledVotees);
				  assumptionsVO.setTargetedVotesForTDP(targetedVotesForTDP);
				  assumptionsVO.setAddtionalVoters(addtionalVoters);
				  assumptionsVO.setAddtionalPerc(addtionalPerc);
				  
				  assumptionsVO.setAssuredPer(assured);
				  assumptionsVO.setTdpPer(tdpPerc);
				  
				  assumptionsVO.setHeading1("Poll Percentage "+base+"% ");
				  assumptionsVO.setHeading2("Contending Party’s – 3 Major Parties (TDP, INC and YSRCP)");
				  assumptionsVO.setHeading3("Assured Voter Base for our Party – "+assured+"% (Based on the Previous Votes Polled)");
				  assumptionsVO.setHeading4("Final Goal");
				  
			  }
		} catch (Exception e) {
			LOG.error("Exception raised in votersAssumptionsService() method ",e);
		}
		return assumptionsVO;
	  }
	  
	  public void generatePdfForLocalElectionResults(PartyResultsVerVO prevResults , Document document){
		  LOG.debug("Entered Into LocalElectionResults Blocks For GENERATING PDF");
		//  Document document = new Document();
		  try {
			  Font calibriBold = FontFactory.getFont("Calibri",9,Font.BOLD);
			  BaseColor winner=new BaseColor(146, 208, 80);
			  BaseColor runner= new BaseColor(141,180,226);
			  Font calibriBold1 = FontFactory.getFont("Calibri",21,Font.BOLD);
			  Font calibriBold3 = FontFactory.getFont("Calibri",18,Font.BOLD);
		      TITLE.setColor(BaseColor.BLACK);
		      Font calibriBold2 = FontFactory.getFont("Calibri",25,Font.BOLD);
		  Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
		  //Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
		  
		//  Font TBCELL = new Font(Font.FontFamily.TIMES_ROMAN, 8,Font.BOLD);
		  Font TBCELL =calibriBold;
		  Font TBCELLSM = calibriBold;
		//  Font TBCELLSM_WIN = new Font(Font.FontFamily.TIMES_ROMAN,7,Font.NORMAL);
		  Font TBCELLSM_WIN = calibriBold;
		  
		  Font SMALLFONT_WIN1 = FontFactory.getFont("Calibri",7,Font.NORMAL);
		

		/*  Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
		  subHeading.setColor(BaseColor.MAGENTA);
		  */
		  
		  
		  if(prevResults.getPartyResultsVOList()!=null && prevResults.getPartyResultsVOList().size()>0){
			  
		  document.add(new Paragraph(prevResults.getZptcMptcTitle(),SMALLFONT));
		  document.add(Chunk.NEWLINE);
		  
		  int columnsCount=10;
		  boolean trsAvail=false;
		  if(prevResults.getDistrictId()<=10){
			  columnsCount=12;
			  trsAvail=true;
		  }
		 int padding =6;
		  BaseColor bcolor=BaseColor.YELLOW;
		  PdfPTable table = new PdfPTable(columnsCount);
		  table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		 
		  PdfPCell column=null;
		  column = new PdfPCell(new Phrase("YEAR",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Total Votes",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes Polled",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("TDP",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase(" %    Votes    (TDP)",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setPadding(padding);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("  Margin     Votes     (%)",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("INC",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);

		  column = new PdfPCell(new Phrase("   %          Votes    (INC)",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  if(trsAvail){
			  column = new PdfPCell(new Phrase("TRS",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setBackgroundColor(bcolor);
			  table.addCell(column);

			  column = new PdfPCell(new Phrase("%         Votes      (TRS)",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setBackgroundColor(bcolor);
			  table.addCell(column);
		  }
		  
		  column = new PdfPCell(new Phrase("OTHERS",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);

		  column = new PdfPCell(new Phrase("   %         Votes      (OTHERS)",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  for(PartyResultsVO temp:prevResults.getPartyResultsVOList()){
			  PdfPTable pt =new PdfPTable(1);
			  pt.getDefaultCell().setBorder(0);
			PdfPCell cel=  new PdfPCell(new Phrase(new Chunk(temp.getYear().toString()+"-",TBCELLSM)));
			cel.setBorder(Rectangle.NO_BORDER);
			pt.addCell(cel);
			cel=new PdfPCell(new Phrase(new Chunk(" "+temp.getElectionName(),SMALLFONT_WIN1)));
			cel.setBorder(Rectangle.NO_BORDER);
			//cel.setBorder(PdfPCell.NO_BORDER);
			pt.addCell(cel);
			  
			 // column = new PdfPCell(new Phrase(new Chunk(temp.getYear().toString(),TBCELLSM))+"-"+new Phrase(new Chunk(temp.getElectionName(),SMALLFONT_WIN1)));
			column=new PdfPCell(pt);
			 column.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(column);
			  
			  column = new PdfPCell(new Phrase("--",TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(temp.getValidVotes().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  
			  boolean tdpexist=false;
			  boolean incexist=false;
			  boolean trsexist=false;
			  
			  //BUILDING TDP CELLS
			  for(PartyResultsVO temp1:temp.getPartyResultsVOList()){
				  if(temp1.getPartyId().longValue()==872l){
					  if(temp1.getRank()!=null){
						  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM_WIN));
						  column.setHorizontalAlignment(Element.ALIGN_CENTER);
						  if(temp1.getRank()==1l){
							  column.setBackgroundColor(winner);
						  }else if(temp1.getRank()==2l){
							  column.setBackgroundColor(runner);
						  }
						  table.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp1.getDiffPercent(),TBCELLSM));
						  column.setHorizontalAlignment(Element.ALIGN_CENTER);
						  column.setPadding(padding);
						  table.addCell(column);
					  }else{
						  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM));
						  column.setHorizontalAlignment(Element.ALIGN_CENTER);
						  table.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp1.getDiffPercent(),TBCELLSM));
						  column.setHorizontalAlignment(Element.ALIGN_CENTER);
						  column.setPadding(padding);
						  table.addCell(column);
					  }
					  
					  tdpexist=true;
				  }
			  }
			  
			  if(!tdpexist){
				  column = new PdfPCell(new Phrase("--",TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  table.addCell(column);
				  
				  column = new PdfPCell(new Phrase("--",TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  table.addCell(column);
			  }
			  
			  
			  column = new PdfPCell(new Phrase(temp.getMarginVotes().toString()+"       ("+temp.getMarginPercent()+")",TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setPadding(6);
			  table.addCell(column);
			  
			  
			//BUILDING INC CELLS
			  for(PartyResultsVO temp1:temp.getPartyResultsVOList()){
				  if(temp1.getPartyId().longValue()==362l){
					  if(temp1.getRank()!=null){
						  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM_WIN));
						  column.setHorizontalAlignment(Element.ALIGN_CENTER);

						  if(temp1.getRank()==1l){
							  column.setBackgroundColor(winner);
						  }else if(temp1.getRank()==2l){
							  column.setBackgroundColor(runner);
						  }
						  table.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp1.getDiffPercent(),TBCELLSM));
						  column.setHorizontalAlignment(Element.ALIGN_CENTER);

						  table.addCell(column);
					  }else{
						  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM));
						  column.setHorizontalAlignment(Element.ALIGN_CENTER);

						  table.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp1.getDiffPercent(),TBCELLSM));
						  column.setHorizontalAlignment(Element.ALIGN_CENTER);

						  table.addCell(column);
					  }
					  
					  incexist=true;
				  }
			  }
			  
			  if(!incexist){
				  column = new PdfPCell(new Phrase("--",TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);

				  table.addCell(column);
				  
				  column = new PdfPCell(new Phrase("--",TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);

				  table.addCell(column);
			  }
			  
			if(trsAvail){
			 //BUILDING TRS CELLS
			  for(PartyResultsVO temp1:temp.getPartyResultsVOList()){
				  if(temp1.getPartyId().longValue()==886l){
					  if(temp1.getRank()!=null){
						  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM_WIN));
						  column.setHorizontalAlignment(Element.ALIGN_CENTER);

						  if(temp1.getRank()==1l){
							  column.setBackgroundColor(winner);
						  }else if(temp1.getRank()==2l){
							  column.setBackgroundColor(runner);
						  }
						  table.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp1.getDiffPercent(),TBCELLSM));
						  column.setHorizontalAlignment(Element.ALIGN_CENTER);

						  table.addCell(column);
					  }else{
						  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM));
						  column.setHorizontalAlignment(Element.ALIGN_CENTER);

						  table.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp1.getDiffPercent(),TBCELLSM));
						  column.setHorizontalAlignment(Element.ALIGN_CENTER);

						  table.addCell(column);
					  }
					  
					  trsexist=true;
				  }
			  }
			  
			  if(!trsexist){
				  column = new PdfPCell(new Phrase("--",TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);

				  table.addCell(column);
				  
				  column = new PdfPCell(new Phrase("--",TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);

				  table.addCell(column);
			  }
			}
			if(temp.getOtherVotes()!=null)
			  column = new PdfPCell(new Phrase(temp.getOtherVotes().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  if(temp.isOtherRankedOne()){
				  column.setBackgroundColor(winner);
			  }else if(temp.isOtherRankedTwo()){
				  column.setBackgroundColor(runner);
			  }
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(temp.getOtherVotesPercent(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);

			  table.addCell(column);
		  }
		  
		  document.add(Chunk.NEWLINE);
		  
		  float[] widths = new float[] {1.2f, 1.2f ,1.5f,1.2f,1.3f, 1.7f ,1.2f,1.2f,1.6f,1.8f };
			table.setWidths(widths);
		  document.add(table);
		  document.add(Chunk.NEWLINE);
		  }
		  TITLE.setColor(subHeading);
		
		  Chunk id = new Chunk("                                                    ",calibriBold2);
	         
		     
		     Chunk id1 = new Chunk("Winner", SMALLFONT);
		  
		     Chunk id2 = new Chunk("-", calibriBold);
		     Chunk id7 = new Chunk(" ", calibriBold);
		     
		     Chunk id3 = new Chunk("   ", calibriBold3);
		     id3.setBackground(winner);
		     Chunk id9 = new Chunk(" ", calibriBold);
		     Chunk id4 = new Chunk("Runner", SMALLFONT);
			  
			     Chunk id5 = new Chunk("-", calibriBold);
			     Chunk id8 = new Chunk(" ", calibriBold3);
			    
			     Chunk id6 = new Chunk("   ", calibriBold3);				     
			     id6.setBackground(runner);
	        // id3.setHorizontalScaling(2);
	         
	        // Image img = Image.getInstance(IConstants.IMAGE);
		       
		 // Chunk id1= new Chunk(img, 5, 5, false);
	   //  id1.setBackground(BaseColor.RED);
	    // id1.setHorizontalScaling(2);
		
	
	     document.add(id);
	     document.add(id1);
	     document.add(id2);
	     document.add(id7);
	     document.add(id3);
	     document.add(id9);
	     document.add(id4);	    
	     document.add(id5);
	     document.add(id8);
	     document.add(id6);
	     
		  if(prevResults.getPartyStrengths()!=null){
			  if(prevResults.getPartyStrengths().size()>0){
				  Paragraph phead = new Paragraph("Ward Wise Election Results In 2005",TITLE);
				  document.add(phead);
				  List<PartyResultsVO> resultsVOs=prevResults.getPartyStrengths();
				  
				  boolean tdpexist=false;
				  boolean incexist=false;
				  boolean trsexist=false;
				  boolean bjpexist=false;
				  
				  boolean trsavail=false;
				  
				  Long disrictId=prevResults.getDistrictId();
				  if(disrictId<=10){
					  trsavail=true;
				  }
				  
				  int columnsCount=12;
				  boolean trsAvail=false;
				  if(prevResults.getDistrictId()<=10){
					  columnsCount=15;
					  trsAvail=true;
				  }
				  
				  PdfPTable table2 = new PdfPTable(columnsCount);
				  PdfPCell column=null;
				  column = new PdfPCell(new Phrase("TDP",TBCELL));
				  column.setBackgroundColor(bcolor);
				  column.setColspan(3);
				  table2.addCell(column);
				  
				  
				  column = new PdfPCell(new Phrase("INC",TBCELL));
				  column.setBackgroundColor(bcolor);
				  column.setColspan(3);
				  table2.addCell(column);
				  
				  column = new PdfPCell(new Phrase("BJP",TBCELL));
				  column.setBackgroundColor(bcolor);
				  column.setColspan(3);
				  table2.addCell(column);
				  
				  if(trsavail){
					  column = new PdfPCell(new Phrase("TRS",TBCELL));
					  column.setBackgroundColor(bcolor);
					  column.setColspan(3);
					  table2.addCell(column);
				  }
				  
				  column = new PdfPCell(new Phrase("OTHERS",TBCELL));
				  column.setBackgroundColor(bcolor);
				  column.setColspan(3);
				  table2.addCell(column);
				  
				  
				  column = new PdfPCell(new Phrase("Participated",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  column = new PdfPCell(new Phrase("Secured",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  column = new PdfPCell(new Phrase("%",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  
				  column = new PdfPCell(new Phrase("Participated",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  column = new PdfPCell(new Phrase("Secured",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  column = new PdfPCell(new Phrase("%",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  
				  column = new PdfPCell(new Phrase("Participated",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  column = new PdfPCell(new Phrase("Secured",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  column = new PdfPCell(new Phrase("%",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  
				  if(trsavail){
				  column = new PdfPCell(new Phrase("Participated",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  column = new PdfPCell(new Phrase("Secured",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  column = new PdfPCell(new Phrase("%",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  }
				  
				  column = new PdfPCell(new Phrase("Participated",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  column = new PdfPCell(new Phrase("Secured",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  column = new PdfPCell(new Phrase("%",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table2.addCell(column);
				  
				  
				  for(PartyResultsVO temp:resultsVOs){
					  if(temp.getPartyId().longValue()==872l){
						  column = new PdfPCell(new Phrase(temp.getParticipated().toString(),TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp.getWon().toString(),TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp.getPercentage(),TBCELLSM));
						  table2.addCell(column);
						  
						  tdpexist=true;
					  }
				  }
					  if(!tdpexist){
						  column = new PdfPCell(new Phrase("-",TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase("-",TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase("-",TBCELLSM));
						  table2.addCell(column);
					  }
					  
					  for(PartyResultsVO temp:resultsVOs){
					  if(temp.getPartyId().longValue()==362l){
						  column = new PdfPCell(new Phrase(temp.getParticipated().toString(),TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp.getWon().toString(),TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp.getPercentage(),TBCELLSM));
						  table2.addCell(column);
						  
						  incexist=true;
					  }
					  }
					  if(!incexist){
						  column = new PdfPCell(new Phrase("-",TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase("-",TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase("-",TBCELLSM));
						  table2.addCell(column);
					  }
					  
					  for(PartyResultsVO temp:resultsVOs){
					  if(temp.getPartyId().longValue()==163l){
						  column = new PdfPCell(new Phrase(temp.getParticipated().toString(),TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp.getWon().toString(),TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp.getPercentage(),TBCELLSM));
						  table2.addCell(column);
						  
						  bjpexist=true;
					  }
					  }
					  if(!bjpexist){
						  column = new PdfPCell(new Phrase("-",TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase("-",TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase("-",TBCELLSM));
						  table2.addCell(column);
					  }
					  if(trsavail){
						  for(PartyResultsVO temp:resultsVOs){
					  if(temp.getPartyId().longValue()==886l){
						  column = new PdfPCell(new Phrase(temp.getParticipated().toString(),TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp.getWon().toString(),TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase(temp.getPercentage(),TBCELLSM));
						  table2.addCell(column);
						  
						  trsexist=true;
					  }
						  }
					  if(!trsexist){
						  column = new PdfPCell(new Phrase("-",TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase("-",TBCELLSM));
						  table2.addCell(column);
						  
						  column = new PdfPCell(new Phrase("-",TBCELLSM));
						  table2.addCell(column);
					  }
					  }
					  
					  column = new PdfPCell(new Phrase(prevResults.getParticipated().toString(),TBCELLSM));
					  table2.addCell(column);
					  
					  column = new PdfPCell(new Phrase(prevResults.getWon().toString(),TBCELLSM));
					  table2.addCell(column);
					  
					  column = new PdfPCell(new Phrase(prevResults.getOtherVotesPercent(),TBCELLSM));
					  table2.addCell(column);
					  
					  
				 
						float[] widths = new float[] {1.2f, 1.2f ,1.2f,1.2f,1.2f, 1.2f ,1.2f,1.2f,1.2f, 1.2f ,1.2f,1.2f};
						table2.setWidths(widths);
				        document.add(table2);  
			    }
			  
		  
		  document.add(Chunk.NEWLINE);
		  document.add(new Paragraph(prevResults.getTotalNoOfWardsTitle(),TITLE));
		  document.add(Chunk.NEWLINE);
		  document.add(new Paragraph(prevResults.getWardTitle(),SMALLFONT));
		  document.add(Chunk.NEWLINE);
		  
		 
		  }
		  
		  if(prevResults.getGmcResults()!=null || prevResults.getMuncipalCorpResults()!=null){
			  document.add(new Paragraph(prevResults.getMuncipalOrCorpOrGmc(),TITLE));
			  document.add(Chunk.NEWLINE);
			  
			  int columnsCount=7;
			  boolean trsAvail=false;
			  if(prevResults.getDistrictId()<=10){
				  columnsCount=8;
				  trsAvail=true;
			  }
			  
			  PdfPTable table1 = new PdfPTable(columnsCount);
			  PdfPCell column=null;
			  column = new PdfPCell(new Phrase("Location",TBCELL));
			  column.setBackgroundColor(bcolor);
			  table1.addCell(column);
			  
			  column = new PdfPCell(new Phrase("Total Votes",TBCELL));
			  column.setBackgroundColor(bcolor);
			  table1.addCell(column);
			  
			  column = new PdfPCell(new Phrase("Votes Polled",TBCELL));
			  column.setBackgroundColor(bcolor);
			  table1.addCell(column);
			  
			  column = new PdfPCell(new Phrase("TDP",TBCELL));
			  column.setBackgroundColor(bcolor);
			  table1.addCell(column);
			  
			  
			  column = new PdfPCell(new Phrase("INC",TBCELL));
			  column.setBackgroundColor(bcolor);
			  table1.addCell(column);
			  
			  column = new PdfPCell(new Phrase("BJP",TBCELL));
			  column.setBackgroundColor(bcolor);
			  table1.addCell(column);

			  
			  if(trsAvail){
				  column = new PdfPCell(new Phrase("TRS",TBCELL));
				  column.setBackgroundColor(bcolor);
				  table1.addCell(column);
			  }
			  
			  column = new PdfPCell(new Phrase("OTHERS",TBCELL));
			  column.setBackgroundColor(bcolor);
			  table1.addCell(column);
			  
			  List<PartyResultsVO> results=new ArrayList<PartyResultsVO>();
			  
			  if(prevResults.getMuncipalCorpResults()!=null){
				  results=prevResults.getMuncipalCorpResults();
			  }
			  if(prevResults.getGmcResults()!=null){
				  results=prevResults.getGmcResults();
			  }
			  for(PartyResultsVO temp:results){
				  
				  column = new PdfPCell(new Phrase(temp.getLocation(),TBCELLSM));
				  table1.addCell(column);
				  
				  column = new PdfPCell(new Phrase(temp.getVotesPolled().toString(),TBCELLSM));
				  table1.addCell(column);
				  
				  column = new PdfPCell(new Phrase(temp.getValidVotes().toString(),TBCELLSM));
				  table1.addCell(column);
				  
				  boolean tdpexist=false;
				  boolean incexist=false;
				  boolean trsexist=false;
				  boolean bjpexist=false;
				  
				  boolean ranked=false;
				  
				  for(PartyResultsVO temp1:temp.getPartyResultsVOList()){
					  
					  if(temp1.getPartyId().longValue()==872l){
						  if(temp1.getRank()==1l){
							  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM_WIN));
							  column.setBackgroundColor(winner);
							  table1.addCell(column);
							  
							  ranked=true;
						  }else{
							  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM));
							  table1.addCell(column);
						  }
						  tdpexist=true;
					  }
				  }
					  if(!tdpexist){
						  column = new PdfPCell(new Phrase("--",TBCELLSM));
						  table1.addCell(column);
						 
					  }
					  
					  //FOR INC
					  for(PartyResultsVO temp1:temp.getPartyResultsVOList()){
						  
					  if(temp1.getPartyId().longValue()==362l){
						  if(temp1.getRank()==1l){
							  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM_WIN));
							  column.setBackgroundColor(winner);
							  table1.addCell(column);
							  
							  ranked=true;
						  }else{
							  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM));
							  table1.addCell(column);
						  }
						  incexist=true;
					  }
					  }
					  if(!incexist){
						  column = new PdfPCell(new Phrase("--",TBCELLSM));
						  table1.addCell(column);
						 
					  }
					  
					  
					  //FOR BJP
					  for(PartyResultsVO temp1:temp.getPartyResultsVOList()){
						  
					  if(temp1.getPartyId().longValue()==163l){
						  if(temp1.getRank()==1l){
							  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM_WIN));
							  column.setBackgroundColor(winner);
							  table1.addCell(column);
							  
							  ranked=true;
						  }else{
							  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM));
							  table1.addCell(column);
						  }
						  bjpexist=true;
					  }
					  }
					  if(!bjpexist){
						  column = new PdfPCell(new Phrase("--",TBCELLSM));
						  table1.addCell(column);
						  
					  }
					  
					  if(trsAvail){
					  //FOR TRS
						  for(PartyResultsVO temp1:temp.getPartyResultsVOList()){
							  
					  if(temp1.getPartyId().longValue()==886l){
						  if(temp1.getRank()==1l){
							  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM_WIN));
							  column.setBackgroundColor(winner);
							  table1.addCell(column);
							  
							  ranked=true;
						  }else{
							  column = new PdfPCell(new Phrase(temp1.getVotesEarned().toString(),TBCELLSM));
							  table1.addCell(column);
						  }
						  trsexist=true;
					  }
						  }
					  if(!trsexist){
						  column = new PdfPCell(new Phrase("--",TBCELLSM));
						  table1.addCell(column);
					  }
						 
					  }
					  
					  if(temp.getOtherVotes()!=null){
						  if(!ranked){
							  column = new PdfPCell(new Phrase(temp.getOtherVotes().toString(),TBCELLSM_WIN));
							  column.setBackgroundColor(winner);
							  table1.addCell(column);
						  }else{
							  column = new PdfPCell(new Phrase(temp.getOtherVotes().toString(),TBCELLSM));
							  table1.addCell(column);
						  }
					  }else{
						  column = new PdfPCell(new Phrase("--",TBCELLSM));
						  table1.addCell(column);
					  }
					  
				  }
			  
			  document.add(new Paragraph(prevResults.getInformation(),SMALLFONT)); 
			  document.add(Chunk.NEWLINE);
			  document.add(table1);
			  

		  }
		  
		 
		  
		  } catch (Exception e) {
			  LOG.debug("Exception Raised while GENERATING PDF in LocalElectionResults Blocks" ,e);
			
		  } 
		 
	  }
	  
	  public void generatePDFForVoterInfo(PDFHeadingAndReturnVO result,String task,Document document ){
		  LOG.debug("Entered Into VoterInfo Blocks For GENERATING PDF");
		  
		  //Document document = new Document();
		  try {
			//PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(IConstants.pdfLocalPath));
		
		  
			//document.open();
		  
		  //Font TITLE = new Font(Font.FontFamily.TIMES_ROMAN, 9,Font.BOLD);
		  TITLE.setColor(subHeading);
		  
		  Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
		 // Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
		  
		  Font SMALLFONT_WIN=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
		  SMALLFONT_WIN.setColor(BaseColor.GREEN);

		  //Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
		 // subHeading.setColor(BaseColor.MAGENTA);
		  
		  
		  Font TBCELL = new Font(calibriBold);
		  Font TBCELLSM = new Font(SMALLFONT);
		  
		  
		  if(task.equalsIgnoreCase("voterInfo")){
			  
			  
	      document.add(new Paragraph(result.getMainHeading(),TITLE));
			 
		  document.add(new Phrase(""));			  
		  PdfPTable table = new PdfPTable(4);
		  table.setWidthPercentage(50);
		  table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		  PdfPCell column=null;
		  column = new PdfPCell(new Phrase("Publication Date",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  column.setPadding(5);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Total Voters",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setPadding(5);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Male Voters",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setPadding(5);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Female Voters",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setPadding(5);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  List<VoterAgeRangeVO> rslt=result.getVoterInfoByPublicationList();
		  
		  for(VoterAgeRangeVO temp1:rslt){
		  column = new PdfPCell(new Phrase(temp1.getPublicationDate(),TBCELLSM));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setPadding(5);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase(temp1.getTotalVoters().toString(),TBCELLSM));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setPadding(5);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase(temp1.getMaleVoters().toString(),TBCELLSM));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setPadding(5);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase(temp1.getFemaleVoters().toString(),TBCELLSM));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setPadding(5);
		  table.addCell(column);
		  }
		  float[] widths=new float[] {2.0f,0.8f,0.8f,0.8f};
		  table.setWidths(widths);
		  document.add(table);
		  document.add(Chunk.NEWLINE);
		 
		  }
		  if(task.equalsIgnoreCase("genderWise")){
			  
			  document.add(new Paragraph(result.getMainHeading(),TBCELL));
			  document.add(new Phrase(""));
			  
			  PdfPTable table = new PdfPTable(8);
			  table.setWidthPercentage(86);
			  table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
			  PdfPCell column=null;
			  column = new PdfPCell(new Phrase("Publication Name",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setRowspan(2);
			  column.setPadding(5);
			  column.setBackgroundColor(bcolor);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase("Previous Publication Name",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setBackgroundColor(bcolor);
			  column.setPadding(5);
			  column.setRowspan(2);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase("Added",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setBackgroundColor(bcolor);
			  column.setColspan(3);
			  column.setPadding(5);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase("Deleted",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setBackgroundColor(bcolor);
			  column.setColspan(3);
			  column.setPadding(5);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase("Total",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setBackgroundColor(bcolor);
			  column.setPadding(5);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase("Male",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setPadding(5);
			  column.setBackgroundColor(bcolor);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase("Female",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setPadding(5);
			  column.setBackgroundColor(bcolor);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase("Total",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setBackgroundColor(bcolor);
			  column.setPadding(5);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase("Male",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setBackgroundColor(bcolor);
			  column.setPadding(5);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase("Female",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setBackgroundColor(bcolor);
			  table.addCell(column);
			  
			 			  
			  List<VoterModificationGenderInfoVO> rslt=result.getGenderWiseVoterModificationInfoForEachPublicationList();
			  
			  for(VoterModificationGenderInfoVO temp1:rslt){
			  column = new PdfPCell(new Phrase(temp1.getPublicationName(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setPadding(8);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(temp1.getPreviousPublicationName(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setPadding(8);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(temp1.getAddedTotal().toString(),TBCELLSM));
			  column.setPadding(5);
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase(temp1.getAddedMale().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setPadding(5);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase(temp1.getAddedFemale().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setPadding(5);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(temp1.getDeletedTotal().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setPadding(5);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase(temp1.getDeletedMale().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setPadding(5);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase(temp1.getDeletedFemale().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setPadding(5);
			  table.addCell(column);
			  }
			  float[] widths=new float[] {2.5f,2.5f,1f,1f,1.2f,1f,1f,1f};
			  table.setWidths(widths);
			  document.add(table);
			  document.add(Chunk.NEWLINE);
		  }
		  
		  if(task.equalsIgnoreCase("addedDeleted")){
			  
			  document.add(new Paragraph(result.getSubHeading(),TITLE));
			  
			  document.add(new Phrase(""));
			  PdfPTable table = new PdfPTable(7);
			  table.setWidthPercentage(65);
			  table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
			  PdfPCell column=null;
			  
			  List<VoterModificationAgeRangeVO> rslt=result.getAgeRangeWiseAddedDeletedList();
			  
			  column = new PdfPCell(new Phrase("Age Range",TBCELL));
			  column.setPadding(7);
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setBackgroundColor(bcolor);
			  table.addCell(column);
			  for(VoterModificationAgeRangeVO temp1:rslt){
				  column = new PdfPCell(new Phrase(temp1.getRange(),TBCELL));
				  column.setBackgroundColor(bcolor);
				  column.setPadding(7);
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  table.addCell(column);
			  }
			  
			  column = new PdfPCell(new Phrase("Added",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setPadding(7);
			  table.addCell(column);
			  
			  for(VoterModificationAgeRangeVO temp1:rslt){
				  column = new PdfPCell(new Phrase(temp1.getAddedCount().toString(),TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  column.setPadding(7);
				  table.addCell(column);
			  }

			  column = new PdfPCell(new Phrase("Deleted",TBCELL));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setPadding(7);
			  table.addCell(column);
			  
			  for(VoterModificationAgeRangeVO temp1:rslt){
				  column = new PdfPCell(new Phrase(temp1.getDeletedCount().toString(),TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  column.setPadding(7);
				  table.addCell(column);
				  
			  }
			  float[] widths=new float[] {2.0f,1.8f,1.5f,1.5f,1.5f,1.5f,1.7f};
			  table.setWidths(widths);
			  document.add(table);
			  document.add(Chunk.NEWLINE);
		  }
		  TITLE.setColor(BaseColor.BLACK);
		 // document.close(); 
		  }
		  catch (Exception e) {
			  LOG.error("Exception Occured While Generating PDF for VotersInfo Block of VoterModification" ,e);
		}
	  }
		  
		  
	  
	  public void generatePDFForDensity(VoterDensityWithPartyVO result,Document document){
		  LOG.debug("Entered Into Density For GENERATING PDF");
		  
	
		  try {  
		  //Font TITLE = new Font(Font.FontFamily.TIMES_ROMAN, 9);
		  //TITLE.setColor(BaseColor.BLACK);
		  
		  Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
		  BIGFONT.setColor(new BaseColor(141,180,226));
		 // Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	  
		  Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
		  subHeading.setColor(BaseColor.MAGENTA);
		  
		  Font TBCELL = new Font(calibriBold);
		  Font TBCELLSM = new Font(SMALLFONT);
		  
		  document.add(new Paragraph(result.getMainHeading(),TITLE));
		  document.add(new Phrase(""));
		  document.add(new Paragraph(result.getInformation(),SMALLFONT));
		  document.add(new Paragraph(" "));
		  PdfPTable table = new PdfPTable(11);
		  
		  table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		  table.setWidthPercentage(85);
		  PdfPCell column=null;
		  column = new PdfPCell(new Phrase("Voters Range",TBCELL));
		  column.setBackgroundColor(BaseColor.YELLOW);
		  table.addCell(column);		 
		  List<VoterDensityWithPartyVO> rslt=result.getDensityList();
		  if(rslt!=null){
		  for(VoterDensityWithPartyVO temp:rslt){
			  column = new PdfPCell(new Phrase(temp.getType(),TBCELL));
			  column.setBackgroundColor(BaseColor.YELLOW);
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
		  }
		  }
		  
		  column = new PdfPCell(new Phrase("No of Panchayats",TBCELL));
		  column.setBackgroundColor(new BaseColor(141,180,226));
		  table.addCell(column);
		  
		  if(rslt!=null){
		  for(VoterDensityWithPartyVO temp1:rslt){
			  column = new PdfPCell(new Phrase(temp1.getCount().toString(),TBCELLSM));
			  column.setBackgroundColor(new BaseColor(141,180,226));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
		  }
		  }
		  
		  if(result.getPartyWiseList()!=null){
		  for(VoterDensityWithPartyVO temp2:result.getPartyWiseList()){
			  column = new PdfPCell(new Phrase(temp2.getPartyName(),TBCELLSM));
			  table.addCell(column);
			  for(VoterDensityWithPartyVO temp3:temp2.getDensityList()){
				  column = new PdfPCell(new Phrase(temp3.getCount().toString(),TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  table.addCell(column);
			  }
		  }
		  }
		  
		  column = new PdfPCell(new Phrase("Ananymous",TBCELLSM));
		  table.addCell(column);
		  
		  if(result.getAnanymousDensity()!=null){
		  for(VoterDensityWithPartyVO temp4:result.getAnanymousDensity()){
			  column = new PdfPCell(new Phrase(temp4.getCount().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
		  }
		  }
		  float[] widths=new float[] {1.5f,1.0f,0.8f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f};
		  table.setWidths(widths);
		  document.add(table);
		  document.add(Chunk.NEWLINE);
		  document.add(new Paragraph(result.getInformation2(),TBCELL));
		  document.add(Chunk.NEWLINE);
		  
		//  document.close();
		  
		  }catch (Exception e) {
			  LOG.debug("Exception Raised while GENERATING PDF in Density Block" ,e);

		}
		  
	  }
	  
	  public void generatePDFForAssuredTargetVotersBlock(AssumptionsVO result,Document document){
		  LOG.debug("Entered Into generatePDFForAssuredTargetVotersBlock For GENERATING PDF");
		  
		 // result=votersAssumptionsService(232l,85l,46l,10l,54l);
		  
		 // Document document = new Document();
		  try {
			//PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(IConstants.pdfLocalPath));
		
			
		  //document.open();
	  
		  //Font TITLE = new Font(Font.FontFamily.TIMES_ROMAN, 9,Font.BOLD);
		  //TITLE.setColor(BaseColor.BLACK);
		  
		  //Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
		  //Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	  
		  //Font SMALLFONT_WIN=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
		  //SMALLFONT_WIN.setColor(BaseColor.GREEN);

		  //Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
		  //subHeading.setColor(BaseColor.MAGENTA);
			  
		  calibriBold.setColor(BaseColor.BLACK);
		  Font TBCELL = new Font(calibriBold);
		  Font TBCELLSM = new Font(calibriBold);
		  TITLE.setColor(subHeading);
		  document.add(new Paragraph("Assumptions",TITLE));	
		  TITLE.setColor(BaseColor.BLACK);
		  document.add(Chunk.NEWLINE);
		  
		  
		  document.add(new Paragraph(result.getHeading1(),SMALLFONT));
		  document.add(Chunk.NEWLINE);			  		  
		  document.add(new Phrase("Contending Party's - ",SMALLFONT));
		  document.add(new Phrase(" 3 Major Parties (TDP, INC and YSRCP)",calibriBold));	  
		  document.add(Chunk.NEWLINE);
		  document.add(Chunk.NEWLINE);
		  document.add(new Paragraph(result.getHeading3(),SMALLFONT));		  
		  
		  document.add(new Phrase(""));			  
		  PdfPTable table = new PdfPTable(2);
		  table.setWidthPercentage(69);
		  table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		  
		  PdfPCell column=null;
		 		  
		  column = new PdfPCell(new Phrase("Current Total Voters",TBCELL));
		  column.setPadding(3);
		  column.setBackgroundColor(bcolor);
		  
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setPadding(3);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase(result.getTotalVoters().toString(),TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setPadding(3);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Expected Polling Percentage",TBCELL));
		  column.setPadding(3);
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase(result.getExpPerc().toString()+" %",TBCELL));
		  column.setPadding(3);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Targeted Polled Votes",TBCELL));
		  column.setPadding(3);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase(result.getTargetedPolledVotees().toString(),TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setPadding(3);
		  table.addCell(column);
		  float[] widths1=new float[] {1.0f,0.5f};
		  table.setWidths(widths1);
		  document.add(table);
		  document.add(Chunk.NEWLINE);
		  TITLE.setColor(subHeading); 
		  document.add(new Paragraph(result.getHeading4(),TITLE));
		  TITLE.setColor(BaseColor.BLACK);
		  
		  document.add(new Paragraph(" "));		  
		  PdfPTable table1 = new PdfPTable(2);
		  table.setWidthPercentage(60);
		  table1.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);	  
		  column = new PdfPCell(new Phrase("Targeted Votes for TDP in 2014                                                                            ("+result.getTdpPer()+" % Votes Share in Polled Votes)",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setPadding(10);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase(result.getTargetedVotesForTDP().toString(),TBCELL));
		  column.setPadding(10);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Additional Votes("+result.getAddtionalPerc()+" %) Required for TDP                                                                  ( After removing the Assured ("+result.getAssuredPer()+" %) voters Base )",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setPadding(10);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase(result.getAddtionalVoters().toString(),TBCELL));
		  column.setPadding(10);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  float[] widths=new float[] {1.5f,0.5f};
		  table1.setWidths(widths);
		  document.add(table1);
		  document.add(Chunk.NEWLINE);
		  
		//  document.close();
		  
		  }catch (Exception e) {
			  LOG.debug("Exception Raised while GENERATING PDF in generatePDFForAssuredTargetVotersBlock Block",e);

		}
		  
	  }
	  
	  public void generatePDFForDelimitationEffect(DelimitationEffectVO result,Document document){
		  LOG.debug("Entered Into generatePDFForDelimitationEffect For GENERATING PDF");
		  
		 // DelimitationEffectVO result=getDelimationEffectOnConstituency(159l,882l);
		  
		//  Document document = new Document();
		  try {
			//PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(IConstants.pdfLocalPath));
		
			
		 // document.open();
	  
		 // Font TITLE = new Font(Font.FontFamily.TIMES_ROMAN, 9,Font.BOLD);
		 // TITLE.setColor(BaseColor.BLACK);
		  
		 // Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
		 // BIGFONT.setColor(new BaseColor(141,180,226));
		 // Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	  
		 // Font SMALLFONT_WIN=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
		 // SMALLFONT_WIN.setColor(BaseColor.GREEN);

		 // Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
		 // subHeading.setColor(BaseColor.MAGENTA);
		  
		  Font TBCELL = new Font(calibriBold);
		  Font TBCELLSM = new Font(SMALLFONT);
		  
		  TITLE.setColor(subHeading);
		  document.add(new Paragraph(result.getHeading1(),TITLE));		  
		  document.add(new Phrase(""));		  
		  document.add(new Paragraph(result.getHeading2(),SMALLFONT));
		  document.add(Chunk.NEWLINE);		  
		  document.add(new Paragraph(result.getHeading3(),SMALLFONT));
		  
		  int partiesCount=result.getDelimitationEffectVO()!=null?result.getDelimitationEffectVO().size():0;
		  document.add(new Phrase(""));
		  PdfPTable table = new PdfPTable(4+partiesCount*2);
		  table.setWidthPercentage(100);
		  table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		  PdfPCell column=null;
		 		  
		  column = new PdfPCell(new Phrase("",TBCELL));
		  column.setBorder(0);
		  column.setRowspan(2);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Total Votes",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  column.setRowspan(2);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes Polled",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  column.setRowspan(2);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Poll %",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  column.setRowspan(2);
		  table.addCell(column);
		  
		  for(DelimitationEffectVO temp1:result.getDelimitationEffectVO()){
			  column = new PdfPCell(new Phrase(temp1.getPartyName(),TBCELL));
			  column.setColspan(2);
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setBackgroundColor(bcolor);
			  table.addCell(column);
		  }
		  
		  for(DelimitationEffectVO temp1:result.getDelimitationEffectVO()){
			  column = new PdfPCell(new Phrase("Votes",TBCELL));	
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setBackgroundColor(bcolor);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(" % ",TBCELL));;
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  column.setBackgroundColor(bcolor);
			  table.addCell(column);
		  }
		  
		  
		  column = new PdfPCell(new Phrase(result.getPreviousyear(),TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase(result.getPreviousCount().toString(),TBCELLSM));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase(result.getPreviousPolledVotes().toString(),TBCELLSM));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase(result.getPreviousPerc().toString(),TBCELLSM));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		  for(DelimitationEffectVO temp2:result.getDelimitationEffectVO()){
			  if(temp2.getPreviousCount()!=null && temp2.getPreviousCount() >0){
				  column = new PdfPCell(new Phrase(temp2.getPreviousCount().toString(),TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  table.addCell(column);
			  
				  column = new PdfPCell(new Phrase(temp2.getPreviousPerc().toString(),TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  table.addCell(column);
			  }else{
				  column = new PdfPCell(new Phrase(" - ",TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  table.addCell(column);
				  
				  column = new PdfPCell(new Phrase(" - ",TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  table.addCell(column);
			  }
		  }
		  
		  column = new PdfPCell(new Phrase(result.getPresentYear(),TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(BaseColor.YELLOW);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase(result.getPresentCount().toString(),TBCELLSM));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase(result.getPresentPolledVotes().toString(),TBCELLSM));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase(result.getPresentPerc().toString(),TBCELLSM));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		  for(DelimitationEffectVO temp2:result.getDelimitationEffectVO()){
			  if(temp2.getPresentCount()!=null && temp2.getPresentCount() >0){
				  column = new PdfPCell(new Phrase(temp2.getPresentCount().toString(),TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  table.addCell(column);
			  
				  column = new PdfPCell(new Phrase(temp2.getPresentPerc().toString(),TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  table.addCell(column);
			  }else{
				  column = new PdfPCell(new Phrase(" - ",TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  table.addCell(column);
				  
				  column = new PdfPCell(new Phrase(" - ",TBCELLSM));
				  column.setHorizontalAlignment(Element.ALIGN_CENTER);
				  table.addCell(column);
			  }
		  }
		  
		  
		  document.add(table);
		  document.add(Chunk.NEWLINE);
		  document.add(new Paragraph("We have considered 2009 Delimitation area results for comparison",SMALLFONT));
		  document.add(Chunk.NEWLINE);
		  
		  //document.close();
		  
		  }catch (Exception e) {
			  LOG.debug("Exception Raised while GENERATING PDF in generatePDFForDelimitationEffect Block" ,e);

		}
		  
	  }
	  public void generateBoothWiseAddedDeletedVoters(List<AgeRangeVO> result,Document document){
		  LOG.debug("Entered Into BoothWiseAddedDeletedVoters For GENERATING PDF");
		  
		 // Document document = new Document();
		  try {
			//PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(IConstants.pdfLocalPath));
		
			
		 // document.open();
		  Font calibriBold = FontFactory.getFont("Calibri",8,Font.BOLD);
		  //Font TITLE = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
		  TITLE.setColor(BaseColor.BLACK);
		  
		  Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
		  //Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
		  
		  Font TBCELL = new Font(calibriBold);
		  Font TBCELLSM = new Font(SMALLFONT);
	  
		  Font SMALLFONT_WIN=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
		  SMALLFONT_WIN.setColor(BaseColor.GREEN);

		  Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
		  subHeading.setColor(BaseColor.MAGENTA);
		  
		  document.add(new Paragraph(result.get(0).getMainHeading1(),TITLE));
		  
		  document.add(new Phrase(""));		  
		  PdfPTable table = new PdfPTable(17);
		  table.setWidthPercentage(100);
		  table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		  PdfPCell column=null;
		  column = new PdfPCell(new Phrase("PANCHAYAT",TBCELL));
		  column.setPaddingTop(12);
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setRowspan(3);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("BOOTH",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setPaddingTop(12);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setRowspan(3);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("TOTAL VOTES",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setPaddingTop(7);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setRowspan(3);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("ADDED",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(14);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Total Added",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Young Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("18-25",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table.addCell(column);

		  column = new PdfPCell(new Phrase("26-35",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("36-45",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("46-60",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table.addCell(column);

		  column = new PdfPCell(new Phrase("Above 60",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setBackgroundColor(bcolor);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table.addCell(column);
		  
		 int count=0;
		
		  for(AgeRangeVO param:result){
			  column = new PdfPCell(new Phrase(param.getPanchayat(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_LEFT);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getHamlet(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getTotalVotersInBooth().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getTotalVotersAdded().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase(param.getTotalVotersAddedPer(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getYoungVoters().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase(param.getYoungVotersPer().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getAge18To25().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase(param.getAge18To25Per(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getAge26to35().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase(param.getAge26to35Per().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getAge36to45().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase(param.getAge36to45Per().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getAge46to60().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase(param.getAge46to60Per(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getAbove60().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  column = new PdfPCell(new Phrase(param.getAbove60Per().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table.addCell(column);
			  
			  count++;
			  if(count==15)
				  break;
		  }
		  count=0;
		  float[] widths=new float[] {8.0f,4.0f,4.0f,3f,3.4f,3f,3.4f,3f,3.4f,3f,3.4f,3f,3.4f,3f,3.4f,3f,3.4f};
		  table.setWidths(widths);
		  document.add(table);
		  document.add(Chunk.NEWLINE);
		  
		  document.add(Chunk.NEXTPAGE);
		  document.add(new Paragraph(result.get(0).getMainHeading2(),TITLE));
		  
		  document.add(new Phrase(""));	
		  PdfPTable table1 = new PdfPTable(17);
		  table1.setWidthPercentage(100);
		  table1.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		  column = new PdfPCell(new Phrase("PANCHAYAT",TBCELL));
		  column.setPaddingTop(12);
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setRowspan(3);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("BOOTH",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setPaddingTop(12);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setRowspan(3);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("TOTAL VOTES",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setPaddingTop(7);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setRowspan(3);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("DELETED",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(14);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Total Deleted",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Young Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("18-25",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table1.addCell(column);

		  column = new PdfPCell(new Phrase("26-35",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("36-45",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("46-60",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table1.addCell(column);

		  column = new PdfPCell(new Phrase("Above 60",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  column.setColspan(2);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  
		  column = new PdfPCell(new Phrase("Votes",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  column = new PdfPCell(new Phrase("%",TBCELL));
		  column.setBackgroundColor(bcolor);
		  column.setHorizontalAlignment(Element.ALIGN_CENTER);
		  table1.addCell(column);
		  
		  
		  for(AgeRangeVO param:result){
			  column = new PdfPCell(new Phrase(param.getPanchayat(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_LEFT);
			  table1.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getHamlet(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getTotalVotersInBooth().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getTotalVotersDeleted().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  column = new PdfPCell(new Phrase(param.getTotalVotersDeletedPer(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getDelYoungVoters().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  column = new PdfPCell(new Phrase(param.getDelYoungVotersPer().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getDelAge18To25().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  column = new PdfPCell(new Phrase(param.getDelAge18To25Per(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getAge26to35().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  column = new PdfPCell(new Phrase(param.getAge26to35Per().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getDelAge36to45().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  column = new PdfPCell(new Phrase(param.getDelAge36to45Per().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getDelAge46to60().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  column = new PdfPCell(new Phrase(param.getDelAge46to60Per(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  
			  column = new PdfPCell(new Phrase(param.getDelAbove60().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  column = new PdfPCell(new Phrase(param.getDelAbove60Per().toString(),TBCELLSM));
			  column.setHorizontalAlignment(Element.ALIGN_CENTER);
			  table1.addCell(column);
			  
			  count++;
			  if(count==15)
				  break;
		  }

		  table1.setWidths(widths);
		  document.add(table1);
		 // document.close();
		  } catch (Exception e) {
				LOG.error("Exception Occured While Generating PDF for BoothWiseAddedDeletedVoters",e);

			} 
	  }
	  
	  public void getPDFForSubLevelAddedDeleted(VoterModificationVO resultvo, Document document ){
		  LOG.debug("Entered Into getPDFForSubLevelAddedDeleted For GENERATING PDF");
		  
		  //Document document = new Document();
		  try {
			  
			  
			//PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(IConstants.pdfLocalPath));
		
			//document.open();
	  
			//Font TITLE = new Font(Font.FontFamily.TIMES_ROMAN, 9,Font.BOLD);
			TITLE.setColor(subHeading);
		  
			Font BIGFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
			/*Font SMALLFONT = new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
	  */
			Font SMALLFONT_WIN=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
			SMALLFONT_WIN.setColor(BaseColor.GREEN);

			Font subHeading = new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
			subHeading.setColor(BaseColor.MAGENTA);
			
			 Font TBCELL = SMALLFONT;
			 Font TBCELLSM =SMALLFONT;
			 //Font TBCELLSM = new Font(Font.FontFamily.TIMES_ROMAN,7,Font.NORMAL);
		  
			List<SelectOptionVO> publications=resultvo.getModifiedVotersList().get(0).getSelectOptionVOsList();
			
			
			document.add(new Paragraph(resultvo.getMainHeading(),TITLE));
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(9);
			 table.setWidthPercentage(100);
			
			PdfPCell column=null;
			
			column = new PdfPCell(new Phrase("             Mandal",BIGFONT));
			column.setRowspan(2);
			column.setBackgroundColor(bcolor);
			column.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(column);
			
			column = new PdfPCell(new Phrase("Voters",BIGFONT));
			column.setColspan(2);
			column.setBackgroundColor(bcolor);
			column.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(column);
			
			column = new PdfPCell(new Phrase("Total Voters",BIGFONT));
			column.setColspan(2);
			column.setBackgroundColor(bcolor);
			column.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(column);
			
			column = new PdfPCell(new Phrase("Male Voters",BIGFONT));
			column.setColspan(2);
			column.setBackgroundColor(bcolor);
			column.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(column);
			
			column = new PdfPCell(new Phrase("Female Voters",BIGFONT));
			column.setColspan(2);
			column.setBackgroundColor(bcolor);
			column.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(column);
			
			for(SelectOptionVO temp:publications){
				column = new PdfPCell(new Phrase(temp.getName(),BIGFONT));
				column.setBackgroundColor(bcolor);
				column.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(column);
			}
			
			column = new PdfPCell(new Phrase("Added",BIGFONT));
			column.setBackgroundColor(bcolor);
			column.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(column);
			column = new PdfPCell(new Phrase("Deleted",BIGFONT));
			column.setBackgroundColor(bcolor);
			column.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(column);
			
			column = new PdfPCell(new Phrase("Added",BIGFONT));
			column.setBackgroundColor(bcolor);
			column.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(column);
			column = new PdfPCell(new Phrase("Deleted",BIGFONT));
			column.setBackgroundColor(bcolor);
			column.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(column);
			
			column = new PdfPCell(new Phrase("Added",BIGFONT));
			column.setBackgroundColor(bcolor);
			column.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(column);
			column = new PdfPCell(new Phrase("Deleted",BIGFONT));
			column.setBackgroundColor(bcolor);
			column.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(column);
			
			for(VoterModificationVO temp1:resultvo.getModifiedVotersList()){
				
				List<SelectOptionVO> publicationsList=temp1.getSelectOptionVOsList();
				
				column = new PdfPCell(new Phrase(temp1.getName(),TBCELLSM));
				column.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(column);
			
			
			for(SelectOptionVO temp2:publicationsList){
				column = new PdfPCell(new Phrase(temp2.getId().toString(),TBCELLSM));
				column.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(column);
			}
			
			
				column = new PdfPCell(new Phrase(temp1.getAddedCount().toString(),TBCELLSM));
				column.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(column);
				
				column = new PdfPCell(new Phrase(temp1.getDeletedCount().toString(),TBCELLSM));
				column.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(column);
				
				column = new PdfPCell(new Phrase(temp1.getMaleVotersAdded().toString(),TBCELLSM));
				column.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(column);
				
				column = new PdfPCell(new Phrase(temp1.getMaleVotersDeleted().toString(),TBCELLSM));
				column.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(column);
				
				column = new PdfPCell(new Phrase(temp1.getFemaleVotersAdded().toString(),TBCELLSM));
				column.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(column);
				
				column = new PdfPCell(new Phrase(temp1.getFemaleVotersDeleted().toString(),TBCELLSM));
				column.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(column);
			}
			
			document.add(table);
			//document.close();
			
			
		  
		  	}catch (Exception e) {
			  LOG.error("Exception Occured While Generating PDF for BoothWiseAddedDeletedVoters",e);
		  	}  
	  }
	 
	  public ResultStatus getRecordsCountToCasteContainConsti(Long constituencyId)
	  {
		  ResultStatus resultStatus = new ResultStatus();
		  try{
				  if(constituencyId != null && constituencyId > 0)
				  {
				 Long count =  casteContainConstituencyDAO.getRecordsCountToCasteContainConsti(constituencyId);
				  if(count > 0)
				  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				  else
					  resultStatus.setResultCode(ResultCodeMapper.FAILURE);  
				  }
				 
		  	}
		  catch (Exception e) {
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  e.printStackTrace();
			
		  }
		return resultStatus;
	}

	public void buildAutoStrategy(Long constituencyId) {
		// TODO Auto-generated method stub
		
	}

	public Long getConstituencyNo(Long constituencyId) {
		// TODO Auto-generated method stub
		return null;
	}
	
		public static Comparator<PartyResultsVO> sortByVotesEarned = new Comparator<PartyResultsVO>()
            {
          
                public int compare(PartyResultsVO resultList1, PartyResultsVO resultList2)
                {
                    if(resultList1.getVotesEarned() == null || resultList2.getVotesEarned() == null){
                        return 0;
                    }
                    else{
                    	return (resultList1.getVotesEarned()).compareTo(resultList2.getVotesEarned());
                    }
                }
            };
			
	  
}
