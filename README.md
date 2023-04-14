# quotesApp
API test


# What used 
The app gets quotes from https://quotable.io/quotes site through api call (test quotes). And stores it to database (as a catch)
<br>
If network is not available then it load data from local catch else from network api
<br>
The whole test app is based on MVVM architecture(which one is google recommended)
*****************************************************************************************
Background Task added to this app : Duty is to request the server peridically every 30 minutes and store the 
record to the local storage ( the quotes page is randomly selected from 1 to 10 ) 
****************************************************************************************
Error Handling Features with Generic Classes for API call. (added)
