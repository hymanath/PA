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
			                			alert( "Failed to load result" + o.status + " " + o.statusText);
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
	var phamletFieldEl = document.getElementById("phamletField");
	var pstateFieldEl = document.getElementById("pstateField");
	var pdistrictFieldEl = document.getElementById("pdistrictField");
	var pconstituencyFieldEl = document.getElementById("pconstituencyField");
	var pmandalFieldEl = document.getElementById("pmandalField");
	var pvillageFieldEl = document.getElementById("pvillageField");
	var probSourceEl = document.getElementById("probSourceEl");
	var sourceAlertEl = document.getElementById("sourceAlertEl");
	var stateValue = pstateFieldEl.options[pstateFieldEl.selectedIndex].value;
	var hamletValue = phamletFieldEl.options[phamletFieldEl.selectedIndex].value;
	var districtFieldVal = pdistrictFieldEl.options[pdistrictFieldEl.selectedIndex].value;
	var constituencyFieldVal =pconstituencyFieldEl.options[pconstituencyFieldEl.selectedIndex].value; 
	var mandalFieldEl=pmandalFieldEl.options[pmandalFieldEl.selectedIndex].value;
	var villageFieldEl=pvillageFieldEl.options[pvillageFieldEl.selectedIndex].value;
		
	var alertEl = document.getElementById("locationAlert");
	var sourceAlertEl = document.getElementById("sourceAlert");
	var flag;
	if(stateValue == '0'|| hamletValue == '0' || districtFieldVal == '0' || constituencyFieldVal == '0' || mandalFieldEl == '0' || villageFieldEl == '0') 
	{		
		alertEl.innerHTML = '';
		alertEl.innerHTML = 'Please select valid location';
		flag=false;
			
	}else {
		
		flag=true;
	}
		return flag;
}


function refreshParentWindow()
{
	incrementHidden();
	window.opener.document.location.reload(true);
	window.close();

}