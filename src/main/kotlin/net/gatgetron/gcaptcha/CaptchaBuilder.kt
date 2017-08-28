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

import net.gatgetron.gcaptcha.RandomUtils.chooseRandomBackground
import net.gatgetron.gcaptcha.RandomUtils.chooseRandomFont
import net.gatgetron.gcaptcha.RandomUtils.generateRandomString
import java.awt.Color
import java.awt.Font
import java.awt.Image
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*
import javax.imageio.ImageIO


/**
 * @author Gatgeagent
 * @cpackage net.gatgetron.gcaptcha
 * @cdate 27.08.17
 * @ctime 11:45
 * @cproject G-Captcha
 */
class CaptchaBuilder(private val characterSet: String, private val length: Int, private val minLines: Int, private val maxLines: Int, private val backgroundsDir: String, private val fonts: ArrayList<String>) {

    fun generateCaptcha(): Captcha {
        val random = Random()

        val string = generateRandomString(random, characterSet, length)

        val image = ImageIO.read(File(chooseRandomBackground(backgroundsDir))).getScaledInstance(1000, 300, Image.SCALE_FAST)
        val graphicsImage = BufferedImage(1000, 300, BufferedImage.TYPE_INT_ARGB)
        var graphics = graphicsImage.createGraphics()

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

        graphics.font = Font(chooseRandomFont(fonts), Font.PLAIN, random.nextInt(40) + 56)

        graphics.drawImage(image, 0, 0, null)

        graphics.color = Color.getHSBColor(random.nextFloat(), random.nextFloat(), random.nextFloat())


        graphics.drawString(string, random.nextInt(400) + 30, random.nextInt(140) + 60)
        graphics.color = Color.getHSBColor(random.nextFloat(), random.nextFloat(), random.nextFloat())

        graphics.fillOval(random.nextInt(20) + 10, random.nextInt(20) + 10, random.nextInt(20) + 20, random.nextInt(20) + 20)
        val pair = GraphicUtils.drawLines(graphics, minLines, maxLines, random)
        graphics = pair.first
        val lineCount = pair.second
        graphics.background = Color.BLACK
        val output = ByteArrayOutputStream()
        ImageIO.write(graphicsImage, "PNG", output)

        return Captcha(output.toByteArray(), string, lineCount, graphics.font.fontName)
    }
}