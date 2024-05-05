package core.const

import androidx.compose.ui.graphics.Color

object ColorConst {
    object Background {
        val PRIMARY = mineShaft
        val SECONDARY = darkMineShaft
        val WHITE = Color.White
    }

    object Text {
        val PRIMARY = Color.White
        val SECONDARY = gray
        val DARK = darkMineShaft
    }

    object Colors {
        val ACCENT = piper
    }
}

private val mineShaft = Color(0xFF303030)
private val darkMineShaft = Color(0xFF262626)
private val piper = Color(0xFFC25120)
private val gray = Color(0xFF838383)
private val forestGreen = Color(0xFF3A8F2C)
private val easternBlue = Color(0xFF1993AE)
private val oldBrick = Color(0x8A1C1C)
