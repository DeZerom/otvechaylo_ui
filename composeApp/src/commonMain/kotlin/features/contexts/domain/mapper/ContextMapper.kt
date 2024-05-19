package features.contexts.domain.mapper

import features.contexts.data.model.ContextLightweightDto
import features.contexts.data.model.ContextRichDto
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.model.ContextRich
import features.contexts.domain.model.ContextSource

fun ContextLightweightDto.toDomain(
    source: ContextSource,
    hasConflict: Boolean
) = ContextLightweight(
    id = id ?: "",
    name = name ?: "",
    description = description ?: "",
    source = source,
    hasConflict = hasConflict
)

fun ContextRichDto.toDomain(
    source: ContextSource
) = ContextRich(
    id = id ?: "",
    name = name ?: "",
    description = description ?: "",
    hash = hash ?: "",
    context = context ?: "",
    source = source
)
