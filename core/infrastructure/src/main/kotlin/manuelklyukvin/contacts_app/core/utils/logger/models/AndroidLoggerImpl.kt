package manuelklyukvin.contacts_app.core.utils.logger.models

import android.util.Log

class AndroidLoggerImpl : Logger() {
    override fun debug(message: String?, throwable: Throwable?) {
        Log.d(TAG, message, throwable)
    }

    override fun error(message: String?, throwable: Throwable?) {
        Log.e(TAG, message, throwable)
    }

    private companion object {
        const val TAG = "Contacts App"
    }
}