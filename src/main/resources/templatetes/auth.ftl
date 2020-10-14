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
        <input type="number" name="tc" placeholder="tc"/>
        <input type="password" name="password" placeholder="password"/>
        <input type="submit"/>
    </form>
</div>
</body>
</html>