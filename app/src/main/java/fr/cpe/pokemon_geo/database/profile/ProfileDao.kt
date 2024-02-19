package fr.cpe.pokemon_geo.database.profile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {
    @Query("SELECT * FROM $PROFILE_TABLE_NAME LIMIT 1")
    fun getProfile(): Profile?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(profile: Profile)
}