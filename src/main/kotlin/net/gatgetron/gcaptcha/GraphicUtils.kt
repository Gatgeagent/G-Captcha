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

import java.awt.Color
import java.awt.Graphics2D
import java.util.*

/**
 * @author Gatgeagent
 * @cpackage net.gatgetron.gcaptcha
 * @cdate 27.08.17
 * @ctime 17:03
 * @cproject G-Captcha
 */
object GraphicUtils {
    fun drawLines(graphics: Graphics2D, minLines: Int, maxLines: Int, random: Random): Pair<Graphics2D, Int> {
        var lineCount = 0

        do {
            lineCount++
            graphics.color = Color.getHSBColor(random.nextFloat(), random.nextFloat(), random.nextFloat())
            val x1 = random.nextInt(250)
            val x2 = random.nextInt(250)
            val y1 = random.nextInt(250)
            val y2 = random.nextInt(250)
            graphics.drawLine(-1 + x1, -1 + y1, 99 + x2, 99 + y2)
            graphics.drawLine(0 + x1, 0 + y1, 100 + x2, 100 + y2)
            graphics.drawLine(1 + x1, 1 + y1, 101 + x2, 101 + y2)
        } while ((random.nextBoolean() || random.nextBoolean() ||  random.nextBoolean() || (random.nextBoolean() && random.nextBoolean()) || lineCount < minLines) && lineCount < maxLines)

        return Pair(graphics, lineCount)
    }
}