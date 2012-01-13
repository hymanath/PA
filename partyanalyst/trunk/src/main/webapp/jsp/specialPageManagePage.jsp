<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<script type="text/javascript" src="js/specialPage/specialPage.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>

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
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	 
	<!-- YUI Skin Sam -->
<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
	
<!-- JQuery files (Start) -->

<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>

<style>
#problemHead
{
	
}
body {
	color:#000000;
	font-family:Arial,Helvetica,sans-serif;
	line-height:1.3em;
}
h2 {
	font-size:20px;
    font-weight:normal;
    margin-bottom:20px;
    margin-top:8px;
    padding:0;
}
hr {
	-moz-background-clip:border;
	-moz-background-inline-policy:continuous;
	-moz-background-origin:padding;
	background:transparent url(images/hr.jpg) repeat-x scroll 0 0;
	border:medium none;
	padding:5px;
}
p{
	font-size:14px;
	margin:0;
	line-height:1.3em;	
}
.bluetext {
	color:#3B5998;
	font-weight: bold;
}
h3 {
	font-size:15px;
	font-weight:normal;
	margin:0;
	padding:0 0 5px;
}
#description, #description p {
	color:#000000;
	font-family:Verdana,Arial,Helvetica,sans-serif;
	font-size:12px;
	margin:0;
}
.bluebar_heading {
	background-color:ThreeDDarkShadow;
	color:#FFFFFF;
	font-size:15px;
	font-weight:bold;
	padding:8px 5px;
}
.divInfo
{
 background-color:#FFFFFF;
 border-bottom: 1px solid #B3C1CE;
 border-left: 1px solid #B3C1CE;
 border-right: 1px solid #B3C1CE;
 padding:5px;
}
.accept
{
	background: url{images/accept.jpeg};
}

.reject
{
	background: url{images/reject.jpeg};
}

.imageButton {
	-moz-border-radius:5px 5px 5px 5px;
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
.buttonStyle {
	-moz-border-radius:5px 5px 5px 5px;
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

.imgFieldset
{
	-moz-border-radius: 4px 4px 4px 4px;
	border			: 4px solid #9F81F7;
    margin-bottom	: 10px;
	margin-top		: 5px;
}


.gallaryImg
{
	width  : 150px;
	height : 130px;
}
.tdWidth {           
  font-weight:bold;
  color:#4b74c6;
  width:161px;
}
.tdWidth1 {           
  color:#4B74C6;
  font-weight:bold;
  width:162px;
  text-align:left;
}
.tdWidth2 {           
  font-weight:bold;
  color:#4b74c6;
  width:164px;
} 
.selectWidth{
 width:175px;
}
.selectWidthPadd{
padding-left:0px;
 width:175px;
 text-align:left;
}
.paddingCss {
padding-left:49px;
}
.requiredFont {
color:red;
font-size:18px;
}
.imageStyle {
  vertical-align:top;
}

.canSelect
{
	width : 230px;
}
</style>

</head>
<body>
	<div id='profileManagementMainOuterDiv'>

	<div id='profileManagementHeaderDiv'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
			  <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg">
				</td>
				<td width="98%">
				   <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					 <span style="text-decoration: none;" class="headerLabelSpan2"><center>Special Page Management Admin</center></span>
				   </div>
				</td>
			   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
			   </td>
			</tr>
		</table>
	</div>
	 <!-- For Heading end -->
	 
	<div id='profileManagementMainInnerDiv' class="divInfo">
		<div id="profileManagementDiv">
			<table class="statusData_table" width="100%">	
			<tr>
				<td class="statusData_table_data" width="83%">
					<table>
					  <tr>
						<td style="padding-left:350px"><b>Select Special Page</b></td>
						 <td><input type="text" id ="specialPageId" name = "specialPage" value="1">
						 </td>
						 <span id="alertMsg1" style="padding-left:410px"></span>
					  </tr>
					</table>
				</td>
			</tr>
			  <tr>
				<td class="statusData_table_data" width="100%" style="padding-top:23px">
					<table>
					  <tr>
					    <td style="padding-left:30px"><b><input type="button" class="buttonStyle" value="Create New" onClick="buildCreateNewDiv()"></b></td>
						<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Photo Gallery" id="photoGalleryId" onClick=""></b></td>
						<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Video Gallery" id="videoGalleryId" onClick="showVideoGallary()"></b> </td>
						<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="News Gallery" id="newsGalleryId" onClick="showNewsGallaey()"></b> </td>
						<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Development Activity" id="developmentGalleryId" onClick=""></b> </td>
						<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Event Description" id="profileGalleryId" onClick="insertProfileDiscription()"></b> </td>
					 </tr>
					</table>
				</td>
			 </tr>
			</table>
		
	</div>
	</div>
	</div>

	<!-- for  body 1 start    result  -->
	<HR>
	<div id='profileManagementMainOuterDiv1' style="display:none">
		<div id='profileManagementHeaderDiv1'>
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
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
			</table>
		</div>

		<div id='photoGallaryDiv' class="divInfo">
		
		</div>

	</div>

	<!-- for  body 1 end    result  -->

	<!-- for  body 2 start    result  -->

	<div id='profileManagementMainOuterDiv2' style="display:none">
		<div id='profileManagementHeaderDiv2'>
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
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
			</table>
		</div>
	

		
	</div>
	
	<div id='videoGallaryDiv' class="divInfo">
	</div>

	<div id='createNewDiv' class="divInfo">
	</div>

	<!-- for  body 2 end    result  -->
	<div id='profileManagementMainOuterDiv3' style="display:none">
		<div id='profileManagementHeaderDiv3'>
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				  <tr>
					   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
					   </td>
					   <td width="98%">
						 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
						   <span style="text-decoration: none;" class="headerLabelSpan2">News Gallary</span>
						 </div>
					   </td>
					   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
					   </td>
				 </tr>
			</table>
				
		</div>
			
	</div>
	<div id='newsGallaryDiv' class="divInfo">
		 </div>

	<!-- for  body Discription start      -->

	<div id='profileManagementMainOuterDiv5' style="display:none">
		<div id='profileManagementHeaderDiv2'>
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				  <tr>
					   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
					   </td>
					   <td width="98%">
						 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
						   <span style="text-decoration: none;" class="headerLabelSpan2">Event Description</span>
						 </div>
					   </td>
					   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
					   </td>
				 </tr>
			</table>
		</div>

		
	</div>
		<div id='descriptionDiv' class="divInfo">
		
		</div>
	<div id='profileManagementMainOuterDiv6' style="display:none">
		<div id='profileManagementHeaderDiv2'>
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				  <tr>
					   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
					   </td>
					   <td width="98%">
						 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
						   <span style="text-decoration: none;" class="headerLabelSpan2">Update Event Gallary</span>
						 </div>
					   </td>
					   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
					   </td>
				 </tr>
			</table>
		</div>

		<div id='updateGallaryDiv' class="divInfo">

	  </div>
	 </div>
	  
</body>
</html>