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
                        <a class="navbar-brand" href="RiseComplaints.jsp" style="color: aliceblue;">RiseComplaint</a>
                           </div>
               <a class="navbar-brand" href="viewcomplaint" style="color: aliceblue;">View complaints</a>

              <div>
            <a class="navbar-brand" href="ProfileEdit.jsp" style="color: aliceblue;">Edit Profile</a>
               </div>
                 </div>
                           <img src="${pageContext.request.contextPath}${sessionScope.profilefind}" width="70" height="70" value="" class="rounded-circle profile-image id=profileImage">
                       </div>
        </div>
    </nav>
</body>
</html>
