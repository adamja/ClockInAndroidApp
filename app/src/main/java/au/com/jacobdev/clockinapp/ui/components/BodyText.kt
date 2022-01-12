package au.com.jacobdev.clockinapp.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = AnnotatedString(text),
        color = Color.Black,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Left,
        textDecoration = TextDecoration.None,
        modifier = modifier,
    )
}