<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message Center Voter Search Details</title>

<!-- Javascript  start-->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
<!-- Javascript end -->
<!-- Css styles start -->
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
<link type="text/css" rel="stylesheet" href="styles/cadreSearch/cadreSearch.css"></link>

<!-- Css styles end -->

<style>
#votersByLocationTabContentDiv_body table{
text-align: left;
width:100%;
}
 #mainDiv{margin: 20px auto; float: none; width: 980px;}
 #voterDetailsDiv table {
    border: 1px solid #D3D3D3;
    border-collapse: collapse;
    margin-left: auto;
    margin-right: auto;
    padding: 10px;
    width: 100%;
}
#voterDetailsDiv table th {
    background-color: #CDE6FC;
    color: #333333;
    font-size: 13px;
    font-weight: bold;
    padding: 10px;
    text-align: left;
}
#voterDetailsDiv table td {
    color: #676A67;
    font: small-caption;
    padding: 8px 8px 8px 10px;
}
#paginationDivId{float: none;
    margin-left: auto;
    margin-right: auto;
    margin-top: 15px;
    width: 800px;}
#btnDiv{text-align: center; margin-top: 4px;}

#votersByLocationTabContentDiv_body table th{
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
	 #votersByLocationTabContentDiv_body table td{padding:2px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
	#votersByLocationTabContentDiv_body table tr:nth-child(even){background:#EdF5FF;}
	#votersByLocationTabContentDiv_body table{ border: 1px solid #7F7F7F;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}

.btnClass
{
	background-image:url(images/icons/indexPage/swasthic_body.png);
	background-repeat:repeat-x;
	border:1px solid #ADADAD;
	color:#244565;
	font-weight:bold;
	padding:5px;
	text-decoration:none;
}
</style>

<script type="text/javascript">
	 var constituencyId = '${constituencyId}';
	 var publicationId = '${publicationDateId}';
	 var locationId = '${locationValue}';
	 var locationType = '${locationType}';

	var isAgeSelected = '${isAgeSelected}';
	var isCasteSelected = '${isCasteSelected}';
	var isFamilySelected = '${isFamilySelected}';
	var isNameSelected = '${isNameSelected}';
	var isGenderSelected = '${isGenderSelected}';


	var startAge = '${startAge}';
	var endAge = '${endAge}';
	var casteIds = '${casteIds}';
	var houseNo = '${houseNo}';
	var name = '${name}';
	var gender = '${gender}';
</script>
</head>
<body>

<h2 style="text-align:center;color:#06ABEA;"><u>VOTER SEARCH RESULTS</u></h2>


<div style="margin-left:173px;width:660px;padding:10px;background-color:#f3f3f3;font-weight:bold;font-family:Arial;font-size:11px;">
<h4>Instructions to select voter for sending voice SMS</h4>

 <ul>
  <li>Select the voter to whom you would like to send SMS and click <a href="javascript:{closeWindow();}">here</a> once you are done with the selection, to close this window.</li>
 
</li>
 </ul>
</div>

<!--<input type="button" class="btnClass" onClick="window.close();" value="Click here to close the window" style="float:right;margin-right:40px;"/>-->
<span id="peopleCount" style="font-size:13px;font-weight:bold;color:#0082A3;margin-left: 50px;"></span>
<div style="margin-left:30px;margin-top:13px;padding:5px;">
	<div id="votersByLocationTabContentDiv_body" class="yui-skin-sam yui-dt-sortable"></div>
</div>


<script>

 function callAjax(jsObj,url)
 {
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task=="getVoterDetails")
								{
								  buildVoterDetails(myResults,jsObj);
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

$(document).ready(function(){

	 $('.voter').live("change",function(){

			if($(this).is(':checked'))
                   window.opener.document.getElementById("voterCount").innerHTML = parseInt(window.opener.document.getElementById("voterCount").innerHTML) +1;
			else{
				window.opener.document.getElementById("voterCount").innerHTML = parseInt(window.opener.document.getElementById("voterCount").innerHTML) -1;
				$('.allVoters').attr('checked',false);
			}
		});
  
  $(".allVoters").live("change",function(){
	
	   if(this.checked)
	   {
		 $(".voter").attr('checked', true);		 
		$('.voter').each(function(index,value){
			var voterId = $(this).val().split("-")[0];
			var mobileNumber = $(this).val().split("-")[1];

		   if(window.opener.selectedVotersDetails[voterId] == undefined){
					window.opener.selectedVotersDetails[voterId] = mobileNumber;
			window.opener.document.getElementById("voterCount").innerHTML = parseInt(window.opener.document.getElementById("voterCount").innerHTML) +1;
		   }


		   if($.inArray("91"+mobileNumber, window.opener.selectedMobileNumbers) == -1)
				   window.opener.selectedMobileNumbers.push("91"+mobileNumber);
		});
	   }
  });

});

function unSelectAllCheckBoxes(){
	$(".voter").attr('checked', false);
	$(".allVoters").attr('checked', false);
}

getVotersDetailsBySearchCritteria();

function getVotersDetailsBySearchCritteria()
{

   YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	{

			var voterName= oRecord.getData("name");
			var mobileNumber = oRecord.getData("mobileNumber");
			var voterIdCardNo = oRecord.getData("voterIdCardNo");

			 if(window.opener.selectedVotersDetails[voterIdCardNo] == undefined)
				var str ='<input type="checkbox" onchange="pushIntoVoterObject(\''+voterIdCardNo+'\',\''+mobileNumber+'\')" class="voter" value="'+voterIdCardNo+'-'+mobileNumber+'"/>';
			else
				var str ='<input type="checkbox" checked onchange="pushIntoVoterObject(\''+voterIdCardNo+'\',\''+mobileNumber+'\')" class="voter" value="'+voterIdCardNo+'-'+mobileNumber+'"/>';
		
		elLiner.innerHTML =str;
	}


var votersByLocBoothColumnDefs = [
{key:"select", label: "<input type='checkbox' class='allVoters'/>", width:70,formatter:YAHOO.widget.DataTable.Type},
{key:"name", label: "Voter Name",sortable: true},
{key:"mobileNumber",label:"Mobile Number",sortable:false},
{key:"houseNo",label:"House Number",sortable:false},
{key:"startAge",label:"Age",sortable:false},
{key:"voterIdCardNo",label:"Voter Id",sortable:false}
];


var urlStr = "getVotersDetailsBySearchToSendSMS.action?publicationDateId="+publicationId+"&constituencyId="+constituencyId+"&locationType="+locationType+"&locationValue="+locationId+"&";


urlStr += "isAgeSelected="+isAgeSelected+"&isCasteSelected="+isCasteSelected+"&isFamilySelected="+isFamilySelected+"&isNameSelected="+isNameSelected+"&isGenderSelected="+isGenderSelected+"&";

   if(isAgeSelected == "true")
	   urlStr += "startAge="+startAge+"&endAge="+endAge+"&";
   if(isCasteSelected == "true")
	   urlStr += "casteIds="+casteIds+"&";

   if(isFamilySelected == "true")
	   urlStr += "houseNo="+houseNo+"&";
   if(isNameSelected == "true")
	   urlStr += "name="+searchName+"&";
   if(isGenderSelected == "true")
	   urlStr += "gender="+gender+"&";

   var votersByLocBoothDataSource = new YAHOO.util.DataSource(urlStr);


votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "resultVotersList",
fields: [
{key:"name"},
"mobileNumber","houseNo","startAge","voterIdCardNo"],

metaFields: {
totalRecords: "totalCount" // Access to value in the server response
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

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);
//debugger;

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
	oPayload.totalRecords = oResponse.meta.totalRecords;
	$('#peopleCount').html("Total Count : <span>"+oResponse.meta.totalRecords +"</span>");
return oPayload;
}


return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};
}

function pushIntoVoterObject(voterName , mobileNumber)
{
	if(window.opener.selectedVotersDetails[voterName] == undefined){
      window.opener.selectedVotersDetails[voterName] = mobileNumber;
	   window.opener.selectedMobileNumbers.push("91"+mobileNumber);	   
	}
	else{

		delete window.opener.selectedVotersDetails[voterName];

		var index =  window.opener.selectedMobileNumbers.indexOf("91"+mobileNumber);
         window.opener.selectedMobileNumbers.splice(index, 1);
	}
}
function closeWindow()
{
	window.close();
}
</script>
</body>
</html>