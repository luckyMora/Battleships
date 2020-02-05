fetchLogin = () => {
    const URL = `http://localhost:8080/api/login`;
    let userName = document.getElementById("Username").value
    let password = document.getElementById("password").value


    fetch(URL, {
            method: "POST",
            credentials: "include",
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
                alert("Invalid username or password")
                console.log("Invalid username or password");
            }
        })
        .catch(err => ("err", err));
};

logout = () => {
    const URL = `http://localhost:8080/api/logout`;
    let userName = document.getElementById("Username").value
    let password = document.getElementById("password").value


    fetch(URL, {
            method: "POST",
        })
        .then(response => {
            if (response.status === 200) {
                console.log("logged out!");
            } else {
                console.log("log out did not work")
            }
        })
        .catch(err => ("err", err));
};