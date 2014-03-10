<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style>
		.m-top25{margin-top:25px;}
		#constituencyId{width:200px;}
		.resultDiv table th{text-align:center;}
		.resultDiv {margin-left:-100px;}
		
		.resultDiv1 table th{text-align:center;}
		.resultDiv1 {margin-left:-100px;}
		
		.partyStrengths table th{text-align:center;}
		.partyStrengths {margin-left:-100px;}
	</style>
</head>
<body>

 <div class="container">
	<div class="m-top25" style="width:500px;margin-left:auto;margin-right:auto;">
	   <span style="font-weight:bold;font-size:16px;">Select Constituency</span> <s:select theme="simple" name="constituency" id="constituencyId" list="constituenciesList" listKey="id" listValue="name"/>
       <div><input type="button" class="btn btn-primary" onClick="getReport();" value="Get Stratagic Report"></input></div>
	</div>
	
	<div class="prevoiusElectionTrendsDiv"></div>
	<div class="prevoiusElectionTrendsParliamentDiv"></div>
	<div class="addedDeletedDiv" style="display:none;"></div>
	<div class="resultDiv" style="display:none;"></div>
	<div class="partyStrengths" style="display:none;"></div>
	<div class="resultDiv1" style="display:none;"></div>
	
</div>
 

<div style="margin-left:252px;">
 <input type="button" value="Get Mandal Wise CasteInfo" class="btn" id="mandalcasteInfobtn" onclick="redirectToCasteInfo('mandal')" style="margin-top:10px;"/>
 <input type="button" value="Get Panchayat Wise CasteInfo" class="btn" id="panchayatcasteInfobtn" onclick="redirectToCasteInfo('panchayat')" style="margin-top:10px;"/>
 <input type="button" value="Get Booth Wise CasteInfo" class="btn" id="boothcasteInfobtn" onclick="redirectToCasteInfo('booth')" style="margin-top:10px;"/>
  <input type="button" value="Get Hamlet Wise CasteInfo" class="btn" id="hamletcasteInfobtn" onclick="redirectToCasteInfo('hamlet')" style="margin-top:10px;"/>
  
</div>


<script>
	function getReport(){
		getBoothWiseAddedAndDeletedVoters();
		getPreviousTrendsReport();
		getPreviousTrendsReportParliament();
		getMptcZptcResultsOfConstituency();
		
	}
	function getPreviousTrendsReport()
	 {
		var constituencyDetails={constituencyId:""};
		constituencyDetails.constituencyId=$("#constituencyId").val();
		
		$.ajax({
	          type:'POST',
	          url: 'getPreviousTrendsReportAction.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(constituencyDetails)},
	     	  }).done(function(result){ 
				  $("#ajaxLoad").css("display","none");
				if(result != null){
					buildPrevoiusElectionTrendsDiv(result);
				}
		   });
	 }
	function getPreviousTrendsReportParliament()
	 {
		var constituencyDetails={constituencyId:""};
		constituencyDetails.constituencyId=$("#constituencyId").val();
		
		$.ajax({
	          type:'POST',
	          url: 'getPreviousTrendsReportParliamentAction.action',
	          dataType: 'json',
	          data: {task:JSON.stringify(constituencyDetails)},
	     	  }).done(function(result){ 
				  $("#ajaxLoad").css("display","none");
				if(result != null){
					buildprevoiusElectionTrendsParliamentDiv(result);
				}
		   });
	 }
 function getBoothWiseAddedAndDeletedVoters()
 {
	var constituencyDetails={constituencyId:""};
	constituencyDetails.constituencyId=$("#constituencyId").val();
	
	$.ajax({
          type:'POST',
          url: 'getBoothWiseAddedAndDeletedVotersStratagicAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			  $("#ajaxLoad").css("display","none");
			if(result != null){
				buildBoothWiseAddedAndDeletedVoters(result);
			}
	   });
 }
 
 function buildBoothWiseAddedAndDeletedVoters(results){
	
	$(".addedDeletedDiv").css("display","block");
	
 
	var str="";
	str+="<h4 style='text-align:center;'> Booth Wise ADDED Voters</h4>";
	str+="<table class='table table-bordered' style='font-size:16px;'>";
	str+="<thead>";
	str+="<tr><th rowspan=3>Panchayat</th><th rowspan=3>Booth</th><th rowspan=3>Total Voters</th><th colspan=14>Added</th></tr>";
	str+="<tr>";
		str+="<th colspan=2>Total Added</th><th colspan=2>Young Voters</th><th colspan=2>18-25</th><th colspan=2>26-35</th><th colspan=2>36-45</th><th colspan=2>46-60</th><th colspan=2>Above 60</th>";
	str+"</tr>";
	str+="<tr>";
		str+="<th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th>";
	str+"</tr>";
	str+="</thead>";
	str+="<tbody>";
		for(var i in results){
			str+="<tr>";
			str+="<td>"+results[i].panchayat+"</td><td>Booth-"+results[i].hamlet+"</td><td>"+results[i].totalVotersInBooth+"</td>";
			str+="<td>"+results[i].totalVotersAdded+"</td><td>"+results[i].totalVotersAddedPer+"</td><td>"+results[i].youngVoters+"</td><td>"+results[i].youngVotersPer+"</td><td>"+results[i].age18To25+"</td><td>"+results[i].age18To25Per+"</td><td>"+results[i].age26to35+"</td><td>"+results[i].age26to35Per+"</td><td>"+results[i].age36to45+"</td><td>"+results[i].age36to45Per+"</td><td>"+results[i].age46to60+"</td><td>"+results[i].age46to60Per+"</td><td>"+results[i].above60+"</td><td>"+results[i].above60Per+"</td>";
			str+="</tr>";
		}
	str+="</tbody>";
	str+="</table>";
	
	
	str+="<h4 style='text-align:center;'> Booth Wise DELETED Voters</h4>";
	str+="<table class='table table-bordered' style='font-size:16px;'>";
	str+="<thead>";
	str+="<tr><th rowspan=3>Panchayat</th><th rowspan=3>Booth</th><th rowspan=3>Total Voters</th><th colspan=14>Deleted</th></tr>"
	str+="<tr>";
		str+="<th colspan=2>Total Deleted</th><th colspan=2>Young Voters</th><th colspan=2>18-25</th><th colspan=2>26-35</th><th colspan=2>36-45</th><th colspan=2>46-60</th><th colspan=2>Above 60</th>";
	str+"</tr>";
	str+="<tr>";
		str+="<th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th>";
	str+"</tr>";
	str+="</thead>";
	str+="<tbody>";
		for(var i in results){
			
			str+="<tr>";
			str+="<td>"+results[i].panchayat+"</td><td>Booth-"+results[i].hamlet+"</td><td>"+results[i].totalVotersInBooth+"</td>";
			str+="<td>"+results[i].totalVotersDeleted+"</td><td>"+results[i].totalVotersDeletedPer+"</td><td>"+results[i].delYoungVoters+"</td><td>"+results[i].delYoungVotersPer+"</td><td>"+results[i].delAge18To25+"</td><td>"+results[i].delAge18To25Per+"</td><td>"+results[i].delAge26to35+"</td><td>"+results[i].delAge26to35Per+"</td><td>"+results[i].delAge36to45+"</td><td>"+results[i].delAge36to45Per+"</td><td>"+results[i].delAge46to60+"</td><td>"+results[i].delAge46to60Per+"</td><td>"+results[i].delAbove60+"</td><td>"+results[i].delAbove60Per+"</td>";
			str+="</tr>";
		}
	str+="</tbody>";
	str+="</table>";
	
	$(".addedDeletedDiv").html(str);
 }
 

 function buildPrevoiusElectionTrendsDiv(results){
		$(".prevoiusElectionTrendsDiv").html("");
	 
		var str="";
		str+="<h4 style='text-align:center;'>Assembly Constituency Wise Result</h4>";
		str+="<table class='table table-bordered' style='font-size:16px;'>";
		str+="<thead>";
	//	str+="<tr><th rowspan=3>Panchayat</th><th rowspan=3>Booth</th><th rowspan=3>Total Voters</th><th colspan=14>Added</th></tr>";
	//	str+="<tr>";
	//		str+="<th colspan=2>Total Added</th><th colspan=2>Young Voters</th><th colspan=2>18-25</th><th colspan=2>26-35</th><th colspan=2>36-45</th><th colspan=2>46-60</th><th colspan=2>Above 60</th>";
			//str+="<th colspan=2>Total Deleted</th><th colspan=2>Young Voters</th><th colspan=2>18-25</th><th colspan=2>26-35</th><th colspan=2>36-45</th><th colspan=2>46-60</th><th colspan=2>Above 60</th>";
		//str+"</tr>";
		str+="<tr>";
			str+="<th>Ranks</th><th>Year</th><th>Total Voters</th><th>Votes Polled</th><th>TDP</th><th>% Votes(TDP)</th><th>Margin Votes(%)</th><th>INC</th><th>% Votes(INC)</th><th>PRP/YSRCP</th><th>% Votes(PRP/YSRCP)</th><th>OTHERS</th><th>% votes (OTHERS)</th>";
			//str+="<th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th>";
		str+"</tr>";
		str+="</thead>";
		str+="<tbody>";
			for(var i in results){
				
				if(results[i].tdpVo !=null){
				var  tdpRank = parseInt(results[i].tdpVo.rank);
				var tdpPer="";
				 if(tdpRank ==1)
				 tdpPer=results[i].tdpVo.marginVotes+"("+results[i].tdpVo.marginVotesPercentage+"%)";
				 else
				 tdpPer="-"+results[i].tdpVo.marginVotes+"(-"+results[i].tdpVo.marginVotesPercentage+"%)";
				var  incRank = parseInt(results[i].incVo.rank);
				var  prpRank = parseInt(results[i].prpVo.rank);
				var  othersRank = parseInt(results[i].othersVo.rank);
				
				str+="<tr>";
				//str+="<td>"+results[i].panchayat+"</td><td>Booth-"+results[i].hamlet+"</td><td>"+results[i].totalVotersInBooth+"</td>";
				str+="<td>TDP Rank-"+tdpRank+"  INC rank-"+incRank+" PRP/YSRCPrank-"+prpRank+" Others Rank-"+othersRank+"</td><td>"+results[i].electionYear+"</td><td>"+results[i].totalVoters+"</td><td>"+results[i].totalVotesPolled+"</td><td>"+results[i].tdpVo.votesEarned+"</td><td>"+results[i].tdpVo.percentage+"</td><td>"+tdpPer+"</td><td>"+results[i].incVo.votesEarned+"</td><td>"+results[i].incVo.percentage+"</td><td>"+results[i].prpVo.votesEarned+"</td><td>"+results[i].prpVo.percentage+"</td><td>"+results[i].othersVo.votesEarned+"</td><td>"+results[i].othersVo.percentage+"</td>";
				//str+="<td>"+results[i].totalVotersDeleted+"</td><td>"+results[i].totalVotersDeletedPer+"</td><td>"+results[i].delYoungVoters+"</td><td>"+results[i].delYoungVotersPer+"</td><td>"+results[i].delAge18To25+"</td><td>"+results[i].delAge18To25Per+"</td><td>"+results[i].delAge26to35+"</td><td>"+results[i].delAge26to35Per+"</td><td>"+results[i].delAge36to45+"</td><td>"+results[i].delAge36to45Per+"</td><td>"+results[i].delAge46to60+"</td><td>"+results[i].delAge46to60Per+"</td><td>"+results[i].delAbove60+"</td><td>"+results[i].delAbove60Per+"</td>";
				//str+="<th>Panchayat</th><th>Booth</th><th>Young Voters</th><th>18 - 25</th><th>26 - 35</th><th>36 - 45</th><th>46 - 60</th>
				//"<th>Above 60</th><th>Young Voters</th><th>18 - 25</th><th>26 - 35</th><th>36 - 45</th><th>46 - 60</th><th>Above 60</th>";
				str+="</tr>";
			}
			}
		str+="</tbody>";
		str+="</table>";
		
		
		
		
		$(".prevoiusElectionTrendsDiv").html(str);
	 }
 function buildprevoiusElectionTrendsParliamentDiv(results){
		$(".prevoiusElectionTrendsParliamentDiv").html("");
	 
		var str="";
		str+="<h4 style='text-align:center;'>Parliament Constituency Wise Result</h4>";
		str+="<table class='table table-bordered' style='font-size:16px;'>";
		str+="<thead>";
	//	str+="<tr><th rowspan=3>Panchayat</th><th rowspan=3>Booth</th><th rowspan=3>Total Voters</th><th colspan=14>Added</th></tr>";
	//	str+="<tr>";
	//		str+="<th colspan=2>Total Added</th><th colspan=2>Young Voters</th><th colspan=2>18-25</th><th colspan=2>26-35</th><th colspan=2>36-45</th><th colspan=2>46-60</th><th colspan=2>Above 60</th>";
			//str+="<th colspan=2>Total Deleted</th><th colspan=2>Young Voters</th><th colspan=2>18-25</th><th colspan=2>26-35</th><th colspan=2>36-45</th><th colspan=2>46-60</th><th colspan=2>Above 60</th>";
		//str+"</tr>";
		str+="<tr>";
			str+="<th>Year</th><th>Total Voters</th><th>Votes Polled</th><th>TDP</th><th>% Votes(TDP)</th><th>Margin Votes(%)</th><th>INC</th><th>% Votes(INC)</th><th>PRP/YSRCP</th><th>% Votes(PRP/YSRCP)</th><th>OTHERS</th><th>% votes(OTHERS)</th>";
			//str+="<th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th>";
		str+"</tr>";
		str+="</thead>";
		str+="<tbody>";
			for(var i in results){
				if(results[i].tdpVo !=null){
					if(results[i].tdpVo !=null){
				str+="<tr>";
				//str+="<td>"+results[i].panchayat+"</td><td>Booth-"+results[i].hamlet+"</td><td>"+results[i].totalVotersInBooth+"</td>";
				str+="<td>"+results[i].electionYear+"</td><td>"+results[i].totalVoters+"</td><td>"+results[i].totalVotesPolled+"</td><td>"+results[i].tdpVo.votesEarned+"</td><td>"+results[i].tdpVo.percentage+"</td><td>"+results[i].tdpVo.marginVotes+"("+results[i].tdpVo.marginVotesPercentage+"%)</td><td>"+results[i].incVo.votesEarned+"</td><td>"+results[i].incVo.percentage+"</td><td>"+results[i].prpVo.votesEarned+"</td><td>"+results[i].prpVo.percentage+"</td><td>"+results[i].othersVo.votesEarned+"</td><td>"+results[i].othersVo.percentage+"</td>";
				//str+="<td>"+results[i].totalVotersDeleted+"</td><td>"+results[i].totalVotersDeletedPer+"</td><td>"+results[i].delYoungVoters+"</td><td>"+results[i].delYoungVotersPer+"</td><td>"+results[i].delAge18To25+"</td><td>"+results[i].delAge18To25Per+"</td><td>"+results[i].delAge26to35+"</td><td>"+results[i].delAge26to35Per+"</td><td>"+results[i].delAge36to45+"</td><td>"+results[i].delAge36to45Per+"</td><td>"+results[i].delAge46to60+"</td><td>"+results[i].delAge46to60Per+"</td><td>"+results[i].delAbove60+"</td><td>"+results[i].delAbove60Per+"</td>";
				//str+="<th>Panchayat</th><th>Booth</th><th>Young Voters</th><th>18 - 25</th><th>26 - 35</th><th>36 - 45</th><th>46 - 60</th>
				//"<th>Above 60</th><th>Young Voters</th><th>18 - 25</th><th>26 - 35</th><th>36 - 45</th><th>46 - 60</th><th>Above 60</th>";
				str+="</tr>";
					}
				}
			}
		str+="</tbody>";
		str+="</table>";
		$(".prevoiusElectionTrendsParliamentDiv").html(str);
	 }
	 
	 function getMptcZptcResultsOfConstituency()
	{
		var constituencyDetails={constituencyId:""};
		constituencyDetails.constituencyId=$("#constituencyId").val();
	
		$.ajax({
			type:'POST',
			url: 'getZptcMptcResultsOfConstituencyAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(constituencyDetails)},
			}).done(function(myResults){ 
				$("#ajaxLoad").css("display","none");
				if(myResults != null){
					buildMptcZptcResults(myResults);
					buildMuncipalCorpResults(myResults);
				}
		});
	}
	
	function buildMptcZptcResults(myResults){	
	
	$(".resultDiv").css("display","block");
	
	var str="";
	str+="<h4 style='text-align:center;'> ZPTC & MPTC Results </h4>";
	str+="<table class='table table-bordered' style='font-size:16px;'>";
	str+="<thead>";
	str+="<tr><th>YEAR </th><th>Total Voters</th><th>Votes Polled</th><th>TDP</th><th>%</th><th>MARGIN (%)</th><th>INC</th><th>%</th><th>TRS</th><th>%</th><th>OTHERS</th><th>%</th></tr>";
	str+="</thead>";
	str+="<tbody>";
	
	var results=myResults.partyResultsVOList;
		for(var i in results){
			str+="<tr>";
				str+="<tr><td>"+results[i].year+"-"+results[i].electionName+"</td><td>--</td><td>"+results[i].validVotes+"</td>";
					
					var tdp_exist=false;
					var trs_exist=false;
					var inc_exist=false;
				
					for(var j=0;j<results[i].partyResultsVOList.length;j++){
						if(results[i].partyResultsVOList[j].partyId==872){
							if(results[i].partyResultsVOList[j].rank!=null){
								str+="<td style='background:#CBE699;'>"+results[i].partyResultsVOList[j].votesEarned+"</td><td>"+results[i].partyResultsVOList[j].diffPercent+"</td>";
							}else{
								str+="<td style='background:#F7DFAF;'>"+results[i].partyResultsVOList[j].votesEarned+"</td><td>"+results[i].partyResultsVOList[j].diffPercent+"</td>";
							}
							
							tdp_exist=true;
						}
					}
					if(!tdp_exist){
						str+="<td> -- </td><td> -- </td>";
					}
					str+="<td>"+results[i].marginVotes+"("+results[i].marginPercent+")</td>";
					
					
					
					for(var j=0;j<results[i].partyResultsVOList.length;j++){
						if(results[i].partyResultsVOList[j].partyId==362){
							if(results[i].partyResultsVOList[j].rank!=null){
								str+="<td style='background:#CBE699;'>"+results[i].partyResultsVOList[j].votesEarned+"</td><td>"+results[i].partyResultsVOList[j].diffPercent+"</td>";
							}else{
								str+="<td>"+results[i].partyResultsVOList[j].votesEarned+"</td><td>"+results[i].partyResultsVOList[j].diffPercent+"</td>";
							}
							inc_exist=true;
						}
					}
					
					if(!inc_exist){
						str+="<td> -- </td><td> -- </td>";
					}
					for(var j=0;j<results[i].partyResultsVOList.length;j++){
						if(results[i].partyResultsVOList[j].partyId==886){
							if(results[i].partyResultsVOList[j].rank!=null){
								str+="<td style='background:#CBE699;'>"+results[i].partyResultsVOList[j].votesEarned+"</td><td>"+results[i].partyResultsVOList[j].diffPercent+"</td>";
							}else{
								str+="<td>"+results[i].partyResultsVOList[j].votesEarned+"</td><td>"+results[i].partyResultsVOList[j].diffPercent+"</td>";
							}
							trs_exist=true;
						}
					}
					
					if(!trs_exist){
						str+="<td> -- </td><td> -- </td>";
					}
					if(results[i].otherVotes!=null){
						str+="<td>"+results[i].otherVotes+"</td><td>"+results[i].otherVotesPercent+"</td>";
					}else{
						str+="<td> -- </td><td> -- </td>";
					}
					
			str+="</tr>";
		}
	str+="</tbody>";
	str+="</table>";
	
	$(".resultDiv").html(str);
	}
	function buildMuncipalCorpResults(myResults){	
	if(myResults.muncipalCorpResults!=null || myResults.gmcResults !=null){
	$(".resultDiv1").css("display","block");
	$(".partyStrengths").css("display","block");
	
	
	var str="";
	str+="<h4 style='text-align:center;'> Muncipal/Corporation Results </h4>";
	str+="<table class='table table-bordered' style='font-size:16px;'>";
	str+="<thead>";
	str+="<tr><th> Location </th><th>Total Voters</th><th>Votes Polled</th><th>TDP</th><th>INC</th><th>BJP</th><th>TRS</th><th>Others</th>";
	//str+="<th>TRS</th><th>%</th><th>OTHERS</th><th>%</th></tr>";
	str+="</thead>";
	str+="<tbody>";
	if(myResults.muncipalCorpResults!=null){
		var results=myResults.muncipalCorpResults;
	}else if(myResults.gmcResults!=null){
		var results=myResults.gmcResults;
	}
	
		for(var i in results){
			str+="<tr>";
				str+="<tr><td>"+results[i].location+"</td>";
				str+="<td>"+results[i].votesPolled+"</td>";
				str+="<td>"+results[i].validVotes+"</td>";
					
					var tdp_exist=false;
					var trs_exist=false;
					var inc_exist=false;
					var bjp_exist=false;
					var ranked=false;
				
					for(var j=0;j<results[i].partyResultsVOList.length;j++){
						if(results[i].partyResultsVOList[j].partyId==872){
							if(results[i].partyResultsVOList[j].rank==1){
								str+="<td style='background:#CBE699;'>"+results[i].partyResultsVOList[j].votesEarned+"</td>";
								ranked=true;
							}else{
								str+="<td style='background:#F7DFAF;'>"+results[i].partyResultsVOList[j].votesEarned+"</td>";
							}
							
							tdp_exist=true;
						}
					}
					if(!tdp_exist){
						str+="<td> -- </td>";
					}
					
					for(var j=0;j<results[i].partyResultsVOList.length;j++){
						if(results[i].partyResultsVOList[j].partyId==362){
							if(results[i].partyResultsVOList[j].rank==1){
								str+="<td style='background:#CBE699;'>"+results[i].partyResultsVOList[j].votesEarned+"</td>";
								ranked=true;
							}else{
								str+="<td>"+results[i].partyResultsVOList[j].votesEarned+"</td>";
							}
							inc_exist=true;
						}
					}
					
					if(!inc_exist){
						str+="<td> -- </td>";
					}
					for(var j=0;j<results[i].partyResultsVOList.length;j++){
						if(results[i].partyResultsVOList[j].partyId==163){
							if(results[i].partyResultsVOList[j].rank==1){
								str+="<td style='background:#CBE699;'>"+results[i].partyResultsVOList[j].votesEarned+"</td>";
								ranked=true;
							}else{
								str+="<td>"+results[i].partyResultsVOList[j].votesEarned+"</td>";
							}
							bjp_exist=true;
						}
					}
					
					if(!bjp_exist){
						str+="<td> -- </td>";
					}
					for(var j=0;j<results[i].partyResultsVOList.length;j++){
						if(results[i].partyResultsVOList[j].partyId==886){
							if(results[i].partyResultsVOList[j].rank==1){
								str+="<td style='background:#CBE699;'>"+results[i].partyResultsVOList[j].votesEarned+"</td>";
								ranked=true;
							}else{
								str+="<td>"+results[i].partyResultsVOList[j].votesEarned+"</td>";
							}
							trs_exist=true;
						}
					}
					
					if(!trs_exist){
						str+="<td> -- </td>";
					}
					
					if(results[i].otherVotes!=null){
						if(ranked==false){
							str+="<td style='background:#CBE699;'>"+results[i].otherVotes+"</td>";
						}else{
							str+="<td>"+results[i].otherVotes+"</td>";
						}
					}else{
						str+="<td> -- </td>";
					}
			str+="</tr>";
		}
		str+="</tbody>";
		str+="</table>";
	
		$(".resultDiv1").html(str);
	
	
		if(myResults.partyStrengths!=null){
			var vresults=myResults.partyStrengths;
					var v_tdp_exist=false;
					var v_trs_exist=false;
					var v_inc_exist=false;
					var v_bjp_exist=false;
					
					
			var vstr="";
				vstr+="<h4 style='text-align:center;'> Muncipal/Corporation Party Strength </h4>";
				vstr+="<table class='table table-bordered' style='font-size:16px;'>";
				vstr+="<thead>";
					vstr+="<tr><th colspan=3>TDP</th><th colspan=3>INC</th><th colspan=3>BJP</th><th colspan=3>TRS</th><th colspan=3>OTHERS</th></tr>";
					vstr+="<tr><th>Participated</th><th>Secured</th><th>Votes %</th><th>Participated</th><th>Secured</th><th>Votes %</th><th>Participated</th><th>Secured</th><th>Votes %</th><th>Participated</th><th>Secured</th><th>Votes %</th><th>Participated</th><th>Secured</th><th>Votes %</th></tr>";
				vstr+="</thead>";
				vstr+="<tbody>";
					
				
					for(var i=0;i<vresults.length;i++){
						if(vresults[i].partyId==872){
							vstr+="<td>"+vresults[i].participated+"</td>";
							vstr+="<td>"+vresults[i].won+"</td>";
							vstr+="<td>"+vresults[i].percentage+"</td>";
							v_tdp_exist=true;
						}
					}
					if(!v_tdp_exist){
						str+="<td> - </td><td> - </td><td> - </td>";
					}
					for(var i=0;i<vresults.length;i++){
						if(vresults[i].partyId==362){
							vstr+="<td>"+vresults[i].participated+"</td>";
							vstr+="<td>"+vresults[i].won+"</td>";
							vstr+="<td>"+vresults[i].percentage+"</td>";
							v_inc_exist=true;
						}
					}
					if(!v_inc_exist){
						str+="<td> - </td><td> - </td><td> - </td>";
					}
					for(var i=0;i<vresults.length;i++){
						if(vresults[i].partyId==163){
							vstr+="<td>"+vresults[i].participated+"</td>";
							vstr+="<td>"+vresults[i].won+"</td>";
							vstr+="<td>"+vresults[i].percentage+"</td>";
							v_bjp_exist=true;
						}
					}
					if(!v_bjp_exist){
						vstr+="<td> - </td><td> - </td><td> - </td>";
					}
					
					for(var i=0;i<vresults.length;i++){
						if(vresults[i].partyId==886){
							vstr+="<td>"+vresults[i].participated+"</td>";
							vstr+="<td>"+vresults[i].won+"</td>";
							vstr+="<td>"+vresults[i].percentage+"</td>";
							v_trs_exist=true;
						}
					}
					if(!v_trs_exist){
						vstr+="<td> - </td><td> - </td><td> - </td>";
					}
					
					vstr+="<td>"+myResults.participated+"</td>";
					vstr+="<td>"+myResults.won+"</td>";
					vstr+="<td>"+myResults.otherVotesPercent+"</td>";
					
					
				vstr+="</tbody>";
				vstr+="</table>";
		}
		
		$(".partyStrengths").html(vstr);
		}
	}
		
	

function redirectToCasteInfo(type)
{
var win=window.open('casteReportAction.action?type='+type+'', '_blank');
win.focus();
}

 
 </script>
</body>
</html>