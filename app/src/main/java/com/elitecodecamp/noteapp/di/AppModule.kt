package com.elitecodecamp.noteapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elitecodecamp.noteapp.data.NoteDao
import com.elitecodecamp.noteapp.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context) : NoteDatabase{
        return Room.databaseBuilder(context,
            NoteDatabase::class.java,
            "notes_db").fallbackToDestructiveMigration()
            .build()

    }
    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: NoteDatabase) : NoteDao
    = noteDatabase.noteDao()


}