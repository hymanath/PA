<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>News Analysis - Telugudesham Party</title>
	<meta name="" content="">
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="css/style.css">
	<!-------PT-sans font---->
	<link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
	
	<style>
	.content_widget {background-color: #FFFFFF; border: 1px solid #E5E5E5; height: 100%; padding: 10px;}
	.m_topN21{margin-top:-21px;}
	.m_left0{margin-left: 0px !important;}
	
	.ui-multiselect {
    padding: 2px 0 2px 4px;
    text-align: left;
    width: 250px !important;
	height:33px !important;
	}
	
	.ui-multiselect-menu {
    display: none;
    padding: 3px;
    position: absolute;
    text-align: left;
    z-index: 10000;
	}
	
	.badge-50 , .label-50{
		background-color: rgba(158, 158, 158, 0.5);
	}
	</style>
	
</head>
<body>
		<!----Header DIV------>
		<!--<div class="container-fluid headerBg" style="padding-left: 0px; padding-right: 0px;">
		<!---Header----->
		<div class="container">	
		<div class="row">
			<!----Logo----->
			<!--<div class="span12">
				<img src="images/logo.png" alt="Telugudhesham party logo" />		
			</div>
			
			
		</div>
		</div>
		</div>
		-->
		<!----Container---->
		<div class="container">
			<!--------- Row-1 -------->
			<div class="row-fluid">
				<div class="span12 content_widget">
				<legend class="boxHeading ">News Analysis by Candidate Name / Party</legend>
				
					<div class="row-fluid">
						<div class="span6">
							<label class="help-inline"><strong>Select From Date :</strong></label>
							<input type="text"  id="fromDateId" readonly="readonly">						
						</div>
						<div class="span6">
							<label class="help-inline"><strong>Select To Date :</strong></label>
							<input type="text" id="todateId" readonly="readonly">						
						</div>
					</div>
					
					<!------Who Div---->
					<div class="row-fluid label label-50">
						<div class="span1 btn-block btn btn-large">
							<h4 class=" text-center ">Who</h4>					
						</div>
						<div class="span4">
							<label>Select Party Name</label>
							<select class="input-block-level" id="partyList"onchange="getCandidatesOfSelectedParty(this.value,'candidateId');">	
								<option value=0>Select Party</option>
							</select>							
						</div>
						<div class="span4">
							<label>Selecte Candidate Name</label>
							<select class="input-block-level" id="candidateId">	
								<option value=0>Select Candidate</option>
							</select>						
						</div>
						<div class="span3">
							<label>Select Benifits</label>
							<select class="input-block-level" id="benifitsList">	
								<option value=0>Select Benifits</option>
							</select>					
						</div>
					</div><!------Who Div END---->
					
					<!------Whom Div---->
					<div class="row-fluid label label-50  m_top10">
						<div class="span1 btn-block btn btn-large">
							<h4 class=" text-center ">Whom</h4>					
						</div>
						<div class="span4">
							<label>Select Party Name</label>
							<select class="input-block-level" id="whomPartysList" onchange="getCandidatesOfSelectedParty(this.value,'whomCandidatesList');">	
								<option value=0>Select Party</option>
							</select>						
						</div>
						<div class="span4">
							<label>Selecte Candidate Name</label>
							<select class="input-block-level" id="whomCandidatesList">	
								<option value=0>Select Candidate</option>
							</select>						
						</div>
						<div class="span3">
							<label>Select Benifits</label>
							<select class="input-block-level" id="whomBenfitsList">	
								<option value=0>Select Benifits</option>
							</select>						
						</div>
					</div><!------Whom Div END---->
					
					<label class="checkbox inline">
						<input type="checkbox" value="option1" id="keywordscheckId" onClick="unCheckCandidateCheckBox();"> <strong>Search By Keywords</strong>
					</label>	
					<label class="checkbox inline">
						<input type="checkbox" value="option1" id="candidateCheckId" onClick="unCheckKeywordsCheckBox();" style=""><strong>Search By Categoery</strong>
					</label>	
					
					
					<div class="row-fluid">
						<div class="span4" id="keywordsDiv" style="display:none;">
							<label>Select Key Word</label>
							<select Class="input-block-level" id="keywordsList"  readonly="readonly"></select>
												
						</div>
					</div>
					
					<div class="row-fluid m_top10 " >
						
						
						
						<div class="span4">
							<label>Select News Source</label>
							<select  id="newsSourceId"></select>
												
						</div>
					
					
					
						
						<div class="span4">
							<label>Select News Level</label>
							<select id="locationLevelId" onChange="getRespectedLocationValues(this.value);">
							<option value="0">Select Location</option>
							<option value="1">District</option>
							<option value="2">PARLIAMENT CONSTITUENCY</option>
							<option value="3">ASSEMBLY CONSTITUENCY</option>
							</select>
												
						</div>
						
						<div class="span4">
							
							<div  class="LocationLevelId">
							<label>Select Location</label>
							<select>
							<option>Select Location</option>
							</select>
							</div>
							
							<div style="display:none;" class="districtSelReport"><label>Select District</label>
							<s:select name="districtSelReport" id="districtSelReportId" list="districtsList" theme="simple" listKey="id" listValue="name" headervalue="Select District"/></div>
							<div style="display:none;" class="parliamSelReport"><label>Select Constituency</label>
							<s:select name="parliamSelReport" id="parliamSelReportId" list="parlConstiList" theme="simple" listKey="id" listValue="name"/>
							</div>
							<div style="display:none;" class="assembSelReport"><label>Select Constituency</label>
							<s:select name="assembSelReport" id="assembSelReportId" list="assemConstiList" theme="simple" listKey="id" listValue="name"/>
							</div>
												
						</div>
						
						<!--<div class="span4">
							<label>Select News Location</label>
							<select></select>
												
						</div>-->
				
					</div>
					<!-------Submit Button------>
					<div class="form-actions">
						<button class="btn btn-primary" type="submit" onClick="getAnalysisData();">Submit</button>
						<button class="btn" type="button">Cancel</button>
					</div><!-------Submit Button END------>
				</div>	
			</div>
			<!--------- Row-1 End -------->
			</div><!----Container END---->
		
			<!-----Footer---->
			<!--<div class="container- fluid">
				<footer>
					<p class="text-center">&copy; Telugudesham Party 2013</p>
				</footer>
			</div>-->
		
		<!------JS------>
	<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.carousel.js"></script>
	 <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
    <script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
	
	<link  rel="stylesheet" type="text/css" href="styles/partyManagement/partyManagement.css"/>
	<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />

	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
	<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.filter.js"></script>			

	<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

	<!-- YUI Dependency files (End) -->
	
	<SCRIPT type="text/javascript" src="js/newsAnalysis/newsAnalysis.js"></SCRIPT>
</body>
</html>