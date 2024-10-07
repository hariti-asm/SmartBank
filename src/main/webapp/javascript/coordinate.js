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