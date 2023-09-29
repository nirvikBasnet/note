package com.elitecodecamp.noteapp.data

class NotesDataSource{
    fun loadNotes (): List<Note>{
        return listOf(
            Note(title = "A busy day in office", description = "Too many tickets"),
            Note(title = "Android Compose", description = "Learning jetpack compose")
        )
    }

}