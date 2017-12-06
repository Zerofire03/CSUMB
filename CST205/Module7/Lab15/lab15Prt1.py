#Christopher Holmes

from random import *

def rollDice():
    return randint(1,6)

loopControl = True
point = 0

while loopControl:

    dice1 = rollDice()
    dice2 = rollDice()
    total = dice1 + dice2
    
    print("You rolled a %s and a %s.")%(dice1, dice2)
    
    if point == 0:
        if total == 7 or total == 11:
            print("Congrats you rolled a %s! You win!")%total
            loopControl = False
        elif total == 2 or total == 3 or total == 12:
            print("You rolled a %s, you lose.")%total
            loopControl = False
        else:
            print("You rolled a %s. Roll again.")%total
            raw_input("Press any key to roll again.")
            point = total
    else:
        if total == point:
            print("Congrats you rolled the point %s again! You win!")%total
            loopControl = False
        elif total == 7:
            print("You rolled a %s, you lose.")%total
            loopControl = False
        else:
            print("You rolled a %s. Roll again.")%total
            point = total
            raw_input("Press any key to roll again.")
            
            