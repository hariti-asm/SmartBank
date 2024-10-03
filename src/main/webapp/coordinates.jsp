<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Demander mon crédit en ligne</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/coordinates.css">
</head>
<body>
<div class="container">
    <header class="header">
        <div class="breadcrumb">
            <span class="back-link">← Retour</span>
            Accueil / Demander mon crédit en ligne
        </div>
        <h1>Demander mon crédit en ligne</h1>
    </header>

    <main class="main-content">
        <section class="form-section">
            <div class="steps">
                <div class="step completed">1<br>Simuler mon crédit</div>
                <div class="step active">2<br>Mes coordonnées</div>
                <div class="step">3<br>Mes infos personnelles</div>
            </div>

            <form id="creditForm" method="post" action="${pageContext.request.contextPath}/coordinates">
                <div class="form-group">
                    <input type="email" id="email" name="email" placeholder="Email*" required>
                </div>
                <div class="form-group">
                    <input type="tel" id="phone" name="phone" placeholder="Téléphone mobile*" required>
                </div>

                <button type="submit" class="submit-btn">Continuer<br>Sans engagement</button>
                <div class="footer">
                    <p>Simulation à titre indicatif et non contractuelle. La mensualité minimale est de 180 dirhams. Un client Wafasalaf peut bénéficier d'une tarification plus avantageuse en fonction de ses conditions préférentielles.</p>
                    <p>Conformément à la loi 09-08, vous disposez d'un droit d'accès, de rectification et d'opposition au traitement de vos données personnelles. Ce traitement est autorisé par la CNDP sous le numéro A-GC-206/2014.</p>
                </div>
            </form>
        </section>

        <aside class="summary-section">
            <h2>Mon récapitulatif</h2>
            <div class="summary-content">
                <h3>Mon projet</h3>
                <p id="project"></p>
                <h3>Détails de mon crédit</h3>
                <div class="summary-item">
                    <span class="label">Vous êtes:</span>
                    <span class="value" id="status"></span>
                </div>
                <div class="summary-item">
                    <span class="label">Montant:</span>
                    <span class="value" id="amount"></span>
                </div>
                <div class="summary-item">
                    <span class="label">Durée:</span>
                    <span class="value" id="duration"></span>
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
    </main>
</div>

<div id="error-alert" class="error-alert">
    <div class="error-message">
        <p>Veuillez vérifier vos informations.</p>
    </div>
    <button class="close-btn">Fermer</button>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/coordinate.js"></script>


</body>
</html>
