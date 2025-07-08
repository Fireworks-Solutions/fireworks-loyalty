package com.fireworks.myeventsdk.model.rewards_search

import com.fireworks.myeventsdk.Utils.Constants
import com.fireworks.myeventsdk.model.DashboardSearchItem
import com.fireworks.myeventsdk.model.DirectoryResultsItem
import com.fireworks.myeventsdk.model.MemberItem


data class SearchRewardItem(
    val itemTitle: String,
    val itemId: String? = null,
    val itemType: String? = "",
    val itemUnits: String? = "",
    val module: String? = null
)

//fun List<DirectoryResultsItem>.getSearchMerchantResults(
//    searchTerm: String
//): List<SearchRewardItem> {
//    val staticItemsAtTheEnd = listOf(
//        SearchRewardItem(
//            "Search for $searchTerm in",
//            module = Constants.SEARCH_IN_MERCHANT
//        ),
//    )
//    return this.map {
//        SearchRewardItem(
//            itemId = it.id.toString(),
//            itemTitle = it.title.toString(),
//            module = Constants.SEARCH_IN_MERCHANT
//        )
//    } + staticItemsAtTheEnd
//}
//
//fun List<MemberItem>.getSearchMemberResults(
//    searchTerm: String
//): List<SearchRewardItem> {
//    val staticItemsAtTheEnd = listOf(
//        SearchRewardItem(
//            "Search for $searchTerm in",
//            module = Constants.SEARCH_IN_MEMBER
//        ),
//    )
//    return this.map {
//        SearchRewardItem(
//            itemTitle = it.name.toString(),
//            itemId = it.custid,
//            module = Constants.SEARCH_IN_MEMBER
//        )
//    } + staticItemsAtTheEnd
//}
//
//fun List<DashboardSearchItem>.getSearchDashboardResults(
//    searchTerm: String
//): List<SearchRewardItem> {
//    val staticItemsAtTheEnd = listOf(
//        SearchRewardItem(
//            "Search for $searchTerm in",
//            module = Constants.SEARCH_IN_VOUCHER,
//        ),
//        SearchRewardItem(
//            "Search for $searchTerm in",
//            module = Constants.SEARCH_IN_MERCHANT,
//        ),
//        SearchRewardItem(
//            "Search for $searchTerm in",
//            module = Constants.SEARCH_IN_MEMBER,
//        ),
//        SearchRewardItem(
//            "Search for $searchTerm in",
//            module = Constants.SEARCH_IN_DEAL,
//        ),
//    )
//    return this.map {
//        SearchRewardItem(
//            itemTitle = it.name,
//            itemId = it.id,
//            module = when (it.module) {
//                "Coupon" -> Constants.SEARCH_IN_VOUCHER
//                "Deal" -> Constants.SEARCH_IN_DEAL
//                "Merchant" -> Constants.SEARCH_IN_MERCHANT
//                "Member" -> Constants.SEARCH_IN_MEMBER
//                else -> Constants.SEARCH_IN_VOUCHER
//            },
//        )
//    } + staticItemsAtTheEnd
//}
//

