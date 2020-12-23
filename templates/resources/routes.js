function send() {
    let form = $("#form")[0];
    let formObj = new FormData(form);
    let root = {};
    formObj.forEach((value, key) => root[key] = value);
    if (root["station"].search(/[A-Z][A-Z][A-Z]/) !==-1 && root["time1"].search(/\d{2}:\d{2}/)!==-1 && root["time2"].search(/\d{2}:\d{2}/)!==-1) {
        let json = JSON.stringify(root);
        $.ajax({
            type: 'POST',
            url: form.action,
            data: json,
            processData: false,
            contentType: false,
            data_type: "json",
            success: function (_data) {
                let inf = JSON.parse(_data);
                let rootEl = document.getElementById("list");
                rootEl.innerText = "";
                for (i in inf) {
                    console.log(inf[i]["id"]);
                    let el = document.createElement("div");
                    el.innerHTML = "<div class=\"card text-white bg-dark mb-3\" > " +
                        "<div class=\"card-header\">"
                        + decodeURI("%D0%9C%D0%B0%D1%80%D1%88%D1%80%D1%83%D1%82%20%D0%BD%D0%BE%D0%BC%D0%B5%D1%80")
                        + ' ' + inf[i]["route_num"]
                        + "</div> <div class=\"card-body\"> <h2 class=\"card-title\">"
                        + inf[i]["time"] + "</h2> <p class=\"card-text\">"
                        + decodeURI("%D0%94%D0%B5%D0%BD%D1%8C%20%D0%BD%D0%B5%D0%B4%D0%B5%D0%BB%D0%B8")
                        + ': ' + inf[i]["day_of_the_week"] +
                        "</p> </div> </div>";
                    rootEl.appendChild(el);
                }

            },
            error: function (_data) {
                document.write(_data.responseText);
            }
        });
    } else alert("wrong input");
}