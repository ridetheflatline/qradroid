var search = $("#search");
var state = $("#state");
var city = $("#city");

function getCookie(c_name)
{
	var c_value = document.cookie;
	var c_start = c_value.indexOf(" " + c_name + "=");
	if (c_start == -1)
	{
		c_start = c_value.indexOf(c_name + "=");
	}
	if (c_start == -1)
	{
		c_value = null;
	}
	else
	{
		c_start = c_value.indexOf("=", c_start) + 1;
		var c_end = c_value.indexOf(";", c_start);
		if (c_end == -1)
		{
			c_end = c_value.length;
		}
		c_value = unescape(c_value.substring(c_start,c_end));
	 }
	return c_value;
}

$("#findBtn").button().click(function(){
	console.log("search: " + search.val());
	console.log("state: " + state.val());
	console.log("city: " + city.val());
	
	var urlStr = "/searchconferences?search="+search.val()+"&state="+state.val()
	+"&city="+city.val() + "&user_id";
	console.log("urlStr: "+urlStr);
	
	 $.get(urlStr,function(data,status){
		 //console.log("data: "+data);
		 $("#searchResultsTableBody").empty();
		 generateHTMLResults(data);
	 });
	
});

function generateHTMLResults(data){
	var dataArray = JSON.parse(data);
	for(var i = 0; i < dataArray.length; i++){
		var appendValues = $("<tr>").append("<td>" + dataArray[i]["conf_name"] + "</td>");
		appendValues.append("<td>"+dataArray[i]["conf_descrip"] + "</td>");
		appendValues.append("<td>"+dataArray[i]["numSessions"]+"</td>");
		//conf_code is conf_id
		var joinHref = "/joinconference?conf_code=" + dataArray[i]["conf_id"] + "&page_output=true";
		
		console.log(getCookie("userKeyId"));
		
		if(getCookie("userKeyId") != null){
			joinHref += "&user_id=" + getCookie("userKeyId");
		}
		
		appendValues.append($("<td>").append($("<a>").text("Join").attr("href",joinHref)));
		var moreInfoHref = "/moreconfinfo?conf_id=" + dataArray[i]["conf_id"] + "&page_output=true";
		appendValues.append($("<td>").append($("<a>").text("More Information").attr("href",moreInfoHref)));
		$("#searchResultsTableBody").append(appendValues);
	}

}