package fr.cpe.pokemon_geo.ui.screen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.profile.Profile
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: PokemonGeoRepository,
): ViewModel() {

    private val _profileLiveData = MutableLiveData<Profile?>()
    val profileLiveData: LiveData<Profile?> = _profileLiveData

    init {
        fetchProfile()
    }

    private fun fetchProfile() {
        viewModelScope.launch {
            val profile = repository.getProfile()
            if (profile == null) {
                val newProfile = createDefaultProfile()
                _profileLiveData.value = newProfile
            } else {
                _profileLiveData.value = profile
            }
        }
    }

    private fun createDefaultProfile(): Profile {
        val newProfile = Profile(
            id = "louis-charnay",
            pseudo = "Dark Louis",
        )
        viewModelScope.launch {
            repository.insertProfile(newProfile)
        }
        return newProfile
    }

}