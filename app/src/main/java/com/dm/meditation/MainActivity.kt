package com.dm.meditation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.dm.meditation.ui.theme.ColorFirst
import com.dm.meditation.ui.theme.ColorSecond
import com.dm.meditation.ui.theme.MeditationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Home() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(70.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            ColorFirst,
                            ColorSecond
                        )
                    )
                )
        ) {
            TextBlock(
                modifier = Modifier
                    .size(150.dp)
                    .padding(start = 20.dp, top = 5.dp),
                text = "Sleep Sound",
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        }
        LazyVerticalGrid(
            modifier = Modifier.padding(top = 10.dp, bottom = 80.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(15) { index ->

                Card(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(vertical = 2.dp)
                        .clickable {

                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Image(
                            rememberAsyncImagePainter(
                                "https://tvoemisto.tv/media/gallery/full/n/o/noty_db5fd.jpg"
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        TextBlock(
                            Modifier.align(Alignment.BottomCenter),
                            text = "titlr",
                            textStyle = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SpacerBlock(size: Int) {
    Spacer(modifier = Modifier.height(size.dp))
}

@Composable
fun TextBlock(
    modifier: Modifier,
    text: String,
    textStyle: TextStyle
) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle
    )
}

@Composable
fun Test() {

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val selectedPage = remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomBar(
                destinations = listOf(Screen.Home, Screen.Search),
                selectedPage = selectedPage,
                navController = navController,
            )
        }
    ) {
        NavHost(navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) { Home() }
            composable(Screen.Search.route) { Test() }
//                    composable(Screen.Profile.route) { ProfileScreen() }
//                    // Add more composables for other screens
        }
    }
}

@Composable
fun BottomBar(
    destinations: List<Screen>,
    selectedPage: MutableIntState,
    navController: NavController,
) {
    NavigationBar(
        containerColor = ColorFirst
    ) {
        destinations.forEachIndexed { index, item ->
            val isSelected = index == selectedPage.intValue
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    selectedPage.intValue = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(id = R.string.app_name)
                    )
                },
                label = {
                    Text(text = item.label)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MeditationTheme {
        Navigation()
    }
}