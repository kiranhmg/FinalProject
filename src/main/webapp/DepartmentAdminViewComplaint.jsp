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
             <div class="card style=width: 18rem;">
                 <div class="card-header">
                     Rise complaint
                 </div>
                 <div class="card-body">
                     <span style="color:red">
                     <c:forEach items="${valid}" var="obj">
                     ${obj.defaultMessage}
                     </c:forEach>
                     </span>
                   <h1 style="color:red">${msg}</h1>
                   <h1 style="color:red">${failed}</h1>
                     <h1 style="color:green">${success}</h1>

                             </form>
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
      <th scope="col">Assign</th>

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
                                       <form action="assigndepartmentemployee" method="post">
                                           <input type="hidden" name="id" value="${inventory.id}">
                                           <div class="btn-group">
                                               <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                                   Assign
                                               </button>
                                                <ul class="dropdown-menu">
                                                    <c:forEach items="${sessionScope.emplist}" var="department">
                                                        <li>

                                                                <button class="dropdown-item" type="submit" name="assign" value="${department.id}">
                                                                    ${department.name}
                                                                </button>

                                                        </li>
                                                    </c:forEach>
                                                </ul>

                                           </div>
                                       </form></td>
                </c:if>


        </tr>
  </c:forEach>

  </tbody>
</table>
</c:if>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>


</body>
</html>
