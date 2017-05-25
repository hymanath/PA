<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sent Resolution</title>
<link rel="shortcut icon" href="static/img/fevicon.png">
<link href="resolutionStyles/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="resolutionStyles/css/custom.css" rel="stylesheet" type="text/css">
<link href="resolutionStyles/css/responsive.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="resolutionStyles/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
header.eventsheader 
	{
		background-image:url("resolutionStyles/img/header-footer.png");
		background-color:#fed501;
		background-size:100% auto;
		background-position:center bottom;
		background-repeat: no-repeat;
		background-origin: border-box;
		background-repeat: no-repeat;
		height: 71px;
	}
	p {
    line-height: 17px;
    padding: 0 0 15px;
}
</style>
<body >
 <header class="eventsheader">
	<div class="container">
        <div class="row">
            <div class="col-md-3 col-xs-6 col-sm-2 span2 logoR">
                <img src="resolutionStyles/img/logo.png" class="img-responsive" alt="logo">
            </div>
            <div class="col-md-1 col-xs-1 col-sm-2 span1 cbnR">
                <img src="resolutionStyles/img/CBN1.png" class="img-responsive" alt="cbn">
            </div>
            <div class="col-md-4 col-xs-7 col-sm-4 text-center headingR" style="line-height:20px;">               
                 <p class="header-text display-style" id="mainheading" style="font-size:22px !important;color:#5c2d25 !important;margin-top:12px !important;margin-bottom:0px;text-align:center">TELUGU DESAM PARTY</p><p  style="text-align:center;color: rgb(92, 45, 37) ! important; font-size: 14px ! important;"  class="header-text display-style">Dare To Dream - Strive To Achieve</p>                
            </div>
            <div class="col-md-1 col-xs-1 col-sm-2 span1 ntrR"><img src="resolutionStyles/img/NTR1.png" style="margin-top: 5px;" class="img-responsive" alt="ntr">  
            </div>
        </div>       
    </div>
</header>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
						<div class="col-sm-6">
							<h4 class="panel-title">SEND RESOLUTION </h4>
						</div>						
					</div>
				</div>
				<div class="panel-body">
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="createNotification">
								<div class="col-md-4 col-md-offset-3">
									<label>MEMBERSHIPID:</label>
									<input type="text"  name="name"  class="form-control" id="TdpCardeId"
											placeholder="Please Enter MemberShip ID"/>								
									<div style="color:red;" id="TdpCardeIdErrId"></div>
								</div>
								<div class="col-md-4 col-md-offset-3">
									<label>Subject:</label>
									<input type="text"  name="name"  class="form-control" id="ResoultionTypeId"
											placeholder="Please Enter Subject Text"/>								
									<div style="color:red;" id="ResoultionTypeErrId"></div>
								</div>
								<div class="col-md-4 col-md-offset-3">
									<label>Mail Body Description:</label>
									<textarea type="text"  name="name"  class="form-control" id="DescriptionId"
											placeholder="Please Enter Body Text"></textarea>								
									<div style="color:red;" id="DescriptionErrId"></div>
								</div>
								<div class="col-md-4 col-md-offset-3">
									<label>URL LINK:</label>
									<input type="text"  name="name"  class="form-control" id="addYoutubeUrlId"	placeholder="Please Enter URL Key"/>								
									<div style="color:red;" id="addNotificationTypeTextErrId"></div>
								</div>
									
								<div class="col-md-4 col-md-offset-3">
									<input type="button" id="notificationId" attr_success="Mail Sended..." style="padding-top: 5px; margin-top: 14px;"  class="btn btn-success btn-block"   value="SEND EMAIL"  onclick="SendMails();"/>
									<div id="notificationSuccessId" sytle="color:green"></div>
								</div>
							</div>
						</div>
					</div>
				</div>				
			</div>
		</div>
	</div>
</div>

<script src="resolutionStyles/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="resolutionStyles/js/bootstrap.min.js" type="text/javascript"></script>
<script src="resolutionStyles/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script type="text/javascript">
$(".chosenClass").css("width","100%");
function SendMails(){
	$("#TdpCardeIdErrId").html("");
	$("#ResoultionTypeErrId").html("");
	$("#DescriptionERRId").html("");
	var TdpCardeId = $("#TdpCardeId").val();
	var ResoultionTypeId = $("#ResoultionTypeId").val().trim();
	var DescriptionId = $("#DescriptionId").val().trim();
	var addYoutubeUrl = $("#addYoutubeUrlId").val().trim();
	if(TdpCardeId==0){
		$("#TdpCardeIdErrId").html("Please select one Cadre Id.");
		return;
	}
	if(ResoultionTypeId.length==0){
		$("#ResoultionTypeErrId").html("Please type one ResoultionType.");
		return;
	}
	if(DescriptionId.length==0){
		$("#DescriptionERRId").html("Please type one Description.");
		return;
	}
	var JSONObject= {"TdpCardeId":TdpCardeId,
	"subject":ResoultionTypeId,
	"description":DescriptionId,
	"addYoutubeUrl":addYoutubeUrl};
	$.ajax({
		type : 'POST',
		url : 'sentresolutionMail',
		dataType : 'json',
		data: JSONObject
	}).done(function(result){  
	if(result=="Success"){
		$("#submitId").html("Notification Sent Done Successfully");
		$("#TdpCardeId").val('');
		$("#ResoultionTypeId").val('');
		$("#DescriptionId").val('');
		$("#addYoutubeUrlId").val('');
		setTimeout(function(){ 
			$( "#submitId" ).fadeOut( "slow" );
		}, 2000);
	}else{
		$("#submitId").html("Please Try again...");
	}
});
}
</script>
</body>
</html>