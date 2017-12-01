#Lab 11
#Christopher Buckey
#John Seals
#Mike Loeser
#Patrick Gonzalez
#Christopher Holmes

import sys

#Array holding possible direction commands
directions = ['fd','bk','lt','rt','pu','it','op','use','vw','yes','y','no','n','exit', 'help']
directionsList = ['Move Forward', 'Move Back', 'Move Left', 'Move Right', 'Pick Up', 'Interact', 'Open', 'Use', 'View', 'Yes', 'Yes', 'No', 'No', 'Exit', 'Help']

#Variables for the keys
key1Obtained = false
key2Obtained = false



#Help Function
def helpMenu():
    print("This is the help menu.")
    print("Available choices are: ")
    #print(directions, sep=' ', end='.', flush=True)
    for i, d in enumerate(directions):
        if i == len(directions)-1:
            print"%s - %s."%(d,directionsList[i])
        else:
            print"%s - %s, "%(d,directionsList[i]),

#function for entering the game
def enter():
    enterhouse = raw_input("Do you want to play a game?  Great rewards lie inside! ")
    if (enterhouse == "yes"):
        roomone()
    else:
        print "Ok, bye"
        
def leftHouse():
  print("You made it out alive. Congrats!")
        
#Room 1 function
def roomone():
    loopControl = true
    
    while loopControl:
      direction = raw_input("Please choose a room (lt, fd, rt, bk), or (vw) at the object in the center of the room: ")
      if direction == "rt":
         room2()
      elif direction == "fd":
         room4()
      elif direction == "lt":
         roomthree(1)
      elif direction == "vw":
         print "Moving toward the object, you see that there is a box with a sign next to it.  The sign says IT IS DANGEROUS TO GO ALONE, TAKE ONE OF THESE!  The box is empty."
      elif direction == 'help':
         helpMenu()
      elif direction == "bk":
        if key2Obtained:
          loopControl = false
          leftHouse()
          sys.exit()
        else:
          print "I am afraid you can't go back.  You must move forward"
      else:
        print "That is not a direction, insolent fool!"   
        
#Room 2
def room2():
    #Tell the user about the room
    print("------The Locker Room------")
    print("This room has one door which is behind you. There is also a locker in front of you with a lock on it.")
    
    #Get what the user wants to do
    userActivity = raw_input("Would you like to inspect the locker further or leave the way you came?")
    
    #Check the input from the user
    loopControl = true
    
    while loopControl:
        #Check to see if the user wanted the help menu printed
        if userActivity == 'help':
            helpMenu()
            userActivity = raw_input("Would you like to inspect the locker further or leave the way you came?")
        #Checks to see if the user entered a valid choice, if not, asks for another choice
        elif userActivity not in directions:
            print("I wasn't expecting that.")
            userActivity = raw_input("Would you like to inspect the locker further or leave the way you came?")
        #If a valid choice was entered, loopControl is set to false to break out of the loop
        else:
            if userActivity == 'help':
                break
            else:
                loopControl = false
            
    #Section to check to see which choice the user made
    #Checks if the user chose to exist the room with bk
    if userActivity == 'bk':
        #insert function call to room1 here to return to main lobby
        roomone()
    #checks to see if the user entered exit to end the game
    elif userActivity == 'exit':
        print("Death is your only escape!")
        exit()
    #Checks to see if the user entered yes to interact with the locker
    elif userActivity == 'yes' or userActivity == 'y':
        print("You chose to inspect the locker.")
        #Checks to see if the user has obtained the key that is needed to open the locker. If it has, key is used.
        
        if key1Obtained == true:
            print("You have a key that looks like it will fit. You try the key and it unlocks the lock.")
            print("Inside the locker you find another key that you take with you.")
            setKey2()
            return room2()
        #If key has not been obtained, cannot open locker and tells user.
        else:
            print("There is a lock on the locker. You need a key to unlock it.")
            return room2()
    elif userActivity == 'no' or userActivity == 'n':
        print("What to do now?")
        return room2()
        
#Room 3
def roomthree(room):#we will need to know which door the user is arriving through, so we will need the calling function to provide this
  print("You are now in room 3.")
  print("There is a poster on the left wall,  it's caption says CSUMBERIZED. There are also some windows to the right which look out into deep space.  You can clearly distinguish several halloween decorations, obviously 2 or 3 years old. You see two vending machines along the left wall, a sink, a microwave and a referidgerator.  It looks like some sort of intergalactic break room. There is something suspicious about the microwave, you get the feeling that it is making fun of you somehow.  ")
  if room == 1:
    x = 1
    y = 0
  if room == 5:
    x = 1
    y = 4
    
  p = 0
  nextRoom = 0
  userInput = ""
  hasCoin = false
  hasScrewdriver = false
  while userInput != 'exit':
    #userInput = requestString("Enter command: ") 
    userInput = raw_input("Enter command: ")
    if userInput == "fd":
      if y < 4:
        y = y + 1
        print("You move forward one space. Your location is now: ")
        display(x,y)
        #map(x,y)
      elif y == 4:
        print("You can't move any further in that direction.  ")
  
    elif userInput == "bk":
      if y > 0:
        y = y - 1
        print("You move backward one space. Your location is now: ")
        display(x,y)
      elif y == 0:
        print("You can't move any further in that direction. ")  
    
    elif userInput == "lt":
      if x > 0:
        x = x - 1
        print("You move left one space. Your location is now: ")
        display(x,y)
      elif x == 0:
        print("You can't move any further in that direction. ")  
      
    elif userInput == "rt":
      if x < 2:
        x = x + 1
        print("You move right one space. Your location is now: ")
        display(x,y)
      elif x == 2:
        print("You can't move any further in that direction.  ")
    elif userInput == "lb":
      if p < 2:
        print("There is nothing behind you. ")
      elif 2 <= p and p < 4:
        print("Nobody's following you. ")
      elif p >= 4:
        print("You're PARANOID. ")
      p = p + 1  
    elif userInput == "pu":
      if x == 2 and y == 4:
        print("You pick up a handful of coins thinking smugly, these will come in handy later. ")
        hasCoin = true
      if x == 2 and y == 3:
        print("You wonder how someone could leave a useful tool such as this behind, you pick it up and wield it carefully.")
      else:
        print("There is nothing to pick up here. ")
    
    elif userInput == "op":
      if x == 1 and y == 4:
        print("You have opened the door to room 5. ")
        #reply = requestString("Do you wish to walk through the door? ")
        reply = raw_input('Do you wish to walk through the door? ')
        if reply == 'yes' or reply == 'y':
          #nextRoom = 5
          room5()
          #break
      elif x == 1 and y == 0:
        print("You have opened the door to room 1. ")
        #reply = requestString("Do you wish to walk through the door? ")
        reply = raw_input('Do you wish to walk through the door? ')
        if reply == 'yes' or reply == 'y':
          #nextRoom = 1
          roomone()
          #break
      else:
        print("There is no door to open here. ")
        
    elif userInput == "it":
      if x == 0 and y == 2:
        print("There is nothing in the referidgerator, although there are several colorful magnets and a post it note that says 'feed me'. ")
      if x == 2 and y <= 1:
        print("In the bottom corner of the room there is a pinball machine, you can just make out the lettering on the faded backboard, it says 'Dirty Harry'.  You would love to play the game but it looks like it's out of order.")
      if x == 2 and y >= 3:
        print("There is a table in the upper right corner of the room surrounded by chairs, some of which have been tipped over. The table is barren, except for a small pile of change near the top edge of the table and a screwdriver laying on it's side towards the bottom end of the table.")
      if x == 0 and y == 1:
        print("There is a change machine here. It accepts $1 and $5 bills. ")
      if x == 0 and y == 4:
        print("The soda machine looks to be in good working condition.  On the front, in big bold letters it says 'Enjoy Flavo-Bev Cola Today!'. Unfortunately, all the flavors are sold out...with the exception of diet, caffeine free cola.  The dollar bill acceptor is severly damaged, it looks as if someone has mangled it with some sort of hand held tool.")  
      if x == 0 and y == 3:
        print("The snacks look overpriced, and not very healthy, better just get something to drink instead.")
      if ((x == 0 and y == 0) or x == 1) or (x == 2 and y == 2):
        print("There is nothing to interact with. ")
      if x == 2 and y == 2:
        print("There is a comfortable looking couch here. ")  
        
    elif userInput == "use":
      if x == 2 and y <= 1:
        print("You would love to play the pinball machine, but it appears to be out of order. ")
      if x == 2 and y == 2:
        print("You lay on the couch, somehow you feel much better about everything. ")
      if x == 0 and y == 1:
        print("You put a dollar into the change machine but nothing changes. ")
      if x == 0 and y == 4:
        if hasCoin == true and hasScrewdriver == true:
          print("You cram the screwdriver into the bill acceptor, it looks like this has been done several times before.You then put a coin into the machine and twist the screwdriver...there is a sound, like some kind of horrible malfunction... then you look below you...oh dear, instead of diet crappy flavor soda, a small, solid gold otter pops out from the machine, Eureka! YOU'RE RICH! Wait, it's only gold foil...as it turns out, this is a chocolate otter wrapped in gold foil.  Mmmm tasty chocolate.")
        if hasCoin == true and hasScrewdriver == false:
          print("You put several coins into the machine. There is a muffled bang...you look below you to find a nice, cold can of soda.  It tastes terrible.  ")
        if hasCoin == false:
          print("You don't have any change.")
      if (x == 0 and y == 0) or (x == 0 and (y == 2 or y == 3)) or x == 1 or (x == 2 and (y == 3 or y == 4)):   
        print("There is nothing to use here. ")
          
    elif userInput == "hint":
      print("If you're thirsty, have a drink.  There is a pile of coins on the table.  Rumor has it that strange treasures are known to come out of the soda machine, with a little help...")          
      
    elif userInput == "help":
      print("Here is the list of commands: To pick up type 'pu', To interact/look type 'it', To open door type 'op', To use something type 'use', To exit type 'exit'. ")
    else:
      print("Invalid command")
  #exit to another room
  #you will only be able to exit to two roomms from this room, room 1 and 5, we may call those rooms from here
  #print nextRoom
  #return nextRoom
  return 0
  
def display(x,y):
  print("x =", x, "y = ", y)


#Room 4
def room4():

  d = direction()#Holds the array of all possible commands

  #Intro to the room

  printNow("------The Room of Questionnaire------")

  printNow ("There are no other doors in the room except the one behind you, but there is a strange mirror..")

  # Ask for user activity

  activity = ""

  activity = raw_input("Do you want to inspect the mirror further or head back the way you came? ").lower()

  #Test the input from the user

  while true:

    #Check if the input is a letter

    if activity.isalpha() == False:

      activity = raw_input("Do you want to inspect the mirror further or head back the way you came? ").lower()

    #Check if the guess asked for help

    if activity == 'help':

      helpMenu()

      activity = raw_input("Do you want to inspect the mirror further or head back the way you came? ").lower()

    #Check if the user entered a proper command

    if activity not in d:

        print("That wasn't what I was expecting.")

        activity = raw_input("Do you want to inspect the mirror further or head back the way you came? ").lower()

    #If they did input a proper command then move on to what they want to do 

    if activity in d:

      #print("Flag: activity in d")

      break

      

  # Once you have checked if the user input a proper command move on to the next step 

  if activity == 'bk':

    roomone() #input the function definition call to room 1

  elif activity == 'exit':

        print("Death is your only escape! \m/")

  elif activity == 'yes' or activity =='y':

      #print("Flag: They said yes inspect the mirror")

      #The player gets the chance to solve the mysterious quote

      mystery = hangMan() #If they pass the hangMan function it will return 'solved' otherwise it return 'incorrect'

      #If they solved the quote correctly they have the choice to enter the secret room or not

      if mystery == 'solved':  

        printNow("You you solved the mysterious quote")

        printNow("The mirror swings open leading to another room")

        decision = raw_input("Do you enter the room? (y/n) ").lower()

        while true:

          #Check if the input is a letter

          if decision.isalpha() == False:

            decision = raw_input("Do you enter the room? (y/n) ").lower

          #If they said yes to entering the room take them to secretRoom function

          if decision == 'yes' or decision =='y':

            #print "Flag: Yes enter the secret room"

            return secretRoom()

          #If they said no to entering the room take them to the begining of room4 function

          if decision == 'no' or decision =='n':

           # print "Flag: No do not enter the secret room"

            return room4()

          else:

            printNow("You didnt enter yes or no")

            decision = raw_input("Do you enter the room? (y/n) ").lower

      elif mystery == 'incorrect':

        printNow("You didnt solve the mysterious quote, lets start over.")

        return room4()    

  elif activity == 'no' or activity =='n':

    printNow("What to do...?")

    return room4()
    
#an array to hold all the direction to check if the user entered a proper commmand  

def direction():

  d = ['fd','bk','lt','rt','pu','it','op','use','yes','y','no','n','exit']

  return d
  
#function for the secret room

def secretRoom():

  print "You have entered the secret room and were teleported back to the start. Congrats!"
  sys.exit()
  
####################################

# This ask the user to solve the blank in the mirror

#The rules are the same as hangman with 6 tries

#return 'solved' if they answer correctly

#return 'inccorect' if they answere wrong

  

def hangMan():

  print "Upon further inspection of the mirror you see a quote:"

  print "What dosn't kill you only makes you stronger, except for ______, they will definitly kill you."

  print "What do you suppose will definitly kill you?"

  

  

  #This array holds all the guessed letters

  incorrect = []

  #This variable counts the number of attempts

  attempts = 6

  

  #The secret word to be guessed

  theWord = "sharks"

 

  #An array of blank Spaces

  # That will show the correct guesses

  #blankWord = [u'\u005F']*len(theWord)

  blankWord = ['_']*len(theWord)

  

  #This loop asks the user for the guessed letter

  # keeps looping until the user has reached the alloted number of guess attempts

  while attempts > 0:

    print "You have %s guesses" %attempts

    print"Here is what you got so far:"

    print blankWord

    

    #Ask for a guessed letter from the user 

    guess = raw_input("Guess a letter: ").lower()

    while true:

       #Check if the guess is a letter

       if guess.isalpha() == False:

           guess = raw_input("Letters only.  Please try again: ").lower()

       #Check if the guess has been guessed already

       if guess in incorrect:

         guess = raw_input("You guessed that already.  Please try again: ").lower()

       #Check if the user enters one letter at a time

       if len(guess) !=1:

         guess = raw_input("Only one at a time.  Please try again: ").lower()

       else:

         break

    

    #Is the guess letter in the seceret word

    if guess in theWord:

      #Then find the index of the correct guess in the seceret word

      for i, correct in enumerate(theWord):

        #When you find the index of where the guess letter belongs in the secerte word

        if guess == correct:

          #Then assign the guessed letter to the matching index of the secerte word to 

          #  that of the blankWord array to show to the user what they have guessed correctly 

          blankWord[i] = guess 

          #Turns the array of charecters into a string to check agaisnt the secret word

          temp = ''.join(blankWord)

          #Checks to see if the user has guessed all the letters so far

          if temp == theWord:

            print "interesting... it says, What dosn't kill you only makes you stronger, except for \"sharks\", they will definitly kill you."

            return 'solved'

    #When the user guess the letter wrong:

    #  attach the incorrect guessed letter to the incorrect array

    #  decrease attempt to let the user know how many attempts they have left 

    else:

        incorrect.append(guess)

        attempts -=1

        print "You guessed: "

        print incorrect      

  return 'incorrect'
  
#Room 5
def room5():
  viewedPainting = false
  keyVisible = false
  # user enters the room
  printNow("--- You have entered The Shrine ---")
  printNow("This is a dark room with no furniture.")
  printNow("On one wall is a curious looking painting.")
  
  userAction = raw_input("What do you want to do? - type 'help' for a list of actions. ")
  
  while userAction.lower() != "exit":
    if userAction.lower() == 'help':
      helpMenu()
    elif userAction.lower() == 'vw':
      showView()
      viewedPainting = true
    elif userAction.lower() == 'it':
      if viewedPainting == false:
        printNow("That is an invalid command")
      else:
        showShift()
        keyVisible = true
    elif userAction.lower() == 'pu':
      if keyVisible == false:
        printNow("That is an invalid command")
      else:
        showGrab()
        setKey1()
    elif userAction.lower() == 'bk':
      showExit()
    else:
      printNow("That is an invalid command")
      
    userAction = raw_input("What do you want to do? - type 'help' for a list of actions. ")


#Show View
def showView():
  printNow("you are in a darkened empty room")
  printNow("there are no windows")
  printNow("you see a large painting, at least 3 feet wide")
  printNow("it depicts a scene of a great white shark")
  printNow("cresting the water in an attack")
  printNow("the painting is at an odd angle")
  printNow("type 'it' to straighten the painting")

#Show Shift
def showShift():
  printNow("you straighten the painting")
  printNow("a key falls to the floor")
  printNow("type 'pu' to pick up the key")

#Show Grab
def showGrab():
  printNow("you reach down and pick up the key")
  printNow("it looks like a skeleton key to the house")
  printNow("I wonder what it opens...")

#Show Exit
def showExit():
  printNow("you have exited the room")
  roomthree(5)
  # the exit will call room 3 function
  
#Function to set key1
def setKey1():
  global key1Obtained
  key1Obtained = true
  
#Function to set key2
def setKey2():
  global key2Obtained
  key2Obtained = true
  
#Begin Main part of program where everything is called
#Print the intro to the scenario of the game
printNow("It's a lovely day in Monterey.  You're walking down the hall at your")
printNow("favorite college. You're looking forward to a delicious lunch in your")
printNow("favorite cafeteria, but first you have to attend your last class of")
printNow("the day, the CST 205 lecture. Seems like another normal day at CSUMB.")
printNow("Nothing sinister could happen, could it?...could it?...OR COULD IT?")
printNow("You reach the classroom door, funny though...the window on the door is") 
printNow("all black and there is a handwritten note on the door that says 'Do you")
printNow("want to play a game?' You figure the lights are just off and the room is")
printNow("dark.  Suddenly, and without warning the door opens by itself and you walk through...")
printNow("BANG! The door slams shut and you're stuck!  What strange manner of room is this?")
printNow("Wherever you are, one thing is certain, you can't go back the way you came in...")

#Call the enter function
enter()