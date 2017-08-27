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