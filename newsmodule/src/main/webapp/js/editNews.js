var timeST = new Date().getTime();

function deleteFile(fileTableId)
{
	var confirmFile = confirm('Do you want to delete file');
	if(confirmFile)
		$("#"+fileTableId+"").html('');

}
function getLanguage(fillOptionId)
{
	if(languagesObj == null)
	{
		var jsObj={
		fillOptionId :fillOptionId,
			time:timeST,
		  task:"getLanguage"
			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getEventsGallariesForUplaodAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	else
		{
			clearOptionsListForSelectElmtId(fillOptionId);
		   createOptionsForSelectElmtId(fillOptionId,languagesObj);
		}

}
function getSource(selectOptionId){

	if(sourceObj ==null)
	{
		var jsObj = {
			time : timeST,
			selectOptionId :selectOptionId,
			task : "getSource"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getEventsGallariesForUplaodAction.action?"+rparam;						
	callAjax(jsObj,url);
	}
	 else
		{
			clearOptionsListForSelectElmtId(selectOptionId);
		   createOptionsForSelectElmtId(selectOptionId,sourceObj);
		}
}


function callAjax(jsObj,url)
{
	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText); 
		  if (jsObj.task == "getCandidatesOfAParty")
		 {
			  	$('#candidateAjaxImg').hide();
			 buildPartyCandidates(myResults);
		 }else if (jsObj.task == "generateKeyForReport")
		 {
		   $('#'+jsObj.id).text(myResults);
		   $('#'+jsObj.id).show();
		 }
		 else if (jsObj.task == "getCandidatesListByPartyId")
		 {
		   $("#"+jsObj.type+"Img").css("display","none");
			 buildCandidatesForKeywords(myResults,jsObj.type);
		 }

		 else if(jsObj.task == "getPartyPhotoGallaryDetail")
			{
               buildCandidatePhotoGallary(myResults);
			}
		else if(jsObj.task == "partyGallariesForUplaod" && jsObj.updatePhoto== "UpdatePhoto")
			{
               clearOptionsListForSelectElmtId('gallaryPhotoSelectId');
			   createListData('gallaryPhotoSelectId',myResults);
			   $("#gallaryPhotoSelectId").prepend("<option value='0'>Select Gallery</option>");
			   document.getElementById('gallaryPhotoSelectId').value = 0;
			}else if(jsObj.task == "getCategory")
			{ 
               clearOptionsListForSelectElmtId('category');
			   createOptionsForSelectElmtId('category',myResults);
			   clearOptionsListForSelectElmtId('keywordCategory');
			   createOptionsForSelectElmtId('keywordCategory',myResults);
			   clearOptionsListForSelectElmtId('keywordCategory1');
			   createOptionsForSelectElmtId('keywordCategory1',myResults);
			}
			else if(jsObj.task == "partyVideoGallariesForUplaod")
			{
		
           showUploadVideoStatus(myResults);  
			}

			else if(jsObj.task == "partyVideoVisibility")
			{
				
			
				hideVedioVisibility(myResults);
			}
		else if(jsObj.task == "partyNewsVisibility")
			{
				hideNewsVisibility(myResults);
			}

			else if(jsObj.task == "partyPhotoVisibility")
			{
				hidePhotoVisibility(myResults);
			}
		else if(jsObj.task == "saveDiscription")
			{
           showDiscriptionStatus(myResults);  
			}	
		else if(jsObj.task == "deleteDiscription")
			{
           showDeleteStatus(myResults);  
			}	
        else if(jsObj.task == "deleteFilesAndPhotos")
			{
           showDeleteFilesAndPhotosStatus(myResults);  
			}	
        else if(jsObj.task == "deletePartyGallary")  
			{
           showDeleteGallary(myResults);  
			}
			
		else if(jsObj.task == "UpdatePartyGallary")  
			{
           updateGallaryDiv(myResults);  
			}		
		else if(jsObj.task == "UpdatePhotoGallary")  
			{
           updateGallaryStatus(myResults);  
			}	
		else if(jsObj.task == "updateFilesAndPhotos")  
			{
           updateFilesAndPhotosDiv(myResults,jsObj.fileId);  
			}		
		else if(jsObj.task == "updateProfileDiscription")
			{
           showDiscriptionUpdateStatus(myResults);  
			}		
		else if(jsObj.task == "getPhotosInAGallary")
			{
               showPhotosInAGallary(myResults);
			}
       else if(jsObj.task == "getCandidateNewsGallaryDetail")
			{
               buildCandidateGallary(myResults,"zero","news");
			}
		else if(jsObj.task == "getNewsInAGallary")
			{ 
               buildGallary(myResults,"zero","news");
			}
		else if(jsObj.task == "getDevelopmentsInAGallary")
			{ 
               buildGallary(myResults,"DevelopmentsGallaryDiv","developments");
			}
		else if(jsObj.task == "getCandidateDevelopmentGallaryDetail")
			{ 
               buildCandidateGallary(myResults,"DevelopmentsGallaryDiv","developments");
			}
		else if(jsObj.task == "searchNewsDetails")
		{ 
           buildGallary(myResults,"zero","newsSearch");
		}	
			else if(jsObj.task == "getScopesForNewSearch")
			{ 
			   clearOptionsListForSelectElmtId(jsObj.divId);
               buildResults(myResults,jsObj.divId);
			}
			else if(jsObj.task == "getElectionType")
			{ 
			   clearOptionsListForSelectElmtId("electionType");
               buildResultsForElectType(myResults);
			}			
			
			else if(jsObj.task == "createNewGallary" && jsObj.contentType =='News Gallary')
			{  
               showNewsGallaryCreateMsg(myResults);
			}
		
			else if(jsObj.task == "createNewGallary")  
			{ 
               showGallaryCreateMsg(myResults,jsObj.createOrUpdate);
			}
			
			else if(jsObj.task == "createVideoNewGallary")  
			{ 
               showVideoGallaryCreateMsg(myResults,jsObj.createOrUpdate);
			}
			else if(jsObj.task == "partyGallariesForUplaod" && jsObj.contentType=="News Gallary")
			{ 

               clearOptionsListForSelectElmtId(''+jsObj.type);
			   createOptionsForSelectElmtsId(''+jsObj.type,myResults);
			              $('#'+jsObj.type).multiselect();
						  $('#'+jsObj.type).multiselect({
							  noneSelectedText:"Select Category"});
								$('#'+jsObj.type).multiselect('refresh');
								$('#'+jsObj.type).multiselect('create');
								$('#'+jsObj.type).multiselect('create');
			}			
			else if(jsObj.task == "getSource")
			{
               sourceObj = myResults;
			   clearOptionsListForSelectElmtId(jsObj.selectOptionId);
			   createOptionsForSelectElmtId(jsObj.selectOptionId,sourceObj);
			}
			else if(jsObj.task == "getLanguage")
			{ 
               languagesObj = myResults;
               clearOptionsListForSelectElmtId(jsObj.fillOptionId);
			   createOptionsForSelectElmtId(jsObj.fillOptionId,languagesObj);
			}
			else if(jsObj.task == "getNewsImportance")
			{ 
				
               clearOptionsListForSelectElmtId('newsimportance');
			   createOptionsForSelectElmtId('newsimportance',myResults);
			}
			
			else if(jsObj.task == "partyGallariesForUplaod" && jsObj.contentType == "Video Gallary")
			{ 
               clearOptionsListForSelectElmtId('gallarySelectId');
			   createOptionsForSelectElmtId('gallarySelectId',myResults);
			   $("#gallarySelectId").prepend("<option value = '0'> Select Gallery</option>")
					document.getElementById('gallarySelectId').value = 0;
			}
			else if(jsObj.task == "partyGallariesForUplaod" && jsObj.contentType == "Photo Gallary")
			{ 
               clearOptionsListForSelectElmtId('gallaryPhotoSelectId');
			   createOptionsForSelectElmtId('gallaryPhotoSelectId',myResults);
			   $("#gallaryPhotoSelectId").prepend("<option value = '0'> Select Gallery</option>")
					document.getElementById('gallaryPhotoSelectId').value = 0;
			}


			else if(jsObj.task == "partyDescriptionUpdate")
			{ 

               showPartyDescription(myResults);
			}
			else if(jsObj.task == "updateIndividualPhotoDetails")
			{ 
               showPhotoUpdateDetails(myResults);
			}
			else if(jsObj.task == "getAssmblParlElecYears")
			{ 
               createListData('assmblParlElecYearsSelect',myResults);
			}
			else if(jsObj.task == "candiadteVideoGallariesForUplaod")
			{
				 showUploadVideoStatus(myResults);
				
			}else if(jsObj.task == 'getAllGallariesOfAParty')
			{
				buildGallaries('gallariesList' , myResults);
			}
			else if(jsObj.task == 'getAllTheNewsOfAGallary' || jsObj.task == 'getNewsTitlesForACandidate' || jsObj.task == 'getNewsForACandidateByCategoryId')
			{
				 clearOptionsListForSelectElmtId('candidateNewsList');
                 buildNewsOfAGallary('candidateNewsList' , myResults);
			}
			
			else if(jsObj.task == "getCandidateRelatedGallaries")
			{
			  clearOptionsListForSelectElmtId('gallaryList');
			  createOptionsForSelectElement('gallaryList',myResults);
			  
			}

			else if(jsObj.task == "getCandidateRelatedCategories")
			{
			  clearOptionsListForSelectElmtId('candidateCategoryId');
			  createOptionsForSelectElement('candidateCategoryId',myResults);
			}
			else if(jsObj.task == "getGallariesForSelectedCategory")
			{
				clearOptionsListForSelectElmtId('gallaryList');
			    createOptionsForSelectElement('gallaryList',myResults);

			}

			else if(jsObj.task == "getGalleryListForAParty")
			{
              clearOptionsListForSelectElmtId('assignNewsgallaryList');
			  createOptionsForSelectElement('assignNewsgallaryList',myResults);

			  clearOptionsListForSelectElmtId('responseNewsgallaryList');
			  createOptionsForSelectElement('responseNewsgallaryList',myResults);

			}

		   else if(jsObj.task == "getNewsByGalleryId")
		   {
			  if(jsObj.divId == "assignNewsgallaryList")
			  {
                clearOptionsListForSelectElmtId('newsTitlesSelectList');
			    buildNewsOfAGallary('newsTitlesSelectList',myResults);
			  }
              else
			  {
				clearOptionsListForSelectElmtId('responseNewsTitlesSelectList');
			    buildNewsOfAGallary('responseNewsTitlesSelectList',myResults); 
			  }
			  			  
			}
			else if(jsObj.task == "getCandidatesByPartyId")
			{
              clearOptionsListForSelectElmtId('candidatesLists');
			  createOptionsForSelectElement('candidatesLists',myResults);
			}
			else if(jsObj.task == "storeSource")
				{
					if(myResults.message=="exist"){
						$('#errorDiv').html('<span>Source Already Exist</span>');
						$('#errorDiv').css('color','red');
					}else{
						$('#errorDiv').html('<span>Created Successfully</span>');
						$('#errorDiv').css('color','green');
					}
				}
		else if(jsObj.task == "getLocationScope")
		{
		 showUserAccessLocationScopeList(myResults);
		}
	   else if(jsObj.task == "getKeywords")
			{
				buildKeyWords(myResults,jsObj);
			}
			else if(jsObj.task == "getGallaryMapedKeywords")
			{
				clearOptionsListForSelectElmtId('keywords');
			    createOptionsForSelectElement('keywords',myResults);

			}
			else if(jsObj.task == "getMainCategories")
			{
				clearOptionsListForSelectElmtId('mainCategory');
			    createOptionsForSelectElement('mainCategory',myResults);

			}
			else if(jsObj.task == "getCandidatesByPartyIds")
			{
			 buildCandidateList(myResults);
			   
			}
			else if(jsObj.task == "saveCandidate")
			 showCandidateSaveStatus(myResults,jsObj);

			else if(jsObj.task == "getBenefitList")
			 buildBenefitsList(myResults);
			 
			else if(jsObj.task == "getPartyList")
			 buildPartyList(myResults,jsObj);
			
			else if(jsObj.task == "getDesignationsList")
			 builddesignationsList(myResults,jsObj);
			 
			else if(jsObj.task == "createNewDesignation")
			{
				if(myResults.resultCode == 0)
				{
					$('#statusDivForDesignation').html('<b style="color:green">Designation created successfully</b>');
				}
				else
				{
					$('#statusDivForDesignation').html('<b style="color:red">Designation already exists</b>');
				}
			}
			else if(jsObj.task == "createNewParty")
			{
				if(myResults.resultCode == 0)
				{
					$('#statusForParty').html('<b style="color:green">Party created successfully</b>');
				}
				else
				{
					$('#statusForParty').html('<b style="color:red">Party already exists</b>');
				}
			}
			 else if(jsObj.task == "getTotalKeyWords")
			{ 
			
               clearOptionsListForSelectElmtId(''+jsObj.type);
			   createOptionsForSelectElmtId(''+jsObj.type,myResults);
			   $('#'+jsObj.type).multiselect();

  $('#'+jsObj.type).multiselect({
	  noneSelectedText:"Select Keyword"});
		$('#'+jsObj.type).multiselect('refresh');
		$('#'+jsObj.type).multiselect('create');
			}
		}
		catch(e)
		{   
		 //alert("Invalid JSON result" + e);   
		}  
	 },
	scope : this,
	failure : function( o )
	{
								//alert( "Failed to load result" + o.status + " " + o.statusText);
	}
  };

 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}


function createOptionsForSelectElmtsId(elmtId,optionsList){
var elmt = document.getElementById(elmtId);

	if( !elmt || optionsList == null)
		return;
	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;

		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}

	}
} 
function getPartyGallariesForUplaod(contentType,type)
{
	var partyId=872;
	var jsObj =
		{ 
            partyId : partyId,
			contentType : contentType,
			type:type,
		   	task : "partyGallariesForUplaod"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyGallariesForUplaodPhotoAction.action?"+rparam;
	callAjax(jsObj,url);
}
function getCandidateGallariesForUpdatePhoto()
{
var partyId=872;
	var jsObj =
		{ 
            partyId : partyId,
			contentType :'Photo Gallary',
			updatePhoto:'UpdatePhoto',
		   	task : "partyGallariesForUplaod"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyGallariesForUplaodPhotoAction.action?"+rparam;
	callAjax(jsObj,url);
}
	
	function enableButton(id)
{
	document.getElementById(id).disabled  = false;
}

function disableButton(id)
{
	//document.getElementById(id).disabled  = true;
}

function showUploadStatus(myResult)
{
	var result = (String)(myResult);
	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '';

	if(result.search('success') != -1)
	{
		clearUploadFileFields();
		str += '<font color="green"><b>Photo Uploaded Successfully.</b>';
	}
	else if(result.search('fail') != -1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	else
	{
		str += '<font color="red"><b>'+result+'</b>';
	}
	errorDivEle.innerHTML = str;
}
function removeAllUnwantedCharacters1(str)
{
	return str;
} 

function showNewsUploadStatus(myResult)
{

		
	var result = (String)(myResult);
	var errorDivEle = document.getElementById('uploadNewsFileErrorDiv1');
	var str = '';

	if(result.search('success') != -1)
	{
		
		str += '<font color="green"><b>News Uploaded Successfully.</b>';
		 $("#uploadNewsBtnId").css("background","#51A351");
		$('html, body').animate({
         scrollTop: $("#uploadNewsFileErrorDiv1").offset().top
     }, 2000);
		clearNewsUploadFileFields();
	}
	else if(result.search('fail') != -1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	else
	{
		str += '<font color="red"><b>'+result+'</b>';
	}
	errorDivEle.innerHTML = str;

	
}

function showNewsUploadStatus1(myResult)
{

	
	var result = (String)(myResult);
	var errorDivEle = document.getElementById('uploadNewsFileErrorDiv');
	var str = '';

	if(result.search('success') != -1)
	{
		addSource = 0;
		addFile = 0;
		who = 0;
		whome = 0;
		uploadNewsForPartyAndCandidate(null);		
		//getKeywordList();		
		setTimeout(showSuccessMsg,2000);
		if(editType == "addresponse"){
		   window.opener.getTotalNewsWithPagination();
		}else if(editType == "generatereport"){
		  window.opener.getNewsDetailsForNewsReportGeneration();
		}
	 
	}
	else if(result.search('fail') != -1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
		$("#uploadNewsFileErrorDiv").html(str);
	}
	else
	{
		str += '<font color="red"><b>'+result+'</b>';
		$("#uploadNewsFileErrorDiv").html(str);
	}
	//errorDivEle.innerHTML = str;

	
}

function showSuccessMsg()
{
$("#uploadNewsFileErrorDiv").html('');
 $("#uploadNewsFileErrorDiv").html('<font color="green"><b>News Updated Successfully.</b></font>');
 setTimeout("window.close();",500);
}

function clearNewsUploadFileFields1()
{

    $('#gallaryId').val(0);
	$('#newsfileTitle , #newsfileDescription , #keywords , #existingFromTextNews , #ImagenewsfileId').val('');
	$('#candidateList').find('option').remove();
	$('#uploadFilesDiv').html('');
    $("#newsDesc").val('');

   // newKeywordsInserting();

	 getScopes();
	 getSource("source");
	 getLanguage("language");
	 getNewsImportance();
	 getCategory();
	// getCandidatesofUser();
}


function newKeywordsInserting()
{
  //keywords
  var tempKeywordArray = new Array();
    if(keywordsArray != null && keywordsArray.length > 0)
	  for(var i in keywordsArray)
	   tempKeywordArray.push(keywordsArray[i].name);
	

	 $(".as-selections li").each(function(){
		var name = $.trim($(this).text()).substring(1);
        if(tempKeywordArray.indexOf(""+name+"") == -1)
		{
		   var obj = {value:0,name:""+name+""};
		   keywordsArray.push(obj);
		}

	 });
        
	data = {items:keywordsArray};

     $(".multiKeywordList").html('');
	 $(".multiKeywordList").html('<input type="text" name="keywordList1" id="keywordListId1"/>');

	 $("#keywordListId1").autoSuggest(data.items, {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});


}

function clearNewsUploadFileFields()
{
	$("#addMoreFilesDiv").html('');
	$("#otherProNewsDiv").html('');
	document.getElementById('newsfileTitle').value = '';
	document.getElementById('newsfileDescription').value = '';
	document.getElementById('source').value = '';
	document.getElementById('newsfileId').value = '';
	//document.getElementById('publicRadioId').checked = true;
	document.getElementById('existingFromTextNews').value = '';
	document.getElementById('ImagenewsfileId').value = '';
	document.getElementById('newsDescriptionId').value = '';
	getScopes();

	//newKeywordsInserting();
}
	
function clearUploadFileFields()
{
	$("#addMorePhotosDiv").html('');
	$("#otherProPhotoDiv").html('');
	document.getElementById('photofileTitleId').value = '';
	document.getElementById('photofileDescId').value = '';
	//document.getElementById('publicRadioId').checked = true;
	document.getElementById('photofileId').value = '';
	
	document.getElementById('existingFromTextPhoto').value = '';
}

function clearGallaryFields()
{
	
	document.getElementById('pGallaryNameId').value = '';
	document.getElementById('pGallaryDescId').value = '';
	//document.getElementById('publicRadioId').checked = true;
}
	
function formValidation()
{
	
		return false;
	
	
}
	
function buildUploadNewsForMultipleUsers()
{
	
   var tempPartyId = 872;
   var str ='';
	str+='<div id="content" style="width:650px;">';
	
	str += '<form name="uploadForm1" action="uploadFilesForMultipleCandidates.action" enctype="multipart/form-data"  method="post" id="uploadNewsForm">';
	str += '<h2 align="center">Upload A News</h2>';
	str += '<table class="aligncenter"><tr><td><div id="uploadNewsFileErrorDiv" /></td></tr></table>';

     str+='<div class="aligncenter" style="margin-left:-45px;">';
    
	 str+='<a href="javascript:void(0)" title="Click here to respond to news"  id="responseDiv" style="margin-left:586px;"><img id="" src="images/responseImage.png"/></a>';

	str+='<div id="responseContentDiv" style="display:none;border:1px solid #a6a66a;width:526px;margin:17px 0px 25px 94px">';
       str+='<div style="margin-left:72px;">';
	   	 	 str+='<div id="dateErrorMessage"></div><br>';
        str+='<div id="noNewsError"></div>';

	     str+='<span style="margin-right:30px;"><b>Start Date:<font class="requiredFont">*</font></b><input type="text" name="fromDate" class="inputClass" id="fromDateId" readonly="true"/></span>';
		 str+='<span><b>End Date:<font class="requiredFont">*</font></b><input type="text" name="toDate" readonly="true" class="inputClass" id="toDateId"/></span>';
	   str+='</div>';

       str+='<div><label style="float:left;margin-left:182px;"><input style="margin-top: 0px; margin-right: 4px;" type="radio" name="newsType" value="party" class="typeRadio">Party</input></label><label style="margin-left:249px;"><input type="radio" style="margin-top: 0px; margin-right: 4px;" checked="checked" name="newsType" value="candidate" class="typeRadio" selected>Candidate</input></label></div>'

	     str+='<div style="margin-left:70px;" id="candidatesDiv">';
	     str+='<b>Select Candidate :<font class="requiredFont">*</font></b><select id="candidatesList"></select>';
		 str+='</div>';

		 str+='<div id="partyNewsDiv" style="display:none;">';

		 str+='<div style="margin-left:70px;" id="partyDiv">';

	     str+='<b>Select Party :<font class="requiredFont">*</font></b><select id="partyList" style="margin-left:30px;"><option value="0">Select Party</option><option value="163">BJP<option value="265">CPI</option><option value="269">CPM</option><option value="362">INC</option><option value="990">MIM</option><option value="872">TDP</option><option value="886">TRS</option><option value="1117">YSRCP</option></select>';
		 str+='</div>';

          
		 str+='<div style="margin-left:70px;" id="gallariesDiv">';
	     str+='<b>Select Gallery :<font class="requiredFont">*</font></b><select id="gallariesList" style="margin-left:17px"></select>';
		 str+='</div>';

		 str+='</div>';

		 str +='<div id="categoryGallaryHideShowDiv" style="display:none;">';
		 str +='<label id="allLabelId"><input type="checkbox" value="ByGallery" id="gallaryAllCheckboxId" checked="true" onclick="getCandidateNews();"/>All</label>';
		 str +='<label id="byGallaryLabelId"><input type="checkbox" value="ByGallery" id="gallaryCheckboxId"/>By Gallery</label>';
		 str +='<label id="byCategoryLabelId"><input type="checkbox" value="ByCategory" id="categoryCheckboxId"/>By Category</label>';
		 str +='</div>';
                 
         str +='<div id="categoryShowHideDiv" style="display:none;">';
		 str +='<b>Select Category :<font class="requiredFont">*</font></b><select id="candidateCategoryId"></select>';
		 str +='<input type="checkbox" value="categoryGallaries" id="categoryGallary"/>Gallery'; 
         str +='</div>';

		 str +='<div id="gallaryShowHideDiv" style="display:none;"><b>Select Gallery :<font class="requiredFont">*</font></b><select id="gallaryList"></select></div>';

		
	     str+='<div style="text-align:center;">';
		 str+='<div style="clear:both;"><select id="candidateNewsList"  multiple="true" style="display:none;"></select></div>';

	     str+='<div style="display:none;" id="buttonsDiv"><input type="button" id="addFile" value="Add"/>';
		 str+='<input type="button" value="Remove" id="deleteFile"/>';
	   str+='</div>';

	   str+='<div>';
	    str+='<select id="respenseNewsList" name="responseFileIds" multiple ="true" style="display:none;"></select>';
	   str+='</div>';
	   str+='</div>';
	   
	str+='</div>';
	str += '<table class="aligncenter" style="margin-left:123px;left:50%;">';
	
	str +='<tr>';
	str += '       <td class="tdWidth1">Keyword : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd multiKeywordList"><input type="text" name="keywordList1" id="keywordListId1"/></td>';
	str +='</tr>';

	str +='<tr>';
	str += '       <td class="tdWidth1"></td>';
	str += '  <td class="selectWidthPadd"><div style="margin-top: 2px; margin-bottom: 14px;">After entering the keyword comma(,) is mandatory</div></td>';
	str +='</tr>';

    str += '   <tr>';
	str += '       <td class="tdWidth1">Title : <font class="requiredFont">*</font><b></td>';
	str += '       <td class="selectWidthPadd"><input type="text" id="newsfileTitle" name="fileTitle" size="25" maxlength="800"></input></td>'; 
	str += ' <td><a href="javascript:{changeLanguage();}" id="sourceTelugu"><img id="" src="images/letter-t-video.png"  style="height:35px;width:35px;" alt="Select Telugu language" title="Eenadu Telugu Language"/></a> </td>';
	str += ' <td><a href="javascript:{changeLanguage();}" id="sourceEnglish" style="display:none"><img src="images/e-letter.jpg" style="height:35px;width:35px;" title="English Language"></img></a></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Description : <font class="requiredFont">*</font></td>';
	str += '       <td class="selectWidthPadd"><textarea id="newsfileDescription" cols="20" rows="3" name="fileDescription" maxlength="1800"></textarea></td>';
	str += '   </tr>';

	str += '<TR>';
	str += ' <td><b><font>File Date<font class="requiredFont">*</font></font></b></td>';
	str += '<TD style="padding-right: 31px;"><input type="text" id="existingFromTextNews" class="dateField" readonly="true" name="fileDate" size="20" />';
	
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '<TD>';
	str += '<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	
	str += '</TD>';
	str += '</TR>';
	
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Importance : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="newsimportance" name="newsimportance"><option value="0">Select NewsImportance</option></select></td>';
	str += '   </tr>';

	str += '   <tr>';
	str += '       <td class="tdWidth1">Select Party : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="partiesList" name="party" onchange="getCandidatesOfSelectedParty(this.value)"><option value="0">Select Party</option><option value="163">BJP</option><option value="265">CPI</option>	  <option value="269">CPM</option><option value="362">INC</option><option value="990">MIM</option><option value="872" selected>TDP</option><option value="886">TRS</option><option value="1117">YSRCP</option></select></td>';
	str += '   </tr>';
	

	
	str+='<tr><td></td><td><img id="candidateAjaxImg" src="images/search.jpg" style="display:none;"/></td></tr>';


	str +='<tr><td class="tdWidth1"><div style="margin-top:100px;width:155px;">Select Candidate : </div></td><td><select multiple="true" id="list1"></select><input type="button" id="button1" value="Add" class="btn"/>&nbsp;<input type="button" id="button2" value="Remove" class="btn"/><br/><select multiple="true" id="candidateList" name="candidateList" style="margin-top:5px;"></select></td></tr>';
	str += '   <tr>';
	str += '       <td></td>';
	str += ' <td id="newsPublicRadioDiv"><label class="radio"><input type="radio" value="public" name="visibility" id="newsPublicRadioId" checked="true"><b><font id="newsfontDiv">Visible to Public Also</font></b></input></label></td>';
    str += '   </tr>';
	str += '   <tr>';
	str += '       <td></td>';
	str += '       <td id="newsprivateRadioDiv"><label class="radio"><input type="radio" value="private" name="visibility" id="newsprivateRadioId"><b><font>Make This Private</font></b></input></label></td>';
	str += '   </tr>';
	str +='    <tr>';
    str +='	   <td class="tdWidth1">Location Scope : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="scopeDiv" name="locationScope" onchange="getLocations(this.options[this.selectedIndex].value)"  /></td>';
    str +='  </tr>';
	str +='  <tr>';
	str +='    <td colspan="2">';
	str +='       <div id="showScopeSubs" />'; 
	str +='    </td>';
	str +='  </tr>';

	str += '   <tr>';
	str += '       <td class="tdWidth1">News description in details : </td>';
	str += '       <td class="selectWidthPadd"><textarea id="newsDesc" cols="20" rows="3" name="newsDescription"></textarea></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '</table>';

	str +='<input type="hidden" name="profileType" value="party_profile">';
	str +='<input type="hidden" name="profileId" value="'+tempPartyId+'">';
	str +='<input type="hidden" name="profileGalleryType" value="news_gallery">';
	
	
	str+='<div id="uploadFilesDiv" style="margin-left:122px;"></div>';
	str+='<div id="otherProNewsDiv" style="margin: 10px;"></div>'; 
	str += '<table class="aligncenter"><tr><td><input id="uploadNewsBtnId" type="button" class="btn btn-success" value="Upload News"  onClick="uploadNewsFromPartyPage1()"></td><td><input id="uploadNewsBtnId" type="button" class="btn btn-success highlight" value="Cancel"  onClick="clearDiv(\'newsGallaryDiv\');"></td></tr></table>';

	str +='<input type="hidden" id="keywordListId" name="keywordList"/>';

	str += '</form>';
	//str += '</fieldset>';

	str+='</div></div>';
	document.getElementById("newsGallaryDiv").innerHTML = str;

	getScopes();
	 getSource("source");
	 getLanguage("language");
	 getNewsImportance();
	 getCategory();
	 //getCandidatesofUser();

	  getScopeForUser();
	  getCandidates();
	  $('#partiesList').trigger('change');

  //keywords
  $("#keywordListId1").autoSuggest("getKeyWordsBySearchCriteria.action", {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});

}


function buildCreateNewsCategory()
{
   var str ='';
	str+='<div id="content" style="width:650px;">';
	
	str += '<h2 align="center" style="margin-left: 80px;"> Create A New Category</h2>';
	str+='<table class="aligncenter"><tr><td><div id="newsErrorMsgDivId" /></td></tr></table>';
	str += '<table class="aligncenter">';
    
    str +='<tr><td><b><font>Main Category <font class="requiredFont">*</font></font></b></td><td><select id="categoriesForGallary" name="categoriesForGallary"></td></tr>';
	str +='<tr><td><b><font>Category Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="newsCateName" size="25" maxlength="100" /></td></tr>';

	str += '<tr><td><b><font> Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="newsCateDesc" cols="27" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<table class="aligncenter"><tr><td><label class="radio"><input type="radio" value="public" name="visibility" id="newsPublicRadio" checked="true"><b><font>Visible to Public Also</font></b></input></label></td></tr>';
	str += '<tr><td><label class="radio"><input type="radio" value="private" name="visibility" id="newsPrivateRadio"><b><font>Make This Private</font></b></input></label></td></tr></table>';
	str+='<div id="createnewsgalAjax" style="display:none;margin-left:430px;clear:both;"><img src="images/search.jpg"/></div>';
	str += '<table class="aligncenter"><tr><td><input type="button" class="btn btn-success highlight" value="Create Gallary" style="background-color:#57B731" onClick="createCategory()"></td><td><input type="button" class="btn btn-success highlight" value="Cancel"  onClick="clearDiv(\'newsGallaryDiv\');"></td></tr></table>';

	str += '<div>';
	str+='</div>';
	document.getElementById("newsGallaryDiv").innerHTML = str;

	getAllCategoriesForGallary();
}

function createNewsCategory()
{
	$("#newsGallaryDiv").html('');
	var str = '';
	str +='<div id="content" style="width:650px;">';
	str +='<h2 align="center">Create A News Category</h2>';
	str+='<table class="aligncenter"><tr><td  style="padding-bottom:10px;"><div id="errorMsgDiv" /></td></tr></table>';
	str +='<table class="aligncenter">';
	str +='<tr><td class="tdWidth1">MainCategories :<font class="requiredFont">*</font></td><td class="selectWidthPadd"><select id="mainCategory" name="mainCategory" id="mainCategoryList"></select></td>';
	str +='<tr><td><b>Category Name</b><font class="requiredFont">*</font></td>';
	str +='<td><input type="text" id="userNewsCategory" /></td></tr>';
	str +='</table>';
	str += '<table class="aligncenter"><tr><td><label class="radio"><input type="radio" value="public" name="visibility" id="categoryPublicRadio" checked="true"><b><font>Visible to Public Also</font></b></input></label></td></tr>';
	str += '<tr><td><label class="radio"><input type="radio" value="private" name="visibility" id="categoryPrivateRadio"><b><font>Make This Private</font></b></input></label></td></tr></table>';
	str += '<table class="aligncenter"><tr><td><input type="button" class="btn btn-success highlight" value="Create Category" style="background-color:#57B731" onClick="createUserNewsCategory()"></td><td><input type="button" class="btn btn-success highlight" value="Cancel"  onClick="clearDiv(\'newsGallaryDiv\');"></td></tr></table>';
	
	str +='<div class="btn btn-inverse" id="updateCategoryId" style="margin-left:250px;margin-top:20px;">Update Category</div>';
	str +='<div id="categoriesTable">';
	str +='</div>';
	str +='</div>';
	$("#newsGallaryDiv").html(str);
getMainCategories();
}

function createUserNewsCategory()
{
   $("#errorMsgDiv").html('');
   var name = $("#userNewsCategory").val();
   var mainCategory = $("#mainCategory").val();
   
	if(mainCategory == 0)
	{
	  $("#errorMsgDiv").html('select Category').css('color','red');
	  return;
	}
	if($.trim(name) == "" || name.length == 0)
	{
	  $("#errorMsgDiv").html('Category Name is Required.').css('color','red');
	  return;
	}
	
   var isPublic = document.getElementById("categoryPublicRadio").checked;
   var makeThis = 'true';
   if(isPublic)
	 makeThis = "false";
   
   var jsObj =
		{ 
			mainCategory:mainCategory,
            name : name,
		    visibility : makeThis,
			task : "createUserNewsCategory"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createUserNewsCategoryAction.action?"+rparam;						
	callAjax1(jsObj,url);

}

function getDistricts1(stateId){
  var jsObj =
		{ 
            time : timeST,
			stateId : stateId,
			task:"getDistrictsByStateId"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}
function getDistricts2(stateId){
  var jsObj =
		{ 
            time : timeST,
			stateId : stateId,
			task:"getDistrictsByStateId",
            type:"forEdit" 
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callnewAjax(jsObj,url);
 
}

	function createCategory()
{
	var newsCatrgoryName = document.getElementById('newsCateName').value;
	var newsCatrgoryDesc = document.getElementById('newsCateDesc').value;
	var isPublic = document.getElementById('newsPublicRadio').checked;
	var partyId = 872;
	var makeThis = 'true';
	var categoryId = $("#categoriesForGallary").val();

	var errorDivEle = document.getElementById('newsErrorMsgDivId');
	var eFlag = false;

	var str = '<font color="red">';
	if(partyId.length <= 0)
	{
	  document.getElementById('alertMsg1').innerHTML='<font color="red">partyId is Required</font>';
	  eFlag = true;
	}

	if(newsCatrgoryName.length == 0)
	{
		str += 'Category Name is Required<br>';
		eFlag = true;
	}
	if(newsCatrgoryDesc.length == 0)
	{
		str += 'Description is Required<br>';
		eFlag = true;
	}
	
	if(newsCatrgoryDesc.length > 300)
	{
		str += 'Description should be less than 300 Characters<br>';
		eFlag = true;
	}

	if(categoryId == 0)
	{
      str +='Please Select Any Category.';
      eFlag = true;
	}
	
	str += '</font>';
	errorDivEle.innerHTML = str;
	if(eFlag)
	{
		$('html, body').animate({
         scrollTop: $("#newsErrorMsgDivId").offset().top
     }, 2000);
	}
	if(eFlag)
		return;

	if(isPublic)
		makeThis = 'false';
	
	newsCatrgoryName = removeAllUnwantedCharacters(newsCatrgoryName);
	newsCatrgoryDesc = removeAllUnwantedCharacters(newsCatrgoryDesc);
	$("#createnewsgalAjax").show();
	var jsObj =
		{ 
            name : newsCatrgoryName,
		    desc : newsCatrgoryDesc,
			visibility : makeThis,
			createOrUpdate:'Create',
			partyId : partyId,
			contentType : 'News Gallary',
			categoryId:categoryId,
			task : "createNewGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getScopes(){
  
 var jsObj =
		{ 
            time : timeST,
			divId:"scopeDiv",
  		    task:"getScopesForNewSearch"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}
function getStatesForPartyManifesto()
{
  var jsObj =
		{ 
            time : timeST,
			task:"getStates"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function clearAllForSelect(elmtId)
   {
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=1;i--)
	{
		elmt.remove(i);
	}	
   }
function clearAll(elmtId)
   {
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
   }
function clearAllElmts(scopeId,value){
 if(scopeId==4)
 { 
    if(value==1)
	clearAllForSelect("constituencyDiv");
	
 }
 if(scopeId==5)
 {
   if(value==1)
   {
	clearAllForSelect("constituencyDiv");
	clearAllForSelect("mandalDiv");
	}
   if(value==2)
	clearAllForSelect("mandalDiv");
 }
 if(scopeId==6)
 {
   if(value==1)
   {
	clearAllForSelect("constituencyDiv");
	clearAllForSelect("mandalDiv");
	clearAllForSelect("villageDiv");
	}
	if(value==2)
	{
	clearAllForSelect("mandalDiv");
	clearAllForSelect("villageDiv");
	}
	if(value==3)
	clearAllForSelect("villageDiv");
 }
 if(scopeId==9)
 {
   if(value==1)
   {
	clearAllForSelect("constituencyDiv");
	clearAllForSelect("mandalDiv");
	clearAllForSelect("villageDiv");
	}
	if(value==2)
	{
	clearAllForSelect("mandalDiv");
	clearAllForSelect("villageDiv");
	}
	if(value==3)
	clearAllForSelect("villageDiv");
 }

}

 function buildParlCountryYears()
 {
    document.getElementById("assemblyStates").innerHTML = "";
    if(document.getElementById("assmblParlElecYears") != null)
      document.getElementById("assmblParlElecYears").innerHTML = "";
    buildAssmblParlElecYears('Parliament');
 }

function addMoreFiles()
{
	var moreDivElmt = document.createElement("addMoreFilesDiv");
	var str ='';
	str +='<table style="background:#e3e3e3;border-radius:9px;padding:5px;margin-top:12px;" id="moreFileTableId'+fileCount+'">';
	str += ' <tr>';
	str +='<td>File Path</td>';
	str += ' <td class="selectWidthPadd"><input type="file" name="userImage" id="newsFileId'+fileCount+'" size="25"  class="newsFile" style="width: 225px;"/></td>';
	str += ' </tr>';
	str += ' <tr>';
	str += ' <td class="tdWidth1">Source : <font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="filesourceId'+fileCount+'" name="fileSourceId" style="width:175px;"><option value="0">Select Source</option></select></td>';
	str += ' </tr>';
	str += ' <tr>';
	str += ' <td class="tdWidth1">Language : <font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="sourceLangId'+fileCount+'" name="sourceLanguageId" style="width:175px;"><option value="0">Select Language</option></select></td>';
	
	str += ' </tr>';
	str += '       <td class="tdWidth1">Edition : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="newsedition'+fileCount+'" name="newsedition" style="width:175px;margin-top:8px;"><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Page Number : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="pageno'+fileCount+'" name="pageno" class="pageno pagenoCls" size="25" maxlength="200" style="margin-top:8px;" onKeyup="IsNumeric(this.value);"></input></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Length : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="newslength'+fileCount+'" class="newslength newsLengthCls" name="newslength" size="25" maxlength="200" style="margin-top:8px;" onKeyup="IsNumeric1(this.value);"></input></td>';
	str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteFile(\'moreFileTableId'+fileCount+'\')"></td>';
	str += '   </tr>';
	str +='</table>';
	moreDivElmt.innerHTML = str;
	document.getElementById("addMoreFilesDiv").appendChild(moreDivElmt);
	getSource("filesourceId"+fileCount+"");
	getLanguage('sourceLangId'+fileCount+'');
	fileCount++;
} 

function getCategory()
{
var jsObj =
		{ 
            time : timeST,
			task:"getCategory"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getNewsGalleryForUplaodAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}
function getNewsImportance()
{
	var jsObj =
		{ 
            time : timeST,
			task:"getNewsImportance"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getNewsGalleryForUplaodAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}
function clearDiv(divId)
{
  document.getElementById(divId).innerHTML = "";
}

function buildCreateVideoGallaryDiv()
{
	var str ='';
	str+='<div id="content" style="width:650px;">';
	
	str += '<h2 align="center">Create A Video Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table class="aligncenter"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table class="aligncenter"><tr><td><b><font>Gallery Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="pVGallaryNameId" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font>Description</font><b></td>';
	str += '<td><textarea id="pVGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str+='<table class="aligncenter">';
	str+='<tr><td>';
	str += '<div ><label class="radio"><input type="radio" value="public" name="visibility" id="vpublicRadioId" checked="true"><b><font>Visible to Public Also</font></b></input></label></div>';
	str+='</td></tr>';
	str+='<tr><td>';
	str += '<div><label class="radio"><input type="radio" value="private" name="visibility" id="vprivateRadioId"><b><font>Make This Private</font></b></input></label></div>';
	str+='</td></tr>';
	str+='</table>';
	str+='<div id="createvediogalAjax" style="display:none;margin-left:430px;clear:both;"><img src="images/search.jpg"/></div>';
	str += '<table class="aligncenter"><tr><td><input type="button" class="btn btn-success highlight" value="Create Gallery" style="background-color:#57B731" onClick="createVideoGallary(\'Video Gallary\')"></td><td><input type="button" class="btn btn-success highlight" value="Cancel" onclick="clearDiv(\'videoGallaryDiv\')"></td></tr></table>';

	
	//str += '</fieldset>';
	str+='</div>';
	document.getElementById("videoGallaryDiv").innerHTML = str;

}

function buildUploadVideoDiv()
{
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	str += '<h2 align="center">Upload A Video</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
    str += '<table class="aligncenter"><tr><td>';
	str+='<div id="fileSuccessDiv"></div><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table class="aligncenter" style="margin-left:123px;left:50%;">';
	str += '<tr><td><b><font>Select Gallery</font></b></td><td><select onchange="buildPartyVedioVisibility()" id="gallarySelectId" name="gallarySelectId"><option value="0">Select</option></select></td></tr>';
	str += '<tr><td><b><font>Video Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="fileTitleId" name="videoTitle" size="25" maxlength="50" style="margin-top:8px;"></td></tr>';
    str += '<tr><td><b><font>Video Description<font class="requiredFont">*</font></font></b></td><td><textarea id="fileDescId" name="videoDescription" cols="19" rows="3" name="requirement" style="margin-top:8px;"></textarea></td></tr>';
    str += '<TR>';
	str += ' <td><b><font>File Date<font class="requiredFont">*</font></font></b></td>';
	str += '<TD style="padding-right: 31px;"><input type="text" id="existingFromText" class="dateField" readonly="true" name="fileDate" size="20" style="margin-top:8px;"/>';
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '<TD>';
	str += '<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	//str += '<IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '</TD>';
	str += '</TR>';
	str += '<tr><td><b><font>Video Path In Youtube<font class="requiredFont">*</font></font></b></td><td><input type="text" id="path" name="path" size="25" maxlength="200" style="margin-top:8px;"></td></tr>';
	str += '<tr><td><b><font>Keyword</font></b></td><td><input type="text" id="keyword" name="keyword" size="25" maxlength="200" style="margin-top:8px;"></td></tr>';
	str += '<tr><td><b><font>Source</font><font class="requiredFont">*</font></b></td><td><select id="source" name="fileSourceId" style="margin-top:8px;"><option value="0">Select Source</option></select></td></tr>';
	str += '<tr><td><b><font>Language</font><font class="requiredFont">*</font></b></td><td><select id="language" name="sourceLanguageId" style="margin-top:8px;"><option value="0">Select Language</option></select></td><td class="selectWidthPadd"><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMoreVideos()" title="Click here to add more Videos" alt="Click here to add more images"/></td></tr>';
	str += '<tr><td colspan="3"><div id="addMoreVideosDiv"></div></td></tr>';
	str += '</table>';
	str+='<table class="aligncenter">';
	str+='<tr><td>';
	str += '<div id="vedioPublicDiv"><label class="radio"><input type="radio" value="public" name="visibility" id="vpublicRadioId" checked="true"><b><font>Visible to Public Also</font></b></input></label></div>';
	str += '<div id="vedioPrivateDiv"><label class="radio"><input type="radio" value="private" name="visibility" id="vprivateRadioId"><b><font>Make This Private</font></b></input></label></div>';
	//str+='<input type="radio" style="margin-left:57px;" onclick="otherProfiles(\'otherProVideoDiv\',\'fromPartyProfile\',\'Video Gallary\')"/>    Do you want to upload this file to other profiles';
	str+='<div id="otherProVideoDiv" style="margin: 10px;"></div>'; 
	str+='</td></tr>';
	str+='</table>';
	str+='<div id="uploadvediogalAjax" style="display:none;margin-left:430px;clear:both;"><img src="images/search.jpg"/></div>';
	str += '<table class="aligncenter"><tr><td><input type="button" id="uploadVideoBtnId" class="btn btn-success highlight" value="Upload Video" style="background-color:#57B731" onclick="uploadVideoGallary()"></td><td><input type="button" class="btn btn-success highlight" value="Cancel" onclick="clearDiv(\'videoGallaryDiv\')"></td></tr></table>';
	//str += '</fieldset>';
	str+='</div>';
	document.getElementById("videoGallaryDiv").innerHTML = str;
	getPartyGallariesForUplaod('Video Gallary');
	getSource("source");
	getLanguage("language");


}

function getEnadu()
{


}

function getCompleteVideoGallaries(gallaryId){
   
}

function createVideoGallary(contentType)
{

	var galName = document.getElementById('pVGallaryNameId').value;
	var galDesc = document.getElementById('pVGallaryDescId').value;
	var isPublic = document.getElementById('vpublicRadioId').checked;
	var partyId=872;
	var makeThis = 'true';

	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var eFlag = false;

	var str = '<font color="red">';

	if(galName.length == 0)
	{
		str += 'Gallery Name Required<br>';
		eFlag = true;
	}
	if(galDesc.length > 300)
	{
		str += 'Description should be less than 300 Characters<br>';
		eFlag = true;
	}
	
	str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;

	if(isPublic)
		makeThis = 'false';
	
	galName = removeAllUnwantedCharacters(galName);
	galDesc = removeAllUnwantedCharacters(galDesc);
	$("#createvediogalAjax").show();
	var jsObj =
		{ 
            name : galName,
		    desc : galDesc,
			visibility : makeThis,
			partyId : partyId,
			contentType : contentType,
			createOrUpdate:'Create',
			task : "createVideoNewGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageAction.action?"+rparam;						
	callAjax(jsObj,url);
}



function buildPartyVedioVisibility()
{

	var galEle = document.getElementById('gallarySelectId');
	var galId = galEle.options[galEle.selectedIndex].value;
	var jsObj =
	{
		gallaryId : galId,
			task : "partyVideoVisibility"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageVisibilityAction.action?"+rparam;
	callAjax(jsObj,url);
}



function buildPartyNewsVisibility()
{
	var galEle = document.getElementById('gallaryId');
	var galId = galEle.options[galEle.selectedIndex].value;

	var jsObj =
	{
		gallaryId : galId,
			task : "partyNewsVisibility"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageVisibilityAction.action?"+rparam;
	callAjax(jsObj,url);
}

function buildPhotoVisibility()
{
var galEle = document.getElementById('gallaryPhotoSelectId');
	var galId = galEle.options[galEle.selectedIndex].value;

	var jsObj =
	{
		gallaryId : galId,
		task : "partyPhotoVisibility"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageVisibilityAction.action?"+rparam;
	callAjax(jsObj,url);
}
function showVideoGallaryCreateMsg(result)
{
	$("#createvediogalAjax").hide();
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var str = '';
	
	if(result.resultCode == 0)
	{
		clearVideoGallaryFields();
		if(result.exceptionMsg == null)
			str += '<font color="green"><b>Gallery Created Successfully.</b>';
		else
			str += '<font color="red"><b>Gallery Name is already exist.</b>';
			
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

	errorDivEle.innerHTML = str;
}

function showUploadVideoStatus(result)
{
	var errorDivEle = document.getElementById('fileSuccessDiv');
	document.getElementById('uploadVideoBtnId');
	var str = '';

	if(result.resultCode == 0)
	{
		clearUploadVideoFields();
		enableButton('uploadVideoBtnId');
		str += '<font color="green"><b>Video Uploaded Successfully.</b>';
	}
	else if(result.resultCode == 1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	else
	{
	
		str += '<font color="red"><b>'+result+'</b>';
	}
	errorDivEle.innerHTML = str;
}

function clearVideoGallaryFields()
{

	document.getElementById('pVGallaryNameId').value = '';
	document.getElementById('pVGallaryDescId').value = '';
	//document.getElementById('vpublicRadioId').checked = true;
}

function clearUploadVideoFields()
{
	$("#addMoreVideosDiv").html('');
	$("#otherProVideoDiv").html('');
	document.getElementById('fileTitleId').value = '';
	document.getElementById('fileDescId').value = '';
	//document.getElementById('vpublicRadioId').checked = true;
	document.getElementById('path').value = '';
	document.getElementById("keyword").value = '';
	document.getElementById("source").value = '';	
	document.getElementById('existingFromText').value = '';
}
//****** profile discription ***

function cleardescriptionFields()
{
	document.getElementById('profileDescId').value = '';

}
function insertProfileDiscription()
{

if(!formValidation()){
document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
document.getElementById("profileManagementMainOuterDiv2").style.display = 'none';

document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';

$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});

profileDiscriptionDiv();
}
return;
}
 
 function updateDescriptionDiv()
 {	
   
	var jsObj =
		{ 
            partyId : 872,
			
		   	task : "partyDescriptionUpdate"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyGallariesForUplaodAction.action?"+rparam;
	callAjax(jsObj,url); 
	
 }

function  showPartyDescription(myResults)
 {

	var listSize = myResults.length;
	sizeOfArray = listSize;
	var i;
	var str ='';
	str += '<div id="content" style="width:750px;">';
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +='<table align="center" style="width:32%;margin-top:7px;">';
	str += '<tr>';
	str += '<td><input type="button" class="btn btn-success highlight" value="Add Description" onclick="addDescriptionDiv()"></td>';
	str += '<td><input type="button" class="btn btn-success highlight" value="Update Description" onclick="updateDescriptionDiv()"></td>';
	str += '</tr>';
	str += '</table>'
	str += '<fieldset class="imgFieldset" style="margin-left:-16px;width:718px;">';	
	str += '<center><b style="font-size:15px"><font color="#669900">Update The Profile Description </font> </b> </center>';
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<div id="galErrorMsgDivId"></div>';
	str += '<div id="fileupdateUploadErrorMsgDiv"></div>';
	str += '<tr>';
	str += '<td>';
	str += '<b><font>Order No</font></b></td><td style="padding-left: 82px"><b><font>Description </font></b></td></tr>';
	for(i=0 ; i<listSize ; i++)
	{
	str += ' <tr><td style="padding-right:38px"><input type="text" id="orderNoId_'+i+'" value= "'+myResults[i].orderNo+'" size="5"></td>';
	str += ' <td style="padding-right: 29px"> <textarea id="descId_'+i+'" cols="25" rows="4" style="background:#ffffff !important;"> '+myResults[i].description+'</textarea> </td>';
	str += ' <td style="padding-right: 82px"> <input type = "button" id="delete_'+i+'" class="buttonStyle" style="background: none repeat scroll 0 0 #F61D50;" value = "Delete" onClick="deleteProfileById('+myResults[i].partyProfileDescriptionId+')"></td></tr>';
	str += ' <input type="hidden" id="partyProfileDescriptionId_'+i+'" value="'+myResults[i].partyProfileDescriptionId+'">';
	}
	str += '</table>';
	str += '<table style="margin:5px;margin-left:auto;margin-right:auto;margin-bottom:7px;width:23%"><tr><td><input type="button" class="btn btn-success highlight" value="Update Discription" style="background-color:#57B731" onClick="updateProfileDiscription()"></td><td><input type="button" class="btn btn-success highlight" value="Cancel" onclick="clearDiv(\'discriptionDiv\')" ></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	
	document.getElementById("discriptionDiv").innerHTML = str;
	
	}
function addProfileDiscription()
 {
   var fileDesc = document.getElementById('profileDescId').value;
   var partyId=872;
   var errorDivEle = document.getElementById('galErrorMsgDivId');
   var eFlag = false;

	var str = '<font color="red">';

	if(fileDesc.length == 0)
	{
		str += 'Profile Discription is Required<br>';
		eFlag = true;
	}
    str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;
	var jsObj =
		{ 
		    
            partyId : partyId,
			fileDesc : fileDesc,
		   	task : "saveDiscription"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageAction.action?"+rparam;
	callAjax(jsObj,url);	
    
 }
 
 function deleteProfileById(profDescId)
 {

 var r=confirm("Do you want to delete!");
 if (r==true)
 {
 var jsObj =
		{ 
     		profDescId : profDescId,
		   	task : "deleteDiscription"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewPartyGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
	}
	
 }
 function showDevelopmentActivity()
 {
   $("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
   $("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
   $("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
   $("#developmentGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
   $("#profileGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
   $("#manifestoId").css({"background":"none repeat scroll 0 0 #0063DC"});
 }
 function showDiscriptionStatus(myResult)  
 {
	var errorDivEle = document.getElementById('fileUploadErrorMsgDiv');
	var str = '';
	if(myResult.resultCode == 0)
	{
		cleardescriptionFields();
		str += '<font color="green"><b>Profile Discription saved Successfully.</b>';

	}
	else if(myResult.resultCode == 1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	
	errorDivEle.innerHTML = str;
}
function removeAllUnwantedCharactersInArray(str)
{
   var strng = str.replace(/[\\\\%\&\#\"+"]/g," ");
   return replaceEnterKey(strng,"  ");
} 
function updateProfileDiscription()
{
	
	var partyId=872;
	var orderNoArr = [];
	var descriptionArr = [];
	var profDescIdArr = [];
	   for(i=0 ; i < sizeOfArray ; i++)
   		{
		
			orderNoArr.push(document.getElementById('orderNoId_'+i).value);
			profDescIdArr.push(removeAllUnwantedCharactersInArray(document.getElementById('partyProfileDescriptionId_'+i).value));
			descriptionArr.push(document.getElementById('descId_'+i).value);		
   		}
  
  var jsObj =
		{ 
		    partyId :partyId,
			orderNoArr : orderNoArr,
			descriptionArr : descriptionArr,
			profDescIdArr : profDescIdArr,
		   	task : "updateProfileDiscription"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createUpdateGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
    
}

function showDiscriptionUpdateStatus(myResult)  
{

	var errorDivEle = document.getElementById('fileupdateUploadErrorMsgDiv');
	var str = '';

	if(myResult.resultCode == 0)
	{
		str += '<font color="green"><b>Profile Discription Updated Successfully.</b>';
	}
	else if(myResult.resultCode == 1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	
	errorDivEle.innerHTML = str;
}

function showDeleteStatus(myResult) 
{

var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '';

	if(myResult.resultCode == 0)
	{
		updateDescriptionDiv();
		str += '<font color="green"><b>Profile Discription Deleted Successfully.</b>';
	}
	else if(myResult.resultCode == 1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	
	errorDivEle.innerHTML = str;
}

function showDeleteFilesAndPhotosStatus(myResult)
	{
	
	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
		var str = '';

		if(myResult.resultCode == 0)
		{
			getCompleteGallaries(gGallaryId);
			str += '<font color="green"><b>Photo Deleted Successfully.</b>';
		}
		else if(myResult.resultCode == 1) 
		{
			str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
		}
		
		errorDivEle.innerHTML = str;
		
}

function showDeleteGallary(myResult)   
	{
	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
		var str = '';

		if(myResult.resultCode == 0)
		{
			showPhotoGallary();
			str += '<font color="green"><b>Gallary Deleted Successfully.</b>';
		}
		else if(myResult.resultCode == 1) 
		{
			str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
		}
		
		errorDivEle.innerHTML = str;
		
}

function deleteFilesAndPhotos(fileId)
{
var r=confirm("Do you want to delete!");
 if (r==true)
 {
 var jsObj =
		{ 
		    gallaryId : gGallaryId,
     		fileId : fileId,
		   	task : "deleteFilesAndPhotos"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
	}
}

function deleteGallary(gallaryId)
{
var r=confirm("Do you want to delete!");
 if (r==true)
 {
 var jsObj =
		{ 
     		gallaryId : gallaryId,
		   	task : "deletePartyGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "deletePartyGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
	}
}

function updateGallary(gallaryId)
 {
  var r=confirm("Do you want to update!");
 if (r==true)
    {
	var partyId = 872;
	var jsObj =
		{ 
     		gallaryId : gallaryId,
			partyId : partyId,
		   	task : "UpdatePartyGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updatePartyPhotoGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
	}
 
 }
 
 function updateGallaryDiv(myResults)
 {
    var str ='';
	document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
	
	str+='<div id="content" style="width:650px;">';
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Update A Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font>Gallery Name<font class="requiredFont"> * </font></font></b></td><td><input type="text" id="pGallaryNameId" size="25" maxlength="100"></td></tr>';
	str += '<tr><td><b><font>Description</font><b></td>';
	str += '<td><textarea id="pGallaryDescId" cols="19" rows="3" name="requirement">'+myResults.description+'</textarea></td></tr></table>';
	str +='<input type = "hidden" name ="gallaryId" id ="gallaryId" value ='+myResults.gallaryId+'>';
	str += '<div style="padding-left: 14px; padding-right: 120px; "><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font>Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-left: 14px; padding-right: 120px; "><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font>Make This Private</font></b></input></div>';
	str += '<table style="margin-left:auto;margin-right:auto;"><tr><td style="padding-right: 4px;"><input type="button" class="btn btn-success highlight" value="Update Gallery" style="background-color:#57B731" onClick="createGallary(\'Photo Gallary\',\'Update\')"></td><td style="padding-right: 49px;"><input type="button" class="btn btn-success highlight" value="Cancel" onclick="showPhotoGallary()" style="background-color:#CF4740"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("updateGallaryDiv").innerHTML = str;
	document.getElementById("pGallaryNameId").value=myResults.gallaryName;
	
 }
 
 function  updateGallaryStatus(myResults)
    {
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var str = '';
	
	if(myResults.resultCode == 0)
	{
		showPhotoGallary();
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

	errorDivEle.innerHTML = str;
	

	}
	
	function updateFilesAndPhotos(fileId)
		{
			var r=confirm("Do you want to update the photo(or files)!");
			 if (r==true)
			 {
			 var jsObj =
					{ 
						gallaryId : gGallaryId,
						fileId : fileId,
						task : "updateFilesAndPhotos"
					};

				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "candidatePhotoGallaryUpdateAction.action?"+rparam;
				callAjax(jsObj,url);	
				}
		}
		
function updateFilesAndPhotosDiv(myResults,fileId)
     {
	 var str ='';
		str+='<div id="content" style="width:650px;">';
		str += '<fieldset class="imgFieldset" style="width:400px;">';
		str += '<h2 align="center">Update A Photo</h2>';
		str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
		str += '<table align="left" class="paddingCss"><tr><td><div id="fileUploadErrorMsgDivId"></div></td></tr></table>';
		str += '<table width="75%">';
		str += '<tr><td><b><font>Select Gallery</font></b></td><td><select id="gallaryPhotoSelectId" class="gallaryTitleVal" name="gallaryId" style="width:175px;"></select></td></tr>';
		str += '<tr><td><b><font>Photo Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="fileTitleId" name="fileTitle" size="25" maxlength="50"></td></tr>';
		str += '<tr><td><b><font>Description<font class="requiredFont">*</font></font><b></td>';
		str += '<td><textarea id="fileDescId" name="fileDescription" cols="19" rows="3" name="requirement">'+myResults.fileDescription1+'</textarea></td></tr></table>';
		str += '<div style="padding-right: 113px;"><input type="radio" value="public" name="visibility" id="publicRadioId"><b><font>Visible to Public Also</font></b></input></div>';
		str += '<div style="padding-right: 127px;"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font>Make This Private</font></b></input></div>';
		str += '<table><tr><td style="padding-right: 40px;"><input type="button" class="btn btn-success highlight" value="Update Photo" style="background-color:#57B731" onClick="updatePhoto('+fileId+','+myResults.fileTypeId+')"></td><td style="padding-right: 41px;"><input type="button" class="btn btn-success highlight" value="Cancel" onclick="getCompleteGallaries(gGallaryId)"  style="background-color:#CF4740"></td></tr></table>';
		str += '</fieldset>';
		str+='</div>';
		document.getElementById("photoGallaryDiv").innerHTML = str;
		document.getElementById('fileTitleId').value = myResults.fileTitle1;

		getCandidateGallariesForUpdatePhoto();
	 }	 
function updatePhoto(fileId,fileGallaryId)
{ 
  var galEle = document.getElementById('gallaryPhotoSelectId');
     var gallaryId = galEle.options[galEle.selectedIndex].value;
	 var title = document.getElementById('fileTitleId').value;
	var galDesc = document.getElementById('fileDescId').value;
	var isPublic = document.getElementById('publicRadioId').checked;	
	var makeThis = 'true';
	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var eFlag = false;
	var str = '<font color="red">';

	if(title.length == 0)
	{
		str += 'Title is  Required<br>';
		eFlag = true;
	}
	if(title.length >50)
	{
		str += 'Title should be less than 50 Characters<br>';
		eFlag = true;
	}
	if(galDesc.length == 0)
	{
		str += 'Description is  Required<br>';
		eFlag = true;
	}
	if(galDesc.length > 300)
	{
		str += 'Description should be less than 300 Characters<br>';
		eFlag = true;
	}
	
	str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;

	var jsObj =
		{ 
            title : title,
		    desc : galDesc,
			visibility : makeThis,
             fileId:fileId,
			gallaryId : gallaryId,
			fileGallaryId:fileGallaryId,
			task : "updateIndividualPhotoDetails"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}	 
	
 

	  function hideVedioVisibility(myResults)
	  {
		

		  if(myResults.isPrivate == "true"){

			  document.getElementById('vedioPublicDiv').style.display = 'none';

			document.getElementById('vedioPrivateDiv').style.display = 'block';
			document.getElementById('vprivateRadioId').checked = true;
		  }
		  else
		  {
			document.getElementById('vedioPublicDiv').style.display = 'block';
		   document.getElementById('vpublicRadioId').checked = true;

		   document.getElementById('vedioPrivateDiv').style.display = 'block';
		  }
	  }
	  function hideNewsVisibility(myResults)
	  {
		  if(myResults.isPrivate == "true")
		  {
			 document.getElementById('newsPublicRadioDiv').style.display = 'none';
			 
			document.getElementById('newsprivateRadioDiv').style.display = 'block';
			document.getElementById('newsprivateRadioId').checked = true;
		  }
		  else
		  {
			 document.getElementById('newsPublicRadioDiv').style.display = 'block';
			document.getElementById('newsPublicRadioId').checked = true;
		   
		   document.getElementById('newsprivateRadioDiv').style.display='block';
		   
		  }
	  }

	  function hidePhotoVisibility(myResults)
	  {
		  
		if(myResults.isPrivate == "true")
		  {
			 document.getElementById('photoPublicRadioDiv').style.display = 'none';

			document.getElementById('photoPrivateRadioDiv').style.display = 'block';
			document.getElementById('photoprivateRadioId').checked = true;
		  }
		  else
		  {
			  document.getElementById('photoPublicRadioDiv').style.display = 'block';
		   document.getElementById('PhotopublicRadioId').checked = true;
		   document.getElementById('photoPrivateRadioDiv').style.display = 'block';

		  }
	  }
	  function checkAllValuesAndSendAjaxForNews()
{
	if($('#fromDateId1').val() != "" && $('#toDateId1').val() != "" && $('#fromDateId').val() != "" && $('#toDateId').val() != "" && $('#gallariesList').val() != "" && $('#gallariesList').val() != "0"){
		$('#dateErrorMessage').html('');
		$('#noNewsError').html("");
		$('#gallariesList').change();
	}

}

function checkAllValuesAndSendAjax()
{
	if($('#fromDateId1').val() != "" && $('#toDateId1').val() != "" && $('#fromDateId').val() != "" && $('#toDateId').val() != "" && $('#candidatesList').val() != ""){
		$('#dateErrorMessage').html('');
		$('#candidatesList').change();
		$('#noNewsError').html("");
	}

}

function showTheNewsToUpdate()
{
  responseFileIdsArray = new Array();
  $("#selectedNewsCount").html('0');
  document.getElementById("profileManagementMainOuterDiv2").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';
  $("#profileManagementMainOuterDiv5").css("display","none");
  $('#profileManagementMainOuterDiv4').show();
  document.getElementById("profileManagementMainOuterDiv6").style.display = 'none';
   $("#profileManagementMainOuterDiv7").css("display","none");

  $("#newDesignationDiv").css("display","none");
  $("#newPartyCreationDiv").css("display","none");
  $("#dateSelectDiv").css("display","block");
  $("#newsFromDateId").val('');
  $("#newsToDateId").val('');
   populateDate();
  getTotalNewsWithPagination();

}
function buildNewsDetails()
{
	$('#ajaxImg').show();
	var fromDate = $("#newsFromDateId").val();
	var toDate = $("#newsToDateId").val();
    

    var jsObj = {
			queryType: 'getAllNews',
			task: 'getAllNews',
			fromDate:fromDate,
			toDate:toDate

	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getAllNewsForAUser.action?"+rparam;
	callnewAjax(jsObj,url);
}
function callnewAjax(jsObj,url){

var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText); 

		 if(jsObj.task == "getNewsForACandidate")
		 {

			 buildNewsDetailsOfcandidate(myResults);

		 }
		 else if (jsObj.task == "getCandidates")
		 {
             buildCandidates(myResults);
		 }else if(jsObj.task == "getCandidatesOfAUser")
		 {
			 buildCandidatesOfAUser(myResults);
		 }else if(jsObj.task == "getAllNews"){
			 	$('#ajaxImg').hide();
			 newsDetails = myResults;
            buildAllNewsDetails(myResults);
		 } 
		  if(jsObj.task == "Update" || jsObj.task == "Delete"){
        $('#newsSuccessDiv').html("<font style='font-weight:bold;color:green;margin-left:50px;'>News Updated Successfully.</font>");
           setTimeout(hideDialog,3000);
			buildNewsDetails();
		 } 
         else if(jsObj.task == "getCandidatePhotoGallaryDetail")
			{
               buildCandidatePhotoGallary(myResults);
			}
			else if(jsObj.task == "candidateGallariesForUplaod")
			{ 
			   if(jsObj.contentType=="News Gallary")
				{
						appendResults1(myResults,"gallaryIdForEdit");
				
				}
				else if(jsObj.contentType=="Video Gallary")
				{
					clearOptionsListForSelectElmtId('gallarySelectId');
					createOptionsForSelectElmtId('gallarySelectId',myResults);

					$("#gallarySelectId").prepend("<option value ='0'>Select Gallery</option>");
					document.getElementById("gallarySelectId").value = 0;
				}
				else if(jsObj.contentType=="Photo Gallary")
				{
					clearOptionsListForSelectElmtId('gallaryphotoselectId');
					createOptionsForSelectElmtId('gallaryphotoselectId',myResults);
					$("#gallaryphotoselectId").prepend("<option value='0'>Select Gallery</option>");
                    document.getElementById("gallaryphotoselectId").value = 0;
				}
			}
			else if(jsObj.task == "getScopesForNewSearch")
			{ 
			  appendResults1(myResults,jsObj.divId);
			}
			else if(jsObj.task == "getStates" && jsObj.type == "forEdit")
			{
               appendResults1(myResults,"stateDivForEdit");
			}
			else if(jsObj.task == "getStates"){

              appendResults1(myResults,"stateDiv");
			}
			else if(jsObj.task == "getDistrictsByStateId" && jsObj.type == "forEdit"){
				appendResults1(myResults,"districtDivForEdit");

			}
			else if(jsObj.task == "getDistrictsByStateId"){
				appendResults1(myResults,"districtDiv");
			}else if(jsObj.task == "constituenciesInDistrict" && jsObj.type=="forEdit"){
                  appendResults1(myResults,"constituencyDivForEdit");
			}
			else if(jsObj.task == "constituenciesInDistrict"){
				appendResults1(myResults,"constituencyDiv");
			}else if(jsObj.task == "getConstNotInGivenAreaType" && jsObj.type=="forEdit"){
                appendResults1(myResults,"constituencyDivForEdit");
			}
			else if(jsObj.task == "getConstNotInGivenAreaType"){
                appendResults1(myResults,"constituencyDiv");
			}else if(jsObj.task == "subRegionsInConstituency" && jsObj.type=="forEdit"){ 

               appendResults1(myResults,"mandalDivForEdit");
			}else if(jsObj.task == "subRegionsInConstituency"){ 
               appendResults1(myResults,"mandalDiv");
			}else if(jsObj.task == "hamletsOrWardsInRegion" && jsObj.type=="forEdit"){ 
               appendResults1(myResults,"villageDivForEdit");
			}
			else if(jsObj.task == "hamletsOrWardsInRegion"){ 
               appendResults1(myResults,"villageDiv");
			}else if(jsObj.task == "boothsInTehsilOrMunicipality" && jsObj.type=="forEdit"){ 
               appendResults1(myResults,"villageDivForEdit");
			}
			else if(jsObj.task == "boothsInTehsilOrMunicipality"){ 
               appendResults1(myResults,"villageDiv");
			}else if(jsObj.task == "saveFileComment"){

				alert("Your comment is saved successfully.");
			}
			else if(jsObj.task == "getNews"){ 
               buildLocationWiseNews(myResults);
			}
			else if(jsObj.task == "saveNews")
			{
			showReportFileNewsStatus(myResults);
			}
			else if(jsObj.task == "updateGallaryKeyword")
			{
				showStatusForupdateGallaryKeyword(myResults);
			}
		else if(jsObj.task == "updatexistingKeyword")
			{
				showStatusForupdateGallaryKeyword1(myResults);
			}
			
			else if(jsObj.task == "getGallaries")
			{
				keywordGallaries = myResults;
			}
			else if(jsObj.task == "getAllNewsReports")
			{
				buildAllNewsReports(myResults);
			}
		
}
		catch(e)
		{   
		}  
	 },
	scope : this,
	failure : function( o )
	{
							
	}
  };
 var conn = YAHOO.util.Connect;
if(jsObj.task == "Update")
{
//conn.initHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8', true);  anil 
}
 conn.asyncRequest('GET', url, callback);
}

function buildkeywordGallaries(result)
{
	if(result != null && result.length > 0)
	for(var i in result)
		{
	keywordGallaries.push(result[i]);
	}
}
function buildAllNewsDetails(results)
{

	 var pageNumber = 0;
if(modifiedRecord == 0)
	pageNumber = 0;
else{
	for(var j in results)
	 if( results[j].fileId == modifiedRecord )
		pageNumber = Math.ceil(j/noOfRowsPerPage) - 1;

}


	var str="";
	str+='<table id="newsDetailsTable">';
	str+='<thead>';
	 str+='<tr>';
	
	  str+='<th>SOURCE</th>';
	  str+='<th>TITLE</th>';
	  str+='<th>DESCRIPTION</td>';
	  str+='<th>IMPACT AREA </th>';
	  str+='<th>AREA NAME</th>';
	  str+='<th>NEWS DATE</th>';
	  str+='<th>Add Response</th>';
	  str+='</thead>';
	 str+='</tr>';
	 for(var i in results)
	 {
		 str+='<tr>';
		    
			str+='<td>'+results[i].source+'</td>';			
			if(results[i].eenaduTeluguFontStr != null && results[i].eenaduTeluguFontStr == "Eenadu Telugu")
		     str+='<td><span class="enadu">'+results[i].fileTitle1+'</span></td>';
			else
	         str+='<td>'+results[i].fileTitle1+'</td>';

			if(results[i].descEenadu)
		     str+='<td><span class="enadu">'+results[i].description+'</span></td>';
			else
			 str+='<td>'+results[i].description+'</td>';

			str+='<td>'+results[i].locationScopeValue+'</td>';
			if(results[i].locationValue !=null)
				str+='<td>'+results[i].locationValue+'</td>';
			else
				str+='<td></td>';
	        str+='<td>'+results[i].fileDateAsString+'</td>';
			
		
			str +='<td><a type="button"  title="Click here to add response" href="javascript:{addNewsResponseDetails('+results[i].fileId+');}">Add Response</td>';
			
 		str+='</tr>';
           
	 }
 	str+='</table>';
	
	$('#profileManagementMainOuterDiv4').html(str);

	$(function() {
		var table = $('#newsDetailsTable').dataTable({
			"iDisplayLength": 10,
			"bPaginate": true,

		});
		table.fnPageChange(pageNumber,true);
		});

}


function addNewsResponseDetails(fileId)
{
 showNewsGallaey(fileId);
}

function addToNewsResponse()
{
  $("#errorMsgNewsDiv").html('');
  var fileIdStr = "";
  if(responseFileIdsArray.length > 0)
  {
   for(var i in responseFileIdsArray)
    fileIdStr +=''+responseFileIdsArray[i]+',';
  }
 
  var length = fileIdStr.length;
  if(length > 0)
  {
   fileIdStr = fileIdStr.substring(0,length-1);
   showNewsGallaey(fileIdStr);
  }
  else{
   
   $("#errorMsgNewsDiv").html('Please Select News').css("color","red");
  }
  
   
}

function editNewsDetails(fileId,source){



  $("#editNewsOuter").dialog({ stack: false,
							    height: 570,
								width: 720,
								position:[130,130],								
								modal: true,
								title:'Edit News Details',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#editNewsOuter").dialog();
	var str= '';
	
	
	for(var i in newsDetails){
	  if(newsDetails[i].fileId == fileId)
	  {
	    reqFile = newsDetails[i];
		
	  }
	}

	var str ='';
	
	str+='<form id="updateNewsDetailsForm" method="post" action="updateDeleteNewsAction.action" name="updateNewsDetailsForm">';
	str+='<input type="hidden" name="task" id="updateNewsDetailsId" />';
    str+='</form>';

	str += '<fieldset>';
	
	str += '<table><tr><td><div id="uploadNewsFileErrorDiv"/></td></tr></table>';
	str += '<table style="display:-moz-inline-box;">';
	str+='<tr>';
	str+='<td class="tdWidth">Select News Gallery : <font class="requiredFont">*</font></td>';
	str+='<td class="selectWidthPadd"><select style="width:222px;"  id="gallaryIdForEdit" name="gallaryId" class="selectWidth"/><option value="0">Select</option></select></td>';
	str +='<td></td>';
	str+='</tr>';
    str += '   <tr>';
	str += '       <td class="tdWidth">Title : <font class="requiredFont">*</font></td>';
	
	str +='<td>';
	
	if(source == "Eenadu Telugu")
	 str += ' <input style="font-family: eFont; font-size: 20px;" type="text" id="fileTitle" size="25" maxlength="100" />';
	else 
	 str += ' <input type="text" id="fileTitle" size="25" maxlength="100" />';

	str +='</td>';
	str += '   </tr>';
	str += '   <tr>';
    
	str += '       <td class="tdWidth">News Description : <font class="requiredFont">*</font></td>';
	str +='<td>';
	if(source == "Eenadu Telugu")
     str += '<textarea style="font-family: eFont; font-size: 20px;" id="fileDescription" cols="20" rows="3"></textarea>';
	else
	 str += '<textarea id="fileDescription" cols="20" rows="3"></textarea>';
	str +='</td>';
	
	str += '   </tr>';

    str += '   <tr>';
	str += '       <td class="tdWidth">Keywords : </td>';
	if(source == "Eenadu Telugu")
	str += '       <td class="selectWidthPadd"><input type="text"  style="font-family: eFont; font-size: 20px;" id="keywordsForEdit" name="keywords" size="25" maxlength="200" style="margin-top:8px;" value="'+reqFile.keywords+'" ></text></td></tr>';
	else
	str += '       <td class="selectWidthPadd"><input type="text" id="keywordsForEdit" name="keywords" size="25" maxlength="200" style="margin-top:8px;" value="'+reqFile.keywords+'" ></text></td></tr>';

	str += '   <TR>';
	
	
	str += ' <td class="tdWidth"><b><font>File Date : <font class="requiredFont">*</font></font></b></td>';
	str += '<TD><input type="text" id="existingFromText" readonly="true" name="fileDate" size="20" style="margin-top:8px;"/>';
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '<TD>';
	str += '<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	str += '<IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '</TD>';
	
	str += '   </TR>';


	for(var i in reqFile.fileVOList)
	{
	   str += '   <tr>';
	   str += '       <td class="tdWidth">Source : <font class="requiredFont">*</font></td>';
	   str += '       <td><select id="sourceEdit'+i+'" style="width:222px;"></select></td>';
	   str += '       <td><input type="hidden" id="sourceEditId'+i+'" value="'+reqFile.fileVOList[i].fileSourceLanguageId+'" /></td>';
	   str += '   </tr>';
	
	   str += '   <tr>';
	   str += '       <td class="tdWidth">Language : <font class="requiredFont">*</font></td>';
	   str += '       <td><select id="languageEdit'+i+'" style="width:222px;"></select></td>';
	   str += '       <td><input type="hidden" id="languageEditId'+i+'" value="'+reqFile.fileVOList[i].fileSourceLanguageId+'" /></td>';
	   str += '   </tr>';
	}
	str += '       <td class="tdWidth">Category : </td>';
	str += '  <td><select id="categoryEdit" style="width:222px;"><option value="0">-select category-</option></select></td>';
	str += '   </tr>';
	str += '   </tr>';
	str += '       <td class="tdWidth">News Importance : <font class="requiredFont">*</font></td>';
	str += '       <td><select id="newsimportanceForEdit" style="width:222px;"></select></td>';
	str += '   </tr>';

	str += '   <tr>';
	str += '      <td colspan="2"> <div id="addMoreFilesDiv" style="margin-left:107px;"></div></td>';
	str += '   </tr>';

	str += '   <tr>';
	str += '       <td></td>';

	if(reqFile.visibility == "false")
	
		str += '<td id="newsPublicRadioId"><label><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><i  class="icon-bullhorn" ></i></input></label></td>';
	else

	str += '<td id="newsPublicRadioId"><label><input type="radio" value="public" name="visibility" id="publicRadioId"><i  class="icon-bullhorn" ></i></input></label></td>';

    str += '   </tr>';
	str += '   <tr>';
	str += '       <td></td>';

	if(reqFile.visibility == "true")


	  str += '<td id="newsPrivateRadioId"><label><input type="radio" value="private" name="visibility" checked="true" id="privateRadioId"></input><i  class="icon-lock" ></i></label></td>';
   else
	 
    str += '<td id="newsPrivateRadioId"><label><input type="radio" value="private"  name="visibility" id="privateRadioId"></input><i  class="icon-lock" ></label></td>';

	str += '   </tr>';

	str +='    <tr>';
    str +='	   <td class="tdWidth">Location Scope : <font class="requiredFont">*</font></td>';
    str +='	   <td><select id="scopeDivForEdit" name="locationScope" style="width:222px;" class="selectWidth" onchange="getLocations1(this.options[this.selectedIndex].value)"  /></td>';
    str +='  </tr>';
 
	str +='  <tr>';
	str +='    <td colspan="2">';
	str +='       <div id="showScopeSubsForEdit" />'; 
	str +='    </td>';
	str +='  </tr>';

   if(reqFile.locationScope != null && reqFile.locationScope != "null"){


		str +='  <tr>';
		str +='    <td>';

       str+='<input type="hidden" id="locationValueId" value='+reqFile.location+'></input>';

		str +='       <div style="font-weight:bold;margin-left: 54px;">'+reqFile.locationValue+'</div>'; 
		str +='    </td>';
		str +='    <td>';
		str +='<input type="button" class="btn" value="Edit" onClick="getLocationDiv();"/>'; 
		str +='    </td>';
		str +='  </tr>';

   }
   str +='    <tr>';
    str +='	   <td class="tdWidth">News description in details : </td>';
	if(source == "Eenadu Telugu")
	    str +='	   <td><textarea id="newsfileDescriptionForEdit" style="font-family: eFont; font-size: 20px;" cols="20" rows="3" name="fileDescription" >'+reqFile.newsDescription+'</textarea></td>';
     else
    str +='	   <td><textarea id="newsfileDescriptionForEdit" cols="20" rows="3" name="fileDescription" >'+reqFile.newsDescription+'</textarea></td>';
    str +='  </tr>';


	str += '</table>';

	str+='<div id="newsSuccessDiv"></div>';
	str += '<div style="padding-left:223px;padding-top:10px; margin-bottom: 25px; margin-top: 11px;"> <input type="button" value="Update" class="btn btn-success highlight" onclick="updateDeleteNews(\'Update\','+fileId+');" /></div>';
	str += '</fieldset>';
	str+='</div>';

	
    
	str+='<input type="hidden" name="fileGallaryId"  id="fileGallaryId" value='+reqFile.contentId+'></input>';
	    document.getElementById("editNewsInner").innerHTML = str;
		
	    document.getElementById("fileTitle").value = reqFile.fileTitle1;
	    document.getElementById("keywords").value = reqFile.keywords;
		document.getElementById("fileDescription").value = reqFile.description;
		document.getElementById("existingFromText").value = reqFile.fileDateAsString;

		getNews("sourceEdit","getAllSourceDetails","","","","","","","","","","");
        getNews("categoryEdit","getAllCategoryDetails",reqFile.categoryId,"","","","","","","","","");
        getNews("languageEdit","getAllSourceLanguageDetails","","","","","","","","","","");
        getNews("newsimportanceForEdit","getAllNewsImportanceDetails",reqFile.newsImportanceId,"","","","","","","","","");
	
		
		//CHANGE BY SAMBA
	getCandidateGallariesForUplaod("News Gallary",reqFile.fileId);  
	getScopes1();
 }
 function getNews(task,queryType,fileType,sourceId,languegeId,categoryId,newsImportanceId,locationScope,location,title,fromDate,toDate){

    var timeST = new Date().getTime();	
var jsObj=
	      { 
		    queryType	:queryType,
			fileType	:fileType,
			sourceId	:sourceId,
            languegeId	:languegeId,
            categoryId	:categoryId,
            newsImportanceId :newsImportanceId,
            locationScope :locationScope,
            location	:location,
			fromDate	:fromDate,
			toDate		:toDate,
			title		:title,
			timeST      :timeST,
			task		:task
	       }
	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "getAllNewsForAUser.action?"+rparam;						
      callAjax1(jsObj,url);
}


function getCandidateGallariesForUplaod(contentType , fileId)
{
	var jsObj =
		{ 
			contentType : contentType,
		   	task : "candidateGallariesForUplaod"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidateGallaries.action?"+rparam;
	callnewAjax(jsObj,url);
}
 function getScopes1(){
 var jsObj =
		{ 
            time : timeST,
			task:"getScopesForNewSearch",
            divId:"scopeDivForEdit" 
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callnewAjax(jsObj,url);
 
}

function appendResults1(results , divId){


	$('#'+divId).find('option').remove();


    if(divId == "scopeDiv" || divId == "scopeDivForEdit"){
		for(var i in results){			

			$('#'+divId).append('<option value='+results[i].locationScope+'>'+results[i].locationScopeValue+'</option>');
		}

		if(reqFile.locationScope != null && reqFile.locationScope != "null")
		$('#'+divId).val(reqFile.locationScope);

	}

	if(divId == "stateDiv" || divId == "stateDivForEdit"){
		for(var i in results){

		$('#'+divId).append('<option value='+results[i].ids+'>'+results[i].names+'</option>');
	    }
	}

	if(divId == "districtDiv" || divId == "districtDivForEdit"){
		for(var i in results){

		$('#'+divId).append('<option value='+results[i].ids+'>'+results[i].names+'</option>');
	    }
	}
   
    if(divId == "constituencyDiv" || divId == "constituencyDivForEdit"){

		for(var i in results){
		$('#'+divId).append('<option value='+results[i].id+'>'+results[i].name+'</option>');
	    }

	} 

	if(divId == "mandalDiv" || divId == "mandalDivForEdit"){

for(var i in results){
		$('#'+divId).append('<option value='+results[i].id+'>'+results[i].name+'</option>');
	    }


	}

	if(divId == "villageDiv" || divId == "villageDivForEdit"){
		for(var i in results){
		$('#'+divId).append('<option value='+results[i].id+'>'+results[i].name+'</option>');
	    }

	}
	if(divId == "gallaryId" || divId == "gallaryIdForEdit"){

		for(var i in results){

		$('#'+divId).append('<option value='+results[i].id+'>'+results[i].name+'</option>');

	    }

if(divId == "gallaryIdForEdit")
	$('#gallaryIdForEdit').val(reqFile.fileGallaryId);
else
	$('#gallaryId').val(reqFile.fileGallaryId);


	}
}
 function getLocationDiv(){
	bvalue=true;
	var id =  $('#scopeDivForEdit').val();
   getLocations1(id);
}

function callAjax1(jsObj,url){
var myResults;	
var callback = {			
    success : function( o ) {
		try {												
			myResults = YAHOO.lang.JSON.parse(o.responseText);
			if(jsObj.queryType == "getAllNews"){
			     buildAllNewsDetails(myResults);
				// newsDetails = myResults;
			}
			
			 if(jsObj.queryType == "getCount")
			 {
			   showNewsCountDetails(myResults,jsObj);
			   hideImg();
			 }
			 else if(jsObj.queryType == "getNews")
			 {	
				newsDetails = myResults;
				showNewsDetails(jsObj,myResults);
				hideImg();
			 }
			else if(jsObj.queryType == "getAllSourceDetails")
			 {	
			     if(jsObj.task != "sourceEdit")
				  bildDate(myResults,jsObj.task,jsObj.fileType);
				 else
				   bildDateForSource(myResults);
			 }
			 else if(jsObj.queryType == "getAllCategoryDetails")
			 {	
				bildDate(myResults,jsObj.task,jsObj.fileType);
			 }
			 else if(jsObj.queryType == "getAllSourceLanguageDetails")
			 {	
			    if(jsObj.task != "languageEdit")
				   bildDate(myResults,jsObj.task,jsObj.fileType);
				else
				  bildDateForLanguage(myResults); 
			 }
			 else if(jsObj.queryType == "getAllNewsImportanceDetails")
			 {	
				bildDate(myResults,jsObj.task,jsObj.fileType);
			 }
			 else if(jsObj.task == "Delete")
			 {
			    newsSearch();
				showDeletedMessage(myResults);
			 }
			 else if(jsObj.task == "Update")
			 {
				returnedResults = myResults;
			    newsSearch();
				showUpdatedMessage(myResults.resultStatus);
			 }
			 else if(jsObj.type == "flagedNews" || jsObj.type == "notedNews" )
			 {
				buildFlagedAndNotedNews(myResults);
			 }
			 else if(jsObj.type == "flagedCount" || jsObj.type == "notedCount")
			 {
				buildFlagedAndNotedData(myResults,jsObj);
			 }
			 else if(jsObj.task == "createUserNewsCategory"){
			  showUserNewsCategoryStatus(myResults);
			}
			else if(jsObj.task == "assignResToCandidateOrAGallary")
			{
              showAssignNewsStatus(myResults);
			}
			else if(jsObj.task == "getAllCategories")
			{
				buildCategoriesOfUser(myResults,jsObj);
			} else if(jsObj.task =="updateCategory"){
				if(myResults.resultCode==1){
					$('#errMsg').html(myResults.message);
					$('#errMsg').css('color','red');
				}else{
					$('#errMsg').html("Updated Successfully");
					$('#errMsg').css('color','green');
				}
				$('#updateCategoryId').trigger('click');
			}
			else if(jsObj.task == "onOroffCategory")
			{
				if(myResults.resultCode==0){
					if(jsObj.name=="delete"){
						alert('Category Disabled Successfully..');
					}else{
						alert('Category Enabled Successfully..');
					}
					
					$('#updateCategoryId').trigger('click');
				}else{
					alert('Sorry Unable to Process the request..Please try again later');
				}
			}

			else if(jsObj.task == "getTotalCategories")
			{
              clearOptionsListForSelectElmtId('categoriesForGallary');
			  createOptionsForSelectElement('categoriesForGallary',myResults);
			}

			else if(jsObj.task == "getGallariesInCategory")
			{
			  clearOptionsListForSelectElmtId('gallaryId');
			  createOptionsForSelectElement('gallaryId',myResults);
			}
			else if(jsObj.task == "getGallariesInCategory1")
			{
			
			  buildGallariesForCategory(myResults,jsObj);
			 
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
function bildDateForSource(results)
 {
   for(var j in reqFile.fileVOList)
  {
    elmt = document.getElementById('sourceEdit'+j+'');
    for(var i in results)
	{
		var option = document.createElement('option');
		option.value=results[i].ids;
		option.text=results[i].names;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
	document.getElementById('sourceEdit'+j+'').value = reqFile.fileVOList[j].sourceId;
  }
}
function bildDateForLanguage(results)
 {
      for(var j in reqFile.fileVOList)
  {
    elmt = document.getElementById('languageEdit'+j+'');
    for(var i in results)
	{
		var option = document.createElement('option');
		option.value=results[i].ids;
		option.text=results[i].names;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
	document.getElementById('languageEdit'+j+'').value = reqFile.fileVOList[j].languegeId;
  }
 }
 function bildDate(optionsList,elmtId,val){
   var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].ids;
		option.text=optionsList[i].names;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
	if(elmtId == "sourceEdit")
	{
	  document.getElementById("sourceEdit").value = val;
	}
	else if(elmtId == "categoryEdit")
	{
	  document.getElementById("categoryEdit").value = val;
	}
	else if(elmtId == "languageEdit")
	{
	  document.getElementById("languageEdit").value = val;
	}
	else if(elmtId == "newsimportance")
	{
	  document.getElementById("newsimportance").value = val;
	}else if (elmtId == "newsimportanceForEdit")
	{
		 document.getElementById("newsimportanceForEdit").value = val;
	}
}

function getStatesForLocationScope1()
{
  var jsObj =
		{ 
            time : timeST,
			task:"getStates",
            type:"forEdit"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callnewAjax(jsObj,url);
}

function getStatesForLocationScope()
{
  var jsObj =
		{ 
            time : timeST,
			task:"getStates"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callnewAjax(jsObj,url);
}

 function  hideDialog()
 {
	
	 $("#editNewsOuter").dialog('close');
 }
function getCandidates(){
  
 var jsObj =
		{ 
			task:"getCandidates"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getNewsContainedCandidates.action?"+rparam;						
	callnewAjax(jsObj,url);
 
}
function getCandidatesofUser(){
  
 var jsObj =
		{ 
			task:"getCandidatesOfAUser"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidatesOfAUser.action?"+rparam;						
	callAjax(jsObj,url);
 
}
 function buildCandidatesOfAUser(results)
 {
   $('#list1').find('option').remove();
   $.each(results,function(index,value){
	   $('#list1').append('<option value="'+results[index].id+'">'+results[index].name+'</option>');

   })
 }

function buildCandidates(results)
 {
   $('#candidatesList').find('option').remove();

   $(' #candidatesList').append('<option value="">Select Candidate</option>');
   $.each(results,function(index,value){
	   $(' #candidatesList').append('<option value="'+results[index].id+'">'+results[index].name+'</option>');

   })

 }


 function uploadNewsFromPartyPage1()
{

	if(validateNewsFileUpload1())
	{
		disableButton('uploadNewsBtnId');
		var newsprivateRadioId = document.getElementById('newsprivateRadioId').checked;
			if(newsprivateRadioId == true)
		document.getElementById('newsprivateRadioId').checked = 'true';
			if(newsprivateRadioId == false)
		document.getElementById('newsPublicRadioId').checked = 'true';


	$('.destinationKeywords').each(function() {
		var key = $(this).attr("key");	
		var str = '';
			var $ul = $(this).closest('ul');
			  $ul.find('li').each(function(){
			  str +=''+$(this).text()+',';
			});	
		  var length = str.length;
		  if(length > 0)
		   str = str.substring(0,length-2);
		
           $("#"+key+"Hidden").val(str);
        });
  

		var uploadHandler = {
					upload: function(o) {
						uploadResult = o.responseText;
						showNewsUploadStatus1(uploadResult);	
						enableButton('uploadNewsBtnId');
					}
				};

			
			YAHOO.util.Connect.setForm('uploadForm1',true);
			YAHOO.util.Connect.asyncRequest('POST','editUploadedFilesForPartyAndCandidatesKeywords.action',uploadHandler);
	}
	else
		return;
}


 function addMoreFiles1(value,text)
{
	var str ='';
	str+='<div id="'+value+'"  class="div'+value+'">';
	str+="<u><span style='font-weight:bold;'>Upload "+text+" Files</span></u>";
	str +='<table style="background:#e3e3e3;border-radius:9px;padding:5px;margin-top:12px;" id="moreFileTableId0'+value+'">';

    str +='<tr><td class="tdWidth1" style="width:97px;">Image To Display : </td><td><input type="file" name="imageForDisplay" id="ImagenewsfileId" size="25"  class="newsFile" style="width: 225px;"/></td></tr>';

	str += ' <tr>';
	str +='<td>File Path</td>';
	str += ' <td class="selectWidthPadd"><input type="file" name="userImage" id="newsFileId'+value+'" size="25" class="newsFile" style="width: 225px;"/></td>';
	str += ' </tr>';
	str += ' <tr>';
	str += ' <td class="tdWidth1">Source : <font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="filesourceId'+value+'" name="fileSourceId" style="width:175px;"><option value="0">Select Source</option><option value="1">Eenadu</option><option value="2">Sakshi</option></select></td>';
	str += ' </tr>';
	str += ' <tr>';
	str += ' <td class="tdWidth1">Language : <font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="sourceLangId'+value+'" name="sourceLanguageId" style="width:175px;"></select></td>';
	
	str += ' </tr>';
	str += '       <td class="tdWidth1">Edition : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="newsedition'+value+'" name="newsedition" style="width:175px;margin-top:8px;"><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Page Number : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="pageno'+value+'" name="pageno" class="pageno pagenoCls" size="25" maxlength="200" style="margin-top:8px;" onKeyup="IsNumeric(this.value);"></input></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Length : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="newslength'+value+'" class="newslength newsLengthCls" name="newslength" size="25" maxlength="200" style="margin-top:8px;" onKeyup="IsNumeric1(this.value);"></input></td>';
	str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/plus.png" title="Click here to add more file" onclick="addMoreFiles2('+value+')"></td>';
	str += '   </tr>';
	str+='<input type="hidden" name="filesList" value="'+value+'"/>';
	str +='</table>';
	
	str+='</div>';
	$('#uploadFilesDiv').append(str);
	getSource("filesourceId"+value+"");
	getLanguage('sourceLangId'+value+'');
} 
var divCount = 1;
function addMoreFiles2(value)
{
	var str ='';
	str +='<table class="moreFileTable" style="background:#e3e3e3;border-radius:9px;padding:5px;margin-top:12px;" id="moreFileTableId'+divCount+''+value+'">';
	str += ' <tr>';
	str +='<td>File Path</td>';
	str += ' <td class="selectWidthPadd"><input type="file" name="userImage" id="newsFileId'+value+'" size="25"  class="newsFile" style="width: 225px;"/></td>';
	str += ' </tr>';
	str += ' <tr>';
	str += ' <td class="tdWidth1">Source : <font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="filesourceId'+divCount+''+value+'" name="fileSourceId" style="width:175px;" class="source'+value+'" ></select></td>';
	str += ' </tr>';
	str += ' <tr>';
	str += ' <td class="tdWidth1">Language : <font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="sourceLangId'+divCount+''+value+'" name="sourceLanguageId" style="width:175px;" class="lang'+value+'"></select></td>';
	
	str += ' </tr>';
	str += '       <td class="tdWidth1">Edition : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="newsedition'+value+'" name="newsedition" style="width:175px;margin-top:8px;"><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Page Number : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="pageno'+value+'" name="pageno" class="pageno pagenoCls" size="25" maxlength="200" style="margin-top:8px;" onKeyup="IsNumeric(this.value);"></input></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Length : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="newslength'+value+'" class="newslength newsLengthCls" name="newslength" size="25" maxlength="200" style="margin-top:8px;" onKeyup="IsNumeric1(this.value);"></input></td>';
	
	str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteFileDiv(this)"></td>';
	str += '   </tr>';
	str+='<input type="hidden" name="filesList" value="'+value+'"/>';
	str +='</table>';
	$('#'+value+'').append(str);
	getSource1("filesourceId"+divCount+value);
	getLanguage1("sourceLangId"+divCount+value);
	divCount ++;

} 
function deleteFileDiv(element)
{
	$($(element)).closest('table').remove();
}
function getSource1(selectOptionId){
	$.each(sourceObj,function(index,value){
		$('#'+selectOptionId).append('<option value="'+sourceObj[index].id+'">'+sourceObj[index].name+'</option>');
	});
}
function getLanguage1(fillOptionId)
{
	$.each(languagesObj,function(index,value){
		$('#'+fillOptionId).append('<option value="'+languagesObj[index].id+'">'+languagesObj[index].name+'</option>');

	});
}

function  buildUploadNews()
{
   var tempPartyId = 872;
   var str ='';
	str+='<div id="content" style="width:650px;">';

	str += '<form name="uploadForm" action="uploadFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadNewsForm">';
	str += '<h2 align="center">Upload A News</h2>';
	str += '<table class="aligncenter"><tr><td><div id="uploadNewsFileErrorDiv1" /></td></tr></table>';
	str += '<table class="aligncenter" style="margin-left:123px;left:50%;">';
	
    
	//keyworda Start
	str +='<tr>';
	str += '       <td class="tdWidth1">Keyword : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd multiKeywordList"><input type="text" name="keywordList" id="keywordListId1"/></td>';
	str +='</tr>';

    str +='<tr>';
	str += '       <td class="tdWidth1"></td>';
	str += '  <td class="selectWidthPadd"><div style="margin-top: 2px; margin-bottom: 14px;">After entering the keyword comma(,) is mandatory</div></td>';
	str +='</tr>';

    str += '   <tr>';
	str += '       <td class="tdWidth1">Title : <font class="requiredFont">*</font><b></td>';
	str += '       <td class="selectWidthPadd"><input type="text" id="newsfileTitle" name="fileTitle" size="25" maxlength="160" ></input></td>'; 
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Description : <font class="requiredFont">*</font></td>';
	str += '       <td class="selectWidthPadd"><textarea id="newsfileDescription" cols="20" rows="3" name="fileDescription" maxlength="330"></textarea></td>';
	str += '   </tr>';
	
	str += '<TR>';
	str += ' <td><b><font>File Date<font class="requiredFont">*</font></font></b></td>';
	str += '<TD style="padding-right: 31px;"><input type="text" id="existingFromTextNews" class="dateField" readonly="true" name="fileDate" size="20" />';
	
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '<TD>';
	str += '<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	//str += '<IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '</TD>';
	str += '</TR>';
	str	+= '<tr>';
	str += '       <td class="tdWidth1">Source : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="source" name="fileSourceId" ><option value="0">Select Source</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Language : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="language" name="sourceLanguageId" ><option value="0">Select Language</option></select></td>';
	
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Importance : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="newsimportance" name="newsimportance" ><option value="0">Select NewsImportance</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Edition : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="newsedition" name="newsedition" ><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Page Number : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="pageno" name="pageno" size="25" class="pageno pagenoCls" maxlength="200" onKeyup="IsNumeric(this.value);"></input></td>';
	str += '    <td id="Err4Numer">Only Numbers</td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Length : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="newslength" name="newslength" class="newslength newsLengthCls" size="25" maxlength="200" onKeyup="IsNumeric1(this.value);"></input></td>';
	str += '   <td id="Err4Numer1">Only Numbers</td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">File Path : <font class="requiredFont">*</font></td>';
	str += '       <td class="selectWidthPadd"><input type="file" name="userImage" id="newsfileId" size="25" style="margin-top:8px;" class="newsFile" style="width: 225px;"/></td>';
	str += '       <td class="selectWidthPadd"><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMoreFiles()" title="Click here to add more images" alt="Click here to add more images"/></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '      <td colspan="2"> <div id="addMoreFilesDiv"></div></td>';
	str += '   </tr>';
	str += '   </tr>';

    str +='<tr><td class="tdWidth1" style="width:97px;">Image To Display : </td><td><input type="file" name="imageForDisplay" id="ImagenewsfileId" size="25" class="newsFile" style="width: 225px;"/></td></tr>';

	str += '   <tr>';
	str += '       <td></td>';
	str += ' <td id="newsPublicRadioDiv"><label class="radio"><input type="radio" value="public" name="visibility" id="newsPublicRadioId" checked="true"><b><font id="newsfontDiv" >Visible to Public Also</font></b></input></label></td>';
    str += '   </tr>';
	str += '   <tr>';
	str += '       <td></td>';
	str += '       <td id="newsprivateRadioDiv"><label class="radio"><input type="radio" value="private" name="visibility" id="newsprivateRadioId"><b><font>Make This Private</font></b></input></label></td>';
	str += '   </tr>';
	str +='    <tr>';
    str +='	   <td class="tdWidth1">Location Scope : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="scopeDiv" name="locationScope" onchange="getLocations(this.options[this.selectedIndex].value)"  /></td>';
    str +='  </tr>';
	str +='  <tr>';
	str +='    <td colspan="2">';
	str +='       <div id="showScopeSubs" />'; 
	str +='    </td>';
	str +='  </tr>';
	str +='<tr>';
	str +='<td class="tdWidth1">News description in details : </td>';
	str +='<td><textarea id="newsDescriptionId" name="newsDescription" rows="3" cols="20" id="newsDesc"></textarea></td>';
	str +='</tr>';
	str += '</table>';

	str +='<input type="hidden" name="profileType" value="party_profile">';
	str +='<input type="hidden" name="profileId" value="'+tempPartyId+'">';
	str +='<input type="hidden" name="profileGalleryType" value="news_gallery">';
	str+='<div id="otherProNewsDiv" style="margin: 10px;"></div>'; 
	str+='<div id="uploadnewsgalAjax" style="display:none;margin-left:430px;clear:both;"><img src="images/search.jpg"/></div>';
	str += '<table class="aligncenter"><tr><td><input id="uploadNewsBtnId" type="button" class="btn btn-success " value="Upload News" style="background-color:#57B731" onClick="uploadNewsFromPartyPage()"></td><td><input id="uploadNewsBtnId" type="button" class="btn btn-success highlight" value="Cancel"  onClick="clearDiv(\'newsGallaryDiv\');"></td></tr></table>';

    str +='<input type="hidden" id="keywordListId" name="keywordList"/>';

	str += '</form>';
	str+='</div>';
	document.getElementById("newsGallaryDiv").innerHTML = str;

	 getScopes();
	 getSource("source");
	 getLanguage("language");
	 getNewsImportance();
	 getScopeForUser();

   //keywords
   $("#keywordListId1").autoSuggest("getKeyWordsBySearchCriteria.action", {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});



	 $(document).ready(function(){
	 	$("#source").change(function () {
	
var str = "";
str = $("#source option:selected").text();
//alert(str)

if(/Eenadu Telugu/i.test(str))
{
if(!$('#newsfileTitle').hasClass('enadu'))
{
$('#newsfileTitle').addClass('enadu');
$('#newsfileDescription').addClass('enadu');
$('#keywords').addClass('enadu');
$('#newsDescriptionId').addClass('enadu');
}
}else{
if($('#newsfileTitle').hasClass('enadu'))
{
$('#newsfileTitle').removeClass('enadu');
$('#newsfileDescription').removeClass('enadu');
$('#keywords').removeClass('enadu');
$('#newsDescriptionId').removeClass('enadu');
}
}
});
});
}

function getCandidatesOfSelectedParty(partyId)
{
    $('#list1').find('option').remove();
	$('#candidateAjaxImg').show();
	
		var jsObj = {
			partyId :partyId,
			task : "getCandidatesOfAParty"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidatesOfAParty.action?"+rparam;					
	
callAjax(jsObj,url);
}

function buildPartyCandidates(results)
{
	$.each(results,function(index , value){
		$('#list1').append('<option value="'+value.id+'">'+value.name+'</option>');

	});
}
function showUserNewsCategoryStatus(results)
{
	
  if(results.resultCode == 0 && results.message != null)
  {
	$("#userNewsCategory").val('');
	$("#errorMsgDiv").html(''+results.message+'').css('color','green');
	return;
  }
  else if(results.resultCode == 0 && results.message == null)
  {
	$("#userNewsCategory").val('');
	$("#errorMsgDiv").html('News Category Created Successfully..').css('color','green');
	return;
  }
  $("#errorMsgDiv").html('Error Occured! Try Again..').css('color','red');
	return;
}

function updateExistingKeyword()
{
var checkedgallariesArr = [];
var unCheckedgallariesArr = [];
var keywordCategory = $("#keywordCategory1").val();
var keywordErrorMsgDivId = document.getElementById("keywordErrorMsgDivId1");
 var selectedkeyWord = $("#keywords").val();
	$(".gallary1").each(function() {
		 if($(this).is(":checked"))
		 checkedgallariesArr.push($(this).val());
		 else
		 unCheckedgallariesArr.push($(this).val());
	  
    });
	var flag =false;
	 var str='<font color="red">';
	
	if(keywordCategory == 0)
	{
	str +='Select Category<br/>';
		flag =true;
	}
	else if(checkedgallariesArr == "")
	{
		str +='Select atleast one Gallary<br/>';
		flag =true;
	}
	else if(selectedkeyWord == 0)
	{
		str +='Select keyWord<br/>';
		flag =true;
	}
	if(flag == true)
	{
	keywordErrorMsgDivId.innerHTML = str;
	return;
	}
	else
	keywordErrorMsgDivId.innerHTML = '';
    $("#keywordAjaxImg1").css("display","inline-block");
		var jsObj = {
			checkedgallariesArr:checkedgallariesArr,
			unCheckedgallariesArr:unCheckedgallariesArr,
			keyWord:selectedkeyWord,
			task: 'updatexistingKeyword',
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "updateGallaryKeywordAction.action?"+rparam;
	callnewAjax(jsObj,url);
	
}
function buildKeyWords(result,jobj)
{
	
	var str='';
	var divEle = document.getElementById("keywordsDiv");
	if(result.length > 0)
	{
	str+=' <label><strong>Select Multiple Keywords and Map to Gallary</strong></label>';
	for(var i in result)
	{
	str+='<label class="label label-success "><input type="checkbox" value='+result[i].id+' id='+result[i].id+' class="keywords" />&nbsp;'+result[i].name+'</label>&nbsp;';
	}
	}
		divEle.innerHTML = str;
}
function buildGallariesForCategory(result,jobj)
{
    var divId = jobj.selEle;
	var str='';
	var divEle1 = '';
	if(divId == "keywordCategory")
    divEle1 = document.getElementById("categorygallary");
	else
	 divEle1 = document.getElementById("categorygallary1");
	if(result.length > 0)
	{
	str+=' <label><strong>Select Multiple Gallaries</strong></label>';
	for(var i in result)
	{
		if(divId == "keywordCategory")
	str+='<label class="label label-success"> <input type="checkbox" value='+result[i].id+' id='+result[i].id+' class="gallary"/> &nbsp;' +result[i].name+ '</label>&nbsp;';
	else
		{
	
	if($.inArray(result[i].id, keywordGallaries) != -1 )
	str+='<label class="label label-important"> <input type="checkbox" value='+result[i].id+' id='+result[i].id+' class="gallary1" checked="checked"/>&nbsp;' +result[i].name+ '</label>&nbsp;';
	else
	str+='<label class="label label-important"> <input type="checkbox" value='+result[i].id+' id='+result[i].id+' class="gallary1"/> &nbsp;' +result[i].name+ '</label>&nbsp;';
		}
	}
	}
		divEle1.innerHTML = str;
}
function showStatusForupdateGallaryKeyword(result)
{
	$("#keywordAjaxImg").css("display","none");
	if(result.resultCode == 0)
	{
	 $("#keywordErrorMsgDivId").html('Updated successfully..').css('color','green');
	 getKeyWords();
	}
	else
	$("#keywordErrorMsgDivId").html('Error Occured! Try Again..').css('color','red');
  return;
}
function showStatusForupdateGallaryKeyword1(result)
{
	$("#keywordAjaxImg1").css("display","none");
	if(result.resultCode == 0)
	{
	 $("#keywordErrorMsgDivId1").html('Updated successfully..').css('color','green');
	
	}
	else
	$("#keywordErrorMsgDivId1").html('Error Occured! Try Again..').css('color','red');
  return;
}

function getGallaryId()
{
	var keyword = $("#keywords option:selected").val();
	var jsObj = {
			keyword:keyword,
			task: 'getGallaries',
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getGallariesAction.action?"+rparam;
	callnewAjax(jsObj,url);
}

function getNewsReports ()
{

  $("#newsAssignGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").html('');
  $("#profileManagementMainOuterDiv4").css("display","none");
  $("#profileManagementHeaderDiv2").css("display","none");
  $("#profileManagementMainOuterDiv3").css("display","none");
  $("#profileManagementHeaderDiv3").css("display","none");
  $("#videoGallaryDiv").css("display","none");
  $("#dateSelectDiv").css("display","none");
  $("#profileManagementMainOuterDiv5").css("display","none");
  $("#profileManagementHeaderDiv5").css("display","none");
  $("#profileManagementMainOuterDiv6").css("display","none");
  $("#profileManagementHeaderDiv6").css("display","none");
  $("#profileManagementMainOuterDiv7").css("display","block");
   $("#profileManagementHeaderDiv7").css("display","none");
   $("#profileManagementMainOuterDiv8").css("display","none");
   $("#newDesignationDiv").css("display","none");
  $("#newPartyCreationDiv").css("display","none");
  		$('#statusDiv1').html('');
		$('#statusDiv2').html('');
    var str ='';
	str+='<div class="container well">';
	str+='<div class="row clearfix">';
	str+='<div class="row-fluid"><div class="span12 well-small " id="reportsDiv">';
	str+=' </div>';
    str+='</div>';
	str+='</div>';
	str+='</div>';
	document.getElementById("newsReportsMainDiv").innerHTML = str;

	getNewsReport();
}
function getReports()
{
var jsObj = {
			
			task: 'getAllNewsReports',
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getAllNewsReportsForAUser.action?"+rparam;
	callnewAjax(jsObj,url);
}

function buildAllNewsReports(result)
{
	var str='';
	str+='<h2 style="text-align:center;">News Reports</h2>';
	
	if(result==null || result=='')
	{
	str+='<span style="text-align:center;">There are no News reports available</span>';
	$("#reportsDiv").html(str);
	return;
	}
	else
	
	str+='<table  class="table table-striped">';
	str+='<tr>';
	str+='<th>NewsReport</th>';
	str+='<th>Created Date</th>';
	str+='<th>View Report</th>';
	str+='<th>Generate Url For Creating Pdf</th>';
	str+='</tr>';
	for(var i in result)
	{
	str+='<tr>';
	str+='<td>' +result[i].description+'</td>';
	str+='<td>' +result[i].identifiedDateOn+'</td>';
	str+='<td><input id="reportFiles" class="btn btn-info" type="button" onclick="getReportFiles('+result[i].newsImportanceId+')" value="View"></td>';
	str+='<td><input  class="btn btn-info" type="button" onclick="generateKey('+result[i].newsImportanceId+',\'generatedUrl'+i+'\')" value="Generate Url"></td>';
	str+='<td><textarea id="generatedUrl'+i+'"></textarea></td>';
	str+='</tr>';
	}
	str+='</table>';
	$("#reportsDiv").html(str);
}

function getMainCategories()
{
var jsObj={
	
		task:'getMainCategories'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidateRelatedGallariesAction.action?"+rparam;
	callAjax(jsObj, url);


}
var candidatesList =[];
function buildCandidatesForKeywords(results,type)
{
	candidatesList = new Array();
             $("#"+type+" option").remove();
			 $('#'+type).append('<option value="0">Select Candidate</option>');
            $.each(results,function(index , value){
		$('#'+type).append('<option value="'+value.id+'">'+value.name+'</option>');
		candidatesList.push(value.id);

	});
 
}

function showCandidateSaveStatus(result,jsObj)
{
  $("#errorMsgDiv").html('');

  if(result == null || result.resultCode == 1)
  {
   $("#errorMsgDiv").html('Error occured! try again.').css("color","red");
   return;
  }

  else if(result.resultCode == 0 && result.message != null)
  {
   $("#errorMsgDiv").html('Candidate Saved Successfully.').css("color","green");
   setTimeout(function(){$("#errorMsgDiv").html('').css("color","red");},3000); 
   $("#newCandidateName").val('');
   $("#partySelectNewList").val(0);
   $("#designationsList").val(0);
   $("#locationId").val(0);
   $('#pcConstituencyRow').hide();
   $('#acConstituencyRow').hide();
   var partyId = $("#"+jsObj.partyListId+"").val();
   
   if(partyId != null && partyId > 0)
     getCandidatesListByPartyId(partyId,""+jsObj.candidateListId+"");

   return;
  }
  else{
   $("#errorMsgDiv").html('Candidate is already exist.').css("color","green");
   
   return;
  }
  
}

function  getBenefitList()
{
  var jsObj = {
			 task : "getBenefitList"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getBenefitListAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function buildBenefitsList(result)
{

  $("#sourceBenfitsDiv").html('');
  var str = '';
  str +='<label>Benefits</label>';
  if(result != null && result.length > 0)
   for(var i in result)
   {
    str +='<label class="btn">';
	str +='<input type="checkbox" class="checkbox" name="sourceBenefitId" value="'+result[i].id+'">';
	str +=''+result[i].name+'</label>';
   }
  $("#sourceBenfitsDiv").html(str);
}

function getCandidatePartyBenefitsDiv()
{
  $("#candidatePartyBenefitsDiv").dialog({ stack: false,
							    height: 530,
								width: 900,
								position:[130,130],								
								modal: true,
								title:'News Details',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#candidatePartyBenefitsDiv").dialog();
}


function getPartiesList(partySelectBoxId,partiesListForWhome)
{
 var jsObj={
		partySelectBoxId:partySelectBoxId,
		partiesListForWhome:partiesListForWhome,
		task:'getPartyList'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getPartiesListAction.action?"+rparam;
	callAjax(jsObj, url);
}

function buildPartyList(results,jsObj)
{
//debugger;
  $("#"+jsObj.partySelectBoxId+"").find('option').remove();
  $("#"+jsObj.partySelectBoxId+"").append('<option value="0">Select Party</option>');
  
  if(jsObj.partiesListForWhome != null)
  {
   $("#"+jsObj.partiesListForWhome+"").find('option').remove();
   $("#"+jsObj.partiesListForWhome+"").append('<option value="0">Select Party</option>');
  }
  
  if(results != null)
  {
   for(var i in results)
    $("#"+jsObj.partySelectBoxId+"").append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
  
   if(jsObj.partiesListForWhome != null)
	for(var i in results)
    $("#"+jsObj.partiesListForWhome+"").append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
  }
}

function builddesignationsList(results,jsObj)
{

  $("#"+jsObj.designationList+"").find('option').remove();
  $("#"+jsObj.designationList+"").append('<option value="0">Select Designation</option>');

  if(results != null && results.length > 0)
  {
    for(var i in results)
	 $("#"+jsObj.designationList+"").append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
  }

}

function getKeywordList(){
 $.ajax({
    	type: "GET",
		url: "getKeywordsListAction.action",
		data: { task:"getKeywordsList" }
		})
		.done(function( result ) {
		if(result != null && result.length > 0)
		{
		 keywordsArray = new Array();
		 $.each(result,function(index,keywords){
			var obj = {value: keywords.id,
			name:''+keywords.name+''}
			keywordsArray.push(obj);
		});
		 var data = {items:keywordsArray};
		 $("#keywordDiv").html('');
		 $("#keywordDiv").html('<input type="text" class="input-block-level keyword0 destinationKeywords" key="keywordId0" id="keywordId">');
		 
         $("#keywordId").autoSuggest(data.items, {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});		 
		}
		
		});
}


function buildSearchDiv(divId1,elmtId){

var divSourceId;
var divId = divId1; 
if(divId1 != 11 && divId1 != 22){
divSourceId = (divId1 + "").charAt(0);
divId = (divId1 + "").charAt(1);

}
$("#errDiv"+divId1+"").html('');
	var partyName = '';
	var partyId =0;
	var elemtValue = elmtId;
	if(elmtId ==0){
	elmtId ="";
	}

if(divId == 11){
	 partyName = $("#partiesList0 :selected").text();
	 partyId = $("#partiesList0").val();
	if($('#partiesList0').val() ==0){
	$("#errDiv"+divId+"").html('Please select party.');
	return;
	}
}
else if(divId == 22){
	partyName = $("#partiesListForWhome0 :selected").text();
	partyId = $("#partiesListForWhome0").val();
	 
	if(partyId ==0){
	$("#errDiv"+divId+"").html('Please select party.');
	return;
	}
}

else if(divSourceId == 4){
	partyName = $("#partiesListForWhome"+elmtId+" :selected").text();
	partyId = $("#partiesListForWhome"+elmtId+"").val();
	 
	if(partyId ==0){
	$("#errDiv4"+divId+"").html('Please select party.');
	return;
	}
}

else if(divSourceId == 3){

	partyName = $("#partiesList"+elmtId+" :selected").text();
	partyId = $("#partiesList"+elmtId+"").val();
	 
	if(partyId ==0){
	$("#errDiv3"+divId+"").html('Please select party.');
	return;
	}
}
	$("#searchDiv").dialog({
            modal: true,
            title: "<b>Search Candidate</b>",
			width: 500,
            height: 300
           
        });
		
	$("#searchDiv").append("<div id='searchInnerDiv' align='center'></div>")
	$('#searchInnerDiv').html('');
	var str='';
	str +='<div id="searchErrDiv'+divId+'" style="font-weight:bold;color:red;"></div>';
	str +='<table>';
	str +='<tr>';
	str +='<td>Party Name : </td>';
	str +='<td>'+partyName+' </td>';
	str +='</tr>';
	str +='<tr>'
	str +='<td>Designation : </td>';
	str +='<td><select id="designationsList'+divId+'"></select></td>';
	str +='</tr>'
	str +='<tr>'	
	str +='<td style="width: 160px;"> <a class="btn btn-info" id="searchId'+divId+'" href="javascript:{showORhideSearchOptions('+divId+')}" >Advanced Search</a></td>'	
	str +='<td> <select id="locationList'+divId+'" onchange="buildLocationDiv('+divId+');">';
	str +='<option value="0"> Select Location</option>';
	str +='<option value="1">District</option>';
	str +='<option value="2">Assembly Constituency</option>';
	str +='<option value="3">Parliament Constituency</option>';
	str+='</select></td>'	
	str +='</tr>'
	str +='</table>'
	
	str +='<div id="distiDiv'+divId+'" style="display:none;"><table>'
	str +='<tr>'
	str +='<td style="width: 160px;"> District Name:</td>'
	str +='<td> <select id="districtId'+divId+'" ></select> </td>'
	str +='</tr>'	
	str +='</table></div>'	
	
	str +='<div id="assConstiDiv'+divId+'" style="display:none;"> <table>'
	str +='<tr>'
	str +='<td style="width: 160px;"> Constituency Name:</td>'
	str +='<td> <select id="assemConstId'+divId+'" ></select> </td>'
	str +='</tr>'	
	str +='</table> </div>'	
	
	str +='<div id="parliConstiDiv'+divId+'" style="display:none;"><table>'
	str +='<tr>'
	str +='<td style="width: 160px;"> Constituency Name:</td>'
	str +='<td> <select id="parlConstId'+divId+'" ></select> </td>'
	str +='</tr>'	
	str +='</table> </div>'
	
	str +='<table>'
	str +='<tr>'
	str +='<td style="width: 160px;">Candidate Name : </td>';
	str +='<td><input type="text" id="candidateId'+divId+'" name="candidateName" onkeyUp="getCandidateNames('+divId+','+partyId+');"/></td>';
	str +='</tr>'
	str +='</table>';
	


if(divSourceId == 3)
	str +='<button class="btn btn-success" onclick="updateDetails(3'+divId+','+elemtValue+');"> Ok </button>';
else if(divSourceId == 4)
	str +='<button class="btn btn-success" onclick="updateDetails(4'+divId+','+elemtValue+');"> Ok </button>';
else
	str +='<button class="btn btn-success" onclick="updateDetails('+divId+','+elemtValue+');"> Ok </button>';
	$('#searchInnerDiv').html(str);	
	
	$("#locationList"+divId+"").attr("disabled","disabled");
    getDesignationList("designationsList"+divId);
}

var candidates = {};
function getCandidateNames(divId,partyId){

var content = $("#candidateId"+divId+"").val();
var designationId = $("#designationsList"+divId+"").val();
var locationTypeId = $("#locationList"+divId+"").val();
var locationType = '';
var locationId = 0;
$("#searchErrDiv"+divId+"").html('');
if(designationId ==0){
	$("#searchErrDiv"+divId+"").html('Please select designation.');
	return;
}

if(counts != 1){
locationType = $("#locationList"+divId+" option:selected").text();
	if(designationId ==0){
	$("#searchErrDiv"+divId+"").html('Please select Location.');
	return;
	}
	if(locationTypeId == 1)
		locationId = $("#districtId"+divId+"").val();
		
	else if(locationTypeId == 2)
		locationId = $("#assemConstId"+divId+"").val();
		
	else if(locationTypeId == 3)
		locationId = $("#parlConstId"+divId+"").val();
		
}

 $.ajax({
     type: "POST",
     url: "filterCandidateNames.action",
     data: {  
		 partyId:partyId, 
		 designationId:designationId,
		 locationType:locationType,
		 locationId:locationId,
		 content:content,
		 task:"filterCandidateNames" 
		}
    })
    .done(function( result ) {
	$("#searchErrDiv"+divId+"").html('');
	 var candidatesList = new Array();
	 if(result.length > 0)	 
		 for(var i in result)
			candidatesList.push(result[i].name);
			 for(var i in result)
			 {
			 candidates[result[i].name] = result[i].id;
			 }
	 $("#candidateId"+divId+"").autocomplete({
        source:candidatesList
        });
	 });
}

function updateDetails(divId1,elmntId){

var divSourceId;
var divId = divId1; 
if(divId1 != 11 && divId1 != 22){
divSourceId = (divId1 + "").charAt(0);
divId = (divId1 + "").charAt(1);
}
var candiId = $("#candidateId"+divId+"").val();

var id = candidates[candiId];

	if(candidatesList.indexOf(id) == -1){

		if(divId == 11){
			$('#candidateListForParty0').append('<option value="' + id + '">' + candiId + '</option>');
		}
		else if(divId == 22){
			$("#candidateListForPartyForNewsTo0").append('<option value="' + id + '">' + candiId + '</option>');
		}

		else if(divSourceId == 3){
			$("#candidateListForParty"+elmntId+"").append('<option value="' + id + '">' + candiId + '</option>');
		}

		else if(divSourceId == 4){
			$("#candidateListForPartyForNewsTo"+elmntId+"").append('<option value="' + id + '">' + candiId + '</option>');
		}

	}


	if(divId == 11){
	$("#candidateListForParty0").val(id);
	}
	else if(divId == 22){
		$("#candidateListForPartyForNewsTo0").val(id);
	}

	else if(divSourceId == 3){
	$("#candidateListForParty"+elmntId+"").val(id);
	}

	else if(divSourceId == 4){
		$("#candidateListForPartyForNewsTo"+elmntId+"").val(id);
	}

$('#searchDiv').dialog('close');

}

function buildLocationDiv(divId){
var locationType = $("#locationList"+divId).val();

	if(locationType == 1){
		$("#distiDiv"+divId+"").css("display","block");
		$("#assConstiDiv"+divId+"").css("display","none");
		$("#parliConstiDiv"+divId+"").css("display","none");
		buildSelectBox("#districtId"+divId+"",districtsArr,"district");
	}
	else if(locationType == 2){
		$("#assConstiDiv"+divId+"").css("display","block");
		$("#distiDiv"+divId+"").css("display","none");
		$("#parliConstiDiv"+divId+"").css("display","none");
		buildSelectBox("#assemConstId"+divId+"",assemblyConstiArr,"Constituency");
	}
	else if(locationType == 3){
		$("#parliConstiDiv"+divId+"").css("display","block");
		$("#distiDiv"+divId+"").css("display","none");
		$("#assConstiDiv"+divId+"").css("display","none");
		buildSelectBox("#parlConstId"+divId+"",parliamentConstiArr,"Constituency");
	}
}

function buildSelectBox(elmtId,result,location){
 $(""+elmtId+"").find("option").remove();
 $(""+elmtId+"").append("<option value='0'> Select "+location+"</option>");
 $.each( result, function( index, myResult ){
    $(""+elmtId+"").append("<option value="+myResult.id+">"+myResult.name+"</option>");
}); 


}

var counts = 1;
function showORhideSearchOptions(divId){
	if(counts == 1){
	$("#locationList"+divId+"").attr("disabled",false);
	counts = counts-1;
	}
	else{
	$("#locationList"+divId+"").attr("disabled","disabled");
	counts = counts+1;
	}
	
}
function deleteFileFromNewsReport(fileId)
{
	
	var confirmFile = confirm('Do you want to delete file');
	if(confirmFile)
	{
	var jsObj={
		fileId:fileId,
		task:'deleteFile'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "deleteNewsAction.action?"+rparam;
	callAjax(jsObj, url);
	}
}
function deleteFile(fileId)
{
	var jsObj={
		fileId:fileId,
		task:'deleteFile'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "deleteNewsAction.action?"+rparam;
	callAjax(jsObj, url);
}

function editFile(id){

  var browser1 = window.open("editNewsAction.action?fileId="+id,"EditNewsWindow","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
}

var addSource = 0;
function addNewFileSource(){

        addSource = parseInt(addSource)+1;
        var str ='';
        str+='<div id="newfilesourceremov'+addSource+'" class="row-fluid ">';
		str+='    <div class="span12">';
		str+='        <div class="row-fluid">';
		str+='<div class="container " style="margin-left: -1px;" align="left">';
		str+='        <div class="span4" style="width: 275px;margin-left: 12px;">';
		str+='        <label><strong>File Path</strong></label>';
			   
		str+='<br/><input type="file" name="fileSourceVOList['+addSource+'].sourceFileList[0].fileImage" class="btn fileImgCls newsFile0" key="'+addSource+'aaanewsfileDescription" id="fileDescription'+addSource+'" style="width: 245px;"/> <span class="icon-remove" style="cursor: pointer;float: right; position: absolute; margin-top: 15px;" title="Click Here To remove Image" onclick="clearExistingImg(\'fileDescription'+addSource+'\');"></span>';
		

		str+='        </div>';

		str+='<div class="span2 ">';
		str+='        <label><strong>News<br> Edition</strong><span class="requiredFont">*</span></label>';
		str+='        <select  name="fileSourceVOList['+addSource+'].sourceFileList[0].newsEdition"  class="input-block-level "><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select>';

		str+='        </div>';
		str+='<div class="span1">';
		str+='        <label><strong>Page Number</strong><span class="requiredFont">*</span></label>';
		str+='        <input type="text" name="fileSourceVOList['+addSource+'].sourceFileList[0].pageNo" onKeyup="IsNumeric(this.value);" class="input-block-level pagenoCls">';

		str+='        </div>';
		str+='<div class="span1 ">';
		str+='        <label><strong>News Length</strong><font class="requiredFont">*</font></label>';
		str+='        <input type="text"  name="fileSourceVOList['+addSource+'].sourceFileList[0].newsLength" onKeyup="IsNumeric1(this.value);" class="input-block-level newsLengthCls">';

		str+='        </div>';
		str+='<div class="span2 ">';
		str+='        <label><strong>File Source</strong></label>';
		str+='        <select id="'+addSource+'addfilenewSource" style="margin-top:25px;" name="fileSourceVOList['+addSource+'].fileSourceId"  class="input-block-level m_top20 fileNewSourceCls"><option value="0">Select Source</option></select>';

		str+='        </div>';
		str+='<div class="span2 ">';
		str+='        <label><strong>News Language</strong><i class="icon-trash" title="Click here to remove this source" onclick="removeThisFileSource(\'newfilesourceremov'+addSource+'\')" style="margin-left: 20px;"></i></label>';
		str+='        <select  id="'+addSource+'addfilenewLanguage" style="margin-top:25px;"  name="fileSourceVOList['+addSource+'].sourceLangId" class="input-block-level m_top20"><option value="0">Select Language</option></select></select>';

		str+='        </div>';
		str+='</div>';
		str+='<div id="source'+addSource+'newfile"></div>';
		str+='<div class="container m_top5">';
		str +='<span class="help-block">&nbsp;&nbsp;&nbsp;File Path Or Detailed News Description is Mandatory. File must be .jpeg or .jpg or .png or .gif formats only.</span>';
		str+='<div class="span12 ">         <label><strong>Detailed News Description</strong></label>         <textarea id="aaanewsfileDescription'+addSource+'" name="fileSourceVOList['+addSource+'].completeDesc" rows="2" cols="20" class="input-block-level completeDetailedDescCls" style="width: 875px;" ></textarea> <span class="help-block">&nbsp;&nbsp;&nbsp;<input id="newsdetdescchk'+addSource+'" onclick="changeToEEnadutxt(\'newsdetdescchk'+addSource+'\',\'aaanewsfileDescription'+addSource+'\','+addSource+');" style="margin-top:-1px;" name="fileSourceVOList['+addSource+'].newsDescCheck" type="checkbox"/>&nbsp;Please chec,k if detailed news description is from eenadu.net</span>       </div>';

		str+='        </div>';
			 
		str+='</div>';
		str+='</div>';
		str+='<div class="" id="addButtonDivId'+addSource+'">';
		str+='      <a href="javascript:void(0);" onclick="addNewFilePart('+addSource+',0);" class="btn btn-success span6">Click here to <span class="label">Add <i class="icon-plus-sign icon-white"></i></span> another file to this source                   </a></div></div>';
        $("#addNewsSourceToExisting1").append(str);
		
		getSource(addSource+'addfilenewSource');
		getLanguage(addSource+'addfilenewLanguage');
		
}

var addFile = 0;
var id = 0;
function addNewFilePart(id,addFile){

	if(id == 0){
		id=id;
	}
     addFile =  parseInt(addFile)+1;

        var str ='';
        str+='<div id ="'+addFile+'newpart'+id+'" class="container " style="margin-left: 12px; float: left;">';
		str+='        <div class="span4" style="width: 275px;margin-right: -17px;">';
				
		str+='<input type="file" name="fileSourceVOList['+id+'].sourceFileList['+addFile+'].fileImage" class="btn addFileImgCls newsFile" style="width: 246px;"/>';

		str+='        </div>';

		str+='<div class="span2 " style="margin-left: 30px;">';
				
		str+='        <select name="fileSourceVOList['+id+'].sourceFileList['+addFile+'].newsEdition" class="input-block-level "><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select>';

		str+='        </div>';
		str+='<div class="span1">';
			  
		str+='        <input type="text" name="fileSourceVOList['+id+'].sourceFileList['+addFile+'].pageNo" class="input-block-level pagenoCls">';

		str+='        </div>';
		str+='<div class="span1 ">';
			   
		str+='        <input type="text" name="fileSourceVOList['+id+'].sourceFileList['+addFile+'].newsLength"  class="input-block-level newsLengthCls">';

		str+='        </div>';
		str+='<div class="span2 ">';
			   
		str+='        <a href="javascript:void(0);" onclick="removeThisFile(\''+addFile+'newpart'+id+'\',\'innerFile\','+id+')" class="btn btn-block"><i class="icon-trash"></i> Delete This Row</a>';

		str+='        </div>';

		str+='</div>';

		$("#source"+id+"newfile").append(str);
		
		$("#addButtonDivId"+id+"").html('');
		
		var str = '';
		str +=' <a href="javascript:void(0);" onclick="addNewFilePart('+id+','+addFile+');" class="btn btn-success span5" style="margin-left: 15px;">Click here to <span class="label">Add <i class="icon-plus-sign icon-white"></i></span> another file to this source</a>';
		$("#addButtonDivId"+id+"").html(str);
}

function zoomSelectedImg(img){
 $("#selectedImgZoom").html("<img src='"+img+"'></img>");
   $("#selectedImgZoom").dialog({
				resizable:true,
				title:'News Image',
				height:600,
				width:800,
				modal: true				
	});
}

function deleteExistingImg(id){

  if(confirm("Do You Want To Delete Existing Image ?")){
    $("#imgToDisplayDeleted"+id).val("true");
	$("#imgToDisplayRemove"+id).html("");
	
  }
}

function imgToDisplayRemove(){
  $("#imgToDisplayRemove").html("");
}

function getCandidatesListByPartyId(partyId,type,divId1)
{

	var divSourceId;
	var divId = divId1; 
	if(divId1 != 11 && divId1 != 22){
		divSourceId = (divId1 + "").charAt(0);
		divId = (divId1 + "").charAt(1);
	}
	var candiId = $("#candidateId"+divId+"").val();

	var id = candidates[candiId];

	if(divId == 11){
	$("#errDiv11").html('');
	}
	else if(divId == 22){
		$("#errDiv22").html('');
	}

	else if(divSourceId == 3){
	$("#errDiv3"+divId+"").html('');
	}

	else if(divSourceId == 4){
		$("#errDiv4"+divId+"").html('');
	}

    $("#"+type+"Img").css("display","block");
		var jsObj = {
			partyId :partyId,
			type:type,
			task : "getCandidatesListByPartyId"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidatesOfAParty.action?"+rparam;					
	
   callAjax(jsObj,url);
}


var who = 0;
 var whome = 0;

 
function addNewFrom(who){

 who = who + 1;
 var str ='';
 str+='    <div id="whocandidate'+who+'" style="margin-left: 0px;" class="row alert alert-warning">';
		str+='    <div class="span5 well well-small ">';
		str+='<label style="float: left;"><strong>Select Party</strong></label><span id="errDiv3'+who+'" style="margin-top: -25px; color: red; margin-left: 125px; margin-bottom: 9px;" ></span>';
		
		str+='<select class="input-block-level" id="partiesList'+who+'" name="candidatePartyNewsVOList.sourceVOList['+who+'].partyId" onchange="getCandidatesListByPartyId(this.value,\'candidateListForParty'+who+'\',3'+who+')">';
		if(partyArray.length>0){
		for(var i in partyArray)
			str+='<option value='+partyArray[i].value+'> '+partyArray[i].name+'</option>';
		}
		str +='</select>';
		
		str +='<img src="images/search.jpg" id="candidateListForParty'+who+'Img" style="display:none;"/>';
		str+='</div>';

		str+='    <div class="span5 well well-small">';
		str+='<label><strong>Select Candidate</strong></label>';
		str +='<div class="btn btn-mini pull-right " style="float: right; position: absolute; margin-top: -24px; margin-left: 260px;"> <a onclick="buildSearchDiv(3'+who+','+who+');"><i class="icon-search" title="Click here to Search Candidates"></i> </a></div>';
		str +='<span class="btn btn-mini pull-right m_topN65"><img src="images/user.png" title="Click Here To Create New Candidate" class="createCandidateCls createNewCandidate" key="candidateListForParty'+who+'" partylistid="partiesList'+who+'"></span>';
		str +='<select class="input-block-level" name="candidatePartyNewsVOList.sourceVOList['+who+'].candidateId" id="candidateListForParty'+who+'" >';
		str+='    <option value="0">Select Candidate</option>';
		str+='</select>';
		
		str+='</div>';
		str+='    <div class="span2 well well-small">';
		str+='   <label><strong>Rating</strong></label><select class="input-block-level" name="candidatePartyNewsVOList.sourceVOList['+who+'].benefitId">';
        str +='      <option value="1">Positive</option><option value="2">Negative</option><option value="3">Neutral</option>';
        str +='   </select>';
		
		str+='    </div>';
		str+='<div style="margin-left: 0px;" class="span12"><p>If you want to delete this block please click on this Button <a href="javascript:void(0);" onclick="deletethisDiv(\'whocandidate'+who+'\');" class="btn"><i class="icon-trash"></i> Delete This Block</a>    </p></div>	';	   
		str+='</div>  ';
		

$( "#whoTalkedMainDIV").append(str);

$("#addNewFromDivBtn").html('');
var str1 ='';
str1 +='<a class="btn btn-danger" onclick="addNewFrom('+who+');" href="javascript:void(0);">Click to add another From - Who</a>';

$("#addNewFromDivBtn").html(str1);
getPartiesList("partiesList"+who+"",null);

}
var removCountForWho = 0;
var removCountForWhom = 0;
function deletethisDiv(id,divId,candiPartyfileId){
 if(confirm("Do you want to delete this Party/Candidate?")){
 
    $("#"+id).remove();
	var str='';

	if(divId =="who"){
	str +='<input type="hidden" name="candidatePartyNewsVOList.deletedFileSourceList['+removCountForWho+']" value="'+candiPartyfileId+'"/>';
		$("#deletedSourceCandidatePartyDivForWho").append(str);
		removCountForWho = removCountForWho+1;
	}
	else if(divId == "whom"){

	str +='<input type="hidden" name="sourceList['+removCountForWhom+'].fileId" value="'+candiPartyfileId+'"/>';
		$("#deletedSourceCandidatePartyDivForWhom").append(str);
		removCountForWhom = removCountForWhom+1;
	}
	
  }
}

var whome = 0;
function addWhome(whome){

 whome = whome+1;
var str ='';

	    str+='    <div id="whomecandidate'+whome+'"><div class="row alert alert-warning" style="margin-left: 0px;">';
		str+='    <div class="span2 well well-small ">';
		str+='<label><strong>Select Party</strong></label><span id="errDiv4'+whome+'" style="float:left;position:absolute;margin-top: 30px; color: red; margin-left: -5px; margin-bottom: 9px;" ></span><select class="input-block-level" id="partiesListForWhome'+whome+'" name="candidatePartyNewsVOList.destinationVOList['+whome+'].partyId" onchange="getCandidatesListByPartyId(this.value,\'candidateListForPartyForNewsTo'+whome+'\',4'+whome+')">';
		str+='<option value="0">Select Party</option>';
		str +='</select>';
		str +='<img src="images/search.jpg" id="candidateListForPartyForNewsTo'+whome+'Img" style="display:none;" />';
		str+='</div>';

		str+='    <div class="span4 well well-small">';
		str+='<label><strong>Select Candidate</strong></label>';
		str +='<div class="btn btn-mini pull-right " style="float: right; position: absolute; margin-top: -24px; margin-left: 190px;"> <a onclick="buildSearchDiv(4'+whome+','+whome+');"><i class="icon-search" title="Click here to Search Candidates"></i> </a></div>';
		str +='<span class="btn btn-mini pull-right m_topN65"><img partylistid="partiesListForWhome'+whome+'" key="candidateListForPartyForNewsTo'+whome+'" class="createCandidateCls createNewCandidate" title="Click Here To Create New Candidate" src="images/user.png"></span>';
		str +='<select id="candidateListForPartyForNewsTo'+whome+'" name="candidatePartyNewsVOList.destinationVOList['+whome+'].candidateId" class="input-block-level" >';
		str+='    <option value="0">Select Candidate</option>';
		str+='</select>';

		str+='</div>';
		str+='<div class="span4 well well-small">';
		str+='<label><strong>Select Categories</strong></label>';
		str+='<span style="float:left;margin-left:135px;margin-top:-25px"><a title="refresh list" onclick="refreshCategories(\''+whome+'whomegallaryId\');" href="javascript:{}"><i class="icon-refresh"></i></a></span>';
		str+='<select name="keywordIdHiddenCat'+whome+'" multiple="multiple" style="width: 252px;" id="'+whome+'whomegallaryId" >';
		str+='    <option>Select Category</option>';
		str+='</select>';
		
		str+='    </span>';
		str+='</div>';

		str+='    <div class="span2 well well-small">';
		str+='   <label><strong>Rating</strong></label><select name="candidatePartyNewsVOList.destinationVOList['+whome+'].benefitId" class="input-block-level">';
        str+='      <option value="1">Positive</option><option value="2">Negative</option><option value="3">Neutral</option>';
		str+='</select>';

		str+='    </div>';
		str+='<div class="span12 well well-small" style="margin-left: 0px;">';
		str+='<label><strong>Enter Keywords</strong></label><input type="text" class="input-block-level keyword'+whome+' destinationKeywords" key="keywordId'+whome+'" id="'+whome+'keywordId">';

		str+='</div>';
		str +='<input type="hidden" id="keywordId'+whome+'Hidden" name="candidatePartyNewsVOList.destinationVOList['+whome+'].keywordsList" />';
        str +='<input type="hidden" id="keywordIdHiddenCat'+whome+'" name="candidatePartyNewsVOList.destinationVOList['+whome+'].categoryIdsStr" />';		   
		str+='<div style="margin-left: 0px;" class="span12"><p>If you want to delete this block please click on this Button <a href="javascript:void(0);" onclick="deletethisDiv(\'whomecandidate'+whome+'\');" class="btn"><i class="icon-trash"></i> Delete This Block</a>    </p></div>	   </div>';


$( "#whomeTalkedMainDIV").append(str);

$("#addWhomDiv").html('');
var str1 ='';
str1 +='<a href="javascript:void(0);" onclick="addWhome('+whome+');" class="btn btn-danger">Click to add another To - Whom</a>';

$("#addWhomDiv").html(str1);

$("#"+whome+"keywordId").autoSuggest("getKeyWordsBySearchCriteria.action", {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});

getPartyGallariesForUplaod("News Gallary",whome+"whomegallaryId");
getPartiesList("partiesListForWhome"+whome+"",null);

}
var o = 0;
function addNewFileToUpload(id){
   o = o+1;
   $("#multifile"+id).append('<div id="removeThisFil'+o+'"><table><tr><td><input type="file" name="fileSourceVOList['+id+'].fileImage"  size="25"  class="newsFile" style="width: 225px;"/></td><td><a class="btn btn-small btn-block" style="width: 17px;" href="javascript:void(0)" onclick="removeThisFile(\'removeThisFil'+o+'\');"> <i class="icon-minus-sign"></i></a></td></tr></table></div>');

}

function removeThisFile(id,removeExistSource,indexValue){

 if(confirm("Do you want to delete this row?")){
	 if(indexValue == 0){
			//$("#fileDescription0").removeClass("newsFile");
			//$("#fileDescription0").addClass("newsFile0");
		}
 	$("#"+id).html('');	
	if(removeExistSource != 'innerFile')
		document.getElementById(removeExistSource).value = 'true';  
  }
}
function removeAlreadyExistingSource(id,removeExistSource){
 if(confirm("Do you want to delete this source?")){
 	$("#"+id).html('');	 
	document.getElementById(removeExistSource).value = 'true';  
  }
}
var t =0;
function addNewSourceToDiv(){
t=t+1;
var str='';
str += '        <tr id="newFileSourceFile'+t+'">';
str += '            <td><input  type="file" name="fileSourceVOList['+t+'].fileImage"  size="25"  class="newsFile" style="width: 225px;"/><div id="multifile'+t+'"></div>';
str += '<a href="javascript:void(0)" onclick="addNewFileToUpload('+t+');" style="width: 100px; margin-left: 51px; margin-top: 11px;" class="btn btn-small btn-block"> <i class="icon-plus-sign"></i></a></td>';
str += '            <td><select id="filesourceId'+t+'" name="fileSourceVOList['+t+'].fileSourceId" class="input-small"><option value="0">Select Source</option></select></td>';
str += '            <td><select id="sourceLangId'+t+'" name="fileSourceVOList['+t+'].sourceLangId" class="input-small"><option value="0">Select Language</option></select></td>';
str += '            <td><select id="newsedition'+t+'" name="fileSourceVOList['+t+'].newsEdition" class="input-small"><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select></td>';
str += '            <td><input type="text" id="pageno'+t+'" name="fileSourceVOList['+t+'].pageNo" class="pageno input-small"  onKeyup="IsNumeric(this.value);"></input></td>';
str += '            <td><input type="text" id="newslength'+t+'" class="newslength input-small" name="fileSourceVOList['+t+'].newsLength" onKeyup="IsNumeric1(this.value);"></input></td>';
str += '            <td><textarea  id="completeDesc'+t+'" name="fileSourceVOList['+t+'].completeDesc" class="completeDescCls'+t+' input-block-level"></textarea></td>';
str += '            <td><a onclick="removeThisFileSource(\'newFileSourceFile'+t+'\');" href="javascript:void(0)" style="width: 17px;" class="btn btn-small btn-block"> <i class="icon-minus-sign"></i></a></td>';
str += '               </tr>';

$('#uploadFilesTable > tbody:last').append(str);
  getSource("filesourceId"+t);
	getLanguage('sourceLangId'+t);
}
function removeThisFileSource(id){

  if(confirm("Do you want to delete this Source?")){
  $("#"+id).remove();
  }
}
function addMoreFilesForPartyCandidate()
{
	var moreDivElmt = document.createElement("addMoreFilesDiv");
	var str ='';
	str +='<table style="background:#e3e3e3;border-radius:9px;padding:5px;margin-top:12px;" id="moreFileTableId'+fileCount+'" class="moreFileDivCls" key="'+fileCount+'">';
	str += ' <tr>';
	str +='<td>File Path</td>';
	str += ' <td class="selectWidthPadd"><input type="file" name="fileSourceVOList['+fileCount+'].fileImage" class="newsFileId'+fileCount+' newsFile" size="25"   class="newsFile" style="width: 225px;"/></td>';
    str +='<td><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMoreImagesForPartyCandidate(\''+fileCount+'\')" title="Click here to add more images" alt="Click here to add more images"></td>';

	str += ' </tr>';
    
	str +='<tr>';
	str +='<td colspan="2"><div id="moreFilePathsImagesDiv'+fileCount+'"></div></td>';
	str +='</tr>';

	str += ' <tr>';
	str += ' <td class="tdWidth1">Source : <font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="filesourceId'+fileCount+'" name="fileSourceVOList['+fileCount+'].fileSourceId" style="width:175px;"><option value="0">Select Source</option></select></td>';
	str += ' </tr>';
	str += ' <tr>';
	str += ' <td class="tdWidth1">Language : <font class="requiredFont">*</font></td>';
	str += ' <td class="selectWidthPadd"><select id="sourceLangId'+fileCount+'" name="fileSourceVOList['+fileCount+'].sourceLangId" style="width:175px;"><option value="0">Select Language</option></select></td>';
	
	str += ' </tr>';
	str += '       <td class="tdWidth1">Edition : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="newsedition'+fileCount+'" name="fileSourceVOList['+fileCount+'].newsEdition" style="width:175px;margin-top:8px;"><option value="1">Main Edition</option><option value="2">District/Sub Edition</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Page Number : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="pageno'+fileCount+'" name="fileSourceVOList['+fileCount+'].pageNo" class="pageno pagenoCls" size="25" maxlength="200" style="margin-top:8px;" onKeyup="IsNumeric(this.value);"></input></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Length : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><input type="text" id="newslength'+fileCount+'" class="newslength newsLengthCls" name="fileSourceVOList['+fileCount+'].newsLength" size="25" maxlength="200" style="margin-top:8px;" onKeyup="IsNumeric1(this.value);"></input></td>';

	str += '   </tr>';
    
	str +='<tr>';
	str +='<td>News description in details : </td>';
	str +='<td><textarea rows="3" columns = "20" id="completeDesc'+fileCount+'" name="fileSourceVOList['+fileCount+'].completeDesc" class="completeDescCls'+fileCount+'"></textarea></td>';
	str +='<td><input type="checkbox" id="newsDescCheck'+fileCount+'" name="fileSourceVOList['+fileCount+'].newsDescCheck" value="true" class="newsDescCheckId" key="'+fileCount+'" /><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteFile(\'moreFileTableId'+fileCount+'\')"></td>';
	str +='</tr>';

	str +='</table>';
	moreDivElmt.innerHTML = str;
	document.getElementById("addMoreFilesDiv").appendChild(moreDivElmt);
	getSource("filesourceId"+fileCount+"");
	getLanguage('sourceLangId'+fileCount+'');
	fileCount++;
}

function addMoreImagesForPartyCandidate(count)
{
  
  
  var divElmt = document.createElement("moreFilePathsImagesDiv"+count+"");
  var str = '';
  str +='<table id="moreFilePathsId'+count+''+fileImgCount+'">';
  str += '<tr>';
  str += '<td>File Path : </td>';
  str += '<td><input type="file" class="newsFileId newsFile'+count+'" size="50" name="fileSourceVOList['+count+'].fileImage"  class="newsFile" style="width: 225px;"/></td>';
  str += '<td><img onclick="deleteFileImagePaths(\''+count+''+fileImgCount+'\');" title="Click here to delete file" src="images/minus.png" style="background: #fff; border-radius: 11px; padding: 4px;"></td>';
  str +='<tr>';
  divElmt.innerHTML = str;
  document.getElementById("moreFilePathsImagesDiv"+count+"").appendChild(divElmt);
  
  fileImgCount++;
}

function deleteFileImagePaths(imgId)
{
 
 $('#moreFilePathsId'+imgId+'').html('');
}

function getTypeOfConstituency(value)
{	
	if(value == 1)
	{
		$('#pcConstituencyRow').hide();
		$('#acConstituencyRow').show();
	}
	else if(value == 2)
	{
		$('#pcConstituencyRow').show();
		$('#acConstituencyRow').hide();
	}
	else
	{
		$('#pcConstituencyRow').hide();
		$('#acConstituencyRow').hide();
	}
}

function validateNewsFileUpload1()
{

var fileTitle = document.getElementById('newsfileTitle').value;
	var fileDesc = document.getElementById('newsfileDescription').value;
	
	var fileDate = document.getElementById("newsdatedatepic").value;
//	var scope = document.getElementById("scopeDiv").value;
	 var sourceArr1 = new Array();
	var flag = true;

	fileTitle = removeAllUnwantedCharacters1(fileTitle);	
	fileDesc = removeAllUnwantedCharacters1(fileDesc);
	document.getElementById('newsfileTitle').value = fileTitle;
	document.getElementById('newsfileDescription').value = fileDesc;
	var synopsysDesc  = $.trim($("#newsfileSynopsys").val());
	
	var errorDivEle = document.getElementById('uploadNewsFileErrorDiv');
	var str = '<font color="red">';
	var flag1 =true;
	if(fileDate.length == 0)
	{
		str +='File Date is Required<br>';
		flag = false;
	}
	if(fileTitle.length == 0)
	{
		str += ' Title is Required.<br>';
		flag = false;
	}

	$('.fileNewSourceCls').each(function() {

		 var sourceId = $(this).val();			   
		   if(sourceArr1.indexOf(sourceId) != -1){
				str += ' File Sources must be different (Ex: Eenadu,Sakshi...etc).<br>';
				 flag = false;
				 return false;
			}
			else
				sourceArr1.push($(this).val());

       });
	   
	$('.newsFile0').each(function() {
           if($.trim($(this).val()).length == 0)
	       {
		   var id=$(this).attr('id');	
		   
		   var detalDesc = $("#aaanews"+id+"").val();

			   if( detalDesc.length <= 0 || typeof(detalDesc) ==  "undefined"){
				str+='File OR Detailed news description Required.<br>';
				 flag = false;
				 return false;
			   }		 
	       }
       });
	
	$('.newsFile').each(function() {

	   if($.trim($(this).val()).length == 0)
	   {
		 str+='File Required.<br>';
		 flag = false;
		 return false;
	   }
	});
	
	if(synopsysDesc.length == 0)
	{
		str += 'News Synopsys is Required.<br>';
		flag = false;
	}
	
	$('.destinationKeywords').each(function() {
	  
		var key = $(this).attr("key");	
		
		var str = '';
			var $ul = $(this).closest('ul');
			  $ul.find('li').each(function(){
			  str +=''+$(this).text()+',';
			});	
		  var length = str.length;
		  if(length > 0)
		   str = str.substring(0,length-2);
		
		if(isValidKeyword(str) == false){
		flag1 = false;
		return flag1;
		}
		  //$("#"+key+"Hidden").val(str);
        });
	
		if(flag1 == false)
		{
	
		str +='Keyword Name should not contain #,$,%,& Special characters<br>';
			flag = false;
		}
	 $('.pagenoCls').each(function() {
           if($.trim($(this).val()).length == 0)
	       {
		     str += ' Page Number is Required.<br>';
		     flag = false;
			 return false;
	       }
       });
	$('.pagenoCls').each(function() {
           if($.trim($(this).val()).length > 0)
	       {
		     if(isNaN($.trim($(this).val()))){
				 str += ' Page Number must be Integer.<br>';
				 flag = false;
				 return false;
			 }
	       }
       });
	   
	   $('.newsLengthCls').each(function() {
           if($.trim($(this).val()).length == 0)
	       {
		     str += ' News Length is Required.<br>';
		     flag = false;
			 return false;
	       }
       });
	$('.newsLengthCls').each(function() {
           if($.trim($(this).val()).length > 0)
	       {
		     if(isNaN($.trim($(this).val()))){
				 str += ' News Length must be number.<br>';
				 flag = false;
				 return false;
			 }
	       }
       });
	
	if(fileDesc.length == 0)
	{
		str += 'Description is Required.<br>';
		flag = false;
	}
	
	var allLocSeleted = true;
	$('.scopeLevelDiv').each(function(i,value) {
	var locationDisabled = $(this).attr('disabled');
	var scope =  $(this).val();
		if(locationDisabled != 'disabled'){
			
				if(scope == 0 )
				{
					str += 'Location Scope is Required.';
					flag = false;
					allLocSeleted = false;
				}
				if(scope == 2)
				{
					var stateVal=$('#stateDiv'+i+'').val();
					if(stateVal == 0)
					{
					if(!(str.indexOf("State Name is Required") > -1))
					str += 'State Name is Required.<br>';
					flag = false;
					allLocSeleted = false;
					}
				}	
				if(scope == 3)
				{
					var stateVal=$('#stateDiv'+i+'').val();
					var districtVal=$('#districtDiv'+i+'').val();
					if(stateVal == 0)
					{
					if(!(str.indexOf("State Name is Required") > -1))
					str += 'State Name is Required.<br>';
					flag = false;
					allLocSeleted = false;
					}
					if(districtVal == 0)
					{
					if(!(str.indexOf("District Name is Required") > -1))
					str += 'District Name is Required.<br>';
					flag = false;
					allLocSeleted = false;
					}
					
				}
				if(scope == 4)
				{
					var stateVal=$('#stateDiv'+i+'').val();
					var districtVal=$('#districtDiv'+i+'').val();
					var constituencyVal=$('#constituencyDiv'+i+'').val();
					if(stateVal == 0)
					{
					if(!(str.indexOf("State Name is Required") > -1))
					str += 'State Name is Required.<br>';
					flag = false;
					allLocSeleted = false;
					}
					if(districtVal == 0)
					{
					if(!(str.indexOf("District Name is Required") > -1))
					str += 'District Name is Required.<br>';
					flag = false;
					allLocSeleted = false;
					}
					if(constituencyVal == 0)
					{
					if(!(str.indexOf("Constituency Name is Required") > -1))
					str += 'Constituency Name is Required.<br>';
					flag = false;
					}
					
				}
				if(scope == 5)
				{
					var stateVal=$('#stateDiv'+i+'').val();
					var districtVal=$('#districtDiv'+i+'').val();
					var constituencyVal=$('#constituencyDiv'+i+'').val();
					var mandalVal=$('#mandalDiv'+i+'').val();
					if(stateVal == 0)
					{
					if(!(str.indexOf("State Name is Required") > -1))
					str += 'State Name is Required.<br>';
					flag = false;
					allLocSeleted = false;
					}
					if(districtVal == 0)
					{
					if(!(str.indexOf("District Name is Required") > -1))
					str += 'District Name is Required.<br>';
					flag = false;
					allLocSeleted = false;
					}
					if(constituencyVal == 0)
					{
					if(!(str.indexOf("Constituency Name is Required") > -1))
					str += 'Constituency Name is Required.<br>';
					flag = false;
					}
					if(mandalVal == 0)
					{
					if(!(str.indexOf("Mandal Name is Required") > -1))
					str += 'Mandal Name is Required.';
					flag = false;
					allLocSeleted = false;
					}
					
				}
				if(scope == 6 || scope == 8 || scope == 9)
				{
					var stateVal=$('#stateDiv'+i+'').val();
					var districtVal=$('#districtDiv'+i+'').val();
					var constituencyVal=$('#constituencyDiv'+i+'').val();
					var mandalVal=$('#mandalDiv'+i+'').val();
					var villageVal=$('#villageDiv'+i+'').val();
					if(stateVal == 0)
					{
					if(!(str.indexOf("State Name is Required") > -1))
					str += 'State Name is Required.<br>';
					flag = false;
					allLocSeleted = false;
					}
					if(districtVal == 0)
					{
					if(!(str.indexOf("District Name is Required") > -1))
					str += 'District Name is Required.<br>';
					flag = false;
					allLocSeleted = false;
					}
					if(constituencyVal == 0)
					{
					if(!(str.indexOf("Constituency Name is Required") > -1))
					str += 'Constituency Name is Required.<br>';
					flag = false;
					}
					if(mandalVal == 0)
					{
					if(!(str.indexOf("Mandal Name is Required") > -1))
					str += 'Mandal Name is Required.';
					flag = false;
					allLocSeleted = false;
					}
					if(villageVal == 0)
					{
					if(!(str.indexOf("Villiage Name is Required") > -1))
					str += 'Villiage Name is Required.';
					flag = false;
					allLocSeleted = false;
					}
				}
				if(scope == 7)
				{
					var stateVal=$('#stateDiv'+i+'').val();
					var districtVal=$('#districtDiv'+i+'').val();
					var constituencyVal=$('#constituencyDiv'+i+'').val();
					var mandalVal=$('#mandalDiv'+i+'').val();
					
					if(stateVal == 0)
					{
					if(!(str.indexOf("State Name is Required") > -1))
					str += 'State Name is Required.<br>';
					flag = false;
					allLocSeleted = false;
					}
					if(districtVal == 0)
					{
					if(!(str.indexOf("District Name is Required") > -1))
					str += 'District Name is Required.<br>';
					flag = false;
					allLocSeleted = false;
					}
					if(constituencyVal == 0)
					{
					if(!(str.indexOf("Constituency Name is Required") > -1))
					str += 'Constituency Name is Required.<br>';
					flag = false;
					}
					if(mandalVal == 0)
					{
					if(!(str.indexOf("Mandal Name is Required") > -1))
					str += 'Mandal Name is Required.';
					flag = false;
					allLocSeleted = false;
					}
					
				}
			}
			
		});

	 if(allLocSeleted){
     var containsDuplicateLoc = false;
	  var locations = new Array();
	  $('.scopeLevelDiv').each(function(index,value) {
	    var locScope = $(this).val();
		var locValue;
		if(locScope == 2){
			locValue = $('#stateDiv'+index+'').val();
		}
		else if(locScope == 3){
			locValue = $('#districtDiv'+index+'').val();
		}
		else if(locScope == 4){
			locValue = $('#constituencyDiv'+index+'').val();
		}
		else if(locScope == 5 || locScope == 7){
			locValue = $('#mandalDiv'+index+'').val();
		}
		else if(locScope == 6 || locScope == 8 || locScope == 9){
			locValue = $('#villageDiv'+index+'').val();
		}
	     
		for(var i in locations){
		   if(locations[i].id == locScope && locations[i].value == locValue ){
		      containsDuplicateLoc = true;
			  flag = false;
			  str += ' <b>News Location cannot be duplicate</b>( Ex: If Location Scope is Constituency and Assembly Consti is kavali Once again Location Scope Constituency and Assembly Consti kavali cannot be selected).<br>';
		   }
		  if(containsDuplicateLoc){
		     break;
		  }
		}
		if(containsDuplicateLoc){
		     return false;
		}
		var obj={
		   id:locScope,
		   value:locValue
		}
		locations.push(obj);
	  });
  }
  
	str += '</font>';
	errorDivEle.innerHTML = str;
	if(flag == false)
	{ 
		 $("#uploadNewsBtnId").css("background","#51A351");
		$("form").submit(function (e) {
        e.preventDefault(); 
		});
		$('html, body').animate({
         scrollTop: $("#uploadNewsFileErrorDiv").offset().top
     }, 2000);
		
	}
	$('html, body').animate({
			 scrollTop: $("#uploadNewsFileErrorDiv").offset().top
		 }, 2000);
return flag;

}
function isValidKeyword(str){

 var iChars = "#$%&";
 var flag = true;
	for (var i = 0; i < str.length; i++) {
		if (iChars.indexOf(str.charAt(i)) != -1) {			
			flag = false;
		}
    }
		
	return flag;

}
function changeToEEnadutxt(ck,id,sourceIndex){

    if($('#'+ck).is(':checked')){

		if(ck.indexOf("newsdetdescch") != -1)
			$('#fontStatus'+sourceIndex+'').val('1');
			
     $('#'+id).addClass('enadu');

   }else{

	  if(ck.indexOf("newsdetdescch") != -1)
				$('#fontStatus'+sourceIndex+'').val('0');
			
		$('#'+id).removeClass('enadu');
   }
}
function getScopeForUser(){
  
 var jsObj =
		{ 
            time : timeST,
			divId:"scopeDiv",
  		    task:"getLocationScope"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationScopeAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}

function changeLanguage(){

	if($("#sourceTelugu").is(':checked') == true)
	{
		$('#newsfileTitle').addClass('enadu');
		$('#sourceTelugu').val('true');
			
	}
	else
	{
		$('#newsfileTitle').removeClass('enadu');	
		$('#sourceTelugu').val(null);
	}
	if($("#sourceDescTelugu").is(':checked') == true){
		$('#newsfileDescription').addClass('enadu');
		$('#sourceDescTelugu').val('true');
	}
	else{
		$('#newsfileDescription').removeClass('enadu');
		$('#sourceDescTelugu').val(null);
	}
	
	if($("#synopsysDescTelugu").is(':checked') == true){
		$('#newsfileSynopsys').addClass('enadu');
		$('#synopsysDescTelugu').val('true');
	}
	else{
		$('#newsfileSynopsys').removeClass('enadu');
		$('#synopsysDescTelugu').val(null);
	}
}

function newKeywordsInserting()
{
  //keywords
  var tempKeywordArray = new Array();
    if(keywordsArray != null && keywordsArray.length > 0)
	  for(var i in keywordsArray)
	   tempKeywordArray.push(keywordsArray[i].name);
	

	 $(".as-selections li").each(function(){
		var name = $.trim($(this).text()).substring(1);
        if(tempKeywordArray.indexOf(""+name+"") == -1)
		{
		   var obj = {value:0,name:""+name+""};
		   keywordsArray.push(obj);
		}

	 });
        
	data = {items:keywordsArray};

     $(".multiKeywordList").html('');
	 $(".multiKeywordList").html('<input type="text" name="keywordList1" id="keywordListId1"/>');

	 $("#keywordListId1").autoSuggest(data.items, {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});


}
function refreshCategories(id){
	//console.log(id);
	$.ajax({
		type:'POST',
		url: 'getAllCategoryListAction.action',
		dataType: 'json',
		data: {task:JSON.stringify({task:"getCategoryList"})},
		success:function(result){
			$("#"+id+"").multiselect("destroy")
			clearAll(id);
			 $.each( result, function( index, myResult ){
				$("#"+id+"").append("<option value="+myResult.id+">"+myResult.name+"</option>");
			});
			$("#"+id+"").multiselect({
		  noneSelectedText:"Select Category"});
		}
	});

}



function getStatesForSpecialPageForEdit(index)
{

  var jsObj =
		{ 
			div:"stateDiv"+index+"",
            time : timeST,
			task:"getStates"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjaxForEdit(jsObj,url);
}
function getDistrictsForEdit(stateId,index){
  var jsObj =
		{ 
			div:"districtDiv"+index+"",
            time : timeST,
			stateId : stateId,
			task:"getDistrictsByStateId"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjaxForEdit(jsObj,url);
 
}

 function getAllDetailsForEdit(id,task,areaType,constId,divId,index)
   {
        var jsObj =
		{ 
			div:divId,
            time : timeST,
			id:id,
			task:task,
			taskType:"",
			selectElementId:"",
			address:"",
			areaType:areaType,
			index:index,
			constId:constId
		};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "locationsHierarchiesAjaxAction.action?"+rparam;						
	 callAjaxForEdit(jsObj,url);
   }
function callAjaxForEdit(jsObj,url)
{
	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText); 
			if(jsObj.task == "getStates")
			{
               buildResultsForEdit(myResults,jsObj);
			}
			
			else if(jsObj.task == "getDistrictsByStateId")
			{
               buildResultsForEdit(myResults,jsObj);
			}
			else if(jsObj.task == "constituenciesInDistrict")
			{
				$('#mandalDiv'+jsObj.index+'').find('option').remove();
				$('#villageDiv'+jsObj.index+'').find('option').remove();
				$('#mandalDiv'+jsObj.index+'').append('<option value="0">Select Location </option>');
				$('#villageDiv'+jsObj.index+'').append('<option value="0">Select Location </option>');
                buildResultsForEdit(myResults,jsObj);
			}
			else if(jsObj.task == "getConstNotInGivenAreaType")
			{
				$('#mandalDiv'+jsObj.index+'').find('option').remove();
				$('#mandalDiv'+jsObj.index+'').append('<option value="0">Select Location </option>');
              
               buildResultsForEdit(myResults,jsObj);
			}
			else if(jsObj.task == "subRegionsInConstituency")
			{
				$('#villageDiv'+jsObj.index+'').find('option').remove();
				$('#villageDiv'+jsObj.index+'').append('<option value="0">Select Location </option>');
               buildResultsForEdit(myResults,jsObj);
			}
			else if(jsObj.task == "hamletsOrWardsInRegion")
			{
               buildResultsForEdit(myResults,jsObj);
			}
			else if(jsObj.task == "boothsInTehsilOrMunicipality")
			{
               buildResultsForEdit(myResults,jsObj);
			}

		}
		catch(e)
		{   
		 //alert("Invalid JSON result" + e);   
		}  
	 },
	scope : this,
	failure : function( o )
	{
								//alert( "Failed to load result" + o.status + " " + o.statusText);
	}
  };

 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function getLocationsForEdit(id,index){

  var str = '';
  if(id==0 || id == 1)
   $("#showScopeSubs"+index+"").html(str);	
  else if(id==2)
  {

   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" name="fileVO['+index+'].locationValue" style="margin-left:5px;width: 137px;"  id="stateDiv'+index+'"></select>';
   str +='</div>';

   $("#showScopeSubs"+index+"").html(str);
   getStatesForSpecialPageForEdit(index);
  }
  else if(id==3)
  {
   
   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv'+index+'" style="margin-left:5px;width: 137px;" onchange="clearAll(\'districtDiv'+index+'\');getDistrictsForEdit(this.options[this.selectedIndex].value,'+index+')"></select>';
   str +='</div>';

   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" name="fileVO['+index+'].locationValue" id="districtDiv'+index+'"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   $("#showScopeSubs"+index+"").html(str);
   getStatesForSpecialPageForEdit(index);
  }
  else if(id==4)
  {
   
   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv'+index+'" style="margin-left:5px;width: 137px;" onchange="clearAllElmts(4,1);clearAll(\'districtDiv\');getDistrictsForEdit(this.options[this.selectedIndex].value,'+index+')"></select>';
   str +='</div>';

   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" id="districtDiv'+index+'" onchange="clearAll(\'constituencyDiv'+index+'\');getAllDetailsForEdit(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\',\'constituencyDiv'+index+'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';
  
   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>Assembly Consti</label>';
   str += ' <select class="input-block-level" name="fileVO['+index+'].locationValue" id="constituencyDiv'+index+'"><option value="0">Select Location</option></select>';
   str +='</div>';

   $("#showScopeSubs"+index+"").html(str);
  
   getStatesForSpecialPageForEdit(index);
  }
  else if(id==5 || id==7)
  {
  var task='';
   if(id==5)
   {
     areaType1 = "URBAN" ;
     areaType2 = "RURAL" ;
	 task = "mandalsInConstituency"
   }
   if(id==7)
   {
     areaType1 = "RURAL" ;
     areaType2 = "URBAN" ;
	 	 task = "muncipalitiesInConstituency"
   }
   
   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv'+index+'" style="margin-left:5px;width: 137px;" onchange="clearAllElmts(5,1);clearAll(\'districtDiv\');getDistrictsForEdit(this.options[this.selectedIndex].value,'+index+')"></select>';
   str +='</div>';

   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" id="districtDiv'+index+'" onchange="clearAllElmts(5,2);clearAll(\'constituencyDiv'+index+'\');getAllDetailsForEdit(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\',\'constituencyDiv'+index+'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';
  
   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>Assembly Consti</label>';
   str += ' <select class="input-block-level" id="constituencyDiv'+index+'" onchange="clearAll(\'mandalDiv'+index+'\');getAllDetailsForEdit(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\',\'mandalDiv'+index+'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   str += '<div class="span2" style="width: 155px;margin-left:15px;">';
   str += ' <label>Mndl/Munic/Corp/GMC</label>';
   str += ' <select class="input-block-level" name="fileVO['+index+'].locationValue" id="mandalDiv'+index+'" name="mandalId" ><option value="0">Select Location</option></select>';
   str +='</div>';

   $("#showScopeSubs"+index+"").html(str);
  
   getStatesForSpecialPageForEdit(index);
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

   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv'+index+'" style="margin-left:5px;width: 137px;" onchange="clearAllElmts(6,1);clearAll(\'districtDiv\');getDistrictsForEdit(this.options[this.selectedIndex].value,'+index+')"></select>';
   str +='</div>';

   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" id="districtDiv'+index+'" onchange="clearAllElmts(6,2);clearAll(\'constituencyDiv'+index+'\');getAllDetailsForEdit(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\',\'constituencyDiv'+index+'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';
  
   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>Assembly Consti</label>';
   str += ' <select class="input-block-level" id="constituencyDiv'+index+'" onchange="clearAllElmts(6,3);clearAll(\'mandalDiv'+index+'\');getAllDetailsForEdit(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\',\'mandalDiv'+index+'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   str += '<div class="span2" style="width: 155px;margin-left:15px;">';
   str += ' <label>Mndl/Munic/Corp/GMC</label>';
   
   if(id==6)
   {
    str += ' <select class="input-block-level" id="mandalDiv'+index+'"  name="mandalId" onchange="clearAll(\'villageDiv'+index+'\');getAllDetailsForEdit(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\',\'villageDiv'+index+'\','+index+')"><option value="0">Select Location</option></select>';
   }
   if(id==8)
   {
     str += ' <select class="input-block-level" id="mandalDiv'+index+'"  name="fileVO['+index+'].location" onchange="clearAll(\'villageDiv'+index+'\');getAllDetailsForEdit(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\',\'villageDiv'+index+'\','+index+')"><option value="0">Select Location</option></select>';
   }

   
   str +='</div>';

   str += '<div class="span2" style="margin-left:238px;">';
   str += ' <label>Village/Ward/Division</label>';
   str += ' <select class="input-block-level" id="villageDiv'+index+'" name="fileVO['+index+'].locationValue"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   
   $("#showScopeSubs"+index+"").html(str);
   
   getStatesForSpecialPageForEdit(index);
  }
  else if(id==9)
  {

   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv'+index+'" style="margin-left:5px;width: 137px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,1);clearAll(\'districtDiv'+index+'\');getDistrictsForEdit(this.options[this.selectedIndex].value,'+index+')"></select>';
   str +='</div>';

   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" id="districtDiv'+index+'" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,2);clearAll(\'constituencyDiv'+index+'\');getAllDetailsForEdit(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\',\'constituencyDiv'+index+'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';
  
   str += '<div class="span2" style="margin-left:15px;">';
   str += ' <label>Assembly Consti</label>';
   str += ' <select class="input-block-level" id="constituencyDiv'+index+'" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,3);clearAll(\'mandalDiv'+index+'\');getAllDetailsForEdit(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'\',\'\',\'mandalDiv'+index+'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';

   str += '<div class="span2" style="width: 155px;margin-left:15px;">';
   str += ' <label>Mndl/Munic/Corp/GMC</label>';
   str += ' <select class="input-block-level" id="mandalDiv'+index+'" name="mandalId"  onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAll(\'villageDiv'+index+'\');getAllDetailsForEdit(this.options[this.selectedIndex].value,\'boothsInTehsilOrMunicipality\',\'\',document.getElementById(\'constituencyDiv'+index+'\').options[document.getElementById(\'constituencyDiv'+index+'\').selectedIndex].value,\'villageDiv'+index+'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';

   str += '<div class="span2" style="margin-left:238px;">';
   str += ' <label>Village/Ward/Division</label>';
   str += ' <select class="input-block-level" id="villageDiv'+index+'" name="fileVO['+index+'].locationValue"><option value="0">Select Location</option></select>';
   str +='</div>';
   $("#showScopeSubs"+index+"").html(str);
    
   getStatesForSpecialPageForEdit(index);
  }
 }

function buildResultsForEdit(results,jsObj){

var divId = jsObj.div;

	$('#'+divId+"").find('option').remove();

  var locationScopeId = $("#scopeDiv").val(); 
 
  var elmt = document.getElementById(divId);
         if(divId=='scopeDiv' || divId == "scopeDivForEdit")
		 {
		    var option1 = document.createElement('option');
		 option1.value= 0;
		option1.text= "Select Scope";
		 }
		 else if(divId=='electionType')
		   {
		     var option1 = document.createElement('option');
		 option1.value= 0;
		option1.text= "Select Election Type";
		   }
		 else{
	     var option1 = document.createElement('option');
		 option1.value= 0;
		option1.text= "Select Location";
		}
		try
		{
			elmt.add(option1,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option1); // IE only
		}

	  if(divId.indexOf("mandalDiv") != -1)
	  {
        for(var j in results)
	    {
		  var tempId = ""+results[j].id+""; 
		  if(results[j].id != 0 && (locationScopeId == 8 || locationScopeId == 6)){
		  if(locationScopeId == 8 && tempId.substring(0,1) == "1")
		  {
		    var option = document.createElement('option');
		    option.value=results[j].id;
		    option.text=results[j].name;
            try{
			   elmt.add(option,null); // standards compliant
		     }
		     catch(ex){
			   elmt.add(option); // IE only
		     }  
		  }
         
		 if(locationScopeId == 6 && tempId.substring(0,1) == "2")
		  {
		    var option = document.createElement('option');
		    option.value=results[j].id;
		    option.text=results[j].name;
            try{
			   elmt.add(option,null); // standards compliant
		     }
		     catch(ex){
			   elmt.add(option); // IE only
		     }  
		  }
		}
		
		  else if(locationScopeId == 7){
				
				var option = document.createElement('option');
					if((results[j].name.toLowerCase()).indexOf("muncipality") != -1 || (results[j].name.toLowerCase()).indexOf("greater") != -1 || (results[j].name.toLowerCase()).indexOf("corporation") != -1 ){
							option.value=results[j].id;
							option.text=results[j].name;
							try{
							   elmt.add(option,null); // standards compliant
							 }
							 catch(ex){
							   elmt.add(option); // IE only
							 }  
					}
				
			}
			
         else if(results[j].id != 0)
		  {
		    var option = document.createElement('option');
		    option.value=results[j].id;
		    option.text=results[j].name;
            try{
			   elmt.add(option,null); // standards compliant
		     }
		     catch(ex){
			   elmt.add(option); // IE only
		     }  
		  }

		}
		 
	  }
	  else{
	  for(var i in results)
	  {
		var option = document.createElement('option');
		if(divId =="scopeDiv" || divId == "scopeDivForEdit")
		  {
		  option.value=results[i].locationScope;
		  option.text=results[i].locationScopeValue;
		  }
		else if(divId.indexOf("stateDiv") != -1 || divId.indexOf("districtDiv") != -1)
		{
		  option.value=results[i].ids;
		  option.text=results[i].names;
		}
		else if(divId=="electionType")
		{
		option.value=results[i].candidateId;
		option.text=results[i].file;
		}
		else{
		  if(results[i].id!=0)
		  {
		  option.value=results[i].id;
		  option.text=results[i].name;
		  }
		}
		if(results[i].id!=0)
		  {
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
		}
	  }
	  }
}