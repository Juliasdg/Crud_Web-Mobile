const express = require("express");
const app = express();
const handlebars = require("express-handlebars").engine;
const bodyParser = require("body-parser");
const cookieParser = require("cookie-parser"); // Importar o cookie-parser
const { initializeApp, cert } = require("firebase-admin/app");
const { getFirestore } = require("firebase-admin/firestore");
const { getAuth } = require("firebase-admin/auth");
const serviceAccount = require("./firebase/chave.json");
const axios = require("axios");

// Inicializar Firebase Admin
initializeApp({
  credential: cert(serviceAccount),
});

const db = getFirestore();
const auth = getAuth();


app.engine("handlebars", handlebars({ defaultLayout: "main" }));
app.set("view engine", "handlebars");

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(cookieParser()); // Middleware de cookies
app.use(express.static("public"));
app.use(checkAuth); // Middleware global


// Middleware para verificar autenticação
function checkAuth(req, res, next) {
  const sessionCookie = req.cookies.session || ""; // Acessar cookies com cookie-parser
  auth
    .verifySessionCookie(sessionCookie, true)
    .then((decodedClaims) => {
      req.user = decodedClaims; // Adiciona os dados do usuário à requisição
      next();
    })
    .catch(() => {
      req.user = null; // Nenhum usuário autenticado
      next();
    });
}


// Login
app.get("/login", (req, res) => {
  res.render("login");
});

app.post("/login", async (req, res) => {
  const { email, password } = req.body;

  try {
    // Enviar uma solicitação para o Firebase REST API
    const response = await axios.post(
      "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword",
      {
        email,
        password,
        returnSecureToken: true,
      },
      {
        params: {
          key: "AIzaSyBjtPpxWN5iPD6VBWTYGe9jgQAsLAc6N1A",
        },
      }
    );

    // Extrair o token ID do usuário autenticado
    const idToken = response.data.idToken;

    // Criar um cookie de sessão a partir do token ID
    const sessionCookie = await auth.createSessionCookie(idToken, {
      expiresIn: 60 * 60 * 24 * 5 * 1000, // 5 dias
    }); 

    // Configurar o cookie de sessão no cliente
    res.cookie("session", sessionCookie, {
      httpOnly: true,
      secure: true,
    });

    // Redireciona para a página /home se estiver logado
    res.redirect("/");
  } catch (error) {
    console.error("Erro ao autenticar usuário:", error.response?.data || error.message);
    res.redirect("/login"); // Volta ao login em caso de erro
  }
});


// Logout
app.get("/logout", (req, res) => {
  res.clearCookie("session");
  res.redirect("/");
});

// Home acessível por todos
app.get("/", checkAuth, (req, res) => {
  res.render("index", { user: req.user });
});

// Outras rotas seguem o mesmo padrão
app.get("/sobre", checkAuth, (req, res) => {
  res.render("sobre", { user: req.user });
});

// Rota para a página de personagens
app.get("/personagens", checkAuth, function (req, res) {
  res.render("personagens", { user: req.user });
});

// Rota para a página de easter eggs
app.get("/easterEggs", checkAuth, function (req, res) {
  res.render("easterEggs", { user: req.user });
});

// Rota para a página de soundtrack
app.get("/soundtrack", checkAuth, function (req, res) {
  res.render("soundtrack", { user: req.user });
});

// Rota para a página de resistência
app.get("/resistencia", checkAuth, function (req, res) {
  res.render("resistencia", { user: req.user });
});

// Rota para a página de login (GET)
app.get("/login", function (req, res) {
  res.render("login");
});

// Rota para cadastro (GET)
app.get("/register", function (req, res) {
  res.render("register");  // Renderiza o formulário de cadastro
});

// Rota de consulta no Firestore
app.get("/consulta", checkAuth, async function (req, res) {
  if (!req.user) {
    return res.redirect("/login");
  }
  try {
    const resistentesRef = db.collection('resistentes');
    const querySnapshot = await resistentesRef.select('nome', 'idade', 'especialidade', 'localizacao', 'observacao').get();
    const resistentes = [];

    querySnapshot.forEach((doc) => {
      resistentes.push({ id: doc.id, ...doc.data() });
    });

    res.render("consulta", { resistentes, user: req.user });
  } catch (error) {
    console.error("Erro ao buscar documentos: ", error);
    res.status(500).send("Erro ao buscar documentos");
  }
});

// Rota para editar um item
app.get("/editar/:id", checkAuth, async function (req, res) {
  const resistenteId = req.params.id;

  db.collection('resistentes').doc(resistenteId).get()
    .then((doc) => {
      if (!doc.exists) {
        res.status(404).send("Resistente não encontrado");
      } else {
        res.render("editar", { resistentes: { id: doc.id, data: doc.data() } });
      }
    })
    .catch((error) => {
      console.log("Erro ao recuperar documento:", error);
      res.status(500).send("Erro ao recuperar documento");
    });
});

// Rota para atualizar um item
app.post("/atualizar/:id", async function (req, res) {
  try {
    const docRef = db.collection('resistentes').doc(req.params.id);
    await docRef.update({
      nome: req.body.nome,
      idade: req.body.idade,
      especialidade: req.body.especialidade,
      localizacao: req.body.localizacao,
      observacao: req.body.observacao
    });
    console.log('Documento atualizado com sucesso');
    res.redirect('/consulta');
  } catch (error) {
    console.error("Erro ao atualizar documento: ", error);
    res.status(500).send("Erro ao atualizar documento");
  }
});

// Rota para excluir um item
app.get("/excluir/:id", async function (req, res) {
  try {
    await db.collection('resistentes').doc(req.params.id).delete();
    console.log('Documento excluído com sucesso');
    res.redirect('/consulta');
  } catch (error) {
    console.error("Erro ao excluir documento: ", error);
    res.status(500).send("Erro ao excluir documento");
  }
});

// Rota para adicionar um novo resistente
app.post("/cadastrar", function (req, res) {
  db.collection('resistentes').add({
    nome: req.body.nome,
    idade: req.body.idade,
    especialidade: req.body.especialidade,
    localizacao: req.body.localizacao,
    observacao: req.body.observacao
  }).then(function () {
    console.log('Documento adicionado com sucesso');
    res.redirect('/');
  }).catch(function (error) {
    console.error("Erro ao cadastrar documento: ", error);
    res.status(500).send("Erro ao cadastrar documento");
  });
});

// Iniciando o servidor
app.listen(8081, function () {
  console.log("Servidor ativo na porta 8081!");
});
