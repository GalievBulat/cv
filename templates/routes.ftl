<#include  "resources/config.ftl">
<@config name="routes"/>
<#include "resources/background.ftl">
<#include "resources/nav.ftl">
<div  style="margin-top: 56px;">
    <#include  "resources/header.ftl">
    <@header text ="Маршруты"/>
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
                <div class="btn btn-dark main_button btn-sm default" onclick="send()">
                    <p class="highlight  default" style="font-size: 150%">Поиск</p></div>
            </form>
            <#include "resources/field.ftl">
        </div>
        <div class="figure2 rightAl "  style="text-align: center;">
            <div style="background: black;height: 45px;border-radius: 0.5rem 0.5rem 0 0;"></div>
            <div class="spread" id="list" style="overflow: auto; height: 555px;"></div>
        </div>
    </div>
</div>
<#include "resources/footer.ftl">
<@footer margin=30/>
</body>
<@bootstrapjs></@bootstrapjs>
<script src="templates/resources/routes.js" charset="UTF-8"></script>

</html>