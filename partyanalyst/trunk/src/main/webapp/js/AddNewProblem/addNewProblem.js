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
	if(window.document.form.reportedDateField.value ==""){
	  window.document.form.reportedDateField.value = date;
	}
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
function showDateCal() {
	var id = document.getElementById("existingFromText_Div");
	if (dateCalendar)
		dateCalendar.destroy();

	var navConfig = {
		strings : {
			month : "Choose Month",
			year : "Enter Year",
			submit : "OK",
			cancel : "Cancel",
			invalidYear : "Please enter a valid year"
		},
		monthFormat : YAHOO.widget.Calendar.SHORT,
		initialFocus : "year"
	};

	var dateCalendar = new YAHOO.widget.Calendar(id, {
		navigator : navConfig,
		maxdate: maxDate,
		title : "Choose a date:",
		close : true
	});
	dateCalendar.selectEvent.subscribe(displayDateText, dateCalendar, true);
	dateCalendar.render();
	dateCalendar.show();
}
function displayDateText(type, args, obj) {
	var dates = args[0];
	var date = dates[0];
	var year = date[0], month = date[1], day = date[2];
	var divId = obj.containerId;
	var divElmt = document.getElementById(divId);

	var txtDate1 = document.getElementById("existingFromText");
	txtDate1.value = day + "/" + month + "/" + year;
	minDate = month + "/" + day + "/" + year;
	divElmt.style.display = 'none';
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
	//window.opener.document.location.reload(true);

}