package com.example.gostrong.data.Remoto

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object Supabase {
    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://gvfnihpkuycobbqaklxh.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imd2Zm5paHBrdXljb2JicWFrbHhoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzY2NjMxOTMsImV4cCI6MjA5MjIzOTE5M30.qmTL3yDwKD1RtItzlj1qK6Gl-XQ9BZ0CIZou_UbfhN0"
    ) {
        install(Auth)
        install(Postgrest)
    }
}