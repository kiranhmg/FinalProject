<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>admin login</title>
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
        </div>
    </nav>
     <div class="container mt-5">
             <div class="card style=width: 18rem;">

                 <div class="card-body">




                         <div class="form-group mb-3">
                            <h1>Sign up details:</h1>
                         </div>


                     </form>
                 </div>
             </div>

             <c:if test="${list.isEmpty() == false}">
             <table class="table table-dark">
               <thead>
                 <tr>
                   <th scope="col">id</th>
                   <th scope="col">first name</th>
                   <th scope="col">last name</th>
                   <th scope="col">email</th>
                     <th scope="col">phone</th>
                      <th scope="col">created by</th>
                       <th scope="col">created date</th>

                 </tr>
               </thead>
               <tbody>
               <c:forEach items="${list}" var="inventory">
                <tr>
                     <th scope="row">${inventory.id}</th>
                     <td>${inventory.firstName}</td>
                     <td>${inventory.lastName}</td>
                     <td>${inventory.email}</td>
                      <td>${inventory.phone}</td>
                      <td>${inventory.createdBy}</td>
                        <td>${inventory.createdDate}</td>



                     </tr>
               </c:forEach>

               </tbody>
             </table>
             </c:if>
         </div>
</body>
</html>

