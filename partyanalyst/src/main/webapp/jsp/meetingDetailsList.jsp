<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Party Meetings - MOM & ATR POINTS</title>

<link type="text/css" href="dist/css/bootstrap.css" rel="stylesheet" />
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>

<link href="css/Training/css/basic.css" rel="stylesheet" type="text/css">
<link href="css/Training/css/dropzone.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<style>
footer{background-color:#5c2d25;color:#ccc;padding:30px}
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
	.alertstyle{background: none repeat scroll 0 0 rgba(0, 0, 0, 0.8);color:#fff !important;height:75px;width:420px;}
	.alerttop{top:215px;}
	.mleft_190{margin-left:190px;font-weight:bold;}
	.mtop-5{margin-top:-5px;}
	.alertstyle h5{font-weight:bold;}
	.colorchange{color:#fff !important;}
	.bt{
		border:none;
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
					   <li><a href="meetingList.action"><span>PARTY MEETING - MOM & ATR </span></a> </li>
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
                            <h4 class="panel-title text-uppercase" ><span id="meetingType"></span>&nbsp; <i><small id="location" ></small></i></h4>
							
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-6 ">
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="background-color:#DDD">
                                            <h4 class="panel-title text-bold">Meeting Minutes</h4>
                                        </div>
										<div class="table-responsive">
											<table class="table table-bordered">
												<tr>
													<td>
														<h4 class="m_0 text-center">Total Minute Points </h4>
														<h2 class="m_0 text-center"><span id="minitePointsCount">0</span></h2>
													</td>
													<td>
														<h4 class="m_0 text-center">Total Documents Uploaded </h4>
														<h2 class="m_0 text-center"><span id="miniteDocsCount">0</span></h2>
													</td>
												</tr>
											</table>
										</div>
										<!--<div style="border:1px solid #ababab; margin:5px;padding:5px;">
											<h4>Total MinitePoints : 
											<span style="margin-left:20px;"></span>
										</div>-->
										
                                        <div class="panel-body">
											<div id="addMoreDiv">
                                                <div class="input-group bin-div m_top10" id="list1" style="display:none">
                                                    <span type="text" class="form-control" id="minutes1"></span>
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
                                                    <span class="input-group-addon trash" style="background-color:#CCC;cursor:pointer"  title="Delete">
                                                        <i class="glyphicon glyphicon-trash"></i>
                                                    </span>
													<span class="input-group-addon saveMinute" style="background-color:#CCC;cursor:pointer;border-left:1px solid #ddd"  title="Save">
														<i class="glyphicon glyphicon-ok"></i>
													</span>
                                                </div>
                                           
                                            <button class="btn btn-primary btn-xs pull-right addMeetMint" style="border-radius:1px;">ADD</button>
											<br/><br/>
											
												  
													<div class="" id="mintueDocumentDivId"></div>
												 
											
											
											<div style="display:none;" id="fileDiv" class="row col-md-12">
												<input type="file" class="m_top10 fileCls col-md-6" name="imageForDisplay" style="width: 225px;"/>
												<div class="col-md-6"><span class="btn btn-primary btn-xs m_top10 removeBtnCls "  title="Remove File"><i class="glyphicon glyphicon-minus"></i></span></div>
											</div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <form id="uploadMinutesDocs" name="uploadMinutesDocs" class="uploadBox">
														<input type="file" class="m_top10 fileCls" name="imageForDisplay" id="fileDivId0" style="width: 225px;margin-left:15px;"/>
														<div id="ExtraFiles"></div>
														<input type= "button" value="Upload Minutes Files" style="margin-left:15px;margin-top:10px;background-color:#666;border-color:#666" class="btn btn-primary btn-sm" id="uploadMinutesDocsId"></input>
														<input type="hidden" name="partyMeeting" id="partyMeetingId"/>
														<input type="hidden" name="partyMeetingType" value="MINUTE"/>
													</form>
													<button class="btn btn-primary btn-xs pull-right m_top20 "   title="Add one more file" id="addFiles"><i class="glyphicon glyphicon-plus"></i></button>
                                                    <p class="m_0 font-10 pull-right">Note: Multiple files upload. Allowed Types: PDF,Word,Excel,Jpg,JPEG,PNG</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                 <div class="col-md-6 ">
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="background-color:#DDD">
                                            <h4 class="panel-title text-bold">ATR</h4>
                                        </div>
										
										<table class="table table-bordered">
											<tr>
												<td>
													<h4 class="m_0 text-center">Total ATR Points </h4>
													<h2 class="m_0 text-center"><span id="atrPointsCount">0</span></h2>
												</td>
												<td>
													<h4 class="m_0 text-center">Total Documents Uploaded </h4>
													<h2 class="m_0 text-center"><span id="atrDocsCount">0</span></h2>
												</td>
											</tr>
										</table>
										
                                            <div class="panel-body">
											<div>
                                                <div id="atrDivId"></div>
											<div class="pull-right m_top10">
											<button class="btn btn-primary btn-xs addingRequests" style="border-radius:1px;">ADD</button>
											</div>
											</div>
											<br/><br/>
											
												<div  class="" id="atrDocumentDivId"></div>
										
											<div class="row">
												 <div class="col-md-12">
                                                    <form id="uploadATRDocs" name="uploadATRDocs" class="uploadBox	">
														<input type="file" class="m_top10 fileCls" name="imageForDisplay" id="atrFileId0" style="width: 225px;margin-left:15px;"/>
														<div id="ExtraFilesATR"></div>
														<input type= "button" value="Upload ATR Files" style="margin-left:15px;margin-top:10px;background-color:#666;border-color:#666" class="btn btn-primary btn-sm" id="uploadATRDocsId"></input>
														<input type="hidden" name="partyMeeting" id="partyMeetingATRId"/>
														<input type="hidden" name="partyMeetingType" value="ATR"/>
													</form>
													<button class="btn btn-primary btn-xs pull-right m_top20" id="addATRFiles"><i class="glyphicon glyphicon-plus"></i></button>
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
<!---------alert pop up----------------->
<div class="modal fade" id="deletedmsg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4>Are you Sure,want to Delete This Meeting Minutes ?</h4>
      </div>
     
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="deletedMinMsg" data-dismiss="modal">OK</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="deletedmsgAtr" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4>Are you Sure,want to Delete This ATR  ?</h4>
      </div>
     
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="AtrdeletedMsg" data-dismiss="modal">OK</button>
      </div>
    </div>
  </div>
</div>
<div id="showmsshmin"></div>
<!----------------------------->
<!--------Minutes popup-------->
<div class="modal fade" id="mintModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Meeting Minutes</h4>
		<span>Press Alt+t to toggle between Telugu & English</span>
      </div>
      <div class="modal-body">
         	<input type="text" id="meetRaised"  class="form-control"/>
		</div>
		<span id="momError" style="color:red;margin-left:20px;"></span>
      <div class="modal-footer">
        <button type="button" class="btn btn-default"  data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary saveMinute"  id="saveBtnMeetMin" attr_minuteid="0">Save changes</button>
      </div>
    </div>
  </div>
</div>
<!----------------->
<div class="modal fade" id="myModashow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 <div class="modal-dialog">
		 <div class="modal-content">
			<div class="modal-header" style="background-color:#CCC">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">ATR Points</h4>
				<span>Press Alt+t to toggle between Telugu & English</span>
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
			 <span id="atrError" style="color:red;margin-left:20px;"></span>
			 <div class="modal-footer">
				 <button type="button" class="btn btn-success btn-sm" id="saveBtn" ><i class="icon-check"></i> SAVE </button>
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
        <p class="text-center">All &copy; 2015. Telugu Desam Party</p>
</footer>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="js/TrainingProgram/component.js" type="text/javascript"></script>
<script src="js/TrainingProgram/fileupload.js" type="text/javascript"></script>
<script src="dist/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<!-- Add fancyBox main JS and CSS files -->
<script type="text/javascript" src="pdfexpand/source/jquery.fancybox.js?v=2.1.5"></script>
<link rel="stylesheet" type="text/css" href="pdfexpand/source/jquery.fancybox.css?v=2.1.5" media="screen" />
<script type="text/javascript" src="js/blockui.js"></script>
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
		control.makeTransliteratable(['meetRaised']);
		
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
	//$('.tooltipBtnDiv').tooltip();
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
     getPartyMeetingMinutesAtrDetails(partyMeetingId);
     getTheMeetingLevelDetails()
   });

    $("#mainheading").html("PARTY MEETINGS - MOM & ATR ");
    var mainDivCount=1;
	
   var maximumDivCount=1;
 	
	$(document).on('click', '.addMeetMint', function(){
			$("#mintModal").modal("show");
			$("#meetRaised").val("");
	 }); 	
	 
 $(document).on('click', '.conformDel', function(){
		  $("#deletedmsg").modal("show");
		  
		  var attrId4 = $(this).attr("attr_div_count");
		 $("#deletedMinMsg").attr("attr_div_count",attrId4);
		 
		 var attrId2 = $(this).attr("attr_txt");
		 $("#deletedMinMsg").attr("attr_txt",attrId2);
		 
		 var attrId3 = $(this).attr("attr_minuteId");
		 $("#deletedMinMsg").attr("attr_minuteId",attrId3);
		 
		 
	 });deletedmsgAtr
	 
	 
    $(document).on('click', '#deletedMinMsg', function(){
		$.blockUI({ message: "<div style='padding:10px; background-color:#ccc;'><h5> Deleted Please Wait..</h5>",css : { width : "auto",left:"40%"}});
		var divId = $(this).attr("attr_txt");
		var divCount = $(this).attr("attr_div_count");
        $("#"+divId).remove();
        //$("#save"+divCount).remove(); 
		//$(this).remove();
		$("#list"+divCount).remove();		
		
		var minuteId = $(this).attr("attr_minuteId");
		
		var jsObj =    {minuteId : minuteId}
       
        $.ajax(
        {
            type: "POST",
            url:"deleteMeetingMinuteAction.action",
            data:{task :JSON.stringify(jsObj)}
        }
        ).done(function(result){
			$.unblockUI();
			if(result=="success"){
			var reslt;
			reslt = "Minute Deleted Successfully"
			$("#mintupdatealertmag").html(reslt);
			$('#alertmintsave').modal('show');
			updateMinCount("delete");
		   }
		});
		
    });
   
   function updateMinCount(type){
		if(type=="delete"){
			$("#minitePointsCount").text(parseInt($("#minitePointsCount").text())-1);
		}else{
			$("#minitePointsCount").text(type);
		}
		
	}
	
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
			  url: 'getNewDistrictsOfStateSplittedAction.action',
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
		 
		 var locationDiv = $("#DistrictShowId0");
		 locationDiv.find(".locationCls").attr("id","locationDivId");
		 
		 $("#locationInPop").html($("#DistrictShowId0").html());
		 $("#locationInPop").attr("locationScope",1);
		 
   });
  }
 function getConstituenciesForDistricts(district,atrId,locationLevelValue){
        $("#searchDataImgForman"+atrId).show();
        var distArrTemp = [district];
       var jsObj={				
			districtId:distArrTemp,
			stateId:0,
			task:"getConstituenciesForDistricts"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesOfDistrictWithSplittedAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
           $("#searchDataImgForman"+atrId).hide();
            for(var i in result){
               if(result[i].id == 0){
                 // $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
               }else{
				   if(locationLevelValue==result[i].id){
					   $("#constituencyId"+atrId).append('<option value='+result[i].locationId+' selected>'+result[i].locationName+'</option>');
				   }else{
						$("#constituencyId"+atrId).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				   }
                  
               }
            }
			
			$("#locationInPop").html("");
			
			var locationDiv = $("#ConstShowId0");
			locationDiv.find(".locationCls").attr("id","locationDivId");
			
			$("#locationInPop").html($("#ConstShowId0").html());
			$("#locationInPop").attr("locationScope",2);
        });
    }


   
    function getMandalVillageDetails(locationId, locationLevel,atrId,locationLavelValue){
          $("#searchDataImgForcons"+atrId).show();
        var constituencyId = [];
        var mandalId = [];
        var locationTemp = "#ManTwnDivShowId0";
		
		var districts = [];
	   
        if(locationLevel==4){
			$("#locationInPop").attr("locationScope",3);
             $("#searchDataImgForcons"+atrId).show();
            constituencyId.push(locationId);
        }
        if(locationLevel==5){
			$("#locationInPop").attr("locationScope",4);
           locationTemp = "#VillWardShowIdSpan0";
            mandalId.push(locationId);
        }
       
       var jsObj={               
            stateId : 0,
            districtId : districts,
            constituencyId : constituencyId,//228
            mandalId : mandalId,
            locationLevel : locationLevel,
            task:""
        }
        $.ajax({
              type:'POST',
              url: 'getSubLevelLctnsForConstituencyAndMandalAction.action',
              dataType: 'json',
              data: {task:JSON.stringify(jsObj)}
       }).done(function(result){
            $("#searchDataImgForcons"+atrId).hide();
              var divId = "#manTowDivId"+atrId;
              if(locationLevel ==5){
                  divId = "#villWardId"+atrId;
              }
              
            for(var i in result){
				if(locationLavelValue==result[i].locationId){
					$(divId).append('<option value='+result[i].locationId+' selected>'+result[i].locationName+'</option>');
				}else{
					$(divId).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
                
            }
			
			$("#locationInPop").html("");
			
			var locationDiv = $(locationTemp);
			locationDiv.find(".locationCls").attr("id","locationDivId");
			
			$("#locationInPop").html($(locationTemp).html());
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
			
			//$("#VillWardShowIdSpan"+atrId).val($("#VillWardId"+atrId).text());
            getMandalVillageDetails(locationId, 5,atrId,locationLevelValue);
        }
    }
	
	function getPartyMeetingMinutesAtrDetails(partyMeetingID){
		
		var jsObj={				
			partyMeetingID : partyMeetingID
		}
		$.ajax({
			  type:'POST',
			  url: 'getPartyMeetingMinutesAtrDetailsAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   
		   if(result!=null){
			   $("#meetingType").html(result.partyMeetingType+" - "+result.name);
			   $("#location").html(result.locationName);
			   
			   if(result.minutesDocuments!=null && result.minutesDocuments.length>0){
				   $("#miniteDocsCount").html(result.minutesDocuments.length);
				   var str='';
				    str+='<div class="panel panel-default">';
					str+='<div class="panel-heading text-bold">UPLOAD DOCUMENTS</div>';
					str+=' <div class="panel-body">';
					str+='<ul class="list-group row">';
				    for(var i in result.minutesDocuments){
						str+='<li class="col-md-12 list-group-item" id="minuteDocFileId'+result.minutesDocuments[i].id+'">';
					    str+='<a target="_tab" class="col-md-10" href="'+result.minutesDocuments[i].url+'">'+result.minutesDocuments[i].name+'</a>';
						str+='<div class="deleteDoc pull-right" attr_type="minute" id="'+result.minutesDocuments[i].id+'" style="cursor:pointer;"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</li>';
						
						
				   }
				    str+='</ul>';
				   str+='</div>';
				   str+='</div>';
				   str+='</div>';
				   $("#mintueDocumentDivId").html(str);
			   }
			    if(result.atrDocuments!=null && result.atrDocuments.length>0){
					$("#atrDocsCount").html(result.atrDocuments.length);
				   var str='';
				    str+='<div class="panel panel-default">';
					str+='<div class="panel-heading text-bold">UPLOAD DOCUMENTS</div>';
					str+=' <div class="panel-body">';
					str+='<ul class="list-group row">';
				   for(var i in result.atrDocuments){
					    str+='<li class="col-md-12 list-group-item" id="atrDocFileId'+result.atrDocuments[i].id+'">';
					    str+='<a target="_tab" class="col-md-10" href="'+result.atrDocuments[i].url+'">'+result.atrDocuments[i].name+'</a>';
						str+='<div class="pull-right deleteDoc" attr_type="atr" id="'+result.atrDocuments[i].id+'" style="cursor:pointer;"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</li>';
						
				   }
				   str+='</ul>';
				   str+='</div>';
				   str+='</div>';
				   str+='</div>';
				   $("#atrDocumentDivId").html(str);
				  // $('.fancybox').fancybox();
			   }
			   
			   
			   if(result.minutesDetails!=null && result.minutesDetails.length>0){
				   $("#minitePointsCount").html(result.minutesDetails.length);
				   minutesFiles = result.minutesDetails.length;
				   var str='';
				    mainDivCount=i;
					str+='<ul class="list-group" id="list'+mainDivCount+'">';
				   for(var i in result.minutesDetails){
					  	 mainDivCount=i;
						str+='<li class="list-group-item " id="list'+mainDivCount+'">';
						str+='<p id="minutes'+mainDivCount+'" class="updatedMeetMintValue" style="margin-bottom: 0px; margin-top: 11px;" onclick=enableSaveOption("'+mainDivCount+'");>'+result.minutesDetails[i].minutePoint+'</p>';
						str+='<div class="btn-group btn-group-sm pull-right" role="group" style="display: inline-block;position: absolute;right: 0;top: 0;">';
				       str+=' <button class="btn btn-default conformDel"  title="Delete" attr_txt="minutes'+mainDivCount+'" attr_div_count="'+mainDivCount+'" attr_minuteId="'+result.minutesDetails[i].partyMeetingMinuteId+'"><i class="glyphicon glyphicon-trash"></i></button>';
					   str+=' <button class="btn btn-default updatedMeetMin" attr_minuteId="'+result.minutesDetails[i].partyMeetingMinuteId+'" id="save'+mainDivCount+'" attr_txt="minutes'+mainDivCount+'"  title="Edit"   ><i class="glyphicon glyphicon-edit"></i></button>';
					   str+=' </div>';
						str+=' </li>';
						
							
				   }
				   str+='</ul>';
				   $("#addMoreDiv").html(str);
				 //  $('.trash').tooltip()
			   }else{
				   $("#addMoreDiv").html("<h5>No Meeting Minutes</h5>");
				   
			   }
			   
			   if(result.atrDetails!=null && result.atrDetails.length>0){
				   $("#atrPointsCount").html(result.atrDetails.length);
				   atrFiles = result.atrDetails.length;
				   var str='';
				   for(var i in result.atrDetails){
				   	   maximumDivCount=i;
					   str+='<div id="atrInnerDiv'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='<div class="panel-body" id="requestDivId" style="border:1px solid #c3c3c3;">';
					   str+='<div class="row">';
					   str+='<div class="btn-group btn-group-sm pull-right" style="margin-top: -16px; margin-right: -1px; z-index: 999;margin-bottom: 2px;">';
				       str+=' <button class="btn btn-default  deleteAtr"  attr_reqId="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_actntknId="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_rsdById="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_atrId="'+result.atrDetails[i].partyMeetingAtrPointId+'"  title="Delete"  attr_txt="removeDivId'+maximumDivCount+'"><i class="glyphicon glyphicon-trash"></i></button>';
					   str+=' <button class="btn btn-default editBtn "   title="Edit"  attr_id="'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_txt="editDivId'+maximumDivCount+'" attr_location_value='+result.atrDetails[i].locationValue+'><i class="glyphicon glyphicon-edit"></i></button>';
					   str+=' </div>';
					    str+='<ul class="" style="padding-left: 0px;margin-top: -48px;">';
					   str+='<li class="col-md-12 list-group-item" >';
					   str+='<span class="text-bold">REQUEST : </span>';
					   str+='<span class="updaterequest" id="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].request+'</span>';
					   str+='</li>';
					   str+='<li class="col-md-12 list-group-item">';
					   str+='<span class="text-bold">ACTION TAKEN : </span>';
					   str+='<span class="updateaction" id="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].actionTaken+'</span>';
					   str+='</li>';
					   str+='<li class="col-md-12 list-group-item">';
					   str+='<span class="text-bold">RAISED BY : </span>';
					   str+='<span class="updateraisedBy" id="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].raisedBy+'</span> - <i>'+result.atrDetails[i].locationName+'</i>';
					   str+='</li>';
					   str+='</div>';
					   
					   str+='<div class="pull-right m_top10" style="display:none;" id="btnsDiv'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					   str+='</div>';
					   str+='</div>';
					   str+='</div>';
					   
				   }
				   
				   $("#atrDivId").html(str);
				   getmeetinglocationlevel(result.meetingLevelId,result.locationValue,0,0);
				   
			   }else{
				   $("#atrDivId").html("<h5>No ATR Points</h5>");
				   getmeetinglocationlevel(result.meetingLevelId,result.locationValue,0,0);
			   }
		   }
	   });
	}
	$(document).on('click', '.updatedMeetMin', function(){
		$("#mintModal").modal("show");
		var meetmin=$(this).parent().parent().find(".updatedMeetMintValue").text();
		$("#meetRaised").val(meetmin);
		
		var attrId1 = $(this).attr("attr_minuteId");
		 $("#saveBtnMeetMin").attr("attr_minuteId",attrId1);
		
	});
	
	/* function enableSaveOption(txtBoxCnt){
		
		$("#save"+txtBoxCnt).show();
	} */
	
	$(document).on('click', '.saveMinute', function(){
		$("#momError").text("");
		var reslt;
		var minuteText = $("#meetRaised").val();
		if(minuteText.trim().length==0){
			$("#momError").text(" Please Enter Minute Text");
			return;
		}
		
		$.blockUI({ message: "<div style='padding:10px; background-color:#ccc;'><h5> Saving Minutes Please Wait..</h5>",css : { width : "auto",left:"40%"}});
		var minuteId = $(this).attr("attr_minuteid");
		var jsObj={		
			minuteId : minuteId,
			minuteText : minuteText,
			partyMeetingId : partyMeetingId//global var
		}
		$.ajax({
			  type:'POST',
			  url: 'updateMeetingMinutePointAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$('#mintModal').modal('hide');
			
		   if(result=="success"){
				rebuildMinutes(partyMeetingId,minuteId)
		   }else{
				reslt = "Please Try Again"
				$("#mintupdatealertmag").html(reslt);
				$('#alertmintsave').modal('show');
				$.unblockUI();
		   }
		   
			$("#saveBtnMeetMin").attr("attr_minuteId",0);
		});
	});
	
	function rebuildMinutes(partyMeetingId,minuteId){
		var jsObj={		
			partyMeetingId : partyMeetingId//global var
		}
		$.ajax({
			  type:'POST',
			  url: 'getTheMinutePointsForAMeetingAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.minutesDetails!=null && result.minutesDetails.length>0){
			   minutesFiles = result.minutesDetails.length;
			   updateMinCount(minutesFiles);
			   var str='';
			   for(var i in result.minutesDetails){
				   mainDivCount=i;
					str+='<li class="list-group-item " id="list'+mainDivCount+'">';
					 //str+='<div class=" m_top10" id="list'+mainDivCount+'">';
					 str+='<span id="minutes'+mainDivCount+'" class="updatedMeetMintValue" onclick=enableSaveOption("'+mainDivCount+'");>'+result.minutesDetails[i].minutePoint+'</span>';
					 str+='<div class="btn-group btn-group-sm pull-right" style="display: inline-block;position: absolute;right: 0;top: 0;">';
					 str+=' <button class="btn btn-default ToolTipDiv conformDel" data-toggle="tooltip" data-placement="top" title="Delete" attr_txt="minutes'+mainDivCount+'" attr_div_count="'+mainDivCount+'" attr_minuteId="'+result.minutesDetails[i].partyMeetingMinuteId+'"><i class="glyphicon glyphicon-trash"></i></button>';
					 str+=' <button class="btn btn-default updatedMeetMin ToolTipDiv " attr_minuteId="'+result.minutesDetails[i].partyMeetingMinuteId+'" id="save'+mainDivCount+'" attr_txt="minutes'+mainDivCount+'" data-toggle="tooltip" data-placement="top" title="Edit" ><i class="glyphicon glyphicon-edit"></i></button>';
					 str+=' </div>';
					 str+=' </li>';
				}
			   $("#addMoreDiv").html(str);
			   //$('.trash').tooltip()
			}
			$.unblockUI();
			//alert("Updated Successfully");
			if(minuteId == 0){
				var stts = "Saved Successfully"
				$("#mintupdatealertmag").html(stts);
				$('#alertmintsave').modal('show');
			}else if(minuteId > 0){
				var stts = "Updated Successfully"
				$("#mintupdatealertmag").html(stts);
				$('#alertmintsave').modal('show');
			}
		});
	}
	
	$("#uploadMinutesDocsId").click(function(){
		$("#partyMeetingId").val(partyMeetingId);
		var files = [];
		$("#uploadMinutesDocs input[type=file]").each(function() {
			if($(this).val().trim().length>0){
				files.push($(this).val());
			}
		});
	
		if(files.length==0){
			var reslt;
			reslt = "Please Select Documents"
			$("#mintupdatealertmag").html(reslt);
			$('#alertmintsave').modal('show');
			//alert("Please Select Documents");
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
			var reslt;
			reslt = "Please Select Documents"
			$("#mintupdatealertmag").html(reslt);
			$('#alertmintsave').modal('show');
			//alert("Please Select Documents");
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
		var reslt;
		var result = myResult;
		if (result.indexOf("success") >= 0){
			$('#alertmintsave').modal('show')
			//alert("File Uploaded Successfully");
			rebuildTheDocumentsDiv(docSourceType,reslt);
		}
		else{
			reslt = "Failed to Upload.. Please Try Again"
			$("#mintupdatealertmag").html(reslt);
			$('#alertmintsave').modal('show');
			//alert("Failed to Upload.. Please Try Again");
		}
		
		partyMeetingDocs(docSourceType);
	}
	
	function rebuildTheDocumentsDiv(fromType,result){
		var jsObj={		
			partyMeetingId : '${partyMeetingId}'
		}
		$.ajax({
			  type:'POST',
			  url: 'getDocumentDetailsForAMeetingAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(fromType=="MINUTE"){
				if(result.minutesDocuments!=null && result.minutesDocuments.length>0){
					$("#miniteDocsCount").text(result.minutesDocuments.length);
				   var str='';
				   str+='<div class="panel panel-default">';
					str+='<div class="panel-heading text-bold"> UPLOAD DOCUMENTS</div>';
					str+=' <div class="panel-body">';
					str+='<ul class="list-group row">';
				    for(var i in result.minutesDocuments){
					    str+='<li class="list-group-item col-md-12" id="docDiv'+result.minutesDocuments[i].id+'">';
					    str+='<a target="_tab" href="'+result.minutesDocuments[i].url+'">'+result.minutesDocuments[i].name+'</a>';
						str+='<div class="pull-right deleteDoc" id="'+result.minutesDocuments[i].id+'" style="cursor:pointer;"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</li>';
						
				   }
				   str+='</ul>';
				   str+='</div>';
				   str+='</div>';
				   str+='</div>';
				   $("#mintueDocumentDivId").html(str);
			   }
			   $("#ExtraFiles").html("");
			}
			else if(fromType=="ATR"){
				if(result.atrDocuments!=null && result.atrDocuments.length>0){
					$("#atrDocsCount").text(result.atrDocuments.length);
				   var str='';
				   str+='<div class="panel panel-default">';
					str+='<div class="panel-heading text-bold">UPLOAD DOCUMENTS</div>';
					str+=' <div class="panel-body">';
					str+='<ul class="list-group row">';
				   for(var i in result.atrDocuments){
					    str+='<li class="col-md-12  list-group-item" id="docDiv'+result.atrDocuments[i].id+'">';
					    str+='<a target="_tab" href="'+result.atrDocuments[i].url+'">'+result.atrDocuments[i].name+'</a>';
						str+='<div class="pull-right deleteDoc" id="'+result.atrDocuments[i].id+'" style="cursor:pointer;"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</li>';
						
				   }
				   str+='</ul>';
				   str+='</div>';
				   str+='</div>';
				   str+='</div>';
				   $("#atrDocumentDivId").html(str);
			   }
			   $("#ExtraFilesATR").html("");
			}
			reslt = "File Uploaded Successfully"
			$("#mintupdatealertmag").html(reslt);
			
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
			  type:'POST',
			  url: 'deletePartyMeetingDocumentAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result=="success"){
				updateMinDocCount(type);
				$("#docDiv"+docId).remove();
				$(""+divId+"").remove();
			}else{
			var reslt;
			reslt = "Please Try Again"
			$("#mintupdatealertmag").html(reslt);
			$('#alertmintsave').modal('show');
				//alert("Please Try Again");
			}
		});
	});
	
	function updateMinDocCount(type){
		if(type=="atr"){
			$("#atrDocsCount").text(parseInt($("#atrDocsCount").text())-1);
		}else{
			$("#miniteDocsCount").text(parseInt($("#miniteDocsCount").text())-1);
		}
	}
	
	var editAtrId='';
	 $(document).on('click', '.editBtn', function(){
		 $("#myModashow").modal("show");
		 var req=$(this).parent().parent().find(".updaterequest").text();
		 var actn=$(this).parent().parent().find(".updateaction").text();
		 var raised=$(this).parent().parent().find(".updateraisedBy").text();
		 var attrId = $(this).attr("attr_id");
		 //var lctnVal = $(this).parent().parent().parent().find(".selectedLctn").attr("attr_lctnid");
		 var lctnVal = $(this).attr("attr_location_value");
		 
		 $("#saveBtn").attr("attr_id",attrId);
		 $("#request").val(req);
		 $("#actionTaken").val(actn);
		 $("#raisedBy").val(raised);
		 
		 $("#locationDivId").val(lctnVal);
		 
		 //$('#locationInPop option[value="'+lctnVal+'"]').prop('selected', true);
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
		//$.blockUI({ message: "<h5> Saving ATR Please Wait..</h5>",css : { width : "auto",left:"40%"}});
		$("#atrError").text("");
		var reslt;
		var request = $("#request").val();
		var ActionTaken = $("#actionTaken").val();
		var raisedBy = $("#raisedBy").val();
		var locationId = $("#locationDivId").val();
		var atrId = $(this).attr("attr_id");
		var partyMeetingId = '${partyMeetingId}';
		var locationscope = $("#locationInPop").attr("locationscope");
		
		var errorTxt = "Please Enter";
		var isError = false;
		if(request.trim().length==0){
			errorTxt+=" Request"; 
			isError = true;
		}
		if(ActionTaken.trim().length==0){
			
			if(isError){
				errorTxt+=",";
			}
			errorTxt+=" Action Taken";
			isError = true;
		}
		if(raisedBy.trim().length==0){
			
			if(isError){
				errorTxt+=",";
			}
			errorTxt+=" Raised By";
			isError = true;
		}
		if(locationId==0){
			
			if(isError){
				errorTxt+=",";
			}
			errorTxt+=" Location";
			isError = true;
		}
		
		if(isError){
			$("#atrError").text(errorTxt);
			return;
		}
		
		$.blockUI({ message: "<div style='padding:10px; background-color:#CCC;'><h5> Saving ATR Please Wait..</h5>",css : { width : "auto",left:"40%"}});
		
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
			  type:'POST',
			  url: 'updateMeetingAtrPointAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#myModashow").modal("hide");
			if(result=="success"){
				//alert("Updated Successfully");
				getAtrPointsForAMeeting(partyMeetingId,atrId);
			}else{
				reslt = "Please Try Again"
				$("#mintupdatealertmag").html(reslt);
				$('#alertmintsave').modal('show');
				//alert("Please Try Again");
				$.unblockUI();
			}
		});	
		
	});
	
	function getAtrPointsForAMeeting(partyMeetingId,atrId){
		var jsObj={		
			partyMeetingId : partyMeetingId
		}
		$.ajax({
			  type:'POST',
			  url: 'getAtrPointsForAMeetingAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result.atrDetails!=null && result.atrDetails.length>0){
				updateAtrCount(result.atrDetails.length);
				$("#atrDivId").html("");
				   atrFiles = result.atrDetails.length;
				   var str='';
				   for(var i in result.atrDetails){
				   	   maximumDivCount=i;
					   
					   str+='<div id="atrInnerDiv'+result.atrDetails[i].partyMeetingAtrPointId+'" class="m_top10">';
					   str+='<div class="panel-body" id="requestDivId" style="border:1px solid #c3c3c3;">';
					   str+='<div class="row">';
					   str+='<div class="btn-group btn-group-sm pull-right" style="margin-top: -16px; margin-right: -1px; z-index: 999;margin-bottom: 2px;">';
				       str+=' <button class="btn btn-default  deleteAtr"  attr_reqId="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_actntknId="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_rsdById="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_atrId="'+result.atrDetails[i].partyMeetingAtrPointId+'"  title="Delete"  attr_txt="removeDivId'+maximumDivCount+'"><i class="glyphicon glyphicon-trash"></i></button>';
					   str+=' <button class="btn btn-default editBtn "   title="Edit"  attr_id="'+result.atrDetails[i].partyMeetingAtrPointId+'" attr_txt="editDivId'+maximumDivCount+'" attr_location_value='+result.atrDetails[i].locationValue+'><i class="glyphicon glyphicon-edit"></i></button>';
					   str+=' </div>';
					    str+='<ul class="" style="padding-left: 0px;margin-top: -48px;">';
					   str+='<li class="col-md-12 list-group-item" >';
					   str+='<span class="text-bold">REQUEST : </span>';
					   str+='<span class="updaterequest" id="requestId'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].request+'</span>';
					   str+='</li>';
					   str+='<li class="col-md-12 list-group-item">';
					   str+='<span class="text-bold">ACTION TAKEN : </span>';
					   str+='<span class="updateaction" id="actionTakenId'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].actionTaken+'</span>';
					   str+='</li>';
					   str+='<li class="col-md-12 list-group-item">';
					   str+='<span class="text-bold">RAISED BY : </span>';
					   str+='<span class="updateraisedBy" id="raisedById'+result.atrDetails[i].partyMeetingAtrPointId+'" onclick="showBtnsDiv(\''+result.atrDetails[i].partyMeetingAtrPointId+'\');">'+result.atrDetails[i].raisedBy+'</span> - <i>'+result.atrDetails[i].locationName+'</i>';
					   str+='</li>';
					   str+='</div>';
					   
					   str+='<div class="pull-right m_top10" style="display:none;" id="btnsDiv'+result.atrDetails[i].partyMeetingAtrPointId+'">';
					  
					   str+='</div>';
					   str+='</div>';
					   str+='</div>';
					   
					  
				   }
				   
				   $("#atrDivId").html(str);
			   }else{
				   $("#atrDivId").html("<h5>No ATR Points</h5>");
				  // getmeetinglocationlevel(result.meetingLevelId,result.locationValue,0,0);
			   }
			   $.unblockUI();
			   //alert("Updated Successfully");
				if(atrId == 0){
					var  stts = "Saved Successfully"
					$("#mintupdatealertmag").html(stts);
					$('#alertmintsave').modal('show');
				}else if(atrId >0){
					var stts = "Updated Successfully"
					$("#mintupdatealertmag").html(stts);
					$('#alertmintsave').modal('show');
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
			type:'POST',
			url: 'getDocsOfMeetingAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			 if(result!=null && result.length>0){
				   var str='';
				    for(var i in result){
					    str+='<div class="col-md-12 row" id="minuteDocFileId'+result[i].id+'" style="padding:6px;">';
					    str+='<a class="col-md-10" href="'+result[i].url+'">'+result[i].name+'</a>';
						str+='<div class="deleteDoc col-md-2" attr_type="minute" id="'+result[i].id+'" style="cursor:pointer;"><i class=" glyphicon glyphicon-remove"></i></div>';
						str+='</div>';
				   }
				   $(""+divId+"").html(str);
			   }	
		});	
	}
	 $(document).on('click', '.deleteAtr', function(){
		  $("#deletedmsgAtr").modal("show");
		  
		  var attrId5 = $(this).attr("attr_atrid");
		 $("#AtrdeletedMsg").attr("attr_atrid",attrId5);
		 
		
		 
	 });
	$(document).on('click', '#AtrdeletedMsg', function(){
		$.blockUI({ message: "<div style='padding:10px; background-color:#ccc;'><h5> Deleted Please Wait..</h5>",css : { width : "auto",left:"40%"}});
		var atrId = $(this).attr("attr_atrid");
		
		var jsObj={		
			atrId : atrId
		}
		$.ajax({
			  type:'POST',
			  url: 'deleteMeetingAtrPointAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$.unblockUI();
			if(result=="success"){
			var reslt;
			reslt = "ATR Deleted Successfully"
			$("#mintupdatealertmag").html(reslt);
			$('#alertmintsave').modal('show');
				//alert("ATR Deleted");
				$("#atrInnerDiv"+atrId).remove();
				updateAtrCount("delete");
			}
		});
		
	});
	
	function updateAtrCount(type){
		if(type=="delete"){
			$("#atrPointsCount").text(parseInt($("#atrPointsCount").text())-1);
		}else{
			$("#atrPointsCount").text(type);
		}
	}
	
	alertmssgshowing();
	function alertmssgshowing(){
		var str='';
		str+='<div class="modal fade alerttop" id="alertmintsave" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" style="z-index:99999">';
		str+='<div class="modal-dialog modal-sm">';
		str+=' <div class="modal-content modal-sm alertstyle">';
		str+='<div class="modal-body ">';
		str+=' <div><h5 class="text-center colorchange" id="mintupdatealertmag"></h5></div>';
		str+=' </div>';
		str+=' <div class="modal-footer pad-0 bt">';
		str+='<div class="row">';
		str+='<div class="mtop-5">';
		str+='<button type="button" class="btn btn-primary btn-xs mleft_190 pull-left" data-dismiss="modal" style="margin-top: -11px;">Close</button>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		$("#showmsshmin").html(str);
	}
	
	$(document).on("change","#locationDivId",function(){
		var someVal = $(this).val();
		$('#locationDivId').find('option[value='+someVal+']').prop('selected',true);
	});
	
</script>
</body>
</html>