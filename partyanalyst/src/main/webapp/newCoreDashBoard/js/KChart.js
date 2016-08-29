(function ( $ ) {
	 "use strict";
    $.fn.KChart = function(options) 
		{
			var KChartDefault = $.extend(true,{
					// These are the defaults.
					colors: ['rgba(124,181,236,1)','rgba(67,67,72,1)','rgba(144,237,125,1)'],
					backgroundColor: "white",
					borderLine : true,
					labelText : true,
					showPercentage: true,
					chartHeight:'150px',
					labelLegend : [''],
					percentColor:'#fff',
					labelLegendShow:true,
					widthBtnGraphs:'8',
					fontWeight:'bold',
					graphHeading:'Heading',
				}, options );
			var j = 0;
			$(this).each(function(){
				j = j + 1;
				
				var XValue = 0;
				var TextXValue = 0;
				$(this).wrap("<div id='KChart_"+j+"'></div>");
				$("#KChart_"+j+"").find(this).hide();
				$("#KChart_"+j+"").append(KChartDefault.graphHeading);
				if(KChartDefault.labelLegendShow)
				{
					var labelLegG = document.createElementNS('http://www.w3.org/2000/svg','g');
						labelLegG.setAttribute('class','labelLegend');
				}
				
				var getWidth = $("#KChart_"+j+"").width();
				var svg = document.createElementNS('http://www.w3.org/2000/svg','svg');
					 svg.setAttribute("height",KChartDefault.chartHeight);
					 svg.setAttribute("width",getWidth);
					 svg.setAttribute("class","KChart_"+j+"");
					 svg.setAttribute("role","img");
				 $("#KChart_"+j+"").append(svg);
				
				var graphHeight = $("#KChart_"+j+"").find("svg").height() - 10;
				var getNoOfLis = getWidth / ($("#KChart_"+j+" li").length);
				
				var kk = getNoOfLis.toFixed();
				var svgWidth = $("#KChart_"+j+"").width();
				svgWidth = svgWidth / $("#KChart_"+j+" li").length - KChartDefault.widthBtnGraphs;
				$("#KChart_"+j+" li").width(kk);
				var myColors = KChartDefault.colors;
				var i = 0;
				var n = 0;
				$.each(  KChartDefault.labelLegend, function( i, l ){
					var labelLegGS = document.createElementNS('http://www.w3.org/2000/svg','g');
						labelLegGS.setAttribute('class','labelLegendS'+i+'');
					$("#KChart_"+j+"").find("svg").append(labelLegG);
					
					
					var legendSingleWidth = 25+(i*100);
					var labelLegT = document.createElementNS('http://www.w3.org/2000/svg','text');
						labelLegT.setAttribute("class","legendText"+i+"");
						labelLegT.setAttribute("x",legendSingleWidth);
						labelLegT.setAttribute("y",graphHeight);
						labelLegT.setAttribute("dy",".35em");	
						labelLegT.setAttribute("style","cursor:pointer");
						//labelLegT.setAttribute("onclick","alert('hoverChange"+n+"')");
						labelLegT.innerHTML = l;
						
					$("#KChart_"+j+"").find(".labelLegend").append(labelLegT);
				});
				
				$("#KChart_"+j+" li").each(function(){
					var h = 0;
					n = n + 1;
					
					var group1 = document.createElementNS('http://www.w3.org/2000/svg','g');
					group1.setAttribute("class","myBarSingle"+j+""+n+"");
					$("#KChart_"+j+"").find("svg").append(group1);
					var getNoOfSpans = $(this).find("span").length;
					var svgXL = 0 ;
					var indexValue = 0;
					var textIndexValue = 4;
					var spanHeight = 0 ;
					$(this).find("span").each(function(){
						spanHeight += Number($(this).attr("attr_percent"));
					});
					
					//alert(spanHeight);
					var getNameOfLi = $(this).find(".name").html();
						
					
					$(this).find("span").each(function(){
						
						$(this).css('background-color', KChartDefault.colors[i]);
						var getPercentage = $(this).attr("attr_percent");
						var getPercentageShow = $(this).attr("attr_percent");
						if(getPercentage < 10)
						{
							getPercentage = 10;
						}
						i = (i + 1) % getNoOfSpans;
						
						var rectAngle = document.createElementNS('http://www.w3.org/2000/svg','rect');
							rectAngle.setAttribute('height',getPercentage);
							rectAngle.setAttribute('width',svgWidth);
							rectAngle.setAttribute('x',XValue);
							rectAngle.setAttribute('y',indexValue);
							rectAngle.setAttribute('fill',myColors[i]);
							//rectAngle.setAttribute("transform","rotate(90deg)");
							rectAngle.setAttribute('class','hoverChange hoverChange'+n+''+h+'');
							rectAngle.setAttribute("data-toggle","tooltip");
							rectAngle.setAttribute("data-placement","top");
							rectAngle.setAttribute("title",getNameOfLi+' '+getPercentage);
							$('[data-toggle="tooltip"]').tooltip({
								'container': 'body'
							})
						
						h= h+1
						
						var percEntage = document.createElementNS('http://www.w3.org/2000/svg','text');
							percEntage.setAttribute('x',TextXValue);
							percEntage.setAttribute('y',indexValue);
							percEntage.setAttribute('fill',KChartDefault.percentColor);
							percEntage.setAttribute('class','textClass');
							percEntage.setAttribute("dy","14px");
							percEntage.setAttribute("font-weight",KChartDefault.fontWeight);
							percEntage.setAttribute('width',svgWidth);
							percEntage.innerHTML=getPercentageShow+'%';
						
						$(".myBarSingle"+j+""+n+"").append(rectAngle);
						$(".myBarSingle"+j+""+n+" rect:first-child").attr("rx","4");
						
						if(KChartDefault.showPercentage)
						{
							$(".myBarSingle"+j+""+n+"").append(percEntage);
						}
						svgXL = svgXL+parseInt(kk) + 5;
						$(".myBarSingle"+j+""+n+" rect:first-child").attr("y","0");
						indexValue = parseInt(getPercentage) + parseInt(indexValue);
						$(".myBarSingle"+j+""+n+" rect:first-child").attr("y","2");
					});
					var liCount = $(this).find("span").length;
					var textFirst  = $(".myBarSingle"+j+""+n+" rect").attr("y");
						textFirst = textFirst / 2;
					var XValueP = XValue+ (XValue * 0.018);
					if( liCount === 1)
					{
						var liSingleP = 100 - $(this).find("span").attr("attr_percent");
						
						$(".myBarSingle"+j+""+n+" rect").attr("y",liSingleP);
						$(".myBarSingle"+j+""+n+"").find(".textClass").attr("x",XValueP);
						$(".myBarSingle"+j+""+n+"").find(".textClass").attr("y",liSingleP);
					}
					var textFirstMany  = $(".myBarSingle11 rect:first-child").attr("width");
						textFirstMany = textFirstMany * 0.30;
						//alert(textFirstMany);
						$(".myBarSingle11").find(".textClass").attr("x",textFirstMany);
						$(".myBarSingle11").find(".textClass").attr("y",liSingleP);
					var rectText = document.createElementNS('http://www.w3.org/2000/svg','text');
						rectText.setAttribute("x",XValue);
						rectText.setAttribute("y",graphHeight-20);
						rectText.setAttribute("dy",".35em");
						var SubStr = getNameOfLi;
						var legendLa = svgWidth/10;
						var resultSubStr = SubStr.length <= legendLa?SubStr:SubStr.substring(0, legendLa+1)+'..';
						rectText.innerHTML=resultSubStr;
						rectText.setAttribute("data-toggle","tooltip");
						rectText.setAttribute("data-placement","bottom");
						rectText.setAttribute("title",getNameOfLi);
						$('[data-toggle="tooltip"]').tooltip({
							'container': 'body'
						})
					var rectAngle1 = document.createElementNS('http://www.w3.org/2000/svg','rect');
						rectAngle1.setAttribute("height","6");
						rectAngle1.setAttribute("width",svgWidth);
						rectAngle1.setAttribute('fill','#A2A2A2');
						rectAngle1.setAttribute('x',XValue);
						rectAngle1.setAttribute("y","105");
					
					if(KChartDefault.borderLine)
					{
						 $(".myBarSingle"+j+""+n+"").append(rectAngle1);
					}
					if(KChartDefault.labelText)
					{
						 $(".myBarSingle"+j+""+n+"").append(rectText);
					}

					XValue = parseInt(kk) + parseInt(XValue);
					TextXValue = XValue + (svgWidth * 0.20);
					var noOfLis = $("#KChart_"+j+" li").length;
				});
				$.each(  KChartDefault.colors, function( i, l ){
					var legendSingleWidth = 25+(i*100);
					var labelLegC = document.createElementNS('http://www.w3.org/2000/svg','circle');	
						labelLegC.setAttribute("fill",l);
						labelLegC.setAttribute("cx",legendSingleWidth-10);
						labelLegC.setAttribute("cy",graphHeight);
						labelLegC.setAttribute("r","6");
					$("#KChart_"+j+"").find(".labelLegend").append(labelLegC);
				});
			});
			
		};
}( jQuery ));
	