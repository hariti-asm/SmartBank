let currentRequestId;

function openModal(requestId) {
    currentRequestId = requestId;
    const modal = document.getElementById('statusModal');
    modal.style.display = 'block';
    fetchStatusHistory(requestId);
}

function closeModal() {
    const modal = document.getElementById('statusModal');
    modal.style.display = 'none';
}

function fetchStatusHistory(requestId) {
    fetch(`http://localhost:8080/demo_war/api/creditRequests/${requestId}/statushistory`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            const historyDiv = document.getElementById('statusHistory');
            historyDiv.innerHTML = '<h4>Status History:</h4>';
            if (!data.statusHistory || data.statusHistory.length === 0) {
                historyDiv.innerHTML += '<p>No status history available for this request.</p>';
            } else {
                data.statusHistory.forEach(entry => {
                    historyDiv.innerHTML += `<p>${entry.status} - ${new Date(entry.updatedAt).toLocaleString()}</p>`;
                });
            }
        })
        .catch(error => {
            console.error('Error fetching status history:', error);
            const historyDiv = document.getElementById('statusHistory');
            historyDiv.innerHTML = `<p>Error fetching status history: ${error.message}</p>`;
        });
}

function updateStatus() {
    const statusName = document.getElementById('statusSelect').value;
    const description = document.getElementById('description').value;

    fetch(`http://localhost:8080/demo_war/api/creditRequests/${currentRequestId}/status`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            name: statusName,
            description: description
        }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.text();
        })
        .then(text => {
            if (!text) {
                throw new Error('Empty response received from server');
            }
            try {
                return JSON.parse(text);
            } catch (error) {
                console.error('Invalid JSON:', text);
                throw new Error('Invalid JSON response from server');
            }
        })
        .then(data => {
            console.log('Success:', data);
            fetchStatusHistory(currentRequestId);
            // Update the status in the table without reloading the page
            const statusCell = document.querySelector(`tr[data-id="${currentRequestId}"] td:nth-child(9)`);
            if (statusCell) {
                statusCell.textContent = statusName;
            }
            alert('Status updated successfully!');
        })
        .catch((error) => {
            console.error('Error:', error);
            alert(`Failed to update status: ${error.message}`);
        });
}window.onclick = function(event) {
    const modal = document.getElementById('statusModal');
    if (event.target == modal) {
        modal.style.display = "none";
    }
}