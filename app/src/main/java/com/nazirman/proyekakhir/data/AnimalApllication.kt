package com.nazirman.proyekakhir.data

import android.app.Application

class AnimalApllication  : Application() {
    // Using by lazy so the database is only created when needed
    // rather than when the application starts
    val database: HewanDatabase by lazy { HewanDatabase.getDatabase(this) }

}