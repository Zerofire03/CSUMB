#Room 2
#The Locker Room
#Christopher Holmes

#Array holding possible direction commands
directions = ['fd','bk','lt','rt','pu','it','op','use','yes','y','no','n','exit', 'help']

#Variables for the keys
key1Obtained = True
key2Obtained = False

#Function to printt the help menu
def helpMenu():
    print("This is the help menu.")
    print("Available choices are: ")
    #print(directions, sep=' ', end='.', flush=True)
    for i, d in enumerate(directions):
        if i == len(directions)-1:
            print"%s."%d
        else:
            print"%s, "%d,

def room2():
    #Tell the user about the room
    print("------The Locker Room------")
    print("This room has one door which is behind you. There is also a locker in front of you with a lock on it.")
    
    #Get what the user wants to do
    userActivity = raw_input("Would you like to inspect the locker further or leave the way you came?")
    
    #Check the input from the user
    loopControl = True
    
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
                loopControl = False
            
    #Section to check to see which choice the user made
    #Checks if the user chose to exist the room with bk
    if userActivity == 'bk':
        #insert function call to room1 here to return to main lobby
        print("Call to room1 here")
    #checks to see if the user entered exit to end the game
    elif userActivity == 'exit':
        print("Death is your only escape! You loose.")
        exit()
    #Checks to see if the user entered yes to interact with the locker
    elif userActivity == 'yes' or userActivity == 'y':
        print("You chose to inspect the locker.")
        #Checks to see if the user has obtained the key that is needed to open the locker. If it has, key is used.
        if key1Obtained:
            print("You have a key that looks like it will fit. You try the key and it unlocks the lock.")
            print("Inside the locker you find another key that you take with you.")
            key2Obtained = True
            return room2()
        #If key has not been obtained, cannot open locker and tells user.
        else:
            print("There is a lock on the locker. You need a key to unlock it.")
            return room2()
    elif userActivity == 'no' or userActivity == 'n':
        print("What to do now?")
        return room2()
room2()