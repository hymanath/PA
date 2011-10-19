<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${candidateVO.candidateName} 'S  Profile</title>
<script type="text/javascript" src="js/candidatePage/candidatePage.js"></script>
<link rel="stylesheet" type="text/css" href="styles/candidatePage/candidatePage.css">
<script type="text/javascript">

function buildCandidateElectionInfo()
{
	
	var flag = true;;
	var str ='';
	var electionInfoElmt = document.getElementById("electionInfo");
	str+='<table>';
    str+='<div style="margin-bottom: 21px; font-weight: normal; font-size: 19px; font-family: tahoma;">Election Profile</div>';
	str+='<s:iterator value="candidateElectionDetails" status="stat">';

	str+='<s:if test="status == true">';
	if(flag ==true){
	str+='<div><img src="images/icons/won.jpg"><br><br></div>';
	flag=false;
	}
	
    str+='<div style="cursor: pointer;" onclick="showCandidateElectionDetails(\'constituencyElectionResultsAction.action?constituencyId=<s:property value="constituencyId"/>&electionType=<s:property value="electionType"/>&electionYear=<s:property value="electionYear"/>\')">';
	str+='<b><img src="images/icons/round.JPG">Won</b></s:if>';
	str+=' <b>in <s:property value="electionYear" /></b>&nbsp;&nbsp;<s:property value="electionType" />&nbsp;&nbsp;Election with &nbsp;&nbsp;<b><s:property value="votesPercentage" />% </b>&nbsp;&nbsp;of votes gain for   <s:property value="partyName" />&nbsp;&nbsp;party in &nbsp;&nbsp;<s:property value="constituencyName" /> constituency<br><br></div>';
	str+='<s:else>';
	if(flag ==true){
	str+='<img src="images/icons/won.jpg"><br><br>';
	flag=false;
	}
	str+='<b>Lost</b>';
	str+=' <b>in <s:property value="electionYear" /></b>&nbsp;&nbsp;<s:property value="electionType" />&nbsp;&nbsp;Election with &nbsp;&nbsp;<b><s:property value="votesPercentage" />% </b>&nbsp;&nbsp;of votes gain for &nbsp;&nbsp;<s:property value="partyName" />&nbsp;&nbsp;party in &nbsp;&nbsp;<s:property value="constituencyName" /> constituency<br>';
	str+='</s:else>';
	str+'</s:iterator>';
	str+='</table>';
	
 electionInfoElmt.innerHTML = str;
}
function candidateInfo()
{
	var candidateInfoElmt = document.getElementById("candidateInfo");
    var str='';

	str+='<table>';
	str+='<img id="candidateImage" height="250" width="180" onerror="setDefaultImage(this)" src="images/candidates/'+candidateInfoObject.candidateInfoArray[0].candidateName+'.jpg">';
	str+='<tr><td>';
	str+='<span id="candidateName">'+candidateInfoObject.candidateInfoArray[0].candidateName+'</span></td></tr>';
	str+='<tr><td align="center">';
	str+='<span  style="font-weight: bold; font-size:12px;">';
    if(candidateInfoObject.candidateInfoArray[0].electionType =="Assembly")
	{
     str+='MLA';
	}
	if(candidateInfoObject.candidateInfoArray[0].electionType =="Parliament")
	{
     str+='MP';
	} 
	str+='</span></td>';
	str+='</tr>';
	str+='<tr><td align="center">';
	str+='<span  style="font-weight: bold; font-size: 12px;">'+candidateInfoObject.candidateInfoArray[0].constituencyName+'&nbsp;Constituency</span></td></tr>';
	str+='<tr><td align="center">';
	str+='<span  style="font-weight: bold; font-size: 12px;">'+candidateInfoObject.candidateInfoArray[0].partyName+' Party</span></td>';
	
	candidateInfoElmt.innerHTML = str;
}
</script>
</head>
<body>
<div id="candidateProfileInfo">
 <span style="margin-top: 12px; margin-left: 12px;">${candidateVO.candidateName} 'S  Profile</span></div>
<table width="987px" border="0" align="center" cellpadding="0" cellspacing="0">
 <tr>
  <td width="206">
  <div class="rel">
   <div class="box1">
   <div id="candidateInfo" >
	
	</div>
	<div style="border-bottom: 1px solid #D7E2EB;"></div>
	 <div class="linkClass">About
	   </div>
	   <div class="linkClass">News And Events
	   </div>
	   <div class="linkClass">Videos
	   </div>
	   <div class="linkClass">Photo Gallery
	   </div>
	   <div class="linkClass">Elections
	   </div>
	   <div class="linkClass">Developments
	   </div>
    </div>
	</div>
 </td>
 <td width="10">&nbsp;</td>
 <td width="444" valign="top"><div class="rel">
   <div class="box2">
 <div style="font-weight: bold; font-size: 15px;">About Chandrababu Naidu</div>
	<p>Nara Chandrababu Naidu (born April 20, 1951) was the chief minister of Andhra Pradesh during 1995-2004. He holds the record of being the longest serving chief minister of A. P.He is currently the leader of the Telugu Desam Party, the second largest party in Andhra Pradesh.</p>
	<p>Mr. N. Chandrababu Naidu was born in the Naravaripally village of Chittoor district on 20th April, 1950. He did his Masters in Economics from the Sri Venkateswara University, Tirupati. He was working towards a Ph.D. degree at the same university, when he took to politics.Naidu is married to Mrs. Bhuvaneswari and has a son Lokesh.</p>
<div style="text-align: right;"><a href="javascript:{}" style="color: LightSkyBlue;">
Read More >></a></div>
<div id="electionInfo"></div>
  </div>
 </div>
</td>
 <td width="10">&nbsp;</td>
<td width="326">
<div class="rel">
 <div class="box3"><img src="images/icons/news_events.jpg"></div>
 </div>
 </td>
 </tr>
 
</table>

<script type="text/javascript">	
	
	
	candidateInfoObject.name = "${candidateVO.candidateName}";
	candidateInfoObject.candidateImgURL = "<%=request.getContextPath()%><s:property value="getText('imageURL')" />default.JPG" ;
	candidateInfoObject.contextPath = "<%=request.getContextPath()%>";
	candidateInfoObject.candidatePartyFlag = "<%=request.getContextPath()%>/images/party_flags/${partyFlag}";

	<c:forEach var="candidateElectionResults" items="${candidateElectionDetails}" >		
			var candidateObj={
								electionId:'${candidateElectionResults.electionId}',
								candidateName:'${candidateElectionResults.candidateName}',
								partyName:'${candidateElectionResults.partyName}',
								partyFlag:'${candidateElectionResults.partyFlag}',
								constituencyName:'${candidateElectionResults.constituencyName}',
								electionType:'${candidateElectionResults.electionType}',
								electionYear:'${candidateElectionResults.electionYear}',
								districtName:'${candidateElectionResults.districtName}',
								stateName:'${candidateElectionResults.stateName}',
								votesEarned:'${candidateElectionResults.votesEarned}',
								votePercentage:'${candidateElectionResults.votesPercentage}',
								constituencyId:'${candidateElectionResults.constituencyId}',
								education:'${candidateElectionResults.education}',
								partyShortName:'${candidateElectionResults.shortName}',	
								status:'',
								oppositionCandidates:[]
							};
			<c:if test="${candidateElectionResults.status == true }">
				candidateObj.status='Won';
			</c:if>						
			<c:if test="${candidateElectionResults.status == false }">
				candidateObj.status='Lost';
			</c:if>

			<c:forEach var="detailedResult" items="${candidateElectionResults.oppositionCandidates}" >
				var oppositionList={
									candidateName:'${detailedResult.candidateName}',
									partyName:'${detailedResult.partyName}',
									votesEarned:'${detailedResult.votesEarned}',
									votesPercentage:'${detailedResult.votesPercentage}',
									status:''
								};
						<c:if test="${detailedResult.status == true }">
							oppositionList.status='Won';
						</c:if>
						<c:if test="${detailedResult.status == false }">
							 oppositionList.status='Lost';
						</c:if>
					candidateObj.oppositionCandidates.push(oppositionList);
			</c:forEach>			
			candidateInfoObject.candidateInfoArray.push(candidateObj);			
	</c:forEach>
	
	function setDefaultImage(img)
	{
		img.src = "images/candidates/human.jpg";
	}

function showCandidateElectionDetails(str)
{
	
	//var data = candidateInfoObject.candidateInfoArray[index];
	var politicalChangesWindow = window.open(str+"","politicalChangesWindow","scrollbars=yes,height=600,width=850,left=200,top=200");
    politicalChangesWindow.focus();
}


candidateInfo();
buildCandidateElectionInfo();
</script>
</body>
</html>