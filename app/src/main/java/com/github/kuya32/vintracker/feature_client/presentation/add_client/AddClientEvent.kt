package com.github.kuya32.vintracker.feature_client.presentation.add_client

sealed class AddClientEvent {
    data class EnteredClientFirstName(val value: String): AddClientEvent()
    data class EnteredClientLastName(val value: String): AddClientEvent()
    data class EnteredClientPhoneNumber(val value: String): AddClientEvent()
    data class EnteredClientEmail(val value: String): AddClientEvent()
    data class EnteredClientOptionalAddress(val value: String): AddClientEvent()
    data class EnteredClientDriversLicense(val value: String): AddClientEvent()
    data class EnteredClientNotes(val value: String): AddClientEvent()
    object ClientAddressChosen: AddClientEvent()
    object StateDropdownClicked: AddClientEvent()
    object ChooseClientState: AddClientEvent()
    object DateOfBirthClicked: AddClientEvent()
    object LaunchCameraForLicense: AddClientEvent()
    object LaunchGalleryForLicense: AddClientEvent()
    object ChooseCamera: AddClientEvent()
    object ChooseFromGallery: AddClientEvent()
    object AddClient: AddClientEvent()
}