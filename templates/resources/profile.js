
window.onload = function() {
    $("#avatarPicking").toggle();
}
function send(){
    //$("#bt_sbm").prop("onclick", "");
    let form = $("#avatarPicking")[0];
    let formObj = new FormData(form);
    if (formObj != null) {
        $.ajax({
            enctype: 'multipart/form-data',
            type: 'POST',
            url: form.action,
            data: formObj,
            processData: false,
            contentType: false,
            data_type: "text/plain",
            success: function (_data) {
                $("#picture").prop("src", _data + "?" + new Date().getTime());
            },
            error: function (data) {
                document.write(data.responseText);
            }
        });
    }
}
let html =
    '<input />';
let nameLocal;
let surnameLocal;
let state = 0;
function changeProfile(){

    if (state === 0) {
        surnameLocal =  $("#surname").text();
        nameLocal =  $("#name").text();
        $("#avatarPicking").toggle();
        $("#name").html(html);
        $("#surname").html(html);
        state++
        $("#change").text(decodeURI("%D1%81%D0%BE%D1%85%D1%80%D0%B0%D0%BD%D0%B8%D1%82%D1%8C"));
    }else if (state === 1) {
        $("#avatarPicking").toggle();
        let root = {};
        let name = $("#name").children().val();
        let surname = $("#surname").children().val();
        if(name!=='' && surname!=='') {
            root["name"] = '' + name;
            root["surname"] = '' + surname;
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
            state = 0;
        } else {
            $("#name").text(nameLocal);
            $("#surname").text(surnameLocal);
            state = 0;
        }
        $("#change").text(
            decodeURI("%D0%98%D0%B7%D0%BC%D0%B5%D0%BD%D0%B8%D1%82%D1%8C%20%D0%BF%D1%80%D0%BE%D1%84%D0%B8%D0%BB%D1%8C"));
    }
}