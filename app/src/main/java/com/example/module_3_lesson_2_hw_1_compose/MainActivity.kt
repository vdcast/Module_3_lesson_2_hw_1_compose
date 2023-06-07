package com.example.module_3_lesson_2_hw_1_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.module_3_lesson_2_hw_1_compose.ui.theme.Module_3_Lesson_2_hw_1_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Module_3_Lesson_2_hw_1_ComposeTheme {

                MainScreen()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){

    val prefs = SharedPrefs(LocalContext.current)

    val language = prefs.getLanguage()
    val languageSwitch = prefs.getLanguageSwitch()
    val username = prefs.getUsername()
    val graphics = prefs.getGraphics()
    val graphicsSwitch = prefs.getGraphicsSwitch()
    val autosave = prefs.getAutosave()
    val autosaveSwitch = prefs.getAutosaveSwitch()

    val (isCheckedLanguage, setCheckedLanguage) = remember { mutableStateOf(languageSwitch) }
    val languageState = remember { mutableStateOf(language) }
    val usernameState = remember { mutableStateOf(username) }
    val (isCheckedGraphics, setCheckedGraphics) = remember { mutableStateOf(graphicsSwitch) }
    val graphicsState = remember { mutableStateOf(graphics) }
    val (isCheckedAutosave, setCheckedAutosave) = remember { mutableStateOf(autosaveSwitch) }
    val autosaveState = remember { mutableStateOf(autosave) }

    val isEditingUsername = remember { mutableStateOf(false) }
    val editButtonText = remember { mutableStateOf("Edit") }

//    val textFieldUsernameState = remember { mutableStateOf("kek") }


    val focusManager = LocalFocusManager.current


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Text(
                    text = "Language:",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = languageState.value,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Switch(
                    checked = isCheckedLanguage,
                    onCheckedChange = { newValue ->
                        setCheckedLanguage(newValue)
                        if (newValue) {
                            prefs.setLanguage("UKR")
                            languageState.value = prefs.getLanguage()
                            prefs.setLanguageSwitch(true)
                        } else {
                            prefs.setLanguage("ENG")
                            languageState.value = prefs.getLanguage()
                            prefs.setLanguageSwitch(false)
                        }
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Text(
                    text = "Username:",
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                if (isEditingUsername.value){
                    TextField(
                        value = usernameState.value,
                        onValueChange = {newValue ->
                            usernameState.value = newValue
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        ),
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp)
                            .fillMaxWidth()
                            .weight(4f),
                        textStyle = TextStyle(fontSize = 16.sp)
                    )
                } else {
                    Text(
                        text = usernameState.value,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }


                Button(
                    onClick = {
                        isEditingUsername.value = !isEditingUsername.value
                        editButtonText.value = if (isEditingUsername.value) "Save" else "Edit"
                        prefs.setUsername(usernameState.value)
                    }
                ) {
                    Text(text = editButtonText.value)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Text(
                    text = "Graphics mode:",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = graphicsState.value,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Switch(
                    checked = isCheckedGraphics,
                    onCheckedChange = { newValue ->
                        setCheckedGraphics(newValue)
                        if (newValue) {
                            prefs.setGraphics("High")
                            graphicsState.value = prefs.getGraphics()
                            prefs.setGraphicsSwitch(true)
                        } else {
                            prefs.setGraphics("Low")
                            graphicsState.value = prefs.getGraphics()
                            prefs.setGraphicsSwitch(false)
                        }
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Text(
                    text = "Auto-save:",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = autosaveState.value,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Switch(
                    checked = isCheckedAutosave,
                    onCheckedChange = { newValue ->
                        setCheckedAutosave(newValue)
                        if (newValue) {
                            prefs.setAutosave("On")
                            autosaveState.value = prefs.getAutosave()
                            prefs.setAutosaveSwitch(true)
                        } else {
                            prefs.setAutosave("Off")
                            autosaveState.value = prefs.getAutosave()
                            prefs.setAutosaveSwitch(false)
                        }
                    }
                )
            }
        }
    }
}
