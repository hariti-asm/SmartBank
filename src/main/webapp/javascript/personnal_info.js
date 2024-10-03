document.addEventListener('DOMContentLoaded', function() {
    const summaryData = {
        email: localStorage.getItem('email'),
        phone: localStorage.getItem('phone'),
        status: "Fonctionnaire",
        amount: localStorage.getItem('amount'),
        duration: localStorage.getItem('duration'),
        monthlyPayment: localStorage.getItem('monthly'),
        applicationFee: localStorage.getItem('fraisDossier')
    };

    document.querySelector('.summary-content h3 + p').textContent = "Prêt Personnel"; // Mon projet
    document.querySelector('.summary-content dl:nth-of-type(1) dd:nth-of-type(1)').textContent = summaryData.email; // Email
    document.querySelector('.summary-content dl:nth-of-type(1) dd:nth-of-type(2)').textContent = summaryData.phone; // Téléphone
    document.querySelector('.summary-content dl:nth-of-type(2) dd:nth-of-type(1)').textContent = summaryData.status; // Vous êtes
    document.querySelector('.summary-content dl:nth-of-type(2) dd:nth-of-type(2)').textContent = summaryData.amount + " DH"; // Montant
    document.querySelector('.summary-content dl:nth-of-type(2) dd:nth-of-type(3)').textContent = summaryData.duration + " mois"; // Durée
    document.querySelector('.summary-content dl:nth-of-type(2) dd:nth-of-type(4)').textContent = summaryData.monthlyPayment + " DH"; // Mensualité
    document.querySelector('.summary-content dl:nth-of-type(2) dd:nth-of-type(5)').textContent = summaryData.applicationFee + " DH"; // Frais de dossier
});