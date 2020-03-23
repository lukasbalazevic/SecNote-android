package app.vut.secnote.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.vut.secnote.data.model.room.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<Category>)

    @Query("SELECT * FROM category")
    fun geCategories(): Flow<List<Category>>
}
