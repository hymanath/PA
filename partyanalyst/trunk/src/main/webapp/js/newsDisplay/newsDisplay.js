
function getFlagStatus(contentId){

	
	var jsObj=
	      {
		    contentId:contentId,
				task :"checkForFlag"
           }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "checkForFlag.action?"+rparam;						
      callAjaxForNotes(jsObj,url);

}
function getNotes(contentId){
	//alert(contentId);

	var jsObj=
	      {
		    contentId:contentId,
				task :"getNotes"
           }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "getContentNotesByContentId.action?"+rparam;						
      callAjaxForNotes(jsObj,url);

}

function saveNotes(contentId){
	//alert(contentId);

	var notesText = $('#notes'+contentId).val();

	if(notesText.trim() == "")
		return false;


	var jsObj=
	      {
		    contentId:contentId,
			comment:$('#notes'+contentId).val(),
				task :"saveNotes"
           }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "saveContentNotesByContentId.action?"+rparam;						
      callAjaxForNotes(jsObj,url);

}

function deleteNotes(contentNotesId){

	var jsObj=
	      {
		    conentNotesId:contentNotesId,
				task :"deleteNotes"
           }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "removeContentNotes.action?"+rparam;						
      callAjaxForNotes(jsObj,url);

}


function callAjaxForNotes(jsObj,url){var myResults;	
  var callback = {			
    success : function( o ) {
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);	


			if(jsObj.task == "getNotes")
				buildNotes(jsObj ,myResults);
			else if (jsObj.task == "saveNotes"){


			 var str='';

			 str+='<div id="contentNotes'+myResults+'"  style="text-align:left;width:700px;background-color:#fff;border:1px solid #c3c3c3;padding:4px;margin:4px;border-radius:3px;"><span style="margin-right:20px;">'+jsObj.comment+"</span><a href='javaScript:{deleteContentNotes("+myResults+")}' style='float:right;margin-left:10px;' title='delete this news'><i class='icon-remove-sign'></a></div>";

			   //alert("Your message saved successfully");
              // $('#contentNotesDiv'+jsObj.contentId).append(str);

			  $('#contentCommentsDiv').append(str);
			  $('#notes'+jsObj.contentId).val('');
			}
			else if(jsObj.task == "deleteNotes"){
				$('#contentNotes'+jsObj.conentNotesId).remove();
			}
			else if(jsObj.task == "saveFlag"){
				//alert('Flag Saved Successfully');
				$('#unFlagDiv'+jsObj.contentId).css('display','none');
				$('#flagDiv'+jsObj.contentId).css('display','block');

				 $('#unFlagSetDiv'+jsObj.contentId).css('display','none');
				 $('#flagSetDiv'+jsObj.contentId).css('display','block');
				 var flagCount = $('#totalFlagNewsCount').val();
				 var newCount = parseInt(flagCount) +1;
				  $('#totalFlagNewsCount').val(newCount);
			}
			else if(jsObj.task == "checkForFlag")
				if(myResults =="true")
  				 $('#flagDiv'+jsObj.contentId).css('display','block');
			   else
				  $('#unFlagDiv'+jsObj.contentId).css('display','block');
		   else if(jsObj.task == "removeFlag"){

			  // alert('Flag Removed Successfully');
				$('#flagDiv'+jsObj.contentId).css('display','none');

				//console.log('#unflagDiv'+jsObj.contentId);
				$('#unFlagDiv'+jsObj.contentId).css('display','block');

				 $('#unFlagSetDiv'+jsObj.contentId).css('display','block');
				 $('#flagSetDiv'+jsObj.contentId).css('display','none');
				   var flagCount = $('#totalFlagNewsCount').val();
				  var newCount = parseInt(flagCount)-1;
                 $('#totalFlagNewsCount').val(newCount);

		   }else if(jsObj.task == "saveFlagInTable"){
                $('#unFlagSetDiv'+jsObj.contentId).css('display','none');
				 $('#flagSetDiv'+jsObj.contentId).css('display','block');
				  var flagCount = $('#totalFlagNewsCount').val();
				  var newCount = parseInt(flagCount)+1;
                 $('#totalFlagNewsCount').val(newCount);
				 
		   }else if(jsObj.task == "removeFlagInTable"){
			   $('#unFlagSetDiv'+jsObj.contentId).css('display','block');
				 $('#flagSetDiv'+jsObj.contentId).css('display','none');
				  var flagCount = $('#totalFlagNewsCount').val();
				  var newCount = parseInt(flagCount)-1;
                 $('#totalFlagNewsCount').val(newCount);

		   }else if(jsObj.task == "updateVisibility"){


            if(jsObj.place == "table"){

			   if(jsObj.visibility  == "false"){

				  // alert('changed to public');

				   $('#nonVisibilityDiv'+jsObj.contentId).css('display','none');
   				   $('#visibilityDiv'+jsObj.contentId).css('display','block');
			   }
			   else{
				  // alert('changed to private');

				    $('#nonVisibilityDiv'+jsObj.contentId).css('display','block');
   				    $('#visibilityDiv'+jsObj.contentId).css('display','none');
			   }
			}else{

				 if(jsObj.visibility  == "false"){

					 $('#private'+jsObj.contentId).css('display','none');
 					 $('#public'+jsObj.contentId).css('display','block');
					 $('#nonVisibilityDiv'+jsObj.contentId).css('display','none');
   				     $('#visibilityDiv'+jsObj.contentId).css('display','block');

				 }else{

					  $('#private'+jsObj.contentId).css('display','block');
 					  $('#public'+jsObj.contentId).css('display','none');
					  $('#nonVisibilityDiv'+jsObj.contentId).css('display','block')
   				      $('#visibilityDiv'+jsObj.contentId).css('display','none')

				 }
			}
		   }else if(jsObj.task == "visibilityStatus"){
               if(myResults == "Public")
				   $('#public'+jsObj.contentId).css('display','block');
			   else
				   $('#private'+jsObj.contentId).css('display','block');
		   }
			
			}catch (e) {   		
		   	//alert("Invalid JSON result" + e);   
		}  
    },
    scope : this,
    failure : function( o ) {
     			//alert( "Failed to load result" + o.status + " " + o.statusText);
              }
    };

YAHOO.util.Connect.asyncRequest('GET', url, callback);

}

function buildNotes(jsObj , results){

  var str='';

    str+='<div id="contentCommentsDiv">';
	for(var i in results){

		str+='<div id="contentNotes'+results[i].commentId+'"  style="text-align:left;width:700px;background-color:#fff;border:1px solid  #c3c3c3;padding:4px;margin:4px;border-radius:3px;"><span style="margin-left:20px;">'+results[i].comment+"</span><a href='javaScript:{deleteContentNotes("+results[i].commentId+")}' style='float:right;margin-left:10px;' title='delete this news'><i class='icon-remove-sign'></i></a></div>";
	}
	str+='</div>';

    str+='<div>';
	//str+='<div style="width:700px;"><span style="float:left;">Enter Notes:</span><textarea id="notes'+jsObj.contentId+'" style="width:700px;height:70px;margin:3px;"></textarea></div>';

	str+='<div><textarea placeholder="Enter your notes" id="notes'+jsObj.contentId+'" style="width:700px;height:70px;margin:3px;"></textarea></div>';

	str+='<div> <a  style="float:right;margin-right:105px;"href="javaScript:{saveContentNotes('+jsObj.contentId+')}" class="btn btn-primary" >Save Notes</a></div>';

	str+='</div>';

	$('#contentNotesDiv'+jsObj.contentId).html(str);

}

function deleteContentNotes(contentNotesId){

	if (confirm('Are you sure to delete?')) {
       deleteNotes(contentNotesId);
     } 

}

function saveContentNotes(contentId){

	saveNotes(contentId);
	//alert($('#notes'+contentId).val());
}

function saveFlag(contentId){

	var jsObj=
	      {
		    contentId:contentId,
				task :"saveFlag"
           }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "addFlagToANews.action?"+rparam;						
      callAjaxForNotes(jsObj,url);
}

function removeFlag(contentId){

	var jsObj=
	      {
		    contentId:contentId,
				task :"removeFlag"
           }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "removeFlagForNews.action?"+rparam;						
      callAjaxForNotes(jsObj,url);

}

function saveFlagInTable(contentId){



	var jsObj=
	      {
		    contentId:contentId,
				task :"saveFlagInTable"
           }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "addFlagToANews.action?"+rparam;						
      callAjaxForNotes(jsObj,url);

}

function removeFlagInTable(contentId){

	var jsObj=
	      {
		    contentId:contentId,
				task :"removeFlagInTable"
           }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "removeFlagForNews.action?"+rparam;						
      callAjaxForNotes(jsObj,url);

}


function updateVisibilityToPublic(contentId){

	updateVisibility(contentId,"false",'table');

}

function updateVisibilityToPrivate(contentId){
 updateVisibility(contentId,"true",'table');
}

function updateVisibilityToPublic1(contentId){
	updateVisibility(contentId,"false",'dialog');
}

function updateVisibilityToPrivate1(contentId){
 updateVisibility(contentId,"true",'dialog');

}

function updateVisibility(contentId , visibility,place){

	var jsObj=
	      {
		    contentId:contentId,
			visibility:visibility,
			place :place,
			task :"updateVisibility"
           }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "updateVisibility.action?"+rparam;						
      callAjaxForNotes(jsObj,url);


}


function getVisibilityStatus(contentId){

		var jsObj=
	      {
		    contentId:contentId,
				task :"visibilityStatus"
           }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "checkForVisibilityStatus.action?"+rparam;						
      callAjaxForNotes(jsObj,url);

}


function setFlagVariable(value){

	if(value == "false"){
		$('#flagSet').css('display','none');
		$('#flagUnSet').css('display','block');
	}else{

		$('#flagSet').css('display','block');
		$('#flagUnSet').css('display','none');
	}

	$('#flagInd').val(value);
}