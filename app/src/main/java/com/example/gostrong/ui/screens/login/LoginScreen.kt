package com.example.gostrong.ui.screens.login

import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.compose.ui.res.stringResource
import com.example.gostrong.R
import com.example.gostrong.data.util.ManejoSesion
import com.example.gostrong.viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    usuarioViewModel: UsuarioViewModel,
    login: () -> Unit = {},
    registro: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf<String?>(null) }
    var cargando by remember { mutableStateOf(false) }
    var mostrarDialogRecuperacion by remember { mutableStateOf(false) }
    var emailRecuperacion by remember { mutableStateOf("") }

    val context = LocalContext.current
    val manejoSesion = remember { ManejoSesion(context) }

    // Colores del tema activo
    val colorNaranjaFuerte = MaterialTheme.colorScheme.primary
    val colorFondo = MaterialTheme.colorScheme.background

    // LÓGICA BIOMÉTRICA (FaceID / Huella)
    fun autenticarConBiometria() {
        val fragmentActivity = context as? FragmentActivity
        if (fragmentActivity == null) {
            Toast.makeText(context, context.getString(R.string.login_error_activity), Toast.LENGTH_SHORT).show()
            return
        }

        val executor = ContextCompat.getMainExecutor(context)
        val biometricPrompt = BiometricPrompt(fragmentActivity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)

                    // Comprobamos si ya teníamos una sesión previa guardada
                    val ultimoEmail = manejoSesion.obtenerEmailUsuario()
                    if (!ultimoEmail.isNullOrEmpty()) {
                        // Forzamos el acceso sin contraseña
                        manejoSesion.guardarSesion(ultimoEmail)
                        login()
                    } else {
                        Toast.makeText(context, context.getString(R.string.login_inicia_primera_vez), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(context.getString(R.string.login_acceso_biometrico_titulo))
            .setSubtitle(context.getString(R.string.login_acceso_biometrico_subtitulo))
            .setNegativeButtonText(context.getString(R.string.login_usar_contrasena))
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorFondo)
            .padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // CABECERA / LOGO
        Text(
            text = "GoStrong",
            color = colorNaranjaFuerte,
            fontSize = 42.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 2.sp
        )
        Text(
            text = stringResource(id = R.string.login_bienvenido),
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp, bottom = 48.dp)
        )

        // CAMPO EMAIL
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(id = R.string.login_correo)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CAMPO CONTRASEÑA (CON OJO PARA VER/OCULTAR)
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.login_contrasena)) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = stringResource(id = R.string.login_ocultar_mostrar_pass))
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        // MENSAJE DE ERROR
        if (mensajeError != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = mensajeError!!,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // BOTÓN PRINCIPAL DE LOGIN
        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    val sCompletar = context.getString(R.string.login_completar_todo)
                    mensajeError = sCompletar
                    return@Button
                }
                cargando = true
                usuarioViewModel.login(email, password) { exito, error ->
                    cargando = false
                    if (exito) {
                        manejoSesion.guardarSesion(email)
                        login()
                    } else {
                        // Ahora le pasamos el error exacto de red o de credenciales
                        val sIncorrectas = context.getString(R.string.login_credenciales_incorrectas)
                        mensajeError = error ?: sIncorrectas
                    }
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorNaranjaFuerte),
            enabled = !cargando
        ) {
            Text(
                text = if (cargando) stringResource(id = R.string.cargando) else stringResource(id = R.string.login_entrar),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ACCESO RÁPIDO BIOMÉTRICO
        OutlinedButton(
            onClick = { autenticarConBiometria() },
            modifier = Modifier.fillMaxWidth().height(55.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Filled.Fingerprint, contentDescription = "Biometría", modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(id = R.string.login_entrar_biometria), fontSize = 16.sp)
        }

        TextButton(onClick = { mostrarDialogRecuperacion = true }) {
            Text(stringResource(id = R.string.login_olvido_contrasena), color = colorNaranjaFuerte)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // BOTÓN DE REGISTRO
        TextButton(onClick = registro) {
            Text(
                stringResource(id = R.string.login_no_tienes_cuenta),
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold
            )
        }
        if (mostrarDialogRecuperacion) {
            AlertDialog(
                onDismissRequest = { mostrarDialogRecuperacion = false },
                title = { Text(stringResource(id = R.string.login_recuperar_contrasena)) },
                text = {
                    TextField(
                        value = emailRecuperacion,
                        onValueChange = { emailRecuperacion = it },
                        label = { Text(stringResource(id = R.string.login_tu_correo)) },
                        singleLine = true
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        usuarioViewModel.recuperarContrasenaSupabase(emailRecuperacion) { exito, error ->
                            if (exito) {
                                Toast.makeText(context, context.getString(R.string.login_correo_enviado), Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(context, "${context.getString(R.string.login_error)} $error", Toast.LENGTH_LONG).show()
                            }
                        }
                        mostrarDialogRecuperacion = false
                    }) {
                        Text(stringResource(id = R.string.enviar))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { mostrarDialogRecuperacion = false }) {
                        Text(stringResource(id = R.string.cancelar))
                    }
                }
            )
        }
    }
}
