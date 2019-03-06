#Random number
randNum = rand(1..99)

#Read in word from file and great array
word = IO.readlines("words.txt")[randNum]
word_array = word.chars.to_a
remaining_letters = word.chars.to_a

#Remove extra white space
word_array.delete_at(word_array.length-1)
word_array.delete_at(word_array.length-1)
remaining_letters.delete_at(remaining_letters.length-1)
remaining_letters.delete_at(remaining_letters.length-1)

#Letters
letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"]
guesses = 4

#Print Main
puts "Welcome to Hangman\n"
puts "enter a guess of 'hint'\n"
puts "You have #{guesses} guesses left"
word_array.each do |x|
  print "_ "
end
print "\n"

#read in first guess and check
guess = gets.chomp.upcase.strip

until letters.include?(guess) == true || guess == "HINT"
  puts "Invalid choice. Choose again."
  puts "Make a choice from available letters\n"
  guess = gets.chomp.upcase.strip
end

#End game when word is guessed or no more guesses
until guesses == 0 || remaining_letters == []
  if word_array.include?(guess.downcase) == true
    puts "Yes, there is a #{guess} in the word\n"
    puts "You have #{guesses} left\n"
    letters.delete(guess)
    remaining_letters.delete(guess.downcase)
    
    if remaining_letters == []
      break
    end #end if remaining_letters == []
    
    word_array.each do |x|
      if letters.include?(x.upcase) == true
        print "_ "
      else
        print "#{x.upcase} "
      end #end if letters.include
    end #end word_array.each
    puts "\n"
    
    #Get next guess
    guess = gets.chomp.upcase.strip
    
    until letters.include?(guess) == true || guess == "HINT"
      puts "Invalid choice. Choose again."
      puts "Make a choice from available letters\n"
      guess = gets.chomp.upcase.strip
    end
    
  elsif guess == "HINT"
    puts "Hint used\n"
    guesses -= 1
    puts "The letter #{remaining_letters[0]} is in the word.\n"
    guess = gets.chomp.upcase.strip
        
    until letters.include?(guess) == true || guess == "HINT"
      puts "Invalid choice. Choose again."
      puts "Make a choice from available letters\n"
      guess = gets.chomp.upcase.strip
    end
    
    
    
  elsif guesses > 1
    guesses -= 1
    
    puts "Sorry, no #{guess} in the word.\n"
    puts "You have #{guesses} left\n"
    letters.delete(guess)
    
    word_array.each do |x|
      if letters.include?(x.upcase) == true
        print "_ "
      else
        print "#{x.upcase} "
      end #end if letters.include
    end #end word_array.each
    puts "\n"
    
    guess = gets.chomp.upcase.strip
    
    until letters.include?(guess) == true || guess == "HINT"
      puts "Invalid choice. Choose again."
      puts "Make a choice from available letters\n"
      guess = gets.chomp.upcase.strip
    end
    
  else
    guesses -= 1
    puts "Sorry, you loose.\n"
    puts "The word was #{word.upcase}"
  end
    
  end #end if word_array.include
  
  if remaining_letters == []
    puts "Contratulation! You guessed #{word.upcase}"
  end
  
#end #end until guesses == 0