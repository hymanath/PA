
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
	var electionTypeId = results.electionTypeId;
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
		//headStr+='';
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
	contentStr+='<DIV class="wonLostPosHeading">Analysis in Party Lost Positions</DIV>';
	contentStr+='<DIV>';
	contentStr+='<TABLE class="wonLostPosTable" width="80%">';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">Seats Lost</TH>';
	contentStr+='<TD style="width:30%">'+results.resultTypeValue+'</TD>';
	contentStr+='</TR>';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">Analyzed Constituencies</TH>';
	contentStr+='<TD style="width:30%">'+results.analyzedConsti+'</TD>';
	contentStr+='</TR>';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">Yet to be  Analyzed Constituencies</TH>';
	contentStr+='<TD style="width:30%">'+results.notAnalyzedConsti+'</TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';								
	contentStr+='</DIV>';
	divEl.innerHTML=contentStr;
	/*<DIV>
		<DIV style="text-decoration:underline;font-size:15px;font-weight:bold;text-align:left;margin-left:70px;margin-top:10px;">Reasons</DIV>
		<TABLE class="wonLostPosTable" width="80%">
			<TR>
				<TH style="width:50%">No Candidate Ifluence</TH>
				<TD style="width:5%">10</TD>
				<TD style="width:25%"><A title="Click To View Results" href="javascript:{}">View Results</A></TD>
			</TR>
			<TR>
				<TH style="width:50%">Alliance Impact</TH>
				<TD style="width:5%">10</TD>
				<TD style="width:25%"><A title="Click To View Results" href="javascript:{}">View Results</A></TD>
			</TR>
			<TR>
				<TH style="width:50%">Poor Campaign</TH>
				<TD style="width:5%">20</TD>
				<TD style="width:25%"><A title="Click To View Results" href="javascript:{}">View Results</A></TD>
			</TR>
		</TABLE>
		<DIV>
			<H3 style="width:510px;">Constituencies with Multiple Reasons</H3>
				<TABLE  cellpadding="0" cellspacing="0" width="75%"  class="multipleClassificationsTable">
					<TR>
						<TH width="30%">No of Analysis Reasons</TH>
						<TH width="5%">2</TH>
						<TH width="5%">3</TH>
						<TH width="5%">4</TH>
						<TH width="5%">N</TH>								
					</TR>
					<TR>
						<TD width="30%"><B>No of Constituencies</B></TD>
						<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}">10</A></TD>
						<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}">3</A></TD>
						<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}">5</A></TD>
						<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}">6</A></TD>								
					</TR>
				</TABLE>							
		</DIV>	
	</DIV>
	*/
}
function showAnalysisDetailsInPartyWonPositions(results)
{
	var divEl = document.getElementById("wonPosAnalisisDetails");
	contentStr='';
	contentStr+='<DIV class="wonLostPosHeading">Analysis in Party Won Positions</DIV>';
	contentStr+='<DIV>';
	contentStr+='<TABLE class="wonLostPosTable" width="80%">';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">Seats Won</TH>';
	contentStr+='<TD style="width:30%">'+results.resultTypeValue+'</TD>';
	contentStr+='</TR>';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">Analyzed Constituencies</TH>';
	contentStr+='<TD style="width:30%">'+results.analyzedConsti+'</TD>';
	contentStr+='</TR>';
	contentStr+='<TR>';
	contentStr+='<TH style="width:50%">Yet to be  Analyzed Constituencies</TH>';
	contentStr+='<TD style="width:30%">'+results.notAnalyzedConsti+'</TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';								
	contentStr+='</DIV>';
	divEl.innerHTML=contentStr;
}

function initializePage()
{
	buildPageLayout();
	
}