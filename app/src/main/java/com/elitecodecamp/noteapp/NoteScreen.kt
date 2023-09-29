package com.elitecodecamp.noteapp

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elitecodecamp.noteapp.data.Note
import com.elitecodecamp.noteapp.data.NotesDataSource
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) ->Unit
){

    var title by remember {
        mutableStateOf("")
    }
    var content by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {

        CenterAlignedTopAppBar(
            title = {
                    Text(text = "Note")
            },
            actions = {
                      Icon(imageVector = Icons.Rounded.Notifications,
                      contentDescription = "Icon"
                          )
            }

        )

        // Content

        Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp
                ),
                text = title,
                label = "Title",
                onTextChanged = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                }
            )
            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp
                ),
                text = content,
                label = "Add a note",
                onTextChanged = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) content = it
                }
            )

            NoteButton(text ="Save",
            onClick = {
                if(title.isNotEmpty()&&content.isNotEmpty()){
                    //save/add to the list
                    onAddNote(Note(title = title, description = content))
                    title = ""
                    content = ""

                    Toast.makeText(context,"Note Added!",Toast.LENGTH_LONG).show()
                }
            })

        }

        Divider(modifier = Modifier.padding(10.dp))

        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note, onNoteClicked = {
                    onRemoveNote(note)
                })
            }
        }



    }

}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked:(Note) -> Unit
){
    Surface(
        modifier = Modifier.padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        shadowElevation = 6.dp
    ){

        Column (modifier.clickable {
            onNoteClicked(note)
        }.padding(
            horizontal = 14.dp,
            vertical = 6.dp,
        ),
        horizontalAlignment = Alignment.Start
            ){

            Text(text = note.title, style = MaterialTheme.typography.titleSmall)
            Text(text = note.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = note.entryDate
                .format(DateTimeFormatter.ofPattern("EEE, d MMM"))
                , style = MaterialTheme.typography.bodySmall)

        }

    }

}



@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}