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
import com.example.apptlou.ui.components.EasterEggsSection
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EasterEggsScreen(navController: NavController) {
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
                                .background(Color(0xFF1B1B1B))
                                .padding(paddingValues)
                                .verticalScroll(rememberScrollState())
                        ) {
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
                                                colors = listOf(Color.Transparent, Color(0xFF1B1B1B)),
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
                                EasterEggsSection(
                                    title = "Referência a \"Uncharted\"",
                                    text = "The Last of Us contém várias referências a outros jogos da Naughty Dog, incluindo *Uncharted*. Durante a jornada de Joel e Ellie, é possível encontrar alguns itens, como um boneco de ação, que fazem alusão ao protagonista Nathan Drake."
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                EasterEggsSection(
                                    title = "O Cartaz de Bill",
                                    text = "No capítulo \"The Town\", existe um cartaz de recrutamento que faz uma referência a Bill, personagem do jogo. Este cartaz foi colocado por um dos desenvolvedores do jogo como uma espécie de homenagem ao personagem e à sua história."
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                EasterEggsSection(
                                    title = "O Relógio de Sarah",
                                    text = "Uma das maiores emoções do jogo acontece logo no início, quando Joel perde sua filha, Sarah. A peça final dessa tragédia é o relógio de Sarah, que Joel guarda como uma lembrança eterna. O relógio aparece em vários momentos, reforçando a dor de Joel pela perda."
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                EasterEggsSection(
                                    title = "A Pizza do Shambler",
                                    text = "No momento em que os jogadores encontram os \"Shamblers\" (uma nova forma de inimigo do jogo), um dos ambientes apresenta uma pizza jogada no chão. Este detalhe é um aceno a um famoso meme da comunidade de desenvolvedores e gamers, que brincam sobre a pizza estar \"em qualquer lugar\"."
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                // Primeira seção: História
                                EasterEggsSection(
                                    title = "Clássica Música de Sarah",
                                    text = "Ao longo do jogo, a música que Sarah ouvia no seu aniversário aparece em momentos chave. A música simboliza tanto a perda quanto o amor que Joel ainda sente por ela. Essa canção traz um componente emocional profundo à narrativa do jogo."
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                EasterEggsSection(
                                    title = "Mensagem de Amor no Game",
                                    text = "Em algumas partes do jogo, é possível encontrar mensagens de amor e esperança espalhadas pelo mundo. Essas mensagens, muitas vezes escondidas em lugares improváveis, mostram o impacto que as pessoas tiveram na vida uns dos outros antes da epidemia."
                                )

                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    })
            })
    }
}