package features.contexts.data.mapper

import features.contexts.data.model.ContextRichDto
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.model.ContextSource
import ru.dezerom.Context

fun Context.toDto() = ContextRichDto(
    id = id,
    name = name,
    description = description,
    context = context,
    hash = hash,
)

fun ContextRichDto.toLightweight(source: ContextSource, hasConflict: Boolean) = ContextLightweight(
    id = id ?: "",
    name = name ?: "",
    description = description ?: "",
    source = source,
    hasConflict = hasConflict
)
