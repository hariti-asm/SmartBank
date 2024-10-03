document.addEventListener('DOMContentLoaded', function() {
    const amountInput = document.getElementById('amount');
    const amountOutput = document.getElementById('amount-output');
    const durationInput = document.getElementById('duration');
    const durationOutput = document.getElementById('duration-output');
    const monthlyOutput = document.getElementById('monthly-output');
    const monthlyInput = document.getElementById('monthly');
    const fraisDossierOutput = document.getElementById('frais-dossier-output');
    const projectSelect = document.getElementById('project');
    const projectType = document.getElementById('project-type');

    function updateOutputs() {
        amountOutput.textContent = amountInput.value;
        durationOutput.textContent = durationInput.value;
        calculateMonthly();
        calculateFraisDossier();
        updateProjectType();
    }

    function calculateMonthly() {
        const K = parseFloat(amountInput.value); // capital emprunté
        const t = 0.12; // taux annuel (12% as an example)
        const n = parseFloat(durationInput.value); // nombre de mensualités

        if (isNaN(K) || isNaN(n) || n <= 0) {
            monthlyOutput.textContent = '0.00';
            monthlyInput.value = '0.00';
            return;
        }

        const monthlyRate = t / 12;
        const m = (K * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -n));

        monthlyOutput.textContent = m.toFixed(2);
        monthlyInput.value = m.toFixed(2);
    }

    function calculateFraisDossier() {
        const montant = parseFloat(amountInput.value);
        const pourcentageFraisDossier = 0.01;
        const fraisMin = 100;
        const fraisMax = 2000;

        let fraisDossier = montant * pourcentageFraisDossier;
        fraisDossier = Math.min(Math.max(fraisDossier, fraisMin), fraisMax);
        fraisDossierOutput.textContent = fraisDossier.toFixed(2);
    }

    function updateProjectType() {
        projectType.textContent = projectSelect.options[projectSelect.selectedIndex].text;
    }

    amountInput.addEventListener('input', updateOutputs);
    durationInput.addEventListener('input', updateOutputs);
    projectSelect.addEventListener('change', updateProjectType);

    // Initial calculation
    updateOutputs();

    // Form submission
    document.getElementById('loan-form').addEventListener('submit', function(e) {
        // Store values in localStorage
        localStorage.setItem('amount', amountInput.value);
        localStorage.setItem('duration', durationInput.value);
        localStorage.setItem('monthly', monthlyOutput.textContent);
        localStorage.setItem('fraisDossier', fraisDossierOutput.textContent);
        console.log("Form submitted. Stored values:", {
            amount: localStorage.getItem('amount'),
            duration: localStorage.getItem('duration'),
            monthly: localStorage.getItem('monthly'),
            fraisDossier: localStorage.getItem('fraisDossier')
        });

    });
});