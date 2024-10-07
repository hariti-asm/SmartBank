<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap"
          rel="stylesheet">
    <title>Crédit Bancaire</title>
    <style>
        * {
            font-family: "Roboto", sans-serif;
            font-style: normal;
        }

        .bg {
            background-color: #F1F2F6;
        }

        .margin-x {
            margin-left: 280px;
            margin-right: 280px;
        }

        .m-t-5 {
            margin-top: 20px;
        }

        .margin-x-1 {
            margin-left: 72px;
            margin-right: 72px;
        }

        .m-t-1 {
            margin-top: 3px;
        }

        .m-b-5 {
            margin-bottom: 10px;
        }

        .label {
            font-size: 15px;
            color: #30435C;
            font-weight: 600;
        }

        .titre-center {
            text-align: center;
        }

        .m-t-lg {
            margin-top: 100px;
        }

        .w-75 {
            width: 65%;
        }

        .w-20 {
            width: 32%;
        }

        .flex {
            display: flex;
            justify-content: space-between;
            align-items: start;
        }

        a {
            text-decoration: none;
            color: black;
        }

        a:hover {
            color: #02AFBC;
        }

        p {
            margin: 0px;
        }

        .w-30 {
            width: 33.2%;
        }

        .first {
            background-color: #02AFBC;
        }

        .seconde {
            background-color: #FDDF35;
        }

        .third {
            background-color: #9B9B9B;
        }

        .w-100 {
            width: 100%;
        }

        .d-flex {
            display: flex;
            align-items: start;
        }

        .m-t-0 {
            margin-top: 0px;
        }

        .flexe {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .white {
            color: white;
        }

        .gap-5 {
            gap: 5px;
        }

        .p-10 {
            font-size: 18px;
            font-weight: 700;
            color: white;
        }

        .p-9 {
            font-size: 17.1px;
            font-weight: 700;
            color: white;
        }

        .p-2 {
            font-size: 13px;
            font-weight: 500;
            color: white;
        }

        .p-4 {
            font-size: 18px;
        }

        .w-fit {
            width: fit-content;
        }

        .justify-b {
            justify-content: space-between;
        }

        .w-8 {
            width: 88px;
        }

        .w-5 {
            width: 46px;
        }

        .w-9 {
            width: 125px;
        }

        .input1 {
            height: 28px;
            padding: 3px 4px;
            font-size: 25px;
            font-weight: 800;
            border: none;
            margin-top: 3px;
            color: #02AFBC;
            border-radius: 5px;
            box-shadow: inset 0px 3px 7px rgba(0, 0, 0, 0.2), inset 2px 0px 2px rgba(0, 0, 0, 0.1), inset -2px 0px 2px rgba(0, 0, 0, 0.1), inset 0px -5px 5px rgba(0, 0, 0, 0.05);
        }

        input[type=number]::-webkit-outer-spin-button,
        input[type=number]::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        input:focus {
            border-color: transparent;
            outline: none;
        }

        .m-y-1 {
            margin-top: 10px;
            margin-bottom: 10px;
        }

        .color-first {
            color: #02AFBC;
        }

        .color-seconde {
            color: #FDDF35;
        }

        .color-third {
            color: #9B9B9B;
        }

        .color-four {
            color: #30435C;
        }

        .fs-1 {
            font-size: 11.7px;
        }

        .p-y-5 {
            padding-top: 13px;
            padding-bottom: 15px;
        }

        .p-1 {
            font-size: 14px;
            font-weight: 600;
        }

        .number {
            font-size: 32px;
            font-weight: bolder;
        }

        .slider-container {
            width: 100%;
            margin: 10px auto;
        }

        .slider {
            -webkit-appearance: none;
            appearance: none;
            width: 100%;
            height: 1px;
            border-radius: 0;
            outline: none;
            position: relative;
        }

        .slider::-webkit-slider-thumb {
            -webkit-appearance: none;
            appearance: none;
            width: 25px;
            height: 25px;
            background: #FDDF35;
            border-radius: 50%;
            cursor: pointer;
            position: relative;
            top: -12px;
            box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.3);
        }

        .slider::-moz-range-thumb {
            width: 25px;
            height: 25px;
            background: #FDDF35;
            border-radius: 50%;
            cursor: pointer;
            position: relative;
            top: -12px;
            box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.3);
        }

        .slider::-webkit-slider-runnable-track {
            height: 1px;
            background: #30435C;
        }

        .m-t-10 {
            margin-top: 34px;
            background-color: #F1F2F6;
            padding: 30px 28px;
        }

        .m-t-4 {
            margin-top: 16px;
        }

        .m-b-6 {
            margin-bottom: 50px;
        }

        .mon {
            font-size: 23px;
            font-weight: 600;
            margin: 17px 0px;
        }

        .button1 {
            background-color: #02AFBC;
            padding: 10px 20px;
            border: #F1F2F6 3px solid;
            border-radius: 4px;
            box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.4);
            cursor: pointer;
        }

        .button3 {
            background-color: #02AFBC;
            padding: 6px 15px;
            border: #F1F2F6 3px solid;
            border-radius: 4px;
            box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.2);
            cursor: pointer;
        }

        .button2 {
            background-color: #02AFBC;
            padding: 16px 30px;
            border: #F1F2F6 3px solid;
            border-radius: 4px;
            box-shadow: 0px 5px 10px rgba(0, 0, 0, 0.4);
            cursor: pointer;
        }

        .bg1 {
            background-color: #F1F2F6;
            font-size: 16px;
            font-weight: 500;
            padding: 6px 8px;
        }

        .button1:hover {
            background-color: #028C96;
        }

        .button2:hover {
            background-color: #028C96;
        }

        .button3:hover {
            background-color: #028C96;
        }

        .flex-center {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 22px;
        }

        .pret {
            font-size: 14px;
            font-weight: 700;
            padding: 4px 8px;
        }

        .m-b-9 {
            margin-bottom: 30px;
        }

        .m-b-10 {
            margin-bottom: 80px;
        }

        .input-container {
            position: relative;
        }

        .custom-input {
            width: 100%;
            padding: 9px 8px 4px 8px;
            font-size: 18px;
            border: none;
            border-bottom: 2px solid #ccc;
            outline: none;
            background-color: transparent;
            color: #02AFBC;
            font-weight: 500;
            transition: border-color 0.3s ease;
        }

        .custom-input:focus {
            border-bottom-color: #ccc;
        }

        .m-t-9 {
            margin-top: 30px;
        }

        .custom-label {
            position: absolute;
            left: 5px;
            top: 10px;
            font-size: 18px;
            font-weight: 500;
            color: #999;
            transition: all 0.3s ease;
            pointer-events: none;
        }

        .custom-input:focus + .custom-label,
        .custom-input:not(:placeholder-shown) + .custom-label {
            top: -11px;
            font-size: 14px;
            color: #30435C;
            font-weight: 600;
        }

        .custom-input::placeholder {
            color: transparent;
        }

        .custom-input:focus::placeholder {
            color: #999;
        }

        .custom-input:disabled {
            background-color: #e0e0e0;
            border-bottom: 0;
        }

        .data {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 6px 8px;
        }

        .border-b {
            border-bottom: 1px solid #DEE1E5;
        }

        .fs2 {
            font-size: 15px;
        }

        .fw {
            font-weight: 600;
        }

        .radio-container {
            margin-bottom: 15px;
            padding: 3px 4px;
        }

        .radio-title {
            font-weight: 600;
            margin-bottom: 7px;
            display: block;
            font-size: 14.5px;
            color: #30435C;
        }

        .radio-group {
            display: flex;
            gap: 17px;
        }

        label {
            cursor: pointer;
            display: flex;
            align-items: center;
        }

        .custom_field {
            position: relative;
            width: 12px;
            height: 12px;
            display: inline-block;
            border-radius: 50%;
            border: 1px solid #30435C;
            margin-right: 4px;
        }

        .custom_field::before {
            content: "";
            position: absolute;
            width: 6px;
            height: 6px;
            left: 50%;
            top: 50%;
            background-color: white;
            transform: translate(-50%, -50%);
            border-radius: 50%;
            transition: all 0.6s ease;
        }

        input[type=radio] {
            display: none;
        }

        input[type=radio]:checked + .custom_field::before {
            background-color: #02AFBC;
        }

        input[type=radio]:checked + .custom_field {
            border-color: #02AFBC;
        }

        input[type=radio]:checked + .custom_field + span {
            color: #02AFBC;
            font-weight: bold;
        }

        .fs1 {
            font-size: 14px;
        }

        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: white;
            margin: 15% auto;
            padding: 18px 0px;
            border: 1px solid #888;
            width: 500px;
            border-radius: 0.375rem;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
        }

        .flex-end {
            display: flex;
            justify-content: flex-end;
            align-items: center;
        }

        .p-5 {
            padding: 0px 15px;
        }

        .erreur {
            width: 93%;
            background-color: #FBD0D5;
            border: 1px solid #F9BDC4;
            margin-bottom: 18px;
            border-radius: 0.375rem;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        .error-list {
            color: #7A0A17;
        }

        .card {
            border-color: rgb(42, 24, 24);
            background-color: white;
            border: 1px;
            box-shadow: -3px 0 3px rgba(0, 0, 0, 0.1), 3px 0 3px rgba(0, 0, 0, 0.1), 0 3px 3px rgba(0, 0, 0, 0.4);
        }

        select {
            background-color: white;
            font-size: 15px;
            font-weight: 500;
            border-radius: 4px;
            border-color: #999999;
            display: inline-block;
            line-height: 1.5em;
            padding: 0.4em 3.5em 0.4em 1em;
            width: 100%;
            cursor: pointer;
            margin: 0;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            -webkit-appearance: none;
            -moz-appearance: none;
        }

        select.minimal {
            background-image: linear-gradient(45deg, transparent 50%, rgb(0, 0, 0) 50%), linear-gradient(135deg, rgb(0, 0, 0) 50%, transparent 50%);
            background-position: calc(100% - 20px) calc(1em + 2px), calc(100% - 15px) calc(1em + 2px), calc(100% - 2.5em) 0.5em;
            background-size: 5px 5px, 5px 5px, 1px 1.5em;
            background-repeat: no-repeat;
        }

        select.minimal:focus {
            border-color: #999999;
            outline: 0;
        }
    </style>
</head>
<body class="bg">
<div class="margin-x">
    <h1 class="titre-center m-t-lg">Demander mon crédit en ligne</h1>
    <a class="d-flex gap-5 w-fit m-y-1" href="step2.jsp">
        <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-arrow-left"
             viewBox="0 0 16 16">
            <path fill-rule="evenodd"
                  d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8"></path>
        </svg>
        <p class="p-4">Retour</p>
    </a>
    <div class="flex">
        <div class="card w-75 m-b-6">
            <div class="d-flex justify-b m-b-5">
                <div class="w-30 first titre-center white p-y-5">
                    <p class="number">1</p>
                    <p class="p-1">Simuler mon crédit</p>
                </div>
                <div class="w-30 first titre-center white p-y-5">
                    <p class="number">2</p>
                    <p class="p-1">Mes coordonnées</p>
                </div>
                <div class="w-30 flexe">
                    <div class="seconde w-100 titre-center color-four p-y-5">
                        <p class="number">3</p>
                        <p class="p-1">Mes infos personnelles</p>
                    </div>
                    <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="#FDDF35"
                         class="bi bi-caret-down-fill m-t-0" viewBox="0 0 16 16">
                        <path d="M7.247 11.14 2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z"></path>
                    </svg>
                </div>
            </div>
            <form action="${pageContext.request.contextPath}/personalInfo" method="post">
                <div class="margin-x-1">
                    <div class="radio-container">
                        <label for="civility" class="radio-title">Civilité</label>
                        <div class="radio-group" id="civility">
                            <label class="custom-radio">
                                <input type="radio" name="civility" value="monsieur" required>
                                <div class="custom_field"></div>
                                <span class="fs1 color-four">Monsieur</span>
                            </label>
                            <label class="custom-radio">
                                <input type="radio" name="civility" value="madame">
                                <div class="custom_field"></div>
                                <span class="fs1 color-four">Madame</span>
                            </label>
                            <label class="custom-radio">
                                <input type="radio" name="civility" value="mademoiselle">
                                <div class="custom_field"></div>
                                <span class="fs1 color-four">Mademoiselle</span>
                            </label>
                        </div>
                    </div>

                    <div class="m-t-5 input-container">
                        <input type="text" name="lastName" id="lastName" class="custom-input" required>
                        <label for="lastName" class="custom-label">first name : ${firstName}</label>
                    </div>
                    <div class="m-t-9 input-container">
                        <input type="text" name="firstName" id="firstName" class="custom-input" required>
                        <label for="firstName" class="custom-label">Last name : ${lastName}</label>
                    </div>

                    <div class="m-t-9 input-container">
                        <input type="text" name="cin" id="cin" class="custom-input" required>
                        <label for="cin" class="custom-label">Numéro CIN / Carte de séjour*</label>
                    </div>

                    <div class="m-t-9 input-container">
                        <input type="text" name="birthdate" id="birthDate" placeholder="YYYY-MM-DD" class="custom-input"
                               required>
                        <label for="birthDate" class="custom-label">Date de naissance*</label>
                    </div>

                    <div class="m-t-9 input-container">
                        <input type="text" name="workdate" id="workdate"
                               placeholder="YYYY-MM-DD" class="custom-input" required>
                        <label for="workdate" class="custom-label">Date d'embauche/début de
                            l'activité*</label>
                    </div>

                    <div class="m-t-9 input-container">
                        <input type="number" name="monthlyPayment" id="monthlyPayment" class="custom-input" required>
                        <label for="monthlyPayment" class="custom-label">Total revenus mensuels (net en DH)*</label>
                    </div>

                    <div class="radio-container m-t-4">
                        <label for="hasActivateCredits" class="radio-title">Avez-vous des crédits en cours ?</label>
                        <div class="radio-group" id="hasActivateCredits">
                            <label class="custom-radio">
                                <input type="radio" name="hasActivateCredits" value="oui" required>
                                <div class="custom_field"></div>
                                <span class="fs1 color-four">Oui</span>
                            </label>
                            <label class="custom-radio">
                                <input type="radio" name="hasActivateCredits" value="non">
                                <div class="custom_field"></div>
                                <span class="fs1 color-four">Non</span>
                            </label>
                        </div>
                    </div>

                    <div class="flex-center m-b-10">
                        <button class="button2 m-t-5" type="submit">
                            demander credit
                        </button>
                        <button class="button2 m-t-5">
                            <p class="p-9">Voir mon récapitulatif</p>
                        </button>
                    </div>
                </div>
            </form>
            <div class="m-t-10">
                <p class="color-four fs-1">Simulation à titre indicatif et non contractuelle. La mensualité minimale est
                    de 180 dirhams. Un client Wafasalaf peut bénéficier d'une tarification plus avantageuse en fonction
                    de ses conditions préférentielles.</p>
                <p class="color-four fs-1 m-t-4">Conformément à la loi 09-08, vous disposez d’un droit d’accès, de
                    rectification et d’opposition au traitement de vos données personnelles. Ce traitement est autorisé
                    par la CNDP sous le numéro A-GC-206/2014.</p>
            </div>
        </div>
        <div class="card w-20">
            <p class="titre-center mon color-four">Mon récapitulatif</p>
            <p class="color-four bg1 ">Mon projet</p>
            <p class="color-first pret"><%= session.getAttribute("projectType") %>
            </p>
            <p class="color-four bg1">Coordonnées et infos personnelles</p>
            <p class="color-four bg1">Coordonnées et infos personnelles</p>
            <div class="data">
                <p class="color-four fs2">Email:</p>
                <p class="color-first fs2 fw">${email}</p>
            </div>
            <div class="data">
                <p class="color-four fs2">Téléphone:</p>
                <p class="color-first fs2 fw">${phone}</p>
            </div>
            <p class="color-four bg1">Détails de mon crédit</p>
            <div class="data">
                <p class="color-four fs2">Montant demandé:</p>
                <p class="color-first fs2 fw">${amount}</p>
                <p class="color-first fs2 fw">${folderCost}</p>


            </div>
            <div class="data">
                <p class="color-four fs2">Durée:</p>
                <p class="color-first fs2 fw">${duration}</p>
            </div>
            <p class="color-four bg1">Détails de mes infos personnelles</p>
            <p class="color-four fs2">Nom:</p>
            <p class="color-first fs2 fw">${lastName}
            </p>
            <p class="color-four fs2">Prénom:</p>
            <p class="color-first fs2 fw">${firtName}
            </p>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/personnal_info.js"></script>
</body>
</html>
