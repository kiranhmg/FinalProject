<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Employee Login</title>
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

      <div class="container mt-5 mb-5 d-flex justify-content-center">
          <div class="card px-5 py-4 bg-light shadow-lg p-3 mb-5 bg-white rounded">
              <div class="card-body">
                  <span class="text-danger">
                      <c:forEach items="${errors}" var="objectError">
                          ${objectError.defaultMessage}<br>
                      </c:forEach>
                  </span>
                  <span class="text-success fs-6 fw-bold">${msg}</span>
                  <c:if test="${not empty errorsMsg}">
                      <div class="alert alert-danger" role="alert">${errorsMsg}</div>
                  </c:if>

                  <h6 class="card-title mb-3 text-dark fs-2">Employee Sign In</h6>

                  <form action="getOtp" method="post">
                      <div class="form-group mb-3">
                          <label for="email">Email</label>
                          <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email Id" required value="${param.email}">
                          <span class="text-danger">${emailError}</span>
                      </div>
                      <div class="form-group mb-3 text-center">
                          <button class="btn btn-primary" id="getOtpBtn" type="submit">Get OTP</button>
                      </div>
                  </form>

    <c:if test="${param.email != null}">
                <form action="loginWithOtp" method="post">
                    <input type="hidden" name="email" value="${param.email}">
                    <div class="form-group mb-3">
                        <label for="otp">OTP</label>
                        <input type="text" class="form-control" id="otp" name="otp" placeholder="Enter your OTP" required>
                        <span class="text-danger">${otpError}</span>
                    </div>
                    <div class="form-group mb-3 text-center">
                        <button class="btn btn-primary" id="loginBtn" type="submit">Login</button>
                    </div>
                </form>
            </c:if>
        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>
</body>
</html>