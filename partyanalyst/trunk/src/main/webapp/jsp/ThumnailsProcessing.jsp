<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <title>ThumnailProcessing</title>
 
<style>



.mainDiv{
	margin-top:60px;
	padding:5px;
}
.selectDivStyle{
	font: bold 12px verdana;
	text-align:left;
	padding-left:20px;
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
    font-size: 30px;
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
    padding-left: 60px;
	
     color: 86A7EB;
     font-size: 20px;
    font-weight: bold;
}
</style>
</head>
<body>
<div style="background-color:#F5F5F5;width:100%;">

<fieldset class="f3" style="margin-left: 180px;">
  <legend class="l2"> create Thumnnails</legend>
  <div  id="RemoveVideos"  class="errStyle"></div>
  <div class="selectDivStyle">
  <table style="text-align:left;">
  <tr>
	     <div style="padding-left:80px;padding-top:5px;">
	   <input type="button" id="thumbSubmit" class="buttonStyle" value="Create Tumbnails" onclick="ajaxCallForThumnails()" /><span id="searchCand_ImgSpan" style="padding-left:10px;display:none;"><img src="images/icons/partypositions.gif" /></span></div>
	  
	   </tr>
	   </table>
</fieldset>

<fieldset class="f3" style="margin-left: 180px;">
 <!-- <legend class="l2"> Remove Expired Videos from YouTube</legend>
   
  -->
  <div  id="resultDiv"  class="errStyle"></div>
 
</fieldset>

</div>
<script type="text/javascript" language="JavaScript">


function ajaxCallForThumnails()
{
	disableButton('thumbSubmit');
	setTextToDiv();
	
	url="processThumbNailsAction.action";
	var callback = {
			 success : function(o){
			  //  alert(YAHOO.lang.JSON.parse(o.responseText));
			 try {
			 status = YAHOO.lang.JSON.parse(o.responseText);
				// alert(pathVOList);
				 enableButton('thumbSubmit');
				setResultToDiv(status);
				 
				 
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
function setTextToDiv()
{
	var str='';
    str +='<DIV>Please Wait..</DIV>';
	   str +='   <IMG src="images/icons/339.gif"/>';
    document.getElementById("resultDiv").innerHTML = str;   
	
		
}
function setResultToDiv(status)
{
    document.getElementById("resultDiv").innerHTML = status;   

	
		
}
function enableButton(id)
{
	document.getElementById(id).disabled  = false;
}

function disableButton(id)
{
	
	document.getElementById(id).disabled  = true;
}

</script>



</body>
</html>