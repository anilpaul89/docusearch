var apiEndpoint = "http://localhost:9000/v1/search?q=";

var docusearch = function(){
        $("#search").on("click", function(){
            $("#searchresult").fadeOut();
            $("#results").fadeOut();
            $("#noresults").fadeOut();
            var searchText = $("#searchquery").val();
            callAPI(searchText)
        })
}

var callAPI = function(text){
 $("#tablebody").html("");
    $.ajax({
        url:apiEndpoint + text,
        success: function(results){
         $("#searchresult").fadeIn();
           if(results.length == 0){
            $("#noresults").fadeIn();
             $("#results").fadeOut();
           } else {
            $("#results").fadeIn();
            $("#noresults").fadeOut();
            var counter = 1;
            results.forEach(result => {
                console.log(result)
                var str = "<tr><th scope='row'>"+counter+"</th><td>"+result['title']+"</td><td><a href='#' downloadpath='"+result['downloadPath']+"' > Download</a></td></tr>";
                counter++;
                $("#tablebody").append(str);
            });
            $("[downloadpath]").on('click',function(e){
                e.preventDefault();
                window.location.href = "http://localhost:9000/v1/download/"+encodeURIComponent($(this).attr("downloadpath"))+"?source=DROPBOX";
            })
           }
        }
    })
}