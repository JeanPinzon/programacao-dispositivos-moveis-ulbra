package br.com.ulbra.pdm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/** Modulo 5: entidade que representa a tabela "tarefas" no banco local. */
@Entity(tableName = "tarefas")
data class Tarefa(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val concluida: Boolean = false
)
