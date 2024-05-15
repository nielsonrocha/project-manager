<%@ include file="commons/header.jspf" %>
<%@ include file="commons/navigation.jspf" %>
<div class="container-fluid">
    <div class="row">

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Membros do Projeto ${projectDto.name}</h1>
            </div>
            <c:if test="${messageError}">
                <div class="alert alert-danger" role="alert">

                </div>
            </c:if>

            <form:form  method="post" modelAttribute="personDto" class="row g-3 needs-validation"
                        novalidate="novalidate">
                <div class="row">
                    <div class="col-md-6">
                        <form:label path="id" class="form-label">Selecione o Funcion&aacute;rio</form:label>
                        <form:select class="form-select" path="id">
                            <form:option value="">Selecione</form:option>
                            <c:forEach var="person" items="${personList}">
                                <form:option value="${person.id}">${person.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-success">Adicionar</button>
                    </div>
                </div>
            </form:form>
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nome</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="member" items="${membersList}">
                        <tr>
                            <th scope="col">${member.person.id}</th>
                            <th scope="col">${member.person.name}</th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>
<%@ include file="commons/footer.jspf" %>