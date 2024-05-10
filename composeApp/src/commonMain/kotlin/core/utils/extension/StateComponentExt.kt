package core.utils.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import core.components.StateComponent

@Composable
fun <T> StateComponent<T>.collectAsState() = state.collectAsState()