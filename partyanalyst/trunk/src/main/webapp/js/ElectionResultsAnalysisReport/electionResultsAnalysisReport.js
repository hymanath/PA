
function buildPageLayout()
{
	var pageLayout = new YAHOO.widget.Layout('page_layout_main', { 
	height:1500,
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

function showBasicAnalysisDetails(results)
{
	
	var stateName = results.stateName;
	var electionId = results.electionId;	
	var electionYear = results.electionYear;
	var electionType = results.electionType;
	var partyName = results.partyName;
	var partyId = results.partyId;
	var basicDetailsDivEl = document.getElementById("basicDetails");
	var tablerDetailsEl = document.getElementById("tablerDetails");
	var alliancePartiesBasicDetails = results.alliancPartiesBasicAnalysisVO;
	var basicDetailsHeadDivEl = document.getElementById("basicDetailsHead");	
	var analysisChartName = results.partyBasicAnalysisVO.analysisChart;
	var resultsChartName = results.partyBasicAnalysisVO.resultsChart;
	var headStr='';
	if(electionType != "Parliament")
	{
		headStr+='<H3>'+stateName+' '+electionYear+' '+electionType+' Elections Analysis for '+partyName+'</H3>';
	} if(electionType == "Parliament")
	{
		headStr+='<H3>'+electionYear+' '+electionType+' Elections Analysis for '+partyName+'</H3>';
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
	tablerDataStr+='<TD class="middle"><A href="javascript:{}" title="Click To View Constituencies" onclick="openPartyElectionResultsWindow('+electionId+','+partyId+',\'1\',\''+results.partyBasicAnalysisVO.partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+')">'+results.partyBasicAnalysisVO.seatsWon+'</A></TD>';
	tablerDataStr+='<TD class="middle"><A href="javascript:{}" title="Click To View Constituencies" onclick="openPartyElectionResultsWindow('+electionId+','+partyId+',\'0\',\''+results.partyBasicAnalysisVO.partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+')">'+results.partyBasicAnalysisVO.seatsLost+'</A></TD>';
	tablerDataStr+='<TD class="middle"><A href="javascript:{}" title="Click To View Analyzed Constituencies" onclick="openPartyElectionResultsAnalysisWindow('+electionId+','+partyId+',\'analyzed\',\''+results.partyBasicAnalysisVO.partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+')">'+results.partyBasicAnalysisVO.analyzedConsti+'</A></TD>';
	tablerDataStr+='<TD class="edge"><A href="javascript:{}"  title="Click To View Yet to be Analyzed Constituencies" onclick="openPartyElectionResultsAnalysisWindow('+electionId+','+partyId+',\'notAnalyzed\',\''+results.partyBasicAnalysisVO.partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+')">'+results.partyBasicAnalysisVO.notAnalyzedConsti+'</A></TD>';
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
			tablerDataStr+='<TD class="middle"><A href="javascript:{}" title="Click To View Constituencies" onclick="openPartyElectionResultsWindow('+electionId+','+alliancePartiesBasicDetails[j].partyId+',\'1\',\''+alliancePartiesBasicDetails[j].partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+')">'+alliancePartiesBasicDetails[j].seatsWon+'</A></TD>';
			tablerDataStr+='<TD class="middle"><A href="javascript:{}" title="Click To View Constituencies" onclick="openPartyElectionResultsWindow('+electionId+','+alliancePartiesBasicDetails[j].partyId+',\'0\',\''+alliancePartiesBasicDetails[j].partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+')">'+alliancePartiesBasicDetails[j].seatsLost+'</A></TD>';
			tablerDataStr+='<TD class="middle"><A href="javascript:{}" title="Click To View Analyzed Constituencies" onclick="openPartyElectionResultsAnalysisWindow('+electionId+','+alliancePartiesBasicDetails[j].partyId+',\'analyzed\',\''+alliancePartiesBasicDetails[j].partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+')">'+alliancePartiesBasicDetails[j].analyzedConsti+'</A></TD>';
			tablerDataStr+='<TD class="edge"><A href="javascript:{} title="Click To View Yet to be Analyzed Constituencies" onclick="openPartyElectionResultsAnalysisWindow('+electionId+','+alliancePartiesBasicDetails[j].partyId+',\'notAnalyzed\',\''+alliancePartiesBasicDetails[j].partyName+'\',\''+electionType+'\',\''+stateName+'\','+electionYear+')">'+alliancePartiesBasicDetails[j].notAnalyzedConsti+'</A></TD>';
			tablerDataStr+='</TR>';
		}
	}			
	tablerDataStr+='</TABLE>';
	tablerDetailsEl.innerHTML =tablerDataStr; 
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

function initializePage()
{
	buildPageLayout();	
}