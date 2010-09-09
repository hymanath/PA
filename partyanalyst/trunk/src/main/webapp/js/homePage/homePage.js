
var statesInCountryObj = new Array();

function initializeHomePage()
{
	getDistrictsComboBoxForAState(1, 'districtList_d');
	
}
function navigateToStatePage()
{
	var stateSelectEl = document.getElementById("stateList_s");
	var stateSelectElVal = stateSelectEl.options[stateSelectEl.selectedIndex].value
	window.location="statePageAction.action?stateId="+stateSelectElVal; 
}
function navigateToDistrictPage()
{
	var distSelectEl = document.getElementById("districtList_d");
	var distSelectElVal = distSelectEl.options[distSelectEl.selectedIndex].value;
	var distSelectElText =  distSelectEl.options[distSelectEl.selectedIndex].text;
	window.location="districtPageAction.action?districtId="+distSelectElVal+"&districtName="+distSelectElText;
	
}
function navigateToConstituencyPage()
{
	var stateSelectEl = document.getElementById("stateList_c");
	var stateSelectElVal = stateSelectEl.options[stateSelectEl.selectedIndex].value
	window.location = "constituencyPageAction.action?constituencyId="+stateSelectElVal; 	
}
function hideUnhideSelectBox(radioElement, selectElement)
{
	var tableEl = document.getElementById("constTable");
	var stateSelectEl = document.getElementById("stateList_c");
	var stateId = stateSelectEl.options[stateSelectEl.selectedIndex].value;
	
	if(radioElement == 'a_radio')
	{
		// election type 2 for mla const
		getAllConstituenciesInStateByType(2,stateId,selectElement);
	} else if(radioElement == 'p_radio')
	{
		// election type 1 for mp const
		getAllConstituenciesInStateByType(1,stateId,selectElement);
	}
	if(tableEl.style.display == 'none')
	{
		tableEl.style.display = 'block';
	} 
}