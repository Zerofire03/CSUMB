from sys import exit
#name = requestString("What is your name?")

#while True:
#  name = requestString("What is your name?")
#  if name == 'stop':
#    break
    
def hangMan():
  theWord = "socal"
  incorrect = []

  print("Insert how to play hangman here")
  
  blankWord = ['_'] * len(theWord)
  count=0
  
  while count < 6:
    print "The count is %s:" %(count)
    print "There are %s blank spaces." %(len(blankWord))
    print blankWord
    if count > 0:
      print "Here is what you have guessed so far:"
      print incorrect
    letter = requestString("Guess a letter: ").lower()
    while true:
      if letter.isalpha() == False or letter in incorrect or len(letter) != 1:
        letter = requestString("Letter not valid. Please try again:").lower()
      else:
        break
    
    if letter in theWord:
      for i, elm in enumerate(theWord):
        if letter == elm:
          blankWord[i] = letter
          temp = ''.join(blankWord)
          print temp
          if temp == theWord:
            print("Congratulations you won!")
            count = 6
    else:
      print("Incorrect guess")
      count = count + 1
      incorrect.append(letter)
      if count == 6:
        print "HangMan! You loose!"
      
  