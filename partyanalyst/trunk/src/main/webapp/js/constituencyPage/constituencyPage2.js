var constituencyPageMainObj={
								constituencyAddress:'',
								contextPath:'',
								forwardTask:'',
								constituencyInfo:{
									                constituencyId:'',
													constituencyName:'',
													districtName:'',
													stateName:'',
													startDate:'',
													deformDate:'',
													constituencyType:'',
													reservation_zone:''
												 },
								constituencyElectionInfo:[],
								constituencyVotersInfo:[],
								constituencyVotersBasicInfo:[],
								presentAssemblyCandidate:[],
								munAssemblyConstituencyIds:[],
								corpAssemblyConstituencyIds:[],
								presentParliamentCandidate:[],
								problemsInfo:[],
								electionTrendzReportVO:{
															state:'',
															electionType:'',
															electionYear:'',
															constituencyId:'',
															constituencyName:'',
															otherElectionYears:[],
															electionTrendzOverviewVO:{
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
																						maleAndFemalePollingPercent:'',

																						maleVotersPercent:'',
																						femaleVotersPercent:'',
																						maleOrFemaleVotersPercent:'',	

																						overallMalePollPercent:'',
																						overallFemalePollPercent:'',
																						overallMaleOrFemalePollPercent:'',
																						
																						maleVotersInConstituency:'',
																						femaleVotersInConstituency:'',

																						maleVotersPercentInConsti:'',
																						femaleVotersPercentInConsti:'',

																						malePolledPercentInTotalPolled:'',
																						femalePolledPercentInTotalPolled:'',
																						maleOrFemalePolledPercentInTotalPolled:'',
																						
																						electionTrendzCharts:	{
																									pollingDetailsChart:'',
																									votingTrendzMainChart:'',
																									candOverallVotesPercent:'',
																									candVotingTrendz:''
																								},
																																			
																						wonCandidateResultTrendz:
																												{
																													candidateName:'',	
																													partyName:'',
																													totalVotes:'',
																													maleVotes:'',
																													femaleVotes:'',
																													maleAndFemaleVotes:'',
																													totalVotesPercent:'',
																													maleVotesPercent:'',
																													femaleVotesPercent:'',
																													maleAndFemaleVotesPercent:'',
																													overallMaleVotesPercent:'',
																													overallFemaleVotesPercent:'',
																													overallMaleOrFemaleVotesPercent:'',
																													maleVotesPercentInConstiVotes:'',
																													femaleVotesPercentInConstiVotes:'',
																													maleOrFemaleVotesPercentInConstiVotes:'',
																													status:''	
																												},
																						partyElectionTrendzVO:[]
													
																					
																					 },
															previousElectionYears:{
																					assemblyElections:[],
																					parliamentElections:[]
																				  }
														}
							};

var candidateIndex = 1,candidateListSize,prevButtonElmt,nextButtonElmt;
var constituencyConnectedPeople = new Array();
var connectStatus = new Array();
var userLoginStatus;
var userStatus = '${userStatus}';
var userId = '';
var parliamentConstiId = '';
var parliamentConstiName = '';

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
	var divElmtBody = document.getElementById("constituencyType");
    
	/*var heading='<h3>Constituency Info </h3>';
	if(divElmtHead)
		divElmtHead.innerHTML=heading;*/
	
	var str = '';
	
	str+=' '+constituencyPageMainObj.constituencyInfo.constituencyName+' ';
	str+='</tr>';
	if(constituencyPageMainObj.constituencyInfo.constituencyType.length > 0)
	{
		
		str+=' '+constituencyPageMainObj.constituencyInfo.constituencyType+' ';
		str+='</tr>';
	}
	str+=' Constituency Details';
	
	if(constituencyPageMainObj.constituencyInfo.deformDate.length > 0)
	{
		
		str+='( delimited in '+defDate.substring(0,4)+' )';
	}
	
	if(constituencyPageMainObj.constituencyInfo.reservation_zone.length > 0)
	{
		str+='('+constituencyPageMainObj.constituencyInfo.reservation_zone+')';
	}
	if(divElmtBody)
		divElmtBody.innerHTML=str;

}

function buildElectionResults()
{
	//var HeadElmt = document.getElementById('constituencyPageElectionInfoDiv_Head');
	var BodyElmt = document.getElementById('constituencyPageElectionInfoDiv_Body');
	
	if(HeadElmt)
		//HeadElmt.innerHTML = ' Election Information in '+constituencyPageMainObj.constituencyInfo.constituencyName;

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
	str+='<th>State</th>';
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
	
	var divElmt = document.getElementById("constituencyCenterContentOuter1");
	if(divElmt == null)
		return;
	if(constituencyPageMainObj.constituencyVotersInfo.length == 0)
	{
		divElmt.style.display='none';
			return;
	}
	
	var elmtHead = document.getElementById("mandalsVotersInfoDiv_Head");
	if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Assembly')
		elmtHead.innerHTML = 'Mandals Voters Details Of '+constituencyPageMainObj.constituencyInfo.constituencyName+' Assembly:';
	if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Parliament')
		elmtHead.innerHTML = 'Assemblies Voters Details Of '+constituencyPageMainObj.constituencyInfo.constituencyName+' Parliament:';
	var elmt = document.getElementById("mandalsVotersInfoDiv_Body");
	
	var dataAvailability = false;
	for(var i in constituencyPageMainObj.constituencyVotersInfo)
	{
		if(constituencyPageMainObj.constituencyVotersInfo[i].info.length!=0){
			dataAvailability = true;	
		}
	}
	
	if(!dataAvailability){
		document.getElementById("constituencyCenterContentOuter1").style.display='none';
	}else{	
			for(var i in constituencyPageMainObj.constituencyVotersInfo)
			{
				var divChild = document.createElement('div');
				var str = '';
				var data = constituencyPageMainObj.constituencyVotersInfo[i];
				if(constituencyPageMainObj.constituencyVotersBasicInfo.length!=0)
				{
				var basicData = constituencyPageMainObj.constituencyVotersBasicInfo[i];
				divChild.setAttribute("id","divChild"+i);
				str+='<div id="divChild_Head_'+i+'" class="voterInfoHead">';
				if(basicData.year == "2009"){
					if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Assembly')
						str+='Mandals After Delimitation';
					if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Parliament')
						str+='Assemblies After Delimitation';
				}
				else{
					if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Assembly')
						str+='Mandals Before Delimitation';
					if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Parliament')
						str+='Assemblies Before Delimitation';
				}
				}	
				str+='</div>';
				if(data.info.length!=0){
					str+='<div id="divInteractive_Chart_'+i+'"></div>';
					str+='<div id="divChild_Body_'+i+'" ></div>';
				}else{
					str+='<div id="divChild_Body_'+i+'" ></div>';
				}
				
				divChild.innerHTML=str;
		
				if(elmt)
					elmt.appendChild(divChild);
				
				var field,column;
				
				if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Parliament'){
					field = {key:"mandalName"};
					column = {key:"mandalName", label:'Assembly Name', sortable:true, resizeable:true};
				}
				
				if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Assembly'){
					field = {key:"mandalName"};
					column = {key:"mandalName", label:'Mandal Name', sortable:true, resizeable:true};
				}
				
				if(data.info.length!=0){
					 var myDataSource = new YAHOO.util.DataSource(data.info); 
					 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
					 myDataSource.responseSchema = { 
								fields: [
								         	field,
											{	key : "mandalMaleVoters",parser:"number"},
											{	key : "mandalFemaleVoters",parser:"number"},
											{	key : "mandalTotalVoters",parser:"number"},
											{	key : "isPartial"}
										]
							}; 
					
					 var myColumnDefs = [ 
					             column,
								{key:"mandalMaleVoters", label:'Male Voters', sortable:true, resizeable:true}, 
								{key:"mandalFemaleVoters", label:'Female Voters',sortable:true, resizeable:true}, 
								{key:"mandalTotalVoters",label:'Total Voters', sortable:true, resizeable:true},
								{key:"isPartial",label:'Is Partial', sortable:true, resizeable:true}
							]; 
					 
					var myDataTable = new YAHOO.widget.DataTable("divChild_Body_"+i+"",myColumnDefs, myDataSource); 
				}else{
					if(basicData != null)
					{
					 var myDataSource = new YAHOO.util.DataSource(basicData.info); 
					 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
					 myDataSource.responseSchema = { 
								fields: [
								         	field,									
											{	key : "isPartial"}
										]
							}; 
					
					 var myColumnDefs = [ 
					             column,						
								{key:"isPartial",label:'Is Partial', sortable:true, resizeable:true}
							]; 
					 
					var myDataTable = new YAHOO.widget.DataTable("divChild_Body_"+i+"",myColumnDefs, myDataSource); 
					}
				}
		}
	}
}

function showCandidateNominationsInRecentElections(myResults)
{
   var HeadElmt = document.getElementById('constituencyPageCandidateNominationsInfo_Head');
   var BodyElmt = document.getElementById('constituencyPageCandidateNominationsInfo_Body');

   if(myResults.candidateNominations != null && myResults.candidateNominations.length > 0)
   {
	   var headStr = '';
	   headStr+='Candidates Affidavit Summary for ' + myResults.electionType + ' '+myResults.electionYear;
	  

   
     var myDataSource = new YAHOO.util.DataSource(myResults.candidateNominations); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	 myDataSource.responseSchema = { 
				fields: [
							{	key : "candidateName"},
							{	key : "party"},
							{	key : "gender"},
					        {	key : "education"},
					        {	key : "assets"},
					        {	key : "liabilities"},
							
						]
			}; 
	
	 var myColumnDefs = [ 
				{key:"candidateName",label:'Candidate Name', sortable:true, resizeable:true}, 
				{key:"party", label:'Party',sortable:true, resizeable:true},
				{key:"gender", label:'Gender', sortable:true, resizeable:true}, 
		        {key:"education", label:'Education', sortable:true, resizeable:true}, 
		        {key:"assets", label:'Assets', sortable:true, resizeable:true}, 
		        {key:"liabilities", label:'Liabilities', sortable:true, resizeable:true}
				
			];
	
	var myDataTable = new YAHOO.widget.DataTable("constituencyPageCandidateNominationsInfo_Top",myColumnDefs, myDataSource,{caption:headStr});

   }

}
function buildAssetsAndLiabilities(myResults)
{


   var HeadElmt = document.getElementById('constituencyPageCandidateAssets_Head');
   
   YAHOO.widget.DataTable.problemLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var candidateName = oData;
		var candidateId = oRecord.getData("candidateId");
		//elLiner.innerHTML ="<a href='candidateElectionResultsAction.action?candidateId="+candidateId+"'>"+candidateName+"</a>";		
		elLiner.innerHTML ='<a id='+candidateName+' href="candidateElectionResultsAction.action?candidateId='+candidateId+'" onmouseover="displayImage(\''+candidateName+'\');" onmouseout="return nd();">'+candidateName+'</a>';		
	};

   if(myResults.candidateNominations != null && myResults.candidateNominations.length > 0)
   {
	  var headStr = '';
	  headStr+='<span class="layoutHeadersClass">Candidates Affidavit Summary for ' + myResults.electionType + ' '+myResults.electionYear+' </span>';
		 
	 var myDataSource = new YAHOO.util.DataSource(myResults.candidateNominations); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	 myDataSource.responseSchema = { 
				fields: [
							{	key : "candidateName"},
							{	key : "candidateId"},                            
							{	key : "party"},
							{	key : "gender"},   
					        {	key : "education"},
					        {	key : "assets"},
					        {	key : "liabilities"},
							
						]
			}; 
	
	 var myColumnDefs = [ 
				{key:"candidateName",label:'Candidate Name', sortable:true, resizeable:true , formatter:YAHOO.widget.DataTable.problemLink}, 
				{key:"party", label:'Party',sortable:true, resizeable:true},
				{key:"gender", label:'Gender', sortable:true, resizeable:true}, 
		        {key:"education", label:'Education', sortable:true, resizeable:true}, 
		        {key:"assets", label:'Assets', sortable:true, resizeable:true}, 
		        {key:"liabilities", label:'Liabilities', sortable:true, resizeable:true}
				
			];
	
	var myDataTable = new YAHOO.widget.DataTable("electionYearsWithAssets_Panel_Div",myColumnDefs, myDataSource,{caption:headStr});

   }

}

function showCurrentlyElectedCandidate()
{	

/*
    var HeadElmt = document.getElementById('constituencyPageCandidateInfo_Head');
	var BodyElmt = document.getElementById('constituencyPageCandidateInfo_Body');

	var headStr = '';
	headStr+='Elected Candidate Info';
	if(HeadElmt)
		HeadElmt.innerHTML=headStr;

 Building Assembly candidate Info datatable 
	if(constituencyPageMainObj.presentAssemblyCandidate == null && constituencyPageMainObj.presentParliamentCandidate == null)
	{
		BodyElmt.innerHTML = 'This constituency has been delimitated ';
		return;
	}

	if(constituencyPageMainObj.presentAssemblyCandidate.length == 0 && constituencyPageMainObj.presentParliamentCandidate.length == 0)
	{		
		BodyElmt.innerHTML = 'This constituency has been delimitated ';
		return;
	}*/

	 if(constituencyPageMainObj.presentAssemblyCandidate != null && constituencyPageMainObj.presentAssemblyCandidate.length != 0)
	{
		var title = "MLA's Of Assembly Constituencies In "+constituencyPageMainObj.constituencyInfo.constituencyName+" Parliament"; 
	 var myDataSource = new YAHOO.util.DataSource(constituencyPageMainObj.presentAssemblyCandidate); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	 myDataSource.responseSchema = { 
				fields: [
							{	key : "candidateName"},
							{	key : "constituencyName"},
							{	key : "party"},
							{	key : "partyFlag"},
							{	key : "knowMore"}
						]
			}; 
	
	 var myColumnDefs = [ 
				{key:"candidateName",label:'Candidate Name', sortable:true, resizeable:true}, 
				{key:"constituencyName", label:'Constituency Name', sortable:true, resizeable:true}, 
				{key:"party", label:'Party',sortable:true, resizeable:true},
				{key:"partyFlag", label:'Party Flag',sortable:true, resizeable:true},
				{key : "knowMore", label:'Complete Results', resizeable:true}
			]; 
		 
	if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Assembly')
		var myDataTable = new YAHOO.widget.DataTable("constituencyPageCandidateInfo_Top",myColumnDefs, myDataSource,{caption:"<font style='color:#C20000;font-weight:bold;font-size:12px;font-style: normal;'>"+title+"</font>"});
	else
		var myDataTable = new YAHOO.widget.DataTable("constituencyPageCandidateInfo_Bottom",myColumnDefs, myDataSource,{caption:"<font style='color:#C20000;font-weight:bold;font-size:12px;font-style: normal;'>"+title+"</font>"});
    
	}
/*
     if(constituencyPageMainObj.presentParliamentCandidate != null && constituencyPageMainObj.presentParliamentCandidate.length != 0)
	{
 Building Parliament candidate Info datatable 

	 var myDataSource = new YAHOO.util.DataSource(constituencyPageMainObj.presentParliamentCandidate); 
	 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	 myDataSource.responseSchema = { 
				fields: [
							{	key : "candidateName"},
							{	key : "constituencyName"},
							{	key : "party"},
							{	key : "partyFlag"},
							{	key : "knowMore"}
						]
			}; 
	
	 var myColumnDefs = [ 
				{key:"candidateName",label:'Candidate Name', sortable:true, resizeable:true}, 
				{key:"constituencyName", label:'Constituency Name', sortable:true, resizeable:true}, 
				{key:"party", label:'Party',sortable:true, resizeable:true},
				{key:"partyFlag", label:'Party Flag',sortable:true, resizeable:true},
				{key : "knowMore", label:'Complete Results', resizeable:true}
			]; 
	
	if(constituencyPageMainObj.constituencyInfo.constituencyType == 'Assembly')
		var myDataTable = new YAHOO.widget.DataTable("constituencyPageCandidateInfo_Bottom",myColumnDefs, myDataSource,{caption:"Parliament Candidate : "});
	else
		var myDataTable = new YAHOO.widget.DataTable("constituencyPageCandidateInfo_Top",myColumnDefs, myDataSource,{caption:"Parliament Candidate : "});

	}*/
}

function buildProblemViewingWindow()
{

	var headElmt = document.getElementById('problemViewingDiv_Head');
	var bodyElmt = document.getElementById('problemViewingDiv_Body');
	
	var str='';
	//str+='<fieldset id="problemViewingFieldSet">';
	//str+='<legend> View Your constituency Problems</legend>';
	str+='<div id="problemViewingContentDiv" class="problemPostingContentDivClass">';	
	str+='<marquee direction="up" scrolldelay="200" onmouseover="this.stop();" onmouseout="this.start();" style="height:80px;" >';

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
		onclick="getProblemCompleteDetails('+data.problemHistoryId+')"
			str+='<div class="problemDataDivClass">';
			str+='<table>';
			str+='	<tr>';
			str+='		<td width="80%">';
			str+='			<span><img height="10" width="10" src="/PartyAnalyst/images/icons/constituencyPage/bullet_blue.png"></img></span>';
			str+='			<span>';
			str+='				<a class="districtAnc" href="completeProblemDetailsAction.action?problemId='+data.problemHistoryId+'">'+data.problem+'</a></span>';
			str+='		</td>';
			str+='		<td width="10%"><img width="20" height="20" title="Accepted Votes" src="images/icons/accept.png"/></td><td style="font-weight:bold;font-size:13px">'+data.acceptedCount+'</td>';	
			str+='      <td><img width="20" height="20" src="images/icons/reject.png" title="Rejected Votes"/></td><td style="font-weight:bold;font-size:13px">'+data.rejectedCount+'</td>';		
			str+='	</tr>';
			str+='</table>';			
			str+='</div>';
			str+='<div id="constituencyMgmtBodyDiv" class="yui-skin-sam"><div id="moreDetailsPanelDiv"></div></div>';
		
			
		}
	}
	
	str+='</marquee>';
	str+='</div>';
	//str+='</fieldset>';
	
	if(bodyElmt)
		bodyElmt.innerHTML=str;
}
function  showProblemDetails(selectedProblemId)
{
	for(var i in constituencyPageMainObj.problemsInfo)
		{
			var data = constituencyPageMainObj.problemsInfo[i];
			if(data.problemId==selectedProblemId){
						var elmt = document.getElementById('constituencyMgmtBodyDiv');
						var divChild = document.createElement('div');
						divChild.setAttribute('id','createDiv');
						var problemName = data.problem;
						data.problem.name = problemName[0].toUpperCase();
						elmt.appendChild(divChild);	

						var showProblemData='';		
						showProblemData+='<div align="center"><h3>Complete Report of <span style="color:green">'+data.problem+'</span> </h3></div>';
						showProblemData+='<fieldset>';  		
						showProblemData+='<legend style="font-family:arial,helvetica,clean,sans-serif;">Details of the Problem</legend>';
						showProblemData+='		<table id="probDetailsTable">';						
						showProblemData+='			<tr>';
						showProblemData+='				<td><b> Problem :</b></td>';
						showProblemData+='				<td>'+data.problem+'</td>';
						showProblemData+='			</tr>';
						showProblemData+='			<tr>';
						showProblemData+='				<td><b> Description :</b></td>';
						showProblemData+='				<td>'+data.description+'</td>';
						showProblemData+='			</tr>';
						showProblemData+='			<tr>';
						showProblemData+='				<td><b> Complained Person Name :</b></td>';
						showProblemData+='				<td>'+data.name+'</td>';
						showProblemData+='			</tr>';
						showProblemData+='			<tr>';
						showProblemData+='				<td><b> PostedDate  :</b></td>';
						showProblemData+='				<td>'+data.postedDate+'</td>';
						showProblemData+='			</tr>';
						showProblemData+='		</table>';		
						showProblemData+='</fieldset>';

				
						
						showProblemData+='<div id="showProblems" class="yui-skin-sam" align="center"></div>';

						if(createGroupDialog)
							createGroupDialog.destroy();
						createGroupDialog = new YAHOO.widget.Dialog("createDiv",
								{ width : "600px", 		
								  fixedcenter : false, 
								  visible : true,  
								  constraintoviewport : true, 
								  iframe :false,
								  modal :false,
								  hideaftersubmit:true,
								  close:true,
								  x:400,
								  y:300,				  
								  buttons : [ { text:"Ok", handler: handleSubmit, isDefault:true}, 
											  { text:"Cancel", handler: handleCancel}]
								 } );


					
						
						createGroupDialog.setBody(showProblemData);
						
						createGroupDialog.render();
			}
		}
}
function handleSubmit()
{
	createGroupDialog.hide();			
}

function handleCancel()
{
	this.cancel();
}

function openAddNewProblemWindowForConstituency()
{	
	var browser1 = window.open("addNewProblemAction.action?requestSrc=4&constituencyId="+constituencyPageMainObj.constituencyInfo.constituencyId,"addNewProblemInConstituency","scrollbars=yes,height=600,width=600,left=200,top=200");				 
	browser1.focus();
}

function buildProblemPostingWindow()
{
	
	var headElmt = document.getElementById('problemPostingDiv_Head');
	var bodyElmt = document.getElementById('problemPostingDiv_Body');
		
	var str='';
	//str+='<fieldset id="ProblemPostingFieldSet">';
	//str+='<legend style="font-weight:bold;"> Post Your constituency Problem</legend>';
	str+='<div id="ProblemPostingContentDiv" class="problemPostingContentDivClass">';	
	//str+='<div>Post your constituency problem and bring it to the all people notice.</div>';
	
	if(userType == "PARTY_ANALYST_USER" || userType == "FREE_USER")
	{		
		str+='<div id="problemPostingButtonDiv"><a class="post-problem-but" type="button" id="postButton" value = "Post Problem" onclick="openAddNewProblemWindowForConstituency()">Post Problem</a></div>';
	}
	else
	{    
		str+='<div id="problemPostingButtonDiv"><a class="post-problem-but" href="problemPostControlAction.action?redirectLoc=CONSTITUENCY&constituencyId='+constituencyPageMainObj.constituencyInfo.constituencyId+'">POST</a></div>';
	}
	str+='</div>';
	//str+='</fieldset>';
	
	if(bodyElmt)
		bodyElmt.innerHTML=str;

	/*var postButton = new YAHOO.widget.Button("postButton");
	postButton.on("click", openAddNewProblemWindow); */
}

function buildAnalyzeConstituencyWindow()
{
	var headElmt = document.getElementById('analyzeConstituencyPageDiv_Head');
	var bodyElmt = document.getElementById('analyzeConstituencyPageDiv_Body');
		
	var str='';
	//str+='<fieldset id="analyzeConstituencyFieldSet">';
	//str+='<legend style="font-weight:bold;"> Assess Your Party Results</legend>';
	str+='<div id="analyzeConstituencyContentDiv" class="problemPostingContentDivClass">';	
	//str+='<div>Assess your constituency election results and post your reasons for winning/loosing .</div>';
	//str+='<div id="problemPostingButtonDiv"><input type="button" id="postButton" value = "Post" onclick="openAddNewProblemWindow()"/></div>';
	//str+='<div id="analyzeConstituencyButtonDiv"><a href="analyzeConstituencyPopupAction.action?redirectLoc=CONSTITUENCY&constituencyId='+constituencyPageMainObj.constituencyInfo.constituencyId+'">Analyze</a></div>';

	if(loginStat == 'out')
	{
		if(userType == "PARTY_ANALYST_USER" || userType == "FREE_USER")
		{
			str	+= '<div id="analyzeConstituencyButtonDiv" >';
			str += '<a href="javascript:{}" class="buttnStyle" title="Click to View Previous Posts" style="margin-right:10px;" onclick="openAnalyzeConstituencyWindow(\'viewResults\')" >Previous Posts</a>';
			str += '<a href="javascript:{}" class="buttnStyle" title="Click to Assess Results" style="margin-right:10px;" onclick="openAnalyzeConstituencyWindow(\'analyze\')" >Assess</a>';
			
			str += '</div>';		
		}
	}
	else {
      str += '<div id="analyzeConstituencyButtonDiv" style="text-align:right;padding:5px;" >';
	  	str += '<a href="javascript:{}" class="buttnStyle" title="Click to View Previous Posts" style="margin-right:10px;" onclick="openAnalyzeConstituencyWindow(\'viewResults\')" >Previous Posts</a>';
		str += '<a href="loginInputAction.action?redirectLoc=CONSTITUENCY&constituencyId='+constituencyPageMainObj.constituencyInfo.constituencyId+'&parliamentConstiId='+parliamentConstiId+'&parliamentConstiName='+parliamentConstiName+'&constituencyName='+constituencyPageMainObj.constituencyInfo.constituencyName+'&taskType=analyze" title="Click to Assess Results" style="margin-right:10px;" class="buttnStyle">Assess</a>';
		//str += '<a href="javascript:{}" class="buttnStyle" title="Click to Assess Results" style="margin-right:10px;" onclick="openAnalyzeConstituencyWindow(\'analyze\')" >Assess</a>';
		str += '</div>';			
	}
		
	
	str+='</div>';
	//str+='</fieldset>';
	
	if(bodyElmt)
		bodyElmt.innerHTML=str;
}


function openAnalyzeConstituencyWindow(type)
{	
	var taskType = type;
	var constituencyId= constituencyPageMainObj.constituencyInfo.constituencyId;
	var constituencyName = constituencyPageMainObj.constituencyInfo.constituencyName;
	if(parliamentConstiId =='')
		parliamentConstiId = constituencyId;
	if(parliamentConstiName =='')
		parliamentConstiName = constituencyName;

	if((userId != "" && (userStatusType == "FREE_USER" ||userStatusType == "BOTH" )) || type == "viewResults")
	{
	   if(consticType == 'Assembly')
		 var browser1 = window.open("analyzeConstituencyPopupAction.action?redirectLoc=ANALYZECONSTITUENCYPOPUP&constituencyId="+constituencyId+"&parliamentConstiId="+parliamentConstiId+"&parliamentConstiName="+parliamentConstiName+"&constituencyName="+constituencyName+"&userId="+userId+"&taskType="+taskType,"analyzeConstituencyPopup","scrollbars=yes,height=800,width=700,left=200,top=200");	
       else
       	 var browser1 = window.open("analyzeConstituencyPopupAction.action?redirectLoc=ANALYZECONSTITUENCYPOPUP&constituencyId="+constituencyId+"&parliamentConstiId="+parliamentConstiId+"&parliamentConstiName="+parliamentConstiName+"&constituencyName="+constituencyName+"&userId="+userId+"&parlchecked=true&taskType="+taskType,"analyzeConstituencyPopup","scrollbars=yes,height=800,width=700,left=200,top=200");	 
		browser1.focus();
	}
	else if(userId == "" && userStatusType == "FREE_USER")
	{
		alert("Please Login To Post Comment");
	}
	else if(userStatusType != "FREE_USER" || userStatusType != "BOTH")
	{
		alert("Comment For Free User Only");
	}
}

function buildConstituencyConnectPeopleWindow()
{
	var headElmt = document.getElementById('constituencyPeopleConnectDiv_Head');
	var bodyElmt = document.getElementById('constituencyPeopleConnectDiv_Body');
	var constiId = constituencyPageMainObj.constituencyInfo.constituencyId;
	var constituencyName = constituencyPageMainObj.constituencyInfo.constituencyName;
	var headStr = 'Connect To Your Constituency People';
	if(headElmt)
		headElmt.innerHTML=headStr;
	if(constituencyConnectedPeople.length == 0 && userLoginStatus == "false")
	{
		var errorStr = '';
		errorStr += '<div class="view-all" style="font-family: Helvetica;" > No people have been connected.</div>';
		errorStr += '<div class="view-all" style="margin-top: 6px;font-family: Helvetica;" >Register to connect to your area.</div>';
		errorStr += '<div class="view-all" style="margin-top: 6px;font-family: Helvetica;" >Connect functionality provides the user to connect to his/her area and share information, group certain people, sending messages etc..,</div>';
		errorStr += '<div class="view-all" style="margin-top: 6px;font-family: Helvetica;" >To connect to your district people <a href="freeUserRegistration.action" style="margin-right: 25px;"><b>Register</b></a></div><br/>';
		errorStr += '<div class="view-all" style="margin-top: 6px;font-family: Helvetica;" >Already Have an account? <a style="margin-right: 40px;" href="connectPeopleAction.action?redirectLoc=CONSTITUENCY&constituencyId='+constiId+'&constituencyName='+constituencyName+'"><b>Login</b></a></div>';
		
		bodyElmt.innerHTML = errorStr;
		return;
	}
	else if(constituencyConnectedPeople.length == 0 && userLoginStatus == "true")
	{
		var errorStr = '';
		errorStr += '<div class="errorStr" style="font-family: Helvetica;" > No people have been connected.</div>';
		errorStr += '<div class="errorStr" style="margin-top: 6px;font-family: Helvetica;" >Register to connect to your area.</div>';
		errorStr += '<div class="errorStr" style="margin-top: 6px;font-family: Helvetica;" >Connect functionality provides the user to connect to his/her area and share information, group certain people, sending messages etc..,</div>';
		errorStr += '<div class="view-all" style="margin-top: 6px;font-family: Helvetica;" >To connect to your district people <a href="freeUserRegistration.action"><b>Register</b></a></div>';		
		bodyElmt.innerHTML = errorStr;
		return;
	}
	
	buildConnectUsersContent(constituencyConnectedPeople,"constituencyPeopleConnectDiv_Body","CONSTITUENCY",constiId,constituencyName,userLoginStatus,userId);
	
}


function buildVotingTrendzLayout(divId,obj)
{	
	var elmt = document.getElementById(divId);

	var str = '';	
	str+='	<div id="constituencyVotersInfoDiv_Body">';
	str+='	<table>';
	str+='		<tr>';
	str+='			<td><div id="constituencyVotersInfoDiv_Head" class="layoutHeadersClass"></div></td>';
	str+='			<td><div id="constituencyVotersInfoDiv_Navigation"></div></td>';
	str+='		</tr>';
	str+='		<tr>';
	str+='			<td colspan="2">';
	str+='				<div id="constituencyVotersInfoDiv_Body_votingTrendzGraph" class="layoutBodyClass yui-skin-sam"></div>';
	str+='			</td>';
	str+='		</tr>';
	str+='		<tr>';
	str+='			<td width="50%"><div id="constituencyVotersInfoDiv_Body_voters" class="layoutBodyClass yui-skin-sam"></div></td>';
	str+='          <td width="50%"><div class="commentsDiv"> ';
	str+='				* Total Male Voters In Constituency   : <font style="color:#FF0000;">'+obj.electionTrendzOverviewVO.maleVotersInConstituency+' 							</font> </div>';
	str+='              <div class="commentsDiv"> * Total Female Voters In Constituency : <font 																				style="color:#FF0000;">'+obj.electionTrendzOverviewVO.femaleVotersInConstituency+' </font> </div>';
    str+='              <div class="commentsDiv"> ';
	str+='				 * Known Classified Male Voters % In Total Male Voters :<font style="color:#FF0000;"> '+obj.electionTrendzOverviewVO.maleVoters+' </font><font style="color:#0000FF;"> ( '+obj.electionTrendzOverviewVO.maleVotersPercentInConsti+' % )</font> </div>';
	str+='              <div class="commentsDiv"> ';
	str+='				 * Known Classified Female Voters % In Total Female Voters   :<font     			   style="color:#FF0000;">'+obj.electionTrendzOverviewVO.femaleVoters+'</font><font style="color:#0000FF;"> ( '+obj.electionTrendzOverviewVO.femaleVotersPercentInConsti+' % )</font> </div>';
	str+='           </td>';
	str+='		</tr>';
	str+='		<tr>';
	str+='			<td colspan="2">';
	str+='				<div id="constituencyVotersInfoDiv_Body_candidateTrendzGraph" class="layoutBodyClass yui-skin-sam">';
	str+='					<div id="candidateTrendzGraph_head"></div>';
	str+='					<div id="candidateTrendzGraph_body">';
	str+='					<table>';
	str+='						<tr>';
	str+='							<td style="vertical-align:top;width:30%;"> <div id="candidateVotingGraph1" class="graphHeadingDivClass"></div></td>';
	str+='							<td style="vertical-align:center"> <div id="candidateTrendzGraphDataDiv" class="graphHeadingDivClass"></div> </td>';
	str+='							<td style="vertical-align:top;width:30%;"> <div id="candidateVotingGraph2" class="graphHeadingNewDivClass"></div></td>';
	str+='						</tr>';
	str+='					</table>';
	str+='				</div>';
	str+='				<div id="candidateTrendzGraph_footer"></div>';
	str+='				</div>';
	str+='			</td>';
	str+='		</tr>';
	str+='		<tr>';
	str+='			<td colspan="2">';
	str+='				<div id="constituencyVotersInfoDiv_Body_candidate" class="layoutBodyClass yui-skin-sam"></div>';
	str+='			</td>';
	str+='		</tr>';
	str+='	</table>';
	str+='	</div>';
	str+='<div id="constituencyVotersInfoDiv_Footer"></div>';

	if(elmt)
		elmt.innerHTML = str;
	
	buildVotingTrendzData(obj);
}

function buildVotingTrendzData(obj)
{	

	constituencyPageMainObj.electionTrendzReportVO.electionTrendzOverviewVO = obj.electionTrendzOverviewVO;

	buildConstituencyVotingTrendzHeader(obj);
	buildCenterConstituencyVotersInfoContent(obj.electionTrendzOverviewVO);	
	buildConstituencyVotingTrendzGraph(obj.electionTrendzOverviewVO);	
	buildCandidateVotingTrendzGraphData(obj.electionTrendzOverviewVO,"");
	candidateVotingTrendzDatatable(obj.electionTrendzOverviewVO);
}

function buildCandidateVotingTrendzGraphData(obj,results)
{	

	var candidateTrendzGraphelmt_head = document.getElementById('candidateTrendzGraph_head');
	var candidateTrendzGraphelmt_footer = document.getElementById('candidateTrendzGraph_footer');
	var candidateTrendzGraphelmt = document.getElementById('candidateTrendzGraphDataDiv');

	var data;
	var localObj = constituencyPageMainObj.electionTrendzReportVO.electionTrendzOverviewVO;
	
	
	if(candidateTrendzGraphelmt_head && candidateTrendzGraphelmt_footer && candidateTrendzGraphelmt)
	{
		candidateTrendzGraphelmt_head.innerHTML = '';
		candidateTrendzGraphelmt_footer.innerHTML = '';
		candidateTrendzGraphelmt.innerHTML = '';
	}
	
	if(results)
		data = obj;
	else
		data = obj.wonCandidateResultTrendz;

	candidateListSize = localObj.partyElectionTrendzVO.length;

	var hStr = '';
	hStr += '<font style="color:Tomato">'+data.candidateName+'</font> Voting Trendz';
	hStr += ' ... ';
	hStr += ' Total Votes Earned : <font style="color:Tomato">' +data.totalVotes + '</font>';
	hStr += ' ... ';
	hStr += ' Total Votes % : <font style="color:Tomato">' +data.totalVotesPercent+' %</font>';
	hStr += ' ... ';
	hStr += ' Result Status : <font style="color:Tomato">' +data.status + '</font>';
	
	if(candidateTrendzGraphelmt_head)
		candidateTrendzGraphelmt_head.innerHTML=hStr;

	var fStr = '';
	fStr += '<table width="100%">';
	fStr += '<tr>';
	fStr += '<td align="left"><input type="button"  id="prevButton" value="Previous" onclick="showNextPreviousCandidateVotingTrendz(candidateIndex,\'previous\')"></td>';
	fStr += '<td></td>';
	fStr += '<td align="right"><input type="button" id="nextButton" value="Next" onclick="showNextPreviousCandidateVotingTrendz(candidateIndex,\'next\')"></td>';
	fStr += '</tr>';
	fStr += '</table>';


	if(candidateTrendzGraphelmt_footer)
		candidateTrendzGraphelmt_footer.innerHTML=fStr;

	prevButtonElmt = document.getElementById("prevButton");
	nextButtonElmt = document.getElementById("nextButton");

	if(prevButtonElmt){
		if(candidateIndex!=1)
		  prevButtonElmt.disabled = false;
		else
		  prevButtonElmt.disabled = true;
	}

	if(nextButtonElmt){
	if(candidateListSize>1 && candidateIndex!=candidateListSize)
		nextButtonElmt.disabled = false;
	else
		nextButtonElmt.disabled = true;
	}
	

	var str = '';
	str+='<div class="CandidateResultsInfoHeading_head">MALE</div>';
	str+='<div class="CandidateResultsInfoHeading_body">';
	str+='<div>M Votes Earned                      : <font style="color:#CA6666">'+data.maleVotes+' ('+data.maleVotesPercent+ ' %)</font> </div>';
	str+='<div>M Votes % In Candidate Gained Votes : <font style="color:DarkGoldenRod">'+data.overallMaleVotesPercent+'%</font> </div>';
    str+='<div>Total M Votes %  In Constituency    : <font style="color:MediumPurple">'+data.maleVotesPercentInConstiVotes+ ' % </font></div>';

	str+='<div class="CandidateResultsInfoHeading_head">FEMALE</div>';
	str+=' <div class="CandidateResultsInfoHeading_body">';
	str+=' <div>F Votes Earned                     : <font style="color:#CA6666">'+data.femaleVotes+' ('+data.femaleVotesPercent+ ' %)</font> </div>';
	str+='<div>F Votes % In Candidate Gained Votes : <font style="color:DarkGoldenRod">'+data.overallFemaleVotesPercent+'%</font> </div>';
	str+='<div>Total F Votes %  In Constituency    : <font style="color:MediumPurple">'+data.femaleVotesPercentInConstiVotes+ ' % </font></div>';

	str+='<div class="CandidateResultsInfoHeading_head">MALE/FEMALE</div>';
	str+=' <div class="CandidateResultsInfoHeading_body">';
	str+=' <div>M/F Votes Earned                     : <font style="color:#CA6666">'+data.maleAndFemaleVotes+' ('+data.maleAndFemaleVotesPercent+ ' %) </font></div>';
	str+='<div>M/F Votes % In Candidate Gained Votes : <font style="color:DarkGoldenRod">'+data.overallMaleOrFemaleVotesPercent+'%</font> </div>';
	str+='<div>Total M/F Votes %  In Constituency    : <font style="color:MediumPurple">'+data.maleOrFemaleVotesPercentInConstiVotes+ ' % </font></div>';


	if(candidateTrendzGraphelmt)
		candidateTrendzGraphelmt.innerHTML = str;
	
	if(results)
	{		
		var imgChart1 = document.getElementById("candVotingTrendz");
		var imgChart2 = document.getElementById("candOverallVotesPercent");
		
		imgChart1.src = 'charts/'+results.candOverallVotesPercent;
		imgChart2.src = 'charts/'+results.candVotingTrendz;

		
	}
	else
	{		
		var graph1Elmt = document.getElementById("candidateVotingGraph1");
		var graph2Elmt = document.getElementById("candidateVotingGraph2");

		if(graph2Elmt !=null)
		  graph2Elmt.innerHTML='<div >% Votes Gained By Candidate In Total Constituency ...</div><IMG id="candVotingTrendz" SRC="charts/'+obj.electionTrendzCharts.candOverallVotesPercent+'"/>';
		if(graph1Elmt !=null)
		graph1Elmt.innerHTML='<div > Male,Female,M/F Votes % In Candidate Gained Votes ... </div><IMG id="candOverallVotesPercent" SRC="charts/'+obj.electionTrendzCharts.candVotingTrendz+'"/>';
	}
}

function buildConstituencyVotingTrendzHeader(obj)
{
	var headElmt = document.getElementById("constituencyVotersInfoDiv_Head");
	
	var str = '';
	str += '<div id="constituencyTrendzHead">'+obj.constituencyName +' Voting Trendz For The Year '+obj.electionYear+'</div>';

	if(headElmt)
		headElmt.innerHTML = str;
}	

function buildCenterConstituencyVotersInfoContent(obj)
{		
	var  votersElmt = document.getElementById('constituencyVotersInfoDiv_Body_voters');		
	var  votersGraphElmt = document.getElementById('constituencyVotersInfoDiv_Body_votersGraph');		

	var str = '';
	str+='<table class="constituencyInfoTable" width="100%">';
	
	str+='<tr>';
	str+='<th colspan="5"> Constituency Voting Info</th>';
	str+='</tr>';
	
	str+='<tr>';
	str+='<th></th>';
	str+='<th>Voters</th>';
	str+='<th>Polled Votes</th>';
	str+='<th>Polling % </th>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Male</th>';
	str+='<td>'+obj.maleVoters+'</td>';
	str+='<td>'+obj.malePolledVotes+'<font style="color:Tomato"> ( '+obj.malePolledPercentInTotalPolled+' % )</font></td>';
	str+='<td>'+obj.malePollingPercent+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Female</th>';
	str+='<td>'+obj.femaleVoters+'</td>';
	str+='<td>'+obj.femalePolledVotes+'<font style="color:Tomato"> ( '+obj.femalePolledPercentInTotalPolled+' % )</font></td>';
	str+='<td>'+obj.femalePollingPercent+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Male/Female</th>';
	str+='<td>'+obj.maleAndFemaleVoters+'</td>';
	str+='<td>'+obj.maleAndFemalePolledVotes+'<font style="color:Tomato"> ( '+obj.maleOrFemalePolledPercentInTotalPolled+' % )</font></td>';
	str+='<td>'+obj.maleAndFemalePollingPercent+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Total</th>';
	str+='<td>'+obj.totalVoters+'</td>';
	str+='<td>'+obj.totalPolledVotes+'<font style="color:Tomato"> ( 100 % )</font></td>';
	str+='<td>'+obj.pollingPercent+'</td>';
	str+='</tr>';

	str+='</table>';

	if(votersElmt)
		votersElmt.innerHTML=str;


	/*var gStr = '';
	gStr+='<IMG id="pollingDetailsChartImg" SRC="charts/'+obj.electionTrendzCharts.pollingDetailsChart+'"/>';
	
	if(votersGraphElmt)
		votersGraphElmt.innerHTML = gStr;*/
}	

function buildConstituencyVotingTrendzGraph(obj)
{	
	var elmt = document.getElementById('constituencyVotersInfoDiv_Body_votingTrendzGraph');
	
	if(elmt)
		elmt.innerHTML = '<IMG id="votingTrendzChartImg" SRC="charts/'+obj.electionTrendzCharts.votingTrendzMainChart+'"/>';

	/*var imgElmt = document.getElementById("votingTrendzChartImg");
	imgElmt.src = 'charts/'+obj.electionTrendzCharts.votingTrendzMainChart;	*/
}


function candidateVotingTrendzDatatable(obj)
{
	
	var candidateTrendzArr = obj.partyElectionTrendzVO;
	 var myDataSource = new YAHOO.util.DataSource(candidateTrendzArr); 
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

}


function buildelectionYearsForVotingTrendz(obj)
{
	var elmt = document.getElementById('constituencyVotersInfoDiv_Navigation');

	var str = '';
	str+='<span><b>View Voting Trendz : </b></span>';
	str+='<span>';
	str+='<input type="radio" name="electionType" value="assembly" onclick="enableElectionYearSelect(this.value)">';
	str+='<select id="assembly_YearSelect" disabled="disabled">';
	str+='<option value="0">Assembly</option>';
	for(var i in obj.assemblyElections)
		str+='<option value="'+obj.assemblyElections[i].electionId+'_'+obj.assemblyElections[i].electionTypeId+'">'+obj.assemblyElections[i].electionYear+'</option>';
	str+='</select>';
	str+='</input>';
	str+='</span>';

	str+='<span>';
	str+='<input type="radio" name="electionType" value="parliament" onclick="enableElectionYearSelect(this.value)"/>';
	str+='<select id="parliament_YearSelect" disabled="disabled">';
	str+='<option value="0">Parliament</option>';
	for(var i in obj.parliamentElections)
		str+='<option value="'+obj.parliamentElections[i].electionId+'_'+obj.parliamentElections[i].electionTypeId+'">'+obj.parliamentElections[i].electionYear+'</option>';
	str+='</select>';
	str+='</span>';

	str+='<span>';
	str+='<input type="button" value="View" style="font-weight:bold;" onclick="getVotingTrendzForyear()">';
	str+='</span>';
	if(elmt)
		elmt.innerHTML = str;
				  
}

function enableElectionYearSelect(value)
{
	var asmbSelectElmt = document.getElementById("assembly_YearSelect");
	var parSelectElmt = document.getElementById("parliament_YearSelect");

	if(value=="assembly")
	{
		asmbSelectElmt.disabled = false;
		parSelectElmt.disabled = true;
	}
	else if(value=="parliament")
	{
		asmbSelectElmt.disabled = true;
		parSelectElmt.disabled = false;
	}
}

function buildVotingTrendzInPopUp()
{
	
	//var headElmt = document.getElementById('votingTrendzDiv_Head');
	var bodyElmt = document.getElementById('votingTrendzDiv_Body');
	
	var str='';
  
	str+=' <h1 class="cp-sub-title"><span><b>View Voting Trendz</b></span></h1>';
	str+='<div class="cp-cont-sec">'
	str+='<div id="cp-cont-sub-fields">';
	str+='<div id="votingTrendzContentDiv_main" class="votingTrendzContentDivClass">';	
	str+='<div id="votingTrendzContentDiv">View '+constituencyPageMainObj.constituencyInfo.constituencyName+' complete male and female voting trendz.</div>';
	str+='<div id="votingTrendzButtonDiv" class="view-all"><a href="javascript:{}" onclick="viewVotingTrendzPopup()"/>View</a></div>';
	str+='</div>';
	str+='</div>';
	str+='</div>';
	
	if(bodyElmt)
	{
		bodyElmt.innerHTML=str;
		var postButton = new YAHOO.widget.Button("votingTrendzButton");
		postButton.on("click", viewVotingTrendzPopup);
	}
}

function viewVotingTrendzPopup()
{	
	var popup1 = window.open("jsp/votingTrendzPopup.jsp","popup2","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
	popup1.focus();
	//popup1.electionTrendzReportVO = constituencyPageMainObj.electionTrendzReportVO;
}
function hideZptcDiv()
{
	var imgElmt = document.getElementById("zptcPartyTrendsDetailsDiv");
	var electionDetails="";
	electionDetails +="<br/>";
	electionDetails +="<b>Zptc Data is not available.</b>";			
	imgElmt.innerHTML = electionDetails;		

	 var candLink = document.getElementById("zptcCandidateLink");
	 if(candLink == null)
		 return;
	 var candidateLink="";
	 candLink.innerHTML = candidateLink;
}
function hideMptcDiv()
{
	var imgElmt = document.getElementById("mptcPartyTrendsDetailsDiv");
	var electionDetails="";
	electionDetails +="<br/>";
	electionDetails +="<b>Mptc Data is not available.</b>";			
	imgElmt.innerHTML = electionDetails;

	 var candLink = document.getElementById("mptcCandidateLink");
	 var candidateLink="";
	 candLink.innerHTML = candidateLink;
}
function hideMptcZptcDiv()
{
	var divElmt = document.getElementById("zptcMptcCompleteData");
	if(divElmt == null)
		return;
	divElmt.style.display = 'none';	
}
function buildZptcResults(results){

	/*var str='';
	if(results==null && results.length>0)
	{
		alert(results);
		str +='<font color="#5CB275" size:10px>No Data available .......</font>';
		document.getElementById('zptcDivBody').style.display='none';
		document.getElementById('zptcElectionIdsSelectDiv').style.display='none';
		document.getElementById('zptcCandidateLink').style.display='none';

	}*/
	
	assignToPartyDataArray = new Array();
	var candLink = document.getElementById("zptcCandidateLink");
	if(candLink == null)
	{
		document.getElementById('zptcCandidateLink').innerHTML='';;
		return;
	}
	var linkRef = '<a href="javascript:{}" title="Click here to View '+constituencyName+' '+consticType+' Constituency ZPTC Candidates Election Results" onclick="redirectZptcCandidateLink()" style="text-decoration:none;float:right" class="candidateDetailsStyle" >Show Results</a>';
	candLink.innerHTML = linkRef;
	totalZptcSeats = results[0].totalSeats;		//	var totalZptcSeats,totalMptcSeats;
	for(var i in results)
	{		
		var problemObj=		
		 {		
				partyName:results[i].partyName,
				participatedSeats:results[i].participatedSeats,
				seatsWonByParty:results[i].seatsWonByParty,
				percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
		 };
		
		assignToPartyDataArray.push(problemObj);
		tehsilDetails.partyArray=assignToPartyDataArray;	
	}

	var zptcCount = document.getElementById("totalZptcCountResultDiv");
	zptcCount.innerHTML ='';
	var totalZptcSeats='';
	totalZptcSeats+="<b>"+results[0].totalSeats+"</b>";
	zptcCount.innerHTML +=totalZptcSeats;
	var emptyArr = new Array();
    if(results.length == 0)
	{	
		document.getElementById('zptcDiv').innerHTML='';
		tehsilDetails.partyArray = emptyArr;
	
	}
    initializeResultsTableForParty();
	buildZptcChart(tehsilDetails.partyArray);
}

function buildMptcResults(results){

	assignToPartyDataArray = new Array();

	var candLink = document.getElementById("mptcCandidateLink");
	if(candLink == null)
		return;
	var linkRef = '<a href="javascript:{}" title="Click here to View '+constituencyName+' '+consticType+' Constituency MPTC Candidates Election Results" onclick="redirectMptcCandidateLink()" style="text-decoration:none;float:right" class="candidateDetailsStyle" >Show Results</a>';
	candLink.innerHTML = linkRef;
	  totalMptcSeats = results[0].totalSeats;
	for(var i in results)
	{		
		var problemObj=		
		 {		
				partyName:results[i].partyName,
				participatedSeats:results[i].participatedSeats,
				seatsWonByParty:results[i].seatsWonByParty,
				percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
		 };
		
		assignToPartyDataArray.push(problemObj);
		tehsilDetails.partyMptcArray=assignToPartyDataArray;	
	}

	var mptcCount = document.getElementById("totalMptcCountResultDiv");
	mptcCount.innerHTML='';
	
	var totalMptcSeats='';
	totalMptcSeats+="<b>";
	totalMptcSeats+=results[0].totalSeats;
	totalMptcSeats+="</b>";
	mptcCount.innerHTML +=totalMptcSeats;
	
	var emptyArr = new Array();
    if(results.length == 0)
	{	
    	tehsilDetails.partyMptcArray = emptyArr;				
	}
    initializeMptcResultsTableForParty(); 
	buildMptcChat(tehsilDetails.partyMptcArray);
}

	function buildMptcChat(results){

	var data = new google.visualization.DataTable();
	data.addColumn('string','Party');
	data.addColumn('number','Seats Won');
	data.addRows(results.length);

	for(var j=0; j<results.length; j++)
	{
		data.setValue(j,0,results[j].partyName);
		data.setValue(j,1,results[j].seatsWonByParty);
	} 

	var chart = new google.visualization.PieChart(document.getElementById('MptcChartDiv'));
		chart.draw(data, {width: 330, height: 230,legend:'right',legendTextStyle:{fontSize:12}, title:'All Parties Performance In '+constituencyName+' MPTC\'s In '+mptcElectionYear+'',titleTextStyle:{color:'blue',fontName:'verdana',fontSize:9}
	});




}

function initializeResultsTableForParty(){
	
	var resultsDataSourceForTehsil = new YAHOO.util.DataSource(tehsilDetails.partyArray);
	resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSourceForTehsil.responseSchema = {
		fields : [ {
			key : "partyName"
		}, {
			key : "participatedSeats"
		}, {
			key : "seatsWonByParty"
		}, {
			key : "percentageOfVotesWonByParty"
		}]
	};

	var resultsColumnDefsForTehsil = [ {
		key : "partyName",
		label : "Party",
		sortable : true
	}, {
		key : "participatedSeats",
		label : "Seats",
		sortable : true
	}, {
		key : "seatsWonByParty",
		label : "Won",
		sortable : true
	}, {
		key : "percentageOfVotesWonByParty",
		label : "Votes %",
		sortable : true
	} ];
	if(tehsilDetails.partyArray.length >10)
	{
		var myConfigsForTehsil = {
					paginator : new YAHOO.widget.Paginator({
			        rowsPerPage: 10		  
	    })
		};
	}		
	myDataTableForParty = new YAHOO.widget.DataTable("zptcPartyTrendsDetailsDiv",resultsColumnDefsForTehsil, resultsDataSourceForTehsil,myConfigsForTehsil);
	 
	return {
		oDS:resultsDataSourceForTehsil, 
		oDT:myDataTableForParty			
	}; 

	
}

function buildZptcChart(results)
{
	var data = new google.visualization.DataTable();
	data.addColumn('string','Party');
	data.addColumn('number','Seats Won');
	data.addRows(results.length);

	for(var j=0; j<results.length; j++)
	{
		data.setValue(j,0,results[j].partyName);
		data.setValue(j,1,results[j].seatsWonByParty);
	} 

	var chart = new google.visualization.PieChart(document.getElementById('zptcChartDiv'));
		chart.draw(data, {width: 330, height: 230,legend:'right',legendTextStyle:{fontSize:12}, title:'All Parties Performance In '+constituencyName+' ZPTC\'s In '+zptcElectionYear+'',titleTextStyle:{color:'blue',fontName:'verdana',fontSize:9}
	});
}
function initializeMptcResultsTableForParty(){
	
	
	var resultsDataSourceForTehsil = new YAHOO.util.DataSource(tehsilDetails.partyMptcArray);
	resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSourceForTehsil.responseSchema = {
		fields : [ {
			key : "partyName"
		}, {
			key : "participatedSeats"
		}, {
			key : "seatsWonByParty"
		}, {
			key : "percentageOfVotesWonByParty"
		}]
	};

	var resultsColumnDefsForTehsil = [ {
		key : "partyName",
		label : "Party",
		sortable : true
	}, {
		key : "participatedSeats",
		label : "Seats",
		sortable : true
	}, {
		key : "seatsWonByParty",
		label : "Won",
		sortable : true
	}, {
		key : "percentageOfVotesWonByParty",
		label : "Votes %",
		sortable : true
	} ];
	if(tehsilDetails.partyMptcArray.length >10)
	{
		var myConfigsForTehsil = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 10 
	    })
		};
	}		
	myDataTableForMptcParty = new YAHOO.widget.DataTable("mptcPartyTrendsDetailsDiv",resultsColumnDefsForTehsil, resultsDataSourceForTehsil,myConfigsForTehsil);

	return {
		oDS:resultsDataSourceForTehsil, 
		oDT:myDataTableForMptcParty			
	}; 		
}
function getAllZptcYears()

{	 
	

	if(tehsilElections.zptcElectionYears.length!=0){
		var selectDiv = document.getElementById("zptcElectionIdsSelectDiv");
		var electionYearSelect="";
		electionYearSelect+="<b>Select a Election Year :&nbsp;&nbsp;&nbsp;</b>";
		electionYearSelect+='<select class="selectWidth" id="staticGrpSelectBox" name="zptcYears" onchange="funChangeReq();getZptcPartyDetails(this.options[this.selectedIndex].value)">';
		for(var i in tehsilElections.zptcElectionYears)
		{
			electionYearSelect+='<option value='+tehsilElections.zptcElectionYears[i].id+'>'+tehsilElections.zptcElectionYears[i].value+'</option>';
		}
		electionYearSelect+='</select>&nbsp;&nbsp;&nbsp;';
		if(selectDiv != null)
			selectDiv.innerHTML = electionYearSelect;
		getZptcPartyDetails(tehsilElections.zptcElectionYears[0].value);
	}
}

function funChangeReq()
{
     
 changeReq = 1;
}
function getAllMptcYears()
{
	if(tehsilElections.mptcElectionYears.length!=0){
		var selectDiv = document.getElementById("mptcElectionIdsSelectDiv");
		var electionYearSelect="";
		electionYearSelect+="<b>Select a Election Year :&nbsp;&nbsp;&nbsp; </b>";
		electionYearSelect+='<select class="selectWidth" id="staticGrpSelectBox" name="mptcYears" onchange="funChangeReq();getMptcPartyDetails(this.options[this.selectedIndex].value)">';	   

		for(var i in tehsilElections.zptcElectionYears)
		{			   
			electionYearSelect+='<option value='+tehsilElections.mptcElectionYears[i].id+'>'+tehsilElections.mptcElectionYears[i].value+'</option>';
		}
		electionYearSelect+='</select>&nbsp;&nbsp;&nbsp;';
		if(selectDiv != null)
			selectDiv.innerHTML = electionYearSelect;
		
		getMptcPartyDetails(tehsilElections.mptcElectionYears[0].value);
	}			  		
}

function getMunicipalityResults()
{

	/*if(document.getElementById('municipalityData_main').style.display=='none')
		document.getElementById('municipalityData_main').style.display='block';
	*/
	if(document.getElementById('zptcPartyTrendsDetailsDiv').style.display=='block')
		document.getElementById('zptcPartyTrendsDetailsDiv').style.display='none';
	if(document.getElementById('mptcPartyTrendsDetailsDiv').style.display=='block')
		document.getElementById('mptcPartyTrendsDetailsDiv').style.display='none';
	var lebElmt = document.getElementById("municipalitySelect");
	if(lebElmt == null)
		return;
	var lebElmtValue = lebElmt.options[lebElmt.selectedIndex].value; 
	  var id;
	if(constituencyId == null || constituencyId =='')
	 { 
	    id = constiId;
	 }
	else
	 {
	    id = constituencyId;
	 }	 
	var jsObj = {
			localBodyElectionId:lebElmtValue,
			constituencyId:id,
			task:"municipalElectionsInfo"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getLocalBodyConstiResults.action?"+rparam;
	callAjax(jsObj, url);
}

function getMandalVotesShareDetailsChart(constId)
{
    var jsObj = {
			constituencyId:constId,
			task:"mandalVotesShareDetailsChart"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getMandalsVotesShareInConstituencyAjaxAction.action?"+rparam;
	callAjax(jsObj, url);
}

function getAllPartiesAllElectionResultsChart(constId)
{
	
    var jsObj = {
			constituencyId:constId,
			task:"partiesPerformanceInDiffElectionsAjax"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getPartiesPerformanceInDiffElectionsAjax.action?"+rparam;
	callAjax(jsObj, url);
}

function getCoroporationResults()
{
	var lebElmt = document.getElementById("corporationSelect");
	if(lebElmt == null)
		return;
	var lebElmtValue = lebElmt.options[lebElmt.selectedIndex].value; 
	   var id;
	if(constituencyId == null || constituencyId =='')
	 { 
	    id = constiId;
	 }
	else
	 {
	    id = constituencyId;
	 }	 
	var jsObj = {
			localBodyElectionId:lebElmtValue,
			constituencyId:id,
			task:"corporationElectionsInfo"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getLocalBodyConstiResults.action?"+rparam;
	callAjax(jsObj, url);
}

function getGreaterResults()
{
	var lebElmt = document.getElementById("greaterSelect");
	if(lebElmt == null)
		return;
	var lebElmtValue = lebElmt.options[lebElmt.selectedIndex].value; 
	var id;
	if(constituencyId == null || constituencyId =='')
	 { 
	    id = constiId;
	 }
	else
	 {
	    id = constituencyId;
	 }	 
	var jsObj = {
			localBodyElectionId:lebElmtValue,
			constituencyId:id,
			task:"greaterElectionsInfo"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getGreaterConstiResults.action?"+rparam;
	callAjax(jsObj, url);
}

function changeWardWiseResultsCriteria(type)
{
	var partySelectElmt = document.getElementById("wardWise_parties");
	var wardSelectElmt = document.getElementById("wardWise_ward");
	
	if(type == "all")
	{
		partySelectElmt.style.visibility = 'hidden';
		wardSelectElmt.style.visibility = 'hidden';		
		getWardWiseElectionResults('all',0);
	}
	else if(type == "partyWise")
	{
		wardSelectElmt.style.visibility = 'hidden';
		partySelectElmt.style.visibility = 'visible'			
	}
	else if(type == "wardWise")
	{
		partySelectElmt.style.visibility = 'hidden';
		wardSelectElmt.style.visibility = 'visible'			
	}
}

function getWardWiseElectionResults(type,value)
{	
	var jsObj=
	{		
		type:type,
		value:value,
		constituencyId:constituencyId,
		task:"getGhmcResultsBasedOnSelection"					
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getGhmcResultsAction.action?"+rparam;						
	
	callAjax(jsObj,url);
}

function getAnnouncementDetails(constituencyId)
{

	var jsObj=
	{		
		constituencyId:constituencyId,
		task:"getAnnouncementDetailsOfAConstituency"					
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAnnouncementDetailsOfAConstituency.action?"+rparam;						
	
	callAjax(jsObj,url);

}

function getUserAnnouncementDetails(userId,name,divId)
{
	
	var jsObj=
	{		
		userId : userId,
		name : name,
		divId : divId,
		task:"getUserAnnouncementDetails"			
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAnnouncementDetailsOfAConstituency.action?"+rparam;						
	callAjax(jsObj,url);
}

function buildAnnouncementDetails(result)
{

	var resultDiv = document.getElementById('announcementsInConstituencyDiv');
	var str = '';
	if(result == null || result.length == 0)

	{
		
		str += '&nbsp;&nbsp;&nbsp; NO Announcements From Your Leaders';
		resultDiv.innerHTML = str;
	}
	
	
	for(var i=0;i<result.length;i++)
	{
		var id = 'annUserAjaxImg'+i;

		str += '<div class="annDivId">'
		str += '<Table>';
		str += '<tr class="annHeaderFont"><td><table><tr>';
		str += '<th style="width:88px;color:navy;">Announcement<th>';
		str += '<td class="li-red"">'+result[i].fromDate+'<td>';
		//str += '<td><Div onClick="getUserAnnouncementDetails('+result[i].userId+',\''+result[i].userName+'\',\'annUserAjaxImg'+i+'\')"></div></td>';'+result[i].userName+'(before div),class="annUserNameDiv"
		str += '<td><Div class="annUserNameDiv" onClick="getUserAnnouncementDetails('+result[i].userId+',\''+result[i].userName+'\',\'annUserAjaxImg'+i+'\')">'+result[i].userName+'</div></td>';
		//str += '<td><Div id="annUserAjaxImg'+i+'" style="padding-left:10px;display:none;"><img src="images/icons/ajaxImg.gif" height="20px;"></img></div></td>';
		str += '</tr></table></td></tr>';
		str += '<tr><td class="annText" style="font-family: verdana;font-size: 11px;" >'+result[i].title+'</td></tr>';
		str += '<tr class="annHeaderFont" style="color:navy;"><th>Description</th></tr>';
		str += '<tr><td class="annText" style="font-family: verdana;font-size: 11px;" >'+result[i].message+'</td></tr>';
		str += '</Table>';
		str += '</div>'
	}
	resultDiv.innerHTML = str;
}

function buildUserAnnouncementDetails(jsObj,result)
{
	var resultDiv = document.getElementById('announcementsOfAUserDiv');
	var str = '';

	if(result == null || result.length == 0)
	 {
		return;
     }
	$("#announcementsOfAUserDiv").dialog({ stack: false,
							    height: 620,
								width: 700,
								position:[102,713],								
								modal: true,
								title:'<font color="Navy">Announcements From '+jsObj.name+'</font>',
								overlay: { opacity: 0.5, background: 'black'},
							   });
						

			
	var str = '';
	
  for(var i=0;i<result.length;i++)
	{
		var id = 'annUserAjaxImg'+i;

		str += '<div class="annDivId1">'
		str += '<Table>';
		str += '<tr class="annHeaderFont"><td><table><tr>';
		str += '<th style="width:200px;color: #0053B2;">Announcement<th>';
		str += '<td style="width:120px;color: #0053B2;font-size: 14px;">'+result[i].fromDate+'<td>';
		str += '<td><Div class="annUserNameDiv">';
		
		if(constituencyId == result[i].constituency)
			str += result[i].constituencyName;
		else
			str += '<a href="constituencyPageAction.action?constituencyId='+result[i].constituency+'" style="color:#ffffff;">'+result[i].constituencyName+'</a>';

		str += '</div></td>';
		str += '</tr></table></td></tr>';
		str += '<tr><td class="annText" style="font-family: verdana;font-size: 12px;padding-left: 5px;">'+result[i].title+'</td></tr>';
		str += '<tr class="annHeaderFont" style="color: #0053B2;"><th>Description</th></tr>';
		str += '<tr><td class="annText" style="font-family: verdana;font-size: 12px;padding-left: 5px;">'+result[i].message+'</td></tr>';
		str += '</Table>';
		str += '</div>'
	}
	resultDiv.innerHTML = str;
}

function hideAnnouncementDetails()
{
	document.getElementById('constituencyAnnouncementsDiv').style.display = 'block';
}

function showAjaxImage(divId)
{
	document.getElementById(divId).style.display = 'block';
}

function hideAjaxImage(divId)
{
	document.getElementById(divId).style.display = 'none';
}

function initializeConstituencyPage()
{
	showCurrentlyElectedCandidate();
buildConstituencyInfo();
	buildConstituencyConnectPeopleWindow();
	buildProblemPostingWindow();

	buildAnalyzeConstituencyWindow();	
	buildProblemViewingWindow();
	buildRightlayoutMap();
	buildElectionResults();	
	getAllPartiesAllElectionResultsChart(constituencyPageMainObj.constituencyInfo.constituencyId);
	getMandalVotesShareDetailsChart(constituencyPageMainObj.constituencyInfo.constituencyId);
	
	getAnnouncementDetails(constituencyPageMainObj.constituencyInfo.constituencyId);
	buildCenterVotersCandidateInfoContent();

    getConstituencyElections();
	
	//getMunicipalityResults();
	

	buildVotingTrendzInPopUp();
	buildVotingTrendzLayout("constituencyVotersInfoDiv_Main",constituencyPageMainObj.electionTrendzReportVO);
	buildelectionYearsForVotingTrendz(constituencyPageMainObj.electionTrendzReportVO.previousElectionYears);
	//candidateNominationsdetails(constituencyPageMainObj.constituencyInfo.constituencyId);

}
function buildNews()
{	

	var options = {
    "format" : "300x250",
	"queryList" : [
          {
            "title" : "Nellore",
            "q" : "Kavali,Nellore"
          }
     ],
	"linkTarget" : "_blank"
  }

  var content = document.getElementById('Politician_news');
  var newsShow = new google.elements.NewsShow(content, options);
}

function getMunicipalityResultsForAssemblies()
{
	
 var constituencyIds = new Array();
	/*if(document.getElementById('municipalityData_main').style.display=='none')
		document.getElementById('municipalityData_main').style.display='block';*/
	
	if(document.getElementById('zptcPartyTrendsDetailsDiv').style.display=='block')
		document.getElementById('zptcPartyTrendsDetailsDiv').style.display='none';
	if(document.getElementById('mptcPartyTrendsDetailsDiv').style.display=='block')
		document.getElementById('mptcPartyTrendsDetailsDiv').style.display='none';
	var lebElmt = document.getElementById("municipalitySelect");
	if(lebElmt == null)
		return;
	var lebElmtValue = lebElmt.options[lebElmt.selectedIndex].value; 
	var consIds = constituencyPageMainObj.munAssemblyConstituencyIds;
	
	 
			    for(var i in consIds){
				 constituencyIds.push(consIds[i].constituencyId);
				}
				 
		var jsObj = {
			localBodyElectionId:lebElmtValue,
			constituencyId:constituencyIds,
			task:"municipalElectionsInfoByAssmeblyConsIds"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getLocalBodyConstiResultsForAssembly.action?"+rparam;
	callAjax(jsObj, url);
}


function getCoroporationResultsForAssemblies()
{
	 var constituencyIds = new Array();
	var lebElmt = document.getElementById("corporationSelect");
	if(lebElmt == null)
		return;
	var lebElmtValue = lebElmt.options[lebElmt.selectedIndex].value; 
	  var consIds = constituencyPageMainObj.munAssemblyConstituencyIds;
	
	 
			    for(var i in consIds){
				 constituencyIds.push(consIds[i].constituencyId);
				}
				 
	var jsObj = {
			localBodyElectionId:lebElmtValue,
			constituencyId:constituencyIds,
			task:"corporationElectionsInfoByAssmeblyConsIds"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getLocalBodyConstiResultsForAssembly.action?"+rparam;
	callAjax(jsObj, url);
}


function showMuncipalDetailsForAssemblyConst(result,electionType){		
		
		var muncipalityDIV = '';
		
		if(electionType == "MUNCIPALITY"){

			
			muncipalityDIV = document.getElementById("muncipalitiesDiv1");
			
		}else{			
			
			muncipalityDIV = document.getElementById("corporationsDiv");
		
			
		}	
		
		var listSize = result[0].totalMuncipalities-1;
		var rvStr = '';
		rvStr+='<table width="92%">';		
		for(var i in result)
		{		
			if(i%2==0){
				rvStr+='</tr>';
				rvStr+='<tr>';
			}
			
			if(i == listSize)
				rvStr+='<td colspan="2" style="vertical-align: top;float:left;">';
			else
				rvStr+='<td  style="vertical-align: top;float:right;">';		
			assignToPartyDataArray = new Array();
			
			rvStr += '<div class="localBodiesElectionHead" style="margin-top: 15px;color:#E5805C;">';
			rvStr += '<table>';
			rvStr += '<tr>';
			rvStr += '<td width="10px"><img width="8" height="9" src="images/icons/indexPage/listIcon.png"></img></td>';
			if(electionType == "MUNCIPALITY")
				rvStr += '<td style="font-weight:bold;font-size:15px;"><u> '+result[i].muncipalityName+' '+result[i].latestMuncipalElectionYear+' Muncipality Election Details</u></td>';
			else
				rvStr += '<td style="font-weight:bold;font-size:15px;"><u> '+result[i].muncipalityName+' '+result[i].latestMuncipalElectionYear+' Corporation Election Details</u></td>';
			rvStr += '</tr>';
			rvStr += '</table>';
			rvStr += '</div>';
			rvStr += '<div id="allMuncipalitiesDetails'+i+'" style="width:95%;vertical-align:top;" >';
			rvStr += '<table width="90%" style="width:auto;margin-left:5px;">';
			rvStr += '<tr>';
			if(electionType == "MUNCIPALITY")
			rvStr += '<th align="left" style="font-family:sans-serif;color:  #72587F;font-size:13px;">Muncipality Name :</th><td class="tableTextStyle" style="font-size:13px;font-family: Comic Sans MS;" align="left">&nbsp;'+result[i].muncipalityName+'</td>'; 
			else
			rvStr += '<th align="left" style="font-family:sans-serif;color:  #72587F;font-size:13px;">Corporation Name :</th><td class="tableTextStyle" style="font-size:13px;font-family: Comic Sans MS;" align="left">&nbsp;'+result[i].muncipalityName+'</td>'; 
			rvStr += '<th align="left" style="font-family:sans-serif;color:  #72587F;font-size:13px;">&nbsp;&nbsp;Total Wards :</th><td class="tableTextStyle" align="left">'+result[i].totalWards+'</td>';
			rvStr += '</tr>';
			rvStr += '<tr>';
			rvStr += '<th align="left" style="font-family:sans-serif;color: #72587F;font-size:13px;">Total Voters :</th><td class="tableTextStyle" align="left">'+result[i].totalVoters+'</td>';
			rvStr += '<th align="left" style="font-family:sans-serif;color: #72587F;font-size:13px;">&nbsp;&nbsp;Total Polled Votes :</th><td class="tableTextStyle" align="left">'+result[i].totalPolledVotes+'</td>';
			rvStr += '</tr>';
			rvStr += '</table>';	
			rvStr +='<div>';
			rvStr +='<table>';
			rvStr +='<tr><td style="height:10px;"></td></tr>';																					
			rvStr +='<tr>';																					
			rvStr +='<td style="vertical-align:top;">';			
			if(electionType == "MUNCIPALITY"){
				rvStr +='<a href="javascript:{}" title="Click here to view '+result[i].muncipalityName+' '+result[i].latestMuncipalElectionYear+' Muncipality Election Candidate Results " onclick="redirectMuncipalityLink('+ result[i].muncipalityId+','+result[i].latestMuncipalElectionYear+','+result[i].electionTypeId+',\''+result[i].muncipalityName+'\',\'MUNCIPALITY\')"  style="text-decoration:none;color:#ffffff;background:#639CA4" class="candidateDetailsStyle">Show Candidate Details</a></td>';
			}else{
				rvStr +='<a href="javascript:{}" title="Click here to view '+result[i].muncipalityName+' '+result[i].latestMuncipalElectionYear+' Corporation Election Candidate Results "  onclick="redirectMuncipalityLink('+ result[i].muncipalityId+','+result[i].latestMuncipalElectionYear+','+result[i].electionTypeId+',\''+result[i].muncipalityName+'\',\'CORPORATION\' )"  style="text-decoration:none;color:#ffffff;background:#639CA4;" class="candidateDetailsStyle">Show Candidate Details</a></td>';
			}			
			rvStr+='</td>';
			rvStr +='</tr>';
		
			rvStr +='<tr>';
			if(electionType == "MUNCIPALITY"){
				rvStr +='<td style="vertical-align: top;width:430px;"> <div><div id="dataTable'+i+'" class="muncipality"></div></div></td>';
			}else{
				rvStr +='<td style="vertical-align: top;width:430px;"> <div><div id="corporationDataTable'+i+'" class="muncipality"></div></div></td>';
			}
			rvStr +='</tr>';				
			rvStr +='</table></div>';
			rvStr+='</td>';
		}
        if(electionType == "MUNCIPALITY")
		   {		   
		     rvStr+='<tr>';
		     rvStr+='<td colspan="2"><div id="tabledata" style=" border: 1px solid #B7B2B2;margin-top: 5px;position:relative;"/></td>';
		     rvStr+='</tr>';
           }		
		rvStr+='</table>';		
		muncipalityDIV.innerHTML = rvStr;	
		
		for(var i in result)
		{
			var localDataArr = new Array();
			for(var j in result[i].muncipalityVO)
			{					
				var muncipalObj =
				 {		
						partyId: result[i].muncipalityVO[j].partyId,
						partyName:result[i].muncipalityVO[j].partyName,
						participatedSeats:result[i].muncipalityVO[j].participatedSeats,
						seatsWonByParty:result[i].muncipalityVO[j].seatsWonByParty,
						percentageOfVotesWonByParty:result[i].muncipalityVO[j].percentageOfVotesWonByParty				
				 };
				localDataArr.push(muncipalObj);
			}	
			if(electionType == "MUNCIPALITY"){	
				initializeMuncipalResultsTableForParty('dataTable'+i,localDataArr,electionType) ;
			}else{
				initializeMuncipalResultsTableForParty('corporationDataTable'+i,localDataArr,electionType) ;
				
				 
			}
		}
		if(electionType == "MUNCIPALITY")
		  buildMunicipalityGraph(result);
	}



     function buildMunicipalityGraph(result)
	  {
	     var partyArray = new Array();
		for(var a in result[0].muncipalityVO)
		 {
		   partyArray.push(result[0].muncipalityVO[a].partyName);
		 }
	    for(var i in result)
		{
		  for(var j in result[i].muncipalityVO)
		  {
		     var count = 0;
		     for(var k in partyArray)
			  {
			   if(partyArray[k] == result[i].muncipalityVO[j].partyName)
			     count = count+1;
			  }
			  if(count == 0)
			   partyArray.push(result[i].muncipalityVO[j].partyName);
		  }
		}
		  var data = new google.visualization.DataTable();
              data.addColumn('string', 'Municipality');
			  for(var b in partyArray)
              data.addColumn('number',partyArray[b]);
           for(var d in result)
		   {
		      var pdata = new Array();
			   pdata.push(result[d].muncipalityName);			  
			  for(var c in partyArray)
			   {			     
			     var partyCount = 0;
			     for(var e in result[d].muncipalityVO)
				 {
				   if(partyArray[c] == result[d].muncipalityVO[e].partyName)
				   {
				     pdata.push(result[d].muncipalityVO[e].seatsWonByParty);
					 partyCount=partyCount+1;
				   }
				 }
				 if(partyCount == 0)
				 pdata.push(0);
			   }
		    data.addRow(pdata);
		   }
		   
		  ctitle = 'All Parties Muncipality Wise Performance In Constituency Based On Seats Won';
		  new google.visualization.LineChart(document.getElementById('tabledata')).
			  draw(data, {curveType: "function",width: 880, height: 380, pointSize: 4,title:ctitle,hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'navy'}}
			  });
	  }
	
		

function initializeMuncipalResultsTableForParty(divId, dataSrc,electionType)
		{
			
		var resultsDataSourceForTehsil = new YAHOO.util.DataSource(dataSrc);
		resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		YAHOO.widget.DataTable.partyLink = function(elLiner, oRecord, oColumn, oData) 
	    {
		     var Party = oRecord.getData("partyName");
		     var partyIds = oRecord.getData("partyId");
		     if(Party != 'IND' && partyIds != null){
			    elLiner.innerHTML ="<a title='Click here to view "+Party+" party Election results,news,photos and videos' href='partyPageAction.action?partyId="+partyIds+"' >"+Party+"</a>";
		      }
		else
			elLiner.innerHTML =""+Party;
	    };
		resultsDataSourceForTehsil.responseSchema = {
			fields : [ {
				key : "partyName"
			}, {
				key : "participatedSeats"
			}, {
				key : "seatsWonByParty"
			}, {
				key : "partyId"
			}, {
				key : "percentageOfVotesWonByParty"
			}]	
		};
		
		var resultsColumnDefsForTehsil = [ 
				{key:"partyName",label : "Party Name",sortable:true, formatter:YAHOO.widget.DataTable.partyLink}, 
				{key:"participatedSeats",label : "Participated Seats",sortable:true,resizeable:true}, 
				{key:"seatsWonByParty",label : "Seats Won",sortable:true, resizeable:true}, 
				{key:"percentageOfVotesWonByParty",label : "Votes %", sortable:true, resizeable:true}	           
		];

						
		var myDataTableForMuncipalParty	 = new YAHOO.widget.DataTable(divId,resultsColumnDefsForTehsil, resultsDataSourceForTehsil);

		if(electionType=='CORPORATION')
			{
		
		var data = new google.visualization.DataTable();
		data.addColumn('string','partyName');
		data.addColumn('number','seatsWonByParty');
		data.addRows(dataSrc.length);
		for(var j=0; j<dataSrc.length; j++)
		{
			
			data.setValue(j,0,dataSrc[j].partyName);
			data.setValue(j,1,dataSrc[j].seatsWonByParty);
		}
		var chart = new google.visualization.PieChart(document.getElementById('CorporationPieChartDiv'));
	
		chart.draw(data,{width: 350, height: 230,legend:'right',legendTextStyle:{fontSize:12},title:'All Parties Performance In District',titleTextStyle:{color:'blue',fontName:'verdana',fontSize:9}});
			}
			
	}
	function redirectMuncipalityLink(muncipalityId,latestMuncipalElectionYear,electionTypeId,name,electionType){
		
		var browser4 = window.open("muncipalElectionReportAction.action?muncipalityId="+muncipalityId+"&muncipalityElectionType="+electionType+"&name="+name+"&muncipalityElectionId="+electionTypeId+"&electionYear="+latestMuncipalElectionYear,"browser3","scrollbars=yes,height=670,width=1170,left=200,top=200");
		browser4.focus();
	}

	