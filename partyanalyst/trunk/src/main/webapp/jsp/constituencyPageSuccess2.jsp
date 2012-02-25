     <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="s" uri="/struts-tags"%>
	
	<html>
	<head> 

	<title><c:out value='${constituencyDetails.constituencyName}'/> Constituency Page - News, Details, Mandals, Parties Performance, Voting Trendz, Election Results,MLA, MP,MPTC, ZPTC Election Results</title>

	<script type="text/javascript" src="js/constituencyPage/constituencyPage2.js"></script> 
	<script type="text/javascript" src="js/connectPeople/connectPeopleContent2.js"></script>
	<script type="text/javascript" src="js/districtPage/districtPage.js"></script>
	<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
	
	<!--<script type="text/javascript" src="js/jQuery/jquery-1.5.2.js"></script> --> 
	<script
	src="js/jQuery/jquery-ui.min.js">
		</script>
	<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>
	<script src="${mapKey}" type="text/javascript"></script>
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
	<!-- Combo-handled YUI CSS files: --> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/button/assets/skins/sam/button.css"> 

	<!-- Combo-handled YUI JS files: --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/button/button-min.js"></script> 
	<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage2.css">	</link>
	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
	<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

	<script type="text/javascript">
	$(document).ready(function(){
	$("#MandalsDelimit a").click(function(){
		$("#MandalsDelimit a").removeClass("active");
		$(this).addClass("active activepinkdownarrow");
	});
	
	showMunicipalityResults();
	getMunicipalityResults();

	$("#LocalElectionsNav a").click(function(){
		$("#LocalElectionsNav a").removeClass("active");
		$(this).addClass("active");
	});
});
	google.load("visualization", "1", {packages:["corechart"]});
	
	var userType = "${sessionScope.USER.userStatus}";
	var stateName = '${constituencyDetails.stateName}';
	var districtName = '${constituencyDetails.districtName}';
	var constituencyName ='${constituencyDetails.constituencyName}'
	var constiId = ${constituencyId}; 
	var parliamentconstituency='${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType}'
	
	
	
	
	var conn='${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateName}';
	
	
	
	var parliamentcandidateName='${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName}';
	
	var constituencyId = '${candidateDetailsForConstituency.assemblyCandidateInfo[0].constituencyId}';

	var elecType = '${candidateDetailsForConstituency.assemblyCandidateInfo[0].constituencyType}';
	var elecYear = '${candidateDetailsForConstituency.assemblyCandidateInfo[0].latestElecYear}';
	var party='${candidateDetailsForConstituency.assemblyCandidateInfo[0].party}';
	var totalNoOflocalElectionsBodies = 0;
	
	var candidateId='${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateId}';
	var assembly='${candidateDetailsForConstituency.assemblyCandidateInfo[0].constituencyType == 'Assembly'}';
	
	</script>
	<style>
     
	#mandalsVotersInfoDiv_Head{display:none;}
	#mandalsVotersInfoDiv_Body{width:auto;margin-left:auto;margin-right:auto;float:none;display:table;}

	.detailed-ele-inf * table,#mandalsVotersInfoDiv_Body div div table{border-collapse:collapse;padding:10px;}
 
	.detailed-ele-inf * th, #mandalsVotersInfoDiv_Body div div table th{text-align:left;
    padding:10px;font-weight:bold;background-color:#ceedf0;font-size:13px;}

	#CorporationTableDiv_0 > table,#greaterTableDiv_0 > table,#elecResDiv > table{border-collapse:collapse;border:1px solid #d3d3d3;width:100%;}
	#CorporationTableDiv_0 > table * th,#greaterTableDiv_0 > table * th,#elecResDiv > table * th{height:20px;text-align:left;background-color:#ceedf0; padding:5px;font-weight:bold;border:1px solid #d3d3d3;}
	#CorporationTableDiv_0 > table * td,#greaterTableDiv_0 > table * td, #elecResDiv > table * td{padding:3px;padding-left:5px;font-weight:normal;border:1px solid #d3d3d3;}
	#elecResDiv > table * tr:nth-child(even){background:#f9f9f9;}
	#CorporationTableDiv_0 > table * a,#greaterTableDiv_0 > table * a,#elecResDiv > table * a{text-decoration:none;color:#3d3d3d;font-weight:bold;}
	
	.detailed-ele-inf * td, #mandalsVotersInfoDiv_Body div div table td{padding:8px;padding-left:10px;font-weight:normal;}
	.detailed-ele-inf * tr:nth-child(even),#mandalsVotersInfoDiv_Body div div table tr:nth-child(even){background:#f9f9f9;}
	
	.detailed-ele-inf * table * a,#mandalsVotersInfoDiv_Body div div table th a,#mandalsVotersInfoDiv_Body div div table td a{text-decoration:none;color:#3d3d3d;}
	
	.detailed-ele-inf * table *a:hover, #mandalsVotersInfoDiv_Body div div table th a:hover,#mandalsVotersInfoDiv_Body div div table td a:hover{text-decoration:underline;color:#000;}
	
	.cd-right-sec{float:left;margin-left:8px;}
	
	#inner-content-mainsec{width:1000px;;margin-left:auto;margin-right:auto;float:none;background:#fff;border-radius:5px;
	display:table;}
	
	.mvd-fields li a:hover, .mvd-fields li a.active{
	background: url("images/constituencyPage/pink-down-arrow.png") no-repeat scroll center bottom transparent;
	color: #9D5CB2;
    float: left;
    padding-bottom: 8px;}
	.mvd-fields li a:hover strong, .mvd-fields li a.active strong{background-color:#F5E4FA;}
	
	.detailed-ele-inf {
		display:table;
		padding-top: 0;
		position: relative;
		width: auto;
		margin-left: auto;
		margin-right: auto;
		float: none;
		Clear: both; 
	}
	#zptcPartyTrendsDetailsDiv > table{width:100%;}
	
	#censusSelect{
    margin-right: auto;
    padding-right: 0;
    width: 149px;}
	#announcementsInConstituencyDiv > table{border-collapse:collapse;border-bottom:1px solid #d3d3d3;width:100%;}
	.cp-cont-sub-fields li{border:none;}

	.yui-skin-sam .yui-dt table{border-collapse:collapse;border:1px solid #d3d3d3;}

	#electionYearsWithAssets_Panel_Div > table{width:100%;}

	#zptcDivBody{width:auto;margin-left:auto;margin-right:auto;}
	#mptcPartyTrendsDetailsDiv{width:300px;border:1px solid #d3d3d3;clear:both;}
	#mptcPartyTrendsDetailsDiv > table{width:100%;}
	#zptcCandidateLink,#zptcElectionIdsSelectDiv{width:auto;display:inline-block;}
	#zptcElectionIdsSelectDiv{position:relative;padding-left:10px;top:-13px;}
	#mptcElectionIdsSelectDiv,#mptcCandidateLink{width:auto;display:inline-block;}
	#mptcElectionIdsSelectDiv{position:relative;top:-13px;padding-left:10px;}
	  </style>
	  </head>
	<div class="clear"></div>
     <div class="main-title-sec">
        <div class="main-mbg">${constituencyDetails.constituencyName} CONSTITUENCY DETAILS</div>
        <div class="main-bbg"></div>
      </div>
      
	  

	  
	 <!--CIBSTUTYEBCT DETAILS LEFT SECTION START-->
      <body>

	
		<div id="inner-content-mainsec">
		
		<div class="cd-left-sec">
        <div class="cd-mid-cont-sec">





	
		
          <p class="pa-fi">State: <strong>
		  <a style="color: #000000;text-decoration: none"; href="statePageAction.action?stateId=${navigationVO.stateInfo.id}">${constituencyDetails.stateName}</a> </strong>&gt;  
		  District: <strong>
		  <c:forEach var="district" items="${navigationVO.districtInfo}">
					<a style="color: #000000;text-decoration: none"; href="districtPageAction.action?districtId=${district.id}&districtName=${district.name}">${district.name}</a>
			</c:forEach></strong>
			<c:if test="${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType == 'Parliament'}"><s:if test="%{navigationVO.pcsInfo.size > 0}">&gt; 
		Parliament:

			<strong><c:forEach var="parliament" items="${navigationVO.pcsInfo}">
			 <a style="color:#000000;text-decoration:none;" href="constituencyPageAction.action?constituencyId=${parliament.id}">${parliament.name}</a>
			
			
		</c:forEach>	
		</strong>
		</s:if>
		</c:if> 
		

		 <c:if test="${candidateDetailsForConstituency.assemblyCandidateInfo[0].constituencyType == 'Assembly'}">
		 <s:if test="%{navigationVO.acsInfo.size > 0}">&gt;Assembly :
			<c:forEach var="assembly" items="${navigationVO.acsInfo}">
		 	 <strong>  <a href="constituencyPageAction.action?constituencyId=${assembly.id}">${assembly.name}</a>&nbsp;
			
			</c:forEach>
		</s:if>
		</c:if>
		<!--Constituency Type:&nbsp
		<strong>${constituencyDetails.constituencyType}</strong>-->
			 
		</p>
         

<!--add-->
<!-- Constituency Details -->
<c:if test="${constituencyDetails.constituencyType == 'Assembly'}">
<div class="cl-sub-fields-sec">

<h1 class="org-title"><span>${constituencyDetails.constituencyName} Assembly Constituency Details </span></h1>
										<div id="constituencyInfoDiv">
										<div id="constituencyInfoDiv_Head"></div>
										<div id="constituencyInfoDiv_Body" style="text-align:left;padding:5px;padding-left:34px;margin-left:20px;"></div>
								</div>	
      </div> 
	
</c:if>	

<c:if test="${constituencyDetails.constituencyType != 'Assembly'}">
  <div class="cl-sub-fields-sec">
  	

<h1 class="org-title"><span>${constituencyDetails.constituencyName} Parliament Constituency Details </span></h1>
										<div id="constituencyInfoDiv">
										<div id="constituencyInfoDiv_Head"></div>
										<div id="constituencyInfoDiv_Body" style="text-align:left;padding:5px;padding-left:34px;margin-left:20px;"></div>
									</div>  	
      </div> 
	  </c:if> 
          <!--ELECTED CANDIDATE INFO SECTION START-->
          
          <div class="cl-sub-fields-sec">
            <h1 class="org-title"><span>Elected Candidate Info</span></h1><br><br>
			<c:if test="${constituencyDetails.deformDate !=null}">
			 
			
			<span style="clear:right;margin-left:71px;text-decoration: none;color:#5CB275;">This constituency has been delimited</span>
			
		         
			</c:if>
			<c:if test="${constituencyDetails.deformDate ==null}">
            <ul class="eci-fieds-sec">
              <li>
				 
				 <c:if test="${constituencyDetails.constituencyType == 'Parliament'}">
			<div id="constituencyPageCandidateInfo_Main" class="innerLayoutDivClass">
							<div id="constituencyPageCandidateInfo_Head" class="layoutHeadersClass"></div>
							<div id="constituencyPageCandidateInfo_Body" class="layoutBodyClass yui-skin-sam">
								<div id="constituencyPageCandidateInfo_Top"></div>
                               	<div id="constituencyPageCandidateInfo_Bottom"></div>
				
								</div>
						  
						  </c:if>
							
						
			<c:if test="${constituencyDetails.constituencyType != 'Parliament'}">

			

                <div  id="assemblyDiv" class="eci-field-cont-sec">
                  <div class="eci-place-title">Assembly Candidate :			  
	</div>
                  <div class="eci-party-title"><strong class="tc-tf">Party</strong></div>

				
                  <div class="clear"></div>

				  <c:if test="${constituencyDetails.constituencyType == 'Assembly'}">
                  <div class="eci-place-cont"><strong class="tc-tf">${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateName}
	</strong><a href="candidateElectionResultsAction.action?candidateId=${candidateDetailsForConstituency.assemblyCandidateInfo[0].candidateId}">  -View Profile</a></div>
                  <div class="eci-party-cont"><img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.assemblyCandidateInfo[0].partyFlag}" height="30" width="40"/>  <span class="party-symbol-code">${candidateDetailsForConstituency.assemblyCandidateInfo[0].party}</span> </div>
                  <div class="pl-sub-but"><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(${candidateDetailsForConstituency.assemblyCandidateInfo[0].constituencyId},'${candidateDetailsForConstituency.assemblyCandidateInfo[0].constituencyType}','${candidateDetailsForConstituency.assemblyCandidateInfo[0].latestElecYear}')"><strong>View Results</strong></a>
					</div>
               </div>
				</c:if>

			




			<div id="candidatesAffidavitSummary" class="cas-view">
			<c:if test="${constituencyDetails.constituencyType == 'Assembly'}">
				<a href="javascript:{}" onclick="getcandidateAssetsAndLiabilities(constiId),getAssetsElectionYearsInfo(constiId)">Candidates Affidavit Summary</a></li>
			<div id="electionYearsPanel_Main_Div" class="innerLayoutDivClass">
								<div id="constituencyPageCandidateAssets_Head" class="layoutHeadersClass"></div>

								<div id="electionYears_Main">
									<div id="electionYearsWithAssets_Div"></div>
								</div>
								<div id="constituencyPageCandidateAssetsInfo_Body" class="layoutBodyClass      yui-skin-sam" style="height:auto;">
										<div id="electionYearsWithAssets_Panel_Div"></div>
										<div id="constituencyPageCandidateNominationsInfo_Bottom"></div>
								</div>
								</div>
								
								<div id="electionYearsPanel_Main_Div" class="innerLayoutDivClass">
								<div id="constituencyPageCandidateAssets_Head" class="layoutHeadersClass"></div>

				</c:if>
				</c:if>
				     </div>          
					
								

								
								
              
              <li>
                <div class="eci-field-cont-sec">
                  <div class="eci-place-title">Parliament Candidate :
	</div>
                  <div class="eci-party-title"><strong class="tc-tf">Party</strong></div>
                  <div class="clear"></div>
                  <div class="eci-place-cont">
				  <strong class="tc-tf">${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName}</strong>  <a href="candidateElectionResultsAction.action?candidateId=${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}">  -View Profile</a></div>
                  <div class="eci-party-cont"><img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.parliamentCandidateInfo.partyFlag}" height="30" width="40"/><span class="party-symbol-code">${candidateDetailsForConstituency.parliamentCandidateInfo.party}</span> </div>
                  <div class="pl-sub-but"><a href="javascript:{}" onclick="getConstituencyElecResultsWindow(${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId},'${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType}','${candidateDetailsForConstituency.parliamentCandidateInfo.latestElecYear}')"><strong>View Results</strong></a>

				
				 </div>
                </div>


				
                <div class="cas-view">
				<c:if test="${constituencyDetails.constituencyType == 'Parliament'}"><a href="javascript:{}" onclick="getcandidateAssetsAndLiabilities(constiId),getAssetsElectionYearsInfo(constiId)">Candidates Affidavit Summary</a> </li>

						



					<div id="electionYearsPanel_Main_Div" class="innerLayoutDivClass">
								<div id="constituencyPageCandidateAssets_Head" class="layoutHeadersClass"></div>

								<div id="electionYears_Main">
									<div id="electionYearsWithAssets_Div"></div>
								</div>
								<div id="constituencyPageCandidateAssetsInfo_Body" class="layoutBodyClass      yui-skin-sam" style="height:auto;max-height:400px;overflow-y:auto">
										<div id="electionYearsWithAssets_Panel_Div"></div>
										<div id="constituencyPageCandidateNominationsInfo_Bottom"></div>
								</div>
								</div>
								
								<div id="electionYearsPanel_Main_Div" class="innerLayoutDivClass">
								<div id="constituencyPageCandidateAssets_Head" class="layoutHeadersClass"></div>
				</c:if>



				</div>


			
          
			 </ul>
			</c:if>
			</div>
          
          <!--ELECTED CANDIDATE INFO SECTION END--> 
          
          <!--ELECTED INFORMATION SECTION START-->
          
          <div class="cl-sub-fields-sec">
            <h1 class="blu-title"><span>Election Information in ${constituencyDetails.constituencyName}</span></h1>
            <div class="detailed-ele-inf"> 
            </div>
             
            
			
            <div class="clear"></div>
			<div id="detailedChartDIV" class="yui-skin-sam"></div>
				<div id="constituencyPageElectionEnlargedImgDiv" class="layoutHeadersClass"></div>
					<div id="constituencyPageElectionImgDiv" ></div>
						<div id="constituencyPageElectionInfoDiv_Body" class="layoutBodyClass"></div>
							
		
            
            <p class="detailed-ele-inf" style="display:block"> <span class="fleft" style="font-weight:bold;">Detailed Election Information</span><span>click here</span>
			<span id="minusPlusDiv"><a  onclick="detailedElectionResult()"> <img src="images/plus.png" alt="" class="fleft" style="padding:3px 0px 0px 5px;"/></span></a></p>
			<div id="detailedElectionInfoDiv_Body"></div>
          </div>
          
		 
          <!--ELECTED INFORMATION SECTION END--> 
          
          <!--MANDALS VOTERS SECTION START-->
          
          <div class="cl-sub-fields-sec">
		  
            <h1 class="pur-title"><span>Mandals Voters Details Of ${constituencyDetails.constituencyName} Assembly:</span></h1>
            <ul class="mvd-fields" id="MandalsDelimit">
              <li><a href="javascript:{}" onclick="showMandalsAfterDelimitationDiv(constiId)" class="active" style="cursor:pointer"><strong>Mandals After Delimitation</strong></a></li>
              <li><a onclick="showMandalsBeforeDelimitationDiv(constiId)" style="cursor:pointer"><strong>Mandals Before Delimitation</strong></a></li>
            </ul>
			
            <div class="clear"></div>
            <div class="mandals-voters-sec" style="float:none;margin-left:auto;margin-right:auto;padding:0 0 15px 0px;">
			
						<div id="constituencyCenterContentOuter1" class="rounded"> 						
						

						<div id="divInteractive_Chart_0"></div>
						<div id="divInteractive_Chart_1"></div>

						<div id="mandalsVotersInfoDiv_Main" class="innerLayoutDivClass">
						<div id="mandalsVotersInfoDiv_Head" class="layoutHeadersClass"></div>
							
						<div id="mandalsVotersInfoDiv_Body" class="mv-full-details yui-dt"></div>
							
						</div>
				
						</div>
						
		
				</div>
			</div>
          
       
          
          <!--MANDALS VOTERS SECTION END--> 
          
          <!--ALL LOCAL ELECTIONS CONSITITUENCY SECTION START-->


		  
          
          <div class="cl-sub-fields-sec ale-sec">
            <h1 class="gre-title"><span>All Local Elections Happened In ${constituencyDetails.constituencyName} Constituency</span></h1>
			
			<ul class="mvd-fields" id="LocalElectionsNav">

              <li id="muncipalityDiv"><a href="javascript:{}" onclick="showMunicipalityResults(),getMunicipalityResults()" class="active"><strong>MUNCIPALITY</strong></a></li>

              <li id="ZptcDiv"> 
			
			
			  <a href="javascript:{}" onclick="showZptcPartyDetails()" style="cursor:pointer"><strong>ZPTC's</strong></a></li>
			  
              <li id="mptcDiv"><a href="javascript:{}" onclick="showMptcPartyDetails()" style="cursor:pointer"><strong>MPTC's</strong></a></li>
			   <li id="greterMunicipalDiv"><a href="javascript:{}" onclick="showGreterElectionDetails()" style="cursor:pointer"><strong>GREATER MUNICIPALCORP</strong></a></li>
			    <li id="corporationTabDiv"><a href="javascript:{}" onclick="showCorporationDetails()"  style="cursor:pointer"><strong>CORPORATION's</strong></a></li>


            </ul>

			 <div id="muncipalDivBody" class="detailed-ele-inf">
			<div id="MuncipalElectionSelectDiv" style="float:left;width:auto;margin-top:-4px;">Select Election Year:
				<s:select theme="simple" id="municipalitySelect" name="municipalities" list="municipalElections" listKey="id" listValue="name"></s:select>&nbsp&nbsp
						
			</div>
			
			 <div id = "muncipalDiv"></div>
			 <div id="showMoreMuncipalResultsDiv" style="clear:right;margin-left:3px;text-decoration: none;"></div>
					<div id="municipalityData_main"></div>
					
			</div>
			<!--corporationDiv-->
					<div id = "corporationDiv">
					
									<div id="corporationHeadingDIV" style="display:block;clear:both;">
									
									<table>
											<tr>
												<td>
													<b>Select Corporation Election&nbsp;:&nbsp;</b><s:select theme="simple" id="corporationSelect" label="Select Cororation Election" name="corporations" list="corporateElections" listKey="id" listValue="name" onchange="getCoroporationResults()"></s:select>
												</td>
												<td>
													<div id="showMoreCorporationResultsDiv"></div>										
												</td>
											</tr>
										</table>	
									</div>
									<div id="coroporationData_main"></div>
								</div>
									
								<div id = "greaterDiv" style="display:block;clear:both;">
									<div>
									<table>
											<tr>
												<td>
													<b>Select Greater Election&nbsp;:&nbsp;</b><s:select theme="simple" id="greaterSelect" label="Select Greater Election" name="greaters" list="greaterElections" listKey="id" listValue="name" onchange="getGreaterResults()"></s:select>
													</td>
												<td>
													<div id="showMoreGreaterResultsDiv"></div>										
												</td>
											</tr>
										</table>
									</div>
									<div id="wardsElectionResults_body" class="productFeatureBody yui-skin-sam">
											<div id="wardsElectionResults_body_radioSelectDiv" style="padding:5px;font-weight:bold;">
												Select Results Criteria :
												<input type="radio" name="wardWiseElectionRadio" onclick="changeWardWiseResultsCriteria(this.value)"  value="all" checked="checked"/>All
												<input type="radio" name="wardWiseElectionRadio" onclick="changeWardWiseResultsCriteria(this.value)" value="partyWise"/>Party Wise Results	
												<s:select theme="simple" cssClass="selectBoxWidth" cssStyle="visibility:hidden;width:100px;" name="wardWise_parties" id="wardWise_parties" list="greaterInfo.listOfParties" listKey="id" listValue="name" headerKey="0" headerValue="Select" onchange="getWardWiseElectionResults('partyWise',this.options[this.selectedIndex].value)"/>
												<input type="radio" name="wardWiseElectionRadio" onclick="changeWardWiseResultsCriteria(this.value)" value="wardWise"/>Ward Wise Results	
												<s:select theme="simple" cssClass="selectBoxWidth" cssStyle="visibility:hidden;width:100px;" name="wardWise_wards" id="wardWise_ward" list="greaterInfo.listOfWards" listKey="id" listValue="name" headerKey="0" headerValue="Select" onchange="getWardWiseElectionResults('wardWise',this.options[this.selectedIndex].value)"/>
											</div>											
										</div>
									<div id="GHMCData_main"></div>
								</div><!--
							</td>
						</tr>
					</table>
				</div>-->
	

			

            <div id="zptcDivBody" class="detailed-ele-inf">

			<div id="headDiv" style="color: #5CB275;text-decoration: none;display:block;clear:left;padding:10px;">Detailed View of ${constituencyDetails.constituencyName} Zptc Election Results</div>
			
				
				<div id="zptcElectionIdsSelectDiv"></div>
					<div id="zptcCandidateLink" ></div>
					<div id="countZptcDiv">
			<span>Total Number of ZPTC's :<span id="totalZptcCountResultDiv" style="display:inblock";></span> </span></div>
						<div id="zptcPartyTrendsDetailsDiv"></div>
						
			 </div>
			<div id="mptcDivBody" class="detailed-ele-inf">
				<div id="mptcheadDiv" style="color: #5CB275;text-decoration: none;display:block;clear:both;padding:10px;">Detailed View of ${constituencyDetails.constituencyName} Mptc Election Results</div>
			<div id="mptcElectionIdsSelectDiv"></div>
				<div id="mptcCandidateLink"></div>
				<div id="countMptcDiv">
				<span style="float:left";>Total Number of MPTC's : </span>
					<span id="totalMptcCountResultDiv" style="float:left";></span>
			  
						</div>								
							<div id="mptcPartyTrendsDetailsDiv"></div>
							
				</div>
		
          
			 </div>
		
        
          <!--ALL LOCAL ELECTIONS CONSITITUENCY SECTION END-->
          
          <div class="clear"></div>
		  	
<c:if test="${userDetails.loginStatus}">
		<div id="mandalwisevotingTrendz"class="cl-sub-fields-sec ale-sec">
		
		<c:if test="${constituencyDetails.viewCompletePage}">
			<div id="MandalwiseVotingTrendz" class="rounded">
				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
				<div class="corner bottomLeft"></div>
				<div class="corner bottomRight"></div>
				<div id="MandalVotingTrendz_head"></div>
				<div id="electionIdsSelectDiv" style="padding-left:10px;"></div>
				<div id="censusSelectDiv"></div>
				<div id="censusErrorMsgDiv" style="padding-left:10px;"></div>
				<div id="mandalOrConstiElecResultDiv">
				<div id="parliamentElectionResultsDiv" style="overflow:auto;"></div>
				<div id="electionResultsInConstituencyDiv"></div>
				<div id="labelRadioDiv"></div>			
				<div id="resultsDataTableDiv"></div>
				<div id="missingDataInfoDiv"></div>
				
				</div>
						
			</div>
		</c:if>
		
		</div>
			</c:if>	
        </div>
	</div>
	   

     

      <!--CIBSTUTYEBCT DETAILS LEFT SECTION END--> 

		
	        <!--CIBSTUTYEBCT DETAILS RIGHT SECTION START-->
      
      <div class="cd-right-sec"> 
        
		  <div class="cp-sub-field-sec">

		
		<c:if test="${constituencyDetails.hasAnalize}">
			<input type="button" class="button" style="background-color:#3897A5;cursor:pointer;" value="${constituencyDetails.constituencyName} Detailed Analysis" onclick="openConstVotingTrendzWindow('${constituencyDetails.districtId}','${constituencyDetails.constituencyId}','${constituencyDetails.constituencyName}')" />
		</c:if>
	
	</div>
						
        <!--VIEW YOUR PROBLEMS SECTION START-->
        
        <div class="vy-problems-sec">
          <h1 class="vyp-title"><img src="images/view-your-problems.png" alt=""/></h1>
        <div class="vy-fields-sec">
        <div class="post-problem">
              <div>Post your constituency problem and<br />
                bring it to the all people notice.</div>
				<div id="problemPostingDiv_Head"></div>
				<div id="problemPostingDiv_Body"></div>
				<div id="problemViewingDiv">
								
							</div>
			
              
            </div>
			<div id="problemViewingDiv_Head"></div>
			<div id="problemViewingDiv_Body"></div>
			
            
        </div>
        
        <!--VIEW YOUR PROBLEMS SECTION END--> 
        
        <!--CD SUB RIGHT SECTION START-->
        
        <div class="cd-sub-right-sec">
          <div class="cd-sub-right-cont"> 
            
            <!--CONNECT PEOPLE SECTION START-->
            
            <div class="connect-people-sec">
              <div class="cp-title-sec">
               <h1 class="cp-title">Connect To Your Constituency People</h1>
           </div>
              <div class="cp-cont-sec">
			  <div id="constituencyPeopleConnectDiv">
								<!--<div id="constituencyPeopleConnectDiv_Head"></div>-->
								<div id="constituencyPeopleConnectDiv_Body"></div>
							</div>

			  </div>
                
                  
				  
				  
               
              <!-- <div class="view-all">-->

			   <div id="connectPeoplePopup_outer" class="yui-skin-sam">
			<div id="connectPeoplePopup"><div id="allConnectedUsersDisplay_main"></div></div>
			</div>
				 </div>
              </div>
              <div class="cp-bbg"></div>
            </div>
            <div id="connectPeoplePopup"></div>
			
            <!--CONNECT PEOPLE SECTION END--> 
            
            <!--ANNOUNCEMENTS SECTION START-->
            
            <div class="cp-sub-field-sec">
			
					
			  <h1 class="cp-sub-title"><span>Announcements</span></h1>

			 
              <div class="cp-cont-sub-fields">
               <ul>
                  <li>
                    <div id="constituencyAnnouncementsDiv" class="rounded"> 						
						
							<div id="announcements_div_main" class="innerLayoutDivClass">
								<div id="announcementsInConstituencyDiv" style="height:auto;max-height:550px;overflow-y:auto;margin-top:10px;">
								</div>
								<div id="announcementsOfAUserDiv" >
								</div>
							</div> </li></ul>
                 
              </div>
            </div>
            
            <!--ANNOUNCEMENTS SECTION END--> 
            
            <!--ASSESS YOUR PARTY RESULTS SECTION START-->
            
            <div class="cp-sub-field-sec">
              <h1 class="cp-sub-title"><span>Assess Your Party Results</span></h1>
              <div class="cp-cont-sub-fields">
                <ul>
                  <li>
                    <p>Assess your constituency election results and post your reasons for winning/loosing .</p>
                    <p class="ass-pre">
					<div id="analyzeConstituencyPageDiv_main">
								<div id="analyzeConstituencyPageDiv_Head"></div>
								<div id="analyzeConstituencyPageDiv_Body"></div>
							</div>
					</p>
                  </li>
                </ul>
              </div>
            </div>
            
            <!--ASSESS YOUR PARTY RESULTS SECTION END--> 
            
            <!--LOCATION MAP SECTION START-->
            
            <div class="cp-sub-field-sec">
              <h1 class="cp-sub-title"><span>Location Map</span></h1>
              <div id="map_canvas" style="position: relative; background-color: rgb(229, 227, 223); height: 283px; margin-top: 41px; width: 311px;"></div>
            </div>
            
            <!--LOCATION MAP SECTION END--> 
            
            <!--NEWS AND UPDATES SECTION START-->
			<c:if test="${userDetails.loginStatus}">
            <div class="cp-sub-field-sec">
			  <c:if test="${constituencyDetails.votingTrendz}">
			 	<div  class="cp-sub-field-sec" id="votingTrendzDiv_Body">
					 </div>
				</c:if>						
			 
                
             
            
            <!--NEWS AND UPDATES SECTION END--> 
            
          </div>
</c:if>

        </div>
        
        <!--CD SUB RIGHT SECTION END--> 
        
      </div>
      
		
      <!--CIBSTUTYEBCT DETAILS RIGHT SECTION END--> 
	<script type="text/javascript">
	
var defDate = constituencyPageMainObj.constituencyInfo.deformDate;
	userLoginStatus = '${userDetails.loginStatus}';
	userId = '${userDetails.userId}';
	var userId = "${sessionScope.USER.registrationID}";
	var userType = "${sessionScope.USER.userStatus}";
	var loginStat = "${sessionScope.loginStatus}";
	var constituencyResults,createGroupDialog;
	
	/*	Constituency Page basic Info
		-----------------------------
	*/
	constituencyPageMainObj.constituencyAddress="${constituencyDetails.constituencyName},${constituencyDetails.districtName},${constituencyDetails.stateName}";
	constituencyPageMainObj.contextPath = "<%=request.getContextPath()%>";	
	
	constituencyPageMainObj.forwardTask = "${redirectLoc}";
	constituencyPageMainObj.constituencyInfo.constituencyId = "${constituencyId}";
	constituencyPageMainObj.constituencyInfo.constituencyName = "${constituencyDetails.constituencyName}";
	constituencyPageMainObj.constituencyInfo.districtName = "${constituencyDetails.districtName}";
	constituencyPageMainObj.constituencyInfo.stateName = "${constituencyDetails.stateName}";
	constituencyPageMainObj.constituencyInfo.startDate = "${constituencyDetails.startDate}";
	constituencyPageMainObj.constituencyInfo.deformDate = "${constituencyDetails.deformDate}";
	constituencyPageMainObj.constituencyInfo.constituencyType = "${constituencyDetails.constituencyType}";
	constituencyPageMainObj.constituencyInfo.reservation_zone = "${constituencyDetails.reservation_zone}";	
	
	<c:forEach var="parliament" items="${navigationVO.pcsInfo}">
		parliamentConstiId = "${parliament.id}";
		parliamentConstiName = "${parliament.name}";		
	</c:forEach>


/*	Assembly Candidate Info
		-------------------------
	*/
	<c:if test="${candidateDetailsForConstituency.assemblyCandidateInfo != null}">
	<c:forEach var="cInfo" items="${candidateDetailsForConstituency.assemblyCandidateInfo}">	
	var candidateObj={
						constituencyId : '${cInfo.constituencyId}',
						constituencyName : '<a href="constituencyPageAction.action?constituencyId=${cInfo.constituencyId}"> ${cInfo.constituencyName}</a>',
						constituencyType:'${cInfo.constituencyType}',
						deformDate:'${cInfo.deformDate}',
						candidateId : '${cInfo.candidateId}',
						candidateName:'<a href="candidateElectionResultsAction.action?candidateId=${cInfo.candidateId}"> ${cInfo.candidateName}</a>',	
						partyId:' ${cInfo.partyId}',
						party : '${cInfo.party}',
						partyFlag : '<img src="<%=request.getContextPath()%>/images/party_flags/${cInfo.partyFlag}" height="30" width="40"/>',
						knowMore:'<a href="javascript:{}" onclick="getConstituencyElecResultsWindow(\'${cInfo.constituencyId}\',\'${cInfo.constituencyType}\',\'${cInfo.latestElecYear}\')">view results</a>'
					 };		
	
	constituencyPageMainObj.presentAssemblyCandidate.push(candidateObj);
	</c:forEach>
	</c:if>
	
	
	/*	Parliament Candidate Info
		-------------------------
	*/
	<c:if test="${candidateDetailsForConstituency.parliamentCandidateInfo != null}">
	var pmtObj = {
					constituencyId : '${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}',
					constituencyName :  '<a href="constituencyPageAction.action?constituencyId=${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}">${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyName}</a>',									
					constituencyType:'${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType}',
					deformDate:'${candidateDetailsForConstituency.parliamentCandidateInfo.deformDate}',
					candidateId : '${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}',
					candidateName:'<a href="candidateElectionResultsAction.action?candidateId=${candidateDetailsForConstituency.parliamentCandidateInfo.candidateId}"> ${candidateDetailsForConstituency.parliamentCandidateInfo.candidateName}</a>',		
					partyId:' ${candidateDetailsForConstituency.parliamentCandidateInfo.partyId}',
					party : '${candidateDetailsForConstituency.parliamentCandidateInfo.party}',
					partyFlag : '<img src="<%=request.getContextPath()%>/images/party_flags/${candidateDetailsForConstituency.parliamentCandidateInfo.partyFlag}" height="30" width="40"/>',
					knowMore:'<a href="javascript:{}" onclick="getConstituencyElecResultsWindow(\'${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyId}\',\'${candidateDetailsForConstituency.parliamentCandidateInfo.constituencyType}\',\'${candidateDetailsForConstituency.parliamentCandidateInfo.latestElecYear}\')">view results</a>'
				 };		
	constituencyPageMainObj.presentParliamentCandidate.push(pmtObj);
	</c:if>






	/*	Constituency Election Results Info
		----------------------------------
	*/

	<c:forEach var="constituencyElectionResults" items="${constituencyElectionResultsVO}">	
	var constiObj=
				{
					candidateName:'${constituencyElectionResults.candidateResultsVO.candidateName}',
					partyName:'${constituencyElectionResults.candidateResultsVO.partyName}',
					year:'${constituencyElectionResults.electionYear}',
					votesEarned:'${constituencyElectionResults.candidateResultsVO.votesEarned}',
					votesPercentage:'${constituencyElectionResults.candidateResultsVO.votesPercentage}',
					oppositionCandInfo:[]
				 };
			<c:forEach var="detailedResult" items="${constituencyElectionResults.candidateOppositionList}" >
				var oppositionList={
										candidateName:'${detailedResult.candidateName}',
										partyName:'${detailedResult.partyName}',
										year:'${constituencyElectionResults.electionYear}',
										votesEarned:'${detailedResult.votesEarned}',
										votesPercentage:'${detailedResult.votesPercentage}'										
									};
						
					constiObj.oppositionCandInfo.push(oppositionList);
			</c:forEach>			
			constituencyPageMainObj.constituencyElectionInfo.push(constiObj);			
	</c:forEach>

	/*	Constituency problems Info
		-------------------------
	*/
	
	<c:forEach var="problem" items="${problemBean}">	

	var probDesc = "${problem.description}";
    probDesc = probDesc.replace("&#39;","&#92;&#39;");

	var problemObj={
						problemId:'${problem.problemId}',
						problem:'${problem.problem}',
						//description:'${problem.description}',
						description:probDesc,
						state:'${problem.state}',
						district:'${problem.district}',
						constituency:'${problem.constituency}',
						tehsil:'${problem.tehsil}',
						village:'${problem.village}',
						hamlet:'${problem.hamlet}',
						reportedDate:'${problem.postedDate}',
						existingFrom:'${problem.existingFrom}',
						name:'${problem.name}',
						postedPersonName:'${problem.postedPersonName}',
						email:'${problem.email}',						
						phone:'${problem.phone}',
						mobile:'${problem.mobile}',
						address:'${problem.address}',
						problemLocationId:'${problem.problemLocationId}',
						problemHistoryId:'${problem.problemHistoryId}',
						acceptedCount: '${problem.acceptedCount}',
						rejectedCount: '${problem.rejectedCount}'
					};
		
	constituencyPageMainObj.problemsInfo.push(problemObj);
	</c:forEach>

	/*
		Voting Trendz Info 
		------------------
	*/	

		var vTObj = constituencyPageMainObj.electionTrendzReportVO;

		vTObj.state = '${electionTrendzReportVO.state}';
		vTObj.electionType = '${electionTrendzReportVO.electionType}';
		vTObj.electionYear = '${electionTrendzReportVO.electionYear}';
		vTObj.constituencyId = '${electionTrendzReportVO.constituencyId}';
		vTObj.constituencyName = '${electionTrendzReportVO.constituencyName}';
				

		vTObj.electionTrendzOverviewVO.totalVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.totalVoters}';
		vTObj.electionTrendzOverviewVO.maleVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVoters}';
		vTObj.electionTrendzOverviewVO.femaleVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVoters}';
		vTObj.electionTrendzOverviewVO.maleAndFemaleVoters = '${electionTrendzReportVO.electionTrendzOverviewVO.maleAndFemaleVoters}';	
		
		vTObj.electionTrendzOverviewVO.totalPolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.totalPolledVotes}';
		vTObj.electionTrendzOverviewVO.malePolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.malePolledVotes}';
		vTObj.electionTrendzOverviewVO.femalePolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.femalePolledVotes}';
		vTObj.electionTrendzOverviewVO.maleAndFemalePolledVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.maleAndFemalePolledVotes}';

		vTObj.electionTrendzOverviewVO.pollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.pollingPercent}';
		vTObj.electionTrendzOverviewVO.malePollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.malePollingPercent}';
		vTObj.electionTrendzOverviewVO.femalePollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.femalePollingPercent}';
		vTObj.electionTrendzOverviewVO.maleAndFemalePollingPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.maleAndFemalePollingPercent}';
		
		vTObj.electionTrendzOverviewVO.maleVotersPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVotersPercent}';
		vTObj.electionTrendzOverviewVO.femaleVotersPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVotersPercent}';
		vTObj.electionTrendzOverviewVO.maleOrFemaleVotersPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.maleOrFemaleVotersPercent}';

		vTObj.electionTrendzOverviewVO.overallMalePollPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.overallMalePollPercent}';
		vTObj.electionTrendzOverviewVO.overallFemalePollPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.overallFemalePollPercent}';
		vTObj.electionTrendzOverviewVO.overallMaleOrFemalePollPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.overallMaleOrFemalePollPercent}';
				
		vTObj.electionTrendzOverviewVO.maleVotersInConstituency = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVotersInConstituency}';
		vTObj.electionTrendzOverviewVO.femaleVotersInConstituency = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVotersInConstituency}';

		vTObj.electionTrendzOverviewVO.maleVotersPercentInConsti = '${electionTrendzReportVO.electionTrendzOverviewVO.maleVotersPercentInConsti}';
		vTObj.electionTrendzOverviewVO.femaleVotersPercentInConsti = '${electionTrendzReportVO.electionTrendzOverviewVO.femaleVotersPercentInConsti}';

		vTObj.electionTrendzOverviewVO.malePolledPercentInTotalPolled = '${electionTrendzReportVO.electionTrendzOverviewVO.malePollingPercentInTotalPolledVotes}';
		vTObj.electionTrendzOverviewVO.femalePolledPercentInTotalPolled = '${electionTrendzReportVO.electionTrendzOverviewVO.femalePollingPercentInTotalPolledVotes}';
		vTObj.electionTrendzOverviewVO.maleOrFemalePolledPercentInTotalPolled = '${electionTrendzReportVO.electionTrendzOverviewVO.maleOrFemalePollingPercentInTotalPolledVotes}';
	
		
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.pollingDetailsChart = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.pollingDetailsChart}';
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.votingTrendzMainChart = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.votingTrendzMainChart}';
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.candOverallVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.candOverallVotesPercent}';
		vTObj.electionTrendzOverviewVO.electionTrendzCharts.candVotingTrendz = '${electionTrendzReportVO.electionTrendzOverviewVO.electionTrendzCharts.candVotingTrendz}';


		<c:forEach var="candidate" items="${electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO}">	
			var cObj = {
							partyId:'${candidate.partyId}',
							partyName:'${candidate.partyName}',
							partyLogo:'${candidate.partyLogo}',
							partyFlag:'${candidate.partyFlag}',
							candidateId:'${candidate.candidateId}',
							candidateName:'${candidate.candidateName}',							
							validVotes:'${candidate.validVotes}',
							totalVotes:'${candidate.totalVotes}',
							maleVotes:'${candidate.maleVotes}',
							femaleVotes:'${candidate.femaleVotes}',
							maleAndFemaleVotes:'${candidate.maleAndFemaleVotes}',
							totalVotesPercent:'${candidate.totalVotesPercent}',
							maleVotesPercent:'${candidate.maleVotesPercent}',
							femaleVotesPercent:'${candidate.femaleVotesPercent}',
							maleAndFemaleVotesPercent:'${candidate.maleAndFemaleVotesPercent}',
							rank:'${candidate.rank}',
							overallMaleVotesPercent : '${candidate.overallMaleVotesPercent}',
							overallFemaleVotesPercent : '${candidate.overallFemaleVotesPercent}',
							overallMaleOrFemaleVotesPercent : '${candidate.overallMaleOrFemaleVotesPercent}',						
							maleVotesPercentInConstiVotes:'${candidate.maleVotesPercentInConstiVotes}',
							femaleVotesPercentInConstiVotes:'${candidate.femaleVotesPercentInConstiVotes}',
							maleOrFemaleVotesPercentInConstiVotes:'${candidate.maleOrFemaleVotesPercentInConstiVotes}',
							status:'${candidate.status}'
					   };
					  vTObj.electionTrendzOverviewVO.partyElectionTrendzVO.push(cObj);
		</c:forEach>
		
		
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.candidateName = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.candidateName}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.partyName = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.partyName}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.totalVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleAndFemaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.overallFemaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.overallFemaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleOrFemaleVotesPercent = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.overallMaleOrFemaleVotesPercent}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.status = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.status}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercentInConstiVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleVotesPercentInConstiVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercentInConstiVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.femaleVotesPercentInConstiVotes}';
		vTObj.electionTrendzOverviewVO.wonCandidateResultTrendz.maleOrFemaleVotesPercentInConstiVotes = '${electionTrendzReportVO.electionTrendzOverviewVO.wonCandidateResultTrendz.maleOrFemaleVotesPercentInConstiVotes}';

		
		<c:forEach var="asmElection" items="${electionTrendzReportVO.prevElectionYearsInfo.assemblyElections}">	
			var assemblyElecObj={
									electionId:'${asmElection.electionId}',
									electionTypeId:'${asmElection.electionTypeId}',
									electionType:'${asmElection.electionType}',
									stateId:'${asmElection.stateId}',
									state:'${asmElection.state}',
									electionYear:'${asmElection.electionYear}' 
								};

			vTObj.previousElectionYears.assemblyElections.push(assemblyElecObj);
		</c:forEach>

		<c:forEach var="parElection" items="${electionTrendzReportVO.prevElectionYearsInfo.parliamentElections}">	
			var parElecObj={
									electionId:'${parElection.electionId}',
									electionTypeId:'${parElection.electionTypeId}',
									electionType:'${parElection.electionType}',
									stateId:'${parElection.stateId}',
									state:'${parElection.state}',
									electionYear:'${parElection.electionYear}'
								};

			vTObj.previousElectionYears.parliamentElections.push(parElecObj);
		</c:forEach>



	<c:forEach var="vInfo" items="${constituencyVO.assembliesOfParliamentInfo}" >	
		var obj ={
					year:'${vInfo.year}',
					info:[]
				};
		<c:forEach var="info" items="${vInfo.votersInfoForMandalVO}" >	
		var urlStr = '';
		if('${constituencyVO.electionType}' == 'Parliament')
			urlStr += 'constituencyPageAction.action?constituencyId=${info.mandalId}';
		else{
			if(${info.isMandal})
				urlStr += 'mandalPageElectionInfoAction.action?MANDAL_ID=${info.mandalId}&MANDAL_NAME=${info.mandalName}';
			else
				urlStr += 'localBodyElectionAction.action?localBodyId=${info.mandalId}';
		}
		
		var vObj=
				{
					mandalId:'${info.mandalId}',
					mandalName:'<a href="'+urlStr+'"> ${info.mandalName}</a>',
					mandalMaleVoters:'${info.totalMaleVoters}',
					mandalFemaleVoters:'${info.totalFemaleVoters}',
					mandalTotalVoters:'${info.totalVoters}',
					isPartial:'${info.isPartial}'
				 };
			obj.info.push(vObj);
		</c:forEach>
			constituencyPageMainObj.constituencyVotersInfo.push(obj);
	</c:forEach>
	<c:forEach var="candidate" items="${userDetails.candidateVO}">	
			var userObj={
								id:'${candidate.id}',
								candidateName:'${candidate.candidateName}',
								status:'${candidate.status}',
								constituencyName:'${candidate.constituencyName}'
							};
				
			constituencyConnectedPeople.push(userObj);
		</c:forEach>

	var tehsilElections={
			zptcElectionYears:[],
			mptcElectionYears:[]
	};
	var totalZptcSeats,totalMptcSeats;
	var hideMptcZptcDiv;
	var counter = 0;
	var myDataTableForParty,myDataTableForMptcParty,zptcElectionYear,mptcElectionYear;
	var mptcElectionTypeId=${mptcElectionId},zptcElectionTypeId=${zptcElectionId};
	var mptcElectionType="${mptcElectionType}",zptcElectionType="${zptcElectionType}";
	
	var tehsilDetails={
			zptcArray:[],
			mptcArray:[],
			partyArray:[],
			partyMptcArray:[]
	};
	
	<c:forEach var="zptcElectionYears"  items="${zptcElectionYears}" >
		var ob={
					id:'${zptcElectionYears.id}',
					value:'${zptcElectionYears.name}'
				};
	tehsilElections.zptcElectionYears.push(ob);	
	</c:forEach>

	<c:forEach var="mptcElectionYears"  items="${mptcElectionYears}" >
		var ob={
					id:'${mptcElectionYears.id}',
					value:'${mptcElectionYears.name}'
				};
	tehsilElections.mptcElectionYears.push(ob);	
	</c:forEach>
	



	function callAjax(jsObj,url)
	{	
	
		
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task=="partiesPerformanceInDiffElectionsAjax")
								{
									showAllPartiesAllElectionResultsChart(myResults);
								
								}

								else if(jsObj.task == "getNextPrevCandidateTrendz")
								{
									buildCandidateVotingTrendzGraphData(jsObj,myResults);
								}										
								else if(jsObj.task == "getVotingTrendzForElectionYears")
								{									
									buildVotingTrendzData(myResults);
								}


								else if(jsObj.task == "getConstituencyElections")
								{		
									buildElectionsSelectBox(myResults);									
								}else if(jsObj.task == "getParliamentConstituencyElectionResults")
								{		
									parliamentResult = myResults;
									buildParliamentResults(myResults);			
								}

								else if(jsObj.task == "getConstituencyResultsBySubLocations")
								{		
									constituencyResults = myResults;
									buildCensusSelect(constituencyResults);
									buildConstituencyElecResultsDataTable("number");
									buiidElecResultRadioSelect();
									buildConstituencyElectionResultsDataTable("number");
								}

								else if(jsObj.task == "getCensusDetailsForAConstituency")
								{
									censusResult = myResults;
									buildCensusChartForAConstituency(myResults);
									buiidCensusRadioSelect();	buildConstituencyElectionResultsDataTableWithCensus(myResults,"number")
								}
								else if(jsObj.task == "getAnnouncementDetailsOfAConstituency")
								{
                                  
									   buildAnnouncementDetails(myResults);
								  
								 /* else
								   {
									   hideAnnouncementDetails();
								   }*/
								}

								else if(jsObj.task == "getUserAnnouncementDetails")
								{
								   hideAjaxImage(jsObj.divId)
								buildUserAnnouncementDetails(jsObj,myResults);
								}





								else if(jsObj.task == "mandalVotesShareDetailsChart")
								{
									showMandalVotesShareDetailsChart(myResults);
									showMandalsAfterDelimitationDiv();
								}

								

								else if(jsObj.task == "getZptcElectionResults")
								{		
								
								
									if(myResults!= null &&  myResults.length>0){
									hideMptcZptcDiv=false;
									buildZptcResults(myResults);		
									}else{
										
										hideMptcZptcDiv =true;
										hideZptcOrMptcDiv('ZPTC');
									}	
								
								}

								else if(jsObj.task == "getMptcElectionResults")
								{	
									
									if(myResults!= null &&  myResults.length>0){
									hideMptcZptcDiv =false;	
									buildMptcResults(myResults);
									}
								
									
										 
								

								else{
											hideMptcZptcDiv =true;
										hideZptcOrMptcDiv('MPTC');
										}
								}	
							

								else if(jsObj.task == "getConstituencyElectionYears")
								{
                                   if(myResults != null && myResults.length > 0)
								  {
                                      buildElectionYearsWithAssets(myResults);
								   }
								}
								else if(jsObj.task == "getCandidateAssetsAndLiabilitiesInfo")
								{
                                   if(myResults != null)
								   {  
									   buildAssetsAndLiabilities(myResults);

								   }
								   hideBusyImgWithId("electionYearSelectForAssets");
								}

								else if(jsObj.task == "getAllConnectedUsers")
								{	
									showAllConnectedUsersInPanel(jsObj,myResults);
								}	
								if(jsObj.task == "connectUserSet")
								{									
									showAllConnectedUsersStatus(jsObj,myResults);
								}
								if(jsObj.task == "getAllConnectedUsersByFilterView")
								{
									showAllConnectedUsersInPanelByFilterView(jsObj,myResults);
								}
								if(jsObj.task == "getCandidateNominationDetails")
								{
									showCandidateNominationsInRecentElections(myResults);
								}
								if(jsObj.task == "sendMessageToConnectUser")
								{						
									showMessageConfirmation(myResults);
								}
								
								
								else if(jsObj.task == "municipalElectionsInfo")
								{		
									
										//totalNoOflocalElectionsBodies++;						
										showMunicipalInfo(myResults);

									}



										else if(jsObj.task == "corporationElectionsInfo")
								{	
																
										showCorporationInfo(myResults);
									
									
										
								}
								else if(jsObj.task == "greaterElectionsInfo" || jsObj.task == "getGhmcResultsBasedOnSelection")
								{		
													
										showGreaterInfo(myResults);
									
										
								}


								/*	else if(jsObj.task == "corporationElectionsInfo")
								{	
									if(myResults.muncipalityVO!=null){
							totalNoOflocalElectionsBodies++;								
										showCorporationInfo(myResults);
									}else{
									totalNoOflocalElectionsBodies--;
									document.getElementById("corporationDiv").style.display = "none";
										checkTohideLocalElectionsBodyDiv();
									}			
								}else if(jsObj.task == "greaterElectionsInfo" || jsObj.task == "getGhmcResultsBasedOnSelection")
								{		
									if(myResults.isDataExists==true){			
										totalNoOflocalElectionsBodies++;				
										showGreaterInfo(myResults);
									}else{
										totalNoOflocalElectionsBodies--;
										document.getElementById("greaterDiv").style.display = "none";
										checkTohideLocalElectionsBodyDiv();
									}			
								}
									*/
								
							}catch (e) {   
							  // 	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                		//	alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}

	
		function openConstVotingTrendzWindow(distId,constId,constName)
			{			
	
		var browser1 = window.open("constituencyVotingTrendzAction.action?districtId="+distId+"&constiId="+constId+"&constiName="+constName,"biElectionConstituencyResults1","scrollbars=yes,resizable=1,height=650,width=900,left=200,top=200");

		browser1.focus();
		}
		
		function getConstituencyElecResultsWindow(constiId,elecType,elecYear)
		{
		
	   var browser1 = window.open("constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
	   browser1.focus();
		}


	function showDetailedChart(chartName)
		{		
		var elmt = document.getElementById('detailedChartDIV');
		var divChild = document.createElement('div');
		divChild.setAttribute('id','createGroupmDiv');

	    var str='';
		str+='<img src="charts/'+chartName+'" />';
		divChild.innerHTML=str;
		elmt.appendChild(divChild);	
		if(createGroupDialog)
			createGroupDialog.destroy();
		createGroupDialog = new YAHOO.widget.Dialog("createGroupmDiv",
				{ width : "800px", 		
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
				  close:true,
				  x:600,
				  y:800
	             } );
		createGroupDialog.render();
	}
	function handleCreateGroupSubmit()
	{
		createGroupDialog.hide();			
	}

	function handleCreateGroupCancel()
	{
		this.cancel();
	}
	function hideComparedResultsDiv()
    {
	var elmt = document.getElementById("detailedChartDiv");
	if(!elmt)
		return;
		
	elmt.style.display = 'none';
	
    }
	

	
	function buildConstituencyElecResultsDataTable(value){
	
	if(constituencyResults == null){
		var mandalwiseVotingTrendzEL = document.getElementById("MandalwiseVotingTrendz");
		if(mandalwiseVotingTrendzEL)
			mandalwiseVotingTrendzEL.style.display = 'none';
		return;
	}
	
	if(constituencyResults.electionType == 'Assembly'){		
		var parliamentButtonDiv = document.getElementById("parliamentResultsButtonDiv");
		var str = '';
		var electionSelect = document.getElementById("electionYearSelect");
		var elecYear = electionSelect.options[electionSelect.selectedIndex].text;
		str += "<table><tr>";
		str += '<td><div class="view-all"><a style="cursor:pointer;" onclick="getParliamentResults('+elecYear+')"/> Parliament(s)</a></td>';

		// Detailed Chart is disabled for Assembly.
		// modified by sai
		/*str += '<td><div><input type="button" class="button" onclick="showDetailedChart(\''+constituencyResults.detailedChartPath+'\')" value="Detailed Chart For Assembly"></div></td>';*/

		str += '<td><div class="view-all"><a  href="municipalWardsAssemblyBoothsMapperAction.action?windowTask=update&constituencyId='+constituencyId+'" >Map Municipal/Corp/GMC to Assembly</a></div></td>';
		str += "</tr></table>";
		parliamentButtonDiv.innerHTML = str;		

		
	}
	if(constituencyResults.electionType != 'Assembly'){		
		var details = document.getElementById("detailsDiv");
		var detailsDIV = '';
		detailsDIV += '<table><tr>';
		detailsDIV += '<td><div><a href="javascript:{}" class="button" onclick="showDetailedChart(\''+constituencyResults.detailedChartPath+'\')" value="Detailed Chart">Detailed Chart</a></div></td>';
		details.innerHTML = detailsDIV;
	}
	
		/*if(counter>=1){
		
		// Parliament Detailed Chart is disabled.
		// Modified by sai

		var details = document.getElementById("detailsDiv");
		var detailsDIV = '';
		detailsDIV += '<table><tr>';
		detailsDIV += '<td><div><input type="button" class="button" onclick="showDetailedChart(\''+constituencyResults.detailedChartPath+'\')" value="Detailed Chart For Parliament"></div></td>';
		details.innerHTML = detailsDIV;
	}*/
	
	var chartResultDiv = document.getElementById("electionResultsInConstituencyDiv");
	var chart = '';
	chart += '<div><img src="charts/'+constituencyResults.chartPath+'" width="500" height="278"/></div>';
	chartResultDiv.innerHTML = chart;
	var imgElmt = document.getElementById('AjaxImgDiv');
	if(imgElmt.style.display == "block")
	{
          imgElmt.style.display = "none";
	}
    var conName = constituencyResults.constituencyName;
	var elecYear = constituencyResults.electionYear;
	var elecTyp = constituencyResults.electionType;
    getInteractiveChart(chartResultDiv,constituencyResults.constituencyOrMandalWiseElectionVO,constituencyResults.candidateNamePartyAndStatus,elecTyp,conName,elecYear);
		
	
	
	}

	function getParliamentResults(elecYear){
	counter++;	
	var jsObj = {
			constituencyId:${constituencyId},
			electionYear:elecYear,
			task:"getParliamentConstituencyElectionResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getParliamentMandalResultsAction.action?"+rparam;
	callAjax(jsObj, url);
	}


	function buildParliamentResults(){
		var parliamentDiv = document.getElementById("parliamentElectionResultsDiv");
		if(parliamentDiv.style.display == "none")
		{
			parliamentDiv.style.display = "block";
		}
		var str = '';
		str += '<table>';
		str += '<th>Select Format You Want:</th>';
		str += '<td><input type="radio" name="parliament" value="number" checked="checked" onclick="buildParliamentResultDT(this.value)">By Votes</td>';
		str += '<td><input type="radio" name="parliament" value="percentage" onclick="buildParliamentResultDT(this.value)">By Percentage</td>';
		str += '</table>';
		str += '<div id="resultDataTableDiv"></div>';
		parliamentDiv.innerHTML = str;
		buildParliamentResultDT("number");
	}

		function buildParliamentResultDT(checked){
		var parliamentDiv = document.getElementById("resultDataTableDiv");
		var str = '';
		var details = document.getElementById("detailsDiv");
		var detailsDIV = '';

		if(parliamentResult == null){
			detailsDIV += 'No Data Available';
			return;	
		}
		
		str += '<div id="parliamentChartDiv"><img src="charts/'+parliamentResult.chartPath+'"></div>';
		
		// Parliament Detailed Chart is disabled.
		// Modified by sai

		/*detailsDIV += '<div><input type="button" class="button" onclick="showDetailedChart(\''+parliamentResult.detailedChartPath+'\')" value="Detailed Chart For Paliament"></div>';	*/
		
		str += '<div id="parliamentElecResDiv" style="margin-top:20px;">';
		str += '<table id = "parliamentElecResTable">';
		for(var j in parliamentResult.constituencyOrMandalWiseElectionVO){
			str += '<tr>';
			if(parliamentResult.constituencyOrMandalWiseElectionVO[j].showLink)
				str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationId+'&MANDAL_NAME='+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationName+'">'+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationName+'</a></td>';
			else
				str += '<td>'+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationName+'</td>';
			for(var k in parliamentResult.constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs){
				if(checked == 'number')
					str += '<td>'+parliamentResult.constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs[k].votesEarned+'</td>';
				else
					str += '<td>'+parliamentResult.constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs[k].votesPercentage+'</td>';
			}
			str += '</tr>';
		}
		str += '</table>';
		str += '</div>';
		
		parliamentDiv.innerHTML = str;
		if(counter!=0){
			details.innerHTML = detailsDIV;
		}
		
		 var myColumnDefs = new Array();
		 var myFields = new Array();
		 
		 var villageHead = {
		 			key:"Mandal",
		 			lable: "Mandal",
		 			sortable:true
			   }

		 var villageValue = {key:"Mandal"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
		 

		 for(var j in parliamentResult.candidateNamePartyAndStatus){
			var obj1 = {
						key:parliamentResult.candidateNamePartyAndStatus[j].party +'['+parliamentResult.candidateNamePartyAndStatus[j].rank+']',
						label:parliamentResult.candidateNamePartyAndStatus[j].party +'['+parliamentResult.candidateNamePartyAndStatus[j].rank+']',
						sortable:true
					}
			var obj2 = {
						key:parliamentResult.candidateNamePartyAndStatus[j].party +'['+parliamentResult.candidateNamePartyAndStatus[j].rank+']',
						parser:"number"
					}
			myColumnDefs.push(obj1);
			myFields.push(obj2);
		 }

		 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
					.get("parliamentElecResTable")); 
		 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
		 myDataSource.responseSchema = { 
								            fields:myFields    
								        };
		        
		var villageDataTable = new YAHOO.widget.DataTable("parliamentElecResDiv",myColumnDefs, myDataSource);
		
		var conName = parliamentResult.constituencyName;
		var elecYear = parliamentResult.electionYear;
		var elecTyp = parliamentResult.electionType;
		var parlDivElmnt = document.getElementById("parliamentChartDiv");
		getInteractiveChart(parlDivElmnt, parliamentResult.constituencyOrMandalWiseElectionVO,parliamentResult.candidateNamePartyAndStatus,elecTyp,conName,elecYear);
	}
	

//All Parties Performance In Different Elections Linechart
	function showAllPartiesAllElectionResultsChart(myResults)
	{
		
   var chartColumns = myResults[0].partiesList;
  
     var data = new google.visualization.DataTable();
	
	 data.addColumn('string', 'Party');
 
     var partiesArray = new Array();
     //for chart columns
	 for(var i in chartColumns)
	 {
		
	   var colData = chartColumns[i].name;
	  
	   data.addColumn('number', colData);

	   partiesArray.push(chartColumns[i].name);
	 }

      //for chart rows
	  for(var j in myResults)
	  {
		  
		  var array = new Array();
		  var year = myResults[j].electionYear+" "+myResults[j].electionType;
		  array.push(year);

		  for(var k in myResults[j].partyResultsVO)
		  {
			  var percentage = myResults[j].partyResultsVO[k].votesPercent;
              array.push(percentage);
		  }
		 
		  data.addRow(array);
	  }

	  var ctitle = 'All Parties Performance In Different Elections'; 
	 var chartResultDiv = document.getElementById("constituencyPageElectionImgDiv");

      //static colors for parties
      var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);

	  if(staticColors != null && staticColors.length > 0)
	  {
		  new google.visualization.LineChart(chartResultDiv).
			  draw(data, {curveType: "function",width: 623, height: 400,title:ctitle,colors:staticColors,pointSize: 4,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:40}});
	  }
	  else
	  {
          new google.visualization.LineChart(chartResultDiv).
			  draw(data, {curveType: "function",width: 623, height: 400,title:ctitle,pointSize: 4,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:40}});
	  }
}

//Mandals Voters Details  piechart
	function showMandalVotesShareDetailsChart(myResults)
	{
	
	
	var mandalwiseVotersShare = myResults.assembliesOfParliamentInfo;
			
		for(var c in mandalwiseVotersShare){

			var data = new google.visualization.DataTable();
			
			data.addColumn('string', 'Mandals');
			data.addColumn('number', 'Voters % Share');


			data.addRows(mandalwiseVotersShare[c].votersInfoForMandalVO.length);
			var k=0;
			for (var i in mandalwiseVotersShare[c].votersInfoForMandalVO)
			{
			data.setValue(k, 0, mandalwiseVotersShare[c].votersInfoForMandalVO[i].mandalName);
			data.setValue(k, 1,  mandalwiseVotersShare[c].votersInfoForMandalVO[i].totVoters);
			k++;
			}
			
			var ctitle='';
			var chartDiv='';
          
			if(c == 0)
			{
				if(document.getElementById('divInteractive_Chart_0')){
					
			
					if(c == 0){
						chartDiv = document.getElementById('divInteractive_Chart_0');
						if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Parliament')
						ctitle = 'Assembly Voters % Share In '+constituencyPageMainObj.constituencyInfo.constituencyName+' In 2009';
						else 
						ctitle = 'Mandals Voters % Share In '+constituencyPageMainObj.constituencyInfo.constituencyName+' In 2009';
					}
					var chart = new google.visualization.PieChart(chartDiv);
					chart.draw(data, {width: 580, height: 320, title: ctitle, legendFontSize:14,fontSize:13,titleFontSize:16,tooltipFontSize:15, stroke:3});
				}
			}
			else
			{
				if(document.getElementById('divInteractive_Chart_1')){
					
					
					if(c == 1){
					chartDiv = document.getElementById('divInteractive_Chart_1');
					if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Parliament')
					ctitle = 'Assembly Voters % Share In '+constituencyPageMainObj.constituencyInfo.constituencyName+' In 2004';
					else
					ctitle = 'Mandals Voters % Share In '+constituencyPageMainObj.constituencyInfo.constituencyName+' In 2004';
					}
					var chart = new google.visualization.PieChart(chartDiv);
					chart.draw(data, {width: 580, height: 320, title: ctitle, legendFontSize:14,fontSize:13,titleFontSize:16,tooltipFontSize:15, stroke:3});
					
					
				}
			}
			
		}
		buildCenterVotersCandidateInfoContent();
}

	function getZptcPartyDetails(elecYear){
	
	if(document.getElementById('mptcPartyTrendsDetailsDiv').style.display=='block')
		document.getElementById('mptcPartyTrendsDetailsDiv').style.display='none';
	if(document.getElementById('municipalityData_main').style.display=='block')
		document.getElementById('municipalityData_main').style.display='none';

	if(document.getElementById('zptcPartyTrendsDetailsDiv').style.display=='none')
		document.getElementById('zptcPartyTrendsDetailsDiv').style.display='block';
	
	zptcElectionYear = elecYear;
	constituencyTYPE = constituencyPageMainObj.constituencyInfo.constituencyType;
	var jsObj = {
			constituencyType:constituencyPageMainObj.constituencyInfo.constituencyType,
			constituencyId:${constituencyId},
			electionYear:elecYear,
			task:"getZptcElectionResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/constituencyWiseMandalElectionsResultAction.action?"+rparam;
	callAjax(jsObj, url);
	}

	function getMptcPartyDetails(elecYear){
	
	if(document.getElementById('mptcPartyTrendsDetailsDiv').style.display=='none')
		document.getElementById('mptcPartyTrendsDetailsDiv').style.display='block';
	if(document.getElementById('zptcPartyTrendsDetailsDiv').style.display=='block')
		document.getElementById('zptcPartyTrendsDetailsDiv').style.display='none';
	if(document.getElementById('municipalityData_main').style.display=='block')
		document.getElementById('municipalityData_main').style.display='none';
	
	
	constituencyTYPE = constituencyPageMainObj.constituencyInfo.constituencyType;

	mptcElectionYear = elecYear;
	var jsObj = {
			constituencyType:constituencyPageMainObj.constituencyInfo.constituencyType,
			constituencyId:${constituencyId},
			electionYear:elecYear,
			task:"getMptcElectionResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/constituencyWiseMandalElectionsResultAction.action?"+rparam;
	callAjax(jsObj, url);
	}
	function hideZptcOrMptcDiv(election)
	{
		
		
	var divEle = null;
	if(election == 'MPTC')
	{
		divEle = document.getElementById('mptcPartyTrendsDetailsDiv');
			document.getElementById('mptcheadDiv').innerHTML ='';
			document.getElementById('countMptcDiv').innerHTML ='';
		document.getElementById('mptcCandidateLink').innerHTML ='';
			document.getElementById('mptcElectionIdsSelectDiv').innerHTML ='';
		//document.getElementById('mptcDivBody').innerHTML ='';
	}
	else if(election == 'ZPTC')
	{

		divEle = document.getElementById('zptcPartyTrendsDetailsDiv');
			document.getElementById('headDiv').innerHTML ='';
			document.getElementById('countZptcDiv').innerHTML ='';
		document.getElementById('zptcCandidateLink').innerHTML ='';
			document.getElementById('zptcElectionIdsSelectDiv').innerHTML ='';
						
	}
		document.getElementById('ZptcDiv').innerHTML='';
		document.getElementById('mptcDiv').innerHTML='';
	//var str = '<font color="#5CB275"><B>No Data Available For This Selection</B></font>';
	//divEle.innerHTML = str;
	
	}

	function showAndHide(id)
	{
	
	//var divInteractive_Chart_0=document.getElementById('divInteractive_Chart_0');
	//var divInteractive_Chart_1=document.getElementById('divInteractive_Chart_1');
	if(id == 'divInteractive_Chart_0'){
	if(document.getElementById("divInteractive_Chart_1").style.display == 'block')
	document.getElementById("divInteractive_Chart_1").style.display = 'none';
 
	}
	if(id == 'divInteractive_Chart_1'){
	if(document.getElementById("divInteractive_Chart_0").style.display == 'block')
	document.getElementById("divInteractive_Chart_0").style.display = 'none';
 
	}

	}

	function showMunicipalInfo(myResults){


	var HeadElmt = document.getElementById('municipalityData_main');
	var str ='';	
	if(myResults.muncipalityVO == null){
		var showDiv = document.getElementById('muncipalDiv');
		str +='No Data available For MUNCIPALITY';
		//showDiv.style.display = "str";
	}
	
	buildCorpOrMunicipTable(HeadElmt, myResults, "MUNCIPALITY","showMoreMuncipalResultsDiv");
	}

	function buildCorpOrMunicipTable(divId, myResults, elecType,showMoreDiv){

	var moreElmt = document.getElementById(showMoreDiv);
	
	var appendingStr = '';
	for(var i in myResults.muncipalityVO){
		appendingStr += '<a href="javascript:{}" onclick="redirectMuncipalityCandidateLink(\''+elecType+'\','+ myResults.muncipalityVO[i].electionTypeId+','+ myResults.muncipalityVO[i].muncipalityId+','+myResults.muncipalityVO[i].latestMuncipalElectionYear+',\''+myResults.muncipalityVO[i].muncipalityName+'\')" style="text-decoration:none;" class="candidateDetailsStyle">show Results</a>';
	}	
	moreElmt.innerHTML = appendingStr;
	
	var str = '';
	var selectId=document.getElementById('MuncipalElectionSelectDiv');
	if(myResults.muncipalityVO==null)
	{
		document.getElementById('muncipalityDiv').innerHTML='';
		//str +='<font color="#5CB275" size:10px>No Data available .......</font>';
		document.getElementById('MuncipalElectionSelectDiv').innerHTML='';
		
	}
	for(var i in myResults.muncipalityVO){
		str += '<div style="display:block;width:auto;position:relative;clear:both;margin-top:15px;color:#5CB275;">';
		str += '<a  style="text-decoration:none;color:#5CB275;" href=\"localBodyElectionAction.action?stateId='+myResults.muncipalityVO[i].stateId+'&localBodyElectionTypeId='+myResults.muncipalityVO[i].electionTypeId+'&localBodyId='+myResults.muncipalityVO[i].muncipalityId+'\">Detailed View of '+myResults.muncipalityVO[i].muncipalityName+' Election Results '+elecType+' In '+myResults.muncipalityVO[i].latestMuncipalElectionYear+'</a></div>';
		//str += '<table><tr><td>';
		str += '<div style="display:inline-block;width:auto;position:relative;float:left;margin:10px;" id=\"'+elecType+'TableDiv_'+i+'\"></div>';
		//str+='</td><td>'
		str += '<div  style="display:inline-block;width:auto;position:relative;float:left;margin:10px;"><img src=\"charts/'+myResults.muncipalityVO[i].chartName+'\"></div>';
		//str += '</td></tr></table>';
	}
	divId.innerHTML = str;
	buildDataTable(elecType);
	}



	function buildElectionResults()
	{
	
	var HeadElmt = document.getElementById('constituencyPageElectionInfoDiv_Head');
	var chartResultDiv = document.getElementById("constituencyPageElectionImgDiv");
	var chartName = "${chartName}";
	var chart = '';
	var enlargedChartName = "${enlargedChartName}";
	var details = document.getElementById("constituencyPageElectionEnlargedImgDiv");
	var detailsDIV = '';

	chart+='<img src="charts/'+chartName+'" style="width:550px"/>';
	chartResultDiv.innerHTML = chart;
	
	detailsDIV += '<table><tr>';
	detailsDIV += '<td><div><a href="javascript:{}" style="background:none repeat scroll 0 0 #335291;color:#FFFFFF;font-size:13px;margin-left:432px;padding:5px;width:113px;" onclick="showDetailedChart(\''+enlargedChartName+'\')" value="Detailed Chart">Detailed Chart</a></div></td>';
	
	details.innerHTML = detailsDIV;
	document.getElementById('constituencyPageElectionInfoDiv_Body').style.display='none';
	
}

	function detailedElectionResult(){
		
		var BodyElmt = document.getElementById('detailedElectionInfoDiv_Body');
		
		var minusPlusDivElmt = document.getElementById("minusPlusDiv");
		var elecStr = '';
		var str = '';
			str+='<a  onclick="hideDetailedElectionResultDiv(\'detailedElectionInfoDiv_Body\')"> <img src="images/minus.png" alt="" class="fleft" style="padding:3px 0px 0px 5px;"/></span></a>';
		minusPlusDivElmt.innerHTML = str;

		for(var i in constituencyPageMainObj.constituencyElectionInfo)
		{
			var data = constituencyPageMainObj.constituencyElectionInfo[i];
			var info = constituencyPageMainObj.constituencyInfo;
			elecStr+='<div id="constituencyElectionInfo_'+i+'" class="electionInformationClass" onclick="showDetailedElectionResult(this.id)">';
			elecStr+='<span id="pointerImg"> <img height="10" width="10" src="'+constituencyPageMainObj.contextPath+'/images/icons/arrow.png"/></span>';
			elecStr+='<span id=""> '+info.constituencyType+' Election Results in '+data.year+' - '+data.candidateName+' Won with '+data.votesPercentage+' votes %</span>';		
			elecStr+='</div>';
		}
		
		if(BodyElmt)
			
			BodyElmt.innerHTML=elecStr;
		document.getElementById('detailedElectionInfoDiv_Body').style.display ='block';
		
		}

		function hideDetailedElectionResultDiv(divElmt)
		{
		document.getElementById(divElmt).style.display ='none';
		var minusPlusDivElmt = document.getElementById("minusPlusDiv");
		var str = '';

		str+='<a  onclick="detailedElectionResult()"> <img src="images/plus.png" alt="" class="fleft" style="padding:3px 0px 0px 5px;"/></span></a>';

		minusPlusDivElmt.innerHTML = str;
		}
	 function buildElectionYearsWithAssets(myResults)
		{
		
		var divElmt = document.getElementById("electionYearsWithAssets_Div");
		var selectedVal = '';

        var electionYearSelect = '';

        electionYearSelect += '<table>';
		electionYearSelect += '<th>Select Election Year to view Assets & Liabilities :</th>';
		electionYearSelect += '<th style="text-align:left;">';
		electionYearSelect += '<select id="electionYearSelectForAssets" class = "selectWidth" onchange = "getcandidateAssetsAndLiabilities(this.options[this.selectedIndex].value)">';
		for(var i in myResults)
		{			
			electionYearSelect += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
			if(i == 0)
				selectedVal = myResults[i].id;
		}
		electionYearSelect += '</select>';
		electionYearSelect += '<td><span id="electionYearSelectForAssets_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></span></td>';
		electionYearSelect += '</th>';

		electionYearSelect += '</table>';
		electionYearSelect += '<span class="hideDetailsBtnClass"><a style="cursor:pointer;" onclick="hideCandidateAffidavit(\'electionYearsPanel_Main_Div\')" >Hide Details</a></span>';

		
	    divElmt.innerHTML = electionYearSelect; 
		
		getcandidateAssetsAndLiabilities(selectedVal);
		document.getElementById("electionYearsPanel_Main_Div").style.display = 'block';
		document.getElementById("electionYearsPanel_Main_Div").style.clear = 'both';

	}

	function hideCandidateAffidavit(divId)
	{
		
		document.getElementById("electionYearsPanel_Main_Div").style.display = 'none';
	}

	function getAssetsElectionYearsInfo(constiId)
	{
        var jsObj=
		{
				constituencyId:constiId,
				task:"getConstituencyElectionYears"						
		};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/constituencyElectionYearsWithAssets.action?"+rparam;
		callAjax(jsObj,url);
	}


	function getcandidateAssetsAndLiabilities(constiELecId)
	{	
		showBusyImgWithId("electionYearSelectForAssets");

		var jsObj=
		{
				constiElecId:constiELecId,
				task:"getCandidateAssetsAndLiabilitiesInfo"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/candidateAssetsAndLiabilitiesAction.action?"+rparam;
		callAjax(jsObj,url);
	}

	function hideBusyImgWithId(elmtId)
	{
	
	var spanElmt = document.getElementById(elmtId+"_ImgSpan");
	if(spanElmt)
		spanElmt.style.display = "none";
	}

	function showMandalsAfterDelimitationDiv()
	{
		document.getElementById('divInteractive_Chart_0').style.display = 'block';
		document.getElementById('divChild_Body_0').style.display = 'block';
		document.getElementById('divInteractive_Chart_1').style.display = 'none';
		document.getElementById('divChild_Body_1').style.display = 'none';
		
		
	}

	function showMandalsBeforeDelimitationDiv()
	{
		document.getElementById('divInteractive_Chart_0').style.display = 'none';
		document.getElementById('divChild_Body_0').style.display = 'none';
		document.getElementById('divInteractive_Chart_1').style.display = 'block';
		document.getElementById('divChild_Body_1').style.display = 'block';
		
	}


		function showGreterElectionDetails(){
		document.getElementById('greaterDiv').style.display='block';
		document.getElementById('corporationDiv').style.display='none';
		document.getElementById('zptcDivBody').style.display='none';
		document.getElementById('mptcDivBody').style.display='none';
		document.getElementById('muncipalDivBody').style.display='none';
		
		
		}
		function showCorporationDetails(){
		document.getElementById('corporationDiv').style.display='block';
		document.getElementById('zptcDivBody').style.display='none';
		document.getElementById('mptcDivBody').style.display='none';
		document.getElementById('muncipalDivBody').style.display='none';
		document.getElementById('greaterDiv').style.display='none';
		
		
		}
		function showZptcPartyDetails()
		{
		
		document.getElementById('zptcDivBody').style.display='block';
		document.getElementById('mptcDivBody').style.display='none';
		document.getElementById('muncipalDivBody').style.display='none';
		document.getElementById('corporationDiv').style.display='none';
		document.getElementById('greaterDiv').style.display='none';
		
		
		}
		function showMptcPartyDetails()
		{
		document.getElementById('zptcDivBody').style.display='none';
		document.getElementById('mptcDivBody').style.display='block';
		document.getElementById('muncipalDivBody').style.display='none';
		document.getElementById('corporationDiv').style.display='none';
		document.getElementById('greaterDiv').style.display='none';
		
		}
		function showMunicipalityResults(){
		
		document.getElementById('muncipalDivBody').style.display='block';
		document.getElementById('zptcDivBody').style.display='none';
		document.getElementById('mptcDivBody').style.display='none';
		document.getElementById('corporationDiv').style.display='none';
		document.getElementById('greaterDiv').style.display='none';
			
		}
		function redirectMptcCandidateLink(){
			 
			 var browser2 = window.open("constituencyPageCandidateDetailsAjaxAction.action?constId="+${constituencyId}+"&eleType="+mptcElectionType+"&eleYear="+mptcElectionYear+"&constTYPE="+constituencyTYPE,"browser2","scrollbars=yes,height=630,width=1020,left=200,top=200");
			 browser2.focus();
		}



		function redirectZptcCandidateLink(){	

			
			 
			 var browser1 = window.open("constituencyPageCandidateDetailsAjaxAction.action?constId="+${constituencyId}+"&eleType="+zptcElectionType+"&eleYear="+zptcElectionYear+"&constTYPE="+constituencyTYPE,"browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
			 browser1.focus();
		}
		


		function redirectMuncipalityCandidateLink(muncipalityElectionType,muncipalityElectionId,muncipalityId,latestMuncipalElectionYear,name){	
	
		var browser3 = window.open("muncipalElectionReportAction.action?muncipalityId="+muncipalityId+"&muncipalityElectionType="+muncipalityElectionType+"&name="+name+"&muncipalityElectionId="+muncipalityElectionId+"&electionYear="+latestMuncipalElectionYear,"browser3","scrollbars=yes,height=670,width=1170,left=200,top=200");
		browser3.focus();
	}



	function buildDataTable(elecType){

	for(var i in myResults.muncipalityVO){
		var resultsDataSource = new YAHOO.util.DataSource(myResults.muncipalityVO[i].muncipalityVO);
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "partyName"
			}, {
				key : "participatedSeats"
			}, {
				key : "seatsWonByParty"
			}, {
				key : "percentageOfVotesWonByParty"
			}]
		};

		var resultsColumnDefs = [ {
			key : "partyName",
			label : "Party",
			sortable : true
		}, {
			key : "participatedSeats",
			label : "Seats Contested",
			sortable : true
		}, {
			key : "seatsWonByParty",
			label : "Won",
			sortable : true
		}, {
			key : "percentageOfVotesWonByParty",
			label : "Votes %",
			sortable : true
		} ];		
		myDataTableForMptcParty = new YAHOO.widget.DataTable(elecType+"TableDiv_"+i,resultsColumnDefs, resultsDataSource,null);
	}
	
	}


	function showNextPreviousCandidateVotingTrendz(index,type)
	{
	if(type == 'Previous')
		candidateIndex-=1;
	else if(type == 'Next')
		candidateIndex+=1;

	if(candidateIndex!=1)
		prevButtonElmt.disabled = false;
	else
		prevButtonElmt.disabled = true;

	if(candidateListSize>1 && candidateIndex!=candidateListSize)
		nextButtonElmt.disabled = false;
	else
		nextButtonElmt.disabled = true;

	for(var i =0;i<constituencyPageMainObj.electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO.length;i++)
	{		
		var data = constituencyPageMainObj.electionTrendzReportVO.electionTrendzOverviewVO.partyElectionTrendzVO[i];
		if(data.rank == candidateIndex)
		{
			var jsObj=	{
							partyId:data.partyId,
							partyName:data.partyName,
							partyLogo:data.partyLogo,
							partyFlag:data.partyFlag,
							candidateId:data.candidateId,
							candidateName:data.candidateName,							
							validVotes:data.validVotes,
							totalVotes:data.totalVotes,
							maleVotes:data.maleVotes,
							femaleVotes:data.femaleVotes,
							maleAndFemaleVotes:data.maleAndFemaleVotes,
							totalVotesPercent:data.totalVotesPercent,
							maleVotesPercent:data.maleVotesPercent,
							femaleVotesPercent:data.femaleVotesPercent,
							maleAndFemaleVotesPercent:data.maleAndFemaleVotesPercent,
							rank:data.rank,
							overallMaleVotesPercent : data.overallMaleVotesPercent,
							overallFemaleVotesPercent : data.overallFemaleVotesPercent,
							overallMaleOrFemaleVotesPercent : data.overallMaleOrFemaleVotesPercent,						
							status:data.status,
							maleVotesPercentInConstiVotes : data.maleVotesPercentInConstiVotes,
							femaleVotesPercentInConstiVotes :data.femaleVotesPercentInConstiVotes,
							maleOrFemaleVotesPercentInConstiVotes : data.maleOrFemaleVotesPercentInConstiVotes, 
							task:'getNextPrevCandidateTrendz'
						}

			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/getNextPrevCandidateVotingTrendz.action?"+rparam;
			
			callAjax(jsObj, url);			
			return;
		}
	}
}

	function showDetailedElectionResult(id)
	{

	var index = id.substring((id.indexOf('_')+1),id.length);
	
	var data = constituencyPageMainObj.constituencyElectionInfo[index];
	var info = constituencyPageMainObj.constituencyInfo;
	var constiId = ${constituencyId}; 
	var elecType = constituencyPageMainObj.constituencyInfo.constituencyType; 
	var elecYear = data.year; 

		var browser1 = window.open("constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
    browser1.focus();	

	/*var str='';
	str+='<fieldset id="constituencyInfoFieldSet">';
	str+='<legend> Constituency Info </legend>';
	str+='<div id="coinstituencyInfoDiv">';
	str+='<table id="constituencyInfoTableClass" class="legendTable" width="100%">';
	str+='<tr>';
	str+='<th>Constituency Name</th>';
	str+='<td>'+info.constituencyName+'</td>';
	str+='<th>Constituency Type</th>';
	str+='<td>'+info.constituencyType+'</td>';	
	str+='</tr>';

	str+='<tr>';	
	str+='<th>District</th>';
	str+='<td>'+info.districtName+'</td>';
	str+='<th>State</th>';
	str+='<td>'+info.stateName+'</td>';	
	str+='</tr>';	
	str+='</table>';
	str+='</div>';
	str+='</fieldset>';
	//--------------
	str+='<fieldset id="WinningCandidateFieldSet">';
	str+='<legend> Winning Candidate Info </legend>';
	str+='<div id="WinningCandidateDiv">';
	str+='<table id="WinningCandidateTableClass" class="legendTable" width="100%">';
	str+='<tr>';
	str+='<th>Name</th>';
	str+='<td colspan="3">'+data.candidateName+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Party</th>';
	str+='<td>'+data.partyName+'</td>';	
	str+='<th>Year</th>';
	str+='<td>'+data.year+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Votes Earned</th>';
	str+='<td>'+data.votesEarned+'</td>';	
	str+='<th>Votes Percentage</th>';
	str+='<td>'+data.votesPercentage+'</td>'
	str+='</tr>';
	str+='</table>';
	str+='</div>';	
	str+='</fieldset>';
	//--------
	str+='<fieldset id="oppositionResultsField">';
	str+='<legend> Opposition\'s Results Info </legend>';
	str+='<div id="oppCandResultsDiv">';	
	str+='</div>';
	str+='</fieldset>';

	ElectionResultPanel = new YAHOO.widget.Panel("electionResults_Panel", 
				{
					width:"800px", 
					fixedcenter : false, 
					visible : true,  
					constraintoviewport : true,
					x:200,
					y:400,
					iframe :true,
					modal :true,
					visible:true,						
					draggable:true, 
					close:true
				} ); 
	

	ElectionResultPanel.render();
	ElectionResultPanel.setHeader(' Election Results');
	ElectionResultPanel.setBody(str);
	
	 var myDataSource = new YAHOO.util.DataSource(data.oppositionCandInfo); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	 myDataSource.responseSchema = { 
	            fields: [
							{key:"candidateName"},
							{key:"partyName"},
							{key:"year",parser:"number"},
							{key:"votesEarned",parser:"number"},
							{key:"votesPercentage",parser:"number"}
						] 
	        }; 
	
	 var myColumnDefs = [ 
	            {key:"candidateName",label:'Candidate Name', sortable:true, resizeable:true}, 
	            {key:"partyName", label:'Party Name', sortable:true, resizeable:true}, 
	            {key:"year", label:'Year',sortable:true, resizeable:true}, 
	            {key:"votesEarned",label:'Votes Earned',formatter:YAHOO.widget.DataTable.formatNumber, sortable:true, resizeable:true}, 
	            {key:"votesPercentage",label:'Votes %', sortable:true, resizeable:true} 
	        ]; 
	 
	var myDataTable = new YAHOO.widget.DataTable("oppCandResultsDiv",myColumnDefs, myDataSource); 
	*/
	}




	function buildConstituencyElectionResultsDataTable(value)
	{
	var resultDiv = document.getElementById("resultsDataTableDiv");	
	var str = '';
	str += '<div id="elecResDiv" style="width=900px;overflow-x:auto;margin-top:20px;">';
	str += '<table id = "elecResTable">';
	for(var i in constituencyResults.constituencyOrMandalWiseElectionVO){
		str += '<tr>';
		if(constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName == 'Others *' || 
				!constituencyResults.constituencyOrMandalWiseElectionVO[i].showLink)
			str += '<td>'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</td>';
		else
		if(constituencyResults.electionType == 'Assembly'){
			if(constituencyResults.constituencyOrMandalWiseElectionVO[i].isUrban)
				str += '<td><a  href="localBodyElectionAction.action?stateId=1&localBodyElectionTypeId=5&localBodyId='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
			else
				str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'&MANDAL_NAME='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		}
		else
			str += '<td><a href="constituencyPageAction.action?constituencyId='+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationId+'">'+constituencyResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		for(var j in constituencyResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs){
			if(value == 'number')
				str += '<td>'+constituencyResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesEarned+'</td>';
			else
				str += '<td>'+constituencyResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesPercentage+'</td>';
		}
		str += '</tr>';
	}		
	str += '</table>';
	str += '</div>';
	resultDiv.innerHTML = str;

	var resultDiv = document.getElementById("missingDataInfoDiv");	
	var missingVotesDiv = '';
	missingVotesDiv+='<b>';
	missingVotesDiv += '<font color="Red"><b>*</b></font>';		
	missingVotesDiv += ' Others Include Postal Ballet Votes';
	missingVotesDiv+='</b>';
	resultDiv.innerHTML = missingVotesDiv;

	 var myColumnDefs = new Array();
	 var myFields = new Array();
	 
	 if(constituencyResults.electionType == 'Assembly'){
		 var villageHead = {
		 			key:"Mandal",
		 			lable: "Mandal",
		 			sortable:true
			   }

		 var villageValue = {key:"Mandal"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }else{
		 var villageHead = {
		 			key:"Assembly Constituency",
		 			lable: "Assembly Constituency",
		 			sortable:true
			   }

		 var villageValue = {key:"Assembly Constituency"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }
	 

	 for(var i in constituencyResults.candidateNamePartyAndStatus){
		var obj1 = {
					key:constituencyResults.candidateNamePartyAndStatus[i].party +'['+constituencyResults.candidateNamePartyAndStatus[i].rank+']',
					label:constituencyResults.candidateNamePartyAndStatus[i].party +'['+constituencyResults.candidateNamePartyAndStatus[i].rank+']',
					sortable:true
				}
		var obj2 = {
					key:constituencyResults.candidateNamePartyAndStatus[i].party +'['+constituencyResults.candidateNamePartyAndStatus[i].rank+']',
					parser:"number"
				}
		myColumnDefs.push(obj1);
		myFields.push(obj2);
	 }

	 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("elecResTable")); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
	 myDataSource.responseSchema = { 
							            fields:myFields    
							        };

	 if(constituencyResults.electionType == 'Parliament'){
		var extraInfoDiv = document.getElementById("missingDataInfoDiv");
		var str = '';
		str += '<br>';
		str += '<table>';
		str += '<tr>';
		str += '<td>';
		str += '<font color="Red"><b>*</b></font>';				
		str += '</td>';
		str += '<td><b>';
			if(constituencyResults.isDataInsufficient){
				str += ' Others Include Postal Ballet Votes, ';
				for(var i in constituencyResults.missingConstituencies)
					str += '<a href="constituencyPageAction.action?constituencyId='+constituencyResults.missingConstituencies[i].id+'">'+constituencyResults.missingConstituencies[i].name + '</a> Assembly ,';
				str = str.substring(0,str.length-1);
				str += ' Wise ${constituencyDetails.constituencyName} ${constituencyDetails.constituencyType} Election Results';
			}else{
				str += ' Others Include Postal Ballet Votes';
			}
		str += '</b></td>';
		str += '</tr>';
		str += '</table>';		
		extraInfoDiv.innerHTML = str;		
	 }
	 var villageDataTable = new YAHOO.widget.DataTable("elecResDiv",myColumnDefs, myDataSource);
	}

	function getConstituencyElections(){

	var jsObj = {
			constituencyId:${constituencyId},
			task:"getConstituencyElections"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getElectionYearsAction.action?"+rparam;
	callAjax(jsObj, url);
	}


	function buildCensusSelect(myResult)
	{
	var censelectEle = document.getElementById("censusSelectDiv");
	
	var cenvar = '';
	cenvar += '<table>';
	cenvar += '<tr>';
	if('${constituencyDetails.constituencyType}' == 'Assembly')
	{
		cenvar += '<th>To Compare Mandal Wise Election Results With Census, Select Any Census Parameter:';
	}

	if('${constituencyDetails.constituencyType}' == 'Parliament')
	{
		cenvar += '<th>To Compare Assembly Wise Election Results With Census, Select Any Census Parameter:';
	}
	cenvar += '&nbsp;&nbsp;';
	cenvar += '<select id="censusSelect" onchange = "getCensusDetailsForAConstituency(\'${constituencyId}\',this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)">';
	cenvar += '<option value=\'0\'>Select Census</option>';
	cenvar += '<option value=\'1\'>SC Population</option>';
	cenvar += '<option value=\'2\'>ST Population</option>';
	cenvar += '<option value=\'3\'>Literates</option>';
	cenvar += '<option value=\'4\'>illiterates</option>';
	cenvar += '<option value=\'5\'>Working People</option>';
	cenvar += '<option value=\'6\'>Non Working People</option>';
	cenvar += '</select>';
	cenvar += '</th>'
	cenvar += '<td><div id="censusAjaxImgDiv" align="center" style="display:none;"><img width="5" height="5" src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></div></td>';
	cenvar += '</tr>';
	cenvar += '<tr>';
	cenvar += '<th>(In the graph Census Details are in Percentages, these Percentages Calculated Over Total Population)';
	cenvar += '</tr>';
	cenvar += '</table>';


	censelectEle.innerHTML = cenvar;
	}


	function buiidElecResultRadioSelect()
	{
	var ElecResultselectEle = document.getElementById("labelRadioDiv");
	
	var selectEle = '';

	selectEle += '<table><tr>';
	selectEle += '<td id="labelRadio"><b>Select The Format You Want :</b></td>';
	selectEle += '<td><input type="radio" name="dispaly" value="number" checked="true" onclick="buildConstituencyElectionResultsDataTable(this.value)">By Votes </td>';
	selectEle += '<td><input type="radio" name="dispaly" value="percentage" onclick="buildConstituencyElectionResultsDataTable(this.value)"/>By Percentage </td>';
	selectEle += '</tr></table>';

	ElecResultselectEle.innerHTML = selectEle;
	}
	function buildElectionsSelectBox(myResults){
	var selectDiv = document.getElementById("electionIdsSelectDiv");
	var electionYearSelect = '';	
	var selectDivEl = document.getElementById("MandalwiseVotingTrendz");
	if(myResults.length == 0){
		if(selectDivEl){
			selectDivEl.style.display = 'none';
		}
			
		//electionYearSelect +='<b>Sorry, Constituency/Mandal Data Not Available For This Constituency</b>';
		//var resultDiv = document.getElementById("mandalOrConstiElecResultDiv");
		//resultDiv.style.display = "none";
		//selectDiv.innerHTML = electionYearSelect; 
		return;
	}

	var headingDiv = document.getElementById("MandalVotingTrendz_head");
	var str='';
	if(headingDiv == null)
		return;
	if('${constituencyDetails.constituencyType}' == 'Assembly'){
		//headingDiv.innerHTML = ' Mandal Wise Voting Trendz ';
	str +='<h1 class="gre-title">';
	str +='<span>Mandal Wise Voting Trendz</span>';
		str +='</h1>';
	headingDiv.innerHTML=str;
	}
	if('${constituencyDetails.constituencyType}' == 'Parliament')
		{
		//headingDiv.innerHTML = ' Assembly Wise Voting Trendz '; 
	str +='<h1 class="gre-title">';
	str +='<span> Assembly Wise Voting Trendz</span>';
	str +='</h1>';
		headingDiv.innerHTML = str;
	str+='<br>';
	str+='<br>';
	}
	electionYearSelect += '<table>';
	electionYearSelect += '<th>Select Election Year :</th>';
	electionYearSelect += '<th>';
	electionYearSelect += '<select id="electionYearSelect" class = "selectWidth" onchange = "getConstituencyResults(this.options[this.selectedIndex].text)">';
	for(var i in myResults)
	{			
		electionYearSelect += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
	}
	electionYearSelect += '</select>';
	electionYearSelect += '</th>';
	electionYearSelect += '<td><div id="AjaxImgDiv" align="center" style="display:none;"><img width="5" height="5" src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></div></td>';
	electionYearSelect += '<td><div id="parliamentResultsButtonDiv"></td>';
	electionYearSelect += '<td><div id="detailsDiv"></td>';
	electionYearSelect += '</table>';
	selectDiv.innerHTML = electionYearSelect; 
	getConstituencyResults(myResults[0].name);
	}


	function getConstituencyResults(elecYear){
	var imgElmt = document.getElementById('AjaxImgDiv');
	var parliamentDiv = document.getElementById('parliamentElectionResultsDiv');
	parliamentDiv.style.display = "none";

	if(imgElmt.style.display == "none")
	{
          imgElmt.style.display = "block";
	}
	var jsObj = {
			constituencyId:${constituencyId},
			electionYear:elecYear,
			chartHeight: 350,
			chartWidth: 500,
			others:true,
			task:"getConstituencyResultsBySubLocations"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/assemblyWiseParliamentResultAction.action?"+rparam;
	callAjax(jsObj, url);
	}



	function buildConstituencyElectionResultsDataTableWithCensus(myResults,value)
	{ 
	var constType = '${constituencyDetails.constituencyType}';
	var resultDiv = document.getElementById("resultsDataTableDiv");	
	var selectedIndex = myResults.censusVO[0].censusSelectedIndex;
	
	var str = '';
	str += '<div id="elecResDiv" style="width=250px;overflow-x:auto;margin-top:20px;">';
	str += '<table id = "elecResTable">';

	for(var i in myResults.constituencyOrMandalWiseElectionVO)
	{
		str += '<tr>';
			
		if(constType == 'Assembly')
		{	
			str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+myResults.constituencyOrMandalWiseElectionVO[i].locationId+'&MANDAL_NAME='+myResults.constituencyOrMandalWiseElectionVO[i].locationName+'">'+myResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		}
		else if(constType == 'Parliament')
			str += '<td><a href="constituencyPageAction.action?constituencyId='+myResults.constituencyOrMandalWiseElectionVO[i].locationId+'">'+myResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		
		if(value == 'number')
		{
			if(selectedIndex == 1)
				str += '<td>'+myResults.censusVO[i].populationSC+'</td>';
			else if(selectedIndex == 2)
				str += '<td>'+myResults.censusVO[i].populationST+'</td>';
			else if(selectedIndex == 3)
				str += '<td>'+myResults.censusVO[i].literates+'</td>';
			else if(selectedIndex == 4)
				str += '<td>'+myResults.censusVO[i].illiterates+'</td>';
			else if(selectedIndex == 5)
				str += '<td>'+myResults.censusVO[i].workingPeople+'</td>';
			else if(selectedIndex == 6)
				str += '<td>'+myResults.censusVO[i].nonWorkingPeople+'</td>';

		}
		else
		{
			if(selectedIndex == 1)
				str += '<td>'+myResults.censusVO[i].populationSCPercentage+'</td>';
			else if(selectedIndex == 2)
				str += '<td>'+myResults.censusVO[i].populationSTPercentage+'</td>';
			else if(selectedIndex == 3)
				str += '<td>'+myResults.censusVO[i].literatesPercentage+'</td>';
			else if(selectedIndex == 4)
				str += '<td>'+myResults.censusVO[i].illiteratesPercentage+'</td>';
			else if(selectedIndex == 5)
				str += '<td>'+myResults.censusVO[i].workingPeoplePercentage+'</td>';
			else if(selectedIndex == 6)
				str += '<td>'+myResults.censusVO[i].nonWorkingPeoplePercentage+'</td>';
		}

		for(var j in myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs)
		{
			if(value == 'number')
				str += '<td>'+myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesEarned+'</td>';
			else
				str += '<td>'+myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesPercentage+'</td>';
		}
			str += '</tr>';
	}		
	str += '</table>';
	str += '</div>';
	resultDiv.innerHTML = str;

	 var myColumnDefs = new Array();
	 var myFields = new Array();
	 
	 if(constType == 'Assembly'){
		 var villageHead = {
		 			key:"Mandal",
		 			lable: "Mandal",
		 			sortable:true
			   }

		 var villageValue = {key:"Mandal"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }else{
		 var villageHead = {
		 			key:"Assembly Constituency",
		 			lable: "Assembly Constituency",
		 			sortable:true
			   }

		 var villageValue = {key:"Assembly Constituency"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }
	 
	 var cenObj1 = {
			key:myResults.censusVO[0].censusFields[0],
			label:myResults.censusVO[0].censusFields[0],
			sortable:true
	    }
	
	 var cenObj2 = {
			 key:myResults.censusVO[0].censusFields[0],
			 parser:"number"
		}
		myColumnDefs.push(cenObj1);
		myFields.push(cenObj2);
	 for(var i in myResults.candidateNamePartyAndStatus){
		var obj1 = {
					key:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					label:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					sortable:true
				}
		var obj2 = {
					key:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					parser:"number"
				}
		myColumnDefs.push(obj1);
		myFields.push(obj2);
	 }

	 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("elecResTable")); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
	 myDataSource.responseSchema = { 
							            fields:myFields    
							       };

	 var villageDataTable = new YAHOO.widget.DataTable("elecResDiv",myColumnDefs, myDataSource);
	} 

	function buiidCensusRadioSelect()
	{
	var cenRadioselectEle = document.getElementById("labelRadioDiv");
	
	var cenRadiovar = '';

	cenRadiovar += '<table><tr>';
	cenRadiovar += '<td id="labelRadio"><b>Select The Format You Want :</b></td>';
	cenRadiovar += '<td><input type="radio" name="dispaly" value="number" checked="true" onclick="buildConstituencyElectionResultsDataTableWithCensus(censusResult,this.value)">By Votes </td>';
	cenRadiovar += '<td><input type="radio" name="dispaly" value="percentage" onclick="buildConstituencyElectionResultsDataTableWithCensus(censusResult,this.value)"/>By Percentage </td>';
	cenRadiovar += '</tr></table>';

	cenRadioselectEle.innerHTML = cenRadiovar;
	}


	function getCensusDetailsForAConstituency(constituencyId,index,text)
	{
	if(index == 0)
			return;

	var imgElmt = document.getElementById('censusAjaxImgDiv');
	
	if(imgElmt.style.display == "none")
	{
          imgElmt.style.display = "block";
	}
	
	var electionSelectEle = document.getElementById("electionYearSelect");
	var electionYear      = electionSelectEle.options[electionSelectEle.selectedIndex].text;
	var constituencyType  = '${constituencyDetails.constituencyType}';

		var jsObj = {
			constituencyId  : constituencyId,
			censusYear      : 2001,
			delimitationYear: 2009,
			seletedIndex    : index,
			seletedText     : text,
			electionYear    : electionYear,
			constituencyType: constituencyType,
			others          : false,
			task:"getCensusDetailsForAConstituency"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getCensusDetailsForAConstituency.action?"+rparam;
	callAjax(jsObj, url);
	}


	function buildCensusChartForAConstituency(myResults)
	{
	if(myResults.censusVO == null || myResults.censusVO.length == 0)
	{
		showCensusError();
		hideCensusAjaxImage();
		return;
	}

	else if(myResults.censusVO.length != myResults.constituencyOrMandalWiseElectionVO.length)
	{
		showCensusError();
		hideCensusAjaxImage();
		return;
	}
	
	else if(myResults.censusVO.length > 0)
	{
    var chartColumns = myResults.candidateNamePartyAndStatus;
	var chartRows    = myResults.constituencyOrMandalWiseElectionVO;
	var census       = myResults.censusVO;
    var partiesArray = new Array();
	var selectedIndex= census[0].censusSelectedIndex;
	
	 var data = new google.visualization.DataTable();
	 data.addColumn('string', 'Party');

	 var colData = census[0].censusFields[0];
	 data.addColumn('number',colData);
	 partiesArray.push(colData);

	 //for chart columns
	 for(var i in chartColumns)
	 {
	   var colData = chartColumns[i].party +'['+chartColumns[i].rank+']';
	   data.addColumn('number',colData);

	   partiesArray.push(chartColumns[i].party);
	 }

      //for chart rows
	  for(var j in chartRows)
	  {
		  var array = new Array();
		  array.push(chartRows[j].locationName);
		
		if(selectedIndex == 1)
		var censusPercentage = census[j].populationSCPercent;
		else if(selectedIndex == 2)
		var censusPercentage = census[j].populationSTPercent;
		else if(selectedIndex == 3)
		var censusPercentage = census[j].literatesPercent;
		else if(selectedIndex == 4)
		var censusPercentage = census[j].illiteratesPercent;
		else if(selectedIndex == 5)
		var censusPercentage = census[j].workingPeoplePercent;
		else if(selectedIndex == 6)
		var censusPercentage = census[j].nonWorkingPeoplePercent;
	  
	     array.push(censusPercentage);

		  for(var k in chartRows[j].partyElectionResultVOs)
		  {
			  var percentage = chartRows[j].partyElectionResultVOs[k].votesPercent;
              array.push(percentage);
		  }

		  data.addRow(array);
	  }
    var chartResultDiv = document.getElementById("electionResultsInConstituencyDiv");

	if('${constituencyDetails.constituencyType}' == 'Assembly')
	{
		ctitle = "Mandal Wise Election Results V/S Census Chart For '${constituencyDetails.constituencyName}'";
	}
	if('${constituencyDetails.constituencyType}' == 'Parliament')
	{
		ctitle = "Assembly Constituency Wise Election Results V/S Census Chart For '${constituencyDetails.constituencyName}'";
	}
	
	var staticColors = setStaticColorsForInteractiveChartsForCensusAndPartiesArray(partiesArray);
    
	 if(chartRows.length == 1)
	{
		if(staticColors != null && staticColors.length > 0)
		{
		  new google.visualization.ColumnChart(chartResultDiv).
		      draw(data, {width:500, height: 450,title:ctitle,colors:staticColors,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
		else
		{
		 new google.visualization.ColumnChart(chartResultDiv).
		  draw(data, {width: 600, height: 450,title:ctitle,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
	}
	 if(chartRows.length > 1)
	{
		 if(staticColors != null && staticColors.length > 0)
		{
		 new google.visualization.LineChart(chartResultDiv).
		      draw(data, {curveType: "function",width: 600, height: 450,title:ctitle,colors:staticColors,pointSize: 6,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
		else
		{
		   new google.visualization.ColumnChart(chartResultDiv).
		  draw(data, {width: 600, height: 450,title:ctitle,pointSize: 6,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
	 }
   hideCensusAjaxImage();
   removeCensusNotAvailableErrorMessage();
  }

	}



	function getInteractiveChart(chartResultDiv,constituencyResults,partiesList,constiType,constiName,electionYear)
	{
	var chartColumns = partiesList;
	var chartRows = constituencyResults;
    var partiesArray = new Array();

	 var data = new google.visualization.DataTable();
	 data.addColumn('string', 'Party');

     //for chart columns
	 for(var i in chartColumns)
	 {
	   var colData = chartColumns[i].party +'['+chartColumns[i].rank+']';
	   data.addColumn('number',colData);

	   partiesArray.push(chartColumns[i].party);
	 }

      //for chart rows
	  for(var j in constituencyResults)
	  {
		  var array = new Array();
		  array.push(constituencyResults[j].locationName);

		  for(var k in chartRows[j].partyElectionResultVOs)
		  {
			  var percentage = chartRows[j].partyElectionResultVOs[k].votesPercent;
              array.push(percentage);
		  }
		 
		  data.addRow(array);
	  }

      var ctitle='';
	  if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Parliament')
	  {
	    ctitle = 'Assembly Constituency Wise Election Results Chart For '+constiName+' '+constiType+' Constituency In '+electionYear;
	  }else
	  {
         ctitle = 'Mandal Wise Election Results Chart For '+constiName+' '+constiType+' Constituency In '+electionYear;
	  }
       
      //static colors for parties
      var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);
	  
	  if(staticColors != null && staticColors.length > 0)
	  {
		 new google.visualization.LineChart(chartResultDiv).
		      draw(data, {curveType: "function",width:600, height: 450,title:ctitle,colors:staticColors,pointSize: 4,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
	  }else
	  {
		 new google.visualization.LineChart(chartResultDiv).
		      draw(data, {curveType: "function",width:600, height: 450,pointSize: 4,title:ctitle,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
	  }
	}



function getVotingTrendzForElectionYears()
{
	var jsObj=	{					
					task:'getVotingTrendzForElectionYears'
				}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getVotingTrendzForElectionYears.action?"+rparam;
	callAjax(jsObj, url);	
}


	function getVotingTrendzForyear()
	{

	var elements = document.getElementsByTagName('input'); 
	var electionType;
	for(var i=0;i<elements.length;i++)
	{
		if(elements[i].type=="radio" && elements[i].name=="electionType" && elements[i].checked==true)
			electionType = elements[i].value;
	}
	if(!electionType)
		return;
	
	var selectElmt = document.getElementById(electionType+"_YearSelect");
	if(!selectElmt)
		return;
	selectElmt.disabled = false;

	var year = selectElmt.options[selectElmt.selectedIndex].text;
	if(year == "Assembly" || year == "Parliament")
		return;

	var value = selectElmt.options[selectElmt.selectedIndex].value;	
	var electionId = value.substring(0,(value.indexOf('_')));
	var electionTypeId = value.substring((value.indexOf('_')+1),value.length);

	

	var jsObj = {
					constituencyId:constituencyPageMainObj.electionTrendzReportVO.constituencyId,
					electionId:electionId,
					electionTypeId:electionTypeId,
					electionYear:year,
					task:'getVotingTrendzForElectionYears'
				};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getVotingTrendzForElectionYears.action?"+rparam;
	callAjax(jsObj, url);	
	
	}


	function showCorporationInfo(myResults){
	var str='';
	var HeadElmt = document.getElementById('coroporationData_main');
	
	if(myResults.muncipalityVO == null){
		var showDiv = document.getElementById('corporationDiv');
		var corporationHeadingDIVEle = document.getElementById('corporationHeadingDIV');
		//str +='<font style="clear:right;margin-left:71px;text-decoration: none;color:#5CB275;cursor:pointer;">No Data available .......</font>';
		document.getElementById('corporationTabDiv').innerHTML='';
		showDiv.innerHTML=str;
		showDiv.style.display = "none";
	}

	buildCorpOrMunicipTable(HeadElmt, myResults, "Corporation","showMoreCorporationResultsDiv");
	}


	function showGreaterInfo(myResults){
	var moreElmt = document.getElementById('showMoreGreaterResultsDiv');	
	var elecType= "Greater Municipal Corp";
	var appendingStr = '';
	for(var i in myResults.localElectionsInfo){
		appendingStr += '<a href="javascript:{}" onclick="redirectMuncipalityCandidateLink(\''+elecType+'\','+ myResults.localElectionsInfo[i].electionTypeId+','+ myResults.localElectionsInfo[i].id+','+myResults.localElectionsInfo[i].electionYear+',\''+myResults.localElectionsInfo[i].name+'\')" style="text-decoration:none;" class="candidateDetailsStyle" >Show Results</a>';
	}	
	moreElmt.innerHTML = appendingStr;
	
	var HeadElmt = document.getElementById('GHMCData_main');
	var str='';
	if(myResults.localElectionsInfo == null){
		var showDiv = document.getElementById('greaterDiv');
		//str +='<font style="clear:right;margin-left:71px;text-decoration: none;color:#5CB275;cursor:pointer;">No Data available .......</font>';
		document.getElementById('greterMunicipalDiv').style.display = "none";
		
		showDiv.innerHTML=str;
		showDiv.style.display = "none";
	}
	var str = '';
	for(var i in myResults.localElectionsInfo){
		str += '<div style="color:#5CB275 !important;text-decoration: none;display:block;clear:left;padding:10px;">';
		str += '<a style="color:#5CB275;" href=\"localBodyElectionAction.action?stateId='+myResults.localElectionsInfo[i].stateId+'&localBodyElectionTypeId='+myResults.localElectionsInfo[i].electionTypeId+'&localBodyId='+myResults.localElectionsInfo[i].id+'\">Detailed view of '+myResults.localElectionsInfo[i].name+' Election Results In '+myResults.localElectionsInfo[i].electionYear+'</a></div>';
		str += '<div><img src=\"charts\\'+myResults.localElectionsInfo[i].chartName+'\"></div>';
		str += '<div id=\"greaterTableDiv_'+i+'\"></div>';
	}
	HeadElmt.innerHTML = str;
	for(var i in myResults.localElectionsInfo){
		var resultsDataSource = new YAHOO.util.DataSource(myResults.localElectionsInfo[i].wardwiseResultsForParty);
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "constituencyName"
			}, {
				key : "partyName"
			}, {
				key : "candidateName"
			}, {
				key : "votesEarned"
			}, {
				key : "votesPercentage"
			}, {
				key : "rank"
			}, {
				key : "totalVotes"
			}]
		};

		var resultsColumnDefs = [ {
			key : "constituencyName",
			label : "Ward",
			sortable : true
		}, {
			key : "partyName",
			label : "Party",
			sortable : true
		}, {
			key : "candidateName",
			label : "Candidate",
			sortable : true
		}, {
			key : "votesEarned",
			label : "Votes Gained",
			sortable : true
		}, {
			key : "votesPercentage",
			label : "Votes %",
			sortable : true
		}, {
			key : "rank",
			label : "Rank",
			sortable : true
		}, {
			key : "totalVotes",
			label : "Total Voters",
			sortable : true
		}  ];		
		if(myResults.localElectionsInfo[i].wardwiseResultsForParty.length >10)
		{
			var recordsPerPage = {
		    paginator : new YAHOO.widget.Paginator({
		        rowsPerPage: 10 
		    })
			};
		}	
		myDataTableForMptcParty = new YAHOO.widget.DataTable("greaterTableDiv_"+i,resultsColumnDefs, resultsDataSource,recordsPerPage);
	}
	
	}
	
getAllZptcYears();	
getAllMptcYears();
getGreaterResults();
getCoroporationResults();
buildConstituencyInfo();
initializeConstituencyPage();

 </script>
  </body>
</html>