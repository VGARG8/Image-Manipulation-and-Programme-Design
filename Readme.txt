# PDP_NEU

Project Description

This program provides a set of image processing operations that can be performed on an image in ppm.
Users can load a ppm file from a file path, save an image to a file path, and modify the image in a
variety of ways. The available operations include:

Image Processing Operations

This program provides various image processing operations that can be performed on an image.
Changes in Iteration 2:
1. added new filters to the project blur, sepia tone, dither, greyscale, sharpen using gaussian matrix.
also removed explicit use of string and introduced Constants class to hold string constants.
2. optimized the code by introducing dynamic programming in filters.
3. Abstracted the methods related to flip the image.
Changes in Iteration 3:
1. providing a GUI with the previous implementation.
2.  removed the lengthy switch cases in controller instead of that shifted to command design
pattern.
3. There is a new analysis functionality added to project that is generating histograms both bar and line.
Operations
4. Abstracted sharpen and blur methods, removed redundancy in the code.

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

16 Sepia-tone : create a sepia tone image using guassian filter.

17. Blur : crate blur image using gauss filter.

18. Sharpen : sharpen image using gauss filter.

19. Dither : Dither the given image.

20. Greyscale-tone:  using luma filter matrix multiply.

Limitations : For now these operations are performed on
ppm file, jpg,bmp and png.
We are converting images to greyscale for improving the efficiency.

At current project can be used in three ways one is to
upload the script having command related to operations to be performed on image.
Second, one can use menu-driven console based method as well.
In third you can mention the script path to run it.

The script files used for our image are located in Res folder: scriptfile.txt and scriptfile2.txt
and script3.txt.
All the required images, ppm files, script files are in Res folder.

Program design Image is also located in the Res Folder.

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


------------------------------------
New operations

# for loading PNG/JPG/BMP files
load ./Res/face.png face

# to blur an image
blur image blurred_image_name

# to sharpen an image
sharpen image sharpened_image_name

# to dither an image
dither image dithered_image_name

# to get sepia tone an image
sepia-tone image sepia_image_name

# to get greyscale tone an image
greyscale-tone image sepia_image_name

load ./Res/face.png face

vertical-flip face face-vertical
# to blur an image
blur face-vertical face-vertical-blur
# to apply sepia tone to image already blurred
sepia-tone  face-vertical-blur  face-vertical-blur-sepia
# to save image as jpg  <operation, image name, image to be saved>
save face-vertical-blur-sepia.jpg face-vertical-blur-sepia
# applying dither
dither face face-dither
# saving it as bmp
save face-dither.bmp face-dither
---- script file end ------

# Command to run a script through interactive mode:
"run-script filepath"

# To run the program using the terminal by passing a scriptfile as argument:
java Main -file filepath




Images used for testing :

1. gamecontroller.ppm, which is generated from png format.
This Image is downloaded from
https://pixabay.com/illustrations/nintendo-videogame-joystick-game-5964773/
Pixabay License.Free to use under the Pixbay

2. face.png, which is bought from
https://pixels.com/featured/pretending-original-pop-art-tom-fedro-fidostudio.html
Pretending - Original Pop ArtOriginal by Tom Fedro

3. car.bmp, which is downloaded from
https://pixabay.com/vectors/travel-moving-road-trip-car-6768660/, resized and converted to bmp using
photoshop.
Pixabay License
Free to use under the Pixabay license
No attribution required