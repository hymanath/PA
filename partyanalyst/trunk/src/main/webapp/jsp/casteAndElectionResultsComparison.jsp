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
 <script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
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
   .tableRow
   {
		height: 56px;
   }
   .hero-unit{padding:22px;color:black;font-size:15px;margin-bottom: 5px;margin-top: 10px;}
   .hero-unit {
    color: black;
    font-size: 15px;
    margin-bottom: 5px;
    margin-top: 10px;
    padding: 22px;
	}
   label {
    display: inline-block;
	}
	label {
		margin-bottom: 5px;
	}
	label, input, button, select, textarea {
		font-size: 13px;
		font-weight: normal;
		line-height: 18px;
	}
	input[type="radio"], input[type="checkbox"] {
    margin: 5px;
	}
	#errorMsgDiv {
    color: red;
    font-size: 14px;
    margin-left: -117px;
    margin-top: 10px;
	}
 </style>
 <script type="text/javascript">
  var locationSelected = "mandal";
  var mainStr = "";
  var substr = "";
  var attrPerc;
  var resultsData;
  var type;
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
	var value =  $("#constituencyList option:selected").val();
	  var jsObj=
			{
					
					selected:value,
					selectElmt:"mandalField",
					str   : "all",
					task:"getMandalList"
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjaxToGetData(jsObj,url);
	}
	function buildMandalList(result)
	{
		var name = "";
		
		name += '<b>Mandals </b><font class="requiredFont" style="color:red">*</font>';
		$('#multiSelectLevelsHeading').html(name);
		
		var value = "";
		value += '<select name="values" id="multipleSelect" onChange="getUserCategoersFoeSelect(\'mandal\');" class="multipleSelect" multiple >';
		if(result != null)
		{
			
			var id ;
			for(var i in result)
			{
				if(i > 0)
				{
					value += '<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
			}
			
		}
		value += '</select>';
		$('#multiSelectLevelsValues').html(value);

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
								
									if(jsObj.task == "getMandalList")
									{
										if(jsObj.str == "all")
										{
											buildMandalList(myResults);
										}
										else
										{
											buildAllMandals(myResults,jsObj,'');
										}
									}
									else if(jsObj.task == "getPanchayat")
									{
										buildPanchayats(myResults);
									}
									else if(jsObj.task == "getReportLevelDetails")
									{
										if(jsObj.str == "all")
										{
											buildReportLevelData(myResults,jsObj);
										}
										else if(jsObj.str == "mandal")
										{
											buildAllMandals(myResults,jsObj,'');
										}
										else if(jsObj.str == "panchayat")
										{
											buildAllPanchayats(myResults,jsObj);
										}
									}
									else if(jsObj.task == "getUserCategoeryValues")
									{
										$('#processingImg').hide();
										buildUserCategoerySelectedValues(myResults,jsObj);
									}
									else if(jsObj.task == "getUserCatgValuesForWard")
									{
										$('#processingImg').hide();
										buildUserCategoerySelectedValues(myResults,jsObj);
									}
									else if(jsObj.task == "getUserCategoeryValuesForWard")
									{
										buildUserCategoerySelectedValues(myResults,jsObj);
									}
									else if(jsObj.task == "getCategoriesForAUser")
									{
									   buildCategoriesListInit(myResults);
									}
									else if(jsObj.task == "getSelectedMandalOrPanchayatData")
									{
									    buildPanchayatsOrMandalsList(myResults,jsObj,'mandal');
									}
									else if(jsObj.task == "hamletsOrWardsInRegion")
									{
										buildPanchayatsOrMandalsList(myResults,jsObj ,'muncipality');
										
									}
									else if(jsObj.task == "getAllBoothsInAWard")
									{
										buildPanchayatsOrMandalsList(myResults,jsObj ,'wards');	
									}
									else if(jsObj.task == "getBoothsInMuncipality")
									{
										buildPanchayatsOrMandalsList(myResults,jsObj ,'');
									}
									else if(jsObj.task == "getConstituencyType")
									{
										
										buildConstituencyRelatedList(myResults);
									}
									else if(jsObj.task == "getAllWards")
									{
										if(jsObj.type == "main")
										{
											buildAllMandals(myResults,jsObj,'ward');
										}
										else
										{
											buildAllWards(myResults,jsObj);
										}
									}else if(jsObj.task == "buildChart"){
									  
									  resultsData = myResults;
									  buildChart("voterscount");
									}
									else if(jsObj.task == "getConstiEleAndPartiesForSelected")
									{
										buildElectionAndPartyDetails(myResults);
									}
									else if(jsObj.task == "getCategValuesForMuncipalWard")
									{
										buildElectionAndPartyDetails(myResults);
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
	  var name ='';
	  var value = "";
		name +='<b>Select Type</b>';
		$("#requiredFieldsToCheckName").html(name);
		value +='<select id="selectdUserCatgId" onChange="checkAndGetData(\'cast\',\'Caste\',mainStr);"><option value="0">Select Type</ortion>';
		value +='<option value="1">Caste</option>';
		value +='</select><span><img alt="Processing Image" src="./images/icons/search.gif" id="processingImg" style="display:none"></span>';
		 $("#requiredFieldsToCheckValue").html(value);
	    //str+='<input type="" onclick="checkAndGetData(\'cast\',\'Cast\');" name="attributesRadio" class="attributeTypeClassIni" value="cast,Caste"/>  Caste';
		/* str+='   <td style="padding-left:20px;"><input type="radio" onclick="checkAndGetData(\'party\',\'Party\');" name="attributesRadio" class="attributeTypeClassIni" value="party,Party"/>  Party</td>';
		var x = 2;
		if(result != null && result.category != null && result.category.length > 0){
		  for(var i in result.category){
		   if(x%5 == 0)
		    str+='<tr>';
		   str+='   <td style="padding-left:20px;"><input type="radio" onclick="checkAndGetData('+result.category[i].id+',\''+result.category[i].name+'\');" name="attributesRadio" class="attributeTypeClassIni">  '+result.category[i].name+'</td>';
	        x = x+1;
			if(x%5 == 0)
		    str+='</tr>';
		  }	
		  if(x%5 != 0)
		    str+='</tr>';
		}else{
		  str+='</tr>';
		}
	   str+='</table>'; */
	   
	    
	   
	  }
	   function checkAndGetData(type,nameVal,selected)
	   {
		
		var categId = $('#selectdUserCatgId').val();
		if(categId == 0)
		{
			$('#selUserCatgId').hide();
		}
		else
		{
			$('#selUserCatgId').show();
		
			
			$('#rangeSliderDiv').show();
			$('#processingImg').show();
			var name = "";
			if(selected == "ward")
			{
				getUserCategoeryValuesForSelecetdWards();
			}
			
			else
			{
				var selectedValues = $('.multipleSelect').val();
				var values = "";
				var checkVal = "";
				for(var i in selectedValues)
				{
					checkVal = values+","+selectedValues[i];
					values = values+","+selectedValues[i];
				}
				 name = $("#levelId option:selected").text();
				if(name == 'Mandal')
				{
					if(checkVal.charAt(1) == 1)
					{
						var constituencyId = $("#constituencyList option:selected").val();
						var jsObj=
							{
								constituencyId   : constituencyId,
								status           : type,
								selectedValues   : values.slice(1),
								type             : "mandal",
								nameVal          : nameVal,
								task             : "getUserCategoeryValues"
							}
							var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
							var url = "getUserCategoeryValues.action?"+rparam;	

						callAjaxToGetData(jsObj,url);
					} 
					else
					{
					
						 name = $("#levelId option:selected").text();
						var id = $("#constituencyList option:selected").val();
						var jsObj=
							{
								constituencyId   : id,
								status           : type,
								selectedValues   : values.slice(1),
								type             : name,
								nameVal          : nameVal,
								task             : "getUserCategoeryValues"
							}
							var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
							var url = "getUserCategoeryValues.action?"+rparam;	

						callAjaxToGetData(jsObj,url);
					}
				}	
				else
				{
					if(selected == "booth" && substr == "RURAL")
					{
						
						name = $("#levelId option:selected").text();
					}
					else if(selected == "panchayat")
					{
						name = $("#levelId option:selected").text();
					}
					else if(selected == "")
					{
						name = $("#levelId option:selected").text();
					}
					else
					{
						 name = $("#urbanIds option:selected").text();
					}
					
					var id = $("#constituencyList option:selected").val();
					var jsObj=
						{
							constituencyId   : id,
							status           : type,
							selectedValues   : values.slice(1),
							type             : name,
							nameVal          : nameVal,
							task             : "getUserCategoeryValues"
						}
						var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
						var url = "getUserCategoeryValues.action?"+rparam;	

					callAjaxToGetData(jsObj,url);
				}
			}	
		}
	   }
	   
	   function buildUserCategoerySelectedValues(result,jsObj)
	   {
			var name = "";
			var value = "";
			name+= '<b>'+jsObj.nameVal+' </b><font class="requiredFont" style="color:red">*</font> ';
			$('#userSelCatgListHeading').html(name);
			value+= '<select id="selCategoeryList" multiple>';
			for(var i in result)
			{
				value+= '<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			value+='</select>';
			$('#userSelCatgListValue').html(value);
			
	   }
	 /*  function unselectAll(){
		  $('.attributeTypeClassIni').each(function() {
            $(this).removeAttr('checked');
          });
	  }
	    */
	function getRespectiveSelection()
	{
		var selecetd =  $("#levelListId option:selected").val();
		getRespectiveFields(selecetd);
	}
	
	function getRespectiveValues(constituencyType)
	{	/* 
		var checkedStatus = $('.radioCheck').is('ckecked');
		if(checkedStatus == false)
		{
			$('.radioCheck').prop('checked', false);
			$('#specificPanchayatValue').hide();
			$('#specificMandalValue').hide();
		} */
		
		$('#selReqFileldId').hide();
		$('#selUserCatgId').hide();
		var id = $("#constituencyList option:selected").val();
		if(constituencyType == 'urban')
		{
			$('#selLevelId').hide();
			$('#selMandalId').show();
			type = $("#urbanIds option:selected").text();
		}
		else
		{
			type = $("#levelId option:selected").text();
		}
		if(type == 'Mandal')
		{
			$('#selLevelId').show();
			$('#selMandalId').hide();
			$('#selPanchayatRow').hide();
			getMandals();
		}
		else if(type == "Ward")
		{
			var jsObj=
					{
						
						id    : id,
						type  : "main",
						task  : "getAllWards"
					}
					var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
					var url = "getUserCategoeryValues.action?"+rparam;	

				callAjaxToGetData(jsObj,url);
		}
		else
		{
			$('#selLevelId').show();
			$('#selPanchayatRow').show();
			$('#specificMandalValue').hide();
			var value = 1;
			var jsObj=
					{
						type  : type,
						level : value,
						id    : id,
						str   : "all",
						constituencyType:constituencyType,
						task  : "getReportLevelDetails"
					}
					var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
					var url = "getReportLevelDetails.action?"+rparam;	

				callAjaxToGetData(jsObj,url);
		}
	}
	function buildReportLevelData(result,jsObj)
	{
		var name = "";
		var mandal ="";
		var panchayat = "";
		if(jsObj.constituencyType == "urban")
		{
			$('#multiSelectLevelsHeading').show();
			$('#multiSelectLevelsValues').show();
			$('#specificMandalName').show(); 
			$('#selPanchayatRow').hide(); 
			name += '<b>Booths </b><font class="requiredFont" style="color:red">*</font> ';
			$('#multiSelectLevelsHeading').html(name);
			mandal += '<input type="radio" onClick="getWardsAConstituency();" class="mandalCheck,radioCheck"></input>';
			mandal +='<b class="space">Specific Ward</b>';
			$('#specificMandalName').html(mandal);
			var value = "";
				value += '<select name="values" id="multipleSelect" onChange="getUserCategoersFoeSelect(\'booth\');" class="multipleSelect" multiple >';
				if(result != null)
				{
					for(var i in result)
					{
						value += '<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					
				}
				value += '</select>';
				$('#multiSelectLevelsValues').html(value);
		}
		else
		{
			var name = "";
			var mandal ="";
			var panchayat = "";
			if(jsObj.type == "Mandal")
			{
				name += '<b>Mandals </b><font class="requiredFont" style="color:red">*</font>';
			}
			else if(jsObj.type == "Panchayat")
			{
				$('#selMandalId').show();
				$('#selPanchayatRow').hide();
				$('#specificMandalName').show();
				name += '<b>Panchayats </b><font class="requiredFont" style="color:red">*</font> ';

				
				mandal += '<input type="radio" onClick="getAllMandalsInAConstituency();" class="mandalCheck,radioCheck"></input>';
				mandal +='<b>Specific Mandal</b>';
				$('#specificMandalName').html(mandal);
				$('#multiSelectLevelsHeading').html(name);
				var value = "";
				value += '<select name="values" id="multipleSelect" onChange="getUserCategoersFoeSelect(\'panchayat\');" class="multipleSelect" multiple >';
				if(result != null)
				{
					for(var i in result)
					{
						value += '<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					
				}
				value += '</select>';
				$('#multiSelectLevelsValues').html(value);
			}
			else if(jsObj.type == "Booth")
			{
				$('#selPanchayatRow').show();
				$('#specificPanchayatName').show();
				$('#selMandalId').show();
				$('#specificMandalName').show();
				name += '<b>Booths </b><font class="requiredFont" style="color:red">*</font> ';
				
				mandal += '<input type="radio" onClick="getAllMandalsInAConstituency();" class="mandalCheck "></input>';
				mandal +='<b class="space">Specific Mandal</b>';
				panchayat += '<input type="radio" onClick="getAllPancayatsInAConstituency();" class="panchayatCkeck"></input>';
				panchayat +='<b>Specific Panchayat</b>';
				$('#specificPanchayatName').html(panchayat);
				$('#specificMandalName').html(mandal);
				$('#multiSelectLevelsHeading').html(name);
				var value = "";
				value += '<select name="values" id="multipleSelect" onChange="getUserCategoersFoeSelect(\'booth\');" class="multipleSelect" multiple >';
				if(result != null)
				{
					for(var i in result)
					{
						value += '<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
					
				}
				value += '</select>';
				$('#multiSelectLevelsValues').html(value);
				
			}
			
			
			
		}
		
		
	}
	function buildMandals(results)
	{
		$("#mandalId option").remove();
		
		for(var i in results)
		{
			$('#mandalId').append('<option value='+results[i].id+'>'+results[i].name+'</option>');
		}
	}
	
	function getPanchayats()
	{
	var selected = $("#mandalId option:selected").val();
	var value = selected.substr(1);
		var jsObj=
			{
					
				selected:value,
				checkedele:"panchayat",
				selectedEle:"panchayatField",
				constituencyId:$("#constituencyList option:selected").val(),
				flag:-1,
				type:"mandal",
				publicationValue : null,
				task:"getPanchayat"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByConstituencyAction.action?"+rparam;						
		callAjaxToGetData(jsObj,url);
	}
	function buildPanchayats(results)
	{
		$("#panchayatId option").remove();
		
		for(var i in results)
		{
			$('#panchayatId').append('<option value='+results[i].id+'>'+results[i].name+'</option>');
		}
	}
	function getAllPancayatsInAConstituency()
	{
		$('#specificPanchayatValue').show();
		$('#specificMandalValue').hide();
		var id = $("#constituencyList option:selected").val();
		var jsObj=
				{
					type  : "Panchayat",
					level : 1,
					id    : id,
					str   : "panchayat",
					task  : "getReportLevelDetails"
				}
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "getReportLevelDetails.action?"+rparam;	

			callAjaxToGetData(jsObj,url);
	}
	function getAllMandalsInAConstituency()
	{
		$('#specificMandalValue').show();
		$('#specificPanchayatValue').hide();
		var value =  $("#constituencyList option:selected").val();
		var jsObj=
			{
					
					selected:value,
					selectElmt:"mandalField",
					str   : "mandal",
					task:"getMandalList"
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjaxToGetData(jsObj,url);
		/* 
		var id = $("#constituencyList option:selected").val();
		var jsObj=
				{
					type  : "Mandal",
					level : 1,
					id    :id,
					str   : "mandal",
					task  :"getReportLevelDetails"
				}
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "getReportLevelDetails.action?"+rparam;	

			callAjaxToGetData(jsObj,url); */
	}
	function buildAllMandals(result,jsObj,type)
	{
		
		
		if(result != null)
		{
			if(type == "ward")
			{
				$('#specificMandalName').show();
				$('#specificMandalValue').show();
				var name="";
				name += '<b>Wards</b><font class="requiredFont" style="color:red">*</font>';
				$('#specificMandalName').html(name);
				var value = "";
				value+= '<select id="wardsList"  onChange="getUserCategoersFoeSelect(\'ward\');" class="multipleSelect" multiple>';
				for(var i in result)
				{
					value+= '<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				value+= '</select>';
				$('#specificMandalValue').html(value);
			
			}
			else
			{
				
				$('.panchayatCkeck').prop('checked', false);
				var mandalStr = "";
				mandalStr+= '<select id="maldalsList" onChange="getSelectedPanchayatsOrBooths(\'mandal\');" multiple>';
				for(var i in result)
				{
					if(i > 0)
					{
						mandalStr+= '<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
				}
				mandalStr+= '</select>';
				$('#specificMandalValue').html(mandalStr);
			}
		}
	}
	function buildAllPanchayats(result,jsObj)
	{
		
		$('.mandalCheck').prop('checked', false);
		if(result != null)
		{
			var panchayatStr = "";
			panchayatStr+= '<select id="panchayatsList" onchange="getSelectedPanchayatsOrBooths(\'panchayat\');" multiple>';
			for(var i in result)
			{
				panchayatStr+= '<option value='+result[i].id+'>'+result[i].name+'</option>';
			}
			panchayatStr+= '</select>';
			$('#specificPanchayatValue').html(panchayatStr);
		}
	}
	
	function getSelectedPanchayatsOrBooths(title)
	{
		var selectedValues ;
		var values = "";
		var checkVal = "";
		var level = $('#levelId').val();
		if(title == "mandal")
		{
		
			selectedValues	= $('#maldalsList').val();
		}
		else if(title == "panchayat")
		{
			selectedValues	= $('#panchayatsList').val();
		}
		
		if(title == "panchayat")
		{
			for(var i in selectedValues)
			{
				checkVal = values+","+selectedValues[i];
				values = values+","+selectedValues[i];
			}
			var jsObj=
				{
					type   : title,
					level  : level,
					values : values.slice(1),
					task   : "getSelectedMandalOrPanchayatData"
				}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getReportLevelDetails.action?"+rparam;	

			callAjaxToGetData(jsObj,url);
		}
		else if(title == "mandal")
		{
			for(var i in selectedValues)
			{
				checkVal = values+","+selectedValues[i];
				values = values+","+selectedValues[i].substr(1);
			}
			if(checkVal.charAt(1) == 1)
			{
				{
					var timeST = new Date().getTime();
					var jsObj =
					{ 
						time : timeST,
						id:checkVal.slice(1),
						task:"hamletsOrWardsInRegion",
						taskType:"",
						selectElementId:"",
						address:"",
						areaType:"",
						constId:""
						
					};
					var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
					var url = "locationsHierarchiesAjaxAction.action?"+rparam;
					callAjaxToGetData(jsObj,url);
				}
			}
			else
			{
				var jsObj=
					{
						type   : title,
						level  : level,
						values : values.slice(1),
						task   : "getSelectedMandalOrPanchayatData"
					}
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "getReportLevelDetails.action?"+rparam;	

				callAjaxToGetData(jsObj,url);
			}
		}
		
	}
	function buildPanchayatsOrMandalsList(result,jsObj,type)
	{
		mainStr = type;
		var name = "";
		var value = "";
		if(result != null)
		{
			
			if(type == 'mandal')
			{
				if(jsObj.level == 2)
				{
					name += '<b>Panchayats</b><font class="requiredFont" style="color:red">*</font>';
				}
				else if(jsObj.level == 3)
				{
					name += '<b>Booths</b><font class="requiredFont" style="color:red">*</font>';
				}
				$('#multiSelectLevelsHeading').html(name);
				value += '<select id="selMandalList" onChange="getUserCategoersFoeSelect(\'\');" class="multipleSelect" multiple>';
				for(var i in result)
				{
					value += '<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				value+='</select>';
				$('#multiSelectLevelsValues').html(value);
			}
			
		
			else if (type == 'muncipality')
			{
				name += '<b>Wards</b><font class="requiredFont" style="color:red">*</font>';
				$('#multiSelectLevelsHeading').html(name);
				value += '<select id="selMandalList" onChange="getUserCategoersFoeSelect(\'muncipality\');" class="multipleSelect" multiple>';
				for(var i in result)
				{
					if(i > 0)
					{
						value += '<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
				}
				value+='</select>';
					$('#multiSelectLevelsValues').html(value);
			}
			else
			{
				name += '<b>Booths</b><font class="requiredFont" style="color:red">*</font>';
				$('#multiSelectLevelsHeading').html(name);
				value += '<select id="selMandalList" onChange="getUserCategoersFoeSelect(\'booth\');" class="multipleSelect" multiple>';
				for(var i in result)
				{
					value += '<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				value+='</select>';
					$('#multiSelectLevelsValues').html(value);
			}
		}
	}
	function getUserCategoersFoeSelect(type)
	{
		$('#selReqFileldId').show();
		mainStr = type;
		$('#selReqFileldId').show();
		if(type == "ward")
		{
			$('#selMandalId').show();
			
		}
		else if(type == "mandal")
		{
			$('#selMandalId').hide();
		}
		else if(type == "panchayat")
		{
			$('#selPanchayatRow').hide();
		}
		else if (type == "")
		{
			$('#selPanchayatRow').show();
		}
		else if (type == "booth")
		{
			$('#selPanchayatRow').show();
		}
		else if(type == "muncipality")
		{
			var selectedValues = $('.multipleSelect').val();
			var values = "";
			var checkVal = "";
			for(var i in selectedValues)
			{
				values = values+","+selectedValues[i];
			}
			var constituencyId = $("#constituencyList option:selected").val();
				var jsObj=
				{
					constituencyId   : constituencyId,
					status           : type,
					selectedValues   : values.slice(1),
					task             : "getCategValuesForMuncipalWard"
				}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getUserCategoeryValues.action?"+rparam;	

			callAjaxToGetData(jsObj,url);
		}
		else
		{
			$('#selPanchayatRow').hide();
		}
		
		var value = $("#selectdUserCatgId option:selected").val();
		if(value > 0)
		{  
			checkAndGetData('cast','Caste',type);
		}
		$('#requiredFieldsToCheckValue').show();
		$('#requiredFieldsToCheckName').show();
	}
	
	function getConstituencyType()
	{
		
		var constituencyId = $("#constituencyList option:selected").val();
		var jsObj=
				{
					constituencyId : constituencyId,
					task           : "getConstituencyType"
				}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getReportLevelDetails.action?"+rparam;	

			callAjaxToGetData(jsObj,url);
	}

	function buildConstituencyRelatedList(result)
	{
		$('#levelId').val(0);
		if(result[0].name == "URBAN")
		{
			substr = "URBAN";
			$('#urbanConstituency').show();
			$('#ruralConstituency').hide();
			$('#selLevelId').hide();
			$('#selMandalId').hide();
			$('#selPanchayatRow').hide();
			$('#selReqFileldId').hide();
			$('#selUserCatgId').hide();
			
		}
		else
		{
			substr = "RURAL";
			$('#ruralConstituency').show();
			$('#urbanConstituency').hide();
			$('#selLevelId').hide();
			$('#selMandalId').hide();
			$('#selPanchayatRow').hide();
			$('#selReqFileldId').hide();
			$('#selUserCatgId').hide();
			
		}
		
		getConstituencyEleAndParties();
	}
	
	function getWardsAConstituency()
	{
		var id = $("#constituencyList option:selected").val();
			var jsObj=
					{
						
						id    : id,
						type  : "sub",
						task  : "getAllWards"
					}
					var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
					var url = "getUserCategoeryValues.action?"+rparam;	

				callAjaxToGetData(jsObj,url);
	}
	
	function buildAllWards(result,jsObj)
	{
		$('#specificMandalValue').show();
		var mandalStr = "";
				mandalStr+= '<select id="wardsList" onChange="getSelectedBoothsForAWard();" multiple>';
				for(var i in result)
				{
					if(i > 0)
					{
						mandalStr+= '<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
				}
				mandalStr+= '</select>';
				$('#specificMandalValue').html(mandalStr);
	}
	
	function getSelectedBoothsForAWard()
	{
		var selectedValues = $('#wardsList').val();
		var values = "";
		for(var i in selectedValues)
		{
			values = values+","+selectedValues[i];
		}
		var jsObj=
				{
					
					values    : values.slice(1),
					task      : "getAllBoothsInAWard"
				}
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "getUserCategoeryValues.action?"+rparam;	

		callAjaxToGetData(jsObj,url);
	}
	
	function getUserCategoeryValuesForSelecetdWards()
	{
		var selectedValues = $('#wardsList').val();
		var values = "";
		var constituencyId = $("#constituencyList option:selected").val();
		for(var i in selectedValues)
		{
			values = values+","+selectedValues[i];
		}
		var jsObj=
				{
					constituencyId    :constituencyId,
					values            : values.slice(1),
					status            : "cast",
					nameVal           : "Caste",
					task              : "getUserCatgValuesForWard"
				}
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "getUserCategoeryValues.action?"+rparam;	

		callAjaxToGetData(jsObj,url);
		
	}
	
	function getConstituencyEleAndParties()
	{
		var constituencyId = $("#constituencyList option:selected").val();
			var jsObj= {
				constituencyId : constituencyId,
					task:"getConstiEleAndPartiesForSelected"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getConstituencyElectionYearsAction.action?"+rparam;	
		   
			callAjaxToGetData(jsObj,url);
	}
	
	function buildElectionAndPartyDetails(myResults)
	{
		$('.hero-unit').show();
		$('#voterSelDiv').show();
		if(myResults.electionsInMandal.length == 0)
		$("#mandalElecResultsDiv").css("display","none");
      if(myResults != null && myResults.partiesInMandal != null && myResults.partiesInMandal.length > 0  && myResults.electionsInMandal != null && myResults.electionsInMandal.length > 0){
		   $("#mandalElecResultsDiv").css("display","block");
		  var electionsLength = myResults.electionsInMandal.length;
	     var str='';
		 str+='<table><tr><th align="left">Parties : </th><td>';
		 for(var i in myResults.partiesInMandal){
		  str+='<input id="parties-'+i+'" checked="true" class="partySelForPanc" type="checkbox" value="'+myResults.partiesInMandal[i].id+'" name="parties"><label class="checkboxLabel" for="parties-'+i+'">'+myResults.partiesInMandal[i].name+'</label>';
		 }
		 str+='</td></tr>';
	     
		 str+='<tr><th align="left">Elections  : </th><td>';
		 str+='<table>';
		 for(var i in myResults.electionsInMandal){
			if(i%6==0)
				str+='<tr>';
			if(i == electionsLength-1)
			 {
				str+='<td><input id="elections-'+i+'" checked="true" type="checkbox" class="elecSelForPanc" value="'+myResults.electionsInMandal[i].id+'" name="parties"><label class="checkboxLabel" for="elections-'+i+'">'+myResults.electionsInMandal[i].name+'</label></td>';
			 }
			else{
	 str+='<td><input id="elections-'+i+'"  type="checkbox" class="elecSelForPanc" value="'+myResults.electionsInMandal[i].id+'" name="parties"><label class="checkboxLabel" for="elections-'+i+'">'+myResults.electionsInMandal[i].name+'</label></td>';
		   }
			if((i%6)+1==0)
		   str+='</tr>';
		 }
		 str+='</table>';
		 str+='</td></tr>';
		
		 str+='</table>';
	    
			$("#mandalElecResultsElections").html(str);
		}
	}
	
	function selectAll(){
		var elmts = document.getElementsByName('parties');
		if(elmts.length == 0)
			return;
			for(var i=0; i<elmts.length; i++)
			{
				if(elmts[i].type == "checkbox" && !elmts[i].checked)
					elmts[i].checked = true;
			}
	}

	function deSelectAll(){
	
		var elmts = document.getElementsByName('parties');
		if(elmts.length == 0)
			return;
		for(var i=0; i<elmts.length; i++)
		{
			if(elmts[i].type == "checkbox" && elmts[i].checked)
				elmts[i].checked = false;
		}
	}

	function getCastWiseElectionResults(){

	$('#ajaxImg').show();
     var parties = '';
     var elections = '';
     var locationIds = '';
     var attributeIds = '';	 
    $('.elecSelForPanc').each(function(){
	  if($(this).is(':checked'))
	    elections+=','+$(this).val();
    });
	$('.partySelForPanc').each(function(){
	  if($(this).is(':checked'))
	    parties+=','+$(this).val();
    });
	$('.multipleSelect :selected').each(function(i, selected){ 
	   locationIds+=','+$(selected).val();
    });
	$('#selCategoeryList :selected').each(function(i, selected){ 
	   attributeIds+=','+$(selected).val();
    });
	var type ;
	if(substr == "URBAN")
	{
		var lvlId = $("#urbanIds option:selected").val();
		if(lvlId == 4)
		{
			type = 'ward';
		}
		else
		{
			type = 'booth';
		}
	}
	else if(substr == "RURAL")
	{
		var lvlId = $("#levelId option:selected").val();
		if(lvlId == 1){
			var selectedValues = $('.multipleSelect').val();
			var values = "";
			for(var i in selectedValues)
			{
				values = values+","+selectedValues[i];
			}
			var mandalIds = values.slice(1);
			if(mandalIds.charAt(0) == 1)
			{
				 type = 'ward';
			}
			if(mandalIds.charAt(0) == 2)
			{
				 type = 'mandal';
			}
		}else if(lvlId == 2){
		  type = 'panchayat';
		}else if(lvlId == 3){
		  type = 'booth';
		}
	}
	var jsObj=
			{
		      electionIds:elections.substr(1),
			  partyIds:parties.substr(1),
			  locationIds:locationIds.substr(1),
			  attributeIds:attributeIds.substr(1),
			  constituencyId:$("#constituencyList option:selected").val(),
			  type:type,
			  attributeType:"caste",
			  attrPerc:attrPerc,
			  task:"buildChart"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getAttributeWiseElecResultsAction.action?"+rparam;	
   
		callAjaxToGetData(jsObj,url);
	}
	
	
 function buildChart(buildType){
 
	var result = resultsData;
	$('#noDataAvaliableDiv').hide();
	$("#show_hide_votes").show();
	$('#container').show();
	$('#ajaxImg').hide();
		var linechartDataArr = new Array();
		var results = new Array();
		var results1 = new Array();
		var results2 = new Array();
		var results3 = new Array();
		var data_perc=[];
		var data_vv=[];
		var attr_perc=[];
		var attr_vv=[];
		var data = new Array();
		$("#show_hide_votes").show();
		for(var i in result.locationNames){
		linechartDataArr.push(result.locationNames[i]);
		 }
		results2 = result.locationResults;
		results = result.attributeResults;
		results3 = result.locationPercnts;
		results1 = result.attributePercnts;
		var x = 0;
		for(var i in results){	
			x = x+1;
		   var obj = {};
			obj["name"] = i ;	
			obj['type']='spline';
			obj["data"] = results[i];	 
			attr_vv.push(obj);			
		}
		for(var i in results1){	
			x = x+1;
		   var obj = {};  
			obj["name"] = i ;	
			obj['type']='spline';
			obj["data"] = results1[i];	 
			attr_perc.push(obj);			
		}
		for(var i in results2){	
		   var obj = {};
			obj["name"] = i ;	
			obj['type']='spline';
			obj['yAxis']=1;
			obj["data"] = results2[i];	 
			data_vv.push(obj);			
		}
		for(var i in results3){	
		   var obj = {};  
			obj["name"] = i ;	
			obj['type']='spline';
			obj['yAxis']=1;
			obj["data"] = results3[i];	 
			data_perc.push(obj);			
		}
		if(x > 0)
		{	
			$('#voterSelDiv').show();
			if(buildType == "voterscount")
			{
				$("#votersByCount").attr('checked', 'checked');
				for(var i=0;i<attr_vv.length;i++){
					data.push(attr_vv[i]);
				}		
				for(var i=0;i<data_vv.length;i++){
					data.push(data_vv[i]);	
				}
				buildVoterChart(data,linechartDataArr);
			}
			else
			{
				$("#votersByPerc").attr('checked', 'checked');
				for(var i=0;i<attr_perc.length;i++){
					data.push(attr_perc[i]);			
				}		
				for(var i=0;i<data_perc.length;i++){
					data.push(data_perc[i]);	
				}
				buildVoterPercChart(data,linechartDataArr);
			}
		}
		else
		{
			var str = "";
			str += '<b style="color:red">No Data Avaliable For Your Selection</b>';
			$('#voterSelDiv').hide();
			$('#noDataAvaliableDiv').show();
			$('#container').hide();
			$('#noDataAvaliableDiv').html(str);
			$("#show_hide_votes").hide();
		}
			$('input[name="show_hide_votes"]').click(function(){
			var chart = $('#container').highcharts();
			var series = chart.series;
				if(this.value === "show")
					$.each(series, function(index, series1) {
						series1.show();
					});
				else if (this.value === "hide")
					$.each(series, function(index, series1) {
					   series1.hide();
					});
			});
		
 }
 

	
	$(function() {
	$( "#slider" ).slider({
	value:1,
	min: 0,
	max: 50,
	step: 1,
	slide: function( event, ui ) {
	$( "#amount" ).val( "Percentage of Voters Caste: " + ui.value +" %");
	},
	change: function( event, ui ) {
	$( "#amount" ).val( "Percentage of Voters Caste: " + ui.value +" %");
	attrPerc=ui.value;
	//buildGraphBySlide(casteRange);
	}
	});
	attrPerc=$( "#amount" ).val( "Percentage of Voters Caste: " + $( "#slider" ).slider( "value" ) +" %");
	attrPerc=$( "#slider" ).slider( "value" );
	});
	
	function validationCheck()
	{
		var flag = true;
		var errorMsg = "";
		var constituencyId   = $("#constituencyList option:selected").val();
		var level ;
		var name  ;
		if(substr == "RURAL")
		{
			level            = $("#levelId option:selected").val();
			name             = $("#levelId option:selected").text();
		}
		else
		{
			level            = $("#urbanIds option:selected").val();
			name             = $("#urbanIds option:selected").text();
		}
		var mandalIds        = $(".multipleSelect option:selected").val();
		var castIds          = $("#selCategoeryList option:selected").val();
		var categId          = $("#selectdUserCatgId option:selected").val();
		if(constituencyId == 0)
		{
			errorMsg += 'Please Select Constituency';
			flag = false;
		}
		else if(level == 0 && substr == 'RURAL')
		{
			errorMsg += 'Please Select Level';
			flag = false;
		}
		
		else if(mandalIds == undefined)
		{
			errorMsg += 'Please Select '+name+'';
			flag = false;
		}
		else if(categId == 0)
		{
			errorMsg += 'Please Select Type';
			flag = false;
		}
		else if(castIds == undefined)
		{
			errorMsg += 'Please Select Caste';
			flag = false;
		}
	
	
		
		if(!flag)
		{
			$('#errorMsgDiv').html(errorMsg);
			$('#errorMsgDiv').show().delay("2000").hide('slow');
		}
		else
		{
			getCastWiseElectionResults();
		}
	}
	function buildVoterChart(data,linechartDataArr)
	{
		$('#container').highcharts({
		chart: {
			type: 'line',
			zoomType: 'xy'
		},
		
		title: {
			text: 'Cast Percentage Wise Votes Analysis'
		},
		
		xAxis: [{
			categories:linechartDataArr,
			labels: {
							align:'right',
							style: {
								  cursor: 'pointer',
								  fontSize: '12px',
							},
							rotation: -45,
						} 
		}],
		yAxis: [{ // Primary yAxis
			labels: {
				formatter: function() {
					return this.value +'';
				},
				style: {
					color: '#4572A7'
				}
			},
			title: {
				text: 'Caste',
				style: {
					color: '#4572A7'
				}
			},
			opposite: true

		},{ // ter yAxis
			labels: {
				formatter: function() {
					return this.value +'';
				},
				style: {
					color: '#89A54E'
				}
			},
			title: {
				text: 'Votes',
				style: {
					color: '#89A54E'
				}
			},
			opposite: true

		},],
		
		colors: ['#2f7ed8','#0d233a','#8bbc21','#910000','#1aadce','#492970','#f28f43',  '#77a1e5', '#c42525', '#a6c96a'],
		
		legend: {
				margin:20
			},
		series:data
		
		
	});
	}
	function buildVoterPercChart(data,linechartDataArr)
	{
		$('#container').highcharts({
		chart: {
			type: 'line',
		   // marginRight: 130,
		   // marginBottom:240 ,
		   // height:500,
			zoomType: 'xy'
		},
		
		title: {
			text: 'Cast Percentage Wise Votes Analysis'
		},
		
		xAxis: [{
			categories:linechartDataArr,
			labels: {
							align:'right',
							style: {
								  cursor: 'pointer',
								  fontSize: '12px',
							},
							rotation: -45,
						} 
		}],
		yAxis: [{ // Primary yAxis
			// Secondary yAxis
			gridLineWidth: 0,
			title: {
				text: 'Caste %',
				style: {
					color: '#7C4088'
				}
			},
			labels: {
				formatter: function() {
					return this.value +'%';
				},
				style: {
					color: '#7C4088'
				}
			},
			opposite: true
		},{ // aux yAxis
			gridLineWidth: 0,
			title: {
				text: 'Votes %',
				style: {
					color: '#AA4643'
				}
			},
			labels: {
				formatter: function() {
					return this.value +'%';
				},
				style: {
					color: '#AA4643'
				}
			},
			opposite: true
		}],
		
		colors: ['#2f7ed8','#0d233a','#8bbc21','#910000','#1aadce','#492970','#f28f43',  '#77a1e5', '#c42525', '#a6c96a'],
		
		legend: {
				margin:20
			},
			
		/*tooltip: {
			shared: true
		},*/
		series:data
		
		
	});
	}
 </script>
</head>
<body>
	
  <div  class="widget blue" id="votersBasicInformationDiv" align="center" style="font-family: verdana;font-size: 12px;">
  <div id="errorDiv" style="color:red;display:none"></div>
	<div id="errorMsgDiv"></div>
	<table style="">
	<tr class="tableRow" >
	<div id="constituencyDiv" class="selectDiv">
	<td><b>Constituency </b><font class="requiredFont" style="color:red">*</font></td><td>
	<s:select theme="simple" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name"  class="selectWidth" onChange="getConstituencyType()"/></td>
	</div></tr>
	
	<tr class="tableRow" id="ruralConstituency" style="display:none;">
	<div id="SelectLevelDiv" class="selectDiv">
	<td><b>Level</b><font class="requiredFont" style="color:red">*</font></td>
	<td><select id="levelId" onChange="getRespectiveValues('');" >
	<option value="0">Select Level</option>
	<option value="1">Mandal</option>
	<option value="2">Panchayat</option>
	<option value="3">Booth</option>
	</select></td>
	</div>
	</tr>
	
	<tr class="tableRow" id="urbanConstituency" style="display:none;">
	<div id="SelectLevelDiv2" class="selectDiv">
	<td><b>Level</b><font class="requiredFont" style="color:red">*</font></td>
	<td><select id="urbanIds" onChange="getRespectiveValues('urban');" >
	<option value="0">Select Level</option>
	<option value="4">Ward</option>
	<option value="3">Booth</option>
	</select></td>
	</div>
	</tr>
	
	<tr class="tableRow" id="selLevelId">
	<td><div id="multiSelectLevelsHeading" ></div></td>
	<td><div id="multiSelectLevelsValues" ></div></td>
	</tr>
	
	<tr class="tableRow" id="selMandalId">
	<td><div id="specificMandalName" style="display:none;"></div></td>
	<td><div id="specificMandalValue" style="display:none;"></div></td>
	</tr>
		
	<tr class="tableRow" id="selPanchayatRow">
	<td><div id="specificPanchayatName" style="display:none;"></div></td>
	<td><div id="specificPanchayatValue" style="display:none;"></div></td>
	</tr>
	
	<tr id="selReqFileldId" class="tableRow" style="display:none;">
	<td><div id="requiredFieldsToCheckName" ></div></td>
	<td><div id="requiredFieldsToCheckValue" ></div></td>
	</tr>
	
	<tr id="selUserCatgId" class="tableRow">
	<td><div id="userSelCatgListHeading" ></div></td>
	<td><div id="userSelCatgListValue" ></div></td>
	</tr>
	
	</table>
	
	<div id="rangeSliderDiv" style="width:500px;margin-left:auto;margin-right:auto;border:1px solid #ccc;padding:5px 20px;margin-top:50px;display:none;" >
			<h5 style="text-align:center;">Drag Slider for Building Chart Based on Voters Caste Percentage </h5>
			<div id="slider" class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" aria-disabled="false"><a href="#" class="ui-slider-handle ui-state-default ui-corner-all" style="left: 0%;"></a>
			</div>
				<p style="padding-bottom:2px;">
					<input type="text" id="amount" style="border: 0; color: #f6931f; font-weight: bold;width: 255px;" />
				</p>
		</div>
	<div class="hero-unit" style="display:none;">
	<div id="mandalElecResultsElections"></div>
	<div id="selectionDiv" align="left" style="margin-left: 138px;" >
    <input type="button" id="selectAll"  class="btn" onclick="selectAll()" name="selection" value="Select All"><span></span>
	<input type="button" id="deSelectAll" class="btn" onclick="deSelectAll()" name="selection" value="Unselect All"><span></span>
	</div>
	<div id="submitbtn"><input type="button" class="btn" value="Submit" onclick="validationCheck();" style="margin-top: -25px; margin-left: -179px;"></input><span><img alt="Processing Image" src="./images/icons/search.gif" id="ajaxImg" style="float: right; margin-right: 473px; margin-top: -21px;display:none;"></span></div>
	</div>
	<div id="voterSelDiv"  style="font-family: verdana; font-size: 16px; float: left; margin-left: 162px; font-weight: bolder;display:none;">
	<input type="radio" id="votersByCount" value="voter" checked="true" name="voter" onClick="buildChart('voterscount')">Voters Wise</input>
	<input type="radio" id="votersByPerc" value="voter %" name="voter" onClick="buildChart('votersperc')">Percentage Wise</input>
	</div>
	<div id="noDataAvaliableDiv"></div>
  </div>
  <div id="container"> </div>
  <div style="margin-left:auto;margin-right:auto;width:200px;margin-bottom:10px;display:none;" id="show_hide_votes">
		<span class="label label-info" style="padding:5px;margin:5px;">Show All <input type="radio" name="show_hide_votes" value="show" checked="checked"></span>
		<span class="label label-info" style="padding:5px;margin:5px;">Hide All <input type="radio" name="show_hide_votes" value="hide"></span>
	</div>
	
  </body>
</html>