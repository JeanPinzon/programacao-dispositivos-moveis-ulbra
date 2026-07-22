# Programação para Dispositivos Móveis · ULBRA

Código-fonte progressivo da disciplina **Programação para Dispositivos Móveis** (EAD, Android nativo com Kotlin e Jetpack Compose), Prof. Jean Pinzon.

O mesmo aplicativo cresce a cada módulo. Cada etapa vive em uma **branch** própria, para você acompanhar exatamente o que muda de uma aula para a outra.

## Você está na branch: `modulo-4`

**Sensores e APIs do aparelho.** O menu ganha dois destinos novos: **Localização** (`ui/screens/LocalizacaoScreen.kt`), que pede a permissão em tempo de execução e mostra latitude/longitude via serviço de localização fundida; e **Acelerômetro** (`ui/screens/SensorScreen.kt`), que lê o sensor com `DisposableEffect`, registrando e liberando o ouvinte conforme o ciclo de vida. As permissões estão declaradas no `AndroidManifest.xml`.

> A localização funciona melhor em um **celular físico**. No emulador, defina uma posição nos controles estendidos.

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
