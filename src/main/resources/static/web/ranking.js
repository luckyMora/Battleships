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
        data.sort((a, b) => b.Totalscore.totalscore - a.Totalscore.totalscore)
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
        let tabledataLoses = document.createElement("th")
        tabledataLoses.innerHTML = "Loses"
        let tabledatascore = document.createElement("th")
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

        for (let i = 0; i < data.length; i++) {
            let tr = document.createElement("tr")
            let cell1 = document.createElement("td")
            let cell2 = document.createElement("td")
            let cell3 = document.createElement("td")
            let cell4 = document.createElement("td")
            let cell5 = document.createElement("td")


            cell1.innerHTML = data[i].Player
            if (data[i].Scores.length == 0)
                data[i].Scores[0] = {
                    Wins: 0,
                    Ties: 0,
                    Loses: 0,
                }

            cell2.innerHTML = data[i].Scores[0].Wins
            cell3.innerHTML = data[i].Scores[0].Ties
            cell4.innerHTML = data[i].Scores[0].Loses
            cell5.innerHTML = data[i].Totalscore.totalscore



            tr.appendChild(cell1)
            tr.appendChild(cell2)
            tr.appendChild(cell3)
            tr.appendChild(cell4)
            tr.appendChild(cell5)
            tablebody.appendChild(tr)

        };

        table.appendChild(tablebody)


    })
    .catch(error => console.log(error));