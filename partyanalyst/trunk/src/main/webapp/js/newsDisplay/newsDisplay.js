
var previousSelectedColumn = "categoryType";
var selectedColumn = "categoryType";
var previousDirection = "asc";
var selectedDirection = "asc";
var modifiedInd = false;
var totalRecords = 1000;
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
function buildVotersByLocCustomWardDataTable1(task,queryType,fileType,sourceId,languegeId,categoryId,newsImportanceId,locationScope,location,title,fromDate,toDate)
{
	
	$("#searchAjaxImgSpan").css('display','none');
	YAHOO.widget.DataTable.news = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var title = oRecord.getData("fileTitle1");
	var fileId = oRecord.getData("fileId");


	//BY SAMBA START
	var gallaryId = oRecord.getData("contentId");
	//elLiner.innerHTML ="<a href='javascript:{}' onclick='showNews(\""+fileId+"\")'>"+title+"</a>";
	elLiner.innerHTML ="<a href='javascript:{}' onclick='getVideoDetails(\""+gallaryId+"\")'>"+title+"</a>";

	//elLiner.innerHTML ="<a href='javascript:{}' onclick='showNews(\""+fileId+"\")'>"+title+"</a>";

		//BY SAMBA END

  };
 YAHOO.widget.DataTable.edit = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var fileId= oRecord.getData("fileId");


	var str='';
	//str+="<a style='float:left;' title='edit this news' href='javascript:{}' onclick='editNewsDetails("+fileId+")'><img style='text-decoration: none; border: 0px none;' src='images/icons/edit.png'></a>";

	str+="<a style='float:left;' title='edit this news' href='javascript:{}' onclick='editNewsDetails("+fileId+")'><i class='icon-pencil'></i></a>";

	//str+="<a style='float:right;' title='delete this news'  href='javascript:{}' onclick='updateDeleteNews(\"Delete\","+fileId+");'><img style='text-decoration: none; border: 0px none;' src='images/icons/delete.png'></a>";

   str+="<a style='float:right;' title='delete this news'  href='javascript:{}' onclick='updateDeleteNews(\"Delete\","+fileId+");'><i class='icon-remove-sign'></i></a>";


	elLiner.innerHTML =str;
		
  };
  /*YAHOO.widget.DataTable.delet = function(elLiner, oRecord, oColumn, oData) 
  {
	var fileId= oRecord.getData("fileId");
	elLiner.innerHTML ="<a href='javascript:{}' onclick='updateDeleteNews(\"Delete\","+fileId+");'><img style='text-decoration: none; border: 0px none;' src='images/icons/delete.png'></a>";
		
  };*/

   YAHOO.widget.DataTable.visibilty = function(elLiner, oRecord, oColumn, oData) 
  {
	var visibility= oRecord.getData("visibility");
	var contentId= oRecord.getData("contentId");
    var str='';
	if(visibility == "false"){
	  str+="<div id='visibilityDiv"+contentId+"'><a href='javaScript:{updateVisibilityToPrivate("+contentId+")}' title='make this news as private'><i class='icon-bullhorn'></i></a><input type='hidden' name='visibility' id=visibility"+contentId+"/ value='public'></input></div>";

	  str+="<div id='nonVisibilityDiv"+contentId+"' style='display:none;'><a href='javaScript:{updateVisibilityToPublic("+contentId+")}' title='make this news as public'><i class='icon-lock'></i></a><input type='hidden' name='visibility' id=visibility"+contentId+" value='private'></input></div>";
	}
	   
	//elLiner.innerHTML ="";
	else if(visibility == "true"){

		 str+="<div id='visibilityDiv"+contentId+"'  style='display:none;'><a href='javaScript:{updateVisibilityToPrivate("+contentId+")}' title='make this news as private'><i class='icon-bullhorn'></i></a><input type='hidden' name='visibility' id=visibility"+contentId+"/ value='public'></input></div>";

	     str+="<div id='nonVisibilityDiv"+contentId+"'><a href='javaScript:{updateVisibilityToPublic("+contentId+")}' title='make this news as public'><i class='icon-lock'></i></a><input type='hidden' name='visibility' id=visibility"+contentId+" value='private'></input></div>";
	
	}

	elLiner.innerHTML = str;
	  

		
  };

  YAHOO.widget.DataTable.flagSet = function(elLiner, oRecord, oColumn, oData) 
  {
	var flagSet= oRecord.getData("flagSet");
	var contentId= oRecord.getData("contentId");
    var str='';

	if(flagSet == "true"){

		str+="<div id='flagSetDiv"+contentId+"'><a href='javaScript:{removeFlagInTable("+contentId+")}' title='unflag this news'><i class='icon-flag'></i></a><input type='hidden' id='flag"+contentId+"' value='flagged'></input></div>";

		str+="<div id='unFlagSetDiv"+contentId+"' style='display:none;'><a href='javaScript:{saveFlagInTable("+contentId+")}' title='flag this news' class='unflagClass'><i class='icon-flag'></i></a><input type='hidden' id='flag"+contentId+"' value='flagged'></input></div>";
	
	
	}
	  
	// elLiner.innerHTML ="";
	else if(flagSet == "false"){

		str+="<div id='flagSetDiv"+contentId+"'  style='display:none;'><a href='javaScript:{removeFlagInTable("+contentId+")}' title='unflag this news'><i class='icon-flag'></i></a><input type='hidden' id='flag"+contentId+"' value='flagged'></input></div>";

		str+="<div id='unFlagSetDiv"+contentId+"'><a href='javaScript:{saveFlagInTable("+contentId+")}' title='flag this news' class='unflagClass'><i class='icon-flag'></i></a><input type='hidden' id='flag"+contentId+"' value='flagged'></input></div>";
	
	
	}

	 elLiner.innerHTML = str;
		
  };

  YAHOO.widget.DataTable.notes = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oData;
	var notesExist= oRecord.getData("notesExist");

	if(notesExist == "true")
	  elLiner.innerHTML ="<div style='text-align:center;'><a href='javaScript:{}' ><i class='icon-edit'></i></a></div>";
	else
	  elLiner.innerHTML ="<div style='text-align:center;'><a href='javaScript:{}' class='unflagClass'><i class='icon-edit'></i></a></div>";

		
  };
	
var str='';
var votersByLocBoothColumnDefs = [
{key:"categoryType", label: '<span onClick="updateColumn(\'categoryType\')">NEWS CATEGORY<span>',sortable: true, resizeable:true},
{key:"gallaryName", label: '<span onClick="updateColumn(\'gallaryName\')">GALLERY<span>',sortable: true, resizeable:true},
{key:"source", label: '<span onClick="updateColumn(\'source\')">SOURCE<span>',sortable: false, resizeable:true},
{key:"fileTitle1", label: '<span onClick="updateColumn(\'fileTitle1\')">TITLE<span>',formatter:YAHOO.widget.DataTable.news, sortable: true, resizeable:true},
{key:"description", label:'<span onClick="updateColumn(\'description\')">DESCRIPTIONS<span>',sortable: true, resizeable:true},
{key:"locationScopeValue", label:'<span onClick="updateColumn(\'locationScopeValue\')">IMPACT AREA<span>',sortable: true, resizeable:true},
{key:"locationValue", label:'<span onClick="updateColumn(\'locationValue\')">AREA NAME<span>',sortable: false, resizeable:true},
{key:"fileDate", label: '<span onClick="updateColumn(\'fileDate\')">NEWS DATE<span>',sortable: true, resizeable:true},

{key:"visibility", label: "<i class=' icon-eye-open'></i>",formatter:YAHOO.widget.DataTable.visibilty, sortable: false},
{key:"flagSet", label: "<i class='icon-flag'></i>", sortable: false,formatter:YAHOO.widget.DataTable.flagSet},
{key:"notesExist", label: "<i class='icon-edit'></i>",formatter:YAHOO.widget.DataTable.notes, sortable: false},
{key:"update", label: "",formatter:YAHOO.widget.DataTable.edit}
/*{key:"", label: "a", width:60,formatter:YAHOO.widget.DataTable.Type},

{key:"",label:"b",sortable:true,width:50},
{key:"", label: "c", formatter:YAHOO.widget.DataTable.ActionLink}
{key:"",label:"",sortable:true,width:50},*/
];

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getNewsToDisplayAction1.action?queryType="+queryType+"&fileType="+fileType+"&sourceId="+sourceId+"&languegeId="+languegeId+"&categoryId="+categoryId+"&newsImportanceId="+newsImportanceId+"&locationScope="+locationScope+"&location="+location+"&fromDate="+fromDate+"&toDate="+toDate+"&title"+title+"&timeST="+timeST+"&task="+task+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "fileVOList",
fields: ["categoryType", "gallaryName", "source", "fileTitle1","description","locationScopeValue","locationValue","fileDate","contentId","flagSet","visibility","notesExist","fileId","fileDateAsString","categoryId","locationScope","newsImportanceId","keywords","fileVOList","location","locationVal","fileGallaryId"],

metaFields: {
totalRecords: "fileVOList[0].count" // Access to value in the server response
},
};



	var myConfigs = {
	initialRequest: "sort=categoryType&dir=asc&startIndex=0&results=100", // Initial request for first page of data

	initialRequest: "sort="+selectedColumn+"&dir="+selectedDirection+"&startIndex=0&results=100", // Initial request for first page of data
	dynamicData: true, // Enables dynamic server-driven data
	   paginator : new YAHOO.widget.Paginator({ 
					rowsPerPage    : 100 
					})  // Enables pagination
	};
    if(modifiedInd){

		 var stindx = 0;
		 try{
		  stindx =  (parseInt($.trim($('.yui-pg-current-page').html()))-1)*100;
		  myConfigs["paginator"] = new YAHOO.widget.Paginator({ 
						rowsPerPage    : 100 ,
						initialPage:$('.yui-pg-current-page').html(),
						totalRecords:totalRecords
						}) 
		  }catch(e){}
		 myConfigs["initialRequest"] ="sort="+selectedColumn+"&dir="+selectedDirection+"&startIndex="+stindx+"&results=100&initialPage ="+$('.yui-pg-current-page').html();
		}


var votersByLocBoothDataTable = new YAHOO.widget.DataTable("showNews",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);


votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
pResults = oResponse.resultsCount;
oPayload.totalRecords = oResponse.meta.totalRecords;
totalRecords = oResponse.meta.totalRecords;

newsDetails = oResponse.results;

   if ($('#searchBtn').length > 0)
			$('#searchBtn').attr('disabled',false);

return oPayload;
}

return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};
}

function updateColumn(columnName)
{	
	selectedColumn = columnName;

	if(previousSelectedColumn == selectedColumn)
	{
		if(previousDirection == "asc")
		{
			selectedDirection = "desc";
		}else if(previousDirection == "desc")
		{
            selectedDirection = "asc";
		}

	}else
	{
        selectedDirection = "asc";
	}

	previousSelectedColumn = columnName;
}
