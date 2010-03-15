
var constituencyPageMainObj={
								constituencyAddress:'',
								contextPath:'',
								constituencyInfo:{
													constituencyName:'',
													districtName:'',
													stateName:'',
													startDate:'',
													deformDate:'',
													constituencyType:''
												 },
								constituencyElectionInfo:[],
								constituencyVotersInfo:[],
								presentAssemblyCandidate:[],
								presentParliamentCandidate:[],
								problemsInfo:[],
								votingTrendzInfo:{
													totalVoters:'',
													maleVoters:'',
													femaleVoters:'',
													maleAndFemaleVoters:'',
													totalPolledVotes:'',
													malePolledVotes:'',
													femalePolledVotes:'',
													maleAndFemalePolledVotes:'',
													pollingPercent:'',
													malePollingPercent:'',
													femalePollingPercent:'',
													maleAndFemaleVotesPercent:'',
													votingTrendzTable:[]
												 }
							};

/*var address="${constituencyDetails.constituencyName},${constituencyDetails.districtName},${constituencyDetails.stateName}";		
		var map = new GMap2(document.getElementById("map_canvas"));
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


function showDetails(id)
{	
	tr=document.getElementsByTagName('tr')

	for (i=0;i<tr.length;i++)
	{			
		if (tr[i].id && tr[i].id==id)
		{				
			if ( tr[i].style.display=='none' )
			{
				tr[i].style.display = '';
			}
			else
			{
				tr[i].style.display = 'none';
			}
		}
	}		
}
function getString() 
{
  alert("In function");
  if (GBrowserIsCompatible())
  {        
	//var str=document.getElementById("add").value;
	//console.log("In second if");
//	showAddress(str);      
  }
}

function showAddress(address)
{
	var map = new GMap2(document.getElementById("map_canvas"));
	var geocoder = new GClientGeocoder();
	geocoder.getLatLng(address,
	function(point)
	{			
	  if (!point) 
	  {
		//alert(address + " not found");
	  }
	  else 
	  {
		map.setCenter(point, 14);
		var marker = new GMarker(point);
		map.addOverlay(marker);
		//marker.openInfoWindowHtml(address);
		map.setUIToDefault();
	  }
	})
}
*/

function buildRightlayoutMap()
{
	var address=constituencyPageMainObj.constituencyAddress;		
		var map = new GMap2(document.getElementById("map_canvas"));
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

function buildConstituencyInfo()
{
	var defDate = constituencyPageMainObj.constituencyInfo.deformDate;

	var divElmtHead = document.getElementById("constituencyInfoDiv_Head");
	var divElmtBody = document.getElementById("constituencyInfoDiv_Body");

	var heading='<h3>Constituency Info </h3>';
	if(divElmtHead)
		divElmtHead.innerHTML=heading;
	
	var str = '';
	str+='<table id="constituencyInfoTable">';
	str+='<tr>';
	str+='<th> Constituency Name </th>';
	str+='<th> : </th>';
	str+='<td> '+constituencyPageMainObj.constituencyInfo.constituencyName+' </td>';
	str+='</tr>';
	if(constituencyPageMainObj.constituencyInfo.constituencyType.length > 0)
	{
		str+='<tr>';
		str+='<th> Constituency Type </th>';
		str+='<th> : </th>';
		str+='<td> '+constituencyPageMainObj.constituencyInfo.constituencyType+' </td>';
		str+='</tr>';
	}
	str+='<tr>';
	str+='<th> District Name </th>';
	str+='<th> : </th>';
	str+='<td> '+constituencyPageMainObj.constituencyInfo.districtName+' </td>';
	str+='</tr>';
	str+='<tr>';
	str+='<th> State Name </th>';
	str+='<th> : </th>';
	str+='<td> '+constituencyPageMainObj.constituencyInfo.stateName+' </td>';
	str+='</tr>';
	if(constituencyPageMainObj.constituencyInfo.startDate.length > 0)
	{
		str+='<tr>';
		str+='<th> Start Date </th>';
		str+='<th> : </th>';
		str+='<td> '+constituencyPageMainObj.constituencyInfo.startDate+' </td>';
		str+='</tr>';
	}
	if(constituencyPageMainObj.constituencyInfo.deformDate.length > 0)
	{
		str+='<tr>';
		str+='<th> Deformation Year </th>';
		str+='<th> : </th>';
		str+='<td> '+defDate.substring(0,4)+' </td>';
		str+='</tr>';
	}
	
	if(divElmtBody)
		divElmtBody.innerHTML=str;

}

function buildElectionResults()
{
	var HeadElmt = document.getElementById('constituencyPageElectionInfoDiv_Head');
	var BodyElmt = document.getElementById('constituencyPageElectionInfoDiv_Body');
	
	if(HeadElmt)
		HeadElmt.innerHTML = ' Election Information in '+constituencyPageMainObj.constituencyInfo.constituencyName;

	var elecStr = '';
	for(var i in constituencyPageMainObj.constituencyElectionInfo)
	{
		var data = constituencyPageMainObj.constituencyElectionInfo[i];
		var info = constituencyPageMainObj.constituencyInfo;
		elecStr+='<div id="constituencyElectionInfo_'+i+'" class="electionInformationClass" onclick="showDetailedElectionResult(this.id)">';
		elecStr+='<span id="pointerImg"> <img height="10" width="10" src="'+constituencyPageMainObj.contextPath+'/images/icons/arrow.png"/></span>';
		elecStr+='<span id=""> '+info.constituencyType+' Election Results in '+data.year+' - '+data.candidateName+' Won with '+data.votesPercentage+' votes %</span>';		
		elecStr+='</div>';
	}
	
	if(BodyElmt)
		BodyElmt.innerHTML=elecStr;
}

function showDetailedElectionResult(id)
{
	
	var index = id.substring((id.indexOf('_')+1),id.length);
	
	var data = constituencyPageMainObj.constituencyElectionInfo[index];
	var info = constituencyPageMainObj.constituencyInfo;
		
	var str='';
	str+='<fieldset id="constituencyInfoFieldSet">';
	str+='<legend> Constituency Info </legend>';
	str+='<div id="coinstituencyInfoDiv">';
	str+='<table id="constituencyInfoTableClass" class="legendTable" width="100%">';
	str+='<tr>';
	str+='<th>Constituency Name</th>';
	str+='<td>'+info.constituencyName+'</td>';
	str+='<th>Constituency Type</th>';
	str+='<td>'+info.constituencyType+'</td>';	
	str+='</tr>';

	str+='<tr>';	
	str+='<th>District</th>';
	str+='<td>'+info.districtName+'</td>';
	str+='<th>District</th>';
	str+='<td>'+info.stateName+'</td>';	
	str+='</tr>';	
	str+='</table>';
	str+='</div>';
	str+='</fieldset>';
	//--------------
	str+='<fieldset id="WinningCandidateFieldSet">';
	str+='<legend> Winning Candidate Info </legend>';
	str+='<div id="WinningCandidateDiv">';
	str+='<table id="WinningCandidateTableClass" class="legendTable" width="100%">';
	str+='<tr>';
	str+='<th>Name</th>';
	str+='<td colspan="3">'+data.candidateName+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Party</th>';
	str+='<td>'+data.partyName+'</td>';	
	str+='<th>Year</th>';
	str+='<td>'+data.year+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Votes Earned</th>';
	str+='<td>'+data.votesEarned+'</td>';	
	str+='<th>Votes Percentage</th>';
	str+='<td>'+data.votesPercentage+'</td>'
	str+='</tr>';
	str+='</table>';
	str+='</div>';	
	str+='</fieldset>';
	//--------
	str+='<fieldset id="oppositionResultsField">';
	str+='<legend> Opposition\'s Results Info </legend>';
	str+='<div id="oppCandResultsDiv">';	
	str+='</div>';
	str+='</fieldset>';

	ElectionResultPanel = new YAHOO.widget.Panel("electionResults_Panel", 
				{
					width:"800px", 
					fixedcenter : false, 
					visible : true,  
					constraintoviewport : true,
					x:200,
					y:400,
					iframe :true,
					modal :true,
					visible:true,						
					draggable:true, 
					close:true
				} ); 
	

	ElectionResultPanel.render();
	ElectionResultPanel.setHeader(' Election Results');
	ElectionResultPanel.setBody(str);
	
	 var myDataSource = new YAHOO.util.DataSource(data.oppositionCandInfo); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	 myDataSource.responseSchema = { 
	            fields: [
							{key:"candidateName"},
							{key:"partyName"},
							{key:"year",parser:"number"},
							{key:"votesEarned",parser:"number"},
							{key:"votesPercentage",parser:"number"}
						] 
	        }; 
	
	 var myColumnDefs = [ 
	            {key:"candidateName",label:'Candidate Name', sortable:true, resizeable:true}, 
	            {key:"partyName", label:'Party Name', sortable:true, resizeable:true}, 
	            {key:"year", label:'Year',sortable:true, resizeable:true}, 
	            {key:"votesEarned",label:'Votes Earned',formatter:YAHOO.widget.DataTable.formatNumber, sortable:true, resizeable:true}, 
	            {key:"votesPercentage",label:'Votes %', sortable:true, resizeable:true} 
	        ]; 
	 
	var myDataTable = new YAHOO.widget.DataTable("oppCandResultsDiv",myColumnDefs, myDataSource); 
}

/*
function buildConstituencyLayout()
{
	var constituencyPageLayout = new YAHOO.widget.Layout('constituencyPageLayoutDiv', { 
			height:900,
			units: [					 
					{ 
						position: 'right',
						header:false,
						width: 280,							
						resize: false,
						gutter: '5px',
						collapse: false,
						scroll: false,
						body: 'constituencyPageRightMapDiv',
						animate: true
					},	 
					{ 
						position: 'center',						
						body: 'constituencyPageCenterInfoDiv',
						resize: false,
						gutter: '5px',
						collapse: true,
						scroll: false,						
						animate: true
					} 
	    ] 
		}); 
		constituencyPageLayout.render(); 
}*/

function buildCenterVotersCandidateInfoContent()
{
	var elmt = document.getElementById("mandalsVotersInfoDiv_Body");
	
	if(constituencyPageMainObj.constituencyVotersInfo.length == 0)
	{
		elmt.innerHTML='Voter Info Unavailable';
			return;
	}
	

	for(var i in constituencyPageMainObj.constituencyVotersInfo)
	{
		var data = constituencyPageMainObj.constituencyVotersInfo[i];

		var divChild = document.createElement('div');
		divChild.setAttribute("id","divChild"+i);

		var str = '';
		str+='<div id="divChild_Head_'+i+'" class="voterInfoHead">';
		if(data.year == "2009")
			str+='Mandals After Delimitation';
		else
			str+='Mandals Before Delimitation';
		str+='</div>';
		str+='<div id="divChild_Body_'+i+'" class="voterInfoBody"></div>';
		divChild.innerHTML=str;

		if(elmt)
			elmt.appendChild(divChild);
		
		 var myDataSource = new YAHOO.util.DataSource(data.info); 
		 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		 myDataSource.responseSchema = { 
					fields: [
								{	key : "mandalName"},
								{	key : "mandalMaleVoters",parser:"number"},
								{	key : "mandalFemaleVoters",parser:"number"},
								{	key : "mandalTotalVoters",parser:"number"},
								{	key : "isPartial"}
							]
				}; 
		
		 var myColumnDefs = [ 
					{key:"mandalName",label:'Mandal Name', sortable:true, resizeable:true}, 
					{key:"mandalMaleVoters", label:'Male Voters', sortable:true, resizeable:true}, 
					{key:"mandalFemaleVoters", label:'Female Voters',sortable:true, resizeable:true}, 
					{key:"mandalTotalVoters",label:'Total Voters', sortable:true, resizeable:true},
					{key:"isPartial",label:'Is Partial', sortable:true, resizeable:true}
				]; 
		 
		var myDataTable = new YAHOO.widget.DataTable("divChild_Body_"+i+"",myColumnDefs, myDataSource); 

	}
}

function showCurrentlyElectedCandidate()
{	

	var HeadElmt = document.getElementById('constituencyPageCandidateInfo_Head');
	var BodyElmt = document.getElementById('constituencyPageCandidateInfo_Body');

	var headStr = '';
	headStr+='Elected Candidate Info';
	if(HeadElmt)
		HeadElmt.innerHTML=headStr;

	/* ---- Building Assembly candidate Info datatable ---- */
	
	if(constituencyPageMainObj.presentAssemblyCandidate.length == 0 || constituencyPageMainObj.presentParliamentCandidate == 0)
	{		
		
		BodyElmt.innerHTML = 'This constituency has been delimitated ';
		return;
	}
	 var myDataSource = new YAHOO.util.DataSource(constituencyPageMainObj.presentAssemblyCandidate); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	 myDataSource.responseSchema = { 
				fields: [
							{	key : "candidateName"},
							{	key : "constituencyName"},
							{	key : "party"},
							{	key : "partyFlag"}
						]
			}; 
	
	 var myColumnDefs = [ 
				{key:"candidateName",label:'Candidate Name', sortable:true, resizeable:true}, 
				{key:"constituencyName", label:'Constituency Name', sortable:true, resizeable:true}, 
				{key:"party", label:'Party',sortable:true, resizeable:true},
				{key:"partyFlag", label:'Party Flag', resizeable:true}
			]; 
		 
	var myDataTable = new YAHOO.widget.DataTable("constituencyPageCandidateInfo_Assembly",myColumnDefs, myDataSource,{caption:"Assembly Candidate : "}); 

	
	/* ---- Building Parliament candidate Info datatable ---- */

	 var myDataSource = new YAHOO.util.DataSource(constituencyPageMainObj.presentParliamentCandidate); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	 myDataSource.responseSchema = { 
				fields: [
							{	key : "candidateName"},
							{	key : "constituencyName"},
							{	key : "party"},
							{	key : "partyFlag"}
						]
			}; 
	
	 var myColumnDefs = [ 
				{key:"candidateName",label:'Candidate Name', sortable:true, resizeable:true}, 
				{key:"constituencyName", label:'Constituency Name', sortable:true, resizeable:true}, 
				{key:"party", label:'Party',sortable:true, resizeable:true},
				{key:"partyFlag", label:'Party Flag', resizeable:true}
			]; 
		 
	var myDataTable = new YAHOO.widget.DataTable("constituencyPageCandidateInfo_Parliament",myColumnDefs, myDataSource,{caption:"Parliament Candidate : "}); 
}

function buildProblemViewingWindow()
{
	var headElmt = document.getElementById('problemViewingDiv_Head');
	var bodyElmt = document.getElementById('problemViewingDiv_Body');
	
	if(constituencyPageMainObj.presentAssemblyCandidate.length == 0 || constituencyPageMainObj.presentParliamentCandidate == 0)
	{		
		//bodyElmt.innerHTML = 'This constituency has been delimitated ';
		return;
	}

	var str='';
	str+='<fieldset id="problemViewingFieldSet">';
	str+='<legend> View Your constituency Problems</legend>';
	str+='<div id="problemViewingContentDiv" class="problemPostingContentDivClass">';	
	str+='<marquee direction="up" scrolldelay="200" onmouseover="this.stop();" onmouseout="this.start();">';

	if(constituencyPageMainObj.problemsInfo.length == 0)
	{
		str+='<div class="problemDataDivClass" onclick="javascript:{}">';
		str+='<span><img height="10" width="10" src="/PartyAnalyst/images/icons/constituencyPage/bullet_blue.png"></img></span>';
		str+='<span> No problems has been posted </span>';
		str+='</div>';
	}
	else
	{
		for(var i in constituencyPageMainObj.problemsInfo)
		{
			var data = constituencyPageMainObj.problemsInfo[i];
			str+='<div class="problemDataDivClass" onclick="javascript:{}">';
			str+='<span><img height="10" width="10" src="/PartyAnalyst/images/icons/constituencyPage/bullet_blue.png"></img></span>';
			str+='<span> '+data.name+' </span>';
			str+='</div>';
		}
	}
	
	str+='</marquee>';
	str+='</div>';
	str+='</fieldset>';
	
	if(bodyElmt)
		bodyElmt.innerHTML=str;
}

function buildProblemPostingWindow()
{
	var headElmt = document.getElementById('problemPostingDiv_Head');
	var bodyElmt = document.getElementById('problemPostingDiv_Body');
	
	if(constituencyPageMainObj.presentAssemblyCandidate.length == 0 || constituencyPageMainObj.presentParliamentCandidate == 0)
	{		
		//bodyElmt.innerHTML = 'This constituency has been delimitated ';
		return;
	}

	var str='';
	str+='<fieldset id="ProblemPostingFieldSet">';
	str+='<legend> Post Your constituency Problem</legend>';
	str+='<div id="ProblemPostingContentDiv" class="problemPostingContentDivClass">';	
	str+='<div>Post your constituency problem and bring it to the all people notice.</div>';
	str+='<div id="problemPostingButtonDiv"><input type="button" id="postButton" value = "Post" onclick="javascript:{}"/></div>';
	str+='</div>';
	str+='</fieldset>';
	
	if(bodyElmt)
		bodyElmt.innerHTML=str;

	var postButton = new YAHOO.widget.Button("postButton");

}

function buildConstituencyConnectPeopleWindow()
{
	var headElmt = document.getElementById('constituencyPeopleConnectDiv_Head');
	var bodyElmt = document.getElementById('constituencyPeopleConnectDiv_Body');

	var headStr = 'Connect To Your Constituency People';
	if(headElmt)
		headElmt.innerHTML=headStr;

	var bodyStr='';
	bodyStr+='<div id="connectedNumberDiv"> ';
	bodyStr+='<span><img height="20" width="25" src="/PartyAnalyst/images/icons/constituencyPage/groups.png"></img></span>';
	bodyStr+='<span style="position:relative;left:5px;top:-5px;"> 20 people connected to this constituency </span>';
	bodyStr+='</div>';
	bodyStr+='<div id="connectedPersonsDiv">';
	bodyStr+='<table width="100%">';
	bodyStr+='<tr>';
	bodyStr+='<td>';
	bodyStr+='<table width="100%">';
	bodyStr+='<tr>';
	bodyStr+='<td rowspan="2" width="25%"><span><img height="40" width="35" src="/PartyAnalyst/images/icons/constituencyPage/human1.png"/></span></td>';
	bodyStr+='<td align="left"><span class="groupPersonNameSpanClass">Sai Krishna </span></td>';
	bodyStr+='</tr>';
	bodyStr+='<tr>';	
	bodyStr+='<td align="right"><span class="groupPersonMessageSpanClass">Send Mail</span></td>';
	bodyStr+='</tr>';
	bodyStr+='</table>';
	bodyStr+='</td>';
	bodyStr+='</tr>';

	bodyStr+='<tr>';
	bodyStr+='<td><div style="border:1px solid #F1F3F5;margin:0px 5px 0px 5px"></div></td>';
	bodyStr+='</tr>';
	
	bodyStr+='<tr>';
	bodyStr+='<td>';
	bodyStr+='<table width="100%">';
	bodyStr+='<tr>';
	bodyStr+='<td rowspan="2" width="25%"><span><img height="40" width="35" src="/PartyAnalyst/images/icons/constituencyPage/human1.png"/></span></td>';
	bodyStr+='<td align="left"><span class="groupPersonNameSpanClass">Siva Kumar</span></td>';
	bodyStr+='</tr>';
	bodyStr+='<tr>';	
	bodyStr+='<td align="right"><span class="groupPersonMessageSpanClass">Send Mail</span></td>';
	bodyStr+='</tr>';
	bodyStr+='</table>';
	bodyStr+='</td>';
	bodyStr+='</tr>';
	
	bodyStr+='<tr>';
	bodyStr+='<td><div style="border:1px solid #F1F3F5;margin:0px 5px 0px 5px"></div></td>';
	bodyStr+='</tr>';

	bodyStr+='<tr>';
	bodyStr+='<td>';
	bodyStr+='<table width="100%">';
	bodyStr+='<tr>';
	bodyStr+='<td rowspan="2" width="25%"><span><img height="40" width="35" src="/PartyAnalyst/images/icons/constituencyPage/human1.png"/></span></td>';
	bodyStr+='<td align="left"><span class="groupPersonNameSpanClass">Raghavendra Prasad</span></td>';
	bodyStr+='</tr>';
	bodyStr+='<tr>';	
	bodyStr+='<td align="right"><span class="groupPersonMessageSpanClass">Send Mail</span></td>';
	bodyStr+='</tr>';
	bodyStr+='</table>';
	bodyStr+='</td>';
	bodyStr+='</tr>';

	bodyStr+='<tr>';
	bodyStr+='<td><div style="border:1px solid #F1F3F5;margin:0px 5px 0px 5px"></div></td>';
	bodyStr+='</tr>';

	bodyStr+='</table>';
	bodyStr+='</div>';

	bodyStr+='<div id="viewAllPersonConnectDiv">';
	bodyStr+='<table width = "100%"><tr>';
	bodyStr+='<td align="right">';
	bodyStr+='<span class="connectAncSpan"> <a href="javascript:{}" class="connectAnc">View All</a> </span>';
	bodyStr+='<span class="connectAncSpan"> | </span>';
	bodyStr+='<span class="connectAncSpan"> <a href="javascript:{}" class="connectAnc"> Connect </a> </span>';
	bodyStr+='</td>';
	bodyStr+='</tr></table>';
	bodyStr+='</div>';

	if(bodyElmt)
		bodyElmt.innerHTML=bodyStr;

	var connectButton = new YAHOO.widget.Button("connectButton");

}

function buildCenterConstituencyVotersInfoContent()
{
	var  votersElmt = document.getElementById('constituencyVotersInfoDiv_Body_voters');		

	var str = '';
	str+='<table class="constituencyInfoTable" width="60%">';
	str+='<tr>';
	str+='<th></th>';
	str+='<th>Voters</th>';
	str+='<th>Polled Votes</th>';
	str+='<th>Polling % </th>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Male</th>';
	str+='<td>'+constituencyPageMainObj.votingTrendzInfo.maleVoters+'</td>';
	str+='<td>'+constituencyPageMainObj.votingTrendzInfo.malePolledVotes+'</td>';
	str+='<td>'+constituencyPageMainObj.votingTrendzInfo.malePollingPercent+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Female</th>';
	str+='<td>'+constituencyPageMainObj.votingTrendzInfo.femaleVoters+'</td>';
	str+='<td>'+constituencyPageMainObj.votingTrendzInfo.femalePolledVotes+'</td>';
	str+='<td>'+constituencyPageMainObj.votingTrendzInfo.femalePollingPercent+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Male/Female</th>';
	str+='<td>'+constituencyPageMainObj.votingTrendzInfo.maleAndFemaleVoters+'</td>';
	str+='<td>'+constituencyPageMainObj.votingTrendzInfo.maleAndFemalePolledVotes+'</td>';
	str+='<td>'+constituencyPageMainObj.votingTrendzInfo.maleAndFemalePollingPercent+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Total</th>';
	str+='<td>'+constituencyPageMainObj.votingTrendzInfo.totalVoters+'</td>';
	str+='<td>'+constituencyPageMainObj.votingTrendzInfo.totalPolledVotes+'</td>';
	str+='<td>'+constituencyPageMainObj.votingTrendzInfo.pollingPercent+'</td>';
	str+='</tr>';

	str+='</table>';

	if(votersElmt)
		votersElmt.innerHTML=str;
	


	 var myDataSource = new YAHOO.util.DataSource(constituencyPageMainObj.votingTrendzInfo.votingTrendzTable); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	 myDataSource.responseSchema = { 
				fields: [
							{	key : "candidateName"},							
							{	key : "partyName"},																		
							{	key : "totalVotes",parser:"number"},							
							{	key : "maleVotes",parser:"number"},
							{	key : "femaleVotes",parser:"number"},
							{	key : "maleAndFemaleVotes",parser:"number"},
							{	key : "totalVotesPercent",parser:"number"},
							{	key : "maleVotesPercent",parser:"number"},
							{	key : "femaleVotesPercent",parser:"number"},
							{	key : "maleAndFemaleVotesPercent",parser:"number"},
							{	key : "status"}
						]
			}; 
	
	 var myColumnDefs = [ 
				{key:"candidateName",label:'Name', sortable:true}, 
				{key:"partyName", label:'Party', sortable:true}, 							
				{key:"totalVotes", label:'Total Votes', sortable:true},
				{key:"maleVotes", label:'Votes(M)', sortable:true},
				{key:"femaleVotes", label:'Votes(F)', sortable:true},
				{key:"maleAndFemaleVotes", label:'Votes(M/F )', sortable:true},
				{key:"totalVotesPercent", label:'Votes %', sortable:true},
				{key:"maleVotesPercent", label:'M %', sortable:true},
				{key:"femaleVotesPercent", label:'F %', sortable:true},
				{key:"maleAndFemaleVotesPercent", label:'M/F %', sortable:true},
				{key:"status", label:'status', sortable:true}
			]; 
		 

	var captionStr = '';
	captionStr += '<div width="100%">';
	captionStr += '<span id="dataTableTitle">Candidate Voting Trendz</span>';
	captionStr += '<span id="dataTableHead"> <Font color="Red">* </Font>M - Male , <Font color="Red">* </Font> F - Female </span>';
	captionStr += '</div>';
	var myDataTable = new YAHOO.widget.DataTable("constituencyVotersInfoDiv_Body_candidate",myColumnDefs, myDataSource,{caption:captionStr}); 

	
	buildVotingTrendzGraph();
	buildCandidateVotingTrendzGraph();
}

function buildVotingTrendzGraph()
{
	var votingTrendzelmt = document.getElementById('constituencyVotersInfoDiv_Body_votingTrendzGraph');
	
	
}

function buildCandidateVotingTrendzGraph()
{
	var candidateTrendzelmt = document.getElementById('constituencyVotersInfoDiv_Body_candidateTrendzGraph');
	
}

function initializeConstituencyPage()
{		
	buildRightlayoutMap();
	buildConstituencyInfo();
	buildConstituencyConnectPeopleWindow();
	buildProblemPostingWindow();
	buildProblemViewingWindow();
	buildElectionResults();
	buildCenterConstituencyVotersInfoContent();
	buildCenterVotersCandidateInfoContent();
	showCurrentlyElectedCandidate();
}