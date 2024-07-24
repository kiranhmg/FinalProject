 function validateFirstName() {
            const firstName = document.getElementById("firstName").value.trim();
            const firstNameError = document.getElementById("firstNameError");

            if (firstName === "") {
                firstNameError.innerHTML = "First Name is required.";
            } else {
                firstNameError.textContent = "";
            }
        }

        function validateLastName() {
            const lastName = document.getElementById("lastName").value.trim();
            const lastNameError = document.getElementById("lastNameError");

            if (lastName === "") {
                lastNameError.textContent = "Last Name is required.";
            } else {
                lastNameError.textContent = "";
            }
        }

        function validateEmail() {
            const email = document.getElementById("email").value.trim();
            const emailError = document.getElementById("emailError");
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (email === "") {
                emailError.textContent = "Email is required.";
            } else if (!emailPattern.test(email)) {
                emailError.textContent = "Invalid email format.";
            } else {
                // AJAX call to validate email
                const xhr = new XMLHttpRequest();
                xhr.open("GET", `http://localhost:8080/FinalProject/validateEmail?email=${encodeURIComponent(email)}`, true);
                xhr.setRequestHeader("Content-Type", "application/json");

                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4) {
                        if (xhr.status === 200) {
                            const responseText = xhr.responseText.trim();
                            if (responseText !== "") {
                                emailError.textContent = responseText;
                            } else {
                                emailError.textContent = "";
                            }
                        } else {
                            console.error('Error validating email:', xhr.statusText);
                        }
                    }
                };

                xhr.send();
            }
        }

        function validatePhone() {
            const phone = document.getElementById("phone").value.trim();
            const phoneError = document.getElementById("phoneError");
            const phonePattern = /^[0-9]{10}$/;

            if (phone === "") {
                phoneError.textContent = "Phone number is required.";
            } else if (!phonePattern.test(phone)) {
                phoneError.textContent = "Invalid phone number format.";
            } else {
                // AJAX call to validate phone
                const xhr = new XMLHttpRequest();
                xhr.open("GET", `http://localhost:8080/FinalProject/validatePhone?phone=${encodeURIComponent(phone)}`, true);
                xhr.setRequestHeader("Content-Type", "application/json");

                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4) {
                        if (xhr.status === 200) {
                            const responseText = xhr.responseText.trim();
                            if (responseText !== "") {
                                phoneError.textContent = responseText;
                            } else {
                                phoneError.textContent = "";
                            }
                        } else {
                            console.error('Error validating phone:', xhr.statusText);
                        }
                    }
                };

                xhr.send();
            }
        }