# G-Captcha
Simple Captcha library written in Kotlin

G-Captcha is a library to generate images which are hard to read for robots/OCR readers, it consists just out of five classes.  
I needed a library which generates captchas I could use in the Spark framework, but everything I found were servlets.  
And this was what I came up with:

## Examples
#### For these examples, I used following fonts:
- Buff (Huckle Buff), [download][huckle-buff]
- Gorillaz2, [download][gorillaz2]
- MomsTypeWriter, [download][mumstypewriter]
- NoRefunds, [download][norefunds]
- Zreaks NFI, [download][zreaks]
#### Backgrounds:
The backgrounds from these pictures come from [JCaptcha][jcaptcha], a library which unfortunately does not seem to be maintained currently.
### Pictures:
You can find more pictures in the examples/ directory.  

![1][1]
![13][13]
![15][15]
![16][16]
![18][18] 

## Usage

- Add your fonts (TrueTypeFont (.ttf) preferred) to your fonts directory, which is specified in the constructor of FontLoader.
- Add your backgrounds (it works with JPGs) to the backgrounds directory, which is specified in the constructor of CaptchaBuilder.

It should be possible to use this library using Java (becuase Kotlin compiles to Java-Bytecode), and maybe even other JVM languages like Groovy, Scala, and so on...

##### Create three numeric captcha with 3 - 45 random lines, 

```kotlin
val fontLoader = FontLoader("fonts") // Registers each font in the "File("fonts")" directory to the GraphicsEnvironment.

/*
 * class CaptchaBuilder(
 *     characterSet: String, // The character set to choose characters from, eg "ABCDEFG", "123456", "ABCabc123"
 *     length: Int, // The length of the text displayed on the captcha, "6" works very well
 *     minLines: Int, // How many random lines to draw at least
 *     maxLines: Int, // How many random lines to draw maximally
 *     fonts: ArrayList<String> // From which fonts to choose from
 *     )
 */
val builder = CaptchaBuilder("0123456789", 6,  3, 45, "backgrounds", fontLoader.fontnames)

val captcha1 = builder.generateCaptcha()
val captcha2 = builder.generateCaptcha()
val captcha3 = builder.generateCaptcha()
```

##### Get the several values from the captcha
```kotlin
val captcha = builder.generateCaptcha()

val image: ByteArray = captcha.pngImage // Get the captcha png-image (as ByteArray)
val text: String = captcha.text     // Get the text on the captcha
val lineCount: Int = captcha.lineCount // Get the amount of lines drawn
val fontName: String = captcha.fontName // Get the font used to draw the string onto the image
``` 

##### Check if the user entered the correct captcha:
```kotlin
val captcha = builder.generateCaptcha()

val image = captcha.pngImage

user.sendCaptchaImage(image) // Use your method of sending the image, eg. picture on website

val userResponse = user.getResponse() // Use your method of getting input, eg. form on website

if (captcha.text == userResponse) {
    println("Correct.")
} else {
    println("Incorrect.")
}
```

##### Generate a captcha using the Spark Framework:
```kotlin
val fontLoader = FontLoader("fonts")
val builder = CaptchaBuilder("AA", 3, 20, "backgrounds", fontLoader.fontnames)

get("/captcha") { request, response ->
    response.type("image/png")
    return@get builder.generateCaptcha().pngImage
}
```
##

Some variables are hardcoded to CaptchaBuilder.kt, like the width of the picture.  
Please change it to your needs. Unfortunately, I didn't had much luck with other settings than the current default ones. 
I didn't spent too much time writing this, it is fairly easy, but the code's maybe not the prettiest. 

[huckle-buff]: http://www.downloadfreefonts.com/fonts/512/huckle_buff.php
[gorillaz2]: https://fonts2u.com/gorillaz-2.font
[mumstypewriter]: http://www.dafont.com/moms-typewriter.font
[norefunds]: http://www.dafont.com/moms-typewriter.font
[zreaks]: http://www.dafont.com/zreaks-nfi.font

[jcaptcha]: https://sourceforge.net/projects/jcaptcha/

[1]: https://raw.githubusercontent.com/Gatgeagent/G-Captcha/master/examples/1.png
[13]: https://raw.githubusercontent.com/Gatgeagent/G-Captcha/master/examples/13.png
[15]: https://raw.githubusercontent.com/Gatgeagent/G-Captcha/master/examples/15.png
[16]: https://raw.githubusercontent.com/Gatgeagent/G-Captcha/master/examples/16.png
[18]: https://raw.githubusercontent.com/Gatgeagent/G-Captcha/master/examples/18.png
