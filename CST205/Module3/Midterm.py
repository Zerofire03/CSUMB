#Chris Holmes
#Midterm 1 CST205
#ComicBook Filter

#Chromakey function that we wrote previously to add the background to a pic
def chromakey(pic, picBG):
  for x in range(0, getWidth(pic)):
    for y in range(0, getHeight(pic)):
      px = getPixel(pic, x, y)
      pxBG = getPixel(picBG, x, y)
      color = getColor(px)
      colorBG = getColor(pxBG)
      if distance(color, white) < 200.0:
        setColor(px, colorBG)
  return pic

#The artify function that I used to make the picture have that comicbook   
def artify(pic):
  # Current color range	New value for color
  #color < 64	31
  #63 < color < 128	95
  #127 < color < 192	159
  #191 < color < 256	223
  #pic =  makePicture(pickAFile())
  
  for px in getPixels(pic):
    R = getRed(px) 
    B = getBlue(px)
    G = getGreen(px)
    
    # red
    if R < 64:
      setRed(px, 31)
    elif R >= 64 and R < 128:
      setRed(px, 95)
    elif R >= 128 and R < 192:
      setRed(px, 159)
    elif R >= 192 and R < 256:
      setRed(px, 223)

    # blue
    if B < 64:
      setBlue(px, 31)
    elif B >= 64 and B < 128:
      setBlue(px, 95)
    elif B >= 128 and B < 192:
      setBlue(px, 159)
    elif B >= 192 and B < 256:
      setBlue(px, 223)
    
    # green
    if G < 64:
      setGreen(px, 31)
    elif G >= 64 and G < 128:
      setGreen(px, 95)
    elif G >= 128 and G < 192:
      setGreen(px, 159)
    elif G >= 192 and G < 256:
      setGreen(px, 223)

#Copy function that starts the picture at a given coordinate
def copyStartBackground(picture, background, xStart, yStart):
  width = getWidth(picture)
  height = getHeight(picture)
  for x in range (xStart, width):
    for y in range (yStart, height):
      pix = getPixel(picture, x, y)
      color = getColor(pix)
      setColor(getPixel(background, x, y), color)
 

#BEtter black and white function we previously wrote
def betterBnW(pic):
  pixels = getPixels(pic)
  for p in pixels:
    r = getRed(p)
    g = getGreen(p)
    b = getBlue(p)
    avg = r*0.299 + g*0.587 + b*0.114
    setRed(p, avg)
    setGreen(p, avg)
    setBlue(p, avg)
  return pic

#Line drawing function that creates the line drawing used for the contrast in the picture
def lineDraw(pic, largeEnough):  
  pic = betterBnW(pic) #Resulting image is BnW, should probably convert to BnW
  
  for x in range(0, getWidth(pic)-1): #subtract one from width to account for when checking for pixel next to
    for y in range(0, getHeight(pic)-1): #subtract one from width to account for when checking for pixel next to
      pix = getPixel(pic, x, y) #Pixel
      pixRight = getPixel(pic,x+1, y) #Pixel to the right
      pixBelow = getPixel(pic, x, y+1) #Pixel below
      
      #Calculate the difference in the luminance between the pixel to the right and the pixel below using the ABS function
      lumDifRight = abs(getRed(pix)+getGreen(pix)+getBlue(pix))-(getRed(pixRight)+getGreen(pixRight)+getBlue(pixRight))
      lumDifBelow = abs(getRed(pix)+getGreen(pix)+getBlue(pix))-(getRed(pixBelow)+getGreen(pixBelow)+getBlue(pixBelow))
      
      #Lets do some drawing finally
      #Check to see if the pixel to the right is greater than the largeEnough value passed, and check to see if the value below is greater than the largeEnough as well
      if largeEnough < lumDifRight and lumDifBelow > largeEnough:
        setColor(pix, black)
      else:
        setColor(pix, white)

#Function used to add the border to the final picture. Border is currently set to blue.
def addBorder(pic):
  width = getWidth(pic)
  height = getHeight(pic)
  borderThickness = 20
  largeBlank = makeEmptyPicture(width+borderThickness, height+borderThickness, blue)
  copyStartBackground(pic, largeBlank, borderThickness, borderThickness)
  return largeBlank

#Simple copy function used to copy a picture  
def simpleCopy(picture):
  width = getWidth(picture)
  height = getHeight(picture)
  newPic = makeEmptyPicture(width, height)
  for x in range (0, width):
    for y in range (0, height):
      pix = getPixel(picture, x, y)
      color = getColor(pix)
      setColor(getPixel(newPic, x, y), color)
  return newPic

#Comicbook filter that calls all the functions and does all the work.    
def comicFilter():
  originalPic =  makePicture(pickAFile()) #Function call to select the picture you want to have the modifications done to
  temp = simpleCopy(originalPic) #Save the original picture into a temp file so that we can use it later
  lineDraw(originalPic, 20) #Line Draw function call that makes the line drawing picture used for contrast
  lineDrawing = simpleCopy(originalPic) #Save the line drawing picture into a variable
  originalPic = simpleCopy(temp) #Copy the original picture from the temp variable back into the originalPic variable
  chromaKeyed = chromakey(lineDrawing, originalPic) #Call the chromakey function to add the colored background onto the line drawing
  artify(chromaKeyed) #Call the artify function to give it that comicbookesq look
  artified = simpleCopy(chromaKeyed) #Save the chromakeyed file into the aritified variable
  bordered = addBorder(artified) #Call the function to add the border to the final picture
  return bordered #Return the finished picture
  
#file = r"D:\GitHub\CSUMB\CST205\Midterm\midtermPicture.jpg"
#writePictureTo(foo, file)