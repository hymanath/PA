var addCommentsDialog;

function showCommentsDialog(id,candidateName,category, rank,constituencyId,constituencyName,partyName)
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
	contentStr+='<INPUT type="button" id="addCommentsButton" style="width:50px;" class="button" onclick="handleAddCommentsCancel()" value="Exit"/></DIV>';
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
	
	//showExistingComments('+id+',\''+category+'\','+constituencyId+');
}
