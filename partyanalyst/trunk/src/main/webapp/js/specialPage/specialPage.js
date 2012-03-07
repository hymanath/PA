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
  //var specialPageId = ${specialPageVO.specialPageId};
   
   var specialPageDescriptionId='${specialPageDescriptionId}';
var timeST = new Date().getTime();

function insertProfileDiscription()
{
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

	document.getElementById("headingnames").innerHTML = "Event Description";
	var str ='';
	str += '<div id="content" style="width:650px;">';
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
	str += '<div id="content" style="width:650px;">';
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
			   clearOptionsListForSelectElmtId('source');
			   createOptionsForSelectElmtId('source',myResults);
			}
			else if(jsObj.task == "getLanguage")
			{ 
				
               clearOptionsListForSelectElmtId('language');
			   createOptionsForSelectElmtId('language',myResults);
			}
			else if(jsObj.task == "eventGallariesForUplaod")
			{
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
			else if(jsObj.task == "getPhotosInAGallary")
			{
               showPhotosInAGallary(myResults);
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

	}
		catch(e)
						{   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
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
	str += '<div id="content" style="width:650px;">';
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
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
	str += ' <tr><td style="padding-right:150px"><input type="text" id="orderNoId_'+i+'" value= "'+myResults[i].orderNo+'" size="5"></td>';
	str += ' <td style="padding-right: 82px"> <textarea id="descId_'+i+'" cols="25" rows="4"> '+myResults[i].description+'</textarea> </td>';
	str += ' <td style="padding-right: 82px"> <input type = "button" id="delete_'+i+'" class="buttonStyle" style="background: none repeat scroll 0 0 #F61D50;" value = "Delete" onClick="deleteProfileById('+myResults[i].specialPageDescriptionId+')"></td></tr>';
	str += ' <input type="hidden" id="specialPageDescriptionId_'+i+'" value="'+myResults[i].specialPageDescriptionId+'">';

	}
	str += '</table>';
	str += '<table><tr><td style="padding-right: 23px"><input type="button" class="imageButton" value="Update Discription" style="background-color:#57B731" onClick="updateProfileDiscription()"></td><td style="padding-left: 20px"><input type="button" class="imageButton" value="Cancel" style="background-color:#CF4740"></td></tr></table>';
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
function updateProfileDiscription(){
	var specialPageId = document.getElementById('specialPageId').value;
	var orderNoArr = [];
	var descriptionArr = [];
	var profDescIdArr = [];
	for(i=0 ; i < sizeOfArray ; i++)
   		{
		
			orderNoArr.push(document.getElementById('orderNoId_'+i).value);
			profDescIdArr.push(document.getElementById('specialPageDescriptionId_'+i).value);
			descriptionArr.push(removeAllUnwantedCharacters(document.getElementById('descId_'+i).value));		
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


    document.getElementById("headingnames").innerHTML = "Video Gallery";
		
	var videoGallaryDivElmt = document.getElementById("videoGallaryDiv");
	var str ='';
	str +='<div id="content" style="width:650px;">';
		
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload Video" onclick="buildUploadVideoDiv()"></td>';
	str += '</tr>';
	str += '</table>';

	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A Video Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="pVGallaryNameId" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pVGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<div style="padding-right: 63px"><input type="radio" value="public" name="visibility" id="vpublicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 78px"><input type="radio" value="private" name="visibility" id="vprivateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	
	str += '<table><tr><td style="padding-right:40px"><input type="button" class="imageButton" value="Create Gallery" style="background-color:#57B731" onClick="createVideoGallary(\'Video Gallary\')"></td><td style="padding-right: 10px"><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'videoGallaryDiv\')" style="background-color:#CF4740"></td></tr></table>';

	
	str += '</fieldset>';
	str +='</div>';
	
	videoGallaryDivElmt.innerHTML = str;

}



function buildCreateVideoGallaryDiv(){
	 var videoGallaryDivElmt = document.getElementById("videoGallaryDiv");
	var str ='';
	str +='<div id="content" style="width:650px;">';
		
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload Video" onclick="buildUploadVideoDiv()"></td>';
	str += '</tr>';
	str += '</table>';

	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A Video Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="pVGallaryNameId" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pVGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<div style="padding-right: 63px"><input type="radio" value="public" name="visibility" id="vpublicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 78px"><input type="radio" value="private" name="visibility" id="vprivateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	
	str += '<table><tr><td style="padding-right:40px"><input type="button" class="imageButton" value="Create Gallery" style="background-color:#57B731" onClick="createVideoGallary(\'Video Gallary\')"></td><td style="padding-right: 10px"><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'videoGallaryDiv\')" style="background-color:#CF4740"></td></tr></table>';

	
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
		str += '<font color="green"><b>Gallery Created Successfully.</b>';
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
	
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<tr>';
	str += '<td><input type="button" class="imageButton" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload Video" onclick="buildUploadVideoDiv()"></td>';
	str += '</tr>';
	str += '</table>';
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Upload A Video</h2>';
	str+=  '<div id="fileSuccessDiv"></div>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
    str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%">';
	str += '<tr><td><b><font color="#4B74C6">Select Gallery</font></b></td><td><select id="gallarySelectId" name="gallarySelectIdss" style="width:175px;"><option value="0">Select</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Video Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="fileTitleId" name="videoTitle" size="25" maxlength="200"></td></tr>';
    str += '<tr><td><b><font color="#4B74C6">Video Description<font class="requiredFont">*</font></font></b></td><td><textarea id="fileDescId" name="videoDescription" cols="19" rows="3" name="requirement"></textarea></td></tr>';
    str += '<TR>';
	str += ' <td><b><font color="#4B74C6">File Date<font class="requiredFont">*</font></font></b></td>';
	str += '<TD style="padding-right: 31px;"><input type="text" id="existingFromText" readonly="true" name="fileDate" size="20"/>';
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '<TD>';
	str += '<A href="javascript:{}" id="existingFromText_Div" title="Click To Select A Date" onclick="showDateCal()">';
	str += '<IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '</TD>';
	str += '</TR>';
	str += '<tr><td><b><font color="#4B74C6">Video Path In Youtube<font class="requiredFont">*</font></font></b></td><td><input type="text" id="path" name="path" size="25" maxlength="200"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Keyword</font></b></td><td><input type="text" id="keyword" name="keyword" size="25" maxlength="200"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Source</font><font class="requiredFont">*</font></b></td><td><select id="source" name="source" style="width:175px;"><option value="0">Select Source</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Language</font><font class="requiredFont">*</font></b></td><td><select id="language" name="language" style="width:175px;"><option value="0">Select Language</option></select></td></tr>';
	str += '</table>';
	str += '<div style="padding-right: 72px;"><input type="radio" value="public" name="visibility" id="vpublicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 88px;"><input type="radio" value="private" name="visibility" id="vprivateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	str += '<table><tr><td style="padding-right: 18px;"><input type="button" class="imageButton" value="Upload Video" style="background-color:#57B731" onClick="uploadVideoGallary()"></td><td style="padding-right: 31px;"><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'videoGallaryDiv\')"   style="background-color:#CF4740"></td></tr></table>';
	
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("videoGallaryDiv").innerHTML = str;
	getEventGallariesForUplaod('Video Gallary');
	getSource();
	getLanguage();
}


function getSource(){
	
var jsObj = {
	time : timeST,
	task : "getSource"	
};
	
var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getEventsGallariesForUplaodAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}
function getLanguage()
{
	
	var jsObj={
		time:timeST,
	  task:"getLanguage"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getEventsGallariesForUplaodAction.action?"+rparam;						
	callAjax(jsObj,url);
 

}

function uploadVideoGallary(){

	var fileTitle = document.getElementById('fileTitleId').value;
	var fileDesc = document.getElementById('fileDescId').value;
	var path = document.getElementById('path').value;
	var specialPageId=document.getElementById("specialPageId").value;
	var galId = document.getElementById("gallarySelectId").value;
	var keyword = document.getElementById("keyword").value;
	var sourceId = document.getElementById("source").value;
	var languageId = document.getElementById("language").value;
	var fileDate = document.getElementById("existingFromText").value;
	var isPublic = document.getElementById('vpublicRadioId').checked;
	var makeThis = 'private';
	
	var errorDivEle = document.getElementById('galErrorMsgDivId');
	var eFlag = false;
	var str = '<font color="red">';

	if(fileTitle.length == 0)
	{
		str += 'Title Is Required<br>';
		eFlag = true;
	}
	if(fileTitle.length > 200)
	{
		str += 'Title should be less than 50 Characters<br>';
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

	if(fileDate.length == 0)
	{
		str += 'File Date is Required<br>';
		eFlag = true;
	}
	str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;

	if(isPublic)
		makeThis = 'public';

	var jsObj =
		{ 
		    
            specialPageId	: specialPageId,
			gallaryId	: galId,
			videoTitle	: fileTitle,
			videoDescription : fileDesc,
			path		: path,
			keywords	: keyword,
			sourceId    : sourceId,
			languageId  : languageId,
			fileDate	: fileDate,
			visibility	: makeThis,
		   	task		: "eventVideoGallariesForUpload"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "eventManageAction.action?"+rparam;
	callAjax(jsObj,url);
	clearFields();
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


	//var newsGallaryDiv = document.getElementById("newsGallaryDiv");
	document.getElementById("headingnames").innerHTML = "News Gallery";
	var str ='';
	str+='<div id="content" style="width:650px;">';
	str +=  '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="imageButton" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News" onclick="buildUploadNews()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A News Category</h2>';
	str+='<table align="left" class="paddingCss"><tr><td><div id="newsErrorMsgDivId" /></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Category Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="newsCateName" size="25" maxlength="100" /></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="newsCateDesc" cols="27" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<table><tr><td><input type="radio" value="public" name="visibility" id="newsPublicRadio" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></tr></td>';
	str += '<tr><td><input type="radio" value="private" name="visibility" id="newsPrivateRadio"><b><font color="#4B74C6">Make This Private</font></b></input></tr></td></table>';
	
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
	str+='<div id="content" style="width:650px;">';
	str +=  '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="imageButton" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News" onclick="buildUploadNews()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A News Category</h2>';
	str+='<table align="left" class="paddingCss"><tr><td><div id="newsErrorMsgDivId" /></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Category Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="newsCateName" size="25" maxlength="100" /></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="newsCateDesc" cols="27" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<table><tr><td><input type="radio" value="public" name="visibility" id="newsPublicRadio" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></tr></td>';
	str += '<tr><td><input type="radio" value="private" name="visibility" id="newsPrivateRadio"><b><font color="#4B74C6">Make This Private</font></b></input></tr></td></table>';
	
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
		str += '<font color="green"><b>Gallery Created Successfully.</b>';
	}
	else
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';

	errorDivEle.innerHTML = str;
	document.getElementById('newsCateName').value='';
	document.getElementById('newsCateDesc').value='';
}
function  buildUploadNews()
{
	
   var tempPartyId = document.getElementById("specialPageId").value;
   var str ='';
	str+='<div id="content" style="width:650px;">';
	str +=  '<table style="margin:5px;width:40%;margin-left:50px;">';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="imageButton" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News" onclick="buildUploadNews()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';
	str += '<fieldset class="imgFieldset" style="width:480px;">';
	str += '<form name="uploadForm" action="uploadFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadNewsForm">';
	str += '<h2 align="center">Upload A News</h2>';
	str += '<table  align="left" class="paddingCss"><tr><td><div id="uploadNewsFileErrorDiv" /></td></tr></table>';
	str += '<table>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Select Category</td><td class="selectWidthPadd"><select id="gallaryId" name="gallaryId" class="selectWidth" /></select></td>';
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
	str += '     <TD class="selectWidthPadd"><input type="text" id="existingFromText" readonly="true" name="fileDate" size="25"/>';
	str += '         <DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '     <TD>';
	str += '       <A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	str += '       <IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '     </TD>';
	str += '   </tr>';
	str += '</table>';
	str += '     </td>';
	str += '   </TR>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Source<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="source" name="source" style="width:175px;"><option value="0">Select Source</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">Language<font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="language" name="language" style="width:175px;"><option value="0">Select Language</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">File Path<font class="requiredFont">*</font></td>';
	str += '       <td class="selectWidthPadd"><input type="file" name="userImage" id="newsFileId" size="25" /></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td></td>';
	str += '       <td><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></td>';
    str += '   </tr>';
	str += '   <tr>';
	str += '       <td></td>';
	str += '       <td><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></td>';
	str += '   </tr>';
	str +='    <tr>';
    str +='	   <td class="tdWidth1">Location Scope</td>';
    str +='	   <td class="selectWidthPadd"><select id="scopeDiv" name="locationScope" class="selectWidth" onchange="getLocations(this.options[this.selectedIndex].value)"  /></td>';
    str +='  </tr>';
	str +='  <tr>';
	str +='    <td colspan="2">';
	str +='       <div id="showScopeSubs" />'; 
	str +='    </td>';
	str +='  </tr>';
	str += '</table>';

	str +='<input type="hidden" name="profileType" value="party_profile">';
	str +='<input type="hidden" name="profileId" value="'+tempPartyId+'">';
	str +='<input type="hidden" name="profileGalleryType" value="news_gallery">';

	str += '<table><tr><td><input type="button" class="imageButton" value="Upload News" style="background-color:#57B731" onClick="uploadNews()"></td><td><input type="button" class="imageButton" value="Cancel"  onClick="clearDiv(\'newsGallaryDiv\');" style="background-color:#CF4740"></td></tr></table>';
	str += '</form>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("newsGallaryDiv").innerHTML = str;
	getPartyGallariesForUplaod("News Gallary");
	getScopes();
	getSource();
	getLanguage();
}
function uploadNews()
{
	if(validateNewsFileUpload())
	{
		var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					showNewsUploadStatus(uploadResult);				
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
	document.getElementById('fileTitle').value = '';
	document.getElementById('fileDescription').value = '';
	document.getElementById('keywords').value = '';
	document.getElementById('existingFromText').value = '';
	document.getElementById('source').value = '';
	document.getElementById('newsFileId').value = '';	
	document.getElementById('publicRadioId').checked = true;
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
	var fileDate = document.getElementById("existingFromText").value;
	var flag = true;

	var errorDivEle = document.getElementById('uploadNewsFileErrorDiv');
	var str = '<font color="red">';

	if(fileTitle.length == 0)
	{
		str += ' Title Is Required.<br>';
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
		str += 'File Is Required.<br>';
		flag = false;
	}
	if(source.length == 0)
	{
		str += 'Source Is Required.<br>';
		flag = false;
	}
	if(languageId.length == 0)
	{
		str += 'Language Is Required.<br>';
		flag = false;
	}
	/*if(categoryId.length == 0)
	{
		str += 'Category Is Required.<br>';
		flag = false;
	}*/
	if(keywords.length >200)
	{
		str += 'Keywords should be less than 200 Characters<br>';
		flag = false;
	}
	if(fileDate.length == 0)
	{
		str += 'File Date is required';
		flag = false;
	}
	
	str += '</font>';
	errorDivEle.innerHTML = str;

	return flag;
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
		 else if(jsObj.task == "getFileByFileId")
			{
               showNews(myResults,jsObj.arrayType);
			}
		 else if(jsObj.task == "getNewsToDisplay")
			{
               showTotalNews(myResults);
			}
		 else if(jsObj.task == "getFirstThreePhotoGallaryDetail")
			{
               buildFirstThreePhotoRecords(myResults);
			}
		/* else if(jsObj.task == "getPhotoGallaryWithOutGallerySizeZero")
			{
               buildSpecialPagePhotoGallary(myResults);
			}*/
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

		}
		catch(e)
		{   
		 alert("Invalid JSON result" + e);   
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
   str+='<table width="100%" style="margin-top:10px;">'
   str+= '<tr style="display:none">';
    str+= '<td width="33%" class="imageStyle"><table class="tableStyle">';
    str+= '<tr><td><a rel="photo_gallery" id="photoClickId" href="'+results[0].path+'" title="'+results[0].fileTitle1+'"><img alt="" src="'+results[0].path+'" class="gallaryImg" height="100px" /></a></td></tr>';
	str += '<tr><td><div><b>'+results[0].fileDescription1+'</b></div></td></tr>';
    str+= '<tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';
    str+= '</tr>';
   for(var i =1;i<results.length; i++)
   {
     no_of_imagesPerRow = 3; 
     j = i;
     if(j++ % no_of_imagesPerRow == 0){
       str+= '<tr style="display:none">';
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

   fireEvent(document.getElementById('photoClickId'),'click');
   document.getElementById("buildPhotoGallaryDiv").innerHTML ='';
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
  
  var str ='';
  
  if(results.length>0)
  {
   
   str +='<h1 class="pr-title">news &amp; Events<span class="or-down-arrow"><img src="images/candidatePage/or-down-arrow.png" alt=""/></span></h1>';

   str+='<ul>';
   for(var i =0 ;i<results.length && i<4;i++)
   {
     initialFileIdArray[i]=results[i].fileId;
     str+='<a href="javascript:{}" onclick="getNews('+results[i].fileId+','+i+',\'initialArray\')" class="titleStyle"\">';
	 
	 if(results[i].fileTitle1.length > 40)
		str +='<li><strong>'+results[i].fileTitle1.substring(0,40)+'..</strong>';
	 else
		str +='<li><strong>'+results[i].fileTitle1+'</strong>';;

	 str += '</a>';
     str+='<div class="year-time"><span class="li-red">'+results[i].source+'</span> | '+results[i].fileDate+'</div>';
     
	 if(results[i].fileDescription1.length > 62)
		str += results[i].fileDescription1.substring(0,62)+'..</li>';
	 else
		str += ''+results[i].fileDescription1+'</li>';

     }
   str+='  </ul>';
   
   str+='<div class="more"><a href="javascript:{}" onclick="getTotalNews(\'totalNews\');">More</a></div>';
   
   str+='<table><tr><td><div id="showNewsDiv" /></td></tr></table>';
   str+='<table><tr><td><div id="showAllNewsDiv" /></td></tr></table>';
   
  
    for(var i =4 ;i<results.length;i++)
	{
	  initialFileIdArray[i]=results[i].fileId;
	}
   }
	else
	{
		str+='<div class="pft-sec"> <img src="./images/new_homepage/pft.jpg" alt=""/>';
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

 function getTotalNews(viewType)
 {  
	var queryType='Public';
   	var jsObj =
		{   
		    time : timeST,
			viewtype:viewType,
			specialPageId:specialPageId,
			startRecord:0,
			maxRecord:20,
			queryType:queryType,
			task:"getNewsToDisplay"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "specialPagePhotoGallaryAction.action?"+rparam;						
	callAjaxForSpecialPage(jsObj,url);  
 }
 function showTotalNews(results)
 { 
    $("#showAllNewsDiv").dialog({ stack: false,
							    height: 570,
								width: 720,
								position:[150,120],								
								modal: true,
								title:'<font color="Navy"><b>News</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
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
     str+='       <td><a href="javascript:{}" onclick="getNews('+results[i].fileId+','+i+',\'array\')" class="titleStyle"\">'+results[i].fileTitle1+'</a></td>';
     str+='     </tr>';
//	 str+='     <tr>';
 //  str+='       <td><img alt="" src="'+results[i].path+'" style="width:242px;height:275px;"/></td>';
 //  str+='     </tr>';
     str+='     <tr>';
     str+='       <td><a href="javascript:{}" onclick="getNewsBySource1(\''+results[i].source+'\')" class="titleStyle"\"><font color="#FF4500">'+results[i].source+'</font></a> | <a href="javascript:{}" onclick="getNewsByLanguage1(\''+results[i].language+'\')" class="titleStyle"\"><font color="#FF4500">'+results[i].language+'</font></a> | '+results[i].fileDate+'</td>';
     str+='     </tr>';
     str+='     <tr>';
     str+='       <td>'+results[i].fileDescription1+'</td>';
     str+='     </tr>';
	 str+='    <tr><td><hr style="width:98%;"></td></hr></tr>';
   }
   str+='  </table>';
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
			maxResults:20,
			queryType:queryType,
			task:"getNewsByScope"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidateNewsGallaryAction.action?"+rparam;						
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
			maxResults:20,
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
 function getNews(fileId,filSize,arrayType)
 { 
  if(arrayType=="initialArray")
  initialCurrentSize=filSize;
  if(arrayType=='array')
  currentSize= filSize;
    var jsObj =
		{   arrayType:arrayType,
		    time : timeST,
			fileId:fileId,
			task:"getFileByFileId"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjaxForSpecialPage(jsObj,url);  
 }
 function showNews(results,arrayType)
  {
    arraySize = fileIdArray.length;
	initialArraySize = initialFileIdArray.length;
    var fileType = results[0].name.split(".");
	  $.fx.speeds._default = 1000;
	  $("#showNewsDiv").dialog({ stack: false,
								height: 'auto',
								width: 'auto',
								closeOnEscape: true,
								position:[20,20],
								show: "blind",
								hide: "explode",
								modal: true,
								title:'<font color="Navy">'+results[0].fileTitle1+'</font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#showNewsDiv").dialog();
	var str='';
	if(arrayType=='array')
	{
	 str+='<table>';
	 	 str+='     <tr>';
	 str+='       <td>';
	 str+='        <B>Source</B> : <font color="#FF4500">'+results[0].source+'</font> <B> Date </B>:<font color="#FF4500"> '+results[0].fileDate+'</font>';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='   <tr>';
	 str+='      <td>';
	 str+='         <table>';
	 str+='	          <tr>';
	 if(currentSize-1 >=0)
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getPreviousNews('+currentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
	 }
	 if(fileType[(fileType.length-1)] == "pdf"  ){
	 str+='             <td><img alt="" src="images/doc_images/PDFImage.png" onclick="openFile(\''+results[0].path+'\')" style="cursor:pointer;" /></td>';
	 }
	 else
	 {
	 str+='             <td><div class="container"><img alt="" style="max-width:780px;max-length:800px;" src="'+results[0].path+'" /></div></td>';
	 }
	 if(currentSize+1 <= (arraySize-1))
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getNextNews('+currentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/next.png"  class="newsImage" /></a></td>';
     }
	 str+='		      </tr>';
	 str+='         </table>';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='     <tr>';
	 str+='       <td>';
	 str+='        '+results[0].fileDescription1+'';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='<table>';
	}
	else if(arrayType=="initialArray")
	{
	 str+='<table>';
	 str+='     <tr>';
	 str+='       <td>';
	 str+='        <B>Source</B> : <font color="#FF4500">'+results[0].source+'</font> <B> Date </B>: <font color="#FF4500">'+results[0].fileDate+'</font>';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='   <tr>';
	 str+='      <td>';
	 str+='         <table>';
	 str+='	          <tr>';
	 if(initialCurrentSize-1 >=0)
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getPreviousNews('+initialCurrentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
	 }
	 if(fileType[(fileType.length-1)] == "pdf"  ){
	 str+='             <td><img alt="" src="images/doc_images/PDFImage.png" onclick="openFile(\''+results[0].path+'\')" style="cursor:pointer;" /></td>';
	 }
	 else
	 {
	 str+='             <td><div class="container"><img alt="" style="max-width:780px;max-length:800px;" src="'+results[0].path+'" /></div></td>';
	 }
	 if(initialCurrentSize+1 <= (initialArraySize-1))
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getNextNews('+initialCurrentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/next.png"  class="newsImage" /></a></td>';
     }
	 str+='		      </tr>';
	 str+='         </table>';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='     <tr>';
	 str+='       <td>';
	 str+='        '+results[0].fileDescription1+'';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='<table>';
	}
	 document.getElementById("showNewsDiv").innerHTML=str;
	
  }
  function getPreviousNews(val,arrayType)
  { if(arrayType=="initialArray")
    {
     initialCurrentSize = val-1;
    getNews(initialFileIdArray[initialCurrentSize],initialCurrentSize,arrayType);
	}
	else if(arrayType=='array')
	{
	  currentSize = val-1;
    getNews(fileIdArray[currentSize],currentSize,arrayType);
	
	}
	
	
  }
  function getNextNews(val,arrayType)
  {
    if(arrayType=="initialArray")
	{
     initialCurrentSize = val+1;
     getNews(initialFileIdArray[initialCurrentSize],initialCurrentSize,arrayType);
	 }
	else if(arrayType=='array')
	{
	 currentSize = val+1;
     getNews(fileIdArray[currentSize],currentSize,arrayType);
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
	   str+='<li><img alt="" src="'+results[0].path+'" style="height:120px;width:127px;" onclick="getCandidatesPhotosInAGallary(\''+results[0].gallaryId+'\')"/><br />';
	  str+=''+results[0].title+'</li>';
	 
	  }
	  if(results[1]!=null && results[1].path!=null)
	  {
	  count++;
	  str+='<li><img alt="" src="'+results[1].path+'" style="height:120px;width:127px;" onclick="getCandidatesPhotosInAGallary(\''+results[1].gallaryId+'\')"/><br />';
	  str+=''+results[1].title+'</li>';
	  }
	  if(results[2]!=null  && results[2].path!=null)
	  {
	  count++;
	  str+=' <li><img alt="" src="'+results[2].path+'" style="height:120px;width:127px;" onclick="getCandidatesPhotosInAGallary('+results[2].gallaryId+')"/><br />';
	  str+=''+results[2].title+'</li>';
	  
	  }
	  for(var i=3;i<results.length;i++)
	  {
	   if(results[i]!=null  && results[i].path!=null && count<3)
	   {
		count++;
		str+='<li><img alt="" src="'+results[i].path+'" style="height:120px;width:127px;" onclick="getCandidatesPhotosInAGallary('+results[i].gallaryId+')"/><br />';
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
function openFile(filePath,fileType){

window.open(filePath, "browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
}

function getVideosOfCandidate()
{
	var jsObj =
		{   
			specialPageId : specialPageId,
			startIndex  : 0,
			maxRecords	: 20,
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
   $("#videoGallaryPopUpDiv").dialog({ stack: false,
							    height: 350,
								width: 520,
								position:[150,120],								
								modal: true,
								title:'<font color="Navy">Video Galleries</font>',
								overlay: { opacity: 0.5, background: 'black'}
								});

	showAllVideoGalleries();
}

function showAllVideoGalleries(){
		
   var jsObj = {
	       	   time : timeST,
			   specialPageId : specialPageId,
			   startRecord:0,
			   maxRecord:20,
			   task:"getSpecialPageGallaryDetail"
            };

    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "specialPagePhotoGallaryAction.action?"+rparam;						
	callAjaxForSpecialPage(jsObj,url);
	}

function buildVideoGallaries(results)
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

function buildAllVideosInGallary(results){

 var str ='';
 var videosDivElmt = document.getElementById("videoGallaryPopUpDiv");
		
		str+='<a href=javascript:{} style="color: #FFFFFF;margin-left: 339px;"" onclick="showAllVideoGalleries()" class="imageButton">Back To My Gallary</a>';
		
		str+='<table style="width:100%;">';
		for(var i in results)
		{
			no_of_imagesPerRow = 3; 
			j = i;

			if(j++ % no_of_imagesPerRow == 0)
				str += '<tr style="height:230px;">';
			
			str+='<td>';
			str+='<table style="font-size: 13px; font-family: verdana,arial;">';
			str+='<tr>';
			str+='<td>'+results[i].title+'';
			str+='</td>';
			str+='</tr>';
			str+='	<tr >';
			str+='<td>';
			str+='<a target="blank"  href="http://www.youtube.com/v/'+results[i].pathOfFile+'?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer">';
			str+='<img style="border: 2px solid #CCCCCC;padding:5px;" src="http://img.youtube.com/vi/'+results[i].pathOfFile+'/0.jpg" width="110px;" height="100px;"/></td></a>';
			str+='</tr>';
			str+='<tr>';
			str+='<td width="30%">'+results[i].description+'';
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


	document.getElementById("headingnames").innerHTML = "Create New";
	var createNewDivElmt = document.getElementById("createNewDiv");
	var str ='';
	str +='<div id="content" style="width:650px;">';
		
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A Special Page</h2>';
	
	str += '<div id="createNewInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	
	str += '<table align="left" class="paddingCss"><tr><td><div id="createNewErrorMsgDivId"></div></td></tr></table>';

	str += '<table width="75%">';
	str += '<tr><td><b><font color="#4B74C6">Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="createNewNameId" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="createNewTitleId" size="25" maxlength="500"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Heading<font class="requiredFont">*</font></font></b></td><td><input type="text" id="createNewHeadingId" size="25" maxlength="300"></td></tr>';

	str += '<table><tr><td style="padding-right:40px"><input type="button" class="imageButton" value="Create" style="background-color:#57B731" onClick="createNewSpecialPage()"></td><td style="padding-right: 10px"><input type="button" class="imageButton" value="Cancel" onclick="clearCreateNewDiv()" style="background-color:#CF4740"></td></tr></table>';

	
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
		specialPageId : 1,
		task:"setEmailAlertsForEvent"
	};

 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
 var url="specialEmailAlertsForUserAction.action?"+rparam;
callAjax(jsObj,url);

}

function showStatusForEmailSubscription(results)
{
	var str='';
	if(results.resultCode == 0)
	{
		clearEmailFields();
		document.getElementById("alertMsg").innerHTML='<font color="green">You are Subscribed For Email alerts Successfully</font>';
	}
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
  str +='	   <td class="tdWidth">State :</td>';
  str +='	   <td><select id="stateDiv" name="locationValue"/></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStates();
  }
  else if(id==3)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">State :</td>';
  str +='	   <td><select id="stateDiv" class="selectWidth"  onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District :</td>';
  str +='	   <td><select id="districtDiv" class="selectWidth" name="locationValue" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStates();
  }
  else if(id==4)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">State :</td>';
  str +='	   <td><select id="stateDiv" class="selectWidth"  onchange="clearAllElmts(4,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District :</td>';
  str +='	   <td><select id="districtDiv" class="selectWidth"  onchange="clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Assembly Constituency :</td>';
  str +='	   <td><select id="constituencyDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStates();
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
  str +='	   <td class="tdWidth">State :</td>';
  str +='	   <td><select id="stateDiv"   class="selectWidth" onchange="clearAllElmts(5,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District :</td>';
  str +='	   <td><select id="districtDiv"  class="selectWidth" onchange="clearAllElmts(5,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Assembly Constituency :</td>';
  str +='	   <td><select id="constituencyDiv"  class="selectWidth" onchange="clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Mandal/Municipality/Corp/GMC :</td>';
  str +='	   <td><select id="mandalDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStates();
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
  str +='	   <td class="tdWidth">State :</td>';
  str +='	   <td><select id="stateDiv"  class="selectWidth" onchange="clearAllElmts(6,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District :</td>';
  str +='	   <td><select id="districtDiv"  class="selectWidth" onchange="clearAllElmts(6,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Assembly Constituency :</td>';
  str +='	   <td><select id="constituencyDiv"  class="selectWidth" onchange="clearAllElmts(6,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Mandal/Municipality/Corp/GMC :</td>';
  str +='	   <td><select id="mandalDiv"  class="selectWidth" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Village/Ward/Division :</td>';
  str +='	   <td><select id="villageDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStates();
  }
  else if(id==9)
  {
     var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">State :</td>';
  str +='	   <td><select id="stateDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">District :</td>';
  str +='	   <td><select id="districtDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Assembly Constituency :</td>';
  str +='	   <td><select id="constituencyDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Mandal/Municipality/Corp/GMC :</td>';
  str +='	   <td><select id="mandalDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'boothsInTehsilOrMunicipality\',\'\',document.getElementById(\'constituencyDiv\').options[document.getElementById(\'constituencyDiv\').selectedIndex].value)"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">Village/Ward/Division :</td>';
  str +='	   <td><select id="villageDiv" name="locationValue" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStates();
  }
}

function getStates()
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


	document.getElementById("headingnames").innerHTML = "Meta Info";
	var metaInfoDivElmt = document.getElementById("metaInfoDiv");
	var str ='';
	str +='<div id="content" style="width:650px;">';
		
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Add Meta Information</h2>';
	
	str += '<div id="metaInfoInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	
	str += '<table align="left" class="paddingCss"><tr><td><div id="metaInfoErrorMsgDivId"></div></td></tr></table>';

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
    str +='<div id="content" style="width:650px;">';
	str +='<table>';
	str +='   <tr>';
	str +='      <td><input type="button" class="imageButton" value="Create Gallery" onclick="buildPhotoGalleryDiv()"/></td>';
	str +='      <td><input type="button" class="imageButton" value="Upload Photos" onclick="buildUploadPhotosDiv()"/></td>';
	str +='   </tr>';
	str +='</table>';
	str +='<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
    str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDiv"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="pGallaryNameId" size="25" maxlength="200"></td></tr>';
    str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<div style="padding-right: 63px"><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 78px"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	str += '<table><tr><td style="padding-right: 35px;"><input type="button" class="imageButton" value="Create Gallery" style="background-color:#57B731" onClick="createGallary(\'Photo Gallary\',\'Create\')"></td><td style="padding-right: 49px;"><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'photoGallaryDiv\')" style="background-color:#CF4740"></td></tr></table>';


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
    str +='<div id="content" style="width:650px;">';
	str +='<table>';
	str +='   <tr>';
	str +='      <td><input type="button" class="imageButton" value="Create Gallery" onclick="buildPhotoGalleryDiv()"/></td>';
	str +='      <td><input type="button" class="imageButton" value="Upload Photos" onclick="buildUploadPhotosDiv()"/></td>';
	str +='   </tr>';
	str +='</table>';
	str +='<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Create A Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
    str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDiv"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="pGallaryNameId" size="25" maxlength="200"></td></tr>';
    str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<div style="padding-right: 63px"><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 78px"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	str += '<table><tr><td style="padding-right: 35px;"><input type="button" class="imageButton" value="Create Gallery" style="background-color:#57B731" onClick="createGallary(\'Photo Gallary\',\'Create\')"></td><td style="padding-right: 49px;"><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'photoGallaryDiv\')" style="background-color:#CF4740"></td></tr></table>';


	str +='</fieldset>';
	str+='</div>';
	photoGallaryDivEle.innerHTML = str;
}
function buildUploadPhotosDiv()
{
	
	var tempCandidateId = document.getElementById("specialPageId").value;
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	str += '<tr>';
	str += '	<td><input type="button" class="imageButton" value="Create Gallery" onclick="buildPhotoGalleryDiv()"></td>';
	str += '	<td><input type="button" class="imageButton" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
	str += '</tr>';
	str += '</table>';
	
	str += '<form name="uploadForm" action="uploadFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadFilesForm">';

	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Upload A Photo</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="fileUploadErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%">';
	str += '<tr><td><b><font color="#4B74C6">Select Gallery</font></b></td><td><select id="gallarySelectId" name="gallaryId" style="width:175px;"><option value="0">Select</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Photo Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="fileTitleId" name="fileTitle" size="25" maxlength="200"></td></tr>';

	str += '<tr><td><b><font color="#4B74C6">Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="fileDescId" name="fileDescription" cols="19" rows="3" name="requirement"></textarea></td></tr>';
	str +='<tr><td><b><font color="#4B74C6">File Path<font class="requiredFont">*</font></font><b></td><td><input type="file" name="userImage" id="fileId"/></td></tr></table>';
	str += '<div style="padding-right: 113px;"><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 127px;"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';

	str +='<input type="hidden" name="profileType" value="candidate_profile">';
	str +='<input type="hidden" name="profileId" value="'+tempCandidateId+'">';
	str +='<input type="hidden" name="profileGalleryType" value="photo_gallery">';
	
	str += '<table><tr><td style="padding-right: 40px;"><input type="button" class="imageButton" value="Upload Photo" style="background-color:#57B731" onClick="uploadAFile()"></td><td style="padding-right: 41px;"><input type="button" class="imageButton" value="Cancel" onclick="clearDiv(\'photoGallaryDiv\')"  style="background-color:#CF4740"></td></tr></table>';


	str += '</form>';
	str += '</fieldset>';
	str+='</div>';
	document.getElementById("photoGallaryDiv").innerHTML = str;
	if(document.getElementById("gallarySelectId")!=null)
      getPhotoGallariesForUpload("Photo Gallary");
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
	if(validateFileUpload())
	{
		var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					showUploadStatus(uploadResult);				
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
	document.getElementById('fileTitleId').value='';
	document.getElementById('fileDescId').value='';
	document.getElementById('fileId').value='';
	
}

function validateFileUpload()
{
	var fileTitle = document.getElementById('fileTitleId').value;
	var fileDesc = document.getElementById('fileDescId').value;
	var fileVal = document.getElementById("fileId").value;
	var galId = document.getElementById("gallarySelectId").value;
	var flag = true;

	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '<font color="red">';

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
		str += '<font color="green"><b>Gallery Created Successfully.</b>';
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

		    document.getElementById("photoGallaryDiv").style.display = 'block' ;

			document.getElementById("headingnames").innerHTML = "Photo Gallery";

	var specialPageId = document.getElementById("specialPageId").value;
    document.getElementById("profileManagementMainOuterDiv1").style.display = 'block';
    document.getElementById("profileManagementMainOuterDiv6").style.display = 'none';
    var jsObj =
		{   
		    time : timeST,
			specialPageId:specialPageId,
			startRecord:0,
			maxRecord:20,
			task:"getspaecialPagePhotoGallaryDetails"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "showPhotoGalleryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function buildCandidatePhotoGallary(results)
{
	var str ='';

	
		str+='<div id="content" style="width:650px;">';
		
		str += '<table style="margin:5px;width:40%;margin-left:50px;">';
		str += '<tr>';
		str += '	<td><input type="button" class="imageButton" value="Create Gallery" onclick="buildPhotoGallery()"></td>';
		str += '	<td><input type="button" class="imageButton" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
		str += '</tr>';
		str += '</table>'
        str += '<center><div id="fileUploadErrorMsgDivId"></div></center>'; 
		str += '<fieldset class="imgFieldset">';
		str +='<table width="100%" style="margin-top:10px;">';
	if(results.length<=0)
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
		    gallaryId:gallaryId,
			task:"getPhotosInAGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function showPhotosInAGallary(results){
   var str ='';
   str+='<div id="content" style="width:650px;">';
   str += '<fieldset class="imgFieldset">';
   if(results.length>0)
   {
   str +='<table>';
   str +='   <tr>';
   str +='     <td><b style="color:green;font-size:14px;">'+results[0].gallaryName+'</b></td>'; 
   str +='   </tr>';
   str +='</table>';
   }
   str += '<center><div id="fileUploadErrorMsgDivId"></div></center>';
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
     str+= '<tr><td><a rel="photo_gallery" href="'+results[i].path+'" title="'+results[i].fileDescription1+'"><img alt="" src="'+results[i].path+'" class="gallaryImg" height="100px" /></a></td></tr>';
	 str += '<tr><td><div><b>'+results[i].fileDescription1+'</b></div></td></tr>';
	 str += '<tr><td><table><tr><td><input type = "button" class="buttonStyle" style="background: none repeat scroll 0 0 #0063dc;" value = "Update" id= "updateFile_'+i+'" onclick="updateFilesAndPhotos('+results[i].fileId+')"></td>';
	 str += '<td> <input type = "button" class="buttonStyle" style="background: none repeat scroll 0 0 #F61D50;" value = "Delete" id= "deleteFile_'+i+'" onclick="deleteFilesAndPhotos('+results[i].fileId+')"></td></tr></table>';
     str+= '<tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';
     if(j % no_of_imagesPerRow == 0){
       str+= '</tr>';
     }

   }
   str+= ' </table>';
   str += ' </fieldset>';
   str+='</div>';
   document.getElementById("photoGallaryDiv").innerHTML = str;

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
	
	str+='<div id="content" style="width:650px;">';
	str += '<fieldset class="imgFieldset" style="width:400px;">';
	str += '<h2 align="center">Update A Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDiv"></div></td></tr></table>';
	str += '<table width="75%"><tr><td><b><font color="#4B74C6">Gallery Name<font class="requiredFont"> * </font></font></b></td><td><input type="text" id="pGallaryNameId" size="25" maxlength="100"></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Description</font><b></td>';
	str += '<td><textarea id="pGallaryDescId" cols="19" rows="3" name="requirement">'+myResults.gallaryDescription+'</textarea></td></tr></table>';
	str +='<input type = "hidden" name = gallaryId id = gallaryId value ='+myResults.gallaryId+'>';
	str += '<div style="padding-left: 14px; padding-right: 120px; "><input type="radio" value="public" name="visibility" id="publicRadioId"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
	str += '<div style="padding-right: 123px;"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
	str += '<table><tr><td style="padding-right: 35px;"><input type="button" class="imageButton" value="Update Gallery" style="background-color:#57B731" onClick="createGallary(\'Photo Gallary\',\'Update\')"></td><td style="padding-right: 49px;"><input type="button" class="imageButton" value="Cancel" onclick="showPhotoGallery()" style="background-color:#CF4740"></td></tr></table>';
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
		str += '<div style="padding-right: 113px;"><input type="radio" value="public" name="visibility" id="publicRadioId"><b><font color="#4B74C6">Visible to Public Also</font></b></input></div>';
		str += '<div style="padding-right: 127px;"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font color="#4B74C6">Make This Private</font></b></input></div>';
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
function checkUserLoginStatus(user,divId)
{
	if(user == '' || user == null) 
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
		str+='<div class="container"><h4><div style="margin: 10px;color:ActiveCaption;">Only registered users can view this content.</div></h4><h5 style="color:#000;display:inline;position:relative;top:0px;"><div style="margin: 10px;"> Already a member ,   Click here to <a style="color:red;" href="loginInputAction.action">Login</a></div><div style="margin-left:160px;">(OR)</div><div style="margin: 10px;margin-top:-20px;">Not a member, Click here for <a style="color: Red; width: 114px; height: 8px;" href="freeUserRegistration.action"> FREE REGISTRATION <span style="margin-bottom: 20px;"><img src="images/specialPage/freeUser.png"></span></a></div></h5></div>';
		document.getElementById(divId).innerHTML = str;
	}
}
