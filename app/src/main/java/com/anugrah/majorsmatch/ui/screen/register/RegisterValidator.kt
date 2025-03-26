package com.anugrah.majorsmatch.ui.screen.register

import android.util.Patterns
import java.util.regex.Pattern

private const val MIN_PASSWORD_LENGTH = 8
private const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

fun String.isValidEmail(): Boolean {
    return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.isNotBlank() &&
            this.length >= MIN_PASSWORD_LENGTH &&
            Pattern.compile(PASSWORD_PATTERN).matcher(this).matches()
}

object Validator {
    fun validateFullName(fullName: String): String? {
        return if (fullName.isBlank()) "Full name cannot be empty" else null
    }
    fun validateName(name: String): String? {
        return if (name.isBlank()) "Name cannot be empty" else null
    }

    fun validateEmail(email: String): String? {
        return if (!email.isValidEmail()) "Invalid email address" else null
    }

    fun validatePassword(password: String): String? {
        return if (!password.isValidPassword()) "Password must be at least 8 characters long and contain uppercase letters, lowercase letters, and numbers" else null
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): String? {
        return if (password != confirmPassword) "Passwords do not match" else null
    }
}
