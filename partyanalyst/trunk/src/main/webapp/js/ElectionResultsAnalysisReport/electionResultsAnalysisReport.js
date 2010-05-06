		
	var electionAnalysisObj = {
								marginVotesInfoWon:'',
								marginVotesInfoLost:''
							  };

	var stateName = '';
	var electionId = '';
	var electionYear = '';
	var electionType = '';
	var electionTypeId = '';
	var partyName = '';
	var partyId = '';
	

function buildPageLayout()
{
	var pageLayout = new YAHOO.widget.Layout('page_layout_main', { 
	height:2000,
	units: [			
			{ 
				position: 'right', 
				width: 280,
				header:false,
				body: 'page_layout_right',
				resize: false,
				gutter: '2px',
				collapse: false,
				scroll: true,						
				animate: true 
			}, 					 
			{ 
				position: 'center',						
				body: 'page_layout_center',
				resize: false,
				gutter: '2px',
				collapse: true,
				scroll: true,						
				animate: true
			} 
		 ] 
		
	});
	pageLayout.render(); 
}
function populateElectionTypeDropdown(results)
{
	var electionTypesEl = document.getElementById("electionTypeSelectEl");
	removeSelectElements(electionTypesEl);
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
		try
			{
			electionTypesEl.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
			electionTypesEl.add(opElmt); // IE only
			}
	}
}
function populateElectionYearDropdown(results)
{
	var electionYearsEl = document.getElementById("electionYearSelectEl");
	removeSelectElements(electionYearsEl);
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
		try
			{
			electionYearsEl.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
			electionYearsEl.add(opElmt); // IE only
			}
	}
}
function populatePartiesDropdown(results)
{
	var partySelectEl = document.getElementById("partySelectEl");
	removeSelectElements(partySelectEl);
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
		try
			{
			partySelectEl.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
			partySelectEl.add(opElmt); // IE only
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

function showBasicAnalysisDetails(results,tools)
{	
	stateName = results.stateName;
	electionId = results.electionId;	
	electionYear = results.electionYear;
	electionType = results.electionType;	
	electionTypeId = results.electionTypeId;
	partyName = results.partyName;
	partyId = results.partyId;	
	var basicDetailsDivEl = document.getElementById("basicDetails");
	var tablerDetailsEl = document.getElementById("tablerDetails");
	var alliancePartiesBasicDetails = results.alliancPartiesBasicAnalysisVO;
	var basicDetailsHeadDivEl = document.getElementById("basicDetailsHead");	
	var analysisChartName = results.partyBasicAnalysisVO.analysisChart;
	var resultsChartName = results.partyBasicAnalysisVO.resultsChart;
	var headStr='';
	if(electionType != "Parliament")
	{
		headStr+='';
		headStr+='<TABLE border="0" cellpadding="0" cellspacing="0">';
		headStr+='<TR>';
		headStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
		headStr+='<TD><H3>'+stateName+' '+electionYear+' '+electionType+' Elections Analysis for '+partyName+'</H3></TD>';
		headStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
		headStr+='</TR>';
		headStr+='</TABLE>';
		
	} if(electionType == "Parliament")
	{
		headStr+='';
		headStr+='<TABLE border="0" cellpadding="0" cellspacing="0">';
		headStr+='<TR>';
		headStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
		headStr+='<TD><H3>'+electionYear+' '+electionType+' Elections Analysis for '+partyName+'</H3></TD>';
		headStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
		headStr+='</TR>';
		headStr+='</TABLE>';
	}	
	basicDetailsHeadDivEl.innerHTML = headStr;
	var str='';	
	if(alliancePartiesBasicDetails == null || alliancePartiesBasicDetails.length == 0)
	{
		basicDetailsDivEl.innerHTML = '';
		str+='<TABLE width="100%" border="1" cellspacing="0" cellpadding="0">';
		str+='<TR><TD width="50%" align="center"><IMG src="charts/'+analysisChartName+'" border="none"/></TD>';
		str+='<TD width="50%" align="center"><IMG src="charts/'+resultsChartName+'" border="none"/></TD></TR>';	
		str+='</TABLE>';
		basicDetailsDivEl.innerHTML = str;
	}
	if(alliancePartiesBasicDetails != null && alliancePartiesBasicDetails.length > 0)
	{
		basicDetailsDivEl.innerHTML = '';
		str+='<UL>';
		str+='<LI>';
		str+='<TABLE width="100%" border="1" cellspacing="0" cellpadding="0">';
		str+='<TR><TD width="50%" align="center"><IMG src="charts/'+analysisChartName+'" border="none"/></TD>';
		str+='<TD width="50%" align="center"><IMG src="charts/'+resultsChartName+'" border="none"/></TD></TR>';	
		str+='</TABLE>';
		str+='</LI>';		
		for(var i in alliancePartiesBasicDetails)
		{
			str+='<LI>';
			str+='<TABLE width="100%" border="1" cellspacing="0" cellpadding="0">';
			str+='<TR><TD width="50%" align="center"><IMG src="charts/'+alliancePartiesBasicDetails[i].analysisChart+'" border="none"/></TD>';
			str+='<TD width="50%" align="center"><IMG src="charts/'+alliancePartiesBasicDetails[i].resultsChart+'" border="none"/></TD></TR>';	
			str+='</TABLE>';
			str+='</LI>';				
			//<IMG src="charts/'+alliancePartiesBasicDetails[i].analysisChart+'" border="none"/></LI>';
			//<IMG src="charts/'+alliancePartiesBasicDetails[i].resultsChart+'" border="none"/>
		}
		str+='</UL>';
		basicDetailsDivEl.innerHTML = str;
		buildGraphsCarousel("basicDetails");
	}	
	var tablerDataStr = '';
	tablerDataStr+='<TABLE border="0" cellpadding="0" cellspacing="0">';
	tablerDataStr+='<TR>';
	tablerDataStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
	tablerDataStr+='<TD><H3>Analysis Details</H3></TD>';
	tablerDataStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
	tablerDataStr+='</TR>';
	tablerDataStr+='</TABLE>';	
	tablerDataStr+='<TABLE  cellpadding="0" cellspacing="0" class="analysisDetailsTable">';
	tablerDataStr+='<TR>';
	tablerDataStr+='<TH class="head">Party</TH>';
	tablerDataStr+='<TH class="head">Participated</TH>';
	tablerDataStr+='<TH class="head">Won</TH>';	
	tablerDataStr+='<TH class="head">Lost</TH>';
	tablerDataStr+='<TH class="head">Analyzed</TH>';
	tablerDataStr+='<TH class="edgeH">Yet to be Analyzed</TH>';
	tablerDataStr+='</TR>';
	tablerDataStr+='<TR>';
	tablerDataStr+='<TD class="middle">'+results.partyBasicAnalysisVO.partyName+'</TD>';
	tablerDataStr+='<TD class="middle">'+results.partyBasicAnalysisVO.partiConstituencies+'</TD>';
	if(results.partyBasicAnalysisVO.seatsWon != '0')
	{
		tablerDataStr+='<TD class="middle"><A href="javascript:{}" title="Click To View Constituencies" onclick="openPartyElectionResultsWindow('+electionId+','+partyId+',\'1\',\''+results.partyBasicAnalysisVO.partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+','+electionTypeId+')">'+results.partyBasicAnalysisVO.seatsWon+'</A></TD>';
	} else 
	{
		tablerDataStr+='<TD class="middle">'+results.partyBasicAnalysisVO.seatsWon+'</TD>';	
	}
	if(results.partyBasicAnalysisVO.seatsLost != 0)
	{
		tablerDataStr+='<TD class="middle"><A href="javascript:{}" title="Click To View Constituencies" onclick="openPartyElectionResultsWindow('+electionId+','+partyId+',\'0\',\''+results.partyBasicAnalysisVO.partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+','+electionTypeId+')">'+results.partyBasicAnalysisVO.seatsLost+'</A></TD>';
	} else
	{
		tablerDataStr+='<TD class="middle">'+results.partyBasicAnalysisVO.seatsLost+'</TD>';
	}
	if(results.partyBasicAnalysisVO.analyzedConsti != 0)
	{	
		tablerDataStr+='<TD class="middle"><A href="javascript:{}" title="Click To View Analyzed Constituencies" onclick="openPartyElectionResultsAnalysisWindow('+electionId+','+partyId+',\'analyzed\',\''+results.partyBasicAnalysisVO.partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+')">'+results.partyBasicAnalysisVO.analyzedConsti+'</A></TD>';
	} else 
	{
		tablerDataStr+='<TD class="middle">'+results.partyBasicAnalysisVO.analyzedConsti+'</TD>';
	}
	if(results.partyBasicAnalysisVO.notAnalyzedConsti != 0)
	{
		tablerDataStr+='<TD class="edge"><A href="javascript:{}"  title="Click To View Yet to be Analyzed Constituencies" onclick="openPartyElectionResultsAnalysisWindow('+electionId+','+partyId+',\'notAnalyzed\',\''+results.partyBasicAnalysisVO.partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+')">'+results.partyBasicAnalysisVO.notAnalyzedConsti+'</A></TD>';
	} else
	{
		tablerDataStr+='<TD class="edge">'+results.partyBasicAnalysisVO.notAnalyzedConsti+'</TD>';
	}
	tablerDataStr+='</TR>';
	if(alliancePartiesBasicDetails != null && alliancePartiesBasicDetails.length != 0)
	{
		tablerDataStr+='<TR>';
		tablerDataStr+='<TD colspan="6"><H3 style="width:625px;">Alliance Parties Analysis</H3></TD>';
		tablerDataStr+='</TR>';
		for(var j in alliancePartiesBasicDetails)
		{
			tablerDataStr+='<TR>';
			tablerDataStr+='<TD class="middle">'+alliancePartiesBasicDetails[j].partyName+'</TD>';
			tablerDataStr+='<TD class="middle">'+alliancePartiesBasicDetails[j].partiConstituencies+'</TD>';
			if(alliancePartiesBasicDetails[j].seatsWon != 0)
			{
				tablerDataStr+='<TD class="middle"><A href="javascript:{}" title="Click To View Constituencies" onclick="openPartyElectionResultsWindow('+electionId+','+alliancePartiesBasicDetails[j].partyId+',\'1\',\''+alliancePartiesBasicDetails[j].partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+','+electionTypeId+')">'+alliancePartiesBasicDetails[j].seatsWon+'</A></TD>';
			} else 
			{
				tablerDataStr+='<TD class="middle">'+alliancePartiesBasicDetails[j].seatsWon+'</TD>';
			}
			if( alliancePartiesBasicDetails[j].seatsLost!= 0)
			{			
				tablerDataStr+='<TD class="middle"><A href="javascript:{}" title="Click To View Constituencies" onclick="openPartyElectionResultsWindow('+electionId+','+alliancePartiesBasicDetails[j].partyId+',\'0\',\''+alliancePartiesBasicDetails[j].partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+','+electionTypeId+')">'+alliancePartiesBasicDetails[j].seatsLost+'</A></TD>';
			} else 
			{
				tablerDataStr+='<TD class="middle">'+alliancePartiesBasicDetails[j].seatsLost+'</TD>';
			}
			if(alliancePartiesBasicDetails[j].analyzedConsti != 0)
			{	
				tablerDataStr+='<TD class="middle"><A href="javascript:{}" title="Click To View Analyzed Constituencies" onclick="openPartyElectionResultsAnalysisWindow('+electionId+','+alliancePartiesBasicDetails[j].partyId+',\'analyzed\',\''+alliancePartiesBasicDetails[j].partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+')">'+alliancePartiesBasicDetails[j].analyzedConsti+'</A></TD>';
			} else
			{
					tablerDataStr+='<TD class="middle">'+alliancePartiesBasicDetails[j].analyzedConsti+'</TD>';
			}
			if(alliancePartiesBasicDetails[j].notAnalyzedConsti != 0)
			{	
				tablerDataStr+='<TD class="edge"><A href="javascript:{} title="Click To View Yet to be Analyzed Constituencies" onclick="openPartyElectionResultsAnalysisWindow('+electionId+','+alliancePartiesBasicDetails[j].partyId+',\'notAnalyzed\',\''+alliancePartiesBasicDetails[j].partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+')">'+alliancePartiesBasicDetails[j].notAnalyzedConsti+'</A></TD>';
			} else 
			{
				tablerDataStr+='<TD class="edge">'+alliancePartiesBasicDetails[j].notAnalyzedConsti+'</TD>';
			}	
			tablerDataStr+='</TR>';
		}
	}			
	tablerDataStr+='</TABLE>';
	tablerDetailsEl.innerHTML =tablerDataStr; 
	//mycode -raghav
	if(tools == 'toolsTrue')
	{	
		var sideHeaderDivEl = document.getElementById("sideHeader");
		var sideHeadContentStr='';
		sideHeadContentStr+='<TABLE border="0" cellpadding="0" cellspacing="0">';
		sideHeadContentStr+='<TR>';
		sideHeadContentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/1.png"></TD>';
		sideHeadContentStr+='<TD><DIV class="sideHeading">Analysis Tools</DIV></TD>';
		sideHeadContentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/3.png"></TD>';
		sideHeadContentStr+='</TR>';
		sideHeadContentStr+='</TABLE>';
		sideHeaderDivEl.innerHTML = sideHeadContentStr;
		var toolsDivEl = document.getElementById("toolsDiv");
		var toolsDivElContent = '';
		toolsDivElContent+='<DIV class="toolHead">';
		toolsDivElContent+='<TABLE border="0" cellpadding="0" cellspacing="0">';
		toolsDivElContent+='<TR>';
		toolsDivElContent+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
		toolsDivElContent+='<TD><DIV class="toolHeading">Election Results Analysis</DIV></TD>';
		toolsDivElContent+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
		toolsDivElContent+='</TR>';
		toolsDivElContent+='</TABLE>';		
		toolsDivElContent+='<DIV class="toolBody">'; 
		toolsDivElContent+='<TABLE width="100%">';
		toolsDivElContent+='<TR>';
		toolsDivElContent+='<TD colspan="2"><P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">View another year election results analysis.</P></TD>';
		toolsDivElContent+='</TR>';
		toolsDivElContent+='<TR>';
		toolsDivElContent+='<TD colspan="2"><DIV id="yearAlertAnalysisReport" style="display:none;color:red;text-align:left;" >Error Message</DIV></TD>';
		toolsDivElContent+='</TR>';						
		toolsDivElContent+='<TR>';	
		toolsDivElContent+='<TD>Year:</TD>';
		toolsDivElContent+='<TD><SELECT id="selectYearAnalysisTool" name="selectYearAnalysisTool" style="width: 100px; margin-top: 3px;">';
		for(var k in results.electionYearsList)
		{	
			if(electionYear != results.electionYearsList[k].name)
			{	
				toolsDivElContent+='<OPTION value='+results.electionYearsList[k].id+'>'+results.electionYearsList[k].name+'</OPTION>';
			}
		}								
		toolsDivElContent+='</SELECT>';	
		toolsDivElContent+='</TD>';
		toolsDivElContent+='</TR>';							
		toolsDivElContent+='</TABLE>';
		toolsDivElContent+='<DIV align="right" style="margin-top:50px;"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" onclick="openPreYearStatewiseAnalysisWindow(\''+electionType+'\','+electionTypeId+','+electionYear+','+partyId+')" /></A></DIV>';		 
		toolsDivElContent+='</DIV>';
		toolsDivElContent+='</DIV>';	
		toolsDivElContent+='<DIV class="toolHead">';
		if(electionType != 'Parliament')
		{	
			toolsDivElContent+='<TABLE border="0" cellpadding="0" cellspacing="0">';
			toolsDivElContent+='<TR>';
			toolsDivElContent+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
			toolsDivElContent+='<TD><DIV class="toolHeading">Statewise Election Results</DIV></TD>';
			toolsDivElContent+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
			toolsDivElContent+='</TR>';
			toolsDivElContent+='</TABLE>';			
			toolsDivElContent+='<DIV class="toolBody">'; 
			toolsDivElContent+='<TABLE width="100%">';
			toolsDivElContent+='<TR>';
			toolsDivElContent+='<TD colspan="2"><P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">Analyze election results Statewise.</P></TD>';
			toolsDivElContent+='</TR>';
		} else if(electionType == 'Parliament')
		{
			toolsDivElContent+='<TABLE border="0" cellpadding="0" cellspacing="0">';
			toolsDivElContent+='<TR>';
			toolsDivElContent+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
			toolsDivElContent+='<TD><DIV class="toolHeading">Countrywise Election Results</DIV></TD>';
			toolsDivElContent+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
			toolsDivElContent+='</TR>';
			toolsDivElContent+='</TABLE>';			
			toolsDivElContent+='<DIV class="toolBody">'; 
			toolsDivElContent+='<TABLE width="100%">';
			toolsDivElContent+='<TR>';
			toolsDivElContent+='<TD colspan="2"><P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">Analyze election results Countrywise.</P></TD>';
			toolsDivElContent+='</TR>';
		}	
		toolsDivElContent+='<TR>';
		toolsDivElContent+='<TD colspan="2"><DIV id="yearAlertERR" style="display:none;color:red;text-align:left;" >Error Message</DIV></TD>';
		toolsDivElContent+='</TR>';
		toolsDivElContent+='<TR>';	
		toolsDivElContent+='<TD>Year:</TD>';
		toolsDivElContent+='<TD><SELECT id="selectYearERR" name="selectYearERR" style="width: 100px; margin-top: 3px;">';
		for(var k in results.electionYearsList)
		{		
			 toolsDivElContent+='<OPTION value='+results.electionYearsList[k].id+'>'+results.electionYearsList[k].name+'</OPTION>';			
		}										
		toolsDivElContent+='</SELECT>';
		toolsDivElContent+='</TD>';
		toolsDivElContent+='</TR>';														
		toolsDivElContent+='</TABLE>';
		toolsDivElContent+='<DIV align="right" style="margin-top:50px;"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" onclick="openStatewiseElectionResultsWindow('+electionYear+',\''+stateName+'\',\''+electionType+'\')" /></A></DIV>';		 
		toolsDivElContent+='</DIV>';
		toolsDivElContent+='</DIV>';
		toolsDivElContent+='<DIV class="toolHead">';
		if(electionType != 'Parliament')
		{
			toolsDivElContent+='<TABLE border="0" cellpadding="0" cellspacing="0">';
			toolsDivElContent+='<TR>';
			toolsDivElContent+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
			toolsDivElContent+='<TD><DIV class="toolHeading">Districtwise Election Results</DIV></TD>';
			toolsDivElContent+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
			toolsDivElContent+='</TR>';
			toolsDivElContent+='</TABLE>';			
			toolsDivElContent+='<DIV class="toolBody">'; 
			toolsDivElContent+='<TABLE width="100%">';
			toolsDivElContent+='<TR>';
			toolsDivElContent+='<TD colspan="2"><P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">Analyze election results Districtwise.</P></TD>';
			toolsDivElContent+='</TR>';
		} else if(electionType == 'Parliament')
		{
			toolsDivElContent+='<TABLE border="0" cellpadding="0" cellspacing="0">';
			toolsDivElContent+='<TR>';
			toolsDivElContent+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
			toolsDivElContent+='<TD><DIV class="toolHeading">Statewise Election Results</DIV></TD>';
			toolsDivElContent+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
			toolsDivElContent+='</TR>';
			toolsDivElContent+='</TABLE>';			
			toolsDivElContent+='<DIV class="toolBody">'; 
			toolsDivElContent+='<TABLE width="100%">';
			toolsDivElContent+='<TR>';
			toolsDivElContent+='<TD colspan="2"><P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">Analyze election results Statewise.</P></TD>';
			toolsDivElContent+='</TR>';
		}
		toolsDivElContent+='<TR>';
		toolsDivElContent+='<TD colspan="2"><DIV id="yearAlertERR1" style="display:none;color:red;text-align:left;" >Error Message</DIV></TD>';
		toolsDivElContent+='</TR>';
		toolsDivElContent+='<TR>';	
		toolsDivElContent+='<TD>Year:</TD>';
		toolsDivElContent+='<TD><SELECT id="selectYearERR1" name="selectYearPPR" style="width: 100px; margin-top: 3px;">';
		for(var k in results.electionYearsList)
		{		
			 toolsDivElContent+='<OPTION value='+results.electionYearsList[k].id+'>'+results.electionYearsList[k].name+'</OPTION>';			
		}									
		toolsDivElContent+='</SELECT>';
		toolsDivElContent+='</TD>';
		toolsDivElContent+='</TR>';														
		toolsDivElContent+='</TABLE>';
		toolsDivElContent+='<DIV align="right" style="margin-top:50px;"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" onclick="openDistwiseElectionResultsWindow('+electionYear+',\''+stateName+'\',\''+electionType+'\')" /></A></DIV>';		 
		toolsDivElContent+='</DIV>';
		toolsDivElContent+='</DIV>';
		if(electionType == 'Assembly' || electionType == 'Parliament') 
		{
			toolsDivElContent+='<DIV class="toolHead">';
			toolsDivElContent+='<TABLE border="0" cellpadding="0" cellspacing="0">';
			toolsDivElContent+='<TR>';
			toolsDivElContent+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
			toolsDivElContent+='<TD><DIV class="toolHeading">Party Performance Report</DIV></TD>';
			toolsDivElContent+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
			toolsDivElContent+='</TR>';
			toolsDivElContent+='</TABLE>';			
			toolsDivElContent+='<DIV class="toolBody">'; 
			toolsDivElContent+='<TABLE width="100%">';
			toolsDivElContent+='<TR>';
			toolsDivElContent+='<TD colspan="2"><P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">Analyze a party performance for selected election year</P></TD>';
			toolsDivElContent+='</TR>';
			toolsDivElContent+='<TR>';
			toolsDivElContent+='<TD colspan="2"><DIV id="yearAlertPPR" style="display:none;color:red;text-align:left;" >Error Message</DIV></TD>';
			toolsDivElContent+='</TR>';
			toolsDivElContent+='<TR>';
			toolsDivElContent+='<TD>Party Name:</TD>';
			toolsDivElContent+='<TD>';
			toolsDivElContent+='<select id="selectPartyPPR" name="selectParty">';
			for(var l in results.partiesList)
			{		
				 toolsDivElContent+='<OPTION value='+results.partiesList[l].id+'>'+results.partiesList[l].name+'</OPTION>';			
			}
			toolsDivElContent+='</select>';							
			toolsDivElContent+='</TD>';
			toolsDivElContent+='</TR>';	
			toolsDivElContent+='<TR>';	
			toolsDivElContent+='<TD>Year:</TD>';
			toolsDivElContent+='<TD><SELECT id="selectYearPPR" name="selectYearPPR" style="width: 100px; margin-top: 3px;">';
			for(var k in results.electionYearsList)
			{		
				 toolsDivElContent+='<OPTION value='+results.electionYearsList[k].id+'>'+results.electionYearsList[k].name+'</OPTION>';			
			}									
			toolsDivElContent+='</SELECT>';
			toolsDivElContent+='</TD>';
			toolsDivElContent+='</TR>';	
			toolsDivElContent+='<TR>';	
			toolsDivElContent+='<TD colspan="2"><INPUT type="checkbox" id="pprCheckBox" value="hasAllianceParties" name="alliances"/>Include Alliances</TD>';
			toolsDivElContent+='</TR>';							
			toolsDivElContent+='</TABLE>';
			toolsDivElContent+='<DIV align="right" style="margin-top:50px;"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" onclick="openPartyPerformanceWindow('+electionTypeId+')" /></A></DIV>';		 
			toolsDivElContent+='</DIV>';
			toolsDivElContent+='</DIV>';
		}	
		toolsDivEl.innerHTML = toolsDivElContent;
	}	
	
	//my code end -raghav
	getAnalysisDetailsInPartyWonPositions(electionType,electionYear,electionId,partyId);
	getAnalysisDetailsInPartyLostPositions(electionType,electionYear,electionId,partyId);
}
function buildGraphsCarousel(divId)
{
	var elmt = document.getElementById(divId);
	
	var graphImagesCarousel = new YAHOO.widget.Carousel(divId,
			{
				carouselEl: "UL",
				isCircular: true,
				isVertical: false,
				numVisible: 1,
				animation: { speed: 1.0 },
				autoPlayInterval: 2000
			});

	graphImagesCarousel.render(); 
	graphImagesCarousel.show();
}


function showAnalysisDetailsInPartyLostPositions(results)
{		
	var divEl = document.getElementById("lostPosAnalisisDetails");
	contentStr='';
	contentStr+='<TABLE border="0" cellpadding="0" cellspacing="0">';
	contentStr+='<TR>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/1.png"></TD>';
	contentStr+='<TD><DIV class="wonLostPosHeading">Analysis in Party Lost Positions</DIV></TD>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/3.png"></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';	
	contentStr+='<DIV>';
	contentStr+='<TABLE class="wonLostPosTable" width="80%">';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">Seats Lost</TH>';
	contentStr+='<TD style="width:30%"><a href="javascript:{}" onclick="openPartyElectionResultsWindow('+electionId+','+partyId+',\'0\',\''+partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+','+electionTypeId+')">'+results.resultTypeValue+'</a></TD>';
	contentStr+='</TR>';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">Analyzed Constituencies</TH>';
	contentStr+='<TD style="width:30%"><a href="javascript:{}" onclick="openMainPartyElectionResultsAnalysisWindow('+electionId+','+partyId+',\'analyzed\',\''+partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+',\'Lost\')">'+results.analyzedConsti+'</a></TD>';
	contentStr+='</TR>';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">Yet to be  Analyzed Constituencies</TH>';
	contentStr+='<TD style="width:30%"><a href="javascript:{}" onclick="openMainPartyElectionResultsAnalysisWindow('+electionId+','+partyId+',\'notAnalyzed\',\''+partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+',\'Lost\')">'+results.notAnalyzedConsti+'</a></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';								
	contentStr+='</DIV>';
	contentStr+='<DIV class="wonLostPosHeading" id="positionAnalysisDetails_head">Analysis in Party Lost Positions</DIV>';
	contentStr+='<DIV id="positionAnalysisDetails_head">';
	contentStr+='	<DIV id="positionAnalysisDetails_data">';
	contentStr+='		<TABLE class="wonLostPosTable" width="80%">';
	contentStr+='			<TR>';
	contentStr+='				<TH style="width:50%">Seats Lost</TH>';
	contentStr+='				<TD style="width:30%">';
	contentStr+='					<a href="javascript:{}" onclick="openPartyElectionResultsWindow('+electionId+','+partyId+',\'0\',\''+partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+','+electionTypeId+')">'+results.resultTypeValue+'</a>';
	contentStr+='				</TD>';
	contentStr+='			</TR>';
	contentStr+='			<TR>';
	contentStr+='				<TH style="width:50%">Analyzed Constituencies</TH>';
	contentStr+='				<TD style="width:30%">';
	contentStr+='					<a href="javascript:{}" onclick="openMainPartyElectionResultsAnalysisWindow('+electionId+','+partyId+',\'analyzed\',\''+partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+',\'Lost\')">'+results.analyzedConsti+'</a>';
	contentStr+='				</TD>';
	contentStr+='			</TR>';
	contentStr+='			<TR>';
	contentStr+='				<TH style="width:50%">Yet to be  Analyzed Constituencies</TH>';
	contentStr+='				<TD style="width:30%"><a href="javascript:{}" onclick="openMainPartyElectionResultsAnalysisWindow('+electionId+','+partyId+',\'notAnalyzed\',\''+partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+',\'Lost\')">'+results.notAnalyzedConsti+'</a></TD>';
	contentStr+='			</TR>';
	contentStr+='		</TABLE>';								
	contentStr+='	</DIV>';
	if(results.analysisCategoryBasicResultVO != null)
	{
	contentStr+='<div><br></br></div>';
	contentStr+='<TABLE border="0" cellpadding="0" cellspacing="0">';
	contentStr+='<TR>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
	contentStr+='<TD><H3 style="width:510px;">Reasons Analysis</H3></TD>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';	
    contentStr+='<div id="categoriesClassificationDiv">';
    for(var i in results.analysisCategoryBasicResultVO)
	{
    contentStr+='<TABLE class="wonLostPosTable" width="80%">';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">'+results.analysisCategoryBasicResultVO[i].categoryType+'</TH>';
	contentStr+='<TD style="width:30%"><a href="javascript:{}" onclick="openMainPartyElectionResultsAnalysisCategoryWindow('+electionId+','+partyId+',\'analyzed\',\''+partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+',\'Lost\','+results.analysisCategoryBasicResultVO[i].categoryId+')">'+results.analysisCategoryBasicResultVO[i].categoryResultCount+'</a></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';		
	}
    contentStr+='</div>';
	{	
		contentStr+='<H3 style="width:510px;">Reasons Analysis</H3>';
		contentStr+='<div id="categoriesClassificationDiv">';
		contentStr+='<TABLE class="wonLostPosTable" width="80%">';
		for(var i in results.analysisCategoryBasicResultVO)
		{

		contentStr+='<TR>';
		contentStr+='<TH style="width:50%">'+results.analysisCategoryBasicResultVO[i].categoryType+'</TH>';
		contentStr+='<TD style="width:30%"><a href="javascript:{}" onclick="openMainPartyElectionResultsAnalysisCategoryWindow('+electionId+','+partyId+',\'analyzed\',\''+partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+',\'Lost\','+results.analysisCategoryBasicResultVO[i].categoryId+')">'+results.analysisCategoryBasicResultVO[i].categoryResultCount+'</a></TD>';
		contentStr+='</TR>';	
		}
		contentStr+='</TABLE>';		
		contentStr+='</div>';
	}
	contentStr+='<div><br></br></div>';
	contentStr+='<div id="multipleCategoriesDiv">';
	contentStr+='<TABLE border="0" cellpadding="0" cellspacing="0">';
	contentStr+='<TR>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
	contentStr+='<TD><H3 style="width:510px;">Constituencies with Multiple Reasons</H3></TD>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';
	contentStr+='	<TABLE  cellpadding="0" cellspacing="0" width="75%"  class="multipleClassificationsTable">';
	contentStr+='	<TR>';
	contentStr+='		<TH width="30%">No of Analysis Reasons</TH>';
	for(var i in results.multipleCategories)
	{
		if(results.multipleCategories[i].id == 0)
			contentStr+='		<TH width="5%">N</TH>';
		else
			contentStr+='		<TH width="5%">'+results.multipleCategories[i].id+'</TH>';
	}
	contentStr+='	</TR>';
	contentStr+='	<TR>';
	contentStr+='	<TD width="30%"><B>No of Constituencies</B></TD>';
	for(var i in results.multipleCategories)
	{
		contentStr+='<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}" onclick="openMainPartyMultipleReasonsAnalysisWindow('+electionId+','+partyId+',\'analyzed\',\''+partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+',\'Lost\','+results.multipleCategories[i].id+','+results.multipleCategories[i].name+')">'+results.multipleCategories[i].name+'</A></TD>';
	}
	contentStr+='	</TR>';
	contentStr+='	</table>';					
	contentStr+='</div>';
	contentStr+='<br/><br/>';
	contentStr+='<div id="votesMarginLost_main">';
	contentStr+='<TABLE border="0" cellpadding="0" cellspacing="0">';
	contentStr+='<TR>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
	contentStr+='<TD><div id="votesMarginLost_Head"><H3 style="width:510px;">Analysis Based On Votes Margin</H3></div></TD>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';	
	contentStr+='<div id="votesMarginLost_Body" class="votesMargin_Body_Main"></div>';
	contentStr+='</div>';

	divEl.innerHTML=contentStr;

	buildVotesMarginInfo(electionId,partyId,"LOST");
}

function showMarginBody(id)
{
	var marginBodyId = id.substring(0,id.lastIndexOf('_'))+"_body";

	var marginBodyElmt = document.getElementById(marginBodyId);
	
	if(marginBodyElmt.style.display == 'none')
		marginBodyElmt.style.display = 'block';
	else if(marginBodyElmt.style.display == 'block')
		marginBodyElmt.style.display = 'none';
}
function showAnalysisDetailsInPartyWonPositions(results)
{
	var divEl = document.getElementById("wonPosAnalisisDetails");
	contentStr='';
	contentStr+='<TABLE border="0" cellpadding="0" cellspacing="0">';
	contentStr+='<TR>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/1.png"></TD>';
	contentStr+='<TD><DIV class="wonLostPosHeading">Analysis in Party Won Positions</DIV></TD>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/3.png"></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';	
	contentStr+='<DIV>';
	contentStr+='<TABLE class="wonLostPosTable" width="80%">';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">Seats Won</TH>';
	contentStr+='<TD style="width:30%"><a href="javascript:{}" onclick="openPartyElectionResultsWindow('+electionId+','+partyId+',\'1\',\''+partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+','+electionTypeId+')">'+results.resultTypeValue+'</a></TD>';
	contentStr+='</TR>';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">Analyzed Constituencies</TH>';
	contentStr+='<TD style="width:30%"><a href="javascript:{}" onclick="openMainPartyElectionResultsAnalysisWindow('+electionId+','+partyId+',\'analyzed\',\''+partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+',\'Won\')">'+results.analyzedConsti+'</a></TD>';
	contentStr+='</TR>';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">Yet to be  Analyzed Constituencies</TH>';
	contentStr+='<TD style="width:30%"><a href="javascript:{}" onclick="openMainPartyElectionResultsAnalysisWindow('+electionId+','+partyId+',\'notAnalyzed\',\''+partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+',\'Won\')">'+results.notAnalyzedConsti+'</a></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';								
	contentStr+='</DIV>';
	if(results.analysisCategoryBasicResultVO != null)
	{
	contentStr+='<div><br></br></div>';	
	contentStr+='<TABLE border="0" cellpadding="0" cellspacing="0">';
	contentStr+='<TR>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
	contentStr+='<TD><H3 style="width:510px;">Reasons Analysis</H3></TD>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';	
    contentStr+='<div id="categoriesClassificationDiv">';
    for(var i in results.analysisCategoryBasicResultVO)
	{
    contentStr+='<TABLE class="wonLostPosTable" width="80%">';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">'+results.analysisCategoryBasicResultVO[i].categoryType+'</TH>';
	contentStr+='<TD style="width:30%"><a href="javascript:{}" onclick="openMainPartyElectionResultsAnalysisCategoryWindow('+electionId+','+partyId+',\'analyzed\',\''+partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+',\'Won\','+results.analysisCategoryBasicResultVO[i].categoryId+')">'+results.analysisCategoryBasicResultVO[i].categoryResultCount+'</a></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';		
	}
    contentStr+='</div>';
	}
	contentStr+='<div><br></br></div>';
	contentStr+='<div id="multipleCategoriesDiv">';
	contentStr+='<TABLE border="0" cellpadding="0" cellspacing="0">';
	contentStr+='<TR>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
	contentStr+='<TD><H3 style="width:510px;">Constituencies with Multiple Reasons</H3></TD>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';	
	contentStr+='	<TABLE  cellpadding="0" cellspacing="0" width="75%"  class="multipleClassificationsTable">';
	contentStr+='	<TR>';
	contentStr+='		<TH width="30%">No of Analysis Reasons</TH>';
	for(var i in results.multipleCategories)
	{
		if(results.multipleCategories[i].id == 0)
			contentStr+='		<TH width="5%">N</TH>';
		else
			contentStr+='		<TH width="5%">'+results.multipleCategories[i].id+'</TH>';
	}
	contentStr+='	</TR>';
	contentStr+='	<TR>';
	contentStr+='	<TD width="30%"><B>No of Constituencies</B></TD>';
	for(var i in results.multipleCategories)
	{
		contentStr+='<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}">'+results.multipleCategories[i].name+'</A></TD>';
	}
	contentStr+='	</TR>';
	contentStr+='	</table>';					
	contentStr+='</div>';

	contentStr+='<br/><br/>';

	contentStr+='<div id="votesMarginWon_main">';
	contentStr+='<TABLE border="0" cellpadding="0" cellspacing="0">';
	contentStr+='<TR>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>';
	contentStr+='<TD><div id="votesMarginWon_Head"><H3 style="width:510px;">Analysis Based On Votes Margin</H3></div></TD>';
	contentStr+='<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';
	contentStr+='';
	contentStr+='<div id="votesMarginWon_Body" class="votesMargin_Body_Main"></div>';
	contentStr+='</div>';

	divEl.innerHTML=contentStr;

	buildVotesMarginInfo(electionId,partyId,"WON");
}


function showMarginAnalysisData(index,task)
{
	var elmt = document.getElementById("marginInfo_"+task+"_row_"+index);

	if(!elmt)
		return;

	if(elmt.style.display == 'none')
		elmt.style.display = '';
	else if(elmt.style.display == '')
		elmt.style.display = 'none';
}

function buildVotesMarginInfoContent(jsObj,results)
{	
	var elmt,str='';

	if(jsObj.status == 'LOST')
	{
		elmt = document.getElementById("votesMarginLost_Body");
		electionAnalysisObj.marginVotesInfoLost = results;
	}
	else if(jsObj.status == 'WON')
	{
		elmt = document.getElementById("votesMarginWon_Body");
		electionAnalysisObj.marginVotesInfoWon = results;
	}
	
	str += '<table width="75%" style="width:75%" class="votesMarginTable" border="1">';
	str += '<tr>';
	str += '<th style="background-color:#9DA6AF;"></th>';
	str += '<th align="left" style="background-color:#9DA6AF;">Votes Margin</th>';
	str += '<th style="background-color:#9DA6AF;">Constituencies '+jsObj.status+'</th>';
	str += '<th style="background-color:#9DA6AF;">Analyzed</th>';
	str += '</tr>';
	for(var i in results)
	{
		str+= '<tr onclick="showMarginAnalysisData('+i+',\''+jsObj.status+'\')">';
		str+= '<th style="background-color:#FFFFFF;"><img src="images/icons/indexPage/listIcon.png"/></th>';
		str+= '<th align="left">'+results[i].marginRange+'</th>';		
		if(results[i].candidatesCount != 0)
			str+= '<th><a href="javascript:{}" onclick="showMarginCountAnalysisForConstituenciesPopup('+i+','+partyId+',\''+jsObj.status+'\')">'+results[i].candidatesCount+'</a></th>';
		else
			str+= '<th>'+results[i].candidatesCount+'</th>';
		
		
		if(results[i].analyzedCount != 0)
			str+= '<th><a href="javascript:{}" onclick="showMarginCountAnalysisForAnalyzedConstituenciesPopup('+i+','+partyId+',\''+jsObj.status+'\')">'+results[i].analyzedCount+'</a></th>';
		else
			str+= '<th>'+results[i].analyzedCount+'</th>';

		str+= '</tr>';
		if(results[i].analysisCategoryBasicVO != null)
		{
			str+= '<tr id="marginInfo_'+jsObj.status+'_row_'+i+'" style="display:none;">';
			str+= '<td colspan="4">';
			str+= '<div class="marginBodyDivClass">';
			str+= '<table width="70%" border="0" class="votesMarginDataTable">';
			for(var j in results[i].analysisCategoryBasicVO)
			{
				var dt = results[i].analysisCategoryBasicVO[j];				
				str+= '<tr>';
				str+= '<td align="left">'+dt.categoryType+'</td>';
				str+= '<td><a href="javascript:{}" onclick="showMarginCountAnalysisForCategory('+i+','+partyId+','+dt.categoryId+',\''+jsObj.status+'\')">'+dt.categoryResultCount+'</a></td>';						
				str+= '</tr>';
			}
			str+= '</table>';
			str+= '</div>';
			str+= '</td>';
			str+= '</tr>';
		}
	}	
	str += '</table>';

	if(elmt)
		elmt.innerHTML = str;
}


function initializePage()
{
	buildPageLayout();
	
}