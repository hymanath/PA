<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Party Meetings - MINUTES & ATR POINTS</title>

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
body,h1,h2,h3,h4,h5,h6{color:#666 !important}
.text-bold{font-weight:bold}
.m_0
{
	margin:0px
}
.uploadBox
{
	border:1px solid #ccc;
	padding:8px;
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
.mouse-over
    {
        padding:0px;
    }
    
    .mouse-over:hover:after
    {
        content:"click here to expand";
        position:absolute;
        left:35px;
        z-index:9999;
        color:#fff;
        background:rgba(0,0,0,0.5);
        padding:10px;
        font-size:22px;
		margin-top:20px;
    }
</style>

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/yahoo/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	<!-- YUI Dependency files (End) -->
	
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
                        <div class="panel-heading" style="background-color:#CCC">
                            <h4 class="panel-title" id="meetingType"></h4><h6 id="location" class="text-bold"></h6>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-6 m_top20">
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="background-color:#DDD">
                                            <h4 class="panel-title text-bold">Meeting Minutes</h4>
                                        </div>
                                        <div class="panel-body">
                                            <div id="addMoreDiv">
                                                <div class="input-group bin-div m_top10" id="list1">
                                                    <input type="text" class="form-control" id="minutes1"></input>
                                                    <span class="input-group-addon trash" attr_txt="minutes1">
                                                        <i class="glyphicon glyphicon-trash"></i>
                                                    </span>
													<span class="input-group-addon saveMinute" attr_minuteid="0" attr_txt="minutes1">
                                                        <i class="glyphicon glyphicon-ok"></i>
                                                    </span>
                                                </div>
                                            </div>   
                                           
                                                <div class="input-group bin-div m_top10" id="list" style="display:none;">
                                                    <input type="text" class="form-control txtbox"></input>
                                                    <span class="input-group-addon trash" style="background-color:#CCC;cursor:pointer" data-toggle="tooltip" data-placement="top" title="Delete">
                                                        <i class="glyphicon glyphicon-trash"></i>
                                                    </span>
													<span class="input-group-addon saveMinute" style="background-color:#CCC;cursor:pointer;border-left:1px solid #ddd" data-toggle="tooltip" data-placement="top" title="Save">
														<i class="glyphicon glyphicon-ok"></i>
													</span>
                                                </div>
                                           
                                            <button class="btn btn-success btn-xs pull-right m_top20" onclick="myFunction()">ADD</button>
											<br/><br/><div class="m_top20"><h4 class="text-bold"> Uploaded Documents<hr class="m_0" style="border-color:#666"/> </h4>

												<div class="" id="mintueDocumentDivId"></div>
											</div>
											<div style="display:none;" id="fileDiv" class="row col-md-12">
												<input type="file" class="m_top10 fileCls col-md-6" name="imageForDisplay" style="width: 225px;"/>
												<div class="col-md-6"><span class="btn btn-success btn-xs m_top10 removeBtnCls tooltipBtnDiv" data-toggle="tooltip" data-placement="top" title="Remove File"><i class="glyphicon glyphicon-minus"></i></span></div>
											</div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <form id="uploadMinutesDocs" name="uploadMinutesDocs" class="uploadBox">
														<input type="file" class="m_top10 fileCls" name="imageForDisplay" id="fileDivId0" style="width: 225px;margin-left:15px;"/>
														<div id="ExtraFiles"></div>
														<input type= "button" value="Upload Minutes Files" style="margin-left:15px;margin-top:10px;background-color:#666;border-color:#666" class="btn btn-success btn-sm" id="uploadMinutesDocsId"></input>
														<input type="hidden" name="partyMeeting" id="partyMeetingId"/>
														<input type="hidden" name="partyMeetingType" value="MINUTE"/>
													</form>
													<button class="btn btn-success btn-xs pull-right m_top20 tooltipBtnDiv" data-toggle="tooltip" data-placement="top" title="Add one more file" id="addFiles"><i class="glyphicon glyphicon-plus"></i></button>
                                                    <p class="m_0 font-10 pull-right">Note: Multiple files upload. Allowed Types: PDF,Word,Excel,Jpg,JPEG,PNG</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                 <div class="col-md-6 m_top20">
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="background-color:#DDD">
                                            <h4 class="panel-title text-bold">ATR</h4>
                                        </div>
                                            <div class="panel-body">
											<div>
                                                <div id="atrDivId"></div>
											<div class="pull-right m_top10">
											<button class="btn btn-success btn-xs addingRequests">ADD</button>
											</div>
											</div>
											<br/><br/><div class="m_top20"><h4 class="text-bold"> Uploaded Documents <hr class="m_0" style="border-color:#666"/></h4>
												<div  class="" id="atrDocumentDivId"></div>
											</div>
											<div class="row">
												 <div class="col-md-12">
                                                    <form id="uploadATRDocs" name="uploadATRDocs" class="uploadBox	">
														<input type="file" class="m_top10 fileCls" name="imageForDisplay" id="atrFileId0" style="width: 225px;margin-left:15px;"/>
														<div id="ExtraFilesATR"></div>
														<input type= "button" value="Upload ATR Files" style="margin-left:15px;margin-top:10px;background-color:#666;border-color:#666" class="btn btn-success btn-sm" id="uploadATRDocsId"></input>
														<input type="hidden" name="partyMeeting" id="partyMeetingATRId"/>
														<input type="hidden" name="partyMeetingType" value="ATR"/>
													</form>
													<button class="btn btn-success btn-xs pull-right m_top20" id="addATRFiles"><i class="glyphicon glyphicon-plus"></i></button>
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

<div class="modal fade" id="myModashow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 <div class="modal-dialog">
		 <div class="modal-content">
			<div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">ATR Points</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					 <div class="col-md-12">
						 <label>REQUEST</label><br/>
						 <textarea rows="2" id="request" class="form-control"></textarea>
					 </div>
					 <div class="col-md-12 m_top20">
						 <label>ACTION TAKEN</label><br/>
						 <textarea rows="2" id="actionTaken" class="form-control"></textarea>
					 </div>
					 
					 <div class="col-md-12 m_top20">
						 <label>RAISED BY</label><br/>
						 <input type="text" id="raisedBy"  class="form-control"/>
					 </div>
					 <div class="col-md-12" id="locationInPop"></div>
				 </div>
			 </div>
			 <div class="modal-footer">
				 <button type="button" class="btn btn-success btn-sm" id="saveBtn" data-dismiss="modal"><i class="icon-check"></i> SAVE </button>
				 <button type="button" class="btn btn-success btn-sm" style="background-color:#666;border-color:#666" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> CANCEL</button>
			 </div>
		 </div>
	 </div>
 </div>
 <div style="display:none;">
	<div id="DistrictShowId0">
		<label>DISTRICT</label>
		<select id="districtId0" class="form-control locationCls"><option value="0"> Select District</option></select>
	</div>
	<div id="ConstShowId0">
		<label>CONSTITUENCY</label>
		<select id="constituencyId0" class="form-control locationCls"><option value="0"> Select Constituency</option></select>
	</div>
	<div id="ManTwnDivShowId0">
		<label>MANDAL/ TOWN/ DIVISION</label>
		<select id="manTowDivId0" class="form-control locationCls"><option value="0"> Select Mandal/Town/Division</option></select>
	</div>
	<div id="VillWardShowIdSpan0">
		<label>VILLAGE/ WARD</label>
		<select id="villWardId0" class="form-control locationCls"><option value="0"> Select Village/Ward</option></select>
	</div>
	
 </div>

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
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<!-- Add fancyBox main JS and CSS files -->
<script type="text/javascript" src="pdfexpand/source/jquery.fancybox.js?v=2.1.5"></script>
<link rel="stylesheet" type="text/css" href="pdfexpand/source/jquery.fancybox.css?v=2.1.5" media="screen" />
<script type="text/javascript">

// Load the Google Transliterate API
    google.load("elements", "1", {
        packages: "transliteration"
    });
	
    function onLoad() {
      var options = {
          sourceLanguage:
              google.elements.transliteration.LanguageCode.ENGLISH,
          destinationLanguage:
              [google.elements.transliteration.LanguageCode.TELUGU],
          shortcutKey: 'alt+t',
          transliterationEnabled: true
      };

      // Create an instance on TransliterationControl with the required
      // options.
      var control =
          new google.elements.transliteration.TransliterationControl(options);

      // Enable transliteration in the textbox with id
      // 'descrptionId'.
      
		control.makeTransliteratable(['request']);
		control.makeTransliteratable(['actionTaken']);
		control.makeTransliteratable(['raisedBy']);
		
    }
    google.setOnLoadCallback(onLoad);
	
	var minutesFiles = 0;
	$("#addFiles").click(function(){
		minutesFiles = minutesFiles +1;
		var c = $("#fileDiv").clone(true);
			c.removeAttr("style");
			c.attr("id","fileDivId"+minutesFiles);
            c.find(".fileCls").attr("id","fileId"+minutesFiles);
            c.find(".removeBtnCls").attr("id","remove"+minutesFiles);
			c.find(".removeBtnCls").attr("attr_count",minutesFiles);
	    $("#ExtraFiles").append(c);
	});
	$('.tooltipBtnDiv').tooltip();
	var atrFiles = 0;
	$("#addATRFiles").click(function(){
		atrFiles = atrFiles +1;
		var c = $("#fileDiv").clone(true);
			c.removeAttr("style");
			c.attr("id","atrFileDivId"+atrFiles);
            c.find(".fileCls").attr("id","atrFileId"+atrFiles);
            c.find(".removeBtnCls").attr("id","removeATR"+atrFiles);
			c.find(".removeBtnCls").attr("attr_count",atrFiles);
	    $("#ExtraFilesATR").append(c);
	});
	
	

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

    $("#mainheading").html("PARTY MEETINGS");
    var mainDivCount=1;
    function myFunction() {
        mainDivCount = parseInt(mainDivCount)+1;
        var c = $("#list").clone(true);
            c.removeAttr("style");
            c.attr("id","list"+mainDivCount)
            c.find(".txtbox").attr("id","minutes"+mainDivCount);
            c.find(".trash").attr("attr_txt","minutes"+mainDivCount);
			c.find(".saveMinute").attr("id","save"+mainDivCount);
			c.find(".saveMinute").attr("attr_txt","minutes"+mainDivCount);
			c.find(".saveMinute").attr("attr_minuteId","0");
			c.find(".trash").attr("attr_minuteId","0");
			c.find(".trash").attr("title",'Delete');
        $("#addMoreDiv").append(c);
		$('.saveMinute').tooltip();
		$('.trash').tooltip();
}
   var maximumDivCount=1;
   /* $(document).on('click', '.addingRequests', function(){
	   maximumDivCount = parseInt(maximumDivCount)+1;
	   var c = $("#requestDivId").clone(true);
           c.attr("id","requestDivId"+maximumDivCount)
           c.find(".removebtn").attr("id","removeDivId"+maximumDivCount);
		   c.find(".removebtn").attr("id","requestDivId"+maximumDivCount);
		  $("#atrDivId").append(c);
    }); */
   
   /*  $(document).on('click', '.removebtn', function(){
		
		var removedivId = $(this).attr("id");
        $("#"+removedivId).remove();
        //$(this).remove();
    }); */
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
            $("#meetingLocationLevel").append('<option value="0">Meeting Level</option>');
            if(result!=null && result.length>0){
                for(var i in result){
                $("#meetingLocationLevel").append('<option value="'+result[i].locationId+'">'+result[i].locationLevel+'</option>');
                }
            }
           
        });
    }
   
    function getDistrictsForStates(state,atrId,locationLevelValue){
       
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
		   if(locationLevelValue==result[i].id){
			   $("#districtId"+atrId).append('<option selected value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		   else{
				$("#districtId"+atrId).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
          
       }
     }
	 
	 $("#locationInPop").html("");//locationCls
	 
	 
	 var lctn = $("#districtId"+atrId+" option:selected").text();
	 var lctnId = $("#districtId"+atrId+" option:selected").val();
	 $("#DistrictShowIdSpan"+atrId).html("<h4 class='selectedLctn' attr_lctnId='"+lctnId+"'>"+lctn+" District</h4>");
	 
	 var locationDiv = $("#DistrictShowId"+atrId);
	 locationDiv.find(".locationCls").attr("id","locationDivId");
	 
	 $("#locationInPop").html($("#DistrictShowId"+atrId).html());
	 $("#locationInPop").attr("locationScope",1);
   });
  }
 function getConstituenciesForDistricts(district,atrId,locationLevelValue){
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
				   if(locationLevelValue==result[i].id){
					   $("#constituencyId"+atrId).append('<option value='+result[i].id+' selected>'+result[i].name+'</option>');
				   }else{
						$("#constituencyId"+atrId).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				   }
                  
               }
            }
			
			$("#locationInPop").html("");
			
			var lctn = $("#constituencyId"+atrId+" option:selected").text();
			var lctnId = $("#constituencyId"+atrId+" option:selected").val();
			$("#ConstShowIdSpan"+atrId).html("<h4 class='selectedLctn' attr_lctnId='"+lctnId+"'>"+lctn+" Constituency</h4>");
			
			var locationDiv = $("#ConstShowId"+atrId);
			locationDiv.find(".locationCls").attr("id","locationDivId");
			
			$("#locationInPop").html($("#ConstShowId"+atrId).html());
			$("#locationInPop").attr("locationScope",2);
        });
    }


   
    function getMandalVillageDetails(locationId, locationLevel,atrId,locationLavelValue){
          $("#searchDataImgForcons"+atrId).show();
        var constituencyId = 0;
        var mandalId = 0;
        var locationTemp = "#ManTwnDivShowIdSpan"+atrId;
		
	   
        if(locationLevel==4){
			$("#locationInPop").attr("locationScope",3);
             $("#searchDataImgForcons"+atrId).show();
            constituencyId =  locationId;
        }
        if(locationLevel==5){
			$("#locationInPop").attr("locationScope",4);
           locationTemp = "#VillWardShowIdSpan"+atrId;
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
				if(locationLavelValue==result[i].locationId){
					$(divId).append('<option value='+result[i].locationId+' selected>'+result[i].locationName+'</option>');
				}else{
					$(divId).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
                
            }
			
			
			var lctn = $(""+divId+" option:selected").text();
			var lctnId = $(""+divId+" option:selected").val();
			
			$(locationTemp).html("<h4 class='selectedLctn m_0' attr_lctnId='"+lctnId+"'>"+lctn+"</h4>");
			
			var locationDiv = $(locationTemp);
			locationDiv.find(".locationCls").attr("id","locationDivId");
			
			$(locationTemp).val($(divId).text());
        });
    }
   
    //getmeetinglocationlevel(1, 36);
    function getmeetinglocationlevel(locationLevel, locationId,atrId,locationLevelValue){
       //alert(locationLevel+"---"+locationId+"---"+atrId+"---"+locationLevelValue);
	   
        if(locationLevel == 1){
            $("#stateShowIdSpan"+atrId).hide();
            $("#DistrictShowIdSpan"+atrId).show();
            $("#ConstShowIdSpan"+atrId).hide();
            $("#ManTwnDivShowIdSpan"+atrId).hide();
            $("#VillWardShowIdSpan"+atrId).hide();
			
			getDistrictsForStates(locationId,atrId,locationLevelValue);
        }
        else if(locationLevel == 2){
            $("#stateShowIdSpan"+atrId).hide();
            $("#DistrictShowIdSpan"+atrId).hide();
            $("#ConstShowIdSpan"+atrId).show();
            $("#ManTwnDivShowIdSpan"+atrId).hide();
            $("#VillWardShowIdSpan"+atrId).hide();
			
            getConstituenciesForDistricts(locationId,atrId,locationLevelValue);
        }
        else if(locationLevel == 3){
            $("#stateShowIdSpan"+atrId).hide();
            $("#DistrictShowIdSpan"+atrId).hide();
            $("#ConstShowIdSpan"+atrId).hide();
            $("#ManTwnDivShowIdSpan"+atrId).show();
            $("#VillWardShowIdSpan"+atrId).hide();
			
			
            getMandalVillageDetails(locationId, 4,atrId,locationLevelValue);
        }
        else if(locationLevel == 4){
            $("#stateShowIdSpan"+atrId).hide();
            $("#DistrictShowIdSpan"+atrId).hide();
            $("#ConstShowIdSpan"+atrId).hide();
            $("#ManTwnDivShowIdSpan"+atrId).hide();
            $("#VillWardShowIdSpan"+atrId).show();
			
			$("#VillWardShowIdSpan"+atrId).val($("#VillWardId"+atrId).text());
            getMandalVillageDetails(locationId, 5,atrId,locationLevelValue);
        }
    }
	
	var globalLocationId=0;
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
			   
			   
			   
			   if(result.minutesDocuments!=null && result.minutesDocuments.length>0){
				   var str='';
				    for(var i in result.minutesDocuments){
					    str+='<div class="col-md-12 row" id="minuteDocFileId'+result.minutesDocuments[i].id+'" style="padding:6px;">';
					    str+='<a target="_tab" class="col-md-10" href="'+result.minutesDocuments[i].url+'">'+result.minutesDocuments[i].name+'</a>';
						str+='<div class="deleteDoc col-md-2" attr_type="minute" id="'+result.minutesDocuments[i].id+'"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</div>';
						
				   }
				   $("#mintueDocumentDivId").html(str);
			   }
			    if(result.atrDocuments!=null && result.atrDocuments.length>0){
				   var str='';
				   for(var i in result.atrDocuments){
					    str+='<div class="col-md-12 row" style="padding:6px;" id="atrDocFileId'+result.atrDocuments[i].id+'">';
					    str+='<a target="_tab" class="col-md-10" href="'+result.atrDocuments[i].url+'">'+result.atrDocuments[i].name+'</a>';
						str+='<div class="pull-right deleteDoc col-md-2" attr_type="atr" id="'+result.atrDocuments[i].id+'"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</div>';
						
				   }
				   $("#atrDocumentDivId").html(str);
				  // $('.fancybox').fancybox();
			   }
			   
			   
			   if(result.minutesDetails!=null && result.minutesDetails.length>0){
				   minutesFiles = result.minutesDetails.length;
				   var str='';
				   for(var i in result.minutesDetails){
					   mainDivCount=i;
						str+='<div class="input-group bin-div m_top10" id="list'+mainDivCount+'">';
						str+='<input type="text" class="form-control" id="minutes'+mainDivCount+'" onclick=enableSaveOption("'+mainDivCount+'"); value="'+result.minutesDetails[i].minutePoint+'"></input>';
						str+='<span class="input-group-addon trash" style="background-color:#CCC;cursor:pointer" data-toggle="tooltip" data-placement="top" title="Delete" attr_txt="minutes'+mainDivCount+'" attr_minuteId="'+result.minutesDetails[i].partyMeetingMinuteId+'">';
							str+='<i class="glyphicon glyphicon-trash"></i>';
						str+='</span>';
						str+='<span class="input-group-addon saveMinute" style="display:none;" attr_minuteId="'+result.minutesDetails[i].partyMeetingMinuteId+'" id="save'+mainDivCount+'" attr_txt="minutes'+mainDivCount+'">';
							str+='<i class="glyphicon glyphicon-ok"></i>';
						str+='</span>';
						str+='</div>';
				   }
				   $("#addMoreDiv").html(str);
				   $('.trash').tooltip()
			   }
			   
			   if(result.atrDetails!=null && result.atrDetails.length>0){
				   atrFiles = result.atrDetails.length;
				   var str='';
				   for(var i in result.atrDetails){
				   	   maximumDivCount=i;
					   str+='<div id="atrInnerDiv'+result.atrDetails[i].partyMeetingAtrPointId+'" class="m_top10">';
					   str+='<div class="panel-body" id="requestDivId" style="border:1px solid #c3c3c3;">';
					   str+='<div class="row">';
					   str+='<div class="pull-right" style="margin-right:5px;">';
				       str+=' <button class="btn btn-default btn-xs ToolTipDiv deleteAtr"  attr_reqId="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_actntknId="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_rsdById="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_atrId="'+result.atrDetails[i].partyMeetingAtrPointId+'" data-toggle="tooltip" data-placement="top" title="Delete" style="background-color:#CCC" attr_txt="removeDivId'+maximumDivCount+'"><i class="glyphicon glyphicon-trash"></i></button>';
					   str+=' <button class="btn btn-default btn-xs editBtn ToolTipDiv"  data-toggle="tooltip" data-placement="top" title="Edit" style="background-color:#CCC" attr_id="'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_txt="editDivId'+maximumDivCount+'"><i class="glyphicon glyphicon-edit"></i></button>';
					   str+=' </div>';
					   str+='<div class="col-md-12">';
					   str+='<span class="text-bold">REQUEST : </span>';
					   str+='<span class="updaterequest" id="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');"></span>';
					   str+='</div>';
					   str+='<div class="col-md-12 m_top10">';
					   str+='<span class="text-bold">ACTION TAKEN : </span>';
					   str+='<span class="updateaction" id="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');"></span>';
					   str+='</div>';
					   str+='<div class="col-md-12 m_top10">';
					   str+='<span class="text-bold">RAISED BY : </span>';
					   str+='<span class="updateraisedBy" id="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].raisedBy+'</span>';
					   str+='</div>';
					   str+='</div>';
					   str+='<div class="col-md-12 m_top10">';
					   str+='<div class="col-md-12" id="stateShowId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="display:none;">';
					   str+='<label>State</label>';
					   str+='<select class="form-control" id="statesDivId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select State</option>';
					   str+='<option value="0">All</option>';
					   str+='<option value="1">AndhraPradesh</option>';
					   str+='<option value="36">Telangana</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='<div class="col-md-1">';
					   str+='<img src="./images/icons/search.gif" class="offset7"  id="searchDataImgForDist'+result.atrDetails[i].partyMeetingAtrPointId+'" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;height: 22px; width: 15px;"/>';
					   str+='</div>';
					   str+='<div class="col-md-12" id="DistrictShowId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="display:none;">';
					   str+='<label>District</label>';
					   str+='<select class="form-control locationCls" id="districtId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select District</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='<span style="display:none;" id="DistrictShowIdSpan'+result.atrDetails[i].partyMeetingAtrPointId+'"></span>';
					   str+='<div class="col-md-1">';
					   str+='<img src="./images/icons/search.gif"" class="offset7"  id="searchDataImgForcons'+result.atrDetails[i].partyMeetingAtrPointId+'" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;height: 22px; width: 15px;"/>';
					   str+='</div>';
					   str+='<div class="col-md-12" id="ConstShowId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="display:none;">';
					   str+='<label>Constituency</label>';
					   str+='<select class="form-control locationCls" id="constituencyId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select Constituency</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='<span style="display:none;" id="ConstShowIdSpan'+result.atrDetails[i].partyMeetingAtrPointId+'"></span>';
					   
					   str+='<div class="col-md-1">';
					   str+='<img src="./images/icons/search.gif" class="offset7"  id="searchDataImgForman'+result.atrDetails[i].partyMeetingAtrPointId+'" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;height: 22px; width: 15px;"/>';
					   str+='</div>';
					   str+='<div class="col-md-12" id="ManTwnDivShowId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="display:none;">';
					   str+='<label>Mandal/Town/Division</label>';
					   str+='<select class="form-control locationCls" id="manTowDivId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select Mandal/Town/Division</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='<span style="display:none;" id="ManTwnDivShowIdSpan'+result.atrDetails[i].partyMeetingAtrPointId+'"></span>';
					   
					   str+='<div class="col-md-12" id="VillWardShowId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="display:none;">';
					   str+='<label>Village/Ward</label>';
					   str+='<select class="form-control locationCls" id="villWardId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select Village/Ward</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='<span style="display:none;" id="VillWardShowIdSpan'+result.atrDetails[i].partyMeetingAtrPointId+'"></span>';
					   str+='</div>';
					   /* str+='<div class="row">';
					   str+='<div class="col-md-12">';
					   str+='<h4><i class="icon-upload"></i> Upload File</h4>';
					   str+='<p class="m_0">Drag and drop file below box or click on box to upload file</p>';
					   str+='<form action="/target" class="dropzone"></form>';
					   str+='<p class="m_0 font-10 pull-right">Note: Multiple files upload. Allowed Types: PDF,Word,Excel,Jpg,JPEG,PNG</p>';
					   str+='</div>';
					   str+='</div>'; */
					   str+='<div class="pull-right m_top10" style="display:none;" id="btnsDiv'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   //str+='<button class="btn btn-success btn-xs  updateAtr" style="padding:3px;" attr_reqId="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_actntknId="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_rsdById="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_atrId="'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_locationScope="'+result.atrDetails[i].locationScopeId+'">UPDATE</button>';
					   //str+='<button class="btn btn-success btn-xs deleteAtr" attr_reqId="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="padding:3px;" attr_actntknId="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_rsdById="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_atrId="'+result.atrDetails[i].partyMeetingAtrPointId+'">DELETE</button>';
					   str+='</div>';
					   str+='</div>';
					   str+='</div>';
					   
				   }
				   
				   $("#atrDivId").html(str);
				    $('.ToolTipDiv').tooltip()
				   for(var i in result.atrDetails){
					   //globalLocationId = result.locationValue;
					   $("#requestId"+result.atrDetails[i].partyMeetingAtrPointId).html(result.atrDetails[i].request);
					   $("#actionTakenId"+result.atrDetails[i].partyMeetingAtrPointId).html(result.atrDetails[i].actionTaken);
					   getmeetinglocationlevel(result.atrDetails[i].locationScopeId,result.locationValue,result.atrDetails[i].partyMeetingAtrPointId,result.atrDetails[i].locationValue);
					   
				   }
				   
				   
			   }else{
				   $("#atrDivId").html("<h5>No ATR Points</h5>");
				   getmeetinglocationlevel(result.meetingLevelId,result.locationValue,0,0);
			   }
		   }
	   });
	}
	
	function enableSaveOption(txtBoxCnt){
		$("#save"+txtBoxCnt).show();
	}
	
	$(document).on('click', '.saveMinute', function(){
		var saveBtnId = $(this).attr("id");
		var textBoxId = $(this).attr("attr_txt");
		var minuteText = $("#"+textBoxId).val();
		var minuteId = $(this).attr("attr_minuteId");
		var jsObj={		
			minuteId : minuteId,
			minuteText : minuteText,
			partyMeetingId : partyMeetingId//global var
		}
		$.ajax({
			  type:'GET',
			  url: 'updateMeetingMinutePointAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
		   if(result=="success"){
			   alert("Updated Successfully");
			  $("#"+saveBtnId).hide();
		   }
		});
	});
	
	$("#uploadMinutesDocsId").click(function(){
		$("#partyMeetingId").val(partyMeetingId);
		
		var files = [];
		$("#uploadMinutesDocs input[type=file]").each(function() {
			if($(this).val().trim().length>0){
				files.push($(this).val());
			}
		});
	
		if(files.length==0){
			alert("Please Select Documents");
			return;
		}
		
		var uploadHandler = {
				upload: function(o) {
				    uploadResult = o.responseText;
					showingStatus(uploadResult,"MINUTE");
				}
			};

		YAHOO.util.Connect.setForm('uploadMinutesDocs',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadMinutesDocAction.action',uploadHandler);
	});
	
	$("#uploadATRDocsId").click(function(){
		$("#partyMeetingATRId").val(partyMeetingId);
		var files = [];
		$("#uploadATRDocs input[type=file]").each(function() {
			if($(this).val().trim().length>0){
				files.push($(this).val());
			}
		});
	
		if(files.length==0){
			alert("Please Select Documents");
			return;
		}
		
		var uploadHandler = {
				upload: function(o) {
				    uploadResult = o.responseText;
					showingStatus(uploadResult,"ATR");
				}
			};

		YAHOO.util.Connect.setForm('uploadATRDocs',true);
		YAHOO.util.Connect.asyncRequest('POST','uploadMinutesDocAction.action',uploadHandler);
	});
	
	
	function showingStatus(myResult,docSourceType){
		
		var result = myResult;
		if (result.indexOf("success") >= 0){
			alert("File Uploaded Successfully");
			rebuildTheDocumentsDiv(docSourceType);
		}
		else{
			alert("Failed to Upload.. Please Try Again");
		}
		
		partyMeetingDocs(docSourceType);
	}
	
	function rebuildTheDocumentsDiv(fromType){
		var jsObj={		
			partyMeetingId : '${partyMeetingId}'
		}
		$.ajax({
			  type:'GET',
			  url: 'getDocumentDetailsForAMeetingAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(fromType=="MINUTE"){
				if(result.minutesDocuments!=null && result.minutesDocuments.length>0){
				   var str='';
				    for(var i in result.minutesDocuments){
					    str+='<div id="docDiv'+result.minutesDocuments[i].id+'">';
					    str+='<a target="_tab" href="'+result.minutesDocuments[i].url+'">'+result.minutesDocuments[i].name+'</a>';
						str+='<div class="pull-right deleteDoc" id="'+result.minutesDocuments[i].id+'"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</div><br/>';
						
				   }
				   $("#mintueDocumentDivId").html(str);
			   }
			}
			else if(fromType=="ATR"){
				if(result.atrDocuments!=null && result.atrDocuments.length>0){
				   var str='';
				   for(var i in result.atrDocuments){
					    str+='<div id="docDiv'+result.atrDocuments[i].id+'">';
					    str+='<a target="_tab" href="'+result.atrDocuments[i].url+'">'+result.atrDocuments[i].name+'</a>';
						str+='<div class="pull-right deleteDoc" id="'+result.atrDocuments[i].id+'"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</div><br/>';
						
				   }
				   $("#atrDocumentDivId").html(str);
			   }
			}
			    
		});
	}
	
	
	$(document).on("click",".removeBtnCls",function(){
		$(this).parent().parent().remove();
	});
	
	
	function showBtnsDiv(atrId){
		$("#btnsDiv"+atrId).show();
	}
	
	$(document).on('click', '.deleteDoc', function(){
		var docId = $(this).attr("id");
		var type = $(this).attr("attr_type");
		var divId = "#"+type+"DocFileId"+docId;
		
		var jsObj={		
			docId : docId
		}
		$.ajax({
			  type:'GET',
			  url: 'deletePartyMeetingDocumentAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result=="success"){
				//alert("Document Deleted");
				$("#docDiv"+docId).remove();
				$(""+divId+"").remove();
			}else{
				alert("Please Try Again");
			}
		});
	});
	
	var editAtrId='';
	 $(document).on('click', '.editBtn', function(){
		 $("#myModashow").modal("show");
		 var req=$(this).parent().parent().find(".updaterequest").text();
		 var actn=$(this).parent().parent().find(".updateaction").text();
		 var raised=$(this).parent().parent().find(".updateraisedBy").text();
		 var attrId = $(this).attr("attr_id");
		 var lctnVal = $(this).parent().parent().parent().find(".selectedLctn").attr("attr_lctnid");
		 
		 $("#saveBtn").attr("attr_id",attrId);
		 $("#request").val(req);
		 $("#actionTaken").val(actn);
		 $("#raisedBy").val(raised);
		 
		 $('#locationInPop option[value="'+lctnVal+'"]').prop('selected', true);
	});
	
	$(document).on('click', '.addingRequests', function(){
		 $("#request").val("");
		 $("#actionTaken").val("");
		 $("#raisedBy").val("");
		  $("#saveBtn").attr("attr_id",0);
		 $('#locationInPop option:selected').prop('selected', false);
		 
		 $("#myModashow").modal("show");
	});
	
	
	$(document).on('click', '#saveBtn', function(){
		//alert($("#locationDivId option:selected").val());
		var request = $("#request").val();
		var ActionTaken = $("#actionTaken").val();
		var raisedBy = $("#raisedBy").val();
		var locationId = $("#locationDivId").val();
		var atrId = $(this).attr("attr_id");
		var partyMeetingId = '${partyMeetingId}';
		var locationscope = $("#locationInPop").attr("locationscope");
		var jsObj={		
			atrId : atrId,
			request : request,
			ActionTaken : ActionTaken,
			raisedBy : raisedBy,
			locationId : locationId,
			partyMeetingId : partyMeetingId,
			locationscope : locationscope
		}
		$.ajax({
			  type:'GET',
			  url: 'updateMeetingAtrPointAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result=="success"){
				alert("Updated Successfully");
				getAtrPointsForAMeeting(partyMeetingId);
			}else{
				alert("Please Try Again");
			}
		});	
		
	});
	
	function getAtrPointsForAMeeting(partyMeetingId){
		var jsObj={		
			partyMeetingId : partyMeetingId
		}
		$.ajax({
			  type:'GET',
			  url: 'getAtrPointsForAMeetingAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result.atrDetails!=null && result.atrDetails.length>0){
				$("#atrDivId").html("");
				   atrFiles = result.atrDetails.length;
				   var str='';
				   for(var i in result.atrDetails){
				   	   maximumDivCount=i;
					   str+='<div id="atrInnerDiv'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<div class="panel-body" id="requestDivId" style="border:1px solid #c3c3c3;">';
					   str+='<div class="row">';
					   str+='<div class="pull-right" style="margin-right:5px;">';
					  
					  str+=' <button class="btn btn-default btn-xs ToolTipDiv deleteAtr"  attr_reqId="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_actntknId="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_rsdById="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_atrId="'+result.atrDetails[i].partyMeetingAtrPointId+'" data-toggle="tooltip" data-placement="top" title="Delete" style="background-color:#CCC" attr_txt="removeDivId'+maximumDivCount+'"><i class="glyphicon glyphicon-trash"></i></button>';
					   
					   //str+=' <button class="btn btn-danger btn-xs deleteAtr" attr_reqId="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'"  attr_actntknId="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_rsdById="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_atrId="'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_txt="removeDivId'+maximumDivCount+'">REMOVE</button>';
					   
					   str+=' <button class="btn btn-default btn-xs editBtn ToolTipDiv"  data-toggle="tooltip" data-placement="top" title="Edit" style="background-color:#CCC" attr_id="'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_txt="editDivId'+maximumDivCount+'"><i class="glyphicon glyphicon-edit"></i></button>';
					   
					   //str+=' <button class="btn btn-danger btn-xs editBtn" attr_id="'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_txt="editDivId'+maximumDivCount+'">EDIT</button>';
					   str+=' </div>';
					   str+='<div class="col-md-12">';
					   str+='<label>REQUEST</label><br/>';
					   str+='<span class="updaterequest" id="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');"></span>';
					   str+='</div>';
					   str+='<div class="col-md-12 m_top20">';
					   str+='<label>ACTION TAKEN</label><br/>';
					   str+='<span class="updateaction" id="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');"></span>';
					   str+='</div>';
					   str+='<div class="col-md-12 m_top20">';
					   str+='<label>RAISED BY</label><br/>';
					   str+='<span class="updateraisedBy" id="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].raisedBy+'</span>';
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
					   str+='<select class="form-control locationCls" id="districtId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select District</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='<span style="display:none;" id="DistrictShowIdSpan'+result.atrDetails[i].partyMeetingAtrPointId+'"></span>';
					   str+='<div class="col-md-1" style="height: 44px; width: 10px;">';
					   str+='<img src="./images/icons/search.gif"" class="offset7"  id="searchDataImgForcons'+result.atrDetails[i].partyMeetingAtrPointId+'" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>';
					   str+='</div>';
					   str+='<div class="col-md-12" id="ConstShowId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="display:none;">';
					   str+='<label>Constituency</label>';
					   str+='<select class="form-control locationCls" id="constituencyId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select Constituency</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='<span style="display:none;" id="ConstShowIdSpan'+result.atrDetails[i].partyMeetingAtrPointId+'"></span>';
					   
					   str+='<div class="col-md-1" style="height: 44px; width: 10px;">';
					   str+='<img src="./images/icons/search.gif" class="offset7"  id="searchDataImgForman'+result.atrDetails[i].partyMeetingAtrPointId+'" style="margin-left: -13px;margin-top: 30px;width:20px;height:20px;display:none;"/>';
					   str+='</div>';
					   str+='<div class="col-md-12" id="ManTwnDivShowId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="display:none;">';
					   str+='<label>Mandal/Town/Division</label>';
					   str+='<select class="form-control locationCls" id="manTowDivId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select Mandal/Town/Division</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='<span style="display:none;" id="ManTwnDivShowIdSpan'+result.atrDetails[i].partyMeetingAtrPointId+'"></span>';
					   
					   str+='<div class="col-md-12" id="VillWardShowId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="display:none;">';
					   str+='<label>Village/Ward</label>';
					   str+='<select class="form-control locationCls" id="villWardId'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<option>Select Village/Ward</option>';
					   str+='</select>';
					   str+='</div>';
					   str+='<span style="display:none;" id="VillWardShowIdSpan'+result.atrDetails[i].partyMeetingAtrPointId+'"></span>';
					   str+='</div>';
					   /* str+='<div class="row">';
					   str+='<div class="col-md-12">';
					   str+='<h4><i class="icon-upload"></i> Upload File</h4>';
					   str+='<p class="m_0">Drag and drop file below box or click on box to upload file</p>';
					   str+='<form action="/target" class="dropzone"></form>';
					   str+='<p class="m_0 font-10 pull-right">Note: Multiple files upload. Allowed Types: PDF,Word,Excel,Jpg,JPEG,PNG</p>';
					   str+='</div>';
					   str+='</div>'; */
					   str+='<div class="pull-right m_top10" style="display:none;" id="btnsDiv'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   //str+='<button class="btn btn-success btn-xs  updateAtr" style="padding:3px;" attr_reqId="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_actntknId="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_rsdById="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_atrId="'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_locationScope="'+result.atrDetails[i].locationScopeId+'">UPDATE</button>';
					   //str+='<button class="btn btn-success btn-xs deleteAtr" attr_reqId="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" style="padding:3px;" attr_actntknId="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_rsdById="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_atrId="'+result.atrDetails[i].partyMeetingAtrPointId+'">DELETE</button>';
					   str+='</div>';
					   str+='</div>';
					   str+='</div>';
					   
				   }
				   
				   $("#atrDivId").html(str);
				   
				   for(var i in result.atrDetails){
					   $("#requestId"+result.atrDetails[i].partyMeetingAtrPointId).html(result.atrDetails[i].request);
					   $("#actionTakenId"+result.atrDetails[i].partyMeetingAtrPointId).html(result.atrDetails[i].actionTaken);
					   getmeetinglocationlevel(result.atrDetails[i].locationScopeId,result.locationValue,result.atrDetails[i].partyMeetingAtrPointId,result.atrDetails[i].locationValue);
					   
				   }
				   
				   
			   }else{
				   $("#atrDivId").html("<h5>No ATR Points</h5>");
				   getmeetinglocationlevel(result.meetingLevelId,result.locationValue,0,0);
			   }
		});
	}
	
	function partyMeetingDocs(docSourceType){
		
		var divId = "#mintueDocumentDivId";
		if(docSourceType=="ATR"){
			divId = "#atrDocumentDivId";
			$("#uploadATRDocs input[type=file]").each(function() {
				$(this).val("");
			});
		}else{
			$("#uploadMinutesDocs input[type=file]").each(function() {
				$(this).val("");
			});
		}
		
		var partyMeetingId = '${partyMeetingId}';
		var jsObj={		
			partyMeetingId : partyMeetingId,
			docSourceType:docSourceType
		}
		$.ajax({
			type:'GET',
			url: 'getDocsOfMeetingAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 if(result!=null && result.length>0){
				   var str='';
				    for(var i in result){
					    str+='<div class="col-md-12 row" id="minuteDocFileId'+result[i].id+'" style="padding:6px;">';
					    str+='<a class="col-md-10" href="'+result[i].url+'">'+result[i].name+'</a>';
						str+='<div class="deleteDoc col-md-2" attr_type="minute" id="'+result[i].id+'"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</div>';
				   }
				   $(""+divId+"").html(str);
			   }	
		});	
	}
	
	$(document).on('click', '.deleteAtr', function(){
		var atrId = $(this).attr("attr_atrid");
		
		var jsObj={		
			atrId : atrId
		}
		$.ajax({
			  type:'GET',
			  url: 'deleteMeetingAtrPointAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result=="success"){
				alert("ATR Deleted");
				$("#atrInnerDiv"+atrId).remove();
			}
		});
		
	});
	
</script>
</body>
</html>