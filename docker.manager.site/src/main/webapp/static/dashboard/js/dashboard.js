/**
 * Created by Administrator on 2018/2/26.
 */
$("#panel-title").click(function(){
    $.ajax({
        url:"/engine/services/listimages",
        method: "GET",
        dataType: "json",
        success : function(data){
           var json = data.resultInfo.data;
           var tr = "";
            for(var i=0,l=5;i<l;i++){
                tr += "<tr>" +
                    "<td>" + json[i].Created + "</td>" +
                    "<td>" + json[i].RepoDigests + "</td>" +

                    "</tr>";
                }
            $("#list-images tbody").html(tr);
        }
    })

})




