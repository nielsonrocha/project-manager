<%@ include file="commons/header.jspf" %>
<%@ include file="commons/navigation.jspf" %>
<div class="container-fluid">
    <div class="row">
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <h3>Ocorreu um erro inesperado</h3>
            <div class="alert alert-danger" role="alert">
                ${exception.message}
            </div>
            <a href="${url}" class="btn btn-primary" role="button">Voltar</a>
        </main>

    </div>
</div>
<%@ include file="commons/footer.jspf" %>