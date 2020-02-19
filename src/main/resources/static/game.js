const urlParams = new URLSearchParams(window.location.search);
const param = window.location.search.split("=")[1];
const myParam = urlParams.get("myParam");
let safedSalvoslocations = []
console.log("myParam", param);
let Shipsarray = [];
async function getData() {
    let response = await fetch(`http://localhost:8080/api/game_view/${param}`);
    console.log(response);
    let data = await response.json();
    console.log(data)
    for (let i = 0; i < data[0].Salvos.length; i++) {
        if (safedSalvoslocations.includes(data[0].Salvos[i].SalvoLocation)) {
            safedSalvoslocations.push(data[0].Salvos[i].SalvoLocation)
        }
    }
    //console.log(safedSalvoslocations)
    console.log(data);

    //                           Display Names on top of the tables
    let turn = 0
    let turnNr = data[0].Salvos.length
    console.log(turnNr)
    if (turnNr != 0) {
        turn = data[0].Salvos[turnNr - 1].SalvoTurnNumber;
    }
    console.log(turn)
    currentturn = turn;
    console.log(currentturn)
    let x = data[0].GamplayerID;
    let myname = data[0].User;
    let enemyName = data[0].Enemy[0].Enemy_User_Name;
    console.log(enemyName);
    console.log(x);
    console.log(myname);
    let player2 = document.getElementById("player2");
    let player1 = document.getElementById("player1");
    player1.innerHTML = myname;
    player2.innerHTML = enemyName;

    let myShipsLocation = [];

    //                                Display Ships
    data[0].Ships.forEach(ship => {
        ship.ShipLocation.forEach(location => {
            myShipsLocation.push(location);
            document.getElementById(location).className = "filled";
        });
    });
    console.log(myShipsLocation);
    //                               Display Enemy Salvo
    data[0].Enemy[0].Enemy_Salvos.forEach(eSalvo => {
        if (myShipsLocation.includes(eSalvo.SalvoLocation)) {
            document.getElementById(eSalvo.SalvoLocation).style.backgroundColor =
                "red";
        } else {
            document.getElementById(eSalvo.SalvoLocation).style.backgroundColor =
                "gray";
        }
    });
    //                               Display my Salvos

    data[0].Salvos.forEach(salvo => {
        document.getElementById("1" + salvo.SalvoLocation).className = "shooting";
    });

    if (myShipsLocation.length > 0) {
        document.getElementById("ShipBtn").className = "invisible"
    }
    return data;
}

console.log("test");
let currentturn = 0;
getData()
let dragStartShip = "";
let shipid = "";
let cellid = "";

function onDragStart(event) {
    event.dataTransfer.setData("ShipID", event.target.id);
    dragStartShip = event.target;
    console.log(dragStartShip);
    this.shipid = event.target.id;
    console.log(this.shipid);

    setTimeout(() => (event.target.className = "invisible"), 0);
}

function onDrop(event) {
    event.dataTransfer.setData("CellID", event.target.id);
    if (
        event.target.className == "filled" ||
        event.target.className !== "empty" ||
        event.target.className == ""
    ) {
        dragStartShip.classList.remove("invisible");
        dragStartShip = "";
    } else {
        event.target.className = "filled";
        this.cellid = event.target.id;
        console.log(this.cellid);
        createShips(this.cellid);
        dragStartShip = "";
    }
}

function positionShips(länge, a, b, cid, sid) {
    let checked1 = document.querySelectorAll("input[type=checkbox]:checked");
    let array = [];
    let completeShip = {
        type: sid,
        locations: [cid]
    };
    for (let j = 0; j < checked1.length; j++) {
        array.push(checked1[j].name);
    }
    console.log(array);
    if (array.includes("vertical" + b)) {
        if (this.cellid.slice(0, 1) > a) {
            let ShipLength = länge;
            let zahl1 = this.cellid.slice(0, 1);
            let zahl = this.cellid.slice(1);
            let m = parseInt(zahl1);
            for (let i = 0; i < ShipLength; i++) {
                m--;
                let zahl2 = m.toString();
                let IDzahl = zahl2 + zahl;
                document.getElementById(IDzahl).className = "filled";
                completeShip.locations.push(IDzahl);
            }
        } else {
            let ShipLength = länge;
            let zahl1 = this.cellid.slice(0, 1);
            let zahl = this.cellid.slice(1);
            let m = parseInt(zahl1);
            for (let i = 0; i < ShipLength; i++) {
                m++;
                let zahl2 = m.toString();
                let IDzahl = zahl2 + zahl;
                document.getElementById(IDzahl).className = "filled";
                completeShip.locations.push(IDzahl);
            }
        }
    } else {
        if (this.cellid.slice(1) > a) {
            let ShipLength = länge;
            let zahl1 = this.cellid.slice(0, 1);
            let zahl = this.cellid.slice(1);
            let m = parseInt(zahl);
            for (let i = 0; i < ShipLength; i++) {
                m--;
                let zahl2 = m.toString();
                let IDzahl = zahl1 + zahl2;
                document.getElementById(IDzahl).className = "filled";
                completeShip.locations.push(IDzahl);
            }
        } else {
            let ShipLength = länge;
            let zahl1 = this.cellid.slice(0, 1);
            let zahl = this.cellid.slice(1);
            let m = parseInt(zahl);
            for (let i = 0; i < ShipLength; i++) {
                m++;
                let zahl2 = m.toString();
                let IDzahl = zahl1 + zahl2;
                document.getElementById(IDzahl).className = "filled";
                completeShip.locations.push(IDzahl);
            }
        }
    }
    Shipsarray.push(completeShip);
    console.log(Shipsarray);
}

function createShips(id) {
    if (this.shipid == "submarine") {
        positionShips(1, 8, "1", id, "submarine");
    } else if (this.shipid == "destroyer") {
        positionShips(2, 7, "2", id, "destroyer");
    } else if (this.shipid == "cruiser") {
        positionShips(3, 6, "3", id, "cruiser");
    } else if ((this.shipid = "carrier")) {
        positionShips(4, 5, "4", id, "carrier");
    }
}

function onDragOver(event) {
    event.preventDefault();
}

document.getElementById("resetAllbutton").addEventListener("click", () => {
    Shipsarray = [];
    let blacktdlist = document
        .getElementById("mytable")
        .querySelectorAll("td[class=filled]");
    for (let i = 0; i < blacktdlist.length; i++) {
        blacktdlist[i].className = "empty";
    }
    let invisbleships = document.querySelectorAll(".invisible");
    console.log(invisbleships);
    for (let j = 0; j < invisbleships.length; j++) {
        invisbleships[j].classList.remove("invisible");
    }
    console.log(invisbleships);
});

document.getElementById("fixButton").addEventListener("click", () => {
    console.log("Shipsarray", Shipsarray);

    if (Shipsarray.length == 4) {
        fetch(`/api/games/players/${param}/ships`, {
                method: "POST",
                credentials: "include",
                headers: {
                    "Content-type": "application/json"
                },
                body: JSON.stringify(Shipsarray)
            })
            .then(response => {
                console.log(response);

                if (response.status === 201) {
                    console.log("Ships send to backend");
                    document.getElementById("ShipBtn").className = "invisible";
                } else {
                    console.log("failed to send ships");
                }
            })
            .then(res => {
                console.log(res);
            })
            .catch(error => console.log(error));
    } else {
        alert("You did not post enough Ships");
    }
});


let salvoLocArray = []

console.log("element", document.getElementsByClassName("enemyGrid"));
Array.from(document.getElementsByClassName("enemyGrid")).forEach(elem =>
    elem.addEventListener("click", () => {
        salvoLocArray.push(elem.id)
        let salvoId = elem.id
        let saLocaton = salvoId.slice(1)
        console.log()
        console.log("nööööö")
        if (safedSalvoslocations.includes(saLocaton)) {
            console.log("position already destroyed")
            salvoLocArray = []
        } else {

            if (salvoLocArray.length <= 1) {
                console.log(salvoLocArray.length);

                document.getElementById(salvoId).className = "shooting"
                let salvoButton = document.getElementById("salvoBtn")
                let rSalvoButton = document.createElement("button")
                rSalvoButton.innerHTML = "Reset Salvo"
                let sSalvoButton = document.createElement("button")
                sSalvoButton.innerHTML = "Send Salvo"
                salvoButton.appendChild(sSalvoButton)
                salvoButton.appendChild(rSalvoButton)
                rSalvoButton.onclick = () => {
                    salvoLocArray = []
                    rSalvoButton.style.display = "none";
                    sSalvoButton.style.display = "none";
                    document.getElementById(salvoId).classList.remove("shooting")
                }
                sSalvoButton.onclick = () => {
                    rSalvoButton.style.display = "none";
                    sSalvoButton.style.display = "none";


                    console.log(saLocaton)
                    safedSalvoslocations.push(saLocaton)
                    currentturn++
                    let completeSalvo = {
                        turnNumber: currentturn,
                        salvolocations: saLocaton
                    };
                    fetch(`/api/games/players/${param}/salvos`, {
                            method: "POST",
                            credentials: "include",
                            headers: {
                                "Content-type": "application/json"
                            },
                            body: JSON.stringify(completeSalvo)
                        })
                        .then(response => {
                            console.log(response);

                            if (response.status === 201) {
                                console.log("Salvo send to backend");
                            } else {
                                console.log("failed to send salvo");
                            }
                        })
                        // .then(res => {
                        //     console.log(res);

                        // })
                        .catch(error => console.log(error));

                    console.log("lucky")
                    setTimeout(() => (getData()), 100)
                    salvoLocArray = []

                }

            }
        }
    })
);