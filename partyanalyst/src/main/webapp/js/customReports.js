var currentFromDate=moment().startOf('month').format("DD/MM/YYYY");
var currentToDate=moment().endOf('month').format("DD/MM/YYYY");
$(document).on('click','.applyBtn', function() {
	var dates= $(".multiDateRangePicker").val();
	 var str=dates.split("-");
	currentFromDate = str[0];
	currentToDate = str[1];
	getCustomReportPrograms();
});
$(document).on('change','#programSelId', function() {
	getProgramReportsDetails();
	getRequiredDocumentsSummary();
});

getCustomReportPrograms();
	function getCustomReportPrograms(){
		var $list = $('#programSelId'),
			templatess = Handlebars.compile($('#select-box').html());
	    var jsObj={
	      startDateStr:currentFromDate,
	      endDateStr:currentToDate
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
		var id = $("#programSelId").val();
		var jsObj={
			id:id
		}
		
		$.ajax({
		  type : "GET",
		  url : "getProgramReportsDetailsAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			buildProgramReportDetails(result,id);
		});
	}
	function buildProgramReportDetails(result,id){
					var str='';
			str+='<ul class="activeUlCls alertFilterCls list-inline pull-right">'
            str+='<li class="optionsCls " attr_id="'+id+'" attr_type="" style="background:#ddd;margin-right: 15px;">ALL</li>'
           str+='<li class="optionsCls" attr_id="'+id+'" attr_type="Y" style="margin-left: -7px;margin-right: 15px;">Submited</li>'
           str+='<li class="optionsCls" attr_id="'+id+'" attr_type="N" style="margin-left: -5px;margin-right: 10px;">NotSubmited</li>' 
            str+='</ul>'
			str+='<table class="table table-bordered" id="detailedReportsTableId">';
				str+='<thead>';
					str+='<tr style="background-color:#EDEEF0;border:1px solid #ddd">';
						str+='<th>LOCATION</th>';
						str+='<th>OBSERVER NAME</th>';
						//str+='<th>IMAGES</th>';
						str+='<th>FILES</th>';
						str+='<th>EDIT</th>';
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
							str+='<td style="color:#3C81BC;">'+observers+'</td>';
							
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
							
							str+='<td><span class="editReportCls" attr_report_id="'+result[i].reportId+'"><i class="glyphicon glyphicon-edit" style="cursor:pointer;"></i></span></td>';
							
							str+='</tr>';
						}
					}else{
						$("#detailedReportsDivId").html("No reports available for this program");
					}
				str+='</tbody>';
			str+='</table>';
			
			$("#detailedReportsDivId").html(str);
			$("#detailedReportsTableId").dataTable();
	}
	$(document).on("click",".editReportCls",function(){
		$("#uploadModalDivId").modal("show");
		var jsObj={
			reportId:$(this).attr("attr_report_id")
		}
		
		$.ajax({
		  type : "GET",
		  url : "getReportFullDetailsAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			buildReportFullDetails(result);
		});
	});
	function buildReportFullDetails(result){
		var str='';
		
		if(result.observersList != null && result.observersList.length > 0)
		{
			str+='<ul class="list-inline observerList">';
			for(var i in result.observersList)
			{
				str+='<li>';
					str+='<img src="https://mytdp.com/images/'+result.observersList[i].path+'" class="img-responsive"/>';
					str+='<p>Name : '+result.observersList[i].name+'</p>';
					str+='<p>Voter No : '+result.observersList[i].voterNum+'</p>';
					str+='<p>Mobile No : '+result.observersList[i].mobileNum+'</p>';
					str+='<p>Membership No : '+result.observersList[i].membershipNo+'</p>';
				str+='</li>';
			}
			str+='</ul>';
		}
			
		
		str+='<div class="block">'+result.name+'</div>';
		
		if(result.fileList != null && result.fileList.length > 0)
		{
			str+='<ul class="observerList list-inline">';
			for(var i in result.fileList)
			{
				str+='<li>';
					str+='<a href="'+result.fileList[i].path+'"><i class="fa fa-file-pdf-o text-danger"></i>&nbsp;&nbsp;'+result.fileList[i].name+'</a>';
				str+='</li>';
			}
			str+='</ul>';
		}
		
		$("#reportFullDetails").html(str);
	}
   $(document).on("click",".optionsCls",function(){
	   var id = $(this).attr("attr_id");
	   var type = $(this).attr("attr_type");
	   var jsObj={
			reportId:$(this).attr("attr_id"),
			type:type
		}
		$.ajax({
		  type : "GET",
		  url : "getCustomReportProgramForreportId.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			buildProgramReportDetails(result);
		});
   });
