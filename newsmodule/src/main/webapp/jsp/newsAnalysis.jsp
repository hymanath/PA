<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>News Analysis</title>
	<meta name="" content="">
	<!-- Bootstrap -->
	
	<!-------PT-sans font---->
	<!-- <link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'> -->
	<link  rel="stylesheet" type="text/css" href="js/jquery.google.api/googleAPIStyles.css"/>
	
	<style>
	#fromDateId,#todateId{
	cursor: pointer;
	}
	#headings{
	color:#5B5B5B;
	font-variant: small-caps; 
	text-transform: none; 
	font-weight: 500; 
	height: 20px; 
	padding: 5px; 
	width: 918px;
	margin-bottom:0px;

	}
	.content_widget {background-color: #FFFFFF; border: 1px solid #E5E5E5; height: 100%; padding: 10px;}
	.m_topN21{margin-top:-21px;}
	.m_left0{margin-left: 0px !important;}
	
	.ui-multiselect {
    padding: 2px 0 2px 4px;
    text-align: left;
    width: 293px !important;
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
	
	.label-success-50, .badge-success-50 {
    background-color: #d3d3d3 !important;
    color: #333333 !important;}
	.tabel-bordered{border-color:3px solid #ffcc00;}
	.analysisResult thead tr th{background:#ffcc00;}
	.tabel tr, .tabel td {border-top: 1px solid #FFCC00;}
	.requiredFont {
    color: #FF0000;
    font-size: 18px;
	margin-left:3px;
}
.accordion-heading{
  font-weight: bold;
}
.ui-multiselect-filter{
 width:100%;
}
ul.as-selections li {
    float: left;
     margin: 2px 6px 3px 0;
}

ul.as-selections1 li {
    float: left;
     margin: 2px 6px 3px 0;
}
.as-selection-item{
    background-color: #FFFFFF;
    border-color: #C0D9E9 #ACC3EC #ACC3EC;
    border-image: none;
    border-radius: 12px;
    border-right: 1px solid #ACC3EC;
    border-style: solid;
    border-width: 1px;
    box-shadow: 0 1px 1px #E4EDF2;
    color: #2B3840;
    font-family: "Lucida Grande",arial,sans-serif;
    font-size: 13px;
    padding: 2px 7px 2px 10px;
}
 li {list-style-type:none} 
 .as-selection-item a{
   color: #878787;
 }
 .partyCounts{
    display: inline-block;
    width: 30%;
 }
 .partyCountImg{
       margin-left: 3%;
 }
 .evenColor{
   background-color:#E9E9E9;
 }
 .oddColor{
  background-color:#f9f9f9;
 }
.accordion-inner{
 background-color: rgba(255, 255, 0, 0.1);
}
.as-close{
  margin-left: 3px;
}
	</style>
    <script type="text/javascript">
	var booleanVal = false;
	//$('#analyseCandidateDesti').attr('checked','checked');

	$("#advanceView").live("click",function(){
	$("#errormessageDiv").hide();
	     if(!booleanVal){
		   booleanVal = true;
			$('#analyseCandidateDesti').removeAttr('checked');
			$('#keywordscheckId').removeAttr('checked');
			$('#candidateCheckId').removeAttr('checked');
            $('#whomPartysList').val(0);
			$('#whomCandidatesList').val(0);
			$('#whomBenfitsList').val(0);
			$('#locationLevelId').val(0);
			$("#locationLevelId").change();
		    $("#newsSourceId").multiselect("uncheckAll"); 
			$("#advanceDiv").css("display","block");
			$('#keywordsDiv').hide();
			$('#analyseCandidateSource').attr('disabled',false);
			$('#benifitsList').attr('disabled',false);
			$("#advanceView").html('Hide Advance Search options');
		}else{
		    booleanVal = false;
			$('#analyseCandidateDesti').removeAttr('checked');
			$("#advanceDiv").css("display","none");
			$("#advanceView").html('Show Advance Search options');
			$('#analyseCandidateSource').removeAttr('checked');
            $('#analyseCandidateSource').attr('disabled',true);
            
            $('#benifitsList').attr('disabled',true);
            $('#benifitsList').val(0);
			  $('#keywordscheckId').removeAttr('checked');
			$('#candidateCheckId').removeAttr('checked');
            $('#whomPartysList').val(0);
			$('#whomCandidatesList').val(0);
			$('#whomBenfitsList').val(0);
			$('#locationLevelId').val(0);
            }
	});
	</script>
</head>
<body>

		<!----Container---->
		<div class="container">
			<!--------- Row-1 -------->
			<div class="row-fluid">
				<div class="span12 content_widget">
				<legend class="boxHeading text-center">News Analysis</legend>
				
					<div class="row-fluid">
						<div class="span6">
							<label class="help-inline"><strong>From Date :</strong></label>
							<input type="text"  id="fromDateId" readonly="readonly">
                            <span title="Clear From Date" style="cursor: pointer;" onclick="clearDate('fromDateId');" class="icon-remove-sign"></span>							
						</div>
						<div class="span6">
							<label class="help-inline"><strong>To Date :</strong></label>
							<input type="text" id="todateId" readonly="readonly">	
                            <span title="Clear To Date" style="cursor: pointer;" onclick="clearDate('todateId');" class="icon-remove-sign"></span>							
						</div>
					</div>
					
					<!------Who Div---->
					<div class="row-fluid label label-success-50">
					<div class="row-fluid">
						<!--<div class="span1 btn-block btn btn-large">
							<h4 class=" text-center ">Who</h4>					
						</div>-->
						<div class="span4">
							<label>Select Party<span class="requiredFont">*</span></label>
							<select class="input-block-level" id="partyList" onchange="getCandidatesOfSelectedParty(this.value,'candidateId');">	
								<option value=0>Select Party</option>
							</select>							
						</div>
						
						<div class="span4">
							<label>Select News Level</label>
							<select id="locationLevelId" class="input-block-level" onChange="clearAllSelVals();getRespectedLocationValues(this.value);">
							<option value="0">Select News Level</option>
							<option value="1">District</option>
							<option value="2">PARLIAMENT CONSTITUENCY</option>
							<option value="3">ASSEMBLY CONSTITUENCY</option>
							</select>
												
						</div>
						<div class="span4">
							
							<div  class="LocationLevelId">
							<label>Select Location</label>
							<select class="input-block-level">
							<option>Select Location</option>
							</select>
							</div>
							
							<div style="display:none;" class="districtSelReport"><label>Select District</label>
							<s:select name="districtSelReport" cssClass="input-block-level" id="districtSelReportId" list="districtsList" theme="simple" listKey="id" listValue="name" headervalue="Select District"/></div>
							<div style="display:none;" class="parliamSelReport"><label>Select Constituency</label>
							<s:select name="parliamSelReport" cssClass="input-block-level" id="parliamSelReportId" list="parlConstiList" theme="simple" listKey="id" listValue="name"/>
							</div>
							<div style="display:none;" class="assembSelReport"><label>Select Constituency</label>
							<s:select name="assembSelReport" cssClass="input-block-level" id="assembSelReportId" list="assemConstiList" theme="simple" listKey="id" listValue="name"/>
							</div>
												
						</div>
						
						<!--<div class="span3">
							<label>Benifits</label>
							<select class="input-block-level" id="benifitsList" disabled="true">	
								<option value=0>Select Benifits</option>
							</select>					
						</div>
						<div class="span2">
							<label>Analyse By Who</label>
							 <input type="checkbox" id="analyseCandidateSource" disabled="true"/>				
						</div>-->
					</div><!------Who Div END---->
					<div class="row-fluid">
					<div class="span4">
							<label>Select Candidate</label>
							<select class="input-block-level" id="candidateId">	
								<option value=0>Select Candidate</option>
							</select>						
					</div></div>
					<div class="row-fluid">
					<div class="span12">
							Hint :  Please Select Single Party To Analyse Candidate Wise						
					</div></div>
					</div>
					<div id="partiesSelShowDiv">			
					</div>
					<div id="locationsSelShowDiv">
					</div>
					<!------Whom Div---->
					<!--<div class="row-fluid label label-success-50  m_top10">
						<div class="span1 btn-block btn btn-large">
							<h4 class=" text-center ">Whom</h4>					
						</div>
						<div class="span3">
							<label>Select Party</label>
							<select class="input-block-level" id="whomPartysList" onchange="getCandidatesOfSelectedParty(this.value,'whomCandidatesList');">	
								<option value=0>Select Party</option>
							</select>						
						</div>
						<div class="span3">
							<label>Select Candidate</label>
							<select class="input-block-level" id="whomCandidatesList">	
								<option value=0>Select Candidate</option>
							</select>						
						</div>
						<div class="span3">
							<label>Benifits</label>
							<select class="input-block-level" id="whomBenfitsList">	
								<option value=0>Select Benifits</option>
							</select>						
						</div>
						<div class="span2">
							<label>Analyse By Whome</label>
							 <input type="checkbox" id="analyseCandidateDesti" class="srish"/>				
						</div>
					</div>--><!------Whom Div END---->
					<!--
					<label class="checkbox inline">
						<input type="checkbox" value="option1" id="keywordscheckId" onClick="unCheckCandidateCheckBox();"> <strong>Search By Keywords</strong>
					</label>	
					<label class="checkbox inline">
						<input type="checkbox" value="option1" id="candidateCheckId" onClick="unCheckKeywordsCheckBox();" style=""><strong>Search By Category</strong>
					</label>	
					
					
					<div class="row-fluid">
						<div class="span4" id="keywordsDiv" style="display:none;">
							<label><span id="keywordCategTitle">Select Key Word</span></label>
							<select Class="input-block-level" id="keywordsList"  readonly="readonly"></select>
												
						</div>
					</div>
					-->
					<div class="row-fluid m_top10 " >
						
						
						<!--
						<div class="span4">
							<label>Select Source</label>
							<select  id="newsSourceId"></select>
												
						</div>
					
					-->
					
						
						
						
						
						
						<!--<div class="span4">
							<label>Select News Location</label>
							<select></select>
												
						</div>-->
				
					</div>
					

					<!--<div style="height: 25px; margin-top: 10px;"><button class="btn btn-success" type="submit" id="advanceView" style="float:right;">Show Advance Search Options</button></div>-->
					<!-------Submit Button------>
					
					
					<div class="form-actions text-center">
						<button class="btn btn-success" type="submit" onClick="getAnalysisDataNewFroMultiple();">Submit</button><img id="submitDataImg" style="display: none;margin-left:10px;" src="images/search.jpg">
						<div id="errormessageDiv" style="display:none;color:red;margin-top:5px;">Please Select Atleast One Option To Analyse</div>
					</div><!-------Submit Button END------>
					<!--<div id="partyGraphId" style="min-width: 310px; height: 400px; margin: 0 auto"></div>-->
					<div id="responseTable" style="overflow-x:scroll;display:none;"></div>
					<div id="responseTable1" style="overflow-x:scroll; margin-top: 20px;display:none;"></div>			
					<div id="myResult"></div>
					<div id="myResult1"></div>
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
	<!-- <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>-->
	<script type="text/javascript" src="js/jquery.google.api/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	 <!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" /> -->
<link  rel="stylesheet" type="text/css" href="js/jquery.google.api/jquery-ui.css"/>
    <!-- <script src="http://code.jquery.com/jquery-1.8.2.js"></script> -->
<script type="text/javascript" src="js/jquery.google.api/jquery-1.8.2.js"></script>
    <!-- <script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script> -->
<script type="text/javascript" src="js/jquery.google.api/jquery-ui1.9.0.js"></script>
	
	<script type="text/javascript" src="js/multiSelectBox/jquery.multiselectforanalysis.js"></script>
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
	<script type="text/javascript" src="js/highcharts.js"></script>
    <script type="text/javascript" src="js/blockui.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css" /> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css" />
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css" /> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css" /> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css" />
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css" />
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css" />
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css" />     

	<!-- YUI Dependency files (End) -->
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityDateOperations.js"> </script>
	<SCRIPT type="text/javascript" src="js/newsAnalysis/newsAnalysis.js"></SCRIPT>
	<!--<SCRIPT type="text/javascript">
	$(document).ready(function(){
	   $("#myResult1").html('<div class="accordion" id="accordion2"><div class="accordion-group"><div class="accordion-heading"><a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">Collapsible Group Item #1</a></div><div id="collapseOne" class="accordion-body collapse in"><div class="accordion-inner">Anim pariatur cliche...</div></div></div><div class="accordion-group"><div class="accordion-heading"><a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">Collapsible Group Item #2</a></div><div id="collapseTwo" class="accordion-body collapse"><div class="accordion-inner">Anim pariatur cliche...</div></div></div></div>');
	});
	</SCRIPT>-->
</body>
</html>