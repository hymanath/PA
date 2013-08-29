<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message Center</title>

<!-- javascript start-->
    <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
    <script src="http://code.jquery.com/ui/1.8.24/jquery-ui.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
<!-- javascript end-->
<!-- css styles start-->
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link type="text/css" rel="stylesheet" href="styles/cadreSearch/cadreSearch.css"></link>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.24/themes/base/jquery-ui.css">
<!-- css styles end-->

</head>
<body>



<div id="responseDetailsDiv">
 <div id="responseDetailsInnerDiv"></div>
</div>


<div id="voiceSmsHistoryDiv" class="yui-skin-sam yui-dt-sortable"></div>

<script>
getVoiceSmsHistory();
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
		{key:"details",label:"Details",width:70,formatter:YAHOO.widget.DataTable.details}
		];

		var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoiceSmsHistoryForAuser.action?");

		votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
		votersByLocBoothDataSource.responseSchema = {
		resultsList: "responseDetailsList",
		fields: [
		{key:"responseCode"},
		"dateSent","description","details"],

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