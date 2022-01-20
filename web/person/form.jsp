<%-- Document : person Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

<%@page import="ucan.conection.DBConnection"%>
<%@page import="ucan.utils.HtmlObj" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ include file="../partials/html-head.jsp" %>  
<html>
    <%
        HtmlObj obj = new HtmlObj();
        DBConnection connection = new DBConnection();
    %>
    <body>
        <%@ include file="../partials/navbar.jsp" %>  

        <a href="<%=request.getContextPath()%>/person/list.jsp" class="btn btn-primary btn-sm m-4"><< Voltar</a>

        <div class="h-100 container d-flex justify-content-center align-items-start">
            <div class="card p-5" style="width: 100%;">
                <form class="form-container" action="person" method="POST">
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
                            <%=obj.getSelectBox(connection, "sexo", "gender")%>
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
                            <%=obj.getSelectBox(connection, "pais", "country")%>
                        </div>
                        <div class="form-group col-4">
                            <label for="province" class="required">Provincia</label>
                            <select id="province" name="province" class="form-control"> 
                                <option>-- Selecione um pais primeiro --</option>
                            </select>
                        </div>     
                        <div class="form-group  col-4">
                            <label for="municipality" class="required">Municipio</label>
                            <select id="municipality" name="municipality" class="form-control"> 
                                <option>-- Selecione uma provincia primeiro --</option>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group  col-3">
                            <label for="commune" class="required">Comuna</label>
                            <select id="commune" name="commune" class="form-control"> 
                                <option>-- Selecione um municipio primeiro --</option>
                            </select>
                        </div>
                        <div class="form-group col-3">
                            <label for="district">Bairro</label>
                            <input name="district" id="district" type="text" class="form-control">
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
                        √ Adicionar
                    </button>
                </form>
            </div>
        </div>
    </body>

    <%  connection.closeConnection();%>

    <script type="text/javascript">

        // get current context path or base url path name 
        function getContextPath() {
            return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
        }

        function fetchList(operation, id) {
            const selectElement = document.getElementById(operation);

            fetch(getContextPath() + '/person-servlet?' + new URLSearchParams({operation, id}), {method: 'GET'})
                    .then(response => response.json())
                    .then(data => {
                        data.forEach((item) => {
                            selectElement.options[selectElement.options.length] = new Option(item.name, item[operation + 'Id']);
                        });
                    })
                    .catch(() => {
                        selectElement.options[0] = new Option('Sem dados disponíveis', '');
                    });
        }

        function clearProvinceSelect() {
            const provinceNode = document.getElementById("province");
            while (provinceNode.firstChild)
                provinceNode.removeChild(provinceNode.lastChild);
            provinceNode.options[provinceNode.options.length] = new Option('Selecione uma provincia');
        }

        function clearMunicipalitySelect() {
            const municipalityNode = document.getElementById("municipality");
            while (municipalityNode.firstChild)
                municipalityNode.removeChild(municipalityNode.lastChild);
            municipalityNode.options[municipalityNode.options.length] = new Option('Selecione um município');
        }

        function clearCommuneSelect() {
            const communeNode = document.getElementById("commune");
            while (communeNode.firstChild)
                communeNode.removeChild(communeNode.lastChild);
            communeNode.options[communeNode.options.length] = new Option('Selecione uma comuna');
        }

        // -- Handling changes of selectboxes --

        document.getElementById('country').addEventListener('change', function (event) {
            clearProvinceSelect();
            clearMunicipalitySelect();
            clearCommuneSelect();
            fetchList('province', event.target.value);
        });

        document.getElementById('province').addEventListener('change', function (event) {
            clearMunicipalitySelect();
            clearCommuneSelect();
            fetchList('municipality', event.target.value);
        });

        document.getElementById('municipality').addEventListener('change', function (event) {
            clearCommuneSelect();
            fetchList('commune', event.target.value);
        });
    </script>
</html>