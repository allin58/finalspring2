<!DOCTYPE HTML>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/time.tld" %>

<html>




     <c:url value="/login?command=logout" var="logoutUrl"/>
    <c:url value="/wallet?command=towallet" var="walletUrl"/>
    <c:url value="?command=tocabinet" var="tocabinet"/>
    <c:url value="/market?command=tomarket&pair=" var="marketUrl"/>
    <c:url value="/order?command=toorders" var="toorderUrl"/>
    <c:url value="/order?command=setlimitorder" var="setlimitorderUrl"/>
    <c:url value="/market?command=executemarketorder" var="executemarketorderUrl"/>
    <c:url value="/market?command=settypeoforder" var="settypeoforderUrl"/>

    <head>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@ include file="/css/style.css" %>
        </style>
<title>Markets</title>
    </head>
    <body>

    <H2>${usertext} ${user.userName} ${market}</H2>

    <div class="container"  style="background-color:#f1f1f1;  width: 100%; top: 5%; left: 0%">


        <FORM action="${marketUrl}" method="post" style="float: left; padding: 12px 20px; ">
            <BUTTON type="submit">${market}</BUTTON>
        </FORM>


        <FORM action="${walletUrl}" method="post" style="float: left;  padding: 12px 20px;"  >
            <BUTTON type="submit">${mywallet}</BUTTON>
        </FORM>

        <FORM action="${toorderUrl}" method="post" style="float: left;padding: 12px 20px; ">
            <BUTTON type="submit" >${myorders}</BUTTON>
        </FORM>

        <FORM action="${logoutUrl}" method="post" style="float: left; padding: 12px 20px; ">
            <BUTTON type="submit">${logout}</BUTTON>
        </FORM>


    </div>

    <div class="container"  style="background-color:#f1f1f1;  width: 100%; top: 25%; left: 0%;">

        <H2 style="float: left; color: black">${accessiblemarkets} </H2>

        <c:set var="messagemarketerror">${marketerror}</c:set>
        ${marketError[messagemarketerror]}



<c:forEach var = "pair" items = "${activepairs}">
     <FORM action="${marketUrl}+${pair.pair}" method="post" style="float: left; padding: 12px 20px;">
         <BUTTON type="submit">${pair.pair}</BUTTON>
     </FORM>


</c:forEach>
    </div>



    <c:if test="${pair != null}">
    <div class="container"  style="background-color:#f1f1f1 ;  left: 3%; top: 45%">





    ${pair}
        <ctg:info-time/>
    <c:set var="ordermes">${ordermessage}</c:set>
    ${transactionError[ordermes]}




        <c:if test="${typeoforder == 'limit'}">
        <FORM action="${settypeoforderUrl}" method="post">

        <select name="typeoforder" onchange="this.form.submit()">
            <option value="limit" selected>limit</option>
            <option value="market">market</option>
        </select>
        </FORM>

    <FORM action="${setlimitorderUrl}" method="post">

         <INPUT type="text" id="price" name="price" placeholder=${price}>
         <INPUT type="text" id="amount" name="amount" placeholder=${amount}>

        <button type="submit" name="buybutton" value="buy">${buy}</button>
        <button type="submit" name="sellbutton" value="sell">${sell}</button>
    </FORM>

        </c:if>



        <c:if test="${typeoforder == 'market'}">
            <FORM action="${settypeoforderUrl}" method="post">

                <select name="typeoforder" onchange="this.form.submit()">
                    <option value="limit" >limit</option>
                    <option value="market" selected>market</option>
                </select>
            </FORM>


            <FORM action="${executemarketorderUrl}" method="post">
                <INPUT type="text" id="amountm" name="amount" placeholder=${amount}>
                <button type="submit" name="buybutton" value="buy">${buy}</button>
                <button type="submit" name="sellbutton" value="sell">${sell}</button>
            </FORM>


        </c:if>





    </div>



    <div class="container"  style="background-color:#f1f1f1 ;  left: 40%; top: 45%; width: 10%; ">
        <h1>${ask}</h1>
        <hr>
        <table>
            <tbody class="scrollable">
            <tr> <td>${price}</td> <td>${volume}</td> </tr>
            <c:forEach var = "ask" items = "${asklist}">
                <tr> <td>${ask.price}</td> <td>${ask.amount}</td> </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>


    <div class="container"  style="background-color:#f1f1f1 ;  left: 60%; top: 45%; width: 10%;">
        <h1>${bid}</h1>
        <hr>
        <table>
            <tbody class="scrollable">
            <tr> <td>${price}</td> <td>${volume}</td> </tr>
            <c:forEach var = "bid" items = "${bidlist}">
                <tr> <td>${bid.price}</td> <td>${bid.amount}</td> </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>




   </c:if>


  <footer style ="color : white;
                  position: absolute;
                  bottom: 0;
                  height: 58px;">
   ${buildVersion}   ${buildDate}
  </footer>


</body>





</html>