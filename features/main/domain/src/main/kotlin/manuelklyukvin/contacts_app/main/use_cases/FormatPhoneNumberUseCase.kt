package manuelklyukvin.contacts_app.main.use_cases

class FormatPhoneNumberUseCase {
    operator fun invoke(rawPhoneNumber: String): String? {
        val digits = rawPhoneNumber.filter { it.isDigit() }

        if (digits.length < 10) return null

        val (countryCode, nationalPhoneNumber) = when {
            digits.length == 10 -> "+1" to digits
            digits.length == 11 && digits.startsWith("8") -> "+7" to digits.drop(1)
            digits.length in 11..13 -> {
                val codeLength = digits.length - 10
                "+${digits.substring(0, codeLength)}" to digits.substring(codeLength)
            }
            else -> return null
        }

        if (nationalPhoneNumber.length != 10) return null

        val areaCode = nationalPhoneNumber.substring(0, 3)
        val part1 = nationalPhoneNumber.substring(3, 6)
        val part2 = nationalPhoneNumber.substring(6, 8)
        val part3 = nationalPhoneNumber.substring(8, 10)

        return "$countryCode ($areaCode) $part1-$part2-$part3"
    }
}