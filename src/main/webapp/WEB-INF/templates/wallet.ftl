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
    <title>Wallet</title>

</head>

<body>

      <#assign logoutUrl = "/logout">
      <#assign walletUrl = "/wallet?command=towallet">
      <#assign marketUrl = "/market?command=tomarket&pair=">
      <#assign toorderUrl = "/order?command=toorders">
      <#assign todepositUrl = "/wallet?command=todeposit">
      <#assign towithdrawUrl = "/wallet?command=towithdraw">
      <#assign rejectransactionUrl = "/wallet?command=rejectransaction&identity">





<div  style="height:100%; background-color: cornsilk">

    <header>
        <div class="uui-header navigation-header">
            <nav>

                <ul class="uui-navigation nav navbar-nav">
                    <li><a href=${marketUrl}>${market}</a></li>
                    <li><a href=${walletUrl}>${mywallet}</a></li>
                    <li><a href=${toorderUrl}>${myorders}</a></li>
                    <li><a href=${logoutUrl}>${logout}</a></li>

                    <li> <H2 style = "color: aliceblue" >${usertext} ${user.userName} ${mywallet} </H2></li>
                </ul>
            </nav>
        </div>

      </header>



    <!--------------------------------------------------------->
    <div class="container-fluid" style = "margin-top: 10%;">
        <div class="row">
            <div class="col-sm-4" style="background-color:cornsilk;">
                <div class="uui-login-panel" style = "margin-top : 20% ; margin-left : 5% ; background: #7F993A ">

                    <table>

                        <tr> <td>Bitcoin</td> <td>${wallet.btc}</td>
                            <td>
                                <FORM action="${todepositUrl}+&coin=BTC" method="post">
                                    <button type="submit" class="uui-button test-color dark-gray">${deposit}</button>

                                </FORM>
                            </td>

                            <td>
                                <FORM action="${towithdrawUrl}+&coin=BTC" method="post">
                                    <button type="submit" class="uui-button test-color dark-gray">${withdraw}</button>

                                </FORM>
                            </td>

                        </tr>

                        <tr> <td>Ethereum</td> <td>${wallet.eth}</td>
                            <td>
                                <FORM action="${todepositUrl}+&coin=ETH" method="post">
                                    <button type="submit" class="uui-button test-color dark-gray">${deposit}</button>

                                </FORM>
                            </td>

                            <td>
                                <FORM action="${towithdrawUrl}+&coin=ETH" method="post">
                                    <button type="submit" class="uui-button test-color dark-gray">${withdraw}</button>
                                </FORM>
                            </td>

                        </tr>
                        <tr> <td>Tether</td> <td>${wallet.usdt}</td>

                            <td>
                                <FORM action="${todepositUrl}+&coin=USDT" method="post">
                                    <button type="submit" class="uui-button test-color dark-gray">${deposit}</button>
                                </FORM>
                            </td>

                            <td>
                                <FORM action="${towithdrawUrl}+&coin=USDT" method="post">
                                    <button type="submit" class="uui-button test-color dark-gray">${withdraw}</button>
                                </FORM>
                            </td>

                        </tr>

                    </table>




                </div>
            </div>

            <div class="col-sm-4" style="background-color:cornsilk;">
                <div class="uui-info-panel-horizontal white" style = "margin-top : 20% ; margin-left : 5% ; background: #7F993A ">

                    <div style="height:300px;overflow-y:scroll;">
                        <table class="table">

                            <tbody>

                             <#list transactions as transaction>

                             <tr>
                                 <td>${transaction.coin}</td>
                                 <td>${transaction.amount}</td>
                                 <td>${transactionType["${transaction.type}"]}</td>
                                 <td>${transactionStatus["${transaction.status}"]}</td>
                                 <#if transaction.status == "pending">
                                 <td >
                                     <FORM action="${rejectransactionUrl}=${transaction.identity}&from=wallet" method="post">
                                       <button type="submit" class="uui-button test-color dark-gray">${cancel}</button>
                                     </FORM>
                                 </td>
                                 </#if>
                             <tr>

                             </#list>
                            </tbody>
                        </table>
                    </div>




                </div>
            </div>
        </div>
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