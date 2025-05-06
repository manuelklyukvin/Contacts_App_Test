package manuelklyukvin.contacts_app.core.utils.logger.models

abstract class Logger {
    abstract fun debug(message: String?, throwable: Throwable? = null)
    abstract fun error(message: String?, throwable: Throwable? = null)
}