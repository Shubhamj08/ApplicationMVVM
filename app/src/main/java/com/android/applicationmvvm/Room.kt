package com.android.applicationmvvm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PetDao {
    @Query("select * from databasepets")
    fun getPets(): LiveData<List<DatabasePets>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pets: List<DatabasePets>)
}

@Database(entities = [DatabasePets::class], version = 1)
abstract class PetDatabase : RoomDatabase() {
    abstract val petDao: PetDao
}

private lateinit var INSTANCE: PetDatabase

fun getDataBase(context: Context): PetDatabase {
    synchronized(PetDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                PetDatabase::class.java, "pets"
            ).build()
        }
    }
    return INSTANCE
}
