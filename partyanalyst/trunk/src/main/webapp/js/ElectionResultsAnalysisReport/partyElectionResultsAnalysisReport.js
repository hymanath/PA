var partyElectionResultsAnalysisObj ={
		candidateCommentsArr:[]
};
var emptyArray = new Array();
function showAnalysisDetails(results)
{
	var contentStr='';
	var analysisDetailsEl = document.getElementById("analysisDetails");
	analysisDetailsEl.innerHTML = '';
	for(var i in results)
	{		
		contentStr += '<div id="'+results[i].constituencyName+'_main" class="constituencyAnalysisMainDiv">';
		contentStr += '	<div id="'+results[i].constituencyName+'_head" class="constituencyAnalysisHeadDiv" onclick="showBodyDiv(this.id)">';
		contentStr += '		<table><tr>';
		contentStr += '		<td><img  id="'+results[i].constituencyName+'_img" onclick="showBodyDiv(this.id)" src="images/icons/plusNew.png"/></td>';
		contentStr += '		<td style="vertical-align:center;width:150px;"> - '+results[i].constituencyName+' </td>';
		contentStr += '     <td style="vertical-align:center"> Constituency Analysis Details - No. Of Reasons '+results[i].candidateComments.length+'</td>';
		contentStr += '		<td><input type="button" onclick="getMoreDetails('+results[i].constituencyId+')" value="View Complete Results"/></td>';
		contentStr += '		</tr></table>';
		contentStr += '</div>';
		contentStr += '	<div id="'+results[i].constituencyName+'_body" class="yui-skin-sam constituencyAnalysisBodyDiv" style="display:none">';
		contentStr += '		<div id="dataTable'+i+'"></div>';
		contentStr += '</div>';
		contentStr += '</div>';


		/*contentStr+='<FIELDSET>';
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
		contentStr+='</FIELDSET>';*/
	
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