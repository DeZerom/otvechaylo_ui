package app.db

import app.cash.sqldelight.db.SqlDriver
import ru.dezerom.Db

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class DbDriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driver: DbDriverFactory) = Db(driver.createDriver())
