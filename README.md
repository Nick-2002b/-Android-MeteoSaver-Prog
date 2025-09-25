## Relazione Finale: MeteoSaver App

## 1. Scopo del Progetto

**Obiettivo Principale:** Sviluppare un'applicazione Android nativa, chiamata "MeteoSaver", che permetta agli utenti di consultare le condizioni meteorologiche attuali di diverse città.

### **Funzionalità Chiave:**

> **Ricerca Dati Online:** L'app recupera dati meteo in tempo reale da un'API remota [ OpenWeather tramite RapidAPI](https://rapidapi.com/worldapi/api/open-weather13).

> **Salvataggio Locale:** Permette di salvare le città preferite in un database locale (usando Room) per una consultazione rapida e per l'accesso offline ai dati più recenti.

> **Gestione Completa (CRUD):** L'utente può aggiungere nuove città tramite una ricerca, visualizzarle in una lista, aggiornare i dati di tutte le città salvate e eliminare quelle che non interessano più.

> **Interfaccia Utente Moderna:** L'app è costruita con Jetpack Compose, offrendo un'interfaccia dichiarativa, reattiva e moderna.

## 2. Descrizione della Struttura dei Sorgenti

**Architettura Generale:** Il progetto è basato sui principi della `Clean Architecture` per garantire la separazione delle responsabilità `"Separation of Concerns"`, la manutenibilità e la testabilità del codice. L'architettura è suddivisa in tre moduli principali: `:domain`, `:data`, e `:ui`.

**Modulo** `:domain`

Scopo: Contiene la logica di business pura dell'applicazione, senza alcuna dipendenza da Android. È il cuore dell'app.

#### Componenti:

- **Models** `Weather.kt`: Definisce l'oggetto principale dell'app la `data class Weather`
  . Rappresenta la "verità" universale per tutti gli altri moduli.

- **Repository (Interfaccia)** `WeatherRepository.kt`: Definisce il "contratto" per l'accesso ai dati. Specifica cosa si può fare (`weatherList` `fetchRemoteWeatherByCity`, `refreshAllCities`, `deleteCity`), ma non come.

- **Use Cases:** Rappresentano singole azioni specifiche che l'utente può compiere. Fanno da ponte tra il ViewModel e il Repository, promuovendo il Single Responsibility Principle (`FetchRemoteWeatherUseCase.kt`, `DeleteCityUseCase`, `GetWeatherListUseCase`, `RefreshAllCitiesUseCase`).

---

**Modulo** `:data`

**Scopo:** Implementa il contratto definito nel `:domain`. Si occupa di gestire le fonti di dati concrete (rete e database).

#### Componenti:

- **Repository (Implementazione)** `WeatherRepositoryImpl.kt`: Implementa l'interfaccia WeatherRepository. Contiene la logica per decidere se prendere i dati dalla rete o dal database.

- **Data Sources** (**Remote** & **Local**):

  - **Remote**: Gestisce le chiamate di rete usando Retrofit e la conversione JSON con Moshi. Include i `DTO` (Data Transfer Objects), che mappano la risposta dell'API.

  - **Local**: Gestisce il database locale usando Room. Include le Entità (le tabelle del DB) e i `DAO` (Data Access Objects) per le query.

- **Mappers**: Funzioni di supporto che convertono gli oggetti tra i vari livelli (es. da DTO a Entità, e da Entità a Modello di Dominio).

---

**Modulo** `:ui`

**Scopo:** Contiene tutto ciò che riguarda l'interfaccia utente. Adotta un pattern **MVVM (Model-View-ViewModel)**.

#### Componenti:

- **UI (Composable)** (`HomeScreen.kt`, `DescriptionScreen.kt`): Schermate e componenti grafici costruiti con Jetpack Compose. Sono "stupidi" e si limitano a mostrare lo stato che ricevono.

- **ViewModel** (`WeatherViewModel.kt`): Contiene la logica della UI. Non parla mai direttamente con il modulo `:data`, ma solo con gli Use Case. Espone i dati alla UI tramite StateFlow, rendendo l'interfaccia reattiva.

- **Navigazione:** Gestita con Navigation Compose in modo type-safe, usando oggetti serializzabili per definire le rotte.

## 3. Punti di Forza (Scelte Architetturali)

- **Architettura Pulita e Modulare:** La divisione in moduli (`:domain`, `:data`, `:ui`) garantisce un basso accoppiamento. È possibile sostituire la fonte di dati (es. cambiare API o database) modificando solo il modulo `:data`, senza impattare la logica di business o la UI.

- **Single Source of Truth (SSOT):** L'architettura adotta il database Room come unica fonte di verità per i dati mostrati all'utente. La UI osserva i dati solo dal database tramite un Flow. Le chiamate di rete servono unicamente ad aggiornare il database, che poi notifica automaticamente la UI. Questo rende l'app robusta, reattiva e funzionante anche offline.

- **UI Reattiva e Dichiarativa:** L'uso di Jetpack Compose insieme a StateFlow nel ViewModel crea un flusso di dati unidirezionale. La UI si aggiorna automaticamente al variare dello stato, senza bisogno di codice imperativo per manipolare manualmente le viste.

- **Iniezione delle Dipendenze (Manuale):** L'uso di Factory per i ViewModel e di Provider per Use Case e Repository, seppur manuale, applica il principio di Dependency Injection. I componenti non creano le proprie dipendenze, ma le ricevono dall'esterno, rendendoli più flessibili e disaccoppiati.

## 4. Possibili Migliorie

1. **Geolocalizzazione:** Implementare la funzionalità "meteo nella mia posizione attuale". Questo richiederebbe l'aggiunta della gestione dei permessi di localizzazione in runtime.

2. **Previsioni Meteo (Seconda Chiamata API):** Arricchire la schermata di dettaglio con le previsioni per le ore o i giorni successivi. .

3. **Dependency Injection con Hilt:** Sostituire l'attuale sistema di provider manuali con Hilt, il framework di dependency injection raccomandato da Google per Android. Questo ridurrebbe il codice "boilerplate" e automatizzerebbe la gestione delle dipendenze.

4. **Test Unitari e di Integrazione:** Scrivere un set completo di test (unit test per ViewModel/Use Case, test di integrazione per i DAO di Room, test della UI con Compose) per garantire la stabilità e la correttezza dell'applicazione.
