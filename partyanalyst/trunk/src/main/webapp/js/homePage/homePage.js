
var statesInCountryObj = new Array();
var assemblyResultsArray = new Array();
var parliamentResultsArray = new Array();
var MPTCResultsArray = new Array();
var ZPTCResultsArray = new Array();
var emptyArray = new Array();

var selectedState = '';
var selectedStateId = '';

function initializeHomePage()
{
	buildLogoImage();	
	buildElectionTrendzTabView();

	var stateEl = document.getElementById("stateList_res");
	var stateSelectElVal = stateEl.options[stateEl.selectedIndex].value;
	getDistrictsComboBoxForAState(1, 'districtList_d');
	getRecentElectionsInState(stateSelectElVal);
	getProblemsInState(stateSelectElVal);
	buildPolls();
}

function buildPolls()
{
	var jsObj=
	{
			task:"getAllPolls"					
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllPolls.action?"+rparam;						
	homePageAjaxCall(rparam,jsObj,url);
}

function buildNewPoll(result){
	
	if(result.quesitons!=null){
		var elmt = document.getElementById("pollsWidgetBody");
		if(!elmt)
			return;
		
		var questionId = '';
		var str = '';
		for(var i=0; i<1;i++)
		{
			questionId = result.quesitons[i].questionId;
			str += '<div id="pollQuestionDiv">Q)';
			str += result.quesitons[i].question;
			str += '</div>';
			str += '<div id="pollOptionsDiv">';
			str += '<table>';
			for(var j=0 ; j<result.quesitons[i].options.length; j++){
				str += '<tr><td><input type="radio" name="pollradio" value="'+result.quesitons[i].options[j].optionId+'">';
				str += result.quesitons[i].options[j].option;
				str += '</td></tr>';			
			}
			str += '</table>';
			str += '</div>';
		}
		
		str += '<div id="pollSubmitDiv">';
		str += '<div onclick="savePollResult(\''+questionId+'\')" class="viewReportButtonSpan" style="left:">';
		str += '	<span class="viewReportButtonLabel"  style="left:20px;top:5px;">Submit</span>';
		str += '</div>';
		str += '</div>';
		str += '</div>';

		elmt.innerHTML = str;

	}
}

function savePollResult(questionId){

	var elmts = document.getElementsByName("pollradio");
	var checkedElmtId = '';
	
	for(var i in elmts)
	{
		if(elmts[i].checked == true)
			checkedElmtId = elmts[i].value;
	}
	var jsObj=
	{
			questionId:questionId,
			selectedPollId:checkedElmtId,
			task:"saveSelectedPoll"					
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveSelectedPoll.action?"+rparam;						
	homePageAjaxCall(rparam,jsObj,url);
	//console.log(questionId ,checkedElmtId);
}


function buildLogoImage()
{
	var elmt = document.getElementById("pa_Logo");

	if(!elmt)
		return;

	var str = '';	
	
	
	if(navigator.appName=="Microsoft Internet Explorer")
		str += '<img src="images/icons/homePage_new/logo.gif">';
	else
		str += '<img src="images/icons/homePage_new/logo.png">';

	elmt.innerHTML = str;
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
function getProblemsInState(stateId)
{
	var jsObj = {
			stateId: stateId,
			task: "getProblemsInState"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
		var url = "getProblemsInState.action?"+rparam; 
		homePageAjaxCall(rparam,jsObj,url);
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
								if(jsObj.task == "getAllPolls")
								{									
									buildNewPoll(myResults);
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

function buildElectionTrendzTabView()
{
	var tabView = new YAHOO.widget.TabView(); 
	
	var content = '';
	content += '<div id="stateSelectionDiv">';
	content += '<table>';
	content += '	<tr>';
	content += '		<th style="color:#213540"> Recent Election Results In :</th>';
	content += '		<td>';
	content += '		<select id="stateList_res" onchange="getRecentElectionsInState(this.options[this.selectedIndex].value)">';
	for(var i in statesInCountryObj)
		content += '		<option value='+statesInCountryObj[i].id+'>'+statesInCountryObj[i].name+'</option>';
	content += '		</select>';
	content += '</td>';
	content += '	</tr>';
	content += '</table>';
	content += '</div>';
	
	var content1 = '';
	content1 += content;
	content1 += '<div id="assemblyElectionTrendzDiv_main">';
	content1 += '</div>';

	var content2 = '';
	//content2 += content;
	content2 += '<div id="parliamentElectionTrendzDiv_main">';
	content2 += '</div>';


	tabView.addTab( new YAHOO.widget.Tab({
        label: 'Assembly',
        content: content1,
        active: true
    }));

    tabView.addTab( new YAHOO.widget.Tab({
        label: 'Parliament',
        content:content2 

    }));
	
	tabView.appendTo('electionTrendzDiv_main'); 

	var tab0 = tabView.getTab(0);
	var tab1 = tabView.getTab(1);

	tab0.addListener('click', handleClick);
	tab1.addListener('click', handleClick);

	function handleClick(e) {  
		if(e.originalTarget.innerHTML == "Assembly")
		{			
			buildResultsHTMLTable(assemblyResultsArray,"assemblyElectionTrendzDiv_main","Assembly");
		}
		else if(e.originalTarget.innerHTML == "Parliament")
		{			
			buildResultsHTMLTable(parliamentResultsArray,"parliamentElectionTrendzDiv_main","Parliament");  
		}
	}
}
function showResults(results)
{
	assemblyResultsArray = new Array();
	parliamentResultsArray = new Array();
	if(results.length == 0 || results == null)
		elmt.innerHTML = 'No Results To Display';

	var stateSelectEl = document.getElementById("stateList_res");
	var stateSelectElVal = stateSelectEl.options[stateSelectEl.selectedIndex].text;
	var stateSelectElId = stateSelectEl.options[stateSelectEl.selectedIndex].value;

	selectedState = stateSelectElVal;
	selectedStateId = stateSelectElId;

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

	buildResultsHTMLTable(assemblyResultsArray,"assemblyElectionTrendzDiv_main","Assembly");
		
	
}

function buildResultsTableForElectionType(elecType)
{
	if(elecType == 'assembly')
		buildResultsHTMLTable(assemblyResultsArray);
	else if(elecType == 'parliament')
		buildResultsHTMLTable(parliamentResultsArray);

}

function buildResultsHTMLTable(arr,divId,elecType)
{
	var elmt = document.getElementById(divId);
	
	if(!elmt)
		return;

	var navigationArr = new Array();

	var str = '';	
	str += '<div id="jQuerySliderDiv_'+elecType+'" class="slider yui-skin-sam">';
	str += '<ul>';
	for(var i=0;i<arr.length;i++)
	{
		navigationArr.push(''+(i+1));
		str += ' <li>';
		str += '<div class="resultsHeading">';
		str += '<table>';
		str += '<tr>';
		str += '<td><img src="images/icons/indexPage/listIcon.png"/></td>';
		if(elecType == "Assembly")
		{
			if(arr[i].electionSubtype == "BYE")
				str += '<td>'+selectedState+' '+arr[i].electionType+' ['+arr[i].electionSubtype+'] Election Results In Year '+arr[i].year+'</td>';
			else
				str += '<td>'+selectedState+' '+arr[i].electionType+' Election Results In Year '+arr[i].year+'</td>';
		}
		else if(elecType == "Parliament")		
		{
			if(arr[i].electionSubtype == "BYE")
				str += '<td>'+arr[i].electionType+' ['+arr[i].electionSubtype+'] Election Results In Year '+arr[i].year+'</td>';
			else
				str += '<td>'+arr[i].electionType+' Election Results In Year '+arr[i].year+'</td>';
		}
		str += '</tr>';
		str += '</table>';
		str += '</div>';
		str += '<div id="electionTrendzBodyDiv_'+elecType+'_'+i+'" >';
		str += '<table id="electionTrendzBodyTable_'+elecType+'_'+i+'">';
		for(var j=0;j<arr[i].partyResultsVO.length;j++)
		{
			if(j == 6)
				break;
			str += '<tr>';
			str += '<td>'+arr[i].partyResultsVO[j].partyName+'</td>';
			str += '<td>'+arr[i].partyResultsVO[j].totalSeatsWon+'</td>';
			str += '<td> <img height="30" width="40" src="images/party_flags/'+arr[i].partyResultsVO[j].partyFlag+'"></td>';
			str += '</tr>';			
		}
		str += '</table>';
		str += '</div>';
		str += '<div id="electionTrendzBodyDiv_'+elecType+'_'+i+'_footer" style="padding:5px;">';
		str += '	<a href="javascript:{}" onclick="showElectionResults('+i+',\''+elecType+'\')" class="viewAncs">View All Party Results</a>';
		str += '		|';
		str += '	<a class="viewAncs" href="electionDetailsReportAction.action?electionId='+arr[i].electionId+'&stateID='+selectedStateId+'&stateName='+selectedState+'&electionType='+arr[i].electionType+'&electionTypeId='+arr[i].electionTypeId+'&year='+arr[i].year+'">Analyze</a>';	
		str += '</div>';
		str += '</li>';
	}
	str += '</ul>';
	str += '</div>';

	elmt.innerHTML = str;
		
	for(var i=0;i<arr.length;i++)
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("electionTrendzBodyTable_"+elecType+"_"+i));
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

		/*var myConfigs = {
			paginator : new YAHOO.widget.Paginator({
				rowsPerPage: 5
			})
		};*/
		var myDataTable = new YAHOO.widget.DataTable("electionTrendzBodyDiv_"+elecType+"_"+i,resultsColumnDefs, resultsDataSource);  
	}
	
	buildSlider(navigationArr,elecType);
}

function buildSlider(navArray,elecType)
{
	
		$("#jQuerySliderDiv_"+elecType).sudoSlider({ 
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

function showElectionResults(index,elecType)
{	
	if(elecType == "Assembly")
		results = assemblyResultsArray[index].partyResultsVO;
	else if(elecType == "Parliament")
		results = parliamentResultsArray[index].partyResultsVO;


	var str='';
	str+='<div id="elecResultsDiv" class="yui-skin-sam">';
	str+='<table id="elecResultsTab">';
	for(var item in results)
	{			
		str+='<tr>';
		str+='<td>'+results[item].partyName+'</td>';
		if(results[item].partyFlag)
			str+='<td><img src="images/party_flags/'+results[item].partyFlag+'" height="30" width="40"/></td>';
		else	
			str+='<td><img src="images/party_flags/no_Image.png" height="30" width="40"/></td>';
		str+='<td align="center">'+results[item].totalSeatsWon+'</td>';
		str+='</tr>';
	}
	str+='</table>';
	str+='</div>';
	
	var myPanel = new YAHOO.widget.Panel("electionResultsPopupDiv_inner", {			
			 width:"500px",
			 fixedcenter: true, 
			 constraintoviewport: true, 
			 underlay: "none", 
			 close: true, 
			 visible: true, 
			 draggable: true
   });
   myPanel.setHeader("Election Results ..");
   myPanel.setBody(str);
   myPanel.render();
	   

	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("elecResultsTab"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "partyName"
		},{
			key : "partyFlag"
		}, {
			key : "totalSeatsWon",parser:"number"
		}]
	};

	var resultsColumnDefs = [ {
		key : "partyName",
		label : "PARTY NAME",
		sortable : true
	},{
		key : "partyFlag",
		label : "PARTY Flag"
	}, {
		key : "totalSeatsWon",
		label : "SEATS WON",
		sortable : true
	}];

	
	var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10
			    }) 
				};	

   	var myDataTable = new YAHOO.widget.DataTable("elecResultsDiv",resultsColumnDefs, resultsDataSource,myConfigs);  
}
