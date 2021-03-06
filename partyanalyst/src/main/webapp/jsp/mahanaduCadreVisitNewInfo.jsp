<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MAHANADU VISITORS INFO</title>

 <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet"/>
	<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	 <script src="js/cadreCommittee/js/jquery.smartmenus.min.js" type="text/javascript"></script>
    <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>
	 <script src="js/jquery.classyloader.min.js"></script>
	 
<script src="dist/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="dist/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="dist/js/dataTables.responsive.js" type="text/javascript"></script>
	 
<link href="dist/eventDashboard/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/css/dataTables.responsive.css" rel="stylesheet" type="text/css">
<link href="dist/Date/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
<link href="dist/MultiSelect/css/component.css" rel="stylesheet" type="text/css">
<script src="dist/MultiSelect/js/magicselection.js" type="text/javascript"></script>
<script src="dist/MultiSelect/js/modernizr.custom.js" type="text/javascript"></script>
<script src="dist/Date/moment.min.js" type="text/javascript"></script>
<script src="dist/Date/daterangepicker.js" type="text/javascript"></script>
<style>
	header.eventsheader {
    background:url("dist/img/header-footer.png") no-repeat scroll center bottom / 100% auto  #fed501;
    background-origin: border-box;
    background-repeat: no-repeat;
    height: 71px;
}
.panel-heading .panel-title , .panel-title
{
	font-weight:bold
}
.pad_left0
{
	padding-left:0px;
}
</style>
</head>
<body>
<section class="container">
	<div class="well well-sm" style="background:#C0C0C0">
		<div class="row">
		  <div class="col-md-4" style="margin-top:5px;">
			 <h4 class="m_0 panel-title text-capitalise">mahanadu visitors info dashboard</h4>
		  </div>
		  <div class="col-md-2 col-md-offset-1">
			<div id="mahanaduEventDashBoardLinkId" style="display:none">
			  <button id="mahanaduLinkId" type="button" class="btn btn-primary pull-right">ENTRY/EXIT DASHBOARD</button>
			</div>
			<div class="refreshButton">
			  <span  class="text">Last Updated Time <span id="timeUpdationId"></span></span>
			  <a onclick="" title=" Page Refresh" class="refreshIcon" style="cursor:pointer">
				<span class="glyphicon glyphicon-refresh"></span>
			  </a>
			</div>
			</div>
			<div class="col-md">
				<select style="height:32px;display:inline-block;width:150px;" class="form-control" id="enrollmentId">
					<option value="0">All</option>
					<option value="3" >2014-2016</option>
					<option value="4" selected>2016-2018</option>
				</select>
				<select style="height:32px;display:inline-block;width:150px;" class="form-control" id="mainEventSelectId">
					<option value="0">Select Event</option>
					<option value="7" >Mahanadu - 2015</option>
					<option value="30" >Mahanadu - 2016</option>
					<option value="51" selected>Mahanadu - 2017</option>
					<option value="58" >T-Mahanadu - 2017</option>
				</select>				
				<select style="height:32px;display:inline-block;width:100px;" class="form-control" id="eventDatesSelectId"></select>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title SelDay"></h4>
				</div>
				<div class="panel-body panelDefault">
					<h4 class="m_0">TOTAL VISITORS</h4>
					<h3 id="totalVisitorsId">0</h3>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title SelDay"></h4>
				</div>
				<div class="panel-body panelDefault">
					<h4 class="m_0">INVITEES AS VISITORS</h4>
					<h3 id="inviteesAsVisitorsId">0</h3>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title SelDay"></h4>
				</div>
				<div class="panel-body panelDefault">
					<h4 class="m_0">NON INVITEES AS VISITORS</h4>
					<h3 id="nonInviteesAsVisitorsId">0</h3>
					 <center><img id="tdyVstrsPrcssngImgId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title SelDay"></h4>
				</div>
				<div class="panel-body panelDefault">
					<h4 class="m_0">VISITORS PRESENT IN CAMPUS</h4>
					<h3 id="visitorsPresentInCampusId">0</h3>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title SelDay"></h4>
				</div>
				<div class="panel-body panelDefault">
					<h4 class="m_0">INVITEES PRESENT IN  CAMPUS</h4>
					<!--<div data-sparkline="71, 78, 39, 66 " class="m_top10"></div>-->
					<h3 id="inviteesPresentInVisitorsId">0</h3>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			<div class="panel panel-default panel-custom-default"><!--<i class="glyphicon glyphicon-refresh pull-right refreshIconPanel" title="page refresh" id="ttlTdyVstrsId"></i>-->
				<div class="panel-heading">
					<h4 class="panel-title SelDay"></h4>
				</div>
				<div class="panel-body panelDefault">
					<h4 class="m_0">NON INVITEES PRESENT IN  CAMPUS</h4>
					<h3 id="nonInviteesPresentInVisitorsId">0</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title" style="font-size: 15px;">HOUR WISE VISITORS PRESENT IN CAMPUS<!--<i class="glyphicon glyphicon-refresh pull-right hrWseVstrsCls refreshIconPanel" title="page refresh" id="hrWiseVstrsId"></i>--></h4>
					<p class="font-10 fontColor m_0">Last updated On: <span id="hourWiseTableTimeId1"></span></p>
				</div>
				<div class="panel-body pad_0" style="height:370px">
				<div><center><img id="hrWiseVstrsPrcssngImgId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center></div>
				 <div id="visitorsTableId"></div>
				</div>
			</div>
		</div>
		<div class="col-md-8">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<h4 class="panel-title">Hour Wise Visitors Attended VS Visitors In Campus<i class="glyphicon glyphicon-refresh pull-right hrWseVstrsInCampCls refreshIconPanel" title="page refresh" id="hrWiseVstrsGraphId"></i></h4>
					<!--<p class="font-10 fontColor">Last updated On: <span id="hourWiseGraphTimeId"></span></p>-->
				</div>
				<div class="panel-body">
				  <div class="row">
					<div class="col-md-12">
						<div class="pull-right" id="radioDivId">
							<!-- <input type="radio" class="dayRadio" name="dayRadioName" checked value="1"/><label>Day - 1</label>
							<input type="radio" class="dayRadio" name="dayRadioName" value="2"/><label>Day - 2</label>
							<input type="radio" class="dayRadio" name="dayRadioName" value="3"/><label>Day - 3</label> -->
						</div>
					</div>
					<div class="col-md-12"><center><img id="hrWiseVstrsHghChrtPrcssngImgId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center></div>
					<div class="col-md-12">
						<div id="hoursWiseVisitors" style="height:315px;width:100%;"></div>
					</div>
				  </div>
					
					
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="panel panel-default panel-custom-default" style="height:230px;">
				<div class="panel-heading">
					<span class="panel-title" style="font-size:14px;">DAY WISE UNIQUE AND REVISIT SUMMARY<i class="glyphicon glyphicon-refresh pull-right refreshIconPanel" title="page refresh" id="refreshDaysWiseRevisitId"></i></span>
				</div>
				<div class="panel-body pad_8">
				<div><center><img id="dayWsUnquVstrsPrcssngImgId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center></div>
				 <div id="daysSummaryUniqueTableId"></div>
				</div>
			</div>
		</div>
		<div class="col-md-8">
			<div class="panel panel-default panel-custom-default" style="height:230px;">
				<div class="panel-heading">
					<h4 class="panel-title">DAY WISE VISIT SUMMARY<i class="glyphicon glyphicon-refresh pull-right refreshIconPanel" title="page refresh" id="rfrshDyWsPrgrssRvstId"></i></h4>
				</div>
				<div class="panel-body">
				<div><center><img id="dayWsVstrsPrcssngImgId" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center></div>
				<div id="totalVisitorsDtlsId"></div>
					<!--<p class="m_0">1 DAY VISITORS<span class="pull-right" id="frstDyVstrsId">100%</span></p>
					<div class="progress progressNewCustom">
					  <div class="progress-bar progress-bar-info" id="progressBarInfoId" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
						<span class="sr-only" id="prgrssBrVstrsInfoId">80% Complete</span>
					  </div>
					</div>
					<p class="m_0">2 DAY'S VISITORS<span class="pull-right" id="scndDyVstrsId">60%</span></p>
					<div class="progress progressNewCustom">
					  <div class="progress-bar progress-bar-success " id="progressBarSuccessId" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
						<span class="sr-only" id="prgrssBrVstrsSccssId">60% Complete</span>
					  </div>
					</div>
					<p class="m_0">3 DAY'S VISITORS<span class="pull-right" id="thrdDyVstrsId">60%</span></p>
					<div class="progress progressNewCustom">
					  <div class="progress-bar progress-bar-warning" role="progressbar" id="progressBarWarningId" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
						<span class="sr-only" id="prgrssBrVstrsWrnngId">60% Complete</span>
					  </div>
					</div>-->
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default panel-custom-default">
				<div class="panel-heading">
					<div class="row">
						<div class="col-md-8">
							<h4 class="panel-title">VISITORS NOW IN CAMPUS</h4>
						</div>
						<div class="col-md-4">
							<div class="apSwitch">
								<input type="checkbox" value="1" name="apSwitch" class="apSwitch-checkbox" id="apSwitch" checked>
								<label class="apSwitch-label" for="apSwitch">
									<span class="apSwitch-inner"></span>
									<span class="apSwitch-switch"></span>
								</label>
							</div>
							<div class="tsSwitch">
								<input type="checkbox" value="36" name="tsSwitch" class="tsSwitch-checkbox" id="tsSwitch" checked>
								<label class="tsSwitch-label" for="tsSwitch">
									<span class="tsSwitch-inner"></span>
									<span class="tsSwitch-switch"></span>
								</label>
							</div>
							<div class="otherStates">
								<input type="checkbox" value="0" name="otherStates" class="otherStates-checkbox" id="otherStates" >
								<label class="otherStates-label" for="otherStates">
									<span class="otherStates-inner"></span>
									<span class="otherStates-switch"></span>
								</label>
							</div>
							
						</div>
					</div>
					
				</div>
				<div class="panel-body">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-md-6">
									<h4 class="panel-title">DISTRICT WISE</h4>
								</div>
								<div class="col-md-3">
									<button class="btn btn-xs btn-success col-md-offset-9 "  id="districtExcelBtnId" onclick="generateExcel()" style="display:none;">Export Excel</button>
								</div>
							</div>
						</div>
						<div class="panel-body" style="padding:0px;">
							<center><img id="distWiseTableAjax" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
							<div id="distWiseTableDivId" ></div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
						<div class="row">
								<div class="col-md-6">
									<h4 class="panel-title">CONSTITUENCY WISE</h4>
								</div>
								<div class="col-md-3">
									<button class="btn btn-xs btn-success col-md-offset-9 "  id="constituecnyExcelBtnId" onclick="generateExcel1()" style="display:none;">Export Excel</button>
								</div>
							</div>
						</div>
						<div class="panel-body" style="padding:0px;">
						 	<center><img id="constWiseTableAjax" src="images/Loading-data.gif" style="display:none;width:70px;height:60px;"/></center>
							<div  id="constWiseTableDivId"></div>
						</div>
					</div>					
				</div>
			</div>
			
		</div>
	</div>
</section>
<script src="https://code.highcharts.com/highcharts.js"></script>
	
<script type="text/javascript">
 var enrollmentIdsArr=[];
 var maxHeight = 0;
$(".panelDefault").each(function(){
   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
});
$(".panelDefault").height(maxHeight);	
 function buildVisitorsDtlsGraph(result){
	  var finalDataArr=[];
	  var daysArr=["Day - 1","Day - 2","Day - 3"];
	  for(var i in result){
		   var jsonObj={name:daysArr[i],data:[result[i].above8hrs,result[i].seventoeight,result[i].sixtoseven,result[i].fivetosix,result[i].fourtofive,result[i].threetofour,result[i].twotothree,result[i].onetotwo,result[i].halfanhour,result[i].belowhalfanhour]};
		   finalDataArr.push(jsonObj);
	  }
	  //buildHighChart(finalDataArr);
  }
  	function buildHighChart(finalDataArr){
			$('#hoursWiseVisitors').highcharts({
				chart: {
					type: 'area'
				},
				title: {
					text: ' '
				},
				subtitle: {
					text: ' '
				},
				xAxis: {
					categories: ['Above 8 Hours', '7 to 8 Hours', '6 to 7 Hours', '5 to 6 Hours', '4 to 5 Hours', '3 to 4 Hours', '2 to 3 Hours','1 to 2 Hours','1/2 to 1 Hours','< 1/2 Hour'],
					tickmarkPlacement: 'on',
					title: {
						enabled: false
					}
				},
				yAxis: {
					title: {
						text: ''
					},
					labels: {
						formatter: function () {
							return ((this.value / 1000)+" K");
						}
					}
				},
				tooltip: {
					shared: true,
					valueSuffix: ' '
				},
				plotOptions: {
					area: {
						stacking: 'normal',
						lineColor: '#666666',
						lineWidth: 1,
						marker: {
							lineWidth: 1,
							lineColor: '#666666'
						}
					}
				},
				series:finalDataArr
			});
		}
	</script>
  <script type="text/javascript">
   
   getSubEventsOfEvent();
	 var globalMainEntryId = 0 ;
	  function getSubEventsOfEvent(){
		 var eventId = $("#mainEventSelectId").val();
		  var jsObj={
			  eventId : eventId
		  }
		   $.ajax({
			  type:'GET',
			  url: 'getSubEventsOfEventAction.action',
			  data :{task:JSON.stringify(jsObj)}
          }).done(function(result){	
			 	  
			  if(result !=null && result.length>0){
				  for(var i in result){
					  if(result[i].name == "Main Entry" ||  result[i].name.trim() == "Main Entry" ){
						 globalMainEntryId = result[i].id;
					  }
				  }
				  getEventDates();	
			  }			  
		  });
	  }
   
   $( "#mainEventSelectId" ).change(function() {
	  getmainEventsChange();
	  getEventDates();
	   
   });
    function getmainEventsChange(){	 
		getSubEventsOfEvent();		
		  setTimeout(function(){
			allCalls();
			}, 2000);
    } 
	
	$(document).on("click",".refreshButton",function(){
		var currentdate = new Date(); 
		var datetime = currentdate.getDate() + "-"
                + (currentdate.getMonth()+1)  + "-" 
                + currentdate.getFullYear() + " "  
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes() ;

		$("#timeUpdationId").html(datetime);
		getSubEventsOfEvent();		
		  setTimeout(function(){
			allCalls();
			}, 2000);
	});
	
    $(document).ready(function(){
		//page refreshing for each 10 mnts
		/* setInterval(function(){ 
			$( ".refreshButton" ).trigger( "click" );
		}, 600000); */
		
		var currentdate = new Date(); 
		var datetime = currentdate.getDate() + "-"
                + (currentdate.getMonth()+1)  + "-" 
                + currentdate.getFullYear() + " "  
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes() ;

		$("#timeUpdationId").html(datetime);
		
		

		/*  setTimeout(function(){
			allCalls();
		}, 2000); */
	});
	
	var constObj={8:1,4:2,295:3,296:4,2:5,5:6,1:7,3:8,7:9,6:10,10:11,13:12,15:13,12:14,18:15,16:16,342:17,343:18,11:19,321:20,23:21,322:22,318:23,26:24,30:25,24:26,20:27,323:28,31:29,319:30,21:31,320:32,40:33,35:34,36:35,32:36,37:37,41:38,39:39,337:40,336:41,34:42,57:43,367:44,345:45,346:46,347:47,56:48,348:49,349:50,351:51,350:52,55:53,58:54,60:55,59:56,50:57,49:58,315:59,47:60,314:61,51:62,316:63,46:64,317:65,44:66,43:67,54:68,313:69,52:70,53:71,66:72,335:73,68:74,64:75,369:76,69:77,73:78,63:79,62:80,70:81,61:82,65:83,71:84,67:85,77:86,338:87,79:88,339:89,78:90,84:91,82:92,80:93,75:94,81:95,85:96,74:97,89:98,87:99,362:100,86:101,91:102,93:103,94:104,363:105,364:106,97:107,365:108,92:109,324:110,107:111,101:112,104:113,103:114,325:115,105:116,102:117,326:118,100:119,111:120,352:121,117:122,114:123,116:124,108:125,109:126,112:127,353:128,113:129,360:130,124:131,125:132,122:133,120:134,121:135,361:136,129:137,127:138,368:139,354:140,355:141,356:142,357:143,358:144,134:145,136:146,359:147,138:148,133:149,141:150,135:151,140:152,137:153,163:154,157:155,156:156,307:157,155:158,147:159,308:160,159:161,153:162,146:163,160:164,310:165,152:166,309:167,303:168,304:169,305:170,149:171,306:172,172:173,366:174,181:175,174:176,173:177,167:178,179:179,178:180,177:181,180:182,169:183,170:184,171:185,176:186,168:187,194:188,193:189,184:190,185:191,187:192,327:193,328:194,182:195,329:196,330:197,196:198,331:199,195:200,191:201,192:202,186:203,210:204,215:205,206:206,211:207,217:208,213:209,216:210,209:211,212:212,312:213,311:214,199:215,208:216,214:217,207:218,203:219,205:220,344:221,221:222,228:223,218:224,219:225,229:226,227:227,223:228,225:229,226:230,222:231,224:232,232:233,241:234,233:235,340:236,341:237,236:238,231:239,237:240,239:241,238:242,242:243,252:244,243:245,246:246,248:247,251:248,245:249,244:250,250:251,249:252,254:253,332:254,261:255,260:256,263:257,262:258,333:259,257:260,264:261,258:262,265:263,334:264,253:265,255:266,276:267,279:268,297:269,278:270,277:271,298:272,272:273,299:274,273:275,270:276,275:277,300:278,267:279,271:280,290:281,285:282,294:283,286:284,280:285,291:286,289:287,288:288,283:289,301:290,281:291,302:292,284:293,282:294}
	
	$(document).on("change","#eventDatesSelectId",function(){
		var t = $("#eventDatesSelectId").find("option:selected").attr("attr_dates").split(",");
		getHourWiseNowInCampusCadresCount(t[parseInt(t.length)-2]);
		getDetails();
		getTodayTotalVisitors();
		getDaysUniqueAndRevisitSummary();
		getDayWiseVisitSummary();
		getDistrictWiseMembersCountInCampus();
		getConstituencyWiseMembersCountInCampus();
	});
	
	function getEventDates(){
		var jsObj={
			eventId : $("#mainEventSelectId").val(),
			enrollmentIdsList:enrollmentIdsArr
		}
		  
		$.ajax({
			type:'GET',
			url: 'getEventDatesAction.action',
			data :{task:JSON.stringify(jsObj)}
        }).done(function(result){	
			$("#eventDatesSelectId").html("");
			$("#radioDivId").html("");
			if(result != null && result.length > 0){
				var str='';
				var isTodayAvailabel = true;
				
				for(var i in result){
					if(result[i].name != null){
						if(result[i].percentage != null && result[i].percentage=="toDay"){
							isTodayAvailabel = false;
						}
					}
				}
				
				for(var i in result){
					if(result[i].name != null){
						var t = result[i].name.split(",");
						
						if(isTodayAvailabel){
							if(i == 0){
								$("#eventDatesSelectId").append("<option value='"+(parseInt(i)+1)+"' attr_dates='"+result[i].name+"' selected>Day - "+(parseInt(i)+1)+"</option>");
								str+='<input type="radio" class="dayRadio" name="dayRadioName" checked value="'+t[parseInt(t.length)-2]+'"/><label>Day - '+(parseInt(i)+1)+'</label>';
								getHourWiseNowInCampusCadresCount(t[parseInt(t.length)-2]);
							}else{
								$("#eventDatesSelectId").append("<option value='"+(parseInt(i)+1)+"' attr_dates='"+result[i].name+"'>Day - "+(parseInt(i)+1)+"</option>");
								str+='<input type="radio" class="dayRadio" name="dayRadioName" value="'+t[parseInt(t.length)-2]+'"/><label>Day - '+(parseInt(i)+1)+'</label>';
							}
						}else{
							if(result[i].percentage != null && result[i].percentage=="toDay"){
								isTodayAvailabel = false;
								$("#eventDatesSelectId").append("<option value='"+(parseInt(i)+1)+"' attr_dates='"+result[i].name+"' selected>Day - "+(parseInt(i)+1)+"</option>");
								str+='<input type="radio" class="dayRadio" name="dayRadioName" checked value="'+t[parseInt(t.length)-2]+'"/><label>Day - '+(parseInt(i)+1)+'</label>';
								getHourWiseNowInCampusCadresCount(t[parseInt(t.length)-2]);
							}else{
								$("#eventDatesSelectId").append("<option value='"+(parseInt(i)+1)+"' attr_dates='"+result[i].name+"'>Day - "+(parseInt(i)+1)+"</option>");
								str+='<input type="radio" class="dayRadio" name="dayRadioName" value="'+t[parseInt(t.length)-2]+'"/><label>Day - '+(parseInt(i)+1)+'</label>';
							}
						}
						
					}
				}
				
				$("#radioDivId").html(str);
				allCalls();
				
			}
		});
	}
	
	function allCalls(){
		enrollmentIdsArr=[];

		var enrollmentId=$('#enrollmentId').val();
		if(enrollmentId ==null || parseInt(enrollmentId)==0){
			enrollmentIdsArr.push(3);
			enrollmentIdsArr.push(4);
		}else{
			enrollmentIdsArr.push(enrollmentId);
		}
		
		
		getDaysUniqueAndRevisitSummary();	
        getTodayTotalVisitors(); 
        getDetails();		
        getDayWiseVisitSummary();			
		getDistrictWiseMembersCountInCampus();
		getConstituencyWiseMembersCountInCampus();
		
	}
	
 
 function getDaysUniqueAndRevisitSummary(){

 var subEvents1 = [];
	var stateId =0;
	var eventId = $("#mainEventSelectId").val();
	 if(globalMainEntryId !=null && globalMainEntryId>0){
		subEvents1.push(globalMainEntryId); // -- MAHANADU MAIN ENTRY
	 }
	 
	 var attrDateValue = $("#eventDatesSelectId option:selected").attr("attr_dates");
	 
	 var jObj = {
			eventId:eventId,			
			stateId:stateId,
			reportLevelId:0,
			subEvents : subEvents1,	
			dateValues : attrDateValue,
			enrollmentIdsList:enrollmentIdsArr
		}	
		$("#daysSummaryUniqueTableId").html(' ');
		$("#dayWsUnquVstrsPrcssngImgId").show();
		$.ajax({
          type:'GET',
          url: 'getDaysUniqueAndRevisitSummaryAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			$("#dayWsUnquVstrsPrcssngImgId").hide();
			if(result!=null && result.length>0){
				buildDaysUniqueAndRevisitSummary(result);
			}else{
				$("#daysSummaryUniqueTableId").html("<h5> No Data Availabel.</h5>");
			}
		});
 }
  
  function buildDaysUniqueAndRevisitSummary(result){
	
	var str='';
	  str+='<table class="table tableDayWiseRevisit table-condensed">';
	  for(var i in result){
		  str+='<tr>';
		    str+='<td><span class="countNo">'+(parseInt(i)+1)+'</span></td>';
			str+='<td>Total Unique <br/>'+result[i].total+'</td>';
			str+='<td>Only 1 Day<br/>'+result[i].oneDayCount+'</td>';
			str+='<td>Revisits<br/>'+result[i].revisitCount+'</td>';
	     str+'</tr>';
	  }
	 str+='</table>';
	 $("#daysSummaryUniqueTableId").html(str);
  }
  function getTodayTotalVisitors(){
	  	
	$("#tdyVstrsPrcssngImgId").show();
	$(".SelDay").text($("#eventDatesSelectId option:selected").text());
	var attrDateValue = $("#eventDatesSelectId option:selected").attr("attr_dates");
	var value = attrDateValue.split(",").length-2;
	var presentDate= attrDateValue.split(",")[value];
   	var eventId = $("#mainEventSelectId").val();
	   var jObj = {
			eventId:eventId,
			date : presentDate,
			enrollmentIdsList:enrollmentIdsArr
		}
		
		$.ajax({
          type:'GET',
          url: 'getTodayTotalVisitorsInfoAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			$("#tdyVstrsPrcssngImgId").hide();
			if(result!=null){
				  $("#totalVisitorsId").html(result.totalVisitors);
				  $("#inviteesAsVisitorsId").html(result.totalInviteeVisitors);
				  $("#nonInviteesAsVisitorsId").html(result.totalNonInviteeVisitors);
				  $("#visitorsPresentInCampusId").html(result.currentVisitors);
				  $("#inviteesPresentInVisitorsId").html(result.currentInviteeVisitors);
				  $("#nonInviteesPresentInVisitorsId").html(result.currentNonInviteeVisitors);
			}
		});
  }
    function getDetails(){
		  
		  $("#visitorsTableId").html(' ');
		  //$("#hoursWiseVisitors").html(' ');
		  $("#hrWiseVstrsPrcssngImgId").show();
		  //$("#hrWiseVstrsHghChrtPrcssngImgId").show();
		  var currentdate = new Date(); 
		
		var datetime = currentdate.getDate() + "-"
                + (currentdate.getMonth()+1)  + "-" 
                + currentdate.getFullYear() + " "  
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes() ;

		$("#hourWiseTableTimeId").html(datetime);
		$("#hourWiseGraphTimeId").html(datetime);
		
		var attrDateValue = $("#eventDatesSelectId option:selected").attr("attr_dates");
    	 var  eventId = $("#mainEventSelectId").val();
		   var jsObj={
			   eventId:eventId,
			   dateValues:attrDateValue,
				enrollmentIdsList:enrollmentIdsArr
		   }
		  $.ajax({
			  type:'GET',
			  url: 'getTodayTotalAndCurrentUsersInfo.action',
			  data :{task:JSON.stringify(jsObj)}
          }).done(function(result){
			    $("#hrWiseVstrsPrcssngImgId").hide();
				//$("#hrWiseVstrsHghChrtPrcssngImgId").hide();
			  if(result!=null && result.length>0){
				  buildVisitorsResultByTime(result);
				  //buildVisitorsDtlsGraph(result);
			  }else{
				  $("#visitorsTableId").html("<h5> No Data Availabel.</h5>");
				  //$("#hoursWiseVisitors").html("<h5> No Data Availabel.</h5>");
			  }
	      });
	
	  var needInvitees_a = false;
	 function buildVisitorsResultByTime(result){
		 $("#hourWiseTableTimeId1").html(result[(result.length-1)].lastUpdated);
		 var str='';
		 str+='<table class="table table-condensed tableHourWise" cellspacing="0" width="100%">';
                            str+='<thead>';
                                str+='<tr>';
                                  str+='  <th class="back-white">TIME STATUS</th>';
								  for(var i in result){
									  var reqi = parseInt(i)+1;
									  if(needInvitees_a){
										  str+='<th colspan="3" class="text-center table-color1">DAY '+reqi+'</th>';
									  }else{
                                         str+='<th class="text-center table-color1">DAY '+reqi+'</th>';
									  }
								  }
                                str+='</tr>';
                                str+=' <tr>';
                               // str+=' 	<th class="back-white"></th>';
									for(var i in result){
                                    //str+='<th class="color-black table-color2">ATTENDED</th>';
										if(needInvitees_a){
											str+='<th class="color-black table-color2">INVITEES</th>';
											str+='<th class="color-black table-color2">NON INVITEES</th>';
										}
									 }
                                str+='</tr></thead>';
                            
                            str+='<tbody>';
                                str+="<tr>";
				  str+="   <td class='back-white'>Above 8 Hours</td>";
				  for(var i in result){
					  if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].above8hrs != null){
						str+="   "+result[i].above8hrs+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					  if(needInvitees_a){
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].above8hrsInv != null){
							str+="   "+result[i].above8hrsInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].above8hrsInv != null && result[i].above8hrs != null){
							str+="   "+result[i].above8hrs-result[i].above8hrsInv+"</td>";
						  }else if(result[i].above8hrs != null){
							 str+="   "+result[i].above8hrs+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  }
					  
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>7 to 8 Hours</td>";
				  for(var i in result){
					     if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].seventoeight != null){
						str+=""+result[i].seventoeight+"</td>";
					  }else{
						  str+="0</td>";
					  }
					  
					    if(needInvitees_a){
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].seventoeightInv != null){
							str+="   "+result[i].seventoeightInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].seventoeightInv != null && result[i].seventoeight != null){
							str+="   "+result[i].seventoeight-result[i].seventoeightInv+"</td>";
						  }else if(result[i].seventoeight != null){
							 str+="   "+result[i].seventoeight+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						}
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>6 to 7 Hours</td>";
				  for(var i in result){
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].sixtoseven != null){
						str+="  "+result[i].sixtoseven+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					  
					  if(needInvitees_a){  
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].sixtosevenInv != null){
							str+="   "+result[i].sixtosevenInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].sixtosevenInv != null && result[i].sixtoseven != null){
							str+="   "+result[i].sixtoseven-result[i].sixtosevenInv+"</td>";
						  }else if(result[i].sixtoseven != null){
							 str+="   "+result[i].sixtoseven+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  }
					  
					  
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>5 to 6 Hours</td>";
				  for(var i in result){
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].fivetosix != null){
						str+="   "+result[i].fivetosix+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					    if(needInvitees_a){
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].fivetosixInv != null){
							str+="   "+result[i].fivetosixInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].fivetosixInv != null && result[i].fivetosix != null){
							str+="   "+result[i].fivetosix-result[i].fivetosixInv+"</td>";
						  }else if(result[i].fivetosix != null){
							 str+="   "+result[i].fivetosix+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  
						}
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>4 to 5 Hours</td>";
				  for(var i in result){
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
						
					  if(result[i].fourtofive != null){
						str+="  "+result[i].fourtofive+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					  if(needInvitees_a){  
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].fourtofiveInv != null){
							str+="   "+result[i].fourtofiveInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].fourtofiveInv != null && result[i].fourtofive != null){
							str+="   "+result[i].fourtofive-result[i].fourtofiveInv+"</td>";
						  }else if(result[i].fourtofive != null){
							 str+="   "+result[i].fourtofive+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  
					  } 
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>3 to 4 Hours</td>";
				  for(var i in result){
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].threetofour != null){
						str+="   "+result[i].threetofour+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					    if(needInvitees_a){
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].threetofourInv != null){
							str+="   "+result[i].threetofourInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].threetofourInv != null && result[i].threetofour != null){
							str+="   "+result[i].threetofour-result[i].threetofourInv+"</td>";
						  }else if(result[i].threetofour != null){
							 str+="   "+result[i].threetofour+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  
						}
					  
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>2 to 3 Hours</td>";
				  for(var i in result){
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].twotothree != null){
						str+="   "+result[i].twotothree+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					 
					  if(needInvitees_a){
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].twotothreeInv != null){
							str+="   "+result[i].twotothreeInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].twotothreeInv != null && result[i].twotothree != null){
							str+="   "+result[i].twotothree-result[i].twotothreeInv+"</td>";
						  }else if(result[i].twotothree != null){
							 str+="   "+result[i].twotothree+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  }  
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>1 to 2 Hours</td>";
				  for(var i in result){
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].onetotwo != null){
						 str+="   "+result[i].onetotwo+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					  if(needInvitees_a){  
						   if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].onetotwoInv != null){
							str+="   "+result[i].onetotwoInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].onetotwoInv != null && result[i].onetotwo != null){
							str+="   "+result[i].onetotwo-result[i].onetotwoInv+"</td>";
						  }else if(result[i].onetotwo != null){
							 str+="   "+result[i].onetotwo+"</td>";
						  }else{
							  str+="   0</td>";
						  }
					  } 
					  
					  
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'>1/2 to 1 Hours</td>";
				  for(var i in result){
					
					  if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].halfanhour != null){
						 str+="   "+result[i].halfanhour+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					  
					    if(needInvitees_a){
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].halfanhourInv != null){
							str+="   "+result[i].halfanhourInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].halfanhourInv != null && result[i].halfanhour != null){
							str+="   "+result[i].halfanhour-result[i].halfanhourInv+"</td>";
						  }else if(result[i].halfanhour != null){
							 str+="   "+result[i].halfanhour+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						}
				  }
				  str+="</tr>";
				  str+="<tr>";
				  str+="   <td class='back-white'> < 1/2 Hour</td>";
				  for(var i in result){
				
					   if(i == 0){str+="   <td class='table-color1'>";}
						  if(i == 1){str+="   <td class='table-color2'>";}
						  if(i == 2){str+="   <td class='table-color1'>";}
					  if(result[i].belowhalfanhour != null){
						 str+="   "+result[i].belowhalfanhour+"</td>";
					  }else{
						  str+="   0</td>";
					  }
					    if(needInvitees_a){
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].belowhalfanhourInv != null){
							str+="   "+result[i].belowhalfanhourInv+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						  
						  if(i == 0){str+="   <td class='table-color1'>";}
							  if(i == 1){str+="   <td class='table-color2'>";}
							  if(i == 2){str+="   <td class='table-color1'>";}
						  if(result[i].belowhalfanhourInv != null && result[i].belowhalfanhour != null){
							str+="   "+result[i].belowhalfanhour-result[i].belowhalfanhourInv+"</td>";
						  }else if(result[i].belowhalfanhour != null){
							 str+="   "+result[i].belowhalfanhour+"</td>";
						  }else{
							  str+="   0</td>";
						  }
						}
				  }
    				  str+="</tr>";
                            str+="</tbody>";
                        str+="</table>";
					
					$("#visitorsTableId").html(str);
			   }
	 }
  function getDayWiseVisitSummary(){
	var subEvents1 = [];
	var stateId =0;
	 var eventId = $("#mainEventSelectId").val();
	 if(globalMainEntryId !=null && globalMainEntryId>0){
		subEvents1.push(globalMainEntryId); // -- MAHANADU MAIN ENTRY
	 }
	 $("#totalVisitorsDtlsId").html(' ');
     $("#dayWsVstrsPrcssngImgId").show();
	 var jObj = {
			eventId:eventId,			
			stateId:stateId,
			reportLevelId:0,
			subEvents : subEvents1,
			date:$("#eventDatesSelectId option:selected").attr("attr_dates"),
			enrollmentIdsList:enrollmentIdsArr
		}	
		
		$.ajax({
          type:'GET',
          url: 'getDayWiseVisitSummaryAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
		  $("#dayWsVstrsPrcssngImgId").hide();
		  if(result!=null && result.length>0){
			buildTotalVisitorsResult(result);
		  }else{
			$("#totalVisitorsDtlsId").html("<h5> No Data Availabel.</h5>");
		  }
		});
}
var progressBarClsArr=["progress-bar-info","progress-bar-success","progress-bar-warning"];
function buildTotalVisitorsResult(result){
	
	  var totalVisitorsCount=0;
	  for(var i in result){
		  totalVisitorsCount=totalVisitorsCount+result[i].total;
	  }
	 var str='';
	for(var i in result){
		 var number = parseInt(i)+1;
		 var prcntgCmpltd=(result[i].total)*100/totalVisitorsCount;
			if(number>1){
			str+="<p class='m_0'>"+result[i].id+" DAY'S VISITORS &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+result[i].total+" <span class='pull-right'>"+prcntgCmpltd.toFixed(2)+"%</span></p>";
			}else{
		    str+="<p class='m_0'>"+result[i].id+" DAY VISITORS &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+result[i].total+"<span class='pull-right'>"+prcntgCmpltd.toFixed(2)+"%</span></p>";
			}
			str+="<div class='progress progressNewCustom'>";
			   str+="<div class='progress-bar "+progressBarClsArr[i]+"' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width:"+prcntgCmpltd.toFixed(2)+"% '>";
				 str+="<span class='sr-only'>"+result[i].total+" Complete</span>";
			  str+=" </div>";
			str+="</div>";
	 } 
	 $("#totalVisitorsDtlsId").html(str);
}
	
	$(document).on("click",".hrWseVstrsCls",function(){
	 	getDetails();
  	});
	$(document).on("click",".hrWseVstrsInCampCls",function(){
		getHourWiseNowInCampusCadresCount($('input[name=dayRadioName]:checked').val());
  	});
  	$(document).on("click","#rfrshDyWsPrgrssRvstId",function(){
		 getDayWiseVisitSummary();
  	});
  	$(document).on("click","#refreshDaysWiseRevisitId",function(){
	  getDaysUniqueAndRevisitSummary();
  	});
  	/* $(document).on("click","#ttlTdyVstrsId",function(){
	  getTodayTotalVisitors();
  	});
   */
	$(document).on("click","#tsSwitch",function(){
		getDistrictWiseMembersCountInCampus();
		getConstituencyWiseMembersCountInCampus();	
	});
	
	$(document).on("click","#otherStates",function(){
		getDistrictWiseMembersCountInCampus();
		getConstituencyWiseMembersCountInCampus();
	});
	
	$(document).on("click","#apSwitch",function(){
		getDistrictWiseMembersCountInCampus();
		getConstituencyWiseMembersCountInCampus();
	});
	
	function getDistrictWiseMembersCountInCampus(){
		
		var stateIds = [];
		var enrollmentYearIds = [];
		$("#distWiseTableId").html('<img src="images/Loading-data.gif" style="width:70px;height:60px;"/>');
		if($("#tsSwitch").is(":checked")){
			stateIds.push($("#tsSwitch").val());
		}
		
		if($("#otherStates").is(":checked")){
			stateIds.push($("#otherStates").val());
		}
		
		if($("#apSwitch").is(":checked")){
			stateIds.push($("#apSwitch").val());
		}
		
		var t = $("#eventDatesSelectId").find("option:selected").attr("attr_dates").split(",");
		enrollmentYearIds.push($("#enrollmentId").val());
        
		if(stateIds.length > 0){
			var jObj = {
				eventId:$("#mainEventSelectId").val(),			
				stateIds:stateIds,
				date:t[t.length-2],
				enrollmentIdsList : enrollmentYearIds
			}	
			$("#distWiseTableAjax").show();
			$.ajax({
			  type:'GET',
			  url: 'getDistrictWiseMembersCountInCampusAction.action',
			  data : {task:JSON.stringify(jObj)} ,
			}).done(function(result){
				$("#distWiseTableAjax").hide();
				if(result != null && result.subList != null && result.subList.length > 0){
					var str = '';
					var flag=false;
				str+='<table class="table table-condensed tableNewDistWise" id="distWiseTableId">';
				str+='<thead>';
				str+='<th>District Id</th>';
				str+='<th>Name</th>';
				str+='<th>Total</th>';
				str+='<th>Now In Campus</th>';
				str+='<th>Now In Campus %</th>';
				str+='</thead>';
				
					str+='<tbody class="scrollLength">';
					for( var  i in result.subList ){
						if(result.subList[i].total != null && result.subList[i].total > 0 && result.subList[i].cadreCount != null && result.subList[i].cadreCount > 0){
							flag=true;
							str+='<tr>';
							str+='<td>'+result.subList[i].id+'</td>';
							str+='<td>'+result.subList[i].name+'</td>';
							str+='<td>'+result.subList[i].total+'</td>';
							str+='<td>'+result.subList[i].cadreCount+'</td>';
							str+='<td>'+((result.subList[i].cadreCount*100)/result.subList[i].total).toFixed(2)+'%</td>';
							str+='</tr>';
						}
					}
					str+='</tbody>';
					str+='</table>';
					if(!flag){
						$("#distWiseTableDivId").html('No Data Available.');
					}else{
						$("#distWiseTableDivId").html(str);
						$("#districtExcelBtnId").show();
						$('#distWiseTableId').DataTable({
							 "paging":   false,
							 "info":     false,
							 "searching": true,
							 "autoWidth": true,
							"sDom": '<"top"iflp>rt<"bottom"><"clear">',
							"order": [[ 1, "asc" ]]
		
						});
						$("#distWiseTableId_filter").css("margin-top","-31px");
							var Scrollerlength = $(".scrollLength").find("tr").length;
							if(Scrollerlength >=11){
								$("#distWiseTableId").css("display","block");
								$("#distWiseTableId").css("height","380px");
								$("#distWiseTableId").css("overflow-y","scroll");
								$("#distWiseTableId").css("overflow-x","scroll");
								
							}else{
								$("#distWiseTableId").css("height","auto");
								
							}
					}
					
				}else{
					$("#distWiseTableDivId").html('No Data Available.');
				}
				
			});
		}else{
			$("#distWiseTableDivId").html("<h5>Please Select State.</h5>");
		}
	}
	
	
	function getConstituencyWiseMembersCountInCampus(){
		
		
		var stateIds = [];
		var enrollmentYearIds = [];
		$("#distWiseTableId").html('<img src="images/Loading-data.gif" style="width:70px;height:60px;"/>');
		if($("#tsSwitch").is(":checked")){
			stateIds.push($("#tsSwitch").val());
		}
		
		if($("#otherStates").is(":checked")){
			stateIds.push($("#otherStates").val());
		}
		
		if($("#apSwitch").is(":checked")){
			stateIds.push($("#apSwitch").val());
		}
		
		var t = $("#eventDatesSelectId").find("option:selected").attr("attr_dates").split(",");
		enrollmentYearIds.push($("#enrollmentId").val());
		if(stateIds.length > 0){
			var jObj = {
				eventId:$("#mainEventSelectId").val(),			
				stateIds:stateIds,
				date:t[t.length-2],
				enrollmentIdsList : enrollmentYearIds
			}	
			$("#constWiseTableAjax").show();
			$.ajax({
			  type:'GET',
			  url: 'getConstituencyWiseMembersCountInCampusAction.action',
			  data : {task:JSON.stringify(jObj)} ,
			}).done(function(result){
				$("#constWiseTableAjax").hide();
				if(result != null && result.subList != null && result.subList.length > 0){
					var str = '';
					var flag=false;
				str+='<table class="table table-condensed tableNewDistWise" id="constWiseTableId">';
				str+='<thead>';
				str+='<th>Constituency Id</th>';
				str+='<th>Name</th>';
				str+='<th>Total</th>';
				str+='<th>Now In Campus</th>';
				str+='<th>Now In Campus %</th>';
				str+='</thead>';
				
					str+='<tbody classs="scrollLength">';
					for( var  i in result.subList ){
						if(result.subList[i].total != null && result.subList[i].total > 0 && result.subList[i].cadreCount != null && result.subList[i].cadreCount > 0){
							flag=true;
							str+='<tr>';
							if(constObj[result.subList[i].id] === undefined || typeof constObj[result.subList[i].id] == "undefined" ){
								str+='<td>-</td>';
							}else{
								str+='<td>'+constObj[result.subList[i].id]+'</td>';
							}
							str+='<td>'+result.subList[i].name+'</td>';
							str+='<td>'+result.subList[i].total+'</td>';
							str+='<td>'+result.subList[i].cadreCount+'</td>';
							str+='<td>'+((result.subList[i].cadreCount*100)/result.subList[i].total).toFixed(2)+'%</td>';
							str+='</tr>';
						}
					}
					str+='</tbody>';
					str+='</table>';
					if(!flag){
						$("#constWiseTableDivId").html('No Data Available.');
					}else{
						$("#constWiseTableDivId").html(str);
						$("#constituecnyExcelBtnId").show();
						$('#constWiseTableId').DataTable({
							 "paging":   false,
							 "info":     false,
							 "searching": true,
							 "autoWidth": true,
							"sDom": '<"top"iflp>rt<"bottom"><"clear">',
							"order": [[ 1, "asc" ]]
		
						});
						$("#constWiseTableId_filter").css("margin-top","-31px");
							var Scrollerlength = $(".scrollLength").find("tr").length;
							if(Scrollerlength >=11){
								$("#constWiseTableId").css("display","block");
								$("#constWiseTableId").css("height","380px");
								$("#constWiseTableId").css("overflow-y","scroll");
								$("#constWiseTableId").css("overflow-x","scroll");
								
							}else{
								$("#constWiseTableId").css("height","auto");
								
							}
					}
					
				}else{
					$("#constWiseTableDivId").html('Please Select State.');
				}
			});
		}else{
			$("#constWiseTableDivId").html("<h5> No Data Availabel.</h5>");
		}
	}
	
	/* function getTodayCount(type){
		var eventId = $("#mainEventSelectId").val(); 
		$.ajax({
			type:'GET',
			url: 'getTodayCountAction.action',
			data : {eventId:eventId} ,
		}).done(function(result){
			if(result != null && result > 0){
				$("input[name=dayRadioName][value=" + result + "]").prop('checked', true);
				$("input[name=dayRadioName][value=" + result + "]").trigger("click");
			}else if(result == 0){
				$("input[name=dayRadioName][value=1]").trigger("click");
			}
		});
	} */
	
	$(document).on("click",".dayRadio",function(){
		getHourWiseNowInCampusCadresCount($(this).val());
	});
	
	function getHourWiseNowInCampusCadresCount(date){
		$('#hoursWiseVisitors').html("");
		$("#hrWiseVstrsHghChrtPrcssngImgId").show();
		
		var jObj = {
				dayVal:date,
				eventId : $("#mainEventSelectId").val(),
				enrollmentIdsList:enrollmentIdsArr,
				eventType : "MAIN ENTRY"
			}	
			
			$.ajax({
			  type:'GET',
			  url: 'getHourWiseNowInCampusCadresCountAction.action',
			  data : {task:JSON.stringify(jObj)} ,
			}).done(function(result){
				$("#hrWiseVstrsHghChrtPrcssngImgId").hide();
				
				if(result != null && result.length > 0){
					
					var categoriesArr=[];
					var totalAttendedArr=[];
					var nowInCampusArr=[];
					
					//var d = new Date();
					//var n = d.getHours(); 
					var n = 20;
					for(var i in result){
						
						if(result[i].id <= n){
							categoriesArr.push(result[i].name);
							totalAttendedArr.push(parseInt(result[i].total));
							nowInCampusArr.push(parseInt(result[i].cadreCount));
						}else{
							categoriesArr.push(result[i].name);
							totalAttendedArr.push(0);
							nowInCampusArr.push(0);
						}
					}
					
					$(function () {
						$('#hoursWiseVisitors').highcharts({
							chart: {
								type: 'column'
							},
							title: {
								text: 'Hour Wise In Campus Counts'
							},
							subtitle: {
								text: ''
							},
							xAxis: {
								categories: categoriesArr,
								crosshair: true
							},
							yAxis: {
								min: 0,
								title: {
									text: 'Members Count'
								}
							},
							tooltip: {
								headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
								pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
									'<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
								footerFormat: '</table>',
								shared: true,
								useHTML: true
							},
							plotOptions: {
								column: {
									pointPadding: 0.2,
									borderWidth: 0
								}
							},
							series: [{
								name: 'Total',
								data: totalAttendedArr

							}, {
								name: 'Now In Campus',
								data: nowInCampusArr

							}]
						});
					});
				}else{
					$('#hoursWiseVisitors').html("No Data Availabel.");
				}
			});
	}
function generateExcel(){
	tableToExcel(distWiseTableId, 'District Wise Report');
}
function generateExcel1(){
	tableToExcel(constWiseTableId, 'Constituency Wise Report');
}

$(document).on("change","#enrollmentId",function(){
	enrollmentIdsArr=[];
	var enrollmentId=$('#enrollmentId').val();
	if(enrollmentId ==null || parseInt(enrollmentId)==0){
		enrollmentIdsArr.push(3);
		enrollmentIdsArr.push(4);
	}else{
		enrollmentIdsArr.push(enrollmentId);
	}
	$( "#mainEventSelectId" ).trigger('change');
});

</script>
<script>
var tableToExcel = (function() {
   var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()

</script>
</body>
</html>