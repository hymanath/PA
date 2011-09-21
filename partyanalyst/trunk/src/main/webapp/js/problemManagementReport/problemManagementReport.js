
function buildProblemHistoryDataTable(divId, probsArr) {
	var resultsDataSource = new YAHOO.util.DataSource(probsArr);
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "status"
		}, {
			key : "movedDate"
		},{
			key : "comments"
		},{
			key : "problemHistoryId"
		},{
			key : "isDelete"
		}]   
	};	

	var resultsColumnDefs = [ {
		key : "status",
		label : "Status",
		sortable : true,
		resizable:true
	}, {
		key : "movedDate",
		label : "Date",
		sortable : true,
		resizable:true
	},{
		key : "comments",
		label : "Comments If Any",
		sortable : true,
		resizable:true
	},{
		key : "problemHistoryId",
		label : "problemHistoryId",
		sortable : true,
		resizable:true,
		hidden:true	
	},{
		key : "isDelete",
		label : "isDelete",
		sortable : true,
		resizable:true,
		hidden:true	
	} ];	

	var myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,null);
}

function showProblemsHistoryReport(results){
	assignToProblemsArray = new Array();

			for(var i in results.problemHistories)
			{
				var problemObj= {
						problemHistoryId:results.problemHistories[i].problemHistoryId,
						isDelete:results.problemHistories[i].isDelete,
						comments:results.problemHistories[i].comments,
						movedDate:results.problemHistories[i].movedDate,						
						status:results.problemHistories[i].status					
					};
				
				assignToProblemsArray.push(problemObj);
			}
			var emptyArr = new Array();
		    if(results.length == 0)
			{	
		    	problemDetails.problemHistoryArray = emptyArr;				
			}
		    buildMoreDetailsPopUp(results, assignToProblemsArray);	
}

function  buildMoreDetailsPopUp(result, probsArr)
{   
		var contentStr = '';
		
		contentStr+='<div align="center"><h3>Complete Report of <span style="color:green">'+result.problem+'</span> at<span style="color:blue"> '+result.hamlet+'<span></h3></div>';
		contentStr+='<fieldset>';  		
		contentStr+='<legend style="font-family:arial,helvetica,clean,sans-serif;">Details of the Problem</legend>';
		contentStr+='<table id="probDetailsTable">';
		contentStr+='<tr><th>Problem</th>';		
		contentStr+='<th>Description</th>';
		contentStr+='<th>IdentifiedDate</th></tr>';
		contentStr+='<tr><td>'+result.problem+'</td>';
		contentStr+='<td>'+result.description+'</td>';
		contentStr+='<td>'+result.existingFrom+'</td></tr></table>';
		contentStr+='</fieldset>';

		contentStr+='<fieldset>';
		contentStr+='<legend style="font-family:arial,helvetica,clean,sans-serif;">Complained Person</legend>';		
		contentStr+='<table id="postedPersonTable">';
		contentStr+='<tr><th>Name</th>';
		contentStr+='<th>Phone </th>';	
		contentStr+='<th>Address  </th>';
		contentStr+='<th>Email</th></tr>';		
		contentStr+='<tr><td>'+result.postedPersonName+'</td>';
		contentStr+='<td>'+result.phone+'</td>';
		contentStr+='<td>'+result.address+'</td>';					
		contentStr+='<td>'+result.email+'</td></tr></table>';			
		contentStr+='</fieldset>';

		if(result.isAssigned){
			contentStr+='<fieldset>';
			contentStr+='<legend style="font-family:arial,helvetica,clean,sans-serif;">Assigned Department</legend>';		
			contentStr+='<table id="postedPersonTable">';
			contentStr+='<tr><th>Department </th>';
			contentStr+='<th>Concerned Person</th>';					
			contentStr+='<th>Designation </th>';	
			contentStr+='<th>ContactNumber </th>';
			contentStr+='<th>Assigned Date </th>';
			contentStr+='<tr><td>'+result.department+'</td>';
			contentStr+='<td>'+result.departmentConcernedPersonName+'</td>';			
			contentStr+='<td>'+result.designation+'</td>';				
			contentStr+='<td>'+result.departmentConcernedPersonPhoneNumber+'</td>';
			contentStr+='<td>'+result.updatedDate+'</td></tr></table>';
			contentStr+='</fieldset>';	
		}
		
		contentStr += '<div id="dataTable"></div>';

		var myPanel = new YAHOO.widget.Dialog("moreDetailsPanelDiv", {
			width : "620px",
			fixedcenter : false,
			visible : true,
			constraintoviewport : true,
			iframe : true,
			modal : true,
			hideaftersubmit : true,
			close : true
		});
		myPanel.setHeader("Problems Details");
		myPanel.setBody(contentStr);
		myPanel.render();
		buildProblemHistoryDataTable("dataTable", probsArr);

}