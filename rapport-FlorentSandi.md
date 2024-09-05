# Sandi's Logbook

## In this document, you will find my every advancements, struggles and solutions during this project

### First session

    - Discoverd the project/ Read the subject.
    
    - Initialized and configured git on Netbeans, quite a struggle to understand what the tutorial meant at first, but it only became easier and easier as I went on.
    
    - Started the first general UML : what a general UML means was not clear, so I detailed with every method and Attributes and didn't show the USES links

### Second session

    - Finished the first UML and started the second one (Still not aware of what general UML was supposed to mean)

    -Fixed the UMLs by finally understanding what "general" meant : I just deleted every method and finally added the USEs links.

### Third session

    -Started to code the project : created Salle class and implemented it completely.
    
    -Started the method charger in the Etage class : The files were complicated to remember how to work with, but some research were enough.
    Understanding how to charge the files took me some time for I didn't read the subject very well, so I read it again to be sure to understand everything

### Fourth session

    -Finished the charger method and added some prints to check if it was working
    
    -I also started commenting some elements to understand the code easily

    -I attempted the estAdjacente method, with a first draft without really understanding how it was supposed to work : a drawing would have helped

### Fifth session

    -I updated the charger method without really knowing if it was working, the rules were still kinda blurry for me, but after asking some friends as there were no teachers I finally understood, but yet I'm still wondering if two rooms are on the same coordinates but 1 level apart, are they adjacent ? Well I'll have to ask a teacher the next day (Seventh session)

    -After finishing the adjacency method, I started the tests to be sure it was working fine and had to do some tweaks but managed to make all tests pass.

    - Started to work on the labyrinthe code : added the constructor and searched a way to make determine the entrance and exit

### Sixth session

    - I finished the constructor, and for the entrance and exit I determined that searching for them in the labyrinthe class with a while loop would be easier than trying to fetch it from the floor class. But perhaps in the floor class I could create an entrance and exit variables that'll be null unless it has found either one of both. Still need to figure out which is better.

### Seventh session

    - I finally drew the floors and finished the first objective. I also personalized a little bit just for fun.

### Eighth session

    - I drew white squares instead of the images I used, but personalisation is still easily accessible
    - Salle methods were already tested so I directly passed the 8th question
    - I added tests for the Etage class

### Ninth session

    - I finished the 3rd objective by overriding the add method of the Etage class. To do so I simply had to first check if the coordinates were valid, then using the contains method to check if a room was already added to the floor. I had forgotten that the contains method uses equals method so I tried a lot of things and lost too much time before realising it. 