<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Demander mon crédit en ligne</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
                <p id="project"> ${project}</p>
                <h3>Détails de mon crédit</h3>
                <div class="summary-item">
                    <span class="label">Vous êtes:</span>
                    <input type="text" name="job" value="${job}" required>
                </div>
                <div class="summary-item">
                    <span class="label">Montant: ${amount}</span>
                    <span class="value" id="amount"></span>
                </div>
                <div class="summary-item">
                    <span class="label">Durée: ${duration}</span>
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
<script>
    document.addEventListener('DOMContentLoaded', function() {
        displayStoredData();
        setupFormSubmission();
    });

    function displayStoredData() {
        const fields = [
            { key: 'project', suffix: '' },
            { key: 'status', suffix: '' },
            { key: 'amount', suffix: ' DH' },
            { key: 'duration', suffix: ' mois' },
            { key: 'monthly', suffix: ' DH', elementId: 'monthlyPayment' },
            { key: 'fraisDossier', suffix: ' DH', elementId: 'frais-dossier-output' }
        ];

        fields.forEach(field => {
            const value = localStorage.getItem(field.key) || 'N/A';
            const elementId = field.elementId || field.key;
            const element = document.getElementById(elementId);

            if (element) {
                element.textContent = value + field.suffix;
            } else {
                console.warn(`Element with id '${elementId}' not found.`);
            }

            console.log(`${field.key}:`, value);
        });
    }

    function setupFormSubmission() {
        const form = document.getElementById('creditForm');
        if (!form) {
            console.warn("Form with id 'creditForm' not found.");
            return;
        }

        form.addEventListener('submit', function (event) {
            const fields = ['monthly', 'amount', 'duration'];

            fields.forEach(field => {
                const value = localStorage.getItem(field);
                if (value) {
                    const input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = field;
                    input.value = value;
                    form.appendChild(input);
                } else {
                    console.warn(`${field} value is missing in localStorage!`);
                }
            });
        });
    }
</script>
</body>
</html>
