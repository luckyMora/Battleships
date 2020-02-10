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



async function getgamesData() {

    let response = await fetch(`http://localhost:8080/api/games`, requestOptions);
    console.log(response);
    let data = await response.json()
    console.log(data)
    return data;


}

getgamesData()
    .then(data => {

        console.log(data);
        let gamesid = document.getElementById("games")
        let newdiv = document.createElement("div")
        gamesid.appendChild(newdiv)

        for (let i = 0; data.length; i++) {
            if (data[i].Gameplayers.PlayerName1 == data[i].currentLoginUserName || data[i].Gameplayers.PlayerName2 == data[i].currentLoginUserName) {
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
                    if (data[i].currentLoginUserName == data[i].Gameplayers.PlayerName1) {
                        let gameplayerid = data[i].Gameplayers.GamePlayer_Id1
                        link.addEventListener("click", rejoinGame(gameplayerid))
                    } else {
                        let gameplayerid = data[i].Gameplayers.GamePlayer_Id2
                        link.addEventListener("click", rejoinGame(gameplayerid))
                    }

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


function rejoinGame(gameplayerid) {
    window.open("http://localhost:8080/games.html?gp=" + gameplayerid);
}



function createGameFunction() {
    fetch("/api/games", {
            method: "POST",
            credentials: "include",
            headers: {
                Accept: "application/json",
                "Content-type": "application/x-www-form-urlencoded"
            }

        })
        .then(response => {
            console.log(response);

            if (response.status === 201) {
                console.log("game created!!");
                return response.json();
            } else {
                console.log("failed to create the game");
            }
        })
        .then(res => {
            console.log(res)
            if (res.GPid) {
                console.log(res.GPid);
                window.open("http://localhost:8080/games.html?gp=" + res.GPid);
            }
        })
        .catch(error => console.log(error));
}