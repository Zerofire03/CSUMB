#Christopher Holmes

import calendar
from datetime import *

def returnDay(int):
    if int == 0:
        return "Monday"
    elif int == 1:
        return "Tuesday"
    elif int == 2:
        return "Wednesday"
    elif int == 3:
        return "Thursday"
    elif int == 4:
        return "Friday"
    elif int == 5:
        return "Saturday"
    elif int == 6:
        return "Sunday"
    
def returnMonth(int):
    if int == 1:
        return "January"
    elif int == 2:
        return "February"    
    elif int == 3:
        return "March"
    elif int == 4:
        return "April"
    elif int == 5:
        return "May"
    elif int == 6:
        return "June"
    elif int == 7:
        return "July"
    elif int == 8:
        return "August"
    elif int == 9:
        return "September"
    elif int == 10:
        return "October"
    elif int == 11:
        return "November"
    elif int == 12:
        return "December"

#year = int(raw_input("Enter the year: "))
#month = int(raw_input("Enter the month: "))
#day = int(raw_input("Enter the day: "))

year = int(1991)
month = int(1)
day = int(25)
print(calendar.month(year, month))

today = date.today()
futureDay = date(2018, 1, 25)
remaining = futureDay - today
print("There are %s days until %s.")%(remaining.days, futureDay)

declerationSigned = date(1776, 8, 2)
dayInt = declerationSigned.today().weekday()

print("The Decleration of Independence was signed on %s %s %s, %s")%(returnDay(dayInt), returnMonth(declerationSigned.month), declerationSigned.day, declerationSigned.year)
    