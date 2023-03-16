# PDP_NEU

Project Description

This program provides a set of image processing operations that can be performed on an image in ppm.
Users can load an ppm file from a file path, save an image to a file path, and modify the image in a
variety of ways. The available operations include:

Image Processing Operations

This program provides various image processing operations that can be performed on an image.

Operations

1. Load: Load the image from a filepath.
2. Save: Save the image to a filepath.
3. Brighten: Increase the brightness of the image by a given value.
4. Darken: Decrease the brightness of the image by a given value.
5. Vertical-flip: Flip the image vertically.
6. Horizontal-flip: Flip the image horizontally.
7. Greyscale red-component: Create a greyscale image using the red-component of the original image.
8. Greyscale green-component: Create a greyscale image using the green-component of the original image.
9. Greyscale blue-component: Create a greyscale image using the blue-component of the original image.

10.Greyscale value-component: Create a greyscale image using the value-component of the original image.
11. Greyscale intensity-component: Create a greyscale image using the intensity-component of the
 original image.
12. Greyscale luma-component: Create a greyscale image using the luma-component of the original image.
13. RGB-split: Split the original image into three greyscale images containing its red, green, and blue
components, respectively.

14 RGB-combine: Combine three greyscale images into a single image that gets its red, green, and blue
components from the three images, respectively.

15 Run-script: Load and run the script commands in the specified file.

Limitations : For now these operations are performed on
ppm file.
We are converting images to greyscale to improve the efficiency.

At current project can be used in two ways one is to
upload the script having command related to operations to be performed on image.
Second, one can use menu driven console based method as well.

The example of how to create script commands is given below :


---- script file start------

#load koala.ppm and call it 'koala'
load images/koala.ppm koala

#brighten koala by adding 10  
brighten 10 koala koala-brighter

#flip koala vertically
vertical-flip koala koala-vertical

#flip the vertically flipped koala horizontally
horizontal-flip koala-vertical koala-vertical-horizontal

#create a greyscale using only the value component, as an image koala-greyscale
greyscale value-component koala koala-greyscale

#save koala-brighter
save images/koala-brighter.ppm koala-brighter

#save koala-greyscale
save images/koala-gs.ppm koala-greyscale

#overwrite koala with another file
load images/upper.ppm koala

#give the koala a red tint
rgb-split koala koala-red koala-green koala-blue

#brighten just the red image
brighten 50 koala-red koala-red

#combine them back, but by using the brightened red we get a red tint
rgb-combine koala-red-tint koala-red koala-green koala-blue
save images/koala-red-tint.ppm koala-red-tint

---- script file end -------
