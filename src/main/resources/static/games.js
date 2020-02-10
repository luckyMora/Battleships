const urlParams = new URLSearchParams(window.location.search);
const param = window.location.search.split("=")[1]
const myParam = urlParams.get('myParam');
console.log('myParam', param)

async function getData() {

    let response = await fetch(`http://localhost:8080/api/game_view/${param}`);
    console.log(response);
    let data = await response.json()
    return data;

}





console.log("test");

getData()
    .then(data => {
        console.log(data);
        //                           Display Names on top of the tables
        let Player = {};
        let enemy = {};
        console.log("na")
        let x = data[0].GamplayerID
        let myname = data[0].User
        let enemyName = data[0].Enemy[0].Enemy_User_Name
        console.log(enemyName);
        console.log(x);
        console.log(myname);
        let player2 = document.getElementById("player2")
        let player1 = document.getElementById("player1")
        player1.innerHTML = myname
        player2.innerHTML = enemyName

        let myShipsLocation = [];

        //                                Display Ships
        data[0].Ships.forEach(ship => {
            ship.ShipLocation.forEach(location => {
                myShipsLocation.push(location)
                console.log('location', location)
                document.getElementById(location).style.backgroundColor = "black";
            })

        });
        console.log(myShipsLocation)
        //                               Display Enemy Salvo
        data[0].Enemy[0].Enemy_Salvos.forEach(eSalvo => {
            if (myShipsLocation.includes(eSalvo.SalvoLocation)) {
                document.getElementById(eSalvo.SalvoLocation).style.backgroundColor = "red";
            } else {
                document.getElementById(eSalvo.SalvoLocation).style.backgroundColor = "gray";
            }
        })
        //                               Display my Salvos
        data[0].Salvos.forEach(salvo => {
            document.getElementById("1" + salvo.SalvoLocation).style.backgroundColor = "green";
        })



    })



    .catch(error => console.log(error));