<%-- Document : publisher/new Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

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
        <a href="<%=request.getContextPath()%>/publisher/list.jsp" class="btn btn-primary btn-sm m-4"><< Voltar</a>

        <div class="h-100 container d-flex justify-content-center align-items-start">
            <div class="card px-5 py-3" style="width: 100%;">

                <h5 class="text-center mb-3">Nova Editora</h5>

                    <form class="form-container" method="POST"
                          action="<%=request.getContextPath()%>/publisher-servlet">

                        <div class="row">
                            <div class="form-group col-4">
                                <label for="name" class="required">Nome</label>
                                <input name="name" id="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group  col-4">
                                <label for="nif" class="required">NIF</label>
                                <input name="nif" id="nif" type="text" class="form-control" required>
                            </div> 
                            <div class="form-group col-4">
                                <label for="fax" class="required">Fax</label>
                                <input name="fax" id="fax" type="text" class="form-control" required>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-4">
                                <label for="phone1" class="required">Telefone principal</label>
                                <input name="phone1" id="phone1" type="tel" class="form-control" required>
                            </div>
                            <div class="form-group col-4">
                                <label for="phone2">Telefone secundário (opcional)</label>
                                <input name="phone2" id="phone2" type="tel" class="form-control">
                            </div>   
                        </div>

                        <div class="row">
                            <div class="form-group col-4">
                                <label for="email1" class="required">Email principal</label>
                                <input name="email1" id="email1" type="email" class="form-control" required>
                            </div>  
                            <div class="form-group col-4">
                                <label for="email2">Email secundário (opcional)</label>
                                <input name="email2" id="email2" type="email" class="form-control">
                            </div>                        
                        </div>

                        <p class="h6 my-2 text-center text-muted">Morada</p> 

                        <div class="row mt-3">
                            <div class="form-group col-4">
                                <label for="country" class="required">Pais</label>
                                <%=obj.getSelectBox(connection, "pais", "country", -1)%>
                            </div>
                            <div class="form-group col-4">
                                <label for="province" class="required">Provincia</label>
                                <select id="province" name="province" class="form-control"> 
                                    <option>-- Selecione um pais antes --</option>
                                </select>
                            </div>     
                            <div class="form-group  col-4">
                                <label for="municipality" class="required">Municipio</label>
                                <select id="municipality" name="municipality" class="form-control"> 
                                    <option>-- Selecione uma provincia antes --</option>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group  col-3">
                                <label for="commune" class="required">Comuna</label>
                                <select id="commune" name="commune" class="form-control"> 
                                    <option>-- Selecione um municipio antes --</option>
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
                            √  Confirmar
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

            fetch(getContextPath() + '/publisher-servlet?' + new URLSearchParams({operation, id}), {method: 'GET'})
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

        // -- Handling changes for selectboxes --

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