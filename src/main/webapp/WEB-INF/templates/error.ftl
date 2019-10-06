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
<title>Error</title>

    </head>

    <body>

      <#assign loginUrl = "/market?command=login">
      <#assign regUrl = "/registration?command=toregistration">
      <#assign changelanguageUrl = "/login?command=changelanguage">

<div  style="height:100%; background-color: cornsilk">

<div class="uui-login-panel" style = "margin-top : 20% ; margin-left : 40% ; background: #7F993A ">
    <p style="color: aliceblue" class="build">ERROR</p>
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