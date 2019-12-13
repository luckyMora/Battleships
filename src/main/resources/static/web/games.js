async function getData() {
    //await the response of the fetch call
    console.log("here")
    let response = await fetch('http://localhost:8080/api/games');
    console.log(response);
    //proceed once the first promise is resolved.
    let data = await response.json()
    //proceed only when the second promise is resolved
    controller(data)
    console.log("IN GET DATA")
    //return data;
}
//call getData function
// getData()
//     .then(data => console.log(data)); //log the data
// getData();

async function controller(data) {
    console.log("im controller")
    let results = await getData()
    console.log("halo" + data)
    
}
getData()
console.log("here") 

let gamesInfoList = document.getElementsId("GamesList")

function createList(){
    if(data.lenght > 0)
    for(let i = 0 ; i > data.lenght; i++){
        let myGamesList = createElement("ol")

    }
}