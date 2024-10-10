<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Credit Requests</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/requests.css">
</head>
<body>

<div class="container">
    <h1>Credit Requests</h1>

    <!-- New filter section -->
    <div class="filter-container">
        <label for="dateFilter">Filter by Date:</label>
        <input type="date" id="dateFilter">

        <label for="statusFilter">Filter by Status:</label>
        <select id="statusFilter">
            <option value="">All</option>
            <option value="pending">Pending</option>
            <option value="accepted">Accepted</option>
            <option value="rejected">Rejected</option>
        </select>

        <button onclick="applyFilters()">Apply Filters</button>
        <button onclick="resetFilters()">Reset Filters</button>
    </div>

    <c:if test="${creditRequestList.size() == 0}">
        <p>No credit requests found.</p>
    </c:if>

    <c:if test="${creditRequestList.size() > 0}">
        <table id="creditRequestsTable">
            <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>CIN</th>
                <th>Job</th>
                <th>Monthly Payment</th>
                <th>Revenues</th>
                <th>Request Date</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="creditRequest" items="${creditRequestList}">
                <tr data-id="${creditRequest.id}" data-date="${creditRequest.createdAt}" data-status="${creditRequest.statusHistory[creditRequest.statusHistory.size() - 1].status.name}">
                    <td>${creditRequest.id}</td>
                    <td>${creditRequest.firstName}</td>
                    <td>${creditRequest.lastName}</td>
                    <td>${creditRequest.cin}</td>
                    <td>${creditRequest.job}</td>
                    <td>${creditRequest.monthlyPayment}</td>
                    <td>${creditRequest.revenues}</td>
                    <td>${creditRequest.createdAt}</td>
                    <td>
                        <c:if test="${not empty creditRequest.statusHistory}">
                            ${creditRequest.statusHistory[creditRequest.statusHistory.size() - 1].status.name}
                        </c:if>
                    </td>
                    <td><button class="view-status-btn" onclick="openModal(${creditRequest.id})">View Status</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

</div>

<div id="statusModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>Update Credit Status</h2>

        <label for="statusSelect">Status Name:</label>
        <select id="statusSelect">
            <option value="pending">Pending</option>
            <option value="accepted">Accepted</option>
            <option value="rejected">Rejected</option>
        </select>

        <label for="description">Description:</label>
        <textarea id="description" rows="4" placeholder="Enter description..."></textarea>

        <button class="btn" onclick="updateStatus()">Update</button>

        <div id="statusHistory"></div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/requests.js"></script>
<script>
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
</script>

</body>
</html>