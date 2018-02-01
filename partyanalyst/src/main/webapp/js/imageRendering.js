var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
if(wurl.length == 3)
  wurl = url.substr(0,(url.indexOf(".in")+3));
var glStartDate = moment().startOf('month').format("DD-MM-YYYY");
var glEndDate = moment().format("DD-MM-YYYY");
var spinner = '<div class="row"><div class="col-sm-12"><div style=" color: orange;font-size: 20px; font-weight:bold; text-align : center">LOADING ...</div></div>';
getAllLocations(2,'1');
getAllNewsPapers();
var districtName="";
var constituencyName="";
var parlimentName="";
var newsPaper=[];
var editionType=[];

$(".chosen-select").chosen();
$("#dateRangeFromDateNewsId").daterangepicker({
		startDate: glStartDate,
        singleDatePicker: true,
		locale: {
		  format: 'DD-MM-YYYY'
		},
		
	});
$("#dateRangeToDateNewsId").daterangepicker({
		startDate: glEndDate,
        singleDatePicker: true,
		locale: {
		  format: 'DD-MM-YYYY'
		},
		
	})

$(".newsLettersRefresh").click(function(){
	getAllLocations(2,'1');
	getAllNewsPapers();
	$('#newsLetterEditionId').html('<option> Main </option> <option> District </option> <option> Online </option>').trigger('chosen:updated');		
});
function getAllLocations(levelId,levelValue){
	if(levelId==2){
			$('#newsLetterDistrictId').html("ALL districts");
			$('#newsLetterConstituencyId').html("ALL constituencies");
		var jsObj={
				"stateId":levelValue
			}
		$.ajax({
			type : "GET",
			url : "getAllDistrictsForLoationDashBoardAction.action",
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			return buildResult('newsLetterDistrictId',result);
		});
	}else if(levelId==3){
		$('#newsLetterConstituencyId').html("ALL constituencies");
		var jsObj={
				"districtId":levelValue
			}
		$.ajax({
			type : "GET",
			url : "getConstituenciesByDistrictForLoationDashBoardAction.action",
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			return buildResult('newsLetterConstituencyId',result);
		});
	}
	
}
function buildResult(levelTypeId,result){
	var optionStr='';
	if(levelTypeId=="newsLetterDistrictId"){
		optionStr+='<option>ALL Districts</option>';
	}else if(levelTypeId =="newsLetterConstituencyId"){
		optionStr+='<option>ALL constituencies</option>';
	}/* else if(levelTypeId =="newsLetternewsPaperId"){
		optionStr+='<option> ALL</option>';
	} */
	if(levelTypeId =="newsLetternewsPaperId"){
		for(var i in result){
			optionStr+='<option>'+result[i].paperName+'</option>';
		}
	}else {
		for(var i in result){
			optionStr+='<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>';
		}
	}
	if(levelTypeId=="newsLetterDistrictId"){
		$('#'+levelTypeId).html(optionStr);
		$('#newsLetterConstituencyId').html('<option>ALL constituencies</option>');
	}else{
		$('#'+levelTypeId).html(optionStr);
	}
	$('#'+levelTypeId).trigger("chosen:updated");
	$('#newsLetterConstituencyId').trigger("chosen:updated");
}

$('#newsLetterDistrictId').change(function() {
    var districtId= $("#newsLetterDistrictId").val();
	districtName= $("#newsLetterDistrictId option:selected").text();
	getAllLocations(3,districtId); 
});
$('#newsLetterConstituencyId').change(function() {
    var constituencyId= $("#newsLetterConstituencyId").val();
	constituencyName= $("#newsLetterConstituencyId option:selected").text();// $("#newsLetterDistrictId").text();
	
});

$('#newsLetterEditionId').change(function() {          
        editionType=$(this).val();
});
$('#newsLetternewsPaperId').change(function() {
	newsPaper = $(this).val();
 });
function getAllNewsPapers(){
	
	$.ajax({
		type : 'GET', 
		url: wurl+"/CommunityNewsPortal/webservice/getAllNewsPapers/AP"
      //url: "http://192.168.11.172:8446/CommunityNewsPortal/webservice/getAllNewsPapers/AP"
    }).then(function(result){
		return buildResult('newsLetternewsPaperId',result);
      
    });    
}
$(document).on("click","#submitId",function(){
	$('#imageId').html(spinner);
	var district="";
	var constituency=""
	if(districtName=="ALL Districts"){
		district="";
	}else{
		district=districtName;
	}if(constituencyName="ALL constituencies"){
		constituency="";
	}else{
		constituency=constituencyName;
	}
	var json={
		"stateName":"Andhra Pradesh",
		"districtName":district,
		"constituencyName":constituency,
		"parliamentName":parlimentName,
		"newsPaperName":newsPaper,
		"editionType":editionType,
		"fromDateStr":glStartDate,
		"toDateStr":glEndDate
	};
	var newsurl=wurl+"/CommunityNewsPortal/webservice/imageRendering";
	//var url="http://192.168.11.172:8446/CommunityNewsPortal/webservice/imageRendering";
	$.ajax({
		url: newsurl,
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp){
			if(ajaxresp!=null){
				if(ajaxresp.impagePath == "" && ajaxresp.impagePath.length == 0){
					$('#imageId').html('<h3><span style="color:red; padding-left: 59px; ">Something Went Wrong...Please Try Again</span></h3>'); 
				}else{	
					$('#imageId').html('<img  class="img-responsive"  src="'+ajaxresp.impagePath+'" alt="Something Went Wrong... Please Try Again ">'); 
				}
			}else{
				$('#imageId').html('<span style="textAlign:center; text-color:red">NO DATA AVAILABLE</span>'); 
			}
			
		}
	});
	
});
