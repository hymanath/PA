
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

function getContentDetails(contentId)
{
	//document.getElementById("contentAjaxCallImg").style.display="block";
	var jsObj =
		{   
		    contentId : contentId,
			requestFrom : 'Candidate Page',
			requestPageId :'0',
			isCustomer:'true',
			task:"getSelectedContent"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSelectedContentAndRelatedGalleriesAForCustomer.action?"+rparam;
	callAjaxForSpecialPage(jsObj,url); 
}
var showContentResultList;
function callAjaxForSpecialPage(jsObj,url)
{


	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText);




		  if(jsObj.task == "getSelectedContent")
			{
				showContentResultList = myResults;
				buildContentDetails();
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
var commentsObject = new Object();
var result;
function buildContentDetails()
{

//console.log(111);
	//document.getElementById("contentAjaxCallImg").style.display="none";
	result = showContentResultList;

//console.log(result);
	if(result == null)
		return;

	var divEle = document.getElementById('showContentDivInnerDiv');
	var str = '';
	var titleStr = null;
	var pathStr = null;
	var descriptionStr = null;
	var preContentId = null;
	var curPos = null;
	var totSize = null;
	var commentText="" ;
    var fileId1  = null;
	
	//document.getElementById('ui-dialog-title-showContentDiv').innerHTML = '<font color="darkgreen"><b>${specialPageVO.heading} - '+result.contentType;

	document.getElementById('ui-dialog-title-showContentDiv').innerHTML = '<font color="darkgreen"><b>'+result.contentType;

	str += '<Div><center>';
	str += '<div class="main-title-sec" style="clear:both;">';
	str += '<div id="showContentHeaderDiv" class="main-mbg" style="width:850px;border-radius:0px 0px 0px 0px;"></div><div class="main-bbg"/></div>';

	
	for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
	if(result.relatedGalleries[0].filesList[i].isSelectedContent)
	{

		
	    selectedContentFile = result.relatedGalleries[0].filesList[i];
		titleStr = result.relatedGalleries[0].filesList[i].title;
		pathStr = result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path;
		descriptionStr = result.relatedGalleries[0].filesList[i].description;
		preContentId = result.relatedGalleries[0].filesList[i].contentId;
		curPos = i+1;
		totSize = result.relatedGalleries[0].filesList.length;



		if(result.contentType == 'Video Gallary' || result.contentType == 'News Gallary')
		{


			str+='<table>';
			str+='<tr>';
			str+='<td>';
			
			if(result.relatedGalleries[0].filesList[i].categoryType != null)
				str+='<B>CategoryType</B> : <font color="#FF4500"><span id="sourceChangeSpan">'+result.relatedGalleries[0].filesList[i].categoryType+'</span></font> &nbsp;&nbsp;&nbsp;<B>';

			if(result.relatedGalleries[0].filesList[i].locationScopeValue != null)
				str+=' RegionScope  </B>:<font color="#FF4500"> '+result.relatedGalleries[0].filesList[i].locationScopeValue+'</font>';


			 str+='</td>';

			 str+='<td>';
			if(result.relatedGalleries[0].filesList[i].fileVOList[0].source != null)
				str+='<B>Source</B> : <font color="#FF4500"><span id="sourceChangeSpan">'+result.relatedGalleries[0].filesList[i].fileVOList[0].source+'</span></font> &nbsp;&nbsp;&nbsp;<B>';

			if(result.relatedGalleries[0].filesList[i].fileDate != null)
				str+=' Date </B>:<font color="#FF4500"> '+result.relatedGalleries[0].filesList[i].fileDate+'</font>';


			 str+='</td>';


			 str+='</tr>';
			 str+='</table>';

		}
	}
	

	if(result.contentType == 'Video Gallary')
	{
		if(result.relatedGalleries[0].filesList.length < 2)
			str += '<table width="530px">';
		else
			str += '<table width="880px">';
		str += '<tr>';

		if(result.relatedGalleries[0].filesList.length >= 2){
		str += '<td valign="top">';
		str += '<div class="popupcontainer" style="height:425px;overflow:auto;width:140px;">';
		str += '<b><font color="blue">Other Videos</font></b>';
		str += '<Table>';
		
		for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		if(!result.relatedGalleries[0].filesList[i].isSelectedContent && (i%2 == 0))
		{
			str += '<tr><td><a href="javascript:{}" onClick="buildContentDetailsOfSelected('+preContentId+','+result.relatedGalleries[0].filesList[i].contentId+')" title="Click here to See the Video about - '+result.relatedGalleries[0].filesList[i].description+'"><img style="margin-top:8px;" src="http://img.youtube.com/vi/'+result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path+'/1.jpg" alt="'+result.relatedGalleries[0].filesList[i].title+'"></img></a></td></tr>';
		}
		str += '</Table>';
		str += '</div>';
		str += '</td>';
		}
		
		str += '<td valign="top" style="horizontal-align:center;">';
		str += '<div class="popupcontainer" id="nextPartImage" style="width:500px;text-align:center;">';
		str += '<iframe width="500" height="396" src="http://www.youtube.com/embed/'+pathStr+'" frameborder="0" allowfullscreen="true"></iframe></div>';
		str += '<table><tr>';
		str += '<td>';
		str += ''+descriptionStr+'';
		str += '</td>';
		str += '</tr>';
		str += '</table>';
		


		
		str +='<div id="buildNewSourceParts">';
	       str += '<center><table><tr>';

	         for(var j=1;j<selectedContentFile.fileVOList[0].fileVOList.length;j++)
	         {
	            str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[0].fileSourceLanguageId+','+selectedContentFile.fileVOList[0].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[0].fileVOList[j].path+'\',\'video\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="http://img.youtube.com/vi/'+selectedContentFile.fileVOList[0].fileVOList[j].path+'/1.jpg" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[0].fileVOList[j].orderName+'</a></td>';
	         }
		 
	       str += '  </tr></table>';
	       str +='</center></div>';
		   
		   if(selectedContentFile.multipleSource >1 )
	          {
	             str +='<div id="buildVideoNewSources">';
	             str += '<center><table><tr><td><b>Same Video in another sources</b></td></tr></table></center>';
	             str += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
	           
			      for(var k=1;k<selectedContentFile.fileVOList.length;k++)
	              {
	               str += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[k].fileSourceLanguageId+',\'video\')">'+selectedContentFile.fileVOList[k].source+'</a></td>';
	              }
	            str += '  </tr></table>';
	            str +='</center></div>';
	          }
		   
		
		str += '</div>';
		str += '</td>';
		
		if(result.relatedGalleries[0].filesList.length >= 2){
		str += '<td valign="top">';
		str += '<div class="popupcontainer" style="height:425px;overflow:auto;width:140px;">';
		str += '<b><font color="blue">Other Videos</font></b>';
		str += '<Table>';

		for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		if(!result.relatedGalleries[0].filesList[i].isSelectedContent && (i%2 == 1))
		{
			str += '<tr><td><a href="javascript:{}" onClick="buildContentDetailsOfSelected('+preContentId+','+result.relatedGalleries[0].filesList[i].contentId+')" title="Click here to See the Video about - '+result.relatedGalleries[0].filesList[i].description+'"><img style="margin-top:8px;" src="http://img.youtube.com/vi/'+result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path+'/1.jpg" alt="'+result.relatedGalleries[0].filesList[i].title+'"></img></a></td></tr>';
		}
		str += '</Table>';
		str += '</div>';
		str += '</td>';
		}
		
	str += '</tr>';
	str += '</table>';
	}

	else if(result.contentType == 'Photo Gallary' || result.contentType == 'News Gallary')
	{
		str += '<table>';

		for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		if(result.relatedGalleries[0].filesList[i].isSelectedContent)
		{
			descriptionStr = result.relatedGalleries[0].filesList[i].description;

			
			 if(result.relatedGalleries[0].filesList.length == 1){
					
			  }else{
			    if(i > 0)
				{
					str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[i-1].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[i-1].contentId+')"><img src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
				}else{
				   str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[result.relatedGalleries[0].filesList.length-1].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[result.relatedGalleries[0].filesList.length-1].contentId+')"><img src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
				}
			  }
			str += '<td><div class="popupcontainer" id="nextPartImage" style="width:700px;text-align:center;"><img alt="'+result.relatedGalleries[0].filesList[i].title+'" title="'+result.relatedGalleries[0].filesList[i].description+'" align="middle" style="max-width:780px;max-length:800px;" src="'+result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path+'" /></div></td>';


          // commentsObject[result.relatedGalleries[0].filesList[i].fileId] = result.relatedGalleries[0].filesList[i].comments;


		   fileId1 = result.relatedGalleries[0].filesList[i].fileId;

		   var id = result.relatedGalleries[0].filesList[i].fileId;

			 if(commentsObject[id] == undefined){

				 commentText = result.relatedGalleries[0].filesList[i].comments;

				 commentsObject[result.relatedGalleries[0].filesList[i].fileId] = result.relatedGalleries[0].filesList[i].comments

			 }else{
               commentText = commentsObject[result.relatedGalleries[0].filesList[i].fileId];
			 }
			
			if(result.relatedGalleries[0].filesList.length == 1){
				
			  }else{
				if(i != result.relatedGalleries[0].filesList.length-1)
				{
					str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[i+1].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[i+1].contentId+')"><img src="images/icons/jQuery/next.png" class="newsImage" /></a></td>';
				}else{
				   str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[0].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[0].contentId+')"><img src="images/icons/jQuery/next.png" class="newsImage" /></a></td>';
				}
			  }

		}

		str += '</table>';

		
		str += '<div>';
		str += '<table>';
		str += '<tr><td>Description : <b>'+descriptionStr+'</b></td></tr>';
		str += '</table>';
		str += '</div>';
			

		//FOR COMMNET START

/*
		if(commentText != null && commentText !=""){
		str+='<div class="popupcontainer" style="width:800px;margin:4px;text-align:left;" id="commentDefault'+fileId1+'"><b>Notes :</b>'+commentText;
			str+='<a hrfe="#" class="btn btn-mini btn-info" style="margin-left:8px;" onClick="editComment('+fileId1+');"  title="click here to edit notes">Edit</a></div>';
            str+='<div class="commentClass'+fileId1+'" style="width: 700px;height:104px;display:none;margin:11px;"><b style="float:left;">Enter Notes:</b><textarea style="width:650px;height:50px;margin-right:49px;" id="commentId'+fileId1+'">'+commentText+'</textarea>';

			str+='<input type="button" style="margin:3px 8px 0px 410px" class="btn btn-mini" value="Save" onClick="saveFileComment('+fileId1+');"/>';

			str+='<input type="button" class="btn btn-mini" value="Cancel" onClick="cancelPostComment('+fileId1+');"/></div>';
		}
		else{
		str+='<div style="width:700px;height:104px;margin:11px;"><b style="float:left;">Enter Notes:</b><textarea style="width:650px;height:50px;margin-right:49px;" id="commentId'+fileId1+'"></textarea>';

	     str+='<input type="button" style="float:right;margin:3px 49px;" class="btn btn-mini" value="Save" onClick="saveFileComment('+fileId1+');"/></div>';
		}


 */  		   //FOR COMMNET END



		
	   for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		  
		if(result.relatedGalleries[0].filesList[i].isSelectedContent)
		{
		   selectedContentFile = result.relatedGalleries[0].filesList[i];
		   str +='<div id="buildNewSourceParts">';
	       str += '<center><table><tr>';

	         for(var j=1;j<selectedContentFile.fileVOList[0].fileVOList.length;j++)
	         {
	            str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[0].fileSourceLanguageId+','+selectedContentFile.fileVOList[0].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[0].fileVOList[j].path+'\',\'other\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="'+selectedContentFile.fileVOList[0].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[0].fileVOList[j].orderName+'</a></td>';
	         }
		 
	       str += '  </tr></table>';
	       str +='</center></div>';
		   
		   if(selectedContentFile.multipleSource >1 )
	          {
	             str +='<div id="buildNewSources">';
	             str += '<center><table><tr><td><b>Same News in another sources</b></td></tr></table></center>';
	             str += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
	           
			      for(var k=1;k<selectedContentFile.fileVOList.length;k++)
	              {
	               str += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[k].fileSourceLanguageId+',\'other\')">'+selectedContentFile.fileVOList[k].source+'</a></td>';
	              }
	            str += '  </tr></table>';
	            str +='</center></div>';
	          }
		}

		
	}

	

	//str+='<div><b>Enter Comment:</b><textarea style="width:650px;height:50px;" id="commentId'+fileId+'"></textarea></div>';

	//str+='<input type="button" style="float:right;margin:3px 133px;" class="btn" value="Save" onClick="saveFileComment('+fileId+');"/>';


	if(result.otherGalleries != null && result.otherGalleries.length > 0)
	{
		var galType = null;
		
		if(result.contentType == 'Photo Gallary')
			galType = ' Photo ';
		else if(result.contentType == 'News Gallary')
			galType = ' News ';
		else if(result.contentType == 'Video Gallary')
			galType = ' Video ';

		str += '<div>';

		str += '<Div><center>';
		str += '<div class="main-title-sec" style="clear:left;">';
		//str += '<div class="main-mbg" style="width:850px;border-radius:0px 0px 0px 0px;">Other '+galType+' gallaries Of ${specialPageVO.heading}</div><div class="main-bbg"/></div>';
		str += '<div class="main-mbg" style="width:850px;border-radius:0px 0px 0px 0px;">Other  gallaries </div><div class="main-bbg"/></div>';
		
		str += '<div class="popupcontainer" style="overflow:auto;width:880px;max-width:850px;">';
		str += '<Table>';
		
		for(var i=0;i<result.otherGalleries.length;i++)
		{
			if(i%5 == 0)
				str += '<tr>';
			
			str += '<td width="20%" valign="top">';

			str += '<table>';
			str += '<tr><td class="videoGalTitleStyle"><a href="javascript:{}" onClick="getContentDetails('+result.otherGalleries[i].filesList[0].fileId+')" title="Click here to View '+result.otherGalleries[i].gallaryName+''+galType+' Gallery"><font color="red">'+result.otherGalleries[i].gallaryName+'</font></a></td></tr>';
			str += '<tr><td><a href="javascript:{}" onClick="getContentDetails('+result.otherGalleries[i].filesList[0].fileId+')" title="Click here to View '+result.otherGalleries[i].gallaryName+''+galType+' Gallery">';
			
			if(result.contentType == 'Photo Gallary' || result.contentType == 'News Gallary')
				str += '<img width="120px" height="90px" alt="'+result.otherGalleries[i].gallaryName+'" src="'+result.otherGalleries[i].filesList[0].path+'"></img>';
				
			else if(result.contentType == 'Video Gallary')
				str += '<img src="http://img.youtube.com/vi/'+result.otherGalleries[i].filesList[0].path+'/1.jpg"></img>';
			
			str += '</a></td></tr>';
			str += '<tr><td class="videoGallDescStyle">Gallery Size : ('+result.otherGalleries[i].orderNo+')</td></tr>';
			str += '<tr><td class="videoGallDescStyle">'+result.otherGalleries[i].description+'</td></tr>';
			str += '</table>';

			str += '</td>';

			if(i%5 == 4)
				str += '</tr>';
		}
		str += '</Table>';
		str += '</div>';

		str += '</div>';
	}
	
	str += '</center></Div>';

	divEle.innerHTML = str;

	var str = '';
	str += ''+titleStr+' ('+curPos+' of '+totSize+')<span style="margin-top:10px;margin-right:18px;float:right">';
	//str +='<a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/specialPageAction.action?specialPageId=${specialPageId}&contentId='+preContentId+'\')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a>';
	str += '</span>';
	
	document.getElementById("showContentHeaderDiv").innerHTML=str;
	
}

function editComment(fileId){

	$('.commentClass'+fileId).css('display','block');
	$('#commentDefault'+fileId).css('display','none');
}


function cancelPostComment(fileId){

	$('.commentClass'+fileId).css('display','none');
	$('#commentDefault'+fileId).css('display','block');

}

function saveFileComment(fileId){


	 var comment = $('#commentId'+fileId).val();

	 commentsObject[fileId] = comment;

	  var jsObj =
		{ 
		    comment:comment,
            fileId : fileId,
			task:"saveFileComment"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveFileCommentAction.action?"+rparam;						
	callnewAjax(jsObj,url);

 }

function buildContentDetailsOfSelected(preId,selId)
{
	for(var i=0;i<showContentResultList.relatedGalleries[0].filesList.length;i++)
	{
		if(showContentResultList.relatedGalleries[0].filesList[i].contentId == preId)
			showContentResultList.relatedGalleries[0].filesList[i].isSelectedContent = false;
		if(showContentResultList.relatedGalleries[0].filesList[i].contentId == selId)
			showContentResultList.relatedGalleries[0].filesList[i].isSelectedContent = true;
	}

	buildContentDetails();
}

function showNextNewsPart(fileSourceLanguageId,orderNo,path,type)
{
  for(var i in selectedContentFile.fileVOList)
  {
    if(selectedContentFile.fileVOList[i].fileSourceLanguageId == fileSourceLanguageId)
	{
	  if(type != 'video')
	    var str='<img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" align="middle" style="max-width:780px;max-length:800px;" src="'+path+'" />';
	  else
	   var str='<iframe width="500" height="396" src="http://www.youtube.com/embed/'+path+'" frameborder="0" allowfullscreen="true"></iframe>';
	  document.getElementById("nextPartImage").innerHTML = str;
	
	   str = '<center><table><tr>';

	    for(var j=0;j<selectedContentFile.fileVOList[i].fileVOList.length;j++)
	     {
		   if(selectedContentFile.fileVOList[i].fileVOList[j].orderNo != orderNo)
		    {
			  if(type != 'video')
	             str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[i].fileSourceLanguageId+','+selectedContentFile.fileVOList[i].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[i].fileVOList[j].path+'\',\'other\')"><img width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" src="'+selectedContentFile.fileVOList[i].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[i].fileVOList[j].orderName+'</a></td>';
	          else
			     str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[i].fileSourceLanguageId+','+selectedContentFile.fileVOList[i].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[i].fileVOList[j].path+'\',\'video\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="http://img.youtube.com/vi/'+selectedContentFile.fileVOList[i].fileVOList[j].path+'/1.jpg" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[i].fileVOList[j].orderName+'</a></td>';
		    }
		 }
		 
	   str += '  </tr></table>';
	   str +='</center>';
	  document.getElementById("buildNewSourceParts").innerHTML = str;
	}
  
  }

}

function showNewAnotherSource(fileSourceLanguageId,type)
{
     var str1 ='';
	   if(type != 'video')
	     str1 += '<center><table><tr><td><b>Same News in another sources</b></td></tr></table></center>';
	   else 
         str1 += '<center><table><tr><td><b>Same Video in another sources</b></td></tr></table></center>';	   
		 str1 += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
  for(var m in selectedContentFile.fileVOList)
  {
    if(selectedContentFile.fileVOList[m].fileSourceLanguageId == fileSourceLanguageId)
	{
	  if(document.getElementById("sourceChangeSpan") != null)
	    document.getElementById("sourceChangeSpan").innerHTML = ''+selectedContentFile.fileVOList[m].source+'';
	  if(type != 'video')
	    var str='<img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" align="middle"  style="max-width:780px;max-length:800px;" src="'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" />';
	  else
	    var str='<iframe width="500" height="396" src="http://www.youtube.com/embed/'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" frameborder="0" allowfullscreen="true"></iframe>';
	  document.getElementById("nextPartImage").innerHTML = str;
	
	   str = '<center><table><tr>';

	    for(var j=1;j<selectedContentFile.fileVOList[m].fileVOList.length;j++)
	     {
		    if(type != 'video')
	         str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[m].fileSourceLanguageId+','+selectedContentFile.fileVOList[m].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[m].fileVOList[j].path+'\',\'other\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="'+selectedContentFile.fileVOList[m].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[m].fileVOList[j].orderName+'</a></td>';
			else 
			 str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[m].fileSourceLanguageId+','+selectedContentFile.fileVOList[m].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[m].fileVOList[j].path+'\',\'video\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="http://img.youtube.com/vi/'+selectedContentFile.fileVOList[m].fileVOList[j].path+'/1.jpg" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[m].fileVOList[j].orderName+'</a></td>';
	     }
		 
	   str += '  </tr></table>';
	   str +='</center>';
	  document.getElementById("buildNewSourceParts").innerHTML = str;
	}
    else
	{
	   if(type != 'video')
	    str1 += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[m].fileSourceLanguageId+',\'other\')">'+selectedContentFile.fileVOList[m].source+'</a></td>';	             	          	
	   else
	    str1 += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[m].fileSourceLanguageId+',\'video\')">'+selectedContentFile.fileVOList[m].source+'</a></td>';
	}
  }
     	str1 += '  </tr></table>';
	    str1 +='</center>';
     if(document.getElementById("buildNewSources") != null)
       document.getElementById("buildNewSources").innerHTML = str1;
	 else
	   document.getElementById("buildVideoNewSources").innerHTML = str1;
}

 var locationValue;
 var locationId;
 var  publicationId;
 var newsString ;
 var titleString;
function showNewsDetails(){


$('#newsCountDiv').html('');
$('#newsDisplayDiv').html('');
	newsString = "";
	titleString = "";

  var reportLevel = $('#reportLevel').val();

	if(reportLevel == 1){
		locationValue = $('#constituencyList').val();

		if($('#constituencyList').val() != 0)
		 newsString += $('#constituencyList :selected').text()+" "+"Constituency News Details By Category";
		 titleString += "Constituency :<font style=;color:red;'>" + $('#constituencyList :selected').text()+"</font>  ";
	}
	else if(reportLevel == 2){
        locationValue = $('#mandalField').val();

		if($('#mandalField').val() != 0)
		 newsString += $('#mandalField :selected').text()+" "+"Mandal News Details By Category";
 		 titleString += "Mandal :<font style=;color:red;'>"+ $('#mandalField :selected').text()+" "+"</font> ";
	}
	else if(reportLevel == 3){
        locationValue =$('#panchayatField').val();

		if($('#panchayatField').val() != 0)
		newsString += $('#panchayatField :selected').text()+" "+"Panchayat News Details By Category";
		titleString += "Panchayat :<font style=;color:red;'>"+$('#panchayatField :selected').text()+" "+"</font> ";;
	}

	// publicationId = $('#publicationDateList').val();

	//if($('#reportLevel').val() == 3 && $('#publicationDateList').val() == 0)
	//	return false;

	var locationType = $('#reportLevel :selected').text();


	if(locationType == "Constituency")
		locationId = 4;
	else if(locationType == "Panchayat")
		locationId = 3;
	else if(locationType == "Mandal")
		locationId = 5;


  if(locationValue == 0 || locationId == 0 || locationValue == null || locationValue == "null" || locationId == null || locationId == "null")
 	return false;

  if(reportLevel =="2"){

	  

	  if(locationValue.charAt(0) == "2"){
        locationValue = locationValue.substring(1);
		locationId =5;
	  }
	  else  if(locationValue.charAt(0) == "1"){
         locationId = 7;
		 locationValue = locationValue.substring(1);
	  }
  }

	  
   var jObj=
	{
		locationValue:locationValue,
		locationId:locationId,
		publicationId:0,
		//locationValue:locationValue,
		//locationId:locationId,
		//publicationId:publicationId

		task:"newsForALocation"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getNewsCountForALocationByCategory.action?"+rparam;	

	callAjaxToShowNewsDetails(jObj,url);

}


function showSubNewsDetails(locationValue,locationId){


$('#newsCountDiv').html('');
$('#newsDisplayDiv').html('');
	newsString = "";
locationValue = locationValue.substring(1);
	 var jObj=
	{
		locationValue:locationValue,
		locationId:locationId,
		publicationId:0,
		//locationValue:locationValue,
		//locationId:locationId,
		//publicationId:publicationId

		task:"newsForALocation"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getNewsCountForALocationByCategory.action?"+rparam;	

	callAjaxToShowNewsDetails(jObj,url);

}

function callAjaxToShowNewsDetails(jObj,url){
		
			 var myResults;

			 var callback = {			
 		              success : function( o ) {
					try {
						
				       myResults = YAHOO.lang.JSON.parse(o.responseText);

					   if(jObj.task == "getNewsByLocation")
                          displayNewsByImportance(jObj,myResults);					  
					   else if(jObj.task == "newsForALocation")
					      buildProblemsCount(myResults);
					   
					  
							
					}catch (e) {
						//alert('error');
					}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
						  // alert('error');
 		                }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback); 	

	}

function callAjaxToShowProblemDetails(jObj,url){
		
			 var myResults;

			 var callback = {			
 		              success : function( o ) {
					try {
						
				       myResults = YAHOO.lang.JSON.parse(o.responseText);

					   if(jObj.task == "getProblemsByLocation")
                          buildProblemsCountByLocation(myResults,jObj);					  
					  
					  
							
					}catch (e) {
						//alert('error');
					}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
						  // alert('error');
 		                }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback); 	

	}

/* function buildProblemsCount(results){

	  var str='';


	  str+='<div class="span9" style="background-color:#E8DE92;padding:12px;">';

	  for(var i=0;i<results.length;i++){

       var categoryId = results[i].categoryId;
		str+='<div class="span3" style="height:74px;background-color:#FFF;margin:2px;border-radius:3px;">';
		str+='<div style="color:#4A7CD4;text-align:center;font-family:Helvetica;font-size:14px;font-weight:bold;margin:5px;"><a href="#">'+results[i].categoryName+'</a></div>';

		str+='<div style="text-align:center;margin-top:5px;">';

		if(results[i].highImpactCount == null || results[i].highImpactCount == "null")
			str+='<span class="badge label-info" style="float:left;margin-left:15px">0</span>';
		else
			str+='<span class="badge label-info" style="float:left;margin-left:15px">'+results[i].highImpactCount+'</span>';

         if(results[i].mediumImpactCount == null || results[i].mediumImpactCount == "null")
		   str+='<span class="badge label-info">0</span>';
		 else
		   str+='<span class="badge label-info">'+results[i].mediumImpactCount+'</span>';

          if(results[i].lowImpactCount == null || results[i].lowImpactCount == "null")
		    str+='<span class="badge label-info" style="float:right;margin-right:15px;">0</span>';
		  else
             str+='<span class="badge label-info" style="float:right;margin-right:15px;">'+results[i].lowImpactCount+'</span>';


        str+='</div>';
		str+='<div style="text-align:center;">';
		str+='<a href="javaScript:{getNews1(3,'+categoryId+');}" style="float:left;margin-left:21px;font-family:Verdana;font-size:13px;">High</a>';
		str+='<a href="javaScript:{getNews1(2,'+categoryId+');}" style="margin-right:8px;font-family:Verdana;font-size:13px;">Medium</span>';
		str+='<a href="javaScript:{getNews1(1,'+categoryId+');}" style="float:right;margin-right:24px;font-family:Verdana;font-size:13px;">low</a>';
        str+='</div>';
	str+='</div>';

	  }

	  str+='</div>';

	  $('#newsCountDiv').html(str);

	}*/



function  buildProblemsCount(results){


	//$('#newsCountDiv').html('');
	//$('#newsDisplayDiv').html('');

	var str='';

	/*if(results.length == 0){
		newsString = "";
		$('#newsDiv').html("<h5>No news Avialable</h5>");
		$('#showHideLink').hide();
		return false;
	}*/



	//str+='<div>';
	//str+='<div class="span3" style="border:1px solid #5e5e5e;float:left;margin:6px 3px 6px 5px;background-color:#f5f5f5;">';
	//str+='</div>';

  str+='<h5 style="margin-left:15px;">'+newsString+'</h5>';


	for(var i=0;i<results.length;i++){

		 var categoryId = results[i].categoryId;

	   str+='<div class="span3" style="border:1px solid #5e5e5e;float:left;margin:6px 3px 6px 15px;background-color:#f5f5f5;">';

	   str+='<div style="text-align:center;margin-top:6px;font-family:verdana;">';
	   str+='<a>'+results[i].categoryName+'</a>';
	   str+='</div>';

		str+='<div style="margin-top:3px;">';

		if(results[i].highImpactCount != null)
			str+='<span class="badge badge-info" style="margin-left:30px">'+results[i].highImpactCount+'</span>';
		else
			str+='<span class="badge badge-info" style="margin-left:30px">0</span>';
		if(results[i].mediumImpactCount != null)
			str+='<span style="margin-left:29px" class="badge badge-info">'+results[i].mediumImpactCount+'</span>';
		else
			str+='<span style="margin-left:29px" class="badge badge-info">0</span>';
		if(results[i].lowImpactCount != null)
			str+='<span class="badge badge-info" style="margin-left: 22px;">'+results[i].lowImpactCount+'</span>';
		else
			str+='<span class="badge badge-info" style="margin-left: 22px;">0</span>';
		str+='</div>';
		str+='<div style="margin-top:3px;">';
			str+='<a href="javaScript:{getNews1(3,'+categoryId+','+results[i].highImpactCount+');}" class="linkClass" style="margin-left:34px">High</a>';
			str+='<a href="javaScript:{getNews1(2,'+categoryId+','+results[i].mediumImpactCount+');}" class="linkClass" style="margin-left:25px" >Medium</a>';
			str+='<a href="javaScript:{getNews1(1,'+categoryId+','+results[i].lowImpactCount+');}" class="linkClass" style="margin-left: 26px;">Low</a>';
		str+='</div>';

		str+='</div>';
	}

	str+='</div>';


	 $('#newsCountDiv').html(str);
}
var displayStr;
var categoryStr;
function getNews1(importanceId , categoryId , count){
	 categoryStr='';
	 categoryStr+=' Category :';
	 categoryStr+='<font style="color:red;">';
	if(categoryId == 1) 
		categoryStr+="Problems";
	else if(categoryId == 2)
		categoryStr+="Development Activity";
	else if(categoryId == 3)
		categoryStr+="Political Movements ";
	else if(categoryId == 4)
		categoryStr+="Political Movements - Opp Parties";
	else if(categoryId == 5)
		categoryStr+="Events";
	else if(categoryId == 6)
		categoryStr+="Profile News";
	categoryStr+='</font>';
	

    displayStr='';
	if(importanceId == 3){
		displayStr+='High level impacted News :<font style="color:red;">'+count+'</font>';
	}
	else if (importanceId == 2){
		displayStr+='Medium level impacted News :<font style="color:red;">'+count+'</font>';;
	}
	else if (importanceId == 1){
		displayStr+='low level impacted News :<font style="color:red;">'+count+'</font>';;
	}

		startIndex = 0;
	    lastIndex = 10;
		getNews(importanceId , categoryId);

}

var startIndex;
var lastIndex;
function getNews(importanceId , categoryId){


	var jObj=
	{
		locationValue:locationValue,
		locationId:locationId,
		publicationId:0,
		importanceId:importanceId,
		categoryId:categoryId,
		startIndex:startIndex,
	    lastIndex:lastIndex,
		task:"getNewsByLocation"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getNewsByLocation.action?"+rparam;	

	callAjaxToShowNewsDetails(jObj,url);

}


function displayNewsByImportance(jObj,results){

     var importanceId = jObj.importanceId;
	 var  categoryId = jObj.categoryId;


  var str='';

 // str+='<div style="margin:13px;"><b>'++'</b></div>';
str+='<div style="margin:13px;"><b>'+titleString+"  "+categoryStr+" "+displayStr+'</b></div>';

	for(var i=0;i<results.length;i++){

		str+='<h6 style="margin:5px;"><img src="./images/icons/bullet.png" style="margin-bottom:2px;"/><a style="font-family:Georgia,Times New Roman,Times,serif;color:#3E78FD;" href="javaScript:{getVideoDetails('+results[i].contentId+');}">'+results[i].title+'</a></h6>';
	}

	if(results.length >=10)
		str+='<h6 style="margin:5px;text-align:center;"><a class="btn btn-mini btn-info" style="color:#fff;float:right;" href="javaScript:{incrementStartEndIndexes('+importanceId+','+categoryId+');}">Next .....</a></h6>';

  $('#newsDisplayDiv').html(str);

  $('#newsDisplayOuterDiv').dialog({    title:'News Details',
	                            height: 'auto',
								width: 950,
								closeOnEscape: false,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 950,
								overlay: { opacity: 0.5, background: 'black'}

  });

}


function incrementStartEndIndexes(importanceId,categoryId){
	startIndex = startIndex + 10;
	lastIndex =  10 ; 

	getNews(importanceId , categoryId);

}

function getProblemsByLocation()
{
	document.getElementById('problemsCountDiv').style.display = 'none';
	var level = $("#reportLevel").val();
	var locationId =0;
	var type = '';
	var locationValue =0;
	if(level == 1){
		locationValue = $("#constituencyList").val();
		locationId = 4;
	}
	else if(level == 2)
	{
		locationValue = $("#mandalField").val();
		locationId = 5;
	}
	else if(level == 3)
	{
		locationValue = $("#panchayatField").val();
		locationId = 3;
		return;
	}
	else if(level == 4)
	{
		locationValue = $("#pollingStationField").val();
		locationId = 9;
		return;
	}
	var jObj=
	{
		locationValue:locationValue,
		locationId:locationId,
		//publicationId:0,
		
		task:"getProblemsByLocation"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getProblemsByLocation.action?"+rparam;	

	callAjaxToShowProblemDetails(jObj,url);
}

/*function buildProblemsCountByLocation(results,jsObj)
{
	
	var str='';
	var divEle = document.getElementById('problemsCountDiv');
	$("#problemsCountDiv").css({'float':'left','margin':'6px 3px 6px 12px','background-color':'#f5f5f5','width':'940px'});
	str+='<div style="margin-left:10px;width:800px;"> ';
	str+='<div class="span4"> ';
	str+='<h5 style="margin-left:10px;">Problems</h5>';
	str +='<div class="span1 breadcrumb center"><span class="badge badge-info badge-add" style="background:#55FE56;">'+results[0].fixedProblems+'</span><div>Resolved</div></div>&nbsp;&nbsp;&nbsp;&nbsp;';
	str +='<div class="span1 breadcrumb center"><span class="badge badge-info badge-add">'+results[0].progressProblems+'</span><div>Progress</div></div>&nbsp;&nbsp;&nbsp;&nbsp;';
	str +='<div class="span1 breadcrumb center"><span class="badge badge-info badge-add">'+results[0].pendingProblems+'</span><div>Pending</div></div>&nbsp;&nbsp;&nbsp;&nbsp;';
	str+='</div> ';
	str+='<div class="span4 breadcrumb"> ';
	str+='<h5 style="margin-left:10px;">NEW</h5>';
	str +='<div class="span1 breadcrumb"><span class="badge badge-info badge-add">'+results[0].cadreProblems+'</span><div>Cadre</div></div>';
	str +='<div class="span1 breadcrumb"><span class="badge badge-info badge-add">'+results[0].userProblems+'</span><div>PAUser</div></div>';
	str+='</div> ';
	str+='</div> ';
	divEle.innerHTML=str;
	
}*/


function buildProblemsCountByLocation(results,jsObj)
{
	
	document.getElementById('problemsCountDiv').style.display = 'block';
	var str='';
	var divEle = document.getElementById('problemsCountDiv');
	$("#problemsCountDiv").css({'float':'left','margin':'6px 3px 6px 12px','background-color':'#f5f5f5','width':'940px'});
	str+='<div class="row breadcrumb"> ';
	str+='<div class="span11 breadcrumb"><h5>Problems</h5></div>';
	str+='<div class="span5"> ';
	str+='<div class="row-fluid"> ';
	str +='<div class="span4 center"><span class="badge badge-info badge-add" style="background:#55FE56;">'+results[0].fixedProblems+'</span><div>Resolved</div></div>&nbsp;&nbsp;&nbsp;&nbsp;';
	str +='<div class="span4 center"><span class="badge badge-info badge-add">'+results[0].progressProblems+'</span><div>Progress</div></div>&nbsp;&nbsp;&nbsp;&nbsp;';
	str +='<div class="span4 center" style="margin-top:-15px;"><span class="badge badge-info badge-add">'+results[0].pendingProblems+'</span><div>Pending</div></div>&nbsp;&nbsp;&nbsp;&nbsp;';
	str+='</div>';
	str+='</div>';
	str+='<div class="span5" style="padding:5px 0px 5px 0px;border:1px solid #d3d3d3;"> ';
	str+='<div class="row-fluid"> ';
	str +='<div class="span2"><h5 style="margin-left:10px;">NEW</h5></div>';
	str +='<div class="span4"><span class="badge badge-info badge-add" style="background:red;">'+results[0].cadreProblems+'</span><div>Cadre</div></div>';
	str +='<div class="span4"><span class="badge badge-info badge-add" style="background:red;">'+results[0].userProblems+'</span><div>PAUser</div></div>';
	str+='</div> ';
	str+='</div> ';

	str+='</div> ';
	divEle.innerHTML=str;
	
}