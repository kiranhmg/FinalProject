<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha384-KyZXEAg3QhqLMpG8r+Knujsl5/ieIJ6gIFhvjaCln2gq5oWeFf3vSUcIB+q5VR7j" crossorigin="anonymous">
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
             <div>
                <a class="navbar-brand" href="admin.jsp" style="color: aliceblue;">Admin Login</a>
              </div>
              <div>
                <a class="navbar-brand" href="Departmentadminlogin.jsp" style="color: aliceblue;">Department Admin Login</a>
               </div>
                <div>
                               <a class="navbar-brand" href="EmployeeLogin.jsp" style="color: aliceblue;">Employee Login</a>
                              </div>
        </div>
    </nav>
     <div class="container mt-5">
             <div class="card style=width: 18rem;">
                 <div class="card-header">
                     Sign In
                 </div>
                 <div class="card-body">
                     <form action="signindata" method="post" id="signinForm" onsubmit="return validate()">
                     ${msg}
                     ${msg1}
                      ${msg2}
                     <span style="color:red">
                     <c:forEach items="${valid}" var="obj">
                     ${obj.defaultMessage}
                     </c:forEach>
                     </span>


                         <div class="form-group mb-3">
                             <label for="email">Email</label>
                             <input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
                             <small class="error" id="emailError"></small>
                         </div>
                         <div class="form-group mb-3">
                             <label for="password">Password</label>
                             <input type="text" class="form-control" id="password" name="password" placeholder="Enter password">
                             <small class="error" id="passwordError"></small>
                         </div>
                          <div class="form-group mb-3">
                           <div class="captcha">
                                                                  <label for="captcha-input">Enter Captcha</label>
                                                                  <div id="captchaPreview" class="mb-2 bg-white p-2 text-center border"></div>
                                                                  <div class="captcha d-flex align-items-center">
                                                                      <input type="text" name="captcha" id="captcha" placeholder="Enter captcha text" class="form-control me-2">
                                                                      <button type="button" class="btn btn-secondary" onclick="generateCaptcha()">
                                                                          <i class="fas fa-sync-alt"></i>
                                                                           &#8635;
                                                                      </button>
                                                                  </div>
                                                                  <span id="captchaError" class="text-danger"></span>
                                                              </div>
                                                </div>

                         <input type="submit" class="btn btn-primary" value="submit" id="btn">
                         <a href="ForgogtPassword.jsp">Forgot Password</a>
                     </form>
                 </div>
             </div>
         </div>

         <script>
         let getFields=  {
         "captcha":false
         }

         function validate(){

         let flag=false;

         for(let[key,value] of Object.entries(getFields)){
         if(value===false){

         flag=true;
         break;
         }
         }
         if(!flag){
         document.getElementById("btn").removeAttribute("disabled");
         }else{
         document.getElementById("btn").setAttribute("disabled","");
         }
         }




         function generateCaptcha() {
             let characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
             captchaCode = '';
             for (let i = 0; i < 6; i++) {
                 captchaCode += characters.charAt(Math.floor(Math.random() * characters.length));
             }
             document.getElementById("captchaPreview").innerText = captchaCode;
             getFields["captcha"] = false; // Reset captcha validation
             document.getElementById("captchaError").innerText = ""; // Clear any previous error message
             validate();
         }

         function setCaptcha() {
             let captchaInput = document.getElementById("captcha").value.trim();
             let error = document.getElementById("captchaError");

             if (captchaInput === captchaCode) {
                 getFields["captcha"] = true;
                 error.innerHTML = "";
             } else {
                 error.innerHTML = "Please enter the correct captcha.";
                 error.style.color = 'red';
                 getFields["captcha"] = false;
             }
             validate();
         }

         document.addEventListener("DOMContentLoaded", function() {
             generateCaptcha();

             document.getElementById("captcha").addEventListener("input", setCaptcha);

             document.getElementById("signInForm").addEventListener("submit", function(event) {
                 setCaptcha(); // Validate captcha on form submit

                 if (!getFields["captcha"]) {
                     event.preventDefault(); // Prevent form submission if captcha is incorrect
                 }
             });
         });
         </script>
</body>
</html>

