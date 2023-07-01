package com.example.dota2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dota2.ui.theme.Dota2Theme
import com.example.screens.main.feature.MainScreenNavPoint
import com.example.screens.main.impl.MainScreenHolder
import com.example.screens.player.feature.PlayerScreenNavPoint

@ExperimentalLayoutApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val mainScreenNavPoint = MainScreenNavPoint {
                navController.navigate(PlayerScreenNavPoint.link)
            }

            Dota2Theme(dynamicColor = false) {

                NavHost(
                    navController = navController,
                    startDestination = mainScreenNavPoint.link
                ) {
                    composable(mainScreenNavPoint.link) {
                        mainScreenNavPoint.ui(
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    composable(PlayerScreenNavPoint.link) {
                        PlayerScreenNavPoint.ui(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}