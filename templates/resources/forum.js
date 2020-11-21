let i =0;
$(window).scroll(function(){
    if($(window).scrollTop()+$(window).height()>=$(document).height()*0.95) {
        i++;
        if(i === 1) {
            $.ajax({
                type: 'POST',
                url: "http://localhost:8088/cv/update_comments",
                data: currentPost,
                processData: false,
                contentType: "text/plain",
                data_type: "json",
                success: function (data) {
                    printPosts(data);
                    i=0;
                },
                error: function (data) {
                    document.write(data.responseText);
                    i=0;
                }
            });
        }
    }
});
function addParent(id,parent_name) {
    parentId=id;
    $("#parent").text( decodeURI("%D0%9E%D1%82%D0%B2%D0%B5%D1%82%D1%8C%D1%82%D0%B5") +  ' '+ parent_name);
}
function printPosts(data){
    let json = JSON.parse(data);
    if (json.length !== 0) {
        for (it in json) {
            let head = '<div id ="'+json[it]["id"] +'" class=\"card_style card\"> <div class="card-body"> ';
            let answer = '';
            if (json[it]["parent_id"] !== 0) {
                head = '<div id ="'+json[it]["id"] + '" style="margin-left: 10% " ' + ' class=\"card_style card\" class=\"card-body\">' +
                    ' <div class="card-body"> <h5 class="card-title">';
                answer = '<p class="card-text">' + decodeURI("%D0%92%20%D0%BE%D1%82%D0%B2%D0%B5%D1%82%20")
                    + json[it]["parent_name"] +'</p>';
            }
            let author='<h5 class="card-title">' + json[it]["author_name"] + '</h5> ';
            let categories = '';
            if(json[it]["categories"].length>0){
                let catIds = "";
                for (iter in json[it]["categories"]) {
                    catIds+= json[it]["categories"][iter] + ' ';
                }
                categories = '<h6 class="card-subtitle mb-2 text-muted">' +
                    decodeURI("%D0%9A%D0%B0%D1%82%D0%B5%D0%B3%D0%BE%D1%80%D0%B8%D0%B8") + ': ' + catIds + '</h6>';
            }
            let addressedMessage = answer +'<p class="card-text">'+ json[it]["message"] +'</p>';
            let end= '</div></div>';
            let html = head + author + categories+ addressedMessage;
            let comment = '<a class="card-link" onclick="addParent('+json[it]["id"]+',\''+ json[it]["author_name"] +'\')">'+
                decodeURI("%D0%9A%D0%BE%D0%BC%D0%B5%D0%BD%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D1%82%D1%8C")+' </a>';
            if (json[it]["comments_num"]!== 0){
                html+='<a onclick="getComments(' + json[it]["id"] + ')" class="card-link">'+
                    decodeURI("%D0%9A%D0%BE%D0%BC%D0%B5%D0%BD%D1%82%D0%B0%D1%80%D0%B8%D0%B8")+'</a>';
            }
            html+=comment
            html+=end;
            if (json[it]["parent_id"] === 0) {

                $("#discussions").append($(html));
                currentPost++;
            }else{
                let num = (json[it]["parent_id"]);
                ($(html)).insertAfter($("#" + num));
            }

        }
    }
}
function getComments(id){
    let json = JSON.stringify(id);
    $.ajax({
        type: 'POST',
        url: "http://localhost:8088/cv/get_comments",
        data: json,
        processData: false,
        contentType: "json",
        data_type: "json",
        success: function (data) {
            printPosts(data);
            $("#" + id).children(".card-body").children("a.card-link").remove();
        },
        error: function (data) {
            document.write(data.responseText);
        }
    });
}
let parentId=0;
function sendComment() {
    let root = {};
    let message= document.getElementById('message').value;
    let categories= $('#categories').val();
    root["parent_id"] = '' + parentId;
    root["author_id"] = '' + authorId;
    root["message"] = message;
    root["categories"] = '' + categories;
let json = JSON.stringify(root);
$.ajax({
    type: 'POST',
    url: 'http://localhost:8088/cv/forum',
    data: json,
    processData: false,
    contentType: false,
    data_type: "json",
    success: function (data) {
},
    error: function (_data) {
        document.write(_data.responseText);
}
});
}