package fr.cpe.pokemon_geo.ui.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.database.profile.ProfileEntity
import fr.cpe.pokemon_geo.database.user_inventory.UserInventoryEntity
import fr.cpe.pokemon_geo.model.inventory_item.INVENTORY_ITEM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val repository: PokemonGeoRepository,
): ViewModel() {

    fun createProfile(pseudo: String): Boolean {
        if (pseudo.trim().isEmpty()) return false

        val newProfile = ProfileEntity(pseudo = pseudo)
        val newUserInventory = UserInventoryEntity(type = INVENTORY_ITEM.pokeball.name, quantity = 50)
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProfile(newProfile)
            repository.insertUserInventory(newUserInventory)
        }
        return true
    }
}