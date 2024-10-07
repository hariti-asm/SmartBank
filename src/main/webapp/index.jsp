<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simuler mon crédit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
<div class="container">
    <div class="form-container">
        <form id="loan-form" class="credit-form" method="post" action="homeServlet">
            <h2>Mon projet</h2>
            <div class="form-group">
                <label for="project">Mon projet</label>
                <select id="project" name="project">
                    <option value="need-money" selected>J'ai besoin d'argent</option>
                    <option value="finance-vehicle-used">Je finance mon véhicule d'occasion</option>
                    <option value="manage-unforeseen">Je gère mes imprévus</option>
                    <option value="finance-vehicle-new">Je finance mon véhicule neuf</option>
                    <option value="equip-house">J'équipe ma maison</option>
                </select>
            </div>
            <div class="form-group">
                <label for="status">Je suis</label>
                <select id="status" name="job">
                    <option value="fonctionnaire" selected>Fonctionnaire</option>
                    <option value="retire">Retraité</option>
                    <option value="unemployed">Chômeur</option>
                </select>
            </div>
            <div class="form-group">
                <label for="amount">Montant (en DH): <span id="amount-output">10000</span></label>
                <input type="range" id="amount" name="amount" min="0" max="50000" value="10000">
            </div>
            <div class="form-group">
                <label for="duration">Durée (en mois): <span id="duration-output">24</span></label>
                <input type="range" id="duration" name="duration" min="6" max="60" value="24">
            </div>
            <div class="form-group">
                <label for="monthly">Mensualité (en DH): <span id="monthly-output">0.00</span></label>
                <input type="hidden" id="monthly" name="monthly" readonly>
            </div>
            <div class="form-group">
                <label for="frais-dossier">Frais de dossier (en DH): <span id="frais-dossier-output">0.00</span></label>
            </div>
            <button type="submit">Continuer<br>Sans engagement</button>
        </form>
    </div>
    <div class="summary">
        <h2>Mon récapitulatif</h2>
        <div class="summary-content">
            <h3>Mon projet</h3>
            <p id="project-type">Prêt Personnel</p>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/script.js"></script>
</body>
</html>