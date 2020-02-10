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



function usingShips() {


    //is two cells big
    let ships = document.getElementById("ship1");
    ships.addEventListener("dragstart", dragStart(ship1));
    ships.addEventListener("dragend", dragEnd(ship1));
    // let ship2 = document.getElementById("ship2");
    // ship2.addEventListener("dragstart", dragStart(ship2));
    // ship2.addEventListener("dragend", dragEnd(ship2));
    // let ship3 = document.getElementById("ship3");
    // ship3.addEventListener("dragstart", dragStart(ship3));
    // ship3.addEventListener("dragend", dragEnd(ship3));
    // let ship4 = document.getElementById("ship4");
    // ship4.addEventListener("dragstart", dragStart(ship4));
    // ship4.addEventListener("dragend", dragEnd(ship4));

}

let ships = document.getElementsByClassName("ship");
ships.addEventListener("dragstart", dragStart);
ships.addEventListener("dragend", dragEnd);

let empties = document.querySelectorAll(".empty")
for (let empty in empties) {
    empty.addEventListener("dragover", dragOver)
    empty.addEventListener("dragEnter", dragEnter)
    empty.addEventListener("dragLeave", dragLeave)
    empty.addEventListener("drop", dragDrop)
}

function dragStart() {
    fill.className += "hold"
    fill.className = "invisible"
}

function dragEnd(fill) {
    fill.className = ship
}

function dragOver(e) {
    e.preventDefault()
    console.log("hi")
}

function dragEnter(e) {
    e.preventDefault()
    console.log("na")
}

function dragLeave() {

    console.log("wie")
}

function dragDrop() {
    console.log("gehts")
}