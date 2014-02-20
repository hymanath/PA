
function isDateValid()
{
	$("#selectedNewsDetBtn").attr("disabled", false);	
	$('#errorMsgDiv').html('');		
	var fromDate = $("#existingFromText").val();
	var toDate  = $("#existingToText").val();
		if(fromDate.length > 0 && toDate.length > 0 )
		{		    
		  var dt1  = parseInt(fromDate.substring(0,2),10);
		  var mon1 = parseInt(fromDate.substring(3,5),10);
		  mon1 = mon1-1;
		  var yr1  = parseInt(fromDate.substring(6,10),10);
		  var dt2  = parseInt(toDate.substring(0,2),10);
		  var mon2 = parseInt(toDate.substring(3,5),10);
		   mon2 = mon2-1;
		  var yr2  = parseInt(toDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{
		 $('#errorMsgDiv').html("From Date should be Less Than To Date ");
		 $("#selectedNewsDetBtn").attr("disabled", true);
		}
	}
}

function checkIsDateValid(fromId,toId)
{	
	var fromDate = $("#"+fromId).val();
	var toDate  = $("#"+toId).val();
		if($.trim(fromDate).length > 0 && $.trim(toDate).length > 0 )
		{		    
		  var dt1  = parseInt(fromDate.substring(0,2),10);
		  var mon1 = parseInt(fromDate.substring(3,5),10);
		  mon1 = mon1-1;
		  var yr1  = parseInt(fromDate.substring(6,10),10);
		  var dt2  = parseInt(toDate.substring(0,2),10);
		  var mon2 = parseInt(toDate.substring(3,5),10);
		  mon2 = mon2-1;
		  var yr2  = parseInt(toDate.substring(6,10),10);
		  var date1 = new Date(yr1, mon1, dt1);
		  var date2 = new Date(yr2, mon2, dt2);

		if(date2 < date1)
		{
		  return true;
		}
	}
	return false;
}	