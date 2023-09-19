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

### Third Party Libraries

- `androidx.test.espresso:espresso-core:3.3.0` - UI testing
- `androidx.recyclerview:recyclerview:1.1.0` - RecyclerView functionalities for movie listings

- `com.squareup.retrofit2:retrofit:2.6.0` - Network calls
- `com.squareup.retrofit2:converter-gson:2.6.0` - GSON parsing for server data in JSON format

- `com.github.bumptech.glide:glide:4.10.0` - Image loading library
- `com.github.bumptech.glide:compiler:4.10.0` - Annotation processor for Glide

- `androidx.lifecycle:lifecycle-extensions:2.2.0` - Used in the MVVM pattern for LiveData handling
- `com.squareup.okhttp3:logging-interceptor:3.12.1` - For logging HTTP calls made through Retrofit
- `androidx.fragment:fragment-ktx:1.2.5` - Utilized for the latest ViewModel declaration method
- `org.mockito:mockito-core:2.1.0` - Used for UI testing
