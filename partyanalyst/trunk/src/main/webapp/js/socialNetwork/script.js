var tweetUsers = new Array();
var buildString = "";

$(document).ready(function(){
$('#politician').attr('disabled', !$("#rpolitician").is(":checked"));
$('#party').attr('disabled', !$("#rparty").is(":checked"));
	$('#twitter-ticker').slideDown('slow');
	window.setInterval(function(){callMethod()}, 5000);
});
function callMethod(){
	var partyRadioele=document.getElementById('rparty');
	var politicianRadioele=document.getElementById('rpolitician');
if(partyRadioele.checked){
	setTweetUser($("#party option:selected").val());
}else if(politicianRadioele.checked){
	setTweetUser($("#politician option:selected").val());
}else if(politicianRadioele.checked){
	setTweetUser($("#all option:selected").val());
}
}
function setTweetUser(val){


tweetUsers=new Array();
tweetUsers.push(val);

if($('#party').val() == 0 && $('#politician').val() == null)
	return false;
getTweets();
}

function getAllTweets(result){
	i++;
var partyTweets = new Array();

for(var i=0;i<result.length;i++)
	{
partyTweets.push(result[i].url,result[i].curl);
}
var buildString = "";
for(var i=0;i<partyTweets.length;i++)
{
if(i!=0) buildString+='+OR+';
buildString+='from:'+partyTweets[i];
}

var fileref = document.createElement('script');
fileref.setAttribute("type","text/javascript");
//fileref.setAttribute("src", "http://search.twitter.com/search.json?q="+buildString+"&callback=TweetTick&rpp=50");
fileref.setAttribute("src", "https://search.twitter.com/search.json?q=from:@JaiTDP+OR+from:null+OR+from:@youthBJP+OR+from:null+OR+from:@tgten+OR+from:null+OR+from:null+OR+from:@narendramodi+OR+from:null+OR+from:@trsharish+OR+from:null+OR+from:@theleaderysjmr+OR+from:null+OR+from:@SushmaSwarajbjp+OR+from:null+OR+from:@naralokesh&callback=TweetTick&rpp=50");

document.getElementsByTagName("head")[0].appendChild(fileref);
}
function getTweets(){
	i++;

var buildString = "";
for(var i=0;i<tweetUsers.length;i++)
{
if(i!=0) buildString+='+OR+';
buildString+='from:'+tweetUsers[i];
}

var fileref = document.createElement('script');
fileref.setAttribute("type","text/javascript");
fileref.setAttribute("src", "http://search.twitter.com/search.json?q="+buildString+"&callback=TweetTick&rpp=50");

document.getElementsByTagName("head")[0].appendChild(fileref);
}

function TweetTick(ob)
{
	var container=$('#tweet-container');
	container.html('');
	
	$(ob.results).each(function(el){
		var str = '	<div class="tweet">\
					<div class="avatar"><a href="http://twitter.com/'+this.from_user+'" target="_blank"><img src="'+this.profile_image_url+'" alt="'+this.from_user+'" /></a></div>\
					<div class="user"><a href="http://twitter.com/'+this.from_user+'" target="_blank">'+this.from_user+'</a></div>\
					<div class="time">'+relativeTime(this.created_at)+'</div>\
					<div class="txt">'+formatTwitString(this.text)+'</div>\
					</div>';
		
		container.append(str);
	
	});
	//alert("checknow");
	scrollpanenew();
	//container.jScrollPane();
}

function formatTwitString(str)
{
	str=' '+str;
	str = str.replace(/((ftp|https?):\/\/([-\w\.]+)+(:\d+)?(\/([\w/_\.]*(\?\S+)?)?)?)/gm,'<a href="$1" target="_blank">$1</a>');
	str = str.replace(/([^\w])\@([\w\-]+)/gm,'$1@<a href="http://twitter.com/$2" target="_blank">$2</a>');
	str = str.replace(/([^\w])\#([\w\-]+)/gm,'$1<a href="http://twitter.com/search?q=%23$2" target="_blank">#$2</a>');
	return str;
}

function relativeTime(pastTime)
{	
	var origStamp = Date.parse(pastTime);
	var curDate = new Date();
	var currentStamp = curDate.getTime();
	
	var difference = parseInt((currentStamp - origStamp)/1000);

	if(difference < 0) return false;

	if(difference <= 5)				return "Just now";
	if(difference <= 20)			return "Seconds ago";
	if(difference <= 60)			return "A minute ago";
	if(difference < 3600)			return parseInt(difference/60)+" minutes ago";
	if(difference <= 1.5*3600) 		return "One hour ago";
	if(difference < 23.5*3600)		return Math.round(difference/3600)+" hours ago";
	if(difference < 1.5*24*3600)	return "One day ago";
	
	var dateArr = pastTime.split(' ');
	return dateArr[4].replace(/\:\d+$/,'')+' '+dateArr[2]+' '+dateArr[1]+(dateArr[3]!=curDate.getFullYear()?' '+dateArr[3]:'');
}