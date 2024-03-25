package fr.cpe.pokemon_geo.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.profile.ProfileEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: PokemonGeoRepository,
): ViewModel() {

    private val _profile = MutableStateFlow<ProfileEntity?>(null)
    val profile: StateFlow<ProfileEntity?> = _profile

    fun fetchProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val profile = repository.getProfile()

            withContext(Dispatchers.Main) {
                _profile.value = profile
            }
        }
    }
}