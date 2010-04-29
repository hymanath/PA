var partyElectionResultsAnalysisObj ={
		candidateCommentsArr:[]
};
var emptyArray = new Array();
function showAnalysisDetails(results)
{
	
	var analysisDetailsEl = document.getElementById("analysisDetails");
	analysisDetailsEl.innerHTML = '';
	for(var i in results)
	{	
		var resultsArr = new Array();
		var contentStr='';
		contentStr+='<FIELDSET>';
		contentStr+='<LEGEND>'+results[i].constituencyName+' Constituency Analysis</LEGEND>';
		contentStr+='<DIV id="basicDetails'+i+'">';
		contentStr+='<TABLE width="50%" border="1">';
		contentStr+='<TR>';
		contentStr+='<TH>Party:</TH>';
		contentStr+='<TD>'+results[i].partyName+'</TD>';
		contentStr+='<TH>Constituency:</TH>';
		contentStr+='<TD>'+results[i].constituencyName+'</TD>';
		contentStr+='</TABLE>';
		contentStr+='<DIV class="yui-skin-sam" style="margin-top:10px;margin-bottom:10px;"><DIV id="dataTable'+i+'"></DIV></DIV>';
		contentStr+='</DIV>';
		contentStr+='</FIELDSET>';
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
		
		
		analysisDetailsEl.innerHTML += contentStr;
		buildCandidateCommentsDataTable('dataTable'+i,resultsArr)
	}
	
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