<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet"/>
<title>Partial Booth Panchayat</title>
</head>
<script>

function getPublicationDate(type)
{
	var value = "";
	if(type == 'add')
	{
		value =  $("#constituencyList option:selected").val();
	}
	else
	{
		value =  $("#constituencyListForEdit option:selected").val();
	}
	var jsObj=
	{
		selected : value,
		type     : type,
		task     : "getPublicationDate"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+rparam;	
	callAjaxToGetData(jsObj,url);
}


function callAjaxToGetData(jsObj,url)
{
	 var myResults;
	 var callback = {			
	   success : function( o ) {
		try {												
				myResults = YAHOO.lang.JSON.parse(o.responseText);	
				if(jsObj.task == "getPublicationDate")
				{
					if(jsObj.type == 'add')
					{
						buildSelectBoxValues(myResults,'publicationDate');
					}
					else
					{
						buildSelectBoxValues(myResults,'publicationDateForEdit');
					}
					
				}
				
				else if(jsObj.task == "subRegionsInConstituency")
				{
					if(jsObj.type == 'add')
					{
						buildSelectBoxValues(myResults,'mandal');
					}
					else
					{
						buildSelectBoxValues(myResults,'mandalForEdit');
					}
					
				}
				
				else if(jsObj.task == "getPanchayat" && jsObj.name == "add")
				{
					buildSelectBoxValues(myResults,'partialPanchayat');
					buildSelectBoxValues(myResults,'panchayat');
				}
				
				else if(jsObj.task == "getPanchayat" && jsObj.name == "edit")
				{
					//buildSelectBoxValues(myResults,'panchayatEdit');
					buildSelectBoxValuesForPanchaytEdit(myResults,'partialPanchayatEdit',jsObj.partialPanchayatId);
				}
				
				else if(jsObj.task == "getSelectedMandalOrPanchayatData" && jsObj.name == "add")
				{
					buildSelectBoxValues(myResults,'booth');
				}
				
				else if(jsObj.task == "getSelectedMandalOrPanchayatData" && jsObj.name == "edit")
				{
					buildSelectBoxValues(myResults,'BoothEdit');
				}
				
				else if(jsObj.task == "savePartianBoothDetails")
				{
					statusMsgForSaveing(myResults);
				}
				
				else if(jsObj.task == "getPartianBoothDetails")
				{
					buildPartialBoothDetails(myResults);
				}
				
				else if(jsObj.task == "deleteSelectedPartialBooth")
				{
					buildDeleteStatus(myResults,jsObj.divId);
				}
				
				else if(jsObj.task == "editSelectedPartialBooth")
				{
					buildEditPartialBoothDetails(myResults);
				}
				
				else if(jsObj.task == "updatePartianBoothDetails")
				{
					buildUdatePartianBoothDetails(myResults);
				}
				
				else if(jsObj.task == "getHamlets" && jsObj.name == "add" )
				{
					buildSelectBoxValues(myResults,"hamlet");
				}
				else if(jsObj.task == "getHamlets" && jsObj.name == "edit" )
				{
					buildSelectBoxValuesForPanchaytEdit(myResults,"hamletEdit",jsObj.hamletId);
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
function buildSelectBoxValues(result,divId)
{
	if(result != null)
	{
		$('#'+divId+' option').remove();
		for(var i in result)
		{
			$('#'+divId+'').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
	}
}

function buildSelectBoxValuesForPanchaytEdit(result,divId,value)
{
	if(result != null)
	{
		$('#'+divId+' option').remove();
		for(var i in result)
		{
			$('#'+divId+'').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
	}
	$('#'+divId+'').val(value);
}

function getMandals(type)
{
	var value = "";
	if(type == 'add')
	{
		value =  $("#constituencyList option:selected").val();
	}
	else
	{
		value =  $("#constituencyListForEdit option:selected").val();
	}
	var jsObj=
	{
		id              : value,
		taskType        : "newProblemPost",
		selectElementId : "mandalField",
		address         : "currentAdd",
		areaType        : "RURAL",
		constId         : null,
		isParliament    : null,
		type            : type,
		task            : "subRegionsInConstituency"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "locationsHierarchiesAjaxAction.action?"+rparam;	
	callAjaxToGetData(jsObj,url);
}

function getPanchayts()
{
	var jsObj=
	{
		checkedele         : "panchayat",
		constituencyId     : $("#constituencyList option:selected").val(),
		flag               : -1,
		publicationValue   : $("#publicationDate option:selected").val(),
		selected           : $("#mandal option:selected").val().slice(1),
		selectedEle        : "panchayatField",
		type               : "mandal",
		name               : "add",
		task               : "getPanchayat"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPanchayatByConstituencyAction.action?"+rparam;	
	callAjaxToGetData(jsObj,url);
	
}

function getHamlets()
{
	var jsObj=
	{
		panchaytId     : $('#partialPanchayat option:selected').val(),
		name           : "add",
		task           : "getHamlets"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getHamletsForPanchayatAction.action?"+rparam;	
	callAjaxToGetData(jsObj,url);
}

function getHamletsForEdit(panchayatId,hamletId)
{
	var jsObj=
	{
		panchaytId     : panchayatId,
		hamletId       : hamletId,
		name           : "edit",
		task           : "getHamlets"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getHamletsForPanchayatAction.action?"+rparam;	
	callAjaxToGetData(jsObj,url);
}
function getPanchayatsForEdit(constituencyId,publicationId,mandalId,partialPanchayatId)
{
	var jsObj=
	{
		checkedele         : "panchayat",
		constituencyId     : constituencyId,
		flag               : -1,
		publicationValue   : publicationId,
		selected           : mandalId.slice(1),
		selectedEle        : "panchayatField",
		type               : "mandal",
		name               : "edit",
		partialPanchayatId : partialPanchayatId,
		task               : "getPanchayat"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPanchayatByConstituencyAction.action?"+rparam;	
	callAjaxToGetData(jsObj,url);
}
function getBoothsForEdit(constituencyId,publicationId,panchayatId)
{
	var jsObj=
	{
		type               : "panchayat",
		constituencyId     : constituencyId,
		level              : 3,
		publicationId      : publicationId,
		values             : panchayatId,
		name               : "edit",
		task               : "getSelectedMandalOrPanchayatData"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getReportLevelDetails.action?"+rparam;	
	callAjaxToGetData(jsObj,url);
}
function getBooths()
{
	var jsObj=
	{
		type               : "panchayat",
		constituencyId     : $("#constituencyList option:selected").val(),
		level              : 3,
		publicationId      : $("#publicationDate option:selected").val(),
		values             : $("#panchayat option:selected").val(),
		name               : "add",
		task               : "getSelectedMandalOrPanchayatData"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getReportLevelDetails.action?"+rparam;	
	callAjaxToGetData(jsObj,url);
}

function saveDetails()
{
	var hamletIds = new Array();
	$('#hamlet :selected').each(function(i, selected){ 
	  hamletIds.push($(selected).val()); 
	});
	var jsObj=
	{
		panchayatId   : $("#panchayat option:selected").val(),
		boothId       : $("#booth option:selected").val(),
		description   : $('#descriptionId').val(),
		pdescription  : $('#PartialDescription').val(), 
		ppanchayatId  : $("#partialPanchayat option:selected").val(),
		hamletsIds    : hamletIds,
		task          : "savePartianBoothDetails"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "savePartialBoothPanchayatAction.action?"+rparam;	
	callAjaxToGetData(jsObj,url);
}
function getSelectedDetails()
{
	$('#deleteStatusMsg').html('');
	var jsObj=
	{
		mandalId       : $("#mandalForEdit option:selected").val().slice(1),
		publicationId  : $("#publicationDateForEdit option:selected").val(),
		task           : "getPartianBoothDetails"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartialBoothPanchayatAction.action?"+rparam;	
	callAjaxToGetData(jsObj,url);
}
function statusMsgForSaveing(result)
{
	if(result.resultCode == 0)
	{
		$('#successMsg').html(''+result.exceptionMsg+'');
		$('#errorMsg').html('');
	}
	
	if(result.resultCode == 1)
	{
		var boothNo = $('#booth option:selected').text();
		var panchayat = $('#panchayat option:selected').text();
		var partalpanchayat = $('#partialPanchayat option:selected').text();
		var publicationDate = $('#publicationDate option:selected').text();
	$('#errorMsg').html(' '+boothNo+'  '+result.exceptionMsg+' FOR '+panchayat+ ' AND '+partalpanchayat+ ' PANCHAYAT FOR THE PUBLICATION DATE OF '+publicationDate+'');
	$('#successMsg').html('');
	}
	
}

function buildPartialBoothDetails(result)
{
	if(result != null && result.length > 0)
	{
		var str = '';
		str += '<table class="partialBoothsList table table-bordered table-hover">';
		str += '<tr>';
		str += '<th>Booth</th>';
		str += '<th>Panchayat</th>';
		str += '<th>Hamlet</th>';
		str += '<th>Description</th>';
		str += '<th>Actions</th>'
		str += '</tr>';
		for(var i in result)
		{
			str += '<tr id="'+i+'">';
			str += '<td>'+result[i].boothName+'</td>';
			str += '<td>'+result[i].panchayatName+'</td>';
			str += '<td>'+result[i].hamletName+'</td>';
			str += '<td>'+result[i].description+'</td>';
			str += '<td><a onClick="editSelectedPartialBooth('+result[i].partialBoothPanchayatId+');"><img src="images/icons/edit.png" style="border: 0px none; text-decoration: none; margin-left: 19px;" title="edit"></a><a onClick="deleteSelectedPartialBooth('+result[i].partialBoothPanchayatId+','+result[i].boothId+','+i+');"><img src="images/icons/delete.png" style="border: 0px none; text-decoration: none; margin-left: 19px;" title="delete"></a></td>';
			str += '</tr>';
			
		}
		str += '<table>';
		$('#tableDiv').html(str);
	}
	else
	{
		$('#tableDiv').html('<b style="color: red; float: left; margin-top: -39px; margin-left: 326px;">No records found</b>');
	}
}

function editSelectedPartialBooth(id)
{
	var jsObj=
	{
		id       : id,
		task     : "editSelectedPartialBooth"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "editOrDeleteSelectedPartialBoothAction.action?"+rparam;	
	callAjaxToGetData(jsObj,url);
}

function deleteSelectedPartialBooth(id,boothId,divId)
{
	var jsObj=
	{
		id       : id,
		boothId  : boothId,
		divId    : divId, 
		task     : "deleteSelectedPartialBooth"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "editOrDeleteSelectedPartialBoothAction.action?"+rparam;	
	callAjaxToGetData(jsObj,url);
}

function buildDeleteStatus(result,divId)
{
	//$('#deleteStatusMsg').html(''+result.message+'');
	alert(''+result.message+'');
	$('#'+divId+'').remove();
}

function buildEditPartialBoothDetails(result)
{
var panchayatId;
	if(result != null && result.length > 0)
	{
		var constituencyId = $('#constituencyListForEdit option:selected').val();
		var constituencyName = $('#constituencyListForEdit option:selected').text();
		var publicationId = $('#publicationDateForEdit option:selected').val();
		var publicationDate = $('#publicationDateForEdit option:selected').text();
		var mandalId = $('#mandalForEdit option:selected').val();
		var mandalName = $('#mandalForEdit option:selected').text();
		panchayatId = result[0].partialpanchayatId;
		
		//getBoothsForEdit(constituencyId,publicationId,panchayatId);
		var str = '';
		str += '<div id="updateStatusDiv"></div>'
		str += '<div><b>Constituency : </b> <span  style="margin-left: 81px;">'+constituencyName+' </span></div></br>';
		str += '<div><b>Publication Date : </b> <span  style="margin-left: 51px;">'+publicationDate+' </span></div></br>';
		str += '<div><b>Mandal : </b> <span  style="margin-left: 126px;">'+mandalName+' </span></div></br>';
		
		str += '<div><b>Panchayat : </b> <span  style="margin-left: 100px;">'+result[0].partialPanchayatName+' </span></div></br>';
		
		str += '<div><b>Booth : </b> <span  style="margin-left: 138px;">'+result[0].boothName+' </span></div></br>';
		//str += '<div><b>Panchayat: </b> <select id="panchayatEdit"  style="margin-left: 100px;"></select></div>';
		//str += '<div><b>Booth: </b> <select id="BoothEdit"  style="margin-left: 138px;"></select></div>';
		str += '<div><b>Description : </b><textarea    id="descriptionEdit" style="margin-left: 89px;">'+result[0].description+'</textarea></div>'
		str += '<div><b>Panchayat: </b> <select id="partialPanchayatEdit"  style="margin-left: 100px;"></select></div>';
		str += '<div><b>Hamlet: </b> <select id="hamletEdit"  style="margin-left: 125px;"></select></div>';
		str += '<div><b>Description : </b><textarea    id="PartialDescriptionEdit" style="margin-left: 89px;">'+result[0].partialDescription+'</textarea></div>';
		str += '<div id="updateButtonDiv" style="margin-left: 170px; margin-top: 12px; float: left;"><input type="submit" value="Update" class="btn btn-success" onClick="updateDetails('+result[0].partialBoothPanchayatId+','+panchayatId+','+result[0].boothId+','+result[0].hamletId+');"></input></div>';
		$('#editDetailsDiv').html(str);
		getPanchayatsForEdit(constituencyId,publicationId,mandalId,result[0].panchayatId);
		
		getHamletsForEdit(result[0].panchayatId,result[0].hamletId);
		//alert(result[0].partialpanchayatId);
		//$('#partialPanchayatEdit').val(result[0].partialpanchayatId);
		
		
		$('#editDetailsDiv').dialog({
		title : "Update Details...",
		width : 500,
		height : 550
		});
		
		
	}
}

function updateDetails(id,panchayatId,boothId,hamletId)
{
	var jsObj=
	{
		id            : id,
		panchayatId   : panchayatId,
		boothId       : boothId,
		description   : $('#descriptionEdit').val(),
		pdescription  : $('#PartialDescriptionEdit').val(), 
		ppanchayatId  : $("#partialPanchayatEdit option:selected").val(),
		hamletId  	  : $("#hamletEdit option:selected").val(),
		task          : "updatePartianBoothDetails"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updatePartialBoothPanchayatAction.action?"+rparam;	
	callAjaxToGetData(jsObj,url);      
}

function buildUdatePartianBoothDetails(result)
{
	if(result.resultCode == 0)
	{
		$('#updateStatusDiv').html('<b style="color:green">'+result.message+'</b>');
	}
	else
	{
		$('#updateStatusDiv').html('<b style="color:red">'+result.message+'</b>');
	}
	
}

function validateTheFields()
{
	$('#errorMsg').html('');
	var flag = true;
	var boothId       = $('#booth option:selected').val();
	var description   = $('#descriptionId').val();
	var panchayatId   = $('#partialPanchayat option:selected').val();
	var pdescription  = $('#PartialDescription').val();
	var phamlet = $('#hamlet option:selected').val();
	var str = '';
	if(boothId == 0)
	{
		flag = false;
		str += '<b style="color:red">Please select the booth</b></br>';
	}
	if(description == '')
	{
		flag = false;
		str += '<b style="color:red">Please enter the description</b></br>';
	}
	if(panchayatId == 0 )
	{
		flag = false;
		str += '<b style="color:red">Please select the Panchayat</b></br>';
	}		
	if(phamlet == undefined)
	{
		flag = false;
		str += '<b style="color:red">Please select the Hamlet</b></br>';
	}
	if(pdescription == '')
	{
		flag = false;
		str += '<b style="color:red">Please enter the description</b></br>';
	}
	
	if(flag)
	{
		saveDetails();
	}
	else
	{
		$("#errorMsg").show();
		$('#errorMsg').html(str);
		$("#errorMsg").delay("2000").hide("slow");
	}
	
}
</script>
<body>
<div id="mainDiv">
	<div>
	<div id="headingDiv" align="center"  style="margin-top: 25px; background-color: #06ABEA; height: 31px; color: white; font-family: verdana; padding-top: 10px; font-size: 21px; border-radius: 4px 4px 4px 4px;">Save Partial Booth Panchayat Details</div>
	<div id="bodyDiv" style="float: left; margin-left: 258px; margin-top: 18px;">
	<div id="successMsg" style="color: green; font-family: verdana; font-size: 14px; font-weight: bold;"></div>
	<div id="errorMsg" style="color: red; font-family: verdana; font-size: 12px; font-weight: bold;"></div>
	<div><b>Constituency : </b><s:select theme="simple" cssClass="selectWidth" label="Select Your State" name="userAccessConstituencyList" id="constituencyList" list="userAccessConstituencyList" listKey="id" listValue="name"  class="selectWidth" onChange="getPublicationDate('add');" style="margin-left: 100px;"/></div>
	<div><b>Publication Date : </b><select id="publicationDate" name="publicationDate"style="margin-left: 79px;" onChange="getMandals('add');"><option value="0">Publication Date</option></select></div>
	<div><b>Mandal : </b><select id="mandal" name="mandal" style="margin-left: 141px;"onChange="getPanchayts();"><option value="0">Select Mandal</option></select></div>
	<div><b>Panchayat : </b><select id="panchayat" name="panchayatsList" style="margin-left: 121px;" onChange="getBooths();"><option value="0">Select Panchayat</option></select></div>
	<div><b>Booth : </b><b style="color:red">*</b><select id="booth" name="boothsList" style="margin-left: 145px;"><option value="0">Select Booth</option></select></div>
	<div><b>Description : </b><b style="color:red">*</b><textarea name="description" id="descriptionId" style="margin-left: 105px;"></textarea></div>
	<div><b>Panchayat : </b><b style="color:red">*</b><select id="partialPanchayat" name="partialPanchayatsList" style="margin-left: 116px;" onChange="getHamlets();"><option value="0">Select Panchayat</option></select></div>
	<div><b>Hamlet : </b><b style="color:red">*</b><select id="hamlet" name="hamletList" style="margin-left: 140px;" multiple ><option value="0">Select Hamlet</option></select></div>
	<!--<div><b>Booth : </b><select id="PartialBooth" name="PartialBooth" style="margin-left: 145px;"><option value="0">Select Booth</option></select></div>-->
	<div><b>Description : </b><b style="color:red">*</b><textarea name="PartialDescription" id="PartialDescription" style="margin-left: 106px;"></textarea></div>
	<div id="submitButton" style="float: left; margin-left: 200px; margin-bottom: 10px;"><input type="submit" value="Submit" class="btn btn-success" onClick="validateTheFields();"></input></div>
	</div>
	</div>
	
	<div>
	<div id="headingDivForEdit" align="center" style="margin-top: 25px; background-color: #06ABEA; height: 31px; color: white; font-family: verdana; padding-top: 10px; font-size: 21px; border-radius: 4px 4px 4px 4px; float: left; position: static;width: 1000px;">Edit Partial Booth Panchayat Details</div>
	<div id="bodyDivForEdit" style="float: left; margin-left: 258px; margin-top: 18px;">
	<div><b>Constituency : </b><s:select theme="simple" cssClass="selectWidth" label="Select Your State" name="userAccessConstituencyList" id="constituencyListForEdit" list="userAccessConstituencyList" listKey="id" listValue="name"  class="selectWidth" onChange="getPublicationDate('edit');" style="margin-left: 100px;"/></div>
	<div><b>Publication Date : </b><select id="publicationDateForEdit" name="publicationDate"style="margin-left: 79px;" onChange="getMandals('edit');"><option value="0">Publication Date</option></select></div>
	<div><b>Mandal : </b><select id="mandalForEdit" name="mandalForEdit" style="margin-left: 141px;"><option value="0">Select Mandal</option></select></div>
	<div id="submitButtonForEdit" style="float: left; margin-left: 200px; margin-bottom: 10px;"><input type="submit" value="Get" class="btn btn-success" onClick="getSelectedDetails();"></input></div>
	<div id="tableDiv"  style="float: left; margin-left: -170px; margin-top: 43px; width: 891px;">
	<!--<div id="deleteStatusMsg" style="color: red; float: left; margin-top: -39px; margin-left: 184px;"></div>-->
	</div>	
	</div>
	</div>
	<div id="editDetailsDiv"></div>
</div>
</body>
</html>