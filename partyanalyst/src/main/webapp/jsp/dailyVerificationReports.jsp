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
			
			
			.wiget-yellow{background:#ffcc00; border:1px solid #ccc; width:100%; height:370px;padding:10px;}
			.wiget-yellow-normal{background:#ffcc00; border:1px solid #ccc; width:100%; padding:10px;}
			.wiget-yellow-normal h3{font-size:16px;border-bottom:2px solid #eee; line-height:20px;padding:5px; }
			.wiget-yellow:hover{box-shadow: 0px -1px 5px #333;}
			.wiget-yellow small{color:red; font-size:18px;}
			.wiget-yellow h4{font-size:22px;border-bottom:2px solid #eee; text-align:center;}
			
			.Constituency-name-nav li a{width:165px;line-height:40px;color:#333333;display:block; background:#eee;padding:10px; border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin: 5px;text-decoration:none; font-size:14px; display: inline-table;float:left;}
			.Constituency-name-nav li a:hover{color:#333333;border:1px solid #ffcc00; box-shadow:0px 0px 3px #ccc;}
			
			.wiget-yellow-normal:hover{ box-shadow: 0 -1px 5px rgb(51, 51, 51);}
			.Constituency-name-nav li a:hover{color:#333333;border:1px solid #ffcc00;  background-color:rgb(204,204,204);}
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
					<li><a class="highlight" id="userTrackingReportTab" onclick="showHideReportTabs(this.id);"> User Tracking Report</a></li>
					<li><a class="highlight" id="thirdpPartyReportTab" onclick="showHideReportTabs(this.id);">QC Report</a></li>
					<li><a class="highlight" id="dashboardReportTab" onclick="showHideReportTabs(this.id);"> Dashboard </a></li>
				</ul>
			</div>
		</div>
  
	
  
  
  <div class="container">
  
  <!----- CTP DASHBOARD START  -->
		<div class="row" id="dashBoardDiv">	</div>
  <!----- CTP DASHBOARD END  -->

		<!---- Survey monitoring---->	
		<div class="span12" style="display:none;margin-top:20px;" id="stateWiseReportId">
				<!-----State Overview Div ---->
				<div class="row-fluid">
					<div class="span12 widgetservey_Red m_top20">
						<h4>State Overview</h4>
						<div class="row-fluid m_top20">
							<div class="span3 wiget-yellow-normal">
								<h3>Started Constituency Count</h3>
								<!--<h2>80</h2>-->
								<h2><a href="javascript:{getConstituencyWiseReport('${resultVO.startedConstituencyIds}');}">${resultVO.startedCount}</a></h2>
							
							</div>
							<div class="span3 wiget-yellow-normal">
								<h3>Completed Constituency Count</h3>
								<h2><a href="javascript:{getConstituencyWiseReport('${resultVO.completedConstituencyIds}');}">${resultVO.completedCount}</a></h2>
							</div>
							<div class="span3 wiget-yellow-normal">
								<h3>Processing Constituency Count</h3>
								<h2><a href="javascript:{getConstituencyWiseReport('${resultVO.processConstituencyIds}');}">${resultVO.processingCount}</a></h2>
							</div>
							<div class="span3 wiget-yellow-normal">
								<h3>Not Yet Started Constituency Count</h3>
								<h2>${resultVO.notStartedCount}</h2>
							</div>
						</div>
						
						<div class="row-fluid m_top10">
							<div class="span6 wiget-yellow-normal">
								<h3>Total Caste Collected Count</h3>
								<!-- <h2 id="TotalcasteCount">${resultVO.completedCount}</h2> -->
								<h2 id="TotalcasteCount">0</h2> 

							</div>
							<div class="span6 wiget-yellow-normal">
							<h3>	Today Caste Collected Count 	</h3>
							<!--	<h2 id="TodaycasteCount">${resultVO.completedCount}</h2> -->
							<h2 id="TodaycasteCount">0</h2> 

							</div>
						</div>
					</div>		
				</div><!-----State Overview Div end---->
				
				<div id="casteCountDiv" class="offset2" style="margin-top:20px;"></div>
				<div id="dateWisecastePopupDiv">
				<div id="PopupContentDiv">
				<img id="popupImg" src="./images/icons/search.gif" alt="Processing Image" style="display:none;"/>
				</div>
				</div>
				
				<!-- constituency Div -->
				
				<div class="row-fluid " >
				
				  <div class="span12  m_top20  widgetservey" id="buldingConstituenciesDivId">
					<h4>Constituency Wise Processing Status</h4>
					<img id="popupImgid1" src="./images/icons/search.gif" alt="Processing Image" style="display:block;margin-left:410px"/>
				  </div>
				 
				</div>
				<div class="row-fluid ">
					<div class="span12 m_top20 widgetservey">
						<h4>Districts Started</h4>
							<ul class="inline unstyled Constituency-name-nav">	
								<c:if test="${empty resultVO.started}">
									SURVEY NOT STARTED IN ANY DISTRICT
								</c:if>
								<c:forEach var="startedDistrict" items="${resultVO.started}">
									  <li><a href="javascript:{showConstituenciesDetails(${startedDistrict.locationId},'${startedDistrict.locationName}')}">${startedDistrict.locationName}</a></li>
								</c:forEach>

							</ul>
					</div>
				</div>
				
				<div class="row-fluid ">
					<div class="span12 m_top20 widgetservey">
						<h4>Districts Completed </h4>
						<ul class="inline unstyled Constituency-name-nav">	
						        <c:if test="${empty resultVO.completed}">
									SURVEY NOT COMPLETED IN ANY DISTRICT
								</c:if>

								<c:forEach var="completedDistrict" items="${resultVO.completed}">
									  <li><a href="javascript:{showConstituenciesDetails(${completedDistrict.locationId},'${completedDistrict.locationName}')}">${completedDistrict.locationName}</a></li>
								</c:forEach>
							</ul>
					</div>
				</div>
		</div>
		
		
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
												<s:select theme="simple"  name="constituency" id="constituencyId"  headerKey="0" headerValue="Select Constituency" list="dataAvilableConstituencies" listKey="id" listValue="name" onChange="getBoothsDetailsByConstituencyId(this.value,'boothId'),getCasteCollectedDatesByConstituencyId(this.value);"/>
										</div>
										<div class="span3">
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
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getDayWiseReportByConstituencyIdAndUserType()">SUBMIT</button></div>
						<div id="retunMsg" class="clearCls"></div>
                             <div id="dayWiseReportDiv1" class="clearCls" style="overflow-x:scroll;"></div>
							<img src='images/Loading-data.gif' class="offset5"  id="mainajaximg" style="width:70px;height:60px;display:none;"/>
					</div>
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
		
		
	
	<div  style="display:none;margin-top:20px;" id="surveyUserTrackingId">
		<jsp:include page="surveyUserTracking.jsp" flush="true"/>	
	</div>
</div>
</div>
<script>
var finalRes = null;
var userTypeVal;
showHideReportTabs('stateWiseReportTab');
getTotalCasteCounts();

$(".highlight").click(function()
{
	$('#titleId').html("");
	$(".highlight").removeClass("selected");
	var val= $(this).attr("value");
	
	if(val == 1){
		$('#titleId').html("Data Collector Report");		
		$("#dataCollectorTab").addClass("selected");
		userTypeVal = 1;
	}
	else if(val == 2){
		$('#verifiertitleId').html("Verifier Report");
		$("#verifierReportTab").addClass("selected");
		userTypeVal=4;
	}
	
	else if(val == 3){
		$('#wmtitleId').html("Web Monitoring Report ");
		//$("#thirdPartyReportTab").addClass("selected");
		$("#wmReportTab").addClass("selected");
				$("#wmReportDiv").show();
		userTypeVal= val;
	}
	else
	{
		$(".highlight").removeClass("selected");
		$(this).addClass("selected");
	}
})
getSurveyCompletedLocationsDetailsForSurveyStartedConstituencies();
</script>
</body>
</html>