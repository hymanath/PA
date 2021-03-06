<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Core DashBoard</title>
<link href="dist/coreDashboard/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/coreDashboard/css/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">


</head>
<body>
<header>
	<nav class="navbar navbar-default navbarHeader">
      <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">
          	<span class="logo"></span>
            <span class="logoText">TDP PARTY</span>
          </a>
        </div>
    
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
          	<li><a href="#"><i class="glyphicon glyphicon-gift"></i></a></li>
            <li><a href="#"><i class="glyphicon glyphicon-bell"></i></a></li>
            <li class="dropdown profileDropDown">
              <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><img src="dist/coreDashboard/img/logo.png" class="profileImage"/> <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">My Profile</a></li>
                <li><a href="#">Change Password</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Sign Out</a></li>
              </ul>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
    <div class="navbar navbar-default navbarProfile">
    	<div class="container">
        	<div class="row">
            	<div class="col-md-7 col-xs-12 col-sm-7">
                	<ul class="nav navbar-nav navbar-left headerProfileName">
                        <li class="dropdown profileDropDown toggleViewIcon">
                          <a style="cursor:pointer;">ASHOK DAKAVARAM 
                          	<span class="caretBackground">
                            	<span class="caret" style="margin-top: 9px;"></span>
                            </span>
                          </a>
						 <div class="dropdown-menu settingsDropDownOptionsView">
								<div id= "userLevelDetailsDiv"></div>
							</div>
                        </li>
                    </ul>
                </div>
                <div class="col-md-3 col-xs-12 col-sm-3">
                	<ul class="list-inline profileSelection">
                    	<li class="active">
                        	<a href="#">general</a>
                        </li>
                        <li>
                        	<a href="#">profile</a>
                        </li>
                    </ul>
                </div>
                <div class="col-md-2 col-xs-12 col-sm-2">
                	<button class="btn btn-success btnYellow"><img src="dist/coreDashboard/img/Icon.png" alt="Icon" class="img-responsive"/>query builder</button>
                   
                    <div class="settingsDropDown">
                      <a href="#"> <i class="glyphicon glyphicon-cog settingsIcon"></i></a>                      
                      <ul class="settingsDropDownOptions">
                      	<li><a href="#">Dashboard settings</a></li>
                        <li><a href="#">committee settings</a>
                        	<div class="settingsDropDownOptionsSub">
                            	<h4 class="panel-title text-capital text-center">committees block settings</h4>
                                <div class="m_top20">
                                    <ul class="radioStyling">
                                      <li>
                                        <input type="radio" id="f-option" name="selector">
                                        <label for="f-option">Cumulative</label>
                                        
                                        <div class="check"></div>
                                      </li>
                                      <li>
                                        <input type="radio" id="s-option" name="selector">
                                        <label for="s-option">Comparative</label>
                                        
                                        <div class="check"><div class="inside"></div></div>
                                      </li>
                                    </ul>
                                </div>
                                <div>
                                    <label class="m_top20">Select Period</label>
                                    <select class="select selectBoxStyle">
                                        <option>Till Date</option>
                                    </select>
                                </div>
                                <div class="m_top20">
                                	<label class="text-capital">select type of committees</label>
                                    <ul class="committeesSelect">
                                    	<li>
                                        	<div class="squaredOne">
                                              <input type="checkbox" value="None" id="squaredOne" name="check" />
                                              <label for="squaredOne"></label>
                                            </div>
                                            <span>Main Committee</span>
                                        </li>
                                        <li>
                                        	<div class="squaredOne">
                                              <input type="checkbox" value="None" id="squaredOne1" name="check" />
                                              <label for="squaredOne1"></label>
                                            </div>
                                            <span>Telugu Yuvatha</span>
                                        </li>
                                        <li>
                                        	<div class="squaredOne">
                                              <input type="checkbox" value="None" id="squaredOne2" name="check"/>
                                              <label for="squaredOne2"></label>
                                            </div>
                                            <span>Telugu Mahila</span>
                                        </li>
                                    </ul>
                                </div>
                                <div class="m_top20 text-center">
                                	<button class="btn btn-success btnYellow btn-lg">SUBMIT</button>
                                </div>
                            </div>
                        </li>
                        <li><a href="#">meetings</a></li>
                        <li><a href="#">party news</a></li>
                        <li><a href="#">govt news</a></li>
                      </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="navbar navbar-default navbarProfile">
    	<div class="container">
        	<div class="row">
            	<div class="col-md-4 col-xs-12 col-sm-4 col-xs-offset-2">
				<p class="m_top10">
                	<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="cumulativeId" value="cumulative" checked> COMULATIVE
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="comparitiveId" value="comparitive"> COMPARITIVE
					</label>
					</p>
                </div>
				
                <div class="col-md-2 col-xs-12 col-sm-2 pull-right"  style="margin-top: -10px;">
                	<ul class="list-inline profileSelection">
                    	<li class="active" >
                        	<a  style="cursor:pointer;text-decoration:none;" class="stateCls">AP</a>
                        </li>
                        <li>
                        	<a  style="cursor:pointer;text-decoration:none;" class="stateCls">TS</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="container m_top20">
	<div class="row">
    	<div class="col-md-4 col-xs-12 col-sm-4 committeesBlock">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons committeesIcon">committees</span>
                    	<i class="glyphicon glyphicon-option-vertical pull-right"></i>
                    </h4>
                </div>
				 <div class="panel-body">
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="committeesForComulative" class="chart"></div>
							<div class="row">
							<div id="CamparitiveBasicBlock" style ="display:none;"></div>
							</div>
						</div>
						
						<div id="levelWiseComulativeForCommittees" style ="display:none;"></div>
						<div id="levelWiseComparativeForCommittees" style ="display:none;"></div>
						
                        <div class="col-md-12 col-xs-12 col-sm-12">
                        	<i class="glyphicon glyphicon-option-horizontal pull-right committeesExpandIcon " style ="cursor:pointer"></i>
                        </div>
                    </div>
                </div>
			</div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons meetingsIcon">meetings</span>
                    	<i class="glyphicon glyphicon-option-vertical pull-right"></i>
                    </h4>
                </div>
                <div class="panel-body">
                	<div id="meetings" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right expandIcon"></i>
                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons newsIcon">news</span>
                    	<i class="glyphicon glyphicon-option-vertical pull-right"></i>
                    </h4>
                </div>
                <div class="panel-body">
                	<div id="news" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right expandIcon"></i>                    
                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons eventsIcon">events</span>
                    	<i class="glyphicon glyphicon-option-vertical pull-right"></i>
                    </h4>
                </div>
                <div class="panel-body">
                	<div id="events" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right expandIcon"></i>                    
                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons trainingIcon">trainings</span>
                    	<i class="glyphicon glyphicon-option-vertical pull-right"></i>
                    </h4>
                </div>
                <div class="panel-body">
                	<div id="trainings" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right expandIcon"></i>                    
                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-4">
        	<div class="panel panel-default panelCustomBlock">
            	<div class="panel-heading">
                	<h4 class="panel-title"><span class="coreIcons debateIcon">debates</span>
                    	<i class="glyphicon glyphicon-option-vertical pull-right"></i>
                    </h4>
                </div>
                <div class="panel-body">
                	<div id="debates" class="chart"></div>
                    <i class="glyphicon glyphicon-option-horizontal pull-right expandIcon"></i>                    
                </div>
            </div>
        </div>
    </div>
</div>
<script src="dist/coreDashboard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/coreDashboard/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/coreDashboard/js/coreDashboard.js" type="text/javascript"></script>
<script src="dist/coreDashboard/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script type="text/javascript">

 //GLOBAL VARIABLES.
	var globalUserId = '${sessionScope.USER.registrationID}';
	var globalUserTypeId;
	var globalUserAccessLevelId;
	var globalUserAccessLevelValues = [];
	var globalState = 'AP';
	
	getUserBasicDetails();
	function onLoadCalls(){
		getCommitteesCumulativeBasicReportChart(globalUserAccessLevelId,globalUserAccessLevelValues);
		getLoggedInUserStructure();
		getDistrictWiseCommitteesCountReport();
		getCommitteesBasicCountReport();
		getLevelWiseBasicCommitteesCountReport();
	}
	
	$(document).ready(function(){
		
		//Main header remove
		$(".eventsheader").hide();
		blockHeights();
		buildBasicgraphs();
		
		$("#cumulativeId").prop("checked",true);
	});
	
	 
	$(document).on("click",".committeesExpandIcon",function(){
		var i =0;
		i=i+1;
		$(".committeesExpandIcon").addClass("expandIcon");
		$(".committeesBlock").removeClass("col-md-4").addClass("col-md-12");
		if($("#cumulativeId").is(':checked')){
			$("#committeesForComulative").hide();
			$("#levelWiseComulativeForCommittees").show();
			getCommitteesCumulativeOverallReportCharts(globalUserAccessLevelId,globalUserAccessLevelValues);
		}
		if($("#comparitiveId").is(':checked')){
			$("#CamparitiveBasicBlock").hide();
			$("#levelWiseComparativeForCommittees").show();
			getCommitteesComparativeOverallReportChart(globalUserAccessLevelId,globalUserAccessLevelValues);
		
		}
		
		var getWidth = $('.committeesBlock').width();
		$('#levelWiseComulativeForCommitteeslarge'+i+'').css("width",getWidth+'px');
		$('#levelWiseComparativeForCommitteesDetails'+i).css("width",getWidth+'px');
		blockHeights();
	});
	$(document).on("click",".expandIcon",function(){
		$(".committeesBlock").addClass("col-md-4").removeClass("col-md-12");
		$(".committeesExpandIcon").removeClass("expandIcon");
		if($("#cumulativeId").is(':checked')){
			$("#committeesForComulative").show();
			$("#levelWiseComulativeForCommittees").hide();
			$("#levelWiseComparativeForCommittees").hide();
		}
		if($("#comparitiveId").is(':checked')){
			$("#CamparitiveBasicBlock").show();
			$("#levelWiseComparativeForCommittees").hide();
		}
		blockHeights();
	});
   $(document).on("click","#comparitiveId",function(){
	   $(".committeesBlock").addClass("col-md-4").removeClass("col-md-12");
		$("#committeesForComulative").hide();
		$("#CamparitiveBasicBlock").show();
		$("#levelWiseComulativeForCommittees").hide();
		$("#levelWiseComparativeForCommittees").hide();
		getCommitteesComparativeBascicReportChart(globalUserAccessLevelId,globalUserAccessLevelValues);
		
	}); 
    $(document).on("click","#cumulativeId",function(){
		$(".committeesBlock").addClass("col-md-4").removeClass("col-md-12");
		$("#committeesForComulative").show();
		$("#CamparitiveBasicBlock").hide();
		$("#levelWiseComparativeForCommittees").hide();
		getCommitteesCumulativeBasicReportChart(globalUserAccessLevelId,globalUserAccessLevelValues);
		
	}); 
	
	
    $(".stateCls").click(function(event){
      $(".stateCls").parent().removeClass("active");
	  $(this).parent().addClass("active");
	  globalState = $(this).html();
	  event.preventDefault();
    });
	$(document).on("click",".settingsDropDown",function(){
		$(this).toggleClass("dropdownOpen")
	});
	$(document).on("click",".toggleViewIcon",function(){
		$(this).toggleClass("dropDownView");
		//$(".settingsDropDownOptionsView").show();
	});
	$(document).on("click",".settingsDropDownOptions li,.toggleViewIcon .settingsDropDownOptionsView",function(e){
		e.stopPropagation();
	});
	
	function MainPart(result){
		
	var str='';
	if(result.subList != null && result.subList.length > 0){
			str+='<div class="panel-heading" role="tab" id="headingOne"  style="height:30px;">';
				str+='<a role="button" style="width:30px" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">';
					str+='<span class="profileImageView">';
					str+='<img src="dist/coreDashboard/img/logo.png" />';
					str+='</span>';
				str+=' </a>';
				str+='<ul class="radioStyling" style="padding-left:0px;">';
					str+='<li>';
						str+='<input type="radio" id="mian" name="selector" >';
						var locationName = '';
						if(result.locationName != null && $.trim(result.locationName).length > 0){
							locationName = result.locationName;
						}else{
							locationName = result.userType;
						}
						str+='<label for="mian" class="text-capital userStructureClass" attr_userTypeId='+result.userTypeId+' attr_activityMemberId ='+result.activityMemberId+' attr_userAccessLevelId='+result.locationLevelId+' attr_userAccessLevelValuesString='+result.locationValuesSet+' ><span class="hideDropDownView">'+locationName+' - <i>'+result.name+'</i></span>';
						
						str+='<span class="profileImageView">';
						str+='<img src="https://mytdp.com/images/cadre_images/'+result.image+'" />';
						str+='</span>';
						str+='</label>';
						str+='<div class="check"></div>';
					str+='</li>';
				str+='</ul>';
			str+='</div>';
		}else{
			str+='<div class="panel-body">';
				str+=' <ul class="radioStyling">';
					str+='<li>';
						str+='<input type="radio" id="mian" name="selector">';
						var locationName = '';
						if(result.locationName != null && $.trim(result.locationName).length > 0){
							locationName = result.locationName;
						}else{
							locationName = result.userType;
						}
						str+=' <label for="mian" class="userStructureClass" attr_userTypeId='+result.userTypeId+' attr_activityMemberId ='+result.activityMemberId+' attr_userAccessLevelId='+result.locationLevelId+' attr_userAccessLevelValuesString='+result.locationValuesSet+'><span class="hideDropDownView">'+locationName+' - <i>'+result.name+'</i></span>';
						str+='<span class="profileImageView">';
						str+='<img src="https://mytdp.com/images/cadre_images/'+result.image+'" />';
						str+='</span>';
						str+='</label>';
						str+='<div class="check"></div>';
					str+='</li>';
				str+='</ul>';
			str+='</div>';
		}
		return str;
	}
	function getView(i,shortName){
		var str='';
		str+='<div class="panel-heading" role="tab" id="headingOneSubView'+i+'">';
				str+='<a role="button" style="width: 30px; display: inline-block;" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordionSub" href="#collapseOneSubView'+i+'" aria-expanded="true" aria-controls="collapseOneSubView'+i+'">';
					str+='<span class="profileImageView">';
					str+='<img src="dist/coreDashboard/img/logo.png" />';
					str+='</span>';
				str+=' </a>';
				str+='<h4 class="panel-title  text-capital"  >'+shortName+' - View</h4>';
		str+='</div>';	
		return str;
	}
	function getMemberName(locationName,shortName){
		var locName = '';
		if(locationName != null && $.trim(locationName).length > 0){
			locName = locationName;
		}else{
			locName =shortName;
		}
		return locName;
	}
	function buildUserLoginLevelDetails(result){
		
	var str='';
	if(result != null ){
		str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
		  str+='<div class="panel panel-default panelProfileView">';
			//MAIN level start
			  var str1 = MainPart(result);
			  str = str + "" +str1;
			//MAIN level end
					
			//GENERAL SECRETARY and DISTRICT and... VIEW Start
			str+='<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">';
			str+='<div class="panel-body">';
			str+='<div class="panel-group" id="accordionSub" role="tablist" aria-multiselectable="true">';
			var userType = result.subList;					
			if(userType != null && userType.length > 0){
			  for(var i in userType){
				str+='<div class="panel panel-default panelProfileView">';
				  var str2 = getView(i,userType[i].shortName);
					str = str+ " " + str2;
				   //
					str+='<div id="collapseOneSubView'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneSubView'+i+'">';
					str+='<div class="panel-body">';
					str+=' <div class="panel-group" id="accordionFirstLevelId'+i+'" role="tablist" aria-multiselectable="true">';
					var userTypeMember = userType[i].subList;
					if(userTypeMember != null && userTypeMember.length >0){
					  for(var j in userTypeMember){
						str+='<div class="panel panel-default panelProfileView">';
						if(userTypeMember[j].subList != null && userTypeMember[j].subList.length > 0){
								str+='<div class="panel-heading" role="tab" id="generalSecSubView'+i+''+j+'" style="height:30px;display:inline-block">';
									str+=' <a role="button" style="width:30px" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordionFirstLevelId'+i+'" href="#collapseGeneralSecSubView'+i+''+j+'" aria-expanded="true" aria-controls="collapseGeneralSecSubView'+i+''+j+'">';
										str+='<span class="profileImageView">';
										str+='<img src="dist/img/logo.png" />';
										str+='</span>';
									str+=' </a>';
									str+='<ul class="radioStyling" style="float:left;">';
										str+=' <li>';
											str+='<input type="radio" id="'+i+''+j+'" name="selector">';
											var locationName = getMemberName(userTypeMember[j].locationName,userTypeMember[j].shortName);
											str+=' <label for="'+i+''+j+'" class="text-capital userStructureClass" attr_userTypeId='+userTypeMember[j].userTypeId+' attr_activityMemberId ='+userTypeMember[j].activityMemberId+' attr_userAccessLevelId='+userTypeMember[j].locationLevelId+' attr_userAccessLevelValuesString='+userTypeMember[j].locationValuesSet+' ><span class="hideDropDownView">'+locationName+' - <i>'+userTypeMember[j].name+'</i></span>';
											str+='<span class="profileImageView">';
											str+='<img src="https://mytdp.com/images/cadre_images/'+userTypeMember[j].image+'" />';
											str+='</span>';
											str+='</label>';
											str+='<div class="check"></div>';
										str+='</li>';
									str+='</ul>';
								str+='</div>';
						}else{
								str+='<div class="panel-body dottedLine" style="float:left;padding-top:0px;padding-bottom:0px;">';
								str+=' <ul class="radioStyling">';
									str+=' <li>';
									str+='<input type="radio" id="'+i+''+j+'" name="selector">';
									var locationName = getMemberName(userTypeMember[j].locationName,userTypeMember[j].shortName);
									str+=' <label for="'+i+''+j+'" class="userStructureClass"  attr_userTypeId='+userTypeMember[j].userTypeId+' attr_activityMemberId ='+userTypeMember[j].activityMemberId+' attr_userAccessLevelId='+userTypeMember[j].locationLevelId+' attr_userAccessLevelValuesString='+userTypeMember[j].locationValuesSet+' ><span class="hideDropDownView">'+locationName+' - <i>'+userTypeMember[j].name+'</i></span>';
									str+='<span class="profileImageView">';
									str+='<img src="https://mytdp.com/images/cadre_images/'+userTypeMember[j].image+'" />';
									str+='</span>';
									str+='</label>';
									str+='<div class="check"></div>';
									str+='</li>';
								str+='</ul>';
								str+='</div>';
						}
					    //GENERAL SECRETARY and DISTRICT and... Level End
						//ORGANIZING SECRETARY and CONSTITUENCT and... Level Start
						
					
					    str+='<div id="collapseGeneralSecSubView'+i+''+j+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="generalSecSubView'+i+''+j+'">';
						str+='<div class="panel-body">';
						str+=' <div class="panel-group" id="sree'+i+''+j+'" role="tablist" aria-multiselectable="true">';
						if(userTypeMember[j].subList != null && userTypeMember[j].subList.length > 0){
							childUserType = userTypeMember[j].subList;
						  for(var k in childUserType){
							if(childUserType[k].subList != null && childUserType[k].subList.length > 0){
							  
							  var childMember = childUserType[k].subList;
								for(var l in childMember){
									str+='<div class="panel panel-default panelProfileView">';
									if(childMember[l].subList != null && childMember[l].subList.length > 0){
										str+='<div class="panel-heading" style="height:30px" role="tab" id="organizerSecSubView'+i+''+j+''+k+''+l+'">';
											str+='<a role="button" style="width:30px;" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#sree'+i+''+j+'" href="#collapseOragaizerSecSubView'+i+''+j+''+k+''+l+'" aria-expanded="true" aria-controls="collapseOragaizerSecSubView'+i+''+j+''+k+''+l+'">';
											str+='<span class="profileImageView">';
											str+='<img src="dist/img/logo.png" />';
											str+='</span>';
											str+='</a>';
											str+='<ul class="radioStyling" style="float:left;">';
												str+='<li>';
													str+='<input type="radio" id="'+i+''+j+''+k+''+l+'" name="selector">';
													var locationName = getMemberName(childMember[l].locationName,childMember[l].shortName);
													str+=' <label for="'+i+''+j+''+k+''+l+'"  class="userStructureClass" attr_userTypeId='+childMember[l].userTypeId+' attr_activityMemberId ='+childMember[l].activityMemberId+' attr_userAccessLevelId='+childMember[l].locationLevelId+' attr_userAccessLevelValuesString='+childMember[l].locationValuesSet+' ><span class="hideDropDownView">'+locationName+' - <i>'+childMember[l].name+'</i></span>';
													str+='<span class="profileImageView">';
													str+='<img src="https://mytdp.com/images/cadre_images/'+childMember[l].image+'" />';
													str+='</span>';
													str+='</label>';
													str+='<div class="check"></div>';
													str+='</li>';
											str+='</ul>';
										str+='</div>';
									}else{
										str+='<div class="panel-body" style="padding-top:0px;padding-bottom:0px;">';
										str+='<ul class="radioStyling" style="float:left;">';
											str+=' <li class=" dottedLine">';
												str+='<input type="radio" id="'+i+''+j+''+k+''+l+'" name="selector">';
												var locationName = getMemberName(childMember[l].locationName,childMember[l].shortName);
												str+=' <label for="'+i+''+j+''+k+''+l+'" class="userStructureClass" attr_userTypeId='+childMember[l].userTypeId+' attr_activityMemberId ='+childMember[l].activityMemberId+' attr_userAccessLevelId='+childMember[l].locationLevelId+' attr_userAccessLevelValuesString='+childMember[l].locationValuesSet+' ><span class="hideDropDownView">'+locationName+' - <i>'+childMember[l].name+'</i></span>';
												str+='<span class="profileImageView">';
												str+='<img src="https://mytdp.com/images/cadre_images/'+childMember[l].image+'" />';
												str+='</span>';
												str+='</label>';
												str+='<div class="check"></div>';
											str+='</li>';
										str+='</ul>';
										str+='</div>';
									}
                                     									
                                     //sub sub childs start
                                    
					    str+='<div id="collapseOragaizerSecSubView'+i+''+j+''+k+''+l+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="organizerSecSubView'+i+''+j+''+k+''+l+'">';
						str+='<div class="panel-body">';
						str+=' <div class="panel-group" id="accordionThirdLevel" role="tablist" aria-multiselectable="true">';
						if(childMember[l].subList != null && childMember[l].subList.length > 0){
							childChildUserType = childMember[l].subList;
						  for(var m in childChildUserType){
							if(childChildUserType[m].subList != null && childChildUserType[m].subList.length > 0){
							 
							  var childChildMember = childChildUserType[m].subList;
								for(var n in childChildMember){
									 str+='<div class="panel panel-default panelProfileView">';
									if(childChildMember[n].subList != null && childChildMember[n].subList.length > 0){
										str+='<div class="panel-heading" style="height:30px" role="tab" id="organizerSecSubView'+i+''+j+''+k+''+l+''+m+''+n+'">';
											str+='<a role="button" style="width:30px;" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordionThirdLevel" href="#collapseOragaizerSecSubView'+i+''+j+''+k+''+l+''+m+''+n+'" aria-expanded="true" aria-controls="collapseOragaizerSecSubView'+i+''+j+''+k+''+l+''+m+''+n+'">';
											str+='<span class="profileImageView">';
											str+='<img src="dist/img/logo.png" />';
											str+='</span>';
											str+='</a>';
											str+='<ul class="radioStyling">';
												str+='<li>';
													str+='<input type="radio" id="'+i+''+j+''+k+''+l+''+m+''+n+'" name="selector">';
													var locationName = getMemberName(childChildMember[n].locationName,childChildMember[n].shortName);
													str+=' <label for="'+i+''+j+''+k+''+l+''+m+''+n+'"  class="userStructureClass" attr_userTypeId='+childChildMember[n].userTypeId+' attr_activityMemberId ='+childChildMember[n].activityMemberId+'  attr_userAccessLevelId='+childChildMember[n].locationLevelId+' attr_userAccessLevelValuesString='+childChildMember[n].locationValuesSet+' ><span class="hideDropDownView">'+locationName+' - <i>'+childChildMember[n].name+'</i></span>';
													str+='<span class="profileImageView">';
													str+='<img src="https://mytdp.com/images/cadre_images/'+childChildMember[n].image+'" />';
													str+='</span>';
													str+='</label>';
													str+='<div class="check"></div>';
													str+='</li>';
											str+='</ul>';
										str+='</div>';
									}else{
										str+='<div class="panel-body" style="padding-top:0px;padding-bottom:0px;">';
										str+='<ul class="radioStyling">';
											str+=' <li class=" dottedLine">';
												str+='<input type="radio" id="'+i+''+j+''+k+''+l+''+m+''+n+'" name="selector">';
												var locationName = getMemberName(childChildMember[n].locationName,childChildMember[n].shortName);
												str+=' <label for="'+i+''+j+''+k+''+l+''+m+''+n+'" class="userStructureClass" attr_userTypeId='+childChildMember[n].userTypeId+' attr_activityMemberId ='+childChildMember[n].activityMemberId+' attr_userAccessLevelId='+childChildMember[n].locationLevelId+' attr_userAccessLevelValuesString='+childChildMember[n].locationValuesSet+' ><span class="hideDropDownView">'+locationName+' - <i>'+childChildMember[n].name+'</i></span>';
												str+='<span class="profileImageView">';
												str+='<img src="https://mytdp.com/images/cadre_images/'+childChildMember[n].image+'" />';
												str+='</span>';
												str+='</label>';
												str+='<div class="check"></div>';
											str+='</li>';
										str+='</ul>';
										str+='</div>';
									}
									str+='</div>';
								}//for
						
					}//if
			}//for
		 }
		 str+='</div>';
		 str+='</div>';
		 str+=' </div>';	
		     
		//sub sub childs end

								str+='</div>';	
								}//for
						
					}//if
			}//for
		 }
		 str+='</div>';
		 str+='</div>';
		 str+=' </div>';	
		
		 //ORGANIZING SECRETARY and CONSTITUENCT and... Level End
	  str+='</div>';
	    }//for
	  }//if
				str+='</div>';
				str+='</div>';
				str+=' </div>';
				 str+='</div>';
				}//view for
			}//view if
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
		
	  $("#userLevelDetailsDiv").html(str);

}
	
</script>
</body>
</html>