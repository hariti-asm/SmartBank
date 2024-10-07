document.addEventListener('DOMContentLoaded', function() {
    // Retrieve data from localStorage
    const summaryData = {
        amount: localStorage.getItem('amount'),
        duration: localStorage.getItem('duration'),
        monthlyPayment: localStorage.getItem('monthly'),
        applicationFee: localStorage.getItem('fraisDossier')
    };
    document.getElementById('hidden-amount').value = summaryData.amount;
    document.getElementById('hidden-duration').value = summaryData.duration;
    document.getElementById('hidden-monthlyPayment').value = summaryData.monthlyPayment;
    document.getElementById('hidden-applicationFee').value = summaryData.applicationFee;
    console.log(localStorage.getItem('amount'));
    console.log(localStorage.getItem('duration'));
    console.log(localStorage.getItem('monthly'));
    console.log(localStorage.getItem('fraisDossier'));


    // Update the summary section in the aside
    document.querySelector('.summary-content dl:nth-of-type(2) dd:nth-of-type(2)').textContent = summaryData.amount + " DH"; // Montant
    document.querySelector('.summary-content dl:nth-of-type(2) dd:nth-of-type(3)').textContent = summaryData.duration + " mois"; // Durée
    document.querySelector('.summary-content dl:nth-of-type(2) dd:nth-of-type(4)').textContent = summaryData.monthlyPayment + " DH"; // Mensualité
    document.querySelector('.summary-content dl:nth-of-type(2) dd:nth-of-type(5)').textContent = summaryData.applicationFee + " DH"; // Frais de dossier

    // Populate hidden form fields
    document.getElementById('hidden-amount').value = summaryData.amount;
    document.getElementById('hidden-duration').value = summaryData.duration;
    document.getElementById('hidden-monthlyPayment').value = summaryData.monthlyPayment;
    document.getElementById('hidden-applicationFee').value = summaryData.applicationFee;
});
