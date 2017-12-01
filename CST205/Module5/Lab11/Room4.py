#Room 4
#The Room of Questionnaire
#Patrick Gonzalez

#Array holding possible direction commands
directions = ['fd','bk','lt','rt','pu','it','op','use','yes','y','no','n','exit', 'help']

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
            
#function for the secret room
def secretRoom():
    print("You have entered the secret room")
            
def hangMan():
    print "Upon further inspection of the mirror you see a quote:"
    print "What dosn't kill you only makes you stronger, except for ______, they will definitely kill you."
    print "What do you suppose will definitely kill you?"

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
        print "You have %s guesses"%attempts
        print"Here is what you got so far:"
        print blankWord

        #Ask for a guessed letter from the user 
        guess = raw_input("Guess a letter: ").lower()
        while True:
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
                        print "interesting... it says, What dosn't kill you only makes you stronger, except for sharks, they will definitly kill you."
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

def room4():
    print("------The Room of Questionnaire------")
    print("There are no other doors in the room except the one behind you, but there is a strange mirror..")
    
    #Ask for user activity
    activity = raw_input("Do you want to inspect the mirror further or head back the way you came?").lower()
    
    #Test the input from the user
    while True:
        #Check if the input is a letter
        if activity.isalpha() == False:
            activity = raw_input("Do you want to inspect the mirror further or head back the way you came?").lower()
        #Check if the user asked for help
        elif activity == 'help':
            helpMenu()
            activity = raw_input("Do you want to inspect the mirror further or head back the way you came?").lower()
        #Check if the user entered a proper command
        if activity not in directions:
            print("That wasn't what I was expecting.")
            activity = raw_input("Do you want to inspect the mirror further or head back the way you came?").lower()
        #If they did input a proper command then move on to what they want to do
        if activity in directions:
            break
    
    #Once you have checked if the user input a proper command, move on to the next step
    if activity == 'bk':
        #insert function call to room 1
        print("Insert function call to room 1 here")
    elif activity == 'exit':
        print("Death is your only escape! \m/")
    elif activity == 'yes' or activity =='y':
        mystery = hangMan() #If they pass the hangMan function it will return 'solved' otherwise it return 'incorrect'
        #If they solved the quote correctly they have the choice to enter the secret room or not
        if mystery == 'solved':
            print("You solved the mysterious quote")
            print("The mirror swings open leading to another room")
            decision = raw_input("Do you enter the room? (y/n)").lower()
            while True:
                if decision.isalpha() == False:
                    decision = raw_input("Do you enter the room? (y/n)").lower()
                #if they said yes to entering the room, take them to secretRoom function
                if decision == 'yes' or decision == 'y':
                    return secretRoom()
                #if they said no to entering the room, take them to the begining of room4 function
                if decision == 'no' or decision == 'n':
                    return room4()
                else:
                    print("You didn't enter yes or no")
                    decision = raw_input("Do you enter the room? (y/n)").lower
        elif mystery == 'incorrect':
            print("You didn't solve the mysterious quote, lets start over.")
            return room4()
    elif activity == 'no' or activity == 'n':
        print("What to do...?")
        return room4()
room4()