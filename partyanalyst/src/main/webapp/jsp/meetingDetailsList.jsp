<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Meeting</title>

<link type="text/css" href="dist/css/bootstrap.css" rel="stylesheet" />
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/daterange/daterangepicker-bs3.css" type="text/css" rel="stylesheet">
<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
<link href="dist/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<link href="css/Training/css/basic.css" rel="stylesheet" type="text/css">
<link href="css/Training/css/dropzone.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">

<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<style>
header.eventsheader {
 background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto #fed501;
 background-origin: border-box;
 background-repeat: no-repeat;
 height: 71px;
}
.grievance-training ul li
{
    border:1px solid #999;
    list-style:none;
}
.grievance-training ul li p
{
    margin:0px;
}
.grievance-training ul li .data
{
    padding:10px;
}
.grievance-training ul li .data-edit .row
{
    padding:10px;
}
</style>

</head>
<body>
<header  class="eventsheader">
<!-- <img src="css/Training/img/header.jpg" width="100%"> -->
    <div class="container">
        <div class="row">
            <div class="col-md-2 col-xs-4 col-sm-1">
                <img src="dist/img/logo.png" class="img-responsive">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1">
                <img src="dist/img/CBN1.png" class="img-responsive">
            </div>
            <div class="col-md-6 col-xs-7 col-sm-7 text-center">              
                 <p class="header-text display-style" id="mainheading" style="font-size:38px;"></p>              
            </div>
            <div class="col-md-1 col-xs-1 col-sm-1"><img src="dist/img/NTR1.png" class="img-responsive" />  
            </div>
            <div class="col-md-2 col-xs-1 col-sm-1">
                <div class="" style="color:white;margin-top: 5px;"><b> Welcome ${sessionScope.UserName} </b></div>
                    <a href="#" class="dropdown-toggle btn btn-default btn-xs m_top10" data-toggle="dropdown" aria-expanded="false" style="margin-top: 5px;">
                    Menu <img src="images/menu_icon.png" />
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);">
                       <!--<li><a href="mahanaduCadreVisitInfoAction.action"><span>ENTRY/EXIT DASHBOARD</span></a> </li>-->
                       <li><a href="dashBoardAction.action"><span>DASHBOARD</span></a> </li>
                       <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
                   
                    </ul>  
            </div>           
        </div>      
    </div>
   
   
</header>
<main>
    <div class="container">
        <div class="row">
            <section>
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title" id="meetingType"></h4><h6 id="location"></h6>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <!--<div class="col-md-3">
                                    <label>Type Of Meeting</label>
                                    <select class="form-control" disabled>
                                        <option>District level</option>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <label>Meeting Location</label>
                                    <select class="form-control" disabled>
                                        <option>District level</option>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <label>Meeting Called By</label>
                                    <select class="form-control" disabled>
                                        <option>District level</option>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <label>Meeting Date & TIme</label>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                        <input type="text" class="form-control" id="birthday">
                                    </div>
                                </div>-->
                                <div class="col-md-6 m_top20">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">Meeting Minutes</h4>
                                        </div>
                                        <div class="panel-body">
                                            <!-- <div class="input-group bin-div">
                                                <input type="text" class="form-control">
                                                <span class="input-group-addon">
                                                    <i class="glyphicon glyphicon-trash"></i>
                                                </span>
                                            </div>
                                            <div class="input-group bin-div m_top10">
                                                <input type="text" class="form-control">
                                                <span class="input-group-addon">
                                                    <i class="glyphicon glyphicon-trash"></i>
                                                </span>
                                            </div>
                                            <div class="input-group bin-div m_top10">
                                                <input type="text" class="form-control">
                                                <span class="input-group-addon">
                                                    <i class="glyphicon glyphicon-trash"></i>
                                                </span>
                                            </div>
                                            <div class="pull-right m_top10">
                                                <button class="btn btn-success btn-xs" id="add-fields">ADD</button>
                                            </div> -->
                                           
                                            <div id="addMoreDiv">
                                                <div class="input-group bin-div m_top10" id="list1">
                                                    <input type="text" class="form-control" id="minutes1"></input>
                                                    <span class="input-group-addon trash" attr_txt="minutes1">
                                                        <i class="glyphicon glyphicon-trash"></i>
                                                    </span>
													<span class="input-group-addon saveMinute" attr_txt="minutes1">
                                                        <i class="glyphicon glyphicon-trash"></i>
                                                    </span>
                                                </div>
                                            </div>   
                                           
                                                <div class="input-group bin-div m_top10" id="list" style="display:none;">
                                                    <input type="text" class="form-control txtbox"></input>
                                                    <span class="input-group-addon trash">
                                                        <i class="glyphicon glyphicon-trash"></i>
                                                    </span>
                                                </div>
                                           
                                            <button class="btn btn-success btn-xs pull-right m_top20" onclick="myFunction()">ADD</button>
											<br/><br/><div class="m_top20"><h4> Uploaded Documents </h4>
												<div class="" id="mintueDocumentDivId"></div>
											</div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <h4><i class="icon-upload"></i> Upload File</h4>
                                                    <p class="m_0">Drag and drop file below box or click on box to upload file</p>
                                                    <form id="my-awesome-dropzone" action="/target" class="dropzone"></form>
                                                    <p class="m_0 font-10 pull-right">Note: Multiple files upload. Allowed Types: PDF,Word,Excel,Jpg,JPEG,PNG</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                 <div class="col-md-6 m_top20">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">ATR</h4>
                                        </div>
                                            <div class="panel-body">
                                                <div class="row" id="atrDivId">
                                                    <!--<div class="col-md-12">
                                                      <input type="text" class="form-control" placeholder="ATR Raised by"/>
                                                       
                                                    </div>
                                                    <div class="col-md-12 m_top20">
                                                        <input type="text" class="form-control" placeholder="Grievance Given"/>
                                                       </div>
                                                    <div class="col-md-12 m_top20">
                                                      <input type="text" class="form-control" placeholder="Action Taken"/>
                                                    </div>-->
                                                    <div class="panel-body">
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <label>REQUEST</label><br/>
                                                                <textarea rows="4" cols="40" id="requestId"></textarea>
                                                            </div>
                                                            <div class="col-md-12 m_top20">
                                                                <label>ACTION TAKEN</label><br/>
                                                                <textarea rows="4" cols="40" id="actionTakenId"></textarea>
                                                            </div>
                                                            <!--<div class="col-md-12 m_top20">
                                                                <label>REQUEST FROM</label>
                                                                <input type="radio" name="requestFrom" value="private" checked>Individual
                                                                <input type="radio" name="requestFrom" value="public" checked>Public
                                                            </div>-->
                                                            <div class="col-md-12 m_top20">
                                                                <label>RAISED BY</label><br/>
                                                                <input type="text" id="raisedById"/>
                                                            </div>
                                                        </div>
                                                       
                                                        <div class="col-md-12 m_top20">
                                                            <div class="col-md-5" id="stateShowId" style="display:none;">
                                                                    <label>State</label>
                                                                    <select class="form-control" id="statesDivId">
                                                                    <option>Select State</option>
                                                                    <option value="0">All</option>
                                                                    <option value="1">AndhraPradesh</option>
                                                                    <option value="36">Telangana</option>
                                                                    </select>
                                                            </div>
                                                            <div class="col-md-1" style="height: 44px; width: 10px;">
                                                                <img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForDist" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>
                                                            </div>
                                                            <div class="col-md-5" id="DistrictShowId" style="display:none;">
                                                                    <label>District</label>
                                                                    <select class="form-control" id="districtId">
                                                                    <option>Select District</option>
                                                                    </select>
                                                            </div>
                                                            <div class="col-md-1" style="height: 44px; width: 10px;">
                                                                <img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForcons" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>
                                                            </div>
                                                            <div class="col-md-5" id="ConstShowId" style="display:none;">
                                                                    <label>Constituency</label>
                                                                    <select class="form-control" id="constituencyId">
                                                                    <option>Select Constituency</option>
                                                                    </select>
                                                            </div>
                                                            <div class="col-md-1" style="height: 44px; width: 10px;">
                                                                <img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForman" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>
                                                            </div>
                                                            <div class="col-md-5" id="ManTwnDivShowId" style="display:none;">
                                                                    <label>Mandal/Town/Division</label>
                                                                    <select class="form-control" id="manTowDivId">
                                                                    <option>Select Mandal/Town/Division</option>
                                                                    </select>
                                                            </div>
                                                           
                                                            <div class="col-md-5" id="VillWardShowId" style="display:none;">
                                                                    <label>Village/Ward</label>
                                                                    <select class="form-control" id="villWardId">
                                                                    <option>Select Village/Ward</option>
                                                                    </select>
                                                            </div>
                                                        </div>
                                                       
                                                        <div class="pull-right m_top10">
                                                            <button class="btn btn-success btn-xs">ADD</button>
                                                        </div>
													</div>
												<!--<div class="row m_top20">
                                                        <div class="col-md-12" style="margin-left: -42px;">
                                                            <div class="grievance-training">
                                                                <ul>
                                                                    <li>
                                                                        <button class="btn btn-custom-g btn-xs pull-right">Edit</button>
                                                                        <div class="data">
                                                                            <p class="m_0"><span class="text-bold">ATR Raised by</span> - kavali</p>  
                                                                            <p><span class="text-bold">Grievance Given:</span>
                                                                            Grievance text</p>
                                                                            <p class="m_top10"><span class="text-bold">Action Taken:</span>
                                                                            Grievance text</p>
                                                                        </div>
                                                                        <div class="data-edit">
                                                                            <div class="row">
                                                                                <div class="col-md-12">
                                                                                    <label>ATR Raised by</label>
                                                                                    <select class="form-control">
                                                                                        <option>Kavali</option>
                                                                                    </select>
                                                                                </div>
                                                                                <div class="col-md-12 m_top20">
                                                                                    <label>Grievance Given</label>
                                                                                    <select class="form-control">
                                                                                        <option>Kavali</option>
                                                                                    </select>
                                                                                </div>
                                                                                <div class="col-md-12 m_top20">
                                                                                    <label>Action Taken</label>
                                                                                    <select class="form-control">
                                                                                        <option>Kavali</option>
                                                                                    </select>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                     </li>
                                                                  
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </div>-->
                                               
                                            </div>
											<br/><br/><div class="m_top20"><h4> Uploaded Documents </h4>
												<div  class="" id="atrDocumentDivId"></div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<h4><i class="icon-upload"></i> Upload File</h4>
													<p class="m_0">Drag and drop file below box or click on box to upload file</p>
													<form action="/target" class="dropzone"></form>
													<p class="m_0 font-10 pull-right">Note: Multiple files upload. Allowed Types: PDF,Word,Excel,Jpg,JPEG,PNG</p>
												</div>
											</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</main>

<footer>
        <img src="css/Training/img/footer.jpg" width="100%">
</footer>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
<script src="js/TrainingProgram/component.js" type="text/javascript"></script>
<script src="js/TrainingProgram/fileupload.js" type="text/javascript"></script>
<script src="dist/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>

<script type="text/javascript">

    var partyMeetingId='${partyMeetingId}';
    
    $('.btn-custom-g').click(function(){
        $('.data-edit').toggle();
        //$('.data').hide();
    });
   
    $('#add-fields').click(function(){
        $('div.bin-div').show();
    });
   
    $(document).ready(function() {
      $('#birthday').daterangepicker(
      {
           singleDatePicker: true,
           timePicker: true,
           format: 'DD/MM/YYYY h:mm A'
      }, function(start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
      });
      
	 getPartyMeetingMinutesAtrDetails(partyMeetingId);
     getTheMeetingLevelDetails()
   });
              
    $("#mainheading").html("TRAINING PROGRAM");
    var mainDivCount=1;
    function myFunction() {
        mainDivCount = parseInt(mainDivCount)+1;
        var c = $("#list").clone(true);
            c.removeAttr("style");
            c.attr("id","list"+mainDivCount)
            c.find(".txtbox").attr("id","minutes"+mainDivCount);
            c.find(".trash").attr("attr_txt","list"+mainDivCount)
        $("#addMoreDiv").append(c);
    }
   
    $(document).on('click', '.trash', function(){
        
		var divId = $(this).attr("attr_txt");
        $("#"+divId).remove();
        $(this).remove();
		
		var minuteId = $(this).attr("attr_minuteId");
		
		var jsObj =    {minuteId : minuteId}
       
        $.ajax(
        {
            type: "POST",
            url:"deleteMeetingMinuteAction.action",
            data:{task :JSON.stringify(jsObj)}
        }
        ).done(function(result){
			if(result=="success"){
			   alert("Minute Deleted");
		   }
		});
		
    });
   
    function getTheMeetingLevelDetails(){
        var jsObj =    {}
       
        $.ajax(
        {
            type: "POST",
            url:"getTheMeetingLevelDetailsAction.action",
            data:{task :JSON.stringify(jsObj)}
        }
        ).done(function(result){
            $("#meetingLocationLevel").append('<option value="0">Select Location Type</option>');
            if(result!=null && result.length>0){
                for(var i in result){
                $("#meetingLocationLevel").append('<option value="'+result[i].locationId+'">'+result[i].locationLevel+'</option>');
                }
            }
           
        });
    }
   
    function getDistrictsForStates(state,atrId){
       
    $("#searchDataImgForDist"+atrId).show();
   
   
   var jsObj=
   {               
                stateId:state,
                elmtId:"districtList_d",
                type:"default",
                task:"getDistrictsForState"               
    }
    $.ajax({
          type:'GET',
          url: 'getDistrictsForStateAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
       $("#searchDataImgForDist"+atrId).hide();
     for(var i in result){
       if(result[i].id == 0){
         //$("#districtId").append('<option value='+result[i].id+'>ALL</option>');
       }else{
          $("#districtId"+atrId).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
       }
     }
   });
  }
 function getConstituenciesForDistricts(district,atrId){
        $("#searchDataImgForman"+atrId).show();
        var jsObj={               
            districtId:district,
            elmtId:"districtList_d",
            type:"default",
            task:"getConstituenciesForDistricts"               
        }
        $.ajax({
              type:'GET',
              url: 'getConstituenciesForADistrictAjaxAction.action',
              dataType: 'json',
              data: {task:JSON.stringify(jsObj)}
       }).done(function(result){
           $("#searchDataImgForman"+atrId).hide();
            for(var i in result){
               if(result[i].id == 0){
                 // $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
               }else{
                  $("#constituencyId"+atrId).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
               }
            }
        });
    }


   
    function getMandalVillageDetails(locationId, locationLevel,atrId){
          $("#searchDataImgForcons"+atrId).show();
        var constituencyId = 0;
        var mandalId = 0;
       
        if(locationLevel==4){
             $("#searchDataImgForcons"+atrId).show();
            constituencyId =  locationId;
        }
        if(locationLevel==5){
           
            mandalId = locationId;
        }
       
       var jsObj={               
            stateId : 0,
            districtId : 0,
            constituencyId : constituencyId,//228
            mandalId : mandalId,
            locationLevel : locationLevel,
            task:"getConstituenciesForDistricts"
        }
        $.ajax({
              type:'GET',
              url: 'getSubLevelLctnsForConstituencyAndMandalAction.action',
              dataType: 'json',
              data: {task:JSON.stringify(jsObj)}
       }).done(function(result){
            $("#searchDataImgForcons"+atrId).hide();
              var divId = "#manTowDivId"+atrId;
            //  $(divId).append("<option value=''>SELECT MANDAL/ TOWN/ DIVISION</option>");
              if(locationLevel ==5){
                  divId = "#villWardId"+atrId;
                //  $(divId).append("<option value=''>SELECT VILLAGE/ WARD </option>");
              }
              
              //$(divId).append("<option value='0'>ALL</option>");
             
            for(var i in result){
                $(divId).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
            }
        });
    }
   
    //getmeetinglocationlevel(1, 36);
    function getmeetinglocationlevel(locationLevel, locationId,atrId){
       
        if(locationLevel == 1){
            $("#stateShowId"+atrId).hide();
            $("#DistrictShowId"+atrId).show();
            $("#ConstShowId"+atrId).hide();
            $("#ManTwnDivShowId"+atrId).hide();
            $("#VillWardShowId"+atrId).hide();
            getDistrictsForStates(locationId,atrId);
        }
        else if(locationLevel == 2){
            $("#stateShowId"+atrId).hide();
            $("#DistrictShowId"+atrId).hide();
            $("#ConstShowId"+atrId).show();
            $("#ManTwnDivShowId"+atrId).hide();
            $("#VillWardShowId"+atrId).hide();
            getConstituenciesForDistricts(locationId,atrId);
        }
        else if(locationLevel == 3){
            $("#stateShowId"+atrId).hide();
            $("#DistrictShowId"+atrId).hide();
            $("#ConstShowId"+atrId).hide();
            $("#ManTwnDivShowId"+atrId).show();
            $("#VillWardShowId"+atrId).hide();
            getMandalVillageDetails(locationId, 4,atrId);
        }
        else if(locationLevel == 4){
            $("#stateShowId"+atrId).hide();
            $("#DistrictShowId"+atrId).hide();
            $("#ConstShowId"+atrId).hide();
            $("#ManTwnDivShowId"+atrId).hide();
            $("#VillWardShowId"+atrId).show();
            getMandalVillageDetails(locationId, 5,atrId);
        }
    }
	
	function getPartyMeetingMinutesAtrDetails(partyMeetingID){
		
		var jsObj={				
			partyMeetingID : partyMeetingID
		}
		$.ajax({
			  type:'GET',
			  url: 'getPartyMeetingMinutesAtrDetailsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   
		   if(result!=null){
			   $("#meetingType").html(result.partyMeetingType);
			   $("#location").html(result.meetingLevel);
			   
			   if(result.minuteDocuments!=null){
					$("#mintueDocumentDivId").html(result.minuteDocuments);
			   }
			   if(result.atrDocuments!=null){
				   $("#atrDocumentDivId").html(result.atrDocuments);
			   }
			   
			   
			   if(result.minutesDetails!=null && result.minutesDetails.length>0){
				   var str='';
				   for(var i in result.minutesDetails){
					   mainDivCount=i;
						str+='<div class="input-group bin-div m_top10" id="list'+mainDivCount+'">';
						str+='<input type="text" class="form-control" id="minutes'+mainDivCount+'" onclick=enableSaveOption("'+mainDivCount+'"); value="'+result.minutesDetails[i].minutePoint+'"></input>';
						str+='<span class="input-group-addon trash" attr_txt="minutes'+mainDivCount+'" attr_minuteId="'+result.minutesDetails[i].partyMeetingMinuteId+'">';
							str+='<i class="glyphicon glyphicon-trash"></i>';
						str+='</span>';
						str+='<span class="input-group-addon saveMinute" style="display:none;" attr_minuteId="'+result.minutesDetails[i].partyMeetingMinuteId+'" id="save'+mainDivCount+'" attr_txt="minutes'+mainDivCount+'">';
							str+='<i class="glyphicon glyphicon-ok"></i>';
						str+='</span>';
						str+='</div>';
				   }
				   $("#addMoreDiv").html(str);
			   }
			   
			   if(result.atrDetails!=null && result.atrDetails.length>0){
				   var str='';
				   for(var i in result.atrDetails){
					   str+='<div class="panel-body">';
					   str+='<div class="row">';
					   str+='<div class="col-md-12">';
					   str+='<label>REQUEST</label><br/>';
					   str+='<textarea rows="4" cols="40" id="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'"></textarea>';
					   str+='</div>';
					   str+='<div class="col-md-12 m_top20">';
					   str+='<label>ACTION TAKEN</label><br/>';
					   str+='<textarea rows="4" cols="40" id="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'"></textarea>';
					   str+='</div>';
					   str+='<div class="col-md-12 m_top20">';
					   str+='<label>RAISED BY</label><br/>';
					   str+='<input type="text" id="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" value="'+result.atrDetails[i].raisedBy+'"/>';
					   str+='</div>';
					   str+='</div>';
					   str+='<div class="col-md-12 m_top20">';
					   str+='<div class="col-md-12" id="stateShowId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="display:none;">';
					   str+='<label>State</label>';
					   str+='<select class="form-control" id="statesDivId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select State</option>';
					   str+='<option value="0">All</option>';
					   str+='<option value="1">AndhraPradesh</option>';
					   str+='<option value="36">Telangana</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='<div class="col-md-1" style="height: 44px; width: 10px;">';
					   str+='<img src="./images/icons/search.gif" class="offset7"  id="searchDataImgForDist'+result.atrDetails[i].partyMeetingAtrPointId+'" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>';
					   str+='</div>';
					   str+='<div class="col-md-12" id="DistrictShowId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="display:none;">';
					   str+='<label>District</label>';
					   str+='<select class="form-control" id="districtId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select District</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='<div class="col-md-1" style="height: 44px; width: 10px;">';
					   str+='<img src="./images/icons/search.gif"" class="offset7"  id="searchDataImgForcons'+result.atrDetails[i].partyMeetingAtrPointId+'" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>';
					   str+='</div>';
					   str+='<div class="col-md-12" id="ConstShowId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="display:none;">';
					   str+='<label>Constituency</label>';
					   str+='<select class="form-control" id="constituencyId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select Constituency</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='<div class="col-md-1" style="height: 44px; width: 10px;">';
					   str+='<img src="./images/icons/search.gif" class="offset7"  id="searchDataImgForman'+result.atrDetails[i].partyMeetingAtrPointId+'" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>';
					   str+='</div>';
					   str+='<div class="col-md-12" id="ManTwnDivShowId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="display:none;">';
					   str+='<label>Mandal/Town/Division</label>';
					   str+='<select class="form-control" id="manTowDivId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select Mandal/Town/Division</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='<div class="col-md-12" id="VillWardShowId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="display:none;">';
					   str+='<label>Village/Ward</label>';
					   str+='<select class="form-control" id="villWardId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select Village/Ward</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='</div>';
					   /* str+='<div class="row">';
					   str+='<div class="col-md-12">';
					   str+='<h4><i class="icon-upload"></i> Upload File</h4>';
					   str+='<p class="m_0">Drag and drop file below box or click on box to upload file</p>';
					   str+='<form action="/target" class="dropzone"></form>';
					   str+='<p class="m_0 font-10 pull-right">Note: Multiple files upload. Allowed Types: PDF,Word,Excel,Jpg,JPEG,PNG</p>';
					   str+='</div>';
					   str+='</div>'; */
					   str+='<div class="pull-right m_top10">';
					   str+='<button class="btn btn-success btn-xs">ADD</button>';
					   str+='</div>';
					   str+='</div>';
					   
				   }
				   
				   $("#atrDivId").html(str);
				   
				   for(var i in result.atrDetails){
					   $("#requestId"+result.atrDetails[i].partyMeetingAtrPointId).html(result.atrDetails[i].request);
					   $("#actionTakenId"+result.atrDetails[i].partyMeetingAtrPointId).html(result.atrDetails[i].actionTaken);
					   
					   getmeetinglocationlevel(result.atrDetails[i].locationScopeId,result.atrDetails[i].locationValue,result.atrDetails[i].partyMeetingAtrPointId);
					   
				   }
			   }
		   }
	   });
	}
	
	function enableSaveOption(txtBoxCnt){
		$("#save"+txtBoxCnt).show();
	}
	
	$(document).on('click', '.saveMinute', function(){
		var textBoxId = $(this).attr("attr_txt");
		var minuteText = $("#"+textBoxId).val();
		var minuteId = $(this).attr("attr_minuteId");
		
		var jsObj={		
			minuteId : minuteId,
			minuteText : minuteText
		}
		$.ajax({
			  type:'GET',
			  url: 'updateMeetingMinutePointAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   if(result=="success"){
			   alert("Updated Successfully");
		   }
		});
	});
	
	
</script>
</body>
</html>