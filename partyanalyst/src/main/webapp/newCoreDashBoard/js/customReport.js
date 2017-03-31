var currentFromDateCustom = moment().subtract(1,'year').format("DD/MM/YYYY");
var currentToDateCustom = moment().format("DD/MM/YYYY");
getCustomReportPrograms();
function getCustomReportPrograms(){
	var jsObj={
		startDateStr:currentFromDateCustom,
		endDateStr:currentToDateCustom
	}
	
	$.ajax({
	  type : "GET",
	  url : "getCustomReportProgramAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){
		console.log(result);
		if(result != null && result.length > 0){
			buildCustomReportPrograms(result);
		}	
	});
}
function buildCustomReportPrograms(result)
{
	var str='';
	
	str+='<div class="panel-group m_top20" id="accordionCustomReport" role="tablist" aria-multiselectable="true">';
		for(var i in result)
		{
		str+='<div class="panel panel-default panelNew">';
			str+='<div class="panel-heading" role="tab" id="headingCustom'+i+'">';
			if(i == 0)
			{
				str+='<a role="button" class="collapseDebatesIcon" onClick="getRequiredDocumentsSummary('+result[i].id+')" data-toggle="collapse" data-parent="#accordionCustomReport" href="#collapseCustom'+i+'" aria-controls="collapseCustom'+i+'">';
			}else{
				str+='<a role="button" class="collapsed collapseDebatesIcon" onClick="getRequiredDocumentsSummary('+result[i].id+')" data-toggle="collapse" data-parent="#accordionCustomReport" href="#collapseCustom'+i+'" aria-controls="collapseCustom'+i+'">';
			}
				
					str+='<h4 class="panel-title text-capital">'+result[i].name+'</h4>';
				str+='</a>';
			str+='</div>';
			if(i == 0)
			{
				str+='<div id="collapseCustom'+i+'" class="panel-collapse collapse in" aria-labelledby="headingCustom'+i+'">';
			}else{
				str+='<div id="collapseCustom'+i+'" class="panel-collapse collapse" aria-labelledby="headingCustom'+i+'">';
			}
				str+='<div class="panel-body">';
					str+='<div id="customReortsMainDivId'+result[i].id+'"></div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		}
	str+='</div>';
	$("#customReortsMainDiv").html(str);
	getRequiredDocumentsSummary(0)
}
function getRequiredDocumentsSummary(id){
	var jsObj={
		id:id
	}
	$("#customReortsMainDivId"+id).html(spinner);
	$.ajax({
	  type : "GET",
	  url : "getTotalExpectedReportsAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){
		buildRequiredDocumentsSummary(result,id);
	});
}
function buildRequiredDocumentsSummary(result,id)
{
	var str='';
	//if(result != null && result.length > 0){
		str+='<div class="row">';
		str+='<div class="col-md-4 col-xs-12 col-sm-4">';
			str+='<div class="bg_ED pad_10">';
			if(result.totalCount != null){
				str+='<span class="totalCountCls" attr_type="" attr_id ='+id+' style="cursor:pointer;color:#3C81BC">'+result.totalCount+'</span>';
			}else{
				str+='<span class="totalCountCls" attr_type="" attr_id ='+id+' style="cursor:pointer;color:#3C81BC">0</span>';
			}
			str+='<p class="text-capital">total reports</p>';
			str+='</div>';
		str+='</div>';
		for(var i in result.locationsList){
		str+='<div class="col-md-4 col-xs-12 col-sm-4">';
			str+='<div class="bg_ED pad_10">';
			if(result.locationsList[i].name == "Y"){
				if(result.locationsList[i].count != null){
					str+='<span class="totalCountCls" attr_type="Y" attr_id ='+id+' style="cursor:pointer;color:#3C81BC" >'+result.locationsList[i].count+'('+((parseFloat(result.locationsList[i].count)*100)/parseFloat(result.totalCount)).toFixed(2)+')</span>';
				}else{
					str+='<span>0</span>';
				}
				str+='<p class="text-capital">submitted reports</p>';
			}
			str+='</div>';
		str+='</div>';
		str+='<div class="col-md-4 col-xs-12 col-sm-4">';
			str+='<div class="bg_ED pad_10">';
			 if(result.locationsList[i].name == "N"){
				  if(result.locationsList[i].count != null){
					  str+='<span class="totalCountCls" attr_type="N" attr_id ='+id+' style="cursor:pointer;color:#3C81BC">'+result.locationsList[i].count+'('+((parseFloat(result.locationsList[i].count)*100)/parseFloat(result.totalCount)).toFixed(2)+')</span>';
				  }else{
					  str+='<span>0</span>';
				  }
				str+='<p class="text-capital">not submitted reports</p>';
			 }
			str+='</div>';
			
		str+='</div>';
	str+='</div>';
	}
	$("#customReortsMainDivId"+id).html(str);
	//}
}

function getProgramReportsDetails(id,type){
	$("#detailedReportsDivId").html("");
	$("#customModalId").modal("show");
	if(type == ""){
		$("#customHeadingId").html("TOTAL CUSTOM REPORT DETAILS");
	}else if(type == "Y"){
		$("#customHeadingId").html("SUBMITTED CUSTOM REPORT DETAILS");
	}else if(type == "N"){
		$("#customHeadingId").html("NOT SUBMITTED CUSTOM REPORT DETAILS");
	}
	
		var jsObj={
			reportId:id,
			type:type
		}
		$.ajax({
		  type : "GET",
		  url : "getCustomReportProgramForreportId.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			var str='';
			
			str+='<table class="table table-bordered">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>Location</th>';
						str+='<th>Observer Name</th>';
						//str+='<th>Images</th>';
						str+='<th>Files</th>';
						//str+='<th>Edit</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					if(result != null && result.length > 0){
						for(var i in result){
							str+='<tr>';
							
							var locations = "";
							if(result[i].locationsList != null && result[i].locationsList.length > 0){
								for(var t in result[i].locationsList){
									locations = locations==""?result[i].locationsList[t].locationName:locations+", "+result[i].locationsList[t].locationName;
								}
							}
							str+='<td>'+locations+'</td>';
							
							var observers = "";
							if(result[i].observersList != null && result[i].observersList.length > 0){
								for(var t in result[i].observersList){
									observers = observers==""?result[i].observersList[t].name:observers+", "+result[i].observersList[t].name;
								}
							}
							str+='<td>'+observers+'</td>';
							
							/* if(result[i].imagesList != null && result[i].imagesList.length > 0){
								str+='<td><i class="glyphicon glyphicon-picture"></i></td>';
							}else{
								str+='<td>Not Submitted</td>';
							} */
							
							if(result[i].fileList != null && result[i].fileList.length > 0){
								str+='<td><i class="glyphicon glyphicon-file"></i></td>';
							}else{
								str+='<td>Not Submitted</td>';
							}
							
							//str+='<td><span class="editReportCls" attr_report_id="'+result[i].reportId+'"><i class="glyphicon glyphicon-edit" style="cursor:pointer;"></i></span></td>';
							
							str+='</tr>';
						}
					}else{
						str+='<tr><td rowspan="5"></h5>No Reports Available For this Program.<h5></td></tr>'
					}
				str+='</tbody>';
			str+='</table>';
			
			$("#detailedReportsDivId").html(str);
		});
	}
$(document).on("click",".totalCountCls",function(){
	var type = $(this).attr("attr_type");
	var id = $(this).attr("attr_id");
	getProgramReportsDetails(id,type);
});
	