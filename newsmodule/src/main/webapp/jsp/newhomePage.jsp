<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/> -->
	<title>Telugudesham Party</title>
	<meta name="" content="">
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
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
	<script src="js/partyWiseNewsDisplay.js"></script>
	<style type="text/css">
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

	</style>
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
						       
                             <div ${ctr.first ? 'class="item active"' : 'class="item"'}>
                          
						  
						 
                         <h4> <s:property value="fileTitle1"/></h4>
									<div class="row-fluid">				
									
									<s:if test="%{#newsGallaryDetails.fileType == 'Party'}" >
										 <c:if test="${newsGallaryDetails.displayImagePath != null}">
											<a class="thumbnail span4" style="height:120px;" href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img style="float:left;width:150px;height:110px;" src="${newsGallaryDetails.displayImagePath}" alt='<s:property value="fileTitle1"/> Image'/></a>
										</c:if>

										<c:if test="${newsGallaryDetails.displayImagePath == null}">
											<a class="thumbnail span4" style="height:120px;" href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img style="float:left;width:150px;height:110px;" src="./images/party_flags/${newsGallaryDetails.imagePathInUpperCase}" alt='<s:property value="fileTitle1"/> Image'/></a>
										</c:if>
										<p class="span8"><s:property value="description"/></p>
									    </div>
										<div class="row-fluid m_top10">
													<div class="span9">
														<p class="text-error">Source :${newsGallaryDetails.source}</p>
													</div>
													<div class="span2 ">
														<a href="newsPaginationAction.action?level=state">
															<button class="btn btn-mini pull-right" type="button">More...</button>
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
								<div class="carousel slide center" id="myCarousel">
										<ol class="carousel-indicators">
										  <li data-slide-to="0" data-target="#myCarousel" class="active"></li>
										  <li data-slide-to="1" data-target="#myCarousel" class=""></li>
										  <li data-slide-to="2" data-target="#myCarousel" class=""></li> 
										  <li data-slide-to="3" data-target="#myCarousel" class=""></li>
										</ol>
										<div class="carousel-inner">
										 <s:if test="resultMap != null && resultMap.size() > 0"> 
										
							    <s:iterator value="resultMap.NewsGallaryForDist" var="newsGallaryDetails" status="ctr">
						       
                             <div ${ctr.first ? 'class="item active"' : 'class="item"'}>
                          
						  
						 
                         <h4> <s:property value="fileTitle1"/></h4>
									<div class="row-fluid">				
									
									<s:if test="%{#newsGallaryDetails.fileType == 'Party'}" >
										 <c:if test="${newsGallaryDetails.displayImagePath != null}">
											<a class="thumbnail span4" style="height:120px;" href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img style="float:left;width:150px;height:110px;" src="${newsGallaryDetails.displayImagePath}" alt='<s:property value="fileTitle1"/> Image'/></a>
										</c:if>

										<c:if test="${newsGallaryDetails.displayImagePath == null}">
											<a class="thumbnail span4" style="height:120px;" href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img style="float:left;width:150px;height:110px;" src="./images/party_flags/${newsGallaryDetails.imagePathInUpperCase}" alt='<s:property value="fileTitle1"/> Image'/></a>
										</c:if>
										<p class="span8"><s:property value="description"/></p>
									    </div>
										<div class="row-fluid m_top10">
													<div class="span9">
														<p class="text-error">Source :${newsGallaryDetails.source}</p>
													</div>
													<div class="span2 ">
														<a href="newsPaginationAction.action?level=district">
															<button class="btn btn-mini pull-right" type="button">More...</button>
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
				<div class="span3" style="height:554px">
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>View your Constituency News</h4></div>
						<div class="span12">
						<table style="margin-top:15px;">
							<tr id="tableRowS">
								<td id="tdWidth" style="padding-right: 55px;">
									Area:<font id="requiredValue" class="requiredFont">*</font> 
								</td>
								<td>
									<select id="listValue" onchange="populateLocations(this.options[this.selectedIndex].value)">
									<option value="0"> Select Area </option>
									<option value="Constituency" selected="selected"> Constituency </option>
									<!--<option value="Mandal"> Mandal / Muncipality </option>
									<option value="Panchayat"> Panchayath / Ward </option>
									<option value="Booth"> Booth </option> -->
									</select>
								</td>
								</tr>
								<tr>
								<td class="tdWidth1">Constituency:<font id="requiredValue" class="requiredFont">*</font></td>
								<td><select id="userAccessConstituencyList" class="selectWidth" name="userAccessConstituencyList"><!-- onchange="getMandalList(this.options[this.selectedIndex].value);">-->
								</select></td>	 
							</tr>
						</table>
						<button id="sendButton" class="btn btn-warning" onclick="handleSubmit()" style="margin-bottom: 15px; margin-left: 75px;font-weight:bold;" > View News</button> 
						</div>
					</div>
				</div>
				<!-----View your Constituency News End------>
				<!-----Top view News DIv------>
				<div class="span3" style="height:554px">
					<div class="row-fluid widget">
						<div class="span12 boxHeading"><h4>Top Views</h4></div>
						<div class="span12">
							<ul class=" nav nav-list bs-docs-sidenav">
								<li>
									<h6>News Main Title</h6>
										<span ><i class="smal">04 June 2013</i></span>
								</li>
								
								
							</ul>
						</div>
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
									<!-- <li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet</a></li>
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet</a></li>
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet</a></li>
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet</a></li>
									<li><a href="#" class="muted"><i class="icon-share-alt"></i> Lorem ipsum dolor sit amet</a></li> -->
									<c:if test="${fileVOsList != null}">
									 <c:forEach var="newDetails" items="${fileVOsList}">
									  <li><a href="javascript:{}" onclick="getLatestNewsDetails(${newDetails.contentId})" class="muted"><i class="icon-share-alt"></i>${newDetails.fileTitle1}</a></li>
									 </c:forEach>
									</c:if>
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
							   <a href="javascript:{showAllgallaries()}" class=" btn btn-mini pull-right ">More..</a>
									
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
							<div class="span12 boxHeading"><h4>Latest Video</h4></div>
								<div class="row-fluid ">
									<div class="span12 pad10 ">
										<ul class="unstyled">
											<!--<li><a href="#" class="thumbnail span2"><img src="http://placehold.it/200x150"></a></li>
											<li><a href="#" class="thumbnail span2"><img src="http://placehold.it/200x150"></a></li>
											<li><a href="#" class="thumbnail span2"><img src="http://placehold.it/200x150"></a></li>
											<li><a href="#" class="thumbnail span2"><img src="http://placehold.it/200x150"></a></li>
											<li><a href="#" class="thumbnail span2"><img src="http://placehold.it/200x150"></a></li>
											<li><a href="#" class="thumbnail span2"><img src="http://placehold.it/200x150"></a></li>-->
											<c:forEach var="file" items="${fileList}">
											<li><a class="thumbnail span2" href="javascript:{showVideoGallery(${file.fileId})}"><img src='http://img.youtube.com/vi/<s:property value="filePath"/>/0.jpg'/></a></li>   </c:forEach>
										</ul>
										<!--<a href="#" class="pull-right btn btn-mini" style="margin-top:75px;">More</a>-->
									</div>
								</div>
						</div>
					</div>
				</div>
				<div id="video_dialog">
					<div id="videos"></div>
				</div>
			<!-------- Row-3 end -------------->
		<div id="showContentDiv">
	 <div id="contentAjaxCallImg"><img src="images/icons/goldAjaxLoad.gif"></div>
	 <div id="showContentDivInnerDiv"></div>
	</div>		
	</div>
		<!------JS------>
	<!--<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>-->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.carousel.js"></script>
	
<script>
getAllConstituenciesInStateByType(2, 1, 'constituency');
function showFilesInGallary(gallaryId)
{
 var urlstr = "showAllFilesOfAGallary.action?gallaryId="+gallaryId+"";
		
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

							alert("Invalid JSON result" + e);   
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

</script>
</body>

</html>