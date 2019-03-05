#Random number
randNum = rand(1..99)

#Read in word from file
word = IO.readlines("words.txt")[randNum]

#Print Main
puts "Welcome to Hangman\n"
puts "enter a guess of 'hint'\n"
puts "You have 4 guesses left"
i = 0
blanks = ""
loop do
  blanks +  "_ "
  i += 1
  if i == word.length
    break
  end
end
puts blanks
#Close file
#words.close