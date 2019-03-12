# Christopher Holmes
# ID: 002928626
# Module 1 - Hangman MVC
# 3/11/19

class HangmanModel
  #Random number
  randNum = rand(1..99)
  
  #Read in word from file and great array
  @@word = IO.readlines("words.txt")[randNum]
  @@word_array = @@word.chars.to_a
  @@remaining_letters = @@word.chars.to_a
  
  #Remove extra white space
  @@word_array.delete_at(@@word_array.length-1)
  @@word_array.delete_at(@@word_array.length-1)
  @@remaining_letters.delete_at(@@remaining_letters.length-1)
  @@remaining_letters.delete_at(@@remaining_letters.length-1)
  
  #Letters
  @@letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"]
  @@guesses = 4
  
  #Getter function for Word_array
  def getWord_array
    @@word_array
  end
  
  #Getter function for guesses
  def get_guesses
    @@guesses
  end
  
  #Getter function for letters
  def get_letters
    @@letters
  end
  
  #Getter function for remaining letters
  def get_remaining_letters
    @@remaining_letters
  end
  
  #Delete function for letters
  def del_letter(guess)
    @@letters.delete(guess)
  end
  
  #Delete function for remaining letters
  def del_remaining_letter(guess)
    @@remaining_letters.delete(guess.downcase)
  end
  
  #Decrement guesses
  def decrement_guesses
    @@guesses -= 1
  end
  
  #Getter function for specific remaining letter
  def get_letter(pos)
    @@remaining_letters[pos]
  end
  
  #Getter function for word
  def get_word
    @@word
  end

  
end