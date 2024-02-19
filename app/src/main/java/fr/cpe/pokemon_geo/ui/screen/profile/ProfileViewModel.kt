package fr.cpe.pokemon_geo.ui.screen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.profile.ProfileEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: PokemonGeoRepository,
): ViewModel() {

    private val _profileLiveData = MutableLiveData<ProfileEntity?>()
    val profileLiveData: LiveData<ProfileEntity?> = _profileLiveData

    init {
        createDefaultProfile()
        fetchProfile()
    }

    private fun fetchProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val profile = repository.getProfile()

            withContext(Dispatchers.Main) {
                _profileLiveData.value = profile
            }
        }
    }

    private fun createDefaultProfile() {
        val newProfile = ProfileEntity(pseudo = "Dresseur")
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProfile(newProfile)
        }
    }

}