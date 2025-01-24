package com.example.tipcaculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.tipcaculatorapp.ui.theme.TipCaculatorAppTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCaculatorAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCaculator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TipCaculator(modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("0") }
    var amount = amountInput.toDoubleOrNull()?: 0.0
    var tip = calculateTip(amount)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Column(){
            Text(
                text="Calculate Tip",
                fontSize = 35.sp
            )
            EditNumberField(
                value = amountInput,
                onValueChange = {amountInput=it}
            )
            Text(
                text="Tip Amount: $tip",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(alignment = Alignment.End)
            )
        }
    }
}
@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String)->Unit,
    modifier: Modifier=Modifier){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text("Bill amount")},
        singleLine = true
    )
}
private fun calculateTip(amount: Double, tipPercent: Double = 15.0):String{
    var tip = tipPercent/100 *amount
//    return tip.toString()
    return NumberFormat.getCurrencyInstance().format(tip)
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCaculatorAppTheme {
        TipCaculator()
    }
}