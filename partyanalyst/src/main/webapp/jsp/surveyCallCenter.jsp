<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

		</style>
		
  </head>
  
  <body>
	<div class="container">
		<div class="row">
			<div class="span12 m_top20 survey_nav">
				<ul class="inline unstyled">
					
					<li><a class="highlight   selected" id="startTimeTab" onclick="showHideTabs(this.id);">Field Report</a></li>
					<li><a class="highlight" id="boothWiseTab" onclick="showHideTabs(this.id);">Data Report</a></li>
					<li><a class="highlight" id="callCenterTab" onclick="showHideTabs(this.id);"> Web Monitoring </a></li>
					<li><a class="highlight" id="dataCollectorWise" onclick="showHideTabs(this.id);"> Verfication report </a></li>
				</ul>
			</div>
		</div>
		<div class="row" id="callCenter">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4> Web Monitoring </h4>	
						
						 <img src='images/Loading-data.gif' class="offset5"  id="searchDataImg" style="width:70px;height:60px;display:none;"/>
						<div class="row-fluid">
							<div id="casteInfoDiv" style="background-color: #dff0d8; padding: 5px;display:none;margin-top:25px;margin-bottom:25px;" class="errClass"></div>	
						</div>
						
						<div class="row-fluid">
							<div id="voterInfoDIv" class="errClass" ></div>	
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
						<div class="row">
						<div class="span8 offset3">
									<div class="row-fluid">
						<div id="errDivIdForStartTime" class="span8 errClass offset" style="color:#FF0020;font-size:15px;" ></div>
						</div></div></div>
						<div class="row">
								<div class="span8 offset3">
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
					
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getUserDetailsByConstituency()">SUBMIT</button></div>
						  <div id="userDetailsReportDiv"></div>
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
					<div id="errDivIdForStartTime" style="color:#FF0020;font-size:15px;" ></div>	
					
						<div class="row">
								<div class="span8 offset2">
								<div id="errorMsgDiv" class="offset1 errClass"  style="color:#FF0020;font-size:15px;"></div>
									<div class="row-fluid">
									
										<div class="span4 offset1">
											<label>Select Constituency</label>
										<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="constituencyId" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" onchange="setConstituency(this.value);" />
								
										</div>
										<div class="span3">
											<label>Select User Type</label>
											<select name="constituency" id="userTypeId"  style="width:145px;" onchange="setUserTypeId(this.value)">
											<option value="0">Select user type</option>
											<option value="1">Data Collectors</option>
											
											</select>
										</div>	
										<div class="span3">
											<label>Select Date</label>
											<input type="text" placeholder="Select Date" class="input-block-level date" id="FielddateId" readonly/>
										</div>	
									</div>	
									
								</div>
							</div>
							<div class="row text-center m_top20" style="margin-right:51px;"><button type="button" class="btn btn-success" onClick="getSurveyUserLoctionCount();">SUBMIT</button>
							<img id="processingImg" style="display: none;" src="./images/icons/search.gif" alt="Processing Image"></img>
							</div>
							<div id="basicCountDiv" class="span10 m_top20">

					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row" id="dataCollector">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4 id="titleId">Verifier Report</h4>
							<div class="row">
						<div id="errorDiv" class="span8 offset1"></div>
						</div>
								<div class="row">
								<div class="offset1">
									<div class="row-fluid">
										
										<div class="span3">
											Select Constituency <font class="requiredFont">*</font>
											<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="constituencyIdForVerfication" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" onchange="getBoothsDetailsByConstituencyId(this.value);" />
												
										</div>
										<div class="span3">
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
										</div>
									</div>	
									<div class="row-fluid">
										
										
									</div>
									</div>
									</div>
						<div class="row text-center m_top20"><button type="button" class="btn btn-success" style="cursor:pointer;" onclick="getDayWiseReportByConstituencyIdAndUserType()">SUBMIT</button></div>
						<div id="retunMsg"></div>
                             <div id="dayWiseReportDiv1"></div>
							<img src='images/Loading-data.gif' class="offset5"  id="mainajaximg" style="width:70px;height:60px;display:none;"/>
					</div>
				</div>
				
				
			</div>
		</div>
	<script>
		var userIds = new Array();
		<c:forEach var="user" items="${usersList}">
			userIds.push('${user.id}')
		</c:forEach>
		console.log(userIds);
	</script>
	<script>
		showHideTabs('startTimeTab');
		$(".highlight").click(function()
		{
			$(".highlight").removeClass("selected");
			$(this).addClass("selected");
		})
		
		$( document ).ready(function() {
		$('#boothIdForVerfication').multiselect({
			  noneSelectedText:"Select Booth(s)"});
		});	
		
		$(function() {
			$(".date").datepicker({ 
			dateFormat: 'dd-mm-yy',
		   }).datepicker('setDate', new Date());
		  
		});

	</script>
 </body>
 </html>