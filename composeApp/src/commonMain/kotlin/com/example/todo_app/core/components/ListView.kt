package com.example.todo_app.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


internal fun LazyListState.reachedBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun <T> ListView(
    modifier: Modifier = Modifier,
    isLoadingMore: Boolean = false,
    listState: LazyListState = rememberLazyListState(),
    items: List<T>,
    itemKey: (T) -> Any,
    itemContent: @Composable (T) -> Unit,
    loadingItem: @Composable () -> Unit,
    loadMore: () -> Unit,
    itemGap: Dp = 8.dp,
    topPadding: Dp = 10.dp,
    bottomPadding: Dp = 80.dp,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit = {},
) {
    val state = rememberPullToRefreshState()

    // observe list scrolling
    val reachedBottom: Boolean by remember { derivedStateOf { listState.reachedBottom() } }


    // load more if scrolled to bottom
    LaunchedEffect(reachedBottom) {
        if (reachedBottom) {
            loadMore()
        }
    }


    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier,
        state = state,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                state = state
            )
        },
    ){
        LazyColumn(
            modifier = modifier,
            state = listState,
            verticalArrangement = Arrangement.spacedBy(itemGap),
            contentPadding = PaddingValues(
                top = topPadding,
                bottom = bottomPadding
            ),
        ) {
            items(
                items = items,
                key = { item: T -> itemKey(item) },
            ) { item ->
                itemContent(item)
            }
            if (isLoadingMore) {
                item {
                    loadingItem()
                }
            }

        }
    }
}