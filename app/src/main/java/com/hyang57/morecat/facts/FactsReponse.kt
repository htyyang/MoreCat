package com.hyang57.morecat.facts

data class FactsResponse(
    val data: List<String> = listOf() // Default to an empty list if 'facts' is missing
)