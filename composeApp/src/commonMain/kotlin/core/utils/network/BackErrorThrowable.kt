package core.utils.network

import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
class BackErrorThrowable(
    override val message: String?
): Throwable()