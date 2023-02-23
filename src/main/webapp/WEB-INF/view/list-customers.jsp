<%--
  Created by IntelliJ IDEA.
  User: HICHAM
  Date: 1/14/2023
  Time: 11:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.bojji.util.SortUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>List Customers</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>Customer Relationship Manager</h2>
        </div>
    </div>

    <input type="button" value="Add Customer"
           onclick="window.location.href='showFormForAdd'; return false"
           class="add-button">

    <!-- add a search box -->
    <form:form action="search" method="GET">
        <label for="input-search">Search customer</label>
        <input id="input-search" type="text" name="searchName">
        <input type="submit" value="Search" class="add-button">
    </form:form>

    <!-- construct a sort link for first name -->
    <c:url var="sortByFirstName" value="/customer/list">
        <c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" />
    </c:url>

    <!-- construct a sort link for last name -->
    <c:url var="sortByLasttName" value="/customer/list">
        <c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>" />
    </c:url>

    <!-- construct a sort link for email -->
    <c:url var="sortByEmail" value="/customer/list">
        <c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>" />
    </c:url>

    <div id="container">
        <div id="content">
            <table>
                <thead>
                    <tr>
                        <th><a href="${sortByFirstName}">First Name</a></th>
                        <th><a href="${sortByLasttName}">Last Name</a></th>
                        <th><a href="${sortByEmail}">Email</a></th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="customer" items="${customers}">
                        <%-- construct an "update" link with customer id --%>
                        <c:url var="updateLink" value="/customer/showFormForUpdate">
                            <c:param name="customerId" value="${customer.id}" />
                        </c:url>

                        <c:url var="deleteLink" value="/customer/delete">
                            <c:param name="customerId" value="${customer.id}" />
                        </c:url>

                        <tr>
                            <td>${customer.firstName}</td>
                            <td>${customer.lastName}</td>
                            <td>${customer.email}</td>
                            <td>
                                <!-- display the update link -->
                                <a href="${updateLink}">Update</a>
                                |
                                <!-- display the delete link -->
                                <a href="${deleteLink}"
                                   onclick="return (confirm('Are you sure you want to delete this customer ?'))">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
