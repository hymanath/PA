<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
<style type="text/css">
table.dataTable {
    border: 1px solid;
    border-radius: 4px 4px 4px 4px;
    clear: both;
    margin: 0 auto;
    width: 100%;
}
.dataTables_filter {
    font-family: arial;
    font-size: 16px;
    text-align: right;
	margin-bottom:10px;
}

.dataTables_length {
    float: left;
    font-family: arial;
    font-size: 16px;
}

table.dataTable thead th {
    cursor: pointer;
    font-family: arial;
    font-size: 16px;
    padding: 3px 18px 3px 10px;
	background: none repeat scroll 0 0 #D9EDF7;
}
table.dataTable td {
    font-family: arial;
    font-size: 15px;
    padding: 3px 10px;
}

.dataTables_paginate {
    float: right;
    font-family: arial;
    font-size: 16px;
    text-align: right;
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

#voterimpFamDtlsOuterPopUp {
    font-family: arial;
    font-size: 16px;
}

.yui-skin-sam thead .yui-dt-sortable {
    background-color: #D9EDF7;
    color: black;
    font-size: 16px;
    text-decoration: none;
	font-family: arial;
}
.yui-skin-sam thead .yui-dt-sortable {
    cursor: pointer;
}
.yui-skin-sam thead .yui-dt-sortable {
    cursor: pointer;
}
a:focus {
    outline: thin dotted #333333;
    outline-offset: -2px;
}
a, .reg-form li a:hover, a.change:hover {
    outline: medium none;
    text-decoration: none;
}
#voterimpFamDtlsOuterPopUp
{
	font-family: arial;
    font-size: 16px;
    height: 291px;
    min-height: 0;
    width: auto;
}

#headingDiv {
    background: none repeat scroll 0 0 #49AFCD;
    border-radius: 4px 4px 4px 4px;
    color: white;
    font-family: arial;
    font-size: 16px;
    height: 29px;
	margin-top: 10px;
	font-weight: bolder;
	margin-bottom: 10px;
	padding-top: 10px;
}

.dataTables_info {
    clear: both;
    float: left;
    font-family: arial;
    font-size: 16px;
}
</style>
<title>Booth Wise Voter Modification Report</title>
<script type="text/javascript">
var locationType               =  '${locationType}';
var locationValue              =  '${locationValue}';
var constituencyId             =  '${constituencyId}';
var fromPublicationDateId      =  '${fromPublicationDateId}';
var toPublicationDateId        =  '${toPublicationDateId}';
var forGender                  =  ${forGender};
var status                     =  '${status}';
var voterStatusId              =  '${voterStatusId}';
var ageRangeId                 =  ${ageRangeId};
var gender                     =  '${gender}';
/*
	This Method is used for making a jax call for getting all modifed voter on booth lavel
*/
function getGenderWiseVoterDetails()
{
	var jObj=
	{
			locationType:locationType,
		    locationValue:locationValue,
			constituencyId:constituencyId,
			fromPublicationDateId:fromPublicationDateId,
			toPublicationDateId:toPublicationDateId,
			forGender:"true",
			status:status,
			voterStatusId:voterStatusId,
			ageRangeId:"0",
			gender:gender,	 
			task:"getGenderBasedVoterDetails"
	};
	var rparam ="&task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getSelectedGenderBasedVoterDetails.action?"+rparam;	

	callAjax(jObj,url);
}
/*
	This Method is used for handling all ajax calls
*/
function callAjax(jsObj,url)
{
	var myResults;
	var callback = {			
 		success : function( o ) {
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);	
			if(jsObj.task == "getGenderBasedVoterDetails")
			{
				buildGenderWiseOrAddedVotersTable(myResults,jsObj);
			}
			else if(jsObj.task == "getVotersInAFamilyByConstituencyId")
			{
				buildFamilyInfo(myResults,jsObj.partNo);
			}					
			}
			catch (e) {
			}  
 		    },
 		    scope : this,
 		    failure : function( o ) {
 		    //alert( "Failed to load result" + o.status + " " + o.statusText);
 		    }
 		    };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
}
/*
	This method is used for builg data table for voter modifications on booth level
*/
function buildGenderWiseOrAddedVotersTable(myResults,jsObj){
var title='';
$('#GenderOrAddedVotersTable').html('');
var str='';
var booth='';
str+='<table id="dt" class="table table-bordered table-hover"><thead><tr>';
if(jsObj.locationType!='booth'){
	str+='<th>BoothNo</th>';}
str+='<th>Name</th><th>Age</th>';
if(jsObj.forGender=='true'){
	if(jsObj.gender=='TOTAL'){
		str+='<th>Gender</th>';
	}
}
else
	str+='<th>Gender</th>';
str+='<th>House No</th><th>Village</th></tr></thead><tbody>';
for(var i in myResults){
booth=myResults[0].boothNo; 

str+='<tr id="checkForEmpty">';
if(jsObj.locationType!='booth'){

str+='<td>'+myResults[i].boothNo+'</td>';}

str+='<td>'+myResults[i].firstName+'</td><td>'+myResults[i].age+'</td>';

if(jsObj.forGender=='true'){
	if(jsObj.gender=='TOTAL'){
		str+='<td class="center">'+myResults[i].gender+'</td>';
	}
}
else
str+='<td class="center">'+myResults[i].gender+'</td>';

str+='<td><a style="cursor:pointer;color:#3392C2;" onclick="getVotersInAFamilyByPublication('+myResults[i].boothNo+','+fromPublicationDateId+','+toPublicationDateId+','+constituencyId+',\''+myResults[i].houseNo+'\')">'+myResults[i].houseNo+'</a></td><td>'+myResults[i].villagesCovered+'</td></tr>';
}
str+='</tbody></table>';
$('#GenderOrAddedVotersTable').html(str);

if(jsObj.forGender=='false'){
	var range='';
	switch (jsObj.ageRangeId) {
		case 1: range="18-25";break;
		case 2: range="26-35";break;
		case 3: range="36-45";break;
		case 4: range="46-60";break;
		case 5: range="60-Above";break;
	}
	
	title= range+" Age "+jsObj.status+" Voters Details";
}
else{
	if(jsObj.gender=='TOTAL')
		gend='Total';
		
	else
		gend=(jsObj.gender=='M')?"Male":"Female";
	
	title= gend+" Voters "+jsObj.status+" Details";
	
	if(jsObj.locationType=='booth'){
		title=gend+" Voters "+jsObj.status+" Details in Booth - "+booth;
	}
}
$('#dt').dataTable({
		"aaSorting": [[ 0, "asc" ]],
		"iDisplayLength":50,
		"aLengthMenu": [[50, 100, 150, 200,250,-1], [50, 100, 150, 200,250,"All"]],
		//"bFilter": false,"bInfo": false
		"bSort": true,
		// "aoColumns": [null,null,null,null,null] 
		});
}

function getVotersInAFamilyByPublication(partNo,fromPublication,ToPublication,constituencyId,hNo){

	//$.blockUI({ message: '<h4><img src="./images/icons/search.gif" />  Request Processing...</h4>',baseZ: 5000 }); 
//	$("#popupAjaxImage").show();
    var jsObj=
			{
					
				hno:hNo,
				fromPublication:fromPublication,
				ToPublication:ToPublication,
				partNo:partNo,
				constituencyId:constituencyId,
				task:"getVotersInAFamilyByConstituencyId"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersInAFamilyByConstituencyId.action?"+rparam;						
		callAjax(jsObj,url);

}
function buildFamilyInfo(results,partNo)
{
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
		    	           // {key:"select", label: "Select", formatter:YAHOO.widget.DataTable.select},
							//{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", sortable: true , width:130},
							{key:"gender", label: "Gender", sortable: true, width:50},
		    				{key:"age", label: "Age",sortable:true, width:50},
							{key:"houseNo", label: "House No",sortable:true, width:120},
							{key:"gaurdian", label: "Guardian Name",sortable:true, width:130},
							{key:"relationship", label: "Relationship",sortable:true, width:100},
							{key:"boothName",label:"Booth",sortable:true, width:50},
							{key:"mobileNo",label:"MobileNo",sortable:true, width:100}
						]; 

    var myConfigs = { 
			    
				};
	var myDataSource = new YAHOO.util.DataSource(results);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","gender","age","houseNo","gaurdian","relationship","voterId","boothName"]
					};
		var familesDataSource = new YAHOO.widget.DataTable("voterimpFamDtls", votersResultColumnDefs,myDataSource, myConfigs);
    $("#voterimpFamDtlsOuterPopUp").dialog({
            modal: true,
            title: "<b>Voters Details</b>",
			width: 950,
            height: 350
           
        }); 
}
getGenderWiseVoterDetails();
</script>
</head>
<body>
<div id="headingDiv" align="center"><span id="heading">Booth Wise Voter Modification Report</span></div>
<div id="GenderOrAddedVotersPopup">
<div id="GenderOrAddedVotersTable">
</div></div>
<div id="voterimpFamDtlsOuterPopUp">
<div id="voterimpFamDtls" class="yui-skin-sam typeTable yui-dt" ></div>
</div>
</body>
</html>