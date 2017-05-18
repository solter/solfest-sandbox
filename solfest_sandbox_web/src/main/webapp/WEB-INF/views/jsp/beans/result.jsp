<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en"> 
    <jsp:include page="../fragments/header.jsp" />

    <body>

        <div class="container">
            <h1>Run Result</h1>
            <p>
            ${result}
            </p>
        </div>

        <jsp:include page="../fragments/footer.jsp" />

    </body>
</html>
