function buildConstituencyElecResultsDataTable(value){

	if(constituencyResults == null){
		var mandalwiseVotingTrendzEL = document.getElementById("MandalwiseVotingTrendz");
		if(mandalwiseVotingTrendzEL)
			mandalwiseVotingTrendzEL.style.display = 'none';
		return;
	}
	
	if(constituencyResults.electionType == 'Assembly'){		
		var parliamentButtonDiv = document.getElementById("parliamentResultsButtonDiv");
		var str = '';
		var electionSelect = document.getElementById("electionYearSelect");
		var elecYear = electionSelect.options[electionSelect.selectedIndex].text;
		str += "<table><tr>";
		str += '<td><div class="view-all"><a style="cursor:pointer;" onclick="getConstiElecYearsForAss();"/> Parliament(s)</a></td>';
        str += '<td><div style="display:none" id="constituencyParlYearsDiv"><b>Select Parliament Election Year :&nbsp;<select id="constituencyParlYears" onchange="getParliamentResults(this.options[this.selectedIndex].value)"/> <option value="0">Select Year</option></select></div></td>';
		str += '<td><div id="censusAjaxImgDivForParlinit" align="center" style="display:none;"><img width="16px" height="16px" src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></div></td>';
		
		str += "</tr></table>";
		parliamentButtonDiv.innerHTML = str;		

		
	}
	if(constituencyResults.electionType != 'Assembly'){		
		var details = document.getElementById("detailsDiv");
		var detailsDIV = '';
		detailsDIV += '<table><tr>';
		detailsDIV += '<td><div><a href="javascript:{}" class="button detailedChartBtn" onclick="showDetailedChart(\''+constituencyResults.detailedChartPath+'\')" value="Detailed Chart">Detailed Chart</a></div></td>';
		details.innerHTML = detailsDIV;
	}
	
	var chartResultDiv = document.getElementById("electionResultsInConstituencyDiv");
	var chart = '';
	chart += '<div style="margin-left:100px;"><img src="charts/'+constituencyResults.chartPath+'"/></div>';
	chartResultDiv.innerHTML = chart;
	var imgElmt = document.getElementById('AjaxImgDiv');
	if(imgElmt.style.display == "block")
	{
          imgElmt.style.display = "none";
	}
    var conName = constituencyResults.constituencyName;
	var elecYear = constituencyResults.electionYear;
	var elecTyp = constituencyResults.electionType;
    getInteractiveChart(chartResultDiv,constituencyResults.constituencyOrMandalWiseElectionVO,constituencyResults.candidateNamePartyAndStatus,elecTyp,conName,elecYear);
		
	
	
	}
	
function getInteractiveChart(chartResultDiv,constituencyResults,partiesList,constiType,constiName,electionYear)
	{

	var chartColumns = partiesList;
	var chartRows = constituencyResults;
    var partiesArray = new Array();

	 var data = new google.visualization.DataTable();
	 data.addColumn('string', 'Party');

     //for chart columns
	 for(var i in chartColumns)
	 {
	   var colData = chartColumns[i].party +'['+chartColumns[i].rank+']';
	   data.addColumn('number',colData);

	   partiesArray.push(chartColumns[i].party);
	 }

      //for chart rows
	  for(var j in constituencyResults)
	  {
		  var array = new Array();
		  array.push(constituencyResults[j].locationName);

		  for(var k in chartRows[j].partyElectionResultVOs)
		  {
			  var percentage = chartRows[j].partyElectionResultVOs[k].votesPercent;
              array.push(percentage);
		  }
		 
		  data.addRow(array);
	  }

      var ctitle='';
	  if(constituencyType == 'Parliament')
	  {
	    ctitle = "Assembly Constituency Wise Election Results Chart For constiName constiType Constituency In electionYear";
	  }else
	  {
         ctitle = "Mandal Wise Election Results Chart For constiName constiType Constituency In electionYear";
	  }
       
      //static colors for parties
      var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);
	  
	  if(staticColors != null && staticColors.length > 0)
	  {
		 new google.visualization.LineChart(chartResultDiv).
		      draw(data, {curveType: "function",width:800, height: 500,title:ctitle,colors:staticColors,pointSize: 4,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
	  }else
	  {
		 new google.visualization.LineChart(chartResultDiv).
		      draw(data, {curveType: "function",width:800, height: 500,pointSize: 4,title:ctitle,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
	  }
	}


	function buildCensusSelect(myResult)
	{
	var censelectEle = document.getElementById("censusSelectDiv");
	
	var cenvar = '';
	cenvar += '<table  style="padding-left:11px;padding-top:20px;">';
	cenvar += '<tr>';
	if(constituencyType == 'Assembly')
	{
		cenvar += '<th>To Compare Mandal Wise Election Results With Census, Select Any Census Parameter:';
	}

	if(constituencyType == 'Parliament')
	{
		cenvar += '<th>To Compare Assembly Wise Election Results With Census, Select Any Census Parameter:';
	}
	cenvar += '&nbsp;&nbsp;';
	cenvar += '<select id="censusSelect" onchange = "getCensusDetailsForAConstituency(\'232\',this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)">';
	cenvar += '<option value=\'0\'>Select Census</option>';
	cenvar += '<option value=\'1\'>SC Population</option>';
	cenvar += '<option value=\'2\'>ST Population</option>';
	cenvar += '<option value=\'3\'>Literates</option>';
	cenvar += '<option value=\'4\'>illiterates</option>';
	cenvar += '<option value=\'5\'>Working People</option>';
	cenvar += '<option value=\'6\'>Non Working People</option>';
	cenvar += '</select>';
	cenvar += '</th>'
	cenvar += '<td><div id="censusAjaxImgDiv" align="center" style="display:none;"><img width="16px" height="16px" src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></div></td>';
	cenvar += '</tr>';
	cenvar += '<tr>';
	cenvar += '<td><b>(In the graph Census Details are in Percentages, these Percentages Calculated Over Total Population)</b></td>';
	cenvar += '</tr>';
	cenvar += '</table>';


	censelectEle.innerHTML = cenvar;
	}

function buildCensusChartForAConstituency(myResults)
	{
	if(myResults.censusVO == null || myResults.censusVO.length == 0)
	{
		showCensusError();
		hideCensusAjaxImage();
		return;
	}

	else if(myResults.censusVO.length != myResults.constituencyOrMandalWiseElectionVO.length)
	{
		showCensusError();
		hideCensusAjaxImage();
		return;
	}
	
	else if(myResults.censusVO.length > 0)
	{
    var chartColumns = myResults.candidateNamePartyAndStatus;
	var chartRows    = myResults.constituencyOrMandalWiseElectionVO;
	var census       = myResults.censusVO;
    var partiesArray = new Array();
	var selectedIndex= census[0].censusSelectedIndex;
	
	 var data = new google.visualization.DataTable();
	 data.addColumn('string', 'Party');

	 var colData = census[0].censusFields[0];
	 data.addColumn('number',colData);
	 partiesArray.push(colData);

	 //for chart columns
	 for(var i in chartColumns)
	 {
	   var colData = chartColumns[i].party +'['+chartColumns[i].rank+']';
	   data.addColumn('number',colData);

	   partiesArray.push(chartColumns[i].party);
	 }

      //for chart rows
	  for(var j in chartRows)
	  {
		  var array = new Array();
		  array.push(chartRows[j].locationName);
		
		if(selectedIndex == 1)
		var censusPercentage = census[j].populationSCPercent;
		else if(selectedIndex == 2)
		var censusPercentage = census[j].populationSTPercent;
		else if(selectedIndex == 3)
		var censusPercentage = census[j].literatesPercent;
		else if(selectedIndex == 4)
		var censusPercentage = census[j].illiteratesPercent;
		else if(selectedIndex == 5)
		var censusPercentage = census[j].workingPeoplePercent;
		else if(selectedIndex == 6)
		var censusPercentage = census[j].nonWorkingPeoplePercent;
	  
	     array.push(censusPercentage);

		  for(var k in chartRows[j].partyElectionResultVOs)
		  {
			  var percentage = chartRows[j].partyElectionResultVOs[k].votesPercent;
              array.push(percentage);
		  }

		  data.addRow(array);
	  }
    var chartResultDiv = document.getElementById("electionResultsInConstituencyDiv");

	if(constituencyType == 'Assembly')
	{
		ctitle = "Mandal Wise Election Results V/S Census Chart For constituencyName";
	}
	if(constituencyType == 'Parliament')
	{
		ctitle = "Assembly Constituency Wise Election Results V/S Census Chart For constituencyName";
	}
	
	var staticColors = setStaticColorsForInteractiveChartsForCensusAndPartiesArray(partiesArray);
    
	 if(chartRows.length == 1)
	{
		if(staticColors != null && staticColors.length > 0)
		{
		  new google.visualization.ColumnChart(chartResultDiv).
		      draw(data, {width:800, height: 500,title:ctitle,colors:staticColors,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
		else
		{
		 new google.visualization.ColumnChart(chartResultDiv).
		  draw(data, {width: 800, height: 500,title:ctitle,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
	}
	 if(chartRows.length > 1)
	{
		 if(staticColors != null && staticColors.length > 0)
		{
		 new google.visualization.LineChart(chartResultDiv).
		      draw(data, {curveType: "function",width: 800, height: 500,title:ctitle,colors:staticColors,pointSize: 6,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
		else
		{
		   new google.visualization.ColumnChart(chartResultDiv).
		  draw(data, {width: 800, height: 500,title:ctitle,pointSize: 6,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
		}
	 }
   hideCensusAjaxImage();
   removeCensusNotAvailableErrorMessage();
  }

	}



	 function hideCensusAjaxImage()
{
 if(document.getElementById('censusAjaxImgDiv') != null)
 {
    var imgElmt = document.getElementById('censusAjaxImgDiv');
	if(imgElmt.style.display == "block")
	{
          imgElmt.style.display = "none";
	}
 }
}

function showCensusError(myResult)
{
  if(document.getElementById('censusErrorMsgDiv') != null)
 {
	var cenErrorEle = document.getElementById("censusErrorMsgDiv");
	
	var cenvar = '';
	cenvar += '<table>';
	cenvar += '<th>Census Data not avaliable for this Constituency.</th>';
	cenvar += '</table>';
	cenErrorEle.innerHTML = cenvar;
	cenErrorEle.style.display = '';
  }
}

function removeCensusNotAvailableErrorMessage()
{ 
  if(document.getElementById('censusErrorMsgDiv') != null)
 {
	var cenErrorEle = document.getElementById("censusErrorMsgDiv");

	if(cenErrorEle)
	{
		cenErrorEle.style.display = "none";
	}
 }
}

function buiidCensusRadioSelect()
	{
	var cenRadioselectEle = document.getElementById("labelRadioDiv");
	
	var cenRadiovar = '';

	cenRadiovar += '<table style="margin-bottom: -14px;"><tr>';
	cenRadiovar += '<td id="labelRadio"><b>Select The Format You Want :</b></td>';
	cenRadiovar += '<td><input type="radio" name="dispaly" value="number" checked="true" onclick="buildConstituencyElectionResultsDataTableWithCensus(censusResult,this.value)">By Votes </td>';
	cenRadiovar += '<td><input type="radio" name="dispaly" value="percentage" onclick="buildConstituencyElectionResultsDataTableWithCensus(censusResult,this.value)"/>By Percentage </td>';
	cenRadiovar += '</tr></table>';

	cenRadioselectEle.innerHTML = cenRadiovar;
	}


 function buildConstituencyElectionResultsDataTableWithCensusParl(myResults,value){
	var resultDiv = document.getElementById("resultDataTableDivForParl");	
	var selectedIndex = myResults.censusVO[0].censusSelectedIndex;
	
	var str = '';
	str += '<div id="elecResDivForPal" style="width=250px;overflow-x:auto;margin-top:20px;">';
	str += '<table id = "elecResTableForParl">';

	for(var i in myResults.constituencyOrMandalWiseElectionVO)
	{
		str += '<tr>';
			
		
			str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+myResults.constituencyOrMandalWiseElectionVO[i].locationId+'&MANDAL_NAME='+myResults.constituencyOrMandalWiseElectionVO[i].locationName+'">'+myResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		
		
		if(value == 'number')
		{
			if(selectedIndex == 1)
				str += '<td>'+myResults.censusVO[i].populationSC+'</td>';
			else if(selectedIndex == 2)
				str += '<td>'+myResults.censusVO[i].populationST+'</td>';
			else if(selectedIndex == 3)
				str += '<td>'+myResults.censusVO[i].literates+'</td>';
			else if(selectedIndex == 4)
				str += '<td>'+myResults.censusVO[i].illiterates+'</td>';
			else if(selectedIndex == 5)
				str += '<td>'+myResults.censusVO[i].workingPeople+'</td>';
			else if(selectedIndex == 6)
				str += '<td>'+myResults.censusVO[i].nonWorkingPeople+'</td>';

		}
		else
		{
			if(selectedIndex == 1)
				str += '<td>'+myResults.censusVO[i].populationSCPercentage+'</td>';
			else if(selectedIndex == 2)
				str += '<td>'+myResults.censusVO[i].populationSTPercentage+'</td>';
			else if(selectedIndex == 3)
				str += '<td>'+myResults.censusVO[i].literatesPercentage+'</td>';
			else if(selectedIndex == 4)
				str += '<td>'+myResults.censusVO[i].illiteratesPercentage+'</td>';
			else if(selectedIndex == 5)
				str += '<td>'+myResults.censusVO[i].workingPeoplePercentage+'</td>';
			else if(selectedIndex == 6)
				str += '<td>'+myResults.censusVO[i].nonWorkingPeoplePercentage+'</td>';
		}

		for(var j in myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs)
		{
			if(value == 'number')
				str += '<td>'+myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesEarned+'</td>';
			else
				str += '<td>'+myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesPercentage+'</td>';
		}
			str += '</tr>';
	}		
	str += '</table>';
	str += '</div>';
	resultDiv.innerHTML = str;

	 var myColumnDefs = new Array();
	 var myFields = new Array();
	 
	
		 var villageHead = {
		 			key:"Mandal",
		 			lable: "Mandal",
		 			sortable:true
			   }

		 var villageValue = {key:"Mandal"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 
	 
	 var cenObj1 = {
			key:myResults.censusVO[0].censusFields[0],
			label:myResults.censusVO[0].censusFields[0],
			sortable:true
	    }
	
	 var cenObj2 = {
			 key:myResults.censusVO[0].censusFields[0],
			 parser:"number"
		}
		myColumnDefs.push(cenObj1);
		myFields.push(cenObj2);
	 for(var i in myResults.candidateNamePartyAndStatus){
		var obj1 = {
					key:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					label:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					sortable:true
				}
		var obj2 = {
					key:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					parser:"number"
				}
		myColumnDefs.push(obj1);
		myFields.push(obj2);
	 }

	 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("elecResTableForParl")); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
	 myDataSource.responseSchema = { 
							            fields:myFields    
							       };

	 var villageDataTable = new YAHOO.widget.DataTable("elecResDivForPal",myColumnDefs, myDataSource);
  }
  function buiidElecResultRadioSelect()
	{
	var ElecResultselectEle = document.getElementById("labelRadioDiv");
	
	var selectEle = '';

	selectEle += '<table><tr>';
	selectEle += '<td id="labelRadio"><b>Select Format You Want :</b></td>';
	selectEle += '<td><input type="radio" name="dispaly" value="number" checked="true" onclick="buildConstituencyElectionResultsDataTable(this.value)" style="margin: 3px;">By Votes </td>';
	selectEle += '<td><input type="radio" name="dispaly" value="percentage" onclick="buildConstituencyElectionResultsDataTable(this.value)" style="margin: 3px;"/>By Percentage </td>';
	selectEle += '</tr></table>';

	ElecResultselectEle.innerHTML = selectEle;
	}
	function buildElectionsSelectBox(myResults){
	var selectDiv = document.getElementById("electionIdsSelectDiv");
	var electionYearSelect = '';	
	var selectDivEl = document.getElementById("MandalwiseVotingTrendz");
	if(myResults.length == 0){
		if(selectDivEl){
			selectDivEl.style.display = 'none';
		}
			
		
		return;
	}

	var headingDiv = document.getElementById("MandalVotingTrendz_head");
	var str='';
	if(headingDiv == null)
		return;
	if(constituencyType == 'Assembly'){
	
	str +='<h1 class="topfour"></h1>';
	str +='<h1 class="gre-title">';
	str +='<span>Mandal Wise Voting Trendz</span>';
		str +='</h1>';
	headingDiv.innerHTML=str;
	}
	if('${constituencyDetails.constituencyType}' == 'Parliament')
		{
		//headingDiv.innerHTML = ' Assembly Wise Voting Trendz '; 
	str +='<h1 class="topfour"></h1>';
	str +='<h1 class="gre-title">';
	str +='<span> Assembly Wise Voting Trendz</span>';
	str +='</h1>';
		headingDiv.innerHTML = str;
	str+='<br>';
	str+='<br>';
	}
	electionYearSelect += '<table>';
	if(constituencyType == 'Assembly')
		  electionYearSelect += '<th>Select Assembly Election Year :</th>';
		else
		  electionYearSelect += '<th>Select Parliament Election Year :</th>';
	electionYearSelect += '<th>';
	electionYearSelect += '<select id="electionYearSelect" class = "selectWidth" onchange = "getConstituencyResults(this.options[this.selectedIndex].text)">';
	for(var i in myResults)
	{			
		electionYearSelect += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
	}
	electionYearSelect += '</select>';
	electionYearSelect += '</th>';
	electionYearSelect += '<td><div id="AjaxImgDiv" align="center" style="display:none;"><img width="5" height="5" src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></div></td>';
	electionYearSelect += '<td><div id="parliamentResultsButtonDiv"></td>';
	electionYearSelect += '<td><div id="detailsDiv"></td>';
	electionYearSelect += '</table>';
	selectDiv.innerHTML = electionYearSelect; 
	getConstituencyResults(myResults[0].name);
	}


	function buildConstituencyElectionResultsDataTableWithCensus(myResults,value)
	{ 
	var constType = constituencyType;
	var resultDiv = document.getElementById("resultsDataTableDiv");	
	var selectedIndex = myResults.censusVO[0].censusSelectedIndex;
	
	var str = '';
	str += '<div id="elecResDiv" style="width=250px;overflow-x:auto;margin-top:20px;">';
	str += '<table id = "elecResTable">';

	for(var i in myResults.constituencyOrMandalWiseElectionVO)
	{
		str += '<tr>';
			
		if(constType == 'Assembly')
		{	
			str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+myResults.constituencyOrMandalWiseElectionVO[i].locationId+'&MANDAL_NAME='+myResults.constituencyOrMandalWiseElectionVO[i].locationName+'">'+myResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		}
		else if(constType == 'Parliament')
			str += '<td><a href="constituencyPageAction.action?constituencyId='+myResults.constituencyOrMandalWiseElectionVO[i].locationId+'">'+myResults.constituencyOrMandalWiseElectionVO[i].locationName+'</a></td>';
		
		if(value == 'number')
		{
			if(selectedIndex == 1)
				str += '<td>'+myResults.censusVO[i].populationSC+'</td>';
			else if(selectedIndex == 2)
				str += '<td>'+myResults.censusVO[i].populationST+'</td>';
			else if(selectedIndex == 3)
				str += '<td>'+myResults.censusVO[i].literates+'</td>';
			else if(selectedIndex == 4)
				str += '<td>'+myResults.censusVO[i].illiterates+'</td>';
			else if(selectedIndex == 5)
				str += '<td>'+myResults.censusVO[i].workingPeople+'</td>';
			else if(selectedIndex == 6)
				str += '<td>'+myResults.censusVO[i].nonWorkingPeople+'</td>';

		}
		else
		{
			if(selectedIndex == 1)
				str += '<td>'+myResults.censusVO[i].populationSCPercentage+'</td>';
			else if(selectedIndex == 2)
				str += '<td>'+myResults.censusVO[i].populationSTPercentage+'</td>';
			else if(selectedIndex == 3)
				str += '<td>'+myResults.censusVO[i].literatesPercentage+'</td>';
			else if(selectedIndex == 4)
				str += '<td>'+myResults.censusVO[i].illiteratesPercentage+'</td>';
			else if(selectedIndex == 5)
				str += '<td>'+myResults.censusVO[i].workingPeoplePercentage+'</td>';
			else if(selectedIndex == 6)
				str += '<td>'+myResults.censusVO[i].nonWorkingPeoplePercentage+'</td>';
		}

		for(var j in myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs)
		{
			if(value == 'number')
				str += '<td>'+myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesEarned+'</td>';
			else
				str += '<td>'+myResults.constituencyOrMandalWiseElectionVO[i].partyElectionResultVOs[j].votesPercentage+'</td>';
		}
			str += '</tr>';
	}		
	str += '</table>';
	str += '</div>';
	resultDiv.innerHTML = str;

	 var myColumnDefs = new Array();
	 var myFields = new Array();
	 
	 if(constType == 'Assembly'){
		 var villageHead = {
		 			key:"Mandal",
		 			lable: "Mandal",
		 			sortable:true
			   }

		 var villageValue = {key:"Mandal"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }else{
		 var villageHead = {
		 			key:"Assembly Constituency",
		 			lable: "Assembly Constituency",
		 			sortable:true
			   }

		 var villageValue = {key:"Assembly Constituency"}
	
		 myColumnDefs.push(villageHead);
		 myFields.push(villageValue);
	 }
	 
	 var cenObj1 = {
			key:myResults.censusVO[0].censusFields[0],
			label:myResults.censusVO[0].censusFields[0],
			sortable:true
	    }
	
	 var cenObj2 = {
			 key:myResults.censusVO[0].censusFields[0],
			 parser:"number"
		}
		myColumnDefs.push(cenObj1);
		myFields.push(cenObj2);
	 for(var i in myResults.candidateNamePartyAndStatus){
		var obj1 = {
					key:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					label:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					sortable:true
				}
		var obj2 = {
					key:myResults.candidateNamePartyAndStatus[i].party +'['+myResults.candidateNamePartyAndStatus[i].rank+']',
					parser:"number"
				}
		myColumnDefs.push(obj1);
		myFields.push(obj2);
	 }

	 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom.get("elecResTable")); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
	 myDataSource.responseSchema = { 
							            fields:myFields    
							       };

	 var villageDataTable = new YAHOO.widget.DataTable("elecResDiv",myColumnDefs, myDataSource);
	} 

function showDetailedChart(chartName)
		{		
		var elmt = document.getElementById('detailedChartDIV');
		var divChild = document.createElement('div');
		divChild.setAttribute('id','createGroupmDiv');

	    var str='';
		str+='<img src="charts/'+chartName+'" />';
		divChild.innerHTML=str;
		elmt.appendChild(divChild);	
		if(createGroupDialog)
			createGroupDialog.destroy();
		createGroupDialog = new YAHOO.widget.Dialog("createGroupmDiv",
				{ width : "800px", 		
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
				  close:true,
				  x:600,
				  y:800
	             } );
		createGroupDialog.render();
	}

	function handleSubmit()
{
	createGroupDialog.hide();			
}

function handleCancel()
{
	this.cancel();
}


function buildConstituencyElectionsYersForAss(optionsList)
{	
    document.getElementById("constituencyParlYearsDiv").style.display="block";
	
	var elmt = document.getElementById("constituencyParlYears");
	
	if( !elmt || optionsList == null)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=1;i--)
	{
		elmt.remove(i);
	}	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].name;
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

function buildParliamentResults(parlyear,results){
		if(results != null){
			var parliamentDivElmt = document.getElementById("parliamentElectionResultsDivNew");
			if(parliamentDivElmt.style.display == "none")
			{
				parliamentDivElmt.style.display = "block";
			}
			var str = '';
			str += '<table  style="padding-left:11px;padding-top:20px;">';
		    str += '<tr>';
		    str += '<th>To Compare Mandal Wise Election Results With Census, Select Any Census Parameter:';
		    
		    str += '&nbsp;&nbsp;';
		    str += '<select id="censusSelectForParl" onchange = "getParliamentResultsForCensus(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'+parlyear+')">';
		    str += '<option value=\'0\'>Select Census</option>';
		    str += '<option value=\'1\'>SC Population</option>';
		    str += '<option value=\'2\'>ST Population</option>';
		    str += '<option value=\'3\'>Literates</option>';
		    str += '<option value=\'4\'>illiterates</option>';
		    str += '<option value=\'5\'>Working People</option>';
		    str += '<option value=\'6\'>Non Working People</option>';
		    str += '</select>';
		    str += '</th>'
		    str += '<td><div id="censusAjaxImgDivForParl" align="center" style="display:none;"><img width="16px" height="16px" src="<%=request.getContextPath()%>/images/icons/search.gif" /></img></div></td>';
		    str += '</tr>';
		    str += '<tr>';
		    str += '<td><b>(In the graph Census Details are in Percentages, these Percentages Calculated Over Total Population)</b></td>';
		    str += '</tr>';
		    str += '</table>';
			str+='<span id="heading"></span>';
			str += '<div id="newCensusSelForConParl">';
			str += '<table  style="margin-left: 13px; margin-bottom: -43px;">';
			str += '<th>Select Format You Want:</th>';
			str += '<td><input type="radio" name="parliament" value="number" checked="checked" onclick="buildParliamentResultDT(this.value)" style="margin: 3px;">By Votes</td>';
			str += '<td><input type="radio" name="parliament" value="percentage" onclick="buildParliamentResultDT(this.value)" style="margin: 3px;">By Percentage</td>';
			str += '</table>';
			str += '</div>';
			str += '<div id="resultDataTableDivForParl"></div>';
			str += '<div id="resultGraphDivForParl"></div>';
			parliamentDivElmt.innerHTML = str;
			buildParliamentResultDT("number");
		 }else{
		    var parliamentDivElmt = document.getElementById("parliamentElectionResultsDivNew");
			if(parliamentDivElmt.style.display == "none")
			{
				parliamentDivElmt.style.display = "block";
			}
			parliamentDivElmt.innerHTML = "<span style='margin-left:400px;'><b>No Records Found</span></b>";
		 }
			
		}


		function buildParliamentResultDT(checked){
	var parliamentDiv = document.getElementById("resultDataTableDivForParl");
	var str = '';
	var details = document.getElementById("detailsDiv");
	var detailsDIV = '';

	if(parliamentResult == null){
		detailsDIV += 'No Data Available';
		return;	
	}
	
	// Parliament Detailed Chart is disabled.
	// Modified by sai

	/*detailsDIV += '<div><input type="button" class="button" onclick="showDetailedChart(\''+parliamentResult.detailedChartPath+'\')" value="Detailed Chart For Paliament"></div>';	*/
	//$("#heading").html("Mandal Wise Election Results For "+parliamentResult.constituencyName+" Parliament In "+parliamentResult.electionYear+"");

	str += '<div id="parliamentElecResDiv" style="margin-top:20px;">';
	str += '<table id = "parliamentElecResTable" width="80%">';
	for(var j in parliamentResult.constituencyOrMandalWiseElectionVO){
		str += '<tr>';
		if(parliamentResult.constituencyOrMandalWiseElectionVO[j].showLink)
			str += '<td><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationId+'&MANDAL_NAME='+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationName+'">'+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationName+'</a></td>';
		else
			str += '<td>'+parliamentResult.constituencyOrMandalWiseElectionVO[j].locationName+'</td>';
		for(var k in parliamentResult.constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs){
			if(checked == 'number')
				str += '<td>'+parliamentResult.constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs[k].votesEarned+'</td>';
			else
				str += '<td>'+parliamentResult.constituencyOrMandalWiseElectionVO[j].partyElectionResultVOs[k].votesPercentage+'</td>';
		}
		str += '</tr>';
	}
	str += '</table>';
	str += '</div>';
	str += '<div id="parliamentChartDiv"><img src="charts/'+parliamentResult.chartPath+'"></div>';

	parliamentDiv.innerHTML = str;
	if(counter!=0){
		details.innerHTML = detailsDIV;
	}
	
	 var myColumnDefs = new Array();
	 var myFields = new Array();
	 
	 var villageHead = {
				key:"Mandal",
				lable: "Mandal",
				sortable:true
		   }

	 var villageValue = {key:"Mandal"}

	 myColumnDefs.push(villageHead);
	 myFields.push(villageValue);
	 

	 for(var j in parliamentResult.candidateNamePartyAndStatus){
		var obj1 = {
					key:parliamentResult.candidateNamePartyAndStatus[j].party +'['+parliamentResult.candidateNamePartyAndStatus[j].rank+']',
					label:parliamentResult.candidateNamePartyAndStatus[j].party +'['+parliamentResult.candidateNamePartyAndStatus[j].rank+']',
					sortable:true
				}
		var obj2 = {
					key:parliamentResult.candidateNamePartyAndStatus[j].party +'['+parliamentResult.candidateNamePartyAndStatus[j].rank+']',
					parser:"number"
				}
		myColumnDefs.push(obj1);
		myFields.push(obj2);
	 }

	 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("parliamentElecResTable")); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
	 myDataSource.responseSchema = { 
										fields:myFields    
									};
var villageDataTable = new YAHOO.widget.DataTable("parliamentElecResDiv",myColumnDefs, myDataSource,{caption:"Mandal Wise Election Results For "+parliamentResult.constituencyName+" Parliament Constituency In "+parliamentResult.electionYear+""});
	
	var conName = parliamentResult.constituencyName;
	var elecYear = parliamentResult.electionYear;
	var elecTyp = parliamentResult.electionType;
	var parlDivElmnt = document.getElementById("parliamentChartDiv");
	getInteractiveChart(parlDivElmnt, parliamentResult.constituencyOrMandalWiseElectionVO,parliamentResult.candidateNamePartyAndStatus,elecTyp,conName,elecYear);
}



