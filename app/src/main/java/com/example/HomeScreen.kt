package com.example

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AmberPoints
import com.example.ui.theme.IndigoAccent
import com.example.ui.theme.PrimaryBlue

data class Module(
    val name: String, 
    val docCount: Int, 
    val id: String, 
    val colorAccent: Color = PrimaryBlue,
    val bgAccent: Color = PrimaryBlue.copy(alpha = 0.1f)
)

@Composable
fun HomeScreen() {
    val modules = listOf(
        Module("Bio Cellulaire", 42, "1", PrimaryBlue, PrimaryBlue.copy(alpha = 0.1f)),
        Module("Chimie Org.", 28, "2", IndigoAccent, IndigoAccent.copy(alpha = 0.1f)),
        Module("Biostatistique", 15, "3", com.example.ui.theme.GreenSuccess, com.example.ui.theme.GreenSuccess.copy(alpha = 0.1f)),
        Module("Génétique", 33, "4", AmberPoints, AmberPoints.copy(alpha = 0.1f))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                        .background(PrimaryBlue)
                        .shadow(1.dp, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("YA", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Salut,", fontWeight = FontWeight.Medium, fontSize = 12.sp, color = com.example.ui.theme.TextSecondaryLight)
                    Text("Youssef Amrani", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(
                    modifier = Modifier
                        .background(AmberPoints.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(modifier = Modifier.size(8.dp).background(AmberPoints, CircleShape))
                    Text("120 pts", color = AmberPoints, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {}, modifier = Modifier.size(40.dp)) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Notifications", tint = com.example.ui.theme.TextSecondaryLight)
                }
            }
        }

        // University Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 24.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Brush.linearGradient(listOf(PrimaryBlue, IndigoAccent)))
                .padding(20.dp)
        ) {
            Column {
                Text(
                    "UNIVERSITÉ MOHAMMED V — RABAT",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "SMPC — S4 Chimie",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column {
                        Text("Uploads", color = Color.White.copy(alpha=0.7f), fontSize = 10.sp)
                        Text("18", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                    Box(modifier = Modifier.width(1.dp).height(32.dp).background(Color.White.copy(alpha = 0.2f)))
                    Column {
                        Text("Downloads", color = Color.White.copy(alpha=0.7f), fontSize = 10.sp)
                        Text("52", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                    Box(modifier = Modifier.width(1.dp).height(32.dp).background(Color.White.copy(alpha = 0.2f)))
                    Column {
                        Text("Ranking", color = Color.White.copy(alpha=0.7f), fontSize = 10.sp)
                        Text("#3", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Main Browse Column
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Browse Modules", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MaterialTheme.colorScheme.onBackground)
                Text("See All", color = PrimaryBlue, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable{})
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(modules) { module ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(module.bgAccent),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Filled.Science, contentDescription = null, tint = module.colorAccent, modifier = Modifier.size(20.dp))
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(module.name, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = MaterialTheme.colorScheme.onBackground)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text("${module.docCount} Documents", fontSize = 11.sp, color = com.example.ui.theme.TextSecondaryLight)
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Community Activity Preview
            Text("Community Activity", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MaterialTheme.colorScheme.onBackground, modifier = Modifier.padding(bottom = 12.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom=8.dp)) {
                        Box(modifier = Modifier.size(24.dp).clip(CircleShape).background(Color.LightGray))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Anas B. ", fontWeight = FontWeight.Medium, fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground)
                        Text("• 5m ago", fontSize = 12.sp, color = com.example.ui.theme.TextSecondaryLight)
                    }
                    Text("Qui a le résumé du module d'Histologie S4?", fontSize = 13.sp, color = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}
