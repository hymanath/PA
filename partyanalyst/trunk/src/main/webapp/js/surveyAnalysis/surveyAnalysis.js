function buildChart(result,div)
{
var chartDiv = document.getElementById(''+div+'');

var data = new google.visualization.DataTable();
data.addColumn('string', 'option');
data.addColumn('number', 'votesObtained');
data.addRows(result.length);
		
		for (var i=0;i<result.length;i++)
			{
			
			data.setValue(i, 0,result[i].option);
			data.setValue(i, 1,result[i].votesObtained);
			}
			var chart = new google.visualization.PieChart(chartDiv);
					chart.draw(data, {width:350, height:200, title: "", legendFontSize:10,fontSize:10});
}


function buildChartForOptionSurveyAnalysis(result,div)
{
var chartDiv = document.getElementById(''+div+'');

var data = new google.visualization.DataTable();
data.addColumn('string', 'option');
data.addColumn('number', 'optionCount');
data.addRows(result.length);
		
		for (var i=0;i<result.length;i++)
			{
			
			data.setValue(i, 0,result[i].option);
			data.setValue(i, 1,result[i].optionCount);
			}
			var chart = new google.visualization.PieChart(chartDiv);
					chart.draw(data, {width:350, height:200, title: "", legendFontSize:10,fontSize:10});
}

function buildChartForSurveyAnalysis(result,div)
{
var chartDiv = document.getElementById(''+div+'');

var data = new google.visualization.DataTable();
data.addColumn('string', 'option');
data.addColumn('number', 'total');
data.addRows(result.length);
		
		for (var i=0;i<result.length;i++)
			{
			
			data.setValue(i, 0,result[i].option);
			data.setValue(i, 1,result[i].total);
			}
			var chart = new google.visualization.PieChart(chartDiv);
					chart.draw(data, {width:350, height:200, title: "", legendFontSize:10,fontSize:10});
}
