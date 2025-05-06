package manuelklyukvin.contacts_app.core.utils.logger.models

import android.util.Log

class AndroidLogger : Logger() {
    override fun debug(
        tag: String,
        message: String?,
        throwable: Throwable?
    ) {
        Log.d(tag, message, throwable)
    }

    override fun error(
        tag: String,
        message: String?,
        throwable: Throwable?
    ) {
        Log.e(tag, message, throwable)
    }
}