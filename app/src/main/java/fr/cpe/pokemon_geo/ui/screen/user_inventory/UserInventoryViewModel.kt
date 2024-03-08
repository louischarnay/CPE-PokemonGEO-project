package fr.cpe.pokemon_geo.ui.screen.user_inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.cpe.pokemon_geo.database.PokemonGeoRepository
import fr.cpe.pokemon_geo.model.inventory_item.InventoryItem
import fr.cpe.pokemon_geo.model.inventory_item.SearchInventoryItemType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserInventoryViewModel @Inject constructor(
    private val repository: PokemonGeoRepository
): ViewModel() {

    private val _userInventory = MutableStateFlow<List<InventoryItem>>(mutableListOf())
    val userInventory: StateFlow<List<InventoryItem>> = _userInventory

    init {
        fetchUserInventory()
    }

    private fun fetchUserInventory() {
        viewModelScope.launch {
            val inventoryList = mutableListOf<InventoryItem>()
            val userInventory = repository.getUserInventory()

            userInventory.forEach {
                val inventoryItemType = SearchInventoryItemType.byName(it.type)
                inventoryList.add(InventoryItem(inventoryItemType, it.quantity))
            }

            withContext(Dispatchers.Main) {
                _userInventory.value = inventoryList
            }
        }
    }
}