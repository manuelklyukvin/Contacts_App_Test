package manuelklyukvin.contacts_app.core.utils.logger.models

abstract class Logger {
    abstract fun debug(
        tag: String = TAG,
        message: String?,
        throwable: Throwable? = null
    )

    abstract fun error(
        tag: String = TAG,
        message: String?,
        throwable: Throwable? = null
    )

    private companion object {
        const val TAG = "Contacts App"
    }
}