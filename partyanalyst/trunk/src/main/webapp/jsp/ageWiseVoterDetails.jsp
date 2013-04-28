<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<!-- YUI Dependency files (Start) -->
<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script>  
<script type="text/javascript" src="js/yahoo/element-min.js"></script> 	
<script src="js/yahoo/resize-min.js"></script> 
<script src="js/yahoo/layout-min.js"></script>  
<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
<script type="text/javascript" src="js/json/json-min.js"></script>
<script type="text/javascript" src="js/yahoo/connection-min.js"></script>  
<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>   
<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
<!-- Skin CSS files resize.css must load before layout.css -->  
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
<!-- YUI Dependency files (End) -->
<script type="text/javascript" src="js/highcharts/js/highcharts.js"></script>

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
<script type="text/javascript" src="js/myCustChart.js"></script>
<style type="text/css">
#voterDetailsNote{
  margin-bottom:5px;
}

#ageAndgenderWiseDetails th,#agewiseDetails th,#tableDiv th{text-align:center;}
#voterAgeAngGenderwiseDetailsNoteInPercent .table th, .table td{padding:6px;}
#voterAgeAngGenderwiseDetailsInPercent th{text-align:center;}
.widget h4, h2{font-family:arial;}
#ageAndgenderWiseDetails th, #agewiseDetails th, #tableDiv th , #voterAgeAngGenderwiseDetailsNoteInPercent th ,#voterAgeAngGenderwiseDetailsInPercent th{
    background-color: #D9EDF7;
    font-family: verdana;
    font-size: 14px;
    font-weight: normal;
    text-align: center;
}
.ageTable
{
	width: 104%; 
	max-width: 104%; 
	margin: 1px -18px 1px 10px;
	border: 2px solid powderblue;
}
#headingId
{
	background-color: #05A8E9;
    border-radius: 4px 4px 4px 4px;
    color:snow;
    font-family: arial;
    height: 27px;
    margin-left: 4px;
    padding-top: 11px;
    width: 947px;
}
table.dataTable tr.odd {
    background-color: #ffffff;
}
table.dataTable tr.even {
    background-color:#EdF5FF;
}
table.dataTable tr.odd td.sorting_1 {
    background-color: #ffffff;
}
table.dataTable tr.even td.sorting_1 {
    background-color: #EdF5FF;
}
.dataTables_filter {
    padding:5px;
}

table.dataTable td {
    font-family: arial;
    font-size: 16px;
    font-weight: normal;
    padding: 3px 10px;
}
</style>
<title>Age Wise Voters Details</title>
<script type="text/javascript">
/*
	These are the global variables to access any wherethis jsp
*/
var type               =  ${type};
var constituencyId     =  '${constituencyId}';
var publicationDateId  =  '${publicationDateId}';
var mandalId           =  '${mandalId}';
var panchayatId        =  '${panchayatId}';
var buildType          =  '${buildType}';
var name               =  '${name}';
var retrieveType       =  ${retrieveType};
var publicationYear    =  '${publicationYear}';
var startNumber        =  '${startNumber}';
/*
	This Condition is used for checking for Constituency level Age wise analysis
*/
if(type == "constituency")
{
	var jsObj=
				{					
					constituencyId:constituencyId,
					publicationDateId:publicationDateId,
					mandalId:'0',
					boothId:'0',
					panchayatId:'0',
					name:name,
					retrieveType:retrieveType,
				    type:"constituency",
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;						
		
		callAjax(jsObj,url);
}
/*
	This Condition is used for checking for Mandal level Age wise analysis
*/
else if(type == "mandal")
{
	if(startNumber == "2"){

			var jsObj=
					{
						constituencyId:constituencyId,
						publicationDateId:publicationDateId,
						mandalId:mandalId,
						boothId:'0',
						panchayatId:'0',
						name:name,
						retrieveType:retrieveType,
						type:"mandal"
						
					};
		}else if(startNumber == "1"){

			var jsObj=
					{
					constituencyId:constituencyId,
					mandalId:mandalId,
					publicationDateId:publicationDateId,
					name:name,
					boothId:0,
					panchayatId:'0',
					retrieveType:retrieveType,
					type:"localElectionBody"
						
					};
		}

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

		callAjax(jsObj,url);
}
/*
	This Condition is used for checking for Panchayat level Age wise analysis
*/
else if(type == "panchayat")
{
	var jsObj=
				{
			        constituencyId:constituencyId,
					mandalId:'0',
					boothId:'0',
					panchayatId:panchayatId,
					publicationDateId:publicationDateId,
					name:name,
					retrieveType:retrieveType,
					buildType:buildType,
					type:"panchayat"
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

      callAjax(jsObj,url);
} 
/*
	This Condition is used for checking for Ward level Age wise analysis
*/
else if(type == "ward")
{
	var jsObj=
				{
			        constituencyId:constituencyId,
					mandalId:'0',
					boothId:'0',
					panchayatId:panchayatId,
					publicationDateId:publicationDateId,
					name:name,
					retrieveType:retrieveType,
					type:"ward"
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

      callAjax(jsObj,url);
}
/*
	This Condition is used for checking for Hamlet level Age wise analysis
*/
else if(type == "hamletLocalArea" || type == "hamletBooths" || type == "boothHamlets")
{
	var jsObj=
				{
			        constituencyId:constituencyId,
					mandalId:'0',
					boothId:'0',
					panchayatId:panchayatId,
					publicationDateId:publicationDateId,
					name:name,
					retrieveType:retrieveType,
					type:type
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

      callAjax(jsObj,url);
}
/*
	This Method is used for ajax call handling for given responce
*/
function callAjax(jsObj,url)
{
	var myResults;
	var callback = {			
 	success : function( o ) {
	try {								
		myResults = YAHOO.lang.JSON.parse(o.responseText);					
		if(jsObj.retrieveType == "all")
		{

			if(jsObj.type != "booth" && jsObj.type!= "hamlet" )
			{
				if(jsObj.type == "panchayat" || jsObj.type == "localElectionBody" || jsObj.type == "ward"|| jsObj.type == "hamletLocalArea"  || jsObj.type == "hamletBooths")
				{
					if(myResults.boothVotersDetails!=null && myResults.boothVotersDetails.length!=0)
					{
						buildVoterDetailsTable(myResults,jsObj.type,jsObj.retrieveType);
						buildAgewiseDetails(myResults,jsObj);
						buildAgeAndGenderWiseDetails(myResults,jsObj);
						buildAgeAndGenderWiseDetailsForPercent(myResults,jsObj);
					}
					else{
						$('#errorDiv').html('<span style="color: red;font-family: verdana;font-size: 28px;">No Data Avaliabe For That Selection</span>');
						$('#ageWiseInfoDiv').hide();
						
					}
				}else
				{
					buildVoterDetailsTable(myResults,jsObj.type,jsObj.retrieveType);
				    buildAgewiseDetails(myResults,jsObj);
					buildAgeAndGenderWiseDetails(myResults,jsObj);
					buildAgeAndGenderWiseDetailsForPercent(myResults,jsObj);
				}
			}
			$("#ajaxImageDiv").css('display','none');
		}
		}catch (e) {}  
 		},
 		scope : this,
 		failure : function( o ) {
 		    //alert( "Failed to load result" + o.status + " " + o.statusText);
        }
 		};

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
}
/*
	This method is used for building the data table for  voter details
*/
function buildVoterDetailsTable(result,type,retrieveType){

	if(result.votersDetailsVO.length == 0){
		$('#votersDiv4').hide();
		return false;
	}
	else
	{
		$('#votersDiv4').show();
	}

	var noteString = '';
	if(type == "constituency")
	{
		noteString = name + " Constituency"; 
	}
	else if(type == "mandal")
		noteString = name ;
	else if(type == "panchayat")
		noteString = name;
	else if(type == "localElectionBody")
		noteString = name;
	else if(type == "hamlet")
	noteString =  "Hamlet" +" "+"Details";
	else 
		noteString = name;

	$('#voterDetailsNote').html('<div align="center"><h4 id="headingId">'+noteString+" "+"voters details"+' in '+publicationYear+'</h4></div>');
	
	var str='';
	str+='<table class="ageTable table table-bordered table-hover" id="ageWiseDetailsTable" >';
	
	str+='<thead class="info">'
	str+='<tr>'
	str+='<th rowspan="2" id="ageRangeId">Age Range</th>';
	str+='<th colspan="2">Total Voters</th>';
	str+='<th colspan="2">Male</th>';
	str+='<th colspan="2">Female</th>';
	//str+='<th colspan="2">UnKnown</th>';
	str+='</tr>';
    
	str+='<tr>';
	str+='<th>Total Voters</th>';
	str+='<th>Total Percentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	//str+='<th>Voters</th>';
	//str+='<th>Percentage</th>';
	str+='</tr>';
	str+='</thead><tbody>';
	if(result.votersDetailsVO == null ||result.votersDetailsVO.length ==0 ){
	str='<span style="color:red">No Data Available</span>';
	//$("#ageLink").hide();
	return;
}
	for(var i in result.votersDetailsVO){

	
	str+='<tr>';
	str+='<td>'+result.votersDetailsVO[i].ageRange+'</td>';
	str+='<td>'+result.votersDetailsVO[i].totalVoters+'</td>';

	if(result.votersDetailsVO[i].totalVotersPercent != null)
		 str+='<td>'+result.votersDetailsVO[i].totalVotersPercent.toFixed(2)+'</td>';	 
	else
		str+='<td>0.00</td>';
	

	str+='<td>'+result.votersDetailsVO[i].totalMaleVoters+'</td>';
    
	if(result.votersDetailsVO[i].totalMaleVotersPercent != null)
		str+='<td>'+result.votersDetailsVO[i].totalMaleVotersPercent.toFixed(2)+'</td>';
	else
		str+='<td>0.00</td>';

	str+='<td>'+result.votersDetailsVO[i].totalFemaleVoters+'</td>';

	if(result.votersDetailsVO[i].totalFemaleVotersPercent != null)
	  str+='<td>'+result.votersDetailsVO[i].totalFemaleVotersPercent.toFixed(2)+'</td>';
	else
		str+='<td>0.00</td>';
		
	str+='</tr>';

	}

	str+='</tbody>';
	str+='</table>';
	$('#tableDiv').html(str);
	$('#ageWiseDetailsTable').dataTable();
	if(retrieveType == "all"){
		$('#tableDiv').css('display','block');
		$('#voterDetailsNote').css('display','block');
	}
}
var yaxisOpt = new Array();
var xaxisOpt =  new Array("18-25","26-35","36-45","46-60","60-Above");

var YDataObject = new Array();

/*
	This method is used for building the data table for  voter age details and there percentage range
*/
function buildAgewiseDetails(results , obj){

   var type = obj.type;
   var innerResults;
   var noteString;
	if(type == "constituency"){
		innerResults = results.mandalsVotersDetails;
		noteString = "Mandal wise voters age details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "mandal"){
		innerResults = results.panchayatVotersDetails;
			noteString = "Panchayat wise voters age details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "panchayat"){
		innerResults = results.boothVotersDetails;
		if(buildType == "hamlet")
		noteString = "Hamlet wise voters age details of "+obj.name+" in "+publicationYear;
		else
	  	noteString = "Booth wise voters age details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;
		if( results.boothVotersDetails[0].muncipalityType == "Greater Municipal Corp")
		   noteString = "Ward wise voter age details of "+obj.name+" in "+publicationYear;
		else
			 noteString = "Booth wise voter age details of "+obj.name+" in "+publicationYear;
	}
   else if(type == "ward"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth wise voter age details of "+obj.name+" in "+publicationYear;
	}
	else if(type="hamlet" && obj.type == "hamletLocalArea"){
		innerResults = results.boothVotersDetails;
		noteString = "LocalArea wise voter age details of "+obj.name+" in "+publicationYear;
	}
	else if( type="hamlet" && obj.type == "hamletBooths"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth wise voter age details of "+obj.name+" in "+publicationYear;
	}
	else if( type="booth" && obj.type == "boothHamlets"){
		innerResults = results.boothVotersDetails;
		noteString = "Hamlet wise voter age details of "+obj.name+" in "+publicationYear;
	}

	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgewiseDetailsNote').html('<div align="center"><h4 id="headingId">'+noteString+'</h4></div>');

	var str='';
	str+='<table  class="ageTable table table-bordered table-hover" id="mandalWiseVoterAgeTable" >';
	str+='<thead class="info">';
	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	  	if(buildType == "hamlet")
		str+='<th rowspan="2">HamletName</th>';
		else
	  str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody"){
		if( results.boothVotersDetails[0].muncipalityType == "Greater Municipal Corp")
	     str+='<th rowspan="2">Ward</th>';
		else
 		 str+='<th rowspan="2">Booth</th>';
	}
	else if(type == "ward" || (type="hamlet" && obj.type == "hamletBooths"))
	   str+='<th rowspan="2">Booth No</th>';
	   else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<th rowspan="2">LocalArea</th>';
	   
	else if( type="booth" && obj.type == "boothHamlets"){
		str+='<th rowspan="2">HamletName</th>';
	}
	
	
//18111	 
  
	str+='<th  rowspan="2">Total Voters</th>';
	str+='<th colspan="2">18-25</th>';
	str+='<th colspan="2">26-35</th>';
	str+='<th colspan="2">36-45</th>';
	str+='<th colspan="2">46-60</th>';
	str+='<th colspan="2">60-Above</th>';
	str+='</tr>';
	str+='<tr>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	
		  var oldstr ="";

for(var i=0;i<innerResults.length;i++){

var YDataObjectTemp = new Object();
  if(innerResults[i].totalVotersFor18To25 != null){
	str+='<tr>';

	if(type == "constituency")
	 str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	 str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	 	if(buildType == "hamlet")
		str+='<td>'+innerResults[i].hamletName+'</td>';
		else
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	 str+='<td>'+innerResults[i].boothName+'</td>';
    else if(type == "ward")
	 str+='<td>'+innerResults[i].boothName+'</td>';
      else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<td>'+innerResults[i].localityName+'</td>';
	    else if((type="hamlet" && obj.type == "hamletBooths") ||(type="booth" && obj.type == "boothHamlets") )
	   str+='<td>'+innerResults[i].hamletName+'</td>';
	  
	  /*  var mystr = str;
	  
       if(i != 0)
	   {
	   
     mystr= mystr.replace(oldstr);
	 oldstr = str;
	   } else
	     oldstr=mystr; */
	   	 var str4 = str.match(/(<tr><td>(.*?)<\/td>)/g);
		
       var str2 = str4[i].replace("<tr><td>","").replace("</td>","");
	   YDataObjectTemp['name'] = str2;
	   var ageTemp = new Object();
	 
	   ageTemp['18-25']   =      innerResults[i].totalVotersFor18To25;
	   ageTemp['26-35']   =      innerResults[i].totalVotersFor26To35;
		ageTemp['36-45']  =	   innerResults[i].totalVotersFor36To45;
		ageTemp['46-60']   =   innerResults[i].totalVotersFor46To60;
		ageTemp['60-Above'] =	   innerResults[i].totalVotersForAbove60;
	   YDataObjectTemp['data'] = ageTemp;
	   YDataObject.push(YDataObjectTemp);
	   
	str+='<td>'+innerResults[i].totalVoters+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor18To25+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor18To25+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor26To35+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor26To35+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor36To45+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor36To45+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor46To60+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor46To60+'</td>';
	str+='<td>'+innerResults[i].totalVotersForAbove60+'</td>';
	str+='<td>'+innerResults[i].votersPercentForAbove60+'</td>';
	str+='</tr>';
  }
}
str+='</tbody>';
str+='</table>';
var res = str.match(/(<tr><td>(.*?)<\/td>)/g);
for (var k in res )
{
yaxisOpt.push(res[k].replace("<tr><td>","").replace("</td>",""));
}

$('#agewiseDetails').html(str);

$('#mandalWiseVoterAgeTable').dataTable({
		"aaSorting": [[ 1, "desc" ]]
		});
	var utilObject = new Object();
		utilObject['title'] = noteString;
		utilObject['ytitle'] = 'No of Voters';
		utilObject['tooltipText'] = ' Voters';
		
		//alert(xaxisOpt);
		// build linechart based on avarage 
	var newXaxis = buildHamletWiseCastResultsGraph( xaxisOpt,YDataObject);
	
	var newYaxis = buildColumnsForLineChart(newXaxis , YDataObject );
	
	var myChart  = buildMyLineChart(newXaxis , newYaxis , utilObject ,"ageGrid");
}
/*
	This method is used for building the data table for  voter age details and gender details
*/

function buildAgeAndGenderWiseDetails(results , obj){
    var type = obj.type;
	
	var innerResults;
	var noteString;


	if(type == "constituency"){
		innerResults = results.mandalsVotersDetails;
		noteString = "Mandal wise voters Age and gender details of "+obj.name+" in "+publicationYear;

	}
	else if(type == "mandal"){
		innerResults = results.panchayatVotersDetails;
		noteString = "Panchayat wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "panchayat"){
		innerResults = results.boothVotersDetails;
		if(buildType == "hamlet")
		noteString = "Hamlet wise voters Age and gender details of "+obj.name+" in "+publicationYear;
		else
	   	noteString = "Booth wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;

		if( results.boothVotersDetails[0].muncipalityType == "Greater Municipal Corp")
		  noteString = "Ward wise voters Age and gender details of "+obj.name+" in "+publicationYear;
		else
 		 noteString = "Booth wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
     else if(type == "ward"){
		innerResults = results.boothVotersDetails;
	   	noteString = "Booth wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
		else if(type="hamlet" && obj.type == "hamletLocalArea"){
		innerResults = results.boothVotersDetails;
		noteString = "LocalArea wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	else if( type="hamlet" && obj.type == "hamletBooths"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	else if( type="booth" && obj.type == "boothHamlets"){
		innerResults = results.boothVotersDetails;
		noteString = "Hamlet wise  voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgeAngGenderwiseDetailsNote').html('<div align="center"><h4 id="headingId">'+noteString+'</h4></div>');

	var str='';

	str+='	<table  class="ageTable table table-bordered table-hover" id="mandalWiseAgeAndGenderTable">';
	str+='<thead class="info">';
	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	  	if(buildType == "hamlet")
		 str+='<th rowspan="2">HamletName</th>';
		 else
	  str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody"){

    if( results.boothVotersDetails[0].muncipalityType == "Greater Municipal Corp")
	   str+='<th rowspan="2">Ward</th>';
	else
		str+='<th rowspan="2">Booth</th>';

	}
	else if(type == "ward" || (type="hamlet" && obj.type == "hamletBooths"))
	   str+='<th rowspan="2">Booth No</th>';
	    else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<th rowspan="2">LocalArea</th>';
	   
	else if( type="booth" && obj.type == "boothHamlets"){
		 str+='<th rowspan="2">HamletName</th>';
	}
	   
	str+='<th colspan="2">18-25</th>';
	str+='<th colspan="2">26-35</th>';
	str+='<th colspan="2">36-45</th>';
	str+='<th colspan="2">46-60</th>';
	str+='<th colspan="2">60-Above</th>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
for(var i=0;i<innerResults.length;i++){
  if(innerResults[i].totalMaleVotesFor18To25 != null){
	str+='<tr>';
	if(type == "constituency")
	str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
		if(buildType == "hamlet")
		str+='<td>'+innerResults[i].hamletName+'</td>';
		else
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	str+='<td>'+innerResults[i].boothName+'</td>';
    else if(type == "ward")
	str+='<td>'+innerResults[i].boothName+'</td>';
	 else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<td>'+innerResults[i].localityName+'</td>';
	    else if((type="hamlet" && obj.type == "hamletBooths")||(type="booth" && obj.type == "boothHamlets"))
	   str+='<td>'+innerResults[i].hamletName+'</td>';
	   
	str+='<td>'+innerResults[i].totalMaleVotesFor18To25+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor18To25+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor26To35+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor26To35+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor36To45+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor36To45+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor46To60+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor46To60+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersForAbove60+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersForAbove60+'</td>';

	str+='</tr>';
  }
}
   str+='</tbody>';
   str+='</table>';

$('#ageAndgenderWiseDetails').html(str);
$('#mandalWiseAgeAndGenderTable').dataTable({
		"aaSorting": [[ 1, "desc" ]]
		});
}
/*
	This method is used for building the data table for  voter age details and gender details in terms of percentage
*/
function buildAgeAndGenderWiseDetailsForPercent(results , obj){

    var type = obj.type;
	var innerResults;
	var noteString;
	$("#ajaxImageDiv").css('display','none');
	if(type == "constituency"){
		innerResults = results.mandalsVotersDetails;
		noteString = "Mandal wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;

	}
	else if(type == "mandal"){
		innerResults = results.panchayatVotersDetails;
		noteString = "Panchayat wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "panchayat"){
		innerResults = results.boothVotersDetails;
	   		if(buildType == "hamlet")
			noteString = "Hamlet wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
			else
		noteString = "Booth wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;
		 if( results.boothVotersDetails[0].muncipalityType == "Greater Municipal Corp")
		  noteString = "Ward wise  voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
		 else
		 noteString = "Booth wise  voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "ward"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth wise  voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if(type="hamlet" && obj.type == "hamletLocalArea"){
		innerResults = results.boothVotersDetails;
		noteString = "LocalArea wise  voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if( type="hamlet" && obj.type == "hamletBooths"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if( type="booth" && obj.type == "boothHamlets"){
		innerResults = results.boothVotersDetails;
		noteString = "Hamlet wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}

	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgeAngGenderwiseDetailsNoteInPercent').html('<div align="center"><h4 id="headingId">'+noteString+'</h4></div>');

	var str='';

	str+='	<table id="mandalWiseAgePercentageTable"         class="ageTable table table-bordered table-hover">';
	str+='<thead class="info">';
	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	if(buildType == "hamlet")
	 str+='<th rowspan="2">Hamlet Name</th>';
	else
	   str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody"){

		 if( results.boothVotersDetails[0].muncipalityType == "Greater Municipal Corp")
	       str+='<th rowspan="2">Ward</th>';
		 else
			 str+='<th rowspan="2">Booth</th>';
	}
	else if(type == "ward" ||(type="booth" && obj.type == "boothHamlets"))
	   str+='<th rowspan="2">Booth</th>';  
	       else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<th rowspan="2">LocalArea</th>';
	   else if( type="hamlet" && obj.type == "hamletBooths"){
		
		  str+='<th rowspan="2">Hamlet Name</th>';
	}
	   
	str+='<th colspan="3">18-25</th>';
	str+='<th colspan="3">26-35</th>';
	str+='<th colspan="3">36-45</th>';
	str+='<th colspan="3">46-60</th>';
	str+='<th colspan="3">60-Above</th>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
for(var i=0;i<innerResults.length;i++){
 if(innerResults[i].totalVotersFor18To25 != null){
	str+='<tr>';
	if(type == "constituency")
	str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	if(buildType == "hamlet")
	str+='<td>'+innerResults[i].hamletName+'</td>';
	else
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "ward")
	str+='<td>'+innerResults[i].boothName+'</td>';
	 else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<td>'+innerResults[i].localityName+'</td>';
	    else if((type="hamlet" && obj.type == "hamletBooths")||(type="booth" && obj.type == "boothHamlets"))
	   str+='<td>'+innerResults[i].hamletName+'</td>';

    str+='<td>'+innerResults[i].totalVotersFor18To25+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentFor18To25+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentFor18To25+'</td>';

    str+='<td>'+innerResults[i].totalVotersFor26To35+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentFor26To35+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentFor26To35+'</td>';

    str+='<td>'+innerResults[i].totalVotersFor36To45+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentFor36To45+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentFor36To45+'</td>';

    str+='<td>'+innerResults[i].totalVotersFor46To60+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentFor46To60+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentFor46To60+'</td>';

    str+='<td>'+innerResults[i].totalVotersForAbove60+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentForAbove60+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentForAbove60+'</td>';

	str+='</tr>';
  }
}
   str+='</tbody>';
   str+='</table>';

$('#voterAgeAngGenderwiseDetailsInPercent').html(str);

$('#mandalWiseAgePercentageTable').dataTable({
		"aaSorting": [[ 1, "desc" ]] 
		});
if(obj.retrieveType == "all"){
	$('#tableDiv').css('display','block');
	$('#voterDetailsNote').css('display','block');      
}

if(type == "constituency" || type == "mandal")
{
$("#AgeWiseNoteDiv").css("display","block"); 
$("#AgeWiseNoteDiv").html('<font style="font-family:verdana;font-size:12px;"> <strong>Note : </strong> To View Family wise Voter Details Select Report Level Panchayat/Polling Station</font>');
}
}
</script>
</head>
<body>
<div id="errorDiv" align="center"></div>
<div id="ageGrid" align="center"></div>
<div id="ajaxImageDiv" align="center" style="margin-top: 100px;"><img src="./images/icons/goldAjaxLoad.gif" alt="Processing Image"/> </div>
<div id="ageWiseVotersDetailsOuterDiv">
	<div id='ageWiseInfoDiv' class=""  style="height:500px;">
	<br><br>
	<div id="ageWiseVotersBasicInfoSubChartDiv" style="margin-left:100px;" ></div>
	<div class="thumbnail" style="margin:15px 10px;">
	<div id="voterDetailsNote" class="noteDiv thumbnail breadcrumb" style="display:none;text-align:center;"></div>
	<div id="tableDiv" style="padding:10px;display:none;overflow-x:scroll" class="voterDetails"></div>
	</div>
	<div class="thumbnail" style="margin:15px 10px;">
	<div id="voterAgewiseDetailsNote" class="noteDiv thumbnail breadcrumb" style="text-align:center;color:#3F3636;"></div>
	<div id="agewiseDetails" style="padding:10px;overflow-x:scroll;" class="voterDetails"></div>
	</div>
	<div class="thumbnail" style="margin:15px 10px;">
	<div id="voterAgeAngGenderwiseDetailsNote" class="noteDiv thumbnail breadcrumb" style="text-align:center;"></div>
	<div id="ageAndgenderWiseDetails" style="padding:10px;overflow-x:scroll;" class="voterDetails"> </div>
	</div>
	<div class="thumbnail" style="margin:15px 10px;">
	<div id="voterAgeAngGenderwiseDetailsNoteInPercent" class="noteDiv thumbnail breadcrumb" style="text-align:center;"></div>
	<div id="voterAgeAngGenderwiseDetailsInPercent" style="overflow-x:scroll;padding:10px;" class="voterDetails"></div>
	</div>
	<div id="AgeWiseNoteDiv" style="border: 1px solid #D3D3D3;
    display: block;margin-bottom: 14px;margin-left: 9px;    margin-top: 33px;padding: 19px;width: 851px;"></div>
	<div id="tableDiv"></div>
</div>
</div> </div>
</body>
</html>