package com.github.kuya32.vintracker.feature_car.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.AppNavGraph
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.core.presentation.components.StandardToolbar
import com.github.kuya32.vintracker.core.presentation.ui.theme.extraSmallSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.largeSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.mediumSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.smallSpace
import com.github.kuya32.vintracker.feature_car.domain.models.Car
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@AppNavGraph
@Destination
@Composable
fun CarDetailScreen(
    car: Car = Car(
        year = "1990",
        make = "Subaru",
        model = "Crosstrek"
    ),
    navigator: DestinationsNavigator,
    viewModel: CarViewModel = hiltViewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        StandardToolbar(
            navigator = navigator,
            showBackArrow = true,
            title = {
                Text(text = stringResource(id = R.string.car_details, car.model))
            }
        )
        Spacer(modifier = Modifier.height(mediumSpace))
        Image(
            painter = painterResource(id = R.drawable.ic_car),
            contentDescription = stringResource(id = R.string.car)
        )
        Spacer(modifier = Modifier.height(largeSpace))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumSpace)
        ) {
            CarSpecsColumnItem(
                title = stringResource(id = R.string.year),
                carSpec = car.year
            )
            CarSpecsColumnItem(
                title = stringResource(id = R.string.make),
                carSpec = car.make
            )
            CarSpecsColumnItem(
                title = stringResource(id = R.string.model),
                carSpec = car.model
            )
        }
        Spacer(modifier = Modifier.height(mediumSpace))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .background(Color.White)
                .wrapContentHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = largeSpace,
                        start = mediumSpace,
                        end = mediumSpace,
                        bottom = mediumSpace
                    )
            ) {
                CarSpecsRowItem(
                    icon = Icons.Default.Palette,
                    title = stringResource(id = R.string.color),
                    carSpec = car.color,
                    isDivided = true
                )
                Spacer(modifier = Modifier.height(smallSpace))
                CarSpecsRowItem(
                    icon = Icons.Default.AttachMoney,
                    title = stringResource(id = R.string.price),
                    carSpec = car.price,
                    isDivided = true
                )
                Spacer(modifier = Modifier.height(smallSpace))
                CarSpecsRowItem(
                    icon = Icons.Default.CheckBox,
                    title = stringResource(id = R.string.status),
                    isStatus = true
                )
            }
            Button(
                onClick = {  },
                enabled = viewModel.isAvailable.value,
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .fillMaxWidth()
                    .padding(start = mediumSpace, end = mediumSpace, bottom = largeSpace)
                    .align(Alignment.BottomCenter)
            ) {
                Text(text = stringResource(id = R.string.locate))
            }
        }
    }
}

@Composable
fun CarSpecsColumnItem(
    title: String,
    carSpec: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = Color.LightGray,
            fontSize = 18.sp,
            textDecoration = TextDecoration.Underline,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(smallSpace))
        Text(
            text = carSpec,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CarSpecsRowItem(
    icon: ImageVector,
    title: String,
    carSpec: String? = null,
    isStatus: Boolean = false,
    isDivided: Boolean = false,
    viewModel: CarViewModel = hiltViewModel()
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row {
            Icon(
                imageVector = icon,
                contentDescription = "Icon",
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(smallSpace))
            Text(text = title, fontSize = 24.sp)
        }
        if (isStatus) {
            if (viewModel.isAvailable.value) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(32.dp))
                        .background(color = Color.Green)
                        .padding(horizontal = mediumSpace, vertical = smallSpace)
                ) {
                    Text(text = stringResource(id = R.string.available))
                }
            } else {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(32.dp))
                        .background(color = Color.Red)
                        .padding(horizontal = mediumSpace, vertical = smallSpace)
                ) {
                    Text(text = stringResource(id = R.string.unavailable))
                }
            }
        } else {
            if (carSpec != null) {
                Text(text = carSpec, fontSize = 24.sp)
            }
        }
    }
    if (isDivided) {
        Divider(modifier = Modifier.padding(top = extraSmallSpace))
    }
}