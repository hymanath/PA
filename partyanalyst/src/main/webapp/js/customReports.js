getCustomReportPrograms();
 function getCustomReportPrograms(){
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
	  var str='';
      str+='<option value="0">select program</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
        }  
    }  
       $("#programId").html(str); 
	  
	}); 
 }