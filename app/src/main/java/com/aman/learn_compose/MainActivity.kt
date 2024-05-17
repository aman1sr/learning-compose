package com.aman.learn_compose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aman.learn_compose.data.Dog
import com.aman.learn_compose.data.dogs
import com.aman.learn_compose.ui.theme.WoofTheme

/*
* 4. color: https://developer.android.com/codelabs/basic-android-kotlin-compose-material-theming?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-3-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-material-theming#3
*
* */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    WoofApp()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun WoofApp() {
        Scaffold(
            topBar = {
                WoofTopAppBar()
            }
        ) { it ->
            LazyColumn(contentPadding = it) {
                items(dogs) {
                    DogItem(
                        dog = it,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun WoofTopAppBar(modifier: Modifier = Modifier) {
        CenterAlignedTopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.image_size))
                            .padding(dimensionResource(id = R.dimen.padding_small)),
                        painter = painterResource(R.drawable.ic_woof_logo),
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.displayLarge
                    )
                }
            },
            modifier = modifier
        )
    }
    
    /**
     * Composable that displays a list item containing a dog icon and their information
     *
     * @param dog contains the data that populates the list item
     * @param modifier modifiers to set to this composable
     */
    @Composable
    fun DogItem(
        dog: Dog,
        modifier: Modifier = Modifier
    ) {
        Card(modifier = Modifier) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                DogIcon(dog.imageResourceId)
                DogInformation(dog.name, dog.age)
            }
        }
    }

    /**
     * Composable that displays a photo of a dog.
     *
     * @param dogIcon is the resource ID for the image of the dog
     * @param modifier modifiers to set to this composable
     */
    @Composable
    fun DogIcon(
        @DrawableRes dogIcon: Int,
        modifier: Modifier = Modifier
    ) {
        Image(
            modifier = modifier
                .size(dimensionResource(R.dimen.image_size))
                .padding(dimensionResource(R.dimen.padding_small)),
            painter = painterResource(dogIcon),

            // Content Description is not needed here - image is decorative, and setting a null content
            // description allows accessibility services to skip this element during navigation.

            contentDescription = null
        )
    }

    /**
     * Composable that displays a dog's name and age.
     *
     * @param dogName is the resource ID for the string of the dog's name
     * @param dogAge is the Int that represents the dog's age
     * @param modifier modifiers to set to this composable
     */
    @Composable
    fun DogInformation(
        @StringRes dogName: Int,
        dogAge: Int,
        modifier: Modifier = Modifier
    ) {
        Column(modifier = modifier) {
            Text(
                text = stringResource(dogName),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
            )
            Text(
                text = stringResource(R.string.years_old, dogAge),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    /**
     * Composable that displays what the UI of the app looks like in light theme in the design tab.
     */
    @Preview
    @Composable
    fun WoofPreview() {
        WoofTheme(darkTheme = false) {
            WoofApp()
        }
    }


}
