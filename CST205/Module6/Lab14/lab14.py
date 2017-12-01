#Christopher Holmes
#Lab 14 Problem 1

try:
    fin = open('eggs.txt')
except:
    print("Something went wrong opening the file.")
    
numWords = 0
individualWords = []
countWords = []
for line in fin:
    words = line.split()
    numWords += len(words)
    for word in words:
        if word.lower() not in individualWords:
            individualWords.append(word.lower())
            countWords.append(1)
        else:
            index = individualWords.index(word.lower())
            countWords[index] += 1

mostUsed = 0
mostUsedIndex = 0
for i, individualWord in enumerate(individualWords):
    print("%s: %s") %(individualWord, countWords[i])
    if countWords[i] > mostUsed:
        mostUsed = countWords[i]
        mostUsedIndex = i            
    
print("Total number of words: %s")%numWords
print("Number of unique words: %s")%len(individualWords)
print("The most used word is '%s' being used %s times.")%(individualWords[mostUsedIndex], mostUsed) 
