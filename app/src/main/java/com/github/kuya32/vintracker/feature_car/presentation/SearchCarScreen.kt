package com.github.kuya32.vintracker.feature_car.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.core.presentation.navigation.AppNavGraph
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.destinations.CarListingsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@AppNavGraph(start = true)
@Destination
@Composable
fun SearchCarScreen(
    navigator: DestinationsNavigator,
    viewModel: CarViewModel = hiltViewModel()
) {
    var radioState by remember {
        mutableStateOf(true)
    }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .selectableGroup()
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = radioState,
                    onClick = { radioState = true }
                )
                Text(
                    text = stringResource(id = R.string.make),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = !radioState,
                    onClick = { radioState = false }
                )
                Text(
                    text = stringResource(id = R.string.model),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
        OutlinedTextField(
            value = "",
            onValueChange = {

            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                }
            ),
            maxLines = 1,
            singleLine = true
        )
        Button(
            onClick = { navigator.navigate(CarListingsScreenDestination) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
        ) {
            Text(text = stringResource(id = R.string.search))
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Black),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(20) {
                Image(
                    painter = painterResource(id = R.drawable.ic_vintracker_logo),
                    contentDescription = null
                )
            }
        }
    }
}