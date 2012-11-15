		<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<%@taglib prefix="s" uri="/struts-tags" %>
		<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ page import="java.util.ResourceBundle;" %>
		
		
		
		
		<title>Party Analyst.com</title>
		<meta name="" content="">
		
		
		<!--<link type="text/css" href="styles/assets/css/bootstrap-responsive.min.css" rel="stylesheet" />-->
		 		 
		<link rel="stylesheet" type="text/css" href="style.css"/> 
		<link type="">
	         <link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
		<link type="text/css" href="styles/bootstrapInHome/bootstrap-responsive.min.css" rel="stylesheet" />
		<link href="styles/rating/jquery.rating.css" rel="stylesheet" type="text/css">
		
		<script src="js/rating/jquery.MetaData.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/connectPeople/connectPeople.js"></script> 
	<!--	<script type="text/javascript" src="js/bootstrapInHome/bootstrap.min.js"></script> 
		<script type="text/javascript" src="js/bootstrapInHome/bootstrap-carousel.js"></script> 
		<script type="text/javascript" src="js/bootstrapInHome/bootstrap-transition.js"></script> 
	---<script type="text/javascript" src=""/>--->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	      <script src="js/slides.min.jquery.js"></script>
		  <script type="text/javascript" src="js/jquery.carousel.js"></script>
		  <script type="text/javascript" src="js/homePage/newHomePage.js"> </script>

		  
		   <script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
		   	<script type="text/javascript" src="js/bootstrapInHome/bootstrap-transition.js"></script> 
			<script type="text/javascript" src="js/raty/js/jquery.raty.min.js"></script>
	       <link media="screen" type="text/css" href="http://fonts.googleapis.com/css?family=Abel&amp;ver=3.4.2" id="abel_google-fonts-css" rel="stylesheet">
	</head>
	<style>
	.jqCarousel ul {
		    position:absolute;
		    overflow:hidden;
		    margin:0;
		    padding:0;
		    list-style:none;
		}

		.no-js .jqCarousel ul {position:static;}

		.jqCarousel .mask {
		    position:relative;
		    overflow:hidden;
		   // border:6px solid #444;
		}

		.jqCarousel ul li {
			float:left;
			}

		.jqCarousel .pagination-links {
		    list-style:none;
		    margin:0;
		    padding:0;
		}
		 
		.jqCarousel .pagination-links li {
		    display:inline;
		}

		.jqCarousel .pagination-links li a {
			padding:2px 6px;	
		}

		.jqCarousel .pagination-links li a:hover {text-decoration:none;}

		.jqCarousel .pagination-links li.current a {
			 background:#444;
			 color:#fff;
		}
		
		 
		.jqCarousel .disabled {
		    color:gray;
		    cursor:default;
		}
		
		/** my jqCarousel 1 **/
		#my-jqCarousel-1 .mask {
			width:100%;

		}
		#my-jqCarousel-1 ul li {
			height:250px;
			width:598px;
			
			position:relative;
		}
		
		#my-jqCarousel-1 .prev{
		  left:22px;
		}
		#my-jqCarousel-1 .next{
		  right:22px;
		}
		#my-jqCarousel-1 ol{display:none;}
		#my-jqCarousel-news .mask {
			width:600px;
		}
		
		#my-jqCarousel-news ol{display:none;}
		#my-jqCarousel-news ul li {
			height:210px;
			width:596px;
			padding:3px;
		}
		#my-jqCarousel-news .prev{
		  display:none;
		}
		#my-jqCarousel-news .next{
		 display:none;
		}
		
		/** my jqCarousel 2 **/
		#my-jqCarousel-2 .mask {
			width:630px;
		}

		#my-jqCarousel-2 ul li {
			margin:0 10px 0 0;
		}

		/** my jqCarousel 3 **/
		#my-jqCarousel-3 .mask {
			margin-top:20px;
			width:600px;
		}
		#my-jqCarousel-3 ul li {
			height:225px;
			width:150px;
			padding:3px;
		}

	.news-paginate{display: table;
    left: 84%;
    margin: 0;
    padding: 0;
    position: absolute;
    top: 80%;}
		
	
	.photogallery-caption {color:#fff;font:14px;position:absolute;bottom:0px;left:0px;width:98%;background:rgba(0,0,0,0.8);margin:0px;padding:5px;color:#fff;}
	
	body{background:#5D5D5D;}
	p{color:#333;font-size:13px;line-height:18px;}
	.media_hr{border-bottom:1px solid #333; padding-bottom:5px;}
	.media_hr:last-child{border:none;}
	.p_list{background:#fff; display:table;width:100%; padding:3px 3px 3px 3px;border-radius:5px; margin-top:5px;}
	.p_stars{}
	.d{color:black;padding:15px;font-size:30px;}
	.hottopics a{text-decoration:none;}
	
	.form-horizontal label{  display:block; font:12px Arial;
    margin-bottom:5px;padding-top:5px;cursor:hand;cursor:pointer;}
	.form-horizontal .radio
	{
	font-size: 13px;
    font-weight: normal;
    margin-top:-1px;
	margin-right:8px;
	}
	
.opinionpoll .question{padding:5px;border-bottom:1px dashed #ccc;font:13px Arial;}
.opinionpoll .answer{border-bottom:1px dashed #ccc;padding:0px ;margin-bottom:5px;margin-left:19px;}
.opinionpoll .votebtn{margin:0px auto;display:block;width:75px;}
.resultdisplay a{display:inline-block;text-decoration:none;}
.resultdisplay .previouslink{ float:left;}
.resultdisplay .nextlink{ float:right;}

.opinionpoll .answer .span2{margin-left:0px;}
.opinionpoll .answer span{margin-left:10px;}

 #newsGallaryDisplay .pagination , #videoGallariesDisplay .pagination{margin:0px;}
		#newsGallaryDisplay .overview li{display:none;}
	#newsGallaryDisplay .overview li:first-child{display:inline-block;}
	#newsGallaryDisplay .overview li h3{height:54px;color:#0067CC;}
	.round-link{ background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 15px 15px 15px 15px;
    display: inline-block;
    padding: 5px 14px;
	  color: #0088CC;
    text-decoration: none;
	}
	.round-link:hover{background-color: #fff;
    border: 1px solid #aaa;   text-decoration: none;}
	.centerblock{
	
  background-color: #fbfbfb;
  	
	}
	#videoGallariesDisplay .thumbnails a{ text-decoration: none;text-align:center;color:#000;}
	#videoGallariesDisplay .thumbnails p{ text-overflow: ellipsis;   /* change to your preferences */
      overflow:hidden;height:30px;text-align:center;}
	  #videoGallariesDisplay .thumbnails{height:170px;overflow:hidden;}
	#videoGallariesDisplay .span4{margin-left:7px;}
#videoGallariesDisplay .span4 .thumbnail{padding:5px 0px;}
.carousel-caption {padding:5px;}
.carousel-caption p{padding:5px;margin-bottom:0px;}
.bg-color{background-color: #F7F7F9;
    border: 1px solid #E1E1E8;}
.container-fluid >.row-fluid > .span3 , .container-fluid >.row-fluid > .span6{margin-left:10px;margin-top:0px;}

.body-background{background: #EBEBEB; /* Old browsers */
}

.widget-simple{

    background: none repeat scroll 0 0 #FFFFFF;
    padding: 20px;
	/*border-top: 5px solid #000;*/
	box-shadow:0 0 4px rgba(0, 0, 0, 0.3);
	margin:10px 0px 20px;
	position:relative;
}
.widget{

    background: none repeat scroll 0 0 #FFFFFF;
    padding:0px 20px 20px;
	border-top: 5px solid #000;
	box-shadow:0 0 4px rgba(0, 0, 0, 0.3);
	margin:10px 0px 20px;
	position:relative;
}
.p-top20{padding-top:20px;}

.red:before {
    background: #ff0000;
    content: " ";
    width: 45px;
    height: 5px;
    position: absolute;
    top: -5px;
    left: 0px;
}

.green:before {
    background: #A4D070;
    content: " ";
    width: 45px;
    height: 5px;
    position: absolute;
    top: -5px;
    left: 0px;
}
.yellow:before {
    background: #FDE498;
    content: " ";
    width: 45px;
    height: 5px;
    position: absolute;
    top: -5px;
    left: 0px;
}
.blue:before {
    background:#09B2EE;
    content: " ";
    width: 45px;
    height: 5px;
    position: absolute;
    top: -5px;
    left: 0px;
}

.gray:before {
    background:#aaa;
    content: " ";
    width: 45px;
    height: 5px;
    position: absolute;
    top: -5px;
    left: 0px;
}


.whitegloss{background: rgb(238,238,238); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(238,238,238,1) 0%, rgba(255,255,255,1) 49%, rgba(255,255,255,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(238,238,238,1)), color-stop(49%,rgba(255,255,255,1)), color-stop(100%,rgba(255,255,255,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(238,238,238,1) 0%,rgba(255,255,255,1) 49%,rgba(255,255,255,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(238,238,238,1) 0%,rgba(255,255,255,1) 49%,rgba(255,255,255,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(238,238,238,1) 0%,rgba(255,255,255,1) 49%,rgba(255,255,255,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(238,238,238,1) 0%,rgba(255,255,255,1) 49%,rgba(255,255,255,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#eeeeee', endColorstr='#ffffff',GradientType=0 ); /* IE6-9 */


}

.background{  background: url("images/bg01.jpg") repeat scroll 0 0 transparent;}

.widget h2 {
    color: #fff;
    font-size: 20px;
    font-weight: normal;
    line-height: 20px;
    text-transform: uppercase;
	text-rendering: optimizelegibility;
	font-family:'Abel',sans-serif,Helvetica;
	margin:0px -20px;
	background:#5e5e5e;
	padding:10px 20px;
}
.widget .b-top1{
border-top:1px solid #efefef;
}

.widget .widget-block h5{
    color: #1212b8;
    font-size: 16px;
    font-weight: normal;
    line-height: 16px;
    text-transform: uppercase;
	text-rendering: optimizelegibility;
	font-family:'Abel',sans-serif,Helvetica;
	text-shadow:1px 1px #fff;
}

.widget .widget-block{margin:5px -20px;
border-bottom:1px solid #efefef;
padding:5px 20px;
}
.quicklinks .widget-block .btn {display:block;clear:both;}
.m-left5{margin-left:5px;}
.m-top15{margin-top:15px;}
.clear-both{clear:both;}
.widget .pager .btn{display:inline;clear:none;}
/* UPDATED BY YAZU */	
#photoGallaryDisplay .thumbnails a{ text-decoration: none;text-align:center;color:#000;}
	#photoGallaryDisplay .thumbnails p{ text-overflow: ellipsis;   /* change to your preferences */
      overflow:hidden;height:30px;text-align:center;}
	  #photoGallaryDisplay .thumbnails{height:131px;overflow:hidden;}
	#photoGallaryDisplay .span4{margin-left:7px;}
#photoGallaryDisplay .span4 .thumbnail{padding:5px 0px;}



.problemheadClass{
height:40px;border-bottom:1px solid #f3f3f3;padding:10px;
}

.dropdown-toggle img{margin-right:10px;}
 

	</style>
	
<div class="container-fluid m-top15">
		<div class="row-fluid">
			
		<!--------left div------->
			<div class="span3">
			<!-------Quick Links Block--------------->
		<div class="widget green quicklinks"><h2>Quick Links</h2>
				
				<!---- View Election Results------->
							<div class="widget-block" contentindex="4c">
								<h5> View Election Result</h5>
								<table>
									<tbody>
									<tr>
										<td width="65%"><div id="electionDetailsErrorMsgDiv" style="display:none;"><font color="red"><b>*Select All Inputs</b></font></div></td>
									</tr>
										<tr>
											<td>
												<select id="electionTypeId" name="electionType"  cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;width: 153px;"
		                                          onchange="checkElectionType(this.options[this.selectedIndex].value)">
		                                               <option value="0">Select Type</option>
		                                               <option value="2">Assembly</option>
		                                               <option value="1">Parliament</option>
		                                       </select>
											</td>
										</tr>
										<tr>
											<td>
												<select id="states" name="state_s" cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;width:153px;"onchange="getElectionYearsInHomePage('Assembly')">
		                                       	</select>
											</td>
										</tr>
										<tr>
											<td>
                                                  <select id="electionYears" cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;width:153px;">
			                                      </select>
											</td>
										</tr>
									</tbody>
								</table>
								
								    <button class="btn btn-success" type="button" onclick="viewElectionResults()">Go</button>
								
							</div>
					<!---------------->
		
							<!------View Your State---------->
							<div class="widget-block b-top1" contentindex="0c" >
								<h5>View Your State</h5>
								<p>Select your state to view its Assembly, Parliament, Local Bodies election results.</p>
								<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="statesList" listKey="id" listValue="name" onchange="setStateValue()"/>
								
								<button class="btn btn-success clear-both" type="button" onclick="navigateToStatePage()">Go</button>
										
							</div>
						<!----View Your district------>
							<div class="widget-block" contentindex="1c">
							<div id="alertMessage_district" style="color:red;font-weight:bold;"></div>
								     <h5>View Your district</h5>
									 <p>Select your district to view its election results in district level.</p>
									 <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state" id="stateList_d" list="statesList" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtList_d')"></s:select>
                                     <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district" id="districtList_d" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select District"/>
									     <br>
									   <a onclick="navigateToDistrictPage()" href="javascript:{}">
									     <button class="btn btn-success" type="button">Go</button>
									   </a>
								    
							</div>
						<!--View your constituency-->
							<div class="widget-block" contentindex="2c">
							<div id="alertMessage" style="color:red;font-weight:bold;"></div>
								<h5>View Your Constituency</h5>
								<p>Select Constituency Type<br>
										<input type="radio" onclick="hideUnhideSelectBox(this.id, 'constituency')" id="assembly_radio" name="assembly_radio" checked="checked">
											Assembly
											<br>
										<input type="radio" onclick="hideUnhideSelectBox(this.id,'constituency')" id="p_radio" name="assembly_radio" >
											Parliament
								</p>
								<table style="display: block;" id="stateTable">
									<tbody>
										<tr>
											<td>
												<s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="state" id="stateList_c" list="statesList" listKey="id" listValue="name"  onchange="getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,'constituency')"/>
											</td>
										</tr>
									</tbody>
								</table>
								<table id="constTable" style="display:block;">
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your Constituency" name="constituency" id="constituency" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select Constituency"/></td>
									</tr>
								</table>
								
								<button class="btn btn-success" type="button" onclick="navigateToConstituencyPage()">Go</button>
							</div>
							
							<!--View your Locality-->
						
							<div class="widget-block" contentindex="3c" >
								<h5>View Your Locality</h5>
                   				<div>
									<table>								
										<tbody>
											<tr>
												<td>
													<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_l" list="statesListForLocalBodyElection" listKey="id" listValue="name" onchange="getLocalBodiesForState(this.options[this.selectedIndex].value)"/>
												</td>									
											</tr>
											
											<tr>
												<td><div id="localBodiesRadioDiv_label"></div></td>
											</tr>
											
											<tr>
												<td>
													<div id="localBodiesRadioDiv_data">
														<input type="radio" value="5" onclick="getSelectElmtForLocalBody(this.value)" name="localBodyRadio"/>Muncipality
														<input type="radio" value="6" onclick="getSelectElmtForLocalBody(this.value)" name="localBodyRadio"/>Corporation
														<input type="radio" value="7" onclick="getSelectElmtForLocalBody(this.value)" name="localBodyRadio"/>Greater Municipal Corp
													</div>
												</td>									
											</tr>
											
											<tr>
												<td style=""><div id="localBodiesSelectDiv_label"></div></td>
											</tr>
											<tr>
												<td><div id="localBodiesSelectDiv_data"></div></td>									
											</tr>
											<tr>
												<td><div id="localBodies_errorDiv"></div></td>									
											</tr>
										</tbody>
									</table>
								</div>
								  
								<button class="btn btn-success" type="button" onclick="navigateToLocalBodyPage()">Go</button>
								  
							</div>	
							<div class="widget-block" contentindex="4c" >
							    <div class="btn-group">
									<a class="btn dropdown-toggle btn-small" data-toggle="dropdown" href="#" style="text-decoration:none;width:140px;"><h5>View Your Party<b class="caret"></b></h5></a>
										<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel" id="viewYourParty">
											<li><a tabindex="-1" href="partyPageAction.action?partyId=362"><img src="images/party_flags/INC.png" width="50" height="50"  alt="INC Party" /> INC</a></li>
											  <li class="divider"></li>
											<li><a tabindex="-1" href="partyPageAction.action?partyId=872"><img src="images/party_flags/TDP.PNG" width="50" height="50" alt="TDP Party" />TDP</a></li>	
											  <li class="divider"></li>
											<li><a tabindex="-1" href="partyPageAction.action?partyId=1117"><img src="images/party_flags/YSRC.PNG" width="50" height="50" alt="YSRC Party" />YSRC</a></li>
											  <li class="divider"></li>
											<li><a tabindex="-1" href="partyPageAction.action?partyId=163"><img src="images/party_flags/BJP.png" width="50" height="50" alt="BJP Party" /> BJP</a></li>
											  <li class="divider"></li>
											<li><a tabindex="-1" href="partyPageAction.action?partyId=886"><img src="images/party_flags/TRS.png" width="50" height="50" alt="TRS Party" />TRS</a></li>
											  <li class="divider"></li>
											<li><a tabindex="-1" href="partyPageAction.action?partyId=839"><img src="images/party_flags/SP.png" width="50" height="50" alt="SP Party" />SP</a></li>
											  <li class="divider"></li>
											<li><a tabindex="-1" href="partyPageAction.action?partyId=239"><img src="images/party_flags/BSP.png" width="50" height="50" alt="BSP Party" /> BSP</a></li>
											  <li class="divider"></li>
											<li><a tabindex="-1" href="partyPageAction.action?partyId=76"><img src="images/party_flags/AITC.png" width="50" height="50" alt="AITC Party" /> AITC</a></li>
											  <li class="divider"></li>
											<li><a tabindex="-1" href="partyPageAction.action?partyId=579"><img src="images/party_flags/NCP.png" width="50" height="50" alt="NCP Party" />NCP</a></li>
										</ul>
								</div>
							</div>
			</div>
			
							<div class="widget yellow p-top20">
								<h5>The right to vote is the foundation of democracy</h5>
								<h6>your vote is your voice.don't lose your voice.</h6>
								<a href="voters.action" class="btn btn-primary" >Look into It</a>
							</div>
			
	</div>
			
		<!--------Center div------>
			<div class="span6">
			<!--sasi photo gallary start-->
				<div class="widget blue p-top20" style="height:270px;">
			
					<div id="PhotoGalleryCarousel" class="carousel" style="display:none;">
						<!-- Carousel items -->
						<div class="carousel-inner">
							<s:if test="resultMap != null && resultMap.size() > 0">
							<s:iterator value="resultMap.photogallary" var="photogallaryDetails">
        
								<div class="item">
		
			
									<s:if test="%{#photogallaryDetails.fileType == 'Candidate'}" >         
										<a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' class="">
									</s:if>
									<s:if test="%{#photogallaryDetails.fileType == 'Party'}" >
										<a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' class="">
									</s:if>
									<s:if test="%{#photogallaryDetails.fileType == 'Special Page'}" >
										<a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' class="">
									</s:if>
					
									<img src='<s:property value="pathOfFile"/>'  alt='<s:property value="fileTitle1"/>' title='<s:property value="description"/>' /></a>
					
			
									<div class="carousel-caption">
										<p> <s:property value="gallaryName"/> Photo Gallary.</p>
									
									
									<s:if test="%{#photogallaryDetails.fileType == 'Candidate'}" >
										<a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' style="color: #000000;font-family: verdana;" class="btn btn-mini pull-right">
									</s:if>
									<s:if test="%{#photogallaryDetails.fileType == 'Party'}" >
										<a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' style="color: #000000;font-family: verdana;" class="btn btn-mini pull-right">
									</s:if>
									<s:if test="%{#photogallaryDetails.fileType == 'Special Page'}" >
										<a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' style="color: #000000;font-family: verdana;" class="btn btn-mini pull-right">
									</s:if>
									View Album</a>
									</div>	
								</div>
							</s:iterator>
							</s:if>
						</div>
						<!-- Carousel nav -->
						<a class="carousel-control left" href="#" data-slide="prev">&lsaquo;</a>
						<a class="carousel-control right" href="#" data-slide="next">&rsaquo;</a>
					</div>
					
					<div id="my-jqCarousel-1" class="jqCarousel module thumbnail">
					 <ul>
		    <s:if test="resultMap != null && resultMap.size() > 0">
			  <s:iterator value="resultMap.photogallary" var="photogallaryDetails">
			   
			    
			      <li >
			        <s:if test="%{#photogallaryDetails.fileType == 'Candidate'}" >         
					  <a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>'>
			        </s:if>
					<s:if test="%{#photogallaryDetails.fileType == 'Party'}" >
					  <a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>'>
			        </s:if>
					<s:if test="%{#photogallaryDetails.fileType == 'Special Page'}" >
					  <a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>'>
			        </s:if>
		            <img src='<s:property value="pathOfFile"/>' style="width:600px;height:270px;" alt='<s:property value="fileTitle1"/>' title='<s:property value="description"/>'/></a>			         
				    <p class="photogallery-caption span12">
						  <s:property value="description"/>

			        <s:if test="%{#photogallaryDetails.fileType == 'Candidate'}" >
					  <a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' class="btn btn-mini pull-right">
			        </s:if>
					<s:if test="%{#photogallaryDetails.fileType == 'Party'}" >
					  <a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' class="btn  btn-mini pull-right">
			        </s:if>
					<s:if test="%{#photogallaryDetails.fileType == 'Special Page'}" >
					  <a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' class="btn  btn-mini pull-right">
			        </s:if>
					
			          <!--<s:property value="gallaryName"/> --> View Album</a>
				

					</p>
				  </li>
			    
			  </s:iterator>
			 </s:if>
			 </ul>
		</div>
					
				</div>
			
			<!--sasi photo gallary END-->
			
			
				<!-----NEWS Block-------->
				<div class="widget green">
				  
				<!--sasi news from database start-->
							
				<div id="my-jqCarousel-news" class="jqCarousel module">
		    <ul class="overview media">
							<s:if test="resultMap != null && resultMap.size() > 0"> 
							<s:iterator value="resultMap.NewsGallary" var="newsGallaryDetails">
								<li>
									<h4><s:property value="fileTitle1"/></h4>
									<div class="row-fluid">
									<s:if test="%{#newsGallaryDetails.fileType == 'Candidate'}" >
										<a class="thumbnail span4" href="#">
											<img src="http://placehold.it/200x150">
										</a>
										<p class="span7"><s:property value="description"/></p>
										<a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' class="pull-right round-link">
									</s:if>
									
									<s:if test="%{#newsGallaryDetails.fileType == 'Party'}" >
										<a class="thumbnail span4" href="#">
											<img src="http://placehold.it/200x150">
										</a>
										<p class="span7"><s:property value="description"/></p>
										<a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' class="pull-right round-link">
									</s:if>
									
									<s:if test="%{#newsGallaryDetails.fileType == 'Special Page'}" >
										<a class="thumbnail span4" href="#">
											<img src="http://placehold.it/200x150" class="thum">
										</a>
										<p class="span7"><s:property value="description"/></p>
										<a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' class="pull-right round-link">
									</s:if>
									more &rarr;
									</a>
									
									</div>
									
								</li>
							</s:iterator>
							</s:if>
						</ul>
						
			
		</div>
		<div class="pagination news-paginate">
              <ul>
                <li><a href="#" class="prev-sl" title="Previous News">˼/a></li>
                 <li><a href="#" class="next-sl" title="Next News">ۼ/a></li>
             </ul>
            </div>
				</div>	
				<!-- END OF NEWS BLOCK -->
				<div class="widget yellow">
				<h2>Latest Video Galleries</h2>
				<!--video gallary-->
				<div id="my-jqCarousel-3" class="jqCarousel module">
		    <ul class="thumbnails">
					<s:if test="resultMap != null && resultMap.size() > 0">
					<s:iterator value="resultMap.VideoGallary" var="videoGallaryDetails">

					<li class="">
					 <div class="thumbnail row-fluid">
					<s:if test="%{#videoGallaryDetails.fileType == 'Candidate'}" >
					<a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' class="span12" >
					</s:if>
					<s:if test="%{#videoGallaryDetails.fileType == 'Party'}" >
					<a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' class="span12">
					</s:if>
					<s:if test="%{#videoGallaryDetails.fileType == 'Special Page'}" >
					<a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' class="span12">
					</s:if>
					<img src='http://img.youtube.com/vi/<s:property value="pathOfFile"/>/1.jpg' alt='<s:property value="fileTitle1"/>' title='<s:property value="description"/>'/></a>

					<s:if test="%{#videoGallaryDetails.fileType == 'Candidate'}" >
					<a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' >
					</s:if>
					<s:if test="%{#videoGallaryDetails.fileType == 'Party'}" >
					<a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'>
					</s:if>
					<s:if test="%{#videoGallaryDetails.fileType == 'Special Page'}" >
					<a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'>
					</s:if>
					</a>
					<p class="caption"><s:property value="gallaryName"/></p>
					</div>	
					</li>

					</s:iterator>
					</s:if>
					</ul>
				</div>
				<!--sasi video gallary start-->
				
				</div>
				<!--sasi video gallary end-->
							
				
				<!---------Your Locality ProblemsView Block----->
				<div class="widget red">
				  <h2>Your Locality Problems</h2>
				  <h5>New or Existing - Our Platform elevates your problems to the external world. </h5>
				<div class="pager widget-block"> 
					
							<a href="problemSearchAction.action" class="btn btn-danger pull-right">View All Problems</a>
			
								<c:if test="${sessionScope.hasFreeUserRole != true && sessionScope.hasPartyAnalystUserRole != true}"> 
									<a href="javascript:{}" onclick="showNotLogIn();" class="btn btn-danger  pull-right" >Post Your Problems</a>
								</c:if>
								<c:if test="${sessionScope.hasFreeUserRole == true && sessionScope.hasPartyAnalystUserRole != true}">
									<a href="javascript:{}" onclick="openAddNewProblemWindowForDashBoard();" class="btn btn-danger btn-large pull-right" style="margin:5px;">Post Your Problems</a>
								</c:if>
				</div>
				<div id="logInDiv"></div>
					<div id="problemsShowDIV"></div>
				</div>
				
				<div class="widget blue">
					<h2> Tweets From Parties and Leaders </h2>
						<div class="widget-block b-top1">
							<jsp:include page="newsocialtwitter.jsp" /> 
						</div>
				</div>
				<!-- Close Center DIV -->			
			</div>
		<!--------Right div------>
			<div class="span3">
			
				<div class="widget yellow p-top20">You've come to the right place!<h5>THE NEW PARTYANALYST</h5>
				<a href="viewFeaturesAction.action" class="btn btn-primary" >Explore!</a>
				</div>
				
				<div class="alert alert-info">
				<h5>Your Ad Here...</h5>
				
				</div>
				<!-----------Hot Topics--------------->
				<div class="widget red hottopics">
					<h2>Hot Topics</h2>
					<c:forEach var="specialPages" items="${specialPageVOList}">
					<div class="media media_hr widget-block b-top1">
						
						<div class="media-body">
						<a href="specialPageAction.action?specialPageId=${specialPages.specialPageId}" title="${specialPages.title}">
							<h5>${specialPages.title}</h5>
								<p>
								<img src="${specialPages.eventImagePath}" class="pull-left thumbnail span7"/>
									${specialPages.description}
								</p>
						</a>
						</div>
						
					</div>
						</c:forEach>				
					<!-- <div class="media media_hr widget-block b-top1">
						
						<div class="media-body">
						<a href="specialPageAction.action?specialPageId=13" title="Gujarat Elections - 2012">
							<h5>Gujarat Elections - 2012</h5>
								<p>
								<img src="./images/new_homepage/GujaratTham.png" alt="Gujarat Elections - 2012" class="pull-left thumbnail span7"/>
									From 1995 Gujarath is Ruled By BJP ,The top most developed state in all aspects in india is going to face 2012 elections
								</p>
						</a>
						</div>
						
					</div> -->
					<!--------->
					<!-- <div class="media media_hr widget-block">
						
						
						<a href="specialPageAction.action?specialPageId=14" title="Himachal Pradesh Elections - 2012">
						<div class="media-body">
							<h5 class="media-heading">Himachal Pradesh Elections - 2012</h5>
								<p>
								<img src="./images/new_homepage/Himachal Pradesh Tham.png" alt="Himachal Pradesh Elections - 2012" class="pull-left thumbnail span7"/>
								Himachal Pradesh Elections were held on 4th NOV and The fate of INC and BJP in Himachal Pradesh is going to out on 20th NOV.</p>
						</div></a>

						
					</div> !--> 
					<!----------------->
					<!-- <div class="media media_hr widget-block">
						
						
						<a href="specialPageAction.action?specialPageId=12" title="Vasthunna Meekosam">
						<div class="media-body">
							<h5 class="media-heading">Vastunna Meekosam</h5>
								<p>
								<img  class="media-object pull-left thumbnail span7" src="images/tdp.png"  >
								Telugu Desam Party President and former Andhra Pradesh Chief Minister N Chandrababu Naidu on 2nd NOV embarked on a marathon 2,340 km-long Padayatra (foot march) to reach out to the people across the length and breadth of the state. </p>
						</div></a>
						
					</div> !-->
					
					<!----------------->
					<!-- <div class="media media_hr widget-block">
						
						<a href="specialPageAction.action?specialPageId=15" title="Indiramma Bata">
						<div class="media-body">
							<h5 class="media-heading">Indiramma Bata</h5>
							<img  class="media-object pull-left thumbnail span7" src="images/congress.png" >
								<p>Andhra Pradesh chief minister N Kiran Kumar Reddy launched a new mass contact program Indiramma Bata in every district of the state to assess whether the government schemes were reaching people at the grass-root level. </p>
						</div></a>
						
					</div> !--> 
					
					<!----------------->
					<!-- <div class="media media_hr widget-block">
						
						<a href="specialPageAction.action?specialPageId=16" title="Maro Praja Prasthanam">
						<div class="media-body">
							<h5 class="media-heading">Maro Praja Prasthanam</h5>
								<p>
								<img  class="media-object pull-left thumbnail span7" src="images/ysr.png" >
								Y.S. Sharmila, the daughter of former Chief Minister of Andhra Pradesh, late Dr. YS Rajasekhar Reddy is gearing up for Maro Praja Prasthanam from Idupulapaya to Ichapuram in Srikakulam district. </p>
						</div></a>
						
					</div> !-->
					
				<!-- <input type="button" class="btn-primary btn" value="View More" onclick="getAllSpecialPagesForHomePage()" />-->

				<a href="specialPageInfoAction.action" class="btn btn-primary" >View More</a>

				</div>
				<!-----------Opinion Poll--------------->
				<div class="widget blue">
					<h2>Opinion Poll</h2>
				<div class="widget-block b-top1">
						<div id="pollsWidgetHeader"></div>

                                     <div>
									 <div id="alreadyVotedMsg"></div>
                                     </div>
									<div id="pollsWidgetBody">
								
									
									</div>
				
				<div id="pollsWidgetFooter">
										
									</div></div>
				
				</div>
			
				
			</div>
						
		</div>
	</div>
	
	<script>
	showVotesObtainedForOpinionPoll();
		  
	function showVotesObtainedForOpinionPoll()
	{
    

    var elmt = document.getElementById("pollsWidgetBody");
	var str='';	

	
	if(${opinionPollVO.avaliability != true})
	{

		str+='<div class="opinionpoll">';
		
		str+='<div ><p class="question"><b>${opinionPollVO.quesitons[0].question}</b></p>';	
	
		str+='<div id="qstnDiv1" style="margin-top:3px;">';	

		str+='<div class="control-group form-horizontal "><p class="answer">';
		<s:iterator status="stat" value="opinionPollVO.quesitons[0].options">

		<s:if test="%{#stat.index == 0}">         

			str +='<label><input type="radio" class="radio" name="pollradio" value="<s:property value="optionId"/>" checked="true">';
	        str +="<s:property value='option'/></label>"; 
		</s:if>
		<s:else>
			str +='<label><input type="radio" class="radio"  name="pollradio" value="<s:property value="optionId"/>">';
	        str+="<s:property value='option'/></label>";
		</s:else></s:iterator>
		
		str+='</p><a href="javaScript:saveCurrentPollResult(${opinionPollVO.quesitons[0].questionId});" class="btn btn-primary votebtn" title="Click Here To Vote">Vote</a>';
		
		str+='<p class="resultdisplay" style="margin-top:10px;"><a  class="previouslink" href="javaScript:{callAjaxToGetQuestionsDetails(\'vote\',${opinionPollVO.quesitons[0].questionId});}" title="Click Here To See This Poll Result">View Results</a>';

		    
		str+=' <a class="nextlink" href="completeResultForAPollAction.action?questionId=${opinionPollVO.quesitons[0].questionId}&comments=getComments" title="Click Here To See Comments On This Poll" >View Comments</a></p>';
		str+=' </div></div>';
		str+='</div>';
		str+='<div class="pager" style="margin-top:40px;">';
   
        str+='<a style="float:left;" href="completeResultForAPollAction.action?questionId=${opinionPollVO.quesitons[0].questionId}&comments=getComments"  class="btn btn-mini" title="Post Your Comment On This Poll">Post Your Comment</a>';
 
  
    str+='<a style="float:right;" class="btn btn-mini" href="getAllPollsAction.action"  title="Click Here To View Rececnt Poll Details">View Recent Polls </a>';
   
    str+='</div>';
	elmt.innerHTML = str;		
	
	}
	else
	{
	 displayPollResult('${opinionPollVO.questionsOptionsVO.questionId}');
	}
	
}

function displayPollResult(questionId){



	callAjaxToGetQuestionsDetails("",questionId);
	
}

function afterRefreshOpinionPOll()
{

	

var elmt = document.getElementById("pollsWidgetBody");
	var str = '';
	str += '<table><tr><td>';
	str += '<div id="pollQuestionDiv" > Q) '  +'${opinionPollVO.questionsOptionsVO.question}';
	str += '</div>';
	str += '</td></tr>';
	
	str += '<tr><td>';
	//str += '<img src="charts/'+myResults.imagePath+'"></img>';
	str += '</td></tr>';
	
	str += '<tr><td>';
	str += '<div id="viewPollResDiv">';
	str += '<table style="margin-left: 26px;"><tr>';
	str += '<td><div style="width:157px;"><a href="completeResultForAPollAction.action?questionId=${opinionPollVO.questionsOptionsVO.questionId}" style="cursor: pointer; color:#0C67AC; font-weight: bold; text-decoration: none; background:#e3e3e3; padding: 4px; border-radius: 5px; margin: 2px;"> View Current Poll Result</a></div>';
	str += '</td>';
	str += '<td><div style="width:94px;"><a href="getAllPollsAction.action?.action" style="text-align: right; cursor: pointer; color:#0C67AC; font-weight: bold; text-decoration: none; background:#e3e3e3; padding: 4px; border-radius: 4px;"> View All Polls</a></div>';
	str += '</td>';	
	str += '</tr></table>';
	str += '</div>';
	str += '</tr></table>';
	

	str+='<div id="pollsChart" style=" height: auto;width: 324px; overflow: hidden;"></div>';
	elmt.innerHTML = str;


var arrData = pollStatus;

	var data = new google.visualization.DataTable();
	data.addColumn('string','option');
	data.addColumn('number','votesObtained');
		
	data.addRows(arrData.length);

		for(var j=0; j<arrData.length; j++)
		{
			
			data.setValue(j,0,arrData[j].option);
			data.setValue(j,1,arrData[j].votesObtained);
			
		}
			var chart = new google.visualization.LineChart(document.getElementById('pollsChart'));
	
	chart.draw(data,{width: 300, height: 280,legend:'right', 
	legendTextStyle:{fontSize:12},title:'${opinionPollVO.questionsOptionsVO.title}',titleTextStyle:{fontName:'verdana',fontSize:9}});
	
}


function callAjaxToGetQuestionsDetails(voteStatus,questionId){
	


	var jsObj =
			{				
			   questionId:questionId,
				task:"getPollDetails"

			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getQuestionAndPercentageOfVotesForChoices.action?"+rparam;						
		callAjax(voteStatus,jsObj,url); 

}

function callAjax(voteStatus,jsObj,url)
{
	
		var callback = {			
	 	success : function( o ) {
		try
		{ 
			myResults = YAHOO.lang.JSON.parse(o.responseText);

		    
			if(jsObj.task == "getPollDetails"){				
				buildResult(voteStatus,myResults);		   
			}
		    
		}catch(e){}
	  },
			scope : this,
			failure : function( o )
			{}
		  };

		 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildResult(voteStatus,result){


	var elmt = document.getElementById("pollsWidgetBody");

	var str='';
  
	str+='<div class="opinionpoll">';

	/*str+='<h3 style="background:#0088CC;color:#fff;padding:5px;margin-left:-10px;box-shadow:3px 0px 2px #888;margin-bottom:5px;border-radius:0px 5px 5px 0px;"><i class="icon-forward icon-white" style="margin-top:3px;"></i> Opinion Poll</span></h3>';*/

	str+='<div class=""><p class="question"><b>'+result.question+'</b></p>';	


	//str+='<p class="" style="margin-bottom:3px;"><b>'+result.question+'</b></p>'; 

	str+='<div id="qstnDiv1" style="margin-top:3px;">';	


	str+='<p style="margin-bottom: 0px; padding-bottom: 0px;" class="pull-right">Total Votes  Polled:<b>'+result.totalVotesObtainedForPoll+'</b></p>';

	
	str+='<ul class="span12" >';
	for(var i=0;i<result.options.length;i++){   
		
          str+='<li class="widget-block">';
		str+='<h6 style="margin:1px;">'+result.options[i].option+'</h6>';

		str+='<div>';
		str+='<div class="span9 pull-left">';

			str+='<div class="progress" style="margin:0px;">';
			  str+='<div id="option1" class="bar" style="width:'+result.options[i].percentage+'%"></div>';
			str+='</div>';							
			str+='</div>	';

			str+='<span class="label label-info m-left5">'+result.options[i].percentage+'% </span>';
	  str+= '</div>';
	   str+='</li>';
		
	}
	str+='</ul></div>';
	
	 if(voteStatus == "vote")

        str+='<a class="btn btn-primary" href="javaScript:{showVotesObtainedForOpinionPoll()}");" class="btn btn-primary" title="Click Here To Vote" style="margin:10px 0px 0px 104px;">Vote Now</a>';

		str+='</div>';
  
		//str+='</div>';

  
		
	    str+='<div class="pager" style="margin-top:25px;">';
		str+='<a href="completeResultForAPollAction.action?questionId='+result.questionId+'&comments=getComments"  id='+result.questionId+' class="btn btn-mini" style="float:left;" title="Click Here To Post Your Comment On This poll">Post Your Comment</a>';

		


		str+='<a href="getAllPollsAction.action" class="btn btn-mini" style="float:right;" title="Click Here To See Recent Poll Details">View Recent Polls</a>';
		str+='</div>';
	
	

	elmt.innerHTML=str;

}

function saveCurrentPollResult(questionId){

	var elmts = document.getElementsByName("pollradio");
	var checkedElmtId = '';
	
	for(var i=0; i<elmts.length;i++)
	{
		if(elmts[i].checked == true)
			checkedElmtId = elmts[i].value;
	}
	var jsObj=
	{
			questionId:questionId,
			selectedPollId:checkedElmtId,
			task:"saveSelectedPoll"					
	};
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveSelectedPoll.action?"+rparam;						
	callAjaxToSaveSelectedPollDetails(rparam,jsObj,url,questionId);	
}

function callAjaxToSaveSelectedPollDetails(param,jsObj,url,questionId){

	var myResults;
		
		var callback = {			
		               success : function( o ) 
					  {
						try {			
								if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);


									if(myResults.availability == true){

										var cssObj = {    
										'font-weight' : 'bold',
										'color' : 'green'
								      }
									   $('#alreadyVotedMsg').text("You are already voted.").css(cssObj).show().delay(2000).fadeOut(400);

										

									}							
															
								 
							displayPollResult(questionId);
													
								
						}
						catch (e)
							{   
							  	//alert("Invalid JSON result" + e);   
							}	  
			              },
			               scope : this,
			               failure : function( o ) {

			            }
			               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
}


initializeNewHomePage();
getProblemDetails();

function getProblemDetails()
{
	
	var jsObj=
		{		
			task:"getProblemDetails"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getProblemDetailsForHomePageAction.action?"+rparam+"&resultsCount=10&startIndex=0";		
		homePageProblemAjaxCall(jsObj,url);
}


function homePageProblemAjaxCall(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == 'getProblemDetails')
							{
								showFeedBackStatus(myResults);
							}		
						}
						catch(e)
						{   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}


function showFeedBackStatus(result)
{
	var str = '';
	var probEle = document.getElementById("problemsShowDIV");
	for(var i in result)
	{
		str += '<div class ="widget-block">';
				
		str+='<div style="width:50%;float:left;"><a title="Click Here To View Problem Complete details" class ="problemTitleClass" href="completeProblemDetailsAction.action?problemId='+result[i].problemId+'" >'+(result[i].problem).toUpperCase()+'</a></div>';
		
		str+='<div style="width:20%;float:left;"><div class="star"></div><input type="hidden" style="display:none;" value='+result[i].averageRating.avgRating +'" >';
		
		str+='</div>';
		
		if(result[i].url != null){
	     str += ' <div style="width:30%;float:left;">  <a title="Click Here To View  '+initialCap(result[i].problemLocation)+' '+initialCap(result[i].impactLevel)+' Details, Election Results and Different Parties Performances" href="'+result[i].url+'" style="margin-left:16px;"> '+initialCap(result[i].problemLocation)+'</a> </div>&nbsp;&nbsp;&nbsp;&nbsp;';
	   }else{
	     str += '   <div style="width:30%"><table><tr><td> '+initialCap(result[i].problemLocation)+'&nbsp;&nbsp;&nbsp;&nbsp;</td></tr></table></div>';
	   }
		
		str += '</div>';
	}
	probEle.innerHTML = str;

}

function initialCap(data) {
   data = data.substr(0, 1).toUpperCase() + data.substr(1).toLowerCase();
   return data;
}

function showNotLogIn()
{
   document.getElementById("logInDiv").style.display='block';
			var str='';
		$("#logInDiv").dialog({ stack: false,
									height: 'auto',
									width: 500,
									position:'center',								
									modal: true,
									title:'<font color="#000">ALERT</font>',
									overlay: { opacity: 0.5, background: 'black'},
									
							});
		str+='<div class="container"><h4><div style="margin: 10px;color:ActiveCaption;">Only Registered Users Can Post Problems.</div></h4><h5 style="color:#000;display:inline;position:relative;top:0px;"><div style="margin: 10px;"> Already a member ,   Click here to <a style="color:red;" href="loginInputAction.action">Login</a></div><div style="margin-left:160px;">(OR)</div><div style="margin: 10px;margin-top:-20px;">Not a member, Click here for <a style="color: Red; width: 114px; height: 8px;" href="freeUserRegistration.action"> FREE REGISTRATION <span style="margin-bottom: 20px;"><img src="images/specialPage/freeUser.png"></span></a></div></h5></div>';
		document.getElementById("logInDiv").innerHTML = str;
   

}
var uname = '${sessionScope.USER.userName}';
var emailId='${sessionScope.USER.email}';
var changedUserName='${sessionScope.changedUserName}';
var loadingFirstTime = '${sessionScope.loadingFirstTime}';


function viewParty(value){
	
}



$(document).ready(function(){


 $('#my-jqCarousel-3').jqCarousel({
				itemsPerPage: 3,
				itemsPerTransition: 3,
				easing: 'linear',
				noOfRows: 1
			});
			
$('#my-jqCarousel-news').jqCarousel();

$('#my-jqCarousel-1').jqCarousel();

$(".news-paginate .prev-sl").click(function(){

$("#my-jqCarousel-news .prev").trigger("click");
});

$(".news-paginate .next-sl").click(function(){

$("#my-jqCarousel-news .next").trigger("click");
});
			
	setTimeout("applyRaty()",1000);

});


function applyRaty(){
$('.star').each(function(){
var rating=0;
if($(this).next().val()!="null"){rating= $(this).next().val();}
	$(this).raty({
				half       : true,
	     		precision  : true,
				readOnly	: true,
				score		:rating
				});
});
}

 /* Stop page jumping when links are pressed  */
    $('a[href="#"]').live("click", function(e) {
         return false; // prevent default click action from happening!
         e.preventDefault(); // same thing as above
    });
</script>
<!--<script type='text/javascript'>


</script>
<script type="text/javascript">
   
</script>-->