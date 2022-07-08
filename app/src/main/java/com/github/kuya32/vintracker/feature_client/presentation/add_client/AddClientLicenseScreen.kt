package com.github.kuya32.vintracker.feature_client.presentation.add_client

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kuya32.vintracker.AddClientNavGraph
import com.github.kuya32.vintracker.R
import com.github.kuya32.vintracker.core.presentation.components.StandardTextField
import com.github.kuya32.vintracker.core.presentation.components.StandardToolbar
import com.github.kuya32.vintracker.core.presentation.ui.theme.extraLargeSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.extraSmallSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.largeSpace
import com.github.kuya32.vintracker.core.presentation.ui.theme.mediumSpace
import com.github.kuya32.vintracker.feature_auth.presentation.utils.AuthErrors
import com.github.kuya32.vintracker.feature_client.domain.models.Client
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterialApi::class)
@AddClientNavGraph
@Destination
@Composable
fun AddClientLicenseScreen(
    client: Client,
    navigator: DestinationsNavigator,
    focusManager: FocusManager = LocalFocusManager.current,
    viewModel: AddClientViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val bottomSheetModalState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.onEvent(event = AddClientEvent.LaunchGalleryForLicense, context = context, licenseUri = uri)
    }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        viewModel.onEvent(event = AddClientEvent.LaunchCameraForLicense, context = context, licenseBitmap = bitmap)
    }
    val permissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            if (viewModel.isCameraSelected.value) {
                cameraLauncher.launch()
            } else {
                galleryLauncher.launch("image/*")
            }
            coroutineScope.launch {
                bottomSheetModalState.hide()
            }
        } else {
            Toast.makeText(context, "Permission denied!", Toast.LENGTH_SHORT).show()
        }
    }
    val clientLicenseState = viewModel.clientLicenseState.value

    ModalBottomSheetLayout(
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.primary.copy(0.08f))
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.add_photo),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(mediumSpace),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    Text(
                        text = stringResource(id = R.string.take_photo),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(mediumSpace)
                            .clickable {
                                when (PackageManager.PERMISSION_GRANTED) {
                                    ContextCompat.checkSelfPermission(
                                        context, Manifest.permission.CAMERA
                                    ) -> {
                                        cameraLauncher.launch()
                                        coroutineScope.launch {
                                            bottomSheetModalState.hide()
                                        }
                                    }
                                    else -> {
                                        viewModel.onEvent(AddClientEvent.ChooseCamera)
                                        permissionsLauncher.launch(Manifest.permission.CAMERA)
                                    }
                                }
                            },
                        fontSize = 20.sp
                    )
                    Divider(
                        modifier = Modifier
                            .height(0.5.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    Text(
                        text = stringResource(id = R.string.choose_from_gallery),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(mediumSpace)
                            .clickable {
                                when (PackageManager.PERMISSION_GRANTED) {
                                    ContextCompat.checkSelfPermission(
                                        context, Manifest.permission.READ_EXTERNAL_STORAGE
                                    ) -> {
                                        galleryLauncher.launch("image/*")
                                        coroutineScope.launch {
                                            bottomSheetModalState.hide()
                                        }
                                    }
                                    else -> {
                                        viewModel.onEvent(AddClientEvent.ChooseFromGallery)
                                        permissionsLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                                    }
                                }
                            },
                        fontSize = 20.sp
                    )
                    Divider(
                        modifier = Modifier
                            .height(0.5.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    Text(
                        text = stringResource(id = R.string.cancel),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(mediumSpace)
                            .clickable {
                                coroutineScope.launch {
                                    bottomSheetModalState.hide()
                                }
                            },
                        fontSize = 20.sp
                    )
                }
            }
        },
        sheetState = bottomSheetModalState,
        sheetShape = RoundedCornerShape(topStart = largeSpace, topEnd = largeSpace),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                StandardToolbar(
                    navigator = navigator,
                    showBackArrow = true,
                    title = {
                        Text(
                            text = stringResource(id = R.string.client_license),
                        )
                    }
                )
                Spacer(modifier = Modifier.height(largeSpace))
                StandardTextField(
                    text = clientLicenseState.text,
                    onValueChange = {
                        viewModel.onEvent(AddClientEvent.EnteredClientDriversLicense(it))
                    },
                    label = stringResource(id = R.string.drivers_license),
                    hint = "",
                    leadingIcon = Icons.Default.Description,
                    error = when (clientLicenseState.error) {
                        is AuthErrors.FieldEmpty -> {
                            stringResource(id = R.string.license_required)
                        }
                        else -> ""
                    },
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = largeSpace)
                )
                Spacer(modifier = Modifier.height(extraLargeSpace))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = largeSpace)
                        .height(350.dp)
                        .border(1.dp, Color.White, MaterialTheme.shapes.medium)
                        .clickable {
                            coroutineScope.launch {
                                if (!bottomSheetModalState.isVisible) {
                                    bottomSheetModalState.show()
                                } else {
                                    bottomSheetModalState.hide()
                                }
                            }
                        }
                ) {
                    if (viewModel.isPhotoSelected.value) {
                        viewModel.licenseBitmap.value?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = stringResource(id = R.string.license_photo),
                                alignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Fit
                            )
                        }
                    } else {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.add_photo),
                            tint = Color.White
                        )
                    }
                }
            }
            Button(
                onClick = {
                      viewModel.onEvent(
                          event = AddClientEvent.AddClient,
                          client = Client(
                              fullName = client.fullName,
                              email = client.email,
                              phoneNumber = client.phoneNumber,
                              dateOfBirth = client.dateOfBirth,
                              address = client.address
                          )
                      )
                },
                border = BorderStroke(
                    2.dp,
                    Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(start = largeSpace, end = largeSpace, bottom = largeSpace)
            ) {
                Text(
                    text = stringResource(id = R.string.add_client),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(extraSmallSpace))
                Icon(
                    imageVector = Icons.Default.PersonAdd,
                    contentDescription = stringResource(id = R.string.add_client),
                    modifier = Modifier
                        .size(16.dp)
                )
            }
        }
    }
}