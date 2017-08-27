package net.gatgetron.gcaptcha

import java.io.File
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author Gatgeagent
 * @cpackage net.gatgetron.gcaptcha
 * @cdate 27.08.17
 * @ctime 15:52
 * @cproject G-Captcha
 */
object RandomUtils {
    fun chooseRandomFont(fonts: ArrayList<String>): String {
        val random = Random()
        return fonts[random.nextInt(fonts.size)]
    }

    fun chooseRandomBackground(backgroundsPath: String): String {
        val random = Random()
        val pictures = File(backgroundsPath).listFiles()
        return pictures[random.nextInt(pictures.size)].absolutePath
    }

    fun generateRandomString(rng: Random, characters: String, length: Int): String {
        val text = CharArray(length)
        for (i in 0 until length) {
            text[i] = characters[rng.nextInt(characters.length)]
        }
        return String(text)
    }
}