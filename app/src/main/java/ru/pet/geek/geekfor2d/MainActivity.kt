package ru.pet.geek.geekfor2d

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import ru.pet.geek.geekfor2d.container.MainContainer
import ru.pet.geek.ui.GeekTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //TODO add control from other place
            GeekTheme(isDarkTheme = isSystemInDarkTheme()) {
                MainContainer().Content()
            }

        }
    }
}
