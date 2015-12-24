<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Registration Report</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
 <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script> 
<script type="text/javascript" src="js/exportexcel.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	.text-lowercase{text-transform:lowercase;}
	.text-uppercase{text-transform:uppercase;}
	.text-capitalize{text-transform:capitalize;}
	.text-center{text-align: center;}
	.text-red{color:#dc504a;}
	.text-green{color:#4dbd74;}
	.text-orange{color:#f9a834;}
	.text-skyblue{color:#46acca;}
	.mb-0{margin-bottom:0px}
	.mb-10{margin-bottom:10px}
	.Previousmembercount td{width:20%;}
	.membercount td{width:25%;}
	.membercount td h2, .Previousmembercount td h2{margin:0px;}
	.progress{height:10px;}
	.height-300{height: 300px; overflow: auto;}
	.height-320{height: 300px; overflow: auto;width: 440px;}
	.f-16{font-size: 16px;}
	body {
    color: #333333;
    font-size: 14px;
    line-height: 20px;
    margin: 0;
    }
	p {
    color: #333;
    font-size: 14px;
   }
   .background {
    background: none repeat scroll 0 0 #e5e5e5;
   }
   .text-right {
    text-align: right;
   }
   .imgStyle{
      margin-left: 75px;
      margin-top: 30px;
	}

  #userStatusDialogDIV,#locationStatusDialogDIV{
    padding-top: 35px;
  }
	.survey_nav{height:38px; background: #ffea51;
					background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZWE1MSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmE2MDAiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
					background: -moz-linear-gradient(top,  #ffea51 0%, #ffa600 100%);
					background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffea51), color-stop(100%,#ffa600));
					background: -webkit-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -o-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -ms-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: linear-gradient(to bottom,  #ffea51 0%,#ffa600 100%);
					filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffea51', endColorstr='#ffa600',GradientType=0 );
					}
			.survey_nav ul li{line-height:38px;}
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:13px; padding:12px 12px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
			.survey_nav ul li a:hover{background:rgba(255,0,0,0.1);}
			.survey_nav ul li a.selected{color:#fff; background:red;text-shadow:0px 1px #4f4f4f; }
	.highlight {
        cursor: pointer;
    }
	
	#usersStatusReportTab  thead th,#daywiseReportsTab  thead th, #locationWiseReportTab  thead th,#usersStatusReportTab2  thead th{
				background-color: #dff0d8  !important;
				color : #468847 !important;
				line-height: 20px !important;
			}
			
			#statedisplaydivid,#trackingStatesDispalyMainDiv,#distdisplaydivid,#constdisplaydivid,#parlConstdisplaydivid{display:none;}
			#statedisplaydivid1,#distdisplaydivid1,#constdisplaydivid1{display:none;}
			#statedisplaydivid2,#distdisplaydivid2,#constdisplaydivid2,#parlConstdisplaydivid2{display:none;}
	</style>
</head>
<body>
   <div class="container m_top10">
        <div class="row" style="margin-top:10px;">
		  <div class="span12 m_top20 survey_nav">
			<ul class="inline unstyled">
				<li><a href="cadreDashBoardAction.action">Home</a></li>
				<li><a onclick="showHideTabs(this.id);" id="userReportTab" class="highlight selected">Users Working Status</a></li>
                <li><a onclick="showHideTabs(this.id);" id="locationReportTab" class="highlight">Location Wise Cadre Info</a></li>
				<li><a onclick="showHideTabs(this.id);" id="userTrackingTab" class="highlight">Users Working Consolidation Report</a></li>
				<li><a onclick="showHideTabs(this.id);" id="slowUserTrackingTab" class="highlight">Slow User Tracking Details</a></li>
			</ul>
		  </div>
		</div>
		<div id="usersWorkingStatusDiv">
			<div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase">Users Working Status</h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errStatusDiv" align="center" ></div>
			   <table  style="margin-left: 270px;">
					 <tr>
					   <td><b>Select Scope : </b></td>
					   <td>
						 <c:if test="${sessionScope.USER.accessType == 'STATE'}">
							<select id="selLctnType" onchange="selectLocation(this.value)">						 
							<option value="0">All</option> 
							<option value="1">State</option>
							<option value="2">District</option>
							<option value="3">Constituency</option>
							<option value="4">Parliament</option>
							</select>
							</c:if>
							<c:if test="${sessionScope.USER.accessType == 'DISTRICT'}">
							<select id="selLctnType" onChange="selectLocationByAccess(this.value)">	
								<option value="2">District</option>
								<option value="3">Constituency</option>
								<option value="4">Parliament</option>
								</select>
							</c:if>
							<c:if test="${sessionScope.USER.accessType == 'MP'}">
								<select id="selLctnType">	
								
								<option value="4">Parliament</option>
								</select>
							</c:if>
							<c:if test="${sessionScope.USER.accessType == 'MLA'}">
								<select id="selLctnType">	
								<option value="3">Constituency</option>			
								</select>
							</c:if>
						</td>
					 </tr>
				     <tr id="statedisplaydivid">
						<td><b>Select State</b></td>
						<td>
						 <c:if test="${sessionScope.USER.stateName == 'both'}">
						  <select id="statesDivId">
							<option value="0">All</option>
							<option value="1">AndhraPradesh</option>
							<option value="2">Telangana</option>
						  </select>
						  </c:if>
						  <c:if test="${sessionScope.USER.stateName == 'TS'}">
						  <select id="statesDivId">
							<option value="2">Telangana</option>
						  </select>
						  </c:if>
						   <c:if test="${sessionScope.USER.stateName == 'AP'}">
						  <select id="statesDivId">
							<option value="1">AndhraPradesh</option>
						  </select>
						  </c:if>
						</td>
				     </tr>
				   <tr id="distdisplaydivid">
					   <td><b>Select District : </b></td>
					   <td><select id="displaydistbox"></select></td>
				   </tr>
				   <tr id="constdisplaydivid">
					   <td><b>Select constituency : </b></td>
					   <td><select id="displayconstbox"></select></td>
				   </tr>
				   <tr id="parlConstdisplaydivid">
					   <td><b>Select Parliament : </b></td>
					   <td><select id="displayParlConstbox"></select></td>
				   </tr>
				   <tr><td><b>From Date :</b>&nbsp;</td><td><input type="text" readonly="readonly" id="fromDate"/></td></tr>
				   <tr><td><b>To Date   :</b>&nbsp;</td><td><input type="text" readonly="readonly" id="toDate" /></td></tr>
				<tr><td></td><td><input type="checkbox" id="timeCheckBox" style="margin-top:-3px">&nbsp;&nbsp;Start Time And End Time 
				</td></tr>
				<tr><td></td><td> <input type="radio" name="sourceTyperadio" id="sourceType" value="TAB" checked style="margin-top: -3px;">&nbsp;TAB&nbsp;<input type="radio" name="sourceTyperadio" id="sourceType1" value="WEB" style="margin-top: -3px;">&nbsp;WEB&nbsp;<input type="radio" name="sourceTyperadio" id="sourceType2" value="ONLINE" style="margin-top: -3px;">&nbsp;ONLINE&nbsp;
				</td></tr>
				   <tr>
				      <td></td><td><input type="button" style="margin-left: 12px;margin-top: 13px;" class="btn btn-success" id="getCandidateDataCollectionInfoId" onclick="getCandidateDataCollectionInfo();" value="Submit"/>
						<img id="ajaxImgStyle" style="display:none;margin-left: 10px;" src="images/icons/search.gif"/>
					  </td>
				  </tr>
			</table>
					<div id="userStatusDialogDIV"></div>
			  </div>
			</div>
		</div>
		<div id="locationWiseCadreInfoDiv"  style="display:none;">
		   <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase">Location Wise Cadre Registration Info</h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
				  <div id="errMsgDiv"></div>
				  <table  style="margin-left: 270px;">
				     <tr>
					   <td><b>Select Report Level :</b></td>
					   <td>
					    <c:if test="${sessionScope.USER.accessType == 'STATE'}">
					   <select id="locationsDispalyId" onchange="showCorrespondingLocs(this.value);">
							 <option value="2">State</option>
							 <option value="3">District</option>
							 <option value="4">Constituency</option>
							 <option value="5">Mandal/Municipality</option>
							 <option value="6">Panchayat</option>
							 <option value="9">Booth</option>
					       </select> 
						   </c:if>
						    <c:if test="${sessionScope.USER.accessType == 'DISTRICT'}">
							 <select id="locationsDispalyId" onchange="showCorrespondingLocsByAccess(this.value,'${sessionScope.USER.accessType}');">
							<option value="3">District</option>
							 <option value="4">Constituency</option>
							 <option value="5">Mandal/Municipality</option>
							 <option value="6">Panchayat</option>
							 <option value="9">Booth</option>
							</select>
							</c:if>
							 <c:if test="${sessionScope.USER.accessType == 'MP' || sessionScope.USER.accessType == 'MLA'}">
							 <select id="locationsDispalyId" onchange="showCorrespondingLocsByAccess(this.value,'${sessionScope.USER.accessType}');">
							 <option value="4">Constituency</option>
							 <option value="5">Mandal/Municipality</option>
							 <option value="6">Panchayat</option>
							 <option value="9">Booth</option>
							</select>
							</c:if>
							
					   </td>
				     </tr>
				     <tr id="statesDispalyMainDiv">
						 <td><b>Select State :</b></td>
						 <td>  
						  <c:if test="${sessionScope.USER.stateName == 'both'}">
						   <select id="statesDispalyId" onchange="getConstituenciesForState(this.value);">
							 <option value="0">All</option>
							 <option value="1">Andhra Pradesh</option>
							 <option value="36">Telangana</option>
						   </select> 
						   </c:if>
						    <c:if test="${sessionScope.USER.stateName == 'TS'}">
							<select id="statesDispalyId">							
							  <option value="36">Telangana</option>							
						   </select> 
							</c:if>
							 <c:if test="${sessionScope.USER.stateName == 'AP'}">
							 <select id="statesDispalyId">						
							 <option value="1">Andhra Pradesh</option>							
						   </select> 
							 </c:if>
						 </td>
					 </tr>
					 <tr id="districtsDispalyMainDiv" style="display:none;">
						 <td><b>Select District :</b></td>
						 <td>  
						   <select id="districtsDispalyId" onchange="getConstituenciesForDistricts(this.value);">
						   </select> 
						 </td>
					 </tr>
					 <tr id="constituencyDispalyMainDiv" style="display:none;">
						 <td><b>Select Constituency :</b></td>
						 <td>  
						   <div id="constituencySelectDIV"><select id="constituencyDispalyId">
						   </select></div>
						 </td>
					 </tr>
					 <tr id="mandalDispalyMainDiv" style="display:none;">
						 <td><b>Select Mandal/Municipality :</b></td>
						 <td>  
						   <select id="mandalDispalyId">
						   </select> 
						 </td>
					 </tr>
					 <tr id="panchayatDispalyMainDiv" style="display:none;">
						 <td><b>Select Panchayat :</b></td>
						 <td>  
						   <select id="panchayatDispalyId">
						   </select> 
						 </td>
					 </tr>
					 <tr id="boothDispalyMainDiv" style="display:none;">
						 <td><b>Select Booth :</b></td>
						 <td>  
						   <select id="boothDispalyId">
						   </select> 
						 </td>
					 </tr>
					  <tr id="formDateDiv" >
						 <td><b>From Date :</b></td>
						 <td>  
						   <input type="text" id="fromDate1" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls fromDateCls" placeholder="From Date"  readOnly="true" style="cursor:text;"></input>
						 </td>
					 </tr>
					  <tr id="toDateDiv">
						 <td><b>To Date :</b></td>
						 <td>  
						    <input type="text" id="toDate1" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls fromDateCls"  placeholder="From Date"  readOnly="true" style="cursor:text;"></input>
						 </td>
					 </tr>
					 <tr>
                       <td><b>Enter Cadre Count :</b></td>
                       <td>
                       <input type="text" id="cadreCountId" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls fromDateCls" style="cursor:text;"></input>
                      </td>
                     </tr>
					 
					 <tr><td></td><td> <input style="margin-top:10px;" type="button" id="locationSubmitBtn" class="btn btn-success" onclick="getLocationWiseCadreInfo();" value="Submit"/><img id="ajaxImgStyleNew" style="display:none;margin-left:10px; margin-top:10px;" src="images/icons/search.gif"/></td></tr>
				  </table>
				  <div id="locationStatusDialogDIV"></div>
			  </div>
		   </div>
		</div>
		
		<div id="userTrackingDiv" class="row-fluid " style="display:none;">
		 <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase"> Users Consolidation Report </h3>
				</div>
			</div>
			   <div style="min-height: 300px;background:#ffffff;margin-left: 0px;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
				  <div id="ErrorLDiv" align="center"></div>
				  <table  style="margin-left: 270px;">
				     <tr>
					   <td><b>Select Report Level :</b></td>
					    <td>
					    <c:if test="${sessionScope.USER.accessType == 'STATE'}">
						<select id="trackingLocationsDispalyId" onchange="trackingShowCorrespondingLocs(this.value);">
							 <option value="2">State</option>
							 <option value="3">District</option>
							 <option value="4">Constituency</option>
							 <option value="5">Parliament Constituency</option>
					       </select>
						   </c:if>
							<c:if test="${sessionScope.USER.accessType == 'DISTRICT'}">
						    <select id="trackingLocationsDispalyId" onchange="trackingShowCorrespondingLocsByAccess(this.value);">
							<option value="3">District</option>
							 <option value="4">Constituency</option>
							  <option value="5">Parliament Constituency</option>
						
						  </select>
						  </c:if>
						    <c:if test="${sessionScope.USER.accessType == 'MLA'}">
						  <select id="trackingLocationsDispalyId">				
						  <option value="4">Constituency</option>
						  </select>
						  </c:if>
						  <c:if test="${sessionScope.USER.accessType == 'MP'}">
						  <select id="trackingLocationsDispalyId">			
							<option value="5">Parliament</option>
						  </select>
						  
						  </c:if>						   
					   </td>
				     </tr>
				     <tr id="trackingStatesDispalyMainDiv">
						 <td><b>Select State :</b></td>
						 <td>  
						
						 <c:if test="${sessionScope.USER.stateName == 'both'}">
						  <select id="trackingStatesDispalyId" onchange="getLocationDetailsForState(this.value);">
							 <option value="0">All</option>
							 <option value="1">Andhra Pradesh</option>
							 <option value="2">Telangana</option>
						   </select>
						   </c:if>
						  <c:if test="${sessionScope.USER.stateName == 'TS'}">
						    <select id="trackingStatesDispalyId">							
							 <option value="1">Andhra Pradesh</option>						
						   </select>
						    </c:if>
						   <c:if test="${sessionScope.USER.stateName == 'AP'}">
						   <select id="trackingStatesDispalyId">							
							 <option value="1">Andhra Pradesh</option>							
						   </select>
						   </c:if>						   
						 </td>
					 </tr>
					 <tr id="trackingDistrictsDispalyMainDiv" style="display:none;">
						 <td><b>Select District :</b></td>
						 <td>  
						   <select id="trackingDistrictsDispalyId">
						   </select> 
						 </td>
					 </tr>
					 <tr id="trackingConstituencyDispalyMainDiv" style="display:none;">
						 <td><b>Select Constituency :</b></td>
						 <td>  
						   <div id="trackingConstituencySelectDIV"><select id="trackingConstituencyDispalyId">
						   </select></div>
						 </td>
					 </tr>
					 <tr id="trackingParlDispalyMainDiv" style="display:none;">
						 <td><b>Select Parliament :</b></td>
						 <td>  
						   <select id="trackingParlDispalyId">
						   </select> 
						 </td>
					 </tr>
					 
					  <tr id="formDateDiv" >
						 <td><b>From Date :</b></td>
						 <td>  
						   <input type="text" id="userTrackingFromDate" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls " placeholder="From Date"  readOnly="true" style="cursor:text;"></input>
						 </td>
					 </tr>
					  <tr id="toDateDiv">
						 <td><b>To Date :</b></td>
						 <td>  
						    <input type="text" id="userTrackingToDate" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls "  placeholder="From Date"  readOnly="true" style="cursor:text;"></input>
						 </td>
					 </tr>
					 
					 <tr>
					 <td></td>
					 <td><input type="button" style="margin-left: 12px;margin-top: 13px;" class="btn btn-success" id="getUserTrackingReport" onclick="getUserTrackingReport();" value="Submit"/>
						<img id="ajaxImgStyle1" style="display:none;margin-left: 10px;" src="images/icons/search.gif"/>
					  </td>
					 </tr>
				  </table>
				  <div id="userTrackingDetls" style="overflow:scroll;display:none;"></div>
			  </div>
		   </div>
		
		
		<div id="slowUserTrackingTabDiv"  style="display:none;">
		   <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase">Slow Users Tracking</h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errStatusDiv1" align="center" ></div>
			   <table  style="margin-left: 270px;">
					 <tr>
					   <td><b>Select Scope : </b></td>
					   <td>
					    <c:if test="${sessionScope.USER.accessType == 'STATE'}">
						  <select id="selLctnType2" onchange="selectLocation2(this.value)">
							<option value="0">All</option>
							<option value="1">State</option>
							<option value="2">District</option>
							<option value="3">Constituency</option>
							<option value="4">Parliament</option>
						  </select>
						  </c:if>
						  <c:if test="${sessionScope.USER.accessType == 'DISTRICT'}">
						    <select id="selLctnType2" onchange="selectLocationByAccess(this.value)">
							<option value="2">District</option>
							<option value="3">Constituency</option>
							<option value="4">Parliament</option>
						  </select>
						  </c:if>
						    <c:if test="${sessionScope.USER.accessType == 'MLA'}">
						  <select id="selLctnType2">						
							<option value="3">Constituency</option>
						  </select>
						  </c:if>
						  <c:if test="${sessionScope.USER.accessType == 'MP'}">
						  <select id="selLctnType2">						
							<option value="4">Parliament</option>
						  </select>
						  
						  </c:if>
						</td>
					 </tr>
				     <tr id="statedisplaydivid2">
						<td><b>Select State</b></td>
						<td>
						<c:if test="${sessionScope.USER.stateName == 'both'}">
						  <select id="statesDivId2">
							<option value="0">All</option>
							<option value="1">AndhraPradesh</option>
							<option value="2">Telangana</option>
						  </select>
						   </c:if>
						  <c:if test="${sessionScope.USER.stateName == 'TS'}">
						   <select id="statesDivId2">
							<option value="2">Telangana</option>
						  </select>
						    </c:if>
						   <c:if test="${sessionScope.USER.stateName == 'AP'}">
						    <select id="statesDivId2">
							<option value="1">AndhraPradesh</option>
						  </select>
						   </c:if>
						</td>
				     </tr>
				   <tr id="distdisplaydivid2">
					   <td><b>Select District: </b></td>
					   <td><select id="displaydistbox2"></select></td>
				   </tr>
				   <tr id="constdisplaydivid2">
					   <td><b>Select constituency : </b></td>
					   <td><select id="displayconstbox2"></select></td>
				   </tr>
				    <tr id="parlConstdisplaydivid2">
					   <td><b>Select Parliament : </b></td>
					   <td><select id="displayParlConstbox2"></select></td>
				   </tr>
				   <tr><td><b>From Date :</b>&nbsp;</td><td><input type="text" readonly="readonly" id="fromDate2"/></td></tr>
				   <tr><td><b>To Date   :</b>&nbsp;</td><td><input type="text" readonly="readonly" id="toDate2" /></td></tr>
				   <tr><td><input type="checkbox" id="slowUsersCheck" style="margin-top:-3px">&nbsp;&nbsp;Show Only Slow Users</td></tr>
				   <tr>
				      <td></td><td><input type="button" style="margin-top: 13px;" class="btn btn-success" id="getCandidateDataCollectionInfoId2" onclick="getCandidateDataCollectionInfo2();" value="Submit"/>
						<img id="ajaxImgStyle2" style="display:none;margin-left: 10px;" src="images/icons/search.gif"/>
					  </td>
				  </tr>
			</table>
					<div id="userStatusDialogDIV2" style="padding-top: 20px"></div>
			  </div>
			</div>
		</div>
		
		
		<div id="dialogueLocationsCadDiv" style="display: none;">
		  <div id="dialogueLocationsCadTable"></div>
	    </div>
			<div id="userDetailsIdForDialog">
			<div id="userDetailsId" style="display:none"> </div>
			</div>
	</div>
	
<script type="text/javascript">

	var accessType = "${sessionScope.USER.accessType}";
	var accessValue = "${sessionScope.USER.accessValue}";
	var accessState = "${sessionScope.USER.stateName}"
	$(document).ready(function(){
		$('#locationsDispalyId').val(1);
	});
	 
	 
	<c:if test="${sessionScope.USER.accessType == 'DISTRICT'}">	
		selectLocationByAccess(2);
		trackingShowCorrespondingLocsByAccess(3);
		showCorrespondingLocsByAccess(3,"DISTRICT");
	</c:if>
	<c:if test="${sessionScope.USER.accessType == 'MLA'}">	
		selectLocationByAccess(3);
		trackingShowCorrespondingLocsByAccess(4);
		showCorrespondingLocsByAccess(4,"MLA");
	</c:if>
	<c:if test="${sessionScope.USER.accessType == 'MP'}">	
		selectLocationByAccess(4);
		trackingShowCorrespondingLocsByAccess(5);
		showCorrespondingLocsByAccess(4,"MP");
	</c:if>
	getLocationNameByAccessValues(accessType,accessValue);
	
	$("#statesDivId").change(function(){
			var lctnType = $("#selLctnType").val();
			if(lctnType == 3){
				getConstituenciesUWS();
				$("#distdisplaydivid").hide();
			}
			if(lctnType ==2) {
				getdistrictsUWS();
				$("#constdisplaydivid").hide();
			}
			if(lctnType ==4) {
				getparliamentConstituencies('displayParlConstbox');
			}
		});
		$("#statesDivId2").change(function(){
			var lctnType = $("#selLctnType2").val();
			if(lctnType == 3){
				getConstituenciesUWS2();
				$("#distdisplaydivid2").hide();
			}
			if(lctnType ==2) {
				getdistrictsUWS2();
				$("#constdisplaydivid2").hide();
			}
			if(lctnType ==4) {
				getparliamentConstituencies2('displayParlConstbox2');
			}
		});
		$("#fromDate,#fromDate1,#userTrackingFromDate,#fromDate2").datepicker({
			dateFormat: "dd-mm-yy",
			changeMonth: true,
			changeYear: true,
			maxDate: new Date()
		})
		$("#fromDate,#fromDate1,#userTrackingFromDate,#fromDate2").datepicker("setDate", new Date());
		$("#toDate,#toDate1,#userTrackingToDate,#toDate2").datepicker({
			dateFormat: "dd-mm-yy",
			changeMonth: true,
			changeYear: true,
			maxDate: new Date()
		})
		$("#toDate,#toDate1,#userTrackingToDate,#toDate2").datepicker("setDate", new Date());
	
function getCandidateDataCollectionInfo(){
    var allConstituencies = "";
	var ruralConstis = "";
    $("#userStatusDialogDIV").html("");
    $("#errStatusDiv").html("");
	
	var locationType=$( "#selLctnType" ).val();
	var sourceType = $('input[name=sourceTyperadio]:radio:checked').val();
	var locationId;
	if(locationType==0){locationId=0;}
	if(locationType==1){locationId=getReqIds("statesDivId");}
	if(locationType==2){locationId=getReqIds("displaydistbox");}
	if(locationType==3){locationId=getReqIds("displayconstbox");}
	if(locationType==4){locationId=getReqIds("displayParlConstbox");}
	var timeCheckBox = $("#timeCheckBox").is(':checked');
	
	var startDate = $("#fromDate").val();
	var endDate = $("#toDate").val();
	
	if(startDate.trim().length >0 && endDate.trim().length >0)
		{
                 var arrr = startDate.split("-");
				    var fromDat=arrr[0];
					var frommonth=arrr[1];
					var fromyear=arrr[2];
			   var arr = endDate.split("-");
					var toDat=arr[0];
					var tomonth=arr[1];
					var toyear=arr[2];
					
					if(fromyear>toyear){
						$('#errStatusDiv').html('<font style="color:red;">From Date should not greater than To Date </font>');
						  return;
					}
					 if(frommonth>tomonth){
						   if(fromyear == toyear){
							$('#errStatusDiv').html('<font style="color:red;">From Date should not greater than To Date </font>');
						   return;
						}
						
					}
					
					if(fromDat>toDat){	
						if(frommonth == tomonth && fromyear == toyear){			
							$('#errStatusDiv').html('<font style="color:red;">From Date should not greater than To Date </font>');
						   return;	
						   }
					}			
		}
		
	$("#getCandidateDataCollectionInfoId").attr("disabled","disabled");
	$("#ajaxImgStyle").show();
    $.ajax({
          type:'GET',
          url: 'getCadreDashBoardBasicInfo.action',
          data: {task:"candidateDataCollectionInfo",locationType:locationType,locationId:locationId,fromDate:$("#fromDate").val(),toDate:$("#toDate").val(),timeCheckBox:timeCheckBox,sourceType:sourceType,stateTypeId:$('#statesDivId').val()}
       }).done(function(result){
	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
		  if(sourceType == "TAB")
	      buildTABUsers(result,sourceType,timeCheckBox);
		  else if(sourceType == "WEB")
		buildWEBUsers(result,sourceType,timeCheckBox);
		else if(sourceType == "ONLINE")
		buildONLINEUsers(result,sourceType,timeCheckBox);
       });
   }
   function buildONLINEUsers(result,sourceType,timeCheckBox)
   {
	    var str ='';
		var searchType = $('#selLctnType').val();
			if(result.length > 0){
		        str+='<input type="button"  style="margin-bottom:15px;margin-left: 375px;"  class="btn" onclick="generateExcel(\'usersStatusReportTab\');" value="Click Here To Generate Excel"/>';
		        str+='<div id="resultTableDiv" style="overflow-x:scroll;"><table class="table table-bordered table-striped table-hover" id="usersStatusReportTab"><thead>';
				str+='<tr>';
				if($("#fromDate").val()!=$("#toDate").val()){				
					str+='<th rowspan="2" >State</th>';
					str+='<th rowspan="2" >District</th>';
					if(searchType != 2)
					{
						str+='<th rowspan="2">Parliament</th>';
						str+='<th rowspan="2">Constituency</th>';	
					}					
					//str+='<th rowspan="2">User</th>';
					//str+='<th rowspan="2">Name</th>';
					//str+='<th rowspan="2" >MobileNo</th>';
					
					//str+='<th rowspan="2">Tab No</th>';*/
					str+='<th rowspan="2">Total Count</th>';
					//str+='<th rowspan="2">Total Amount</th>';
				}else{
					str+='<th>State</th>';
					str+='<th>District</th>';
					if(searchType != 2)
					{
						str+='<th>Parliament</th>';
						str+='<th>Constituency</th>';	
					}					
					/*str+='<th>User</th>';
					str+='<th>Name</th>';
					str+='<th>MobileNo</th>';
					
					str+='<th>Tab No</th>';*/
					str+='<th>Total Count</th>';
					//str+='<th>Total Amount</th>';
				}
				

					for(var i in result[0].infoList){
						if($("#fromDate").val()!=$("#toDate").val()){
							if(timeCheckBox == true){
								str+='<th colspan="3">'+result[0].infoList[i].date+'</th>';
							}else{ 
								str+='<th colspan="1">'+result[0].infoList[i].date+'</th>';
							}
						}else{
							if(timeCheckBox == true){
								str+='<th>Start Time</th>';
								str+='<th>End Time</th>';
							}
								//str+='<th>Count</th>';
						}
					}
				str+='</tr>';
				if($("#fromDate").val()!=$("#toDate").val()){
					str+='<tr>';
					for(var i in result[0].infoList){
						if(timeCheckBox == true){
						   str+='<th>Start Time</th>';
						   str+='<th>End Time</th>';
						   str+='<th>Count</th>';
						}else{
						   str+='<th>Count</th>';
						}
					}
					str+='</tr>';
				}
				str+='</thead><tbody>';
				
				
				for(var i in result){
				  str+='<tr>';
				  
				  if(result[i].state != null){
				     str+='  <td>'+result[i].state+'</td>';
				   }else{
				      str+='  <td></td>';
				   }				   
				   if(result[i].district != null){
				     str+='  <td>'+result[i].district+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				   if(searchType != 2)
				   {
						if(result[i].parliament != null){
							str+='  <td>'+result[i].parliament+'</td>';
						}else{
							str+='  <td></td>';
						}
				   
				 
					   if(result[i].name != null){
						 str+='  <td>'+result[i].name+'</td>';
					   }else{
						  str+='  <td></td>';
					   }
				   
				   }
				  /* 
				  if(result[i].memberShipNo != null){
				     str+='  <td>'+result[i].memberShipNo+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
					str+='  <td>'+result[i].name+'</td>';
				   str+='  <td>'+result[i].uname+'</td>';
				   
				    if(result[i].area != null){
				     str+='  <td>'+result[i].area+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				 
				   str+='  <td>'+result[i].tabNo+'</td>';*/
				   if(result[i].totalCount > 0){
				      str+='  <td>'+result[i].totalCount+'</td>';
					}else{
					  str+='  <td></td>';
					}
					/*if(result[i].totalCount > 0){
				      str+='  <td>'+result[i].totalAmount+'</td>';
					}
					else{
					  str+='  <td></td>';
					}*/
				 
				  for(var j in result[i].infoList){
				  
				  if($("#fromDate").val()!=$("#toDate").val()){
					if(timeCheckBox == true){
						if(result[i].infoList[j].area != null){
						  str+='  <td>'+result[i].infoList[j].area+'</td>';
						}else{
						  str+='  <td></td>';
						}
						if(result[i].infoList[j].location != null){
						  str+='  <td>'+result[i].infoList[j].location+'</td>';
						}else{
						  str+='  <td></td>';
						}
						
						if(result[i].infoList[j].totalCount != null){
						  str+='  <td>'+result[i].infoList[j].totalCount+'</td>';
						}else{
						  str+='  <td></td>';
						}
					}else{
						if(result[i].infoList[j].totalCount != null){
						  str+='  <td>'+result[i].infoList[j].totalCount+'</td>';
						}else{
						  str+='  <td></td>';
						}
					}
					
					/*if(result[i].infoList[j].totalCount != null){
				      str+='  <td>'+result[i].infoList[j].amount+'</td>';
					}
					else{
					  str+='  <td></td>';
					}*/
				  }else{
					if(timeCheckBox == true){
						if(result[i].infoList[j].area != null){
						  str+='  <td>'+result[i].infoList[j].area+'</td>';
						}else{
						  str+='  <td></td>';
						}
						
						if(result[i].infoList[j].location != null){
						  str+='  <td>'+result[i].infoList[j].location+'</td>';
						}else{
						  str+='  <td></td>';
						}
					}
				  }
				 
				}
				 str+='</tr>';
			}	
				
				str+='</tbody></table></div>';
				str+='<input type="button" style="margin-top:15px;margin-left: 375px;" class="btn" onclick="generateExcel(\'usersStatusReportTab\');" value="Click Here To Generate Excel"/>';
		   }else{
		     str+='<div style="font-weight:bold;padding-left: 375px;padding-top: 30px;">No Data Available</div>';
		   }
		   $("#userStatusDialogDIV").html(str);
		   $("#ajaxImgStyle").hide();
		   $("#getCandidateDataCollectionInfoId").removeAttr("disabled");
		   $("#usersStatusReportTab").dataTable({
					aLengthMenu: [
						[25, 50, 100, 200, -1],
						[25, 50, 100, 200, "All"]
					],
					iDisplayLength: -1
				});
   }
   function buildWEBUsers(result,sourceType,timeCheckBox)
   {
	    var str ='';
if(result.length > 0){
		        str+='<input type="button"  style="margin-bottom:15px;margin-left: 375px;"  class="btn" onclick="generateExcel(\'usersStatusReportTab\');" value="Click Here To Generate Excel"/>';
		        str+='<div id="resultTableDiv" style="overflow-x:scroll;"><table class="table table-bordered table-striped table-hover" id="usersStatusReportTab"><thead>';
				str+='<tr>';
				if($("#fromDate").val()!=$("#toDate").val()){				
					//str+='<th rowspan="2" >State</th>';
					//str+='<th rowspan="2" >District</th>';
					str+='<th rowspan="2" >Parliament</th>';
					//str+='<th rowspan="2" >Constituency</th>';				
					str+='<th rowspan="2">User</th>';
					str+='<th rowspan="2">Name</th>';
					//str+='<th rowspan="2" >MobileNo</th>';
					
					//str+='<th rowspan="2">Tab No</th>';
					str+='<th rowspan="2">Total Count</th>';
					//str+='<th rowspan="2">Total Amount</th>';
				}else{
					/*str+='<th>State</th>';
					str+='<th>District</th>';*/
					str+='<th>Parliament</th>';
					//str+='<th>Constituency</th>';				
					str+='<th>User</th>';
					str+='<th>Name</th>';
					//str+='<th>MobileNo</th>';
					
					//str+='<th>Tab No</th>';
					str+='<th>Total Count</th>';
					//str+='<th>Total Amount</th>';
				}
				

					for(var i in result[0].infoList){
						if($("#fromDate").val()!=$("#toDate").val()){
							if(timeCheckBox == true){
								str+='<th colspan="3">'+result[0].infoList[i].date+'</th>';
							}else{ 
								str+='<th colspan="1">'+result[0].infoList[i].date+'</th>';
							}
						}else{
							if(timeCheckBox == true){
								str+='<th>Start Time</th>';
								str+='<th>End Time</th>';
							}
								//str+='<th>Count</th>';
						}
					}
				str+='</tr>';
				if($("#fromDate").val()!=$("#toDate").val()){
					str+='<tr>';
					for(var i in result[0].infoList){
						if(timeCheckBox == true){
						   str+='<th>Start Time</th>';
						   str+='<th>End Time</th>';
						   str+='<th>Count</th>';
						}else{
						   str+='<th>Count</th>';
						}
					}
					str+='</tr>';
				}
				str+='</thead><tbody>';
				
				
				for(var i in result){
					if(result[i].name != 'Test User')
					{
				  str+='<tr>';
				  /*
				  if(result[i].state != null){
				     str+='  <td>'+result[i].state+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				  if(result[i].district != null){
				     str+='  <td>'+result[i].district+'</td>';
				   }else{
				      str+='  <td></td>';
				   }*/
				   if(result[i].parliament != null){
				     str+='  <td>'+result[i].parliament+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				   
				  if(result[i].memberShipNo != null){
				     str+='  <td>'+result[i].memberShipNo+'</td>';
				   }else{
				      str+='  <td> Party Office</td>';
				   }
					str+='  <td>'+result[i].name+'</td>';
				   //str+='  <td>'+result[i].uname+'</td>';
				   
				   /* if(result[i].area != null){
				     str+='  <td>'+result[i].area+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				 */
				  // str+='  <td>'+result[i].tabNo+'</td>';
				   if(result[i].totalCount > 0){
				      str+='  <td>'+result[i].totalCount+'</td>';
					}else{
					  str+='  <td></td>';
					}
					/*if(result[i].totalCount > 0){
				      str+='  <td>'+result[i].totalAmount+'</td>';
					}
					else{
					  str+='  <td></td>';
					}*/
				 
				  for(var j in result[i].infoList){
				  
				  if($("#fromDate").val()!=$("#toDate").val()){
					if(timeCheckBox == true){
						if(result[i].infoList[j].area != null){
						  str+='  <td>'+result[i].infoList[j].area+'</td>';
						}else{
						  str+='  <td></td>';
						}
						if(result[i].infoList[j].location != null){
						  str+='  <td>'+result[i].infoList[j].location+'</td>';
						}else{
						  str+='  <td></td>';
						}
						
						if(result[i].infoList[j].totalCount != null){
						  str+='  <td>'+result[i].infoList[j].totalCount+'</td>';
						}else{
						  str+='  <td></td>';
						}
					}else{
						if(result[i].infoList[j].totalCount != null){
						  str+='  <td>'+result[i].infoList[j].totalCount+'</td>';
						}else{
						  str+='  <td></td>';
						}
					}
					
					/*if(result[i].infoList[j].totalCount != null){
				      str+='  <td>'+result[i].infoList[j].amount+'</td>';
					}
					else{
					  str+='  <td></td>';
					}*/
				  }else{
					if(timeCheckBox == true){
						if(result[i].infoList[j].area != null){
						  str+='  <td>'+result[i].infoList[j].area+'</td>';
						}else{
						  str+='  <td></td>';
						}
						
						if(result[i].infoList[j].location != null){
						  str+='  <td>'+result[i].infoList[j].location+'</td>';
						}else{
						  str+='  <td></td>';
						}
					}
				  }
				 
				}
				 str+='</tr>';
			}	
			}	
				str+='</tbody></table></div>';
				str+='<input type="button" style="margin-top:15px;margin-left: 375px;" class="btn" onclick="generateExcel(\'usersStatusReportTab\');" value="Click Here To Generate Excel"/>';
		   }else{
		     str+='<div style="font-weight:bold;padding-left: 375px;padding-top: 30px;">No Data Available</div>';
		   }
		   $("#userStatusDialogDIV").html(str);
		   $("#ajaxImgStyle").hide();
		   $("#getCandidateDataCollectionInfoId").removeAttr("disabled");
		   $("#usersStatusReportTab").dataTable({
					aLengthMenu: [
						[25, 50, 100, 200, -1],
						[25, 50, 100, 200, "All"]
					],
					iDisplayLength: -1
				});
   }
function buildTABUsers(result,sourceType,timeCheckBox)
{
		   var str ='';
if(result.length > 0){
		        str+='<input type="button"  style="margin-bottom:15px;margin-left: 375px;"  class="btn" onclick="generateExcel(\'usersStatusReportTab\');" value="Click Here To Generate Excel"/>';
		        str+='<div id="resultTableDiv" style="overflow-x:scroll;"><table class="table table-bordered table-striped table-hover" id="usersStatusReportTab"><thead>';
				str+='<tr>';
				if($("#fromDate").val()!=$("#toDate").val()){				
					str+='<th rowspan="2" >State</th>';
					str+='<th rowspan="2" >District</th>';
					str+='<th rowspan="2" >Parliament</th>';
					str+='<th rowspan="2" >Constituency</th>';				
					str+='<th rowspan="2">User</th>';
					str+='<th rowspan="2">Name</th>';
					str+='<th rowspan="2" >MobileNo</th>';
					
					str+='<th rowspan="2">Tab No</th>';
					str+='<th rowspan="2">Total Count</th>';
					//str+='<th rowspan="2">Total Amount</th>';
				}else{
					str+='<th>State</th>';
					str+='<th>District</th>';
					str+='<th>Parliament</th>';
					str+='<th>Constituency</th>';				
					str+='<th>User</th>';
					str+='<th>Name</th>';
					str+='<th>MobileNo</th>';
					
					str+='<th>Tab No</th>';
					str+='<th>Total Count</th>';
					//str+='<th>Total Amount</th>';
				}
				

					for(var i in result[0].infoList){
						if($("#fromDate").val()!=$("#toDate").val()){
							if(timeCheckBox == true){
								str+='<th colspan="3">'+result[0].infoList[i].date+'</th>';
							}else{ 
								str+='<th colspan="1">'+result[0].infoList[i].date+'</th>';
							}
						}else{
							if(timeCheckBox == true){
								str+='<th>Start Time</th>';
								str+='<th>End Time</th>';
							}
								//str+='<th>Count</th>';
						}
					}
				str+='</tr>';
				if($("#fromDate").val()!=$("#toDate").val()){
					str+='<tr>';
					for(var i in result[0].infoList){
						if(timeCheckBox == true){
						   str+='<th>Start Time</th>';
						   str+='<th>End Time</th>';
						   str+='<th>Count</th>';
						}else{
						   str+='<th>Count</th>';
						}
					}
					str+='</tr>';
				}
				str+='</thead><tbody>';
				
				
				for(var i in result){
				  str+='<tr>';
				  
				  if(result[i].location != null){
				     str+='  <td>'+result[i].location+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				  if(result[i].number != null){
				     str+='  <td>'+result[i].number+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				   if(result[i].percentStr != null){
				     str+='  <td>'+result[i].percentStr+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				   
				  if(result[i].memberShipNo != null){
				     str+='  <td>'+result[i].memberShipNo+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
					str+='  <td>'+result[i].name+'</td>';
				   str+='  <td>'+result[i].uname+'</td>';
				   
				    if(result[i].area != null){
				     str+='  <td>'+result[i].area+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				 
				   str+='  <td>'+result[i].tabNo+'</td>';
				   if(result[i].totalCount > 0){
				      str+='  <td>'+result[i].totalCount+'</td>';
					}else{
					  str+='  <td></td>';
					}
					/*if(result[i].totalCount > 0){
				      str+='  <td>'+result[i].totalAmount+'</td>';
					}
					else{
					  str+='  <td></td>';
					}*/
				 
				  for(var j in result[i].infoList){
				  
				  if($("#fromDate").val()!=$("#toDate").val()){
					if(timeCheckBox == true){
						if(result[i].infoList[j].area != null){
						  str+='  <td>'+result[i].infoList[j].area+'</td>';
						}else{
						  str+='  <td></td>';
						}
						if(result[i].infoList[j].location != null){
						  str+='  <td>'+result[i].infoList[j].location+'</td>';
						}else{
						  str+='  <td></td>';
						}
						
						if(result[i].infoList[j].totalCount != null){
						  str+='  <td>'+result[i].infoList[j].totalCount+'</td>';
						}else{
						  str+='  <td></td>';
						}
					}else{
						if(result[i].infoList[j].totalCount != null){
						  str+='  <td>'+result[i].infoList[j].totalCount+'</td>';
						}else{
						  str+='  <td></td>';
						}
					}
					
					/*if(result[i].infoList[j].totalCount != null){
				      str+='  <td>'+result[i].infoList[j].amount+'</td>';
					}
					else{
					  str+='  <td></td>';
					}*/
				  }else{
					if(timeCheckBox == true){
						if(result[i].infoList[j].area != null){
						  str+='  <td>'+result[i].infoList[j].area+'</td>';
						}else{
						  str+='  <td></td>';
						}
						
						if(result[i].infoList[j].location != null){
						  str+='  <td>'+result[i].infoList[j].location+'</td>';
						}else{
						  str+='  <td></td>';
						}
					}
				  }
				 
				}
				 str+='</tr>';
			}	
				
				str+='</tbody></table></div>';
				str+='<input type="button" style="margin-top:15px;margin-left: 375px;" class="btn" onclick="generateExcel(\'usersStatusReportTab\');" value="Click Here To Generate Excel"/>';
		   }else{
		     str+='<div style="font-weight:bold;padding-left: 375px;padding-top: 30px;">No Data Available</div>';
		   }
		   $("#userStatusDialogDIV").html(str);
		   $("#ajaxImgStyle").hide();
		   $("#getCandidateDataCollectionInfoId").removeAttr("disabled");
		   
		   var  selectLevelType = $("#selLctnType").val();
		   
		   if(selectLevelType  == 0 || selectLevelType == 1){
				 $("#usersStatusReportTab").dataTable({
					aLengthMenu: [
								[25, 50, 100, 200, -1],
								[25, 50, 100, 200, "All"]
							],
							iDisplayLength: -1
				 });
		   }
		   
		   if(selectLevelType  == 2){
		   		   $("#usersStatusReportTab").dataTable({
							"aaSortingFixed": [[1,'asc']],
							aLengthMenu: [
								[25, 50, 100, 200, -1],
								[25, 50, 100, 200, "All"]
							],
							iDisplayLength: -1
				});
				
		   }
		   
		   if(selectLevelType  == 3){
		   		   $("#usersStatusReportTab").dataTable({
							"aaSortingFixed": [[3,'asc']],
							aLengthMenu: [
								[25, 50, 100, 200, -1],
								[25, 50, 100, 200, "All"]
							],
							iDisplayLength: -1
				});
		   }
		   
		   if(selectLevelType  == 4){
		   		   $("#usersStatusReportTab").dataTable({
							"aaSortingFixed": [[2,'asc']],
							aLengthMenu: [
								[25, 50, 100, 200, -1],
								[25, 50, 100, 200, "All"]
							],
							iDisplayLength: -1
				});
		   }
}
   
   function getCandidateDataCollectionInfo1(){
    var allConstituencies = "";
	var ruralConstis = "";
    $("#userStatusDialogDIV1").html("");
    $("#errStatusDiv1").html("");
	
	var locationType1=$( "#selLctnType1" ).val();
	
	var locationId1;
	if(locationType1==0){locationId1=0;}
	if(locationType1==1){locationId1=getReqIds("statesDivId1");}
	if(locationType1==2){locationId1=getReqIds("displaydistbox1");}
	if(locationType1==3){locationId1=getReqIds("displayconstbox1");}
	
	var startDate1 = $("#fromDate1").val();
	var endDate1 = $("#toDate1").val();
	
	if(startDate1.trim().length >0 && endDate1.trim().length >0)
		{
                 var arrr = startDate1.split("-");
				    var fromDat=arrr[0];
					var frommonth=arrr[1];
					var fromyear=arrr[2];
			   var arr = endDate1.split("-");
					var toDat=arr[0];
					var tomonth=arr[1];
					var toyear=arr[2];
					
					if(fromyear>toyear){
						$('#errStatusDiv1').html('<font style="color:red;">From Date should not greater than To Date </font>');
						  return;
					}
					 if(frommonth>tomonth){
						   if(fromyear == toyear){
							$('#errStatusDiv1').html('<font style="color:red;">From Date should not greater than To Date </font>');
						   return;
						}
						
					}
					
					if(fromDat>toDat){	
						if(frommonth == tomonth && fromyear == toyear){			
							$('#errStatusDiv1').html('<font style="color:red;">From Date should not greater than To Date </font>');
						   return;	
						   }
					}			
		}
		
	$("#getCandidateDataCollectionInfoId1").attr("disabled","disabled");
	$("#ajaxImgStyle1").show();
    $.ajax({
          type:'GET',
          url: 'getUserTrackingDetails.action',
          data: {task:"userTrackingDetails",locationType:locationType1,locationId:locationId1,fromDate:$("#fromDate1").val(),toDate:$("#toDate1").val()}
       }).done(function(result){
			
	   });
   }
   
   function generateExcel(reqId){
     tableToExcel(reqId, 'Users Working Status');
   }
   function showCorrespondingLocs(locationLvl){
      var state = false;
	  var district = false;
	  var constituency = false;
	  var mandal = false;
	  var panchayat = false;
	  var booth = false;
	  
	  if(locationLvl == 2){
	     state = true;
	  }else if(locationLvl == 3){
	     district = true;
		 getDistricts();
	  }else if(locationLvl == 4){
	     showCompleteConstituencyDetails("ALL");
	     constituency = true;
	  }else if(locationLvl == 5){
		 $("#mandalDispalyId option").remove();
	     showCompleteConstituencyDetails("mandal")
	     constituency = true;
		 mandal = true;
	  }else if(locationLvl == 6){
	     $("#panchayatDispalyId option").remove();
	     showRuralConstituencyDetails();
	     constituency = true;
		 panchayat = true;
	  }else if(locationLvl == 9){
	     $("#boothDispalyId option").remove();
	     showCompleteConstituencyDetails("booth")
	     constituency = true;
		 booth = true;
	  }
	  if(state){
	     $("#statesDispalyMainDiv").show();
		 $("#statesDispalyId").val(0);
	  }else{
	     $("#statesDispalyMainDiv").hide();
	  }
	  if(district){
	     $("#statesDispalyMainDiv").show();
		 $("#statesDispalyId").val(0);
	     $("#districtsDispalyMainDiv").show();
		 $("#districtsDispalyId").val(0);
	  }else{
	    $("#districtsDispalyMainDiv").hide();
	  }
	  if(constituency){
	    $("#statesDispalyMainDiv").show();
		$("#statesDispalyId").val(0);
		$("#constituencyDispalyMainDiv").show();
		$("#constituencyDispalyId").val(0);
	  }else{
	    $("#constituencyDispalyMainDiv").hide();
	  }
	  if(mandal){
	     $("#constituencyDispalyMainDiv").show();
		 $("#constituencyDispalyId").val(0);
	     $("#mandalDispalyMainDiv").show();
		 $("#mandalDispalyId").val(0);
		 $("#statesDispalyMainDiv").hide();
	  }else{
	     $("#mandalDispalyMainDiv").hide();
	  }
	  if(panchayat){
	    $("#panchayatDispalyMainDiv").show();
		$("#statesDispalyMainDiv").hide();
	  }else{
	    $("#panchayatDispalyMainDiv").hide();
	  }
	  if(booth){
	    $("#boothDispalyMainDiv").show();
		$("#statesDispalyMainDiv").hide();
	  }else{
	    $("#boothDispalyMainDiv").hide();
	  }
   }
 function getDistricts(){
     var jsObj=
		{				
				stateId:1,
				elmtId:"districtList_d",
                type:"default",
				task:"findDistrictsForAState"				
		}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForAStateAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
     for(var i in result){
	   if(result[i].id == 0){
          $("#districtsDispalyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#districtsDispalyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
   function getConstituencies(repType){
    $.ajax({
          type:'GET',
          url: 'getLocationWiseRegistrationInfo.action',
		  data: {task:"assemblyNames",type:repType}
   }).done(function(result){
	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   if(repType == 'ALL'){
          allConstituencies = result;
	   }else{
	      ruralConstis = result;
	   }
   });
  }
  
  function showCompleteConstituencyDetails(type){
   if(type == "ALL"){
     $("#constituencySelectDIV").html('<select id="constituencyDispalyId"></select>');
     $("#constituencyDispalyId").append('<option value="0">ALL</option>');
   }else{
     if(type == "mandal"){
	   $("#constituencySelectDIV").html('<select id="constituencyDispalyId" onchange="getTehsils(this.value);"></select>');
	 }else if(type == "booth"){
	   $("#constituencySelectDIV").html('<select id="constituencyDispalyId" onchange="getBooths(this.value);"></select>');
	 }
     $("#constituencyDispalyId").append('<option value="0">Select Constituency</option>');
   }
     for(var i in allConstituencies){
	      $("#constituencyDispalyId").append('<option value='+allConstituencies[i].id+'>'+allConstituencies[i].name+'</option>');
	 }
  }
  function showRuralConstituencyDetails(){
     $("#constituencySelectDIV").html('<select id="constituencyDispalyId"  onchange="getPanchayats(this.value);"></select>');
     $("#constituencyDispalyId").append('<option value="0">Select Constituency</option>');
     for(var i in ruralConstis){
	      $("#constituencyDispalyId").append('<option value='+ruralConstis[i].id+'>'+ruralConstis[i].name+'</option>');
	 }
  }
  
  function getTehsils(constiId){
      $("#mandalDispalyId option").remove();
	  if(constiId == 0){
	    return;
	  }
      var jsObj=
		{
			id:constiId,
			task:"subRegionsInConstituency",
			taskType:"",
			selectElementId:"",
			address:"",
			areaType:"null",
			constId:constiId				
		}
    $.ajax({
          type:'GET',
          url: 'locationsHierarchiesAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
     for(var i in result){
	   if(result[i].id == 0){
          $("#mandalDispalyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#mandalDispalyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });	
  }
  function getPanchayats(constiId){
     $("#panchayatDispalyId option").remove();
	  if(constiId == 0){
	    return;
	  }
     $.ajax({
          type:'GET',
          url: 'getLocationWiseRegistrationInfo.action',
		  data: {task:"panchayatNames",constituencyId:constiId}
   }).done(function(result){
	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
     $("#panchayatDispalyId").append('<option value="0">ALL</option>');
     for(var i in result){
	   $("#panchayatDispalyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }
   });
  } 
  function getBooths(constiId){
    $("#boothDispalyId option").remove();
	  if(constiId == 0){
	    return;
	  }
     $.ajax({
          type:'GET',
          url: 'getLocationWiseRegistrationInfo.action',
		  data: {task:"boothNames",constituencyId:constiId}
   }).done(function(result){
	   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
     $("#boothDispalyId").append('<option value="0">ALL</option>');
     for(var i in result){
	   $("#boothDispalyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }
   });
  }
  function getReqIds(id){
    var selectedId = $("#"+id).val();
	if(selectedId == null){
	  return "";
	}
	if(selectedId == 0){
	 selectedId = "";
	 var options = $('#'+id+' option');
	 var values = $.map(options ,function(option) {
        return option.value;
     });
	  for(var i in values){
	    if(values[i] != 0){
		   if(selectedId.length > 0){
		     selectedId=selectedId+','+values[i];
		   }else{
		     selectedId = values[i];
		   }
		}
	  }
	}
	return selectedId;
  }
  function getLocationWiseCadreInfo(){
   $("#errMsgDiv").html('');
   $("#locationStatusDialogDIV").html("");
   var cadreCount=$("#cadreCountId").val().trim();

    var locationId = $("#locationsDispalyId").val();
	var reqTask="";
	var reqIds ="";
	var errMsg="";
	
	var startDate = $('#fromDate1').val();
	var endDate	= $('#toDate1').val();
		
	if(locationId == 2){
	  reqTask="stateInfo";
	  reqIds = getReqIds("statesDispalyId");
	  if(reqIds.length == 0){
	    errMsg ="Please Select State";
	  }
	}else if(locationId == 3){
	  reqTask="districtInfo";
	  reqIds = getReqIds("districtsDispalyId");
	  if(reqIds.length == 0){
	    errMsg ="Please Select District";
	  }
	}else if(locationId == 4){
	  reqTask="assemblyInfo";
	  reqIds = getReqIds("constituencyDispalyId");
	  if(reqIds.length == 0){
	    errMsg ="Please Select Constituency";
	  }
	}else if(locationId == 5){
	  reqTask="mandalInfo";
	  reqIds = getReqIds("mandalDispalyId");
	  if(reqIds.length == 0){
	    errMsg ="Please Select Mandal";
	  }
	}else if(locationId == 6){
	  reqTask="panchayatInfo";
	  reqIds = getReqIds("panchayatDispalyId");
	  if(reqIds.length == 0){
	    errMsg ="Please Select Panchayat";
	  }
	}else if(locationId == 9){
	  reqTask="boothInfo";
	  reqIds = getReqIds("boothDispalyId");
	  if(reqIds.length == 0){
	    errMsg ="Please Select Booth";
	  }
	}
	if(startDate.trim().length >0 && endDate.trim().length >0)
		{
			
		    var arrr = startDate.split("-");
		    var fromDat=arrr[0];
			var frommonth=arrr[1];
			var fromyear=arrr[2];
	   var arr = endDate.split("-");
			var toDat=arr[0];
			var tomonth=arr[1];
			var toyear=arr[2];
			
			if(fromyear>toyear){
				 errMsg ="From Date should not greater than To Date";		
			}
			 if(frommonth>tomonth){
				 if(fromyear == toyear){
					 errMsg ="From Date should not greater than To Date";		
				}
				
			}
			
			if(fromDat>toDat){	
				if(frommonth == tomonth && fromyear == toyear){			
					errMsg ="From Date should not greater than To Date";		
			   }
			}	
		}
		
	if(errMsg.length > 0){
	  $("#errMsgDiv").html('<div style="font-weight:bold;color:red;margin-bottom:8px;margin-left:400px;margin-top:5px;">'+errMsg+'</div>');
	  return;
	}
	$("#locationSubmitBtn").attr("disabled","disabled");
	$("#ajaxImgStyleNew").show();
         $.ajax({
          type:'GET',
          url: 'getLocationWiseRegistrationInfo.action',
		  data: {task:reqTask,ids:reqIds,fromDate:startDate,toDate:endDate,userCountValue:cadreCount}
	   }).done(function(result){
		   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		   location.reload(); 
    	   }
	       var str='';
	       if(result.length > 0){
		        str+='<input type="button"  style="margin-bottom:15px;margin-left: 375px;"  class="btn" onclick="generateExcel(\'locationWiseReportTab\');" value="Click Here To Generate Excel"/>';
		        str+='<div id="resultTableDiv" style="overflow-x:scroll;"><table class="table table-bordered table-striped table-hover" id="locationWiseReportTab"><thead>';
				str+='<tr>';
				if(locationId == 2){
				    str+='<th>STATE</th>';
				    str+='<th>VIEW DETAILS</th>';
			    }else if(locationId == 3){
				    str+='<th>DISTRICT</th>';
				    str+='<th>VIEW DETAILS</th>';
			    }else if(locationId == 4){
				    str+='<th>CONSTITUENCY</th>';
				    str+='<th>VIEW DETAILS</th>';
			    }else if(locationId == 5){
				    str+='<th>MANDAL/MUNICIPALITY</th>';
				    str+='<th>VIEW DETAILS</th>';
			    }else if(locationId == 6){
				    str+='<th>PANCHAYAT</th>';
				    str+='<th>VIEW DETAILS</th>';
			    }else if(locationId == 9){
				    str+='<th>BOOTH</th>';
					str+='<th>PANCHAYAT</th>';
					str+='<th>LOCATION</th>';
					str+='<th>VILLAGES COVERED</th>';
					
			    }
			    str+='<th>2014 CADRE COUNT</th>';
			    str+='<th>2012 CADRE COUNT</th>';
				if(locationId == 5 || locationId == 6 || locationId == 9){
					str+='<th> OVERALL TARGET CADRE </th>';
					str+='<th> OVERALL REGISTERED CADRE </th>';
					str+='<th> REGISTERED %</th>';
				}
				str+='</tr>';
				str+='</thead><tbody>';
				for(var i in result){
				  str+='<tr>';
				  str+='  <td>'+result[i].location+'</td>';
				  if(locationId == 9){
				    str+='<th>'+result[i].fromDate+'</th>';
					str+='<th>'+result[i].parliament+'</th>';
					str+='<th>'+result[i].state+'</th>';
				  }
				  if(locationId != 9){
				    if(result[i].apCount == null || result[i].id == null){
					  str+='<td></td>';
				   }else if(locationId == 2){
					    //str+='<td>STATE</td>';
					    str+='<td><a href="javascript:{}" title="Click Here To View District Wise Registration Info" onclick="viewDetails(\'state\','+result[i].id+',\'district\',\''+startDate+'\',\''+endDate+'\')"><i class="icon-search"></i></a><a href="javascript:{}" title="Click Here To View Constituency Wise Registration Info" onclick="viewDetails(\'state\','+result[i].id+',\'constituency\',\''+startDate+'\',\''+endDate+'\')"><i class="icon-zoom-in"></i></a></td>';
				    }else if(locationId == 3){
					    //str+='<th>DISTRICT</th>';
					    str+='<td><a href="javascript:{}" title="Click Here To View Constituency Wise Registration Info" onclick="viewDetails(\'district\','+result[i].id+',\'constituency\',\''+startDate+'\',\''+endDate+'\')"><i class="icon-search"></i></a></td>';
				    }else if(locationId == 4){
					    //str+='<th>CONSTITUENCY</th>';
				    	str+='<td>';
				    	if(result[i].name != null){
				    	   str+='<a href="javascript:{}" title="Click Here To View Mandal/Municipality Wise Registration Info" onclick="viewDetails(\'constituency\','+result[i].id+',\'mandal\',\''+startDate+'\',\''+endDate+'\')"><i class="icon-ok"></i></a>';
				        }
				    	if(result[i].area != null){
					       str+='<a href="javascript:{}" title="Click Here To View Panchayat Wise Registration Info" onclick="viewDetails(\'constituency\','+result[i].id+',\'panchayat\',\''+startDate+'\',\''+endDate+'\')"><i class="icon-zoom-in"></i></a>';
					    }
				    	   str+='<a href="javascript:{}" title="Click Here To View Booth Wise Registration Info" onclick="viewDetails(\'constituency\','+result[i].id+',\'booth\',\''+startDate+'\',\''+endDate+'\')"><i class="icon-search"></i></a>';
				    	str+='</td>';
				    }else if(locationId == 5){
					    //str+='<th>MANDAL/MUNICIPALITY</th>';
					    str+='<td>';
						if(result[i].area == null){
							str+='<a href="javascript:{}" title="Click Here To View Booth Wise Registration Info" onclick="viewDetails(\'municipality\','+result[i].id+',\'booth\',\''+startDate+'\',\''+endDate+'\')"><i class="icon-search"></i></a>';					
						}
					    if(result[i].area != null){
					       str+='<a href="javascript:{}" title="Click Here To View Panchayat Wise Registration Info" onclick="viewDetails(\'mandal\','+result[i].id+',\'panchayat\',\''+startDate+'\',\''+endDate+'\')"><i class="icon-zoom-in"></i></a>';
						   str+='<a href="javascript:{}" title="Click Here To View Booth Wise Registration Info" onclick="viewDetails(\'mandal\','+result[i].id+',\'booth\',\''+startDate+'\',\''+endDate+'\')"><i class="icon-search"></i></a>';
					    }
				    	
				    	str+='</td>';
				    }else if(locationId == 6){
					    //str+='<th>PANCHAYAT</th>';
					    str+='<td><a href="javascript:{}" title="Click Here To View Booth Wise Registration Info" onclick="viewDetails(\'panchayat\','+result[i].id+',\'booth\',\''+startDate+'\',\''+endDate+'\')"><i class="icon-search"></i></a></td>';
				    }
				  }
				    if(result[i].apCount != null){
				      str+='  <td>'+result[i].apCount+'</td>';
					}else{
					  str+='  <td></td>';
					}
					if(result[i].tgCount != null){
				      str+='  <td>'+result[i].tgCount+'</td>';
					}else{
					  str+='  <td></td>';
					}
					if(locationId == 5 || locationId == 6 || locationId == 9){
						if(result[i].targetCadre != null){
							str+='  <td>'+result[i].targetCadre+'</td>';
							if(result[i].overAllRegCount == null){
								str+='  <td> 0 </td>';
							}else{
								str+='  <td>'+result[i].overAllRegCount+'</td>';
							}
							str+='  <td>'+result[i].regPercent+'</td>';
						}else{
							str+='  <td> - </td>';
							str+='  <td> - </td>';
							str+='  <td> - </td>';
						}
					}
					
					
					
				  str+='</tr>';
				}
				str+='</tbody></table></div>';
				 str+='<input type="button" style="margin-top:15px;margin-left: 375px;" class="btn" onclick="generateExcel(\'locationWiseReportTab\');" value="Click Here To Generate Excel"/>';
		   }else{
		     str+='<div style="font-weight:bold;padding-left: 375px;padding-top: 30px;">No Data Available</div>';
		   }
		   $("#locationStatusDialogDIV").html(str);
		   $("#ajaxImgStyleNew").hide();
		   $("#locationSubmitBtn").removeAttr("disabled");
			   $("#locationWiseReportTab").dataTable({
					aLengthMenu: [
						[25, 50, 100, 200, -1],
						[25, 50, 100, 200, "All"]
					],
					iDisplayLength: -1
				});
	   });
  }
  var tabSelected=false;
  function showHideTabs(id){
     $("#userReportTab").removeClass("selected");
	 $("#locationReportTab").removeClass("selected");
	 $("#userTrackingTab").removeClass("selected");
	 $("#slowUserTrackingTab").removeClass("selected");
	 $("#userStatusDialogDIV").html("");
	 $("#locationStatusDialogDIV").html("");
	 $("#userTrackingTabDiv").html();
	 //$("#slowUserTrackingTabDiv").html("");
     if(id == "userReportTab"){
       $("#userReportTab").addClass("selected");
	   $("#locationWiseCadreInfoDiv").hide();
	   $("#userTrackingTabDiv").hide();
	   $("#slowUserTrackingTabDiv").hide();
	   $("#usersWorkingStatusDiv").show();
	     $("#userTrackingDiv").hide();
	 }else if(id == "locationReportTab"){
       $("#locationReportTab").addClass("selected");
	   $("#usersWorkingStatusDiv").hide();
	   $("#userTrackingTabDiv").hide();
	   $("#slowUserTrackingTabDiv").hide();
	   $("#locationWiseCadreInfoDiv").show();
	     $("#userTrackingDiv").hide();
		tabSelected= true; 
		<c:if test="${sessionScope.USER.accessType  == 'MP'}">	
		getAssemblyConstituencies(accessValue);
		</c:if>
	 }
	 else if(id == "slowUserTrackingTab"){
       $("#slowUserTrackingTab").addClass("selected");
	   $("#usersWorkingStatusDiv").hide();
	   $("#userTrackingTabDiv").hide();
	   $("#locationWiseCadreInfoDiv").hide();
	   $("#userTrackingDiv").hide();
	   $("#slowUserTrackingTabDiv").show();
	 }
	 else{
		$("#userTrackingTab").addClass("selected");
		$("#usersWorkingStatusDiv").hide();
		$("#slowUserTrackingTabDiv").hide();
		$("#locationWiseCadreInfoDiv").hide();
		$("#userTrackingDiv").show();
		$("#userTrackingTabDiv").show();
	 }
	 <c:if test="${sessionScope.USER.accessType  != 'STATE'}">	
		if(id != "locationReportTab"){
			getLocationNameByAccessValues(accessType,accessValue);
		}
	</c:if>
	  
  }
  function viewDetails(frmLocation,frmLocationId,toLoc,startDate,endDate){
   $('#dialogueLocationsCadTable').html('<img src="images/Loading-data.gif" style="margin-left: 350px;margin-top:78px;width:70px;height:60px;">');
       var constiId = $("#constituencyDispalyId").val();
	   if(constiId == null || $.trim(constiId).length == 0){
	      constiId =0;
	   }
	    $('#dialogueLocationsCadDiv').dialog(
			{
				width : 850,
				height:550,
				title : " "+toLoc+" Wise Registration Information"
			});
	   $.ajax({
          type:'GET',
          url: 'getDataForSubLocations.action',
		  data: {fromLocationId:frmLocationId,constituencyId:constiId,fromLocation:frmLocation,toLocation:toLoc,fromDateStr:startDate,toDateStr:endDate}
	   }).done(function(result){
	      if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		   location.reload(); 
    	   }
		     var str='';
	       if(result.length > 0){
		        str+='<input type="button"  style="margin-bottom:15px;margin-left: 295px;"  class="btn" onclick="generateExcel(\'locationWiseReportTabPoP\');" value="Click Here To Generate Excel"/>';
		        str+='<div><table class="table table-bordered table-striped table-hover" id="locationWiseReportTabPoP"><thead>';
				str+='<tr>';
				str+='<th>'+toLoc+'</th>';
			    str+='<th>2014 CADRE COUNT</th>';
			    str+='<th>2012 CADRE COUNT</th>';
				str+='</tr>';
				str+='</thead><tbody>';
				for(var i in result){
				  str+='<tr>';
				  str+='  <td>'+result[i].location+'</td>';
				    if(result[i].apCount != null){
				      str+='  <td>'+result[i].apCount+'</td>';
					}else{
					  str+='  <td></td>';
					}
					if(result[i].tgCount != null){
				      str+='  <td>'+result[i].tgCount+'</td>';
					}else{
					  str+='  <td></td>';
					}
				  str+='</tr>';
				}
				str+='</tbody></table></div>';
				 str+='<input type="button" style="margin-top:15px;margin-left: 295px;" class="btn" onclick="generateExcel(\'locationWiseReportTabPoP\');" value="Click Here To Generate Excel"/>';
		   }else{
		     str+='<div style="font-weight:bold;padding-left: 375px;padding-top: 30px;">No Data Available</div>';
		   }
		   $("#dialogueLocationsCadTable").html(str);
			   $("#locationWiseReportTabPoP").dataTable({
					aLengthMenu: [
						[25, 50, 100, 200, -1],
						[25, 50, 100, 200, "All"]
					],
					iDisplayLength: -1
				});
	   });
  }
  getDistricts();
  getConstituencies("ALL");
  getConstituencies("rural");
  
  function selectLocation(value){
	if(value==0){
		$("#statedisplaydivid").hide();
		$("#distdisplaydivid").hide();
		$("#constdisplaydivid").hide();
		$("#parlConstdisplaydivid").hide();
	}
	if(value==1){
		$("#statedisplaydivid").show();	
		$('#statesDivId').val('0');
		$("#distdisplaydivid").hide();
		$("#constdisplaydivid").hide();
		$("#parlConstdisplaydivid").hide();
	}
	if(value==2){
		$("#statedisplaydivid").show();	
		$('#statesDivId').val('0');
		
		$("#distdisplaydivid").hide();
		$("#constdisplaydivid").hide();
		$("#parlConstdisplaydivid").hide();
		getdistrictsUWS();
	}
	if(value==3){
		$("#statedisplaydivid").show();
		$('#statesDivId').val('0');
		
		$("#distdisplaydivid").hide();
		$("#constdisplaydivid").hide();
		$("#parlConstdisplaydivid").hide();
		getConstituenciesUWS();
	}
		if(value==4){
		$("#statedisplaydivid").show();
		$('#statesDivId').val('0');
		
		$("#distdisplaydivid").hide();
		$("#constdisplaydivid").hide();
		$("#parlConstdisplaydivid").hide();
		getparliamentConstituencies('displayParlConstbox');
	}
  }
  
  function selectLocation1(value){
  if(value==0){
		$("#statedisplaydivid1").hide();
		$("#distdisplaydivid1").hide();
		$("#constdisplaydivid1").hide();
	}
	if(value==1){
		$("#statedisplaydivid1").show();	
		$('#statesDivId1').val('0');
		$("#distdisplaydivid1").hide();
		$("#constdisplaydivid1").hide();
	}
	if(value==2){
		$("#statedisplaydivid1").show();	
		$('#statesDivId1').val('0');
		
		$("#distdisplaydivid1").hide();
		$("#constdisplaydivid1").hide();
		getdistrictsUWS1();
	}
	if(value==3){
		$("#statedisplaydivid1").show();
		$('#statesDivId1').val('0');
		
		$("#distdisplaydivid1").hide();
		$("#constdisplaydivid1").hide();
		getConstituenciesUWS1();
	}
	
  
  }
  function getdistrictsUWS(){
	var selState = $("#statesDivId").val();
	
	$("#displaydistbox").html("");
	
	
		var jsObj={
			stateid:selState
		}
		$.ajax({
			  type:'GET',
			  url: 'getDistrictsByStateWiseAction.action',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
			var str = "<option value='0'>All</option>";
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#displaydistbox").html(str);
			$("#distdisplaydivid").show();
	   });	
	
  }
  
  function getdistrictsUWS1(){
	var selState = $("#statesDivId1").val();
	
	$("#displaydistbox1").html("");
	
	
		var jsObj={
			stateid:selState
		}
		$.ajax({
			  type:'GET',
			  url: 'getDistrictsByStateWiseAction.action',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
			var str = "<option value='0'>All</option>";
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#displaydistbox1").html(str);
			$("#distdisplaydivid1").show();
	   });	
	
  }
 
	function getConstituenciesUWS(){
		var selState = $("#statesDivId").val();
		$("#displayconstbox").html("");
		
		var jObj ={
			stateid:selState,				  
			task:"getConstituenciesForUWS"             
		}	
		$.ajax({
			type : "POST",
			url : "getConstsAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			var str = "<option value='0'>All</option>";
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#displayconstbox").html(str);
			$("#constdisplaydivid").show();
		});
	}
	
	function getConstituenciesUWS1(){
		var selState = $("#statesDivId1").val();
		$("#displayconstbox1").html("");
		
		var jObj ={
			stateid:selState,				  
			task:"getConstituenciesForUWS"             
		}	
		$.ajax({
			type : "POST",
			url : "getConstsAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			var str = "<option value='0'>All</option>";
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#displayconstbox1").html(str);
			$("#constdisplaydivid1").show();
		});
	}
	
  function getConstituenciesForState(state){
  getDistrictsForStates(state);
   var jsObj=
   {				
				stateId:state,
				elmtId:"stateList",
                type:"default",
				task:"getConstituenciesForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForStateAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   $("#constituencyDispalyId").empty();
	   
     for(var i in result){
	   if(result[i].id == 0){
          $("#constituencyDispalyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#constituencyDispalyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
  
  function getConstituenciesForDistricts(district){
	var jsObj=
   {				
				districtId:district,
				elmtId:"districtList_d",
                type:"default",
				task:"getConstituenciesForDistricts"				
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForADistrictAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   $("#constituencyDispalyId").empty();
     for(var i in result){
	   if(result[i].id == 0){
          $("#constituencyDispalyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#constituencyDispalyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
  
  function getDistrictsForStates(state){
   
   var jsObj=
   {				
				stateId:state,
				elmtId:"districtList_d",
                type:"default",
				task:"getDistrictsForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   $("#districtsDispalyId").empty();
     for(var i in result){
	   if(result[i].id == 0){
          $("#districtsDispalyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#districtsDispalyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
  
 function selectLocation2(value){
  if(value==0){
		$("#statedisplaydivid2").hide();
		$("#distdisplaydivid2").hide();
		$("#constdisplaydivid2").hide();
		$("#parlConstdisplaydivid2").hide();
	}
	if(value==1){
		$("#statedisplaydivid2").show();	
		$('#statesDivId2').val('0');
		$("#distdisplaydivid2").hide();
		$("#constdisplaydivid2").hide();
		$("#parlConstdisplaydivid2").hide();
	}
	if(value==2){
		$("#statedisplaydivid2").show();	
		$('#statesDivId2').val('0');
		$("#parlConstdisplaydivid2").hide();
		$("#distdisplaydivid2").hide();
		$("#constdisplaydivid2").hide();
		getdistrictsUWS2();
	}
	if(value==3){
		$("#statedisplaydivid2").show();
		$('#statesDivId2').val('0');
		$("#parlConstdisplaydivid2").hide();
		$("#distdisplaydivid2").hide();
		$("#constdisplaydivid2").hide();
		getConstituenciesUWS2();
	}
	if(value==4){
		$("#statedisplaydivid2").show();
		$('#statesDivId2').val('0');
		
		$("#distdisplaydivid2").hide();
		$("#constdisplaydivid2").hide();
		$("#parlConstdisplaydivid2").hide();
		getparliamentConstituencies2('displayParlConstbox2');
	}
	}
  
  
  function getdistrictsUWS2(){
	var selState = $("#statesDivId2").val();
	
	$("#displaydistbox2").html("");
	
	
		var jsObj={
			stateid:selState
		}
		$.ajax({
			  type:'GET',
			  url: 'getDistrictsByStateWiseAction.action',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
			var str = "<option value='0'>All</option>";
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#displaydistbox2").html(str);
			$("#distdisplaydivid2").show();
	   });	
	
  }
 
	function getConstituenciesUWS2(){
		var selState = $("#statesDivId2").val();
		$("#displayconstbox2").html("");
		
		var jObj ={
			stateid:selState,				  
			task:"getConstituenciesForUWS"             
		}	
		$.ajax({
			type : "POST",
			url : "getConstsAction.action",
			data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			var str = "<option value='0'>All</option>";
		   for(var i in result){
				str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			$("#displayconstbox2").html(str);
			$("#constdisplaydivid2").show();
		});
	}
	
	function getparliamentConstituencies2(divId)
	{
		var stateTypeId = $('#statesDivId2').val();
		$("#parlConstdisplaydivid2").show();
		var jsObj = {
		searchType :"parliament",
		stateTypeId : stateTypeId,
		tesk:"locationWiseTransactionReport"
		}
	
	$('#'+divId+'').find('option').remove();
	$('#'+divId+'').append('<option value="0"> All </option>');

		$.ajax({
		type : "POST",
		url : "getParliamentsForStateAction.action",
		data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){

			var constiArr = new Array();
			if(result != null && result.length >0)
			{
				for(var i in result)
				{
					if(constiArr.indexOf(result[i].id) <0)
					{
						$('#'+divId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						constiArr.push(result[i].id);
					}
					
				}
			}
		});
	}
	
function getCandidateDataCollectionInfo2(){
    var allConstituencies = "";
	var ruralConstis = "";
    $("#userStatusDialogDIV2").html("");
    $("#errStatusDiv2").html("");
	
	var locationType=$( "#selLctnType2" ).val();
	
	var locationId;
	if(locationType==0){locationId=0;}
	if(locationType==1){locationId=getReqIds("statesDivId2");}
	if(locationType==2){locationId=getReqIds("displaydistbox2");}
	if(locationType==3){locationId=getReqIds("displayconstbox2");}
	if(locationType==4){locationId=getReqIds("displayParlConstbox2");}
	var slowUsersCheck = $("#slowUsersCheck").is(':checked');
	var startDate = $("#fromDate2").val();
	var endDate = $("#toDate2").val();
	
	if(startDate.trim().length >0 && endDate.trim().length >0)
		{
                 var arrr = startDate.split("-");
				    var fromDat=arrr[0];
					var frommonth=arrr[1];
					var fromyear=arrr[2];
			   var arr = endDate.split("-");
					var toDat=arr[0];
					var tomonth=arr[1];
					var toyear=arr[2];
					
					if(fromyear>toyear){
						$('#errStatusDiv2').html('<font style="color:red;">From Date should not greater than To Date </font>');
						  return;
					}
					 if(frommonth>tomonth){
						   if(fromyear == toyear){
							$('#errStatusDiv2').html('<font style="color:red;">From Date should not greater than To Date </font>');
						   return;
						}
						
					}
					
					if(fromDat>toDat){	
						if(frommonth == tomonth && fromyear == toyear){			
							$('#errStatusDiv2').html('<font style="color:red;">From Date should not greater than To Date </font>');
						   return;	
						   }
					}			
		}
		
	$("#getCandidateDataCollectionInfoId2").attr("disabled","disabled");
	$("#ajaxImgStyle2").show();
    $.ajax({
          type:'GET',
          url: 'getCadreDashBoardBasicInfo.action',
          data: {task:"slowPerformanceUsers",locationType:locationType,locationId:locationId,fromDate:$("#fromDate2").val(),toDate:$("#toDate2").val(),targetRecords:10}
       }).done(function(result){
			
	       var str='';
	       if(result.length > 0){
		        str+='<input type="button"  style="margin-bottom:15px;margin-left: 375px;"  class="btn" onclick="generateExcel(\'usersStatusReportTab2\');" value="Click Here To Generate Excel"/>';
		        str+='<div id="resultTableDiv2" style="overflow-x:scroll;"><table class="table table-bordered table-striped table-hover" id="usersStatusReportTab2"><thead>';
				str+='<tr>';
				str+='<th rowspan="2" >District</th>';
				str+='<th rowspan="2" >Parliament</th>';
				str+='<th rowspan="2" >Constituency</th>';		
				str+='<th rowspan="2">User</th>';
				str+='<th rowspan="2">Name</th>';
				str+='<th rowspan="2" >MobileNo</th>';
				if(result[0].infoList.length>1){
					for(var i in result[0].infoList){
					
						str+='<th colspan="5">'+result[0].infoList[i].date+'</th>';
					
					}
				}
				str+='</tr>';
				str+='<tr>';
				for(var i in result[0].infoList){
					
				  str+='<th>Start Time</th>';
				  str+='<th>End Time</th>';	
				  str+='<th>Actual Records To Be Collected</th>';				  
				  str+='<th>Records Collected</th>';
				  str+='<th>Average Time(in mins)</th>';
				}
				str+='</tr>';
				str+='</thead><tbody>';
				for(var i in result){
				if(slowUsersCheck == true){
				if(result[i].slowUser == true){
					str+='<tr>';
					
				   if(result[i].number != null){
				     str+='  <td>'+result[i].number+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				     if(result[i].percentStr != null){
				     str+='  <td>'+result[i].percentStr+'</td>';
				   }else{
				      str+='  <td></td>';
				   }	
				   if(result[i].memberShipNo != null){
				     str+='  <td>'+result[i].memberShipNo+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				 				   
				   str+='  <td>'+result[i].name+'</td>';
				   str+='  <td>'+result[i].uname+'</td>';
				 
				 
				  if(result[i].area != null){
				     str+='  <td>'+result[i].area+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				  for(var j in result[i].infoList){
					  
				    if(result[i].infoList[j].area != null){
				      str+='  <td>'+result[i].infoList[j].area+'</td>';
					}else{
					  str+='  <td></td>';
					}
					if(result[i].infoList[j].location != null){
				      str+='  <td>'+result[i].infoList[j].location+'</td>';
					}else{
					  str+='  <td></td>';
					}
					
					if(result[i].infoList[j].tgCount != null){
					if(result[i].infoList[j].tgCount > 0)
				      str+='  <td>'+result[i].infoList[j].tgCount+'</td>';
					else
					 str+='  <td>1</td>';
					}else{
					  str+='  <td></td>';
					}
					
					if(result[i].infoList[j].totalCount != null){
					if(result[i].infoList[j].totalCount < result[i].infoList[j].tgCount)
				      str+='  <td style="background-color:#D5432E">'+result[i].infoList[j].totalCount+'</td>';
					  else
					  str+='  <td style="background-color:#FFFFFF">'+result[i].infoList[j].totalCount+'</td>';
					}else{
					  str+='  <td></td>';
					}
					if(result[i].infoList[j].avgTime != null){
						if(result[i].infoList[j].avgTime >0)
							str+='  <td>'+result[i].infoList[j].avgTime+'</td>';
						else
							str+='  <td>10</td>';
					}else{
					  str+='  <td></td>';
					}
				  }
				  str+='</tr>';
				}	
				}
				else{
				
				  str+='<tr>';
				   if(result[i].number != null){
				     str+='  <td>'+result[i].number+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				     if(result[i].percentStr != null){
				     str+='  <td>'+result[i].percentStr+'</td>';
				   }else{
				      str+='  <td></td>';
				   }	
				   if(result[i].memberShipNo != null){
				     str+='  <td>'+result[i].memberShipNo+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				 				   
				   str+='  <td>'+result[i].name+'</td>';
				   str+='  <td>'+result[i].uname+'</td>';
				 
				 
				  if(result[i].area != null){
				     str+='  <td>'+result[i].area+'</td>';
				   }else{
				      str+='  <td></td>';
				   }
				  for(var j in result[i].infoList){
					  
				    if(result[i].infoList[j].area != null){
				      str+='  <td>'+result[i].infoList[j].area+'</td>';
					}else{
					  str+='  <td></td>';
					}
					if(result[i].infoList[j].location != null){
				      str+='  <td>'+result[i].infoList[j].location+'</td>';
					}else{
					  str+='  <td></td>';
					}
					
					if(result[i].infoList[j].tgCount != null){
					if(result[i].infoList[j].tgCount > 0)
				      str+='  <td>'+result[i].infoList[j].tgCount+'</td>';
					else
					 str+='  <td>1</td>';
					}else{
					  str+='  <td></td>';
					}
					
					if(result[i].infoList[j].totalCount != null){
					if(result[i].infoList[j].totalCount < result[i].infoList[j].tgCount)
				      str+='  <td style="background-color:#D5432E">'+result[i].infoList[j].totalCount+'</td>';
					  else
					  str+='  <td style="background-color:#FFFFFF">'+result[i].infoList[j].totalCount+'</td>';
					}else{
					  str+='  <td></td>';
					}
					if(result[i].infoList[j].avgTime != null){
						if(result[i].infoList[j].avgTime >0)
							str+='  <td>'+result[i].infoList[j].avgTime+'</td>';
						else
							str+='  <td>10</td>';
					}else{
					  str+='  <td></td>';
					}
				  }
				  str+='</tr>';
				}
				}
				str+='</tbody></table></div>';
			
		   }else{
		     str+='<div style="font-weight:bold;padding-left: 375px;padding-top: 30px;">No Data Available</div>';
		   }
		   $("#userStatusDialogDIV2").html(str);
		   $("#ajaxImgStyle2").hide();
		   $("#getCandidateDataCollectionInfoId2").removeAttr("disabled");
		   var selLoctnType2 = $("#selLctnType2").val();
		   
		   if(selLoctnType2 == 0 || selLoctnType2 == 1 || selLoctnType2 == 2){
				 $("#usersStatusReportTab2").dataTable({
					aLengthMenu: [
						[25, 50, 100, 200, -1],
						[25, 50, 100, 200, "All"]
					],
					iDisplayLength: 25
				});
		   }
		   
		   if(selLoctnType2 == 3){
				$("#usersStatusReportTab2").dataTable({
				"aaSortingFixed": [[2,'asc']],
					aLengthMenu: [
						[25, 50, 100, 200, -1],
						[25, 50, 100, 200, "All"]
					],
					iDisplayLength: 25
				});
		   }
		   
		   if(selLoctnType2 == 4){
				$("#usersStatusReportTab2").dataTable({
				"aaSortingFixed": [[1,'asc']],
					aLengthMenu: [
						[25, 50, 100, 200, -1],
						[25, 50, 100, 200, "All"]
					],
					iDisplayLength: 25
				});
		   }
		  
       });
   }
   
   function getUserTrackingReport(){
    var allConstituencies = "";
	var ruralConstis = "";
	var areaType ="assembly";
	var stateTypeId = $('#trackingStatesDispalyId').val();
	
    $("#userTrackingDetls").html("");
    $("#errStatusDiv1").html("");
		$('#userTrackingDetls').hide();
	var locationType1=$( "#trackingLocationsDispalyId" ).val();
	
	var locationId1;
	if(locationType1==0)
	{
		locationId1=0;
	}
	if(locationType1==2)
	{
		//locationId1=getReqIds("trackingStatesDispalyId");
		locationId1=0;
	}
	if(locationType1==3)
	{
		locationId1=getReqIds("trackingDistrictsDispalyId");
		areaType ="district";
	}
	if(locationType1==4)
	{
		locationId1=getReqIds("trackingConstituencyDispalyId");
		areaType ="assembly";
	}
	if(locationType1==5)
	{
		locationId1=getReqIds("trackingParlDispalyId");
		areaType ="parliament";
	}
	
	var startDate1 = $("#userTrackingFromDate").val();
	var endDate1 = $("#userTrackingToDate").val();
	
	if(startDate1.trim().length >0 && endDate1.trim().length >0)
		{
                 var arrr = startDate1.split("-");
				    var fromDat=arrr[0];
					var frommonth=arrr[1];
					var fromyear=arrr[2];
			   var arr = endDate1.split("-");
					var toDat=arr[0];
					var tomonth=arr[1];
					var toyear=arr[2];
					
					if(fromyear>toyear){
						$('#errStatusDiv1').html('<font style="color:red;">From Date should not greater than To Date </font>');
						  return;
					}
					 if(frommonth>tomonth){
						   if(fromyear == toyear){
							$('#errStatusDiv1').html('<font style="color:red;">From Date should not greater than To Date </font>');
						   return;
						}
						
					}
					
					if(fromDat>toDat){	
						if(frommonth == tomonth && fromyear == toyear){			
							$('#errStatusDiv1').html('<font style="color:red;">From Date should not greater than To Date </font>');
						   return;	
						   }
					}			
		}
		
	$("#getCandidateDataCollectionInfoId1").attr("disabled","disabled");
	$("#ajaxImgStyle1").show();
	
	var jsObj = {
			usersType:locationId1,
			areaType: areaType,
			stateTypeId:stateTypeId,
			fromdateStr:startDate1,
			todateStr : endDate1,
			 tesk:"daywiseTransactionReports"            
		   }	
			$.ajax({
				type : "POST",
				url : "getLocationswiseUsersListAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				$("#ajaxImgStyle1").hide();
				$('#userTrackingDetls').show();
				if(result !=null)
				{
					buildLocationWiseUsersReport(result);	
				}
				else
				{
					$('#userTrackingDetls').html(' <span class="span12" style="font-weight:bold;" align="center">No Data Available...</span>');
				}
			});
   }
    function buildLocationWiseUsersReport(result)
   {
    
	 var locationTypeFinder=$( "#trackingLocationsDispalyId" ).val();
		var location="";
		if(locationTypeFinder==2)
		 location="Constituency";
		else if(locationTypeFinder==3)
		 location="District";
		else if(locationTypeFinder==4)
		 location="Constituency";
		 else if(locationTypeFinder==5)
		 location="Parliament";

		var str ='';
		if(result.length >0)
		{
			str +='<table class="table table-bordered " id="daywiseReportsTab">';
			str +='<thead>';
			str +='<tr>';
			str +='<th > '+location+' </th>';
			if(locationTypeFinder != 3 && locationTypeFinder != 5)
			{
				str +='<th > Parliament </th>';
			}
			for(var i in result[0].surveyTransactionVOList)
			{
				str +='<th colspan="2">'+result[0].surveyTransactionVOList[i].surveyDate+'  </th>';
			}
			str +='</tr>';
			str +='<tr>';
			str +='<th> </th>';
			if(locationTypeFinder != 3 && locationTypeFinder != 5)
			{
				str +='<th> </th>';
			}
			for(var i in result[0].surveyTransactionVOList)
			{
				str +='<th> Started  </th>';
				str +='<th>  Yet to be Started  </th>';
				
			}
			str +='</tr>';
		}
	
		str +='</thead>';
		str +='<tbody>';
		for(var i in result)
		{
			str +='<tr>';
			str +='<td>'+result[i].name+'</td>';
			if(locationTypeFinder != 3 && locationTypeFinder != 5)
			{
				str +='<td> '+result[i].locationName+'</td>';
			}
			for(var j in result[i].surveyTransactionVOList)
				{
					if(result[i].surveyTransactionVOList[j].teamSize != 0)
					{
						str+='<td><a href="javascript:{getUserDetails('+result[i].surveyTransactionVOList[j].id+',\''+location+'\',\'submit\',\'Started\',\''+result[i].surveyTransactionVOList[j].surveyDate+'\',\''+result[i].name+'\',1)}">'+result[i].surveyTransactionVOList[j].teamSize+'</a></td>';
					}
					else
					{
						str+='<td>'+result[i].surveyTransactionVOList[j].teamSize+'</td>';
					}
					
					if(result[i].surveyTransactionVOList[j].idleTeamSize != 0)
					{
						str+='<td><a href="javascript:{getUserDetails('+result[i].surveyTransactionVOList[j].id+',\''+location+'\',\'notSubmit\',\'Yet to be Started \',\''+result[i].surveyTransactionVOList[j].surveyDate+'\',\''+result[i].name+'\',2)}">'+result[i].surveyTransactionVOList[j].idleTeamSize+'</a></td>';
					}
					else
					{
						str+='<td>'+result[i].surveyTransactionVOList[j].idleTeamSize+'</td>';
					}
					
				}
			str +='</tr>';
		}
		str +='</tbody>';
		str +='</table>';
		
		$('#userTrackingDetls').html(str);
		$('#daywiseReportsTab').dataTable({
			"iDisplayLength": 50,
			"aLengthMenu": [[50, 100, 200, -1], [50, 100, 200, "All"]]
		});
		$('#exportExclId2').show();
   }
   
  
	function getparliamentConstituencies(divId)
	{
		var stateTypeId = $('#statesDivId').val();
		$("#Pconstdisplaydivid").show();
		var jsObj = {
		searchType :"parliament",
		stateTypeId : stateTypeId,
		tesk:"locationWiseTransactionReport"
		}
	$("#parlConstdisplaydivid").show();
	$('#'+divId+'').find('option').remove();
	$('#'+divId+'').append('<option value="0"> All </option>');

		$.ajax({
		type : "POST",
		url : "getParliamentsForStateAction.action",
		data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){

			var constiArr = new Array();
			if(result != null && result.length >0)
			{
				for(var i in result)
				{
					if(constiArr.indexOf(result[i].id) <0)
					{
						$('#'+divId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						constiArr.push(result[i].id);
					}
					
				}
			}
		});
	}
	
	function trackingShowCorrespondingLocs(locationLvl)
	{
	  $("#trackingLocationsDispalyId").show();
	  $("#trackingStatesDispalyId").val(0);
	  
	   
	  if(locationLvl == 2)
	  {
		$("#trackingStatesDispalyMainDiv").hide();
		$("#trackingConstituencyDispalyMainDiv").hide();
		$("#trackingDistrictsDispalyMainDiv").hide();
		$("#trackingParlDispalyMainDiv").hide();
		$("#mandalDispalyMainDiv").hide();
	  }
	  if(locationLvl == 3)
	  {
		$("#trackingStatesDispalyMainDiv").show();
		$("#trackingDistrictsDispalyMainDiv").show();
		$("#trackingDistrictsDispalyId").val(0);
		$("#trackingConstituencyDispalyMainDiv").hide();
		$("#trackingParlDispalyMainDiv").hide();
		
	  }else if(locationLvl == 4)
	  {
		$("#trackingStatesDispalyMainDiv").show();
		$("#trackingDistrictsDispalyMainDiv").hide();
		$("#trackingDistrictsDispalyId").val(0);
		$("#trackingConstituencyDispalyMainDiv").show();
		$("#trackingParlDispalyMainDiv").hide();
	  }
	 else if(locationLvl == 5)
	  {
		$("#trackingStatesDispalyMainDiv").show();
		$("#trackingDistrictsDispalyMainDiv").hide();
		$("#trackingDistrictsDispalyId").val(0);
		$("#trackingConstituencyDispalyMainDiv").hide();
		$("#trackingParlDispalyMainDiv").show();		
	  }
	getLocationDetailsForState();
   }
	
	function getLocationDetailsForState()
	{
	  var locationLvl = $('#trackingLocationsDispalyId').val();
	  var divId = '';
	  var stateId =  $('#trackingStatesDispalyId').val();
	  var dataType = '';
	  var selectOption = '';
	
	  if(locationLvl == 3)
	  {
		divId = 'trackingDistrictsDispalyId';
		dataType = "district";
		selectOption = 'All ';
	  }
	  else if(locationLvl == 4)
	  {
	  	divId = 'trackingConstituencyDispalyId';
		dataType = "assembly";
		selectOption = 'All ';
	  }
	  else if(locationLvl == 5)
	  {
		divId = 'trackingParlDispalyId';	
		dataType = "parliament";
		selectOption = 'All ';
	  }
	  
	  if(locationLvl != 2)
	  {	 
		  var jsObj = {
				searchType :dataType,
				stateTypeId : stateId,
				tesk:"locationWiseTransactionReport"            
		   }
	  
			$.ajax({
				type : "POST",
				url : "getParliamentsForStateAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				$('#'+divId+'').find('option').remove();
				$('#'+divId+'').append('<option value="0"> '+selectOption+' </option>');
				var constiArr = new Array();
				if(result != null && result.length >0)
				{
					for(var i in result)
					{
						if(constiArr.indexOf(result[i].id) <0)
						{
							$('#'+divId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
							constiArr.push(result[i].id);
						}
					}
				}
			});
		}
	}
	
	function getUserDetails(locationId,location,searchType,type,dateString,locationName,typeId)
   {
   
				var jsObj = {
	             
			      location:location,
				  locationId: locationId,
		          type:searchType,
			      dateString:dateString,
			      }	
			$.ajax({
				type : "GET",
				url : "gettingUserDetailsByLocationAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
					$("#userDetailsIdForDialog,#userDetailsId").show();
				if(result!=null)
				 {
				 var str ='';
				  if((result.notStartVO !=null && result.notStartVO.length > 0) || (result.startVO !=null && result.startVO.length > 0))
				  {
				    str +='<table class="table table-bordered " id="getUsersDetailsId">';
			        str +='<thead>';
			        str +='<tr>';
					if(location == 'district' || location == 'parliament')
					{
						str +='<th> Constituency </th>';
						str +='<th> Parliament </th>';
					}
			        
			        str +='<th>UserName</th>';
				    str +='<th>Name</th>';
				    str+='<th>MobileNO</th>';
				   // str+='<th>TabNO</th>';
				    str +='</tr>';
				    str +='</thead>';
					str+="</tbody>";
					
					if(typeId == 2)
					{
						for(var i in result.notStartVO ) 
							{
							  str+='<tr>';
							  if(location == 'district'  || location == 'parliament')
								{
								str+='<td>'+result.notStartVO[i].location+'</td>';
								str+='<td>'+result.notStartVO[i].uniqueCode+'</td>';
								}
							  
							  str+='<td>'+result.notStartVO[i].userName+'</td>';
							  str+='<td>'+result.notStartVO[i].name+'</td>';
							  str+='<td>'+result.notStartVO[i].description+'</td>';
							 // str+='<td>'+result.notStartVO[i].pwd+'</td>';
							  str+='</tr>';
							}			
					}
					else if(typeId == 1)
					{
						 
						for(var i in result.startVO ) 
							{
							  str+='<tr>';
							  if(location == 'district'  || location == 'parliament')
							  {
							   str+='<td>'+result.startVO[i].location+'</td>';
							   str+='<td>'+result.startVO[i].uniqueCode+'</td>';
							  }
							  str+='<td>'+result.startVO[i].userName+'</td>';
							  str+='<td>'+result.startVO[i].name+'</td>';
							  str+='<td>'+result.startVO[i].description+'</td>';
							 // str+='<td>'+result.startVO[i].pwd+'</td>';
							   str+='</tr>';
							}			
					}
					
				    str+="</tbody>";
					str+="</table>";
				 }

				$("#userDetailsId").html(str);
			
				$('#getUsersDetailsId').dataTable({
			      "iDisplayLength": 10,
			      "aLengthMenu": [[10,20,50, 100, 200, -1], [10,20,50, 100, 200, "All"]]
		         });
				 
				 
				 $('#userDetailsIdForDialog').dialog(
				   {
					 width : 850,
					 height: 350,
					 title : "<span style='text-transform: capitalize;'>"+locationName+" "+location+" "+type+"</span> Users Details on "+ dateString+" "
				  });

   
   }
  });
  }
  
	
	function selectLocationByAccess(value){
		if(value==2){
			
			$("#statedisplaydivid").show();
			$("#distdisplaydivid").show();
			$("#constdisplaydivid").hide();
			$("#parlConstdisplaydivid").hide();
			$("#distdisplaydivid2").show();
			$("#constdisplaydivid2").hide();
			$("#parlConstdisplaydivid2").hide();
			getLocationNameByAccessValues("DISTRICT",accessValue);
		}
		if(value==3){
			$("#statedisplaydivid").show();		
			$("#distdisplaydivid").hide();
			$("#constdisplaydivid").show();
			$("#parlConstdisplaydivid").hide();
			$("#distdisplaydivid2").hide();
			$("#constdisplaydivid2").show();
			$("#parlConstdisplaydivid2").hide();
			if(accessType == "DISTRICT")
			getAssemblyParlConstituencies(accessValue,"Assembly");
		}
		if(value==4){
			$("#statedisplaydivid").show();
			$("#distdisplaydivid").hide();
			$("#constdisplaydivid").hide();
			$("#parlConstdisplaydivid").show();
			$("#distdisplaydivid2").hide();
			$("#constdisplaydivid2").hide();
			$("#parlConstdisplaydivid2").show();
			if(accessType == "DISTRICT")
			getAssemblyParlConstituencies(accessValue,"Parliament");
		}	
  }
	function trackingShowCorrespondingLocsByAccess(locationLvl)
	{
	  
	  if(locationLvl == 3)
	  {		  
	    $("#trackingStatesDispalyMainDiv").show();
		$("#trackingDistrictsDispalyMainDiv").show();
		$("#trackingConstituencyDispalyMainDiv").hide();
		$("#trackingParlDispalyMainDiv").hide();		
		getLocationNameByAccessValues("DISTRICT",accessValue);
	  }
	  
	 if(locationLvl == 4)
	  {	
	    $("#trackingStatesDispalyMainDiv").show();
		$("#trackingDistrictsDispalyMainDiv").hide();
		$("#trackingConstituencyDispalyMainDiv").show();
		$("#trackingParlDispalyMainDiv").hide();
		if(accessType == "DISTRICT")
			getAssemblyParlConstituencies(accessValue,"Assembly");
	  }
	 else if(locationLvl == 5)
	  {	
		 $("#trackingStatesDispalyMainDiv").show();
		$("#trackingDistrictsDispalyMainDiv").hide();
		$("#trackingConstituencyDispalyMainDiv").hide();
		$("#trackingParlDispalyMainDiv").show();
		if(accessType == "DISTRICT")
			getAssemblyParlConstituencies(accessValue,"Parliament");		
	  }
	
   }
	
	
	
	function getLocationNameByAccessValues(type,value){

	  var str='';
		var jsObj={
			usersType:type,
			areaType:value
		}
		$.ajax({
			  type:'GET',
			  url: 'getLocationNameByIdAndTypeAction.action',
			  data: {task:JSON.stringify(jsObj)}
			
	   }).done(function(result){
			if(type == 'DISTRICT'){
		  		str +='<option value="${sessionScope.USER.accessValue}">'+result+'</option>';
				$("#displaydistbox").html(str);
				$("#displaydistbox2").html(str);
				$("#trackingDistrictsDispalyId").html(str);	
				$("#districtsDispalyId").html(str);
			}
			if(type == 'MLA'){
		  		str +='<option value="${sessionScope.USER.accessValue}">'+result+'</option>';
				$("#displayconstbox").html(str);
				$("#displayconstbox2").html(str);
				$("#trackingConstituencyDispalyId").html(str);
				$("#constituencyDispalyId").html(str);
				var selConstituencyId =$("#constituencyDispalyId").val();
				if(tabSelected == true && $("#locationsDispalyId").val() == 5){					
					getTehsils(selConstituencyId);
				}
				if(tabSelected == true && $("#locationsDispalyId").val() == 9){			
					getBooths(selConstituencyId);
				}
				if(tabSelected == true && $("#locationsDispalyId").val() == 6){		
					getPanchayats(selConstituencyId);
				}
			}
			if(type == 'MP'){
		  		str +='<option value="${sessionScope.USER.accessValue}">'+result+'</option>';
				$("#displayParlConstbox").html(str);
				$("#displayParlConstbox2").html(str);
				$("#trackingParlDispalyId").html(str);
				$("#constituencyDispalyId").html(str);
			}
	   });	
	
	
	}
	
function getAssemblyParlConstituencies(districtId,type){

		var str='';
		var jsObj={
			mainUserLocationId:districtId,
			reportLevel:type
		}
		$.ajax({
			  type:'GET',
			  url: 'getSubUserAccessValueAction.action',
			  data: {task:JSON.stringify(jsObj)}
			
	   }).done(function(result){
				for(var i in result)
				{
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				if(type=="Parliament"){				
					$("#displayParlConstbox").html(str);
					$("#displayParlConstbox2").html(str);
					$("#trackingParlDispalyId").html(str);
				}	
				if(type=="Assembly"){
					$("#displayconstbox").html(str);
					$("#displayconstbox2").html(str);
					$("#trackingConstituencyDispalyId").html(str);
					$("#constituencyDispalyId").html(str);
					var selConstituencyId =$("#constituencyDispalyId").val();					
					if(tabSelected == true && $("#locationsDispalyId").val() == 5){							
					getTehsils(selConstituencyId);
					}
					if(tabSelected == true && $("#locationsDispalyId").val() == 9){	
					getBooths(selConstituencyId);
					}
					if(tabSelected == true && $("#locationsDispalyId").val() == 6){
					getPanchayats(selConstituencyId);
					}
				}		
	   });		
	}
	
	function showCorrespondingLocsByAccess(locationLvl,access){
      if(locationLvl == 3){
		   $("#statesDispalyMainDiv").show();
		   $("#districtsDispalyMainDiv").show();
		   $("#constituencyDispalyMainDiv").hide();
		   $("#mandalDispalyMainDiv").hide();
		   $("#panchayatDispalyMainDiv").hide();
		   $("#boothDispalyMainDiv").hide();
		   getLocationNameByAccessValues(access,accessValue);
	  }else if(locationLvl == 4){
	       $("#statesDispalyMainDiv").show();
		   $("#districtsDispalyMainDiv").hide();
		   $("#constituencyDispalyMainDiv").show();
		   $("#mandalDispalyMainDiv").hide();
		   $("#panchayatDispalyMainDiv").hide();
		   $("#boothDispalyMainDiv").hide();
			if(access == 'DISTRICT'){
				getAssemblyParlConstituencies(accessValue,"Assembly");
			}else if(access == 'MLA'){		
				getLocationNameByAccessValues(access,accessValue);
			}else if(access == 'MP'){		   
				getAssemblyConstituencies(accessValue);
		    }
	  }else if(locationLvl == 5){
		   $("#statesDispalyMainDiv").hide();
		   $("#districtsDispalyMainDiv").hide();
		   $("#constituencyDispalyMainDiv").show();
		   $("#mandalDispalyMainDiv").show();
		   $("#panchayatDispalyMainDiv").hide();
		   $("#boothDispalyMainDiv").hide();
		   if(access == 'DISTRICT'){
		        showCorrespondingConstituencyDivs("mandal");
				getAssemblyParlConstituencies(accessValue,"Assembly");	
		   }else if(access == 'MLA'){
				getLocationNameByAccessValues(access,accessValue);
		   }
		   else if(access == 'MP'){
				showCorrespondingConstituencyDivs("mandal");
				getAssemblyConstituencies(accessValue);		
		   }	  
	  }else if(locationLvl == 6){
		   $("#statesDispalyMainDiv").hide();
	       $("#districtsDispalyMainDiv").hide();
		   $("#constituencyDispalyMainDiv").show();
		   $("#mandalDispalyMainDiv").hide();
		   $("#panchayatDispalyMainDiv").show();
		   $("#boothDispalyMainDiv").hide();
		   if(access == 'DISTRICT'){
			   showCorrespondingConstituencyDivs("panchayat");
			   getAssemblyParlConstituencies(accessValue,"Assembly");
		   }else if(access == 'MLA'){
				getLocationNameByAccessValues(access,accessValue);
		   }
		   else if(access == 'MP'){
				showCorrespondingConstituencyDivs("panchayat");
				getAssemblyConstituencies(accessValue);	    
		   }		  
	  }else if(locationLvl == 9){
		   $("#statesDispalyMainDiv").hide();
		   $("#districtsDispalyMainDiv").hide();
		   $("#constituencyDispalyMainDiv").show();
		   $("#mandalDispalyMainDiv").hide();
		   $("#panchayatDispalyMainDiv").hide();
		   $("#boothDispalyMainDiv").show();
		   if(access == 'DISTRICT'){
			  getAssemblyParlConstituencies(accessValue,"Assembly");
			  showCorrespondingConstituencyDivs("booth");	
		   }else if(access == 'MLA'){
			  getLocationNameByAccessValues(access,accessValue);
		   }
		   else if(access == 'MP'){
			 showCorrespondingConstituencyDivs("booth");
		     getAssemblyConstituencies(accessValue);			 
		   }	  
	  }
  }
  
   function showCorrespondingConstituencyDivs(type){
     if(type == "mandal"){
	   $("#constituencySelectDIV").html('<select id="constituencyDispalyId" onchange="getTehsils(this.value);"></select>');
	 }else if(type == "booth"){
	   $("#constituencySelectDIV").html('<select id="constituencyDispalyId" onchange="getBooths(this.value);"></select>');
	 }else if(type == "panchayat"){
	   $("#constituencySelectDIV").html('<select id="constituencyDispalyId" onchange="getPanchayats(this.value);"></select>');
	 } 	
  }
 
 
 function getAssemblyConstituencies(constiId){
	var str;
	var jsObj = 
	       {
			  parliamentConstiId : constiId,
			  electionYear:2005,
			  task:"getAssemblyDetailsForParliamnt"             
	       }	
		    $.ajax({
				type : "POST",
				url : "getAssemblyDetailsForParliamntAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				$("#constituencyDispalyId option").remove();
				$('#constituencyDispalyId').append('<option value="0"> All</option>');
				if(result.selectOptionsList != null && result.selectOptionsList.length > 0)
				{						
					for(var i in result.selectOptionsList)
					{
						str +='<option value='+result.selectOptionsList[i].id+'>'+result.selectOptionsList[i].name+'</option>';
					}
					$("#constituencyDispalyId").html(str);
					var selConstituencyId =$("#constituencyDispalyId").val();
					
					if($("#locationsDispalyId").val() == 5){							
					getTehsils(selConstituencyId);
					}
					if( $("#locationsDispalyId").val() == 9){	
					getBooths(selConstituencyId);
					}
					if($("#locationsDispalyId").val() == 6){
					getPanchayats(selConstituencyId);
					}
				}
		  });
}
  
</script>
</body>
</html>