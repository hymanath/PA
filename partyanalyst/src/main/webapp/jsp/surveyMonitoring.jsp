<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<%@taglib prefix="s" uri="/struts-tags" %>
		<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ page import="java.util.ResourceBundle;" %>
<html>
  <head>	
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
		<style>
			body{background:#f0f0f0;}
			.m_top20{margin-top:20px;}
			.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
			.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
			.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.voterDetals_widget ul li{border-bottom:1px solid #ccc; padding:10px 5px;}
			.voterDetals_widget ul li:first-child{border-bottom:none; padding:5px; margin:-5px -10px 0px 0px; padding:5px 10px; background:#DFF0D8;}
			.voterDetals_widget ul li:last-child{border-bottom:none; }
			td .voterDetals_widget:hover{shadow: inset 0 0 10px #000000; width:100%; height:100%; }	

			.requiredFont{
				color:red;
				font-size:13px;
			}			
		</style>
		
  </head>
  
  <body>
	<div class="container">
		<!---- Survey monitoring---->		
		<div class="row">
			<div class="span12">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>Survey monitoring</h4>
							<div class="row">
								<div id="reportErrorDiv" class="span8 offset2"></div>
							</div>
								<div class="row">
								<div class="span8 offset2">
									<div class="row-fluid">
										<!--<div class="span6">
											Select User Name<font class="requiredFont">*</font>
											<select class="input-block-level"> <option>01</option></select>
										</div>-->
										<div class="span6">
												Select Constituency <font class="requiredFont">*</font>
												<s:select theme="simple"  name="constituency" id="constituencyId" headerKey="0" headerValue="Select Constituency" list="constituenciesList" listKey="id" listValue="name" />
										</div>
										<div class="span5">
											Select Booth<font class="requiredFont">*</font>
											<select class="input-block-level" id="boothId"> <option value="0">Select Booth</option></select>
										</div>
									</div>	
									<!--<div class="row-fluid">
										<div class="span6">
											Panchayat <font class="requiredFont">*</font>
											<select class="input-block-level" id="panchayatId"> <option value="0">Select Panchayat</option></select>
										</div>
										<div class="span6">
											Booths <font class="requiredFont">*</font>
											<select class="input-block-level" id="boothId"> <option value="0">Select Booth</option></select>
										</div>
									</div>-->
									</div>
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onclick="getComparisionReport();">SUBMIT</button></div>
					</div>
				</div>
				
				<div id="comparisonReportId"></div>
			</div>
		</div>
	</div>
<script>

$('#constituencyId').change(function(){
		getBoothDetails();
 });

function getBoothDetails()
{
	var jObj =
	{
	 constituencyId:$('#constituencyId').val(),
	}
	$.ajax({
			type:'GET',
			url: 'getBoothDetailsByConstituencyAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				$('#boothId').find('option').remove();
				$('#boothId').prepend('<option value="0">Select Booth</option>');
				for(var i in result){				 
				 $('#boothId').append('<option value="'+result[i].boothId+'">Booth- '+result[i].partNo+'</option>');
				}
		});
}	



function getComparisionReport()
{
	var constituencyId = $("#constituencyId").val();
	var boothId = $("#boothId").val();
	
	if(constituencyId == 0)
	{
		$("#reportErrorDiv").html("Please Select Constituency").css("color","red");
		return;
	}
	if(boothId == 0)
	{
		$("#reportErrorDiv").html("Please Select Booth").css("color","red");
		return;
	}
	
	var jObj =
	{
	 boothId:boothId
	}
	$.ajax({
			type:'GET',
			url: 'getReportForVerificationByBoothId.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				console.log(result);
				buildComparisonReport(result);
		});

}
function buildComparisonReport(result)
{
	var str="";
	str+='<div class="row-fluid "><div class="span12 m_top20 widgetservey">';										
	str+='<div class="row-fluid">';
	
		str+='<table class="table table-bordered m_top20 table-hover table-striped">';
		str+='<thead class="alert alert-success"><tr>';
		str+='<th>Voter ID</th>';	
		str+='<th><span class="pull-right"><input type="checkbox"></span> COLLECTOR</th>';
		str+='<th><span class="pull-right"><input type="checkbox"></span> VERIFIER</th>';
		str+='<th><span class="pull-right"><input type="checkbox"></span> 3rd PARTY VERIFIER</th>';						
		str+='</tr></thead>';
	
		str+='<tbody>';
	for(var i in result)
	{
			str+='<tr>';		
			str+='<td  class="span2">'+result[i].voterIDCardNo+'</td>';
			
			if(result[i].dataCollector != null)
			{
				str+='<td class="span3"><div class="voterDetals_widget">';
				if(result[i].dataCollector.verified == "Y")
					str+='<ul class="unstyled"><li class="pull-right"><label><input type="radio" name="'+result[i].voterId+'" value="'+result[i].dataCollector.surveyDetailsInfoId+'" class="voterChkbox" checked></label></li>';	
				else
					str+='<ul class="unstyled"><li class="pull-right"><label><input type="radio" name="'+result[i].voterId+'" value="'+result[i].dataCollector.surveyDetailsInfoId+'" class="voterChkbox"></label></li>';	
				str	+='<li><strong>Caste:</strong>'+result[i].dataCollector.casteId+'</li>';
				str+='<li><strong>Hamlet:</strong>'+result[i].dataCollector.hamletName+'</li>';
				str+='<li><strong>Local Area:</strong>'+result[i].dataCollector.localArea+'</li>';
				str+='<li><strong>Cadre:</strong>'+result[i].dataCollector.isCadre+' </li>';
				str+='<li><strong>Influence People:</strong>'+result[i].dataCollector.isInfluencePeople+'</li>';
				str+='</ul>';
				str+='</div></td>';
			}
			else{
				str+='<td class="span3"><div class="voterDetals_widget"></div></td>';
				}						
			

			if(result[i].verifier != null)
			{	
				str+='<td class="span3"><div class="voterDetals_widget">';

				if(result[i].verifier.verified == "Y")
					str+='<ul class="unstyled"><li class="pull-right"><label><input type="radio" name="'+result[i].voterId+'"  value="'+result[i].verifier.surveyDetailsInfoId+'" class="voterChkbox" checked></label></li>';
				else
					str+='<ul class="unstyled"><li class="pull-right"><label><input type="radio" name="'+result[i].voterId+'"  value="'+result[i].verifier.surveyDetailsInfoId+'" class="voterChkbox"></label></li>';

				str	+='<li><strong>Caste:</strong>'+result[i].verifier.casteId+'</li>';
				str+='<li><strong>Hamlet:</strong>'+result[i].verifier.hamletName+'</li>';
				str+='<li><strong>Local Area:</strong>'+result[i].verifier.localArea+'</li>';
				str+='<li><strong>Cadre:</strong> '+result[i].verifier.isCadre+'</li>';
				str+='<li><strong>Influence People:</strong>'+result[i].verifier.isCadre+'</li>';
				str+='</ul>';
				str+='</div></td>';
			}
			else{
				str+='<td class="span3"><div class="voterDetals_widget"></div></td>';
			}
			
			if(result[i].thirdParty != null)
			{	
				str+='<td class="span3"><div class="voterDetals_widget">';

				if(result[i].thirdParty.verified == "Y")
					str+='<ul class="unstyled"><li class="pull-right"><label><input type="radio" name="'+result[i].voterId+'"  value="'+result[i].thirdParty.surveyDetailsInfoId+'" class="voterChkbox" checked></label></li>';	
				else
					str+='<ul class="unstyled"><li class="pull-right"><label><input type="radio" name="'+result[i].voterId+'"  value="'+result[i].thirdParty.surveyDetailsInfoId+'" class="voterChkbox"></label></li>';	

				str	+='<li><strong>Caste:</strong>'+result[i].thirdParty.casteId+'</li>';
				str+='<li><strong>Hamlet:</strong>'+result[i].thirdParty.hamletName+'</li>';
				str+='<li><strong>Local Area:</strong>'+result[i].thirdParty.localArea+'</li>';
				str+='<li><strong>Cadre:</strong> '+result[i].thirdParty.isCadre+'</li>';
				str+='<li><strong>Influence People:</strong>'+result[i].thirdParty.isCadre+'</li>';
				str+='</ul>';
				str+='</div></td>';
			}
			else{
					str+='<td class="span3"><div class="voterDetals_widget">No Records</div></td>';
			}
			str+='</tr>';
		}						
		str+='</tbody></table>';
		str+='</div>';
		str+='<div class="row text-center m_top20"><button class="btn btn-large btn-success" type="button" onClick="saveVerifiedRecordsDetails();">UPDATE SANITY CHECK</button></div>';
		str+='</div></div>';
		$("#comparisonReportId").html(str);
		
}


function saveVerifiedRecordsDetails()
{
	var jObj =
	{
		verifiedIds : []
	}

	$('.voterChkbox').each(function(){
		if(this.checked)
			jObj.verifiedIds.push(this.value);
	});

	$.ajax({
			type:'GET',
			url: 'saveVerifiedRecordsDetails.action',
			dataType: 'json',
			data: {task:JSON.stringify(jObj)},
		  }).done(function(result){
				console.log(result);
		});
}
	
		
	
	</script>	
  </body>
 </html>