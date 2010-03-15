/**
 * @author Mohan
 * This script provides the  functionalities which are used in common. 
 */


/**
 * Function to create options for the HTML select element.
 * @method createOptionsForSelectElmtId
 * @param {String} elmtId .The select element id for which the options should 
 * @param {List} optionsList. The List of items with which the options should be built.  
 */

function createOptionsForSelectElmtId(elmtId,optionsList)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
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
	for(i=len-1;i>0;i--)
	{
		elmt.remove(i);
	}	
}