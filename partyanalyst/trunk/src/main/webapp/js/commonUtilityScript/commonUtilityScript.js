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

// Function is to Refresh parent window
function refreshParent() {
  window.opener.location.href = window.opener.location.href;

  if (window.opener.progressWindow)
		
 {
    window.opener.progressWindow.close()
  }
  window.close();
}

function replaceEnterKey(value,replaceWith)
{
   var val = escape(value);  
     //encode all characters in string  
     //to find carriage return character  
   for(z=0; z < val.length; z++)  
   {   
    //loop through string, replacing carriage return   
    //encoding with HTML break tag  
    if(val.indexOf("%0D%0A") > -1)  
    {   
     //Windows encodes returns as \r\n hex  
     val=val.replace("%0D%0A",replaceWith);  
    }  
    else if(val.indexOf("%0A") > -1)  
    {   
     //Unix encodes returns as \n hex  
     val=val.replace("%0A",replaceWith);  
    }  
    else if(val.indexOf("%0D") > -1)  
    {   
     //Macintosh encodes returns as \r hex  
     val=val.replace("%0D",replaceWith);  
    }  
   }  
   val=unescape(val);  
   return val;
  //decode all characters in string 
}

function removeAllUnwantedCharacters(str)
{
   var strng = str.replace(/[\'\\\%\&\#\"+"]/g," ");
   return replaceEnterKey(strng,"  ");
} 

function enableButton(id)
{
	document.getElementById(id).disabled  = false;
}

function disableButton(id)
{
	document.getElementById(id).disabled  = true;
}

function ltrim(str)
{
	if(str != null && str.length > 0)
	{
		var tempStr = str;
		for(var i=0; i<str.length; i++)
		{
			if(str.charAt(i) == ' ')
				tempStr = tempStr.substring(1);
			else
				return tempStr;
		}
		return tempStr;
	}
	else
		return str;
}

function rtrim(str)
{
	if(str != null && str.length > 0)
	{
		var tempStr = str;
		for(var i=str.length-1; i>=0; i--)
		{
			if(str.charAt(i) == ' ')
				tempStr = tempStr.substring(0,tempStr.length-1);
			else
				return tempStr;
		}
		return tempStr;
	}
	else
		return str;
}

function trim(str)
{
	if(str != null && str.length > 0)
	{
		str = ltrim(str);
		str = rtrim(str);
		return str;
	}
	else
		return str;
}

function showFeedBackFormPanel()
{		
	$("#feedback_window").dialog({
			resizable:false,
			width: 600,
			minHeight:350,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();
		$(".ui-widget-overlay").css("width","1000px");

		var elmt = document.getElementById("feedback_window_inner");

		var str = '';
		str += '<div id="feedback_window_head" style="font-size:12px;">Feed Back<a onclick="closeFeedbackwindow();"><img src="images/icons/close.jpg" style="height:12px;float:right;"></a></div>';
		str += '<div id="feedback_window_body">';
		str += '	<div id="feedBackNote_div">';
		str += '		<table>';
		str += '		<tr>';
		str += '		<td><img src="images/icons/infoicon.png"></td>';
		str += '		<td style="font-size:12px;">Fields marked with (<font color="red">*</font>) are mandatory</td>';
		str += '		</tr>';
		str += '		</table>';
		str += '	</div>';
		str += '	<div id="feedBackForm_div">';
		str += '		<table id="feedbackTable" width="100%">';
		str += '		<tr>';
		str += '		<th style="font-size:11px;"><font color="red">*</font> Select Feed Back type </th>';
		str += '		<td style="font-size:12px;">';
		str += '			<input type="radio" style="margin-top:0px;margin-right:4px;" checked="checked" class="selectWidth" value="1" name="commentType"> Complaint';
		str += '			<input type="radio" style="margin-top:0px;margin-right:4px;"  margin-top:0px;margin-right:4px;class="selectWidth" value="2" name="commentType"> Problem ';
		str += '			<input type="radio"  style="margin-top:0px;margin-right:4px;"  class="selectWidth" value="3" name="commentType"> Praise ';
		str += '			<input type="radio" style="margin-top:0px;margin-right:4px;"  class="selectWidth" value="4" name="commentType"> Suggestion ';
		str += '		</td>';
		str += '		</tr>';

		str += '		<tr>';
		str += '		<th style="font-size:12px;"><font color="red">*</font> FeedBack about</th>';
		str += '		<td style="font-size:12px;">';
		str += '			<select id="taskId">';
		str +='             <option value="0">Select feedback</option>';
		str += '			<option value="1">Web Site</option>';
		str += '			<option value="2">Party Analysis </option>';
		str += '			<option value="3">Constituency page</option>';
		str += '			<option value="4">Politician Analysis</option>';
		str += '			<option value="5">Search Analysis</option>';
		str += '			<option value="6">Other</option>';
		str += '			</select>';
		str += '		</td>';
		str += '		</tr>';

		str += '		<tr>';
		str += '		<th style="font-size:12px;"><font color="red">*</font> FeedBack </th>';
		str += '		<td style="font-size:12px;">';
		str += '			<textarea align="right" id="commentId" style="background-color:white;" rows="5" cols="39" name="comment"></textarea>';
		str += '		</td>';
		str += '		</tr>';

		str += '		<tr>';
		str += '		<th style="font-size:12px;"><font color="red">*</font> Select Response Type </th>';
		str += '		<td style="font-size:12px;">';
		str += '		      <input type="radio" style="margin-top:0px;margin-right:4px;"  checked="checked" value="Early" name="responseCategory">Early ';
		str += '		      <input type="radio" style="margin-top:0px;margin-right:4px;"  value="Late" name="responseCategory">Late';
		str += '		</td>';
		str += '		</tr>';

		str += '		</table>';
		str += '	</div>';
		str += '</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr>';
		str += '	<td width="65%" align="left"><div id="feedback_window_Msg"></div></td>';
		str += '	<td width="35%" align="right">';
		str += '		<input id="postButton" type="button" value="Post"></input>';
		str += '		<input id="close" type="button" value="Cancel"></input>';
		str += '	</td>';
		str += '	</tr>';
		str += '	</table>';	
		str += '</div>';
		elmt.innerHTML = str;

		var oPushButton1 = new YAHOO.widget.Button("postButton");  
		var oPushButton2 = new YAHOO.widget.Button("close");

		oPushButton1.on("click",function(){
			postFeedbackAjaxCall();
		});

		oPushButton2.on("click",function(){
			$("#feedback_window").dialog("destroy");
		});
	}	
	
function postFeedbackAjaxCall()
{
	var errorElmt = document.getElementById("feedback_window_Msg");
	
	var feedBackElmt = document.getElementById("commentId");
	feedBackElmtValue = feedBackElmt.value;
   
	if(feedBackElmtValue == "")
	{	
		errorElmt.innerHTML = '<font size="2" color="red">Feedback box cannot be empty</font>';
	    return; 
	}
	
	else
		errorElmt.innerHTML = "";
	
   var feedbackTypeElmt = document.getElementsByName("commentType");
   var feedbackAboutElmt = document.getElementById("taskId");
   var responseTypeElmt = document.getElementsByName("responseCategory");
	
   var feedbackType = '';
   var feedbackAbout = '';
   var responseType = '';

	feedbackAbout = feedbackAboutElmt.value;
	if(feedbackAbout == 0)
	{
		errorElmt.innerHTML = '<font size="2" color="red">Please Select Feedback</font>';
	return;
	}
	else 
		errorElmt.innerHTML = "";
   for(var i=0; i<feedbackTypeElmt.length; i++)
		 if(feedbackTypeElmt[i].checked==true)
             feedbackType = feedbackTypeElmt[i].value;
	
	for(var j=0;j<responseTypeElmt.length;j++)
		if(responseTypeElmt[j].checked == true)
				responseType = responseTypeElmt[j].value;

  var jObj=
         {
			feedback:feedBackElmtValue,
			commentTypeId :feedbackType,
			commentTaskId :feedbackAbout,
			responseType :responseType,
			task : "getComments"
		 };
		 
	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
     var url = "userFeedbackSubmitAjaxAction.action?"+rparam;
	 callAjaxForFeedBack(jObj,url);
}
function callAjaxForFeedBack(jObj,url)
{
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	

							if(jObj.task == 'getComments')
							{
								showFeedBackStatusMessage(myResults);
								$("#taskId").prepend("<option value='0'>Select feedback</option>");
								document.getElementById("taskId").value = 0;
							}
						}
					catch(e)

						{   
							//alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function showFeedBackStatusMessage(result)
{


var feedback_window = document.getElementById('feedback_window');
	if(result.exceptionEncountered == null)
	{
		var errorElmt = document.getElementById("feedback_window_Msg");
			errorElmt.innerHTML = "<font color='green'>Your FeedBack Submitted Successfully.</font>";
			clearFeedBackFields();
			
		setTimeout("closeFeedbackwindow()",3000);
	}
	else
	{
		var errorElmt = document.getElementById("feedback_window_Msg");
			errorElmt.innerHTML = "<font color='red'>Sorry,Your FeedBack not " +
					"ted.Please Try again.</font>";
	}
	
}

function clearFeedBackFields()
{
document.getElementById('commentId').value='';
	
}

function closeFeedbackwindow()
{
	$("#feedback_window").dialog("destroy");
}

function createOptionsForSelectElmtId1(elmtId,optionsList)
{
	var elmt = document.getElementById(elmtId);
	if( !elmt || optionsList == null)
		return;
	
	for(var i in optionsList[0].selectOptionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[0].selectOptionsList[i].id;
		option.text=optionsList[0].selectOptionsList[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
//for given value population
$("option[value='"+optionsList[0].populateId+"']").attr('selected', 'selected');
}
