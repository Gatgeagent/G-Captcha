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

import junit.framework.Assert
import junit.framework.Test
import junit.framework.TestCase
import junit.framework.TestSuite
import java.awt.GraphicsEnvironment

class CaptchaTest(testName: String) : TestCase(testName) {

    fun testApp() {

        println("Loading fonts...")
        val fontLoader = FontLoader("fonts")
        Assert.assertNotNull(fontLoader)

        println("Generating a lot of normal captchas...")
        for (i in 1..99) {
            val captcha = CaptchaBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789", 6, 3, 45, "backgrounds", fontLoader.fontnames).generateCaptcha()
            Assert.assertNotNull(captcha)
        }

        println("Generating a number captcha with a lot of lines...")
        Assert.assertNotNull(CaptchaBuilder("0123456789", 6, 10000, 10000, "backgrounds", fontLoader.fontnames).generateCaptcha())

        println("Generating a captcha with no text or lines...")
        try {
            Assert.assertNotNull(CaptchaBuilder("", 6, 0, 0, "backgrounds", fontLoader.fontnames).generateCaptcha())
            Assert.fail("Created a captcha with no text or lines")
        } catch (e: IllegalArgumentException) {

        }

        println("Checking captcha values...")
        val captcha = CaptchaBuilder("Q", 6, 3, 3, "backgrounds", fontLoader.fontnames).generateCaptcha()
        Assert.assertTrue(GraphicsEnvironment.getLocalGraphicsEnvironment().availableFontFamilyNames.contains(captcha.fontName))

        Assert.assertEquals(3, captcha.lineCount)

        Assert.assertNotNull(captcha.pngImage)

        Assert.assertEquals("QQQQQQ", captcha.text)


    }

    companion object {

        fun suite(): Test {
            return TestSuite(CaptchaTest::class.java)
        }
    }
}
