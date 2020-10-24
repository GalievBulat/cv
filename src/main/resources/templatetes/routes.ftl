
<!DOCTYPE html>
<html style="height: 100%;"  xmlns="http://www.w3.org/1999/html">
<html lang="ru">
<head>
    <#include "styles.css">
    <meta charset="UTF-8" />
    <title>JSP Application</title>
    <link rel="stylesheet" type="text/css" href="styles.css"/>
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body style=" margin: 0;  height: 100%;font-family: Times New Roman,serif;font-style: normal;font-weight: 600;">
<div class="backgr" style="overflow: auto;">
    <div id="parallelogram" class="figure" style="margin-top: 0;">
        <p class="text skewed">Routes</p>
    </div>
    <div style="display: flex;">
        <div class="figure2 leftAl">
            <form method="post" id = "form" action="/cv/timetable">
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
                    <p class="form_text">c </p>
                    <input class="form_element" style="width: 100%" name="time1" type="time" value="12:00"/>
                </div>
                <div style="display: flex">
                    <p  class="form_text"> по </p>
                    <input class="form_element" style="width: 100%" name="time2" type="time" value="22:00"/>
                </div>
                <p><input class="figure3 unskewed" style="font-size: 100%; width: 100%;" type="submit" value="Поиск" onclick="send()"></p>
            </form>
            <script>
                function send() {
                    let form = $("#form")[0];
                    let formObj = new FormData(form);
                    let root = {};
                    formObj.forEach((value, key) => root[key] = value);
                    let json =(JSON.stringify(root));
                    $.ajax({
                        type: 'POST',
                        url: '/cv/timetable',
                        data: json,
                        processData: false,
                        contentType: false,
                        data_type: "json",
                        success: function (_data) {
                            let inf = JSON.parse(JSON.stringify(_data))
                            let rootEl = document.getElementById("list");
                            for (st in inf) {
                                let el = document.createTextNode(st);
                                rootEl.appendChild(el);
                            }
                            /*let res = _data;
                            if (res['redirect'] !== null) {
                                window.location.replace(window.location.origin + res['redirect']);
                            }*/
                        },
                        error: function (_data) {
                            alert(_data);
                        }
                    });
                }
            </script>
        </div>
        <div class="figure2 rightAl" id="list">
            <#if tList?has_content>
                <#list tList as item>
                    <p>${item.toString()}</p>
                </#list>
            </#if>
        </div>
    </div>
</div>

</body>
