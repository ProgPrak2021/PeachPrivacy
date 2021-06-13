package com.peachprivacy.userservice.authentication

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import java.security.Key
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.annotation.PostConstruct

@Service
class AuthenticationService @Autowired constructor(val accountRepository: AccountRepository, val mailSender: JavaMailSender) {
    val salt: String = BCrypt.gensalt()

    @Value("\${jwt.secret}")
    lateinit var secret: String
    lateinit var key: Key

    @PostConstruct
    fun initializeKey() {
        key = Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun register(email: String, password: String): Boolean {
        if (accountRepository.existsByEmail(email)) return false
        val account = accountRepository.save(Account().apply {
            this.email = email
            this.password = BCrypt.hashpw(password, salt)
            this.role = "default"
            this.emailToken = UUID.randomUUID().toString().replace("-", "")
        })
        sendRegisterMail(account)
        return true
    }

    private fun sendRegisterMail(account: Account) {
        val message = mailSender.createMimeMessage()
        val messageHelper = MimeMessageHelper(message)
        messageHelper.setFrom("no-reply@peachprivacy.com")
        messageHelper.setTo(account.email)
        messageHelper.setSubject("PeachPrivacy - Account bestätigen")

        val html = ClassPathResource("account-confirmation.html")
            .inputStream
            .readAllBytes()
            .decodeToString()
            .replace("%LINK%", "https://peachprivacy.dev/email/confirm/${account.emailToken}")

        messageHelper.setText(html, true)

        mailSender.send(message)
    }

    fun confirmEmail(token: String): Boolean {
        val account = accountRepository.findByEmailToken(token) ?: return false
        account.emailToken = null
        return true
    }

    fun login(email: String, password: String): String? {
        val account = accountRepository.findByEmail(email) ?: return null
        if (account.emailToken != null) return null
        if (!BCrypt.checkpw(password, account.password)) return null
        return generateToken(
            account.id.toString(),
            account.email,
            listOf(account.role, account.id.toString(), "any")
        )
    }

    fun generateToken(id: String, email: String, authorities: List<String>): String =
        Jwts.builder()
            .setClaims(mutableMapOf("id" to id, "authorities" to authorities.joinToString(",")))
            .setSubject(email)
            .setIssuedAt(Date())
            .setExpiration(
                Date.from(
                    LocalDateTime.now().plusDays(14L).atZone(ZoneId.systemDefault()).toInstant()
                )
            )
            .signWith(key)
            .compact()
}