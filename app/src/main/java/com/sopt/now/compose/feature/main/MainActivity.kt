package com.sopt.now.compose.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

val LocalPhoneSizeComposition = staticCompositionLocalOf {
    PhoneSize.MEDIUM
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()
            val deviceWidth = applicationContext?.resources?.displayMetrics?.widthPixels ?: 0

            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompositionLocalProvider(
                        LocalPhoneSizeComposition provides PhoneSize.getPhoneSize(deviceWidth)
                    ) {
                        MainScreen(navigator)
                    }
                }
            }
        }
    }
}

enum class PhoneSize(val minWidthSize: Int) {
    BIG(1840), // Pixel Fold 기준
    MEDIUM(1080), // Android Studio Medium Phone 기준
    SMALL(720); // Android Studio Small Phone 기준

    companion object {
        fun getPhoneSize(phoneWidth: Int): PhoneSize = when {
            BIG.minWidthSize <= phoneWidth -> BIG
            MEDIUM.minWidthSize <= phoneWidth -> MEDIUM
            else -> SMALL
        }
    }
}
