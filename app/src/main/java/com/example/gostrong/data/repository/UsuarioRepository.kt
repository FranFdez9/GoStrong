package com.example.gostrong.data.repository

import com.example.gostrong.data.local.dao.UsuarioDao
import com.example.gostrong.data.local.entity.UsuarioEntity

class UsuarioRepository(private val usuarioDao: UsuarioDao) {
    suspend fun insertarUsuario(usuario: UsuarioEntity){
        usuarioDao.insertarUsuario(usuario)
    }

    suspend fun obtenerUsuarioPorNombre(nombreUsuario: String): UsuarioEntity? {
        return usuarioDao.obtenerUsuarioPorNombre(nombreUsuario)
    }

    suspend fun actualizarUsuario(usuario: UsuarioEntity) {
        usuarioDao.actualizarUsuario(usuario)
    }

    suspend fun eliminarUsuario(usuario: UsuarioEntity) {
        usuarioDao.eliminarUsuario(usuario)
    }

    suspend fun obtenerUsuarioPorEmail(email: String): UsuarioEntity? {
        return usuarioDao.obtenerUsuarioPorEmail(email)
    }

}