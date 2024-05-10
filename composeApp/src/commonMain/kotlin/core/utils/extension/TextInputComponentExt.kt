package core.utils.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import core.components.TextInputComponent

@Composable
fun TextInputComponent.collectTextAsState() = value.collectAsState()
