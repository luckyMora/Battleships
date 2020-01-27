// function login(evt) {
//     evt.preventDefault();
//     var form = evt.target.form;
//     $.post("/api/login", {
//         userName: form["userName"].value,
//         password: form["password"].value
//     })
//     console.log("form["
//             userName "].value")

// }

// function logout(evt) {
//     evt.preventDefault();
//     $.post("/api/logout")

// }

// async function getData() {

//     let response = await fetch(`http://localhost:8080/api/login`);
//     console.log(response);
//     let data = await response.json()
//     return data;

// }
fetchLogin = () => {
    const URL = `http://localhost:8080/api/login`;
    let userName = document.getElementById("Username").value
    let password = document.getElementById("password").value

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
            body: `userName=${userName}&password=${password}`
        })
        .then(response => {
            if (response.status === 200) {
                console.log("logged in!");
            } else {
                console.log("Invalid username or password");
            }
        })
        .catch(err => ("err", err));
};