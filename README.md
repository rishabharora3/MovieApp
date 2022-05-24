https://drive.google.com/file/d/1IiMuykq9pZf--iwbWMoRtfqUVcuBYqaM/view

## APPROACH FOR THE FIRST MOVIE LISTING SCREEN

* Added a single recycler view for the whole screen, dividing each view of the screen into different items.
* items are in a Any type mutable list which contains data for every element of the screen
* Used the MVVM architecture for the implementation
* Image caching done using third party library glide
* pagination logic handled using the single recycler view scroll listener
* used a fragment over an activity
* rating view has been handled using a progressbar, setting a progress drawable on runtime based on the conditions given
* Used retrofit for network connections
* Imp : duration key missing in popular movie api

## APPROACH FOR THE SECOND MOVIE DETAIL SCREEN

* Used FlowLayout to show the genres and the glide for loading the poster image
* used a fragment over an activity
* added a unit test case for a function used to convert date format

## THIRD PARTY LIBRARIES USED

* androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0' - UI tests
* implementation 'androidx.recyclerview:recyclerview:1.1.0' - Adding Recycler view functionalities for showing listing of movies

* implementation 'com.squareup.retrofit2:retrofit:2.6.0' - network calls
* implementation 'com.squareup.retrofit2:converter-gson:2.6.0' -  GSON parsing of the data from the server in json format

* implementation 'com.github.bumptech.glide:glide:4.10.0' - image loading library
* annotationProcessor 'com.github.bumptech.glide:compiler:4.10.0'

* implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0' - used in the mvvm  pattern for live data handling
* implementation "com.squareup.okhttp3:logging-interceptor:3.12.1" -  for logging http calls through retrofit
* implementation 'androidx.fragment:fragment-ktx:1.2.5' -  use the latest viewmodel declaration method
* testImplementation 'org.mockito:mockito-core:2.1.0' - UI tests

