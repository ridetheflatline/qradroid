var search = $("#search");
var state = $("#state");
var city = $("#city");

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
		var joinHref = "google.com";
		appendValues.append($("<td>").append($("<a>").text("Join").attr("href",joinHref)));
		var moreInfoHref = "/moreconfinfo?conf_id=" + dataArray[i]["conf_id"] + "&page_output=true";
		appendValues.append($("<td>").append($("<a>").text("More Information").attr("href",moreInfoHref)));
	}
	$("#searchResultsTableBody").append(appendValues);
}