// JavaScript Document
ddaccordion.init({
	headerclass: "submenuheader",
	contentclass: "submenu",
	revealtype: "click",
	mouseoverdelay: 200,
	collapseprev: true,
	defaultexpanded: [0],
	onemustopen: false,
	animatedefault: false,
	persiststate: false,
	toggleclass: ["", ""],
	togglehtml: ["suffix", "<img src='images/plus.png' class='statusicon' />", "<img src='images/minus.png' class='statusicon' />"],
	
	animatespeed: "fast",
	oninit:function(headers, expandedindices){

	},
	onopenclose:function(header, index, state, isuseractivated){
		
	}
})