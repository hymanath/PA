
/*===========================================
	
	electionVotingTrendz.js  is to provide the provide the functionality
	of displaying the voting trendz according to the response object.

=============================================*/



/*========================================================================
***************** Start Of ElectionVotingTrendz Object *******************
=========================================================================*/


var electionVotingTrendzObjMain =	{
										mandalVotingTrendz:{
																mandalId:'',
																mandalName:'',
																maleVotingTrendz:'',
																femaleVotingTrendz:'',
																maleAndFemaleVotingTrendz:'',
																completeVotingTrendz:'',
																resultStatus:''
															},
										constituencyVotingTrendz:{
																	state:'',
																	electionType:'',
																	electionYear:'',
																	constituencyId:'',
																	constituencyName:'',
																	otherElectionYears:'',
																	mandalsInfo:'',
																	completeTrendz:'',
																	maleTrendz:'',
																	femaleTrendz:'',
																	maleAndFemaleTrendz:''															
																 }
									};


/*========================================================================
***************** End Of ElectionVotingTrendz Object *******************
=========================================================================*/



/*
	Function to fill Districts in district select Element
*/
function fillDistrictSelect(results)
{		
	clearOptionsListForSelectElmtId("districtSelect");
	createOptionsForSelectElmtId("districtSelect",results);
}


/*
	
*/
function fillMandalSelect(results)
{
	clearOptionsListForSelectElmtId("mandalSelect");
	createOptionsForSelectElmtId("mandalSelect",results);
}

function fillConstituencySelect(results)
{
	clearOptionsListForSelectElmtId("constituencySelect");
	createOptionsForSelectElmtId("constituencySelect",results);
}



	
/*
	
*/
function setVotingTrendzValuesToJSObj(results)
{	

	var localObj = electionVotingTrendzObjMain.constituencyVotingTrendz;
	localObj.state = results.state;
	localObj.electionType = results.electionType;
	localObj.electionYear = results.electionYear;
	localObj.constituencyId = results.constituencyId;
	localObj.constituencyName = results.constituencyName;
	localObj.otherElectionYears = results.otherElectionYears;
	localObj.mandalsInfo = results.mandalsInfo;
	localObj.completeTrendz = results.completeTrendz;
	localObj.maleTrendz = results.maleTrendz;
	localObj.femaleTrendz = results.femaleTrendz;
	localObj.maleAndFemaleTrendz = results.maleAndFemaleTrendz;

	//console.log(electionVotingTrendzObjMain);

}


/*
	
*/
function buildElectionVotingTrendzLayout(votingTrendzType)
{
	var headElmt = document.getElementById("votingTrendzReportHead");
	var bodyElmt = document.getElementById("votingTrendzReportBody");

	var headStr = '';
	headStr+='Election Voting Trendz Report';
	if(headElmt)
	headElmt.innerHTML = headStr;

	var tableStr='';
	tableStr+='<div id="votingTrendzBodyMain">';
	tableStr+='<table id="votingTrendzMainTable">';
	tableStr+='<tr>';
	tableStr+='<td width="75%" style="vertical-align:top">';
	tableStr+='<div id="votingTrendzbasicInfo_outer"></div>';
	tableStr+='<div id="votersDetailsInfo_outer"></div>';
	tableStr+='</td>';
	tableStr+='<td><div id="constituencyMap_outer"></div></td>';
	tableStr+='</tr>';

	tableStr+='<tr>';
	tableStr+='<td colspan="2"><div id="votingTrendzGraph"></div></td>';
	tableStr+='</tr>';

	tableStr+='<tr>';
	tableStr+='<td colspan="2"><div id="CandidateTrendzInfoGraph"></div></td>';
	tableStr+='</tr>';

	tableStr+='<tr>';
	tableStr+='<td colspan="2"><div id="partyResults_outer_main"></div></td>';
	tableStr+='</tr>';

	tableStr+='</table>';
	tableStr+='</div>';
	
	
	if(bodyElmt)
		bodyElmt.innerHTML = tableStr;
	
	

	buildVotingTrendzBasicInfo(electionVotingTrendzObjMain.constituencyVotingTrendz);
	buildConstituencymap();	
	buildVotingTrendzGraph();
	buildCandidateTrendz();
	buildPartyResults(electionVotingTrendzObjMain.constituencyVotingTrendz);	
}


function buildVotingTrendzGraph()
{
	var divElmt = document.getElementById("votingTrendzGraph");
	var str = '';
	str+='<div id="votingTrendzGraph_head" class="reportHeaders"> Voting Trends Graph</div>';
	str+='<div id="votingTrendzGraph_body"></div>';

	if(divElmt)
		divElmt.innerHTML = str;
}

function buildCandidateTrendz()
{
	var divElmt = document.getElementById("CandidateTrendzInfoGraph");
	var str = '';
	str+='<div id="CandidateTrendzInfoGraph_head" class="reportHeaders"> Candidate Voting Trends Graph</div>';
	str+='<div id="CandidateTrendzInfoGraph_body"></div>';

	if(divElmt)
		divElmt.innerHTML = str;
}



function buildVotingTrendzBasicInfo(data)
{

	var str = '';
	str+='<div id="votingTrendzbasicInfo_main">';	
	str+='<div id="votingTrendzbasicInfo_head" class="reportHeaders">Constituency Info</div>';
	str+='<div id="votingTrendzbasicInfo_body">';	
	str+='<table id="constituencyDetailstable" class="constituencyInfoTable" width="90%">';
	str+='<tr>';
	str+='<th>Constituency</th>';
	str+='<td>'+data.constituencyName+'</td>';
	str+='<th>Constituency Type</th>';
	str+='<td>'+data.electionType+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th> State </th>';
	str+='<td>'+data.state+'</td>';
	str+='<th>Election Year</th>';
	str+='<td>'+data.electionYear+'</td>';
	str+='<tr>';

	str+='<table>';
	str+='</div>';
	str+='</div>';

	var constituencyObj = electionVotingTrendzObjMain.constituencyVotingTrendz.completeTrendz.constituencyWiseResults;
	var polledVotes = constituencyObj.partyElecResults[0].validVotes;

	str+='<div id="voterDetails_main">';
	str+='<div id="voterDetails_head" class="reportHeaders"> Constituency Voters Info</div>';
	str+='<div id="voterDetails_body">';
	str+='<table id="VotersInfoTable" class="constituencyInfoTable" width="90%">';
	str+='<tr>';
	str+='<th colspan="2">Total Number of Booths</th>';
	str+='<td colspan="2"> '+constituencyObj.noOfBooths+' </td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th> No Of Voters </th>';
	str+='<td> '+ constituencyObj.noOfVoters +' </td>';
	str+='<th> Valid Voters </th>';
	str+='<td> '+polledVotes+' </td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th> Male Voters </th>';
	str+='<td> '+constituencyObj.maleVoters+' </td>';
	str+='<th> Female Voters </th>';
	str+='<td> '+constituencyObj.femaleVoters+' </td>';
	str+='</tr>';
	str+='<table>';	
	str+='</div>';
	str+='<div>';
	
	basicInfoOuterElmt = document.getElementById("votingTrendzbasicInfo_outer");
	if(basicInfoOuterElmt)
		basicInfoOuterElmt.innerHTML = str;
}



function buildPartyResults(data)
{	
	var resultsStr='';
	resultsStr+='<div id="partyElectionResultsInfo_main">';
	resultsStr+='<div id="partyElectionResultsInfo_head" class="reportHeaders">Party Election Results Info</div>';
	resultsStr+='<div id="partyElectionResultsInfo_body" class="yui-skin-sam">';
	resultsStr+='<div id="partyElectionResultsInfo_body_table"></div>';
	resultsStr+='</div>';
	resultsStr+='</div>';

	partyResultsOuterDivElmt = document.getElementById("partyResults_outer_main");
	if(partyResultsOuterDivElmt)
		partyResultsOuterDivElmt.innerHTML=resultsStr;	

	var constituencyObj = electionVotingTrendzObjMain.constituencyVotingTrendz.completeTrendz.constituencyWiseResults;
	

	var myColumnDefs = [ 	           
		{key:"candidateName",label : "Candidate",sortable:true,resizeable:true}, 
		{key:"partyName",label : "Party", sortable:true, resizeable:true}, 
		{key:"partyFlag",label : "Flag", resizeable:true},
		{key:"votesEarned",label : "Votes Earned", sortable:true, resizeable:true},
		{key:"votesPercentage",label : "Votes %", sortable:true, resizeable:true},    
		{key:"status",label : "Status", sortable:true, resizeable:true}
	]; 
	
	var polledVotes = constituencyObj.partyElecResults[0].validVotes;
	var myDataSource = new YAHOO.util.DataSource(constituencyObj.partyElecResults); 
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	myDataSource.responseSchema = { 
		fields: [
					{key:"candidateName"},
					{key:"partyName"},
					{key:"candidateName"},
					{key:"partyFlag"},
					{key:"votesEarned",parser:"number"},
					{key:"votesPercentage",parser:"number"},
		            {key:"status"}
				] 
	};

	var captionStr = " <b> No. Of Booths : </b>"+constituencyObj.noOfBooths+" <b> No. Of Voters : </b>"+constituencyObj.noOfVoters+" <b>Male Voters : </b>"+constituencyObj.maleVoters+" <b>Female Voters : </b>"+constituencyObj.femaleVoters+" <b>Valid Voters : </b>"+  polledVotes;
	 var myDataTable = new YAHOO.widget.DataTable("partyElectionResultsInfo_body_table",myColumnDefs, myDataSource,{caption:captionStr}); 
}

function buildMandalsAndYearsInfo(data)
{
	var mandalStr='';
	mandalStr+='<div id="mandalsInfoDiv">';
	mandalStr+='<table width="100%">';
	mandalStr+='<tr>';
	mandalStr+='<td>';
	mandalStr+='<fieldset>';
	mandalStr+='<legend> Mandals in '+electionVotingTrendzObjMain.constituencyVotingTrendz.constituencyName+' Constituency</legend>';
	mandalStr+='<div>';
	mandalStr+='<table>';
	mandalStr+='<tr>';
	for(var mandal in electionVotingTrendzObjMain.constituencyVotingTrendz.mandalsInfo)
	{
		mandalInfo = electionVotingTrendzObjMain.constituencyVotingTrendz.mandalsInfo[mandal];		
		
		if(mandal%2 == 0)
		{
			mandalStr+='</tr><tr>';			
		}

		mandalStr+='<td style="padding:10px;"> <span id="'+mandalInfo.id+'" class="mandalSpan"> '+mandalInfo.name+' </span></td>';		
	}

	mandalStr+='</table>';
	mandalStr+='</div>';
	mandalStr+='</fieldset>';
	mandalStr+='</td>';
	mandalStr+='<td>';
	mandalStr+='<fieldset>';
	mandalStr+='<legend> Voting Trends For Election Years</legend>';
	mandalStr+='<div>';
	mandalStr+='<table>';
	mandalStr+='<tr>';
	for(var y in electionVotingTrendzObjMain.constituencyVotingTrendz.otherElectionYears)
	{
		year = electionVotingTrendzObjMain.constituencyVotingTrendz.otherElectionYears[y];
		
		if(y%2 == 0)
		{
			mandalStr+='</tr><tr>';			
		}
		mandalStr+='<td style="padding:10px;"<span class="mandalSpan"> '+year+' </span></td>';
	}
	mandalStr+='</table>';
	mandalStr+='</div>';
	mandalStr+='</fieldset>';
	mandalStr+='</td>';
	mandalStr+='</tr>';
	mandalStr+='</table>';
	mandalStr+='</div>';
	
	electionYearsOuterDivElmt = document.getElementById("electionYears_outer_main");

	if(electionYearsOuterDivElmt)
		electionYearsOuterDivElmt.innerHTML=mandalStr;	
}
	
	


function buildConstituencymap()
{
	var localObj = electionVotingTrendzObjMain.constituencyVotingTrendz;
	

	var address=localObj.constituencyName+'-'+localObj.state+',India';		
	var map = new GMap2(document.getElementById("constituencyMap_outer"));
	var geocoder = new GClientGeocoder();
	geocoder.getLatLng(address,
	function(point)
	{			
	  if (!point) 
	  {
		alert(address + " not found");
	  }
	  else 
	  {
		map.setCenter(point, 8);
		var marker = new GMarker(point);
		map.addOverlay(marker);
		map.setUIToDefault();
	  }
	})
}
/*
	******** function to build carousel items for booth level results **********
*/
function buildboothTrendzCarousal(divId)
{	
	var carousel = new YAHOO.widget.Carousel(divId,
						{
							carouselEl: "UL",
							isCircular: true,
							isVertical: true,
							numVisible: 6,
							animation: { speed: 1.0 },
							autoPlayInterval: 2000
						});

	carousel.render(); 
	carousel.show();
}


/**
 * Function to show Election Voting Trendz.
 * @method showElectionVotingTrendz
 * @param {Object} results .The results object from which the election voting trends is built.
 * @param {String} divId . The container Element Id where the voting trends is built.  
 */

function showElectionVotingTrendz(results,divId)
{
	var containerElement = document.getElementById(divId);
	if(!containerElement)
	{
		alert("No Div Container To Build Election Voting Trendz ....");
		return;
	}

	containerElement.style.display = 'block';
	
	// Building the basic header,body,footer content.

	var str = '';
	str+='<div id="votingTrendzReportHead"></div>';
	str+='<div id="votingTrendzReportBody"></div>';
	str+='<div id="votingTrendzReportFooter"></div>';

	var mainDivElmt = document.createElement('div');
	mainDivElmt.setAttribute('id','votingTrendzReportMain');
	mainDivElmt.innerHTML=str; 
	containerElement.appendChild(mainDivElmt);

	// Building the javascript objects from the results passed and building the voting trendz

	setVotingTrendzValuesToJSObj(results);
	buildElectionVotingTrendzLayout("completeTrendz");
}




/*
	
*/
function buildYUIInputSelectBox()
{
	
	
}


/*
	
*/
function intializeVotingTrendzReport()
{
	buildYUIInputSelectBox();
}




function dummyData()
{
	for(var i in data.constiWiseBoothInfo)
	{
		var boothInfo = data.constiWiseBoothInfo[i];
		var partyInfo = data.constiWisePartyResults[i];		

		str+='<div id="votingTrendsResults_main_'+boothInfo.constituencyId+'" class="votingTrendzResultsMainClass">';
		str+='		<div id="votingTrendsResults_head_'+boothInfo.constituencyId+'" class="votingTrendzResultsHeaderClass">';
		str+='			Voting trends for the constituency : '+boothInfo.constiName;
		str+='		</div>';
		str+='		<div id="boothVotingTrendsResults_main_'+boothInfo.constituencyId+'">';
		str+='			<div id="boothVotingTrendsResults_head_'+boothInfo.constituencyId+'" class="votingTrendzResultsHeaderClass"> Booth level Results</div>';
		str+='			<div id="boothVotingTrendsResults_body_'+boothInfo.constituencyId+'">';				
		for(var j in boothInfo.boothsDetails)
		{
							var info = boothInfo.boothsDetails[j];
							
							var str = '';
							str+='<table id="boothTrendzTable_'+info.boothID+'" class="boothTable">';
							str+='<tr>';
							str+='<th> Part No </th>';
							str+='<td>'+info.partNo+'</td>';
							str+='</tr>';
							str+='<tr>';
							str+='<th> Location </th>';
							str+='<td>'+info.location+'</td>';
							str+='</tr>';
							str+='<tr>';
							str+='<th> Villages Covered </th>';
							str+='<td>'+info.villagesCovered+'</td>';
							str+='</tr>';
							str+='<tr>';
							str+='<th> Male Votes </th>';
							str+='<td>'+info.maleVotes+'</td>';
							str+='<th> Female Votes </th>';
							str+='<td>'+info.femaleVotes+'</td>';
							str+='<th> Total Votes </th>';
							str+='<td>'+info.totalVotes+'</td>';
							str+='</tr>';
							str+='</table>';
		}	
		str+='			</div>';
		str+='		</div>';
		

		str+='		<div id="partyVotingTrendsResultsMain_'+boothInfo.constituencyId+'">';
		str+='			<div id="partyVotingTrendsResults_head_'+boothInfo.constituencyId+'" class="votingTrendzResultsHeaderClass">Party Election Results</div>';
		str+='			<div id="partyVotingTrendsResults_body_'+boothInfo.constituencyId+'" class="partyVotingTrendsResults">';		
		str+='				<table id="table_'+boothInfo.constituencyId+'" class="boothTable">';
		str+='				<tr>';
		str+='				<th> Candidate Name</th>';	
		str+='				<th> Party Name</th>';	
		str+='				<th> Party Flag</th>';	
		str+='				<th> Party Logo</th>';	
		str+='				<th> Male Voters</th>';	
		str+='				<th> Female Voters</th>';	
		str+='				<th> Total Voters</th>';	
		str+='				<th> Valid Votes</th>';	
		str+='				<th> Votes Earned</th>';	
		str+='				<th> Votes % </th>';	
		str+='				</tr>';
		
		for(var k in partyInfo.partyElecResults)
		{
			var pInfo = partyInfo.partyElecResults[k];
						str+='<tr>';
						str+='<td>'+pInfo.candidateName+'</td>';	
						str+='<td>'+pInfo.partyName+'</td>';	
						str+='<td>'+pInfo.partyFlag+'</td>';	
						str+='<td>'+pInfo.partyLogo+'</td>';	
						str+='<td>'+pInfo.maleVoters+'</td>';	
						str+='<td>'+pInfo.femaleVoters+'</td>';	
						str+='<td>'+pInfo.validVotes+'</td>';	
						str+='<td>'+pInfo.votesEarned+'</td>';	
						str+='<td>'+pInfo.votesPercentage+'</td>';	
						str+='</tr>';
		}
		str+='			</table>';
		
		str+='		</div>';
		str+='	</div>';
		str+='</div>';
	}


	/*
	******** function to build data table for party results *************
*/
function buildElectionResultsDatatable(divId)
{
	var constituencyObj = electionVotingTrendzObjMain.constituencyVotingTrendz.completeTrendz.constituencyWiseResults;
	

	var myColumnDefs = [ 	           
		{key:"candidateName",label : "Candidate",sortable:true,resizeable:true}, 
		{key:"partyName",label : "Party", sortable:true, resizeable:true}, 
		{key:"partyFlag",label : "Flag", resizeable:true},
		{key:"partyLogo",label : "Logo", sortable:true, resizeable:true},
		{key:"maleVoters",label : "Male Voters", sortable:true, resizeable:true}, 
		{key:"femaleVoters",label : "Female Voters",sortable:true, resizeable:true}, 
		{key:"totalVoters",label : "Total Voters", sortable:true, resizeable:true},    
		{key:"votesEarned",label : "Votes Earned", sortable:true, resizeable:true},
		{key:"votesPercentage",label : "Votes %", sortable:true, resizeable:true},    
	    {key:"status",label : "Status", sortable:true, resizeable:true}
		
	]; 

	var myDataSource = new YAHOO.util.DataSource(constituencyObj.partyElecResults); 
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	myDataSource.responseSchema = { 
		fields: [
					{key:"candidateName"},
					{key:"partyName"},
					{key:"candidateName"},
					{key:"partyFlag"},
					{key:"partyLogo"},
					{key:"maleVoters",parser:"number"},
					{key:"femaleVoters",parser:"number"},
					{key:"totalVoters",parser:"number"},
					{key:"votesEarned",parser:"number"},
					{key:"votesPercentage",parser:"number"},
			        {key:"status"}
				] 
	};

	var captionStr = "<b> No. Of Booths : </b>"+constituencyObj.noOfBooths+" <b> No. Of Voters : </b>"+constituencyObj.noOfVoters;
	 var myDataTable = new YAHOO.widget.DataTable(divId,myColumnDefs, myDataSource,{caption:captionStr}); 
}

}