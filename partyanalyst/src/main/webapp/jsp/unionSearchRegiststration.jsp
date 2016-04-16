<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<style type="text/css">
h1,h2,h3,h4,h5,h6,p,ul
{
	margin:0px;
}
label
{
	font-weight:400
}
.registeredMem
{
	padding:0px;
	margin-top:20px;
}
.registeredMem li
{
	list-style:none;
	border:1px solid #ddd;
	padding:4px;
}
.registeredMem li img.profile
{
	height:60px;
	width:40px;
}
.registeredMem li .media .media-object
{
	margin:0px;
}
.m_top20
{
	margin-top:20px;
}
.m_top25
{
	margin-top:25px;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-8 col-md-offset-2">
        	<div class="panel panel-default">
            	<div class="panel-heading">
               		<h4 class="panel-title">TELUGU GRADUATES CELL ENROLLEMENT</h4>
                </div>
                <div class="panel-body">
                	<div class="row">
                    	<div class="col-md-12">
                        	<div class="form-group">
                            	<label>Are you registered as TDP Cadre in 2014-2016 &nbsp;</label>
								<label class="radio-inline">
									<input type="radio" name="tdpCadre" value="yes" class="searchCls" checked />Yes
								</label>
								<label class="radio-inline">
									<input type="radio" name="tdpCadre" value="no" class="searchCls"/>No
								</label>
                            </div>
                        </div>
						
						
						<div class="inputChoice col-md-8 col-sm-12 col-xs-12">
							<div class="form-inline btn btn-default">
								<div class="radio">
									<label><input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" id="membershipId" value="1">  MEMBERSHIP ID    &nbsp;&nbsp;</label>
								
									<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  value="2" >  VOTER ID    &nbsp;&nbsp;</label>
								
									<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="mobileNo"    value="3"> MOBILE NO &nbsp;&nbsp;</label><br>
									
									
									<input type="hidden" id="cadreSearchType" value="membershipId" />
								</div>				  
							</div>
						</div>
						
						
                        <div class="col-md-6 m_top20">
                        	<label class="memberCls">Search By Membership Id/Mobile No/Voter Id</label>
							<label class="nonMemberCls" style="display:none;">Search By Membership Voter Id</label>
                            <input type="text" id="searchBy" class="form-control"/>
							<div class="errCls" style="color:red;"></div>
							<div id="searchErrDiv"></div>
                        </div>
						<div class="col-md-2 m_top20">
							<button class="btn btn-success m_top25" id="searchId">SEARCH</button>
						</div>
						<!--
                        <div class="col-md-12">
                        	<ul class="registeredMem">
                            	<li>
                                	<div class="media">
                                    	<div class="media-left">
                                        	<img class="media-object thumbnail" src="" alt="Profile Image"/>
                                        </div>
                                        <div class="media-body">  
	                                        <span class="pull-right">
                                            	<input type="checkbox"/>
                                            </span>
                                        	<p>Name: Ashok Dakavaram</p>
                                            <p>Mobile No: XXXXX-XX766</p>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
						
						-->
						<input type="hidden" value="" id="hiddenCadreId"/>
						<div class="">
							<img src='images/icons/cadreSearch.gif' class="offset7"  id="searchDataImg" class="col-md-4 col-md-offset-2" style="display:none"/>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
								<div id="topPaginationDivId" class="paginationDivId"></div>
								<div class="well well-sm" style="border: medium none transparent;margin-bottom:2px;overflow:scroll:900px;display:none;" id="cadreDetailsDiv"></div>
								<div id="paginationDivId"  class="paginationDivId"></div>

							</div>
						</div>	
						
						
                        <div class="col-md-2">
                        	<button class="btn btn-success m_top20">GET OTP</button>
                        </div>
                        <div class="col-md-2">
                        	<input type="text" class="form-control m_top20"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>	
	<!-- Modal for Remove cadre -->
			<div class="modal fade" id="removeModalDivId">
			  <div class="modal-dialog modal-sm">
				<div class="modal-content">
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="removeModalTitleId" style="color:red;">Removing Cadre</h4>
				  </div>
				  <div class="modal-body" id="ramoveModalBodyDivId">
					<div id="errorDivId" style="color:red"></div>
					<div id="successDivId"></div>
					<div class="row">
						<div class="col-md-12">
							<div><b>Cadre Name :</b> <span id="cadreName"></span></div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 m_top10">
							<div><b>Reason <span style="color:red">*</span>:</b>
								<select id="reasonSelectId" class="form-control">
									<option value="0">Select Reason</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 m_top10">
							<div><b>Remark <span style="color:red">*</span>:</b> 
							<textarea class="form-control" id="remarkTextAreaId"></textarea></div>
						</div>
					</div>
					</div>
				  <div class="modal-footer">
					<button type="button" class="btn btn-primary btn-sm" id="saveRemovingCadreDetailsId">Remove</button>
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
				  </div>
				</div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
			<div class="modal fade" id="modalDivId">
			  <div class="modal-dialog">
				<div class="modal-content">
				
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="modalTitleId" style="color:red;"><span id="modalTitleNameId"></span></h4>
				  </div>
				  
				  <div class="modal-body" id="modalBodyDivId"></div>
				  
				  <div class="modal-footer">
					<span class="pull-left" id="modalSuccessId"></span>
					<span id="modalfooterNameId"></span>
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
				  </div>
				  
				</div>
			  </div>
			</div>

<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="js/tdpCadreAjax/tdpCadreAjax.js" type="text/javascript"></script>
<script src="js/unionSearchRegiststration/unionSearchRegiststration.js" type="text/javascript"></script>
<script>
$(document).keypress(function(e) {
	if(e.keyCode==13){
		getCadreDetailsBySearchCriteria(0);
	paginationDivId}
});
function buildCadreDetails(result,jsObj){
		$(".paginationDivId").show();
		var str ='';
		var elegRolCnt=0;
		var dtCnt = 0;
		if(result != null)
		{
			for(var i in result)
			{
				
				if(result[i].deletedStatus == "MD"){
					str+='<div class="media eachCadreMainDivCls" style="background: rgba(255, 0, 0, 0.1) none repeat scroll 0 0;padding: 5px;border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].tdpCadreId+'>';
				}else{
					 str+='<div class="media" id="main'+result[i].tdpCadreId+'" style="border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].tdpCadreId+'>';
				}
				
				str+='<span href="#" class="media-left">';
				str+='<img style="width: 64px; height: 64px;" src="images/cadre_images/'+result[i].imageURL+'" />';
				str+='</span>';
				str+='<div class="media-body">';
				str+='<h5 class="media-heading"> <span style="font-weight:bold;"> Name:</span> '+result[i].cadreName+' ; ';				
				str+=' <span style="font-weight:bold;"> Relative Name: </span>'+result[i].relativeName+' </h5>';
				str+='<ul class="list-inline">';
				str+='<li>Age:'+result[i].age+';</li>';
				str+='<li>Gender: '+result[i].gender+'</li>';
				str+='<li>Mobile No: <span id="mobile'+result[i].tdpCadreId+'">'+result[i].mobileNo+'</span></li>';
				str+='<li>Caste: <span id="caste'+result[i].tdpCadreId+'">'+result[i].casteName+'</span></li>';
				str+='<li>Voter ID: '+result[i].voterCardNo+'</li>';
				str+='<li>MemberShipNo: '+result[i].memberShipCardId+'</li>';
				str+='<li>Registered Through: '+result[i].dataSourceType+'</li>';
				if(result[i].deletedStatus == "MD"){
					str+='<li><b style="color:red;">Deleted Reason</b> : '+result[i].deletedReason+'</li>';
				}
				else{
					str+='<li id="delete'+result[i].tdpCadreId+'"></li>';
				}
				//str+='<li>Aadhar: '+result[i].imageURL+'</i>';
				str+='</ul>';
				
				str+='<div>';
				if(result[i].deletedStatus != "MD"){
					
					<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT_GROUP') || fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT')}">
					
						str+='<div id="rc'+result[i].tdpCadreId+'" class="pull-right cadreRemoveCls" style="margin-left:3px;" attr_cadre_id='+result[i].tdpCadreId+' attr_cadre_name ="'+result[i].cadreName+'"><i class="glyphicon glyphicon-remove remove-icon" data-toggle="tooltip" data-placement="bottom" title="Remove Cadre"></i></div>';
						
						str+='<div id="uc'+result[i].tdpCadreId+'" class="pull-right updateCadreClass" style="margin-left:3px;" attr_cadre_id='+result[i].tdpCadreId+' attr_mobile_no ="'+result[i].mobileNo+'" attr_caste_name ="'+result[i].casteName+'" attr_cadre_name ="'+result[i].cadreName+'"><i class="glyphicon glyphicon-edit remove-icon" data-toggle="tooltip" data-placement="bottom" style="margin-right: 3px;" title="Update Cadre MobileNo And Caste"></i></div>';
						
					</c:if> 
				}
				
				<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_DETAILS' )}">
				str+='<div id="cadreDetailsDivId" class="cadreDetailsCls" attr_cadre_id='+result[i].tdpCadreId+' attr_membership_id='+result[i].memberShipCardId+' style="cursor:pointer;"><input type="button" value="More Cadre Details" class="btn btn-sm btn-primary pull-right"></div>';
				</c:if> 
				str+='</div>';
				
				if(result[i].committeePosition != null && result[i].committeePosition.trim().length > 0)
				{
					str+='<ul>';
					str+='<li style="font-weight:bold;display: block;">Existing Designation : '+result[i].committeePosition+' for '+result[i].committeeName+' Committee in '+result[i].committeeLocation+'</li>';	
					if(result[i].previousRoles != null && result[i].previousRoles.length > 0){
						for(var j in result[i].previousRoles){
							str+='<li style="font-weight:bold;">Electoral for '+result[i].previousRoles[j].committeeName+' Committee in '+result[i].previousRoles[j].committeeLocation+'</li>';
						}
					}
					
					str+='</ul>';	
					str+='</div>';
					str+='</div>';
			}
			
				else{
					if(result[i].previousRoles != null && result[i].previousRoles.length > 0){
					    str+='<ul>';
						for(var j in result[i].previousRoles){
							str+='<li style="font-weight:bold;">Electoral for '+result[i].previousRoles[j].committeeName+' Committee in '+result[i].previousRoles[j].committeeLocation+'</li>';
						}
					    str+='</ul>';
					}
					str+='</div>';
					str+='</div>';
				
				}
				elegRolCnt++;
				dtCnt++;
			}
		if(result[0].mobileType > 50)	
		{
		var itemsCount=result[0].mobileType;
	    var maxResults=jsObj.maxIndex;
	   
	     if(jsObj.startIndex==0){
		   $(".paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*50;
				getCadreDetailsBySearchCriteria(num);
				
			}
			});
		}
		
		}
		}
		$('#cadreDetailsDiv').html(str);
		$('[data-toggle="tooltip"]').tooltip();
}

</script>
</body>
</html>
