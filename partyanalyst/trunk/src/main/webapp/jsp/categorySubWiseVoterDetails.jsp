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
	<!-- Skin CSS files resize.css must load before layout.css --> 
	
<title>Voters Attributes Analysis</title>
</head>
<body>
<style>
 .table thead.info th{
    background: none repeat scroll 0 0 #D9EDF7;
    color: #454545;
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
								
                                }catch (e) {
							    
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
   
   function buildResults(results,jsObj){
        if(results != null && results.length >0 && results[0].partyWisevoterCastInfoVOList != null && results[0].partyWisevoterCastInfoVOList.length > 0){
	      var str = "";
		  str+= "<h2 id='subHeading' style='font-size:21px;'><b>"+results[0].mandalName+" Wise "+results[0].castName+" Attribute Analysis</b></h2>";
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
		 str+= "<h2 id='subHeading' style='margin-left:-5px;width:97%;'><b>"+result[i].name+" Attribute Age Wise Voters Analysis</b></h2>";
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

</script>
<div id="categorySubtable" style='overflow-x:scroll;'></div>
<br><br>
<img id="ajaxImage" src="./images/icons/goldAjaxLoad.gif" alt="Processing Image" style="display:none;"/>

<div id="agerangeDiv"></div>
<script type="text/javascript">
   getCategorySubDetails();
   getAgewiseInfoForVoterCategory();
</script>
</body>
</html>