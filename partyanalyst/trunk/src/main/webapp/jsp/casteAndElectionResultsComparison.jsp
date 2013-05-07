<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cast Wise Election Results Analysis</title>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css">
  <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
  <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
  <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
 <style>
   .blue {
      color:black;
   }
   .space{
     margin-left:8px;
	 margin-right:50px;
   }
   
   .topBottomSpace{
     margin-top:10px;
	 padding-bottom:10px;
   }
   
   #votersBasicInformationDiv{
     padding-bottom: 20px;
   }
   
   #selectionErrMsgDiv{
    color:red;
	font-weight:bold;
   }
 </style>
 <script type="text/javascript">
  var locationSelected = "mandal";
    function showHideLocations(type){
	     locationSelected = type;
	       $("#constituencyList").val(0);
		  if(type == 'mandal'){
		   $("#showMandal").hide();
		    //$("#mandalSelectedSpan").html("<select id='mandalSelectedId' multiple='multiple'></select>");
		   $("#showPanchayat").hide();
		   $("#showBooth").hide();
		  }else if(type == 'panchayat'){
		   $("#showMandal").show();
		   $("#mandalSelectedSpan").html("<select id='mandalSelectedId' ></select>");
		   $("#showPanchayat").hide();
		   //$("#panchayatSelectedSpan").html("<select id='panchayatSelectedId' multiple='multiple'></select>");
		   $("#showBooth").hide();
		  }else if(type == 'booth'){
		   $("#showMandal").show();
		   $("#mandalSelectedSpan").html("<select id='mandalSelectedId' onchange='getPanchayaths();'></select>");
		   $("#showPanchayat").show();
		   $("#panchayatSelectedSpan").html("<select id='panchayatSelectedId' ></select>");
		   $("#showBooth").hide();
		   //$("#boothSelectedSpan").html("<select id='boothSelectedId' multiple='multiple'></select>");
		  }
	}
	
	$(document).ready(function(){
	   showHideLocations('mandal');
	   var jsObj=
			{
				task:"getCategoriesForAUser"
	
			}
	     var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersInfoBySearchAction.action?task=categories&save=";						
		   callAjaxToGetData(jsObj,url);
	});
	
	function getMandals(){
	  var reqId = $("#constituencyList").val();
	  if(reqId == 0)
	    return;
		var jsObj=
			{
		      id:reqId,
			  task:"subRegionsInConstituency",
			  taskType:"",
			  selectElementId:"",
			  address:"",
			  areaType:"RURAL",
			  constId:"",
			  buildId:"mandalSelectedId"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "locationsHierarchiesAjaxAction.action?"+rparam;	
   
		callAjaxToGetData(jsObj,url);
	}
	
	function getPanchayaths(){
	  var reqId = $("#mandalSelectedId").val();
	  if(reqId == 0)
	    return;
	  var jsObj=
		{
		  tehsilId:reqId.slice(1),
		  task:"getPanchayatsByTehsilId",
		  buildId:"panchayatSelectedId"
		}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPanchayatAndBoothDetailsAction.action?"+rparam;	

	callAjaxToGetData(jsObj,url);
	}
	
	function getBooths(){
	  var reqId = $("#panchayatSelectedId").val();
	  if(reqId == 0)
	    return;
	  var jsObj=
		{
		  panchayatId:reqId,
		  task:"getBoothsByPanchayatID",
		  constituencyId:$("#constituencyList").val(),
		  buildId:"boothSelectedId"
		}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPanchayatAndBoothDetailsAction.action?"+rparam;	

	callAjaxToGetData(jsObj,url);
	}
	
	function callAjaxToGetData(jsObj,url)
    {
			 var myResults;
			 var callback = {			
 		               success : function( o ) {
							try {												
								    myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
									if(jsObj.task == "subRegionsInConstituency" || jsObj.task == "getPanchayatsByTehsilId"  || jsObj.task == "getBoothsByPanchayatID" )
									{
										buildResults(myResults,jsObj);
									}else if(jsObj.task == "getCategoriesForAUser"){
									   buildCategoriesListInit(myResults);
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
   
   function buildResults(results,jsObj){
       $("#"+jsObj.buildId+" option").remove();
	   var type = $("#"+jsObj.buildId).attr("multiple");
      if(results != null && results.length > 0){
	      for(var i in results){
		    if(!(type == "multiple" && results[i].id == 0)){
			   var id = results[i].id+"";
			  if(!(results[i].id != 0 && jsObj.buildId == "mandalSelectedId" && id.substring(0,1) == "1"))
                $("#"+jsObj.buildId).append('<option value='+results[i].id+'>'+results[i].name+'</option>');
			}	
          }		  
	  }
   }
   
   function buildCategoriesListInit(result){
	  var str ='';
	    str+='<table style="margin-left:5px;margin-bottom:5px;color:#000000;">';
		str+='  <tr>';
	    str+='   <td style="padding-left:20px;"><input type="radio" onclick="checkAndGetData(\'cast\');" name="attributesRadio" class="attributeTypeClassIni" value="cast,Caste"/>  Caste</td>';
		str+='   <td style="padding-left:20px;"><input type="radio" onclick="checkAndGetData(\'party\');" name="attributesRadio" class="attributeTypeClassIni" value="party,Party"/>  Party</td>';
		var x = 2;
		if(result != null && result.category != null && result.category.length > 0){
		  for(var i in result.category){
		   if(x%5 == 0)
		    str+='<tr>';
		   str+='   <td style="padding-left:20px;"><input type="radio" onclick="checkAndGetData('+result.category[i].id+');" name="attributesRadio" class="attributeTypeClassIni" value="'+result.category[i].id+','+result.category[i].name+'"/>  '+result.category[i].name+'</td>';
	        x = x+1;
			if(x%5 == 0)
		    str+='</tr>';
		  }	
		  if(x%5 != 0)
		    str+='</tr>';
		}else{
		  str+='</tr>';
		}
	   str+='</table>';
	   
	    $("#requiredFieldsToCheck").html(str);
	  }
	  
	  function checkAndGetData(type){
	      if(locationSelected == 'mandal'){
			   var id = $("#constituencyList").val();
			   if(id == 0 || id == null){
				 $("#selectionErrMsgDiv").html("Please Select Constituency");
				 unselectAll();
				 return;
				 
			   }
			   $("#selectionErrMsgDiv").html("");
			   
		  }else if(locationSelected == 'panchayat'){
		       var id = $("#mandalSelectedId").val();
			   if(id == 0 || id == null){
				 $("#selectionErrMsgDiv").html("Please Select Mandal");
				 unselectAll();
				 return;
			   }
			   $("#selectionErrMsgDiv").html("");
			   var jsObj=
			   {
				
				tehsilId:id.substr(1),
				task:"getElectionsAndParties"
			   }
			   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			   var url = "getPanchayatWiseElectionResultsAction.action?"+rparam;	
		       callAjaxToGetData(jsObj,url);
		  }else if(locationSelected == 'booth'){
		       var id = $("#panchayatSelectedId").val();
			   if(id == 0 || id == null){
				 $("#selectionErrMsgDiv").html("Please Select Panchayat");
				 unselectAll();
				 return;
			   }
			   $("#selectionErrMsgDiv").html("");
			   var jsObj=
			   {
				
				panchayatId:id,
				task:"getPanchayatElectionsAndParties"
			   }
			   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			   var url = "getBoothWiseElectionResultsAction.action?"+rparam;	
		       callAjaxToGetData(jsObj,url);
		  }
	  
	  }
	  
	  function unselectAll(){
		  $('.attributeTypeClassIni').each(function() {
            $(this).removeAttr('checked');
          });
	  }
	  
	  if(type == "mandal"){
         
     }
      else if(type == "panchayat")
      {
        
     }
  
 </script>
</head>
<body>
  <div  class="widget blue whitegloss" id="votersBasicInformationDiv" align="center">
    <div class="topBottomSpace"><span><input onclick="showHideLocations('mandal')" class="locationLevel" name="locationLevel" type="radio" checked="checked"/><b class="space">Mandal Wise</b></span><input onclick="showHideLocations('panchayat')" class="locationLevel" name="locationLevel" type="radio"/><b class="space">Panchayat Wise</b></span><input  class="locationLevel"  onclick="showHideLocations('booth')" name="locationLevel" type="radio"/><b class="space">Booth Wise</b></span></div>
	<table style="margin-bottom:20px;margin-top:5px;margin-left: -205px;">
	 <tr>
	    <td colspan="2"><div id="selectionErrMsgDiv"></div></td>
	 </tr>
	 <tr>
		<td><b>Constituency :</b></td><td> <s:select theme="simple" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="getMandals();"/></td>
	 </tr>
     <tr id="showMandal">	 
		<td><b>Mandal :</b></td><td> <span id="mandalSelectedSpan"></span></td>
	 </tr>
     <tr id="showPanchayat">	
		<td><b>Panchayat :</b></td><td> <span id="panchayatSelectedSpan"></span></td>
	 </tr>
     <tr id="showBooth">	
		<td><b>Booth :</b></td><td>  <span id="boothSelectedSpan"></span></td>
	 </tr>
	</table>
	<div id="requiredFieldsToCheck"></div>
  </div>
  
  </body>
</html>