<%@ page language="java" contentType="text/html; charset=utf-8" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <META http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> TDP News Portal </title>

<!-- <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>-->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
	<script type="text/javascript" src="js/jquery.google.api/jquery.min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	 
	<!-- YUI Skin Sam -->
<!-- YUI Dependency files (End) -->
<script type="text/javascript" src="js/simplePagination/gallaryResponsePagination.js" ></script>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 
<!--  <script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script> -->
<script type="text/javascript" src="js/jquery.google.api/jquery.2.8.2.combo.js"></script>
 <!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" /> -->
 <link  rel="stylesheet" type="text/css" href="js/jquery.google.api/jquery-ui1.10.3.css"/>
<!-- <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script> -->
 <script type="text/javascript" src="js/jquery.google.api/jquery-ui.js"></script>

 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 

<style type="text/css">
 #showContentHeaderDiv{background-color: #06ABEA;
    color: #FFFFFF;
    float: none;
    font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    padding-left: 13px;
    text-align: left;
    border-radius:5px;}
	
#buildSources, #buildNewSources {
    border: 1px solid #D3D3D3;
    margin: 10px auto 20px;
    width: 618px;
}
.tableCls{margin: 10px auto; float: none;}
 
 .main-mbg{float:none; background-color: #06ABEA;
    color: #FFFFFF;
    font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    padding-left: 13px;
    text-align: left;
    text-transform: uppercase;
    width: 974px;}
 #newDisplayMainDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 980px;
	margin-top:20px;
	margin-bottom:15px;
	padding-bottom: 52px;
	padding-top: 16px;}
#newstitleSourceDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    margin-top: 10px;
    text-align: center;
    width: 400px;}
	#newsImage{margin-top: 13px;}
	#newsImage img{height: 152px;
    width: 450px;}

.popupcontainer {
    background-color: #FFFFFF;
    box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
    margin: 9px auto 10px;
    max-width: 780px;
    padding: 10px;
}
h3 {
    font-size: 18px;
    line-height: 27px;
}

	#descriptionDiv{margin-left: 32px; margin-top: 20px;}
	.commentdate {
    color: #888888;
    font-size: 10px;
}
#rattingDiv {
    border: 1px solid #D3D3D3;
    height: 109px;
    border-radius: 3px 3px 3px 3px;
    margin-left: 32px;
    margin-top: 12px;
    padding-bottom: 17px;
    padding-top: 10px;
    width: 680px;
}
.headingStyle{background-color: #06ABEA;
    color: #FFFFFF;
    float: none;
    font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    padding-left: 13px;
    text-align: left;
    text-transform: uppercase; padding: 6px 35px;border-radius:4px;}
.left-panel {
    background-color: #FBFBFB;
    background-image: -moz-linear-gradient(center top , #CCCCCC, #F5F5F5);
    background-repeat: repeat-x;
    border: 1px solid #DDDDDD;
    border-radius: 3px 3px 3px 3px;
    box-shadow: 0 1px 0 #CCCCCC inset;
    list-style: none outside none;
    
    padding: 5px;
}
.relatedproblem > li {
    background-color: #FBFBFB;
    background-image: -moz-linear-gradient(center top , #FFFFFF, #F5F5F5);
    background-repeat: repeat-x;
    border: 1px solid #DDDDDD;
    border-radius: 3px 3px 3px 3px;
    box-shadow: 0 1px 0 #FFFFFF inset;
    cursor: pointer;
    margin-top: 10px;
    padding: 5px;
}
.newssources{
 background-color:#97DFEB;
 padding:8px 8px 8px 8px;
 margin-left:5px;
 border-radius: 5px 5px 5px 5px;
 display:inline-block;
 font-weight: bold;
}
.newsParts{
  
  color:#FF4500;
}
#releatedNewsDiv{height: 200px;
    overflow-y: scroll;}

@font-face{ font-family: 'eFont'; src: url('fonts/eenadu.eot');}
@font-face {
    font-family: "eFont";
    font-style: normal;
    font-weight: normal;
    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
}

.enadu
{
font-family: eFont;
font-size:20px;
}
#newsResponseDiv{clear:both;display:table;margin-top:20px;backGround:#FFF;}
.blue:before {
    background: none repeat scroll 0 0 #548BD4;
    content: " ";
    height: 5px;
    left: 0;
    position: absolute;
    top: 0px;
    width: 45px;
}
.widget {
    background: none repeat scroll 0 0 #FAFAFA;
    border-top: 5px solid #000000;
    box-shadow: 0 0 1px rgba(0, 0, 0, 0.3);
    margin: 10px 0 20px;
    position: relative;
}
.blue {
    color: #2A4F97;
}
.widget h4, h2 {
    font-family: arial;
}
.widget h4, h2 {
    border-bottom: 1px solid #C0C0C0;
     font-size: 14px;
    font-weight: bold;
    line-height: 20px;
      padding-left: 10px;
    text-rendering: optimizelegibility;
    text-transform: uppercase;
	 color: #000000;
    
}
#paginationId{margin-bottom: 15px;margin-top: 10px;}
#newsResponseDiv{float: none;margin-left: auto;margin-right: auto;width: 900px;}
#responseNewsGallaryDiv ul li{  margin-left: 20px;}
#gallaryMainDiv{margin-bottom:20px;}
#gallaryDiv{height:220px;}
#newsDescriptionHeadingDiv{ color: #0088CC;
     font-size: 15px;
    font-weight: bold;margin-right: 2px;}
#newsPageNOSpan,#newsChangeEdition{margin-left: 15px;}
#imgDiv{text-align: center;}
#responseNewsCountImg{height: 30px; width: 40px; margin-right: 3px;cursor: pointer;}

li{list-style: none outside none;}
#responseNewsUL{overflow-y: scroll; height: 700px;}
</style>
</head>
<body>
<div id="newDisplayMainDiv">
 <div id="newDisplayInnerDiv">
   <div class="span12" style="margin-top:25px;margin-bottom:30px;">
    <div class="row">
     
	 <div id="ajaxImg" style="display:none;text-align: center;"><img src="images/icons/goldAjaxLoad.gif" /></div>

	 <!-- left Div Start -->
	  <div class="span12 right-panel m5-left" style="background:#ffffff;position:relative;">
	  <div id="newsDetailsDiv"></div>
		<!-- <div id="showContentHeaderDiv"></div>
		<div id="newstitleSourceDiv"></div>
	    <div style="text-align:center;" id="newsImage" class="popupcontainer"></div>
		 <div id="descriptionDiv"><table><tr><td>Description : <span id="desc"></span></td></tr></table></div>
		  <div id="newsDisplayDiv"></div>-->

		 <div>
		<div style="display:none;" class="errorClass" id="errormsgdiv"></div>
		<span id="newsDescriptionHeadingDiv"></span>
		<span id="newsDescriptionDiv"></span>
		<!--<h3>Comments:</h3>-->
		 <div class="commentSection">
		 <!--<textarea style="width:100%;" id="commenttext" class="textareaid"></textarea>--></div><!--<a style="margin-top:4px;margin-bottom:4px;" class="pull-right btn btn-info"  href="javascript:{}">Post</a>-->
		 <span id="newsDescriptionHeadingDiv"></span>
		 <div id="newsDescriptionDiv"></div>
		 <div style="padding-top: 0px; margin-left: 290px; margin-top: 36px; margin-bottom: -29px;" id="abusedErrorDiv"></div>
         <div style="display:none;" id="postedcomments"></div>	
		</div>

	</div>
	<!-- left Div End -->

	<!-- right Div End -->
    <div class="span3" style=" width: 250px;display:none;">
	
	<div class="left-panel" id="gallaryMainDiv" style="display:none;">
	    <h3>Main Article</h3>
	   <div id="gallaryDiv"></div>
	   <div class="text-center">
	   <div id="paginationDiv"></div>
	   </div>
	</div>

	  <div class="left-panel">
	    <h3 id="gallaryNameId" style="text-transform:capitalize;">Other News in this Gallery</h3>
	   <div id="releatedNewsDiv"></div>
	  </div>
	</div>
	<!-- right Div End -->
  </div>
 </div>
 </div>

  <div id="responseMainDiv">
  <div class="widget blue" id="newsResponseDiv">
	<h4 id="responseHeading"></h4>
	<div id="responseNewsGallaryDiv"></div>
		<!----pagination Div----->
		<!-- <div class="span12 text-center">
			<div id="paginationId"></div>						
		</div> -->
  </div>
</div>
</div>	
 

<script type="text/javascript">
var contentId = "${contentId}";
var showContentResultList = null;
var selectedContentFile;

 function getContentDetails()
 {
	$("#ajaxImg").css("display","block");
	var jsObj =
		{   
		    contentId : contentId,
			requestFrom : 'Party Page',
			requestPageId : 872,
			task:"getSelectedContent"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSelectedContentAndRelatedGalleriesAction.action?"+rparam;					callAjax(jsObj,url); 
}

function callAjax(jsObj,url)
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
			 if(jsObj.task == "getResponseGallaryDetails")
			{
			  showResponseGallaryDetails(myResults,jsObj);
			}
			if(jsObj.task == "getMainArticleDetails")
			{
			 showMainArticles(myResults,jsObj);
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


function buildContentDetails()
{
  $("#ajaxImg").css("display","none");
  result = showContentResultList;
	if(result == null)
		return;
	$("#newsDetailsDiv").html('');
    var str = '';
	var titleStr = null;
	var pathStr = null;
	var descriptionStr = null;
	var preContentId = null;
	var curPos = null;
	var totSize = null;
	var source = "";
	var descSource = "";
	var totSize = null;
	var detaildesc = null;
	var detaildescsource = null;
    $("#newsDescriptionDiv").html('');
	$("#newsDescriptionHeadingDiv").html('');
   for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
	if(result.relatedGalleries[0].filesList[i].isSelectedContent)
	{
	   source = result.relatedGalleries[0].filesList[i].eenadu;
	   descSource = result.relatedGalleries[0].filesList[i].descEenadu;
	   if(result.relatedGalleries[0].filesList[i].gallaryName != null && result.relatedGalleries[0].filesList[i].gallaryName != ""){
		$('#gallaryNameId').html('');
	   	$('#gallaryNameId').html('Other News in Gallery: '+(result.relatedGalleries[0].filesList[i].gallaryName));
		}
	   if (result.relatedGalleries[0].filesList[i].newsDescription != null && result.relatedGalleries[0].filesList[i].newsDescription != "")
	   {  
		  if(source) $('#newsDescriptionDiv').addClass("enadu").html(result.relatedGalleries[0].filesList[i].newsDescription);
		  else
	       $('#newsDescriptionDiv').removeClass("enadu").html(result.relatedGalleries[0].filesList[i].newsDescription);
		   $("#newsDescriptionHeadingDiv").html("Detailed News: ");
	   }
	   

		
	    selectedContentFile = result.relatedGalleries[0].filesList[i];
		titleStr = result.relatedGalleries[0].filesList[i].title;
		if(result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList != null && result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList.length > 0)
		 pathStr = result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path;
		else
		 pathStr = null;
		descriptionStr = result.relatedGalleries[0].filesList[i].description;
		
		preContentId = result.relatedGalleries[0].filesList[i].contentId;
		detaildesc =  result.relatedGalleries[0].filesList[i].fileVOList[0].description;
		detaildescsource = result.relatedGalleries[0].filesList[i].fileVOList[0].eenadu;
		curPos = i+1;
		totSize = result.relatedGalleries[0].filesList.length;

		if(source)
		{
			str +='<div id="showContentHeaderDiv"><span class="enadu">'+titleStr+' </span> ('+curPos+' of '+totSize+')</div>';
		}
		else
		{
			str +='<div id="showContentHeaderDiv" style="text-transform: uppercase;">'+titleStr+' ('+curPos+' of '+totSize+')</div>';
		}
		
		
		str+='<table class="tableCls">';
			str+='<tr>';
			str+='<td>';
			if(result.relatedGalleries[0].filesList[i].fileVOList[0].source != null)
				str+='<B>Source</B> : <font color="#FF4500"><span id="sourceChangeSpan">'+result.relatedGalleries[0].filesList[i].fileVOList[0].source+'</span></font> &nbsp;&nbsp;&nbsp;<B>';

			if(result.relatedGalleries[0].filesList[i].fileDate != null)
				str+=' Date </B>:<font color="#FF4500"> '+result.relatedGalleries[0].filesList[i].fileDate+'</font>';
			 
			if(result.relatedGalleries[0].filesList[i].fileVOList[0].newsEdition != null)
				str +='<span id="newsChangeEdition"><b>Edition</b> : <font color="#FF4500">'+result.relatedGalleries[0].filesList[i].fileVOList[0].newsEdition+'</font></span>';
			
			if(result.relatedGalleries[0].filesList[i].fileVOList[0].pageNo != null)
				str +='<span id="newsPageNOSpan"><b>Page NO</b> : <font color="#FF4500">'+result.relatedGalleries[0].filesList[i].fileVOList[0].pageNo+'</font></span>';

			 str+='</td>';
			 str+='</tr>';
			 str+='</table>';
			  str+='<table class="tableCls">';
            
			// if(result.relatedGalleries[0].filesList[i].locationName != null && result.relatedGalleries[0].filesList[i].locationName != "")
			//  str +='<tr><td><b>Location </b>: <font color="#FF4500"><span> '+result.relatedGalleries[0].filesList[i].locationScopeValue+' : '+result.relatedGalleries[0].filesList[i].locationName+'</font></span></td></tr>';
		    if(result.relatedGalleries[0].filesList[i].locationScopeValue == 'DISTRICT' || result.relatedGalleries[0].filesList[i].locationScopeValue == 'CONSTITUENCY'|| result.relatedGalleries[0].filesList[i].locationScopeValue == 'STATE'){
		    str +='<tr><td><b>Location </b>: <font color="#FF4500"><span>'+result.relatedGalleries[0].filesList[i].locationScopeValue+': '+result.relatedGalleries[0].filesList[i].locationName+'</i></h5>';
			str+='</hgroup>';		 
	       }
	       else{
			str +='<tr><td><b>Location </b>: <font color="#FF4500"><span>'+result.relatedGalleries[0].filesList[i].locationName+'</i></h5>';
			str+='</hgroup>';		 
	       }
              str +='<tr>';
			 if(result.relatedGalleries[0].filesList[i].candidateName != null && result.relatedGalleries[0].filesList[i].candidateName != "")
			  str +='<td><b>Candidate(s) </b>: <font color="#FF4500"><span> '+result.relatedGalleries[0].filesList[i].candidateName+'</font></span></td>';
			 // if(result.relatedGalleries[0].filesList[i].keyWordsList != null && result.relatedGalleries[0].filesList[i].keyWordsList.length>0)
		     //str +='<td><b style="margin-left: 15px;">Keywords</b>  : <font color="#FF4500">'+result.relatedGalleries[0].filesList[i].keyWordsList+'</font></td>';
			if(result.relatedGalleries[0].filesList[i].responseCount > 0)
			str+='<td><p><span class="text-error" style="font-weight: bold; margin-left: 15px;"><a href="showNewsResponseAction.action?responseContentId ='+result.relatedGalleries[0].filesList[i].contentId+' "><img alt="response count" title="Response Count" src="images/responseCountIcon.png" id="responseNewsCountImg" /></span>'+result.relatedGalleries[0].filesList[i].responseCount +'</a></p></td>';
			 str +='</tr>';
			 if(result.relatedGalleries[0].filesList[i].categoryName != null && result.relatedGalleries[0].filesList[i].categoryName != "")
			  str +='<tr><td colspan="2"><b>Category(s) </b>: <font color="#FF4500"><span> '+result.relatedGalleries[0].filesList[i].categoryName+'</font></span></td></tr>';
			if(result.relatedGalleries[0].filesList[i].keywords != null && result.relatedGalleries[0].filesList[i].keywords != "")
			  str +='<tr><td colspan="2"><b>keyword(s) </b>: <font color="#FF4500"><span> '+result.relatedGalleries[0].filesList[i].keywords+'</font></span></td></tr>';
			 str+='</table>';
			   if(descSource)
				{
					str +='<div><span>Description: </span><b class="enadu">'+descriptionStr+'</b>';
				}
				else
				{
					str +='<div><span><b>Description: </b></span>'+descriptionStr+'</div>';
				}
		   if(pathStr != null){
			str +='<div id="imgDiv" class="popupcontainer"><img   style="max-width:600px;max-length:800px;" src="'+pathStr+'" /></div>';
			str +='<div id="zoomImageDiv" class="popupcontainer" style="display:none;"><img  style="width:950px;height:850px;" src="'+pathStr+'" /></div>';
			}else{
			 str +='<div id="imgDiv" style="display:none;" class="popupcontainer"></div>';
			str +='<div id="zoomImageDiv" class="popupcontainer" style="display:none;"></div>';
			
			}
		if(detaildesc != null && detaildesc !=''){
		   if(detaildescsource)
		     str +='<div id="detaildescoffile"><b>Detail Description :</b><span class="enadu">'+detaildesc+'</span></div>';
		   else
		     str +='<div id="detaildescoffile"><b>Detail Description :</b>'+detaildesc+'</div>';
		}else{
		     str +='<div id="detaildescoffile"></div>';
		}
		
		str +='<button class="btn btn-info" onclick="getZoomImage()" style="float:right;"> Zoom Image </button> </div>';
	}

	for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		if(result.relatedGalleries[0].filesList[i].isSelectedContent)
		{
		   selectedContentFile = result.relatedGalleries[0].filesList[i];
		   str +='<div id="buildNewSourceParts">';
	       str += '<center><table><tr>';
			
	         for(var j=1;j<selectedContentFile.fileVOList[0].fileVOList.length;j++)
	         {
	           if(selectedContentFile.fileVOList[0].fileVOList[j].path != null)
	            str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[0].fileSourceLanguageId+','+selectedContentFile.fileVOList[0].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[0].fileVOList[j].path+'\',\'other\')"><img  width="65" height="60"   src="'+selectedContentFile.fileVOList[0].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[0].fileVOList[j].orderName+'</a></td>';
	         }
		 
	       str += '  </tr></table>';
	       str +='</center></div>';
	
	   if(selectedContentFile.multipleSource >1 )
	          {
	             str +='<div id="buildNewSources">';
	             str += '<center><table><tr><td><b>Same News in another source</b></td></tr></table></center>';
	             str += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
	           
			      for(var k=1;k<selectedContentFile.fileVOList.length;k++)
	              {
	               str += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[k].fileSourceLanguageId+',\'other\')">'+selectedContentFile.fileVOList[k].source+'</a></td>';
	              }
	            str += '  </tr></table>';
	            str +='</center></div>';
	          }
		}
		$("#newsDetailsDiv").html(str);


	var releatedGallary = '';
	releatedGallary +="<ul class='unstyled relatedproblem' style='width:220px;'>";

    for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
	if(!result.relatedGalleries[0].filesList[i].isSelectedContent)
	{
	var source = result.relatedGalleries[0].filesList[i].fileVOList[0].source;;
	if(source == "Eenadu Telugu")
	{
		releatedGallary += '<li><a href="javascript:{}" style="font-weight:bold;" onClick="buildContentDetailsOfSelected('+preContentId+','+result.relatedGalleries[0].filesList[i].contentId+')" class="enadu">'+result.relatedGalleries[0].filesList[i].title+'</a></li>';
	}
	else
	{
		releatedGallary += '<li><a href="javascript:{}"  onClick="buildContentDetailsOfSelected('+preContentId+','+result.relatedGalleries[0].filesList[i].contentId+')">'+result.relatedGalleries[0].filesList[i].title+'</a></li>';
	}
      
	  
	}
	releatedGallary +='</ul>';
	 $("#releatedNewsDiv").addClass("releatedNewsDiv").html(releatedGallary);


  for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
  if(result.relatedGalleries[0].filesList[i].isSelectedContent)
  {
    
	//main artical
	if(result.relatedGalleries[0].filesList[i].mainArticalExist)
     buildMainArticals(result.relatedGalleries[0].filesList[i].contentId);
	else
	  {
		$("#releatedNewsDiv").css("height","400");
	    $("#gallaryMainDiv").css("display","none");
	
	  }

    //response
    if(result.relatedGalleries[0].filesList[i].responseExist)
     buildResponseNews(result.relatedGalleries[0].filesList[i].contentId);
	else
	  {
		$("#responseNewsGallaryDiv").html('');
	    $("#responseHeading").html('');
        $("#responseMainDiv").css("display","none");
	  }

   }
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

function showNewAnotherSource(fileSourceLanguageId,type)
{
     var str1 ='';
	   str1 += '<center><table><tr><td><b>Same News in another source</b></td></tr></table></center>';
	    str1 += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
  for(var m in selectedContentFile.fileVOList)
  {
    if(selectedContentFile.fileVOList[m].fileSourceLanguageId == fileSourceLanguageId)
	{
	  if(document.getElementById("sourceChangeSpan") != null)
	    document.getElementById("sourceChangeSpan").innerHTML = ''+selectedContentFile.fileVOList[m].source+'';

	  if(document.getElementById("newsChangeEdition") != null && selectedContentFile.fileVOList[m].newsEdition != null)
		document.getElementById("newsChangeEdition").innerHTML = ''+selectedContentFile.fileVOList[m].newsEdition+'';

	 if(document.getElementById("newsPageNOSpan") != null && selectedContentFile.fileVOList[m].pageNo != null)
		document.getElementById("newsPageNOSpan").innerHTML = ''+selectedContentFile.fileVOList[m].pageNo+'';

	  var str ='';
	  if(selectedContentFile.fileVOList[m].fileVOList != null && selectedContentFile.fileVOList[m].fileVOList.length > 0){
	    if(selectedContentFile.fileVOList[m].fileVOList[0].path != null){
		 str+='<div class="" id="imgDiv" style="text-align:center;"><img   style="max-width:600px;max-length:800px;" src="'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" ></img></div>';
		 str +='<div id="zoomImageDiv" class="popupcontainer" style="display:none;"><img  style="width:950px;height:850px;" src="'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" /></div>';
	     $("#imgDiv").show();
	    }else{
	    	$("#imgDiv").hide();
	    }
	  }else{
	    str+='<div class="" id="imgDiv" style="text-align:center;"></div>';
		 str +='<div id="zoomImageDiv" class="popupcontainer" style="display:none;"></div>';
	     $("#imgDiv").hide();
	  }
	  
	  var stra1='';
	 var detaildesc =  selectedContentFile.fileVOList[m].description;
		var detaildescsource = selectedContentFile.fileVOList[m].eenadu;
		if(detaildesc != null && detaildesc !=''){
		   if(detaildescsource)
		     stra1 +='<div><b>Detail Description :</b><span class="enadu">'+detaildesc+'</span></div>';
		   else
		     stra1 +='<div><b>Detail Description :</b>'+detaildesc+'</div>';
		}else{

		}
		$("#detaildescoffile").html(stra1);
	  document.getElementById("imgDiv").innerHTML = str;
	
	   str = '<center><table><tr>';

	    for(var j=1;j<selectedContentFile.fileVOList[m].fileVOList.length;j++)
	     {
		    if(selectedContentFile.fileVOList[m].fileVOList[j].path != null)
	         str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[m].fileSourceLanguageId+','+selectedContentFile.fileVOList[m].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[m].fileVOList[j].path+'\',\'other\')"><img  width="65" height="60"  title="'+selectedContentFile.description+'"  src="'+selectedContentFile.fileVOList[m].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[m].fileVOList[j].orderName+'</a></td>';
			
	     }
		 
	   str += '  </tr></table>';
	   str +='</center>';
	  document.getElementById("buildNewSourceParts").innerHTML = str;
	}
    else
	{
	   
	    str1 += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[m].fileSourceLanguageId+',\'other\')">'+selectedContentFile.fileVOList[m].source+'</a></td>';	             	          	
	   
	}
  }
     str1 += '  </tr></table>';
	    str1 +='</center>';
     if(document.getElementById("buildNewSources") != null)
       document.getElementById("buildNewSources").innerHTML = str1;
	 else
	   document.getElementById("buildVideoNewSources").innerHTML = str1;
}

function showNextNewsPart(fileSourceLanguageId,orderNo,path,type)
{
  for(var i in selectedContentFile.fileVOList)
  {
    if(selectedContentFile.fileVOList[i].fileSourceLanguageId == fileSourceLanguageId)
	{
	  
	    var str='<div class="" id="imgDiv" style="text-align:center;"><img   style="max-width:600px;max-length:800px;" src="'+path+'"></img></div>';
		str +='<div id="zoomImageDiv" class="popupcontainer" style="display:none;"><img   style="width:950px;height:850px;" src="'+path+'" /></div>';
	  document.getElementById("imgDiv").innerHTML = str;
	
	   str = '<center><table><tr>';

	    for(var j=0;j<selectedContentFile.fileVOList[i].fileVOList.length;j++)
	     {
		   if(selectedContentFile.fileVOList[i].fileVOList[j].orderNo != orderNo)
		    {
			   if(selectedContentFile.fileVOList[i].fileVOList[j].path != null) 
	             str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[i].fileSourceLanguageId+','+selectedContentFile.fileVOList[i].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[i].fileVOList[j].path+'\',\'other\')"><img width="65" height="60"   src="'+selectedContentFile.fileVOList[i].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[i].fileVOList[j].orderName+'</a></td>';
	         
		    }
		 }
		 
	   str += '  </tr></table>';
	   str +='</center>';
	  document.getElementById("buildNewSourceParts").innerHTML = str;
	}
  
  }

}
function getNewsForPagination(startIndex)
{
	
var jObj=
	{
	  fileGallaryId:contentId,
	  firstResult:startIndex,
	  maxResult:3,
	  task:"getResponseGallaryDetails"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getResponseGallaryDetailsAction.action?"+rparam;
	callAjax(jObj,url);
}
function showResponseGallaryDetails(result,jsObj)
{
	$("#responseNewsGallaryDiv").html('');
	$("#responseHeading").html('');
	if(result == null || result.resGallTotRecordsCount == 0)
	{
	  $("#responseMainDiv").css("display","none");
	  return;
	}
	
   $("#responseMainDiv").css("display","block");
   $("#responseHeading").html('Responses');
   var results = result.responseGallaryList;
   var str="";
	str+="<ul class='unstyled pad10'>";
	for(var i in results){
		var source = results[i].fileVOList[0].source;
		str+="<li>";
		if(source == "Eenadu Telugu")
		 str+="<h3><a href='javascript:{}' class='enadu'>"+results[i].title+"</a></h3>";
		else
		 str+="<h3><a href='javascript:{}'>"+results[i].title+"</a></h3>";
		str+="<div class='row-fluid'>";
		str+="<a class='thumbnail span4' style='width: 146px;' href='javascript:{}'>";
		
		var path = results[i].fileVOList[0].fileVOList[0].path;
		

		str+="<img id='myImg' style='width:100%' src="+path+" onerror='imgError(this)'></a>";
		if(source == "Eenadu Telugu")
		str+="<p class='span8 enadu'>"+results[i].description+"</p>";
		else
		 	str+="<p class='span8'>"+results[i].description+"</p>";

		str+="</div>";

		str+="<div class='row-fluid m_top10'><div class='span9'>";
		str +='<table><tr><td>';
		str +='<p style="margin-right: 8px; width: 200px;"><span class="text-error">Source :</span>';
		var length = results[i].fileVOList.length;

		for(var j in results[i].fileVOList)
		{
		  str +=''+results[i].fileVOList[j].source+'';
		  if(length-1 != j)
			str +=',';
		}
		str +='</p></td><td style="vertical-align: top;"><p style="width: 130px;"><span class="text-error">Date :</span> '+results[i].fileDate+'</p></td><td style="vertical-align: top;"><p style="width: 301px;"><span class="text-error">candidate Name :</span> '+results[i].candidateName+'</td><td style="vertical-align: top;"><p style="width: 132px; margin-left: 13px;"><span class="text-error"><img alt="response count" title="Response Count" src="images/responseCountIcon.png" id="responseNewsCountImg" /></span> '+results[i].count+'</p></td></tr>';
		
		str +='</table>';
		str +='</div>';
		
		str+="<div class='span2'><a onclick='getNewsDetailsByContentId("+results[i].contentId+")' class='btn btn-mini btn-info pull-right' type='button'>More...</a></div></li>";
		var len = results.length;
		
		if(len-1 != i)
		 str +='<hr>';
	}
	
	var itemsCount=result.resGallTotRecordsCount;
	
	var maxResults=jsObj.maxResult;
	str+="</ul>";
   
	$("#responseNewsGallaryDiv").html(str);
	
	if(jsObj.firstResult==0){
		$("#paginationId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme'
		});
	}
}
function imgError(image) {
    image.onerror = "";
    image.src = "images/TDP.PNG";
    return true;
}
function getNewsDetailsByContentId(contentId)
{
  var urlstr = "newsDetailsPopupAction.action?contentId="+contentId+"&";
	
    var browser1 = window.open(urlstr,"gallaryDetails"+contentId+"","scrollbars=yes,height=600,width=1050,left=200,top=200");	
    browser1.focus();
}


function getMainArticles()
{
 var jObj=
	{
	  fileGallaryId:contentId,
	  task:"getMainArticleDetails"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getMainArticleDetailsAction.action?"+rparam;
	callAjax(jObj,url);
}

function showMainArticles(result,jsObj)
{
	
  if(result.count == 0)
  {
    $("#releatedNewsDiv").css("height","400");
	$("#gallaryMainDiv").css("display","none");
	return;
  }
  $("#gallaryMainDiv").css("display","block");
  $("#gallaryDiv").html('');
 
  var str = '';

  str +="<ul class='unstyled relatedproblem' style='width:220px;'>";
  for(var i in result.filesList)
  {
	  var source = result.filesList[i].fileVOList[0].source;
	  if(source == "Eenadu Telugu")
       str += '<li><a class="enadu" href="javascript:{}"  onClick="getNewsDetailsByContentId('+result.filesList[i].contentId+')">'+result.filesList[i].title+'</a></li>';
	  else
       str += '<li><a href="javascript:{}"  onClick="getNewsDetailsByContentId('+result.filesList[i].contentId+')">'+result.filesList[i].title+'</a></li>';
  }

  	str +='</ul>';
	 $("#gallaryDiv").addClass("gallaryDiv").html(str);
	var itemsCount=result.count;
	
	var maxResults=jsObj.maxResult;
	 if(jsObj.firstResult==0){
		$("#paginationDiv").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme'
		});
	}
  
}
function getZoomImage(){
$("#zoomImageDiv").dialog({
				resizable:true,
				title:'News Image',
				height:600,
				width:800,
				modal: true				
	});
}

function buildMainArticals(selectedContentId)
{
  
  $("#gallaryMainDiv").css("display","block");
  $("#gallaryDiv").html('');
  var result = showContentResultList.relatedGalleries[0].filesList;
  var str = '';

  for(var i=0;i<result.length;i++)
  {
	if(result[i].contentId == selectedContentId)
	{
      var mainArticalList = result[i].mainArticalsList;
	  str +="<ul class='unstyled relatedproblem' style='width:220px;'>";
	  for(var j=0;j<mainArticalList.length;j++)
	  {
        var source = mainArticalList[j].fileVOList[0].source;

	    if(source == "Eenadu Telugu")
         str += '<li><a class="enadu" href="javascript:{}"  onClick="getNewsDetailsByContentId('+mainArticalList[j].contentId+')">'+mainArticalList[j].title+'</a></li>';
	    else
         str += '<li><a href="javascript:{}"  onClick="getNewsDetailsByContentId('+mainArticalList[j].contentId+')">'+mainArticalList[j].title+'</a></li>';
	  }
	  str +='</ul>';
	}
			
  }
   $("#gallaryDiv").html(str);
}

function buildResponseNews(selectedContentId)
{
  $("#responseNewsGallaryDiv").html('');
	$("#responseHeading").html('');
	$("#responseMainDiv").css("display","block");
   $("#responseHeading").html('Responses');
   //debugger;
   var str = '';
   str+="<ul class='unstyled pad10' id='responseNewsUL'>";
   str +='<table id="responseTab">';
   str+='<thead>';
   str +='<tr>';
   str +='<th></th>';
   str +='</tr>';
   str+='</thead>';
   
   str+='<tbody>';
   str +='<tr>';
   str +='<td>';
   var result = showContentResultList.relatedGalleries[0].filesList;
   
   for(var j=0;j<result.length;j++)
   {
	if(result[j].contentId == selectedContentId)
	{
      var results = result[j].responseGallariesList;
	  
	  for(var i=0;i<results.length;i++)
	  {
        var source = results[i].fileVOList[0].source;
		str+="<li>";
		if(source == "Eenadu Telugu")
		 str+="<h3><a onclick='getNewsDetailsByContentId("+results[i].contentId+")' style='cursor: pointer;' class='enadu'>"+results[i].title+"</a></h3>";
		else
		 str+="<h3><a onclick='getNewsDetailsByContentId("+results[i].contentId+")' style='cursor: pointer;'>"+results[i].title+"</a></h3>";
		str+="<div class='row-fluid'>";
		str+="<a class='thumbnail span4' style='width: 146px;' href='javascript:{}'>";
		
		var path = results[i].fileVOList[0].fileVOList[0].path;
		

		str+="<img id='myImg' style='width:100%' src="+path+" onerror='imgError(this)'></a>";
		if(source == "Eenadu Telugu")
		str+="<p class='span8 enadu'>"+results[i].description+"</p>";
		else
		 	str+="<p class='span8'>"+results[i].description+"</p>";

		str+="</div>";

		str+="<div class='row-fluid m_top10'><div class='span9'>";
		str +='<table><tr><td>';
		str +='<p style="margin-right: 8px; width: 200px;"><span class="text-error">Source :</span>';
		var length = results[i].fileVOList.length;

		for(var k in results[i].fileVOList)
		{
		  str +=''+results[i].fileVOList[k].source+'';
		  if(length-1 != k)
			str +=',';
		}
		str +='</p></td><td style="vertical-align: top;"><p style="width: 130px;"><span class="text-error">Date :</span> '+results[i].fileDate+'</p></td><td style="vertical-align: top;"><p style="width: 301px;"><span class="text-error">candidate Name :</span> '+results[i].candidateName+'</td><td style="vertical-align: top;"><p style="width: 132px; margin-left: 13px;"><span class="text-error"><img alt="response count" title="Response Count" src="images/responseCountIcon.png" id="responseNewsCountImg" /></span> '+results[i].count+'</p></td></tr>';
		
		str +='</table>';
		str +='</div>';
		
		str+="<div class='span2'><a onclick='getNewsDetailsByContentId("+results[i].contentId+")' class='btn btn-mini btn-info pull-right' type='button'>More...</a></div></li>";
		var len = results.length;
		
		if(len-1 != i)
		 str +='<hr>';
	  }
	  
	}
			
  }
  str +='</td>';
  str +='</tr>';
  str+='</tbody>';
  str +='</table>';
  str +='</ul>';
  //$('#responseTab').dataTable();
  $('#responseTab').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 2,
		"aLengthMenu": [[2, 30, 90, -1], [2, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null
		] 
		});

  $("#responseNewsGallaryDiv").html(str);

}

getContentDetails();
//getNewsForPagination(0);
//getMainArticles();

</script>
</body>
</html>