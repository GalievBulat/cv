<#include  "resources/config.ftl">
<@config name="reg"/>
<#include "resources/background.ftl">
<#include "resources/nav.ftl">

<div style="margin-top: 56px;">
    <#include  "resources/header.ftl">
    <@header text ="Регистрация"/>

    <div class="figure2" style="width: 80%;">
        <#include "resources/errors.ftl">
        <form method="post"  class="form">
            <label class="highlight">Введите регестрационные данные:</label>
            <input class="form_element" type="text" name="name" placeholder="имя"/>
            <input class="form_element" type="text" name="surname" placeholder="фамилия"/>
            <input class="form_element" type="tel" name="phone_num" placeholder="тел номер (+7**********)"/>
            <input class="form_element" type="number" name="tc" placeholder="тр карта"/>
            <input class="form_element" type="email" name="email" placeholder="эл почта"/>
            <input class="form_element" type="password" name="password" placeholder="пароль" id = "password_input" oninput="onPrint()"/>
            <div id="invalid password"></div>

            <input class="form_element" type="date" name="birth_day" placeholder="день рождения"/>
            <input class="figure3 unskewed btn btn-dark" type="submit" value="REGISTER"/>
        </form>
    </div>

</div>
<#include "resources/footer.ftl">
<@footer margin=30/>
<script src="templates/resources/reg.js" charset="UTF-8"></script>
<@bootstrapjs></@bootstrapjs>
</body>
</html>