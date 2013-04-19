var partyElectionResultsAnalysisObj ={
		candidateCommentsArr:[],
		notAnalyzedCandidates:[]
};
var emptyArray = new Array();
var selectedBodyDivId = null;


function showAnalysisDetails(jsObj,results)
{		
	var rank;	
	if(results && results.length>0 && results[0].candidateComments && results[0].candidateComments.length>0)
	{		
		rank = results[0].candidateComments[0].rank;	
	}
	else
	{
		if((jsObj.position && jsObj.position == "Won") || (jsObj.resultStatus && jsObj.resultStatus == "WON"))
			rank = 1;
		else if((jsObj.position && jsObj.position == "Lost") || (jsObj.resultStatus && jsObj.resultStatus == "LOST"))
			rank = 2;
	}

	var contentStr='';
	var analysisDetailsEl = document.getElementById("analysisDetails");
	analysisDetailsEl.innerHTML = '';
	for(var i in results)
	{		
		var candidateId = results[i].candidateComments[0].candidateId;
		var candidateName = results[i].candidateComments[0].candidate;
		var constituencyId = results[i].constituencyId;
		var constituencyName = results[i].constituencyName;
		var partyName = results[i].partyName;		
		var nominationId = results[i].candidateComments[0].nominationId;
		contentStr += '<div id="'+results[i].constituencyName+'_main" class="constituencyAnalysisMainDiv">';
		contentStr += '	<div id="'+results[i].constituencyName+'_head" class="constituencyAnalysisHeadDiv" onclick="showBodyDiv(this.id,\''+results[i].candidateComments[0].nominationId+'\')">';
		contentStr += '		<table width="100%"><tr>';
		contentStr += '		<td width="3%"><img style="cursor:default;" height="30" width="15" id="'+results[i].constituencyName+'_img" onclick="showBodyDiv(this.id,nominationId)" src="images/icons/jQuery/next.png"/></td>';
		contentStr += '		<td style="vertical-align:center;">'+results[i].constituencyName+' - Constituency Analysis Details</td>';
		//contentStr += '     <td style="vertical-align:center"> Constituency Analysis Details - No. Of Reasons '+results[i].candidateComments.length+'</td>';
		contentStr += '		<td width="20%" align="right"><a href="javascript:{}" class="analysisLink" onclick="getMoreDetails('+results[i].constituencyId+')">View Complete Results<a/></td>';
		contentStr += '		<td width="12%" align="right"><a href="javascript:{}" class="analysisLink" onclick="showCommentsDialog('+candidateId+',\''+candidateName+'\',\'candidate\',\''+rank+'\','+constituencyId+',\''+constituencyName+'\',\''+partyName+'\',\''+jsObj.task+'\',\''+jsObj.status+'\')" >AddReason</a></td>';
		contentStr += '		</tr></table>';
		contentStr += '</div>';

		if(i == 0)
			contentStr += '	<div id="'+results[i].constituencyName+'_body" class="yui-skin-sam constituencyAnalysisBodyDiv" style="display:none">';
		else
			contentStr += '	<div id="'+results[i].constituencyName+'_body" class="yui-skin-sam constituencyAnalysisBodyDiv" style="display:none">';

		contentStr += '		<div id="dataTable'+i+'"></div>';
		contentStr += '</div>';
		contentStr += '</div>';
	}
	
	analysisDetailsEl.innerHTML = contentStr;

	/*for(var i in results)
	{
		var resultsArr = new Array();
		for(var j in results[i].candidateComments)
		{
			var obj={
					candidate: results[i].candidateComments[j].candidate,
					commentDesc: results[i].candidateComments[j].commentDesc,
					commentCategory: results[i].candidateComments[j].commentCategory,					
					commentedBy: results[i].candidateComments[j].commentedBy,
					commentedOn: results[i].candidateComments[j].commentedOn					
			};
			resultsArr.push(obj);
		}

		
		//buildCandidateCommentsDataTable('dataTable'+i,resultsArr);
	}*/
}

function showBodyDiv(id,nominationId)
{
	
	if(nominationId == null || nominationId == "null")
		return;
	
	var bodyId = id.substring(0,id.indexOf('_'))+"_body";
	var bodyElmt = document.getElementById(bodyId);


	if(!bodyElmt)
		return;

	var jsObj= 
	{
		nominationId: nominationId,	 	
		bodyId:bodyId,
		task:"getConstituencyAnalyzedComments"		
	}
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	url = "getConstituencyAnalyzedCommentsAction.action?"+param+"&hidden="+hidden;

	callAjax(param,jsObj,url);

	

	if(selectedBodyDivId != null)
		$("#"+selectedBodyDivId).slideToggle();

	selectedBodyDivId = bodyId;		
	$("#"+bodyId).slideToggle();

	/*if(bodyElmt.style.display == 'none')
		bodyElmt.style.display = 'block';
	else if(bodyElmt.style.display == 'block')
		bodyElmt.style.display = 'none';*/

}

function showConstituencyAnalyzedComments(jsObj,myResults)
{
	var elmt = document.getElementById(jsObj.bodyId);
	elmt.style.display = "block";
	if(!elmt)
		return;
	
	var results = myResults[0];

	var str = '';
	str += '<div class="commentContent_candidate">';
	str += '<table class="commentContent_candidate_table">';
	str += '<tr>';
	str += '<th align="left">Candidate Name</th>';
	str += '<td>:</td>';
	str += '<th align="left">'+results.candidate+'</th>';			
	str += '</tr>';
	str += '<tr>';
	str += '<th align="left">Posted Users</th>';
	str += '<td>:</td>';
	str += '<th align="left">'+results.postedUsersCount+'</th>';			
	str += '</tr>';
	str += '</table>';
	str += '</div>';
	
	str += '<div class="commentContent_comment">';
	str += '<div class="commentsDetailsLabel"> Reasons </div>';
	if(results.commetsAndScores == null || results.commetsAndScores.length == 0)
	{
		str += '<font class="noCommentFont">No comments has been posted </font>';
	}
	else
	{
		str += '<table class="commentContentTable">';
		for(var j=0; j<results.commetsAndScores.length; j++)
		{
			var data = results.commetsAndScores[j];				
			
			str += '<tr>';
			str += '<td><img src="images/icons/districtPage/listIcon.png"></img></td>';
			str += '<td>'+data.commentCategory+'</td>';
			str += '<td>'+data.commentScore+'</td>';
			str += '</tr>';
		}
		str += '</table>';
	}
	str += '</div>';

	str += '</div>';

	elmt.innerHTML = str;

}

function buildCandidateCommentsDataTable(divId, dataSrc)
{	
	var candidateCommentsColumnDefs = [
														
		              	 	    {key: "candidate", label: "Candidate", sortable:true},
		              	 	 	{key: "commentDesc", label: "Comment", sortable:true},
		              	 	    {key: "commentCategory", label:"Reason", sortable:true},
		              	 	 	{key: "commentedBy", label: "Commented By", sortable:true},
		              	 	 	{key: "commentedOn", label: "Date"}  	 	 	      	 	 	
		              	 	    ];                	 	    

		var candidateCommentsDataSource = new YAHOO.util.DataSource(dataSrc); 
		
		candidateCommentsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSArray; 
		candidateCommentsDataSource.responseSchema = {
                fields: [		
                         		  {key: "candidate"},
                         		  {key: "commentDesc"},
                         		  {key: "commentCategory"},
                         		  {key: "commentedBy"},
                         		  {key: "commentedOn"}     		  
                         		  ] 
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10			        
			    })
			     
				};
		
		var candidateCommentsDataTable = new YAHOO.widget.DataTable(divId, candidateCommentsColumnDefs, candidateCommentsDataSource,myConfigs);					

}

function showNotAnalyzedDetails(jsObj,results)
{	
	var notAnalyzedCandidatesResults = new Array();
	var analysisDetailsEl = document.getElementById("analysisDetails");
	var str = '';
	str+='<div id="notAnalyzedDataTableDiv_main" class="yui-skin-sam"><div id="notAnalyzedDataTableDiv"> </div></div>';

	if(analysisDetailsEl)
	analysisDetailsEl.innerHTML = str;

	for(var i  in results)
	{
		if(results[i].rank == null)
		{	
			var ob={
					candidateName: results[i].candidateName,
					constituencyName: results[i].constituencyName,
					totalValidVotes: results[i].totalValidVotes,
					totalVotesEarned: results[i].totalVotesEarned,
					votesPercentage: results[i].votesPercentage,
					rank: '1',
					completeResults: '<a href="javascript:{}" onclick="getMoreDetails('+results[i].constituencyId+')">  Complete Results</a>',
					addReason : '<a href="javascript:{}" onclick="showCommentsDialog('+results[i].candidateId+',\''+results[i].candidateName+'\',\'candidate\',\''+results[i].rank+'\','+results[i].constituencyId+',\''+results[i].constituencyName+'\',\''+results[i].partyName+'\',\''+jsObj.task+'\')">  Add Reason</a>'
				};
			//partyElectionResultsAnalysisObj.notAnalyzedCandidates.push(ob);
			notAnalyzedCandidatesResults.push(ob);
		} else if(results[i].rank != null)
		{
			var obj={
					candidateName: results[i].candidateName,
					constituencyName: results[i].constituencyName,
					totalValidVotes: results[i].totalValidVotes,
					totalVotesEarned: results[i].totalVotesEarned,
					votesPercentage: results[i].votesPercentage,
					rank: results[i].rank,
					completeResults: '<a href="javascript:{}" onclick="getMoreDetails('+results[i].constituencyId+')">  Complete Results</a>',
					addReason : '<a href="javascript:{}" onclick="showCommentsDialog('+results[i].candidateId+',\''+results[i].candidateName+'\',\'candidate\',\''+results[i].rank+'\','+results[i].constituencyId+',\''+results[i].constituencyName+'\',\''+results[i].partyName+'\',\''+jsObj.task+'\')">  Add Reason</a>'
				};
			//partyElectionResultsAnalysisObj.notAnalyzedCandidates.push(obj);
			notAnalyzedCandidatesResults.push(obj);
		}	
	}
	
	buildCandidateElectionResultsDataTable(notAnalyzedCandidatesResults);		
}

function buildCandidateElectionResultsDataTable(notAnalyzedCandidatesResults)
{	

if(electionType == "Assembly" || electionType == "Parliament")
			var name = "Constituency"; 
		else if(electionType == "Zptc") 
			name = "Zptc Name";
		else if(electionType == "Mptc")
		name = "Mptc Name";
		else
		name = "Ward";
	var candidateElectionResultsColumnDefs = [
								{key: "candidateName", label: "Candidate", sortable:true},										
								{key: "constituencyName", label:name, sortable:true},								
								{key: "totalVotesEarned", label: "Votes Earned",formatter:"number", sortable:true},
								{key: "votesPercentage", label: "Votes Percentage",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},
								{key: "rank", label:"Rank", sortable:true},
								{key: "completeResults", label:"Complete Results"},
								{key: "addReason", label:"Add Reason"}
								];                	 	    

		var candidateElectionResultsDataSource = new YAHOO.util.DataSource(notAnalyzedCandidatesResults); 
		candidateElectionResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		candidateElectionResultsDataSource.responseSchema = {
				fields: [		  {key:"candidateName"},
								  {key:"constituencyName"},								 
								  {key:"totalVotesEarned", parser:"number"},
								  {key:"votesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber},								  
								  {key:"rank", parser:"number"},
								  {key:"completeResults"},
								  {key:"addReason"}
								  ] 
				};

		var myConfigs = { 
				paginator : new YAHOO.widget.Paginator({ 
					rowsPerPage    : 50,
					template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
					rowsPerPageOptions: [50,100,150,200], 
					pageLinks: 50			        
				})
				 
				};
		
		candidateElectionResultsDataTable = new YAHOO.widget.DataTable("notAnalyzedDataTableDiv", candidateElectionResultsColumnDefs, candidateElectionResultsDataSource,myConfigs);						
					
	
}