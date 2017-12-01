#Christopher Holmes
#Lab 13 Problem 1

originalParagraph = "Missile and launch components have been moved to the east coast of North Korea in the \"last few days,\" a U.S. official with direct knowledge of the information told CNN Thursday. The apparent deployment comes amid further threatening statements by North Korea and heightened tensions in the region -- a situation that \"does not need to get hotter,\" a U.S. State Department spokeswoman said. The move of the missile and launch equipment could mean that Pyongyang, which unleashed another round of scathing rhetoric accusing the United States of pushing the region to the \"brink of war,\" may be planning a missile launch soon."

wordsList = []
words = originalParagraph.split()
    
userAnswers = []
questions = ["What is your favorite animal? ", "Adverb. ", "Favorite place you want to travel to? ", "Proper noun. "]

for question in questions:    
    userAnswers.append(raw_input(question))

for i, word in enumerate(words):
    if i == 2:
        words[i] = userAnswers[0]
    
for word in words:
    print word,
    
#Loop to get the index number of each of the words in the paragraph
#for i, word in enumerate(words):
#    print "Index: %s Word: %s"%(i,word)