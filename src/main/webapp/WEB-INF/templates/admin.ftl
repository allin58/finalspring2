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
<title>Admin</title>

    </head>

    <body>

      <#assign logoutUrl = "/logout">
      <#assign approvteransactionUrl = "/admin?command=approvetransaction&identity=">
      <#assign rejectransactionUrl = "/admin?command=rejectransaction&identity">



<div  style="height:100%; background-color: cornsilk">


    <header>
    <div class="uui-header navigation-header">
        <nav>

            <ul class="uui-navigation nav navbar-nav">
                <li><a href=${logoutUrl}>${logout}</a></li>
                 <H2 style = "color: aliceblue" >${administrator} ${user.userName}</H2>
            </ul>
        </nav>
    </div>

</header>



<div class="uui-info-panel-horizontal white" style = " background: #7F993A;  ">
   <H2 style="color: black;margin-top:100px;  ">${applicationfordw} </H2>


    <div style="height:300px;overflow-y:scroll;">
        <table class="table">

            <tbody>

                             <#list transactionData as transaction>

                             <tr>
                                 <td>${transaction.user}</td>
                                 <td>${transaction.coin}</td>
                                 <td>${transaction.amount}</td>


                                 <td> <FORM action="${approvteransactionUrl}${transaction.identity}" method="post">
                                     <button type="submit" class="uui-button test-color dark-gray">${approve}</button>
                                 </FORM>
                                 </td>


                                 <td> <FORM action="${rejectransactionUrl}${transaction.identity}&from=admin" method="post">
                                     <button type="submit" class="uui-button test-color dark-gray">${reject}</button>
                                 </FORM>
                                 </td>

                             <tr>

                             </#list>
            </tbody>
        </table>
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

</div>

    </body>
</html>