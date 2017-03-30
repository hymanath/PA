getCustomReportPrograms();
	function getCustomReportPrograms(){
		var $list = $('#programSelId'),
			templatess = Handlebars.compile($('#select-box').html());
		var jsObj={
			startDateStr:"21-03-2017",
			endDateStr:"21-03-2017"
		}
		
		$.ajax({
		  type : "GET",
		  url : "getCustomReportProgramAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				$list.html(templatess(result));
				$list.trigger("chosen:updated");
				getRequiredDocumentsSummary();
				getProgramReportsDetails();
			}			
		});
	}
	
	function getProgramReportsDetails(){
		var jsObj={
			id:$("#programSelId").val()
		}
		
		$.ajax({
		  type : "GET",
		  url : "getProgramReportsDetailsAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			var str='';
			
			str+='<table class="table table-bordered">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>Location</th>';
						str+='<th>Observer Name</th>';
						str+='<th>Images</th>';
						str+='<th>Files</th>';
						str+='<th>Edit</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					if(result != null && result.length > 0){
						for(var i in result){
							str+='<tr>';
							
							var locations = "";
							if(result[i].locationsList != null && result[i].locationsList.length > 0){
								for(var t in result[i].locationsList){
									locations = locations==""?result[i].locationsList[t].locationValue:locations+", "+result[i].locationsList[t].locationValue;
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
							
							if(result[i].imagesList != null && result[i].imagesList.length > 0){
								str+='<td><i class="glyphicon glyphicon-picture"></i></td>';
							}else{
								str+='<td>Not Submitted</td>';
							}
							
							if(result[i].fileList != null && result[i].fileList.length > 0){
								str+='<td><i class="glyphicon glyphicon-file"></i></td>';
							}else{
								str+='<td>Not Submitted</td>';
							}
							
							str+='<td><span class="editReportCls" attr_report_id="'+result[i].reportId+'"><i class="glyphicon glyphicon-edit"></i></span></td>';
							
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
	
	$(document).on("click",".editReportCls",function(){
		var jsObj={
			reportId:$(this).attr("attr_report_id")
		}
		
		$.ajax({
		  type : "GET",
		  url : "getReportFullDetailsAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			
		});
	});