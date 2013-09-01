<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message Center</title>
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
	<script type="text/javascript" src="js/messageCenter.js"></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script> <script type="text/javascript" src="js/blockui.js"></script>

  <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.24/themes/base/jquery-ui.css">
  <style>#slider { margin: 10px; }	</style>
  <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
  <script src="http://code.jquery.com/ui/1.8.24/jquery-ui.js"></script>


<style>

.radioSpecial > input[type=radio]{
		display:none;
	}

.radioSpecial > input[type=radio] + label{
	    font-family:calibri;
		display:inline-block;
		margin:-2px;
		padding: 4px 12px;
		margin-bottom: 0;
		font-size: 14px;
		line-height: 20px;
		color: #333;
		text-align: center;
		text-shadow: 0 1px 1px rgba(255,255,255,0.75);
		vertical-align: middle;
		cursor: pointer;
		background-color: #ffffff;
		background-image: -moz-linear-gradient(top,#fff,#e6e6e6);
		background-image: -webkit-gradient(linear,0 0,0 100%,from(#fff),to(#e6e6e6));
		background-image: -webkit-linear-gradient(top,#fff,#e6e6e6);
		background-image: -o-linear-gradient(top,#fff,#e6e6e6);
		background-image: linear-gradient(to bottom,#fff,#e6e6e6);
		background-repeat: repeat-x;
		border: 1px solid #ccc;
		border-color: #e6e6e6 #e6e6e6 #bfbfbf;
		border-color: rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);
		border-bottom-color: #b3b3b3;
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff',endColorstr='#ffe6e6e6',GradientType=0);
		filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
		-webkit-box-shadow: inset 0 1px 0 rgba(255,255,255,0.2),0 1px 2px rgba(0,0,0,0.05);
		-moz-box-shadow: inset 0 1px 0 rgba(255,255,255,0.2),0 1px 2px rgba(0,0,0,0.05);
		box-shadow: inset 0 1px 0 rgba(255,255,255,0.2),0 1px 2px rgba(0,0,0,0.05);
	}

.radioSpecial >	 input[type=radio]:checked + label{
		   background-image: none;
		outline: 0;
		-webkit-box-shadow: inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05);
		-moz-box-shadow: inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05);
		box-shadow: inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05);
			background-color:#0082A3;
			color:#ffffff;
	}
</style>

<script>
var startAge = 18;
var endAge = 68;
  $(function() {
    $( "#slider-range" ).slider({
      range: true,
      min: 18,
      max: 110,
      values: [ 18, 68 ],
      slide: function( event, ui ) {
       $( "#amount" ).html(ui.values[ 0 ] + " - " + ui.values[ 1 ] );
	   startAge = ui.values[0];
	   endAge = ui.values[1];
      }
    });
    $( "#amount" ).html( $( "#slider-range" ).slider( "values", 0 ) +
      " - " + $( "#slider-range" ).slider( "values", 1 ) );
	
  });
  </script>

<style>

select {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    width: 200px;
}

select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input,#fromDate,#toDate {
    border-radius: 4px 4px 4px 4px;
	color: #000000;
    display: inline-block;
    font-size: 13px;
    line-height: 18px;
    padding: 4px;
}
input, button, select, textarea {
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
}

ul li {list-style-type:square;}

   .textAreaClass{
	   background-color:#fff;
   }
  .datagrid table {
	  border-collapse: collapse; 
	  text-align: left; 
	  width: 100%;
   } 
   .datagrid {
	   font: normal 12px/150% Arial, Helvetica, sans-serif; background: #fff; overflow: hidden; border: 1px solid #006699; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;
  }
  .datagrid table td, .datagrid table th {
	  padding: 3px 10px;
	  text-align:center;
  }
  .datagrid table thead th {background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F) );#006699;
	  background-color:#006699; 
	  color:#FFFFFF; 
	  font-size: 15px;
	  font-weight: bold;
	  border-left: 1px solid #0070A8;
  } 
  .datagrid table thead th:first-child {
	  border: none; 
  }
  .datagrid table tbody td {
	  color: #00496B; 
	  border-left: 1px solid #E1EEF4;
	  font-size: 12px;
	  font-weight: normal;
   }
   .datagrid table tbody .alt td {
	   background: #E1EEF4;
	   color: #00496B;
	}
	.datagrid table tbody td:first-child {
		border-left: none;
	}
	.datagrid table tbody tr:last-child td {
		border-bottom: none;
	}

	.datagrid table tr:nth-child(even) {background: #E1EEF4}
    .datagrid table tr:nth-child(odd) {background: #FFF}

	
  </style>
	<style>
	.requiredFont{
		color:red;
	}
	.main-title-sec {
  background-color:#06ABEA;
  background-position:initial initial;
  background-repeat:initial initial;
  border-bottom-left-radius:0;
  border-bottom-right-radius:0;
  border-top-left-radius:5px;
  border-top-right-radius:5px;
  display:table;
  height:36px;
  width:942px;
}

.main-mbg {
  color:#FFFFFF;
  display:table-cell;
  font-family:'Trebuchet MS', Arial, Helvetica, sans-serif;
  font-size:20px;
  font-style:normal;
  font-variant:normal;
  font-weight:bold;
  height:25px;
  line-height:normal;
  padding-left:10px;
  text-transform:uppercase;
 text-align:center;
  padding-top:9px;
  padding-left:31px;
}
	</style>
	<style>
 .selectDivs{
	 margin:5px;
 }
div.tabscontainer{
    margin:0px 0px;
}

div.tabscontainer div.tabs{
    list-style: none;
    width: 260px;
    cursor: pointer;
    float:left;
    margin-top: 10px;
    left: 0px;
    z-index: 2;
}

div.tabscontainer div.curvedContainer{
	margin-left: 259px;
	border:1px solid #7c7c77;
	height:400px;
	overflow:auto;
	background-color:#eff1f1;
	margin:0px 10px 0px 270px;
	-moz-border-radius: 11px;
	-webkit-border-radius: 11px;
	border-radius: 11px;
	position:relative;
	z-index: inherit;
	zoom: 1; /* For IE6 */
	
}

div.tabscontainer div.curvedContainer .tabcontent{
	display:none;
	padding:20px;
	font-size:12px;
	font-family: Arial, Helvetica, sans-serif;
	margin:0px 10px 0px 9px;
	-moz-border-radius: 11px;
	-webkit-border-radius: 11px;
	border-radius: 11px;
	behavior: url(js/border-radius.htc);
}

div.tabs div.tab{
    display: block;
    height: 45px;
    background: #06ABEA;
    border: #d6d6d2 solid 1px;
    border-top: none;
    position: relative;
   	color: #fff;
	margin:0px 0px 0px 10px;
	width:150px;
	border-radius:5px;
	cursor:pointer;
}

div.tabs div.link{
	padding-left: 15px;
	padding-top:11px;
	font-family: Arial, Helvetica, sans-serif;
    font-size: 14px;
}

 div.tabs div.tab.selected{
    background-color: #4B7ED4;
}


div.tabs div.tab.first{
	border-top: #dbdbb7 solid 0px;
	-moz-border-radius-topleft: 13px;
	border-top-left-radius: 13px;
}

div.tabs div.tab.last{
	-moz-border-radius-bottomleft: 13px;
	-webkit-border-radius: 0px 0px 0px 10px;
}
div.tabs div.tab.first{
	-moz-border-radius-topleft:13px;
	-webkit-border-radius: 10px 0px 0px 0px;
}

div.tabs div.tab div.arrow{

    background: url(./images/homeSelArrow.png) no-repeat;
    height: 58px;
    width: 17px;
	position:relative;
	z-index: inherit;
	margin:-33px 0px 0px 0px;
	zoom: 1; 
    left: 100%;
    top: 0px;
    display: none;
}

div.tabs div.tab.selected div.arrow{
    display: block;
}
 .tableSettingtyle{
   height:183px; 
   width:555px;
   overflow:auto;
   margin-left:-6px;
   -moz-border-radius-topright:15px;
   -moz-border-radius-bottomright:15px;
   -webkit-border-radius: 0px 10px 10px 0px;
 }
.widget {
    margin-bottom: 10px;
	background: none repeat scroll 0 0 #FAFAFA;
    border-top: 5px solid #000000;
    box-shadow: 0 0 1px rgba(0, 0, 0, 0.3);
    margin: 5px 0 20px;
    padding: 0 20px 20px;
    position: relative;
}

</style>

<script>

var selectedVotersDetails = {
};

var selectedCadreDetails = {
};

var selectedInfluencePeopleDetails = {
};
var mySearch ='cadre';
var selectedMobileNumbers = new Array();
var selectedCriteria={};
var accessType = '${accessType}';
$(document).ready(function() {

   $('#reportLevel').change(function(){
     if($(this).val() == 2 || $(this).val() == 3 || $(this).val() == 4 || $(this).val() == 5 || $(this).val() == 6){
		 $('#constituencyList').trigger('change');
	 }
	 if( accessType != "MLA" && accessType != "MP" && $(this).val() != 8 && $(this).val() != 9 && $(this).val() != 1 && $('input:radio[name=searchFor]:checked').val() != "voter"){
		 $('#constituencyList').find('option').remove();
	 }
	 
   });


	$('#directSMSToVotersId').hide();

	$('.smsType').change(function(){

		if($(this).val() == "text")
		{
			$('#textSmsDiv').show('slide', {direction: 'left'}, 500);
			$('#voiceSmsDiv').hide();

		}else
		{
			 $('#textSmsDiv').hide();
			$('#voiceSmsDiv').show('slide', {direction: 'right'}, 500);

		}
	});


	$('.searchType').change(function(){
		if($(this).val() == "cadre")
		{
			$('#directSMSToVotersId').hide();
			//clearFieldsData();
			showHideLocationOptionsForOtherthanVoter();
			$('#houseDiv,#casteDiv').hide();
			$('#ageDiv').hide();			
			if(mySearch =='voter' && $('#reportLevel').val() <= 7 ){
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('districtDiv').style.display = 'block';		
			document.getElementById('publicationDateDiv').style.display = 'none';
			}
			if(mySearch =='voter' && accessType != "MLA" && accessType != "MP"){
			 $("<option value='9'>District</option>").insertBefore($('#reportLevel option:first-child'));
			$("<option value='8'>State</option>").insertBefore($('#reportLevel option:first-child'));
			 }
			$("#reportLevel option[value='3']").remove();
			if($('#reportLevel').val() == 1)
					$('#constiTypeDiv').show();
			mySearch ='cadre';
			populateConstituencies();
		}
		else if($(this).val() == "influencePeople")
		{ 
			$('#directSMSToVotersId').hide();
			//clearFieldsData();
			showHideLocationOptionsForOtherthanVoter();
			populateConstituencies();
			$('#constiTypeDiv').hide();

			try
			{
				if(mySearch =='voter' && $('#reportLevel').val() <= 7 ){
					document.getElementById('ConstituencyDiv').style.display = 'block';
					document.getElementById('districtDiv').style.display = 'block';		
					document.getElementById('publicationDateDiv').style.display = 'none';
			}
				
			}
			catch (err)
			{
			}
			
			 $('#houseDiv ,#ageDiv,#casteDiv').hide();
			if(mySearch =='voter'  && accessType != "MLA" && accessType != "MP"){

			 $("<option value='9'>District</option>").insertBefore($('#reportLevel option:first-child'));
			$("<option value='8'>State</option>").insertBefore($('#reportLevel option:first-child'));
			 }
			
		    $("#reportLevel option[value='3']").remove();
			mySearch ='influencePeople';
		}
		else if($(this).val() == "voter")
		{
			$("#reportLevel option").eq(4).before($("<option></option>").val(3).html("Panchayat"));

		    $("#reportLevel option[value='8']").remove();
			$("#reportLevel option[value='9']").remove();
			
			$('#constiTypeDiv,#ConstituencyDiv').hide();
            mySearch ='voter';
			$('#constiDiv').show();
			$('#directSMSToVotersId').show();
			$('#houseDiv , #ageDiv,#casteDiv').show();

		
			document.getElementById('ConstituencyDiv').style.display = 'block';
			if($('#reportLevel').val() > 1 && $('#reportLevel').val() <= 7 )		
			  document.getElementById('publicationDateDiv').style.display = 'block';
			
		   // $("#reportLevel").append('<option value="3">Panchayat</option>');

		   		
	         clearFieldsData();
			getConstituencyList();
			showHideLocationOptionsForVoter();


		}
	});

	
		if('${verifiedNumbersCount}' == 0)
		{
			$('#mainDiv').hide();
			$('#noVerificationNumbers').html('<h5>You do not have any verified mobile numbers.Please contact us to get your mobile number approved by us<h5>');
			return false;
		}else
	    {
			ajaxToGetRecordingDetails();
            getVerifiedNumbersOfUser();
           // getVoiceSmsHistoryOfUser('hide');
			//getSubLevelInfluenceData(1,"Andhra Pradesh","STATE","VILLAGE/WARD","",0,true);

		}
	$('#mobileNumbersDiv').hide();
	$('.subTab ,#tableDiv,#influenceDiv').hide();

	//$('#influencePelpleDiv').show();
	$('#voterSearchDiv').show();

	$('#historyHeading').live("click",function(){
		$('#smsHistory').slideToggle();
	});

	$('.tab').click(function(){
		$('.tab').removeClass('selected');
		$(this).addClass('selected');
	});

	$('#tab_menu_1').click(function(){
		//$('.subTab').hide();
        $('#voterSearchDiv , #cadreDiv').hide();
		$('#influencePelpleDiv,#tableDiv,#influenceDiv').show();

	});

	$('#tab_menu_2').click(function(){
		$('#tableDiv,#influenceDiv,#influencePelpleDiv,#cadreDiv').hide();
		$('#voterSearchDiv').show();
	});

	$('#tab_menu_3').click(function(){
		$('#influenceDiv,#influencePelpleDiv,#cadreDiv,#voterSearchDiv').hide();
		$('#cadreDiv').show();
	});

	$('#parliamentRdio').click(function(){
		$('#ConstituencyDiv').show();
		$('#districtDiv').hide();

		$('#constituencyList')
		.find('option')
		.remove()
		.end()
		.append('<option value="0">Select Constituency</option>')
		.val('');
		cnstiType ='parliamentRdio';
		var jsObj=
		{		
			stateId: $('#statesList').val(),
			task: "getPariamentConstituencies"				
		}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getParliamentConstituencies.action?"+rparam					
		callAjax1(rparam,jsObj,url)
	});
	
	$('#assemblyRdio').click(function(){
	cnstiType ='assemblyRdio';
	$('#districtList').val(0);
	$('#constituencyList').find('option').remove().end()
		.append('<option value="0">Select Constituency</option>').val('');
		$('#ConstituencyDiv').show();
		$('#districtDiv').show();
	});
	
});

function getCandidateLocationDetails()
{
   var jsObj={
				value:"locationWise",
				taskType:"search",
				task:"getUserLocation"
			  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "cadreSMSLocationWiseData.action?"+rparam;

	callAjax1(rparam,jsObj,url);

}
var stattus = false;
function isValidateFields(type){

		//var cadreLocationId;
		var stateId;
		var districtId;
		var constituencyId;
		var mandalId;
		var panchayatId;
		var pollingStationId;
		var wardId;
		var hamletId;
		var reportLevelValue = $("#reportLevel").val();
		var selctedType = $('input:radio[name=searchFor]:checked').val();
		
		$('#AlertMsgs').html('');
		
			if(reportLevelValue == 8){
				stateId = $("#statesList").val(); 
				if(($('#statesList option').length ) >0 && stateId ==0){
					$('#AlertMsgs').html('Please Select State');
					stattus = false;
					return;
				}
				
			}
			else if(reportLevelValue == 9){
				stateId = $("#statesList").val(); 
				districtId = $("#districtList").val();
				if(($('#statesList option').length ) >0 && stateId ==0){
					$('#AlertMsgs').html('Please Select State');
					stattus = false;
					return;
				}
				if(($('#districtList option').length ) >0 &&  districtId == 0){
					$('#AlertMsgs').html('Please Select District');
					stattus = false;
					return;
				}
			}
			
			else if(reportLevelValue == 1){
				stateId = $("#statesList").val(); 
				districtId = $("#districtList").val();
				constituencyId = $("#constituencyList").val();
				if(selctedType !="voter")
				{
					if(($('#statesList option').length ) >0 && stateId == 0){
						$('#AlertMsgs').html('Please Select State');
						stattus = false;
						return;
					}
					if(cnstiType !='parliamentRdio' && ($('#districtList option').length ) >0 &&  districtId == 0){
						$('#AlertMsgs').html('Please Select District');
						stattus = false;
						return;
					}
				}
				if(constituencyId==null || constituencyId == 0){
					$('#AlertMsgs').html('Please Select Constituency');
					stattus = false;
					return;
				}
				
			}
			
			else if(reportLevelValue == 2 || reportLevelValue == 5){
							
				stateId = $("#statesList").val(); 
				districtId = $("#districtList").val();
				constituencyId = $("#constituencyList").val(); 
				
				if(selctedType !="voter")
				{
				
					if(($('#statesList option').length ) == 0 || stateId ==0){
						$('#AlertMsgs').html('Please Select State');
						stattus = false;
						return;
					}
					if(($('#districtList option').length ) == 0 || districtId == 0){
						$('#AlertMsgs').html('Please Select District');
						stattus = false;
						return;
					}
				}
				if(($('#constituencyList option').length) == 0|| constituencyId==null || constituencyId == 0){
					$('#AlertMsgs').html('Please Select Constituency');
					stattus = false;
					return;
				}
				mandalId = $("#mandalField").val();
				if(mandalId==null || mandalId == 0){
					$('#AlertMsgs').html('Please Select Mandal');
					stattus = false;
					return;
				}
				if(reportLevelValue == 5){
				wardId = $("#wardField").val();
				if(wardId==null || wardId == 0){
					$('#AlertMsgs').html('Please Select Ward');
					stattus = false;
					return;
				}
				}
			}
			
			else if(reportLevelValue == 3){
				stateId = $("#statesList").val(); 
				districtId = $("#districtList").val();
				constituencyId = $("#constituencyList").val();
				
				if(selctedType !="voter")
				{
			
					if(($('#statesList option').length ) >0 && stateId ==0){
						$('#AlertMsgs').html('Please Select State');
						stattus = false;
						return;
					}
					if(districtId == 0){
						$('#AlertMsgs').html('Please Select District');
						stattus = false;
						return;
					}
				}
				if(constituencyId==null || constituencyId == 0){
					$('#AlertMsgs').html('Please Select Constituency');
					stattus = false;
					return;
				}
				mandalId = $("#mandalField").val(); 
				if(mandalId==null || mandalId == 0){
					$('#AlertMsgs').html('Please Select Mandal');
					stattus = false;
					return;
				}
				panchayatId = $("#panchayatField").val();
				if(panchayatId==null || panchayatId == 0){
					$('#AlertMsgs').html('Please Select Panchayat');
					stattus = false;
					return;
				}

			}
			 else if(reportLevelValue == 4){

				stateId = $("#statesList").val(); 
				districtId = $("#districtList").val();
				constituencyId = $("#constituencyList").val(); 
				
				if(selctedType !="voter")
				{
					if(stateId ==0){
						$('#AlertMsgs').html('Please Select State');
						stattus = false;
						return;
					}
					if(districtId == 0){
						$('#AlertMsgs').html('Please Select District');
						stattus = false;
						return;
					}
				}
				if(constituencyId==null || constituencyId == 0){
					$('#AlertMsgs').html('Please Select Constituency');
					stattus = false;
					return;
				}
				mandalId = $("#mandalField").val();
				if(mandalId==null || mandalId == 0){
					$('#AlertMsgs').html('Please Select Mandal');
					stattus = false;
					return ;
				}

				pollingStationId = $("#pollingStationField").val();
				if(pollingStationId==null || pollingStationId == 0){
					$('#AlertMsgs').html('Please Select Polling Station');
					stattus = false;
					return;
				}
			}
			else if(reportLevelValue == 6){
				
				stateId = $("#statesList").val(); 
				districtId = $("#districtList").val();
				constituencyId = $("#constituencyList").val(); 
				
				if(selctedType !="voter")
				{
					if(stateId ==0){
						$('#AlertMsgs').html('Please Select State');
						stattus = false;
						return;
					}
					if(districtId == 0){
						$('#AlertMsgs').html('Please Select District');
						stattus = false;
						return;
					}
				}
				if(constituencyId==null || constituencyId == 0){
					$('#AlertMsgs').html('Please Select Constituency');
					stattus = false;
					return;
				}
				mandalId = $("#mandalField").val();
				if(mandalId==null || mandalId == 0){
					$('#AlertMsgs').html('Please Select Mandal');
					stattus = false;
					return;
				}				
				panchayatId = $("#panchayatField").val();
				if(panchayatId==null || panchayatId == 0){
					$('#AlertMsgs').html('Please Select Panchayat');
					stattus = false;
					return;
				}				 
				hamletId = $("#hamletField").val(); 
				if(hamletId==null || hamletId == 0){
					$('#AlertMsgs').html('Please Select Hamlet');
					stattus = false;
					return;
				}
			}


	if(type == 'direct')
		sendDirectSMSToVoters();
	else
	{
	 stattus = true;
	 getCandidatesInfo(stattus);
	}
}
	
function getCandidatesInfo(stattus){
//alert(stattus);
	if(stattus)
		getCandidatesData("search");
}

var cnstiType;

var isAgeSelected = false;
var isCasteSelected = false;
var isFamilySelected = false;
var isNameSelected = false;
var isGenderSelected = false;
var houseNo = "";
var publicationDateId = "";
var reportLevelValue = 1;
var areaId=0;
var searchName = "";
var casteIds = "";
var gender = "";
var searchArea='constituency';
var cadreLocationId =1;

function getCandidatesData(sentType){
	casteIds='';
	flag=false;
        var selectedCasts = new Array();
		if( $('#casteId').val() != null)
		 selectedCasts = $('#casteId').val();

		 searchName=$("#searchName").val();
		//var startAge =$("#age1").val();
		//var endAge =$("#age2").val();		
		 houseNo =$("#searchHouseNo").val();
		var caste =$("#searchCaste").val();
		 gender=$('#genderDiv input:radio:checked').val();
		 reportLevelValue = $("#reportLevel").val();
		 publicationDateId = $("#publicationDateList").val();
	
	
		var panchayatFieldId = 0; 
		var wardFieldId = $("#wardField").val(); 
		var hamletFieldId = $("#hamletField").val(); 
		var pollingStationFieldId = $("#pollingStationField").val(); 
		
	
		//var isAgeSelected = (startAge.trim() == "") && endAge.trim() == "" ? false : true;

          
	   isAgeSelected  = $('#ageCheckbox').is(':checked')  ? true :false;
	   isCasteSelected = (selectedCasts.length == 0) ? false : true;
	   isFamilySelected = (houseNo.trim() == "") ? false : true;
	   isNameSelected = (searchName.trim() == "") ? false : true;	  
	   isGenderSelected =  (gender == "All") ? false : true;

	   var cadreReportLevelValue;
			
			if(publicationDateId == null)
				publicationDateId = 8;
			if(reportLevelValue == 1){
				areaId = $("#constituencyList").val(); 			
				searchArea = "constituency";
				cadreLocationId = 4;
				
			}
			else if(reportLevelValue == 2){
				areaId = $("#mandalField").val().substring(1);
				cadreReportLevelValue =  $("#mandalField").val();
				searchArea = "mandal";
				cadreLocationId = 5;
			}
			else if(reportLevelValue == 3){
				areaId = $("#panchayatField").val(); 
				searchArea = "panchayat";
			}
			 else if(reportLevelValue == 4){

				areaId = $("#pollingStationField").val(); 
				searchArea = "booth";
				cadreLocationId = 9;
			}
			else if(reportLevelValue == 5){
				areaId = $("#wardField").val(); 
				searchArea = "ward";
				cadreLocationId = 8;
			}
			else if(reportLevelValue == 6){
				areaId = $("#hamletField").val(); 
				searchArea = "hamlet";
				cadreLocationId = 6;
			}
			else if(reportLevelValue == 8){
				stateId = $("#statesList").val(); 
				areaId = $("#statesList").val(); 
				cadreLocationId = 2;
			}
			else if(reportLevelValue == 9){
				areaId = $("#districtList").val();
				searchArea = "district";
				cadreLocationId = 3;
			}
		
			if(cnstiType =='parliamentRdio' && reportLevelValue == 1)
            {
				cadreLocationId = 10;
			}


		
		  
		   if(selectedCasts != null){
			if(selectedCasts.length ==1){
				for(var i in selectedCasts){		
					casteIds += selectedCasts[i];
				}
			}
			else{
			   for(var i in selectedCasts){		
					casteIds += selectedCasts[i]+"-";
					//casteIds = casteIds.substring(1);
					}
				}
			}
			console.log(casteIds);
			  selectedCriteria.isAgeSelected = isAgeSelected;
			  selectedCriteria.isCasteSelected = isCasteSelected;
			  selectedCriteria.isFamilySelected = isFamilySelected;
			  selectedCriteria.isNameSelected = isNameSelected;
			  selectedCriteria.isGenderSelected = isGenderSelected;
			  selectedCriteria.startAge = startAge;
			  selectedCriteria.endAge = endAge;
			  selectedCriteria.houseNo = houseNo;
			  selectedCriteria.casteIds = casteIds;
			  selectedCriteria.gender = gender;
			  selectedCriteria.reportLevelValue = areaId;
			  selectedCriteria.publicationDateId = publicationDateId;
			  selectedCriteria.searchArea = searchArea;
			  selectedCriteria.constituencyId = 232;
			  selectedCriteria.searchName = searchName;
			  selectedCriteria.cadreLocationId = cadreLocationId;
			  selectedCriteria.cadreReportLevelValue = cadreReportLevelValue;

			  selectedCriteria.socialStatus = false;
			  

        if(sentType != "direct")
	    {

			if(areaId !=0 && $('input[name=searchFor]:checked').val() == "voter")
				getVoterSearchDetails();
			else if (areaId !=0 && $('input[name=searchFor]:checked').val() == "cadre")
				openCadreWindow();  //getCadreSearchDetails();
			else if(areaId !=0)
				openInfluencePeopleWindow(); 
	   }
	   else
		   sendDirectSMSToVotersAjax();
}
var flag=false;
function getVoterSearchDetails()
{
  if(!flag){
	flag=true;
  var urlStr = "messageCenterVoterSearchAction.action?constituencyId="+selectedCriteria.constituencyId+"&publicationDateId="+selectedCriteria.publicationDateId+"&locationValue="+selectedCriteria.reportLevelValue+"&locationType="+selectedCriteria.searchArea+"&locationName=KAVALI&";

   if(selectedCriteria.isAgeSelected == true)
	   urlStr += "startAge="+selectedCriteria.startAge+"&endAge="+selectedCriteria.endAge+"&";
   if(selectedCriteria.isCasteSelected == true)
	   urlStr += "casteIds="+selectedCriteria.casteIds+"&";
   if(selectedCriteria.isFamilySelected == true)
	   urlStr += "houseNo="+selectedCriteria.houseNo+"&";
   if(selectedCriteria.isNameSelected == true)
	   urlStr += "name="+selectedCriteria.searchName+"&";
   if(selectedCriteria.isGenderSelected == true)
	   urlStr += "gender="+selectedCriteria.gender+"&";

   urlStr += "isAgeSelected="+selectedCriteria.isAgeSelected+"&isCasteSelected="+selectedCriteria.isCasteSelected+"&isFamilySelected="+selectedCriteria.isFamilySelected+"&isNameSelected="+selectedCriteria.isNameSelected+"&isGenderSelected="+selectedCriteria.isGenderSelected+"&";


  var browser2 = window.open(urlStr,"messageCenterVoterSearch","scrollbars=yes,height=570,width=1300,left=200,top=50");	
  browser2.focus();
	}
}
function openInfluencePeopleWindow(){
	
	var urlStr = "searchInfluencePeopleForSmsAction.action?locationValue="+selectedCriteria.reportLevelValue+"&locationType="+selectedCriteria.searchArea+"&";
	
	if(selectedCriteria.isAgeSelected == true)
	   urlStr += "startAge="+selectedCriteria.startAge+"&endAge="+selectedCriteria.endAge+"&";
   if(selectedCriteria.isCasteSelected == true)
	   urlStr += "casteIds="+selectedCriteria.casteIds+"&";
   if(selectedCriteria.isFamilySelected == true)
	   urlStr += "houseNo="+selectedCriteria.houseNo+"&";
   if(selectedCriteria.isNameSelected == true)
	   urlStr += "name="+selectedCriteria.searchName+"&";
   if(selectedCriteria.isGenderSelected == true)
	   urlStr += "gender="+selectedCriteria.gender+"&";
   urlStr += "isAgeSelected="+selectedCriteria.isAgeSelected+"&isCasteSelected="+selectedCriteria.isCasteSelected+"&isFamilySelected="+selectedCriteria.isFamilySelected+"&isNameSelected="+selectedCriteria.isNameSelected+"&isGenderSelected="+selectedCriteria.isGenderSelected+"&";
  var browser3 = window.open(urlStr,"messageCenterinfluencingPeopleSearch","scrollbars=yes,height=570,width=1300,left=200,top=50");	
  browser3.focus();
	
}
function getallConstituencies(district){
var jsObj=
		{						
				districtId:district,
				task:"getConstituencyNames"
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "sendUpdatesByemailsAction.action?"+rparam;						
	callAjax1(rparam,jsObj,url);
}
</script>
 </head>
 <body>


<div id="noVerificationNumbers" style="text-align:center;margin:5px;"></div>
<div id="mainDiv" class="container" style="margin-top:12px;">
<div class="row">

 <div class="main-title-sec span12">
    <div class="main-mbg">Message Center</div>
 </div>

  
    

  <div style="height:auto;border:1px solid #06ABEA;" class="span12" >

   
     <div class="radioSpecial" style="margin:9px 0px 0px 72px;">
		 <span style="font-size:13px;font-weight:bold;">Search By:</span><input type="radio" id="cadre" name="searchFor" value="cadre" class="searchType" checked>
		   <label for="cadre">Cadre</label>
		<input type="radio" id="influencePeople" name="searchFor" value="influencePeople" class="searchType" >
		   <label for="influencePeople">Influence People</label>
		<input type="radio" id="voter" name="searchFor" value="voter" class="searchType">
		   <label for="voter">Voter</label>
	 </div>

	
  
  <div class="widget whitegloss span5"  id="voterSearchDiv1" style="border:0;text-align:center;margin:9px;">

    
		   
		<div id="AlertMsgs" style="font-family: verdana;font-size: 13px;color:red;"></div>
		<div id="AlertMsg" style="font-family: verdana;font-size: 13px;color:red;"></div>
	  <div id="errorMsgAlert" style="font-family: verdana;font-size:13px;color:red;margin-left:100px;margin-bottom: 12px; margin-top: 3px;"></div>
     
	  <div id="reportLevelDiv"><span style="margin-left: 5px;">Select Level</span><font class="requiredFont">*</font>
	  <select id="reportLevel" class="selectWidth" style="margin-left:70px;width:165px;" name="constituencyList" onchange="showReportsLevels(this.options[this.selectedIndex].value);">
		<option value=8 class="soption">State</option>
		<option value=9 class="doption">District</option>
		<option value=1 class="coption">Constituency</option>
		<option value=2 class="moption">Mandal  / Muncipality</option>
		<option value=5 class="woption">Ward</option>
	    <option value=6 class="hoption">Hamlets</option>
        <option value=4 class="poption">PollingStation</option>
		</select>
      </div>

      
	     	 
		 <div class="selectDivs" id="stateDiv">
			 <span>Select State</span>
			 <font class="requiredFont">*</font>
			  <s:select theme="simple" class="selectWidth" style="margin-left:71px;width:165px;" cssClass="selectWidth" label="Select Your State" name="statesList" id="statesList" list="statesList" listKey="id" listValue="name" onchange="clearErrDiv(),"/>
		 </div>


         <c:if test="${accessType != 'MP' && accessType != 'MLA'}">
		 <div class="" id="constiTypeDiv" style="font-weight:bold;font-size:13px;display:none;margin-left:128px;display:none;">
		<div><label for="assemblyRdio"><input class="contiType" checked="true" type="radio" name="constituency" id="assemblyRdio" style="margin:0px;"/> Assembly Constituency</label></div><label for="parliamentRdio" style="margin-left:9px;" id="parlimntRdio"><input class="contiType" type="radio" name="constituency" id="parliamentRdio" style="margin:0px;"/> Parliament Constituency</label>
		</div>
		</c:if>

		 <div class="selectDivs" id="districtDiv">
			 <span>Select District</span>
			 <font class="requiredFont">*</font>
			 <s:select theme="simple" class="selectWidth" style="margin-left:60px;width:165px;" cssClass="selectWidth" label="Select Your State" name="districtList" id="districtList" list="districtList" listKey="id" listValue="name" onchange="clearErrDiv(),getallConstituencies(this.value);"/>
		 </div>

         <c:if test="${accessType == 'MLA'}">


			<div class="selectDivs" id="ConstituencyDiv" style="margin-left:0px;"> 
			 <div class="selectDivs" id="constiDiv" ><span>Select Constituency</span>
			 <font class="requiredFont">*</font>
			<!--<select id="constituencyList" style="margin-left:37px;width:165px;" onchange="clearErrDiv(),getMandalOrMuncipalityList();getPublicationDate();getAllTheCastesOfConstituency(this.value)"><option value="0">Select Constituency</option></select>-->
               
             <s:select theme="simple" class="selectWidth" style="margin-left:37px;width:165px;" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="clearErrDiv(),getMandalOrMuncipalityList();getPublicationDate();getAllTheCastesOfConstituency(this.value)"/>

			 </div>
			 </div>

		 </c:if>

		 <c:if test="${accessType == 'MP'}">
			<div class="selectDivs" id="ConstituencyDiv" style="margin-left:0px;"> 
			 <div class="selectDivs" id="constiDiv" ><span>Select Constituency</span>
			 <font class="requiredFont">*</font>


			 <s:select theme="simple" class="selectWidth" style="margin-left:37px;width:165px;" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="constituencyList" list="parliamentConstituencyList" listKey="id" listValue="name" onchange="clearErrDiv(),getMandalOrMuncipalityList();getPublicationDate();getAllTheCastesOfConstituency(this.value)"/>
			 </div>
			 </div>
		 </c:if>

		 <c:if test="${accessType != 'MP' && accessType != 'MLA'}">
			<div class="selectDivs" id="ConstituencyDiv" style="margin-left:0px;display:none;"> 
			 <div class="selectDivs" id="constiDiv" ><span>Select Constituency</span>
			 <font class="requiredFont">*</font>


			 <s:select theme="simple" class="selectWidth" style="margin-left:37px;width:165px;" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="clearErrDiv(),getMandalOrMuncipalityList();getPublicationDate();getAllTheCastesOfConstituency(this.value)"/>
			 </div>
			 </div>
		 </c:if>

	     <div class="selectDivs" id="publicationDateDiv" style="display:none;"><span>Select Publication Date</span><font class="requiredFont">*</font> <select id="publicationDateList" class="selectWidth" style="width:165px;margin-left:20px;" name="publicationDateList" >
		</select>  <span style='display:none;float: right;' id='ajaxLoad'><img src='./images/icons/search.gif' /></span>	
		</div>
	  
	  <div id="mandalDiv" class="selectDivs" style="display:none;">
	     <span id="mandalSpan">Select Mandal</span><font class="requiredFont">*</font>
		 <select id="mandalField" class="selectWidth" name="state" onchange="clearErrDiv(),getBooths();" style="margin-left:68px;width:165px;">
		 <option value="0">Select Mandal</option></select>
	  </div>


	   <div id="wardDiv" style="display:none;" class="selectDivs">
	    <span>Select Ward</span><font class="requiredFont">*</font> <select id="wardField" class="selectWidth" name="state" onchange="clearErrDiv(),getLocalitiesList('ward','wardField');getLocalitiesList('pollingstationByPublication','pollingStationField');" style="margin-left:74px;width:165px;">
		<option value="0">Select Ward</option></select> 
	  </div>
		
	  <div id="panchayatDiv"  style="display:none;" class="selectDivs">
	    <span>Select Panchayat</span><font class="requiredFont">*</font> 	
	    <select id="panchayatField" class="selectWidth" name="state" onchange="clearErrDiv(),getHamletsList('hamlet','hamletField');" style="margin-left:48px;width:165px;">
		<option value="0">Select Panchayat</option></select>
	  </div>
	   <div id="hamletDiv"  style="display:none;" class="selectDivs">
	    <span>Select Hamlet</span><font class="requiredFont">*</font> <select id="hamletField" class="selectWidth" name="state" onchange="clearErrDiv(),getLocalitiesList('ward','wardField');getLocalitiesList('pollingstationByPublication','pollingStationField');" style="margin-left:67px;width:165px;"><option value="0">Select Hamlet</option></select> 
	 </div>
	
	  <div id="pollingStationDiv"  style="display:none;" class="selectDivs">
	    <span>Select PollingStation</span><font class="requiredFont">*</font><select id="pollingStationField" class="selectWidth" name="state"  style="margin-left:37px;width:165px;" onchange="clearErrDiv()">
		<option value="0">Select PollingStation</option></select>
	  </div>
	  <div id="localityDiv" class="selectDiv" style="display:none;">
	   <span>Select Locality</span><font class="requiredFont">*</font> <select id="localityField" class="selectWidth" name="state" onchange="getLocalitiesList('ward','wardField'),getLocalitiesList('pollingstationByPublication','pollingStationField');" style="margin-left:60px;width:165px;">
	   <option value="0">Select Locality</option></select> 
	  </div>
   
 </div>

  <div class="span4" style="border:2px solid #F2F2F2;padding:8px;background-color:#FAFAFA;margin:5px 0px 0px 95px;" id="selectedPeople">
    <label>No Of Cadre Selected<span id="cadreCount" style="font-weight:bold;margin-left:83px;" class="badge">0</span><a class="pull-right" href="javascript:{removeCadre()}" title="Click here to remove selected cadre numbers"><img style='text-decoration: none; border: 0px none;' src='images/cross.png'></a></label>
    <label>No Of Influencing People Selected<span id="influencePeopleCount" style="font-weight:bold;margin-left:2px;" class="badge">0</span><a class="pull-right" href="javascript:{removeInfluencePeople()}" title="Click here to remove selected influencing people numbers"><img style='text-decoration: none; border: 0px none;' src='images/cross.png'></a></label>
    <label>No Of Voters Selected<span id="voterCount" style="font-weight:bold;margin-left:77px;" class="badge">0</span><a href="javascript:{removeVoters();}" title="Click here to remove selected voter numbers" class="pull-right"><img style='text-decoration: none; border: 0px none;' src='images/cross.png'></a></label>
  </div>


   <div class="span5 widget" style="font-weight:bold;margin-bottom: 15px;font-size:13px;width: 381px;border:0px;padding:17px 0px 0px 37px;display:none;clear:both;margin-left:10px;" id="searchOptionsDiv">

   	   
   
     <div class="selectDiv radioSpecial" id="genderDiv"style="margin-bottom: 10px;">
	 <span style="margin-right:100px;">GENDER:</span>
		 <input type="radio" id="all"  name="gender" value="All"  checked>
		   <label for="all">All</label>
		<input type="radio" id="male" name="gender" value="M"  >
		   <label for="male">Male</label>
		<input type="radio" id="female" name="gender" value="F" >
		   <label for="female">Female</label>
	 </div>
	 
	<div class="selectDiv" style="margin-bottom: 10px;" id="nameDiv">
	    Name  <span style="margin-left:90px;">:</span><input type="text" id="searchName" style="width:154px;margin-left: 25px;">
	  </div>
	   <div class="selectDiv" style="margin-bottom: 10px;display:none;" id="casteDiv">
	    Caste  <span style="margin-left:109px;">:</span><!--<input type="text" id="searchCaste" style="width:154px;margin-left: 25px;">-->
		<select multiple="true" id="casteId">
		
		</select>
	  </div>	  
    <div class="selectDiv" style="margin-bottom: 10px;display:none;" id="houseDiv">
	    House No  <span style="margin-left:86px;">:</span><input type="text" id="searchHouseNo" style="width:154px;margin-left: 2px;">
	  </div>
	  <!--
	<div class="selectDiv" style="margin-bottom: 10px;display:none;" id="ageDiv">
	    Between Age  <span style="margin-left:45px;">:</span><input type="text" id="age1" style="width:100px; margin-left: 25px;">
		<input type="text" id="age2" style="width:100px;">
	 </div>
	 -->
	 <div id="ageDiv" style="display:none;">
	  <!-- <div  class="selectDiv"  style="float:left;">
		 <input type="checkbox" id="ageCheckbox"/>Age range :</div>-->
		 <span>Age Range</span>
		 <!-- <input type="text" id="amount" style="border: 0; color: #f6931f; font-weight: bold;width:50px;" />-->
		 <div style="width:165px;margin-left:162px;">
		   <div id="slider-range"></div>

		   <span id="amount"></span>
		  </div>

	</div>

  
   </div>

   <div style="clear:both;">
	
   </div>

    <div style="margin-left:100px;margin-bottom:9px;">
          <!--<button id="directSMSToVotersId" class="btn btn-success" style="" onclick="isValidateFields('direct');"> Send SMS To All Voters </button> -->
		  <a href="javascript:{$('#searchOptionsDiv').toggle('slow');}" style="font-size:12px;"><b>Show/Hide Search Options</b></a>

	     <button id="searchCandidatesId" class="btn btn-primary" onclick="isValidateFields('search');"> Search </button> 
		
   </div>




    <div class="span4" style="margin:19px 0px 14px 292px;border:2px solid #F2F2F2;padding:8px;display:none;" id="selectedPeople">
    <label>No Of Cadre Selected<span id="cadreCount" style="font-weight:bold;margin-left:83px;" class="badge">0</span><a class="pull-right" href="javascript:{removeCadre()}" title="Click here to remove selected cadre numbers"><img style='text-decoration: none; border: 0px none;' src='images/cross.png'></a></label>
    <label>No Of Influencing People Selected<span id="influencePeopleCount" style="font-weight:bold;margin-left:2px;" class="badge">0</span><a class="pull-right" href="javascript:{removeInfluencePeople()}" title="Click here to remove selected influencing people numbers"><img style='text-decoration: none; border: 0px none;' src='images/cross.png'></a></label>
    <label>No Of Voters Selected<span id="voterCount" style="font-weight:bold;margin-left:77px;" class="badge">0</span><a href="javascript:{removeVoters();}" title="Click here to remove selected voter numbers" class="pull-right"><img style='text-decoration: none; border: 0px none;' src='images/cross.png'></a></label>
  </div>


  </div>

	
<div style="border:1px solid #06ABEA;" class="span12" >

<h4 style="color: rgb(255, 255, 255); text-align: center; background-color: rgb(6, 171, 234); padding: 7px; background-position: initial initial; background-repeat: initial initial;">YOU CAN ENTER  NUMBERS MANUALLY HERE</h4>

 <div style="text-align:center;margin:0px;" class="widget whitegloss"><label>(Add Country Code Before Mobile Number):<br>Example:<b>919999999999</b></label><textarea id="mobileNumber" class="textAreaClass"></textarea></div>
</div>


    <div class="selectDiv radioSpecial" id="ssmTypeDiv" style="margin:0px 0px 9px 413px;">
		 <input type="radio" id="textSMS"  name="smsType" value="text"  class="smsType" checked>
		   <label for="textSMS">Text SMS</label>
		<input type="radio" id="voiceSMS" name="smsType" value="voice" class="smsType">
		   <label for="voiceSMS">Voice SMS</label>	
	 </div>

 <div style="height:auto;border:1px solid #06ABEA;" class="span12" >

<div style="clear:both;" id="textSmsDiv">
<h4 style="color:#FFF;text-align:center;background:#06ABEA;padding:7px;">SEND TEXT SMS</h4>

<div class="widget whitegloss" style="margin:0px 0px 0px 0px;text-align:center;">

<span id="errorMsg"></span>
<label>Enter Message To send:<font style="color:red;">*</font></label><textarea id="textSmsDescription" class="textAreaClass"></textarea></div>

</div>

<!-- SEND VOICE SMS START-->
<div style="clear:both;display:none;" id="voiceSmsDiv">

<h4 style="color:#FFF;text-align:center;background:#06ABEA;padding:7px;">SEND VOICE SMS</h4>

<div class="widget whitegloss" style="margin:0px 0px 20px 0px;">
<h5 style="margin:8px 0px 0px 92px;">Instructions To Send Voice SMS</h5>
 <ul style="margin:8px 0px 0px 92px;font-size:13px;font-weight:bold;"> 
  <li>Enter new mobile numbers or search from voters , cadre , influencing people from the above search feature provided and add their mobile numbers.</li>
  <li>Enter the description specifying the purpose of the voice SMS.</li>
  <li>Please install flash player to listen to the audio files sent previously or uploaded to the server by you.</li>
  <li>You can send already existing audio files in the server or select an audio file from your computer and upload to the server or record your own new voice message<a href="uploadAudioFile.action" target="_blank"><b>Upload/ Record</b></a></li>.
  <li>You can not send promotional audios.</li>
  <li>If you want to send voice SMS from a new mobile number which doesn't exist in the verified numbers list, please contact us to get your new number approved by us.</li>
  <li>You need to accept our terms and conditions.</li>




 </ul>
</div>


  <div style="text-align:center;margin-top:5px;"><label>Enter Description:<font style="color:red;">*</font></label><textarea id="voiceSmsDescription" class="textAreaClass"></textarea></div>

<div id="invalidContactsDiv" style="float: right; margin-top: -200px; position: static; width: 200px;">
 <!--<span id="notValidContactSpan" style="border: 1px solid rgb(156, 156, 156); width: 180px; height: 200px; font-weight: bold;"><font style="margin-left: 40px;">Invalid Contacts:</font>
 
 <div id="notValidContact" style="width: 175px; margin-left: 5px; overflow: scroll; height: 182px;"></div>
 </span>-->
 </div>
  <h4 style="color:#3A87AD;margin:40px 0px 0px 128px;"><u>AUDIO FILES AVAILABLE</u><font style="color:red;">*</font><a href="javascript:{ajaxToGetRecordingDetails()}"><img src="images/icons/refreshImg.png" alt="Processing Image" title="Click here to refresh audio files"/></a></h4>
  <div id="audioFilesDiv"></div>

  <h4 style="color:#3A87AD;margin:40px 0px 0px 124px;"><u>VERIFIED NUMBERS TO SEND VOICE SMS</u><font style="color:red;">*</font></h4>
  <div id="verifiedNumbersDiv"></div>

  <div style="margin:14px 0px 0px 116px"><label><input type="checkbox" style="margin:0px;" id="termsAndConditions"/><span style="margin:0px 0px 0px 7px;">I have read and agree to the <a href="javascript:{showTermsAndConditiond();}">Terms and Conditions</a></span></label></div>


   
<div id="errorDiv" style="color:red;font-family:verdana;font-size:12px;margin:20px 0 0 125px;"></div>


<div id="audioOuter">
	 <div id="audioInner"></div>
</div>


<div id="termsAndConditionsDialog" style="display:none;">
<jsp:include page="termsAndConditions.jsp" />
</div>



<!--<div style="margin:2px;">
<input type="button" class="btn btn-info" value="Send Voice SMS" style="margin-left:750px;" onClick="validateFieldsForSendingSms()"/>
</div>-->

<!--
<div   style='background:#06ABEA;border-radius:0px;text-align:center;position:relative;margin-bottom:-45px;padding:7px;color:#fff;'><a id='historyHeading' href="javascript:{}" style="color:#fff;"><h4 style="color:#fff;margin-left:25px;">SHOW/HIDE SMS HISTORY</a><a href="javascript:{getVoiceSmsHistoryOfUser('show')}"><img src="images/icons/refreshImg.png" alt="Processing Image" title="Click here to refresh SMS history" /></a></h4></div>


<div class="breadcrumb" style="margin-top:40px;"> 
 <div id="smsHistory"></div>
</div>
-->

<div id="responseDetailsDiv">
 <div id="responseDetailsInnerDiv">
</div>


</div>
<!-- SEND VOICE SMS END-->




</div>

<!-- Complete options  end -->
 
 </div>

 </div>

<div style="margin:5px;">
 <a class="btn pull-left btn-primary" href="javascript:{openSmsHistoryWindow();}">Click Here To See History</a>

 <span id="successMsg" style="font-weight:bold;color:green;margin-left:200px;"></span>
 <a class="btn pull-right btn-primary" href="javascript:{sendSMS();}">Send SMS </a>
</div>
 </div>

 <!--<a class="btn" href="javascript:{openSmsHistoryWindow();}">HISTORY</a>-->



<script>
function ajaxToGetRecordingDetails(){
	var jsObj=
			{
				task:"getRecordingsOfUser",
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getAllTheRecordedFilesOfAUser.action?"+rparam;	
			callAjax1(rparam,jsObj,url);
}
function getVerifiedNumbersOfUser(){
	var jsObj=
			{
				task:"getVerifiedNumbersOfUser",
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVerifiedNumbersOfUser.action?"+rparam;	
			callAjax1(rparam,jsObj,url);
}
function getVoiceSmsHistoryOfUser(visibility){
	var jsObj=
			{
				task:"getVoiceSmsHistoryForAuser",
			    visibility:visibility		 
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVoiceSmsHistoryForAuser.action?"+rparam;	
			callAjax1(rparam,jsObj,url);
}
function showMessageResponseDetails(responseCode){
	var jsObj=
			{
				task:"getResponseDetails",
                messageResponseCode:responseCode
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getResponseDetailsForSms.action?"+rparam;	
			callAjax1(rparam,jsObj,url);

			$('#responseDetailsInnerDiv').html("<img style='margin-left:130px;' src='./images/icons/search.gif' />");
				

					     $('#responseDetailsDiv').dialog({
						    title:"Response Details" ,
                            modal:true,
						    buttons: {								
								"Ok":function(){$(this).dialog("close");} 
							}
	       });
}

function validateFieldsForSendingSms()
{

	var audioFileName = $("input:radio[name=audio]:checked").attr('id');
    var senderNumber = $("input:radio[name=senderNumber]:checked").val();


	var error = false;
	$('#errorDiv').html('');

	var str='';


	if($('#voiceSmsDescription').val() == ""){
		str+='<b>Description is required</b></br>';
		error = true;
	}
	

	if(audioFileName == undefined)
	{
		str+='<b>Select an audio to send</b></br>';
		error = true;
	}

	if(senderNumber == undefined)
	{
		str+='<b>Select a sender number to sent sms</b></br>';
		error = true;
	}

	if(!$("#termsAndConditions").prop('checked') == true){
       
		str+='<b>You need to accept our terms and conditions.</b></br>';
		error = true;
    }
	if(error == true)
	{
		$('#errorDiv').html(str);
		return false;
	}
	else
		ajaxToSendVoiceSms();


}

function ajaxToSendVoiceSms(){




	 $('#responseDetailsInnerDiv').html("<img style='margin-left:130px;' src='./images/icons/search.gif' />");
				

					     $('#responseDetailsDiv').dialog({
						    title:"Response Details" ,
						    buttons: {								
								"Ok":function(){$(this).dialog("close");} 
							}
	       });


	var audioFileName = $("input:radio[name=audio]:checked").attr('id');
    var senderNumber = $("input:radio[name=senderNumber]:checked").val();
	var mobileNumbers = ($('#mobileNumber').val().trim() != "" ? $('#mobileNumber').val():"");
	var description = $('#voiceSmsDescription').val();

	

	var cadreDetails = new Array();
	var influencePeopleDetails = new Array();
	var votersDetails = new Array();

	$.each(selectedCadreDetails, function(index, value) {
		cadreDetails.push(value);
    });

    $.each(selectedInfluencePeopleDetails, function(index, value) {
		influencePeopleDetails.push(value);
    });

	$.each(selectedVotersDetails, function(index, value) {
		votersDetails.push(value);
    });



	var jsObj=
			{
				task:"sendVoiceSms",
                audioFileName:audioFileName,
                mobileNumbers:mobileNumbers,
                senderMobileNumber:senderNumber,
                selectedMobileNumbers:selectedMobileNumbers,
				description:description,
				cadreDetails:cadreDetails,
				influencePeopleDetails:influencePeopleDetails,
                votersDetails:votersDetails
					
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "sendVoiceSMS.action?"+rparam;	
			callAjax1(rparam,jsObj,url);
}


</script>



<script>
$('#mobilesId').html("");

$('input[name=type][value=ipeople]').prop("checked",true);
removeOptions();

function removeOptions(){
	for(var i=0;i<=9;i++){
			if(i>4){
				$("#subLevelsId option[value="+i+"]").remove();
			}
		}
}

$('#subLevelsId').val(2);
$('#stateField_s').val(1);

var sublevelArr=[];


$("#subLevelsId option").each(function()
{
	var obj={};
	var value=$(this).val();
	var name=$(this).text();
   
	obj[name] = value;
	sublevelArr.push(obj);
});
	
	
	

$("input:radio[name=type]").click(function() {
    var value = $(this).val();
	
	if(value=="ipeople"){
		
		$('.selectBoxes,#sublevels').css('display','block');
		$("#voterSearchDiv").css("display","none");
	
		for(var i=0;i<=9;i++){
			if(i>4){
				$("#subLevelsId option[value="+i+"]").remove();
			}
		}
	}
	else if(value=="voterSearch")
	{
	  $('.selectBoxes,#sublevels').css('display','none');
      $("#voterSearchDiv").css("display","block");
	}

	else{
		$("#voterSearchDiv").css("display","none");
		$("#subLevelsId").html('');
		var str='';
		$.each(sublevelArr, function(idx, obj){ 
			$.each(obj, function(name, value){
				str+='<option value="'+ value +'">'+ name +'</option>';
			});
		});
		$("#subLevelsId").html(str);
		
		$('.selectBoxes,#sublevels').css('display','none');
		
		buildSearchPagePopup("Search");
	}
	
});

function getConstituencies(){
	var districtId=$('#districtField_s option:selected').val();
		getConstituenciesOfDistrict(districtId);
}
function getDistricts(){
	var stateId=$('#stateField_s option:selected').val();
		getDistrictsOfState(stateId);
}
function getSubRegions(){
	var constituencyId=$('#constituencyField_s option:selected').val();
		getSubRegionsOfConsti(constituencyId);
}
function getSubRegionsInMandal(){
	var localId=$('#mandalField_s option:selected').val();
	var constituencyId=$('#constituencyField_s option:selected').val();
	var name=$('#mandalField_s option:selected').text();
		getSubRegionsOfLocalBody(localId,name,constituencyId);
}
function getSubRegionsInMuncipal(){
	var localId=$('#muncipalField option:selected').val();
	var name=$('#muncipalField option:selected').text();
	var constituencyId=$('#constituencyField_s option:selected').val();
		getSubRegionsOfLocalBody(localId,name,constituencyId);
}


var areaType='';
$('.selectBoxes > :not(#stateField_s)').hide();
var scopeSelected='';
function getScopes(){
scopeSelected=$('#subLevelsId option:selected').text();
$('#Shead').css("display","none");
$('#Dhead').css("display","none");
$('#Chead').css("display","none");
$('.selectBoxes').show();
if(scopeSelected =="Select Level"){
$('.selectBoxes').hide();
}
if(scopeSelected=="STATE"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s)').hide();
	$('#Shead').css("display","block");
}
if(scopeSelected=="DISTRICT"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s)').hide();
	$('#Shead').css("display","block");
	$('#Dhead').css("display","block");
}
if(scopeSelected=="CONSTITUENCY"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s,#constituencyField_s)').hide();
	$('#Shead').css("display","block");
	$('#Dhead').css("display","block");
	$('#Chead').css("display","block");
}
if(scopeSelected=="MANDAL"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s,#constituencyField_s,#mandalField_s)').hide();
}
if(scopeSelected=="VILLAGE"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s,#constituencyField_s,#mandalField_s,#hamletField_s)').hide();
}
if(scopeSelected=="MUNICIPAL-CORP-GMC"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s,#constituencyField_s,#mandalField_s)').hide();
}
if(scopeSelected=="WARD"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s,#constituencyField_s,#mandalField_s,#hamletField_s)').hide();
}
if(scopeSelected=="BOOTH"){
	$('.selectBoxes > select').show();
	$('.selectBoxes > :not(#stateField_s,#districtField_s,#constituencyField_s,#mandalField_s,#boothField_s)').hide();
}

	if(scopeSelected == 'WARD' || scopeSelected == 'MUNICIPAL-CORP-GMC')
	{
		areaType = 'RURAL';
	}
	if(scopeSelected == 'HAMLET' ||  scopeSelected == 'MANDAL' || scopeSelected == 'VILLAGE')
	{
		areaType = 'URBAN';
	}
}



function getDistrictsOfState(stateId){
	var task="getDistricts";
	var jsObj=
			{
					locationId:stateId,
					task:task						
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getSublevelsOfMessageCenterAction.action?"+rparam;	
			callAjax1(rparam,jsObj,url);
}
function getConstituenciesOfDistrict(districtId){
	if(scopeSelected == 'WARD' || scopeSelected == 'MUNICIPAL-CORP-GMC')
	{
		areaType = 'RURAL';
	}
	var task="getConstituencies";
	var jsObj=
			{
					locationId:districtId,
					task:task,
					areaType:areaType
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getSublevelsOfMessageCenterAction.action?"+rparam;	
			callAjax1(rparam,jsObj,url);
}
function getSubRegionsOfConsti(constId){
	var task="subRegionsInConstituency";
	if(scopeSelected == 'WARD' || scopeSelected == 'MUNICIPAL-CORP-GMC')
	{
		areaType = 'URBAN';
	}
	else if(scopeSelected == 'HAMLET' ||  scopeSelected == 'MANDAL' || scopeSelected == 'VILLAGE'){
		areaType= 'RURAL';
	}
	else{
		areaType='';
	}
	var jsObj=
			{
					locationId:constId,
					task:task,
					areaType:areaType
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getSublevelsOfMessageCenterAction.action?"+rparam;	
			callAjax1(rparam,jsObj,url);
}
function getSubRegionsOfLocalBody(localId,name,constId){
	var task ="";
	var flag = name.search("Greater Municipal Corp");
	
	if(scopeSelected == 'BOOTH')
	{
		if(flag == '-1')
		{
			task="boothsInTehsilOrMunicipality";
		}else{
			task="hamletsOrWardsInRegion";
		}
	}
	if(scopeSelected == 'WARD' || scopeSelected == 'VILLAGE' ) {
		task="hamletsOrWardsInRegion";
	}
	
	var jsObj=
			{
					locationId:localId,
					task:task,
					name:name,
					constId:constId,
					areaType:''
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getSublevelsOfMessageCenterAction.action?"+rparam;	
			callAjax1(rparam,jsObj,url);
	
}

$('#stateField_s').change(function(){
	var stateId=$('#stateField_s option:selected').val();
	var state=$('#stateField_s option:selected').text();
	
	getSubLevelInfluenceData(stateId,state,"STATE","VILLAGE/WARD","",0,true);
});



$('#districtField_s').change(function(){
	var districtId=$('#districtField_s option:selected').val();
	var district=$('#districtField_s option:selected').text();
	
	reGetInfluencingPeopleInAConstituency('DISTRICT',districtId,district);
});

$('#constituencyField_s').change(function(){
	var constId=$('#constituencyField_s option:selected').val();
	var consti=$('#constituencyField_s option:selected').text();
	
	reGetInfluencingPeopleInAConstituency('CONSTITUENCY',constId,consti);
});
//getSubLevelInfluenceData(regionId,regionName,regionType,areaType,"",0,false)

function getSubLevelInfluenceData(regionId,regionName,regionType,areaType,regionTitle,regionTitleId,status)
		{
		
			var jsObj= 
			{	
				regionId:regionId,
				regionName:regionName,	
				regionType:regionType,
				regionTitle:regionTitle,
				regionTitleId:regionTitleId,
				areaType:areaType,
				status:status,
				task: "getSubLevelInfluencePeople"						
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/subLevelInfluenceAction.action?"+param;
			
			callAjax1(param,jsObj,url);		
		}
	function getConstituencyList(){

	var jsObj= 
		{	
			task:"getUserConstituencyNames"		
		};
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getConstituenciesByPartyNYearAction.action?"+param;
		callAjax1(param,jsObj,url);

}

function callAjax1(param,jsObj,url){

	var myResults;	
	var callback = {			
	    success : function( o ) {
			try {	
					if(o.responseText.length!=0){
						myResults = YAHOO.lang.JSON.parse(o.responseText);	
					}						
					if(jsObj.task == "getStates")
					{
						clearOptionsListForSelectElmtId("stateId");
						createOptionsForSelectElmtId("stateId",myResults);
					}
					else if(jsObj.task == "getDistricts")
					{
						clearOptionsListForSelectElmtId("districtField_s");
						createOptionsForSelectElmtId("districtField_s",myResults);	
					}	
					else if(jsObj.task == "getConstituencies")
					{
						clearOptionsListForSelectElmtId("constituencyField_s");
						createOptionsForSelectElmtId("constituencyField_s",myResults);
					}		
					else if(jsObj.task == "subRegionsInConstituency" )
					{
						clearOptionsListForSelectElmtId("mandalField_s");
						createOptionsForSelectElmtId("mandalField_s",myResults);
					}
					else if(jsObj.type == "hamletsOrWardsInRegion")
				    {
                        buildHamletsForOtherthanVoters(myResults);
					}
					
					else if(jsObj.task == "hamletsOrWardsInRegion")
					{
						var name=jsObj.name;
						var flag = name.search("Greater Municipal Corp");
						if(flag != '-1'){
							$('.selectBoxes #hamletField_s').show();
						}
						
						clearOptionsListForSelectElmtId("hamletField_s");
						createOptionsForSelectElmtId("hamletField_s",myResults);
					}
					else if(jsObj.task == "boothsInTehsilOrMunicipality1")
					{
						clearOptionsListForSelectElmtId("boothField_s");
						createOptionsForSelectElmtId("boothField_s",myResults);
					}
					else if(jsObj.task == "addNewPosition"){

					clearOptionsListForSelectElmtId("position");
					createOptionsForSelectElmtId1("position",myResults);
	                document.getElementById("positionId").innerHTML='';
					}else if(jsObj.task=="getSubLevelInfluencePeople"){
						buildRegionWiseInfluencePeople(myResults,jsObj)
					}else if(jsObj.task == "reGetInfluencingPeopleInAConstituency")
					{
						//getSubLevelInfluenceData(jsObj.regionId,jsObj.region,regionType,"VILLAGE/WARD","",0,true);
						
						buildRegionWiseInfluencePeopleForDistrictAndContsi(myResults,jsObj)
					}else if(jsObj.task=="getMobileNumbersOfSelectedId"){
						buildMobileNos(myResults,jsObj);
					}else if(jsObj.task == "getRecordingsOfUser")
				        buildResultForAudioFiles(myResults);
					else if(jsObj.task == "getVerifiedNumbersOfUser")
						 buildVerifiedNumbersForUser(myResults);
					else if(jsObj.task == "getVoiceSmsHistoryForAuser"){
						buildVoiceSmsHistory(myResults,jsObj.visibility);
					}
					else if(jsObj.task == "getResponseDetails")
						buildResponseDetails(myResults);

					else if(jsObj.task == "sendVoiceSms")
				    { 
							
							 selectedVotersDetails = {};

							 selectedCadreDetails = {};

							 selectedInfluencePeopleDetails = {};

							 selectedMobileNumbers = new Array();

							 $('#cadreCount , #influencePeopleCount, #voterCount').html(0)

						$('#responseDetailsDiv').dialog('close');
						$('#successMsg').html(myResults);

				    }
					else if(jsObj.task == "getConstituencyNames")
				    {
						 iterateDetailsNames(myResults);
				    }
					else if(jsObj.task == "getUserConstituencyNames")
				    {
						if(myResults.length == 1)
						{
							$('#publicationDateDiv,#mandalDiv,#panchayatDiv,#wardDiv,#pollingStationDiv,#hamletDiv').hide();

							return false;
						}
						 iterateUserConstiDetailsNames(myResults);
				    }					
					else if(jsObj.task == "influencingPeopleSearch")
				    {
						 alert(myResults);
				    }
					else if(jsObj.task == "getAllTheCastesOfConstituency")
				    {
                        buildCasteDetails(myResults);
					}
					else if(jsObj.task == "sendTextSms")
				    {
						alert(123);
					}
					else if(jsObj.task == "boothsInTehsilOrMunicipality"){
						buildBoothResultdForOtherthanVoter(myResults);
					}
					else if(jsObj.task == "getPariamentConstituencies"){
					
						iterateDetailsNames(myResults);
					}else if(jsObj.task == "getUserLocation"){
						buildRegionsSelectBoxes(myResults);
					}
					
			}catch (e) {   		
			   	//alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function iterateDetailsNames(result){
	var elmt = document.getElementById('constituencyList');
	var option = document.createElement('option');
	clearOptionsListForSelectElmtId('constituencyList');
	option.value="0";
	option.text="Select Constituency";
	try
	{
	elmt.add(option,null);	
	}
	catch (ex)
	{
		elmt.add(option);
	}
	for(var i in result)
	{		
		var option=document.createElement('option');		
		option.value=result[i].id;
		option.text=result[i].name;		
	try
	{
	elmt.add(option,null);	
	}
	catch (ex)
	{
		elmt.add(option);
	}
	}


}

function iterateUserConstiDetailsNames(result){
	var elmt = document.getElementById('constituencyList');
	var option = document.createElement('option');
	clearOptionsListForSelectElmtId('constituencyList');
	
	for(var i in result)
	{		
		var option=document.createElement('option');		
		option.value=result[i].id;
		option.text=result[i].name;		
	try
	{
	elmt.add(option,null);	
	}
	catch (ex)
	{
		elmt.add(option);
	}
	}


}
var mobileNoArr=[];
var contactNos=[];
var phoneNosArr=[];
var voterSearchPhoneNo=[];
var person;
var personArr = [];
//111

function buildMobileNos(results,jsObj){
	var str="";
	//str+='<h4 style="padding:15px;">Contacts for Sending SMS</h4>';
	
	/*for(var i in results){
	if($.inArray(results[i].mobileNO,mobileNoArr)==-1){
	str+='<div class="span12 row-fluid mainClass thumbnail" id="removeThis'+i+'" style="margin:1px;">';
	//str+='<span class="btn bnt-mini">'+results[i].mobileNO+'<span></span></span>';	
	str+='<div class="span6">'+results[i].cadreName+'</div><div class="span4">'+results[i].mobileNO+'</div><div class="span1"><img src="images/icons/delete.png"  style="margin:5px;height:12px;width:12px;" onclick="removeThis('+i+')"/></div></div>';
	mobileNoArr.push(results[i].mobileNO);
	contactNos.push('91'+results[i].mobileNO)
	}
	}
	$('#mobilesId').append(str);
	$('#mobileNumber').val(contactNos);
	
	*/
	
	for(var i in results){
		person=new Object();
		if($.inArray(results[i].mobileNO)==-1){
		
		str+='<div class="span12 row-fluid mainClass thumbnail" id="removeThis'+i+'" style="margin:1px;">';
		//str+='<span class="btn bnt-mini">'+results[i].mobileNO+'<span></span></span>';	
		str+='<div class="span6">'+results[i].cadreName+'</div><div class="span4">'+results[i].mobileNO+'</div><div class="span1"><img src="images/icons/delete.png"  style="margin:5px;height:12px;width:12px;" onclick="removeThis('+i+')"/></div></div>';
		mobileNoArr.push(results[i].mobileNO);
		person.name=results[i].cadreName;
		person.mobileNo = '91'+results[i].mobileNO;
		contactNos.push('91'+results[i].mobileNO);
		personArr.push(person);
		}
	}
	$('#mobilesId').append(str);
	//$('#mobileNumber').val(contactNos);
	validateContacts(personArr);
}

var notvalidContactArr;
var validContactArr;

/*function validateContacts(personArr){

	notvalidContactArr = new Array();
	validContactArr = new Array();

	$.each(personArr,function(i){
	
		if((personArr[i].mobileNo).length != 12){
			notvalidContactArr.push(personArr[i]);
		}
		else if((personArr[i].mobileNo).length == 12){
			validContactArr.push(personArr[i]);
		}
		
	});
	//console.log(validContactArr);

	if(validContactArr == '' && validContactArr.length==0)
		$('#mobileNumber').val('');
	if(validContactArr != '' && validContactArr.length!=0){
	for(var j in validContactArr){			
			$('#mobileNumber').val($('#mobileNumber').val()+validContactArr[j].mobileNo+',');
		}
	}
	var contants1 = ($('#mobileNumber').val());
	validContactArr=[];
	$('#mobileNumber').val(contants1);
	personArr.splice(0,personArr.length);
	
	if(notvalidContactArr != '' && notvalidContactArr.length>0)
	buildInvalidNumbers(notvalidContactArr);
}
*/
var searchType ='Booth';
function buildInvalidNumbers(notvalidContactArr){
	$('#notValidContactSpan').css('display','block');
	$('#notValidContact').html('');
	var str='';	
	str +='<table>';
	$.each(notvalidContactArr,function(i){
	var id='';
	if(searchType!='')	
		id = ","+searchType+ " id: "+notvalidContactArr[i].boothName;
	str +='<tr>';
	str +='<td><a class="invldNo'+i+'" onclick="validateEditedContacts('+i+');"><i class="icon-ok" title="Add to send sms"></i> </a></td>';
	str +='<td><span id="errMsg'+i+'"></span><input id="invldNo'+i+'" class="invldcontacts" type="text" maxlength="12" value="'+notvalidContactArr[i].mobileNo+'" style="margin-left: 10px; height: 15px; margin-top: 15px; width: 100px;" title="'+notvalidContactArr[i].name+''+id+'"></td>';
	str +='</tr>';
	});
	str +='</table>';
	$('#notValidContact').html(str);
	personArr.splice(0,personArr.length);
}

function validateEditedContacts(id){
	validContactArr = new Array();
	var editNo = $('#invldNo'+id).val();
		if(!isNaN(editNo) && editNo.length == 12){
			notvalidContactArr.splice(0,1)
			validContactArr.push(editNo);
			$('#invldNo'+id).remove();
			$('.invldNo'+id).remove();
			$('#errMsg'+id).remove();
		}
		else
		$('#errMsg'+id).html('<font class="requiredFont">*</font>');

	if(notvalidContactArr.length == 0){
		$('#notValidContactSpan').css('display','none');
	}
	if(validContactArr != '' && validContactArr.length > 0 ){
		if($('#mobileNumber').val().length == 0)
			$('#mobileNumber').val(validContactArr);
		else
		$('#mobileNumber').val($('#mobileNumber').val()+','+validContactArr);
		
		}
		
}

function removeThis(val){
	$('#removeThis'+i).html('');
}

function reGetInfluencingPeopleInAConstituency(regionType,regionId,region)
		{
			var jsObj= 
			{	
				regionId:regionId,
				regionType:regionType,
				region:region,
				task: "reGetInfluencingPeopleInAConstituency"
						
			};
			
			var param="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/influencingPeopleInConstituencyAction.action?"+param;
			
			callAjax1(param,jsObj,url);	
		}
		
function buildRegionWiseInfluencePeople(data,jsObj){
	$('#tableDiv').show();
	$('#tableDiv').html('');
	var results = data.regionWiseOverview;
	
	str="<table>";
	for(var i=0; i<results.length; i++)
	{
	
		var availableRegions = new Array();
		var zeroRegions = new Array();
		
		
		if(results[i].subRegionWiseOverview != null)
		{
			
			for(var k=0; k<results[i].subRegionWiseOverview.length; k++)
			{	
				
				if(results[i].subRegionWiseOverview[k].countValue == 0)
					zeroRegions.push(results[i].subRegionWiseOverview[k]);
				else 
					availableRegions.push(results[i].subRegionWiseOverview[k]);
			}

		}
		
		if(results[i].countValue!=0){
		str+="<tr><td>"+results[i].regionName+"("+results[i].regionType+")--";
		str += '<a href="javascript:{}" style="color:#77471D" class="regionCountAnc" onclick="openCandidatesPopup(\''+jsObj.regionId+'\',\''+results[i].regionId+'\',\''+results[i].regionName+'\',\''+results[i].regionType+'\',\'region\')">'+results[i].countValue+'</a></td><td>';
		
			for(var j=0; j<availableRegions.length; j++)
			{
				str+=""+availableRegions[j].subRegionName+"--";
				str+='<a href="javascript:{}" style="color:#77471D" class="regionCountAnc" onclick="openCandidatesPopup(\''+results[i].regionId+'\',\''+availableRegions[j].subRegionId+'\',\''+availableRegions[j].subRegionName+'\',\''+availableRegions[j].subRegionType+'\',\'region\')">'+availableRegions[j].countValue+'</a><br>';
			}
		}
		
	}
	str+="</td></tr></table>";
	
	
	$('#tableDiv').html(str);

}
function buildRegionWiseInfluencePeopleForDistrictAndContsi(data,jsObj){
	$('#tableDiv').html('');
	var reslts = data.regionWiseOverview;
	
	var results=[];
	results.push(reslts);
	str="<table class='table table-bordered'>";
	for(var i=0; i<results.length; i++)
	{
	
		var availableRegions = new Array();
		var zeroRegions = new Array();
		
		
		if(results[i].subRegionWiseOverview != null)
		{
			
			for(var k=0; k<results[i].subRegionWiseOverview.length; k++)
			{	
				
				if(results[i].subRegionWiseOverview[k].countValue == 0)
					zeroRegions.push(results[i].subRegionWiseOverview[k]);
				else 
					availableRegions.push(results[i].subRegionWiseOverview[k]);
			}

		}
		
		if(results[i].countValue!=0){
		str+="<tr><td>"+results[i].regionName+"("+results[i].regionType+")--";
		str += '<a href="javascript:{}" style="color:#77471D" class="regionCountAnc" onclick="openCandidatesPopup(\''+jsObj.regionId+'\',\''+results[i].regionId+'\',\''+results[i].regionName+'\',\''+results[i].regionType+'\',\'region\')">'+results[i].countValue+'</a></td><td>';
		
			for(var j=0; j<availableRegions.length; j++)
			{
				str+=""+availableRegions[j].subRegionName+"--";
				str+='<a href="javascript:{}" style="color:#77471D" class="regionCountAnc" onclick="openCandidatesPopup(\''+results[i].regionId+'\',\''+availableRegions[j].subRegionId+'\',\''+availableRegions[j].subRegionName+'\',\''+availableRegions[j].subRegionType+'\',\'region\')">'+availableRegions[j].countValue+'</a><br>';
			}
		}else{
		str+='<tr><td>No data Avialable'	
		}
		
	}
	str+="</td></tr></table>";
	
	
	$('#tableDiv').html(str);

}

function openCandidatesPopup(parentRegionId,regionId,regionName,regionType,scopeType)
{
	$('#notValidContact').html('');	
	$('#notValidContactSpan').css('display','none');
	var fromParent="messageCenter";
	var urlStr = "influencingPeopleDataActionForMessageCenter.action?windowTask=influencingPersonInfoPopup&parentRegionId="+parentRegionId+"&regionId="+regionId+"&regionName="+regionName+"&regionType="+regionType+"&scopeType="+scopeType+"&fromParent="+fromParent;
	var browser2 = window.open(urlStr,"influencingPersonInfoPopup2","scrollbars=yes,height=570,width=1300,left=200,top=50");	
	browser2.focus();
}

function buildSearchPagePopup(type)
{	
	var fromParent="messageCenter";
	var urlStr = "cadreSearchActionForMessageCenter.action?windowTask="+type+"&fromParent="+fromParent;
	var browser2 = window.open(urlStr,"cadreSearchAndSMSPopup2","scrollbars=yes,height=650,width=1100,left=150,top=100");	
	browser2.focus();

}

window.receiveFromChild = function(data) {

	var jsObj= 
		{	
			ids:data,
			task: "getMobileNumbersOfSelectedId"
		};
			
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getMobileNumbersOfSelectedId.action?"+param;
		callAjax1(param,jsObj,url);
};
window.receiveFromCadreChild = function(data) {
	var str='';
	/*console.log(mobileNoArr);
	//str+='<h4 style="padding:15px;">Contacts for Sending SMS</h4>';
	for( var i=0, l=data.length; i<l; i++ ) {
	if($.inArray(data[i].cadreMobile,mobileNoArr)==-1){
		str+='<div class="span12 row-fluid mainClass thumbnail" id="removeThis'+i+'" style="margin:1px;">';
		
		str+='<div class="span6">'+data[i].cadreName+'</div><div class="span4">'+data[i].cadreMobile+'</div><div class="span1"><img src="images/icons/delete.png"  style="margin:5px;height:12px;width:12px;" onclick="removeThis('+i+')"/></div></div>';
		mobileNoArr.push(data[i].cadreMobile);
		contactNos.push('91'+data[i].cadreMobile)
		}
	}
	console.log(mobileNoArr);
	$('#mobilesId').append(str);
	$('#mobileNumber').val(contactNos);*/
	
	contactNos.splice(0, contactNos.length);
	//console.log(data);
	for( var i=0; i<data.length; i++ ) {
		person = new Object();
		person.name= data[i].cadreName;		
		person.mobileNo= '91'+data[i].cadreMobile;
		person.boothName=data[i].cadreId;
		
	  voterSearchPhoneNo.push('91'+data[i]);
	  personArr.push(person);
	}
	
	//$('#mobileNumber').val(voterSearchPhoneNo);
	//validateContacts(voterSearchPhoneNo);
	//validateContacts(personArr);
	
	/* //str+='<h4 style="padding:15px;">Contacts for Sending SMS</h4>';
	for( var i=0, l=data.length; i<l; i++ ) {
	if($.inArray(data[i].cadreMobile)==-1){
		str+='<div class="span12 row-fluid mainClass thumbnail" id="removeThis'+i+'" style="margin:1px;">';
		
		str+='<div class="span6">'+data[i].cadreName+'</div><div class="span4">'+data[i].cadreMobile+'</div><div class="span1"><img src="images/icons/delete.png"  style="margin:5px;height:12px;width:12px;" onclick="removeThis('+i+')"/></div></div>';
		mobileNoArr.push(data[i].cadreMobile);
		contactNos.push('91'+data[i].cadreMobile)
		}
	}
	$('#mobilesId').append(str);
	//$('#mobileNumber').val(contactNos);
	validateContacts(contactNos); */
	
	
}

//2222
window.receiveFromVoterSearchChild = function(data) {
	
	
/*	for( var i=0; i<data.length; i++ ) {
	  voterSearchPhoneNo.push('91'+data[i])
	}
	
	$('#mobileNumber').val(voterSearchPhoneNo);
*/
	voterSearchPhoneNo.splice(0, voterSearchPhoneNo.length);
	//console.log(data);
	for( var i=0; i<data.length; i++ ) {
		person = new Object();
		person.name= data[i].name;		
		person.mobileNo= '91'+data[i].mobileNo;
		person.boothName=data[i].boothName;
		
	  voterSearchPhoneNo.push('91'+data[i]);
	  personArr.push(person);
	}
	
	//$('#mobileNumber').val(voterSearchPhoneNo);
	//validateContacts(voterSearchPhoneNo);
	//validateContacts(personArr);
}

</script>

<script>
function buildResultForAudioFiles(results)
{
   var noOfAudios = 0;
	$.each(results,function(key,value){

		 noOfAudios++;
	});


	if(noOfAudios == 0)
	{
		$('#audioFilesDiv').html("<h5 style='color:#06ABEA;;text-align:center;'>No Audio Files Exist.To Upload Audio <a href='uploadAudioFile.action' target='_blank'>Click here</a></h5>");
		return false;
	}
	var str='';
	


	str+='<div style="margin:5px 6px 0px 124px;border:1px solid #06ABEA;padding:4px;border-radius:4px;" class="widget blue whitegloss">';
	//str+='<a href="javascript:{ajaxToGetRecordingDetails()}"><img src="images/icons/refreshImg.png" alt="Processing Image" title="Click here to refresh audio files" style="float:right;padding:5px;"/></a>';


      var i=0;
	
	$.each(results,function(key,value){

		   str+='<label><input name="audio" type="radio" value="'+i+'" id="'+key+'"/><a href="javascript:{showAudio(\''+key+'\','+i+','+value.split("--")[1]+')}" title="'+value.split("--")[0]+'">'+key+'</a> -- '+value.split("--")[0]+'</label>';
                  i++;
	});

	str+='</div>';

	$('#audioFilesDiv').html(str);
}
function showAudio(path,i,userId)
{
	var str='';
	//$('#audioOuter').dialog();
	 str+='<embed src="voice_recordings/'+userId+'/'+path+'"  autostart=true loop=false>';

	 $('#audioInner').html(str);
	 	    $( "#audioOuter" ).dialog({
				title:'Play Audio',
				width:'auto',
				height:'auto',
				buttons: {
					"Select To Send SMS":function() {

						$("input:radio[value="+i+"]").attr("checked","checked");
						$(this).dialog("close");
						},
                    "Exit":function(){$(this).dialog("close");} 
				}
			});
}
function buildVerifiedNumbersForUser(results)
{ 
	var str='';

	  if(results == null || results.length == 0)
	  {
		  str +='<b>You Dont Have Any Verified Numbers.Please Contact Us To verify Mobile Number.</b>';
		  	$('#verifiedNumbersDiv').html(str);

		  return false;
					  
	  }

    str+='<div style="margin:5px 6px 0px 119px;border:1px solid #06ABEA;padding:4px;border-radius:4px;" class="widget blue whitegloss">';
	$.each(results,function(index,value){

		if(index == 0)
		 str+='<label style="font-weight:bold;"><input type="radio" checked name="senderNumber" value="'+value+'"/>'+value+'</label>';
		else
			str+='<label style="font-weight:bold;"><input type="radio" name="senderNumber" value="'+value+'"/>'+value+'</label>';
	});
	str+='</div>';
	$('#verifiedNumbersDiv').html(str);
}

function buildVoiceSmsHistory(results,visibility)
{
	var str='';
    str+='<div class="datagrid">';
    str+='<table border="1">';
	str+='<thead>'; 
	str+='<tr>';
	 str+='<th>Message Id</th>';
 	 str+='<th>Date Sent</th>';
 	 //str+='<th>Mobile Numbers</th>';
	 str+='<th>Description</th>';
	 str+='<th>Check Details</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	$.each(results,function(index,value){

	 str+='<tr>';
	  str+='<td>'+value.responseCode+'</td>';
	  str+='<td>'+value.dateSent+'</td>';
	  str+='<td>'+value.description+'</td>';

	  //str+='<td>'+value.numbers+'</td>';
	  //str+='<td><a href="javascript:{showMessageResponseDetails('+value.responseCode+');}">Click here For Details</a></td>';

	  str+='<td><a title="Click Here to See Details" href="javascript:{showMessageResponseDetails('+value.responseCode+');}"><img src="./images/icons/details.png"/></a></td>';
	  
	 str+='</tr>';
	});
	str+='</tbody>';
	str+='</table>';
	str+='</div>';

$('#smsHistory').html(str);

if(visibility == "hide")
  $('#smsHistory').hide();
else
  $('#smsHistory').show();
}

function buildResponseDetails(results)
{
	if(results.length == 0)
	{
     	$('#responseDetailsInnerDiv').html("Response is not generated.It will take some time.");
			$('#responseDetailsInnerDiv').html(str);

		return false;

	}

	var str='';

   str+='<div class="datagrid">';
	str+='<table>';
	str+='<thead>';
	 str+='<tr>';
	  str+='<th>S No</th>'
	  str+='<th>Mobile Number</th>'
	  str+='<th>Status</th>';
	 str+='</tr>';
	str+='</thead>';
	str+='<tbody>';

	$.each(results,function(index,value){
       var sNo = index +1;
        str+='<tr>';
		str+='<td>'+sNo+'</td>';
		 str+='<td>'+value.numbers+'</td>';
	     str+='<td>'+value.sentStatus+'</td>';
        str+='</tr>';

	});
	str+='</tbody>';
	str+='</table>';
	str+='</div>';

	$('#responseDetailsInnerDiv').html(str);
	
}

//voter search

function clearErrDiv(){
	$("#errorMsgAlert").html("");
	$("#AlertMsgs").html("");	
}

function getMandalOrMuncipalityList()
{
    var tempVar = "mandalList";
    var selectElmt = "mandalField";
    var reportLevelValue = $("#reportLevel").val();
	var constituencyId = $("#constituencyList").val(); 
	if(constituencyId == 0)
	return;
	else{
	if(reportLevelValue == 5)
     tempVar = "muncipalityList";
			
		var jsObj=
			{
				constituencyId:$('#constituencyList').val(),
				tempVar:tempVar,
				selectElmt:selectElmt,
				task:"getMandalOrMuncipalityList"
					
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getMandalOrMuncipalityListForVotersAnalysisAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
}

function clearFieldsData(){
		$("#errorMsgAlert").html("");
		var mandalId=document.getElementById("mandalField");
		var publicationDateId=document.getElementById("publicationDateList");
		var panchayatFieldId=document.getElementById("panchayatField");
		var wardFieldId=document.getElementById("wardField");
		var hamletFieldId=document.getElementById("hamletField");
		var pollingStationFieldId=document.getElementById("pollingStationField");
		var constiList = document.getElementById("constituencyList");
		removeSelectElements(pollingStationFieldId);
		removeSelectElements(hamletFieldId);
		removeSelectElements(mandalId);
		removeSelectElements(wardFieldId);
		removeSelectElements(constiList);
		//removeSelectElements(publicationDateId);
		removeSelectElements(panchayatFieldId);
		$('#constituencyList').val(0);
		$('#districtList').val(0);
	}

function showNewsDetails(){
  }
/*
function voterDetailsValidation()
{
  /* var id7 = $("#mandalField").val();
	       		  
				if(type !=  "ward" && id7.charAt(0) !="1"){
                  ColType1 = "Hamlet";
				}else{
                 ColType1 = "Ward";
				 muniId = $("#mandalField").val().substring(1);
				}*/
	/*$('#notValidContact').html('');	
	$('#notValidContactSpan').css('display','none');
		
	isMuncipality=false;	
	$('#errorMessageDiv').hide();
    $("#multipleVoterFamiliesEditDiv").html("");
	$("#errorMsgAlert").html("");
    var level = $("#reportLevel").val();
	 selectedLevel=level;
	var type = '';
	var id='';
	var publicationDateId ='';
	
	var str ='';
	id = $("#constituencyList").val();
	if(id == 0 ||id == null)
	{
		str +='<div>Please Select Constituency</div>';
		$("#errorMsgAlert").html(str);
		return;
	}
	if(level == 1){
		type = 'constituency';
		selectedType=type;
		selectedTypeId=id;	
	}
	if(level == 2 || level == 3 || level == 4 || level == 5 || level == 6 ){
	id = $("#mandalField").val();
			if(id == 0 || id == null)
			{
				str +='<div>Please Select Mandal</div>';
				$("#errorMsgAlert").html(str);
				return;
			}	
    }
    if(level == 3 || level == 6){
	  id = $("#panchayatField").val();
		if(id == 0 || id == null)
		{
			str +='<div>Please Select Panchayat</div>';
			$("#errorMsgAlert").html(str);
			return;
		}
	}
	if(level == 4){
	  id = $("#pollingStationField").val();
        if(id == 0 || id == null)
		{
			str +='<div>Please Select Polling Station</div>';
			$("#errorMsgAlert").html(str);
			return;
		}
	}
	if(level == 5){		
		 id = $("#wardField").val();
			if(id == 0 || id == null)
			{
				str +='<div>Please Select Ward</div>';
				$("#errorMsgAlert").html(str);
				return;
			}		
    }
	if(level == 6){
		id = $("#hamletField").val();
        if(id == 0 || id == null)
		{
			str +='<div>Please Select Hamlet</div>';
			$("#errorMsgAlert").html(str);
			return;
		}
	}
	
	publicationDateId = $("#publicationDateList").val();
	publicationId=publicationDateId;
	if(publicationDateId == 0 || publicationDateId == null){
	    str +='<div>Please Select Publication Date</div>';
		$("#errorMsgAlert").html(str);
		return;
	}
	
	var locationName = "";
	if(level == 1){
		type = 'constituency';
		id = $("#constituencyList").val();
		selectedType=type;
		selectedTypeId=id;
		locationName = $("#constituencyList option:selected").text();
	}
	else if(level == 2){
		type = 'mandal';
		id = $("#mandalField").val();
			if(id.slice(0,1)==1){
				isMuncipality=true;
			}
		selectedType=type;
		selectedTypeId=id.substring(1);
			if(id.charAt(0) =="1"){
				 selectedType = "muncipality";
				madalType = "muncipality";
			}
	 locationName = $('#mandalField option:selected').text();
   }
   else if(level == 3){
	  type = 'panchayat';
	  id = $("#panchayatField").val();
	  selectedType=type;
	  selectedTypeId=id;
	  locationName = $('#panchayatField option:selected').text();
	}
	else if(level == 4){
		 type = 'booth';
		 id = $("#pollingStationField").val();
		 selectedType=type;
	     selectedTypeId=id;
		 locationName = $('#pollingStationField option:selected').text();
	}
	else if(level == 5){
		 type = 'ward';
		 id = $("#wardField").val();
		 selectedType=type;	
		 locationName = $('#wardField option:selected').text();
	}
	else if(level == 6){
		 type = 'hamlet';
		 id = $("#hamletField").val();
		 selectedType=type;
	     selectedTypeId=id;
		 locationName = $('#hamletField option:selected').text();
	}

  var constituencyId = $("#constituencyList").val();
     
*/
 // getVoterDetails(constituencyId,publicationId,id,selectedType,locationName);
 /*  getVoterDetails(232,8,122992,"booth","Booth-201");
}*/



function getVoterDetails(constituencyId,publicationId,id,selectedType,locationName)
{
 
  var urlStr = "messageCenterVoterSearchAction.action?constituencyId="+constituencyId+"&publicationDateId="+publicationId+"&locationValue="+id+"&locationType="+selectedType+"&locationName="+locationName+"&";
  var browser2 = window.open(urlStr,"messageCenterVoterSearch","scrollbars=yes,height=570,width=1300,left=200,top=50");	
  browser2.focus();  
}

function openCadreWindow()
{
	var str='';
		$.each(sublevelArr, function(idx, obj){ 
			$.each(obj, function(name, value){
				str+='<option value="'+ value +'">'+ name +'</option>';
			});
		});
		$("#subLevelsId").html(str);
		
		$('.selectBoxes,#sublevels').css('display','none');
		
		buildSearchPagePopup("Search");
}

function showTermsAndConditiond()
{
  $('#termsAndConditionsDialog').dialog({
	  title:'Terms And Conditions',
      width:550,
	  buttons: {
		"Accept":function(){$('#termsAndConditions').attr('checked',true);$(this).dialog("close");} ,
		"Exit":function(){$(this).dialog("close");} 
	}
  });
}

function showReportsLevels(value)
{
	$('.selectDivs').hide();
	$('#constiTypeDiv').hide();
	var selectedType = $('input:radio[name=searchFor]:checked').val();

	if(selectedType != "voter"){
          $('#publicationDateDiv,#panchayatDiv').hide();
		  $('#stateDiv').show();
		  if(selectedType =="cadre" && $('#reportLevel').val() == 1)
				$('#constiTypeDiv').show();
				
	}else
	{
		if(value == 2 || value == 3 || value == 4 || value == 5 || value == 6)
			$('#publicationDateDiv').show();

		 $('#stateDiv,#districtDiv').hide();
	}
	if(selectedType =='voter'){
	$('#publicationDateDiv,#panchayatDiv').show();
	if(value == 1)
		$(" #ConstituencyDiv,#constiDiv").show();
	else if(value == 2)
		$(" #ConstituencyDiv,#constiDiv,#mandalDiv").show();
	else if(value == 3)
		$("#ConstituencyDiv,#constiDiv,#mandalDiv,#panchayatDiv").show();
	else if(value == 4)
		$("#ConstituencyDiv,#constiDiv,#mandalDiv,#pollingStationDiv").show();
	else if(value == 5)
		$("#ConstituencyDiv,#constiDiv,#mandalDiv,#wardDiv").show();
	else if(value == 6){
		$("#ConstituencyDiv,#constiDiv,#mandalDiv,#hamletDiv").show();
	getMandalOrMuncipalityList($('#constituencyList').val());
		if(selectedType == "voter"){
		   $('#panchayatDiv').show();
		}
	}
	else if(value == 8)
		$("#stateDiv ").show();
	else if(value == 9)
		$("#stateDiv ,#districtDiv").show();
	}
	else{

	if(value == 1)
		$("#stateDiv , #districtDiv , #ConstituencyDiv,#constiDiv").show();
	else if(value == 2)
		$("#stateDiv , #districtDiv , #ConstituencyDiv,#constiDiv,#mandalDiv").show();
	else if(value == 3)
		$("#stateDiv , #districtDiv , #ConstituencyDiv,#constiDiv,#mandalDiv,#panchayatDiv").show();
	else if(value == 4)
		$("#stateDiv , #districtDiv , #ConstituencyDiv,#constiDiv,#mandalDiv,#pollingStationDiv").show();
	else if(value == 5)
		$("#stateDiv , #districtDiv , #ConstituencyDiv,#constiDiv,#mandalDiv,#wardDiv").show();
	else if(value == 6){
		$("#stateDiv , #districtDiv , #ConstituencyDiv,#constiDiv,#mandalDiv,#hamletDiv").show();
		getMandalOrMuncipalityList($('#constituencyList').val());
		if(selectedType == "voter"){
		   $('#panchayatDiv').show();
		}
	}
	else if(value == 8)
		$("#stateDiv ").show();
	else if(value == 9)
		$("#stateDiv ,#districtDiv").show();
	
	}
	
}
function sendSMS()
{
	
	if($("input:radio[name=smsType]:checked").val() == "text")
		sendTextSms();
	else
		validateFieldsForSendingSms();


}

function sendTextSms()
{
	$('#errorMsg').html("");

	var cadreDetails = new Array();
	var influencePeopleDetails = new Array();
	var votersDetails = new Array();
	var otherNumbers = new Array();

	$.each(selectedCadreDetails, function(index, value) {
		cadreDetails.push(value);
    });

    $.each(selectedInfluencePeopleDetails, function(index, value) {
		influencePeopleDetails.push(value);
    });

	$.each(selectedVotersDetails, function(index, value) {
		votersDetails.push(value);
    });

    if($('#textSmsDescription').val().trim() == "")
	{
		$('#errorMsg').html("<font style='font-weight:bold;color:red;'>Please Enter The Message To Send.</font>");
		return false;
	}
  if($('#mobileNumber').val().trim() != "")
  {

	  var othrNums= $('#mobileNumber').val().split(",");
	 
	 for(var i=0;i<othrNums.length;i++)
		 otherNumbers.push(othrNums[i].substring(2));
 
  }


	var jsObj=
			{
				task:"sendTextSms",
                cadreDetails:cadreDetails,
                influencePeopleDetails:influencePeopleDetails,
                votersDetails:votersDetails,
                otherNumbers:otherNumbers,
				message: $('#textSmsDescription').val()


			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "sendTextSMS.action?"+rparam;	
			callAjax1(rparam,jsObj,url);

}

function getAllTheCastesOfConstituency(value)
{


	if($('input[name=searchFor]:checked').val() != "voter")
		return false;

	if(value == 0)
	return;
	else{
		var jsObj=
			{
				task:"getAllTheCastesOfConstituency",
                constituencyId:value,
               // publicationDateId:$('#publicationDateList').val()
				 publicationDateId:8


			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getAllTheCastesOfConstituency.action?"+rparam;	
			callAjax1(rparam,jsObj,url);
	}

}
var castes = [];
function compareThings(castes1, castes2) {
    var name1 = castes1["name"].toLowerCase();
    var name2 = castes2["name"].toLowerCase();
    if (name1 < name2) {
        return -1;
    }
    if (name1 > name2) {
        return 1;
    }
    return 0;
}

function buildCasteDetails(casteDetails)
{
	$('#casteId').find('option').remove();
	$.each(casteDetails,function(index,value){
		var caste = {"name" : value, "number" : index};
		castes.push(caste);
	});
	castes.sort(compareThings);
	
	for(var i in castes){
	  $('#casteId').append('<option value="'+castes[i].number+'">'+castes[i].name+'</option>');
	 }

	
}


function showHideLocationOptionsForOtherthanVoter()
{

	var selectedValue = $('#reportLevel').val();

   $('.selectDivs').hide();
   if(selectedValue == 8)
	    $('#stateDiv').show();
   else if(selectedValue == 9)
	   $('#stateDiv,#districtDiv').show();
   else if(selectedValue == 1)
	   $('#stateDiv,#districtDiv,#ConstituencyDiv,#constiDiv').show();
   else if(selectedValue == 2)
	   $('#stateDiv,#districtDiv,#ConstituencyDiv,#constiDiv,#mandalDiv').show();
   else if(selectedValue == 4){
	   	   $('#stateDiv,#districtDiv,#ConstituencyDiv,#constiDiv,#mandalDiv,#pollingStationDiv').show();
		   $('#mandalField').change();
   }
   else if(selectedValue == 5)
	   	   $('#stateDiv,#districtDiv,#ConstituencyDiv,#constiDiv,#mandalDiv,#wardDiv').show();
   else if(selectedValue == 6){
	   	   $('#stateDiv,#districtDiv,#ConstituencyDiv,#constiDiv,#mandalDiv,#hamletDiv').show();
		   $('#mandalField').change();
   }
    else if(selectedValue == 3)
	   	   $('#stateDiv,#districtDiv,#ConstituencyDiv,#constiDiv,#mandalDiv').show();

}


function showHideLocationOptionsForVoter()
{
	var selectedValue = $('#reportLevel').val();

   // $('#ConstituencyDiv').show();
   $('.selectDivs').hide();

    if(selectedValue == 1)
	   $('#ConstituencyDiv,#constiDiv').show();
   else if(selectedValue == 2)
	   $('#ConstituencyDiv,#constiDiv,#mandalDiv').show();
   else if(selectedValue == 4){
	   	   $('#ConstituencyDiv,#constiDiv,#mandalDiv,#pollingStationDiv').show();
		   		   $('#mandalField').change();

   }
   else if(selectedValue == 5)
	   	   $('#ConstituencyDiv,#constiDiv,#mandalDiv,#wardDiv').show();
   else if(selectedValue == 6)
	   	   $('#ConstituencyDiv,#constiDiv,#mandalDiv,#hamletDiv').show();

}


function getBoothDetailsForCadre()
{

	var jsObj=
			{
				 task:"boothsInTehsilOrMunicipality",
                 address:"currentAdd",
				 constId:$('#constituencyList').val(),
				 id:$('#mandalField').val(),
				 isParliament:null,
				 selectElementId:"boothField",
				 taskType:"cadreReg"


			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "locationsHierarchiesAjaxAction.action?"+rparam;	
			callAjax1(rparam,jsObj,url);

}

function getHamletsInMandal()
{

	var jsObj=
			{
		         type:"hamletsOrWardsInRegion",
				 task:"hamletsOrWardsInRegion",
                 address:"currentAdd",
				 id:$('#mandalField').val(),
				 isParliament:null,
				 selectElementId:"villageField",
				 taskType:"cadreReg",
				 areaType:null


			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "locationsHierarchiesAjaxAction.action?"+rparam;	
			callAjax1(rparam,jsObj,url);

}

function getBooths()
{
	

		if($('input[name=searchFor]:checked').val() == "voter" || $('#reportLevel').val() == 5){
		   getPanchayatOrWardsList('panchayat','panchayatField');
		   getPanchayatOrWardsList('pollingstationByPublication','pollingStationField');
		   getPanchayatOrWardsList('ward','wardField');
		}
		else{
			if($('#reportLevel').val() == 6)	
                getHamletsInMandal();
			else	
  			   getBoothDetailsForCadre();
		}
	
}

function buildBoothResultdForOtherthanVoter(results)
{
	$('#pollingStationField').find('option').remove();

	$.each(results , function(index,value){
		$('#pollingStationField').append('<option value="'+value.id+'">'+value.name+'</option>');

	});
}

function buildHamletsForOtherthanVoters(results)
{

	$('#hamletField').find('option').remove();

	$.each(results , function(index,value){
		$('#hamletField').append('<option value='+value.id+'>'+value.name+'</option>');

	});
}

function openSmsHistoryWindow()
{
 
  var urlStr = "voiceSmsHistory.action";
  var browser2 = window.open(urlStr,"voiceSmsHistory","scrollbars=yes,height=570,width=1300,left=200,top=50");	
  browser2.focus(); 

}


function removeCadre()
{

	 selectedCadreDetails = {};

	 $.each(selectedCadreDetails, function(index, value) {
	 var numberIndex =  window.opener.selectedMobileNumbers.indexOf(value);
         selectedMobileNumbers.splice(numberIndex, 1);
     });

	 $('#cadreCount').html(0);

}

function removeInfluencePeople()
{
		 	//console.log(selectedCadreDetails);

	 selectedInfluencePeopleDetails = {};

	 $.each(selectedInfluencePeopleDetails, function(index, value) {
	 var numberIndex =  window.opener.selectedMobileNumbers.indexOf(value);
         selectedMobileNumbers.splice(numberIndex, 1);
     });

	 $('#influencePeopleCount').html(0);

}

function removeVoters()
{
	selectedVotersDetails = {};

	$.each(selectedVotersDetails, function(index, value) {
	 var numberIndex =  window.opener.selectedMobileNumbers.indexOf(value);
         selectedMobileNumbers.splice(numberIndex, 1);
     });
	 $('#voterCount').html(0);
}

function sendDirectSMSToVoters()
{
   getCandidatesData("direct");
}

function sendDirectSMSToVotersAjax()
{
	  var jsObj=
			{
				 isAgeSelected:isAgeSelected,
                 isFamilySelected:isFamilySelected,
				 isNameSelected:isNameSelected,
				 isGenderSelected:isGenderSelected,
                 isCasteSelected:isCasteSelected,  
				 startAge:startAge,
				 endAge:endAge,
				 houseNo:houseNo,
                 name:searchName,
				 casteIds:casteIds,
				 gender:gender,
				 locationType:searchArea,
				 locationValue:areaId,
				 publicationDateId:publicationDateId	,
				 senderMobileNumber:$("input:radio[name=senderNumber]:checked").val(),
				 audioFileName : $("input:radio[name=audio]:checked").attr('id'),
				 description : $('#voiceSmsDescription').val(),
                 message:$('#textSmsDescription').val(),
                 smsType:$("input:radio[name=smsType]:checked").val()

			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "sendVoiceSMSDirectlyToVoters.action?"+rparam;	
			callAjax1(rparam,jsObj,url);
}
getCandidateLocationDetails();
buildReportLevelValues();

function buildReportLevelValues()
{

	if(accessType == "DISTRICT"){	
		$('#reportLevel option:eq(0)').remove();
		$('.soption').hide();
	}
    else if(accessType == "MLA"){	
		$('.soption , .doption ,.parlimntRdio').remove(); 
		$('#constituencyList').val(1);
	}
	else if(accessType == "MP"){	
		$('.soption ,.doption,#districtDiv').remove(); 
		$('.soption ,.doption,#districtDiv').remove();
		$('#constituencyList').val(1);
	}

	if('${areaType}' == "URBAN")
	{
		$('.hoption').remove();
	}else if('${areaType}' == "RURAL")
	{
		$('.woption').remove();
	}

}

function populateConstituencies()
{

$('#constituencyList').find('option').remove();
$('#constituencyList').append('<option value="0">Select Constituency</option>')   

	 if(accessType == "MP"){
		 <c:forEach items="${parliamentConstituencyList}" var="item">
			  $('#constituencyList').append('<option value="${item.id}">'+'${item.name}'+'</option>')   
        </c:forEach>
	 }
	else{
		<c:forEach items="${constituencyList}" var="item">
               $('#constituencyList').append('<option value="${item.id}">'+'${item.name}'+'</option>');		  
        </c:forEach>
			if($('#reportLevel').val() == 1)
			  $('#constiTypeDiv').show();
        	else
				$('#constiTypeDiv').hide();
	}

}
</script>
 </body>
 </html>