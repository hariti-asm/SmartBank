function displayStoredData() {
    // Retrieve values from local storage
    const project = localStorage.getItem('project') || 'N/A';
    const status = localStorage.getItem('status') || 'N/A';
    const amount = localStorage.getItem('amount') || 'N/A';
    const duration = localStorage.getItem('duration') || 'N/A';
    const monthlyPayment = localStorage.getItem('monthly') || 'N/A';
    const fraisDossier = localStorage.getItem('fraisDossier') || 'N/A';

    document.getElementById('project').textContent = project;
    document.getElementById('status').textContent = status;
    document.getElementById('amount').textContent = amount + ' DH';
    document.getElementById('duration').textContent = duration + ' mois';
    document.getElementById('monthlyPayment').textContent = monthlyPayment + ' DH';
    document.getElementById('frais-dossier-output').textContent = fraisDossier + ' DH';
}

window.onload = displayStoredData;