<#include  "config.ftl">
<@config name="routes"/>
<body style=" margin: 0;  height: 100%;font-family: Times New Roman,serif;font-style: normal;font-weight: 600;">
<#include "nav.ftl">
<div class="backgr" style="overflow: auto;margin-top: 56px;">
    <#include  "header.ftl">
    <@header text ="Timetable"/>
    <div style="display: flex;">
        <div class="figure2 leftAl">
            <form method="post" id = "form" action="/cv/timetable">
                <select class="list form_element" name="station">
                    <option disabled>Выберите остановку</option>
                    <option selected value="PSH">Пушкина</option>
                    <option value="SHR">Сахарова</option>
                    <option value="PBD">Победы</option>
                    <option value="SLV">Славы Водолазов</option>
                    <option value="SHT">Шахтерская</option>
                    <option value="BSH">Башкирская</option>
                    <option value="KRC">Керченская</option>
                </select>
                <div style="display: flex">
                    <p class="form_text d-flex align-content-center flex-wrap">c</p>
                    <input class="form_element" style="width: 100%" name="time1" type="time" value="12:00"/>
                </div>
                <div style="display: flex" onclick="send()">
                    <p  class="form_text d-flex align-content-center flex-wrap"> по </p>
                    <input class="form_element" style="width: 100%" name="time2" type="time" value="22:00"/>
                </div>
                <select class="list form_element" name="day_of_the_week">
                    <option disabled>Выберите день недели</option>
                    <option selected value="*">Любой</option>
                    <option value="1">Понедельник</option>
                    <option value="2">Вторник</option>
                    <option value="3">Среда</option>
                    <option value="4">Четверг</option>
                    <option value="5">Пятница</option>
                    <option value="6">Суббота</option>
                    <option value="7">Воскресенье</option>
                </select>
                <div class="btn btn-dark main_button btn-sm default"<#-- style="background-color: #1c3738;
    border-color: #1c3738;" -->  onclick="send()">
                    <p class="highlight  default" style="font-size: 150%">Поиск</p></div>
            </form>
            <#include "field.ftl">
            <script>
                function send() {
                    let form = $("#form")[0];
                    let formObj = new FormData(form);
                    let root = {};
                    formObj.forEach((value, key) => root[key] = value);
                    let json = JSON.stringify(root);
                    $.ajax({
                        type: 'POST',
                        url: form.action,
                        data: json,
                        processData: false,
                        contentType: false,
                        data_type: json,
                        success: function (_data) {
                            let inf = JSON.parse(_data);
                            let rootEl = document.getElementById("list");
                            rootEl.innerText = "";
                            for (i in inf){
                                console.log(inf[i]["id"]);
                                let el = document.createElement("div");
                                el.innerHTML = "<div class=\"card text-white bg-dark mb-3\" > <div class=\"card-header\">" + "Маршрут номер:" + inf[i]["route_num"] + "</div> <div class=\"card-body\"> <h5 class=\"card-title\">" + inf[i]["time"] + "</h5> <p class=\"card-text\">" + "День недели: " + inf[i]["day_of_the_week"] + "</p> </div> </div>";
                                rootEl.appendChild(el);
                            }

                        },
                        error: function (_data) {
                            alert(JSON.stringify(_data));
                        }
                    });
                }
            </script>
        </div>
        <div class="figure2 rightAl " >
            <div style="background: black;height: 45px;"></div>
            <div class="spread" id="list"></div>
        </div>
    </div>
</div>
</body>
<@bootstrapjs></@bootstrapjs>

</html>