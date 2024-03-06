package fr.cpe.pokemon_geo.ui.screen.starter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.profile.ProfileEntity
import fr.cpe.pokemon_geo.database.user_pokemon.UserPokemonEntity
import fr.cpe.pokemon_geo.model.pokemon.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val repository: PokemonGeoRepository,
): ViewModel() {

    private val _showWelcomeScreen = MutableStateFlow(false)
    val showWelcomeSreen: StateFlow<Boolean> = _showWelcomeScreen

    private val _showProfileScreen = MutableStateFlow(false)
    val showProfileScreen: StateFlow<Boolean> = _showProfileScreen

    init {
        viewModelScope.launch {
            val isProfileCreated = isProfileCreated()
            val hasAtLeastOnePokemon = hasAtLeastOnePokemon()

            if (!isProfileCreated) _showProfileScreen.value = true
            if (!isProfileCreated || !hasAtLeastOnePokemon) _showWelcomeScreen.value = true
        }
    }

    fun createProfile(pseudo: String) {
        if (pseudo.trim().isEmpty()) return

        val newProfile = ProfileEntity(pseudo = pseudo)
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProfile(newProfile)
        }
        _showProfileScreen.value = false
    }

    fun chooseStarter(pokemon: Pokemon) {
        viewModelScope.launch {
            val newPokemon = UserPokemonEntity(pokemonId = pokemon.getOrder())
            repository.insertUserPokemon(newPokemon)
        }
        _showWelcomeScreen.value = false
    }

    private suspend fun isProfileCreated(): Boolean {
        return repository.getProfile() != null
    }

    private suspend fun hasAtLeastOnePokemon(): Boolean {
        return repository.getAllUserPokemon().isNotEmpty()
    }
}