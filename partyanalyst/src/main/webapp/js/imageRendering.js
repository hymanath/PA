var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
if(wurl.length == 3)
  wurl = url.substr(0,(url.indexOf(".in")+3));
var glStartDate = moment().format("YYYY-MM-DD");
var glEndDate = moment().format("YYYY-MM-DD");
var isDepartment="N";
var newsPaper=[];
var editionType=[];
var districts = [];
var constituencies=[];
var departments=[];
var departmentNames=[];
var newspaperNames=[];
var editionTypes=[];
var constituencyNames=[];
var districtNames=[];
$(".chosen-select").chosen();

var width = 509
var height = 449
var expanded = false;
var districts_selected = [];
var globalNewsPapres=[];
var globalDistricts=[];
var globalEditionType=["District","Main","Online"];
var globalDepartMentNames=[];
document.getElementById("svg").setAttribute("width", width)
document.getElementById("svg").setAttribute("height", height)

$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});

$("#startDate").daterangepicker({
		startDate: glStartDate,
        singleDatePicker: true,
		locale: {
		  format: 'YYYY-MM-DD'
		},
		
	});
$("#endDate").daterangepicker({
	startDate: glEndDate,
        singleDatePicker: true,
		locale: {
		  format: 'YYYY-MM-DD'
		},
		
	})
	
$('#startDate').on('apply.daterangepicker', function(ev, picker) {
	
	glStartDate = picker.endDate.format('YYYY-MM-DD');
});
$('#endDate').on('apply.daterangepicker', function(ev, picker) {
	
	glEndDate = picker.endDate.format('YYYY-MM-DD');
});
onLoadCalls();
function onLoadCalls(){
	populateNewspapers('onload');
	getAllDepartments(isDepartment,'onload');
	getAllLocations(2,'1','onload');
	$('#editionType').multiselect("destroy");
	$('#editionType').multiselect({
		enableFiltering: true,
		includeSelectAllOption: true,
		selectAllText: 'All Editions',
		maxHeight: 300,
		buttonWidth: '100%',
		dropDown: true,
		selectAllName: true,
		allSelectedText: 'All Editions selected'
	});
	$('#wordCloudConstituency').multiselect("destroy");
	$('#wordCloudConstituency').multiselect({
		enableFiltering: true,
		includeSelectAllOption: true,
		//selectAllText: 'All',
		maxHeight: 300,
		buttonWidth: '100%',
		dropDown: true,
		selectAllName: true,
		allSelectedText: 'All selected'
	});
	$('#newspapers').multiselect("destroy");
	$('#newspapers').multiselect({
		enableFiltering: true,
		includeSelectAllOption: true,
		selectAllText: 'All',
		maxHeight: 300,
		buttonWidth: '100%',
		dropDown: true,
		selectAllName: true,
		allSelectedText: 'All selected'
	});
	$('#wordCloudDepartmentNames').multiselect("destroy");
	$('#wordCloudDepartmentNames').multiselect({
		enableFiltering: true,
		includeSelectAllOption: true,
		selectAllText: 'All',
		maxHeight: 300,
		buttonWidth: '100%',
		dropDown: true,
		selectAllName: true,
		allSelectedText: 'All selected'
	});
}
function drawWordCloud(data) {
	// let word_count = {"à°•à°¾à°°à±à°¤à±‹": 4};
	let w_c = {}
	_.forEach(data, (value, key) => {
		// console.log(key, value)
		w_c[key] = value.count
	})
	var svg_location = "#svg";
	var fill = d3.scale.category20();
	var word_entries = d3.entries(w_c);


	var xScale = d3.scale.linear()
		.domain([0, d3.max(word_entries, function (d) {
			return d.value;
		})
		])
		.range([10, 100]);

	d3.layout.cloud().size([width, height])
		.timeInterval(20)
		.words(word_entries)
		.fontSize(function (d) { return xScale(+d.value); })
		.text(function (d) { return d.key; })
		.rotate(0)
		.spiral("archimedean")
		.font("Impact")
		.on("end", draw)
		.start();

	function draw(words) {
		d3.select(svg_location)
			.append("g")
			.attr("transform", "translate(" + [width >> 1, height >> 1] + ")")
			.selectAll("text")
			.data(words)
			.enter().append("text")
			.style("font-size", function (d) { return xScale(d.value) + "px"; })
			.style("font-family", "Impact")
			.style("fill", function (d, i) {
				const obj = _.find(data, (value, key) => {
					return key === d.key;
				});
				switch (obj.sentiment.toLowerCase()) {
					case "positive": return "green";
					case "negative": return "red";
					case "critical": return "purple";
					default: return "grey";
				}
			})
			.attr("text-anchor", "middle")
			.attr("transform", function (d) {
				return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
			})
			.text(function (d) { return d.key; });
		var el = document.getElementById('svg');
		el.addEventListener('click', (evt) => {
			//console.log("clientY:"+evt.clientY+"-----evt.clientX:"+evt.clientX)
			let elem = document.getElementsByClassName("headline-div")[0]
			elem.style.display = "block";
			//elem.style.top = evt.clientY + "px";
			//elem.style.left = evt.clientX + "px";
			const obj = _.find(data, (value, key) => {
				return evt.target.innerHTML === key
			})
			let article_data = ''
			_.forEach(obj.titles, (val, index) => {
				switch (obj.article_senti[index]) {
					case 1: article_data = article_data + "<li class='list-group-item green' data-article-id='"
						+ obj.articles[index] + "' onclick = getArticleId('" + obj.articles[index] + "')>" + val + "</li>"
						break;
					case -1: article_data = article_data + "<li class='list-group-item red' data-article-id='"
						+ obj.articles[index] + "' onclick = getArticleId('" + obj.articles[index] + "')>" + val + "</li>"
						break;
					default: article_data = article_data + "<li class='list-group-item grey' data-article-id='"
						+ obj.articles[index] + "' onclick = getArticleId('" + obj.articles[index] + "')>" + val + "</li>"
				}
			})
			document.getElementsByClassName("headline-div-content")[0].innerHTML = article_data;
		})
	}
	d3.layout.cloud().stop();
	document.getElementsByClassName("data-processing-alert")[0].style.display = "none";
	document.getElementsByClassName("btn-primary")[0].disabled = "";
}

function getArticleId(id) {
	getOverAllDetailsOfAnArticle(id);
	$("#myModalShowNews").modal('show');
}   

function closeDiv() {
    document.getElementsByClassName("headline-div")[0].style.display = "none";
}

function fetchDataForWordCloud(type) {
	if(districtNames.length == 0){
		districtNames=globalDistricts;
	}
	if(newspaperNames.length ==0){
		newspaperNames=globalNewsPapres;
	}	
	if(editionTypes.length == 0){
		editionTypes=globalEditionType;
	}
	if(departmentNames.length == 0){
		departmentNames=[];
	}	
	$("#svg").html("");
	   document.getElementsByClassName("btn-primary")[0].disabled = "true";
		document.getElementsByClassName("headline-div")[0].style.display = "none";
		document.getElementsByClassName("headline-div-content")[0].innerHTML = '';

	if(type =="fromPage"){
		districts= $.unique(districts);
		var requestObj = { "Constituency": constituencies, "News_Paper": newspaperNames, "Distrtict": districtNames,"Edition_Type":editionTypes, "start_date": glStartDate, "end_date": glEndDate,
		"is_department":isDepartment,"department_name":departmentNames};
	}else{
		var requestObj = { "Constituency": constituencies,"News_Paper": globalNewsPapres, "Distrtict": globalDistricts, "Edition_Type":globalEditionType,"start_date": glStartDate, "end_date": glEndDate,
		"is_department":isDepartment,"department_name":departmentNames};

	}
		console.log(requestObj)
		document.getElementsByClassName("data-sent-alert")[0].style.display = "block";
		const url = 'http://139.59.3.60:8000/wordcloud/'
		//const url = 'http://139.59.3.60:9000/test/'
		fetch(url, { method: 'post', body: JSON.stringify(requestObj) })
			.then((res) => {
				//console.log(res)
				document.getElementsByClassName("data-sent-alert")[0].style.display = "none";
				document.getElementsByClassName("data-processing-alert")[0].style.display = "block";
				return res.json();
			})
			.then(res => {
				//console.log(res)
				if (_.isEmpty(res.received_data)) {
					document.getElementsByClassName("data-processing-alert")[0].style.display = "none";
					document.getElementsByClassName("empty-alert")[0].style.display = "block";
					setTimeout(() => {
						document.getElementsByClassName("empty-alert")[0].style.display = "none";
						document.getElementsByClassName("btn-primary")[0].disabled = "";
					}, 2000)
				} else {
					drawWordCloud(res.received_data);
				}
			})
			.catch(function (error) {
			   // console.log(error);
				document.getElementsByClassName("data-processing-alert")[0].style.display = "none";
				document.getElementsByClassName("data-sent-alert")[0].style.display = "none";
				document.getElementsByClassName("error-alert")[0].style.display = "block";
				setTimeout(() => {
					document.getElementsByClassName("error-alert")[0].style.display = "none";
					document.getElementsByClassName("btn-primary")[0].disabled = "";
				}, 2000);
			});
}
function getOverAllDetailsOfAnArticle(articleId){
	$("#myModalShowNews").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
		//url: "http://mytdp.com/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
		
	}).then(function(results){
		var obj = ["","State","District","Constituency","Parliament","Mandal","Panchayat","Village","CORP-GMC","Ward","NATIONAL","INTERNATIONAL","MUNICIPALITY"];
			var result = results[0];
			var str = '';
				str+='<div class="modal-dialog modal-lg" role="document">';
				str+='<div class="modal-content">';
				str+='<div class="modal-header">';
				str+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
				str+='<h4 class="modal-title" id="myModalLabel">';
				str+='<p class="m_bottom0" style="height:40px;" id="mdlArtclTtl">'+result.articleTitle+'</p>';
				str+='<p class="m_bottom0 text-italic font-16" id="mdlArtclDesc"><i>Edition Source :'+result.editionSource+' ['+result.articleInsertedTime+' ]</i></p>';
				str+='</h4>';
				str+='</div>';
				str+='<div class="modal-body">';
				str+='<div class="row">';
				str+='<div class="col-md-12">';
				str+='<img class="mainImage"  src="http://mytdp.com/NewsReaderImages/'+result.imageURL+'" style="display:block;margin:auto;width:100%;" alt="Img Title"/>';
				str+='</div>';
				str+='<div class="col-md-12 m_top10">';
				str+='<h4 class="panel-title text-success">Description</h4>';
				str+='<p class="m_0 f_14">'+result.description+'</p>';
				str+='</div>';
				str+='<div class="col-md-12">';
				if( result.subList != null && result.subList.length > 0){
					for(var i in result.subList){
						/* Candidate*/
						str+='<div class="row ">';
						str+='<div class="col-md-6">';
						str+='<div class="panel panel-default panelArticleGroup">';
						str+='<div class="panel-heading">';
						str+='<h4 class="panel-title">FROM WHOM</h4>';
						str+='</div>';
						str+='<div class="panel-body">';
							/* From Table*/
							if(result.subList[i].fromList != null && result.subList[i].fromList.length > 0){
								for( var j in result.subList[i].fromList){
									str+='<table class="table table-bordered m_top10">';
									str+='<tr>';
									if( result.subList[i].fromList[j].organizationName != null && $.trim(result.subList[i].fromList[j].organizationName).length > 0 ){
										str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].fromList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].fromList[j].organizationName+'</td>';
									}
									str+='<td><img class="img-circle" src=""Assests/images/'+result.subList[i].fromList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].fromList[j].benefit+'</td>';
									str+='</tr>';
									str+='<tr>';
									str+='<td colspan="2">';
									var candidataExist = false;
									if( result.subList[i].fromList[j].candidateName != null && $.trim(result.subList[i].fromList[j].candidateName).length > 0 ){
										candidataExist = true; 
										str+=''+result.subList[i].fromList[j].candidateName;
									}
									if( result.subList[i].fromList[j].designation != null && $.trim(result.subList[i].fromList[j].designation).length > 0 ){
										candidataExist = true; 
										str+=' ('+result.subList[i].fromList[j].designation + ")";
									}
									if(!candidataExist){
										str+=' - ';
									}
									str+='</td>';
									str+='</tr>';
									str+='<tr>';
									str+='<td colspan="2">';
									if(result.subList[i].fromList[j].impactLevel != null && $.trim(result.subList[i].fromList[j].impactLevel).length > 0){
										str+='<p class="m_0">Impact Level : '+result.subList[i].fromList[j].impactLevel+'</p>';	
									}else{ 
										str+='<p class="m_0">Impact Level : - </p>';	
									}
									if(result.subList[i].fromList[j].categories != null && $.trim(result.subList[i].fromList[j].categories).length > 0){
										str+='<p class="m_0">Category : '+result.subList[i].fromList[j].categories+'</p>';	
									}else{ 
										str+='<p class="m_0">Category : - </p>';	
									}
									if(result.subList[i].fromList[j].newsActivity != null && $.trim(result.subList[i].fromList[j].newsActivity).length > 0){
										str+='<p class="m_0">News Activity : '+result.subList[i].fromList[j].newsActivity+' </p>';
									}else{ 
										str+='<p class="m_0">News Activity : - </p>';	
									}
									if(result.subList[i].fromList[j].newsType != null && $.trim(result.subList[i].fromList[j].newsType).length > 0){
										str+='<p class="m_0">News type : '+result.subList[i].fromList[j].newsType+' </p>';
									}else{ 
										str+='<p class="m_0">News type : - </p>';	
									}
									if( result.subList[i].fromList[j].newsType != null && result.subList[i].fromList[j].newsType == "Problems"){
										if(result.subList[i].fromList[j].newsRelated != null && $.trim(result.subList[i].fromList[j].newsRelated).length > 0){
											str+='<p class="m_0">News Related : '+result.subList[i].fromList[j].newsRelated+' </p>';
										}else{ 
											str+='<p class="m_0">News Related : - </p>';	
										}
										if(result.subList[i].fromList[j].priority != null && $.trim(result.subList[i].fromList[j].priority).length > 0){
											str+='<p class="m_0">Priority : '+result.subList[i].fromList[j].priority+' </p>';
										}else{ 
											str+='<p class="m_0">Priority : - </p>';	
										}
										if(result.subList[i].fromList[j].solution != null && $.trim(result.subList[i].fromList[j].solution).length > 0){
											str+='<p class="m_0">Solution : '+result.subList[i].fromList[j].solution+' </p>';
										}else{ 
											str+='<p class="m_0">Solution : - </p>';	
										}
									}
									str+='</td>';
									str+='</tr>';
									str+='</table>';
								}
							}
						str+='</div>';//panel-body
						str+='</div>';//panel
						str+='</div>';//colmd6
						str+='<div class="col-md-6">';
						str+='<div class="panel panel-default panelArticleGroup">';
						str+='<div class="panel-heading">';
						str+='<h4 class="panel-title">TO WHOM</h4>';
						str+='</div>';
						str+='<div class="panel-body">';
							/* TO Table*/
							if(result.subList[i].toList != null && result.subList[i].toList.length > 0){
								for( var j in result.subList[i].toList){
									str+='<table class="table table-bordered m_top10">';
									str+='<tr>';
									if( result.subList[i].toList[j].organizationName != null && $.trim(result.subList[i].toList[j].organizationName).length > 0 ){
										str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].toList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].toList[j].organizationName+'</td>';
									}else{
										str+='<td> - </td>';
									}
									str+='<td><img class="img-circle" src="Assests/images/'+result.subList[i].toList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].toList[j].benefit+'</td>';
									str+='</tr>';
									str+='<tr>';
									str+='<td colspan="2">';
									var candidataExist = false;
									if( result.subList[i].toList[j].candidateName != null && $.trim(result.subList[i].toList[j].candidateName).length > 0 ){
										candidataExist = true; 
										str+=''+result.subList[i].toList[j].candidateName;
																		}
																		if( result.subList[i].toList[j].designation != null && $.trim(result.subList[i].toList[j].designation).length > 0 ){
																			candidataExist = true; 
																			str+=' ('+result.subList[i].toList[j].designation + ")";
																		}
																		if(!candidataExist){
																			str+=' - ';
																		}
																	   str+='</td>';
																str+='</tr>';
																str+='<tr>';
																	str+='<td colspan="2">';
																		
																		if(result.subList[i].toList[j].impactLevel != null && $.trim(result.subList[i].toList[j].impactLevel).length > 0){
																		  str+='<p class="m_0">Impact Level : '+result.subList[i].toList[j].impactLevel+'</p>';	
																		}else{ 
																		  str+='<p class="m_0">Impact Level : - </p>';	
																		}
																	
																		if(result.subList[i].toList[j].categories != null && $.trim(result.subList[i].toList[j].categories).length > 0){
																		  str+='<p class="m_0">Category : '+result.subList[i].toList[j].categories+'</p>';	
																		}else{ 
																		  str+='<p class="m_0">Category : - </p>';	
																		}
																		if(result.subList[i].toList[j].newsActivity != null && $.trim(result.subList[i].toList[j].newsActivity).length > 0){
																		  str+='<p class="m_0">News Activity : '+result.subList[i].toList[j].newsActivity+' </p>';
																		}else{ 
																		  str+='<p class="m_0">News Activity : - </p>';	
																		}
																		if(result.subList[i].toList[j].newsType != null && $.trim(result.subList[i].toList[j].newsType).length > 0){
																		  str+='<p class="m_0">News type : '+result.subList[i].toList[j].newsType+' </p>';
																		}else{ 
																		  str+='<p class="m_0">News type : - </p>';	
																		}
																		if( result.subList[i].toList[j].newsType != null && result.subList[i].toList[j].newsType == "Problems"){
																			
																			if(result.subList[i].toList[j].newsRelated != null && $.trim(result.subList[i].toList[j].newsRelated).length > 0){
																			  str+='<p class="m_0">News Related : '+result.subList[i].toList[j].newsRelated+' </p>';
																			}else{ 
																			  str+='<p class="m_0">News Related : - </p>';	
																			}
																			if(result.subList[i].toList[j].priority != null && $.trim(result.subList[i].toList[j].priority).length > 0){
																			  str+='<p class="m_0">Priority : '+result.subList[i].toList[j].priority+' </p>';
																			}else{ 
																			  str+='<p class="m_0">Priority : - </p>';	
																			}
																			if(result.subList[i].toList[j].solution != null && $.trim(result.subList[i].toList[j].solution).length > 0){
																			  str+='<p class="m_0">Solution : '+result.subList[i].toList[j].solution+' </p>';
																			}else{ 
																			  str+='<p class="m_0">Solution : - </p>';	
																			}
																		}
																	str+='</td>';
																str+='</tr>';
															str+='</table>';
														}
													}
													
												str+='</div>';//panelbody
											str+='</div>';//panel
										str+='</div>';//colmd6
										
									str+='</div>';//row
							  }
							}
							
							str+='</div>';//colmd12
						str+='</div>';//row
						
						/* Tracking*/
						str+='<div class="row">';
							str+='<div class="col-md-6 m_top10">';
								str+='<div class="panel panel-default panelArticleGroup">';
								if(result.trackLocationScope!=null && $.trim(result.trackLocationScope.length > 0)){
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">Article Tracking &nbsp;&nbsp;&nbsp<i class="glyphicon glyphicon-ok text-success"></i></h4>';
									str+='</div>';
									str+='<div class="panel-body">';
										str+='<table class="table table-bordered">';
											str+='<tr>';
											   if(result.trackLocationScope!=null && $.trim(result.trackLocationScope.length > 0)){
												 str+='<td>Tracking Location Scope : '+result.trackLocationScope+'</td>';  
											   }else{
												  str+='<td>Tracking Location Scope : - </td>';   
											   }
											str+='</tr>';
											
											str+='<tr>';
											 if(result.trackLocationValue!=null && $.trim(result.trackLocationValue.length > 0)){
												 str+='<td>Tracking Location  : '+result.trackLocationValue+'</td>';  
											 }else{
												  str+='<td>Tracking Location : - </td>';   
											 }
											str+='</tr>';
											
											str+='<tr>';
											if(result.trackLabelName!=null && $.trim(result.trackLabelName.length > 0)){
												 str+='<td>Label Name  : '+result.trackLabelName+'</td>';  
											 }else{
												  str+='<td>Label Name : - </td>';   
											 }
											str+='</tr>';
										str+='</table>';
									str+='</div>';
								}else{
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">Article Tracking </h4>';
									str+='</div>';
									str+='<div class="panel-body">';
									str+='<h4 class="panel-title" style="text-align:center;">Tracking &nbsp;&nbsp;&nbsp<i class="glyphicon glyphicon-remove text-danger"></i></h4>';
									str+='</div>';
								}
									
								str+='</div>';
							str+='</div>';
					
						/* Characteristics */
					
							str+='<div class="col-md-6 m_top10">';
								str+='<div class="panel panel-default panelArticleGroup">';
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">Article Characteristics</h4>';
									str+='</div>';
									str+='<div class="panel-body">';
										str+='<table class="table table-condensed" style="border:1px solid #ddd;">';
											str+='<tr>';
											   if(result.important == 'Y'){
												str+='<td>Is Important : <i class="glyphicon glyphicon-ok text-success"></i></td>';   
											   }else{
												   str+='<td>Is Important :<i class="glyphicon glyphicon-remove text-danger"></i></td>';
											   }
											str+='</tr>';
											str+='<tr>';
											   if(result.actionable == 'Y'){
												   str+='<td>Actionable : <i class="glyphicon glyphicon-ok text-success"></i></td>';   
												}else{
												   str+='<td>Actionable :<i class="glyphicon glyphicon-remove text-danger"></i></td>';
												}
											str+='</tr>';
											str+='<tr>';
												if(result.newsBulliten == 'Y'){
												   str+='<td>News Bulletin : <i class="glyphicon glyphicon-ok text-success"></i></td>';   
												}else{
												   str+='<td>News Bulletin :<i class="glyphicon glyphicon-remove text-danger"></i></td>';
												}
											str+='</tr>';
										str+='</table>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						
						
						str+='<div class="row">';
							/* NewsType */
							str+='<div class="col-md-6">';
								str+='<div class="panel panel-default panelArticleGroup">';
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">NEWS TYPE</h4>';
									str+='</div>';
									str+='<div class="panel-body">';
										str+='<table class="table table-condensed">';
											str+='<tr>';
												str+='<td>Published Article</td>';
												if(result.publishedArticle!=null){
												   str+='<td>'+result.publishedArticle+'</td>';
												}else{
													str+='<td> - </td>';
												}
											str+='</tr>';
											str+='<tr>';
												str+='<td>Article Nature</td>';
												if(result.articleNature != null){
												  str+='<td>'+result.articleNature+'</td>';	
												}else{
													str+='<td> - </td>';	
												}
											str+='</tr>';
										str+='</table>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							
							/* Article Scope Location */
							str+='<div class="col-md-6">';
								str+='<div class="panel panel-default panelArticleGroup">';
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">LOCATION DETAILS</h4>';
									str+='</div>';
									str+='<div class="panel-body">';
										str+='<table class="table table-condensed">';
											str+='<tr>';
												str+='<td>Impact Scope</td>';
												if(result.impactScopeId!=null){
													str+='<td>'+obj[result.impactScopeId]+'</td>';
												}else{
													str+='<td> - </td>';
												}
											str+='</tr>';
											str+='<tr>';
												str+='<td>Location</td>';
												if(result.scopeLocation!=null){
													str+='<td>'+result.scopeLocation+'</td>';
												}else{
													str+='<td> - </td>';
												}
											str+='</tr>';
										str+='</table>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						
						str+='<div class="row">';
							 /*Lnking*/
							str+='<div class="col-md-6">';
								str+='<div class="panel panel-default panelArticleGroup">';
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">LINKED ARTICLES</h4>';
									str+='</div>';
									str+='<div class="panel-body">';
										 if( result.linkedList != null && result.linkedList.length > 1){
										str+='<div class="row">';
											for( var i in result.linkedList){
												if(result.linkedList[i].articleId !=articleId ){
													str+='<div class="col-md-4" style="margin-top:5px;">';
														str+='<img  class="thumbnail img-responsive linkedArticlesClickId" src="http://mytdp.com/NewsReaderImages/'+result.linkedList[i].imageURL+'" style="display:block;margin:auto;height:90px;"/>';
													str+='</div>';
												}
											}
										str+='</div>';
										}else{
											str+="<h5> No Linked Articles Available </h5>";
										}
										
									str+='</div>';
								str+='</div>';
							str+='</div>';
							/*Grouping*/
							str+='<div class="col-md-6">';
								str+='<div class="panel panel-default panelArticleGroup">';
									str+='<div class="panel-heading">';
										str+='<h4 class="panel-title">GROUPED ARTICLES</h4>';
									str+='</div>';
									str+='<div class="panel-body">';
										if( result.groupedArticlesList != null && result.groupedArticlesList.length > 1){
											if( result.groupedArticlesList.length > 6){
												str+='<div class="row" style="height:200px;overflow-y:scroll">';
											}else{
												str+='<div class="row">';
											}
											for( var i in result.groupedArticlesList){
												if(result.groupedArticlesList[i].articleId !=articleId ){
													str+='<div class="col-md-4 m_top10">';
														str+='<img class="img-responsive thumbnail groupedArticleId" attr_articleId='+result.groupedArticlesList[i].articleId+' src="http://mytdp.com/NewsReaderImages/'+result.groupedArticlesList[i].imageURL+'" style="display:block;margin:auto;height:90px;"/>';
													str+='</div>';
												}
											}
										str+='</div>';
										}else{
											str+="<h5> No Grouped Articles Available </h5>";
										}
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						
					  str+='</div>';
					str+='</div>';
				 str+='</div>';
				 
				$("#myModalShowNews").html(str);
				
			
	});
}
  
function getAllLocations(levelId,levelValue,type){
	if(levelId==2){
			$('#wordCloudDistrict').html("ALL districts");
			$('#wordCloudConstituency').html("ALL constituencies");
		var jsObj={
				"stateId":levelValue
			}
		$.ajax({
			type : "GET",
			url : "getAllDistrictsForLoationDashBoardAction.action",
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			return buildResultforWordCloud('wordCloudDistrict',result,type);
		});
		
	}else if(levelId==3){
		$('#wordCloudConstituency').html("ALL constituencies");
		var jsObj={
				"districtId":levelValue
			}
		$.ajax({
			type : "GET",
			url : "getConstituenciesByDistrictForWordCloudAction.action",
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			return buildResultforWordCloud('wordCloudConstituency',result);
		});
	}
	
}

function buildResultforWordCloud(levelTypeId,result,type,isDepartment){
	var optionStr='';
		
	if(levelTypeId =="newspapers"){
		globalNewsPapres=[];
		for(var i in result){
			optionStr+='<option selected>'+result[i].paperName+'</option>';
			if(result[i].paperId !=8){
				globalNewsPapres.push(result[i].paperName);
			}
		}
	}else if(levelTypeId =="wordCloudDepartmentNames"){
		globalDepartMentNames=[];
		document.getElementsByClassName("btn-primary")[0].disabled = "";
		if(isDepartment == "N"){
			$("#typeId").html("Party Names");
			for(var i in result){		
				optionStr+='<option selected>'+result[i].shortName+'</option>';
				globalDepartMentNames.push(result[i].shortName);
			}
		}else{
			$("#typeId").html("Department Names");
			for(var i in result){		
				optionStr+='<option selected>'+result[i].shortName+'</option>';
				globalDepartMentNames.push(result[i].shortName);
			}
		}
		
	}else {
		globalDistricts=[];
		for(var i in result){
			optionStr+='<option selected>'+result[i].locationName+'</option>';
			if(levelTypeId=="wordCloudDistrict"){
				globalDistricts.push(result[i].locationName);
			}
		}
	}
	if(levelTypeId=="wordCloudDistrict"){
		$('#'+levelTypeId).html(optionStr);
	}else{
		$('#'+levelTypeId).html(optionStr);
	}
	$('#'+levelTypeId).multiselect("destroy");
	if(levelTypeId == "wordCloudDistrict"){
		$('#'+levelTypeId).multiselect({
		enableFiltering: true,
		includeSelectAllOption: true,
		selectAllText: 'All Districts',
		maxHeight: 300,
		buttonWidth: '100%',
		dropDown: true,
		selectAllName: true,
		allSelectedText: 'All Districts selected',
		onChange: function() {
			districtNames = $('#wordCloudDistrict').val();
			getAllLocations(3,$('#wordCloudDistrict').val(),"onChange");
		}
	});
	}else if(levelTypeId == "newspapers"){
		$('#'+levelTypeId).multiselect({
		enableFiltering: true,
		includeSelectAllOption: true,
		selectAllText: 'All News Paper',
		maxHeight: 300,
		buttonWidth: '100%',
		dropDown: true,
		selectAllName: true,
		allSelectedText: 'All News Paper selected',
		onChange: function() {
			newspaperNames=$('#newspapers').val();
		}
	});
	}else if(levelTypeId == "wordCloudConstituency"){
		$('#'+levelTypeId).multiselect({
		enableFiltering: true,
		includeSelectAllOption: true,
		selectAllText: 'All Constituencies',
		maxHeight: 300,
		buttonWidth: '100%',
		dropDown: true,
		selectAllName: true,
		allSelectedText: 'All Constituencies selected',
		onChange: function() {
			constituencies=$('#wordCloudConstituency').val();
			}
	});
	}else if(levelTypeId == "wordCloudDepartmentNames"){
		var name="";
		if(isDepartment == "N"){
			name="All Parties"
		}else{
			name="All Departments"
		}
		$('#'+levelTypeId).multiselect({
		enableFiltering: true,
		includeSelectAllOption: true,
		selectAllText: name,
		maxHeight: 300,
		buttonWidth: '100%',
		dropDown: true,
		selectAllName: true,
		allSelectedText: name+' selected',
		onChange: function() {
			departmentNames=$('#wordCloudDepartmentNames').val();
		}
	});
	}
	
	callfetchFunction(type);
}
function callfetchFunction(type){
	//document.getElementsByClassName("data-sent-alert")[0].style.display = "block";
	if(type !='onchange' && type !==undefined && type !== 'undefined'){
		if(globalDistricts != null && globalDistricts.length>0 && globalNewsPapres !=null && globalNewsPapres.length>0 && globalDepartMentNames !=null && globalDepartMentNames.length>0){
			fetchDataForWordCloud("") ;
		}else{
			document.getElementsByClassName("data-sent-alert")[0].style.display = "";
		}
	}
}
function populateNewspapers(type){
	$.ajax({
		type : 'GET', 
		crossOrigin: true,
		url: wurl+"/CommunityNewsPortal/webservice/getAllNewsPapers/AP"
       //url: "http://mytdp.com/CommunityNewsPortal/webservice/getAllNewsPapers/AP"
	  
    }).then(function(result){
	return buildResultforWordCloud('newspapers',result,type);
      
    });    
}

function getAllDepartments(isDepartment,type){
	$.ajax({
		type : 'GET', 
		url: wurl+"/CommunityNewsPortal/webservice/getAllDepartMentNames/"+isDepartment
      //url: "http://mytdp.com/CommunityNewsPortal/webservice/getAllDepartMentNames/"+isDepartment
    }).then(function(result){
	return buildResultforWordCloud('wordCloudDepartmentNames',result,type,isDepartment);
      
    });    
}

 function refreshWordCloudfunction(){
	$(".alertCategoryWiseCls li").removeClass("active");
	$(".alertCategoryWiseCls li:nth-child(1)").addClass("active");
	document.getElementsByClassName("data-sent-alert")[0].style.display = "block";
	getAllLocations(2,'1','onchange');
	populateNewspapers('onchange');
	getAllDepartments("N",'onload');
}

 $(document).on("click",".alertCategoryWiseCls li",function(){
		$(this).parent("ul").find("li").removeClass("active");
		$(this).addClass("active");
		isDepartment = $(this).attr("attr_type");
		$("#typeId").html('<div class="row"><div class="col-sm-12"><img src="D2D_Assests/images/spinner.gif" style="width:20px;height:20px;"/></div></div>');
	$("#wordCloudDepartmentNames").html("");
	getAllDepartments(isDepartment,'onchange');
	document.getElementsByClassName("btn-primary")[0].disabled = "true";
	document.getElementsByClassName("newsLettersRefresh")[0].disabled = "true";
});