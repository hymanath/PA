<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bleading Details</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/BloodBankCustomFiles/custom.css" rel="stylesheet" type="text/css">
<link href="dist/DateRange/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<!--<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" rel="stylesheet" type="text/css">-->
<link href="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:400,500,300,500italic,400italic,300italic,700,900" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12">
        	<div class="block">
            	<div class="row">
                	<div class="col-md-3 col-xs-3">
                    	<h3 class="m_top20">BLEADING DETAILS</h3>
                    </div>
                    <div class="col-md-1 col-xs-2">
                  	  <img src="dist/BloodBankCustomFiles/logo.png" class="img-responsive" alt="Logo"/>
                    </div>
                    <div class="col-md-8 col-xs-7">
                    	<h3 class="text-capitalise">ntr trust blood bank</h3>
                        <p class="text-capitalise">ntr trust bhavan, road no-2, banjara hills, hyderabad - 34</p>
                        <p>Tel: 040 30799999 | E-Mail:hydbb@ntrtrust.org | Lic No: 01/HD/AP/2008/BB/CP</p>
                    </div>
                </div>
                <hr style="border-color:#333"/>
                <div class="row">
                	<div class="col-md-3">
                    	<label>Showing</label>
                        <select id="totalStatusId" class="form-control totalStatusCls">
                        	<option value="0">All Applications</option>
                        </select>
                    </div>
					<!--<div id="errorDiv" style="color:#ff0020;margin-right:20px;" class="pull-right"></div>-->
                    <div class="col-md-3 col-md-offset-2">
                    	<label>Search</label><span style='color:red' id="errorDiv"></span>
                       	<input class="form-control" type="text" id="searchInputId" placeholder="Name/MobileNo/MembershipID" />
                    </div>
					<div class="col-md-2">
						<label>Date</label>
						<select class="form-control" id="datesSelId"></select>
					</div>
					<div class="col-md-2">
						<button class="btn btn-success m_top25" style="margin-top:25px;" id="serachButtonId" onclick="getPrePopulateTheDataDetails(true)">SEARCH</button>
					</div>
                </div>
                <div class="row">
                	<div class="col-md-12 m_top10">
                    	<div class="bg-info">
                        	<!--<p>Currently Showing Search Results - 40<span class="pull-right">Clear Search Results</span></p>-->	
                        </div>
                    </div>
                	<div class="col-md-12 m_top10">
                    	<div class="panel panel-default">
                        	<div class="panel-heading bg_cc">
							    <span style="color:red" class="errorCls pull-right"></span>
								 <h4><input type="button" class="btn btn-info pull-right btn-xs" id="exportToExcelId" value="ExportToExcel" style='display:none;'/><h4>
                            	<h4 class="panel-title text-capitalise"><span  id="tableHdngId">all applications</span> - <span id="statusCount">0</span></h4>
						    </div>
							<div class="panel-body pad_0">
							 <img id="cadreDetailsLoadingId" src="images/Loading-data.gif" style="width: 70px; height: 60px;margin-left: 533px;display:none;"/>
								<div id="BleedingCadreDetailsId"></div>
                            </div>
                        </div>
                   </div>
				  <div class="row">
				     <div class="col-md-12 m_top10">
					    <div id="exprtTExclBldDnrTblId" style='display:none'></div>
					 </div>
				  </div>
                </div>
             </div>
         </div>
    </div>
</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="js/bloodBankScript/bloodBankBleading.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<!--<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>-->
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script>

var globalCampId=2;
getBloodBankCampDates();
function getBloodBankCampDates(){
	var jObj={
		campId:globalCampId
	};
	$.ajax({
		type:"GET",
		url:'getBloodBankCampDatesAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		
	});
}
$( "#searchInputId" ).blur(function() {
	var value = $("#searchInputId" ).val();
	if(value == "" || value ==undefined){
		var statusId=$("#totalStatusId").val();
		var statusIdList = [];  
		if(statusId !=null && statusId>0){
			statusIdList.push({"id":statusId});   
		}
		getBleedingCadreDetails(statusIdList,globalCampId);
	}
});	
</script>
</body>
</html>
