/**
 * Created by Administrator on 2018/3/4.
 */
//创建compose项目

$(function(){
    loadlist();
})
$("#ntn-primary").click(function(){
    var  s = $("#wangduo").val();
  // var p = ReplaceSeperator(s);
    $.ajax({
        url:"/engine/compose/creatfromyaml",
        method: "POST",
        dataType: "text",
        data: {"yaml":s,"name":"sss"},
        success : function(data){
            alert(data)
        }
    })

})

function loadlist(){
    $.ajax({
        url:"/engine/compose/listprojects",
        method: "GET",
        dataType: "json",
        success : function(data){
            var json = data.resultInfo.data;
            var obj = JSON.parse(json);
           var p = obj.projects;
            var tr = "";
            var n =1;

            var obj = {};
            for(var k in p) {
                tr += "<tr>" +
                    "<td>" + n++ + "</td>" +
                    "<td>" + k + "</td>" +
                    "<td>" + p[k] + "</td>" +
                    "</tr>";
            }
            $("#project-list tbody").html(tr);
        }
    })

}