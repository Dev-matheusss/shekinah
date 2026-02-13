package com.example.shekinah.presentation.screen.listprays

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shekinah.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    onMenuOption: (String) -> Unit = {}
) {

    var menuExpanded by remember { mutableStateOf(false) }
    Surface(
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 10.dp,
        modifier = Modifier
            .statusBarsPadding()
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(colorResource(R.color.orange_mod)),
            title = { Text("Orações", fontSize = 30.sp, style = TextStyle(Color.White)) },
            actions = {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Acount",
                        tint = Color.White,
                        modifier = Modifier.size(38.dp)
                    )
                }
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = {menuExpanded = false}
                ){
                    DropdownMenuItem(
                        text = {Text("Perfil")},
                        onClick = {
                            menuExpanded = false
                            onMenuOption("profile")
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Avisos")},
                        onClick = {
                            menuExpanded = false
                            onMenuOption("avisos")
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Sair")},
                        onClick = {
                            menuExpanded = false
                            onMenuOption("logout")

                        }
                    )
                }
            }
        )
    }
}