package manuelklyukvin.contacts_app.core.utils.logger.models

abstract class Logger {
    abstract fun debug(tag: String, message: String?)
    abstract fun error(tag: String, message: String?)
}