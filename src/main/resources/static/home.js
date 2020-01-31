var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

var urlencoded = new URLSearchParams();

var requestOptions = {
    method: 'GET',
    headers: {
        "Content-Type": "application/x-www-form-urlencoded"
    },
    //body: urlencoded,
    //redirect: 'follow'

};

// var myHeaders = new Headers();
// myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

// var urlencoded = new URLSearchParams();
// urlencoded.append("userName", "jackyB");
// urlencoded.append("password", "11111111");

// var requestOptions = {
//     method: 'POST',
//     headers: myHeaders,
//     body: urlencoded,
//     redirect: 'follow'
// };


async function getgamesData() {

    let response = await fetch(`http://localhost:8080/api/games`, requestOptions);
    console.log(response);
    let data = await response.json()
    console.log(data)
    return data;


}

getgamesData()
    .then(data => {
        //data.sort()
        console.log(data);
        let gamesid = document.getElementById("games")
        let newdiv = document.createElement("div")
        gamesid.appendChild(newdiv)

        for (let i = 0; data.length; i++) {
            if (data[i].Gameplayers.PlayerName1 == data[i].currentLoginUserName || data[i].Gameplayers.PlayerName2 == data[i].currentLoginUserName) {
                console.log(data[i].Gameplayers)
                let gamediv = document.createElement("div")
                gamediv.setAttribute("class", "gamediv")
                let gameNo = document.createElement("p")
                gameNo.innerHTML = "GAME " + data[i].Game_Id
                let players = document.createElement("p")
                players.innerHTML = data[i].Gameplayers.PlayerName1 + " vs " + data[i].Gameplayers.PlayerName2
                if (data[i].Score.ActualScore1 == 0 && data[i].Score.ActualScore2 == 0) {
                    let link = document.createElement("Button")
                    link.setAttribute("class", "stylelink")
                    link.innerHTML = "Rejoin"
                    gamediv.appendChild(link)
                    //link.addEventListener("click", joingame(data[i].Game_Id))
                }
                let creationdate = document.createElement("p")
                creationdate.innerHTML = data[i].created

                newdiv.appendChild(gamediv)
                gamediv.appendChild(gameNo)
                gamediv.appendChild(players)

                gamediv.appendChild(creationdate)
            }
        }
    })
    .catch(error => console.log(error));



//let daten = data

function createGameFunction() {
    getgamesData()
        .then(data => {
            console.log("hey")
            console.log(data)

        })


}
createGameFunction()