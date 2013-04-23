
// this js is usefull to build line chart  
  // castMain is x-axis data  myChart is  y-axis data model 
   function buildHamletWiseCastResultsGraph(castMain , myChart )
{    
		//sorting xaxis values to based on avarage 
	 var mySort = new Array();
	 var newCast = new Array();
	 
	
	var countColor = 0;
	
  
	for( var k in castMain )
	{ 
	var custSort = new Object();
	custSort["cast"] = castMain[k];
	 var avgData = 0;
	 
	for(var l in myChart) 
	{
	var reqObj1 = myChart[l];
	var dataObj1 = reqObj1['data'];
	if(dataObj1[castMain[k]]){

	 avgData = parseInt(avgData)+parseInt(dataObj1[castMain[k]]);
	
	} else{
	 	 }
	}
	
	custSort["avg"] = avgData/(myChart.length);//(count));
	mySort.push(custSort);
	}
	mySort.sort(function(a,b) { return parseFloat(b.avg) - parseFloat(a.avg) } );
	
  	
	//sorting x axis
	var gruopCast = new Array();
	for (var p in mySort)
	{
	var myObj = mySort[p];
	newCast.push(myObj['cast']);
	
	}
  return newCast;
	}
	
	//setting null values to colums
   function buildColumnsForLineChart(newCast , myChart)
	{
	 var tempLine = new Array();
	// building column
      for(var i in myChart) 
	 {
	var clmTemp = new Object();
	var reqObj = myChart[i];
	//clmTemp['type'] = 'column';
	clmTemp['name'] = reqObj['name'];
	
	 var dataObj= reqObj['data'];
	 var newdataObj = new Array();
	for( var j in newCast )
	{ 
	 if(dataObj[newCast[j]])
	 newdataObj.push(dataObj[newCast[j]]);
	 else{
	 	 newdataObj.push(0);
	 }
	}
	clmTemp['data'] = newdataObj;
	    tempLine.push(clmTemp);
	
	}
	
	return tempLine;
	}
	
	// anils test method for the final line chart obviously
  function buildMyLineChart(xarray , tempLine ,utilObject , divId )
	
	{
	try{
	var chart1;
/*  var fileref=document.createElement('script');
  fileref.setAttribute("type","text/javascript");
  fileref.setAttribute("src", "js/highcharts/js/highcharts.js"); */
	    chart1 = new Highcharts.Chart({
            chart: {
                renderTo: divId,
                type: 'line',
				 zoomType: 'x',
                        events: {
                            click: function() {
                                this.xAxis[0].setExtremes();
								                       }
                        }
                   },
            title: {
                text: utilObject.title,
                x: -20 //center
            },
            subtitle: {
                text: 'Drag Between Any 3 Points To See In Zoom',
                x: -20
            },
            xAxis: {
               categories: xarray,
				
				 labels: {
                    rotation: -45,
                    align: 'right',
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                } 
            },
            yAxis: {
                title: {
                    text: utilObject.ytitle 
                }
            }, 
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        this.x +': '+ this.y + utilObject.tooltipText;
                }
            },
             series: tempLine //myChart1 
        });
		}catch(e){}
	return chart1;
		}
	//method that returns unique array	
  function sort_unique(a) {
     var temp = {};
    for (var i = 0; i < a.length; i++)
        temp[a[i]] = true;
    var r = [];
    for (var k in temp)
        r.push(k);
    return r;
}