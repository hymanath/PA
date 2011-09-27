/**
 * @author Mohan
 * This script provides the  functionalities which are used in common. 
 */

/**
 * Function to create options for the HTML select element with default Select Option.
 * @method createOptionsForSelectElmtIdWithSelectOption
 * @param {String} elmtId .The select element id for which the options should 
 * @param {List} optionsList. The List of items with which the options should be built.  
 */

function createOptionsForSelectElmtIdWithSelectOption(elmtId,optionsList)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	
	var option = document.createElement('option');
	option.value="0";
	option.text="Select";
	try
	{
		elmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		elmt.add(option); // IE only
	}

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
 * This method takes element id as input and populates the select box with select item
 * @method clearOptionsListForSelectElmtId
 * @param elmtId The element Id that needs to be populated with select item. 
 */
function createSelectOptionsForSelectElmtId(elmtId)
{	

	var elmt = document.getElementById(elmtId);
	
	if( !elmt)
		return;
	
	var option = document.createElement('option');
	option.value="0";
	option.text="Select";
	try
	{
		elmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		elmt.add(option); // IE only
	}
}

 
 /**
  * This method takes element id as input and populates the select box with select item
  * @method clearOptionsListForSelectElmtId
  * @param elmtId The element Id that needs to be populated with select item. 
  */
 function createSelectOptionsForSelectConstituencyElmtId(elmtId)
 {	

 	var elmt = document.getElementById(elmtId);
 	
 	if( !elmt)
 		return;
 	
 	var option = document.createElement('option');
 	option.value="0";
 	option.text="Select Constituency";
 	try
 	{
 		elmt.add(option,null); // standards compliant
 	}
 	catch(ex)
 	{
 		elmt.add(option); // IE only
 	}
 }
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
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
}


/**
 * Function to remove /n or enter stroke and change to single string.
 * @method removeEnterStrokeForString
 * @param {String} str .The string from wich the enter stroke should be removed. 
 */

function removeEnterStrokeForString(str)
{
	if(str.length==0 || !str)
		return str;
	
	var strSingleLine = str.replace( new RegExp( "\\n", "g" ), " " );		
	return strSingleLine;
}


/** 
 * Function to display day of the week.
 * @method getDayOfWeek
 * @param {Number} num .The digit corresponding to that day,must be less than or equal to 6. 
 */

function getDayOfWeek(num)
{
	if(num>6)
	{
		alert("Invalid Number For retrieving Day");
		return;
	}
	if(num == 0)
		return "Sunday";
	else if(num == 1)
		return "Monday";
	else if(num == 2)
		return "Tuesday";
	else if(num == 3)
		return "Wednesday";
	else if(num == 4)
		return "Thursday";
	else if(num == 5)
		return "Friday";
	else if(num == 6)
		return "Saturday";
}


/** 
 * Function to display Month.
 * @method getMonth
 * @param {Number} num .The digit corresponding to that month,must be less than or equal to 11. 
 */

function getMonth(num)
{
	if(num>12)
	{
		alert("Invalid Number For retrieving Month");
		return;
	}
	if(num == 0)
		return "January";
	else if(num == 1)
		return "February";
	else if(num == 2)
		return "March";
	else if(num == 3)
		return "April";
	else if(num == 4)
		return "May";
	else if(num == 5)
		return "June";
	else if(num == 6)
		return "July";
	else if(num == 7)
		return "August";
	else if(num == 8)
		return "September";
	else if(num == 9)
		return "October";
	else if(num == 10)
		return "November";
	else if(num == 11)
		return "December";
}


// For Calendar
 function focusCalTextElmt(id){
	 id1="#"+id;
	$(id1).datepick();
	document.getElementById(id).focus();
}
function showCalendar(id){
	id1="#"+id;
	$(id1).datepick();
}
function showBusyImgWithId(elmtId)
{		
		var spanElmt = document.getElementById(elmtId+"_ImgSpan");
		if(spanElmt)
			spanElmt.style.display = 'block';
}
function hideBusyImgWithId(elmtId)
{
	var spanElmt = document.getElementById(elmtId+"_ImgSpan");
	if(spanElmt)
		spanElmt.style.display = "none";
}