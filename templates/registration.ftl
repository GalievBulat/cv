<#include  "config.ftl">
<@config name="reg"/>
<body style=" margin: 0;  height: 100%;font-family: Times New Roman,serif;font-style: normal;font-weight: 600;">
<#include "nav.ftl">

<div class="backgr" style="margin-top: 56px;">
    <#include  "header.ftl">
    <@header text ="Sign up"/>

    <div class="figure2" style="width: 80%;">
        <#if errorMessage?has_content >
        <p class="highlight">${errorMessage}</p>
        </#if>
        <form method="post"  class="form ">
            <label class="highlight">Введите регестрационные данные:</label>
            <input class="form_element" type="text" name="name" placeholder="имя"/>
            <input class="form_element" type="text" name="surname" placeholder="фамилия"/>
            <input class="form_element" type="tel" name="phone_num" placeholder="тел номер"/>
            <input class="form_element" type="number" name="tc" placeholder="тр карта"/>
            <input class="form_element" type="email" name="email" placeholder="эл почта"/>
            <input class="form_element" type="password" name="password" placeholder="пароль"/>
            <input class="form_element" type="date" name="birth_day" placeholder="день рождения"/>
            <input class="figure3 unskewed btn btn-dark" type="submit" value="REGISTER"/>
        </form>
    </div>

</div>

<@bootstrapjs></@bootstrapjs>
</body>
</html>