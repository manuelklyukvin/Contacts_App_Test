package manuelklyukvin.contacts_app.main.use_cases

import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FormatPhoneNumberUseCaseTest {
    private val formatPhoneNumberUseCase = FormatPhoneNumberUseCase()

    @Test
    fun `should format US phone number with no code`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("1234567890")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+1 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format US phone number with code`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("11234567890")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+1 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format US phone number with plus`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("+11234567890")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+1 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format spaced US phone number with no code`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("123 456 78 90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+1 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format spaced US phone number with code`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("1 123 456 78 90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+1 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format spaced US phone number with plus`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("+1 123 456 78 90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+1 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format formated US phone number with no code`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("(123) 456-78-90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+1 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format formated US phone number with code`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("1 (123) 456-78-90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+1 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format formated US phone number with plus`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("+1 (123) 456-78-90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+1 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format bad formated US phone number with plus`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("1 1 23 45678-90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+1 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format RU phone number starting with 8`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("81234567890")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+7 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format RU phone number starting with 7`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("71234567890")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+7 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format RU phone number with plus`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("+71234567890")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+7 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format spaced RU phone number starting with 8`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("8 123 456 78 90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+7 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format spaced RU phone number starting with 7`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("7 123 456 78 90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+7 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format spaced RU phone number with plus`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("+7 123 456 78 90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+7 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format formated RU phone number starting with 8`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("8 (123) 456-78-90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+7 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format formated RU phone number starting with 7`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("7 (123) 456-78-90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+7 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format formated RU phone number with plus`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("+7 (123) 456-78-90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+7 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format bad formated RU phone number with plus`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("7123) 456-7890")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+7 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format international phone number`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("4201234567890")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+420 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format international phone number with plus`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("+4201234567890")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+420 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should return error on too short phone number`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("12345")
        assertTrue(formatPhoneNumberResult is OperationResult.Error)
        assertEquals("Phone number must have minimum 10 digits", (formatPhoneNumberResult as OperationResult.Error).error)
    }

    @Test
    fun `should return error on invalid RU phone number length starting with 8`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("8123456789")
        assertTrue(formatPhoneNumberResult is OperationResult.Error)
        assertEquals("Invalid phone number format", (formatPhoneNumberResult as OperationResult.Error).error)
    }

    @Test
    fun `should return error on invalid RU phone number length starting with 7`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("7123456789")
        assertTrue(formatPhoneNumberResult is OperationResult.Error)
        assertEquals("Invalid phone number format", (formatPhoneNumberResult as OperationResult.Error).error)
    }

    @Test
    fun `should return error on invalid RU phone number length starting with plus`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("+7123456789")
        assertTrue(formatPhoneNumberResult is OperationResult.Error)
        assertEquals("Invalid phone number format", (formatPhoneNumberResult as OperationResult.Error).error)
    }

    @Test
    fun `should return error on completely invalid input`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("abcde")
        assertTrue(formatPhoneNumberResult is OperationResult.Error)
        assertEquals("Phone number must have minimum 10 digits", (formatPhoneNumberResult as OperationResult.Error).error)
    }
}