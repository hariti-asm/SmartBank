document.addEventListener('DOMContentLoaded', function() {
    const amountInput = document.getElementById('amount');
    const amountOutput = document.getElementById('sliderValue');
    const durationInput = document.getElementById('duration');
    const durationOutput = document.getElementById('durationValue');
    const monthlyOutput = document.getElementById('mensualiteValue');
    const fraisDossierOutput = document.getElementById('frais-dossier-output');
    const projectSelect = document.getElementById('project');
    const projectType = document.getElementById('project-type');

    function updateOutputs() {
        amountOutput.value = amountInput.value;
        durationOutput.value = durationInput.value;
        calculateMonthly();
        calculateFraisDossier();
        updateProjectType();
    }

    function calculateMonthly() {
        const K = parseFloat(amountInput.value);
        const t = 0.12;
        const n = parseFloat(durationInput.value);
        if (isNaN(K) || isNaN(n) || n <= 0) {
            monthlyOutput.value = '0.00';
            monthlyInput.value = '0.00';
            return;
        }

        const monthlyRate = t / 12;
        const m = (K * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -n));

        monthlyOutput.value = m.toFixed(2);
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

    updateOutputs();

    document.getElementById('loan-form').addEventListener('submit', function(e) {
        localStorage.setItem('amount', amountInput.value);
        localStorage.setItem('duration', durationInput.value);
        localStorage.setItem('monthly', monthlyOutput.value);
        localStorage.setItem('fraisDossier', fraisDossierOutput.textContent);
        console.log("Form submitted. Stored values:", {
            amount: localStorage.getItem('amount'),
            duration: localStorage.getItem('duration'),
            monthly: localStorage.getItem('monthly'),
            fraisDossier: localStorage.getItem('fraisDossier')
        });
    });
});
