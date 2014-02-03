<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> TDP News Portal </title>

<!-- YUI Dependency files (Start) -->
	
	
	
  
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/> 

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
 <script type="text/javascript" src="pagination/jquery.simplePagination.js"></script>
	<link rel="stylesheet" type="text/css" href="pagination/simplePagination.css">
	<script type="text/javascript" src="pagination/pagination1.js"></script>
   
<style>
@font-face   
{
font-family:eFont;src: url('img/eenadu.ttf');
 }
 .enadu
{
font-family: eFont;
font-size:20px;
}
@font-face{ font-family: 'eFont'; src: url('fonts/eenadu.eot');}
@font-face {
    font-family: "eFont";
    font-style: normal;
    font-weight: normal;
    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
}

#existingFromText,#existingToText{width:155px;cursor: text;}
#errorMsgDiv{font-size:12px;}
#responseNewsCountImg{height: 30px; width: 40px; margin-right: 3px;cursor: pointer;}
</style>

</head>
<body>

<!--<div class="span2">
<div id="mainDiv"></div>
<div class="row-fluid widget">
<div class="span12 boxHeading"><h4>Gallery News</h4></div>
</div>				
</div>-->
<!--<div id="gallarysId" style="margin:22px 0px 31px 102px;"></div>-->
<div style="" id="mainDiv">
<div class="span12">
<div class="row-fluid widget">
<div class="span12 boxHeading" align="center"><h4>News Updates </h4></div>
<div id="imageForMail"  class = "span3"  style="display:none;font-weight:bold;color: #0174DF;height:20px;width:500px;">
<font>Please wait...</font>
<img src="images/icons/goldAjaxLoad.gif" style="width: 150px; height: 15px;" width="18" height="11"/>
</div>
<div class="span12" id="newsDisplayDiv">						
</div>


<div id="paginationId"></div>

</div>
</div>
</div>
<script>
        var sourceCandId = '${sourceCandId}';
		var sourcePartyId = '${sourcePartyId}';
		var locationLvl = '${locationLvl}';
		var locationIds = '${locationIds}';
		var startDate = '${startDate}';
		var endDate = '${endDate}';
		var sourceType = '${sourceType}';
		var benifitsFor = '${benifitsFor}';
		var destiPartyId = '${destiPartyId}';
		var categoryId = '${categoryId}';
		var sourceId = '${sourceId}';
  getAllGallaries(0,10);
            
function getAllGallaries(startIndex,endIndex)
{
   $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	}); 

	$.ajax({
	  type:'POST',
	  url: 'getAllAnalysedNewsAction.action',
	  dataType: 'json',
	  data: {startValue:startIndex,
	         endValue:endIndex,
		    sourceCandId:sourceCandId,
			sourcePartyId:sourcePartyId,
			destiPartyId:destiPartyId,
			locationLvl:locationLvl,
			locationIds:locationIds,
			 benifitsFor:benifitsFor,
			startDate:startDate,
			endDate:endDate,
			sourceType : sourceType,
			categoryId:categoryId,
			sourceId:sourceId			
	  },
		 
	  success: function(results){ 
		   buildFilesInGallaryDetails(results,0,startIndex,endIndex);
	 },
	  error:function() { 
	  }
	});

}


function buildFilesInGallaryDetails(results,selectedvalue,index,endValue)
{   
	var totalPages;
	var requestedFor = "";
	$('#imageForMail').css("display","none");
	$("#newsDisplayDiv").html('');
  if(results == null || results.length == 0)
  {
    $("#newsDisplayDiv").html('No Data Found.');
	return;
  }
	

   var str='';

   str+='<div class="span12">';
   str+='<ul class="unstyled pad10">';
   for(var i in results)
   {
	str+='<li>';
	str+='<div class="">';
	/* var source = '';
	for (var t in results[i].fileTypesList)
	{
		 source = results[i].fileTypesList[t];
	} */
	//var source = results[i].fileType.trim();
	var fontId = results[i].fontId;
	if(fontId >  0)
	{
		str+='<h4 style="" class="enadu"><a style="color: #005580;" href="javascript:{getNewsDetailsByContentId('+results[i].fileId+')}">'+results[i].title+'</a></h4>';
	}
	else
	{
		str+='<h4 style="text-transform: capitalize;"> <a style="color: #005580;" href="javascript:{getNewsDetailsByContentId('+results[i].fileId+')}">'+results[i].title+'</a></h4>';
	}
		

		str+='<div class="row-fluid">';
		str+='<a style="width: 146px;" href="javascript:{getNewsDetailsByContentId('+results[i].fileId+')}" class="thumbnail span4">';
		if(results[i].fileName1 != null && results[i].fileName1 != "") 
			str+='<img style="width:100%" src="'+results[i].fileName1+'" >';
		else
		str+='<img style="width:100%"src="/TDP/images/TDP.PNG" >';
		str+='</a>';
		
		if(results[i].descEenadu)
		{
			str+='<p class="span8 enadu">'+results[i].description+'</p>';
		}
		else
		{
			str+='<p class="span8">'+results[i].description+'</p>';
		}
		str+='</div>';
		if(results[i].categoriesList != null)
		{
			
			str += '<div><p class="text-error" style="font-weight:bold;">Categorie(s) : <span style="font-weight:normal;color:black;">';
			var categ = "";
			var count = 0;
			for(var m in results[i].categoriesList)
			{
			count++;
			var length = results[i].categoriesList.length;
			if(results[i].categoriesList[m] == 1)
			{
				categ += ''+results[i].categoriesList[m]+'';
			}
			else 
			{
				if(count == length)
				{
					categ += ''+results[i].categoriesList[m]+'';
				}
				else
				{
					categ += ''+results[i].categoriesList[m]+', ';
				}
				
			}
			
			}
			str += ''+categ+'</span></p>';
			str += '</div>';
		
		}
			
		if(results[i].keywordsList != null)
		{
			str += '<div><p class="text-error" style="font-weight:bold;">Keyword(s) : <span style="font-weight:normal;color:black;">';
		
			var keyword = "";
			var count = 0;
			for(var n in results[i].keywordsList)
			{
				count++;
				var length = results[i].keywordsList.length;
				if(results[i].keywordsList[n] == 1)
				{
					keyword += ''+results[i].keywordsList[n]+'';
				}
				else 
				{
					if(count == length)
					{
						keyword += ''+results[i].keywordsList[n]+'';
					}
					else
					{
						keyword += ''+results[i].keywordsList[n]+', ';
					}
					
				}
				
			}
			str += ''+keyword+'</span></p>';
			str += '</div>';
		}
		
		if(results[i].candidateName!='' && results[i].candidateName!=undefined)
		{   
			str += '<div><p class="text-error" style="font-weight:bold;">Candidate(s) : <span style="font-weight:normal;color:black;">';
		
			var candidates = "";
			candidates += ''+results[i].candidateName +'';
			str += ''+candidates+'</span></p>';
			str += '</div>';
		}
		
		str+='<div class="row-fluid m_top10">';
		str+='<div class="span9" style="width:550px;">';
		str+='<table><tr><td style="width:260px;font-weight:bold;"><p class="text-error" >Source : <span style="font-weight:normal;color:black;">';
		
			str += ''+results[i].source+'';
		
		str += '</span></p></td><td style="font-weight:bold;"><p class="text-error" >Date : <span style="font-weight:normal;color:black;">'+results[i].fileDate+'</span></p></td>';
		/* if(results[i].responseCount > 0)
		str+='<td style="font-weight:bold;padding-left: 20px;"><p class="text-error" ><img alt="response count" title="Response Count" src="images/responseCountIcon.png" id="responseNewsCountImg" /><span style="font-weight:normal;color:black;">'+results[i].responseCount+'</span></p></td>'; */
		str+='</tr></table></div>';
		
		
		//str+='<br><div class="span2" style="float:right;">';
		
		//str+='	<a type="button" class="btn btn-mini btn-info pull-right" onClick="getNewsDetailsByContentId('+results[i].fileGallaryId+')">Details...</a>';
			
		//str+='</div>';
	str+='</div>'; 
	str+='</div>';
	str+='</li>';
   }
    str+='</ul>';
   str+='</div>';
$("#newsDisplayDiv").html(str);
var itemsCount=results[0].count;

var maxResults=endValue;
if(index==0){
	$("#paginationId").pagination({
		items: itemsCount,
		itemsOnPage: maxResults,
		cssStyle: 'light-theme'
	});
}
 $('html,body').animate({
        scrollTop: $("#mainDiv").offset().top},
        'slow');
}


function callAjaxToGetTheResults(selectedvalue)
{
	var startIndex = 0;
	var endIndex = 10; 
	var selectedvalue1 = 0;
	if(selectedvalue == "1")
		startIndex = 0;
	else{
		selectedvalue1 = selectedvalue - 1;
		startIndex = selectedvalue1*10;
	}
	getAllGallaries(startIndex,endIndex)
}

</script>

</body>