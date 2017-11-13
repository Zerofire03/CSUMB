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
  
def lineDraw(largeEnough):
  pic = makePicture(pickAFile())
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
  file = r"/Users/chris/Documents/GitHub/CSUMB/CST205/Module3/portfolio/lineDrawImage.jpg"
  writePictureTo(pic, file)