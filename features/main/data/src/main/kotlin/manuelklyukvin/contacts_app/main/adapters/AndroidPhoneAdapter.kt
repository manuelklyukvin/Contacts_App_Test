package manuelklyukvin.contacts_app.main.adapters

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

class AndroidPhoneAdapter(private val context: Context) : PhoneAdapter {
    override fun makeCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = "tel:$phoneNumber".toUri()
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}