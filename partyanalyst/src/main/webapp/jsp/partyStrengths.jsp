<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/skin.css"> 

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>  

<!-- JQuery files (Start) -->
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<!--CSS files (default YUI Sam Skin) -->
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
 
<!--JS files Dependencies -->
<script src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/json/json-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/get/get-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>

<script type="text/javascript" src="https://www.google.com/jsapi"></script> 
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
<script type="text/javascript" src="js/partyStrength.js"></script>

<% if(request.getRequestURL().indexOf("partyanalyst.com") != -1){
%>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>
<% }
%>
	
<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">

<title>Party Strengths and Weakness</title>
<style type="text/css">
	table.CandidateElectionResultsTable{
		font-family: verdana,arial,sans-serif;
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #666666;
		border-collapse: collapse;
	}
	
	#partyStrenghtsTable 
	{
	 background: none repeat scroll 0 0 #FFFFFF;
    border: 2px solid #D3D3D3;
    margin-left: auto;
	margin-right:auto;
    margin-top: 15px;
    padding: 25px;
    width: 996px;
	
	}
	table #partyStrenghtsTable {border-collapse: separate;}
	#remaining_const_body, #new_const_body, #required_const_body  {
	    border-bottom: 1px solid #E0E0D6;
	    border-left: 1px solid #E0E0D6;
	    border-right: 1px solid #E0E0D6;
	  
	    overflow: auto;
	}
	
	#headingDiv
	{
		color: #247CD4;
	    font-size: 19px;
	    font-weight: bold;
	    text-align: center;
	    padding-bottom: 29px;
    	padding-top: 22px;
	}
	.tdStyle
	{
		font-weight:bold;
		color:#808080;
		font-size:12px;
	}
	.detailReportHeader 
	{
	    background-image: url("images/icons/partyPerformance/detailReportHeading.png");
	    color: #FBAD2B;
	    font-family: Trebuchet MS;
	    font-size: 13px;
	    font-weight: bold;	
	    margin-bottom:4px;
	    padding: 5px 5px 6px 15px;
	    width: 519px;
	    height: 23px;
	}
	.partyStyle
	{
	    color: #FBAD2B;
	    font-family: Trebuchet MS;
	    font-size: 14px;
	    font-weight: bold;
	}
	.viewAndHideDetails 
	{
	    color: #FFFFCC;
	    cursor: pointer;
	}
	
	.completePartyDetails {
	    background-image: url("images/icons/partyPerformance/reportHeaders.png");
	    font-size: 13px;
	    font-weight: bold;
	    height: 30px;
	    margin-bottom: 10px;
	    padding-left: 10px;
	    padding-right: 10px;
	    padding-top: 5px;
	    width: 790px;
	}
	.headerStyle
	{
		color:#247CD4;
		font-weight:bold;
		padding-left:18px;
	}
	
	#busyImage
	{
		border:1px solid #ADADAD;
		font-size:12px;
		font-weight:bold;
		width:300px;
		margin-bottom:10px;
		
	}
	.cursorStyle
	{
		color:#69A74E;
		font-weight:bold;
		cursor:pointer;
	}
	
	#required_const_body table
	{
		width:100%;
	}
	.districtPageRoundedHeaders_center{width:842px;}
</style>

<script type="text/javascript">

	var electionType='Assembly';
	var selectedStateElmts=1;
	var selectedPartyId=8;
	var selectedPartyName;
	var partyId;
	var selectedElecId=0;
	var latestElectionYear = 2009;
	var matchingCriteria;
	
	google.load("visualization", "1", {packages:["corechart"]});
	
	function callAjax(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
				"",					
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "getStatesAjaxAction")
					{					
						buildStates(results);					
					}
					if(jsObj.task == "getDefaultDetails")
					{	
						
						var main_container_div_elmt = document.getElementById("main_container_div");						
						main_container_div_elmt.style.display = 'block';

						var elmt = document.getElementById("busyImage");
						if(elmt)
							elmt.style.display = 'none';
				
						buildOverViewDataTable(results); 
						
						if(results.requiredConstituenciesInfo.partiesStrengthsInfoVO ==null || results.requiredConstituenciesInfo.partiesStrengthsInfoVO.length ==0){
							showRequiredConstituencies(results);
							// showRequiredConstituenciesErrorMessage(results);
							hideImg();
						}else{
							
							buildDefaultDetails(results);							
							if(results.partyName=="All Parties"){
								initializeResultsTable(results,"dataTableId","dataTableMainDiv","others");
								//initializeTable(results);
							}
							else{
								initializeResultsTable2(results,"dataTableId","dataTableMainDiv");
								//initializeTable(results);
							}
						}
						
						if(results.latestConstituenciesInfo.partiesStrengthsInfoVO==null || results.latestConstituenciesInfo.partiesStrengthsInfoVO.length ==0){
							hideLatestConstituenciesDiv();
						}else{
							var elmt3 = document.getElementById("new_const_main");
							elmt3.style.display = 'block';
							buildDefaultDetailsForNewConstituencies(results);
							if(results.partyName=="All Parties"){
								initializeResultsTable(results,"dataTableId_latestConstituencies","dataTableMainDiv_latestConstituencies","without_others");
							}else{
								initializeResultsTable2(results,"dataTableId_latestConstituencies","dataTableMainDiv_latestConstituencies");
							}
							
							interactiveChartForNewConstituencyResults(results.partyOverView);
						}
						
						if(results.remainingConstituenciesInfo.partiesStrengthsInfoVO==null || results.remainingConstituenciesInfo.partiesStrengthsInfoVO.length ==0){
							hideRemainingConstituenciesDiv();
						}else{
							var elmt2 = document.getElementById("remaining_const_main");
							elmt2.style.display = 'block';
							buildDefaultDetailsForRemianingConstituencies(results);
							if(results.partyName=="All Parties"){
								initializeResultsTable(results,"dataTableId_remainingConstituencies","dataTableMainDiv_remainingConstituencies","without_others");
							}else{
								initializeResultsTable2(results,"dataTableId_remainingConstituencies","dataTableMainDiv_remainingConstituencies");
							}	
						}																		
					}
					if(jsObj.task == "getDefaultElectionYearsAjaxAction")
					{
						buildDefaultElectionYears(results);
					}
					if(jsObj.task == "getAllElectionsAjaxAction")
					{					
						buildElectionYears(results);			
					}
					if(jsObj.task == "getAllPartiesData")
					{										
						buildAllPartiesData(results);			
					}
					if(jsObj.task == "getAllAllianceYearsForAPartyAjaxAction")
					{										
						buildAllAllianceElectionYears(results);			
					}						
					if(jsObj.task =="getExcludingAllianceDataAjaxAction")
					{
						buildExcludingAllianceData(results);	
					}
					if(jsObj.task =="getIncludingAllianceDataAjaxAction")
					{
						//buildExcludingAllianceData(results);	
						buildIncludingAllianceData(results,jsObj);	
					}
					if(jsObj.task =="getConstituenciesMatchingCriteria" || jsObj.task =="getConstituenciesByDistrictMatchingCriteria")
					{
						//if(results.requiredConstituenciesInfo.partiesStrengthsInfoVO ==null || results.requiredConstituenciesInfo.partiesStrengthsInfoVO.length ==0){
						if(results.requiredConstituenciesInfo.partiesStrengthsInfoVO ==null){
							showRequiredConstituencies(results);
							showRequiredConstituenciesErrorMessage(results);
							
						}else{
							
							buildDefaultDetails(results);							
							if(results.partyName=="All Parties"){
								initializeResultsTable(results,"dataTableId","dataTableMainDiv","others");								
							}else{
								initializeResultsTable2(results,"dataTableId","dataTableMainDiv");								
							}
						}
					}
			}catch (e) {   		
			   	//alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			alert( "Failed to load result" + o.status + " " + o.statusText);
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}

	function hideImg()
	{
		var searchByConstituencyDataImgDiv = document.getElementById("searchByConstituencyDataIMG");
		if(searchByConstituencyDataImgDiv)
			searchByConstituencyDataImgDiv.style.display = 'none';

		var imgDiv = document.getElementById("searchByDistrictDataIMG");
		if(imgDiv)
			imgDiv.style.display = 'none';		
	}
	
	/*
	/* Pie Chart for party results in new constituencies 
	/* Interactive chart code by sai
	*/
    function interactiveChartForNewConstituencyResults(myResults)
	{
		
		var data = new google.visualization.DataTable();
        data.addColumn('string', 'Party');
        data.addColumn('number', 'seats');
        data.addRows(myResults.length);

		for(var i=0; i<myResults.length; i++)
		{
			data.setValue(i, 0, myResults[i].name);
	        data.setValue(i, 1, parseInt(myResults[i].id));
		}

        var chart = new google.visualization.PieChart(document.getElementById('partyOverViewChartDetailsForNewConstituencies'));
        chart.draw(data, {width: 400, height: 257, title: 'Partys Results In New Constituencies'});

	}
	
	function buildExcludingAllianceData(results)
	{
	
		var partyName = results.partyName;	
		var partyId = results.partyId;	
		var partyDiv = document.getElementById(partyName+"DATA_DIV");
		var partiesDetails = results.allPartiesDetails.partiesDetailsVO;

		var elmt = partyName+"_AlliancesPartiesData";		
		var allianceDIV = document.getElementById(elmt);
		allianceDIV.style.display = 'none';
		
		var selPartyName = "ajaxImageEl_"+partyName;
				
		var imageDiv =document.getElementById(selPartyName);
		imageDiv.style.display ='none';

		var alliance = partyName+"DATA_DIV";			
		var allianceResults = document.getElementById(alliance);

		if(allianceResults)
			allianceResults.style.display ='block';
		
		var str='';
	
		str += '	<table class="table table-bordered">';				
		str +='		<tr>';
		str += '		<td class="headerStyle">Total No of Times Won </td>';
		str += '		<td class="headerStyle">Constituencies Count </td>';	
		str += '		<td class="headerStyle"> Total Won Seats in '+latestElectionYear+' </td>';
		str += '		<td class="headerStyle"> Total Lost Seats in '+latestElectionYear+' </td>';			
		str += '	</tr>';
		
		for(var k=0;k<partiesDetails.length;k++){	
			for(var j=0;j<partiesDetails[k].partyDetails.length;j++){	
				
				var size = partiesDetails[k].partyDetails[j].won;	
				var counter = partiesDetails[k].partyDetails[j].count;	
				
				if(size!=0){		
					str += '	<tr>';				
					str += '<td align="center">'+counter+'</td>';
	
					str += '<td align="center">';
					if(partiesDetails[k].partyDetails[j].won==0){
						str += '<b class="cursorStyle">0 </b>';
					}else{
						str += '<a title="click here to view constituencies details" onclick="getData(\''+partyName+'\',\''+counter+'\',\''+results.selectedYearsCount+'\')">';
						str += '<b class="cursorStyle">'+partiesDetails[k].partyDetails[j].won+' </b>';
					}
					str += '</td>';	

					if(counter == 0)
					{
                        str += '<td align="center">';
						str += '<b class="cursorStyle">0 </b>';
						str += '</td>';

						str += '<td align="center">';
						str += '<a title="click here to view constituencies details" onclick="getData(\''+partyName+'\',\''+counter+'\',\''+results.selectedYearsCount+'\')">';
						str += '<b class="cursorStyle">'+partiesDetails[k].partyDetails[j].won+' </b>';
						str += '</td>';	
					}
	                else
					{
						str += '<td align="center">';
						if(partiesDetails[k].partyDetails[j].wonRecently==0){
							str += '<b class="cursorStyle">0 </b>';
						}else{
							str += '<a title="click here to view constituencies details" onclick="getExcludingWonData(\''+partyId+'\',\''+counter+'\',\''+partyName+'\',\''+results.selectedYearsCount+'\')">';
							str += '<b class="cursorStyle">'+partiesDetails[k].partyDetails[j].wonRecently+' </b>';
						}					
						str += '</td>';
		
						str += '<td align="center">';
						if(partiesDetails[k].partyDetails[j].lostRecently==0){
							str += '<b class="cursorStyle">0 </b>';
						}else{
							str += '<a title="click here to view constituencies details" onclick="getExcludingLostData(\''+partyId+'\',\''+counter+'\',\''+partyName+'\',\''+results.selectedYearsCount+'\')">';
							str += '<b class="cursorStyle">'+partiesDetails[k].partyDetails[j].lostRecently+' </b>';
						}					
						str += '</td>';	

					}

					str += '	</tr>';				
				}		
			}	
		}
		str += '	</table>';		
		partyDiv.innerHTML = str;
	}

	
	function buildAllAllianceElectionYears(results)
	{	
		
		var eleId = selectedPartyName+"_AlliancesYears";		
		var divElement = document.getElementById(eleId);		
		var details = results.details;	
		var str='';
		
		if(details.length!=0){
			str+='<select id="AlliancesYears" style="width:130px;" onChange="getIncludingAllianceData(this.options[this.selectedIndex].value,\''+results.partyId+'\',\''+selectedPartyName+'\')">';			
			var count=0;
			str+='<option value="0">All Elections</option>';
			for(var i in details)
			{
				str+='<option value="'+details[i].id+'">'+details[i].name+'</option>';
				if(count ==0)
					count = details[i].id;
			}
			str+='</select>';
			if(count !=0){
				var defaultCount = 0;
				getIncludingAllianceData(defaultCount,results.partyId,selectedPartyName);
			}else{
				var selPartyName = "ajaxIncludingImageEl_"+selectedPartyName;
				
				var imageDiv =document.getElementById(selPartyName);
				imageDiv.style.display ='none';
			}
		}else{
			str+='<span style="color:green;font-weight:bold;padding:5px">'+results.partyName +'<span style="color:red;">&nbsp;&nbsp; has no alliances</span></span>';
			var partyNameDiv = results.partyName;
			var partyDiv = document.getElementById(partyNameDiv+"_DIV");
			partyDiv.style.display = 'block';
			var selPartyName = "ajaxIncludingImageEl_"+selectedPartyName;
				
			var imageDiv =document.getElementById(selPartyName);
			imageDiv.style.display ='none';

			var alliance = partyNameDiv+"DATA_DIV";			
			var allianceResults = document.getElementById(alliance);

			if(allianceResults)
				allianceResults.style.display ='none';
		}
		divElement.innerHTML = str;
	}

	
	function buildOverViewDataTable(results)
	{

		latestElectionYear = results.allPartiesDetails.electionYears[0];

		var headerElmt = document.getElementById("headerDiv");
		var tempStr ='';
		tempStr+='	<table>';
		tempStr+='		<tr>';
		if(results.partyName =="")
		{
		tempStr+='			<td>All Parties Strengths and Weakness Details in <td>';
		}
		else
		{
		tempStr+='			<td>'+results.partyName+' Strengths and Weakness Details in <td>';
		}
		tempStr+='			<td> <b style="color:red;">'+results.totalNumberOfConstituencies+'</b> Constituencies<td>';
		tempStr+='		</tr>';
		tempStr+='	</table>';
		tempStr+='';
		headerElmt.innerHTML = tempStr;

		var elecYears = results.allPartiesDetails.electionYears;
		var elecElmt = document.getElementById("electionYearsDiv");
		var elecYearsStr='';
		elecYearsStr+='<table>';
		elecYearsStr+='	<tr>';
		elecYearsStr+='		<td>';
		elecYearsStr+='			<b style="color:green;">Total Number of constituencies in the last '+results.selectedYearsCount+' election years</b> <b style="color: red;font-weight:bold;"/>'+results.requiredConstituenciesCount;
		elecYearsStr+='		</td>';	
		elecYearsStr+='	</tr>';
		if(results.latestConstituenciesInfo.totalNumberOfConstituencies!=null){
		elecYearsStr+='	<tr>';
		elecYearsStr+='		<td>';
		elecYearsStr+='			<br/><b style="color:green;">Total Number of constituencies for the latest election year</b>   <b style="color: red;font-weight:bold;"/>'+results.latestConstituenciesCount;
		elecYearsStr+='		</td>';
		elecYearsStr+='	</tr>';	
		}
		elecYearsStr+='</table>';
		elecYearsStr+='<br/>';
		elecYearsStr+='<table>';	
		elecYearsStr+='	<tr>';
		elecYearsStr+='		<td>';
		elecYearsStr+='			<b tyle="color:green;"> Total Election Years Considered </b>';
		elecYearsStr+='		</td>';
		elecYearsStr+='		<td>';
		for(var n=0;n<elecYears.length;n++){			
			if(elecYears.length!=(n+1))
				elecYearsStr+=' <b style="color:red;"> '+elecYears[n]+' ,</b>';
			else
				elecYearsStr+=' <b style="color:red;"> '+elecYears[n]+'</b>';
		}
		elecYearsStr+='		</td>';
		elecYearsStr+='	</tr>';
		elecYearsStr+='</table>';
		elecElmt.innerHTML = elecYearsStr;
		
		var elmt = document.getElementById("overViewDiv");
		
		var str='';
		var partiesDetails = results.allPartiesDetails.partiesDetailsVO;	
		
		str += '<div id="mainDataDiv">';		
		for(var k=0;k<partiesDetails.length;k++){
			
			if(partiesDetails[k].partyName == null){
			continue;
			}

			str += '  <div id="overviewOfPartiesDiv">';
			str += '	<table id="CompleteDetailsTable">';
			str += '		<tr>';	
			str += '			<td>';
			str += '			<table id="mainDetails" class="detailReportHeader"> ';
			str += '				<tr>';
            str += '					<td style="color:white;margin-left:4px;margin-right:4px;">';
			str += '							Winning Position of '+partiesDetails[k].partyName+' Party ';
			str += '			in the last <b style=" color: #69A74E;font-weight: bold;">'+results.selectedYearsCount+'</b> election years <b class="viewAndHideDetails"';
			str += '			</td>';
			str += '			<td>'; 
			str += '			<b style="margin-left:27px;cursor: pointer;text-decoration:underline;" id="hideOrShowId_'+partiesDetails[k].partyName+'" onclick="showData(\''+partiesDetails[k].partyName+'\')"> View Details</b></td>';
			str += '		</tr>';	
			str += '	</table>';	
			str += '		</tr>';	
			str += '	</table>';	
			str += '   </div">';
			str += '   <div id="'+partiesDetails[k].partyName+'_DIV" style="display:none;">';			
			str += '	<table>';
			str +='		<tr style="font-weight:bold;font-size:11px;">';
			str += '		<td><input type="radio" style="margin-top:0px;" id="default_checkId_'+partiesDetails[k].partyName+'" checked="checked" name="alliance_'+partiesDetails[k].partyName+'" onclick="getExcludingAllianceData(\''+partiesDetails[k].partyId+'\',\''+partiesDetails[k].partyName+'\')" value="exclude"/>&nbsp;&nbsp;Excluding Alliance </td>';
			str += '		<td><span id="ajaxImageEl_'+partiesDetails[k].partyName+'" style="margin-left:10px;display:none;"><img src="images/icons/search.gif"/></span></td>';
			str += '		<td><input type="radio" style="margin-top:0px;" onclick="getIncludingAllianceData('+0+',\''+partiesDetails[k].partyId+'\',\''+partiesDetails[k].partyName+'\')" name="alliance_'+partiesDetails[k].partyName+'"  value="include"/>&nbsp;&nbsp;Including Alliance </td>';
			//str += '		<td><input type="radio" style="margin-top:0px;" name="alliance_'+partiesDetails[k].partyName+'" onclick="getAllAllianceYearsForAParty(\''+partiesDetails[k].partyId+'\',\''+partiesDetails[k].partyName+'\')" value="include"/>&nbsp;&nbsp;Including Alliance </td>';
			str += '		<td><span id="ajaxIncludingImageEl_'+partiesDetails[k].partyName+'" style="margin-left:10px;display:none;"><img src="images/icons/search.gif"/></span></td>';					
			str += '		<td><div id="'+partiesDetails[k].partyName+'_AlliancesYears"></div></td>';				
			str += '	</tr>';	
			str += '	</table>';			
			str += '	<div style="display:none;" id="'+partiesDetails[k].partyName+'_AlliancesPartiesData"></div>';	
			str += '	<div id="'+partiesDetails[k].partyName+'DATA_DIV">';		
			str += '	<table class="table table-bordered">';				
			str +='		<tr>';
			str += '		<td class="headerStyle">Total No of Times Won </td>';
			str += '		<td class="headerStyle">Constituencies Count </td>';	
			str += '		<td class="headerStyle"> Total Won Seats in '+latestElectionYear+' </td>';
			str += '		<td class="headerStyle"> Total Lost Seats in '+latestElectionYear+' </td>';				
			str += '	</tr>';	
			for(var j=0;j<partiesDetails[k].partyDetails.length;j++){				
				var size = partiesDetails[k].partyDetails[j].won;	
				var counter = partiesDetails[k].partyDetails[j].count;				
				if(size!=0){		
					str += '	<tr>';				
					str += '<td align="center">'+counter+'</td>';

					str += '<td align="center">';
					str += '<a title="click here to view constituencies details" onclick="getData(\''+partiesDetails[k].partyName+'\',\''+counter+'\',\''+results.selectedYearsCount+'\')">';
					str += '<b class="cursorStyle">'+partiesDetails[k].partyDetails[j].won+' </b>';
					str += '</td>';	

					if(counter == 0)
					{

                        str += '<td align="center">';
						str += '<b class="cursorStyle">0 </b>';
						str += '</td>';

						str += '<td align="center">';
						str += '<a title="click here to view constituencies details" onclick="getData(\''+partiesDetails[k].partyName+'\',\''+counter+'\',\''+results.selectedYearsCount+'\')">';
						str += '<b class="cursorStyle">'+partiesDetails[k].partyDetails[j].won+' </b>';
						str += '</td>';	

					}
					else
					{

						str += '<td align="center">';
						if(partiesDetails[k].partyDetails[j].wonRecently==0){
							str += '<b class="cursorStyle">0</b>';
						}else{
							str += '<a title="click here to view constituencies details" onclick="getExcludingWonData(\''+partiesDetails[k].partyId+'\',\''+counter+'\',\''+partiesDetails[k].partyName+'\',\''+results.selectedYearsCount+'\')">';
							str += '<b class="cursorStyle">'+partiesDetails[k].partyDetails[j].wonRecently+' </b>';
						}
						
						str += '</td>';

						str += '<td align="center">';
						if(partiesDetails[k].partyDetails[j].lostRecently==0){
							str += '<b class="cursorStyle">0</b>';
						}else{
							str += '<a title="click here to view constituencies details" onclick="getExcludingLostData(\''+partiesDetails[k].partyId+'\',\''+counter+'\',\''+partiesDetails[k].partyName+'\',\''+results.selectedYearsCount+'\')">';
							str += '<b class="cursorStyle">'+partiesDetails[k].partyDetails[j].lostRecently+' </b>';
						}
						str += '</td>';	
					}
					str += '	</tr>';				
				}		
			}			
			str += '	</table>';			
			str += '	</div>';
			str += '	</div>';
		}
		str += ' </div">';		
		elmt.innerHTML =  str;		
	} 

	function getExcludingAllianceData(partyId,selectedPartyNamed)
	{
		var selPartyName = selectedPartyNamed;
		selPartyName = "ajaxImageEl_"+selectedPartyNamed;
		
		var imageDiv =document.getElementById(selPartyName);
		imageDiv.style.display ='block';
		
		var divId = selectedPartyNamed+"_AlliancesYears";
		var elmet =  document.getElementById(divId);
		elmet.innerHTML = '';

		var elmt = selectedPartyNamed+"_AlliancesPartiesData";		
		var allianceDIV = document.getElementById(elmt);
		allianceDIV.style.display = 'none';
		
		var electionTypeSelect = document.getElementById("electionTypeSelect").value;
		var stateSelect = document.getElementById("stateSelect").value;
		var electionYearsSelect = document.getElementById("electionYearsSelect").value;
		
		var jsObj=
		{		
				electionType : electionTypeSelect,
				stateId : stateSelect, 
				partyName:partyId,
				partyShortName:selectedPartyNamed,
				elecYears : electionYearsSelect,
				task:"getExcludingAllianceDataAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getExcludingAllianceDataAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);		
	}
	

	function getIncludingAllianceData(electionId,partyId,partyName)
	{
		var electionTypeSelect = document.getElementById("electionTypeSelect").value;
		var stateSelect = document.getElementById("stateSelect").value;
		var electionYearsSelect = document.getElementById("electionYearsSelect").value;
		selectedElecId = electionId;
		var jsObj=
		{		
				electionType : electionTypeSelect,
				stateId : stateSelect, 
				partyId:partyId,
				electionId : electionId,
				partyName :partyName,
				elecYears : electionYearsSelect,
				task:"getIncludingAllianceDataAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getIncludingAllianceDataAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}

	function getAllAllianceYearsForAParty(partyId,partyName)
	{		

		var selPartyName = "ajaxIncludingImageEl_"+partyName;
		
		var imageDiv =document.getElementById(selPartyName);
		imageDiv.style.display ='block';
		
		var stateSelect = document.getElementById("stateSelect").value;
		selectedPartyName = partyName;	
		partyId = partyId;
		var jsObj=
		{	
				stateId : selectedStateElmts, 
				partyId:partyId,		
				partyName:partyName,		
				task:"getAllAllianceYearsForAPartyAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllAllianceYearsForAPartyAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	
	function showData(partyName)
	{
		var divId = "hideOrShowId_"+partyName;
		var elmt = document.getElementById(divId);		
		
		
		var data = document.getElementById(partyName+"_DIV");		
		if(data.style.display=='block'){
			data.style.display = 'none';
			elmt.innerHTML = "View Details";
		}else{
			data.style.display = 'block';
			elmt.innerHTML = "Hide Details";
		}
	}
	
	function getData(partyName,columnId,selectedPartyId){
		
		var browser1 = window.open("<s:url action="candidateStrenthsAction.action"/>?electionType="+electionType+"&selectedStateElmts="+selectedStateElmts+"&partyName="+partyName+"&elecYears="+selectedPartyId+"&columnId="+columnId,"excludingAllianceResults","scrollbars=yes,height=500,width=800,left=200,top=200");
		browser1.focus();		
	}

	function getIncludingData(partyName,columnId,partyId){	
		var selectedPartyId = $("#electionYearsSelect").val();
		var browser1 = window.open("<s:url action="candidateStrenthsAction.action"/>?electionType="+electionType+"&selectedStateElmts="+selectedStateElmts+"&partyName="+partyName+"&elecYears="+selectedPartyId+"&columnId="+columnId+"&partyId="+partyId+"&elecId="+selectedElecId+"&type=all","includingAllianceResults","scrollbars=yes,height=500,width=800,left=200,top=200");
		browser1.focus();		
	}

	function getWonData(partyId,columnId,partyName){
		var selectedPartyId = $("#electionYearsSelect").val();
		var browser1 = window.open("<s:url action="candidateStrenthsAction.action"/>?electionType="+electionType+"&selectedStateElmts="+selectedStateElmts+"&partyName="+partyName+"&elecYears="+selectedPartyId+"&columnId="+columnId+"&partyId="+partyId+"&elecId="+selectedElecId+"&type=WINNER","WonData","scrollbars=yes,height=500,width=800,left=200,top=200");
		browser1.focus();	
	}

	function getLostData(partyId,columnId,partyName){
		var selectedPartyId = $("#electionYearsSelect").val();
		var browser1 = window.open("<s:url action="candidateStrenthsAction.action"/>?electionType="+electionType+"&selectedStateElmts="+selectedStateElmts+"&partyName="+partyName+"&elecYears="+selectedPartyId+"&columnId="+columnId+"&partyId="+partyId+"&elecId="+selectedElecId+"&type=lost","LostData","scrollbars=yes,height=500,width=800,left=200,top=200");
		browser1.focus();		
	}
	function getExcludingWonData(partyId,columnId,partyName,selectedPartyId){	
		
		var browser1 = window.open("<s:url action="candidateStrenthsAction.action"/>?electionType="+electionType+"&selectedStateElmts="+selectedStateElmts+"&partyName="+partyName+"&elecYears="+selectedPartyId+"&columnId="+columnId+"&elecId="+selectedElecId+"&excludeType=WINNER","WonData","scrollbars=yes,height=500,width=800,left=200,top=200");
		browser1.focus();	
	}

	function getExcludingLostData(partyId,columnId,partyName,selectedPartyId){		
		var browser1 = window.open("<s:url action="candidateStrenthsAction.action"/>?electionType="+electionType+"&selectedStateElmts="+selectedStateElmts+"&partyName="+partyName+"&elecYears="+selectedPartyId+"&columnId="+columnId+"&elecId="+selectedElecId+"&excludeType=SUCCESSOR","LostData","scrollbars=yes,height=500,width=800,left=200,top=200");
		browser1.focus();		
	}
	
	function initializeTable(results){

		var partiesArray = new Array();
		var columnDataArray = new Array();
		
		var resultsObj = {
			key: "partyName"							
		};
		partiesArray.push(resultsObj);
		
		var columnObj ={
			key : "partyName",
			label : "Party Name",
			sortable : true,
			resizeable:true
		};
		columnDataArray.push(columnObj);
		var j=0;
		for(var i=0;i<results.selectedYearsCount;i++){
			j = i+1;
			var resultsObj = {
					key: j+""							
			};
			partiesArray.push(resultsObj);

			var allColumnObj ={
				key : j+"",
				label : j+"",
				sortable : true,
				resizeable:true
			};
			columnDataArray.push(allColumnObj);
		}
			
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("overView_Table"));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;

		resultsDataSource.responseSchema = {
			fields : partiesArray
		};
	
		var resultsColumnDefs = columnDataArray;
		
		var paginatorConfig = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10
	    })
		};
				
		var myDataTable = new YAHOO.widget.DataTable("overViewDiv",resultsColumnDefs, resultsDataSource,paginatorConfig);  
	}	
	
	
	function showRequiredConstituencies(results)
	{

		var elmet = document.getElementById('required_const_body');
			elmet.style.display = 'none';
		var count = document.getElementById("requiredConstituenciesCount");
		var countElmt = '';
		//countElmt+='<span><a style="color:green;font-weight:bold;font-size:12px;" href="javascript:{}" title="click here to hide and show the table" onclick="hideOrShow(\'required_const_body\')"> Constituencies Present in the last '+ results.selectedYearsCount +' election years</a></span>';
		countElmt+='<span><a style="color:green;font-weight:bold;font-size:12px;" href="javascript:{}" title="click here to hide and show the table"> Constituencies Present in the last '+ results.selectedYearsCount +' election years</a></span>';
		countElmt+='<b style="font-weight:bold;color:red;font-size:12px;"> : 0 </b>';
		count.innerHTML = countElmt;	
			$('#noDataDiv').css('display','block');
	}

	function showRequiredConstituenciesErrorMessage(results){
		

		var elmt = document.getElementById("dataTableBuild");
		elmt.innerHTML = "";
		
		var str='';
		str+='<b style="color:red;"> No Constituencies Were present for matching the Criteria </b>';
		elmt.innerHTML = str;
		hideImg();
	}

	function hideLatestConstituenciesDiv(){
		
		var elmt = document.getElementById("dataTableBuildForNewConstituencies");
		var str='';	
		elmt.innerHTML = str;
	
		var elmt2 = document.getElementById("new_const_main");
		elmt2.style.display = 'none';
		//elmt2.innerHTML = str;
	}

	function hideRemainingConstituenciesDiv(){
		var elmt = document.getElementById("dataTableBuildForRemainingConstituencies");
		var str='';	
		elmt.innerHTML = str;
	
		var elmt2 = document.getElementById("remaining_const_main");
		elmt2.style.display = 'none';
		//elmt2.innerHTML = str;
	}
	
	function initializeResultsTable(results,tableId,divId,type) {

		var partiesArray = new Array();
		var columnDataArray = new Array();
		var allParties = results.allPartiesDetails.allPartiesData;
		var partySelected = $("#partySelect option:selected").text();

			var resultsObj = {
				key: "constituencyName"							
			};
			partiesArray.push(resultsObj);
			
			var columnObj ={
				key : "constituencyName",
				label : "Constituency Name",
				sortable : true,
				resizeable:true
			};
			columnDataArray.push(columnObj);			
		
			for(var i=0;i<allParties.length;i++){
				var resultsObj = {
							key: allParties[i].name							
					};
					partiesArray.push(resultsObj);
					if(partySelected == 'All Parties'){			
						var allColumnObj ={
							key : allParties[i].name,
							label : allParties[i].name,
							sortable : true,
							resizeable:true
						};
						columnDataArray.push(allColumnObj);						
					}
					else{
						if(allParties[i].name == partySelected){
								var allColumnObj ={
									key : allParties[i].name,
									label : "No of Times Won",
									sortable : true,
									resizeable:true
								};
								columnDataArray.push(allColumnObj);
							}						
					}
				}
				
				if(partySelected == 'All Parties'){
				//if(type=="others"){
								var resultsWithOthers = {
									key: "others"							
								};
								partiesArray.push(resultsWithOthers);
								
								var columnObjWithOthers ={
									key : "others",
									label : "Others *",
									sortable : true,
									resizeable:true
								};
								columnDataArray.push(columnObjWithOthers);
							//}
				
				}
			
			var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
					.get(tableId));
			resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;

			resultsDataSource.responseSchema = {
				fields : partiesArray
			};
		
			var resultsColumnDefs = columnDataArray;
			
			var paginatorConfig = {
			paginator : new YAHOO.widget.Paginator({
				rowsPerPage: 10
			})
			};
			
			var myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,paginatorConfig);  
	
		
	}

	
	
	function initializeResultsTable2(results,tableId,divId) {
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get(tableId));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "constituencyName"
			}, {
				key : "count"
			}]
		};
	
		var resultsColumnDefs = [ {
			key : "constituencyName",
			label : "Constituency Name",
			sortable : true,
			 resizeable:true
		}, {
			key : "count",
		    label : "No of Times Won",
			sortable : true,
			 resizeable:true
		}];
		
		var paginatorConfig = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10
	    })
		};
		
		var myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,paginatorConfig);  
	}

	
	function getStatesInPS(selectedElmt)
	{
		buildSearchCriteria(selectedElmt);
		
		electionType = selectedElmt;
		//updated here
		var jsObj=
		{		
				electionType :selectedElmt,		
				task:"getStatesAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getStatesAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);

		var jsObj=
		{	
			stateId : 1,
			electionType :electionType,		
			task:"getAllElectionsAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllElectionsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
		//updated here
		var jsObj=
		{		
				stateId : 1,
				electionType :electionType,		
				task:"getAllPartiesData"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllPartiesMatchingCriteria.action?"+rparam;						
		callAjax(jsObj,url);	
		
	}

	function getStatesOfAp(selectedElmt)
	{
		electionType = selectedElmt;	
	
		var jsObj=
		{		
				electionType :selectedElmt,		
				task:"getStatesAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getStatesAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}

	function getFrequencyOfYears(selectedState)
	{	
		selectedStateElmts = selectedState;
		var jsObj=
		{		
				stateId : selectedState,
				electionType :electionType,		
				task:"getAllElectionsAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllElectionsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);


		var jsObj=
		{		
				stateId : selectedState,
				electionType :electionType,		
				task:"getAllPartiesData"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllPartiesMatchingCriteria.action?"+rparam;						
		callAjax(jsObj,url);

		//validateAndForwardToAction();
	}

	function getParties(selectedParty)
	{
		selectedPartyId = selectedParty;
		/*var jsObj=
		{		
				stateId : selectedStateElmts,
				electionType :electionType,	
					test:'tst3',
				task:"getAllPartiesData"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllPartiesMatchingCriteria.action?"+rparam;						
		callAjax(jsObj,url);*/

		validateAndForwardToAction();
	}
	
	function getDefaultElectionYearsAjaxAction()
	{	
		var jsObj=
		{		
				stateId : 1,
				electionType : 'Assembly',		
				task:"getDefaultElectionYearsAjaxAction"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllElectionsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}

	
	
	function getDefaultParties()
	{
		var jsObj=
		{		
				stateId : 1,
				electionType :'Assembly',		
				task:"getAllPartiesData"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllPartiesMatchingCriteria.action?"+rparam;						
		callAjax(jsObj,url);
	}
	
	function buildAllPartiesData(results)
	{		
		var allPartiesData = document.getElementById("partySelectTd");
		
		var populateAllPartiesData='';
		populateAllPartiesData+='<select id="partySelect" style="width:130px;" onChange="return validateAndForwardToAction()">';
		populateAllPartiesData+='<option value="0">All Parties</option>';
		for(var i in results)
		{
			populateAllPartiesData+='<option value="'+results[i].id+'">'+results[i].name+'</option>';
		}
		populateAllPartiesData+='</select>';
		allPartiesData.innerHTML = populateAllPartiesData;

	}
	
	function buildDefaultElectionYears(results)
	{		 		
		buildElectionYearsTable(results);		
	}
	
	function buildElectionYears(results)
	{ 	
		buildElectionYearsTable(results);
		validateAndForwardToAction();
	}
	
	function buildElectionYearsTable(results)
	{
		
		$('#partySelect').val('0');//updated here

		var showElections = document.getElementById("electionTypeTd");
		
		var populateElections='';
		populateElections+='<select id="electionYearsSelect" style="width:50px;" onchange="getParties(this.options[this.selectedIndex].value)">';
		
		for(var i in results)  
		{
			/*if(results[i].id==7){
				populateElections+='<option value="'+results[i].id+'" selected="selected">'+results[i].name+'</option>';
			}else{*/
				populateElections+='<option value="'+results[i].id+'">'+results[i].name+'</option>';
			//}
			
		}
		populateElections+='</select>';
		showElections.innerHTML = populateElections;
	}
	
	

	
	
	function buildStates(results)
	{			
		var showStates = document.getElementById("stateTd");
		var populateStates='';
		populateStates+='<select id="stateSelect" style="width:130px;" onchange="getFrequencyOfYears(this.options[this.selectedIndex].value)">';
		for(var i in results)
		{
			populateStates+='<option value="'+results[i].id+'">'+results[i].name+'</option>';
		}
		populateStates+='</select>';
		showStates.innerHTML = populateStates;
	}

	function getConstituenciesByDistrict()
	{
		$('#searchByConstituencyData').val('');
		$('#districtErrDiv').html('');
		$('#ConstituencyErrDiv').html('');
		var party = $("#partySelect").val();
		var name = $('#searchByDistrictData').val();
		
		if(name.length >0){
			if(/[^a-zA-Z]/.test(name)){
			$('#districtErrDiv').html('Search By District Accepts only Characters');			
			return false;
			}
		}
		matchingCriteria = "DISTRICT";
		
		var imgDiv = document.getElementById("searchByDistrictDataIMG");
		imgDiv.style.display = 'block';
		
		var enteredText = document.getElementById("searchByDistrictData").value;
		var electionYearsSelect = document.getElementById("electionYearsSelect").value;
		
		var jsObj=
		{		
				selectedNoOfYears : electionYearsSelect,
				searchType : "DISTRICT",
				searchText : enteredText,
				stateId : selectedStateElmts,
				electionType :electionType,	
				party:party,
				task:"getConstituenciesByDistrictMatchingCriteria"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getConstituenciesByDistrictMatchingCriteria.action?"+rparam;						
		callAjax(jsObj,url);

	}

	function getConstituencies()
	{	
		$('#searchByDistrictData').val('');
		$('#districtErrDiv').html('');
		$('#ConstituencyErrDiv').html('');

		var name = $('#searchByConstituencyData').val();
		var party = $("#partySelect").val();
		if(name.length >0){
			if(/[^a-zA-Z]/.test(name)){
			$('#ConstituencyErrDiv').html('Search By Constituency Accepts only Characters');
			return false;
			}
		}
		matchingCriteria = "CONSTITUENCY";
		
		var searchByConstituencyDataImgDiv = document.getElementById("searchByConstituencyDataIMG");
		searchByConstituencyDataImgDiv.style.display = 'block';
		
		var enteredText = document.getElementById("searchByConstituencyData").value;
		var electionYearsSelect = document.getElementById("electionYearsSelect").value;
		
		var jsObj=
		{		
				selectedNoOfYears : electionYearsSelect,
				searchType : "CONSTITUENCY",
				searchText : enteredText,
				stateId : selectedStateElmts,
				electionType :electionType,
				party:party,
				task:"getConstituenciesMatchingCriteria"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getConstituenciesMatchingCriteria.action?"+rparam;						
		callAjax(jsObj,url);

	}

	function buildSearchCriteria(selectedElectionType)
	{		
		var searchByDist = document.getElementById("searchByDistrict");
		var searchByDistStr = '';
		searchByDistStr += '	<table>';
		searchByDistStr += 			'<tr>';		
		if(selectedElectionType!="Parliament"){
			searchByDistStr += '<td style="color:#FBAD2B;font-weight:bold;">Search By District : '; 
			searchByDistStr += '<input id="searchByDistrictData" title="Enter District Name" type="text" onkeyup="getConstituenciesByDistrict()"></input></td>';
			searchByDistStr += '<td><img style="display:none;" id="searchByDistrictDataIMG" src="images/icons/search.gif"/></td>';
		//	searchByDistStr += '<td style="color:red;font-weight: bold;"> (or) </td>';
		}		
		searchByDistStr += 			'	<td style="color:#FBAD2B;font-weight:bold;">Search By Constituency : ';
		searchByDistStr += 			'	<input id="searchByConstituencyData" title="Enter Constituency Name" type="text" onkeyup="getConstituencies()"></input></td>';
		searchByDistStr += 			'	<td><img style="display:none;" id="searchByConstituencyDataIMG" src="images/icons/search.gif"/></input></td>';
		searchByDistStr += 			'</tr>';
		searchByDistStr += 			'<tr>';
		searchByDistStr += 			'<td> <div id="districtErrDiv" style="color:red;font-weight:bold;font-size:13px;"></div>';
		searchByDistStr += 			'</td>';
		searchByDistStr += 			'<td>';
		searchByDistStr += 			'</td>';
		searchByDistStr += 			'<td <div id="ConstituencyErrDiv" style="color:red;font-weight:bold;font-size:13px;"></div>';
		searchByDistStr += 			'</td>';
		searchByDistStr += 			'</tr>';
		searchByDistStr += '</table>';

		searchByDistStr += '';
		searchByDist.innerHTML = searchByDistStr;	
		
	}
	
	function buildDefaultDetails(results)
	{	
		if(matchingCriteria == "DISTRICT"){
			var imgDiv = document.getElementById("searchByDistrictDataIMG");
			imgDiv.style.display = 'none';
		}else{
			var searchByConstituencyDataImgDiv = document.getElementById("searchByConstituencyDataIMG");
			searchByConstituencyDataImgDiv.style.display = 'none';
		}		
		
		var dataTable = document.getElementById("dataTableBuild");

		var data = results.requiredConstituenciesInfo.partiesStrengthsInfoVO;

		var partiesData = results.requiredConstituenciesInfo;

		var str='';		
		str+='<div id="dataTableMainDiv">';
		str+='	<table id="dataTableId">';
		
		for(var i =0;i<data.length;i++)
		{			
			str+='	<tr>';
			str+='		<td>'+data[i].constituencyName+'</td>';
			for(var j =0;j<data[i].partyResults.length;j++)
			{
				str+='	<td>'+data[i].partyResults[j].count+'</td>';
			}
			str+='	</tr>'
		}
		str+='	</table>';
		str+='</div>';
		
		dataTable.innerHTML = str;

		var count = document.getElementById("requiredConstituenciesCount");
		var countElmt = '';
		if(results.partyName == "All Parties"){
			countElmt+='<span><a style="color:green;font-weight:bold;font-size:12px;" href="javascript:{}" title="click here to hide and show the table" onclick="hideOrShow(\'required_const_body\')"> Constituencies Present in the last '+ results.selectedYearsCount +' election years</a></span>';
		}else{
			countElmt+='<span><a style="color:green;font-weight:bold;font-size:12px;" href="javascript:{}" title="click here to hide and show the table" onclick="hideOrShow(\'required_const_body\')"> Current constituencies present in any of last '+ results.selectedYearsCount +' election years and won by <b style="font-weight:bold;color:red;">'+ results.partyName +'</b> party in any election </a></span>';
		}
		countElmt+='<b style="font-weight:bold;color:red;font-size:12px;"> : '+results.requiredConstituenciesInfo.totalNumberOfConstituencies+'</b>';
		count.innerHTML = countElmt;
		document.getElementById('required_const_body').style.display = 'block';
	}

	function buildDefaultDetailsForNewConstituencies(results)
	{
		var sri = 0; // sri value created by srishailam
	   var partyOverViewDiv = document.getElementById("partyOverViewDetailsForNewConstituencies");
	   var overViewStr='';
	   overViewStr+='<table  class="table table-bordered table-striped">';
	   var partyOverViewDetails = results.partyOverView;
	   overViewStr+='	<tr>';
	   var leng = 0;
	   if(partyOverViewDetails.length<3)
		   leng = 1;
		else
		   leng = 3;
		
	   for(var k=0;k<leng;k++)
	   {   
		 //  overViewStr+='		<td style="background:lightGrey;"> </td>';
		   overViewStr+='		<td style="background:lightGrey;"><span style="color:#247CD4;font-weight:bold;font-size:12px;"> Party </span></td>';
		   overViewStr+='<td style="background:lightGrey;"> <span style="font-weight:bold;color:darkgreen;font-size:12px;"> No. of Seats Won  </span> </td>';
	   }
	   overViewStr+='	</tr>';
	   overViewStr+='	<tr>';
	   overViewStr+='	</tr>';
	   overViewStr+='	<tr>';
	   for(var m=0;m<partyOverViewDetails.length;m++)
	   {
		// overViewStr+='		<td style="font-weight:bold;width: 10px;padding-left: 10px;padding-right: 10px;"><img src="images/icons/districtPage/listIcon.png" ></td>';		 	
		 	   overViewStr+='		<td> <span style="color:red;font-size:10px;">'+partyOverViewDetails[m].name+'</span></td>';
		 	   overViewStr+='<td align="center"> <span style="color:green;font-size:10px;">'+partyOverViewDetails[m].id+'</span></td>';		
		   if((m+1)%3==0){	
				sri = 0;
		  	   overViewStr+='	</tr>';
		  	   overViewStr+='	<tr>';		
		   } 
		   sri = sri+1;
		}
		if(sri == 2){
		overViewStr+='	<td style="text-align: center;font-weight:bold;"> - </td>';
		overViewStr+='	<td style="font-weight:bold;"> - </td>';	
		overViewStr+='	<td style="text-align: center;font-weight:bold;"> - </td>';
		overViewStr+='	<td style="font-weight:bold;"> - </td>';			   
		}
		if(sri == 3){
		overViewStr+='	<td style="text-align: center;font-weight:bold;"> - </td>';
		overViewStr+='	<td style="font-weight:bold;"> - </td>';			   
		}
	   overViewStr+='	</tr>';
	   overViewStr+='</table>';
	   partyOverViewDiv.innerHTML = overViewStr;

		
		
		var dataTable = document.getElementById("dataTableBuildForNewConstituencies");

		var data = results.latestConstituenciesInfo.partiesStrengthsInfoVO;

		var partiesData = results.latestConstituenciesInfo;

		var str='';	
		str+='<div id="dataTableMainDiv_latestConstituencies">';
		str+='	<table id="dataTableId_latestConstituencies">';
		
		for(var i =0;i<data.length;i++)
		{			
			str+='	<tr>';
			str+='		<td>'+data[i].constituencyName+'</td>';
			for(var j =0;j<data[i].partyResults.length;j++)
			{
				str+='	<td>'+data[i].partyResults[j].count+'</td>';
			}
			str+='	</tr>'
		}
		str+='	</table>';
		str+='</div>';
		
		dataTable.innerHTML = str;

		var count = document.getElementById("newConstituenciesCount");
		var countElmt = '';

		var othersInfo = document.getElementById("othersInfoForNewConstituencies");
		var info='';
		
		if(results.partyName == "All Parties"){
			countElmt+='<span><a style="color:green;font-weight:bold;font-size:12px;" href="javascript:{}" title="click here to hide and show the table" onclick="hideOrShow(\'new_const_body\')" > New Constituencies for '+latestElectionYear+' election</a></span>';

			
			if(results.hasOthers){
				info+='<table style="font-weight:bold;">';
				info+='		<tr>';
				info+='			<td style="font-weight:bold;color:red;"> *</td>';
				info+='			<td> Others Include</td>';
				info+='			<td>'+results.otherDetails+'</td>';
				info+='	</tr>';
				info+='</table>';		
				othersInfo.innerHTML = info;
			}
			
		}else{
			countElmt+='<span><a style="color:green;font-weight:bold;font-size:12px;" href="javascript:{}" title="click here to hide and show the table" onclick="hideOrShow(\'required_const_body\')"> Constituencies Won by <b style="font-weight: bold; color: red;">'+ results.partyName +'</b> Party in new constituencies formed in '+latestElectionYear+' </a></span>';
		}
		countElmt+='<b style="font-weight:bold;color:red;font-size:12px;"> : '+results.latestConstituenciesInfo.totalNumberOfConstituencies+'</b>';
		count.innerHTML = countElmt;
		othersInfo.innerHTML = info;
	}

	function buildDefaultDetailsForRemianingConstituencies(results)
	{
		var dataTable = document.getElementById("dataTableBuildForRemainingConstituencies");
		
		var data = results.remainingConstituenciesInfo.partiesStrengthsInfoVO;

		var partiesData = results.remainingConstituenciesInfo;
		
		var str='';
		//str+='<b style="color:green;font-weight:bold;font-size:12px;"> Remaining Constituencies Details </b>';
		str+='<div id="dataTableMainDiv_remainingConstituencies">';
		str+='	<table id="dataTableId_remainingConstituencies">';
		
		for(var i =0;i<data.length;i++)
		{			
			str+='	<tr>';
			str+='		<td>'+data[i].constituencyName+'</td>';
			for(var j =0;j<data[i].partyResults.length;j++)
			{
				str+='	<td>'+data[i].partyResults[j].count+'</td>';
			}
			str+='	</tr>'
		}
		str+='	</table>';
		str+='</div>';
		
		dataTable.innerHTML = str;

		var count = document.getElementById("delimitationConstituenciesCount");
		var countElmt = '';
		countElmt+='<span><a style="color:green;font-weight:bold;font-size:12px;" title="click here to hide and show the table" href="javascript:{}" onclick="hideOrShow(\'remaining_const_body\')"> Delimitation Constituencies Details</a>  </span> ';
		countElmt+='<b style="font-weight:bold;color:red;font-size:12px;"> : '+results.remainingConstituenciesInfo.totalNumberOfConstituencies+'</b>';
		count.innerHTML = countElmt;
	}
	
	function populateDefaultDetails()
	{
      var jsObj=
		{	
				stateId : '1',
				electionType : 'Assembly',	
				electionYears : '8',
				party : 0,
				task:"getDefaultDetails"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "populateDefaultDetailsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}

	function validateAndForwardToAction()
	{		
		$('#searchByDistrictData').val('');
		$('#searchByConstituencyData').val('');		
		$('#noDataDiv').css('display','none');
		var electionTypeSelect = document.getElementById("electionTypeSelect").value;
		var stateSelect = document.getElementById("stateSelect").value;
		var electionYearsSelect = document.getElementById("electionYearsSelect").value;
		var partySelect = document.getElementById("partySelect").value;
	
		getElectionDetailsForSelectedCriteria(electionTypeSelect,stateSelect,electionYearsSelect,partySelect);	
	}

	function getElectionDetailsForSelectedCriteria(electionTypeSelect,stateSelect,electionYearsSelect,partySelect)
	{
		var elmt = document.getElementById("busyImage");
		elmt.style.display = 'block';		
		var jsObj=
		{	
				stateId : stateSelect,
				electionType : electionTypeSelect,	
				electionYears : electionYearsSelect,
				party : partySelect,				
				task:"getDefaultDetails"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "populateDefaultDetailsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}

	function hideOrShow(divId)
	{
		var elmet = document.getElementById(divId);

		if(elmet.style.display == 'none')
			elmet.style.display = 'block';
		else
			elmet.style.display = 'none';
	}
	function buildIncludingAllianceData(results,jobj)
	{

		var partyName = results.partyName;	
		var selectedPartyName = jobj.partyName;
		var partyId = results.partyId;	
		var partyDiv = document.getElementById(partyName+"DATA_DIV");
		var partiesDetails = results.alliancePartyDetails;
		var allianceDetails = results.allianceDetails;

		var selPartyName = "ajaxIncludingImageEl_"+partyName;
		if(selPartyName){
			var imageDiv =document.getElementById(selPartyName);
			imageDiv.style.display ='none';
		}	
		
		var str='';
	
		str += '	<table class="table table-bordered">';				
		str +='		<tr>';
		str += '		<td class="headerStyle">Total No of Times Won </td>';
		str += '		<td class="headerStyle">Constituencies Count </td>';	
		str += '		<td class="headerStyle"> Total Won Seats in '+latestElectionYear+' </td>';
		str += '		<td class="headerStyle"> Total Lost Seats in '+latestElectionYear+' </td>';			
		str += '	</tr>';
		if(allianceDetails!=null)
		{
			var elmt = selectedPartyName+"_AlliancesPartiesData";
			var allianceDIV = document.getElementById(elmt);
			allianceDIV.style.display = 'block';

			var name = selectedPartyName+"_AlliancesPartiesData";
			var alliancePartiesDetails = document.getElementById(name);
			var allianceStr = '';
			/*allianceStr +='<table>';
			allianceStr +='		<tr>';			
			allianceStr +='		<td><b style="font-weight:bold;color:green;">Alliance Parties for </b><b style="font-weight:bold;color:red;"> '+results.partyName+'</b></td>';
			for(var j=0;j<allianceDetails.length;j++)
			{
				if(j<allianceDetails.length-1)
					allianceStr +='		<td> <b style="font-weight:bold;color:#C0B959;">'+allianceDetails[j].name+'</b>,</td>';
				else
					allianceStr +='		<td> <b style="font-weight:bold;color:#C0B959;">'+allianceDetails[j].name+'</b></td>';
			}			
			allianceStr +='		</tr>';
			allianceStr +='</table>';
			alliancePartiesDetails.innerHTML = allianceStr;	*/		
		}
	
			for(var j=0;j<partiesDetails.length;j++){				
				var size = partiesDetails[j].won;	
				var counter = partiesDetails[j].count;				
				if(size!=0){		
					str += '	<tr>';				
					str += '<td align="center">'+counter+'</td>';
	
					str += '<td align="center">';
					if(partiesDetails[j].won==0){
						str += '<b class="cursorStyle">0 </b>';
					}else{
						str += '<a title="click here to view constituencies details" onclick="getIncludingData(\''+partyName+'\',\''+counter+'\',\''+partyId+'\')">';
						str += '<b class="cursorStyle">'+partiesDetails[j].won+' </b>';
					}					
					str += '</td>';	

					if(counter == 0)
					{

                        str += '<td align="center">';
						str += '<b class="cursorStyle">0 </b>';
						str += '</td>';

						str += '<td align="center">';
						str += '<a title="click here to view constituencies details" onclick="getIncludingData(\''+partyName+'\',\''+counter+'\',\''+partyId+'\')">';
						str += '<b class="cursorStyle">'+partiesDetails[j].won+' </b>';
						str += '</td>';	

					}
	                else
					{
	                
						str += '<td align="center">';
						if(partiesDetails[j].wonTimes==0){
							str += '<b class="cursorStyle">0 </b>';
						}else{
							str += '<a title="click here to view constituencies details" onclick="getWonData(\''+partyId+'\',\''+counter+'\',\''+partyName+'\')">';
							str += '<b class="cursorStyle">'+partiesDetails[j].wonTimes+' </b>';
						}
						str += '</td>';

						str += '<td align="center">';
						if(partiesDetails[j].lostTimes==0){
							str += '<b class="cursorStyle">0 </b>';
						}else{					
							str += '<a title="click here to view constituencies details" onclick="getLostData(\''+partyId+'\',\''+counter+'\')">';
							str += '<b class="cursorStyle">'+partiesDetails[j].lostTimes+' </b>';
						}
						str += '</td>';	
					}
					str += '	</tr>';				
				}		
			}	
		
		str += '	</table>';		
		partyDiv.innerHTML = str;
	}
</script>


</head>
<body>
		<div id="main_div">
			<div id="header_div" style="margin-top: 24px;margin-left:auto;margin-right:auto;width:990px;">
				<table>
					<tr>
						<td colspan="2" id="headingDiv">
							<span style="background: none repeat scroll 0 0 #05A8E9;
    border-radius: 5px 5px 5px 5px;
    color: #FFFFFF;
    margin-left: 200px;
    margin-top: 2px;
    padding: 6px;
    position: absolute;
    text-align: center;
    width: 605px;">Party Strengths and Weakness</span>
						</td>
					</tr>
				</table>
				<table class="partyStrengthsTable">
					<tr>
						<td colspan="2">
							<div style="color: red;font-weight:bold;" id="errorMessageDiv">
								<s:actionerror />
								<s:fielderror />
								<s:actionmessage/>		
     						</div>
						</td>
					</tr>
				</table>
				</div>
				<form name="partyStrengthResultsAction" action="partyStrengthResultsAction" method="POST">
				<table id="partyStrenghtsTable" width="97%;">		
					
						<tr>
							<td align="left" class="tdStyle">Election Type</td>
							<td align="left">
								<select id="electionTypeSelect" name="electionType"  style="width:130px;"  onchange="getStatesInPS(this.options[this.selectedIndex].value)">
									<option value="Assembly">Assembly</option>		
									<option value="Parliament">Parliament</option>									
								</select>
							</td>
						
							<td align="left" id="selectStateId" class="tdStyle">Select State</td>
							<td align="left" id="stateTd">
								<select id="stateSelect" name="state" class = "selectWidth">								
										<option value="1">Andhra Pradesh</option>									
								</select>
							</td>
						
							<td align="left" class="tdStyle">No.of Previous Election Years</td>
							<td align="left" id="electionTypeTd">
								<select id="electionYearsSelect" name="electionYears" class = "selectWidth">																
															
								</select>								
							</td>
						
							<td align="left" class="tdStyle">Select Party</td>
							<td align="left" id="partySelectTd">												
								<select id="partySelect" name="party" class = "selectWidth">																
									 <option value="0">All Party</option>								
								</select>
							</td>
						</tr>
						
						<tr>
							<td colspan="5">
								<div id="busyImage" style="margin-top:20px;margin-left:300px;">				
					<div style="margin-left:5px;"> Loading Data Please Wait..</div><br/>
					<img id="ajaxImg" height="13" width="100" style="margin-left:10px;margin-bottom:5px;" src="<%=request.getContextPath()%>/images/icons/goldAjaxLoad.gif"/>							
			</div>	
							</td>
						</tr>
						
					</table>
							
				</form>	
                         	
			</div>	
			<table>
				<tr>
		 			<td align="left">
		 				<div id="errorMessage"></div>
		 			</td>
		 		</tr> 
			</table>
			
			<div style="border: 2px solid #d3d3d3;
    margin:auto;
   padding: 21px;
    width: 950px;
		
		background: none repeat scroll 0 0 #ffffff;">
			<table >
				<tr>
					<td>
						<div id="headerDiv" align="left" class="completePartyDetails" style="margin-bottom:20px;"></div>
					</td>
		 		</tr> 
		 		<tr>
					<td>
						<div id="electionYearsDiv" align="left" style="margin-bottom:20px;font-family:verdana,san-serif;"></div>
					</td>
		 		</tr>
		 		<tr>
					<td>
						<div id="overViewDiv" align="left" style="margin-left:28px;margin-bottom:20px;"></div>
					</td>
		 		</tr> 
			</table>
			
			<div id="main_container_div" style="display:none;margin-left:50px;margin-top:10px;" align="left">
			 <table>
			 	<tr>
			 		<td>
			 			<div id="required_const_main" style="margin-bottom:15px;">	
							<div id="required_const_head">
								<table border="0" cellpadding="0" cellspacing="0" style="width:101%;">
									<tr>
										<td width="30px"><img  width="30" height="36" src="images/icons/districtPage/header_left.gif"/></td>
										<td><div class="districtPageRoundedHeaders_center"> <div id="requiredConstituenciesCount"></div></div></td>
										<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif"/></td>
									</tr>
								</table>
							</div>
							<div id="noDataDiv" style="display:none;font-weight:bold;font-size:12px;border: 2px solid #CFD6DF;color:red;"><span style="margin-left:40px">Party doesn't exist for the elections
happened before 2009.</span></div>
							<div id="required_const_body">
									<div id="newConstAncSpan" class="mandalNamesDiv">		
									<table>		
										<tr>
											<td>
												<div id="searchByDistrict" align="left"></div>
											</td> 
											<td>
												<div id="searchByConstituency" align="left"></div>
											</td> 
										</tr>								
										<tr>
											<td>
												<div id="dataTableBuild" class="yui-skin-sam" align="left"></div>
											</td> 
										</tr>
									</table>
								</div>						
							</div>
						</div>
					</td>
				</tr>
								
				<tr>
			 		<td>
			 			<div id="new_const_main">		
							<div id="new_const_head">
								<table border="0" cellpadding="0" cellspacing="0" style="width:101%;">
									<tr>
										<td width="30px"><img  width="30" height="36" src="images/icons/districtPage/header_left.gif"/></td>
										<td><div class="districtPageRoundedHeaders_center" > <div id="newConstituenciesCount"></div></div></td>
										<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif"/></td>
									</tr>
								</table>
							</div>
							<div id="new_const_body">
									<div id="newConstAncSpan" class="mandalNamesDiv">		
									<table>
									<tr>
											<td>
												<table width="100%">
											   <tr>
													<td width="60%" valign="top" style="padding-top: 50px;"><div id="partyOverViewDetailsForNewConstituencies" style="font-family:verdana;font-size:14px;font-weight:bold;"></div></td>
													<td width="40%"><div  id="partyOverViewChartDetailsForNewConstituencies"></div></td>
											   </tr>
											   
											</table>
											</td> 
										</tr>
										<tr>
											<td>
												<div id="dataTableBuildForNewConstituencies" class="yui-skin-sam" align="left"></div>
											</td>
										</tr> 
										<tr>
											<td>
												<div id="othersInfoForNewConstituencies" align="left"></div>
											</td> 
										</tr>
									</table>
								</div>						
							</div>
						</div>
					</td>
				</tr>
				
				<tr>
			 		<td>
			 			<div id="remaining_const_main">	
							<div id="remaining_const_head">
								<table border="0" cellpadding="0" cellspacing="0" style="width:101%;">
									<tr>
										<td width="30px"><img  width="30" height="36" src="images/icons/districtPage/header_left.gif"/></td>
										<td><div class="districtPageRoundedHeaders_center"><div id="delimitationConstituenciesCount"></div></div></td>
										<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif"/></td>
									</tr>
								</table>
							</div>
							<div id="remaining_const_body">
									<div id="newConstAncSpan" class="mandalNamesDiv">		
									<table>
										<tr>
											<td>
												<div id="dataTableBuildForRemainingConstituencies" class="yui-skin-sam" align="left"></div>
											</td> 
										</tr>
									</table>
								</div>						
							</div>
						</div>
					</td>
				</tr>
			</table>
			</div>
		</div>		
	</div>
<script type="text/javascript">
	populateDefaultDetails();
	getStatesOfAp('Assembly');
	getDefaultElectionYearsAjaxAction();
	getDefaultParties();
	buildSearchCriteria('Assembly');
	//validateAndForwardToAction();
</script>

</body>
</html>