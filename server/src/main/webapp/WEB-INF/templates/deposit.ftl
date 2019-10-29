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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>Deposit</title>

</head>

<body style="background-color: cornsilk">

      <#assign logoutUrl = "/logout">
      <#assign walletUrl = "/wallet?command=towallet">
      <#assign marketUrl = "/market?command=tomarket&pair=">
      <#assign toorderUrl = "/order?command=toorders">
      <#assign depositUrl = "/wallet?command=deposit">





<div  style="height:100%; background-color: cornsilk">

    <header>
        <div class="uui-header navigation-header">
            <nav>

                <ul class="uui-navigation nav navbar-nav">
                    <li><a href=${marketUrl}>${market}</a></li>
                    <li><a href=${walletUrl}>${mywallet}</a></li>
                    <li><a href=${toorderUrl}>${myorders}</a></li>
                    <li><a href=${logoutUrl}>${logout}</a></li>

                    <li> <H2 style = "color: aliceblue" >${usertext} ${user.userName} ${deposit} </H2></li>
                </ul>
            </nav>
        </div>

      </header>



    <!--------------------------------------------------------->
    <div class="uui-login-panel" style = "margin-top : 20% ; margin-left : 40% ; background: #7F993A ">

        <FORM action="${depositUrl}" method="post">

            <input style = "margin-top : 20px ;" type="text" class="uui-form-element" id="amount" name="amount" placeholder=${amount} />
            <button type="submit" class="uui-button test-color dark-gray" style="margin-top: 20px;width: 100%;">${deposit} ${coin}</button>

        </FORM>




        <H2 style="color: #222222">${transactionError[transactionerror]}</H2>

    </div>


</div>

<footer style="position: absolute;left: 0;bottom: 0;width: 100%;height: 80px;">
    <div class="uui-footer">
        <div class="footer-logo-copyright">

            <p style="color: aliceblue" class="build">Build version ${buildVersion}</p>
            <p style="color: aliceblue" class="build">Build date  ${buildDate} </p>
        </div>


    </div>
</footer>




</body>
</html>