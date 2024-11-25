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
import com.example.apptlou.ui.components.Section
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

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
                                        text = "Sobre o Jogo",
                                        color = Color(0xFFFF6666),
                                        style = MaterialTheme.typography.displayMedium.copy(
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(8.dp)) // Espaçamento entre título e subtítulo
                                    Text(
                                        text = "Uma narrativa inesquecível em um mundo devastado.",
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
                                Section(
                                    title = "História",
                                    text = "Lançado em 14 de junho de 2013 pela Naughty Dog, The Last of Us narra a jornada de Joel e Ellie em um mundo devastado por uma infecção fúngica chamada Cordyceps, que transforma humanos em criaturas violentas. O jogo se passa 20 anos após o início da pandemia, onde sobreviventes lutam contra infectados e outros humanos em meio ao colapso da sociedade. Joel, um homem marcado por traumas, recebe a missão de escoltar Ellie, uma adolescente com imunidade misteriosa, através de um cenário pós-apocalíptico. Conforme enfrentam perigos, sua relação se aprofunda, explorando temas como perda, sacrifício e esperança, em uma narrativa emocionante e envolvente.",
                                    imageResId = R.drawable.historia
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Section(
                                    title = "Jogabilidade",
                                    text = "Criado pela renomada equipe da Naughty Dog, sob a liderança de Neil Druckmann e Bruce Straley, The Last of Us marcou a história dos videogames com sua abordagem inovadora. O jogo combina uma narrativa profundamente emocional com mecânicas de jogo imersivas, explorando questões humanas em um cenário sombrio e realista. A equipe utilizou uma avançada captura de movimentos e dublagem para dar vida aos personagens, resultando em performances impactantes. Além disso, a atenção aos detalhes no design dos ambientes e na trilha sonora de Gustavo Santaolalla contribuiu para criar uma experiência inesquecível, elevando o padrão da narrativa em jogos eletrônicos.",
                                    imageResId = R.drawable.logoempresa
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Section(
                                    title = "Impacto Cultural",
                                    text = "Amplamente reconhecido como um dos maiores jogos de todos os tempos, The Last of Us recebeu inúmeros Game of the Year Awards, consolidando seu lugar na história dos videogames. Sua narrativa envolvente e qualidade técnica revolucionaram o gênero, conquistando tanto críticos quanto jogadores ao redor do mundo. O impacto do jogo foi tão significativo que, em 2023, ele ganhou uma adaptação televisiva de sucesso pela HBO, que expandiu a história para um novo público, reforçando ainda mais seu legado como uma obra-prima cultural.",
                                    imageResId = R.drawable.tlouhbo
                                )
                            }
                        }
                    }
                )
            }
        )
    }
}
