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
            <img src="${pageContext.request.contextPath}${sessionScope.profilefind}" width="70" height="70" value="" class="rounded-circle profile-image id=profileImage">
        </div>
    </nav>
     <div class="container mt-5">
             <div class="card style=width: 18rem;">
                 <div class="card-header">
                   Profile Edit
                 </div>
                 <div class="card-body">
                     <form action="editProfile" method="post" id="signUpForm" enctype="multipart/form-data">
                     <span style="color:red">
                     <c:forEach items="${valid}" var="obj">
                     ${obj.defaultMessage}
                     </c:forEach>
                     </span>
                   <h1 style="color:red">${msg}</h1>
                   <h1 style="color:red">${failed}</h1>
                     <h1 style="color:green">${success}</h1>
                         <div class="form-group mb-3">
                             <label for="firstName">First Name</label>
                             <input type="text" class="form-control" id="firstName" name="firstName"  value="${sessionScope.firstName}" placeholder="Enter first name">
                             <small class="error" id="firstNameError"></small>
                         </div>
                         <div class="form-group mb-3">
                             <label for="lastName">Last Name</label>
                             <input type="text" class="form-control" id="lastName" name="lastName"  value="${sessionScope.lastName}" placeholder="Enter last name">
                             <small class="error" id="lastNameError"></small>
                         </div>
                         <div class="form-group mb-3">
                             <label for="email">Email</label>
                             <input type="email" class="form-control" id="email" name="email" ${sessionScope.action =='edit'?'readonly':''} value="${sessionScope.email}" placeholder="Enter email">
                             <small class="error" id="emailError"></small>
                         </div>
                         <div class="form-group mb-3">
                             <label for="phone">Phone</label>
                             <input type="tel" class="form-control" id="phone" name="phone"  value="${sessionScope.phone}" placeholder="Enter phone number">
                             <small class="error" id="phoneError"></small>
                         </div>
                         <div class="form-group form-check mb-3">
                             <label for="fileUpload">Upload File:</label>
                            <input type="file" class="form-control" id="fileUpload" name="fileUpload">
                         </div>
                         <input type="submit" class="btn btn-primary" value="Upload">
                     </form>
                 </div>
             </div>
         </div>
</body>
</html>
