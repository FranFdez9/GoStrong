package com.example.gostrong.data.Remoto

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object Supabase {
    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = "PUT YOUR SUPABASE URL HERE",
        supabaseKey = "PUT YOUR SUPABASE KEY HERE"
    ) {
        install(Auth)
        install(Postgrest)
    }
}
