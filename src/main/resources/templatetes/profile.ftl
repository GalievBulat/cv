
<#include "styles.css">
<!DOCTYPE html>
<html style="height: 100%;"  xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8" />
    <title>JSP Application</title>
    <link rel="stylesheet" type="text/css" href="styles.css"/>
</head>
<body style=" margin: 0;  height: 100%;font-family: Times New Roman,serif;font-style: normal;font-weight: 600;">
<div class="backgr" style="overflow: auto;">
    <div class="main_f">
    <p  class="text main_t" style="margin-top: 2%;">Profile</p>
    </div>
    <div id="parallelogram" class="figure" style="margin-top: 1%;">
        <p class="text skewed">${name}</p>
    </div>
    <div id="parallelogram2" class="figure">
        <p   class="text skewed">${surname}</p>
    </div>
    <div id="parallelogram3" class="figure">
        <p  class="text skewed">${tc}</p>
    </div>
</div>
</body>