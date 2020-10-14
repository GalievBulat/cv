<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8" />
    <title>JSP Application</title>
    <style>
        input{
            font-size: large;
            text-align: center;
        }
        label{
            font-size: larger;
            text-align: center;
        }
    </style>
</head>
<body>
<div>
    <div>
        <#if errorMessage?has_content>
            <h2>${errorMessage}</h2>
        </#if>
    </div>
    <form method="post"  style="flex-direction: column;display: flex;">
        <label>register</label>
        <input type="text" name="name" placeholder="name"/>
        <input type="text" name="surname" placeholder="surname"/>
        <input type="tel" name="phone_num" placeholder="phone number"/>
        <input type="number" name="tc" placeholder="tc"/>
        <input type="password" name="password" placeholder="password"/>
        <input type="email" name="email" placeholder="email"/>
        <input type="date" name="birth_day" placeholder="birthday"/>
        <input type="submit"/>
    </form>
</div>
</body>
</html>