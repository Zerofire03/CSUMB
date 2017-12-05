from random import *
from __builtin__ import True

gameBoard = []
answer = []
boardSize = 4
pieces = [0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7]
won = False
numMatches = 0

def initializeGameBoard(board):
    for i in range(boardSize):
        board.append([])
    for y in board:
        for x in range(boardSize):
            y.append('X')
            
def initializeAnswerBoard(board):
    for i in range(boardSize):
        board.append([])
    for y in board:
        for x in range(boardSize):
            index = randint(0, len(pieces)-1)
            y.append(pieces[index])
            del[pieces[index]]
            
def printBoard(board):
    for l in board:
        for e in l:
            print e,
        print
        
def printBoardGuess(gBoard, aBoard, firstX, firstY, secondX, secondY):
    for y in range(len(gBoard)):
        for x in range(len(gBoard)):
            if (y == firstY or y == secondY) and (x == firstX or x == secondX):
                if y == firstY and x == firstX:
                    print aBoard[firstY][firstX],
                elif y == secondY and x == secondX:
                    print aBoard[secondY][secondX],
                else:
                    print gBoard[y][x], 
            else:
                print gBoard[y][x],
        print
        
def checkGuess(firstX, firstY, secondX, secondY):
    if answer[firstY][firstX] == answer[secondY][secondX]:
        gameBoard[firstY][firstX] = answer[firstY][firstX]
        gameBoard[secondY][secondX] = answer[secondY][secondX]
        return True
    else:
        False
        
initializeGameBoard(gameBoard)
initializeAnswerBoard(answer)
printBoard(gameBoard)
printBoard(answer)

while not won:
    guess1Already = True
    guess2Already = True
    while guess1Already:
        guess1X = int(raw_input("Enter the X-Coordinate of the first guess: "))
        guess1Y = int(raw_input("Enter the Y-Coordinate of the first guess: "))
        if gameBoard[guess1Y][guess1X] != 'X':
            print("You already matched that square, (%s,%s). Try again")%(guess1X, guess1Y)
            guess1Already = True
        else:
            break
    while guess2Already:
        guess2X = int(raw_input("Enter the X-Coordinate of the second guess: "))
        guess2Y = int(raw_input("Enter the Y-Coordinate of the second guess: "))
        if gameBoard[guess2Y][guess2X] != 'X':
            print("You already matched that square, (%s,%s). Try again")%(guess2X, guess2Y)
            guess2Already = True
        else:
            break
    if checkGuess(guess1X, guess1Y, guess2X, guess2Y):
        print("Match Found!")
        numMatches += 1
        if numMatches == 8:
            print("Congrats, you won!")
            won = True
        printBoard(gameBoard)
    else:
        print("No match, try again.")
        printBoardGuess(gameBoard, answer, guess1X, guess1Y, guess2X, guess2Y)
