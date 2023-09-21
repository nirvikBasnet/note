package com.elitecodecamp.noteapp.data

import com.elitecodecamp.noteapp.Note

class NotesDataSource{
    fun loadNotes (): List<Note>{
        return listOf(
            Note(title = "A busy day in office", content = "Too many tickets"),
            Note(title = "Android Compose", content = "Learning jetpack compose")
        )
    }

}