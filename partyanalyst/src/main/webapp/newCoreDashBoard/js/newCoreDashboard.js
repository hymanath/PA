
	/* function getUserBasicDetails(){
		
		var jsObj ={userId:globalUserId}
		$.ajax({
			type : 'POST',
			url : 'getUserBasicDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				globalUserTypeId = result.userTypeId;
				globalUserAccessLevelId = result.userAccessLevelId;
				globalUserAccessLevelValues = result.userAccessLevelValuesList;
				
				//onLoadCalls();
			}
		});
	} */

	
	$(document).on("click",".iconExpand",function(){
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".committeesBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		setTimeout(function(){
			$(".committeesHiddenBlock,.moreBlocksIcon").toggle();
			initialiseGraph();
		},800);
	});
	/* Notes Functionality Complete*/
	$(document).on("click",".notesIcon",function(){
		$(this).closest(".panel-heading").find(".notesDropDown").toggle();
	});
	$(document).on("click",".btnCustomCreate",function(){
		var getNewNotes = $(".notesArea").val();
		var todayDate = moment().format("DD MMMM YYYY");
		var commentText = '<span class="notesText">'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotes"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes"></i>'; 
		$(".notesUl").append("<li>"+commentText+"</li>");
		$(".notesArea").val('');	
	});
	$(document).on("click",".editNotes",function(){
		var notesHtml = $(this).closest("li").find(".notesText").html();
		$(".notesArea").val(notesHtml);
	});
	$(document).on("click",".deleteNotes",function(){
		$(this).closest("li").remove();
	});
	$(document).on("click",".moreBlocksIcon",function(){
		$(".moreBlocks").toggle();
		getLevelWiseBasicCommitteesCountReport();
		customBuildGraph();
		var tdpCommitteeLevelIdsClickedArray = [];
		tdpCommitteeLevelIdsClickedArray.push(6);
		tdpCommitteeLevelIdsClickedArray.push(8);
		getcommitteesPerformanceCohort(tdpCommitteeLevelIdsClickedArray);
	});
	$(document).on("click",".activeUlCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
	});
	
	function customBuildGraph()
	{
		var j = 0;
		$(".graphStructure").each(function(){
			j = j + 1;
			$(this).attr("id","graphStructure_"+j+"");
			var getWidth = $("#graphStructure_"+j+"").width();
			
			var getNoOfLis = getWidth / $("#graphStructure_"+j+" li").length - 4;
			var kk = getNoOfLis.toFixed() + 'px';
			$("#graphStructure_"+j+" li").width(kk);
			
			var myColors = ['#66728C','#F56800','#31AA74'];
			var i = 0;
			$("#graphStructure_"+j+" li").each(function(){
				var l = $(this).find("span").length;
				var getNoOfSpans = $(this).find("span").length;
				$(this).find("span").each(function(){
					$(this).css('background-color', myColors[i]);
					i = (i + 1) % getNoOfSpans;
				});
				$(this).find("span").each(function(){
					var getPercentage = $(this).attr("attr_percent");
					if(l > 1)
					{
						$(this).css("height",getPercentage+'%');
					}else{
						$(this).css("height","100%");
					}
				});
			});
			
		});
	}
	
	$(document).on("click",".comparisonSelect li",function(){
		if($(this).hasClass("active") == true)
		{
			$(this).removeClass("active");
		}else{
			$(".comparisonSelect li").removeClass("active");
			$(this).addClass("active");
		}
	});
	
	
	
