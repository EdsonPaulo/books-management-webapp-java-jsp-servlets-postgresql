<%-- Document : person Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

<%@page import="ucan.utils.HtmlObj" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biblioteca UCAN - Formul]ario Pessoa</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./css/bootstrap.min.css" />
        <link rel="stylesheet" href="./css/styles.css" />
        <script src="./js/jquery-3.4.1.min.js"></script>
        <script src="./js/popper.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
    </head>

    <% HtmlObj obj = new HtmlObj();%>

    <body>
        <button type="button" class="btn btn-primary btn-sm m-4">
            <a href="." class="text-light text-decoration-none"><< Voltar</a>
        </button>

        <div class="h-100 container d-flex justify-content-center align-items-start">
            <div class="card p-5" style="width: 100%;">
                <form class="form-container" action="pessoa" method="POST">
                    <div class="row">
                        <div class="form-group col-4">
                            <label for="name" class="required">Nome</label>
                            <input name="name" id="name" type="text" class="form-control" required>
                        </div>
                        <div class="form-group  col-4">
                            <label for="surname" class="required">Sobrenome</label>
                            <input name="surname" id="surname" type="text" class="form-control" required>
                        </div> 
                        <div class="form-group col-4">
                            <label for="bi" class="required">BI</label>
                            <input name="bi" id="bi" type="text" class="form-control" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-3">
                            <label for="birthDate" class="required">Data Nascimento</label>
                            <input name="birthDate" id="birthDate" type="date" class="form-control" required>
                        </div> 
                        <div class="form-group col-3">
                            <label for="gender" class="required">Genero</label>
                            <%=obj.getSelectBox("sexo", "gender")%>
                        </div>
                        <div class="form-group col-3">
                            <label for="email" class="required">Email</label>
                            <input name="email" id="email" type="text" class="form-control" required>
                        </div>
                        <div class="form-group col-3">
                            <label for="phone" class="required">Telefone</label>
                            <input name="phone" id="phone" type="text" class="form-control" required>
                        </div>
                    </div>

                    <p class="mt-4 text-muted">Morada</p> 

                    <div class="row mt-3">
                        <div class="form-group col-4">
                            <label for="country" class="required">Pais</label>
                            <%=obj.getSelectBox("pais", "country")%>
                        </div>
                        <div class="form-group col-4">
                            <label for="province" class="required">Provincia</label>
                            <%=obj.getSelectBox("provincia", "province")%>
                        </div>     
                        <div class="form-group  col-4">
                            <label for="municipality" class="required">Municipio</label>
                            <%=obj.getSelectBox("municipio", "municipality")%>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group  col-3">
                            <label for="commune" class="required">Comuna</label>
                            <%=obj.getSelectBox("comuna", "commune")%>
                        </div>
                        <div class="form-group  col-3">
                            <label for="district" class="required">Bairro</label>
                            <%=obj.getSelectBox("bairro", "district")%>
                        </div>                    
                        <div class="form-group col-3">
                            <label for="street">Rua</label>
                            <input name="street" id="street" type="text" class="form-control">
                        </div>
                        <div class="form-group col-3">
                            <label for="houseNum">Nº da casa</label>
                            <input name="houseNum" id="houseNum" type="number" class="form-control">
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary my-2 float-right">
                        Adicionar
                    </button>
                </form>
            </div>
        </div>
    </body>
</html>