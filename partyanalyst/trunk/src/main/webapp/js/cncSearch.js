
var stateId = '';
var searchType = '';
var constituencyType = '';


function buildAutoSuggest()
{
	//var datastore= value;

	var selectEl = document.getElementById("stateSelect");
	stateId =  selectEl.options[selectEl.selectedIndex].value;

	var searchNameEls = document.getElementsByName("searchName");
	var constTypeEls = document.getElementsByName("constType");
	
	for(var i = 0; i< searchNameEls.length; i++)
	{
		if(searchNameEls[i].checked)
			searchType = searchNameEls[i].value;
	}
	for(var j = 0; j< constTypeEls.length; j++)
	{
		if(constTypeEls[j].checked)
			constituencyType = constTypeEls[j].value;
	}
	
	
	var imgElmt=document.getElementById("ajaxLoaderimg");
	imgElmt.style.display="none";

	var txtDivElmt=document.getElementById("textFldDiv");
	if(navigator.appName=="Microsoft Internet Explorer")
	{		
		var txtstr='<input id="myInput" type="text" size="25" name="searchText" onkeyup="ajax_showOptions(this,\'getCountriesByLetters\',event)" style="position:absolute;"/>';
		txtstr += '<input type="hidden" id="myInput_hidden" name="myInput_ID">';
		txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;top:22px;width:215px;max-height:250px;"></div>';
		txtDivElmt.innerHTML=txtstr;
	}
	else
	{
		var txtstr='<input id="myInput" type="text" size="40" name="searchText" onkeyup="ajax_showOptions(this,\'getCountriesByLetters\',event)" style="padding: 3px 0px 2px 0px; font-size: 10px; font-family: arial;"/>';
		txtstr += '<input type="hidden" id="myInput_hidden" name="myInput_ID">';
		txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;top:22px;width:215px;max-height:250px;"></div>';
		txtDivElmt.innerHTML=txtstr;
	}

}
function getXmlHttpObj()
{	
	var xmlHttp;
	// Firefox, Opera 8.0+, Safari
	try
	{	
		xmlHttp=new XMLHttpRequest();
	}
	// Internet Explorer
	catch (e)
	{
		try
		{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
			
		}
		catch (e)
		{
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
				
			}
			catch (e){return null;}
		}
	}
	return xmlHttp;	
}
 function getParser(stateId)
 {	
	 ajax_list_cachedLists = new Array();
	 
	if(stateId == '0')
	{
		alert("Invalid Selection");
		return;
	}	
	var imgElmt=document.getElementById("ajaxLoaderimg");
	var errDivElmt=document.getElementById("errorDiv");
	var searchNameEls = document.getElementsByName("searchName");
	var constTypeEls = document.getElementsByName("constType");
	var mlaEl=document.getElementById("mlaRadio");
	var mpEl=document.getElementById("mpRadio");
	if(!mlaEl.checked && !mpEl.checked)
	{
		errDivElmt.innerHTML="Select Constituency Type";
		return;
	}
	imgElmt.style.display="block";	
	errDivElmt.innerHTML="";
	
	var searchType;
	var constituencyType;
	for(var i = 0; i< searchNameEls.length; i++)
	{
		if(searchNameEls[i].checked)
			searchType = searchNameEls[i].value;
	}
	for(var j = 0; j< constTypeEls.length; j++)
	{
		if(constTypeEls[j].checked)
			constituencyType = constTypeEls[j].value;
	}
	
	var jsObj=
	{
		searchCriteria: searchType,
		stateId: stateId,
		constituencyType: constituencyType		
	}	
	
	var param ="task="+YAHOO.lang.JSON.stringify(jsObj); 
	
	ajaxCall(param);
 }

function validateTextField()
{
	var txtElmt=document.getElementById("myInput");
	var errDivElmt=document.getElementById("errorDiv");
	var cand=document.getElementById("candidateRadio");
	var cons=document.getElementById("constituencyRadio");
	var mlaRadio=document.getElementById("mlaRadio");
	var mpRadio=document.getElementById("mpRadio");
	var stateSelectEl = document.getElementById("stateSelect");
	if(!txtElmt)
		return false;
	if(!errDivElmt)
		return false;	
	errDivElmt.innerHTML="";
	if(!cand.checked && !cons.checked)
	{		
		errDivElmt.innerHTML="Select Search Criteria";
		return false;
	}
	if(!mlaRadio.checked && !mpRadio.checked)
	{
		errDivElmt.innerHTML="Select Consituency Type";
		return false;
	}
	if(stateSelectEl.options[stateSelectEl.selectedIndex].value == '0')
	{
		errDivElmt.innerHTML="Select Valid State";
		return false;
	}
	
	var value=txtElmt.value;		
	if(value=="")
	{		
		errDivElmt.innerHTML="Search String cannot be empty";
		return false;	
	}
	else
	{
		errDivElmt.innerHTML="";
		return true;
	}
	
}
function validateRadio()
{
	var cand=document.getElementById("candidateRadio");
	var cons=document.getElementById("constituencyRadio");
	
	var errElmt=document.getElementById("errorDiv");
	if(!cand.checked && !cons.checked)	
		errElmt.innerHTML="Select search criteria... ";		
	else
		errElmt.innerHTML="";
}

function resetConstTypeOptions()
{
	var radioEl = document.getElementsByName("constType");

	for(i=0; i< radioEl.length; i++)
	{
		if(radioEl[i].checked == true)
			radioEl[i].checked = false;
	}
	resetStateSelect();
}

function resetStateSelect()
{
	var stateSelectEl = document.getElementById("stateSelect");
	var errDivElmt=document.getElementById("errorDiv");
	stateSelectEl.selectedIndex = '0';
	errDivElmt.innerHTML="";
	
}

function getStates(selectedId, task, module, elementId, addressType, areaType)
{		
	var jsObj=
		{				
			id: selectedId,
			task: task,
			taskType:module,
			selectElementId: elementId ,
			address: addressType,
			areaType: areaType
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "locationsHierarchiesAjaxAction.action?"+rparam;						
	callAjaxForStates(jsObj,url);
}

function callAjaxForStates(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							clearOptionsListForSelectElmtId(jsObj.selectElementId);
							fillOptionsForSelectedElmt(jsObj.selectElementId, myResults);
							executeonLoad();
						}
						catch(e)
						{   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function executeonLoad()
{
	var selectEl = document.getElementById("stateSelect");	
	//selectEl.selectedIndex = '1';

	buildAutoSuggest();
	var selectedState =  selectEl.options[selectEl.selectedIndex].value;
	
}