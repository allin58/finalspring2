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
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
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

<link rel="stylesheet" href="css/lib/components/bootstrap-select.min.css" />
<script src="/js/lib/components/bootstrap-select.min.js"></script>
<script src="/js/uui/uui-dropdown.js"></script>
<title>Registration</title>

    </head>

    <body>

      <#assign regUrl = "/registration?command=registration">


<div  style="height:100%; background-color: cornsilk">

<div class="uui-login-panel" style = "margin-top : 20% ; margin-left : 40% ; background: #7F993A ">
      <H2 style="color: #222222">${registration}</H2>







      <FORM action="/registration?command" method="post" style="margin-top: 20px">

        <input type="text" class="uui-form-element" id="username" name="username" placeholder=${username} />
        <input type="text" class="uui-form-element" id="name" name="name" placeholder=${name} />
        <input type="text" class="uui-form-element" id="surname" name="surname" placeholder=${surname} />
        <input type="text" class="uui-form-element" id="password" name="password" placeholder=${password} />


        <button type="submit" class="uui-button test-color dark-gray" style="margin-top: 20px;width: 100%;">${approve}</button>

        </FORM>


<H2 style="color: #222222">${registrationFailed["${registrationmessage}"]}</H2>

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