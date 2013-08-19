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
</script>
</head>
<body>
<div id="mainDiv">

<div id="voterDetailsDiv"></div>
<div id="paginationDivId"></div>
<div id="btnDiv" style="display:none;"><input type="button" class="btn btn-info" value="Add This Contacts" id="phoneNoBtn" onclick="getPhoneNos()" /></div>

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
  str +='<th>Select</th>';
  str +='<th>Name</th>';
  str +='<th>House No</th>';
  str +='<th>Mobile No</th>';
  str +='<th>Booth Name</th>';
  str +='</tr>';
  for(var i in result)
  {
    str +='<tr>';
    str +='<td><input type="checkbox" name="check" value="'+result[i].mobileNo+'" /></td>';
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
getWardsForMuncipality(0);


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
</script>
</body>
</html>