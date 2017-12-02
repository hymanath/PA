$(document).ready(function() {
	
	if(cadreParticipatedConstiId != null && cadreParticipatedConstiId.trim().length >0){		
		if(cadreParticipatedConstiId != null && cadreParticipatedConstiId.length>0)
			$('#cadreParticipatedConstituencyId').val(parseInt(cadreParticipatedConstiId));
		if(cadreParticipatedDistiId != null && cadreParticipatedDistiId.length>0)
			$('#cadreParticipatedDistrictId').val(parseInt(cadreParticipatedDistiId));
	}else{
		
	}
	
	
  var cb = function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
  }

setcolorsForStatus();
  var optionSet1 = {
	startDate: moment().subtract(29, 'days'),
	endDate: moment(),
	minDate: '01/01/2012',
	maxDate: '12/31/2015',
	//dateLimit: { days: 60 },
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'btn-small btn-primary newsSubmitBtn',
	cancelClass: 'btn-small',
	format: 'MM/DD/YYYY',
	separator: ' to ',
	locale: {
		applyLabel: 'Submit',
		cancelLabel: 'Clear',
		fromLabel: 'From',
		toLabel: 'To',
		customRangeLabel: 'Custom',
		daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		firstDay: 1
	}
  };

  var optionSet2 = {
	startDate: moment().subtract(7, 'days'),
	endDate: moment(),
	opens: 'left',
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	}
  };

  $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

  $('#reportrange').daterangepicker(optionSet1, cb);

  $('#reportrange').on('show.daterangepicker', function() { console.log("show event fired"); });
  $('#reportrange').on('hide.daterangepicker', function() { console.log("hide event fired"); });
  $('#reportrange').on('apply.daterangepicker', function(ev, picker) { 
	console.log("apply event fired, start/end dates are " 
	  + picker.startDate.format('MMMM D, YYYY') 
	  + " to " 
	  + picker.endDate.format('MMMM D, YYYY')
	); 
  });
  $('#reportrange').on('cancel.daterangepicker', function(ev, picker) { console.log("cancel event fired"); });
   var cb = function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
  }
  
  /*Meeting DatePicker*/
//setcolorsForStatus();
  var MeetingSet = {
	startDate: moment().subtract(1, 'year'),
	endDate: moment(),
	/* minDate: '01/01/2012',
	maxDate: '12/31/2015', */
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   'Last Year': [moment().subtract(1, 'year'),moment()]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'btn-small btn-primary meetingSubmitBtn',
	cancelClass: 'btn-small',
	format: 'MM/DD/YYYY',
	separator: ' to ',
	locale: {
		applyLabel: 'Submit',
		cancelLabel: 'Clear',
		fromLabel: 'From',
		toLabel: 'To',
		customRangeLabel: 'Custom',
		daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		firstDay: 1
	}
  };
  $('#meetingDatePicker span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

  $('#meetingDatePicker').daterangepicker(MeetingSet, cb);

  $('#meetingDatePicker').on('show.daterangepicker', function() { /*console.log("show event fired"); */});
  $('#meetingDatePicker').on('hide.daterangepicker', function() { /*console.log("hide event fired"); */});
  $('#meetingDatePicker').on('apply.daterangepicker', function(ev, picker) { 
	/*console.log("apply event fired, start/end dates are " 
	  + picker.startDate.format('MMMM D, YYYY') 
	  + " to " 
	  + picker.endDate.format('MMMM D, YYYY')
	); */
  });
  $('#MeetingDatePicker').on('cancel.daterangepicker', function(ev, picker) { /*console.log("cancel event fired");*/ });
  
  //alert DatePicker
  var Alertset = {
	startDate: moment(),
	endDate: moment(),
	/* minDate: '01/01/2012',
	maxDate: '12/31/2015', */
	showDropdowns: true,
	showWeekNumbers: true,
	timePicker: false,
	timePickerIncrement: 1,
	timePicker12Hour: true,
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   'Last Year': [moment().subtract(1, 'year'),moment()]
	},
	opens: 'left',
	buttonClasses: ['btn btn-default'],
	applyClass: 'alertSubmitBtn',
	cancelClass: 'btn-small',
	format: 'MM/DD/YYYY',
	separator: ' - ',
	locale: {
		applyLabel: 'Submit',
		cancelLabel: 'Clear',
		fromLabel: 'From',
		toLabel: 'To',
		customRangeLabel: 'Custom',
		daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
		monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
		firstDay: 1
	}
  };
  $('#alertsDatePicker').val(moment().subtract(1, 'year').format('MM/DD/YYYY') + ' - ' + moment().format('MM/DD/YYYY'));

  $('#alertsDatePicker').daterangepicker(Alertset);

  $('#alertsDatePicker').on('show.daterangepicker', function() { /*console.log("show event fired"); */});
  $('#alertsDatePicker').on('hide.daterangepicker', function() { /*console.log("hide event fired"); */});
  $('#alertsDatePicker').on('apply.daterangepicker', function(ev, picker) { 
	 /* picker.startDate.format("MM/DD/YYYY"));
	picker.endDate.format("MM/DD/YYYY")); */ 
	  getCadreAlertDetails();
  });
  $('#alertsDatePicker').on('cancel.daterangepicker', function(ev, picker) { /*console.log("cancel event fired");*/ });
  
  getLocationWisePartyMeetings();
});

function getParticipatedConstituencyId(cadreId){
		var jsObj={
			tdpCadreId:cadreId
		}	
		$.ajax({
				type:'POST',
				 url: 'getParticipatedConstituencyAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(result !=null){
					
						if(result.districtId != null && result.districtId > 0)
						$("#cadreParticipatedDistrictId").val(''+result.districtId+'');
						if(result.id != null && result.id > 0)
							$("#cadreParticipatedConstituencyId").val(''+result.id+'');
						if(result.parlimentId != null && result.parlimentId > 0)
						$("#cadreParticipatedPConstituencyId").val(''+result.parlimentId+'');
					
						participatedConstituencyId =result.id;
						participatedConstituencyType = result.electionType;
						participatedParliamentId = result.parlimentId;
						participatedDistrictId=result.districtId;
						
						participatedConstName = result.name;
						participatedParlName = result.parliament;
						participatedDistName = result.district;
						if(participatedConstituencyId >0){
							$(".participatedClass").hide();
						}
					
						if(participatedConstituencyId != null && participatedConstituencyId > 0){
						
							$("#participatedDivId").show();
							$("#participatedConstId").html(""+result.name+"&nbsp;&nbsp;"+participatedConstituencyType+"");
						}else if(participatedParliamentId != null && participatedParliamentId > 0){
							$("#participatedDivId").show();
							participatedConstituencyType=" Parliament";
							$("#participatedConstId").html(""+result.name+"&nbsp;&nbsp;"+participatedConstituencyType+"");
						}
						getCategoryWiseStatusCount();				
						//getCadreFamilyDetailsByCadreId();//swadhin
						//getElectionPerformanceInCadreLocation();
						getApprovedFinancialSupprotForCadre();
						cadreFormalDetailedInformation(globalCadreId);
						getEventDetailsOfCadre(globalCadreId);
						//getTdpCadreSurveyDetails(globalCadreId,0,null,"All",0);
						getLocationwiseCommitteesCount();
						getPartyMeetingsOverViewForCadre();
						getEventsOverviewFortdpCadre();
						//getCandidateAndConstituencySurveyResult();
						getActivityDetails();
						//getCandidateAppliedPostsByCadre(0);
					}
				});
			}
		
var globalVoterCardNo = "";	
var globalidentityMembershipNo = ""	;
	function cadreFormalDetailedInformation(globalCadreId){
			var localCadreId=globalCadreId;
			//loading images showing
			$("#dataLoadingsImgForownBoothDetailsId").show();
			//$("#dataLoadingsImgForImagePath").show();
			
			$('#enrollementDiv').html('');
			
			//hiding divs of Election && and Grievance details
			$("#electionProfileMainDivId").hide();
			$("#grievanceDetailsMainDivId").hide();
			var jsobj={
				cadreId:localCadreId
			}
			$.ajax({
				type:'GET',
				 url: 'cadreFormalDetailedInformationAction.action',
				 data : {task:JSON.stringify(jsobj)} ,
			}).done(function(result){
				getElectionPerformanceInCadreLocation();
				getCadreNotesInformationDetails(0);
				//loading images hiding
				 $("#dataLoadingsImgForownBoothDetailsId").hide();
				// $("#dataLoadingsImgForImagePath").hide();
				 
				var str='';
				var strEnrollment='';
				if(result !=null){
						//$("#dataLoadingsImgForownBoothDetailsId").show();
						//$("#dataLoadingsImgForImagePath").show();
						
				//nameId dobId ageId qualificationId occupationId voterIdSpan panchayatId mandalId constituencyId positionId representativeId
					/* start Survey Fields */	
									
					$('#cadreRole').val(result.role);
					$('#cadreLevel').val(result.status);
					$('#cadreCommitteeType').val(result.commiteeName);
					$('#publicRepresentativeTypId').val(result.representativeType);
					
				/*if((result.commiteeName != null && (result.commiteeName == 'Main' || result.commiteeName == 'main' )) )
					{
						var mstr='';
						mstr+=' <ul class="nav nav-tabs nav-tabs-custom" role="tablist">';
						if(result.status != null)
						{
							/*if(result.status == 'central' || result.status == 'Central' )
							{																
								 mstr+='<li role="presentation"  class="meetingCls  "  id="district" key="districttabs"><a href="#districttabs" aria-controls="districttabs" role="tab" data-toggle="tab" onclick="getConductedPartyMeetingDetails(\'districttabs\',\'district\',\'true\');">Central</a></li>';								
							}*/
						/*	if(result.status == 'district' || result.status == 'District' )
							{																
								 mstr+='<li role="presentation"  class="meetingCls  "  id="district" key="districttabs"><a href="#districttabs" aria-controls="districttabs" role="tab" data-toggle="tab" onclick="getConductedPartyMeetingDetails(\'districttabs\',\'district\',\'true\');">District</a></li>';								
							}
							if(result.status == 'district' || result.status == 'District' || result.status == 'constituency' || result.status == 'Constituency' )
							{																
								mstr+='<li role="presentation"   class="meetingCls" id="constituency" key="constabs"><a href="#constabs" aria-controls="constabs" role="tab" data-toggle="tab" onclick="getConductedPartyMeetingDetails(\'constabs\',\'constituency\',\'true\');">Constituency</a></li>';								
							}
							if(result.status == 'district' || result.status == 'District' || result.status == 'mandal' || result.status == 'Mandal' || result.status == 'town' || result.status == 'Town'  || result.status == 'division' || result.status == 'Division' || result.status == 'constituency' || result.status == 'Constituency' )
							{																
								mstr+='<li role="presentation"  class="meetingCls"  id="MandalORTownORDivision" key="mandaltabs"><a href="#mandaltabs" aria-controls="mandaltabs" role="tab" data-toggle="tab" onclick="getConductedPartyMeetingDetails(\'mandaltabs\',\'MandalORTownORDivision\',\'true\');">Mandal/Town/Division</a></li>';
								
							}
							if(result.status == 'district' || result.status == 'District' || result.status == 'ward' || result.status == 'Ward' || result.status == 'village' || result.status == 'Village' || result.status == 'mandal' || result.status == 'Mandal' || result.status == 'town' || result.status == 'Town'  || result.status == 'division' || result.status == 'Division' || result.status == 'constituency' || result.status == 'Constituency' ){
								mstr+='<li role="presentation"  class="meetingCls"  id="VillageORWard" key="villagetabs"><a href="#villagetabs" aria-controls="settings" role="tab" data-toggle="tab" onclick="getConductedPartyMeetingDetails(\'villagetabs\',\'VillageORWard\',\'true\');">Village/Ward</a></li>';
							}
							
						}
                         
                         mstr+=' </ul>';
                         mstr+=' <div class="tab-content">';
                         mstr+=' <div role="tabpanel" class="tab-pane active" id="statetabs">';
                         mstr+=' </div>';
                         mstr+=' <div role="tabpanel" class="tab-pane" id="districttabs"></div>';
                         mstr+=' <div role="tabpanel" class="tab-pane" id="constabs"></div>';
                         mstr+=' <div role="tabpanel" class="tab-pane" id="mandaltabs"></div>';
                         mstr+=' <div role="tabpanel" class="tab-pane" id="villagetabs"></div>';
                         mstr+=' </div>';
							
						$('#committeMeetingDivId').html(mstr);
						$('#commitMeetingDiv').show();
	
					}*/
					/*else if(result.representativeType != null){
						alert(result.representativeType.trim())
						var representStr = result.representativeType.trim().split(",");
						alert(representStr);
						if(representStr.search('MLA') || representStr.search('mla')){
							alert(1111);
							
						}
					}*/
					//else if((result.representativeType != null && (result.representativeType.trim().split(",") == 'MLA' || result.representativeType.trim() == 'mla' )) )
					//{
						var mstr='';
							mstr+=' <ul class="nav nav-tabs nav-tabs-custom" role="tablist">';															
							mstr+='<li role="presentation"   class="meetingCls" id="constituency" key="constabs"><a href="#constabs" aria-controls="constabs" role="tab" data-toggle="tab" onclick="getConductedPartyMeetingDetails(\'constabs\',\'constituency\',\'true\',\'0\');">Constituency</a></li>';
																						
							mstr+='<li role="presentation"  class="meetingCls"  id="MandalORTownORDivision" key="mandaltabs"><a href="#mandaltabs" aria-controls="mandaltabs" role="tab" data-toggle="tab" onclick="getConductedPartyMeetingDetails(\'mandaltabs\',\'MandalORTownORDivision\',\'true\',\'0\');">Mandal/Town/Division</a></li>';	
							mstr+='<li role="presentation"  class="meetingCls active"  id="VillageORWard" key="villagetabs"><a href="#villagetabs" aria-controls="settings" role="tab" data-toggle="tab" onclick="getConductedPartyMeetingDetails(\'villagetabs\',\'VillageORWard\',\'true\',\'0\');">Village/Ward</a></li>';
													 
							 mstr+=' </ul>';
							 mstr+=' <div class="tab-content">';
							 mstr+='   <div role="tabpanel" class="tab-pane" id="statetabs">';
							 mstr+='   </div>';
							  //mstr+='  <div role="tabpanel" class="tab-pane" id="districttabs"></div>';
							  mstr+='  <div role="tabpanel" class="tab-pane" id="constabs"></div>';
							  mstr+='  <div role="tabpanel" class="tab-pane" id="mandaltabs"></div>';
							 mstr+='   <div role="tabpanel" class="tab-pane active" id="villagetabs"></div>';
							 mstr+='  </div>';
								
							$('#committeMeetingDivId').html(mstr);
							$('#commitMeetingDiv').show();
				//	}
					
					//getConductedPartyMeetingDetails('villagetabs','VillageORWard','true','0');
					
				//nameId dobId ageId qualificationId occupationId voterIdSpan panchayatId mandalId constituencyId positionId representativeId
					/* start Survey Fields */	
									
					$('#cadreBoothId').val(result.boothId);				
					$('#cadrePartNo').val(result.partNo);				
					$('#cadrePanchaytId').val(result.panchayatId);
					$('#cadremandalId').val(result.tehsilId);	
					$('#cadreRuralORUrbanId').val(result.localElectionBody);						
					$('#cadreConstituencyId').val(result.constituencyId);	
					$('#cadrePConstituencyId').val(result.pConstituencyId);						
					$('#cadreDistrictId').val(result.districtId);
					$('#cadreVoterCardNo').val(result.voterIdCardNo);
					$('#cadreMemberShipId').val(result.membershipNo);
					
					$('#cadreWardId').val(result.wardId);
					$('#wardName').val(result.wardName);
					/* end Survey Fields */
					
					/*  fb Details */

					if(result.fbUrl !=null && result.fbUrl !="" && result.fbUrl !="-"){
						$("#fbUrlImageId").html('<a href="'+result.fbUrl+'"><img src="images/fbIcon.png" width="20px" height="20px"></a>');
					}
					if(result.wAppStatus !=null && result.wAppStatus !="" && result.wAppStatus =="YES"){
						$("#wAppImageId").html('<img src="images/watsApp.png" width="20px" height="20px" style="margin-left: 10px;">');
					}
					
					
					/* candidate Delete Reason*/
					if(result.deletedStatus !=null && result.deletedStatus =="MD"){
						$("#deletedReasonId").html("<td class='text-bold' style='color:yellow;'>DELETED REASON :&nbsp "+result.deletedreason+"</td>");
					}
					
					 $("#nameId").html(result.name);
					 $("#dobId").html(result.dateOfBirth); 
					 $("#ageId").html(result.age);
					 $("#qualificationId").html(result.qualification);
					 $("#occupationId").html(result.occupation);
					 if(result.voterIdCardNo != ""){
						 globalVoterCardNo = result.voterIdCardNo;
					 $("#voterIdSpan").html(result.voterIdCardNo);
					 
					 if(result.isFamilyVoterId =="false"){
						// $("#isFamilyId").html('<b>Own Voter ID</b>');
						 $("#isFamilyId").html('<b><img src="images/User_icon_BLACK-01.png" title="Own Voter ID"> </b>');
					 }else if(result.isFamilyVoterId == "true"){
						 //$("#isFamilyId").html('<b>Family Voter ID </b>');
						 $("#isFamilyId").html('<b><img src="images/family_icon.png"  title="Family Voter ID" ></b>');
					 }
					 }else{
						 $("#isFamilyId").html("Not Available."); 
					 }
					 if(result.panchayatName != null && result.panchayatName != "-"){
						$("#panchayatId").html(result.panchayatName);
					 }
					 else{
						$("#panchayatId").html(result.wName); 	 
					 }
					$("#mandalId").html(result.tehsilName);
					$("#constituencyId").html(result.constituencyName);
					 if(result.partyPosition != null)
					 $("#positionId").html(result.partyPosition);
					else 
					 $("#positionId").html('N/A');
					 $("#representativeId").html(result.representativeType);
					 
					 var x = parseInt(result.volunteerCount);
					 if(x>0)
						$("#volunteerId").html(result.volunteerCount);
					else
						$("#volunteerNullId").hide();
					 //console.log(result.volunteerCount);
					 $("#mobileNoId").html(result.mobileNo);
					 $("#memberShipNoId").html(result.membershipNo);
					 $("#casteFormalId").html(result.casteName);
					 $("#registeredOnId").html(result.registeredTime);
					 $("#registeredAtId").html(result.registeredOn);
					 globalidentityMembershipNo = result.membershipNo;
					 
					 //previous enrollmetYears building
					/* var preEnrollemets=[];
					 var preEnrolleArray=new Array();
					 if(result.enrollmentYears !=null && result.enrollmentYears !=""){
						preEnrollemets = result.enrollmentYears.split(",");
					 }
					 
					 if(preEnrollemets !=null && preEnrollemets.length>0){
						 $(".enrollmentCls").show();
						 for(var e in preEnrollemets){
							  strEnrollment+='<li class="badge">'+preEnrollemets[e]+'</li> ';
						 }
						 $("#cadreYearsUlId").html(strEnrollment);
					 }
					 */
					 var str2 ='';
					if(result.enrollmentYears != null && result.enrollmentYears.trim().length > 0)
					{
						var years = result.enrollmentYears.split(",");	
						
						if(years.indexOf("2016") > -1)
							str2+='<li class="yes">2016<span></span></li>&nbsp;';
						else
							str2+='<li class="no">2016<span></span></li>&nbsp;';
						if(years.indexOf("2014") > -1)
							str2+='<li class="yes">2014<span></span></li>&nbsp;';
						else
							str2+='<li class="no">2014<span></span></li>&nbsp;';
						if(years.indexOf("2012") > -1)
							str2+='<li class="yes">2012<span></span></li>&nbsp;';
						else
							str2+='<li class="no">2012<span></span></li>&nbsp;';
						if(years.indexOf("2010") > -1)
							str2+='<li class="yes">2010<span></span></li>&nbsp;';
						else
							str2+='<li class="no">2010<span></span></li>&nbsp;';
					}
					else
					{
						str2+='<li class="no">2016<span></span></li>&nbsp;';
						str2+='<li class="no">2014<span></span></li>&nbsp;';
						str2+='<li class="no">2012<span></span></li>&nbsp;';
						str2+='<li class="no">2010<span></span></li>&nbsp;';
					}

					$("#enrollementDiv").html(str2);
					 
					 if(result.candidate !=null && result.candidate !=0){
						 globalCandidateId=result.candidate;
					 }
					 
					 if(result.emailId ==null || result.emailId =="" || result.emailId ==undefined){
						 $("#emailMainSpanId").hide();
						  //$("#emailSpanId").html(result.emailId);
					 }
					else{
						 $("#emailMainSpanId").show();
						$("#emailSpanId").html(result.emailId);
					}
					
					 $("#districtNoId").html(result.districtName);
					 var cadreDistrictId  = parseInt(result.districtId);
					if(cadreDistrictId >= 1 && cadreDistrictId <=10 ){						
						$("#cadreStateId").val(36);
						$("#stateNoId").html("Telangana");
					}
					else if(cadreDistrictId >= 11 && cadreDistrictId <=23 ){
						$("#stateNoId").html("Andhra Pradesh");
						$("#cadreStateId").val(1);
					}
					else
						$("#stateNoId").html(result.stateName);
					
					 $("#houseNoId").html(result.houseNo);
					 
					 $("#globalAreaType").html(result.areaType);
					 
					 if(result.imagePath !=null && result.imagePath !=""){
						 $("#imagePathId").html('<img src="'+result.imagePath+'" class="media-object img-circle" style="border:1px solid #ccc;margin-top:10px;" width="80px" height="80px;">'); 
					 }else{
						 $("#imagePathId").html('<img src="images/User.png" class="media-object img-circle" style="border:1px solid #ccc;margin-top:10px;" width="80px" height="80px;">');
					 }
					 
					 //assigning radio ButtonIds for News 
					 
					 $("#panchayatRadioNewsId").val(result.panchayatId);
					 $("#mandalRadioNewsId").val(result.tehsilId);
					 
					 
					 if(participatedConstituencyType == null || participatedConstituencyType =="" || participatedConstituencyType == undefined){
						 
						 //Hiding Panchayat && Mandal Div
						 if(result.localElectionBody ==0 || result.localElectionBody ==null){
							$(".hidingMandalCls").show();
							//$('#panchayatRadioNewsId').prop('checked', true);
						 }else{
							//$('#aConstiRadioNewsId').prop('checked', true);
							 $(".hidingMandalCls").hide();					 
						 }
						 
						  $("#aConstiRadioNewsId").val(result.constituencyId);
						  $("#pConstiRadioNewsId").val(result.pConstituencyId);
						  $("#districtRadioNewsId").val(result.districtId);
					 }
					 else{
						 
						  $("#aConstiRadioNewsId").val(participatedConstituencyId);
						  $("#pConstiRadioNewsId").val(participatedParliamentId);
						  $("#districtRadioNewsId").val(participatedDistrictId);
						  if(participatedConstituencyType == "Parliament"){
							  $(".hidingAssemlyCls").hide();
						  }
						  else{
							  $(".hidingAssemlyCls").show();
						  }
						  
						  $(".hidingMandalCls").hide();	
					 }
					 $('#districtRadioNewsId').prop('checked', true);
					 
					  
					  //Calling WebService 
						   getCandidateAndLocationSummaryNews();

					
					 //
					  $("#dataLoadingsImgForownBoothDetailsId").hide();
					  $("#dataLoadingsImgForImagePath").hide();
					 
					 if(result.ccmVO !=null && result.ccmVO!=""){
						 ownBoothDetailsVo=result.ccmVO;
						 buildingOwnBoothDetails(ownBoothDetailsVo);
					 }
					
					 //assigning values to global variables
					 if(result.localElectionBody ==0 || result.localElectionBody ==null){
						globalPanchayatId=result.panchayatId;
						globalTehsilId=result.tehsilId;
					 }
					 globalConstituencyId=result.constituencyId;
					 globalParliamentId=result.pConstituencyId;
					 globalDistrictId=result.districtId;
					 globalElectionBodyId=result.localElectionBody;	

					 globalPancName = result.panchayatName;
					 globalTehsName = result.tehsilName;
					 globalConstName = result.constituencyName;
					 globalParlName = result.pConstituencyName;
					 globalDistName = result.districtName;
					    
						
					getCandidateAndConstituencySurveyResult();
					complaintDetailsOfCadre(localCadreId,result.membershipNo);
					getCandidateElectDetatails(localCadreId);
					getCheckCandidateExits();
					getDeathsAndHospitalizationDetails();
					//getTdpCadreSurveyDetails(globalCadreId,0,null,"NotAll",0,'true');
					getCadreFamilyDetailsByCadreId();
					getTotalComplaintsForCandidate();
					getPDFReportsForNominatedComplaints();
					buildReport();
					buildCadreHealthReport();
					getRefferelDetailsStatusWise();
					getConductedPartyMeetingDetails("","","true","0");
					getTrainingCampAttendenceInfoInCadreLocation();
					//getGrievanceStatusDetails();
					getStatusCountsForGrievanceDetails();
					getApprovedAmountsForGrievance();
					var cadreDisId = $('#cadreDistrictId').val();
					
					if(cadreDisId != null && cadreDisId<=10)
						$("#trainingCampdetailsDiv").hide();
					getTotalIVRDetailsByTdpCadreId(0);
					getIVRSummaryByTdpCadreId();
					getCadreLocationWiseEventAttendeeCounts($('#cadreDistrictId').val(),"DISTRICT","DISTRICT","cadreEvntdistrictAttnds","onload","");
					getTotalMemberShipRegistrationsInCadreLocation(4,22);
					getTotalMemberShipRegistrationsInCadreLocation(3,11);
					getCadreAlertDetails();	
					//getCadreToursDetails();	
					  getCadreTourDetails();
					//getUpdatedCastePartyInfo();
				}
			});
		}
	function buildingOwnBoothDetails(boothDetailsVo){
			
			var ownBoothDetailsVo=boothDetailsVo;
			var str='';
			
			if(ownBoothDetailsVo !=null && ownBoothDetailsVo !=""){
				if(ownBoothDetailsVo.ownBoothPerc !=null){//own booth
					str+='<td>'+ownBoothDetailsVo.ownBoothPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(ownBoothDetailsVo.ownPanchPerc !=null){//own panchayat
					str+='<td>'+ownBoothDetailsVo.ownPanchPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(ownBoothDetailsVo.ownMandalPerc !=null){//own Mandal
					str+='<td>'+ownBoothDetailsVo.ownMandalPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(ownBoothDetailsVo.ownConstiPerc !=null){//own Constituency
					str+='<td>'+ownBoothDetailsVo.ownConstiPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(ownBoothDetailsVo.ownWardPerc !=null){//own WardPerc
					str+='<td>'+ownBoothDetailsVo.ownWardPerc+'</td>';
				}else{
					str+='<td>-</td>';
				}
				
				$("#ownBoothDetailsId").html(str);
			}
			
		}
		
		function complaintDetailsOfCadre(cadreId,membershipId){
			var localCadreId=cadreId;
			var localMemberShipId=membershipId;
			//$("#dataLoadingsImgForComplaint").show();
			//33160
			var jsobj={
				cadreId:localCadreId,
				membershipId:localMemberShipId
			}
			$.ajax({
				type:'GET',
				 url: 'complaintDetailsOfCadreAction.action',
				 data : {task:JSON.stringify(jsobj)} ,
			}).done(function(result){
				var str='';
				/* if(result !=null){
					if(result.knownList !=null && result.knownList.length>0){
						$("#grievanceDetailsMainDivId").show();
					
						$("#totalComplaintsId").html(result.knownList.length);
					
					for(var i in result.knownList){
						str+='<tr>';
							str+='<td>'+result.knownList[i].name+'</td>';
							str+='<td>'+result.knownList[i].occupation+'</td>';
							str+='<td>'+result.knownList[i].dateOfBirth+'</td>';
							str+='<td>'+result.knownList[i].status+'</td>';
							str+='<td>'+result.knownList[i].representativeType+'</td>';
							str+='<td>'+result.knownList[i].type+'</td>';
						str+='</tr>';
					}
					$("#grievanceDetailsId").html(str);
					}
					else{
						$("#grievanceDetailsId").html("Data Not Available.");
					}
				} */
			});
		}
	
		function getEventDetailsOfCadre(globalCadreId){
			var localCadreId=globalCadreId;
			//8341157
			var jsobj={
				cadreId:localCadreId
			}
			$.ajax({
				type:'GET',
				 url: 'getEventDetailsOfCadreAction.action',
				 data : {task:JSON.stringify(jsobj)} ,
			}).done(function(myresult){
			if(myresult !=null && myresult.length>0 && myresult !=""){
				//$("#participationTableMainDivId").show();
				$("#eventParticipationCollapseDivId").show();
				
				var str='';
				for(var k in myresult)
				{
					str+='<h4>'+myresult[k].eventTypeStr.toUpperCase()+'</h4>';
					str+='<div class="table-responsive">';
					str+='<table class="table m_0 table-bordered table-responsive" style="margin-top: 10px">';
					str+='<thead style="background:#f2f2f2">';
					str+='<tr>';
					/* str+='<th colspan="5" style="background-color:#CCCCCC;text-align:center;"> '+myresult[k].eventTypeStr.toUpperCase()+' PARTICIPATION DETAILS </th>';		 */	

					//$("#eventHeadingId").html('<span> EVENT PARTICIPATION DETAILS </span>');
					str+='</tr>';
						str+='<tr>';
						str+='<th>MAIN EVENT</th>';
						if(myresult[k].eventTypeStr != 'Visits'){
						str+='<th>INIVITED</th>';
						}
						str+='<th>SUB EVENT</th>';
						str+='<th>ATTENDED</th>';
						if(myresult[k].eventTypeStr == 'Visitor Event')
							str+='';
						else
							str+='<th>NOT ATTENDED</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					var results = myresult[k].knownList;
					if(results !=null && results.length>0){
					for(var i in results){
							var subLength;
							if(results[i].knownList !=null){
								subLength=results[i].knownList.length;
							}					
							if(results[i].knownList !=null){
								var subLength = results[i].knownList.length;
								for(var j in results[i].knownList){
									str+='<tr>';
									if(j==0){											
											str+='<td style="border-bottom:#fff;">'+results[i].name+'</td>';
									}else{
										str+='<td style="border:#fff;">  </td>';
									}
									if(results[i].knownList[j].eventTypeId != 2 && j==0){
										str+='<td style="text-align:center;" rowspan='+subLength+'>'+results[i].knownList[j].invitationCount+'</td>';
									}
									str+='<td>'+results[i].knownList[j].name+'</td>';
									
									//str+='<td>'+results[i].knownList[j].total+' <i class="glyphicon glyphicon-eye-open attedenceCls pull-right" title="Click here to get Attendance Details" style="cursor:pointer" attr_event_name='+results[i].knownList[j].name+' attr_event_id="'+results[i].knownList[j].id+'"></i></td>';
									if(results[i].knownList[j].total != null && results[i].knownList[j].total > 0)
										str+='<td><span class="attedenceCls" attr_event_name='+results[i].knownList[j].name+' attr_event_id="'+results[i].knownList[j].id+'" style="text-align:center;"><a style="cursor:pointer">'+results[i].knownList[j].total+' Day(s) </a></td>';
									else
										str+='<td style="text-align:center;"> - </td>';
									if(results[i].knownList[j].eventTypeId != 2){
										if(results[i].knownList[j].absentCount > 0){
										str+='<td style="text-align:center;position:relative;">'+results[i].knownList[j].absentCount+'  ';
										if(results[i].knownList[j].casteName != null && results[i].knownList[j].casteName != ""){
											str+='<span>  <i class="glyphicon glyphicon-info-sign reasonCls" title="Click here for the reason." data-toggle="tooltip" data-placement="top" style="cursor:pointer;"></i></span>';
											str+='<div class="rasonShowCls arrow_box4" style="display:none">'+results[i].knownList[j].casteName+'</div>';
										}else{
											str+='<div class="rasonShowCls arrow_box4" style="display:none">Reason Not Given</div>';
										}
										str+='</td>';
											
										}else{
										str+='<td style="text-align:center;">'+results[i].knownList[j].absentCount+'</td>';
										}
									}else
										str+='<td style="text-align:center;"> - </td>';
									 str+='</tr>';
								}
							}
							else{
								str+='</tbody>';
								str+='</table>';
								$("#participationTableDivId").html("Data Not Available.");
								return;
							}	
						}
					str+='</tbody>';
				    str+='</table>';	
				    str+='</div>';	
				   //$("#participationTableDivId").html(str);
				}else{
					str+='</tbody>';
				    str+='</table>';	
				    str+='</div>';	
					//$("#participationTableDivId").html("Data Not Available.");
					}					
				}
				 $("#participationTableDivId").html(str);	
				   //$('[data-toggle="tooltip"]').tooltip()

				}else{
					$("#eventParticipationCollapseDivId").hide();
					//$("#participationTableMainDivId").hide();
				}
				
			});
		}
		$(document).on("click",".reasonCls",function(e){
			e.stopPropagation();
			$(".rasonShowCls").hide();
			$(this).closest("td").find(".rasonShowCls").toggle();
		})
		$(document).on("click",function(){
			$(".rasonShowCls").hide();
		})
		$(document).on("click",".attedenceCls",function(){
			$(".eventAttendanceModalId").modal("show");
			$("#eventAttendanceInfoBodyId").html('');
			$("#dataLoadingsImgForEventAttendanceInfoId").show();
			var eventId = $(this).attr("attr_event_id");
			var eventName = $(this).attr("attr_event_name");
			var localCadreId=globalCadreId;
			$("#eventAttendanceModalHeadingId").html(eventName+" Event Attendance Details")
			//8341157
			var jsobj={
				cadreId:localCadreId,
				eventId : eventId
			}
			$.ajax({
				type:'GET',
				 url: 'getEventAttendanceOfCadreAction.action',
				 data : {task:JSON.stringify(jsobj)} ,
			}).done(function(result){
				if(result != null){
					var str='';
					str+='<div class="table-responsive">';
					str+='<table class="table m_0 table-bordered" id="eventAttendanceInfoTableId">';
					str+='<thead>';
						str+='<th style="text-align:center;">S.NO</th>';
						str+='<th style="text-align:center;">DATE</th>';
						str+='<th style="text-align:center;">COUNT</th>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result){
						var count = 0;
						if(result[i].subList != null)
							count = result[i].subList.length;
						str+='<tr>';
							str+='<td style="text-align:center;">'+(parseInt(i)+1)+'</td>';
							str+='<td style="text-align:center;">'+result[i].name+'</td>';
							if(result[i].count > 0){
								str+='<td class="dayWiseAttendedClass" style="text-align:center;" attr_divId="dayWiseAttendedDiv'+i+'"><a style="cursor:pointer">'+count+'</a>';
								str+='<ul id="dayWiseAttendedDiv'+i+'" style="display:none">';
								for(var j in result[i].subList)
									str+='<li>'+result[i].subList[j]+'</li>';
								str+='</ul>';
								str+='</td>';
							}
							else
								str+='<td style="text-align:center;"> - </td>'; 
						str+='</tr>';
					}
					str+='</tbody>';
					/*str+='<thead>';
						str+='<th style="text-align:center;"></th>';
					for(var i in result)
						str+='<th style="text-align:center;">Day - '+(parseInt(i)+1)+' ('+result[i].name+') </th>';
					str+='</thead>';
					str+='<tbody>';
					str+='<tr>';
						str+='<td style="text-align:center;">Attended Time</td>';
					for(var i in result){
						if(result[i].count > 0)
							str+='<td style="text-align:center;">'+result[i].dateStr+'</td>';
						else
							str+='<td style="text-align:center;"> - </td>'; 
				}
				str+='</tr>';
				str+='</tbody>';*/
				str+='</table>';
				str+='</div>';
				$("#dataLoadingsImgForEventAttendanceInfoId").hide();
				$("#eventAttendanceInfoBodyId").html(str);
				$("#eventAttendanceInfoTableId").dataTable({
					"iDisplayLength": 15,
					"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
				 }); 
				}
				$("#dataLoadingsImgForEventAttendanceInfoId").hide();
			});
		});
		
		$(document).on("click",".dayWiseAttendedClass",function(){
			var divId = $(this).attr("attr_divId");
			$("#"+divId).show();
		});
		
		function familyMembersSurveyDetails(votercardNo)
		{	
			$("#familySurveyDataLoadoing").show();
			$(".arrow_box3").hide();
			if(participatedConstituencyType == '')
			participatedConstituencyType = null;
			$('.familySurveyDetailsCls').html("");
			if(participatedConstituencyId == null )
				participatedConstituencyId=0;
			var jsObj={
				cadreId:0,
				surveyId:0,
				searchTypeStr: "All",
				boothId: 0,
				isPriority: "false",
				voterCardNo:votercardNo,
				constituencyId: participatedConstituencyId,
				constiTypeStr: participatedConstituencyType
			}
			
			$.ajax({
				type:'GET',
				 url: 'getTdpCadreSurveyDetailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				//console.log(result);
				$("#familySurveyDataLoadoing").hide();
				if(result != null){
					if(result.verifierVOList !=null){
						var str='';
						str='';
						str+='<div class="tab-content m_top20">';
						str+='<div role="tabpanel" class="tab-pane active" id="area">';
						str+='<div class="panel-group m_0" id="accordion1" role="tablist" aria-multiselectable="true">';
							for(var i in result.verifierVOList){
								str+='<div class="panel panel-default m_0">';
								str+='<div class="panel-heading bg_f9 innerDivFamilyCls" attr_survy_divId="familySurveyTable'+i+'" role="tab" id="heading'+i+'">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion1" onclick="getTdpCadreFamilySurveyDetails('+globalCadreId+','+result.verifierVOList[i].id+',\'null\',\'NotAll\',\'familySurveyTable'+i+'\',\'true\',\''+votercardNo+'\',\''+result.verifierVOList[i].isSampleVerified+'\');" aria-expanded="true" aria-controls="" style="cursor:pointer;"> ';
								str+='<h4 class="panel-title text-bold">';
								str+=''+result.verifierVOList[i].name+'';
								
								if(result.verifierVOList[i].isSampleVerified != null && result.verifierVOList[i].isSampleVerified =='false'){
									str+='   - <b>Verification Status :</b> <span style="color:red;"> Not Verified </span> ';
								}else if(result.verifierVOList[i].isSampleVerified != null  && result.verifierVOList[i].isSampleVerified =='true'){
									str+='   - <b>Verification Status :</b> <span style="color:green;"> '+result.verifierVOList[i].isSampleVerified+'.</span> ';
								}else if(result.verifierVOList[i].isSampleVerified == "null"){
									str+='   - <b>Verification Status :</b>  <span style="color:red;"> Not Verified </span> ';
								}else if(result.verifierVOList[i].isSampleVerified == "Invalid Mobile Number" || result.verifierVOList[i].isSampleVerified == "Not Reachable" || result.verifierVOList[i].isSampleVerified == "Valid" || result.verifierVOList[i].isSampleVerified == "Partially Valid" || result.verifierVOList[i].isSampleVerified == "InValid" || result.verifierVOList[i].isSampleVerified == "Invalid Respondent" || result.verifierVOList[i].isSampleVerified == "Switched Off" || result.verifierVOList[i].isSampleVerified == "Call Not Lifted" ){
									str+='   - <b>Verification Status :</b>  <span style="color:Green;"> '+result.verifierVOList[i].isSampleVerified+'</span> ';
								}
								else{
									str+='   - <b>Verification Status :</b>  <span style="color:red;"> Not Verified </span> ';
								}
								
								str+='<span class="pull-right"><i class="glyphicon glyphicon-triangle-top topsurveyTable" id="topsurveyTable'+i+'" style=""></i><i class="glyphicon glyphicon-triangle-bottom bottomsurveyTable" id="bottomsurveyTable'+i+'" style="display:none;"></i></span>';
								str+='</h4> </a><div style="offset4"><center><img id="familyAjax'+result.verifierVOList[i].id+'" src="images/icons/survey-details.gif" style="display:none;width:250px;height:200px;"/></center></div>';
								str+='</div>';
								str+='<div id="familySurveyTable'+i+'" class="panel-collapse collapse in allSurveyDtlsCls hideSurveyCls " role="" aria-labelledby="" style="display:none;">';
								str+='<div class="panel-body table-responsive">';										
								str+='</div>';
								str+='</div>';
								str+='</div>';
								str+='</div>';

							}
					str+='</div>';
					str+='</div> ';                     
					$('.familySurveyDetailsCls').html(str);
				 }
				}
			});
		}
		function getTdpCadreFamilySurveyDetails(globalCadreId,surveyId,indexId,searchTypeStr,divId,isPriority,voterCardNo,isVerified){
		$("#"+divId+"").html("");
		if($("#"+divId).hasClass("showSurveyCls")){
			return;
		}
		if(participatedConstituencyType == '')
		participatedConstituencyType = null; 
		var temp="familyAjax"+surveyId+"";
			$("#"+temp).show();
		var localCadreId=globalCadreId;
		var surveyId=surveyId;
		var indexId=indexId;
		if(participatedConstituencyId == null )
			participatedConstituencyId=0;
		var jsObj={
			cadreId:localCadreId,
			surveyId:surveyId,
			searchTypeStr: searchTypeStr,
			boothId: $('#cadreBoothId').val(),
			isPriority: "false",
			voterCardNo:voterCardNo,
			constituencyId: participatedConstituencyId,
			constiTypeStr: participatedConstituencyType
		}
		
		$.ajax({
			type:'GET',
			 url: 'getTdpCadreSurveyDetailsAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			$("#"+temp).hide();
			if(result != null){
			var str='';
				if(result.verifierVOList !=null){	
					if(result.verifierVOList != null && result.verifierVOList.length>0)
					{			
						str+='<div class="panel-body">';
						/*if(isPriority == 'true')
							{
								str+='<div class="pull-right tooltipClass" style="margin-bottom: 5px;"> <a href="javascript:{getTdpCadreFamilySurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'All\',\''+divId+'\',\'false\');;}" class="btn btn-default btn-xs " style="padding:3px 5px 5px;background-color:#CCC;border-radius:0px;" data-toggle="tooltip" data-placement="bottom" title="View All Questions Response"><i class="glyphicon glyphicon-list"></i></a> </div>';
							}*/
						str+='<div class="table-responsive">';
						str+='<table class="table m_0 table-bordered">';
						str+='<thead>';
							str+='<th style="text-align:center;">Question</th>';
							str+='<th style="text-align:center;">Answer</th>';
						str+='</thead>';
						str+='<tbody>';
						
						for(var i in result.verifierVOList[0].verifierVOList){
							if(result.verifierVOList[0].verifierVOList[i].option != null && result.verifierVOList[0].verifierVOList[i].option.length>0){
								str+='<tr>';
									str+='<td>'+result.verifierVOList[0].verifierVOList[i].name+'</td>';
									
									if(isVerified != null && isVerified =="true"){
										if(result.verifierVOList[0].verifierVOList[i].isSampleVerified != null && result.verifierVOList[0].verifierVOList[i].isSampleVerified =='false'){
											str+='<td>'+result.verifierVOList[0].verifierVOList[i].option+'';
										}else{
											str+='<td style="background-color:lightgreen;">'+result.verifierVOList[0].verifierVOList[i].option+'';
										}
									}
									else if(isVerified != null && isVerified =="false"){
											str+='<td>'+result.verifierVOList[0].verifierVOList[i].option+'';
									}
									else
										str+='<td>'+result.verifierVOList[0].verifierVOList[i].option+'';
									str+='</td>';
									
								str+='</tr>';
								}						
						}
						str+='</tbody>';
						str+='</table>';
						str+='</div>';
					}				
					$("#"+divId+"").show();		
					$("#"+divId+"").html(str);	
				}
			}
		});
		
	}
	var participatedSurveysArr = new Array();
	var isparticipatedSelected = '';
	function getTdpCadreSurveyDetails(globalCadreId,surveyId,indexId,searchTypeStr,divId,isPriority){

		if($("#"+divId).hasClass("showSurvey")){
			if(isPriority=="true"){
			return;
			}
		}
		if(participatedConstituencyType == '')
		participatedConstituencyType = null;
	var temp="ajax"+surveyId+"";
			$("#"+temp).show();
			var localCadreId=globalCadreId;
			var surveyId=surveyId;
			var indexId=indexId;
			if(surveyId != null && surveyId>0)
			{
				$('.topsurveyTable').show();
				$('.bottomsurveyTable').hide();
				
				
				$('#top'+divId+'').hide();								
				$('#bottom'+divId+'').show();				
			}
			
			if(isPriority =='false')
				$('#'+divId+'').html('<div style="offset4"><img id="ajaxsurveyTable" src="images/icons/survey-details.gif" style="width:250px;height:200px;margin-left:300px;"/></div>');
			
			$('.allSurveyDtlsCls').hide();
			$("#img"+divId+"").show();
			if(surveyId !=0 && localCadreId !=0){
				$("#dataLoadingsImg").show();
			}
			if(surveyId>0){
				searchTypeStr = "NotAll";
			}
			else{
				$('#surveyDataLoadoing').show();
			}
				
			$('#list1').click(function(){
				$('#list2').removeClass('li_arr');
				$('#list2').removeClass('active');
				$('#list1').addClass('active');
				$('#list1').addClass('li_arr');
				$('#list3').removeClass('active');
				$('#list3').removeClass('li_arr');
				isparticipatedSelected = '';
			});
			$('#list2').click(function(){
				$('#list1').removeClass('li_arr');
				$('#list1').removeClass('active');
				$('#list2').addClass('active');
				$('#list2').addClass('li_arr');
				$('#list3').removeClass('active');
				$('#list3').removeClass('li_arr');
				isparticipatedSelected = 'false';
			});
			$('#list3').click(function(){
				$('#list2').removeClass('li_arr');
				$('#list2').removeClass('active');
				$('#list1').removeClass('active');
				$('#list1').removeClass('li_arr');
				$('#list3').addClass('active');
				$('#list3').addClass('li_arr');
				isparticipatedSelected = '';
			});
			var voterCardNo = 'false';
			//console.log("isparticipatedSelected "+isparticipatedSelected)
			if(participatedSurveysArr != null && participatedSurveysArr.length>0)
			{
				if(isparticipatedSelected == 'false')
				{
					voterCardNo = "notParticipated";
				}
				else
				{					
					for(var i in participatedSurveysArr)
					{
						var id = participatedSurveysArr[i].id;
						if(surveyId == id )
						{
							voterCardNo = "participated";
						}
					}
				}
				
			}
			if(participatedConstituencyId == null )
				participatedConstituencyId=0;
			var jsObj={
				cadreId:localCadreId,
				surveyId:surveyId,
				searchTypeStr: searchTypeStr,
				boothId: $('#cadreBoothId').val(),
				isPriority: isPriority,
				voterCardNo:voterCardNo,
				constituencyId: participatedConstituencyId,
				constiTypeStr: participatedConstituencyType
			}
			$('#'+divId+'').show();
			$.ajax({
				type:'GET',
				 url: 'getTdpCadreSurveyDetailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			$('#surveyDataLoadoing').hide();
			if(result !=null){
				$("#"+temp).hide();
				$("#surveyDetailsMainDivId").show();
				$("#img"+divId+"").hide();
				if(result.verifierVOList !=null){
				if(surveyId ==0 && localCadreId !=0){
					var str='';
					if(searchTypeStr == 'NotAll')
					{
						participatedSurveysArr =[];
						
						str+='<ul class="nav nav-tabs tab-list display-style" role="tablist">';
						if(result.count != null && result.count >0)
						{
							str+='<li class="active li_arr" style="margin-top: 0px;padding:0px;" id="list1"><a href="#participated" onclick="getTdpCadreSurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'NotAll\',\'\',\'true\');" class="text-bold" data-toggle="tab" style="cursor:pointer;" >CANDIDATE PARTICIPATED SURVEYS&nbsp;&nbsp;&nbsp;&nbsp;'+result.count+'</a></li>';
							
							str+='<li  style="margin-top: 0px;padding:0px; left: 10px;" id="list3" ><a href="#participated" onclick="getCandidateAndConstituencySurveyResult();">SURVEYS ON CANDIDATE &nbsp;&nbsp;&nbsp;&nbsp;'+candiConstiSurveyCount+'</a></li>';
							
							str+='<li  style="margin-top: 0px;padding:0px; left: 20px;" id="list2" ><a href="#area" onclick="getTdpCadreSurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'All\',\'\',\'false\');" class="text-bold" data-toggle="tab"  style="cursor:pointer;">SURVEYS IN CANDIDATE AREA&nbsp;&nbsp;&nbsp;&nbsp;'+result.totalCount+'</a></li>';
							
							//getCandidateAndConstituencySurveyResult();
						}
						else{
							str+='<li style="padding:10px 15px;" >CANDIDATE PARTICIPATED SURVEYS&nbsp;&nbsp;&nbsp;&nbsp;'+result.count+'</li>';
							
							if(candiConstiSurveyCount != null && candiConstiSurveyCount >0){
								str+='<li class="active li_arr" style="margin-top:10px;padding:0px; left: 10px;" id="list3"><a href="#participated" onclick="getCandidateAndConstituencySurveyResult();">SURVEYS ON CANDIDATE &nbsp;&nbsp;&nbsp;&nbsp;'+candiConstiSurveyCount+'</a></li>';
							
								str+='<li    style="margin-top: 0px;padding:0px; left: 20px;" id="list2" ><a href="#area" onclick="getTdpCadreSurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'All\',\'\',\'false\');" class="text-bold" data-toggle="tab"  style="cursor:pointer;">SURVEYS IN CANDIDATE AREA&nbsp;&nbsp;&nbsp;&nbsp;'+result.totalCount+'</a></li>';
								//getCandidateAndConstituencySurveyResult();
							}
							else if(candiConstiSurveyCount == null || candiConstiSurveyCount == 0){
								str+='<li style="margin-top: 0px;padding:10px; left: 10px;">SURVEYS ON CANDIDATE &nbsp;&nbsp;&nbsp;&nbsp;'+candiConstiSurveyCount+'</li>';
							
								str+='<li  class="active li_arr"  style="margin-top: 0px;padding:0px; left: 20px;" id="list2" ><a href="#area" onclick="getTdpCadreSurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'All\',\'\',\'true\');" class="text-bold" data-toggle="tab"  style="cursor:pointer;">SURVEYS IN CANDIDATE AREA&nbsp;&nbsp;&nbsp;&nbsp;'+result.totalCount+'</a></li>';
								
								//getTdpCadreSurveyDetails(globalCadreId,surveyId,'null','All','','true');
							}
							else{
								str+='<li class="" style="margin-top: 0px;padding:10px; left: 10px;" id="list3"><a href="#participated" onclick="getCandidateAndConstituencySurveyResult();">SURVEYS ON CANDIDATE &nbsp;&nbsp;&nbsp;&nbsp;'+candiConstiSurveyCount+'</a></li>';
							
								str+='<li    class="active li_arr" style="margin-top: 0px;padding:0px; left: 20px;" id="list2" ><a href="#area" onclick="getTdpCadreSurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'All\',\'\',\'false\');" class="text-bold" data-toggle="tab"  style="cursor:pointer;">SURVEYS IN CANDIDATE AREA&nbsp;&nbsp;&nbsp;&nbsp;'+result.totalCount+'</a></li>';
								//getTdpCadreSurveyDetails(globalCadreId,surveyId,'null','All','','true');
							}
						}
						str+='</ul>';
						$('.surveyDetailssCls').html(str);
					}
					
					//console.log("candiConstiSurveyCount : "+candiConstiSurveyCount);
					//console.log("result.verifierVOList.length : "+result.verifierVOList.length);
					//console.log("isPriority : "+isPriority);
					if(isPriority == 'false' || (candiConstiSurveyCount == null || candiConstiSurveyCount == 0)){
						isPriority = 'true';
					str='';
					str+='<div class="tab-content m_top20">';
					str+='<div role="tabpanel" class="tab-pane active" id="area">';
					str+='<div class="panel-group m_0" id="accordion1" role="tablist" aria-multiselectable="true">';
					
					
							for(var i in result.verifierVOList){
								if(result.isVerified != null && result.isVerified =='true')
								{
									var obj = {
										id:result.verifierVOList[i].id
									};
									participatedSurveysArr.push(obj);
								}
								str+='<div class="panel panel-default m_0">';
								
								str+='<div class="panel-heading bg_f9 innerDiv" role="tab" id="heading'+i+'" attr_survy_divId="surveyTable'+i+'">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion1" onclick="tableshidesandShow(\'surveyTable'+i+'\','+i+');" aria-expanded="true" aria-controls="" style="cursor:pointer;"> ';
								str+='<h4 class="panel-title text-bold">';
								str+=''+result.verifierVOList[i].name+'';
								if(result.count != null && result.count >0)
								{
									if(result.verifierVOList[i].isSampleVerified != null && result.verifierVOList[i].isSampleVerified.length>0)
										str+=' - <b>Verification Status :</b> <span style="color:Green;"> '+result.verifierVOList[i].isSampleVerified+'  </span>';
									else
										str+='  - <b>Verification Status :</b> <span style="color:red;"> Not Verified </span>';
								}
								//sris
								str+='<span class="pull-right"><i class="glyphicon glyphicon-triangle-top topsurveyTable" id="topsurveyTable'+i+'" style=""></i><i class="glyphicon glyphicon-triangle-bottom bottomsurveyTable topsurveyTable'+i+'" id="topsurveyTable'+i+'" style="display:none;"></i></span>';
								str+='</h4> </a><div style="offset4"><img id="ajaxsurveyTable'+i+'" src="images/icons/survey-details.gif" style="display:none;width:250px;height:200px;margin-left:300px;"/></div>';
								
								str+='</div>';
																
									str+='<div id="surveyTable'+i+'" class="panel-collapse collapse in allSurveyDtlsCls table-responsive" role="" aria-labelledby="" style="display:none;">';
									str+='<img id="ajaxsurveyTable'+i+'" src="images/icons/survey-details.gif" style="width:250px;height:200px;margin-left:300px;"/>';
									str+='<div class="panel-body">';										
									str+='</div>';
									str+='</div>';
									
										getTdpCadreSurveyDetails(globalCadreId,result.verifierVOList[i].id,indexId,searchTypeStr,'surveyTable'+i+'',isPriority);
									str+='</div>';
						str+='</div>';

							}

					
					str+='</div>';
					str+='</div> ';                     
					str+='</div>';
					
					$('.surveyDetailsCls').html(str);
					}
					//console.log(participatedSurveysArr);
				}
				else if(surveyId !=0 ){
					buildingSurveyQuestionsDetails(result,surveyId,indexId,divId,isPriority);
				}
			 }		
			}else{
				$("#"+temp).hide();
				if(surveyId ==0 && localCadreId !=0){
					$('.surveyDetailsCls').html('<div>Data Not Available.</div>');
				}
				else if(surveyId !=0 && surveyId !=0 ){
					$("#collapse"+surveyId+''+indexId).html('<div>Data Not Available.</div>');	
				}
				$("#surveyDetailsMainDivId").hide();
				
				 $("#"+divId+"").show();		
				$("#"+divId+"").html("<div style='text-align:center;'>Data Not Available.</div>");	
			}
		});
			
	}
	
	function buildingSurveyQuestionsDetails(results,surveyId,indexId,divId,isPriority){
			$("#dataLoadingsImg").hide();
			var str='';
				if(results.verifierVOList !=null){					
					str+='<div class="panel-body">';
					if(isPriority == 'true')
					{
						str+='<div class="pull-right tooltipClass" style="margin-bottom: 5px;"> <a href="javascript:{getTdpCadreSurveyDetails('+globalCadreId+','+surveyId+',\'null\',\'All\',\''+divId+'\',\'false\');;}" class="btn btn-default btn-xs " style="padding:3px 5px 5px;background-color:#CCC;border-radius:0px;" data-toggle="tooltip" data-placement="bottom" title="View All Questions Response"><i class="glyphicon glyphicon-list"></i></a> </div>';
					}
						str+='<div class="table-responsive">';
						str+='<table class="table m_0 table-bordered">';
							/*str+='<thead>';
								str+='<th style="text-align:center;">Question</th>';
								str+='<th style="text-align:center;">Answer</th>';
							str+='</thead>';*/
							str+='<tbody>';
							for(var i in results.verifierVOList){
								str+='<tr>';
									str+='<td>'+results.verifierVOList[i].name+'</td>';
									str+='<td>'+results.verifierVOList[i].option+'</td>'; 
								str+='</tr>';
								if(results.verifierVOList[i].verifierVOList != null && results.verifierVOList[i].verifierVOList.length>0){
									if(results.verifierVOList[i].verifierVOList[0].verifierVOList != null && results.verifierVOList[i].verifierVOList[0].verifierVOList.length>0){
										str+='<tr>';
											str+='<td colspan="2">';
											str+='<div class="table-responsive" style="width:1059px;overflow-y:auto">';
											str+='<table class="table table-bordered">';
												str+='<thead >';
													str+='<th style="text-align:center;background-color:lightgrey;width:60px;">';
														str+=' Location ';
													str+='</th>';
													for(var k in results.verifierVOList[i].verifierVOList[0].verifierVOList)
													{	
														str+='<th style="background-color:lightgrey;">';
														str+=''+results.verifierVOList[i].verifierVOList[0].verifierVOList[k].option+'';
														str+='</th>';
													}
												str+='</thead>';
												str+='<tbody>';
													for(var k in results.verifierVOList[i].verifierVOList)
													{	
														str+='<tr>';
														str+='<th style="background-color:lightgrey;">';
														str+=''+results.verifierVOList[i].verifierVOList[k].name+'';
														str+='</th>';
														for(var l in results.verifierVOList[i].verifierVOList[k].verifierVOList)
														{
															var perc = results.verifierVOList[i].verifierVOList[k].verifierVOList[l].percentage;
															perc = parseFloat(perc).toFixed(2);
															str+='<td>'+perc+'</td>';
														}
														str+='</tr>';
													}
												str+='</tbody>';
												str+='</table>';
												str+='</div>'; 
											str+='</td>'; 
										str+='</tr>';
									}
								}
							}
					str+='</tbody>';
					str+='</table>';
					str+='</div>';
					str+='</div>';
				}else{
					str+='<div>"Data Not Available."</div>';
				}
				//$("#"+divId+"").show();		
				$("#"+divId+"").html(str);		
		}
		function getCandidateElectDetatails(cadreId){
			var localCadreId=cadreId;
			var jsObj={
				cadreId:localCadreId,
			}
			$.ajax({
				type:'GET',
				 url: 'getCandidateElectDetatailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				var str='';
				if(result !=null && result !=''){
					$("#electionProfileMainDivId").show();
					str+='<div>';
						for(var i in result){
								str+='<ul class="wl-sub-details" style="margin-bottom:0px;">';
								str+='<li onmouseover="this.style.color=\'#06ABEA\';" onmouseout="this.style.color=\'#333333\';" style="cursor:pointer;text-align:left;" class="eachParticipationListCls" attr_constId='+result[i].constituencyId+' attr_election_type='+result[i].electionType+' attr_election_year='+result[i].electionYear+'>';
								str+='<strong>';
								if(result[i].status ==false){
									str+='<span style="color:red">Lost</span> ';
								}  
								if(result[i].status ==true){
									str+='<span style="color:green">Won</span> ';
								}
								str+='in '+result[i].electionYear+' </strong> '+result[i].electionType+' Election with <strong>'+result[i].votesPercentage+'</strong> of votes gain for <strong>'+result[i].partyName+'</strong> party in <strong>'+result[i].constituencyName+'</strong> constituency</li>';
								str+='</ul>';    
						}
						/* for(var i in result){
							if(result[i].status ==false){
								str+='<ul class="wl-sub-details">';
								str+='<li onmouseover="this.style.color=\'#F13144\';" onmouseout="this.style.color=\'#333333\';" style="cursor:pointer;text-align:left;" class="eachParticipationListCls" attr_constId='+result[i].constituencyId+' attr_election_type='+result[i].electionType+' attr_election_year='+result[i].electionYear+'>';
								str+='<strong><span style="color:red">Lost</span> in '+result[i].electionYear+' </strong> '+result[i].electionType+' Election with <strong>'+result[i].votesPercentage+'</strong> of votes gain for <strong>'+result[i].partyName+'</strong> party in <strong>'+result[i].constituencyName+'</strong> constituency</li>';
								str+='</ul>';
							}
						} */
					str+='</div>';
					$("#electionProfileDivId").html(str);
				}
				 else{
					$("#electionProfileMainDivId").hide();
				}
			});
		}
	function getTotalMemberShipRegistrationsInCadreLocation(yearId,publicationId){
		
	if(yearId == 3){
		$("#memberShipCountDiv").html('<center><img alt="Processing Image" src="images/icons/loading.gif"></center>');
	}else{
		$("#memberShipCountDiv1").html('<center><img alt="Processing Image" src="images/icons/loading.gif"></center>');
	}
	var boothId = "0";
			boothId=$('#cadreBoothId').val();
			var pcId=0;
			//pcId:participatedConstituencyId,pcType:participatedConstituencyType
			if(participatedConstituencyId != null && participatedConstituencyId > 0){
				pcId = participatedConstituencyId;
			}else{
				pcId = participatedParliamentId;
			}
			var pcType = participatedConstituencyType;
			//pcType="Assembly";
			  $.ajax({
					type : "POST",
					url  : "getTotalMemberShipRegsInCadreLocationAction.action",
					data : {tdpCadreId:globalCadreId,pcId:pcId,pcType:pcType,yearId:yearId,publicationId:publicationId,boothId:boothId}
				  }).done(function(result){
					if(result != null){
						  buildTotalMemberShipRegInCadreLocation(result,pcType,yearId);
					}
				  else{
					 if(yearId == 3)
						$("#memberShipCountDiv").html('No Data Available.');
					else
						$("#memberShipCountDiv1").html('No Data Available.');
				  }
				});
		}
function buildTotalMemberShipRegInCadreLocation(result,pcType,yearId){
	var str = '';
		str += '<ul class="performanceGraph">';
	if(pcType !=null && pcType !="" && pcType !=undefined){
		if(pcType == "Assembly"){
			str += '<li>';
			str += '<div class="fulCircleCls'+yearId+'" data-dimension="100%" data-text="'+result.constiPerc+'%" data-percent="'+result.constiPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own AC ('+participatedConstName.toUpperCase()+')"></div>';
			str += '</li>';
		}
		str += '<li>';
		str += '<div class="fulCircleCls'+yearId+'" data-dimension="100%" data-text="'+result.parConsPerc+'%" data-percent="'+result.parConsPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own PC ('+participatedParlName.toUpperCase()+')"></div>';
		str += '</li>';
		
		str += '<li>';
		str += '<div class="fulCircleCls'+yearId+'" data-dimension="100%" data-text="'+result.districtPerc+'%" data-percent="'+result.districtPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own District ('+participatedDistName.toUpperCase()+')"></div>';
		str += '</li>';
	}
	else{
		if(globalVoterCardNo != ""){
			str += '<li>';
				str += '<div class="fulCircleCls'+yearId+'" data-dimension="100%" data-text="'+result.boothPerc+'%" data-percent="'+result.boothPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Booth"></div>';
			str += '</li>';
		}else{
			str += '<li>';
				str += '<div class="fulCircleCls'+yearId+'" data-dimension="100%" data-text="0" data-percent="0" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Booth (No Voter ID)"></div>';
			str += '</li>';
		}
		
		if(result.cadreLocation =="Mandal")
		{
			str += '<li>';
			str += '<div class="fulCircleCls'+yearId+'" data-dimension="100%" data-text="'+result.panchPerc+'%" data-percent="'+result.panchPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Panchayat ('+globalPancName.toUpperCase()+')"></div>';
			str += '</li>';
		 } 
		 
		str += '<li>';
		//console.log(globalTehsName);
		if(globalTehsName != null && globalTehsName.trim().length>0){
			str += '<div class="fulCircleCls'+yearId+'" data-dimension="100%" data-text="'+result.mandalPerc+'%" data-percent="'+result.mandalPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Man/Mun ('+globalTehsName.toUpperCase()+')"></div>';
		}
		else  if(result.mandalNameStr != null && result.mandalNameStr.length>0){
			str += '<div class="fulCircleCls'+yearId+'" data-dimension="100%" data-text="'+result.mandalPerc+'%" data-percent="'+result.mandalPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Man/Mun ('+result.mandalNameStr.toUpperCase()+')"></div>';
			$("#mandalId").html(result.mandalNameStr.toUpperCase());
		}
	
		//str += '<div class="fulCircleCls" data-dimension="100%" data-text="'+result.mandalPerc+'%" data-percent="'+result.mandalPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Man/Mun ('+globalTehsName.toUpperCase()+')"></div>';
		str += '</li>';
		
		str += '<li>';
		str += '<div class="fulCircleCls'+yearId+'" data-dimension="100%" data-text="'+result.constiPerc+'%" data-percent="'+result.constiPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own AC ('+globalConstName.toUpperCase()+')"></div>';
		str += '</li>';
		
		str += '<li>';
		str += '<div class="fulCircleCls'+yearId+'" data-dimension="100%" data-text="'+result.parConsPerc+'%" data-percent="'+result.parConsPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own PC ('+globalParlName.toUpperCase()+')"></div>';
		str += '</li>';
		
		str += '<li>';
		str += '<div class="fulCircleCls'+yearId+'" data-dimension="100%" data-text="'+result.districtPerc+'%" data-percent="'+result.districtPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own District ('+globalDistName.toUpperCase()+')"></div>';
		str += '</li>';
		
		}
		str += '</ul>';
	if(yearId == 3){
		$("#memberShipCountDiv").html(str);
		 
	}else{
		$("#memberShipCountDiv1").html(str);
		 //$('.fulCircleCls').circliful();
	}
	$('.fulCircleCls'+yearId+'').circliful();
	
	 var windowWidth = $(window).width(); 
	 if( windowWidth > 768)
	 {
		 var graphCount = $(".performanceGraph li").length;
		 var pixelWidth = (100 / graphCount) ;
		 $(".performanceGraph li").css("width",pixelWidth+'%');
	 }
	 if(yearId == 3){
			 if(participatedConstituencyId != null && participatedConstituencyId > 0){
				$("#cadreEnrolementParticepateStatusId").attr("data-original-title","PARTICIPATED CONSTITUENCY");   
				$('#cadreEnrolementParticepateStatusId').tooltip();
			}else{
				$("#cadreEnrolementParticepateStatusId").attr("data-original-title","OWN CONSTITUENCY");
				$('#cadreEnrolementParticepateStatusId').tooltip();
			}
	 }else{
			 if(participatedConstituencyId != null && participatedConstituencyId > 0){
				$("#cadreEnrolementParticepateStatusId1").attr("data-original-title","PARTICIPATED CONSTITUENCY");   
				$('#cadreEnrolementParticepateStatusId1').tooltip();
			}else{
				$("#cadreEnrolementParticepateStatusId1").attr("data-original-title","OWN CONSTITUENCY");
				$('#cadreEnrolementParticepateStatusId1').tooltip();
			} 
	 }
	
	 	 
}

function getCadreFamilyDetailsByCadreId(){
	//$("#familyMembersDiv").html('<img alt="Processing Image" src="./images/icons/search.gif">');
	$("#dataLoadingsImgForFamilyMembers").show();
		  $.ajax({
		  type : "POST",
		  url  : "getCadreFamilyDetailsAction.action",
		  data : {tdpCadreId:globalCadreId}
	  }).done(function(result){
	  $("#dataLoadingsImgForFamilyMembers").hide();
		if(result != null && result.length > 0){
		  buildCadreFamilyDetails(result); 
		}else{
			$("#familyMembersDiv").html("No Data Available");
		}
			getNtrTrustStudentDetailsInstitutionWise("cadre");
	  });
 } 
 
 var familycadreIdsArrayGlobal=[];
 function buildCadreFamilyDetails(result){ 
 //console.log(result.familyMembersList)
	var familyCount = result.length;
	$("#totalFamilymembersCount").html(''+familyCount+'');
	  familyInfoArr=[];
	 var constId = $('#cadreConstituencyId').val();
	 var partNo = $('#cadrePartNo').val();
	 var str = '';
	 
	 str += '<ul>';
	 for(var i in result){
		 if(result[i].tdpCadreId !=null){
			 familycadreIdsArrayGlobal.push(result[i].tdpCadreId);
		 }
		var imgPath;
			 if(result[i].imagePath != null && result[i].imagePath.trim().length > 0){ 
				imgPath = result[i].imagePath;
			 }
			 /*else{
				imgPath = "http://www.mytdp.com/voter_images/"+constId+"/Part"+partNo+"/"+result[i].votercardNo+".jpg"	;
			  }*/
	 var familyObj = {
	 voterId:result[i].votercardNo,
	 membershipId:result[i].membershipNo,
	 name:result[i].name,
	 relation:result[i].relation,
	 relativeName:result[i].relativeName,
	 imagePath:imgPath
	 };
	 familyInfoArr.push(familyObj);
		if(result[i].isRenewal == "true")
		{
			str += '<li style="margin-bottom: 10px; padding-bottom: 10px; border-bottom: 1px solid #eee;background-color:#e1e3ea">';
		}else{
			str += '<li style="margin-bottom: 10px; padding-bottom: 10px; border-bottom: 1px solid #eee;">';	
		}
	 	 
         str += '<div class="media">';
		 var imgPath='';
		 if(result[i].imagePath != null && result[i].imagePath.trim().length > 0){ 
			imgPath = result[i].imagePath;
		 }
		 /*else{
			imgPath="http://www.mytdp.com/voter_images/"+constId+"/Part"+partNo+"/"+result[i].votercardNo+".jpg" ;
		 }*/
		  str += '<div class="media-left font-12" >';
		  str += '<img src="'+imgPath+'" class="img-responsive media-object img-circle"  style="height: 45px;width:45px;border:1px solid #ddd;" >';
		  str += '</div>';
		 /*if(result[i].tdpCadreId != null ){
			 str += '<div class="media-left ">';
			 //str += '<img src="dist/img/family-member.png" class="img-responsive media-object img-circle" alt="profile">';
			// str += '<img src="http://www.mytdp.com/voter_images/'+constId+'/Part'+partNo+'/'+result[i].votercardNo+'.jpg" class="img-responsive media-object img-circle"  style="height: 50px;width:35px;" >';
			str += '<img src="'+imgPath+'" class="img-responsive media-object img-circle"  style="height: 50px;width:35px;" >';
			 str += '</div>';
		 }
		 else
		 {
			 str += '<div class="media-left">';
			// str += '<img src="dist/img/family-member.png" class="img-responsive media-object img-circle" alt="profile">';
			// str += '<img src="http://www.mytdp.com/voter_images/'+constId+'/Part'+partNo+'/'+result[i].votercardNo+'.jpg" class="img-responsive media-object img-circle"  style="height: 50px;width:35px;" >';
			str += '<img src="'+imgPath+'" class="img-responsive media-object img-circle"  style="height: 50px;width:35px;" >';
			 str += '</div>';
		 }*/
		 
/* 		  if(result[i].deletedStatus == "MD"){
			  str += '<div class="media-body" style="background:red;padding:5px;">';
		  }
		  else{
			  str += '<div class="media-body">';
		  } */
         str += '<div class="media-body">';
		  if(result[i].deletedStatus == "MD"){
			  str += '<div class="m_0"><span style="color:red;">'+result[i].name+'</span>';
		  }else{
			  str += '<div class="m_0"> <b>'+result[i].name+'</b>';
		  }
		 if(result[i].tdpCadreId != null )
		str+=' [ <b><a href="cadreDetailsAction.action?cadreId='+result[i].tdpCadreId+'" data-toggle="tooltip" data-placement="right" title="Membership No" class="membershipno-cls">'+result[i].membershipNo+'</a></b> ] ';
		/*if(result[i].publicRepresentativeStr != null){
			str+='[<b>'+result[i].publicRepresentativeStr+'</b>]';
		}*/
		 str += '<span class="pull-right">';
		
		 str += '</span>';
		 str += '<ul class="survey-hover survey-hover'+i+' arrow_box3" style="display:none">';
        
		 if(result[i].education != null && result[i].education.trim().length > 0)
			 str += '<li>Education : <span class="pull-right">'+result[i].education+'</span></li>';
		
         if(result[i].occupation != null && result[i].occupation.trim().length > 0)
			 str += '<li>Occupation : <span class="pull-right">'+result[i].occupation+'</span></li>';
		 
        /* if(result[i].count != null && result[i].count> 0)
			 str += '<li>Participated in Survey : <span class="pull-right" style="cursor:pointer; color:#485EDB;" data-toggle="modal" data-target=".modalForSurvey" onclick = "familyMembersSurveyDetails(\''+result[i].votercardNo+'\')">'+result[i].count+'</span></li>';
		 */
		 str += '</ul>';
         str += '</div>';
         str += '<p class="m_0">Relation : <span class="textTransFormCls">'+result[i].relation+'</span>';
		 if(result[i].relativeName != null && result[i].relativeName.trim().length > 0)
		  str += ' - <span class="textTransFormCls">'+result[i].relativeName.toLowerCase()+'</span></p>';
         str += '<p class="m_0">Age : ';
		 if(result[i].age != null)
		   str +=''+result[i].age+'';
			str+='<ul class="enrolled-mem" id="">';
			//$("#familyMembersDiv").html(result[i].enrollmentYear);
			if(result[i].enrollmentYear != null && result[i].enrollmentYear.trim().length > 0)
			{
				var year = result[i].enrollmentYear.split(",");	
			
				if(year.indexOf("2016") > -1)
					str+='<li class="yes">2016<span></span></li>&nbsp;';
				//else
					//str+='<li class="no">2016<span></span></li>&nbsp;';
				if(year.indexOf("2014") > -1)
					str+='<li class="yes">2014<span></span></li>&nbsp;';
				//else
				//	str+='<li class="no">2014<span></span></li>&nbsp;';
				if(year.indexOf("2012") > -1)
					str+='<li class="yes">2012<span></span></li>&nbsp;';
				//else
				//	str+='<li class="no">2012<span></span></li>&nbsp;';
				if(year.indexOf("2010") > -1)
					str+='<li class="yes">2010<span></span></li>&nbsp;';
				//else
				//	str+='<li class="no">2010<span></span></li>&nbsp;';
			}
			else
			{
				//str+='<li class="no">2016<span></span></li>&nbsp;';
				//str+='<li class="no">2014<span></span></li>&nbsp;';
				//str+='<li class="no">2012<span></span></li>&nbsp;';
				//str+='<li class="no">2010<span></span></li>&nbsp;';
			}
   			str+='</ul>	';
		 str += '';
		if(result[i].publicRepresentativeStr != null)
		{
			str += '<p class="m_0" style="font-weight:bold">Public Repr. : <span class="textTransFormCls" style="color:#2772C0">'+result[i].publicRepresentativeStr+'</span>';
		}
		if(result[i].partyPositionStr != null)
		{
			str += '<p class="m_0" style="font-weight:bold">Party Position: <span class="textTransFormCls" style="color:#2772C0">'+result[i].partyPositionStr+'</span>';
		}
         str += '</p>';
		  if(result[i].count != null && result[i].count> 0){
           //str += '<img class="img-responsive survey-drop" src="img/survey.png" style="cursor:pointer;" id="survey-dropdown" >';
		   //glyphicon-list-alt
		   str += ' <p  style="padding: 2px 5px; border-radius: 3px; background-color: rgba(0, 0, 255, 0.1);" > Participated Surveys :<span class="pull-right" style="cursor:pointer; color:#485EDB;" data-toggle="modal" data-target=".modalForSurvey" onclick = "familyMembersSurveyDetails(\''+result[i].votercardNo+'\')"> <u style="color:green;margin-right: 55px;font-weight:bold;">'+result[i].count+'</u></span></p>';
		 }
		   else
		  //str += '<img class="img-responsive" src="img/survey.png" id="survey-dropdown">';
	  
		 if(result[i].deletedStatus == "MD"){
			 str += '<p class="m_0" style="font-weight:bold">Deleted Reason : <span class="textTransFormCls" style="color:#2772C0">'+result[i].deletedReason+'</span>';
		 }
		 str += '</div>';
         str += '</div>';
		 str +='</div>';
         str += '</li>';
	 }
	 str += '</ul>';
	$("#familyMembersDiv").html(str);
	getMemberComplaints();
	$('.membershipno-cls').tooltip();
	getNtrTrustStudentDetailsInstitutionWise("family");
	benefitSchemesRelatedCalls();
 }
 //Grievance  CANDIDATE//
 
function getTotalComplaintsForCandidate(){
    $("#complaintCountDiv").html('');
	$("#financialDiv").html('');
	$("#complaintsDiv").html('');
	//$("#comaplaintCountInmgMainDivid").html('');
	 $("#candidateRequestAmount").html('');
	$("#candidateApprovedAmount").html('<img src="images/icons/loading.gif" style="width:20px;height:20px;"></img>');
	$("#candidateDeathInsurance").html('<img src="images/icons/loading.gif" style="width:20px;height:20px;"></img>');
	$("#candidateHospitalizationInsurance").html('<img src="images/icons/loading.gif" style="width:20px;height:20px;"></img>');
	var votercardNo = $('#cadreVoterCardNo').val();
	var membershipId = $('#cadreMemberShipId').val();
	var arr =[];

	$("#complaintCountImg").show();

	var obj = {
		"voterId":votercardNo,
		"membershipId" :membershipId,
		"name":"",
		"relation":""
	}

	arr.push(obj);
		
	$.ajax({
				type : "POST",
				//url: "http://mytdp.com/Grievance/WebService/Auth/getCategoryWiseStatusCountForCandidate",
				url: wurl+"/Grievance/WebService/Auth/getCategoryWiseStatusCountForCandidate",
				//url: "http://localhost:8080/Grievance/WebService/Auth/getCategoryWiseStatusCountForCandidate",
				  data: JSON.stringify(arr),
				 contentType: "application/json; charset=utf-8",
				 dataType: "json",
					username: "grievance",
					password: "grievance@!tG"	
				 }).done(function(myresult){
					$("#candidateapprovedDiv").hide();
					$("#candidatedeathDiv").hide();
					$("#candidatehospitalDiv").hide();
					$("#candidaterequestedDiv").hide();
					$("#complaintCountImg").hide();
					if(myresult != null && myresult !=""){
						buildTotalComplaints(myresult,0);
						buildInsuranceTotalComplaints(myresult,0);
					}
				});
	}
var nominatedResult = [];
var nominatedPostResult = [];
function getPDFReportsForNominatedComplaints(){
var membershipId = $('#cadreMemberShipId').val();
	var obj = {
		"membershipId" :membershipId
	}
	$.ajax({
     type: "POST",
     url: "getGrievancePDFReportAction.action",
	 dataType: 'json',
     data: {task:JSON.stringify(obj)}
    })
    .done(function(result){
		nominatedResult = result;
		getNominatedPostReportFiles();
		
		});
	}
function buildNominatedReports()
{
	var str = '';
	var flag = false;
	if((nominatedResult != null && nominatedResult.length > 0)||(nominatedPostResult != null && nominatedPostResult.length > 0))
			{
			flag = true;
			str +='<table id="NominatedreportTableId" class="table table-bordered">';
			str +='<thead>';
			str +='<th>REPORT TYPE</th>';
			//str +='<th>PREFERABLE STATUS</th>';  
			str +='<th>REPORT DATE</th>';
			str +='</thead>';  
			str +='<tbody>';
			
			for(var i in nominatedPostResult) //Nominated Post
				{
				str +='<tr>'; 
				str +='<td><span  title="Click Here To Get Reports Detail" filePath="'+nominatedPostResult[i].reportPath+'" style="cursor:pointer;" data-toggle="modal" data-target="#pdfModelId" class="showPdfCls2" >Nominated Post</span></td>'; 
						/*str +='<td><span  title="Click Here To Get Reports Detail" filePath="'+nominatedResult[i].scanCopyList[j].path+'" style="cursor:pointer;" data-toggle="modal" data-target="#pdfModelId" class="showPdfCls1" id="showPdfId" >N/A</span></td>'; */
						str +='<td><span  title="Click Here To Get Reports Detail" filePath="'+nominatedPostResult[i].reportPath+'" style="cursor:pointer;" data-toggle="modal" data-target="#pdfModelId" class="showPdfCls2" >'+nominatedPostResult[i].insertedTime+'</span></td>'; 
				str +='</tr>'; 
				}
				if(nominatedResult != null && nominatedResult.length > 0)
				{
				for(var i in nominatedResult)//Grievance Nominated Post
				{
				str +='<tr>'; 
				if(nominatedResult[i].scanCopyList != null && nominatedResult[i].scanCopyList.length > 0)
				{
						for(var j in nominatedResult[i].scanCopyList)
						{
						str +='<td><span  title="Click Here To Get Reports Detail" filePath="'+nominatedResult[i].scanCopyList[j].path+'" style="cursor:pointer;" data-toggle="modal" data-target="#pdfModelId" class="showPdfCls1" >'+nominatedResult[i].issueType+' (GRIEVANCE)</span></td>'; 
						/*str +='<td><span  title="Click Here To Get Reports Detail" filePath="'+nominatedResult[i].scanCopyList[j].path+'" style="cursor:pointer;" data-toggle="modal" data-target="#pdfModelId" class="showPdfCls1" id="showPdfId" >N/A</span></td>'; */
						str +='<td><span  title="Click Here To Get Reports Detail" filePath="'+nominatedResult[i].scanCopyList[j].path+'" style="cursor:pointer;" data-toggle="modal" data-target="#pdfModelId" class="showPdfCls1" >'+nominatedResult[i].date+'</span></td>'; 	
					}
				}
					else
					{
							str +='<td><span>'+nominatedResult[i].issueType+' (GRIEVANCE)</span></td>'; 
							//str +='<td><span>N/A</span></td>'; 
							str +='<td><span>'+nominatedResult[i].date+'</span></td>'; 	
					}
				
			str +='</tr>';  	
				}
			}
			
		str +='</tbody>';
	str +='</table>'; 
			}
	if(flag == true)
		{
			$("#profilesInfoId").html('<i class="glyphicon glyphicon-list-alt remove-icon"  data-placement="bottom" style="margin-right: 3px;cursor:pointer;color:green;" id="nominatedreportsId" title="Click Here To Get Profiles Detail" data-toggle="modal" data-target="#profileModelId"></i>');
			$("#profileDetailsId").html(str);
			$("#NominatedreportTableId").dataTable(); 
		}
		else
		{
			$("#profilesInfoId").html('<i class="glyphicon glyphicon-list-alt remove-icon"  data-toggle="tooltip" data-placement="bottom" style="margin-right: 3px;cursor:pointer;color:gray;" id="" title="No Profiles are available" ></i>');
		}
}
	function buildTotalComplaints(result,complaintId)
	{
	var str = '';
	str += '<ul class="list-inline">';
	$("#candidateTotalComplaintsDiv").html(''+result[0].count+'');
	str += '<li style="margin-top:5px">';
	str += '<ul class="display-style pull-right graph-list count-list">';
	for(var i in result[0].subList){
		if(result[0].subList[i].count  > 0){
		var color=getColorCodeByStatus(result[0].subList[i].status);
			str += '<li><span style="background-color:'+color+';height:11px;width:11px;display:inline-block;margin-right:4px"></span><span class="approved-text">'+result[0].subList[i].status.toUpperCase()+'<span class="pull-right">'+result[0].subList[i].count+'</span></span></li>';
		}
	}
	str += '</ul>';
	str += '</li>';
	str += '</ul>';
	$("#complaintCountDiv").html(str);
	var comp1='';
	var totalCounts = 0;
	if(result[0].amountVO != null){
	if(result[0].amountVO.cmRefiedFund == null)
	result[0].amountVO.cmRefiedFund =0;
	if(result[0].amountVO.partyFund == null)
	result[0].amountVO.partyFund =0;
	if(result[0].amountVO.requested > 0){
		$("#candidaterequestedDiv").show();
		$("#candidateRequestAmount").html(''+result[0].amountVO.requested+'/-');
		totalCounts = parseInt(totalCounts)+parseInt(result[0].amountVO.requested);
	}if(result[0].amountVO.approved  >0){
		$("#candidateApprovedAmount").html(''+result[0].amountVO.approved+'/-');
		$("#candidateapprovedDiv").show();
		totalCounts = parseInt(totalCounts)+parseInt(result[0].amountVO.approved);
		}else{
		$("#candidateapprovedDiv").hide();
		}
		$("#candidateBenifitsCountsId").html(result[0].count + result[1].count);
	}
	var comp = '';
	comp += '<ul class="inbox-messages custom-scroll-ins" style="margin-bottom:0px;">';
	for(var j in result[0].voList){
		var color = getColorCodeByStatus(result[0].voList[j].status);
	
		if(result[0].voList[j].complaintId == complaintId){
		comp += '<li style="cursor:pointer;background:'+color+';border-left:4px solid '+color+'"';
		comp += '<p class="m_0"><a href="'+wurl+'/Grievance/homeAction.action?complaintId='+result[0].voList[j].complaintId+'&typeOfIssue=Grievance" target="_blank">C ID - '+result[0].voList[j].complaintId+'</a></p>';
			comp += '<p class="m_0">'+result[0].voList[j].subject+'</p>';
			comp += '<p class="m_0">Status - <span class="textTransFormCls">'+result[0].voList[j].status+'</span></p>';
			if(result[0].voList[j].raisedDate != null)
			 comp += '<p class="m_0">'+result[0].voList[j].raisedDate+'</p>';
			 comp += '</li>';
		}
	 }
   
   for(var j in result[0].voList){
		if(result[0].voList[j].complaintId != complaintId){
		var color = getColorCodeByStatus(result[0].voList[j].status);
			comp += '<li style="background:'+color+';border-left:4px solid '+color+'" ';
		    comp += '<p class="m_0"><a href="'+wurl+'/Grievance/homeAction.action?complaintId='+result[0].voList[j].complaintId+'&typeOfIssue=Grievance" target="_blank">C ID - '+result[0].voList[j].complaintId+'</a></p>';
			comp += '<p class="m_0">'+result[0].voList[j].subject+'</p>';
			comp += '<p class="m_0">Status - <span class="textTransFormCls">'+result[0].voList[j].status+'</span></p>';
			if(result[0].voList[j].raisedDate != null)
			 comp += '<p class="m_0">'+result[0].voList[j].raisedDate+'</p>';
			comp += '</li>';
		}
   }
   
	comp += '</ul>';
	 if(result[0].count == 0)
	$("#complaintCountDiv").html('No Data Available.');
	$("#complaintsDiv").html(comp);
	$(".custom-scroll-ins").mCustomScrollbar();
}
function buildInsuranceTotalComplaints(result,complaintId)
{
	 var hosReq = 0;
	 var deathReq=0;
	 for(var j in result[1].voList){
		if(result[1].voList[j].issueType == 'Hospitalization'){
			hosReq = 1 +hosReq;
		}if(result[1].voList[j].issueType == 'Death'){
			deathReq = 1 +deathReq;
		}
	}
	var str ='';
	str+=''+deathReq+'';
	if(deathReq > 0){
		str+='<ul class="hoverclassul">';
		 for(var j in result[1].voList){
			 if(result[1].voList[j].issueType == 'Death'){
					str+='<li>';
					str+='<p class="m_0" ><a href="'+wurl+'/Grievance/homeAction.action?complaintId='+result[1].voList[j].complaintId+'&typeOfIssue=Insurance" target="_blank">C ID -'+result[1].voList[j].complaintId+'</a></p>';
					str+='<p class="m_0">'+result[1].voList[j].subject+'</p>';
					str+='<p class="m_0">Status - '+result[1].voList[j].status +'</p>';
					str+='<p class="m_0"> '+result[1].voList[j].raisedDate+'</p>';
					str+='</li>';
				}
			}
		str+='</ul>';
		$("#candidateDeathInsurance").html(str);
		$("#candidatedeathDiv").show();
	}
	else{
	$("#candidatedeathDiv").hide();
	}
	var str1 ='';
	str1+=''+hosReq+'';
	if(hosReq > 0){
	str1+='<ul class="hoverclassul">';
		 for(var j in result[1].voList){
			 if(result[1].voList[j].issueType == 'Hospitalization'){
				str1+='<li>';
				str1+='<p class="m_0" ><a href="'+wurl+'/Grievance/homeAction.action?complaintId='+result[1].voList[j].complaintId+'&typeOfIssue=Insurance" target="_blank">C ID -'+result[1].voList[j].complaintId+'</a></p>';
				str1+='<p class="m_0">'+result[1].voList[j].subject+'</p>';
				str1+='<p class="m_0">Status - '+result[1].voList[j].status +'</p>';
				str1+='<p class="m_0"> '+result[1].voList[j].raisedDate+'</p>';
				str1+='</li>';
			}
		}
			str1+='</ul>';
			$("#candidateHospitalizationInsurance").html(str1);
			$("#candidatehospitalDiv").show();
	}
	else{
	$("#candidatehospitalDiv").hide();
	}
}
//end//

var familyInfoArr =new Array();
function getMemberComplaints()
{
  $("#familyDeathInsurance").html('<img src="images/icons/loading.gif" style="width:20px;height:20px;"></img>');
  $("#familyApprovedAmount").html('<img src="images/icons/loading.gif" style="width:20px;height:20px;"></img>');
  $("#familyHospitalizationInsurance").html('<img src="images/icons/loading.gif" style="width:20px;height:20px;"></img>');
  $("#familyRequestAmount").html('');
  $("#familyMemberDiv").html('');
    $.ajax({
			type : "POST",
			url: wurl+"/Grievance/WebService/Auth/getTotalComplaintsForCandidate",
			// url: "http://localhost:8080/Grievance/WebService/Auth/getTotalComplaintsForCandidate",
			  data: JSON.stringify(familyInfoArr),
			 contentType: "application/json; charset=utf-8",
			 dataType: "json",
			username: "grievance",
				 password: "grievance@!tG"	
			 }).done(function(myresult){
				$("#familyMemberImg").hide();
				 $("#familydeathDiv").hide();
				 $("#familyhospitalDiv").hide();
				 $("#familyapprovedDiv").hide();
				 $("#familyrequestedDiv").hide();
				if(myresult != null && myresult.length > 0)
				{
					buildFamilyMemberComplaint(myresult);
					buildInsuranceFamilyMemberComplaint(myresult);
				}
				else{
					$("#familyMemberDiv").html("<div style='text-align:center;padding:10px'>NO DATA AVAILABLE </div>");
				}
			});
}
function buildFamilyMemberComplaint(result,jobj){
	
	var flag = false;
 try{
	 var cnt = 0;
	if(result!=null && result.length>0){
		for(var j in result[0].subList){
			if(globalidentityMembershipNo != result[0].subList[j].familyRefererMembership)
			{
			  if(result[0].subList[j].subList != null && result[0].subList[j].subList.length > 0)
			  {
				  for(var k in result[0].subList[j].subList)
							{
							cnt  = cnt + 1;
					  }
			  }
	       }
	}		  
	var comp = '';
	var totalFamCount = 0;
	if(result[0].amountVO != null){
	if(result[0].amountVO.cmRefiedFund == null)
	result[0].amountVO.cmRefiedFund =0;
	if(result[0].amountVO.partyFund == null)
	result[0].amountVO.partyFund =0;
	
   if(result[0].amountVO.requested > 0 ){
	$("#familyrequestedDiv").show();
	$("#familyRequestAmount").html(''+result[0].amountVO.requested+'/-');
	totalFamCount = parseInt(totalFamCount)+parseInt(result[0].amountVO.requested);
	}
	$("#familyapprovedDiv").show();
	$("#familyApprovedAmount").html(''+result[0].amountVO.approved+'/-');
	totalFamCount = parseInt(totalFamCount)+parseInt(result[0].amountVO.approved);
	}
	else
	{
	$("#familyapprovedDiv").hide();
	}
	$("#familyBenifitsCountsId").html(totalFamCount);
	$("#totalFamilyComplaints").html(''+cnt+'');
	//comp+='</div>';
	//comp+='</div>';
	//comp += '<div class="panel-group m_0" id="accordion155" role="tablist" aria-multiselectable="true">';
	 for(var j in result[0].subList){
	  if(result[0].subList[j].subList != null && result[0].subList[j].subList.length > 0)
			{ 
			flag = true;
			//comp += '<div class="panel panel-default">';
			//comp += '<div class="panel-heading" style="background-color:#FFF" role="tab" id="headingOne155'+j+'">';
			//comp += '<div role="button" style="color:#666" data-toggle="collapse" data-parent="#accordion155" href="#collapseOne155'+j+'" aria-expanded="true" aria-controls="collapseOne155'+j+'">';
			if(globalidentityMembershipNo != result[0].subList[j].familyRefererMembership)
			{
			comp += '<div class="media" style="padding:10px">';
			comp += '<div class="media-left">';
			comp += '<img src="'+result[0].subList[j].image+'" style="height:35px;width:35px;"  class="media-object img-border img-circle">';
			comp += '</div>';
			comp += '<div class="media-body">';
			comp += '<ul class="list-inline">'; 
			comp += '<li style="width:100%">';
			
			if(result[0].subList[j].familyVoter != null)
				comp += '<p class="m_0">Refer Name- '+result[0].subList[j].name+'</p>';
			else
			comp += '<p class="m_0"> Name- '+result[0].subList[j].name+'</p>';
			
			comp += '<p class="m_0">Relation- '+result[0].subList[j].relation+' - '+result[0].subList[j].status+' </p>';
			if(result[0].subList[j].membershipId == null)
			comp += '<p class="m_0">MemberShipID- N/A</p>';
			else
			comp += '<p class="m_0">MemberShipID- <a target="_blank" title="Click here to View '+result[0].subList[j].name+' Cadre Details " href="cadreDetailsAction.action?memberShipId='+result[0].subList[j].membershipId+'"><b>'+result[0].subList[j].membershipId+'</b></p></a>';
			if(result[0].subList[j].familyVoter != null)
			{
				if(result[0].subList[j].familyRefererMembership == null)
				{
					comp += '<p class="m_0">Refer Name- '+result[0].subList[j].name+'</p>';
				}
				else
				{
					
					comp += '<p class="m_0">Cadre- '+result[0].subList[j].familyRefererName+'  MemberShipID- <a target="_blank" title="Click here to View '+result[0].subList[j].familyRefererName+' Cadre Details " href="'+wurl+'/cadreDetailsAction.action?memberShipId='+result[0].subList[j].familyRefererMembership+'"><b>'+result[0].subList[j].familyRefererMembership+'</b></p></a>';
					//'+result[0].subList[j].name+'
				}
				
			}
			
				
			comp += '</li>'; 
			/* comp += '<li>';  
			comp += '<span class="countStyleSpan">'+result[0].subList[j].subList.length+'</span>';
			comp += '</li>'; */
			comp += '</ul>';
			comp += '</div>';
			comp += '<div class="media-right m_top10" >';
			comp += '<span class="countStyleSpan" style="top: 5px;">'+result[0].subList[j].subList.length+'</span>';
			comp += '</div>';
			comp += '</div>';
			//comp += '</div>';
			//comp += '</div>';
			//comp += '<div id="collapseOne155'+j+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne155'+j+'">';
				comp += '<div class="panel-body pad_0">';
				
				comp+='<ul style="margin-bottom:0px;box-shadow:none" class="inbox-messages custom-scroll-ins">';
				for(var k in result[0].subList[j].subList)
				{
		     	var color = getColorCodeByStatus(result[0].subList[j].subList[k].status);
				comp += '<li style="background:'+color+'"';
				
				comp+=' <p class="m_0"><a href="'+wurl+'/Grievance/homeAction.action?complaintId='+result[0].subList[j].subList[k].complaintId+'&typeOfIssue=Grievance" target="_blank">C ID -'+result[0].subList[j].subList[k].complaintId+'</a></p><p class="m_0">'+result[0].subList[j].subList[k].subject+'</p><p class="m_0">Status - <span class="textTransFormCls">'+result[0].subList[j].subList[k].status+'</span></p><p class="m_0">'+result[0].subList[j].subList[k].raisedDate+'</p></li>';
				}
				comp+='</ul>';
			 } 
				comp += '</div>';
			//comp += '</div>';
			comp += '</div>';
		}
	  //comp += '</div>';
	 
      $("#familyMemberDiv").html(comp);
	  if(result[0].count>=7){
	    $("#familyMemberDiv").css("height","760px");
	  }else{
		$("#familyMemberDiv").css("height","auto"); 
	  }
	}
	}
	
	if(!flag)
	{
	 $("#totalFamilyComplaints").html('0');
      $("#familyMemberDiv").html("<div style='text-align:center;padding:10px'>NO DATA AVAILABLE </div>");
	  $("#familyApprovedAmount").html('0/-');
	  
	}
	
 }catch(e){}
 $(".custom-scroll-ins").mCustomScrollbar();
}
function buildInsuranceFamilyMemberComplaint(result)
{
	
	    var hosReq = 0;
		var deathReq=0;
	for(var j in result[1].subList){
	  if(result[1].subList[j].subList != null && result[1].subList[j].subList.length > 0){ 
		for(var k in result[1].subList[j].subList){
					if(result[1].subList[j].subList[k].issueType == 'Hospitalization'){
						hosReq = 1 +hosReq;
					}
				if(result[1].subList[j].subList[k].issueType == 'Death'){
					deathReq = 1 +deathReq;
					}
			}  
		}
	} 
	var str = '';
	str+=''+deathReq+'';
	if(deathReq > 0)
	{
	str+='<ul class="hoverclassul">';
	for(var j in result[1].subList){
	  if(result[1].subList[j].subList != null && result[1].subList[j].subList.length > 0)
			{ 
			for(var k in result[1].subList[j].subList)
				{
				if(result[1].subList[j].subList[k].issueType == 'Death')
				{
					str+='<li>';
					str+='<p class="m_0"><a href="'+wurl+'/Grievance/homeAction.action?complaintId='+result[1].subList[j].subList[k].complaintId+'&typeOfIssue=Insurance" target="_blank">C ID -'+result[1].subList[j].subList[k].complaintId+'</a></p>';
					
					str+='<p class="m_0">'+result[1].subList[j].subList[k].subject+'</p>';
					str+='<p class="m_0">Status - '+result[1].subList[j].subList[k].status +'</p>';
					str+='<p class="m_0"> '+result[1].subList[j].subList[k].raisedDate+'</p>';
					str+='</li>';
			 }
		}
	}
	}
	str+='</ul>';
	$("#familydeathDiv").show();
	$("#familyDeathInsurance").html(str);
	}
	else
	{
	$("#familydeathDiv").hide();
	}
	
	var str1 = '';
	str1+=''+hosReq+'';
	if(hosReq > 0)
	{
	str1+='<ul class="hoverclassul">';
	for(var j in result[1].subList){
	  if(result[1].subList[j].subList != null && result[1].subList[j].subList.length > 0)
			{ 
			for(var k in result[1].subList[j].subList)
				{
				if(result[1].subList[j].subList[k].issueType == 'Hospitalization')
				{
					str1+='<li>';
					str1+='<p class="m_0"><a href="'+wurl+'/Grievance/homeAction.action?complaintId='+result[1].subList[j].subList[k].complaintId+'&typeOfIssue=Insurance" target="_blank">C ID -'+result[1].subList[j].subList[k].complaintId+'</a></p>';
					str1+='<p class="m_0">'+result[1].subList[j].subList[k].subject+'</p>';
					str1+='<p class="m_0">Status - '+result[1].subList[j].subList[k].status +'</p>';
					str1+='<p class="m_0"> '+result[1].subList[j].subList[k].raisedDate+'</p>';
					str1+='</li>';
			 }
		}
	}
	}
	str1+='</ul>';
	$("#familyhospitalDiv").show();
	$("#familyHospitalizationInsurance").html(str1);
	}
	else
	{
	$("#familyhospitalDiv").hide();
	}
}
 function getElectionPerformanceInCadreLocation()
 {
			
	  $(".electionPerformanceDiv").html('<img alt="Processing Image" src="./images/icons/search.gif">');
			$.ajax({
				type : "POST",
				url  : "getElectionPerformanceInCadreLocationAction.action",
				data : {tdpCadreId:globalCadreId}
			}).done(function(result){
				$(".electionPerformanceDiv").html("");
				if(result != null && result.length > 0)
				  buildElectionPerformanceInCadreLocation(result);
			  else
				$(".electionPerformanceDiv").html("No Data Available");
			});
	}

function buildElectionPerformanceInCadreLocation(result)
{
	$(".electionPerformanceDiv").html('');
	
	var str = '';
	for(var i in result)
	{
	
		 if(result[i].mandalPerc == "0"){
		 str += '<div class="panel panel-default">';
		str += '<div class="panel-heading  bg_white" style="" role="tab" id="headingOne'+result[i].year+'">';
		str += '<h4 class="panel-title">';
		str += '<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne'+result[i].year+'" ';
		if(result[i].year == "2014")
		  str += ' aria-expanded="true" ';
	    else
		 str += ' aria-expanded="false" '; 
	 
		str +=' aria-controls="collapseOne'+result[i].year+'"> '+result[i].year+' PERFORMANCE';
		//if(result[i].year == "2014")
		str += '<span class="pull-right"><i class="glyphicon glyphicon-chevron-up"></i></span></a>';
		//else
		//str += '<span class="pull-right"><i class="glyphicon glyphicon-plus"></i></span></a>';
		
        str += '</h4>';
	    str += '</div>';
		
		str += '<div id="collapseOne'+result[i].year+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+result[i].year+'">';
		str += '<div class="panel-body" style="padding: 0px;" >';
		
		str += '<div class="row ">';
		str += '<div class="col-md-12 table-responsive">';
		str += '<ul class="performanceGraph">';
	 //if(result[i].year != "2009" && globalVoterCardNo != "")
		str += '<li>';
		str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].constiPerc+'%" data-percent="'+result[i].constiPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own AC ('+result[i].constituencyName.toUpperCase()+')"></div>';
		str += '</li>';
		
		str += '<li>';
		str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].parConsPerc+'%" data-percent="'+result[i].parConsPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own PC ('+result[i].parliamentName.toUpperCase()+')"></div>';
		str += '</li>';
		
		str += '<li>';
		str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].districtPerc+'%" data-percent="'+result[i].districtPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own District ('+result[i].districtName.toUpperCase()+')"></div>';
		str += '</li>';
		
	 }else{
			
			str += '<div class="panel panel-default">';
			str += '<div class="panel-heading  bg_white" style="" role="tab" id="headingOne'+result[i].year+'">';
			str += '<h4 class="panel-title">';
			str += '<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne'+result[i].year+'" ';
			if(result[i].year == "2014")
			  str += ' aria-expanded="true" ';
			else
			 str += ' aria-expanded="false" '; 
		 
			str +=' aria-controls="collapseOne'+result[i].year+'"> '+result[i].year+' PERFORMANCE';
			//if(result[i].year == "2014")
			str += '<span class="pull-right"><i class="glyphicon glyphicon-chevron-up"></i></span></a>';
			//else
			//str += '<span class="pull-right"><i class="glyphicon glyphicon-plus"></i></span></a>';
			
			str += '</h4>';
			str += '</div>';
			
			str += '<div id="collapseOne'+result[i].year+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+result[i].year+'">';
			str += '<div class="panel-body" style="padding: 0px;" >';
			
			str += '<div class="row ">';
			str += '<div class="col-md-12 table-responsive">';
			str += '<ul class="performanceGraph">';
		 //if(result[i].year != "2009" && globalVoterCardNo != "")
			 if(result[i].year != "2009" && globalVoterCardNo != "")
			 {
				   str += '<li style="" >';
				 if(result[i].boothPerc == "0"){
					  str += '<div class="fulCircleCls1" data-dimension="100%" data-info="Own Booth -('+result[i].reasonStr+') " data-text="0%" data-percent="0" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>';
				 }
				 else{
					  str += '<div class="fulCircleCls1" data-dimension="100%" data-info="Own Booth" data-text="'+result[i].boothPerc+'%" data-percent="'+result[i].boothPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>';
				 }
			 
			  
			   str += '</li>';
			 }else if(globalVoterCardNo == ""){
			   str += '<li style="" >';
			   str += '<div class="fulCircleCls1" data-dimension="100%" data-info="Own Booth (No Voter ID)" data-text="0" data-percent="0" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" ></div>';
			   str += '</li>';
			 } 
		   
		if(result[i].cadreLocation =="Mandal")
		{
			str += '<li>';
			str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].panchPerc+'%" data-percent="'+result[i].panchPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Panchayat ('+globalPancName.toUpperCase()+')"></div>';
			str += '</li>';
		 }
		
		str += '<li>';
		if(globalTehsName != null && globalTehsName.trim().length>0)
			str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].mandalPerc+'%" data-percent="'+result[i].mandalPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Man/Mun ('+globalTehsName.toUpperCase()+')"></div>';
		else  if(result[i].mandalNameStr != null && result[i].mandalNameStr.length>0){
			str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].mandalPerc+'%" data-percent="'+result[i].mandalPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own Man/Mun ('+result[i].mandalNameStr.toUpperCase()+')"></div>';
			$("#mandalId").html(result[i].mandalNameStr.toUpperCase());
		}
		
		str += '</li>';
		
		str += '<li>';
		str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].constiPerc+'%" data-percent="'+result[i].constiPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own AC ('+globalConstName.toUpperCase()+')"></div>';
		str += '</li>';
		
		str += '<li>';
		str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].parConsPerc+'%" data-percent="'+result[i].parConsPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own PC ('+globalParlName.toUpperCase()+')"></div>';
		str += '</li>';
		
		str += '<li>';
		str += '<div class="fulCircleCls1" data-dimension="100%" data-text="'+result[i].districtPerc+'%" data-percent="'+result[i].districtPerc+'" data-fgcolor="#330000" data-bgcolor="#cccccc" data-type="half" data-info="Own District ('+globalDistName.toUpperCase()+')"></div>';
		str += '</li>';
	
	 }
		
		str += '</ul>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
	}
	$(".electionPerformanceDiv").html(str);
	$('.fulCircleCls1').circliful();
	var windowWidth = $(window).width();
	 if( windowWidth > 768)
	 {
		var graphCount = $(".performanceGraph li").length;
		var pixelWidth = (100 / graphCount) ;
		$(".performanceGraph li").css("width",pixelWidth+'%');
	 }
}

// Financial
var financialColorArr = [];
var typearr = ['EDP','CM Relief Fund','Educational','Financial Support','Personal'];
function setcolorsForFinancialchart()
	{
	financialColorArr = new Array();
		var colorStatic = new Array('#D64D54','#66CDCC','#00B17D','#663300','#4169E1','#1771D7');
		
		var colorCount = 0;
		
		for(var i in typearr)
		{
		 		var obj = {
				 type : typearr[i],
				 color : colorStatic[colorCount]
				}
				financialColorArr.push(obj)
			  if(colorCount == (colorStatic.length)-1)
				colorCount = 0;
				 colorCount++;
		}
		return financialColorArr;
	}
	function getColorCodeByType(type)
	{

		if(financialColorArr != null && financialColorArr.length > 0)
		{
			for(var i in financialColorArr)
			{
				if(financialColorArr[i].type == type)
					return financialColorArr[i].color;
			}
		}
	}
	
	function getApprovedFinancialSupprotForCadre()
{
	setcolorsForFinancialchart();
	$.ajax({
		type : "POST",
		url  : "getApprovedFinancialSupprotForCadreAction.action",
		data : {tdpCadreId:globalCadreId}
		
	}).done(function(result){
		$("#headingId").html("");
		$("#donutchart2").html("");
		$("#financeSupportUL").html("");
		
		if(result != null && result.length > 0)
		  buildApprovedFinancialSupprotForCadre(result);
	  else
		$("#donutchart2").html("No Data Available."); 
	});
}

function buildApprovedFinancialSupprotForCadre(result)
{
	var dataArr = [];
	var colorsArr = [];
	
   if(result != null && result.length > 0)
   {
	 for(var i in result)
	 {
	  if(result[i].name !='')
	  {
		  var data= new Array();
		  data.push(result[i].name,result[i].donationAmount);
		  dataArr.push(data);
		  colorsArr.push(getColorCodeByType(result[i].name));
	  }
	
	  }
	}
	 
	Highcharts.setOptions({
        colors: colorsArr
    });
	$('#donutchart2').highcharts({
        chart: {
            type: 'pie',
			backgroundColor: 'transparent',
			
            options3d: {
                enabled: false,
                alpha: 50
            }
        },
		legend: {
                enabled: true,
                align: 'right',
                verticalAlign: 'right',
                floating: false,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
        plotOptions: {
            pie: {
                innerSize: 40,
                depth: 10,
				dataLabels: {
                    enabled: false,
				}
            }, 
        },
		
		series: [{
			name : 'Count',
            data: dataArr
        }]
    });
    $("#headingId").html(""+result[0].totalComplaints+" ["+result[0].totalRequests+"]/-");

	var str = '';
	for(var i in result)
	{
		if(result[i].name !='')
		{
		  var color = getColorCodeByType(result[i].name);
		  str += '<li style="color:'+color+';text-transform:uppercase">';
		  str += '<span style="margin-right:4px;padding:0px 8px;background-color:'+color+';"></span>';
		  str += ''+result[i].name+' '+result[0].count+'';  
		   if(result[i].donationAmount != null)
			 str += '['+result[i].donationAmount+'/-]</li>';
		  else
		   str += '[0/-]</li>';  
		}
	}
	$("#financeSupportUL").html(str);
	
	
}
function getCandidateAndLocationSummaryNews(){
	
	//rebuilding the blocks
	$("#candidateCategoryWiseNewsId").html("");
	$("#propertiesId").html("");
	$("#issuesSummary").html("");
	$("#issuesCount").html("");
	
	//data loading Image.
	$("#dataLoadingsImgForNewsId1").show();
	$("#hideShowNewsDiv").hide();
	
		var locatioinType;
		var locationId;
		if($(".newsRadioCls").is(':checked')) {
			if($("#panchayatRadioNewsId").is(':checked')){
					locatioinType="panchayat";
					locationId=$("#panchayatRadioNewsId").val();
			}
			else if($("#mandalRadioNewsId").is(':checked')){
				locatioinType="mandal";
				locationId=$("#mandalRadioNewsId").val();
			}
			else if($("#aConstiRadioNewsId").is(':checked')){
				locatioinType="constituency";
				locationId=$("#aConstiRadioNewsId").val();
			}
			else if($("#pConstiRadioNewsId").is(':checked')){
				locatioinType="parliament";
				locationId=$("#pConstiRadioNewsId").val();
			}
			else if($("#districtRadioNewsId").is(':checked')){
				locatioinType="district";
				locationId=$("#districtRadioNewsId").val();
			}
		}
		
		var fromDate=$(".dp_startDate").val();//mm/dd/yyyy
		var toDate=$(".dp_endDate").val();
		
		var startDate;
		var endDate;
		if((fromDate !=null && fromDate !="") && (toDate !=null && toDate !="")){
			var startArray=fromDate.split("/");
			startDate=startArray[1]+"-"+startArray[0]+"-"+startArray[2];
			var endArray=toDate.split("/");
			endDate=endArray[1]+"-"+endArray[0]+"-"+endArray[2];
		}
		if(globalCandidateId ==undefined || globalCandidateId ==null){
			globalCandidateId=0;
		}
		candidateId=globalCandidateId;
		locationType=locatioinType;
		locationId=locationId;
		//locationType="district";
		//locationId=13;
		startDate=startDate;
		endDate=endDate;
		
	 $.ajax({
		//url: "http://mytdp.com/CommunityNewsPortal/webservice/getCandidateAndLocationSummary/"+startDate+"/"+endDate+"/"+locationType+"/"+locationId+"/"+candidateId+""
		url: wurl+"/CommunityNewsPortal/webservice/getCandidateAndLocationSummary/"+startDate+"/"+endDate+"/"+locationType+"/"+locationId+"/"+candidateId+"/"+globalCadreId+""
	}).then(function(result) {
		$("#dataLoadingsImgForNewsId1").hide();
		$("#hideShowNewsDiv").show();
		if(result !=null && result !=""){
			var str="";
				str+='<div class="panel-body pad_0">';
					if(result.candidateSummary !=null){
						  var communityNewsCandidateId = result.candidateSummary.communityNewsCandidateId;
                            	str+='<table class="table m_0 table-bordered m_0">';
                                	str+='<tr>';
                                    	str+='<td>TOTAL ARTICLES <a class="candidateRedirectedCls" attr_candidateId="'+communityNewsCandidateId+'" attr_categoryId="0" attr_benefitId="0" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.totalCount+'" style="cursor:pointer;"> <span class="pull-right text-bold">'+result.candidateSummary.totalCount+'</span></a></td>';
                                        str+='<td>POSITIVE ARTICLES <a class="candidateRedirectedCls" attr_candidateId="'+communityNewsCandidateId+'" attr_categoryId="0" attr_benefitId="1" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.positiveCount+'" style="cursor:pointer;"> <span class="pull-right text-bold">'+result.candidateSummary.positiveCount+'</span></a></td>';
                                        str+='<td>NEGATIVE ARTICLES <a class="candidateRedirectedCls" attr_candidateId="'+communityNewsCandidateId+'" attr_categoryId="0" attr_benefitId="2" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.negativeCount+'" style="cursor:pointer;"> <span class="pull-right text-bold">'+result.candidateSummary.negativeCount+'</span></a></td>';
                                        str+='<td>NEUTRAL ARTICLES <a class="candidateRedirectedCls" attr_candidateId="'+communityNewsCandidateId+'" attr_categoryId="0" attr_benefitId="3" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.neutralCount+'" style="cursor:pointer;"> <span class="pull-right text-bold">'+result.candidateSummary.neutralCount+'</span></a></td>';
                                    str+='</tr>';
                                str+='</table>';
								if(result.candidateSummary.categoryList !=null && result.candidateSummary.categoryList.length>0){
									str+='<table class="table m_0 m_0">';
										str+='<thead>';
											str+='<th width="50%">CATEGORY NAME</th>';
											str+='<th>POSITIVE</th>';
											str+='<th>NEGATIVE</th>';
											str+='<th>NEUTRAL</th>';
										str+='</thead>';
										str+='<tbody>';
										
											for(var i in result.candidateSummary.categoryList){
												str+='<tr>';
													str+='<td id="'+result.candidateSummary.categoryList[i].categoryId+'">'+result.candidateSummary.categoryList[i].categoryName+'</td>';
													if(result.candidateSummary.categoryList[i].positiveCount !=0){
														str+='<td><a class="candidateRedirectedCls" attr_candidateId="'+communityNewsCandidateId+'" attr_categoryId="'+result.candidateSummary.categoryList[i].categoryId+'" attr_benefitId=1 attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.categoryList[i].positiveCount+'" style="cursor:pointer;"><span class="text-success">'+result.candidateSummary.categoryList[i].positiveCount+'</span></a></td>';
													}else{
														str+='<td><span class="text-success">'+result.candidateSummary.categoryList[i].positiveCount+'</span></td>';
													}
													if(result.candidateSummary.categoryList[i].negativeCount !=0){
														str+='<td><a class="candidateRedirectedCls" attr_candidateId="'+communityNewsCandidateId+'" attr_categoryId="'+result.candidateSummary.categoryList[i].categoryId+'" attr_benefitId=2 attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.categoryList[i].negativeCount+'"   style="cursor:pointer;"><span class="text-danger">'+result.candidateSummary.categoryList[i].negativeCount+'</span></a></td>';
													}else{
														str+='<td><span class="text-danger">'+result.candidateSummary.categoryList[i].negativeCount+'</span></td>';
													}
													if(result.candidateSummary.categoryList[i].neutralCount !=0){
														str+='<td><a class="candidateRedirectedCls" attr_candidateId="'+communityNewsCandidateId+'" attr_categoryId="'+result.candidateSummary.categoryList[i].categoryId+'" attr_benefitId=3 attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_count="'+result.candidateSummary.categoryList[i].neutralCount+'" style="cursor:pointer;"><span class="text-warning">'+result.candidateSummary.categoryList[i].neutralCount+'</span></a></td>';
													}else{
														str+='<td><span class="text-warning">'+result.candidateSummary.categoryList[i].neutralCount+'</span></td>';
													}
													
												str+='</tr>';
											}
									
										str+='</tbody>';
									str+='</table>';
								}
								else{
										str+='<div class="text-center">&nbsp No Data Available.</div>';
									}
                    str+='</div> ';
				
					$("#candidateCategoryWiseNewsId").html(str);
				}else{
					$("#candidateCategoryWiseNewsId").html("Candidate Wise Data Not Available.");
				}
				if(result.departmentSummary !=null && result.departmentSummary.length>0){
					buildingIssuesTable(result.departmentSummary,startDate,endDate,locationId,locationType);
				}else{
					$("#issuesSummary").html("<center><h5>&nbsp No Data Available.</h5></center>");
				}
				if(result.locationSummary !=null){
					$("#propertiesId").html("");
					buildingPropertiesResult(result.locationSummary,startDate,endDate,locationId,locationType);
				}else{
					$("#propertiesId").html("Location Wise Data Not Available.");
				}
		}else{
			$("#newsMainDivId").html("Problem Ocuured While Getting Data..Please Contact Admin..");
		}
	}); 
}
	function buildingIssuesTable(result,startDate,endDate,locationId,locationType){
		$("#issuesSummary").html("");
		$("#issuesCount").html("");
		var str = "";
		var ttlCount = result[0].totalCount;
		$("#issuesCount").html(' TOTAL COUNT - <a class="departmentNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_departmentId="0" attr_locationId="'+locationId+'" attr_locationType="'+locationType+'" attr_count ="'+ttlCount+'" style="cursor:pointer;" >'+ttlCount+'</a>');
		for(var i in result){
			str +="<tr>";
			str +="<td width='80%'>"+result[i].partyName+"</td>";
			str +='<td><a class="departmentNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_departmentId="'+result[i].partyId+'" attr_locationId="'+locationId+'" attr_locationType="'+locationType+'" attr_count ="'+result[i].count+'" style="cursor:pointer;" >'+result[i].count+'</a></td>';
			str +="</tr>";
		}
		$("#issuesSummary").html(str);
	}
function buildingPropertiesResult(result,startDate,endDate,locationId,locationType){
		$("#propertiesId").html("");
		var props = result.typeList;
		var str = '';
		if(props!=null && props.length>0){
			for(var i in props){
				if(props[i].propertyType=="detail"){
					str+='<div class="col-md-6">';
                        str+='<div class="panel panel-default">';
                        str+='<div class="panel-heading bg_f9">';
                        str+='<h4 class="panel-title text-center text-capitalize">'+props[i].aliasName+'</h4>';
                        str+='</div>';
                        str+='<div class="panel-body pad_0 table-scroll">';
						var totalCheck=false;
                        str+='<table class="table m_0 m_0" id="analysisStoriesId">';
                        str+='<thead id="divId">';
                        str+='<tr>';
                        str+='<th>Party Name</th>';
                        str+='<th>Positive</th>';
                        str+='<th>Negative</th>';
                        str+='<th>Neutral</th>';
                        str+='</tr>';
                        str+='</thead>';
						$("#divId").show();//thead Showing for Analysis
						for(var j in props[i].partiesList){
							var condCheck=true;
							var cnt = 0;
							for(var m in props[i].partiesList[j].oppenentsList){
								if(props[i].partiesList[j].oppenentsList[m].count==0){
									cnt = cnt + 1;
								}
							}
							if(cnt == 3){condCheck = false;}
							if(condCheck){
								totalCheck=true;
									str+='<tr>';
										str+='<td>'+props[i].partiesList[j].partyName+'</td>';
										for(var k in props[i].partiesList[j].oppenentsList){
											str+='<td>';
											if(props[i].partiesList[j].oppenentsList[k].aliasName=='Pos'){
												if(props[i].partiesList[j].oppenentsList[k].count==0){
													str+='<span>'+props[i].partiesList[j].oppenentsList[k].count+'</span>';
												}else{
													str+='<a class="analysisNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_benefitId=1 attr_partyId="'+props[i].partiesList[j].partyId+'" attr_locationType="'+locationType+'" attr_propertyId="'+props[i].id+'" attr_locationId="'+locationId+'" attr_propertyType="'+props[i].propertyType+'" attr_count="'+props[i].partiesList[j].oppenentsList[k].count+'" style="cursor:pointer;"><span class="text-success">'+props[i].partiesList[j].oppenentsList[k].count+'</span></a>';
												}
											}else if(props[i].partiesList[j].oppenentsList[k].aliasName=='neg'){
												if(props[i].partiesList[j].oppenentsList[k].count==0){
													str+='<span>'+props[i].partiesList[j].oppenentsList[k].count+'</span>';
												}else{
													str+='<a class="analysisNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_benefitId=2 attr_partyId="'+props[i].partiesList[j].partyId+'" attr_propertyId="'+props[i].id+'" attr_locationType="'+locationType+'" attr_locationId="'+locationId+'" attr_propertyType="'+props[i].propertyType+'" attr_count="'+props[i].partiesList[j].oppenentsList[k].count+'" style="cursor:pointer;"><span class="text-danger">'+props[i].partiesList[j].oppenentsList[k].count+'</span></a>';
												}
											}else if(props[i].partiesList[j].oppenentsList[k].aliasName=='Neutral'){
												if(props[i].partiesList[j].oppenentsList[k].count==0){
													str+='<span>'+props[i].partiesList[j].oppenentsList[k].count+'</span>';
												}else{
													str+='<a class="analysisNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_benefitId=3 attr_partyId="'+props[i].partiesList[j].partyId+'" attr_locationType="'+locationType+'" attr_propertyId="'+props[i].id+'" attr_locationId="'+locationId+'" attr_propertyType="'+props[i].propertyType+'" attr_count="'+props[i].partiesList[j].oppenentsList[k].count+'" style="cursor:pointer;"><span class="text-warning">'+props[i].partiesList[j].oppenentsList[k].count+'</span></a>';
												}
											}
											str+='</td>';
									
								}	
								str+='</tr>';
							}
							
						}
                        str+='</table>';
						if(totalCheck){
							$("#divId").show();
						}else{
							$("#divId").hide();//thead Hiding for Analysis
							str+='<tr><center><h5>No Data Available</h5></center></tr>';
						}
                        str+='</div>';
                        str+='</div>';
					
				}else if(props[i].propertyType=="versus"){
					
                        str+='<div class="panel panel-default">';
                        str+='<div class="panel-heading bg_f9" style="padding-left:5px;padding-right:5px;">';
                        str+='<h4 class="panel-title text-center text-capitalize">'+props[i].aliasName+' </h4>';
                        str+='</div>';
                        str+='<div class="panel-body pad_0 table-scroll">';
                        str+='<table class="table m_0 table-bordered m_0">';
						
						var isExist=false;var z=1;
						if(props[i].partiesList !=null && props[i].partiesList.length>0){
							for(var j in props[i].partiesList){
								if(props[i].partiesList[j].oppenentsList !=null && props[i].partiesList[j].oppenentsList.length >0){
									for(var k in props[i].partiesList[j].oppenentsList){
										if(props[i].partiesList[j].oppenentsList[k].count !=0){
											if(z==1){
												str+='<h5 style="font-weight:bold;"><center> BY '+props[i].partiesList[j].partyName+' PARTY </center></h5>';
											}
											isExist=true;
											str+='<tr>';
											str+='<td>'+props[i].partiesList[j].oppenentsList[k].partyName+'</td>';
											str+='<td><a class="actionReactionNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_partyId=872 attr_propertyId="'+props[i].id+'" attr_locationId="'+locationId+'" attr_secondaryPartyId="'+props[i].partiesList[j].oppenentsList[k].partyId+'" attr_locationType="'+locationType+'" attr_propertyType="'+props[i].propertyType+'" attr_count="'+props[i].partiesList[j].oppenentsList[k].count+'" style="cursor:pointer;">'+props[i].partiesList[j].oppenentsList[k].count+'</a></td>';
											str+='</tr>';
											z=2;
										}
									}
								}
							}
							if(!isExist){
									str+='<tr><center><h5>No Data Available</h5></center></tr>';
								}
						}
						else{
							str+='<tr><center><h5>No Data Available</h5></center></tr>';
						}
						str+='<tr>';
						str+='</tr>';
						str+='</table>';
                        str+='</div>';
                        str+='</div>';
                   
				}else if(props[i].propertyType=="summary" && props[i].aliasName =="SPOT NEWS"){
					
                        str+='<div class="panel panel-default">';
                        str+='<div class="panel-heading bg_f9" style="padding-left:5px;padding-right:5px;">';
                        str+='<h4 class="panel-title text-center text-capitalize">'+props[i].aliasName+'</h4>';
                        str+='</div>';
                        str+='<div class="panel-body pad_0 table-scroll">';
                        str+='<table class="table m_0 table-bordered m_0">';
						var valExist=false;
						for(var j in props[i].partiesList){
							if( props[i].partiesList[j].count !=0){
								valExist=true;
								str+='<tr>';
									str+='<td>'+props[i].partiesList[j].partyName+'</td>';
									str+='<td><a class="summaryNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_propertyId="'+props[i].id+'" attr_locationId="'+locationId+'" attr_locationType="'+locationType+'" attr_partyId="'+props[i].partiesList[j].partyId+'" attr_propertyType="'+props[i].propertyType+'" style="cursor:pointer;" attr_count="'+props[i].partiesList[j].count+'">'+props[i].partiesList[j].count+'</a></td>';
								str+='</tr>';
							}
						}
						if(valExist==false){
								str+='<tr><center><h5>No Data Available</h5></center></tr>';
							}
						str+='</table>';
                        str+='</div>';
                        str+='</div>';
                    str+='</div>';
				}
				else if(props[i].propertyType=="summary" && props[i].aliasName !="SPOT NEWS"){
					str+='<div class="col-md-6">';
                        str+='<div class="panel panel-default">';
                        str+='<div class="panel-heading bg_f9" style="padding-left:5px;padding-right:5px;">';
                        str+='<h4 class="panel-title text-center text-capitalize">'+props[i].aliasName+'</h4>';
                        str+='</div>';
                        str+='<div class="panel-body pad_0 table-scroll">';
                        str+='<table class="table m_0 table-bordered m_0">';
						var valExist=false;
						for(var j in props[i].partiesList){
							if( props[i].partiesList[j].count !=0){
								valExist=true;
								str+='<tr>';
									str+='<td>'+props[i].partiesList[j].partyName+'</td>';
									str+='<td><a class="summaryNewsCls" attr_fromDate="'+startDate+'" attr_toDate="'+endDate+'" attr_propertyId="'+props[i].id+'" attr_locationId="'+locationId+'" attr_locationType="'+locationType+'" attr_count="'+props[i].partiesList[j].count+'" attr_partyId="'+props[i].partiesList[j].partyId+'" style="cursor:pointer;">'+props[i].partiesList[j].count+'</a></td>';
								str+='</tr>';
							}
						}
						if(valExist==false){
								str+='<tr><center><h5>No Data Available</h5></center></tr>';
							}
						str+='</table>';
                        str+='</div>';
                        str+='</div>';
                    str+='</div>';
				}
			}
		}
		$("#propertiesId").html(str);
		$('.table-scroll').scrollator({
			custom_class: 'table-scroll',
		});
	}
	
	//Dont Delete the Below Content Please..
	
	 /* var jsObj={
		candidateId:347,
		locationType:"district",
		locationId:13,
		startDate:"09-06-2015",
		endDate:"15-07-2015"	
	}
		$.ajax({
				type : "POST",
				url  : "getCandidateAndLocationSummaryNewsAction.action",
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				console.log(result);
			}); */
			
/*function getCadreIdByMemberShipId(){
	$.ajax({
		type : "POST",
		url  : "getCadreIdByMembershipIdAction.action",
		data : {membershipId:"38324292"}
		}).done(function(result){
			//console.log(result);
		});
	}*/

function getCategoryWiseStatusCount()
{
	$.ajax({
		type : "POST",
		url  : "getCategoryWiseStatusCountAction.action",
		data : {tdpCadreId:globalCadreId}
		
	}).done(function(result){
		$("#donutchart").html("");
		$("#totalComplaintsId").html("");
		$("#complaintStatusUL").html("");
		if(result != null && result.subList != null && result.subList.length > 0)
		{
		  categroyWiseChart(result.subList);
		  buildCategroyInfo(result);
		}
	  else
	   $("#totalComplaintsId").html("0");
	});
}

function categroyWiseChart(result)
{
	
	var dataArr = [];
	var colorsarr = new Array();
	for(var i in result)
	{
	  var data= new Array();
	  data.push(result[i].status,result[i].count);
	  dataArr.push(data);
	  colorsarr.push(result[i].color);
	}

	Highcharts.setOptions({
        colors: colorsarr
    });
    $('#donutchart').highcharts({
        chart: {
            type: 'pie',
			backgroundColor: 'transparent',
            options3d: {
                enabled: false,
                alpha: 50
            }
        },
		legend: {
                enabled: true,
                align: 'right',
                verticalAlign: 'right',
                floating: false,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
        plotOptions: {
            pie: {
                innerSize: 40,
                depth: 10,
				dataLabels: {
                    enabled: false,
				}
            }, 
        },
		
		series: [{
			name : 'Count',
            data: dataArr
        }]
    });

}

function buildCategroyInfo(result)
{
	$("#totalComplaintsId").html(""+result.count+"");
	var str = '';
	for(var i in result.subList)
	{
		str += '<li class="show-dropdown">';
		if(result.subList[i].status.toLowerCase() == ("In Progress").toLowerCase())
		{
		 str += '<span class="InProgress"></span>'+result.subList[i].status+'<span><span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		if(result.subList[i].status.toLowerCase() == ("Completed").toLowerCase())
		{
		 str += '<span class="Completed">'+result.subList[i].status+'<span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		if(result.subList[i].status.toLowerCase() == ("Not Verified").toLowerCase())
		{
		 str += '<span class="NotVerified">'+result.subList[i].status+'<span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		if(result.subList[i].status.toLowerCase() == ("Not Eligible").toLowerCase())
		{
		 str += '<span class="NotEligible"></span>'+result.subList[i].status+'<span><span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		if(result.subList[i].status.toLowerCase() == ("Not possible").toLowerCase())
		{
		 str += '<span class="NotPossible">'+result.subList[i].status+'<span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		if(result.subList[i].status.toLowerCase() == ("Approved").toLowerCase())
		{
		 str += '<span class="Approved">'+result.subList[i].status+'<span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		if(result.subList[i].status.toLowerCase() == ("Verified").toLowerCase())
		{
		 str += '<span class="Verified">'+result.subList[i].status+'<span class="pull-right">'+result.subList[i].count+'</span></span>';
		}
		str += '<ul class="count-hover arrow_box3">';
		for(var j in result.subList[i].subList)
		str += '<li>'+result.subList[i].subList[j].status+'<span class="pull-right">'+result.subList[i].subList[j].count+'</span></li>';
		str += '</ul>';
		str += '</li>';
	}
	$("#complaintStatusUL").html(str);
	
}

function getCadreIdByMemberShipId(){
	
	$.ajax({
		type : "POST",
		url  : "getCadreIdByMembershipIdAction.action",
		data : {membershipId:membershipId,constituencyId:constituencyId}
		
	}).done(function(result){
		//console.log(result);
		if(result != null){
			globalCadreId = result;
			if((participatedConstituencyId == null || participatedConstituencyId == 0) && (globalCadreId != null && globalCadreId > 0)){
				getParticipatedConstituencyId(globalCadreId);
				/* getCategoryWiseStatusCount();
				getTotalMemberShipRegistrationsInCadreLocation();		
				//getCadreFamilyDetailsByCadreId();
				getElectionPerformanceInCadreLocation();
				getApprovedFinancialSupprotForCadre();
				cadreFormalDetailedInformation(globalCadreId);
				getEventDetailsOfCadre(globalCadreId);
				//getTdpCadreSurveyDetails(globalCadreId,0,null,"All",0);
				getLocationwiseCommitteesCount();
				getPartyMeetingsOverViewForCadre();
				getEventsOverviewFortdpCadre(); */
				
			}
			else{
				getCategoryWiseStatusCount();
				//getTotalMemberShipRegistrationsInCadreLocation();		
				//getCadreFamilyDetailsByCadreId();//swadhin
				//getElectionPerformanceInCadreLocation();
				getApprovedFinancialSupprotForCadre();
				cadreFormalDetailedInformation(globalCadreId);
				getEventDetailsOfCadre(globalCadreId);
				//getTdpCadreSurveyDetails(globalCadreId,0,null,"All",0);
				getLocationwiseCommitteesCount();
				getPartyMeetingsOverViewForCadre();
				getEventsOverviewFortdpCadre();
				getActivityDetails();
				//getCandidateAppliedPostsByCadre(0);
			}
			
		}

	});
}
function getLocationwiseCommitteesCount()
{
	var locationId=0;
	var electionType="";
	if(participatedConstituencyType !=null && participatedConstituencyType !="" && participatedConstituencyType !=undefined){
		electionType = participatedConstituencyType;
		if(participatedConstituencyId !=0){
			locationId=participatedConstituencyId;
		}else if(participatedParliamentId !=0){
			locationId = participatedParliamentId;
		}
	}
	var committeeEnrollmentYrIds = [];
	if($("#tdpCommitteeYearId").val() != "" && $("#tdpCommitteeYearId").val() != null){
		committeeEnrollmentYrIds.push($("#tdpCommitteeYearId").val());
	}else{
		committeeEnrollmentYrIds.push(2);
	}
	
	var locationType = $("input[name='committeeLocation']:checked").val();
		
	$("#committeesCountDiv").html("");
	$("#committeesCountDiv").html('<img alt="Processing Image" src="./images/icons/search.gif">');
	
	var jsObj=
			{
				locationType : locationType,
				tdpCadreId:globalCadreId,
				locationId:locationId,
				electionType:electionType,
				committeeEnrollmentYrIds:committeeEnrollmentYrIds	
			}
	$.ajax({
		type : "POST",
		url  : "getLocationwiseCommitteesCountAction.action",
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0)
		 buildLocationwiseCommitteesCount(result);
	 else
	  $("#committeesCountDiv").html("No Data Available.");
		//console.log(result);
	});
}

function buildLocationwiseCommitteesCount(result)
{
	var str = '';
	str += '<table class="table m_0 table-bordered">';
	str += '<thead style="background:#f2f2f2">';
	str += '<tr>';
	str += '<th></th>';
    str += '<th class="text-center" colspan="5">Main Committees</th>';
    str += '<th class="text-center" colspan="5">Affliated Committees</th>';
    str += '</tr>';
	str += '<tr>';
    str += '<th></th>';
    str += '<th>Total</th>';
    str += '<th>Started</th>';
	str += '<th>Not Yet Started</th>';
    str += '<th>Completed</th>';
	
    str += '<th>Members</th>';
    str += '<th>Total</th>';
    str += '<th>Started</th>';
	str += '<th>Not Yet Started</th>';
    str += '<th>Completed</th>';
	
    str += '<th>Members</th>';
    str += '</tr>';
	str += '</thead>';
	if(result[0].areaType !=null && result[0].areaType !=""){
		globalAreaType=result[0].areaType;
	}
	
	for(var i in result)
	{
	   str += '<tr>';
	  if(result[i].locationType == "Village" || result[i].locationType =="mandalLevelVillage" || result[i].locationType =="Ward" || result[i].locationType=="municipalWiseWards")
	   str += '<td>Village/Ward Committees</td>';
   
     if(result[i].locationType == "Mandal" || result[i].locationType=="localElection")
	   str += '<td>Mandal/Town/Division Committees</td>';
   
    if(result[i].locationType == "District")
	   str += '<td>District Committees</td>';
   
   var affiNotyetStarted=0;
   var mainNotyetStarted=0;
	if(result[i].mainCommTotalCount !=null && result[i].mainCommTotalCount !=0){
		mainNotyetStarted=result[i].mainCommTotalCount - (result[i].mainCommStartedCount + result[i].mainCommCompletedCount);
	}
	if(result[i].affiCommTotalCount !=null && result[i].affiCommTotalCount !=0){
		affiNotyetStarted=result[i].affiCommTotalCount -(result[i].affiCommStartedCount + result[i].affiCommCompletedCount);
	}
   
     str += '<td>'+result[i].mainCommTotalCount+'</td>';
	 str += '<td>'+result[i].mainCommStartedCount+'</td>';
	  str += '<td>'+mainNotyetStarted+'</td>';
	 str += '<td>'+result[i].mainCommCompletedCount+'</td>';
	 str += '<td>'+result[i].mainCommTotalMembers+'</td>';
	 str += '<td>'+result[i].affiCommTotalCount+'</td>';
	 str += '<td>'+result[i].affiCommStartedCount+'</td>';
	  str += '<td>'+affiNotyetStarted+'</td>';
	 str += '<td>'+result[i].affiCommCompletedCount+'</td>';
	 
	 str += '<td>'+result[i].affiCommTotalMembers+'</td>';
	 
      str += '</tr>';
	}
	
	str += '</table>';
	$("#committeesCountDiv").html(str);
}

var globalCandidateResult;	
 function getCheckCandidateExits(){
	var obj={
		 cadreId : globalCadreId
		  }
		  
    $.ajax({
		type:'GET',
		url :'getCheckCandidateCadreExitsAction.action',
		data : {task:JSON.stringify(obj)} 
	}).done(function(result){
		globalCandidateResult=[];
		if(result!=null && result.length>0){
			globalCandidateResult = result;
			
		}
	});
};


function getDeathsAndHospitalizationDetails(){
	//data Loading image
	$("#dataLoadingsImgForDeathCount").show();
	$("#deathHospitalDivId").html("");

	var panchaId = 0;
	var mandId = 0;
	var localElecId = 0;
	var consttncyId = 0;
	var parlmntId = 0;
	var dstctId = 0;
	
	if(participatedConstituencyId != null && participatedConstituencyId > 0){
		consttncyId = participatedConstituencyId;
		parlmntId = participatedParliamentId;
		dstctId = participatedDistrictId;
	}
	else{
		panchaId = globalPanchayatId;
		mandId = globalTehsilId;
		localElecId = globalElectionBodyId;
		consttncyId = globalConstituencyId;
		parlmntId = globalParliamentId;
		dstctId = globalDistrictId;
	}
	
	var jsobj={
		panchayatId : panchaId,
		mandalId : mandId,
		lebId : localElecId,
		constituencyId : consttncyId,
		parliamentId : parlmntId,
		districtId : dstctId
	}
	$.ajax({
			type:'POST',
			 url: 'getDeathAndHospitalizationDetailsAction.action',
			 data : {task:JSON.stringify(jsobj)} ,
			}).done(function(result){
				var str='';
				if(result != null){
					str+='<div class="panel-body pad_0">';
					//str+='<p class="text-center m_0" style="font-size:12px;margin-top:10px;"> NOTE : DEATH - DEATH INSURANCE ,  HOSP. - HOSPITALIZATION INSURANCE</p>';
					str+='<div class="table-responsive">';
						str+='<table class="table m_0 table-bordered m_0">';
							str+='<thead class="background:#f2f2f2;">';
								str+='<tr>';
									str+='<th rowspan="2" style="text-align:center;">STATUS</th>';
									for(var i in result.locationList){
										str+='<th colspan="2" style="text-align:center;text-transform:uppercase"> '+result.locationList[i].name+' </th>';
										}
								str+='</tr>';
								str+='<tr>';
								for(var i in result.locationList){
									str+='<th style="text-align:center;">DEATH</th>';
									str+='<th style="text-align:center;">HOSPITALIZATION</th>';
								}
								str+='</tr>';
								str+='</thead>';
								str+='<tbody>';
									for(var i in result.subList){
										str+='<tr>';
										str+='<td style="text-transform:uppercase">'+result.subList[i].name+'</td>';
										if(result.subList[i].subList != null && result.subList[i].subList.length>0){
											for(var k in result.subList[i].subList){
													if(result.subList[i].subList[k].count != null)
														if(result.subList[i].name != 'TOTAL')
															str+='<td style="text-align:center;"><a class="statusWiseDetailsCls" style="cursor:pointer;" attr_status_id="'+result.subList[i].subList[k].id+'" attr_statusName="'+result.subList[i].name+'" attr_issue_type="'+result.subList[i].subList[k].name+'" attr_locationStr="'+result.subList[i].subList[k].locationName+'" >'+result.subList[i].subList[k].count+'</a></td>';
														else
															str+='<td style="text-align:center;">'+result.subList[i].subList[k].count+'</td>';
													else
														str+='<td style="text-align:center;"> 0 </td>';
											}											
										}
										str+='</tr>';
									}
								str+='</tbody>';
						str+'</table>';
					str+='</div>';
					str+='</div>';
					$('#deathHospitalDivId').html(str);
					$('#deathHospitalDivId').append('<div style="margin-top:15px;"> <b>Note</b>: HOSPI.- HOSPITALIZATION</div>');
				}
				 else{
					 str+='<div>Some Problem Occured Please Contact Admin.</div>';
					 $('#deathHospitalDivId').html(str);
				 }
				$("#dataLoadingsImgForDeathCount").hide();
			});
		if(participatedConstituencyId != null && participatedConstituencyId > 0){
			$("#deathAndHospitalParticepationId").attr("data-original-title","PARTICIPATED CONSTITUENCY"); 
			$('#deathAndHospitalParticepationId').tooltip();			
		}else{
			$("#deathAndHospitalParticepationId").attr("data-original-title","OWN CONSTITUENCY");
			$('#deathAndHospitalParticepationId').tooltip();
		}
}

$(document).on("click",".tdpCandidatePageCls",function(){
	
	var deathTdpCadreId = $(this).attr("attr_tdpCadreId");
	var redirectWindow=window.open('cadreDetailsAction.action?cadreId='+deathTdpCadreId+'','_blank');
});

$(document).on("click",".openmodalshow",function(){
	var divId=$(this).attr("attr_divId");
	$("#"+divId).show();
});

$(document).on("click",".openmodalshowGrievance",function(){
	var divId=$(this).attr("attr_div_id");
	$("#"+divId).show();
});
	
function getComplaintTrackingDetails(complaintId,divId){
	
	$("#viewGrievanceStatusFlow").modal("show")
	var jsobj={
		complaintId : complaintId
	}
	$.ajax({
		 type:'POST',
		 url: 'getStatusTrackingDetailsOfInsuranceByComplaintAction.action',
		 data : {task:JSON.stringify(jsobj)} ,
	}).done(function(result){
		if(result!=null && result.onlystatus!=null && result.onlystatus.trim().length>0){
			 var str='';
			 str+='<div class="ui-steps-border">';
			 str+='<div class="ui small steps" style="width:100%;">';
			 str+='<div class="active step" style="padding:20px 10px">';
				str+='<div class="content">';
					str+='<div class="title" style="font-size:12px">'+result.onlystatus.toUpperCase() +'</div>';
				 str+='</div>';
			 str+='</div>';
			 str+='</div>';
			 str+='</div>';
			 $('#grievanceStatusFlowModalBodyId').html(str);
		}
		else if(result!=null && result.simpleVOList1!=null && result.simpleVOList2!=null){
		 var str='';
		 str+='<div class="ui-steps-border">';
		 str+='<div class="ui small steps" style="width:100%;">';
		 for(var i in result.simpleVOList1){
			 
			 if(result.simpleVOList1[i].dateString==null)
				str+='<div class="disabled step"  style="padding:20px 10px">';
			 else{
				  if(result.simpleVOList1[i].status=='current')
					 str+='<div class="active step"  style="padding:20px 10px">';
				  else
					 str+='<div class="step">';
			 }
			 str+='<div class="content">';
				  str+='<div class="title" style="font-size:12px">'+result.simpleVOList1[i].name.toUpperCase() +'</div>';
				  if(result.simpleVOList1[i].name=='Applied'){
					  if(result.simpleVOList1[i].dateString!=null)
					   str+='<div class="description"  style="font-size:11px">Requested on  '+result.simpleVOList1[i].dateString+'</div>';
				  }
				  else{
					  if(result.simpleVOList1[i].dateString!=null){
						  str+='<div class="description">'+result.simpleVOList1[i].dateString+'</div>';
					  }
				  }
			 str+='</div>';
			 if( result.simpleVOList1[i].name!='Cheque Received' && result.simpleVOList1[i].name!='Not Eligible' && result.simpleVOList1[i].name!='Rejected')
			   if(result.simpleVOList1[i].type!=null)
				 str+='<span class="days">'+result.simpleVOList1[i].type+'</span>';
			 str+='</div>';
		 }
		 
		str+='</div>';
		str+='</div>';
		if(result.simpleVOList2!=null){
			
			str+='<div class="pull-right">';
			str+='<button type="button" class="btn btn-default btn-sm openmodalshow" attr_divId="totaldivId'+complaintId+'" style="margin-top:5px"><i class="glyphicon glyphicon-eye-open"></i> &nbsp; Total Flow</span>';
			str+='</div>';
			 
			str+='<div id="totaldivId'+complaintId+'" style="display:none;">';
			str+='<table class="table table-bordered">';
		   str+='<th>USERNAME</th>';
		   str+='<th>STATUS</th>';
		   str+='<th>DATE</th>';
		   for(var i in result.simpleVOList2){
			   str+='<tr>';
			   str+='<td>'+result.simpleVOList2[i].username+'</td>';
			   str+='<td>'+result.simpleVOList2[i].name.toUpperCase()+'</td>';
			   str+='<td>'+result.simpleVOList2[i].dateString+'</td>';
			   str+='</tr>';
		   }
		   str+='<tr>';
		  
		   str+='</tr>';
		   str+='</table>';
		   str+='</div>';
		   str+='</div>';
		}
		$('#grievanceStatusFlowModalBodyId').html(str);
	 }else{
	 
		 var str='';
		 str+='<div class="ui-steps-border">';
		 str+='<div class="ui small steps" style="width:100%">';
		 for(var i in result.simpleVOList1){
			 
			 if(result.simpleVOList1[i].dateString==null)
				str+='<div class="disabled step"  style="padding:20px 10px">';
			 else{
				  if(result.simpleVOList1[i].status=='current')
					 str+='<div class="active step"  style="padding:20px 10px">';
				  else
					 str+='<div class="step"  style="padding:20px 10px">';
			 }
			 str+='<div class="content">';
				  str+='<div class="title" style="font-size:12px;">'+result.simpleVOList1[i].name.toUpperCase() +'</div>';
				  if(result.simpleVOList1[i].name=='not verified'){
					  if(result.simpleVOList1[i].dateString!=null)
					  str+='<div class="description" style="font-size:11px;">Requested on  '+result.simpleVOList1[i].dateString+'</div>';
				  }
				  else{
					  if(result.simpleVOList1[i].dateString!=null){
						  str+='<div class="description" style="font-size:11px;">'+result.simpleVOList1[i].dateString+'</div>';
					  }
				  }
			 str+='</div>';
			 
			 if( result.simpleVOList1[i].name!='Cheque Received' && result.simpleVOList1[i].name!='Not Eligible' && result.simpleVOList1[i].name!='Rejected')
			   if(result.simpleVOList1[i].type!=null)
				 str+='<span class="days">'+result.simpleVOList1[i].type+'</span>';
			 str+='</div>';
		 }
		 
		str+='</div>';
		str+='</div>';
		 $('#grievanceStatusFlowModalBodyId').html(str);
         }
	});
}

$(document).on("click",".statusWiseDetailsCls",function(){
	
	
	$("#deathHospModalBodyId").html('');
	$("#deathHospModelDivId").modal("show");
	$("#dataLoadingsImgForDeathHospDetails").show();
	
	var locationId = 0;
	var locationType = $(this).attr("attr_locationStr");
	var statusId = $(this).attr("attr_status_id");
	var statusName = $(this).attr("attr_statusName");
	var issueType = $(this).attr("attr_issue_type");
	
	//var heading = ""+locationType+" "+issueType+" "+statusName+" Requests Life Cycle ";
	$("#deathHospModalHeadingId").html("<span style='text-transform:uppercase'>"+locationType+" Wise "+issueType+" Insurance "+statusName+" Requests Life Cycle </span>");
	
	if(locationType == "panchayat")
		locationId = globalPanchayatId;
	else if(locationType == "mandal")
		locationId = globalTehsilId;
	else if(locationType == "muncipality")
		locationId = globalElectionBodyId
	else if(locationType == "assembly")
		if(participatedConstituencyId != null && participatedConstituencyId > 0)
			locationId = participatedConstituencyId;
		else
			locationId = globalConstituencyId;
	else if(locationType == "parliament")
		if(participatedConstituencyId != null && participatedConstituencyId > 0)
			locationId = participatedParliamentId;
		else
			locationId = globalParliamentId;
	else if(locationType == "district")
		if(participatedConstituencyId != null && participatedConstituencyId > 0)
			locationId = participatedDistrictId;
		else
			locationId = globalDistrictId;
		
	var jsobj={
		locationId : locationId,
		locationType : locationType,
		statusId : statusId,
		issueType : issueType
	}
	$.ajax({
		 type:'POST',
		 url: 'getComplaintsDetailsByLocationAndStatusAction.action',
		 data : {task:JSON.stringify(jsobj)} ,
		}).done(function(result){
			if(result != null){
				var str='';
				
				str+='<div class="row"><div class="col-md-12"><button type="button" class="pull-right btn btn-primary" id="exportToExcelId" onclick="generateExcel()"> Export To Excel </button></div></div>';
				str+='<div class="table-responsive">';
				str+='<table class="table table-bordered m_0" style="font-size:13px;" id="deathHospLifeCycleTableId">';
				str+='<thead>';
						str+='<tr>';
							str+='<th>Complaint Id</th>';
							str+='<th>Complaint Person Details</th>';
							str+='<th>Subject</th>';
							str+='<th>Description</th>';
							str+='<th>Status</th>';
							str+='<th>Posted Date</th>';
							str+='<th>Last Updated Date</th>';
							str+='<th>View Details</th>';
							/*str+='<th> Name </th>';
							str+='<th> Issue Description </th>';
							str+='<th> Complaint Info </th>';
							str+='<th> Issue Type </th>';
							str+='<th> Present Status </th>';
							str+='<th> View Details </th>';*/
						str+='</tr>';
					str+='</thead>';
					str+='<tbody style="background:#f3f3f3;font-size:13px;">'
					for(var i in result){
						str+='<tr>';
							str+='<td><a href="'+wurl+'/Grievance/homeAction.action?complaintId='+result[i].complaintId+'&typeOfIssue=Insurance" target="_blank">'+result[i].complaintId+'</a></td>';
							str+='<td>';
							str+='<p class="m_0">N:'+result[i].firstName+'</p>';
							str+='<p class="m_0">D:'+result[i].locationName+'</p>';
							str+='<p class="m_0">C:'+result[i].constituency+'</p>';
							str+='<p class="m_0">M:'+result[i].mandalName+'</p>';
							str+='<p class="m_0">V:'+result[i].villageName+'</p>';
							str+='</td>';
							str+='<td>'+result[i].subject+'</td>';
							str+='<td>'+result[i].description+'</td>';
							str+='<td>'+result[i].status+'</td>';
							str+='<td>'+result[i].raisedDate+'</td>';
							str+='<td>';
							if(result[i].updatedDate.length > 0)
								str+=''+result[i].updatedDate+'</td>';
							str+='<td><input type="button" value="View" class="btn btn-sm btn-primary complaintTrackingCla" onclick="getComplaintTrackingDetails('+result[i].complaintId+',\'statusDivIdForInsurance'+i+'\')"/></td>';
							//str+='<td><p>'+result[i].firstName+'</p>';
							//str+='<p>MemberShip No : <a class="tdpCandidatePageCls" style="cursor:pointer;" attr_tdpCadreId="'+result[i].tdpCadreId+'">'+result[i].membershipNo+'</a></p>';
							//str+='<p>Mobile No : '+result[i].mobileNo+'</p>';
							//str+='</td>';
							//str+='<td><p>Subject : </p>';
							//str+='<p>Description : </p>';
							//str+='</td>';
							//str+='</td>';
							//str+='<td><p>Complaint Id : </p>';
							//str+='<p>Posted Date : </p>';
							//str+='</td>';
							//str+='<td>'+result[i].typeOfIssue+'</td>';
							//str+='<td><p></p>';
							
							//str+='</td>';
							//str+='<td><input type="button" value="View" class="btn btn-sm btn-primary complaintTrackingCla" onclick="getComplaintTrackingDetails('+result[i].complaintId+',\'statusDivIdForInsurance'+i+'\')"/></td>';
						//str+='</tr>';
						//str+='<tr id="trstatusDivIdForInsurance'+i+'" style="display:none;"><td colspan="6"><div id="statusDivIdForInsurance'+i+'"></div></td></tr>';
					}
					str+='</tbody>'
				str+='</table>';
				str+='</div>';
				
				$("#dataLoadingsImgForDeathHospDetails").hide();
				$("#deathHospModalBodyId").html(str);
				$("#deathHospLifeCycleTableId").dataTable();
				$("#deathHospLifeCycleTableId").removeClass("dataTable");
				$("#deathHospLifeCycleTableId_wrapper").css("margin-top","5px");
			}
		});
});
	
function getPartyMeetingDetails()
{
	//$('#partyMeetingDescDiv').html('');
	var jsObj={
		tdpCadreId:globalCadreId		
	}
/*	
	$.ajax({
			type:'POST',
			 url: 'getPartyMeetingDetailsForCadre.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result != null && result.partyMeetingVOList != null && result.partyMeetingVOList.length >0)
				{
					var str='';
					str+='<table class="table m_0 table-bordered">';
					str+='<thead>';
					str+='<th class="text-center" colspan="3">PARTY MEETINGS</th>';
					str+='</thead>';
					str+='<tr class="text-center">';
					var myResult = result.partyMeetingVOList[0];

						str+='<td>'+myResult.invitedCount+'<br/>Invited</td>';						
						if(parseInt(myResult.invitedCount) > parseInt(myResult.attendedCount))
						{
							var absentCount = parseInt(myResult.invitedCount) - parseInt(myResult.attendedCount);
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						else if(parseInt(myResult.invitedCount) < parseInt(myResult.attendedCount))
						{
							var absentCount = 0;
							str+='<td>'+parseInt(myResult.attendedCount)+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						else
						{
							var absentCount = parseInt(myResult.invitedCount) - parseInt(myResult.attendedCount);
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						str+='</tr>';
					str+='</table>';

					$('#partyMeetingDescDiv').html(str);
				}
			});
			*/
}
function getPartyMeetingDetaildReprt()
{
	$('#partyMetindetlsDivId').html('');
	$("#meetingParticipatingDivModalBodyId").html('');
	var jsObj={
		tdpCadreId:globalCadreId
	}	
	$.ajax({
			type:'POST',
			 url: 'getPartyMeetingDetailsForCadre.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result != null && result.partyMeetingVOList != null && result.partyMeetingVOList.length >0)
				{
					var invitedCount =0;
					var attendCount =0;
					var abentCount =0;
					
					$('#partymettingParlDivId').show();
					$('#partyMeetingDetailsShowHideDiv').show();
					var str='';
					str+='<table class="table m_0 table-bordered" style="margin-top:10px;">';
					str+='<thead>';
					//str+='<tr>';
					//str+='<th colspan="4" style="background-color:#CCCCCC;text-align:center;"> PARTY MEETINGS PARTICIPATION DETAILS </th>';						
					//str+='</tr>'; 
					str+='<tr>';						
					str+='<th class="text-center"> MEETING NAME </th>';
					str+='<th class="text-center"> INVITED </th>';
					str+='<th class="text-center"> ATTENDED </th>';
					str+='<th class="text-center"> NOT ATTENDED </th>';								
					str+='</tr>';
					str+='</thead>';
					
					for(var i in result.partyMeetingVOList)
					{
						if(result.partyMeetingVOList[i].invitedCount != null)
							invitedCount = invitedCount +parseInt(result.partyMeetingVOList[i].invitedCount);
						if(result.partyMeetingVOList[i].attendedCount != null)
							attendCount = attendCount +parseInt(result.partyMeetingVOList[i].attendedCount);
						
						str+='<tr class="text-center">';
						str+='<td style="text-align:left;">'+result.partyMeetingVOList[i].location+' - '+result.partyMeetingVOList[i].name+' </td>';
						str+='<td> <ul class="list-inline"><li class="invitedDetlsDiv" style="cursor:pointer;color:#13759D" name="invitedDetlsDiv'+i+'" key="'+result.partyMeetingVOList[i].id+'" attr_name="'+result.partyMeetingVOList[i].location+' - '+result.partyMeetingVOList[i].name+'">'+result.partyMeetingVOList[i].invitedCount+'';
						str+='<ul class="count-hover left_arrow" >';
						str+='<li>';
							str+='<div id="invitedDetlsDiv'+i+'" class="invitationCls"></div>';
						str+='</li>';
						str+='</ul>';
						
						str+='</td>';
						
						if(parseInt(result.partyMeetingVOList[i].invitedCount) > parseInt(result.partyMeetingVOList[i].attendedCount))
						{
							var absentCount = parseInt(result.partyMeetingVOList[i].invitedCount) - parseInt(result.partyMeetingVOList[i].attendedCount);
							str+='<td> '+result.partyMeetingVOList[i].attendedCount+' </td>';
							str+='<td> '+absentCount+' </td>';
							abentCount =abentCount+absentCount;
						/*str+='<td> <ul class="list-inline"><li class="show-dropdown"><u style="color:#23527C;">'+absentCount+'</u>';
						str+='<ul class="count-hover right_arrow" >';
						str+='<li>';
						str+='<table class="table table-bordered" style="margin: 10px">';
						str+='<thead>';
						
						str+='<tr>';
						//str+='<th style=""> MEETING NAME </th>';
						str+='<th style=""> LOCATION </th>';
						str+='<th style=""> TIME </th>';
						str+='<th style=""> STATUS </th>';
						str+='</tr>';
						str+='<thead>';
						str+='<tbody>';
						if(absetPArtyMeetingsArr != null && absetPArtyMeetingsArr.length>0)
						{
							for(var k in absetPArtyMeetingsArr)
							{
								str+='<tr>';
									//str+='<td> '+absetPArtyMeetingsArr[k].meetingName+'</td>';
									str+='<td> '+absetPArtyMeetingsArr[k].location+' </td>';
									str+='<td> '+absetPArtyMeetingsArr[k].time+' </td>';
									str+='<td> '+absetPArtyMeetingsArr[k].status+' </td>';
								str+='</tr>';
							}
						}
						str+='</tbody>';
						str+='</table>';
						str+='</li>';
						str+='</ul> </td>';*/
						}
						else if(parseInt(result.partyMeetingVOList[i].invitedCount) < parseInt(result.partyMeetingVOList[i].attendedCount))
						{
							var absentCount = 0;
							str+='<td> '+result.partyMeetingVOList[i].attendedCount+' </td>';
							str+='<td> '+absentCount+' </td>';
						}
						else
						{
							var absentCount = 0;
							str+='<td> '+result.partyMeetingVOList[i].attendedCount+' </td>';
							str+='<td> '+absentCount+' </td>';							

						}
						str+='</tr>';
					}
					
					str+='</table>';


					$('#partyMetindetlsDivId').html(str);
					
					/*str='';
					$(".invitedDetlsDiv").hover(function(){
						var divId = $(this).attr('name');
						var meetingTypeId = $(this).attr('key');
						var jsObj={
							tdpCadreId:globalCadreId,
							meetingTypeId:meetingTypeId
						}	
						$.ajax({
								type:'POST',
								 url: 'getPartyMeetingTypeWiseDetails.action',
								 data : {task:JSON.stringify(jsObj)} ,
								}).done(function(result){
									var str='';
									if(result != null && result.partyMeetingVOList != null && result.partyMeetingVOList.length >0)
									{//srish
										str+='<table class="table table-bordered" style="margin: 10px">';
										str+='<thead>';
										str+='<tr>';
										//str+='<th style=""> LOCATION </th>';
										str+='<th style=""> MEETING NAME  </th>';
										str+='<th style=""> START DATE</th>';
										str+='<th style=""> END DATE</th>';
										str+='</tr>';
										str+='<thead>';
										str+='<tbody>';
										if(result.partyMeetingVOList != null && result.partyMeetingVOList.length>0)
										{
											for(var k in result.partyMeetingVOList)
											{
												if(result.partyMeetingVOList[k].name != null ){
													str+='<tr>';
													//str+='<td>'+result.partyMeetingVOList[k].location+'</td>';
													str+='<td>'+result.partyMeetingVOList[k].name+'</td>';										
													str+='<td>'+result.partyMeetingVOList[k].startDateStr+'</td>';
													str+='<td>'+result.partyMeetingVOList[k].endDateStr+'</td>';
													str+='</tr>';
												}												
											}
										}
										str+='</tbody>';
										str+='</table>';
										
										$('#'+divId+'').html(str);
									}
								});
					});
					*/
					
					var str='';
					str+='<table class="table m_0 table-bordered">';
					str+='<thead>';
					str+='<th class="text-center" colspan="3">PARTY MEETINGS</th>';
					str+='</thead>';
					str+='<tr class="text-center">';
					str+='<td>'+invitedCount+'<br/>Invited</td>';	
					str+='<td>'+attendCount+'<br/>Attended</td>';	
					str+='<td>'+abentCount+'<br/>Absent</td>';	
					
					str+='</tr>';
					str+='</table>';

					$('#partyMeetingDescDiv').html(str);
					
				}
			});
}
getPartyMeetingsOverViewForCadre();
getEventsOverviewFortdpCadre();

var absetPArtyMeetingsArr = new Array();
function getPartyMeetingsOverViewForCadre()
{
	absetPArtyMeetingsArr=[];
	$('#partyMetindetlsDivId').html('');
	var jsObj={
		tdpCadreId:globalCadreId
	}	
	$.ajax({
			type:'POST',
			 url: 'getPartyMeetingsOverViewForCadre.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(myResult){
				if(myResult != null)
				{
					var str='';
					str+='<table class="table m_0 table-bordered">';
					str+='<thead>';
					str+='<th class="text-center" colspan="3">PARTY MEETINGS</th>';
					str+='</thead>';
					str+='<tr class="text-center">';
						str+='<td>'+myResult.invitedCount+'<br/>Invited</td>';						
						if(parseInt(myResult.invitedCount) > parseInt(myResult.attendedCount))
						{
							var absentCount = parseInt(myResult.invitedCount) - parseInt(myResult.attendedCount);
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						else if(parseInt(myResult.invitedCount) < parseInt(myResult.attendedCount))
						{
							var absentCount = 0;
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						else
						{
							var absentCount = parseInt(myResult.invitedCount) - parseInt(myResult.attendedCount);
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						str+='</tr>';
					str+='</table>';

					$('#partyMeetingDescDiv').html(str);
					
					var resultList = myResult.partyMeetingVOList;
					if(resultList != null && resultList.length>0)
					{
						for(var k in resultList)
						{
							var absentObj = {
								id:resultList[k].id,
								meetingName:resultList[k].meetingType,
								location:resultList[k].location,
								time:resultList[k].name,
								status : resultList[k].memberStatus
							};							
							absetPArtyMeetingsArr.push(absentObj);
						}
					}
					
					getPartyMeetingDetaildReprt();
				}
			});
}
function getEventsOverviewFortdpCadre()
{
	$('#eventInvitatinDiv').html('');
	var jsObj={
		tdpCadreId:globalCadreId
	}	
	$.ajax({
			type:'POST',
			 url: 'getEventsOverviewFortdpCadre.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(myResult){
				if(myResult != null)
				{
					var str='';
					str+='<table class="table m_0 table-bordered">';
					str+='<thead>';
					str+='<th class="text-center" colspan="3"> EVENTS INVITATION </th>';
					str+='</thead>';
					str+='<tr class="text-center">';
						str+='<td>'+myResult.invitedCount+'<br/>Invited</td>';						
						if(parseInt(myResult.invitedCount) > parseInt(myResult.attendedCount))
						{
							var absentCount = parseInt(myResult.invitedCount) - parseInt(myResult.attendedCount);
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						else if(parseInt(myResult.invitedCount) < parseInt(myResult.attendedCount))
						{
							var absentCount = 0;
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						else
						{
							var absentCount = parseInt(myResult.invitedCount) - parseInt(myResult.attendedCount);
							str+='<td>'+myResult.attendedCount+'<br/>Attended</td>';
							str+='<td>'+absentCount+'<br/>Absent</td>';
						}
						str+='</tr>';
					str+='</table>';

					$('#eventInvitatinDiv').html(str);
				}
			});
}
function getNtrTrustStudentDetailsInstitutionWise(type){
		
		$(".dataLoadingsImgForTabSection").show();
		$("#cadreIdSpanForEducationBenefit").html("");
		$("#familyIdSpanForEducationBenefit").html("");
		
		cadreId = globalCadreId;
		
		var cadreIdsArr=[];
		if(type == "cadre"){
			cadreIdsArr.push(cadreId);
			var jsObj={	
				cadreIdsArr:cadreIdsArr
			}	
		}
		else{
			var jsObj={	
				cadreIdsArr:familycadreIdsArrayGlobal
			}
		}
		
		$("#benefitsEducationCountsId").html("");
		
		
		/* var jsObj={
			tdpCadreId:cadreId,
			familyCadreIds:familycadreIdsArrayGlobal
		}	 */
		$.ajax({
				type:'POST',
				 url: 'getNtrTrustStudentDetailsInstitutionWiseAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					
					$(".dataLoadingsImgForTabSection").hide();
					
					var str='';
					if(result !=null){
						if(type == "cadre"){
							if(result.count !=null && result.count>0){
								//var rsltLst =  result.ntrTrustStudentVoList;
								$("#candidateEducationBenefitDiv").show();
									buildingStudentDetailsInstitutionWiseOfCadre(result,type);
							}
							else{
								if(result.count ==null){
									result.count =0;
								}
								$("#candidateEducationBenefitDiv").hide();
								$("#cadreIdSpanForEducationBenefit").html('NTR TRUST EDUCATION BENEFITS<ul class="pull-right"><li>'+result.count+'</li></ul>');
							}
						}
						if(type == "family"){
							if(result.count !=null && result.count>0){
								    $("#familyEducationBenefitDiv").show();
									buildingStudentDetailsInstitutionWiseOfFamily(result,type);
							}else{
								if(result.count ==null){
									result.count =0;
								}
								$("#familyEducationBenefitDiv").hide();
								$("#familyIdSpanForEducationBenefit").html('NTR TRUST EDUCATION BENEFITS<ul class="list-inline pull-right"><li>'+result.count+'</li></ul>');
							}
						}
					}
					
			});
	}
function buildingStudentDetailsInstitutionWiseOfCadre(result,type){
		str='';
		if(result !=null){
			if(type =="cadre"){
			$("#cadreIdSpanForEducationBenefit").html('NTR TRUST EDUCATION BENEFITS<ul class="pull-right list-inline showStudentBenefitModalcls" attr_title="NTR TRUST EDUCATION BENEFITS" attr_type="'+type+'"><li><a style="cursor:pointer;">'+result.count+'</a></li></ul>');
			}
			
			if(result.ntrTrustStudentVoList !=null && result.ntrTrustStudentVoList.length>0){
				str+='<div class="panel-group" id="accordionNtrTrust1" role="tablist" aria-multiselectable="true">';
				
						for(var i in result.ntrTrustStudentVoList){
							str+='<div class="panel panel-default">';
							str+='<div class="panel-heading">';
							
							str+='<a role="button" data-toggle="collapse" data-parent="#accordionNtrTrust1" href="#collapseNtrOne'+i+'" aria-expanded="true" aria-controls="collapseNtrOne'+i+'" class="ntrBenefitCountCls" attr_id="'+result.ntrTrustStudentVoList[i].id+'" attr_type="'+type+'" style="cursor:pointer" >';
							str+='<h4 class="panel-title" id="headingNtrOne'+i+'">'+result.ntrTrustStudentVoList[i].name+'';
							str+='<span class=" pull-right">'+result.ntrTrustStudentVoList[i].count+'</span>';
							str+='</h4>';
							str+='</a>';
							str+='</div>';
							str+='<div id="collapseNtrOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingNtrOne'+i+'">';
							str+='<div class="panel-body">';
							str+='<center><img id="dataLoadingImageForModelSchoolCadre" src="images/icons/loading.gif" style="width:50px;height:50px;margin-top:50px;display:none"/></center>';
							str+='<div class="NtrTrustStudentDivClass">';
								
							str+='</div>';
							str+='</div>';
							str+='</div>';
							str+='</div>';
						}
				str+='</div>';
				$("#benefitsEducationCountsId").html(str);
			}		
			
		}
	}
	function buildingStudentDetailsInstitutionWiseOfFamily(result,type){
		str='';
		if(result !=null){
			$("#familyIdSpanForEducationBenefit").html('NTR TRUST EDUCATION BENEFITS<span class="pull-right showStudentBenefitModalclsFamily" attr_title="NTR TRUST EDUCATION BENEFITS" attr_type="'+type+'"><a style="cursor:pointer;">'+result.count+'</a></span>');

			
			if(result.ntrTrustStudentVoList !=null && result.ntrTrustStudentVoList.length>0){
				str+='<div class="panel-group" id="accordionNtrTrustFamily" role="tablist" aria-multiselectable="true">';
				
						for(var i in result.ntrTrustStudentVoList){
							str+='<div class="panel panel-default">';
							str+='<div class="panel-heading">';
							
							str+='<a role="button" data-toggle="collapse" data-parent="#accordionNtrTrustFamily" href="#collapseNtrOneFamily'+i+'" aria-expanded="true" aria-controls="collapseNtrOneFamily'+i+'" class="ntrBenefitCountClsFamily" attr_id="'+result.ntrTrustStudentVoList[i].id+'" attr_type="'+type+'" style="cursor:pointer" >';
							str+='<h4 class="panel-title" id="headingNtrOneFamily'+i+'">'+result.ntrTrustStudentVoList[i].name+'';
							str+='<span class=" pull-right">'+result.ntrTrustStudentVoList[i].count+'</span>';
							str+='</h4>';
							str+='</a>';
							str+='</div>';
							str+='<div id="collapseNtrOneFamily'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingNtrOneFamily'+i+'">';
							str+='<div class="panel-body">';
							str+='<center><img id="dataLoadingImageForModelSchoolFamily" src="images/icons/loading.gif" style="width:50px;height:50px;margin-top:50px;display:none"/></center>';
							str+='<div class="NtrTrustStudentDivClassFamily">';
								
							str+='</div>';
							str+='</div>';
							str+='</div>';
							str+='</div>';
						}
				str+='</div>';
				$("#benefitsEducationCountsFamilyId").html(str);
			}		
			
		}
	}
	
function getStudentFormalDetailsByCadre(institutionId,type){
		if(type =="cadre"){
			$(".NtrTrustStudentDivClass").html("");
			$("#dataLoadingImageForModelSchoolCadre").show();
		}else{
			$(".NtrTrustStudentDivClassFamily").html("");
			$("#dataLoadingImageForModelSchoolFamily").show();
		}
		
		cadreId = globalCadreId;
		
		var cadreIds=[];
		if(type == "cadre"){
			cadreIds.push(cadreId);
			var jsObj={	
				cadreIds:cadreIds,
				institutionId:institutionId
			}	
		}
		else{
			var jsObj={	
				cadreIds:familycadreIdsArrayGlobal,
				institutionId:institutionId
			}
		}
		$.ajax({
				type:'POST',
				 url: 'getStudentFormalDetailsByCadreAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					
					//$("#dataLoadingsImgForNtrTrust").hide();
					if(type =="cadre"){
						$("#dataLoadingImageForModelSchoolCadre").hide();
					}else{
						$("#dataLoadingImageForModelSchoolFamily").hide();
					}
					var str='';
					if(result !=null && result.length>0){
						if(type =="cadre"){
							str+='<div class="panel-group" style="margin:0" id="accordion323" role="tablist" aria-multiselectable="true">';
						}
						else{
							str+='<div class="panel-group" style="margin:0" id="accordion3255" role="tablist" aria-multiselectable="true">';
						}
						
						for(var i in result){
						  str+='<div class="panel panel-default" style="margin-top:0px">';
						
							if(type == "cadre"){
								str+='<div class="panel-heading" style="background-color:#f4f4f4" role="tab" id="headingOne'+i+'">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion323" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
									str+='<h4 class="panel-title" id="'+result[i].id+'">'+result[i].name+'</h4>';
								str+='</a>';
							}
							else{
								str+='<div class="panel-heading" style="background-color:#f4f4f4" role="tab" id="headingTwo'+i+'">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion3255" href="#collapsetwo'+i+'" aria-expanded="true" aria-controls="collapsetwo'+i+'">';
									str+='<h4 class="panel-title" id="'+result[i].id+'">'+result[i].name+'</h4>';
								str+='</a>';
							}
								
							str+='</div>';
							if(type == "cadre"){
								str+='<div id="collapseOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+'">';
							}
							else{
								str+='<div id="collapsetwo'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo'+i+'">';
							}
							  str+='<div class="panel-body">';
								str+='<ul class="list-inline">';
									str+='<li><b>Father Name </b> : '+result[i].fatherName+'</li>';
									str+='<li><b>Mother Name </b> :'+result[i].motherName+'</li>';
									if(result[i].guardian !=null && result[i].guardian !=""){
										str+='<li><b>Guardian </b> :'+result[i].guardian+'</li>';
									}
									str+='<li><b>Caste </b>:'+result[i].casteStr+'</li>';
									str+='<li><b>Date Of Birth </b> :'+result[i].dateStr+'</li>';
									if(result[i].yearOfJoining !=null && result[i].yearOfJoining !=""){
										str+='<li><b>Year Of Joining </b> :'+result[i].yearOfJoining+'</li>';
									}
									if(result[i].tdpCadreId !=null && result[i].tdpCadreId >0){
										str+='<li><b>Cadre Id </b> :'+result[i].tdpCadreId+'</li>';
									}
									if(result[i].membershipNo !=null && result[i].membershipNo>0){
										str+='<li><b>MemberShip No </b> :'+result[i].membershipNo+'</li>';
									}
									str+='<li><b>Relation With Cadre</b> :'+result[i].relation+'</li>';
								str+='</ul>'
								
								str+='<h4 class="m_0" style="color:#a94442 !important"><i class="glyphicon glyphicon-map-marker text-danger" style="font-size:14px"></i>Communication Address</h4><hr class="m_0"/>'
								
								for(var j in result[i].addressDetailsList){
									str+='<ul class="Student-List">';
									if(result[i].addressDetailsList[j].houseNoStr !=null && result[i].addressDetailsList[j].houseNoStr !=""){
										str+='<li><b>H NO</b> : '+result[i].addressDetailsList[j].houseNoStr+'</li>';
									}
									if(result[i].addressDetailsList[j].stateStr !=null && result[i].addressDetailsList[j].stateStr !=""){
										str+='<li><b>State</b> : '+result[i].addressDetailsList[j].stateStr+'</li>';
									}
									if(result[i].addressDetailsList[j].districtStr !=null && result[i].addressDetailsList[j].districtStr !=""){
										str+='<li><b>District</b> : '+result[i].addressDetailsList[j].districtStr+'</li>';
									}
									if(result[i].addressDetailsList[j].constituencyStr !=null && result[i].addressDetailsList[j].constituencyStr !=""){
										str+='<li><b>Constituency</b> : '+result[i].addressDetailsList[j].constituencyStr+' </li>';
									}
									if(result[i].addressDetailsList[j].tehsilStr !=null && result[i].addressDetailsList[j].tehsilStr !=""){
										str+='<li><b>Mandal</b> : '+result[i].addressDetailsList[j].tehsilStr+'</li>';
									}
									if(result[i].addressDetailsList[j].localElectionBodyStr !=null && result[i].addressDetailsList[j].localElectionBodyStr !=""){
										str+='<li><b>Muncipality</b> : '+result[i].addressDetailsList[j].localElectionBodyStr+' </li>';
									}
									if(result[i].addressDetailsList[j].panchayatStr !=null && result[i].addressDetailsList[j].panchayatStr !=""){
										str+='<li><b>Panchayat</b> : '+result[i].addressDetailsList[j].panchayatStr+'  </li>';
									}
									if(result[i].addressDetailsList[j].wardStr !=null && result[i].addressDetailsList[j].wardStr !=""){
										str+='<li><b>Ward</b> : '+result[i].addressDetailsList[j].wardStr+' Ward</li>';
									}
									if(result[i].addressDetailsList[j].locationStr !=null && result[i].addressDetailsList[j].locationStr !=""){
										str+='<li><b>Location</b> : '+result[i].addressDetailsList[j].locationStr+' </li>';
									}
									if(result[i].addressDetailsList[j].streetStr !=null && result[i].addressDetailsList[j].streetStr !=""){
										str+='<li><b>Street</b> : '+result[i].addressDetailsList[j].streetStr+' </li>';
									}
									if(result[i].addressDetailsList[j].pincodeLng !=null && result[i].addressDetailsList[j].pincodeLng>0){
										str+='<li><b>Pincode</b> : '+result[i].addressDetailsList[j].pincodeLng+'</li>';
									}
									
									for(var l in result[i].ntrTrustStudentVoList){
											str+='<li><b>Contact:</b>'+result[i].ntrTrustStudentVoList[l].phoneNo+'('+result[i].ntrTrustStudentVoList[l].phoneType+')';
											str+='</li>';
										}
									str+='</ul>';
								}
								str+='<h4 class="m_0" style="color:#a94442 !important"><i class="glyphicon glyphicon-book text-danger" style="font-size:14px"></i> Acedamic Details</h4><hr class="m_0"/>';
								str+='<table class="table table-bordered">';
									str+='<thead>';
										str+='<th style="background-color:#f5f5f5">Course</th>';
										str+='<th style="background-color:#f5f5f5">Start Date</th>';
										str+='<th style="background-color:#f5f5f5">End Date</th>';
									str+='</thead>';
									for(var k in result[i].academicDetailsList){
										str+='<tr>';
											str+='<td>'+result[i].course+'</td>';
											str+='<td>'+result[i].academicDetailsList[k].startMonth+' '+result[i].academicDetailsList[k].startYear+'</td>';
											str+='<td>'+result[i].academicDetailsList[k].endMonth+' '+result[i].academicDetailsList[k].endYear+'</td>';
										str+='</tr>';
									}
																		
								str+='</table>';
								
								str+='<h4 class="m_0" style="color:#a94442 !important"><i class="glyphicon glyphicon-share text-danger" style="font-size:14px"></i> Recommendation Details</h4><hr class="m_0"/>';
								str+='<table class="table table-bordered">';
								  str+='<thead>';
									str+='<th style="background-color:#f5f5f5">Person Name</th>';
									str+='<th style="background-color:#f5f5f5">Designation</th>';
									str+='<th style="background-color:#f5f5f5">Contact</th>';
									str+='<th style="background-color:#f5f5f5">MemberShip No</th>';
								  str+='</thead>';
								  for(var b in result[i].recomendationDetailsList){
									str+='<tr>';
										str+='<td>'+result[i].recomendationDetailsList[b].name+'</td>';
										str+='<td>'+result[i].recomendationDetailsList[b].designation+'</td>';
										str+='<td>'+result[i].recomendationDetailsList[b].phoneNo+'</td>';
										if(result[i].recomendationDetailsList[b].membershipNo !=null && result[i].recomendationDetailsList[b].membershipNo >0){
											str+='<td>'+result[i].recomendationDetailsList[b].membershipNo+'</td>';
										}else{
											str+='<td>-</td>';
										}
										
									str+='</tr>';
								  }
								 
								str+='</table>';
							  str+='</div>';
							str+='</div>';
						  str+='</div>';
						}
						str+='</div>';
					}
					if(type=="cadre"){
						$(".NtrTrustStudentDivClass").html(str);
					}else{
						$(".NtrTrustStudentDivClassFamily").html(str);
					}
				});
	}
/* function buildNtrStudentDetails(result){
		var str='';
			if(result !=null){
				
				if(type=="family"){
					 //str+='<h4 class="m_0 text-primary">Family</h4>';
					 str+='<div class="panel-group" style="margin-top:10px" id="accordion324" role="tablist" aria-multiselectable="true">';
				}
				if(type=="cadre"){
					// str+='<h4 class="m_0 text-primary">Cadre</h4>';
					str+='<div class="panel-group" style="margin-top:10px" id="accordion323" role="tablist" aria-multiselectable="true">';
				}
				
						for(var i in result){
						  str+='<div class="panel panel-default" style="margin:0px">';
						  
						  if(type == "family"){
							  str+='<div class="panel-heading" role="tab" id="headingTwo'+i+'">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion324" href="#collapseTwo'+i+'" aria-expanded="true" aria-controls="collapseTwo'+i+'">';
									str+='<h4 class="panel-title" id="'+result[i].id+'">'+result[i].name+'</h4>';
								str+='</a>';
							str+='</div>';
							str+='<div id="collapseTwo'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo'+i+'">';
						  }
						  if(type=="cadre"){
							str+='<div class="panel-heading" role="tab" id="headingOne'+i+'">';
								str+='<a role="button" data-toggle="collapse" data-parent="#accordion323" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
									str+='<h4 class="panel-title" id="'+result[i].id+'">'+result[i].name+'</h4>';
								str+='</a>';
							str+='</div>';
							str+='<div id="collapseOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+'">';
						  }
							  str+='<div class="panel-body">';
								str+='<ul class="list-inline">';
									str+='<li><b>Father Name </b> : '+result[i].fatherName+'</li>';
									str+='<li><b>Mother Name </b> :'+result[i].motherName+'</li>';
									if(result[i].guardian !=null && result[i].guardian !=""){
										str+='<li><b>Guardian </b> :'+result[i].guardian+'</li>';
									}
									str+='<li><b>Caste </b>:'+result[i].casteStr+'</li>';
									str+='<li><b>Date Of Birth </b> :'+result[i].dateStr+'</li>';
									if(result[i].yearOfJoining !=null && result[i].yearOfJoining !=""){
										str+='<li><b>Year Of Joining </b> :'+result[i].yearOfJoining+'</li>';
									}
									if(result[i].tdpCadreId !=null && result[i].tdpCadreId >0){
										str+='<li><b>Cadre Id </b> :'+result[i].tdpCadreId+'</li>';
									}
									if(result[i].membershipNo !=null && result[i].membershipNo>0){
										str+='<li><b>MemberShip No </b> :'+result[i].membershipNo+'</li>';
									}
									str+='<li><b>Relation With Cadre</b> :'+result[i].relation+'</li>';
								str+='</ul>'
								
								str+='<h4 class="m_0" style="color:#a94442 !important "><i class="glyphicon glyphicon-map-marker text-danger" style="font-size:14px"></i>Communication Address</h4><hr class="m_0"/>'
								
								for(var j in result[i].addressDetailsList){
									str+='<ul class="Student-List">';
									if(result[i].addressDetailsList[j].houseNoStr !=null && result[i].addressDetailsList[j].houseNoStr !=""){
										str+='<li><b>H NO</b> : '+result[i].addressDetailsList[j].houseNoStr+'</li>';
									}
									if(result[i].addressDetailsList[j].stateStr !=null && result[i].addressDetailsList[j].stateStr !=""){
										str+='<li><b>State</b> : '+result[i].addressDetailsList[j].stateStr+'</li>';
									}
									if(result[i].addressDetailsList[j].districtStr !=null && result[i].addressDetailsList[j].districtStr !=""){
										str+='<li><b>District</b> : '+result[i].addressDetailsList[j].districtStr+'</li>';
									}
									if(result[i].addressDetailsList[j].constituencyStr !=null && result[i].addressDetailsList[j].constituencyStr !=""){
										str+='<li><b>Constituency</b> : '+result[i].addressDetailsList[j].constituencyStr+' </li>';
									}
									if(result[i].addressDetailsList[j].tehsilStr !=null && result[i].addressDetailsList[j].tehsilStr !=""){
										str+='<li><b>Mandal</b> : '+result[i].addressDetailsList[j].tehsilStr+'</li>';
									}
									if(result[i].addressDetailsList[j].localElectionBodyStr !=null && result[i].addressDetailsList[j].localElectionBodyStr !=""){
										str+='<li><b>Muncipality</b> : '+result[i].addressDetailsList[j].localElectionBodyStr+' </li>';
									}
									if(result[i].addressDetailsList[j].panchayatStr !=null && result[i].addressDetailsList[j].panchayatStr !=""){
										str+='<li><b>Panchayat</b> : '+result[i].addressDetailsList[j].panchayatStr+'  </li>';
									}
									if(result[i].addressDetailsList[j].wardStr !=null && result[i].addressDetailsList[j].wardStr !=""){
										str+='<li><b>Ward</b> : '+result[i].addressDetailsList[j].wardStr+' Ward</li>';
									}
									if(result[i].addressDetailsList[j].locationStr !=null && result[i].addressDetailsList[j].locationStr !=""){
										str+='<li><b>Location</b> : '+result[i].addressDetailsList[j].locationStr+' </li>';
									}
									if(result[i].addressDetailsList[j].streetStr !=null && result[i].addressDetailsList[j].streetStr !=""){
										str+='<li><b>Street</b> : '+result[i].addressDetailsList[j].streetStr+' </li>';
									}
									if(result[i].addressDetailsList[j].pincodeLng !=null && result[i].addressDetailsList[j].pincodeLng>0){
										str+='<li><b>Pincode</b> : '+result[i].addressDetailsList[j].pincodeLng+'</li>';
									}
									
									for(var l in result[i].ntrTrustStudentVoList){
											str+='<li><b>Contact:</b>'+result[i].ntrTrustStudentVoList[l].phoneNo+'('+result[i].ntrTrustStudentVoList[l].phoneType+')';
											str+='</li>';
										}
									str+='</ul>';
								}
								str+='<h4 class="m_0" style="color:#a94442 !important"><i class="glyphicon glyphicon-book text-danger" style="font-size:14px"></i> Acadamic Details</h4><hr class="m_0"/>';
								str+='<table class="table table-bordered">';
									str+='<thead>';
										str+='<th style="background-color:#f5f5f5">Course</th>';
										str+='<th style="background-color:#f5f5f5">Start Date</th>';
										str+='<th style="background-color:#f5f5f5">End Date</th>';
									str+='</thead>';
									for(var k in result[i].academicDetailsList){
										str+='<tr>';
											str+='<td>'+result[i].course+'</td>';
											str+='<td>'+result[i].academicDetailsList[k].startMonth+' '+result[i].academicDetailsList[k].startYear+'</td>';
											str+='<td>'+result[i].academicDetailsList[k].endMonth+' '+result[i].academicDetailsList[k].endYear+'</td>';
										str+='</tr>';
									}
																		
								str+='</table>';
								
								str+='<h4 class="m_0" style="color:#a94442 !important"><i class="glyphicon glyphicon-share text-danger" style="font-size:14px"></i> Recommendation Details</h4><hr class="m_0"/>';
								str+='<table class="table table-bordered">';
								  str+='<thead>';
									str+='<th style="background-color:#f5f5f5">Person Name</th>';
									str+='<th style="background-color:#f5f5f5">Designation</th>';
									str+='<th style="background-color:#f5f5f5">Contact</th>';
									str+='<th style="background-color:#f5f5f5">MemberShip No</th>';
								  str+='</thead>';
								  for(var b in result[i].recomendationDetailsList){
									str+='<tr>';
										str+='<td>'+result[i].recomendationDetailsList[b].name+'</td>';
										str+='<td>'+result[i].recomendationDetailsList[b].designation+'</td>';
										str+='<td>'+result[i].recomendationDetailsList[b].phoneNo+'</td>';
										if(result[i].recomendationDetailsList[b].membershipNo !=null && result[i].recomendationDetailsList[b].membershipNo >0){
											str+='<td>'+result[i].recomendationDetailsList[b].membershipNo+'</td>';
										}else{
											str+='<td>-</td>';
										}
										
									str+='</tr>';
								  }
								 
								str+='</table>';
							  str+='</div>';
							str+='</div>';
						  str+='</div>';
						}
						str+='</div>';
					}
			if(type =="cadre"){
				$("#ntrTrustDetails").html(str);
			}else if(type == "family"){
				$("#ntrTrustCadreFamilyDetails").html(str);
			} 
		
	}*/
	
	var candiConstiSurveyCount = 0;
function getCandidateAndConstituencySurveyResult()
{
	$('.surveyDetailsCls').html("");
	$('#surveyDataLoadoing').show();
	
	var candidateId = globalCandidateId;
	var constituencyId = 0;
	
	if(participatedConstituencyId != null && participatedConstituencyId > 0){
		constituencyId = participatedConstituencyId;
	}else if(globalConstituencyId > 0){
		constituencyId = globalConstituencyId;
	}
	var surveyId = 0;
	if (typeof(candidateId) != "undefined"){
		var jsObj={
				candidateId:candidateId,
				constituencyId:constituencyId,
				surveyId:surveyId
		}
		$.ajax({
			type:'GET',
			url :'getCandidateAndConstituencySurveyResultAction.action',
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			if(result != null){
				if(result.length != null && result.length > 0){
					candiConstiSurveyCount = result.length;
				}
				buildCandidateAndConstituencySurveyResult(result,surveyId,null);
				getTdpCadreSurveyDetails(globalCadreId,0,null,"NotAll",0,'true');
			}
		});
	}
}
function getCandidateAndConstituencySurveyResultBySurvey(surveyId,divId){
	var candidateId = globalCandidateId;
	var constituencyId = 0;
	
	if(participatedConstituencyId != null && participatedConstituencyId > 0){
		constituencyId = participatedConstituencyId;
	}else if(globalConstituencyId > 0){
		constituencyId = globalConstituencyId;
	}
	var surveyId = surveyId;
	//$("#"+divId+"").html("");
	if (typeof(candidateId) != "undefined"){
		var jsObj={
				candidateId:candidateId,
				constituencyId:constituencyId,
				surveyId:surveyId
		}
		$.ajax({
			type:'GET',
			url :'getCandidateAndConstituencySurveyResultAction.action',
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			//alert(222);
			if(result != null){
				buildCandidateAndConstituencySurveyResult(result,surveyId,divId);
				
			}
		});
	}
}

function buildCandidateAndConstituencySurveyResult(result,surveyId,divId){
	if(result != null && result.length > 0){
		if(surveyId == 0){
			var str='';
			str+='<div class="panel-group" style="margin-top:20px" id="accordion121" role="tablist" aria-multiselectable="true">';
			for(var i in result){
			 str+='<div class="panel panel-default">';
					str+='<div class="panel-heading" role="tab" id="headingOne'+i+'" attr_survy_divId="candiConstSurveyId'+i+'" onclick="getCandidateAndConstituencySurveyResultBySurvey('+result[i].surveyId+',\'candiConstSurveyId'+i+'\')">';
						str+='<a role="button" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion121" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
							str+='<h4 class="panel-title">';
								str+=''+result[i].surveyName+'';
							str+='</h4>';
						str+='</a>';
					str+='</div>';
					
					str+='<div id="collapseOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+'">';
						str+='<center><img id="candiConstSurveyAjaxId'+result[i].surveyId+'" src="images/icons/survey-details.gif" style="width:250px;height:200px;"/></center>';
						str+='<div class="panel-body">';
						str+='<div  id="candiConstSurveyId'+i+'"></div>';
					 str+='</div>';
					str+='</div>';
				  str+='</div>';
			}
			str+='</div>';
			
			$('.surveyDetailsCls').html(str);
			$('#surveyDataLoadoing').hide();
		}else if(surveyId > 0){
		var temp="candiConstSurveyAjaxId"+surveyId+"";
		var str='';
		for(var i in result){
				  if(result[i].questions != null){
					for(var j in result[i].questions)
					{
						str+='<table class="table table-bordered">';
						str+='<tr><td colspan="3" style="text-weight:bold">'+result[i].questions[j].question+'</td></tr>';
						str+='<b><tr><td>Option</td><td>Count</td><td>Percentage</td></tr></b>';
						for(var k in result[i].questions[j].options){
							str+='<tr>';
								str+='<td>'+result[i].questions[j].options[k].optionVal+'</td>';
								str+='<td>'+result[i].questions[j].options[k].count+'</td>';
								str+='<td>'+result[i].questions[j].options[k].percentage+'</td>';
							str+='</tr>';
						}
						str+='</table>';
					}
					}
					}
				  $("#"+temp+"").hide();
				  $("#"+divId+"").html(str);
				 
		}
		
	}else{
		$('#surveyDataLoadoing').hide();
		$('.surveyDetailsCls').html("NO DATA AVAILABLE");
	}
}
function getIVRDetails()
{
	$("#ivrsurveyDataLoadoing").show();
	$("#ivrSurveysMainDivId").show();	
	var candidateId = globalCandidateId;//290951
	
	$.ajax({
		url: wurl+"/Survey/WebService/getCandidateIVRResult/"+candidateId+""
	}).then(function(result) {
		$("#ivrsurveyDataLoadoing").hide();
		if(result != null && result.length > 0){
			buildPublicScoreTable(result);
		}else{
			
			$("#ivrSurveysMainDivId").hide();			
			$(".ivrDetailsCls").html("NO DATA AVAILABLE");
		}
	});
}

function buildPublicScoreTable(myResult)
{
	$(".ivrDetailsCls").html("");
	var str ="";
	if(myResult!=null){
		if(myResult.length>0){
			for(var i in myResult){
					result = myResult[i];
					
						str+="<h5 style='font-weight:bold'> ROUND - "+result.round+"</h5>";
						str+='<table style="margin:5px;" class="gridtable"  border="1"  width="95%">';
						str+='<thead>';
						str+='<tr>';
								str+='<th>Total Dialed</th>';
								str+='<th>Answered</th>';
								str+='<th>Feedback</th>';
								if(result.option1!=null){
									if(result.topPrior == "Option1"){
										if(result.option1Name!=null){
											str+='<th style="background:green">'+result.option1Name+'</th>';
										}else{
											str+='<th style="background:green">Option1</th>';
										}
									}else{
										if(result.option1Name!=null){
											str+='<th>'+result.option1Name+'</th>';
										}else{
											str+='<th>Option1</th>';
										}
									}
								}
								if(result.option2!=null){
									if(result.topPrior == "Option2"){
										if(result.option2Name!=null){
											str+='<th style="background:green">'+result.option2Name+'</th>';
										}else{
											str+='<th style="background:green">Option2</th>';
										}
									}else{
										if(result.option2Name!=null){
											str+='<th>'+result.option2Name+'</th>';
										}else{
											str+='<th>Option2</th>';
										}
									}
								}
								if(result.option3!=null){
									if(result.topPrior == "Option3"){
										if(result.option3Name!=null){
											str+='<th style="background:green">'+result.option3Name+'</th>';
										}else{
											str+='<th style="background:green">Option3</th>';
										}
									}else{
										if(result.option3Name!=null){
											str+='<th>'+result.option3Name+'</th>';
										}else{
											str+='<th>Option3</th>';
										}
									}
								}
								if(result.option4!=null){
									if(result.topPrior == "Option4"){
										if(result.option4Name!=null){
											str+='<th style="background:green">'+result.option4Name+'</th>';
										}else{
											str+='<th style="background:green">Option4</th>';
										}
									}else{
										if(result.option4Name!=null){
											str+='<th>'+result.option4Name+'</th>';
										}else{
											str+='<th>Option4</th>';
										}
									}
								}
								
								if(result.option5!=null){
									if(result.topPrior == "Option5"){
										if(result.option5Name!=null){
											str+='<th style="background:green">'+result.option5Name+'</th>';
										}else{
											str+='<th style="background:green">Option5</th>';
										}
									}else{
										if(result.option5Name!=null){
											str+='<th>'+result.option5Name+'</th>';
										}else{
											str+='<th>Option5</th>';
										}
									}
								}
								if(result.option6!=null){
									if(result.topPrior == "Option6"){
										if(result.option6Name!=null){
											str+='<th style="background:green">'+result.option6Name+'</th>';
										}else{
											str+='<th style="background:green">Option6</th>';
										}
									}else{
										if(result.option6Name!=null){
											str+='<th>'+result.option6Name+'</th>';
										}else{
											str+='<th>Option6</th>';
										}
									}
								}
								
								if(result.others!=null){
									if(result.topPrior == "Others"){
										str+='<th style="background:green"> Others </th>';
									}else{
										str+='<th> Others </th>';
									}
								}
								str+='<th> TYPE </th>';
						str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
					
						if(result!=null){
							str+="<tr>";
									str+="<td>"+result.totalDialed+"</td>";
									str+="<td>"+result.answered+"( "+result.answeredPer+" %)</td>";
									str+="<td>"+result.feedback+" ( "+result.feedbackPer+" %)</td>";
									if(result.option1!=null){
										if(result.topPrior == "Option1"){
											str+='<td style="background:lightGreen">'+result.option1+'('+result.option1Per+' %)</td>';
										}else{
											str+='<td>'+result.option1+'('+result.option1Per+' %)</td>';
										}
									}
									if(result.option2!=null){
										if(result.topPrior == "Option2"){
											str+='<td style="background:lightGreen">'+result.option2+'('+result.option2Per+' %)</td>';
										}else{
											str+='<td>'+result.option2+'('+result.option2Per+' %)</td>';
										}
									}
									if(result.option3!=null){
										if(result.topPrior == "Option3"){
											str+='<td style="background:lightGreen">'+result.option3+'('+result.option3Per+' %)</td>';
										}else{
											str+='<td>'+result.option3+'('+result.option3Per+' %)</td>';
										}
											
									}
									if(result.option4!=null){
										if(result.topPrior == "Option4"){
											str+='<td style="background:lightGreen">'+result.option4+'('+result.option4Per+' %)</td>';
										}else{
											str+='<td>'+result.option4+'('+result.option4Per+' %)</td>';
										}
									}
									if(result.option5!=null){
										if(result.topPrior == "Option5"){
											str+='<td style="background:lightGreen">'+result.option5+'('+result.option5Per+' %)</td>';
										}else{
											str+='<td>'+result.option5+'('+result.option5Per+' %)</td>';
										}
									}
									if(result.option6!=null){
										if(result.topPrior == "Option6"){
											str+='<td style="background:lightGreen">'+result.option6+'('+result.option6Per+' %)</td>';
										}else{
											str+='<td>'+result.option6+'('+result.option6Per+' %)</td>';
										}
									}
									
									if(result.others!=null){
										if(result.topPrior == "Others"){
											str+="<td style='background:lightGreen'>"+result.others+" ( "+result.othersPer+" %)</td>";
										}else{
											str+="<td>"+result.others+" ( "+result.othersPer+" %)</td>";
										}
									}
									str+="<td>"+result.type+"</td>";
								str+="</tr>";
							}
				str+"</tbody>";
				str+="</table>";
			}
		}
	}
	$("#ivrsurveyDataLoadoing").hide();
	$(".ivrDetailsCls").html(str);
}
function getCategoryFeedBackAnswerForCadre(){
$("#feedbackDivId").html("");

	var jsObj ={
		tdpCadreId:globalCadreId
	}
	$.ajax({
		type:'GET',
		url :'getCategoryFeedBackAnswerForCadreAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		var str='';
		if(result!=null && result.length>0){
			str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default">';
			for(var i in result){
				
				if(result[i].mainCategoryName != null)
				{
					str+='<div class="panel-heading" role="tab" id="heading'+i+'">';
					  str+='<h4 class="panel-title">';
					  str+='<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">'+result[i].mainCategoryName+'</a>';
					  str+='</h4>';
					  str+='</div>';
					  if(result[i].categoryFeedBackList != null && result[i].categoryFeedBackList.length>0)
					  {
						  for(var k in result[i].categoryFeedBackList)
						  {
							str+='<div id="collapse'+i+''+k+'11" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+'">';
							str+='<div class="panel-body">';
							 str+='<h4 class="panel-heading	">';
							str+=''+result[i].categoryFeedBackList[k].subCategoryName+'';
							  str+='</h4>';
							  str+='<div class="panel-body">';
								str+=''+result[i].categoryFeedBackList[k].description+'';
								str+='</div>';
							
							str+='</div>';
							
							str+='</div>';  
						  }					  
					  }
					  else
					  {
						  str+='<div id="collapse'+i+'0" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+'">';
							str+='<div class="panel-body">';
							str+=''+result[i].mainCategoryName+' -- '+result[i].description+'';
							str+='</div>';
							str+='</div>'; 
					  }
					str+='</div>';
					str+='</div>';
				}
				
			
				  if(result[i].healthCardsPaths != null && result[i].healthCardsPaths.length>0)
				  {
					str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" style="margin-top:10px;">';
					str+='<div class="panel panel-default">';
					str+='<div class="panel-heading" role="tab" id="heading'+i+'">';
					  str+='<h4 class="panel-title">';
					  str+='<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse101"> HEALTH CARD FILES:  </a>';
					  str+='</h4>';
					  str+='</div>';
						for(var K in result[i].healthCardsPaths)
						{
							 str+='<div style="margin-left:25px;"> <a href="'+wurl+'/'+result[i].healthCardsPaths[K].imageStr+'" target="_bank">HealthCard_File_'+(K+1)+'</a></div>';
						}					
					str+='</div>';
					str+='</div>'; 
				  }
				  
				  if(result[i].feedbackCardsPaths != null && result[i].feedbackCardsPaths.length>0)
				  {
					str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" style="margin-top:10px;">';
					str+='<div class="panel panel-default">';
					str+='<div class="panel-heading" role="tab" id="heading'+i+'">';
					  str+='<h4 class="panel-title">';
					  str+='<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse101"> FEEDBACK UPLOADED FILES:  </a>';
					  str+='</h4>';
					  str+='</div>';
						for(var K in result[i].feedbackCardsPaths)
						{
							  str+='<div style="margin-left:25px;"> <a href="'+wurl+'/'+result[i].feedbackCardsPaths[K].imageStr+'" target="_bank"> Feedback_File_'+(K+1)+'</a></div>';
						}					
					str+='</div>';
					str+='</div>'; 
				  }
				  
			}
		
		}
		$("#feedbackDivId").html(str);
	});
}
getStatusCountOfCadreForInvitationAndAttendance();
function getStatusCountOfCadreForInvitationAndAttendance(){
	
	$("#trainingDetailsBodyId").html('');
	$("#modelForTrainingDetails").hide();
	var jsObj ={
		tdpCadreId:globalCadreId
	}
	$.ajax({
		type:'GET',
		url :'getStatusCountOfCadreForInvitationAndAttendanceAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null && result.length>0 && result !=""){
			buiildingTrainigStatusDetailsOfCadre(result);
		}
		else{
			$("#trainingDetailsMainDivId").hide();
			$("#trainingDetailsBodyId").html("No Data Available");
		}
	});
	
}
function buiildingTrainigStatusDetailsOfCadre(result){
	
	$("#trainingDetailsMainDivId").show();
	var str='';
	
	$("#totalInvitedCountId").html(result[0].totalInviteeCount +'</br>Invited');
	$("#totalAttendedCountId").html(result[0].totalAttendedCount+'</br>Attended');
	$("#totalAbsentedCountId").html(result[0].totalAbsentCount+'</br>Absent');
	
	for(var i in result){
		str+='<tr>';
			 str+='<td id="'+result[i].id+'">'+result[i].progName+'</td>';
			 str+='<td>'+result[i].count+'</td>';
			 str+='<td>'+result[i].total+'</td>';
			 if(result[i].dateString =="Absent"){
				 str+='<td>1</td>';
			 }
			 else{
				  str+='<td>0</td>';
			 }
			 str+='<td><input class="btn btn-sm btn-primary detailsCls" attr_programId="'+result[i].id+'" type="button" value="Details" type="button" data-toggle="modal" data-target=".modelForTrainingDetails"/></td>';
		str+='</tr>';
	}
	$("#trainingDetailsBodyId").html(str);
}

function getAttendedTrainingCampBatchDetailsOfCadre(programId,cadreId){
	$("#trainingDetailsTableId").html("");

	var jsObj ={
		programId:programId,
		tdpCadreId:cadreId
	}
	$.ajax({
		type:'GET',
		url :'getAttendedTrainingCampBatchDetailsOfCadreAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null)
		{
			
			
			var str='';
			str+='<table class="table table-bordered" id="attendanceTab">';
				str+='<thead>';
					str+='<th>Program Name</th>';
					str+='<th>Center Name</th>';
					str+='<th>Batch Name</th>';
					str+='<th>Attended date</th>';
					/*for(var i in result.simpleVOList1){
						var day = parseInt(i) + parseInt(1);
						str+='<th>Day '+day+' ('+result.simpleVOList1[i].dateString+')</th>';
					}*/
				str+='</thead>';
				str+='<tbody>';
				for(var i in result.simpleVOList1){
					if(result.simpleVOList1[i] != null && result.simpleVOList1[i].batchName != "Speakers"){
						str+='<tr>';
							str+='<td>'+result.simpleVOList1[i].progName+'</td>';
							str+='<td>'+result.simpleVOList1[i].campName+'</td>';
							str+='<td>'+result.simpleVOList1[i].batchName+'</td>';
							str+='<td>'+result.simpleVOList1[i].dateString+'</td>';							
						str+='</tr>';
					}
				}
				str+='</tbody>';
			str+='</table>';
			
			
			/*
			var str='';
			str+='<table class="table table-bordered">';
				str+='<thead>';
					str+='<th>Program Name</th>';
					str+='<th>Center Name</th>';
					str+='<th>Batch Name</th>';
					for(var i in result.simpleVOList1){
						var day = parseInt(i) + parseInt(1);
						str+='<th>Day '+day+' ('+result.simpleVOList1[i].dateString+')</th>';
					}
				str+='</thead>';
				str+='<tbody>';
					str+='<tr>';
						str+='<td>'+result.progName+'</td>';
						str+='<td>'+result.campName+'</td>';
						str+='<td>'+result.batchName+'</td>';
						for(var i in result.simpleVOList1){
							str+='<td>'+result.simpleVOList1[i].isAttended+'</td>';
						}
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
			*/
			$("#trainingDetailsTableId").html(str);
			
			$('#attendanceTab').dataTable();
		}else{
			//$("#trainingDetailsTableId").html("NO ATTENDNACE DATA AVAILABLE...");
			$("#trainingDetailsTableId").html("");
		}
						
	});
	
}
function getRemarkSOfCadreByCallPurpose(programId,cadreId){
	var jsObj ={
		programId:programId,
		tdpCadreId:cadreId
	}
	$.ajax({
		type:'GET',
		url :'getRemarkSOfCadreByCallPurposeAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			var str='';
			str+='<h4 class="m_0">REMARKS</h4>';
			str+='<table class="table table-bordered m_top10" >';
				str+='<thead>';
					str+='<th>Purpose</th>';
					str+='<th>Remarks</th>';
					str+='<th>Date & Time</th>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
					if(result[i].remarks != null && result[i].remarks.length > 0){
						str+='<tr>';
							str+='<td>'+result[i].name+'</td>';
							str+='<td>'+result[i].remarks+'</td>';
							str+='<td>'+result[i].dateString+'</td>';
						str+='</tr>';
					}
				}
				str+='</tbody>';
			str+='</table>';
			
			$("#dataLoadingsImgForTrainingDetails").hide();
			$("#remarkDetailsId").html(str);
		}
		else{
			$("#dataLoadingsImgForTrainingDetails").hide();
		}
	});
}


	function getIVRSummaryByTdpCadreId(){
		$("#ivrSummaryajaxImg").html('<img alt="Processing Image" src="./images/icons/search.gif">');
		//var tdpCadreId='${param.cadreId}' ;
		var tdpCadreId=globalCadreId ;
		
		var jsObj ={
			tdpCadreId:tdpCadreId
		}
		$.ajax({
			type:'GET',
			url :'getIVRSummaryByTdpCadreIdAction.action',
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			$("#ivrSummaryajaxImg").html('');
			if(result!=null){
			  var str ='';
			  str+='<div class="row">';
					str+='<div class="col-xs-12">';
						str+='<table class="table m_0 table-bordered">';
							str+='<tr class="text-center">';
							if(result.totalCount==null){
								str+='<td ><span> 0 </span><br/><b>Total Calls</b></td>';
							}else{
								str+='<td >'+result.totalCount+'<br/><b>Total Calls</b></td>';
							}
							if(result.answeredcount==null){
								str+='<td ><span> 0 </span><br/><b>Answered Calls</b></td>';
							}else{
								str+='<td >'+result.answeredcount+'<br/><b>Answered Calls</b></td>';
							}
							if(result.unAnsweredCount==null){
								str+='<td ><span> 0 </span><br/><b>UnAnswered Calls</b></td>';
							}else{
								str+='<td >'+result.unAnsweredCount+'<br/><b>UnAnswered Calls</b></td>';
							}		
							str+='</tr>';
						str+='</table>';
						if(result.totalCount==null){
							str+='<button type="button" style="display:none;" class="btn btn-primary btn-custom btn-sm Ivrpopupopen pull-right m_top10">View Details</button>';
						}else{
							str+='<button type="button"  class="btn btn-primary btn-custom btn-sm Ivrpopupopen pull-right m_top10">View Details</button>';
						}
					
					str+='</div>';
			str+='</div>';
			
			 $("#ivrSummaryDetailsId").html(str);
			 
			}
		});
    } 
	$(document).on("click",".Ivrpopupopen",function(){
	$("#Ivrmodal").modal("show");
	getTotalIVRDetailsByTdpCadreId(0);
	
	});	
	
	function getTotalIVRDetailsByTdpCadreId(startIndex){
		
		$("#ivrDetailsdataLoding").html('<img alt="Processing Image" src="./images/icons/loading.gif" style="width: 35px; height: 35px;">');
		//var tdpCadreId='${param.cadreId}' ;
		var tdpCadreId=globalCadreId ;
		//record counts per page in pagination.
	       var rcrdsCount = 3;
		var jsObj ={
			tdpCadreId:tdpCadreId,
			startIndex:startIndex,
		    maxIndex:rcrdsCount
		}
		   
		$.ajax({
			type:'GET',
			url :'getTotalIVRDetailsByTdpCadreIdAction.action',
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			$("#ivrDetailsdataLoding").html('');
			if(result!=null && result.length>0){
				buildIvrDetails(result,startIndex,rcrdsCount);
			}else{
				$('#modalBodyId').html("NO DATA AVAILABLE..");
			}
			
		});
		
	}
	
	function buildIvrDetails(results,startIndex,rcrdsCount){
		
		var str='';
		for(var i in results){
			$("#ivrCadreNameId").html(results[i].tdpCadreName);
			 str+='<div class="well" style="border: 2px solid rgb(204, 204, 204);">';
			 
				str+='<p><b>IVR NAME</b> : '+results[i].name+' <span class="col-xs-offset-3"><b>Date</b> : '+results[i].dateString+'</span></p>';
				str+='<p><b>QUESTION</b> : '+results[i].question+'</p>';
				
				if(results[i].isAnswered){
					str+='<p><b>CALL ANSWERED</b> &nbsp;&nbsp;:&nbsp;&nbsp; <img style="width: 30px; height: 30px;" src="./images/call answered.png"></img>&nbsp;&nbsp; <b>( ROUND - '+results[i].roundId+' )</b></p>';
					if(results[i].optionId!=null && results[i].optionId>0){
						 str+='<p><b>OPTION SELECTED : </b></p>';
						 str+='<div>'+results[i].option+' </div>'; 
					}else{
						str+='<p><b>COMMENT : </b></p>';
						str+='<div>'+results[i].description+' </div>'; 
					}
				}else{
					str+='<p></b>CALL ANSWERED</b> &nbsp;&nbsp;:&nbsp;&nbsp; <img style="width: 30px; height: 30px;" src="./images/call not answered.png"></img></p>';
				}
		  str+='</div>';
		}
		$('#modalBodyId').html(str);
		//code for server side pagination.
		if(startIndex==0){
		
			$("#paginationDivId").pagination({
				items: results[0].totalCount,
				itemsOnPage:rcrdsCount,
				cssStyle: 'light-theme',
				onPageClick: function(pageNumber, event) {
					var num=(pageNumber-1)*rcrdsCount;
					getTotalIVRDetailsByTdpCadreId(num);
				}
			});
			if(results[0].totalCount>rcrdsCount){
				$("#paginationDivId").show();
			}else{
				$("#paginationDivId").hide();
			}
	    }
	}
	
function getActivityDetails()
{

	var jsObj={
		tdpCadreId:globalCadreId
	}	
	$.ajax({
		type:'GET',
		url :'getActivityDetailsByTdpCadreIdAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		
		if(result != null){
			var str = '';
			
			str+='<table class="table table-bordered"  style="font-size: 12px;">';
				str+='<thead>';
					
					str+='<tr >';
					str+='<th class="text-center" rowspan="2">ACTIVITY LEVEL</th>';
					str+='<th class="text-center" rowspan="2">TOTAL ACTIVITIES</th>';
					str+='<th class="text-center" colspan="3">WITH ATTENDANCE</th>';
					str+='<th class="text-center" colspan="3">WITHOUT ATTENDANCE</th>';
					str+='</tr>';
					str+='<tr >';					
					str+='<th class="text-center" >TOTAL</th>';
					str+='<th class="text-center" >ATTENDED</th>';
					str+='<th class="text-center" > NOT ATTENDED</th>';
					str+='<th class="text-center" >TOTAL</th>';
					str+='<th class="text-center" > CONDUCTED</th>';
					str+='<th class="text-center" > NOT CONDUCTED</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					if(result.activityVoList != null && result.activityVoList.length > 0){
						for(var i in result.activityVoList){
							
							if(result.activityVoList[i].totalCount != null){
								var totalCoutn =0;
								var attenddCoutn =0;
								var hasConductdCount=0;
								var hasAttendenceCount=0;
								var hasNotConductdtotalCount=0;
									str+='<tr class="text-center">';
										str+='<td style="text-align:left;">'+result.activityVoList[i].name+'</td>';
										if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount>0){
											str+='<td class="activityLvlCls" attr_id="t" attr_activity_level='+result.activityVoList[i].name+' style="cursor:pointer;font-weight:bold;color:green;;text-align:center;" attr_levelId='+result.activityVoList[i].id+'><u>'+result.activityVoList[i].totalCount+'</u></td>';
											totalCoutn = result.activityVoList[i].totalCount;
										}else{
											str+='<td style="text-align:center;"> - </td>';
										}
										
										if(result.activityVoList[i].hasAttendenceCount != null && result.activityVoList[i].hasAttendenceCount>0){
											hasAttendenceCount = result.activityVoList[i].hasAttendenceCount;
											str+='<td attr_id="hat"  class="activityLvlCls" attr_activity_level='+result.activityVoList[i].name+' style="cursor:pointer;font-weight:bold;color:green;;text-align:center;" attr_levelId='+result.activityVoList[i].id+'> <u>'+hasAttendenceCount+'</u> </td>';
										}else{
											str+='<td style="text-align:center;"> - </td>';
										}	
										if(result.activityVoList[i].attendedCount != null && result.activityVoList[i].attendedCount>0){
											str+='<td attr_id="ha"  class="activityLvlCls" attr_activity_level='+result.activityVoList[i].name+' style="cursor:pointer;font-weight:bold;color:green;text-align:center;" attr_levelId='+result.activityVoList[i].id+' ><u>'+result.activityVoList[i].attendedCount+'</u></td>';
											attenddCoutn = result.activityVoList[i].attendedCount;
											
										}else{
											str+='<td style="text-align:center;"> - </td>';
										}
										
										var notAttndedCount = hasAttendenceCount-attenddCoutn;
										if(notAttndedCount != null && notAttndedCount>0){
											str+='<td attr_id="hna" class="activityLvlCls" attr_activity_level='+result.activityVoList[i].name+' style="cursor:pointer;font-weight:bold;color:green;text-align:center;" attr_levelId='+result.activityVoList[i].id+' ><u>'+notAttndedCount+'</u></td>';
										}else{
											str+='<td style="text-align:center;"> - </td>';
										}
										if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount>0){
											hasNotConductdtotalCount = totalCoutn - hasAttendenceCount;
											str+='<td attr_id="hct"  class="activityLvlCls" attr_activity_level='+result.activityVoList[i].name+' style="cursor:pointer;font-weight:bold;color:green;;text-align:center;" attr_levelId='+result.activityVoList[i].id+'> <u>'+hasNotConductdtotalCount+' </u></td>';
										}else{
											str+='<td style="text-align:center;"> - </td>';
										}	
										
										if(result.activityVoList[i].hasConductedCount != null && result.activityVoList[i].hasConductedCount>0){
											hasConductdCount = result.activityVoList[i].hasConductedCount;
											
											if(hasConductdCount>hasNotConductdtotalCount)
												hasConductdCount = hasNotConductdtotalCount;
											
											str+='<td  attr_id="hc"  class="activityLvlCls" attr_activity_level='+result.activityVoList[i].name+' style="cursor:pointer;font-weight:bold;color:green;text-align:center;" attr_levelId='+result.activityVoList[i].id+'> <u> '+hasConductdCount+'</u></td>';
										}else{
											str+='<td style="text-align:center;"> - </td>';
										}
										var notcnductedCoint = (hasNotConductdtotalCount-hasConductdCount);
										if(notcnductedCoint != null && notcnductedCoint>0){
										str+='<td  attr_id="hnc"  class="activityLvlCls" attr_activity_level='+result.activityVoList[i].name+' style="cursor:pointer;font-weight:bold;color:green;text-align:center;" attr_levelId='+result.activityVoList[i].id+'> <u>'+notcnductedCoint+'</u></td>';	
										}else{
											str+='<td style=";text-align:center;"> - </td>';
										}
										/*
										
										
										*/
										str+='</tr>';
								
									}
							}
						}
					
				str+='</tbody>';
			str+='</table>';
		}
		
		$("#activityTableDivId").html(str);
	});
}


$(document).on('click', '.activitLvlCls', function(){
	var activityLevelId = $(this).attr("attr_levelId");
	var activityScopeId = $(this).attr("attr_scope_id");
	var statusCode = $(this).attr("attr_id");
	$('#myModal111e').modal('show');
	getCanditeActivtyAttendanceLocationsDtls(activityLevelId,activityScopeId,statusCode);
	
});

$(document).on('click', '.activityLvlCls', function(){
	var activityLevelId = $(this).attr("attr_levelId");
	var activeCode = $(this).attr("attr_id");
	
	getCandateActivityAttendance(activityLevelId,activeCode);
	
});

function getCandateActivityAttendance(activityLevelId,activeCode){

		$("#activityAttented").html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
	var jsObj={
		cadreId:globalCadreId,
		activityLevelId:activityLevelId	,
		panchayatId: $('#cadrePanchaytId').val(),
		mandalId:$('#cadremandalId').val(),
		lebId:$('#cadreRuralORUrbanId').val(),
		assemblyId:$('#cadreConstituencyId').val(),
		districtId :$('#cadreDistrictId').val(),
		stateId :$('#cadreStateId').val(),
		participatedAssemblyId:0,
		activeCode:activeCode
	}
	
	$.ajax({
			type:'POST',
			 url: 'getCandateActivityAttendanceAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				$("#activityAttented").html('');
				if(result!= null && result.length >0)
					buildCandateActivityAttendance(result,activeCode,activityLevelId);
				else
					$("#activityAttented").html(' Candidate Not Available with any Activity.');
				
				//console.log(result);
			});
}
//getCandateActivityAttendance();
function buildCandateActivityAttendance(result,activeCode,activityLevelId){

	var str = '';
	str+='<div class="table-responsive">';
	str+='<table class="table table-bordered" style="font-size: 12px;">';
	str+='<thead style="background:#ccc">';
	str+='<th>Activity level</th>';
	str+='<th>Activity Name</th>';
	str+='<th>Status</th>';
	
	if(activeCode== "t" || activeCode== "hat" || activeCode== "ha" || activeCode== "hna" ){
		str+='<th>Invited</th>';
		str+='<th>Attended</th>';
		str+='<th>Not Attended </th>';
	}
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
	str+='<tr>';
	str+='<td>'+result[i].isLocation+'</td>';
	if(result[i].attributeId == 2)
		str+='<td style="text-align:left;"> <span class="glyphicon glyphicon-thumbs-up" style="margin-right:15px;" title="Event has attendance system." ></span>'+result[i].attendendLocation+'</td>';
	else
		str+='<td>'+result[i].attendendLocation+'</td>';
	str+='<td style="text-align:center;"> '+result[i].conductedDate+' </td>';
	//if(result[i].invitteeCnt != null && result[i].invitteeCnt >0 && result[i].attendedCount != null && result[i].attendedCount>0)
	//	str+='<td> Conducted </td>';
	//else if(result[i].conductedDate != null && result[i].conductedDate =='Conducted')
	//	str+='<td> Conducted </td>';
	//if(result[i].conductedDate != null && result[i].conductedDate.length >0 )
	//	str+='<td> Conducted </td>';	
	//else
	//	str+='<td> - </td>';
var invitationCount = 1;
	if(activeCode== "t" || activeCode== "hat" || activeCode== "ha" || activeCode== "hna" ){
		if(result[i].invitteeCnt == 0){
			str+='<td style="text-align:center;">-</td>';
		}else{
			str+='<td style="text-align:center;font-weight:bold;color:green;cursor:pointer;" class="activitLvlCls" attr_scope_id="'+result[i].activityScopeId+'" attr_id="inv" attr_levelId="'+activityLevelId+'"><u>'+result[i].invitteeCnt+'</u></td>';
		}
		if(result[i].attendedCount == 0){
		str+='<td style="text-align:center;">-</td>';
		}else{
			str+='<td style="text-align:center;"  class="" attr_scope_id="'+result[i].activityScopeId+'"  attr_id="att" attr_levelId="'+activityLevelId+'" >'+result[i].attendedCount+'</td>';
		}
		if(result[i].invitteeCnt == 0){
			if(result[i].attributeId != null && result[i].attributeId == 2 && result[i].attendedCount <=0)
				str+='<td style="text-align:center;"> 1 </td>';
			else
				str+='<td style="text-align:center;"> - </td>';
		}
		else if(result[i].abscentCnt == 0){
			var count = (result[i].invitteeCnt - result[i].attendedCount);
			if(count>0)
				str+='<td style="text-align:center;"> '+count+'</td>';
			else
				str+='<td style="text-align:center;"> - </td>';
		}else{
			str+='<td style="text-align:center;"  class="" attr_scope_id="'+result[i].activityScopeId+'"   attr_id="abs" attr_levelId="'+activityLevelId+'">'+result[i].abscentCnt+'</td>';
		}
	}
	str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	str+='</div>';
	
	$("#activityAttented").html(str);
}

$("#mainheading").parent().find("p").removeClass("display-style");



//getTypeWiseIvrDetailsOFCadre();
function getTypeWiseIvrDetailsOFCadre(){
	$("#ivrDetailsBodyId").html("");
	$("#ivrTypeDetailsDivId").html("");
	$('.ivrSurveyCandtDetailsCls').html("");
	var jsObj={
		cadreId:globalCadreId
	}
	$('#ivrsurveyDataLoadoing').show();
	$.ajax({
		type:'GET',
		url :'getTypeWiseIvrDetailsOFCadreAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){		
		var str='';		
		if(result !=null && result.length>0){
			str+='<div class="row m_top10">';
			for(var i in result){
					var totalCount = result[i].answeredCount + result[i].unAnsweredCount + result[i].othersCount;
				str+='<div class="col-md-4">';
					str+='<table class="table table-bordered text-center">';
						str+='<tr>';
							str+='<td colspan="2">';
							if(result[i].count != 0){
								str+='<h3 class="m_0"><span class="ivrAnsweredCls" attr_event_name="'+result[i].name+'" attr_searchType="total" style="cursor:pointer;" attr_event_type_id='+result[i].id+'>'+result[i].count+'</span></h3>';
							}else{
								str+='<h3 class="m_0">0</h3>';
							}
								str+='<h5 class="m_0" id='+result[i].id+'>'+result[i].name+'</h5>';
							str+='</td>';
						str+='</tr>';
						str+='<tr>';
						if(result[i].answeredCount != 0){
							str+='<td><p class="m_0">ANSWERED<span class="pull-right ivrAnsweredCls" attr_searchType="total" style="cursor:pointer;" attr_searchType="answered" attr_event_name="'+result[i].name+'" attr_event_type_id='+result[i].id+'>'+result[i].answeredCount+'</span></p></td>';
						}else{
							str+='<td><p class="m_0">ANSWERED<span class="pull-right">0</span></p></td>';
						}
						
						if(result[i].unAnsweredCount != 0){
							str+='<td><p class="m_0">UNANSWERED<span class="pull-right ivrAnsweredCls" attr_searchType="unanswered" style="cursor:pointer;" attr_event_name="'+result[i].name+'" attr_event_type_id='+result[i].id+'>'+result[i].unAnsweredCount+'</span></p></td>';
						}else{
							str+='<td><p class="m_0">UNANSWERED<span class="pull-right">0</span></p></td>';
						}
						str+='</tr>';
					str+='</table>';
				str+='</div>';
			}
			str+='</div>';
				$('#ivrsurveyDataLoadoing').hide();
			/* str+='<table class="table m_0 table-bordered">';
				str+='<thead>';
					str+='<th class="text-center">IVR TYPE </th>';
					str+='<th class="text-center"> TOTAL </th>';
					str+='<th class="text-center"> ANSWERED </th>';
					str+='<th class="text-center"> UNANSWERED </th>';
				str+='</thead>';
				
				str+='<tbody class="text-center">';
				for(var i in result){
					var totalCount = result[i].answeredCount + result[i].unAnsweredCount + result[i].othersCount;
					str+='<tr>';
						str+='<td id='+result[i].id+'>'+result[i].name+'</td>';
						if(totalCount != 0){
							str+='<td><span class="ivrAnsweredCls" data-toggle="tooltip" data-placement="top" title="Click Here To Get Total Details" attr_event_name="'+result[i].name+'" attr_searchType="total" style="cursor:pointer;" attr_event_type_id='+result[i].id+'>'+totalCount+'</span></td>';
						}else{
							str+='<td>0</td>';
						}
						if(result[i].answeredCount != 0){
							str+='<td><span class="ivrAnsweredCls" data-toggle="tooltip" data-placement="top" title="Click Here To Get Answered Details" attr_event_name="'+result[i].name+'" attr_searchType="answered" style="cursor:pointer;" attr_event_type_id='+result[i].id+'>'+result[i].answeredCount+'</span></td>';
						}else{
							str+='<td>0</td>';
						}
						if(result[i].unAnsweredCount != 0){
							str+='<td><span class="ivrAnsweredCls" data-toggle="tooltip" data-placement="top" title="Click Here To Get UnAnswered Details" attr_event_name="'+result[i].name+'" attr_searchType="unanswered" style="cursor:pointer;" attr_event_type_id='+result[i].id+'>'+result[i].unAnsweredCount+'</span></td>';
						}else{
							str+='<td>0</td>';
						}
					str+='</tr>';
				}										
				str+='</tbody>';
			str+='</table>'; */		
		}else{
			str+='<div>Data Not Available</div>';
			$('#ivrsurveyDataLoadoing').hide();
		}		
		$("#ivrTypeDetailsDivId").html(str);
		$('[data-toggle="tooltip"]').tooltip()
		
	});
}

function getIvrSurveyDetails(searchType,eventTypeId,eventName){
	$("#ivrDetailsBodyId").html("");
	$("#dataLoadingsImgForIVRDetails").show();
	$('.ivrSurveyCandtDetailsCls').html("");
	var jsObj={
		tdpCadreId : globalCadreId,
		entityTypeId : eventTypeId,
		searchType : searchType
	}
	$.ajax({
		type:'GET',
		url :'getIvrSurveyInfoByTdpCadreIdAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null && result.length > 0){
			
			if(eventTypeId !=null && eventTypeId==2){
				buildSurveyAnswerDetailsForActivity(result,eventName);
			}
			else if(eventTypeId !=null && eventTypeId==3){
				buildSurveyAnswerDetailsForTrainingCamps(result,eventName);
			}
			else if(eventTypeId !=null && eventTypeId==5){
				buildSurveyAnswerDetailsForSpecialSurveys(result,eventName);
			}
			else  if(eventTypeId !=null && eventTypeId==4){
			
				var str='';
				str+='<div class="panel panel-default" style="margin-top: 20px; ">';		
				str+='<div class="panel-heading">';		
				str+='<h4 class="panel-title"><span id="ivrModalHeadingId">'+eventName+'</span></h4>';
				str+='</div>';
				str+='<div class="panel-body">';
				str+='<div class="table-responsive">';
				str+='<table class="table m_0 table-bordered">';
					str+='<thead style="background-color:#f4f4f4">';
						/*str+='<th class="text-center"> MEETING </th>';
						str+='<th class="text-center"> DATE </th>';*/
						str+='<th class="text-center"> SURVEY </th>';
						str+='<th class="text-center"> ROUND </th>';
						str+='<th class="text-center"> QUESTION </th>';
						str+='<th class="text-center"> OPTION </th>';
					str+='</thead>';
					
					str+='<tbody class="text-center">';
					for(var i in result){
						str+='<tr>';
							/*str+='<td>'+result[i].eventName+' ('+result[i].name+')</td>';
							str+='<td>'+result[i].dateStr+'</td>';*/
							str+='<td>'+result[i].surveyName+'</td>';
							str+='<td>'+result[i].round+'</td>';
							str+='<td>'+result[i].question+'</td>';
							str+='<td>'+result[i].option+'</td>';
						str+='</tr>';
					}
					str+='</tbody>';
				str+='</table>';
				str+='</div>';
				str+='</div>';
				str+='</div>';
			
				$("#dataLoadingsImgForIVRDetails").hide();
				$("#ivrDetailsBodyId").html(str);
			}
		}
		else{
			$("#dataLoadingsImgForIVRDetails").hide();
			$("#ivrDetailsBodyId").html("NO DATA AVAILABLE...");
		}
	});	
}

function buildSurveyAnswerDetailsForActivity(result,eventName){
	var str='';
	
	var str='';
			str+='<div class="panel panel-default">';		
				str+='<div class="panel-heading">';		
					str+='<h4 class="panel-title"><span id="ivrModalHeadingId">'+eventName+'</span></h4>';
				str+='</div>';
				
				str+='<div class="panel-body">';
				str+='<div class="table-responsive">';
				str+='<table class="table m_0 table-bordered">';
					str+='<thead style="background-color:#f4f4f4">';
						/*str+='<th class="text-center"> ACTIVITY </th>';
						str+='<th class="text-center"> ACTIVITY DATE </th>';
						str+='<th class="text-center"> ACTIVITY LEVEL </th>';*/
						str+='<th class="text-center"> SURVEY </th>';
						str+='<th class="text-center"> ROUND </th>';
						str+='<th class="text-center"> QUESTION </th>';
						str+='<th class="text-center"> OPTION </th>';
					str+='</thead>';
					
					str+='<tbody class="text-center">';
					for(var i in result){
						str+='<tr>';
							/*str+='<td>'+result[i].name+'</td>';
							if(result[i].startDate !=null && result[i].endDate !=null){
								str+='<td>'+result[i].startDate +" - "+result[i].endDate+'</td>';
							}else{
								str+='<td></td>';
							}
							str+='<td>'+result[i].levelValue+'</td>';*/
							str+='<td>'+result[i].surveyName+'</td>';
							str+='<td>'+result[i].round+'</td>';
							str+='<td>'+result[i].question+'</td>';
							str+='<td>'+result[i].option+'</td>';
						str+='</tr>';
					}
					str+='</tbody>';
				str+='</table>';
				str+='</div>';
				str+='</div>';
				str+='</div>';
				$("#dataLoadingsImgForIVRDetails").hide();
				$("#ivrDetailsBodyId").html(str);
}

function buildSurveyAnswerDetailsForSpecialSurveys(result,eventName){
	var str='';
				str+='<div class="panel panel-default">';		
				str+='<div class="panel-heading">';		
				str+='<h4 class="panel-title"><span id="ivrModalHeadingId">'+eventName+'</span></h4>';
				str+='</div>';
				
				str+='<div class="panel-body">';
				str+='<div class="table-responsive">';
			    str+='<table class="table m_0 table-bordered">';
				str+='<thead style="background-color:#f4f4f4">';
					/*str+='<th class="text-center"> EVENT </th>';
					str+='<th class="text-center"> DATE </th>';*/
					str+='<th class="text-center"> SURVEY </th>';
					str+='<th class="text-center"> ROUND </th>';
					str+='<th class="text-center"> QUESTION </th>';
					str+='<th class="text-center"> OPTION </th>';
				str+='</thead>';
				
				str+='<tbody class="text-center">';
				for(var i in result){
					str+='<tr>';
						/*str+='<td>'+result[i].eventName+'</td>';
						str+='<td>'+result[i].dateStr+'</td>';*/
						str+='<td>'+result[i].surveyName+'</td>';
						str+='<td>'+result[i].round+'</td>';
						str+='<td>'+result[i].question+'</td>';
						str+='<td>'+result[i].option+'</td>';
					str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';

	$("#dataLoadingsImgForIVRDetails").hide();
	$("#ivrDetailsBodyId").html(str);
}

function buildSurveyAnswerDetailsForTrainingCamps(result,eventName){
	
	var str='';
	str+='<div class="panel panel-default">';		
				str+='<div class="panel-heading">';		
					str+='<h4 class="panel-title"><span id="ivrModalHeadingId">'+eventName+'</span></h4>';
				str+='</div>';
				
				str+='<div class="panel-body">';		
				str+='<div class="table-responsive">';		
			str+='<table class="table m_0 table-bordered">';
				str+='<thead style="background-color:#f4f4f4">';
					str+='<th class="text-center"> SURVEY </th>';
					str+='<th class="text-center"> ROUND </th>';
					str+='<th class="text-center"> QUESTION </th>';
					str+='<th class="text-center"> OPTION </th>';
				str+='</thead>';
				
				str+='<tbody class="text-center">';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].surveyName+'</td>';
						str+='<td>'+result[i].round+'</td>';
						str+='<td>'+result[i].question+'</td>';
						str+='<td>'+result[i].option+'</td>';
					str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#dataLoadingsImgForIVRDetails").hide();
	$("#ivrDetailsBodyId").html(str);
}

$(document).on('click', '.ivrAnsweredCls', function(){
	var searchType = $(this).attr("attr_searchType");
	var eventName = $(this).attr("attr_event_name");
	var eventTypeId = $(this).attr("attr_event_type_id");
	
	//$("#ivrModalHeadingId").html(eventName);
	getIvrSurveyDetails(searchType,eventTypeId,eventName);
});

function getRefferelDetailsStatusWise(){
	//$("#referralGrievanceDetailsId").html('');
	
	var cadreId = globalCadreId;
	$("#referralGrievanceLoadingImg").show(); 
	
	$.ajax({
		type:'GET',
		url: wurl+"/Grievance/WebService/getRefferelDetailsStatusWise/"+cadreId+"",
		//url: "http://localhost:8080/Grievance/WebService/getRefferelDetailsStatusWise/"+cadreId+"",
			 contentType: "application/json; charset=utf-8",
			 dataType: "json",
			username: "grievance",
				 password: "grievance@!tG"	
	}).done(function(result){
		$("#referralGrievanceLoadingImg").hide();
		var value='';
		if(result !=null && result.length>0){
		
		var totalApprovetAmt = result[0].totalApprovedAmount;
		var statusArr = [];
			value+='<h3 class="text-center" style="margin-top:10px;margin-bottom:0px"><img src="images/IndianRupee.png" style="display:inline-block;height:15px;margin:0px;"/>'+totalApprovetAmt+'/-</h3>';
			value+='<h5 class="text-center">TOTAL FINANCIAL SUPPORT</h5>';
			//value+='<ul class="referralGrievanceDetails" >';		
				for(var i in result){
					//value+='<li>'+result[i].name.toUpperCase()+'<span class="pull-right"><a onclick="getReferealComplaintDetails(\''+result[i].name+'\');" href="#">'+result[i].count+'</a></span></li>';
					statusArr.push(result[i].name);
				}
				
			//value+='</ul>';	
				var obj={
					 cadreId : globalCadreId,
					 status :"All",
					 referTypeId:0
				}
				$.ajax({
					type:'POST',
					url: wurl+"/Grievance/WebService/getRefferelComplaintDetailsForCandidate",
					//url: "http://localhost:8080/Grievance/WebService/getRefferelComplaintDetailsForCandidate",
						 dataType: "json",
						 data: JSON.stringify(obj),
						 contentType: "application/json; charset=utf-8",
						username: "grievance",
				 password: "grievance@!tG"	
				}).done(function(result){
					
					value += '<div class="panel-body pad_0">';
					value+='<ul style="margin-bottom:0px;box-shadow:none" class="inbox-messages custom-scroll-ins">';
					for(var i in result)
					{
					var color = getColorCodeByStatus(result[i].status);
					
					value += '<li style="background:'+color+'">';
					value += '<div class="row">';
						value += '<div class="col-sm-6">';
							value += '<p class="m_0"><a href="'+wurl+'/Grievance/homeAction.action?complaintId='+result[i].complaintId+'&typeOfIssue=Grievance" target="_blank">C ID -'+result[i].complaintId+'</a></p><p class="m_0">'+result[i].subject+'</p><p class="m_0">Status - <span class="textTransFormCls">'+result[i].status+'</span></p><p class="m_0">'+result[i].raisedDate+'</p>';
						value += '</div>';
						value += '<div class="col-sm-6">';
							value +='<button class="referalGrievenceCls btn btn-success btn-xs pull-right"  style="padding-bottom: 3px; padding-top: 6px; border-bottom-width: 1px; margin-top: 20px;cursor:pointer;" class="btn btn-success m_top25" attr_status="'+result[i].status+'" attr_complaintId='+result[i].complaintId+'>View More</button>';
						value += '</div>';
					value += '</div>';		
					value+='</li>';
					}
					value+='</ul>';  
				  
					value += '</div>';   
					$("#referralGrievanceDetailsId").html(value);
					//$("#refferelTotalCountId").html('<a style="cursor:pointer;" onclick="getReferealComplaintDetails(\'All\');" href="#">'+result[0].totalCount+'</a>');
					
				});
			
		}
		$("#refferelTotalCountId").html('<a style="" onclick="" href="#">'+result[0].totalCount+'</a>');
		$(".custom-scroll-ins").mCustomScrollbar();
	});
}

$(document).on('click','.referalGrievenceCls',function(){
	
	$this = $(this);
	var status = $($this).attr("attr_status");
	var complaintId=  $($this).attr("attr_complaintId");

	
	var obj={
		 cadreId : globalCadreId,
		 status :status,
		 referTypeId:0,
		 complaintId:complaintId
	}  
	
	$.ajax({
		type:'POST',
		url: wurl+"/Grievance/WebService/getRefferelComplaintDetailsForCandidate",
		//url: "http://localhost:8080/Grievance/WebService/getRefferelComplaintDetailsForCandidate",
			 dataType: "json",
			 data: JSON.stringify(obj),
			 contentType: "application/json; charset=utf-8",
			 username: "grievance",
				 password: "grievance@!tG"	
	}).done(function(result){
		buildPopupComplaintInfo1(result);
		
	});
});

function buildPopupComplaintInfo1(result) {
	$("#myModalForTableGrieId").modal("show");
	var str = '';
	str+='	<div style="display:block;" id="autoExport"></div>';
	str+='	<div class="table-responsive">';
	str+='<table class="display table table-bordered table-striped table-hover m_top5" id="tableId">';
    str+='<thead style="border-top:1px solid #ccc;background:#ccc;">';
    str+='<th >Complaint ID</th>';
	str+='<th >Complaint Person Details</th>';
    str+='<th >Subject</th>';
    str+='<th >Is Party Member?</th>';
    str+='<th >Status</th>';
	str+='<th >Referer Name</th>';
    str+='<th >Posted Date</th>';
	str+='<th >Last Updated Date</th>';
	str+='<th >Financial Support</th>';
	str+='</thead>';
   str+='<tbody>';
	for(var i in result){
		str+='<tr>';
          str+='<td>'+result[i].complaintId+'</td>';
		str+='<td>';
		str+='N :'+result[i].name+'<br/>';
		str+='D :'+result[i].location+'<br/>C :'+result[i].constituency+'<br/>M :'+result[i].mandal+'<br/>V :'+result[i].villageName+'</td>';
        
		str+='<td>'+result[i].subject+'</td>';
	
		if(result[i].memberType == null)
		{
			str+='<td>-</td>';
		}
		else{
		str+='<td>'+result[i].memberType.toUpperCase()+'</td>';	
			var color = getColorCodeByStatus(result[i].status);
			 str+='<td><span>'+result[i].status.toUpperCase()+'</span></td>';
		
			}
		if(result[i].referDetailsVO != null && result[i].referDetailsVO.referName !=''){
			
			str+='<td>'+result[i].referDetailsVO.referName.toUpperCase()+'</td>';
		}
		else{
			str+='<td>-</td>';
		}
		str+='<td>'+result[i].raisedDate+'</td>';
		str+='<td>'+result[i].updatedDate+'</td>';
		if(result[i].amount !=null){
			str+='<td>'+result[i].amount+' /-</td>';
		}else{
			str+='<td>0</td>';
		}
		
		str +='<td></td>';
        str+='</tr>';
	}
	str+='</tbody>';
    str+='</table>';
    str+='</div>';
	$("#popupContentDiv").html(str)
	$("#tableId").dataTable();
	$("#tableId").removeClass("dataTable")
}

function getTrainingCampAttendenceInfoInCadreLocation(){
	
	$("#trainingCampParticipationDivId").html('');
	
	var panchaId = 0;
	var mandId = 0;
	var localElecId = 0;
	var consttncyId = 0;
	var parlmntId = 0;
	var dstctId = 0;
	
	if(participatedConstituencyId != null && participatedConstituencyId > 0){
		consttncyId = participatedConstituencyId;
		parlmntId = participatedParliamentId;
		dstctId = participatedDistrictId;
	}
	else{
		panchaId = globalPanchayatId;
		mandId = globalTehsilId;
		localElecId = globalElectionBodyId;
		consttncyId = globalConstituencyId;
		parlmntId = globalParliamentId;
		dstctId = globalDistrictId;
	}
	
	var jsobj={
		boothId : $("#cadreBoothId").val(),
		panchayatId : panchaId,
		wardId : 0,
		mandalId : mandId,
		lebId : localElecId,
		constituencyId : consttncyId,
		parliamentid : parlmntId,
		districtid : dstctId
	}
	$.ajax({
		type:'GET',
		 url: 'getTrainingCampAttendenceInfoInCadreLocationAction.action',
		 data : {task:JSON.stringify(jsobj)} ,
	}).done(function(result){
		if(result != null){
			var str='';
			 str+'<div class="panel-body pad_0">';
			 str+'<div class="table-responsive">';
			// str+='NOTE : IA:INVITEE ATTENTED  , NI:NON-INVITEE';
				str+='<table class="table m_0 table-bordered m_0" style="font-size:12px">'
					
					str+='<thead>';
						str+='<th style="background-color:#f5f5f5">LOCATION</th>';
						str+='<th style="background-color:#f5f5f5">TOTAL INVITED</th>';
						str+='<th style="background-color:#f5f5f5">ATTENDED</th>';
						str+='<th style="background-color:#f5f5f5">INVITEE ATTENDED (%)</th>';
					str+='</thead>'
					for(var i in result){
						str+='<tr>';
							str+='<td style="text-transform:uppercase">'+result[i].name+'</td>';
							str+='<td>'+result[i].count+'</td>';
							str+='<td>'+result[i].actualCount+' (IA), '+result[i].availableCount+' (NIA)</td>';
							str+='<td>'+result[i].percentage+' </td>';
						str+='</tr>';
					}	
					
				str+'</table>';
				str+='<p style="margin-left:15px;text-align:center">IA:INVITEE ATTENDED  ,<b></b> NIA:NON-INVITEE ATTENDED</p>';
			str+='</div>';
			str+='</div>';
		
		$("#trainingCampParticipationDivId").html(str);
		}
		$("#dataLoadingsImgForTrainingCampParticipation").hide();
	});
}


function getIvrSurveyForCandidateParticipated(cadreId)
{
	
	var jsObj={
		cadreId:cadreId,
		task:""	
			}
			
			$.ajax({
				type:'GET',
				 url: 'getTdpCadreIvrSurveyDetailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result != null){
					buildAnsweredIvrSurveys(result);
					buildUnAnsweredIvrSurveys(result);
				}
			})				
}

getAppointmentsUserDetails();
function getAppointmentsUserDetails()
{
	$("#buildCandidateAppointmentUser").html('');
	$("#totalAptId").html(0);
	$("#candidateapptID").show();
	$("#candidateAppointmentImg").show();
	var appointmentUserIdsArray=[];
		appointmentUserIdsArray.push(2);
		var cadreId = globalCadreId;
	
	var jsObj={
			appointmentUserIds:appointmentUserIdsArray,
			cadreId:cadreId
		}
			$.ajax({
				type:'GET',
				 url: 'getAppointmentsUserDetailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				$("#candidateAppointmentImg").hide();
				var totalCount = 0;
				var str='';
				if(result == null || result.length == 0){
					$("#buildCandidateAppointmentUser").hide();
				}
				else if(result != null){
						str+='<table class="table table-bordered m_0">';
						str+='<thead style="background-color:#f2f2f2;">';
						str+='<th>CANDIDATE USER</th>';
						str+='<th>COUNT</th>';
						str+='</thead>';
						str+='<tbody>';
					for(var i in result){
						str+='<tr>';
						str+='<td style="text-transform: uppercase;">'+result[i].name+'</td>';
						str+='<td attr_apptcandName="'+result[i].apptcandidateName+'" attr_apptUserId='+result[i].apptUserId+' attr_apptcandId="'+result[i].apptcandidateId+'"  class="text-bold historyShowModalBtn" style="font-size: 18px;cursor:pointer;text-align:center;" >'+result[i].apptCount+'</td>';
						str+='</tr>';
						totalCount = totalCount + result[i].apptCount ; 
						
					}	
					str+='</tbody>';
					str+='</table>';
					$("#buildCandidateAppointmentUser").html(str);
					$("#totalAptId").html(totalCount);
				}else{
				    $("#buildCandidateAppointmentUser").html("No Data Available");	
				}
			});				
}
	$(document).on("click",".historyShowModalBtn",function(){
			$(".historyShowModal").modal("show");
			var apptUserId = $(this).attr("attr_apptUserId");
			var apptcandidateId = $(this).attr("attr_apptcandId");
			var apptcandidateName = $(this).attr("attr_apptcandName");
			//$("#appCandidateNameId").html(apptcandidateName);
			getAppointStatusOverviewforCandidate(apptUserId,apptcandidateId);
			getAppointmentHistoryForCandidate(apptUserId,apptcandidateId)
		});
		

 function getAppointStatusOverviewforCandidate(apptUserId,apptcandidateId){
	 
		  $("#aptCandidateHistorystatusOverViewDiv").html('<img src="images/search.gif" />');
	    	var jsObj={
	    			appointmentCandidateId:apptcandidateId,
					apptUserId:apptUserId,
					task:""
	    		}
	    		$.ajax({
	    			  type:'GET',
	    			  url: 'getAppointStatusOverviewforCandidateAction.action',
	    			  data: {task:JSON.stringify(jsObj)}
	    	   }).done(function(result){
					
					buildAppointmentStatusOverView(result);
					
	    	   });	
		  }
		  function buildAppointmentStatusOverView(result)
		  {
			  
			var str = '';
			var total = 0;
			for(var i in result)
			{
				total = total + result[i].availableCount;
			}
			str+='<p>Total Appointment Requested - '+total+'</p>';
			str+='<div class="table-responsive">';
			str+='<table class="table table-bordered">';
			str+='<tr class="text-center">';
			for(var i in result)
			{
			
			str+='<td>';
			str+='<h4>'+result[i].availableCount+'</h4>';
			str+='<h5>'+result[i].name+'</h5>';
			str+='</td>';
			
			}
			str+='</tr>';
			str+='</table>';
			str+='</div>';
		   $("#aptCandidateHistorystatusOverViewDiv").html(str);			
		  }
		  
		  function getAppointmentHistoryForCandidate(apptUserId,apptcandidateId){
			$("#aptCandidateHistoryDiv").html('<img src="images/search.gif" />');
			//$("#buildCommentsForHistoryView").html('<img src="images/search.gif" />');
	    	var jsObj={
	    			appointmentCandidateId:apptcandidateId,
					apptUserId:apptUserId,
					task:""
	    		}
		$.ajax({
		  type : 'GET',
		  url : 'getAppointmentHistoryForCandidateAction.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
		  buildAppointmentHistoryForCandidate(result);
		  });
	  }
	  
	function buildAppointmentHistoryForCandidate(result)
	{
		var str='';
		str+='<div class="table-responsive">';
		str+='<table class="table table-condensed" style="border:1px solid #ddd" id="aptCandidateHistorydatatable">';
		str+='<thead>';
		str+='<th>ID</th>';
		str+='<th>PURPOSE</th>';
		str+='<th>CREATED ON</th>';
		str+='<th>PREFERED DATE</th>';
		str+='<th>CONFIRMED DATE</th>';
		str+='<th>STATUS</th>';
		str+='<th></th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result)
		{
		str+='<tr>';
		str+='<td>'+result[i].uniqueCode+'</td>';
		str+='<td>'+result[i].purpose+'</td>';
		str+='<td>'+result[i].createdOn+'</td>';
		str+='<td>'+result[i].preferredDate+'</td>';
		str+='<td>'+result[i].confirmedDate+'</td>';
		str+='<td>'+result[i].status+'</td>';
		str+='<td><img onclick="getAppointCommentsForTracking(\''+result[i].id+'\',\''+result[i].uniqueCode+'\')" style="height:16px;cursor:pointer;margin-right:5px;" title="View Status History" attr-aptname="'+result[i].uniqueCode+'" attr-id="'+result[i].id+'" class="pull-right showanimate" src="dist/Appointment/img/reqHistoryicon+.png">';
		str+='</tr>';	
		}
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		str+='<div id="appointmentCommentsDiv" class="m_top30"></div>';
		$("#aptCandidateHistoryDiv").html(str);	
	     $('#aptCandidateHistorydatatable').DataTable();
		 $('#aptCandidateHistorydatatable').removeClass("dataTable");
	}
	
	function getAppointCommentsForTracking(id,name)
	{
		$("#appointmentCommentsDiv").html('<img src="images/search.gif" />');
		var jsObj={
	    			appntmntId:id,
					task:""
	    		}
		$.ajax({
		  type : 'GET',
		  url : 'getAppointmentStatusCommentsTrackingDetails.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
		  buildAppointCommentsForTracking(result,name);
		});
		
	}
	
	function buildAppointCommentsForTracking(result,aptName)
	{
			var str='';
	
			str+='<h4>'+aptName+' Appointment Status Tracking </h4>';
			if(result == null || result.length == 0)
				$("#appointmentCommentsDiv").html('No Data Available');
			str+='<ul class="apptStatusTracking">';
			for(var i in result){
				
				str+='<li>';
					str+='<div class="arrow_box">';
					if(result[i].id == 1)
					str+='<p> <span class="text-success"></span> Appointment Created on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';	
						else
						str+='<p>Appointment status changed to <span class="text-success"><b>'+result[i].status+'</b></span> on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';
					
						if(result[i].commentsList != null && result[i].commentsList.length > 0 && result[i].commentsList[0].length > 0)
						{
							str+='<u style="font-size:15px;">Comments</u>';
							for(var j in result[i].commentsList)
							{
							
							str+='<p>'+result[i].commentsList[j]+'</p>';	
							}
						}
						
					str+='</div>';
				str+='</li>';	
			}
			str+='</ul>';
			$("#appointmentCommentsDiv").html(str);
			$('.historyShowModal').animate({
						scrollTop: $("#appointmentCommentsDiv").offset().top},
					'slow');
	 
	}
	
			/*$.ajax({
				type:'GET',
				 url: 'getTdpCadreIvrSurveyDetailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			})				
}*/
function getGrievanceStatusDetails(){
	$("#grievanceStatusMainDivId").html('');
	
	var constituencyId = 0;
	var parliamentid = 0;
	var districtId = 0;
	if(participatedConstituencyId != null && participatedConstituencyId > 0){
		constituencyId = participatedConstituencyId;
		parliamentid = participatedParliamentId;
		districtId = participatedDistrictId;
	}
	else{
		constituencyId = globalConstituencyId;
		parliamentid = globalParliamentId;
		districtId = globalDistrictId;
	}
	var jsobj={
		assemblyId : constituencyId,
		parliamentid : parliamentid,
		districtId : districtId
		}
	$.ajax({
		type:'GET',
		 url: 'getGrievanceStatusDetailsAction.action',
		 data : {task:JSON.stringify(jsobj)} ,
	}).done(function(result){
		if(result != null){
			buildGrievanceStatusDetails(result);
		}
	});
}
function buildGrievanceStatusDetails(result){
	var str='';
			 str+'<div class="panel-body pad_0">';
				str+='<div class="table-responsive">';
				str+='<table class="table m_0 table-bordered table-condensed m_0" style="font-size:9px">';
					str+='<thead>';
						str+='<th style="background-color:#f5f5f5">LOCATION</th>';
						str+='<th style="background-color:#f5f5f5">TOTAL GRIEVANCE REQUESTS</th>';
						str+='<th style="background-color:#f5f5f5">PARTY(%)</th>';
						str+='<th style="background-color:#f5f5f5">GOVT(%)</th>';
						str+='<th style="background-color:#f5f5f5">WELFARE(%)</th>';
					str+='</thead>';
					for(var i in result){
						str+='<tr>';
							str+='<td style="text-transform:uppercase">'+result[i].name+'</td>';
							str+='<td>'+result[i].count+'</td>';
							str+='<td>'+result[i].partyCount+'('+result[i].partyPerc+'%)</td>';
							str+='<td>'+result[i].govtCount+'('+result[i].govtPerc+'%)</td>';
							str+='<td>'+result[i].welfareCount+'('+result[i].welfarePerc+'%)</td>';
						str+='</tr>';
					}		
				str+'</table>';
				str+='</div>';
			str+='</div>';
		$("#dataLoadingsImgForGrievanceStatusCount").hide();
		$("#grievanceStatusMainDivId").html(str);
} 
function getStatusCountsForGrievanceDetails(){
	$("#statusCountsMainDivId").html('');
	
	var constituencyId = 0;
	var parliamentid = 0;
	var districtId = 0;
	if(participatedConstituencyId != null && participatedConstituencyId > 0){
		constituencyId = participatedConstituencyId;
		parliamentid = participatedParliamentId;
		districtId = participatedDistrictId;
	}
	else{
		constituencyId = globalConstituencyId;
		parliamentid = globalParliamentId;
		districtId = globalDistrictId;
	}
	var jsobj={
		assemblyId : constituencyId,
		parliamentid : parliamentid,
		districtId : districtId
		}
	$.ajax({
		type:'GET',
		 url: 'getStatusCountDetailsForGrievanceAction.action',
		 data : {task:JSON.stringify(jsobj)} ,
	}).done(function(result){
		if(result != null){
			buildStatusCountsForGrievanceDetails(result);
		}
	});
}
function buildStatusCountsForGrievanceDetails(result){
var str='';
          str+'<div class="panel-body pad_0">';
          str+'<div class="table-responsive">';
            str+='<table class="table m_0 table-bordered table-condensed m_0" style="font-size:12px">';
              str+='<thead style="background:#f4f4f4">';
                str+='<tr>';
                  str+='<th rowspan="2" style="text-align:center;">STATUS</th>';
                 // for(var i in result){
                   // str+='<th colspan="2" style="text-align:center;"> '+result.locationList[i].name+' </th>';
                    str+='<th colspan="3" style="text-align:center;"> ASSEMBLY </th>';
                    str+='<th colspan="3" style="text-align:center;"> PARLIAMENT </th>';
                    str+='<th colspan="3" style="text-align:center;"> DISTRICT </th>';
                  //}
                str+='</tr>';
                str+='<tr>';
                //for(var i in result){
                  str+='<th style="text-align:center;">Party</th>';
                  str+='<th style="text-align:center;">Govt</th>';
                  str+='<th style="text-align:center;">Welfare</th>';
                  //str+='<th style="text-align:center;">Benifit</th>';
				  str+='<th style="text-align:center;">Party</th>';
                  str+='<th style="text-align:center;">Govt</th>';
                  str+='<th style="text-align:center;">Welfare</th>';
                  //str+='<th style="text-align:center;">Benifit</th>';
				  str+='<th style="text-align:center;">Party</th>';
                  str+='<th style="text-align:center;">Govt</th>';
                  str+='<th style="text-align:center;">Welfare</th>';
                  //str+='<th style="text-align:center;">Benifit</th>';
              //  }
                str+='</tr>';
                str+='</thead>';
                str+='<tbody>';
                  for(var i in result.subList){
                    str+='<tr>';
                    str+='<td>'+result.subList[i].name+'</td>';
                    if(result.subList[i].subList != null && result.subList[i].subList.length>0){
                      for(var k in result.subList[i].subList){
						   if(k!=3 && k!=7 && k!=11){
							   /*str+='<td style="text-align:center;" >'+result.subList[i].subList[k].count+'</td>';						
							  else*/ //if(result.subList[i].subList[k].count != null && result.subList[i].subList[k].count > 0) 
							  if(result.subList[i].subList[k].count != null)
								  if(result.subList[i].name != 'TOTAL')
								str+='<td style="text-align:center;" ><a class="grievanceStatusWiseDetailsCls" style="cursor:pointer;" attr_status_name="'+result.subList[i].name+'" attr_location_type="'+result.subList[i].subList[k].locationName+'" attr_issue_type="'+result.subList[i].subList[k].name+'" >'+result.subList[i].subList[k].count+'</a></td>';
							else
								str+='<td style="text-align:center;">'+result.subList[i].subList[k].count+'</a></td>';
							  else
								str+='<td style="text-align:center;"> - </td>';
						   }
                      }                      
                    }
                    str+='</tr>';
                  }
                str+='</tbody>';
              str+'</table>';
          str+='</div>';
          str+='</div>';
       $("#dataLoadingsImgForStatusCount").hide();
      $("#statusCountsMainDivId").html(str);
       if(participatedConstituencyId != null && participatedConstituencyId > 0){
		$("#grievanceRequestParticepationId").attr("data-original-title","PARTICIPATED CONSTITUENCY");   
		$('#grievanceRequestParticepationId').tooltip();
	}else{
		$("#grievanceRequestParticepationId").attr("data-original-title","OWN CONSTITUENCY");
		$('#grievanceRequestParticepationId').tooltip();
	}
}

$(document).on("click",".grievanceStatusWiseDetailsCls",function(){
	
	$("#grievanceDetailsModalBodyId").html('');
	$("#grievanceDetailsModalDivId").modal("show");
	$("#dataLoadingsImgForGrievanceStatusDetails").show();
	
	var locationId = 0;
	var locationType = $(this).attr("attr_location_type");
	var statusName = $(this).attr("attr_status_name");
	var issueType = $(this).attr("attr_issue_type");
	
	$("#grievanceDetailsModalHeadingId").html("<span style='text-transform:uppercase'>"+locationType+" Wise "+issueType+" Type "+statusName+" Grievance Requests Life Cycle </span>");
	
	if(locationType == "assembly")
		if(participatedConstituencyId != null && participatedConstituencyId > 0)
			locationId = participatedConstituencyId;
		else
			locationId = globalConstituencyId;
	else if(locationType == "parliament")
		if(participatedConstituencyId != null && participatedConstituencyId > 0)
			locationId = participatedParliamentId;
		else
			locationId = globalParliamentId;
	else if(locationType == "district")
		if(participatedConstituencyId != null && participatedConstituencyId > 0)
			locationId = participatedDistrictId;
		else
			locationId = globalDistrictId;
		
	var jsobj={
		locationId : locationId,
		locationType : locationType,
		statusName : statusName,
		issueType : issueType
	}
	$.ajax({
		 type:'POST',
		 url: 'getComplaintsDetailsForGrievanceByLocationAndStatusAction.action',
		 data : {task:JSON.stringify(jsobj)} ,
		}).done(function(result){
			if(result != null){
				var str='';
			str+='<div class="row"><div class="col-md-12"><button type="button" class="pull-right btn btn-primary" id="exportId" onclick="generateExcel1()"> Export To Excel </button></div></div>';
				str+='<div class="table-responsive">';
				str+='<table class="table m_0 table-bordered m_0" id="grievanceStatusWiseTableId" style="font-size:13px !important;">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>Complaint Id</th>';
						str+='<th>Complaint Person Details</th>';
						str+='<th>Subject</th>';
						str+='<th>Description</th>';
						str+='<th>Status</th>';
						str+='<th>Posted Date</th>';
						str+='<th>Last Updated Date</th>';
						str+='<th>Details</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody style="background:#f3f3f3;font-size:12px;">'
				for(var i in result){
					str+='<tr>';
						str+='<td><a href="'+wurl+'/Grievance/homeAction.action?complaintId='+result[i].complaintId+'&typeOfIssue=Grievance" target="_blank">'+result[i].complaintId+'</a></td>';
						str+='<td>';
						str+='<p class="m_0">N:'+result[i].firstName+'</p>';
						str+='<p class="m_0">D:'+result[i].locationName+'</p>';
						str+='<p class="m_0">C:'+result[i].constituency+'</p>';
						str+='<p class="m_0">M:'+result[i].mandalName+'</p>';
						str+='<p class="m_0">V:'+result[i].villageName+'</p>';
						str+='</td>';
						str+='<td>'+result[i].subject+'</td>';
						str+='<td>'+result[i].description+'</td>';
						str+='<td>'+result[i].status+'</td>';
						str+='<td>'+result[i].raisedDate+'</td>';
						str+='<td>';
						if(result[i].updatedDate.length > 0)
							str+=''+result[i].updatedDate+'</td>';
						str+='<td><input type="button" value="View" class="btn btn-sm btn-primary" onclick="getComplaintTrackingDetailsForGrievance('+result[i].complaintId+',\'statusDivIdForGrievance'+i+'\')"/></td>';
					}
				str+='</tbody>'
			str+='</table>';
			str+='</div>';
				
				/*str+='<table class="table m_0 table-bordered m_0">';
					str+='<thead>';
						str+='<tr>';
							str+='<th> Name </th>';
							str+='<th> Issue Description </th>';
							str+='<th> Complaint Info </th>';
							str+='<th> Issue Type </th>';
							str+='<th> Present Status </th>';
							if(issueType == "Benefit")
								str+='<th> Approved Amount </th>';
							str+='<th> View Details </th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>'
					for(var i in result){
						str+='<tr>';
							str+='<td><p>Name : '+result[i].firstName+'</p>';
							//str+='<p>MemberShip No : <a class="tdpCandidatePageCls" style="cursor:pointer;" attr_tdpCadreId="'+result[i].tdpCadreId+'">'+result[i].membershipNo+'</a></p>';
							str+='<p>Mobile No : '+result[i].mobileNo+'</p>';
							str+='</td>';
							str+='<td><p>Subject : '+result[i].subject+'</p>';
							str+='<p>Description : '+result[i].description+'</p>';
							str+='</td>';
							str+='</td>';
							str+='<td><p>Complaint Id : '+result[i].complaintId+'</p>';
							str+='<p>Posted Date : '+result[i].raisedDate+'</p>';
							str+='</td>';
							str+='<td>'+result[i].typeOfIssue+'</td>';
							str+='<td><p>Status : '+result[i].status+'</p>';
							if(result[i].updatedDate.length > 0)
								str+='<p>Last Updated Date : '+result[i].updatedDate+'</p>';
							str+='</td>';
							if(issueType == "Benefit"){
								if(result[i].approvedAmount != null)
									str+='<td>'+result[i].approvedAmount+'</td>';
								else
									str+='<td> - </td>';
							}
								
							str+='<td><input type="button" value="View" class="btn btn-sm btn-primary" onclick="getComplaintTrackingDetailsForGrievance('+result[i].complaintId+',\'statusDivIdForGrievance'+i+'\')"/></td>';
						str+='</tr>';
						str+='<tr id="trstatusDivIdForGrievance'+i+'" style="display:none;">';
						if(issueType == "Benefit")
							str+='<td colspan="7"><div id="statusDivIdForGrievance'+i+'"></div></td>';
						else
							str+='<td colspan="6"><div id="statusDivIdForGrievance'+i+'"></div></td>';
						str+='</tr>';
					}
					str+='</tbody>'
				str+='</table>';*/
				
				$("#dataLoadingsImgForGrievanceStatusDetails").hide();
				$("#grievanceDetailsModalBodyId").html(str);
				$("#grievanceStatusWiseTableId").dataTable();
				$("#grievanceStatusWiseTableId_wrapper").css("margin-top","5px");
			}
		});
});
$(document).on("click",".statusFlowCloseCls",function(){
	$("#viewGrievanceStatusFlow").modal("hide")
	setTimeout(function(){
		$("body").addClass("modal-open")
	},800)
})
function getComplaintTrackingDetailsForGrievance(complaintId,divId){

	$("#viewGrievanceStatusFlow").modal("show")
	var jsobj={
		complaintId : complaintId
	}
	$.ajax({
		 type:'POST',
		 url: 'getAllStatusDetailsByComplaintAction.action',
		 data : {task:JSON.stringify(jsobj)} ,
	}).done(function(result){
		if(result!=null && result.onlystatus!=null && result.onlystatus.trim().length>0){
			 var str='';
			 str+='<div class="ui-steps-border">';
			 str+='<div class="ui small steps" style="width:100%">';
			 str+='<div class="active step" style="padding:20px 8px">';
			    str+='<div class="content">';
			        str+='<div class="title">'+result.onlystatus.toUpperCase() +'</div>';
			     str+='</div>';
			 str+='</div>';
			 str+='</div>';
             str+='</div>';
			  $('#grievanceStatusFlowModalBodyId').html(str);
		}else if(result!=null && result.simpleVOList1!=null && result.simpleVOList2!=null){
			 var str='';
			 str+='<div class="ui-steps-border">';
			 str+='<div class="ui small steps" style="width:100%">';
			 for(var i in result.simpleVOList1){
				 
				 if(result.simpleVOList1[i].dateString==null)
				    str+='<div class="disabled step"  style="padding:20px 8px">';
			     else{
					  if(result.simpleVOList1[i].status=='current')
				         str+='<div class="active step"  style="padding:20px 8px">';
					  else
					     str+='<div class="step" style="padding:20px 8px">';
				 }
				 str+='<div class="content">';
				      str+='<div class="title" style="font-size:15px">'+result.simpleVOList1[i].name.toUpperCase() +'</div>';
					  if(result.simpleVOList1[i].name=='not verified'){
						  if(result.simpleVOList1[i].dateString!=null)
						   str+='<div class="description" style="font-size:11px;">Requested on  '+result.simpleVOList1[i].dateString+'</div>';
					  } 
					  else{
						  if(result.simpleVOList1[i].dateString!=null){
							  str+='<div class="description" style="font-size:11px;">'+result.simpleVOList1[i].dateString+'</div>';
						  } 
					  }
				 str+='</div>';
				 if( result.simpleVOList1[i].name!='completed' && result.simpleVOList1[i].name!='not eligible' && result.simpleVOList1[i].name!='not possible')
				   if(result.simpleVOList1[i].type!=null)
				     str+='<span class="days">'+result.simpleVOList1[i].type+'</span>';
				 str+='</div>';
			 }
			 
			str+='</div>';
            str+='</div>';
			if(result.simpleVOList2!=null){
				
				str+='<div class="pull-right">';
				str+='<button type="button"  class="btn btn-default btn-sm openmodalshowGrievance" attr_div_id="totalGriedivId'+complaintId+'" style="margin-top:5px"><i class="glyphicon glyphicon-eye-open"></i> &nbsp; Total Flow</span>';
				str+='</div>';
				 
				 str+='<div id="totalGriedivId'+complaintId+'" style="display:none;">';
					str+='<table class="table table-bordered">';
				   str+='<th>USERNAME</th>';
				   str+='<th>STATUS</th>';
				   str+='<th>DATE</th>';
				   for(var i in result.simpleVOList2){
					   str+='<tr>';
					   str+='<td>'+result.simpleVOList2[i].username+'</td>';
					   str+='<td>'+result.simpleVOList2[i].name.toUpperCase()+'</td>';
					   str+='<td>'+result.simpleVOList2[i].dateString+'</td>';
					   str+='</tr>';
				   }
				   str+='<tr>';
				  
				   str+='</tr>';
				   str+='</table>';
				   str+='</div>';
				/*str+='<div id="totaldivId">';
				
				str+='<div class="modal fade" id="myModalTotalFlow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">';
				  str+='<div class="modal-dialog" role="document">';
					str+='<div class="modal-content">';
					  str+='<div class="modal-header">';
						str+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
						str+='<h4 class="modal-title" id="myModalLabel">Complaint Status</h4>';
					  str+='</div>';
					  str+='<div class="modal-body">';
						str+='<table class="table table-bordered">';
					   str+='<th>USERNAME</th>';
					   str+='<th>STATUS</th>';
					   str+='<th>DATE</th>';
					   for(var i in result.simpleVOList2){
						   str+='<tr>';
						   str+='<td>'+result.simpleVOList2[i].username+'</td>';
						   str+='<td>'+result.simpleVOList2[i].name.toUpperCase()+'</td>';
						   str+='<td>'+result.simpleVOList2[i].dateString+'</td>';
						   str+='</tr>';
					   }
					   str+='<tr>';
					   
					   str+='</tr>';
					   str+='</table>';
					  str+='</div>';
					str+='</div>';
				  str+='</div>';
				str+='</div>';
				
			   str+='</div>';*/
			}
			 $('#grievanceStatusFlowModalBodyId').html(str);
		 }else{
			 
			 var str='';
			 str+='<div class="ui-steps-border">';
			 str+='<div class="ui small steps" style="width:100%">';
			 for(var i in result.simpleVOList1){
				 
				 if(result.simpleVOList1[i].dateString==null)
				    str+='<div class="disabled step">';
			     else{
					  if(result.simpleVOList1[i].status=='current')
				         str+='<div class="active step">';
					  else
					     str+='<div class="step">';
				 }
				 str+='<div class="content">';
				      str+='<div class="title">'+result.simpleVOList1[i].name.toUpperCase() +'</div>';
					  if(result.simpleVOList1[i].name=='not verified'){
						  if(result.simpleVOList1[i].dateString!=null)
						  str+='<div class="description">Requested on  '+result.simpleVOList1[i].dateString+'</div>';
					  } 
					  else{
						  if(result.simpleVOList1[i].dateString!=null){
							  str+='<div class="description">'+result.simpleVOList1[i].dateString+'</div>';
						  } 
					  }
				 str+='</div>';
				 
				 if( result.simpleVOList1[i].name!='completed' && result.simpleVOList1[i].name!='not eligible' && result.simpleVOList1[i].name!='not possible')
				   if(result.simpleVOList1[i].type!=null)
				     str+='<span class="days">'+result.simpleVOList1[i].type+'</span>';
				 str+='</div>';
			 }
			 
			str+='</div>';
            str+='</div>';
			 $('#grievanceStatusFlowModalBodyId').html(str);
		 }
	});
}
/*function getApprovedAmountsForGrievance(){
	//$("#statusCountsMainDivId").html('');
	
	var constituencyId = 0;
	var parliamentid = 0;
	var districtId = 0;
	if(participatedConstituencyId != null && participatedConstituencyId > 0){
		constituencyId = participatedConstituencyId;
		parliamentid = participatedParliamentId;
		districtId = participatedDistrictId;
	}
	else{
		constituencyId = globalConstituencyId;
		parliamentid = globalParliamentId;
		districtId = globalDistrictId;
	}
	var jsobj={
		constiId : constituencyId,
		parliamentId : parliamentid,
		districtId : districtId
		}
	$.ajax({
		type:'GET',
		 url: 'getApprovedAmountDetailsByLocationAction.action',
		 data : {task:JSON.stringify(jsobj)} ,
	}).done(function(result){
		if(result != null){
			//buildStatusCountsForGrievanceDetails(result);
			buildGrievanceRequests(result)
		}
	});
}*/

function getApprovedAmountsForGrievance(){
	//$("#statusCountsMainDivId").html('');
	$("#dataLoadingsImgForGrievanceRequests").show();
	var constituencyId = 0;
	var parliamentid = 0;
	var districtId = 0;
	if(participatedConstituencyId != null && participatedConstituencyId > 0){
		constituencyId = participatedConstituencyId;
		parliamentid = participatedParliamentId;
		districtId = participatedDistrictId;
	}
	else{
		constituencyId = globalConstituencyId;
		parliamentid = globalParliamentId;
		districtId = globalDistrictId;
	}
	var jsobj={
		constiId : constituencyId,
		parliamentId : parliamentid,
		districtId : districtId
		}
	$.ajax({
		type:'GET',
		 url: 'getApprovedAmountDetailsForGovtAndWilfareByLocationAction.action',
		 data : {task:JSON.stringify(jsobj)} ,
	}).done(function(result){
		if(result != null){
			//buildStatusCountsForGrievanceDetails(result);
			buildGrievanceAmountDetails(result);
		}
	});
}

function buildGrievanceAmountDetails(result){
	var str='';
	var noOfBenForTotGovtCount=0;
	var approvedAmtForTotGovtCount=0;
	var otherBenForTotGovtCount=0;
	var noOfBenForTotWelfareCount=0;
	var approvedAmtForTotWelfareCount=0;
	var otherBenForTotWelfareCount=0;
	str+='<div class="table-responsive">';
	str+='<table class="table m_0 table-bordered m_0" style="font-size:13px;">';
		str+='<thead style="background:#f2f2f2">';
			str+='<tr>';
				str+='<th rowspan="2"></th>';
				if(result[2].simpleVOList1.length > 1){
					str+='<th colspan="3" style="text-align:center;text-transform:uppercase">Govt.</th>';
					str+='<th colspan="3" style="text-align:center;text-transform:uppercase">Welfare</th>';
				}
				else{
					if(result[2].simpleVOList1[0].name == 'Govt')
						str+='<th colspan="3" style="text-align:center;text-transform:uppercase">Govt.</th>';
					else if(result[2].simpleVOList1[0].name == 'Welfare')
						str+='<th colspan="3" style="text-align:center;text-transform:uppercase">Welfare</th>';
				}
				//str+='<th colspan="3">District</th>';
			str+='</tr>';
			str+='<tr>';
				if(result[2].simpleVOList1.length > 1){
					str+='<th style="text-transform:uppercase">No. of Beneficiaries</th>';
					str+='<th style="text-transform:uppercase">Approved Amount</th>';
					str+='<th style="text-transform:uppercase">Other Benifits</th>';
					str+='<th style="text-transform:uppercase">No. of Beneficiaries</th>';
					str+='<th style="text-transform:uppercase">Approved Amount</th>';
					str+='<th style="text-transform:uppercase">Other Benifits</th>';
				}
				else{
					str+='<th style="text-transform:uppercase">No. of Beneficiaries</th>';
					str+='<th style="text-transform:uppercase">Approved Amount</th>';
					str+='<th style="text-transform:uppercase">Other Benifits</th>';
				}
				str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
				str+='<tr>';
				str+='<td class="text-bold" style="text-transform:uppercase">'+result[i].name+'</td>';
				for(var j in result[i].simpleVOList1){
					if(result[i].simpleVOList1[j].count != null){
						str+='<td><a class="grievanceBenifitsStatusWiseDetailsCls" style="cursor:pointer;" attr_issueType="'+result[i].simpleVOList1[j].name+'" attr_location_type="'+result[i].name+'" attr_benifit_type="total">'+result[i].simpleVOList1[j].count+'</a></td>';
						if(result[i].simpleVOList1[j].name == 'Govt')
						noOfBenForTotGovtCount = parseInt(noOfBenForTotGovtCount) + parseInt(result[i].simpleVOList1[j].count);
						else if(result[i].simpleVOList1[j].name == 'Welfare')
						noOfBenForTotWelfareCount = parseInt(noOfBenForTotWelfareCount) + parseInt(result[i].simpleVOList1[j].count);
					}
					else
						str+='<td> - </td>';
					if(result[i].simpleVOList1[j].approvedAmount != null){
						str+='<td>'+result[i].simpleVOList1[j].approvedAmount+'</td>';
					if(result[i].simpleVOList1[j].name == 'Govt')
					 approvedAmtForTotGovtCount = parseInt(approvedAmtForTotGovtCount) + parseInt(result[i].simpleVOList1[j].approvedAmount);
					else if(result[i].simpleVOList1[j].name == 'Welfare')
					 approvedAmtForTotWelfareCount = parseInt(approvedAmtForTotWelfareCount) + parseInt(result[i].simpleVOList1[j].approvedAmount);
					}
					else
						str+='<td> - </td>';
					if(result[i].simpleVOList1[j].otherBenifitCount != null){
						str+='<td><a class="grievanceBenifitsStatusWiseDetailsCls" style="cursor:pointer;" attr_issueType="'+result[i].simpleVOList1[j].name+'" attr_location_type="'+result[i].name+'" attr_benifit_type="other">'+result[i].simpleVOList1[j].otherBenifitCount+'</a></td>';
						if(result[i].simpleVOList1[j].name == 'Govt')
						otherBenForTotGovtCount = parseInt(otherBenForTotGovtCount) + parseInt(result[i].simpleVOList1[j].otherBenifitCount);
						else if(result[i].simpleVOList1[j].name == 'Welfare')
						otherBenForTotWelfareCount = parseInt(otherBenForTotWelfareCount) + parseInt(result[i].simpleVOList1[j].otherBenifitCount);
					}
					else
						str+='<td> - </td>';
				}
				str+='</tr>';
			}
			 str+='<tr>';
			str+='<td class="text-bold" style="text-transform:uppercase"> Total </td>';
			if(result[2].simpleVOList1.length > 1){
				str+='<td class="text-bold">'+noOfBenForTotGovtCount+'</td>';
				str+='<td class="text-bold">'+approvedAmtForTotGovtCount+'</td>';
				str+='<td class="text-bold">'+otherBenForTotGovtCount+'</td>';
				str+='<td class="text-bold">'+noOfBenForTotWelfareCount+'</td>';
				str+='<td class="text-bold">'+approvedAmtForTotWelfareCount+'</td>';
				str+='<td class="text-bold">'+otherBenForTotWelfareCount+'</td>';
			}
			else{
				if(result[2].simpleVOList1[0].name == 'Govt'){
					str+='<td class="text-bold">'+noOfBenForTotGovtCount+'</td>';
					str+='<td class="text-bold">'+approvedAmtForTotGovtCount+'</td>';
					str+='<td class="text-bold">'+otherBenForTotGovtCount+'</td>';
				}
				else if(result[2].simpleVOList1[0].name == 'Welfare'){
					str+='<td class="text-bold">'+noOfBenForTotWelfareCount+'</td>';
					str+='<td class="text-bold">'+approvedAmtForTotWelfareCount+'</td>';
					str+='<td class="text-bold">'+otherBenForTotWelfareCount+'</td>';
				}
			}
			str+='</tr>'; 
		str+='</tbody>';  
	str+='</table>';
	str+='</div>';
	
	$("#dataLoadingsImgForGrievanceRequests").hide();
	$("#grievanceRequestsId").html(str);
	if(participatedConstituencyId != null && participatedConstituencyId > 0){
		$("#grievanceBenefitParticepationId").attr("data-original-title","PARTICIPATED CONSTITUENCY"); 
		$('#cadreEnrolementParticepateStatusId').tooltip();
		$('#cadreEnrolementParticepateStatusId1').tooltip();			
	}else{
		$("#grievanceBenefitParticepationId").attr("data-original-title","OWN CONSTITUENCY");
		$('#cadreEnrolementParticepateStatusId').tooltip();
		$('#cadreEnrolementParticepateStatusId1').tooltip();	
	}
}

$(document).on("click",".grievanceBenifitsStatusWiseDetailsCls",function(){
	$("#grievanceBenifitsDetailsModalBodyId").html('');
	$("#grievanceBenifitsDetailsModalDivId").modal("show");
	$("#dataLoadingsImgForGrievanceBenifitsStatusDetails").show();
	
	var locationId = 0;
	var locationType = $(this).attr("attr_location_type");
	var issueType = $(this).attr("attr_issueType");
	var benifitType = $(this).attr("attr_benifit_type");
	
	$("#grievanceBenifitsDetailsModalHeadingId").html("<span style='text-transform:uppercase'>"+locationType+" Wise "+issueType+" Type Complaint Details </span>");
	
	if(locationType == "assembly")
		if(participatedConstituencyId != null && participatedConstituencyId > 0)
			locationId = participatedConstituencyId;
		else
			locationId = globalConstituencyId;
	else if(locationType == "parliament")
		if(participatedConstituencyId != null && participatedConstituencyId > 0)
			locationId = participatedParliamentId;
		else
			locationId = globalParliamentId;
	else if(locationType == "district")
		if(participatedConstituencyId != null && participatedConstituencyId > 0)
			locationId = participatedDistrictId;
		else
			locationId = globalDistrictId;
		
	var jsobj={
		locationId : locationId,
		locationType : locationType,
		typeOfIssue : issueType,
		benifitType : benifitType
	}
	$.ajax({
		 type:'POST',
		 url: 'getGrievanceBenifitsComplaintsInfoByLocationAction.action',
		 data : {task:JSON.stringify(jsobj)} ,
	}).done(function(result){
		if(result != null){
			var str='';
			str+='<div class="table-responsive">';
			str+='<table class="table m_0 table-bordered m_0" id="grievanceBenifitsTableId" style="font-size:13px !important;">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>Complaint Id</th>';
						str+='<th>Complaint Person Details</th>';
						str+='<th>Subject</th>';
						str+='<th>Description</th>';
						str+='<th>Issue Type</th>';
						if(benifitType == 'total')
							str+='<th>Approved Amount</th>';
						else if(benifitType == 'other')
							str+='<th>Support Purpose</th>';
						str+='<th>Posted Date</th>';
						str+='<th>Last Updated Date</th>';
						str+='<th>View Details</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody style="background:#f3f3f3;font-size:12px;">'
				for(var i in result){
					str+='<tr>';
						str+='<td><a href="'+wurl+'/Grievance/homeAction.action?complaintId='+result[i].complaintId+'&typeOfIssue=Grievance" target="_blank">'+result[i].complaintId+'</a></td>';
						str+='<td>';
						str+='<p class="m_0">N:'+result[i].firstName+'</p>';
						str+='<p class="m_0">D:'+result[i].locationName+'</p>';
						str+='<p class="m_0">C:'+result[i].constituency+'</p>';
						str+='<p class="m_0">M:'+result[i].mandalName+'</p>';
						str+='<p class="m_0">V:'+result[i].villageName+'</p>';
						str+='</td>';
						str+='<td>'+result[i].subject+'</td>';
						str+='<td>'+result[i].description+'</td>';
						str+='<td>'+result[i].issueType+'</td>';
						if(benifitType == 'total')
							str+='<td>'+result[i].approvedAmount+'</td>';
						else if(benifitType == 'other')
							str+='<td>'+result[i].supportPurpose+'</td>';
						str+='<td>'+result[i].raisedDate+'</td>';
						str+='<td>';
						if(result[i].updatedDate.length > 0)
							str+=''+result[i].updatedDate+'</td>';
						str+='<td><input type="button" value="View" class="btn btn-sm btn-primary" onclick="getComplaintTrackingDetailsForGrievance('+result[i].complaintId+',\'statusDivIdForGrievance'+i+'\')"/></td>';
					}
				str+='</tbody>'
			str+='</table>';
			str+='</div>';
			
			$("#dataLoadingsImgForGrievanceBenifitsStatusDetails").hide();
			$("#grievanceBenifitsDetailsModalBodyId").html(str);
			$("#grievanceBenifitsTableId").dataTable();
			$("#grievanceBenifitsTableId").removeClass("dataTable");
		}
	});
});

function buildGrievanceRequests(result)
{
	var str = '';
	str+='';
	str+='';
	  str+='<ul class="nav nav-tabs" role="tablist">';
		str+='<li role="presentation" class="active"><a href="#govtGrievance" aria-controls="govtGrievance" role="tab" data-toggle="tab">Govenment</a></li>';
		str+='<li role="presentation"><a href="#welfareGrievance" aria-controls="welfareGrievance" role="tab" data-toggle="tab">Welfare</a></li>';
		str+='<li role="presentation"><a href="#partyGrievance" aria-controls="partyGrievance" role="tab" data-toggle="tab">Party</a></li>';
	  str+='</ul>';
	  str+='<div class="tab-content">';
		str+='<div role="tabpanel" class="tab-pane active" id="govtGrievance">';
			str+='<div class="table-responsive">';
			str+='<table>';
			str+='<thead>';
				str+='<tr>';
					str+='<th colspan="3">Assembly</th>';
					str+='<th colspan="3">Parliament</th>';
					str+='<th colspan="3">District</th>';
				str+='</tr>';
				str+='<tr>';
					str+='<th>Category</th>';
					str+='<th>Count</th>';
					str+='<th>Amount</th>';
					str+='<th>Category</th>';
					str+='<th>Count</th>';
					str+='<th>Amount</th>';
					str+='<th>Category</th>';
					str+='<th>Count</th>';
					str+='<th>Amount</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				str+='<tr>';
					for(var i in result){
						for(var j in result[i].simpleVOList1){
							if(result[i].simpleVOList1[j].name == 'Govt'){
								for(var k in result[i].simpleVOList1[j].simpleVOList1){
									str+='<td>'+result[i].simpleVOList1[j].simpleVOList1[k].issueType+'</td>';
									str+='<td>'+result[i].simpleVOList1[j].simpleVOList1[k].count+'</td>';
									str+='<td>'+result[i].simpleVOList1[j].simpleVOList1[k].approvedAmount+'</td>';
								}
							}
						}
					}
				str+='</tr>';
			str+='</tbody>';
			str+='</table>';
		str+='</div>';
		str+='</div>';
		str+='<div role="tabpanel" class="tab-pane" id="welfareGrievance">';
			str+='<div class="table-responsive">';
			str+='<table>';
			str+='<thead>';
				str+='<tr>';
					str+='<th colspan="3">Assembly</th>';
					str+='<th colspan="3">Parliament</th>';
					str+='<th colspan="3">District</th>';
				str+='</tr>';
				str+='<tr>';
					str+='<th>Category</th>';
					str+='<th>Count</th>';
					str+='<th>Amount</th>';
					str+='<th>Category</th>';
					str+='<th>Count</th>';
					str+='<th>Amount</th>';
					str+='<th>Category</th>';
					str+='<th>Count</th>';
					str+='<th>Amount</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				str+='<tr>';
					for(var i in result){
						for(var j in result[i].simpleVOList1){
							if(result[i].simpleVOList1[j].name == 'Welfare'){
								for(var k in result[i].simpleVOList1[j].simpleVOList1){
									str+='<td>'+result[i].simpleVOList1[j].simpleVOList1[k].issueType+'</td>';
									str+='<td>'+result[i].simpleVOList1[j].simpleVOList1[k].count+'</td>';
									str+='<td>'+result[i].simpleVOList1[j].simpleVOList1[k].approvedAmount+'</td>';
								}
							}
						}
					}
				str+='</tr>';
			str+='</tbody>';
			str+='</table>';
		str+='</div>';
		str+='</div>';
		str+='<div role="tabpanel" class="tab-pane" id="partyGrievance">';
			str+='<div class="table-responsive">';
			str+='<table>';
			str+='<thead>';
				str+='<tr>';
					str+='<th colspan="3">Assembly</th>';
					str+='<th colspan="3">Parliament</th>';
					str+='<th colspan="3">District</th>';
				str+='</tr>';
				str+='<tr>';
					str+='<th>Category</th>';
					str+='<th>Count</th>';
					str+='<th>Amount</th>';
					str+='<th>Category</th>';
					str+='<th>Count</th>';
					str+='<th>Amount</th>';
					str+='<th>Category</th>';
					str+='<th>Count</th>';
					str+='<th>Amount</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				str+='<tr>';
					for(var i in result){
						for(var j in result[i].simpleVOList1){
							if(result[i].simpleVOList1[j].name == 'Party'){
								for(var k in result[i].simpleVOList1[j].simpleVOList1){
									str+='<td>'+result[i].simpleVOList1[j].simpleVOList1[k].issueType+'</td>';
									str+='<td>'+result[i].simpleVOList1[j].simpleVOList1[k].count+'</td>';
									str+='<td>'+result[i].simpleVOList1[j].simpleVOList1[k].approvedAmount+'</td>';
								}
							}
						}
					}
				str+='</tr>';
			str+='</tbody>';
			str+='</table>';
		str+='</div>';  
		str+='</div>';
	  str+='</div>';
	$("#grievanceRequestsId").html(str)
	if(participatedConstituencyId != null && participatedConstituencyId > 0){
		$("#grievanceBenefitParticepationId").attr("title","PARTICIPATED CONSTITUENCY");   
	}else{
		$("#grievanceBenefitParticepationId").attr("title","OWN CONSTITUENCY");
	}
}
$(document).on("click",".CloseIcon",function(){
	$("#mobileDetailsDivId,.changeIcon").hide();
});
getMobileNumberDetailsByTdpCadre();
function getMobileNumberDetailsByTdpCadre(){
//	$("#mobileDetailsDivId,.changeIcon").toggle();
	var jsobj={
		cadreId : globalCadreId
	}
	$.ajax({
		 type:'POST',
		 url: 'getMobileNumberDetailsByTdpCadreAction.action',
		 data : {task:JSON.stringify(jsobj)} ,
	}).done(function(result){
		if(result.subList == null || result.subList =="" || result.subList ==undefined){
			$("#mobileDetailsId").hide();
		}
		else if(result != null){
			$("#mobileDetailsId").show();
			if(result.subList != null && result.subList.length > 0){
				var results = result.subList[0];
				if(results.connectionType != null)
					$("#mobileConnectionTypeId").html(results.connectionType);
				if(results.networkProvider != null)
					$("#mobileNetworkProviderId").html(results.networkProvider);
				if(results.brand != null && results.brandModel != null)
					$("#mobileBrandId").html(results.brand+" - "+results.brandModel);
				else if(results.brand != null)
					$("#mobileBrandId").html(results.brand);
				if(results.feature != null)
					$("#mobileFeatureId").html(results.feature);
				if(results.networkType != null)
					$("#mobileNetworkTypeId").html(results.networkType);
			}
		}
	});
}
$(document).on("click","#mobileDetailsId",function(){
	$("#mobileDetailsDivId,.changeIcon").toggle();
});

getDebateDetailsOfCadre();
getDebateCount();
function getDebateDetailsOfCadre(){
//	$("#debateCountId").html('');
	$("#debateModelId").html('');		
	
	$("#dataLoadingsImgForDebateModelId").show();
	
	var jsobj={
		tdpCadreId : globalCadreId,
		stateId:1
	}
	$.ajax({
		 type:'POST',
		 url: 'getTotalAttendedDebatesOfCadreAction.action',
		 data : {task:JSON.stringify(jsobj)} ,
	}).done(function(result){
		var str = '';
		$("#dataLoadingsImgForDebateModelId").hide();
		if(result !=null && result.length>0){
			
			//$("#debateMainDivId").show();
			
			//Count setting
			//$("#debateCountId").html(result.length);
			
			str+= '<div class="col-md-12 col-xs-12 col-sm-12">';
		
			str+= '<div class="table-responsive">';
			str+= '<table class="table table-bordered table-condensed">';
				str+= '<thead style="background:#ccc">';
					str+='<th>Subject</th>';
					str+='<th>Date</th>';
					str+='<th>Telecast Time</th>';
					str+='<th>Observer</th>';
					str+='<th>Channel</th>';
					
					
					if(result[0].participantsList !=null && result[0].participantsList.length>0 
					&& result[0].participantsList[0].scaleList !=null 
					&& result[0].participantsList[0].scaleList.length>0){
						for(var i in result[0].participantsList[0].scaleList){
							var namee='';
							if(result[0].participantsList[0].scaleList[i].name !=null && result[0].participantsList[0].scaleList[i].name.length>0){
								namee=result[0].participantsList[0].scaleList[i].name.split("(")[0];
							}
							str+='<th>'+namee+'</th>';
						}						
					}
				str+= '</thead>';
				str+= '<tbody>';				
					for(var i in result){
						str+='<tr>';
						var name="";
						var title="";
						if(result[i].debateNames !=null && result[i].debateNames.length>0){
							for(var j in result[i].debateNames){
							
								name = name.concat(result[i].debateNames[j]);
							}							
						}
						if(name !="")
							title=getTitleContent(name,30);
						
							str+='<td class="debateDetailsCls pointer" attr_debateId='+result[i].debateId+'><a>'+title+'</a></td>';
							str+='<td>'+result[i].date+'</td>';
							str+='<td>'+result[i].startTime+' To '+result[i].endTime+'</td>';
							str+='<td>'+result[i].observorsList[0]+'</td>';
							str+='<td>'+result[i].channelName+'</td>';
							
							if(result[i].participantsList !=null && result[i].participantsList.length>0){
								for(var j in result[i].participantsList){
									if(result[i].participantsList[j].id == result[0].candidateId && result[i].participantsList[j].scaleList !=null && result[i].participantsList[j].scaleList.length>0){
										for(var k in result[i].participantsList[j].scaleList){
											str+='<td class="text-center">'+result[i].participantsList[j].scaleList[k].perc+'</td>';
										}									
									}
								}
							}
							
						str+='</tr>';
					}
					
				str+= '</tbody>';
			str+= '</table>';
			str+= '</div>';
			str+= '</div>';
			str+= '</div>';
			str+= '</div>';
		}
		
		$("#debateModelId").html(str);
		
	});
	
}
function getDebateCount(){
	$("#debateCountId").html('');
	$("#debateMainDivId").hide();
	var jsobj={
			tdpCadreId : globalCadreId
		}
		$.ajax({
			 type:'POST',
			 url: 'getTotalAttendedDebatesOfCadreNewAction.action',
			 data : {task:JSON.stringify(jsobj)} ,
		}).done(function(result){
			if(result !=null && result>0){
				$("#debateMainDivId").show();
				$("#debateCountId").html(result);
			}
		});
}

$(document).on("click","#debateCountId",function(){
	$("#debateModelDivId").modal("show");
});
	function getTitleContent(articleTitle,showCharVal){
		ellipsetext=". . ."
        var showChar = showCharVal;
        var content = articleTitle;
		if(content!=null){
			if(content.length > showChar) {
            var c = content.substr(0, showChar);
            var h = content.substr(showChar-1, content.length - showChar);
            var html = c + ellipsetext;
            return html;
			}
		}
        
		return articleTitle;
    }
	$(document).on("click",".debateDetailsCls",function(){
		var debateId = $(this).attr("attr_debateId");		
		window.open('debateReportAction.action?debateId='+debateId+'','_blank');		 
	});
	
	getPartyWisePerformanceOfCadre();
	function getPartyWisePerformanceOfCadre(){
		
		$('#debatePerformanceId').html('');		
		$("#dataLoadingsImgForDebateperformId").show();
		
		var jsobj={
		tdpCadreId : globalCadreId
		}
		$.ajax({
			 type:'POST',
			 url: 'getPartyWisePerformanceOfCadreAction.action',
			 data : {task:JSON.stringify(jsobj)} ,
		}).done(function(result){
			$("#dataLoadingsImgForDebateperformId").hide();
			buildFun1(result);
		});
	}
	function buildFun1(result){
		if(result != null)
		{
			var str = '';
			str += '<div class="col-md-12 col-xs-12 col-sm-12">';
				str+= '<div class="panel panel-default">';
				str+= '<div class="panel-heading"><h4 class="panel-title">PARTY WISE OVER ALL PERFORMANCE </h4></div>';
				str+= '<div class="panel-body">';
				str += '<div class="table-responsive">';
					str += '<table class="table table-bordered table-condensed verticalAlign" id="firstReport">';
					str += '<thead style="background:#ccc">';
					str += '<tr class=" text-center">';
						str += '<th rowspan="2">Party</th>';
						str += '<th rowspan="2">Total Debates/</br>Performance Count</th>';
						str += '<th colspan="4" class="text-center">Ranking</th>';
						str += '<th rowspan="2">Subject</th>';
						str += '<th rowspan="2">Presentation</th>	';
						str += '<th rowspan="2">Counter Attack</th>';
						str += '<th rowspan="2">Body Language</th>';						
					str += '</tr>';
					
					str += '<tr class=" text-center verticalAlign">';
						str += '<th>1 st</th>';
						str += '<th>2 nd</th>';
						str += '<th>3 rd</th>';
						str += '<th>4 th</th>';
					str += '</tr>';
					str += '</thead>';
					str += '<tbody>';
					for(var i in result)
					{
						str += '<tr class="text-center">';
							str += '<td>'+result[i].partyName+'</td>';
							str += '<td>'+result[i].totalDebates+'/'+result[i].debateScale+'</td>';
							str += '<td>'+result[i].rankingVO.firstRank+'</td>';
							str += '<td>'+result[i].rankingVO.secondRank+'</td>';
							str += '<td>'+result[i].rankingVO.thirdRank+'</td>';
							str += '<td>'+result[i].rankingVO.fourthRank+'</td>';
							for(var j in result[i].subList)
							{
								str += '<td>'+result[i].subList[j].debateScale+'</td>';
							}
						str += '</tr>';
					}
					str += '</tbody>';
					str += '</table>';	
				str += '</div>';	
				str += '</div>';	
				str += '</div>';	
			str += '</div>';	
			$('#debatePerformanceId').html(str);
			
		}
		else{	
			$('#debatePerformanceId').html('<div align="center"> <b>No Data Available </b>.</div>');				
		}
	}
	getPartyWiseStrongAndWeakTopicOfCadre();
	function getPartyWiseStrongAndWeakTopicOfCadre(){
		$('#debateStrongWeekId').html('');	
		$("#dataLoadingsImgForDebateStrId").show();
		
		var jsobj={
			tdpCadreId : globalCadreId
		}
		$.ajax({
			 type:'POST',
			 url: 'getPartyWiseStrongAndWeakTopicOfCadreAction.action',
			 data : {task:JSON.stringify(jsobj)} ,
		}).done(function(result){
			$("#dataLoadingsImgForDebateStrId").hide();
			buildFun2(result);
		});
	}
	function buildFun2(result){
		var str = '';
		if(result != null )
		{
				for(var i in result)
				{
					if(result[i] != null)
					{
						str += '<div class="col-md-12 col-xs-12 col-sm-12">';
					str += '<div class="panel panel-default">';
					str += '<div class="panel-heading">';
						str += '<h4 class="panel-title">TOPIC WISE STRONG AND WEAK PERFORMANCES</h4>';
					str += '</div>';
					str += '<div class="panel-body">';
					str += '<div class="row">';
					str += '<div class="col-md-6 col-xs-12 col-sm-12">';
					//str += '<h4>'+result[i].party+' Strong</h4>';
					str += '<h4> STRONG</h4>';
					str += '<div class="table-responsive">';
					str += '<table class="table table-bordered table-condensed verticalAlign" >';
					str += '<thead style="background:#ccc">';
					str += '<tr>';
					str += '<th>Topic</th>';
					str += '<th>Channel</th>';
					str += '</tr>';
					str += '</thead>';
					str += '<tbody>';
					if(result[i].top != null && result[i].top.length > 0)
					{
						for(var j in result[i].top)
						{
							str += '<tr>';
							if(result[i].top[j].count > 1)
							{
								str += '<td class="debateDetailsCls pointer" attr_debateId='+result[i].top[j].debateId+'><a>'+result[i].top[j].subject+'('+result[i].top[j].count+')</a></td>';
							}
							else
							{
								str += '<td class="debateDetailsCls pointer" attr_debateId='+result[i].top[j].debateId+'><a>'+result[i].top[j].subject+'</a></td>';
							}
							
							str += '<td>'+result[i].top[j].channel+'</td>';
							str += '</tr>';
						}
					}
					str += '</tbody>';
					str += '</table>';
					str += '</div>';
					str += '</div>';
					
					str += '<div class="col-md-6 col-xs-12 col-sm-12">';
					str += '<h4>WEAK</h4>';
					str += '<div class="table-responsive">';
					str += '<table class="table table-bordered table-condensed verticalAlign" >';
					str += '<thead style="background:#ccc">';
					str += '<tr>';
					str += '<th>Topic</th>';
					str += '<th>Channel</th>';
					str += '</tr>';
					str += '</thead>';
					str += '<tbody>';
					if(result[i].weak != null && result[i].weak.length > 0)
					{
						for(var j in result[i].weak)
						{
							str += '<tr>';
							if(result[i].weak[j].count > 1)
							{
								str += '<td class="debateDetailsCls pointer" attr_debateId='+result[i].weak[j].debateId+'><a>'+result[i].weak[j].subject+'('+result[i].weak[j].count+')</a></td>';
							}
							else
							{
								str += '<td class="debateDetailsCls pointer" attr_debateId='+result[i].weak[j].debateId+'><a>'+result[i].weak[j].subject+'</a></td>';
							}
							str += '<td>'+result[i].weak[j].channel+'</td>';
							str += '</tr>';
						}
					}
					str += '</tbody>';
					str += '</table>';
					str += '</div>';
					str += '</div>';
					str += '</div>';
					str += '</div>';
					str += '</div>';
					str += '</div>';
					$('#searchDataImg').hide();
					}
					
				}
				
				$('#debateStrongWeekId').html(str);				
		}
		else{
			$('#debateStrongWeekId').html('<div align="center"> <b>No Data Available </b>.</div>');				
		}
	}
function saveCadreNotesInformationDetails(itemsCount,startIndex){
	 var notes = $(".jqte_editor").html();
if(notes.trim() ==""){
		  $("#errorUpCallId").html("Notes Required.");
		  return;
	  }	
var validTextCount=0;
var invalidTextCount=0;
var NotesValueArr = notes.split(";");
for(var i=0;i<NotesValueArr.length;i++){
	if(NotesValueArr[i]=="&nbsp" || NotesValueArr[i].trim()=="<br>")
		invalidTextCount++;	
	else{
		validTextCount++;
		break;
	}
}	 
if(validTextCount >0){
	var jObj={
		tdpCadreId:globalCadreId,
		notes:notes,
		globalPrimaryId : globalPrimaryId
	};
	$.ajax({
	  type:'POST',
	  url: 'saveCadreNotesInformationDetailsAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jObj)},
	  }).done(function(result){
			if(result != null){
				 if(result.message == "Success"){
					$("#errorUpCallId").html("Notes Data Successfully Updated...");
					$(".jqte_editor").html('');
					$("#errorUpCallId").html("");
					getCadreNotesInformationDetails(globalStartIndex);
					$("#updateButnId").hide();
				}else{
					$("#errorUpCallId").html("Error Occured Try agian..");
				} 
				globalPrimaryId = 0;
			}
	  });
}
else {
	$("#errorUpCallId").html("Notes Required.");
}
	
}
var globalStartIndex = 0;
function getCadreNotesInformationDetails(startIndex){ 
 globalStartIndex = startIndex;
$('#notesExisting').html("");
$("#updateNotesButtonId").show();
	var jObj={
		tdpCadreId:globalCadreId,
		startIndex:globalStartIndex,
		maxIndex : 10
	};
	$.ajax({
	  type:'POST',
	  url: 'getCadreNotesInformationDetailsAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jObj)},
	  }).done(function(result){
			if(result != null && result.length>0){
				buildCadreNotesDetails(result,jObj);
				$("#NoNotesId").html("");
			}
			else if (result == null || result.length ==0){
				$('#notesExisting').html(" <b> No notes are available...</b>");	
				$(".paginationCls").hide();
				$(".paginationCls").html("");
			}
	  });
}
function buildCadreNotesDetails(result,jObj){
	var str = '';
if(result != null && result.length>0){
	var loginUserId = result[0].levelId;//HamletId
	str += '<ul class="apptStatusTracking">';
for(var i in result)
{
	str += '<li id="li'+result[i].id+'">';
	if(loginUserId == result[i].hamletId){
		str += '<span><i class="glyphicon glyphicon-edit editIconCls" attr_id="'+result[i].id+'" attr_div="tejaid'+i+'" title="Click Here To Get Edit Notes"></i><i class="glyphicon glyphicon-remove removeIconCls" attr_notes_id='+result[i].id+' title="Click Here To Get Delete This Notes"></i></span>';
	}else{
		str += '<span></span>';
	}
	
	str += '<div class="arrow_box_left">';
	if(result[i].aliancedWith != null && result[i].aliancedWith.trim() != ""){
		str +='<span class="m_0">Created By:<strong>'+result[i].hamletName+'</strong> At <strong>'+result[i].persent+'</strong>  Latest Time:<strong>'+result[i].aliancedWith+'</strong></span>';	
	}else{
		str +='<span class="m_0">Created By:<strong>'+result[i].hamletName+'</strong> At <strong>'+result[i].persent+'</strong></span>';
	}
	
	str +='<div class="m_0" id="tejaid'+i+'">'+result[i].name+'</div>';
	str += '</div>';
	str += '</li>';
}
str += '</ul>';
	$('#notesExisting').html(str);	
	    if(result != null && result.length>0 && result[0].totalVoters != null && result[0].totalVoters > 0){
				$("#notesId").css({'color': 'green'});
	$("#notesId").addClass('text-success');//green color
	}
	else{
			$("#notesId").css({'color': 'green'});
	$("#notesId").removeClass('text-success');//gray color	
	} 
	if(jObj.startIndex==0){
	var ttalCount =10;
		if(result != null && result.length>0 && result[0].totalVoters != null && result[0].totalVoters > 0)
				ttalCount = result[0].totalVoters;
		$(".paginationCls").pagination({
			items: ttalCount,
			itemsOnPage: 10,
			cssStyle: 'light-theme',
			hrefTextPrefix: '#pages-',
			onPageClick: function(pageNumber) {
				var num=(pageNumber-1)*10;
					getCadreNotesInformationDetails(num);
			}
		});
	}		
  }
}
$(document).on("click",".removeIconCls",function(){
	var cadreNotes = $(this).attr("attr_notes_id"); 
	$("#hiddencadreNotesId").val(cadreNotes);
	deleteCadreNotesData(cadreNotes);	
});
function deleteCadreNotesData(cadreNotes){ 
var notes = $(".apptStatusTracking").html();	 
	var r=confirm("Are you sure want to delete it?")
	if (r)	
	{
	var jsobj = {
		cadreNotes:cadreNotes
	}
	$.ajax({
     type: "POST",
     url: "deleteCadreNotesDataAction.action",
	 dataType: 'json',
     data: {task:JSON.stringify(jsobj)}
    })
    .done(function( result ) {
	if(result == "success")
		{
			 getCadreNotesInformationDetails(globalStartIndex);
			alert("Updated Successfully");
			var t = "li"+cadreNotes;
			$("#"+t).remove();
		}else
		{
			alert("Error Occured.Please try again later...");
		}
     });
	}
}
/* function updateCadreNotesAllInfrmation(){ 
var notesId = $("#hiddencadreNotesId").val(); 
var notes = $(".jqte_editor").html();
var jObj={
		notesId:notesId,
		notes:notes
	};
	$.ajax({
	  type:'POST',
	  url: 'updateCadreNotesInfrmationAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jObj)},
	  }).done(function(result){
			if(result != null){
				if(result.message == "Success"){
					$("#errorUpCallId").html("Notes Data Updation Successfully Executed..");
					$(".jqte_editor").html('');
					$("#errorUpCallId").html("");
				}else{
					$("#errorUpCallId").html("Error Occured Try agian..");
				} 
			}
	  });
} */
var globalPrimaryId = 0;
$(document).on("click",".editIconCls",function(){
	var newNotes = $(this).attr("attr_div"); 
	var id = $(this).attr("attr_id");
	var name = $("#"+newNotes).html();
	$(".jqte_editor").jqteVal(name);
	globalPrimaryId = id;
	$("#updateButnId").show();
	$("#updateNotesButtonId").hide();
	$('#notesModalDivId').animate({
		scrollTop:  $(".jqte_editor").offset().top 
	});
});

function getNominatedPostReportFiles() {
	
	nominatedPostResult = [];
	var jsObj={
		tdpCadreId:globalCadreId
	}
	$.ajax({
		type:'GET',
		url:'getNominatedPostReportFilesAction.action',  
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		nominatedPostResult = result;
		buildNominatedReports(); 
	})
}
//party meetings location wise start.
	function partyMeetingsDatePickerInstantiation(){
		$(".datePartyMeetings").daterangepicker({
			startDate: moment().startOf('year'),
			endDate: moment(),
			maxDate:moment(),
			parentEl:'.partyMeetingsCollapseBody',
			opens:'left',
			format: 'DD/MM/YYYY',
			applyClass: 'partyMeetingsApplyBtnCls',
			ranges: {
			   'Last 1 Year': [moment().subtract(1, 'year').startOf('month'), moment()],
			   'Last 6 Months': [moment().subtract(6, 'months').startOf('month'), moment()],
			   'Last 3 Months': [moment().subtract(3, 'months').startOf('month'), moment()],
			   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			   'This Month': [moment().startOf('month'), moment()],
			   'This Year': [moment().startOf('year'), moment()],
			},
		});
		$(".datePartyMeetings").val(moment().startOf('year').format("DD/MM/YYYY") + ' - '+ moment().format("DD/MM/YYYY"));
		$(".partyMeetingsCollapseBody").find(".ranges").addClass("partyMeetingsDateCls");
	}
	//Modal Party Meetings 
	$(document).on("click",".partyMeetingsModalId",function(){
		$("#partyMeetingsModalId").modal("open");
	});
	$('.partyMeetingsLocCls').click(function(){
       getLocationWisePartyMeetings();
    });
	
	$(document).on("click",".partyMeetingsDateCls li",function(){
		if($(this).html() != "Custom Range"){
			getLocationWisePartyMeetings();
		}
    });
    $(document).on("click",".partyMeetingsApplyBtnCls",function(){
       getLocationWisePartyMeetings();
    });
	
	function getLocationWisePartyMeetings(){
		$('#noteId').html("");  
	   $('#partyMeetingsLocWiseDiv').html('<center><img src="images/icons/loading.gif" style="width: 50px; height: 50px; margin-top: 10px;"></center>');
	   var dates = $("#partyMeetingDateId").val();
	   var startDateString = "";
	   var endDateString = "";
		if($.trim(dates).length > 0){
		   var dates = dates.split("-");
		   startDateString = dates[0].trim();  
		   endDateString = dates[1].trim();
		}

		var locationDetailsObj = getLocationTypeAndValue();
		if(locationDetailsObj["locationValue"] == 0){
			$('#partyMeetingsLocWiseDiv').html("<center><b>NO DATA AVAILABLE..</b></center>");
			return;
		}
		 
		var jsObj={
				locationType:locationDetailsObj["locationType"],
				locationValue:locationDetailsObj["locationValue"],
				startDateString:startDateString,
				endDateString:endDateString
			}
			
			$.ajax({
					type:'GET',
					 url: 'getLocationWisePartyMeetingsAction.action',
					 data : {task:JSON.stringify(jsObj)} ,
					}).done(function(result){
						//$("#partyMeetingsModalId").modal('show');
						if(result !=null && result.length > 0){
							buildPartyMeetingsLocationWiseCountsTable(result);
						}else{
							$('#partyMeetingsLocWiseDiv').html("<center><b>NO DATA AVAILABLE..</b></center>")
						}
				});
	  }
	  
	function buildPartyMeetingsLocationWiseCountsTable(result){
    
	 var str='';
	 str+='<div class="m_0-responsive table-responsive m_top10">';
		str+='<table class="table table-bordered tablePartyMeetings">';
		
		str+='<thead class="text-capital">';
		
		str+='<tr>';
			str+='<th rowspan="3">year</th>';
			str+='<th rowspan="3">Month</th>';
			if(result[0]!=null && result[0].subList!=null && result[0].subList.length > 0){
			  for(var i in result[0].subList){
				  if(result[0].subList[i].name == "VILLAGE"){
					  str+='<th colspan="5" style="background-color:#F0E68C;"> VILLAGE / WARD</th>';
				  }else if(result[0].subList[i].name == "MANDAL"){
					   str+='<th colspan="5" style="background-color:#FAFAD2;"> MANDAL / MUNCIPALITY</th>';  
				  }else{
					  if(result[0].subList[i].name=="DISTRICT"){
						  str+='<th colspan="5" style="background-color:#FAFAD2;">'+result[0].subList[i].name+'</th>';
					  }else{
						  str+='<th colspan="5" style="background-color:#F0E68C;">'+result[0].subList[i].name+'</th>';     
					  }   
				  }
			  }
			}	
		str+='</tr>';
		
		str+='<tr>';
			if(result[0].subList!=null && result[0].subList.length>0){  
				var locationListJsonArr =  {"0":"DISTRICT","1":"CONSTITUENCY","2":"MANDAL","3":"VILLAGE"}; 
				var locationsCount = result[0].subList.length;
				//debugger;
				for(var i=0;i<locationsCount;i++){
				   str+='<th rowspan="2">Total</th>';
				   if(result[0].subList[0].subList != null && result[0].subList[0].subList.length>0){
					   for(var j in result[0].subList[0].subList){
						  if((locationListJsonArr[i] == "DISTRICT") && (result[0].subList[0].subList[j].name == "Ivr")) {
							  //do nothing
						  }else{
							  if((locationListJsonArr[i] == "CONSTITUENCY" || locationListJsonArr[i] == "MANDAL" || locationListJsonArr[i] == "VILLAGE") &&  (result[0].subList[0].subList[j].name == "Attendance")){
							  	//do nothing
							  }else{
								  str+='<th colspan="2" style="width:10%;">'+result[0].subList[0].subList[j].name+'</th>';         
							  }
						  }  
					   }
				   }  
				}
			}
		str+='</tr>';
		
		str+='<tr>';
			var count = result[0].subList.length * 2;     
			for( var i =0; i<count;i++){  
				str+='<th>&nbsp&nbspC</th>';        
				str+='<th>NC</th>';
			}
		str+='</tr>';
		
		str+='</thead>';
		
		str+='<tbody>';
		
		if(result!=null && result.length>0){
			for(var i in result){
				str+='<tr>';
				str+='<td>'+result[i].year+'</td>';
				str+='<td>'+result[i].month+'</td>';
				if(result[i].subList != null && result[i].subList.length>0){
				  for(var j in result[i].subList){
					   //planned count
					   if(result[i].subList[j].plannedCount != null && result[i].subList[j].plannedCount > 0){
						   var location = result[i].subList[j].name;
							str+='<td data-toggle="modal" data-target="#partyMeetingsModalId" attr_level_name="'+result[i].subList[j].name+'" attr_year_id="'+result[i].year+'" attr_month_id="'+result[i].month+'" class="summaryCls" style="cursor:pointer;color: #337ab7;">'+result[i].subList[j].plannedCount+'</td>';   
					   }else{
							str+='<td> - </td>';  
						}
						
					   //conducted not conducted counts.
					   if(result[i].subList[j].subList != null && result[i].subList[j].subList.length>0){
						   for(var k in result[i].subList[j].subList){
							   if(result[i].subList[j].subList[k].name == "Ivr" && location == "DISTRICT"){
								   
							   }else{
								   if((location == "CONSTITUENCY" || location == "MANDAL" || location == "VILLAGE") && (result[i].subList[j].subList[k].name == "Attendance")){
									   
								   }else{
									   //conducted count.   
									if(result[i].subList[j].subList[k].conductedCount != null && result[i].subList[j].subList[k].conductedCount > 0){
										str+='<td>'+result[i].subList[j].subList[k].conductedCount+'</td>';
									}else{
										str+='<td> - </td>';
									}
								   }
								    
							   }
							  //not conducted count.
							  if(result[i].subList[j].subList[k].name == 'Attendance' && location == "DISTRICT"){      
								  var plannedCount = result[i].subList[j].plannedCount;
								  var conductedCount = result[i].subList[j].subList[k].conductedCount; 
								  var notConductedCount = plannedCount - conductedCount;
								  if(notConductedCount > 0){
									  str+='<td>'+notConductedCount+'</td>';
								  }else{
									  str+='<td> - </td>';
								  }
							  }else{
								  if(result[i].subList[j].subList[k].name == "Ivr" && location == "DISTRICT"){
									  
								  }else{
										if((location == "CONSTITUENCY" || location == "MANDAL" || location == "VILLAGE") && (result[i].subList[j].subList[k].name == "Attendance")){
									   
										}else{
											if(result[i].subList[j].subList[k].notConductedCount != null && result[i].subList[j].subList[k].notConductedCount>0){
												str+='<td>'+result[i].subList[j].subList[k].notConductedCount+'</td>';
											}else{
												str+='<td> - </td>';
											}   
									   
										}
										
								  }
								  
							  }  
							  
						   }
					   }
				   }
				}
				str+='</tr>';
			}
		}
		str+='</tbody>';
		str+='</table>';
	str+='</div>';
     $('#partyMeetingsLocWiseDiv').html(str);
     $('#noteId').html("<strong >Note:&nbsp&nbsp</strong>C:&nbsp<span style='color:#337ab7;'>Conducted</span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbspNC:&nbsp<span style='color:#337ab7;'>Not Conducted</span>"); 
     $('.tablePartyMeetings').dataTable({
		"iDisplayLength": 15,
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
	 });
     $('.tablePartyMeetings').removeClass("dataTable");
  }
  
  function getLocationTypeAndValue(){
	  var locationDetailsObj = {"locationType":"","locationValue":0,"headingName":""}
	  
	  var headingName = "";
	  var locationType ="";
	  var locationValue=0;
	  
	  var radioType = $("input[name='partyMetingsLocation']:checked").val();
	  	if(radioType == "district"){
		   locationType = radioType;
		   locationValue = tdpCadreDistrictId;
		   headingName = tdpCadreDistrictName + " District";
		}else if(radioType == "parliamentConstituency"){
		   locationType = radioType;
		   locationValue = tdpCadreParliamentConstituencyId;
		   headingName = tdpCadreParliamentName + " Parliament ";
		}else if(radioType == "assemblyConstituency"){
		  locationType = radioType;
		  locationValue = tdpCadreAssemblyConstituencyId;
		  headingName = tdpCadreAssemblyConstituencyName + " Parliament ";
		}else if(radioType == "mandal"){
		   if(tdpCadreLocalElectionBodyId !=null && tdpCadreLocalElectionBodyId > 0){
			 locationType = "muncipality";
			 locationValue = tdpCadreLocalElectionBodyId;
			 if(tdpCadreElectionTypeId != 7){
				 headingName = tdpCadreLocalElectionBodyName;
			 }else{
				headingName =  tdpCadreLocalElectionBodyName +" "+tdpCadreElectionType;
			 }
		   }else if(tdpCadreTehsilId != null && tdpCadreTehsilId > 0){
			 locationType = "tehsil";
			 locationValue = tdpCadreTehsilId;
			 headingName =  tdpCadreVillageName +" Mandal ";
		   } 
		}else if(radioType == "village"){
		   if( tdpCadreWardId !=null && tdpCadreWardId > 0){
			 locationType = "ward";
			 locationValue = tdpCadreWardId;
			 headingName =   tdpCadreWardName ;
		   }else if(tdpCadreVillageId != null && tdpCadreVillageId > 0){
			 locationType = "village";
			 locationValue = tdpCadreVillageId;
			 headingName =   tdpCadreVillageName +" Village";
		   } 
		}
		
		locationDetailsObj["locationType"] = locationType;
		locationDetailsObj["locationValue"] = locationValue;
		locationDetailsObj["headingName"] = headingName;
		return locationDetailsObj;
  }
  
	$(document).on('click','.summaryCls',function(){
		var location = $(this).attr("attr_level_name");
		var locationDetailsObj = getLocationTypeAndValue();
	    var headingName = locationDetailsObj["headingName"];
		
		var year = $(this).attr("attr_year_id");
		var month = $(this).attr("attr_month_id");
		var monthJsonObj = {"Jan":1,"Feb":2,"Mar":3,"Apr":4,"May":5,"Jun":6,"Jul":7,"Aug":8,"Sep":9,"Oct":10,"Nov":11,"Dec":12};
		var monthId = monthJsonObj[month];
		
		var levelName = $(this).attr("attr_level_name");
		var partyMeetingLevelIds=[];
		if(levelName=="DISTRICT"){
			partyMeetingLevelIds.push(2);
			headingName = headingName + " -  DISTRICT LEVEL PARTY MEETINGS DETAILS";
		}else if(levelName=="CONSTITUENCY"){
			partyMeetingLevelIds.push(3);
			headingName = headingName + " - CONSTITUENCY LEVEL PARTY MEETINGS DETAILS";
		}else if(levelName=="MANDAL"){
			partyMeetingLevelIds.push(4);
			partyMeetingLevelIds.push(5);
			partyMeetingLevelIds.push(6);
			headingName = headingName + " - MANDAL / MUNCIPALITY LEVEL PARTY MEETINGS DETAILS";
		}else if(levelName=="VILLAGE"){
			partyMeetingLevelIds.push(7);
			partyMeetingLevelIds.push(8);
			headingName = headingName + " - VILLAGE / WARD LEVEL PARTY MEETINGS DETAILS";
		}
		
		//popup
		$("#partyMeetingSummaryHeadingId").html(headingName.toUpperCase());
		$('#summaryDivId').html('<center><img src="images/icons/loading.gif" style="width: 50px; height: 50px; margin-top: 10px;"></center>'); 
		$("#detailsId").html("");
			
		var jsObj = {
			locationType : locationDetailsObj["locationType"], 
			locationValue : locationDetailsObj["locationValue"],      
			partyMeetingLevelIds : partyMeetingLevelIds,
			month : monthId,
			year : year
		}
		$.ajax({
		  type:'POST',
		  url: 'getMeetingDetailsForALevelByLocationAction.action',      
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
		if(result != null && result.length > 0){ 
			buildSummary(result,location);
		}
		});
	});
	
	function buildSummary(result,location){
		var conducted = 0;
		var notConducted = 0;
		var mayBe = 0;
		var totalCount = 0;
		var str = '';
		str+='<table class="table table-bordered" id="partyMeetingDetailsTableId">';
			str+='<thead style="background-color:#ECECEC">';
				str+='<th>Location</th>';
				str+='<th>Party Meeting Name</th>';
				str+='<th>Conducted Date </th>';
				str+='<th>Party Office Status</th>';
				if(location != "DISTRICT"){
					str+='<th>IVR Status</th>';
				}
				
				str+='<th>Meeting Status</th>';
			str+='</thead>';
			for(var i in result){
				totalCount+=1;
				if(result[i].meetingStatus == 'Maybe'){
					mayBe+=1;
				}if(result[i].meetingStatus == 'N'){
					notConducted+=1;
				}if(result[i].meetingStatus == 'Y'){
					conducted+=1;
				}
				str+='<tr>';
					str+='<td>'+result[i].location+'</td>';
					str+='<td>'+result[i].name+'</td>';
					 if(result[i].dateString != null && $.trim(result[i].dateString).length > 0){
						str+='<td>'+result[i].dateString.split(" ")[0]+'</td>';
					}else{
						str+='<td> - </td>';
					}
                    str+='<td>'+getRequiredStatus(result[i].dpoStatus)+'</td>';
					if(location != "DISTRICT"){
						str+='<td>'+getRequiredStatus(result[i].ivrStatus)+'</td>';
					}  
 				    	
					str+='<td>'+getRequiredStatus(result[i].meetingStatus)+'</td>';	
					
				str+='</tr>';
			}
		str+='</table>';
		buildFinalSummary(str,conducted,notConducted,mayBe,totalCount,location); 
	}
	
	function getRequiredStatus(statusType){
		var requiredType = "";
		if(statusType == "Y"){
			requiredType = "Conducted"
		}else if(statusType == "N"){
			requiredType = "Not Conducted"
		}else if(statusType == "Maybe"){
			requiredType = "May Be"
		}else if(statusType == null){
			requiredType = "-"
		}
		return requiredType;
	}
	function buildFinalSummary(str,conducted,notConducted,mayBe,totalCount,location){
		var str1 = '';
		str1+='<table class="table table-bordered">';
				str1+='<thead style="background-color:#ECECEC">';
					str1+='<th>Total Meetings</th>';
					str1+='<th>Conducted Count</th>';
					str1+='<th>Not Conducted Count</th>';
					if(location != "DISTRICT"){
						str1+='<th>May Be Count</th>';
					}
					
				str1+='</thead>';
				str1+='<tr>';
					str1+='<td>'+totalCount+'</td>';
					str1+='<td>'+conducted+'</td>';
					str1+='<td>'+notConducted+'</td>';
					if(location != "DISTRICT"){
						str1+='<td>'+mayBe+'</td>';  
					}
					
				str1+='</tr>';  
			str1+='</table>';
			$("#summaryDivId").html(str1);
			$("#detailsId").html(str);
			
			$('#partyMeetingDetailsTableId').dataTable({
			    "iDisplayLength": 25,
			    "aLengthMenu": [[25, 50, 75, -1], [25, 50, 75, "All"]]
		     });
		    $('#partyMeetingDetailsTableId').removeClass("dataTable");
			
			$("#partyMeetingsModalId").modal("show");  
	}
function buildCandidateAppliedPostByCadreDetails(result){
	
	 var str = '';
	 if(result.subList.length > 0 || result.subList1.length > 0){
		$("#nominatedPstStatusId").show();
	 if(result.subList != null && result.subList.length > 0){
		 str+='<div class="col-md-4 col-xs-10 col-sm-8 col-lg-12">';
                     //str+='<div class="bg_ff pad_10" style="border: 1px solid rgb(204, 204, 204);" id="appliedPostId">';
                        	/*str+='<h4 class="panel-title font_weight">APPLIED POSTS FOR THE SELECTED PROFILE <i class="glyphicon glyphicon-list-alt pull-right" style="cursor:pointer;" title="View documents for all application" onclick="getApplicationDocuments('+cadreId+','+candiId+',0);"></i></h4>'; */
                           str+='<div class="row">';
                           // str+='<div class="col-md-4 col-xs-10 col-sm-8 col-lg-12">';
                                	str+='<div class="panel panel-default panelPost">';
                                      /*  str+='<div class="panel-heading">';
                                        str+='<h4 class="panel-title">Nominated Post</h4>';
                                       str+='</div>'; */
                                       str+='<div class="panel-body">';
                                        	str+='<ul class="ulPost">';
											for(var i in result.subList){
                                            	str+='<li>';
												if(result.subList[i].applStatusId == 1)
                                                	str+='<p class="labelStatus " style="background:orange;width:90px;" > Pending </p>';
												else if(result.subList[i].applStatusId == 2 || result.subList[i].applStatusId == 4)
                                                	str+='<p class="labelStatus " style="background:red;width:90px;"> '+result.subList[i].status+' </p>';
												else if(result.subList[i].applStatusId == 3)
                                                	str+='<p class="labelStatus " style="background:lightblue;width:90px;" > '+result.subList[i].status+' </p>';
												else
                                                	str+='<p class="labelStatus " style="background:green;width:90px;">'+result.subList[i].status+' </p>';
													
													/* str+='<i class="glyphicon glyphicon-list-alt pull-right" style="cursor:pointer;" title="View documents for this application" onclick="getApplicationDocuments('+cadreId+','+candiId+','+result.subList[i].nominatePostApplicationId+');"></i>'; */
													if(result.subList[i].levelName != null){
														str+=''+result.subList[i].level+'-'+result.subList[i].levelName+'→  Dept-'+ result.subList[i].cadreName+"→  Board- "+result.subList[i].subCaste+" →  Position- "+result.subList[i].voterName+" : "+result.subList[i].status+"";
												     if(result.subList[i].path != null && result.subList[i].status == "GO Passed"){	
								                        str+=':<i id="" class="glyphicon glyphicon-list-alt remove-icon showPdfCls5" data-placement="bottom" style="margin-right: 3px;cursor:pointer;color:green;" title="Click Here To Get Profiles Detail" data-toggle="modal" data-target="#pdfModelId1" filePath="'+result.subList[i].path+'"></i></br>';
								                        str+=' Duration : '+" "+result.subList[i].fromDate+" - "+result.subList[i].toDate+"";
								                          }
								                         str+='</li>';
													}
													else{
														str+=''+result.subList[i].level+'→' +result.subList[i].subCaste+" → "+result.subList[i].cadreName+" → "+result.subList[i].voterName+" : "+result.subList[i].status+"";
												     if(result.subList[i].path != null && result.subList[i].status == "GO Passed"){	
								                       str+=':<i id="" class="glyphicon glyphicon-list-alt remove-icon showPdfCls5" data-placement="bottom" style="margin-right: 3px;cursor:pointer;color:green;" title="Click Here To Get Profiles Detail" data-toggle="modal" data-target="#pdfModelId1" filePath="'+result.subList[i].path+'"></i></br>';
								                       str+='Duration : '+" "+result.subList[i].fromDate+" - "+result.subList[i].toDate+"";
								                         }
								                       str+='</li>';
												}			
											}
                                           str+='</ul>';
                                        str+='</div>';
                                   str+='</div>';
                                str+='</div>';
								}
							//nominated Party 
							if(result.subList1 != null && result.subList1.length > 0){
								
								str+='<div class="col-md-6 col-xs-12 col-sm-6 col-lg-6">';
                                	str+='<div class="panel panel-default panelPost">';
                                    	str+='<div class="panel-heading">';
                                        	str+='<h4 class="panel-title">Party Post</h4>';
                                        str+='</div>';
                                        str+='<div class="panel-body">';
                                        	str+='<ul class="ulPost">';
											for(var i in result.subList1){
                                                str+='<li>';
                                                	str+='<span class="labelStatus shortlisted">Shortlisted</span>';
                                                	if(result.subList1[i].levelName != null){
													str+=''+result.subList1[i].level+'-'+result.subList1[i].levelName+ '→Dept- ' + result.subList[i].cadreName+"→Board- "+result.subList1[i].subCaste+" →Position- "+result.subList1[i].voterName+" : "+result.subList1[i].status+"</li>";
													}
													else{
														str+=''+result.subList1[i].level+'→' +result.subList1[i].subCaste+" → "+result.subList1[i].cadreName+" → "+result.subList1[i].voterName+" : "+result.subList1[i].status+"</li>";
													}
											}
                                          str+='</ul>';
                                       str+=' </div>';
                                    str+='</div>';
                               str+=' </div>';
							} /* else{
								str+='<div class="col-md-6 col-xs-12 col-sm-6 col-lg-6">';
                                	str+='<div class="panel panel-default panelPost">';
                                    	str+='<div class="panel-heading">';
                                        	str+='<h4 class="panel-title">Party Post</h4>';
                                        str+='</div>';
                                        str+='<div class="" style="padding: 10px;;text-transform: uppercase;">';
                                        	str+='<h4 style="height:100%;width:100%" class="text-center">No Existing Applied Posts are Available...</h4>';
                                       str+=' </div>';
                                    str+='</div>';
                               str+=' </div>';
							}  */
	 
                           //str+='</div>';
                       //str+='</div>';
					   //str+='<p class="text-muted">Note: Do you want to apply for more posts select below options</p>';
                   str+='</div>';
				    $("#nmtedMdlId").html(str);
				   }
				   else{
			          $("#nominatedPstStatusId").css("display","none");
				   }
}
function nominatedPostBuilingDetails(result){
	var str= '';
	var count = result.subList.length;
	 if(result.subList.length > 0 || result.subList1.length > 0){
	 $("#nominatedDivId").show();
	 $("#appliedCountId").html("Applied posts count:<a><b><span id=countId>"+count+"</span></b></a>");
	 $("#countId").attr("data-original-title","Click here to view applied posts");   
	 $('#countId').tooltip();
	  if(result.subList != null && result.subList.length > 0){
			str+='<div>';
			   str+='<div class="panel-body">';
					str+='<ul class="ulPost">';
					for(var i in result.subList){
						str+='<li class="nominatedPostliCls">';
						 if(result.subList[i].applStatusId == 6 || result.subList[i].applStatusId == 7){
							str+='<p class="labelStatus " style="background:green;width:90px;"> '+result.subList[i].status+' </p>';
							/* str+='<i class="glyphicon glyphicon-list-alt pull-right" style="cursor:pointer;" title="View documents for this application" onclick="getApplicationDocuments('+cadreId+','+candiId+','+result.subList[i].nominatePostApplicationId+');"></i>'; */
							if(result.subList[i].levelName != null){
								str+=''+result.subList[i].level+'-'+result.subList[i].levelName+'<br>↓<br/>  Dept-'+ result.subList[i].cadreName+"<br>↓<br/>  Board- "+result.subList[i].subCaste+" <br>↓<br/>  Position- "+result.subList[i].voterName+" : "+result.subList[i].status+" ";
								if(result.subList[i].path != null && result.subList[i].status == "GO Passed"){	
								str+=':<i id="" class="glyphicon glyphicon-list-alt remove-icon showPdfCls5" data-placement="bottom" style="margin-right: 3px;cursor:pointer;color:green;" title="Click Here To Get Profiles Detail" data-toggle="modal" data-target="#pdfModelId1" filePath="'+result.subList[i].path+'"></i>';
								str+='Duration : '+" "+result.subList[i].fromDate+" - "+result.subList[i].toDate+"";
								}
								str+='</li>';
							}
							else{
								str+=''+result.subList[i].level+'→' +result.subList[i].subCaste+" → "+result.subList[i].cadreName+" → "+result.subList[i].voterName+" : "+result.subList[i].status+" ";
								if(result.subList[i].path != null && result.subList[i].status == "GO Passed"){	
								str+=':<i id="" class="glyphicon glyphicon-list-alt remove-icon showPdfCls5" data-placement="bottom" style="margin-right: 3px;cursor:pointer;color:green;" title="Click Here To Get Profiles Detail" data-toggle="modal" data-target="#pdfModelId1" filePath="'+result.subList[i].path+'"></i>';
								str+='Duration : '+" "+result.subList[i].fromDate+" - "+result.subList[i].toDate+"";
								}
								str+='</li>';
							}
						 }else{
							 $("#.nominatedPostliCls").hide();
						 }
					}
				   str+='</ul>';
				str+='</div>';
			str+='</div>';
		//str+='</div>';
		$("#nominatedPstStatusId").html(str);
		}
	 }
	 else{
		$("#nominatedDivId").css("display","none");
	}
}
function checkIsLeader(isLeader){
	if(isLeader == "leader"){
		$("#cdrRegDtlsId").show();
	}
}
$(document).on("click","#cdrRegDtlsId",function(){  
	$("#leaderCadreId").modal("show");
	$("#firstDesigId").html('');    
	$("#secondDesigId").html('');
	$("#firstDesigId").html('<center><img alt="Processing Image" src="./images/icons/loading.gif" style="width: 35px; height: 35px;"></center>');  
	  
	var cadreId = memberCadreId;
	var jsObj ={ 
			 tdpCadreId : cadreId                                
		};   
		$.ajax({  
			type : 'POST',
			url : 'getLeaderDtlsInfoAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#firstDesigId").html('');          
			if(result != null && result.length > 0){
				buildLeadersCadreDtlsReport(result);
			}else{      
			           
			}                 
		});
});  
function buildLeadersCadreDtlsReport(result){
	for(var i in result){
		buildUniqueDesignationCadreReport(result[i],i,0,0);
		$("#enrolementTitleId"+i).html("CADRE ENROLLEMENTS DETAILED VIEW-<b>"+result[i][0].candidataName+"-["+result[i][0].superlocationName+"]</b>");  
	}  
}
function buildUniqueDesignationCadreReport(result,i,idx1,idx2){  
	if(result != null && result.length > 0){
		var cadre2014 = 0;
		var cadre2016 = 0;
		var newCadre = 0;
		var renewalCadre = 0;
		var str = '';            
		str+='<div class="table-responsive">';
		str+='<table class="table table-bordered tablePopup">';
			str+='<thead style="background-color:#E9E9E9">';
				if(i==100){
					str+='<th>CONSTITUENCY</th>';
				}else{
					if(result[0].accessLvlId == 2 || result[0].accessLvlId == 3){
						str+='<th>DISTRICT</th>'; 
					}else{
						str+='<th>CONSTITUENCY</th>';
					}
				}
				str+='<th>2014-16 ENROLLMENT</th>';
				str+='<th>2016-18 ENROLLMENT</th>';
				str+='<th>2016-18 NEW</th>';
				str+='<th>2016-18 RENEWAL</th>';
				str+='<th>2014-16 vs 2016-18 ENROLLMENT %</th>';
			str+='</thead>';
			str+='<tbody>';
			for(var j in result){
				cadre2014 = cadre2014 + result[j].previousCadreCount;
				cadre2016 = cadre2016 + result[j].cadreCount;
				newCadre = newCadre + result[j].newCadre;
				renewalCadre = renewalCadre + result[j].renewalCadre;
				str+='<tr>';
					if(result[0].accessLvlId == 2 || result[0].accessLvlId == 3){
						str+='<td><span><b>'+result[j].name+'</b><i attr_idx_1="'+i+'" attr_idx_2="'+j+'" attr_loc_id="'+result[j].id+'" attr_rowId="consituencyRows'+i+j+'" style="cursor:pointer;padding:3px 4px;background-color:#ccc;border-radius:3px;font-size:11px" class="glyphicon glyphicon-plus consituencyRowsCls pull-right"></i></span></td>';
					}else{
						str+='<td>'+result[j].name+'</td>';  
					}               
					str+='<td>'+result[j].previousCadreCount+'</td>';
					str+='<td>'+result[j].cadreCount+'</td>';
					str+='<td>'+result[j].newCadre+'</td>';
					str+='<td>'+result[j].renewalCadre+'</td>';
					var percent = result[j].cadreCount * (100/result[j].previousCadreCount);
					str+='<td>'+percent.toFixed(2)+'%</td>';                     
				str+='</tr>';
				if(result[0].accessLvlId == 2 || result[0].accessLvlId == 3){
					str+='<tr class="consituencyRowsHide hide consituencyRows'+i+j+'">';
						str+='<td style="background-color:#F0E6DA;box-shadow:inset 0px 0px 5px 5px rgba(0,0,0,0.4);" colspan="6" class="consituencyRowsStr consituencyRowsStr'+i+j+'"></td>';  
					str+='</tr>'; 
				}
				         
			}
			str+='<tr>';
				str+='<td><b>TOTAL ENROLLMENT</b></td>';      
				str+='<td><b>'+cadre2014+'</b></td>';
				str+='<td><b>'+cadre2016+'</b></td>';
				var newPercent = newCadre * (100/cadre2016);
				str+='<td><b>'+newCadre+'('+newPercent.toFixed(2)+'%)</b></td>'; 
				var renewalPercent = renewalCadre * (100/cadre2016);				
				str+='<td><b>'+renewalCadre+'('+renewalPercent.toFixed(2)+'%)</b></td>';
				var totalPercent = cadre2016 * (100/cadre2014);
				str+='<td><b>'+totalPercent.toFixed(2)+'%</b></td>';      
			str+='</tr>';
			str+='</tbody>';
		str+='</table>';
		str+='</div>';    
		if(i==0){
			$("#firstDesigId").html(str);
		}else if(i==1){                
			$("#secondDesigId").html(str);
		}             
		if(i==100){       
			$(".consituencyRowsStr"+idx1+idx2).html(str);    
		}
	}
}

$(document).on("click",".consituencyRowsCls",function(){
	if($(this).hasClass("glyphicon-plus") == true){
		$(".consituencyRowsCls").addClass("glyphicon-plus").removeClass("glyphicon-minus");     
	}
	if($(this).hasClass("glyphicon-minus") == true){
		$(this).addClass("glyphicon-plus").removeClass("glyphicon-minus");
	}else{
		$(this).addClass("glyphicon-minus").removeClass("glyphicon-plus");
	} 
	
	var status = $(this).parent().parent().parent().next('tr').hasClass("hide");
	if(status == false){ 
		$(".consituencyRowsHide").find(".consituencyRowsStr").html(' ');
		$(".consituencyRowsHide").addClass('hide'); 
		return;        
	}
	var rowClsName = $(this).attr("attr_rowId");
	var distId = $(this).attr("attr_loc_id");
	var idx1 = $(this).attr("attr_idx_1");
	var idx2 = $(this).attr("attr_idx_2");   
	
	$(".consituencyRowsHide").addClass('hide');
	$(".consituencyRowsHide").find(".consituencyRowsStr").html(' ');
	$("tr."+rowClsName).removeClass('hide');
	$(".consituencyRowsStr"+idx1+idx2).html('<center><img alt="Processing Image" src="./images/icons/loading.gif" style="width: 35px; height: 35px;"></center>');
	getConstituencyDtls(distId,idx1,idx2);  
});
function getConstituencyDtls(distId,idx1,idx2){
	var jsObj ={ 
			 districtId : distId  
		};
		$.ajax({
			type : 'POST',
			url : 'getCandidateSubLocationDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$(".consituencyRowsStr"+idx1+idx2).html('');  
			if(result != null && result.length > 0){  
				buildUniqueDesignationCadreReport(result,100,idx1,idx2);    
			}else{      
			           
			}                 
		});
}
function getSurveyQuestionWithMarksDetailsByTDpCadreId(){
	$("#surveyQueDetailsIdImgId").show();
	$("#normalSurveyDiv").html("");
	$("#quizSurveyDiv").html("");
	var jsObj={
			cadreId :"7174953"
		}	
	$.ajax({
		 type: "POST",
		 url: "getSurveyQuestionWithMarksDetailsByTDpCadreIdAction.action",
		 data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#surveyQueDetailsIdImgId").hide();
			if(result != null && result.length>0){
				buildnormalSurveyDetails(result);
				buildQuizSurveyDetails(result);
			}else{
				$("#normalSurveyDiv").html(" No data available...");
				$("#quizSurveyDiv").html(" No data available...");
			}	
		});
}
function buildnormalSurveyDetails(result){
	var str="";
		//$("#trainingFeedBackId").html(result[0].surveyName);
		str+='<label>'+result[0].surveyName+' :</label>';
		str+='<div class="table-responsive scrollit">';
		str+='<table class="table table-bordered" id="normalSurveyTableId">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>S.No</th>';
						str+='<th>Question</th>';
						str+='<th>Option</th>';
						str+='</tr>';
				str+='</thead>';
				str+='<tbody>';	
					for(var j in result){
						for(var i in result[j].subList){
							if(result[j].surveyTypeId == 5){
							str+='<tr>';
							var question = result[j].subList[i].question.split(")");
									str+='<td>'+question[0]+'</td>';
									str+='<td>'+question[1]+'</td>';
									
									if(result[j].subList[i].name != null && result[j].subList[i].name !="" && result[j].subList[i].name != "null"){
									str+='<td>'+result[j].subList[i].name+'</td>';
									}else{
										str+='<td>-</td>';
									}
							str+='</tr>';				
						  }
					}
				}			
					
			str+='</tbody>';
		str+='</table>';
		str+='</div>';
		$("#normalSurveyDiv").html(str);
		/*  $('#normalSurveyTableId').dataTable({
			"iDisplayLength": 10,
			"aLengthMenu": [[10, 50, 100, -1], [10, 50, 100, "All"]]
		});
		$('#normalSurveyTableId').removeClass("dataTable"); */
}
function buildQuizSurveyDetails(result){
	var str1='';
		//$("#trainingQuizId").html(result[1].surveyName);
		str1+='<label>'+result[1].surveyName+' :</label>';
			str1+='<div class="table-responsive scrollit">';
			str1+='<table class="table table-bordered" id="quizSurveyTableId">';
				str1+='<thead>';
					str1+='<tr>';
						str1+='<th>S.No</th>';
						str1+='<th>Question</th>';
						str1+='<th>Given Option</th>';
						str1+='<th>Answer</th>';
						str1+='<th>Marks</th>';
					str1+='</tr>';
				str1+='</thead>';
				str1+='<tbody>';
					for(var j in result){
						for(var i in result[j].subList){
								if(result[j].surveyTypeId ==7){
								str1+='<tr>';
										var question = result[j].subList[i].question.split(")");
										str1+='<td>'+question[0]+'</td>';
										str1+='<td>'+question[1]+'</td>';
										if(result[j].subList[i].candidateName != null && result[j].subList[i].candidateName !="" &&
											result[j].subList[i].candidateName != "null"){
												str1+='<td>'+result[j].subList[i].candidateName+'</td>';
											}else{
												str1+='<td>-</td>';
											}
											if(result[j].subList[i].name != null && result[j].subList[i].name !="" &&
											result[j].subList[i].name != "null"){
												str1+='<td>'+result[j].subList[i].name+'</td>';
											}else{
												str1+='<td>-</td>';
											}
											if(result[j].subList[i].marks != null && result[j].subList[i].marks !="" &&
											result[j].subList[i].marks !="null"){
												str1+='<td>'+result[j].subList[i].marks+'</td>';
											}else{
												str1+='<td>-</td>';
											}
											
								str1+='</tr>';	
							}
						}
					}
				
			str1+='</tbody>';
		str1+='</table>';
		str1+='</div>';
		$("#quizSurveyDiv").html(str1);
		/* $('#quizSurveyTableId').dataTable({
			"iDisplayLength": 10,
			"aLengthMenu": [[10, 50, 100, -1], [10, 50, 100, "All"]]
		});
		 $('#quizSurveyTableId').removeClass("dataTable");  */
			
}