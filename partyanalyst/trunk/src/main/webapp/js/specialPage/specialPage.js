
var specialPageDescriptionId='${specialPageDescriptionId}';
var timeST = new Date().getTime();

function insertProfileDiscription()
{
	if(document.getElementById('descriptionDiv').style.display = 'none')
		document.getElementById('descriptionDiv').style.display = 'block' ;
	if(document.getElementById('newsGallaryDiv').style.display = 'block')
	document.getElementById("newsGallaryDiv").style.display = 'none';
	

	if(document.getElementById("videoGallaryDiv").style.display = 'block')
	document.getElementById("videoGallaryDiv").style.display = 'none';
	
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
	
	specialPageId : 1,

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
           showDiscriptionStatus(myResults);  
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

 function showDiscriptionStatus(myResult)  
 {
	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
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
function cleardescriptionFields()
{
	document.getElementById('profileDescId').value='';

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

if(document.getElementById("videoGallaryDiv").style.display = 'none')
	document.getElementById("videoGallaryDiv").style.display = 'block' ;


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
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
    str += '<table align="left" class="paddingCss"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table width="75%">';
	str += '<tr><td><b><font color="#4B74C6">Select Gallery</font></b></td><td><select id="gallarySelectId" name="gallarySelectIdss" style="width:175px;"><option value="0">Select</option></select></td></tr>';
	str += '<tr><td><b><font color="#4B74C6">Video Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="fileTitleId" name="videoTitle" size="25" maxlength="50"></td></tr>';
    str += '<tr><td><b><font color="#4B74C6">Video Description<font class="requiredFont">*</font></font></b></td><td><textarea id="fileDescId" name="videoDescription" cols="19" rows="3" name="requirement"></textarea></td></tr>';
    str += '<TR>';
	str += ' <td><b><font color="#4B74C6">File Date</font></b></td>';
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
	if(fileTitle.length > 50)
	{
		str += 'Title should be less than 50 Characters<br>';
		eFlag = true;
	}
	if(fileDesc.length ==0)
	{
		str += 'Description is required<br>';
		eFlag = true;
	}
	if(fileDesc.length >200)
	{
		str += 'Description should be less than 200 Characters<br>';
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
		   	task		: "eventVideoGallariesForUplaod"
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

if(document.getElementById('newsGallaryDiv').style.display = 'none')
	document.getElementById('newsGallaryDiv').style.display = 'block';
	
	//var newsGallaryDiv = document.getElementById("newsGallaryDiv");
	
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
	specialPageId : 1,
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
function showGallaryCreateMsg(result,createOrUpdate)
{
	var errorDivEle = document.getElementById('galErrorMsgDivId');
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
	str += '       <td class="selectWidthPadd"><input type="text" id="fileTitle" name="fileTitle" size="25" maxlength="50"></text></td>'; 
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
	str += '     <td class="tdWidth2"><b><font color="#4B74C6">File Date</font></b></td>';
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
	str += '       <td class="selectWidthPadd"><input type="file" name="userImage" id="fileId" size="25" /></td>';
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
	
    // document.getElementById('gallarySelectId').value = 'gallaryId';
}
