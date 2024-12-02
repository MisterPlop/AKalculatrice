package com.example.akalculatrice

import android.os.Bundle
import android.os.SystemClock
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
                var nombre by remember { mutableStateOf("0") }
                var operande by remember { mutableStateOf("0") }
                var operateur by remember { mutableStateOf("+") }
                var affichage by remember { mutableStateOf("0") }
                Column {
                    Banniere()
                    Column (modifier = Modifier.padding(8.dp)){
                        // Affichage de l'opération
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ){
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(40.dp),
                                contentAlignment = Alignment.TopEnd
                            ){
                                Text(
                                    text = "$nombre $operateur $operande",                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 30.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = LightOrange
                                )
                            }
                        }
                        // Affichage du calcul
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ){
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(40.dp),
                                contentAlignment = Alignment.Center
                            ){
                                Text(
                                    text = "$affichage",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 50.sp,
                                    fontFamily = FontFamily.Monospace,
                                    color = DarkOrange
                                )
                            }
                        }
                        // Boutons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ){
                            Bouton(
                                text = "7",
                                onClick = {
                                    nombre = CheckError(nombre)
                                    operande = ChangerOperande(
                                        chiffre = "7",
                                        oldChiffre = operande
                                    )},
                                modifier = Modifier.weight(1f),
                            )
                            Bouton(
                                text = "8",
                                onClick = {
                                    nombre = CheckError(nombre)
                                    operande = ChangerOperande(
                                    chiffre = "8",
                                    oldChiffre = operande
                                )},
                                modifier = Modifier.weight(1f),
                            )
                            Bouton(
                                text = "9",
                                onClick = {
                                    nombre = CheckError(nombre)
                                    operande = ChangerOperande(
                                    chiffre = "9",
                                    oldChiffre = operande
                                )},
                                modifier = Modifier.weight(1f),
                            )
                            Bouton(
                                text = "+",
                                onClick = { operateur = "+" },
                                modifier = Modifier.weight(1f),
                                containercolor = DarkBlue
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ){
                            Bouton(
                                text = "4",
                                onClick = {
                                    nombre = CheckError(nombre)
                                    operande = ChangerOperande(
                                    chiffre = "4",
                                    oldChiffre = operande
                                )},
                                modifier = Modifier.weight(1f),
                            )
                            Bouton(
                                text = "5",
                                onClick = {
                                    nombre = CheckError(nombre)
                                    operande = ChangerOperande(
                                    chiffre = "5",
                                    oldChiffre = operande
                                )},
                                modifier = Modifier.weight(1f),
                            )
                            Bouton(
                                text = "6",
                                onClick = {
                                    nombre = CheckError(nombre)
                                    operande = ChangerOperande(
                                    chiffre = "6",
                                    oldChiffre = operande
                                )},
                                modifier = Modifier.weight(1f),
                            )
                            Bouton(
                                text = "-",
                                onClick = { operateur = "-" },
                                modifier = Modifier.weight(1f),
                                containercolor = DarkBlue
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ){
                            Bouton(
                                text = "1",
                                onClick = {
                                    nombre = CheckError(nombre)
                                    operande = ChangerOperande(
                                    chiffre = "1",
                                    oldChiffre = operande
                                )},
                                modifier = Modifier.weight(1f),
                            )
                            Bouton(
                                text = "2",
                                onClick = {
                                    nombre = CheckError(nombre)
                                    operande = ChangerOperande(
                                    chiffre = "2",
                                    oldChiffre = operande
                                )},
                                modifier = Modifier.weight(1f),
                            )
                            Bouton(
                                text = "3",
                                onClick = {
                                    nombre = CheckError(nombre)
                                    operande = ChangerOperande(
                                    chiffre = "3",
                                    oldChiffre = operande
                                )},
                                modifier = Modifier.weight(1f),
                            )
                            Bouton(
                                text = "x",
                                onClick = { operateur = "x"},
                                modifier = Modifier.weight(1f),
                                containercolor = DarkBlue
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ){
                            Bouton(
                                text = "C",
                                onClick = {
                                    operande = ChangerOperande(
                                        chiffre = "0",
                                        oldChiffre = operande,
                                        action = "C"
                                    )
                                },
                                modifier = Modifier.weight(1f),
                            )
                            Bouton(
                                text = "0",
                                onClick = {
                                    nombre = CheckError(nombre)
                                    operande = ChangerOperande(
                                    chiffre = "0",
                                    oldChiffre = operande
                                )},
                                modifier = Modifier.weight(1f),
                            )
                            Bouton(
                                text = ".",
                                onClick = { SystemClock.sleep(100) },
                                modifier = Modifier.weight(1f),
                            )
                            Bouton(
                                text = "/",
                                onClick = { operateur = "/" },
                                modifier = Modifier.weight(1f),
                                containercolor = DarkBlue
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ){
                            Bouton(
                                text = "=",
                                onClick = {
                                    var res = Calcul(
                                        nombre = nombre,
                                        operateur = operateur,
                                        operande = operande
                                    )
                                    if(res != "Division par zéro !"){
                                        nombre = res
                                        affichage = nombre
                                        operateur = "+"
                                        operande = "0"
                                    }
                                    else {
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
                        ){
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

@Composable
fun Banniere(){
    Box(
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

@Composable
fun Bouton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containercolor: Color = SkyBlue
){
    Button(
        onClick = onClick,
        modifier = modifier
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

fun ChangerOperande(chiffre : String, oldChiffre : String, action: String="A"): String{
    if(action != "C"){
        if(oldChiffre == "0"){
            return chiffre
        }
        else {
            return oldChiffre + chiffre
        }
    }
    else{
        if(oldChiffre.length >1){
            return oldChiffre.dropLast(1)
        }
        else{
            return "0"
        }
    }
}

fun Calcul(nombre: String, operateur:String, operande: String): String{
    var newNombre = "0"
    when(operateur){
        "+" -> newNombre = (nombre.toInt() + operande.toInt()).toString()
        "-" -> newNombre = (nombre.toInt() - operande.toInt()).toString()
        "x" -> newNombre = (nombre.toInt() * operande.toInt()).toString()
        "/" -> if(operande == "0"){newNombre="Division par zéro !"} else{newNombre = (nombre.toInt()/operande.toInt()).toString()
        }
    }
    return newNombre
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