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
function applyFilters() {
    const dateFilter = document.getElementById('dateFilter').value;
    const statusFilter = document.getElementById('statusFilter').value.toLowerCase();
    const table = document.getElementById('creditRequestsTable');
    const rows = table.getElementsByTagName('tr');

    for (let i = 1; i < rows.length; i++) {
        const row = rows[i];
        const rowDate = row.getAttribute('data-date');
        const rowStatus = row.getAttribute('data-status').toLowerCase();

        let showRow = true;

        if (dateFilter && rowDate !== dateFilter) {
            showRow = false;
        }

        if (statusFilter && rowStatus !== statusFilter) {
            showRow = false;
        }

        row.style.display = showRow ? '' : 'none';
    }
}

function resetFilters() {
    document.getElementById('dateFilter').value = '';
    document.getElementById('statusFilter').value = '';
    const table = document.getElementById('creditRequestsTable');
    const rows = table.getElementsByTagName('tr');
    for (let i = 1; i < rows.length; i++) {
        rows[i].style.display = '';
    }
}
function fetchStatusHistory(requestId) {
    fetch(`http://localhost:8080/demo_war/api/creditRequests/${requestId}/statushistory`)
        .then(response => {
            if (!response.ok) {
                return response.json().then(errorData => {
                    throw new Error(JSON.stringify(errorData, null, 2));
                });
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
            historyDiv.innerHTML = `<p>Error fetching status history:</p><pre>${error.message}</pre>`;
            alert(`Failed to fetch status history. Check the console for details.`);
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
                return response.json().then(errorData => {
                    throw new Error(JSON.stringify(errorData, null, 2));
                });
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            // Update the currentRequestId with the new credit request ID
            currentRequestId = data.newCreditRequestId;
            fetchStatusHistory(currentRequestId);
            // Update the status and ID in the table without reloading the page
            const row = document.querySelector(`tr[data-id="${currentRequestId}"]`);
            if (row) {
                row.setAttribute('data-id', currentRequestId);
                const statusCell = row.querySelector('td:nth-child(9)');
                if (statusCell) {
                    statusCell.textContent = statusName;
                }
            }
            alert('New credit request created with updated status!');
        })
        .catch((error) => {
            console.error('Error:', error);
            alert(`Failed to update status. Check the console for details.`);
        });
}

window.onclick = function(event) {
    const modal = document.getElementById('statusModal');
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
