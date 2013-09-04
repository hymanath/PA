<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst - Message Center For Influence People Search</title>

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


<title>Party Analyst</title>

<style>

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
#votersByLocationTabContentDiv_body table{ border: 1px solid #7F7F7F;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;margin-top: 20px;}
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
#votersByLocationTabContentDiv_body{
margin-left: auto; 
margin-right: auto; 
width: 55%;
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
<script>
$(document).ready(function(){

	 $('.checkbox').live("change",function(){

			if($(this).is(':checked'))
                   window.opener.document.getElementById("influencePeopleCount").innerHTML = parseInt(window.opener.document.getElementById("influencePeopleCount").innerHTML) +1;
			else{
				window.opener.document.getElementById("influencePeopleCount").innerHTML = parseInt(window.opener.document.getElementById("influencePeopleCount").innerHTML) -1;
				$('.selectAll').attr('checked',false);
			}
		});

  
  $(".selectAll").live("click",function(){
	
      if(this.checked)
	  {
	        $(".checkbox").attr('checked', true);

		$('.checkbox').each(function(index,value){
			var name = $(this).val().split("-")[0];
			var mobileNumber = $(this).val().split("-")[1];

		   if(window.opener.selectedInfluencePeopleDetails[name] == undefined){
					window.opener.selectedInfluencePeopleDetails[name] = mobileNumber;

				window.opener.document.getElementById("influencePeopleCount").innerHTML = parseInt(window.opener.document.getElementById("influencePeopleCount").innerHTML) +1;
		   }


		   if($.inArray(mobileNumber, window.opener.selectedMobileNumbers) == -1)
				   window.opener.selectedMobileNumbers.push(mobileNumber);
		});
	  }
  });

});
function addNewCandidate(){
var urlStr = "influencingPeopleAction.action?windowTask=new&influencingPersonId=0";
window.open(urlStr,"addNewInfluencePeople","scrollbars=yes,height=570,width=1300,left=300,top=50").focus();	
}
</script>
</head>
<body>
  <h2 style="text-align:center;color:#06ABEA;">INFLUENCING PEOPLE SEARCH RESULTS TO SEND SMS</h2>

<div style="float:right;margin:0px 35px 12px 5px;">
<input type="button" class="btnClass" onClick="addNewCandidate()" value="Click Here To Add Influencing People"/>
<input type="button" class="btnClass" onClick="window.close();" value="Click here to close the window"/>
</div>

<div class="span11" align="center" style="margin-top:66px;">
<span id="peopleCount" style="font-size:13px;font-weight:bold;color:#0082A3;margin-left: -445px;"></span>
 <div class="row">

	<div id="votersByLocationTabContentDiv_body" class="yui-skin-sam yui-dt-sortable" style="margin-top:30px;"></div>
	</div>
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

getInfluencePeopleDetailsBySearchCritteria();

function getInfluencePeopleDetailsBySearchCritteria()
{

YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	{

			var name= oRecord.getData("name");
			var mobileNumber = oRecord.getData("mobileNumber");

			if(window.opener.selectedInfluencePeopleDetails[name] == undefined)
		var str ='<input type="checkbox" onchange="pushIntoInfluencePeopleObject(\''+name+'\',\''+mobileNumber+'\')" class="checkbox" value="'+name+'-'+mobileNumber+'" style="margin-left: -1px;"/>';
	else
		var str ='<input type="checkbox" checked onchange="pushIntoInfluencePeopleObject(\''+name+'\',\''+mobileNumber+'\')" class="checkbox" value="'+name+'-'+mobileNumber+'" style="margin-left: 29px;"/>';

		elLiner.innerHTML =str;
	}
	
	YAHOO.widget.DataTable.Caste = function(elLiner, oRecord, oColumn, oData)
	{

			var casteName= oRecord.getData("casteIds");
			//if(casteName.length <= 1)
			//	console.log(casteName);
	if(casteName==null)
		var str ='Not Available';
	else
		var str =casteName;

		elLiner.innerHTML =str;
	}


var votersByLocBoothColumnDefs = [
{key:"select", label: "<input type='checkbox' class='selectAll' style='margin-left:34px;'/>", width:70,formatter:YAHOO.widget.DataTable.Type},
{key:"name", label: "Name",sortable: true},
{key:"mobileNumber",label:"Mobile Number",width:110,sortable:false},
{key:"locationType",label:"Area Type",width:70,sortable:false},
{key:"casteIds",label:"Caste",formatter:YAHOO.widget.DataTable.Caste}
];


var urlStr = "searchCandidatesForVoiceSmsAction.action?publicationDateId=8&locationType="+locationType+"&locationValue="+locationId+"&";


urlStr += "isAgeSelected="+isAgeSelected+"&isCasteSelected="+isCasteSelected+"&isFamilySelected="+isFamilySelected+"&isNameSelected="+isNameSelected+"&isGenderSelected="+isGenderSelected+"&";

   if(isAgeSelected == "true")
	   urlStr += "startAge="+startAge+"&endAge="+endAge+"&";
   if(isCasteSelected == "true")
	   urlStr += "casteIds=&";

   if(isFamilySelected == "true")
	   urlStr += "houseNo="+houseNo+"&";
   if(isNameSelected == "true")
	   urlStr += "name="+name+"&";
   if(isGenderSelected == "true")
	   urlStr += "gender="+gender+"&";

   var votersByLocBoothDataSource = new YAHOO.util.DataSource(urlStr);


votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "resultVotersList",
fields: [
{key:"name"},
"mobileNumber","locationType","casteIds"],

metaFields: {
totalRecords: "totalResultsCount" // Access to value in the server response
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

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;

	if(oResponse.meta.totalRecords  == 0)
	{
		$('#peopleCount').html("<font style='color:red;font-weight:bold;'>No records found matching your search criteria. Changing the search criteria might help you.</font>");
	}
	else
	$('#peopleCount').html("Total Count : <span>"+oResponse.meta.totalRecords +"</span>");
return oPayload;
}


return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};
}

function pushIntoInfluencePeopleObject(voterName , mobileNumber)
{
	if(window.opener.selectedInfluencePeopleDetails[voterName] == undefined){
       window.opener.selectedInfluencePeopleDetails[voterName] = mobileNumber;
	   window.opener.selectedMobileNumbers.push("91"+mobileNumber);
	}
	else{

		delete window.opener.selectedInfluencePeopleDetails[voterName];

		var index =  window.opener.selectedMobileNumbers.indexOf("91"+mobileNumber);
         window.opener.selectedMobileNumbers.splice(index, 1);
	}
}
</script>
</body>
</html>