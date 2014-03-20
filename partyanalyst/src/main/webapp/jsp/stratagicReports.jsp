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
		
		.prevoiusElectionTrendsDiv {margin-left:-100px;}
	</style>
</head>
<body>

 <div class="container">
	<div class="m-top25" style="width:500px;margin-left:auto;margin-right:auto;">
	   <span style="font-weight:bold;font-size:16px;">Select Constituency</span> <s:select theme="simple" name="constituency" id="constituencyId" list="constituenciesList" listKey="id" listValue="name"/>
       <div><input type="button" class="btn btn-primary" onClick="getReport();" value="Get Stratagic Report"></input></div>
	</div>
	
	<div>
		<input type="button" value="Get Mandal Wise CasteInfo" class="btn" id="mandalcasteInfobtn" onclick="redirectToCasteInfo('mandal')" style="margin-top:10px;"/>
		<input type="button" value="Get Panchayat Wise CasteInfo" class="btn" id="panchayatcasteInfobtn" onclick="redirectToCasteInfo('panchayat')" style="margin-top:10px;"/>
		<input type="button" value="Get Booth Wise CasteInfo" class="btn" id="boothcasteInfobtn" onclick="redirectToCasteInfo('booth')" style="margin-top:10px;"/>
		<input type="button" value="Get Hamlet Wise CasteInfo" class="btn" id="hamletcasteInfobtn" onclick="redirectToCasteInfo('hamlet')" style="margin-top:10px;"/>
		<input type="button" value="Get Age Wise Report" class="btn" id="hamletcasteInfobtn" onclick="getAgeReport();" style="margin-top:10px;"/>
	</div>
	
	<div class="prevoiusElectionTrendsDiv"></div>
	<div class="prevoiusElectionTrendsParliamentDiv"></div>
	<div class="addedDeletedDiv" style="display:none;"></div>
	<div class="resultDiv" style="display:none;"></div>
	<div class="partyStrengths" style="display:none;"></div>
	<div class="resultDiv1" style="display:none;"></div>
	
</div>
 




<script>

	function getAgeReport()
	{
		window.open("gettingVotersBasedOnCasteAndAgeAction.action");
	}
	function getReport(){
		clearAllReportDivs();
		
		getVoterInfo();
		getAddedDeletedVoterInfoInALocation();
		getGenderWiseVoterModificationsBetweenPublications();
		getGenderWiseVoterModificationsForEachPublication()
		getVoterDensityReport();
		getMandalWiseVoterModificationReportOfConstituency();
		getBoothWiseAddedAndDeletedVoters();
		getPreviousTrendsReport();
		getPreviousTrendsReportParliament();
		getMptcZptcResultsOfConstituency();
		
	}
	
	function clearAllReportDivs(){
		$(".prevoiusElectionTrendsDiv").html("");
		$(".prevoiusElectionTrendsParliamentDiv").html("");
		$(".addedDeletedDiv").html("");
		$(".resultDiv").html("");
		$(".partyStrengths").html("");
		$(".resultDiv1").html("");
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
	
	
	if(results.length>0){
	$(".addedDeletedDiv").css("display","block");
	
 
	var str="";
	str+="<h4 style='text-align:center;'> Booth Wise ADDED Votes</h4>";
	str+="<table class='table table-bordered' style='font-size:16px;'>";
	str+="<thead>";
	str+="<tr><th rowspan=3>Panchayat</th><th rowspan=3>Booth</th><th rowspan=3>Total Votes</th><th colspan=14>Added</th></tr>";
	str+="<tr>";
		str+="<th colspan=2>Total Added</th><th colspan=2>Young Votes</th><th colspan=2>18-25</th><th colspan=2>26-35</th><th colspan=2>36-45</th><th colspan=2>46-60</th><th colspan=2>Above 60</th>";
	str+"</tr>";
	str+="<tr>";
		str+="<th>Votes</th><th>%</th><th>Voters</th><th>%</th><th>Votes</th><th>%</th><th>Votes</th><th>%</th><th>Votes</th><th>%</th><th>Votes</th><th>%</th><th>Votes</th><th>%</th>";
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
	
	
	str+="<h4 style='text-align:center;'> Booth Wise DELETED Votes</h4>";
	str+="<table class='table table-bordered' style='font-size:16px;'>";
	str+="<thead>";
	str+="<tr><th rowspan=3>Panchayat</th><th rowspan=3>Booth</th><th rowspan=3>Total Votes</th><th colspan=14>Deleted</th></tr>"
	str+="<tr>";
		str+="<th colspan=2>Total Deleted</th><th colspan=2>Young Votes</th><th colspan=2>18-25</th><th colspan=2>26-35</th><th colspan=2>36-45</th><th colspan=2>46-60</th><th colspan=2>Above 60</th>";
	str+"</tr>";
	str+="<tr>";
		str+="<th>Votes</th><th>%</th><th>Votes</th><th>%</th><th>Votes</th><th>%</th><th>Votes</th><th>%</th><th>Votes</th><th>%</th><th>Votes</th><th>%</th><th>Votes</th><th>%</th>";
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
 }
 

 function buildPrevoiusElectionTrendsDiv(results){
		$(".prevoiusElectionTrendsDiv").html("");
	 	var str="";
		str+="<h4 style='text-align:center;'>Assembly Constituency Wise Result</h4>";
		str+="<table class='table table-bordered' style='font-size:16px;'>";
		str+="<thead>";
		str+="<tr>";
			str+="<th>Year</th><th>Total Voters</th><th>Votes Polled</th><th>TDP</th><th>% </th><th>Margin Votes(%)</th><th>INC</th><th>%</th><th>PRP/YSRCP</th><th>%</th>";
			if(results[0].districtId<10){
				str+="<th>BJP</th><th>%</th><th>TRS</th><th>%</th>";
			}
			str+="<th>OTHERS</th><th>%</th>";
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
				}else{
					tdpper="--";
				}
				str+="<tr>";
				str+="<td>"+results[i].electionYear+"</td>";
				if(results[i].totalVoters!=null){
					str+="<td>"+results[i].totalVoters+"</td>";
				}else{
					str+="<td>-</td>";
				}
				str+="<td>"+results[i].totalVotesPolled+"</td>";
				
				if(results[i].tdpVo!=null){
					if(results[i].tdpVo.rank==1){
						str+="<td style='background:#CBE699'>"+results[i].tdpVo.votesEarned+"</td>";
					}else{
						str+="<td>"+results[i].tdpVo.votesEarned+"</td>";
					}
					str+="<td>"+results[i].tdpVo.percentage+"</td>";
					str+="<td>"+tdpPer+"</td>";
				}else{
					str+="<td>-</td><td>-</td><td>-</td>";
				}
				if(results[i].incVo.rank==1){
					str+="<td style='background:#CBE699'>"+results[i].incVo.votesEarned+"</td>";
				}else{
					str+="<td>"+results[i].incVo.votesEarned+"</td>";
				}
				str+="<td>"+results[i].incVo.percentage+"</td>";
				
				if(results[i].prpVo.rank==1){
					str+="<td style='background:#CBE699'>"+results[i].prpVo.votesEarned+"</td>";
				}else{
					str+="<td>"+results[i].prpVo.votesEarned+"</td>";
				}
				str+="<td>"+results[i].prpVo.percentage+"</td>";
				if(results[0].districtId<10){
					if(results[i].bjpVo.rank==1){
						str+="<td style='background:#CBE699'>"+results[i].bjpVo.votesEarned+"</td>";
					}else{
						str+="<td>"+results[i].bjpVo.votesEarned+"</td>";
					}
					str+="<td>"+results[i].bjpVo.percentage+"</td>";
					
					if(results[i].trsVo.rank==1){
						str+="<td style='background:#CBE699'>"+results[i].trsVo.votesEarned+"</td>";
					}else{
						str+="<td>"+results[i].trsVo.votesEarned+"</td>";
					}
					str+="<td>"+results[i].trsVo.percentage+"</td>";
				}
				if(results[i].othersVo.rank==1){
					str+="<td style='background:#CBE699'>"+results[i].othersVo.votesEarned+"</td>";
				}else{
					str+="<td>"+results[i].othersVo.votesEarned+"</td>";
				}
				str+="<td>"+results[i].othersVo.percentage+"</td>";
				str+="</tr>";
			
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
		str+="<tr>";
			str+="<th>Year</th><th>Total Voters</th><th>Votes Polled</th>";
			str+="<th>TDP</th><th>% </th>";
			str+="<th>Margin Votes(%)</th>";
			str+="<th>INC</th><th>% </th>";
			str+="<th>PRP/YSRCP</th><th>% </th>";
			if(results[0].districtId<10){
				str+="<th>BJP</th><th>%</th><th>TRS</th><th>%</th>";
			}
			str+="<th>OTHERS</th><th>%</th>";
		str+"</tr>";
		str+="</thead>";
		str+="<tbody>";
			for(var i in results){
				if(results[i].tdpVo !=null){
					if(results[i].tdpVo !=null){
				str+="<tr>";
				str+="<td>"+results[i].electionYear+"</td>";
				if(results[i].totalVoters!=null){
					str+="<td>"+results[i].totalVoters+"</td>";
				}else{
					str+="<td>-</td>";
				}
				str+="<td>"+results[i].totalVotesPolled+"</td>";
				if(results[i].tdpVo.rank==1){
					str+="<td style='background:#CBE699'>"+results[i].tdpVo.votesEarned+"</td>";
				}else{
					str+="<td>"+results[i].tdpVo.votesEarned+"</td>";
				}
				str+="<td>"+results[i].tdpVo.percentage+"</td>";
				str+="<td>"+results[i].tdpVo.marginVotes+"("+results[i].tdpVo.marginVotesPercentage+"%)</td>";
				if(results[i].incVo.rank==1){
					str+="<td style='background:#CBE699'>"+results[i].incVo.votesEarned+"</td>";
				}else{
					str+="<td>"+results[i].incVo.votesEarned+"</td>";
				}
				str+="<td>"+results[i].incVo.percentage+"</td>";
				if(results[i].prpVo.rank==1){
					str+="<td style='background:#CBE699'>"+results[i].prpVo.votesEarned+"</td>";
				}else{
					str+="<td>"+results[i].prpVo.votesEarned+"</td>";
				}
				str+="<td>"+results[i].prpVo.percentage+"</td>";
				if(results[0].districtId<10){
					if(results[i].bjpVo.rank==1){
						str+="<td style='background:#CBE699'>"+results[i].bjpVo.votesEarned+"</td>";
					}else{
						str+="<td>"+results[i].bjpVo.votesEarned+"</td>";
					}
					str+="<td>"+results[i].bjpVo.percentage+"</td>";
					
					if(results[i].trsVo.rank==1){
						str+="<td style='background:#CBE699'>"+results[i].trsVo.votesEarned+"</td>";
					}else{
						str+="<td>"+results[i].trsVo.votesEarned+"</td>";
					}
					str+="<td>"+results[i].trsVo.percentage+"</td>";
				}
				str+="<td>"+results[i].othersVo.votesEarned+"</td>";
				str+="<td>"+results[i].othersVo.percentage+"</td>";
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
	if(myResults.partyResultsVOList!=null)
	{
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
								str+="<td>"+results[i].partyResultsVOList[j].votesEarned+"</td><td>"+results[i].partyResultsVOList[j].diffPercent+"</td>";
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

function getMandalWiseVoterModificationReportOfConstituency()
 {
	var constituencyDetails={constituencyId:"",locationType:"constituency",locationValue:"",status:"",fromPublicationDateId:"",toPublicationDateId:""};
	constituencyDetails.constituencyId=$("#constituencyId").val();
	constituencyDetails.locationValue=$("#constituencyId").val();
	constituencyDetails.fromPublicationDateId=8;
	constituencyDetails.toPublicationDateId=10;
	
	$.ajax({
          type:'POST',
          url: 'getSubLevelVoterModificationReportForConstituencyAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			  $("#ajaxLoad").css("display","none");
			if(result != null){
				//buildBoothWiseAddedAndDeletedVoters(result);
				console.log(result);
			}
	   });
 }
 
 function getVoterDensityReport()
 {
	var constituencyDetails={constituencyId:"",publicationId:""};
	constituencyDetails.constituencyId=$("#constituencyId").val();
	constituencyDetails.publicationId=10;
		
	$.ajax({
          type:'POST',
          url: 'getVoterDensityPanchayatWiseWithPartyResultAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			  $("#ajaxLoad").css("display","none");
			if(result != null){
				console.log(result);
				buildVoterscountInPanchayats(result);
			}
	   });
 }
 
 function buildVoterscountInPanchayats(result)
{
	
	if(result.densityList != null && result.densityList.length > 0)
	{
		$('.partyStrengths').show();
		var str = '';
		str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;color: black;" class="">VOTER DENSITY VS PANCHAYATS</h4>';
		str += '<table class="table table-hover table-bordered" style="font-size: 12px; font-family: verdana; color: black; font-weight: lighter; margin-top: 15px;margin-left: -15px;">';
		str += '<tr>';
		str += '<th>Voters Range</th>';
		for(var i in result.densityList)
		{
			str += '<th>'+result.densityList[i].type+'</th>';
		}
		str += '</tr>';
		str += '<tr>';
		str += '<td>No of Panchayats</td>';
		for(var j in result.densityList)
		{
			str += '<td>'+result.densityList[j].count+'</td>';
		}
		str += '</tr>';
		
		for(var k in result.partyWiseList){
			str += '<tr>';
			str += '<td>'+result.partyWiseList[k].partyName+'</td>';
			for(var j in result.partyWiseList[k].densityList)
			{
				str += '<td>'+result.partyWiseList[k].densityList[j].count+'</td>';
			}
			str += '</tr>';
		}
		
		str += '<tr>';
		str += '<td> Ananymous </td>';
		for(var j in result.ananymousDensity)
		{
			str += '<td>'+result.ananymousDensity[j].count+'</td>';
		}
		str += '</tr>';
		
		str += '</table>';
		$('.partyStrengths').html(str);
	}
}

//VOTER MODIFICATION REPORT

function getVoterInfo(){
		
	var constituencyDetails={constituencyId:"",locationType:"constituency",locationValue:"",status:"",fromPublicationDateId:"",toPublicationDateId:"",taskToDo:"voterInfo"};
	constituencyDetails.constituencyId=$("#constituencyId").val();
	constituencyDetails.locationValue=$("#constituencyId").val();
	constituencyDetails.fromPublicationDateId=9;
	constituencyDetails.toPublicationDateId=10;
	
	
	$.ajax({
          type:'POST',
          url: 'getVoterInfoForStratagyAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			  $("#ajaxLoad").css("display","none");
			if(result != null){
				//buildBoothWiseAddedAndDeletedVoters(result);
				console.log(result);
			}
	   });
	
	}

	function getAddedDeletedVoterInfoInALocation()
	{
		var constituencyDetails={constituencyId:"",locationType:"constituency",locationValue:"",status:"",fromPublicationDateId:"",toPublicationDateId:"",taskToDo:"addedOrDeletedVoterInfoInALocation"};
	constituencyDetails.constituencyId=$("#constituencyId").val();
	constituencyDetails.locationValue=$("#constituencyId").val();
	constituencyDetails.fromPublicationDateId=9;
	constituencyDetails.toPublicationDateId=10;
	
	
	$.ajax({
          type:'POST',
          url: 'getAddedOrDeletedVoterInfoInALocationForStratagyAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			  $("#ajaxLoad").css("display","none");
			if(result != null){
				//buildBoothWiseAddedAndDeletedVoters(result);
				console.log(result);
			}
	   });
	   
	
	}

	function getGenderWiseVoterModificationsBetweenPublications()
	{
	
	var constituencyDetails={constituencyId:"",locationType:"constituency",locationValue:"",status:"",fromPublicationDateId:"",toPublicationDateId:"",taskToDo:"genderWiseVoterModifiBetweenPublications"};
	constituencyDetails.constituencyId=$("#constituencyId").val();
	constituencyDetails.locationValue=$("#constituencyId").val();
	constituencyDetails.fromPublicationDateId=8;
	constituencyDetails.toPublicationDateId=10;
	
	
	$.ajax({
          type:'POST',
          url: 'getGenderWiseVoterModifiBetweenPublicationsForStratagyAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			  $("#ajaxLoad").css("display","none");
			if(result != null){
				//buildBoothWiseAddedAndDeletedVoters(result);
				console.log(result);
			}
	   });
			
	}

	function getGenderWiseVoterModificationsForEachPublication()
	{
			var constituencyDetails={constituencyId:"",locationType:"constituency",locationValue:"",status:"",fromPublicationDateId:"",toPublicationDateId:"",taskToDo:"genderWiseVoterModifiForEachPublic"};
	constituencyDetails.constituencyId=$("#constituencyId").val();
	constituencyDetails.locationValue=$("#constituencyId").val();
	constituencyDetails.fromPublicationDateId=9;
	constituencyDetails.toPublicationDateId=10;
	
	
	$.ajax({
          type:'POST',
          url: 'getGenderWiseVoterModifiForEachPublicForStratagyAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constituencyDetails)},
     	  }).done(function(result){ 
			  $("#ajaxLoad").css("display","none");
			if(result != null){
				//buildBoothWiseAddedAndDeletedVoters(result);
				console.log(result);
			}
	   });	
	
	}
 
 </script>
</body>
</html>