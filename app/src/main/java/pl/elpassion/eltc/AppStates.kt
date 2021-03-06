package pl.elpassion.eltc

import pl.elpassion.eltc.builds.SelectableProject

sealed class AppState

object InitialState : AppState()
object LoadingBuildsState : AppState()

data class LoginState(
        val host: String = "",
        val user: String = "",
        val password: String = "",
        val error: Error? = null
) : AppState() {
    enum class Error(val message: String) {
        UNKNOWN_HOST("Unknown host"),
        INVALID_CREDENTIALS("Invalid credentials"),
        NETWORK_PROBLEM("Network problem")
    }
}

data class BuildsState(
        val builds: List<Build>,
        val projects: List<Project>
) : AppState()

data class SelectProjectsDialogState(
        val projects: List<SelectableProject>
) : AppState()

data class LoadingDetailsState(
        val build: Build
) : AppState()

data class DetailsState(
        val build: Build,
        val changes: List<Change>
) : AppState()

data class WebBrowserState(
        val url: String
) : AppState()