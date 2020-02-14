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
        // let Player = {};
        // let enemy = {};

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
                document.getElementById(location).className = "filled";
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
let dragStartShip = "";
let shipid = "";
let cellid = "";
let Shipsarray = [];

function onDragStart(event) {
    event
        .dataTransfer
        .setData('ShipID', event.target.id);
    dragStartShip = event.target
    console.log(dragStartShip)
    this.shipid = event.target.id
    console.log(this.shipid)

    setTimeout(() => event.target.className = "invisible", 0)


}


function onDrop(event) {

    event
        .dataTransfer
        .setData('CellID', event.target.id);
    if (event.target.className == "filled" || event.target.className !== "empty" || event.target.className == "") {
        dragStartShip.classList.remove("invisible")
        dragStartShip = ""

    } else {

        event.target.className = "filled";
        this.cellid = event.target.id
        console.log(this.cellid)
        createShips(this.cellid)
        dragStartShip = ""
    }
}



function positionShips(länge, a, b, cid, sid) {
    let checked1 = document.querySelectorAll("input[type=checkbox]:checked")
    let array = []
    let completeShip = {
        type: sid,
        locations: [cid]
    }
    for (let j = 0; j < checked1.length; j++) {
        array.push(checked1[j].name)

    }
    console.log(array)
    if (array.includes("vertical" + b)) {
        if (this.cellid.slice(0, 1) > a) {
            let ShipLength = länge
            let zahl1 = this.cellid.slice(0, 1)
            let zahl = this.cellid.slice(1)
            let m = parseInt(zahl1)
            for (let i = 0; i < ShipLength; i++) {
                m--
                let zahl2 = m.toString()
                let IDzahl = zahl2 + zahl
                document.getElementById(IDzahl).className = "filled"
                completeShip.locations.push(IDzahl)

            }
        } else {
            let ShipLength = länge
            let zahl1 = this.cellid.slice(0, 1)
            let zahl = this.cellid.slice(1)
            let m = parseInt(zahl1)
            for (let i = 0; i < ShipLength; i++) {
                m++
                let zahl2 = m.toString()
                let IDzahl = zahl2 + zahl
                document.getElementById(IDzahl).className = "filled"
                completeShip.locations.push(IDzahl)

            }
        }
    } else {
        if (this.cellid.slice(1) > a) {
            let ShipLength = länge
            let zahl1 = this.cellid.slice(0, 1)
            let zahl = this.cellid.slice(1)
            let m = parseInt(zahl)
            for (let i = 0; i < ShipLength; i++) {
                m--
                let zahl2 = m.toString()
                let IDzahl = zahl1 + zahl2
                document.getElementById(IDzahl).className = "filled"
                completeShip.locations.push(IDzahl)
            }
        } else {
            let ShipLength = länge
            let zahl1 = this.cellid.slice(0, 1)
            let zahl = this.cellid.slice(1)
            let m = parseInt(zahl)
            for (let i = 0; i < ShipLength; i++) {
                m++
                let zahl2 = m.toString()
                let IDzahl = zahl1 + zahl2
                document.getElementById(IDzahl).className = "filled"
                completeShip.locations.push(IDzahl)
            }
        }

    }
    Shipsarray.push(completeShip)
    console.log(Shipsarray)
}

function createShips(id) {
    if (this.shipid == "submarine") {
        positionShips(1, 8, "1", id, "submarine")
    } else if (this.shipid == "destroyer") {
        positionShips(2, 7, "2", id, "destroyer")
    } else if (this.shipid == "cruiser") {
        positionShips(3, 6, "3", id, "cruiser")
    } else if (this.shipid = "carrier") {
        positionShips(4, 5, "4", id, "carrier")
    }
}


function onDragOver(event) {
    event.preventDefault();
}

document.getElementById("resetAllbutton").addEventListener("click", () => {
    Shipsarray = []
    let blacktdlist = document.getElementById("mytable").querySelectorAll("td[class=filled]")
    for (let i = 0; i < blacktdlist.length; i++) {
        blacktdlist[i].className = "empty"
    }
    let invisbleships = document.querySelectorAll(".invisible")
    console.log(invisbleships)
    for (let j = 0; j < invisbleships.length; j++) {
        invisbleships[j].classList.remove("invisible")
    }
    console.log(invisbleships)
})

document.getElementById("fixButton").addEventListener("click", () => {
    console.log('Shipsarray', Shipsarray)

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
                console.log(res)
            })
            .catch(error => console.log(error));
    } else {
        alert("You did not post all Ships")
    }

})