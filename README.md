# SwingClipboardApplication
A simple Java Swing Application, used to grab and retain clipboard contents for the duration of the session. Works for both images and text. 
 
# CurrentProblems 
In V1, the app works in Windows , however due to the nature of Mac OS , the Swing GUI is not updated, if the app is not held in the foreground. Currently working on a fix for V1.1 .
 
# Credits 
I have initially struggled to synchronise the 2 classes , which I was able to overcome using a shared interface (clipShare), this idea came from the [following article](https://medium.com/swlh/creating-a-clipboard-history-application-in-java-with-swing-16b006f7b322). 
