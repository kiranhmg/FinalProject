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
                     Add Department Admin
                 </div>
                 <div class="card-body">
                     <form action="addadminofdepartment" method="post" id="signUpForm">
                     <span style="color:red">
                     <c:forEach items="${valid}" var="obj">
                     ${obj.defaultMessage}
                     </c:forEach>
                     </span>
                   <h1 style="color:red">${msg}</h1>
                   <h1 style="color:red">${failed}</h1>
                     <h1 style="color:green">${success}</h1>
                         <div class="form-group">
                                     <label for="issue">Deparment:</label>
                                     <select class="form-control" id="issue" name="issue">
                                     <option value="0">choose deparment</option>
                                     <c:forEach items="${list}" var="department">
                                     <option value="${department.type}">${department.type}</option>
                                     </c:forEach>


                                     </select>
                                 </div>
                                 <div class="form-group mb-3">
                                   <label for="name">First Name</label>
                                  <input type="text" class="form-control" id="name" name="name" value="${dto.name}" placeholder="Enter first name"  onblur="validateFirstName()">
                                      <small class="error" id="firstNameError"></small>
                                    </div>

                                                         <div class="form-group mb-3">
                                                              <label for="email">Email</label>
                                                              <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" onblur="validateEmail()">
                                                              <small class="error" id="emailError"></small>
                                                          </div>
                                                          <div class="form-group mb-3">
                                                              <label for="phone">Phone</label>
                                                              <input type="tel" class="form-control" id="phone" name="phone" placeholder="Enter phone number" onblur="validatePhone()">
                                                              <small class="error" id="phoneError"></small>
                                                          </div>

                            <input type="submit" class="btn btn-primary" value="submit" name="submit">

                             </form>


</body>
</html>
