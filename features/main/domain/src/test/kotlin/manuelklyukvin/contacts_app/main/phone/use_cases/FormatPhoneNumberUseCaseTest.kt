package manuelklyukvin.contacts_app.main.use_cases

import manuelklyukvin.contacts_app.core.utils.operations.models.OperationResult
import manuelklyukvin.contacts_app.main.phone.use_cases.FormatPhoneNumberUseCase
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
    fun `should return already formated US phone number with no code`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("(123) 456-78-90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+1 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should return already formated US phone number with code`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("1 (123) 456-78-90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+1 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should return already formated US phone number with plus`() {
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
    fun `should return already formated RU phone number starting with 8`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("8 (123) 456-78-90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+7 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should return already formated RU phone number starting with 7`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("7 (123) 456-78-90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+7 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should return already formated RU phone number with plus`() {
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
    fun `should format spaced international phone number`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("420 123 456 78 90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+420 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format spaced international phone number with plus`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("+420 123 456 78 90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+420 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should return already formated international phone number`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("420 (123) 456-78-90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+420 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should return already formated international phone number with plus`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("+420 (123) 456-78-90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+420 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should format bad formated international phone number with plus`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("+42(0123456-78 90")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("+420 (123) 456-78-90", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should return short phone number`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("12345")
        assertTrue(formatPhoneNumberResult is OperationResult.Success)
        assertEquals("12345", (formatPhoneNumberResult as OperationResult.Success).data)
    }

    @Test
    fun `should return error on completely invalid input`() {
        val formatPhoneNumberResult = formatPhoneNumberUseCase("abcde")
        assertTrue(formatPhoneNumberResult is OperationResult.Error)
        assertEquals("Invalid phone number", (formatPhoneNumberResult as OperationResult.Error).error)
    }
}