<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message Center</title>

<!-- Javascript  start-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<!-- Javascript end -->
<!-- Css styles start -->
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
<link type="text/css" rel="stylesheet" href="styles/cadreSearch/cadreSearch.css"></link>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <link rel="stylesheet" href="/resources/demos/style.css" />
<!-- Css styles end -->

<style>

#voiceSmsHistoryDiv table{
 border: 1px solid #7F7F7F;
 border-collapse:collapse;
 padding:10px;
 margin-left:auto;
 margin-right:auto;
 width:100%;
 margin-top: 20px;
 }
#voiceSmsHistoryDiv table th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 5px;
    padding-left: 5px;
    padding-right: 5px;
    padding-top: 5px;
    text-align: left;
	color:#333333;
	}
#voiceSmsHistoryDiv table td{ 
padding:2px;
padding-left:10px;
font-weight:normal;
font:small-caption;
color: #676A67;
}
#voiceSmsHistoryDiv table tr:nth-child(even){
background:#EdF5FF;
}
#voiceSmsHistoryDiv{
margin-left: auto; 
margin-right: auto; 
width: 55%;
}

</style>
</head>
<body>



 <h2 style="text-align:center;color:#06ABEA;"> VOICE SMS HISTORY </h2>
<div id="responseDetailsDiv">
 <div id="responseDetailsInnerDiv"></div>
</div>

<div class="span11" style="margin-left:30px;">
 <div class="row">

<div id="voiceSmsHistoryDiv" class="yui-skin-sam yui-dt-sortable"></div>
 </div>
</div>

<script>

var dataArr = new Array();
<c:forEach var="voiceSmsResponseDetailsVO" items="${responseDtls.responseDetailsList}">
	var obj = {
					userName : "${voiceSmsResponseDetailsVO.userName}",
					dateSent : "${voiceSmsResponseDetailsVO.dateSent}",
					description : "${voiceSmsResponseDetailsVO.description}",
					responseCode : "${voiceSmsResponseDetailsVO.responseCode}",
					responseId : "${voiceSmsResponseDetailsVO.responseId}",
					details:'<a title="Click Here to See Details" href="javascript:{showMessageResponseDetails(${voiceSmsResponseDetailsVO.responseCode});}"><img src="./images/icons/details.png"/></a>'
				
				};
	dataArr.push(obj);
	</c:forEach>
	getVoiceSmsUserHistory();
	//getVoiceSmsHistory();

	
function getVoiceSmsUserHistory(){

	var votersByLocBoothColumnDefs = [
		{key:"responseCode", label: "Message Id",sortable: true},
		{key:"dateSent",label:"Date Sent",sortable:false},
		{key:"description",label:"Description",sortable:false},
		{key:"userName",label:"User Name",width:110,sortable:false},
		{key:"details",label:"Details",width:70}
		];


	 var myConfigs = {    
				
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 100,
						pageLinks: 10
						})
						
					};	

					var myDataSource = new YAHOO.util.DataSource(dataArr);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "checkBox","userName" , "mobile" ,  "constituency"]
					};

				
	var myDataTable = new YAHOO.widget.DataTable("voiceSmsHistoryDiv",votersByLocBoothColumnDefs, myDataSource,myConfigs);  
}
/*
function getVoiceSmsHistory()
{

	   YAHOO.widget.DataTable.details = function(elLiner, oRecord, oColumn, oData)
		{

				var responseCode = oRecord.getData("responseCode");

                 var str='';
				 str+='<a title="Click Here to See Details" href="javascript:{showMessageResponseDetails('+responseCode+');}"><img src="./images/icons/details.png"/></a>';
			
			elLiner.innerHTML =str;
		}


		var votersByLocBoothColumnDefs = [
		{key:"responseCode", label: "Message Id",sortable: true},
		{key:"dateSent",label:"Date Sent",sortable:false},
		{key:"description",label:"Description",sortable:false},
		{key:"userName",label:"User Name",width:110,sortable:false},
		{key:"details",label:"Details",width:70,formatter:YAHOO.widget.DataTable.details}
		];

	

		votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
		votersByLocBoothDataSource.responseSchema = {
		resultsList: "responseDetailsList",
		fields: [
		{key:"responseCode"},
		"dateSent","description","userName","details"],

		metaFields: {
		totalRecords: "responseCount" // Access to value in the server response
		},
		};

		var myConfigs = {
		initialRequest: "sort=name&dir=asc&startIndex=0&results=100", // Initial request for first page of data
		dynamicData: true, // Enables dynamic server-driven data
		sortedBy : {key:"name", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
		   paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 100 
						})  // Enables pagination
		};

		var votersByLocBoothDataTable = new YAHOO.widget.DataTable("voiceSmsHistoryDiv",
		votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

		votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		//oPayload.totalRecords = oResponse.meta.totalRecords;
		//return oPayload;
		}

		return {
		oDS: votersByLocBoothDataSource,
		oDT: votersByLocBoothDataTable
		};
}
*/
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
						buttons: {								
							"Ok":function(){$(this).dialog("close");} 
					}
	             });

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
	  str+='<th>S.No</th>'
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

function callAjax1(param,jsObj,url){


	var myResults;	
	var callback = {			
	    success : function( o ) {
			try {	
					if(o.responseText.length!=0){
						myResults = YAHOO.lang.JSON.parse(o.responseText);	
					}				
					
					 if(jsObj.task == "getResponseDetails")
						buildResponseDetails(myResults);
	
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
</script>
</body>
</html>