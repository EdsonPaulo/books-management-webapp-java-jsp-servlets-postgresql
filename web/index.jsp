<%-- 
    Document   : index
    Created on : 08/01/2022, 20:14:13
    Author     : edsonpaulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biblioteca UCAN - By EdsonPaulo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./css/bootstrap.min.css"/>         
        <link rel="stylesheet" href="./css/styles.css"/> 
        <script src="./js/jquery-3.4.1.min.js" ></script>
        <script src="./js/popper.min.js" ></script>
        <script src="./js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="sidebar bg-primary">
            <p class="h5 text-light">Menu</p>

            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle text-left" data-toggle="dropdown">
                    Livro
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Listar livros</a>
                    <a class="dropdown-item" href="#">Adicionar livro</a>
                </div>
            </div>

            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle text-left" data-toggle="dropdown">
                    Pessoas
                </button>
                <div class="dropdown-menu">
                    <h6 class="dropdown-header">Pessoa</h6>
                    <a class="dropdown-item" href="#">Listar pessoas</a>
                    <a class="dropdown-item" href="formulario-pessoa.jsp">Adicionar pessoa</a>
                    <div class="dropdown-divider"></div>

                    <h6 class="dropdown-header">Leitor</h6>
                    <a class="dropdown-item" href="#">Listar leitores</a>
                    <a class="dropdown-item" href="#">Adicionar leitor</a>
                    <div class="dropdown-divider"></div>


                    <h6 class="dropdown-header">Autor</h6>
                    <a class="dropdown-item" href="#">Listar autores</a>
                    <a class="dropdown-item" href="#">Adicionar autor</a>
                </div>
            </div>

            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle text-left" data-toggle="dropdown">
                    Empréstimo
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Listar empréstimos</a>
                    <a class="dropdown-item" href="#">Adicionar empréstimo</a>
                </div>
            </div>

            <p class="mt-4 h5 text-light">Dependências</p>

            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle text-left" data-toggle="dropdown">
                    Gênero
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Listar gêneros</a>
                    <a class="dropdown-item" href="#">Adicionar gênero</a>
                </div>
            </div>

            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle text-left" data-toggle="dropdown">
                    Endereço
                </button>
                <div class="dropdown-menu">
                    <h6 class="dropdown-header">País</h6>
                    <a class="dropdown-item" href="#">Listar países</a>
                    <a class="dropdown-item" href="#">Adicionar país</a>

                    <h6 class="dropdown-header">Província</h6>
                    <a class="dropdown-item" href="#">Listar províncias</a>
                    <a class="dropdown-item" href="#">Adicionar província</a>
                    <div class="dropdown-divider"></div>

                    <h6 class="dropdown-header">Munícipio</h6>
                    <a class="dropdown-item" href="#">Listar munícipios</a>
                    <a class="dropdown-item" href="#">Adicionar munícipio</a>
                    <div class="dropdown-divider"></div>

                    <h6 class="dropdown-header">Comuna</h6>
                    <a class="dropdown-item" href="#">Listar comunas</a>
                    <a class="dropdown-item" href="#">Adicionar comuna</a>
                    <div class="dropdown-divider"></div>

                    <h6 class="dropdown-header">Bairro</h6>
                    <a class="dropdown-item" href="#">Listar bairros</a>
                    <a class="dropdown-item" href="#">Adicionar bairro</a>
                </div>
            </div>

            <div class="btn-group">
                <button type="button" class="btn btn-primary dropdown-toggle text-left" data-toggle="dropdown">
                    Livro (atributos)
                </button>
                <div class="dropdown-menu">
                    <h6 class="dropdown-header">Categoria</h6>
                    <a class="dropdown-item" href="#">Listar categorias</a>
                    <a class="dropdown-item" href="#">Adicionar categorias</a>

                    <h6 class="dropdown-header">Estado</h6>
                    <a class="dropdown-item" href="#">Listar estados</a>
                    <a class="dropdown-item" href="#">Adicionar estado</a>
                    <div class="dropdown-divider"></div>

                    <h6 class="dropdown-header">Classificação</h6>
                    <a class="dropdown-item" href="#">Listar classificações</a>
                    <a class="dropdown-item" href="#">Adicionar classificação</a>
                    <div class="dropdown-divider"></div>

                    <h6 class="dropdown-header">Descritores</h6>
                    <a class="dropdown-item" href="#">Listar descritores</a>
                    <a class="dropdown-item" href="#">Adicionar descritores</a>
                </div>
            </div>
        </div>
    </body>
</html>
