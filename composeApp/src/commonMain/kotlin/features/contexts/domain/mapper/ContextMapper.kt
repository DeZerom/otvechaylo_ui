package features.contexts.domain.mapper

import features.contexts.data.model.ContextLightweightDto
import features.contexts.domain.model.ContextLightweight
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
