
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Document</title>
</head>
<body>
 <nav class="navbar navbar-light bg-dark"  >
        <div class="container" style="display: flex; flex-direction: row;">
          <a class="navbar-brand" href="#">
                <img src="/FinalProject/res/xworkz.png" alt="" width="100" height="50">
          </a>
          <div>
            <a class="navbar-brand" href="index.jsp" style="color: aliceblue;">home</a>
          </div>

        </div>
          </nav>
      <div class="container mt-5">
            <div class="row justify-content-center mb-3">
                <div class="col-md-6 custom-width">
                    <div class="card">
                        <div class="card-header">
                            <h4>View Complaints</h4>
                        </div>
                        <div class="card-body">
                            <form action="adminviewcomplaint" method="post">
                                <div class="mb-3">
                                    <label for="issueType" class="form-label">Issue Type:</label>
                                    <select class="form-control" id="issueType" name="issueType">
                                        <option value="">Select Issue Type</option>
                                        <option value="electricity">Electricity</option>
                                        <option value="water">Water</option>
                                        <option value="road">Road</option>
                                        <option value="drainage">Drainage</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="area" class="form-label">Area:</label>
                                    <input type="text" class="form-control" id="area" name="area">
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


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
      <th scope="col">assign</th>
      <th scope="col">action</th>
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
           <td class="d-none d-md-table-cell">

                                <c:if test="${inventory.status != 'resolved'}">
                               <form action="assigndepartment" method="post">
                                   <input type="hidden" name="id" value="${inventory.id}">

                                   <div class="btn-group">
                                       <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                           Assign
                                       </button>
                                       <ul class="dropdown-menu">
                                           <li><button class="dropdown-item" type="submit" name="assign" value="water">Water</button></li>
                                           <li><button class="dropdown-item" type="submit" name="assign" value="electricity">Electricity</button></li>
                                           <li><button class="dropdown-item" type="submit" name="assign" value="road">Road</button></li>
                                           <li><button class="dropdown-item" type="submit" name="assign" value="drainage">Drainage</button></li>
                                       </ul>
                                   </div>
                               </form></td>
        </c:if>
<td class="d-none d-md-table-cell">
                        <c:if test="${inventory.status != 'resolved'}">

                            <form action="action" method="post"> <!-- Status form -->
                                <input type="hidden" name="id" value="${inventory.id}">
                                  <div class="input-group">
                                <select class="form-select" id="status" name="status">
                                    <option value="0" ${selectedType == null ? 'selected' : ''}>Choose...</option>
                                    <option value="active" ${selectedType == 'active' ? 'selected' : ''}>Active</option>
                                    <option value="resolved" ${selectedType == 'resolved' ? 'selected' : ''}>Resolved</option>
                                    <option value="pending" ${selectedType == 'pending' ? 'selected' : ''}>Pending</option>

                                </select>
                                <td class="d-none d-md-table-cell">
                                   <button type="submit" class="btn btn-primary">Update</button>
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
 </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>

</body>
</html>