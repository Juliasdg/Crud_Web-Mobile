package com.example.apptlou.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apptlou.R
import com.example.apptlou.ui.components.DrawerContent
import com.example.apptlou.ui.components.PersonagemSection
import com.example.apptlou.ui.components.Section
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonagensScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(navController = navController, isDrawerOpen = drawerState.isOpen)
            },
            content = {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("") },
                            navigationIcon = {
                                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                    Icon(Icons.Filled.Menu, contentDescription = "Abrir Menu")
                                }
                            },
                            colors = TopAppBarColors(
                                containerColor = Color(0xFF1B1B1B),
                                scrolledContainerColor = Color(0xFF1B1B1B),
                                navigationIconContentColor = Color.White,
                                titleContentColor = Color(0xFF1B1B1B),
                                actionIconContentColor = Color(0xFF1B1B1B),
                            )
                        )
                    },
                    content = { paddingValues ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFF1B1B1B)) // Cor de fundo escura
                                .padding(paddingValues)
                                .verticalScroll(rememberScrollState()) // Tornar a tela scrollável
                        ) {
                            // Banner superior com imagem de fundo e gradiente
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(240.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.banner), // Substitua pela imagem correta
                                    contentDescription = "Banner",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(
                                                    Color.Transparent,
                                                    Color(0xFF1B1B1B)
                                                ),
                                                startY = 100f
                                            )
                                        )
                                )
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        text = "Personagens",
                                        color = Color(0xFFFF6666),
                                        style = MaterialTheme.typography.displayMedium.copy(
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(8.dp)) // Espaçamento entre título e subtítulo
                                    Text(
                                        text = "Conheça mais sobre os personagens do universo de The Last Of Us.",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            fontStyle = FontStyle.Italic
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                PersonagemSection(
                                    title = "Joel Miller",
                                    text = "Joel é um dos principais protagonistas da franquia. Ele é um homem endurecido pelo sofrimento de um mundo pós-apocalíptico e com um passado misterioso. Sua jornada começa quando ele é encarregado de proteger Ellie, uma garota que pode ser a chave para a cura da humanidade.",
                                    imageResId = R.drawable.joel
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                PersonagemSection(
                                    title = "Ellie Williams",
                                    text = "Ellie é uma jovem corajosa e inteligente que se torna a protagonista de *The Last of Us Part II*. Ela foi criada em um mundo devastado por uma pandemia e carrega uma carga emocional profunda, especialmente devido ao que acontece com aqueles que ela ama.",
                                    imageResId = R.drawable.ellie
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                PersonagemSection(
                                    title = "Abby Anderson",
                                    text = "Abby é uma personagem complexa que se vê em conflito com as ações e os objetivos de Joel. Sua história é cheia de reviravoltas emocionais, tornando-a uma das figuras mais controversas da série.",
                                    imageResId = R.drawable.abby
                                )

                                PersonagemSection(
                                    title = "Tommy Miller",
                                    text = "Tommy Miller é o irmão mais novo de Joel, que após um desentendimento com ele, acaba indo viver sua vida em um assentamento seguro. Ele é um homem mais idealista e busca uma vida de paz e reconstrução, enquanto Joel se vê cada vez mais envolvido com a luta pela sobrevivência. Embora o relacionamento entre eles tenha sido tenso por um período, Tommy e Joel possuem uma ligação muito forte, e sua amizade é um pilar importante na trama.",
                                    imageResId = R.drawable.tommy
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                PersonagemSection(
                                    title = "Robert",
                                    text = "Robert é um personagem antagonista que aparece no começo do jogo. Ele trai Joel e Tess, roubando armas deles e colocando em risco a missão deles. Sua ação motiva a busca de Joel e Tess por vingança, impulsionando os eventos que se seguem.",
                                    imageResId = R.drawable.robert
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                PersonagemSection(
                                    title = "Marlene",
                                    text = "Marlene é a líder do grupo de resistência Firefly. Ela é uma personagem fundamental, com objetivos que se alinham com os de Joel e Ellie, mas suas escolhas e ações ao longo do jogo tornam sua relação com os protagonistas mais complexa e cheia de dilemas.",
                                    imageResId = R.drawable.marlene
                                )

                                PersonagemSection(
                                    title = "Bill",
                                    text = "Bill é um sobrevivente que Joel e Ellie encontram em sua jornada. Ele é cínico, amargurado e vive isolado, mas sua inteligência e habilidades são vitais para a sobrevivência do trio. Sua relação com Joel tem uma história de amizade, mas também de ressentimento.",
                                    imageResId = R.drawable.bill
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                PersonagemSection(
                                    title = "Henry",
                                    text = "Henry é o irmão mais velho e a figura protetora de Sam. Em sua jornada com Joel e Ellie, Henry se revela um personagem com boas intenções, mas que também enfrenta as dificuldades de um mundo em colapso. Sua relação com Ellie e Joel cria um vínculo de amizade e confiança.",
                                    imageResId = R.drawable.henry
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                PersonagemSection(
                                    title = "Sam",
                                    text = "Sam, o irmão mais novo de Henry, traz uma leveza à história. Ele é curioso, brincalhão e cria uma forte amizade com Ellie. Sua tragédia é um dos momentos mais emocionantes e trágicos do jogo, impactando profundamente os outros personagens.",
                                    imageResId = R.drawable.sam
                                )
                            }
                        }
                    }
                )
            }
        )
    }
}
