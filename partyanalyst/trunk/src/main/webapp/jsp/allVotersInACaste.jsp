<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
 <script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
<script type="text/javaScript" >
google.load("visualization", "1", {packages:["corechart"]});
</script>
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
   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
   
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jquery.dataTables.js"></script>

<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/>
<style type="text/css">
.localCastStatsVotersTitle{
	border-radius: 3px 3px 3px 3px;
    color: #FFFFFF !important;
    font-family: verdana;
    font-size: 15px !important;
    font-weight: bold;
    padding: 5px;
    width: 650px;
    }
.localCastStatsVotersTitle{
	background:#06ABEA;
}

#subLevelTable,#localCastStatsTabContent_subbody1 table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

#subLevelTable tr:nth-child(even),#localCastStatsTabContent_subbody1 table tr:nth-child(even){background:#EdF5FF;}

#subLevelTable td,#localCastStatsTabContent_subbody1 table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}

#subLevelTable th,#localCastStatsTabContent_subbody1 table th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: left;
	color:#333333;
	}

#localCastStatsTabContent_subbody1 table th a{
color:#333333;
}
</style>
<script type="text/javascript">
var mainId= "${mainId}";
var hamletId= "${hamletId}";
var publicationId= "${publicationDateId}";
var casteName = "${caste}";
var type= "${type}";
var name = "${Name}";
var casteStateId = "${casteStateId}";
var casteCategory = "${casteCategory}";
var mainName = '${typename}';
var publicationYear= "${year}";
var buildType= "${buildTypes}";
var constituencyId= "${constituencyId}";

if(mainName.length <=0)
	getVotersInACasteForLocality();
else if(mainId > 0)
	getVotersInACasteForDidffrentLevels();	
else
	getVotersInACaste();
	
function getVotersInACasteForLocality(){
	var jsObj={
			id:mainId,
			publicationDateId:publicationId,
			caste:casteStateId,
			hamletId:hamletId,
			type:name,
            buildType:"",
			task:"getVotersInACaste",
			casteCategory:casteCategory,
			Name:type,
			publicationDate:publicationYear,
			casteName:casteName
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getvotersCastInfoByConstituency.action?"+rparam;				
	callAjax(jsObj,url);
}
function getVotersInACaste(){
var jsObj={
			id:hamletId,
			publicationDateId:publicationId,
			caste:casteStateId,
			casteName:casteName,
			typename:mainName,
			type:type,
			publicationDate:publicationYear,
			Name:name,
            buildType:buildType,
			constituencyId:constituencyId,
			casteCategory:casteCategory,
			task:"getVotersInACaste"
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getvotersCastInfoByConstituency.action?"+rparam;				
	callAjax(jsObj,url);
}

function getVotersInACasteForDidffrentLevels()
{	
	$('#voterCasteAjaxImg').css("display","block");
	$("#localCastStatsVotersTitle").html("");
	$("#localCastStatsTabContent_subbody1").html("");
	var jsObj={			
			id:mainId,
			hamletId:hamletId,
			publicationDateId:publicationId,			
			caste:casteStateId,
			casteName:casteName,
			typename:mainName,
			type:type,
			publicationDate:publicationYear,	
			Name:name,
            buildType:buildType,
			constituencyId:constituencyId,	
			casteCategory:casteCategory,			
			task:"getVotersInACaste"
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getvotersCastInfoByConstituency.action?"+rparam;				
	callAjax(jsObj,url);
}
function callAjax(jsObj,url)
{
	 var myResults;

	 var callback = {			
	               success : function( o ) {
					try {												
							myResults = YAHOO.lang.JSON.parse(o.responseText);
							if(jsObj.task =="getVotersInACaste")
							{
								buildVotersInACaste(myResults,jsObj);					
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
function buildVotersInACaste(results,jsObj)
{
	var result = results;
	$('#voterCasteAjaxImg').css("display","none");
	if(results.votersByHouseNos != null)
		$("#localCastStatsVotersTitle").addClass("localCastStatsVotersTitle").html(" "+jsObj.casteName+"("+jsObj.casteCategory+") Caste voters Details In " +jsObj.Name+" in "+publicationYear+" ");
	else
		$("#localCastStatsVotersTitle").removeClass("localCastStatsVotersTitle");

	YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var boothId=oRecord.getData("boothId");
		var id=oRecord.getData("voterId");
		var name = oRecord.getData("name");
		elLiner.innerHTML ='<a id="openProblemEditFormId" onclick=" openProblemEditForm('+id+','+boothId+');" style="color:#1975FF;cursor: pointer;">'+name+'</a>';
	}
	 var votersResultColumnDefs = [ 		    	             
		    	            
							//{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", width:180,sortable: true,
							formatter:YAHOO.widget.DataTable.NameLink},
							{key:"gender", label: "Gender", width:40, sortable: true},
		    				{key:"age", label: "Age",sortable:true},
							{key:"houseNo", label: "House No",width:80,sortable:true},
							{key:"gaurdian", label: "Guardian Name",sortable:true},
							{key:"relationship", label: "Relationship", width:50,sortable:true},
							{key:"voterIdCardNo",label:"Voter Id",sortable: true},
	                        {key:"mobileNo",label:"MobileNo",sortable:true}
		    	        ]; 
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 20
			    }) 
				};
	var myDataSource = new YAHOO.util.DataSource(results.votersByHouseNos);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","gender","age","houseNo","gaurdian","relationship","voterId","boothId","voterIdCardNo"]
					};
		var familesDataSource = new YAHOO.widget.DataTable("localCastStatsTabContent_subbody1", votersResultColumnDefs,myDataSource, myConfigs);
}

function openProblemEditForm(id,boothId)
{

	var urlStr="votersEditAction.action?voterId="+id+"&boothId="+boothId+" ";
	var updateBrowser1 = window.open(urlStr,"editAnnouncement","scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser1.focus();	
}	
</script>
</head>
<body>
	<div id="voterCasteAjaxImg" style=" display:block;margin-top:100px;margin-left:300px;margin-right:auto;"><img  src="./images/icons/goldAjaxLoad.gif" /></div>
	<div id="mainDiv" align="center">
    <div id ="localCastStatsVotersTitle" ></div>
    <div id='localCastStatsTabContent_subbody1'  class="yui-skin-sam yui-dt-sortable"></div>
	</div>

</body>
</html>