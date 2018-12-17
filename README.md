# KoTMDb (Kotlin/The Movie Database)

A very simple application made in Kotlin to improve my skills and learn more about this fantastic language. The application uses the TMDb API and consists of a screen where the most popular movies and series are listed and another where you can see more detailed information of any of them.

The following is a brief description of the technical decisions taken for the development.


## API Service Highlights

Used the v3 of The Movie Database API (documentation guides)

###### HOST 
    www.themoviedb.org

### Endpoints used
#### Popular TV
Get a list of the current popular TV shows on TMDb. This list updates daily.

###### PATH
    GET /tv/popular
    
#### Recommendations
Get the list of TV show recommendations for this item.

###### PATH
    GET /tv/{tv_id}/similar
    

## Architecture

* Application written in Kotlin based on Clean Architecture for layer abstractions (view/domain/data)
* Model-View-Presenter for the presentation layer
* Interactors / UseCases are used by the presenters to start a data retrieval flow
* UseCases use Repository pattern to request the data needed
* Repository in this case doesn't have DataSources because there's no database/cache/other sources to retrieve data than just * Network. Repository in this project directly request data to the API
* Two different data objects, DTO (Data transfer object) to parse API's response, and Business Object as our own data model.
* DI with Dagger2
* Asynchronous handled by RxJava2 (RxKotlin)
* Image loading handled by Picasso 


## Thirtd-Party

* RxKotlin https://github.com/ReactiveX/RxKotlin
* Dagger 2 https://google.github.io/dagger/
* Retrofit 2 https://square.github.io/retrofit/
* OkHttp 3 https://github.com/square/okhttp
* Picasso http://square.github.io/picasso/
* Lottie https://airbnb.design/lottie/

## Work in progress

* TV show search feature.
* Instrumentation Testing
* Cache / database

