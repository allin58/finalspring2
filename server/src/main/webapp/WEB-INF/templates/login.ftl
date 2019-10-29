<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="utf-8" />
        <title></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <!-- jQuery Core -->
        <script src="uui/js/lib/jquery-1.12.0.min.js"></script>

        <!-- Bootstrap Core -->
        <link rel="stylesheet" href="uui/bootstrap/css/bootstrap.min.css" />
        <script src="uui/bootstrap/js/bootstrap.min.js"></script>

        <!-- EPAM UUI JavaScript Core -->
        <script src="uui/js/uui-core.min.js" type="text/javascript"></script>

        <!-- EPAM UUI Styles Core -->
        <link rel="stylesheet" href="uui/css/uui-all.css" />
        <!-- Your custom CSS Styles -->
        <link rel="stylesheet" href="uui/css/custom-styles.css" />

        <!-- Scroll for UUI Sidebar -->
        <link rel="stylesheet" href="uui/css/lib/components/jquery.mCustomScrollbar.min.css" />
        <script src="uui/js/lib/components/jquery.mCustomScrollbar.concat.min.js"></script>

        <link rel="stylesheet" href="uui/css/lib/components/bootstrap-select.min.css" />
        <script src="uui/js/lib/components/bootstrap-select.min.js"></script>
        <script src="uui/js/uui/uui-dropdown.js"></script>
        <title>Login</title>


    </head>

    <body>

      <#assign loginUrl = "/">
      <#assign regUrl = "/registration">
      <#assign changelanguageUrl = "/changelanguage">

<div  style="height:100%; background-color: cornsilk">

<div class="uui-login-panel" style = "margin-top : 15% ; margin-left : 40% ; background: #7F993A ">
      <H2 style="color: #222222">${cryptocurrencymarket}</H2>

     <FORM action="${changelanguageUrl}" method="post"  >

<#if language == "en" >
 <select class="selectpicker uui-form-element" name="language" onchange="this.form.submit()"  >
     <option value="en" selected>en</option>
      <option value="ru">ru</option>
      <option value="es">es</option>
</select>
</#if>

<#if language == "ru" >
 <select class="selectpicker uui-form-element" name="language" onchange="this.form.submit()"  >
     <option value="en" >en</option>
      <option value="ru" selected>ru</option>
      <option value="es">es</option>
</select>
</#if>

<#if language == "es" >
 <select class="selectpicker uui-form-element" name="language" onchange="this.form.submit()"  >
     <option value="en" >en</option>
      <option value="ru">ru</option>
      <option value="es" selected>es</option>
</select>
</#if>


 </FORM>


    <FORM  method="post" style="margin-top: 20px">

        <input type="text" class="uui-form-element" id="username" name="username" placeholder=${username} />
        <input type="text" class="uui-form-element" type="password" id="password" name="password" placeholder=${password} />

        <button type="submit" class="uui-button test-color dark-gray" style="margin-top: 20px;width: 100%;">${login}</button>

        </FORM>


       <FORM action="${regUrl}" method="post" style="margin-top: 20px; margin-bottom: 20px;width: 100%;">
                <button type="submit" class="uui-button test-color dark-gray" style="width: 100%">${registration} </button>
        </FORM>





    <H2 style="color: #222222">${loginFailed["${loginmessage}"]}</H2>
</div>





      <footer style="position: absolute;left: 0;bottom: 0;width: 100%;height: 80px;">
         <div class="uui-footer">
        <div class="footer-logo-copyright">

           <p style="color: aliceblue" class="build">Build version ${buildVersion}</p>
           <p style="color: aliceblue" class="build">Build date  ${buildDate} </p>

        </div>






    </div>
</footer>

</div>
        
    </body>
</html>