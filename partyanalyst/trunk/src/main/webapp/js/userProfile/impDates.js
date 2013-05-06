function showInitialImpEventsAndDates(eventsarr,type,task)
	{
	
		var divElmt;
		if(type == "impDates")
			var elmt = document.getElementById("cadreImpDatesBodyDiv");
		if(elmt)
			elmt.innerHTML='';
		
		for(var i in eventsarr)
		{			
			if(type == "impDates" && eventsarr[i].impDate)
			{	
				var sDayobj = getDateTime(eventsarr[i].impDate);				
				var startDayStr = sDayobj.day;
				var startMonStr = sDayobj.month;
				var startYearStr = sDayobj.year;
				var startTimeHrs = sDayobj.hours;
				var startTimeMin = sDayobj.minutes;
				
			}

			if(eventsarr[i].endDate)
			{
				var eDayobj = getDateTime(eventsarr[i].endDate);				
				var endDayStr = eDayobj.day;
				var endMonStr = eDayobj.month;
				var endYearStr = eDayobj.year;
				var endTimeHrs = eDayobj.hours;
				var endTimeMin = eDayobj.minutes;
			}
			
			divElmt = document.createElement('div');						

			if(i%2!=0)
				divElmt.setAttribute('style','background-color:#EBF5FF;');
			else
				divElmt.setAttribute('style','background-color:#F9FCFF;');
		
			var str='';
			if(type == "impDates")
			{
				str+='<div id="ImpDate_'+eventsarr[i].importantDateId+'" class="eventSummaryDiv">';	
				str+='<span id="cadreSpan_'+eventsarr[i].importantDateId+'_cross" class="cadresCloseSpan" onclick="deleteSelectedEvent(\'impDate\','+eventsarr[i].importantDateId+')"> X </span>';
				str+='<span id="cadreSpan_'+eventsarr[i].importantDateId+'_edit" class="cadresCloseSpan" onclick="showSelectedDateEvent(\'ImpDate_'+eventsarr[i].importantDateId+'\',\''+eventsarr[i].eventType+'\',\'impDate\')">';
				str+='<img height="10" width="10" src="'+requestPath+'/images/icons/pencil.png"/> </span>';
				
			}
			if(type == "impDates")
			{
			var newdate = startDayStr+'/'+startMonStr+'/'+startYearStr;
			str+='<table style="font-family:arial;" onclick="showUnEditableSelectedDateEvent(\'ImpDate_'+eventsarr[i].importantDateId+'\',\''+eventsarr[i].eventType+'\',\'impDate\',\''+newdate+'\')">';
			}
			
			str+='<tr>';
			str+='<td><img height="10" width="10" src="'+requestPath+'/images/icons/arrow.png"/></td>';
			str+='<td>'+eventsarr[i].title+'</td>';	
			str+='<td> - </td>';	
			str+='<td>'+startDayStr+'-'+startMonStr+'-'+startYearStr+'</td>';
			str+='<tr>';
			str+='</table>';
			str+='</div>';
			divElmt.innerHTML=str;		
				
			if(elmt)
			{
				elmt.appendChild(divElmt);
			}
			
			if(type == "impEvents" && eventsarr[i].endDate)
			{	
				var startDate = new Date(startMonStr+"/"+startDayStr+"/"+startYearStr);
				var endDate = new Date(endMonStr+"/"+endDayStr+"/"+endYearStr);
				
				while(startDate<=endDate)
				{
					var eventColorRender = (startDate.getMonth()+1)+"/"+startDate.getDate()+"/"+startDate.getFullYear();
							
					if(existingDataCheck(eventColorRender,type))
					{
						eventsRenderArr.push(eventColorRender);
					}
					else
					{
						removeByElement(datesRenderArr,eventColorRender);
						eventDateRenderArr.push(eventColorRender);
					}				
					startDate=new Date(startDate.getTime()+86400000);
				}	
								
			}
			else
			{	
				var startDate = new Date(startMonStr+"/"+startDayStr+"/"+startYearStr);
				var renderValue=(startDate.getMonth()+1)+"/"+startDate.getDate()+"/"+startDate.getFullYear();
				
				if(existingDataCheck(renderValue,type))
					datesRenderArr.push(renderValue);
				else
				{
					removeByElement(eventsRenderArr,renderValue);
					eventDateRenderArr.push(renderValue);
				}
			}		
						
		}
	datesRenderArr = new Array();
	if(eventsarr != null &&  eventsarr.length > 0)
	{
	for(var i in eventsarr)
	{
	var impDayobj = getDateTime(eventsarr[i].impDate);
	var impDate = new Date(impDayobj.month+"/"+impDayobj.day+"/"+impDayobj.year);
	var renderValue=(impDate .getMonth()+1)+"/"+impDate.getDate()+"/"+impDate.getFullYear();
	datesRenderArr.push(renderValue);
	}
	}
	else
	{
		datesRenderArr.push("");
	}
	renderStack();
}

	function existingDataCheck(date,type)
	{		
		var testArray = new Array();
		var status = true;
		if(type == "impDates" || type == "createImpDateEvent")
			testArray = eventsRenderArr;
				
		var arrLength = testArray.length;
		
		if(arrLength == 0)
			return true;
		for(var i=0;i<arrLength;i++)
		{
			if(testArray[i] == date)
				status = false;
		}
		
		return status;
	}
	function renderStack()
	{	
		for(var i in eventsRenderArr)
		{
		
			mainEventCalendar.addRenderer(eventsRenderArr[i], mainEventCalendar.renderCellStyleHighlight1);			
		}
		for(var j in datesRenderArr)
		{
			mainEventCalendar.addRenderer(datesRenderArr[j], mainEventCalendar.renderCellStyleHighlight2);			
		}
		for(var k in eventDateRenderArr)
		{
			mainEventCalendar.addRenderer(eventDateRenderArr[k], mainEventCalendar.renderCellStyleHighlight3);			
		}

		
		mainEventCalendar.render(); 
	}
	function getDateTime(date)
	{	
		var startDayStr = date.substring(8,10);		
		var startMonStr = date.substring(5,7);
		var startYearStr = date.substring(0,4);	

		var startTimeHrs = date.substring(11,13);	
		var startTimeMin = date.substring(14,16);	

	
		var dateTimeObj={
							day:startDayStr,
							month:startMonStr,
							year:startYearStr,
							hours:startTimeHrs,
							minutes:startTimeMin
						};

		return dateTimeObj;
	}
	function buildCalendarControl()
	{
		var navConfig = { 
	      strings : { 
	          month: "Choose Month", 
	          year: "Enter Year", 
	          submit: "OK", 
	          cancel: "Cancel", 
	          invalidYear: "Please enter a valid year" 
	      }, 
	      monthFormat: YAHOO.widget.Calendar.SHORT, 
	      initialFocus: "year" 
	}; 
	
		mainEventCalendar = new YAHOO.widget.Calendar("cadreDatesYUICalDiv", {navigator:navConfig}); 
		mainEventCalendar.selectEvent.subscribe(mySelectHandler, mainEventCalendar, true); 
		mainEventCalendar.changePageEvent.subscribe(changePageHandler, mainEventCalendar, true);		
		mainEventCalendar.render(); 
	}
	function mySelectHandler(type,args,obj)
	{ 
		var selected = args[0]; 
		var selDate = this.toDate(selected[0]); 
		var calDateResult = dateToLocaleString(selDate, this);
		var divElmt = document.createElement('div');
		divElmt.setAttribute('id',calDateResult);
		divElmt.innerHTML="SELECTED: " + calDateResult;
		var elmt = document.getElementById("cadreImpEventsBodyDiv");
		if(elmt)
		{
			//elmt.appendChild(divElmt);
		}
	     
	};
	var selYear ;
	var selMonth;
	function changePageHandler(type,args,obj)
	{
		
		var current = args[1];
		var date = new Date(current);
		var month = date.getMonth();
		var year = date.getFullYear();	
		dateObj.dateVal = '1';
		dateObj.monthVal = date.getMonth();
		dateObj.yearVal = date.getFullYear();
		selMonth = date.getMonth();
		selYear = date.getFullYear();
		var nameOfMonth = monthname[month];
		$('#headerDiv').html('<b><font color="blue">'+year+' '+nameOfMonth+'</font> Month Important Dates</b>');
		var jsObj={
					monthVal:month,
					yearval:year,
					task:'nextMonthEvents'
				  };
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url =requestPath+'/getNextMonthDatesEvents.action?'+rparam;	
		callAjax(jsObj,url);
	}
	
	
var	selectedDateObj={
							importantDateId:"",
							eventId:"",
							eventType:"",
							eventName:"",
							startDate:"",	
							endDate:"",
							desc:"",
							frequency:"",
							isDeleted:"",
							task:""
						};
var dateObj={
					dateVal:new Date().getDate(),
					monthVal:new Date().getMonth(),
					yearVal:new Date().getFullYear()
				};
var ImpDatesArray = new Array();
var ImpEventsArray = new Array();
var eventCadresArray = new Array();
var actionCadresArray = new Array();
var eventsRenderArr = new Array();
var datesRenderArr = new Array();
var eventDateRenderArr = new Array();	
var emptyArray = new Array();
var popupPanel;
var smsHidden = 1;
var createNewDate = '${createDate}';
var createNewEvent = '${createEvent}'; 

function buildNewImpDatePopup()
	{
		var elmt = document.getElementById('cadreManagementMainDiv');
		var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();		
		var divChild = document.createElement('div');
		divChild.setAttribute('id','newImpDateDiv');
		divChild.setAttribute('class','yui-skin-sam');
		var eventStr='';
		eventStr+='<div class="hd"></div> ';
		eventStr+='<div class="bd">'; 
		eventStr+='<div id="errorMesgDIV"></div>';
		eventStr+='<table width="200%" class="bordered-table">';
		eventStr+='<tr>';
		eventStr+='<th>Important Date Title <font style="color:red; font-size: 18px;">  * </font></th>';
		eventStr+='<td><input type="text" size="50" id="ImpeventNameText"  name="ImpeventNameText"/></td>';
		eventStr+='</tr>';
		eventStr+='<tr>';
		eventStr+='<th>Important Date</th>';
		eventStr+='<td>';
		eventStr+='<div><input type="text" id="ImpStartDateText_new"  name="ImpStartDateText"  readonly="readonly"/></div>';
		eventStr+='<div id="ImpStartDateText_new_Div" class="tinyDateCal"></div>';
		eventStr+='</td>';		
		eventStr+='</tr>';
		eventStr+='<tr>';
		eventStr+='<th>Description <font style="color:red; font-size: 18px;">  * </font></th>';
		eventStr+='<td colspan="3"><textarea rows="5" cols="50" id="ImpdescTextArea" name="ImpdescTextArea" style="background-color: rgb(255, 255, 255);"></textarea></td>';
		eventStr+='</tr></table>';		
		eventStr+='<table width="100%">';
		eventStr+='<th>Repeat Frequency</th>';
		eventStr+='<td>';
		eventStr+='<select id="repeatFreqSelect" class="timeSelect" onchange="showEndDateText(this.options[this.selectedIndex].text)">';
		eventStr+='<option value="No Repeat">No Repeat</option>';
		eventStr+='<option value="Yearly">Yearly</option><option value="Monthly">Monthly</option><option value="Weekly">Weekly</option></select></td>';
		eventStr+='<th>Repeat Until</th>';
		eventStr+='<td>';
		eventStr+='<input type="text" id="ImpEndDateText_new" name="ImpEndDateText" readonly="readonly"  /></div>';
		eventStr+='<div id="ImpEndDateText_new_Div" class="tinyDateCal">';
		eventStr+='</td>';
		eventStr+='</table>';
		eventStr+='</div>';
		divChild.innerHTML=eventStr;
		elmt.appendChild(divChild);
		
		if(newDateDialog)
		{
		 newDateDialog.remove();
		}
		var newDate = new Date();
		var day = newDate.getDate();
		var month = newDate.getMonth()+1;
		var year = newDate.getFullYear();
		if(day < 9) 
		day = 0+''+day;
		if(month < 9)
		month = 0+''+month;
		var date = day+"/"+month+"/"+year;	
		newDateDialog = $('#newImpDateDiv').dialog({
			width: 600,
			buttons: {
				"Create New Date": function(){
				
					handleImpDateSubmit();
					
				},"Cancel": function(){
					$(this).dialog("close");
				}
			},
			'title':'New Date',
			
		});
		
		$(".ui-dialog-buttonset button").attr("class", "btn btn-primary");
		$(".ui-dialog-title-eventDateDetails").attr("class","Cambria,'Abel',sans-serif,Helvetica");
		$("#ImpStartDateText_new" ).datepicker({
			dateFormat: "dd/mm/yy",
			changeMonth: true,
            changeYear: true,
			minDate: new Date()
			
		});
		
		$("#ImpEndDateText_new" ).datepicker({
			dateFormat: "dd/mm/yy",
			changeMonth: true,
            changeYear: true,
			minDate: new Date(),
			
			
		});
		
		$("#ImpStartDateText_new").val(date);
		$("#ImpEndDateText_new").val(date);
	
	}
	function updateSelectedEvent(type)
	{
		
		var ImpeventNameValue = $("#ImpeventNameText").val();			
		var ImpDescValu = $("#ImpdescTextArea1").val();
			
		var ImpDescVallength=ImpDescValu.trim().length;
			
		if(ImpeventNameValue == '')
		    {
			document.getElementById("errorMesgDIV").innerHTML = '<font color="red">Please Enter Important Date Title </font>';
			return;
			}
			else if ( ImpeventNameValue != null)
			{ 				
				var iChars = "!`@#$%^&*()+=-[]\\\';,./{}|\":<>?~";  
				
		            for (var i = 0; i < ImpeventNameValue.length; i++)
                {      
                    if (iChars.indexOf(ImpeventNameValue.charAt(i)) != -1)
                    {   
					document.getElementById("errorMesgDIV").innerHTML = '<font color="red"> Important Date Title cannot allow special characters & Numbers</font>';
					return false;
                    } 
                }
			
			}
			
		if(ImpDescVallength==0){
					document.getElementById("errorMesgDIV").innerHTML = '<font color="red">Please Enter Description</font>';
				return;
			}
		 
		var results = YAHOO.lang.JSON.parse(jsonStr);	
		var jsObj;
		if(type == 'impDate')
		{
			var ImpeventNameVal = document.getElementById("ImpeventNameText").value;
			var ImpstartDateVal = document.getElementById("ImpStartDateText").value;		
			var ImpendDateVal = document.getElementById("ImpEndDateText").value;		
			var ImpDescVal = document.getElementById("ImpdescTextArea1").value;
			selectedDateObj.importantDateId = results[0].importantDateId;
			selectedDateObj.eventId = results[0].eventId;
			selectedDateObj.eventType = results[0].eventType;
			selectedDateObj.eventName = ImpeventNameVal;
			selectedDateObj.startDate = ImpstartDateVal;
			selectedDateObj.endDate = ImpendDateVal;
			selectedDateObj.desc = ImpDescVal;
			selectedDateObj.frequency = results[0].frequency;
			selectedDateObj.isDeleted = "NO";
			selectedDateObj.task="updateImpDateEvent";
			jsObj = selectedDateObj;			
		}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url =requestPath+'/createEventAction.action?'+rparam;	
			$('#eventDateDetails').dialog("close");
		callAjax(jsObj,url);
		
	}
	
	function handleImpDateSubmit()
	{	
	//alert("handleImpDateSubmit");
		var flag = checkDate("impDate");
		if(flag)
		return;
		var ImpeventNameVal = document.getElementById("ImpeventNameText").value;
		var ImpstartDateVal = document.getElementById("ImpStartDateText_new").value;		
		var ImpendDateVal = document.getElementById("ImpEndDateText_new").value;		
		var ImpDescVal = document.getElementById("ImpdescTextArea").value;
		ImpDescVal = removeEnterStrokeForString(ImpDescVal);
		var repeatFreqElmt = document.getElementById("repeatFreqSelect");
		repeatFreqVal =  repeatFreqElmt.options[repeatFreqElmt.selectedIndex].value;
		var ImpDescVallength=ImpDescVal.trim().length;
		    if(ImpeventNameVal == '')
		    {
			document.getElementById("errorMesgDIV").innerHTML = '<font color="red">Please Enter Important Date Title </font>';
			return;
			}
			else if ( ImpeventNameVal != null)
			{ 				
				var iChars = "!`@#$%^&*()+=-[]\\\';,./{}|\":<>?~";  
				
		            for (var i = 0; i < ImpeventNameVal.length; i++)
                {      
                    if (iChars.indexOf(ImpeventNameVal.charAt(i)) != -1)
                    {   
					document.getElementById("errorMesgDIV").innerHTML = '<font color="red"> Important Date Title cannot allow special characters & Numbers</font>';
			
                    return false; 
                    } 
                }
			}
			if(ImpDescVallength==0)
			{
			   document.getElementById("errorMesgDIV").innerHTML = '<font color="red">Please Enter Description</font>';
			  return;
			}
			 if(repeatFreqVal == "No Repeat")
			{
				ImpendDateVal = ImpstartDateVal;
			}
		
		selectedDateObj.importantDateId="";
		selectedDateObj.eventId="";
		selectedDateObj.eventType="";
		selectedDateObj.eventName=ImpeventNameVal;
		selectedDateObj.startDate=ImpstartDateVal;	
		selectedDateObj.endDate=ImpendDateVal;
		selectedDateObj.desc=ImpDescVal;
		selectedDateObj.frequency=repeatFreqVal;
		selectedDateObj.isDeleted=repeatFreqVal;
		selectedDateObj.task="createImpDateEvent";		
		var rparam ="task="+YAHOO.lang.JSON.stringify(selectedDateObj);
		var url =requestPath+'/createEventAction.action?'+rparam;
		callAjax(selectedDateObj,url);
		
		
	}
	function callAjax(jsObj,url)
	{			
		
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
								if(jsObj.task=="createImpDateEvent")
								{
								$('#newImpDateDiv').dialog("close");	
									
									alert("Important Date created successfully");
									var date = new Date();
									
									var jsObj1={
									monthVal:selMonth,
									yearval:selYear,
									task:'nextMonthEvents'
								   };
									var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj1);
									var url =requestPath+'/getNextMonthDatesEvents.action?'+rparam;	
									callAjax(jsObj1,url);
								}
																
								else if(jsObj.task=="nextMonthEvents")
								{
									showInitialImpEventsAndDates(myResults.userImpDates,"impDates","nextPreviousMonthEvents");
								}
								else if(jsObj.task=="deleteImpDate")
								{
									
									var date = new Date();
									
									var jsObj1={
									monthVal:selMonth,
									yearval:selYear,
									task:'nextMonthEvents'
								   };
									var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj1);
									var url =requestPath+'/getNextMonthDatesEvents.action?'+rparam;	
									callAjax(jsObj1,url);
									removeDeletedElement(myResults,jsObj);
									
								}
								else if(jsObj.task=="showSelectedDateEvent_nonEditable")
								{				
									buildUnEditableSelectedDateEventPopup(myResults,jsObj);
								}	
								else if(jsObj.task=="showSelectedDateEvent")
								{
									
									buildSelectedDateEventPopup(myResults,jsObj);
								}
								else if(jsObj.task=="subscribe")
								{									
									var elmt = document.getElementById('subscribePartyDates');
									if(!elmt)
										alert("No subscribe Element");
									if(elmt.innerHTML == "Subscribe Party Imp Dates")
										elmt.innerHTML == "Unsubscribe Party Imp Dates";
									else if(elmt.innerHTML == "Subscribe Party Imp Dates")
										elmt.innerHTML == "Unsubscribe Party Imp Dates";

									elmt.innerHTML=myResults.subscribeTitle;
									showInitialImpEventsAndDates(myResults.userImpDates,"impDates","subscribe");
								}
								else if(jsObj.task=="updateImpDateEvent")
								{	
									var date = new Date();
									
									var jsObj1={
									monthVal:selMonth,
									yearval:selYear,
									task:'nextMonthEvents'
								   };
									var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj1);
									var url =requestPath+'/getNextMonthDatesEvents.action?'+rparam;	
									callAjax(jsObj1,url);
								}
										
							}catch (e) {   
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
	/*function addCreatedEvent(results,jsObj)
	{
	datesRenderArr = new Array();
	if(results != null &&  results.length > 0)
	{
	for(var i in results)
	{
	var impDayobj = getDateTime(results[i].impDate);
	var impDate = new Date(impDayobj.month+"/"+impDayobj.day+"/"+impDayobj.year);
	var renderValue=(impDate .getMonth()+1)+"/"+impDate.getDate()+"/"+impDate.getFullYear();
	datesRenderArr.push(renderValue);
	}
	}
	else
	{
		datesRenderArr.push("");
	}
		if(jsObj.task == "updateImpDateEvent")
		{
			//eventDateDialog.hide();
			alert("Date Updated Successfully");
			
			if(jsObj.task == "updateImpDateEvent")
			var elmt = document.getElementById("ImpDate_"+results[0].importantDateId);
			
			var parent = elmt.parentNode;
			parent.removeChild(elmt);
				
		}
		
		var divElmt = document.createElement('div');
		
		var str='';
		
		if(jsObj.task == "createImpDateEvent" || jsObj.task == "updateImpDateEvent")
		{			
			str+='<div id="ImpDate_'+results[0].importantDateId+'" class="eventSummaryDiv">';
			str+='<span id="cadreSpan_'+results[0].importantDateId+'_cross" class="cadresCloseSpan" onclick="deleteSelectedEvent(\'impDate\','+results[0].importantDateId+')"> X </span>';
			str+='<span id="cadreSpan_'+results[0].importantDateId+'_edit" class="cadresCloseSpan" onclick="showSelectedDateEvent(this.id,\'ImpDate_'+results[0].importantDateId+'\',\'impDate\')">';
			str+='<img height="10" width="10" src="'+requestPath+'/images/icons/pencil.png"/> </span>';
		}
		if(jsObj.task == "createImpDateEvent" || jsObj.task == "updateImpDateEvent")
		{
		str+='<table style="font-family:arial;" onclick="showUnEditableSelectedDateEvent(\'ImpDate_'+results[0].importantDateId+'\',\''+results[0].importantDateId+'\',\'impDate\')">';	
		}		
		str+='<tr>';
		str+='<td><img height="10" width="10" src=""'+requestPath+'/images/icons/arrow.png"/></td>';
		
		if(jsObj.task == "createImpDateEvent" || jsObj.task == "updateImpDateEvent")
		{
			var impDayobj = getDateTime(results[0].impDate);	
			str+='<td>'+results[0].title+'</td>';
			str+='<td> - </td>';
			str+='<td>'+impDayobj.day+'/'+impDayobj.month+'/'+impDayobj.year+'</td>';			
		}
		str+='<tr>';
		str+='</table>';
		str+='</div>';
		divElmt.innerHTML=str;
				
		var elmt;
		
		if(jsObj.task == "createImpDateEvent" || jsObj.task == "updateImpDateEvent")
			elmt = document.getElementById("cadreImpDatesBodyDiv");

		if(elmt)
		{
			elmt.appendChild(divElmt);
		}
		
		if(results.endDate)
		{		
			var startDate = new Date(sDayobj.month+"/"+sDayobj.day+"/"+sDayobj.year);
			var endDate = new Date(eDayobj.month+"/"+eDayobj.day+"/"+eDayobj.year);

			while(startDate<=endDate)
			{
				var eventColorRender = (startDate.getMonth()+1)+"/"+startDate.getDate()+"/"+startDate.getFullYear();
						
				if(existingDataCheck(eventColorRender,jsObj.task))
				{
					eventsRenderArr.push(eventColorRender);
				}
				else
				{
					removeByElement(datesRenderArr,eventColorRender);
					eventDateRenderArr.push(eventColorRender);
				}				
				startDate=new Date(startDate.getTime()+86400000);
			}			
			var renderValue=sDayobj.month+"/"+sDayobj.day+"/"+sDayobj.year+"-"+eDayobj.month+"/"+eDayobj.day+"/"+eDayobj.year;
		}
		else
		{			
			var impDate = new Date(impDayobj.month+"/"+impDayobj.day+"/"+impDayobj.year);
			var renderValue=(impDate .getMonth()+1)+"/"+impDate.getDate()+"/"+impDate.getFullYear();
			if(existingDataCheck(renderValue,jsObj.task))
				datesRenderArr.push(renderValue);
			else
			{
				removeByElement(eventsRenderArr,renderValue);
				eventDateRenderArr.push(renderValue);
			}
		}
		
		$('#eventDateDetails').dialog("close");
	}*/
	function showEndDateText(val)
	{
		var txtElmt = document.getElementById('ImpEndDateText_new');
		if(val == "No Repeat")
		{
			txtElmt.disabled=true;
		}
		else
			txtElmt.disabled=false;
	}
	
	function showUnEditableSelectedDateEvent(elmtId,eType,taskType,newdate)
	{
		var eid = elmtId.substring((elmtId.indexOf('_')+1),elmtId.length);
		var jsObj={
					eventId:eid,
					currentDay:dateObj.dateVal,
					currentMonth:dateObj.monthVal,
					currentYear:dateObj.yearVal,
					eventType:eType,	
					taskType:taskType,
					newdate:newdate,
					task:"showSelectedDateEvent_nonEditable"
				  };
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = requestPath+'/showImpDateEvent.action?'+rparam;	

		callAjax(jsObj,url);
	}
	
	function showSelectedDateEvent(elmtId,eType,taskType)
	{
		
		var eid = elmtId.substring((elmtId.indexOf('_')+1),elmtId.length);
		if(taskType == "impEvent"){
           var browser2 = window.open("createNewEvent.action?eventId="+eid,"cadreCreateNewEvent","scrollbars=yes,height=600,width=700,left=150,top=100");	
		   browser2.focus();
		   return;
		 }
           
		var jsObj={
					eventId:eid,
					currentDay:dateObj.dateVal,
					currentMonth:dateObj.monthVal,
					currentYear:dateObj.yearVal,
					eventType:eType,	
					taskType:taskType,					
					task:"showSelectedDateEvent"
				  };
		
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url =requestPath+'/showImpDateEvent.action?'+rparam;		
		callAjax(jsObj,url);
	}
	function deleteSelectedEvent(type,eId)
	{	
	
		var jsObj;	
			if(type == 'impDate')
			{
				jsObj = selectedDateObj;
				jsObj.importantDateId = eId;
				jsObj.task="deleteImpDate";
				var status=confirm("Are you sure want to delete this Important Date");	
			}		
			if(status==false){
 				return;
			}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url =requestPath+'/deleteEventAction.action?'+rparam;	
		callAjax(jsObj,url);
			
	}
	function removeDeletedElement(id,jsObj)
	{
	if(eventDateDialog)
			eventDateDialog.hide();
		if(jsObj.task=="deleteImpDate"){
		
		var elmt = document.getElementById("ImpDate_"+myResults);
		alert("Date successfully deleted");
		var parent = elmt.parentNode;
		parent.removeChild(elmt);
		}
		
	}
	
	function buildUnEditableSelectedDateEventPopup(results,jsObj)
	{
		var elmt = document.getElementById('cadreManagementMainDiv');		

		var divChild = document.createElement('div');
		divChild.setAttribute('id','eventDateDetails');
		divChild.setAttribute('class','yui-skin-sam');
		if (jsObj.taskType == "impDate")
		{
			
			if(results[0].startDate)
				var startDateObj = getDateTime(results[0].startDate);
			if(results[0].endDate)
				var endDateObj = getDateTime(results[0].endDate);
			if(results[0].impDate)
				var impDateObj = getDateTime(results[0].impDate);

		}		
		
		var eventStr='';
		eventStr+='<div class="hd" style="color:#2B4E70;"></div> ';
		eventStr+='<div class="bd">'; 
		eventStr+='<table class="selectedDateEvent">';
		eventStr+='<tr>';
		eventStr+='<th>Title</th>';		
		
		if(jsObj.taskType == "impDate")
		{
			eventStr+='<td colspan="3"><span id="" class="fieldSpan" style="height:100px;">'+results[0].title+'</span></td>';					
		}
		eventStr+='</tr>';
		
		
		if(jsObj.taskType == "impDate")
		{
			eventStr+='<tr>';

			eventStr+='<th>Imp date</th>';
			eventStr+='<td><span class="fieldSpan">'+jsObj.newdate+'</span></td>';
			eventStr+='</tr>';
		}

		eventStr+='<tr>';
		eventStr+='<th>Description</th>';
		
		if(jsObj.taskType == "impDate")
		{
			if(results[0].importance != '')
				eventStr+='<td colspan="3"><span class="fieldSpan">'+results[0].importance+'</span></td>';
			else
				eventStr+='<td colspan="3"><span class="fieldSpan"> - </span></td>';
		}
		eventStr+='</tr>';

		
		if(jsObj.taskType == "impDate")
		{
			eventStr+='<tr>';
			eventStr+='<th>Repeat Frequency</th>';
			if(results[0].frequency != null)
				eventStr+='<td colspan="3"><span class="fieldSpan">'+results[0].frequency+'</span></td>';		
			else
				eventStr+='<td colspan="3"><span class="fieldSpan"> - </span></td>';		
			
			if(results[0].endDate)
			{
				eventStr+='<th>Repeat Until</th>';
				eventStr+='<td><span class="fieldSpan">'+endDateObj.day+'/'+endDateObj.month+'/'+endDateObj.year+'</span></td>';
			}
			eventStr+='</tr>';
		}
		eventStr+='</table>';
		eventStr+='</div>';		
		divChild.innerHTML=eventStr;
		elmt.appendChild(divChild);
		if(newDateDialog)
		{
		 newDateDialog.remove();
		}
		newDateDialog = $('#eventDateDetails').dialog({
			width:600,
			'title':'Importent Date Details'
		});
	}
	function buildSelectedDateEventPopup(results,jsObj)
	{
	
		var elmt = document.getElementById('cadreManagementMainDiv');		
		var eventId;
		
		jsonStr = YAHOO.lang.JSON.stringify(results);
		
		var divChild = document.createElement('div');
		divChild.setAttribute('id','eventDateDetails');
		divChild.setAttribute('class','yui-skin-sam');
		
		
		
		if (jsObj.taskType == "impDate")
		{
			eventId = results[0].importantDateId;
			if(results[0].startDate)
				var startDateObj = getDateTime(results[0].startDate);
			if(results[0].endDate)
				var endDateObj = getDateTime(results[0].endDate);
			if(results[0].impDate)
				var impDateObj = getDateTime(results[0].impDate);
		}
			

		var eventStr='';
		eventStr+='<div class="hd" style="color:#2B4E70;"></div> ';
		eventStr+='<div class="bd">'; 
		eventStr+='<div id="errorMesgDIV">&nbsp;</div>';
		eventStr+='<table class="bordered-table">';
		eventStr+='<tr>';
		eventStr+='<th style="width: 140px;">Important Date Title <font style="color:red; font-size: 18px;"> * </font></th>';		
		
		if(jsObj.taskType == "impDate")
		{
			eventStr+='<td colspan="3">';
			eventStr+='<input id="ImpeventNameText" class="fieldSpan" name="srish"style="border-radius: 3px 3px 3px 3px; border: 1px solid rgb(195, 195, 195); width: 218px; height: 35px; margin-bottom: 7px;" onclick="changeToEditableField(this,\'text\',\'impDate\',\'title\')" value="'+results[0].title+'">';
			eventStr+='</td>';					
		}
		eventStr+='</tr>';
		
		
		if(jsObj.taskType == "impDate")
		{
			eventStr+='<tr>';

			eventStr+='<th>Imp date</th>';
			eventStr+='<td>';
			eventStr+='<div><input type="text" id="ImpStartDateText"  name="ImpStartDateText" readonly="readonly" value="'+impDateObj.day+'/'+impDateObj.month+'/'+impDateObj.year+'" /></div>';
			eventStr+='<div id="ImpStartDateText_new_Div" class="tinyDateCal"></div>';
			eventStr+='</td>';			
			eventStr+='</tr>';
		}

		eventStr+='<tr>';
		eventStr+='<th>Description <font style="color:red; font-size: 18px;">  * </font></th>';
		
		if(jsObj.taskType == "impDate")
		{
				eventStr+='<td colspan="3">';
				eventStr+='<textarea rows="5" cols="50" id="ImpdescTextArea1" name="ImpdescTextArea" style="background-color: rgb(255,255,255);" >'+results[0].importance+'</textarea>';
				eventStr+='</td>';				
		}
		eventStr+='</tr>';

		
		if(jsObj.taskType == "impDate")
		{
			eventStr+='<tr>';
			eventStr+='<th>Repeat Frequency</th>';
			if(results[0].frequency != null)
				eventStr+='<td colspan="1"><span class="fieldSpan" onclick="changeToEditableField(this,\'select\',\'impDate\',\'frequency\')">'+results[0].frequency+'</span></td>';
				
			else
				eventStr+='<td colspan="1"><span class="fieldSpan" onclick="changeToEditableField(this,\'select\',\'impDate\',\'frequency\')"> - </span></td>';		
			
			if(results[0].endDate)
			{
			eventStr+='<th>Repeat Until</th>';
				eventStr+='<td>';
				eventStr+='<div><input type="text" id="ImpEndDateText"  name="ImpEndDateText"  readonly="readonly" value="'+impDateObj.day+'/'+impDateObj.month+'/'+impDateObj.year+'" /></div>';
				eventStr+='<div id="ImpEndDateText_Div" class="tinyDateCal"  ></div>';
				eventStr+='</td>';
				
			}
			eventStr+='</tr>';
		}
		
		eventStr+='</table>';
		eventStr+='<div style="background-color: black; width: 648px; margin-left: -10px; height: 1px; margin-bottom: 8px;"> </div>';
		eventStr+='<div class="ui-dialog-buttonset" style="float: right;">';
		eventStr+='<button class="btn btn-primary" type="button" onclick="dateCheck(\''+jsObj.taskType+'\')" style="position: absolute; margin-left: -100px; left: 560px;">Update</button>';
		eventStr+='<button class="btn btn-primary" type="button" onClick="cancelButton()" style="position: relative; left: -15px;">Cancel</button>';	
		eventStr+='</div>';
		eventStr+='</div>';		
		divChild.innerHTML=eventStr;

		elmt.appendChild(divChild);
		
		if(newDateDialog)
		{
		 newDateDialog.remove();
		}
		var newDate = new Date();
		var day = newDate.getDate();
		var month = newDate.getMonth()+1;
		var year = newDate.getFullYear();
		if(day < 9) 
		day = 0+''+day;
		if(month < 9)
		month = 0+''+month;
		var date = day+"/"+month+"/"+year;
		newDateDialog = $('#eventDateDetails').dialog({
			width:650,
			'title':'Update Important Details'
		});
		$("#ImpStartDateText" ).datepicker({
			dateFormat: "dd/mm/yy",
			changeMonth: true,
            changeYear: true,
			minDate: new Date()
			
		});
		$("#ImpEndDateText" ).datepicker({
			dateFormat: "dd/mm/yy",
			changeMonth: true,
            changeYear: true,
			minDate: new Date()
			
		});
	
	}
	
	function dateCheck(task)
	{
		fromDate = $("#ImpStartDateText").val();
		toDate  = $("#ImpEndDateText").val();

	if(fromDate.length > 0 && toDate.length > 0 )
	{
	  
      var dt1  = parseInt(fromDate.substring(0,2),10);
      var mon1 = parseInt(fromDate.substring(3,5),10);
      var yr1  = parseInt(fromDate.substring(6,10),10);
      var dt2  = parseInt(toDate.substring(0,2),10);
      var mon2 = parseInt(toDate.substring(3,5),10);
      var yr2  = parseInt(toDate.substring(6,10),10);
      var date1 = new Date(yr1, mon1, dt1);
      var date2 = new Date(yr2, mon2, dt2);

     if(date2 < date1)
		{ 
		 $('#errorMesgDIV').html("<b><font color='red'>Please Enter a valid Date</font></b>");
		}
	else
		{	
			updateSelectedEvent(task);
			
		}
	}
	}
	function checkDate(task)
	{
	var repeteFrquency = $('#repeatFreqSelect').val();
	if(repeteFrquency == "No Repeat")
	{
	}
	else{
	startDate = $('#ImpStartDateText_new').val();
	endDate = $('#ImpEndDateText_new').val();
	  var dt1  = parseInt(startDate.substring(0,2),10);
      var mon1 = parseInt(startDate.substring(3,5),10);
      var yr1  = parseInt(startDate.substring(6,10),10);
      var dt2  = parseInt(endDate.substring(0,2),10);
      var mon2 = parseInt(endDate.substring(3,5),10);
      var yr2  = parseInt(endDate.substring(6,10),10);
      var date1 = new Date(yr1, mon1, dt1);
      var date2 = new Date(yr2, mon2, dt2);
	
	 if(date2 < date1)
		{ 
		 $('#errorMesgDIV').html("<b><font color='red'>Please Enter a valid Date</font></b>");
		 return true;
		}
	return 	false;
	}
}
function cancelButton()
	{
		$('#eventDateDetails').dialog("close");
	}	
function showDateCal(id,val)
	{
		var date = (new Date().getMonth()+1)+"/"+new Date().getDate()+"/"+ new Date().getFullYear();
		
		if(dateCalendar)
			dateCalendar.destroy();
		
		var navConfig = { 
	      strings : { 
	          month: "Choose Month", 
	          year: "Enter Year", 
	          submit: "OK", 
	          cancel: "Cancel",  
	          invalidYear: "Please enter a valid year" 
	      }, 
	      monthFormat: YAHOO.widget.Calendar.SHORT, 
	      initialFocus: "year" 
	}; 

		dateCalendar = new YAHOO.widget.Calendar(id, {navigator:navConfig, mindate: date, title:"Choose a date:", close:true }); 
		dateCalendar.selectEvent.subscribe(displayDateText, dateCalendar, true); 		
		dateCalendar.render(); 
		dateCalendar.show();		
	}
function displayDateText(type,args,obj)
	{	
		var divId = obj.containerId;		
		var subStr = divId.substring(0,divId.lastIndexOf('_'));	
				
		var selected = args[0]; 
		var selDate = this.toDate(selected[0]); 

		var calDateResult = dateToLocaleString(selDate, this);
		
		var divElmt = document.getElementById(divId);
		var elmt = document.getElementById(subStr);
		
		if(elmt)
		{
			elmt.value = calDateResult;
		}
		
		if(subStr == "startDateText")
		{
			var EendElmt = document.getElementById("endDateText");
			if(EendElmt)
				EendElmt.value = calDateResult;
		}
		else if(subStr == "ImpStartDateText")
		{
			var IendElmt = document.getElementById("ImpEndDateText");
			if(IendElmt)
				IendElmt.value = calDateResult;
		}
		divElmt.style.display='none';
	}
	function dateToLocaleString(dt, cal)
	{ 
		 
		var dStr = dt.getDate(); 
		var mStr = dt.getMonth()+1; 
		var yStr = dt.getFullYear();
		
		return (dStr + "/" + mStr + "/" + yStr); 
	} 
	