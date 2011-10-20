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

<style type="text/css">
.titleStyle {
    font-family:sans-serif;
    font-size: 13px;
    font-weight: bold;
	color:black;
    text-decoration:none;
 }
 p.sourceColor {  
    color:#FF4500;
 }
 .imgFieldset
  {
	-moz-border-radius: 4px 4px 4px 4px;
	border			: 4px solid #9F81F7;
    margin-bottom	: 10px;
	margin-top		: 5px;
  }
</style>
<script type="text/javascript">
   var timeST = new Date().getTime();
   var candidateId = '${candidateId}';
function callAjax(jsObj,url)
{
	
	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText);
         if(jsObj.task == "getFirstFourNewsRecordsToDisplay")
			{
               showFirstFourNewsRecords(myResults);
			}
		if(jsObj.task == "getFileByFileId")
			{
               showNews(myResults);
			}
		}
		catch(e)
		{   
		 alert("Invalid JSON result" + e);   
		}  
	 },
	scope : this,
	failure : function( o )
	{
								//alert( "Failed to load result" + o.status + " " + o.statusText);
	}
  };

 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function getFirstFourNewsRecords(){
   var jsObj =
		{   
		    time : timeST,
			candidateId:candidateId,
			task:"getFirstFourNewsRecordsToDisplay"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function showFirstFourNewsRecords(results)
 { 
  if(results.length>0)
  {
   var str ='';
   str+='  <table>';
  
   for(var i in results)
   {
     str+='     <tr>';
     str+='       <td><a href="javascript:{}" onclick="getNews('+results[i].fileId+')" class="titleStyle"\">'+results[i].fileTitle1+'</a></td>';
     str+='     </tr>';
     str+='     <tr>';
     str+='       <td><font color="#FF4500">'+results[i].source+'</font> | '+results[i].fileDate+'</td>';
     str+='     </tr>';
     str+='     <tr>';
     str+='       <td>'+results[i].fileDescription1+'</td>';
     str+='     </tr>';
	 str+='     <hr style="width:98%;"></hr>';
   }
   str+='  </table>';
   
   str+='<a href="javascript:{}" onclick="getTotalNews()" \"><img src="images/icons/more.jpg" align="right"></a>';
   str+='<div id="showNewsDiv" />';
   
   document.getElementById("newsDisplayDiv").innerHTML=str;
   }
 }
 function getNews(fileId)
 {
    var jsObj =
		{   
		    time : timeST,
			fileId:fileId,
			task:"getFileByFileId"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);  
 }
 function showNews(results)
  {
    var fileType = results[0].name.split(".");
	if(fileType[(fileType.length-1)] == "pdf" || fileType[(fileType.length-1)] == 'text'  )
     openFile(results[0].path);	 
    else if(fileType[(fileType.length-1)].indexOf('word') != -1)
	{
	  window.open(results[0].path);
	}
	else
	{
	  $("#showNewsDiv").dialog({ stack: false,
							    height: 570,
								width: 720,
								position:[150,120],								
								modal: true,
								title:'<font color="Navy">'+results[0].fileTitle1+'</font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#showNewsDiv").dialog();
	var str='';
	 str+='<fieldset class="imgFieldset">';
	 str+='<table>';
	  str+='<tr>';
	   str+='<td>';
	  str+='<img alt="" src="'+results[0].path+'" height="220px" />';
	  str+='</td>';
	   str+='</tr>';
	   str+='<tr>';
	   str+='<td>';
	  str+=''+results[0].fileDescription1+'';
	  str+='</td>';
	   str+='</tr>';
	   str+='<tr>';
	   str+='<td>';
	  str+='Source:<font color="#FF4500">'+results[0].source+'</font>  Date : '+results[0].fileDate+'';
	  str+='</td>';
	   str+='</tr>';
	 str+='<table>';
	 str+='</fieldset>';
	 document.getElementById("showNewsDiv").innerHTML=str;
	}
  }
function openFile(filePath,fileType){

window.open(filePath, "browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
}
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
 <div class="box3"><img src="images/icons/news_events.jpg">
  <div id="newsDisplayDiv">
 
 </div>
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
getFirstFourNewsRecords();
</script>
</body>
</html>