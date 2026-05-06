package com.example.gostrong.ui.screens.register

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import com.example.gostrong.data.local.entity.UsuarioEntity
import com.example.gostrong.data.util.ManejoSesion
import com.example.gostrong.R
import com.example.gostrong.viewmodel.UsuarioViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    usuarioViewModel: UsuarioViewModel,
    onRegistroExitoso: () -> Unit = {}
) {
    val contexto = LocalContext.current
    val manejoSesion = remember { ManejoSesion(contexto) }

    val scrollState = rememberScrollState()

    var cargando by remember { mutableStateOf(false) }
    var nombreUsuario by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }

    val calendario = Calendar.getInstance()
    var fechaNacimiento by remember { mutableStateOf<Long?>(null) }
    var textoFechaNacimiento by remember { mutableStateOf("Selecciona tu fecha") }

    var altura by remember { mutableStateOf(170) }
    var peso by remember { mutableStateOf(70f) }
    var pesoObjetivo by remember { mutableStateOf(70f) }

    val sHombre = stringResource(id = R.string.registro_hombre)
    val sMujer = stringResource(id = R.string.registro_mujer)
    val sOtro = stringResource(id = R.string.registro_otro)
    val opcionesSexo = listOf(sHombre, sMujer, sOtro)
    var sexoSeleccionado by remember { mutableStateOf(opcionesSexo[0]) }

    val sNovato = stringResource(id = R.string.registro_novato)
    val sNormal = stringResource(id = R.string.registro_normal)
    val sAvanzado = stringResource(id = R.string.registro_avanzado)
    val opcionesNivel = listOf(sNovato, sNormal, sAvanzado)
    var nivelSeleccionado by remember { mutableStateOf(opcionesNivel[1]) }

    val sPerderPeso = stringResource(id = R.string.registro_perder_peso)
    val sMantener = stringResource(id = R.string.registro_mantener)
    val sGanarMusculo = stringResource(id = R.string.registro_ganar_musculo)
    val opcionesObjetivo = listOf(sPerderPeso, sMantener, sGanarMusculo)
    var objetivoSeleccionado by remember { mutableStateOf(opcionesObjetivo[1]) }

    var diasEntrenamiento by remember { mutableStateOf(3) }
    var alergias by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título Principal
        Icon(Icons.Default.Person, contentDescription = "Registro", modifier = Modifier.size(64.dp), tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.registro_crea_tu_cuenta),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = stringResource(id = R.string.registro_unete),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))

        // --- TARJETA 1: DATOS DE LA CUENTA ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(stringResource(id = R.string.registro_datos_cuenta), fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)

                OutlinedTextField(
                    value = nombreUsuario, onValueChange = { nombreUsuario = it },
                    label = { Text(stringResource(id = R.string.registro_nombre_usuario)) }, modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp), leadingIcon = { Icon(Icons.Default.Face, null) }
                )
                OutlinedTextField(
                    value = email, onValueChange = { email = it },
                    label = { Text(stringResource(id = R.string.registro_email)) }, modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp), leadingIcon = { Icon(Icons.Default.Email, null) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                OutlinedTextField(
                    value = contraseña, onValueChange = { contraseña = it },
                    label = { Text(stringResource(id = R.string.registro_contrasena)) }, modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp), leadingIcon = { Icon(Icons.Default.Lock, null) },
                    visualTransformation = PasswordVisualTransformation()
                )

                Button(
                    onClick = {
                        DatePickerDialog(contexto, { _, año, mes, dia ->
                            calendario.set(año, mes, dia)
                            fechaNacimiento = calendario.timeInMillis
                            textoFechaNacimiento = "$dia/${mes + 1}/$año"
                        }, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)).show()
                    },
                    modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Icon(Icons.Default.DateRange, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text(textoFechaNacimiento)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // --- TARJETA 2: DATOS FÍSICOS ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(stringResource(id = R.string.registro_perfil_fisico), fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)

                // Sexo (Segmented Button style)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    opcionesSexo.forEach { opcion ->
                        FilterChip(
                            selected = sexoSeleccionado == opcion,
                            onClick = { sexoSeleccionado = opcion },
                            label = { Text(opcion) }
                        )
                    }
                }

                Column {
                    Text(stringResource(id = R.string.registro_altura, altura), fontWeight = FontWeight.Medium)
                    Slider(value = altura.toFloat(), onValueChange = { altura = it.toInt() }, valueRange = 100f..250f)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(stringResource(id = R.string.registro_peso_actual, peso.toInt()), fontSize = 14.sp)
                        Slider(value = peso, onValueChange = { peso = it }, valueRange = 30f..200f)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(stringResource(id = R.string.registro_objetivo_peso, pesoObjetivo.toInt()), fontSize = 14.sp)
                        Slider(value = pesoObjetivo, onValueChange = { pesoObjetivo = it }, valueRange = 30f..200f)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // --- TARJETA 3: ENTRENAMIENTO ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(stringResource(id = R.string.registro_metas), fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)

                Text(stringResource(id = R.string.registro_objetivo), fontSize = 14.sp)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    opcionesObjetivo.forEach { opcion ->
                        FilterChip(
                            selected = objetivoSeleccionado == opcion,
                            onClick = { objetivoSeleccionado = opcion },
                            label = { Text(opcion) }
                        )
                    }
                }

                Text(stringResource(id = R.string.registro_nivel), fontSize = 14.sp)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    opcionesNivel.forEach { opcion ->
                        FilterChip(
                            selected = nivelSeleccionado == opcion,
                            onClick = { nivelSeleccionado = opcion },
                            label = { Text(opcion) }
                        )
                    }
                }

                Column {
                    Text(stringResource(id = R.string.registro_dias_semana, diasEntrenamiento), fontWeight = FontWeight.Medium)
                    Slider(value = diasEntrenamiento.toFloat(), onValueChange = { diasEntrenamiento = it.toInt() }, valueRange = 1f..7f, steps = 5)
                }

                OutlinedTextField(
                    value = alergias, onValueChange = { alergias = it },
                    label = { Text(stringResource(id = R.string.registro_alergias)) }, modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // --- ERROR Y BOTÓN FINAL ---
        mensajeError?.let {
            Text(it, color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Button(
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp),
            enabled = !cargando, // Bloqueamos el botón si está cargando
            onClick = {
                if (nombreUsuario.isBlank() || email.isBlank() || contraseña.isBlank() || fechaNacimiento == null) {
                    mensajeError = contexto.getString(R.string.registro_rellena_campos)
                    return@Button
                }

                cargando = true
                mensajeError = null

                val usuario = UsuarioEntity(
                    nombreUsuario = nombreUsuario, email = email,
                    fechaNacimeinto = fechaNacimiento!!, sexo = sexoSeleccionado, altura = altura,
                    peso = peso, pesoObjetivo = pesoObjetivo, nivel = nivelSeleccionado,
                    objetivo = objetivoSeleccionado, diasEntrenamiento = diasEntrenamiento, alergias = alergias
                )

                usuarioViewModel.registrarUsuario(usuario, contraseña) { exito, error ->
                    cargando = false
                    if (exito) {
                        manejoSesion.guardarSesion(email)
                        onRegistroExitoso()
                    } else {
                        mensajeError = "Network: $error"
                    }
                }
            }
        ) {
            Text(
                text = if (cargando) stringResource(id = R.string.registro_creando_perfil) else stringResource(id = R.string.registro_comenzar),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        }

        Spacer(modifier = Modifier.height(32.dp))
    }
