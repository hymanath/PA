<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <title> TDP News Portal </title>
   <script src="http://code.jquery.com/jquery-1.9.1.js"></script>

   <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

 
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
.ui-tooltip, .arrow:after {
    background: black;
    border: 2px solid white;
  }
  .ui-tooltip {
    padding: 10px 20px;
    color: white;
    border-radius: 20px;
    font: bold 14px "Helvetica Neue", Sans-Serif;
    text-transform: uppercase;
    box-shadow: 0 0 7px black;
	width:200px;
  }
  .arrow {
    width: 70px;
    height: 16px;
    overflow: hidden;
    position: absolute;
    left: 50%;
    margin-left: -35px;
    bottom: -16px;
  }
  .arrow.top {
    top: -16px;
    bottom: auto;
  }
  .arrow.left {
    left: 20%;
  }
  .arrow:after {
    content: "";
    position: absolute;
    left: 20px;
    top: -20px;
    width: 25px;
    height: 25px;
    box-shadow: 6px 5px 9px -9px black;
    -webkit-transform: rotate(45deg);
    -moz-transform: rotate(45deg);
    -ms-transform: rotate(45deg);
    -o-transform: rotate(45deg);
    tranform: rotate(45deg);
  }
  .arrow.top:after {
    bottom: -20px;
    top: auto;
  }
</style>
<style>

label {
    display: inline-block;
    width: 5em;
  }
  too
@font-face
{
font-family:eFont;src: url('img/eenadu.ttf');
 }
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
.newssources{
 background-color:#97DFEB;
 padding:8px 8px 8px 8px;
 margin-left:5px;
 border-radius: 5px 5px 5px 5px;

}
</style>

<script>

 $(document).ready(function(){

	 //$( ".selector" ).tooltip({ content: "Awesome title!" });
	   $( document ).tooltip({
		//tooltipClass: "enadu" ,
      position: {
       // my: "center bottom-20",
        at: "center top",
        using: function( position, feedback ) {
          $( this ).css( position );
          $( "<div>" )
            .addClass( "arrow" )
            .addClass( feedback.vertical )
            .addClass( feedback.horizontal )
            .appendTo( this );
        }
      }
    });

	 $(document).on("click",".nextLink",function() {
		 var divNo = parseInt($(this).attr('data-nextdiv'))+1;
         $('html, body').animate({
            scrollTop: $("#newsDisplayDiv"+divNo).offset().top
         }, 1000);
     });

	 $(document).on("click",".previousLink",function() {
		 var divNo = parseInt($(this).attr('data-nextdiv'))-1;
         $('html, body').animate({
            scrollTop: $("#newsDisplayDiv"+divNo).offset().top
         }, 1000);
     });

 });

</script>

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
var totalRecords = 0;
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
				totalRecords = myResults.length;
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

   str+='<div>';
   for(var i in results)
   {  

	 // alert(results[i].eenadu == true);  
	   str+='<div class="alert" id="newsDisplayDiv'+i+'" style="border:2px solid #5e5e5e;padding:4px;margin:3px 0px 0px 76px;;width:807px;background-color:#ffffff;">';
	   if(results[i].latest == true )
			str+='<h6 style="color:red;text-align:center;">THIS IS LATEST RESPONSE</h6>';

       if(results[i].eenadu == true )
	       str+='<div id="titleDiv" style="color:#5e5e5e;font-weight:bold;text-align:center;"><span  class="enadu">'+results[i].fileTitle1+'</span></div>';
	   else
		   str+='<div id="titleDiv" style="color:#5e5e5e;font-weight:bold;text-align:center;"><span  href="javascript:{}">'+results[i].fileTitle1+'</span></div>';


       if(results[i].fileDescription1 != null && results[i].fileDescription1 != "")
       if(results[i].eenadu == true )
	      str+='<div id="descriptionDiv" class="alert alert-info" style="color:#000000;background-color:#ffffff;"><span class="enadu">'+results[i].fileDescription1+'</span></div>';
	    else
		  str+='<div id="descriptionDiv" class="alert alert-info" style="color:#000000;background-color:#ffffff;">'+results[i].fileDescription1+'</div>';

        if(results[i].newsDescription != null && results[i].newsDescription != "")  
		if(results[i].eenadu == true )			
	       str+='<div id="" class="breadcrumb" style="color:#000000;"><span class="enadu">'+results[i].newsDescription+'</span></div>';
		else
			str+='<div id="" class="breadcrumb" style="color:#000000;"><span>'+results[i].newsDescription+'</span></div>';


		 str+='<div id="imageDiv'+i+'" style="text-align:center;">';

         str+='<div id="mainDiv'+i+'" style="text-align:center;">';
		str+='<div style="height:24px;"><span class="label label-success" style="float:left;">SOURCE:'+ results[i].fileVOList[0].source+'</span>';
		str+='<span class="label label-success" style="margin-right:138px;">EDITION:'+ results[i].fileVOList[0].newsEdition+'</span>';
		str+='<span class="label label-success">PAGE NO:'+ results[i].fileVOList[0].pageNo+'</span>';
		str+='<span style="float:right;" class="label label-success">File Date:'+results[i].fileDate+'</span></div>';

        if(i != results.length-1 )
		str+='<div style="float: right; margin-top: 60px;"><a  class="nextLink" data-nextdiv="'+i+'" href="javascript:void(0)" title="Click here to go to next news"><img src="images/arrow-down_blue.png"></img></a></div>';

        if(i != 0)
        str+='<div style="float: left; margin-top: 60px;"><a  class="previousLink" data-nextdiv="'+i+'" href="javascript:void(0)" title="Click here to go to previous news"><img src="images/arrow-up_blue.png" "></img></a></div>';
		

		str+='<img  src="'+results[i].fileVOList[0].fileVOList[0].path+'" style="" alt="news image not available"></img>';
		str+='</div>';

		if(results[i].fileVOList[0].fileVOList.length > 1)
	    {
			

			str+='<div style="margin-left:100px;margin-top:39px;width:700px;height:143px;" id="otherNews'+i+'">';

			for(var k=1;k<results[i].fileVOList[0].fileVOList.length ;k++){

				str+='<div style="float:left;margin:7px;"><img style="height:100px;width:100px;" src="'+results[i].fileVOList[0].fileVOList[k].path+'" alt="news image not available"></img><br>';

			str+='<span style="margin-left:22px;"><a href="javascript:{showNextnews('+results[i].fileVOList[0].fileSourceLanguageId+','+results[i].fileVOList[0].fileVOList[k].orderNo+',\''+results[i].fileVOList[0].fileVOList[k].path+'\','+i+')};">'+results[i].fileVOList[0].fileVOList[k].orderName+'</a></span></div>';
			}

			str+='</div>';

		}
     str+='</div>';

        if(results[i].fileVOList.length > 1)
	    {
			str+='<div style="text-align:center;margin:6px;background-color:#5e5e5e;color:#ffffff;" id="otherSourcesDiv" class="breadcrumb">';
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
var finalIndex = results.length-1;
    $('html, body').animate({
            scrollTop: $("#newsDisplayDiv"+finalIndex).offset().top
    }, 2000);
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

		  str+='<div style="height:24px;"><span class="label label-success" style="float:left;">SOURCE:'+ showContentResultList[i].fileVOList[j].source+'</span>';

          str+='<span class="label label-success" style="margin-right:138px;">EDITION:'+ showContentResultList[i].fileVOList[0].newsEdition+'</span>';

		  str+='<span class="label label-success">PAGE NO:'+ showContentResultList[i].fileVOList[0].pageNo+'</span>';

		str+='<span style="float:right;" class="label label-success">File Date:'+showContentResultList[i].fileDate+'</span></div>';

		 if(i != totalRecords -1)
		str+='<div style="float: right; margin-top: 60px;"><a  class="nextLink" data-nextdiv="'+i+'" href="javascript:void(0)" title="Click here to go to next news"><img src="images/arrow-down_blue.png"></img></a></div>';

        if(i != 0)
        str+='<div style="float: left; margin-top: 60px;"><a  class="previousLink" data-nextdiv="'+i+'" href="javascript:void(0)" title="Click here to go to previous news"><img src="images/arrow-up_blue.png"></img></a></div>';

		 // str+='<span class="label" style="float:left;">SOURCE:'+ showContentResultList[i].fileVOList[j].source+'</span>'


		str+='<img src="'+showContentResultList[i].fileVOList[j].fileVOList[0].path+'" style="" alt="news image not available"></img>';
		str+='</div>';

		str+='</div>';

		if(showContentResultList[i].fileVOList[j].fileVOList.length > 1)
	    {
			str+='<div style="margin-left:100px;margin-top:39px;width:700px;height:143px;" id="otherNews'+i+'">';


			for(var k=1;k<showContentResultList[i].fileVOList[j].fileVOList.length ;k++){

				str+='<div style="float:left;margin:7px;"><img style="height:100px;width:100px;" src="'+showContentResultList[i].fileVOList[j].fileVOList[k].path+'" alt="news image not available"></img><br>';

			str+='<span style="margin-left:22px;"><a   href="javascript:{showNextnews('+showContentResultList[i].fileVOList[j].fileSourceLanguageId+','+showContentResultList[i].fileVOList[j].fileVOList[k].orderNo+',\''+showContentResultList[i].fileVOList[j].fileVOList[k].path+'\','+i+');}" >'+showContentResultList[i].fileVOList[j].fileVOList[k].orderName+'</a></span></div>';
			}
			  str+='</div>';

		}
     str+='</div>';

	 $('#imageDiv'+i).html(str);

             
		  }else{


			str1+='<span><a  class="btn btn-success" href="javascript:{showAnotherSource('+showContentResultList[i].fileVOList[j].sourceId+','+i+')}"  >'+showContentResultList[i].fileVOList[j].source+'</a></span>';
		  }

	}

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

        //str+='<div id="mainDiv'+i+'" style="text-align:center;">';

		 // str+='<span class="label" style="float:left;">SOURCE:'+ showContentResultList[i].fileVOList[j].source+'</span>'

		  str+='<div style="height:24px;"><span class="label label-success" style="float:left;">SOURCE:'+ showContentResultList[i].fileVOList[j].source+'</span>';

         str+='<span class="label label-success" style="margin-right:138px;">EDITION:'+ showContentResultList[i].fileVOList[0].newsEdition+'</span>';

		 str+='<span class="label label-success">PAGE NO:'+ showContentResultList[i].fileVOList[0].pageNo+'</span>';


		str+='<span style="float:right;" class="label label-success">File Date:'+showContentResultList[i].fileDate+'</span></div>';

      
	    if(i != totalRecords -1)
		str+='<div style="float: right; margin-top: 60px;"><a  class="nextLink" data-nextdiv="'+i+'" href="javascript:void(0)" title="Click here to go to next news"><img src="images/arrow-down_blue.png"></img></a></div>';

        if(i != 0)
        str+='<div style="float: left; margin-top:60px;"><a  class="previousLink" data-nextdiv="'+i+'" href="javascript:void(0)" title="Click here to go to previous news"><img src="images/arrow-up_blue.png"></img></a></div>';


		str+='<img src="'+path+'" style="" alt="news image not available"></img>';


		//str+='</div>';



				//str+='<span text-error"="" class="class=">SOURCE:</span>'+showContentResultList[i].fileVOList[j].source+'';
			//str+='<img src="'+path+'"></img>';

			$("#mainDiv"+i).html(str);

			str1+='<div id="otherNews'+i+'" style="margin-left:100px;margin-top:39px;">';



			for(var k = 0;k<showContentResultList[i].fileVOList[j].fileVOList.length;k++)
			{

			if(showContentResultList[i].fileVOList[j].fileVOList[k].orderNo != orderNo){/*

			str1+='<div>';

			str1+='<img src="'+showContentResultList[i].fileVOList[j].fileVOList[k].path+'" style="height:100px;width:100px;"></img>';
			str1+='</div>';


			str1+='<span style="margin-left:22px;"><a href="javascript:{showNextnews('+showContentResultList[i].fileVOList[j].fileSourceLanguageId+','+showContentResultList[i].fileVOList[j].fileVOList[k].orderNo+',\''+showContentResultList[i].fileVOList[j].fileVOList[k].path+'\','+i+');}">'+showContentResultList[i].fileVOList[j].fileVOList[k].orderName+'</a></span>';

			str1+='</div>';*/



            str1+='<div style="float:left;margin:7px;"><img style="height:100px;width:100px;" src="'+showContentResultList[i].fileVOList[j].fileVOList[k].path+'" alt="news image not available"></img><br>';

			str1+='<span style="margin-left:22px;"><a href="javascript:{showNextnews('+showContentResultList[i].fileVOList[j].fileSourceLanguageId+','+showContentResultList[i].fileVOList[j].fileVOList[k].orderNo+',\''+showContentResultList[i].fileVOList[j].fileVOList[k].path+'\','+i+');};">'+showContentResultList[i].fileVOList[j].fileVOList[k].orderName+'</a></span></div>';

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
 <div id="newsDetailsDiv" class="alert alert-success"></div>
 </body>

</html>
