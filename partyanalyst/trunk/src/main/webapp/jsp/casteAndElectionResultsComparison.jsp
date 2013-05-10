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
   .tableRow
   {
		height: 56px;
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
		
		$('#multiSelectLevelsHeading').show();
		$('#multiSelectLevelsValues').show();
		$('#specificMandalName').hide();
		$('#specificMandalValue').hide();
		$('#fourthRow').hide();
		$('#specificPanchayatName').hide();
		$('#specificPanchayatValue').hide();
		$('#fifthRow').hide();
		$('#userSelCatgListHeading').hide();
		$('#userSelCatgListValue').hide();
		$('#seventhRow').hide();

		var name = "";
		
		name += '<b>Mandals </b><font class="requiredFont" style="color:red">*</font>';
		$('#multiSelectLevelsHeading').html(name);
		
		var value = "";
		value += '<select name="values" id="multipleSelect" onChange="getUserCategoersFoeSelect(\'\');" class="multipleSelect" multiple >';
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
		value +='<select id="selectdUserCatgId" onChange="checkAndGetData(\'cast\',\'Cast\');"><option value="0">Select Type</ortion>';
		value +='<option value="1">Caste</option>';
		value +='</select>';
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
	   function checkAndGetData(type,nameVal)
	   {
		
			var selectedValues = $('.multipleSelect').val();
			var values = "";
			var checkVal = "";
			for(var i in selectedValues)
			{
				checkVal = values+","+selectedValues[i];
				values = values+","+selectedValues[i];
			}
			var name = $("#levelId option:selected").text();
			if(name == 'Mandal')
			{
				if(checkVal.charAt(1) == 1)
				{
					var constituencyId = $("#constituencyList option:selected").val();
					var jsObj=
						{
							constituencyId   : constituencyId,
							status           : type,
							selectedValues   : checkVal.slice(1),
							type             : "muncipality",
							nameVal          : nameVal,
							task             : "getUserCategoeryValuesForWard"
						}
						var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
						var url = "getUserCategoeryValues.action?"+rparam;	

					callAjaxToGetData(jsObj,url);
				} 
				else
				{
				
					var name = $("#levelId option:selected").text();
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
				var name = $("#levelId option:selected").text();
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
	   
	   function buildUserCategoerySelectedValues(result,jsObj)
	   {
			$('#userSelCatgListHeading').show();
			$('#userSelCatgListValue').show();
			$('#seventhRow').show();
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
	  function unselectAll(){
		  $('.attributeTypeClassIni').each(function() {
            $(this).removeAttr('checked');
          });
	  }
	   
	function getRespectiveSelection()
	{
		var selecetd =  $("#levelListId option:selected").val();
		getRespectiveFields(selecetd);
	}
	var type;
	function getRespectiveValues(constituencyType)
	{
		$('#requiredFieldsToCheckName').hide();
		$('#requiredFieldsToCheckValue').hide();
		var id = $("#constituencyList option:selected").val();
		if(constituencyType == 'urban')
		{
			type = $("#urbanIds option:selected").text();
		}
		else
		{
			type = $("#levelId option:selected").text();
		}
		if(type == 'Mandal')
		{
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
			name += '<b>Booths </b><font class="requiredFont" style="color:red">*</font> ';
			$('#multiSelectLevelsHeading').html(name);
			mandal += '<input type="radio" onClick="getWardsAConstituency();" class="mandalCheck"></input>';
			mandal +='<b class="space">Specific Ward</b>';
			$('#specificMandalName').html(mandal);
		}
		else
		{
			$('#multiSelectLevelsHeading').show();
			$('#multiSelectLevelsValues').show();
			$('#specificMandalName').hide();
			$('#specificMandalValue').hide();
			$('#fourthRow').hide();
			$('#thirdRow').show();
			$('#specificPanchayatName').hide();
			$('#specificPanchayatValue').hide();
			$('#fifthRow').hide();
			$('#wardsRow').hide();
			$('#userSelCatgListHeading').hide();
			$('#userSelCatgListValue').hide();
			$('#seventhRow').hide();

			var name = "";
			var mandal ="";
			var panchayat = "";
			if(jsObj.type == "Mandal")
			{
				name += '<b>Mandals </b><font class="requiredFont" style="color:red">*</font>';
			}
			else if(jsObj.type == "Panchayat")
			{
				name += '<b>Panchayats </b><font class="requiredFont" style="color:red">*</font> ';
				
				mandal += '<input type="radio" onClick="getAllMandalsInAConstituency();" class="mandalCheck"></input>';
				mandal +='<b>Specific Mandal</b>';
			}
			else if(jsObj.type == "Booth")
			{
				name += '<b>Booths </b><font class="requiredFont" style="color:red">*</font> ';
				
				mandal += '<input type="radio" onClick="getAllMandalsInAConstituency();" class="mandalCheck"></input>';
				mandal +='<b class="space">Specific Mandal</b>';
				panchayat += '<input type="radio" onClick="getAllPancayatsInAConstituency();" class="panchayatCkeck"></input>';
				panchayat +='<b>Specific Panchayat</b>';
			}
			$('#multiSelectLevelsHeading').html(name);
			
			if(jsObj.type == "Booth")
			{
				$('#specificMandalName').show();
				$('#specificMandalValue').show();
				$('#fourthRow').show();
				$('#specificPanchayatName').show();
				$('#fifthRow').show();
				$('#specificPanchayatValue').show();
				$('#specificMandalName').html(mandal);
				$('#specificPanchayatName').html(panchayat);
			}
			else if(jsObj.type == "Panchayat")
			{
				$('#specificMandalName').show();
				$('#specificMandalValue').show();
				$('#fourthRow').show();
				$('#specificMandalName').html(mandal);
			}
		}
		var value = "";
		value += '<select name="values" id="multipleSelect" onChange="getUserCategoersFoeSelect(\'\');" class="multipleSelect" multiple >';
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
		$('#requiredFieldsToCheckName').hide();
		$('#requiredFieldsToCheckValue').hide();
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
		$('#requiredFieldsToCheckName').hide();
		$('#requiredFieldsToCheckValue').hide();
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
				$('#specificWardValue').show();
				$('#specificWardName').show();
				$('#wardsRow').show();
				$('#thirdRow').hide();
				$('#fourthRow').hide();
				$('#fifthRow').hide();
				var name="";
				name += '<b>Wards</b><font class="requiredFont" style="color:red">*</font>';
				$('#specificWardName').html(name);
				var value = "";
				value+= '<select id="wardsList"  onChange="getUserCategoersFoeSelect(\'ward\');" class="multipleSelect" multiple>';
				for(var i in result)
				{
					value+= '<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				value+= '</select>';
				$('#specificWardValue').html(value);
			
			}
			else
			{
				$('#specificPanchayatValue').hide();
				$('#specificMandalValue').show();
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
		$('#specificMandalValue').hide();
		$('#specificPanchayatValue').show();
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
	
	function getSelectedPanchayatsOrBooths(type)
	{
		var selectedValues ;
		var values = "";
		var checkVal = "";
		var level = $('#levelId').val();
		if(type == "mandal")
		{
		
			selectedValues	= $('#maldalsList').val();
		}
		else if(type == "panchayat")
		{
			selectedValues	= $('#panchayatsList').val();
		}
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
					type   : type,
					level  : level,
					values : values.slice(1),
					task   : "getSelectedMandalOrPanchayatData"
				}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getReportLevelDetails.action?"+rparam;	

			callAjaxToGetData(jsObj,url);
		}
	}
	var mainStr = "";
	function buildPanchayatsOrMandalsList(result,jsObj,type)
	{
		$('#requiredFieldsToCheckName').show();
		$('#requiredFieldsToCheckValue').show();
		$('#requiredFieldsToCheckName').hide();
		$('#requiredFieldsToCheckValue').hide();
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
				value += '<select id="selMandalList" onChange="getUserCategoersFoeSelect(\'\');" class="multipleSelect" multiple>';
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
		}
	}
	
	function getUserCategoersFoeSelect(type)
	{
		if(type == "ward")
		{
			$('#wardsRow').show();
			getUserCategoeryValuesForSelecetdWards();
		}
		else
		{
			$('#wardsRow').hide();
		}
		var value = $("#selectdUserCatgId option:selected").val();
		if(value > 0)
		{  
			checkAndGetData('cast','Cast');
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
		if(result[0].name == "URBAN")
		{
			$('#SelectLevelDiv2').show();
			$('#urbanConstituency').show();
			$('#secondRow').hide();
		}
		else
		{
			$('#SelectLevelDiv').show();
			$('#urbanConstituency').hide();
			$('#secondRow').show();
		}
		
		
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
					
					values            : values.slice(1),
					constituencyId    :constituencyId,
					status            : "cast",
					task              : "getUserCatgValuesForWard"
				}
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "getUserCategoeryValues.action?"+rparam;	

		callAjaxToGetData(jsObj,url);
		
	}
 </script>
</head>
<body>
	
  <div  class="widget blue" id="votersBasicInformationDiv" align="center" style="font-family: verdana;font-size: 12px;">
  <div id="errorDiv" style="color:red;display:none"></div>
	
	<table style="width: 328px;">
	<tr class="tableRow" id="firstRow">
	<div id="constituencyDiv" class="selectDiv">
	<td><b>Constituency </b><font class="requiredFont" style="color:red">*</font></td><td>
	<s:select theme="simple" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name"  class="selectWidth" onChange="getConstituencyType()"/></td>
	</div></tr>
	
	<tr class="tableRow" id="secondRow" style="display:none;">
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
	
	<tr class="tableRow" id="thirdRow">
	<td><div id="multiSelectLevelsHeading" style="display:none;"></div></td>
	<td><div id="multiSelectLevelsValues" style="display:none;"></div></td>
	</tr>
	
	<tr class="tableRow" id="fourthRow">
	<td><div id="specificMandalName" style="display:none;"></div></td>
	<td><div id="specificMandalValue" style="display:none;"></div></td>
	</tr>
	
	<tr class="tableRow" id="wardsRow">
	<td><div id="specificWardName" style="display:none;"></div></td>
	<td><div id="specificWardValue" style="display:none;"></div></td>
	</tr>
	
	<tr class="tableRow" id="fifthRow">
	<td><div id="specificPanchayatName" style="display:none;"></div></td>
	<td><div id="specificPanchayatValue" style="display:none;"></div></td>
	</tr>
	
	<tr id="sixthRow" class="tableRow">
	<td><div id="requiredFieldsToCheckName" style="display:none;"></div></td>
	<td><div id="requiredFieldsToCheckValue" style="display:none;"></div></td>
	</tr>
	
	<tr id="seventhRow" class="tableRow">
	<td><div id="userSelCatgListHeading" style="display:none;"></div></td>
	<td><div id="userSelCatgListValue" style="display:none;"></div></td>
	</tr>
	
	</table>
  </div>
  
  </body>
</html>