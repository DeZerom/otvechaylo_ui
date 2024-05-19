package features.contexts.data.sources

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import features.contexts.data.mapper.toDto
import features.contexts.data.model.ContextRichDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.dezerom.Db

class ContextDbSource(private val db: Db) {

    suspend fun saveContext(context: ContextRichDto) = withContext(Dispatchers.IO) {
        if (context.id == null) return@withContext

        db.contextQueries.insert(
            id = context.id,
            name = context.name ?: "",
            description = context.description,
            context = context.context,
            hash = context.hash
        )
    }

    suspend fun getAll(): List<ContextRichDto> {
        return db.contextQueries
            .getAll()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .first()
            .map { it.toDto() }
    }

    suspend fun getById(id: String): ContextRichDto {
        return db.contextQueries
            .getById(id)
            .asFlow()
            .mapToOne(Dispatchers.IO)
            .first()
            .toDto()
    }
}
