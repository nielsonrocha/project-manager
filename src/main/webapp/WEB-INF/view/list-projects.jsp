<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigation.jspf"%>
<div class="container-fluid">
    <div class="row">

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Listagem de Projetos</h1>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <a href="/add-project" class="btn btn-primary" role="button">+ Novo Projeto</a>
                </div>

            </div>
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Gerente</th>
                        <th scope="col">Status</th>
                        <th scope="col">Risco</th>
                        <th scope="col">Data In&iacute;cio</th>
                        <th scope="col">Data Fim</th>
                        <th scope="col">A&ccedil;&atilde;o</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="project" items="${projectsList}">
                    <tr>
                        <td>${project.id}</td>
                        <td>${project.name}</td>
                        <td>${project.managerName}</td>
                        <td>${project.status.description}</td>
                        <td>${project.risk.description}</td>
                        <td>${project.startDate}</td>
                        <td>${project.endDate}</td>
                        <td>
                            <a href="/edit-project?id=${project.id}" class="btn btn-info btn-sm" role="button">Editar</a>
                            <c:if test="${project.canDelete}">
                                <a onclick="remove(${project.id})" class="btn btn-danger btn-sm" role="button">Excluir</a>
                            </c:if>
                            <a href="/project-members?projectId=${project.id}" class="btn btn-secondary btn-sm" role="button">Membros</a>
                        </td>
                    </tr>
                    </tbody>
                    </c:forEach>
                </table>
            </div>
        </main>
    </div>
</div>
<script>
function remove(id) {
    var conf = confirm("Deseja realmente excluir o registro?");
    if (conf) {
        window.location.href = "/delete-project?id=" + id;
    }
}
</script>
<%@ include file="commons/footer.jspf"%>