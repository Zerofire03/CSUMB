#Christopher Holmes
#Team SocalBetawares
#CST 205 Final
#Memory Game

from random import *
from __builtin__ import True
from time import sleep


gameBoard = []
answer = []
boardSize = 4
pieces = [0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7]

#Empty pictures that are used througout the game
gameBoardPic = makeEmptyPicture(400, 400)
tempGameBoardPic = makeEmptyPicture(400, 400)

#Set the directory that the project is running from to obtain all pictures automatically
showInformation("Please select the folder that contains the script and has all the image files in it.")
folder = pickAFolder()
setMediaFolder(folder)

#Load in all the pictures for the back of the cards
backA = makePicture(getMediaPath("A.png"))
backB = makePicture(getMediaPath("B.png"))
backC = makePicture(getMediaPath("C.png"))
backD = makePicture(getMediaPath("D.png"))
backE = makePicture(getMediaPath("E.png"))
backF = makePicture(getMediaPath("F.png"))
backG = makePicture(getMediaPath("G.png"))
backH = makePicture(getMediaPath("H.png"))
backI = makePicture(getMediaPath("I.png"))
backJ = makePicture(getMediaPath("J.png"))
backK = makePicture(getMediaPath("K.png"))
backL = makePicture(getMediaPath("L.png"))
backM = makePicture(getMediaPath("M.png"))
backN = makePicture(getMediaPath("N.png"))
backO = makePicture(getMediaPath("O.png"))
backP = makePicture(getMediaPath("P.png"))

#Generate dictionary associating letter with image file
backOfCards = {'A': backA, 'B': backB, 'C': backC, 'D': backD, 'E': backE, 'F': backF, 'G': backG, 'H': backH, 'I': backI, 'J': backJ, 'K': backK, 'L': backL, 'M': backM, 'N': backN, 'O': backO, 'P': backP}
letters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P']
coordinates = {'A': (0, 0), 'B': (1,0), 'C': (2, 0), 'D': (3,0), 'E': (0,1), 'F': (1,1), 'G':(2,1), 'H': (3,1), 'I': (0,2), 'J': (1,2), 'K': (2,2), 'L': (3,2), 'M': (0,3), 'N': (1,3), 'O': (2,3), 'P': (3,3)}

#Load in all the images to use as game pieces
image1 = makePicture(getMediaPath("Image1.jpg"))
image2 = makePicture(getMediaPath("Image2.jpg"))
image3 = makePicture(getMediaPath("Image3.jpg"))
image4 = makePicture(getMediaPath("Image4.jpg"))
image5 = makePicture(getMediaPath("Image5.jpg"))
image6 = makePicture(getMediaPath("Image6.jpg"))
image7 = makePicture(getMediaPath("Image7.jpg"))
image8 = makePicture(getMediaPath("Image8.jpg"))

#Generate dictionaries for use
imageDict = {1: image1, 2: image2, 3: image3, 4: image4, 5: image5, 6: image6, 7: image7, 8: image8}

#Function to initialize the game board
def initializeGameBoard(board):
    cardNum = 0
    for i in range(boardSize):
        board.append([])
    for y in board:
        for x in range(boardSize):
            y.append(backOfCards[letters[cardNum]])
            cardNum += 1
            
#Function to initialize the answer board
def initializeAnswerBoard(board):
    for i in range(boardSize):
        board.append([])
    for y in board:
        for x in range(boardSize):
            index = randint(0, len(pieces)-1)
            imageNum = pieces[index]+1
            y.append(imageDict[imageNum])            
            del[pieces[index]]

#pyCopy function to copy one pictures to another            
def pyCopy(source, target, targetX, targetY):
  for x in range(0, getWidth(source)):
    for y in range(0, getHeight(source)):
      c = getColor(getPixel(source,x,y))     #Copies the color for a pixel in the original pic
      p = getPixel(target, x+targetX, y+targetY)  #Points to the pixel of the new pic 
      setColor(p,c)
  
#Function to print out the game board           
def printBoard(board):
    for y in range(0, boardSize):
        for x in range(0, boardSize):
            pyCopy(board[y][x], tempGameBoardPic, 0+(100*x), 0+(100*y))
    repaint(tempGameBoardPic)
            
#Function to print the board with the tiles the user guessed as repaintn
def printBoardGuess(gBoard, aBoard, firstX, firstY, secondX, secondY):
    guessBoard = makeEmptyPicture(400, 400)
    for y in range(len(gBoard)):
        for x in range(len(gBoard)):
            if (y == firstY or y == secondY) and (x == firstX or x == secondX):
                if y == firstY and x == firstX:
                    pyCopy(aBoard[firstY][firstX], guessBoard, 0+(100*firstX), 0+(100*firstY))
                elif y == secondY and x == secondX:
                    pyCopy(aBoard[secondY][secondX], guessBoard, 0+(100*secondX), 0+(100*secondY))
                else:
                    pyCopy(gBoard[y][x], guessBoard, 0+(100*x), 0+(100*y)) 
            else:
                 pyCopy(gBoard[y][x], guessBoard, 0+(100*x), 0+(100*y))
    repaint(guessBoard)
  
    
#Function to compare two pictures to see if they are the same
def compare(pic1,pic2):
  for x in range(0,getWidth(pic1)):
    for y in range(0,getHeight(pic2)):
      if getColor(getPixel(pic1,x,y)) == getColor(getPixel(pic2,x,y)):
        continue
      else:
        return false
  return true
 
#Function to check if the guesses match each other  
def checkGuess(firstX, firstY, secondX, secondY):
    if compare(answer[firstY][firstX], answer[secondY][secondX]):
        gameBoard[firstY][firstX] = answer[firstY][firstX]
        gameBoard[secondY][secondX] = answer[secondY][secondX]
        return True
    else:
        False
        
#Function to write out the answer board to a file for testing           
def writeAnswerBoard(board):
    name = "answer.jpg"
    path = folder + name
    file = r"%s"%path
    for y in range(0, boardSize):
        for x in range(0, boardSize):
            pyCopy(board[y][x], tempGameBoardPic, 0+(100*x), 0+(100*y))
    writePictureTo(tempGameBoardPic, file)
    

def main():
    won = False
    numMatches = 0
    
    #Initialize the game, display the gameboard, and write the answer board to a file in the directory chossen. 
    initializeGameBoard(gameBoard)
    initializeAnswerBoard(answer)
    printBoard(gameBoard)
    writeAnswerBoard(answer)
    
    while not won:
        guess1Already = True
        guess2Already = True
        while guess1Already:
            guess1 = requestString("Enter the letter of the first guess, type q to quit: ").upper()
            if guess1 == 'Q':
              return False
            else:
                if compare(gameBoard[coordinates[guess1][1]][coordinates[guess1][0]], answer[coordinates[guess1][1]][coordinates[guess1][0]]):
                    temp = "You already matched that square, %s. Try again"%guess1
                    showInformation(temp)
                else:
                    break
        while guess2Already:
            guess2 = requestString("Enter the letter of the second guess, type q to quit: ").upper()
            if guess2 == 'Q':
                return False
            else:
                if compare(gameBoard[coordinates[guess2][1]][coordinates[guess2][0]], answer[coordinates[guess2][1]][coordinates[guess2][0]]):
                    temp = "You already matched that square, %s. Try again"%guess1
                    showInformation(temp)
                else:
                    break
        if checkGuess(coordinates[guess1][0], coordinates[guess1][1], coordinates[guess2][0], coordinates[guess2][1]):
            showInformation("Match Found!")
            numMatches += 1
            if numMatches == 8:
                showInformation("Congrats, you solved this puzzle.")
                won = True
                printBoard(gameBoard)
                return True
            printBoard(gameBoard)
        else:
            showInformation("No match, try again.")
            printBoardGuess(gameBoard, answer, coordinates[guess1][0], coordinates[guess1][1], coordinates[guess2][0], coordinates[guess2][1])
            sleep(5)
            printBoard(gameBoard)
       