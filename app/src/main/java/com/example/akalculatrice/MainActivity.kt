package com.example.akalculatrice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import com.example.akalculatrice.ui.theme.AKalculatriceTheme
import com.example.akalculatrice.ui.theme.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AKalculatriceTheme {
                // Déclaration des variables
                // Dont la liste des boutons, organisés par rangées
                var nombre by remember { mutableStateOf("0") }
                var operande by remember { mutableStateOf("0") }
                var operateur by remember { mutableStateOf("+") }
                var affichage by remember { mutableStateOf("0") }
                val boutons = listOf(
                    listOf("7", "8", "9", "+"),
                    listOf("4", "5", "6", "-"),
                    listOf("1", "2", "3", "x"),
                    listOf("C", "0", ".", "/")
                )
                Column {
                    Banniere()
                    Column(modifier = Modifier.padding(8.dp)) {
                        // Affichage de l'opération
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(40.dp),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Text(
                                    text = "$nombre $operateur $operande",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 30.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = LightOrange
                                )
                            }
                        }
                        // Affichage du calcul
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(40.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$affichage",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 50.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = DarkOrange,
                                    lineHeight = 60.sp
                                )
                            }
                        }
                        // Génération des boutons pour les nombres et opérateurs via une boucle
                        boutons.forEach { ligne ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                ligne.forEach { bouton ->
                                    Bouton(
                                        text = bouton,
                                        onClick = {
                                            when (bouton) {
                                                // Réinitialise le dernier chiffre
                                                "C" -> operande = ChangerOperande("0", operande, action = "C")
                                                "." -> {
                                                    // Ajoute un point si pas déjà inséré
                                                    if (!operande.contains(".")) { // évite le chaînage de points
                                                        operande = ChangerOperande(".", operande)
                                                    }
                                                }
                                                // Change l'opérateur
                                                in listOf("+", "-", "x", "/") -> operateur = bouton
                                                else -> {
                                                    nombre = CheckError(nombre)
                                                    operande = ChangerOperande(bouton, operande)
                                                }
                                            }
                                        },
                                        // Donne un poids égal à tous les boutons, pour qu'ils occupent le même espace
                                        modifier = Modifier.weight(1f),
                                        // Vive les couleurs
                                        containercolor = if (bouton in listOf("+", "-", "x", "/")) DarkBlue else SkyBlue
                                    )
                                }
                            }
                        }

                        // Boutons "=" et "RESET"
                        // On les gère en dehors de la boucle, ils sont + spécifiques
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            // Bouton "="
                            Bouton(
                                text = "=",
                                onClick = {
                                    val res = Calcul(
                                        nombre = nombre,
                                        operateur = operateur,
                                        operande = operande
                                    )
                                    if (res != "Division par zéro !") {
                                        // Schéma usuel :
                                        nombre = res // Met à jour le résultat
                                        affichage = nombre
                                        operateur = "+" // Réinitialise l'opérateur
                                        operande = "0" // Réinitialise l'opérande
                                    } else {
                                        // Message d'erreur
                                        affichage = res
                                    }
                                },
                                modifier = Modifier.weight(1f),
                                containercolor = DarkBlue
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            // Bouton "RESET"
                            Bouton(
                                text = "RESET",
                                onClick = {
                                    nombre = "0"
                                    operateur = "+"
                                    operande = "0"
                                    affichage = nombre
                                },
                                modifier = Modifier.weight(1f),
                                containercolor = Blue
                            )
                        }
                    }
                }
            }
        }
    }
}


// Fonction qui sert à générer/afficher la bannière
@Composable
fun Banniere(){
    Box(
        // Modificateurs pour prendre toute la largeur de l'écran
        // Hauteur définie avec .dp, et couleurs définies dans Color.kt
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(Blue)
            .padding(40.dp)
    ){
        Text(
            text = "Bienvenue sur la super calculatrice d'Alan !",
            color = LightOrange,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

// Fonction pour générer un bouton
@Composable
fun Bouton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier, // Modifier pour changer de style
    containercolor: Color = SkyBlue
){
    Button(
        onClick = onClick,
        modifier = modifier // modifier pour les changements esthétiques
            .padding(8.dp)
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containercolor,
        )
    ) {
        Text(
            text = text,
            color = White
        )
    }
}

// Change l'opérande pour le calcul et son affichage
fun ChangerOperande(chiffre: String, oldChiffre: String, action: String = "A"): String {
    if (action != "C") {
        if (chiffre == ".") {
            // Si le point est déjà présent, on retourne directement l'ancien chiffre
            if (oldChiffre.contains(".")) {
                return oldChiffre
            }
            // Si l'ancien chiffre est "0", on retourne "0."
            if (oldChiffre == "0") {
                return "0."
            }
            // Sinon, on ajoute le point au chiffre existant
            return oldChiffre + "."
        } else {
            if (oldChiffre == "0") {
                return chiffre
            } else {
                return oldChiffre + chiffre
            }
        }
    } else {
        //Si l'action est "C", on supprime le dernier caractère
        if (oldChiffre.length > 1) {
            return oldChiffre.dropLast(1)
        } else {
            return "0" // On évite de retirer le caractère s'il est seul et le dernier
        }
    }
}

fun Calcul(nombre: String, operateur: String, operande: String): String {
    var newNombre = "0"
    when (operateur) { // Différents calculs appliqués
        "+" -> newNombre = (nombre.toDouble() + operande.toDouble()).toString()
        "-" -> newNombre = (nombre.toDouble() - operande.toDouble()).toString()
        "x" -> newNombre = (nombre.toDouble() * operande.toDouble()).toString()
        "/" -> if (operande == "0") { //Fail-safe de division par 0
            newNombre = "Division par zéro !"
        } else {
            newNombre = (nombre.toDouble() / operande.toDouble()).toString()
        }
    }
    return FormatDecimales(newNombre) // Formatte le résultat avant de le retourner
}

fun CheckError(nombre : String): String{
    var ret = "0"
    if(nombre == "Division par zéro !"){
        return ret
    }
    else{
        ret = nombre
        return ret
    }
}

//vérifie si un nombre a une partie décimale ou non
fun FormatDecimales(result: String): String {
    val number = result.toDoubleOrNull()
    return if (number != null && number % 1.0 == 0.0) {
        number.toInt().toString() // Retourne un entier si la partie décimale est 0
    } else {
        result // Retourne le résultat tel quel avec virgule
    }
}