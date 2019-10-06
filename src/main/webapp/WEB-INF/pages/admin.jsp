<!DOCTYPE HTML>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>

    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        <%@ include file="/css/style.css" %>
    </style>

<title>Admin</title>
</head>

<body>
    <H2>${administrator} ${user.userName}</H2>


    <c:url value="/login?command=logout" var="logoutUrl"/>
    <c:url value="?command=towallet" var="walletUrl"/>
    <c:url value="?command=tocabinet" var="tocabinet"/>
    <c:url value="?command=tomarket" var="marketUrl"/>
       <c:url value="/admin?command=approvetransaction&identity=" var="approvteransactionUrl"/>
    <c:url value="/admin?command=rejectransaction&identity=" var="rejectransactionUrl"/>
    <c:url value="?command=toorders" var="toorderUrl"/>


    <div class="container"  style="background-color:#f1f1f1; position: fixed; width: 100%; background: white;
    top: 5%;  left: 0%;">



    <FORM action="${logoutUrl}" method="post">
        <BUTTON type="submit" style="width: 10%;">${logout} </BUTTON>
    </FORM>

      </div>

    <div class="container"  style="background-color:#f1f1f1">
        <H2 style="color: black">${applicationfordw} </H2>
    <table>
        <tbody class="scrollable">
        <c:forEach var="transaction" items="${transactionData}" >

            <c:set var="type">${transaction.type}</c:set>
            <tr> <td>${transaction.user}</td> <td>${transaction.coin}</td> <td>${transaction.amount}</td> <td>${transactionType[type]}</td>


                <td>
                    <FORM action="${approvteransactionUrl}+${transaction.identity}" method="post">
                        <BUTTON type="submit">${approve}</BUTTON>
                    </FORM>
                </td>

                <td>
                    <FORM action="${rejectransactionUrl}+${transaction.identity}+&from=admin" method="post">
                        <BUTTON type="submit">${reject}</BUTTON>
                    </FORM>
                </td>

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