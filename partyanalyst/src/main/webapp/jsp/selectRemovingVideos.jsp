<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RemoveYoutubeVideos</title>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
 <script  type="text/javascript"
	src="js/jQuery/jquery-ui.min.js">
 </script>
 <script type="text/javascript" src="js/calendar Component/calendarComponent.js"></script>
 <script type="text/javascript" src="js/problemManagement/problemManagement.js"></script>
 <script type="text/javascript" src="js/jQuery/js/jquery-1.4.2.min.js"></script>
 <link type="text/css" rel="stylesheet" href="styles/jQuery/datepicker/jquery-ui-1.8.14.custom.css" />
 <link type="text/css" rel="stylesheet" href="styles/jQuery/datepicker/demos.css" />
 <script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<style>
.tinyDateCal
{
position:absolute;
}
.mainDiv{
	margin-top:60px;
	padding:5px;
}
.selectDivStyle{
	font: bold 12px verdana;
	text-align:left;
	padding-left:125px;
	padding-top:20px;
}
.headingCss{
	font: bold 12px verdana;
	text-align:left;
	padding-left:120px;
}
.headingCss1{
	font: bold 12px verdana;
	text-align:left;
	padding-left:211px;
}
.heading{
	font: bold 14px verdana;
	color:blue;
	 padding-left: 113px;

}
.l2 {
color:navy;
font-size:12px;
font-weight:bold;
padding:5px;
}
.f3 {
-moz-border-radius:4px 4px 4px 4px;
border:2px solid #CFD6DF;
margin-bottom:10px;
padding:10px;
width:591px;
}
.buttonStyle {
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
}
.requiredFont {
    color: red;
    font-size: 12px;
}
.errStyle{
    padding-left: 102px;
}
</style>
</head>
<body>
<div style="background-color:#F5F5F5;width:100%;">

<fieldset class="f3" style="margin-left: 204px;">
  <legend class="l2"> Remove Expired Videos from YouTube</legend>
  <div  id="RemoveVideos"  class="errStyle"></div>
  <div class="selectDivStyle">
  <table style="text-align:left;">
  
	<tr>
	    <td class="headingCss">From Date<font class="requiredFont">*</font> :</td>
		<td>											
			<input type="text" style="width: 121px;"  READONLY="READONLY" name ="fromDate" id="fromDateTxt" size="15" value=""  />										
			<div class="yui-skin-sam"><div id="fromDate_Div" class="tinyDateCal"></div></div>										
		</td>										
		<td valign="top">										
			<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('fromDate_Div','fromDateTxt','9/2010')" ><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>										
		</td>
	 </tr>
	  <tr>
	    <td class="headingCss">To Date :</td>
		<td>										
			<input type="text" style="width: 121px;"  name ="toDate" id="toDateTxt"  READONLY="READONLY" size="15" value=""/>										
			<div class="yui-skin-sam"><div id="toDate_Div" class="tinyDateCal"></div></div>										
		</td>														
		<td valign="top">										
			<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('toDate_Div','toDateTxt','9/2010')"  )"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>										
		</td>
		<td>
		   <input type="button" onclick="clearDate()"  class="buttonStyle" style="padding: 1px 6px;"  value="Clear" />
		</td>
	 </tr>
      
	 
   </table>
  
       <div style="padding-left:200px;padding-top:5px;">
	   <input type="button"  class="buttonStyle" value="GetExpiredVideos" onclick="getVideoIdsToDispalyImages()" /><span id="searchCand_ImgSpan" style="padding-left:10px;display:none;"><img src="images/icons/partypositions.gif" /></span></div>
	   <div style="padding-left:70px;" class="yui-skin-sam" id="searchCandidate"></div>
	   <span id="assgCand_ImgSpan" style="padding-left:10px;display:none;"><img src="images/icons/partypositions.gif" /></span></div>
</fieldset>

<fieldset class="f3" style="margin-left: 204px;">
 <!-- <legend class="l2"> Remove Expired Videos from YouTube</legend>
   
  -->
  <div  id="dispalyExpiredVideos"  class="errStyle"></div>
</fieldset>

</div>



<script type="text/javascript">

function clearDate()
{  //alert("ok");
    document.getElementById("toDate").value="4";
    
}

function showErrorMessage(result,id,text)
{
   if(result.resultCode == 0)
   {
      document.getElementById(id).innerHTML ='<b><font style="color:green;font-size:12px;">'+text+'</font></b>';
   }
   else if(result.resultCode == 1 && result.exceptionMsg !=null && result.exceptionMsg.length >0)
   {
     document.getElementById(id).innerHTML ='<b><font style="color:red;font-size:12px;">'+result.exceptionMsg+'</font></b>';
   }
   else if(result.resultCode == 1 )
   {
     document.getElementById(id).innerHTML ='<b><font style="color:red;font-size:12px;">Error Ocuured, Try Again.</font></b>';
   }
}

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}
function createNewPosition(){
 document.getElementById("newPosErrMsg").innerHTML ="";
 var newPosition = document.getElementById("newPosition").value;
   newPosition = trim(newPosition);
   if(!(newPosition.length >0))
   {
     document.getElementById("newPosErrMsg").innerHTML = '<b><font style="color:red;font-size:12px;">Add Position Value Is Required</font></b>';
	 return;
   }
   else if(newPosition.length >50)
   {
     document.getElementById("newPosErrMsg").innerHTML = '<b><font style="color:red;font-size:12px;">Add Position Length Must Not Exceed 50 characters</font></b>';
	 return;
   }
 document.getElementById("electionTypeSelect_ImgSpan").style.display="block";
   var jObj = {
                time : new Date().getTime(),
				newPosition : newPosition,
				task : "addNewPosition"
			}
	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url= "updateElectionInformationAction.action?"+rparam;
	
	callAjaxForAdmin(url,jObj);
}
 function clearData(elmtId)
{
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>0;i--)
	{
		elmt.remove(i);
	}	
}
function  getVideoIdsToDispalyImages()

{  document.getElementById("dispalyExpiredVideos").innerHTML =""; 
var fromdate  = document.getElementById("fromDateTxt").value;
var todate  = document.getElementById("toDateTxt").value;
// alert(fromdate);
 //alert(todate);
   if(fromdate == null ||todate == null)
    return;
	displayLoadingMessage();
   var jObj=
				{ 
				       fromDate : fromdate,
					   endDate  : todate  ,
						task:"expiredVideos"
										
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
				var url = "removeVideosAction1.action?"+rparam;	
        				
		        callAjaxForMaintanance(url,jObj);



}
function displayLoadingMessage()
{ var str='';
           str +='<DIV>Please Wait..</DIV>';
		   str +='   <IMG src="images/icons/barloader.gif"/>';
	     document.getElementById("dispalyExpiredVideos").innerHTML = str;   

}
 function callAjaxForMaintanance(url,jObj){
	// alert("ok");
	 
	  var callback = {
	 success : function(o){
	  //  alert(YAHOO.lang.JSON.parse(o.responseText));
	 try {
	 pathVOList = YAHOO.lang.JSON.parse(o.responseText);
		// alert(pathVOList);
		 dispalyRemovingVideoImages(pathVOList);
     
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
  // alert("hello")
	YAHOO.util.Connect.asyncRequest('GET', url, callback);
 }
function  dispalyRemovingVideoImages(pathVOList)
	{ 
	// alert("hello");
	var str='';
	
	if(pathVOList !=null ){
	
      for(var i in pathVOList)
	     {
		//var option = document.createElement('option');
		//option.value=optionsList[i].id;
		
		try
		{ 
		   str +='<table>';
		   str +='    <tr>';
	       str +='      <td><input type="checkbox" id="myCheck" name="videoid" checked="checked" value= '+pathVOList[i]+'></td>';
		   str +='      <td><a rel="#voverlay" href="http://www.youtube.com/v/'+pathVOList[i]+'?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer"><img alt="Title 3" src="http://img.youtube.com/vi/pathVOList[i]/0.jpg"></a> </td>';
	       str +='   </tr>';
		   str +='</table>';
				}
		catch(ex)
		{
			
		}
	 }//for
          str+= '<div style="padding-left:200px;padding-top:5px;"><input type="button"  class="buttonStyle" value="Delete Videos" onclick="ajaxCallToDeleteVideos()" /><span id="searchCand_ImgSpan" style="padding-left:10px;display:none;"><img src="images/icons/partypositions.gif" /></span></div>';
		  document.getElementById("dispalyExpiredVideos").innerHTML = str; 
	 }//if
	 else{
	str+= ' <legend class="l2"> NO videos Found</legend>';
	 document.getElementById("dispalyExpiredVideos").innerHTML = str; 
	 }
	 
	
 }
	
function ajaxCallToDeleteVideos(){   
     	//get all selectbox values
		//alert("ok");
		var selected = new Array(); 
	   $("input:checkbox[name=videoid]:checked").each(function()
             {
          // add $(this).val() to your array
           selected.push($(this).val());
                   });
                //  alert(selected);
				   //convert array to json obj
				   var jObj=
				{ 
				       filePaths : selected,
					    task:"deleteVideos"
														
				};
			
				var rparam ="task1="+YAHOO.lang.JSON.stringify(jObj);
				var url = "deleteVideosAction.action?"+rparam;						
		        callAjaxForMaintanance1(url,jObj);
 
 }
 function callAjaxForMaintanance1(url,jObj){
	
  var callback = {
	 success : function(o){
	 try {
	 myResults = YAHOO.lang.JSON.parse(o.responseText);
	 document.getElementById("dispalyExpiredVideos").innerHTML ="";
	
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

//toworkwith calendar


</script>

</body>
</html>