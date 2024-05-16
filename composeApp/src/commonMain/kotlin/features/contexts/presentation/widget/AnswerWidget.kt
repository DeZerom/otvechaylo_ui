package features.contexts.presentation.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import core.const.ColorConst
import core.const.ShapeConst
import core.const.SizeConst
import core.const.TextConst
import core.widgets.loader.Loader
import features.contexts.presentation.component.AnswerComponent
import features.contexts.presentation.model.AnswerState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.answer
import otvechayloui.composeapp.generated.resources.ask_question
import otvechayloui.composeapp.generated.resources.no_answer

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AnswerWidget(
    component: AnswerComponent,
    modifier: Modifier = Modifier
) {
    Card(
        shape = ShapeConst.DEFAULT_SHAPE,
        backgroundColor = ColorConst.Background.SECONDARY,
        modifier = Modifier
            .heightIn(min = SizeConst.Elements.MIN_ANSWER_HEIGHT)
            .then(modifier)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(SizeConst.Padding.S),
            modifier = Modifier.padding(SizeConst.Padding.S)
        ) {
            Text(
                text = stringResource(Res.string.answer),
                style = TextConst.ST.copy(color = ColorConst.Colors.ACCENT)
            )
            Content(state = component.state.collectAsState().value)
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Content(
    state: AnswerState
) {
    when (state) {
        is AnswerState.Answer ->
            Text(
                text = state.answer,
                style = TextConst.MT
            )
        is AnswerState.Error ->
            Text(
                text = state.message,
                style = TextConst.MT.copy(color = ColorConst.Text.ERROR)
            )
        AnswerState.Initial ->
            Text(
                text = stringResource(Res.string.ask_question),
                style = TextConst.MT.copy(color = ColorConst.Text.SECONDARY)
            )
        AnswerState.NotFound ->
            Text(
                text = stringResource(Res.string.no_answer),
                style = TextConst.MT.copy(color = ColorConst.Colors.BLUE)
            )
        AnswerState.Loading ->
            Loader(
                modifier = Modifier.fillMaxSize()
            )
    }
}
