<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- YUI Dependency files (Start) -->
<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script>  
<script type="text/javascript" src="js/yahoo/element-min.js"></script> 	
<script src="js/yahoo/resize-min.js"></script> 
<script src="js/yahoo/layout-min.js"></script>  
<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
<script type="text/javascript" src="js/json/json-min.js"></script>
<script type="text/javascript" src="js/yahoo/connection-min.js"></script>  
<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>   
<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
<!-- Skin CSS files resize.css must load before layout.css -->  
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
<!-- YUI Dependency files (End) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
<style type="text/css">

.ui-dialog .ui-dialog-title {
    float: left;
    font-family: arial;
    font-size: 14px;
    margin: 0.1em 16px 0.2em 0;
}
#newsDisplayDiv
{
	font-family: arial;
    font-size: 16px;
}
.alert-info {
    background-color: #D9EDF7;
    border-color: #BCE8F1;
    color: #3A87AD;
}
.alert {
    background-color: powderblue;
    border: 1px solid #FBEED5;
    border-radius: 4px 4px 4px 4px;
    color: #C09853;
    margin-bottom: 20px;
    padding: 8px 35px 8px 14px;
    text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
}
.nextOrPrvButton
{
	background-color: #49AFCD;
    border-radius: 4px 4px 4px 4px;
    color: white;
    float: right;
    height: 13px;
    padding: 10px;
    width: 83px;

}

.ui-dialog .ui-dialog-buttonpane button {
    cursor: pointer;
    font-family: arial;
    font-size: 15px;
    margin: 0.5em 0.4em 0.5em 0;
}
</style>
<title>News Glance</title>
<script type="text/javascript">
/*
	These are the Global variables for Accessing these values any where in the application
*/
var  categoryId    = '${categoryId}';
var  importanceId  = '${importanceId}';
var  locationId    = '${locationId}'; 
var  locationValue = '${locationValue}';
var  publicationId = '${publicationId}';
var  count         = '${count}';
var displayStr;
var categoryStr;
var startIndex;
var lastIndex;
var result;
var showContentResultList;
var commentsObject = new Object();

/*
	This Method is Used For Getting The New Glance Type i.e
	those are Problems , Development Activity etc...
*/
function getNews1(count){
    startIndex = 0;
	lastIndex = 10;
var noNews = false;
	if(count== null){
	alert("No news Exist");
	noNews = true;
	} 
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

	categoryStr+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
	

    displayStr='';
	if(importanceId == 3){
	displayStr+='Impact Level :<span style="color:red;">High</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>Count </span>:<font style="color:red;">'+count+'</font>';;
	}
	else if (importanceId == 2){
	displayStr+='Impact Level :<span style="color:red;">Medium</span>&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;<span>Count </span>:<font style="color:red;">'+count+'</font>';;
	}
	else if (importanceId == 1){
	displayStr+='Impact Level :<span style="color:red;">Low</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>Count </span>:<font style="color:red;">'+count+'</font>';
	}
	

	if(noNews == false){
	$('#newsAjaxImage').css('display','block');
		getNewsGlanceDetails();
	}

}

/*
	This Method is Used For the Logic of Previous button working strategy
*/
function decrementStartEndIndexes(){

	startIndex = startIndex - 10;
	lastIndex =  10 ; 

	getNewsGlanceDetails();
}

/*
	This Method is Used For the Logic of Next button working strategy
*/
function incrementStartEndIndexes(){

	startIndex = startIndex + 10;
	lastIndex =  10 ; 

	getNewsGlanceDetails();

}
/*
	This Method is Used for making a ajax Call For getting News Glance Details
*/
function getNewsGlanceDetails()
{
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

/*
	This Method is used for handling the ajax calls and calling respective methods to build the data
*/
function callAjaxToShowNewsDetails(jObj,url){
		var myResults;
        var callback = {			
 		success : function( o ) {
		try {
		myResults = YAHOO.lang.JSON.parse(o.responseText);
		if(jObj.task == "getNewsByLocation"){
        displayNewsByImportance(jObj,myResults);
        $('#newsAjaxImage').css('display','none');
		}
		else if(jObj.task="getNewsOfLocationInPopup"){
		 $("#showAjaxImgForNews").hide();
		showContentResultList = myResults;
		buildContentDetails();
		}
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
/*
	This method is used for building the news Glance details of the particular link is clicked
*/
function displayNewsByImportance(jObj,results){
	
	var importanceId = jObj.importanceId;
	var  categoryId = jObj.categoryId;
	var str='';
	str+='<div style="margin:13px;"><b>'+categoryStr+' '+displayStr+'</b></div>';
  
	for(var i=0;i<results.length;i++){
	var description=results[i].description;

	str+='<div style="height:50px;margin-bottom:4px;"  class="alert alert-info">';

    str+='<div style="height: 50px; float: left; width: 480px;">';
	 str+='<span style="color:black">';

	if(results[i].title.length > 100)
	//str+='<a style="font-family:verdana;color:#686868;font-size:14px;font-weight:bold;" title="'+description+'" href="javaScript:{getNewsInPopup('+results[i].contentId+','+importanceId+','+categoryId+');}">'+results[i].title.substring(0,100)+'...</a>';
	str+='<a style="font-family:verdana;color:#686868;font-size:14px;font-weight:bold;" title="'+description+'" href="javaScript:{getNewsInPopupWindow('+results[i].contentId+','+importanceId+','+categoryId+');}">'+results[i].title.substring(0,100)+'...</a>';
	else
 	//str+='<a style="font-family:verdana;color:#686868;font-size:14px;font-weight:bold;" title="'+description+'" href="javaScript:{getNewsInPopup('+results[i].contentId+','+importanceId+','+categoryId+');}">'+results[i].title+'</a>';
	str+='<a style="font-family:verdana;color:#686868;font-size:14px;font-weight:bold;" title="'+description+'" href="javaScript:{getNewsInPopupWindow('+results[i].contentId+','+importanceId+','+categoryId+');}">'+results[i].title+'</a>';
	str+='</span>';
    str+='</div>';

    if(categoryId == "1"){
	 str+='<div style="height: 50px; width: 181px; float: left;">';
	}else{
		str+='<div style="height: 50px; width: 181px; float: right;">';
	}
	str+='<span style="float: left; clear: right;">';
	str+='<span style="color:sienna;font-size:13px;">'+results[i].source+'  ('+results[i].fileDate+')</span>';
    str+='</span>';

	if(results[i].locationScopeValue != "MUNICIPAL-CORP-GMC")
	str+='<span style="float: left; clear: left;text-transform:capitalize;font-size:13px;font-weight:bold;">'+results[i].locationName.toLowerCase()+'-<span style="color:currentcolor;font-weight:bold;">'+results[i].locationScopeValue.toLowerCase()+'</span></span>';
	else
 	str+='<span style="float: left; clear: left;text-transform:capitalize;font-size:13px;font-weight:bold;">'+results[i].locationName.toLowerCase()+'-<span style="color:currentcolor;font-weight:bold;">Muncipality</span></span>';
	str+='</div>';

	str+='<div style="float: right; height: 50px; width: 178px; ">';
	  if(categoryId == "1"){

	     if(results[i].isProblem == "false"){
	       str+='<a id=notAdded'+results[i].contentId+' style="float: right; color: green;" href="javaScript:{changeToProblem('+results[i].contentId+')}">Post as problem</a>';
   		   str+='<span style="margin-left:18px;" id=added'+results[i].contentId+'> <a   title="Click here to view  problem details" style="float:right;color:red;display:none;" href="javaScript:{}">&nbsp;&nbsp;<img src="images/icons/details.png"></img></a></span>';

		 }else{
		   str+='<a  style="display:none;" id=notAdded'+results[i].contentId+' style="float:right;color:red;" href="javaScript:{changeToProblem('+results[i].contentId+')}" style="color:green;">Make it as problem</a>';

		   str+='<a  id=added'+results[i].contentId+' title="Click here to view problem details" style="float:right;color:red;" href="javaScript:{showProblemDetails('+results[i].problemId+');}" style="color:green;">Added as problem &nbsp;<img src="images/icons/details.png"></img></a>';


		 }

	  }
	str+='</div>';
  str+='</div>';

  }
    if(startIndex > 1){
	
		str+='<h6 style="margin:5px;text-align:center;"><a class="nextOrPrvButton" style="float:left;" href="javaScript:{decrementStartEndIndexes();}"> << Previous </a></h6>';
	}
	if(results.length >=10)
	{
		str+='<h6 style="margin:5px;text-align:center;"><a class="nextOrPrvButton" style="float:right;" href="javaScript:{incrementStartEndIndexes();}">Next>></a></h6>';
	}
	
  $('#newsDisplayDiv').html(str);
  
}
/*
	This Method is used for change the news glance details into problem
*/
function changeToProblem(contentId){

	var str='';
	str+='<div>';
	str+='<div id="errorDiv"></div>';
	str+='<div id="successMsgDiv"></div>';
	str+='<span style="font-family: arial; font-size: 16px;">Existing From:<input type="text" name="date" class="dateField" id="existingFrom" readonly/></span>';
	//str+='<span>Visibility:<input type="text" id="visibility"></input></span>';
	str+='<span style="float: left; margin: 6px; font-family: arial; font-size: 16px;">Visibility:</span>&nbsp;&nbsp;&nbsp;&nbsp;';
	str+='<div><label title="This problem is not visible to public" style="float:left;margin:6px;font-family: arial;font-size: 16px;"><input style="margin:0px; " checked type="radio" name="visibility" value="private"/> Private </label>';
	str+='<label title="This problem is  visible to public" style="float:left;margin:6px; font-family: arial;font-size: 16px;"><input style="margin:0px;" type="radio" name="visibility" value="public"/> Public </label></div>';
	str+='</div>';


   $('#problemInnerDiv').html(str);

   $("#problemOuterDiv").dialog({ 
	                            title:'Add Problem',
	                            height: 'auto',
								width: 400,
								closeOnEscape: false,
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 850,
								overlay: { opacity: 0.5, background: 'black'},
	                             buttons: {
 							   "Add":function() {callAjaxToChangeToProblem(contentId)},
							   "Close":function() {$(this).dialog("close")}
								   }	

     });

}
/*
	this method is used for building the popup for particular news glance is clicked
*/
function getNewsInPopup(cntntId,imprtanceId,ctgryId)
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
		getNewsOfLocation(cntntId,imprtanceId,ctgryId);
}

function getNewsInPopupWindow(cntntId,imprtanceId,ctgryId)
{
	var urlstr = "newsGallaryDisplayWindowAction.action?contentId="+cntntId+"&importanceId="+imprtanceId+"&categoryId="+ctgryId+"&locationValue="+locationValue+"&locationId="+locationId+" "

	var browser1 = window.open(urlstr,"newsDetails","scrollbars=yes,height=600,width=800,left=200,top=200");	
		browser1.focus();
}
/*
	This Method is used for making the ajax call for getting the clicked news details 
*/
function getNewsOfLocation(cntntId,imprtanceId,ctgryId){
	 $("#showAjaxImgForNews").show();
	var jObj=
	{
		locationValue:locationValue,
		locationId:locationId,
		imprtanceId:imprtanceId,
		ctgryId:ctgryId,
		contentid:cntntId,
		requestFrom : 'Candidate Page',
		requestPageId :'0',
		isCustomer:'true',
		task:"getNewsOfLocationInPopup"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getNewsByLocationAndCategoryInPopup.action?"+rparam;	

	callAjaxToShowNewsDetails(jObj,url);

}
/*
	This Method is used for building the Clicked news glance details in a popup for detailed report
*/
function buildContentDetails()
{
  $("#showAjaxImgForNews").hide();
	result = showContentResultList;
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
	document.getElementById('ui-dialog-title-showContentDiv').innerHTML = '<font color="darkgreen"><b>'+result.contentType;

	str += '<Div><center>';
	str += '<div class="main-title-sec" style="clear:both;">';
	str += '<div id="showContentHeaderDiv" class="main-mbg" style="width:900px;border-radius:5px 5px 5px 5px;"></div><div class="main-bbg"/></div>';

	
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
			 str+='<div style="width:850px;font-family:verdana;">';
           
				 str+='<div style="float:left;font-family: arial;">';
				   str+='<span>CategoryType :</span>';

				   if(result.relatedGalleries[0].filesList[i].categoryType != null)

  				   str+='<span  style="color: red; font-family: arial; font-size: 17px;">'+result.relatedGalleries[0].filesList[i].categoryType+'</span>';
				 str+='</div>';

				 str+='<div style="font-family: arial;">';
				   if(result.relatedGalleries[0].filesList[i].locationScopeValue != null){
					   str+='<span>RegionScope :</span>';
				       str+='<span  style="color: red; font-family: arial; font-size: 17px;">'+result.relatedGalleries[0].filesList[i].locationScopeValue+'('+result.relatedGalleries[0].filesList[i].locationName+')</span>';
				   }

				 str+='</div>';

			 str+='</div>';

			 str+='<div style="width:850px;font-family:verdana;">';
           
				 str+='<div style="float:left;font-family: arial;">';
				 if(result.relatedGalleries[0].filesList[i].fileVOList[0].source != null)
				   str+='<span>Source :</span>';
  				   str+='<span id="sourceChangeSpan"  style="color: red; font-family: arial; font-size: 17px;">'+result.relatedGalleries[0].filesList[i].fileVOList[0].source+'</span>';
				 str+='</div>';

				 str+='<div style="font-family: arial;">';
				 if(result.relatedGalleries[0].filesList[i].fileDate != null)
				   str+='<span>Date :</span>';
  				   str+='<span  style="color: red; font-family: arial; font-size: 17px;">'+result.relatedGalleries[0].filesList[i].fileDate+'</span>';
				 str+='</div>';

			 str+='</div>';



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
	str += '</center></Div>';
	divEle.innerHTML = str;
	var str = '';
	str += ''+titleStr+' ('+curPos+' of '+totSize+')<span style="margin-top:10px;margin-right:18px;float:right">';
	str += '</span>';
	
	document.getElementById("showContentHeaderDiv").innerHTML=str;
	
}
/*
	This Method is used for making a alax call for adding news glance details into problem
*/
function callAjaxToChangeToProblem(contentId){

	$('#errorDiv').html('');


	if($('#existingFrom').val() == ""){
		$('#errorDiv').html('<span style="color:red;">Existing from date is required</span>');
	 return false;
	}

	var visibility = $('input:radio[name=visibility]:checked').val();


	var jObj=
	{
		existingFrom:$('#existingFrom').val(),
		//visibility:$('#visibility').val(),
		visibility:visibility,
		contentId:contentId,
		//contentId:12327,
		task:"convertNewsToProblem"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "convertNewsToProblem.action?"+rparam;	

	callAjaxToConvertNewsToProblem(jObj,url);
}
/*
	This method is used for Showing weather the new glance details are added as a problen or not
*/
function callAjaxToConvertNewsToProblem(jObj,url){
		
			 var myResults;

			 var callback = {			
 		              success : function( o ) {
					try {
						
				         myResults = YAHOO.lang.JSON.parse(o.responseText);
                          //alert("News successfully added as problem ");
						  var cssObj = {    
				           'font-weight' : 'bold',
				           'color' : 'green'
			              }
						 $('#successMsgDiv').text("News successfully added as problem").css(cssObj).show().delay(2000).fadeOut(400);
                          setTimeout(function(){
                                 $("#problemOuterDiv").dialog('close');
                      }, 2000);

						
						 $('#notAdded'+myResults.contentId).css('display','none');
 						 $('#added'+myResults.contentId).css('display','block');
						 $('#added'+myResults.contentId).html('<span>Added as problem&nbsp;<a href="javaScript:{showProblemDetails('+myResults.problemId+')}"><img src="images/icons/details.png"></img></a></span>');
							
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
/*
	This method is used for showing the problem details in new window
*/
function showProblemDetails(problemId)
{
  window.open("completeProblemDetailsAction.action?problemId="+problemId, '_blank');
  window.focus();
}
/*
	This Method is used for showing the details of nexe news
*/
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
/* 
	This method for same news in different source updated by Srishailam (Mahesh) 
*/

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
/*
	This method used to same news in more than two pages like part1 , part2, ..... updated by srishailam
*/

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

$(document).ready(function(){
$(".dateField").live("click", function(){
$(this).datepicker({
dateFormat: "dd/mm/yy",
changeMonth: true,
changeYear: true,
maxDate: new Date()
}).datepicker("show");
});
});
getNews1(count);

</script>
</head>
<body>

<div id="newsDisplayDiv" style=""></div>
<div id="problemOuterDiv">
<div id="problemInnerDiv">
</div>
</div>
<div id="showContentDiv">
<div id="showAjaxImgForNews" style="display:none"><img src="images/icons/goldAjaxLoad.gif"/></div>
<div id="showContentDivInnerDiv" style="font-family: arial; font-size: 17px;"></div>
</div>
</body>
</html>
