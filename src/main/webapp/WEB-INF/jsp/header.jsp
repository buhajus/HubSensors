<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <!--
        Stiliai skirti kol kas prisijungimo ir registracijos langui, bet galima juos papildyti ir panaudoti
        kituose puslapiuose, nes į visus įtrauktas šis header failas
    -->
    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #eee;
        }

        .form-signing {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }

        .form-signing .form-signing-heading,
        .form-signing .checkbox {
            margin-bottom: 10px;
        }

        .form-signing .checkbox {
            font-weight: normal;
        }

        .form-signing .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }

        .form-signing .form-control:focus {
            z-index: 2;
        }

        .form-signing input {
            margin-top: 10px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signing button {
            margin-top: 10px;
        }

        .has-error {
            color: red
        }
    </style>
</html>

<!-- compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>