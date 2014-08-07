<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <html>
  <head>	
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="js/maps/leaflet.js"></script>
<link rel="stylesheet" href="css/leaflet.css"></link>
<script src="js/maps/google.js"></script>
<script src="js/maps/Permalink.js"></script>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<script src="js/maps/googleMap.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/surveyCallCenter.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 

		<style>
			body{background:#f0f0f0;}
			.m_top20{margin-top:20px;}
			.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
			.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
			.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
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
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:13px; padding:12px 4px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
			.survey_nav ul li a:hover{background:rgba(255,0,0,0.1);}
			.survey_nav ul li a.selected{color:#fff; background:red;text-shadow:0px 1px #4f4f4f; }
			
			table tr td a{color:#333;}

			.wiget-yellow{background:#ffcc00; border:1px solid #ccc; width:100%; height:370px;padding:10px;}
			.wiget-yellow:hover{box-shadow: 0px -1px 5px #333;}
			.wiget-yellow small{color:red; font-size:18px;}
			.wiget-yellow h4{font-size:22px;border-bottom:2px solid #eee; text-align:center;}
			/*-----*/
			.boothdetails-nav li a{color:#333333; background:#eee;padding:10px; width:136px; display:table;line-height:20px;border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin-bottom: 10px;text-decoration:none; font-size:16px;}
			.boothdetails-nav li a:hover{ background:#ccc; border:1px solid #ffcc00;text-show:0px 1px #fff;}
			.booths-Overview-widget{background:#ddd;padding:10px; width:100%;}
			.booths-Overview-widget-nav li{color:#333333; background:#F6DD78;padding:10px; width:140px; display:table;line-height:20px;border:1px solid #ccc;text-align:center; font-weight:bold; text-transform:uppercase; margin: 10px;text-decoration:none; font-size:16px;}
			.booths-Overview-widget-nav li hgroup h4,h5{font-size:15px;}
	
			.requiredFont{
				color:red;
				font-size:13px;
			}

			#errorDiv
			{
			 font-weight:bold;
			 color:red;
			}
			
			.highlight{
			cursor: pointer;
			}
			
			.datePickerCls{
			 cursor: text !important;
			}
			
			.survey_nav ul li .dropdown-menu{background:#ffcc00; border-top-left-radius:0px; border-top-right-radius:0px;margin-top:-1px; margin-right:5px;}
			.survey_nav ul li .dropdown li a{color:#333; }
			
			.ui-multiselect{
				width:200px !important;
				}

			#reportTab  thead th , #panchayatStatusTable thead th ,#userReportTable thead th{
				border-bottom: 1px solid black  !important;
				cursor: pointer  !important;
				font-weight: bold  !important;
				background-color: #dff0d8  !important;
				color : #468847 !important;
				line-height: 30px !important;
			}
			
			.errorRed{background:red;}
			.errorYellow{background:yellow;}
			.errorLgreen{background:lightgreen;}
			.errorGreen{background:green;}
	
	.ui-icon-closethick 
		{
			margin-top:-8px !important;
			margin-left:-8px !important;
		}
		</style>
		
  </head>
  
  <body>
	<div class="container">
		<div class="row">
			<div class="span12 m_top20 survey_nav">
				<ul class="inline unstyled">
					
					<!-- <li><a class="highlight  " id="completedBoothsTab" onclick="showHideTabs(this.id);">Completed Booths</a></li> -->
					<li><a class="highlight selected" id="surveyStatusRprtTab" onclick="showHideTabs(this.id);">Survey Status</a></li> 
					<li><a class="highlight" id="startTimeTab" onclick="showHideTabs(this.id);">Field Report</a></li>
					<li><a class="highlight" id="boothWiseTab" onclick="showHideTabs(this.id);">Data Report</a></li>
					<li id="webMontrId" style="display:none;"><a class="highlight" id="callCenterTab" onclick="showHideTabs(this.id);">Web Monitoring</a></li>
					<li><a class="highlight" id="dataCollectorWise" onclick="showHideTabs(this.id);">WM Verifier Report</a></li>
					  
					<c:if test="${not fn:contains(sessionScope.USER.entitlements, 'WEB_MONITORING_LEAD')}">
					<li><a class="highlight" id="surveyUserWise" onclick="showHideTabs(this.id);">User Wise Report</a></li>
					</c:if>
					<!--<li><a class="highlight" id="saveBoothPercentagesTab" onclick="showHideTabs(this.id);"> Save Booth Percentages </a></li>-->
					<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CASTE_SURVEY_CALL_CENTER')}">
					<li><a class="highlight" id="wmReportTab" value="3" onclick="showHideTabs(this.id);">WM Report</a></li>
					<li><a class="highlight" id="verifierReportTab" value="2" onclick="showHideTabs(this.id);">Verifier Report</a></li>
					<li><a class="highlight" id="thirdPartyReportTab" onclick="showHideTabs(this.id);">Third Party Report</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="row" id="callCenter">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4> Web Monitoring </h4>	
						
						 
						<div class="row-fluid">
							<div id="collecedCountDiv" class="span4" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:10px;" class="errClass"></div>
							<div id="verifiedCountDiv" class="span4" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:10px;" class="errClass"></div>
							<div id="updatedCountDiv" class="span4" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:10px;" class="errClass"></div>
						</div>
						<div class="row-fluid">
							<div id="matchedCountDiv" class="span4" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:10px;" class="errClass"></div>
							<div id="unMatchedCountDiv" class="span4" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:10px;" class="errClass"></div>
							<div id="notVerifiedCountDiv" class="span4" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:10px;" class="errClass"></div>
						</div>
						<div class="row-fluid">
							<div id="userInfoDiv1" class="span3" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:10px;" class="errClass"></div>
							<div id="userInfoDiv2" class="span3" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:10px;" class="errClass"></div>
							<div id="userInfoDiv3" class="span3" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:10px;" class="errClass"></div>
							<div id="userInfoDiv4" class="span3" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:10px;" class="errClass"></div>
						</div>
						
						<div class="row-fluid">
							<div id="userInfoDiv5" class="span4" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:10px;" class="errClass"></div>
							<div id="userInfoDiv6" class="span4" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:10px;" class="errClass"></div>
							<div id="userInfoDiv7" class="span4" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:10px;" class="errClass"></div>	
						</div>
						
						<div class="row-fluid">
							<div id="casteInfoDiv" style="background-color: #e9e9e9; padding: 5px;display:none;margin-top:25px;margin-bottom:25px;" class="errClass"></div>	
						</div>
						<img src='images/Loading-data.gif' class="offset5"  id="searchDataImg" style="width:70px;height:60px;display:none;"/>
						<div class="row-fluid">
							<div id="voterInfoDIv" class="errClass" ></div>	
						</div>
						
						<div id="editStatusDiv">
							<div id="finalInputDiv" style="border-radius:5px;background-color:#E9E9E9;padding:10px;"></div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		

	<c:if test="${!empty notStartesUsersList}">
	
		<div class="row" id="inActiveUsersDetails">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>Inactive Users Details</h4>	
							<b>NOTE:</b>InActive user means, today no sample is submitted from the user							

						<div class="row">
								<div class="span8 offset3">
									<div class="row-fluid">
										
										 <table class="table table-bordered m_top20 table-hover table-striped username">	
										 <thead class="alert alert-success">
										 <tr> 
										 <th>User Name </th>	
										 <th>Mobile No </th>	
										 </tr>						
										 </thead>
										 <tbody>
										<c:forEach var="userDtls" items="${notStartesUsersList}">
										 <tr>
											  <td> ${userDtls.name}</td>
											  <td> ${userDtls.mobileNo}</td>
										 </tr>
										</c:forEach>				
										 </tbody>
										</table>
										
									</div>	
								
									</div>
									</div>
															
					</div>
				</div>
			</div>
		</div>
		
	</c:if>
		<div class="row" id="startTime">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>User Field Report</h4>
<img id="fieldReportSummaryimg" style="display: none;width: 70px; height: 60px;" src="./images/Loading-data.gif" alt="Processing Image"  class="offset5"></img>
							<div id="fieldReportSummary"></div>					
						<div class="row">
						<div class="span8 offset2">
									<div class="row-fluid">
						<div id="errDivIdForStartTime" class="span8 errClass offset" style="color:#FF0020;font-size:15px;" ></div>
						</div></div></div>
						<div class="row">
								<div class="span8 offset2">
									<div class="row-fluid">
										
										<div class="span6">
											Select Constituency <font class="requiredFont">*</font>
												<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="userConstituencyId" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" />
										</div>
										<div class="span6">
											Select Date <font class="requiredFont">*</font>
											<div>
											<input type="text" placeholder="Select Date" class="input-block-level date" id="dateId" readonly>
											</div>
										</div>
										
									</div>	
								
									</div>
									</div>
					
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getLeadersAndUsersByConstituency(),getUserDetailsByConstituency();">SUBMIT</button></div>
						
						  <div id="userDetailsReportDiv"></div>
						  
						  <img src='images/Loading-data.gif' class="offset5"  id="userFieldImage" style="width:70px;height:60px;display:none;"/>
						  <div class="row-fluid " id = "leaderAndUserDetailsDiv"></div>	
						    <div class="row-fluid " id = "leaderAndUserDetailsInactiveDiv"></div>	
						  <div class="row-fluid " id = "leaderDetailsDiv"></div>
						<div class="row-fluid " id="detaildDiv" style="display:none;">
							<div class="span12 m_top20 widgetservey" id="weathermap" style="height:500px"></div>
						</div>
						<div class="row-fluid " id= "tableDiv"></div>
						<div class="row-fluid " id= "responceCountDiv"></div>
				
											
					</div>
				</div>
			</div>
		</div>
		
		<div class="row" id="boothWise">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>Field Data Report</h4>	
							<img id="fieldDataSummaryimg" style="display: none;width: 70px; height: 60px;" src="./images/Loading-data.gif" alt="Processing Image"  class="offset5"></img>
							<div id="fieldDataSummary"></div>
					<div id="errDivIdForStartTime" style="color:#FF0020;font-size:15px;" ></div>	
					
						<div class="row">
								<div class="span12">
								<div id="errorMsgDiv" class="offset1 errClass"  style="color:#FF0020;font-size:15px;"></div>
									<div class="row-fluid">
									
										<div class="span3 offset1">
											<label>Select Constituency</label>
										<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="constituencyId" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" onchange="setConstituency(this.value),getCasteCollectedDatesByConstituencyId(this.value,'FielddateId','FieldTodateId');" />
								
										</div>
										<div class="span2">
											<label>Select User Type</label>
											<select name="constituency" id="userTypeId"  style="width:145px;" onchange="setUserTypeId(this.value)">
											<option value="0">Select user type</option>
											<option value="1">Data Collectors</option>
											<option value="4">Data Verifier</option>
											</select>
										</div>	
										<div class="span2">
											<label>Select FromDate</label>
											<input type="text" placeholder="Select Date" class="input-block-level date" id="FielddateId" readonly/>
										</div>	
										<div class="span2">
											<label>Select ToDate</label>
											<input type="text" placeholder="Select Date" class="input-block-level date" id="FieldTodateId" readonly/>
										</div>	
									</div>	
									
								</div>
							</div>
							<div class="row text-center m_top20" style="margin-right:51px;"><button type="button" class="btn btn-success" onClick="getSurveyUserLoctionCount();">SUBMIT</button>
							
							<button type="button" class="btn btn-success" id="excelTableID" onClick="generateExcel('SurveyUsertable1');" style="display:none;">Export To Excel</button>
							</div>
							<div id="basicCountDiv" class="span10 m_top20" style="overflow-x:scroll;width:847px;display:none;"></div>
							<img src='images/Loading-data.gif' class="offset5"  id="reportDataImg" style="width:70px;height:60px;display:none;"/>
							
				</div>
			</div>
		</div>
	</div>
	
	<div class="row" id="dataCollector">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4 id="titleId">WM VERIFIER REPORT</h4>
						<div class="row">
						<div id="errorDivForVerification" class="span8 offset4"></div>
						</div>
								<div class="row">
								<div class="offset4">
									<div class="row-fluid">
										
										<div class="span3">
											Select Constituency <font class="requiredFont">*</font></div>
											<div class="span4">
											<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="constituencyIdForVerfication" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency"  />
												
										</div>
										<!--<div class="span3">
											Select Booth 
											<select class="input-block-level" id = "boothIdForVerfication" multiple="true"> <option value="0">Select Booth</option></select></div>
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
										</div>-->
									</div>	
									<div class="row-fluid">
										
										
									</div>
									</div>
									</div>
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getFinalReport()">SUBMIT</button>		
						
						
						<button type="button" class="btn btn-success" id="excelTableID1" onClick="generateExcel('daywisereportTableIdTemp');" style="display:none;">Export To Excel</button>
						
							</div>
						<div id="retunMsg"></div>
						<img src='images/Loading-data.gif' class="offset5"  id="mainajaximg" style="width:70px;height:60px;display:none;"/>
						
                             <div id="dayWiseReportDiv1" style="overflow-x:scroll;width:870px;" class=" m_top20"></div>
							
					</div>
				</div>
				
				
			</div>
		</div>
		
		<div class="row" id="statusReportDiv">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4> State wise Survey Status Report </h4>						
						<div class="row">
						<div class="span8 offset4">
									<div class="row-fluid">
						<!--<div id="constnErrDiv" class="span8 errClass offset" style="color:#FF0020;font-size:15px;" ></div>-->
						<div id="constnErrDiv" class="errClass"></div>
						</div></div></div>
						<div class="row">
								<div class="span12 offset4">
									<div class="row-fluid">
										<!--<div id="constnErrDiv"></div>-->
										<div class="span8">
											Select Constituency <font class="requiredFont">*</font>
												<s:select theme="simple" cssClass="selectBoxWidth span4 input-block-level" id="reportConstituencyId" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" />
										</div>
										
									</div>	
								
									</div>
									</div>
					
						<!--<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getReportForConstituency();"> Get Report </button> </div>-->
						<div class="row text-center m_top20">
							<button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getBoothsStatusDetailsOfConstituency();"> Get Report </button>
							
							<button type="button" class="btn btn-success" id="excelTab" onClick="generateExcel('matchedUnMatchedTable1');" style="display:none;">Export To Excel</button>
						</div>
						<div class="row-fluid " id= "tableForMatchedAndUnMatched" style="margin-top:10px;"></div>	
						<img src='images/Loading-data.gif' class="offset5 hide"  id="stateStatusAjaxMatchUnMatch" style="width:70px;height:60px;"/>	</img>				
						<div class="row-fluid " id= "basicStatusReport" style="margin-top:20px;"></div>	
						<img src='images/Loading-data.gif' class="offset5 hide"  id="stateStatusAjax" style="width:70px;height:60px;"/>	</img>				
						<div class="row-fluid" id="panchayatsStatusDiv"></div>
							<img src='images/Loading-data.gif' class="offset5"  id="statusAjaxImg" style="width:70px;height:60px;display:none;"/>
						<div class="row-fluid " id= "tableDtailsDiv" style="margin-top:20px;"></div>
						<div class="row-fluid" id="panchayatDetailsDiv" style="margin-top:20px;"></div>

					</div>
				</div>
				
				
				
			</div>
		</div>

		<div class="row" id="userReport">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4 id="titleId">Survey User Wise Report</h4>
						<div class="row">
						<div id="errorDiv" class="span8 offset1 errClass"></div>
						</div>
								<div class="row">
								<div class="offset1">
									<div class="row-fluid">
										
										
										<div class="span2">
											Select User Type 
											<select class="input-block-level" id = "userReportUserType" onChange="getRespectiveUsers(this.value)"> 
												<option value="0">Select User Type</option>
												<option value="1">Data Collector</option>
												<option value="4">Data Verifier</option>
											</select>
												
										</div>
										
										<div class="span3">
											Select Constituency 
											<select class="input-block-level" id = "userReportUserConstituency" multiple="true" onchange="getUsersForConstituenies()">
											</select>
											
												
										</div>
										<div class="span2">
											Select User  
											<select class="input-block-level" id = "userReportUser" onchange="getCasteCollectedDatesByUserId(this.value,'fromDateForUserReport','toDateForUserReport');"> <option value="0">Select User </option></select>
										</div>
										<div class="span1" style="margin:25px -8px 0 8px;width: 15px;">
										<img id="userProcessingImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
										</div>
										<div class="span2">
											From Date <font class="requiredFont">*</font>
											<div class="input-append">
											<input type="text" placeholder="From Date..." class="input-block-level date" id="fromDateForUserReport" readonly>
											</div>
										</div>
										<div class="span2">
											To Date <font class="requiredFont">*</font>
											<div class="input-append">
											 <input type="text" placeholder="To Date..." class="input-block-level date" id="toDateForUserReport" readonly>
											</div>
										</div>
										
										
									</div>	
									
									</div>
									</div>
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getUserWiseReport();">SUBMIT</button>
						
						<button type="button" class="btn btn-success" id="excelTableID2" onClick="generateExcel('userReportTable');" style="display:none;">Export To Excel</button>
						
						</div>
                         <div id="userWiseReport"></div>
					<img src='images/Loading-data.gif' class="offset5"  id="userWiseReportImg" style="width:70px;height:60px;display:none;"/></img>
					</div>
				</div>
				
				
			</div>
		</div>
		<div class="row" id="saveBoothsPercentage" style="dispaly:none;">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top30">
							<h4 id="wmtitleId"></h4>
							<div class="row">
						<div id="errorDivSB" class="span8 offset1 errClass"></div>
						</div>
								<div class="row">
									<div class="offset1">
										<div class="row-fluid">
											
											<div class="span4">
												Select Constituency <font class="requiredFont">*</font>
													<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="constituencyForSP" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" onChange="getBoothsDetailsInCallStatusInfoForSavePercnt(this.value,'boothIdForSavePercentages')"/>
													
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
			</div>
				<div class="row" id="verifierReportIdForVerifier" style="dispaly:none;">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top30">
							<h4 id="verifiertitleId"></h4>
							<div class="row">
						<div id="errorDiv1" class="span8 offset3 errClass"></div>
						</div>
								<div class="row">
									<div class="offset3">
										<div class="row-fluid">
											
											<div class="span4">
												Select Constituency <font class="requiredFont">*</font>
												
													<s:select theme="simple"  name="constituency" id="constituencyForVeriFier"  headerKey="0" headerValue="Select Constituency" list="constituenciesList" listKey="id" listValue="name" onChange="getBoothsDetailsInSurveyDetailsInfo(this.value,'boothIdForVerifier')"/>

											</div>
											<div class="span4">
												<!--User Type <font class="requiredFont">*</font>
												<select class="input-block-level" id = "userType"> <option value="0">Select User Type</option></select>-->
												Select Booth  <font class="requiredFont">*</font> : 
												<select class="input-block-level" id = "boothIdForVerifier"> <option value="0">Select Booth</option></select></div>
												<div class="span1" style="margin:25px -8px 0 8px;width: 15px;">
													<img id="boothImageForVerifier" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
												</div>
										</div>	
									</div>
								</div>
						<div class="row text-center m_top20">
							
							<button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getVerifiersInBooth()">SUBMIT</button>
							
							<button type="button" class="btn btn-success" id="excelTableID4" onClick="generateExcel('dayWiseReportTableForVerifier1');" style="display:none;">Export To Excel</button>
							
						</div>
						
						<div id="retunMsg" class="clearCls"></div>
						<img src='images/Loading-data.gif' class="offset5"  id="mainajaximgForVerifier" style="width:70px;height:60px;display:none;"/>
						 <div id="verifiersInbooths" class="clearCls"></div>
						 
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
						<div id="errorDivForWM" class="span8 offset3 errClass"></div>
						</div>
								<div class="row">
									<div class="offset3">
										<div class="row-fluid">
											
											<div class="span4">
												Select Constituency <font class="requiredFont">*</font>
												
													<s:select theme="simple"  name="constituency" id="constituencyForWm"  headerKey="0" headerValue="Select Constituency" list="constituenciesList" listKey="id" listValue="name" onChange="getBoothsDetailsInCallStatusInfo(this.value,'boothIdForWm')"/>
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
						<div class="row text-center m_top20">
						
						<button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getVerfierDetails(2,'mainajaximgForWm','dayWiseReportDivForWm1')">SUBMIT</button>
						
						<button type="button" class="btn btn-success" id="excelTableID3" onClick="generateExcel('dayWiseReportTableForVerifier1');" style="display:none;">Export To Excel</button>
						
						</div>
						
						<div id="retunMsg" class="clearCls"></div>
						<img src='images/Loading-data.gif' class="offset5"  id="mainajaximgForWm" style="width:70px;height:60px;display:none;"/>
                         <div id="dayWiseReportDivForWm1"></div>
							
					</div>
				</div>
			</div>
		</div>
				
		<div class="row" id="thirdPartyReportDiv" style="dispaly:none;">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>THIRD PARTY REPORT</h4>
								<div class="row">
									<div id="errorDivForThirdParty" class="span8 offset4"></div>
								</div>
								<div class="row offset4">								
									<div class="row-fluid">										
										<div class="span3">
											Select Constituency <font class="requiredFont">*</font></div>
											<div class="span4">
											 <s:select theme="simple"  name="constituency" id="constituencyForThirdParty"  headerKey="0" headerValue="Select Constituency" list="constituencies" listKey="id" listValue="name"/>
										</div>												
									</div>
								</div>
								<div class="row text-center m_top10"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="">SUBMIT</button></div>
								<img src='images/Loading-data.gif' class="offset5"  id="thirdPartyAjax" style="width:70px;height:60px;display:none;"/>
					</div>						
                </div>
			</div>				
		</div>
		
	</div>
	<script>
		var userIds = new Array();
		<c:forEach var="user" items="${usersList}">
			userIds.push('${user.id}')
		</c:forEach>
		//console.log(userIds);
	</script>
	<script>
		showHideTabs('surveyStatusRprtTab');
		
		$(".highlight").click(function()
		{
			$(".highlight").removeClass("selected");
			$(this).addClass("selected");
		})
		
		$( document ).ready(function() {
		$('#boothIdForVerfication').multiselect({
			  noneSelectedText:"Select Booth(s)"});
			 $('#userReportUserConstituency').multiselect({
			  noneSelectedText:"Select Constituency(s)"});
		});	

		buildConstituency();
		$('#reportConstituencyId').change(function(){
			$('#basicStatusReport').html('');
		});
		
		var availableDates;
function getCasteCollectedDatesByConstituencyId(constituencyId,fromDateId,toDateId){
var jsObj ={
	constituencyId :constituencyId
}

$.ajax({
		type:'GET',
		url: 'getCasteCollectionDatesByConstituencyId.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
		availableDates = result;
		var strtDt = availableDates[0];
		var endtDt = availableDates[availableDates.length-1];
		
		var arrStrt = strtDt.split('-');
		var arrEnd =  endtDt.split('-');
		
		var strtDate = arrStrt[0];
		var strtMonth = arrStrt[1];
		var strtYear = arrStrt[2];
		
		if(strtDate.length<2){
			strtDate = "0"+strtDate;
		}
		
		if(strtMonth.length<2){
			strtMonth = "0"+strtMonth;
		}
		
		var endDate = arrEnd[0];
		var endMonth = arrEnd[1];
		var endYear = arrEnd[2];
		
		if(endDate.length<2){
			endDate = "0"+endDate;
		}
		
		if(endMonth.length<2){
			endMonth = "0"+endMonth;
		}
		
		/* 
		var dt= strtDt.substring(0,2);
		var month = 0+strtDt.substring(3,4);
		var yr = strtDt.substring(5,9);
		var dt1= endtDt.substring(0,2);
		var month1 = 0+endtDt.substring(3,4);
		var yr1 = endtDt.substring(5,9);
		var date1 = dt+"-"+month+"-"+yr;
		var date2 = dt1+"-"+month1+"-"+yr1; */
		
		var date1 =  strtDate+"-"+strtMonth+"-"+strtYear;
		var date2 =  endDate+"-"+endMonth+"-"+endYear;
		
		$('#'+fromDateId).val(date1).datepicker({ beforeShowDay: displayDates,maxDate: '0',dateFormat: 'dd-mm-yy'});
		$('#'+toDateId).val(date2).datepicker({ beforeShowDay: displayDates ,maxDate: '0' ,dateFormat: 'dd-mm-yy'});
	});
}

function getCasteCollectedDatesByUserId(userId,fromDateId,toDateId){

var jsObj ={
	userId :userId
}

$.ajax({
		type:'GET',
		url: 'getCasteCollectionDatesByUserId.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
		availableDates = result;
		var strtDt = availableDates[0];
		var endtDt = availableDates[availableDates.length-1];
		var arrStrt = strtDt.split('-');
		var arrEnd =  endtDt.split('-');
		
		var strtDate = arrStrt[0];
		var strtMonth = arrStrt[1];
		var strtYear = arrStrt[2];
		
		if(strtDate.length<2){
			strtDate = "0"+strtDate;
		}
		
		if(strtMonth.length<2){
			strtMonth = "0"+strtMonth;
		}
		
		var endDate = arrEnd[0];
		var endMonth = arrEnd[1];
		var endYear = arrEnd[2];
		
		if(endDate.length<2){
			endDate = "0"+endDate;
		}
		
		if(endMonth.length<2){
			endMonth = "0"+endMonth;
		}
		
		/* 
		var dt= strtDt.substring(0,2);
		var month = 0+strtDt.substring(3,4);
		var yr = strtDt.substring(5,9);
		var dt1= endtDt.substring(0,2);
		var month1 = 0+endtDt.substring(3,4);
		var yr1 = endtDt.substring(5,9);
		var date1 = dt+"-"+month+"-"+yr;
		var date2 = dt1+"-"+month1+"-"+yr1; */
		
		var date1 =  strtDate+"-"+strtMonth+"-"+strtYear;
		var date2 =  endDate+"-"+endMonth+"-"+endYear;
		$('#'+fromDateId).val(date1).datepicker({ beforeShowDay: displayDates,maxDate: '0',dateFormat: 'dd-mm-yy'});
		$('#'+toDateId).val(date2).datepicker({ beforeShowDay: displayDates ,maxDate: '0' ,dateFormat: 'dd-mm-yy'});
	});
}

function displayDates(date){
	var dmy = date.getDate() + "-" + (date.getMonth()+1) + "-" + date.getFullYear();
	if ($.inArray(dmy, availableDates) != -1) {
		return [true, "","Available"];
	} else {
		return [false,"","unAvailable"];
	}
}

function getVerifiersInBooth(){

	$("#verifiersInbooths").html("");
	var boothId = $("#boothIdForVerifier").val();
	if($("#constituencyForVeriFier").val() == 0)
	{
		$("#errorDiv1").html("<font color='#FF0000'>Select Constituency</font>");
		return;
	}
	if(boothId  == 0)
	{
		 $("#errorDiv1").html('<font color="#FF0000">Select Booth</font>');
		 return;
	}
	$("#errorDiv1").html("");
	var jsObj ={
		boothId :boothId
	}

	$.ajax({
			type:'GET',
			url: 'getVerifiersInBoothsAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			buildVerifiers(result);
		});
}

function buildVerifiers(result){
	if(result.length>1){
		$("#verifiersInbooths").html("");
		var str  = "";
		for(var i in result){
			str += "<input type='radio' name='verifiers' onclick='getVerfierDetailsWithVerifier()' style='margin:4px;' value="+result[i].verifierId+"><span style='margin:4px;font-size:16px;color:blue;'>"+result[i].verifier+"</span></input>";
		}
		$("#verifiersInbooths").html(str);
		$("input:radio[name=verifiers]:first").attr('checked', true);
		getVerfierDetailsWithVerifier();
		
	}else{
		getVerfierDetails(1,'mainajaximgForVerifier','dayWiseReportDivForVerifier');
	}

	
}
	function buildConstituency()
		{
			
			var result = new Array();
			<c:forEach var="user" items="${constituenciesList}">
			var obj = {
				id : '${user.id}',
				name:'${user.name}'
			}
				result.push(obj);
			</c:forEach>
			
			$.each(result,function(index,value){
					$('#userReportUserConstituency').append('<option value="'+value.id+'"> '+value.name+'</option>');
				});
		
		$('#userReportUserConstituency').multiselect('refresh');
		}

		 function getUsersForConstituenies()
		 {
			 $("#errorDiv").html('');
			 var constituencyIds = new Array();
			 constituencyIds=$("#userReportUserConstituency").val();
			 if(constituencyIds == null)
			 {
				 $("#errorDiv").html('Select atleast Constituency...');
				 return;
			 }
			 var jsObj = {
				 constituencyIds : constituencyIds
			 }
		$.ajax({
		type:'GET',
		url: 'getUsersForConstituenciesAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
			$('#userReportUser').find('option:not(:first)').remove();
			$.each(result,function(index,value){
					
					$('#userReportUser').append('<option value="'+value.id+'"> '+value.name+'</option>');
				});
		});
		 }
		
	</script>
	
 </body>
 </html> 