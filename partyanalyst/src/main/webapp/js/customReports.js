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
			}			
		});
	}