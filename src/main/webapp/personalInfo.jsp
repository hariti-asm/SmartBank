<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Demander mon crédit en ligne - Wafasalaf</title>
    <link rel="stylesheet" href="personnal_info.css">
</head>
<body>


<main class="main-content">


    <h1>Demander mon crédit en ligne</h1>

    <a href="#" class="back-button">← Retour</a>
    <div>

    </div>
    <div class="steps">
        <a href="#" class="step active">
            <span class="step-number">1</span>
            <span class="step-title">Simuler mon crédit</span>
        </a>
        <a href="coordinates.html" class="step completed">
            <span class="step-number">2</span>
            <span class="step-title">Mes coordonnées</span>
        </a>
        <a href="personnal_info.html" class="step">
            <span class="step-number">3</span>
            <span class="step-title">Mes infos personnelles</span>
        </a>
    </div>


    <div class="application-container">

        <form id="credit-form" class="credit-form" action="personalInfo" method="post">
            <div class="form-group">
                <label>Civilité</label>
                <div class="radio-group">
                    <input type="radio" id="madame" name="civilite" value="Madame" checked>
                    <label for="madame">Madame</label>
                    <input type="radio" id="mademoiselle" name="civilite" value="Mademoiselle">
                    <label for="mademoiselle">Mademoiselle</label>
                    <input type="radio" id="monsieur" name="civilite" value="Monsieur">
                    <label for="monsieur">Monsieur</label>
                </div>
            </div>

            <div class="form-group">
                <input type="text" id="nom" name="nom" placeholder="Nom*" required>
            </div>

            <div class="form-group">
                <input type="text" id="prenom" name="prenom" placeholder="Prénom*" required>
            </div>

            <div class="form-group">
                <input type="text" id="cin" name="cin" placeholder="Numéro CIN / Carte de séjour*" required>
            </div>

            <div class="form-group">
                <input type="date" id="date-naissance" name="date-naissance" required>
                <label for="date-naissance">Date de naissance*</label>
            </div>

            <div class="form-group">
                <input type="date" id="date-embauche" name="date-embauche" required>
                <label for="date-embauche">Date d'embauche/début de l'activité*</label>
            </div>

            <div class="form-group">
                <input type="number" id="revenus" name="revenus" placeholder="Total revenus mensuels (net en DH)*" required>
            </div>

            <div class="form-group">
                <label>Avez vous des crédits en cours ?</label>
                <div class="radio-group">
                    <input type="radio" id="credit-oui" name="credits-en-cours" value="Oui">
                    <label for="credit-oui">Oui</label>
                    <input type="radio" id="credit-non" name="credits-en-cours" value="Non" checked>
                    <label for="credit-non">Non</label>
                </div>
            </div>

            <div class="form-group checkbox-group">
                <input type="checkbox" id="conditions" name="conditions" required>
                <label for="conditions">J'ai lu et j'accepte les conditions générales d'utilisation figurant sur les informations légales, notamment la mention relative à la protection des données personnelles</label>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Demander ce crédit</button>
                <button type="button" class="btn btn-secondary">Voir mon récapitulatif</button>
            </div>
        </form>
        <aside class="summary">
            <h2>Mon récapitulatif</h2>
            <div class="summary-content">
                <h3>Mon projet</h3>
                <p></p>
                <h3>Coordonnées et infos personnelles</h3>
                <dl>
                    <dt>Email:${email}</dt>
                    <dd></dd>
                    <dt>Téléphone:${phone}</dt>
                    <dd></dd>
                </dl>
                <h3>Détails de mon crédit</h3>
                <dl>
                    <dt>Vous êtes:</dt>
                    <dd></dd>
                    <dt>Montant: ${amount}</dt>
                    <dd></dd>
                    <dt>Durée: ${duration}</dt>
                    <dd></dd>
                    <dt>Mensualité:</dt>
                    <dd></dd>
                    <dt>Frais de dossier:</dt>
                    <dd></dd>
                </dl>
            </div>
        </aside>



    </div>
    <footer class="page-footer">
        <p>Simulation à titre indicatif et non contractuelle. La mensualité minimale est de 180 dirhams. Un client Wafasalaf peut bénéficier d'une tarification plus avantageuse en fonction de ses conditions préférentielles.</p>
        <p>Conformément à la loi 09-08, vous disposez d'un droit d'accès, de rectification et d'opposition au traitement de vos données personnelles. Ce traitement est autorisé par la CNDP sous le numéro A-GC-206/2014.</p>
    </footer>

</main>

<script src ="./javascript/personnal_info.js"></script>


</body>
</html>