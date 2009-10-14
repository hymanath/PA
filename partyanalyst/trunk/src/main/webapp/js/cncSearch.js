function buildAutoSuggest(value)
{
	var datastore= value;
	
	var imgElmt=document.getElementById("ajaxLoaderimg");
	imgElmt.style.display="none";

	var txtDivElmt=document.getElementById("textFldDiv");
	if(navigator.appName=="Microsoft Internet Explorer")
	{		
		var txtstr='<input id="myInput" type="text" name="searchText" style="position:absolute;top:2px;"/>';
		txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;top:28px;"></div>';
		txtDivElmt.innerHTML=txtstr;
	}
	else
	{
		var txtstr='<input id="myInput" type="text" name="searchText" style="padding: 3px 0px 2px 0px; font-size: 10px; font-family: arial;"/>';
		txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;top:22px;"></div>';
		txtDivElmt.innerHTML=txtstr;
	}
	
	var dsLocalArray = new YAHOO.util.LocalDataSource(datastore); 
	var myAutoComp = new YAHOO.widget.AutoComplete("myInput","suggestDiv",dsLocalArray);
	myAutoComp.prehighlightClassName = "yui-ac-prehighlight"; 
    myAutoComp.useShadow = true;
	myAutoComp.useIFrame = true;
	myAutoComp.textboxKeyEvent.subscribe(validateRadio); 
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

 function getParser(val)
 {
	var imgElmt=document.getElementById("ajaxLoaderimg");
	imgElmt.style.display="block";

	var errDivElmt=document.getElementById("errorDiv");
	errDivElmt.innerHTML="";

	SEARCHFOR=val;
	var jsObj=
	{
		searchCriteria:val
	}
	
	//var param = "task="+JSON.stringify(jsObj);
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
		var datastore="";
		var txtDivElmt=document.getElementById("textFldDiv");
		
		if(navigator.appName=="Microsoft Internet Explorer")
		{				
			var txtstr='<input id="myInput" type="text" name="searchText" onKeyup="validateRadio()" style="position:absolute;top:2px;"/>';
			txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;"></div>';
			txtDivElmt.innerHTML=txtstr;
		}
		else
		{
			var txtstr='<input id="myInput" type="text" name="searchText" onKeyup="validateRadio()" style="padding: 3px 0px 2px 0px; font-size: 10px; font-family: arial;"/>';
			txtstr+='<div id="suggestDiv" style="position:absolute;z-index:50000000;font-size:10px;"></div>';
			txtDivElmt.innerHTML=txtstr;
		}

		var dsLocalArray = new YAHOO.util.LocalDataSource(datastore); 
		var myAutoComp = new YAHOO.widget.AutoComplete("myInput","suggestDiv",dsLocalArray);
		myAutoComp.prehighlightClassName = "yui-ac-prehighlight"; 
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