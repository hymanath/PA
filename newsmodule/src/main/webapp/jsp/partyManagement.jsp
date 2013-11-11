<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> TDP News Portal </title>
<!--<script type="text/javascript" src="js/jquery.js"></script>-->
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
<SCRIPT type="text/javascript" src="js/AddNewProblem/addNewProblem.js"></SCRIPT>

   <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
    <script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
    <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js">
	</script> 
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
	 <SCRIPT type="text/javascript" src="js/specialPage/specialPage.js"></SCRIPT>
	<!-- YUI Skin Sam -->
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<script type="text/javascript" src="js/problemCompleteDetails.js"></script>
	
<!-- JQuery files (Start) -->

<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<script type="text/javascript" src="js/partyManagement.js"></script>

<!-- JQuery files (End) -->
<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

<!-- keywords -->
    <link rel="stylesheet" type="text/css" href="styles/autoSuggest.css"> 
	<script type="text/javascript" src="js/jquery.autoSuggest.js"></script>
	<script type="text/javascript" src="js/jquery.autoSuggest.minified.js"></script>
	<script type="text/javascript" src="js/jquery.autoSuggest.packed.js"></script>

<!-- keywords -->
<link  rel="stylesheet" type="text/css" href="styles/partyManagement/partyManagement.css"/>



<style type="text/css">


</style>
</head>
<script type="text/javascript">
var gGallaryId;
		var timeST = new Date().getTime();
		var sizeOfArray;

var sourceObj = null;
var languagesObj = null;
var fileCount=0;

function showPhotoGallary1()
{
if(!formValidation()){
document.getElementById("profileManagementMainOuterDiv2").style.display = 'none';

document.getElementById("profileManagementMainOuterDiv1").style.display = 'block';
document.getElementById("profileManagementMainOuterDiv3").style.display = 'none';
document.getElementById("profileManagementMainOuterDiv4").style.display = 'none';
$("#dateSelectDiv").css("display","none");
/*$("#photoGalleryId").css({"background":"none repeat scroll 0 0 #F61D50"});
$("#videoGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsGalleryId").css({"background":"none repeat scroll 0 0 #0063DC"});
$("#newsEditId").css({"background":"none repeat scroll 0 0 #0063DC"});*/

 buildCreateGallaryDiv();
}
return;
}


</script>

<body>
<br>
<!-- For Heading -->

<div id="editNewsOuter">
  <div id="editNewsInner"></div>
</div>

<div id='profileManagementMainOuterDiv'>

<div id='profileManagementHeaderDiv'>
	<!--<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tr>
		  <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg">
		    </td>
		    <td width="98%">
		       <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
		         <span style="text-decoration: none;" class="headerLabelSpan2"><center>Party Management Admin</center></span>
		       </div>
		    </td>
		   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
		   </td>
		</tr>
		<span class="span12" style="background:red">Party Management Admin</span>
	</table>-->
	
	
</div><br>
 <!-- For Heading end -->
 
<div id='profileManagementMainInnerDiv' class="divInfo">
	<div id="profileManagementDiv">
		<!--<table class="statusData_table" width="100%">	
          <tr>
			<td class="statusData_table_data" width="100%" style="padding-top:23px">
				<table>
				  <tr>
			    	<td style="padding-left:315px"><b><input type="button" class="buttonStyle" value="Photo Gallery" id="photoGalleryId" onClick="showPhotoGallary1()"></b></td>
				    <td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Video Gallery" id="videoGalleryId" onClick="showVideoGallaey1()"></b> </td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="News Gallery" id="newsGalleryId" onClick="showNewsGallaey()"></b> </td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Update News" id="newsEditId" onClick="showTheNewsToUpdate()"></b> </td>
					</tr>
				</table>
			</td>
		 </tr>
		</table>-->
		
	
	    <ul class="nav nav-tabs" style="margin-left:Auto;margin-right:Auto;width:940px;">
    <!--<li>
    <a value="Photo Gallery" id="photoGalleryId" onClick="showPhotoGallary1()" style="cursor:pointer">Photo Gallery</a>
    </li>-->
    <li> <a value="Video Gallery" id="videoGalleryId" onClick="showVideoGallaey1()" style="cursor:pointer;color: blue;">Video Gallery</a></li>
    <li class="active"><a value="News Gallery" id="newsGalleryId" onClick="showNewsGallaey()" style="cursor:pointer;color: blue;">News Gallery</a></li>
	<li><a value="Update News" id="newsEditId" onClick="showTheNewsToUpdate()" style="cursor:pointer;color: blue;">Update News</a></li>

	<li><a value="Assign News" id="assignNewsId" onClick="assignNewsToCandidate()" style="cursor:pointer;color: blue;">Assign News</a></li>
	<li><a value="create Report" id="createReportId" onClick="createReport()" style="cursor:pointer;color: blue;">Create Report </a></li>
	<li><a value="viewReport" id="viewReports" onclick=" getNewsReports();" style="cursor:pointer;color: blue;">View Report</a>
		
		</li>
		<li><a value="KeyWords" id="createReportId" onclick=" getUnassignedKeyWords();" style="cursor:pointer;color: blue;">KeyWords</a>
		
		</li>
    </ul>
	
</div>
</div>
</div>

<div id="dialog" title="Update Category">
<div>
	<div style="clear:both;"><span>Name</span>: <input type="text" id="categoryNameId"/></div>
	<div style="clear:both;"><span>Visibility</span>: 
				<input type="radio"  name="visibility" value="public" checked="checked" style="margin-top:0px;margin-left:4px;">Public
				<input type="radio"  name="visibility" value="private" style="margin-top:0px;margin-left:4px;">Private</div>
				<input id="idVal" type="hidden" value="" />
				
	<div id="errMsg" style="margin:5px;"></div>
	
	<span class="btn btn-mini pull-right btn-inverse" id="updateCategoryId">Update</span>
</div>
</div>
<!--<button id="opener">Open Dialog</button>-->


<!-- for  body 1 start    result  -->
<HR>
<div id='profileManagementMainOuterDiv1' style="display:none">
	<div id='profileManagementHeaderDiv1' class="row-fluid">
		<!--<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Photo Gallery</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>-->
		
		<div class="span10 offset1 text-center alert"><input type="button" class="btn btn-success highlight" value="Create Gallery" onclick="buildCreateGallaryDiv()">
		<input type="button" class="btn btn-success highlight" value="Upload photos" onclick="buildUploadPhotosDiv()"></div>

		
	</div>

	<div id='photoGallaryDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 1 end    result  -->

<!-- for  body 2 start    result  -->

<div id='profileManagementMainOuterDiv2' style="display:none">
	<div id='profileManagementHeaderDiv2' class="row-fluid">
		<!--<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Video Gallery</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>-->
		<div class="span10 offset1 text-center alert"><input type="button" class="btn btn-success highlight" value="Create Video Gallery" onclick="buildCreateVideoGallaryDiv()">
		<input type="button" class="btn btn-success highlight" value="Upload Video" onclick="buildUploadVideoDiv()">
		</div>
		
	</div>	

	<div id='videoGallaryDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 2 end    result  -->
<div id='profileManagementMainOuterDiv3' style="display:none">
	<div id='profileManagementHeaderDiv3' class="row-fluid">
		<!--<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">News</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>-->
	<div class="span11 offset1 text-center alert" style="margin-left:30px;">
	<input type="button" class="btn btn-success highlight" value="Create News Category" onclick="createNewsCategory()">
	<input type="button" class="btn btn-success highlight" value="Create News Gallery" onclick="buildCreateNewsCategory()">
	<input type="button" class="btn btn-success highlight" value="Upload News" onclick="buildUploadNews()">
	<input type="button" class="btn btn-success highlight" value="Upload News For Multiple Users" onclick="buildUploadNewsForMultipleUsers()">
	<c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
		<input type="button" class="btn btn-success highlight" value="Create New Source" onclick="createNewSource()">
	</c:if>
	<!--<a  class="btn btn-info" href="javascript:{}" onClick="createNewSource();">Create New Source</a>-->
	</div>
</div>
		<div id='newsGallaryDiv' class="divInfo">
	 </div>		
		<div id='newsAssignGallaryDiv' class="divInfo"> </div>		 
	</div>
</div>


<!-- for  body 4  result  start -->

<div id="dateSelectDiv" style="display:none;">
  <h2 style="text-align: center;">Update News</h2>
 <span style="margin-right:30px;"><b>Start Date:<font class="requiredFont">*</font></b><input type="text" name="fromDate" class="inputClass dateField" id="newsFromDateId" readonly="true"/></span>
 <span><b>End Date:<font class="requiredFont">*</font></b><input type="text" name="toDate" readonly="true" class="inputClass dateField" id="newsToDateId"/></span>
 <input type="button" value="submit" onclick="buildNewsDetails()" class="btn btn-info"/>

</div>

<div id='profileManagementMainOuterDiv4' style="display:none">
	<div id='profileManagementHeaderDiv4' class="row-fluid">

		<!--<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">News</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>-->
		<div class="span10 offset1 text-center alert">NEWS</div>
	
</div>
<div id='newsGallaryDiv' class="divInfo">
	 </div>		

<!-- for  body 4  result  end -->

<div id="ajaxImg" style="display:none;margin-left:300px;margin-top:30px;"><img src="images/icons/goldAjaxLoad.gif"></img></div>
</div>
<!-- for  body 5  result  start -->
<div id='profileManagementMainOuterDiv5' style="display:none">
	<div id='profileManagementHeaderDiv5' class="row-fluid">

		<div class="span10 offset1 text-center alert">Report</div>
	
</div>
<div id='newsReportDiv' class="divInfo">
		
	 </div>		
</div>

	 <!-- for  body 5  result  end -->
	 
<!-- for  body 6  result  start -->
<div id='profileManagementMainOuterDiv6' style="display:none">
	<div id='profileManagementHeaderDiv6' class="row-fluid">
	<div class="span10 offset1 text-center alert">KeyWords</div>
	</div>
<div id='keyWordsMainDiv' class="divInfo">
		
	 </div>	
</div>	 

<!-- for  body 6  result  end -->
<!-- for  body 7  result  start -->
<div id='profileManagementMainOuterDiv7' style="display:none">
	<div id='profileManagementHeaderDiv7' class="row-fluid">
	<div class="span10 offset1 text-center alert">News Report</div>
	</div>
<div id='newsReportsMainDiv' class="divInfo">
		
	 </div>	
</div>	 

<!-- for  body 7  result  end -->
<script>
var keywordsArray = new Array();
<c:forEach var="keywords" items="${keywordsList}">
   var obj = {value:${keywords.id},
			name:"${keywords.name}"}

		keywordsArray.push(obj);
</c:forEach>

var keywordGallaries;
var newsDetails;
var bvalue = false;
var noOfRowsPerPage = 10;
var modifiedRecord = 0;
$(document).ready(function() {

$(document).ready(function(){

	$('#partyManagementTabId').addClass('menuActive');
});


	showNewsGallaey();

	$(".nav-tabs li a").click(function()
			{
				$(".nav-tabs li").removeClass('active');
				$(this).parent("li").addClass('active');
				
			});
			$(".highlight").live("click",function(){
				
				$(this).css("background","#BBBB51");
			});
			$(".highlight").live("blur",function(){
				
				$(this).css("background","#51A351");
			});
$(".dateField").live("click", function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
      changeYear: true,
		maxDate: new Date()
		
	}).datepicker("show");
});


	 $(document).on("change",'#gallariesList', function(){
		 $("#noNewsError").html('');
		 $('#dateErrorMessage').html("");

       if($('#fromDateId').val() == "" || $('#toDateId').val() == ""){
			$('#dateErrorMessage').html("<b style='color:red;'>Select Date Range</b>");
		}else{
		getAllNewsOfAGallary(this.value);
		}
     });

     $(document).on("change",'#partyList', function(){
         $('#candidateNewsList , #respenseNewsList').find('option').remove();
		 $('#candidateNewsList , #respenseNewsList , #buttonsDiv').hide();

		getNewsForAParty(this.value);
     });

	$(document).on("change",'.typeRadio', function(){
		$('#noNewsError ,#dateErrorMessage').html('');
		$('#candidateNewsList , #respenseNewsList , #buttonsDiv').hide();
        $("#gallaryShowHideDiv").css("display","none");
		$("#categoryShowHideDiv").css("display","none");
		if(this.value == "candidate"){
			$('#candidatesDiv').show('slow');
			$('#partyNewsDiv').hide('slow');
			$('#candidatesList').val("");
		}else
		{
			$("#categoryGallaryHideShowDiv").css("display","none");
			$('#candidatesDiv').hide();
			$('#partyNewsDiv').show('slow');
			$('#partyList').val(872);
			getNewsForAParty(872);

		}
     });


$(document).on("click",'#fromDateId1 , #toDateId1', function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
        changeYear: true, 
		maxDate: new Date()
	    
	}).datepicker("show");
});
	
$(document).on("click",'#fromDateId , #toDateId', function(){
 $(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
        changeYear: true,
		maxDate: new Date(),
	    onSelect: function(dateText, inst) { 
			isDatesValid();
			if($('input:radio[name=newsType]:checked').val() == "party")
 				   checkAllValuesAndSendAjaxForNews();
			else if($('input:radio[name=newsType]:checked').val() == "candidate")
                   checkAllValuesAndSendAjax();
	}
		
	}).datepicker("show");
});

    $(document).on('click','#responseDiv',function(){
		$('#candidateNewsList').empty();
		$('#respenseNewsList').empty();		
		  if($('#responseContentDiv').css('display') == "block"){
			  $('#candidatesList').val("");
			  $('#dateErrorMessage').html("");
              $('#noNewsError').html('');
		  }

		   $('#responseContentDiv').toggle('slow');
		   		//alert($('#responseContentDiv').is(":visible"))


     });


	    $(document).on('click','#addFile',function(){
    $("#candidateNewsList > option:selected").each(function(){
      $(this).remove().appendTo("#respenseNewsList");
    });
	  $('#respenseNewsList option').prop('selected', 'selected')
  });

  $(document).on('click','#deleteFile',function(){
   $("#respenseNewsList > option:selected").each(function(){
      $(this).remove().appendTo("#candidateNewsList");
   });

	  var my_options = $("#candidateNewsList option");

	my_options.sort(function(a,b) {
		if (a.text > b.text) return 1;
		else if (a.text < b.text) return -1;
		else return 0
	})

	$("#candidateNewsList").empty().append( my_options );

	 $('#respenseNewsList option').prop('selected', 'selected');
	});


	
  $(document).on('click','#button1',function(){
    $("#list1 > option:selected").each(function(){
      $(this).remove().appendTo("#candidateList");
		// showUploadFilesDiv($(this).val(),$(this).text());

        addMoreFiles1($(this).val(),$(this).text());
    });
	  $('#candidateList option').prop('selected', 'selected');

	 

  });

  $(document).on('click','#button2',function(){

		

   $("#candidateList > option:selected").each(function(){
		 $('.div'+$(this).val()).remove();

		/* $("input:[value='"+ $(this).val()+"']").each(function(){
			 alert($(this).attr('id'));

		 });*/
      $(this).remove().appendTo("#list1");
   });

	 var my_options = $("#list1 option");

	my_options.sort(function(a,b) {
		if (a.text > b.text) return 1;
		else if (a.text < b.text) return -1;
		else return 0
	})

	$("#list1").empty().append( my_options );
	 $('#candidateList option').prop('selected', 'selected');

   });

    $(document).on('change','#candidatesList',function(){
        $('#dateErrorMessage').html("");
		$('#noNewsError').html("");
		
		if($('#fromDateId').val() == "" || $('#toDateId').val() == ""){
			$('#dateErrorMessage').html("<b style='color:red;'>Select Date Range</b>");
		}else if($(this).val() != ""){

		 $("#categoryGallaryHideShowDiv").css('display','inline-block');
		 $("#gallaryShowHideDiv").css("display","none");

         $("#respenseNewsList").find('option').remove();
		 $("#candidateNewsList").find('option').remove();

		 $("#gallaryAllCheckboxId").attr("checked",true);
		 getCandidateNews();

         //getCandidateNews($(this).val());
		 //$("#gallaryAllCheckboxId").trigger('click');
		}
   
    });
//CategoryGallary
//gallary
$("#gallaryCheckboxId").live("click",function(){
 $("#categoryCheckboxId").attr('checked',false);
 $("#gallaryAllCheckboxId").attr('checked',false);
 $("#respenseNewsList").find('option').remove();
 $("#respenseNewsList").css('display','none');
 $("#buttonsDiv").css('display','none');
 $("#categoryShowHideDiv").css('display','none');
 if($("#gallaryCheckboxId").is(":checked"))
 {
  getCandidateGallaries();
 }
	
});
$("#gallaryList").live("change",function(){
 getNewsTitlesForACandidateByGallaryId();
	
});
$("#categoryGallarySelect").live("change",function(){
 getNewsTitlesForACandidateByGallaryId();
	
});
//category
$("#categoryCheckboxId").live("click",function(){
 $("#gallaryCheckboxId").attr('checked',false);
 $("#gallaryAllCheckboxId").attr('checked',false);
 $("#candidateNewsList").css("display","none");
 $("#buttonsDiv").css("display","none");
 $("#respenseNewsList").find("option").remove();
 $("#respenseNewsList").css("display","none");
 $("#categoryGallary").attr('checked',false);

 $("#candidateNewsList").css("display","none");

 $("#respenseNewsList").find('option');
 $("#respenseNewsList").css("display","none");

 $("#gallaryShowHideDiv").css("display","none");

 if($("#categoryCheckboxId").is(":checked"))
  getCandidatecategories();
	
});
$("#gallaryList").live("change",function(){
 $("#respenseNewsList").find('option').remove();
	
});

$("#categoryGallary").live("click",function(){

	var candidateId = $("#candidatesList").val();
	$('#dateErrorMessage').html('');
	$("#candidateNewsList").css('display','none');
    $("#buttonsDiv").css('display','none');
	$("#respenseNewsList").css('display','none');
	$('#noNewsError').html('');

  if($("#categoryGallary").is(":checked"))
  {
	 
	 if(candidateId == 0)
	 {
       $('#dateErrorMessage').html('Please Select Candidate.');
		return;
	 }
      if($("#candidateCategoryId").val() == 0)
	  {
        $('#dateErrorMessage').html('Please Select Category.');
		return;
	  }

	 var categoryIdsArray = new Array();
    
	  $('#candidateCategoryId > option:selected').each(
       function(i){
         categoryIdsArray.push($(this).val());
     });
       if(categoryIdsArray == null || categoryIdsArray == "null" || categoryIdsArray.length == 0)
	   {
         $('#dateErrorMessage').html('Please Select Category.');
		 return;
	   }
	$("#gallaryShowHideDiv").css('display','block');
	
      var jsObj={
		candidateId:candidateId,
		categoryIds:categoryIdsArray,
		task:'getGallariesForSelectedCategory'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getGallariesForSelectedCategoryAction.action?"+rparam;
	callAjax(jsObj, url);
	 
 
  }
  else
	{
     clearOptionsListForSelectElmtId('categoryGallarySelect');
	 $("#gallaryShowHideDiv").css("display","none");
	}

});
$("#candidateCategoryId").live("click",function(){
	
	$("#categoryGallary").attr('checked', false);
	$("#gallaryShowHideDiv").css("display","none");
});


//Category Onchange

$("#candidateCategoryId").live("change",function(){

  var candidateId = $("#candidatesList").val();
  var categoryId = $("#candidateCategoryId").val();
  var fromDate = $("#fromDateId").val();
  var toDate = $("#toDateId").val();
  $("#respenseNewsList").find('option').remove();
  $("#candidateNewsList").find('option').remove();

  $("#respenseNewsList").css('display','none');
  $("#candidateNewsList").css('display','none');
  $("#buttonsDiv").css('display','none');

  $("#noNewsError").html('');
  $("#dateErrorMessage").html('');

  if(categoryId == 0)
  {
    $("#noNewsError").html('Please Select Category.');
	return;
  }
  
  
  if($("#categoryGallary").is(":checked") == false)
  {
	 $("#respenseNewsList").css('display','inline-block');
     $("#candidateNewsList").css('display','inline-block');
	 $("#buttonsDiv").css('display','inline-block');

    var jsObj={
		candidateId:candidateId,
		categoryId:categoryId,
		fromDate:fromDate,
	    toDate:toDate,
		task:'getNewsForACandidateByCategoryId'
	  };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	 var url = "getNewsForACandidateByCategoryIdAction.action?"+rparam;
	 callAjax(jsObj, url);
  }
	
});


$("#assignNewsgallaryList").live("change",function(){
getNewsTitlesByGalleryId("assignNewsgallaryList");
 
});
$("#responseNewsgallaryList").live("change",function(){
	
getNewsTitlesByGalleryId("responseNewsgallaryList");	
});

$("#assignNewsbtn").live("click",function(){
   
    $("#errorMessageDiv").html('');
    
	var gallaryId = $("#assignNewsgallaryList").val();
    var fileGalleryId = $("#newsTitlesSelectList").val();
	var radioVal = $('input[name=assignNewsRadio]:checked').val();
	var responseId =0;
	var candidateId =0;
	var tempVar = "";
	var resFileGalId;
	if(gallaryId == 0)
	{
     $("#errorMessageDiv").html('Please Select Gallery.');
	 return;
	}
    else if(fileGalleryId == 0)
    {
     $("#errorMessageDiv").html('Please Select News.');
	 return;
	}
	if(radioVal == "assignResponse")
	{

	  var responseGalleryId = $("#responseNewsgallaryList").val();
	  resFileGalId = $("#responseNewsTitlesSelectList").val();
      
	   if(responseGalleryId == 0)
      {
       $("#errorMessageDiv").html('Please Select Response Gallery.');
	   return;
	  }
	  else if(fileGalleryId == 0)
      {
       $("#errorMessageDiv").html('Please Select News.');
	   return;
	  }
	  else if(fileGalleryId == resFileGalId)
	  {
       $("#errorMessageDiv").html("We Can't assign this news to response.");
	   return;
	  }
	  tempVar = "assignResponse";
	}
	else
    {
		var partyId = $("#partiesList").val();
		candidateId = $("#candidatesLists").val();
		resFileGalId = 0;
		if(partyId == 0)
		{
		 $("#errorMessageDiv").html('Please Select Party.');
	     return;
		}
		else if(candidateId == 0)
		{
         $("#errorMessageDiv").html('Please Select Candidate.');
	     return;
		}

	   
	   tempVar = "assignToCandidate";

	}
	var jsObj={
		candidateId:candidateId,
		fileGalleryId:fileGalleryId,
		resFileGalId:resFileGalId,
	    tempVar:tempVar,
		task:'assignResToCandidateOrAGallary'
	  };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	 var url = "assignResToCandidateOrAGallaryAction.action?"+rparam;
	 callAjax1(jsObj, url);

	
});


$(".assignNewsRadioCls").live("click",function(){
 var radioVal = $('input[name=assignNewsRadio]:checked').val();
 
 if(radioVal == "assignToCandidate")
 {
  $("#candidateShowHideDiv").css("display","block");
  $("#showHideResponseGallaryDiv").css("display","none");
  getCandidatesByPartyId();
 }
 else
 {
   $("#showHideResponseGallaryDiv").css("display","block");
    $("#candidateShowHideDiv").css("display","none");
 }
	
});


});//End of Ready 





</script>

<script>







var count = 0;
function changeLanguage(){
var str='';
if(count == 0)
	str='Eenadu Telugu';	
if(/Eenadu Telugu/i.test(str))
{
 count = count+1; 
$('#sourceTelugu').css("display","none");
$('#sourceEnglish').css("display","block");
if(!$('#newsfileTitle').hasClass('enadu'))
{
$('#newsfileTitle').addClass('enadu');
$('#newsfileDescription').addClass('enadu');
$('#newsDesc').addClass('enadu');
}
}else{
	count = 0;
$('#sourceTelugu').css("display","block");
$('#sourceEnglish').css("display","none");
if($('#newsfileTitle').hasClass('enadu'))
{
$('#newsfileTitle').removeClass('enadu');
$('#newsfileDescription').removeClass('enadu');
$('#newsDesc').removeClass('enadu');
}
}


}


//changes by anil
function htmlEntity(aa) {

var bb = '';
for (i = 0; i < aa.length; i++)
bb += hoj(aa.charAt(i));
return bb;
}
function hoj(d) 
{         if (d == '<')
         return '&lt;';
       else
        if (d == '>')
          return '&gt;';
    
if (d == '<')
return '&lt;';
if (d == '>')
return '&gt;';
if (d == '&')
return '&amp;';
  
   if (d.charCodeAt(0) > 127)
   return '&#' + d.charCodeAt(0) + ';';
  return d;
    }

	function buildNewsOfAGallary(divId , results)
{
 $('#noNewsError').html('');
  $('#'+divId).find('option').remove();
  if(results != null && results.length > 0){
	  	  $('#candidateNewsList , #respenseNewsList , #buttonsDiv').show();


	 $.each(results , function(index , value){

		if(value.flag == true)
		  $('#'+divId).append('<option class="enadu" title="'+value.name+'" value="'+value.id+'">'+value.name+'</option>');
		else
			$('#'+divId).append('<option class="notEenadu" title="'+value.name+'" value="'+value.id+'">'+value.name+'</option>');


	});
  }else{
	  $('#noNewsError').html('<b style="color:red;">No news found.Changing the date range may help you.</b>');
	  $('#candidateNewsList , #respenseNewsList , #buttonsDiv').hide();

  }

}



function buildGallaries(divId , results)
{
   $('#'+divId).find('option').remove();
   $('#'+divId).append('<option value="0">Select Gallery</option>');

  $.each(results,function(key , value){
   $('#'+divId).append('<option value="'+value.id+'">'+value.name+'</option>');

  });


}
function IsNumeric(val) {
	$('#Err4Numer').hide();
	if (!(Number(val)==val)) {
	$('#Err4Numer').show();
		$('.pageno').val('');
	}
}
function IsNumeric1(val) {
	$('#Err4Numer1').hide();
	if (!(Number(val)==val)) {
		$('#Err4Numer1').show();
		$('.newslength').val('');
	}
}


//Gallary

function getCandidateGallaries()
{
  $("#candidateNewsList").css("display","none");
  $("#gallaryShowHideDiv").css("display","block");
    var fromDate = "";
	var toDate = "";
	var candidateId = $("#candidatesList").val();
	$("#noNewsError").html('');
	$("#dateErrorMessage").html('');
	if(candidateId == 0)
	{
	  $("#noNewsError").html('Please Select Candidate');
	  return;	
	}
	   fromDate = $("#fromDateId").val();
	   toDate = $("#toDateId").val();
	
	   if(fromDate=="" && toDate == "")
	   {
		 $("#noNewsError").html('Please Select From And To Dates');
		 return;
	   }
	   else if(fromDate =="")
	   {
	     $("#noNewsError").html('Please Select From Date');
		 return;
	   }
	   else if(toDate =="")
	   {
	     $("#noNewsError").html('Please Select To Date');
		 return;
	   }
	   /* else if (Date.parse(fromDate) > Date.parse(toDate)) {
         $("#noNewsError").html('Invalid Date Selection.');
         return;
	   }  */
	

	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		candidateId:candidateId,
		task:'getCandidateRelatedGallaries'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidateRelatedGallariesAction.action?"+rparam;
	callAjax(jsObj, url);


}

function getCandidatecategories()
{
	$("#categoryShowHideDiv").css("display","block");
   var fromDate = "";
	var toDate = "";
	var candidateId = $("#candidatesList").val();
	$("#dateErrorMessage").html('');
	$('#noNewsError').html("");
	if(candidateId == 0)
	{
	  $("#dateErrorMessage").html('Please Select Candidate');
	  return;	
	}
	
	   fromDate = $("#fromDateId").val();
	   toDate = $("#toDateId").val();
	
	   if(fromDate=="" && toDate == "")
	   {
		 $("#dateErrorMessage").html('Please Select From And To Dates');
		 return;
	   }
	   else if(fromDate =="")
	   {
	     $("#dateErrorMessage").html('Please Select From Date');
		 return;
	   }
	   else if(toDate =="")
	   {
	     $("#dateErrorMessage").html('Please Select To Date');
		 return;
	   }
	  /*  else if (Date.parse(fromDate) > Date.parse(toDate)) {
         $("#dateErrorMessage").html('Invalid Date Selection.');
         return;
	    
	} */

	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		candidateId:candidateId,
		task:'getCandidateRelatedCategories'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidateRelatedCategoriesAction.action?"+rparam;
	callAjax(jsObj, url);

}

function getNewsTitlesForACandidateByGallaryId()
{
	$("#noNewsError").html('');
	$("#dateErrorMessage").html('');
  var candidateId = $("#candidatesList").val();
	var gallaryId = $("#gallaryList").val();
	var fromDate = $("#fromDateId").val();
	var toDate = $("#toDateId").val();

	$("#respenseNewsList").find('option').remove();
  $("#candidateNewsList").find('option').remove();

  $("#respenseNewsList").css('display','none');
  $("#candidateNewsList").css('display','none');
  $("#buttonsDiv").css('display','none');

	if(gallaryId == 0)
	{
	  $("#noNewsError").html('Please Select Gallery.');
	  return;
	}

    $("#respenseNewsList").css('display','inline-block');
    $("#candidateNewsList").css('display','inline-block');
    $("#buttonsDiv").css('display','inline-block');

	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		candidateId:candidateId,
		gallaryId:gallaryId,
		task:'getNewsTitlesForACandidate'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getNewsTitlesForACandidateAction.action?"+rparam;
	callAjax(jsObj, url);
}

function createOptionsForSelectElement(elmtId,optionsList)
{	
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	
	var option = document.createElement('option');
	option.value="0";
	option.text="Select";
	try
	{
		elmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		elmt.add(option); // IE only
	}

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

}


function assignNewsToCandidate()
{
  $("#newsGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").css("display","block");
  $("#newsAssignGallaryDiv").html('');
  $("#profileManagementMainOuterDiv4").css("display","none");
  $("#profileManagementHeaderDiv2").css("display","none");
  $("#profileManagementMainOuterDiv3").css("display","block");
  $("#profileManagementHeaderDiv3").css("display","none");
  $("#videoGallaryDiv").css("display","none");
  $("#dateSelectDiv").css("display","none");
  $("#profileManagementMainOuterDiv5").css("display","none");
  $("#profileManagementMainOuterDiv6").css("display","none");
   $("#profileManagementMainOuterDiv7").css("display","none");
  var str = '';
  str +='<div id="content" style="width:650px;" class="assignNewsDivCls">';
  str +='<h2 style="text-align: center;">Assign News</h2>';
  str +='<div id="errorMessageDiv"></div>';
  str +='<div id="assignNewsInnerDiv">';
  str +='<label id="fromDateLabelId">From Date:<input type="text" readonly="true" id="fromDateId1" class="inputClass assignNewsDateCls fromDateCls" name="fromDate"></label>';
  str +='<label id="toDateLabelId">ToDate :<input type="text" id="toDateId1" class="inputClass assignNewsDateCls toDateCls" readonly="true" name="toDate"></label>';
  str +='<label>Select Gallery: <select id="assignNewsgallaryList"></select></label>';
  str +='<label>Select News: <select id="newsTitlesSelectList"></select></label>';
  str +='<div id="assignNewsRadioDiv">';
  str +='<input id="assignNewsResradio" type="radio" value="assignResponse" name="assignNewsRadio" class="assignNewsRadioCls" checked="true"/>Set As Response';
  str +='<input id="assignNewsCandidateRadio" type="radio" value="assignToCandidate" name="assignNewsRadio" class="assignNewsRadioCls"/>Assign Candidate';
  str +='</div>';
  
  str +='<div id="showHideResponseGallaryDiv">';
  str +='<label>Select Gallery: <select id="responseNewsgallaryList"></select></label>';
  str +='<label>Select News: <select id="responseNewsTitlesSelectList"></select></label>';
  str +='</div>';

  str +='<div id="candidateShowHideDiv" style="display:none;">';
  str += '<label>Select Party : <font class="requiredFont">*</font>';
  str += ' <select id="partiesList" name="party" onchange="getCandidatesByPartyId()"><option value="0">Select Party</option><option value="163">BJP</option><option value="265">CPI</option><option value="269">CPM</option><option value="362">INC</option><option value="990">MIM</option><option value="872" selected>TDP</option><option value="886">TRS</option><option value="1117">YSRCP</option></select></label>';
	

  str +='<label>Select Candidate : <font class="requiredFont">*</font><select id="candidatesLists"></select></label>';
  str +='</div>';
  str +='<input type="button" value="submit" class="btn btn-info" id="assignNewsbtn"/>';

  str +='</div>';
  str +='</div>';
  $("#newsAssignGallaryDiv").html(str);

  getGalleryListForAParty();
}


function getGalleryListForAParty()
{
	var fromDate = $("#fromDateId1").val();
	var toDate = $("#toDateId1").val();
    var locationIdsArray = new Array();
	var locationScope = "";
	var jsObj={
		fromDate:fromDate,
		toDate:toDate,
		locationIdsArray:locationIdsArray,
		locationScope:locationScope,
		task:'getGalleryListForAParty'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getGalleryListForAPartyAction.action?"+rparam;
	callAjax(jsObj, url);
}

function getNewsTitlesByGalleryId(divId)
{
	var gallaryId;
	var fromDate;
	var toDate;
  if(divId == "assignNewsgallaryList"){
   gallaryId = $("#assignNewsgallaryList").val();
	fromDate = $("#fromDateId1").val();
	toDate = $("#toDateId1").val();
   }

  if(divId == "responseNewsgallaryList"){
    gallaryId = $("#responseNewsgallaryList").val();
	toDate = $("#toDateId1").val();
	fromDate = $("#fromDateId1").val();
   }
 $("errorMsgDiv").html('');
 if(gallaryId == 0)
 {
   $("errorMsgDiv").html('Please Select Gallery.');
   return;
 }

 var jsObj={
		gallaryId:gallaryId,
		fromDate:fromDate,
	    toDate:toDate,
        divId:divId,
		task:'getNewsByGalleryId'
	  };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	 var url = "getNewsByGalleryIdAction.action?"+rparam;
	 callAjax(jsObj, url);

}

function getCandidatesByPartyId()
{
 
 $("#errorMessageDiv").html('');
 var partyId = $("#partiesList").val();
 if(partyId == 0)
 {
   $("#errorMessageDiv").html('Please Select Party.');
   return;
 }

  var jsObj = {
			partyId :partyId,
			task : "getCandidatesByPartyId"	
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidatesOfAParty.action?"+rparam;					
	
  callAjax(jsObj,url);
}

function showAssignNewsStatus(results)
{
	$("#errorMessageDiv").html('');
  if(results.resultCode == 0 && results.message != null)
  {
	  $("#assignNewsgallaryList").val("0");
	  $("#responseNewsgallaryList").val("0");

	  clearOptionsListForSelectElmtId('newsTitlesSelectList');
	  clearOptionsListForSelectElmtId('responseNewsTitlesSelectList');

   $("#errorMessageDiv").html('News is Already Assigned.').css("color","green");
   return;
  }
  else if(results.resultCode == 0 && results.message == null)
  {
	  $("#assignNewsgallaryList").val("0");
	  $("#responseNewsgallaryList").val("0");
	  clearOptionsListForSelectElmtId('newsTitlesSelectList');
	  clearOptionsListForSelectElmtId('responseNewsTitlesSelectList');

   $("#errorMessageDiv").html('News Assigned Successfully.').css("color","green");
   return;
  }
  else
  {
   $("#errorMessageDiv").html('Error occured! try again.').css("color","red");
   return;
  }
}	
	
function isDatesValid()
{
	$('#noNewsError').html("");
	var fromDate = $("#fromDateId").val();
	var toDate  = $("#toDateId").val();
		if(fromDate.length > 0 && toDate.length > 0 )
		{		    
		  var dt1  = parseInt(fromDate.substring(0,2),10);
		  var mon1 = parseInt(fromDate.substring(3,5),10);
		  var yr1  = parseInt(fromDate.substring(6,10),10);
		  var dt2  = parseInt(toDate.substring(0,2),10);
		  var mon2 = parseInt(toDate.substring(3,5),10);
		  var yr2  = parseInt(toDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{
		 $('#noNewsError').html("Start Date should be Less Than End Date");
		}
	}
}

function isDatesValid1()
{
	$('#errorMessageDiv').html("");
	var fromDate = $("#fromDateId1").val();
	var toDate  = $("#toDateId1").val();
		if(fromDate.length > 0 && toDate.length > 0 )
		{		    
		  var dt1  = parseInt(fromDate.substring(0,2),10);
		  var mon1 = parseInt(fromDate.substring(3,5),10);
		  var yr1  = parseInt(fromDate.substring(6,10),10);
		  var dt2  = parseInt(toDate.substring(0,2),10);
		  var mon2 = parseInt(toDate.substring(3,5),10);
		  var yr2  = parseInt(toDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{ 
		 $('#errorMessageDiv').html("From Date should be Less Than To Date");
		}
	}
}
function saveNewSourceDetails()
{
	$('#errorDiv').html('');
	var sourceName =  $.trim($('#sourceName').val());
	if(sourceName.length <=0){
		$('#errorDiv').html('Source Name Should not be empty');
		$('#errorDiv').css('color','red')
		return false;
	}
	var jsObj=
	{
		name  : sourceName,
		task  : "storeSource"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "storeSourceDetails.action?"+rparam;	

	callAjax(jsObj,url);
}

function createNewSource()
{
	//$('#sourceDetails').show();
	var str = "";
	str +='<h2 align="center">Create A New Source</h2>';
	str+='<div id="sourceDetails"  style="width: 400px; border: 1px solid #CCCCCC; border-radius: 4px 4px 4px 4px; padding: 4px;margin-left: 298px;">';
	str +=  '<span>Source Name : </span><input type="text" id="sourceName"></input></br>';
	str += "<div id='errorDiv' style='color:red'> </div>";
	str +=  '<input type="button" value="create new Source" onClick="saveNewSourceDetails();" class="btn btn-info" style="margin-left: 244px;"></input>';
	
	str+='</div>';
	$('#newsGallaryDiv').html(str);
	
	
}

$('#updateCategoryId').live('click',function(){
	var jsObj=
	{
		task  : "getAllCategories"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllNewsCategoryAction.action?"+rparam;						
	callAjax1(jsObj,url);
});
function buildCategoriesOfUser(results,jsObj){
var visibility="";
var sno=0;
	var str='';
	str+='<table class="table table-bordered" id="categoriesDT">';
	str+='<thead>';
		str+='<tr>';
			str+='<th>S.No';
			str+='</th>';
			
			str+='<th>Category';
			str+='</th>';
			
			str+='<th>Access Type';
			str+='</th>';
			
			str+='<th>Is Deleted';
			str+='</th>';
			
			str+='<th>Update';
			str+='</th>';
			
		str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
		for(var i in results){
		sno=sno+1;
			str+='<tr>';
				str+='<td>'+sno+'</td>';
			
				str+='<td>'+results[i].name+'</td>';
			
			if(results[i].visibility=="false"||results[i].visibility=="public"){
				visibility="public";
			}else{
				visibility="private";
			}
			
				str+='<td>'+visibility+'</td>';
			
				if(results[i].isDeleted=="false"){
					str+='<td onclick="onCategory('+results[i].id+',\'delete\')" title="Delete Category"><img src="images/icons/delete.png"/></td>';
                   
				 str+='<td onclick="editCategory('+results[i].id+',\''+results[i].name+'\',\''+visibility+'\')"><img src="images/icons/edit.png"/></td>';

				}else{
					str+='<td onclick="onCategory('+results[i].id+',\'accept\')" title="Enable this Category"><img src="images/icons/accept.png"/></td>';

					str+='<td><img src="images/icons/edit.png"/></td>';
				}
			
			str+='</tr>';
		}
	str+='</tbody>';
	str+='</table>';
	$('#categoriesTable').html(str);
	
	$('#categoriesDT').dataTable({
		   "iDisplayLength": 15,
			"aLengthMenu": [[15, 30, -1], [15, 30, "All"]]
	});
}
 $(function() {
	$( "#dialog" ).dialog({
		autoOpen: false,
	    modal: true,
		position:'center',
		resizable: false,

	});
});

function onCategory(id,name){
$('#errMsg').html('');
 var jsObj=
	{
		idVal:id,
		name :name,
		task : "onOroffCategory"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "deleteOrAcceptCategoryAction.action?"+rparam;						
	callAjax1(jsObj,url);
 
}

function editCategory(id,name,visibility){
	$('#errMsg').html('');
	$( "#dialog" ).dialog( "open" );
	$('#categoryNameId').val(name);
	$('input[name=visibility][value='+visibility+']').prop("checked",true);
	$('#idVal').val(id);
	
}

$('#updateCategoryId').click(function(){
	var categoryName=$('#categoryNameId').val();
	var visibility = $('input:radio[name=visibility]:checked').val();
	var idVal=$('#idVal').val();
	
	var jsObj=
	{
		category:categoryName,
		visibility:visibility,
		idVal:idVal,
		task  : "updateCategory"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "updateNewsCategoryAction.action?"+rparam;						
	callAjax1(jsObj,url);
});

function buildUpdateStatus(result)
{
	
	$('#newsSuccessDiv').html("<font style='font-weight:bold;color:green;margin-left:50px;'>News Updated Successfully.</font>");
       setTimeout(hideDialog,3000);
    buildNewsDetails();
}

function getAllCategoriesForGallary()
{
 var jsObj=
	{
		task  : "getTotalCategories"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCategoriesListAction.action?"+rparam;						
	callAjax1(jsObj,url);
}

function getGallariesForSelectedCategory()
{

  $("#uploadNewsFileErrorDiv1").html('');
  var categoryId = $("#category").val();
  if(categoryId == 0)
  {
    $("#uploadNewsFileErrorDiv1").html('Please Select Category.').css("color","red");
     return;
  }
	 var jsObj=
	{
	    categoryId:categoryId,
		task  : "getGallariesInCategory"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getGallariesByCategoryIdAction.action?"+rparam;						
	callAjax1(jsObj,url);
}
function getScopeForUser(){
  
 var jsObj =
		{ 
            time : timeST,
			divId:"scopeDiv",
  		    task:"getLocationScope"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationScopeAction.action?"+rparam;						
	callAjax(jsObj,url);
 
}

function showUserAccessLocationScopeList(results)
{
  
  
  var id = results.scopeId;
 
  $("#scopeDiv").val(id);
  //$("#scopeDiv option:selected").val(id);
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
  str +='	   <td class="tdWidth1">State : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select name="locationValue" id="stateDiv" onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)" /></td>';
  str +='  </tr>';
  
  /*str +='  <tr>';
  str +='	   <td class="tdWidth1">District : <font class="requiredFont">*</font></td>';
  str +='	   <td class="selectWidthPadd"><select id="districtDiv" name="locationValue"></select></td>';
  str +='  </tr>';*/

  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   buildSelectOptionVOList(results.stateList,'stateDiv',1);
   //buildSelectOptionVOList(results.districtList,'districtDiv',null);

  }

  else if(id == 3)
  {

    var str ='';
    str +='<table>';
    str +='  <tr>';
    str +='	   <td class="tdWidth1">State : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="stateDiv" onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)" /></td>';
    str +='  </tr>';
    str +='  <tr>';
    str +='	   <td class="tdWidth1">District : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="districtDiv" name="locationValue"></select></td>';
    str +='  </tr>';
		
	/*str +='  <tr>';
    str +='	   <td class="tdWidth1">Assembly Constituency : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="constituencyDiv" name="locationValue" ></select></td>';
    str +='  </tr>';*/

   

    str +='</table>';
    document.getElementById("showScopeSubs").innerHTML = str;
    buildSelectOptionVOList(results.stateList,'stateDiv',results.stateId);
    buildSelectOptionVOList(results.districtList,'districtDiv',results.districtId);
	//buildSelectOptionVOList(results.constituencyList,'constituencyDiv',null);
	
  }
  


  else if(id == 4)
  {

    var str ='';
    str +='<table>';
    str +='  <tr>';
    str +='	   <td class="tdWidth1">State : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="stateDiv" onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)" /></td>';
    str +='  </tr>';
    str +='  <tr>';
    str +='	   <td class="tdWidth1">District : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="districtDiv" name="locationValue"></select></td>';
    str +='  </tr>';
		
	str +='  <tr>';
    str +='	   <td class="tdWidth1">Assembly Constituency : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="constituencyDiv" name="locationValue" ></select></td>';
    str +='  </tr>';

   /* str +='  <tr>';
    str +='	   <td class="tdWidth1">Mandal/ Municipality/ Corp/GMC : <font class="requiredFont">*</font></td>';
    str +='	   <td class="selectWidthPadd"><select id="mandalDiv"    onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\')"></select></td>';
    str +='  </tr>';*/

    str +='</table>';
    document.getElementById("showScopeSubs").innerHTML = str;
    buildSelectOptionVOList(results.stateList,'stateDiv',results.stateId);
    buildSelectOptionVOList(results.districtList,'districtDiv',results.districtId);
	buildSelectOptionVOList(results.constituencyList,'constituencyDiv',results.constituencyId);
	//buildSelectOptionVOList(results.mandalList,'mandalDiv',null);
  }
  
  
  
}



	
	

function buildSelectOptionVOList(optionsList,elmt,populatedId)
{
	
 if(!elmt || optionsList == null)
  return;
 
 var divEle = document.getElementById(elmt);
  var option = document.createElement('option');
	option.value="0";
	option.text="Select";
  if(populatedId == null)
  {
	try
	{
		divEle.add(option,null); // standards compliant
	}
	catch(ex)
	{
		divEle.add(option); // IE only
	}
  }

	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		try
		{
			divEle.add(option,null); // standards compliant
		}
		catch(ex)
		{
			divEle.add(option); // IE only
		}
	}

	if(populatedId != null)
     divEle.value = populatedId;
	 


}


function createReport()
{

 $("#newsReportDiv").css("display","block");
  $("#newsGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").css("display","none");
  $("#newsAssignGallaryDiv").html('');
  $("#profileManagementMainOuterDiv4").css("display","none");
  $("#profileManagementHeaderDiv2").css("display","none");
  $("#profileManagementMainOuterDiv3").css("display","none");
  $("#profileManagementHeaderDiv3").css("display","none");
  $("#videoGallaryDiv").css("display","none");
  $("#dateSelectDiv").css("display","none");
  $("#profileManagementMainOuterDiv5").css("display","block");
  $("#profileManagementHeaderDiv5").css("display","none");
  $("#profileManagementMainOuterDiv6").css("display","none");
  $("#profileManagementMainOuterDiv7").css("display","none");
  var str = '';
  str +='<div class="container well">';
  str +='<h2 style="text-align: center;"> News Report</h2>';

  str +='<div id="newsReportInnerDiv">';
  str +='<div id="newsReporterrorMessageDiv"></div>';
  str +='<label id="fromDateLabelId">From Date:<input type="text" readonly="true" id="fromDateId1" class="inputClass assignNewsDateCls fromDateCls" name="fromDate"></label>';
  str +='<label id="toDateLabelId">ToDate :<input type="text" id="toDateId1" class="inputClass assignNewsDateCls toDateCls" readonly="true" name="toDate"></label>';
   str+='<table>';
   str+='<tr>';
  str+='<td>News Description </td><td><textarea maxlength="330" name="fileDescription" rows="3" cols="20" id="newsreportfileDescription"></textarea></td>';
   str+='</tr>';
   str+='<tr>';
  str +='<td>Select Level</td><td><select id="regionlevel"><option value="1">All</option><option value="2">STATE</option><option value="3">DISTRICT</option><option value="4">CONSTITUENCY</option></select></td>';
    str+='</tr>';
	  str+='<tr>';
  str +='<td>Select News</td><td><select id="newsPriority"><option value="0">All</option><option value="1">Low</option><option value="2">Medium</option><option value="3">High</option></select></td>';
    str+='</tr>';
	   str+='</table>';
  str +='</div>';
 str+='</table>';
 str+='<div class="form-actions text-center">';
  str +='<input type="button" value="submit" class="btn btn-info" id="getNewsreport" onclick="getNews()"/>';
  str+='<img id="newsReportAjaxImg" src="images/search.jpg" style="display:none;"/>';
    str +='</div>';
  str +='<div id="locationWiseNewsDiv" class="divInfo" style="display:none;">';

  str +='</div>';

  str +='</div>';

  $("#newsReportDiv").html(str);
$("#fromDateId1").datepicker({ dateFormat: 'dd/mm/yy' });
$("#fromDateId1").datepicker("setDate", new Date());
$("#toDateId1").datepicker({ dateFormat: 'dd/mm/yy' });
$("#toDateId1").datepicker("setDate", new Date());

}
function getNews()
{

$("#newsReportAjaxImg").css({ 'display': 'inline-block' });
$("#locationWiseNewsDiv").css("display","none");
	var fromDate = $("#fromDateId1").val();
	var toDate = $("#toDateId1").val();
	var regionLevel = $("#regionlevel").val();
	var importance = $("#newsPriority").val();
    var jsObj = {
			task: 'getNews',
			fromDate:fromDate,
			toDate:toDate,
			regionLevel:regionLevel,
			importance:importance
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getAllNewsForAUserAction.action?"+rparam;
	callnewAjax(jsObj,url);
}


function saveNewsReport()
{


var newsreportfileDescription = $("#newsreportfileDescription").val();
var fileGallaryIds = [];
   $(".find-table").each(function() {
      
		  if($(this).is(":checked")){
		fileGallaryIds.push($(this).val());
	   }
    });
   var str = '<font color="red">';
   var flag =false;
	if(fileGallaryIds == "")
	{
		str +='Select atleast one file<br/>';
		flag =true;
	}
	
	 if(newsreportfileDescription == 0)
	{
		str +='description is required<br/>';
		flag = true;
	}
	
	newsReporterrorMessageDiv.innerHTML = str;
	 if(flag == true)
	{
		$('html, body').animate({
         scrollTop: $("#newsReporterrorMessageDiv").offset().top
     }, 2000);
		
	return;
	}
	else
	{
	$("#savenewsAjaxImg").css({'display': 'inline-block' });
	newsReporterrorMessageDiv.innerHTML = '';
    var jsObj = {
			fileGallaryIds:fileGallaryIds,
			description:newsreportfileDescription,
			task: 'saveNews',
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "saveNewsUserAction.action?"+rparam;
	callnewAjax(jsObj,url);
	}
}
/*function buildLocationWiseNews(results)
{
	var str='';
	var divEle = document.getElementById("locationWiseNewsDiv");
	$("#newsReportAjaxImg").css("display","none");
	if(results != null && results != '')
	{
		for(var i in results)
		{
			str+='<div id="mainDiv">';
			str+='<h4 id="mainheading">'+results[i].scope+' Wise </h4>';
			for(var j=0; j< results[i].fileVOList.length;j++)
			{
			
			str+='<h4 >'+ results[i].fileVOList[j].locationName+' </h4>';
			str+='<div style="border:1px solid #d3d3d3;">';
			str+='<table class="table">'
			str+='<tr>';
			
			str+='<th>check</th>';
			str+='<th>Source</th>';
			str+='<th>Language</th>';
			str+='<th>File Date</th>';
			str+='<th>Candidate Name</th>';
			str+='<th>Location Name</th>';
			str+='</tr>';
			
			for(var k=0;k<results[i].fileVOList[j].fileVOList.length;k++)
			{
				var source = results[i].fileVOList[j].fileVOList[k].source.trim();
		
				str+='<tr >';
				str+='<td><input type="checkbox" class="find-table" value='+results[i].fileVOList[j].fileVOList[k].contentId+'></td>';
				str+='<td colspan="5" >';
				if(source == "Eenadu Telugu")
				{
				str+="<span class='enadu fontStyle pagerRow' style='font-weight:bold;'><a href='javascript:{getNewsDetailsByContentId("+results[i].fileVOList[j].fileVOList[k].contentId+")}'>"+results[i].fileVOList[j].fileVOList[k].fileTitle1+"</a></span>"
				//str+=' <div class="pagerRow">'+results[i].fileVOList[j].fileVOList[k].fileTitle1+'</div>';
				}
				else
			{
			str+="<h4 style='text-transform: capitalize;color: #005580;'><a  href='javascript:{getNewsDetailsByContentId("+results[i].fileVOList[j].fileVOList[k].contentId+")}'>"+results[i].fileVOList[j].fileVOList[k].fileTitle1+"</a></h4>";
			}
			    str+='</td>';
				str+='</tr>';
				str+='<tr>';
				str+='<td></td>';
				str+='<td>';
				str+='<span>'+results[i].fileVOList[j].fileVOList[k].source+'</span>';
				str+='</td>';
				str+='<td>';
				str+='<span>'+results[i].fileVOList[j].fileVOList[k].language+' </span></td>';
				str+='<td>';
				str+='<span>'+results[i].fileVOList[j].fileVOList[k].fileDate+' </span></td>';
				str+='<td>';
				str+='<span>'+results[i].fileVOList[j].fileVOList[k].candidateName+' </span></td>';
				str+='<td>';
				str+='<span>'+results[i].fileVOList[j].fileVOList[k].locationName+' </span></td>';
				str+='</tr>';
			
			}
	
			str+='</table>'
			str+='</div>';
		}
			
	}
	str +='<br/><input type="button" value="submit" class="btn btn-info" id="savenewsReport" onclick="saveNewsReport()"/>';
	
	str+='<img style="display: none;" src="images/search.jpg" id="savenewsAjaxImg">';
	}
	divEle.innerHTML =str;
}*/
function showReportFileNewsStatus(result)
{
	$("#savenewsAjaxImg").css("display","none");
	if(result.resultCode == 0)
	{
	 $("#newsreportfileDescription").val('');
	 $("#newsReporterrorMessageDiv").html('Saved successfully..').css('color','green');
	}
	else
	{
  $("#newsReporterrorMessageDiv").html('Error Occured! Try Again..').css('color','red');
   }
 $('html, body').animate({
         scrollTop: $("#newsReporterrorMessageDiv").offset().top
     }, 2000);
		 return;
}

var data = {items:keywordsArray};


$(document).ready(function(){
	$(function(){
    $("#keywordListId1").autoSuggest(data.items, {selectedItemProp: "name", searchObjProps: "name"});


  });
});

function getUnassignedKeyWords()
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
  $("#profileManagementMainOuterDiv6").css("display","block");
  $("#profileManagementHeaderDiv6").css("display","none");
    $("#profileManagementMainOuterDiv7").css("display","none");
    var str ='';
	str+='<div class="container well">';

	str+='<div class="row clearfix">';
	str+='<legend class="text-center ">Map KeyWords To Gallaries</legend>';
	str+='<div id="keywordErrorMsgDivId" ></div>';
	str+='<div class="row-fluid"><div class="span6 well well-small " id="keywordsDiv">';
	str+=' </div>';
	str+='<div class="span6"> '; 
	str+='<div class="row-fluid">';
	str+='<div class="span12 well well-small ">';
    str+='<label><strong>Select Category Name</strong></label>';
    str+=' <select class="input-block-level"  id="keywordCategory"  onchange="getGallariesForSelectedCategory1(\'keywordCategory\')">';
	str+='</select>';
    str+='</div>';
	str+='</div></div>';
	str+='<div class="row-fluid">';
    str+='<div class="span12 well well-small " id="categorygallary" style="display:none;">';
    str+='<label><strong>List oF (Category) Gallarys</strong></label>';
	str+='</div>';
	str+='</div>';
    str+='</div>';
	str+='<div class="form-actions text-center">';
	str+=' <button type="submit " class="btn btn-success btn-large" onclick="updateGallaryKeyword();">Submit</button>';
	str+='<img style="display:none;" src="images/search.jpg" id="keywordAjaxImg">';
	str+='</div>';
	str+='</div>';

	str+='</div>';
	
		str+='<div class="container well">';
		str+='<div class="row clearfix">';
	str+='<legend class="text-center ">Update KeyWord</legend>';
	str+='<div id="keywordErrorMsgDivId1" ></div>';
    str+='<div class="row-fluid"><div class="span6 well well-small " id="gallaryMapedkeywordsDiv">';
	 str+='<label><strong>Select KeyWord</strong></label>';
     str+=' <select class="input-block-level" id="keywords">';
	 str+='</select>';
	str+=' </div>';
	str+='<div class="span6"> '; 
	str+='<div class="row-fluid">';
	str+='<div class="span12 well well-small ">';
     str+='<label><strong>Select Category Name</strong></label>';
     str+=' <select class="input-block-level" id="keywordCategory1" onchange="getGallaryId();getGallariesForSelectedCategory1(\'keywordCategory1\');">';
	 str+='</select>';
    str+='</div>';
	str+='</div></div>';
	str+='<div class="row-fluid">';
    str+='<div class="span12 well well-small " id="categorygallary1" style="display:none;">';
	
	str+='</div>';
	str+='</div>';
    str+='</div>';
	str+='<div class="form-actions text-center">';
	str+=' <button type="submit " class="btn btn-success btn-large" onclick="updateExistingKeyword();">Submit</button>';
	str+='<img style="display:none;" src="images/search.jpg" id="keywordAjaxImg1">';
	str+='</div>';
	str+='</div>';
	str+='</div>';
	document.getElementById("keyWordsMainDiv").innerHTML = str;
	getCategory();
	getKeyWords();
	getMappedKeyWords();
}

function getKeyWords()
{
	 var jsObj={
		
		task:'getKeywords'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getKeywordsAction.action?"+rparam;
	callAjax(jsObj, url);
	 
 
}
function getMappedKeyWords()
{
	 var jsObj={
		
		task:'getGallaryMapedKeywords'
	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getKeywordsAction.action?"+rparam;
	callAjax(jsObj, url);
	 
 
}

function getGallariesForSelectedCategory1(selEle)
{
 if(selEle == "keywordCategory")
  $("#categorygallary").css("display","block");
  if(selEle == "keywordCategory1")
  $("#categorygallary1").css("display","block");;
  var categoryId = $('#'+selEle+'').val();
 var jsObj=
	{
	    categoryId:categoryId,
		selEle : selEle,
		task  : "getGallariesInCategory1"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getGallariesByCategoryIdAction.action?"+rparam;						
	callAjax1(jsObj,url);
}

function updateGallaryKeyword()
{
var keywordsArr =[]; 
var gallariesArr = [];
$(".keywords").each(function() {
		 if($(this).is(":checked")){
		
		keywordsArr.push($(this).val());
	   }
    });
	$(".gallary").each(function() {
		 if($(this).is(":checked")){
		gallariesArr.push($(this).val());
	   }
    });
	var keywordCategory = $("#keywordCategory").val();
	 var flag =false;
	 var str='<font color="red">';
	var keywordErrorMsgDivId = document.getElementById('keywordErrorMsgDivId');
	
	if(keywordCategory == 0)
	{
	str +='Select Category<br/>';
		flag =true;
	}
	else if(gallariesArr == "")
	{
		str +='Select atleast one Gallary<br/>';
		flag =true;
	}
	else if(keywordsArr == "")
	{
		str +='Select atleast one keyWord<br/>';
		flag =true;
	}
	if(flag == true)
	{
	keywordErrorMsgDivId.innerHTML = str;
	return;
	}
	else
	keywordErrorMsgDivId.innerHTML = '';
	$("#keywordAjaxImg").css("display","inline-block");
		var jsObj = {
			gallariesArr:gallariesArr,
			keywordsArr:keywordsArr,
			task: 'updateGallaryKeyword',
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "updateGallaryKeywordAction.action?"+rparam;
	callnewAjax(jsObj,url);

}


</script>
</body>
</html>