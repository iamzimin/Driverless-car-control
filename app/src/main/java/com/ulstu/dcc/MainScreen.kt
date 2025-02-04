package com.ulstu.dcc

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ulstu.block_scanner.presentation.BlockScannerRoot
import com.ulstu.block_scanner.presentation.WebViewPage
import com.ulstu.resource.R
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme
import com.ulstu.resource.ui.theme.VerticalPadding
import com.ulstu.settings.presentation.SettingsRoot
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentDes = navController.currentBackStackEntryAsState().value?.destination
    val isWebViewScreen = currentDes?.hasRoute(Route.WebView::class) == true

    var siteUrl by remember { mutableStateOf<String?>(null) }
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    ModalNavigationDrawer(
        modifier = Modifier
            .background(AppTheme.colors.background),
        drawerState = drawerState,
        gesturesEnabled = !isWebViewScreen,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .padding(end = 70.dp),
                drawerContainerColor = AppTheme.colors.tileBackground,
            ) {
                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    modifier = Modifier
                        .padding(NavigationDrawerItemDefaults.ItemPadding),
                    text = stringResource(id = R.string.app_name),
                    style = AppTheme.typography.heading,
                    color = AppTheme.colors.text,
                )

                Spacer(modifier = Modifier.height(18.dp))

                NavigationItem.items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = item.title,
                                style = AppTheme.typography.body,
                                color = AppTheme.colors.text,
                            )
                        },
                        selected = index == selectedItemIndex,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = Color.Transparent,
                            selectedContainerColor = AppTheme.colors.secondary,
                        ),
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                tint = AppTheme.colors.text,
                            )
                        },
                        shape = RectangleShape,
                    )
                }
            }
        },
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = AppTheme.colors.background,
            topBar = {
                val titleName = if (isWebViewScreen) {
                    siteUrl ?: "..."
                } else {
                    NavigationItem.items[selectedItemIndex].title
                }
                val icon = if (isWebViewScreen) {
                    Icons.AutoMirrored.Filled.ArrowBack
                } else {
                    Icons.Default.Menu
                }

                TopAppBar(
                    title = {
                        Text(
                            text = titleName,
                            style = AppTheme.typography.heading,
                            color = AppTheme.colors.text,
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = AppTheme.colors.background,
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            if (isWebViewScreen) {
                                navController.popBackStack()
                            } else {
                                scope.launch { drawerState.open() }
                            }
                        }) {
                            Icon(
                                imageVector = icon,
                                contentDescription = icon.name,
                                tint = AppTheme.colors.text,
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Route.BlockScanner,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable<Route.BlockScanner> {
                    BlockScannerRoot(
                        onWebViewScreen = { url ->
                            navController.navigate(Route.WebView(url = url))
                        }
                    )
                }
                composable<Route.Settings> {
                    SettingsRoot()
                }
                composable<Route.WebView> { entry ->
                    val webViewEntryUrl = entry.toRoute<Route.WebView>().url
                    siteUrl = webViewEntryUrl
                    WebViewPage(url = webViewEntryUrl)
                }
            }
        }
    }
}



@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun MainScreenPreview() {
    DriverlessCarControlTheme {
        MainScreen()
    }
}