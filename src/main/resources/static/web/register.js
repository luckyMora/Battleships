fetchRegister = () => {
    const URL = `http://localhost:8080/api/players`;
    let userName = document.getElementById("username").value
    let email = document.getElementById("email").value
    let firstName = document.getElementById("Firstname").value
    let lastName = document.getElementById("Lastname").value
    let password = document.getElementById("Password").value
    // $.post(URL, {
    //     userName,
    //     password
    // })
    fetch(URL, {
            method: "POST",
            // credentials: "include",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `userName=${userName}&email=${email}&firstName=${firstName}&lastName=${lastName}&password=${password}`
        })
        .then(response => {
            if (response.status === 201) {

                console.log("sign uped");
            } else {
                alert("Invalid");
                console.log("something is invalid");
            }
        })
        .catch(err => ("err", err))
}