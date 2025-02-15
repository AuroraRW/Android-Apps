package com.example.woofapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.woofapp.data.Dog
import com.example.woofapp.data.dogs
import com.example.woofapp.ui.theme.WoofAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WoofAppTheme {
                WoofApp()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofTopAppBar(){
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter=painterResource(R.drawable.ic_woof_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small))
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    )

}
@Composable
fun WoofApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            WoofTopAppBar()
        }
        ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding)
        {
            items(dogs){
                DogItem(
                    dog = it,
                    modifier = Modifier.
                    padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
fun DogItem(
    dog: Dog,
    modifier:Modifier=Modifier){
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if(expanded) MaterialTheme.colorScheme.tertiaryContainer
                      else MaterialTheme.colorScheme.primaryContainer
    )
    Card(modifier=modifier){
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .background(color=color)
        ){
            Row(modifier=modifier
                .fillMaxWidth()
            ){
                DogIcon(dog.imageResourceId)
                DogInformation(dog.name, dog.age)
                Spacer(modifier= Modifier.weight(1f))
                DogItemButton(
                    expanded = expanded,
                    onClick = {expanded=!expanded}
                )
            }
            if(expanded){
                DogHobby(
                    dog.hobbies,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_small),
                        bottom=dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}
@Composable
fun DogHobby(
    @StringRes dogHobby:Int,
    modifier: Modifier=Modifier
){
    Column(modifier=modifier){
        Text(
            text = "About:",
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text= stringResource(dogHobby),
            style = MaterialTheme.typography.bodyLarge)
    }
}
@Composable
fun DogItemButton(
    expanded:Boolean,
    onClick:()->Unit
){
    IconButton(
        onClick = onClick
    ){
        Icon(
            imageVector = if(expanded) Icons.Filled.KeyboardArrowUp
                          else Icons.Filled.KeyboardArrowDown,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error
        )
    }
}
@Composable
fun DogIcon(
    @DrawableRes dogIcon:Int
){
    Image(
        modifier = Modifier
                .size(dimensionResource(R.dimen.image_size))
                .padding(dimensionResource(R.dimen.padding_small))
                .clip(MaterialTheme.shapes.small),
        painter = painterResource(dogIcon),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}
@Composable
fun DogInformation(
    @StringRes dogName: Int,
    dogAge: Int
){
    Column()
    {
        Text(
            text = stringResource(dogName),
            style = MaterialTheme.typography.displayMedium)
        Text(
            text= stringResource(R.string.years_old, dogAge),
            style = MaterialTheme.typography.bodyLarge)
    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WoofAppTheme {
        WoofApp()
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreviewDark() {
    WoofAppTheme(darkTheme= true) {
        WoofApp()
    }
}
