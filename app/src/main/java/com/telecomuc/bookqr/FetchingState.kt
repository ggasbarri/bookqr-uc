package com.telecomuc.bookqr

sealed class FetchingState {
    object Fetching : FetchingState()
    object Idle : FetchingState()
    object Failure : FetchingState()
}


