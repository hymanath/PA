$(".hide-div").hide();

	
/*$(document).click(function(e){
	
	$("body").removeClass('ps-active');
	$("#left-panel").removeClass('ps-active-panel');
	e.stopPropagation();
});*/
$(".btn-view").click(function(){
	$(".hide-div").show()
	});
$(".close-icon").click(function(){
	$(".hide-div").hide()
	});
$(".scroll-logo").hide();
/*
setTimeout(function(){
	$( ".headerpart1 .container" ).addClass("animated fadeOutDown");
},3000);	
setTimeout(function(){
	$( ".headerpart1 .container"  ).hide('slow');
},3700);
setTimeout(function(){
	$( "header").addClass("slow navbar-fixed-top");
	$("section").css("margin-top","70px")
},3750);	
   $( ".headerpart2" ).css( "padding", "5px" );
  setTimeout(function(){
   $( ".scroll-logo" ).show();
   $( ".scroll-logo" ).addClass( "animated fadeInUp" );
   $(".border-image").css("height","20px")
   $(".border-image").css("margin-top","0px")
   },3800);	*/ /* Changes by srishailam */
(function () {
var previousScroll = 0;
    $(window).scroll(function(){
       var currentScroll = $(this).scrollTop();
       if (currentScroll > previousScroll){
			$( ".headerpart1 .container" ).addClass("animated fadeOutDown");
			$( ".headerpart1 .container"  ).hide();
			$( ".scroll-logo" ).show();
			$( ".scroll-logo" ).addClass( "animated fadeInUp" );
			$( ".headerpart2" ).css( "padding", "5px" );
			$( "header"  ).addClass("navbar-fixed-top");
			$(".border-image").css("height","20px")
			$(".border-image").css("margin-top","0px")
			$("section").css("margin-top","70px")
			
       } else {
         $( ".headerpart1 .container" ).css( "display", "none" );
       }
       previousScroll = currentScroll;
    });
}());
