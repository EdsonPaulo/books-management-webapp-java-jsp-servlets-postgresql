<%-- 
    Document   : navbar
    Created on : 20/01/2022, 04:10:32
    Author     : edsonpaulo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="navbar px-5 justify-content-around bg-primary">

    <div class="btn-group">
        <button type="button" class="btn btn-primary dropdown-toggle text-left" data-toggle="dropdown">
            Pessoas
        </button>
        <div class="dropdown-menu">
            <h6 class="dropdown-header">Pessoa</h6>
            <a class="dropdown-item"  href="<%=request.getContextPath()%>/person/list.jsp">Listar pessoas</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/person/new.jsp">Adicionar pessoa</a>
            <div class="dropdown-divider"></div>

            <h6 class="dropdown-header">Leitor</h6>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Listar leitores</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Adicionar leitor</a>
            <div class="dropdown-divider"></div>

            <h6 class="dropdown-header">Autor</h6>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Listar autores</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Adicionar autor</a>
        </div>
    </div>

    <div class="btn-group">
        <button type="button" class="btn btn-primary dropdown-toggle text-left" data-toggle="dropdown">
            Editoras
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=request.getContextPath()%>/publisher/list.jsp">Listar editoras</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/publisher/new.jsp">Adicionar editora</a>
        </div>
    </div>

    <div class="btn-group">
        <button type="button" class="btn btn-primary dropdown-toggle text-left" data-toggle="dropdown">
            Livro
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=request.getContextPath()%>/book/list.jsp">Listar livros</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/book/new.jsp">Adicionar livro</a>
        </div>
    </div>

    <div class="btn-group">
        <button type="button" class="btn btn-primary dropdown-toggle text-left" data-toggle="dropdown">
            Empr??stimo
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=request.getContextPath()%>/book-request/list.jsp">Listar requisi????o</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/book-request/new.jsp">Adicionar requisi????o</a>
        </div>
    </div>


    <div class="btn-group">
        <button type="button" class="btn btn-primary dropdown-toggle text-left" data-toggle="dropdown">
            G??nero
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Listar g??neros</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Adicionar g??nero</a>
        </div>
    </div>

    <div class="btn-group">
        <button type="button" class="btn btn-primary dropdown-toggle text-left" data-toggle="dropdown">
            Endere??o
        </button>
        <div class="dropdown-menu">
            <h6 class="dropdown-header">Pa??s</h6>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Listar pa??ses</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Adicionar pa??s</a>

            <h6 class="dropdown-header">Prov??ncia</h6>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Listar prov??ncias</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Adicionar prov??ncia</a>
            <div class="dropdown-divider"></div>

            <h6 class="dropdown-header">Mun??cipio</h6>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Listar mun??cipios</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Adicionar mun??cipio</a>
            <div class="dropdown-divider"></div>

            <h6 class="dropdown-header">Comuna</h6>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Listar comunas</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Adicionar comuna</a>
            <div class="dropdown-divider"></div>
        </div>
    </div>

    <div class="btn-group">
        <button type="button" class="btn btn-primary dropdown-toggle text-left" data-toggle="dropdown">
            Livro (atributos)
        </button>
        <div class="dropdown-menu">
            <h6 class="dropdown-header">Categoria</h6>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Listar categorias</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Adicionar categorias</a>

            <h6 class="dropdown-header">Estado</h6>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Listar estados</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Adicionar estado</a>
            <div class="dropdown-divider"></div>

            <h6 class="dropdown-header">Classifica????o</h6>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Listar classifica????es</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Adicionar classifica????o</a>
            <div class="dropdown-divider"></div>

            <h6 class="dropdown-header">Descritores</h6>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Listar descritores</a>
            <a class="dropdown-item" href="<%=request.getContextPath()%>/#">Adicionar descritores</a>
        </div>
    </div>
</div>
