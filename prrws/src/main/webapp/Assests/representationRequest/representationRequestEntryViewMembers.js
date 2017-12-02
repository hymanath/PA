var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
//getRepresentativeSearchWiseDetails(typeVal);
function getRepresentativeSearchWiseDetails(typeVal){
	$("#errorId").html("");
	var searchValue = typeVal;
	var radioValue = $("input[name='optradio']:checked"). val();
	if(typeVal == ""){
		if(radioValue =="name"){
		$("#errorId").html("<h5>Please Enter Name</h5>");
			return;
		}else if(radioValue =="mobileNo"){
		$("#errorId").html("<h5>Please Enter Mobileno</h5>");
			return;
		}else if(radioValue =="refCode"){
		$("#errorId").html("<h5>Please Enter Refcode</h5>");
			return;
		}else if(radioValue =="emailId"){
		$("#errorId").html("<h5>Please Enter EmailId</h5>");
			return;
		}
	}
	$("#representationRequestEntryTable").html(spinner);
    var json = {
    filterType :radioValue,
	filterValue:searchValue
    }        
  $.ajax({                
    type:'POST',    
    url: 'getRepresentativeSearchWiseDetails',
    dataType: 'json',
    data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
		if(result != null && result.length>0){
			representationRequestEntryTable(result);
		}else{
			$("#representationRequestEntryTable").html("NO DATA AVAILABLE");
		}
  }); 
}
$(document).on("click","#advancedSearchId",function(){
	var typeVal = $("#advancedSearchVal").val();
	getRepresentativeSearchWiseDetails(typeVal);
});
function representationRequestEntryTable(result){
	var str='';
	str+='<table class="table table-bordered">';
		str+='<thead>';
			str+='<tr>';
				str+='<th>ENDT ID</th>';
				str+='<th>REPRESENTEE NAME</th>';
				str+='<th>REPRESENTEE DESIGNATION</th>';
				str+='<th>REPRESENTEE MOBILE NO</th>';
				str+='<th>WORK NAME</th>';
				str+='<th>NO OF WORKS</th>';
				str+='<th>SUBJECT</th>';
				str+='<th>ACTION</th>';
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
			str+='<tr>';
				str+='<td>'+result[i].refCode+'</td>';
				str+='<td>'+result[i].candidateName+'</td>';
				str+='<td>-</td>';
				str+='<td>'+result[i].mobileNo+'</td>';
				str+='<td>'+result[i].workName+'</td>';
				str+='<td>'+result[i].noOfWorks+'</td>';
				str+='<td>'+result[i].subject+'</td>';
				str+='<td><button type="button" class="btn">View/Edit</button></td>';
			str+='</tr>';
			}
		str+='</tbody>';
	str+='</table>';
	$("#representationRequestEntryTable").html(str);
}