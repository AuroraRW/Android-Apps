package com.example.tipcalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculatorapp.ui.theme.TipCalculatorAppTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        modifier = Modifier.padding(innerPadding)

                    )
                }
            }
        }
    }
}

@Composable
fun TipCalculator(modifier: Modifier = Modifier) {
//    var amountInput = "0"
    var amountInput by remember { mutableStateOf("0") }
    var amount = amountInput.toDoubleOrNull() ?: 0.0
    var tip = calculateTip(amount)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column() {
            Text(
                text = "Calculate Tip",
                fontSize = 35.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 40.dp)
            )
//                TextField(
//                    // value and onValueChange are required
//                    value=amountInput,
//    //            onValueChange = {newValue-> amountInput=newValue}
//    //            onValueChange = {it-> amountInput=it}
//                    onValueChange = {amountInput = it},
//                    label = { Text("Bill amount")},
//                    singleLine = true,
//                    modifier= Modifier
//                        .padding(bottom = 32.dp)
//                )

            EditNumberField(
                value = amountInput,
                onValueChange = { amountInput = it },
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Text(
                text = "Tip Amount: $tip",
                style = MaterialTheme.typography.headlineMedium,
                //            modifier = Modifier.align(alignment = Alignment.End)
                //                .padding(end=68.dp)
            )
        }
    }
}
private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
//    return tip.toString()
}

@Composable
fun EditNumberField(
    value:String,
    onValueChange: (String)->Unit,
    modifier: Modifier = Modifier
){
    TextField(
        value=value,
        onValueChange = onValueChange,
        label = { Text("Bill amount")},
        singleLine = true,
        modifier= modifier
    )
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCalculatorAppTheme {
        TipCalculator()
    }
}