<!DOCTYPE html>
<html style="height: 100%;" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <#include "styles.css">
    <meta charset="UTF-8" />
    <title>JSP Application</title>
    <link rel="stylesheet" type="text/css" href="styles.css"/>
</head>
<body style=" margin: 0;  height: 100%;font-family: Times New Roman,serif;font-style: normal;font-weight: 600;">
<div class="backgr" style="overflow: auto;">
    <div id="parallelogram" class="figure" style="margin-top: 0;">
        <p class="text skewed">Log in</p>
    </div>
    <div class="figure2" style="width: 80%;">

        <#if errorMessage?has_content >
            <p class="highlight">${errorMessage}</p>
        </#if>
        <form method="post"  class="form ">
            <label class="highlight">Введите номер карты и пароль</label>
            <input class="form_element" type="number" name="tc" placeholder="tc"/>
            <input class="form_element" type="password" name="password" placeholder="password"/>
            <input class="figure3 unskewed" type="submit" value="AUTH"/>
        </form>
    </div>

</div>
</body>
</html>