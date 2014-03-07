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
	</style>
</head>
<body>

 <div class="container">
	<div class="m-top25" style="width:500px;margin-left:auto;margin-right:auto;">
	   <span style="font-weight:bold;font-size:16px;">Select Constituency</span> <s:select theme="simple" name="constituency" id="constituencyId" list="constituenciesList" listKey="id" listValue="name"/>
       <div><input type="button" class="btn btn-primary" onClick="getReport();" value="Get Stratagic Report"></input></div>
	</div>
	
	
	<div class="resultDiv"></div>
	<div class="prevoiusElectionTrendsDiv"></div>
	<div class="prevoiusElectionTrendsParliamentDiv"></div>
</div>
 


<script>
	function getReport(){
		getBoothWiseAddedAndDeletedVoters();
		getPreviousTrendsReport();
		getPreviousTrendsReportParliament();
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
	$(".resultDiv").html("");
 
	var str="";
	str+="<h4 style='text-align:center;'> Booth Wise ADDED Voters</h4>";
	str+="<table class='table table-bordered' style='font-size:16px;'>";
	str+="<thead>";
	str+="<tr><th rowspan=3>Panchayat</th><th rowspan=3>Booth</th><th rowspan=3>Total Voters</th><th colspan=14>Added</th></tr>";
	str+="<tr>";
		str+="<th colspan=2>Total Added</th><th colspan=2>Young Voters</th><th colspan=2>18-25</th><th colspan=2>26-35</th><th colspan=2>36-45</th><th colspan=2>46-60</th><th colspan=2>Above 60</th>";
		//str+="<th colspan=2>Total Deleted</th><th colspan=2>Young Voters</th><th colspan=2>18-25</th><th colspan=2>26-35</th><th colspan=2>36-45</th><th colspan=2>46-60</th><th colspan=2>Above 60</th>";
	str+"</tr>";
	str+="<tr>";
		str+="<th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th>";
		//str+="<th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th>";
	str+"</tr>";
	str+="</thead>";
	str+="<tbody>";
		for(var i in results){
			str+="<tr>";
			str+="<td>"+results[i].panchayat+"</td><td>Booth-"+results[i].hamlet+"</td><td>"+results[i].totalVotersInBooth+"</td>";
			str+="<td>"+results[i].totalVotersAdded+"</td><td>"+results[i].totalVotersAddedPer+"</td><td>"+results[i].youngVoters+"</td><td>"+results[i].youngVotersPer+"</td><td>"+results[i].age18To25+"</td><td>"+results[i].age18To25Per+"</td><td>"+results[i].age26to35+"</td><td>"+results[i].age26to35Per+"</td><td>"+results[i].age36to45+"</td><td>"+results[i].age36to45Per+"</td><td>"+results[i].age46to60+"</td><td>"+results[i].age46to60Per+"</td><td>"+results[i].above60+"</td><td>"+results[i].above60Per+"</td>";
			//str+="<td>"+results[i].totalVotersDeleted+"</td><td>"+results[i].totalVotersDeletedPer+"</td><td>"+results[i].delYoungVoters+"</td><td>"+results[i].delYoungVotersPer+"</td><td>"+results[i].delAge18To25+"</td><td>"+results[i].delAge18To25Per+"</td><td>"+results[i].delAge26to35+"</td><td>"+results[i].delAge26to35Per+"</td><td>"+results[i].delAge36to45+"</td><td>"+results[i].delAge36to45Per+"</td><td>"+results[i].delAge46to60+"</td><td>"+results[i].delAge46to60Per+"</td><td>"+results[i].delAbove60+"</td><td>"+results[i].delAbove60Per+"</td>";
			//str+="<th>Panchayat</th><th>Booth</th><th>Young Voters</th><th>18 - 25</th><th>26 - 35</th><th>36 - 45</th><th>46 - 60</th>
			//"<th>Above 60</th><th>Young Voters</th><th>18 - 25</th><th>26 - 35</th><th>36 - 45</th><th>46 - 60</th><th>Above 60</th>";
			str+="</tr>";
		}
	str+="</tbody>";
	str+="</table>";
	
	
	str+="<h4 style='text-align:center;'> Booth Wise DELETED Voters</h4>";
	str+="<table class='table table-bordered' style='font-size:16px;'>";
	str+="<thead>";
	str+="<tr><th rowspan=3>Panchayat</th><th rowspan=3>Booth</th><th rowspan=3>Total Voters</th><th colspan=14>Deleted</th></tr>"
	str+="<tr>";
		//str+="<th colspan=2>Total Added</th><th colspan=2>Young Voters</th><th colspan=2>18-25</th><th colspan=2>26-35</th><th colspan=2>36-45</th><th colspan=2>46-60</th><th colspan=2>Above 60</th>";
		str+="<th colspan=2>Total Deleted</th><th colspan=2>Young Voters</th><th colspan=2>18-25</th><th colspan=2>26-35</th><th colspan=2>36-45</th><th colspan=2>46-60</th><th colspan=2>Above 60</th>";
	str+"</tr>";
	str+="<tr>";
		//str+="<th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th>";
		str+="<th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th><th>Voters</th><th>%</th>";
	str+"</tr>";
	str+="</thead>";
	str+="<tbody>";
		for(var i in results){
			
			str+="<tr>";
			str+="<td>"+results[i].panchayat+"</td><td>Booth-"+results[i].hamlet+"</td><td>"+results[i].totalVotersInBooth+"</td>";
			//str+="<td>"+results[i].totalVotersAdded+"</td><td>"+results[i].totalVotersAddedPer+"</td><td>"+results[i].youngVoters+"</td><td>"+results[i].youngVotersPer+"</td><td>"+results[i].age18To25+"</td><td>"+results[i].age18To25Per+"</td><td>"+results[i].age26to35+"</td><td>"+results[i].age26to35Per+"</td><td>"+results[i].age36to45+"</td><td>"+results[i].age36to45Per+"</td><td>"+results[i].age46to60+"</td><td>"+results[i].age46to60Per+"</td><td>"+results[i].above60+"</td><td>"+results[i].above60Per+"</td>";
			str+="<td>"+results[i].totalVotersDeleted+"</td><td>"+results[i].totalVotersDeletedPer+"</td><td>"+results[i].delYoungVoters+"</td><td>"+results[i].delYoungVotersPer+"</td><td>"+results[i].delAge18To25+"</td><td>"+results[i].delAge18To25Per+"</td><td>"+results[i].delAge26to35+"</td><td>"+results[i].delAge26to35Per+"</td><td>"+results[i].delAge36to45+"</td><td>"+results[i].delAge36to45Per+"</td><td>"+results[i].delAge46to60+"</td><td>"+results[i].delAge46to60Per+"</td><td>"+results[i].delAbove60+"</td><td>"+results[i].delAbove60Per+"</td>";
			//str+="<th>Panchayat</th><th>Booth</th><th>Young Voters</th><th>18 - 25</th><th>26 - 35</th><th>36 - 45</th><th>46 - 60</th>
			//"<th>Above 60</th><th>Young Voters</th><th>18 - 25</th><th>26 - 35</th><th>36 - 45</th><th>46 - 60</th><th>Above 60</th>";
			str+="</tr>";
		}
	str+="</tbody>";
	str+="</table>";
	
	
	
	$(".resultDiv").html(str);
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
 
 </script>
</body>
</html>