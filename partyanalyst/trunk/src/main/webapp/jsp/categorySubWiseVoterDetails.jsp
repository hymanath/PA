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
   
</script>
<div id="categorySubtable" style='overflow-x:scroll;'></div>
<script type="text/javascript">
   getCategorySubDetails();
</script>
</body>
</html>