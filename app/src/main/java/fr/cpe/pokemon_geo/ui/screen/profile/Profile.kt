package fr.cpe.pokemon_geo.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.cpe.pokemon_geo.R
import fr.cpe.pokemon_geo.database.profile.ProfileEntity

@Composable
fun Profile(profileViewModel: ProfileViewModel = hiltViewModel()) {
    // Create a mutable state for the profile
    val profile = remember { mutableStateOf<ProfileEntity?>(null) }

    val lifecycleOwner = LocalLifecycleOwner.current

    // Run coroutine when the profileViewModel changes
    LaunchedEffect(key1 = profileViewModel) {
        // Observe the LiveData from the ViewModel
        profileViewModel.profileLiveData.observe(lifecycleOwner) { observedProfile ->
            profile.value = observedProfile
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_account),
            contentDescription = "Profil",
            modifier = Modifier.size(150.dp)
        )
        Text(text = profile.value?.pseudo ?: "", fontSize = 30.sp)
        Text(text = "${profile.value?.experience ?: ""} EXP")
    }
}