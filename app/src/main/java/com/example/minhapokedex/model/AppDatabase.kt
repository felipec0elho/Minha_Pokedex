import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.minhapokedex.model.FavCard
import com.example.minhapokedex.model.FavCardDAO

@Database(entities = [FavCard::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favCardDAO(): FavCardDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "favorite_card_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}