<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>DashBoard</title>
				 
<link rel="stylesheet" type="text/css" href="style.css"/> 
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<link type="text/css" href="styles/bootstrapInHome/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="styles/newhome_inner_styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/connectPeople/connectPeople.js"></script> 
</head>
<style>
	

	#menu ul.menu li.active a {
		background-position: left 0px;
	}
	#menu ul.menu li.active a span {
		color: #E8F3F7;
		background-position: right -27px;
	}
	.container{width:960px;}
	.p-m-bottom0px{padding-bottom:0px;margin-bottom:0px;}
	h4{font-size:13.5px;}
	h5{font-size:12px;}
.votersguide {display:inline-block;padding:0px;position:relative;}
.votersguide .btn{ bottom: 5px;right: 10px;position: absolute;}
.explore .p-5px{display:inline-block;padding:5px;border:0px;padding-bottom: 9px;}
.explore h4{ float: left;margin-left: 5px;width: 56%;margin-top:22px;}
.explore h6{clear:both;text-align:center;padding-top:18px;}
.voterspulse-home{margin-top:10px;}
.voterspulse-home h3{margin-top: 0px; margin-bottom: 0px;font-size:20px;}
.opinionpoll .breadcrumb{margin:0px -20px;display:inline-block;}
.follow-us{margin-left:170px;margin-top:10px;}
.selectBoxWidth{width:168px;}
.v-gallary .thumbnail{display:inline-block;float:left;}
.v-gallary ul li{border:1px solid #EAEAEA;}
#problemsShowDIV h6{color:#005580;margin-bottom:10px;}
#problemsShowDIV p{margin-top:10px;}
#problemsShowDIV .widget-block{display:inline-block;width:100%;}
#my-jqCarousel-3 .mask li:hover{background:#fff;}
#icon_leftsec{margin-right:5px;}
.star{margin-top:-3px;}
.voters-pulse-home{position:relative;}
.voters-pulse-home .btn{position:absolute;right:5px;bottom:5px;}
#my-jqCarousel-news{background:#fafafa;height:160px;}
.widget .widget-block {padding-bottom: 20px;}
.btn-group{margin-top: 10px;}
.problemSearchBtn{background-color: #5BB75B;background-image:linear-gradient(to bottom,#62C462, #51A351);
background-repeat: repeat-x;
border-color:rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
color: #FFFFFF;padding: 6px 10px; border-radius:5px; cursor: pointer; border: medium none; float: right; clear: both; margin-right: 12px;}

#searchAjaxImgSpan{float:right;margin-top:-33px;margin-right: -12px;display:none;}
#problemErrorMsgDiv{margin-top: 17px;font-size:13px;}
#my-jqCarousel-3 .pagination-links{margin-top:5px;}
#my-jqCarousel-3{margin-top:5px;}


#pollsWidgetBody {
border:none;
overflow: visible;
padding:0px;
}

.newLink{color:#000000;}
.newLink:hover{color:#000000;text-decoration:none;}
.widget-simple {
    margin: 6px 0 5px;
}
</style>
<div class="container m-top15">
<div class="row-fluid"><div class="span12 widget" style="padding: 0 20px 4px;"><h2 class="pagination-centered" style="border-bottom: 0px solid #C0C0C0;">DASHBOARD</h2></div></div>
<div class="row-fluid">

<!--------left div------->
<div class="span3">
<!-------Quick Links Block--------------->
<div  class="widget blue">
  <h2>Advanced DashBoard</h2>
   <b>Click here for</b> 
   <a class="btn btn-small btn-primary" href="userProfile.action">Advanced DashBoard</a>
</div>
<div class="widget blue quicklinks"><h2><span><i class="icon-random "id="icon_leftsec"></i></span>
Quick Links</h2>

<!---- View Election Results------->
<div class="widget-block" contentindex="4c">
<h5> View Election Results</h5>
<p>You can view your Assembly or Parliament past Election Results.</p>
<table>
<tbody>
<tr>
<td width="65%"><div id="electionDetailsErrorMsgDiv" style="display:none;"><font color="red"><b>*Select All Inputs</b></font></div></td>
</tr>
<tr>
<td>
<select id="electionTypeId" name="electionType"  cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;width: 168px;margin-bottom:5px;" autoComplete="off"
onchange="checkElectionType(this.options[this.selectedIndex].value)">
<option value="0">Select Type</option>
<option value="2">Assembly</option>
<option value="1">Parliament</option>
</select>
</td>
</tr>
<tr>
<td>
<select id="states" name="state_s" cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;width:168px;margin-bottom:5px;"onchange="getElectionYearsInHomePage('Assembly')">
</select>
</td>
</tr>
<tr>
<td>
<select id="electionYears" cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;width:168px;">
</select>
</td>
</tr>
</tbody>
</table>

<button class="btn btn-success pull-right" type="button" onclick="viewElectionResults()">Go</button>

</div>
<!---------------->

<!------View Your State---------->
<div class="widget-block" contentindex="0c" >
<h5>View Your State</h5>
<p>Select your state to view its Assembly, Parliament, Local Bodies election results.</p>
<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="statesList" listKey="id" listValue="name" onchange="setStateValue()"/>

<button class="btn btn-success clear-both pull-right" type="button" onclick="navigateToStatePage()">Go</button>

</div>
<!----View Your district------>
<div class="widget-block" contentindex="1c">
<div id="alertMessage_district" style="color:red;font-weight:bold;"></div>
<h5>View Your District</h5>
<p>Select your district to view its election results in district level.</p>
<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state" id="stateList_d" list="statesList" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtList_d')"></s:select>
<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district" id="districtList_d" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select District"/>
<br>
<a onclick="navigateToDistrictPage()" href="javascript:{}">
<button class="btn btn-success pull-right" type="button">Go</button>
</a>

</div>
<!--View your constituency-->
<div class="widget-block" contentindex="2c" >
<div id="alertMessage" style="color:red;font-weight:bold;"></div>
<h5>View Your Constituency</h5>
<p>Select Constituency Type<br>
<label class="radio">
<input type="radio" onclick="hideUnhideSelectBox(this.id, 'constituency')" id="assembly_radio" name="assembly_radio" checked="checked">
Assembly
</label>
<label class="radio">
<input type="radio" onclick="hideUnhideSelectBox(this.id,'constituency')" id="p_radio" name="assembly_radio" >
Parliament
</label>
</p>
<table style="display: block;" id="stateTable">
<tbody>
<tr>
<td>
<s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="state" id="stateList_c" list="statesList" listKey="id" listValue="name"  onchange="getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,'constituency')"/>
</td>
</tr>
</tbody>
</table>
<table id="constTable" style="display:block;">
<tr>
<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your Constituency" name="constituency" id="constituency" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select Constituency"/></td>
</tr>
</table>

<button class="btn btn-success pull-right" type="button" onclick="navigateToConstituencyPage()">Go</button>
</div>

<!--View your Locality-->

<div class="widget-block" contentindex="3c" >
<h5>View Your Locality</h5>
<div>
<table>								
<tbody>
<tr>
<td>
<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_l" list="statesListForLocalBodyElection" listKey="id" listValue="name" onchange="getLocalBodiesForState(this.options[this.selectedIndex].value)"/>
</td>									
</tr>

<tr>
<td><div id="localBodiesRadioDiv_label"></div></td>
</tr>

<tr>
<td>
<div id="localBodiesRadioDiv_data">
<input type="radio" value="5" onclick="getSelectElmtForLocalBody(this.value)" name="localBodyRadio"/>Muncipality
<input type="radio" value="6" onclick="getSelectElmtForLocalBody(this.value)" name="localBodyRadio"/>Corporation
<input type="radio" value="7" onclick="getSelectElmtForLocalBody(this.value)" name="localBodyRadio"/>Greater Municipal Corp
</div>
</td>									
</tr>

<tr>
<td style=""><div id="localBodiesSelectDiv_label"></div></td>
</tr>
<tr>
<td><div id="localBodiesSelectDiv_data"></div></td>									
</tr>
<tr>
<td><div id="localBodies_errorDiv"></div></td>									
</tr>
</tbody>
</table>
</div>

<button class="btn btn-success pull-right" type="button" onclick="navigateToLocalBodyPage()">Go</button>

</div>	

</div>
</div>
<div class="span9">
	<div class="row-fluid">
	   <div class="span12 widget-simple">Cross Voting</div>       				
	</div>
	<div class="row-fluid">
	   <div class="span6 widget-simple">Booth Wise Results</div>       
	   <div class="span6 widget-simple">Voters Details Analysis</div>
	</div>
	<div class="row-fluid">
	   <div class="span6 widget-simple">
			 Results Vs Caste
		</div>       
	   <div class="span6 widget-simple">Voters Search</div>					
	</div>
</div>
<script type="text/javascript">
constituencyId = '${sessionScope.USER.constituencyId}';
var loginMode = '${loginMode}';	  
var stateId = $('#stateList_d').val();
getDistrictsComboBoxForAState(stateId,"districtList_d");
getAllConstituenciesInStateByType(2,stateId,"constituency");


</script>
