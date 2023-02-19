# NewsApp
News app was built on clean architecture. Users can search , view and save news

# Screenshots

<p align="center">
<img src="/previews/breakingnews.png" width="20%"/>
<img src="/previews/searchnews.png" width="20%"/>
<img src="/previews/savednews.png" width="20%"/>
<img src="/previews/webview.png" width="20%"/>
<img src="/previews/untitled.gif" width="20%"/>
</p>


# Tech Stack & Open Source Libraries
- Minimum SDK level 21
- %100 [Kotlin](https://kotlinlang.org/) based
- [Repository](https://developer.android.com/topic/architecture/data-layer) pattern is a design pattern that isolates the data layer from the rest of the app.
- [Coroutines](https://developer.android.com/kotlin/coroutines) for asynchronous programming on Android. 
- [Room](https://developer.android.com/training/data-storage/room) provides an abstract layer over the SQLite Database to save and perform the operations on persistent data locally.
- [Flow](https://developer.android.com/kotlin/flow) is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value In coroutines
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) class is a business logic or screen level state holder. It exposes state to the UI and encapsulates related business logic
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) is an observable data holder class.
- [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) is a class that holds the information about the lifecycle state of a component (like an activity or a fragment) and allows other objects to observe this state.
- [Navigation Component](https://developer.android.com/guide/navigation) refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within app
- [Retrofit](https://square.github.io/retrofit/) A type-safe HTTP client for Android and Java
- [Dagger Hilt](https://dagger.dev/hilt/) Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
- [ViewBinding](https://developer.android.com/topic/libraries/view-binding) is a feature that allows you to more easily write code that interacts with views.
- [Glide](https://github.com/bumptech/glide/) Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
- [Extension Functions](https://kotlinlang.org/docs/extensions.html) Kotlin provides the ability to extend a class or an interface with new functionality without having to inherit from the class or use design patterns 
- Paging
- ScrollListener
- ItemTouchHelper
- Serializable
- TypeConverters

