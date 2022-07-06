package com.github.kuya32.vintracker.feature_client.presentation.search_client

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.core.presentation.ui.theme.extraSmallSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.largeSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.mediumSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.smallSpace
import com.github.kuya32.vintracker.feature_client.domain.models.Client

@Composable
fun ClientItem(
    client: Client,
    viewModel: SearchClientListingsViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .padding(mediumSpace)
    ) {
        ClientNameImage(
            fullName = client.fullName,
            viewModel = viewModel,
            modifier = Modifier
                .weight(1f)
        )
        Spacer(modifier = Modifier.width(mediumSpace))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(3f)
        ) {
            Text(
                text = client.fullName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(smallSpace))
            Text(
                text = stringResource(id = R.string.client_item_email, client.email),
                modifier = Modifier
                    .align(Alignment.Start),
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(extraSmallSpace))
            Text(
                text = stringResource(id = R.string.client_item_phone_number, client.phoneNumber),
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .align(Alignment.Start)
            )
        }
    }
}

@Composable
fun ClientNameImage(
    fullName: String,
    viewModel: SearchClientListingsViewModel,
    modifier: Modifier
) {
    Box(contentAlignment= Alignment.Center,
        modifier = modifier
            .background(
                color = viewModel.assignRandomColor(),
                shape = CircleShape
            )
            .layout() { measurable, constraints ->
                // Measure the composable
                val placeable = measurable.measure(constraints)

                //get the current max dimension to assign width=height
                val currentHeight = placeable.height
                var heightCircle = currentHeight
                if (placeable.width > heightCircle)
                    heightCircle = placeable.width

                //assign the dimension and the center position
                layout(heightCircle, heightCircle) {
                    // Where the composable gets placed
                    placeable.placeRelative(0, (heightCircle - currentHeight) / 2)
                }
            }
    ) {
        Text(
            text = viewModel.abbreviateClientName(fullName),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(4.dp)
                .defaultMinSize(24.dp)
        )
    }
}