<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Telugudhesham Party</title>

<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>


<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
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
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	 
	<!-- YUI Skin Sam -->
<!-- YUI Dependency files (End) -->

<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
<!-- <link rel="stylesheet" type="text/css" href="styles/candidatePage/candidatePage.css"> -->
<style type="text/css">
 #showContentHeaderDiv{background-color: #06ABEA;
    color: #FFFFFF;
    float: none;
    font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    padding-left: 13px;
    text-align: left;
    text-transform: uppercase;border-radius:5px;}
	
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

}
.newsParts{
  
  color:#FF4500;
}
#releatedNewsDiv{ height: 400px;
    overflow-y: scroll;}
</style>
</head>
<body>
<div id="newDisplayMainDiv">
 <div id="newDisplayInnerDiv">
   <div class="span12" style="margin-top:25px;">
    <div class="row">
	 <!-- left Div Start -->
	  <div class="span8 right-panel m5-left" style="background:#ffffff;position:relative;">
	  <div id="newsDetailsDiv"></div>
		<!-- <div id="showContentHeaderDiv"></div>
		<div id="newstitleSourceDiv"></div>
	    <div style="text-align:center;" id="newsImage" class="popupcontainer"></div>
		 <div id="descriptionDiv"><table><tr><td>Description : <span id="desc"></span></td></tr></table></div>
		  <div id="newsDisplayDiv"></div>-->

		 <div>
		<div style="display:none;" class="errorClass" id="errormsgdiv"></div>
		<h3>Comments:</h3>
		 <div class="commentSection">
		 <textarea style="width:100%;" id="commenttext" class="textareaid"></textarea></div><a style="margin-top:4px;margin-bottom:4px;" class="pull-right btn btn-info"  href="javascript:{}">Post</a>
		 <div style="padding-top: 0px; margin-left: 290px; margin-top: 36px; margin-bottom: -29px;" id="abusedErrorDiv"></div>
         <div style="display:none;" id="postedcomments"></div>	
		</div>

	</div>
	<!-- left Div End -->

	<!-- right Div End -->
	<div class="span3 left-panel" style=" width: 250px;">
	<h3>Other News in this Gallery</h3>
	   <div id="releatedNewsDiv"></div>
	</div>
	<!-- right Div End -->
  </div>
 </div>
</div>
</div>	
 

<script type="text/javascript">
var contentId = "${contentId}";
var showContentResultList = null;
var selectedContentFile;

 function getContentDetails()
 {
	//document.getElementById("contentAjaxCallImg").style.display="block";
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

   for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
	if(result.relatedGalleries[0].filesList[i].isSelectedContent)
	{
	    selectedContentFile = result.relatedGalleries[0].filesList[i];
		titleStr = result.relatedGalleries[0].filesList[i].title;
		pathStr = result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path;
		descriptionStr = result.relatedGalleries[0].filesList[i].description;
		preContentId = result.relatedGalleries[0].filesList[i].contentId;
		curPos = i+1;
		str +='<div id="showContentHeaderDiv">'+titleStr+'</div>';
		
		str+='<table class="tableCls">';
			str+='<tr>';
			str+='<td>';
			if(result.relatedGalleries[0].filesList[i].fileVOList[0].source != null)
				str+='<B>Source</B> : <font color="#FF4500"><span id="sourceChangeSpan">'+result.relatedGalleries[0].filesList[i].fileVOList[0].source+'</span></font> &nbsp;&nbsp;&nbsp;<B>';

			if(result.relatedGalleries[0].filesList[i].fileDate != null)
				str+=' Date </B>:<font color="#FF4500"> '+result.relatedGalleries[0].filesList[i].fileDate+'</font>';

			 str+='</td>';
			 str+='</tr>';
			 str+='</table>';
		str +='<div id="imgDiv" class="popupcontainer"><img alt="'+titleStr+'" title="'+descriptionStr+'" style="max-width:600px;max-length:800px;" src="'+pathStr+'" /></div>';
		str +='<div><span>Description: </span><b>'+descriptionStr+'<b></div>';
		
	}

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
		$("#newsDetailsDiv").html(str);


	var releatedGallary = '';
	releatedGallary +="<ul class='unstyled relatedproblem' style='width:220px;'>";

    for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
	if(!result.relatedGalleries[0].filesList[i].isSelectedContent)
	{
      releatedGallary += '<li><a href="javascript:{}"  onClick="buildContentDetailsOfSelected('+preContentId+','+result.relatedGalleries[0].filesList[i].contentId+')">'+result.relatedGalleries[0].filesList[i].title+'</a></li>';
	  
	}
	releatedGallary +='</ul>';
	 $("#releatedNewsDiv").addClass("releatedNewsDiv").html(releatedGallary);
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
	   str1 += '<center><table><tr><td><b>Same News in another sources</b></td></tr></table></center>';
	    str1 += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
  for(var m in selectedContentFile.fileVOList)
  {
    if(selectedContentFile.fileVOList[m].fileSourceLanguageId == fileSourceLanguageId)
	{
	  if(document.getElementById("sourceChangeSpan") != null)
	    document.getElementById("sourceChangeSpan").innerHTML = ''+selectedContentFile.fileVOList[m].source+'';
	  
	    var str='<div class="" id="imgDiv" style="text-align:center;"><img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" style="max-width:600px;max-length:800px;" src="'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" ></img></div>';
	  
	  document.getElementById("imgDiv").innerHTML = str;
	
	   str = '<center><table><tr>';

	    for(var j=1;j<selectedContentFile.fileVOList[m].fileVOList.length;j++)
	     {
		    
	         str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[m].fileSourceLanguageId+','+selectedContentFile.fileVOList[m].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[m].fileVOList[j].path+'\',\'other\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="'+selectedContentFile.fileVOList[m].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[m].fileVOList[j].orderName+'</a></td>';
			
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
	  
	    var str='<div class="" id="imgDiv" style="text-align:center;"><img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" style="max-width:600px;max-length:800px;" src="'+path+'"></img></div>';
	  
	  document.getElementById("imgDiv").innerHTML = str;
	
	   str = '<center><table><tr>';

	    for(var j=0;j<selectedContentFile.fileVOList[i].fileVOList.length;j++)
	     {
		   if(selectedContentFile.fileVOList[i].fileVOList[j].orderNo != orderNo)
		    {
			  
	             str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[i].fileSourceLanguageId+','+selectedContentFile.fileVOList[i].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[i].fileVOList[j].path+'\',\'other\')"><img width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" src="'+selectedContentFile.fileVOList[i].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[i].fileVOList[j].orderName+'</a></td>';
	         
		    }
		 }
		 
	   str += '  </tr></table>';
	   str +='</center>';
	  document.getElementById("buildNewSourceParts").innerHTML = str;
	}
  
  }

}

getContentDetails();
</script>
</body>
</html>