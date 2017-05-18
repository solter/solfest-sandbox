<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en"> 
    <jsp:include page="../fragments/header.jsp" />

    <body>

        <div class="container">

            <h1>Runnable Beans</h1>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Bean Name</th>
                        <th>Run Now</th>
                    </tr>
                </thead>

                <c:forEach var="bean_name" items="${bean_list}">
                <tr>
                    <td>${bean_name}</td>
                    <td>
                        <!-- Set up a button to redirect to the url to run the bean -->
                        <spring:url value="/beans/run/${bean_name}" var="beanUrl" />
                        <button class="btn btn-primary" onclick="location.href='${beanUrl}'">New Run</button>
                    </td>
                </tr>
                </c:forEach>
            </table>

        </div>

        <jsp:include page="../fragments/footer.jsp" />

    </body>
</html>
