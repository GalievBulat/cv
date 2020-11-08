function send(){
    $("#bt_sbm").prop("onclick", "");
    let form = $("#avatarPicking")[0];
    let formObj = new FormData(form);
    $.ajax({
        enctype: 'multipart/form-data',
        type: 'POST',
        url: form.action,
        data: formObj,
        processData: false,
        contentType: false,
        data_type: "text/plain",
        success: function (_data) {
            alert("OK");
            $("#picture").prop("src",_data);
        },
        error: function (data) {
            alert(data.responseText);
        }
    });
}
let html =
    '<input />';
window.onload = function() {
    $("#avatarPicking").toggle();
}
let name;
let surname;
let state = 0;
function changeProfile(){
    if (state === 1){
        $("#avatarPicking").toggle();
        let root = {};
        name = $("#name").children().val();
        surname = $("#surname").children().val();
        root["name"] ='' + name;
        root["surname"] ='' + surname;
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8088/cv/profile',
            data: JSON.stringify(root),
            processData: false,
            contentType: false,
            data_type: "json",
            success: function () {
                $("#name").text(name);
                $("#surname").text(surname);
            },
            error: function (data) {
                document.write(data.responseText);
            }
        });
    }
    if (state === 0) {
        $("#avatarPicking").toggle();
        $("#name").html(html);
        $("#surname").html(html);
        state++
    }
}