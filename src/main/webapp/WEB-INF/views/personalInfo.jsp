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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

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
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <c:if test="${not empty validationErrors}">
                <div class="error-messages">
                    <ul>
                        <c:forEach var="error" items="${validationErrors}">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/personalInfo" method="post">
                <div class="margin-x-1">
                    <div class="radio-container">
                        <label for="civility" class="radio-title">Civilité</label>
                        <div class="radio-group" id="civility">
                            <label class="custom-radio">
                                <input type="radio" name="civility"
                                       value="monsieur" ${civility == 'monsieur' ? 'checked' : ''} required>
                                <div class="custom_field"></div>
                                <span class="fs1 color-four">Monsieur</span>
                            </label>
                            <label class="custom-radio">
                                <input type="radio" name="civility"
                                       value="madame" ${civility == 'madame' ? 'checked' : ''}>
                                <div class="custom_field"></div>
                                <span class="fs1 color-four">Madame</span>
                            </label>
                            <label class="custom-radio">
                                <input type="radio" name="civility"
                                       value="mademoiselle" ${civility == 'mademoiselle' ? 'checked' : ''}>
                                <div class="custom_field"></div>
                                <span class="fs1 color-four">Mademoiselle</span>
                            </label>
                        </div>
                    </div>

                    <div class="m-t-5 input-container">
                        <input type="text" name="lastName" id="lastName" class="custom-input" required
                               value="${lastName}">
                        <label for="lastName" class="custom-label">Nom</label>
                    </div>
                    <div class="m-t-9 input-container">
                        <input type="text" name="firstName" id="firstName" class="custom-input" required
                               value="${firstName}">
                        <label for="firstName" class="custom-label">Prénom</label>
                    </div>

                    <div class="m-t-9 input-container">
                        <input type="text" name="cin" id="cin" class="custom-input" required value="${cin}">
                        <label for="cin" class="custom-label">Numéro CIN / Carte de séjour*</label>
                    </div>

                    <div class="m-t-9 input-container">
                        <input type="text" name="birthdate" id="birthDate" placeholder="YYYY-MM-DD" class="custom-input"
                               required value="${birthdate}">
                        <label for="birthDate" class="custom-label">Date de naissance*</label>
                    </div>

                    <div class="m-t-9 input-container">
                        <input type="text" name="workdate" id="workdate"
                               placeholder="YYYY-MM-DD" class="custom-input" required value="${workdate}">
                        <label for="workdate" class="custom-label">Date d'embauche/début de l'activité*</label>
                    </div>

                    <div class="m-t-9 input-container">
                        <input type="number" name="revenues" id="monthlyPayment" class="custom-input" required
                               >
                        <label for="monthlyPayment" class="custom-label">Total revenus mensuels (net en DH)*</label>
                    </div>

                    <div class="radio-container m-t-4">
                        <label for="hasActivateCredits" class="radio-title">Avez-vous des crédits en cours ?</label>
                        <div class="radio-group" id="hasActivateCredits">
                            <label class="custom-radio">
                                <input type="radio" name="hasActivateCredits"
                                       value="oui" ${hasActivateCredits == 'oui' ? 'checked' : ''} required>
                                <div class="custom_field"></div>
                                <span class="fs1 color-four">Oui</span>
                            </label>
                            <label class="custom-radio">
                                <input type="radio" name="hasActivateCredits"
                                       value="non" ${hasActivateCredits == 'non' ? 'checked' : ''}>
                                <div class="custom_field"></div>
                                <span class="fs1 color-four">Non</span>
                            </label>
                        </div>
                    </div>

                    <div class="flex-center m-b-10">
                        <button class="button2 m-t-5" type="submit">
                            Demander crédit
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
            <p class="color-first pret">${project}
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
