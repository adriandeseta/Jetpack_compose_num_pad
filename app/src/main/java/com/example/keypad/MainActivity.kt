package com.example.keypad

import NumPad
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.keypad.ui.theme.KeypadTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeypadTheme {
                var pinCode by remember { mutableStateOf("") }

                Column(
                        Modifier.background(Color.LightGray)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally) {

                    // Muestra el PIN ingresado
                    Text(
                        text = pinCode,
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp,
                        modifier = Modifier.background(Color.Red)
                                   .fillMaxWidth()
                    )

                    // Componente NumPad
                    NumPad(
                        maxDigits = 8,
                        onNumberClick = { number ->
                            if (pinCode.length < 8) { // Restringimos a maxDigits
                                pinCode += number
                            }
                        },
                        onDeleteClick = {
                            if (pinCode.isNotEmpty()) {
                                pinCode = pinCode.dropLast(1)
                            }
                        },
                        onEnterClick = {
                            println("El código ingresado es $pinCode")
                        }
                    )
                }
            }
        }
    }
}

/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    KeypadTheme {
        var pinCode by remember { mutableStateOf("") }

        Column(

            Modifier.background(Color.LightGray)
                    .fillMaxHeight()
                    .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = pinCode,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxWidth()
            )

            // Componente NumPad para el preview
            NumPad(
                onNumberClick = { number ->
                    if (pinCode.length < 4) {
                        pinCode += number
                    }
                },
                onDeleteClick = {
                    if (pinCode.isNotEmpty()) {
                        pinCode = pinCode.dropLast(1)
                    }
                },
                onEnterClick = {
                    println("el codigo es $pinCode")
                },
                maxDigits = 8
            )
        }
    }
}
*/
