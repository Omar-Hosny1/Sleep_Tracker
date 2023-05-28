## Class 1

Android Files Structure :
res -> has all resourse files that we need such as strings, layouts, colors, and much more
manifest -> has the all android application configration and also premissions and access mobile hardware
gradle -> generate the apk
*the activity and layout connected via process known as layout inflaction
*during the inflation process the views in the XML file become view objects in memory
*whenever u create a view and add the ID Android studio will create a constat with that ID's name in the R(res) class
*the activity is responisble for the interactive parts of the app
App Compat Activity -> it's a class that gives us access to modern android features
*setContentView -> it's specify which layout associated with the activity and it's also inflate it
*Linear Layout -> known as a view group
*the orientation of the linear layout by default is horizontall
*sp -> scale independent pixels (used for text)
*tools namespase -> use when you want to define dummy content that it only use when you previewing the app in the preview pane
\*tools attributes-> removed when you actually compiled the app

## Class 2

dp -> independent pixel (it's a unit for making responsive apps)
create a style by clicking on the view and then select refactor then extract style and this will create a style looking that makes you do not repeat your code
scrollView -> can only has one child
layout_gravity -> attributes according to the layout
gravity -> attributes according to the actual view
*Binding Object is created by the compiler when you wrap your xml file with <layout> and the name is derived from the name of the xml file + Binding
*DataBindingUtil.setContentView() = setContentView() -> the two ways used to inflates and sets the layout for the current activity but the difference that the first one create a binding object that can make you access to the views quickly
-> fragment_title => FragmentTitleBinding
\*safeArgs -> can help with passing data through fragments
*types of chains in Constraints layout = Spread chain, Packed chain, Spread inside chain, Packed chain with bias and Weighted chain.
## Class 3

*Each Activity can hold more than fragment
*the activity operates as a frame that contains the UI fragment and can provide UI elements that surround the fragment
*nav host fragment => swaps the fragments in and out as nessessary (controll the stack and the showen fragment)
*inclusive flag will also pop the target screen
*PopTo Not-Inclusive -> Pops off everything on the back stack until it finds the referenced fragment transaction.
*PopTo Inclusive -> Pops off everything on the back stack, including the referenced fragment transaction.
*Intent used to navigate between activities and it has two types implicit and explicit => explicit used to navigate and share data between activites within the app
and implicit used to navigate and share data between activites with other apps
*menuId must be equal to fragment id in the nav grave to do the navigation correctly

## Class 4

The Activity Lifecycle -> initialized, created, started, resumed, destroyed
The Activity Lifecycle callbacks -> onCreate, onStart, onResume, onDestroy, onPause, onStop, onRestart

onCreate -> called once the activity is created but not yet visible and used to do any one type initializations
Application Class -> The Application class is a base class within an Android app that contains global application state for the entire app. It is always running as long as your app is running, even if there are no activities or fragments currently in use.
The Application class is used to maintain global application state, such as initializing libraries, setting up a database connection, or initializing a network connection. It is also used to store data that needs to be accessed by multiple activities or fragments within the app.

*onDestroy -> called when an activity is being destroyed and removed from memory by the garbage collector.
*onStart -> called when an activity becomes visible
*onStop -> called when an activity goes off from the screen
*onResume, onPause -> the focus area
*onResume -> called when you currently interacting with the activity (has focus)
*onPause -> called when the activity loss it's focus (has focus) -> incoming call
*onRestart -> onRestart = onCreate -> but not the first time as onCreate
watch for any change = 'observe'
*onSaveInstanseState => to save some data when the OS destroy your app (not u that will destroy it's the OS)
*onSaveInstanseState -> called when onStopCalled
*Bundle -> is a collection of key value pairs (HashMap)
\*the data that u save in the bundle of onSaveInstanseState callback

## Class 5

- Every view model will destroy when the fragment fully destroyted
- Every view model will execute this function onCleared() before it destroy
- We Will Work for a 3 diffrent classes the UI controller and viewmodel and live data
- UI controller used to display the data and capture the user inputs or events
- UI controller is the fragment or the activity
- view model -> used to hold the data needed for the UI and prepare it for display
- ViewModelProviders.of -> create an instanse of the assosiated viewModel for the first time and when it's called again it call a pre-exist instanse and does not create it again
- Live Data -> makes you interact with the UI controller from the VM
- Elvis Operator -> null ?: 1 = 1, 1 ?: null = 1
  -ViewModelProviders can only instantiate ViewModels with no arg constructor
  So if I have a ViewModel with multiple arguments, then I need to use a Factory
  that I can pass to ViewModelProviders to use when an instance of MyViewModel is required.

## Class 6

- Room is a database library
- DAO (data accsess object) ->  to map kotlin functions to sql queires
- To make the data class to a sql tabel we anotate the data class by Entity 
- we return the data as a liveData will give us the data updated when any change happens (sync)  
- the companion object allows clients to access the methods inside the class without instantiating the class (static in java)
- use <merge></merge> in the activity xml instade of redundant layout
- all the database operation should run away from the UI thread and we do that using coroutines
- we specifiy a job for coroutines, this job is to cancel all coroutines when view model is destroyed
- we specifiy a scope for coroutines, this scope will detrmine which thread the coroutine will run on
- Transformations.map function takes a LiveData object as input and returns a new LiveData object as output. The new LiveData object represents a transformation of the original LiveData object, based on a transformation function that you provide.

- The transformation function is called every time the original LiveData object changes, and it takes the current value of the original LiveData object as input. The transformation function then returns a new value, which is emitted by the new LiveData object.

## Class 6 - Coroutines
- Coroutines are susbendable this means we can execute some functions and pause the execution then we resume it
- we can execute a susbend function from inside a susbend function or coroutine builder
- Global Scope => runs even if the activity shuts down and it will stops when the app closed
- Only main thread can change the UI elements
- async works in parallel
- job is the controller of the Coroutine Builder
- job.join() = await (async fun) => it makes the job finish and then executes the forword lines (or jobs),  = joinAll()    
- Coroutines also can switch the context and change the thread that it actually works on
- Coroutines = lightweight thread with some useful extra functionality
- Coroutines Context = describe which thread our coroutine will be started in
- Dispatchers.main => main thread, useful for changing the UI
- Dispatchers.IO => useful for Networking, writing to databases
- Dispatchers.Defauld => useful for complex or long-running operations
- runBlocking => Block the main thread
- Blocking vs Suspending 
Suspending = pause and do another work then resume and so on
Blocking = work and don't do any thing in the break time

## class 7
- Adapters => convert one interface to work with another
- ViewHolders => holds the view and store information for recycler view
- layoutManager => tells the recycler view how to display the data ie: in a cloumn or grid
- RecyclerView does't interact directly with the view but instade the view holders
- onBindViewHolder function used to tell how actually the view we want to draw
- recycler reuse the view holders in the onBindViewHolder function
- View Holders => tells the recycler view where and how an item should be drawn in the list      