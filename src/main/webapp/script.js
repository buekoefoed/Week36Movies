// console.log('Hello from my new project');

// const persons = ['Henrik','Helge','Hans','Holger'];
const root = document.getElementById('root');
const btn1 = document.getElementById('btn1');
const url = "https://buehub.com/rest-jpa-devops-starter/api/movie/all";

btn1.onclick = function(){
    // console.log('Button was pressed');
    // // root.innerHTML = persons.map(function(el){return '<li>'+el+'</li>'});
    // const p = persons.map(el => '<li>' + el + '</li>');
    // root.innerHTML = '<ul>' + p.join('') + '</ul>';
    fetch(url)
    .then(response => response.json())
    .then(data => root.innerHTML = '<ul>'
        + data.map(el=>'' +
            '<tr>' + '<li>' + el.id + '</li>'  + '</tr>' +
            '<tr>' + '<li>' + el.title + '</li>'  + '</tr>' +
            '<tr>' + '<li>' + el.director + '</li>'  + '</tr>' +
            '<tr>' + '<li>' + el.releaseYear + '</li>'  + '</tr>' +
            '<tr>' + '<li>' + el.actors + '</li>'  + '</tr>'
        ) + '</ul>').join(',');
}

