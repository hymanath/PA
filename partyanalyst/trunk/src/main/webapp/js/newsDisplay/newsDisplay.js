
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
				getFlagedNewsAndNotes("flagedCount");
                $('#unFlagSetDiv'+jsObj.contentId).css('display','none');
				 $('#flagSetDiv'+jsObj.contentId).css('display','block');
				  var flagCount = $('#totalFlagNewsCount').val();
				  var newCount = parseInt(flagCount)+1;
                 $('#totalFlagNewsCount').val(newCount);
				 
		   }else if(jsObj.task == "removeFlagInTable"){
				getFlagedNewsAndNotes("flagedCount");
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

	str+='</br><div> <a  style="float:right;margin-right:105px;"href="javaScript:{saveContentNotes('+jsObj.contentId+')}" class="btn btn-primary" >Save Notes</a></div></br></br>';

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


function getLocations1(id){

   if(id==0)
  {
   var val ='';
  val +='<table>';
  val +='  <tr><td></td>';
  val +='  </tr>';
  val +='</table>';
  document.getElementById("showScopeSubs").innerHTML = val;
    
  }
  else if(id==1)
  {
    var str ='';
  str +='<table>';
  str +='  <tr><td></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
  }
  else if(id==2)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">State<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDiv" name="locationValue" class="selectWidth" style="width: 222px; margin-left: -4px;"/></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForLocationScope();
  }
  else if(id==3)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">State<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDiv" class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">District<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDiv" class="selectWidth" name="locationValue"style="width: 222px; margin-left: -4px;"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForLocationScope();
  }
  else if(id==4)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">State<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDiv" class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="clearAllElmts(4,1);getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">District<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDiv" class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Assembly Constituency<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="constituencyDiv" name="locationValue"style="width: 222px; margin-left: -4px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForLocationScope();
  }
  else if(id==5 || id==7)
  {
   if(id==5)
   {
     areaType1 = "URBAN" ;
     areaType2 = "RURAL" ;
   }
   if(id==7)
   {
     areaType1 = "RURAL" ;
     areaType2 = "URBAN" ;
   }
   
    var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">State<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDiv"style="width: 222px; margin-left: -4px;"  class="selectWidth" onchange="clearAllElmts(5,1);getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">District<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDiv"style="width: 222px; margin-left: -4px;" class="selectWidth" onchange="clearAllElmts(5,2);getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Assembly Constituency<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="constituencyDiv"style="width: 222px; margin-left: -4px;" class="selectWidth" onchange="getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Mandal/ Municipality/ Corp/GMC<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="mandalDiv" name="locationValue"style="width: 222px; margin-left: -4px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForLocationScope();
  }
  else if(id==6 || id==8)
  {
   if(id==6)
   {
     areaType1 = "URBAN" ;
     areaType2 = "RURAL" ;
   }
   if(id==8)
   {
     areaType1 = "RURAL" ;
     areaType2 = "URBAN" ;
   }
    var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">State<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDiv"  class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="clearAllElmts(6,1);getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">District<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDiv"  class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="clearAllElmts(6,2);getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Assembly Constituency<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="constituencyDiv"  class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="clearAllElmts(6,3);getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Mandal/ Municipality/ Corp/GMC<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="mandalDiv"  class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="getAllDetails(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Village/Ward/Division<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="villageDiv" name="locationValue"style="width: 222px; margin-left: -4px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForLocationScope();
  }
  else if(id==9)
  {
     var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">State<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDiv" class="selectWidth"style="width: 222px; margin-left: -4px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,1);getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">District<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDiv" class="selectWidth"style="width: 222px; margin-left: -4px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,2);getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Assembly Constituency<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="constituencyDiv" class="selectWidth"style="width: 222px; margin-left: -4px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,3);getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Mandal/ Municipality/ Corp/GMC<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="mandalDiv" class="selectWidth"style="width: 222px; margin-left: -4px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="getAllDetails(this.options[this.selectedIndex].value,\'boothsInTehsilOrMunicipality\',\'\',document.getElementById(\'constituencyDiv\').options[document.getElementById(\'constituencyDiv\').selectedIndex].value)"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Village/Ward/Division<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="villageDiv" name="locationValue"style="width: 222px; margin-left: -4px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForLocationScope();
  }
}