package com.blackshadow.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blackshadow.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface (
                    modifier = Modifier.fillMaxSize()
                ) {
                    UnitConverterPreview()
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun UnitConverterPreview() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }

    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }

    var leftExpanded by remember { mutableStateOf(false) }
    var rightExpanded by remember { mutableStateOf(false) }

    var leftconversionFactor = remember { mutableStateOf(1.0) }
    var rightconversionFactor = remember { mutableStateOf(1.0) }

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * leftconversionFactor.value * 100.0  / rightconversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    val titleStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 24.sp,
        textAlign = TextAlign.Center
    )
    val resultStyle = TextStyle(
        fontFamily = FontFamily.Monospace
    )

    Column (
        modifier = Modifier.statusBarsPadding().padding(top = 128.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text (
            text = "Unit Converter",
            style = titleStyle,
            modifier = Modifier.padding(bottom = 64.dp)
        )

        OutlinedTextField(
                value = inputValue,
                onValueChange = fun(newValue: String) {
                    inputValue = newValue
                    convertUnits()
            },
            label = { Text("Enter unit") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Row (
            modifier = Modifier.padding(top = 16.dp, end = 16.dp, start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box {
                Button (onClick = { leftExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(expanded = leftExpanded, onDismissRequest = { leftExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") }, onClick = {
                            leftExpanded = false
                            inputUnit = "Centimeters"
                            leftconversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") }, onClick = {
                            leftExpanded = false
                            inputUnit = "Meters"
                            leftconversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") }, onClick = {
                            leftExpanded = false
                            inputUnit = "Feet"
                            leftconversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeters") }, onClick = {
                            leftExpanded = false
                            inputUnit = "Milimeters"
                            leftconversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Box {
                Button (onClick = { rightExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(expanded = rightExpanded, onDismissRequest = { rightExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") }, onClick = {
                            rightExpanded = false
                            outputUnit = "Centimeters"
                            rightconversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") }, onClick = {
                            rightExpanded = false
                            outputUnit = "Meters"
                            rightconversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") }, onClick = {
                            rightExpanded = false
                            outputUnit = "Feet"
                            rightconversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeters") }, onClick = {
                            rightExpanded = false
                            outputUnit = "Milimeters"
                            rightconversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text (
            text = "Result : $outputValue $outputUnit",
            style = resultStyle
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}