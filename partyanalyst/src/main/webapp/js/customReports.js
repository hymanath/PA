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
$(document).on("click",".activeUlCls li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
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
			$list.html(templatess(result));
			$list.trigger("chosen:updated");
			getRequiredDocumentsSummary();
			getProgramReportsDetails();			
		});
	}
	
	function getProgramReportsDetails(){
		$("#statusWiseBtnsDivId").hide();
		$("#detailedReportsDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var id = $("#programSelId").val();
		$(".alertFilterCls li").attr("attr_id",id);
		var jsObj={
			id:id
		}
		
		$.ajax({
		  type : "GET",
		  url : "getProgramReportsDetailsAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				$("#statusWiseBtnsDivId").show();
				buildProgramReportDetails(result);
			}else{
				$("#detailedReportsDivId").html("NO DATA AVAILABLE...")
			}
			
		});
	}
	function buildProgramReportDetails(result){
		var str='';
			
			str+='<table class="table table-bordered" id="detailedReportsTableId">';
				str+='<thead>';
					str+='<tr style="background-color:#EDEEF0;border:1px solid #ddd">';
						str+='<th>Name</th>';
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
							str+='<td>'+result[i].name+'</td>';
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
									//str+='<a href="'+result[i].fileList[k].path+'"><i class="fa fa-file-pdf-o text-danger"></i>&nbsp;&nbsp;'+result[i].fileList[k].name+'</a>';
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
		$(".jFiler-item").html(' ');
		$("#uploadModalDivId").modal("show");
		 $("#uploacFilesBtnId").removeAttr("disabled");
		var reportId = $(this).attr("attr_report_id");
		var programId = $("#programSelId").val();
		var jsObj={
			reportId:reportId
		}
		
		$.ajax({
		  type : "GET",
		  url : "getReportFullDetailsAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			buildReportFullDetails(result,reportId,programId);
		});
	});
	function getExtension(path) {
		var basename = path.split(/[\\/]/).pop(),  // extract file name from full path ...
												   // (supports \\ and / separators)
			pos = basename.lastIndexOf(".");       // get last position of .

		if (basename === "" || pos < 1)            // if file name is empty or ...
			return "";                             //  . not found (-1) or comes first (0)

		return basename.slice(pos + 1);            // extract extension ignoring .
	}
	function buildReportFullDetails(result,reportId,programId){
		var str='';
		
		if(result.observersList != null && result.observersList.length > 0)
		{
			str+='<ul class="list-inline observerList">';
			for(var i in result.observersList){
				str+='<li>';
				if(result.observersList[i].path != null && result.observersList[i].path.trim()!="")
					str+='<img src="https://mytdp.com/images/cadre_images/'+result.observersList[i].path+'" class="img-responsive"/>';
				else
					str+='<img src="https://mytdp.com/images/User.png" class="img-responsive"/>';
					str+='<p>Name : '+result.observersList[i].name+'</p>';
					str+='<p>Voter No : '+result.observersList[i].voterNum+'</p>';
					str+='<p>Mobile No : '+result.observersList[i].mobileNum+'</p>';
					str+='<p>Membership No : '+result.observersList[i].membershipNo+'</p>';
				str+='</li>';
			}
			str+='</ul>';
		}
			
		
		str+='<textarea style="width: 919px; height: 146px;" name="description">'+result.name+'</textarea>';
		str+='<input type="hidden" name="reportId" value="'+reportId+'"></input>';
		str+='<input type="hidden" name="programId" value="'+programId+'"></input>';
		
		str+='<div class="row">';
			if(result.fileList != null && result.fileList.length > 0)
			{
				var extension = ''
				str+='<div class="col-md-6 col-xs-12 col-sm-12">';
					/* str+='<div class="row">';	
						str+='<div class="col-md-12 col-xs-12 col-sm-12">';	
							str+='<ul class="slider-forparty_meeting slider-for">';
								for(var i in result.fileList)
								{
									extension = getExtension(result.fileList[i].path);
									if(extension == 'jpg' || extension == 'png' || extension == 'jpeg')
									{
									str+='<li class="text-center">';
										str+='<img src="'+result.fileList[i].path+'" class="img-responsive"/>';
									str+='</li>';
									}
								}
							str+='</ul>';
							str+='<ul class="slider-nav m_top20">';	
								for(var i in result.fileList)
								{
									extension = getExtension(result.fileList[i].path);
									if(extension == 'jpg' || extension == 'png' || extension == 'jpeg')
									{
									str+='<li class="text-center">';
										str+='<img src="'+result.fileList[i].path+'" class="img-responsive"/>';
									str+='</li>';
									slickInitialisation();
									}
								}
							str+='</ul>';
						str+='</div>';
					str+='</div>'; */
					str+='<ul class="observerList list-inline">';
					for(var i in result.fileList)
					{
						extension = getExtension(result.fileList[i].path);
						if(extension == 'jpg' || extension == 'jpeg' || extension == 'png')
						{
							str+='<li class="text-center">';
								str+='<i class="removeCls glyphicon glyphicon-trash" id="removeId'+i+'" attr_report_id ="'+result.fileList[i].id+'"></i>';
								str+='<img src="'+result.fileList[i].path+'" class="img-responsive" style="height: 100px;width: 100px;"/>';
							str+='</li>';
						}
					}
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-md-6 col-xs-12 col-sm-12">';
					str+='<ul class="observerList list-inline">';
					for(var i in result.fileList)
					{
						extension = getExtension(result.fileList[i].path);
						if(extension == 'pdf' || extension == 'docx' || extension == 'xls')
						{
							str+='<li class="text-center">';
								str+='<i class="removeCls glyphicon glyphicon-trash" id="removeId'+i+'" attr_report_id ="'+result.fileList[i].id+'"></i>';
								str+='<a href="'+result.fileList[i].path+'">';
									str+='<i class="fa fa-file-pdf-o text-danger" style="display:block;font-size:90px;"></i>&nbsp;&nbsp;';
								str+=''+result.fileList[i].name+'</a>';
							str+='</li>';
						}
					}
					str+='</ul>';
				str+='</div>';
			}
		str+='</div>';
		$("#reportFullDetails").html(str);
		
	}
	function slickInitialisation()
	{
		$('.slider-for').slick({
			slidesToShow: 1,
			slidesToScroll: 1,
			slide: 'li',
			arrows: false,
			fade: true,
			asNavFor: '.slider-nav'
		});
		$('.slider-nav').slick({
			slidesToShow: 11,
			slidesToScroll: 0,
			slide: 'li',
			asNavFor: '.slider-for',
			dots: false,
			focusOnSelect: true,
			variableWidth: true
		})
	}
   $(document).on("click",".removeCls",function(){
	   var reportId = $(this).attr("attr_report_id");
	   var id = $(this).attr("id");
	   var jsObj={
			reportId:reportId
		}
	   
		$.ajax({
		  type : "GET",
		  url : "deleteCustomReportFileDetailsAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				if(result.message == "Success"){
					$("#"+id).closest("li").remove();
					setTimeout(function(){
						$("#successSpanModalId").html("<center style='color: green; font-size: 16px;'>File deleted successfully...</center>").fadeOut(3000);			
					}, 500);
				}	
			}
		});
   });
   $(document).on("click",".optionCls",function(){
	   $("#detailedReportsDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	   var id = $(this).attr("attr_id");
	   var type = $(this).attr("attr_type");
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
			if(result != null && result.length > 0)
			{
				buildProgramReportDetails(result);
			}else{
				$("#detailedReportsDivId").html("NO DATA AVAILABLE...");
			}
		});
   });
