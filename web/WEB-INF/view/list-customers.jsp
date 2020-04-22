<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
   <head>
        <meta charset="UTF-8">
        <title> List Customers </title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
   </head>
   <body>
        <div id="wrapper">
            <div id="header">
                <h2> CRM - Customer Relationship Manager</h2>
            </div>
        </div>
        <div id="container">

            <input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd'; return false;"
                class="add-button"/>

            <form:form method="get" action="search">
                Search customer: <input type="text" name="theCustomerName"/>
                <input type="submit" value="Search" class="add-button"/>
            </form:form>

            <div id="content">
                <table>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                    <!-- loop over and print our customers -->
                    <c:forEach var="tempCustomer" items="${customers}">
                        <c:url var="updateLink" value="/customer/showFormForUpdate">
                            <c:param name="customerId" value="${tempCustomer.id}"/>
                        </c:url>
                        <c:url var="deleteLink" value="/customer/delete">
                            <c:param name="deleteId" value="${tempCustomer.id}"/>
                        </c:url>
                        <tr>
                            <td>${tempCustomer.firstName}</td>
                            <td>${tempCustomer.lastName}</td>
                            <td>${tempCustomer.email}</td>
                            <td><a href="${updateLink}">Update</a> | <a href="${deleteLink}" onclick="if (!(confirm('Are yout sure you want to delete this customer?'))) return false">Delete</a> </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
   </body>
</html>