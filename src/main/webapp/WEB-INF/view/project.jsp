<%@ include file="commons/header.jspf" %>
<%@ include file="commons/navigation.jspf" %>
<div class="container-fluid">
    <div class="row">

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Projeto</h1>
            </div>
            <div class="row g-5">
                <div class="col-md-5 col-lg-4 order-md-last">
                    <h4 class="d-flex justify-content-between align-items-center mb-3">
                        <span class="text-primary">Novo Projeto</span>
                    </h4>
                    <form:form method="post" modelAttribute="projectDto"  class="row g-3 needs-validation"
                               novalidate="novalidate">
                        <form:hidden path="id" />
                        <div class="mb-3">
                            <form:label path="name" class="form-label">Nome do Projeto</form:label>
                            <div class="input-group has-validation">
                                <form:input path="name" type="text" class="form-control" required="required"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <form:label path="description" class="form-label">Descri&ccedil;&atilde;o do Projeto</form:label>
                            <div class="input-group has-validation">
                            <form:input type="text" class="form-control" path="description" required="required"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <form:label path="budget" class="form-label">Or&ccedil;amento Total</form:label>
                            <form:input type="text" class="form-control" path="budget" required="required"/>
                        </div>
                        <div class="mb-3">
                            <form:label path="startDate" class="form-label">Data In&iacute;cio</form:label>
                            <form:input type="text" class="form-control" path="startDate" required="required"/>
                        </div>
                        <div class="mb-3">
                            <form:label path="managerId" class="form-label">Gerente Respons&aacute;vel</form:label>
                            <form:select class="form-select" path="managerId" aria-label="Gerente Responsável">
                                <form:option value="">Selecione</form:option>
                                <c:forEach var="manager" items="${managerList}">
                                    <form:option value="${manager.id}">${manager.name}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="mb-3">
                            <form:label path="expectedEndDate" class="form-label">Previs&atilde;o de T&eacute;rmino</form:label>
                            <form:input type="text" class="form-control" path="expectedEndDate" required="required"/>
                        </div>
                        <div class="mb-3">
                            <form:label path="endDate" class="form-label">Data Real de T&eacute;rmino</form:label>
                            <form:input type="text" class="form-control" path="endDate"/>
                        </div>
                        <div class="mb-3">
                            <form:label path="status" class="form-label">Status</form:label>
                            <form:select class="form-select" path="status" aria-label="Status">
                                <form:option value="">Selecione</form:option>
                                <c:forEach var="status" items="${statusList}">
                                    <form:option value="${status}">${status.description}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="mb-3">
                            <form:label path="risk" class="form-label">Risco</form:label>
                            <form:select class="form-select" path="risk" aria-label="Risco">
                                <form:option value="">Selecione</form:option>
                                <c:forEach var="risk" items="${riskList}">
                                    <form:option value="${risk}">${risk.description}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-success">Salvar</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </main>
    </div>
</div>
<script>
    (function () {
        'use strict'

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation')

        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
    })()
</script>
<%@ include file="commons/footer.jspf" %>