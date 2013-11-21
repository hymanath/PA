function buildLocationWiseNews(results)
{
	var str='';
	$("#newsReportAjaxImg").css("display","none");
	
	if(results != null && results != '')
	{
	$("#locationWiseNewsDiv").css("display","block");
		str+='<label class="checkbox inline">';
		str+='<input type="checkbox" id="selectAll" value="Select All" onclick="selectAllCheckBox();">Select All';
		str+='</label>';
		str+='<label class="checkbox inline">';
		str+='<input type="checkbox" id="unselectAll" value="Unselect All" onclick="unselectAllCheckBox();">Unselect All';
		str+='</label>';
		for(var i in results)
		{
		str+='<div class="container">';
		str+='<h4 id="mainheading">'+results[i].scope+' WISE </h4>';
		for(var j=0; j< results[i].fileVOList.length;j++)
			{
			for(var k=0;k<results[i].fileVOList[j].fileVOList.length;k++)
			{
		var source = results[i].fileVOList[j].fileVOList[k].source;
		var eenadu = results[i].fileVOList[j].fileVOList[k].eenadu;
		str+=' <div class="row-fluid">';
        str+='<div class="span12 well">';
        str+='<div class="media">';
        str+=' <div class="media-body">';
        str+='<hgroup>';
		
		if(eenadu || source == "Eenadu Telugu")
				{
			
				str+="<h4 class='media-heading'><span class='enadu fontStyle pagerRow' style='font-weight:bold;'>";
				str+='<input type="checkbox" class="find-table newscheckbox" value='+results[i].fileVOList[j].fileVOList[k].contentId+'>&nbsp;';
				str+="<a href='javascript:{getNewsDetailsByContentId("+results[i].fileVOList[j].fileVOList[k].contentId+")}'>"+results[i].fileVOList[j].fileVOList[k].fileTitle1+"</a></span></h4>";
				}
				else
				{
				str+="<h4 class='media-heading' style='text-transform: capitalize;color: #005580;'>";
				str+='<input type="checkbox" class="find-table newscheckbox" value='+results[i].fileVOList[j].fileVOList[k].contentId+'>&nbsp;';
				str+="<a  href='javascript:{getNewsDetailsByContentId("+results[i].fileVOList[j].fileVOList[k].contentId+")}'>"+results[i].fileVOList[j].fileVOList[k].fileTitle1+"</a></h4><br/>";
				}
			
				str+='<div  class="span3">';
				str+='<span style="font-weight:bold;font-size:14px;"> Source : </span>';
			for(var k1=0;k1<results[i].fileVOList[j].fileVOList[k].fileVOList.length;k1++)
				{
			    str+='<span class="label"> '+results[i].fileVOList[j].fileVOList[k].fileVOList[k1].source+'</span>&nbsp;';
				}
				str+='</div>';
				str+='<div  class="span4">';
				str+='<span style="font-weight:bold;font-size:14px;"> Candidate Name : </span>';
			    str+='<span class="label"> '+results[i].fileVOList[j].fileVOList[k].candidateName+'</span>&nbsp;';
				str+='</div>';
				str+='<div  class="span3 ">';
				str+='<span style="font-weight:bold;font-size:14px;"> file Date : </span>';
			    str+='<span class="label"> '+results[i].fileVOList[j].fileVOList[k].fileDate+'</span>&nbsp;';
				str+='</div>';
			     str+='<i class="pull-right">-'+results[i].fileVOList[j].fileVOList[k].locationName+' </i>';
		        str+='</hgroup>';
		
				 str+=' </div>';
				 str+=' </div>';
				 str+='</div>';
				 str+=' </div>';
				 str+='</div>';
			}
			}
		}
		 str +='<br/><div class="form-actions text-center"><input type="button" value="submit" class="btn btn-info" id="savenewsReport" onclick="saveNewsReport()"/>';
		 str+='<img style="display: none;" src="images/search.jpg" id="savenewsAjaxImg"></div>';
		 $("#locationWiseNewsDiv").html(str);
	}else{
		
		$("#locationWiseNewsDiv").html("<span style='margin-left:353px;'>No News Exists With Your Search Criteria</span>");
		$("#locationWiseNewsDiv").show();
	}
}

function selectAllCheckBox()
{
	$("#unselectAll").attr("checked", false);
	$(".newscheckbox").each(function() {
	 $(this).attr("checked", true);
    });
}
function unselectAllCheckBox()
{
$("#selectAll").attr("checked", false);
$(".newscheckbox").each(function() {
	 $(this).attr("checked", false);
    });
}




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
			  	//$('#candidateAjaxImg').hide();
			 buildCandidatesForKeywords(myResults,jsObj.type);
			 //clearOptionsListForSelectElmtId('mainCategory');
			 //createOptionsForSelectElement('mainCategory',myResults);
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
function buildCandidatePhotoGallary(results)
{
	var str ='';

	
		str+='<div id="content" style="width:650px;">';
		
		//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
		str +='<table style="margin:5px;margin-left:auto;width:31%;margin-right:auto;margin-top:7px;">';
		str += '<tr>';
		str += '	<td><input type="button" class="btn btn-success highlight" value="Create Gallery" onclick="buildCreateGallaryDiv()"></td>';
		str += '	<td><input type="button" class="btn btn-success highlight" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
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

function buildCreateGallaryDiv()
{
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	/*str +='<table class="aligncenter">';
	str += '<tr>';
	str += '	<td><input type="button" class="btn btn-success highlight" value="Create Gallery" onclick="buildCreateGallaryDiv()"></td>';
	str += '	<td><input type="button" class="btn btn-success highlight" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
	str += '</tr>';
	str += '</table>';*/

	//str += '<fieldset class="imgFieldset"  style="width:540px;margin-left:auto;margin-right:auto;">';
	str += '<h2 align="center">Create A Gallery</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table class="aligncenter"><tr><td><div id="galErrorMsgDivId"></div></td></tr></table>';
	str += '<table class="aligncenter"><tr><td><b><font>Gallery Name<font class="requiredFont"> * </font></font></b></td><td><input type="text" id="pGallaryNameId" size="25" maxlength="100"></td></tr>';

	str += '<tr><td><b><font>Description</font><b></td>';
	str += '<td><textarea id="pGallaryDescId" cols="19" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<table class="aligncenter">';
	str+='<tr><td>';


	str += '<div><label class="radio"><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font>Visible to Public Also</font></b></input></label></div>';
	str+='</td></tr>';
	str+='<tr><td>';
	str += '<div><label class="radio"><input type="radio" value="private" name="visibility" id="privateRadioId"><b><font>Make This Private</font></b></input></label></div>';
	str+='</td></tr>';
	str += '</table>';
	str+='<div id="creategalAjax" style="display:none;margin-left:430px;clear:both;"><img src="images/search.jpg"/></div>';
	str += '<table class="aligncenter"><tr><td><input type="button" class="btn btn-success highlight" value="Create Gallery" onClick="createGallary(\'Photo Gallary\',\'Create\')"></td><td ><input type="button" class="btn btn-success highlight" value="Cancel" onclick="clearDiv(\'photoGallaryDiv\')"></td></tr></table>';


	//str += '</fieldset>';
	str+='</div>';
	document.getElementById("photoGallaryDiv").innerHTML = str;

}

function buildUploadPhotosDiv()
{
	var tempPartyId = 872; 
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	/*str +='<table class="aligncenter m_top10">';
	str += '<tr>';
	str += '	<td><input type="button" class="btn btn-success highlight" value="Create Gallery" onclick="buildCreateGallaryDiv()"></td>';
	str += '	<td><input type="button" class="btn btn-success highlight" value="Upload photos" onclick="buildUploadPhotosDiv()"></td>';
	str += '</tr>';
	str += '</table>';*/
	
	str += '<form name="uploadForm" action="uploadFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadFilesForm">';

	//str += '<fieldset class="imgFieldset" style="width:540px;">';
	str += '<h2 align="center">Upload A Photo</h2>';
	str += '<div id="gallaryCreateInnerDiv" style="margin-left:10px;margin-bottom:5px;"></div>';
	str += '<table class="aligncenter"><tr><td><div id="fileUploadErrorMsgDivId"></div></td></tr></table>';
	str += '<table class="aligncenter" style="margin-left:123px;left:50%;">';
	str += '<tr><td><b><font>Select Gallery</font></b></td><td><select onchange=" buildPhotoVisibility()" id="gallaryPhotoSelectId" name="gallaryId"><option value="0">Select</option></select></td></tr>';
	str += '<tr><td><b><font>Photo Title<font class="requiredFont">*</font></font></b></td><td><input type="text" id="photofileTitleId" name="fileTitle" size="25" maxlength="50"></td></tr>';

	str += '<tr><td><b><font>Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="photofileDescId" name="fileDescription" cols="19" rows="3" name="requirement"></textarea></td></tr>';
	str +='<tr><td><b><font>File Path<font class="requiredFont">*</font></font><b></td><td><input type="file" name="userImage" id="photofileId" /></td>';
	str +='<td class="selectWidthPadd"><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMorePhotos()" title="Click here to add more Photos" alt=""Click here to add more images""/></td></tr>';
	str +='<tr><td colspan="3"><div id="addMorePhotosDiv"></div></td></tr>';
	/*str += '   <TR>';
	str += '     <td colspan="2">';
	str += '<table>';
	str += '   <tr>';
	str += '     <td><b><font>File Date<font class="requiredFont">*</font></font></b></td>';
	str += '     <TD class="selectWidthPadd"><input type="text" id="existingFromText" class="dateField" readonly="true" name="fileDate" size="25"/>';
	str += '         <DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '     <TD>';
	str += '       <A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	str += '       <IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '     </TD>';
	str += '   </tr>';
	str += '</table>';
	str += '     </td>';
	str += '   </TR></table>';*/
	str += '<TR>';
	str += ' <td><b><font>File Date<font class="requiredFont">*</font></font></b></td>';
	str += '<TD style="padding-right: 31px;"><input type="text" id="existingFromTextPhoto" class="dateField" readonly="true" name="fileDate" size="20"/>';
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '<TD>';
	str += '<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	//str += '<IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>';
	str += '</TD>';
	str += '</TR></table>';
	str+='<table class="aligncenter">';
	str+='<tr><td>';
	str += '<div id="photoPublicRadioDiv" ><label class="radio"><input type="radio" value="public" name="visibility" id="PhotopublicRadioId" checked="true"><b><font>Visible to Public Also</font></b></input></label></div>';
	str+='</td></tr>';
	str+='<tr><td>';
	str += '<div id="photoPrivateRadioDiv" ><label class="radio"><input type="radio" value="private" name="visibility" id="photoprivateRadioId"><b><font>Make This Private</font></b></input></label></div>';
	str+='</td></tr>';
	str+='</table>';
	str +='<input type="hidden" name="profileType" value="party_profile">';
	str +='<input type="hidden" name="profileId" value="'+tempPartyId+'">';
	str +='<input type="hidden" name="profileGalleryType" value="photo_gallery">';
	//str+='<input type="radio" style="margin-left:57px;" onclick="otherProfiles(\'otherProPhotoDiv\',\'fromPartyProfile\',\'Photo Gallary\')"/>    Do you want to upload this file to other profiles';

	str+='<div id="otherProPhotoDiv" style="margin: 10px;"></div>'; 
	str+='<div id="uploadphotogalAjax" style="display:none;margin-left:430px;clear:both;"><img src="images/search.jpg"/></div>';
	str += '<table class="aligncenter"><tr><td><input type="button" id="uploadPhotoId" class="btn btn-success highlight" value="Upload Photo" onClick="uploadAFile()"></td><td><input type="button" class="btn btn-success highlight" value="Cancel" onclick="clearDiv(\'photoGallaryDiv\')"></td></tr></table>';
	str += '</form>';
	//str += '</fieldset>';
	str+='</div>';
	document.getElementById("photoGallaryDiv").innerHTML = str;
	getPartyGallariesForUplaod('Photo Gallary');
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
   str+='<input type="button" value="Back To Gallery"  class="btn btn-success highlight" onclick="showPhotoGallary();" />';
   
   str+= '</td></tr>';
   for(var i in results)
   {
     no_of_imagesPerRow = 3; 
     j = i;
     if(j++ % no_of_imagesPerRow == 0){
       str+= '<tr style="height:220px;">';
     }
     str+= '<td width="33%" class="imageStyle"><table class="tableStyle">';
	 
	 str +='<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;">';
	 if(results[i].source == 'Eenadu Telugu')
	  str += '<b class="enadu">'+results[i].fileTitle1+'</b>';
     else
       str += '<b>'+results[i].fileTitle1+'</b>';
	 str +='</font></div></td></tr>';

	 str+= '<tr><td><a rel="photo_gallery" href="'+results[i].path+'" title="'+results[i].fileDescription1+'"><img alt="" src="'+results[i].path+'" class="gallaryImg" height="100px" /></a></td></tr>';
	 
	 str += '<tr><td><div>';
	 if(results[i].source == 'Eenadu Telugu')
	  str += '<b class="enadu">'+results[i].fileDescription1+'</b>';
	 else
	  str += '<b>'+results[i].fileDescription1+'</b>';
	 str += '</div></td></tr>';

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

		/* var photoprivateRadioId = document.getElementById('photoprivateRadioId').checked;
		if(photoprivateRadioId == true)
		document.getElementById('photoprivateRadioId').checked= 'true';
		if(photoprivateRadioId == false)
		document.getElementById('PhotopublicRadioId').checked= 'true';*/

		$("#keywordListId1").autoSuggest(data.items, {selectedItemProp: "name", searchObjProps: "name"});


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
 function uploadNewsFromPartyPage()
{
	document.getElementById('uploadNewsBtnId');
	if(validateNewsFileUpload())
	{
		$("#uploadnewsgalAjax").show();
		disableButton('uploadNewsBtnId');
	var newsprivateRadioId = document.getElementById('newsprivateRadioId').checked;
		if(newsprivateRadioId == true)
	document.getElementById('newsprivateRadioId').checked = 'true';
		if(newsprivateRadioId == false)
	document.getElementById('newsPublicRadioId').checked = 'true';

    
    var keywordStr = "";
	$(".as-selections li").each(function(){
              keywordStr +=$(this).text()+",";
				
            });
   
   var length = keywordStr.length;
    keywordStr = keywordStr.substr(0,length-2); 
	$("#keywordListId").val(keywordStr);
    
		var uploadHandler = {
				upload: function(o) {
					$("#uploadnewsgalAjax").hide();

					

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

function validateNewsFileUpload()
{
	var fileTitle = document.getElementById('newsfileTitle').value;
	var fileDesc = document.getElementById('newsfileDescription').value;
	var fileVal = document.getElementById("newsfileId").value;
	var source = document.getElementById("source").value;
	var languageId = document.getElementById("language").value;
	//var keywords = document.getElementById("keywords").value;
	//var galEle = document.getElementById("gallaryId").value;
	var fileDate = document.getElementById("existingFromTextNews").value;
	var scope = document.getElementById("scopeDiv").value;
	
	//var pageNo = document.getElementById("pageno").value;
	//var pageNo = document.getElementById("newslength").value;
	var flag = true;

	fileTitle = removeAllUnwantedCharacters1(fileTitle);	
	fileDesc = removeAllUnwantedCharacters1(fileDesc);
	document.getElementById('newsfileTitle').value = fileTitle;
	document.getElementById('newsfileDescription').value = fileDesc;

	var errorDivEle = document.getElementById('uploadNewsFileErrorDiv1');
	var str = '<font color="red">';
	/*if(galEle == 0)
	{
		str += 'Select Gallery<br>';
		flag = false;
	}*/
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
	 $('.pageno').each(function() {
           if($.trim($(this).val()).length == 0)
	       {
		     str += ' Page Number is Required.<br>';
		     flag = false;
			 return false;
	       }
       });
	$('.pageno').each(function() {
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
	if(fileTitle.length >1000)
	{
		str += 'Title should be less than 1000 Characters<br>';
		flag = false;
	}
	if(fileDesc.length == 0)
	{
		str += 'News Description is Required.<br>';
		flag = false;
	}
	if(fileDesc.length > 2000)
	{
		str += 'Description Should not exceed 2000 Characters.<br>';
		flag = false;
	}
	if(fileVal.length == 0)
	{
		str += 'File Path is Required.<br>';
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
	/*if(keywords.length >200)
	{
		str += 'Keywords should be less than 200 Characters<br>';
		flag = false;
	}*/
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
	if(flag == false)
	{
		 $("#uploadNewsBtnId").css("background","#51A351");
		$('html, body').animate({
         scrollTop: $("#uploadNewsFileErrorDiv1").offset().top
     }, 2000);
	
	}
	else
	 $("#uploadNewsBtnId").css("background","#BBBB51");
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
   var strng = str.replace(/[\\\%\&\#\"+"]/g," ");
  
   return replaceEnterKey(strng,"  ");
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
		//clearNewsUploadFileFields1();
		//str += '<font color="green"><b>News Uploaded Successfully.</b>';
        
		addSource = 0;
		addFile = 0;
		who = 0;
		whome = 0;
		//errorDivEle.innerHTML = str;
		uploadNewsForPartyAndCandidate(null);
		
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

	 $("#keywordListId1").autoSuggest(data.items, {selectedItemProp: "name", searchObjProps: "name"});


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
	getScopes();

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

function buildUploadNewsForMultipleUsers()
{
	
   var tempPartyId = 872;
   var str ='';
	str+='<div id="content" style="width:650px;">';
	//str +=  '<table style="margin:5px;width:40%;margin-left:50px;">';
	/*str +='<table>';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="imageButton" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News" onclick="buildUploadNews()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News For Multiple Users" onclick="buildUploadNewsForMultipleUsers()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';*/
	//str += '<fieldset class="imgFieldset" style="width:504px;">';
	str += '<form name="uploadForm1" action="uploadFilesForMultipleCandidates.action" enctype="multipart/form-data"  method="post" id="uploadNewsForm">';
	str += '<h2 align="center">Upload A News</h2>';
	str += '<table class="aligncenter"><tr><td><div id="uploadNewsFileErrorDiv" /></td></tr></table>';

     str+='<div class="aligncenter" style="margin-left:-45px;">';
     //str+='<a style="margin-left:46px;font-weight:bold;color:blue;" href="javascript:void(0);" id="responseDiv">Click Here To Respond  To News</a>'; 

	 str+='<a href="javascript:void(0)" title="Click here to respond to news"  id="responseDiv" style="margin-left:586px;"><img id="" src="images/responseImage.png"/></a>';


	/*str+='<table class="aligncenter" style="margin-left:123px;left:50%;">';
     
    str+='<td><div id="responseContentDiv" style="display:none;">';
	str+='<table> <tr><td class="tdWidth1">Select Candidate:</td><td class="selectWidthPadd"><select id="candidatesList" onChange="getCandidateNews(this.value)"></select></td></tr>';

	str+='<tr><td class="tdWidth1"></td><td class="selectWidthPadd"><select id="candidateNewsList"  multiple="true" style="display:none;"></select></td></tr>';

	str+='<tr><td class="tdWidth1"></td><td class="selectWidthPadd"><div style="display:none;" id="buttonsDiv"><input type="button" id="addFile" value="Add"/>';
	str+='<input type="button" value="Remove" id="deleteFile"/></div></td></tr>';


	str+='<tr><td class="tdWidth1"></td><td class="selectWidthPadd"><select id="respenseNewsList" name="responseFileIds" multiple ="true" style="display:none;"></select></td></tr></table>';


	 
	str+='</div></td>';
	str+='</table>';*/
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

		 //str+='<div id="noNewsError"></div>';
         
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

		 /* str +='<div id="categoryGallaryShowHideDiv" style="display:none;text-align:center;">';
		 str +='<select id="categoryGallarySelect"></select>';
		 str +='</div>'; */


	     str+='<div style="text-align:center;">';
		 str+='<div style="clear:both;"><select id="candidateNewsList"  multiple="true" style="display:none;"></select></div>';

		 /*str +='<div id="gallaryShowHideDiv" style="display:none;"><select id="gallaryList"></select></div>';*/
		 

	     str+='<div style="display:none;" id="buttonsDiv"><input type="button" id="addFile" value="Add"/>';
		 str+='<input type="button" value="Remove" id="deleteFile"/>';
	   str+='</div>';

	   str+='<div>';
	    str+='<select id="respenseNewsList" name="responseFileIds" multiple ="true" style="display:none;"></select>';
	   str+='</div>';
	   str+='</div>';
	   
	str+='</div>';
	str += '<table class="aligncenter" style="margin-left:123px;left:50%;">';
	
	/*str += '   <tr>';
	str += '       <td class="tdWidth1">Category : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="category" name="category" onchange="getGallariesForSelectedCategory()"><option value="0">Select Category</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += ' <td class="tdWidth1">Select Gallery : </td><td class="selectWidthPadd"><select onchange="buildPartyNewsVisibility()" id="gallaryId" name="gallaryId"/></select></td>';
	str += '   </tr>';*/

       //keyworda Start
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
	str += '       <td class="selectWidthPadd"><input type="text" id="newsfileTitle" name="fileTitle" size="25" maxlength="160"></input></td>'; 
	str += ' <td><a href="javascript:{changeLanguage();}" id="sourceTelugu"><img id="" src="images/letter-t-video.png"  style="height:35px;width:35px;" alt="Select Telugu language" title="Eenadu Telugu Language"/></a> </td>';
	str += ' <td><a href="javascript:{changeLanguage();}" id="sourceEnglish" style="display:none"><img src="images/e-letter.jpg" style="height:35px;width:35px;" title="English Language"></img></a></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Description : <font class="requiredFont">*</font></td>';
	str += '       <td class="selectWidthPadd"><textarea id="newsfileDescription" cols="20" rows="3" name="fileDescription" maxlength="330"></textarea></td>';
	str += '   </tr>';
	
	/*str += '   <tr>';
	str += '       <td class="tdWidth1">Keywords : </td>';
	str += '       <td class="selectWidthPadd"><input type="text" id="keywords" name="keywords" size="25" maxlength="200"></input></td></tr>'; */

	str += '<TR>';
	str += ' <td><b><font>File Date<font class="requiredFont">*</font></font></b></td>';
	str += '<TD style="padding-right: 31px;"><input type="text" id="existingFromTextNews" class="dateField" readonly="true" name="fileDate" size="20" />';
	
	str += '<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>';
	str += '<TD>';
	str += '<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">';
	
	str += '</TD>';
	str += '</TR>';
	
	/*str += '   </tr>';
	str += '       <td class="tdWidth1">Category : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="category" name="category" ><option value="0">Select Category</option></select></td>';
	str += '   </tr>';*/
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
	//str +='<tr><td class="tdWidth1"></td><td><input type="button" id="button1" value="Add"/><input type="button" id="button2" value="Remove"/></td></tr>';
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
	//getPartyGallariesForUplaod("News Gallary");
	
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
  $("#keywordListId1").autoSuggest(data.items, {selectedItemProp: "name", searchObjProps: "name"});

}


function buildCreateNewsCategory()
{
   var str ='';
	str+='<div id="content" style="width:650px;">';
	//str +=  '<table style="margin:5px;width:40%;margin-left:50px;">';
	/*str +='<table class="aligncenter">';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="btn btn-success highlight" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="btn btn-success highlight" value="Upload News" onclick="buildUploadNews()"></td>';
	str += '<td><input type="button" class="imageButton" value="Upload News For Multiple Users" onclick="buildUploadNewsForMultipleUsers()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';*/
	//str += '<fieldset class="imgFieldset" style="width:449px;">';
	str += '<h2 align="center">Create A News Gallary</h2>';
	str+='<table class="aligncenter"><tr><td><div id="newsErrorMsgDivId" /></td></tr></table>';
	str += '<table class="aligncenter">';
    
    str +='<tr><td><b><font>Category Name<font class="requiredFont">*</font></font></b></td><td><select id="categoriesForGallary" name="categoriesForGallary"></td></tr>';
	str +='<tr><td><b><font>Gallary Name<font class="requiredFont">*</font></font></b></td><td><input type="text" id="newsCateName" size="25" maxlength="100" /></td></tr>';

	str += '<tr><td><b><font>Description<font class="requiredFont">*</font></font><b></td>';
	str += '<td><textarea id="newsCateDesc" cols="27" rows="3" name="requirement"></textarea></td></tr></table>';
	str += '<table class="aligncenter"><tr><td><label class="radio"><input type="radio" value="public" name="visibility" id="newsPublicRadio" checked="true"><b><font>Visible to Public Also</font></b></input></label></td></tr>';
	str += '<tr><td><label class="radio"><input type="radio" value="private" name="visibility" id="newsPrivateRadio"><b><font>Make This Private</font></b></input></label></td></tr></table>';
	str+='<div id="createnewsgalAjax" style="display:none;margin-left:430px;clear:both;"><img src="images/search.jpg"/></div>';
	str += '<table class="aligncenter"><tr><td><input type="button" class="btn btn-success highlight" value="Create Gallary" style="background-color:#57B731" onClick="createCategory()"></td><td><input type="button" class="btn btn-success highlight" value="Cancel"  onClick="clearDiv(\'newsGallaryDiv\');"></td></tr></table>';

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
function buildResults(results,divId){
	$('#'+divId).find('option').remove();

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

	  if(divId == "mandalDiv")
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
function getAllDetails1(id,task,areaType,constId)
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
            type:'forEdit'
		};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "locationsHierarchiesAjaxAction.action?"+rparam;						
	 callnewAjax(jsObj,url);
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

 function buildSearchNewsDetails(){
 var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td style="color:#4B74C6;font-weight:bold;">Location Scope :</td>';
  str +='	   <td style="width:172px;padding-left:10px;"><select id="scopeDiv" name="locationScope" class="selectWidth" onchange="getLocations(this.options[this.selectedIndex].value)"  /></td>';
  str +='  </tr>';
  str +='</table>';
  str +='  <span id="showScopeSubs" />'; 

 document.getElementById("uploadNewsDiv").innerHTML = str;
 getScopes();
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

/*function getLocations(id){

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
  str +='	    <td class="tdWidth1">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDiv" name="locationValue"/></td>';
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
  str +='	   <td class="tdWidth1">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDiv" onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)" /></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1">District : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDiv" name="locationValue"><option value="0">Select Location</option></select></td>';
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
  str +='	   <td class="tdWidth1">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDiv" onchange="clearAllElmts(4,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1"> District : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDiv" onchange="clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1">Assembly Constituency : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="constituencyDiv" name="locationValue" ><option value="0">Select Location</option></select></td>';
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
  str +='	   <td class="tdWidth1">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDiv" onchange="clearAllElmts(5,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1">District : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDiv" onchange="clearAllElmts(5,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1">Assembly Constituency : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="constituencyDiv"    onchange="clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1">Mandal/ Municipality/ Corp/GMC : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="mandalDiv" name="locationValue" ><option value="0">Select Location</option></select></td>';
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
  str +='	   <td class="tdWidth1">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDiv"    onchange="clearAllElmts(6,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1">District : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDiv"   onchange="clearAllElmts(6,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1">Assembly Constituency : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="constituencyDiv"  onchange="clearAllElmts(6,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1">Mandal/ Municipality/ Corp/GMC : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="mandalDiv"    onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1">Village/Ward/Division : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="villageDiv" name="locationValue"  ><option value="0">Select Location</option></select></td>';
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
  str +='	   <td class="tdWidth1">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDiv" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/ ></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1">District : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDiv"  onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1">Assembly Constituency : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="constituencyDiv" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1">Mandal/ Municipality/ Corp/GMC : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="mandalDiv" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'boothsInTehsilOrMunicipality\',\'\',document.getElementById(\'constituencyDiv\').options[document.getElementById(\'constituencyDiv\').selectedIndex].value)"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth1">Village/Ward/Division : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="villageDiv" name="locationValue"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
  }
}*/


function getLocations(id){

   var str = '';
  if(id==0 || id == 1)
   $("#showScopeSubs").html(str);
    
  else if(id==2)
  {

   str += '<div class="span2">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" name="locationValue" style="margin-left:5px;width: 137px;"  id="stateDiv"></select>';
   str +='</div>';

   $("#showScopeSubs").html(str);
   getStatesForSpecialPage();
  }
  else if(id==3)
  {
   
   str += '<div class="span2">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv" style="margin-left:5px;width: 137px;" onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" name="locationValue" id="districtDiv"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   $("#showScopeSubs").html(str);
   getStatesForSpecialPage();
  }
  else if(id==4)
  {
   
   str += '<div class="span2">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv" style="margin-left:5px;width: 137px;" onchange="clearAllElmts(4,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" id="districtDiv" onchange="clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select>';
   str +='</div>';
  
   str += '<div class="span2">';
   str += ' <label>Assembly Consti</label>';
   str += ' <select class="input-block-level" name="locationValue" id="constituencyDiv"><option value="0">Select Location</option></select>';
   str +='</div>';

   $("#showScopeSubs").html(str);
  
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
   
   str += '<div class="span2">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv" style="margin-left:5px;width: 137px;" onchange="clearAllElmts(5,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" id="districtDiv" onchange="clearAllElmts(5,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select>';
   str +='</div>';
  
   str += '<div class="span2">';
   str += ' <label>Assembly Consti</label>';
   str += ' <select class="input-block-level" id="constituencyDiv" onchange="clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   str += '<div class="span2">';
   str += ' <label>Mndl/Munic/Corp/GMC</label>';
   str += ' <select class="input-block-level" name="locationValue" id="mandalDiv"><option value="0">Select Location</option></select>';
   str +='</div>';

   $("#showScopeSubs").html(str);
  
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

   str += '<div class="span2">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv" style="margin-left:5px;width: 137px;" onchange="clearAllElmts(6,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" id="districtDiv" onchange="clearAllElmts(6,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select>';
   str +='</div>';
  
   str += '<div class="span2">';
   str += ' <label>Assembly Consti</label>';
   str += ' <select class="input-block-level" id="constituencyDiv" onchange="clearAllElmts(6,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select>';
   str +='</div>';
   
   str += '<div class="span2">';
   str += ' <label>Mndl/Munic/Corp/GMC</label>';
   str += ' <select class="input-block-level" id="mandalDiv" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\')"><option value="0">Select Location</option></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>Village/Ward/Division</label>';
   str += ' <select class="input-block-level" id="villageDiv" name="locationValue"><option value="0">Select Location</option></select>';
   str +='</div>';
   $("#showScopeSubs").html(str);
   
   getStatesForSpecialPage();
  }
  else if(id==9)
  {

   str += '<div class="span2">';
   str += ' <label>State</label>';
   str += ' <select class="input-block-level" id="stateDiv" style="margin-left:5px;width: 137px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>District</label>';
   str += ' <select class="input-block-level" id="districtDiv" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select>';
   str +='</div>';
  
   str += '<div class="span2">';
   str += ' <label>Assembly Consti</label>';
   str += ' <select class="input-block-level" id="constituencyDiv" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'\',\'\')"><option value="0">Select Location</option></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>Mndl/Munic/Corp/GMC</label>';
   str += ' <select class="input-block-level" id="mandalDiv" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'boothsInTehsilOrMunicipality\',\'\',document.getElementById(\'constituencyDiv\').options[document.getElementById(\'constituencyDiv\').selectedIndex].value)"><option value="0">Select Location</option></select>';
   str +='</div>';

   str += '<div class="span2">';
   str += ' <label>Village/Ward/Division</label>';
   str += ' <select class="input-block-level" id="villageDiv" name="locationValue"><option value="0">Select Location</option></select>';
   str +='</div>';
   $("#showScopeSubs").html(str);
    
   getStatesForSpecialPage();
  }
}


function addMoreFiles()
{
	var moreDivElmt = document.createElement("addMoreFilesDiv");
	var str ='';
	str +='<table style="background:#e3e3e3;border-radius:9px;padding:5px;margin-top:12px;" id="moreFileTableId'+fileCount+'">';
	str += ' <tr>';
	str +='<td>File Path</td>';
	str += ' <td class="selectWidthPadd"><input type="file" name="userImage" id="newsFileId'+fileCount+'" size="25" /></td>';
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
function showOrHideVideoFilesDiv()
{
	
document.getElementById("profileManagementMainOuterDiv2").style.display = 'block';
  document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
  document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';
}

function showVideoGallaey1()
{
	
if(!formValidation()){
  $("#profileManagementHeaderDiv2").css("display","block");
  $("#videoGallaryDiv").css("display","block");
  $("#profileManagementMainOuterDiv5").css("display","none");
document.getElementById("profileManagementMainOuterDiv1").style.display = 'none';
document.getElementById("profileManagementMainOuterDiv2").style.display = 'block';
document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';
document.getElementById("profileManagementMainOuterDiv4").style.display = 'none';
document.getElementById("profileManagementMainOuterDiv6").style.display = 'none';
 $("#profileManagementMainOuterDiv7").css("display","none");
/*$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsEditId").css({"background":"none repeat scroll 0 0 #0063DC"});*/

$("#dateSelectDiv").css("display","none");
buildCreateVideoGallaryDiv();

}
return;
}
	

function buildCreateVideoGallaryDiv()
{
	var str ='';
	str+='<div id="content" style="width:650px;">';
		
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	/*str +='<table class="aligncenter">';
	str += '<tr>';
	str += '	<td><input type="button" class="btn btn-success highlight" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()"></td>';
	str += '	<td><input type="button" class="btn btn-success highlight" value="Upload Video" onclick="buildUploadVideoDiv()"></td>';
	str += '</tr>';
	str += '</table>';*/

	//str += '<fieldset class="imgFieldset" style="width:449px;">';
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
		
	//str += '<table style="margin:5px;width:40%;margin-left:50px;">';
	/*str +='<table class="aligncenter m_top10">';
	str += '<tr>';
	str += '	<td><input type="button" class="btn btn-success highlight highlight" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()"></td>';
	str += '	<td><input type="button" class="btn btn-success highlight highlight" value="Upload Video" onclick="buildUploadVideoDiv()"></td>';
	str += '</tr>';
	str += '</table>';*/
	//str += '<fieldset class="imgFieldset" style="width:504px;">';
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
    /*var jsObj =
		{ 
            time : timeST,
		    gallaryId:gallaryId,
			task:"getPhotosInAGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);*/
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
	//var url = "createNewGallaryAction.action?"+rparam;
	var url = "deletePartyGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	
	}
}

function updateGallary(gallaryId)
 {
  var r=confirm("Do you want to update!");
 if (r==true)
    {
	//var candidateId=document.getElementById("candidateId").value;
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
	/*if(myResults.fileName1 != 'false')
	document.getElementById("privateRadioId").checked= true;
	else
	document.getElementById("publicRadioId").checked= true;*/
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
		/*if(myResults.file != 'false')
		document.getElementById('privateRadioId').checked = true;
		else 
		document.getElementById('publicRadioId').checked = true;*/
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

	/*if(isPublic)
		makeThis = 'false';*/
	
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
 /* $("#photoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
  $("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
  $("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
  $("#newsEditId").css({"background":"none repeat scroll 0 0 #F61D50"});*/
  
  $("#dateSelectDiv").css("display","block");
  $("#newsFromDateId").val('');
  $("#newsToDateId").val('');
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

					//clearOptionsListForSelectElmtId('gallaryId');
					//createOptionsForSelectElmtId('gallaryId',myResults);

					//$("#gallaryId").prepend("<option value='0'>Select Gallery</option>");
					//document.getElementById("gallaryId").value = 0;
						//$('#gallaryId').val(reqFile.contentId);

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
			   //clearOptionsListForSelectElmtId("scopeDiv");
              // buildResults(myResults,"scopeDiv");
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

			//if(results[i].source.indexOf("Eenadu Telugu") != -1)
			if(results[i].eenaduTeluguFontStr != null && results[i].eenaduTeluguFontStr == "Eenadu Telugu")
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

	/*str+='<div style="float:right;"><div style="float:left;"><b>Flag Status:</b><input type="hidden" name="flagValue" id="flagInd" value="'+reqFile.flagSet+'"></input></div>';
      if(reqFile.flagSet == "true"){
		  str+='<div id="flagSet" style="width: 130px;"><a href=javaScript:{setFlagVariable(\'false\');}   ><i class="icon-flag"></i></a></div>';

		  str+='<div id="flagUnSet" style="display:none;width: 130px;"><a href=javaScript:{setFlagVariable(\'true\');}  class="unflagClass" ><i class="icon-flag"></i></a></div>';
	  }
	  else{
		    str+='<div id="flagSet" style="display:none;width: 130px;"><a href=javaScript:{setFlagVariable(\'false\');}><i class="icon-flag"></i></a></div>';

		   str+='<div id="flagUnSet" style="width: 130px;"><a href=javaScript:{setFlagVariable(\'true\')}  class="unflagClass" ><i class="icon-flag"></i></a></div>';
		 // str+='';
	  }
	  str+='<div style="clear: both" ></div>';
	  str+='</div>';

	str+='<div>';*/

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

	/*
		str += '   <tr>';
		str += '       <td class="tdWidth">File Path<font class="requiredFont">*</font></td>';
		str += '       <td class="selectWidthPadd"><input type="file" name="userImage" id="newsFileId" size="25" style="margin-top:8px;"/></td>';
		str += '       <td class="selectWidthPadd" style="padding-left:24px;"><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMoreFiles()" title="Click here to add more images" alt=""Click here to add more images""/></td>';
		str += '   </tr>';
	*/

	str += '   <tr>';
	str += '      <td colspan="2"> <div id="addMoreFilesDiv" style="margin-left:107px;"></div></td>';
	str += '   </tr>';

	/*
		str +='<tr><td class="tdWidth" style="width:97px;">Image To Display:</td><td><input type="file" name="imageForDisplay" id="newsfileId" size="25" style="margin-top:8px;"/></td></tr>';
	*/
	//str += '   <tr>';


	str += '   <tr>';
	str += '       <td></td>';

	//Here visibility means isPrivate
	if(reqFile.visibility == "false")

		//str += '<td id="newsPublicRadioId"><label><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><b><font id="visiblePublicText" color="#4B74C6">Visible to Public Also</font></b></input></label></td>';

		str += '<td id="newsPublicRadioId"><label><input type="radio" value="public" name="visibility" id="publicRadioId" checked="true"><i  class="icon-bullhorn" ></i></input></label></td>';
	else

		//str += '       <td id="newsPublicRadioId"><label><input type="radio" value="public" name="visibility" id="publicRadioId"><b><font id="visiblePublicText" color="#4B74C6">Visible to Public Also</font></b></input></label></td>';

		str += '<td id="newsPublicRadioId"><label><input type="radio" value="public" name="visibility" id="publicRadioId"><i  class="icon-bullhorn" ></i></input></label></td>';

    str += '   </tr>';
	str += '   <tr>';
	str += '       <td></td>';

	if(reqFile.visibility == "true")

	 // str += '       <td id="newsPrivateRadioId"><label><input type="radio" value="private" name="visibility" checked="true" id="privateRadioId"><b><font id="visiblePrivateText" color="#4B74C6">Make This Private</font></b></input></label></td>';
	  str += '<td id="newsPrivateRadioId"><label><input type="radio" value="private" name="visibility" checked="true" id="privateRadioId"></input><i  class="icon-lock" ></i></label></td>';
   else
	  // str += '       <td id="newsPrivateRadioId"><label><input type="radio" value="private"  name="visibility" id="privateRadioId"><b><font id="visiblePrivateText" color="#4B74C6">Make This Private</font></b></input></label></td>';

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
		
	   
		//document.getElementById("existingFromText").value = reqFile.fileDate;
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
/*if(returnedResults != undefined){
	modifiedRecord = returnedResults.fileGallaryId;
}*/

   // document.getElementById("newsDeleteMessage").innerHTML = "";
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
//var candidateId=document.getElementById("candidateId").value;
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
		//$('#'+divId).append(new Option("Select Scope", "0"));

		for(var i in results){			

			$('#'+divId).append('<option value='+results[i].locationScope+'>'+results[i].locationScopeValue+'</option>');
		}

		if(reqFile.locationScope != null && reqFile.locationScope != "null")
		$('#'+divId).val(reqFile.locationScope);
		//getLocations(reqFile.locationScope);

	}


	if(divId == "stateDiv" || divId == "stateDivForEdit"){
		//$('#'+divId).append(new Option("Select State", "0"));

		for(var i in results){

		$('#'+divId).append('<option value='+results[i].ids+'>'+results[i].names+'</option>');
	    }
	}

	if(divId == "districtDiv" || divId == "districtDivForEdit"){

		//$('#'+divId).append(new Option("Select District", "0"));

		for(var i in results){

		$('#'+divId).append('<option value='+results[i].ids+'>'+results[i].names+'</option>');
	    }
	}
   
    if(divId == "constituencyDiv" || divId == "constituencyDivForEdit"){

		//$('#'+divId).append(new Option("Select Constituency", "0"));

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

	//$('#'+divId).append(new Option("Select Village", "0"));

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
function getLocations1(id){
   if(id==0)
  {
   var val ='';
  val +='<table>';
  val +='  <tr><td></td>';
  val +='  </tr>';
  val +='</table>';
  document.getElementById("showScopeSubsForEdit").innerHTML = val;
    
  }
  else if(id==1)
  {
    var str ='';
  str +='<table>';
  str +='  <tr><td></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubsForEdit").innerHTML = str;
  }
  else if(id==2)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDivForEdit" name="locationValue" class="selectWidth" style="width: 222px; margin-left: -4px;"/></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubsForEdit").innerHTML = str;
   getStatesForLocationScope1();
  }
  else if(id==3)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDivForEdit" class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="getDistricts2(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">District : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDivForEdit" class="selectWidth" name="locationValue"style="width: 222px; margin-left: -4px;"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubsForEdit").innerHTML = str;
   getStatesForLocationScope1();
  }
  else if(id==4)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDivForEdit" class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="clearAllElmts(4,1);getDistricts2(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">District : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDivForEdit" class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="getAllDetails1(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Assembly Consti : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="constituencyDivForEdit" name="locationValue"style="width: 222px; margin-left: -4px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubsForEdit").innerHTML = str;
   getStatesForLocationScope1();
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
  str +='	   <td class="tdWidth" style="width: 162px;">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDivForEdit"style="width: 222px; margin-left: -4px;"  class="selectWidth" onchange="clearAllElmts(5,1);getDistricts2(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">District : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDivForEdit"style="width: 222px; margin-left: -4px;" class="selectWidth" onchange="clearAllElmts(5,2);getAllDetails1(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Assembly Consti : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="constituencyDivForEdit"style="width: 222px; margin-left: -4px;" class="selectWidth" onchange="getAllDetails1(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Mndl/Munic/Corp/GMC : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="mandalDivForEdit" name="locationValue"style="width: 222px; margin-left: -4px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubsForEdit").innerHTML = str;
   getStatesForLocationScope1();
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
  str +='	   <td class="tdWidth" style="width: 162px;">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDivForEdit"  class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="clearAllElmts(6,1);getDistricts2(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">District : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDivForEdit"  class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="clearAllElmts(6,2);getAllDetails1(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Assembly Consti : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="constituencyDivForEdit"  class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="clearAllElmts(6,3);getAllDetails1(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Mndl/Munic/Corp/GMC : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="mandalDivForEdit"  class="selectWidth"style="width: 222px; margin-left: -4px;" onchange="getAllDetails1(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Village/Ward/Division : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="villageDivForEdit" name="locationValue"style="width: 222px; margin-left: -4px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubsForEdit").innerHTML = str;
   getStatesForLocationScope1();
  }
  else if(id==9)
  {
     var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="stateDivForEdit" class="selectWidth"style="width: 222px; margin-left: -4px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,1);getDistricts2(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">District : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDivForEdit" class="selectWidth"style="width: 222px; margin-left: -4px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,2);getAllDetails1(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Assembly Consti : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="constituencyDivForEdit" class="selectWidth"style="width: 222px; margin-left: -4px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,3);getAllDetails1(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  //str +='	   <td class="tdWidth" style="width: 162px;">Mandal/ Municipality/ Corp/GMC<font class="requiredFont">*</font></td>';
  //str +='	   <td class="selectWidthPadd"><select id="mandalDivForEdit" class="selectWidth" style="width: 222px; margin-left: -4px;" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="getAllDetails1(this.options[this.selectedIndex].value,\'boothsInTehsilOrMunicipality\',\'\',document.getElementById(\'constituencyDiv\').options[document.getElementById(\'constituencyDiv\').selectedIndex].value)"><option value="0">Select Location</option></select></td>';

   str +='	   <td class="tdWidth" style="width: 162px;">Mndl/Munic/Corp/GMC : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="mandalDivForEdit" onchange="getAllDetails1(this.options[this.selectedIndex].value,\'boothsInTehsilOrMunicipality\',\'\',document.getElementById(\'constituencyDivForEdit\').options[document.getElementById(\'constituencyDivForEdit\').selectedIndex].value)"><option value="0">Select Location</option></select></td>';

  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 162px;">Village/Ward/Division : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="villageDivForEdit" name="locationValue"style="width: 222px; margin-left: -4px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubsForEdit").innerHTML = str;
   getStatesForLocationScope1();
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

function updateDeleteNews(task,fileId){

var fileTitle = $('#fileTitle').val();
var fileDescription = $('#fileDescription').val();
var flagInd = $('#flagInd').val();
var scope = $('#scopeDiv').val();
var errorMessage = "";
var validate = false;
var newsDescription = "";

if(fileTitle == ""){
 errorMessage+="File title is required<br>";
 validate = true;
}

if(fileDescription == ""){
	errorMessage+="File description is required";
	validate = true;
}
if(bvalue){
if(scope == 3)
	{
		var stateVal=document.getElementById('stateDivForEdit').value;
		var districtVal=document.getElementById('districtDivForEdit').value;
		if(stateVal == 0)
		{
		errorMessage += 'State Name is Required.<br>';
		validate = true;
		}
		if(districtVal == 0)
		{
		errorMessage += 'District Name is Required.';
		validate = true;
		}
	}

	if(scope == 4)
	{
		var stateVal=document.getElementById('stateDivForEdit').value;
		var districtVal=document.getElementById('districtDivForEdit').value;
		var constituencyVal=document.getElementById('constituencyDivForEdit').value;
		if(stateVal == 0)
		{
		errorMessage += 'State Name is Required.<br>';
		validate = true;
		}
		if(districtVal == 0)
		{
		errorMessage += 'District Name is Required.<br>';
		validate = true;
		}
		if(constituencyVal == 0)
		{
		errorMessage += 'Constituency Name is Required.';
		validate = true;
		}
	}
	if(scope == 5)
	{
		var stateVal=document.getElementById('stateDivForEdit').value;
		var districtVal=document.getElementById('districtDivForEdit').value;
		var constituencyVal=document.getElementById('constituencyDivForEdit').value;
		var mandalVal=document.getElementById('mandalDivForEdit').value;
		if(stateVal == 0)
		{
		errorMessage += 'State Name is Required.<br>';
		validate = true;
		}
		if(districtVal == 0)
		{
		errorMessage += 'District Name is Required.<br>';
		validate = true;
		}
		if(constituencyVal == 0)
		{
		errorMessage += 'Constituency Name is Required.<br>';
		validate = true;
		}
		if(mandalVal == 0)
		{
		errorMessage += 'Mandal Name is Required.';
		validate = true;
		}
	}
	if(scope == 6 || scope == 8 || scope == 9)
	{
		var stateVal=document.getElementById('stateDivForEdit').value;
		var districtVal=document.getElementById('districtDivForEdit').value;
		var constituencyVal=document.getElementById('constituencyDivForEdit').value;
		var mandalVal=document.getElementById('mandalDivForEdit').value;
		var villageVal=document.getElementById('villageDivForEdit').value;
		if(stateVal == 0)
		{
		errorMessage += 'State Name is Required.<br>';
		validate = true;
		}
		if(districtVal == 0)
		{
		errorMessage += 'District Name is Required.<br>';
		validate = true;
		}
		if(constituencyVal == 0)
		{
		errorMessage += 'Constituency Name is Required.<br>';
		validate = true;
		}
		if(mandalVal == 0)
		{
		errorMessage += 'Mandal Name is Required.<br>';
		validate = true;
		}
		if(villageVal == 0)
		{
		errorMessage += 'Villiage Name is Required.';
		validate = true;
		}
	}
	if(scope == 7)
	{
		var stateVal=document.getElementById('stateDivForEdit').value;
		var districtVal=document.getElementById('districtDivForEdit').value;
		var constituencyVal=document.getElementById('constituencyDivForEdit').value;
		var mandalVal=document.getElementById('mandalDivForEdit').value;
		
		if(stateVal == 0)
		{
		errorMessage += 'State Name is Required.<br>';
		validate = true;
		}
		if(districtVal == 0)
		{
		errorMessage += 'District Name is Required.<br>';
		validate = true;
		}
		if(constituencyVal == 0)
		{
		errorMessage += 'Constituency Name is Required.<br>';
		validate = true;
		}
		if(mandalVal == 0)
		{
		errorMessage += 'Mandal Name is Required.';
		validate = true;
		}
		
	}
}
if(validate == true) {
$('#uploadNewsFileErrorDiv').html('<span style="color:red;">'+errorMessage+'</span>');
validate = false;
return false;
}

//document.getElementById("newsDeleteMessage").innerHTML = "";	
 var timeST = new Date().getTime();
 

   var title ="";
   var description ="" ;
   var sourceId =""  ;
   var languegeId ="" ;
   var categoryId ="" ;
   var newsImportanceId ="" ;
   var sourceString ='';
   var languageString = '';

   //CHANGE BY SAMBA START

   var gallaryId = "";
   var keywords = "";
   var fileDate = "";
   var locationScopeId = "0";
   var locationScopeValue = "";
   var visibility = "";
   var fileGallaryId  = 0;
   //CHANGE BY SAMBA END

  if(task == "Update")
  {
   title  = document.getElementById("fileTitle").value;
   description  = document.getElementById("fileDescription").value;
    //changes by anil
	title = htmlEntity(title);
	description = htmlEntity(description);
    //CHANGE BY SAMBA START

	visibility = $('input:radio[name=visibility]:checked').val();

   gallaryId = document.getElementById("gallaryIdForEdit").value;
   keywords = document.getElementById("keywordsForEdit").value;
   fileDate = document.getElementById("existingFromText").value;
   locationScopeId = document.getElementById("scopeDivForEdit").value;
   fileGallaryId = $('#fileGallaryId').val();
   newsDescription = $('#newsfileDescriptionForEdit').val();
   newsDescription = htmlEntity(newsDescription);

   try
  {
  	 locationScopeValue = document.getElementsByName('locationValue')[0].value; 
  }
   catch(err)
  { 
	locationScopeValue = reqFile.locationVal;
  }

  if(locationScopeValue == null || locationScopeValue == "null")
	  locationScopeValue = 0;



  // keywords = removeAllUnwantedCharacters(keywords); 
   keywords = htmlEntity(keywords);

    //CHANGE BY SAMBA END
   
  // title = removeAllUnwantedCharacters(title);   
  // description = removeAllUnwantedCharacters(description);
  
   for(var i in reqFile.fileVOList)
   {
      var sourceEle  = document.getElementById("sourceEdit"+i);
      sourceId  = sourceEle.options[sourceEle.selectedIndex].value;
      var filesourceId1 = document.getElementById("sourceEditId"+i).value;
	  sourceString = sourceString+''+sourceId+','+filesourceId1+'-';
	  
	  
      var languegeEle = document.getElementById("languageEdit"+i);
      languegeId = languegeEle.options[languegeEle.selectedIndex].value;
	  var filesourceId2 = document.getElementById("languageEditId"+i).value;
	  languageString = languageString+''+languegeId+','+filesourceId2+'-';
	  
	  
   }
   var categoryEle = document.getElementById("categoryEdit");
   categoryId = categoryEle.options[categoryEle.selectedIndex].value;
   
   var newsImportanceEle = document.getElementById("newsimportance");
   newsImportanceId = newsImportanceEle.options[newsImportanceEle.selectedIndex].value;
  }
  if(task == "Delete")
  {
    var r = confirm("Do you want to delete this news article");
      if (r == false)
       return;  
  
  }

  if(locationScopeId == 5 || locationScopeId == 7 || locationScopeId == 6 || locationScopeId == 8){

try{
    locationScopeValue = locationScopeValue.substring(1);
}catch(e){
	locationScopeValue = locationScopeValue;

}

  }

  modifiedRecord  = fileId;
   var jsObj=
	      { 
		  title		       :	title,
		  description	   :	description,
		  task			   :	task,
		  sourceId		   :	sourceId,
		  languegeId	   :	languegeId,
		  categoryId	   :	categoryId,
		  fileId           :    fileId,
		  timeST           :    timeST,
		  newsImportanceId :    newsImportanceId,
		  sourceData       :    sourceString.slice(0, -1),
		  languageData     :    languageString.slice(0, -1),
		  gallaryId        :    gallaryId,
          keywords         :    keywords,
          fileDate         :    fileDate,
          locationScopeId  :    locationScopeId,
          locationScopeValue:   locationScopeValue,
		  visibility        :visibility,
		  flagInd          :false,
		  fileGallaryId:fileGallaryId,
          newsDescription:newsDescription 
     }

 if(task == "Update")
 {
   $("#updateNewsDetailsId").val(YAHOO.lang.JSON.stringify(jsObj));
			var uploadHandler = {
			   success : function( o ) {
						  var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
						  //console.log(uploadResult);
						 buildUpdateStatus(uploadResult);
					}									
		   };

	
	YAHOO.util.Connect.setForm('updateNewsDetailsForm',false);
	YAHOO.util.Connect.asyncRequest('POST','updateDeleteNewsAction.action?',uploadHandler);

 }
  else
  {
	
	  var rparam ="task="+encodeURIComponent(unescape(YAHOO.lang.JSON.stringify(jsObj)));
      var url = "updateDeleteNewsAction.action?"+rparam;	//18111
  callnewAjax(jsObj,url);
  }
 
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
function getNewsForAParty(partyId)
{
	if( partyId != 0 ){

	var jsObj =
		{ 
		    partyId : partyId,
			task:"getAllGallariesOfAParty"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllGallariesOfAParty.action?"+rparam;						
	callAjax(jsObj,url);
	}

}

function getAllNewsOfAGallary(gallaryId)
{
	if( gallaryId != 0 ){

	var jsObj =
		{ 
		    gallaryId : gallaryId,
            fromDate : $('#fromDateId').val(),
            toDate : $('#toDateId').val(),
			task:"getAllTheNewsOfAGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllTheNewsOfAGallary.action?"+rparam;						
	callAjax(jsObj,url);
	}

}

function getCandidateNews()
{

    $("#gallaryCheckboxId").attr("checked",false);
	$("#categoryCheckboxId").attr("checked",false);
	$("#respenseNewsList").find('option').remove();

	$("#categoryShowHideDiv").css("display","none");
	$("#gallaryShowHideDiv").css("display","none");
	if($("#gallaryAllCheckboxId").is(":checked"))
	{
	  var candidateId = $("#candidatesList").val();
	  var jsObj =
		{ 
		    candidateId : candidateId,
            fromDate : $('#fromDateId').val(),
            toDate : $('#toDateId').val(),
			task:"getNewsForACandidate"
		};

	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	  var url = "getNewsForACandidate.action?"+rparam;						
	  callnewAjax(jsObj,url);
	}

}

function buildNewsDetailsOfcandidate(results)
{
	$('#noNewsError').html('');
	$('#dateErrorMessage').html('');
	if(results == null || results.length == 0)
	{
		$('#candidateNewsList , #respenseNewsList , #buttonsDiv').hide();
		$('#noNewsError').html('<b style="color:red;">No news found.Changing the date range may help you.</b>');
		$("#categoryGallaryHideShowDiv").css("display","none");
		return false;
	}else{

		$("#categoryGallaryHideShowDiv").css("display","block");
		$('#candidateNewsList , #respenseNewsList , #buttonsDiv').show();
	}
	$('#candidateNewsList').find('option').remove();

	$.each(results , function(index , value){

		if(value.flag == true)
		  $('#candidateNewsList').append('<option class="enadu" title="'+results[index].name+'" value="'+results[index].id+'">'+results[index].name+'</option>');
		else
			$('#candidateNewsList').append('<option class="notEenadu" title="'+results[index].name+'" value="'+results[index].id+'">'+results[index].name+'</option>');


	});

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
	document.getElementById('uploadNewsBtnId');
	if(validateNewsFileUpload1())
	{
		disableButton('uploadNewsBtnId');
	var newsprivateRadioId = document.getElementById('newsprivateRadioId').checked;
		if(newsprivateRadioId == true)
	document.getElementById('newsprivateRadioId').checked = 'true';
		if(newsprivateRadioId == false)
	document.getElementById('newsPublicRadioId').checked = 'true';

    var keywordStr = "";
	$(".as-selections li").each(function(){
              keywordStr +=$(this).text()+",";
				
            });
   
   var length = keywordStr.length;
    keywordStr = keywordStr.substr(0,length-2); 
	$("#keywordId").val(keywordStr);

	
		var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					showNewsUploadStatus1(uploadResult);	
					enableButton('uploadNewsBtnId');
				}
			};

		
		YAHOO.util.Connect.setForm('uploadForm1',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadFilesForMultipleCandidates.action',uploadHandler);
	}
	else
		return;
}


function validateUploadFileDetails()
{
   
   $("#uploadNewsFileErrorDiv").html('');
   var flag = true;
   var fileTitle = $.trim($("#newsfileTitle").val());
   var fileDesc  = $.trim($("#newsfileDescription").val());
   var fileDate = $("#newsdatedatepic").val();
   var scope = document.getElementById("scopeDiv").value;
   
   fileTitle = removeAllUnwantedCharacters1(fileTitle);	
   fileDesc = removeAllUnwantedCharacters1(fileDesc);
	document.getElementById('newsfileTitle').value = fileTitle;
	document.getElementById('newsfileDescription').value = fileDesc;

	var errorDivEle = document.getElementById('uploadNewsFileErrorDiv');
	var str = '<font color="red">';
	
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
	if(fileDate.length == 0)
	{
		str +='File Date is Required<br>';
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
	if(flag == false)
	{
		 $("#uploadNewsBtnId").css("background","#51A351");
		$('html, body').animate({
         scrollTop: $("#uploadNewsFileErrorDiv").offset().top
     }, 2000);
		
	}
	else
	$("#uploadNewsBtnId").css("background","#BBBB51");
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

 function addMoreFiles1(value,text)
{
	//var moreDivElmt = document.createElement("addMoreFilesDiv");
	var str ='';
	str+='<div id="'+value+'"  class="div'+value+'">';
	str+="<u><span style='font-weight:bold;'>Upload "+text+" Files</span></u>";
	str +='<table style="background:#e3e3e3;border-radius:9px;padding:5px;margin-top:12px;" id="moreFileTableId0'+value+'">';

    str +='<tr><td class="tdWidth1" style="width:97px;">Image To Display : </td><td><input type="file" name="imageForDisplay" id="ImagenewsfileId" size="25" /></td></tr>';

	str += ' <tr>';
	str +='<td>File Path</td>';
	str += ' <td class="selectWidthPadd"><input type="file" name="userImage" id="newsFileId'+value+'" size="25" class="newsFile"/></td>';
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
	//document.getElementById("addMoreFilesDiv").appendChild(moreDivElmt);
	//document.getElementById(value).appendChild(moreDivElmt);
	getSource("filesourceId"+value+"");
	getLanguage('sourceLangId'+value+'');
	//fileCount++;
} 
var divCount = 1;
function addMoreFiles2(value)
{
	//var moreDivElmt = document.createElement("addMoreFilesDiv");
	var str ='';
	str +='<table class="moreFileTable" style="background:#e3e3e3;border-radius:9px;padding:5px;margin-top:12px;" id="moreFileTableId'+divCount+''+value+'">';
	str += ' <tr>';
	str +='<td>File Path</td>';
	str += ' <td class="selectWidthPadd"><input type="file" name="userImage" id="newsFileId'+value+'" size="25" /></td>';
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
	//str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteFileDiv(\'moreFileTableId'+value+'\')"></td>';
	str +='<td><img style="background: #fff; border-radius: 11px; padding: 4px;" src="images/minus.png" title="Click here to delete file" onclick="deleteFileDiv(this)"></td>';
	str += '   </tr>';
	str+='<input type="hidden" name="filesList" value="'+value+'"/>';
	str +='</table>';
	//str+='<input type="hidden" name="filesList" value="'+value+'"/>';
	//$('#'+value).append(str);
	$('#'+value+'').append(str);
	//document.getElementById("addMoreFilesDiv").appendChild(moreDivElmt);
	//document.getElementById(value).appendChild(moreDivElmt);
	//alert("filesourceId"+value+"");

	getSource1("filesourceId"+divCount+value);
	getLanguage1("sourceLangId"+divCount+value);
	//fileCount++;
	divCount ++;

} 
function deleteFileDiv(element)
{
	//$('#'+id).remove();

	//$('#'+id).closest().find('.moreFileTable').remove();
	$($(element)).closest('table').remove();
}
function getSource1(selectOptionId){
	//$('#'+selectOptionId).find('option').remove();
	
	$.each(sourceObj,function(index,value){
		$('#'+selectOptionId).append('<option value="'+sourceObj[index].id+'">'+sourceObj[index].name+'</option>');

	});
	
 	
}

function getLanguage1(fillOptionId)
{

//$('#'+fillOptionId).find('option').remove();
	
	$.each(languagesObj,function(index,value){
		$('#'+fillOptionId).append('<option value="'+languagesObj[index].id+'">'+languagesObj[index].name+'</option>');

	});
		
}

function validateNewsFileUpload1()
{
	var fileTitle = document.getElementById('newsfileTitle').value;
	var fileDesc = document.getElementById('newsfileDescription').value;
	//var fileVal = document.getElementById("newsfileId").value;
	//var source = document.getElementById("source").value;
	//var languageId = document.getElementById("language").value;
	//var keywords = document.getElementById("keywords").value;
	//var galEle = document.getElementById("gallaryId").value;
	var fileDate = document.getElementById("existingFromTextNews").value;
	var scope = document.getElementById("scopeDiv").value;
	
	//var pageNo = document.getElementById("pageno").value;
	//var pageNo = document.getElementById("newslength").value;
	var flag = true;

	fileTitle = removeAllUnwantedCharacters1(fileTitle);	
	fileDesc = removeAllUnwantedCharacters1(fileDesc);
	document.getElementById('newsfileTitle').value = fileTitle;
	document.getElementById('newsfileDescription').value = fileDesc;

	var errorDivEle = document.getElementById('uploadNewsFileErrorDiv');
	var str = '<font color="red">';
	/*if(galEle == 0)
	{
		str += 'Select Gallery<br>';
		flag = false;
	}*/
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


		
	 $('.newsFile').each(function() {
           if($.trim($(this).val()).length == 0)
	       {
		     str+='File Required';
		     flag = false;
			 return false;
	       }
       });


	 $('.pageno').each(function() {
           if($.trim($(this).val()).length == 0)
	       {
		     str += ' Page Number is Required.<br>';
		     flag = false;
			 return false;
	       }
       });
	$('.pageno').each(function() {
           if($.trim($(this).val()).length > 0)
	       {
		     if(isNaN($.trim($(this).val()))){
				 str += ' Page Number must be Integer.<br>';
				 flag = false;
				 return false;
			 }
	       }
       });
	   $('.newslength').each(function() {
           if($.trim($(this).val()).length == 0)
	       {
		     str += ' News Length is Required.<br>';
		     flag = false;
			 return false;
	       }
       });
	$('.newslength').each(function() {
           if($.trim($(this).val()).length > 0)
	       {
		     if(isNaN($.trim($(this).val()))){
				 str += ' News Length must be number.<br>';
				 flag = false;
				 return false;
			 }
	       }
       });
	if(fileTitle.length >50)
	{
		str += 'Title should be less than 50 Characters<br>';
		flag = false;
	}
	if(fileDesc.length == 0)
	{
		str += 'Description is Required.<br>';
		flag = false;
	}
	if(fileDesc.length > 200)
	{
		str += 'Description Should not exceed 200 Characters.<br>';
		flag = false;
	}
	/*if(fileVal.length == 0)
	{
		str += 'File is Required.<br>';
		flag = false;
	}*/
	/*if(source.length == 0)
	{
		str += 'Source is Required.<br>';
		flag = false;
	}
	if(languageId.length == 0)
	{
		str += 'Language is Required.<br>';
		flag = false;
	}*/
	/*if(keywords.length >200)
	{
		str += 'Keywords should be less than 200 Characters<br>';
		flag = false;
	}*/

	if($("#candidateList > option:selected").length == 0)
	{
		str += 'Select Candidate<br>';
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
	if(flag == false)
	{
		 $("#uploadNewsBtnId").css("background","#51A351");
		$('html, body').animate({
         scrollTop: $("#uploadNewsFileErrorDiv").offset().top
     }, 2000);
		
	}
	else
	$("#uploadNewsBtnId").css("background","#BBBB51");
	return flag;
}


function  buildUploadNews()
{
   var tempPartyId = 872;
   var str ='';
	str+='<div id="content" style="width:650px;">';
	//str +=  '<table style="margin:5px;width:40%;margin-left:50px;">';
	/*str +='<table class="aligncenter m_top10">';
	str +=  '<tr>';
	str += 	'<td><input type="button" class="btn btn-success highlight" value="Create News Categery" onclick="buildCreateNewsCategory()"></td>';
	str += '<td><input type="button" class="btn btn-success highlight" value="Upload News" onclick="buildUploadNews()"></td>';
	str += 	 '</tr>';
	str += 	'</table>';*/
	//str += '<fieldset class="imgFieldset" style="width:504px;">';
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
	/*str += '   </tr>';
	str += '       <td class="tdWidth1">Category : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="category" name="category" onchange="getGallariesForSelectedCategory()"><option value="0">Select Category</option></select></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += ' <td class="tdWidth1">Select Gallery : </td><td class="selectWidthPadd"><select onchange="buildPartyNewsVisibility()" id="gallaryId" name="gallaryId"/></select></td>';
	str += '   </tr>';*/


    str += '   <tr>';
	str += '       <td class="tdWidth1">Title : <font class="requiredFont">*</font><b></td>';
	str += '       <td class="selectWidthPadd"><input type="text" id="newsfileTitle" name="fileTitle" size="25" maxlength="160" ></input></td>'; 
	str += '   </tr>';
	str += '   <tr>';
	str += '       <td class="tdWidth1">News Description : <font class="requiredFont">*</font></td>';
	str += '       <td class="selectWidthPadd"><textarea id="newsfileDescription" cols="20" rows="3" name="fileDescription" maxlength="330"></textarea></td>';
	str += '   </tr>';
	
	/* str += '   <tr>';
	str += '       <td class="tdWidth1">Keywords : </td>';
	str += '       <td class="selectWidthPadd"><input type="text" id="keywords" name="keywords" size="25" maxlength="200" ></input></td></tr>'; */


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
	/*str += '   </tr>';
	str += '       <td class="tdWidth1">Category : <font class="requiredFont">*</font></td>';
	str += '  <td class="selectWidthPadd"><select id="category" name="category" ><option value="0">Select Category</option></select></td>';
	str += '   </tr>';*/
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
	str += '       <td class="selectWidthPadd"><input type="file" name="userImage" id="newsfileId" size="25" style="margin-top:8px;"/></td>';
	str += '       <td class="selectWidthPadd"><img style="background:#cdcdcd;padding:5px;" src="images/plus.png" onclick="addMoreFiles()" title="Click here to add more images" alt="Click here to add more images"/></td>';
	str += '   </tr>';
	str += '   <tr>';
	str += '      <td colspan="2"> <div id="addMoreFilesDiv"></div></td>';
	str += '   </tr>';
	str += '   </tr>';

    str +='<tr><td class="tdWidth1" style="width:97px;">Image To Display : </td><td><input type="file" name="imageForDisplay" id="ImagenewsfileId" size="25" /></td></tr>';

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
	//str+='<input type="radio" style="margin-left:55px;" onclick="otherProfiles(\'otherProNewsDiv\',\'fromPartyProfile\',\'News Gallary\')"/>    Do you want to upload this file to other profiles';
	str+='<div id="otherProNewsDiv" style="margin: 10px;"></div>'; 
	str+='<div id="uploadnewsgalAjax" style="display:none;margin-left:430px;clear:both;"><img src="images/search.jpg"/></div>';
	str += '<table class="aligncenter"><tr><td><input id="uploadNewsBtnId" type="button" class="btn btn-success " value="Upload News" style="background-color:#57B731" onClick="uploadNewsFromPartyPage()"></td><td><input id="uploadNewsBtnId" type="button" class="btn btn-success highlight" value="Cancel"  onClick="clearDiv(\'newsGallaryDiv\');"></td></tr></table>';

    str +='<input type="hidden" id="keywordListId" name="keywordList"/>';

	str += '</form>';
	//str += '</fieldset>';
	str+='</div>';
	document.getElementById("newsGallaryDiv").innerHTML = str;
	//getPartyGallariesForUplaod("News Gallary");
	
	 getScopes();
	 getSource("source");
	 getLanguage("language");
	 getNewsImportance();
	 //getCategory();

	 getScopeForUser();

   //keywords
   $("#keywordListId1").autoSuggest(data.items, {selectedItemProp: "name", searchObjProps: "name"});



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
    var str ='';
	str+='<div class="container well">';
	str+='<div class="row clearfix">';
	str+='<div class="row-fluid"><div class="span12 well well-small " id="reportsDiv">';
	str+=' </div>';
    str+='</div>';
	str+='</div>';
	str+='</div>';
	document.getElementById("newsReportsMainDiv").innerHTML = str;
	getReports();
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

function buildCandidatesForKeywords(results,type)
{
	
             $("#"+type+" option").remove();
			 $('#'+type).append('<option value="0">Select Candidate</option>');
            $.each(results,function(index , value){
		$('#'+type).append('<option value="'+value.id+'">'+value.name+'</option>');

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

  else if(result.resultCode == 0 && result.message == null)
  {
   $("#errorMsgDiv").html('Candidate Saved Successfully.').css("color","green");
   $("#newCandidateName").val('');
   $("#partySelectNewList").val(0);
   var partyId = $("#"+jsObj.partyListId+"").val();
   
   if(partyId != null && partyId > 0)
     getCandidatesListByPartyId(partyId,""+jsObj.candidateListId+"");
   
   //$("#"+jsObj.candidateListId+"").
   
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
		 str +="<input type='checkbox' class='newsResponseCheckId' value='"+fileId+"' checked='checked'/>";
		else
		 str +="<input type='checkbox' class='newsResponseCheckId' value='"+fileId+"' />";
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
		var description = oRecord.getData("description");
		if(isEenaduTelugu != null && isEenaduTelugu == "Eenadu Telugu")
		  str +="<span class='enadu'>"+description+"</span>";
		  else
		  str +="<span>"+description+"</span>";
		  
		elLiner.innerHTML=str;
					
	};
 
  var fromDate = $("#newsFromDateId").val();
  var toDate = $("#newsToDateId").val();
  
  var newsColumns = [
           {key:"ADD RESPONSE",label:"ADD RESPONSE",formatter:YAHOO.widget.DataTable.checkBox},
		   {key:"source", label:"SOURCE"},
           {key:"title", label:"TITLE",formatter:YAHOO.widget.DataTable.title},
		   {key:"description", label:"DESCRIPTION",formatter:YAHOO.widget.DataTable.description},
		   {key:"locationScopeValue", label:"IMPACT AREA"},
		   {key:"locationValue", label:"AREA NAME"},
		   {key:"fileDateAsString", label:"NEWS DATE"}
  ];
  
  var newsDataSource = new YAHOO.util.DataSource("getTotalNewsAction.action?fromDate="+fromDate+"&toDate="+toDate+"&");
  newsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
  newsDataSource.responseSchema = {
  resultsList: "fileVOList",
   fields: [
             {key:"fileId", parser:"number"},
			        "eenaduTeluguFontStr","source","title", "description", "locationScopeValue","locationValue", "fileDateAsString"],

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

