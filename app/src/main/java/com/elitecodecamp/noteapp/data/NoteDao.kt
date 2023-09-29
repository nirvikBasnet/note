package com.elitecodecamp.noteapp.data

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("Select * from notes_tbl")
    fun getAllNotes() : Flow<List<Note>>

    @Query("Select * from notes_tbl where id = :id")
    fun getNoteById(id : String) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note : Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(note : Note)

    @Query("Delete from notes_tbl")
    fun deleteAll()

    @Delete
    fun deleteNote(note : Note)

}
