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