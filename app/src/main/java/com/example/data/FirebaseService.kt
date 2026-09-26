package com.example.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseService {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun isUserLoggedIn(): Boolean = auth.currentUser != null

    suspend fun saveCalculation(calculation: Calculation) {
        val user = auth.currentUser ?: return
        db.collection("users").document(user.uid)
            .collection("calculations")
            .add(calculation)
            .await()
    }
    
    suspend fun getCalculations(): List<Calculation> {
        val user = auth.currentUser ?: return emptyList()
        val snapshot = db.collection("users").document(user.uid)
            .collection("calculations")
            .get()
            .await()
        return snapshot.toObjects(Calculation::class.java)
    }
}
