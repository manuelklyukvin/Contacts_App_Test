package manuelklyukvin.contacts_app.main.phone.use_cases

import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult

class FormatPhoneNumberUseCase {
    operator fun invoke(rawPhoneNumber: String): OperationResult<String, String?> {
        val digits = rawPhoneNumber.filter { it.isDigit() }

        val (countryCode, nationalPhoneNumber) = when {
            digits.length == 10 && !digits.startsWith("8") && !digits.startsWith("7") -> "+1" to digits
            digits.length == 11 && digits.startsWith("8") -> "+7" to digits.drop(1)
            digits.length == 11 && digits.startsWith("7") -> "+7" to digits.drop(1)
            digits.length in 11..13 -> {
                val countryCodeLength = digits.length - 10
                val countryCode = digits.substring(0, countryCodeLength)
                val nationalPhoneNumber = digits.substring(countryCodeLength)

                if (nationalPhoneNumber.length != 10) return OperationResult.Error("Invalid national phone number length")

                "+$countryCode" to nationalPhoneNumber
            }
            digits.isNotBlank() -> return OperationResult.Success(rawPhoneNumber)
            else -> return OperationResult.Error("Invalid phone number")
        }

        val areaCode = nationalPhoneNumber.substring(0, 3)
        val part1 = nationalPhoneNumber.substring(3, 6)
        val part2 = nationalPhoneNumber.substring(6, 8)
        val part3 = nationalPhoneNumber.substring(8, 10)

        val formatted = "$countryCode ($areaCode) $part1-$part2-$part3"
        return OperationResult.Success(formatted)
    }
}