<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/> -->
	<title> TDP News Portal </title>
 <META http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="css/style.css">
	<!-------PT-sans font---->
	<link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
	<!--<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>-->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.carousel.js"></script>
	
<LINK rel="stylesheet" type="text/css" href="styles/addNewProblem/addNewProblem.css">
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
 <link href="calendar.css" rel="stylesheet" type="text/css">


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

	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">

	<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script> -->

	<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />

	<script src="js/partyWiseNewsDisplay.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
<style type="text/css">
@font-face{ font-family: 'eFont'; src: url('fonts/eenadu.eot');}
@font-face {
    font-family: "eFont";
    font-style: normal;
    font-weight: normal;
    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
}

.currentTab1
{
 background-color:yellowgreen;
 border-radius:8px;
}
.enadu
{
font-family: eFont;
font-size:20px;
}
select {
background-color: #FFFFFF;
border: 1px solid #F3E81E;
width: 130px;
}
#requiredValue{
color:red;
font-size:large;
}	
.main-mbg {
background-color: #06ABEA;
color: #FFFFFF;
font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
height: 35px;
padding-left: 13px;
text-align: left;
text-transform: uppercase;
width: 850px;
border-radius:3px;
}
.tableCls{ margin-left: auto; margin-right: auto; float: none;}
.popupcontainer {
	background-color: #FFFFFF;
    box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
    margin: 9px auto 10px;
    max-width: 780px;
    padding: 10px;margin-top: 12px; float: none;
    margin-left: auto;
    margin-right: auto;
}
.ui-widget-header {
	border:0px;
	color:none;
	font-weight:bold;
	}
.imageButton{
	
	-moz-border-radius: 4px 4px 4px 4px;
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
}


  #buildSources, #buildNewSources {
    border: 1px solid #D3D3D3;
    margin: 10px auto 20px;
    width: 618px;
}
#buildVideoNewSources {
    border: 1px solid #D3D3D3;
    margin: 10px auto 20px;
    width: 500px;
}
.newssources {
    background-color: #97DFEB;
    border-radius: 5px 5px 5px 5px;
    margin-left: 5px;
    padding: 8px;
}
.newsParts {
    color: #FF4500;
}
#showContentDiv{z-index: 111;}
.tdWidth1{
width: 92px;
}

#candidatesListId,#candidateCategoryId,#categoryGallarySelect{width:190px;}

#existingFromText, #existingToText {
    width: 90px;
	cursor: text;
}
#byAllRadio{margin-right: 4px;margin-top: 0;}
#byDateRadio{margin-left: 10px; margin-right: 4px;margin-top: 0;}
#candidateNewsShowHideDatesDiv{margin-top: 10px;}
#cadidateRadioDiv{text-align: center; margin-top: 6px;}
#gallaryRadioId{ margin-left: 5px;
    margin-right: 4px;
    margin-top: 0;}
.errorDiv{color:red;}
#gallaryShowHideDiv{margin-left: 2px;}
#gallaryId{width: 221px;}
#categoryCheckBoxId{margin-top: 0px;margin-right: 6px;}
#categoryCheckBoxDiv{margin-top: 8px;}
#categoryGallary{margin: 0px 5px 0px 0px;}

.nav > li > a:hover {
    -moz-text-blink: none;
    -moz-text-decoration-color: -moz-use-text-color;
    -moz-text-decoration-line: none;
    -moz-text-decoration-style: solid;
    background-color: #EEEEEE;
}
</style>
<script>
$(document).ready(function(){

	$('#homeTabId').addClass('menuActive');
});
</script>
</head>
<body>
		
		<!----Container---->
		<div class="container">
			<div class="row m_top10">
				<div class="span6">
				<!----State waise news----->
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>State News</h4></div>
						
						<!--<div class="span4">
							<ul class="nav nav-tabs pull-right" id="myTab">
								<li class="active"><a data-toggle="tab" href="#News">News</a></li>
								<li class=""><a data-toggle="tab" href="#Video">Video</a></li>
							</ul>
						</div>-->
						<div class="span12">
							<div class="tab-content" id="myTabContent" >
								<!-----------NEWS tab--------->
							 <div id="News" class="tab-pane fade active in">
								<div class="carousel slide center" id="myCarousel">
										<ol class="carousel-indicators">
										  <li data-slide-to="0" data-target="#myCarousel" class="active"></li>
										  <li data-slide-to="1" data-target="#myCarousel" class=""></li>
										  <li data-slide-to="2" data-target="#myCarousel" class=""></li> 
										  <li data-slide-to="3" data-target="#myCarousel" class=""></li>
										</ol>
										<div class="carousel-inner">
										<s:if test="resultMap != null && resultMap.size() > 0"> 
										
							    <s:iterator value="resultMap.NewsGallary" var="newsGallaryDetails" status="ctr">
						       
                             <div ${ctr.first ? 'class="item active"' : 'class="item"'} style="top: 10px;">
                          
						  
						<s:if test="%{#newsGallaryDetails.source.equalsIgnoreCase('Eenadu Telugu')}"> 
							<span class="enadu"><a style="color: #005580;font-weight: bolder;" href='javascript:{}' onclick="getNewsDetailsByContentId(<s:property value='contentId'/>)"> <s:property value="fileTitle1"/> </a></span>
						 </s:if>
						 <s:else>
							<h4 style="text-transform: capitalize;"> <a style="color: #005580;font-weight: bolder;" href='javascript:{}' onclick="getNewsDetailsByContentId(<s:property value='contentId'/>)"><s:property value="fileTitle1"/> </a></h4>
                           </s:else>
						 
									<div class="row-fluid">				
									
									<s:if test="%{#newsGallaryDetails.fileType == 'Party'}" >
										 <c:if test="${newsGallaryDetails.displayImagePath != null}">
											<!-- <a class="thumbnail span4" style="height:120px;" href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img style="float:left;width:150px;height:110px;" src="${newsGallaryDetails.displayImagePath}" alt='<s:property value="fileTitle1"/> Image'/></a> -->

											<a class="thumbnail span4" style="height:120px;" href='javascript:{}'  onclick="getNewsDetailsByContentId(<s:property value='contentId'/>)"><img style="float:left;width:150px;height:110px;" src="${newsGallaryDetails.displayImagePath}" onerror="imgError(this);" /></a>
										</c:if>

										<c:if test="${newsGallaryDetails.displayImagePath == null}">
											<a class="thumbnail span4" style="height:120px;"href='javascript:{}' onclick="getNewsDetailsByContentId(<s:property value='contentId'/>)"> <img style="float:left;width:150px;height:110px;" src="./images/party_flags/${newsGallaryDetails.imagePathInUpperCase}" onerror="imgError(this);"/></a>
										</c:if>
										<s:if test="%{#newsGallaryDetails.source.equalsIgnoreCase('Eenadu Telugu')}"> 
                                       <p class="span8 enadu"><s:property value="description"/></p>
						               </s:if>
						               <s:else>
										<p class="span8"><s:property value="description"/></p>
										</s:else>

									    </div>
										<div class="row-fluid m_top10">
													<div class="span9" style="width:550px;">
													<table><tr><td style="width:200px;font-weight:bold;"><p class="text-error" >Source : <span style="font-weight:normal;color:black;"> ${newsGallaryDetails.source}</span></p></td><td style="font-weight:bold;"><p class="text-error" >Date : <span style="font-weight:normal;color:black;">${newsGallaryDetails.fileDate}</span></p></td>
													<s:if test="#newsGallaryDetails.responseCount >0">
													<td style="font-weight:bold;padding-left: 20px;"><p class="text-error" >Response Count : <span style="font-weight:normal;color:black;">${newsGallaryDetails.responseCount} </span></p></td>
													</s:if>									
													</tr></table>
													</div></br>
													<div class="span2 " style="float:right"><br>
														<a href="newsPaginationAction.action?level=state">
															<button class="btn btn-mini pull-right" type="button"  style="margin-top: -20px; margin-bottom: 15px;margin-right: 25px;">More...</button>
														</a>
													</div>
												</div>
									</s:if>
									
									</div>
									
							
							</s:iterator>
							</s:if>
										
											</div>
										</div>
										<!---<a data-slide="prev" href="#myCarousel" class="left ">‹</a>
										<a data-slide="next" href="#myCarousel" class="right">›</a>--->
									</div>
									
								
							<!------ News tab end ---->
							<!------ Video tab --------->
								<div id="Video" class="tab-pane fade span11">
									<!----------------------->
									<div class="carousel slide center" id="myCarousel">
										<!----<ol class="carousel-indicators">
										  <li data-slide-to="0" data-target="#myCarousel" class="active"></li>
										  <li data-slide-to="1" data-target="#myCarousel" class=""></li>
										  <li data-slide-to="2" data-target="#myCarousel" class=""></li> 
										  <li data-slide-to="3" data-target="#myCarousel" class=""></li>
										</ol>--->
										<div class="carousel-inner ">
											<!-------------------------->
										  <div class="item active">
											<div class="row-fluid">
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
											</div>
										  </div>
										   <div class="item">
											<div class="row-fluid">
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
											</div>
										  </div>
											<!-------------------------->
										  <a data-slide="prev" href="#myCarousel" class="left "><</a> | 
										  <a data-slide="next" href="#myCarousel" class="right">></a>
										  
										</div>
										<!---<a data-slide="prev" href="#myCarousel" class="left ">‹</a>
										<a data-slide="next" href="#myCarousel" class="right">›</a>---->
									</div>
					<!----------------------->
								</div>
							</div>
							<!------- Video tab end ------>
						</div>
					</div>
			<!----State waise news end----->
			<!----District waise news ----->
			<div class="row-fluid widget m_top10">
						<div class="span12 boxHeading"><h4>District level </h4></div>
						
						<!--<div class="span4">
							<ul class="nav nav-tabs pull-right" id="myTab">
								<li class="active"><a data-toggle="tab" href="#News">News</a></li>
								<li class=""><a data-toggle="tab" href="#Video">Video</a></li>
							</ul>
						</div>-->
						<div class="span12">
							<div class="tab-content" id="myTabContent" >
								<!-----------NEWS tab--------->
								<div id="News" class="tab-pane fade active in">
								<div class="carousel slide center" id="myCarousel2">
										<ol class="carousel-indicators">
										  <li data-slide-to="0" data-target="#myCarousel2" class="active"></li>
										  <li data-slide-to="1" data-target="#myCarousel2" class=""></li>
										  <li data-slide-to="2" data-target="#myCarousel2" class=""></li> 
										  <li data-slide-to="3" data-target="#myCarousel2" class=""></li>
										</ol>
										<div class="carousel-inner">
										 <s:if test="resultMap != null && resultMap.size() > 0"> 
										
							    <s:iterator value="resultMap.NewsGallaryForDist" var="newsGallaryDetails" status="ctr">
						       
                             <div ${ctr.first ? 'class="item active"' : 'class="item"'} style="top: 10px;">
                          
						  
						  <s:if test="%{#newsGallaryDetails.source.equalsIgnoreCase('Eenadu Telugu')}"> 
							<span class="enadu"><a style="color: #005580;font-weight: bolder;" href='javascript:{}' onclick="getNewsDetailsByContentId(<s:property value='contentId'/>)"> <s:property value="fileTitle1"/> </a></span>						 </s:if>
						 <s:else>
							<h4 style="text-transform: capitalize;"><a  style="color: #005580;font-weight: bolder;" href='javascript:{}' onclick="getNewsDetailsByContentId(<s:property value='contentId'/>)"> <s:property value="fileTitle1"/> </a></h4>
						 </s:else>
									<div class="row-fluid">				
									
									<s:if test="%{#newsGallaryDetails.fileType == 'Party'}" >
										 <c:if test="${newsGallaryDetails.displayImagePath != null}">
											<a class="thumbnail span4" style="height:120px;" href='javascript:{}' onclick="getNewsDetailsByContentId(<s:property value='contentId'/>)"><img style="float:left;width:150px;height:110px;" src="${newsGallaryDetails.displayImagePath}" onerror="imgError(this);" /></a>
										</c:if>

										<c:if test="${newsGallaryDetails.displayImagePath == null}">
											<a class="thumbnail span4" style="height:120px;" href='javascript:{}' onclick="getNewsDetailsByContentId(<s:property value='contentId'/>)"> <img style="float:left;width:150px;height:110px;" src="./images/party_flags/${newsGallaryDetails.imagePathInUpperCase}" onerror="imgError(this);"/></a>
										</c:if>

										 <s:if test="%{#newsGallaryDetails.source.equalsIgnoreCase('Eenadu Telugu')}"> 
										   <p class="span8 enadu"><s:property value="description"/></p>
										 </s:if>
										  <s:else>
											<p class="span8"><s:property value="description"/></p>
										 </s:else>
									    </div>
										<div class="row-fluid m_top10">
													<div class="span9" style="width:550px;">
													<table><tr><td style="width:200px;font-weight:bold;"><p class="text-error" >Source : <span style="font-weight:normal;color:black;"> ${newsGallaryDetails.source}</span></p></td><td style="font-weight:bold;"><p class="text-error" >Date : <span style="font-weight:normal;color:black;">${newsGallaryDetails.fileDate}</span></p></td>
													<s:if test="#newsGallaryDetails.responseCount >0">
													<td style="font-weight:bold;padding-left: 20px;"><p class="text-error" >Response Count : <span style="font-weight:normal;color:black;">${newsGallaryDetails.responseCount} </span></p></td>
													</s:if>
													</tr></table>
													</div></br>
													<div class="span2 " style="float:right"><br>
														<a href="newsPaginationAction.action?level=district">
															<button class="btn btn-mini pull-right" type="button"  style="margin-top: -20px; margin-bottom: 15px;margin-right: 25px;">More...</button>
														</a>
													</div>
												</div>
									</s:if>
									
									</div>
									
							
							</s:iterator>
							</s:if>
										
										</div>
										<!---<a data-slide="prev" href="#myCarousel" class="left ">‹</a>
										<a data-slide="next" href="#myCarousel" class="right">›</a>--->
									</div>
									
								</div>
							<!------ News tab end ---->
							<!------ Video tab --------->
								<div id="Video" class="tab-pane fade span11">
									<!----------------------->
									<div class="carousel slide center" id="myCarousel">
										<!----<ol class="carousel-indicators">
										  <li data-slide-to="0" data-target="#myCarousel" class="active"></li>
										  <li data-slide-to="1" data-target="#myCarousel" class=""></li>
										  <li data-slide-to="2" data-target="#myCarousel" class=""></li> 
										  <li data-slide-to="3" data-target="#myCarousel" class=""></li>
										</ol>--->
										<div class="carousel-inner ">
											<!-------------------------->
										  <div class="item active">
											<div class="row-fluid">
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
											</div>
										  </div>
										   <div class="item">
											<div class="row-fluid">
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
												<a class="thumbnail span4" href="#">
													<img src="http://placehold.it/200x150">
												</a>
											</div>
										  </div>
											<!-------------------------->
										  <a data-slide="prev" href="#myCarousel" class="left "><</a> | 
										  <a data-slide="next" href="#myCarousel" class="right">></a>
										  
										</div>
										<!---<a data-slide="prev" href="#myCarousel" class="left ">‹</a>
										<a data-slide="next" href="#myCarousel" class="right">›</a>---->
									</div>
					<!----------------------->
								</div>
							</div>
							<!------- Video tab end ------>
						</div>
					</div>
			<!----District waise news end----->
			
				</div>
				<!---View your Constituency News Div--->
				<div class="span3" style="height:555px">
					<div class="row-fluid widget acco">
						<!--<div class="span12 boxHeading"><h4 id="headingDiiv">Get Report</h4></div>
						<div class="span12">
						<div id="generateReportDiv"><a href="candidateNewsDetailsAction.action?fromDate=&toDate=&requestFor=candidate"><span class="btn btn-info">Generate Candidate Report</span></a></div>
						</div>-->
						<!--Created By sasi for Candidate News-->
						<h4 id="" style="text-align:center;font-weight:bold;background:#F9F9F9;color:#337DEB;">View  Candidate News</h4>
						<div class="">
						
						<div id="cadidateRadioDiv">
						<div style="clear:both;">
						 <input type="radio" id="byAllRadio" value="byAll" name="candidateNewsRadio" class="candidateRadioCls" checked="true"/>All
						 <input type="radio" id="byDateRadio" value="byDate" name="candidateNewsRadio" class="candidateRadioCls"/>By Date<br>
						 <!-- <input type="radio" value="gallaryRadio" id="gallaryRadioId" class="gallaryCategoryRadio" onclick="getCandidateGallaries()"/>By Gallary -->
						</div>
						
						<div style="clear:both;width:232px;margin-left:-36px;">
							<input type="checkbox" value="gallaryRadio" id="gallaryRadioId" class="gallaryCategoryRadio"/>By Gallery
						
							<input type="checkbox" value="categoryCheckBox" id="categoryCheckBoxId" class="categoryCheckBoxCls" style="margin-left:18px;" />By Category
						 </div>
						 </div>
						
						<div id="candidateNewsShowHideDatesDiv" style="display:none;width:220px;margin-left:-24px;">
						 <input type="text" size="20" name="fileDate" readonly="true" class="dateField pull-left" id="existingFromText" placeholder="From"/>
						 <input type="text" size="20" name="fileDate" readonly="true" class="dateField pull-right" id="existingToText" placeholder="To"/>
						</div>

						<table style="margin-top:15px;">
							<tr id="tableRowS">
								<td id="tdWidth">
									<s:select theme="simple" label="Candidates" name="candidates" id="candidatesListId" list="candidatesList" listKey="id" listValue="name"  headerKey="0" headerValue="Select Candidate"/> 
								</td>
							</tr>
						</table>

						<div id="gallaryShowHideDiv" style="display:none;">
						  <select id="gallaryId" multiple="multiple" style="width:190px;"></select>
						</div>
						 
						 <div id="categoryShowHideDiv" style="display:none;">
						   <select id="candidateCategoryId" multiple="multiple"></select><br>

						     <input type="checkbox" value="categoryGallaries" id="categoryGallary"/>Gallery 

						 </div>
						<div id="categoryGallaryShowHideDiv" style="display:none;">
						 <select id="categoryGallarySelect" multiple="multiple"></select>
						</div>

						<button id="sendButton" class="btn btn-warning btn-mini" onclick="getCandidatesNews()" style="margin-bottom: 15px; margin-left: 35px;font-weight:bold;" > View News</button> 
						<div class="errorDiv"></div>
						</div>

						
						
						<!--DIV for Constituency Select Filter-->
						<h4 id="headingDiiv" style="text-align:center;font-weight:bold;background:#F9F9F9;color:#337DEB;">View  Constituency News</h4>
						<div class="">
						<div id="errorMsgDiv"></div>
						<table style="margin-top:15px;">
							<tr id="tableRowS">
								<!--<td id="tdWidth" style="padding-right: 32px;">
								<!--	Location:<font id="requiredValue" class="requiredFont">*</font>
								</td>-->
								<td>
									 <!--<select id="listValue" onchange="populateLocations(this.options[this.selectedIndex].value)"> -->

									<select id="listValue" onchange="getLocationList()">
									<option value="0"> Select Location </option>
									<option value="District"> District </option>
									<option value="Constituency" selected="selected"> Constituency </option>
									<!--<option value="Mandal"> Mandal / Muncipality </option>
									<option value="Panchayat"> Panchayath / Ward </option>
									<option value="Booth"> Booth </option> -->
									</select>
								</td>
							</tr>
						</table>
						<div id="showScopeSubsD" style="margin-bottom: 10px;" style="margin-top:15px;"></div>
						<table  id="showScopeSubsC" style="margin-left:-20px;">	
								<tr>
								<td><select id="userAccessConstituencyList" class="selectWidth" name="userAccessConstituencyList" onchange="addCssStyle();" style="margin-left:20px;" ><!-- onchange="getMandalList(this.options[this.selectedIndex].value);">-->
								</select></td>	 
							</tr>
						</table>
						
						<button id="sendButton" class="btn btn-warning btn-mini" onclick="addCssStyle(),handleSubmit()" style="margin-bottom: 15px; margin-left: 35px;font-weight:bold;" > View News</button> 
						</div>
						
					</div>
					
					
					
				</div>
				<!-----View your Constituency News End------>
				<!-----Top view News DIv------>
				<div class="span3" style="height:555px">
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>Hot News</h4></div>
						<div class="span12">
							<ul class=" nav nav-list bs-docs-sidenav" style='margin-top:10px;'>
								<s:iterator value="responseFilesList" var ="responsefiles" status="status">
								<s:if test="%{#status.index < 4}">
								<li class='thumbnail' style='margin: 3px 5px 5px;padding:5px 5px 5px 15px;'>
								<s:if test="%{#responsefiles.source.equalsIgnoreCase('Eenadu Telugu')}">
									<span class='text-info enadu' onclick="getNewsDetailsByContentId(<s:property value='fileGallaryId'/>)" style="cursor:pointer;font-weight:bold;"> <s:property value="title"/></span>
									</s:if>
									<s:else>
									<h6 class='text-info' onclick="getNewsDetailsByContentId(<s:property value='fileGallaryId'/>)" style="cursor:pointer; text-transform: capitalize;"> <s:property value="title"/></h6>
									</s:else>
									<span style='color:#ccc;'>
										<s:if test="%{fileDate!=''}">
										<div>
											<i class="smal" style="color:#3A87AD;">Date: 
											<s:property value="fileDate"/></i>
										</div>
										</s:if>
									</span>
								</li>
								<a href="javascript:{getResponseDetailsByContentId(<s:property value='fileGallaryId'/>)}" class="btn btn-mini btn-primary" style="margin-left:154px;background: none repeat scroll 0% 0% #FAA938;font-weight:bold;margin-top: -2px;">Track</a>
								 </s:if> 
								</s:iterator>
								
							</ul>
							
						</div>
							<br><br>
						<div  class="span12" style="margin-bottom:0px;"><button id="newsresponseMore" class="btn btn-mini" style="margin-top: 15px; margin-left: 175px;" onclick="getCandidatesResponseNews()"> More...</button></div>
						<br><br>
					</div>
				</div>
			<!----- Top view News DIv End ------>
			</div>
		<!------------- Row-2 --------->
				<div class="row m_top10">
					<div class="span4">
						<div class="row-fluid widget">
							<div class="span12 boxHeading"><h4>Latest News</h4></div>
							<div class="span12">
								<ul class="unstyled pad10">
									
									<c:if test="${fileVOsList != null}">
									 <s:iterator value="fileVOsList" var="newDetails">
										<li>
										<s:if test="%{#newDetails.source.equalsIgnoreCase('Eenadu Telugu')}"> 
										<a href="javascript:{}" onclick="getNewsDetailsByContentId(<s:property value='contentId'/>)" class="enadu muted" style='font-weight:bold;text-transform: capitalize;'><i class="icon-share-alt "></i>${newDetails.fileTitle1}</a>
									</s:if>
									<s:else>
										<a href="javascript:{}" onclick="getNewsDetailsByContentId(<s:property value='contentId'/>)" class="muted" style='text-transform: capitalize;'><i class="icon-share-alt"></i>${newDetails.fileTitle1}</a>
									</s:else>
									</li>
									 </s:iterator>
									</c:if>

									<a class=" btn btn-mini pull-right" href="newsDetailsAction.action" style="margin-bottom: 8px;">More...</a>
								</ul>
							</div>
						</div>
					</div>
					<!-------->
					<div class="span4">
						<div class="row-fluid widget">
							<div class="span12 boxHeading"><h4>Category Wise News</h4></div>
							<div class="span12">
								<ul class="unstyled pad10">
										<s:if test="resultMap != null && resultMap.size() > 0"> 
										
							    <s:iterator value="resultMap.categories" var="newsGallaryDetails" status="ctr">
						       	<li><a href='javascript:{showAllgallaries1(<s:property value="candidateId"/>,<s:property value="categoryId"/>)}' class="muted"><i class="icon-share-alt"></i> <s:property value="categoryName"/></a></li>                          		
							</s:iterator>
							</s:if>
							
								</ul>
								<a href="javascript:{showAllCategories()}" class=" btn btn-mini pull-right " style="margin-top: -10px;">More...</a>
							</div>
						</div>
					</div>
					<div class="span4">
						<div class="row-fluid widget">
							<div class="span12 boxHeading"><h4>Galleries</h4></div>
							<div class="span12">
								<ul class="unstyled pad10">
								<c:forEach var="gallary" items="${latestGallariesList}">
								<li><a class="muted" href="javascript:{showFilesInGallary(${gallary.id})}"><i class="icon-share-alt"></i>${gallary.name}</a></li>        
                               </c:forEach>
							   <a href="javascript:{showAllgallaries()}" class=" btn btn-mini pull-right " style="margin-top: -10px;">More...</a>
									
								</ul>
							</div>
						</div>
					</div>
				</div>
			<!-------- Row-2 end -------------->
			<!------------- Row-3 --------->
				<div class="row m_top10">
					<div class="span12">
						<div class="row-fluid widget">
							<div class="span12 boxHeading"><h4>Latest Videos</h4></div>
								<div class="row-fluid ">
									<div class="span12 pad10 ">
										<ul class="unstyled">
										<s:iterator value="fileList" var="newsGallaryDetails" status="ctr">
											<li><a class="thumbnail span2" style="width: 144px;" href="javascript:{openVideo('<s:property value="filePath1"/>')}">
											<img style="width: 140px; height: 100px;" src='http://img.youtube.com/vi/<s:property value="filePath1"/>/0.jpg' title='<s:property value="description"/>'/></a>
											</li>
										</s:iterator>
										
									<!--
									<li><a href="javascript:{}" onclick="openVideo('EUW4YcbUumk');" class="thumbnail span2"><img  src="http://img.youtube.com/vi/EUW4YcbUumk/0.jpg"></a></li>
								 -->
										</ul>
										<!--<a href="#" class="pull-right btn btn-mini" style="margin-top:75px;">More</a>-->
										<button class="btn btn-mini pull-right" style="margin-top: 75px; margin-left: 15px;" onclick="window.location='showMoreVideos.action'">More...</button>
									</div>
								</div>
						</div>
					</div>
				</div>
				<div id="video_dialog">
					<div id="videos"></div>
					<!--<span class="btn" onclick="showMoreVideoGallaries()">More Videos</span>-->
				</div>
			<!-------- Row-3 end -------------->
		<div id="showContentDiv">
	 <div id="contentAjaxCallImg" style="display:none;"><img src="images/icons/goldAjaxLoad.gif"></div>
	 <div id="showContentDivInnerDiv"></div>
	</div>		
	</div>
		<!------JS------>
	<!--<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>-->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.carousel.js"></script>
	
<script>
$(function(){
$('#myCarousel').carousel({ interval: 4000});
$('#myCarousel2').carousel( {interval: 5000});

	$('.acco').accordion({
		autoHeight: false,
		clearStyle: true
	});
});


var responseFilesList = [];
<c:forEach var="status"  items="${responseFilesList}" >
	var ob={
				fileGallaryId:'${status.fileGallaryId}',
				title:"${status.title}",
				fileDate:'${status.fileDate}'
			};
	responseFilesList.push(ob);	
</c:forEach>

$('#listValue').val('Constituency');

/* $( document ).ready(function() {
	$('.enadu1').each(function()
	{
		$(this).addClass('enadu');
	});
	$('#enadu1').addClass('enadu');
 }); */
 /* updated by srishailam
function showMoreVideoGallaries(){
	 var urlstr = "showMoreVideos.action";
		
     var browser1 = window.open(urlstr,"showMoreVideos","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
}
*/
//getAllConstituenciesInStateByType(2, 1, 'constituency');
getCandidates();

function showFilesInGallary(gallaryId)
{
 var urlstr = "showAllFilesOfAGallary.action?gallaryId="+gallaryId+"&category=0&requestFor=null";
		
     var browser1 = window.open(urlstr,"showAllFilesOfAGallary","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
}

function showAllgallaries(){
	   var urlstr = "showNewsGallariesAction.action?candidateId=0&category=0";
		
     var browser1 = window.open(urlstr,"subRegionsWiseAnalysis","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
	}
function showAllgallaries1(partyId,catId){
	   var urlstr = "showNewsGallariesAction.action?candidateId="+partyId+"&category="+catId;
		
     var browser1 = window.open(urlstr,"subRegionsWiseAnalysis","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
	}
function showVideoGallery(fileId)
{
	

task="getVideoGalley"; 		
 var jsObj=
	 {
		task:task		
	 };
var url = "getvideoAGallary.action?&fileId="+fileId+"&task="+task;	
callHomePageAjax11(jsObj,url);


}

function openVideo(path)
	{
		$("#video_dialog").dialog({
				resizable:false,
				title:'videos',
				height: 500,
				width:580,
				top:250,
				left:100,
				modal: true
				
	});
		var str = "";
		str += '<iframe width="500" height="396" src="http://www.youtube.com/embed/'+path+'" frameborder="0" allowfullscreen="true"></iframe></div>';
		$('#videos').html(str);
	} 
	

function buildVideoGallery(myResults)
{
	$("#video_dialog").dialog({
				resizable:false,
				title:'Videos',
				height: 500,
				width:580,
				top:250,
				left:100,
				modal: true
				
	});
	var str = '';
	for(var i in myResults)
	{
		str += '<iframe  src="http://www.youtube.com/embed/'+myResults[i].filePath1+'" frameborder="0" allowfullscreen="true" class="thumbnail span2">></iframe>';
	}
	
	$('#videos').html(str);
}

function callHomePageAjax11(jsObj,url){
	 
	var callback = {			
				   success : function( o ) {
					   
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == "getVideoGalley")
							{	
								buildVideoGallery(myResults);
								
							}	
							
						}catch(e)
						{   

							//alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET',url, callback);



}
/* function getLatestNewsDetails(contentId)
	{
	var urlstr = "newsDetailsAction.action?contentId="+contentId+"&";
	
    var browser1 = window.open(urlstr,"subRegionsWiseAnalysis","scrollbars=yes,height=600,width=1050,left=200,top=200");	
    browser1.focus();
	} 
	function getLatestNewsDetails(contentId)
	{
		
	  var jObj=
	  {
			startIndex:0,
		    maxIndex:5,
		    contentId:contentId,
	        task:"getLatestNews"

	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "latestUpdatedNewsAction.action?"+rparam;
	callAjax(jObj,url);
	}*/

	function getLatestNewsDetails(contentId)
   {
	
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
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
									if(jsObj.task == "getSelectedContent")
								{
										buildLatestNews(myResults,jsObj);
										
								}
								if(jsObj.task =="getMoreVideos")
								{
									//alert('s');
								}
								if(jsObj.task =="getCandidateNamesInHomePage")
								{
									//alert('s');
								}
								if(jsObj.task =="getCandidatesNewsInHomePage")
								{
									//alert('s');
								}
								else if(jsObj.task == "getCandidateRelatedGallaries")
								{
								 clearOptionsListForSelectElmtId('gallaryId');
								 createOptionsForSelectElmtId('gallaryId',myResults);
			  
								}
								else if(jsObj.task == "getCandidateRelatedCategories")
								{
								  clearOptionsListForSelectElmtId('candidateCategoryId');
								 createOptionsForSelectElmtId('candidateCategoryId',myResults);
								}

								else if(jsObj.task == "getGallariesForSelectedCategory")
								{
								  clearOptionsListForSelectElmtId('categoryGallarySelect');
								 createOptionsForSelectElmtId('categoryGallarySelect',myResults);
								}

								}catch (e) {
							     
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}

var selectedContentFile;
function buildLatestNews(result,jsObj)
{
  $("#showContentDiv").dialog({ stack: false,
								height: 600,
								width: 900,
								closeOnEscape: true,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 900,
								minHeight: 600,
								title:'TDP Party News',
								overlay: { opacity: 0.5, background: 'black'},
								close: function(event, ui) {
								document.getElementById("showContentDivInnerDiv").innerHTML ='';
							 }
		  
								});
		$("#showContentDiv").dialog();

		
	var str = '';
	var titleStr = null;
	var pathStr = null;
	var descriptionStr = null;
	
	for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
	if(result.relatedGalleries[0].filesList[i].isSelectedContent)
	{
	    selectedContentFile = result.relatedGalleries[0].filesList[i];
		titleStr = result.relatedGalleries[0].filesList[i].title;
		pathStr = result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path;
		descriptionStr = result.relatedGalleries[0].filesList[i].description;
		
		str +='<div class="main-title-sec" style="margin-top:10px;"><div id="showContentHeaderDiv" class="main-mbg">'+titleStr+'</div></div>';
		
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
		str +='<div id="imgDiv" class="popupcontainer"><img alt="'+titleStr+'" title="'+descriptionStr+'" style="max-width:750px;max-length:800px;" src="'+pathStr+'" /></div>';
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
    
	$("#showContentDivInnerDiv").html(str);
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
	  
	    var str='<div class="" id="imgDiv" style="text-align:center;"><img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" style="max-width:750px;max-length:800px;" src="'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" ></img></div>';
	  
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
	  
	    var str='<div class="" id="imgDiv" style="text-align:center;"><img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" style="max-width:750px;max-length:800px;" src="'+path+'"></img></div>';
	  
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

function getCandidates(){
	var jsObj={
		task:'getCandidateNamesInHomePage'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getCandidateNamesInHomePageAction.action?"+rparam;
	callAjax(jsObj, url);
}



function getCandidatesNews(){
	var candidateId=$('#candidatesListId option:selected').val();
	 $(".errorDiv").html('');
	if(candidateId==0){
		$('.errorDiv').html('<span class="text-error">Please Select Candidate</span>');
		return;
	}
     var fromDate = "";
	 var toDate = "";

	 var radioVal = $('input:radio[name=candidateNewsRadio]:checked').val();
	 if(radioVal == "byDate")
	 {
       fromDate = $("#existingFromText").val();
	   toDate   = $("#existingToText").val();

	   if(fromDate=="" && toDate == "")
	    {
		 $(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Please Select From And To Dates</span>');
		 return;
	    }
	    else if(fromDate =="")
	    {
	     $(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Please Select From Date</span>');
		 return;
	    }
	    else if(toDate =="")
	    {
	      $(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Please Select To Date</span>');
		  return;
	    }
	    else
	    {
		  var fromArrayprev = fromDate.split("/");
		  var datefrom=new Date(fromArrayprev[2], fromArrayprev[1]-1, fromArrayprev[0]);
		  var toArraypres = toDate.split("/");
		  var dateto=new Date(toArraypres[2], toArraypres[1]-1, toArraypres[0]);
		  
		  if (datefrom > dateto)
		  {
            $(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Invalid Date Selection.</span>');
            return;
		  }
	    }

	 }
	 var selectedGallaryIds = "";
	if($("#gallaryRadioId").is(":checked"))
	{
	  selectedGallaryIds = $('#gallaryId').val();
	  if(selectedGallaryIds == null || selectedGallaryIds == "null")
	  {
		$(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Please Select Gallary.</span>');
          return;
	  }

	}
	else
	 selectedGallaryIds = "";

	var categoryIds = "";
	if($("#categoryCheckBoxId").is(":checked"))
	{
      categoryIds = $("#candidateCategoryId").val();
	  if(categoryIds == null || categoryIds == "null")
	  {
		$(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Please Select Category.</span>');
          return;
	  }

	  if($("#categoryGallary").is(":checked"))
	  {
        selectedGallaryIds = $("#categoryGallarySelect").val();
		 if(selectedGallaryIds == null || selectedGallaryIds == "null")
	    {
		$(".errorDiv").html('<span class="text-error" style="margin-left:10px;">Please Select Gallary.</span>');
          return;
	   }
	  }
	}

	 var urlstr = "showNewsOfCandidateAction.action?candidateId="+candidateId+"&fromDate="+fromDate+"&toDate="+toDate+"&gallaryIds="+selectedGallaryIds+"&categoryIds="+categoryIds+"&tempVarable=true&";
		
     var browser1 = window.open(urlstr,"showMoreVideos","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
}

function getResponseDetailsByContentId(contentId)
{
		
     var browser1 = window.open("showNewsResponseAction.action?responseContentId ="+contentId+" ","showNewsResponse","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();


}

function getCandidateGallaries()
{
  $("#gallaryShowHideDiv").css("display","block");
    var fromDate = "";
	var toDate = "";
	var candidateId = $("#candidatesListId").val();
	$(".errorDiv").html('');
	if(candidateId == 0)
	{
	  $(".errorDiv").html('Please Select Candidate');
	  return;	
	}
	var radioVal = $('input:radio[name=candidateNewsRadio]:checked').val();
	if(radioVal == "byDate")
	{
	   fromDate = $("#existingFromText").val();
	   toDate = $("#existingToText").val();
	
	   if(fromDate=="" && toDate == "")
	   {
		 $(".errorDiv").html('Please Select From And To Dates');
		 return;
	   }
	   else if(fromDate =="")
	   {
	     $(".errorDiv").html('Please Select From Date');
		 return;
	   }
	   else if(toDate =="")
	   {
	     $(".errorDiv").html('Please Select To Date');
		 return;
	   }
	   /* else if (Date.parse(fromDate) > Date.parse(toDate)) {
         $(".errorDiv").html('Invalid Date Selection.');
         return;
	   }  */
	}

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
   var fromDate = "";
	var toDate = "";
	var candidateId = $("#candidatesListId").val();
	$(".errorDiv").html('');
	if(candidateId == 0)
	{
	  $(".errorDiv").html('Please Select Candidate');
	  return;	
	}
	var radioVal = $('input:radio[name=candidateNewsRadio]:checked').val();
	if(radioVal == "byDate")
	{
	   fromDate = $("#existingFromText").val();
	   toDate = $("#existingToText").val();
	
	   if(fromDate=="" && toDate == "")
	   {
		 $(".errorDiv").html('Please Select From And To Dates');
		 return;
	   }
	   else if(fromDate =="")
	   {
	     $(".errorDiv").html('Please Select From Date');
		 return;
	   }
	   else if(toDate =="")
	   {
	     $(".errorDiv").html('Please Select To Date');
		 return;
	   }
	   /* else if (Date.parse(fromDate) > Date.parse(toDate)) {
         $(".errorDiv").html('Invalid Date Selection.');
         return;
	   }  */
	}

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

$(document).ready(function(){
	
$(".dateField").live("click", function(){
	$(this).datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
      changeYear: true,
		maxDate: new Date()
		
	}).datepicker("show");
});

$(".candidateRadioCls").click(function(){
	var radioVal = $('input:radio[name=candidateNewsRadio]:checked').val();
	if(radioVal=="byAll")
	{
	 $("#existingFromText").val('');
	 $("#existingToText").val('');
	 $("#candidateNewsShowHideDatesDiv").css("display","none");
	}
	else
     $("#candidateNewsShowHideDatesDiv").css("display","block");
	
	
});


$("#candidatesListId").live("change",function(){
  var candidateId = $("#candidatesListId").val();
   $('.errorDiv').html('');
    clearOptionsListForSelectElmtId('gallaryId');
	clearOptionsListForSelectElmtId('candidateCategoryId');

  if(candidateId == 0)
  {
    $('.errorDiv').html('<span class="text-error" style="margin-left:20px;">Please Select Candidate</span>');
		return;
  }
	
  $("#categoryGallary").attr("checked",false);
  $("#categoryGallaryShowHideDiv").css("display","none");
  if($("#gallaryRadioId").is(":checked"))
  {
	$("#gallaryShowHideDiv").css("display","block");
    $("#gallaryRadioId").attr('checked', true);
	getCandidateGallaries();
  }
  else
  {
	$("#gallaryShowHideDiv").css("display","none");
  }

  if($("#categoryCheckBoxId").is(":checked"))
  {
	$("#categoryShowHideDiv").css("display","block");
    $("#categoryCheckBoxId").attr('checked', true);
	getCandidatecategories();
  }
  else
  {
	$("#categoryShowHideDiv").css("display","none");
  }
	
});

$("#gallaryRadioId").click(function(){
	if($("#gallaryRadioId").is(":checked"))
	{
      $("#gallaryShowHideDiv").css("display","block");
	  getCandidateGallaries();
	  $("#categoryCheckBoxId").attr('checked', false);
	  $("#categoryShowHideDiv").css("display","none");
	  $("#categoryGallaryShowHideDiv").css("display","none");
	}
	else
    {
	  $("#gallaryShowHideDiv").css("display","none");
    }

});

$("#categoryCheckBoxId").click(function(){
	
	if($("#categoryCheckBoxId").is(":checked"))
    {
	  $("#gallaryRadioId").attr('checked', false);
	  $("#gallaryShowHideDiv").css("display","none");
	  $("#categoryShowHideDiv").css("display","block");
	  getCandidatecategories();

	}
	else
	  $("#categoryShowHideDiv").css("display","none");

});


$("#categoryGallary").click(function(){

	var candidateId = $("#candidatesListId").val();
	$('.errorDiv').html('');
  if($("#categoryGallary").is(":checked"))
  {
	 

	 if(candidateId == 0)
	 {
       $('.errorDiv').html('<span class="text-error" style="margin-left:0px;">Please Select Candidate.</span>');
		return;
	 }
	 var categoryIdsArray = new Array();
    
	  $('#candidateCategoryId > option:selected').each(
       function(i){
         categoryIdsArray.push($(this).val());
     });

       if(categoryIdsArray == null || categoryIdsArray == "null" || categoryIdsArray.length == 0)
	   {
         $('.errorDiv').html('<span class="text-error" style="margin-left:20px;">Please Select Category.</span>');
		 return;
	   }
	$("#categoryGallaryShowHideDiv").css("display","block");
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
	 $("#categoryGallaryShowHideDiv").css("display","none");
	}

});


$("#candidateCategoryId").live("click",function(){
	
	$("#categoryGallary").attr('checked', false);
	$("#categoryGallaryShowHideDiv").css("display","none");
});

});
function getCandidatesResponseNews()
{
var urlStr="candidateResponseNews.action";
	var updateBrowser = window.open(urlStr,"editAnnouncement","scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();
}

function getLocationList()
{
 var scope = $("#listValue").val();
 var divEle = "";
 if(scope == "District")
  divEle = "userAccessDistrictList";

 else if(scope == "Constituency")
  divEle = "userAccessConstituencyList";

  var value = scope;
	if(value == 'District')
		{	
			$('#headingDiiv').html('View District Wise News');
			$('#showScopeSubsC').css("display","none");
			var str =''; 
			str +='<table style="margin-top:5px">';
			str +='<tr id="tableRowD">';
			//str+='<td><select id="userAccessDistrictList" class="selectWidth" name="userAccessDistrictList" onchange="getAllConstituenciesInStateByType(2,1,this.options[this.selectedIndex].value);">';
			str+='<td><select id="userAccessDistrictList" class="selectWidth" name="userAccessDistrictList" onchange="addCssStyle();" >';
			str+='</select></td>';	 
			str +='</tr>';
			str +='</table>';
			document.getElementById("showScopeSubsD").innerHTML = str;
			$('#showScopeSubsD').css("display","block");
			//getAllConstituenciesInDistrictByType(1);
		}
		if(value=="Constituency" || value=="Mandal" || value=="Panchayat" || value=="Booth")
		{	
			$('#headingDiiv').html('View Constituency Wise News');
			$('#showScopeSubsD').css("display","none");
			var str =''; 
			str +='<table style="margin-top:5px">';
			str +='<tr id="tableRowC">';
			str+='<td><select id="userAccessConstituencyList" class="selectWidth" name="userAccessConstituencyList" onchange="addCssStyle();" style="margin-left:20px;">';
			//onchange="getMandalList(this.options[this.selectedIndex].value);">';
			str+='</select></td>';	 
			str +='</tr>';
			str +='</table>';
			document.getElementById("showScopeSubsC").innerHTML = str;
			$('#showScopeSubsC').css("display","block");
			//getAllConstituenciesInStateByType(2, 1, 'constituency');
		} 

getLocationWiseNewsDetails(scope,divEle);
	
}

function showAllCategories()
{
    var urlstr = "showAllCategoriesAction.action?";
		
     var browser1 = window.open(urlstr,"allCategories","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
}

getLocationList();
</script>
</body>

</html>