# TMDBExplorer
This application uses TMDB (the movie database) API and creates movies aggregator with Now Playing and Popular Movies sections. When we click on a movie content then Movie Detail screen is shown.

# Components used in the application
  * MVVM Architecture (using LiveData, ViewModel and DataBinding components)
  * Dagger2: This is used to initialize API dependency in the project
  * Glide API is used to load and cache the application
  * Retrofit, OkHttp and RxJava are used to make network calls
  * Other dependencies used to create this project: RecyclerView, ConstraintLayout, fragment and Lifecycle extenstions
   

# Code Structure
Here are the important packages and their significance:
 * api
    * apiresponse: This package contains classes related to API response.
    * apiresponseobjects: API responses contains small set of subclass. This package contains these subclasses.
* di: Defines depedency injection for the application. Application intializes network and api references.
* userinterface
    * adapters: Contains RecyclerView adapters used in the application
    * aggregator: It has classes for main screen Fragment, it's ViewModel and Repository classes. This shows Now Playing and Popular Movies section.
    * contentdetail: This package has classes required to show Detail screen.
    * eventlisteners: This package contains event listeners rlated to UI for example RecyclerView item click listener.
* util: Contains Utility classes related to UI and other object manipulation.
    
