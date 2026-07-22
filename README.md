# Programação para Dispositivos Móveis · ULBRA

Código-fonte progressivo da disciplina **Programação para Dispositivos Móveis** (EAD, Android nativo com Kotlin e Jetpack Compose), Prof. Jean Pinzon.

O mesmo aplicativo cresce a cada módulo. Cada etapa vive em uma **branch** própria, para você acompanhar exatamente o que muda de uma aula para a outra.

## Você está na branch: `modulo-5`

**Estado e persistência de dados.** Dois destinos novos no menu: **Lista de tarefas** (`ui/screens/TarefasScreen.kt` + pacote `data/`), com persistência em banco **Room**, `ViewModel` e lista reativa (`Flow`); e **Consulta de CEP** (`ui/screens/CepScreen.kt` + `data/ViaCep.kt`), consumindo a API pública ViaCEP com **Retrofit**, tratando os três estados (carregando, sucesso, erro).

> Prova da persistência: adicione tarefas, **feche o app por completo** e reabra. As tarefas continuam lá. A consulta de CEP exige internet.

## Como rodar

1. Instale o **Android Studio** (veja o guia de setup do Módulo 1). Link: https://developer.android.com/studio
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
