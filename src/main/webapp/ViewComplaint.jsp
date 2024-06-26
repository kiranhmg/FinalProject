
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
     <div class="container">
            <h2>volunteersearchbyaddress</h2>
            <form  action="viewcomplaint" method="post">


            <input type="submit" class="btn btn-primary" value="search">
            </form>


              </br>
              ${key}

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
      <th scope="col">Edit</th>

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
       <td><a href="risecomplaint?id=${inventory.id}&edit=edit"> Edit</a></td>

        </tr>
  </c:forEach>

  </tbody>
</table>
</c:if>
 </div>

</body>
</html>