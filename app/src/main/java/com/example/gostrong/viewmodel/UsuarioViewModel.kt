package com.example.gostrong.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gostrong.data.local.entity.UsuarioEntity
import com.example.gostrong.data.repository.UsuarioRepository
import kotlinx.coroutines.launch
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import com.example.gostrong.data.Remoto.Supabase
import com.example.gostrong.data.Remoto.dto.UsuarioDto
import com.example.gostrong.data.Remoto.dto.toDto
import com.example.gostrong.data.Remoto.dto.toEntity
class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    // --- NUEVO REGISTRO NUBE + LOCAL ---
    fun registrarUsuario(
        usuario: UsuarioEntity,
        passwordUsuario: String,
        onResult: (Boolean, String?) -> Unit // Ahora podemos devolver si fue éxito y el error
    ) {
        viewModelScope.launch {
            try {
                // 1. Damos de alta al usuario en Supabase (Solo le importa su Email y Password)
                Supabase.client.auth.signUpWith(Email) {
                    email = usuario.email
                    password = passwordUsuario
                }

                // 2. Subimos el perfil físico a la tabla "usuarios_nube" en Supabase.
                Supabase.client.postgrest["usuarios_nube"].insert(usuario.toDto())

                // 3. Si llegamos aquí, la nube está OK. Guardamos su físico en Room.
                repository.insertarUsuario(usuario)

                onResult(true, null)
            } catch (e: Exception) {
                onResult(false, e.localizedMessage)
            }
        }
    }

    fun obtenerUsuarioPorNombre(
        nombreUsuario: String,
        onResult: (UsuarioEntity?) -> Unit
    ) {
        viewModelScope.launch {
            val usuario = repository.obtenerUsuarioPorNombre(nombreUsuario)
            onResult(usuario)
        }
    }

    fun actualizarUsuario(usuario: UsuarioEntity) {
        viewModelScope.launch {
            // Guardamos local INMEDIATAMENTE para fluidez extrema
            repository.actualizarUsuario(usuario)
            
            // Subimos a la nube de fondo
            try {
                Supabase.client.postgrest["usuarios_nube"].upsert(usuario.toDto())
            } catch (e: Exception) {
                // Si falla (ej. sin cobertura en el gimnasio), no bloquea la app
                println("Error al sincronizar con la nube: ${e.message}")
            }
        }
    }

    fun eliminarUsuario(usuario: UsuarioEntity) {
        viewModelScope.launch {
            repository.eliminarUsuario(usuario)
        }
    }

    fun obtenerUsuarioPorEmail(
        email: String,
        onResult: (UsuarioEntity?) -> Unit
    ) {
        viewModelScope.launch {
            val usuario = repository.obtenerUsuarioPorEmail(email)
            onResult(usuario)
        }
    }

    // --- NUEVO LOGIN NUBE ---
    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                // Supabase verifica el email y la contraseña
                Supabase.client.auth.signInWith(Email) {
                    this.email = email
                    this.password = password
                }

                // ✨ SINCRONIZACIÓN MÁGICA DE BAJADA ✨
                // Extraer datos desde la nube e inyectarlos en Room vacío (ej. Móvil Nuevo)
                try {
                    val usuarioNube = Supabase.client.postgrest["usuarios_nube"]
                        .select { filter { eq("email", email) } }
                        .decodeSingleOrNull<UsuarioDto>()

                    if (usuarioNube != null) {
                        val usuarioLocal = repository.obtenerUsuarioPorEmail(email)
                        if (usuarioLocal == null) {
                            // Móvil vacío: Descargamos todo de la nube a Room
                            repository.insertarUsuario(usuarioNube.toEntity())
                        } else {
                            // Móvil existente: Actualizamos manteniendo su ID local
                            val actualizado = usuarioNube.toEntity().copy(id = usuarioLocal.id)
                            repository.actualizarUsuario(actualizado)
                        }
                    }
                } catch (e: Exception) {
                    println("Login exitoso, pero fallo al sincronizar nube->local: ${e.message}")
                }

                // Si la línea de login original no falla, la contraseña es correcta 👍
                onResult(true, null)
            } catch (e: Exception) {
                // Si falla, pasamos el error real para entender qué está pasando
                onResult(false, e.localizedMessage ?: "Error desconocido")
            }
        }
    }

    // --- RECUPERACIÓN ---
    fun recuperarContrasenaSupabase(email: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            try {
                Supabase.client.auth.resetPasswordForEmail(email)
                onResult(true, null)
            } catch (e: Exception) {
                onResult(false, e.localizedMessage)
            }
        }
    }
}
