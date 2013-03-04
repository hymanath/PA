<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>

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
	<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/showGallaries.js"></script>
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
   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <style type="text/css">
#MainDiv,#voterDataOuterDiv{
margin-left:auto;
margin-right:auto;
width:980px;
}
#MainDiv{margin-bottom: 200px; margin-top: 60px;}
fieldset{
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border: 3px solid #CFD6DF;
   
    margin-bottom: 10px;
    padding-bottom: 76px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
	 padding-top: 30px;
	width: 844px;
   margin-left: auto; 
   margin-right: auto;
   float: none;"
}
.selectDiv{
	 width:80%;
	 padding-top:10px;
	 padding-bottom:10px;
	 font-family: verdana;
	 font-size: 12px;
	 margin-left:auto;
	 margin-right:auto;
	 font-weight:bold;
	 color:#333333;

 }
 .requiredFont{
 color:red;
 font-size:12px;
 }
#voterDataInsertDiv{text-align: center; margin-top: 50px;}
#errorMsgDiv{font-size: 14px;
        margin-left: 52px;color:red;}
		#ajaxImage{display: block;
    margin-left: 480px;
    margin-top: -22px;}
	.headingDiv {
     background: none repeat scroll 0 0 #06ABEA;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    float: none;
    font-family: arial;
    font-size: 17px;
    font-weight: bold;
    margin-bottom: 15px;
    margin-left: auto;
    margin-right: auto;
    padding: 3px;
    text-align: center;
    width: 360px;
}
</style>
</head>
<body>
<div id="MainDiv">
<div id="voterDataOuterDiv">
<div class="headingDiv">Populate Voters Data To Intermediate Tables</div>
 <fieldset>
    <div id="errorMsgDiv"></div>
	<div id="ConstituencyDiv" class="selectDiv">
		Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name"/> &nbsp;&nbsp;

		Select Publication Date<font class="requiredFont">*</font> <select id="publicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		</select>
		<span style="float: right; clear: both; margin-right: -19px; margin-top: 8px;display:none;" id="ajaxLoad"><img src="images/icons/search.gif" /></span>

		<div id="voterDataInsertDiv">
			<input type="button" class="btn btn-info" value="Submit" id="voterDataInsertBtn" />
			<input type="button" class="btn btn-info" value="Delete Existing Data" id="voterDataDeleteBtn" />
			<img src="./images/icons/search.gif" style="display:none;" id="ajaxImage" />
		</div>
	</div>
 </fieldset>
</div>	


<!-- voters Caste Div -->
<div id="voterDataOuterDiv">
<div class="headingDiv" style="width:413px;">Populate Caste Voters Data To Intermediate Tables</div>
 <fieldset>
    <div id="casterrorMsgDiv"></div>
	<div id="ConstituencyDiv" class="selectDiv">
		Select Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="castconstituencyList" list="constituencyList" listKey="id" listValue="name"/> &nbsp;&nbsp;

		Select Publication Date<font class="requiredFont">*</font> <select id="castpublicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		</select>
		<span style="float: right; clear: both; margin-right: -19px; margin-top: 8px;display:none;" id="castajaxLoad"><img src="images/icons/search.gif" /></span>

		<div id="voterDataInsertDiv">
			<input type="button" class="btn btn-info" value="Submit" id="castvoterDataInsertBtn" />
			<input type="button" class="btn btn-info" value="Delete Existing Data" id="castvoterDataDeleteBtn" />
			<img src="./images/icons/search.gif" style="display:none;" id="castajaxImage" />
		</div>
	</div>
 </fieldset>
</div>	


</div>

<script type="text/javascript">

function populateVoterData()
{
	var constituencyId = $("#constituencyList").val();
	var jsObj=
		{
				
			id:constituencyId,
			task:"task"
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "populateVoterDataAction.action?"+rparam;						
		callAjax(jsObj,url);
}

$(document).ready(function(){

  $("#constituencyList").change(function(){
	 var id = $("#constituencyList").val();
	  if(id == 0)
	  {
	   $("#errorMsgDiv").html('Please Select Constituency.');
		return;
	  }
	
	 $("#ajaxLoad").css('display','block');
	 var jsObj=
	 {
		selected:id,
		task:"getPublicationDate"
	 };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "voterAnalysisAjaxAction.action?"+rparam;	
	 callAjax(jsObj,url);

	});

	$("#castconstituencyList").change(function(){
	 var id = $("#castconstituencyList").val();
	  if(id == 0)
	  {
	   $("#casterrorMsgDiv").html('Please Select Constituency.');
		return;
	  }
	
	 $("#castajaxLoad").css('display','block');
	 var jsObj=
	 {
		selected:id,
		task:"getPublicationDateForCast"
	 };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "voterAnalysisAjaxAction.action?"+rparam;	
	 callAjax(jsObj,url);

	});
	$("#voterDataInsertBtn").click(function(){

		var constituencyId = $("#constituencyList").val(); 
		var publicationDateId = $("#publicationDateList").val();
		if(constituencyId == 0)
		{
			$("#errorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(publicationDateId == 0 || publicationDateId == null)
		{
		  $("#errorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#voterDataInsertBtn").attr("disabled", "disabled");
		$("#ajaxImage").css("display","block");
		
		var jsObj=
		{
		  id				  :constituencyId,
		  publicationDateId : publicationDateId,
		  task:"insertVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "insertVotersDataToIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);

	});

$("#castvoterDataInsertBtn").click(function(){
	
var castconstituencyId = $("#castconstituencyList").val(); 
		var castpublicationDateId = $("#castpublicationDateList").val();
		if(castconstituencyId == 0)
		{
			$("#casterrorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(castpublicationDateId == 0 || castpublicationDateId == null)
		{
		  $("#casterrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#castvoterDataInsertBtn").attr("disabled", "disabled");
		$("#castajaxImage").css("display","block");
		
		var jsObj=
		{
		  id				  :castconstituencyId,
		  publicationDateId : castpublicationDateId,
		  task:"insertCastVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "insertVotersCasteDataToIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);

	});
	$("#castvoterDataDeleteBtn").click(function(){
		var castconstituencyId = $("#castconstituencyList").val(); 
		var castpublicationDateId = $("#castpublicationDateList").val();
		if(castconstituencyId == 0)
		{
			$("#errorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(castpublicationDateId == 0 || castpublicationDateId == null)
		{
		  $("#casterrorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#castvoterDataDeleteBtn").attr("disabled", "disabled");
		$("#castajaxImage").css("display","block");
		
		var jsObj=
		{
		  id				  :castconstituencyId,
		  publicationDateId : castpublicationDateId,
		  task:"deletecastVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "deleteVoterscastDataFromIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);
	});

});

$("#voterDataDeleteBtn").click(function(){
	
        var constituencyId = $("#constituencyList").val(); 
		var publicationDateId = $("#publicationDateList").val();
		if(constituencyId == 0)
		{
			$("#errorMsgDiv").html('Please Select Constituency.');
			return;
		}
		if(publicationDateId == 0 || publicationDateId == null)
		{
		  $("#errorMsgDiv").html('Please Select Publication Date.');
			return;
		}
		
		$("#voterDataDeleteBtn").attr("disabled", "disabled");
		$("#ajaxImage").css("display","block");
		
		var jsObj=
		{
		  id				  :constituencyId,
		  publicationDateId : publicationDateId,
		  task:"deleteVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "deleteVotersDataFromIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);


});

function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task == "getPublicationDate")
								{
									buildPublicationDateList(myResults);
									
								}
								else if(jsObj.task == "getPublicationDateForCast")
								{
									
									buildPublicationDateList1(myResults);
								}
								
								else if(jsObj.task == "insertVotersData")
								{
									showInsertVoterDataStatus(myResults);
								}
								else if(jsObj.task == "deleteVotersData")
									showDeleteVoterDataStatus(myResults);

								}catch (e) {
							     //$("#votersEditSaveAjaxImg").hide();
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

	function showInsertVoterDataStatus(result)
	{
		$("#ajaxImage").css("display","none");
		$("#voterDataInsertBtn").removeAttr("disabled");
		//document.getElementById('constituencyList').selectedIndex = 0;
		//document.getElementById('publicationDateList').selectedIndex = 0;
		if(result.resultCode == 0)
		{
			$("#errorMsgDiv").html("Voters Data Inserted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#errorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}
	}


function showDeleteVoterDataStatus(result)
	{
		$("#ajaxImage").css("display","none");
		$("#voterDataDeleteBtn").removeAttr("disabled");
		
		if(result.resultCode == 0)
		{
			$("#errorMsgDiv").html("Voters Data Deleted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#errorMsgDiv").html("Error Occured try Again.").css("color","red");
				return;
		}
	}
	function buildPublicationDateList1(results)
	{
	document.getElementById('castajaxLoad').style.display = 'none';
	var selectedElmt=document.getElementById("castpublicationDateList");
	//var selectElmt =jsObj.selectElmt;
	removeSelectElements(selectedElmt);
	for(var val in results)
	{
		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}
}
</script>
</body>
</html>