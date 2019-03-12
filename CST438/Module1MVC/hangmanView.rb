# Christopher Holmes
# ID: 002928626
# Module 1 - Hangman MVC
# 3/11/19

require 'hangmanModel.rb'
model = HangmanModel.new()

#Print Main
puts "Welcome to Hangman\n"
puts "enter a guess of 'hint'\n"
puts "You have #{model.get_guesses()} guesses left"
(model.getWord_array()).each do |x|
  print "_ "
end
print "\n"

#read in first guess and check
guess = gets.chomp.upcase.strip

until (model.get_letters()).include?(guess) == true || guess == "HINT"
  puts "Invalid choice. Choose again."
  puts "Make a choice from available letters\n"
  guess = gets.chomp.upcase.strip
end

#End game when word is guessed or no more guesses
until (model.get_guesses()) == 0 || (model.get_remaining_letters()) == []
  if (model.getWord_array()).include?(guess.downcase) == true
    puts "Yes, there is a #{guess} in the word\n"
    puts "You have #{model.get_guesses()} left\n"
    model.del_letter(guess)
    model.del_remaining_letter(guess)
    #remaining_letters.delete(guess.downcase)
    
    if (model.get_remaining_letters()) == []
      break
    end #end if remaining_letters == []
    
    (model.getWord_array()).each do |x|
      if (model.get_letters()).include?(x.upcase) == true
        print "_ "
      else
        print "#{x.upcase} "
      end #end if letters.include
    end #end word_array.each
    puts "\n"
    
    #Get next guess
    guess = gets.chomp.upcase.strip
    
    until (model.get_letters()).include?(guess) == true || guess == "HINT"
      puts "Invalid choice. Choose again."
      puts "Make a choice from available letters\n"
      guess = gets.chomp.upcase.strip
    end
    
  elsif guess == "HINT"
    puts "Hint used\n"
    model.decrement_guesses()
    puts "The letter #{model.get_letter(0)} is in the word.\n"
    guess = gets.chomp.upcase.strip
        
    until (model.get_letters()).include?(guess) == true || guess == "HINT"
      puts "Invalid choice. Choose again."
      puts "Make a choice from available letters\n"
      guess = gets.chomp.upcase.strip
    end
    
    
    
  elsif model.get_guesses() > 1
    model.decrement_guesses()
    
    puts "Sorry, no #{guess} in the word.\n"
    puts "You have #{model.get_guesses()} left\n"
    model.del_letter(guess)
    
    (model.getWord_array()).each do |x|
      if (model.get_letters()).include?(x.upcase) == true
        print "_ "
      else
        print "#{x.upcase} "
      end #end if letters.include
    end #end word_array.each
    puts "\n"
    
    guess = gets.chomp.upcase.strip
    
    until (model.get_letters()).include?(guess) == true || guess == "HINT"
      puts "Invalid choice. Choose again."
      puts "Make a choice from available letters\n"
      guess = gets.chomp.upcase.strip
    end
    
  else
    model.decrement_guesses()
    puts "Sorry, you loose.\n"
    puts "The word was #{(model.get_word()).upcase}"
  end
    
  end #end if word_array.include
  
  if (model.get_remaining_letters()) == []
    puts "Contratulation! You guessed #{(model.get_word()).upcase}"
  end
  
#end #end until guesses == 0