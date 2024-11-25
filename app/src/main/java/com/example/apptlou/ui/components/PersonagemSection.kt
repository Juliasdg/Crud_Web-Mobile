package com.example.apptlou.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PersonagemSection(title: String, text: String, imageResId: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = title,
            color = Color(0xFFFF6666),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 2.dp,
                    color = Color(0xFFFF6666),
                    shape = RoundedCornerShape(12.dp)
                )
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = text,
            color = Color(0xFFE0E0E0),
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 24.sp
        )
    }
}
