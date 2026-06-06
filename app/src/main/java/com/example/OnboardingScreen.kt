package com.example

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

data class OnboardingSlide(
    val icon: ImageVector,
    val title: String,
    val subtitle: String,
    val buttonText: String
)

val onboardingSlides = listOf(
    OnboardingSlide(
        icon = Icons.Filled.School,
        title = "Welcome to Docly",
        subtitle = "The student-powered platform to share and discover academic resources.",
        buttonText = "Get Started"
    ),
    OnboardingSlide(
        icon = Icons.Filled.TrendingUp,
        title = "Earn Points for Every Upload",
        subtitle = "Upload documents, get downloaded, and climb the leaderboard rankings.",
        buttonText = "Next"
    ),
    OnboardingSlide(
        icon = Icons.Filled.Search,
        title = "Find Any Course Material",
        subtitle = "Search by module, professor, or document type. Everything in one place.",
        buttonText = "Next"
    ),
    OnboardingSlide(
        icon = Icons.Filled.People,
        title = "Join the Community",
        subtitle = "Ask questions, share answers, and connect with students from your university.",
        buttonText = "Let's Go"
    )
)

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    var currentSlide by remember { mutableStateOf(0) }
    val slide = onboardingSlides[currentSlide]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Skip",
                color = TextSecondaryLight,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clickable { onFinish() }
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(120.dp)
                .background(PrimaryBlue.copy(alpha = 0.1f), RoundedCornerShape(24.dp))
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = slide.icon,
                contentDescription = null,
                tint = PrimaryBlue,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = slide.title,
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = slide.subtitle,
            fontSize = 15.sp,
            color = TextSecondaryLight,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in onboardingSlides.indices) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .height(8.dp)
                        .width(if (i == currentSlide) 24.dp else 8.dp)
                        .clip(CircleShape)
                        .background(if (i == currentSlide) PrimaryBlue else PrimaryBlue.copy(alpha = 0.2f))
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (currentSlide < onboardingSlides.size - 1) {
                    currentSlide++
                } else {
                    onFinish()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
        ) {
            Text(
                text = slide.buttonText,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Already have an account? Sign in",
            color = PrimaryBlue,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .clickable { onFinish() }
                .padding(8.dp)
        )
    }
}
