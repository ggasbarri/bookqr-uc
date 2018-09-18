package com.telecomuc.bookqr

sealed class FetchingState

object Fetching : FetchingState()
object Idle : FetchingState()
object Success : FetchingState()
object Failure : FetchingState()

