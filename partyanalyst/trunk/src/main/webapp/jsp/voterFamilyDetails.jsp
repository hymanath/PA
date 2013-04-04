<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%-- <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script> --%>

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/voterAnalysis1.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/showGallaries1.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

	<!-- YUI Dependency files (End) -->
   <script type="text/javascript" src="http://www.google.com/jsapi"></script>
   <%--  <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/facescroll.js"></script>
	   <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/jquery.ui.touch-punch.min.js"></script> --%>

   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
   
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jquery.dataTables.js"></script>

<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">
<style>


/*#impFamilesBasicSubDetails table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#impFamilesBasicSubDetails table tr:nth-child(even){background:#EdF5FF;}

#impFamilesBasicSubDetails table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}*/



#impFamilesBasicSubDetails table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;margin-top:5px;margin-bottom:5px;}
#impFamilesBasicSubDetails table tr:nth-child(even){background:#EdF5FF;}
#impFamilesBasicSubDetails table tr:nth-child(odd){background:#ffffff;}
	#impFamilesBasicSubDetails table th {
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}

#impFamilesBasicSubDetails table td {
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
}

#impfamilydatatable.dataTable tr.odd {background-color: #FFF !important;}
#impfamilydatatable.dataTable tr.even td.sorting_1 {background:#DBEBFF !important;}
#impfamilydatatable.dataTable tr.odd td.sorting_1{background:#FFF !important;}
#impfamilydatatable.dataTable td {padding: 7px 10px;}
#impfamilydatatable{border:1px solid #000;}
#impfamilydatatable.dataTable thead th {background: none repeat scroll 0 0 #DBEBFF;padding: 10px 18px 10px 10px;}
#impfamilydatatable.dataTable tr.even{background:#DBEBFF !important;}


#impFamDtls table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}


#impFamDtls table tr:nth-child(even){background:#EdF5FF;}

#impFamDtls table tr:nth-child(odd){background:#ffffff;}
#impFamDtls table th{ background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}

.buttonsTop{
	  margin-top:20px;
	  width:880px;
	  text-align: center;
	}
	.buttonLeft{
	  margin-left:10px;
	}
	
	#impFamPancBothDtls,#impFamDtls{
 margin-top:12px;
 margin-left: auto;
    margin-right: auto;
    width:100%;
}
</style>
</head>

<body>

<div id="mainDiv" style="width:960px;margin-top:50px;margin-left:auto;margin-right:auto;">
<div id ="impFamilesBasicInfoSubChartDiv" style="border:1px solid black"></div>

<br>

<!--<div id ="impFamilesBasicSubDetailsTitle" ></div>-->
<div id="impFamilesBasicSubDetailsDiv" class="widget blue whitegloss" >
<h4 id="impFamilesBasicSubDetailsTitle"></h4>
<div id ="impFamilesBasicSubDetails" style="display:inline-block;width: 96%;color:#000;position:relative;margin-top: 0px;"></div>
</div>


<div id="descriptionDiv" ></div>
		<div id="impFamPancBothDtlsAgxImg" style="display:none;margin-left:361px;margin-top:10px;"><img src="images/icons/goldAjaxLoad.gif"/></div>
		<div id="impFamPancBothDtls"></div>
	   
		
		<div id="NoteDiv" class="breadcrumb"></div>
		
		<div id="impFamDtlsOuterPopUp" style="display:none;">
		   <div id="impFamDtlsTitle"></div>


 <h5>Check Required Fields To Show</h5>

          <div id="impFamilySelectedDetails" style="padding:10px 0 10px 27px;border:1px solid #c3c3c3;border-radius:5px;">

           

			<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="genderId">Gender</input></label>

			<label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="ageId">Age</input></label>

			 <label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="guardianNameId" >House No</input></label>

		    <label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="guardianNameId" >Guardian Name</input></label>

		    <label style="float:left;margin:0px 7px 4px 0px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="relationShipId">Relation Ship</input></label>
			
			<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes requiredAttrClass" style="margin:0px 7px 4px 0px;" id="mobileId  ">Mobile No</input></label>

			<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes notRequiredAttrClass" style="margin:0px 7px 4px 0px;" id="casteId">Caste</input></label>

			<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes notRequiredAttrClass" style="margin:0px 7px 4px 0px;" id="partyId">Party</input></label>

			<!--<label style="float:left;margin:2px;"><input type="checkbox" class="voterAttributes" id="moneyId">Money</input></label>-->
			<div id="impFamilySelectedDetails1"></div>

			<a class="btn" href="javaScript:{checkForAttributesToDisplay();}">Show Details</a>
		  </div>

           
		   <div id="impFamDtls"  class="yui-skin-sam yui-dt-sortable"></div>
	       <div class="buttonsTop"><span class="buttonLeft"><input class="btn" onclick="selectAll('familyMemberCheck')" type="button" value="Select All" /></span><span class="buttonLeft"><input onclick="deSelectAll('familyMemberCheck')" class="btn" type="button" value="De Select All" /></span><span class="buttonLeft"><input class="btn" type="button" value="Edit" onclick="getAllVoterFamiliesForEdit();"/></span></div>
		   <div id="multipleVoterFamiliesEditDiv"></div>
		</div>









</div>


<form id="getAllVoterFamiliesForEditForm" method="post" action="getMultipleFamilesInfoForEditAction.action" name="getAllVoterFamiliesForEditForm">
	   <input type="hidden" name="task" id="getAllVoterFamiliesForEditFormValues" />
</form>
<form id="getAllVoterFamiliesInfoForEditForm" method="post" action="getMultipleFamilesInfoForEditAction.action" name="getAllVoterFamiliesInfoForEditForm">
	   <input type="hidden" name="task" id="getAllVoterFamiliesInfoForEditFormValues" />
</form>
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
var votersLimitExist = false;
var publicationDateId = "${publicationDateId}";
var buildType = "${buildType}";
var id = "${id}";
var type = "${type}";
var impFamltypename="${typename}";
var maintype="${maintype}";
var constituencyId ="${constituencyId}";
var publicationYear = "${publicationYear}";
var impFamiliesEditArray = new Array();

function getImpFamiliesVotersForHamlet()
{
	var jsObj1=
			{
					
				type:"hamlet",
				id:id,
				publicationDateId:publicationDateId,
				typename:"",
				constituencyId:constituencyId,
				task:"importantFamiliesinfo"
	
			}
	var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj1);
			var url1 = "getImportantFamaliesDetailsForHamlet.action?"+rparam1;						
		callAjax(jsObj1,url1);


}

var buttonType ="impFamilies";

function getvotersBasicInfo(){


    if(type == "booth")
	buildType="hamlet";
	 impFamltype = type;
     impFamlId = id;
     impFamlpublicationDateId = publicationDateId;
     impFamltypename = impFamltypename;
	 if(type == "hamletBooth"){
	 hresult="booth";
	type="hamlet";
	
	}
	if(type == "hamletLocal"){
	type="hamlet";
	hresult="localArea";
	}
   
	   var jsObj1=
			{
					
				type:type,
				id:id,
				publicationDateId:publicationDateId,
				typename:impFamltypename,
				constituencyId:constituencyId,
				buildType:buildType,
				task:"importantFamiliesinfo"
	
			}
	var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj1);
			
		
         var url1 = "getImportantFamiliesInfoAction.action?"+rparam1;
									
		callAjax(jsObj1,url1);
}
function getImpFamiliesVotersToShow(){

	 $("#descriptionDiv").show();
	 if(type == 'panchayat' || type == 'booth' ){
	   
	 var reqtype = type;
	 if(type == 'booth'){
	    reqtype = 'pollingstation';
	 }
	 //showAjaxImgDiv('ajaxImageDiv');
	 $("#impFamPancBothDtlsAgxImg").show();
	    var jsObj2=
			{
					
				type:reqtype,
				id:id,
				publicationDateId:publicationDateId,
				typename:impFamltypename,
                buildType:buildType,
				task:"gettotalimpfamlies"
	
			}
	   var rparam2 ="task="+YAHOO.lang.JSON.stringify(jsObj2);
			var url2 = "votersFamilyDetailsAction.action?"+rparam2;						
		callAjax(jsObj2,url2);
	}
		
	
	}

	
function callAjax(jsObj,url)
		{
			
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task == "importantFamiliesinfo")
								{ 
									 
									buildImpFamilesChart(myResults);

									 if(myResults.subList != null && myResults.subList.length > 0)
							  		  {

										buildTableForImpFamilesMandal(myResults.subList,myResults.name,myResults.type);
										//impFamilesVariableDescription();
									  }


									
								}

								else if(jsObj.task == "importantFamiliesinfo1")
								{ 
								 buildFamilyMembersForAnil(myResults,jsObj.publicationDateId,jsObj.type);
								}



								else if(jsObj.task == "gettotalimpfamlies"  )
								{   
								 $("#impFamPancBothDtlsAgxImg").hide();
								    buildFamilyMembers(myResults,jsObj,jsObj.type);
								}

								else if(jsObj.task =="editAllFamilies"){
								    if(myResults == "notLogged"){
									 openDialogForLoginWindow();
									}else{
										pResults = myResults;
								     buildVotersInFamily(myResults);
									}
								}
								else if(jsObj.task =="allFamiliesEditInfo"){
								    if(myResults == "notLogged"){
									 openDialogForLoginWindow();
									}else{
								     buildVotersInFamilyForEdit(myResults);
									}
								}

								else if(jsObj.task == "getUserCategories")
								{
									buildCategories(myResults);
								}

								else if(jsObj.task == "getVotersInAFamily")
								{
								    buildVotersInFamily(myResults,jsObj.hno);
									pResults = myResults;
								}
								}catch (e) {
							     $("#votersEditSaveAjaxImg").hide();
							     $("#votersEditSaveButtnImg").removeAttr("disabled");
								}  

 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}
	function buildCategories(results){

    var str='';

	for(var i in results){
      
       str+='<label style="float:left;margin:3px;"><input type="checkbox" style="margin:0px 7px 4px 0px;" class="attributeTypeClassIni1" value="'+results[i].id+','+results[i].name+'"/>'+results[i].name+'</label>';
	}

	$('#impFamilySelectedDetails1').html(str);

}
function buildImpFamilesChart(chartInfo)
{

	var data = google.visualization.arrayToDataTable([
			  ['Task', 'Percentage'],
			  ['Families Below 3 Voters',  chartInfo.below3perc],
			  ['Families Between 4-6 Voters', chartInfo.betwn4to6perc],
			  ['Families Between 7-10 Voters',  chartInfo.betwn7to10perc],
			  ['Families Above 10 Voters', chartInfo.above10perc]
			]);

	// Set chart options
	var title = " Family wise Voters details chart of "+chartInfo.name+" "+chartInfo.type+" in "+publicationYear+"";
	var options = {'title':title,
	'width':958,
	'height':280};
	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.PieChart(document.getElementById('impFamilesBasicInfoSubChartDiv'));
	chart.draw(data, options);
}

function buildTableForImpFamilesMandal(impFamilesData,name,type)
{
	

  var impFamiList = new Array();
  for(var i in impFamilesData){
     var data={};
	 
	 data["name"] = impFamilesData[i].name;  
	 data["below3"] = impFamilesData[i].below3;
	 data["below3perc"] = impFamilesData[i].below3perc;
	 data["betwn4to6"] = impFamilesData[i].betwn4to6;
	 data["betwn4to6perc"] = impFamilesData[i].betwn4to6perc;
	 data["betwn7to10"] = impFamilesData[i].betwn7to10;
	 data["betwn7to10perc"] = impFamilesData[i].betwn7to10perc;
	 data["above10"] = impFamilesData[i].above10;
	 data["above10perc"] = impFamilesData[i].above10perc;
	 data["totalVoters"] =  impFamilesData[i].totalVoters;
	 data["totalFemaleVoters"] = impFamilesData[i].totalFemaleVoters;
	 data["totalMaleVoters"] = impFamilesData[i].totalMaleVoters;
	 impFamiList.push(data);
  }
  var reqtytle ="Name";
  for(var t in impFamilesData){
     if(impFamilesData[t].type != null)
	   reqtytle = impFamilesData[t].type;
  }
  $("#impFamilesBasicSubDetailsTitle").html(reqtytle+" wise Voters Family analysis of "+name+" "+type+" in "+publicationYear+"");
  
  var impFamilesColumnDefs = [
    {key:"name", label: ""+reqtytle+"", sortable: true},
	{key:"totalVoters", label:"Total",sortable: true},
	{key:"totalMaleVoters", label:"Male Voters",sortable: true},
	{key:"totalFemaleVoters", label:"Female Voters",sortable: true},
    {key:"below3", label: "<3", formatter:"number", sortable: true},
    {key:"below3perc", label: "<3 %", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"betwn4to6", label: "4 to 6", formatter:"number", sortable: true},
    {key:"betwn4to6perc", label: "4 to 6%", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"betwn7to10", label: "7 to 10", formatter:"number", sortable: true},
    {key:"betwn7to10perc", label: "7 to 10 %", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"above10", label: ">10", formatter:"number",sortable:true},
    {key:"above10perc", label: ">10 %", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true}
  ];
var impFamilesDataSource = new YAHOO.util.DataSource(impFamiList);
impFamilesDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
impFamilesDataSource.responseSchema = {
fields: [{key:"name"},{key:"below3", parser:"number"},{key:"totalVoters"},{key:"totalMaleVoters"},{key:"totalFemaleVoters"},{key:"below3perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"betwn4to6", parser:"number"},{key:"betwn4to6perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"betwn7to10", parser:"number"},{key:"betwn7to10perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"above10", parser:"number"},{key:"above10perc", parser:YAHOO.util.DataSourceBase.parseNumber}]
};
var myConfigs = {
};
var impFamilesDataTable = new YAHOO.widget.DataTable("impFamilesBasicSubDetails", impFamilesColumnDefs,
impFamilesDataSource, myConfigs);
return {
oDS: impFamilesDataSource,
oDT: impFamilesDataTable
};
if(type == "constituency" || type == "Mandal/Tehsil")
	{
	$("#NoteDiv").css("display","block"); 
	$("#NoteDiv").html('<font style="font-family:verdana;font-size:12px;"> <strong>Note : </strong> To View Family wise Voter Details Select Report Level Panchayat/Polling Station</font>');
	}

}
function impFamilesVariableDescription()
{ 
 $('#descriptionDiv').html('');
  var div = $('<div class="descriptionInnerDiv"></div>');
  div.append('<span> <b>3 -</b> Families Below 3 Voters</span>');
  div.append('<span> <b>3% -</b> Families Below 3% Voters</span>');
  div.append('<span> <b>4 to 6 -</b> Families Between 4 to 6 Voters</span>');
  div.append('<span> <b>4 to 6 % -</b> Families Between 4-6 % Voters</span>');
  div.append('<span> <b>7 to 10 -</b> Families Between 7 to 10 Voters</span>');
  div.append('<span> <b>7 to 10 % -</b> Families Between 7-10 % Voters</span>');
  div.append('<span> <b>10 - </b> Families Above 10 Voters</span>');
  div.append('<span> <b>10% -</b> Families Above 10% Voters</span>');
  $("#descriptionDiv").append(div).css("display","block");

}
function impFamilesStaticTable(myresults,jsObj)
{
	//if(jsObj.type=="hamlet")
	//$('#impFamiliesMoreInfoButn').hide();
	
	
	
	var value=0;
	var str='';
	var type = jsObj.type;	
	str+='<div class="impFamilesMainDiv row" >';
	
	$("#impFamiliesTitle").html(" "+jsObj.typename+" Family Wise Statistics in "+publicationYear+"");
		
	str += '<div class="span3"><ul class="FamiliyList"> <li> <div style="width:68%;float:left;">Total Voters </div> <span style="clear:left;">: '+myresults.totalVoters+'</span></li>';
		if(myresults.totalFamalies==null){
		str+='<li><div style="width:68%;float:left;"> Total Families </div> : <span style="clear:left;">'+value+'</span> </li> ';
	}
	else{
	str+='<li><div style="width:68%;float:left;"> Total Families </div> : <span style="clear:left;">'+myresults.totalFamalies+'</span></li> ';
	}

	if(myresults.totalMaleVoters==null){
		str +='<li><div style="width:68%;float:left;"> Total Male Voters </div> : <span style="clear:left;"> '+value+'</span></li> ';
	}
	else{
	str +='<li><div style="width:68%;float:left;"> Total Male Voters </div> : <span style="clear:left;">'+myresults.totalMaleVoters+'</span> </li> ';
	}

	if(myresults.totalFemaleVoters==null){
		str +='<li><div style="width:68%;float:left;">Total Female Voters </div>: <span style="clear:left;">'+value+'</span> </li> ';
	}
	else{
	str +='<li> <div style="width:68%;float:left;">Total Female Voters </div>: <span style="clear:left;">'+myresults.totalFemaleVoters+'</span> </li> ';
	}
	
	str +='</ul></div>';
	str+='<div class="span9" style="margin-top:6px;">';
	str+='<table class="table table-bordered table-hover">';
	str+='<thead class="info"><tr>';
	str+='<th>Report</th><th>Voters Below 3</th><th>Voters Between 4-6</th><th>Voters Between 7-10</th><th>Above 10 Voters</th>';
	str+='</tr></thead><tbody>';
	str+='<tr>';
	str+='<th>No of Familes</th>';

	if(myresults.below3 != null)
		str+='<td>'+myresults.below3+'</td>';
	else
		str+='<td>'+0+'</td>';

	if(myresults.betwn4to6 != null)
		str+='<td>'+myresults.betwn4to6+'</td>';
	else
		str+='<td>'+0+'</td>';

	if(myresults.betwn7to10 != null)
		str+='<td>'+myresults.betwn7to10+'</td>';
	else
		str+='<td>'+0+'</td>';

	if(myresults.above10 != null)
		str+='<td>'+myresults.above10+'</td>';
	else
		str+='<td>'+0+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Familes %</th>';

	if(myresults.below3perc != null)
		str+='<td>'+myresults.below3perc+'%</td>';
	if(myresults.betwn4to6perc != null)
		str+='<td>'+myresults.betwn4to6perc+'%</td>';
	if(myresults.betwn7to10perc != null)
		str+='<td>'+myresults.betwn7to10perc+'%</td>';
	if(myresults.above10perc != null)
		str+='<td>'+myresults.above10perc+'%</td>';

	str+='<tr>';
	str+='</tbody></table>';
	str+='</div>';
	$("#impFamilesBasicDetails").html(str);
	if(type == "constituency" || type == "mandal")
	{
	//$("#NoteDiv").css("display","block"); 
	//$("#NoteDiv").html('<font style="font-family:verdana;font-size:12px;"> <strong>Note : </strong> To View Family wise Voter Details Select Report Level Panchayat/Polling Station</font>');
	}
}

 function buildFamilyMembersForAnil(impFamilesData,publicationDateId,type){
 
	var impFamiList = new Array();
  for(var i in impFamilesData){
     var data={};
	 
	 data["name"] = impFamilesData[i].name;  
	 data["below3"] = impFamilesData[i].below3;
	 data["below3perc"] = impFamilesData[i].below3perc;
	 data["betwn4to6"] = impFamilesData[i].betwn4to6;
	 data["betwn4to6perc"] = impFamilesData[i].betwn4to6perc;
	 data["betwn7to10"] = impFamilesData[i].betwn7to10;
	 data["betwn7to10perc"] = impFamilesData[i].betwn7to10perc;
	 data["above10"] = impFamilesData[i].above10;
	 data["above10perc"] = impFamilesData[i].above10perc;
	 data["totalVoters"] =  impFamilesData[i].totalVoters;
	 data["totalFemaleVoters"] = impFamilesData[i].totalFemaleVoters;
	 data["totalMaleVoters"] = impFamilesData[i].totalMaleVoters;
	 impFamiList.push(data);
  }
  var reqtytle ="Name";
  for(var t in impFamilesData){
     if(impFamilesData[t].type != null)
	   reqtytle = impFamilesData[t].type;
  }
  $("#impFamilesBasicSubDetailsTitle").html(reqtytle+" wise Voters Family analysis of "+name+" "+type+" in "+publicationYear+"");
  
  var impFamilesColumnDefs = [
    {key:"name", label: ""+reqtytle+"", sortable: true},
	{key:"totalVoters", label:"Total",sortable: true},
	{key:"totalMaleVoters", label:"Male Voters",sortable: true},
	{key:"totalFemaleVoters", label:"Female Voters",sortable: true},
    {key:"below3", label: "<3", formatter:"number", sortable: true},
    {key:"below3perc", label: "<3 %", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"betwn4to6", label: "4 to 6", formatter:"number", sortable: true},
    {key:"betwn4to6perc", label: "4 to 6%", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"betwn7to10", label: "7 to 10", formatter:"number", sortable: true},
    {key:"betwn7to10perc", label: "7 to 10 %", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"above10", label: ">10", formatter:"number",sortable:true},
    {key:"above10perc", label: ">10 %", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true}
  ];
var impFamilesDataSource = new YAHOO.util.DataSource(impFamiList);
impFamilesDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
impFamilesDataSource.responseSchema = {
fields: [{key:"name"},{key:"below3", parser:"number"},{key:"totalVoters"},{key:"totalMaleVoters"},{key:"totalFemaleVoters"},{key:"below3perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"betwn4to6", parser:"number"},{key:"betwn4to6perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"betwn7to10", parser:"number"},{key:"betwn7to10perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"above10", parser:"number"},{key:"above10perc", parser:YAHOO.util.DataSourceBase.parseNumber}]
};
var myConfigs = {
};
var impFamilesDataTable = new YAHOO.widget.DataTable("impFamilesBasicSubDetails", impFamilesColumnDefs,
impFamilesDataSource, myConfigs);
return {
oDS: impFamilesDataSource,
oDT: impFamilesDataTable
};
  } 




function  buildFamilyMembers(result,jsObj,type){
	var publicationDateId =   jsObj.publicationDateId;
 //debugger;
 //alert("ok");
	/* impFamiliesEditArray = new Array();
	var ajaxImageDiv =  document.getElementById('ajaxImageDiv');
	hideAjaxImgDiv('ajaxImageDiv');
    var name = "";
     if(type == "panchayat"){
	    name = $("#panchayatField option:selected").text();
	 }else{
	  //type = "";
	   name = $("#pollingStationField option:selected").text();
	 }*/
      var str ='<div id="impFamPancBothDtlstitle">Voters Family details in '+name+' '+type+' in '+publicationYear+'</div>';
	      str+=' <div><b style="font-size:14px;">Hint: Please select atmost 30 families to edit</b></div>';
          str+=' <div><input type="button" style="margin-bottom: 14px;margin-left: 20px;" class="btn" value="Edit all selected families" onclick="editSelectedFamilies();"/><input class="btn" type="button" value="UnSelectAll" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="clearAllCheckBoxes()"></input><input type="button" class="btn" value="Refresh" style="width:100px; margin-bottom:15px;margin-left: 10px;" onClick="getvotersFamileyInfo(\'impFamilies\',\'\')"></input><img alt="Processing Image" id="imgDiv" style="display:none;margin-left: 37px;margin-bottom: 12px;"src="./images/icons/search.gif"></div>';
		  str+=' <table id="impfamilydatatable" cellpadding="0" cellspacing="0" border="0" width="100%" style="border:1px solid black">';
          str+='  <thead>';
          str+='   <tr>';
		  str+='     <th>Select</th>';
         // str+='     <th>SNo</th>';
		 if(type == "panchayat" && jsObj.buildType =="hamlet" )
		  str+='     <th>Hamlet</th>';
		   if(type == "hamlet" )
		  str+='     <th>LocalArea</th>';
		  
		  str+='     <th>Booth</th>';
          str+='     <th>House No</th>';
          str+='     <th>Members In Family</th>';
		  str +='	 <th>Caste</th>';
		  str+='	 <th class="widthStyle">Eldest Person</th>';
		  str+='	 <th>Gender</th>';
		  str+='	 <th>Age</th>';
          str+='     <th class="widthStyle">Youngest Person</th>';
		  str+='	 <th>Gender</th>';
		  str+='	 <th>Age</th>';
          str+='   </tr>';
          str+='  </thead>';
          str+='  <tbody>';
	 for(var i in result){
	   var sno = parseInt(i)+1;
	      str +='   <tr>';
		  str +='		<td><input class="impFamilMulSel" id="impFamilSel'+sno+'" type="checkbox" onclick="populate(this.id,'+result[i].boothId+','+publicationDateId+',\''+result[i].houseNo+'\');"/></td>';
         // str +='		<td>'+sno+'</td>';
		  if(type == "panchayat" && jsObj.buildType =="hamlet" )
		  str +='		<td>'+result[i].hamletName+'</td>';
		    if(type == "hamlet" )
		  str +='		<td>'+result[i].localAreaName+'</td>';
		  
		  str +='		<td>'+result[i].boothName+'</td>';
          str +='		<td><a href="javascript:{}" title="Click here to view and edit members in family" onclick="getVotersInAFamily('+result[i].boothId+','+publicationDateId+',\''+result[i].houseNo+'\')">'+result[i].houseNo+'</a></td>';
          str +='		<td>'+result[i].numberOfPeople+'</td>';

		  str +='       <td>'+result[i].cast+'</td>';
          str +='		<td class="widthStyle">'+result[i].elder+'</td>';
		  str +='		<td>'+result[i].elderGender+'</td>';
		  str +='		<td>'+result[i].elderAge+'</td>';
          str +='		<td class="widthStyle">'+result[i].younger+'</td>';
		  str +='		<td>'+result[i].youngerGender+'</td>';
		  str +='		<td>'+result[i].youngerAge+'</td>';
          str+='   </tr>';
	 }
          str+='  </tbody>';
          str+=' </table>';
		  str+=' <div style="clear:both;"><b style="font-size:14px;">Hint: Please select atmost 30 families to edit</b></div>';
	      str+=' <div style="clear:both;"><input type="button" style="margin-top:16px;margin-left:20px;" class="btn" value="Edit all selected families" onclick="editSelectedFamilies();"/><input class="btn" type="button" value="UnSelectAll" style="width:100px; margin-bottom:-17px;margin-left: 10px;"onClick="clearAllCheckBoxes()"></input><input type="button" class="btn" value="Refresh" style="width:100px; margin-bottom:-17px;margin-left: 10px;" onClick="getvotersFamileyInfo(\'impFamilies\',\'\')"></input><img alt="Processing Image" id="imgDiv1" style="display:none;margin-top: 0px;"src="./images/icons/search.gif"></img></div>';

		  if((jsObj.buildType =="hamlet" && type == "panchayat") || (type == "hamlet"))
	       {
			  $('#impFamPancBothDtlsAgxImgForHamlet').html(str);
			  $('#impFamPancBothDtlsAgxImgForHamle').hide();
	      }
		  else
	       $("#impFamPancBothDtls").html(str);
	  
	  	$('#impfamilydatatable').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"bDestroy": true,
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null,null,null,null,null,null
     
	  
    ] 
		});
  }




function editSelectedFamilies(){
  if(impFamiliesEditArray.length > 0){
   if(impFamiliesEditArray.length > 30){
      alert("Please select atmost 30 families to edit");
      return;
   }
   totalCategories = 0;
     var jsObj=
	{
		selectedFamilies:impFamiliesEditArray,
		task:"editAllFamilies"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getMultipleFamilesInfoAction.action?"+rparam+"&save=";	
	callAjax(jsObj,url);
  }
}

  function clearAllCheckBoxes()
{
	impFamiliesEditArray = new Array();
	$('.impFamilMulSel').each(function(){
		$(this).attr("checked",false);
	});
	
}

function hideBoothDivs()
{
//$("#impFamilesBasicInfoSubChartDiv").html("");
//$("#impFamilesBasicSubDetailsTitle").html("");
//$("#descriptionDiv").html("");
$("#impFamPancBothDtls").html("");
 
}



function impFamilesAllInfoForHamletPopUp(){
	$('#impFamPancBothDtlsAgxImgForHamlet').show();
    $("#impFamilesAllInfoForHamletPopUp").dialog({
            modal: true,
            title: "<b>Voters Details</b>",
			width: 970,
            height: 600
           
        });
		 
		 var jsObj2=
			{
					
				type:maintype,
				id:mainreqid,
				publicationDateId:mainpublicationId,
				typename:impFamltypename,
				buildType:"hamlet",
				task:"gettotalimpfamlies"
	
			}
	   var rparam2 ="task="+YAHOO.lang.JSON.stringify(jsObj2);
			var url2 = "votersFamilyDetailsAction.action?"+rparam2;						
		callAjax(jsObj2,url2); 
		}

function buildVotersInFamily(results){
	  getUserCategories();

	$('.requiredAttrClass').each(function(){
	  $(this).attr('checked','checked');
    });

	$('.notRequiredAttrClass').each(function(){
		$(this).attr('checked',false);
	});
//console.log(results);
    $("#multipleVoterFamiliesEditDiv").html("");
   // $("#impFamDtlsTitle").html("<b>Voter Details</b>");
	YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		
		var id=oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		var name = oRecord.getData("name");
		
		elLiner.innerHTML ='<a id="openProblemEditFormId" onclick=" openProblemEditForm('+id+','+boothId+');">'+name+'</a>';
		
	}
	  YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	  {
		var name = oData;
		var id= oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+id+"'/><input type='hidden' class='selectedBoothId' value='"+boothId+"'/>";
					
	  };
     var votersResultColumnDefs = [ 		    	             
		    	            {key:"select", label: "Select", formatter:YAHOO.widget.DataTable.select},
							//{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", sortable: true,formatter:YAHOO.widget.DataTable.NameLink},
							{key:"gender", label: "Gender", sortable: true},
		    				{key:"age", label: "Age",sortable:true},
							{key:"houseNo", label: "House No",sortable:true},
							{key:"gaurdian", label: "Guardian Name",sortable:true},
							{key:"relationship", label: "Relationship",sortable:true},
							{key:"mobileNo",label:"MobileNo",sortable:true}
						]; 

    var myConfigs = { 
			    
				};
	var myDataSource = new YAHOO.util.DataSource(results);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","gender","age","houseNo","gaurdian","relationship","voterId","boothId"]
					};

		var familesDataSource = new YAHOO.widget.DataTable("impFamDtls", votersResultColumnDefs,myDataSource, myConfigs);
    $("#impFamDtlsOuterPopUp").dialog({
            modal: true,
            title: "<b>Voters Details</b>",
			width: 970,
            height: 600
           
        });
}

function getUserCategories(){

	var jsObj=
			{
			 task:"getUserCategories"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getUserCategoriesAction.action?"+rparam;						
		callAjax(jsObj,url);

}

function selectAll(id){
	 $('.'+id).each(function() {
            $(this).attr('checked','checked');
        });
}

function deSelectAll(id){
    $('.'+id).each(function() {
     $(this).removeAttr('checked');
	});
}

function getAllVoterFamiliesForEdit(){
  var impFamiliesEditInfo = new Array();
	 $('.familyMemberCheck').each(function() {
           if($(this).is(':checked')){
		      var voterId = $(this).val();
			  var boothId = $(this).closest("tr").find(".selectedBoothId").val();
		       var obj={
				 boothId:boothId,
				 voterId:voterId
			   }
			   impFamiliesEditInfo.push(obj);
		   }
        });
    if(impFamiliesEditInfo.length > 0){
	     $("#multipleVoterFamiliesEditDiv").html("");
		 if(votersLimitExist){
		    if(impFamiliesEditInfo.length > 30)
			{
			  alert("Please select atomst 30 voters to edit");
			   return;
			}
		  }
		 totalCategories = 0;
		  var jsObj=
		  {
			selectedVoters:impFamiliesEditInfo,
			task:"allFamiliesEditInfo"
		  };
		  $("#getAllVoterFamiliesInfoForEditFormValues").val(YAHOO.lang.JSON.stringify(jsObj));
			  
			  var uploadHandler = {
				success: function(o) {
					var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
					showAllVoterSelectedUpdatedStatus(uploadResult);
				}
			};

		
		  YAHOO.util.Connect.setForm('getAllVoterFamiliesInfoForEditForm',false);
		  YAHOO.util.Connect.asyncRequest('POST','getMultipleFamilesInfoForEditAction.action?save=',uploadHandler);
		/*var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getMultipleFamilesInfoForEditAction.action?"+rparam+"&save=";	
		callAjax(jsObj,url);*/
	}
}


function checkForAttributesToDisplay(){


	 $('.attributeTypeClassIni1').each(function() {
           if($(this).is(':checked')){
		        var ids = ($(this).val()).split(",");
		       reqfieldsArr.push($(this).val());
		       reqfields = reqfields+","+ids[0];
		    }
          });
		   if(reqfields.length > 0)
		  reqfields = reqfields.slice(1);

		   buildVotersInFamilyWithRetrievedResults();

}


getvotersBasicInfo();

getImpFamiliesVotersToShow();
</script>
</body>


</html>