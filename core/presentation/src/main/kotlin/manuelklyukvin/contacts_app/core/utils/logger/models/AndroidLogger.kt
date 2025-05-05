package manuelklyukvin.contacts_app.core.utils.logger.models

import android.util.Log

class AndroidLogger : Logger() {
    override fun debug(tag: String, message: String?) {
        message?.let { Log.d(tag, message) }
    }
    override fun error(tag: String, message: String?) {
        message?.let { Log.d(tag, message) }
    }
}