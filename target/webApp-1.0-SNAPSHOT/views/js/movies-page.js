$(document).ready(() => {
    if($('#searchText'==null)){
        //getTopMovies();
    }
    let entityMovie = sessionStorage.getItem("movieId");
    if(entityMovie!= null){
        getMoviestemp(entityMovie);
        sessionStorage.removeItem("movieId");
    }
    $('#searchForm').on('submit', (e) => {
        let searchText = $('#searchText').val();
        getMoviestemp(searchText);
        e.preventDefault();
});
});

//for movie detail button.
function movieSelected(id){
    sessionStorage.setItem('movieId',id);
}

function getMoviestemp(searchText){
    axios.get('http://www.omdbapi.com/?apikey=f9abc5cc&s='+searchText)
        .then((response) => {
            console.log(response);
            let movies = response.data.Search;
            let output = '';
            $.each(movies, (index, movie) => {
                output+=` 
                <div class="col-md-3">
                    <div class="well text-center">
                        <img src="${movie.Poster}"> 
                        <h5>${movie.Title}</h5>
                        <h6>${movie.Year}</h6>
                        <a onclick="movieSelected('${movie.imdbID}')" class="btn btn-primary" href="http://localhost:8081/webApp_war/router/user/moviedetails">Movie Details</a>
                    </div>
                </div> 
                `;
            });
            $('#movies').html(output);
        })
        .catch((err)=>{
            console.log(err);
        })
}

function getmovietemp(){
    let movieId = sessionStorage.getItem('movieId');
    axios.get('http://www.omdbapi.com/?apikey=f9abc5cc&i='+movieId)
        .then((response) => {
            console.log(response);
            let movie = response.data;
            let output = `
            <div class="row">
                <div class="col-md-4">
                    <img src="${movie.Poster}" class="img-thumbnail">
                </div>
                <div class="col-md-8"> 
                 <h2>${movie.Title}</h2>
                 <ul class="list-group"> 
                    <li class="list-group"><strong>Genre:</strong> ${movie.Genre}</li>
                    <li class="list-group"><strong>Released:</strong> ${movie.Released}</li>
                    <li class="list-group"><strong>IMDB Rating:</strong> ${movie.imdbRating}</li>
                    <li class="list-group"><strong>Director:</strong> ${movie.Director}</li>
                    <li class="list-group"><strong>Writer:</strong> ${movie.Writer}</li>
                    <li class="list-group"><strong>Actors:</strong> ${movie.Actors}</li>
                    <h4>Plot</h4>
                    ${movie.Plot}
                    <hr>
                 </ul>
                </div>
            </div>
            <div class="row"> 
            <div class="well"> 
            <a href="http://imdb.com/title/${movie.imdbID}" target="_blank" class="btn btn-primary">View IMDB</a>
            <a href="http://localhost:8081/webApp_war/router/user/movies" class="btn">Back to search</a>
            </div>
            </div>
            `;
            $('#movie').html(output);
            sessionStorage.setItem("title",)
        })
        .catch((err)=>{
            console.log(err);
        })
}

//displays searched text, from imdb.
function getMovies(searchText){
    axios.get('https://imdb-api.com/en/API/SearchMovie/k_rstrkz43/'+searchText)
        .then((response) => {
            console.log(response);
            let movies = response.data.results;
            let output = '';
            $.each(movies, (index, movie) => {
                output+=` 
                <div class="col-md-3">
                    <div class="well text-center">
                        <img src="${movie.image}"> 
                        <h5>${movie.title}</h5>
                        <h6>${movie.description}</h6>
                        <a onclick="movieSelected('${movie.id}')" class="btn btn-primary" href="http://localhost:8081/webApp_war/router/user/moviedetails">Movie Details</a>
                    </div>
                </div> 
                `;
            });
            $('#movies').html(output);
        })
        .catch((err)=>{
            console.log(err);
        })
}
//for movie details, from imdb.
function getmovie(){
    let movieId = sessionStorage.getItem('movieId');
    axios.get('https://imdb-api.com/en/API/Title/k_rstrkz43/'+movieId)
        .then((response) => {
            console.log(response);
            let movie = response.data;
            let output = `
            <div class="row">
                <div class="col-md-4">
                    <img src="${movie.image}" class="img-thumbnail">
                </div>
                <div class="col-md-8"> 
                 <h2>${movie.fullTitle}</h2>
                 <ul class="list-group"> 
                    <li class="list-group"><strong>Genre:</strong> ${movie.genres}</li>
                    <li class="list-group"><strong>Released:</strong> ${movie.releaseDate}</li>
                    <li class="list-group"><strong>IMDB Rating:</strong> ${movie.imDbRating}</li>
                    <li class="list-group"><strong>Director:</strong> ${movie.directors}</li>
                    <li class="list-group"><strong>Writer:</strong> ${movie.writers}</li>
                    <li class="list-group"><strong>Actors:</strong> ${movie.stars}</li>
                    <h4>Plot</h4>
                    ${movie.plot}
                    <hr>
                 </ul>
                </div>
            </div>
            <div class="row"> 
            <div class="well"> 
            <a href="http://imdb.com/title/${movie.id}" target="_blank" class="btn btn-primary">View IMDB</a>
            <a href="http://localhost:8081/webApp_war/router/user/movies" class="btn">Back to search</a>
            </div>
            </div>
            `;
            $('#movie').html(output);
        })
        .catch((err)=>{
            console.log(err);
        })
}
//shows most popular movies, until user searches something, from imdb.
function getTopMovies(){
    axios.get('https://imdb-api.com/en/API/MostPopularMovies/k_rstrkz43')
        .then((response) => {
            console.log(response);
            let movies = response.data.items;
            let output = '';
            $.each(movies, (index, movie) => {
                output+=` 
                <div class="col-md-3">
                    <div class="well text-center">
                        <img src="${movie.image}"> 
                        <h5>${movie.fullTitle}</h5>
                        <h6>${movie.imDbRating}</h6>
                        <a onclick="movieSelected('${movie.id}')" class="btn btn-primary" href="http://localhost:8081/webApp_war/router/user/moviedetails">Movie Details</a>
                    </div>
                </div> 
                `;
            });
            $('#movies').html(output);
        })
        .catch((err)=>{
            console.log(err);
        })
}