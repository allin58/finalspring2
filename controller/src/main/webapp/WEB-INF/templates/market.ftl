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
    <title>Markets</title>

</head>

<body style="background-color: cornsilk">

      <#assign logoutUrl = "/logout">
      <#assign walletUrl = "/wallet?command=towallet">
      <#assign marketUrl = "/market?command=tomarket&pair=">
      <#assign toorderUrl = "/order?command=toorders">

      <#assign setlimitorderUrl = "/market?command=setlimitorder">
      <#assign executemarketorderUrl = "/market?command=executemarketorder">
      <#assign settypeoforderUrl = "/market?command=settypeoforder">
      <#assign ordermes = "${ordermessage}">




 <#if activepairs?has_content >


<div  style="height:100%; background-color: cornsilk">

    <header>
        <div class="uui-header navigation-header">
            <nav>

                <ul class="uui-navigation nav navbar-nav">
                    <li><a href=${marketUrl}>${market}</a></li>
                    <li><a href=${walletUrl}>${mywallet}</a></li>
                    <li><a href=${toorderUrl}>${myorders}</a></li>
                    <li><a href=${logoutUrl}>${logout}</a></li>

                    <li> <H2 style = "color: aliceblue" >${usertext} ${user.userName} ${market} </H2></li>
                </ul>
            </nav>
        </div>






        <div class="uui-header navigation-header" style="margin-top: 20px ">
            <nav>

                <ul class="uui-navigation nav navbar-nav">
                    <H2 style="float: left; color: aliceblue">${accessiblemarkets} </H2>

  <#list activepairs as pair>

          <li><a href=${marketUrl}+${pair.pair}>${pair.pair}</a></li>
  </#list>

                </ul>
            </nav>
        </div>




    </header>



    <!--------------------------------------------------------->

    <div class="container-fluid" style = "margin-top: 10%;">
        <div class="row">
            <div class="col-sm-4" style="background-color:cornsilk;">
                <div class="uui-login-panel" style = "margin-top : 20% ; margin-left : 5% ; background: #7F993A ">

            ${pair}

            ${transactionError["${ordermessage}"]}




<#if typeoforder == "limit">
 <FORM action="${settypeoforderUrl}" method="post">

     <select name="typeoforder" onchange="this.form.submit()">
         <option value="limit" selected>limit</option>
         <option value="market">market</option>
     </select>
 </FORM>

  <FORM action="${setlimitorderUrl}" method="post">

      <input type="text" class="uui-form-element" id="price" name="price" placeholder=${price} />
      <input type="text" class="uui-form-element" id="amount" name="amount" placeholder=${amount} />

      <button type="submit" name="buybutton" value="buy" class="uui-button test-color dark-gray" style="margin-top: 20px;width: 100%;">${buy}</button>
      <button type="submit" name="sellbutton" value="sell" class="uui-button test-color dark-gray" style="margin-top: 20px;width: 100%;">${sell}</button>

  </FORM>

</#if>


<#if typeoforder == "market">
 <FORM action="${settypeoforderUrl}" method="post">

     <select name="typeoforder" onchange="this.form.submit()">
         <option value="limit" >limit</option>
         <option value="market" selected>market</option>
     </select>
 </FORM>

  <FORM action="${executemarketorderUrl}" method="post">

      <input type="text" class="uui-form-element" id="amount" name="amount" placeholder=${amount} />

      <button type="submit" name="buybutton" value="buy" class="uui-button test-color dark-gray" style="margin-top: 20px;width: 100%;">${buy}</button>
      <button type="submit" name="sellbutton" value="sell" class="uui-button test-color dark-gray" style="margin-top: 20px;width: 100%;">${sell}</button>

  </FORM>

</#if>



            </div>
            </div>
            <div class="col-sm-4" style="background-color:cornsilk;">
                <div class="uui-login-panel" style = "margin-top : 20% ; margin-left : 10% ; background: #7F993A ">




                    <h1>${ask}</h1>




                    <div style="height:300px;overflow-y:scroll;">
                        <table class="table">

                            <thead>
                            <tr> <td>${price}</td> <td>${volume}</td> </tr>

                            </thead>
                            <tbody>

                            <thead>

                             <#list asklist as ask>

                             <tr> <td>${ask.price}</td> <td>${ask.amount}</td> </tr>

                             </#list>

                            </tbody>
                        </table>
                    </div>








                </div>

            </div>
            <div class="col-sm-4" style="background-color:cornsilk;">
                <div class="uui-login-panel" style = "margin-top : 20% ; margin-left : 10% ; background: #7F993A ">

                    <h1>${bid}</h1>
                    <div style="height:300px;overflow-y:scroll;">
                        <table class="table">

                            <thead>
                            <tr> <td>${price}</td> <td>${volume}</td> </tr>

                            </thead>
                            <tbody>

                            <thead>

                             <#list bidlist as bid>

                             <tr> <td>${bid.price}</td> <td>${bid.amount}</td> </tr>

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

  <#else>

<div  style="height:100%; background-color: cornsilk">

    <header>
        <div class="uui-header navigation-header">
            <nav>

                <ul class="uui-navigation nav navbar-nav">
                    <li><a href=${marketUrl}>${market}</a></li>
                    <li><a href=${walletUrl}>${mywallet}</a></li>
                    <li><a href=${toorderUrl}>${myorders}</a></li>
                    <li><a href=${logoutUrl}>${logout}</a></li>

                    <li> <H2 style = "color: aliceblue" >${usertext} ${user.userName} ${market} </H2></li>
                </ul>
            </nav>
        </div>




        <div class="uui-header navigation-header" style="margin-top: 20px ">
            <nav>

                <ul class="uui-navigation nav navbar-nav">
                    <H2 style="float: left; color: aliceblue">${marketError["marketerror"]}</H2>

                </ul>
            </nav>
        </div>

       </div>

    </header>

   </#if>



</body>
</html>