package ai.rever.boss.app

import ai.rever.boss.components.window_panel.SplitDirection
import ai.rever.boss.window.MenuActionsHandler
import ai.rever.boss.window.NativeTitleBarAction

/** Match BossTerm's session controls, using the existing window-scoped menu actions. */
internal fun nativeSessionTitleActions(state: BossAppState): List<NativeTitleBarAction> =
    listOf(
        NativeTitleBarAction("new", "New tab", "plus") { MenuActionsHandler.triggerNewTab(state.windowId) },
        NativeTitleBarAction("split_vertical", "Split left/right", "rectangle.split.2x1") {
            state.openSplitTabDialog(SplitDirection.RIGHT)
        },
        NativeTitleBarAction("split_horizontal", "Split top/bottom", "rectangle.split.1x2") {
            state.openSplitTabDialog(SplitDirection.DOWN)
        },
    )

internal fun nativeMoreTitleAction(state: BossAppState): NativeTitleBarAction =
    NativeTitleBarAction(
        "more",
        "More actions",
        "ellipsis",
        menu =
            listOf(
                NativeTitleBarAction("settings", "Settings…") { state.settingsWindow.open() },
                NativeTitleBarAction("logout", "Sign out…") { state.showLogoutDialog = true },
            ),
        onClick = {},
    )

private fun BossAppState.openSplitTabDialog(direction: SplitDirection) {
    splitViewState.requestSplitWithNewTab(splitViewState.activePanelId, direction)
    newTabDialogInitialType = null
    showNewTabDialog = true
}
