<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title> TDP News Portal </title>
 
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
<style>
.newssources{
 background-color:#97DFEB;
 padding:8px 8px 8px 8px;
 margin-left:5px;
 border-radius: 5px 5px 5px 5px;

}
</style>

<script>
getCompleteDetailsForANewsResponse();
function getCompleteDetailsForANewsResponse()
{
	var jObj=
	{
	  resonseContentId:'${responseContentId}',
	  task:"getCompleteDetailsOfANewsResponse"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getCompleteDetailsOfANewsResponse.action?"+rparam;
	callAjax(jObj,url);
}
var showContentResultList = null;
function callAjax(jsObj,url)
{
	
	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText);

		    if(jsObj.task == "getCompleteDetailsOfANewsResponse")
			{
				showContentResultList = myResults;
               buildNewsDetails(myResults);	
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

 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildNewsDetails(results)
{
	showContentResultList = results;

   var str='';

   str+='<div style="margin-left:50px;">';
   for(var i in results)
   { 
	   str+='<div style="border:2px solid #5e5e5e;padding:4px;margin:3px;">';
	   str+='<div id="titleDiv"><h5 style="text-align:center;" href="javascript:{}">'+results[i].fileTitle1+'</h5></div>';
	   str+='<div id="descriptionDiv" class="alert alert-success">'+results[i].fileDescription1+'</div>';
	   str+='<div id="descriptionDiv">'+results[i].newsDescription+'</div>';

        

		 str+='<div id="imageDiv'+i+'" style="text-align:center;">';

         str+='<div id="mainDiv'+i+'" style="text-align:center;">';
		str+='<span class="label" style="float:left;">SOURCE:'+ results[i].fileVOList[0].source+'</span>';

		str+='<img src="'+results[i].fileVOList[0].fileVOList[0].path+'" style="width:415px;height:244px;"></img>';
		str+='</div>';

		if(results[i].fileVOList[0].fileVOList.length > 1)
	    {
			

			str+='<div style="margin-left:100px;margin-top:39px;width:700px;height:143px;" id="otherNews'+i+'">';

			for(var k=1;k<results[i].fileVOList[0].fileVOList.length ;k++){

				str+='<div style="float:left;margin:7px;"><img style="height:100px;width:100px;" src="'+results[i].fileVOList[0].fileVOList[k].path+'"></img><br>';

			str+='<span style="margin-left:22px;"><a href="javascript:{showNextnews('+results[i].fileVOList[0].fileSourceLanguageId+','+results[i].fileVOList[0].fileVOList[k].orderNo+',\''+results[i].fileVOList[0].fileVOList[k].path+'\','+i+')};">'+results[i].fileVOList[0].fileVOList[k].orderName+'</a></span></div>';
			}

			str+='</div>';

		}
     str+='</div>';

        if(results[i].fileVOList.length > 0)
	    {
			str+='<div style="text-align:center;margin:6px;" id="otherSourcesDiv" class="breadcrumb">';
			str+='<h6>Same News In Other Sources</h6>';
             
            str+='<div id="otherSources'+i+'">';
			for(var l=1;l<results[i].fileVOList.length ;l++)
			{
				str+='<span><a href="javascript:{showAnotherSource('+results[i].fileVOList[l].sourceId+','+i+')}" class="btn btn-success" >'+results[i].fileVOList[l].source+'</a></span>';
			}
			str+='</div>';
			str+='</div>';

		}

	   str+='</div>';

   }
    str+='</div>';

   $('#newsDetailsDiv').html(str);
}

function showAnotherSource(sourceId , i)
{

       var str = '';
	   var str1 = '';
	   var index = 0;
	for(var j =0;j<showContentResultList[i].fileVOList.length;j++)
	{
          
		  if(showContentResultList[i].fileVOList[j].sourceId == sourceId ){


          str+='<div id="mainDiv'+i+'" style="text-align:center;">';

		  str+='<span class="label" style="float:left;">SOURCE:'+ showContentResultList[i].fileVOList[j].sourcee+'</span>'

		//str+='<span class="class="text-error">SOURCE:</span>'+ showContentResultList[i].fileVOList[j].source;

		str+='<img src="'+showContentResultList[i].fileVOList[j].fileVOList[0].path+'" style="width:415px;height:244px;"></img>';
		str+='</div>';

		//str+='<img src="'+showContentResultList[i].fileVOList[j].fileVOList[0].path+'"></img>';
		str+='</div>';

		if(showContentResultList[i].fileVOList[j].fileVOList.length > 0)
	    {
			str+='<div style="margin-left:100px;margin-top:39px;width:700px;height:143px;" id="otherNews'+i+'">';

			//str+='<div style="margin-left:100px;margin-top:39px;" id="otherNews'+i+'">';

			for(var k=1;k<showContentResultList[i].fileVOList[j].fileVOList.length ;k++){

				

				str+='<div style="float:left;margin:7px;"><img style="height:100px;width:100px;" src="'+showContentResultList[i].fileVOList[j].fileVOList[k].path+'"></img><br>';

			str+='<span style="margin-left:22px;"><a   href="javascript:{showNextnews('+showContentResultList[i].fileVOList[j].fileSourceLanguageId+','+showContentResultList[i].fileVOList[j].fileVOList[k].orderNo+',\''+showContentResultList[i].fileVOList[j].fileVOList[k].path+'\','+i+');}" >'+showContentResultList[i].fileVOList[j].fileVOList[k].orderName+'</a></span></div>';

				
/*
				str+='<div><img style="height:100px;width:100px;"src="'+showContentResultList[i].fileVOList[j].fileVOList[k].path+'"></img></div>';
			str+='<span style="margin-left:22px;"><a   href="javascript:{showNextnews('+showContentResultList[i].fileVOList[j].fileSourceLanguageId+','+showContentResultList[i].fileVOList[j].fileVOList[k].orderNo+',\''+showContentResultList[i].fileVOList[j].fileVOList[k].path+'\','+i+');}" >'+showContentResultList[i].fileVOList[j].fileVOList[k].orderName+'</a></span>';*/
			}
			  str+='</div>';

		}
     str+='</div>';

	 $('#imageDiv'+i).html(str);

             
		  }else{

			 // if(index == 0){
			//	str1+='<div id="otherSources'+i+'">';
			  // }
			  //index = 1;

			str+='<span><a href="javascript:{showAnotherSource('+showContentResultList[i].fileVOList[j].sourceId+','+i+')}" class="newssources" >'+showContentResultList[i].fileVOList[j].source+'</a></span>';
		  }

		 //alert(showContentResultList[i].fileVOList[j].source);
	}
	//if(str1 != "")
	//str1+="</div>";


	$('#otherSources'+i).html(str1);

}

function showNextnews(sourceId , orderNo  , path , i)
{
	var str='';
	var str1='';
	for(var j=0;j< showContentResultList[i].fileVOList.length;j++)
	{

		if(showContentResultList[i].fileVOList[j].fileSourceLanguageId == sourceId)
		{
				str+='<span text-error"="" class="class=">SOURCE:</span>'+showContentResultList[i].fileVOList[j].source+'';
			str+='<img src="'+path+'"></img>';

			$("#mainDiv"+i).html(str);

			str1+='<div id="otherNews'+i+'" style="margin-left:100px;margin-top:39px;">';



			for(var k = 0;k<showContentResultList[i].fileVOList[j].fileVOList.length;k++)
			{

			if(showContentResultList[i].fileVOList[j].fileVOList[k].orderNo != orderNo){

			str1+='<div>';

			str1+='<img src="'+showContentResultList[i].fileVOList[j].fileVOList[k].path+'" style="height:100px;width:100px;"></img>';
			str1+='</div>';


			str1+='<span style="margin-left:22px;"><a href="javascript:{showNextnews('+showContentResultList[i].fileVOList[j].fileSourceLanguageId+','+showContentResultList[i].fileVOList[j].fileVOList[k].orderNo+',\''+showContentResultList[i].fileVOList[j].fileVOList[k].path+'\','+i+');}">'+showContentResultList[i].fileVOList[j].fileVOList[k].orderName+'</a></span>';

			str1+='</div>';
				}
			}			

			$('#otherNews'+i).html(str1);
		}

	}


}

/*function showNextNewsPart(fileSourceLanguageId,orderNo,path,i)
{

	var str = '';
	var str1 = '';
  for(var j in showContentResultList[i].fileVOList)
  {
    if(showContentResultList[i].fileVOList[j].fileSourceLanguageId == fileSourceLanguageId)
	{
	  
	    var str='<div class="" id="imgDiv" style="text-align:center;"><img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" style="max-width:600px;max-length:800px;" src="'+path+'"></img></div>';

        str+='<div id="imageDiv'+i+'">';

		str+='<span class="class="text-error">SOURCE:</span>'+ showContentResultList[i].fileVOList[j].source;

		str+='<img src="'+path+'"></img>';


         $('#imageDiv'+i).html(str);	

		if(showContentResultList[i].fileVOList[j].fileVOList.length > 0)
	    {
			str1+='<div style="margin-left:100px;margin-top:39px;" id="otherNews'+i+'">';

			for(var k=0;k<showContentResultList[i].fileVOList[j].fileVOList.length ;k++){

				if(showContentResultList[i].fileVOList[j].orderNo !=  orderNo){
                

				str1+='<div><img style="height:100px;width:100px;"  href="javascript:{showNextnews('+showContentResultList[i].fileVOList[j].sourceId+','+showContentResultList[i].fileVOList[j].orderNo+','+showContentResultList[i].fileVOList[j].fileVOList[k].path+','+i+');}" src="'+showContentResultList[i].fileVOList[j].fileVOList[k].path+'"></img></div>';
			str1+='<span style="margin-left:22px;">'+showContentResultList[i].fileVOList[j].fileVOList[k].orderName+'</span>';
				}
			}

			str1+='</div>';

		}
     str+='</div>';
	 
	}  
  }
}
*/

function buildNewsDetails1(result)
{
   showContentResultList = result;
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
   for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
	if(result.relatedGalleries[0].filesList[i].isSelectedContent)
	{
	  

		source = result.relatedGalleries[0].filesList[i].fileVOList[0].source;
	    selectedContentFile = result.relatedGalleries[0].filesList[i];
		titleStr = result.relatedGalleries[0].filesList[i].title;
		pathStr = result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path;
		descriptionStr = result.relatedGalleries[0].filesList[i].description;
		preContentId = result.relatedGalleries[0].filesList[i].contentId;
		curPos = i+1;
		if(source == "Eenadu Telugu")
		{
			str +='<div id="showContentHeaderDiv"><span class="enadu">'+titleStr+'</span></div>';
		}
		else
		{
			str +='<div id="showContentHeaderDiv" style="text-transform: uppercase;">'+titleStr+'</div>';
		}
		
		
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
		str +='<div id="zoomImageDiv" class="popupcontainer" style="display:none;"><img alt="'+titleStr+'" title="'+descriptionStr+'" style="width:950px;height:850px;" src="'+pathStr+'" /></div>';
		if(source == "Eenadu Telugu")
		{
			str +='<div><span>Description: </span><b class="enadu">'+descriptionStr+'<b>';
		}
		else
		{
			str +='<div><span>Description: </span><b>'+descriptionStr+'<b>';
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
	var source = result.relatedGalleries[0].filesList[i].fileVOList[0].source;;
	if(source == "Eenadu Telugu")
	{
		releatedGallary += '<li><a href="javascript:{}"  onClick="buildContentDetailsOfSelected('+preContentId+','+result.relatedGalleries[0].filesList[i].contentId+')" class="enadu">'+result.relatedGalleries[0].filesList[i].title+'</a></li>';
	}
	else
	{
		releatedGallary += '<li><a href="javascript:{}"  onClick="buildContentDetailsOfSelected('+preContentId+','+result.relatedGalleries[0].filesList[i].contentId+')">'+result.relatedGalleries[0].filesList[i].title+'</a></li>';
	}
      
	  
	}
	releatedGallary +='</ul>';
	 $("#releatedNewsDiv").addClass("releatedNewsDiv").html(releatedGallary);
}


</script>
 </head>

 <body>
 <div id="newsDetailsDiv"></div>
 </body>

</html>
