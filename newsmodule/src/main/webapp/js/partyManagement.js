

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
		 if (jsObj.task == "generateKeyForReport")
		 {
		   $('#'+jsObj.id).text(myResults);
		   $('#'+jsObj.id).show();
		 }
		 else if (jsObj.task == "getCandidatesListByPartyId")
		 {
		   $("#"+jsObj.type+"Img").css("display","none");
			  	//$('#candidateAjaxImg').hide();
			 buildCandidatesForKeywords(myResults,jsObj.type,jsObj.val);
			 //clearOptionsListForSelectElmtId('mainCategory');
			 //createOptionsForSelectElement('mainCategory',myResults);
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
		else if(jsObj.task == "UpdatePhotoGallary")  
			{
           updateGallaryStatus(myResults);  
			}		
		else if(jsObj.task == "updateProfileDiscription")
			{
           showDiscriptionUpdateStatus(myResults);  
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
               buildResults(myResults,jsObj);
			}
			else if(jsObj.task == "getElectionType")
			{ 
			   clearOptionsListForSelectElmtId("electionType");
               buildResultsForElectType(myResults);
			}			
			else if(jsObj.task == "getStates")
			{ 
               buildResults(myResults,jsObj);
			}
			
			else if(jsObj.task == "getDistrictsByStateId")
			{ 
               buildResults(myResults,jsObj);
			}
			else if(jsObj.task == "constituenciesInDistrict")
			{ 
			   jsObj.divId ="constituencyDiv"+jsObj.index;
               buildResults(myResults,jsObj);
			}
			else if(jsObj.task == "getConstNotInGivenAreaType")
			{ 
			   jsObj.divId ="constituencyDiv"+jsObj.index;
               buildResults(myResults,jsObj);
			}
			else if(jsObj.task == "subRegionsInConstituency")
			{ 
			   jsObj.divId ="mandalDiv"+jsObj.index;
               buildResults(myResults,jsObj);
			}
			else if(jsObj.task == "hamletsOrWardsInRegion")
			{ 
			   jsObj.divId ="villageDiv"+jsObj.index;
               buildResults(myResults,jsObj);
			}
			else if(jsObj.task == "boothsInTehsilOrMunicipality")
			{ 
			   jsObj.divId ="villageDiv"+jsObj.index;
               buildResults(myResults,jsObj);
			}
			else if(jsObj.task == "createNewGallary" && jsObj.contentType =='News Gallary')
			{  
               showNewsGallaryCreateMsg(myResults);
			}
		
			else if(jsObj.task == "createNewGallary")  
			{ 
               showGallaryCreateMsg(myResults,jsObj.createOrUpdate);
			}
			else if(jsObj.task == "partyGallariesForUplaod" && jsObj.contentType=="News Gallary")
			{ 
			
				/*$('#existingFromText').attr({
				   id : this.id + '_' + 1 
				 });*/
               clearOptionsListForSelectElmtId(''+jsObj.type);
			   createOptionsForSelectElmtId(''+jsObj.type,myResults);
			                    $('#'+jsObj.type).multiselect();

  $('#'+jsObj.type).multiselect({
	  noneSelectedText:"Select Category"});
		$('#'+jsObj.type).multiselect('refresh');
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
				/*$('#existingFromText').attr({
				   id : this.id + '_' + 2 
				 });*/
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
		 showUserAccessLocationScopeList(myResults,jsObj.index);
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
			else if(jsObj.task == "getDesignationAndLocationByCandidateId"){
			   showDesignationDetails(myResults,jsObj);
			}
			else if(jsObj.task == "saveCandidate")
			 showCandidateSaveStatus(myResults,jsObj);
			 
			 else if(jsObj.task == "saveCandidate1")
			 {
			
			 showCandidateSaveStatus1(myResults,jsObj);
			 }
			  else if(jsObj.task == "updateCandidateDetails"){
			   showCandidateEditStatus(myResults,jsObj);
			  }

			else if(jsObj.task == "getDesignationAndLocationByPartyId")
			 {
			getTextCandidateId();
			 
			 }
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
			else if(jsObj.task == "getEditCandidatesListByPartyId")
			 buildCandidatesList(myResults,jsObj);
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
			else if(jsObj.task == "deleteFile")
			 buildDeleteFile(myResults,jsObj);

			else if(jsObj.task == "deleteFileForReport")
			 buildDeleteFileForReport(myResults,jsObj);
			 
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
function createListData(elmtId,optionsList)
{	
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
	 if(elmtId != 'assmblParlElecYearsSelect')
     document.getElementById('gallarySelectId').value = gGallaryId;
}

function createListData(elmtId,optionsList)
{	
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
	 if(elmtId != 'assmblParlElecYearsSelect')
     document.getElementById('gallaryPhotoSelectId').value = gGallaryId;
}

	function createGallary(contentType,createOrUpdate)
    {
	var galName = document.getElementById('pGallaryNameId').value;
	var galDesc = document.getElementById('pGallaryDescId').value;
	var isPublic = document.getElementById('publicRadioId').checked;
	var partyId=872; 	
	var makeThis = 'true';
    var gallaryId ='';
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	//var galEle = document.getElementById('gallaryPhotoSelectId').value;
	var eFlag = false;
     if(createOrUpdate=='Update')
	{
	  gallaryId = document.getElementById("gallaryId").value;
	}
	var str = '<font color="red">';

	if(galName.length == 0)
	{
		str += 'Gallery Name Required<br>';
		eFlag = true;
	}

	/*if(galEle == 0)
		{
		str +='Select Gallary';
		eFlag = true;
		}*/
	if(galName.length >100)
	{
		str += 'Gallery Name should be less than 100 Characters<br>';
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
	$("#creategalAjax").show();
	var jsObj =
		{ 
            name : galName,
		    desc : galDesc,
			visibility : makeThis,
			partyId : partyId,
			contentType : contentType,
			gallaryId : gallaryId,
			createOrUpdate:createOrUpdate,
			task : "createNewGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyManageAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function showGallaryCreateMsg(result,createOrUpdate)
{
	$("#creategalAjax").hide();
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var str = '';
	
	if(result.resultCode == 0)
	{
	clearGallaryFields();
		if(createOrUpdate=='Create')
		{
			if(result.exceptionMsg == null)
			 str += '<font color="green"><b>Gallery Created Successfully.</b>';
			else
			 str += '<font color="red"><b>Gallery Name is already exist.</b>'; 

		}
		else
		str += '<font color="green"><b>Gallery Updated Successfully.</b>';
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

	errorDivEle.innerHTML = str;
}
function showNewsGallaryCreateMsg(result)
{
	$("#createnewsgalAjax").hide();
	var errorDivEle = document.getElementById('newsErrorMsgDivId');
	var str = '';
	
	if(result.resultCode == 0)
	{
		if(result.exceptionMsg == null)
			str += '<font color="green"><b>Category Created Successfully.</b>';
		else
			str += '<font color="red"><b>Category Name is already exist.</b>';
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

	errorDivEle.innerHTML = str;
	document.getElementById('newsCateName').value='';
	document.getElementById('newsCateDesc').value='';
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
function uploadAFile()
{
	document.getElementById('uploadPhotoId');
	if(validateFileUpload())
	{
		$("#uploadphotogalAjax").show();
		disableButton('uploadPhotoId');

		$("#keywordListId1").autoSuggest("getKeyWordsBySearchCriteria.action", {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});


    var keywordStr = "";
	$(".as-selections li").each(function(){
              keywordStr +=$(this).text()+",";
				
            });
   
   var length = keywordStr.length;
    keywordStr = keywordStr.substr(0,length-2); 
	$("#keywordListId").val(keywordStr);


		var uploadHandler = {
				upload: function(o) {
					$("#uploadphotogalAjax").hide();
					uploadResult = o.responseText;
					showUploadStatus(uploadResult);
					enableButton('uploadPhotoId');
				}
			};

		
		YAHOO.util.Connect.setForm('uploadFilesForm',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadFilesAction.action',uploadHandler);
	}
	else
		return;
}
	function enableButton(id)
{
	document.getElementById(id).disabled  = false;
}

function disableButton(id)
{

	
	document.getElementById(id).disabled  = true;
}
function validateFileUpload()
{
	var fileTitle = document.getElementById('photofileTitleId').value;
	var fileDesc = document.getElementById('photofileDescId').value;
	var fileVal = document.getElementById("photofileId").value;
	var galId = document.getElementById("gallaryPhotoSelectId").value;
	var SpecialPageGalId = document.getElementById("uploadSpecialPageGalleryId");
	var spSelectId = document.getElementById("spSelectId");
	var spcheckboxIdElmt = document.getElementById("spcheckboxId");
	var canGalId = document.getElementById("uploadCandidateGalleryId");
	var candidateSelectId = document.getElementById("candidateSelectId");
	var ccheckboxIdElmt = document.getElementById("ccheckboxId");
	var fileDate = document.getElementById("existingFromTextPhoto").value;
	var flag = true;

	fileTitle = removeAllUnwantedCharacters(fileTitle);
	fileDesc =  removeAllUnwantedCharacters(fileDesc);
	document.getElementById('photofileTitleId').value = fileTitle;
	document.getElementById('photofileDescId').value = fileDesc;

	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '<font color="red">';
	if(fileDate.length == 0)
	{
		str +='File Date is Required';
		flag = false;
	}

	if(galId == 0)
	{
		str += 'Select Gallery<br>';
		flag = false;
	}
	if(fileTitle.length == 0)
	{
		str += 'Photo Title Required.<br>';
		flag = false;
	}
	if(fileTitle.length > 50)
	{
		str += 'Photo Title Should not exceed 50 Characters.<br><br>';
		flag = false;
	}
	if(fileDesc.length == 0)
	{
		str += 'Photo Description Required.<br>';
		flag = false;
	}
	if(fileDesc.length > 200)
	{
		str += 'Photo Description Should not exceed 200 Characters.<br>';
		flag = false;
	}
	if(fileVal.length == 0)
	{
		str += 'Photo Required.<br>';
		flag = false;
	}
	if(spcheckboxIdElmt!=null && spcheckboxIdElmt.checked)
	{
	   if(spSelectId !=null && spSelectId.value == 0)
	  {
		str += 'Select Special Page <br>';
		flag = false;
	  }
     else if(SpecialPageGalId !=null && SpecialPageGalId.value == 0)
	  {
		str += 'Select Special Page Gallery.<br>';
		flag = false;
	  }
	  
	}
	if(ccheckboxIdElmt!=null && ccheckboxIdElmt.checked)
	{
		   if(candidateSelectId !=null && candidateSelectId.value == 0)
		  {
			str += 'Select Candidate <br>';
			flag = false;
		  }
		 else if(canGalId !=null && canGalId.value == 0)
		  {
			str += 'Select Candidate Gallery.<br>';
			flag = false;
		  }
		  
		}
	str += '</font>';
	errorDivEle.innerHTML = str;
	if(flag == false)
	{
		$('html, body').animate({
         scrollTop: $("#fileUploadErrorMsgDivId").offset().top
     }, 2000);
	}
	return flag;
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
  // var strng = str.replace(/[\\\%\&\#\"+"]/g," "); commented by srishailam
	
	/*var strng = str.replace(/[\\\&\#\"+"]/g," ");  
    return replaceEnterKey(strng,"  ");*/
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
	if(result.search('sessionExpired') != -1)
	{
	openDialogForLoginWindow();
	}
	else if(result.search('success') != -1)
	{
		//clearNewsUploadFileFields1();
		//str += '<font color="green"><b>News Uploaded Successfully.</b>';
        
		addSource = 0;
		addFile = 0;
		who = 0;
		whome = 0;
		//errorDivEle.innerHTML = str;
		
		 $('.ui-multiselect-menu').each(function() {
	            $(this).remove();
	        });
		uploadNewsForPartyAndCandidate(null);
		
		getKeywordList();
		
       setTimeout(showSuccessMsg,3000);
	 
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

 $("#uploadNewsFileErrorDiv").html('<font color="green"><b>News Uploaded Successfully.</b></font>');
}

function clearNewsUploadFileFields1()
{

    $('#gallaryId').val(0);
	$('#newsfileTitle , #newsfileDescription , #keywords , #existingFromTextNews , #ImagenewsfileId').val('');
	$('#candidateList').find('option').remove();
	$('#uploadFilesDiv').html('');
    $("#newsDesc").val('');

    newKeywordsInserting();

	 getScopes(0);
	 getSource("source");
	 getLanguage("language");
	 getNewsImportance();
	 getCategory();
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

	 $("#keywordListId1").autoSuggest("getKeyWordsBySearchCriteria.action", {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});


}

function clearNewsUploadFileFields()
{
	$("#addMoreFilesDiv").html('');
	$("#otherProNewsDiv").html('');
	document.getElementById('newsfileTitle').value = '';
	document.getElementById('newsfileDescription').value = '';
	//document.getElementById('keywords').value = '';
	//document.getElementById('existingFromText').value = '';
	document.getElementById('source').value = '';
	document.getElementById('newsfileId').value = '';
	//document.getElementById('publicRadioId').checked = true;
	document.getElementById('existingFromTextNews').value = '';
	document.getElementById('ImagenewsfileId').value = '';
	document.getElementById('newsDescriptionId').value = '';
	getScopes(0);

	newKeywordsInserting();
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
	
function showNewsGallaey(fileId)
{
	  $("#newsGallaryDiv").css("display","block");
	  $("#newsAssignGallaryDiv").css("display","none");
	  $("#profileManagementMainOuterDiv5").css("display","none");
  if(!formValidation()){
  document.getElementById("profileManagementMainOuterDiv2").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv3").style.display = 'block';
  $("#profileManagementHeaderDiv3").css('display','block');
  document.getElementById("videoGallaryDiv").innerHTML=''; 
  document.getElementById("profileManagementMainOuterDiv4").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv6").style.display = 'none';
 $("#profileManagementMainOuterDiv7").css("display","none");
 $("#profileManagementMainOuterDivStatus").css("display","none");
  document.getElementById("videoGallaryDiv").innerHTML=''; 
  /*$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
  $("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
  $("#newsGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
  $("#newsEditId").css({"background":"none repeat scroll 0 0 #0063DC"});*/

  //buildCreateNewsCategory();
  
  $("#profileManagementHeaderDiv3").css("display","block");
  $("#dateSelectDiv").css("display","none");
  //buildUploadNewsForMultipleUsers();
  
  uploadNewsForPartyAndCandidate(fileId);
  
  }
}

function buildCreateNewsCategory()
{
   var str ='';
	str+='<div id="content"  class="contianer well">';
	//str +=  '<table style="margin:5px;width:40%;margin-left:50px;">';
	/*str +='<table class="aligncenter">';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="btn btn-success highlight" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="btn btn-success highlight" value="Upload News" onclick="buildUploadNews()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News For Multiple Users" onclick="buildUploadNewsForMultipleUsers()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';*/
	//str += '<fieldset class="imgFieldset" style="width:449px;">';
	str += '<h2 align="center" style="margin-left: 35px;margin-bottom:5px;"> Create New Category</h2>';
	str +='<div id="cHintDiv" align="center"> Note: Name and description should not contain #,$,%,& Special charactors.	</div>';
	str +='<div id="CErrMsgDiv" align="center"></div>';
	str+='<table class="aligncenter"><tr><td><div id="newsErrorMsgDivId" /></td></tr></table>';
	str += '<table class="aligncenter">';
    
    str +='<tr><td><b><font>Main Category <font class="requiredFont">*</font></font></b></td><td><select id="categoriesForGallary" name="categoriesForGallary" onChange="clearDiv(\'CErrMsgDiv\');"></td></tr>';
	str +='<tr><td><b><font>Category Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="newsCateName" size="25" maxlength="100" onkeyup="clearDiv(\'CErrMsgDiv\');"/></td></tr>';

	str += '<tr><td><b><font> Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="newsCateDesc" cols="27" rows="3" name="requirement" onkeyup="clearDiv(\'CErrMsgDiv\');"></textarea></td></tr></table>';
	str += '<table class="aligncenter"><tr><td><label class="radio" style="display:none;"><input type="radio" value="public" name="visibility" id="newsPublicRadio" checked="true"><b><font>Visible to Public Also</font></b></input></label></td></tr>';
	str += '<tr><td><label class="radio" style="display:none;"><input type="radio" value="private" name="visibility" id="newsPrivateRadio"><b><font>Make This Private</font></b></input></label></td></tr></table>';
	str+='<div id="createnewsgalAjax" style="display:none;margin-left:430px;clear:both;"><img src="images/search.jpg"/></div>';
	str += '<table class="aligncenter"><tr><td><input type="button" class="btn btn-success highlight" value="Create Category" style="background-color:#57B731" onClick="createCategory()"></td><td><input type="button" class="btn btn-success highlight" value="Cancel"  onClick="clearDiv(\'newsGallaryDiv\');"></td></tr></table>';

	str += '<div>';
	//str += '</fieldset>';
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

function getDistricts1(stateId,index){
  var jsObj =
		{ 
            time : timeST,
			stateId : stateId,
			task:"getDistrictsByStateId",
			divId:"districtDiv"+index,
			index:index
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}

	function createCategory()
{
	$('#CErrMsgDiv').html('');
	var newsCatrgoryName = document.getElementById('newsCateName').value;
	var newsCatrgoryDesc = document.getElementById('newsCateDesc').value;
	//var isPublic = document.getElementById('newsPublicRadio').checked;
	var partyId = 872;
	var makeThis = 'false';
	var categoryId = $("#categoriesForGallary").val();
	
    if(isValid(newsCatrgoryName)){
		$('#CErrMsgDiv').html('<b style="color:red">Category Name should not contain #,$,%,& Special charactors</b>');
		return false;
	}
	if(isValid(newsCatrgoryDesc)){
		$('#CErrMsgDiv').html('<b style="color:red">Description should not contain #,$,%,& Special charactors</b>');
		return false;
	}
	
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

	/*if(isPublic)
		makeThis = 'false';*/
	
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

function getScopes(index){
  
 var jsObj =
		{ 
            time : timeST,
			divId:"scopeDiv"+index,
  		    task:"getScopesForNewSearch",
			index:index
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}
function buildResults(results,jsObj){
     var divId = jsObj.divId;
	$('#'+divId).find('option').remove();

  var locationScopeId = $("#scopeDiv"+jsObj.index).val(); 
 
  var elmt = document.getElementById(divId);
         if(divId.indexOf("scopeDiv") > -1)
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

	  if(divId.indexOf("mandalDiv") > -1)
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
					if((results[j].name.toLowerCase()).indexOf("muncipality") != -1 || (results[j].name.toLowerCase()).indexOf("greater") != -1 || (results[j].name.toLowerCase()).indexOf("corporation") != -1){
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
		if(divId.indexOf("scopeDiv") > -1)
		  {
		  option.value=results[i].locationScope;
		  option.text=results[i].locationScopeValue;
		  }
		else if(divId.indexOf("stateDiv") > -1 || divId.indexOf("districtDiv") > -1)
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
/*if(distritcId != '' )
	$('#districtDiv').val(distritcId);
if(cosntiId != '' )
	$('#constituencyDiv').val(cosntiId);*/
}
function buildResultsForElectType(results){

  var elmt = document.getElementById('electionType'); 
  var option1 = document.createElement('option');	
      option1.value='0';
	  option1.text='Select Election Type';	
	  try
		{
			elmt.add(option1,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option1); // IE only
		}
  for(var i=0 ;i<2 ;i++)
	{
	  var option = document.createElement('option');	
      option.value=results[i].candidateId;
	  option.text=results[i].file;		
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
 function getAllDetails(id,task,areaType,constId,index)
   {
        var jsObj =
		{ 
            time : timeST,
			id:id,
			task:task,
			taskType:"",
			selectElementId:"",
			address:"",
			areaType:areaType,
			constId:constId,
			index:index
		};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "locationsHierarchiesAjaxAction.action?"+rparam;						
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
function clearAllElmts(scopeId,value,index){
 if(scopeId==4)
 { 
    if(value==1)
	clearAllForSelect("constituencyDiv"+index);
	
 }
 if(scopeId==5)
 {
   if(value==1)
   {
	clearAllForSelect("constituencyDiv"+index);
	clearAllForSelect("mandalDiv"+index);
	}
   if(value==2)
	clearAllForSelect("mandalDiv"+index);
 }
 if(scopeId==6)
 {
   if(value==1)
   {
	clearAllForSelect("constituencyDiv"+index);
	clearAllForSelect("mandalDiv"+index);
	clearAllForSelect("villageDiv"+index);
	}
	if(value==2)
	{
	clearAllForSelect("mandalDiv"+index);
	clearAllForSelect("villageDiv"+index);
	}
	if(value==3)
	clearAllForSelect("villageDiv"+index);
 }
 if(scopeId==9)
 {
   if(value==1)
   {
	clearAllForSelect("constituencyDiv"+index);
	clearAllForSelect("mandalDiv"+index);
	clearAllForSelect("villageDiv"+index);
	}
	if(value==2)
	{
	clearAllForSelect("mandalDiv"+index);
	clearAllForSelect("villageDiv"+index);
	}
	if(value==3)
	clearAllForSelect("villageDiv"+index);
 }

}

function buildAssmblParlElecYears(electionType)
{ 
     var str ='';
		 str +='<table style="margin-left:45px;">';
		 str +='  <tr>';
		 str +='	   <td class="tdWidth1" style="width:153px;">Election Year : <font class="requiredFont">*</font></td>';
		 str +='	   <td class="selectWidthPadd"><select class="selectWidth" id="assmblParlElecYearsSelect" style="width:175px;text-align:left;" name="electionId"/></td>';
		 str +='  </tr>';
		 str +='</table>';
		 document.getElementById("assmblParlElecYears").innerHTML = str; 
		 getAssmblParlElecYears(electionType);
}
function getAssmblParlElecYears(electionType)
{
    var stateId = "";
	if(document.getElementById("stateDiv") != null)
	{
	  var stateEle = document.getElementById("stateDiv");
	  stateId = stateEle.options[stateEle.selectedIndex].value;
	}
    var jsObj =
		{ 
            time : new Date().getTime(),
			task:"getAssmblParlElecYears",
			electionType:electionType,
			stateId:stateId
		};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "getElectionIdsAndYears.action?"+rparam;						
	 callAjax(jsObj,url);
}
function buildStatesForAssParl(type)
{   
    document.getElementById("assmblParlElecYears").innerHTML="";
    var str ='';
		 str +='<table style="margin-left:45px;">';
		 str +='  <tr>';
		 str +='	   <td class="tdWidth1" style="width:153px;">State : <font class="requiredFont">*</font></td>';
		 str +='	   <td><select id="stateDiv" style="margin-left:5px;width: 137px;"  class="selectWidth" name="locationValue" onchange="buildAssmblParlElecYears(\''+type+'\');"/></td>';
		 str +='  </tr>';
		 str +='</table>';
		 document.getElementById("assemblyStates").innerHTML = str;
		 getStatesForPartyManifesto(); 
}
 function getLocationsForPartyManifUpld(id){
  document.getElementById("assemblyStates").innerHTML = "";
 if(document.getElementById("assmblParlElecYears") != null)
   document.getElementById("assmblParlElecYears").innerHTML = "";
   document.getElementById("parlCounState").innerHTML = "";
     if(id==1)
	 {
	   var str ='';
		 str +='<table style="padding-left:203px;">';
		 str +='  <tr>';
		 str +='	   <td><input type="radio" name="assemblyRadio" id="CountryRadio" checked="checked" onclick="buildParlCountryYears();">Country</input></td>';
		 str +='	   <td><input type="radio" name="assemblyRadio" id="stateRadio" onclick="buildStatesForAssParl(\'Parliament\');">State</input></td>';
		 str +='  </tr>';
		 str +='</table>';
		 document.getElementById("parlCounState").innerHTML = str;
	   buildAssmblParlElecYears('Parliament');
	   
	 }
	 if(id==2)
	 { 
	    document.getElementById("parlCounState").innerHTML ="";
        buildStatesForAssParl('Assembly');		 
	 }
 }
 function buildParlCountryYears()
 {
    document.getElementById("assemblyStates").innerHTML = "";
    if(document.getElementById("assmblParlElecYears") != null)
      document.getElementById("assmblParlElecYears").innerHTML = "";
    buildAssmblParlElecYears('Parliament');
 }


function getLocations(id,index){

   var str = '';
  if(id==0 || id == 1){
   if(index == 0){
	str +='<span style="display:none">changeDel</span>';
	}else{
	str +='<a href="javascript:{};" onclick="removeThisLocation(\'deleteMultiLoc'+index+'\')" title="Click here to remove this location" class="icon-trash delStyle"></a>';
	}
   $("#showScopeSubs"+index).html(str);
   }
    
  else if(id==2)
  {

   str += '<div class="span2">';
   str += ' <label>State';
   if(index == 0){
	str +='<span style="display:none">changeDel</span>';
	}else{
	str +='<a href="javascript:{};" onclick="removeThisLocation(\'deleteMultiLoc'+index+'\')" title="Click here to remove this location" class="icon-trash delStyle"></a>';
   }
   str += '</label>';
   str += ' <select class="input-block-level" name="locationValue['+index+']" style="margin-left:5px;width: 137px;"  id="stateDiv'+index+'" onchange="removeAndAddSelection(\'stateDiv'+index+'\');removeAndAddSelection(\'stateDiv'+index+'\');"></select>';
   str +='</div>';
   
   $("#showScopeSubs"+index).html(str);
   getStatesForSpecialPage(index);
  }
  else if(id==3)
  {
   
   str += '<div class="span2">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv'+index+'" style="margin-left:5px;width: 137px;" onchange="removeAndAddSelection(\'stateDiv'+index+'\');clearAll(\'districtDiv'+index+'\');getDistricts1(this.options[this.selectedIndex].value,'+index+')"></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>District';
    if(index == 0){
	str +='<span style="display:none">changeDel</span>';
	}else{
	str +='<a href="javascript:{};" onclick="removeThisLocation(\'deleteMultiLoc'+index+'\')" title="Click here to remove this location" class="icon-trash delStyle"></a>';
   }
   str += '</label>';
   str += ' <select class="input-block-level" name="locationValue['+index+']" onchange="removeAndAddSelection(\'districtDiv'+index+'\');" id="districtDiv'+index+'"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   $("#showScopeSubs"+index).html(str);
   getStatesForSpecialPage(index);
  }
  else if(id==4)
  {
   
   str += '<div class="span2">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv'+index+'" style="margin-left:5px;width: 137px;" onchange="removeAndAddSelection(\'stateDiv'+index+'\');clearAllElmts(4,1,'+index+');clearAll(\'districtDiv'+index+'\');getDistricts1(this.options[this.selectedIndex].value,'+index+')"></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" id="districtDiv'+index+'" onchange="removeAndAddSelection(\'districtDiv'+index+'\');clearAll(\'constituencyDiv'+index+'\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';
  
   str += '<div class="span2">';
   str += ' <label>Assembly Consti';
   if(index == 0){
	str +='<span style="display:none">changeDel</span>';
	}else{
	str +='<a href="javascript:{};" onclick="removeThisLocation(\'deleteMultiLoc'+index+'\')" title="Click here to remove this location" class="icon-trash delStyle"></a>';
	}
   str +='</label>';
   str += ' <select class="input-block-level" name="locationValue['+index+']" onchange="removeAndAddSelection(\'constituencyDiv'+index+'\');"  id="constituencyDiv'+index+'"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   $("#showScopeSubs"+index).html(str);
  
   getStatesForSpecialPage(index);
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
   
   str += '<div class="span2">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv'+index+'" style="margin-left:5px;width: 137px;" onchange="removeAndAddSelection(\'stateDiv'+index+'\');clearAllElmts(5,1,'+index+');clearAll(\'districtDiv'+index+'\');getDistricts1(this.options[this.selectedIndex].value,'+index+')"></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" id="districtDiv'+index+'" onchange="removeAndAddSelection(\'districtDiv'+index+'\');clearAllElmts(5,2,'+index+');clearAll(\'constituencyDiv'+index+'\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';
  
   str += '<div class="span2">';
   str += ' <label>Assembly Consti</label>';
   str += ' <select class="input-block-level" id="constituencyDiv'+index+'" onchange="removeAndAddSelection(\'constituencyDiv'+index+'\');clearAll(\'mandalDiv'+index+'\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   str += '<div class="span2">';
   str += ' <label>Mndl/Mun/Corp';
   if(index == 0){
	str +='<span style="display:none">changeDel</span>';
	}else{
	str +='<a href="javascript:{};" onclick="removeThisLocation(\'deleteMultiLoc'+index+'\')" title="Click here to remove this location" class="icon-trash delStyle"></a>';
	}
   str += '</label>';
   str += ' <select class="input-block-level" name="locationValue['+index+']" onchange="removeAndAddSelection(\'mandalDiv'+index+'\');" id="mandalDiv'+index+'"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   $("#showScopeSubs"+index).html(str);
  
   getStatesForSpecialPage(index);
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

   str += '<div class="span2">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv'+index+'" style="margin-left:5px;width: 137px;" onchange="removeAndAddSelection(\'stateDiv'+index+'\');clearAllElmts(6,1,'+index+');clearAll(\'districtDiv'+index+'\');getDistricts1(this.options[this.selectedIndex].value,'+index+')"></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" id="districtDiv'+index+'" onchange="removeAndAddSelection(\'districtDiv'+index+'\');clearAllElmts(6,2,'+index+');clearAll(\'constituencyDiv'+index+'\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';
  
   str += '<div class="span2">';
   str += ' <label>Assembly Consti</label>';
   str += ' <select class="input-block-level" id="constituencyDiv'+index+'" onchange="removeAndAddSelection(\'constituencyDiv'+index+'\');clearAllElmts(6,3,'+index+');clearAll(\'mandalDiv'+index+'\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   str += '<div class="span2">';
   str += ' <label>Mndl/Mun/Corp</label>';
   str += ' <select class="input-block-level" id="mandalDiv'+index+'"  name="mandalId['+index+']" onchange="removeAndAddSelection(\'mandalDiv'+index+'\');clearAll(\'villageDiv'+index+'\');getAllDetails(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>Village/Ward/Div';
   if(index == 0){
	str +='<span style="display:none">changeDel</span>';
	}else{
	str +='<a href="javascript:{};" onclick="removeThisLocation(\'deleteMultiLoc'+index+'\')" title="Click here to remove this location" class="icon-trash delStyle"></a>';
	}
   str += ' </label>';
   str += ' <select class="input-block-level" id="villageDiv'+index+'" onchange="removeAndAddSelection(\'villageDiv'+index+'\');" name="locationValue['+index+']"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   $("#showScopeSubs"+index).html(str);
   
   getStatesForSpecialPage(index);
  }
  else if(id==9)
  {

   str += '<div class="span2">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv'+index+'" style="margin-left:5px;width: 137px;" onchange="removeAndAddSelection(\'stateDiv'+index+'\');clearAllElmts(9,1,'+index+');clearAll(\'districtDiv'+index+'\');getDistricts1(this.options[this.selectedIndex].value,'+index+')"></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" id="districtDiv'+index+'"  onchange="removeAndAddSelection(\'districtDiv'+index+'\');clearAllElmts(9,2,'+index+');clearAll(\'constituencyDiv'+index+'\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';
  
   str += '<div class="span2">';
   str += ' <label>Assembly Consti</label>';
   str += ' <select class="input-block-level" id="constituencyDiv'+index+'" onchange="removeAndAddSelection(\'constituencyDiv'+index+'\');clearAllElmts(9,3,'+index+');clearAll(\'mandalDiv'+index+'\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'\',\'\','+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>Mndl/Mun/Corp</label>';
   str += ' <select class="input-block-level" id="mandalDiv'+index+'" name="mandalId['+index+']" onchange="removeAndAddSelection(\'mandalDiv'+index+'\');clearAll(\'villageDiv'+index+'\');getAllDetails(this.options[this.selectedIndex].value,\'boothsInTehsilOrMunicipality\',\'\',document.getElementById(\'constituencyDiv'+index+'\').options[document.getElementById(\'constituencyDiv'+index+'\').selectedIndex].value,'+index+')"><option value="0">Select Location</option></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>Village/Ward/Div';
   if(index == 0){
    str +='<span style="display:none">changeDel</span>';
   }else{
    str +='<a href="javascript:{};" onclick="removeThisLocation(\'deleteMultiLoc'+index+'\')" title="Click here to remove this location" class="icon-trash delStyle"></a>';
   }
   str += '</label>';
   str += ' <select class="input-block-level" id="villageDiv'+index+'" onchange="removeAndAddSelection(\'villageDiv'+index+'\')"; name="locationValue['+index+']"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   $("#showScopeSubs"+index).html(str);
    
   getStatesForSpecialPage(index);
  }
  // setTimeout("setStateId();",1000);

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
function getEnadu()
{


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
 
function profileDiscriptionDiv()
{

	var str ='';
	str += '<div id="content" style="width:650px;">';
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +='<table style="margin:5px;margin-left:auto;width:37%;margin-right:auto;margin-top:7px;">';
	str += '<tr>';
	str += '<td><input type="button" class="btn btn-success highlight" value="Add Description" onclick="addDescriptionDiv()"></td>';
	str += '<td><input type="button" class="btn btn-success highlight" value="Update Description" onclick="updateDescriptionDiv()"></td>';
	str += '</tr>';
	str += '</table>'
    str += '<fieldset class="imgFieldset" style="width:400px;">';	
	str += '<center><b style="font-size:15px"><font color="#669900">Add The Profile Description </font> </b> </center>';
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<div id="galErrorMsgDivId"></div>';
	str += '<div id="fileUploadErrorMsgDiv"></div>';
	str += '<tr>';
	str += '<td>';
	str += '<b><font>Profile  Description</font></b></td><td><textarea id="profileDescId" name="profileDescription" cols="30" rows="5"></textarea></td></tr>';
	str += '</table>';
	str += '<table align="center" style="margin:5px;margin-left:auto;margin-right:auto;margin-bottom:7px;width:33%"><tr><td><input type="button" class="btn btn-success highlight" value="Add Discription" style="background-color:#57B731" onClick="addProfileDiscription()"></td><td><input type="button" class="btn btn-success highlight" value="Cancel" onclick="clearDiv(\'discriptionDiv\')" ></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	

	document.getElementById("discriptionDiv").innerHTML = str;

} 
 function addDescriptionDiv()
 {
 var str ='';
	str += '<div id="content" style="width:650px;">';
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +='<table style="margin:5px;margin-left:auto;width:37%;margin-right:auto;margin-top:7px;">';
	str += '<tr>';
	str += '<td><input type="button" class="btn btn-success highlight" value="Add Description" onclick="addDescriptionDiv()"></td>';
	str += '<td><input type="button" class="btn btn-success highlight" value="Update Description" onclick="updateDescriptionDiv()"></td>';
	str += '</tr>';
	str += '</table>'
    str += '<fieldset class="imgFieldset" style="width:400px;">';	
	str += '<center><b style="font-size:15px"><font color="#669900">Add The Profile Description </font> </b> </center>';
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<div id="galErrorMsgDivId"></div>';
	str += '<div id="fileUploadErrorMsgDiv"></div>';
	str += '<tr>';
	str += '<td>';
	str += '<b><font>Profile  Description</font></b></td><td><textarea id="profileDescId" name="profileDescription" cols="30" rows="5"></textarea></td></tr>';
	str += '</table>';
	str += '<table align="center" style="margin:5px;margin-left:auto;margin-right:auto;margin-bottom:7px;width:33%"><tr><td><input type="button" class="btn btn-success highlight" value="Add Discription" style="background-color:#57B731" onClick="addProfileDiscription()"></td><td><input type="button" class="btn btn-success highlight" value="Cancel" onclick="clearDiv(\'discriptionDiv\')"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	

	document.getElementById("discriptionDiv").innerHTML = str;
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
	//var url = "createNewGallaryAction.action?"+rparam;
	var url = "deletePartyGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
	}
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

function showTheNewsToUpdate()
{
  responseFileIdsArray = new Array();
  $("#selectedNewsCount").html('0');
  $("#newEditCandidateDiv").css("display","none");
  document.getElementById("profileManagementMainOuterDiv2").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';
  $("#profileManagementMainOuterDiv5").css("display","none");
  $('#profileManagementMainOuterDiv4').show();
  document.getElementById("profileManagementMainOuterDiv6").style.display = 'none';
   $("#profileManagementMainOuterDiv7").css("display","none");
   $("#profileManagementMainOuterDivStatus").css("display","none");
   $('#showKeywordsDiv').css("display","none");
 /* $("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
  $("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
  $("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
  $("#newsEditId").css({"background":"none repeat scroll 0 0 #F61D50"});*/
  $("#newDesignationDiv").css("display","none");
  $("#newPartyCreationDiv").css("display","none");
  $("#dateSelectDiv").css("display","block");
  $("#newsFromDateId").val('');
  $("#newsToDateId").val('');
  $('#mergeKeywordDiv').css("display","block");
   populateDate();
  getTotalNewsWithPagination();
 
 // buildNewsDetails();

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

		  if (jsObj.task == "getCandidates")
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
		else if(jsObj.task == "saveFileComment"){

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
			}else if(jsObj.task == "getUserTrackingDetails"){
			   buildUserTracking(myResults);
			}
			else if(jsObj.task == "getUserTrackingExcelDetails"){
			       if(myResults == null || myResults.name == "fail"){
					    alert("Error occured while generationg excel ");
						$.unblockUI();
					  }else{
					     $.unblockUI();
			              window.open(myResults.url);
					  }
			}
		
}
		catch(e)
		{   
		  $.unblockUI();
		  
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
	//alert(keywordGallaries);
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

	str+='<div>';
	str+='<table id="newsDetailsTable">';
	str+='<thead>';
	 str+='<tr>';
	  
	  /*str+='<th>CATEGORY</th>';
	  str+='<th>GALLARY</th>';*/
	  
	  str+='<th>SOURCE</th>';
	  str+='<th>TITLE</th>';
	  str+='<th>DESCRIPTION</td>';
	  str+='<th>IMPACT AREA </th>';
	  str+='<th>AREA NAME</th>';
	  str+='<th>NEWS DATE</th>';
	  str+='<th>Add Response</th>';
	 // str+='<td>DELETE</td>';
	  str+='</thead>';
	 str+='</tr>';
	 for(var i in results)
	 {
		 str+='<tr>';
		    /*str+='<td>'+results[i].categoryType+'</td>';
	        str+='<td>'+results[i].gallaryName+'</td>';*/
	        
			str+='<td>'+results[i].source+'</td>';
			//if(results[i].source.indexOf("Eenadu Telugu") != -1)
			if(results[i].eenaduTeluguFontStr != null && results[i].eenaduTeluguFontStr == "Eenadu Telugu")
		     str+='<td><span class="enadu">'+results[i].fileTitle1+'</span></td>';
			else
	         str+='<td>'+results[i].fileTitle1+'</td>';

			
			//if(results[i].eenaduTeluguFontStr != null && results[i].eenaduTeluguFontStr == "Eenadu Telugu")
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
			
			/*str+='<td><a type="button"  title="Click here to edit" href="javascript:{editNewsDetails('+results[i].fileId+',\''+results[i].source+'\');}"><i class="icon-pencil"></i></a>';
			str+='<a type="button" title="Click here to delete" href="javascript:{updateDeleteNews(\'Delete\','+results[i].fileId+');}" ><i class="icon-remove-sign"></i></a></td>';*/
			
			str +='<td><a type="button"  title="Click here to add response" href="javascript:{addNewsResponseDetails('+results[i].fileId+');}">Add Response</td>';
			
 		str+='</tr>';
           
	 }
 	str+='</table>';
	str+='</div>';
	$('#profileManagementMainOuterDiv4').html(str);

	$(function() {
		var table = $('#newsDetailsTable').dataTable({
			"iDisplayLength": 10,
			//"iDisplayStart":5
			"bPaginate": true,

		});
		table.fnPageChange(pageNumber,true);
		//$('#rrrr').dataTable();
		//var oTable = $('#rrrr').dataTable({
		//	iDisplayStart:1
		});
  //var oSettings = oTable.fnSettings();

  //console.log(oSettings);
	//});
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
  
  
  /*$(".newsResponseCheckId").each(function(){
    if($(this).is(':checked'))
     fileIdStr +=""+$(this).attr('value')+",";
  });*/

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
			else if(jsObj.task == "getAllKeywordsByCount")
			{
			  buildAllKeywords(myResults);			 
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


function validateUploadFileDetails()
{
   
   $("#uploadNewsFileErrorDiv").html('');
   var flag = true;
   var fileTitle = $.trim($("#newsfileTitle").val());
   var fileDesc  = $.trim($("#newsfileDescription").val());
   var synopsysDesc  = $.trim($("#newsSynopsysDesc").val());
   var fileDate = $("#newsdatedatepic").val();
   var newsImporatnce =document.getElementById("newsimportance").value;
   var sourceArr = new Array();


	var errorDivEle = document.getElementById('uploadNewsFileErrorDiv');
	
	var str = '<font color="red">';
	var flag1 =true;
	 
  if($('#filesourceId0 option:selected').length == 0 )
  {
		str +='Please logout and login again, to upload news<br>';
		flag = false;
  }
  else
  {
	if(fileTitle.length == 0)
	{
		str += ' Title is Required.<br>';
		flag = false;
	}
	if(fileDesc.length == 0)
	{
		str += 'News Description is Required.<br>';
		flag = false;
	}
	if(synopsysDesc.length == 0)
	{
		str += 'News Synopsys is Required.<br>';
		flag = false;
	}
	if(fileDate.length == 0)
	{
		str +='File Date is Required<br>';
		flag = false;
	}
	if(newsImporatnce == 0)
	{
		str += ' News Importance is Required.<br>';
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
		
	 $('.fileImgCls').each(function() {
		 if($.trim($(this).val()).length == 0)
		{
		  var key =  $(this).attr('key');
		  var desc = $.trim($("#"+key+"").val());
	      if(desc.length == 0)
	      { 
		    str+='Detailed News Description Or File Path is Required.<br>';
		    flag = false;
		    return false;
		  }
		}		 
		   
      });
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
			  str += ' Page Number must be number.<br>';
				 flag = false;
			}
			else
            {
			 var pageNoTemp = ""+$.trim($(this).val())+"";
			 if(pageNoTemp.indexOf(".") != -1)
			 {
			  str += ' Page Number must be Integer.<br>';
			  flag = false;
			 }

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
			 else{
			 var newsLengthTemp = $.trim($(this).val());
			 if(newsLengthTemp.indexOf(".") != -1)
			 {
			  str += ' News Length must be Integer.<br>';
			  flag = false;
			 }

			 }
	       }
       });

	$('.fileNewSourceCls').each(function() {

		 var sourceId = $(this).val();			   
		   if(sourceArr.indexOf(sourceId) != -1){
				str += ' File Sources must be different (Ex: Eenadu,Sakshi...etc).<br>';
				 flag = false;
				 return false;
			}
			else
				sourceArr.push($(this).val());

       });
	   
	   
	 $('.addFileImgCls').each(function() {
           if($.trim($(this).val()).length == 0)
	       {
		     str += ' File is Required.<br>';
		     flag = false;
			 return false;
	       }
       });
	if(fileTitle.length >1000)
	{
		str += 'Title should be less than 1000 Characters<br>';
		flag = false;
	}
	if(fileDesc.length > 2000)
	{
		str += 'News Description Should not exceed 2000 Characters.<br>';
		flag = false;
	}
var allLocSeleted = true;
$('.scopeLevel').each(function() {
   var scope = $(this).val();
   var key = $(this).attr("key");   
	if(scope == 0 )
	{
	    if(!(str.indexOf("Location Scope is Required") > -1)){
		 str += 'Location Scope is Required.';
		}
		flag = false;
		allLocSeleted = false;
	}
	else if(scope == 2)
	{
		var stateVal=document.getElementById('stateDiv'+key).value;
		if(stateVal == 0)
		{
		if(!(str.indexOf("State Name is Required") > -1)){
		  str += 'State Name is Required.';
		}
		flag = false;
		allLocSeleted = false;
		}
	}
	else if(scope == 3)
	{
		var stateVal=document.getElementById('stateDiv'+key).value;
		var districtVal=document.getElementById('districtDiv'+key).value;
		if(stateVal == 0)
		{
		if(!(str.indexOf("State Name is Required") > -1)){
		 str += 'State Name is Required.<br>';
		}
		flag = false;
		allLocSeleted = false;
		}
		if(districtVal == 0)
		{
		if(!(str.indexOf("District Name is Required") > -1)){
		 str += 'District Name is Required.';
		}
		flag = false;
		allLocSeleted = false;
		}
	}
	else if(scope == 4)
	{
		var stateVal=document.getElementById('stateDiv'+key).value;
		var districtVal=document.getElementById('districtDiv'+key).value;
		var constituencyVal=document.getElementById('constituencyDiv'+key).value;
		if(stateVal == 0)
		{
		if(!(str.indexOf("State Name is Required") > -1)){
		 str += 'State Name is Required.<br>';
		}
		flag = false;
		allLocSeleted = false;
		}
		if(districtVal == 0)
		{
		if(!(str.indexOf("District Name is Required") > -1)){
		 str += 'District Name is Required.<br>';
		}
		flag = false;
		allLocSeleted = false;
		}
		if(constituencyVal == 0)
		{
		if(!(str.indexOf("Constituency Name is Required") > -1)){
		 str += 'Constituency Name is Required.';
		}
		flag = false;
		allLocSeleted = false;
		}
	}
	else if(scope == 5)
	{
		var stateVal=document.getElementById('stateDiv'+key).value;
		var districtVal=document.getElementById('districtDiv'+key).value;
		var constituencyVal=document.getElementById('constituencyDiv'+key).value;
		var mandalVal=document.getElementById('mandalDiv'+key).value;
		if(stateVal == 0)
		{
		if(!(str.indexOf("State Name is Required") > -1)){
		 str += 'State Name is Required.<br>';
		}
		flag = false;
		allLocSeleted = false;
		}
		if(districtVal == 0)
		{
		if(!(str.indexOf("District Name is Required") > -1)){
		 str += 'District Name is Required.<br>';
		}
		flag = false;
		allLocSeleted = false;
		}
		if(constituencyVal == 0)
		{
		if(!(str.indexOf("Constituency Name is Required") > -1)){
		 str += 'Constituency Name is Required.<br>';
		}
		flag = false;
		allLocSeleted = false;
		}
		if(mandalVal == 0)
		{
		if(!(str.indexOf("Mandal Name is Required") > -1)){
		 str += 'Mandal Name is Required.';
		}
		flag = false;
		allLocSeleted = false;
		}
	}
	else if(scope == 6 || scope == 8 || scope == 9)
	{
		var stateVal=document.getElementById('stateDiv'+key).value;
		var districtVal=document.getElementById('districtDiv'+key).value;
		var constituencyVal=document.getElementById('constituencyDiv'+key).value;
		var mandalVal=document.getElementById('mandalDiv'+key).value;
		var villageVal=document.getElementById('villageDiv'+key).value;
		if(stateVal == 0)
		{
		if(!(str.indexOf("State Name is Required") > -1)){
		 str += 'State Name is Required.<br>';
		}
		flag = false;
		allLocSeleted = false;
		}
		if(districtVal == 0)
		{
		if(!(str.indexOf("District Name is Required") > -1)){
		 str += 'District Name is Required.<br>';
		}
		flag = false;
		allLocSeleted = false;
		}
		if(constituencyVal == 0)
		{
		if(!(str.indexOf("Constituency Name is Required") > -1)){
		 str += 'Constituency Name is Required.<br>';
		}
		flag = false;
		allLocSeleted = false;
		}
		if(mandalVal == 0)
		{
		if(!(str.indexOf("Mandal Name is Required") > -1)){
		 str += 'Mandal Name is Required.<br>';
		}
		flag = false;
		allLocSeleted = false;
		}
		if(villageVal == 0)
		{
		if(!(str.indexOf("Villiage Name is Required") > -1)){
		 str += 'Villiage Name is Required.';
		}
		flag = false;
		allLocSeleted = false;
		}
	}
	else if(scope == 7)
	{
		var stateVal=document.getElementById('stateDiv'+key).value;
		var districtVal=document.getElementById('districtDiv'+key).value;
		var constituencyVal=document.getElementById('constituencyDiv'+key).value;
		var mandalVal=document.getElementById('mandalDiv'+key).value;
		
		if(stateVal == 0)
		{
		if(!(str.indexOf("State Name is Required") > -1)){
		 str += 'State Name is Required.<br>';
		}
		flag = false;
		allLocSeleted = false;
		}
		if(districtVal == 0)
		{
		if(!(str.indexOf("District Name is Required") > -1)){
		 str += 'District Name is Required.<br>';
		}
		flag = false;
		allLocSeleted = false;
		}
		if(constituencyVal == 0)
		{
		if(!(str.indexOf("Constituency Name is Required") > -1)){
		  str += 'Constituency Name is Required.<br>';
		 }
		flag = false;
		allLocSeleted = false;
		}
		if(mandalVal == 0)
		{
		if(!(str.indexOf("Mandal Name is Required") > -1)){
		 str += 'Mandal Name is Required.';
		}
		flag = false;
		allLocSeleted = false;
		}
		
	}
  });
  if(allLocSeleted){
     var containsDuplicateLoc = false;
	  var locations = new Array();
	  $('.scopeLevel').each(function() {
	    var locScope = $(this).val();
        var key = $(this).attr("key");
	    var locValue = $('[name="locationValue['+key+']"]').val();
		for(var i in locations){
		   if(locations[i].id == locScope && locations[i].value == locValue ){
		      containsDuplicateLoc = true;
			  flag = false;
			  str += 'News Location cannot be duplicate(Ex: If Location Scope is Constituency and Assembly Consti is kavali Once again Location Scope Constituency and Assembly Consti kavali cannot be selected).<br>';
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
	  try{
	    var invalidImg = false;
	    $('.addFileImgCls').each(function() {
		  if($.trim($(this).val()).length > 0){
            var extension = $(this).val().substring($(this).val().lastIndexOf(".")).toLowerCase();
			 if(invalidImg)
			 return false;
			if(!(extension == '.jpeg' || extension == '.jpg' || extension == '.png' || extension == '.gif')){
			  str += 'News Files must be  .jpeg or .jpg or .png or .gif formats only';
			  invalidImg = true;
			  flag = false;
			}
			}
			
        });
		 $('.fileImgCls').each(function() {
		  if($.trim($(this).val()).length > 0){
            var extension = $(this).val().substring($(this).val().lastIndexOf(".")).toLowerCase();
			 if(invalidImg)
			 return false;
			if(!(extension == '.jpeg' || extension == '.jpg' || extension == '.png' || extension == '.gif')){
			  str += 'News Files must be  .jpeg or .jpg or .png or .gif formats only';
			  invalidImg = true;
			  flag = false;
			}
			}
			
        });
		 $('.m_top10').each(function() {
		  if($.trim($(this).val()).length > 0){
            var extension = $(this).val().substring($(this).val().lastIndexOf(".")).toLowerCase();
			 if(invalidImg)
			 return false;
			if(!(extension == '.jpeg' || extension == '.jpg' || extension == '.png' || extension == '.gif')){
			  str += 'News Files must be  .jpeg or .jpg or .png or .gif formats only';
			  invalidImg = true;
			  flag = false;
			}
			}
			
        });
	   }catch(e){
	   }
  }
	str += '</font>';
	errorDivEle.innerHTML = str;
	if(flag == false)
	{
		 //$("#uploadNewsBtnId").css("background","#51A351");
		$('html, body').animate({
         scrollTop: $("#uploadNewsFileErrorDiv").offset().top
     }, 2000);
		
	}
	else
	//$("#uploadNewsBtnId").css("background","#BBBB51");
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
function uploadFile()
{
	
 if(validateUploadFileDetails())
 {
	
  
  var newsprivateRadioId = document.getElementById('newsprivateRadioId').checked;
		if(newsprivateRadioId == true)
	document.getElementById('newsprivateRadioId').checked = 'true';
		if(newsprivateRadioId == false)
	document.getElementById('newsPublicRadioId').checked = 'true';
  
	setSourceDestinationPartyCandidateIds();

    /*var keywordStr = "";
	$(".as-selections li").each(function(){
              keywordStr +=$(this).text()+",";
				
            });
   
   var length = keywordStr.length;
    keywordStr = keywordStr.substr(0,length-2); 
	$("#keywordId").val(keywordStr);*/
	
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
    
 
 disableButton('uploadNewsBtnId');
 var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					showNewsUploadStatus1(uploadResult);	
					enableButton('uploadNewsBtnId');
				}
			};

		
		YAHOO.util.Connect.setForm('uploadForm1',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadFilesForPartyAndCandidatesKeywords.action',uploadHandler);
	}
	else
		return;
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

function getNewsReports()
{
  
  $("#newEditCandidateDiv").css("display","none");
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
  $("#profileManagementMainOuterDivStatus").css("display","none");
   $("#profileManagementHeaderDiv7").css("display","none");
   $("#profileManagementMainOuterDiv8").css("display","none");
   $("#newDesignationDiv").css("display","none");
  $("#newPartyCreationDiv").css("display","none");
  $('#showKeywordsDiv').css("display","none");
  		$('#statusDiv1').html('');
		$('#statusDiv2').html('');
    var str ='';
	str+='<div class="container well">';
	str+='<div class="row clearfix">';
	str+='<div class="row-fluid"><div class="span12 well-small yui-skin-sam yui-dt-sortable yui-dt yui-dt-paginator yui-pg-container" id="reportsDiv">';
	str+=' </div>';
    str+='</div>';
	str+='</div>';
	str+='</div>';
	document.getElementById("newsReportsMainDiv").innerHTML = str;
	//getReports();
	
	getNewsReport();
}
function showUploadStatus()
{
  $("#newsUploadsReports").html();
  $("#newEditCandidateDiv").css("display","none");
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
  $("#profileManagementMainOuterDiv7").css("display","none");
   $("#profileManagementHeaderDiv7").css("display","none");
   $("#profileManagementMainOuterDiv8").css("display","none");
   $("#profileManagementMainOuterDivStatus").css("display","block");
   $("#newDesignationDiv").css("display","none");
  $("#newPartyCreationDiv").css("display","none");
  $('#showKeywordsDiv').css("display","none");
  		$('#statusDiv1').html('');
		$('#statusDiv2').html('');
    var str ='';
	str+='<div style="background-color:#f5f5f5;" class="row-fluid">';
    str+='<div style="margin-left:240px;" class="span3"><label style="float: left;"><strong>Start Date<span class="requiredFont">*</span></strong></label><input type="text" readonly="true" id="trackFromDateId" class="inpit-block-level" ></div>';
    str+='<div style="margin-left:3px;" class="span3"><label style="float: left;"><strong>End Date<span class="requiredFont">*</span></strong></label><input type="text" id="trackToDateId" class="inpit-block-level" readonly="true" ></div>';
    str+='</div>';
	str+='<input type="button" id="gettrackdtlsId" class="btn btn-info" onclick="getUserTrackingDetailsReport();" value="submit">';
	str+='<div class="container well">';
	str+='<div class="row clearfix">';
	str+='<div class="row-fluid"><div class="yui-skin-sam yui-dt-sortable yui-dt" id="userTrackingDetails">';
	str+=' </div>';
    str+='</div>';
	str+='</div>';
	str+='</div>';
	document.getElementById("newsUploadsReports").innerHTML = str;
	
	$("#trackFromDateId").datepicker({
			dateFormat: "dd/mm/yy",
			changeMonth: true,
		  changeYear: true,
			maxDate: new Date(),
		});
	 $("#trackToDateId").datepicker({
			dateFormat: "dd/mm/yy",
			changeMonth: true,
		  changeYear: true,
			maxDate: new Date(),	
		});
	$('#trackFromDateId').datepicker('setDate', new Date());
	$('#trackToDateId').datepicker('setDate', new Date());
	populateDate();

}

function getUserTrackingDetailsReport(){
  var from = $("#trackFromDateId").val();
  var to = $("#trackToDateId").val();
  var jsObj = {
			fromDate:from,
			toDate:to,
			task: 'getUserTrackingDetails'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getUserUploadStatusAction.action?"+rparam;
	callnewAjax(jsObj,url);
}
function genereateExcelForUpload(){
  var from = $("#trackFromDateId").val();
  var to = $("#trackToDateId").val();
  var jsObj = {
			fromDate:from,
			toDate:to,
			task: 'getUserTrackingExcelDetails'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getUserUploadExcelAction.action?"+rparam;
	callnewAjax(jsObj,url);

}
function buildUserTracking(results){

	 var newsCountColumnDefs = [ 		    	             
		    	            
		    	           	{key:"name", label: "DISTRICT", sortable: true},
							{key:"count", label: "NEWS UPLOADED COUNT", sortable: true}
		    	        ]; 

    
		var myConfigs = { 
			    
				};
		
		
	var myDataSource = new YAHOO.util.DataSource(results);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","count"]
					};

		var newsCountDataSource = new YAHOO.widget.DataTable("userTrackingDetails", newsCountColumnDefs,myDataSource, myConfigs);
  if(results.length > 0)
    $("#userTrackingDetails").prepend('<div style="float: right;"><a onclick="genereateExcelForUpload();" class="btn btn-info">Export As Excel</a></div>');
  
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
function buildCandidatesForKeywords(results,type,idVal)
{
	candidatesList = new Array();
             $("#"+type+" option").remove();
			 $('#'+type).append('<option value="0">Select Candidate</option>');
            $.each(results,function(index , value){
		$('#'+type).append('<option value="'+value.id+'">'+value.name+'</option>');
		candidatesList.push(value.id);

	});
   $('#'+type).val(idVal);
}

function showCandidateSaveStatus(result,jsObj)
{
  $("#errorMsgDiv").html('');
  if(result == null || result.resultCode == 1)
  {
   $("#errorMsgDiv").html('Error occured! try again.').css("color","red");
   return;
  }

  else if(result.resultCode == 0 && result.message != "Candidate is already exist.")
  {
   $("#errorMsgDiv").html('Candidate Created Successfully.').css("color","green");
   setTimeout(function(){$("#errorMsgDiv").html('').css("color","red");},3000);
   $("#newCandidateName").val('');
   $("#partySelectNewList").val(0);
   var partyId = $("#"+jsObj.partyListId+"").val();
   
   if(partyId != null && partyId > 0)
     getCandidatesListByPartyId(partyId,""+jsObj.candidateListId+"","",result.id);
   
   //$("#"+jsObj.candidateListId+"").
   
   return;
  }
  else{
   $("#errorMsgDiv").html('Candidate is already exist.').css("color","green");
   
   return;
  }
  
}
function showCandidateSaveStatus1(result,jsObj)
{
 $("#errorMsgDiv1").html('');
  if(result == null || result.resultCode == 1)
  {
   $("#errorMsgDiv1").html('Error occured! try again.').css("color","red");
   return;
  }

  else if(result.resultCode == 0 && result.message == null)
  {
   $("#errorMsgDiv1").html('Candidate created Successfully.').css("color","green");
   setTimeout(function(){$("#errorMsgDiv1").html('').css("color","red");},3000); 
   $("#newCandidateName1").val('');
   $("#partySelectNewList1").val(0);
   var partyId = $("#"+jsObj.partyListId+"").val();
   
   if(partyId != null && partyId > 0)
     getCandidatesListByPartyId(partyId,""+jsObj.candidateListId+"");
   
   //$("#"+jsObj.candidateListId+"").
   
   return;
  }
  else{
   $("#errorMsgDiv1").html('Candidate is already exist.').css("color","green");
   
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

function getTotalNewsWithPagination()
{

 responseFileIdsArray = new Array();
 
 $("#errorMsgNewsDiv").html('');
 $("#profileManagementMainOuterDiv4").addClass("yui-skin-sam yui-dt-sortable");
 $("#profileManagementMainOuterDiv4").css({'margin-left': 'auto', 'margin-right': 'auto', 'float':' none', 'width': '950px'});
  
 var length = responseFileIdsArray.length;
 $("#selectedNewsCount").html(''+length+'');
 
 YAHOO.widget.DataTable.checkBox = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var fileId = oRecord.getData("fileId");
		
		if(responseFileIdsArray.indexOf(""+fileId+"") != -1)
		 str +="<input type='checkbox' class='newsResponseCheckId' onchange='cleareErrDiv2();' value='"+fileId+"' checked='checked'/>";
		else
		 str +="<input type='checkbox' class='newsResponseCheckId' onchange='cleareErrDiv2();'  value='"+fileId+"' />";
		elLiner.innerHTML=str;
					
	};
	
	
	YAHOO.widget.DataTable.title = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var fileId = oRecord.getData("fileId");
				var title = oRecord.getData("title");
		var isEenaduTelugu = oRecord.getData("eenaduTeluguFontStr");
		if(isEenaduTelugu != null && isEenaduTelugu == "Eenadu Telugu")
		  str +="<span class='enadu'><a href='javascript:{}' onclick='getNewsDetailsByContentId("+fileId+")' class='newsLinkCls'>"+title+"</a></span>";
		  else
		  str +="<span><a href='javascript:{}' onclick='getNewsDetailsByContentId("+fileId+")' class='newsLinkCls'>"+title+"</a></span>";
		  
		elLiner.innerHTML=str;
					
	};
	
	YAHOO.widget.DataTable.description = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var isEenaduTelugu = oRecord.getData("eenaduTeluguFontStr");
		var descEenadu = oRecord.getData("descEenadu");
		var description = oRecord.getData("description");

		if(descEenadu)
		  str +="<span class='enadu'>"+description+"</span>";
		  else
		  str +="<span >"+description+"</span>";
		  
		elLiner.innerHTML=str;
					
	};
	 YAHOO.widget.DataTable.Edit = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var fileId =  oRecord.getData("fileId");
		var userType = oRecord.getData("userType");
		
		if(userType == "Admin")
		{
			str+='<img src="images/icons/edit.png" style="cursor: pointer;" onclick="editFile('+fileId+',\'addresponse\')"/>';
		//str +='<input  class="btn btn-info" type="button" onclick="editFile('+fileId+')" value="Edit" />';

		}
		//str +='<input  class="btn btn-info" type="button" onclick="generateKey('+newsReportId+',\'generatedUrl'+newsReportId+'\')" value="Generate Url" />';
		elLiner.innerHTML=str;
					
	};
	

	 
	YAHOO.widget.DataTable.Delete = function(elLiner, oRecord, oColumn, oData) 
	{
		
	    var str='';
		var name = oData;
		var fileId =  oRecord.getData("fileId");
		var userType = oRecord.getData("userType");
		
		if(userType == "Admin")
		{
					str+='<img src="images/icons/delete.png" style="cursor: pointer;" onclick="deleteFileFromNewsReport('+fileId+')"/>';
		//str +='<input  class="btn btn-info" type="button" onclick="deleteFileFromNewsReport('+fileId+')" value="Delete" />';
		}
	//	var newsReportId = oRecord.getData("newsImportanceId");
		//str +='<textarea id=\'generatedUrl'+newsReportId+'\'></textarea>';
		elLiner.innerHTML=str;
					
	};

	var checkedVal = false;
	if($(".userCheckbox").is(':checked'))
	checkedVal = true;
  var locationVal =0;
  var fromDate = $("#newsFromDateId").val();
  var toDate = $("#newsToDateId").val();
  
  var scope = $("#responseRegionLevel  option:selected").text();
  if(scope == 'STATE')
	 locationVal = 0;
  else if(scope == 'DISTRICT')
	locationVal = $("#districtSelReportId1").val();
  else if(scope == 'PARLIAMENT CONSTITUENCY')
	 locationVal = $("#parliamSelReportId1").val();
   else if(scope == 'ASSEMBLY CONSTITUENCY')
	 locationVal = $("#assembSelReportId1").val();

if(loginUserType !='Admin'){
	  var newsColumns = [
			   {key:"ADD RESPONSE",label:"ADD RESPONSE",formatter:YAHOO.widget.DataTable.checkBox},
			   {key:"source", label:"SOURCE"},
			   {key:"title", label:"TITLE",formatter:YAHOO.widget.DataTable.title},
			   {key:"description", label:"DESCRIPTION",formatter:YAHOO.widget.DataTable.description},
			   {key:"locationValue", label:"LOCATION"},
			   {key:"fileDateAsString", label:"NEWS DATE"}
	  ];
  }
  else{
	   var newsColumns = [
				   {key:"ADD RESPONSE",label:"ADD RESPONSE",formatter:YAHOO.widget.DataTable.checkBox},
				   {key:"source", label:"SOURCE"},
				   {key:"title", label:"TITLE",formatter:YAHOO.widget.DataTable.title},
				   {key:"description", label:"DESCRIPTION",formatter:YAHOO.widget.DataTable.description},
				   {key:"locationValue", label:"LOCATION"},
				   {key:"fileDateAsString", label:"NEWS DATE"},
				   {key:"Edit",label:"Edit",formatter:YAHOO.widget.DataTable.Edit},
				   {key:"Delete",label:"Delete",formatter:YAHOO.widget.DataTable.Delete}
		];
  }
  var newsDataSource = new YAHOO.util.DataSource("getTotalNewsAction.action?fromDate="+fromDate+"&toDate="+toDate+"&locationVal="+locationVal+"&scope="+scope+"&checkedVal="+checkedVal+"&");
  newsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
  newsDataSource.responseSchema = {
  resultsList: "fileVOList",
   fields: [
             {key:"fileId", parser:"number"},
			        "eenaduTeluguFontStr","source","title", "descEenadu","description","locationValue", "fileDateAsString","userType"],
			        

    metaFields: {
    totalRecords: "count" // Access to value in the server response
     },
  };
  
  
  var myConfigs = {
initialRequest: "sort=title&dir=asc&startIndex=0&results=30", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"title", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 30 
			    })  // Enables pagination
};

var newsDataTable = new YAHOO.widget.DataTable("profileManagementMainOuterDiv4",
newsColumns, newsDataSource, myConfigs);

$("#profileManagementMainOuterDiv4").append('<div style="margin-left: 633px; margin-top: 20px; margin-bottom: -36px;"><input type="button" class="btn btn-info" onclick="addToNewsResponse()" value="Add Response"></div>');


newsDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
  
}
}
function cleareErrDiv2(){

	$('#errorMsgNewsDiv').css('display','none');
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
    for(var i in results){
		$("#"+jsObj.designationList+"").append('<option value="'+results[i].id+'">'+results[i].name+'</option>');
	 }
  }
}

function getNewsReport()
{
  
  YAHOO.widget.DataTable.viewReport = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var newsReportId = oRecord.getData("newsImportanceId");
		str +='<input type="button" value="View" onclick="getReportFiles('+newsReportId+')" class="btn btn-info" id="reportFiles">';
		elLiner.innerHTML=str;
					
	};
	
	YAHOO.widget.DataTable.generatePDF = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var newsReportId = oRecord.getData("newsImportanceId");
		
		str +='<input  class="btn btn-info" type="button" onclick="generateKey('+newsReportId+',\'generatedUrl'+newsReportId+'\')" value="Generate Url" />';
		elLiner.innerHTML=str;
					
	};
	
	YAHOO.widget.DataTable.textAreaPDF = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var newsReportId = oRecord.getData("newsImportanceId");
		str +='<textarea id=\'generatedUrl'+newsReportId+'\'></textarea>';
		elLiner.innerHTML=str;
					
	};
  
  var newsReportColumns = [
   {key:"description",label:"NewsReport"},
   {key:"identifiedDateOn",label:"Created Date"},
   {key:"viewReport",label:"View Report",formatter:YAHOO.widget.DataTable.viewReport},
   {key:"",label:"Generate Url For Creating Pdf",formatter:YAHOO.widget.DataTable.generatePDF},
   {key:"",label:"",formatter:YAHOO.widget.DataTable.textAreaPDF},
   
  ];
  
   var newsDataSource = new YAHOO.util.DataSource("getAllNewsReportsForAUser.action?");
  newsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
  newsDataSource.responseSchema = {
  resultsList: "fileVOList",
   fields: [
             {key:"newsImportanceId", parser:"number"},
			        "description","identifiedDateOn"],

    metaFields: {
    totalRecords: "count" // Access to value in the server response
     },
  };
  
  
  var myConfigs = {
initialRequest: "sort=description&dir=asc&startIndex=0&results=10", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"description", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10 
			    })  // Enables pagination
};

var newsDataTable = new YAHOO.widget.DataTable("reportsDiv",
newsReportColumns, newsDataSource, myConfigs);

newsDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
  
}
  
}

function getNewsDetailsForNewsReportGeneration()
{
$("#createNewsreport").css("display","block");
$("#locationWiseNewsDiv").css("display","block");
$("#newsReportBtnDiv").css("display","block");
$("#locationWiseNewsDiv").addClass("yui-skin-sam yui-dt-sortable yui-dt");
$('#reportErrDiv').html('');

var descriptionValue = $("#newsreportfileDescription").val();

	if(isValid(descriptionValue)){
		$('#reportErrDiv').html('<b style="color:red">Report Description should not contain #,$,%,& Special charactors</b>');
		$("#createNewsreport").css("display","none");
		$("#newsReportAjaxImg").css("display","none");
		$("#newsReportBtnDiv").css("display","none");
		$("#locationWiseNewsDiv").css("display","none");
		return false;
	}
	
	var fromDate = $("#fromDateId1").val();
	var toDate = $("#toDateId1").val();
	var regionLevel = $("#regionlevel").val();
	var importance = $("#newsPriority").val();
	var reportRegionLevel = $("#reportRegionLevel").val();
	var reportRegionLevelVal = 0;
	 var reqType="";
	if($("#byLevelChecked").is(':checked')){
		reqType = "byLevel";
	}else{
		reqType = "byRegion";
	  if(reportRegionLevel == 1){
	      reportRegionLevelVal = 1;
	  }else if(reportRegionLevel == 2){
		  reportRegionLevelVal = $("#districtSelReportId option:selected").val();
	  }else if(reportRegionLevel == 3){
		  reportRegionLevelVal = $("#parliamSelReportId option:selected").val();
	  }else if(reportRegionLevel == 4){
		  reportRegionLevelVal = $("#assembSelReportId option:selected").val();
	  }
	}
	var reportGallary1 = [];
	var keywordGallary1 = [];
	if($("#byCategory").is(':checked'))
	reportGallary1 = reportGallary;
	if($("#byKeyword").is(':checked'))
		keywordGallary1 = keywordGallary;
   $("#reportGenaratorNewsDiv").css("display","block");	
 $("#reportGenaratorSpanCLS").html('0');	

   YAHOO.widget.DataTable.title = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var isEenaduTelugu = oRecord.getData("eenaduTeluguFontStr");
		var fileId = oRecord.getData("contentId");
		var title = oRecord.getData("fileTitle1");
		
		if(isEenaduTelugu != null && isEenaduTelugu == "Eenadu Telugu")
		  str +="<span class='enadu'><a href='javascript:{}' onclick='getNewsDetailsByContentId("+fileId+")' class='newsLinkCls'>"+title+"</a></span>";
		  else
		  str +="<span><a href='javascript:{}' onclick='getNewsDetailsByContentId("+fileId+")' class='newsLinkCls'>"+title+"</a></span>";
		  
		elLiner.innerHTML=str;
					
	};
	
	YAHOO.widget.DataTable.newsCheckBox = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		
		var fileId = oRecord.getData("contentId");
		if(newsReportFileIdsArray.indexOf(""+fileId+"") != -1)
		 str +='<input type="checkbox" value="'+fileId+'" class="createReporcCheckBoxCls" checked="checked" />';
		 else
		  str +='<input type="checkbox" value="'+fileId+'" class="createReporcCheckBoxCls" />';
		  
		elLiner.innerHTML=str;
					
	};

	
	YAHOO.widget.DataTable.EditFile = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var fileId =  oRecord.getData("contentId");
		
		
			str+='<img src="images/icons/edit.png" style="cursor: pointer;" onclick="editFile('+fileId+',\'generatereport\')"/>';
		
		elLiner.innerHTML=str;					
	}; 
	YAHOO.widget.DataTable.DeleteFile = function(elLiner, oRecord, oColumn, oData) 
	{
		
	    var str='';
		var name = oData;
		var fileId =  oRecord.getData("contentId");	
		var fileDate = oRecord.getData("fileDate");
        var flag = oRecord.getData("tempvar");
		if(loginUserType == "Admin")
		{
			str+='<img src="images/icons/delete.png" style="cursor: pointer;" onclick="deleteFileFromReport('+fileId+')"/>';		
		}
		if(loginUserType != "Admin" && flag)
		{
			str+='<img src="images/icons/delete.png" style="cursor: pointer;" onclick="deleteFileFromReport('+fileId+')"/>';		
		}
		elLiner.innerHTML=str;					
	};
	
		  var newsColumns = [
		   {key:"",label:"",formatter:YAHOO.widget.DataTable.newsCheckBox},
		   {key:"source",label:"Source"},
		   {key:"fileTitle1",label:"Title",width:180,formatter:YAHOO.widget.DataTable.title},
		   {key:"fileDate",label:"File Date",width:80},
		   {key:"candidateName",label:"Candidate Name"},
		   {key:"locationName",label:"Location"},
		   {key:"",label:"Edit",formatter:YAHOO.widget.DataTable.EditFile},
		   {key:"",label:"delete",formatter:YAHOO.widget.DataTable.DeleteFile}
		  ];  
	
  var newsDataSource = new YAHOO.util.DataSource("getAllNewsForAUserAction.action?fromDate="+fromDate+"&toDate="+toDate+"&regionLevel="+regionLevel+"&importance="+importance+"&reportRegionLevel="+reportRegionLevel+"&reportRegionLevelVal="+reportRegionLevelVal+"&reqType="+reqType+"&reportGallary="+reportGallary1+"&keywordGallary="+keywordGallary1+"&");
  newsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
  newsDataSource.responseSchema = {
  resultsList: "fileVOList",
   fields: [
             {key:"contentId", parser:"number"},
			        "fileTitle1","source","eenaduTeluguFontStr","fileDate","candidateName","locationName","tempvar"],

    metaFields: {
    totalRecords: "count" // Access to value in the server response
     },
  };
  
  
  var myConfigs = {
initialRequest: "&sort=candidateName&dir=asc&startIndex=0&results=100", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"candidateName", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 100 
			    })  // Enables pagination
};

var newsDataTable = new YAHOO.widget.DataTable("locationWiseNewsDiv",
newsColumns, newsDataSource, myConfigs);

newsDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
  
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
			//$('#keywordsList').append('<option value="'+value.id+'">'+value.name+'</option>');
			var obj = {value: keywords.id,
			name:''+keywords.name+''}
			keywordsArray.push(obj);
		});
		 var data = {items:keywordsArray};
		 $("#keywordDiv").html('');
		 $("#keywordDiv").html('<input type="text" class="input-block-level keyword0 destinationKeywords" key="keywordId0" id="keywordId">');
		 
         $("#keywordId").autoSuggest("getKeyWordsBySearchCriteria.action", {minChars: 4,selectedItemProp: "name", searchObjProps: "name"});		 
		}
		
		});
}


function buildSearchDiv(divId1,elmtId){

var divSourceId;
var divId = divId1; 
if(divId1 != 11 && divId1 != 22){
divSourceId = (divId1 + "").charAt(0);
//divId = (divId1 + "").charAt(1);

}
$("#errDiv"+divId1+"").html('');
	var partyName = '';
	var partyId =0;
	var elemtValue = elmtId;
	if(elmtId ==0){
	elmtId ="";
	}

if(divId == 11){
	 partyName = $("#partiesList :selected").text();
	 partyId = $("#partiesList").val();
	if($('#partiesList').val() ==0){
	$("#errDiv"+divId+"").html('Please select party.');
	return;
	}
}
else if(divId == 22){
	partyName = $("#partiesListForWhome :selected").text();
	partyId = $("#partiesListForWhome").val();
	 
	if(partyId ==0){
	$("#errDiv"+divId+"").html('Please select party.');
	return;
	}
}

else if(divSourceId == 4){
	partyName = $("#partiesListForWhome"+elmtId+" :selected").text();
	partyId = $("#partiesListForWhome"+elmtId+"").val();
	 
	if(partyId ==0){
	$("#errDiv"+divId+"").html('Please select party.');
	return;
	}
}

else if(divSourceId == 3){

	partyName = $("#partiesList"+elmtId+" :selected").text();
	partyId = $("#partiesList"+elmtId+"").val();
	 
	if(partyId ==0){
	$("#errDiv"+divId+"").html('Please select party.');
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
	//str +='<td><select id="partySelectNewList'+divId+'"></select></td>';
	str +='</tr>';
	str +='<tr>'
	str +='<td>Designation : </td>';
	str +='<td><select id="designationsList'+divId+'"></select></td>';
	str +='</tr>'
	str +='<tr>'	
	str +='<td style="width: 160px;"> <input type="button" class="btn btn-info" id="searchId'+divId+'" onclick="showORhideSearchOptions('+divId+')" value="Advanced Search" style="margin-top: -10px;" /></td>'	
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
	


if(divSourceId == 3){
	str +='<button class="btn btn-success" onclick="updateDetails('+divId+','+elemtValue+');"> Ok </button>';

	}
else if(divSourceId == 4){
	str +='<button class="btn btn-success" onclick="updateDetails('+divId+','+elemtValue+');"> Ok </button>';

	}
else{
	str +='<button class="btn btn-success" onclick="updateDetails('+divId+','+elemtValue+');"> Ok </button>';
	}
	$('#searchInnerDiv').html(str);	
	$("#locationList"+divId+"").attr("disabled","disabled");

	//getPartiesList("partySelectNewList"+divId,null);
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
			
			//console.log(candidates);
			
	 $("#candidateId"+divId+"").autocomplete({
        source:candidatesList
        });
	 });

 //var id = candidates[];
}

function updateDetails(divId1,elmntId){


/*
if(divId ==1){
	$("#candidateListForParty").val(id);
}
else if(divId ==2){
	$("#candidateListForPartyForNewsTo").val(id);
}
else if(divId ==3){
	$("#candidateListForParty"+elmntId+"").val(id);
}
else if(divId ==4){
	$("#candidateListForPartyForNewsTo"+elmntId+"").val(id);
}
*/

var divSourceId;
var divId = divId1; 
if(divId1 != 11 && divId1 != 22){
divSourceId = (divId1 + "").charAt(0);
//divId = (divId1 + "").charAt(1);
}
var candiId = $("#candidateId"+divId+"").val();

var id = candidates[candiId];
if(candidatesList.indexOf(id) == -1){

	if(divId == 11){
		$('#candidateListForParty').append('<option value="' + id + '">' + candiId + '</option>');
	}
	else if(divId == 22){
		$("#candidateListForPartyForNewsTo").append('<option value="' + id + '">' + candiId + '</option>');
	}

	else if(divSourceId == 3){
		$("#candidateListForParty"+elmntId+"").append('<option value="' + id + '">' + candiId + '</option>');
	}

	else if(divSourceId == 4){
		$("#candidateListForPartyForNewsTo"+elmntId+"").append('<option value="' + id + '">' + candiId + '</option>');
	}

}
	if(divId == 11){
	$("#candidateListForParty").val(id);
	}
	else if(divId == 22){
		$("#candidateListForPartyForNewsTo").val(id);
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
	
	var confirmFile = confirm('Do you want to delete news?');
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
function deleteFileFromReport(fileId)
{
	
	var confirmFile = confirm('Do you want to delete news?');
	if(confirmFile)
	{
	var jsObj={
		fileId:fileId,
		task:'deleteFileForReport'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "deleteNewsAction.action?"+rparam;
	callAjax(jsObj, url);
	}
}
function buildAllKeywords(myResults)
{
    var str='';
	str+='<h2 style="text-align:center;margin-bottom:0px;margin-top:-10px;">Keywords</h2>';
	
	if(myResults==null || myResults=='')
	{
	str+='<span style="text-align:center;">There are no Keywords available</span>';
	$("#showKeywordsDiv").html(str);
	return;
	}
	else
	
	str+='<table  class="table table-striped">';
	str+='<tr>';
	str+='<th>Keywords</th>';
	str+='<th>Count</th>';
	str+='</tr>';
	for(var i in myResults)
	{
	str+='<tr>';
	str+='<td>' +myResults[i].name+'</td>';
	str+="<td><a title='Click Here To See All News'href='javascript:void(0)' title='Click Here To See All News'  onclick='getClickedKeywordData(\""+myResults[i].id+"\");'>"+myResults[i].count+"</a></td>";
	str+='</tr>';
	}
	str+='</table>';
	$("#showKeywordsDiv").html(str);

}
function getClickedKeywordData(id)
{
	 var browser1 = window.open("getNewsAction.action?&keywordId="+id,"KeywordsWindow","scrollbars=yes,height=600,width=1050,left=200,top=200");		
     browser1.focus();

}
function buildDeleteFile()
{
	$("#successMsg").html("deleted successfully..").css("color","green");
	setTimeout(function(){
	$('#successMsg').html('');
	}, 3000);
	getTotalNewsWithPagination();
}
function buildDeleteFileForReport()
{
	$("#successMsg").html("deleted successfully..").css("color","green");
	setTimeout(function(){
	$('#successMsg').html('');
	}, 3000);
	getNewsDetailsForNewsReportGeneration();
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

function editFile(id,type){

  var browser1 = window.open("editNewsAction.action?fileId="+id+"&type="+type,"EditNewsWindow","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
}


function buildCandidatesList(optionsList,jsObj)
{
var id=jsObj.type;
 $("#"+id+" option").remove();
var elmt = document.getElementById(id);
var option = document.createElement('option');
	option.value="0";
	option.text="Select";
	try
	{
		elmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		elmt.add(option); // IE only
	}

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
function showDesignationDetails(myResults,jsObj){
 if(myResults != null){
     $("#designationsList2").val(myResults.id);
	 if(myResults.type == "Assembly"){
	    $("#editcandidateLocType").val(1);
		$("#editassembSelReportId").val(myResults.constituencyId);
        getTypeOfConstituencyForEdit(1,'editacConstituencyRow','editpcConstituencyRow');		
	 }else if(myResults.type == "Parliament"){
	    $("#editcandidateLocType").val(2);
		getTypeOfConstituencyForEdit(2,'editacConstituencyRow','editpcConstituencyRow');	
		$("#editparliamSelReportId").val(myResults.constituencyId);	
	 }else{
	    getTypeOfConstituencyForEdit(0,'editacConstituencyRow','editpcConstituencyRow');
	   $("#editcandidateLocType").val(3);
	 }
 }

}

function getTypeOfConstituencyForEdit(value,ac,pc)
{	
	if(value == 1)
	{
		$('#'+pc).hide();
		$('#'+ac).show();
	}
	else if(value == 2)
	{
		$('#'+pc).show();
		$('#'+ac).hide();
	}
	else
	{
		$('#'+pc).hide();
		$('#'+ac).hide();
	}
}
function updateExistingCandidate(){
    
    
	$("#errorMsgDiv2").html('');
	var partyId = $("#EditpartySelectNewList").val();
	var candidateName = $.trim($("#textCandidate").val());
	var designationId = $("#designationsList2").val();
	var presentPartyId = $("#currentpartySelectNewList").val();
	
    if(partyId == 0)
	{
	  $("#errorMsgDiv2").html("Please Select Party");
	  return;
	}
	if(presentPartyId == 0)
	{
	  $("#errorMsgDiv2").html("Please Select Current Party");
	  return;
	}
	if($.trim(candidateName).length == 0)
	{
	 $("#errorMsgDiv2").html("Please Select Candidate");
	  return;
	}
	if(isValid(candidateName)){
		$('#errorMsgDiv2').html('<b style="color:red">Candidate Name should not contain #,$,%,& Special charactors</b>');
		return false;
	}
	if(designationId == 0)
	{
	 $("#errorMsgDiv2").html("Please Select Designation");
	  return;
	}
	if($('#editcandidateLocType option:selected').val() == 0){
	  $("#errorMsgDiv2").html("Please Select Location");
	  return;
	}
  var locationValue = "";
	if($('#editcandidateLocType option:selected').val() == 1)
	{
		locationValue = $('#editassembSelReportId option:selected').val();
	}
	var candidateId = $('#EditCandidateListForParty option:selected').val();
	if(candidateId == 0)
	{
	 $("#errorMsgDiv2").html("Please Select Candidate");
	  return;
	}
	else if($('#editcandidateLocType option:selected').val() == 2)
	{
		locationValue = $('#editparliamSelReportId option:selected').val();
	}
	if($('#editcandidateLocType option:selected').val() == 3)
	{
		locationValue = 0;
	}
	var jsObj =
		{ 
            partyId : partyId,
			presentPartyId:presentPartyId,
			candidateId:candidateId,
			candidateName:candidateName,
			designationId:designationId,
			locationId : $('#editcandidateLocType option:selected').val(),
			locationValue : locationValue,
			task:"updateCandidateDetails"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateCandidateAndPartyAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function showCandidateEditStatus(result,jsObj)
{
 
 $("#errorMsgDiv2").html('');
  if(result == null || result.resultCode == 1)
  {
   $("#errorMsgDiv2").html('Error occured! try again.').css("color","red");
   return;
  }

  else if(result.resultCode == 0 && result.message == null)
  {
   $("#errorMsgDiv2").html('Candidate Updated Successfully.').css("color","green");
   setTimeout(function(){$("#errorMsgDiv2").html('').css("color","red");},3000);
   $("#textCandidate").val('');
   $("#EditpartySelectNewList").val(0); 
    $("#EditCandidateListForParty").find('option').remove();
       $("#EditCandidateListForParty").append($("<option></option>").attr("value",0).text("Select Candidate"));
   $("#designationsList2").val(0);
    $("#editcandidateLocType").val(0);
   
   getTypeOfConstituencyForEdit(0,'editacConstituencyRow','editpcConstituencyRow');
  
   return;
  }


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

function deleteExistingImg(id){

	$("#"+id+"").val("");

}