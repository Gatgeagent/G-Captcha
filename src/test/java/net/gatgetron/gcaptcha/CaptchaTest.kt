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
            val captcha = CaptchaBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789", 3, 45, "backgrounds", fontLoader.fontnames).generateCaptcha()
            Assert.assertNotNull(captcha)
        }

        println("Generating a number captcha with a lot of lines...")
        Assert.assertNotNull(CaptchaBuilder("0123456789", 10000, 10000, "backgrounds", fontLoader.fontnames).generateCaptcha())

        println("Generating a captcha with no text or lines...")
        try {
            Assert.assertNotNull(CaptchaBuilder("", 0, 0, "backgrounds", fontLoader.fontnames).generateCaptcha())
            Assert.fail("Created a captcha with no text or lines")
        } catch (e: IllegalArgumentException) {

        }

        println("Checking captcha values...")
        val captcha = CaptchaBuilder("Q", 3, 3, "backgrounds", fontLoader.fontnames).generateCaptcha()
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
