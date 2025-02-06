package com.example.tipcalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    var amountInput by remember { mutableStateOf("") }
    var amount = amountInput.toDoubleOrNull()?: 0.0
    var tipInput by remember { mutableStateOf("") }
    var tipPercent = tipInput.toDoubleOrNull()?:0.0
    var roundUp by remember { mutableStateOf(false) }
    var tip = calculateTip(amount, tipPercent,roundUp)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Column() {
            Text(
                text = "Calculate Tip",
                fontSize = 35.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            EditNumberField(
                leadingIcon = R.drawable.money,
                label = R.string.bill_amount,
                value = amountInput,
                onValueChange = {amountInput=it},
                modifier = Modifier.padding(bottom = 32.dp)
            )
            EditNumberField(
                leadingIcon = R.drawable.percent,
                label = R.string.tip_percentage,
                value = tipInput,
                onValueChange = {tipInput=it},
                modifier = Modifier.padding(bottom = 32.dp)
            )
            RoundTheTipRow(
                roundUp = roundUp,
                onRoundUpChanged = {roundUp=it}
            )
            Text(
                text = "Tip Amount: $tip",
                modifier=Modifier.align(alignment = Alignment.End),
                style = MaterialTheme.typography.headlineMedium
            )
        }

    }
}

@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean)->Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text="Round up tip?",
            fontSize = 20.sp
        )
        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            modifier = Modifier.padding(start=95.dp)
        )
    }
}


internal fun calculateTip(amount:Double, tipPercent: Double=15.0, roundUp:Boolean):String{
    var tip = tipPercent/100 *amount
    if(roundUp){
        tip = kotlin.math.ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Composable
fun EditNumberField(
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int,
    value: String,
    onValueChange:(String)->Unit,
    modifier: Modifier=Modifier){
    TextField(
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null
            )},
        value=value,
        onValueChange = onValueChange,
        singleLine = true,
        label = {Text(stringResource(label))},
        modifier=modifier
    )
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCalculatorAppTheme {
        TipCalculator()
    }
}