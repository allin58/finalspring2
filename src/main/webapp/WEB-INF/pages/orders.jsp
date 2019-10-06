<!DOCTYPE HTML>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<%@ taglib prefix="ctg" uri="/WEB-INF/tld/navigation.tld" %>--%>

<html>
<H2>${usertext} ${user.userName} ${myorders} </H2>

  <c:url value="/login?command=logout" var="logoutUrl"/>
  <c:url value="/wallet?command=towallet" var="walletUrl"/>
    <c:url value="?command=tocabinet" var="tocabinet"/>
    <c:url value="/market?command=tomarket" var="marketUrl"/>
    <c:url value="/order?command=toorders" var="toorderUrl"/>
    <c:url value="/order?command=rejectorder&orderid=" var="rejectorderUrl"/>
    <head>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@ include file="/css/style.css" %>
        </style>
<title>Orders</title>
    </head>



    <body>

   <%-- <ctg:navigationtags-menu/>--%>

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

    <div class="container"  style="background-color:#f1f1f1;  left: 35%; overflow: auto;">
    <table>
        <tbody class="scrollable">
        <c:forEach var="order" items ="${orders}"    >
    <c:set var="state">${order.state}</c:set>
    <tr> <td>${order.pair}</td> <td>${order.amount}</td> <td>${order.price}</td> <td>${order.type}</td> <td>${orderState[state]}</td>

        <c:if test = "${order.state == 'active'}">
        <td>
            <FORM action="${rejectorderUrl}+${order.identity}" method="post">
                <BUTTON type="submit">${cancel}</BUTTON>
            </FORM>
        </td>
        </c:if>


    </tr>
</c:forEach>
        </tbody>

    </table>


        </div>

          <footer style ="color : white;
                          position: absolute;
                          bottom: 0;
                          height: 58px;">
           ${buildVersion}   ${buildDate}
          </footer>
    </body>




</html>