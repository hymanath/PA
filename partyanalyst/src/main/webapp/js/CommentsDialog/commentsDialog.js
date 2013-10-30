var addCommentsDialog;
//var previousCommentsDataTable;

function showCommentsDialog(id,candidateName,category, rank,constituencyId,constituencyName,partyName,task,status)
{	
	var elmt = document.getElementById('commentsDialogDiv');

	addCommentsDialog = $( "#commentsDialogDiv" ).dialog({
			title:"Add/View Your Reasons",
			autoOpen: true,
			show: "blind",
			width: 900,
			minHeight:400,
			modal: true,
			hide: "explode"
		});

	/*var divChild = document.createElement('div');
	divChild.setAttribute('id','addCommentDiv');*/
	var contentStr='';
	//contentStr+='<div class="hd" align="left">Add/View Your Reasons</div>';
	contentStr+='<div align="left">';
	contentStr+='<FIELDSET align="center">';
	contentStr+='<LEGEND><B>Previous Reasons</B></LEGEND>';
	contentStr+='<div id="candidateInfo" align="left"></div>';
	contentStr+='<div id="previousComments" style="padding:10px;"></div>';
	contentStr+='</FIELDSET>';	
	contentStr+='<div id="commentsDetailsDivBody">';
	contentStr+='<FIELDSET align="center">';
	contentStr+='<LEGEND><B>Add New Reason</B></LEGEND>';
	contentStr+='<DIV id="alertMessage" style="padding:10px;">Fields marked with * are Mandatory</DIV>';
	contentStr+='<DIV>';
	contentStr+='<TABLE width="100%" class="commentsInputTable" border="1" cellspacing="0" cellpadding="0">';
	if(rank != 'null' && category == 'candidate')
	{
		contentStr+='<TR>';
		contentStr+='<TD align="left" class="commentsInputTd">Reasons*</TD>';	
		contentStr+='<TD class="commentsInputTd" align="left" ><SELECT style="width:300px;" id="commentsClassificaitonSelectBox"  name="selectBox" style="display:block;">';
		contentStr+='<OPTION id="0" >Select Reason</OPTION>';
		contentStr+='</SELECT></TD>';
		contentStr+='</TR>';
		contentStr+='<TR>';
		contentStr += '<td class="commentsInputTd">Set Significance*</td>';
		contentStr += '<td class="commentsInputTd">';
		contentStr += '<div class="yui-skin-sam">';
		
		contentStr += '<div id="slider-bg" class="yui-h-slider" tabindex="-1" title="Slider"> ';
		contentStr += '    <div id="slider-thumb" class="yui-slider-thumb">';
		contentStr += '		<img src="http://yui.yahooapis.com/2.8.2r1/build/slider/assets/thumb-n.gif">';
		contentStr += '	</div> ';
		contentStr += '</div> ';
		contentStr += ' Significance : <span id="slider-value">0</span></p> ';
		contentStr += '</div>';
		contentStr += '</td>';
		contentStr+='</TR>';
		getCommentsClassifications(rank);
		showExistingComments(id,candidateName,category,constituencyId,constituencyName,partyName);
	}	
	contentStr+='<TR>';
	contentStr+='<TD align="left" valign="top" class="commentsInputTd">Description*</TD>';	
	contentStr+='<TD class="commentsInputTd" valign="top" align="left"><TEXTAREA style="width:300px;" id="commentText" name="commentText"></TEXTAREA></TD>';
	contentStr+='</TR>';
	contentStr+='<TR>';
	contentStr+='<TD align="left" class="commentsInputTd" valign="top">Posted By*</TD>';	
	contentStr+='<TD class="commentsInputTd" valign="top" align="left"><input type="text" style="width:300px;" id="commentPostedByText" name="commentPostedByText" value="'+loggedUser+'" readonly="readonly" on/></TD>';
	contentStr+='</TR>';
	contentStr+='</TABLE>';
	contentStr+='</DIV>';
	contentStr+='</FIELDSET>';	
	
	contentStr+='<DIV style="text-align:right;"><INPUT type="button" class="button" id="addCommentsButton" style="width:50px;" onclick="handleAddCommentsSubmit('+id+',\''+category+'\','+constituencyId+')" value="Post"/>';
	contentStr+='<span id="AjaxDiv" style="display:none;float:right;"><img alt="Processing Image" src="./images/icons/search.gif"></span>';
	contentStr+='<INPUT type="button" id="addCommentsButton" style="width:50px;" class="button" onclick="handleAddCommentsCancel(\''+task+'\',\''+status+'\')" value="Exit"/></DIV>';
	contentStr+='</div>';
	contentStr+='</div>';

	elmt.innerHTML = contentStr;
	/*divChild.innerHTML=contentStr;
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
	addCommentsDialog.render();*/
	//setFocus();
	
	
	var commentsEl = document.getElementById("commentText").focus();	
	sliderInit();
	
}

function sliderInit() 
{
	var slider = YAHOO.widget.Slider.getHorizSlider("slider-bg", "slider-thumb", 0, 100);
	slider.animate = true; 
	slider.subscribe("change", function(offsetFromStart) {
		
	 var valnode = document.getElementById("slider-value");
	 if(valnode)
		valnode.innerHTML = offsetFromStart;
	 });
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
			previousCommentsEl.innerHTML="No Previous Reasons";
			}	
}

function buildPreviousCommentsDataTable(data)
{
	//previousComments
	var previousCommentsColumnDefs = [
	               								{key: "classification", label: "Reason", sortable:true},
	               								{key: "comment", label: "Description", sortable:true},
	               								{key: "commentedBy", label: "Posted By", sortable:true},	
	               								{key: "date", label: "Date", sortable:true}	               								             		              	 	 		              	 	 				              	 	 		              	 	 			              	 	 	
	               		              	 	    ];                	 	    

	               		var previousCommentsDataSource = new YAHOO.util.DataSource(data); 
	               		previousCommentsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	               		previousCommentsDataSource.responseSchema = {
	                              fields: [ "comment", "classification", "commentedBy", "date"]     
	               		};
	               		if(data.length > 5)
	               		{
		               		var myConfigs = { 
		               			    paginator : new YAHOO.widget.Paginator({ 
		               				rowsPerPage    : 5	               							        
		               			    }) 
		               				};
	               		}	
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
		 
	
	if(previousCommentsEl.innerHTML == 'No Previous Reasons')
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





