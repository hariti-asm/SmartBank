// Get modal element
const modal = document.getElementById("statusModal");

function openModal(id) {
    console.log("Opening modal for credit request ID: " + id);
    modal.style.display = "block";
}
function closeModal() {
    modal.style.display = "none";
}

function updateStatus() {
    const selectedStatus = document.getElementById("statusSelect").value;
    const description = document.getElementById("description").value;

    console.log("Updated Status:", selectedStatus);
    console.log("Description:", description);
    closeModal();
}

window.onclick = function(event) {
    if (event.target == modal) {
        closeModal();
    }
}
