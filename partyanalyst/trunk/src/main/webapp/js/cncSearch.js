function buildAutoSuggest(value)
{
	var datastore= value;
	
	var imgElmt=document.getElementById("ajaxLoaderimg");
	imgElmt.style.display="none";

	var txtDivElmt=document.getElementById("textFldDiv");
	if(navigator.appName=="Microsoft Internet Explorer")
	{		
		var txtstr='<input id="myInput" type="text" name="searchText" style="position:absolute;top:2px;"/>';
		txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;top:22px;width:215px;max-height:250px;"></div>';
		txtDivElmt.innerHTML=txtstr;
	}
	else
	{
		var txtstr='<input id="myInput" type="text" name="searchText" style="padding: 3px 0px 2px 0px; font-size: 10px; font-family: arial;"/>';
		txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;top:22px;width:215px;max-height:250px;"></div>';
		txtDivElmt.innerHTML=txtstr;
	}
	
	var dsLocalArray = new YAHOO.util.LocalDataSource(datastore); 
	var myAutoComp = new YAHOO.widget.AutoComplete("myInput","suggestDiv",dsLocalArray);
	myAutoComp.prehighlightClassName = "yui-ac-prehighlight"; 
    myAutoComp.useShadow = true;
	myAutoComp.minQueryLength = 3;
	myAutoComp.maxResultsDisplayed = 10;
	myAutoComp.useIFrame = true;
	myAutoComp.textboxKeyEvent.subscribe(validateRadio); 
	var myInputEl = document.getElementById("myInput");
	myInputEl.focus();
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

	if(!txtElmt)
		return false;
	if(!errDivElmt)
		return false;	
	
	if(!cand.checked && !cons.checked)
	{
		errDivElmt.innerHTML="Select search criteria... ";
		return false;
	}

	var value=txtElmt.value;		
	if(value=="")
	{		
		errDivElmt.innerHTML="* Search text cannot be empty";
		return false;	
	}
	else
	{
		errDivElmt.innerHTML="";
		return true;
	}
	
}
function getAutoComplete()
{
		var row4El = document.getElementById("row4");
			
		if(row4El.style.display == 'none')
			row4El.style.display = 'block';
		var row5El = document.getElementById("row5");
		
		if(row5El.style.display == 'none')
			row5El.style.display = 'block';
		
		var datastore="";
		var txtDivElmt=document.getElementById("textFldDiv");
		
		if(navigator.appName=="Microsoft Internet Explorer")
		{				
			var txtstr='<input id="myInput" type="text" name="searchText" onKeyup="validateRadio()" style="position:absolute;top:2px;"/>';
			txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;width:215px;max-height:250px;"></div>';
			txtDivElmt.innerHTML=txtstr;
		}
		else
		{
			var txtstr='<input id="myInput" type="text" name="searchText" onKeyup="validateRadio()" style="padding: 3px 0px 2px 0px; font-size: 10px; font-family: arial;"/>';
			txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;width:215px;max-height:250px;"></div>';
			txtDivElmt.innerHTML=txtstr;
		}

		var dsLocalArray = new YAHOO.util.LocalDataSource(datastore); 
		var myAutoComp = new YAHOO.widget.AutoComplete("myInput","suggestDiv",dsLocalArray);
		myAutoComp.prehighlightClassName = "yui-ac-prehighlight"; 
		myAutoComp.minQueryLength = 3;
		myAutoComp.maxResultsDisplayed = 10;
	    myAutoComp.useShadow = true;
		myAutoComp.useIFrame = true; 
		
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
								alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function executeonLoad()
{
	var selectEl = document.getElementById("stateSelect");
	buildAutoSuggest();
	selectEl.selectedIndex = '1';
	var selectedState =  selectEl.options[selectEl.selectedIndex].value;
	getParser(selectedState);	
}