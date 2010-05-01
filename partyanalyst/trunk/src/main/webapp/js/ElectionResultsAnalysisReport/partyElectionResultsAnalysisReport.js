var partyElectionResultsAnalysisObj ={
		candidateCommentsArr:[],
		notAnalyzedCandidates:[]
};
var emptyArray = new Array();
function showAnalysisDetails(results)
{
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

		contentStr += '<div id="'+results[i].constituencyName+'_main" class="constituencyAnalysisMainDiv">';
		contentStr += '	<div id="'+results[i].constituencyName+'_head" class="constituencyAnalysisHeadDiv" onclick="showBodyDiv(this.id)">';
		contentStr += '		<table><tr>';
		contentStr += '		<td><img  id="'+results[i].constituencyName+'_img" onclick="showBodyDiv(this.id)" src="images/icons/plusNew.png"/></td>';
		contentStr += '		<td style="vertical-align:center;width:150px;"> - '+results[i].constituencyName+' </td>';
		contentStr += '     <td style="vertical-align:center"> Constituency Analysis Details - No. Of Reasons '+results[i].candidateComments.length+'</td>';
		contentStr += '		<td><input type="button" onclick="getMoreDetails('+results[i].constituencyId+')" value="View Complete Results"/></td>';
		contentStr += '		<td><input type="button" onclick="showCommentsDialog('+candidateId+',\''+candidateName+'\',\'candidate\',1,'+constituencyId+',\''+constituencyName+'\',\''+partyName+'\')" value="Add Reason"/></td>';
		contentStr += '		</tr></table>';
		contentStr += '</div>';
		contentStr += '	<div id="'+results[i].constituencyName+'_body" class="yui-skin-sam constituencyAnalysisBodyDiv" style="display:none">';
		contentStr += '		<div id="dataTable'+i+'"></div>';
		contentStr += '</div>';
		contentStr += '</div>';
		}
	
	analysisDetailsEl.innerHTML = contentStr;

	for(var i in results)
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

		
		buildCandidateCommentsDataTable('dataTable'+i,resultsArr);
	}
}

function showNotAnalyzedDetails(results)
{
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
					addReason : '<a href="javascript:{}" onclick="showCommentsDialog('+results[i].candidateId+',\''+results[i].candidateName+'\',\'candidate\',\''+results[i].rank+'\','+results[i].constituencyId+',\''+results[i].constituencyName+'\',\''+results[i].partyName+'\')">  Add Reason</a>'
				};
			partyElectionResultsAnalysisObj.notAnalyzedCandidates.push(ob);
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
					addReason : '<a href="javascript:{}" onclick="showCommentsDialog('+results[i].candidateId+',\''+results[i].candidateName+'\',\'candidate\',\''+results[i].rank+'\','+results[i].constituencyId+',\''+results[i].constituencyName+'\',\''+results[i].partyName+'\')">  Add Reason</a>'
				};
			partyElectionResultsAnalysisObj.notAnalyzedCandidates.push(obj);
		}	
	}
	
	buildCandidateElectionResultsDataTable();		
}

function buildCandidateElectionResultsDataTable()
{	
	var candidateElectionResultsColumnDefs = [
								{key: "candidateName", label: "Candidate", sortable:true},										
								{key: "constituencyName", label: "Constituency", sortable:true},								
								{key: "totalVotesEarned", label: "Votes Earned",formatter:"number", sortable:true},
								{key: "votesPercentage", label: "Votes Percentage",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},
								{key: "rank", label:"Rank", sortable:true},
								{key: "completeResults", label:"Complete Results"},
								{key: "addReason", label:"Add Reason"}
								];                	 	    

		var candidateElectionResultsDataSource = new YAHOO.util.DataSource(partyElectionResultsAnalysisObj.notAnalyzedCandidates); 
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


function showBodyDiv(id)
{
	
	var bodyId = id.substring(0,id.indexOf('_'))+"_body";
	var bodyElmt = document.getElementById(bodyId);


	if(!bodyElmt)
		return;
	if(bodyElmt.style.display == 'none')
		bodyElmt.style.display = 'block';
	else if(bodyElmt.style.display == 'block')
		bodyElmt.style.display = 'none';

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
