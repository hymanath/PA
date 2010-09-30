
function getLocationHierarchies(selectedId, task, module, elementId, addressType)
{		
	var jsObj=
		{				
			id: selectedId,
			task: task,
			taskType:module,
			selectElementId: elementId ,
			address: addressType 
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "locationsHierarchiesAjaxAction.action?"+rparam;						
	callAjaxForLocations(jsObj,url);
}

function callAjaxForLocations(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							clearOptionsListForSelectElmtId(jsObj.selectElementId);
							fillOptionsForSelectedElmt(jsObj.selectElementId, myResults);
							
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

/**
 * Function to clear options for the HTML select element.
 * @method clearOptionsListForSelectElmtId
 * @param {String} elmtId .The select element id for which the options should be cleared. 
 */

function clearOptionsListForSelectElmtId(elmtId)
{
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
}
function fillOptionsForSelectedElmt(elmtId, optionsList)
{	

	var elmt = document.getElementById(elmtId);
	
	if( !elmt)
		return;
	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
}
