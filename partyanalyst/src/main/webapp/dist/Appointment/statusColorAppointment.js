
var statusColorArr = [];
var statusarr = ['Waiting','Approved','Scheduled','Completed','Cancelled','Rejected','Hold','Rescheduled'
,'Withdrawn','Not Attended'];
function setcolorsForStatus()
	{
		statusColorArr = new Array();
		var colorStatic = new Array('#a94442','#48B36B','#673301','#3c763d','#A32D2D',
		'#00BBD4','#FE9601',' #A86FC5','#E4175A','#8a6d3b');
		
		var colorCount = 0;
		
		for(var i in statusarr)
		{
		 
				var obj = {
				 status : statusarr[i],
				 color : colorStatic[colorCount]
				}
				statusColorArr.push(obj)
			  
				if(colorCount == (colorStatic.length)-1)
				colorCount = 0;
				 colorCount++;
		}
		
		return statusColorArr;
	}

	function getColorCodeByStatus(status)
	{

		if(statusColorArr != null && statusColorArr.length > 0)
		{
			for(var i in statusColorArr)
			{
			
				if(statusColorArr[i].status.toLowerCase() == status.toLowerCase())
					return statusColorArr[i].color;
			}
		}
	}