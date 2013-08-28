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

<div class="span11" style="margin-left:200px;">
 <div class="row">
  <h4 style="text-align:center;color:#06ABEA;">INFLUENCE SEARCH RESULTS</h4>

	<div id="votersByLocationTabContentDiv_body" class="yui-skin-sam yui-dt-sortable"></div>
	</div>
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
    str +='<td><input type="checkbox" class="checkboxCls" name="check" value="'+result[i].name+'" id="'+result[i].mobileNo+'" placeholder="'+result[i].partNo+'"/></td>';
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
  var voter;
  $('input:checkbox[name="check"]').each(function() {
	voter = new Object();
        if (this.checked) {
			voter.name = this.value;
			voter.mobileNo = this.id;
			voter.boothName= this.placeholder;
          tempArray.push(voter);
      }
  });

 
  if(tempArray == null || tempArray.length == 0)
	 return;

  window.opener.receiveFromVoterSearchChild ( tempArray );

}

$(document).ready(function(){
  
  $(".selectAll").live("click",function(){
	
      if(this.checked)
	  {
	        $(".checkbox").attr('checked', true);

		$('.checkbox').each(function(index,value){
			var name = $(this).val().split("-")[0];
			var mobileNumber = $(this).val().split("-")[1];

		   if(window.opener.selectedInfluencePeopleDetails[name] == undefined)
					window.opener.selectedInfluencePeopleDetails[name] = mobileNumber;


		   if($.inArray(mobileNumber, window.opener.selectedMobileNumbers) == -1)
				   window.opener.selectedMobileNumbers.push(mobileNumber);
		});
	  }
  });

});

getInfluencePeopleDetailsBySearchCritteria();

function getInfluencePeopleDetailsBySearchCritteria()
{

YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	{

			var name= oRecord.getData("name");
			var mobileNumber = oRecord.getData("mobileNumber");

	 if(window.opener.selectedVotersDetails[name] == undefined)
		var str ='<input type="checkbox" onchange="pushIntoInfluencePeopleObject(\''+name+'\',\''+mobileNumber+'\')" class="checkbox" value="'+name+'-'+mobileNumber+'"/>';
	else
		var str ='<input type="checkbox" checked onchange="pushIntoInfluencePeopleObject(\''+name+'\',\''+mobileNumber+'\')" class="checkbox" value="'+name+'-'+mobileNumber+'"/>';

		elLiner.innerHTML =str;
	}


var votersByLocBoothColumnDefs = [
{key:"select", label: "<input type='checkbox' class='selectAll'/>", width:70,formatter:YAHOO.widget.DataTable.Type},
{key:"name", label: "Name",sortable: true},
{key:"mobileNumber",label:"Mobile Number",sortable:false},
];


var urlStr = "searchCandidatesForVoiceSmsAction.action?publicationDateId=8&locationType="+locationType+"&locationValue="+locationId+"&";


urlStr += "isAgeSelected="+isAgeSelected+"&isCasteSelected="+isCasteSelected+"&isFamilySelected="+isFamilySelected+"&isNameSelected="+isNameSelected+"&isGenderSelected="+isGenderSelected+"&";

   if(isAgeSelected == "true")
	   urlStr += "startAge="+startAge+"&endAge="+endAge+"&";
   if(isCasteSelected == "true")
	   urlStr += "casteIds=4-5&";

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
"mobileNumber"],

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
	   window.opener.selectedMobileNumbers.push(mobileNumber);
	}
	else{

		delete window.opener.selectedInfluencePeopleDetails[voterName];

		var index =  window.opener.selectedMobileNumbers.indexOf(mobileNumber);
         array.splice(index, 1);
	}
}
</script>
</body>
</html>