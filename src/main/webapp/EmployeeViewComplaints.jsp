<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>sign up</title>
     <style>
            .error {
                color: red;
                font-size: 0.9em;
            }
        </style>
        <script src="/FinalProject/res/signup.js"></script>
</head>
<body>
    <nav class="navbar navbar-light bg-dark">
        <div class="container" style="display: flex; flex-direction: row;">
            <a class="navbar-brand" href="#">
                <img src="/FinalProject/res/xworkz.png" alt="" width="100" height="50">
            </a>
            <div>
                <a class="navbar-brand" href="index.jsp" style="color: aliceblue;">Home</a>
            </div>
        </div>
    </nav>
     <div class="container mt-5">


<c:if test="${list.isEmpty() == false}">
<table class="table table-dark">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">issueType</th>
      <th scope="col">country</th>
      <th scope="col">state</th>
      <th scope="col">city</th>
       <th scope="col">status</th>
      <th scope="col">message</th>
       <th scope="col">area</th>
      <th scope="col">Assign</th>
        <th scope="col">update</th>

    </tr>
  </thead>
  <tbody>
  <c:forEach items="${list}" var="inventory">
   <tr>
        <th scope="row">${inventory.id}</th>
        <td>${inventory.issueType}</td>
        <td>${inventory.country}</td>
        <td>${inventory.state}</td>
        <td>${inventory.city}</td>
        <td>${inventory.status}</td>
        <td>${inventory.message}</td>
         <td>${inventory.area}</td>
         <td class="d-none d-md-table-cell">
                                 <c:if test="${inventory.status != 'resolved'}">

                                     <form action="actionupdate" method="post"> <!-- Status form -->
                                         <input type="hidden" name="id" value="${inventory.id}">
                                           <div class="input-group">
                                         <select class="form-select" id="status" name="status">
                                             <option value="0" ${selectedType == null ? 'selected' : ''}>Choose...</option>
                                             <option value="active" ${selectedType == 'active' ? 'selected' : ''}>Active</option>
                                             <option value="resolved" ${selectedType == 'resolved' ? 'selected' : ''}>Resolved</option>
                                             <option value="pending" ${selectedType == 'pending' ? 'selected' : ''}>Pending</option>

                                         </select>
                                         <td class="d-none d-md-table-cell">
                                            <button type="submit" class="btn btn-primary update-btn">Update</button>
                                             </td>
                                          </div>
                                        </form>
                                 </td>
                                  </c:if>
           <c:if test="${inventory.status == 'resolved'}">
         <td></td>
          </c:if>
                 </tr>
</c:forEach>
  </tbody>
</table>
</c:if>

<!-- Modal for updating status -->
<div class="modal fade" id="statusModal" tabindex="-1" aria-labelledby="statusModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="statusModalLabel">Update Status</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="statusForm" action="updateStatus" method="post">
                    <input type="hidden" name="id" id="complaintId">
                    <input type="hidden" name="status" id="complaintStatus">
                    <div class="mb-3">
                        <label for="comments" class="form-label">Comment</label>
                        <textarea class="form-control" id="comments" name="comments" rows="3"></textarea>
                    </div>
                    <div class="mb-3" id="otpSendSection" style="display: none;">
                        <button type="button" class="btn btn-secondary mt-2" id="sendOtpButton">Send OTP</button>
                    </div>
                    <div class="mb-3" id="otpEnterSection" style="display: none;">
                        <label for="otp" class="form-label">Enter OTP</label>
                        <input type="text" class="form-control" id="otp" name="otp">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                     <c:if test="${not empty otpError}">
                                    <div class="alert alert-danger mt-3">${otpError}</div>
                                </c:if>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>
<script>
   document.querySelectorAll('.update-btn').forEach(function(button) {
       button.addEventListener('click', function() {
           const complaintId = this.dataset.complaintId;
           const form = this.closest('form');
           const status = form.querySelector('.status-select').value;
           const modal = new bootstrap.Modal(document.getElementById('statusModal'), {
               backdrop: 'static',
               keyboard: false
           });

           document.getElementById('complaintId').value = complaintId;
           document.getElementById('complaintStatus').value = status;

           if (status === 'resolved') {
               document.getElementById('otpSendSection').style.display = 'block';
           } else {
               document.getElementById('otpSendSection').style.display = 'none';
               document.getElementById('otpEnterSection').style.display = 'none';
           }

           modal.show();
       });
   });

   document.getElementById('sendOtpButton').addEventListener('click', function() {
       document.getElementById('otpSendSection').style.display = 'none';
       document.getElementById('otpEnterSection').style.display = 'block';
   });
</script>


</body>
</html>
