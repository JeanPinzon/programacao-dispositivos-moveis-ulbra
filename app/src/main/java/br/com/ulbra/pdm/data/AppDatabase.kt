package br.com.ulbra.pdm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/** Modulo 5: banco de dados Room, com instancia unica (singleton). */
@Database(entities = [Tarefa::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tarefaDao(): TarefaDao

    companion object {
        @Volatile
        private var instancia: AppDatabase? = null

        fun obter(contexto: Context): AppDatabase =
            instancia ?: synchronized(this) {
                Room.databaseBuilder(
                    contexto.applicationContext,
                    AppDatabase::class.java,
                    "app.db"
                ).build().also { instancia = it }
            }
    }
}
