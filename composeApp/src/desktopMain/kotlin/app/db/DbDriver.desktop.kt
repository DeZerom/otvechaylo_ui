package app.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import ru.dezerom.Db

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DbDriverFactory {
    actual fun createDriver(): SqlDriver {
        return JdbcSqliteDriver(url = "jdbc:sqlite:Db.db", schema = Db.Schema)
    }
}