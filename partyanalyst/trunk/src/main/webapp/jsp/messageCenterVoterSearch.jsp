<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message Center</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>

   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>
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
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
 
 <link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.9.0/build/tabview/assets/skins/sam/tabview.css"> 
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
<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/>


<link type="text/css" rel="stylesheet" href="styles/cadreSearch/cadreSearch.css"></link>



<title>Party Analyst</title>

<style>
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
</head>
<body>
<!--<div id="mainDiv">

<div id="voterDetailsDiv"></div>
<div id="paginationDivId"></div>
<div id="btnDiv" style="display:none;"><input type="button" class="btn btn-info" value="Add  Contacts To Send Voice SMS" id="phoneNoBtn" onclick="getPhoneNos()" /></div>

</div>
-->
<h4 style="text-align:center;color:#06ABEA;">VOTER SEARCH RESULTS</h4>

<div style="margin-left:150px;">
	<div id="votersByLocationTabContentDiv_body" class="yui-skin-sam yui-dt-sortable"></div>
</div>



<script>
var limit = 1000;
var totalReq = 1000;
function getWardsForMuncipality(startIndex)
{
 
   var jsObj=
			{
			
				constituencyId:constituencyId,
				id:locationId,
				publicationDateId:publicationId,
				locationType:locationType,
				startIndex:startIndex,
				results:10,
				task:"getVoterDetails"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVoterDetailsAction.action?"+rparam;						
		callAjax(jsObj,url);
 
}

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



function buildVoterDetails(results,jsObj)
{
  $('#voterDetailsDiv').html('');
  
  if(results == null)
  {
   $('#voterDetailsDiv').html('No Data Found.');
   $('#paginationDivId').html('');
   return;
  }
  $("#btnDiv").css('display','block');
  var result = results.votersList;
  var str = '';
  str +='<table class="dataTable">';
  str +='<tr>';
  str +='<th>All <input type="checkbox" id="selectAll" name="selectAll" /></th>';
  str +='<th>Name</th>';
  str +='<th>House No</th>';
  str +='<th>Mobile No</th>';
  str +='<th>Booth Name</th>';
  str +='</tr>';
  for(var i in result)
  {
    str +='<tr>';
    str +='<td><input type="checkbox" class="checkboxCls" name="check" value="'+result[i].mobileNo+'" /></td>';
    str +='<td>'+result[i].name+'</td>';
    str +='<td>'+result[i].houseNo+'</td>';
    str +='<td>'+result[i].mobileNo+'</td>';
    str +='<td>'+result[i].partNo+'</td>';
    str +='</tr>'; 
  }
  $('#voterDetailsDiv').html(str);

  var itemsCount=results.totalHousesCount;
	    var maxResults=jsObj.results;
	   
	     if(jsObj.startIndex==0){
		   $("#paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*10;
				getWardsForMuncipality(num);
				
			}
		});
	}

}
//getWardsForMuncipality(0);


function getPhoneNos()
{
  var tempArray =[];
  $('input:checkbox[name="check"]').each(function() {
        if (this.checked) {
			
          tempArray.push(this.value);
      }
  });

 
  if(tempArray == null || tempArray.length == 0)
	 return;

  window.opener.receiveFromVoterSearchChild ( tempArray );

}

$(document).ready(function(){
  
  $("#selectAll").live("click",function(){
	
   if(this.checked)
	 $(".checkboxCls").attr('checked', true);
   else
    $(".checkboxCls").attr('checked', false);
	  
  });

});

getVotersDetailsBySearchCritteria();

function getVotersDetailsBySearchCritteria()
{

/*
var urlstr = "getVoterDetailsInitialRequest.action?constituencyId="+constituencyId+"&name="+mainname+"&publicationId="+publicationId+"&panchaytId="+panchaytId+"&sort=voterId&dir=asc&startIndex=0&results=100&maintype=panchayat";

var browser1 = window.open(urlstr,"familyWiseDetails","scrollbars=yes,height=600,width=1050,left=200,top=200");	
browser1.focus();

return;


if(panchaytId == "0" || publicationId == "0")
	return false;
YAHOO.widget.DataTable.ActionLink = function(elLiner, oRecord, oColumn, oData)
{
var str ='';
var id=oRecord.getData("voterIds");
var name = oRecord.getData("firstName");
var influencePerson=oRecord.getData("influencePerson");

str += '<ul class="dd_menu">';
str += ' <li><i class="icon-th-list"></i>';
str += ' <ul>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'cadre\',\''+name+'\');">Create New Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};"  onclick="openCadreSearchWindow('+id+');">Add To Existing Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'influencingPeople\',\''+name+'\');">Create New Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{getInfluencePeopleOfAnUser('+id+')};">Add To Existing Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'candidate\',\''+name+'\');">Add To Politician</a>';
str += ' </li>';
str += ' </ul>';
str += ' </li>';
str += ' </ul>';
elLiner.innerHTML =str;
}*/
YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	{

			var voterName= oRecord.getData("name");
			var mobileNumber = oRecord.getData("mobileNumber");
			var voterIdCardNo = oRecord.getData("voterIdCardNo");

	 if(window.opener.selectedVotersDetails[voterName] == undefined)
		var str ='<input type="checkbox" onchange="pushIntoVoterObject(\''+voterIdCardNo+'\',\''+mobileNumber+'\')"/>';
	else
		var str ='<input type="checkbox" checked onchange="pushIntoVoterObject(\''+voterIdCardNo+'\',\''+mobileNumber+'\')"/>';


		
		elLiner.innerHTML =str;
	}
/*YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var voterId= oRecord.getData("voterIds");


		str+='<a href="javaScript:{getInfluencePeopleOfAnUser('+voterId+');}">Click here</a>';
		//var boothId=oRecord.getData("boothId"); 
		//elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+id+"'/><input type='hidden' class='selectedBoothId' value='"+boothId+"'/>";
		//elLiner.innerHTML=id;
		elLiner.innerHTML=str;
					
	};*/

var votersByLocBoothColumnDefs = [
{key:"select", label: "select", width:70,formatter:YAHOO.widget.DataTable.Type},
{key:"name", label: "Voter Name",sortable: true},
{key:"mobileNumber",label:"Mobile Number",sortable:false},
{key:"houseNo",label:"House Number",sortable:false},
{key:"startAge",label:"Age",sortable:false},
{key:"voterIdCardNo",label:"Voter Id",sortable:false}
];

/*
var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVotersDetailsBySearchToSendSMS.action?publicationDateId=8&isAgeSelected=true&isCasteSelected=false&isFamilySelected=false&isNameSelected=false&isGenderSelected=false&startAge=18&endAge=22&casteIds=&locationType=booth&locationValue=122992&");*/

var urlStr = "getVotersDetailsBySearchToSendSMS.action?publicationDateId="+publicationId+"&constituencyId="+constituencyId+"&locationType="+locationType+"&locationValue="+locationId+"&";


urlStr += "isAgeSelected="+isAgeSelected+"&isCasteSelected="+isCasteSelected+"&isFamilySelected="+isFamilySelected+"&isNameSelected="+isNameSelected+"&isGenderSelected="+isGenderSelected+"&";

   if(isAgeSelected == "true")
	   urlStr += "startAge="+startAge+"&endAge="+endAge+"&";
   if(isCasteSelected == "true")
	   urlStr += "casteIds="+houseNo+"&";

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

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;
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
	   //window.opener.selectedMobileNumbers.push(mobileNumber);
	    var obj = {
					mobileNumber:mobileNumber
				  };
		window.opener.selectedMobileNumbers.push(obj);
	}
	else{

		delete window.opener.selectedVotersDetails[voterName];

		var index =  window.opener.selectedMobileNumbers.indexOf(mobileNumber);
         array.splice(index, 1);
	}
}
</script>
</body>
</html>