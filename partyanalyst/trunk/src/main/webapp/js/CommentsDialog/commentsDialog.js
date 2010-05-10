var addCommentsDialog;

function showCommentsDialog(id,candidateName,category, rank,constituencyId,constituencyName,partyName,task,status)
{	
	var elmt = document.getElementById('commentsDialogDiv');
	var divChild = document.createElement('div');
	divChild.setAttribute('id','addCommentDiv');
	var contentStr='';
	contentStr+='<div class="hd" align="left">Add/View Your Comments</div>';
	contentStr+='<div class="bd" align="left">';
	contentStr+='<FIELDSET align="center">';
	contentStr+='<LEGEND><B>Comments Details</B></LEGEND>';
	contentStr+='<div id="candidateInfo" align="left"></div>';
	contentStr+='<div id="previousComments" style="padding:10px;"></div>';
	contentStr+='</FIELDSET>';	
	contentStr+='<div id="commentsDetailsDivBody">';
	contentStr+='<FIELDSET align="center">';
	contentStr+='<LEGEND><B>Add New Comment</B></LEGEND>';
	contentStr+='<DIV id="alertMessage" style="padding:10px;">Fields marked with * are Mandatory</DIV>';
	contentStr+='<DIV>';
	contentStr+='<TABLE width="100%" class="commentsInputTable" border="1" cellspacing="0" cellpadding="0">';
	if(rank != 'null' && category == 'candidate')
	{
		contentStr+='<TR>';
		contentStr+='<TD align="left" class="commentsInputTd">Reasons*</TD>';	
		contentStr+='<TD class="commentsInputTd" align="left"><SELECT style="width:300px;" id="commentsClassificaitonSelectBox"  name="selectBox" style="display:block;">';
		contentStr+='<OPTION id="0" >Select Reason</OPTION>';
		contentStr+='</SELECT></TD>';
		contentStr+='</TR>';
		getCommentsClassifications(rank);
		showExistingComments(id,candidateName,category,constituencyId,constituencyName,partyName);
	}	
	contentStr+='<TR>';
	contentStr+='<TD align="left" valign="top" class="commentsInputTd">Comment*</TD>';	
	contentStr+='<TD class="commentsInputTd" valign="top" align="left"><TEXTAREA style="width:300px;" id="commentText" name="commentText"></TEXTAREA></TD>';
	contentStr+='</TR>';
	contentStr+='<TR>';
	contentStr+='<TD align="left" class="commentsInputTd" valign="top">Posted By*</TD>';	
	contentStr+='<TD class="commentsInputTd" valign="top" align="left"><input type="text" style="width:300px;" id="commentPostedByText" name="commentPostedByText"/></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';
	contentStr+='</DIV>';
	contentStr+='</FIELDSET>';	
	contentStr+='<DIV style="text-align:right;"><INPUT type="button" class="button" id="addCommentsButton" style="width:50px;" onclick="handleAddCommentsSubmit('+id+',\''+category+'\','+constituencyId+')" value="Save"/>';
	contentStr+='<INPUT type="button" id="addCommentsButton" style="width:50px;" class="button" onclick="handleAddCommentsCancel(\''+task+'\',\''+status+'\')" value="Exit"/></DIV>';
	contentStr+='</div>';
	contentStr+='</div>';
	divChild.innerHTML=contentStr;
	elmt.appendChild(divChild);
	
	if(addCommentsDialog)
		addCommentsDialog.destroy();
	addCommentsDialog = new YAHOO.widget.Dialog("addCommentDiv",
			{ 
			  width : "800px", 
              fixedcenter : false, 
              visible : true,  
              constraintoviewport : true, 
			  iframe :true,
			  modal :true,
			  hideaftersubmit:true,
			  close:true,
			  x:400,
			  y:400,				  
			  buttons : []
             } ); 
	addCommentsDialog.render();
	//setFocus();
	var commentsEl = document.getElementById("commentText").focus();	
	
}
function buildCommentsClassificationsOptions(results)
{
	
	var commentClassifyEl = document.getElementById("commentsClassificaitonSelectBox");
	for(var i in results)
	{
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
		try
			{
			commentClassifyEl.add(opElmt,null); // standards compliant
			}
		catch(ex)
			{
			commentClassifyEl.add(opElmt); // IE only
			}
	}
}

function showPreviousComments(results,jsObj)
{
	var previousComments = results.candidateCommentsVO;
	var previousCommentsEl = document.getElementById("previousComments");
	var candidateInfoEl = document.getElementById("candidateInfo");
	var party = jsObj.partyName;
	var constituencyName = jsObj.constituencyName;
	var candidateName = jsObj.candidateName;
	var commentsData = new Array();	
	var year = jsObj.year;
	var contentStr = '';	
		contentStr+='<TABLE width="100%" class="commentsInputTable">';
		contentStr+='<TR>';
		contentStr+='<TD style="width:20%;" class="commentsInputTd"><B>Candidate:</B></TD>';
		contentStr+='<TD style="width:30%;" class="commentsInputTd">'+candidateName+'</TD>';
		contentStr+='<TD style="width:20%;" class="commentsInputTd"><B>Election:</B></TD>';
		contentStr+='<TD style="width:30%;" class="commentsInputTd">'+electionType+''+year+'</TD>';		
		contentStr+='</TR>';
		contentStr+='<TR>';
		contentStr+='<TD style="width:20%;" class="commentsInputTd"><B>Party:</B></TD>';
		contentStr+='<TD style="width:30%;" class="commentsInputTd">'+party+'</TD>';
		contentStr+='<TD style="width:20%;" class="commentsInputTd"><B>Constituency:</B></TD>';
		contentStr+='<TD style="width:30%;" class="commentsInputTd">'+constituencyName+'</TD>';		
		contentStr+='</TR>';
		contentStr+='</TABLE>';
		candidateInfoEl.innerHTML = contentStr;		
		if(previousComments != null)
		{	
			
			for(var i in previousComments)
			{
				var commentObj = {
						comment: previousComments[i].commentDesc,
						classification: previousComments[i].commentCategory,
						commentedBy: previousComments[i].commentedBy, 
						date: previousComments[i].commentedOn							
						};
				commentsData.push(commentObj);					
			}			
			buildPreviousCommentsDataTable(commentsData);
		} else 
			{
			previousCommentsEl.innerHTML="No Previous Comments";
			}	
}

function buildPreviousCommentsDataTable(data)
{
	//previousComments
	var previousCommentsColumnDefs = [
	               								{key: "comment", label: "Comment", sortable:true},	
	               								{key: "classification", label: "Classification", sortable:true},		
	               								{key: "commentedBy", label: "CommentedBy", sortable:true},	
	               								{key: "date", label: "Date", sortable:true}	               								             		              	 	 		              	 	 				              	 	 		              	 	 			              	 	 	
	               		              	 	    ];                	 	    

	               		var previousCommentsDataSource = new YAHOO.util.DataSource(data); 
	               		previousCommentsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	               		previousCommentsDataSource.responseSchema = {
	                              fields: [ "comment", "classification", "commentedBy", "date"]     
	               		};
	               		var myConfigs = { 
	               			    paginator : new YAHOO.widget.Paginator({ 
	               				rowsPerPage    : 5	               							        
	               			    }),
	               			    caption:"Previous Comments" 
	               				};
	               		
	               		previousCommentsDataTable = new YAHOO.widget.DataTable("previousComments", previousCommentsColumnDefs, previousCommentsDataSource,myConfigs);

	               		return { 
	        	            oDS: previousCommentsDataSource, 
	        	            oDT: previousCommentsDataTable
	               	 };		            	
	               	
}
function updatePreviousCommentsDataTable(results)
{
	var dtArray = new Array();
	var commentVal = document.getElementById("commentText"); 
	var postedByVal = document.getElementById("commentPostedByText"); 
	var commentCategoryEl = document.getElementById("commentsClassificaitonSelectBox");
	var previousCommentsEl = document.getElementById("previousComments");
		 
	
	if(previousCommentsEl.innerHTML == 'No Previous Comments')
	{
		var newCommentDataObj=
		{		
				comment: results.candidateCommentsSaved.commentDesc,
				classification: results.candidateCommentsSaved.commentCategory,
				commentedBy: results.candidateCommentsSaved.commentedBy, 
				date: results.candidateCommentsSaved.commentedOn  		
		};

		dtArray.push(newCommentDataObj);
		buildPreviousCommentsDataTable(dtArray);		
		
	} else
	{
		var newCommentDataObj=
		{		
				comment: results.candidateCommentsSaved.commentDesc,
				classification: results.candidateCommentsSaved.commentCategory,
				commentedBy: results.candidateCommentsSaved.commentedBy, 
				date: results.candidateCommentsSaved.commentedOn  		
		};
		
		previousCommentsDataTable.addRow(newCommentDataObj,0);
	}
	commentVal.value='';
	postedByVal.value='';
	commentCategoryEl.selectedIndex='0';		
}



