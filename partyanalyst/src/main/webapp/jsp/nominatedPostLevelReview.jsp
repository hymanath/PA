
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NOMINATED POST PROFILE CREATION</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/NominatedPost/custom.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<ol class="breadcrumb">
            	<li><i class="glyphicon glyphicon-home"></i></li>
                <li>Nominated Post Shortlisting</li>
            </ol>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12" >
        	<h4 class="headingColor">State Level - Board/Corporation Overview</h4>
				<div id="stateWiseOverviewId"  style="margin-top: 15px"></div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<h4 class="headingColor">District Level - Board/Corporation Overview</h4>
			<div id="districtWiseOverviewId"  style="margin-top: 15px"></div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<h4 class="headingColor">Constituency Level - Board/Corporation Overview</h4>
			<div id="assemblyWiseOverviewId"  style="margin-top: 15px"></div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<h4 class="headingColor">Mandal/Muncipality/Corporation Level - Board/Corporation Overview</h4>
			<div id="mandalORMunciWiseOverviewId"  style="margin-top: 15px"></div>
        </div>
        <div class="col-md-12 col-xs-12 col-sm-12">
        	<h4 class="headingColor">Panchayat/Ward/Division Level - Board/Corporation Overview</h4>
				<div id="villageORWardWiseOverviewId"  style="margin-top: 15px"></div>
        </div>
    </div>
</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>

<script>
	$('document').ready(function(){
	getNominatdPostsOverview("stateWiseOverviewId",2);
	getNominatdPostsOverview("districtWiseOverviewId",3);
	getNominatdPostsOverview("assemblyWiseOverviewId",4);
	getNominatdPostsOverview("mandalORMunciWiseOverviewId",5);
	getNominatdPostsOverview("villageORWardWiseOverviewId",7);
		
	});
	function getNominatdPostsOverview(divId,levelId){
		
		$('#'+divId+'').html('<img id="dataLoadingsImgForImagePath" src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
		var jsObj =
		{
			levelId:levelId,
			startDateStr:"",
			endDateStr:""
		};
		$.ajax({
				type : "POST",
				url : "getNominatdPostsOverviewAction.action",
				data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
				buildStatusDetails(result,levelId,divId);
		});  
	}
	
	function buildStatusDetails(result,levelId,divId){
		var str="";
		if(result != null && result.length>0){
			str+='<ul class="panelBlockCustom">';
			for(var i in result){
				str+='<li style="font-size:12px">';
					str+='<div class="panel panel-default panelCustom">';
					str+='<div class="panel-heading">';
					if(i>0)
						str+='<h3>'+result[i].totalCorp+' <span class="pull-right text-muted">'+result[i].perc+'%</span> </h3>';
					else
						str+='<h3>'+result[i].totalCorp+' </h3>';
					str+='<p>'+result[i].name+'</p>';
					str+='</div>';
					str+='<div class="panel-body">';
					if(i>0)
						str+='<p>'+result[i].totalPositions+' Positions <span class="pull-right text-danger">'+result[i].perc1+'%</span></p>';
					else 
						str+='<p>'+result[i].totalPositions+' Positions</p>';
					
					str+='</div>';
					str+='<div class="panel-footer">';
					str+='<p>'+result[i].totalDept+' Departments</p>';
					str+='</div>';
					str+='</div>';
				str+='</li>';
				
			}
				
			str+='</ul>';
		}
		else{
			str="No data available.";
		}
		$('#'+divId+'').html(str);
	}
</script>
</body>
</html>
