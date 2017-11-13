def pyCopyBlack(source, target, targetX, targetY):
  sWidth = getWidth(source)
  sHeight = getHeight(source)
  tWidth = getWidth(target)
  tHeight = getHeight(target)
  for x in range(0, sWidth):
    for y in range(0, sHeight):
      pix = getPixel(source, x, y)
      color = getColor(pix)
      if color.getRed() == 0 and color.getGreen() == 0 and color.getBlue() == 0:
        setColor(getPixel(target, (targetX + x), (targetY + y)), color)
  return target