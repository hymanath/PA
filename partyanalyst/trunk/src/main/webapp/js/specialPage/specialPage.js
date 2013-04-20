   var descriptions = '${descriptions}'; 
   var timeST = new Date().getTime();
   var fileIdArray = new Array();
   var initialFileIdArray = new Array();
   var initialArraySize =0;
   var initialCurrentSize=0;
   var arraySize =0;
   var currentSize=0;
   var queryTypeChecked='Public';
   var gGallaryId;
   var updateGallId;
   var canCount = 0;
	var parCount = 0;
var SPCount = 0;
  var specialPageId = '${specialPageVO.specialPageId}';
	var newsData;
   var newsPopUpData;
   var selectedContentFile;
   var videosNewData;

   
   var specialPageDescriptionId='${specialPageDescriptionId}';
var timeST = new Date().getTime();

function insertProfileDiscription()
{
	document.getElementById('profileManagementMainOuterDiv6').style.display = 'none' ;
	document.getElementById('profileManagementHeaderDiv1').style.display = 'block' ;
	if(document.getElementById('descriptionDiv').style.display = 'none')
		document.getElementById('descriptionDiv').style.display = 'block';

	if(document.getElementById('newsGallaryDiv').style.display = 'block')
		document.getElementById("newsGallaryDiv").style.display = 'none';

	if(document.getElementById('createNewDiv').style.display = 'block')
		document.getElementById('createNewDiv').style.display = 'none';
	
	if(document.getElementById("videoGallaryDiv").style.display = 'block')
		document.getElementById("videoGallaryDiv").style.display = 'none';
	
    if(document.getElementById("metaInfoDiv").style.display = 'block')
		document.getElementById("metaInfoDiv").style.display = 'none' ;

	if(document.getElementById("photoGallaryDiv").style.display = 'block')
		document.getElementById("photoGallaryDiv").style.display = 'none' ;

	if(document.getElementById('specialPageHighLightsDiv').style.display = 'block')
	   document.getElementById('specialPageHighLightsDiv').style.display = 'none' ;

	if(document.getElementById("specialPageInfoDiv").style.display = 'block')
		document.getElementById("specialPageInfoDiv").style.display = 'none' ;

$("#createNewTabId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#developmentGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#profileGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
$("#specialPageInfoId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageHighLightId").css({"background":"none repeat scroll 0 0 #0063DC"});


	document.getElementById("headingnames").innerHTML = "Event Description";
	var str ='';
	str += '<div id="content" align="center" style="width:650px;">';
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Add Description" onclick="addDescriptionDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Update Description" onclick="updateDescriptionDiv()"></td>';
	str += '</tr>';
	str += '</table>'
    str += '<fieldset class="imgFieldset" style="width:400px;">';	
	str += '<center><b style="font-size:15px"><font color="#4B74C6">Add The Event Description </font> </b> </center>';
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<div id="galErrorMsgDivId"></div>';
	str += '<div id="fileUploadErrorMsgDivId"></div>';
	str += '<tr>';
	str += '<td>';
	str += '<b><font color="#4B74C6">Event  Description</font></b></td><td><textarea id="profileDescId" name="profileDescription" cols="30" rows="5"></textarea></td></tr>';
	str += '</table>';
	str += '<table><tr><td style="padding-left: 82px"><input type="button" class="imageButton" value="Add Discription" style="background-color:#57B731" onClick="addProfileDiscription()"></td><td style="padding-left: 20px"><input type="button" class="imageButton" value="Cancel" style="background-color:#CF4740"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	
	document.getElementById("descriptionDiv").innerHTML = str;

} 
function addDescriptionDiv(){
	var str='';
	str += '<div id="content" align="center" style="width:650px;">';
	str += '<table style="margin-top:10px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Add Description" onclick="addDescriptionDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Update Description" onclick="updateDescriptionDiv()"></td>';
	str += '</tr>';
	str += '</table>'
    str += '<fieldset class="imgFieldset" style="width:400px;">';	
	str += '<center><b style="font-size:15px"><font color="#4B74C6">Add The Event Description </font> </b> </center>';
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<div id="galErrorMsgDivId"></div>';
	str += '<div id="fileUploadErrorMsgDivId"></div>';
	str += '<tr>';
	str += '<td>';
	str += '<b><font color="#4B74C6">Event  Description</font></b></td><td><textarea id="profileDescId" name="profileDescription" cols="30" rows="5"></textarea></td></tr>';
	str += '</table>';
	str += '<table><tr><td style="padding-left: 82px"><input type="button" class="imageButton" value="Add Discription" style="background-color:#57B731" onClick="addProfileDiscription()"></td><td style="padding-left: 5px"><input type="button" class="imageButton" value="Cancel" style="background-color:#CF4740"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	

	document.getElementById("descriptionDiv").innerHTML = str;
}

function updateDescriptionDiv(){
var specialPageId = document.getElementById("specialPageId").value;
var jsObj = {
	
	specialPageId : specialPageId,

	task : "eventDescriptionUpdate"

};
var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getEventGallariesForUplaodAction.action?"+rparam;
	callAjax(jsObj,url); 
}

var sourceObj = null;
var languagesObj = null;

function callAjax(jsObj,url){

	var callback = {			
	 success : function( o ) {
		try
		{ 
			myResults = YAHOO.lang.JSON.parse(o.responseText); 
         	 if(jsObj.task=='eventDescriptionUpdate')
			{
				 showEventDescription(myResults);
			}
			else if(jsObj.task=='deleteDiscription')
			{
				showDeleteStatus(myResults);
			}
			else if(jsObj.task == "saveDiscription")
			{
				 showDescriptionStatus(myResults);  
			}	
			else if(jsObj.task == "updateProfileDiscription")
			{
           showDiscriptionUpdateStatus(myResults);  
			}	
			else if(jsObj.task == "createVideoNewGallary")  
			{ 
               showVideoGallaryCreateMsg(myResults);
			}
			else if(jsObj.task == "createEventNewGallary" && jsObj.contentType =='News Gallary')
			{  
               showNewsGallaryCreateMsg(myResults);
			}
		
			else if(jsObj.task == "createEventNewGallary")  
			{ 
               showGallaryCreateMsg(myResults,jsObj.createOrUpdate);
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
			else if(jsObj.task == "eventGallariesForUplaod")
			{
			
			/* $('#existingFromText').attr({
               id : existingFromText.id + '_' + 1
             });*/
               clearOptionsListForSelectElmtId('gallarySelectId');
			   createListData('gallarySelectId',myResults);
			}
			else if(jsObj.task == "getScopesForNewSearch")
			{ 
			   clearOptionsListForSelectElmtId("scopeDiv");
               buildResults(myResults,"scopeDiv");
			}
			else if(jsObj.task == "partyGallariesForUplaod")
			{
			   /*$('#existingFromText').attr({
               id : existingFromText.id + '_' + 2 
             });*/
              // clearOptionsListForSelectElmtId('gallarySelectId');
			   createListDataForNews('gallaryId',myResults);
			}
			else if(jsObj.task == 'createNewSpecialPage')
			{
				showSuccessOfCreateNew(myResults);
			}
			else if(jsObj.task == 'eventVideoGallariesForUpload')
			{
				showSuccessOfUploadedFile(myResults);
			}
			else if(jsObj.task == "getStates")
			{ 
               buildResults(myResults,"stateDiv");
			}
			else if(jsObj.task == "getDistrictsByStateId")
			{ 
               buildResults(myResults,"districtDiv");
			}
			else if(jsObj.task == "constituenciesInDistrict")
			{ 
               buildResults(myResults,"constituencyDiv");
			}
			else if(jsObj.task == "getConstNotInGivenAreaType")
			{ 
               buildResults(myResults,"constituencyDiv");
			}
			else if(jsObj.task == "subRegionsInConstituency")
			{ 
               buildResults(myResults,"mandalDiv");
			}
			else if(jsObj.task == "hamletsOrWardsInRegion")
			{ 
               buildResults(myResults,"villageDiv");
			}
			else if(jsObj.task == "boothsInTehsilOrMunicipality")
			{ 
               buildResults(myResults,"villageDiv");
			}
			else if(jsObj.task == "addMetaInformation")
			{ 
				showSuccessOfAddMetaInfo(myResults);
			}
			else if(jsObj.task =="createphotoGallary")
			{
				
				showGallaryCreateMsg(myResults,jsObj.createOrUpdate);
			}
			else if(jsObj.task == "photoGallariesForUplaod")
			{
				clearOptionsListForSelectElmtId('gallarySelectId');
			    createOptionsForSelectElmtId('gallarySelectId',myResults);
                // createListDataForNews('gallarySelectId',myResults);
				
			}
			else if(jsObj.task == "getspaecialPagePhotoGallaryDetails")
			{
               buildCandidatePhotoGallary(myResults);
			}
			else if(jsObj.task == "specialPageUpdateGallary")  
			{
               updateGallaryDiv(myResults);  
			}
					
            else if(jsObj.task == "deleteGallary")  
			{
               showDeleteGallary(myResults);  
			}
			else if(jsObj.task == "updateFilesAndPhotos")  
			{   
			   updateGallId = jsObj.gallaryId;
               updateFilesAndPhotosDiv(myResults,jsObj.fileId);  
			}
			else if(jsObj.task == "updateIndividualPhotoDetails")
			{ 
               showPhotoUpdateDetails(myResults);
			}
		    else if(jsObj.task == "deleteFilesAndPhotos")
			{
              showDeleteFilesAndPhotosStatus(myResults);  
			}
			else if(jsObj.task == "photoGallariesForUpdataPhoto" && jsObj.updatePhoto== "UpdatePhoto")
			{
               clearOptionsListForSelectElmtId('gallarySelectId');
			   createOptionsForSelectElmtId('gallarySelectId',myResults);
               document.getElementById("gallarySelectId").value=updateGallId;
			}
			else if(jsObj.task == "candiadteVideoGallariesForUplaod")
			{
				 showUploadVideoStatus(myResults);
				
			}
			else if(jsObj.task == "getSpecialPageInfo")
			{
				buildSpecialPageInfoDiv(myResults);
			}
			else if(jsObj.task == "getHighLights")
			{
				buildSpecialPageHighLightsForUpdate(myResults);
			}
			else if(jsObj.task=='deleteSpecialPageHighLightDesc')
			{
				showSpecialPageHighLightsDeleteStatus(myResults);
			}
			else if(jsObj.task=='getSpecialPageHighLights')
			{
				showSpecialPageHighLightsDescStatus(myResults);
			}
			else if(jsObj.task=='updateSpecialPageHighLights')
			{
				showSpecialPageHighLightsDiscUpdateStatus(myResults);
			}
			
						
	}
	catch(e){   
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

function deleteFile(fileTableId)
{
	var confirmFile = confirm('Do you want to delete file');
	if(confirmFile)
		$("#"+fileTableId+"").html('');

}
function showUploadVideoStatus(result)
{
	var errorDivEle = document.getElementById('fileSuccessDiv');
	var uploadVideoBtnId = document.getElementById('uploadVideoBtnId');
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

function clearUploadVideoFields()
{
	$("#otherProVideoDiv").html('');
	$("#addMoreVideosDiv").html('');
	document.getElementById('fileTitleId').value = '';
	document.getElementById('fileDescId').value = '';
	document.getElementById('vpublicRadioId').checked = true;
	document.getElementById('path').value = '';
	document.getElementById("keyword").value = '';
	document.getElementById("source").value = '';	
	document.getElementById('existingFromText').value = '';
}

function callAjaxForMultiPagesUpload(jsObj,url){

	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText); 
         
		if(jsObj.task == "getParties")
			{
				document.getElementById(jsObj.selectOptionId).style.display = 'block';
		   clearOptionsListForSelectElmtId(jsObj.selectOptionId);
		   $("#"+jsObj.selectOptionId+"").prepend("<option value='0'>Select Party</option>");
		   createOptionsForSelectElmtId(jsObj.selectOptionId,myResults);
            }
		else if(jsObj.task == "partyGallariesForUplaod" || jsObj.task == "candidateGallariesForUplaod" || jsObj.task == "photoGallariesForUplaod")
		{
			if(jsObj.userOption =='Party')
			{
			  if(myResults != null && myResults.length>0)
			  {
				   document.getElementById(jsObj.galSelectOPtionId).style.display = 'block';
				   clearOptionsListForSelectElmtId(jsObj.galSelectOPtionId);
				   $("#"+jsObj.galSelectOPtionId+"").prepend("<option value='0'>Select Gallery</option>");
				   createOptionsForSelectElmtId(jsObj.galSelectOPtionId,myResults);
				   $('#noGalDiv'+jsObj.galSelectOPtionId+'').html('');
				   buildCreateGalleryDiv(jsObj.partyId,jsObj.contentType,jsObj.userOption,jsObj.galSelectOPtionId);
			  }
			  else
				{
				  document.getElementById(jsObj.galSelectOPtionId).style.display = 'none';
				  buildCreateGalleryDiv(jsObj.partyId,jsObj.contentType,jsObj.userOption,jsObj.galSelectOPtionId);
				}
			}
			else if(jsObj.userOption =='Candidate')
			{
				if(myResults != null && myResults.length>0)
				{
				   document.getElementById(jsObj.galSelectOPtionId).style.display = 'block';
				   clearOptionsListForSelectElmtId(jsObj.galSelectOPtionId);
				   $("#"+jsObj.galSelectOPtionId+"").prepend("<option value='0'>Select Gallery</option>");
				   createOptionsForSelectElmtId(jsObj.galSelectOPtionId,myResults);
				   $('#noGalDiv'+jsObj.galSelectOPtionId+'').html('');
				   buildCreateGalleryDiv(jsObj.candidateId,jsObj.contentType,jsObj.userOption,jsObj.galSelectOPtionId);
				}
				else
				{
					document.getElementById(jsObj.galSelectOPtionId).style.display = 'none';
					buildCreateGalleryDiv(jsObj.candidateId,jsObj.contentType,jsObj.userOption,jsObj.galSelectOPtionId);
				}
			}
			else if(jsObj.userOption =='SpecialPage')
			{
				if(myResults != null && myResults.length>0)
				{
				   document.getElementById(jsObj.galSelectOPtionId).style.display = 'block';
				   clearOptionsListForSelectElmtId(jsObj.galSelectOPtionId);
				   $("#"+jsObj.galSelectOPtionId+"").prepend("<option value='0'>Select Gallery</option>");
				   createOptionsForSelectElmtId(jsObj.galSelectOPtionId,myResults);
				   $('#noGalDiv'+jsObj.galSelectOPtionId+'').html('');
				   buildCreateGalleryDiv(jsObj.specialPageId,jsObj.contentType,jsObj.userOption,jsObj.galSelectOPtionId);
				}
				else
				{
					document.getElementById(jsObj.galSelectOPtionId).style.display = 'none';
					buildCreateGalleryDiv(jsObj.specialPageId,jsObj.contentType,jsObj.userOption,jsObj.galSelectOPtionId);
				}
			}
		}
		else if(jsObj.task == "getCandidates")
		{
		   document.getElementById(jsObj.selectOptionId).style.display = 'block';
		   clearOptionsListForSelectElmtId(jsObj.selectOptionId);
		   $("#"+jsObj.selectOptionId+"").prepend("<option value='0'>Select Candidate</option>");
		   createOptionsForSelectElmtId(jsObj.selectOptionId,myResults);
		}
		else if(jsObj.task == "getSpecialPages")
		{
		   document.getElementById(jsObj.selectOptionId).style.display = 'block';
		   clearOptionsListForSelectElmtId(jsObj.selectOptionId);
		   $("#"+jsObj.selectOptionId+"").prepend("<option value='0'>Select Special Page</option>");
		   createOptionsForSelectElmtId(jsObj.selectOptionId,myResults);
		}
		else if(jsObj.task == "createphotoGallary")
		{
		  if(document.getElementById('galleryInnerPopupDiv'))
			document.getElementById('galleryInnerPopupDiv').style.display='none';
		  getGallaries(jsObj.specialPageId,jsObj.contentType,jsObj.userOption,jsObj.galleryOptionId);
		}
		else if(jsObj.task == "createNewGallary")
		{
		  if(document.getElementById('galleryInnerPopupDiv'))
			document.getElementById('galleryInnerPopupDiv').style.display='none';
		  
		  if(jsObj.userOption == 'Candidate')
			{
				getGallaries(jsObj.candidateId,jsObj.contentType,jsObj.userOption,jsObj.galleryOptionId);
			}
		  else if(jsObj.userOption == 'Party')
			{
				getGallaries(jsObj.partyId,jsObj.contentType,jsObj.userOption,jsObj.galleryOptionId);
			}
		}

	}
		catch(e)
			{   
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

function buildCreateGalleryDiv(selectedId,contentType,userOption,galSelectOPtionId)
{
    if(document.getElementById("noGalDiv"+galSelectOPtionId) != null){
           $("#noGalDiv"+galSelectOPtionId).remove();
	}
	var nogalleryDivElmt = document.getElementById(galSelectOPtionId);
	var str='';
	str+='<div id="noGalDiv'+galSelectOPtionId+'" class="noGalDiv'+galSelectOPtionId+''+selectedId+'" ><b><a href="javascript:{}" onclick="showCreateGallery(\''+selectedId+'\',\''+contentType+'\',\''+userOption+'\',\''+galSelectOPtionId+'\')">Create Gallery</a></b>';
	str+='<div id="galleryInnerPopupDiv" style="display:none;"></div></div>';
	$(nogalleryDivElmt).after(str);

}
function showCreateGallery(selectedId,contentType,userOption,galSelectId)
{
	if(document.getElementById('galleryInnerPopupDiv').style.display == 'block')
	{
		document.getElementById('galleryInnerPopupDiv').style.display = 'none';
		return false;
	}
	if(document.getElementById('galleryInnerPopupDiv').style.display == 'none')
	document.getElementById('galleryInnerPopupDiv').style.display = 'block';

	var galleryPopupDivElmt = document.getElementById('galleryInnerPopupDiv');
	
	var str='';
	str+= '<div id="galErrorMsgDiv"></div>';
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center" style="color:#669900;">Create '+contentType+'</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="94%" style="margin-left:13px;"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="galNameId" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="galDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<div style="margin-left:45px;"><input type="radio" value="public" name="gallaryVisibility" id="publicId" checked="true"><b><font color="#4B74C6" style="padding-left:5px;">&nbsp;&nbsp;Visible to Public Also</font></b></input></div>';
	str += '<div style="margin-left:45px;margin-top:1px;"><input type="radio" value="private" name="gallaryVisibility" id="privateId"><b><font color="#4B74C6" style="padding-left:5px;">&nbsp;&nbsp;Make This Private</font></b></input></div>';
	
	str += '<table style="margin-left: auto;margin-right: auto;"><tr><td><input type="button" class="imageButton" style="height:24px;" value="Create Gallery" style="background-color:#57B731" onClick="createPopupGallery(\''+selectedId+'\',\''+contentType+'\',\''+userOption+'\',\''+galSelectId+'\')"></td>';
	str+=  '<td><input type="button" class="imageButton" style="height:24px;" value="Cancel" onclick="clearDiv(\'galleryInnerPopupDiv\')" style="background-color:#CF4740"></td></tr></table>';

	str += '</fieldset>';
		
	galleryPopupDivElmt.innerHTML = str;
	/*$("#galleryPopupDiv").dialog({ stack: false,
									height: 'auto',
									width: 500,
									position:'center',								
									modal: true,
									title:'<font color="#000">Create Gallery</font>',
									overlay: { opacity: 0.5, background: 'black'}
									
							});
	$("#galleryPopupDiv").dialog();*/
}
function createPopupGallery(selectedId,contentType,userOption,galleryOptionId)
{
	var galName = document.getElementById('galNameId').value;
	var galDesc = document.getElementById('galDescId').value;
	var isPublic = document.getElementById('publicId').checked;
	var makeThis = 'true';
    var gallaryId ='';
	var errorDivEle = document.getElementById('galErrorMsgDiv');
	var eFlag = false;
    var str = '<font color="red">';

	if(galName.length == 0)
	{
		str += 'Gallery Name Required<br>';
		eFlag = true;
	}
	if(galName.length >200)
	{
		str += 'Gallery Name should be less than 200 Characters<br>';
		eFlag = true;
	}
	if(galDesc.length > 500)
	{
		str += 'Description should be less than 500 Characters<br>';
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

	if(userOption == 'SpecialPage')
	{
		var jsObj =
			{ 
				galleryOptionId:galleryOptionId,
				userOption:userOption,
				name : galName,
				desc : galDesc,
				visibility : makeThis,
				specialPageId : selectedId,
				contentType : contentType,
				gallaryId : gallaryId,
				createOrUpdate:"Create",
				task : "createphotoGallary"
			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "createPhotoGallaryAction.action?"+rparam;						
		callAjaxForMultiPagesUpload(jsObj,url);
	}
  else if(userOption == 'Party')
  {
	var jsObj =
			{ 
				galleryOptionId:galleryOptionId,
				userOption:userOption,
				name : galName,
				desc : galDesc,
				visibility : makeThis,
				partyId : selectedId,
				contentType : contentType,
				gallaryId : gallaryId,
				createOrUpdate:"Create",
				task : "createNewGallary"
			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "partyManageAction.action?"+rparam;						
		callAjaxForMultiPagesUpload(jsObj,url);
	}
  else if(userOption == 'Candidate')
  {
	var jsObj =
		{ 
			galleryOptionId:galleryOptionId,
			userOption:userOption,
            name : galName,
		    desc : galDesc,
			visibility : makeThis,
			candidateId : selectedId,
			contentType : contentType,
			gallaryId : gallaryId,
			createOrUpdate:"Create",
			task : "createNewGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;						
	callAjaxForMultiPagesUpload(jsObj,url);
  }
}
function showSuccessOfUploadedFile(result){

	var fileSuccessDivEle = document.getElementById('fileSuccessDiv');
	var str = '';

	if(result.resultCode == 0)
	{
		str += '<font color="Green"><strong>File Uploaded SuccessFully</strong></font>';
		
	}
	else 
		str += '<font color="red"><strong>Error Occured, Try Again</strong></font>';
	fileSuccessDivEle.innerHTML = str;

}
function showEventDescription(myResults)
{
	var listSize = myResults.length;
	sizeOfArray = listSize;
	var i;
	var str ='';
	str += '<div id="content" align="center" style="width:650px;">';
	str += '<table style="margin-top:10px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Add Description" onclick="addDescriptionDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Update Description" onclick="updateDescriptionDiv()"></td>';
	str += '</tr>';
	str += '</table>'
	str += '<fieldset class="imgFieldset" style="width:400px;">';	
	str += '<center><b style="font-size:15px"><font color="#4B74C6">Update The Profile Description </font> </b> </center>';
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<div id="galErrorMsgDivId"></div>';
	str += '<div id="fileUploadErrorMsgDivId"></div>';
	str += '<tr>';
	str += '<td>';
	str += '<b><font color="#4B74C6">Order No</font></b></td><td style="padding-left: 82px"><b><font color="#4B74C6">Description </font></b></td></tr>';
	for(i=0 ; i<listSize ; i++)
	{
	str += ' <tr><td style="padding-right:100px"><input type="text" id="orderNoId_'+i+'" value= "'+myResults[i].orderNo+'" size="5" style="width: 40px;"></td>';
	str += ' <td style="padding-right: 75px"> <textarea id="descId_'+i+'" cols="25" rows="4"> '+myResults[i].description+'</textarea> </td>';
	str += ' <td style="padding-right: 80px"> <input type = "button" id="delete_'+i+'" class="buttonStyle" style="background: none repeat scroll 0 0 #F61D50;" value = "Delete" onClick="deleteProfileById('+myResults[i].specialPageDescriptionId+')"></td></tr>';
	str += ' <input type="hidden" id="specialPageDescriptionId_'+i+'" value="'+myResults[i].specialPageDescriptionId+'">';

	}
	str += '</table>';
	str += '<table><tr><td style="padding-right: 5px"><input type="button" class="imageButton" value="Update Discription" style="background-color:#57B731" onClick="updateProfileDiscription()"></td><td><input type="button" class="imageButton" value="Cancel" style="background-color:#CF4740"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	
	document.getElementById("descriptionDiv").innerHTML = str;
	
	}

	function deleteProfileById(specialPageDescriptionId){
	
	var r=confirm("Do you want to Delete!");
	if(r==true)
		var jsObj={

    specialPageDescriptionId:specialPageDescriptionId,
		task:"deleteDiscription"

	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "deleteEventGallaryAction.action?"+rparam;
	callAjax(jsObj,url);
	
		}
		function showDeleteStatus(myResults)
		{
			var errorDivEle=document.getElementById('fileUploadErrorMsgDivId');
			var str='';
			if(myResults.resultCode==0){
			updateDescriptionDiv();
		
			str += '<font color="green"><b> deleted successfully</b></font>';
			}

			else if(myResults.resultCode==1){

				str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
		}
		
		errorDivEle.innerHTML = str;

		}


function addProfileDiscription(){

var fileDesc = document.getElementById('profileDescId').value;
var specialPageId = document.getElementById('specialPageId').value;
var errorDivEle = document.getElementById('galErrorMsgDivId');
   var eFlag = false;
	
	var str='<font color="red">';

   if(fileDesc.length==0){

	   str += 'Profile Discription Is Required<br>';
		eFlag = true;

   }
   str += '</font>';

   errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;


var jsObj={
	
	specialPageId : specialPageId,

	fileDesc : fileDesc,
		task:"saveDiscription"
};

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "EventManageAction.action?"+rparam;
	callAjax(jsObj,url);	
    
 }

 function showDescriptionStatus(myResult)  
 {
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var str = '';
	if(myResult.resultCode == 0)
	{
		cleardescriptionFields();
		str += '<font color="green"><b>Description Saved Successfully.</b>';

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
function updateProfileDiscription(){
	var specialPageId = document.getElementById('specialPageId').value;
	var orderNoArr = [];
	var descriptionArr = [];
	var profDescIdArr = [];
	for(i=0 ; i < sizeOfArray ; i++)
   		{
		
			orderNoArr.push(document.getElementById('orderNoId_'+i).value);
			profDescIdArr.push(document.getElementById('specialPageDescriptionId_'+i).value);
			descriptionArr.push(removeAllUnwantedCharactersInArray(document.getElementById('descId_'+i).value));		
   		}
  
 var jsObj =
		{ 
		    specialPageId :specialPageId,
			orderNoArr : orderNoArr,
			descriptionArr : descriptionArr,
			profDescIdArr : profDescIdArr,
		   	task : "updateProfileDiscription"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createUpdateEventGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
    
}

function showDiscriptionUpdateStatus(myResult)  
{

	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
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


function showVideoGallary(){
document.getElementById('profileManagementMainOuterDiv6').style.display = 'none' ;
document.getElementById('profileManagementHeaderDiv1').style.display = 'block' ;
if(document.getElementById('descriptionDiv').style.display = 'block')
	document.getElementById('descriptionDiv').style.display = 'none' ;

if(document.getElementById('newsGallaryDiv').style.display = 'block')
	document.getElementById('newsGallaryDiv').style.display = 'none';

if(document.getElementById('createNewDiv').style.display = 'block')
	document.getElementById('createNewDiv').style.display = 'none';

if(document.getElementById("metaInfoDiv").style.display = 'block')
		document.getElementById("metaInfoDiv").style.display = 'none' ;

if(document.getElementById("videoGallaryDiv").style.display = 'none')
	document.getElementById("videoGallaryDiv").style.display = 'block' ;

if(document.getElementById("photoGallaryDiv").style.display = 'block')
		document.getElementById("photoGallaryDiv").style.display = 'none' ;

if(document.getElementById("specialPageInfoDiv").style.display = 'block')
		document.getElementById("specialPageInfoDiv").style.display = 'none' ;

if(document.getElementById('specialPageHighLightsDiv').style.display = 'block')
	   document.getElementById('specialPageHighLightsDiv').style.display = 'none' ;

$("#createNewTabId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#developmentGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#profileGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageInfoId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageHighLightId").css({"background":"none repeat scroll 0 0 #0063DC"});


    document.getElementById("headingnames").innerHTML = "Video Gallery";
		
	var videoGallaryDivElmt = document.getElementById("videoGallaryDiv");
	var str ='';
	str +='<div id="content" align="center" style="width:650px;">';
		
	str += '<table style="margin-top:10px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload Video" onclick="buildUploadVideoDiv()"></td>';
	str += '</tr>';
	str += '</table>';

	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A Video Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId" style="margin-left: 50px;"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="pVGallaryNameId" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pVGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<div style="padding-right: 63px"><input type="radio" value="public" name="visibility" id="vpublicRadioId" checked="true"><b><font color="#4B74C6">&nbsp;&nbsp;Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 78px"><input type="radio" value="private" name="visibility" id="vprivateRadioId"><b><font color="#4B74C6">&nbsp;&nbsp;Make This Private</font></b></input></div>';
	
	str += '<table><tr><td style="padding-right:5px"><input type="button" class="imageButton" value="Create Gallery" style="background-color:#57B731" onClick="createVideoGallary(\'Video Gallary\')"></td><td style="padding-right: 10px"><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'videoGallaryDiv\')" style="background-color:#CF4740"></td></tr></table>';

	
	str += '</fieldset>';
	str +='</div>';
	
	videoGallaryDivElmt.innerHTML = str;

}



function buildCreateVideoGallaryDiv(){
	 var videoGallaryDivElmt = document.getElementById("videoGallaryDiv");
	var str ='';
	str +='<div id="content" align="center" style="width:650px;">';
		
	str += '<table style="margin-top:10px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload Video" onclick="buildUploadVideoDiv()"></td>';
	str += '</tr>';
	str += '</table>';

	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A Video Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId" style="margin-left:50px;"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="pVGallaryNameId" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pVGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<div style="padding-right: 63px"><input type="radio" value="public" name="visibility" id="vpublicRadioId" checked="true"><b><font color="#4B74C6">&nbsp;&nbsp;Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 78px"><input type="radio" value="private" name="visibility" id="vprivateRadioId"><b><font color="#4B74C6">&nbsp;&nbsp;Make This Private</font></b></input></div>';
	
	str += '<table><tr><td style="padding-right:5px"><input type="button" class="imageButton" value="Create Gallery" style="background-color:#57B731" onClick="createVideoGallary(\'Video Gallary\')"></td><td style="padding-right: 10px"><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'videoGallaryDiv\')" style="background-color:#CF4740"></td></tr></table>';

	
	str += '</fieldset>';
	str +='</div>';
	
	videoGallaryDivElmt.innerHTML = str;
	

}

function createVideoGallary(contentType){

var galName = document.getElementById('pVGallaryNameId').value;
var galDesc = document.getElementById('pVGallaryDescId').value;
var isPublic = document.getElementById('vpublicRadioId').checked;
var specialPageId = document.getElementById('specialPageId').value;
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
	
	var jsObj =
		{ 
            name : galName,
		    desc : galDesc,
			visibility : makeThis,
			specialPageId : specialPageId,
			contentType : contentType,
			createOrUpdate:'Create',
			task : "createVideoNewGallary"
		};


	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewEventGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function showVideoGallaryCreateMsg(result)
{
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


function clearVideoGallaryFields(){

document.getElementById('pVGallaryDescId').value = '';
document.getElementById('pVGallaryNameId').value = '';
}


function buildUploadVideoDiv()
{
	document.getElementById("newsGallaryDiv").innerHTML = '';
	var str ='';
	str+='<div id="content" align="center" style="width:650px;">';
		
	str += '<table style="margin-top:10px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload Video" onclick="buildUploadVideoDiv()"></td>';
	str += '</tr>';
	str += '</table>';
	str += '<fieldset class="imgFieldset" style="width:440px;">';
	str += '<h2 align="center">Upload A Video</h2>';
	str+=  '<div id="fileSuccessDiv"></div>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
    str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId" style="margin-left:50px;"></div></td></tr></table>';
	str += '<table width="75%">';
	str += '<tr><td><b><font color="#4B74C6">Select Gallery</font></b></td><td><select id="gallarySelectId" name="gallarySelectIdss" style="width:175px;"><option value="0">Select</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Video Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="fileTitleId" name="videoTitle" size="25" maxlength="200"></td></tr>';
    str += '<tr><td><b><font color="#4B74C6">Video Description<font class="requiredFont">*</font></font></b></td><td><textarea id="fileDescId" name="videoDescription" cols="19" rows="3" name="requirement"></textarea></td></tr>';
    str += '<TR>';
	str += ' <td><b><font color="#4B74C6">File Date<font class="requiredFont">*</font></font></b></td>';
	str += '<TD style="padding-right: 31px;"><input type="text" id="existingFromText" class="dateField" readonly="true" name="fileDate" size="20"/>';
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '<TD>';
	str += '<A href="javascript:{}" id="existingFromText_Div" title="Click To Select A Date" onclick="showDateCal()">';
	//str += '<IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '</TD>';
	str += '</TR>';
	str += '<tr><td><b><font color="#4B74C6">Video Path In Youtube<font class="requiredFont">*</font></font></b></td><td><input type="text" id="path" name="path" size="25" maxlength="200"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Keyword</font></b></td><td><input type="text" id="keyword" name="keyword" size="25" maxlength="200"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Source</font><font class="requiredFont">*</font></b></td><td><select id="source" name="fileSourceId" style="width:175px;"><option value="0">Select Source</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Language</font><font class="requiredFont">*</font></b></td><td><select id="language" name="sourceLanguageId" style="width:175px;"><option value="0">Select Language</option></select></td>';
	str += '<td class="selectWidthPadd"><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMoreVideos()" title="Click here to add more Videos" alt="Click here to add more images"/></td></tr>';
	str += '<tr>';
	str +=  '';
	str += '<td colspan="3"><div id="addMoreVideosDiv" style="width: 344px;"></div></td></tr>';
	str += '</table>';
	str += '<div style="padding-right: 72px;"><input type="radio" value="public" name="visibility" id="vpublicRadioId" checked="true"><b><font color="#4B74C6">&nbsp;&nbsp;Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 88px;"><input type="radio" value="private" name="visibility" id="vprivateRadioId"><b><font color="#4B74C6">&nbsp;&nbsp;Make This Private</font></b></input></div>';
	str +='<input type="radio" onclick="otherProfiles(\'otherProVideoDiv\',\'fromSpecialPage\',\'Video Gallary\')"/>    Do you want to upload this file to other profiles';
	str +='<div id="otherProVideoDiv" style="margin: 10px;"></div>'; 
	str += '<table style="margin-top: 27px;"><tr><td style="padding-right: 5px;"><input type="button" id="uploadVideoBtnId" class="imageButton" value="Upload Video" style="background-color:#57B731" onClick="uploadVideoGallary()"></td><td style="padding-right: 31px;"><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'videoGallaryDiv\')"   style="background-color:#CF4740"></td></tr></table>';
	
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("videoGallaryDiv").innerHTML = str;
	getEventGallariesForUplaod('Video Gallary');
	getSource('source');
	getLanguage("language");
}
var fileCount=0;
function addMorePhotos()
{
	var addMorePhotosDivElmt = document.createElement("addMorePhotosDiv");
	var str='';
	str+='<table id="photosTableId'+fileCount+'" style="background:#e3e3e3; border-radius: 6px 6px 6px 6px; padding: 4px; margin-top: 11px; width: 364px;">';
	str+='<tr><td><font color="#4B74C6">File Path </font> <input type="file" name="userImage" id="addMoreFileId'+fileCount+'"/></td>';
	str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteFile(\'photosTableId'+fileCount+'\')"></td></tr>';
	str+='</table>';
	addMorePhotosDivElmt.innerHTML = str;
document.getElementById("addMorePhotosDiv").appendChild(addMorePhotosDivElmt);
 fileCount++;
}
function addMoreVideos()
{
	var addMoreVideosDivElmt = document.createElement("addMoreVideosDiv");
	var str='';
	str+='<table id="videosTableId'+fileCount+'" style="background:#e3e3e3; border-radius: 6px 6px 6px 6px; padding: 4px; margin-top: 11px; width: 364px;">';
	str += '<tr><td><b><font color="#4B74C6">Video Path In Youtube<font class="requiredFont">*</font></font></b></td><td><input type="text" id="path'+fileCount+'" name="path" size="25" maxlength="200"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Keyword</font></b></td><td><input type="text" id="keyword" name="keyword" size="25" maxlength="200"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Source</font><font class="requiredFont">*</font></b></td><td><select id="source'+fileCount+'" name="fileSourceId" style="width:175px;"><option value="0">Select Source</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Language</font><font class="requiredFont">*</font></b></td><td><select id="language'+fileCount+'" name="sourceLanguageId" style="width:175px;"><option value="0">Select Language</option></select></td>';
	str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteFile(\'videosTableId'+fileCount+'\')"></td></tr>';
	str+='</table>';
	addMoreVideosDivElmt.innerHTML=str;
	document.getElementById("addMoreVideosDiv").appendChild(addMoreVideosDivElmt);
	getSource("source"+fileCount+"");
	 getLanguage('language'+fileCount+'');
	 fileCount++;
}
function addMoreFiles()
{
	var moreDivElmt = document.createElement("addMoreFilesDiv");
	var str ='';
		str +='<table style="background:#e3e3e3;border-radius:9px;padding:5px;margin-top:12px;" id="moreFileTableId'+fileCount+'">';
		str += '   <tr>';
		str +='<td>File Path</td>';
		str += '       <td class="selectWidthPadd"><input type="file" name="userImage" id="newsFileId'+fileCount+'" size="25" /></td>';
		str += '   </tr>';
		str += '   <tr>';
		str += '       <td class="tdWidth1">Source<font class="requiredFont">*</font></td>';
		str += '  <td class="selectWidthPadd"><select id="filesourceId'+fileCount+'" name="fileSourceId" style="width:175px;"><option value="0">Select Source</option></select></td>';
		str += '   </tr>';
		str += '   <tr>';
		str += '       <td class="tdWidth1">Language<font class="requiredFont">*</font></td>';
		str += '  <td class="selectWidthPadd"><select id="sourceLangId'+fileCount+'" name="sourceLanguageId" style="width:175px;"><option value="0">Select Language</option></select></td>';
		str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteFile(\'moreFileTableId'+fileCount+'\')"></td>';
		str += '   </tr>';
		str +='</table>';
	  moreDivElmt.innerHTML = str;
	
	document.getElementById("addMoreFilesDiv").appendChild(moreDivElmt);
	 getSource("filesourceId"+fileCount+"");
	 getLanguage('sourceLangId'+fileCount+'');
	 fileCount++;
}
function addMoreCandidates(contentType)
{
  var moreDiv = document.createElement("addMoreCandidatesDiv");
  var str ='';
  str+= '<div id="addMoreCanOption'+canCount+'"></div><div id="candidateSelectionDiv"><select id="candidateSelectId'+canCount+'" style="display:none;width: 250px; margin: 10px;" onchange="getGallaries(this.options[this.selectedIndex].value,\''+contentType+'\',\'Candidate\',\'uploadCandidateGalleryId'+canCount+'\');"></select></div>';
  str+= '<div id="candidateGallerySelectDiv"><select id="uploadCandidateGalleryId'+canCount+'" name="uploadCandidateGalleryId" onchange="showAddedGallery(\'addMoreCanOption'+canCount+'\',\'candidateSelectId'+canCount+'\',this.id)" style="display:none;width: 250px; margin: 10px;"></select></div>';
  moreDiv.innerHTML = str;
  getCandidates("candidateSelectId"+canCount+"");
	canCount++;
	document.getElementById("addMoreCandidatesDiv").appendChild(moreDiv);
}
function addMoreParties(contentType)
{
  var morePartyDiv = document.createElement("addMorePartiesDiv");
  var str ='';
  str+= '<div id="addMorePartyOption'+parCount+'"></div><div id="partySelectionDiv"><select id="partySelectId'+parCount+'" style="display:none;width: 250px; margin: 10px;" onchange="getGallaries(this.options[this.selectedIndex].value,\''+contentType+'\',\'Party\',\'uploadPartyGalleryId'+parCount+'\');"></select></div>';
  str+= '<div id="partyGallerySelectDiv"><select id="uploadPartyGalleryId'+parCount+'" name="uploadPartyGalleryId" onchange="showAddedGallery(\'addMorePartyOption'+parCount+'\',\'partySelectId'+parCount+'\',this.id)" style="display:none;width: 250px; margin: 10px;"></select></div>';
  morePartyDiv.innerHTML = str;
  getParties("partySelectId"+parCount+"");
	parCount++;
	document.getElementById("addMorePartiesDiv").appendChild(morePartyDiv);
}
function addMoreSpecialPage(contentType)
{
  var moreSPDiv = document.createElement("addMoreSPDiv");
  var str ='';
  str+= '<div id="addMoreSPOption'+SPCount+'"></div><div id="spSelectionDiv"><select id="spSelectId'+SPCount+'" style="display:none;width: 250px; margin: 10px;" onchange="getGallaries(this.options[this.selectedIndex].value,\''+contentType+'\',\'SpecialPage\',\'uploadSpecialPageGalleryId'+SPCount+'\');"></select></div>';
  str+= '<div id="spGallerySelectDiv"><select id="uploadSpecialPageGalleryId'+SPCount+'" name="uploadSpecialPageGalleryId" onchange="showAddedGallery(\'addMoreSPOption'+SPCount+'\',\'spSelectId'+SPCount+'\',this.id)" style="display:none;width: 250px; margin: 10px;"></select></div>';
  moreSPDiv.innerHTML = str;
  getSpecialPages("spSelectId"+SPCount+"");
	SPCount++;
	document.getElementById("addMoreSPDiv").appendChild(moreSPDiv);
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
function uploadVideoGallary(){
	var makeThis = 'private';
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var eFlag = false;
	var str = '<font color="red">';
	var fileTitle = document.getElementById('fileTitleId').value;
	var fileDesc = document.getElementById('fileDescId').value;
	var path = document.getElementById('path').value;
	//var candidateId=document.getElementById("candidateId").value;
	var galSelectId = document.getElementById("gallarySelectId").value;
	var keyword = document.getElementById("keyword").value;
	var sourceId = document.getElementById("source").value;
	var languageId = document.getElementById("language").value;
	var fileDate = document.getElementById("existingFromText").value;
	var isPublic = document.getElementById('vpublicRadioId').checked;
	var spcheckboxIdElmt = document.getElementById('spcheckboxId');
	var pcheckboxIdElmt = document.getElementById('pcheckboxId');
	var ccheckboxIdElmt =  document.getElementById('ccheckboxId');
	//var fileDate = document.getElementById("existingFromText").value;
	var uploadVideoBtnId = document.getElementById("uploadVideoBtnId");
	
	var spSelectId ='';
	var SPGalleryId='';
	var partySelectId='';
	var canSelectId ='';
	var canGalleryId = '';
	var partyGalleryId='';
	var canGalIdArray = new Array();
	var spGalIdArray = new Array();
	var partyGalIdArray = new Array();
	var sourceIds = new Array();
	var languageIds = new Array();
	var paths = new Array();

	$("#videoGallaryDiv select[id^= source]").each(function(){
            sourceIds.push(this.value)
        });
			
	$("#videoGallaryDiv select[id^= language]").each(function(){
		languageIds.push(this.value);
	});
	
	$("#videoGallaryDiv input[id^= path]").each(function(){
		if(this.value !='')
		paths.push(this.value);
		else
			str+='Please Select Youtube Path<br>';

	});
	
	if(spcheckboxIdElmt !=null && spcheckboxIdElmt.checked)
	{
		spSelectId = document.getElementById("spSelectId").value;
		SPGalleryId = document.getElementById("uploadSpecialPageGalleryId").value;
		$("#addMoreSPDiv").find("#spGallerySelectDiv").find("select").each(function(){
			if(this.value !="")
			{
			 spGalIdArray.push(this.value);
			}
		});
		if(spGalIdArray !="")
		spGalIdArray.push(SPGalleryId);
	}
	if(pcheckboxIdElmt !=null && pcheckboxIdElmt.checked)
	{	
		partySelectId = document.getElementById("partySelectId").value;
		partyGalleryId = document.getElementById("uploadPartyGalleryId").value;
		$("#addMorePartiesDiv").find("#partyGallerySelectDiv").find("select").each(function(){
			if(this.value !="")
			{
				partyGalIdArray.push(this.value);
			}	
		});
		if(partyGalleryId !="")
			partyGalIdArray.push(partyGalleryId);
	}
	if(ccheckboxIdElmt !=null && ccheckboxIdElmt.checked)
	{	
		canSelectId = document.getElementById("candidateSelectId").value;
		canGalleryId = document.getElementById("uploadCandidateGalleryId").value;
		$("#addMoreCandidatesDiv").find("#candidateGallerySelectDiv").find("select").each(function(){
			if(this.value !="")
			{
				canGalIdArray.push(this.value);
			}		
		});
		if(canGalleryId !="")
		  canGalIdArray.push(canGalleryId);
	}
	if(fileDate.length == 0)
	{
		str +='File Date is Required';
		eFlag =true;
	}

	if(galSelectId == 0)
	{
		str += 'Select Gallery<br>';
		eFlag =true;
	}
	if(fileTitle.length == 0)
	{
		str += 'Title is Required<br>';
		eFlag = true;
	}
	if(fileTitle.length > 200)
	{
		str += 'Title should be less than 200 Characters<br>';
		eFlag = true;
	}
	if(fileDesc.length ==0)
	{
		str += 'Description is required<br>';
		eFlag = true;
	}
	if(fileDesc.length >500)
	{
		str += 'Description should be less than 500 Characters<br>';
		eFlag = true;
	}
	if(path.length ==0)
	{
		str += 'Path is required<br>';
		eFlag = true;
	}
	if(path.length >200)
	{
		str += 'Path should be less than 200 Characters<br>';
		eFlag = true;
	}
	if(keyword.length > 200)
	{
		str += 'Keywords should be less than 200 Characters<br>';
		eFlag = true;
	}
	if(sourceId.length == 0)
	{
		str += 'Source is required<br>';
		eFlag = true;
	}
	if(languageId.length == 0)
	{
		str += 'Language is required<br>';
		eFlag = true;
	}
	str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;

	if(isPublic)
		makeThis = 'public';
	disableButton('uploadVideoBtnId');

	fileTitle = removeAllUnwantedCharacters(fileTitle);
	fileDesc =  removeAllUnwantedCharacters(fileDesc);

	var jsObj =
		{ 
			canGalleryId :canGalIdArray,
		   	SPGalleryId : spGalIdArray,
			partyGalleryId : partyGalIdArray,
            gallaryId	: galSelectId,
			videoTitle	: fileTitle,
			videoDescription : fileDesc,
			paths		: paths,
			keywords	: keyword,
			sourceId    : sourceIds,
			languageId  : languageIds,
			fileDate	: fileDate,
			visibility	: makeThis,
		   	task		: "candiadteVideoGallariesForUplaod"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;
	callAjax(jsObj,url);
}


function clearDiv(videoGallaryDiv)
{
	var videoGallaryDiv=document.getElementById("videoGallaryDiv");
  videoGallaryDiv.innerHTML = "";
}


function getEventGallariesForUplaod(contentType)
{
var specialPageId=document.getElementById("specialPageId").value;
	var jsObj =
		{ 
            specialPageId : specialPageId,
			contentType : contentType,
		   	task : "eventGallariesForUplaod"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getEventGallariesForUplaodPhotoAction.action?"+rparam;
	callAjax(jsObj,url);
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
     document.getElementById('gallarySelectId').value = 'gallaryId';
}
function clearFields()
{
	document.getElementById('fileTitleId').value='';
	document.getElementById('fileDescId').value='';
	document.getElementById('path').value='';
	document.getElementById('fileTitleId').value='';
	document.getElementById('keyword').value='';
	document.getElementById('existingFromText').value='';
	
}

 function enableButton(id)
{
	document.getElementById(id).disabled  = false;
}

function disableButton(id)
{
	
	document.getElementById(id).disabled  = true;
}
function showDateCal() {
	var id = document.getElementById("existingFromText_Div");
	if (dateCalendar)
		dateCalendar.destroy();

	var navConfig = {
		strings : {
			month : "Choose Month",
			year : "Enter Year",
			submit : "OK",
			cancel : "Cancel",
			invalidYear : "Please enter a valid year"
		},
		monthFormat : YAHOO.widget.Calendar.SHORT,
		initialFocus : "year"
	};

	var dateCalendar = new YAHOO.widget.Calendar(id, {
		navigator : navConfig,
		maxdate: maxDate,
		title : "Choose a date:",
		close : true
	});
	dateCalendar.selectEvent.subscribe(displayDateText, dateCalendar, true);
	dateCalendar.render();
	dateCalendar.show();
}


var date = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();
var hidden=1;
var maxDate = (new Date().getMonth() + 1) + "/" + new Date().getDate() + "/" + new Date().getFullYear();

function displayDateText(type, args, obj) {

	var dates = args[0];
	var date = dates[0];
	var year = date[0], month = date[1], day = date[2];
	var divId = obj.containerId;
	var divElmt = document.getElementById(divId);

	var txtDate1 = document.getElementById("existingFromText");
	txtDate1.value = day + "/" + month + "/" + year;
	minDate = month + "/" + day + "/" + year;
	divElmt.style.display = 'none';
}

//for NewsGallary

function showNewsGallaey(){
	document.getElementById('profileManagementMainOuterDiv6').style.display = 'none' ;
	document.getElementById('profileManagementHeaderDiv1').style.display = 'block' ;
if(document.getElementById('videoGallaryDiv').style.display = 'block')
	document.getElementById('videoGallaryDiv').style.display = 'none';

if(document.getElementById('descriptionDiv').style.display = 'block')
	document.getElementById('descriptionDiv').style.display = 'none';

if(document.getElementById('createNewDiv').style.display = 'block')
	document.getElementById('createNewDiv').style.display = 'none';

if(document.getElementById("metaInfoDiv").style.display = 'block')
		document.getElementById("metaInfoDiv").style.display = 'none' ;

if(document.getElementById('newsGallaryDiv').style.display = 'none')
	document.getElementById('newsGallaryDiv').style.display = 'block';

if(document.getElementById("photoGallaryDiv").style.display = 'block')
		document.getElementById("photoGallaryDiv").style.display = 'none' ;

if(document.getElementById("specialPageInfoDiv").style.display = 'block')
		document.getElementById("specialPageInfoDiv").style.display = 'none' ;

if(document.getElementById('specialPageHighLightsDiv').style.display = 'block')
	   document.getElementById('specialPageHighLightsDiv').style.display = 'none' ;

$("#createNewTabId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
$("#developmentGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#profileGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageInfoId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageHighLightId").css({"background":"none repeat scroll 0 0 #0063DC"});


	//var newsGallaryDiv = document.getElementById("newsGallaryDiv");
	document.getElementById("headingnames").innerHTML = "News Gallery";
	var str ='';
	str+='<div id="content" align="center" style="width:650px;">';
	str +=  '<table style="margin-top:10px;">';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="imageButton" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News" onclick="buildUploadNews()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A News Category</h2>';
	str+='<table align="left" class="paddingCss"><tr><td><div id="newsErrorMsgDivId" style="margin-left: 50px;"/></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Category Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="newsCateName" size="25" maxlength="100" /></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="newsCateDesc" cols="27" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<table><tr><td><input type="radio" value="public" name="visibility" id="newsPublicRadio" checked="true"><b><font color="#4B74C6">&nbsp;&nbsp;Visible to Public Also</font></b></input></tr></td>';
	str += '<tr><td><input type="radio" value="private" name="visibility" id="newsPrivateRadio"><b><font color="#4B74C6">&nbsp;&nbsp;Make This Private</font></b></input></tr></td></table>';
	
	str += '<table><tr><td><input type="button" class="imageButton" value="Create Category" style="background-color:#57B731" onClick="createCategory()"></td><td><input type="button" class="imageButton" value="Cancel"  onClick="clearDiv(\'newsGallaryDiv\');" style="background-color:#CF4740"></td></tr></table>';

	str += '<div>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("newsGallaryDiv").innerHTML = str;
}

function buildCreateNewsCategory(){
	if(document.getElementById("videoGallaryDiv").style.display = 'block')
	document.getElementById("videoGallaryDiv").style.display = 'none' ;


var newsGallaryDiv = document.getElementById("newsGallaryDiv");
if(newsGallaryDiv.style.display = 'none')
	newsGallaryDiv.style.display = 'block';
	
	var str ='';
	str+='<div id="content" align="center" style="width:650px;">';
	str +=  '<table style="margin-top:10px;">';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="imageButton" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News" onclick="buildUploadNews()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A News Category</h2>';
	str+='<table align="left" class="paddingCss"><tr><td><div id="newsErrorMsgDivId" style="margin-left:50px;"/></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Category Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="newsCateName" size="25" maxlength="100" /></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="newsCateDesc" cols="27" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<table><tr><td><input type="radio" value="public" name="visibility" id="newsPublicRadio" checked="true"><b><font color="#4B74C6">&nbsp;&nbsp;Visible to Public Also</font></b></input></tr></td>';
	str += '<tr><td><input type="radio" value="private" name="visibility" id="newsPrivateRadio"><b><font color="#4B74C6">&nbsp;&nbsp;Make This Private</font></b></input></tr></td></table>';
	
	str += '<table><tr><td><input type="button" class="imageButton" value="Create Category" style="background-color:#57B731" onClick="createCategory()"></td><td><input type="button" class="imageButton" value="Cancel"  onClick="clearDiv(\'newsGallaryDiv\');" style="background-color:#CF4740"></td></tr></table>';

	str += '<div>';
	str += '</fieldset>';
	str+='</div>';
	newsGallaryDiv.innerHTML = str;

}

function createCategory(){

	var newsCatrgoryName = document.getElementById('newsCateName').value;
	var newsCatrgoryDesc = document.getElementById('newsCateDesc').value; 
	var isPublic = document.getElementById('newsPublicRadio').checked;
	var specialPageId = document.getElementById("specialPageId").value;
	var makeThis = 'true';
	var errorDivEle = document.getElementById('newsErrorMsgDivId');
	var eFlag = false;
	var str = '<font color="red">';

if(specialPageId.length <= 0){
	document.getElementById('alertMsg1').innerHTML='<font color="red">specialPageId Is Required</font>';
	eFlag = true;
	}
if(newsCatrgoryName.length == 0){
str += 'Category Name Is Required<br>';
eFlag = true;
}
if(newsCatrgoryDesc.length == 0){
str += 'Description Is Required<br>';
eFlag = true;

}
if(newsCatrgoryDesc.length > 300){
str +='Description should be less than 300 Characters<br>';
eFlag = true;

}
str += '</font>';
errorDivEle.innerHTML = str;
if(eFlag)
		return;

	if(isPublic)
		makeThis = 'false';

	newsCatrgoryName = removeAllUnwantedCharacters(newsCatrgoryName);
	newsCatrgoryDesc = removeAllUnwantedCharacters(newsCatrgoryDesc);

	var jsObj = {
	name : newsCatrgoryName,
	desc : newsCatrgoryDesc,
	visibility : makeThis,
	createOrUpdate:'Create',
	specialPageId : specialPageId,
	contentType : 'News Gallary',
	task : "createEventNewGallary"

	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createEventGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function showNewsGallaryCreateMsg(result)
{
	var errorDivEle = document.getElementById('newsErrorMsgDivId');
	var str = '';
	
	if(result.resultCode == 0)
	{
		if(result.exceptionMsg == null)
		  str += '<font color="green"><b>Gallery Created Successfully.</b>';
		else
		 str += '<font color="red"><b>Gallery Name is already exist.</b>';
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

	errorDivEle.innerHTML = str;
	document.getElementById('newsCateName').value='';
	document.getElementById('newsCateDesc').value='';
}
function  buildUploadNews()
{
	document.getElementById("videoGallaryDiv").innerHTML = '';
	var specialPageIdEle = document.getElementById("specialPageId");
	var tempSpecialPageId = specialPageIdEle.options[specialPageIdEle.selectedIndex].value
  
   var str ='';
	str+='<div id="content" align="center" style="width:650px;">';
	str +=  '<table style="margin-top:10px;">';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="imageButton" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News" onclick="buildUploadNews()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';
	str += '<fieldset class="imgFieldset" style="width:520px;">';
	str += '<form name="uploadForm" action="uploadFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadNewsForm">';
	str += '<h2 align="center">Upload A News</h2>';
	str += '<table  align="left" class="paddingCss"><tr><td><div id="uploadNewsFileErrorDiv" style="margin-left:32px;"/></td></tr></table>';
	str += '<table width="75%">';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Select Category</td><td class="selectWidthPadd"><select id="gallaryId" name="gallaryId" class="selectWidth"  style="margin-left: 0px;"/><option value="0">Select Category</option></select></td>';
	str += '   </tr>';
    str += '   <tr>';
	str += '       <td class="tdWidth1">Title<font class="requiredFont">*</font><b></td>';
	str += '       <td class="selectWidthPadd"><input type="text" id="fileTitle" name="fileTitle" size="25" maxlength="200"></text></td>'; 
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Description<font class="requiredFont">*</font></td>';
	str += '       <td class="selectWidthPadd"><textarea id="fileDescription" cols="20" rows="3" name="fileDescription"></textarea></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Keywords</td>';
	str += '       <td class="selectWidthPadd"><input type="text" id="keywords" name="keywords" size="25" maxlength="200" ></text></td></tr>';
	str += '   <TR>';
	str += '     <td colspan="2">';
	str += '<table>';
	str += '   <tr>';
	str += '     <td class="tdWidth2"><b><font color="#4B74C6">File Date<font class="requiredFont">*</font></font></b></td>';
	str += '<TD class="selectWidthPadd"><input type="text" id="existingFromTextNews" class="dateField" readonly="true" name="fileDate" size="35"/>';
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '     <TD>';
	str += '       <A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	//str += '       <IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '     </TD>';
	str += '   </tr>';
	str += '</table>';
	str += '     </td>';
	str += '   </TR>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Source<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="source" name="fileSourceId" style="width:175px;"><option value="0">Select Source</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Language<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="language" name="sourceLanguageId" style="width:175px;"><option value="0">Select Language</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">File Path<font class="requiredFont">*</font></td>';
	str += '       <td class="selectWidthPadd"><input type="file" name="userImage" id="newsFileId" size="25" /></td>';
	str += '       <td class="selectWidthPadd"><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMoreFiles()" title="Click here to add more images" alt=""Click here to add more images""/></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '      <td colspan="3"> <div id="addMoreFilesDiv"></div></td>';
	str += '   </tr>';

	  str +='<tr><td class="tdWidth1" style="width:97px;">Image To Display:</td><td><input type="file" name="imageForDisplay" id="ImgnewsfileId" size="25" style="margin-top:8px;"/></td></tr>';
	
	str += '   <tr>';
	str += '       <td></td>';
	str += '       <td><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">&nbsp;&nbsp;Visible to Public Also</font></b></input></td>';
    str += '   </tr>';
	str += '   <tr>';
	str += '       <td></td>';
	str += '       <td style="margin-top:-16px;"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">&nbsp;&nbsp;Make This Private</font></b></input></td>';
	str += '   </tr>';
	str +='    <tr>';
    str +='	   <td class="tdWidth1">Location Scope<font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="scopeDiv" name="locationScope" class="selectWidth" onchange="getLocations(this.options[this.selectedIndex].value)"  style="margin-left: 0px;" /></td>';
    str +='  </tr>';
	str +='  <tr>';
	str +='    <td colspan="2">';
	str +='       <div id="showScopeSubs"/>'; 
	str +='    </td>';
	str +='  </tr>';
	str += '</table>';

	str +='<input type="hidden" name="profileType" value="special_page_profile">';
	str +='<input type="hidden" name="profileId" value="'+tempSpecialPageId+'">';
	str +='<input type="hidden" name="profileGalleryType" value="news_gallery">';
	str +='<input type="radio" onclick="otherProfiles(\'otherProNewsDiv\',\'fromSpecialPage\',\'News Gallary\')"/>    Do you want to upload this file to other profiles';
	str +='<div id="otherProNewsDiv" style="margin: 10px;"></div>';
	str += '<table><tr><td><input type="button" id="uploadNewsBtnId" class="imageButton" value="Upload News" style="background-color:#57B731" onClick="uploadNews()"></td>';
	str +='<td><input type="button" class="imageButton" value="Cancel"  onClick="clearDiv(\'newsGallaryDiv\');" style="background-color:#CF4740"></td></tr></table>';
	str += '</form>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("newsGallaryDiv").innerHTML = str;
	getPartyGallariesForUplaod("News Gallary");
	getScopes();
	getSource("source");
	getLanguage("language");
}
function uploadNews()
{	
	var uploadNewsBtnId =document.getElementById('uploadNewsBtnId');
	if(validateNewsFileUpload())
	{
		disableButton('uploadNewsBtnId');
		var privateRadioId = document.getElementById('privateRadioId').checked;
		if(privateRadioId == true)
		document.getElementById('privateRadioId').checked = true;
		if(privateRadioId == false)
		document.getElementById('publicRadioId').checked = true;
		var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					showNewsUploadStatus(uploadResult);
					enableButton('uploadNewsBtnId');
				}
			};

		
		YAHOO.util.Connect.setForm('uploadNewsForm',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadFilesAction.action',uploadHandler);
	}
	else
		return;
}

function showNewsUploadStatus(myResult)
{
	var result = (String)(myResult);
	var errorDivEle = document.getElementById('uploadNewsFileErrorDiv');
	var str = '';

	if(result.search('success') != -1)
	{
		clearNewsUploadFileFields();
		str += '<font color="green"><b>News Uploaded Successfully.</b>';
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

function clearNewsUploadFileFields()
{
	$("#otherProNewsDiv").html('');
	$("#addMoreFilesDiv").html('');
	document.getElementById('fileTitle').value = '';
	document.getElementById('fileDescription').value = '';
	document.getElementById('keywords').value = '';
	document.getElementById('existingFromTextNews').value = '';
	document.getElementById('source').value = '';
	document.getElementById('newsFileId').value = '';	
	document.getElementById('publicRadioId').checked = true;
	document.getElementById('ImgnewsfileId').value = '';
	getScopes();
}

function validateNewsFileUpload()
{
	var fileTitle = document.getElementById('fileTitle').value;
	var fileDesc = document.getElementById('fileDescription').value;
	var fileVal = document.getElementById("newsFileId").value;
	var source = document.getElementById("source").value;
	var languageId = document.getElementById("language").value;
    //var categoryId = document.getElementById("category").value;
    var keywords = document.getElementById("keywords").value;
	var fileDate = document.getElementById("existingFromTextNews").value;
	var scope = document.getElementById("scopeDiv").value;
	var flag = true;

	fileTitle = removeAllUnwantedCharacters1(fileTitle);
	fileDesc = removeAllUnwantedCharacters1(fileDesc);
	document.getElementById('fileTitle').value = fileTitle;
	document.getElementById('fileDescription').value = fileDesc;

	var errorDivEle = document.getElementById('uploadNewsFileErrorDiv');
	var str = '<font color="red">';

	if(fileTitle.length == 0)
	{
		str += ' Title is Required.<br>';
		flag = false;
	}
	if(fileTitle.length >200)
	{
		str += 'Title should be less than 200 Characters<br>';
		flag = false;
	}
	if(fileDesc.length == 0)
	{
		str += 'Description is Required.<br>';
		flag = false;
	}
	if(fileDesc.length > 500)
	{
		str += 'Description Should not exceed 500 Characters.<br>';
		flag = false;
	}
	if(fileVal.length == 0)
	{
		str += 'File is Required.<br>';
		flag = false;
	}
	if(source.length == 0)
	{
		str += 'Source is Required.<br>';
		flag = false;
	}
	if(languageId.length == 0)
	{
		str += 'Language is Required.<br>';
		flag = false;
	}
	/*if(categoryId.length == 0)
	{
		str += 'Category Is Required.<br>';
		flag = false;
	}*/
	if(keywords.length >200)
	{
		str += 'Keywords should be less than 200 Characters.<br>';
		flag = false;
	}
	if(fileDate.length == 0)
	{
		str += 'File Date is required.<br>';
		flag = false;
	}
	
	if(scope == 0 )
	{
		str += 'Location Scope is Required.';
		flag = false;
	}
	if(scope == 2)
	{
		var stateVal=document.getElementById('stateDiv').value;
		if(stateVal == 0)
		{
		str += 'State Name is Required.';
		flag = false;
		}
	}
	if(scope == 3)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		if(stateVal == 0)
		{
		str += 'State Name is Required.<br>';
		flag = false;
		}
		if(districtVal == 0)
		{
		str += 'District Name is Required.';
		flag = false;
		}
	}
	if(scope == 4)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		var constituencyVal=document.getElementById('constituencyDiv').value;
		if(stateVal == 0)
		{
		str += 'State Name is Required.<br>';
		flag = false;
		}
		if(districtVal == 0)
		{
		str += 'District Name is Required.<br>';
		flag = false;
		}
		if(constituencyVal == 0)
		{
		str += 'Constituency Name is Required.';
		flag = false;
		}
	}
	if(scope == 5)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		var constituencyVal=document.getElementById('constituencyDiv').value;
		var mandalVal=document.getElementById('mandalDiv').value;
		if(stateVal == 0)
		{
		str += 'State Name is Required.<br>';
		flag = false;
		}
		if(districtVal == 0)
		{
		str += 'District Name is Required.<br>';
		flag = false;
		}
		if(constituencyVal == 0)
		{
		str += 'Constituency Name is Required.<br>';
		flag = false;
		}
		if(mandalVal == 0)
		{
		str += 'Mandal Name is Required.';
		flag = false;
		}
	}
	if(scope == 6 || scope == 8 || scope == 9)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		var constituencyVal=document.getElementById('constituencyDiv').value;
		var mandalVal=document.getElementById('mandalDiv').value;
		var villageVal=document.getElementById('villageDiv').value;
		if(stateVal == 0)
		{
		str += 'State Name is Required.<br>';
		flag = false;
		}
		if(districtVal == 0)
		{
		str += 'District Name is Required.<br>';
		flag = false;
		}
		if(constituencyVal == 0)
		{
		str += 'Constituency Name is Required.<br>';
		flag = false;
		}
		if(mandalVal == 0)
		{
		str += 'Mandal Name is Required.<br>';
		flag = false;
		}
		if(villageVal == 0)
		{
		str += 'Villiage Name is Required.';
		flag = false;
		}
	}
	if(scope == 7)
	{
		var stateVal=document.getElementById('stateDiv').value;
		var districtVal=document.getElementById('districtDiv').value;
		var constituencyVal=document.getElementById('constituencyDiv').value;
		var mandalVal=document.getElementById('mandalDiv').value;
		
		if(stateVal == 0)
		{
		str += 'State Name is Required.<br>';
		flag = false;
		}
		if(districtVal == 0)
		{
		str += 'District Name is Required.<br>';
		flag = false;
		}
		if(constituencyVal == 0)
		{
		str += 'Constituency Name is Required.<br>';
		flag = false;
		}
		if(mandalVal == 0)
		{
		str += 'Mandal Name is Required.';
		flag = false;
		}
		
	}
	
	str += '</font>';
	errorDivEle.innerHTML = str;

	return flag;
}

function removeAllUnwantedCharacters1(str)
{
   var strng = str.replace(/[\\\%\&\#\"+"]/g," ");
  
   return replaceEnterKey(strng,"  ");
} 


function getScopes(){
  
 var jsObj =
		{ 
            time : timeST,
			task:"getScopesForNewSearch"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}	

function getPartyGallariesForUplaod(contentType)
{
var specialPageId=document.getElementById("specialPageId").value;
	var jsObj =
		{ 
            specialPageId : specialPageId,
			contentType : contentType,
		   	task : "partyGallariesForUplaod"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getEventGallariesForUplaodPhotoAction.action?"+rparam;
	callAjax(jsObj,url);
}
function getScopes(){
  
 var jsObj =
		{ 
            time : timeST,
			task:"getScopesForNewSearch"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}

function buildResults(results,divId){

  var elmt = document.getElementById(divId);
         if(divId=='scopeDiv')
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
		for(var i in results)
	  {
		var option = document.createElement('option');
		if(divId =="scopeDiv")
		  {
		  option.value=results[i].locationScope;
		  option.text=results[i].locationScopeValue;
		  }
		else if(divId =="stateDiv" || divId =="districtDiv")
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
/*function getPartyGallariesForUplaod(contentType)
{
var specialPageId=document.getElementById("specialPageId").value;
	var jsObj =
		{ 
            specialPageId : specialPageId,
			contentType : contentType,
		   	task : "partyGallariesForUplaod"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getEventGallariesForUplaodPhotoAction.action?"+rparam;
	callAjax(jsObj,url);
}*/

function createListDataForNews(elmtId,optionsList)
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
	
    // document.getElementById('gallarySelectId').value = gGallaryId;
}
   
function onYouTubePlayerReady(playerId) 
{ 
	ytplayer = document.getElementById("video_overlay"); 
	ytplayer.setVolume(100);
}

function callAjaxForSpecialPage(jsObj,url)
{
	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText);
		 if(jsObj.viewtype == "getFirstFourNewsRecordsToDisplay")
			{
               showFirstFourNewsRecords(myResults);
			}
			
			else if(jsObj.task == "getFirstThreePhotoRecords")
		    {
				
              buildFirstThreePhotoRecords(myResults);
		    }

		 else if(jsObj.task == "setEmailAlertsForEvent")
         {
			   showStatusForEmailSubscription(myResults);
			}
		 else if(jsObj.task == "getNewsToDisplay")
			{
               showTotalNews(myResults);
			}
		 else if(jsObj.task == "getFirstThreePhotoGallaryDetail")
			{
               buildFirstThreePhotoRecords(myResults);
			}
		 else if(jsObj.task == "getPhotoGallaryWithOutGallerySizeZero")
			{
               buildCandidatePhotoGallary(myResults);
			}
		else if(jsObj.task == "getPhotosInAGallary" && jsObj.value=="old")
			{
               showPhotosInAGallary(myResults);
			}
		else if(jsObj.task == "getPhotosInAGallary" && jsObj.value=="new")
			{
			  showPhotosInInitialGallary(myResults);
			}
		else if(jsObj.task == "getLatestVideos")
			{
			   buildVideoGallaryDiv(myResults);
			}
		else if(jsObj.task == "getSpecialPageGallaryDetail")
			{
			     buildVideoGallaries(myResults);
		    }
		else if(jsObj.task == "getVideosInGallary")
            {
			   buildAllVideosInGallary(myResults);
			}
		else if(jsObj.task == "getNewsCountByScope")
            {
			   buildNewsCount(myResults);
			}
		else if(jsObj.task == "getNewsByScope")
            {
			   showTotalNews(myResults);
			}
		else if(jsObj.task == "getNewsBySourceScope")
            {
			   showTotalNews(myResults);
			}
		else if(jsObj.task == "getNewsByLanguageScope")
            {
			   showTotalNews(myResults);
			}	
		else if(jsObj.task == "setEmailAlertsForUser")
            {
			   showStatusForEmailSubscription(myResults);
			}
		else if(jsObj.task == "getOtherNews")
            {
			   showTotalNews(myResults);
			}
		else if(jsObj.task == "getContentDetails")	
			{
				showContent(myResults);
			}
		else if(jsObj.task == "getSelectedContent")
			{
				showContentResultList = myResults;
				buildContentDetails();
			}
		else if(jsObj.task == "subscriptionDetails")
			{
				unSubscribeBtnBuild();
			}
		else if(jsObj.task == "unsubscriptionDetails")
			{
				subscribeBtnBuild();
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

function setDefaultImage(img)
{
		img.src = "images/candidates/human.jpg";
}

function cleardescriptionFields()
  {
	document.getElementById('profileDescId').value = '';
}

function photoGallaryPopUp(){
	var str ='';
	 str+='<div style="margin:5px;font-size:13px;margin-left:32px;"> Loading Photo Galleries .....<img style="float:right;margin-right:249px;display:block;" src="images/icons/goldAjaxLoad.gif" id="videosLoadingImg_ImgSpan"></div>';
	$("#buildPhotoGallaryDiv").html(str);

	if(document.getElementById('buildPhotoGallaryDiv') == null)
		return;
     $("#buildPhotoGallaryDiv").dialog({ stack: false,
							    height: 570,
								width: 720,
								position:[130,130],								
								modal: true,
								title:'<font color="Navy">Photo Galleries</font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#buildPhotoGallaryDiv").dialog();
	showPhotoGallery();
  }

function getCompleteGallaries(gallaryId){
	gGallaryId=gallaryId;
    var jsObj =
		{ 
            time : timeST,
			value:"old",
		    gallaryId:gallaryId,
			task:"getPhotosInAGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjaxForSpecialPage(jsObj,url);
}
function showPhotosInAGallary(results){

   var str ='';
   str+='<div id="content" style="width:650px;">';
   str += '<fieldset class="imgFieldset">';
   str+='<table width="100%" style="margin-top:10px;">'
   str+='<tr><td>';
   str+='<input type="button" value="Back To Gallery"  class="imageButton" onclick="showPhotoGallery();" />';
   str+= '</td></tr>';
   for(var i in results)
   {
     no_of_imagesPerRow = 3; 
     j = i;
     if(j++ % no_of_imagesPerRow == 0){
       str+= '<tr style="height:220px;">';
     }
     str+= '<td width="33%" class="imageStyle"><table class="tableStyle">';
	 str += '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].fileTitle1+'</b></font></div></td></tr>';
     str+= '<tr><td><a rel="photo_gallery" href="'+results[i].path+'" title="'+results[i].fileTitle1+'"><img alt="" src="'+results[i].path+'" class="gallaryImg" height="100px" /></a></td></tr>';
	 str += '<tr><td><div><b>'+results[i].fileDescription1+'</b></div></td></tr>';
     str+= '<tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';
     if(j % no_of_imagesPerRow == 0){
       str+= '</tr>';
     }

   }
   str+= ' </table>';
   str += ' </fieldset>';
   str+='</div>';
   document.getElementById("buildPhotoGallaryDiv").innerHTML = str;

   $("a[rel=photo_gallery]").fancybox({
     'transitionIn'		: 'none',
     'transitionOut'		: 'none',
     'titlePosition' 	: 'over',
     'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
        return '<div id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; <span>' + title : '') + '</span></div>';
     }
   });
}
function showPhotosInInitialGallary(results){
   var str ='';
   str+='<div id="content" style="display:none;">';
	count = 0;
   for(var i =0;i<results.length; i++)
   {
   for(var k in results[i].filePath)
	{
     no_of_imagesPerRow = 3; 
     j = count;
     if(j++ % no_of_imagesPerRow == 0){
       str+= '<tr style="display:none">';
     }
     str+= '<td width="33%" class="imageStyle"><table class="tableStyle">';
	 str += '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].fileTitle1+'</b></font></div></td></tr>';
     str+= '<tr><td><a rel="photo_gallery" id="photoClickId'+i+''+k+'" href="'+results[i].filePath[k]+'" title="'+results[i].fileTitle1+'"><img alt="" src="'+results[i].filePath[k]+'" class="gallaryImg" height="100px" /></a></td></tr>';
	 str += '<tr><td><div><b>'+results[i].fileDescription1+'</b></div></td></tr>';
     str+= '<tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';
     if(j % no_of_imagesPerRow == 0){
       str+= '</tr>';
     }
	 count++;
    }
   }
   str+= ' </table>';
   str+='</div>';
   document.getElementById("buildPhotoGallaryDiv").innerHTML = str;

   $("a[rel=photo_gallery]").fancybox({
     'transitionIn'		: 'none',
     'transitionOut'		: 'none',
     'titlePosition' 	: 'over',
     'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
        return '<div id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; <span>' + title : '') + '</span></div>';
     }
   });
    $('#photoClickId00').trigger('click');
   //fireEvent(document.getElementById('photoClickId'),'click');
    //document.getElementById("buildPhotoGallaryDiv").innerHTML ='';
}
function fireEvent(obj,evt){
	
	var fireOnThis = obj;
	if( document.createEvent ) {
	  var evObj = document.createEvent('MouseEvents');
	  evObj.initEvent( evt, true, false );
	  fireOnThis.dispatchEvent(evObj);
	} else if( document.createEventObject ) {
	  fireOnThis.fireEvent('on'+evt);
	}
}

function showFirstFourNewsRecords(results)
 { 
  newsData = results;
  var str ='';
  
  if(results.length>0)
  {
   
   str +='<h1 class="pr-title">news &amp; Events<span class="or-down-arrow"><img src="images/candidatePage/or-down-arrow.png" alt=""/></span></h1>';

   str+='<ul>';
   for(var i =0 ;i<results.length && i<4;i++)
   {
     var source ='';
      for(var j in results[i].fileVOList)
	   source += ''+results[i].fileVOList[j].source+' - ';
	   
	   source  = source.slice(0, -2); 
	   
     initialFileIdArray[i]=results[i].fileId;
     str+='<a href="javascript:{}" onclick="getVideoDetails('+results[i].contentId+')" class="titleStyle">';
	 
	 if(results[i].fileTitle1.length > 40)
		str +='<li><strong>'+results[i].fileTitle1.substring(0,40)+'..</strong>';
	 else
		str +='<li><strong>'+results[i].fileTitle1+'</strong>';

	 str += '</a>';
     str+='<div class="year-time"><span class="li-red">'+source+'</span> | '+results[i].fileDate+'</div>';
     
	 if(results[i].fileDescription1.length > 62)
		str += results[i].fileDescription1.substring(0,103)+'..</li>';
	 else
		str += ''+results[i].fileDescription1+'</li>';

     }
   str+='  </ul>';
   
   str+='<div class="more"><a href="javascript:{}" onclick="getCompleteNews(\'totalNews\');">More</a></div>';
   
   str+='<table><tr><td><div id="showNewsDiv" /></td></tr></table>';
   str+='<table><tr><td><div id="showAllNewsDiv" /></td></tr></table>';
   
  
    for(var i =4 ;i<results.length;i++)
	{
	  initialFileIdArray[i]=results[i].fileId;
	}
   }
	else
	{
	str+='<div class="pft-sec" style="padding-left:48px;"> <img src="./images/new_homepage/pft.jpg" alt=""/>';
            str+='<div class="clear"></div>';
            str+='<p></p>';
            str+='<span class="gray">Are you a</span>'; 
			str+='<strong>Politician';
			str+='<span class="orange">/</span>Political Party';
			str+='<span class="orange">/</span>Media...</strong> Want to know how you can be benefited with ';
			str+='<span class="orange">PartyAnalyst</span> ?';
            str+='<div class="clear"></div>';
            str+='<div class="clickhere-button">';
			str+='<a href="viewFeaturesAction.action">Click Here to Learn More...</a></div>';
          str+='</div>';
	}
    document.getElementById("newsDisplayDiv").innerHTML=str;
 }
 function showNewsWithDiffSources(fileId,filSize,arrayType)
 {
     if(arrayType=="initialArray")
        initialCurrentSize=filSize;
     if(arrayType=='array')
        currentSize= filSize;
	arraySize = fileIdArray.length;
	initialArraySize = initialFileIdArray.length;
     var results;
	 if(arrayType=="initialArray")
	  {
	    for(var i in newsData){
	       if(newsData[i].fileId == fileId)
	        results = newsData[i];
	     }
	  }
	 else
	 {
	    for(var i in newsPopUpData){
	       if(newsPopUpData[i].fileId == fileId)
	        results = newsPopUpData[i];
	     }
	 }
	  $.fx.speeds._default = 1000;
	  $("#showNewsDiv").dialog({ stack: false,
								height: 'auto',
								width: 950,
								closeOnEscape: true,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 950,
								title:'<center><font color="Navy">'+results.fileTitle1+'</font><center>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#showNewsDiv").dialog();

	var str='<DIV id="showremainingNewsSources"><center>';

	 str+='<table>';
	 str+='     <tr>';
	 str+='       <td>';
	 str+='        <B>Source</B> : <font color="#FF4500">'+results.fileVOList[0].source+'</font> &nbsp;&nbsp;&nbsp;<B> Date </B>:<font color="#FF4500"> '+results.fileDate+'</font>';
	 str+='       </td>';
	 str+='       <td>';
	 //str+='       <a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/specialPageAction.action?specialPageId='+specialPageId+'&contentId='+results.contentId+'\')"><img alt="Share in Facebook" src="images/FBshare.jpg" title="Click here to Share this News in Facebook" style="margin-left:30px;"></img></a>';
	 str +='<a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/specialPageAction.action?specialPageId='+specialPageId+'&contentId='+results.contentId+'\')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a>';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='     </table>';
	
	 str+='     <table>';
	 str+='			<tr>';

	if(arrayType=='array')
	{
	 if(currentSize-1 >=0)
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getPreviousNews('+currentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
	 }

	 
		str+='             <td><div  id="newschangeDIV" class="popupcontainer"><img alt="'+results.fileTitle1+'" src="'+results.fileVOList[0].fileVOList[0].path+'" title="'+results.fileDescription1+'" style="max-width:780px;max-length:800px;"/></div></td>';
	 

	 if(currentSize+1 <= (arraySize-1))
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getNextNews('+currentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/next.png"  class="newsImage" /></a></td>';
     }
	 
  }

	else if(arrayType=="initialArray")
	{
	 
	 if(initialCurrentSize-1 >=0)
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getPreviousNews('+initialCurrentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
	 }
	 
	 
	 str+='             <td><div id="newschangeDIV" class="popupcontainer"><img alt="'+results.fileTitle1+'" src="'+results.fileVOList[0].fileVOList[0].path+'" title="'+results.fileDescription1+'" style="max-width:780px;max-length:800px;"/></div></td>';
	 
	 
	 if(initialCurrentSize+1 <= (initialArraySize-1))
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getNextNews('+initialCurrentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/next.png"  class="newsImage" /></a></td>';
     }
	 
	}

	str+='		      </tr>';
	str+='         </table>';

	str += '<table><tr>';
	str+='       <td>';
	str+='        '+results.fileDescription1+'';
	str+='       </td>';
	str+='     </tr>';
	str+='</table>';
	
	str += '</center></DIV>';
	
    str +='<div id="buildSourceParts">';
	str += '<center><table><tr>';

	for(var j=1;j<results.fileVOList[0].fileVOList.length;j++)
	{
	  str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showAnotherNewsPart('+results.fileVOList[0].fileSourceLanguageId+','+results.fileVOList[0].fileVOList[j].orderNo+','+fileId+',\''+arrayType+'\',\''+results.fileTitle1+'\',\''+results.fileDescription1+'\',\''+results.fileVOList[0].fileVOList[j].path+'\')"><img width="65" height="60" alt="'+results.fileTitle1+'" title="'+results.fileDescription1+'" src="'+results.fileVOList[0].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+results.fileVOList[0].fileVOList[j].orderName+'</a></td>';
	}
	str += '  </tr></table>';
	str +='</center></div>';
	
	if(results.multipleSource >1 )
	{
	  str +='<div id="buildSources">';
	  str += '<center><table><tr><td><b>Same News in another sources</b></td></tr></table></center>';
	  str += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
	  for(var k=1;k<results.fileVOList.length;k++)
	  {
	     str += '<td><a class="newssources" href="javascript:{}" onclick="showAnotherSource('+results.fileVOList[k].fileSourceLanguageId+','+fileId+',\''+arrayType+'\')">'+results.fileVOList[k].source+'</a></td>';
	  }
	  str += '  </tr></table>';
	  str +='</center></div>';
	}
	str += '<div id="facebookCommentsInNewsDiv" class="popupcontainer" style="overflow:auto;width:600px;"></div>';

	document.getElementById("showNewsDiv").innerHTML=str;

	var lb = document.getElementById("facebookCommentsInNewsDiv");
	lb.innerHTML='<div class="fb-comments" data-href="http://www.partyanalyst.com/specialPageAction.action?specialPageId='+specialPageId+'&contentId='+results.contentId+'" data-num-posts="5" data-width="500"></div>';
	FB.XFBML.parse(lb);
 
 }
 function showAnotherNewsPart(fileSourceLanguageId,orderNo,fileId,arrayType,title,desc,path)
 {
    var results;
	 if(arrayType=="initialArray")
	  {
	    for(var i in newsData){
	       if(newsData[i].fileId == fileId)
	        results = newsData[i];
	     }
	  }
	 else
	 {
	    for(var i in newsPopUpData){
	       if(newsPopUpData[i].fileId == fileId)
	        results = newsPopUpData[i];
	     }
	 }
	 
	 
	  document.getElementById("newschangeDIV").innerHTML = '<img alt="'+title+'" src="'+path+'" title="'+desc+'" style="max-width:780px;max-length:800px;"/>';
	  var str='';
	  str += ' <center> <table><tr>';
	 for(var j in results.fileVOList)
	 {
	    if(results.fileVOList[j].fileSourceLanguageId == fileSourceLanguageId)
		 for(var k in results.fileVOList[j].fileVOList)
		 {
		   if(results.fileVOList[j].fileVOList[k].orderNo != orderNo)
		    str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showAnotherNewsPart('+results.fileVOList[j].fileSourceLanguageId+','+results.fileVOList[j].fileVOList[k].orderNo+','+results.fileId+',\''+arrayType+'\',\''+results.fileTitle1+'\',\''+results.fileDescription1+'\',\''+results.fileVOList[j].fileVOList[k].path+'\')"><img width="65" height="60" alt="'+results.fileTitle1+'" title="'+results.fileDescription1+'" src="'+results.fileVOList[j].fileVOList[k].path+'" /><br />&nbsp;&nbsp;'+results.fileVOList[j].fileVOList[k].orderName+'</a></td>';
		 }
	 }
	 str += '  </tr></table></center>';
	 document.getElementById("buildSourceParts").innerHTML = str;
 }
 function showAnotherSource(fileSourceLanguageId,fileId,arrayType)
 {
    var results;
	 if(arrayType=="initialArray")
	  {
	    for(var i in newsData){
	       if(newsData[i].fileId == fileId)
	        results = newsData[i];
	     }
	  }
	 else
	 {
	   for(var i in newsPopUpData){
	       if(newsPopUpData[i].fileId == fileId)
	        results = newsPopUpData[i];
	     }
	 }
 var fileVo;
 for(var j in results.fileVOList)
 {
    if(results.fileVOList[j].fileSourceLanguageId == fileSourceLanguageId)
     fileVo = results.fileVOList[j];
 }
    str = '';
    str+='<center>';

	 str+='<table>';
	 str+='     <tr>';
	 str+='       <td>';
	 str+='        <B>Source</B> : <font color="#FF4500">'+fileVo.source+'</font> &nbsp;&nbsp;&nbsp;<B> Date </B>:<font color="#FF4500"> '+results.fileDate+'</font>';
	 str+='       </td>';
	 str+='       <td>';
	 //str+='       <a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/candidateElectionResultsAction.action?candidateId=${candidateId}&contentId='+results.contentId+'\')"><img alt="Share in Facebook" src="images/FBshare.jpg" title="Click here to Share this News in Facebook" style="margin-left:30px;"></img></a>';
	 str +='<a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/candidateElectionResultsAction.action?candidateId=${candidateId}&contentId='+results.contentId+'\')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a>';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='     </table>';
	
	 str+='     <table>';
	 str+='			<tr>';

	if(arrayType=='array')
	{
	 if(currentSize-1 >=0)
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getPreviousNews('+currentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
	 }

	 
		str+='             <td><div  id="newschangeDIV" class="popupcontainer"><img alt="'+results.fileTitle1+'" src="'+fileVo.fileVOList[0].path+'" title="'+results.fileDescription1+'" style="max-width:780px;max-length:800px;"/></div></td>';
	 

	 if(currentSize+1 <= (arraySize-1))
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getNextNews('+currentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/next.png"  class="newsImage" /></a></td>';
     }
	 
  }

	else if(arrayType=="initialArray")
	{
	 
	 if(initialCurrentSize-1 >=0)
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getPreviousNews('+initialCurrentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
	 }
	 
	 
	 str+='             <td><div id="newschangeDIV" class="popupcontainer"><img alt="'+results.fileTitle1+'" src="'+fileVo.fileVOList[0].path+'" title="'+results.fileDescription1+'" style="max-width:780px;max-length:800px;"/></div></td>';
	 
	 
	 if(initialCurrentSize+1 <= (initialArraySize-1))
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getNextNews('+initialCurrentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/next.png"  class="newsImage" /></a></td>';
     }
	 
	}

	str+='		      </tr>';
	str+='         </table>';

	str += '<table><tr>';
	str+='       <td>';
	str+='        '+results.fileDescription1+'';
	str+='       </td>';
	str+='     </tr>';
	str+='</table>';
	
	str += '</center>';
 
 document.getElementById("showremainingNewsSources").innerHTML = str;
 
  str ='';
	str += '<center>  <table><tr>';

	for(var l=1;l<fileVo.fileVOList.length;l++)
	{
	  str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showAnotherNewsPart('+fileVo.fileSourceLanguageId+','+fileVo.fileVOList[l].orderNo+','+fileId+',\''+arrayType+'\',\''+results.fileTitle1+'\',\''+results.fileDescription1+'\',\''+fileVo.fileVOList[l].path+'\')"><img width="65" height="60" alt="'+results.fileTitle1+'" title="'+results.fileDescription1+'" src="'+fileVo.fileVOList[l].path+'" /><br />&nbsp;&nbsp;'+fileVo.fileVOList[l].orderName+'</a></td>';
	}
	str += '  </tr></table></center>';
	
	document.getElementById("buildSourceParts").innerHTML = str;
	
	str ='';
	str += '<center><table><tr><td><b>Same News in another sources</b></td></tr></table></center>';
	str += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
	for(var k=0;k<results.fileVOList.length;k++)
	{
	  if(results.fileVOList[k].fileSourceLanguageId != fileSourceLanguageId)
	  str += '<td><a  class="newssources"  href="javascript:{}" onclick="showAnotherSource('+results.fileVOList[k].fileSourceLanguageId+','+fileId+',\''+arrayType+'\')">'+results.fileVOList[k].source+'</a></td>';
	}
	str += '  </tr></table></center>';

  
    document.getElementById("buildSources").innerHTML = str;
 
 }

 function buildFirstThreePhotoRecords(results)
{
	if(results.length>0)
	 {
	  var count=0;
	  document.getElementById("photoGallaryDiv").innerHTML= '';
	  str ='';
	  str+='<h3 class="main-title"><span class="da-gray">PHOTO GALLERY</span></h3>';
	  str+='<ul class="photo-gallery-fields">';
	  if(results[0].path!=null)
	  {
	   count++;
	   str+='<li><img alt="" src="'+results[0].path+'" style="height: 120px;width: 115px;border: 1px solid #CDCDCD;padding:3px;border-radius: 3px;"  onclick="getCandidatesPhotosInAGallary('+results[0].gallaryId+')"/><br />';
	   str+=''+results[0].gallaryName+'</li>';
	 
	  }
	  if(results[1]!=null && results[1].path!=null)
	  {
	  count++;
	  str+='<li><img alt="" src="'+results[1].path+'" style="height: 120px;width: 115px;border: 1px solid #CDCDCD;padding:3px;border-radius: 3px;" onclick="getCandidatesPhotosInAGallary('+results[1].gallaryId+')"/><br />';
	  str+=''+results[1].gallaryName+'</li>';
	  }
	  if(results[2]!=null  && results[2].path!=null)
	  {
	  count++;
	  str+=' <li><img alt="" src="'+results[2].path+'" style="height: 120px;width: 115px;border: 1px solid #CDCDCD;padding:3px;border-radius: 3px;" onclick="getCandidatesPhotosInAGallary('+results[2].gallaryId+')"/><br />';
	  str+=''+results[2].gallaryName+'</li>';
	  
	  }
	  for(var i=3;i<results.length;i++)
	  {
	   if(results[i]!=null  && results[i].path!=null && count<3)
	   {
		count++;
		str+='<li><img alt="" src="'+results[i].path+'" style="height:120px;width:127px;" onclick="getCandidatesPhotosInAGallary('+results[i].gallaryId+')"/><br />';
		str+=''+results[i].gallaryName+'</li>';
	   }
	  }
	  str+='</ul>';
	  str+='<div class="more">';
	  str+='<a href="javascript:{}" onclick="photoGallaryPopUp()">More</a></div>';
	  str+='<div id="buildPhotoGallaryDiv"></div>';
	  document.getElementById("photoGallaryDiv").innerHTML= str;
	 }
	 else
	 {
		 document.getElementById("photoGallaryDiv").style.display = 'none';
	 }
}

 function getTotalNews(viewType)
 {  
	var queryType='Public';
   	var jsObj =
		{   
		    time : timeST,
			viewtype:viewType,
			specialPageId:specialPageId,
			startRecord:0,
			maxRecord:100,
			queryType:queryType,
			task:"getNewsToDisplay"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "specialPagePhotoGallaryAction.action?"+rparam;						
	callAjaxForSpecialPage(jsObj,url);  
 }
 function showTotalNews(results)
 { 
	 var str='';
     deleteAllElements();
     newsPopUpData = results;
    $("#showAllNewsDiv").dialog({ stack: false,
							    height: 570,
								width: 720,
								position:[150,120],								
								modal: true,
								title:'<font color="Navy"><b>News</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});

	str+='<div style="margin:5px;font-size:13px;margin-left:32px;"> Loading News Galleries .....<img style="float:right;margin-right:249px;display:block;" src="images/icons/goldAjaxLoad.gif" id="videosLoadingImg_ImgSpan"></div>';
	$("#showAllNewsDiv").html(str);
	$("#showAllNewsDiv").dialog();
	
    if(results.length>0)
  {
     var str ='';
     str+='<fieldset class="imgFieldset">';
	 str+='   <table>';
	 str+='     <tr>';
     str+='       <td colspan="2"><div id="showScopeWiseNewsCount" /></td>';
     str+='     </tr>';
	 str+='   </table>';
     str+='  <table style="width: 100%; font-family: trebuchet MS; font-size: 13px;">'; 
  for(var i in results)
   { 
	 fileIdArray[i]=results[i].fileId;	    	  
     str+='     <tr>';
     str+='       <td><a href="javascript:{}" onclick="getVideoDetails('+results[i].contentId+')" class="titleStyle">'+results[i].fileTitle1+'</a></td>';
     str+='     </tr>';
     str+='     <tr>';
	 var sourcedata ='';
	 var languag = new Array();

	 for(var j in results[i].fileVOList)
	 {
	    count = 0;
	   sourcedata+='<span class="titleStyle"><font color="#FF4500">'+results[i].fileVOList[j].source+'</font></span> - ';
       for(var k in languag)
	   {
	     if(languag[k] == results[i].fileVOList[j].language)
	       ++count;
	   }
	   if(count == 0)
	   languag.push(results[i].fileVOList[j].language);
	 }
	 var lang = '';
	 for(var l in languag)
	   {
	     lang+='<span  class="titleStyle"><font color="#FF4500">'+languag[l]+'</font></span> - ';
	   }
	 str+='       <td>'+sourcedata.slice(0, -2)+' | '+lang.slice(0, -2)+' | '+results[i].fileDate+'</td>'; 
	 
     str+='     </tr>';
     str+='     <tr>';
     str+='       <td>'+results[i].fileDescription1+'</td>';
     str+='     </tr>';
	 str+='    <tr><td><hr style="width:98%;"></td></hr></tr>';
   }
   str+='  </table>';
   str+='<table style="width:100%">';
   str+='<tr><td><div id="custom_paginator_class" class="paginatorElmtClass" ><td></tr>';
   str+='</table>';
   str+='</fieldset>';
   document.getElementById("showAllNewsDiv").innerHTML=str;
  //getScopeWiseNewsCount();
   }
 }
 function  buildNewsCount(result)
 {
    if(result[result.length-1].visibility=="False")
	   queryTypeChecked='Public';
    str='';
	str+='   <table align="center">';
	str+='     <tr>';
	if(result[result.length-1].visibility=="True")
	{	 
	 str+='       <td>Sort By : </td>';
	 str+='       <td>';
	 str+='          <select id="candidateVisibility" onchange="getTotalNews(\'totalNews\');"  >';
	 str+='             <option value="Public">Public</option>';
	 str+='             <option value="Private">Private</option>';
	 str+='             <option value="All">All</option>';
	 str+='          </select>';
	 str+='       </td>';	 
   	}
	 str+='       <td>&nbsp;&nbsp;Total News Articels : </td>';
	 str+='       <td><a href="javascript:{}" onclick="getScopeWiseNews(\'\')" ><font color="brown">'+result[result.length-2].fileTypeId+'</font></a></td>';
	 str+='    </tr>';
	 str+='   </table>';
     str+='   <table>';
	 str+='     <tr>';
	 str+='     <td>News Impact: </td>';
	 if(result[3].fileTypeId>0)
     str+='       <td>'+result[3].name+' Level -<a href="javascript:{}" onclick="getScopeWiseNews('+4+')" ><font color="brown">'+result[3].fileTypeId+'</font></a></td>';
	 else
	 str+='       <td>'+result[3].name+' Level -<font color="brown">'+result[3].fileTypeId+'</font></td>';
	 if(result[4].fileTypeId>0)
	 str+='       <td>'+result[4].name+' Level -<a href="javascript:{}" onclick="getScopeWiseNews('+5+')" ><font color="brown">'+result[4].fileTypeId+'</font></a></td>';
	 else
	 str+='       <td>'+result[4].name+' Level -<font color="brown">'+result[4].fileTypeId+'</font></td>';
     if(result[5].fileTypeId>0)
	 str+='       <td>'+result[5].name+' Level -<a href="javascript:{}" onclick="getScopeWiseNews('+6+')" ><font color="brown">'+result[5].fileTypeId+'</font></a></td>';
	 else
	 str+='       <td>'+result[5].name+' Level -<font color="brown">'+result[5].fileTypeId+'</font></td>';
	
	var remainingCount = getRemainingCount(parseInt(result[result.length-2].fileTypeId),parseInt(result[3].fileTypeId),parseInt(result[4].fileTypeId),parseInt(result[5].fileTypeId));
	
	if(remainingCount>0)
	 str+='       <td> Others -<a href="javascript:{}" onclick="getOtherNews()" ><font color="brown">'+remainingCount+'</font></a></td>';
	 else
	 str+='       <td> Others -<font color="brown">'+remainingCount+'</font></td>';
	
     str+='     </tr>';
	 str+='   </table>';
    document.getElementById("showScopeWiseNewsCount").innerHTML=str; 
	if(queryTypeChecked=="Public")
     {
	    if(document.getElementById("candidateVisibility")!=null)  
        {		
	     var candidateVisibilityEle = document.getElementById("candidateVisibility").value='Public';
        }		 
	 }
   if(queryTypeChecked=="Private")
     {
	   if(document.getElementById("candidateVisibility")!=null)    
	    {		
	     var candidateVisibilityEle = document.getElementById("candidateVisibility").value='Private';
        }
	 }
   if(queryTypeChecked=="All")
     {
	   if(document.getElementById("candidateVisibility")!=null)    
	    {		
	     var candidateVisibilityEle = document.getElementById("candidateVisibility").value='All';
        }
	 }
	
 }
 function deleteAllElements()
 {
   
   for(var i=0 ; i<fileIdArray.length;)
   {
     fileIdArray.pop();
   }
 }
 function getScopeWiseNews(scopeId)
 {
   deleteAllElements();
   timeST = new Date().getTime();
   var queryType='Public';
   if(document.getElementById("candidateVisibility")!=null)
    { 
	   var candidateVisibilityEle = document.getElementById("candidateVisibility");
	    if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Public')
		 {
		  queryType='Public';
		  queryTypeChecked='Public';
		 }
	}
   if(document.getElementById("candidateVisibility")!=null)
    {
	   var candidateVisibilityEle = document.getElementById("candidateVisibility");
	    if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Private')
		 {
		  queryType='Private';
		  queryTypeChecked='Private';
		 }
	}
   if(document.getElementById("candidateVisibility")!=null)
    {
	   var candidateVisibilityEle = document.getElementById("candidateVisibility");
	    if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='All')
		  {
		   queryType='All';
		   queryTypeChecked='All';
		  }
	}
   var jsObj =
		{   
		    time : timeST,
			specialPageId:specialPageId,
			scopeType:scopeId,
			startIndex:0,
			maxResults:100,
			queryType:queryType,
			task:"getNewsByScope"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidateNewsGallaryAction.action?"+rparam;						
	callAjaxForSpecialPage(jsObj,url); 
 }
function getCandidatesPhotosInAGallary(gallaryId)
{

    var jsObj =
		{   time : timeST,
			value:"new",
		    gallaryId:gallaryId,
			task:"getPhotosInAGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjaxForSpecialPage(jsObj,url);  
}
 function getOtherNews()
 {
    timeST = new Date().getTime();
   var jsObj =
		{   
		    time : timeST,
			specialPageId:specialPageId,
			startIndex:0,
			maxResults:100,
			queryType:queryTypeChecked,
			task:"getOtherNews"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidateNewsGallaryAction.action?"+rparam;						
	callAjaxForSpecialPage(jsObj,url); 
 
 }
 function getRemainingCount(total,constituency,mandal,village)
 {
    var remaining = total-constituency-mandal-village;
   return remaining;
 }
 function getScopeWiseNewsCount()
 { 
   timeST = new Date().getTime();
   var jsObj =
		{   
		    time : timeST,
			specialPageId:specialPageId,
			queryType:queryTypeChecked,
			task:"getNewsCountByScope"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidateNewsGallaryAction.action?"+rparam;						
	callAjaxForSpecialPage(jsObj,url); 
   
 }
 

  function getPreviousNews(val,arrayType)
  { if(arrayType=="initialArray")
    {
     initialCurrentSize = val-1;
    showNewsWithDiffSources(initialFileIdArray[initialCurrentSize],initialCurrentSize,arrayType);
	}
	else if(arrayType=='array')
	{
	  currentSize = val-1;
    showNewsWithDiffSources(fileIdArray[currentSize],currentSize,arrayType);
	
	}
	
	
  }
  function getNextNews(val,arrayType)
  {
    if(arrayType=="initialArray")
	{
     initialCurrentSize = val+1;
     showNewsWithDiffSources(initialFileIdArray[initialCurrentSize],initialCurrentSize,arrayType);
	 }
	else if(arrayType=='array')
	{
	 currentSize = val+1;
     showNewsWithDiffSources(fileIdArray[currentSize],currentSize,arrayType);
	}
  }
function getFirstThreePhotoRecords(){
	
   var jsObj =
		{   
		    time : timeST,
		    specialPageId:specialPageId,
			task:"getFirstThreePhotoGallaryDetail"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "specialPagePhotoGallaryAction.action?"+rparam;						
	callAjaxForSpecialPage(jsObj,url);  
}
function buildFirstThreePhotoRecords(results)
 {
   
	if(results.length>0)
	 {
	  var count=0;
	  document.getElementById("photoGallaryDiv").innerHTML= '';
	  str ='';
	  str+='<h3 class="main-title"><span class="da-gray">PHOTO GALLERY</span></h3>';
	  str+='<ul class="photo-gallery-fields">';
	  if(results[0].path!=null)
	  {
	   count++;
	   str+='<li><img class="imageClass" alt="'+results[0].title+'" src="'+results[0].path+'" onclick="getCandidatesPhotosInAGallary(\''+results[0].gallaryId+'\')" title="'+results[0].gallaryDescription+'"/><br />';
	  str+=''+results[0].title+'</li>';
	 
	  }
	  if(results[1]!=null && results[1].path!=null)
	  {
	  count++;
	  str+='<li><img class="imageClass" alt="'+results[1].title+'" src="'+results[1].path+'" onclick="getCandidatesPhotosInAGallary(\''+results[1].gallaryId+'\')" title="'+results[1].gallaryDescription+'"/><br />';
	  str+=''+results[1].title+'</li>';
	  }
	  if(results[2]!=null  && results[2].path!=null)
	  {
	  count++;
	  str+=' <li><img class="imageClass" alt="'+results[2].title+'" title="'+results[2].gallaryDescription+'" src="'+results[2].path+'"  onclick="getCandidatesPhotosInAGallary('+results[2].gallaryId+')"/><br />';
	  str+=''+results[2].title+'</li>';
	  
	  }
	  for(var i=3;i<results.length;i++)
	  {
	   if(results[i]!=null  && results[i].path!=null && count<3)
	   {
		count++;
		str+='<li><img class="imageClass" alt="'+results[i].title+'" title="'+results[i].gallaryDescription+'" src="'+results[i].path+'"  onclick="getCandidatesPhotosInAGallary('+results[i].gallaryId+')"/><br />';
	    str+=''+results[i].title+'</li>';
	   }
	  }
	  str+='</ul>';
	  str+='<div class="more">';
	  str+='<a href="javascript:{}" onclick="photoGallaryPopUp()">More</a></div>';
	  str+='<div id="buildPhotoGallaryDiv"></div>';
	  document.getElementById("photoGallaryDiv").innerHTML= str;
	 }
	 else
	 {
		 document.getElementById("photoGallaryDiv").style.display = 'none';
	 }
}
function openFile(filePath,fileType){

window.open(filePath, "browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
}

function getVideosOfCandidate()
{
	var jsObj =
		{   
			specialPageId : specialPageId,
			startIndex  : 0,
			maxRecords	: 100,
			time		: timeST,
			task:"getLatestVideos"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLatestVideosAction.action?"+rparam;					
	callAjaxForSpecialPage(jsObj,url);
}

function buildVideoGallaryDiv(result)
{
	if(result == null || result.length == 0)
		return;

	var videoGalEle = document.getElementById("videogallery");
	var str = '';

	str += '<DIV>';
	str += '<a rel="#voverlay" href="http://www.youtube.com/v/'+result[0].path+'?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer">';
	str += '<img src="http://img.youtube.com/vi/'+result[0].path+'/0.jpg" width="230px;" height="210px;"/></a>';
	str += '</DIV>';

	if(result.length >= 2)
	{
		str += '<DIV><table><tr>';
		for(var i=1;i<result.length && i<5;i++)
		{
			str += '<td><a rel="#voverlay" href="http://www.youtube.com/v/'+result[i].path+'?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer" width="70px;">';
	str += '<img src="http://img.youtube.com/vi/'+result[i].path+'/0.jpg" width="75px;" height="70px;"/></a></td>';
		}
		str += '</tr></table></DIV>';
	}

	videoGalEle.innerHTML = str;
}


function videoGallaryPopUp()
{
	var str='';
   $("#videoGallaryPopUpDiv").dialog({ stack: false,
							    height: 590,
								width: 630,
								position:[150,120],								
								modal: true,
								title:'<font color="Navy">Video Galleries</font>',
								overlay: { opacity: 0.5, background: 'black'},
	   							close: function(event, ui) {
		    document.getElementById("showContentHeaderDiv").innerHTML ='';
		  }
								});
	 str+='<div style="margin:5px;font-size:13px;margin-left:32px;"> Loading Video Galleries .....<img style="float:right;margin-right:249px;display:block;" src="images/icons/goldAjaxLoad.gif" id="videosLoadingImg_ImgSpan"></div>';
	$("#videoGallaryPopUpDiv").html(str);

	showAllVideoGalleries();
}

function showAllVideoGalleries(){
	var str ='';
	str+='<div style="margin:5px;font-size:13px;margin-left:32px;"> Loading Video Galleries .....<img style="float:right;margin-right:249px;display:block;" src="images/icons/goldAjaxLoad.gif" id="videosLoadingImg_ImgSpan"></div>';
	$("#videoGallaryPopUpDiv").html(str);

   var jsObj = {
	       	   time : timeST,
			   specialPageId : specialPageId,
			   startRecord:0,
			   maxRecord:100,
			   task:"getSpecialPageGallaryDetail"
            };

    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "specialPagePhotoGallaryAction.action?"+rparam;						
	callAjaxForSpecialPage(jsObj,url);
	}

/*function buildVideoGallaries(results)
{
    var str ='';
    str+='<table>';
    str += '<tr>';
	for(var i=0;i<results.length;i++)
	{
		str += '<td><table><tr><td><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].gallaryName+'</b></font></div></td></tr>';
		str += '<tr><td>';
		str+='<img src="http://img.youtube.com/vi/'+results[i].path+'/0.jpg" width="72px;" height="75px;" style="cursor: pointer;" onClick="getVideosInAGallary('+results[i].gallaryId+')"/></td></tr>';
		str+='</div>';
		str+= '<tr><td><div style="font-size: 13px; font-family: verdana,arial;""><b>Gallery Size: ('+results[i].sizeOfGallary+')</b></div></td></tr>';
		str += '<tr><td><div style="font-size: 13px; font-family: verdana,arial;"><b>'+results[i].gallaryDescription+'</b></table></td>';
			
		 }
		 str+='</tr>';
		 str+='</table>';
		document.getElementById("videoGallaryPopUpDiv").innerHTML = str;
}*/


function buildVideoGallaries(results)
{
    var str ='';
    str +='<table width="100%">';
	for(var i=0;i<results.length;i++)
	{
		if(i%4 == 0)
			str +='<tr height="185px;">';
			str +='<td valign="top" width="25%">';
			str +='<table width="100%" style="margin-bottom: 15px;">';
			str +='<tr><td style="padding-left:4px;"><img src="http://img.youtube.com/vi/'+results[i].path+'/0.jpg" height="120px;" width="110px;" style="cursor: pointer; border-radius: 5px 5px 5px 5px; left: 13px; padding: 7px; border: 1px solid #ccc; background: none repeat scroll 0pt 0pt #d3d3d3;" onClick="getVideosInAGallary('+results[i].gallaryId+')"/>';
			str +='</td></tr>';
			str +='<tr><td><div style="font-size: 13px; font-family: verdana,arial;"><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].gallaryDescription+'</b></font></td></tr>';
			str +='<tr><td><div style="font-size: 13px; font-family: verdana,arial;"><b>Gallery Size: ('+results[i].sizeOfGallary+')</b></div> </td></tr>';
			str +='</table>';
			str +='</td>';
			
			if((i+1)% 4 == 0)
			str += '</tr>';


	}

		str += '</table>';
		document.getElementById("videoGallaryPopUpDiv").innerHTML = str;
}

function getVideosInAGallary(gallaryId){

    var jsObj = {
			
			time : timeST,
			gallaryId:gallaryId,
			task:"getVideosInGallary"
          };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjaxForSpecialPage(jsObj,url);
}


function getVideoDetails(contentId)
{
	$.fx.speeds._default = 1000;
	  $("#showContentDiv").dialog({ stack: false,
								height: 'auto',
								width: 950,
								closeOnEscape: true,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 950,
								minHeight: 650,
								overlay: { opacity: 0.5, background: 'black'},
								close: function(event, ui) {
								document.getElementById("showContentDivInnerDiv").innerHTML ='';
							 }
		  
								});
		$("#showContentDiv").dialog();
		getContentDetails(contentId);
}

function buildAllVideosInGallary(results){


 var str ='';
 var videosDivElmt = document.getElementById("videoGallaryPopUpDiv");
		
	str+='<a href=javascript:{} style="text-decoration:none;color: #FFFFFF;margin-left: 339px;"" onclick="showAllVideoGalleries()" class="imageButton">Back To Galleries</a>';
		
		str+='<table style="width:100%;margin-top: 8px;">';
		for(var i in results)
		{
			no_of_imagesPerRow = 3; 
			j = i;

			if(j++ % no_of_imagesPerRow == 0)
				str += '<tr style="height:230px;">';
			
			str+='<td width="5%" valign="top">';
			str+='<table style="font-size: 13px; font-family: verdana,arial;width:180px;">';
			/*str+='<tr>';
			  str+='<td><div style="margin-bottom:5px;">'+results[i].title+'</div>';
			str+='</td>';
			str+='</tr>';*/
			str+='<tr>';
			str+='<td style="border:2px solid #ccc;padding:5px;width:100px;">';
			str+= '<a href=javascript:{} onclick="getVideoDetails('+results[i].contentId+')">';
		
			//str+='<a target="blank"  href="http://www.youtube.com/v/'+results[i].pathOfFile+'?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer">';
			//str+='<img style="border: 2px solid #CCCCCC;padding:5px;background:#cccccc;" src="http://img.youtube.com/vi/'+results[i].pathOfFile+'/0.jpg" width="160px;" height="160px;" title="'+results[i].description+'"/></a></td>';
			str+='<img src="http://img.youtube.com/vi/'+results[i].pathOfFile+'/0.jpg" width="160px;" height="160px;" title="'+results[i].description+'"/></a></td>';
			str+='</tr>';
			str+='<tr>';
			/* str+='<td width="30%"><div style="margin-top:5px;">'+results[i].description+'</div>';
			str+='</td>';*/
			str+='<td width="30%"><div style="margin-top:3px;margin-bottom:6px">'+results[i].title+'</div>';
			str+='</td>';

			str+='</table>';
			
			if(j % no_of_imagesPerRow == 0)
             str+= '</tr>';
						
		}
		str+='</table>';
		str+='</div>';
		videosDivElmt.innerHTML =str;
}

function buildCreateNewDiv()
{
document.getElementById('profileManagementMainOuterDiv6').style.display = 'none' ;
document.getElementById('profileManagementHeaderDiv1').style.display = 'block' ;

	if(document.getElementById('descriptionDiv').style.display = 'block')
		document.getElementById('descriptionDiv').style.display = 'none' ;

	if(document.getElementById('newsGallaryDiv').style.display = 'block')
	document.getElementById('newsGallaryDiv').style.display = 'none';

	if(document.getElementById("videoGallaryDiv").style.display = 'block')
		document.getElementById("videoGallaryDiv").style.display = 'none' ;

	if(document.getElementById("createNewDiv").style.display = 'none')
		document.getElementById("createNewDiv").style.display = 'block' ;

	if(document.getElementById("metaInfoDiv").style.display = 'block')
		document.getElementById("metaInfoDiv").style.display = 'none' ;

	if(document.getElementById("photoGallaryDiv").style.display = 'block')
		document.getElementById("photoGallaryDiv").style.display = 'none' ;
	if(document.getElementById("specialPageInfoDiv").style.display = 'block')
		document.getElementById("specialPageInfoDiv").style.display = 'none' ;

	if(document.getElementById('specialPageHighLightsDiv').style.display = 'block')
	   document.getElementById('specialPageHighLightsDiv').style.display = 'none' ;

$("#createNewTabId").css({"background":"none repeat scroll 0 0 #F61D50"});
$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#developmentGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#profileGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageInfoId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageHighLightId").css({"background":"none repeat scroll 0 0 #0063DC"});

	document.getElementById("headingnames").innerHTML = "Create New";
	var createNewDivElmt = document.getElementById("createNewDiv");
	var str ='';
	str +='<div id="content" style="width:650px;">';
		
	str += '<fieldset class="imgFieldset" style="width:400px;margin-top:25px;margin-left:auto;margin-right:auto;">';
	str += '<h2 align="center">Create A Special Page</h2>';
	
	str += '<div id="createNewInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	
	str += '<table style="margin-left: 52px;" class="paddingCss"><tr><td><div id="createNewErrorMsgDivId"></div></td></tr></table>';

	str += '<table align="center" width="75%">';
	str += '<tr><td><b><font color="#4B74C6">Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="createNewNameId" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="createNewTitleId" size="25" maxlength="500"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Heading<font class="requiredFont">*</font></font></b></td><td><input type="text" id="createNewHeadingId" size="25" maxlength="300"></td></tr>';

	str += '<table align="center"><tr><td style="padding-right:5px"><input type="button" class="imageButton" value="Create" style="background-color:#57B731" onClick="createNewSpecialPage()"></td><td style="padding-right: 10px"><input type="button" class="imageButton" value="Cancel" onclick="clearCreateNewDiv()" style="background-color:#CF4740"></td></tr></table>';

	
	str += '</fieldset>';
	str +='</div>';
	
	createNewDivElmt.innerHTML = str;
}


function createNewSpecialPage()
{
	var name	= document.getElementById('createNewNameId').value;
	var title	= document.getElementById('createNewTitleId').value;
	var heading = document.getElementById('createNewHeadingId').value;

	var errorDivEle = document.getElementById('createNewErrorMsgDivId');
	var eFlag = false;

	var str = '<font color="red">';
	if(name.length == 0)
	{
		str += 'Special Page Name Required<br>';
		eFlag = true;
	}
	if(title.length == 0)
	{
		str += 'Special Page Title Required<br>';
		eFlag = true;
	}
	if(heading.length == 0)
	{
		str += 'Special Page Heading Required<br>';
		eFlag = true;
	}
	
	str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;
	
	name = removeAllUnwantedCharacters(name);
	title = removeAllUnwantedCharacters(title);
	heading = removeAllUnwantedCharacters(heading);

	var jsObj =
		{ 
            name	: name,
		    title	: title,
			heading : heading,
			task	: "createNewSpecialPage"
		};


	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewEventGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function clearCreateNewDiv()
{
	var createNewDivElmt = document.getElementById("createNewDiv");
	createNewDivElmt.innerHTML = '';
}

function clearFieldsOfCreateNewDiv()
{
	document.getElementById('createNewNameId').value = '';
	document.getElementById('createNewTitleId').value = '';
	document.getElementById('createNewHeadingId').value = '';
}

function showSuccessOfCreateNew(result)
{
	var errorDivEle = document.getElementById('createNewErrorMsgDivId');
	var str = '';

	if(result.resultCode == 0)
	{
		str += '<font color="Green">Special Page Created SuccessFully</font>';
		clearFieldsOfCreateNewDiv();
	}
	else 
		str += '<font color="red">Special Page is Not Created, Try Again</font>';
	errorDivEle.innerHTML = str;

	clearFieldsOfCreateNewDiv();
}

function validateEmailField()
{
 document.getElementById("alertMsg").innerHTML = '';
 //var specialPageId = document.getElementById("specialPageId").value;
 var emailIdVal = document.getElementById("emailId").value;
 var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
 
 if(emailIdVal !='' && emailIdVal!='your email')
 {
	 if(!emailIdVal.match(emailExp))
	 {

		document.getElementById("alertMsg").innerHTML = '<font color="red">Please Enter Valid Email</font>';
		return;
	 }
 }
 else
 {
	document.getElementById("alertMsg").innerHTML ='<font color="red">Please Enter Valid Email</font>';  
	return;
 }

  var jsObj={
		emailId : emailIdVal,
		specialPageId :specialPageId,
		task:"setEmailAlertsForEvent"
	};

 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
 var url="specialEmailAlertsForUserAction.action?"+rparam;
 callAjaxForSpecialPage(jsObj,url);

}

function showStatusForEmailSubscription(results)
{
	var str='';
	if(results.resultCode == 0)
	{
		if(results.exceptionMsg != null && results.exceptionMsg != '')
		{
			str+='<span style="font-size: 13px; font-family: trebuchet MS;color:red">'+results.exceptionMsg+'</span>';
			
		}
		else
		{
			str+='<span style="font-size: 13px; font-family: trebuchet MS;color:green">You are Subscribed For Email alerts Successfully</span>';
		}
	}
	else
	{
		 str+='<span style="font-size: 13px; font-family: trebuchet MS;color:red">Unable To Process,Please Try Later</span>';
	}
		clearEmailFields();
		document.getElementById("alertMsg").innerHTML='<font color="green">You are Subscribed For Email alerts Successfully</font>';
		document.getElementById("alertMsg").innerHTML = str;
		document.getElementById("emailId").value='';
}

function clearEmailFields(){
	
	document.getElementById('emailId').value = ' ';

}

function getLocations(id){
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
  str +='	   <td class="tdWidth1" style="width: 88px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd" style="padding-top: 8px;"><select id="stateDiv" name="locationValue" style="width:175px;  margin-left: -6px;"/></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
  }
  else if(id==3)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1" style="width: 88px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="stateDiv" class="selectWidth"  onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1" style="width: 88px;">District :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="districtDiv" class="selectWidth" name="locationValue" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
  }
  else if(id==4)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1" style="width: 88px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="stateDiv" class="selectWidth"  onchange="clearAllElmts(4,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1" style="width: 88px;">District :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="districtDiv" class="selectWidth"  onchange="clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1" style="width: 88px;padding-top: 8px;">Assembly Constituency :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="constituencyDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
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
  str +='	   <td class="tdWidth" style="width: 88px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="stateDiv"   class="selectWidth" onchange="clearAllElmts(5,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 88px;">District :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="districtDiv"  class="selectWidth" onchange="clearAllElmts(5,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 88px;padding-top: 8px;">Assembly Constituency :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="constituencyDiv"  class="selectWidth" onchange="clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 88px;padding-top: 5px;">Mandal/ Municipality/ Corp/GMC :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="mandalDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
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
  str +='	   <td class="tdWidth" style="width: 88px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="stateDiv"  class="selectWidth" onchange="clearAllElmts(6,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 88px;">District :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="districtDiv"  class="selectWidth" onchange="clearAllElmts(6,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 88px;padding-top: 8px;">Assembly Constituency :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="constituencyDiv"  class="selectWidth" onchange="clearAllElmts(6,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 88px;padding-top: 5px;">Mandal/ Municipality/ Corp/GMC :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="mandalDiv"  class="selectWidth" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 85px;padding-top: 8px;">Village/Ward/Division :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="villageDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
  }
  else if(id==9)
  {
     var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 88px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="stateDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 88px;">District :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="districtDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 88px;padding-top: 8px;">Assembly Constituency :<font class="requiredFont">*</font></td>';
  str +='	   <td style="padding-top: 8px;"><select id="constituencyDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 88px;padding-top: 5px;">Mandal/ Municipality/ Corp/GMC :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="mandalDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'boothsInTehsilOrMunicipality\',\'\',document.getElementById(\'constituencyDiv\').options[document.getElementById(\'constituencyDiv\').selectedIndex].value)"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 88px;padding-top: 8px;">Village/Ward/Division :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="villageDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
  }
}

function getStatesForSpecialPage()
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

function buildResults(results,divId){
  var elmt = document.getElementById(divId);
         if(divId=='scopeDiv')
		 {
		    var option1 = document.createElement('option');
		 option1.value= 0;
		option1.text= "Select Scope";
		 }
		 else
		 {
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
		for(var i in results)
	  {
		var option = document.createElement('option');
		if(divId =="scopeDiv")
		  {
		  option.value=results[i].locationScope;
		  option.text=results[i].locationScopeValue;
		  }
		else if(divId =="stateDiv" || divId =="districtDiv")
		{
		  option.value=results[i].ids;
		  option.text=results[i].names;
		}
		else 
		{
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

 function getAllDetails(id,task,areaType,constId)
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
		constId:constId
	};
 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
 var url = "locationsHierarchiesAjaxAction.action?"+rparam;						
 callAjax(jsObj,url);
}

function buildMetaInfoDiv()
{
document.getElementById('profileManagementMainOuterDiv6').style.display = 'none' ;
document.getElementById('profileManagementHeaderDiv1').style.display = 'block' ;
	if(document.getElementById('descriptionDiv').style.display = 'block')
		document.getElementById('descriptionDiv').style.display = 'none' ;

	if(document.getElementById('newsGallaryDiv').style.display = 'block')
	document.getElementById('newsGallaryDiv').style.display = 'none';

	if(document.getElementById("videoGallaryDiv").style.display = 'block')
		document.getElementById("videoGallaryDiv").style.display = 'none' ;

	if(document.getElementById("createNewDiv").style.display = 'block')
		document.getElementById("createNewDiv").style.display = 'none' ;

	if(document.getElementById("metaInfoDiv").style.display = 'none')
		document.getElementById("metaInfoDiv").style.display = 'block' ;

	if(document.getElementById("photoGallaryDiv").style.display = 'block')
		document.getElementById("photoGallaryDiv").style.display = 'none' ;

	if(document.getElementById("specialPageInfoDiv").style.display = 'block')
		document.getElementById("specialPageInfoDiv").style.display = 'none' ;

	if(document.getElementById('specialPageHighLightsDiv').style.display = 'block')
	   document.getElementById('specialPageHighLightsDiv').style.display = 'none' ;

$("#createNewTabId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#developmentGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
$("#profileGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageInfoId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageHighLightId").css({"background":"none repeat scroll 0 0 #0063DC"});


	document.getElementById("headingnames").innerHTML = "Meta Info";
	var metaInfoDivElmt = document.getElementById("metaInfoDiv");
	var str ='';
	str +='<div id="content" align="center" style="width:650px;">';
		
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Add Meta Information</h2>';
	
	str += '<div id="metaInfoInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	
	str += '<table align="left" class="paddingCss"><tr><td><div id="metaInfoErrorMsgDivId" style="margin-left:5px;"></div></td></tr></table>';

	str += '<table width="95%">';
	str += '<tr><td><b><font color="#4B74C6">Meta Keywords<font class="requiredFont">*</font></font></b></td><td><textarea id="metaKeywordsId" cols="25" rows="5" name="requirement"></textarea></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Meta Description<font class="requiredFont">*</font></font></b></td><td><textarea id="metaDescriptionId" cols="25" rows="5" name="requirement"></textarea></td></tr>';

	str += '<table><tr><td style="padding-right:10px"><input type="button" class="imageButton" value="Add Meta Info" style="background-color:#57B731" onClick="addMetaInfo()"></td><td style="padding-right: 10px"><input type="button" class="imageButton" value="Cancel" onclick="clearFieldsOfMetaInfoDiv()" style="background-color:#CF4740"></td></tr></table>';
	
	str += '</fieldset>';
	str +='</div>';
	
	metaInfoDivElmt.innerHTML = str;
}

function addMetaInfo()
{
	var keywords	= document.getElementById('metaKeywordsId').value;
	var description	= document.getElementById('metaDescriptionId').value;
	var specialPageId = document.getElementById("specialPageId").value;
	var errorDivEle = document.getElementById('metaInfoErrorMsgDivId');
	var eFlag = false;

	var str = '<font color="red">';

	if(specialPageId.length == 0)
	{
		str += 'Special Page Id Required<br>';
		eFlag = true;
	}
	if(keywords.length == 0)
	{
		str += 'Meta Keywords are Required<br>';
		eFlag = true;
	}
	if(description.length == 0)
	{
		str += 'Meta Description Required<br>';
		eFlag = true;
	}
		
	str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;
	
	var jsObj =
		{ 
            keywords	: keywords,
		    description	: description,
			specialPageId : specialPageId,
			task		: "addMetaInformation"
		};


	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewEventGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function showSuccessOfAddMetaInfo(result)
{
	var errorDivEle = document.getElementById('metaInfoErrorMsgDivId');
	var str = '';

	if(result.resultCode == 0)
	{
		str += '<font color="Green">Meta Information Added SuccessFully</font>';
		clearFieldsOfMetaInfoDiv();
	}
	else 
		str += '<font color="red">Meta Information Not Added, Try Again</font>';
	errorDivEle.innerHTML = str;
}

function clearFieldsOfMetaInfoDiv()
{
	document.getElementById('metaKeywordsId').value = '';
	document.getElementById('metaDescriptionId').value = '';
}
function buildPhotoGallery()
{
	var photoGallaryDivEle = document.getElementById("photoGallaryDiv");
	var str ='';
    str +='<div id="content" align="center" style="width:650px;">';
	str +='<table style="margin-top:10px;">';
	str +='   <tr>';
	str +='      <td><input type="button" class="imageButton" value="Create Gallery" onclick="buildPhotoGalleryDiv()"/></td>';
	str +='      <td><input type="button" class="imageButton" value="Upload Photos" onclick="buildUploadPhotosDiv()"/></td>';
	str +='   </tr>';
	str +='</table>';
	str +='<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
    str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDiv" style="margin-left: 50px;"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="pGallaryNameId" size="25" maxlength="200"></td></tr>';
    str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<div style="padding-right: 63px"><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">&nbsp;&nbsp;Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 78px"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">&nbsp;&nbsp;Make This Private</font></b></input></div>';
	str += '<table><tr><td style="padding-right: 5px;"><input type="button" class="imageButton" value="Create Gallery" style="background-color:#57B731" onClick="createGallary(\'Photo Gallary\',\'Create\')"></td><td style="padding-right: 49px;"><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'photoGallaryDiv\')" style="background-color:#CF4740"></td></tr></table>';
	str +='</fieldset>';
	str+='</div>';
	photoGallaryDivEle.innerHTML = str;

}
function clearDiv(photoGallaryDiv)
{
	var photoGallaryDiv1 =document.getElementById("photoGallaryDiv");
     photoGallaryDiv1.innerHTML = "";
}

function buildPhotoGalleryDiv()
{
	var photoGallaryDivEle = document.getElementById("photoGallaryDiv");
	var str ='';
    str +='<div id="content" align="center" style="width:650px;">';
	str +='<table style="margin-top:10px;">';
	str +='   <tr>';
	str +='      <td><input type="button" class="imageButton" value="Create Gallery" onclick="buildPhotoGalleryDiv()"/></td>';
	str +='      <td><input type="button" class="imageButton" value="Upload Photos" onclick="buildUploadPhotosDiv()"/></td>';
	str +='   </tr>';
	str +='</table>';
	str +='<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
    str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDiv" style="margin-left: 50px;"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="pGallaryNameId" size="25" maxlength="200"></td></tr>';
    str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<div style="padding-right: 63px"><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">&nbsp;&nbsp;Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 78px"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">&nbsp;&nbsp;Make This Private</font></b></input></div>';
	str += '<table><tr><td style="padding-right: 5px;"><input type="button" class="imageButton" value="Create Gallery" style="background-color:#57B731" onClick="createGallary(\'Photo Gallary\',\'Create\')"></td><td style="padding-right: 49px;"><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'photoGallaryDiv\')" style="background-color:#CF4740"></td></tr></table>';
	str +='</fieldset>';
	str+='</div>';
	photoGallaryDivEle.innerHTML = str;
}
function buildUploadPhotosDiv()
{
	var tempSpecialPageId = document.getElementById("specialPageId").value;
	var str ='';
	str+='<div id="content" align="center" style="width:650px;">';
		
	str += '<table style="margin-top:10px;">';
	str += '<tr>';
	str += '	<td><input type="button" class="imageButton" value="Create Gallery" onclick="buildPhotoGalleryDiv()"></td>';
	str += '	<td><input type="button" class="imageButton" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
	str += '</tr>';
	str += '</table>';
	
	str += '<form name="uploadForm" action="uploadFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadFilesForm">';

	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Upload A Photo</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="fileUploadErrorMsgDivId" style="margin-left: 30px;"></div></td></tr></table>';
	str += '<table width="75%">';
	str += '<tr><td><b><font color="#4B74C6">Select Gallery</font></b></td><td><select id="gallarySelectId" name="gallaryId" style="width:175px;"><option value="0">Select</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Photo Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="fileTitleId" name="fileTitle" size="25" maxlength="200"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="fileDescId" name="fileDescription" cols="19" rows="3" name="requirement"></textarea></td></tr>';
	str +='<tr><td><b><font color="#4B74C6">File Path<font class="requiredFont">*</font></font><b></td><td><input type="file" name="userImage" id="fileId"/></td>';
	str +='<td class="selectWidthPadd"><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMorePhotos()" title="Click here to add more Photos" alt=""Click here to add more images""/></td></tr>';
	str +='<tr><td colspan="3"><div id="addMorePhotosDiv"></div></td></tr>';
	str += '   <TR>';
	str += '     <td colspan="2">';
	str += '<table>';
	str += '   <tr>';
	str += '     <td class="tdWidth2" style="width:68px;"><b><font color="#4B74C6">File Date<font class="requiredFont">*</font></font></b></td>';
	str += '     <TD class="selectWidthPadd"><input type="text" id="existingFromTextPhoto" class="dateField" readonly="true" name="fileDate" size="25"/>';
	str += '         <DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '     <TD>';
	str += '       <A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	//str += '       <IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '     </TD>';
	str += '   </tr>';
	str += '</table>';
	str += '     </td>';
	str += '   </TR></table>';
	str += '<div style="padding-right: 113px;"><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">&nbsp;&nbsp;Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 127px;"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">&nbsp;&nbsp;Make This Private</font></b></input></div>';

	str +='<input type="hidden" name="profileType" value="special_page_profile">';
	str +='<input type="hidden" name="profileId" value="'+tempSpecialPageId+'">';
	str +='<input type="hidden" name="profileGalleryType" value="photo_gallery">';
	str+='<input type="radio" onclick="otherProfiles(\'otherProPhotoDiv\',\'fromSpecialPage\',\'Photo Gallary\')"/>    Do you want to upload this file to other profiles';
	str+='<div id="otherProPhotoDiv" style="margin: 10px;"></div>';
	str += '<table style="margin-top: 31px;"><tr><td style="padding-right: 5px;"><input type="button" id="uploadPhotoId" class="imageButton" value="Upload Photo" style="background-color:#57B731" onClick="uploadAFile()"></td><td style="padding-right: 41px;"><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'photoGallaryDiv\')"  style="background-color:#CF4740"></td></tr></table>';
	
	str += '</form>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("photoGallaryDiv").innerHTML = str;
	if(document.getElementById("gallarySelectId")!=null)
      getPhotoGallariesForUpload("Photo Gallary");
}
function otherProfiles(divElmt,requestFrom,contentType)
{
	var otherProDivElmt = document.getElementById(divElmt);
	var str = '';
	$("#otherProPhotoDiv").html('');
	$("#otherProVideoDiv").html('');
	$("#otherProNewsDiv").html('');
	if(requestFrom == 'fromPartyProfile')
	{
		str+= '<span style="font-weight: bold; font-size: 13px;"><input type="checkbox" id="spcheckboxId" name="spcheckbox" onclick="getSpecialPages(\'spSelectId\')">   Special Pages</span>';
		str+= '<span style="float: right;"><input class="imageButton" style="height:24px;" type="button" value="Add More Special Pages" onclick="addMoreSpecialPage(\''+contentType+'\')"></span>';
		str+= '<div id="spOptionDiv"></div><div id="spSelectionDiv"><select id="spSelectId" style="display:none;width: 250px; margin: 10px;" onchange="getGallaries(this.options[this.selectedIndex].value,\''+contentType+'\',\'SpecialPage\',\'uploadSpecialPageGalleryId\');"></select></div>';
		str+= '<div id="spGallerySelectDiv"><select id="uploadSpecialPageGalleryId" name="uploadSpecialPageGalleryId" style="display:none;width: 250px; margin: 10px;" onchange="showAddedGallery(\'spOptionDiv\',\'spSelectId\',this.id)"></select></div>';
		str+= '<div id="addMoreSPDiv"></div>';
		str+= '<div style="font-weight: bold; font-size: 13px; margin-top: 15px; margin-right: 95px;"><input type="checkbox" id="ccheckboxId" name="ccheckbox" onclick="getCandidates(\'candidateSelectId\')">   Candidate Profile Page</div>';
		str+= '<span style="float: right; margin-top: -17px;"><input class="imageButton" type="button" value="Add More Candidates" onclick="addMoreCandidates(\''+contentType+'\')"></span>';
		str+= '<div id="canOptionDiv"></div><div id="candidateSelectionDiv"><select id="candidateSelectId" style="display:none;width: 250px; margin: 10px;" onchange="getGallaries(this.options[this.selectedIndex].value,\''+contentType+'\',\'Candidate\',\'uploadCandidateGalleryId\');"></select></div>';
		str+= '<div id="candidateGallerySelectDiv"><select id="uploadCandidateGalleryId" name="uploadCandidateGalleryId" style="width: 250px; margin: 10px;display:none;" onchange="showAddedGallery(\'canOptionDiv\',\'candidateSelectId\',this.id)"></select></div>';
		//str+= '<div id="nogalleryDiv"></div>';
		str+= '<div id="addMoreCandidatesDiv"></div>';
		//str+= '<div id="galleryPopupDiv"><div id="galleryInnerPopupDiv"></div></div>';
	}
	else if(requestFrom == 'fromSpecialPage')
	{
		str+= '<span style="font-weight: bold; font-size: 13px; margin-right: 94px;"><input type="checkbox" id="ccheckboxId" name="ccheckbox" onclick="getCandidates(\'candidateSelectId\')">   Candidate Profile Page</span>';
		str+= '<span style="float: right; margin-top: -17px;"><input class="imageButton" type="button" value="Add More Candidates" onclick="addMoreCandidates(\''+contentType+'\')"></span>';
		str+= '<div id="canOptionDiv"></div><div id="candidateSelectionDiv"><select id="candidateSelectId" style="display:none;width: 250px; margin: 10px;" onchange="getGallaries(this.options[this.selectedIndex].value,\''+contentType+'\',\'Candidate\',\'uploadCandidateGalleryId\');"></select></div>';
		str+= '<div id="candidateGallerySelectDiv"><select id="uploadCandidateGalleryId" name="uploadCandidateGalleryId" style="display:none;width: 250px; margin: 10px;" onchange="showAddedGallery(\'canOptionDiv\',\'candidateSelectId\',this.id)"></select></div>';
		str+= '<div id="addMoreCandidatesDiv"></div>';
		str+= '<div style="font-weight: bold; font-size: 13px; margin-top: 15px; margin-right: 127px;"><input type="checkbox" id="pcheckboxId" name="pcheckbox" onclick="getParties(\'partySelectId\')">   Party Profile Page</div>';
		str+= '<span style="float: right; margin-top: -17px;"><input class="imageButton" style="height:24px;" type="button" value="Add More Parties" onclick="addMoreParties(\''+contentType+'\')"></span>';
		str+= '<div id="partyOptionDiv"></div><div id="partySelectionDiv"><select id="partySelectId" style="display:none;width: 250px; margin: 10px;" onchange="getGallaries(this.options[this.selectedIndex].value,\''+contentType+'\',\'Party\',\'uploadPartyGalleryId\');"></select></div>';
		str+= '<div id="partyGallerySelectDiv"><select id="uploadPartyGalleryId" name="uploadPartyGalleryId" style="display:none;width: 250px; margin: 10px;" onchange="showAddedGallery(\'partyOptionDiv\',\'partySelectId\',this.id)"></select></div>';
		str+= '<div id="addMorePartiesDiv"></div>';
		
	}
	else if(requestFrom == 'fromCandidateProfile')
	{
		str+= '<span style="font-weight: bold; font-size: 13px;"><input type="checkbox" id="spcheckboxId" name="spcheckbox" onclick="getSpecialPages(\'spSelectId\')">   Special Page</span>';
		str+= '<span style="float: right;"><input class="imageButton" style="height:24px;" type="button" value="Add More Special Pages" onclick="addMoreSpecialPage(\''+contentType+'\')"></span>';
		str+= '<div id="spOptionDiv"></div><div id="spSelectionDiv"><select id="spSelectId" style="display:none;width: 250px; margin: 10px;" onchange="getGallaries(this.options[this.selectedIndex].value,\''+contentType+'\',\'SpecialPage\',\'uploadSpecialPageGalleryId\');"></select></div>';
		str+= '<div id="spGallerySelectDiv"><select id="uploadSpecialPageGalleryId" name="uploadSpecialPageGalleryId" style="display:none;width: 250px; margin: 10px;" onchange="showAddedGallery(\'spOptionDiv\',\'spSelectId\',this.id)"></select></div>';
		str+= '<div id="addMoreSPDiv"></div>';
		str+= '<div style="font-weight: bold; font-size: 13px; margin-top: 15px; margin-right: 127px;"><input type="checkbox" id="pcheckboxId" name="pcheckbox" onclick="getParties(\'partySelectId\')">   Party Profile Page</div>';
	    str+= '<span style="float: right; margin-top: -17px;"><input class="imageButton" style="height:24px;" type="button" value="Add More Parties" onclick="addMoreParties(\''+contentType+'\')"></span>';
		str+= '<div id="partyOptionDiv"></div><div id="partySelectionDiv"><select id="partySelectId" style="display:none;width: 250px; margin: 10px;" onchange="getGallaries(this.options[this.selectedIndex].value,\''+contentType+'\',\'Party\',\'uploadPartyGalleryId\');"></select></div>';
		str+= '<div id="partyGallerySelectDiv"><select id="uploadPartyGalleryId" name="uploadPartyGalleryId" style="display:none;width: 250px; margin: 10px;" onchange="showAddedGallery(\'partyOptionDiv\',\'partySelectId\',this.id)"></select></div>';
		str+= '<div id="addMorePartiesDiv"></div>';
		//str+= '<div id="galleryInnerPopupDiv"></div>';
	}
	$(otherProDivElmt).html(str);


}
function getGallaries(selectedId,contentType,userOption,galSelectOPtionId)
{
	if(userOption == 'Party')
	{
		var jsObj = {
			galSelectOPtionId :galSelectOPtionId,
			userOption : userOption,
			partyId :selectedId,
			contentType : contentType,
			task :'partyGallariesForUplaod'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPartyGallariesForUplaodPhotoAction.action?"+rparam;
		callAjaxForMultiPagesUpload(jsObj,url);
	}
	if(userOption == 'Candidate')
	{
		var jsObj = {
			galSelectOPtionId :galSelectOPtionId,
			userOption : userOption,
			candidateId :selectedId,
			contentType : contentType,
			task : "candidateGallariesForUplaod"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getCandidateGallariesForUplaodAction.action?"+rparam;
		callAjaxForMultiPagesUpload(jsObj,url);
	}
	if(userOption == 'SpecialPage')
	{
		var jsObj =
		{ 
			galSelectOPtionId :galSelectOPtionId,
			userOption : userOption,
            specialPageId : selectedId,
			contentType : contentType,
		   	task : "photoGallariesForUplaod"
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getEventGallariesForUplaodPhotoAction.action?"+rparam;
		callAjaxForMultiPagesUpload(jsObj,url);
	}
	
}

function getParties(selectOptionId)
{
	var pcheckboxIdElmt = document.getElementById("pcheckboxId");
	if(pcheckboxIdElmt.checked)
	{
		var jsObj ={
			selectOptionId:selectOptionId,
			task :"getParties"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPartiesAjaxAction.action?"+rparam;
		callAjaxForMultiPagesUpload(jsObj,url);
	}
	else
	{
		document.getElementById("partySelectId").style.display ='none';
		document.getElementById("uploadPartyGalleryId").style.display ='none';
		$("#addMorePartiesDiv").html("");
	}

}
function getCandidates(selectOptionId)
{
	var ccheckboxIdElmt = document.getElementById("ccheckboxId");
	if(ccheckboxIdElmt.checked)
	{
		var jsObj ={
			selectOptionId :selectOptionId,
			task :"getCandidates"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getCandidatesAjaxAction.action?"+rparam;
		callAjaxForMultiPagesUpload(jsObj,url);
	}
	else
	{
		document.getElementById("candidateSelectId").style.display ='none';
		document.getElementById("uploadCandidateGalleryId").style.display ='none';
		$("#addMoreCandidatesDiv").html("");
	}
}
function getSpecialPages(selectOptionId)
{
	var spcheckboxIdElmt = document.getElementById("spcheckboxId");
	if(spcheckboxIdElmt.checked)
	{
		var jsObj ={
			selectOptionId:selectOptionId,
			task :"getSpecialPages"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getSpecialPagesAjaxAction.action?"+rparam;
		callAjaxForMultiPagesUpload(jsObj,url);
	}
	else
	{
		document.getElementById("spSelectId").style.display ='none';
		document.getElementById("uploadSpecialPageGalleryId").style.display ='none';
		$("#addMoreSPDiv").html('');
	}

}
function showAddedGallery(divElmt,selectedId,galleryId)
{
	var spOptionDivElmt = document.getElementById(divElmt);
	var selected = document.getElementById(selectedId).options[document.getElementById(selectedId).selectedIndex].text;
	var selectedVal = document.getElementById(selectedId).options[document.getElementById(selectedId).selectedIndex].value;
	var galleryName = document.getElementById(galleryId).options[document.getElementById(galleryId).selectedIndex].text;
	var deletId = '.noGalDiv'+galleryId+''+selectedVal+'';
	var str='';
	str+= '<div id="addedDiv'+divElmt+'" style="border-radius:5px;margin-top: 10px; padding: 4px; width: auto; font-weight: bold; color:#000; background: #e3e3e3;">File added to  <font style="color:Highlight;">'+selected+'</font> in <font style="color:Highlight;">'+galleryName+'</font> Gallery';
	str+='<span style="float: right;"><img src="images/icons/delete.png" onclick="deleteAddedGal(\'addedDiv'+divElmt+'\',\''+selectedId+'\',\''+galleryId+'\',\''+deletId+'\')"></span></div>';
	$(spOptionDivElmt).html(str);
	document.getElementById(selectedId).style.display='none';
	document.getElementById(galleryId).style.display='none';

}
function deleteAddedGal(divElmt,selectOptionId,galId,id)
{
	var confirmDelete = confirm('Do you want to added gallery');
	if(confirmDelete)
	{
	    $(id).remove();
		document.getElementById(divElmt).style.display = 'none';
		document.getElementById(selectOptionId).innerHTML = '';
		document.getElementById(galId).innerHTML = '';
	}

}
function getPhotoGallariesForUpload(contentType)
{
	var specialPageId=document.getElementById("specialPageId").value;
	var jsObj =
		{ 
            specialPageId : specialPageId,
			contentType : contentType,
		   	task : "photoGallariesForUplaod"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getEventGallariesForUplaodPhotoAction.action?"+rparam;
	callAjax(jsObj,url);

}
	

function uploadAFile()
{
	
	var uploadPhotoId = document.getElementById('uploadPhotoId');
	if(validateFileUpload())
	{
		disableButton('uploadPhotoId');

		 var privateRadioId = document.getElementById('privateRadioId').checked;
		if(privateRadioId == true)
	document.getElementById('privateRadioId').checked = 'true';
		if(privateRadioId == false)
	document.getElementById('publicRadioId').checked = 'true';

		var uploadHandler = {
				upload: function(o) {
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

function clearUploadFileFields()
{
	$("#addMorePhotosDiv").html('');
	$("#otherProPhotoDiv").html('');
	
	document.getElementById('fileTitleId').value='';
	document.getElementById('fileDescId').value='';
	document.getElementById('fileId').value='';
	document.getElementById('existingFromTextPhoto').value = '';
	
}

function validateFileUpload()
{
	var fileTitle = document.getElementById('fileTitleId').value;
	var fileDesc = document.getElementById('fileDescId').value;
	var fileVal = document.getElementById("fileId").value;
	var galId = document.getElementById("gallarySelectId").value;
	var canGalId = document.getElementById("uploadCandidateGalleryId");
	var candidateSelectId = document.getElementById("candidateSelectId");
	var ccheckboxIdElmt = document.getElementById("ccheckboxId");
	var partyGalId = document.getElementById("uploadPartyGalleryId");
	var partySelectId = document.getElementById("partySelectId");
	var pcheckboxIdElmt = document.getElementById("pcheckboxId");
	var fileDate = document.getElementById("existingFromTextPhoto").value;
	var flag = true;
	
	fileTitle = removeAllUnwantedCharacters(fileTitle);
	fileDesc = removeAllUnwantedCharacters(fileDesc);
	document.getElementById('fileTitleId').value = fileTitle;
	document.getElementById('fileDescId').value = fileDesc;

	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '<font color="red">';
	if(fileDate.length == 0)
	{
		str +='File Date is Required';
		flag = false;
	}

	if(fileTitle.length == 0)
	{
		str += 'Photo Title Required.<br>';
		flag = false;
	}
	if(fileTitle.length > 200)
	{
		str += 'Photo Title Should not exceed 200 Characters.<br><br>';
		flag = false;
	}
	if(fileDesc.length == 0)
	{
		str += 'Photo Description Required.<br>';
		flag = false;
	}
	if(fileDesc.length > 500)
	{
		str += 'Photo Description Should not exceed 500 Characters.<br>';
		flag = false;
	}
	if(fileVal.length == 0)
	{
		str += 'Photo Required.<br>';
		flag = false;
	}
	if(galId == 0)
	{
		str += 'Select Any Gallery.<br>';
		flag = false;
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
	if(pcheckboxIdElmt!=null && pcheckboxIdElmt.checked)
	{
	 
	  if(partySelectId !=null && partySelectId.value == 0)
	  {
		str += 'Select Party <br>';
		flag = false;
	  }
     else if(partyGalId !=null && partyGalId.value == 0)
	  {
		str += 'Select Party Gallery.<br>';
		flag = false;
	  }
	  
	}
	str += '</font>';
	errorDivEle.innerHTML = str;

	return flag;
}


function showGallaryCreateMsg(result,createOrUpdate)
{
	var errorDivEle = document.getElementById('galErrorMsgDiv');
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
function clearGallaryFields()
{
	document.getElementById('pGallaryNameId').value = '';
	document.getElementById('pGallaryDescId').value = '';
	document.getElementById('publicRadioId').checked = true;
}
function clearPhotoGallaryFields()
{
   document.getElementById('pGallaryDescId').value = '';
   document.getElementById('pGallaryNameId').value = '';
}
//update photo gallery
function showPhotoGallery()
{
        
	        document.getElementById('descriptionDiv').style.display = 'none' ;

	    
	        document.getElementById('newsGallaryDiv').style.display = 'none';

	   
		    document.getElementById("videoGallaryDiv").style.display = 'none' ;
   
	    
		    document.getElementById("createNewDiv").style.display = 'none' ;

	    
		    document.getElementById("metaInfoDiv").style.display = 'none' ;

			document.getElementById("specialPageInfoDiv").style.display = 'none' ;

			
		   document.getElementById('specialPageHighLightsDiv').style.display = 'none' ;

		    document.getElementById("photoGallaryDiv").style.display = 'block' ;

			document.getElementById("headingnames").innerHTML = "Photo Gallery";

$("#createNewTabId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#developmentGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#profileGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageInfoId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageHighLightId").css({"background":"none repeat scroll 0 0 #0063DC"});


	var specialPageId = document.getElementById("specialPageId").value;
    document.getElementById("profileManagementMainOuterDiv1").style.display = 'block';
    document.getElementById("profileManagementMainOuterDiv6").style.display = 'none';
    var jsObj =
		{   
		    time : timeST,
			specialPageId:specialPageId,
			startRecord:0,
			maxRecord:100,
			task:"getspaecialPagePhotoGallaryDetails"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "showPhotoGalleryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function buildCandidatePhotoGallary(results)
{
	var str ='';

		str+='<div id="content" align="center" style="width:650px;">';
		
		str += '<table style="margin-top:10px;">';
		str += '<tr>';
		str += '	<td><input type="button" class="imageButton" value="Create Gallery" onclick="buildPhotoGallery()"></td>';
		str += '	<td><input type="button" class="imageButton" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
		str += '</tr>';
		str += '</table>'
        str += '<center><div id="fileUploadErrorMsgDivId"></div></center>'; 
		str += '<fieldset class="imgFieldset">';
		str +='<table width="100%" style="margin-top:10px;">';
	if(results ==null)
	{
		str+='<b>&nbsp;No Photo Galleries Found </b>';
	}
	else
	{
		
		for(var i in results)
		{
			no_of_imagesPerRow = 3; 
			j = i;

			if(j++ % no_of_imagesPerRow == 0)
				str += '<tr style="height:220px;">';
			
			str += '<td width="33%" class="imageStyle">';
			str += '<table class="tableStyle">';
			str += '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].gallaryName+'</b></font></div></td></tr>';
			if(results[i].path!=null)
			{
			 str += '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img src="'+results[i].path+'" class="gallaryImg" onclick="getCompleteGallaries(\''+results[i].gallaryId+'\')"/></a></td></tr>';
			}
			else
			{
			  str += '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img src="images/icons/DefaultPhotoGalleryImage.jpg" class="gallaryImg" onclick="getCompleteGallaries(\''+results[i].gallaryId+'\')"/></a></td></tr>';
			}
			str += '<tr><td><div><b>'+results[i].gallaryDescription+'</b></div></td></tr>';
			str+= '<tr><td><div><b>Gallery Size: ('+results[i].sizeOfGallary+')</b></div></td></tr>';
			str +='<tr>';
			str +='<table><tr>';
			str +='<td> <input type="button" class="buttonStyle" style="background: none repeat scroll 0 0 #0063dc;" value="Update" id= "updateGallary_'+i+'" onclick="updateGallary('+results[i].gallaryId+')"></td>';
			str +='<td style="padding-right:10px;"><input type="button" class="buttonStyle" style="background: none repeat scroll 0 0 #F61D50;" value="Delete" id= "deleteGallary_'+i+'" onclick="deleteGallary('+results[i].gallaryId+')"></td></tr>';
			str +='</tr>';
			str += '</table>';
			str += '</td>';
			
			if(j % no_of_imagesPerRow == 0)
				str+= '</tr>';
		
		}
	}
		str += ' </table>';
		str += ' </fieldset>';
		str+='</div>';
		
		document.getElementById("photoGallaryDiv").innerHTML = str;
	
}
function getCompleteGallaries(gallaryId){
    gGallaryId=gallaryId;
    var jsObj =
		{ 
            time : timeST,
			value:"old",
		    gallaryId:gallaryId,
			task:"getPhotosInAGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjaxForSpecialPage(jsObj,url);
}
function showPhotosInAGallary(results){
   var str ='';
   str+='<div id="content" style="width:650px;">';
   str += '<fieldset class="imgFieldset">';
   str+='<table width="100%" style="margin-top:10px;">'
   str+='<tr><td>';
   str+='<input type="button" value="Back To Gallery"  class="imageButton" onclick="showPhotoGallary();" />';
   str+= '</td></tr>';
   count = 0;
   for(var i in results)
   {
    for(var k in results[i].filePath)
	{
     no_of_imagesPerRow = 3; 
     j = count;
     if(j++ % no_of_imagesPerRow == 0){
       str+= '<tr style="height:220px;">';
     }
     str+= '<td width="33%" class="imageStyle"><table class="tableStyle">';
	 str += '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].fileTitle1+'</b></font></div></td></tr>';
     str+= '<tr><td><a rel="photo_gallery" href="'+results[i].filePath[k]+'" title="'+results[i].fileTitle1+'"><img alt="" src="'+results[i].filePath[k]+'" class="gallaryImg" height="100px" /></a></td></tr>';
	 str += '<tr><td><div><b>'+results[i].fileDescription1+'</b></div></td></tr>';
     str+= '<tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';
     if(j % no_of_imagesPerRow == 0){
       str+= '</tr>';
     }
    count++;
   }
  }
   str+= ' </table>';
   str += ' </fieldset>';
   str+='</div>';
   document.getElementById("buildPhotoGallaryDiv").innerHTML = str;

   $("a[rel=photo_gallery]").fancybox({
     'transitionIn'		: 'none',
     'transitionOut'		: 'none',
     'titlePosition' 	: 'over',
     'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
        return '<div id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; <span>' + title : '') + '</span></div>';
     }
   });
}

function updateGallary(gallaryId)
 {
 var r=confirm("Do you want to update!");
 if (r==true)
    {
	var specialPageId=document.getElementById("specialPageId").value;
	var jsObj =
		{ 
     		gallaryId : gallaryId,
			specialPageId : specialPageId,
		   	task : "specialPageUpdateGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "specialPagePhotoGallaryUpdateAction.action?"+rparam;
	callAjax(jsObj,url);	
	}
 
 }

 function updateGallaryDiv(myResults)
 {
    var str ='';
	document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
	document.getElementById("profileManagementMainOuterDiv6").style.display = 'block';
	
	str+='<div id="content" align="center" style="width:650px;">';
	str += '<fieldset class="imgFieldset" style="width:440px;margin-top:25px;">';
	str += '<h2 align="center">Update A Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDiv" style="margin-left: 50px;"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont"> * </font></font></b></td><td><input type="text" id="pGallaryNameId" size="25" maxlength="100"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pGallaryDescId" cols="19" rows="3" name="requirement">'+myResults.gallaryDescription+'</textarea></td></tr></table>';
	str +='<input type = "hidden" name = gallaryId id = gallaryId value ='+myResults.gallaryId+'>';
	str += '<div style="padding-left: 14px; padding-right: 120px; "><input type="radio" value="public" name="visibility" id="publicRadioId"><b><font color="#4B74C6">&nbsp;&nbsp;Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 123px;"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">&nbsp;&nbsp;Make This Private</font></b></input></div>';
	str += '<table><tr><td style="padding-right: 5px;"><input type="button" class="imageButton" value="Update Gallery" style="background-color:#57B731" onClick="createGallary(\'Photo Gallary\',\'Update\')"></td><td style="padding-right: 49px;"><input type="button" class="imageButton" value="Cancel" onclick="showPhotoGallery()" style="background-color:#CF4740"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("updateGallaryDiv").innerHTML = str;
	document.getElementById("pGallaryNameId").value=myResults.gallaryName;
	if(myResults.fileName1 != 'false')
	document.getElementById("privateRadioId").checked= true;
	else
	document.getElementById("publicRadioId").checked= true;
 }
 function createGallary(contentType,createOrUpdate)
 {
	var galName = document.getElementById('pGallaryNameId').value;
	var galDesc = document.getElementById('pGallaryDescId').value;
	var isPublic = document.getElementById('publicRadioId').checked;
	var specialPageId = document.getElementById("specialPageId").value; 	
	var makeThis = 'true';
    var gallaryId ='';
	var errorDivEle = document.getElementById('galErrorMsgDiv');
	var eFlag = false;

	galName = removeAllUnwantedCharacters(galName);
	galDesc = removeAllUnwantedCharacters(galDesc);

	document.getElementById('pGallaryNameId').value = galName;
	document.getElementById('pGallaryDescId').value = galDesc;
		
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
	if(galName.length >200)
	{
		str += 'Gallery Name should be less than 200 Characters<br>';
		eFlag = true;
	}
	if(galDesc.length > 500)
	{
		str += 'Description should be less than 500 Characters<br>';
		eFlag = true;
	}
	
	str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;

	if(isPublic)
		makeThis = 'false';
	
	var jsObj =
		{ 
            name : galName,
		    desc : galDesc,
			visibility : makeThis,
			specialPageId : specialPageId,
			contentType : contentType,
			gallaryId : gallaryId,
			createOrUpdate:createOrUpdate,
			task : "createphotoGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createPhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function deleteGallary(gallaryId)
{
var r=confirm("Do you want to delete!");
 if (r==true)
 {
 var jsObj =
		{ 
     		gallaryId : gallaryId,
		   	task : "deleteGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
	}
}
function showDeleteGallary(myResult)   
{
	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
		var str = '';

		if(myResult.resultCode == 0)
		{
			showPhotoGallery();
			str += '<font color="green"><b>Gallary Deleted Successfully.</b>';
		}
		else if(myResult.resultCode == 1) 
		{
			str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
		}
		
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
		str += '<tr><td><b><font color="#4B74C6">Select Gallery</font></b></td><td><select id="gallarySelectId" class="gallaryTitleVal" name="gallaryId" style="width:175px;"></select></td></tr>';
		str += '<tr><td><b><font color="#4B74C6">Photo Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="fileTitleId" name="fileTitle" size="25" maxlength="200"></td></tr>';
		str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
		str += '<td><textarea id="fileDescId" name="fileDescription" cols="19" rows="3" name="requirement">'+myResults.fileDescription1+'</textarea></td></tr></table>';
		str += '<div style="padding-right: 113px;"><input type="radio" value="public" name="visibility" id="publicRadioId"><b><font color="#4B74C6">&nbsp;&nbsp;Visible to Public Also</font></b></input></div>';
		str += '<div style="padding-right: 127px;"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">&nbsp;&nbsp;Make This Private</font></b></input></div>';
		str += '<table><tr><td style="padding-right: 40px;"><input type="button" class="imageButton" value="Update Photo" style="background-color:#57B731" onClick="updatePhoto('+fileId+','+myResults.fileTypeId+')"></td><td style="padding-right: 41px;"><input type="button" class="imageButton" value="Cancel" onclick="getCompleteGallaries(gGallaryId)"  style="background-color:#CF4740"></td></tr></table>';
		str += '</fieldset>';
		str+='</div>';
		document.getElementById("photoGallaryDiv").innerHTML = str;
		document.getElementById('fileTitleId').value = myResults.fileTitle1;
		if(myResults.file != 'false')
		document.getElementById('privateRadioId').checked = true;
		else 
		document.getElementById('publicRadioId').checked = true;
		getCandidateGallariesForUpdatePhoto();
}	 
function updatePhoto(fileId,fileGallaryId)
{ 
  var galEle = document.getElementById('gallarySelectId');
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
		str += 'Title Is  Required<br>';
		eFlag = true;
	}
	if(title.length >200)
	{
		str += 'Title should be less than 200 Characters<br>';
		eFlag = true;
	}
	if(galDesc.length == 0)
	{
		str += 'Description Is  Required<br>';
		eFlag = true;
	}
	if(galDesc.length > 500)
	{
		str += 'Description should be less than 500 Characters<br>';
		eFlag = true;
	}
	
	str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;

	if(isPublic)
		makeThis = 'false';
	
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
function showPhotoUpdateDetails(result)
{ 
  var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '';
	
	if(result.resultCode == 0)
	{
	   document.getElementById('fileTitleId').value='';
	   document.getElementById('fileDescId').value='';
		str += '<font color="green"><b>Photo Updated Successfully.</b>';
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

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
function getCandidateGallariesForUpdatePhoto()
{
var specialPageId=document.getElementById("specialPageId").value;
	var jsObj =
		{ 
			specialPageId : specialPageId,
			contentType :'Photo Gallary',
			updatePhoto:'UpdatePhoto',
		   	task : "photoGallariesForUpdataPhoto"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getEventGallariesForUplaodPhotoAction.action?"+rparam;
	callAjax(jsObj,url);
}
function checkUserLoginStatus(divId)
{
	document.getElementById(divId).style.display='block';
			var str='';
		$("#"+divId+"").dialog({ stack: false,
									height: 'auto',
									width: 500,
									position:'center',								
									modal: true,
									title:'<font color="#000">ALERT</font>',
									overlay: { opacity: 0.5, background: 'black'},
									
							});
		str+='<div class="popupcontainer"><h4><div style="margin: 10px;color:ActiveCaption;">Only registered users can view this content.</div></h4><h5 style="color:#000;display:inline;position:relative;top:0px;"><div style="margin: 10px;"> Already a member ,   Click here to <a style="color:red;" href="loginInputAction.action">Login</a></div><div style="margin-left:160px;">(OR)</div><div style="margin: 10px;margin-top:-20px;">Not a member, Click here for <a style="color: Red; width: 114px; height: 8px;" href="freeUserRegistration.action"> FREE REGISTRATION <span style="margin-bottom: 20px;"><img src="images/specialPage/freeUser.png"></span></a></div></h5></div>';
		document.getElementById(divId).innerHTML = str;
	
}




var clickid = null;
function copyId(id)
{
  clickid = id;
}
var custom_paginator = {
	resultsCount:"",
	startIndex:"",
	ajaxCallURL:"",
	callBackFunction:"",
	jsObj:{},
	results:{},
	totalRecords:"",
	paginatorElmt:"",		
	paginator:function(obj){
		this.startIndex = obj.startIndex;
		this.ajaxCallURL = obj.ajaxCallURL;
		this.jsObj = obj.jsObj;
		this.callBackFunction = obj.callBackFunction;
		this.paginatorElmt = obj.paginatorElmt;
		this.resultsCount = obj.resultsCount;		
	},	
	doAjaxCall:function(start){

		var url = this.ajaxCallURL+"&startIndex="+start+"&resultsCount="+this.resultsCount;
		
		var callback = {			
	    success : function( o ) {
			try 
			{				
				results = YAHOO.lang.JSON.parse(o.responseText);
				
				if(results != null && results.length>0)
				    this.totalRecords = results[0].count;	
				else
                     this.totalRecords = 0;
				    this.callBackFunction();
					this.buildPaginator();
				
			}
			catch (e)
			{   		
				//alert("Invalid JSON result" + e);   
			}  
		},
		scope : this,
		failure : function( o ) {
					//alert( "Failed to load result" + o.status + " " + o.statusText);
				  }
		};

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	},
	initialize:function (){		
		this.doAjaxCall(this.startIndex);
	},
	buildPaginator:function()
	{
		var paginatorElmt = document.createElement('Div');
		paginatorElmt.setAttribute("class","paginatorElmtClass");
		var iteration = Math.ceil(this.totalRecords/this.resultsCount);		
		var countIndex = this.startIndex;
		var str = '';

		if(iteration > 1)
		{
			for(var i=1; i<=iteration; i++)
			{			
				str += '<a href="javascript:{}" id="customPaginationId'+i+'" onclick="copyId(this.id);custom_paginator.doAjaxCall('+countIndex+')">'+i+'</a>';
				countIndex+=this.resultsCount;
			}
		}
		
		if(document.getElementById("custom_paginator_class")!=null)	
     	  document.getElementById("custom_paginator_class").innerHTML = str;
		if(clickid != null)
		{
		 $("#"+clickid).addClass('pagenationStyle');
		}
		else
		{
		  $("#customPaginationId1").addClass('pagenationStyle');
		}
	}
};

 
  $(document).ready(function() {
    $('#custom_paginator_class a').live("click",function() {
	   
        $('#custom_paginator_class a').removeClass('pagenationStyle');
		$(this).addClass('pagenationStyle');
      });
	
  });
var news_Obj = {
	
	startIndex:0,
	problemsCount:25,
	
	initialize:function(viewType){
		
		this.getProblemDetails(viewType);

	},
	getProblemDetails:function(viewType)
    {  	
        var jsObj=
		{		time : new Date().getTime(),
		        viewtype:viewType,
			    specialPageId:specialPageId,
				queryType:'Public',
				task:"getNewsToDisplayForPagination"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "specialPagePhotoGallaryAction.action?"+rparam;

	    custom_paginator.paginator({
			startIndex:this.startIndex,
			resultsCount:this.problemsCount,
			jsObj:jsObj,
			ajaxCallURL:url,
			paginatorElmt:"custom_paginator_class",
			callBackFunction:function(){
	    	showTotalNews(results);
			}
		});
		
		custom_paginator.initialize();
    }
	};
 function getCompleteNews(viewType)
 {
  clickid = null;
 news_Obj.initialize(viewType);
}

 


function buildSpecialPageInfoDiv(result)
{
var specialPageId = $("#specialPageId").val();
document.getElementById('profileManagementMainOuterDiv6').style.display = 'none' ;
document.getElementById('profileManagementHeaderDiv1').style.display = 'block' ;
	if(document.getElementById('descriptionDiv').style.display = 'block')
		document.getElementById('descriptionDiv').style.display = 'none';

	if(document.getElementById('newsGallaryDiv').style.display = 'block')
		document.getElementById("newsGallaryDiv").style.display = 'none';

	if(document.getElementById('createNewDiv').style.display = 'block')
		document.getElementById('createNewDiv').style.display = 'none';
	
	if(document.getElementById("videoGallaryDiv").style.display = 'block')
		document.getElementById("videoGallaryDiv").style.display = 'none';
	
    if(document.getElementById("metaInfoDiv").style.display = 'block')
		document.getElementById("metaInfoDiv").style.display = 'none' ;

	if(document.getElementById("photoGallaryDiv").style.display = 'block')
		document.getElementById("photoGallaryDiv").style.display = 'none' ;
	
	if(document.getElementById("specialPageInfoDiv").style.display = 'none')
		document.getElementById("specialPageInfoDiv").style.display = 'block';

	if(document.getElementById('specialPageHighLightsDiv').style.display = 'block')
	   document.getElementById('specialPageHighLightsDiv').style.display = 'none' ;
	
$("#createNewTabId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#developmentGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#profileGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageInfoId").css({"background":"none repeat scroll 0 0 #F61D50"});
$("#specialPageHighLightId").css({"background":"none repeat scroll 0 0 #0063DC"});


	var str ='';
	document.getElementById("headingnames").innerHTML = "Manage Special Page Information";
		
	var specialPageInfoDivElmt = document.getElementById("specialPageInfoDiv");

	if(result == null || result.length == 0)
	{
	str += '<form id="updateSpecialPageFormId" method="post" enctype="multipart/form-data" action="updateSpecialPageForm.action" name="specialPageForm">';
	str +='<div id="content" align="center" style="width:650px;">';
		
	str += '<fieldset class="imgFieldset" style="width:540px;">';
	str += '<h2 align="center">Update A Special Page</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="specialPageInfoErrorMsgDivId"  style="margin-bottom: 5px;margin-left:50px;width:200px;"></div></td></tr></table>';
	str += '<table width="75%" style="margin: 0px 15px 10px 0px;">';

	str += '<tr><td><b><font color="#4B74C6">Title<font class="requiredFont">*</font></font></b></td><td><input name="title" type="text" id="specialPageTitle" size="25" maxlength="100"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="specialPageDescription" cols="19" rows="3" name="description"></textarea></td></tr></table>';
	str += '<tr><td><b><font color="#4B74C6">File Path<font class="requiredFont">*</font></font></td><td class="selectWidthPadd"><input type="file" name="userImage" id="specialPageImgPath" style="margin-left: 11px;" size="25" /></td></tr>';
	str += '<div id="specialPageImgDiv" style="margin-top:10px;"></div>';
	str +='<div style="padding-right: 63px; margin-top: 10px; margin-bottom: 10px;"><b><font color="#4B74C6">Display in Home Page</font></b> <input type="radio" value="Yes" name="specialPageVisibility" id="specialPagePublicRadioId" checked="true" class="specialPagePrivateRadioCls">&nbsp;&nbsp;Yes</input>';
	str += '<input type="radio" value="No" name="specialPageVisibility" id="specialPagePrivateRadioId" class="specialPagePrivateRadioCls">&nbsp;&nbsp;No</input></div>';

	str += '<table><tr><td style="padding-right:5px"><input type="button" id="specialPageButtonId" class="imageButton" value="Update" style="background-color:#57B731" onClick="createorUpdateSpecialPage()"></td><td style="padding-right: 10px"><input type="button" class="imageButton" value="Cancel" onclick="clearSpecialPageInfoDiv(\'specialPageInfoDiv\')" style="background-color:#CF4740"></td></tr></table>';
	str +='<input type="hidden" value="'+specialPageId+'" name="speciPageId"/>';
	str +='</form>';
	str += '</fieldset>';
	str +='</div>';

	}
	else if(result != null || result.length != 0)
	{
	for(i=0;i<result.length;i++)
	{
	
	
	str +='<div id="content" align="center" style="width:650px;">';
		
	str += '<fieldset class="imgFieldset" style="width:540px;">';
	str += '<form id="updateSpecialPageFormId" method="post" enctype="multipart/form-data" action="updateSpecialPageForm.action" name="specialPageForm">';
	str += '<h2 align="center">Update A Special Page</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="specialPageInfoErrorMsgDivId"  style="margin-bottom: 5px;margin-left:50px;width:200px;"></div></td></tr></table>';
	str += '<table width="75%" style="margin: 0px 15px 10px 0px;">';

	str += '<tr><td><b><font color="#4B74C6">Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="specialPageTitle" size="25" maxlength="100" value="'+result[i].title+'" name="title"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="specialPageDescription" cols="19" rows="3" name="description">'+result[i].description+'</textarea></td></tr></table>';
	str += '<tr><td><b><font color="#4B74C6" style="margin-left: -35px;">File Path<font class="requiredFont">*</font></font></td><td class="selectWidthPadd"><input type="file" name="userImage" id="specialPageImgPath" style="margin-left: 43px;" size="25" value="'+result[i].eventImagePath+'"/></td></tr>';
	str += '<div id="specialPageImgDiv" style="margin-top:10px;"><img src="'+result[i].eventImagePath+'" style="border: 2px solid #ccc; padding: 5px; border-radius: 3px; height: 100px; width: 130px;cursor:pointer;"/></div>';
	str += '<div style="padding-right: 63px; margin-top: 10px; margin-bottom: 10px;"><b><font color="#4B74C6">Display in Home Page</font></b>';
	str += '<input type="radio" value="Yes" name="specialPageVisibility" id="specialPagePublicRadioId" ';
	if(result[i].heading == "Yes")
		str +='checked="true"';
	str += 'class="specialPagePrivateRadioCls">Yes</input>';
	str += '<input type="radio" value="No" name="specialPageVisibility" id="specialPagePrivateRadioId" ';
	if(result[i].heading == "No")
		str +='checked="true"';
	str += 'class="specialPagePrivateRadioCls">No</input></div>';

	str += '<table><tr><td style="padding-right:5px"><input type="button" class="imageButton" value="Update" style="background-color:#57B731" onClick="createorUpdateSpecialPage()"></td><td style="padding-right: 10px"><input type="button" class="imageButton" value="Cancel" onclick="clearSpecialPageInfoDiv(\'specialPageInfoDiv\')" style="background-color:#CF4740"></td></tr></table>';
	str +=	'<input type="hidden" value="'+result[i].specialPageId+'" name="speciPageId"/>';
	str +='</form>';
	str += '</fieldset>';
	str +='</div>';
	}
	}
	specialPageInfoDivElmt.innerHTML = str;


}

function getSpecialPageInfo()
{
var specialPageId = $("#specialPageId").val();
	var jsObj =
		{ 
        	specialPageId : specialPageId,
			task : "getSpecialPageInfo"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSpecialPageInfoAction.action?"+rparam;						
	callAjax(jsObj,url);

}
function createorUpdateSpecialPage()
{
	
	var specialPageButtonId =document.getElementById('specialPageButtonId');
	var title = $("#specialPageTitle").val();
	var description = $("#specialPageDescription").val();
	var filePath = $("#specialPageImgPath").val();
	var visibility = $('[name=specialPageVisibility]:checked').val();
	var specialPageId = $("#specialPageId").val();
	var errorDivEle = document.getElementById('specialPageInfoErrorMsgDivId');
	 
	var eFlag = false;
    var str = '<font color="red">';
	if(title.length == 0 && description.length == 0)
	{
		str +="Title and Description is Required.";
		eFlag = true;
	}
	else if(title.length == 0)
	{
		str +='Title is Required.';
		eFlag = true;
	}
	else if(description.length == 0)
	{
		str +='Description is Required.';
		eFlag = true;
	}
	/*else if(filePath.length == 0)
	{
		str +='FilePath is Required.';
		eFlag = true;
	}*/
	str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;

		
			var specialPageHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					
					showSuccessMsgOfSpecialPageInfo(uploadResult);
					
				}
			};

		
		YAHOO.util.Connect.setForm('updateSpecialPageFormId',true);
		YAHOO.util.Connect.asyncRequest('POST','updateSpecialPageForm.action',specialPageHandler);
	
}



function showSuccessMsgOfSpecialPageInfo(result)
{
	var errorDivEle = document.getElementById('specialPageInfoErrorMsgDivId');
	
	var str = '';
		document.getElementById("specialPageTitle").value='';
		document.getElementById("specialPageDescription").value='';
		document.getElementById("specialPageImgPath").value='';
		document.getElementById("specialPageImgDiv").style.display = 'none';
		str += '<font color="green"><b>SpecialPage Updated Successfully.</b>';
		
	errorDivEle.innerHTML = str;
}

function clearSpecialPageInfoDiv(ele)
{
var divEle = document.getElementById(ele);
divEle.innerHTML = "";
}

function buildSpecialPageHighlightsDiv()
{
	document.getElementById('profileManagementMainOuterDiv6').style.display = 'none' ;
	document.getElementById('profileManagementHeaderDiv1').style.display = 'block' ;
	if(document.getElementById('descriptionDiv').style.display = 'none')
		document.getElementById('descriptionDiv').style.display = 'block';

	if(document.getElementById('newsGallaryDiv').style.display = 'block')
		document.getElementById("newsGallaryDiv").style.display = 'none';

	if(document.getElementById('createNewDiv').style.display = 'block')
		document.getElementById('createNewDiv').style.display = 'none';
	
	if(document.getElementById("videoGallaryDiv").style.display = 'block')
		document.getElementById("videoGallaryDiv").style.display = 'none';
	
    if(document.getElementById("metaInfoDiv").style.display = 'block')
		document.getElementById("metaInfoDiv").style.display = 'none' ;

	if(document.getElementById("photoGallaryDiv").style.display = 'block')
		document.getElementById("photoGallaryDiv").style.display = 'none' ;

	if(document.getElementById("specialPageInfoDiv").style.display = 'block')
		document.getElementById("specialPageInfoDiv").style.display = 'none' ;

	if(document.getElementById('descriptionDiv').style.display = 'block')
	   document.getElementById('descriptionDiv').style.display = 'none' ;

	if(document.getElementById('specialPageHighLightsDiv').style.display = 'none')
	   document.getElementById('specialPageHighLightsDiv').style.display = 'block' ;

$("#createNewTabId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#developmentGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#profileGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageInfoId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#specialPageHighLightId").css({"background":"none repeat scroll 0 0 #F61D50"});


	var specialPageHighLightsDiv = document.getElementById("specialPageHighLightsDiv");
	var str ='';
	str += '<div id="content" align="center" style="width:650px;">';
	str += '<table style="margin-top:10px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Add Description" onclick="buildSpecialPageHighlightsDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Update Description" onclick="getSpecialPageHighLights()"></td>';
	str += '</tr>';
	str += '</table>'
    str += '<fieldset class="imgFieldset" style="width:400px;">';	
	str += '<center style="margin-top: 7px;"><b style="font-size:15px"><font color="#4B74C6">Add The SpecialPage Highlights</font> </b> </center>';
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<div id="galErrorMsgDivId"></div>';
	str += '<div id="specPagehighLightsErrorMsgDivId"></div>';
	str += '<tr>';
	str += '<td>';
	str += '<b><font color="#4B74C6">Description</font></b></td><td><textarea id="specialPageHighLightDescId" name="profileDescription" cols="30" rows="5"></textarea></td></tr>';
	str += '</table>';
	str += '<table><tr><td style="padding-left: 82px"><input type="button" class="imageButton" value="Add Discription" style="background-color:#57B731" onClick="addSpecialPageHighLights()"></td><td style="padding-left: 5px"><input type="button" class="imageButton" value="Cancel" style="background-color:#CF4740"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
		specialPageHighLightsDiv.innerHTML = str;
	
}

function addSpecialPageHighLights()
{
	var specialPageId = $('#specialPageId').val();
	var description = $.trim($("#specialPageHighLightDescId").val());
	if(description == "")
	{
		$('#specPagehighLightsErrorMsgDivId').addClass('specPagehighLightsErrorMsgDivId').html('Description is Required.');
		return;
	}
	else if(description.length > 2000)
	{
		$('#specPagehighLightsErrorMsgDivId').addClass('specPagehighLightsErrorMsgDivId').html('Description should be less than 2000 characters.');
		return;
	}
	var jsObj =
		{ 
        	specialPageId : specialPageId,
			description   : description,
			task : "getSpecialPageHighLights"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSpecialPageHighLightsAction.action?"+rparam;						
	callAjax(jsObj,url);
	
}

function deleteSpecialPageHighLightsById(id)
{
	var r=confirm("Do you want to Delete!");
	if(r==true)
		var jsObj={

		id:id,
		task:"deleteSpecialPageHighLightDesc"

	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "deleteSpecialPageHighLightDescAction.action?"+rparam;
	callAjax(jsObj,url);
	
}

 function getSpecialPageHighLights()
 {

   var specialPageId = $('#specialPageId').val();

   var jsObj = {
         specialPageId:specialPageId,
         task:"getHighLights"
         };

    var param="task="+YAHOO.lang.JSON.stringify(jsObj);
    var url = "getSpecialPageHighLights.action?"+param;

    callAjax(jsObj,url);
 }

function buildSpecialPageHighLightsForUpdate(results)
{
	
	sizeOfArray = myResults.length;
	var str ='';
	var i;
	str += '<div id="content" align="center" style="width:650px;">';
	str += '<table style="margin-top:10px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Add Description" onclick="buildSpecialPageHighlightsDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Update Description" onclick="getSpecialPageHighLights()"></td>';
	str += '</tr>';
	str += '</table>'
	str += '<fieldset class="imgFieldset" style="width:400px;">';	
	str += '<center><b style="font-size:15px"><font color="#4B74C6">Update The SpecialPage Highlights </font> </b> </center>';
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<div id="galErrorMsgDivId"></div>';
	str += '<div id="fileUploadErrorMsgDivId"></div>';
	str += '<tr>';
	str += '<td>';
	str += '<b><font color="#4B74C6">Order No</font></b></td><td style="padding-left: 82px"><b><font color="#4B74C6">Description </font></b></td></tr>';
	for(i=0 ; i<results.length ; i++)
	{
	str += ' <tr><td style="padding-right:100px"><input type="text" id="orderNo_'+i+'" value= "'+results[i].orderNo+'" size="5" style="width: 40px;"></td>';
	str += ' <td style="padding-right: 82px"> <textarea id="descriptionId_'+i+'" cols="25" rows="4"> '+results[i].description+'</textarea> </td>';
	str += ' <td style="padding-right: 82px"> <input type = "button" id="delete_'+i+'" class="buttonStyle" style="background: none repeat scroll 0 0 #F61D50;" value = "Delete" onClick="deleteSpecialPageHighLightsById('+results[i].ids+')"></td></tr>';
	str += ' <input type="hidden" id="specialPageDescId_'+i+'" value="'+results[i].ids+'">';
	}
	str += '</table>';
	str += '<table><tr><td style="padding-right: 5px"><input type="button" class="imageButton" value="Update Discription" style="background-color:#57B731" onClick="updateSpecialPageHighLightDiscription()"></td><td><input type="button" class="imageButton" value="Cancel" style="background-color:#CF4740"></td></tr></table>';
	str += '</fieldset>';
	str+='</div>';
	
	document.getElementById("specialPageHighLightsDiv").innerHTML = str;
	

}

function updateSpecialPageHighLightDiscription()
{
	var specialPageId = $('#specialPageId').val();
	var orderNoArr = [];
	var descriptionArr = [];
	var profDescIdArr = [];
	for(i=0 ; i < sizeOfArray ; i++)
   	{
		orderNoArr.push(document.getElementById('orderNo_'+i).value);
		profDescIdArr.push(document.getElementById('specialPageDescId_'+i).value);
		descriptionArr.push(removeAllUnwantedCharactersInArray(document.getElementById('descriptionId_'+i).value));		
   	}
  
    var jsObj =
	{ 
		specialPageId :specialPageId,
		orderNoArr : orderNoArr,
		descriptionArr : descriptionArr,
		profDescIdArr : profDescIdArr,
		task : "updateSpecialPageHighLights"
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateSpecialPageHighLightsAction.action?"+rparam;
	callAjax(jsObj,url);	
 }

 function showSpecialPageHighLightsDeleteStatus(myResults)
 {
	if(myResults.resultCode==0)
	{
	   getSpecialPageHighLights();
	   $("#fileUploadErrorMsgDivId").html('<font color="green"><b> deleted successfully</b></font>');
	   return;
	}
    else if(myResults.resultCode==1)
	{
      $("#fileUploadErrorMsgDivId").html('<font color="red"><b>Error Ocuured, Try Again.</b>');
	  return;
	}
		
}

 function showSpecialPageHighLightsDescStatus(myResult)  
 {
	if(myResult.resultCode == 0)
	{
		$("#specialPageHighLightDescId").val('');
		$("#specPagehighLightsErrorMsgDivId").html('<font color="green"><b>Description Saved Successfully.</b>');
		return;

	}
	else if(myResult.resultCode == 1) 
	{
		$("#specPagehighLightsErrorMsgDivId").html('<font color="red"><b>Error Ocuured, Try Again.</b>');
		return;
	}
	
}

function showSpecialPageHighLightsDiscUpdateStatus(myResult)  
{
	if(myResult.resultCode == 0)
	{
		$('#errorDivEle').html('<font color="green"><b>Profile Discription Updated Successfully.</b>');
		return;
	}
	else if(myResult.resultCode == 1) 
	{
		$('#errorDivEle').html('<font color="red"><b>Error Ocuured, Try Again.</b>');
		return;
	}
	
}
