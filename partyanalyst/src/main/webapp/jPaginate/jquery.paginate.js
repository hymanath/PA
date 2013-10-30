var customVariable = 0;
var json0bject = '';
(function($) {
	$.fn.paginate = function(options) {
		var opts = $.extend({}, $.fn.paginate.defaults, options);
		return this.each(function() {
			$this = $(this);
			var o = $.meta ? $.extend({}, opts, $this.data()) : opts;
			var selectedpage = o.start;
			$.fn.draw(o,$this,selectedpage);	
		});
	};
	var outsidewidth_tmp = 0;
	var insidewidth 	 = 0;
	var bName = navigator.appName;
	var bVer = navigator.appVersion;
	
	if(bVer.indexOf('MSIE 7.0') > 0)
		var ver = "ie7";
	$.fn.paginate.defaults = {
		count 		: 5,
		start 		: 12,
		display  	: 5,
		border					: true,
		border_color			: '#fff',
		text_color  			: '#8cc59d',
		background_color    	: 'black',	
		border_hover_color		: '#fff',
		text_hover_color  		: '#fff',
		background_hover_color	: '#fff', 
		rotate      			: true,
		images					: true,
		mouse					: 'slide',
		onChange				: function(){return false;}
	};
	$.fn.draw = function(o,obj,selectedpage){
		if(o.display > o.count)
			o.display = o.count;
		$this.empty();
		if(o.images){
			var spreviousclass 	= 'jPag-sprevious-img';
			var previousclass 	= 'jPag-previous-img';
			var snextclass 		= 'jPag-snext-img';
			var nextclass 		= 'jPag-next-img';
		}
		else{
			var spreviousclass 	= 'jPag-sprevious';
			var previousclass 	= 'jPag-previous';
			var snextclass 		= 'jPag-snext';
			var nextclass 		= 'jPag-next';
		}
		var _first		= $(document.createElement('a')).addClass('jPag-first').html('First');
		
		if(o.rotate){
			if(o.images) var _rotleft	= $(document.createElement('span')).addClass(spreviousclass);
			else var _rotleft	= $(document.createElement('span')).addClass(spreviousclass).html('&laquo;');		
		}
		
		var _divwrapleft	= $(document.createElement('div')).addClass('jPag-control-back');
		_divwrapleft.append(_first).append(_rotleft);
		
		var _ulwrapdiv	= $(document.createElement('div')).css('overflow','hidden');
		var _ul			= $(document.createElement('ul')).addClass('jPag-pages')
		var c = (o.display - 1) / 2;
		var first = selectedpage - c;
		var selobj;
		for(var i = 0; i < o.count; i++){
			
			var val = i+1;
			if(val == selectedpage){
				
				var _obj = $(document.createElement('li')).html('<span class="jPag-current">'+val+'</span>');
				selobj = _obj;
				_ul.append(_obj);
			}	
			else{

				//var _obj = $(document.createElement('li')).html('<a onClick="getSelectedProblemDetails('+val+');">'+ val +'</a>');

				var _obj = $(document.createElement('li')).html('<a>'+ val +'</a>');
				_ul.append(_obj);
				}				
		}		
		_ulwrapdiv.append(_ul);
		
		if(o.rotate){
			if(o.images) var _rotright	= $(document.createElement('span')).addClass(snextclass);
			else var _rotright	= $(document.createElement('span')).addClass(snextclass).html('&raquo;');
		}
		
		var _last		= $(document.createElement('a')).addClass('jPag-last').html('Last');
		var _divwrapright	= $(document.createElement('div')).addClass('jPag-control-front');
		_divwrapright.append(_rotright).append(_last);
		
		//append all:
		$this.addClass('jPaginate').append(_divwrapleft).append(_ulwrapdiv).append(_divwrapright);
			
		if(!o.border){
			if(o.background_color == 'none') var a_css 				= {'color':o.text_color};
			else var a_css 											= {'color':o.text_color,'background-color':o.background_color};
			if(o.background_hover_color == 'none')	var hover_css 	= {'color':o.text_hover_color};
			else var hover_css 										= {'color':o.text_hover_color,'background-color':o.background_hover_color};	
		}	
		else{
			if(o.background_color == 'none') var a_css 				= {'color':o.text_color,'border':'1px solid '+o.border_color};
			else var a_css 											= {'color':o.text_color,'background-color':o.background_color,'border':'1px solid '+o.border_color};
			if(o.background_hover_color == 'none')	var hover_css 	= {'color':o.text_hover_color,'border':'1px solid '+o.border_hover_color};
			else var hover_css 										= {'color':o.text_hover_color,'background-color':o.background_hover_color,'border':'1px solid '+o.border_hover_color};
		}
		
		$.fn.applystyle(o,$this,a_css,hover_css,_first,_ul,_ulwrapdiv,_divwrapright);
		//calculate width of the ones displayed:
		var outsidewidth = outsidewidth_tmp - _first.parent().width() -3;
		if(ver == 'ie7'){
			_ulwrapdiv.css('width',outsidewidth+72+'px');
			_divwrapright.css('left',outsidewidth_tmp+6+72+'px');
		}
		else{
			_ulwrapdiv.css('width',outsidewidth+'px');
			_divwrapright.css('left',outsidewidth_tmp+6+'px');
		}
		
		if(o.rotate){
			_rotright.hover(
				function() {
				  thumbs_scroll_interval = setInterval(
					function() {
					  var left = _ulwrapdiv.scrollLeft() + 1;
					  _ulwrapdiv.scrollLeft(left);
					},
					20
				  );
				},
				function() {
				  clearInterval(thumbs_scroll_interval);
				}
			);
			_rotleft.hover(
				function() {
				  thumbs_scroll_interval = setInterval(
					function() {
					  var left = _ulwrapdiv.scrollLeft() - 1;
					  _ulwrapdiv.scrollLeft(left);
					},
					20
				  );
				},
				function() {
				  clearInterval(thumbs_scroll_interval);
				}
			);
			if(o.mouse == 'press'){
				_rotright.mousedown(
					function() {
					  thumbs_mouse_interval = setInterval(
						function() {
						  var left = _ulwrapdiv.scrollLeft() + 5;
						  _ulwrapdiv.scrollLeft(left);
						},
						20
					  );
					}
				).mouseup(
					function() {
					  clearInterval(thumbs_mouse_interval);
					}
				);
				_rotleft.mousedown(
					function() {
					  thumbs_mouse_interval = setInterval(
						function() {
						  var left = _ulwrapdiv.scrollLeft() - 5;
						  _ulwrapdiv.scrollLeft(left);
						},
						20
					  );
					}
				).mouseup(
					function() {
					  clearInterval(thumbs_mouse_interval);
					}
				);
			}
			else{
				_rotleft.click(function(e){
					
					var width = outsidewidth - 10;
					var left = _ulwrapdiv.scrollLeft() - width;
					_ulwrapdiv.animate({scrollLeft: left +'px'});
				});	
				
				_rotright.click(function(e){
					var width = outsidewidth - 10;
					var left = _ulwrapdiv.scrollLeft() + width;
					_ulwrapdiv.animate({scrollLeft: left +'px'});
				});
			}
		}
		
		//first and last:
		_first.click(function(e){
          
		     //getProblemDetailsByFirstAndLast('first');	
				_ulwrapdiv.animate({scrollLeft: '0px'});
				_ulwrapdiv.find('li').eq(0).click();
		});
		_last.click(function(e){
			 // getProblemDetailsByFirstAndLast('last');	
				_ulwrapdiv.animate({scrollLeft: insidewidth +'px'});
				_ulwrapdiv.find('li').eq(o.count - 1).click();
		});
		
		//click a page
		_ulwrapdiv.find('li').click(function(e){
          
		 
			
			selobj.html('<a>'+selobj.find('.jPag-current').html()+'</a>'); 
			var currval = $(this).find('a').html();

           getSelectedProblemDetails(currval);
			
			$(this).html('<span class="jPag-current">'+currval+'</span>');
			selobj = $(this);
			$.fn.applystyle(o,$(this).parent().parent().parent(),a_css,hover_css,_first,_ul,_ulwrapdiv,_divwrapright);	
			var left = (this.offsetLeft) / 2;
			var left2 = _ulwrapdiv.scrollLeft() + left;
			var tmp = left - (outsidewidth / 2);

			if(ver == 'ie7')
				_ulwrapdiv.animate({scrollLeft: left + tmp - _first.parent().width() + 52 + 'px'});	
			else
				_ulwrapdiv.animate({scrollLeft: left + tmp - _first.parent().width() + 'px'});	
			o.onChange(currval);	
		});
		
		var last = _ulwrapdiv.find('li').eq(o.start-1);
		last.attr('id','tmp');
		var left = document.getElementById('tmp').offsetLeft / 2;
		last.removeAttr('id');
		var tmp = left - (outsidewidth / 2);
		if(ver == 'ie7') _ulwrapdiv.animate({scrollLeft: left + tmp - _first.parent().width() + 52 + 'px'});	
		else _ulwrapdiv.animate({scrollLeft: left + tmp - _first.parent().width() + 'px'});	
	}
	
	$.fn.applystyle = function(o,obj,a_css,hover_css,_first,_ul,_ulwrapdiv,_divwrapright){
					obj.find('a').css(a_css);
					obj.find('span.jPag-current').css(hover_css);
					obj.find('a').hover(
					function(){
						$(this).css(hover_css);
					},
					function(){
						$(this).css(a_css);
					}
					);
					obj.css('padding-left',_first.parent().width() + 5 +'px');
					insidewidth = 0;
					
					obj.find('li').each(function(i,n){
						if(i == (o.display-1)){
							outsidewidth_tmp = this.offsetLeft + this.offsetWidth ;
						}
						insidewidth += this.offsetWidth;
					})
					_ul.css('width',insidewidth+'px');
	}
})(jQuery);




/*function getProblemDetailsByFirstAndLast(status){
	
	alert("getProblemDetailsByFirstAndLast");
	
	if(status == 'first')
		getSelectedProblemDetails(1);
	else{

		var totlNoOfPages = $('.jPag-pages li').size();
		getSelectedProblemDetails(totlNoOfPages);
	}
}*/

function getSelectedProblemDetails(value){

	var startVal = ((value-1)*10);
     json0bject.firstResult = startVal;
	 json0bject.selectedPage = value;
	 
	var rparam ="task="+YAHOO.lang.JSON.stringify(json0bject);
	var url = "getCompleteProblemsAction.action?"+rparam;						
	callAjax(json0bject,url);
	
}


var problems ;
function processTheResults(results , selectedPage){	
 problems = new Array();
	//alert("processTheResults  for "+selectedPage);

	for(var i=0; i<results.length;i++){

		var problem = {'Problem':results[i].problemTitle,'location1':results[i].problemCompleteLoc,'Rating':results[i].rating,'problemId':results[i].problemId};

		problems.push(problem);	
     }	
  if(results != null && results.length > 0)
     displayProblemsInToDiv(selectedPage,problems,results[0].problemsCount);
  else
     displayProblemsInToDiv(selectedPage,problems,0);	  

}

function displayProblemsInToDivVal(resultPrblms){

	//alert("displayProblemsInToDiv  for "+selectedPage);

	var str='';
      str +='<ul class="unstyled problemcollection">';
	if(resultPrblms != null && resultPrblms.length > 0){
	  for(var i=0;i < resultPrblms.length;i++){
    

			str +='<li>';
						str +='<h5><a href="completeProblemDetailsAction.action?problemId='+resultPrblms[i].problemId+'">'+resultPrblms[i].Problem+'</a></h5>';
						str +='<div>';
						str +='	<span>';
						       if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>0 && parseFloat(resultPrblms[i].Rating)<= 0.25)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
                               if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>0.25 && parseFloat(resultPrblms[i].Rating)<= 0.5)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>0.5 && parseFloat(resultPrblms[i].Rating)<= 0.75)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>0.75 && parseFloat(resultPrblms[i].Rating)<= 1)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>1 && parseFloat(resultPrblms[i].Rating)<= 1.25)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>1.25 && parseFloat(resultPrblms[i].Rating)<= 1.5)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>1.5 && parseFloat(resultPrblms[i].Rating)<= 1.75)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>1.75 && parseFloat(resultPrblms[i].Rating)<= 2)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>2 && parseFloat(resultPrblms[i].Rating)<= 2.25)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>2.25 && parseFloat(resultPrblms[i].Rating)<= 2.5)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>2.5 && parseFloat(resultPrblms[i].Rating)<= 2.75)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>2.75 && parseFloat(resultPrblms[i].Rating)<= 3)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>3 && parseFloat(resultPrblms[i].Rating)<= 3.25)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>3.25 && parseFloat(resultPrblms[i].Rating)<= 3.5)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>3.5 && parseFloat(resultPrblms[i].Rating)<= 3.75)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>3.75 && parseFloat(resultPrblms[i].Rating)<= 4)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>4 && parseFloat(resultPrblms[i].Rating)<= 4.25)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>4.25 && parseFloat(resultPrblms[i].Rating)<= 4.5)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>4.5 && parseFloat(resultPrblms[i].Rating)<= 4.75)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>4.75 && parseFloat(resultPrblms[i].Rating)<= 5)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
						str +='	</span>';
						str +='	<h6>'+resultPrblms[i].location1+'</h6>';
						str +='</div>';
			str +='</li>';
	 }
	}
	else{
	  str +='<span id="probnotexisterrmsg">No Problems Exists With Your Search Criteria</span>';
	}
   str+='</ul>';
	$('#allProblemsDisplayDiv').html(str);
     $('input.star').rating();
}

function displayProblemsInToDiv(selectedPage,resultPrblms,totalCnt){

	//alert("displayProblemsInToDiv  for "+selectedPage);

	var str='';
      str +='<ul class="unstyled problemcollection">';
	if(resultPrblms != null && resultPrblms.length > 0){
	  for(var i=0;i < resultPrblms.length;i++){
    

			str +='<li>';
						str +='<h5><a href="completeProblemDetailsAction.action?problemId='+resultPrblms[i].problemId+'">'+resultPrblms[i].Problem+'</a></h5>';
						str +='<div>';
						str +='	<span>';
						       if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>0 && parseFloat(resultPrblms[i].Rating)<= 0.25)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
                               if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>0.25 && parseFloat(resultPrblms[i].Rating)<= 0.5)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>0.5 && parseFloat(resultPrblms[i].Rating)<= 0.75)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>0.75 && parseFloat(resultPrblms[i].Rating)<= 1)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>1 && parseFloat(resultPrblms[i].Rating)<= 1.25)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>1.25 && parseFloat(resultPrblms[i].Rating)<= 1.5)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>1.5 && parseFloat(resultPrblms[i].Rating)<= 1.75)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>1.75 && parseFloat(resultPrblms[i].Rating)<= 2)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>2 && parseFloat(resultPrblms[i].Rating)<= 2.25)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>2.25 && parseFloat(resultPrblms[i].Rating)<= 2.5)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>2.5 && parseFloat(resultPrblms[i].Rating)<= 2.75)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>2.75 && parseFloat(resultPrblms[i].Rating)<= 3)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>3 && parseFloat(resultPrblms[i].Rating)<= 3.25)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>3.25 && parseFloat(resultPrblms[i].Rating)<= 3.5)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>3.5 && parseFloat(resultPrblms[i].Rating)<= 3.75)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>3.75 && parseFloat(resultPrblms[i].Rating)<= 4)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>4 && parseFloat(resultPrblms[i].Rating)<= 4.25)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>4.25 && parseFloat(resultPrblms[i].Rating)<= 4.5)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>4.5 && parseFloat(resultPrblms[i].Rating)<= 4.75)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
							   if(resultPrblms[i].Rating != null && parseFloat(resultPrblms[i].Rating)>4.75 && parseFloat(resultPrblms[i].Rating)<= 5)
						          str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}" checked="checked"/>';
							   else
                                  str +='	<input name="adv'+i+'" type="radio" disabled="disabled" class="star {split:4}"/>';
						str +='	</span>';
						str +='	<h6>'+resultPrblms[i].location1+'</h6>';
						str +='</div>';
			str +='</li>';
	 }
	}
	else{
	  str +='<span id="probnotexisterrmsg">No Problems Exists With Your Search Criteria</span>';
	}
   str+='</ul>';
	$('#allProblemsDisplayDiv').html(str);
    if(resultPrblms != null && resultPrblms.length >0)
	  displayPagination(selectedPage,totalCnt);
	else
	 displayPagination("",1);
    $('input.star').rating();
}

function displayPagination(selectedPage,totalCnt){

	//alert("displayPagination  for "+selectedPage);

  var noOfPages = Math.ceil(totalCnt/10);
  var selectedPageNumber = 1 ;
  if(selectedPage !='')
	  selectedPageNumber = selectedPage;

	$(function() {			
			
			$("#demo2").paginate({
				count 		: noOfPages,
				start 		: selectedPageNumber,
				display     : 4,
				border					: false,
				text_color  			: '#888',
				background_color    	: '#EEE',	
				text_hover_color  		: 'black',
				background_hover_color	: '#CFCFCF'
			});
	   });
}

var filterdProducts1;
function filterTheProblemsSearched(){
	

	 filterdProducts1 = new Array();
    var key = 0;

	var value = document.getElementById("prependedInput").value;

	if(value =="")
		return false;

	var sortTyp = value;

  // alert(sortTyp);

	$.each(problems, function(i, object) { 
	  filterdProducts1[key] = object;
	  key++;
	 });



 
   desc =false; 
   filterdProducts1.sort(function(a, b){
                    var a1= a[sortTyp], b1= b[sortTyp];
					
					 if(a1 != null)
					   a1 = a1.toUpperCase();
					  if(b1 != null)
					   b1 = b1.toUpperCase();
					
                    if(a1== b1){ return 0;}
                    if(desc){
                        return (a1 > b1) ? -1 : (a1 < b1) ? 1 : 0;
                    }else{
                       return a1> b1? 1: -1; 
                    }  
                });

displayProblemsInToDivVal(filterdProducts1);


}


