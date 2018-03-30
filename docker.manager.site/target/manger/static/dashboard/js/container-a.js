var success = "<span class='label label-success'>" +"RUN"+"</span>";
var danger ="<span class='label label-danger'>"+"STOP"+"</span>"
$(function(){
    var a=GetRequest();
    var index_1=a['name'];
    $("#container-a-name").text(index_1)
    loadlist(index_1);
})

function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}


//加载模板列表
function loadlist(name){
    $.ajax({
        url:"/engine/compose/model/showprojectdetails",
        method: "GET",
        dataType: "json",
        data: {"name":name},
        success : function(data){
            var json = data.resultInfo.data;
            var obj = JSON.parse(json);
            var p = obj.containers;
            var tr = "";
            var n =1;
            var operation_start = "<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#myModal_Prompt' onclick='getmodel(this)'> Del</button>";//
            var up_project = "<button type='button' class='btn btn-primary' onclick='up_project(this)'>up</button>";//
            var ind;
            var ports;
            var addr;

            for(var k in p) {
                ind = p[k].is_running?success:danger
                ports =  p[k].ports;
                for (var p1 in ports){
                    addr = ports[p1][0].HostIp+"/"+ ports[p1][0].HostPort
                }


                tr += "<tr>" +
                    "<td>" + n++ + "</td>" +
                    "<td>" + p[k].name+"</td>" +
                    "<td>" + addr + "</td>" +
                    "<td>" + ind  +"</td>" +
                    "<td>" + up_project + operation_start+"</td>" +
                    "</tr>";
            }
            $("#templatesbadge").text(n-1);
            $("#containerlist tbody").html(tr);
        }
    })

}


