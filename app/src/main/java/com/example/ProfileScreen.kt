package com.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.PrimaryBlue
import com.example.ui.theme.IndigoAccent
import com.example.ui.theme.AmberPoints
import com.example.ui.theme.RedDelete

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(PrimaryBlue),
                contentAlignment = Alignment.Center
            ) {
                Text("YA", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 28.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Youssef Amrani", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)
            Text("SMPC — S4 Chimie", color = com.example.ui.theme.TextSecondaryLight, fontSize = 14.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Brush.horizontalGradient(listOf(PrimaryBlue, IndigoAccent)))
                    .padding(16.dp)
            ) {
                Column {
                    Text("Points", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                    Text("120 pts", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(AmberPoints.copy(alpha = 0.1f))
                    .padding(16.dp)
            ) {
                Column {
                    Text("Ranking", color = AmberPoints, fontSize = 12.sp)
                    Text("#3", color = AmberPoints, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        ProfileMenuItem(icon = Icons.Filled.Settings, text = "Settings")
        ProfileMenuItem(icon = Icons.Filled.Language, text = "Language")
        ProfileMenuItem(icon = Icons.Filled.Palette, text = "Appearance")
        ProfileMenuItem(icon = Icons.Filled.Logout, text = "Log out", color = RedDelete)
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, text: String, color: Color? = null) {
    val tintColor = color ?: MaterialTheme.colorScheme.onBackground
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = tintColor)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 16.sp, color = tintColor, modifier = Modifier.weight(1f))
        if (color == null) {
            Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = com.example.ui.theme.TextSecondaryLight)
        }
    }
}
