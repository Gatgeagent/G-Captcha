package net.gatgetron.gcaptcha

import java.awt.Font
import java.awt.GraphicsEnvironment
import java.io.File

/**
 * @author Gatgeagent
 * @cpackage net.gatgetron.gcaptcha
 * @cdate 27.08.17
 * @ctime 13:50
 * @cproject G-Captcha
 */

class FontLoader(fontsDir: String) {

    val fontnames = ArrayList<String>()

    init {
        val fonts = File(fontsDir).listFiles()
        val graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment()
        fonts.forEach {
            val font = Font.createFont(Font.TRUETYPE_FONT, File(it.absolutePath))
            val name = font.name
            graphicsEnvironment.registerFont(font)
            fontnames.add(name)
        }
    }
}