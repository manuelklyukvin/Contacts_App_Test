package manuelklyukvin.contacts_app.main.use_cases

import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult

class FormatPhoneNumberUseCase {
    operator fun invoke(rawPhoneNumber: String): OperationResult<String, String?> {
        val digits = rawPhoneNumber.filter { it.isDigit() }

        if (digits.length < 10) return OperationResult.Error("Phone number must have minimum 10 digits")

        val (countryCode, nationalPhoneNumber) = when {
            digits.length == 10 && !digits.startsWith("8") && !digits.startsWith("7") -> "+1" to digits
            digits.length == 11 && digits.startsWith("8") -> "+7" to digits.drop(1)
            digits.length == 11 && digits.startsWith("7") -> "+7" to digits.drop(1)
            digits.length in 11..13 -> {
                val codeLength = digits.length - 10
                val code = digits.substring(0, codeLength)
                val nationalPhoneNumber = digits.substring(codeLength)

                if (nationalPhoneNumber.length != 10) return OperationResult.Error("Invalid national phone number length")

                "+$code" to nationalPhoneNumber
            }
            else -> return OperationResult.Error("Invalid phone number format")
        }

        val areaCode = nationalPhoneNumber.substring(0, 3)
        val part1 = nationalPhoneNumber.substring(3, 6)
        val part2 = nationalPhoneNumber.substring(6, 8)
        val part3 = nationalPhoneNumber.substring(8, 10)

        val formatted = "$countryCode ($areaCode) $part1-$part2-$part3"
        return OperationResult.Success(formatted)
    }
}