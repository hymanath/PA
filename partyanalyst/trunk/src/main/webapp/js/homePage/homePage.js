
var statesInCountryObj = new Array();
var assemblyResultsArray = new Array();
var parliamentResultsArray = new Array();
var MPTCResultsArray = new Array();
var ZPTCResultsArray = new Array();

var selectedState = '';

function initializeHomePage()
{
	getDistrictsComboBoxForAState(1, 'districtList_d');
	getRecentElectionsInState(1);
	
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
	var constSelectEl = document.getElementById("constituency");
	var alertEl = document.getElementById("alertMessage");
	var constSelectElVal = constSelectEl.options[constSelectEl.selectedIndex].value
	alertEl.innerHTML = '';
	if(constSelectElVal == 0)
	{
		alertEl.innerHTML = errotMsg;
		return;
	}
	window.location = "constituencyPageAction.action?constituencyId="+constSelectElVal;
	
}
function hideUnhideSelectBox(radioElement, selectElement)
{
	var tableEl = document.getElementById("constTable");
	var stateTableEl = document.getElementById("stateTable");
	var stateSelectEl = document.getElementById("stateList_c");
	var stateId = stateSelectEl.options[stateSelectEl.selectedIndex].value;
	var alertEl = document.getElementById("alertMessage");
	alertEl.innerHTML = '';
	if(radioElement == 'a_radio')
	{
		
		if(stateTableEl.style.display == 'none')
		{
			stateTableEl.style.display = 'block';
		}
		if(tableEl.style.display == 'none')
		{
			tableEl.style.display = 'block';
		}
		/*election type 2 for mla const*/
		getAllConstituenciesInStateByType(2,stateId,selectElement);
	} else if(radioElement == 'p_radio')
	{
		 /*election type 1 for mla const*/
		
		if(stateTableEl.style.display == 'block')
		{
			stateTableEl.style.display = 'none';
		}
		if(tableEl.style.display == 'none')
		{
			tableEl.style.display = 'block';
		}
		getAllParliamentConstInCountry(selectElement);
	}
	 
}
function getRecentElectionsInState(stateId)
{
			var jsObj = {
					stateId: stateId,
					task: "getRecentElectionsInState"
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
				var url = "getRecentElectionsInState.action?"+rparam; 
				homePageAjaxCall(rparam,jsObj,url);		 
}

function homePageAjaxCall(param,jsObj,url){
	var myResults;
		
		var callback = {			
		               success : function( o ) 
					  {
						try {			
								if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task == "getRecentElectionsInState")
								{									
									showResults(myResults);
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
function showResults(results)
{
	
	if(results.length == 0 || results == null)
		elmt.innerHTML = 'No Results To Display';

	var stateSelectEl = document.getElementById("stateList_s");
	var stateSelectElVal = stateSelectEl.options[stateSelectEl.selectedIndex].text;
	selectedState = stateSelectElVal;

	for(var i=0;i<results.length;i++)
	{
		if(results[i].electionType == "Assembly")
			assemblyResultsArray.push(results[i]);
		else if(results[i].electionType == "Parliament")
			parliamentResultsArray.push(results[i]);
		else if(results[i].electionType == "MPTC")
			MPTCResultsArray.push(results[i]);
		else if(results[i].electionType == "ZPTC")
			ZPTCResultsArray.push(results[i]);
	}

	buildResultsHTMLTable(assemblyResultsArray);
	
}

function buildResultsTableForElectionType(elecType)
{
	if(elecType == 'assembly')
		buildResultsHTMLTable(assemblyResultsArray);
	else if(elecType == 'parliament')
		buildResultsHTMLTable(parliamentResultsArray);

}

function buildResultsHTMLTable(arr)
{
	var elmt = document.getElementById("electionTrendzDiv_main");
	
	if(!elmt)
		return;

	var navigationArr = new Array();

	var str = '';	
	str += '<div id="jQuerySliderDiv" class="slider yui-skin-sam">';
	str += '<ul>';
	for(var i=0;i<arr.length;i++)
	{
		navigationArr.push(''+(i+1));
		str += ' <li>';
		str += '<div class="resultsHeading">';
		str += '<table>';
		str += '<tr>';
		str += '<td><img src="images/icons/indexPage/listIcon.png"/></td>';
		str += '<td>'+selectedState+' '+arr[i].electionType+' ['+arr[i].electionSubtype+'] Election Results In Year '+arr[i].year+'</td>';
		str += '</tr>';
		str += '</table>';
		str += '</div>';
		str += '<div id="electionTrendzBodyDiv_'+i+'" >';
		str += '<table id="electionTrendzBodyTable_'+i+'">';
		for(var j=0;j<arr[i].partyResultsVO.length;j++)
		{
			str += '<tr>';
			str += '<td>'+arr[i].partyResultsVO[j].partyName+'</td>';
			str += '<td>'+arr[i].partyResultsVO[j].totalSeatsWon+'</td>';
			str += '<td> <img height="30" width="40" src="images/party_flags/'+arr[i].partyResultsVO[j].partyFlag+'"></td>';
			str += '</tr>';
		}
		str += '</table>';
		str += '</div>';
		str += '</li>';
	}
	str += '</ul>';
	str += '</div>';

	elmt.innerHTML = str;
	
	for(var i=0;i<arr.length;i++)
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("electionTrendzBodyTable_"+i));
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "partyName"
			}, {
				key : "totalSeatsWon",parser:"number"
			}, {
				key : "partyFlag"
			}]
		};

		var resultsColumnDefs = [ {
			key : "partyName",
			parser:"number",
			label : "Party",
			sortable : true
		}, {
			key : "totalSeatsWon",
			label : "Seats Won",
			sortable : true
		}, {
			key : "partyFlag",
			label : "Flag",
			sortable : false
		}];	

		var myConfigs = {
			paginator : new YAHOO.widget.Paginator({
				rowsPerPage: 5
			})
		};
		var myDataTable = new YAHOO.widget.DataTable("electionTrendzBodyDiv_"+i,resultsColumnDefs, resultsDataSource,myConfigs);  
	}
	
	buildSlider(navigationArr);
}

function buildSlider(navArray)
{
	
		$("#jQuerySliderDiv").sudoSlider({ 
			numeric: true,
			fade: true,
			speed:'6000',
			auto:true,
			crossFade: false,
			updateBefore:true,
			prevNext: false,
			startSlide: 1,
			updateBefore: true,			
			numericText:navArray
	   });
	

}

