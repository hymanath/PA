<!DOCTYPE html>
<html>
<head>
	<title> TDP News Portal </title>
	<meta name="" content="">
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="css/style.css">
	<!-------PT-sans font---->
	<!-- <link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'> -->
	<link  rel="stylesheet" type="text/css" href="js/jquery.google.api/googleAPIStyles.css"/>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
	<script type="text/javascript" src="js/jquery.google.api/jquery.min.js"></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"> </script>
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityDateOperations.js"> </script>
	<!--<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>-->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.carousel.js"></script>
	
	<!--<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>-->
	<script src="js/partyWiseNewsDisplay.js"></script>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	<script src="js/sendUpdatesByEmail.js"></script>
	<!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>-->
	
	<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
	<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 

	<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" /> -->
<link  rel="stylesheet" type="text/css" href="js/jquery.google.api/jquery-ui.css"/>

	<style type="text/css">
	select {
    background-color: #FFFFFF;
    border: 1px solid #F3E81E;
    width: 130px;
	}
	#requiredValue{
	color:red;
	font-size:large;
	}	

	.tdWidth1{
	width: 92px;
	}
	#boxHeading1{
	color: #66B16B;
    font-family: 'PT Sans',sans-serif;
    font-size: 16px;
	font-weight: bold;
	height: 40px; 
	
	}
	@font-face
	{
	font-family:eFont;src: url('img/eenadu.ttf');
	 }
	 .enadu
	{
	font-family: eFont;
	font-size:20px;
	}
	@font-face{ font-family: 'eFont'; src: url('fonts/eenadu.eot');}
@font-face {
    font-family: "eFont";
    font-style: normal;
    font-weight: normal;
    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
}

	#existingFromText,#existingToText{width:155px;cursor: text;}
	#errorMsgDiv{font-size:12px;}
	</style>
</head>
<body>
		<!----Container---->
		<div class="container">
		<!--------- Row-1 -------->
			<div class="row m_top10">
				<div class="span2">
				<div id="mainDiv"></div>
				
				  <div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>${scope} News</h4>
						<div class="row-fluid widget">						
						<div class="span12">
						<table  id="showScopeSubsD">	
								<tr>
								<td class="tdWidth1">District:<font id="requiredValue" class="requiredFont">*</font></td>
								</tr>
								<tr>
								<td><select id="userAccessDistrictList" class="selectWidth" name="userAccessDistrictList" onchange="addCssStyle();"></td>	 
							</tr>
						</table>
						<table  id="showScopeSubsC">	
								<tr>
								<td class="tdWidth1">Constituency:<font id="requiredValue" class="requiredFont">*</font></td>
								</tr>
								<tr>
								<td><select id="userAccessConstituencyList" class="selectWidth" name="userAccessConstituencyList" onchange="addCssStyle();"><!-- onchange="getMandalList(this.options[this.selectedIndex].value);">-->
								</select></td>	 
							</tr>
						</table>
						
						<button id="sendButton" class="btn btn-warning" onclick="addCssStyle(),getNewsForPagination(0)" style="margin-bottom: 15px; font-weight:bold;" > View News</button> 
						</div>
				</div>
						</div>
					</div>
				</div>
				<!---View your Constituency News Div--->
				<div class="span7">
					<div class="row-fluid widget">
						<div class="span12 boxHeading" id="boxHeading1"><h4> ${locationName} ${scope} Latest  News Updates</h4></div>
						<div class="span12">
							<div id="imageForMail"  class = "span3"  style="display:none;font-weight:bold;color: #0174DF;height:20px;width:500px;">
								<font>Please wait...</font>
								<img src="images/icons/goldAjaxLoad.gif" style="width: 150px; height: 15px;" width="18" height="11"/>
							</div>
							<div id="newsDispalyId"></div>
						</div>
						
						<!----pagination Div----->
						<div class="span12 text-center">
							<div id="paginationId"></div>
						</div>
						<!-----pagination Div end---->
					</div>
				</div>
				<!-----View your Constituency News End------>
				<!-----All News DIv------>
				<div class="span3" style="height:554px">
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>All News</h4></div>
						<div class="span12">
							<ul class=" nav nav-list bs-docs-sidenav">
								<!-- <li>
									<h6>News Main Title</h6>
									<p>
About Gmail - email from Google

Video chat with a friend, or give someone a ring all from your inbox. See more reasons to switch or check out our newest features. <p>
								</li>-->
				<div id="errorMsgDiv" style="float: right; margin-right: 94px; margin-top: 10px; width: 106px; margin-bottom: 10px;"></div>
		       <p> From Date: <input type="text" id="existingFromText" class="dateField" readonly="true" name="fileDate" size="20"/></p>	
			   <p>To Date: <input type="text" id="existingToText" class="dateField" readonly="true" name="fileDate" size="20"/></p>
			<input type="button" value="Get News" id="selectedNewsDetBtn" onclick="getNewsForPagination(0)" class="btn btn-info"/>
							</ul>
						</div>
					</div>
				</div>
				<!-----All News DIv End ------>
			</div>
		<!--------- Row-1 End -------->
		
		<!----<div class="row">
			<div class="span12">
				
			</div>
		</div>---->
		
	</div>
<Script type="text/javascript">
var scope = '${scope}';
var locationName = '${locationName}';
var locationValue = '${locationValue}';
var partyName = '${partyName}';
var partyId = '${partyId}';

getNewsForPagination(0);
hideDivs();
function hideDivs(){
	if(scope == "District"){
		$('#showScopeSubsD').css("display","block");
		$('#showScopeSubsC').css("display","none");
		//getAllConstituenciesInDistrictByType(1);

		getLocationNewsDetails(scope);
		//getLocationWiseNewsDetails(scope,"userAccessDistrictList");
	}
	if(scope == "Constituency"){
		$('#showScopeSubsC').css("display","block");
		$('#showScopeSubsD').css("display","none");

		getLocationNewsDetails(scope);
		//getAllConstituenciesInStateByType(2, 1, 'constituency');
		//getLocationWiseNewsDetails(scope,"userAccessConstituencyList");
	}
}

function getLocationNewsDetails(value){
	var jsObj =
		{   
		    scope:value,
			task:"getLocationNewsDetails"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationNewsDetailsAction.action?"+rparam;					callsAjax(jsObj,url); 
}

function getNewsForLocation()
 {
	 $("#existingFromText").val('');
	 $("#existingToText").val('');
	var scopes = scope;
	if(scopes == "District"){				
		scopeIdVal = document.getElementById("userAccessDistrictList").value;	
		locationName = $('#userAccessDistrictList option:selected').text();
	}
	if(scopes == "Constituency"){
		scopeIdVal = document.getElementById("userAccessConstituencyList").value;	
		locationName = $('#userAccessConstituencyList option:selected').text();
	}
	if(scopeIdVal != '0'){
	$('#boxHeading1').html('');
	$('#boxHeading1').css("padding-top","9px");
	$('#boxHeading1').html(locationName +' ${scope} Latest News Updates');
	var queryType='Public';
        var jsObj =
		    {   
				locationType:scope,
				locationId:scopeIdVal,
				partyId:partyId,
				startRecord:0,
			    maxRecord:10,
				queryType:queryType,
				fromDate:"",
				toDate:"",
				task:"getPartyWiseNewsToDisplay"
		    };
	 
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyWiseNewsDetailsForALocation.action?"+rparam;	
	$('#imageForMail').css("display","block");
	callsAjax(jsObj,url);  
	}
 }

 function getSelectedNewsDetails()
{
	$("#errorMsgDiv").html('');
	
	var fromDate = $("#existingFromText").val();
	var toDate = $("#existingToText").val();
	if(fromDate=="" && toDate == "")
	{
		$("#errorMsgDiv").html('Please Select From And To Dates');
		return;
	}
	else if(fromDate =="")
	{
	  $("#errorMsgDiv").html('Please Select From Date');
		return;
	}
	else if(toDate =="")
	{
	  $("#errorMsgDiv").html('Please Select To Date');
		return;
	}
	/* else if (Date.parse(fromDate) > Date.parse(toDate)) {
      $("#errorMsgDiv").html('Invalid Date Selection.');
      return;
	}  */

	
	/* var urlstr = "selectedNewsDetailsAction.action?fromDate="+fromDate+"&toDate="+toDate+"&";
	
    var browser1 = window.open(urlstr,"newsDetails"+fromDate+"And"+toDate+"","scrollbars=yes,height=600,width=1050,left=200,top=200");	
    browser1.focus(); */

	var queryType='Public';
        var jsObj =
		    {   
				locationType:scope,
				locationId:locationValue,
				partyId:partyId,
				startRecord:0,
			    maxRecord:10,
				queryType:queryType,
				fromDate:fromDate,
				toDate:toDate,
				task:"getPartyWiseNewsToDisplay"
		    };
	 
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyWiseNewsDetailsForALocation.action?"+rparam;						
	callsAjax(jsObj,url);   
	

}
  $(".dateField").live("click", function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
      changeYear: true,
		maxDate: new Date(),
		onSelect:function(date1,date2){	
			isDateValid();	
		}
		
	}).datepicker("show");
});

function getNewsForPagination(num)
 {
	var locValue = "";
	var locationName1 ="";
	if(scope  == "District")
	{
		locValue = $("#userAccessDistrictList option:selected").val();
		locationName1 = $('#userAccessDistrictList option:selected').text();
	}
	else
	{
		locValue = $("#userAccessConstituencyList option:selected").val();
		locationName1 = $('#userAccessConstituencyList option:selected').text();
	}
	if(locationName1 !=""){
	  locationName = locationName1;
	}
	if(locValue == null)
		locValue = locationValue;
		if(locValue == 0)
		return;
		$('#boxHeading1').html('<h4>'+locationName +' '+scope +' Latest News Updates</h4>');
	$('#imageForMail').css("display","block");
	var fromDtr = $('#existingFromText').val();
	var toDtr = $('#existingToText').val();
	var jsObj =
	{   
		locationId:locValue,
		startRecord:num,
		maxRecord:10,
		queryType:scope,
		fromDate:fromDtr,
		toDate:toDtr,
		task:"getPartyWiseNewsToDisplay"
	};
	 
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyWiseNewsDetailsForALocation.action?"+rparam;						
	callsAjax(jsObj,url);  
 }
</script>	
</body>
</html>