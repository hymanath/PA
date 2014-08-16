<page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<%@taglib prefix="s" uri="/struts-tags" %>
		<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
 <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/dailyVerificationReports.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 


		<style>
		
			body{background:#f0f0f0;}
			.m_top20{margin-top:20px;}
			.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
			.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
			.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.username thead tr:nth-child(2){ background:#eee;}		
			.username td:first-child{ min-width: 200px; }		
			.username th small{ font-size:11px; }				
			.username th{ text-align:center; }
			
			.requiredFont{
				color:red;
				font-size:13px;
			}

			#errorDiv{
				font-weight:bold;
				color:red;
			}
			
			#retunMsg
			{
			 font-weight:bold;
			 color:green;
			}
			.ui-multiselect{
				width:200px !important;
				}
				#constituencyId{
				width:200px !important;
				}
			
			.survey_nav{height:40px; background: #ffea51;
					background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZWE1MSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmE2MDAiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
					background: -moz-linear-gradient(top,  #ffea51 0%, #ffa600 100%);
					background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffea51), color-stop(100%,#ffa600));
					background: -webkit-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -o-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -ms-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: linear-gradient(to bottom,  #ffea51 0%,#ffa600 100%);
					filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffea51', endColorstr='#ffa600',GradientType=0 );
					}
			.survey_nav ul li{line-height:40px;}
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:13px; padding:12px 12px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
			.survey_nav ul li a:hover{background:rgba(255,0,0,0.1);}
			.survey_nav ul li a.selected{color:#fff; background:red;text-shadow:0px 1px #4f4f4f; }
			.requiredFont{
				color:red;
				font-size:13px;
			}
			
			.highlight{
			cursor: pointer;
			}
			#FinalReportWithTPTableId th{ text-align:center;}
		</style>	
	
  </head>
  
  <body>
  <script>
  
  $( document ).ready(function() {
$('#boothId').multiselect({
	  noneSelectedText:"Select Booth(s)"});
	
});
  </script> 
		<div class="container">
		<div class="row">
			<div class="span12 m_top20 survey_nav">
				<ul class="inline unstyled">
					<li><a class="highlight selected" id="stateWiseReportTab" onclick="showHideReportTabs(this.id);"> State Wise Report </a></li>
					<li><a class="highlight" id="dataCollectorTab" value="1" onclick="showHideReportTabs(this.id);"> Data Collector Report </a></li>
					<!--<li><a class="highlight" id="wmReportTab" value="3" onclick="showHideReportTabs(this.id);">WM Report</a></li>
					<li><a class="highlight" id="verifierReportTab" value="2" onclick="showHideReportTabs(this.id);">Verifier Report</a></li>-->
					<!--<li><a class="highlight" id="thirdPartyReportTab" value="3" onclick="showHideReportTabs(this.id);"> Third Party Report </a></li>-->
					<li><a class="highlight" id="userTrackingReportTab" onclick="showHideReportTabs(this.id);"> User Tracking Report</a></li>
					<li><a class="highlight" id="thirdpPartyReportTab" onclick="showHideReportTabs(this.id);">Third Party Report</a></li>
					<!--<li><a class="highlight" id="comparisonReportTab" onclick="showHideReportTabs(this.id);"> Comparison Report </a></li>-->
					<!--<li><a class="highlight" id="saveBoothPercentagesTab" onclick="showHideReportTabs(this.id);"> Save Booth Percentages </a></li>-->

				</ul>
			</div>
		</div>
  
  
  
  
  <div class="container">
		<!---- Survey monitoring---->		
		<div class="row" id="verifierReportId" style="dispaly:none;">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top30">
							<h4 id="titleId"></h4>
							<div class="row">
						<div id="errorDiv" class="span8 offset1 clearCls"></div>
						</div>
								<div class="row">
								<div class="offset1">
									<div class="row-fluid">
										
										<div class="span3">
											Select Constituency <font class="requiredFont">*</font> : 
											<!--<select id="constituencyId" onChange="getBoothsDetailsByConstituencyId(this.value)"></select>-->
												<s:select theme="simple"  name="constituency" id="constituencyId"  headerKey="0" headerValue="Select Constituency" list="dataAvilableConstituencies" listKey="id" listValue="name" onChange="getBoothsDetailsByConstituencyId(this.value,'boothId'),getCasteCollectedDatesByConstituencyId(this.value);"/>
										</div>
										<div class="span3">
											<!--User Type <font class="requiredFont">*</font>
											<select class="input-block-level" id = "userType"> <option value="0">Select User Type</option></select>-->
											Select Booth <font class="requiredFont">*</font> : 
											<select class="input-block-level" id = "boothId" multiple="true"> <option value="0">Select Booth</option></select></div>
											<div class="span1" style="margin:25px -8px 0 8px;width: 15px;">
								<img id="boothImage" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
							
										</div>
										<div class="span2">
											From Date <font class="requiredFont">*</font>
											<div class="input-append">
											<input type="text" placeholder="From Date..." class="input-block-level date" id="fromDate" readonly>
											</div>
										</div>
										<div class="span2">
											To Date <font class="requiredFont">*</font>
											<div class="input-append">
											 <input type="text" placeholder="To Date..." class="input-block-level date" id="toDate" readonly>
											</div>
										</div>
										<div class="span1" style="margin:25px -8px 0 8px;width: 15px;">
								<img id="dateAjaxImage" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
									</div>	
									<div class="row-fluid">
										
										
									</div>
									</div>
									</div>
						<!--	<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getDayWiseReport()">SUBMIT</button></div>-->
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getDayWiseReportByConstituencyIdAndUserType()">SUBMIT</button></div>
						<div id="retunMsg" class="clearCls"></div>
                             <div id="dayWiseReportDiv1" class="clearCls" style="overflow-x:scroll;"></div>
							<img src='images/Loading-data.gif' class="offset5"  id="mainajaximg" style="width:70px;height:60px;display:none;"/>
					</div>
				</div>
				
				<div class="row-fluid " >
				
					<div class="span12 m_top20 widgetservey" id="reportDiv" style="display:none;" class="clearCls">
					
						<h4 id="heading">Verifier Report For Daily Verification </br><small style="color:#333;padding-bottom:10px;display:inline-block;">  From 26 - June - 2014 to 3 - July - 2014 </small> </h4>
							
						<div class="row-fluid" style=" height:300px;">
						<div id="dayWiseReportDiv"></div>
 						    <div id="boothWiseCountDivId"></div>
							
						</div>
							
					</div>
				</div>
			</div>
		</div>
		<div class="row" id="verifierReportIdForVerifier" style="dispaly:none;">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top30">
							<h4 id="verifiertitleId"></h4>
							<div class="row">
						<div id="errorDiv" class="span8 offset1 clearCls"></div>
						</div>
								<div class="row">
									<div class="offset1">
										<div class="row-fluid">
											
											<div class="span5">
												Select Constituency <font class="requiredFont">*</font>
												<!--<select id="constituencyId" onChange="getBoothsDetailsByConstituencyId(this.value)"></select>
													<s:select theme="simple"  name="constituency" id="constituencyForVeriFier"  headerKey="0" headerValue="Select Constituency" list="dataAvilableConstituencies" listKey="id" listValue="name" onChange="getBoothsDetailsByConstituencyId(this.value,'boothIdForVerifier')"/>-->
													<s:select theme="simple"  name="constituency" id="constituencyForVeriFier"  headerKey="0" headerValue="Select Constituency" list="dataAvilableConstituencies" listKey="id" listValue="name" onChange="getBoothsDetailsInSurveyDetailsInfo(this.value,'boothIdForVerifier')"/>

											</div>
											<div class="span4">
												<!--User Type <font class="requiredFont">*</font>
												<select class="input-block-level" id = "userType"> <option value="0">Select User Type</option></select>-->
												Select Booth  <font class="requiredFont">*</font> : 
												<select class="input-block-level" id = "boothIdForVerifier" > <option value="0">Select Booth</option></select></div>
												<div class="span1" style="margin:25px -8px 0 8px;width: 15px;">
													<img id="boothImageForVerifier" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
												</div>
										</div>	
									</div>
								</div>
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getVerfierDetails(1,'mainajaximgForVerifier','dayWiseReportDivForVerifier')">SUBMIT</button></div>
						<div id="retunMsg" class="clearCls"></div>
						<img src='images/Loading-data.gif' class="offset5"  id="mainajaximgForVerifier" style="width:70px;height:60px;display:none;"/>
                         <div id="dayWiseReportDivForVerifier" class="clearCls"></div>
							
					</div>
				</div>
			</div>
		</div>
			<div class="row" id="wmReportDiv" style="dispaly:none;">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top30">
							<h4 id="wmtitleId"></h4>
							<div class="row">
						<div id="errorDiv" class="span8 offset1 clearCls"></div>
						</div>
								<div class="row">
									<div class="offset1">
										<div class="row-fluid">
											
											<div class="span5">
												Select Constituency <font class="requiredFont">*</font>
												<!--<select id="constituencyId" onChange="getBoothsDetailsByConstituencyId(this.value)"></select>
													<s:select theme="simple"  name="constituency" id="constituencyForWm"  headerKey="0" headerValue="Select Constituency" list="dataAvilableConstituencies" listKey="id" listValue="name" onChange="getBoothsDetailsByConstituencyId(this.value,'boothIdForWm')"/>-->
													<s:select theme="simple"  name="constituency" id="constituencyForWm"  headerKey="0" headerValue="Select Constituency" list="dataAvilableConstituencies" listKey="id" listValue="name" onChange="getBoothsDetailsInCallStatusInfo(this.value,'boothIdForWm')"/>
											</div>
											<div class="span4">
												<!--User Type <font class="requiredFont">*</font>
												<select class="input-block-level" id = "userType"> <option value="0">Select User Type</option></select>-->
												Select Booth <font class="requiredFont">*</font> : 
												<select class="input-block-level" id = "boothIdForWm" > <option value="0">Select Booth</option></select></div>
												<div class="span1" style="margin:25px -8px 0 8px;width: 15px;">
													<img id="boothImageForWm" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
												</div>
										</div>	
									</div>
								</div>
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getVerfierDetails(2,'mainajaximgForWm','dayWiseReportDivForWm')">SUBMIT</button></div>
						<div id="retunMsg" class="clearCls"></div>
						<img src='images/Loading-data.gif' class="offset5"  id="mainajaximgForWm" style="width:70px;height:60px;display:none;"/>
                         <div id="dayWiseReportDivForWm" class="clearCls"></div>
							
					</div>
				</div>
			</div>
		</div>
		<div class="row" id="thirdpPartyReport" style="display:none;">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>QC READY FOR REVIEV BOOTHS</h4>
								<img src='images/Loading-data.gif' class="offset5"  id="thirdPartyAjaxImg" style="width:70px;height:60px;display:none;"/>
								<div class="row">
									<div id="errorDivForThirdParty" class="span8 offset4"></div>
								</div>
								
								<div id="constSummary"></div>
								<div id="constSummarySub"></div>
								<div id="boothsSummary"></div>
								<div id="commentsDiv1"></div>
								
								<div class="row offset4" style="margin-top:35px;">								
									<div class="row-fluid">										
										<div class="span3">
											 Constituency <font class="requiredFont">*</font></div>
											<div class="span4">
											 <s:select theme="simple"  name="constituency" id="constituencyForThirdParty"  headerKey="0" headerValue="Select Constituency" list="constituencies" listKey="id" listValue="name"/>
										</div>												
									</div>
								</div>
								<div class="row text-center m_top10"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getFinalReportWithTP()">SUBMIT</button></div>
								<img src='images/Loading-data.gif' class="offset5"  id="thirdPartyAjax" style="width:70px;height:60px;display:none;"/>
								<div id="FinalReportWithTPId"></div></br></br>
								<div id="CommentsDiv"></div>								
					</div>						
                </div>
			</div>
		</div>
		<!--<div class="row" id="saveBoothsPercentage" style="dispaly:none;">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top30">
							<h4 id="wmtitleId"></h4>
							<div class="row">
						<div id="errorDivSB" class="span8 offset1 clearCls"></div>
						</div>
								<div class="row">
									<div class="offset1">
										<div class="row-fluid">
											
											<div class="span4">
												Select Constituency <font class="requiredFont">*</font>
													<s:select theme="simple"  name="constituency" id="constituencyForSP"  headerKey="0" headerValue="Select Constituency" list="dataAvilableConstituencies" listKey="id" listValue="name" onChange="getBoothsDetailsInCallStatusInfoForSavePercnt(this.value,'boothIdForSavePercentages')"/>
											</div>
											
											<div class="span3">
												Select Booth <font class="requiredFont">*</font> : 
												<select class="input-block-level" id = "boothIdForSavePercentages" > <option value="0">Select Booth</option></select>
											</div>
												
												
											<div class="span3">
												Percenage <font class="requiredFont">*</font> : 
												<input type="text" placeholder="Enter Percentage" id="percenageForBooth"/></div>
												<div class="span1" style="margin:25px -8px 0 8px;width: 15px;">
													<img id="boothImageForSavingPercent" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
												</div>
											</div>
											
										</div>	
									</div>
								</div>
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="saveBoothPercentage()">SUBMIT</button></div>
						<div id="retunMsg" class="clearCls"></div>
						<img src='images/Loading-data.gif' class="offset5"  id="mainajaximgForWm" style="width:70px;height:60px;display:none;"/>
                         <div id="dayWiseReportDivForWm" class="clearCls"></div>
							
					</div>
				</div>
			</div>-->
		</div>
	</div>
	
	<div class="container" style="display:none;margin-top:20px;" id="surveyUserTrackingId">
		<jsp:include page="surveyUserTracking.jsp" flush="true"/>	
	</div>
	
	<div class="container" style="display:none;margin-top:20px;" id="comparisonReportId">
		<jsp:include page="surveyMonitoring.jsp" flush="true"/>	
	</div>
	
	<div class="container" style="display:none;margin-top:20px;" id="stateWiseReportId">
		<jsp:include page="surveyDashBoard.jsp" flush="true"/>	
	</div>
	
	
	
	<script>

	</script>
	<script>	
	</script>
<script>


</script>
</body>
</html>