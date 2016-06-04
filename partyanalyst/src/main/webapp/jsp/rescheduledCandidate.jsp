<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
<title>Rescheduled Candidate</title>
<link href="dist/2016DashBoard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">
<style type='text/css'>
</style>
</head>
<section>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading" style="background:#ccc">
						<h3 class="panel-title">Overview Summary Report for Rescheduled Candidates</h3>
					</div>
					<div class="panel-body">
						<img id="ovrvwSmmryfRschduldCnddtsTblPrcngImg" src="images/icons/loading.gif" style="width:50px;height:50px;display:block;margin:auto">
						<div id="ovrvwSmmryfRschduldCnddtsTblId"></div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading" style="background:#ccc">
						<h3 class="panel-title">Rescheduled Candidate Wise Appointments Report Details</h3>
					</div>
					<div class="panel-body">
						<img id="rschdldCnddtWsAppntmntsTblPrcngImg" src="images/icons/loading.gif" style="width:50px;height:50px;display:block;margin:auto">
						<div id="rschdldCnddtWsAppntmntsTblId"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
<div id="ovrvwSmmryOfRschdldCnddtsMdlId" class="modal fade" role="dialog">
  <div class="modal-dialog modal-dialog modal-lg" style='width:80%'>
    <div class="modal-content">
      <div class="modal-header" style="background:#f2f2f2">
        <button type="button" class="close" data-dismiss="modal" style="font-size:30px;">&times;</button>
		<h4 class="modal-title" id="candiHeadingId"></h4>
       </div>
      <div class="modal-body">
	   <center>
			<img id="ovrvwSmmryOfRschdldCnddtsImgPrcssngId" src="images/icons/loading.gif" style="width:50px;height:50px">
	   </center>
	    <div id="ovrvwSmmryOfRschdldCnddtsTblId"></div>
      </div>
    </div>
  </div>
</div>
</section>
<script src='dist/2016DashBoard/js/bootstrap.js' type='text/javascript'></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javaScript">

$(document).ready(function(){
	getOverviewSummaryOfRescheduledCandidates();
	getRescheduledMembersApptDetails();
});
function getOverviewSummaryOfRescheduledCandidates(){
	
	$("#ovrvwSmmryfRschduldCnddtsTblId").html(' ');
	$("#ovrvwSmmryfRschduldCnddtsTblPrcngImg").show();
	var aptUserId = '${param.appuserId}';
    	var jsObj = {
			aptUserId:aptUserId
		}
	 $.ajax({
		 type:'POST',
		 url :'getOverviewSummaryOfRescheduledCandidatesAction.action',
		 dataType:'json',
		data : {task:JSON.stringify(jsObj)}  
	 }).done(function(result){
		 $("#ovrvwSmmryfRschduldCnddtsTblPrcngImg").hide();
	    if(result!=null && result.length>0){
			buildOverviewSummaryOfRescheduledCandidatesRslt(result);
		}else{
		   $("#ovrvwSmmryfRschduldCnddtsTblId").html('<p>No Data Available</p>');	
		}
	 });
}
function buildOverviewSummaryOfRescheduledCandidatesRslt(result){
	
	var str='';
	     str+='<table style="font-size:12px" class="table table-bordered table-condensed  table-striped" id="ovrvwSmmryfRschduldCnddtsDatTblId">';
		 str+='<thead style="background:#f5f5f5">';
		   str+='<th>Name</th>';
		   str+='<th>Image</th>';
		   str+='<th>Mobile No</th>';
		   str+='<th>Designation</th>';
		   str+='<th>Total Appointments Taken</th>';
		   str+='<th>Rescheduled Appointments</th>';
		   str+='<th>Rescheduled Count</th>';
		str+='</thead>';  
		   str+='<tbody>';
		   for(var i in result){
			   var candidateDesignation=result[i].candDesignation;
				 var location=result[i].constituency;
				 var buildCandidateDesignation='';
			   if(candidateDesignation!=null && candidateDesignation.length>0){
				  buildCandidateDesignation=candidateDesignation;
				  if(location!=null && location.length>0){
					buildCandidateDesignation = buildCandidateDesignation + " - " + location ;
				}
			  }
			  
			str+='<tr>';
			  if(result[i].name!=null && result[i].name.length>0){
				   str+='<td style="text-transform:uppercase"><a style="cursor:pointer;" onClick="getCandidateDetails('+result[i].id+',\''+result[i].name+'\',\''+result[i].mobileNo+'\',\''+result[i].imageUrl+'\',\''+buildCandidateDesignation+'\')">'+result[i].name+'<a></td>';
			  }else{
				   str+='<td> - </td>';
			  }
			  str+='<td ><img style="width:55px;height:55px;margin:0px;border:1px solid #ddd" class="media-object img-circle" src='+result[i].imageUrl+' alt="Candidate Image" onerror="setDefaultImage(this);"></td>';
			  if(result[i].mobileNo!=null && result[i].mobileNo.length>0){
				str+='<td>'+result[i].mobileNo+'</td>';
			  }else{
			    str+='<td> - </td>';
			  }
			   
			   if(buildCandidateDesignation!=null && buildCandidateDesignation.length>0){
					 str+='<td style="font-size:13px;">'+buildCandidateDesignation+'</td>';
			   }else{
					 str+='<td> - </td>';
			   }
			   if(result[i].totalApptsTaken!=null && result[i].totalApptsTaken>0){
					 str+='<td style="font-size:13px;">'+result[i].totalApptsTaken+'</td>';
			   }else{
					 str+='<td> - </td>';
			   }
			      
			   if(result[i].rescheduledApptsCount!=null && result[i].rescheduledApptsCount>0){
				  str+='<td>'+result[i].rescheduledApptsCount+'</td>';
			   }else{
				    str+='<td> - </td>';
			   }
			   if(result[i].rescheduledCount!=null && result[i].rescheduledCount>0){
				    str+='<td>'+result[i].rescheduledCount+'</td>';
			   }else{
				   str+='<td> - </td>'; 
			   }
			str+='</tr>'; 
		   }
		  str+='</tbody>';
		str+='</table>'; 
   $("#ovrvwSmmryfRschduldCnddtsTblId").html(str);	
   $('#ovrvwSmmryfRschduldCnddtsDatTblId').dataTable({
			"aaSorting": [],
			"iDisplayLength" : 10	,
			 "bDestroy": true,
			 "bFilter": true,
			"aLengthMenu": [[10,20,30, 50,100, -1], [10,20,30, 50,100, "All"]]			
		});  
	
}
function getCandidateDetails(apptCandidateId,name,mobileNo,imageUrl,designation){
	
	$("#ovrvwSmmryOfRschdldCnddtsTblId").html(' ');
	$("#candiHeadingId").html("Rescheduled Appointment Details For <b>"+name+"</b>");
	$("#ovrvwSmmryOfRschdldCnddtsMdlId").modal("show");
	$("#ovrvwSmmryOfRschdldCnddtsImgPrcssngId").show();
	var aptUserId = '${param.appuserId}';
    	var jsObj = {
			aptUserId:aptUserId,
			apptCandidateId:apptCandidateId
		}
	 $.ajax({
		 type:'GET',
		 url :'getApptRescheduledDetialsByCandidateAction.action',
		 dataType:'json',
		data : {task:JSON.stringify(jsObj)}  
	 }).done(function(result){
		 $("#ovrvwSmmryOfRschdldCnddtsImgPrcssngId").hide();
	      if(result!=null && result.length>0){
			  bulidApptRescheduledDetialsByCandidateRslt(result,name,mobileNo,imageUrl,designation);
		  }else{
			 $("#ovrvwSmmryOfRschdldCnddtsTblId").html('<p>NO Data Available</p>'); 
		  }
	 });
}
function bulidApptRescheduledDetialsByCandidateRslt(result,name,mobileNo,imageUrl,designation){
	
	var str='';
	 str+='<table style="font-size:12px" class="table table-bordered table-condensed  table-striped">';
	 str+='<thead style="background:#f5f5f5">';
	 str+='<th>Candidate Name</th>';
	 str+='<th>Image</th>';
	 str+='<th>Mobile No</th>';
	 str+='<th>Designation</th>';
	 str+='<th>Unique Id</th>';
	 str+='<th>Present Status</th>';
	 str+='<th>Rescheduled Date</th>';
	 str+='<th>Comments</th>';
	 str+='</thead>'; 
	   str+='<tbody>';
	    for(var i in result){
			
			var rescheduledList=result[i].subList;
			 if(rescheduledList!=null && rescheduledList.length>0){
				 for(var j in rescheduledList){
					 str+='<tr>';
						 if(name!=null && name.length>0){
						   str+='<td style="text-transform:uppercase">'+name+'</td>';
							}else{
								str+='<td> - </td>';
							}
							 str+='<td ><img style="width:50px;height:50px;margin:0px;border:1px solid #ddd;" class="media-object img-circle" src='+imageUrl+' alt="Candidate Image" onerror="setDefaultImage(this);"></td>';
							if(mobileNo!=null && mobileNo.length>0){
								str+='<td>'+mobileNo+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(designation!=null && designation.length>0){
								str+='<td>'+designation+'</td>';
							}else{
							  str+='<td> - </td>';
							}
							if(result[i].appointmentUniqueId!=null && result[i].appointmentUniqueId.length>0){
								str+='<td>'+result[i].appointmentUniqueId+'<span class="text-danger">('+result[i].rescheduledCount+')</span></td>';
							}else{
								str+='<td> - </td>';
							}
							if(result[i].presentStatus!=null && result[i].presentStatus.length>0){
								str+='<td>'+result[i].presentStatus+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(rescheduledList[j].date!=null && rescheduledList[j].date.length>0){
								str+='<td>'+rescheduledList[j].date+'</td>';
							}else{
								str+='<td> - </td>';
							}
							
							if(rescheduledList[j].subject!=null && rescheduledList[j].subject.length>0){
								str+='<td>'+rescheduledList[j].subject+'</td>';
							}else{
								str+='<td> - </td>';
							}
					 str+='</tr>';
				 }
			 }
			
		}
	  str+='</tbody>';
	  str+='</table>';
	  $("#ovrvwSmmryOfRschdldCnddtsTblId").html(str);	
	
	
}
function getRescheduledMembersApptDetails(){
  
   $("#rschdldCnddtWsAppntmntsTblId").html(' ');
   $("#rschdldCnddtWsAppntmntsTblPrcngImg").show();
  var aptUserId = '${param.appuserId}';
    	var jsObj = {
			aptUserId:aptUserId
		}
	 $.ajax({
		 type:'POST',
		 url :'getRescheduledMembersApptDetailsAction.action',
		 dataType:'json',
		data : {task:JSON.stringify(jsObj)}  
	 }).done(function(result){
		 $("#rschdldCnddtWsAppntmntsTblPrcngImg").hide();
	     if(result!=null && result.length>0){
			 buildRescheduledCnddtWsAppsDtls(result);
		 }else{
			 $("#rschdldCnddtWsAppntmntsTblId").html('<p>No Data Available</p>');
		 }
	 });
 } 
 function buildRescheduledCnddtWsAppsDtls(result){
	
	var str='';
	 str+='<table style="font-size:12px" class="table table-bordered table-condensed table-striped" id="rschdldAppntmntsCnddtPgntnTblId">';
	 str+='<thead style="background:#f5f5f5">';
	 str+='<th>Candidate Name</th>';
	 str+='<th>Image</th>';
	 str+='<th>Mobile No</th>';
	 str+='<th>Designation</th>';
	 str+='<th>Unique Id</th>';
	 str+='<th>Present Status</th>';
	 str+='<th>Rescheduled Date</th>';
	 str+='<th>Comments</th>';
	 str+='</thead>'; 
	   str+='<tbody>';
	   for(var i in result){
		   
		   var appointmentsList=result[i].subList;
		   
		    if(appointmentsList!=null && appointmentsList.length>0){
				 for(var j in appointmentsList){
					 
					 var rescheduledList=appointmentsList[j].subList;
					 
					 if(rescheduledList!=null && rescheduledList.length>0){
						 for(var k in rescheduledList){
							str+='<tr>';
								if(result[i].name!=null && result[i].name.length>0){
									str+='<td style="text-transform:uppercase">'+result[i].name.toUpperCase()+'</td>';
								}else{
									str+='<td> - </td>';
								}
								 str+='<td ><img style="width:55px;height:55px;margin:0px;border:1px solid #ddd" class="media-object img-circle" src='+result[i].imageUrl+' alt="Candidate Image" onerror="setDefaultImage(this);"></td>';
								if(result[i].mobileNo!=null && result[i].mobileNo.length>0){
									str+='<td>'+result[i].mobileNo+'</td>';
								}else{
									str+='<td> - </td>';
								}
								 var candidateDesignation=result[i].candDesignation;
						         var location=result[i].constituency;
						         var buildCandidateDesignation='';
							   if(candidateDesignation!=null && candidateDesignation.length>0){
								  buildCandidateDesignation=candidateDesignation;
								  if(location!=null && location.length>0){
									buildCandidateDesignation = buildCandidateDesignation + " - " + location ;
								}
							  }
							   if(buildCandidateDesignation!=null && buildCandidateDesignation.length>0){
								     str+='<td style="font-size:13px;">'+buildCandidateDesignation+'</td>';
							   }else{
								     str+='<td> - </td>';
							   }
						 		if(appointmentsList[j].appointmentUniqueId!=null && appointmentsList[j].appointmentUniqueId.length>0){
									str+='<td>'+appointmentsList[j].appointmentUniqueId+'</td>';
								}else{
									 str+='<td> - </td>';
								}
								if(appointmentsList[j].presentStatus!=null && appointmentsList[j].presentStatus.length>0){
									 str+='<td style="font-size:13px;">'+appointmentsList[j].presentStatus+'</td>';
								}else{
									 str+='<td> - </td>';
								}
								if(rescheduledList[k].date!=null && rescheduledList[k].date.length>0){
								   str+='<td>'+rescheduledList[k].date+'</td>';
								}else{
								   str+='<td> - </td>';
								}
								if(rescheduledList[k].subject!=null && rescheduledList[k].subject.length>0){
								   str+='<td>'+rescheduledList[k].subject+'</td>';
								}else{
								   str+='<td> - </td>';
								}
						str+='</tr>'; 
						 }
					 }
				 }
			}
		   
	   }
	  str+='</tbody>';
	  str+='</table>';
	  $("#rschdldCnddtWsAppntmntsTblId").html(str);
	   $('#rschdldAppntmntsCnddtPgntnTblId').dataTable({
			"aaSorting": [],
			"iDisplayLength" : 10	,
			 "bDestroy": true,
			 "bFilter": true,
			"aLengthMenu": [[10,20,30, 50,100, -1], [10,20,30, 50,100, "All"]]		
		});
 }
  function setDefaultImage(img){
	  img.src = "dist/Appointment/img/thumb.jpg";
   }
</script>
</html>
