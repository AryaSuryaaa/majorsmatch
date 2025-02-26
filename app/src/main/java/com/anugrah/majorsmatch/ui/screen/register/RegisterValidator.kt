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
    fun validateName(name: String): String? {
        return if (name.isBlank()) "Nama tidak boleh kosong" else null
    }

    fun validateEmail(email: String): String? {
        return if (!email.isValidEmail()) "Email tidak valid" else null
    }

    fun validatePassword(password: String): String? {
        return if (!password.isValidPassword()) "Password harus minimal 8 karakter, mengandung huruf besar, kecil, dan angka" else null
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): String? {
        return if (password != confirmPassword) "Password tidak cocok" else null
    }
}
