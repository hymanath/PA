var locationDetails={
		stateArr:[],
		districtArr:[],
		constituencyArr:[],
		mandalArr:[],
		villageArr:[],
		hamletArr:[]
		
	};

var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();
var hidden=1;
var maxDate = (new Date().getMonth() + 1) + "/" + new Date().getDate() + "/" + new Date().getFullYear();

function getCurrentDate()
{ 			
	window.document.form.reportedDateField.value = date;
	document.getElementById("nameText").focus();
}

function getPersonDetails(value)
{
	
	var elmt = document.getElementById("personDetailsDiv");
	var nameEl = document.getElementById("personNameField");
	var mobileEl = document.getElementById("mobileField");
	var addressEl = document.getElementById("addressField");
	if(!elmt)
		alert("No div present to display personal details");
	if(value=="EXTERNAL PERSON" || value=="CALL CENTRE") 
		{		
			elmt.style.display = 'block';
			nameEl.value='';
			mobileEl.value = '';
			addressEl.value = '';
			
		}
	else
		{	
			elmt.style.display = 'none';
			nameEl.value='name';
			mobileEl.value = '0000000000';
			addressEl.value = 'address';
		}
}
function callAjax(param,jsObj,url){
	var myResults;
					
		var callback = {			
		               success : function( o ) 
					  {
						try {												
								if(o.responseText)
									myResults = YAHOO.lang.JSON.parse(o.responseText);
								var ajaxImgSpanElmt = document.getElementById("ajaxImgSpan");
								if(ajaxImgSpanElmt.style.display == 'block')
									{
									ajaxImgSpanElmt.style.display = 'none';
									}
								
								if(jsObj.task == "findTownships")
								{
									fillMandalOptions(myResults);
								} else if(jsObj.location == "mandal")
								{
									fillTehsilOptions(myResults);
								} else if(jsObj.location == "constituency")
								{
									fillConstituenciesOptions(myResults);
								} else if(jsObj.location == "hamlet")
								{
									fillHamletOptions(myResults);
								} if(jsObj.task == "getDistricts")
								{
									fillDistrictsOptions(myResults);
								}
																	
							}  
						catch (e)
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
function getTownshipsForMandal(name,value,choice)
{
	var alertEl = document.getElementById("locationAlert");
	alertEl.innerHTML = '';
	if(value == 0)
	{
		alertEl.innerHTML ='Please Select Valid Location';
		return;
	}
	var ajaxImgSpanElmt = document.getElementById("ajaxImgSpan");
	if(ajaxImgSpanElmt.style.display == 'none')
		{
		ajaxImgSpanElmt.style.display = 'block';
		}
	var pvillageFieldEl = document.getElementById("pvillageField");
	var pvillageFieldElOptions = pvillageFieldEl.options;
	
	var phamletFieldEl = document.getElementById("phamletField");
	var phamletFieldElOptions = phamletFieldEl.options;

	if(pvillageFieldElOptions.length != 0)
	{
		pvillageFieldEl.selectedIndex= '0';
	}
	
	if(phamletFieldElOptions.length != 0)
	{
		phamletFieldEl.selectedIndex= '0';
	}
		var jsObj=
		{
				type:"cadreDetails",
				reportLevel:name,
				selected:value,
				changed:choice,
				task:"findTownships"				
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "townshipsForMandalAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
}
function fillMandalOptions(results)
{
	var pvillageFieldEl = document.getElementById("pvillageField");
	removeSelectElements(pvillageFieldEl);
	
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
		try
			{
			pvillageFieldEl.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
			pvillageFieldEl.add(opElmt); // IE only
			}
	}
}
function removeSelectElements(elmt)
{
	if(!elmt)
		return;

	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
}
function getHamletList(name,value,choice)
{	
	var alertEl = document.getElementById("locationAlert");
	alertEl.innerHTML = '';
	if(value == 0)
	{
		alertEl.innerHTML ='Please Select Valid Location';
		return;
	}
	var ajaxImgSpanElmt = document.getElementById("ajaxImgSpan");
	if(ajaxImgSpanElmt.style.display == 'none')
		{
		ajaxImgSpanElmt.style.display = 'block';
		}
	var phamletFieldEl = document.getElementById("phamletField");
	var phamletFieldElOptions = phamletFieldEl.options;
	
	if(phamletFieldElOptions.length != 0)
	{
		phamletFieldEl.selectedIndex= '0';
	}	
	
	var jsObj=
		{
				type:"cadreDetails",
				reportLevel:name,
				selected:value,
				changed:choice,
				location: "hamlet"
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "cadreRegisterAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
}
function fillHamletOptions(results)
{
	var phamletFieldEl = document.getElementById("phamletField");
	removeSelectElements(phamletFieldEl);
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
		try
			{
			phamletFieldEl.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
			phamletFieldEl.add(opElmt); // IE only
			}
	}
}

// Array of max days in month in a year and in a leap year
monthMaxDays= [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
monthMaxDaysLeap= [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
hideSelectTags = [];
function getRealYear(dateObj)
{
	return (dateObj.getYear() % 100) + (((dateObj.getYear() % 100) < 39) ? 2000 : 1900);
}

function getDaysPerMonth(month, year)
{
	/* 
	Check for leap year. These are some conditions to check year is leap year or not...
	1.Years evenly divisible by four are normally leap years, except for... 
	2.Years also evenly divisible by 100 are not leap years, except for... 
	3.Years also evenly divisible by 400 are leap years. 
	*/
	if ((year % 4) == 0)
	{
		if ((year % 100) == 0 && (year % 400) != 0)
			return monthMaxDays[month];
	
		return monthMaxDaysLeap[month];
	}
	else
		return monthMaxDays[month];
}

function createCalender(year, month, day)
{
	 // current Date
	var curDate = new Date();
	var curDay = curDate.getDate();
	var curMonth = curDate.getMonth();
	var curYear = getRealYear(curDate)

	 // if a date already exists, we calculate some values here
	if (!year)
	{
		var year = curYear;
		var month = curMonth;
	}

	var yearFound = 0;
	for (var i=0; i<document.getElementById('selectYear').options.length; i++)
	{
		if (document.getElementById('selectYear').options[i].value == year)
		{
			document.getElementById('selectYear').selectedIndex = i;
			yearFound = true;
			break;
		}
	}
	if (!yearFound)
	{
		document.getElementById('selectYear').selectedIndex = 0;
		year = document.getElementById('selectYear').options[0].value;		
	}
	document.getElementById('selectMonth').selectedIndex = month;

	 // first day of the month.
	var fristDayOfMonthObj = new Date(year, month, 1);
	var firstDayOfMonth = fristDayOfMonthObj.getDay();

	continu		= true;
	firstRow	= true;
	var x	= 0;
	var d	= 0;
	var trs = []
	var ti = 0;
	while (d <= getDaysPerMonth(month, year))
	{
		if (firstRow)
		{
			trs[ti] = document.createElement("TR");
			if (firstDayOfMonth > 0)
			{
				while (x < firstDayOfMonth)
				{
					trs[ti].appendChild(document.createElement("TD"));
					x++;
				}
			}
			firstRow = false;
			var d = 1;
		}
		if (x % 7 == 0)
		{
			ti++;
			trs[ti] = document.createElement("TR");
		}
		if (day && d == day)
		{
			var setID = 'calenderChoosenDay';
			var styleClass = 'choosenDay';
			var setTitle = 'this day is currently selected';
		}
		else if (d == curDay && month == curMonth && year == curYear)
		{
			var setID = 'calenderToDay';
			var styleClass = 'toDay';
			var setTitle = 'this day today';
		}
		else
		{
			var setID = false;
			var styleClass = 'normalDay';
			var setTitle = false;
		}
		var td = document.createElement("TD");
		td.className = styleClass;
		if (setID)
		{
			td.id = setID;
		}
		if (setTitle)
		{
			td.title = setTitle;
		}
		td.onmouseover = new Function('highLiteDay(this)');
		td.onmouseout = new Function('deHighLiteDay(this)');
		if (targetEl)
			td.onclick = new Function('pickDate('+year+', '+month+', '+d+')');
		else
			td.style.cursor = 'default';
		td.appendChild(document.createTextNode(d));
		trs[ti].appendChild(td);
		x++;
		d++;
	}
	return trs;
}

function showCalender(elPos, tgtEl)
{
	targetEl = false;

	if (document.getElementById(tgtEl))
	{
		targetEl = document.getElementById(tgtEl);
	}
	else
	{
		if (document.forms[0].elements[tgtEl])
		{
			targetEl = document.forms[0].elements[tgtEl];
		}
	}
	var calTable = document.getElementById('calenderTable');

	var positions = [0,0];
	var positions = getParentOffset(elPos, positions);	
	calTable.style.left = positions[0]+'px';		
	calTable.style.top = positions[1]+'px';			

	calTable.style.display='block';

	var matchDate = new RegExp('^([0-9]{2})-([0-9]{2})-([0-9]{4})$');
	var m = matchDate.exec(targetEl.value);
	if (m == null)
	{
		trs = createCalender(false, false, false);
		showCalenderBody(trs);
	}
	else
	{
		if (m[1].substr(0, 1) == 0)
			m[1] = m[1].substr(1, 1);
		if (m[2].substr(0, 1) == 0)
			m[2] = m[2].substr(1, 1);
		m[2] = m[2] - 1;
		trs = createCalender(m[3], m[2], m[1]);
		showCalenderBody(trs);
	}

	hideSelect(document.body, 1);
}
function showCalenderBody(trs)
{
	var calTBody = document.getElementById('calender');
	while (calTBody.childNodes[0])
	{
		calTBody.removeChild(calTBody.childNodes[0]);
	}
	for (var i in trs)
	{
		calTBody.appendChild(trs[i]);
	}
}
function setYears(sy, ey)
{
	 // current Date
	var curDate = new Date();
	var curYear = getRealYear(curDate);
	if (sy)
		startYear = curYear;
	if (ey)
		endYear = curYear;
	document.getElementById('selectYear').options.length = 0;
	var j = 0;
	for (y=ey; y>=sy; y--)
	{
		document.getElementById('selectYear')[j++] = new Option(y, y);
	}
}
function hideSelect(el, superTotal)
{
	if (superTotal >= 100)
	{
		return;
	}

	var totalChilds = el.childNodes.length;
	for (var c=0; c<totalChilds; c++)
	{
		var thisTag = el.childNodes[c];
		if (thisTag.tagName == 'SELECT')
		{
			if (thisTag.id != 'selectMonth' && thisTag.id != 'selectYear')
			{
				var calenderEl = document.getElementById('calenderTable');
				var positions = [0,0];
				var positions = getParentOffset(thisTag, positions);	// nieuw
				var thisLeft	= positions[0];
				var thisRight	= positions[0] + thisTag.offsetWidth;
				var thisTop	= positions[1];
				var thisBottom	= positions[1] + thisTag.offsetHeight;
				var calLeft	= calenderEl.offsetLeft;
				var calRight	= calenderEl.offsetLeft + calenderEl.offsetWidth;
				var calTop	= calenderEl.offsetTop;
				var calBottom	= calenderEl.offsetTop + calenderEl.offsetHeight;

				if (
					(
						/* check if it overlaps horizontally */
						(thisLeft >= calLeft && thisLeft <= calRight)
							||
						(thisRight <= calRight && thisRight >= calLeft)
							||
						(thisLeft <= calLeft && thisRight >= calRight)
					)
						&&
					(
						/* check if it overlaps vertically */
						(thisTop >= calTop && thisTop <= calBottom)
							||
						(thisBottom <= calBottom && thisBottom >= calTop)
							||
						(thisTop <= calTop && thisBottom >= calBottom)
					)
				)
				{
					hideSelectTags[hideSelectTags.length] = thisTag;
					thisTag.style.display = 'none';
				}
			}

		}
		else if(thisTag.childNodes.length > 0)
		{
			hideSelect(thisTag, (superTotal+1));
		}
	}
}
function closeCalender()
{
	for (var i=0; i<hideSelectTags.length; i++)
	{
		hideSelectTags[i].style.display = 'block';
	}
	hideSelectTags.length = 0;
	document.getElementById('calenderTable').style.display='none';
}
function highLiteDay(el)
{
	el.className = 'hlDay';
}
function deHighLiteDay(el)
{
	if (el.id == 'calenderToDay')
		el.className = 'toDay';
	else if (el.id == 'calenderChoosenDay')
		el.className = 'choosenDay';
	else
		el.className = 'normalDay';
}
function pickDate(year, month, day)
{
	month++;

	if (!targetEl)
	{
		alert('target for date is not set yet');
	}
	else
	{
		targetEl.value= day+'/'+month+'/'+year;
		closeCalender();
	}
}
function getParentOffset(el, positions)
{
	positions[0] += el.offsetLeft;
	positions[1] += el.offsetTop;
	if (el.offsetParent)
		positions = getParentOffset(el.offsetParent, positions);
	return positions;
}

function displayDateText(type, args, obj) {

	var txtDate1 = document.getElementById("existingFromText");
	txtDate1.value = day + "/" + month + "/" + year;
	minDate = month + "/" + day + "/" + year;
}
function getMandalList(name,value,choice)
{
	var alertEl = document.getElementById("locationAlert");
	alertEl.innerHTML = '';
	if(value == 0)
	{
		alertEl.innerHTML ='Please Select Valid Location';
		return;
	}
	var ajaxImgSpanElmt = document.getElementById("ajaxImgSpan");
	if(ajaxImgSpanElmt.style.display == 'none')
		{
		ajaxImgSpanElmt.style.display = 'block';
		}
	var pmandalFieldEl = document.getElementById("pmandalField");
	var pmandalFieldElOptions = pmandalFieldEl.options;
	
	var pvillageFieldEl = document.getElementById("pvillageField");
	var pvillageFieldElOptions = pvillageFieldEl.options;
	
	var phamletFieldEl = document.getElementById("phamletField");
	var phamletFieldElOptions = phamletFieldEl.options;

	if(pmandalFieldElOptions.length != 0)
	{
		pmandalFieldEl.selectedIndex= '0';
	}
	
	if(pvillageFieldElOptions.length != 0)
	{
		pvillageFieldEl.selectedIndex= '0';
	}
	
	if(phamletFieldElOptions.length != 0)
	{
		phamletFieldEl.selectedIndex= '0';
	}
	var jsObj=
		{
				type:"cadreDetails",
				reportLevel:"Constituencies",
				selected:value,
				changed:choice,
				location: "mandal"
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
	var url = "cadreRegisterAjaxAction.action?"+rparam;
	callAjax(rparam,jsObj,url);
}
function fillTehsilOptions(results)
{
	var pmandalFieldEl = document.getElementById("pmandalField");
	removeSelectElements(pmandalFieldEl);
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
		try
			{
			pmandalFieldEl.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
			pmandalFieldEl.add(opElmt); // IE only
			}
	}
}
function getConstituencyList(name,value,choice)
{
	var alertEl = document.getElementById("locationAlert");
	alertEl.innerHTML = '';
	if(value == 0)
	{
		alertEl.innerHTML ='Please Select Valid Location';
		return;
	}
	var ajaxImgSpanElmt = document.getElementById("ajaxImgSpan");
	if(ajaxImgSpanElmt.style.display == 'none')
		{
		ajaxImgSpanElmt.style.display = 'block';
		}
	var pconstituencyFieldEl = document.getElementById("pconstituencyField");
	var pconstituencyFieldElOptions = pconstituencyFieldEl.options;
	
	var pmandalFieldEl = document.getElementById("pmandalField");
	var pmandalFieldElOptions = pmandalFieldEl.options;
	
	var pvillageFieldEl = document.getElementById("pvillageField");
	var pvillageFieldElOptions = pvillageFieldEl.options;
	
	var phamletFieldEl = document.getElementById("phamletField");
	var phamletFieldElOptions = phamletFieldEl.options;
	
	if(pconstituencyFieldElOptions.length != 0)
	{
		pconstituencyFieldEl.selectedIndex= '0';
	}
	
	if(pmandalFieldElOptions.length != 0)
	{
		pmandalFieldEl.selectedIndex= '0';
	}
	
	if(pvillageFieldElOptions.length != 0)
	{
		pvillageFieldEl.selectedIndex= '0';
	}
	
	if(phamletFieldElOptions.length != 0)
	{
		phamletFieldEl.selectedIndex= '0';
	}
	
	var jsObj=
		{
				type:"cadreDetails",
				reportLevel:"constituency",
				selected:value,
				changed:choice,
				location: "constituency"
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
	var url = "cadreRegisterAjaxAction.action?"+rparam;
	callAjax(rparam,jsObj,url);
}
function fillConstituenciesOptions(results)
{
	var pconstituencyFieldEl = document.getElementById("pconstituencyField");
	removeSelectElements(pconstituencyFieldEl);
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
		try
			{
			pconstituencyFieldEl.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
			pconstituencyFieldEl.add(opElmt); // IE only
			}
	}
}
function getDistrictList(value)
{
	var alertEl = document.getElementById("locationAlert");
	alertEl.innerHTML = '';
	if(value == 0)
	{
		alertEl.innerHTML ='Please Select Valid Location';
		return;
	}	
	var ajaxImgSpanElmt = document.getElementById("ajaxImgSpan");
	if(ajaxImgSpanElmt.style.display == 'none')
		{
		ajaxImgSpanElmt.style.display = 'block';
		}
	var pdistrictFieldEl = document.getElementById("pdistrictField");
	var pdistrictFieldElOptions = pdistrictFieldEl.options;
	
	var pconstituencyFieldEl = document.getElementById("pconstituencyField");
	var pconstituencyFieldElOptions = pconstituencyFieldEl.options;
	
	var pmandalFieldEl = document.getElementById("pmandalField");
	var pmandalFieldElOptions = pmandalFieldEl.options;
	
	var pvillageFieldEl = document.getElementById("pvillageField");
	var pvillageFieldElOptions = pvillageFieldEl.options;
	
	var phamletFieldEl = document.getElementById("phamletField");
	var phamletFieldElOptions = phamletFieldEl.options;
	
	if(pdistrictFieldElOptions.length != 0)
	{
		pdistrictFieldEl.selectedIndex= '0';
	}
	
	if(pconstituencyFieldElOptions.length != 0)
	{
		pconstituencyFieldEl.selectedIndex= '0';
	}
	
	if(pmandalFieldElOptions.length != 0)
	{
		pmandalFieldEl.selectedIndex= '0';
	}
	
	if(pvillageFieldElOptions.length != 0)
	{
		pvillageFieldEl.selectedIndex= '0';
	}
	
	if(phamletFieldElOptions.length != 0)
	{
		phamletFieldEl.selectedIndex= '0';
	}
	
	
	var jsObj=
		{
				id: value,
				task: "getDistricts" 
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
	var url = "addNewProblemAjaxAction.action?"+rparam;
	callAjax(rparam,jsObj,url);
}
function fillDistrictsOptions(results)
{
	var pdistrictFieldEl = document.getElementById("pdistrictField");
	removeSelectElements(pdistrictFieldEl);
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
		try
			{
			pdistrictFieldEl.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
			pdistrictFieldEl.add(opElmt); // IE only
			}
	}
}
function validateInput(value)
{
	var alertEl = document.getElementById("locationAlert");
	alertEl.innerHTML = '';
	if(value == 0)
	{
		alertEl.innerHTML ='Please Select Valid Location';
		return;
	}
}
function validateClientSide()
{
	var pstateFieldEl = document.getElementById("stateField");
	var pdistrictFieldEl = document.getElementById("districtField");
	var pconstituencyFieldEl = document.getElementById("constituencyField");
	var pmandalFieldEl = document.getElementById("mandalField");
	var pvillageFieldEl = document.getElementById("hamletField");
	
	
	var stateValue = pstateFieldEl.options[pstateFieldEl.selectedIndex].value;
	var districtFieldVal = pdistrictFieldEl.options[pdistrictFieldEl.selectedIndex].value;
	var constituencyFieldVal =pconstituencyFieldEl.options[pconstituencyFieldEl.selectedIndex].value; 
	var mandalFieldEl=pmandalFieldEl.options[pmandalFieldEl.selectedIndex].value;
	var villageFieldEl=pvillageFieldEl.options[pvillageFieldEl.selectedIndex].value;
		
	var alertEl = document.getElementById("locationAlert");
	var sourceAlertEl = document.getElementById("sourceAlert");
	var flag;
	alertEl.innerHTML = '';
	if(stateValue == '0'|| hamletValue == '0' || districtFieldVal == '0' || constituencyFieldVal == '0' || mandalFieldEl == '0' || villageFieldEl == '0') 
	{		
		
		alertEl.innerHTML = 'Please select valid location';
		flag=false;
			
	}else {
		
		flag=true;
	}
	
		return flag;
}

function populateLocations(val,source)
{
	if(val == 0)
	{
		alert("Select valid Problem Scope");
		return;
	}
	var row1El = document.getElementById("row1");
	var row2El = document.getElementById("row2");
	var row3El = document.getElementById("row3");
	var row4El = document.getElementById("row4");
	var row5El = document.getElementById("row5");
	var row6El = document.getElementById("row6");
	var hiddenEl = document.getElementById("problemLocation");
	var stateFieldEl = document.getElementById("stateField_s");
	var districtFieldEl = document.getElementById("districtField_s"); 
	var constituencyFieldEl = document.getElementById("constituencyField_s");
	var mandalFieldEl = document.getElementById("mandalField_s");
	var selectedConst = 0;
	var selectedDistrict = 0;
	
	var hamletFieldEl = document.getElementById("hamletField_s");
	var pConstituencyFieldEl = document.getElementById("pConstituencyField_s");
	var boothFieldEl = document.getElementById("boothField_s");
	row1El.style.display = 'none';
	row2El.style.display = 'none';
	row3El.style.display = 'none';
	row4El.style.display = 'none';
	row5El.style.display = 'none';
	row6El.style.display = 'none';
	
	if(districtFieldEl && districtFieldEl.options.length > 0)
		selectedDistrict = districtFieldEl.options[districtFieldEl.selectedIndex].value;	
	if(constituencyFieldEl.options.length > 0)
		 selectedConst = constituencyFieldEl.options[constituencyFieldEl.selectedIndex].value; 
	
	if(source == 'onChange')
	{	
		hiddenEl.value='';
		if(scope == 2)
		{
			
		} else if(scope == 3)
		{
			constituencyFieldEl.selectedIndex = '0';
			mandalFieldEl.selectedIndex = '0';
			hamletFieldEl.selectedIndex = '0';
			boothFieldEl.selectedIndex = '0';
			getSubRegionsInDistrict(selectedDistrict,'newProblemPost','constituencyField_s','currentAdd')
			
		} else if (scope == 4)
		{
			mandalFieldEl.selectedIndex = '0';
			hamletFieldEl.selectedIndex = '0';
			boothFieldEl.selectedIndex = '0';			
			getSubRegionsInConstituency(selectedConst,'newProblemPost','mandalField_s','currentAdd');		
			
		} else if (scope == 7)
		{
			
		}			
		
	} else if(source == "onLoad")
		{
		setLocationValue(accessValue,'onChange');
			if(val == 9)
			{
				mandalField_sVal = mandalFieldEl.options[mandalFieldEl.selectedIndex].text;
				var flag = mandalField_sVal.search("Greater Municipal Corp");
				if(flag == '-1')
				{
					if(row6El.style.display == 'none')
						row6El.style.display = '';						
				} else {
					if(row5El.style.display == 'none')
						row5El.style.display = '';
					if(row6El.style.display == 'none')
						row6El.style.display = '';
				}
			}
		}
	var value = val;
	if(value == 1)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		
	} else if(value == 2)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			
	} else if(value == 3)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';					
	} else if(value == 4)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';			
	} else if(value == 5)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';				
	} else if(value == 6)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		if(row5El.style.display == 'none')
			row5El.style.display = '';			
	} else if(value == 7)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';				
	} else if(value == 8)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		if(row5El.style.display == 'none')
			row5El.style.display = '';			
	} else if(value == 9)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';			
	}		 
}

function setLocationValue(value, source)
{
	if(value == '0')
	{
		alert("Please Select Valid Location");
		return;
	}	
	var hiddenEl = document.getElementById("problemLocation");
	var scopeLevelEl = document.getElementById("scopeLevel");
	var stateFieldEl = document.getElementById("stateField_s");
	var districtFieldEl = document.getElementById("districtField_s");
	var constituencyFieldEl = document.getElementById("constituencyField_s");
	var mandalFieldEl = document.getElementById("mandalField_s");
	var hamletFieldEl = document.getElementById("hamletField_s");
	var boothFieldEl = document.getElementById("boothField_s");

	hiddenEl.value = '';
	var scopeLevelElVal = scopeLevelEl.options[scopeLevelEl.selectedIndex].value;
	if(stateFieldEl.options.length > 0)
		var selectedState = stateFieldEl.options[stateFieldEl.selectedIndex].value;
	if(districtFieldEl.options.length > 0)
		var selectedDistrict = districtFieldEl.options[districtFieldEl.selectedIndex].value;
	if(constituencyFieldEl.options.length > 0)
		 var selectedConstituency = constituencyFieldEl.options[constituencyFieldEl.selectedIndex].value;
	if(mandalFieldEl.options.length > 0)
		var selectedMandal = mandalFieldEl.options[mandalFieldEl.selectedIndex].value;
	if(hamletFieldEl.options.length > 0)
		var selectedHamlet = hamletFieldEl.options[hamletFieldEl.selectedIndex].value;
	if(boothFieldEl.options.length > 0)
		var selectedBooth = boothFieldEl.options[boothFieldEl.selectedIndex].value;
	if(source == 'onChange'){

		if(scopeLevelElVal == 2 && selectedState != 0)
		{	
			hiddenEl.value = selectedState;
		}	
		if(scopeLevelElVal == 3 && selectedDistrict != 0)
		{
			hiddenEl.value = selectedDistrict;
		}		
		if(scopeLevelElVal == 4 && selectedConstituency != 0)
		{
			hiddenEl.value = selectedConstituency;
		}	
		if((scopeLevelElVal == 5 || scopeLevelElVal == 7) && selectedMandal != 0)
		{
			hiddenEl.value = selectedMandal;
		}	
		if((scopeLevelElVal == 6 || scopeLevelElVal == 8) && selectedHamlet != 0)
		{
			hiddenEl.value = selectedHamlet;
		}	
		if(scopeLevelElVal == 9 && selectedBooth != 0)
		{
			hiddenEl.value = selectedBooth;
		}				
		
	} else if(source == 'onLoad'){
		return;
	}
	
}

function refreshParentWindow()
{
	incrementHidden();
	
	window.close();

}