# MovieApp - Android

This is a basic Android application that provides information about currently playing movies and the most popular ones.

<p align="center">
  <img src="https://user-images.githubusercontent.com/14349274/170136134-a8dc278b-1d88-4be8-a6fc-ba1f5d6421dc.png" alt="Image 1" width="300">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://user-images.githubusercontent.com/14349274/170135787-d9eb3007-5e7a-456f-af5b-9daba91e2a69.png" alt="Image 2" width="300">
</p>

## Application Recording

Watch the application in action [here](https://user-images.githubusercontent.com/14349274/170133355-d85c9576-55f3-44f1-a476-68070b23212a.mp4).

## First Movie Listing Screen

- Implemented a single RecyclerView to display the entire screen, with each view divided into different items.
- Utilized an `Any` type mutable list to hold data for each element on the screen.
- Implemented the MVVM architecture for clean separation of concerns.
- Employed the Glide third-party library for image caching.
- Managed pagination logic using a RecyclerView scroll listener.
- Used a Fragment over an Activity for efficient view management.
- Handled rating view with a ProgressBar, dynamically setting a progress drawable based on conditions.
- Utilized Retrofit for network connections.
- Note: The 'duration' key is missing in the popular movie API.

## Second Movie Detail Screen

- Employed FlowLayout to display genres and used Glide for loading the poster image.
- Again, used a Fragment for better modularity and reusability.
- Added a unit test case for a function responsible for converting date formats.

## Tech Stack

### UI Testing
- [Espresso](https://developer.android.com/training/testing/espresso) - Android testing framework for writing UI tests.

### RecyclerView
- [RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview) (version 1.1.0) - Android library for displaying large data sets with views.

### Network Calls
- [Retrofit](https://square.github.io/retrofit/) (version 2.6.0) - Type-safe HTTP client for Android and Java.

### JSON Parsing
- [Gson](https://github.com/google/gson) (version 2.6.0) - Java library for JSON serialization and deserialization.

### Image Loading
- [Glide](https://github.com/bumptech/glide) (version 4.10.0) - Fast and efficient open-source media management and image loading framework for Android.

### LiveData Handling (MVVM)
- [Android Lifecycle Extensions](https://developer.android.com/jetpack/androidx/releases/lifecycle) (version 2.2.0) - Components for handling lifecycle-related tasks.

### Logging HTTP Calls
- [OkHttp Logging Interceptor](https://square.github.io/okhttp/interceptors/) (version 3.12.1) - Interceptor that logs HTTP requests and responses.

### ViewModel Declaration
- [Fragment KTX](https://developer.android.com/kotlin/ktx#fragment) (version 1.2.5) - Kotlin extensions for the Android Jetpack Fragment library.

### Unit Testing
- [Mockito](https://site.mockito.org/) (version 2.1.0) - Mocking framework for unit tests.


