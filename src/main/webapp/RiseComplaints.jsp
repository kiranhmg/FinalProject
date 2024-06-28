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
                     <form action="risecomplaint" method="post" id="signUpForm">
                     <span style="color:red">
                     <c:forEach items="${valid}" var="obj">
                     ${obj.defaultMessage}
                     </c:forEach>
                     </span>
                   <h1 style="color:red">${msg}</h1>
                   <h1 style="color:red">${failed}</h1>
                     <h1 style="color:green">${success}</h1>
                         <div class="form-group">
                                     <label for="issueType">Issue Type:</label>
                                     <select class="form-control" id="issueType" name="issueType">
                                         <option value="">Select Issue Type</option>
                                         <option value="electricity">Electricity</option>
                                         <option value="water">Water</option>
                                         <option value="road">Road</option>
                                         <option value="drainage">Drainage</option>
                                     </select>
                                 </div>

                                 <div class="form-group">
                                     <label for="country">Country:</label>
                                     <select class="form-control country" id="country" name="country" onchange="loadStates()">

                                          <option value="">Select Country</option>
                                     </select>
                                 </div>

                                 <div class="form-group">
                                     <label for="state">State:</label>
                                     <select class="form-control state" id="state" name="state" onchange="loadCities()" disabled>
                                        <option value="">Select state</option>
                                     </select>
                                 </div>

                                 <div class="form-group">
                                     <label for="city">City:</label>
                                     <select class="form-control city" id="city" name="city">

                                          <option value="">Select city</option>
                                     </select>
                                 </div>
                                  <label for="message">Description:</label>
                                             <textarea class="form-control" id="message" name="message" rows="6" maxlength="1500" oninput="updateCounter()"></textarea>
                                             <div class="counter">
                                                 <span id="charCount">0</span> / 1500 characters used

                                             </div>
                                              <div class="mb-3">
                                                                     <label for="area" class="form-label">Area:</label>
                                                                     <input type="text" class="form-control" id="area" name="area">
                                                                 </div>


                                                       <input type="submit" class="btn btn-primary" value="submit" name="submit">

                             </form>

                             <script>
                             function updateCounter() {
                                                                      var message = document.getElementById("message");
                                                                      var charCount = document.getElementById("charCount");
                                                                      var charRemaining = document.getElementById("charRemaining");
                                                                      var maxLength = message.getAttribute("maxlength");
                                                                      var currentLength = message.value.length;

                                                                      charCount.textContent = currentLength;
                                                                      charRemaining.textContent = (${maxLength - currentLength});
                                                   }

                                 var config = {
                                     countriesUrl: 'https://api.countrystatecity.in/v1/countries',
                                     statesUrl: 'https://api.countrystatecity.in/v1/countries/[ciso]/states',
                                     citiesUrl: 'https://api.countrystatecity.in/v1/countries/[ciso]/states/[siso]/cities',
                                     ckey: 'ODIzTTVJUGVIVWdQT1ZJbk1McU50RnJ0ZmtTUVNBcERseFdTb25SSA=='
                                 }

                                 var countrySelect = document.querySelector('.country'),
                                     stateSelect = document.querySelector('.state'),
                                     citySelect = document.querySelector('.city')

                                 function loadCountries() {
                                     fetch(config.countriesUrl, { headers: { "X-CSCAPI-KEY": config.ckey } })
                                         .then(response => response.json())
                                         .then(data => {
                                             data.forEach(country => {
                                                 const option = document.createElement('option')
                                                 option.value = country.iso2
                                                 option.textContent = country.name
                                                 countrySelect.appendChild(option)
                                             })
                                         })
                                         .catch(error => console.error('Error loading countries:', error))

                                     stateSelect.disabled = true
                                     citySelect.disabled = true
                                     stateSelect.style.pointerEvents = 'none'
                                     citySelect.style.pointerEvents = 'none'
                                 }

                                 function loadStates() {
                                     stateSelect.disabled = false
                                     citySelect.disabled = true
                                     stateSelect.style.pointerEvents = 'auto'
                                     citySelect.style.pointerEvents = 'none'

                                     const selectedCountryCode = countrySelect.value
                                     stateSelect.innerHTML = '<option value="">Select State</option>'
                                     citySelect.innerHTML = '<option value="">Select City</option>'

                                     fetch(config.statesUrl.replace('[ciso]', selectedCountryCode), { headers: { "X-CSCAPI-KEY": config.ckey } })
                                         .then(response => response.json())
                                         .then(data => {
                                             data.forEach(state => {
                                                 const option = document.createElement('option')
                                                 option.value = state.iso2
                                                 option.textContent = state.name
                                                 stateSelect.appendChild(option)
                                             })
                                         })
                                         .catch(error => console.error('Error loading states:', error))
                                 }

                                 function loadCities() {
                                     citySelect.disabled = false
                                     citySelect.style.pointerEvents = 'auto'
console.log('running loadCities');
                                     const selectedCountryCode = countrySelect.value
                                     const selectedStateCode = stateSelect.value
console.log('selectedCountryCode'+selectedCountryCode);
console.log('selectedStateCode'+selectedStateCode);
                                     citySelect.innerHTML = '<option value="">Select City</option>'

                                     fetch(config.citiesUrl.replace('[ciso]', selectedCountryCode).replace('[siso]', selectedStateCode), { headers: { "X-CSCAPI-KEY": config.ckey } })
                                         .then(response => response.json())
                                         .then(data => {
                                             data.forEach(city => {
                                                 const option = document.createElement('option')
                                                 option.value = city.name;
                                                 console.log(city);
                                                 option.textContent = city.name
                                                 citySelect.appendChild(option)
                                             })
                                         })
                                         .catch(error => console.error('Error loading cities:', error))
                                 }

                                 window.onload = loadCountries;
                             </script>

</body>
</html>
