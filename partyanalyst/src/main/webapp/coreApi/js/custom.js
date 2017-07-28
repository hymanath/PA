/*

1.Global CLick Actions
	1.1 Collapse Table Click
	1.2 SubString  // spliting the length of the word or characters
	1.3 minimise // full text will be sliced and on hover it will be shown
	1.4 Right Navbar Click
2.Highcharts

*/



globalFunction();
function globalFunction()
{
	'use strict';
	collapseClick();
	responsiveTabs();
	/* casteWiseVoters();
	casteGroupVoters();
	cadreInfoGraph();
	cadreInfoGraphBar();
	grievanceDetails();
	totalApplications();
	nominatedPosts();
	overallAlerts();
	benefitsGraph();
	problemsDetailedGraph();
	committees();
	problemsSolveGraph(); */
	minimise('.right-nav-list li',5);
	rightNav();
	
	$(document).on("click",".switch-btn li",function(){
		$(this).parent("ul").find("li").removeClass("active");
		$(this).addClass("active");
	});
	$(window,document).on('resize', function(){
		responsiveTabs();
	});
}
function responsiveTabs()
{
	var $this = $(this);
	var $windowWidth = $(window).width();
	if($windowWidth < 768)
	{
		$('[role="tabListMobile"]').show();
		$('[role="tablist"]').hide();
	}else{
		$('[role="tabListMobile"]').hide();
		$('[role="tablist"]').show();
	}
	
	$(document).on("change","[role='tabListMobile']",function(){
		var id = $('option:selected', this).attr('tab_id');
		$("#"+id).closest(".tab-content").find("[role='tabpanel']").removeClass("active");
		$("#"+id).addClass("active");
	});
}
function subStr(name,noOfChar){
	ellipsetext=".."
	var showChar = noOfChar;
	var content = name;
	if(content != null){
		if(content.length > showChar) {
			var c = content.substr(0, showChar);
			var html = c + ellipsetext;
			return html;
		}
	}
	return name;
}
function minimise(Id,count)
{
	var id = Id;
	var minimized_elements = $(id);
	minimized_elements.each(function(){    
		var t = $(this).text();        
		if(t.length < count) return;

		$(this).html(
			'<span class="less">'+t.slice(0,count)+'..</span>'+
			'<span style="display:none;" class="more text-capitalize">'+t+'</span>'
		);

	}); 
	
	$(document).on("mouseover",id,function(){
		$(this).find('span').hide();
		$(this).find('span.more').show();
	});
	$(document).on("mouseout",id,function(){
		$(this).find('span.less').show();
		$(this).find('span.more').hide();
	});
	
}
function rightNav()
{
	var indexValue = '';
	var navBar = $(".right-nav-list li");
	
	navBar.click(function(){
		navBar.removeClass("active");
		$(this).addClass("active");
		indexValue = $(this).attr("index");
		setTimeout(function(){
			$('html,body').animate({
				scrollTop: $('[navbar-index='+indexValue+']').offset().top},
			'slow');
		},300);
	});
	//navbar-index="16"
}
function collapseClick()
{
	'use strict';
	var expandId = '';
	$(document).on("click","[collapseid]",function(){
		var $this = $(this);
		expandId = $this.attr("collapseid");
		if($this.hasClass('glyphicon-minus'))
		{
			$this.removeClass('glyphicon-minus').addClass('glyphicon-plus');
			$('[collapseBodyId='+expandId+']').hide();
		}else{
			$this.removeClass('glyphicon-plus').addClass('glyphicon-minus');
			$('[collapseBodyId='+expandId+']').show();
		}
	});
	$(document).on("click",".td-expand-hide",function(){
		$('[collapseBodyId='+expandId+']').hide();
		$('[collapseid='+expandId+']').removeClass('glyphicon-minus').addClass('glyphicon-plus');
	});
}


function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions)
{
	'use strict';
	$('#'+id).highcharts({
		chart: type,
		title: {
			text: null
		},
		subtitle: {
			text: null
		},
		xAxis: xAxis,
		yAxis: yAxis,
		tooltip: {
			enabled:true,
			pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.2f}%</b><br/>'
		},
		plotOptions: plotOptions,
		legend: legend,
		series: data
	});
}
function highChartsDonut(id,data,legend)
{
	$('#'+id).highcharts({
		chart: {
            type: 'pie',
			backgroundColor: 'transparent',
            options3d: {
                enabled: false,
                alpha: 45
            }
        },	
		title: {
			text: ''
		},
		subtitle: {
			text: ''
		},
		plotOptions: {	
			pie: {
                innerSize: 40,
                depth: 40,
				showInLegend: legend,
				dataLabels: {
                    enabled: false,
				}
            }, 
		},
		legend: {
			itemStyle: {
				fontWeight: 'normal',
				'font-family':'roboto',
				'font-size':'13px'
            },
			enabled: true,
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
			useHTML: true,
			
			labelFormatter: function() {
				return this.name + ' ' + this.y + '';
			}
		},
		series:[{
            name : 'Count',
            data: data
        }]
	});
}
function casteWiseVoters()
{
	var id = 'casteWiseVoters';
	var type = 'bar';
	var depth = 0;
	var innerSize = 0;
	var data = [
				['Bananas', 8],
				['Kiwi', 3],
				['Mixed nuts', 1],
				['Oranges', 6],
				['Apples', 8],
				['Pears', 4],
				['Clementines', 4],
				['Reddish (bag)', 1],
				['Grapes (bunch)', 1]
			];
	$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	highcharts(id,type,data,false,'');
}
function casteGroupVoters()
{
	var id = 'casteGroupVoters';
	var type = 'pie';
	var depth = 0;
	var innerSize = 0;
	var data = [
				{name:'Bananas', y:8},
				{name:'Kiwi', y:3},
				{name:'Mixed nuts',y: 1},
				{name:'Oranges',y: 6},
				{name:'Apples',y: 8},
				{name:'Pears',y: 4},
				{name:'Clementines',y: 4},
				{name:'Reddish (bag)',y: 1},
				{name:'Grapes (bunch)',y: 1}
			];
	$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	highcharts(id,type,data,true,'');
}
function cadreInfoGraph()
{
	
	var data = [];
	var depth = 60;
	$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var id  = '';
	for(var i = 0;i<2;i++)
	{
		data = [
				{name:'Bananas', y:8},
				{name:'Kiwi', y:3},
				{name:'Mixed nuts',y: 1},
			];
		id = 'cadreInfoGraph'+i;
		highChartsDonut(id,data,false)
	}
}
function cadreInfoGraphBar()
{
	var id = 'cadreInfoGraphBar';
	var type = 'column';
	var depth = 0;
	var innerSize = 0;
	var data = [
				{name:'Bananas', y:8},
				{name:'Kiwi', y:3},
				{name:'Mixed nuts',y: 1},
				{name:'Oranges',y: 6},
				{name:'Apples',y: 8},
				{name:'Pears',y: 4},
				{name:'Clementines',y: 4},
				{name:'Reddish (bag)',y: 1},
				{name:'Grapes (bunch)',y: 1}
			];
	$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	highcharts(id,type,data,true,'');
	
}
function grievanceDetails()
{
	var data = [];
	var depth = 60;
	
	var id  = '';
	for(var i = 0;i<3;i++)
	{
		data = [
				{name:'Bananas', y:8},
				{name:'Kiwi', y:3},
				{name:'Mixed nuts',y: 1},
			];
		id = 'grievanceDetails'+i;
		$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		highChartsDonut(id,data,true)
	}
}
function totalApplications()
{
	var id = 'applications';
	var type = 'pie';
	var depth = 0;
	var innerSize = 0;
	var data = [
				{name:'Bananas', y:8},
				{name:'Kiwi', y:3},
				{name:'Mixed nuts',y: 1},
				{name:'Oranges',y: 6},
				{name:'Apples',y: 8},
				{name:'Pears',y: 4},
				{name:'Clementines',y: 4},
				{name:'Reddish (bag)',y: 1},
				{name:'Grapes (bunch)',y: 1}
			];
	$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	highChartsDonut(id,data,false)
}
function nominatedPosts()
{
	var id = 'posts';
	var type = 'column';
	var depth = 0;
	var innerSize = 0;
	var data = [
				['Bananas', 8],
				['Kiwi', 3],
				['Mixed nuts', 1],
				['Oranges', 6],
				['Apples', 8],
				['Pears', 4],
				['Clementines', 4],
				['Reddish (bag)', 1],
				['Grapes (bunch)', 1]
			];
	$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	highcharts(id,type,data,false,'');	
}
function overallAlerts()
{
	var id = 'overallAlerts';
	var type = 'pie';
	var depth = 0;
	var innerSize = 0;
	var data = [
				{name:'Bananas', y:8},
				{name:'Kiwi', y:3},
				{name:'Mixed nuts',y: 1},
				{name:'Oranges',y: 6},
				{name:'Apples',y: 8},
				{name:'Pears',y: 4},
				{name:'Clementines',y: 4},
				{name:'Reddish (bag)',y: 1},
				{name:'Grapes (bunch)',y: 1}
			];
	$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	highChartsDonut(id,data,false)
}
function benefitsGraph()
{
	var id = 'benefitsGraph';
	var type = 'pie';
	var depth = 0;
	var innerSize = 0;
	var data = [
				{name:'Bananas', y:8},
				{name:'Kiwi', y:3},
				{name:'Mixed nuts',y: 1},
				{name:'Oranges',y: 6},
				{name:'Apples',y: 8},
				{name:'Pears',y: 4},
				{name:'Clementines',y: 4},
				{name:'Reddish (bag)',y: 1},
				{name:'Grapes (bunch)',y: 1}
			];
	$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	highChartsDonut(id,data,false)
}
function committees()
{
	mandalLevelGraph();
	villageLevelGraph();
	affMandalLevelGraph();
	affVillageLevelGraph();
	function mandalLevelGraph()
	{
		var id = 'mandalLevelGraph';
		var type = 'pie';
		var depth = 0;
		var innerSize = 0;
		var data = [
					{name:'Bananas', y:8},
					{name:'Kiwi', y:3},
					{name:'Mixed nuts',y: 1},
					{name:'Oranges',y: 6},
					{name:'Apples',y: 8},
					{name:'Pears',y: 4},
					{name:'Clementines',y: 4},
					{name:'Reddish (bag)',y: 1},
					{name:'Grapes (bunch)',y: 1}
				];
		$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		highChartsDonut(id,data,false)
	}
	function villageLevelGraph()
	{
		var id = 'villageLevelGraph';
		var type = 'pie';
		var depth = 0;
		var innerSize = 0;
		var data = [
					{name:'Bananas', y:8},
					{name:'Kiwi', y:3},
					{name:'Mixed nuts',y: 1},
					{name:'Oranges',y: 6},
					{name:'Apples',y: 8},
					{name:'Pears',y: 4},
					{name:'Clementines',y: 4},
					{name:'Reddish (bag)',y: 1},
					{name:'Grapes (bunch)',y: 1}
				];
		$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		highChartsDonut(id,data,false)
	}
	function affMandalLevelGraph()
	{
		var id = 'affMandalLevelGraph';
		var type = 'pie';
		var depth = 0;
		var innerSize = 0;
		var data = [
					{name:'Bananas', y:8},
					{name:'Kiwi', y:3},
					{name:'Mixed nuts',y: 1},
					{name:'Oranges',y: 6},
					{name:'Apples',y: 8},
					{name:'Pears',y: 4},
					{name:'Clementines',y: 4},
					{name:'Reddish (bag)',y: 1},
					{name:'Grapes (bunch)',y: 1}
				];
		$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		highChartsDonut(id,data,false)
		}
	function affVillageLevelGraph()
	{
		var id = 'affVillageLevelGraph';
		var type = 'pie';
		var depth = 0;
		var innerSize = 0;
		var data = [
					{name:'Bananas', y:8},
					{name:'Kiwi', y:3},
					{name:'Mixed nuts',y: 1},
					{name:'Oranges',y: 6},
					{name:'Apples',y: 8},
					{name:'Pears',y: 4},
					{name:'Clementines',y: 4},
					{name:'Reddish (bag)',y: 1},
					{name:'Grapes (bunch)',y: 1}
				];
		$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		highChartsDonut(id,data,false)
	}
	
}
function problemsDetailedGraph()
{
	var id = 'problemsDetailedGraph';
	var type = 'pie';
	var depth = 0;
	var innerSize = 0;
	var data = [
				{name:'Bananas', y:8},
				{name:'Kiwi', y:3},
				{name:'Mixed nuts',y: 1},
				{name:'Oranges',y: 6},
				{name:'Apples',y: 8},
				{name:'Pears',y: 4},
				{name:'Clementines',y: 4},
				{name:'Reddish (bag)',y: 1},
				{name:'Grapes (bunch)',y: 1}
			];
	$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	highChartsDonut(id,data,false)
	
}
function problemsSolveGraph()
{
	var id = 'problemsSolveGraph';
	var type = 'bar';
	var depth = 0;
	var innerSize = 0;
	var data = [
				['Bananas', 8],
				['Kiwi', 3],
				['Mixed nuts', 1],
				['Reddish (bag)', 1],
				['Grapes (bunch)', 1]
			];
	$("#"+id).html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	highcharts(id,type,data,false,'');	
}