import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.tooling.preview.Preview
import com.example.keypad.R

@Composable
fun NumPad(
    maxDigits: Int,
    onNumberClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onEnterClick: (String) -> Unit
) {
    var pin by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        val buttons = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "delete", "0", "accept")

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            buttons.forEach { label ->
                item {
                    PinPadButton(
                        label = label,
                        isEnabled = label != "accept" || pin.length == maxDigits // Habilitar "accept" solo cuando se alcanza maxDigits
                    ) {
                        when (label) {
                            "delete" -> {
                                if (pin.isNotEmpty()) {
                                    pin = pin.dropLast(1)
                                    onDeleteClick()
                                }
                            }
                            "accept" -> {
                                if (pin.length == maxDigits) {
                                    onEnterClick(pin)
                                }
                            }
                            else -> {
                                if (pin.length < maxDigits) {
                                    pin += label
                                    onNumberClick(label)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PinPadButton(
    label: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .aspectRatio(1f)
            .background(
                color = if (isEnabled) Color.White else Color.Gray, // Cambiar color si está deshabilitado
                shape = RoundedCornerShape(8.dp)
            )
            .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(8.dp))
            .clickable(enabled = isEnabled) { onClick() }
            .padding(8.dp)
    ) {
        when (label) {
            "delete" -> Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_background),
                contentDescription = "Delete",
                tint = Color.Magenta,
                modifier = Modifier.fillMaxSize(0.5f)
            )
            "accept" -> Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Confirm",
                tint = Color.Magenta,
                modifier = Modifier.fillMaxSize(0.5f)
            )
            else -> Text(
                text = label,
                fontSize = 56.sp,
                color = Color.Magenta,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    NumPad(
        maxDigits = 8,
        onNumberClick = {},
        onDeleteClick = { },
        onEnterClick = {}
    )
}
