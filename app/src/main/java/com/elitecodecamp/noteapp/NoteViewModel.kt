package com.elitecodecamp.noteapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.elitecodecamp.noteapp.data.Note
import com.elitecodecamp.noteapp.data.NotesDataSource

class NoteViewModel : ViewModel() {

    private var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note){
        noteList.add(note)
    }

    fun removeNote(note: Note){
        noteList.remove(note)
    }

    fun getAllNotes(): List<Note>{
        return noteList
    }


}