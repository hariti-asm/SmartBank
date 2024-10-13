<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900&display=swap" rel="stylesheet">
    <title>Crédit Bancaire</title>
</head>

<body class="bg over">
<div class="margin-x">
    <h1 class="titre-center m-t-lg">Demander mon crédit en ligne</h1>
    <a class="d-flex gap-5 w-fit m-y-1" href="form1.jsp">
        <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor"
             class="bi bi-arrow-left" viewBox="0 0 16 16">
            <path fill-rule="evenodd"
                  d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8" />
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

                <div class="w-30 flexe">
                    <div class="seconde w-100 titre-center color-four p-y-5">
                        <p class="number">2</p>
                        <p class="p-1">Mes coordonnées</p>
                    </div>
                    <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="#FDDF35"
                         class="bi bi-caret-down-fill m-t-0" viewBox="0 0 16 16">
                        <path
                                d="M7.247 11.14 2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 0 1 .753 1.659l-4.796 5.48a1 1 0 0 1-1.506 0z" />
                    </svg>
                </div>

                <div class="w-30 third titre-center p-y-5 white">
                    <p class="number">3</p>
                    <p class="p-1">Mes infos personnelles</p>
                </div>
            </div>

            <!-- Form from the first page -->
            <div class="margin-x-1">
                <form id="creditForm" method="post" action="${pageContext.request.contextPath}/coordinates">
                    <div class="m-t-5 input-container">
                        <input type="email" name="email" id="email" placeholder="Email*" class="custom-input" value="" required>
                        <label for="email" class="custom-label">Email*</label>
                    </div>
                    <div class="m-t-9 input-container">
                        <input type="tel" name="phone" id="phone" placeholder="Téléphone mobile*" class="custom-input border-bottom" value="" required>
                        <label for="phone" id="phoneLabel" class="custom-label color">Téléphone mobile*</label>
                    </div>

                    <div class="flex-center">
                        <button type="submit" class="button1 m-t-5">
                            <p class="p-10">Continuer</p>
                            <p class="p-2">Sans engagement</p>
                        </button>
                    </div>
                </form>
            </div>

            <div class="m-t-10">
                <p class="color-four fs-1">Simulation à titre indicatif et non contractuelle. La mensualité minimale est de 180 dirhams. Un client Wafasalaf peut bénéficier d'une tarification plus avantageuse en fonction de ses conditions préférentielles.</p>
                <p>Conformément à la loi 09-08, vous disposez d'un droit d'accès, de rectification et d'opposition au traitement de vos données personnelles. Ce traitement est autorisé par la CNDP sous le numéro A-GC-206/2014.</p>
            </div>
        </div>

        <!-- Summary section from the first page -->
        <aside class="summary-section">
            <h2>Mon récapitulatif</h2>
            <div class="summary-content">
                <h3>Mon projet</h3>
                <p id="project">${project}</p>
                <h3>Détails de mon crédit</h3>
                <div class="summary-item">
                    <span class="label">Vous êtes:</span>
                    <input type="text" name="job" value="${job}" required>
                </div>
                <div class="summary-item">
                    <span class="label">Montant:</span>
                    <span class="value" id="amount">${amount}</span>
                </div>
                <div class="summary-item">
                    <span class="label">Durée:</span>
                    <span class="value" id="duration">${duration}</span>
                </div>
                <div class="summary-item">
                    <span class="label">Mensualité:</span>
                    <span class="value" id="monthlyPayment"> DH</span>
                </div>
                <div class="summary-item">
                    <span class="label">Frais de dossier:</span>
                    <span class="value" id="frais-dossier-output"> DH</span>
                </div>
            </div>
        </aside>
    </div>
</div>

<!-- Error alert -->
<div id="error-alert" class="error-alert">
    <div class="error-message">
        <p>Veuillez vérifier vos informations.</p>
    </div>
    <button class="close-btn">Fermer</button>
</div>

<script src="${pageContext.request.contextPath}/javascript/coordinate.js" defer></script>

</body>
</html>
