<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Cadre Details</title>
	<!-- custom CSS-->
	<link href="css/cadreCommitee/cadreDetails_custom.css" rel="stylesheet" type="text/css">
	<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<!--Bootstrap DatePicker-->
    <link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
		<!--Hover Menu-->
    <link href="css/cadreCommitee/css/jquery.smartmenus.bootstrap.css" rel="stylesheet" type="text/css" />
    	<!--Circle-->
    <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
	<link href="js/scrollator/fm.scrollator.jquery.css" rel="stylesheet" type="text/css">
	<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
	<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
	     <!--   server side pagination CSS-->
    <link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	 <link rel="stylesheet" type="text/css" href="styles/CadreDetails/cadre_details.css"/>

<style>
.prev,.next{
  width:auto !important;
}
</style>
<script>
var globalCadreId = '${cadreId}';
</script>

<style>
.alignDiv{
	padding:5px;
	border-radius:10px;
	border:1px solid #ccc;
	margin-top:5px;
}	
.ui.small.step,
.ui.small.steps .step {
  font-size: 0.92857143rem;
}
.ui.step,
.ui.steps .step {
  font-size: 1rem;
}
.ui.large.step,
.ui.large.steps .step {
  font-size: 1.14285714rem;
}
.ui.steps {
	display: -webkit-inline-box;
	display: -webkit-inline-flex;
	display: -ms-inline-flexbox;
	display: inline-flex;
	-webkit-box-orient: horizontal;
	-webkit-box-direction: normal;
	-webkit-flex-direction: row;
	-ms-flex-direction: row;
	flex-direction: row;
	-webkit-box-align: stretch;
	-webkit-align-items: stretch;
	-ms-flex-align: stretch;
	align-items: stretch;
	margin: 1em 0;
	background: 0 0;
	box-shadow: none;
	line-height: 1.14285714em;
	border-radius: .28571429rem;
	border: 1px solid rgba(34,36,38,.15)
}
.ui.steps:first-child {
	margin-top: 0
}
.ui.steps:last-child {
	margin-bottom: 0
}
.ui.steps .step {
	position: relative;
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-webkit-box-flex: 1;
	-webkit-flex: 1 0 auto;
	-ms-flex: 1 0 auto;
	flex: 1 0 auto;
	-webkit-flex-wrap: wrap;
	-ms-flex-wrap: wrap;
	flex-wrap: wrap;
	-webkit-box-orient: horizontal;
	-webkit-box-direction: normal;
	-webkit-flex-direction: row;
	-ms-flex-direction: row;
	flex-direction: row;
	vertical-align: middle;
	-webkit-box-align: center;
	-webkit-align-items: center;
	-ms-flex-align: center;
	align-items: center;
	-webkit-box-pack: center;
	-webkit-justify-content: center;
	-ms-flex-pack: center;
	justify-content: center;
	margin: 0;
	padding: 1.14285714em 2em;
	background: #fff;
	color: rgba(0,0,0,.87);
	box-shadow: none;
	border-radius: 0;
	border: none;
	border-right: 1px solid rgba(34,36,38,.15);
	-webkit-transition: background-color .1s ease, opacity .1s ease, color .1s ease, box-shadow .1s ease;
	transition: background-color .1s ease, opacity .1s ease, color .1s ease, box-shadow .1s ease
}
.ui.steps .step:after {
	position: absolute;
	z-index: 2;
	content: '';
	top: 50%;
	right: 0;
	border: solid;
	background-color: #fff;
	width: 1.14285714em;
	height: 1.14285714em;
	border-color: rgba(34,36,38,.15);
	border-width: 0 1px 1px 0;
	-webkit-transition: background-color .1s ease, opacity .1s ease, color .1s ease, box-shadow .1s ease;
	transition: background-color .1s ease, opacity .1s ease, color .1s ease, box-shadow .1s ease;
	-webkit-transform: translateY(-50%) translateX(50%) rotate(-45deg);
	-ms-transform: translateY(-50%) translateX(50%) rotate(-45deg);
	transform: translateY(-50%) translateX(50%) rotate(-45deg)
}
.ui.steps .step:first-child {
	padding-left: 2em;
	border-radius: .28571429rem 0 0 .28571429rem
}
.ui.steps .step:only-child {
	border-radius: .28571429rem
}
.ui.steps .step:last-child {
	border-radius: 0 .28571429rem .28571429rem 0;
	border-right: none;
	margin-right: 0
}
.ui.steps .step .title {
	font-family: Lato, 'Helvetica Neue', Arial, Helvetica, sans-serif;
	font-size: 18px;
	font-weight: 400;
	color:#666 !important
}
.ui.steps .step>.title {
	width: 100%
}
.ui.steps .step .description {
	font-weight: 400;
	font-size: 12px;
	color: #666 !important
}
.ui.steps .step>.description {
	width: 100%
}
.ui.steps .step .title~.description {
	margin-top: .25em
}
.ui.steps .step>.icon {
	line-height: 1;
	font-size: 2.5em;
	margin: 0 1rem 0 0
}
.ui.steps .step>.icon, .ui.steps .step>.icon~.content {
	display: block;
	-webkit-box-flex: 0;
	-webkit-flex: 0 1 auto;
	-ms-flex: 0 1 auto;
	flex: 0 1 auto;
	-webkit-align-self: middle;
	-ms-flex-item-align: middle;
	align-self: middle
}
.ui.steps .step>.icon~.content {
	-webkit-box-flex: 1 0 auto;
	-webkit-flex-grow: 1 0 auto;
	-ms-flex-positive: 1 0 auto;
	flex-grow: 1 0 auto
}
.ui.steps:not(.vertical) .step>.icon {
	width: auto
}
.ui.steps .link.step, .ui.steps a.step {
	cursor: pointer
}
.ui.ordered.steps {
	counter-reset: ordered
}
.ui.ordered.steps .step:before {
	display: block;
	position: static;
	text-align: center;
	content: counters(ordered, ".");
	-webkit-align-self: middle;
	-ms-flex-item-align: middle;
	align-self: middle;
	margin-right: 1rem;
	font-size: 2.5em;
	counter-increment: ordered;
	font-family: inherit;
	font-weight: 700
}
.ui.ordered.steps .step>* {
	display: block;
	-webkit-align-self: middle;
	-ms-flex-item-align: middle;
	align-self: middle
}
.ui.vertical.steps {
	display: -webkit-inline-box;
	display: -webkit-inline-flex;
	display: -ms-inline-flexbox;
	display: inline-flex;
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	-webkit-flex-direction: column;
	-ms-flex-direction: column;
	flex-direction: column;
	overflow: visible
}
.ui.vertical.steps .step {
	-webkit-box-pack: start;
	-webkit-justify-content: flex-start;
	-ms-flex-pack: start;
	justify-content: flex-start;
	border-radius: 0;
	padding: 1.14285714em 2em;
	border-right: none;
	border-bottom: 1px solid rgba(34,36,38,.15)
}
.ui.vertical.steps .step:first-child {
	padding: 1.14285714em 2em;
	border-radius: .28571429rem .28571429rem 0 0
}
.ui.vertical.steps .step:last-child {
	border-bottom: none;
	border-radius: 0 0 .28571429rem .28571429rem
}
.ui.vertical.steps .step:after {
	top: 50%;
	right: 0;
	border-width: 0 1px 1px 0;
	display: none
}
.ui.vertical.steps .active.step:after {
	display: block
}
.ui.vertical.steps .step:last-child:after {
	display: none
}
.ui.vertical.steps .active.step:last-child:after {
	display: block
}

@media only screen and (max-width:767px) {
.ui.steps {
	display: -webkit-inline-box;
	display: -webkit-inline-flex;
	display: -ms-inline-flexbox;
	display: inline-flex;
	overflow: visible;
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	-webkit-flex-direction: column;
	-ms-flex-direction: column;
	flex-direction: column
}
.ui.steps .step {
	width: 100%!important;
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	-webkit-flex-direction: column;
	-ms-flex-direction: column;
	flex-direction: column;
	border-radius: 0;
	padding: 2em 2.25em
}
.ui.steps .step:first-child {
	padding: 1.14285714em 2em;
	border-radius: .28571429rem .28571429rem 0 0
}
.ui.steps .step:last-child {
	border-radius: 0 0 .28571429rem .28571429rem
}
.ui.steps .step:after {
	display: none!important
}
.ui.steps .step .content {
	text-align: center
}
.ui.ordered.steps .step:before, .ui.steps .step>.icon {
	margin: 0 0 1rem
}
}
.ui.steps .link.step:hover, .ui.steps .link.step:hover::after, .ui.steps a.step:hover, .ui.steps a.step:hover::after {
	background: #f9fafb;
	color: rgba(0,0,0,.8)
}
.ui.steps .link.step:active, .ui.steps .link.step:active::after, .ui.steps a.step:active, .ui.steps a.step:active::after {
	background: #f3f4f5;
	color: rgba(0,0,0,.9)
}
.ui.steps .step.active {
	cursor: auto;
	background: #f3f4f5
}
.ui.steps .step.active:after {
	background: #f3f4f5
}
.ui.steps .step.active .title {
	color: #4183c4
}
.ui.ordered.steps .step.active:before, .ui.steps .active.step .icon {
	color: rgba(0,0,0,.85)
}
.ui.steps .active.step:after, .ui.steps .step:after {
	display: block
}
.ui.steps .active.step:last-child:after, .ui.steps .step:last-child:after {
	display: none
}
.ui.steps .link.active.step:hover, .ui.steps .link.active.step:hover::after, .ui.steps a.active.step:hover, .ui.steps a.active.step:hover::after {
	cursor: pointer;
	background: #dcddde;
	color: rgba(0,0,0,.87)
}
.ui.ordered.steps .step.completed:before, .ui.steps .step.completed>.icon:before {
	color: #21ba45;
	font-family: Step;
	content: '\e800'
}
.ui.steps .disabled.step {
	cursor: auto;
	background: #fff;
	pointer-events: none
}
.ui.steps .disabled.step, .ui.steps .disabled.step .description, .ui.steps .disabled.step .title {
	color: rgba(40,40,40,.3) !important
}
.ui.steps .disabled.step:after {
	background: #fff
}

@media only screen and (max-width:992px) {
.ui[class*="tablet stackable"].steps {
	display: -webkit-inline-box;
	display: -webkit-inline-flex;
	display: -ms-inline-flexbox;
	display: inline-flex;
	overflow: visible;
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	-webkit-flex-direction: column;
	-ms-flex-direction: column;
	flex-direction: column
}
.ui[class*="tablet stackable"].steps .step {
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	-webkit-flex-direction: column;
	-ms-flex-direction: column;
	flex-direction: column;
	border-radius: 0;
	padding: 1.14285714em 2em
}
.ui[class*="tablet stackable"].steps .step:first-child {
	padding: 1.14285714em 2em;
	border-radius: .28571429rem .28571429rem 0 0
}
.ui[class*="tablet stackable"].steps .step:last-child {
	border-radius: 0 0 .28571429rem .28571429rem
}
.ui[class*="tablet stackable"].steps .step:after {
	display: none!important
}
.ui[class*="tablet stackable"].steps .step .content {
	text-align: center
}
.ui[class*="tablet stackable"].ordered.steps .step:before, .ui[class*="tablet stackable"].steps .step>.icon {
	margin: 0 0 1rem
}
}
.ui.fluid.steps {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	width: 100%
}
.ui.attached.steps {
	width: calc(100% + 2px)!important;
	margin: 0 -1px -1px;
	max-width: calc(100% + 2px);
	border-radius: .28571429rem .28571429rem 0 0
}
.ui.attached.steps .step:first-child {
	border-radius: .28571429rem 0 0
}
.ui.attached.steps .step:last-child {
	border-radius: 0 .28571429rem 0 0
}
.ui.bottom.attached.steps {
	margin: -1px -1px 0;
	border-radius: 0 0 .28571429rem .28571429rem
}
.ui.bottom.attached.steps .step:first-child {
	border-radius: 0 0 0 .28571429rem
}
.ui.bottom.attached.steps .step:last-child {
	border-radius: 0 0 .28571429rem
}
.ui.eight.steps, .ui.five.steps, .ui.four.steps, .ui.one.steps, .ui.seven.steps, .ui.six.steps, .ui.three.steps, .ui.two.steps {
	width: 100%
}
.ui.eight.steps>.step, .ui.five.steps>.step, .ui.four.steps>.step, .ui.one.steps>.step, .ui.seven.steps>.step, .ui.six.steps>.step, .ui.three.steps>.step, .ui.two.steps>.step {
	-webkit-flex-wrap: nowrap;
	-ms-flex-wrap: nowrap;
	flex-wrap: nowrap
}
.ui.one.steps>.step {
	width: 100%
}
.ui.two.steps>.step {
	width: 50%
}
.ui.three.steps>.step {
	width: 33.333%
}
.ui.four.steps>.step {
	width: 25%
}
.ui.five.steps>.step {
	width: 20%
}
.ui.six.steps>.step {
	width: 16.666%
}
.ui.seven.steps>.step {
	width: 14.285%
}
.ui.eight.steps>.step {
	width: 12.5%
}
.ui.small.step, .ui.small.steps .step {
	font-size: .92857143rem
}
.ui.step, .ui.steps .step {
	font-size: 1rem
}
.ui.large.step, .ui.large.steps .step {
	font-size: 1.14285714rem
}
.ui-steps-border
{
	background-color:#B0D5E8;
	padding:10px;
}
.ui.steps .step .days
{
	position:absolute;
	bottom:0px;
	right:-35px;
	z-index:99;
	height:20px;
	width: 60px;
	content:' ';
	background-color:#B0D5E8;
	color:#333;
	padding:4px;
	text-align:center;
	font-weight:bold;
}
.ui.small.steps .step
{
	padding:15px 20px
}
.onoffswitch {
    position: relative; width: 130px;
    -webkit-user-select:none; -moz-user-select:none; -ms-user-select: none;
}
.onoffswitch-checkbox {
    display: none;
}
.onoffswitch-label {
    display: block; overflow: hidden; cursor: pointer;
    border: 2px solid #449D44; border-radius: 20px;
}
.onoffswitch-inner {
    display: block; width: 200%; margin-left: -100%;
    transition: margin 0.3s ease-in 0s;
}
.onoffswitch-inner:before, .onoffswitch-inner:after {
    display: block; float: left; width: 50%; height: 30px; padding: 0; line-height: 30px;
    font-size: 14px; color: white; font-family: Trebuchet, Arial, sans-serif; font-weight: bold;
    box-sizing: border-box;
}
.onoffswitch-inner:before {
    content: "GRIEVANCE";
    padding-left: 10px;
    background-color: #449D44; color: #FFFFFF;
}
.onoffswitch-inner:after {
    content: "INSURANCE";
    padding-right: 10px;
    background-color: #449D44; color: #FFFFFF;
    text-align: right;
}
.onoffswitch-switch {
    display: block; width: 19px; margin: 5.5px;
    background: #FFFFFF;
    position: absolute; top: 0; bottom: 0;
    right: 96px;
    border: 2px solid #449D44; border-radius: 20px;
    transition: all 0.3s ease-in 0s; 
}
.onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-inner {
    margin-left: 0;
}
.onoffswitch-checkbox:checked + .onoffswitch-label .onoffswitch-switch {
    right: 0px; 
}
</style>

</head>
<body>
<div class="modal fade" id="myModalForTableGrieId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog " role="document" style="width: 990px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Candidate Name Complaint Information</h4>
	  </div>
      <div class="modal-body" >
		<div id="modelheightincr">
			<div id="popupContentDiv" ></div>
			<div id="paginationDivId" ></div>			
		</div>		
      </div>
     
    </div>
  </div>
</div>
<section>
	<div class="container">
    	<div class="row">
        	<div class="col-md-4 bg_white" style="padding-top:10px;">

			<input type="hidden" value="" id="cadreRole" />
			<input type="hidden" value="" id="cadreLevel" />
			<input type="hidden" value="" id="cadreCommitteeType" />
				<input type="hidden" value="" id="publicRepresentativeTypId" />
				
			<input type="hidden" value="" id="cadreBoothId" />
			<input type="hidden" value="" id="cadrePartNo" />
			<input type="hidden" value="" id="cadrePanchaytId" />
			<input type="hidden" value="" id="cadremandalId" />
			<input type="hidden" value="" id="cadreRuralORUrbanId" />
			<input type="hidden" value="" id="cadreConstituencyId" />
			<input type="hidden" value="" id="cadrePConstituencyId" />
			<input type="hidden" value="" id="cadreDistrictId" />
			<input type="hidden" value="" id="cadreStateId" />
			<input type="hidden" value="" id="cadreVoterCardNo" />
			<input type="hidden" value="" id="cadreMemberShipId" />
			
            	<table class="table table-bordered">
                	<tr>
                    	<td class="text-bold"><i class="glyphicon glyphicon-user"></i> PERSONAL DETAILS</td>
                    </tr>
					
					<tr style="background:red" id="deletedReasonId">
                      
                    </tr>
                    <tr>
                    	<td>
                        	<div class="media">
                            	<div class="media-left">
                                	<div id="imagePathId">
										<img id="dataLoadingsImgForImagePath" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/>
									</div>
									<div class="font-10" style="padding-left:6px;padding-top:6px">
                                        <i class="glyphicon glyphicon-star"></i>
                                        <i class="glyphicon glyphicon-star"></i>
                                        <i class="glyphicon glyphicon-star"></i>
                                        <i class="glyphicon glyphicon-star-empty"></i>
                                        <i class="glyphicon glyphicon-star-empty"></i>
                                    </div>
									<!--<img src="dist/img/profile.png" class="img-responsive img-rounded" alt="Profile Image">-->
                                </div>
                                <div class="media-body">
                                	<p class="m_0"><strong>NAME</strong> : <span id="nameId"></span></p>
                                    <p class="m_0"><strong>AGE</strong> : <span id="ageId"></span></p>
                                    <p class="m_0"><strong>DOB</strong> : <span id="dobId"></span></p>
                                    <p class="m_0"><strong>QUALIFICATION</strong> : <span id="qualificationId"></span></p>
                                    <p class="m_0"><strong>OCCUPATION</strong> : <span id="occupationId"></span></p>
                                    <p class="m_0"><strong>CASTE</strong> : <span id="casteFormalId"></span></p>
                                    <p class="m_0"><strong>REGISTERED ON</strong>: <span id="registeredOnId"></span></p>
                                    <p class="m_0"><strong>REG. THROUGH</strong>: <span id="registeredAtId"></span></p>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                    	<td>
                        	<i class="glyphicon glyphicon-phone"></i> <span id="mobileNoId"></span> 
                        	<span class="pull-right" id="emailMainSpanId">
	                            <i class="glyphicon glyphicon-envelope"></i> <span id="emailSpanId"></span> 
                            </span><br>
							<div style="margin-top:10px;margin-left:10px;">
								<span id="fbUrlImageId"></span>
								<span id="wAppImageId"></span>
							</div>
                        </td>
                    </tr>
					<tr class="enrollmentCls">
						<td class="text-bold"><i class="glyphicon glyphicon-bookmark"></i> PREVIOUS ENROLEMENTS</td>
					</tr>
					<tr class="enrollmentCls">
					  <td>
						<ul class="enrolled-mem" id="enrollementDiv">
						  
						 </ul>
					  </td>
					</tr>
					<tr>
                    	<td class="text-bold" id="identityHeaderId" style="cursor:pointer;background: rgb(204, 204, 204) none repeat scroll 0% 0%;"><i class="icon-articles"></i> IDENTITY <span class="pull-right" id="identityHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span><span class="pull-right" id="identityShowId"><i class="glyphicon glyphicon-chevron-down"></i></span></td>
                    </tr>
                    <tr >
                    	<td id="identityBodyId">
                        	<p class="m_0">MEMBERSHIP ID : <span id="memberShipNoId"></span></p>
                            <p class="m_0">VOTER CARD NO : <span id="voterIdSpan"></span>&nbsp;&nbsp;(<span class="text-success" id="isFamilyId"></span>)</p>
                            <p class="m_0">PARTY POSITION : <span id="positionId"></span></p>
                            <p class="m_0">PUBLIC REPRESENTATIVE : <span id="representativeId"></span></p>
							<!-- <div id="participatedDivId" style="display:none"><p class="m_0"> PARTICIPATED CONSTITUENCY : <span id="participatedConstId"></span></p></div> -->
                        </td>
                    </tr>
					<tr>
                    	<td class="text-bold" id="addressHeaderId" style="cursor:pointer;background: rgb(204, 204, 204) none repeat scroll 0% 0%;"><i class="glyphicon glyphicon-map-marker"></i> ADDRESS<span class="pull-right" id="addressHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span><span class="pull-right" id="addressShowId"><i class="glyphicon glyphicon-chevron-down"></i></span></td>
                    </tr>
                    <tr>
                    	<td id="addressBodyId">
                        	<p class="m_0">H NO :<span id="houseNoId"></span></p>
                            <p class="m_0">PANCHAYAT : <span id="panchayatId"></span></p>
                            <p class="m_0">MANDAL : <span id="mandalId"></span></p>
                            <p class="m_0">CONSTITUENCY : <span id="constituencyId"></span></p>
                            <p class="m_0">DISTRICT : <span id="districtNoId"></span></p>
                            <p class="m_0">STATE : <span id="stateNoId"></span></p>
                        </td>
                    </tr>
                </table>
				<div class="panel panel-default" id="participatedDivId" style="display:none">
                	<div class="panel-heading" id="participatedConstHeaderId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-file"></i> PARTICIPATED CONSTITUENCY <span class="pull-right" id="participatedConstHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="participatedConstShowId"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>
                    </div>
					<div class="panel-body" id="participatedConstBodyId">
                    	<h5 class="m_0">Location : <span id="participatedConstId" class="text-bold"></span></h5>
                     </div> 
                </div>
				<div class="panel panel-default" id="candidateapptID" style="display:none">
				<div class="panel-heading" id="candidateAppointmentId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="fa fa-calendar-plus-o"></i> CANDIDATE APPOINTMENT <span class="pull-right" id="candidateAppointmentHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="candidateAppointmentShowId"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>
                    </div>
					<div class="panel-body" id="candidateAppointmentBodyId">
					<img id="candidateAppointmentImg" src="images/icons/loading.gif" style="width:45px;height:45px;margin-left:45%">
					<div id="buildCandidateAppointmentUser"></div>
                     </div> 
					 
				</div>
				
            	<div class="panel panel-default">
                	<div class="panel-heading" role="tab" id="headingTwo12">
								<a class="accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion12" href="#comaplaintCountInmgMainDivid" aria-expanded="false" aria-controls="comaplaintCountInmgMainDivid">	
								<h4 class="panel-title text-bold" style="cursor:pointer;">
									<i class="glyphicon glyphicon-user"></i>
										CANDIDATE GRIEVANCE DETAILS
									<span class="pull-right"><span class="count-style" id="candidateTotalComplaintsDiv">0</span></span>
								</h4>
								</a>
							</div>
					<!-- <center><strong>Grievance Details Not Available.</strong></center> -->
                    <div class="panel-body pad_0" id="comaplaintCountInmgMainDivid">
						<img id="complaintCountImg" src="images/icons/loading.gif" style="width:45px;height:45px;margin-left:45%">
						<div id="complaintCountDiv">					  
						</div>
                    	<!--<h5 class="m_0">TOTAL COMPLAINTS <span id="totalComplaintsId" class="text-bold"></span></h5>-->
                        <div class="display-style" id="complaintsDiv">
                          <!--  <div id="donutchart" class="display-style" style="height: 120px;float:left;width:150px;"></div>
                            <ul class="display-style pull-right graph-list" style="padding-right:20px;padding-left:0px;" id="complaintStatusUL">
                            </ul>-->
                        </div>
						<div id="financialDiv" class="m_top20">
					   
						</div>
                    </div> 
                </div>
				<!--swadhin-->
				<div class="panel panel-default">
				<div class="panel-heading" id="cadreBenefitsHeadingId">
					<h4 class="panel-title text-bold" style="cursor:pointer;">
						<img src="images/User_icon_BLACK-01.png"> 
						CANDIDATE BENEFITS <span class="pull-right" id="cadreBenefitsHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="cadreBenefitsShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
					</h4>
				</div>
				<div class="panel-body pad_0" id="cadreBenefitsBodyId">
					<ul class="benefits-block">
						<center><img class="dataLoadingsImgForTabSection" src="images/icons/loading.gif" style="width: 50px; height: 50px;display:none;"></center>
						<li id="candidaterequestedDiv" style="display:none;">
									 FINANCIAL REQUESTED <span class="pull-right" id="candidateRequestAmount">0</span>
						</li>
						<li id="candidateapprovedDiv">APPROVED FINANCIAL SUPPORT <span class="pull-right" id="candidateApprovedAmount">0</span></li>
						<li id="candidatedeathDiv">DEATH INSURANCE REQUESTS <span class="pull-right" >
							<ul  class="hoverclassli">
								<li id="candidateDeathInsurance">0
									
								</li>
							</ul>
							</span></li>
						<li id="candidatehospitalDiv">HOSPITALIZATION INSURANCE REQUESTS <span class="pull-right" >
							<ul  class="hoverclassli">
								<li id="candidateHospitalizationInsurance">0
									
								</li>
							</ul>
							
							</span>
						</li>
						<li id="candidateEducationBenefitDiv">
							<span id="cadreIdSpanForEducationBenefit"></span>	
						</li>
					</ul>
				</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" id="familyMemberHeadingId">
						<h4 class="panel-title text-bold" style="cursor:pointer;">
							<img src="dist/img/family-icon.png">&nbsp;&nbsp;&nbsp;FAMILY MEMBERS<span class="pull-right" id="familyMemberHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span><span class="pull-right" id="familyMemberShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
					</div>
					<div class="panel-body" id="familyMemberBodyId">
					<center><img style="width: 25px; height: 25px;display:none;" src="images/icons/loading.gif" id="dataLoadingsImgForFamilyMembers"/></center>
						<div class="family-members" id="familyMembersDiv"></div>
					</div>
				</div>
				<div class="panel panel-default">
                	<div class="panel-heading" id="familyGrievanceDtlsHeaderId">
					  <h4 class="panel-title text-bold" style="cursor:pointer;"><img src="images/family_icon.png"> FAMILY GRIEVANCE DETAILS<span class="pull-right" id="fmlyGrvncDtlsSpanId"><i class="glyphicon glyphicon-chevron-down" ></i></span>
						 <span class="pull-right"><span class="count-style" id="totalFamilyComplaints">0</span></span>
						</h4>
					</div>
					 <div class="panel-body pad_0" id="familyGrievanceDtlsBodyId">					 
						<div  id="familyMemberDiv">
							<img id="familyMemberImg" src="images/icons/loading.gif" style="width:45px;height:45px;margin-left:45%">
						</div>
                   		<!--<h5>TOTAL FINANCE REQUESTS <span id="headingId" class="text-bold"></span></h5>
                    	<div id="donutchart2" class="display-style" style="height: 120px;float:left;width:90px;"></div>
                    <ul class="display-style pull-right piechart-list pad_0" id="financeSupportUL">
                        
                    </ul>-->
					
                    </div>
                </div>
				<!--SWADHIN-->
				<div class="panel panel-default">
				<div class="panel-heading" id="familyBenefitsHeadingId">
					<h4 class="panel-title text-bold" style="cursor:pointer;">
					<img src="images/family_icon.png">
						FAMILY BENEFITS<span class="pull-right" id="familyBenefitsHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="familyBenefitsShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
					</h4>
				</div>
				<div class="panel-body pad_0" id="familyBenefitsBodyId">
					<ul class="benefits-block">
					<center><img class="dataLoadingsImgForTabSection" src="images/icons/loading.gif" style="width: 50px; height: 50px;display:none"></center>
					<li id="familyrequestedDiv" style="display:none;">
								 FINANCIAL REQUESTED <span class="pull-right" id="familyRequestAmount">0</span>
					</li>
					<li id="familyapprovedDiv">APPROVED FINANCIAL SUPPORT <span class="pull-right" id="familyApprovedAmount">0</span></li>
					<li id="familydeathDiv">DEATH INSURANCE REQUESTS <span class="pull-right" >
						<ul  class="hoverclassli">
							<li id="familyDeathInsurance">0
							</li>
						</ul>
						</span></li>
					<li id="familyhospitalDiv">HOSPITALIZATION INSURANCE REQUESTS<span class="pull-right" >
						<ul  class="hoverclassli">
							<li id="familyHospitalizationInsurance">0
								
							</li>
						</ul>
						
						</span></li>
					<li id="familyEducationBenefitDiv">
						<span id="familyIdSpanForEducationBenefit"></span>	
						
					</li>
					</ul>
				</div>
				</div>
				<!--swadhin-->
				<div class="panel panel-default">
					<div class="panel-heading">
					<a class="accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion12" href="#referralGrievanceDetailsId" aria-expanded="false" aria-controls="referralGrievanceDetailsId">
					
					  <h4 class="panel-title text-bold" style="cursor:pointer;"><img src="images/icon.png">&nbsp REFERRAL GRIEVANCE DETAILS<span class="pull-right" id="referralGrvncDtlsSpanId"></span><span class="pull-right"><span class="count-style" id="refferelTotalCountId">0</span></span></h4>
					  </a>
					</div> 
					<div class="panel-body pad_0" id="referralGrievanceDetailsId" >
					<img id="referralGrievanceLoadingImg" src="images/icons/loading.gif" style="width:45px;height:45px;margin-left:45%;display:none">
					  
					</div>
				  </div>
            	
				<!--SWADHIN-->
				
				<!--<div class="panel panel-default">
                	<div class="panel-heading" id="deathHospitalDivHeaderId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-flash"></i> DEATHS AND HOSPITALIZATION <span class="pull-right" id="deathHospitalDivHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="deathHospitalDivShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
                    </div>
					<center><img id="dataLoadingsImgForDeathCount" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					<div id="deathHospitalDivId">
					</div>
				</div>-->
				<div id="ntrTrustDivId"></div>
				
				<!--<div class="panel panel-default">
                	<div class="panel-heading" id="trainingCampDetailsHeaderId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-flash"></i> TRAINING CAMP DETAILS <span class="pull-right" id="trainingCampDetailsHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up" ></i></span><span class="pull-right" id="trainingCampDetailsShowId"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>
                    </div>
					<!--<center>Deaths And Hospitalization Details Not Available.</center> -->
					<!--<center><img id="dataLoadingsImgForTrainingCampParticipation" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					<div id="trainingCampDetailsBodyId">
						<div id="trainingCampParticipationDivId">
						</div>
					</div>
                </div>-->
				<!--<div class="panel panel-default">
                	<div class="panel-heading" id="grievanceStatusDivId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-flash"></i> GRIEVANCE STATUS DETAILS <span class="pull-right" id="grievanceStatusHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="grievanceStatusShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
                    </div>
					<!--<center>Deaths And Hospitalization Details Not Available.</center> -->
					<!--<center><img id="dataLoadingsImgForGrievanceStatusCount" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					<div id="grievanceStatusMainDivId">
					</div>
				</div>-->
				<!--<div class="panel panel-default">
                	<div class="panel-heading" id="StatusCountsDivId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-flash"></i> GRIEVANCE STATUS COUNTS <span class="pull-right" id="statusCountsHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="statusCountsShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
                    </div>
					<center><img id="dataLoadingsImgForStatusCount" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					<div id="statusCountsMainDivId">
					</div>
				</div>-->
				
            </div>
            <div class="col-md-8">
            	<div class="panel panel-default">
                	<div class="panel-heading" id="cadrMmbrBthHeaderId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-sort-by-attributes" style="transform: rotate(270deg);"></i>&nbsp;&nbsp;&nbsp;CADRE MEMBER BOOTH PERFORMANCE <span class="pull-right" id="cadrMmbrBthShowId"><i class="glyphicon glyphicon-chevron-up"></i></span><span class="pull-right" id="cadrMmbrBthHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>
                    </div>
                    <div class="panel-body" id="cadrMmbrBthBodyId">
                    	<div class="panel-group electionPerformanceDiv" id="accordion" role="tablist" aria-multiselectable="true">
                          
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                	<div class="panel-heading" id="cadreEnrolmentStatsHeaderId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;2014 CADRE ENROLMENT STATS <span class="pull-right" id="cadreEnrolmentStatsShowId"><i class="glyphicon glyphicon-chevron-up"></i></span><span class="pull-right" id="cadreEnrolmentStatsHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>
                    </div>
                    <div class="panel-body" id="cadreEnrolmentStatsBodyId" style="padding:0px 15px;">
                    	<div class="row table-responsive" id="memberShipCountDiv"><!--id="memberShipCountDiv"-->
						</div>
                    </div>
				</div>
					<!--<div class="panel panel-default">
					<div class="panel-heading" id="ivrSummaryHeaderId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;&nbsp;IVR SUMMARY<span class="pull-right" id="ivrSummaryHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span><span class="pull-right" id="ivrSummaryShowId"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>
                    </div>		
					<div class="panel-body" id="ivrSummaryBodyId">
						<div id="ivrTypeDetailsDivId"></div>
					 </div>
				 </div>-->
				<!-- IVR SUMMARY  END ----> 
                <div class="panel panel-default">
                	<div class="panel-heading" id="cadreActivitiesHeaderId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-record"></i>&nbsp;&nbsp;&nbsp;&nbsp;CADRE PARTICIPATION ACTIVITIES <span class="pull-right" id="cadreActivitiesHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span><span class="pull-right" id="cadreActivitiesShowId"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>
                    </div>
                    <div class="panel-body" id="cadreActivitiesBodyId">
                    	<div class="row">
                        	<div class="col-md-4 col-xs-12" id="eventInvitatinDiv">
                            	
                            </div>
                            <div class="col-md-4 col-xs-12">
                            	<table class="table m_0 table-bordered">
                                	<thead>
                                    	<th class="text-center" colspan="3">TRAINING'S</th>
                                    </thead>
                                    <tr class="text-center">
                                    	<td id="totalInvitedCountId">0<br/>Invited</td>
                                        <td id="totalAttendedCountId">0<br/>Attended</td>
                                        <td id="totalAbsentedCountId">0<br/>Absent</td>
                                    </tr>
                                </table>
                            </div>
							 <div class="col-md-4 col-xs-12" id="partyMeetingDescDiv"></div>
							<div class="col-md-12 col-xs-12 col-sm-12" style="margin-top:10px;">
								<div class="panel-group m_top10" id="accordion2323" role="tablist" aria-multiselectable="true">
								  <div class="panel panel-default" id="eventParticipationCollapseDivId" style="display:none;">
									<div class="panel-heading" role="tab" id="headingOne">
									  <a role="button" class="collapsed accordion-toggle" data-toggle="collapse" data-parent="#accordion2323" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
										<h4 class="panel-title">
										  <span>EVENT PARTICIPATION DETAILS</span>
										</h4>
									   </a>
									</div>
									<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
									  <div class="panel-body">
										<div id="participationTableDivId" class="table-responsive"></div>
									  </div>
									</div>
								  </div>
								  <div class="panel panel-default" id="activityMainDivId">
									<div class="panel-heading" role="tab" id="headingFour">
									  <a role="button" class="collapsed accordion-toggle" data-toggle="collapse" data-parent="#accordion2323" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
										<h4 class="panel-title">
										  <span> ACTIVITY PARTICIPATION DETAILS</span>
										</h4>
									   </a>
									</div>
									<div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
									  <div class="panel-body">
										<div id="activityTableDivId" class="table-responsive"></div>
										<div id="activityAttendedTableDivId" class="table-responsive"></div>
									  </div>
									</div>
								  </div>
									<div class="panel panel-default" id="trainingDetailsMainDivId" style="display:none">
									<div class="panel-heading" role="tab" id="headingTwo">
										<a class="collapsed accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion2323" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
										<h4 class="panel-title">
										 TRAINING CAMP PARTICPATION DETAILS
										 </h4>
										</a>
									</div>
									<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
									  <div class="panel-body">
										<table class="table table-bordered">
											<thead>
												<th>Program Name</th>
												<th>INVITED</th>
												<th>ATTENDED</th>
												<th>ABSENT</th>
												<th></th>
											</thead>
											<tbody id="trainingDetailsBodyId">
												
											</tbody>
										</table>
									  </div>
									</div>
								  </div>
								  <div class="panel panel-default" id="partyMeetingDetailsShowHideDiv" style="display:none">
									<div class="panel-heading" role="tab" id="headingThree">
										<a class="collapsed accordion-toggle" role="button" data-toggle="collapse" data-parent="#accordion2323" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
										<h4 class="panel-title">
										 <span id="partymeetingTitledivId"> PARTY MEETING PARTICIPATION DETAILS</span>
										 </h4>
										</a>
									</div>
									<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
									  <div class="panel-body">
										<div id="partymettingParlDivId" style="display:none;" >
											<div id="partyMetindetlsDivId"></div>
										</div>
									  </div>
									</div>
								  </div>
								</div>
							</div>
							
						    </div>
							
                        </div>
                    </div>
					
				
                <div class="panel panel-default" id="electionProfileMainDivId">
                	<div class="panel-heading" id="cadreElectionProfileHeaderId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;&nbsp;CADRE ELECTION PROFILE <span class="pull-right" id="cadreElectionProfileShowId"  style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span><span class="pull-right" id="cadreElectionProfileHideId"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>
                    </div>
                    <div class="panel-body" id="cadreElectionProfileBodyId">
                    	<div class="cadre-election" >
						<div id="electionProfileDivId"> </div>
                        	
                        </div>
                    </div>
                </div>
				
				<!-- Meetings Start -->
                <div class="panel panel-default" id="commitMeetingDiv" style="display:none;" >
                	<div class="panel-heading" id="committeeMetingsHeaderId" style="cursor:pointer;">
                    	<h4 class="panel-title text-bold"><img src="dist/img/photo.png" > COMMITTEE MEETINGS
							<span class="pull-right" style="margin-top:-8px">
							
								<div class="input-group">
									<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i>	</span>
									<input type="text" class="form-control" id="meetingDatePicker" style="width: 170px; padding-left: 0px; padding-right: 0px;"><span class="pull-right" id="committeeMetingsShowId" style="display:none;"><i style="padding-top: 9px;cursor:pointer;" class="glyphicon glyphicon-chevron-up"></i></span><span class="pull-right" id="committeeMetingsHideId"><i class="glyphicon glyphicon-chevron-down" style="padding-top: 9px;cursor:pointer;"></i></span>
								</div>
							
							</span>
						</h4>
                    </div>
                    <div class="panel-body pad_5" id="committeeMetingsBodyId">
                    	<div id="committeMeetingDivId">
                        </div>
						<div id="paginationId" style ="margin-left:267px;"></div>
                    </div>
                </div>
                 <!-- Meetings End -->
				 
				 <div class="panel panel-default">
                	<div class="panel-heading" id="deathHospitalDivHeaderId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><i class="glyphicon glyphicon-flash"></i> DEATHS AND HOSPITALIZATION INSURANCE DETAILS <span class="pull-right" id="deathHospitalDivHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="deathHospitalDivShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
                    </div>
					<center><img id="dataLoadingsImgForDeathCount" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					
					<div id="deathHospitalDivId">
					</div>
				</div>
				
				<div class="panel panel-default">
                	<div class="panel-heading" id="StatusCountsDivId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><!--<i class="glyphicon glyphicon-flash"></i>--><img src="images/family_icon.png"> GRIEVANCE REQUESTS STATUS DETAILS <span class="pull-right" id="statusCountsHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up"></i></span>
						<span class="pull-right" id="statusCountsShowId"><i class="glyphicon glyphicon-chevron-down"></i></span>
						</h4>
                    </div>
					<center><img id="dataLoadingsImgForStatusCount" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					<div id="statusCountsMainDivId" class="table-responsive">
					</div>
				</div>
				
				<div class="panel panel-default">
                	<div class="panel-heading" id="trainingCampDetailsHeaderId">
                    	<h4 class="panel-title text-bold" style="cursor:pointer;"><img src="images/training.png" style="width:30px"/> TRAINING CAMP DETAILS <span class="pull-right" id="trainingCampDetailsHideId" style="display:none;"><i class="glyphicon glyphicon-chevron-up" ></i></span><span class="pull-right" id="trainingCampDetailsShowId"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>
                    </div>
					<!--<center>Deaths And Hospitalization Details Not Available.</center> -->
					<center><img id="dataLoadingsImgForTrainingCampParticipation" src="images/icons/loading.gif" style="width: 50px; height: 50px;"></center>
					<div id="trainingCampDetailsBodyId">
						<div id="trainingCampParticipationDivId">
						</div>
					</div>
                </div>
				
                <div class="panel panel-default">
                	<div class="panel-heading">
					<a id="" class="showbtnCls" title="Click here to Show Committee Details" href="javascript:{newsHideAndShow('newsMainDivId');}">
                    	<h4 class="panel-title text-bold" style=""><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;&nbsp;NEWS<span class="pull-right"><i class="glyphicon glyphicon-chevron-down"></i></span>
						 <i class="pull-right glyphicon glyphicon-triangle-top "></i>							
						</h4>
						</a>
						<a id="" class="newsMainDivId" style="display:none;" title="Click here to Hide Committee Details" href="javascript:{newsHideAndShow('newsMainDivId');}">
						<h4 class="panel-title text-bold" style=""><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;&nbsp;NEWS<span class="pull-right"><i class="glyphicon glyphicon-chevron-up"></i></span>
							 <i class="pull-right glyphicon glyphicon-triangle-bottom "></i>
						</h4>
						</a>
						<div class="pull-right dateCls" style="margin-top: -15px;margin-right: 25px;display:none;">
							<div class="calendar-style"  id="reportrange">
								<div class="caret"></div>
								<i class="glyphicon glyphicon-calendar"></i>
							</div>
						</div>
					</div>
					
					<div class="panel-body" id="newsMainDivId" style="display:none;">
                    	
						
					<center><img style="width: 100px; height: 100px;margin-top:50px" src="images/icons/loading.gif" id="dataLoadingsImgForNewsId"/></center>
					<div id="hideShowNewsDiv" style="display:none;">
                        <div class="panel panel-default">
                        	<div class="panel-heading bg_f9">
                            	<h4 class="panel-title text-bold text-center">
                                	CANDIDATE NEWS
                                </h4>
                            </div>
							<div id="candidateCategoryWiseNewsId"></div>
                         </div>
						
						
						
                        <div class="panel panel-default">
                        	<div class="panel-heading bg_f9">
                            	<h4 class="panel-title text-bold text-center">CANDIDATE LOCATION SPECIFIC NEWS</h4>
                            </div>
                            <div class="panel-body pad_0">
                            	<div class="row pad_10"  >
                                	<div class="col-md-12" style="margin-bottom:10px;" >
										<!-- -->
											<label class="radio-inline">
												<input type="radio" name="radio" id="districtRadioNewsId" class="newsRadioCls">District
											</label>
											<label class="radio-inline">
												<input type="radio" name="radio" id="pConstiRadioNewsId" class="newsRadioCls">Parliament Constituency
											</label>
											<label class="radio-inline hidingAssemlyCls">
												<input type="radio" name="radio" id="aConstiRadioNewsId" class="newsRadioCls">Assembly Constituency
											</label>
											<label class="radio-inline hidingMandalCls" style="display:none;">
												<input type="radio" name="radio" id="mandalRadioNewsId" class="newsRadioCls">Mandal
											</label>
											<label class="radio-inline hidingMandalCls" style="display:none;">
												<input type="radio" name="radio" id="panchayatRadioNewsId" class="newsRadioCls">Panchayat
											</label>
										<!-- -->
									</div>
                                	<div class="col-md-12">
										<div class="row">
											<div class="col-md-12" id="propertiesId"></div>
										</div>
									</div>
								</div>
							</div>
                        </div>
						<div id="issuesMainDiv" class="row">
							<div class="col-md-12 col-xs-12">
								<div class="panel panel-default">
									<div class="panel-heading bg_f9">
										<h4 class="panel-title text-bold ">DEPARTMENT WISE ISSUES SUMMARY<span id="issuesCount" class="pull-right">TOTAL COUNT - 0</span></h4>
									</div>
									<div class="panel-body pad_0">
										<div class="table-scroll">
											<table id="issuesSummary" class="table m_0 m_0">
												
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
                    </div>
                </div>
				
				
				
            </div>
        </div>
		<div class="row m_top20">
        	<div class="col-md-12 col-xs-12">
            	<div class="panel panel-default">
                	<div class="panel-heading">
					<a href="javascript:{hideAndShowCommittee(1);}" title="Click here to Show Committee Details" id="showId">
                    	<h4 class="panel-title text-bold">
                        	<i class="glyphicon glyphicon-briefcase"></i>&nbsp;&nbsp;&nbsp;COMMITTEES<span class="pull-right"><i class="glyphicon glyphicon-chevron-down"></i></span> 
							 <i class="pull-right glyphicon glyphicon-triangle-top "></i>							
                        </h4>
						</a>
						<a href="javascript:{hideAndShowCommittee(2);}" title="Click here to Hide Committee Details" style="display:none;" id="hideId">
							<h4 class="panel-title text-bold">
                        	<i class="glyphicon glyphicon-briefcase"></i>&nbsp;&nbsp;&nbsp;COMMITTEES<span class="pull-right"><i class="glyphicon glyphicon-chevron-up"></i></span> 
							 
							 <i class="pull-right glyphicon glyphicon-triangle-bottom "></i>
                        </h4>
						</a>
                    </div>
					<!--<center><h3>Data Not Available.</h3></center>-->
                    <div class="panel-body" id="committeesDivId" style="display:none;"> 
                    	<div>
                        	<label class="radio-inline urbanClass">
                            	<input type="radio" name="committeeLocation" class="committeeLocCls" value="panchayat">Village/Ward
                            </label>
                            <label class="radio-inline urbanClass">
                            	<input type="radio" name="committeeLocation" class="committeeLocCls" value="mandal">Mandal/Town/Division
                            </label>
                            <label class="radio-inline">
                            	<input type="radio" name="committeeLocation" class="committeeLocCls" value="assemblyConstituency">Assembly Constituency
                            </label>
                            <label class="radio-inline">
                            	<input type="radio" name="committeeLocation" class="committeeLocCls" value="parliamentConstituency">Parliament Constituency
                            </label>
                            <label class="radio-inline">
                            	<input type="radio" name="committeeLocation" class="committeeLocCls" value="district" checked>District
                            </label>
                        </div>
                        <div class="table m_0-responsive m_top10 table-responsive" id="committeesCountDiv">
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<!--<div class="col-md-12 m_top10 pad_10 block" id="surveyDetailsMainDivId">
				<h4 style="border-bottom:1px solid #999">Survey Details</h4>
					
		</div>-->
		 <div class="row">
        	<div class="col-md-12 col-xs-12">
            	<div class="panel panel-default">
                	<div class="panel-heading">
					<a href="javascript:{hideAndShowSurvey(1);}" title="Click here to Show Committee Details" id="surveyshowId"> 
                    	<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;SURVEY DETAILS<span class="pull-right"><i class="glyphicon glyphicon-chevron-down"></i></span>
							<i class="pull-right glyphicon glyphicon-triangle-top "></i>
						</h4>
						</a>
							<a href="javascript:{hideAndShowSurvey(2);}" title="Click here to Hide Committee Details" style="display:none;" id="surveyhideId">
						<h4 class="panel-title text-bold"><i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;&nbsp;SURVEY DETAILS<span class="pull-right"><i class="glyphicon glyphicon-chevron-up"></i></span>
							 <i class="pull-right glyphicon glyphicon-triangle-bottom "></i>
						</h4>
						</a>
                    </div>
                    <div class="panel-body" id="surveyDetailsId" style="display:none;">
						<div>

						  <!-- Nav tabs -->
						  <ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#tabsurvey" aria-controls="tabsurvey" role="tab" data-toggle="tab">TAB SURVEY</a></li>
							<li role="presentation"><a href="#ivrsurvey" aria-controls="ivrsurvey" role="tab" data-toggle="tab" onclick="getSurveysOnCandidateCount(); getCandidateParticipatedSurveyCnt();">IVR SURVEY</a></li>
						  </ul>

						  <!-- Tab panes -->
						  <div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tabsurvey">
								<div class="surveyDetailssCls">	</div>
								<div class="surveyDetailsCls">	</div>
								<center><img id="surveyDataLoadoing" src="images/icons/survey-details.gif" style="width:250px;height:200px;display:none;"/></center>
								<div id="surveyDetailsMainDivId" class=""></div>
							</div>
							<div role="tabpanel" class="tab-pane" id="ivrsurvey">
								<div id="ivrSurveysMainDivId">
								<!--<div class="ivrcandtSurvysCntId"></div>-->
								<div id="ivrSurvysCandtCntId">
								<ul role="tablist" class="nav nav-tabs tab-list display-style" >
								</ul>
								</div>
								<div id="ivrTypeDetailsDivId"></div>
								<div id="ivrDetailsBodyId"></div>
								<div class="ivrSurveyCandtDetailsCls">	</div>
								<center><img id="dataLoadingsImgForIVRDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
								<div class="ivrSurvysDetls"></div>
								<div class="ivrDetailsCls" id="ivrsurveyDetailsId"></div>
								<center><img id="ivrsurveyDataLoadoing" src="images/icons/survey-details.gif" style="width:250px;height:200px;display:none;"/></center>
								</div>
							</div>
						  </div>
						</div>
                    </div>
                </div>
            </div>
        </div>
		
    </div>
    <!-- model -->
<div class="modal fade myModalForDeath">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="deathTitleId"></h4>
      </div>
      <div class="modal-body">
        <div class="row">
			<div class="col-md-12">
				<div style="background: rgba(0, 0, 0, 0.1) none repeat scroll 0% 0%; border: medium none transparent; margin-bottom: 2px;" class="well well-sm">
				<center><img id="dataLoadingsImgForDeathPopUp" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
				<div id="popupForDeathDetails" class="table-scroll-1"></div>	
				</div>
			</div>
		</div>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	<div class="modal fade modalForSurvey">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="background-color:#CCCCCC">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" style="text-align:center;"><b>Survey Participation Details</b></h4>
				</div>
				<center><img id="familySurveyDataLoadoing" src="images/icons/survey-details.gif" style="width:250px;height:200px;display:none;"/></center>
				<div class="modal-body familySurveyDetailsCls">
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	    <!-- model For Ntr Trust -->
<div class="modal fade modalForNtrTrust">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="ntrTrustTitleId"></h4>
      </div>
      <div class="modal-body">
        <div class="row">
			<div class="col-md-12">
				<div>
					<div>

					  <!-- Nav tabs -->
					  <ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active 11"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">CADRE</a></li>
						<li role="presentation" class="22"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">FAMILY</a></li>
					  </ul>
					<!-- Tab panes -->
					  <div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="home">
							<center><img id="dataLoadingsImgForNtrTrust" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
							<div id="ntrTrustDetails"></div>	
						</div>
						<div role="tabpanel" class="tab-pane" id="profile">
							<center><img id="dataLoadingsImgForNtrTrust" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
							<div id="ntrTrustCadreFamilyDetails"></div>	
						</div>
					  </div>

					</div>
				</div>
			</div>
		</div>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

	<!-- model For Training -->
<div class="modal fade modelForTrainingDetails">
  <div class="modal-dialog  modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" style="text-align:center;">TRAINING DETAILS</h4>
      </div>
      <div class="modal-body">
        <div class="row">
			<div class="col-md-12">
			<div id="trainingDetailsTableId"></div>
			<center><img id="dataLoadingsImgForTrainingDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
			</div>
			<div class="col-md-12">
			<div id="remarkDetailsId"></div>
			</div>
			<div class="col-md-12">
				<div id="feedbackDivId"></div>
			</div>
		</div>
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	
<div class="modal fade" id="Ivrmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-lg" role="document">
  <div class="col-md-12 col-xs-12">
	 <div class="panel panel-default">
		<div class="panel-heading">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="panel-title text-bold">IVR DETAILS - <span style="color:green;" id="ivrCadreNameId"></span></h4>
        </div>
		<div class="panel-body">
		<div id="ivrDetailsdataLoding" class="col-xs-offset-6"></div>
		 <div id="modalBodyId"></div>
		 <div id="paginationDivId"  style="margin-top:35px;"></div>
		 <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Close</button>
		 </div>
	 </div>
  </div>
  </div>
</div>
</section>
			<!-- model For benefits -->
		<div class="modal fade" id="modelForBenefitsId">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" style="text-align:center;"><span class="modelTitleCls"><span></h4>
			  </div>
			  <div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div id="benefitsEducationCountsId"></div>
					</div>
				</div>
				</div>
			  <div class="modal-footer">
			   <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<!-- model For benefits Of Family -->
		<div class="modal fade" id="modelForBenefitsFamilyId">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" style="text-align:center;"><span class="modelTitleClsFamily"><span></h4>
			  </div>
			  <div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div id="benefitsEducationCountsFamilyId"></div>
					</div>
				</div>
				</div>
			  <div class="modal-footer">
			   <button type="button" class="btn btn-default btn-success btn-sm" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<div class="modal fade" id="ivrDetailsModelId">
		  <div class="modal-dialog modal-lg">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title text-center"><span id="ivrModalHeadingId"></span></h4>
			  </div>
			  <div class="modal-body">
				<!--<div id="ivrDetailsBodyId">
				</div>-->
				<center><img id="dataLoadingsImgForIVRDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<div class="modal fade" id="deathHospModelDivId">
		  <div class="modal-dialog modal-lg">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title text-center"><span id="deathHospModalHeadingId"></span></h4>
			  </div>
			  <div class="modal-body">
				<div id="deathHospModalBodyId">
				</div>
				<div id="statusDivIdForInsurance" class="m_top20"></div>
				<center><img id="dataLoadingsImgForDeathHospDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<div class="modal fade" id="grievanceDetailsModalDivId">
		  <div class="modal-dialog modal-lg">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title text-center"><span id="grievanceDetailsModalHeadingId"></span></h4>
			  </div>
			  <div class="modal-body">
				<div id="grievanceDetailsModalBodyId">
				</div>
				<div id="statusDivIdForGrievance" class="m_top20"></div>
				<center><img id="dataLoadingsImgForGrievanceStatusDetails" src="images/icons/loading.gif" style="width:50px;height:50px;display:none;margin-top:50px;"/></center>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			</div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
			<div class="modal fade bs-example-modal-sm historyShowModal"  tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">	  
				<div class="modal-dialog modal-lg">
				<div class="modal-content ">
				  <div class="modal-header" style="background-color:#CCCCCC">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title"><span id="appCandidateNameId"></span>APPOINTMENT HISTORY</h4>
				  </div>
				  <div class="modal-body" style="padding:25px 5px 35px 5px;">
					<div id="aptCandidateHistorystatusOverViewDiv"></div>
					<div id="aptCandidateHistoryDiv"></div>
					<div id="appointmentCommentsDiv"></div>
				  </div>
				</div>
			  </div>
		</div>
	

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src=" https://code.jquery.com/ui/1.11.1/jqueryui/1.11.1/jquery-ui.js "></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<!--Circle js file-->
	<script src="js/cadreCommittee/dist/js/jquery.circliful.min.js"></script>
    <!--Bootstrap Date Picker-->
   <script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>   
    <!--Hover Menu-->
    <script src="js/cadreCommittee/js/jquery.smartmenus.min.js" type="text/javascript"></script>
    <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>
	 <script src="js/jquery.classyloader.min.js"></script>
    <script type="text/javascript" src="js/jquery.dataTables.js"></script>	
	<script type="text/javascript" src="js/highcharts/js/highcharts_cadre.js"></script>
	<!-- scrollator -->
	<script type="text/javascript" src="js/scrollator/fm.scrollator.jquery.js"></script>
	<script type="text/javascript" src="dist/scroll/jquery.mCustomScrollbar.js"></script>
	<script type="text/javascript" src="dist/scroll/jquery.mousewheel.js"></script>
	   <!--  SERVER SIDE PAGINATION JS -->
    <script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
	<script src="js/grievance/statusColor.js" type="text/javascript"></script>
	<script src="js/cadreDetails/cadre_details.js" type="text/javascript"></script>
	<script src="js/cadreDetails/cadre_details1.js" type="text/javascript"></script>
	<script>
	var globalCadreId = '${cadreId}';
	
	var participatedConstituencyId=0;
	var participatedConstituencyType="";
	var participatedParliamentId = 0;
	var participatedDistrictId = 0;
	
	var participatedConstName = "";
	var participatedParlName = "";
	var participatedDistName = "";
	
	 //global Ids of CADRE
	 
	 var globalPanchayatId=0;
	 var globalTehsilId=0;
	 var globalConstituencyId=0;
	 var globalParliamentId=0;
	 var globalDistrictId=0;
	 
	 var globalPancName = "";
	 var globalTehsName = "";
	 var globalConstName = "";
	 var globalParlName = "";
	 var globalDistName = "";
	 
	 var globalCandidateId; 
	 var ownBoothDetailsVo;
	 var globalAreaType="";
	
	

	$(document).on('click', '.innerDiv', function(){
		/*var surveyDivId = $(this).attr("attr_survy_divId");
		//$(".allSurveyDtlsCls").hide();
		var temp = $("#"+surveyDivId).css("display");
		alert(temp);
		if(temp != "none"){alert(1);
			$("#"+surveyDivId).css("display")="none";
		}else{alert(2);
			$("#"+surveyDivId).show();
		}*/
		var surveyDivId = $(this).attr("attr_survy_divId");
		
		if($("#"+surveyDivId).hasClass("hideSurvey")){
			$("#"+surveyDivId).removeClass("hideSurvey");
			$("#"+surveyDivId).addClass("showSurvey");
			$("#"+surveyDivId).css({"display":"block"});
		}else if($("#"+surveyDivId).hasClass("showSurvey")){
			$("#"+surveyDivId).removeClass("showSurvey");
			$("#"+surveyDivId).addClass("hideSurvey");
			$("#"+surveyDivId).css({"display":"none"});
		}
	});
	$(document).on('click', '.innerDivFamilyCls', function(){
	
		var surveyDivId = $(this).attr("attr_survy_divId");
		
		if($("#"+surveyDivId).hasClass("hideSurveyCls")){
			$("#"+surveyDivId).removeClass("hideSurveyCls");
			$("#"+surveyDivId).addClass("showSurveyCls");
			$("#"+surveyDivId).css({"display":"block"});
		}else if($("#"+surveyDivId).hasClass("showSurveyCls")){
			$("#"+surveyDivId).removeClass("showSurveyCls");
			$("#"+surveyDivId).addClass("hideSurveyCls");
			$("#"+surveyDivId).css({"display":"none"});
			
		}
	
	});
	
		$('#accordionSurvey').on('shown.bs.collapse', function() {
			var surveyId = $(this).find("div.in").attr("attr_survey_id");
			var cadreId= $(this).find("div.in").attr("attr_cadre_id");
			var indexId=$(this).find("div.in").attr("attr_index_id");
			getTdpCadreSurveyDetails(cadreId,surveyId,indexId,"NotAll",0,'true');
		});

	$(document).on("click",".eachParticipationListCls",function(){
			var constId=$(this).attr("attr_constId");
			var elecType=$(this).attr("attr_election_type");
			var elecYear=$(this).attr("attr_election_year");
			
			var win =window.open("constituencyElectionResultsAction.action?constituencyId="+constId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
			win.focus();
		});
	$('[data-toggle="tooltip"]').tooltip();
		
	$('.table-scroll').scrollator({
		custom_class: 'table-scroll',
	});

$(document).on("click",".newsSubmitBtn",function(){
	getCandidateAndLocationSummaryNews();
});
$(document).on("click",".meetingSubmitBtn",function(){
	getConductedPartyMeetingDetails('','','true','0');
});

var globalDate = "global";
$(document).on("click","#meetingDatePicker",function(){
	globalDate ="meetingGlobal";
});

$("#familyMemberBodyId").collapse('hide');
$(document).on("click","#familyMemberHeadingId",function(){
	var isVisible = $( "#familyMemberHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#familyMemberHideId" ).show();
		 $( "#familyMemberShowId" ).hide();
	}else{
		$( "#familyMemberHideId" ).hide();
		$( "#familyMemberShowId" ).show();
	}
	$("#familyMemberBodyId").collapse('toggle');
});

$("#familyBenefitsBodyId").collapse('hide');
$(document).on("click","#familyBenefitsHeadingId",function(){
	var isVisible = $( "#familyBenefitsHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#familyBenefitsHideId" ).show();
		 $( "#familyBenefitsShowId" ).hide();
	}else{
		$( "#familyBenefitsHideId" ).hide();
		$( "#familyBenefitsShowId" ).show();
	}
	$("#familyBenefitsBodyId").collapse('toggle');
});

$("#cadreBenefitsBodyId").collapse('hide');
$(document).on("click","#cadreBenefitsHeadingId",function(){
	var isVisible = $( "#cadreBenefitsHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#cadreBenefitsHideId" ).show();
		 $( "#cadreBenefitsShowId" ).hide();
	}else{
		$( "#cadreBenefitsHideId" ).hide();
		$( "#cadreBenefitsShowId" ).show();
	}
	$("#cadreBenefitsBodyId").collapse('toggle');
});

$("#deathHospitalDivId").collapse('hide');
$(document).on("click","#deathHospitalDivHeaderId",function(){
	var isVisible = $( "#deathHospitalDivHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#deathHospitalDivHideId" ).show();
		 $( "#deathHospitalDivShowId" ).hide();
	}else{
		$( "#deathHospitalDivHideId" ).hide();
		$( "#deathHospitalDivShowId" ).show();
	}
	$("#deathHospitalDivId").collapse('toggle');
});

$("#familyGrievanceDtlsBodyId").collapse('hide');
$(document).on("click","#familyGrievanceDtlsHeaderId",function(){
	$("#familyGrievanceDtlsBodyId").collapse('toggle');
});

$("#cadreEnrolmentStatsBodyId").collapse('show');
$(document).on("click","#cadreEnrolmentStatsHeaderId",function(){
	var isVisible = $( "#cadreEnrolmentStatsHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#cadreEnrolmentStatsHideId" ).show();
		 $( "#cadreEnrolmentStatsShowId" ).hide();
	}else{
		$( "#cadreEnrolmentStatsHideId" ).hide();
		$( "#cadreEnrolmentStatsShowId" ).show();
	}
	$("#cadreEnrolmentStatsBodyId").collapse('toggle');
});

$("#ivrSummaryBodyId").collapse('hide');
$(document).on("click","#ivrSummaryHeaderId",function(){  
	var isVisible = $( "#ivrSummaryHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#ivrSummaryHideId" ).show();
		 $( "#ivrSummaryShowId" ).hide();
	}else{
		$( "#ivrSummaryHideId" ).hide();
		$( "#ivrSummaryShowId" ).show();
	}
	$("#ivrSummaryBodyId").collapse('toggle');
});

$("#cadreActivitiesBodyId").collapse('hide');
$(document).on("click","#cadreActivitiesHeaderId",function(){
	var isVisible = $( "#cadreActivitiesHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#cadreActivitiesHideId" ).show();
		 $( "#cadreActivitiesShowId" ).hide();
	}else{
		$( "#cadreActivitiesHideId" ).hide();
		$( "#cadreActivitiesShowId" ).show();
	}
	$("#cadreActivitiesBodyId").collapse('toggle');
});

$("#cadreElectionProfileBodyId").collapse('hide');
$(document).on("click","#cadreElectionProfileHeaderId",function(){
	var isVisible = $( "#cadreElectionProfileHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#cadreElectionProfileHideId" ).show();
		 $( "#cadreElectionProfileShowId" ).hide();
	}else{
		$( "#cadreElectionProfileHideId" ).hide();
		$( "#cadreElectionProfileShowId" ).show();
	}
	$("#cadreElectionProfileBodyId").collapse('toggle');
});

$("#participatedConstBodyId").collapse('hide');
$(document).on("click","#participatedConstHeaderId",function(){
	var isVisible = $( "#participatedConstHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#participatedConstHideId" ).show();
		 $( "#participatedConstShowId" ).hide();
	}else{
		$( "#participatedConstHideId" ).hide();
		$( "#participatedConstShowId" ).show();
	}
	$("#participatedConstBodyId").collapse('toggle');
});

$("#cadrMmbrBthBodyId").collapse('show');
$(document).on("click","#cadrMmbrBthHeaderId",function(){ 
var isVisible = $( "#cadrMmbrBthHideId" ).is( ":visible" );
if(isVisible==false){
		 $( "#cadrMmbrBthHideId" ).show();
		 $( "#cadrMmbrBthShowId" ).hide();
	}else{
		$( "#cadrMmbrBthHideId" ).hide();
		$( "#cadrMmbrBthShowId" ).show();
	}
	$("#cadrMmbrBthBodyId").collapse('toggle');
});

$("#trainingCampDetailsBodyId").collapse('hide');
$(document).on("click","#trainingCampDetailsHeaderId",function(){ 
var isVisible = $( "#trainingCampDetailsHideId" ).is( ":visible" );
if(isVisible==false){
		 $( "#trainingCampDetailsHideId" ).show();
		 $( "#trainingCampDetailsShowId" ).hide();
	}else{
		$( "#trainingCampDetailsHideId" ).hide();
		$( "#trainingCampDetailsShowId" ).show();
	}
	$("#trainingCampDetailsBodyId").collapse('toggle');
});

$("#identityBodyId").collapse('hide');
$(document).on("click","#identityHeaderId",function(){ 
var isVisible = $( "#identityHideId" ).is( ":visible" );
if(isVisible==false){
		 $( "#identityHideId" ).show();
		 $( "#identityShowId" ).hide();
	}else{
		$( "#identityHideId" ).hide();
		$( "#identityShowId" ).show();
	}
	$("#identityBodyId").collapse('toggle');
});

$("#addressBodyId").collapse('hide');
$(document).on("click","#addressHeaderId",function(){ 
var isVisible = $( "#addressHideId" ).is( ":visible" );
if(isVisible==false){
		 $( "#addressHideId" ).show();
		 $( "#addressShowId" ).hide();
	}else{
		$( "#addressHideId" ).hide();
		$( "#addressShowId" ).show();
	}
	$("#addressBodyId").collapse('toggle');
});
$("#committeeMetingsBodyId").collapse('hide');
$(document).on("click","#committeeMetingsHeaderId",function(){ 
var isVisible = $( "#committeeMetingsHideId" ).is( ":visible" );
if(isVisible==false){
		 $( "#committeeMetingsHideId" ).show();
		 $( "#committeeMetingsShowId" ).hide();
	}else{
		$( "#committeeMetingsHideId" ).hide();
		$( "#committeeMetingsShowId" ).show();
	}
	$("#committeeMetingsBodyId").collapse('toggle');
});
$("#comaplaintCountInmgMainDivid").collapse('hide');
$("#referralGrievanceDetailsId").collapse('hide');
	$(document).click(function() {
		$('.survey-hover').hide();
	});
	$(document).on("click",".survey-drop",function(){
		$('.survey-hover').hide();
		$(this).parent().find('.survey-hover').show();
		e.stopPropagation(); 
	});
	


$(document).on("click",".newsRadioCls",function(){
	getCandidateAndLocationSummaryNews();
});

	
	
	$(document).on("click",".ranges li",function(){
		if($(this).text() == "Custom"){
			return;
		}
		if(globalDate == "meetingGlobal"){
			getConductedPartyMeetingDetails('','','true','0');
			globalDate="global";
		}else{
			getCandidateAndLocationSummaryNews();
		}
		
		
	});

$('.committeeLocCls').click(function(){
    getLocationwiseCommitteesCount();
  });

$(document).on("click",".candidateRedirectedCls",function(){
	var candidateId=$(this).attr("attr_candidateId");;
	var categoryId=$(this).attr("attr_categoryId");
	var benefitId=$(this).attr("attr_benefitId");
	var fromDate=$(this).attr("attr_fromDate");
	var toDate=$(this).attr("attr_toDate");
	var articlesCount=$(this).attr("attr_count");
	var type="candidate";
	
	var win = window.open('articleDetailsAction.action?cid='+candidateId+'&caid='+categoryId+'&bfid='+benefitId+'&fdt='+fromDate+'&tdt='+toDate+'&typ='+type+'&atCnt='+articlesCount+'', '_blank');
});

$(document).on("click",".departmentNewsCls",function(){
	var fromDate=$(this).attr("attr_fromDate");
	var toDate=$(this).attr("attr_toDate");
	var departmentId=$(this).attr("attr_departmentId");
	var locationId=$(this).attr("attr_locationId");
	var locationType=$(this).attr("attr_locationType");
	var articlesCount=$(this).attr("attr_count");
	var type="department";
	
	var win = window.open('articleDetailsAction.action?did='+departmentId+'&fdt='+fromDate+'&tdt='+toDate+'&typ='+type+'&lid='+locationId+'&ltp='+locationType+'&atCnt='+articlesCount+'', '_blank');
	
});

$(document).on("click",".analysisNewsCls",function(){
	var fromDate=$(this).attr("attr_fromDate");
	var toDate=$(this).attr("attr_toDate");
	var benefitId=$(this).attr("attr_benefitId");
	var locationId=$(this).attr("attr_locationId");
	var partyId=$(this).attr("attr_partyId");
	var propertyId=$(this).attr("attr_propertyId");
	var propertyType=$(this).attr("attr_propertyType");
	var articlesCount=$(this).attr("attr_count");
	var locationType=$(this).attr("attr_locationType");
	var type="location"; 

	var win = window.open('articleDetailsAction.action?fdt='+fromDate+'&tdt='+toDate+'&typ='+type+'&bfid='+benefitId+'&lid='+locationId+'&ltp='+locationType+'&ptid='+partyId+'&prid='+propertyId+'&pttp='+propertyType+'&atCnt='+articlesCount+'','_blank');
});

$(document).on("click",".actionReactionNewsCls",function(){
	var fromDate=$(this).attr("attr_fromDate");
	var toDate=$(this).attr("attr_toDate");
	var locationId=$(this).attr("attr_locationId");
	var partyId=$(this).attr("attr_partyId");
	var propertyId=$(this).attr("attr_propertyId");
	var secondaryPartyId=$(this).attr("attr_secondaryPartyId");
	var propertyType=$(this).attr("attr_propertyType");
	var articlesCount=$(this).attr("attr_count");
	var locationType=$(this).attr("attr_locationType");
	var type="location";
	
	var win = window.open('articleDetailsAction.action?fdt='+fromDate+'&tdt='+toDate+'&typ='+type+'&scpid='+secondaryPartyId+'&ltp='+locationType+'&lid='+locationId+'&ptid='+partyId+'&prid='+propertyId+'&pttp='+propertyType+'&atCnt='+articlesCount+'','_blank');
});

$(document).on("click",".summaryNewsCls",function(){
	var fromDate=$(this).attr("attr_fromDate");
	var toDate=$(this).attr("attr_toDate");
	var locationId=$(this).attr("attr_locationId");
	var partyId=$(this).attr("attr_partyId");
	var propertyId=$(this).attr("attr_propertyId");
	var propertyType=$(this).attr("attr_propertyType");
	var articlesCount=$(this).attr("attr_count");
	var locationType=$(this).attr("attr_locationType");
	var type="location"; 

	var win = window.open('articleDetailsAction.action?fdt='+fromDate+'&tdt='+toDate+'&typ='+type+'&lid='+locationId+'&ltp='+locationType+'&ptid='+partyId+'&prid='+propertyId+'&pttp='+propertyType+'&atCnt='+articlesCount+'','_blank');
});




$(document).on("click",".deathDetailsCls",function(){
	var locationId=$(this).attr("attr_locationId");
	var locationType=$(this).attr("attr_locationType");
	var insuranceTypeId=$(this).attr("attr_insuranceTypeId");
	var insuranceType=$(this).attr("attr_insuranceType");
	
	getCadresDetailsOfDeathsAndHospitalization(locationId,locationType,insuranceTypeId,insuranceType);
});


function getCadresDetailsOfDeathsAndHospitalization(locationId,locationType,insuranceTypeId,insuranceType){
	
	$("#deathTitleId").html('');
	$("#popupForDeathDetails").html('');
	
	$("#dataLoadingsImgForDeathPopUp").show();
	
	var jsObj={
		locationId:locationId,
		locationType:locationType,
		insuranceTypeId:insuranceTypeId
		
	}
	
	$.ajax({
			type:'POST',
			 url: 'getCadresDetailsOfDeathsAndHospitalizationAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				
				$("#dataLoadingsImgForDeathPopUp").hide();
				
				$("#deathTitleId").html("<div style='color:green;'>"+insuranceType+" Cadres Details</div>");
				if(result !=null){
						if(result.knownList !=null && result.knownList.length>0){
							buildingAllCadresDetails(result.knownList);
						}
						else{
							$("#popupForDeathDetails").html("No Data Available.");
						}
					}
				else{
					$("#popupForDeathDetails").html("Problem occured.Please contact admin.");
				}
			});
}
function buildingAllCadresDetails(result){
	var str='';
	for(var i in result){
			
			str+='<div class="media " style="border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].cadreId+'>';
				str+='<span href="#" class="media-left">';
				str+='<img style="width: 64px; height: 64px;" src="'+result[i].imagePath+'" />';
				str+='</span>';
				str+='<div class="media-body">';
				str+='<h5 class="media-heading"> <span style="font-weight:bold;"> Name:</span> '+result[i].name+' ; ';				
				str+=' <span style="font-weight:bold;"> Relative Name: </span>'+result[i].relativeName+' </h5>';
				str+='<ul class="list-inline">';
				str+='<li>Age:'+result[i].age+';</li>';
				str+='<li>Mobile No: '+result[i].mobileNo+'</li>';
				str+='<li>Caste: '+result[i].casteName+'</li>';
				str+='<li>Voter ID: '+result[i].voterIdCardNo+'</li>';
				str+='<li>MemberShip No: '+result[i].membershipNo+'</li>';
				str+='<li>Registered On: '+result[i].registeredOn+'</li>';
				str+='<li>Occupation: '+result[i].occupation+'</li>';
				str+='</ul>';
				 
				str+='</div>';
				<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_DETAILS' )}">
				str+='<div id="cadreDetailsDivId" class="cadreDetailsCls" attr_cadre_id='+result[i].cadreId+' attr_membership_id='+result[i].membershipNo+' style="cursor:pointer;"><input type="button" value="More Cadre Details" class="btn btn-sm btn-primary pull-right"/></div>';
				</c:if>
			str+='</div>';
			
		}
		$("#popupForDeathDetails").html(str);
		$('.table-scroll-1').scrollator({
			custom_class: 'table-scroll-1',
		});
	
}
</script>
<script>

$(document).on("click",".cadreDetailsCls",function(){
		var cadreId=$(this).attr("attr_cadre_id");
		var redirectWindow=window.open('cadreDetailsAction.action?cadreId='+cadreId+'','_blank');
	});

function hideAndShowCommittee(typeId)
{
	if(typeId == 1)
	{
		if(participatedConstituencyType !=null && participatedConstituencyType !="" && participatedConstituencyType !=undefined){
			$(".urbanClass").hide();
		}else{
			if(globalAreaType == "URBAN"){
				$(".urbanClass").hide();
			}
			else{
				$(".urbanClass").show();
			}
		}
			$('#committeesDivId').show();
			$('#showId').hide();
			$('#hideId').show();
		
	}
	else if(typeId == 2)
	{
		$('#committeesDivId').hide();
		$('#showId').show();
		$('#hideId').hide();
	}
}
function hideAndShowSurvey(typeId)
{	
	if(typeId == 1)
	{
		getIVRDetails();
		$('#surveyDetailsId').show();
		$('#surveyshowId').hide();
		$('#surveyhideId').show();
	}
	else if(typeId == 2)
	{
		$('#surveyDetailsId').hide();
		$('#surveyshowId').show();
		$('#surveyhideId').hide();
	}
}

function hideAndShowIVRSurvey(typeId)
{	
	if(typeId == 1)
	{
		getIVRDetails();
		$('#ivrsurveyDetailsId').show();
		$('#ivrsurveyshowId').hide();
		$('#ivrsurveyhideId').show();
	}
	else if(typeId == 2)
	{
		$('#ivrsurveyDetailsId').hide();
		$('#ivrsurveyshowId').show();
		$('#ivrsurveyhideId').hide();
	}
}

function tableshidesandShow(divId,index)
{

	$('#topsurveyTable'+index+'').hide();
	$('.topsurveyTable'+index+'').hide();
	var styleStr = $('#'+divId+'').is(":visible")
	if(styleStr)
	{
		$('#'+divId+'').hide();
		$('.'+divId+'').hide();
		$('.topsurveyTable'+index+'').show();
	}
	else
	{
		$('#'+divId+'').show();
		$('.'+divId+'').show();
		$('#topsurveyTable'+index+'').show();
	}	
}

function newsHideAndShow(divId)
{
	$('.showbtnCls').hide();
	$('.dateCls').hide();
		var styleStr = $('#'+divId+'').is(":visible")
		//console.log(styleStr);
		if(styleStr)
		{
			$('#'+divId+'').hide();
			$('.'+divId+'').hide();
			$('.showbtnCls').show();
			
		}
		else
		{
			$('#'+divId+'').show();
			$('.'+divId+'').show();
			$('.dateCls').show();
		}	
}

	$(document).on("click",".showStudentBenefitModalcls",function(){
		var title=$(this).attr("attr_title");
		var type = $(this).attr("attr_type");
			$(".modelTitleCls").html(title);
			$("#modelForBenefitsId").modal("show");
	});
	
	$(document).on("click",".showStudentBenefitModalclsFamily",function(){
		var title=$(this).attr("attr_title");
		var type = $(this).attr("attr_type");
		$(".modelTitleClsFamily").html(title);
		$("#modelForBenefitsFamilyId").modal("show");
	});
	
	$(document).on("click",".ntrBenefitCountCls",function(){
		var instituId=$(this).attr("attr_id");
		var type=$(this).attr("attr_type");
		getStudentFormalDetailsByCadre(instituId,type);
	});
	
	$(document).on("click",".ntrBenefitCountClsFamily",function(){
		var instituId=$(this).attr("attr_id");
		var type=$(this).attr("attr_type");
		getStudentFormalDetailsByCadre(instituId,type);
	});
	



$(document).on("click",".detailsCls",function(){
	
	var programId = $(this).attr("attr_programId");
	var cadreId = globalCadreId;
	
	$("#modelForTrainingDetails").show();
	$("#dataLoadingsImgForTrainingDetails").show();
	getAttendedTrainingCampBatchDetailsOfCadre(programId,cadreId);
	getRemarkSOfCadreByCallPurpose(programId,cadreId);
	getCategoryFeedBackAnswerForCadre();//call for feedback answer
	
});
/*$("#grievanceStatusMainDivId").collapse('hide');
$(document).on("click","#grievanceStatusDivId",function(){
	var isVisible = $( "#grievanceStatusHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#grievanceStatusHideId" ).show();
		 $( "#grievanceStatusShowId" ).hide();
	}else{
		$( "#grievanceStatusHideId" ).hide();
		$( "#grievanceStatusShowId" ).show();
	}
	$("#grievanceStatusMainDivId").collapse('toggle');
});*/
$("#statusCountsMainDivId").collapse('hide');
$(document).on("click","#StatusCountsDivId",function(){
	var isVisible = $( "#statusCountsHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#statusCountsHideId" ).show();
		 $( "#statusCountsShowId" ).hide();
	}else{
		$( "#statusCountsHideId" ).hide();
		$( "#statusCountsShowId" ).show();
	}
	$("#statusCountsMainDivId").collapse('toggle');
});

$("#candidateAppointmentBodyId").collapse('hide');
$(document).on("click","#candidateAppointmentId",function(){
	var isVisible = $( "#candidateAppointmentHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#candidateAppointmentHideId" ).show();
		 $( "#candidateAppointmentShowId" ).hide();
	}else{
		$( "#candidateAppointmentHideId" ).hide();
		$( "#candidateAppointmentShowId" ).show();
	}
	$("#candidateAppointmentBodyId").collapse('toggle');
});
function getConductedPartyMeetingDetails(divId,searchTypeStr,isFirst,firstRecord)
{
	$("#paginationId").hide();
	/*
		<input type="hidden" value="" id="cadreLevel" />
		<input type="hidden" value="" id="cadreCommitteeType" />
		<input type="hidden" value="" id="publicRepresentativeTypId" />
	*/
	if(searchTypeStr != null && searchTypeStr.trim().length==0)
	{
		var  status = $('#cadreLevel').val();
		//console.log("srishailam : "+status);
		if(status != null && status.length>0)
		{
			if(status == 'district' || status == 'District' )
			{																
				searchTypeStr='district';
				$('#district').addClass('active');
				$('#districttabs').addClass('active');
				divId = "districttabs";
			}
			else if(status == 'constituency' || status == 'Constituency' )
			{																
				searchTypeStr='constituency';	
				$('#constituency').addClass('active');
				$('#constabs').addClass('active');
				divId = "constabs";			
			}
			else if( status == 'mandal' || status == 'Mandal' || status == 'town' || status == 'Town'  || status == 'division' || status == 'Division'  )
			{																
				searchTypeStr='MandalORTownORDivision';
				$('#MandalORTownORDivision').addClass('active');
				$('#mandaltabs').addClass('active');
				divId = "mandaltabs";
			}
			else if(status == 'ward' || status == 'Ward' || status == 'village' || status == 'Village'  ){
				searchTypeStr='VillageORWard';
				$('#VillageORWard').addClass('active');
				$('#villagetabs').addClass('active');
				divId = "villagetabs";
			}
		}
		else
		{
			//var  status = $('#publicRepresentativeTypId').val();
			//console.log("srishailam111 : "+status);
				searchTypeStr='constituency';	
				//$('#constituency').addClass('active');
				$('#constabs').addClass('active');
				divId = "constabs";	
		}
		
	}
				
	//$('#'+divId+'').html('<center><img style="width: 50px; height: 50px;margin-top:50px" src="images/icons/loading.gif" id="dataLoadingsImgForNewsId"/></center>');
	setTimeout(function(){
	var startDate = $('.meetingSubmitBtn').parent().find('.dp_startDate').val();
	var endDate = $('.meetingSubmitBtn').parent().find('.dp_endDate').val();
	
	var searchType ='';
	$('.meetingCls').each(function(){
		var tabDivId = $(this).attr('id');		
		var isActive =  $('#'+tabDivId+'').hasClass('active');
		//console.log(isActive);
		if(isActive)
		{
			searchTypeStr = tabDivId;
			divId =  $('#'+tabDivId+'').attr('key');
		}			
	});
$('#'+divId+'').html('<center><img style="width: 50px; height: 50px;margin-top:50px" src="images/icons/loading.gif" id="dataLoadingsImgForNewsId"/></center>');
	var committeeLevelId = 0;
	var committeeLevelValue = 0;
	var meetingLevel ="Village/Ward";
	var isMandal = false;
	if(searchTypeStr =='state')
	{
		committeeLevelId = 10;
		committeeLevelValue = $('#cadreStateId').val();
		meetingLevel ="State";
	}
	else if(searchTypeStr =='district')
	{
		committeeLevelId = 11;
		committeeLevelValue = $('#cadreDistrictId').val();
		 meetingLevel ="District";
	}
	else if(searchTypeStr =='constituency')
	{
		committeeLevelId = 4 ;
		committeeLevelValue = $('#cadreConstituencyId').val();
		 meetingLevel ="Constituency";
	}
	else if(searchTypeStr =='MandalORTownORDivision')
	{
		committeeLevelId = 9;
		committeeLevelValue = $('#cadreRuralORUrbanId').val();
		if(committeeLevelValue > 0 )
		{
			if(committeeLevelValue != 1110  && committeeLevelValue != 124)
			{
				committeeLevelId = 7;
				committeeLevelValue = $('#cadremandalId').val();
			}
		}
		else{
			isMandal = true;
			committeeLevelId = 5;
			committeeLevelValue = $('#cadremandalId').val();
		}
		 meetingLevel ="Mandal/Town/Division";
	}
	else if(searchTypeStr =='VillageORWard')
	{
		committeeLevelId = 6;
		committeeLevelValue = $('#cadrePanchaytId').val();
		if(isMandal)
		{
			committeeLevelId = 8;
		}
		 meetingLevel ="Village/Ward";
	}
	
	$('#meetingDatePicker').val(startDate+" to "+endDate);
	var jsObj={
			tdpCadreId:globalCadreId,
			searchTypeStr:searchTypeStr,
			committeeLevelId:committeeLevelId,
			committeeLevelValue:committeeLevelValue,
			formDateStr:startDate,
			toDateStr :endDate,
			isFirst:"true",
			firstRecord:firstRecord,
			maxResult:6
		}	
		$.ajax({
				type:'GET',
				 url: 'getAreaWiseConductedPartyMeetingDetailsForCadre.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(result !=null){
						buildConductedMeetingDetails(divId,result,meetingLevel,searchTypeStr,firstRecord);
					}
			});
	
	},1000);
}
function buildConductedMeetingDetails(divId,result,meetingLevel,searchTypeStr,firstRecord)
{
		var str='';
		if(result != null){
		str+='<table class="table table-condensed m_0" style="border:1px solid #ddd">';
		str+='<tr>';
		str+='<td class="pad_10" style="background-color:#CCCCCC;">';
		str+='<h4 class="m_0">';
		str+=''+meetingLevel+' Level<br/> Meeting Details';
		str+='</h4>';
		str+='</td>';
		str+='<td  class="pad_10" style="border-left:1px solid #ddd;background-color:#CCCCCC;">';
		str+='<ul class="list-inline">';
		str+='<li>';
		str+='<h2 class="m_0">';
		if(result.totalCount != null)
			str+=''+result.totalCount+'';
		else 
			str+='0';
		str+='</h2>';
		str+='</li>';
		str+='<li>';
		str+='<h4 class="m_0">';
		str+='Total Planned <br/> Meetings';
		str+='</h4>';
		str+='</li>';
		str+='</ul>';
		str+='</td>';
			str+='<td  class="pad_10" style="border-left:1px solid #ddd;background-color:#CCCCCC;">';
				str+='<ul class="list-inline">';
					str+='<li>';
						str+='<h2 class="m_0">';
						if(result.actualCount != null)
							str+=''+result.actualCount+'';
						else 
							str+='0';
						str+='</h2>';
					str+='</li>';
					str+='<li>';
						str+='<h4 class="m_0">';
						str+='Total Conducted <br/> Meetings';
					str+='</h4>';
					str+='</li>';
				str+='</ul>';
			str+='</td>';
		str+='</tr>';
		if(result.meetingTrackingVOList != null && result.meetingTrackingVOList.length>0)
		{
			for(var i in result.meetingTrackingVOList)
			{
				str+='<tr>';
				str+='<td>'+result.meetingTrackingVOList[i].monthName+'</td>';
				str+='<td>'+result.meetingTrackingVOList[i].totalCount+'</td>';
				str+='<td>'+result.meetingTrackingVOList[i].actualCount+'</td>';
				str+='</tr>';
			}		
		}
		
		str+='<td colspan="3" style="background-color:#ccc">';
		if(result.totalCount >6)
		{
			str+='<p class="m_0 text-center">Showing Last 6 Months Details<br/></p>';
			//str+='<p class="m_0 text-center">Showing Last 6 Months Details<br/><a href="javascript:{getConductedPartyMeetingDetails(\''+divId+'\',\''+searchTypeStr+'\',\'false\',\'0\')}"></a></p>';
		}
			
		str+='</td>';
		str+='</tr>';
		str+='</table>';
		
	}
	
	$('#'+divId+'').html(str);
	$("#paginationId").show();
	if(firstRecord == 0){
		var totalCount = result.totalCount;
        $("#paginationId").pagination({
          items: totalCount,
          itemsOnPage: 6,
          cssStyle: 'light-theme',
          hrefTextPrefix: '#pages-',
          onPageClick: function(pageNumber) { 
            var firstRecord=(pageNumber-1)*6;
			  getConductedPartyMeetingDetails(divId,searchTypeStr,"true",firstRecord);
               
          }
          
        });
	}
		
}
</script>

</body>
</html>