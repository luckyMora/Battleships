async function getgamesData() {

    let response = await fetch(`http://localhost:8080/api/games`);
    console.log(response);
    let data = await response.json()
    return data;

}
getgamesData()
    .then(data => {
        console.log(data);
        let gamesid = document.getElementById("games")
        let newdiv = document.createElement("div")
        gamesid.appendChild(newdiv)

        for (let i = 0; data.length; i++) {
            let gamediv = document.createElement("div")
            gamediv.setAttribute("class", "gamediv")
            let gameNo = document.createElement("p")
            gameNo.innerHTML = "GAME " + data[i].Game_Id
            let players = document.createElement("p")
            players.innerHTML = data[i].Gameplayers[0].Player[0].UserName + " vs " + data[i].Gameplayers[1].Player[0].UserName
            let link = document.createElement("a")
            link.setAttribute("class", "stylelink")
            link.innerHTML = "Join"
            //            link.addEventListener("click", joingame(data[i].Game_Id))
            let creationdate = document.createElement("p")
            creationdate.innerHTML = data[i].created

            newdiv.appendChild(gamediv)
            gamediv.appendChild(gameNo)
            gamediv.appendChild(players)
            gamediv.appendChild(link)
        }
    })
    .catch(error => console.log(error));

function createGameFunction() {

}