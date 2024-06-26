  function validateEmail() {
                         let email = document.getElementById("email").value.trim();
                         let error = document.getElementById("emailError");

                         let xhr = new XMLHttpRequest();
                         xhr.open("GET", `http://localhost:8080/FinalProject/validateEmail?email=${encodeURIComponent(email)}`, true);
                         xhr.setRequestHeader("Content-Type", "application/json");

                         xhr.onreadystatechange = function() {
                             if (xhr.readyState === 4) {
                                 if (xhr.status === 200) {
                                     let responseText = xhr.responseText.trim();
                                     if (responseText !== "") {
                                         error.innerHTML = responseText;
                                         error.style.color = 'red';
                                     } else {
                                         error.innerHTML = "";
                                     }
                                 } else {
                                     console.error('Error validating email:', xhr.statusText);
                                 }
                             }
                         };

                         xhr.send();
                     }

                       function validatePhone() {
                                 let phone = document.getElementById("phone").value.trim();
                                 let error = document.getElementById("phoneError");

                                 let xhr = new XMLHttpRequest();
                                 xhr.open("GET", `http://localhost:8080/FinalProject/validatePhone?phone=${encodeURIComponent(phone)}`, true);
                                 xhr.setRequestHeader("Content-Type", "application/json");

                                 xhr.onreadystatechange = function() {
                                     if (xhr.readyState === 4) {
                                         if (xhr.status === 200) {
                                             let responseText = xhr.responseText.trim();
                                             if (responseText !== "") {
                                                 error.innerHTML = responseText;
                                                 error.style.color = 'red';
                                             } else {
                                                 error.innerHTML = "";
                                             }
                                         } else {
                                             console.error('Error validating phone:', xhr.statusText);
                                         }
                                     }
                                 };

                                 xhr.send();
                             }