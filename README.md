# Programação para Dispositivos Móveis · ULBRA

Código-fonte progressivo da disciplina **Programação para Dispositivos Móveis** (EAD, Android nativo com Kotlin e Jetpack Compose), Prof. Jean Pinzon.

O mesmo aplicativo cresce a cada módulo. Cada etapa vive em uma **branch** própria, para você acompanhar exatamente o que muda de uma aula para a outra.

## Você está na branch: `modulo-6`

**Publicação e distribuição.** Configuração de **release** no `app/build.gradle.kts`: `versionCode`/`versionName`, minificação (R8) e assinatura por `signingConfig` que lê as credenciais de um `keystore.properties` (fora do Git). Há um teste de unidade (`app/src/test/...CalculadoraTest.kt`) e a tela **Sobre**, que mostra a versão via `BuildConfig`.

### Gerar uma versão assinada (AAB)

1. Crie a sua chave: **Build > Generate Signed App Bundle / APK**, opção *Android App Bundle*, e em *Key store path* clique em **Create new**. Guarde o arquivo `.jks` e as senhas com segurança.
2. Para assinar pela linha de comando, copie `keystore.properties.example` para `keystore.properties`, preencha os dados e rode:
   ```bash
   ./gradlew bundleRelease
   ```
3. Rodar os testes: `./gradlew test`

> A perda da keystore impede publicar atualizações do app. Faça backup dela.

## Como rodar

1. Instale o **Android Studio**. A Google mantém um guia oficial passo a passo, em português e com imagens de cada tela:
   https://developer.android.com/codelabs/basic-android-kotlin-compose-install-android-studio?hl=pt-br
   (download direto: https://developer.android.com/studio)
2. Clone o repositório e abra a pasta no Android Studio:
   ```bash
   git clone https://github.com/JeanPinzon/programacao-dispositivos-moveis-ulbra.git
   cd programacao-dispositivos-moveis-ulbra
   ```
3. Troque para a branch do módulo que quer estudar:
   ```bash
   git checkout modulo-1
   ```
4. No Android Studio, aguarde o **Gradle sync** terminar, escolha um emulador ou celular e clique em **Run** (▶).

## As branches (progressão)

| Branch | Módulo | O que adiciona |
|--------|--------|----------------|
| `modulo-1` | Fundamentos | Projeto base + saudação (Hello World) |
| `modulo-2` | Aplicações nativas | Ciclo de vida da Activity com logs (Logcat) |
| `modulo-3` | Interfaces | Jetpack Compose: menu, cartão de perfil e navegação |
| `modulo-4` | Sensores e APIs | Permissões, localização (GPS) e acelerômetro |
| `modulo-5` | Estado e persistência | Lista de tarefas com Room e consulta a API REST |
| `modulo-6` | Publicação | Configuração de release, assinatura e teste |
| `main` | Guia | App final + este guia |

> Dica: para ver **o que mudou** entre duas aulas, use por exemplo
> `git diff modulo-2 modulo-3`.

## Requisitos

- Android Studio (versão recente).
- JDK 17 (já vem embutido no Android Studio).
- Um emulador ou um celular Android com Depuração USB ativada.

---

Material didático completo (apostilas dos 6 módulos): trilha da disciplina no ambiente AULA.
