<#include  "resources/config.ftl">
<@config name="reg"/>
<#include "resources/background.ftl">
<#include "resources/nav.ftl">

<div style="margin-top: 56px;">
    <#include  "resources/header.ftl">
    <@header text ="Sign up"/>

    <div class="figure2" style="width: 80%; text-align: center;">
        <form  method="post" id = "form" action="/cv/booking">
            <label class="highlight">Модель автобуса</label>
            <select class="list form_element" name="bus" style="text-align: center;">
                <option disabled>Выберите марку автобуса</option>
                <option selected value="1">Уаз 2100</option>
                <option value="2">Higer mx212</option>
                <option value="3">Mercedes n3440</option>
                <option value="4">Bentley a5</option>
            </select>
            <label class="highlight">Выберите время</label>
            <input class="form_element" style="width: 100%" name="time" type="time" value="22:00"/>
            <input class="form_element" type="tel" name="phone_num" placeholder="тел номер (+7**********)"/>
            <input class="form_element" type="text" name="spot" placeholder="место приезда"/>
            <input class="form_element" type="date" name="date" placeholder="день приезда"/>
            <input type="submit" class="btn btn-dark main_button btn-sm default" value="Отправить запрос">
        </form>
    </div>

</div>

<@bootstrapjs></@bootstrapjs>
</body>
</html>