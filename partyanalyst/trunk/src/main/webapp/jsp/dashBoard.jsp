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
	<script type="text/javascript" src="js/connectPeople/connectPeople.js"></script> 
	<script type="text/javascript" src="js/homePage/newHomePage.js"> </script>
	<script type="text/javascript" src="js/homePage/newHomePage_inner.js"> </script>
</head>
<style>
	
	table {
    background-color: transparent;
    border-spacing: 14px;
     border-collapse: separate;
    max-width: 564%;
    }
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

#boothResultsDiv .control-label{text-align:left;width:85px;}
#boothResultsDiv .controls{margin-left:100px;}
.boothResults{margin-top:5px;display:none;padding:25px;}
input[type="radio"]{margin-top:-1px;}
.boothResults select{width:160px;}

#boothWiseResult a{color:#000;}
#boothWiseResult a:hover{text-decoration:none;color:#0088CC;}
#boothWiseResult .breadcrumb{padding: 8px 5px;}
#boothWiseResult {margin-top:10px;}
.ui-autocomplete {
		max-height: 200px;
		overflow-y: auto;
		/* prevent horizontal scrollbar */
		overflow-x: hidden;
       max-width:200px;
		/* add padding to account for vertical scrollbar */
		padding-right: 20px;
	}
	/* IE 6 doesn't support max-height
	 * we use height instead, but this forces the menu to always be this tall
	 */
	* html .ui-autocomplete {
		height: 200px;
	}

	.ui-autocomplete-input { border-radius:3px;margin: 0; height:25px; position:relative; width:150px; -moz-border-radius:0px 0px 0px 0px;border:1px solid #CCCCCC;margin: 0 5px 8px;}
	
.thumbnail {
    border: 1px solid #DDDDDD;
    border-radius: 4px 4px 4px 4px;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.055);
    display: block;
    line-height: 20px;
    margin-left: 18px;
    padding: 4px;
    transition: all 0.2s ease-in-out 0s;
    width: 125px;
}
.ui-dialog .ui-dialog-content {
    background: none repeat scroll 0 0 transparent;
    border: 0 none;
    overflow: auto;
    padding: 0.5em 1em;
    position: relative;
}
.ui-dialog .ui-dialog-content {
    background: none repeat scroll 0 0 transparent;
    border: 0 none;
    overflow: auto;
    padding: 0.5em 1em;
    position: relative;
}
.ui-widget-content {
    background: url("images/ui-bg_flat_75_ffffff_40x100.png") repeat-x scroll 50% 50% #FFFFFF;
    border: 1px solid #AAAAAA;
    color: #222222;
}
/*----------------------------27-----*/
.dashboard-header:after {
    clear: both;
    content: "";
    display: block;
    height: 0;
}
.dashboard-header {
    background: linear-gradient(to bottom, #FCFCFC 0%, #ECECEC 100%) repeat scroll 0 0 transparent;
    border: 1px solid #C1C1C1;
    border-radius: 6px 6px 0 0;
    box-shadow: 0 1px 0 #FFFFFF;
	padding:2px;
	line-height:30px;
}
.m-top5{margin-top:5px;}
.m-top10{margin-top:10px;}
.widget{border-top:none;}
.page-header{ border-bottom: 1px solid #FFDC2D;margin: 0px 0px 10px;}
.page-header h4{margin:0px; font-size:15.5px ; }
lable{line-height:40px;}

.background {
    background-attachment: fixed;
    background-color: #E5E5E5;
    background-image: url("chrome://browser/skin/newtab/noise.png"), linear-gradient(rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.2));
    margin-left: 20px;
}
</style>
<div class="container">
	
<div class="row">

		<div class="dashboard-header m-top5">
			<img Src="./images/dashboard/DashBoard_Page_Header.jpg"></img>
		</div>

<div class="row-fluid m-top10" >
<!---------Left Section ----------->
<div class="span3">
<!-------Quick Links Block--------------->
<div class="profile-pic widget blue"  style="min-height: 139px; margin-bottom: 12px;">
<div style="padding-top: 12px;">
<div class="profileimg  thumbnail" >
<c:if test="${loginUserProfilePic == '' || loginUserProfilePic == null}">
<img src="pictures/profiles/human.jpg" id="userProfileImg" style="height: 100px; width: 132px;" >
</c:if>
<c:if test="${loginUserProfilePic !='' && loginUserProfilePic != null}">
<img src="pictures/profiles/${loginUserProfilePic}" id="userProfileImg" style="height: 100px; width: 132px; "">
</c:if>
</div>
<div  id="profileUserName" style="margin-top: 12px;" >
<b style="margin-left: 16px;">${loginUserName}</b> </div></div>
</div>		
 <div class="widget blue">
 <h2>Account Settings</h2>
 <table class="table table-hover" style="margin-bottom: -13px;border-spacing: 2px;"><thead></thead><tbody><tr><td><a href="freeUserRegistration.action"><span class="icon-pencil"></span>  Edit Profile</a></td></tr>  <tr><td><a class="changePwdLink" href="javascript:{}"><span class="icon-hand-right"></span>  Change Password</a></td></tr>   <tr><td><a class="editPictureLink" href="javascript:{}"><span class="icon-user"></span>  Edit Picture</a></td></tr>  </tbody></table>
 </div>
 <div id="userSettingsDialog"></div>

<div id="connectPeoplePopup" style="display:none;">
	<div id="allConnectedUsersDisplay_main"></div>
	</div>
<div class="btn btn-xlarge" style="height: 185px;">
    
    <h2 style="margin-top: 38px;">Advanced Dashboard</h2>
    <a href="userProfile.action" class="btn btn-small btn-success"> Click <i class="icon-hand-up icon-white"></i> To View More Features </a>

            </div>
<div class="widget quicklinks m-top15">
	<h2><span><i class="icon-random "id="icon_leftsec"></i></span>Quick Links</h2>

	<!---- View Election Results------->
	<div class="widget-block" contentindex="4c">
		<h5> View Election Results</h5>
			<p>You can view your Assembly or Parliament past Election Results.</p>
		<table style="margin-left: -34px; margin-top: -35px;">
			<tbody>
				<tr>
					<td width="65%">
						<div id="electionDetailsErrorMsgDiv" style="display:none;">
							<font color="red"><b>*Select All Inputs</b></font>
						</div>
					</td>
				</tr>
				
				<tr>
					<td>
						<select id="electionTypeId" name="electionType"  cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;width: 168px;margin-bottom:5px;" autoComplete="off"
						onchange="checkForElectionType(this.options[this.selectedIndex].value)">
						<option value="0">Select Type</option>
						<option value="2" selected>Assembly</option>
						<option value="1">Parliament</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td>
						<select id="states" name="state_s" cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;width:168px;margin-bottom:5px;"onchange="getElectionYearsInDashBoard('Assembly','')">
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
	</div><!------View Election Results END---------->

	<!------View Your State---------->
	<div class="widget-block" contentindex="0c" >
		<h5>View Your State</h5>
		<p>Select your state to view its Assembly, Parliament, Local Bodies election results.</p>
		<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="statesList" listKey="id" listValue="name" onchange="setStateValue()"/>
		<button class="btn btn-success clear-both pull-right" type="button" onclick="navigateToStatePage()">Go</button>
	</div><!------View Your State END---------->
	
    <!----View Your district------>
	<div class="widget-block" contentindex="1c">
		<div id="alertMessage_district" style="color:red;font-weight:bold;"></div>
		<h5>View Your District</h5>
		<p>Select your district to view its election results in district level.</p>
		<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state" id="stateList_d" list="statesList" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAStateForDashBoard(this.options[this.selectedIndex].value,'districtList_d','')"></s:select>
		<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district" id="districtList_d" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select District"/>
		<br>
		<a onclick="navigateToDistrictPage()" href="javascript:{}">
		<button class="btn btn-success pull-right" type="button">Go</button>
		</a>
	</div> <!----View Your district END------>
	
	<!--View your constituency-->
	<div class="widget-block" contentindex="2c" >
		<div id="alertMessage" style="color:red;font-weight:bold;"></div>
		<h5>View Your Constituency</h5>
		<p>Select Constituency Type<br>
			<label class="radio" style="width:66px;">
			<input type="radio" style="margin-top: 6px;" onclick="hideUnhideSelectBox(this.id, 'constituency')" id="assembly_radio" name="assembly_radio" checked="checked">
			Assembly
			</label>
			
			<label class="radio" style="margin-bottom: -30px;">
			<input type="radio" style="margin-top: 6px;" onclick="hideUnhideSelectBox(this.id,'constituency')" id="p_radio" name="assembly_radio" >
			Parliament
			</label>
		</p>
		<table style="display: block; margin-bottom: -27px;" id="stateTable">
			<tbody>
				<tr>
					<td>
						<s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="state" id="stateList_c" list="statesList" listKey="id" listValue="name"  onchange="getAllConstituenciesInStateByTypeInDashBoard(2,this.options[this.selectedIndex].value,'constituency','')"/>
					</td>
				</tr>

			</tbody>
		</table>
		
		<table id="constTable" style="display:block;">
			<tr>
				<td>
					<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your Constituency" name="constituency" id="constituency" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select Constituency"/>
				</td>
			</tr>
		</table>

		<button class="btn btn-success pull-right" type="button" onclick="navigateToConstituencyPage()" style="margin-top: 10px;">Go</button>
	</div><!--- View your constituency END --->

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
<div id="localBodiesRadioDiv_data" style="margin-bottom:-30px;margin-top: -30px;">
<input type="radio" value="5" onclick="getSelectElmtForLocalBody(this.value)" name="localBodyRadio"/>&nbsp;Muncipality</br>
<input type="radio" value="6" onclick="getSelectElmtForLocalBody(this.value)" name="localBodyRadio"/>&nbsp;Corporation</br>
<input type="radio" value="7" onclick="getSelectElmtForLocalBody(this.value)" name="localBodyRadio"/>&nbsp;Greater Municipal Corp</br>
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

</div>	<!---------locality END----------->

	<!--mandal start-->
	<c:if test="${politician == true && mandalsList != null}">
	<div class="widget-block" contentindex="3c" >
<h5>View Your Mandal</h5>
<div>
<table>								
<tbody>
<tr>
<td>
<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="mandal_s" id="mandalsList_l" list="mandalsList" listKey="id" listValue="name" />
</td>									
</tr>


</tbody>
</table>
</div>

<button class="btn btn-success pull-right" type="button" onclick="getMandalsForState()">Go</button>

</div>
</c:if>
<!---------mandal END----------->

</div>
</div><!---------Left Section END----------->

<!---------Right Section----------->
<div class="span9">
 <c:if test="${party}">
  <!-----State Level Analysis----->
  <div class="row-fluid span12 well well-small">
    <div class="page-header"> <h4><img src="./images/dashboard/ap_icon.png " ></img> State Level Analysis</h4></div>
		<div class="row-fluid">
			<div class="span6 widget">
				<h2> Party Performance Report</h2>
				<div class="media">
				
					<img class="media-object pull-left" alt="Party Performance Report" src="./images/dashboard/Party Performance Report.png"style="width: 64px; height: 64px;"></img>
				
				<div class="media-body">
					<!---<h4 class="media-heading"> Party Performance Report</h4>--->
					<p>To know your Party Performance in Previous Elections and Analyse where your Party
					lost/gain with low/high Margin</p>
					<div>
						<input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('partyperformance');" style="float:right;"></input>
					</div>
				</div> 
				</div> 
			</div> 
			<div class="span6 widget">
					<h2>Elections Comparison</h2>	
					<div class="media">
						<img class="media-object pull-left" alt="Party Performance Report" src="./images/dashboard/Elections Comparison copy.png"style="width: 64px; height: 64px;"></img>
						<div class="media-body">
							<p>Election Comparison Report gives a glance of compared election results for a Party participated in any two elections in detailed view</p>
							<input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('electioncomparison');" style="float:right;"></input>
						</div>
					</div>
			</div>	
							
		</div>
	
		<div class="row-fluid">
			<div class="span6 widget">
				<h2>Party Results Report</h2>
				<div class="media">
					<img class="media-object pull-left" alt="Party Performance Report" src="./images/dashboard/Party Results Report.png"style="width: 64px; height: 64px;"></img>
				<div class="media-body">
					<p>Party Results Report gives to you, your Party Results in all previous elections compared with other parties and their/ your alliances in state/district/constituency levels.
					</p>
					<input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('partyresultsReport');" style="float:right;"></input>
				</div>
				</div>    					
			</div>
			
			<div class="span6 widget">
				<h2>Election Results Analysis Report</h2>
				<div class="media">
					<img class="media-object pull-left" alt="Party Performance Report" src="./images/dashboard/Election Results Analysis Report.png"style="width: 64px; height: 64px;"></img>
					<div class="media-body">
						<p>Now you can analyse and view the reasons of analysed constituencies for winning/lossing in an Election
						</p>
						<input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('electionResReport');" style="float:right;"></input>
					</div>
				</div>
			</div>	
		</div>
		
		<div class="row-fluid">
			<div class="span6 widget">
				<h2> Elections Vs Demographics</h2>
				<div class="media">
					<img class="media-object pull-left" alt="Party Performance Report" src="./images/dashboard/Elections Vs Demographics.png"style="width: 64px; height: 64px;"></img>
					<div class="media-body">
						<p>Now you can analyse the parties performance based on Demographics like SC Population,
						ST Population, litarates,illitarates and etc.
						</p>
						<input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('elecdemog');" style="float:right;"></input>
					</div>
				</div>       
					
			</div>       
		   <div class="span6 widget">
			  <h2>Party Strengths & Weakness</h2>
				<div class="media">
					<img class="media-object pull-left" alt="Party Performance Report" src="./images/dashboard/Strengths & Weakness.png"style="width: 64px; height: 64px;"></img>
					<div class="media-body">
						<p>To Know where your party Strong or Weak to improve your Party Performance in coming Elections.

						</p>
						<input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('partystrweak');" style="float:right;"></input>
					</div>
				</div>						
			</div>
		</div>
	
		<div class="row-fluid">
			<div class="span6 widget">
				<h2> Live & Previous Results Comparison</h2>
				<div class="media">
					<img class="media-object pull-left" alt="Party Performance Report" src="./images/dashboard/District Wise Party Performance.png"style="width: 64px; height: 64px;"></img>
					<div class="media-body">
						<p>Now you can analyse the parties performance in newly formed Constituencies with old Constituencies.
						</p>
						<input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('distperform');" style="float:right;"></input>
					</div>
				</div>       						
			</div>
		</div>
   </div><!--------State Level Analysis END---->
	
 </c:if>
  <!---Constituency Level Analysis--->
  <div class="row-fluid">
	<div class="span12 well well-small">
		<div class="page-header">
			<h4><img src="./images/dashboard/Constituency Level Analysis copy.png" />  Constituency Level Analysis</h4>
		</div>
		
		
		
		<div class="row-fluid">
			<div class="span4 widget">
				<h2>Voters Analysis </h2>
				<fieldset>
					<lable>Select Constituency </lable>
					<s:select cssClass="selectstyle span12" theme="simple" id="constituencyId"  list="constituencyList" listKey="id" listValue="name" ></s:select>
				
					<input type="button" value="View" class="btn btn-small" onCLick="openVotersAnalysts();" style="float:right;"></input>
				</fieldset>
			</div>
			
		    <div class="span4 widget">
				<h2> Results Vs Caste</h2>
				<fieldset>
				<lable>Constituency </lable>
				<s:select cssClass="selectstyle" theme="simple" id="constituencyList"  list="constituencyList" listKey="id" listValue="name" ></s:select>
					
			   <input type="button" value="View" class="btn btn-small" onCLick="openCasteViseAnalysis();" style="float:right;"></input>
				</fieldset>
			</div>
			
			<div class="span4 widget">
			  <h2>Voters Search</h2>
				<div class="media">
					<img class="media-object pull-left m-top15" alt="Party Performance Report" src="./images/dashboard/Voters Search.png"style="width: 64px; height: 64px;"></img>
					<div class="media-body">
						<p>Voters Search</p>
						<input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('voterssearch');" style="float:right;"></input>
					</div>
				</div>  
			</div>
	    </div>
		<div class="row-fluid">
			<div class="span12 widget">
				<h2>Cross Voting Analysis</h2>
				<!----<fieldset>
				  <div class="row-fluid">
					<div class="span2">
						<label>Election year </label>					
						<s:select cssClass="selectstyle" theme="simple" id="electionYearField" name="electionYearField" list="electionYearList" listKey="id" listValue="name" onClick="getParlmentsList();" headerValue="Select Year" headerKey="0"></s:select> 
					</div>
					<div class="span2">
						<label>Parliment Constituency </label>					
						<s:select cssClass="selectstyle" theme="simple" id="pConstituencyList" name="pConstituencyList" list="parlConstis" listKey="id" listValue="name" onChange="getAssemblyConstituencies();"></s:select>
					</div>
					
				  </div>
				</fieldset>----->
				
				<div>
				   <table>
				     <tr>
				        <td><s:label theme="simple" for="electionYearField" value="Election year"/></td>
						<td><s:select cssClass="selectstyle" theme="simple" id="electionYearField" name="crossVotingYear" list="crossVotingVO.yearsList" listKey="id" listValue="name" onChange="getParlmentsList();"/></td>
				        <td><s:label theme="simple" for="pConstituencyList" value="Parliment Constituency"/></td>
						<td><s:select cssClass="selectstyle" theme="simple" id="pConstituencyList" name="crossVotingPConsti" list="crossVotingVO.parliamentLists" listKey="id" listValue="name" onChange="getAssemblyConstituencies();"/></td>
				     </tr>
				     <tr>
				        <td><s:label theme="simple" for="aConstituencyList" value="Assembly Constituency"/></td>
						 <td><s:select theme="simple" id="aConstituencyList" list="crossVotingVO.assemblyList" listKey="id"  name="crossVotingAConsti" listValue="name" onChange="getParties();"/></td>
					    <td><s:label theme="simple" for="partyList" value="Party"/></td>
						<td><s:select theme="simple" id="partyList" list="crossVotingVO.partiesList" listKey="id"  name="crossVotingParty" listValue="name"/></td>
				    </tr>
				   </table>
				</div>
				
				<div>
					<input type="button" value="View" class="btn btn-small" onClick="getCrossVotingReport();" style="float:right;"></input>
				</div>
			</div>       				
		</div>
	    <div class="row-fluid">
			<div class="span12 widget" id="boothResultsDiv" >
				<h2>Booth Wise Results</h2>
				<div id="boothWiseResult"></div>
				   <label class="checkbox" style="margin-top:10px;margin-left:5px;width:200px;" id="boothResultLable">
							<input type="checkbox" id="moreDetailsId" checked> Check for More Details
				   </label>
	   
				<div class="form-horizontal boothResults " name='boothSelection' style="display:block;">
					<div class="control-group" >
						<label class="control-label" for="firstName">Election Type</label>
						<div class="controls " style="">
							<input type="radio" name="electionType" value="2" checked="checked">  Assembly  </input>
							<input type="radio" name="electionType" value="1">  Parliament</input>
						</div>
						
						
					</div>
					<table  style="margin-bottom: -8px; margin-top: -20px;">
					<tr>
					<td><span>Select Year</span></td>
					<td><s:select theme="simple" list="electionYearsList" name="electionYear" listKey="id" listValue="name" headerKey="0" headerValue="Select Year" id="electionYearsId" onChange="constituencyOptions('')"/> </td>
					<td><span>Constituency</span></td>
					<td><select id="constiId" onChange="getPartiesForElections('')" name="constituencyName">
								<option value='0'>Select Constituency</option>
							</select></td>
					</tr>
					<tr>
					<td><span>Party</span></td>
					<td><select id="partyId" name="partyName">
								<option value='0'>Select Party</option>
							</select></td>
					</tr>
					</table>
					<div class="control-group" id="errorDiv" style="color:red;"></div>
					<button class="btn btn-mini btn-small" data-dismiss="modal" aria-hidden="true" onclick="submitRes()" style="float: right; margin-top: -55px; margin-right: 201px;">SUBMIT</button>
					<!--<div class="control-group ">
						<label class="control-label" for="electionYear">Select Year</label>
						<div class="controls">
							<s:select theme="simple" list="electionYearsList" name="electionYear" listKey="id" listValue="name" headerKey="0" headerValue="Select Year" id="electionYearsId" onChange="constituencyOptions()"/> 
						</div>
					</div>
					
					<div class="control-group ">
						<label class="control-label" for="constituency">Constituency</label>
						<div class="controls ">
							<select id="constiId" onChange="getPartiesForElections()" name="constituencyName">
								<option value='0'>Select Constituency</option>
							</select>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="partyId">Party</label>
						<div class="controls">
							<select id="partyId" name="partyName">
								<option value='0'>Select Party</option>
							</select>
						</div>
					</div>-->
					
				</div>
			</div>
			<!----<div class="span6 widget-simple">
				<h4>Voters Search</h4>
				<div><input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('voterssearch');" style="float:right;"></input></div>
			</div>	---->   
		</div>
    </div>
    
    
    
    	<!--	<div class="span6 widget-simple" id="">	
		<h4>Constituency Analysis</h4>
		<table>
	
		<tr>
		<td><span>Level</span></td>
		<td><select id="selectlevel" onChange="getSelectedlevel();">
		<option value="0">Select Type</option>
		<option value="1">Constituency</option>
		<option value="2">Mandal/Muncipality</option>
		<option value="3">Panchayat</option>
		<option value="4">Booth</option>
		</select>
		</tr>
		<tr style="display:none;" id="constituencyRow">
		<td><span>Constituency </span></td><td><s:select cssClass="selectstyle" theme="simple" id="selConstituency" name="selConstituency" list="constituencyList" listKey="id" listValue="name" ></s:select></td>
		</tr>
		<tr style="display:none;" id="mandalRow">
	  
		<td><span>Mandal/Muncipality </span></td><td><s:select cssClass="selectstyle" theme="simple" id="selMandal" name="selMandal" list="mandalList" listKey="id" listValue="name" ></s:select></td>
		</tr>
		<tr style="display:none;" id="panchayatRow">
		
		<td><span>Panchayat  </span></td><td><s:select cssClass="selectstyle" theme="simple" id="selPanchayat" name="selPanchayat" list="panchayatsList" listKey="id" listValue="name" ></s:select></td>
		</tr>
		
		</table>
		<div><input type="button" value="View" class="btn btn-success" onCLick="getVotersAnalysisbasedOnLevel();" style="float:right;"></input></div>
		</div>-->
   </div><!---Constituency Level Analysis END--->
   
   <!--Created By SASI Management Tools Block-->
   
  <div class="row-fluid">
	<div class="span12 well well-small">
     <div class="page-header"><h4><img src="./images/dashboard/Management Tools copy.png" />  Management Tools</h4></div>
		<div class="row-fluid">
			<div class="span6 widget">
				<h2> Add New Problem</h2>
				<div class="media">
					<img class="media-object pull-left" alt="Party Performance Report" src="./images/dashboard/Add New Problem.png"style="width: 64px; height: 64px;"></img>
					<div class="media-body">
						<p>Add a New Problem to solve the Problems easily and effectively</p>
						<input type="button" value="View" class="btn btn-small" onCLick="openAddNewProblemWindow()" style="float:right;"></input>
					</div>
				</div>       
			</div>       
			<div class="span6 widget">
			  <h2>All Problems</h2>
				<div class="media">
					<img class="media-object pull-left" alt="Party Performance Report" src="./images/dashboard/All Problems.png"style="width: 64px; height: 64px;"></img>
					<div class="media-body">
						<p>To View All Problems and analyze the Seviority and priority of the Problems</p>
						<input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('allproblems');" style="float:right;"></input></div>
				</div>					
			</div>					
		</div>
	
		<div class="row-fluid">
		   <div class="span6 widget">
				<h2>Problem Search And Report</h2>
				<div class="media">
					<img class="media-object pull-left" alt="Party Performance Report" src="./images/dashboard/Problem Search And Report.png"style="width: 64px; height: 64px;"></img>
					<div class="media-body">
					<p>To View the Detailed Report of the Problems and Search for a Particular Problem you can use Problem Search and Report</p>
						<input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('problemsearch');" style="float:right;"></input>
				    </div>
				</div>    					
			</div>
			<div class="span6 widget">
			  <h2>Cadre Mangement </h2>
			  <div class="media">
					<img class="media-object pull-left" alt="Party Performance Report" src="./images/dashboard/Cadre Mangement.png"style="width: 64px; height: 64px;"></img>
				<div class="media-body">
				<p>You can create your Cadre, then you can connect with your Cadre via SMS, emails</p>
					<input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('cadremgmt');" style="float:right;"></input>
				</div>
		   </div>	
		</div>
	</div>
		<div class="row-fluid">
			<div class="span6 widget">
				<h2> Constituency Management </h2>
				<div class="media">
					<img class="media-object pull-left" alt="Party Performance Report" src="./images/dashboard/Constituency Management.png"style="width: 64px; height: 64px;"></img>
						<div class="media-body">
						<p>To Add/View the Influencing People, local User Groups, and analyse their impact in your Constituency</p>
							<input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('constituencymgmt');" style="float:right;"></input>
						</div>
				</div> 
			</div> 
			
		   <div class="span6 widget">
			  <h2>Call Center</h2>
			  <div class="media">
					<img class="media-object pull-left" alt="Party Performance Report" src="./images/dashboard/Call Center.png"style="width: 64px; height: 64px;"></img>
				<div class="media-body">
					<p>Now you can know the Problems, Appointments given by your Call Center</p>
					<input type="button" value="View" class="btn btn-small" onCLick="openAllInNewWindow('callcenter');" style="float:right;"></input>
				</div>
			  </div>						
		 </div>
	
        </div>
     </div>
 
 <!--End of Management Tools Block-->
 
 
</div></div>

<script>
 $(document).ready(function(){
	 $('#electionYearsId').val($("#electionYearsId option:eq(1)").val());
	 constituencyOptions("default");

	 $('#moreDetailsId').change();
	//$("#constituencyId option[value=0]").remove();
	//$("#constituencyList option[value=0]").remove();
 	  /*$('#selConstituency').combobox();
 	  
	  $('#selConstituency').next().click(function(){
	    if($(this).val() == "Select Constituency" || $(this).val() == "Select")
		   $(this).val("");
	  });
	  
	  $('#selMandal').combobox();
	  $('#selMandal').next().click(function(){
	    if($(this).val() == "Select Constituency" || $(this).val() == "Select")
		   $(this).val("");
	  });
	  
	  $('#selPanchayat').combobox();
	  $('#selPanchayat').next().click(function(){
	    if($(this).val() == "Select Constituency" || $(this).val() == "Select")
		   $(this).val("");
	  });*/

});
</script>
<script type="text/javascript">
var defaultConstituencyId = '${constituencyId}';
userType = '${UserType}';
constituencyId = '${sessionScope.USER.constituencyId}';
userAccessType = '${sessionScope.USER.accessType}';
var loginMode = '${loginMode}';	  
var stateId = $('#stateList_d').val();
var districtId = '${districtId}';
var selType= "";
$("#constituencyId option[value=0]").remove();
$("#constituencyList option[value=0]").remove();
getDistrictsComboBoxForAStateForDashBoard(stateId,"districtList_d","default");
//getAllConstituenciesInStateByType(2,stateId,"constituency");
getAllConstituenciesInStateByTypeInDashBoard(2,stateId,"constituency","default");

          <c:if test="${boothAnalysisData != null}">
		   var elecYear = '${boothAnalysisData.name}';
		   var conId = '${boothAnalysisData.id}';
		   var elec = '${boothAnalysisData.value}';
		   var type = '${boothAnalysisData.type}';
		   $("#boothWiseResult").html('<a onclick="viewBoothResults()" style="cursor:pointer" class="btn" title="click here to view Booth Wise Results"> Click here for Booth Wise Results('+elecYear+' '+elec+' '+type+') </a>');
	     </c:if>
		 <c:if test="${boothAnalysisData == null}">
		   $("#moreDetailsId").trigger("click");
		   $("#boothResultLable").hide();
		   $('.boothResults').toggle();
		 </c:if> 
function viewBoothResults(){
  window.open("partyBoothResult2Action.action?partyName=872&electionYear="+elecYear+"&constituencyName="+conId+"");
}
//getPartiesForElections();

//Created By SASI

$("input:radio[name=electionType]").click(function() {

	$('#constiId').find('option').remove();
	$('#constiId').append('<option value="0">Select Constituency </option>');
    var value = $(this).val();
	var stateId=1;
	getElectionYears(stateId,value);
	//$('#electionYearsId').val(0);
	$('#partyId').val(0);
	$('#constiId').val(0);
});




function getElectionYears(stateId,electionType){

var jsObj =
		{  	
			stateId:stateId,
			electionType:electionType,
			task:'forElectionYears'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "optionsForBoothAction.action?"+rparam;
		callAjax(jsObj, url);
}
function constituencyOptions(type){
	var stateId=1;
	var electionType=$("input:radio[name=electionType]:checked").val();
	var electionId=$("#electionYearsId option:selected").val();
	var jsObj =
		{  	
			stateId:stateId,
			electionType:electionType,
			electionId:electionId,
            type:type,
			task:'forConstituencies'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "optionsForBoothAction.action?"+rparam;
		callAjax(jsObj, url);
}

function getPartiesForElections(type){
	var electionYear=$("#electionYearsId option:selected").text();
	var constiId=$("#constiId option:selected").val();
	var jsObj =
		{  	
			electionYear:electionYear,
			constituencyId:constiId,
            type:type,
			task:'forParty'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "optionsForBoothAction.action?"+rparam;
		callAjax(jsObj, url);
}
function openCasteViseAnalysis()
{
	var constituencyId = $('#constituencyList option:selected').val();
	if(constituencyId != null && constituencyId != "" && constituencyId > 0)
	{
	window.open("casteAndElectionResultsComparisonAction.action?constituencyId="+constituencyId);
	}
	
}
function openAllInNewWindow(type){
 var url = "";
  if(type == 'partyperformance')
    url ='partyPerformanceMain.action';
	
  else if(type == 'electioncomparison')
    url ='electionComparisonAction.action';
  
  else if(type == 'partyresultsReport')
    url ='partyResultsCriteriaAction.action';
  
  else if(type == 'electionResReport')
    url ='electionResultsAnalysisAction.action';
  
  else if(type == 'elecdemog')
    url ='censusReportAction.action';
  
  else if(type == 'partystrweak')
    url ='partyStrengthAction.action';
  
  else if(type == 'distperform')
    url ='electionLiveResultsAnalysisAction.action';
	
   else if(type == 'voterssearch')
    url ='votersSearchAction.action';

  else if(type == 'allproblems')
    url ='constituencyManagementAction.action?cmTask=PROBLEMS_MANAGEMENT';
  
  else if(type == 'problemsearch')
    url ='completeProblemDetailsSearchAction.action';
  
  else if(type == 'cadremgmt')
    url ='cadreManagementAction.action';
  
  else if(type == 'constituencymgmt')
    url ='initailConstituencyManagementAction.action';
	
   else if(type == 'callcenter')
    url ='callCenterAction.action';
	
	window.open(url);
}

 function openAddNewProblemWindow()
{	
	var browser1 = 
	window.open("addNewProblemAction.action","addNewProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
						 
		 browser1.focus();
}

function openVotersAnalysts()
{
	var constituencyId = $('#constituencyId option:selected').val();
	if(constituencyId != null && constituencyId != "" && constituencyId > 0)
	{
	window.open("votersAnalysisNewAction.action?constituencyId="+constituencyId);
	}
}

function getCrossVotingReport()
{
	
	var electionYear  = $('#electionYearField option:selected').val();
	
	var pConstituency = $('#pConstituencyList option:selected').val();
	
	var aConstituency = $('#aConstituencyList option:selected').val();
	
	var party         = $('#partyList option:selected').val();
	
	if(electionYear != null && electionYear != "" && electionYear > 0)
	{
		window.open("crossVotingReportInputAction.action?year="+electionYear+"&pConstituency="+pConstituency+"&aConstituency="+aConstituency+"&partyId="+party+"");
	}
}

function getParties()
{
	var id   = $('#aConstituencyList option:selected').val();
	var year = $('#electionYearField option:selected').val();
	var jsObj=
	{
			assemblyId : id,
		year           : year,
		task           : "getPariesForAssemply"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getConstituenciesAndParties.action?"+rparam;	

	callAjax(jsObj,url);
}
function buildParties(myResults)
{
	if(myResults != null)
	{
		var str = "";
		$("#partyList option").remove();
		str += '<option value="0">Select Party</option>';
		for(var i in myResults)
		{
			str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
		}
		$('#partyList').html(str);
	}
}

function getAssemblyConstituencies()
{
	var constituencyId = $('#pConstituencyList option:selected').val();
	var year = $('#electionYearField option:selected').val();
	var jsObj=
	{
			parliamentId : constituencyId,
		year           : year,
		task           : "getAssemblysForParliment"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getConstituenciesAndParties.action?"+rparam;	

	callAjax(jsObj,url);
}
function buildAssemblies(myResults)
{
	if(myResults != null)
	{
		var str = "";
		$("#aConstituencyList option").remove();
		str += '<option value="0">Select Location</option>';
		for(var i in myResults)
		{
			str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
		}
		$('#aConstituencyList').html(str);
	}
}
function getParlmentsList()
{
	var electionYear = $('#electionYearField option:selected').val();
	
	var jsObj=
	{
			year   : electionYear,
		task           : "getParlements"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getConstituenciesAndParties.action?"+rparam;	

	callAjax(jsObj,url);
}
function buildParlemnts(myResults)
{
	if(myResults != null)
	{
		var str = "";
		$("#pConstituencyList option").remove();
		str += '<option value="0">Select Location</option>';
		for(var i in myResults)
		{
			str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
		}
		$('#pConstituencyList').html(str);
	}
}


function buildElectionYears(myResult)
{
	$('#errorDiv').html("");
	
	if(myResult == null || myResult.length == 0)
		return;

	var electionYearsElmt = document.getElementById("electionYearsId");
	electionYearsElmt.options.length=0;

	var option = document.createElement('option');
	option.value = "0";
	option.text = "Select Year";
	electionYearsElmt.add(option);
	
	for(var i in myResult)
	{
		option = document.createElement('option');
		option.value = myResult[i].id;
		option.text = myResult[i].name;
		try
		{
			electionYearsElmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			electionYearsElmt.add(option); // IE only
		}
	}	

}
function buildPartiesSelectBox(myResult ,type){
	if(myResult == null || myResult.length == 0)
		return;
	
	$('#errorDiv').html("");
	var electionYearsElmt = document.getElementById("partyId");
	electionYearsElmt.options.length=0;

	var option = document.createElement('option');
	option.value = "0";
	option.text = "Select Party";
	electionYearsElmt.add(option);
	
	
	for(var i in myResult)
	{
		option = document.createElement('option');
		option.value = myResult[i].id;
		option.text = myResult[i].name;
		try
		{
			electionYearsElmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			electionYearsElmt.add(option); // IE only
		}
	}

	if(type == "default")
		$('#partyId').val($('#partyId option:eq(1)').val());

}

function buildConstituenciesDefault(myResult)
{
	if(myResult == null || myResult.length == 0)
		return;

	$('#constiId').find('option').remove();
    $('#constiId').append('<option value="">Select Constituency</option>');
	$.each(myResult,function(index , value){
       $('#constiId').append('<option value="'+value.id+'">'+value.name+'</option>');
	});

	$('#constiId').val($('#constiId option:eq(1)').val());
	getPartiesForElections('default');

	

}

function buildConstituencies(myResult){
	if(myResult == null || myResult.length == 0)
		return;
	
	$('#errorDiv').html("");
	
	var electionYearsElmt = document.getElementById("constiId");
	electionYearsElmt.options.length=0;

	var option = document.createElement('option');
	option.value = "0";
	option.text = "Select Constituency";
	electionYearsElmt.add(option);
	
	
	for(var i in myResult)
	{
		option = document.createElement('option');
		option.value = myResult[i].id;
		option.text = myResult[i].name;
		try
		{
			electionYearsElmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			electionYearsElmt.add(option); // IE only
		}
	}
	
}

function submitRes(){
	
	var electionYear=$("#electionYearsId option:selected").text();
	var val=$("#electionYearsId option:selected").val();
	if(val==0){$('#errorDiv').html("Please Select Election Year"); return ;}
	
	var constituencyName=$("#constiId option:selected").val();
	if(constituencyName==0){$('#errorDiv').html("Please Select Constituency;"); return;}
	
	var partyName=$("#partyId option:selected").val();
	if(partyName==0){$('#errorDiv').html("Please Select Party"); return;}
	
	$('#errorDiv').html("");
	var boothResultsWindow = window.open("partyBoothResult2Action.action?constituencyName="+constituencyName+"&electionYear="+electionYear+"&partyName="+partyName);
}

  $('#moreDetailsId').click(function(){
     $('.boothResults').toggle();
  });

function getSelectedlevel()
{
	var levelId = $('#selectlevel option:selected').val();
	
	if(levelId == 1)
	{
		selType  = "constituency";
		$('#constituencyRow').show();
		$('#mandalRow').hide();
		$('#panchayatRow').hide();
		$('#boothRow').hide();
	}
	else if(levelId == 2)
	{
		selType  = "mandal";
		$('#constituencyRow').hide();
		$('#mandalRow').show();
		$('#panchayatRow').hide();
		$('#boothRow').hide();
	}
	else if(levelId == 3)
	{
		selType  = "panchayat";
		$('#constituencyRow').hide();
		$('#mandalRow').hide();
		$('#panchayatRow').show();
		$('#boothRow').hide();
	}
	else if(levelId == 4)
	{
		selType  = "booth";
		$('#constituencyRow').hide();
		$('#mandalRow').hide();
		$('#panchayatRow').hide();
		$('#boothRow').show();
	}
}

function getVotersAnalysisbasedOnLevel()
{
	var id = "";
	if(selType == "constituency")
	{
		id = $('#selConstituency option:selected').val();
		if(id > 0)
		window.open("votersAnalysisNewAction.action?constituencyId="+id+"&type="+selType+"");
	}
	else if(selType == "mandal")
	{
		id = $('#selMandal option:selected').val();
		if(id > 0)
		window.open("votersAnalysisNewAction.action?mandalId="+id+"&type="+selType+"");
	}
	else if(selType == "panchayat")
	{
		id = $('#selPanchayat option:selected').val();
		if(id > 0)
		window.open("votersAnalysisNewAction.action?panchayatId="+id+"&type="+selType+"");
	}
	/* else if(selType == "booth")
	{
		id = $('#selBooth option:selected').val();
		if(id > 0)
		window.open("votersAnalysisNewAction.action?boothId="+id+"&type="+selType+"");
	} */
	
}

//change Password

$(".changePwdLink").live("click",function(){
	 $("#allConnectedUsersDisplay_main").children().remove();
	
	 $("#connectPeoplePopup").dialog({
            modal: true,
            title: "<b>Change Password</b>",
			width: 565,
            height: 300
           
        });
		var elmt = $("#allConnectedUsersDisplay_main");
		var div = $("<div class='changePwdDiv'></div>");

		div.append('<div id="password_window_errorMsg"></div>');
		div.append('<img src="images/icons/infoicon.png" />');
		div.append('<span>Fields marked with (<font color="red">*</font>) are mandatory</span>');
		div.append('<div> <span>Current Password</span><font color="red"> *</font> <input type="password" id="currentPwdId" name="currentPassword" style="height: 18px; width: 160px; margin-top: 10px; margin-left: 15px;"/></div>');
		div.append('<div> <span>New Password</span><font color="red"> *</font> <input type="password" id="newPwdId" name="newPassword" style="height: 18px; width: 160px; margin-top: 10px;margin-left: 38px;"/></div>');
		div.append('<div> <span>Confirm Password</span><font color="red"> *</font> <input type="password" id="confirmPwdId" name="confirmPassword" style="height: 18px; width: 160px; margin-top: 10px; margin-left: 13px;"/></div>');
        div.append('<div style="margin-left: auto; margin-right: auto; width: 550px;"><input class="btn-info btn-small" id="changePWDButton" type="button" value="Change Password"></input><input class="btn-info btn-small" id="cancelButtonID" type="button" value="Cancel" style="margin-left:10px;"></input></div>');

		elmt.append(div);
	});

   $("#cancelButtonID").live("click",function(){
	 $("#connectPeoplePopup").dialog("destroy");
   });

   $("#changePWDButton").live("click",function(){

		var cpwd = $.trim($("#currentPwdId").val());
		var npwd = $.trim($("#newPwdId").val());
		var cfmpwd = $.trim($("#confirmPwdId").val());
		var errorDiv = $("#password_window_errorMsg");
		errorDiv.html('');		
		if(cpwd.length > 0 &&cpwd.length < 6)
		{
		  errorDiv.html("<font color='red'>Current Password Minimum Of 6 Characters.</font>");
	      return;
		}
	if(npwd.length > 0 &&npwd.length < 6)
	{
	  errorDiv.html("<font color='red'>New Password Minimum Of 6 Characters.</font>");
	  return;
	}
	if(cpwd=="")
	{
     errorDiv.html("<font color='red'>Please enter password.</font>");	
	 return;
	}
     if(npwd.length > 0 && cfmpwd.length > 0 && npwd != cfmpwd)
	{
 	  errorDiv.html("<font color='red'>Passwords do not match.</font>");
       return
	}
	if(cpwd=='')
	{
     errorDiv.html("<font color='red'>Please enter current password.</font>");
	 return;
	}
	if(npwd=='')
	{
      errorDiv.html("<font color='red'>Please enter new password.</font>");
	   return;
	}
	if(cfmpwd=='')
	{
	   errorDiv.html("<font color='red'>Please enter confirm password.</font>");
	   return;
	}
	if(cpwd == npwd)
	{
	  errorDiv.html("<font color='green'>Your new password is same as existing one.</font>");
	  setTimeout("closewdw()",3000);
	  return;
	}
	if(cpwd!='')
	{
	  errorDiv.html("Sending Your Request.Please wait</font>");
	  errorDiv.html('<img src="images/icons/partypositions.gif" style="padding-left:10px;" width="18" height="11">');

      var jsObj={
      crntPassword:cpwd,
      newPassword:npwd,
	  task:"changePassword"	 
	 };
     var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "changeUserPasswordAction.action?"+rparam;						
	callAjax(jsObj,url);
	}
  });
//edit picture

$(".editPictureLink").live("click",function(){
	
$("#allConnectedUsersDisplay_main").children().remove();
	
	$("#connectPeoplePopup").dialog({
		modal: true,
		title: "<b>Change Image</b>",
		width: 583,
		height: "auto"
           
	});
	
	
	$(".ui-dialog-titlebar").hide();

	var elmt = $("#allConnectedUsersDisplay_main");
	var div =$("<div></div>");
	var str = '';
	str += '<div id="uploadPic_window_head">Upload Your Picture</div>';
	str += '<div id="uploadPic_window_body">';
	str += '<form name="uploadForm" action="uploadPicAction.action" enctype="multipart/form-data" method="post" id="uploadPicForm">';
	str += '<table width="100%">';
	str += '<tr>';
	str += '<th valign="top">';
	str += '	<table width="100%" class="uploadPic_string_table">';
	str += '	<tr>';
	str += '	<td width="7px"><img width="7" height="5" src="images/icons/districtPage/listIcon.png"></td>';
	str += '	<td style="font-size:11px;float:left;">Select a image from your computer.</td>';
	str += '	</tr>';

	str += '	<tr>';
	str += '	<td width="7px"><img width="7" height="5" src="images/icons/districtPage/listIcon.png"></td>';
	str += '	<td style="font-size:11px;float:left;"">Image size should be less than 2MB.</td>';
	str += '	</tr>';

	str += '	<tr>';
	str += '	<td width="7px"><img width="7" height="5" src="images/icons/districtPage/listIcon.png"></td>';
	str += '	<td style="font-size:11px;float:left;"">Image should be .jpg or.png or .gif formats only.</td>';
	str += '	</tr>';

	str += '	</table>';	
	str += '</th>';
	str += '<td rowspan="3">';
	str += '	<div style="width:100px;height:100px;overflow:hidden;"><img width="90" height="100" id="Imgpreview" class="jcrop-preview" src="images/icons/indexPage/human.jpg"></div>';
	str += '</td>';
	str += '</tr>';

	str += '<tr>';
	str += '<td>';
	str += '<input type="file" size="45" id="photoUploadElmt" name="upload" onchange="previewImg()" style="width:430px;"/>';
	str += '</td>';
	str += '</tr>';
	
	str += ' <input type="hidden" size="4" value=""  id="xcoardinate"  name="xcoardinate"   value="0" />'; 
     str += ' <input type="hidden" size="4"  value="" id="ycoardinate"  name="ycoardinate"   value="0" />';  
     str += ' <input type="hidden" size="4" value=""  id="width"        name="width" />';  
     str += ' <input type="hidden" size="4"  value="" id="height"       name="height" />'; 
	str += '</table>';	
	str += '</form>';
	str += '</div>';
	str += '<div id="uploadPic_window_footer" class="yui-skin-sam">';
	str += '<table width="100%">';
	str += '<tr>';
	str += '<th width="60%" valign="top"><div id="uploadPic_window_status"></div></th>';
	str += '<td width="40%" valign="top"><input class="btn-info btn-small" type="button" value="Upload" id="uploadPicButton"/>';
	str += '<input class="btn-info btn-small" type="button" value="Cancel" id="cancelPicButton"/>';	
	str += '</td>';
	str += '</tr>';
	str += '</table>';	
	str += '</div>';
	str += '<div style="width:300px;" id="Imgpreview2"><img style="width:300px;height:300px;" width="300" height="300" id="Imgpreview1"  src=""></div>';
	div.append(str);
	elmt.append(div);
	
	oPushButton1 = new YAHOO.widget.Button("uploadPicButton");  
	oPushButton2 = new YAHOO.widget.Button("cancelPicButton");
	
	oPushButton1.on("click",function(){
	   
		var uploadPhotoId = document.getElementById("photoUploadElmt").value;
		var str = '<font color="red">';
		if(uploadPhotoId.length == 0)
	     {   
		     str += ' Please Select a image .<br>';
		     document.getElementById("uploadPic_window_status").innerHTML = str;
	     }
		 else{
		 var photoStatusElmt = document.getElementById("uploadPic_window_status");
		 photoStatusElmt.innerHTML = 'Uploading Image. Please Wait... &nbsp<img width="16" height="11" src="images/icons/partypositions.gif"/>'
		
		 getUploadpic();
		}
	});

	oPushButton2.on("click",function(){
		$("#uploadPic_window").dialog("destroy");
	});

});

$("#cancelPicButton").live("click",function(){
	$("#connectPeoplePopup").dialog("destroy");
});

$("#uploadPicButton").live("click",function(){
	var uploadPhotoId = $.trim($("#photoUploadElmt").val());
		var str = '<font color="red">';
		if(uploadPhotoId.length == 0)
	     {   
	       $("#uploadPic_window_status").html('Please Select a image .<br>');
		   return;
	     }
		 else
		 {
		   $("#uploadPic_window_status").html('Uploading Image. Please Wait... &nbsp<img width="16" height="11" src="images/icons/partypositions.gif"/>');
			 getUploadpic();
		}
});

$("#uploadCoverPicButton").live("click",function(){
	var uploadPhotoId = $.trim($("#photoUploadElmt").val());
		var str = '<font color="red">';
		if(uploadPhotoId.length == 0)
	     {   
	       $("#uploadPic_window_status").html('Please Select a image .<br>');
		   return;
	     }
		 else
		 {
		   $("#uploadPic_window_status").html('Uploading Image. Please Wait... &nbsp<img width="16" height="11" src="images/icons/partypositions.gif"/>');
			 getUploadCoverpic();
		}
});
function showChangePwdStatus(results)
{
	$("#password_window_errorMsg").html('');
	var result = $("#password_window_errorMsg");
	if(results==121)
	{
	  result.html('<div style="color:green">Password changed successfully, Window Closing...</div>');
      setTimeout("closewdw()",3000);
	  return;
	}	
    else if(results==0)
	{
	 result.html('<div style="color:red"> Invalid Current Password</div>');	
	 return;
	}
}

function createCropImage(imgpreview,imgtarget,event)
{     
      //alert(event.target.result); 
	  
   var a=document.getElementById("Imgpreview").src = event.target.result;

   if(jcrop_api){
   
   	
	
	
    jcrop_api.destroy();
	 $('#Imgpreview1').removeAttr('style'); 
	document.getElementById("Imgpreview1").src = event.target.result;
  
  

}
else{
 document.getElementById("Imgpreview1").src = event.target.result;
  $('#xcoardinate').val("0");
  $('#ycoardinate').val("0");
   $('#width').val("300");
   $('#height').val("300");
   
}
}
function previewImg()
{
	var photoElmt = document.getElementById("photoUploadElmt");
	var photoStatusElmt = $("#uploadPic_window_status");
	var fileLimit = 1048576; //1024*1024 = 1048576 bytes (2MB photo limit)
	
	var file = photoElmt.files[0];
	
	var fileType = file.type;
	var fileImgType = fileType.substring(fileType.indexOf('/')+1,fileType.length);
	
	if(fileImgType == "jpeg" || fileImgType == "png" || fileImgType == "gif")
	{
		var fileSize = file.fileSize/fileLimit;
		if(fileSize > 2)
		{
			photoStatusElmt.innerHTML = '<span class="errorStatusMsg">The Image size should be < 2MB.</span>';
		}
		else
		{
			var reader = new FileReader();
           //  init the reader event handlers
			reader.onloadend = handleReaderLoadEnd;
			 
			// begin the read operation
			reader.readAsDataURL(file);
			
			photoStatusElmt.innerHTML = '';
			var previewElmt = document.getElementById("Imgpreview");
			//previewElmt.src = file.getAsDataURL();
			uploadPicStatus = true;
		}
	}
	else
	{
		photoStatusElmt.innerHTML = '<span class="errorStatusMsg">The Image is not of the type specified.</span>';
	}

	
}
function callAjax(jsObj,url){
		 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task =="forElectionYears")
								{
									buildElectionYears(myResults);
								}
								if(jsObj.task =="forParty")
								{
									buildPartiesSelectBox(myResults,jsObj.type);
								}
								if(jsObj.task=="forConstituencies"){

									if(jsObj.type == "default")
										buildConstituenciesDefault(myResults);
									else
 									  buildConstituencies(myResults);
								}
								if(jsObj.task == "getPariesForAssemply")
								{
									buildParties(myResults);
								}
								else if(jsObj.task == "getAssemblysForParliment")
								{
									buildAssemblies(myResults);
								}	
								else if(jsObj.task == "getParlements")
								{
									buildParlemnts(myResults);
								}	
								else if(jsObj.task =="changePassword")
								{
									showChangePwdStatus(myResults);
								}
								else if(jsObj.task == "getStates")
								{
									$('#states').find('option').remove();
										$('#states').append('<option value="0">Select State</option>');
									 $.each(myResults,function(index,value){
										$('#states').append('<option value="'+value.id+'">'+value.name+'</option>');

									});
									if(jsObj.type == "default"){
									 $('#states').val(1);
									 getElectionYearsInDashBoard('Assembly','default');
									}
								}else if(jsObj.task == "getElectionYearsForAState")
								{
									$('#electionYears').find('option').remove();

									 $.each(myResults,function(index,value){
										$('#electionYears').append('<option value="'+value.id+'">'+value.name+'</option>');

									});
									if(jsObj.type == "default"){
									$("#electionYears").val($("#electionYears option:eq(1)").val());
									}
								}
								else if(jsObj.task == "getConstituencies")
							    {
								//clearOptionsListForSelectElmtId(jsObj.elmtId);
								//createOptionsForSelectElmtId(jsObj.elmtId,myResults);

								$('#constituency').find('option').remove();

								 $.each(myResults,function(index,value){
										$('#constituency').append('<option value="'+value.id+'">'+value.name+'</option>');

									});

                                if(jsObj.type == "default")
								$('#constituency').val(defaultConstituencyId);
								hideBusyImgWithId(jsObj.elmtId);
							   }else if(jsObj.task == "findDistrictsForAState")
								{	

								   $('#districtList_d').find('option').remove();

								 $.each(myResults,function(index,value){
										$('#districtList_d').append('<option value="'+value.id+'">'+value.name+'</option>');

									});

                                if(jsObj.type == "default"){
								 $('#districtList_d').val(districtId);
								}
								

							    }
								}catch (e) {
							     
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                		//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}

function checkForElectionType(electionTypeId)
{
    if(electionTypeId == 1)
	{
	  getStatesInDashBoard();
	  $('#states').hide();
	  getElectionYearsInDashBoard('Parliament','');
	}

    if(electionTypeId == 2)
	{
	  $('#states').show();
      getStatesInDashBoard();

	}
}
function getStatesInDashBoard()
{

	var electionType = $('#electionTypeId').val();

	var jsObj=
		{						
				
			electionType:electionType,
            type:"change",
			task:"getStates"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStatesForHomepage.action?"+rparam;
	callAjax(jsObj,url);

}
getStatesInDashBoardByDefault();
function getStatesInDashBoardByDefault()
{


	var jsObj=
		{					
				
			electionType:2,
            type:"default",
			task:"getStates"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStatesForHomepage.action?"+rparam;
	callAjax(jsObj,url);

}


function getElectionYearsInDashBoard(electionType , type)
{

	var stateEle = document.getElementById("states");
	if(electionType == 'Assembly')
	var stateId = stateEle.options[stateEle.selectedIndex].value;
	if(electionType == 'Parliament')
		stateId = 1;
	document.getElementById("electionYears").length = 0;
	
	if(electionType == null || electionType == 'Select Type' || stateId == 0)
		return;
	
	var jObj = {
			stateId : stateId,
		electionType: electionType,
				type:type,	 
				task: 'getElectionYearsForAState'
				};

	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "electionYearsForstateAndElectionTypeAction.action?"+rparam;
	callAjax(jObj,url);
}


function getAllConstituenciesInStateByTypeInDashBoard(electionType, stateId, element,type)
{
	clearOptionsListForSelectElmtId(element);
	showBusyImgWithId(element);
	
	var jsObj=
	{				
			electionTypeId: electionType,
			stateId: stateId,
			task: "getConstituencies",
            type:type,  
			elmtId: element 	
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getAllConstituenciesInState.action?"+rparam;						
callAjax(jsObj,url);
}


function getDistrictsComboBoxForAStateForDashBoard(value,elmtId,type)
{
	var jsObj=
		{				
				stateId:value,
				elmtId:elmtId,
                type:type,
				task:"findDistrictsForAState"				
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getDistrictsForAStateAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getMandalsForState(){
	var mandalsSelectEl = document.getElementById("mandalsList_l");
	var alertEl = document.getElementById("alertMessage");
	var mandalsSelectElVal = mandalsSelectEl.options[mandalsSelectEl.selectedIndex].value;
	alertEl.innerHTML = '';
	if(mandalsSelectElVal == 0)
	{
		alertEl.innerHTML = 'Please Select Your Mandal';
		return;
	}
	window.open("mandalPageElectionInfoAction.action?MANDAL_ID="+mandalsSelectElVal+"");
}
console.log('${politician}');



</script>


</body>
</html>