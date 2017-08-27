/*
 * MIT License
 *
 * Copyright (c) 2017 Gatgeagent
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package net.gatgetron.gcaptcha

import java.io.File
import java.util.*

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