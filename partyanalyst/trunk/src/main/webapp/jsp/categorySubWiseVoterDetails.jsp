<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 

	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 

	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	  <script type="text/javascript" src="js/jQuery/js/jquery-1.4.2.min.js"></script>
	  <link href="styles/assets/css/bootstrap.css" rel="stylesheet">
	  <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
	<!-- Skin CSS files resize.css must load before layout.css --> 
	
<title>Voters Attributes Analysis</title>
</head>
<body>
<style>

#mainDiv{margin-left:auto;margin-right:auto;width:980px;}
 .table thead.info th{
    background: none repeat scroll 0 0 #D9EDF7;
    color: #454545;
   }
 #subHeading{ border-bottom: 1px solid #C0C0C0;
    font-family: sans-serif,Helvetica;
    font-size: 14px;
    font-weight: bold;
    line-height: 20px;
	margin-left:-6px;
	width:940px;
    padding: 5px 20px;
    text-rendering: optimizelegibility;
    text-transform: uppercase;
	font-family:sans-serif,Helvetica;
}  
   .table th, .table td {
    border-top: 1px solid #DDDDDD;
    line-height: 18px;
    padding: 8px !important;
    text-align: left;
    vertical-align: top;
}
</style>
<script type="text/javascript">

function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
									if(jsObj.task == "getCategoryWiseSubDetails")
								    {
										buildResults(myResults,jsObj);
										
								    }
									else if(jsObj.task == "getAgeWiseWiseDetails")
									  buildAgeWiseWiseDetails(myResults,jsObj);
									else if(jsObj.task == "getCasteWiseWiseDetail"){
											   $('#casteAjaxImage').hide();
									buildCasteWiseWiseDetails(myResults,jsObj);
									}
								else if(jsObj.task == "getCategoryWiseDetails")
								   buildCategoryWiseDetails(myResults,jsObj);
                               
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
	
   function getCategorySubDetails(){
    $("#busyImage").show();
	   var jsObj = {
	        attributeId:${id},
			locationType:'${retrieveType}',
			locationId:${locationId},
			constituencyId:${constituencyId},
			publicationId:${publicationId},
			task:"getCategoryWiseSubDetails"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getCategoryWiseDetailsAction.action?"+rparam;
		callAjax(jsObj, url);
	}
	
	
   function buildCategoryWiseDetails(results,jsObj){
	
	 var str = "";
	  if(results != null && results.length >0){
	  $("#busyImage").hide();
	     // str+= "<div style='margin-bottom:5px;'><b style='font-size:13px;'>Total Voters : </b>"+results[0].totalVoters+"</div>";
	     for(var i in results){
		  if(results[i].partyWisevoterCastInfoVOList != null && results[i].partyWisevoterCastInfoVOList.length > 0){
			   str+="<div style='border:1px solid #d3d3d3;padding:5px 5px 31px;margin-bottom:20px;border-radius: 4px 4px 4px 4px;'>";
			   str+= "<h2 id='subHeading'><b>"+results[i].name+" Attribute Wise Voters Analysis</b></h2>";
			   str+= "<div style='margin-top:5px;'><span><b style='font-size:14px;'>Total Voters : </b>"+results[0].totalVoters+"</span>&nbsp;&nbsp;<span><b style='font-size:14px;'>"+results[i].name+" assigned voters : </b>"+results[i].partyWiseAssignedVoters+"  </span>&nbsp;&nbsp;<span><b style='font-size:14px;'>   "+results[i].name+" not assigned voters : </b>"+results[i].partyWiseNotAssignedVoters+"</span></div>";
			   
			   
			   str+="<table class='table table-bordered table-striped table-hover' style='margin-bottom: 5px; margin-top: 7px;'>";
			   str+="<thead class='info'>"
			   str+="  <tr>";
			   str+="     <th>"+results[i].name+"</th>";
			   str+="     <th>Total Voters</th>";
			   str+="     <th>Male Voters</th>";
			   str+="     <th>Female Voters</th>";
			   str+="     <th>Voters %</th>";
			   str+="  </tr>";
			   str+="</thead>"
			   for(var j in results[i].partyWisevoterCastInfoVOList){
			     var obj = results[i].partyWisevoterCastInfoVOList[j];
				  str+="  <tr>";
			      str+="     <td>"+obj.name+"</td>";
			      str+="     <td>"+obj.totalVoters+"</td>";
			      str+="     <td>"+obj.maleVoters+"</td>";
			      str+="     <td>"+obj.femaleVoters+"</td>";
			      str+="     <td>"+obj.partyPercentage+"</td>";
			      str+="  </tr>";
			   }
			   str+="</table>";
			  
			   str+="</div>";
		   }
		 }
		 $("#categoriesValuesDiv").html(str);
	  }
	}


   function buildResults(results,jsObj){
        if(results != null && results.length >0 && results[0].partyWisevoterCastInfoVOList != null && results[0].partyWisevoterCastInfoVOList.length > 0){
	      var str = "";
		  str+= "<h2 id='subHeading'><b>"+results[0].mandalName+" Wise "+results[0].castName+" Attribute Analysis</b></h2>";
		  str+="<table class='table table-bordered table-striped table-hover'>";
		  str+="<thead class='info'>";
		  str+="   <tr>";
		  str+="     <th rowspan='2'>"+results[0].mandalName+"</th>";
		  for(var i in results[0].partyWisevoterCastInfoVOList)
		  str+="     <th colspan='2'>"+results[0].partyWisevoterCastInfoVOList[i].name+"</th>";
		   str+="    <th rowspan='2'>%</th>";
		  str+="   </tr>";
		  str+="   <tr>";
		  
		  for(var i in results[0].partyWisevoterCastInfoVOList){
		   str+="     <th>Male</th>";
		   str+="     <th>Female</th>";
		  }
		  str+="   </tr>";
		  str+="</thead>";
		  for(var i in results){
		  str+="   <tr>";
		  str+="     <td>"+results[i].name+"</td>";
		  for(var j in results[i].partyWisevoterCastInfoVOList){
		    str+="     <td>"+results[i].partyWisevoterCastInfoVOList[j].maleVoters+"</td>";
		    str+="     <td>"+results[i].partyWisevoterCastInfoVOList[j].femaleVoters+"</td>";
		  }
		  str+="     <td>"+results[i].partyPercentage+"</td>";
		  str+="   </tr>";
		  }
		  str+="</table>";
		  $("#categorySubtable").html(str);
		  $("#categorySubtable").show();
	   }else{
	      $("#categorySubtable").hide();
	   }

   } 

   function getCastewiseInfoForVoterCategory(){
	   $('#casteAjaxImage').show();
     
	  var str = ${attributeIds};
		var jsObj = {
	        attributeIds: ''+str+'',
			locationType:'${retrieveType}',
			locationId:${locationId},
			constituencyId:${constituencyId},
			publicationId:${publicationId},
			task:"getCasteWiseWiseDetail"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getCasteWiseUserVoterCategory.action?"+rparam;
		callAjax(jsObj, url);
		$("#ajaxImage").css("display","block");
   }

   function getAgewiseInfoForVoterCategory(){
	  var str = ${attributeIds};
		var jsObj = {
	        attributeIds: ''+str+'',
			locationType:'${retrieveType}',
			locationId:${locationId},
			constituencyId:${constituencyId},
			publicationId:${publicationId},
			task:"getAgeWiseWiseDetails"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getAgeWiseWiseDetailsAction.action?"+rparam;
		callAjax(jsObj, url);
		$("#ajaxImage").css("display","block");
	}

function buildCasteWiseWiseDetails(result,jobj)
{
	if(result == null || result.length == 0)
		return false;

	var attributeName = result[0].categoryName;

	var count = 0;
	var str =  '';
    
	str+='<div style="border:1px solid #d3d3d3;padding:5px 5px 31px;margin-bottom:20px;border-radius: 4px 4px 4px 4px;">';
	str+= "<h2 id='subHeading'><b> "+attributeName+"  Attribute Caste Wise Voters Analysis</b></h2>";
	str += '<table class="table table-bordered table-striped table-hover" id="casteDetailsTable" style="margin-bottom: 5px; margin-top: 7px;">';
	     str+="<thead class='info'>";
		 str += '<tr>';
		  str += '<th>Caste Name</th>';
		  str += '<th>Male Voters</th>';
		  str += '<th>Female Voters</th>';
		  str += '<th>Total Voters</th>';
		  str += '<th>Percent</th>';
		 str += '</tr>';
		 str+='</thead>';

	for(var i in result)
	{
		str += '<tr>';
			str += '<td>'+result[i].castName+'</td>';
			str += '<td>'+result[i].totalMaleVoters+'</td>';
			str += '<td>'+result[i].totalFemaleVoters+'</td>';
			str += '<td>'+result[i].totalVoters+'</td>';
			str += '<td>'+result[i].totalVotersPercent+'</td>';

			count = count + parseInt(result[i].totalVoters);
		str += '</tr>';	

	}

	str+='</table>';
	str+='</div>';


	$('#casteDetailsDiv').html(str);
	$('#casteDetailsTable').dataTable({
		"aaSorting": [[ 4, "desc" ]],
	});


}

 function buildAgeWiseWiseDetails(result,jobj)
 {
	$("#ajaxImage").css("display","none");
	var str = '';
	if(result != null && result.length >0)
	{
		str +='<div>';
		for(var i in result)
		{
		 str+="<div style='border:1px solid #d3d3d3;padding:5px 5px 31px;margin-bottom:20px;border-radius: 4px 4px 4px 4px;'>";
		 str+= "<h2 id='subHeading'><b>"+result[i].name+" Attribute Age Wise Voters Analysis</b></h2>";
		 str+="<table class='table table-bordered table-striped table-hover' style='margin-bottom: 5px; margin-top: 7px;'>";
		 str+="<thead class='info'>";
		 str  +='<tr>';
		 str+="<th rowspan=3>"+result[i].name+"</th>";
		 str+=" <th colspan=3>18-25</th>";
		 str+=" <th colspan=3>25-36</th>";
		 str+=" <th colspan=3>36-45</th>";
		 str+=" <th colspan=3>46-60</th>";
		 str+=" <th colspan=3>60-Above</th>";
		 str+='</tr>';
		 str  +='<tr>';
		 str+=" <th>Male</th>";
		 str+=" <th>Female</th>";
		 str+=" <th>Total</th>";
		 str+=" <th>Male</th>";
		 str+=" <th>Female</th>";
		 str+=" <th>Total</th>";
		 str+=" <th>Male</th>";
		 str+=" <th>Female</th>";
		 str+=" <th>Total</th>";
		 str+=" <th>Male</th>";
		 str+=" <th>Female</th>";
		 str+=" <th>Total</th>";
		 str+=" <th>Male</th>";
		 str+=" <th>Female</th>";
		 str+=" <th>Total</th>";
		 str+='</tr>';
		 str+="</thead>";
	 for(var j in result[i].votersDetailsVOList)
	 {
	   var obj = result[i].votersDetailsVOList[j];

		str +='<tr>';
		if(obj.maleVotersCountBetween18To25 == null)
	 	  obj.maleVotersCountBetween18To25 =0;
		if(obj.femaleVotersCountBetween18To25 == null)
		 obj.femaleVotersCountBetween18To25 =0;
		if(obj.maleVotersCountBetween26To35 == null)
		 obj.maleVotersCountBetween26To35 =0;
		if(obj.femaleVotersCountBetween26To35 == null)
		 obj.femaleVotersCountBetween26To35 =0;
		if(obj.maleVotersCountBetween36To45 == null)
		 obj.maleVotersCountBetween36To45 =0;
		if(obj.femaleVotersCountBetween36To45 == null)
		 obj.femaleVotersCountBetween36To45 =0;
		if(obj.maleVotersCountBetween46To60 == null)
		 obj.maleVotersCountBetween46To60 = 0;
		if(obj.femaleVotersCountBetween46To60 == null)
		 obj.femaleVotersCountBetween46To60 = 0;
		if(obj.maleVotersCountAbove60 == null)
		 obj.maleVotersCountAbove60 = 0;

		if(obj.femaleVotersCountAbove60 == null)
	     obj.femaleVotersCountAbove60 = 0;

		var totalFor18To25 = obj.maleVotersCountBetween18To25+obj.femaleVotersCountBetween18To25;
		var totalFor26To35 = obj.maleVotersCountBetween26To35+obj.femaleVotersCountBetween26To35;
		var totalFor36To45 = obj.maleVotersCountBetween36To45+obj.femaleVotersCountBetween36To45;
		var totalFor46To60 =obj.maleVotersCountBetween46To60+obj.femaleVotersCountBetween46To60;
		var totalFor60 = obj.maleVotersCountAbove60+obj.femaleVotersCountAbove60;

		str +='<td>'+obj.name+'</td>';

		str +='<td>'+obj.maleVotersCountBetween18To25+'</td>';

		str +='<td>'+obj.femaleVotersCountBetween18To25+'</td>';

		str +='<td>'+totalFor18To25+'</td>';
		str +='<td>'+obj.maleVotersCountBetween26To35+'</td>';

		str +='<td>'+obj.femaleVotersCountBetween26To35+'</td>';
		str +='<td>'+totalFor26To35+'</td>';
		str +='<td>'+obj.maleVotersCountBetween36To45+'</td>';

		str +='<td>'+obj.femaleVotersCountBetween36To45+'</td>';
		str +='<td>'+totalFor36To45+'</td>';
		str +='<td>'+obj.maleVotersCountBetween46To60+'</td>';

		str +='<td>'+obj.femaleVotersCountBetween46To60+'</td>';
		str +='<td>'+totalFor46To60+'</td>';
		str +='<td>'+obj.maleVotersCountAbove60+'</td>';

		str +='<td>'+obj.femaleVotersCountAbove60+'</td>';
		str +='<td>'+totalFor60+'</td>';


		str +='</tr>';

		}
 		str+="</table>";
		str+='</div>';
		str+="</div>";
	   }
	 }
	$("#agerangeDiv").html(str);
}

function callMethodToGetData(){
 var str = ${attributeIds};
	   
		$("#categoriesAjximgMsgDiv").show();
	   var jsObj = {
	        attributeIds:''+str+'',
			locationType:'${retrieveType}',
			locationId:'${locationId}',
			constituencyId:'${constituencyId}',
			publicationId:'${publicationId}',
			task:"getCategoryWiseDetails"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "<%=request.getContextPath()%>/getCategoryWiseDetailsAction.action?"+rparam;
		callAjax(jsObj, url);
	}
	

</script>
<div id="mainDiv">
<div id="categoriesValuesDiv"></div><br>
<img id="busyImage" src="./images/icons/goldAjaxLoad.gif" alt="Processing Image" style="display:none;"/>
<div id="categorySubtable" style='overflow-x:scroll;'></div>
<br>
<img id="ajaxImage" src="./images/icons/goldAjaxLoad.gif" alt="Processing Image" style="display:none;"/>

<div id="agerangeDiv"></div>

<img id="casteAjaxImage" src="./images/icons/goldAjaxLoad.gif" alt="Processing Image" style="display:none;" style="display:none;margin:30px;"/>
<div id="casteDetailsDiv"></div>
</div>
<script type="text/javascript">
   getCategorySubDetails();
   getAgewiseInfoForVoterCategory();
   callMethodToGetData();
   getCastewiseInfoForVoterCategory();
</script>
</body>
</html>