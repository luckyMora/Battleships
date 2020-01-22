async function getData() {

    let response = await fetch(`http://localhost:8080/api/ranking`);
    console.log(response);
    let data = await response.json()
    return data;
    
}

console.log("test");

getData()
    .then(data => {
        console.log(data);
        let playerName = data[0].Player
        let totalscore = data[0].Totalscore.totalscore
        let wins = data[0].Scores[0].Wins
        let ties = data[0].Scores[0].Ties
        let loses = data[0].Scores[0].Loses

        let rankingorder = []
        data.sort(function(a, b){
            return a.Totalscore.totalscore - b.Totalscore.totalscore
        })
        console.log(data)


        let tableid = document.getElementById("tableid")

        let table = document.createElement("table")
        let tablehead = document.createElement("tHead")
        let tableheadrow = document.createElement("tr")
        let tabledataUser = document.createElement("th")
        tabledataUser.innerHTML = "User"
        let tabledataWins = document.createElement("th")
        tabledataWins.innerHTML = "Wins"
        let tabledataTies = document.createElement("th")
        tabledataTies.innerHTML = "Ties"
        let tabledataLoses =document.createElement("th")
        tabledataLoses.innerHTML = "Loses"
        let tabledatascore =document.createElement("th")
        tabledatascore.innerHTML = "Total Score"


        tableid.appendChild(table)
        table.appendChild(tablehead)
        tablehead.appendChild(tableheadrow)
        tableheadrow.appendChild(tabledataUser)
        tableheadrow.appendChild(tabledataWins)
        tableheadrow.appendChild(tabledataTies)
        tableheadrow.appendChild(tabledataLoses)
        tableheadrow.appendChild(tabledatascore)

        let tablebody = document.createElement("tbody")

        data.forEach(element => {
            let tr = document.createElement("tr")
            let cell1 = document.createElement("td")
            let cell2 = document.createElement("td")
            let cell3 = document.createElement("td")
            let cell4 = document.createElement("td")
            let cell5 = document.createElement("td")

            cell1.innerHTML = element.Player
            cell2.innerHTML = element.Score[0].Wins
            cell3.innerHTML = element.Score[0].Ties
            cell4.innerHTML = element.Score[0].Loses
            cell5.innerHTML = element.Totalscore.totalscore

            table.appendChild(tablebody)
            tablebody.appendChild(tr)
            cell1.appendChild(tr)
            cell2.appendChild(tr)
            cell3.appendChild(tr)
            cell4.appendChild(tr)
            cell5.appendChild(tr)
        });
       


    })
    .catch(error => console.log(error));