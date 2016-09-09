
var statusColorArr = [];
var statusarr = ['TDP','YSRC','INC','BJP'];
function setcolorsForStatus()
	{
		statusColorArr = new Array();
		var colorStatic = new Array('#FFCB00','#005DB0','#3D9834','#FD9832');
		
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