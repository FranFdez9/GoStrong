package com.example.gostrong.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gostrong.data.local.entity.UsuarioEntity

@Dao
// Indica a Room que esta interfaz es un Data Access Object.
// Aquí se definen todas las operaciones sobre la base de datos.
interface UsuarioDao {

    // Inserta un usuario en la base de datos.
    // Si ya existe uno con la misma clave primaria, se reemplaza.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarUsuario(usuario: UsuarioEntity)

    // Obtiene un usuario por su nombre de usuario.
    // Devuelve null si no existe.
    @Query("SELECT * FROM usuarios WHERE nombreUsuario = :nombreUsuario LIMIT 1")
    suspend fun obtenerUsuarioPorNombre(nombreUsuario: String): UsuarioEntity?

    // Obtiene todos los usuarios almacenados en la base de datos.
    @Query("SELECT * FROM usuarios")
    suspend fun obtenerTodosLosUsuarios(): List<UsuarioEntity>

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun obtenerUsuarioPorEmail(email: String): UsuarioEntity?

    // Actualiza los datos de un usuario existente.
    // Room identifica el usuario por su clave primaria (id).
    @Update
    suspend fun actualizarUsuario(usuario: UsuarioEntity)

    // Elimina un usuario de la base de datos.
    @Delete
    suspend fun eliminarUsuario(usuario: UsuarioEntity)
}

