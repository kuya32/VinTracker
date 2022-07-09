package com.github.kuya32.vintracker.feature_client.presentation.search_client

import android.widget.Space
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.SearchClientNavGraph
import com.github.kuya32.vintracker.core.presentation.components.StandardToolbar
import com.github.kuya32.vintracker.core.presentation.ui.theme.largeSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.mediumSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.smallSpace
import com.github.kuya32.vintracker.feature_client.domain.models.Client
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SearchClientNavGraph
@Destination
@Composable
fun SearchClientInfoScreen(
    client: Client,
    navigator: DestinationsNavigator,
    viewModel: SearchClientListingsViewModel = hiltViewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        StandardToolbar(
            navigator = navigator,
            showBackArrow = true,
            title = {
                Text(
                    text = stringResource(id = R.string.client_details, "${client.fullName.substring(0, client.fullName.indexOf(" "))}'s")
                )
            }
        )
        Spacer(modifier = Modifier.height(mediumSpace))
        Image(
            painter = painterResource(id = R.drawable.ic_profile_image_placeholder),
            contentDescription = stringResource(id = R.string.profile_image),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .weight(1.75f)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(smallSpace))
        Text(
            text = client.fullName,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(.30f)
        )
        Spacer(modifier = Modifier.height(mediumSpace))
        Box(
            modifier = Modifier
                .padding(start = mediumSpace, end = mediumSpace, bottom = mediumSpace)
                .weight(3f)
                .clip(RoundedCornerShape(largeSpace))
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(mediumSpace)
            ) {
                Text(text = stringResource(id = R.string.email_detail, client.email))
                Spacer(modifier = Modifier.height(smallSpace))
                Text(text = stringResource(id = R.string.phone_detail, client.phoneNumber))
                Spacer(modifier = Modifier.height(smallSpace))
                Text(text = stringResource(id = R.string.dob_detail, client.dateOfBirth))
                Spacer(modifier = Modifier.height(smallSpace))
                Text(
                    text = stringResource(id = R.string.address_detail, client.address),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(smallSpace))
                Text(text = stringResource(id = R.string.license_detail, client.license))
                Spacer(modifier = Modifier.height(smallSpace))
                Text(text = stringResource(id = R.string.notes_detail, client.notes))
            }
        }

    }
}
