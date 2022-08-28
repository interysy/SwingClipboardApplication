<h1 align = "center"> SwingClipboardApplication </h1>  
<br>
<p> After completing an introductionary Java course, I have made a simple Swing Application, which stores users' clipboard content for the duration of the session. I have used the Oracle documentation for the ClipboardOwner and related entities (like flavours), alongside the IntelliJ UI designer for the frontend. The application works with both images and text. </p>
  
## Snippets 


# Current Problems 
In V1, the app works in Windows , however due to the nature of Mac OS the Swing GUI is not updated, if the app is not held in the foreground. Currently working on a fix for V1.1, experimenting with flavor listeners and timers.
 
# Credits 
I have initially struggled to synchronise the 2 classes , which I was able to overcome using a shared interface (clipShare), this idea came from the [following article](https://medium.com/swlh/creating-a-clipboard-history-application-in-java-with-swing-16b006f7b322). 
