// JavaScript Document
function formatText(index, panel) {
    		  return index + "";
    	  };
    
        $(function () {
        
            $('.anythingSlider').anythingSlider({
                easing: "easeInOutExpo",
                autoPlay: true,
                delay: 3000,
                startStopped: false,
                animationTime: 1500,
                hashTags: true,
                buildNavigation: true,
            		pauseOnHover: true,
            		startText: "Go",
    		        stopText: "Stop",
    		        navigationFormatter: formatText
            });
            
            $("#slide-jump").click(function(){
                $('.anythingSlider').anythingSlider(4);
            });
            
        });