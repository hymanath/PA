		<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<%@taglib prefix="s" uri="/struts-tags" %>
		<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ page import="java.util.ResourceBundle;" %>
		<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<title>Party Analyst - An Election Analysis &amp; Management Platform</title>
			<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />

			<meta http-equiv="Content-Language" content="en" />

			<meta name="description" content="Party Analyst is a complete Indian Election Analysis Platform that also offers, Constituency, Cadre Management to Indian Political Parties and Politicians." />

			<meta name="keywords" content="Indian Elections, Election Analysis, Indian Democracy, Andhra Pradesh Politics, Indian Political Parties, Indian Politicians, Indian Leaders, Congress, BJP, TDP, TRS, Indian Election Commission, Know Analyze, Act, MLA Elections, MP Elections, Cross Voting, District Election Results, MPTC Elections, ZPTC Elections, Constituency Management, Cadre Management, Party Performance, Election Comparison, Municipal Elections, Corporation Elections" />

			<meta name="copyright" content="IT Grids (India) Pvt. Ltd." />

			<meta name="author" content="Ashok Dakavaram" />

			<meta name="email" content="a.dakavaram@partyanalyst.com" />

			<meta name="Charset" content="ISO-8859-1" />

			<meta name="Distribution" content="Global" />

			<meta name="Rating" content="General" />

			<meta name="ROBOTS" content="INDEX,FOLLOW" />

			<meta name="Revisit-after" content="1 Day" />

			<meta name="expires" content="Never" />
		
		
		<!--<link type="text/css" href="styles/assets/css/bootstrap-responsive.min.css" rel="stylesheet" />-->
		 		 
		<link rel="stylesheet" type="text/css" href="style.css"/> 
		<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
		<link type="text/css" href="styles/bootstrapInHome/bootstrap-responsive.min.css" rel="stylesheet" />
		<link href="styles/rating/jquery.rating.css" rel="stylesheet" type="text/css">
		<link href="styles/newhome_inner_styles.css" rel="stylesheet" type="text/css" />
		<script src="js/rating/jquery.MetaData.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/connectPeople/connectPeople.js"></script> 
			
	      <script src="js/slides.min.jquery.js"></script>
		  <script type="text/javascript" src="js/jquery.carousel.js"></script>
		  <script type="text/javascript" src="js/homePage/newHomePage.js"> </script>
		  <script type="text/javascript" src="js/homePage/newHomePage_inner.js"> </script>
		  
		   <script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
		  <!-- 	<script type="text/javascript" src="js/bootstrapInHome/bootstrap-transition.js"></script> -->
			<script type="text/javascript" src="js/raty/js/jquery.raty.min.js"></script>
	       <link media="screen" type="text/css" href="http://fonts.googleapis.com/css?family=Abel&amp;ver=3.4.2" id="abel_google-fonts-css" rel="stylesheet">
		   
		   	<link rel="stylesheet" type="text/css" href="styles/engine3/style.css" />
			
		</head>
		<style>
		

		#menu ul.menu li.active a {
			background-position: left 0px;
		}
		#menu ul.menu li.active a span {
			color: #E8F3F7;
			background-position: right -27px;
		}
		.container{width:960px;}
		.p-m-bottom0px{padding-bottom:0px;margin-bottom:0px;}
		h4{font-size:13.5px;}
		h5{font-size:12px;}
	.votersguide {display:inline-block;padding:0px;position:relative;}
	.votersguide .btn{ bottom: 5px;right: 10px;position: absolute;}
 	.explore .p-5px{display:inline-block;padding:5px;border:0px;padding-bottom: 9px;}
	.explore h4{ float: left;margin-left: 5px;width: 56%;margin-top:22px;}
 	.explore h6{clear:both;text-align:center;padding-top:18px;}
	.voterspulse-home{margin-top:10px;}
	.voterspulse-home h3{margin-top: 0px; margin-bottom: 0px;font-size:20px;}
	.opinionpoll .breadcrumb{margin:0px -20px;display:inline-block;}
	.follow-us{margin-left:170px;margin-top:10px;}
	.selectBoxWidth{width:168px;}
	.v-gallary .thumbnail{display:inline-block;float:left;}
	.v-gallary ul li{border:1px solid #EAEAEA;}
	#problemsShowDIV h6{color:#005580;margin-bottom:10px;}
	#problemsShowDIV p{margin-top:10px;}
	#problemsShowDIV .widget-block{display:inline-block;width:100%;}
	#my-jqCarousel-3 .mask li:hover{background:#fff;}
	#icon_leftsec{margin-right:5px;}
    .star{margin-top:-3px;}
	.voters-pulse-home{position:relative;}
	.voters-pulse-home .btn{position:absolute;right:5px;bottom:5px;}
	#my-jqCarousel-news{background:#fafafa;height:160px;}
	.widget .widget-block {padding-bottom: 20px;}
	.btn-group{margin-top: 10px;}
	.problemSearchBtn{background-color: #5BB75B;background-image:linear-gradient(to bottom,#62C462, #51A351);
    background-repeat: repeat-x;
    border-color:rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
    color: #FFFFFF;padding: 6px 10px; border-radius:5px; cursor: pointer; border: medium none; float: right; clear: both; margin-right: 12px;}

#searchAjaxImgSpan{float:right;margin-top:-33px;margin-right: -12px;display:none;}
#problemErrorMsgDiv{margin-top: 17px;font-size:13px;}
#my-jqCarousel-3 .pagination-links{margin-top:5px;}
#my-jqCarousel-3{margin-top:5px;}


#pollsWidgetBody {
    border:none;
   overflow: visible;
    padding:0px;
}

.newLink{color:#000000;}
.newLink:hover{color:#000000;text-decoration:none;}

			</style>
<div class="container m-top15">
		<div class="row-fluid">
			
		<!--------left div------->
			<div class="span3">
			<!-------Quick Links Block--------------->
			<div class="widget blue votersguide ">
			                  
								<img src="images/voters_guide.gif" alt="voters guide Image">
								<a href="voters.action" class="btn btn-small btn-primary" >Voter's Guide</a>
								
							</div>
			
		<div class="widget blue quicklinks"><h2><span><i class="icon-random "id="icon_leftsec"></i></span>
Quick Links</h2>
				
				<!---- View Election Results------->
							<div class="widget-block" contentindex="4c">
								<h5> View Election Results</h5>
								<p>You can view your Assembly or Parliment past Election Results.</p>
								<table>
									<tbody>
									<tr>
										<td width="65%"><div id="electionDetailsErrorMsgDiv" style="display:none;"><font color="red"><b>*Select All Inputs</b></font></div></td>
									</tr>
										<tr>
											<td>
												<select id="electionTypeId" name="electionType"  cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;width: 168px;margin-bottom:5px;" autoComplete="off"
		                                          onchange="checkElectionType(this.options[this.selectedIndex].value)">
		                                               <option value="0">Select Type</option>
		                                               <option value="2">Assembly</option>
		                                               <option value="1">Parliament</option>
		                                       </select>
											</td>
										</tr>
										<tr>
											<td>
												<select id="states" name="state_s" cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;width:168px;margin-bottom:5px;"onchange="getElectionYearsInHomePage('Assembly')">
		                                       	</select>
											</td>
										</tr>
										<tr>
											<td>
                                                  <select id="electionYears" cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;width:168px;">
			                                      </select>
											</td>
										</tr>
									</tbody>
								</table>
								
								    <button class="btn btn-success pull-right" type="button" onclick="viewElectionResults()">Go</button>
								
							</div>
					<!---------------->
		
							<!------View Your State---------->
							<div class="widget-block" contentindex="0c" >
								<h5>View Your State</h5>
								<p>Select your state to view its Assembly, Parliament, Local Bodies election results.</p>
								<s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="statesList" listKey="id" listValue="name" onchange="setStateValue()"/>
								
								<button class="btn btn-success clear-both pull-right" type="button" onclick="navigateToStatePage()">Go</button>
										
							</div>
						<!----View Your district------>
							<div class="widget-block" contentindex="1c">
							<div id="alertMessage_district" style="color:red;font-weight:bold;"></div>
								     <h5>View Your District</h5>
									 <p>Select your district to view its election results in district level.</p>
									 <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state" id="stateList_d" list="statesList" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtList_d')"></s:select>
                                     <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district" id="districtList_d" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select District"/>
									     <br>
									   <a onclick="navigateToDistrictPage()" href="javascript:{}">
									     <button class="btn btn-success pull-right" type="button">Go</button>
									   </a>
								    
							</div>
						<!--View your constituency-->
							<div class="widget-block" contentindex="2c" >
							<div id="alertMessage" style="color:red;font-weight:bold;"></div>
								<h5>View Your Constituency</h5>
								<p>Select Constituency Type<br>
								<label class="radio">
										<input type="radio" onclick="hideUnhideSelectBox(this.id, 'constituency')" id="assembly_radio" name="assembly_radio" checked="checked">
											Assembly
											</label>
											<label class="radio">
										<input type="radio" onclick="hideUnhideSelectBox(this.id,'constituency')" id="p_radio" name="assembly_radio" >
											Parliament
								      </label>
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
								
								<button class="btn btn-success pull-right" type="button" onclick="navigateToConstituencyPage()">Go</button>
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
								  
								<button class="btn btn-success pull-right" type="button" onclick="navigateToLocalBodyPage()">Go</button>
								  
							</div>	
							
			</div>
			
				<div class="widget blue party-block" >
							 <h2> <i class="icon-flag "id="icon_leftsec"></i>  Political Party</h2>
							  <p>You can view your favourite Party updates through Party Analyst with a single click. </p>
							    
										<ul class="nav nav-tabs nav-stacked" role="menu" aria-labelledby="dLabel" id="viewYourParty">
											<li><a tabindex="-1" href="partyPageAction.action?partyId=362"><img src="images/party_flags/INC.png" class="thumbnail" alt="INC Party" />  <h6>Indian National Congress [INC]</h6></a></li>
										
											<li><a tabindex="-1" href="partyPageAction.action?partyId=872"><img src="images/party_flags/TDP.png" class="thumbnail"  alt="TDP Party" /><h6> Telugu Desam Party [TDP]</h6></a></li>	
											
											<li><a tabindex="-1" href="partyPageAction.action?partyId=1117"><img src="images/party_flags/YSRC.png" class="thumbnail"  alt="YSRC Party" /> <h6>Yuvajana Sramika Raithu Congress [YSRC]</h6></a></li>
								
											<li><a tabindex="-1" href="partyPageAction.action?partyId=163"><img src="images/party_flags/BJP.png" class="thumbnail" alt="BJP Party" /><h6> Bharath Janathadal Party [BJP]</h6></a></li>
											
											<li><a tabindex="-1" href="partyPageAction.action?partyId=886"><img src="images/party_flags/TRS.png" class="thumbnail"  alt="TRS Party" /> <h6>Telangana Rastra Samithi [TRS]</h6></a></li>
											
											<li><a tabindex="-1" href="partyPageAction.action?partyId=839"><img src="images/party_flags/SP.png" class="thumbnail"  alt="SP Party" /> <h6>Samajwadi Party [SP]</h6></a></li>
											
											<li><a tabindex="-1" href="partyPageAction.action?partyId=239"><img src="images/party_flags/BSP.png" class="thumbnail"  alt="BSP Party" /> <h6>Bahujan Samaj Party [BSP]</h6></a></li>
										
											<li><a tabindex="-1" href="partyPageAction.action?partyId=76"><img src="images/party_flags/AITC.png" class="thumbnail"  alt="AITC Party" /><h6>All India Trinamool Congress [AITC]</h6></a></li>
									
											<li><a tabindex="-1" href="partyPageAction.action?partyId=579"><img src="images/party_flags/NCP.png" class="thumbnail" alt="NCP Party" /> <h6>Nationalist Congress Party [NCP]</h6></a></li>
										</ul>
								
							</div>			
			
	</div>
			
		<!--------Center div------>
			<div class="span6">
			
			
			
				<!-----NEWS Block-------->
				<div class="widget green news-gallery">
				 	<!-- <h2>Latest Political News</h2>
			sasi news from database start-->
							
				<div id="my-jqCarousel-news" class="jqCarousel module widget-block" >
		    <ul class="overview media">
							<s:if test="resultMap != null && resultMap.size() > 0"> 
							<s:iterator value="resultMap.NewsGallary" var="newsGallaryDetails">
								<li>
									<a href="#" class="newLink"><h4 class="headMore" title='<s:property value="fileTitle1"/>'><s:property value="fileTitle1"/></h4></a>
									<div class="row-fluid m-l15px" style="position:relative;">
									<s:if test="%{#newsGallaryDetails.fileType == 'Candidate'}" >
										<!--<a class="thumbnail span4" href="#">
											<img src="http://placehold.it/200x150" style="width:100%">
										</a>-->
										
										<c:if test="${newsGallaryDetails.displayImagePath != null}">
											<a class="thumbnail span4" style="height:120px;"  href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img style="float:left;width:150px;height:110px;" src="${newsGallaryDetails.displayImagePath}" alt='<s:property value="fileTitle1"/> Image'/></a>
											</c:if>

										<c:if test="${newsGallaryDetails.displayImagePath == null}">
											<a class="thumbnail span4" style="height:120px;"  href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img style="float:left;width:150px;height:110px;" src="./images/candidates/${newsGallaryDetails.imagePathInUpperCase}.jpg" alt='<s:property value="fileTitle1"/> Image'/></a>
										</c:if>
										
										<p class="span6 more"><s:property value="description"/></p>
										<a class="readMoreHref" href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' class="pull-right round-link" style="position:absolute;bottom:5px;right:20px;" >
									</s:if>
									
									<s:if test="%{#newsGallaryDetails.fileType == 'Party'}" >
										 <c:if test="${newsGallaryDetails.displayImagePath != null}">
											<a class="thumbnail span4" style="height:120px;" href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img style="float:left;width:150px;height:110px;" src="${newsGallaryDetails.displayImagePath}" alt='<s:property value="fileTitle1"/> Image'/></a>
										</c:if>

										<c:if test="${newsGallaryDetails.displayImagePath == null}">
											<a class="thumbnail span4" style="height:120px;" href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img style="float:left;width:150px;height:110px;" src="./images/party_flags/${newsGallaryDetails.imagePathInUpperCase}" alt='<s:property value="fileTitle1"/> Image'/></a>
										</c:if>
										<p class="span6 more"><s:property value="description"/></p>
										<a class="readMoreHref" href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' class="pull-right round-link" style="position:absolute;bottom:5px;right:20px;">
									</s:if>
									
									<s:if test="%{#newsGallaryDetails.fileType == 'Special Page'}" >
										<c:if test="${newsGallaryDetails.displayImagePath != null}">
											<a class="thumbnail span4" style="height:120px;" href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img style="float:left;width:150px;height:110px;" src="${newsGallaryDetails.displayImagePath}" alt='<s:property value="fileTitle1"/> Image'/></a>
										</c:if>
										<c:if test="${newsGallaryDetails.displayImagePath == null}">
											<a class="thumbnail span4" style="height:120px;" href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img style="float:left;width:150px;height:110px;" src="${newsGallaryDetails.imagePathInUpperCase}" alt='<s:property value="fileTitle1"/> Image'/></a>
										</c:if>
										<p class="span6 more" ><s:property value="description"/></p>
										<a class="readMoreHref" href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' style="position:absolute;bottom:5px;right:20px;" class="pull-left round-link">
									</s:if>
									Read Now &rarr;
									</a>
									<c:if test="${newsGallaryDetails.source != null}">
									<div style="color:#0088CC;clear:both;margin-left:11px;margin-top:15px;">Source : ${newsGallaryDetails.source}</div>
									</c:if>
									</div>
									
								</li>
							</s:iterator>
							</s:if>
						</ul>
						
				<div class="news-paginate">
              
                <span class="prev-sl"><i class="icon-chevron-left icon-white"></i></span>
                <span class="next-sl"><i class="icon-chevron-right icon-white"></i></span>
             
            </div>
		</div>
	
				</div>	
				<!-- END OF NEWS BLOCK -->
				<!--sasi photo gallary start-->
				<div class="widget blue" style="padding-bottom:2px;">
			        <h2><i class="icon-camera "id="icon_leftsec"></i>
						Latest Photo Galleries</h2>
					<div class="widget-block" style="border:none;"> 
					<div id="wowslider-container3">
		<div class="ws_images">
			<ul>
		    <s:if test="resultMap != null && resultMap.size() > 0">
			  <s:iterator value="resultMap.photogallary" var="photogallaryDetails">
			   
			    
			      <li>
			        <s:if test="%{#photogallaryDetails.fileType == 'Candidate'}" >         
					  <a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' style="width:320px;">
			        </s:if>
					<s:if test="%{#photogallaryDetails.fileType == 'Party'}" >
					  <a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'  style="width:320px;">
			        </s:if>
					<s:if test="%{#photogallaryDetails.fileType == 'Special Page'}" >
					  <a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'  style="width:320px;">
			        </s:if>
		            <img src='<s:property value="pathOfFile"/>' width="320px" style="width:320px;height:240px;" alt='<s:property value="fileTitle1"/>' title='<s:property value="description"/>'/></a>			         
				  </li>
			    
			  </s:iterator>
			 </s:if>
			 </ul>
		</div>
		<div class="ws_thumbs">
			<div>
			 <s:if test="resultMap != null && resultMap.size() > 0">
			  <s:iterator value="resultMap.photogallary" var="photogallaryDetails">
				<a href="#">
		            <img src='<s:property value="pathOfFile"/>' style="width:96px;height:72px;" alt='<s:property value="fileTitle1"/>' title='<s:property value="description"/>'/></a>			         
				 

			    </s:iterator>
			 </s:if>
			
			</div>
		</div>
		
			<div class="ws_shadow"></div>
</div>
	
				
		
		</div>
					
				</div>
			
			<!--sasi photo gallary END-->
				<div class="widget yellow" style="padding-bottom:5px;">
				<h2><span><i class="icon-facetime-video icon-black"id="icon_leftsec"></i><span>
				Latest Video Galleries</h2>
				<!--video gallary-->
				<div id="my-jqCarousel-3" class="jqCarousel module v-gallary">
		    <ul>
					<s:if test="resultMap != null && resultMap.size() > 0">
					<s:iterator value="resultMap.VideoGallary" var="videoGallaryDetails">

					<li class="" style="height:175px;">
					
					<s:if test="%{#videoGallaryDetails.fileType == 'Candidate'}" >
					<a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>'  >
					</s:if>
					<s:if test="%{#videoGallaryDetails.fileType == 'Party'}" >
					<a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' >
					</s:if>
					<s:if test="%{#videoGallaryDetails.fileType == 'Special Page'}" >
					<a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' >
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
					
					<!--<p class="caption"><s:property value="gallaryName"/></p>-->
					<p class="caption"><s:property value="fileTitle1"/></p>
					</a>
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
				  <h2><span><i class="icon-map-marker
 "id="icon_leftsec"></i><span>Your Locality Problems</h2>
				  <h5>New or Existing - Our Platform elevates your problems to the external world. </h5>
				<div class="pager widget-block"> 
					
							<a href="completeProblemDetailsSearchAction.action" class="btn btn-info">View All Problems</a>
			
								<c:if test="${sessionScope.hasFreeUserRole != true && sessionScope.hasPartyAnalystUserRole != true}"> 
									<a href="javascript:{}" onclick="showNotLogIn();" class="btn btn-info" >Post Your Problems</a>
								</c:if>
								<c:if test="${sessionScope.hasFreeUserRole == true && sessionScope.hasPartyAnalystUserRole != true}">
									<a href="javascript:{}" onclick="openAddNewProblemWindowForDashBoard();" class="btn btn-info" style="margin:5px;">Post Your Problems</a>
								</c:if>
								
								<div id="problemErrorMsgDiv"></div>
								
								<div style="margin-top: 10px;">
								<span style="font-size: 13px;">To Quick View the Problem, Enter the Ref Id :</span>
								<input type="text" style="width: 80px;" size="10" id="problemReferenceId">
								<input type="button" class="problemSearchBtn" onclick="getProblemReferenceId()" value="Go">
								<span id="searchAjaxImgSpan"><img src="images/icons/search.gif"  width="18px" height="18px;" style="margin-left: 35px;"></img></span>
							</div>

				</div>
					<div id="logInDiv"></div>
					<div id="problemsShowDIV"></div>
				</div>
				
				<div class="widget blue">
					<h2><span><i class="icon-bookmark "id="icon_leftsec"></i><span>
 Tweets From Parties and Leaders </h2>
						<div class="widget-block">
							<jsp:include page="newsocialtwitter.jsp" /> 
						</div>
				</div>
				<!-- Close Center DIV -->			
			</div>
		<!--------Right div------>
			<div class="span4">
			
				<div class="widget yellow explore-info">
				<div class="widget-block" style="padding: 0px; display: inline-block; margin-top: 0px; margin-bottom: 0px;background:#fafafa;">
				<img src="images/RightPlace.gif"><a href="viewFeaturesAction.action" class="btn btn-small btn-warning pull-right inline" style="  bottom: 6px;position: absolute; right: 10px;" alt="Explore About PartyAnalyst Image">Explore!</a>
				
				</div></div>
				
				<div class="widget green voters-pulse-home">
				<div class="widget-block" style="padding: 0px; display: inline-block; margin-top: 0px; margin-bottom: 0px;background:#fafafa;">
				<img src="images/voters_pulse.gif" alt="voters pulse Image">
				<a class="btn btn-success btn-small" href="VotersPulse.action">View Now</a></div>
				</div>
				<div class="widget red explore-info">
				<div class="widget-block" style="padding: 0px; display: inline-block; margin-top: 0px; margin-bottom: 0px;background:#fafafa;">
				<img src="images/RIT_Act_Tham.gif" style="opacity: 0.8;"><a href="righttoinformation.action" class="btn btn-small btn-primary pull-right inline" style="  bottom: 6px;position: absolute; right: 10px;" alt="Right To Information  Image">R T I</a>
				
				</div></div>
					<div class="widget blue videogallery">
				<h2 style="font-size:14px;">PartyAnalyst-2.0 Services</h2>
				<a  href="specialPageAction.action?specialPageId=1&contentId=31753" rel="KeN4RHwpVAc" >
				<img width="236px;" class="thumbnail" style="cursor: pointer; border-radius: 5px 5px 5px 5px; left: 13px; border: 1px solid #ccc;margin-top:7px; " title="Explaining the Indian Election System " src="http://img.youtube.com/vi/KeN4RHwpVAc/1.jpg"></a>
				</div> 
				
				<!-----------Hot Topics--------------->
				<div class="widget red hottopics">
					<h2><span><i class="icon-fire  "id="icon_leftsec"></i><span>
Hot Topics</h2>
					<c:forEach var="specialPages" items="${specialPageVOList}">
					<div class="media media_hr widget-block">
						
						<div class="media-body">
							<h5>${specialPages.title}</h5>
								
								<a  href="specialPageAction.action?specialPageId=${specialPages.specialPageId}"><img src="${specialPages.eventImagePath}" width="200px"height="85px" style="margin-bottom:12px; " alt="${specialPages.title} Image"/></a>
								<p class="hottopics_desc">
									${specialPages.description}
								</p>
						</div>
						<a href="specialPageAction.action?specialPageId=${specialPages.specialPageId}" class="btn-info btn-small pull-right" title="${specialPages.title}">View Now</a>
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

				<a href="specialPageInfoAction.action" class="btn btn-primary" >View More Special Pages</a>

				</div>
				<!-----------Opinion Poll--------------->
				<div class="widget blue" style="padding-bottom:0px;">
					<h2><span><i class="icon-signal "id="icon_leftsec"></i><span>
Opinion Poll</h2>
				<div class="widget-block">
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
	constituencyId = '${sessionScope.USER.constituencyId}';
	showVotesObtainedForOpinionPoll();
		  
	function showVotesObtainedForOpinionPoll()
	{
    var elmt = document.getElementById("pollsWidgetBody");
	var str='';	
	
	if(${opinionPollVO.avaliability != true})
	{
		
		str+='<div class="opinionpoll">';
		
		str+='<div class="breadcrumb"><p class="question"><b>${opinionPollVO.quesitons[0].question}</b></p>';	
	
		str+='<div id="qstnDiv1" style="margin:0px-20px;margin-top:3px">';	

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
		
		str+='<p class="resultdisplay" style="margin-top:10px;"><a  class="previouslink" href="javaScript:{callAjaxToGetQuestionsDetails(\'vote\',${opinionPollVO.quesitons[0].questionId});}" style="margin-bottom:10px;"title="Click Here To See This Poll Result">View Results</a>';

		    
		str+=' <a class="nextlink" style="margin-bottom:10px;"  href="completeResultForAPollAction.action?questionId=${opinionPollVO.quesitons[0].questionId}&comments=getComments" title="Click Here To See Comments On This Poll" >View Comments</a></p>';
		str+=' </div></div>';
		str+='</div>';
		str+='<div class="widget-block" style="padding:2px;height:30px;border:0px;">';
   
        str+='<a style="float:left;margin-left:3px;" href="completeResultForAPollAction.action?questionId=${opinionPollVO.quesitons[0].questionId}&comments=getComments"  class="btn btn-mini" title="Post Your Comment On This Poll">Post Your Comment</a>';
 
  
    str+='<a style="float:right;margin-right:3px;" class="btn btn-mini" href="getAllPollsAction.action"  title="Click Here To View Rececnt Poll Details">View Recent Polls </a>';
   
    str+='</div>';
	elmt.innerHTML = str;		
	
	}
	else
	{
	 displayPollResult('${opinionPollVO.questionsOptionsVO.questionId}');
	}
	
}




initializeNewHomePage();
getProblemDetails();



 
 $(document).ready(function() {
    
    var ellipsestext = "...";
	var ellipsetext = ".."
	
    $('#my-jqCarousel-news .more').each(function() {
		var showChar = 170;
        var content = $(this).html();
 
        if(content.length > showChar) {
 
            var c = content.substr(0, showChar);
            var h = content.substr(showChar-1, content.length - showChar);
			var html = c + ellipsestext;
 
            $(this).html(html);
        }
 
    });   
	
	$('#my-jqCarousel-news .readMoreHref').each(function(index,value) {
		$('#my-jqCarousel-news .newLink').eq(index).attr('href',$(this).attr('href'));
	});
	
	$('#my-jqCarousel-news .headMore').each(function(index,value) {
	 	var showChar = 50;
        var content = $(this).html();
        if(content.length > showChar) {
 
            var c = content.substr(0, showChar);
            var h = content.substr(showChar-1, content.length - showChar);
			var html = c + ellipsetext;
 
            $(this).html(html);
        }
 
    }); 
	
	
	 $('#my-jqCarousel-3 .caption').each(function() {
		
		var showChar=50;
        var content = $(this).html();
	    if(content.length >showChar) {
 
            var c = content.substr(0, showChar);
			var h = content.substr(showChar-1, content.length - showChar);
 
            var html = c + ellipsestext;
 
            $(this).html(html);
        }
 
    });  
	
	$('.hottopics .hottopics_desc').each(function() {
		var showChar=170;
        var content = $(this).html();
 
        if(content.length > showChar) {
 
            var c = content.substr(0, showChar);
            var h = content.substr(showChar-1, content.length - showChar);
 
            var html = c + ellipsestext;
 
            $(this).html(html);
        }
 
    });  
	
	});
	
	
$(document).ready(function(){
	$(".ws_images li").each(function(i){
	 var ids="wows3_"+i;
	  $(this).find("a").attr("id",ids);
	});
	$('#assembly_radio').attr('checked','checked');
	
	
	});
	hideUnhideSelectBox('assembly_radio', 'constituency')
</script>
	<script type="text/javascript" src="styles/engine3/wowslider.js"></script>
	<script type="text/javascript" src="styles/engine3/script.js"></script>
